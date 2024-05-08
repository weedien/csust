package cn.weedien.csust.medium.guess;


import cn.weedien.csust.medium.guess.pojo.GuessGame;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/guess")
public class InputNumberServlet extends HttpServlet {

    private static long count = 0;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        GuessGame guessGame = new GuessGame();
        session.setAttribute("guessGame", guessGame);
        count++;
        System.out.println("NO." + count + " " + guessGame);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Guess Number Game</title></head><body>");
        out.println("<h1>Welcome to Guess Number Game!</h1>");
        out.println("<p>Session ID: " + session.getId() + "</p>");
        out.println("<form action='/guess_number/process' method='post'>");
        out.println("Enter your guess (1-100): <input type='text' name='userGuess'>");
        out.println("<input type='submit' value='Submit Guess'>");
        out.println("</form>");
        out.println("</body></html>");
    }
}
