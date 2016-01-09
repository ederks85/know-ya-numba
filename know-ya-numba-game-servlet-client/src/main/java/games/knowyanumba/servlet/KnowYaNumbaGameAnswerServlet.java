package games.knowyanumba.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;

import games.knowyanumba.exception.WrongNumberAnsweredException;
import games.knowyanumba.manager.GameManager;

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
		if (StringUtils.isBlank(answer)) {
			answerDO = new KnowYaNumberAnswerDO(KnowYaNumberAnswer.INVALID, answer);
		} else {
			try {
				final int nextCurrent = knowYaNumbaGameManager.processAnswer(Integer.parseInt(answer));
				request.getSession().setAttribute("previous", request.getSession().getAttribute("current"));
				request.getSession().setAttribute("current", answer);
				answerDO = new KnowYaNumberAnswerDO(KnowYaNumberAnswer.CORRECT, String.valueOf(nextCurrent));
			} catch (NumberFormatException e) {
				answerDO = new KnowYaNumberAnswerDO(KnowYaNumberAnswer.INVALID, answer);
			} catch (WrongNumberAnsweredException e) {
				final String currentPrevious = String.valueOf(request.getSession().getAttribute("previous"));
				answerDO = new KnowYaNumberAnswerDO(KnowYaNumberAnswer.WRONG, currentPrevious);
			}
		}

		// Prepare and send the response
		Gson gson = new Gson();
		response.setContentType("application/json");
		response.getWriter().write(gson.toJson(answerDO));
	}
}