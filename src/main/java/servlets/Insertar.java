package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.ModeloProducto;
import modelo.DAO.ModeloSeccion;
import modelo.DAO.ModeloSupermercado;
import modelo.DTO.Seccion;
import modelo.DTO.Supermercado;

/**
 * Servlet implementation class Insertar
 */
@WebServlet("/Insertar")
public class Insertar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insertar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Seccion> secciones = new ArrayList<>();
		ModeloSeccion mseccion = new ModeloSeccion();
		secciones = mseccion.getSecciones();
		ModeloSupermercado msupermercado = new ModeloSupermercado();
		ArrayList<Supermercado> supermercados = new ArrayList<>();
		supermercados = msupermercado.cargarSupermercados();
		String aviso = null;
		aviso = request.getParameter("aviso");
		
		request.setAttribute("supermercados", supermercados);
		request.setAttribute("aviso", aviso);
		request.setAttribute("secciones", secciones);
		request.getRequestDispatcher("insert.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		Double precio = Double.parseDouble(request.getParameter("precio"));
		Date caducidad = null;
		String[] supermercados = request.getParameterValues("supermercados");
		
		//Aplicando formato a la fecha de caducidad
		try {
			caducidad = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("caducidad"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ModeloProducto mproducto = new ModeloProducto();
		
		//Comprobar si el codigo del producto ya existe
		boolean existe = mproducto.comprobarCodigo(codigo);
		
		//Si no lo existe comprueba si es valido y lo inserta
		if(!existe) {
			Date hoy = new Date();
			boolean valido = cantidad>=0 && precio>=0 && caducidad.after(hoy) && request.getParameter("seccion") != null;
			if(valido) {
					int seccion = Integer.parseInt(request.getParameter("seccion"));
					mproducto.insertar(codigo, nombre, cantidad, precio, caducidad, seccion);
					ModeloSupermercado msupermercado = new ModeloSupermercado();
					int idProducto = mproducto.getIdProductoPorCodigo(codigo);
					for (String idsupermercado : supermercados) {
					msupermercado.insertarProducto(idProducto, Integer.parseInt(idsupermercado));
					}
					response.sendRedirect(request.getContextPath() + "/Inicio");
			}else {
				response.sendRedirect(request.getContextPath() + "/Insertar?aviso=error");
			}
		}else {
			response.sendRedirect(request.getContextPath() + "/Insertar?aviso=error");
		}
	}

}
