package dk.tec.example3_WebApi;

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
import dk.tec.DBTools;
import dk.tec.Person;


public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.setContentType("application/json");
		
		PrintWriter out = response.getWriter();
		
		DBTools db = new DBTools();
		
		ObjectMapper mapper = new ObjectMapper();
		
		AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());
		
		switch (analyze.getLevel()) {
		case MatchPersonId:
			Person p = db.getPersonById(analyze.getId());
			String jsonStr = mapper.writeValueAsString(p);
			out.println(jsonStr);
			break;
		case MatchPerson:
			ArrayList <Person> persons = db.getAllPerson();
			String jsonStr2 = mapper.writeValueAsString(persons);
			out.println(jsonStr2);
			break;
		
		
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		BufferedReader in = request.getReader();
		String jsonStr = in.readLine();
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		
		
		AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());
		
		switch (analyze.getLevel()) {
		case MatchPerson:
			Person p = mapper.readValue(jsonStr, Person.class);
			DBTools db = new DBTools();
			db.addPerson(p);
			
			break;
			
		default: out.println("bad url");
			break;
		
		}
		
		
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		BufferedReader in = request.getReader();
		String jsonStr = in.readLine();
		
		ObjectMapper mapper = new ObjectMapper();
		
		
		
		
		AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());
		
		switch (analyze.getLevel()) {
		case MatchPerson:
			Person p = mapper.readValue(jsonStr, Person.class);
			DBTools db = new DBTools();
			db.updatePerson(p);
			
			break;
			
		default: out.println("bad url");
			break;
		
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		AnalyzeRequest analyze = new AnalyzeRequest(request.getPathInfo());
		
		switch (analyze.getLevel()) {
		case MatchPersonId:
			
			DBTools db = new DBTools();
			db.deletePerson(analyze.getId());
			
			break;
			
		default: out.println("bad url");
			break;
		
		}
	}

}
