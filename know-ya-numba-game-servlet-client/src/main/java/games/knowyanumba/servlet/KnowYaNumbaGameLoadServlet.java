package games.knowyanumba.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import games.knowyanumba.manager.game.GameManager;

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
		final GameManager knowYaNumbaGameManager = new GameManager();

		final int initialPrevious = knowYaNumbaGameManager.createNewGame();
		final int initialCurrent = knowYaNumbaGameManager.startGame();

		request.getSession().setAttribute("previousValue", initialPrevious);
		request.getSession().setAttribute("currentValue", initialCurrent);

		request.getSession().setAttribute("knowYaNumbaGameManager", knowYaNumbaGameManager);

		request.setAttribute("currentScore", knowYaNumbaGameManager.getScoreManager().getCurrentScore());
		// Navigate to view
		request.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(request, response);
	}
}