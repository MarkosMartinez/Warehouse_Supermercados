package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import modelo.DTO.Producto;
import modelo.DTO.Seccion;

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
				producto.setCodigo(resultado.getString("codigo"));
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

	public void insertar(String codigo, String nombre, int cantidad, Double precio, Date caducidad, int seccion) {
		Conector con = new Conector();
		con.conectar();
		
		try {
			PreparedStatement pSt = con.getCon().prepareStatement("INSERT INTO `productos`(`codigo`, `nombre`, `cantidad`, `precio`, `caducidad`, `id_seccion`) VALUES (?, ?, ?, ?, ?, ?)");
			pSt.setString(1, codigo);
			pSt.setString(2, nombre);
			pSt.setInt(3, cantidad);
			pSt.setDouble(4, precio);
			pSt.setDate(5, new java.sql.Date(caducidad.getTime()));
			pSt.setInt(6, seccion);
			pSt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		con.cerrar();
		
	}

	public boolean comprobarCodigo(String codigo) {
	    boolean existe = false;
	    Conector con = new Conector();
	    con.conectar();
	    
	    try {
	        PreparedStatement pSt = con.getCon().prepareStatement("SELECT codigo FROM productos WHERE codigo = ?;");
	        pSt.setString(1, codigo);
	        ResultSet resultado = pSt.executeQuery();
	        if (resultado.next()) {
	            existe = true;
	        }
	        pSt.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	    con.cerrar();
	    return existe;
	}

	public Producto getProducto(int id) {
		 Conector con = new Conector();
		 con.conectar();
		 Producto producto = new Producto();
		 Seccion seccion = new Seccion();
		    
		    try {
		        PreparedStatement pSt = con.getCon().prepareStatement("SELECT * FROM productos WHERE id = ?;");
		        pSt.setInt(1, id);
		        ResultSet resultado = pSt.executeQuery();
		        if (resultado.next()) {
		        	producto.setId(id);
		            producto.setCodigo(resultado.getString("codigo"));
		            producto.setNombre(resultado.getString("nombre"));
		            producto.setCantidad(resultado.getInt("cantidad"));
		            producto.setPrecio(resultado.getDouble("precio"));
		            producto.setCaducidad(resultado.getDate("caducidad"));
		            seccion.setId(resultado.getInt("id_seccion"));
		            producto.setSeccion(seccion);
		        }
		        pSt.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		con.cerrar();
		return producto;
	}

	public void modificarProducto(Producto productoModificado) {
		 Conector con = new Conector();
		 con.conectar();
		    
		    try {
		        PreparedStatement pSt = con.getCon().prepareStatement("UPDATE `productos` SET `codigo`= ?, `nombre`= ?, `cantidad`= ?, `precio`= ?, `caducidad`= ?, `id_seccion`= ? WHERE id = ?");
		        pSt.setString(1, productoModificado.getCodigo());
		        pSt.setString(2, productoModificado.getNombre());
		        pSt.setInt(3, productoModificado.getCantidad());
		        pSt.setDouble(4, productoModificado.getPrecio());
		        pSt.setDate(5, new java.sql.Date(productoModificado.getCaducidad().getTime()));
		        pSt.setInt(6, productoModificado.getSeccion().getId());
		        pSt.setInt(7, productoModificado.getId());
		        pSt.execute();
		        pSt.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		con.cerrar();
		
	}

	public void eliminarProducto(int id) {
		 Conector con = new Conector();
		 con.conectar();
		    
		    try {
		        PreparedStatement pStEliminar = con.getCon().prepareStatement("DELETE FROM `productos` WHERE id = ?");
		        pStEliminar.setInt(1, id);
		        pStEliminar.execute();
		        pStEliminar.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		con.cerrar();
	}

	public int getIdProductoPorCodigo(String codigo) {
		Conector con = new Conector();
		 con.conectar();
		 int idProducto = -1;
		    
		    try {
		        PreparedStatement pSt = con.getCon().prepareStatement("SELECT id FROM productos WHERE codigo = ?;");
		        pSt.setString(1, codigo);
		        ResultSet resultado = pSt.executeQuery();
		        if (resultado.next()) {
		        	idProducto =  resultado.getInt("id");
		        }
		        pSt.close();
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    
		con.cerrar();
		return idProducto;
	}

	
	
}
