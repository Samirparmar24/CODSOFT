import java.util.Random;
import java.util.Scanner;

public class GuessTheNumber {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int totalScore = 0;
        int roundsPlayed = 0;

        System.out.println("=== Guess The Number Game ===");

        boolean playAgain = true;

        while (playAgain) {
            int numberToGuess = random.nextInt(100) + 1; // 1 to 100
            int maxAttempts = 10;
            int attemptsLeft = maxAttempts;
            boolean guessedCorrectly = false;

            roundsPlayed++;
            System.out.println("\nRound " + roundsPlayed + ": Guess a number between 1 and 100.");
            System.out.println("You have " + maxAttempts + " attempts.\n");

            while (attemptsLeft > 0) {
                System.out.print("Enter your guess: ");
                int guess;

                try {
                    guess = Integer.parseInt(scanner.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }

                attemptsLeft--;

                if (guess == numberToGuess) {
                    System.out.println("Correct! You guessed the number in " + (maxAttempts - attemptsLeft) + " attempts.");
                    int roundScore = Math.max(0, (maxAttempts - (maxAttempts - attemptsLeft)) * 10 + 10);
                    totalScore += roundScore;
                    System.out.println("Score earned this round: " + roundScore);
                    guessedCorrectly = true;
                    break;
                } else if (guess > numberToGuess) {
                    System.out.println("Too high! Attempts left: " + attemptsLeft);
                } else {
                    System.out.println("Too low! Attempts left: " + attemptsLeft);
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Out of attempts! The number was: " + numberToGuess);
            }

            System.out.print("\nDo you want to play again? (y/n): ");
            String response = scanner.nextLine().trim().toLowerCase();
            playAgain = response.equals("y");
        }

        System.out.println("\n=== Game Over ===");
        System.out.println("Rounds played: " + roundsPlayed);
        System.out.println("Total score: " + totalScore);
        System.out.println("Thanks for playing!");

        scanner.close();
    }
}