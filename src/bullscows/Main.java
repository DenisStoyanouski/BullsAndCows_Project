package bullscows;

import java.util.*;

public class Main {
    private static String code;
    private static int lengthOfCode;

    private static int numberOfSymbols;
    private static String range;

    public static void main(String[] args) {

        codeGenerator();
        compareAnswerCode();
    }

    static void codeGenerator () {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        getLengthOfCode();
        getNumberOfSymbols();
        range = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder secretCode = new StringBuilder();

        do {
            String symbol = String.valueOf(range.charAt(random.nextInt(numberOfSymbols)));
            if (secretCode.indexOf(symbol) == -1) {
                secretCode.append(symbol);
            }
        } while (secretCode.length() != lengthOfCode);
        code = String.valueOf(secretCode);
        answerBuilder();
    }

    public static void answerBuilder () {

        String placer1 = code.replaceAll(".", "*");

        if (numberOfSymbols < 11) {
            System.out.printf("The secret is prepared: %s (0-%s).%n", placer1, range.charAt(numberOfSymbols - 1));
        } else if (numberOfSymbols == 11) {
            System.out.printf("The secret is prepared: %s (0-9, a).%n", placer1);
        } else {
            System.out.printf("The secret is prepared: %s (0-9, a-%s).%n", placer1, range.charAt(numberOfSymbols - 1));
        }
    }

    public static void compareAnswerCode() {
        System.out.println("Okay, let's start a game!");
        int turnNumber = 1;
        Scanner scanner = new Scanner(System.in);
        String answer;
        do {
            int bull = 0;
            int cow = 0;
            System.out.printf("Turn %d:%n", turnNumber);
            answer = scanner.nextLine();
            turnNumber++;
            for (int i = 0; i < answer.length(); i++) {
                for (int j = 0; j < code.length(); j++) {
                    if (answer.charAt(i) == code.charAt(j) && i == j) {
                        bull++;
                    } else if (answer.charAt(i) == code.charAt(j)) {
                        cow++;
                    }
                    if (bull <= cow) {
                        cow = cow - bull;
                    }
                }
            }
            if (bull == 0 && cow == 0) {
                System.out.println("Grade: None.");
            } else if (bull == 0) {
                System.out.printf("Grade: %d cow(s).%n", cow);
            } else if (cow == 0) {
                System.out.printf("Grade: %d bull(s).%n", bull);
            } else {
                System.out.printf("Grade: %d bull(s) and %d cow(s).%n", bull, cow);
            }
        }while (!answer.equals(code));
        System.out.println("Congratulations! You guessed the secret code.");
    }

    public static void getLengthOfCode() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please, enter the secret code's length:");
        try {
            lengthOfCode = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: This isn't a valid number.");
            System.exit(0);
        }

        if (lengthOfCode > 36) {
            System.out.println("Error: can't generate a secret number with a length of 37");
            System.exit(0); // finish program;
        }
        if (lengthOfCode < 1) {
            System.out.println("Error: can't generate a secret number with a length of 0");
            System.exit(0); // finish program;
        }
    }

    public static void getNumberOfSymbols() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the number of possible symbols in the code:");

        try {
            numberOfSymbols = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Error: This isn't a valid number.");
            System.exit(0);
        }

        if (numberOfSymbols < lengthOfCode) {
            System.out.printf("Error: it's not possible to generate a code with a length of %d with %d unique symbols.%n", lengthOfCode, numberOfSymbols);
            System.exit(0); // finish program;
        } else if (numberOfSymbols > 36) {
            System.out.println("Error: can't generate a secret number with a length of 37");
            System.exit(0); // finish program;
        } else if (numberOfSymbols < 1) {
            System.out.println("Error: can't generate a secret number with a length of 0");
            System.exit(0); // finish program;
        }
    }


}
