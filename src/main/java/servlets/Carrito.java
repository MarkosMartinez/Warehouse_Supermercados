package servlets;

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
 * Servlet implementation class Carrito
 */
@WebServlet("/Carrito")
public class Carrito extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Carrito() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ArrayList<Producto> carrito = new ArrayList();
		if((ArrayList<Producto>) session.getAttribute("carrito") != null) {
			carrito = (ArrayList<Producto>) session.getAttribute("carrito");
			if(carrito.size() > 0) {
				
			request.setAttribute("carrito", carrito);
			request.getRequestDispatcher("carrito.jsp").forward(request, response);
			}else{
				response.sendRedirect(request.getContextPath() + "/Inicio");
			}
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
