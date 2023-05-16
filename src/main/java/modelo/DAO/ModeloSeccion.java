package modelo.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.DTO.Seccion;

public class ModeloSeccion {
	
	public ModeloSeccion() {
		
	}

	public Seccion cargarSeccion(int id) {
		Conector con = new Conector();
		Seccion seccion = new Seccion();
		con.conectar();
		try {
			PreparedStatement pSt = con.getCon().prepareStatement("SELECT * FROM secciones WHERE id = ?;");
			pSt.setInt(1, id);
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				seccion.setId(resultado.getInt("id"));
				seccion.setNombre(resultado.getString("nombre"));
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.cerrar();
		return seccion;
	}

	public ArrayList<Seccion> getSecciones() {
		Conector con = new Conector();
		ArrayList<Seccion> secciones = new ArrayList<>();
		
		con.conectar();
		try {
			PreparedStatement pSt = con.getCon().prepareStatement("SELECT * FROM secciones");
			ResultSet resultado = pSt.executeQuery();
			while(resultado.next()) {
				Seccion seccion = new Seccion();
				seccion.setId(resultado.getInt("id"));
				seccion.setNombre(resultado.getString("nombre"));
				secciones.add(seccion);
			}
			pSt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		con.cerrar();
		return secciones;
	}
	
	

}
