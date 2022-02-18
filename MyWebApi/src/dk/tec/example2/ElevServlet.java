package dk.tec.example2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import dk.tec.AnalyzeRequest;
import dk.tec.Elev;

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
		
		Elev e1 = new Elev(1099, "Tobias", "Gadefejer");
		e1.addSkill("Graesslaaer");
		e1.addSkill("Hvile paa en skovl");
		
		Elev e2 = new Elev(1098, "Peter", "Alt for damerne");
		e2.addSkill("Spille alt-sax");
		e2.addSkill("Tenor");
		
		Elev e3 = new Elev(1097, "Rasmus", "Programmer");
		e3.addSkill("KodeKarl");
		e3.addSkill("Database-Dennis");
		
		ArrayList<Elev> elever = new ArrayList<Elev>();
		elever.add(e1);
		elever.add(e2);
		elever.add(e3);
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonStr = mapper.writeValueAsString(elever);
		
		
		out.println(jsonStr);
		
		
		
		}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		BufferedReader reader = request.getReader();
		String received = reader.readLine();
		
		System.out.println(received);
		
		ObjectMapper mapper = new ObjectMapper();
		Elev e = mapper.readValue(received, Elev.class);
		
		System.out.println(e.getName());
		
	}


}
