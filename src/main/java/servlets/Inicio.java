package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.ModeloProducto;
import modelo.DTO.Producto;

/**
 * Servlet implementation class Inicio
 */
@WebServlet("/Inicio")
public class Inicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inicio() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ModeloProducto mproducto = new ModeloProducto();
		ArrayList<Producto> productos = mproducto.cargarProductos();
		String busqueda = request.getParameter("buscar");
		if(busqueda != null) {
			busqueda = busqueda.toLowerCase();
		}
		if(busqueda != null) {
			for (int i = 0; i < productos.size(); i++) {
				String idString = "";
				idString += productos.get(i).getId();
				if(!productos.get(i).getNombre().toLowerCase().contains(busqueda) && !idString.contains(busqueda)) {
					productos.remove(i);
					i--;
				}
			}
		}
		
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("inicio.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String busqueda = request.getParameter("busqueda");
		if (busqueda != null && busqueda != "") {
			response.sendRedirect(request.getContextPath() + "/Inicio?buscar=" + busqueda);
		}else {
			response.sendRedirect(request.getContextPath() + "/Inicio");
		}
	}

}
