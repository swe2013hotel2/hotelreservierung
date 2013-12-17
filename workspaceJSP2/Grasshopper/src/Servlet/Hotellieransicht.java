package Servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import swe2013.dao.LocationDAO;
import swe2013.dao.SqlLocationDAO;
import swe2013.dao.SqlUserDAO;
import swe2013.dao.UserDAO;
import swe2013.location.Hotel;
import swe2013.location.Review;
import swe2013.user.Hotellier;

/**
 * Servlet implementation class Hotellieransicht
 */
@WebServlet("/Hotellieransicht")
public class Hotellieransicht extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Hotellieransicht() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("UserID")==null){
			response.sendRedirect("nothotellier.jsp");
			return;
		}
		
		int userclass=0;
		if(session.getAttribute("UserClass")==null){
			response.sendRedirect("nothotellier.jsp");
			return;
		}
		else
		{
			userclass = (Integer) session.getAttribute("UserClass");
		}
		
		if(userclass!=2){
			response.sendRedirect("nothotellier.jsp");
			return;
		}
		else
		{
		System.out.println("userclass" + userclass);
		
		long hotellierID = (Long) session.getAttribute("UserID");
			
		LocationDAO ldao = new SqlLocationDAO();
		UserDAO udao = new SqlUserDAO();
		
		Hotel hotel = ldao.getHotelbyOwner(hotellierID);
		Hotellier hotellier = (Hotellier) udao.getUserbyID(hotellierID);
		long hotelID = hotel.getHotelID();
		String hotelName = hotel.getName();
		int[] roomList = hotel.getNumberOfRooms();
		int[] priceList= hotel.getPricesOfRooms();
		int oneBedRoom=roomList[0];
		int twoBedRoom=roomList[0];
		int priceOneBedRoom=priceList[0];
		int priceTwoBedRoom=priceList[1];
		String email = hotellier.getEmail();
		String street = hotellier.getStreet();
		int zipCode = hotellier.getZipCode();
		String city = hotellier.getCity();
		String country = hotellier.getCountry();		
		String telephone = hotellier.getTelephoneNumber();
		
		//ArrayList<Review> review = Review.getReviewsForHotel(hotelID);
		/*System.out.println(review);
		String reviewtext = review.getReviewText();
		System.out.println(reviewtext);*/
			
		request.setAttribute("hotelID", hotelID);
		request.setAttribute("hotelName", hotelName);
		request.setAttribute("oneBedRoom", oneBedRoom);
		request.setAttribute("twoBedRoom", twoBedRoom);
		request.setAttribute("priceOneBedRoom", priceOneBedRoom);
		request.setAttribute("priceTwoBedRoom", priceTwoBedRoom);
		request.setAttribute("email", email);
		request.setAttribute("telephone", telephone);
		request.setAttribute("street", street);
		request.setAttribute("zipCode", zipCode);
		request.setAttribute("city", city);
		request.setAttribute("country", country);

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Hotellieransichtdetail.jsp");
		dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		if(session.getAttribute("UserID")==null){
			response.sendRedirect("nothotellier.jsp");
			return;
		}
		
		int userclass=0;
		if(session.getAttribute("UserClass")==null){
			response.sendRedirect("nothotellier.jsp");
			return;
		}
		else
		{
			userclass = (Integer) session.getAttribute("UserClass");
		}
		
		if(userclass!=2){
			response.sendRedirect("nothotellier.jsp");
			return;
		}
		else
		{
		System.out.println("userclass" + userclass);
		
		long hotellierID = (Long) session.getAttribute("UserID");
			
		LocationDAO ldao = new SqlLocationDAO();
		UserDAO udao = new SqlUserDAO();
		
		Hotel hotel = ldao.getHotelbyOwner(hotellierID);
		Hotellier hotellier = (Hotellier) udao.getUserbyID(hotellierID);
		long hotelID = hotel.getHotelID();
		String hotelName = hotel.getName();
		System.out.println("hotelName" + hotelName);
		int[] roomList = hotel.getNumberOfRooms();
		System.out.println("roomList" + roomList);
		int[] priceList= hotel.getPricesOfRooms();
		System.out.println("priceList" + priceList);
		int oneBedRoom=roomList[0];
		System.out.println("oneBedRoom" + oneBedRoom);
		int twoBedRoom=roomList[0];
		System.out.println("twoBedRoom" + twoBedRoom);
		int priceOneBedRoom=priceList[0];
		System.out.println("priceOneBedRoom" + priceOneBedRoom);
		int priceTwoBedRoom=priceList[1];
		System.out.println("priceTwoBedRoom" + priceTwoBedRoom);
		String email = hotellier.getEmail();
		System.out.println("email" + email);
		String street = hotellier.getStreet();
		int zipCode = hotellier.getZipCode();
		String city = hotellier.getCity();
		String country = hotellier.getCountry();		
		String telephone = hotellier.getTelephoneNumber();
		
		//ArrayList<Review> review = Review.getReviewsForHotel(hotelID);
		/*System.out.println(review);
		String reviewtext = review.getReviewText();
		System.out.println(reviewtext);*/
			
		request.setAttribute("hotelID", hotelID);
		request.setAttribute("hotelName", hotelName);
		request.setAttribute("oneBedRoom", oneBedRoom);
		request.setAttribute("twoBedRoom", twoBedRoom);
		request.setAttribute("priceOneBedRoom", priceOneBedRoom);
		request.setAttribute("priceTwoBedRoom", priceTwoBedRoom);
		request.setAttribute("email", email);
		request.setAttribute("telephone", telephone);
		request.setAttribute("street", street);
		request.setAttribute("zipCode", zipCode);
		request.setAttribute("city", city);
		request.setAttribute("country", country);

		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Hotellieransichtdetail.jsp");
		dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
