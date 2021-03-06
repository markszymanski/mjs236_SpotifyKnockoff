package edu.pitt.spotify.rest;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.pitt.spotify.utils.DbUtilities;

/**
 * Servlet implementation class get_artists
 */
@WebServlet("/api/get_artists")
public class get_artists extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public get_artists() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		// First Name
		// Last Name
		// Band Name
		// Song
		// Album
		String searchTerm = request.getParameter("searchTerm");
		String sql = "";
		
		if (searchTerm == null || searchTerm == "") {
			sql = "SELECT * FROM artist;";
		}
		else {
			sql = "SELECT * FROM artist WHERE first_name LIKE '%" + searchTerm + "%'";
			sql += " OR last_name LIKE '%" + searchTerm + "%'";
			sql += " OR band_name LIKE '%" + searchTerm + "%';";
		}
		
		//System.out.println(sql);
		/*if(request.getParameter("title") != null){
			title = request.getParameter("title");
			if(!title.equals("")){
				sql = "SELECT * FROM song WHERE title LIKE '" + title + "%';";
			}
		}
		else if(request.getParameter("artist") != null){
			artist = request.getParameter("artist");
			if(! artist.equals("")){
				sql = "SELECT * FROM song JOIN song_artist ON song_id = fk_song_id JOIN artist ON fk_artist_id = artist_id ";
				sql += "WHERE artist.band_name LIKE '" + artist + "%' ";
				sql += "OR artist.first_name LIKE '" + artist + "%' ";
				sql += "OR artist.last_name LIKE '" + artist + "%'; ";
			}
		}
		else if(request.getParameter("songYear")!=null){
			songYear = request.getParameter("songYear");
			if(!songYear.equals("")){
				sql = "SELECT * FROM song WHERE YEAR(release_date) = " + songYear + ";";
			}
		}
		else if(request.getParameter("album")!=null){
			album = request.getParameter("album");
			if(!album.equals("")){
				sql = "SELECT * FROM song JOIN album_song ON song_id = fk_song_id ";
				sql += "JOIN album ON fk_album_id = album_id WHERE album.title LIKE '" + album + "%';";
			}
		}*/
		/*
		if(sql.equals("")){
			sql = "SELECT * FROM artist;";
		}*/
		// response.getWriter().write(sql);
		
		JSONArray artistList = new JSONArray();
		
		try {
			DbUtilities db = new DbUtilities();
			ResultSet rs = db.getResultSet(sql);
			while(rs.next()){
				JSONObject artist = new JSONObject();
				artist.put("artist_id", rs.getString("artist_id"));
				artist.put("first_name", rs.getString("first_name"));
				artist.put("last_name", rs.getString("last_name"));
				artist.put("band_name", rs.getString("band_name"));
				artist.put("bio", rs.getString("bio"));
				artistList.put(artist);
			}
			response.getWriter().write(artistList.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
