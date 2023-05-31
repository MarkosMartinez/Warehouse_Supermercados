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
import javax.servlet.http.HttpSession;

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
		    
		//Para ordenar el codigo
		String orden = request.getParameter("codOrden");
		
		if (orden != null && (orden.equalsIgnoreCase("asc") || orden.equalsIgnoreCase("desc"))) {
	        Comparator<Producto> codigoComparator = new Comparator<Producto>() {
	            public int compare(Producto p1, Producto p2) {
	                return p1.getCodigo().compareTo(p2.getCodigo());
	            }
	        };

	        if (orden.equals("asc")) {
	            Collections.sort(productos, codigoComparator);
	        } else if (orden.equals("desc")){
	            Collections.sort(productos, Collections.reverseOrder(codigoComparator));
	        }
	    }
		
		//Para eliminar multiple (campo de texto)
		if(request.getParameter("multidelete") != null && request.getParameter("multidelete") != "") {
			boolean valido = true;
			String[] codigoAEliminar = request.getParameter("multidelete").replaceAll("\\s+","").split(",");
			for (String codigo : codigoAEliminar) {
				if(valido) {
					valido = mproducto.comprobarCodigo(codigo);
				}
			}
			if(valido) {
				for (String codigo : codigoAEliminar) {
					mproducto.eliminarProductoPorCodigo(codigo);
				}
			}
		}
		
		
		//Para eliminado multiple desde checkbox
		for (int i = 1; i < productos.size(); i++) {
			if(request.getParameter("mdc-" + i) != null) {
				mproducto.eliminarProducto(Integer.parseInt(request.getParameter("mdc-" + i)));
			}
			
		}
		
		
		
		//Obtener el size del carrito
		HttpSession session = request.getSession();
		ArrayList<Producto> carrito = (ArrayList<Producto>) session.getAttribute("carrito");
		int cantCarrito = 0;
		if(carrito != null) {
			cantCarrito = carrito.size();
		}
		
		productos = mproducto.cargarProductos();
		
		//Para aplicar el filtro de busqueda
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
				
				//Para aplicar el filtro de precio maximo / minimo
			    Iterator<Producto> iterator = productos.iterator();
			    while (iterator.hasNext()) {
			        Producto producto = iterator.next();
			        if (producto.getPrecio() < min || producto.getPrecio() > max) {
			            iterator.remove();
			        }
			    }
		
		request.setAttribute("carrito", cantCarrito);
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
