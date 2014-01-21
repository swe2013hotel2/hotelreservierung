package Servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import swe2013.dao.LocationDAO;
import swe2013.dao.SqlLocationDAO;

/**
 * Servlet implementation class Hotelsuche
 */
@WebServlet("/Hotelsuche")
public class Hotelsuche extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hotelsuche() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try{

			String von = request.getParameter("von");
			String bis = request.getParameter("bis");
			String ort = request.getParameter("ort");
			String land = request.getParameter("land");
			String personen = request.getParameter("personen");
			String maxkosten = request.getParameter("maxkosten");
			
			System.out.print("land ="+land);
			if(von==null || bis== null || von.equals("") || bis.equals("")){
				//request.setAttribute("status", "Fehler eingegebene Daten nicht komplett");
				//RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Hotelsuche.jsp");
				//dispatcher.forward(request, response);
				
				response.sendRedirect("Hotelsuche.jsp?message=Fehler%20eingegebene%20Daten%20nicht%20komplett");
				return;
			}
			
			
			//|| ort==null || land== null || personen==null || maxkosten==null ||
			// || ort.equals("") || land.equals("") || personen.equals("") || maxkosten.equals("")
			
			LocationDAO locationDAO = new SqlLocationDAO();
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			
			ort = (ort.equals("")?null:ort);
			land = (land.equals("")?null:land);
			personen = (personen.equals("")?null:personen);
			maxkosten = (maxkosten.equals("")?null:maxkosten);

			Integer beds = (personen==null?null:Integer.parseInt(personen));
			Integer price = (maxkosten==null?null:Integer.parseInt(maxkosten));
			
			Date bd = formatter.parse(von);
			Date ed = formatter.parse(bis);
			
			
			ArrayList<String[]> hotelData = new ArrayList<String[]>();
			
			String[][] summaries = locationDAO.freeHotelsSummary(ort, land, bd, ed, beds, price);

			for(int i=0; i<summaries.length;i++){
				//"hotelid","hotelname","roomid","cityname","countryname","roomcost","roomsize"
				String[] summary = {summaries[i][0],summaries[i][1],summaries[i][3],summaries[i][4],summaries[i][2],summaries[i][6],summaries[i][5],von,bis};
				System.out.println(summary[0]+" , "+summary[1]+" , "+summary[2]);
				
				hotelData.add(summary);
			}

			//request.setParameter("results",hotelData);
			//request.setAttribute("results", hotelData);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/SearchResults.jsp");
			dispatcher.forward(request, response);
			
		}	
		catch (Throwable theException) 	    
		{
			
			System.out.println(theException.getMessage()); 
			response.sendRedirect("Hotelsuche.jsp?message=Fehler%20eingegebene%20Daten%20nicht%20komplett");
		}
	
	}
}
