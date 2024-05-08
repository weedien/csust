package cn.weedien.csust.medium.guess;

import cn.weedien.csust.medium.guess.pojo.GuessGame;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/right")
public class GuessRightServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(true);
        GuessGame guessGame = (GuessGame) session.getAttribute("guessGame");

        if (guessGame == null) {
            response.sendRedirect("/guess_number/guess");
            return;
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Guess Number Game</title></head><body>");
        out.println("<h1>Congratulations!</h1>");
        out.println("<p>You guessed the right number in " + guessGame.getNumberOfGuesses() + " guesses.</p>");
        out.println("<p><a href='/guess_number/guess'>Play Again</a></p>");
        out.println("</body></html>");

        session.removeAttribute("guessGame");
    }
}
