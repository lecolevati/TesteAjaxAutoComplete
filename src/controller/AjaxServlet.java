package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.DummyDB;

import com.google.gson.Gson;

@WebServlet("/ajax")
public class AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjaxServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");
		
        String json = null;

		String query = request.getParameter("term");
        query = query.toLowerCase();
        DummyDB dDb = new DummyDB();
        List<String> listaRetorno = new ArrayList<String>();
        for (String s : dDb.listaPaises()){
            String country = s.toLowerCase();
            if(country.startsWith(query)) {
                listaRetorno.add(s);
            }
        }
        
        json = new Gson().toJson(listaRetorno);
        response.setContentType("application/json");
        response.getWriter().write(json);
        
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}
