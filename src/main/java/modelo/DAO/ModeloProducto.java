package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import modelo.DTO.Producto;

public class ModeloProducto {
	
	public ModeloProducto() {
		
	}

	public ArrayList<Producto> cargarProductos() {
		Conector con = new Conector();
		con.conectar();
		ArrayList<Producto> productos = new ArrayList<>();
		ModeloSeccion mseccion = new ModeloSeccion();
		
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
				producto.setSeccion(mseccion.cargarSeccion(resultado.getInt("id_seccion")));
				productos.add(producto);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		con.cerrar();
		return productos;
	}

	public void insertar(int codigo, String nombre, int cantidad, Double precio, Date caducidad) {
		Conector con = new Conector();
		con.conectar();
		
		try {
			PreparedStatement pSt = con.getCon().prepareStatement("INSERT INTO `productos`(`codigo`, `nombre`, `cantidad`, `precio`, `caducidad`) VALUES (?, ?, ?, ?, ?)");
			pSt.setInt(1, codigo);
			pSt.setString(2, nombre);
			pSt.setInt(3, cantidad);
			pSt.setDouble(4, precio);
			pSt.setDate(5, new java.sql.Date(caducidad.getTime()));
			pSt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		con.cerrar();
		
	}
	
	
	
}
