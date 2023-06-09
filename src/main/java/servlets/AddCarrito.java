package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.DAO.ModeloProducto;
import modelo.DTO.Producto;

/**
 * Servlet implementation class Comprar
 */
@WebServlet("/AddCarrito")
public class AddCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCarrito() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idString = request.getParameter("id");
		if(idString != null) {
			int id = Integer.parseInt(idString);
			Producto producto = new Producto();
			ModeloProducto mproducto = new ModeloProducto();
			producto = mproducto.getProducto(id);
			HttpSession session = request.getSession();
			ArrayList<Producto> carrito = new ArrayList();
			
			//Si el carrito, desde el login, esta vacio, omite el get.
			if((ArrayList<Producto>) session.getAttribute("carrito") != null) {
			carrito = (ArrayList<Producto>) session.getAttribute("carrito");
			}
			
			carrito.add(producto);
			session.setAttribute("carrito", carrito);
			response.sendRedirect(request.getContextPath() + "/Inicio");
		}else {
			response.sendRedirect(request.getContextPath() + "/Inicio");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
