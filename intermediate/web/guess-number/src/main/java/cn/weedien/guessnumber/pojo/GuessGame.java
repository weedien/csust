package cn.weedien.guessnumber.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Random;

@Getter
@Setter
@ToString
public class GuessGame {
    private int randomNumber;
    private int numberOfGuesses;

    public GuessGame() {
        generateRandomNumber();
        numberOfGuesses = 0;
    }

    private void generateRandomNumber() {
        Random random = new Random();
        randomNumber = random.nextInt(100) + 1;
    }

    public String checkGuess(int userGuess) {
        numberOfGuesses++;

        if (userGuess < randomNumber) {
            return "Your guess is too low. Try again!";
        } else if (userGuess > randomNumber) {
            return "Your guess is too high. Try again!";
        } else {
            return "Congratulations! You guessed the right number in " + numberOfGuesses + " guesses.";
        }
    }

    public void resetGame() {
        generateRandomNumber();
        numberOfGuesses = 0;
    }
}
