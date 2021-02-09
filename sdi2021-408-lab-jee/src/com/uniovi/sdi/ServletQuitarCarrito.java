package com.uniovi.sdi;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletQuitarCarrito
 */
@WebServlet("/quitarDeCarrito")
public class ServletQuitarCarrito extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletQuitarCarrito() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		//Sacamos la key del elemento que queremos quitar		

		ConcurrentHashMap<String,Integer> carrito =	(ConcurrentHashMap<String,Integer>) request.getSession().getAttribute("carrito");
		String producto = request.getParameter("producto");

		Iterator<Entry<String,Integer>> iterator = carrito.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Integer> entry = iterator.next();
			if(entry.getKey().equals(producto)) {
				iterator.remove();
			}
		}


		//"Pasamos" el parametro
		request.setAttribute("paresCarrito", carrito);
		getServletContext().getRequestDispatcher("/vista-carrito.jsp").forward(request,	response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}