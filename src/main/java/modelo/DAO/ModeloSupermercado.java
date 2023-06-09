package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Supermercado;

public class ModeloSupermercado {
	
	public ModeloSupermercado() {
		
	}

	public ArrayList<Supermercado> cargarSupermercados() {
		Conector con = new Conector();
		con.conectar();
		ArrayList<Supermercado> supermercados = new ArrayList<>();
		
		try {
			PreparedStatement pSt = con.getCon().prepareStatement("SELECT * FROM supermercados;");
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Supermercado supermercado = new Supermercado();
				supermercado.setId(resultado.getInt("id"));
				supermercado.setNombre(resultado.getString("nombre"));
				supermercados.add(supermercado);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		con.cerrar();
		return supermercados;
	}

	public void insertarProducto(int idProducto, int idSupermercado) {
		Conector con = new Conector();
		con.conectar();
		
		try {
			PreparedStatement pSt = con.getCon().prepareStatement("INSERT INTO `productos_supermercados`(`id_producto`, `id_supermercado`) VALUES (?, ?)");
			pSt.setInt(1, idProducto);
			pSt.setInt(2, idSupermercado);
			pSt.execute();
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		con.cerrar();
		
	}

	public boolean comprobarProducto(int id) {
		boolean existe = false;
		Conector con = new Conector();
		con.conectar();
		
		try {
			PreparedStatement pSt = con.getCon().prepareStatement("SELECT * FROM productos_supermercados WHERE id_producto = ?;");
			pSt.setInt(1, id);
			ResultSet resultado = pSt.executeQuery();
			if(resultado.next()) {
				existe = true;
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		con.cerrar();
		return existe;
	}
	
	public void eliminarProductoDeSupermercados(int id) {
		Conector con = new Conector();
		 con.conectar();
		    
		    try {
		        PreparedStatement pStEliminar = con.getCon().prepareStatement("DELETE FROM `productos_supermercados` WHERE id_producto = ?");
		        pStEliminar.setInt(1, id);
		        pStEliminar.execute();
		        pStEliminar.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		con.cerrar();
	}


}
