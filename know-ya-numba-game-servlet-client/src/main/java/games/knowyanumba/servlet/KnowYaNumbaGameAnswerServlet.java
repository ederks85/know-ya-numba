package games.knowyanumba.servlet;

import com.google.gson.Gson;
import games.knowyanumba.exception.WrongNumberAnsweredException;
import games.knowyanumba.manager.game.GameManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet for handling answers of a Know-ya-numba Game
 * 
 * @author Edwin
 */
@WebServlet(urlPatterns = {"/answer"})
public class KnowYaNumbaGameAnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve the current client's Game from the session
		final GameManager knowYaNumbaGameManager = (GameManager)request.getSession().getAttribute("knowYaNumbaGameManager");
		if (knowYaNumbaGameManager == null) {
			throw new IllegalStateException("Could not find game session");
		}

		// Process the client's answer
		KnowYaNumberAnswerDO answerDO;

		String answer = request.getParameter("answer");
		if (answer == null || answer.strip().equals("")) {
			answerDO = new KnowYaNumberAnswerDO(KnowYaNumberAnswer.INVALID, answer, knowYaNumbaGameManager.getScoreManager().getCurrentScore());
		} else {
			try {
				final int nextCurrent = knowYaNumbaGameManager.processAnswer(Integer.parseInt(answer));
				request.getSession().setAttribute("previousValue", request.getSession().getAttribute("currentValue"));
				request.getSession().setAttribute("currentValue", answer);
				answerDO = new KnowYaNumberAnswerDO(KnowYaNumberAnswer.CORRECT, String.valueOf(nextCurrent),knowYaNumbaGameManager.getScoreManager().getCurrentScore());
			} catch (NumberFormatException e) {
				answerDO = new KnowYaNumberAnswerDO(KnowYaNumberAnswer.INVALID, answer, knowYaNumbaGameManager.getScoreManager().getCurrentScore());
			} catch (WrongNumberAnsweredException e) {
				final String currentPrevious = String.valueOf(request.getSession().getAttribute("previousValue"));
				answerDO = new KnowYaNumberAnswerDO(KnowYaNumberAnswer.WRONG, currentPrevious,knowYaNumbaGameManager.getScoreManager().getCurrentScore());
			}
		}

		// Prepare and send the response
		Gson gson = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(gson.toJson(answerDO));
	}
}