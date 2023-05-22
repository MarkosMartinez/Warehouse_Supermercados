package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

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
		if (busqueda != null) {
		    Iterator<Producto> iterator = productos.iterator();
		    while (iterator.hasNext()) {
		        Producto producto = iterator.next();
		        String idString = String.valueOf(producto.getId());
		        if (!producto.getNombre().toLowerCase().contains(busqueda) && !idString.contains(busqueda)) {
		            iterator.remove();
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
