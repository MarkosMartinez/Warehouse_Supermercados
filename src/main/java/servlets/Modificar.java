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
import modelo.DTO.Producto;
import modelo.DTO.Seccion;

/**
 * Servlet implementation class Modificar
 */
@WebServlet("/Modificar")
public class Modificar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Modificar() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = null;
		id = request.getParameter("id");
		if(id != null) {
			ArrayList<Seccion> secciones = new ArrayList<>();
			ModeloSeccion mseccion = new ModeloSeccion();
			secciones = mseccion.getSecciones();
			ModeloProducto mproducto = new ModeloProducto();
			Producto producto = new Producto();
			producto = mproducto.getProducto(Integer.parseInt(id));
			if(producto.getId() != -1) {
			request.setAttribute("secciones", secciones);
			request.setAttribute("producto", producto);
			request.getRequestDispatcher("modificar.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("inicio.jsp").forward(request, response);
			}
		}else {
			request.getRequestDispatcher("inicio.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Obteniendo los datos del producto modificado
		int id = Integer.parseInt(request.getParameter("id"));
		String codigo = request.getParameter("codigo");
		String nombre = request.getParameter("nombre");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));
		Double precio = Double.parseDouble(request.getParameter("precio"));
		Date fechaCaducidad = null;
		String fechaSinFormato = request.getParameter("caducidad");
		int idSeccion = Integer.parseInt(request.getParameter("seccion"));
		
		//Poniendole un formato a la fecha
		try {
			fechaCaducidad = new SimpleDateFormat("yyyy-MM-dd").parse(fechaSinFormato);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		Seccion seccion = new Seccion();
		seccion.setId(idSeccion);
		
		//Especificando el producto ya modifidado
		Producto productoModificado = new Producto();
		productoModificado.setId(id);
		productoModificado.setCodigo(codigo);
		productoModificado.setNombre(nombre);
		productoModificado.setCantidad(cantidad);
		productoModificado.setPrecio(precio);
		productoModificado.setCaducidad(fechaCaducidad);
		productoModificado.setSeccion(seccion);
		
		
		ModeloProducto mproducto = new ModeloProducto();
		mproducto.modificarProducto(productoModificado);
		response.sendRedirect(request.getContextPath() + "/Modificar?id=" + id);

	}

}
