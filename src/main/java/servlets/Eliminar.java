package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modelo.DAO.ModeloProducto;
import modelo.DAO.ModeloSupermercado;

/**
 * Servlet implementation class Eliminar
 */
@WebServlet("/Eliminar")
public class Eliminar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eliminar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id; 
		if(request.getParameter("id") != null) {
			id = Integer.parseInt(request.getParameter("id"));
			ModeloProducto mproducto = new ModeloProducto();
			ModeloSupermercado msupermercado = new ModeloSupermercado();
			int cantidad = mproducto.getCantidad(id);
			if(cantidad <= 0) {
				boolean existe = msupermercado.comprobarProducto(id);
				if(!existe) {
				mproducto.eliminarProducto(id);
				}else {
					msupermercado.eliminarProductoDeSupermercados(id);
				}
			}else {
				mproducto.eliminarStock(id);
			}
		}
		response.sendRedirect(request.getContextPath() + "/Inicio");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
