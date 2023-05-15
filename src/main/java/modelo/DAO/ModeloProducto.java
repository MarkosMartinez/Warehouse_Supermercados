package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Producto;

public class ModeloProducto {
	
	public ModeloProducto() {
		
	}

	public ArrayList<Producto> cargarProductos() {
		Conector con = new Conector();
		con.conectar();
		ArrayList<Producto> productos = new ArrayList<>();
		
		try {
			PreparedStatement pSt = con.getCon().prepareStatement("SELECT * FROM productos;");
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Producto producto = new Producto();
				producto.setId(resultado.getInt("id"));
				producto.setCodigo(resultado.getInt("codigo"));
				producto.setNombre(resultado.getString("nombre"));
				producto.setCantidad(resultado.getInt("cantidad"));
				producto.setPrecio(resultado.getDouble("precio"));
				producto.setCaducidad(resultado.getDate("caducidad"));
				producto.setId_seccion(resultado.getInt("id_seccion"));
				productos.add(producto);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		con.cerrar();
		return productos;
	}
	
	
	
}
