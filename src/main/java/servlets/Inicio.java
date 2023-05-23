package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
		String busqueda = request.getParameter("busqueda");
		double min = 0;
		if(request.getParameter("min") != null && request.getParameter("min") != "")
			min = Integer.parseInt(request.getParameter("min"));
		double max = Double.POSITIVE_INFINITY;
		if(request.getParameter("max") != null && request.getParameter("max") != "")
			max = Integer.parseInt(request.getParameter("max"));
		if (busqueda != null) {
			busqueda = busqueda.toLowerCase();
		    Iterator<Producto> iterator = productos.iterator();
		    while (iterator.hasNext()) {
		        Producto producto = iterator.next();
		        String codigoString = String.valueOf(producto.getCodigo());
		        if (!producto.getNombre().toLowerCase().contains(busqueda) && !codigoString.contains(busqueda)) {
		            iterator.remove();
		        }
		    }
		}
		
		    Iterator<Producto> iterator = productos.iterator();
		    while (iterator.hasNext()) {
		        Producto producto = iterator.next();
		        if (producto.getPrecio() < min || producto.getPrecio() > max) {
		            iterator.remove();
		        }
		    }
		    
		String orden = request.getParameter("codOrden");
		
		if (orden != null && (orden.equalsIgnoreCase("asc") || orden.equalsIgnoreCase("desc"))) {
	        Comparator<Producto> codigoComparator = new Comparator<Producto>() {
	            public int compare(Producto p1, Producto p2) {
	                return p1.getCodigo().compareTo(p2.getCodigo());
	            }
	        };

	        if (orden.equals("asc")) {
	            Collections.sort(productos, codigoComparator);
	        } else {
	            Collections.sort(productos, Collections.reverseOrder(codigoComparator));
	        }
	    }
		
		
		request.setAttribute("orden", orden);
		request.setAttribute("productos", productos);
		request.getRequestDispatcher("inicio.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
