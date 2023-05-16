package servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.ModeloProducto;

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
		request.getRequestDispatcher("insert.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int codigo = Integer.parseInt(request.getParameter("codigo"));
		String nombre = request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		Double precio = Double.parseDouble(request.getParameter("precio"));
		Date caducidad = null;
		try {
			caducidad = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("caducidad"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		ModeloProducto mproducto = new ModeloProducto();
		
		mproducto.insertar(codigo, nombre, cantidad, precio, caducidad);
		response.sendRedirect(request.getContextPath() + "/Inicio");
	}

}
