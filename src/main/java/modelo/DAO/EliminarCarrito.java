package modelo.DAO;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DTO.Producto;

/**
 * Servlet implementation class EliminarCarrito
 */
@WebServlet("/EliminarCarrito")
public class EliminarCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarCarrito() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Producto> carrito = new ArrayList();
		
		if((ArrayList<Producto>) session.getAttribute("carrito") != null && request.getParameter("id") != null) {
		carrito = (ArrayList<Producto>) session.getAttribute("carrito");
		int id = Integer.parseInt(request.getParameter("id"));
		
		boolean eliminado = false;
		for (int i = 0; i < carrito.size() || !eliminado; i++) {
			if(carrito.get(i).getId() == id && !eliminado) {
				carrito.remove(i);
				eliminado = true;
				i--;
			}
			session.setAttribute("carrito", carrito);
		}
		
		response.sendRedirect(request.getContextPath() + "/Carrito");

		}else {
			response.sendRedirect(request.getContextPath() + "/Carrito");

		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
