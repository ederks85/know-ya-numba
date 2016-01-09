package games.knowyanumba.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import games.knowyanumba.Game;

/**
 * Servlet for initializing a Know-ya-numba Game
 * 
 * @author Edwin
 */
@WebServlet(urlPatterns = {""})
public class KnowYaNumbaGameLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Initiate a new Game for a client and put it in the session
		final Game knowYaNumbaGame = new Game();

		final int initialPrevious = knowYaNumbaGame.init();
		final int initialCurrent = knowYaNumbaGame.start();

		request.getSession().setAttribute("previous", initialPrevious);
		request.getSession().setAttribute("current", initialCurrent);

		request.getSession().setAttribute("knowYaNumbaGame", knowYaNumbaGame);

		// Navigate to view
		request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
	}
}