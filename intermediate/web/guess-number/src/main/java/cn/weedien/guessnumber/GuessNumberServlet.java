package cn.weedien.guessnumber;

import cn.weedien.guessnumber.pojo.GuessGame;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/process")
public class GuessNumberServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        GuessGame guessGame = (GuessGame) session.getAttribute("guessGame");

        if (guessGame == null) {
            response.sendRedirect("/guess_number/guess");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String userGuessStr = request.getParameter("userGuess");

        if (userGuessStr == null || userGuessStr.isEmpty() || !userGuessStr.matches("\\d+")) {
            response.sendRedirect("/guess_number/guess");
            return;
        }

        int userGuess = Integer.parseInt(userGuessStr);
        String result = guessGame.checkGuess(userGuess);

        if (result.contains("Congratulations")) {
            response.sendRedirect("/guess_number/right");
        } else {
            out.println("<html><head><title>Guess Number Game</title></head><body>");
            out.println("<p>" + result + "</p>");
            out.println("<form action='/guess_number/process' method='post'>");
            out.println("Enter your guess (1-100): <input type='text' name='userGuess'>");
            out.println("<input type='submit' value='Submit Guess'>");
            out.println("</form>");
            out.println("</body></html>");
        }
    }
}
