package dk.tec.example1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dk.tec.AnalyzeRequest;

/**
 * Servlet implementation class ElevServlet
 */
//@WebServlet("/ElevServlet")
public class ElevServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		out.println("Context Path: " + request.getContextPath()); // Projektnavn
		out.println("<br />Servlet Path: " + request.getServletPath()); // Servlet
		out.println("<br />Path Info: " + request.getPathInfo());
		out.println("<br /> ");
		
		AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());
				
		switch (analyze.getLevel()) {
		case MatchPersonId:
			
			out.println("Match på Elev med id " + analyze.getId());
			break;
		case MatchPerson:
			out.println("Match på Elev");
			break;
		case NoMatch:
			out.println("Intet match");
			

		}
		
		}


}
