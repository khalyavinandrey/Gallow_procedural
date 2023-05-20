import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        FileReader fileReader = new FileReader("path.txt");

        createArrayOfWordsFromFile(sb, fileReader);

        String randomWord = getRandomWord(sb);
        StringBuilder randomWordForUser = getRandomWordForUser(randomWord);
        giveHint(randomWord);

        startGame(randomWordForUser, randomWord);
    }
    public static void createArrayOfWordsFromFile(StringBuilder sb, FileReader fileReader) throws IOException {
        int character;
        while ((character=fileReader.read()) != -1) {
            sb.append((char) character);
        }
    }

    public static char getCharacterFromUser() {
        Scanner scanner = new Scanner(System.in);
        String strFromScanner = scanner.next();
        if ((strFromScanner.length() != 1) || (!isCharacterAvailable(strFromScanner))) {
            System.out.println("In the input should be non-capital letters of English");
            System.out.print("Try again: ");
            return getCharacterFromUser();
        }
        return strFromScanner.charAt(0);
    }

    public static boolean isCharacterAvailable(String a) {
        ArrayList<String> listOfAvailableCharacters = new ArrayList<>();
        for (AvailableCharacters characters : AvailableCharacters.values()) {
            listOfAvailableCharacters.add(characters.name());
        }
        return listOfAvailableCharacters.contains(a);
    }

    public static String getRandomWord(StringBuilder sb) {
        String[] arrOfWordsFromFile = sb.toString().split(", ");
        int randomNumber = (int)(Math.random() * (arrOfWordsFromFile.length));

        String randomWord = arrOfWordsFromFile[randomNumber];
        return randomWord;
    }

    public static StringBuilder getRandomWordForUser(String str) throws IOException {
        return new StringBuilder(str.replaceAll("[a-zA-z]", "*"));
    }

    public static void giveHint(String string) {
        switch(string) {
            case "father" -> System.out.println("Hint: He loves plants very much");
            case "mother" -> System.out.println("Hint: She works a lot");
            case "brother" -> System.out.println("Hint: He studies a lot");
            case "grandfather" -> System.out.println("Hint: He goes on a mountain every day at morning");
            case "grandmother" -> System.out.println("Hint: She cooks everyday lots of food");
            case "sister" -> System.out.println("Hint: She works as barber");
        }
    }

    public static void startGame(StringBuilder randomWordForUser, String randomWord) {
        StringBuilder usedCharacters = new StringBuilder();
        int availableMistakes = 5;
        int result = 0;

        while ((result != randomWord.length()) && (availableMistakes > 0)) {
            System.out.println();
            System.out.println(randomWordForUser);
            System.out.println();
            System.out.print("Input Character: ");
            char inputCharacter = getCharacterFromUser();
            usedCharacters.append(" " + inputCharacter);
            int count = 0;
            for (int i = 0; i < randomWord.length(); i++) {
                if (randomWord.charAt(i) == inputCharacter) {
                    randomWordForUser.setCharAt(i, inputCharacter);
                    count++;
                    result++;
                }
            }
            if (count == 0) {
                availableMistakes--;
                System.out.println();
                System.out.println("Oops there's no such character");
                System.out.println("You have " + availableMistakes + " mistakes left");
                showGallow(availableMistakes);
                System.out.println("Characters that you used: " + usedCharacters);
            }
            if (availableMistakes == 0) {
                System.out.println();
                System.out.println("YOU LOST");
                System.out.println();
                System.out.println("[T]ry again or [E]xit");
                Scanner scanner1 = new Scanner(System.in);
                if (scanner1.next().charAt(0) == 'T') {
                    availableMistakes = 5;
                    result = 0;
                    giveHint(randomWord);
                } else if (scanner1.next().charAt(0) == 'E') break;
                else System.out.println("Incorrect input");
            }
            if (result == randomWord.length()) {
                System.out.println();
                System.out.println("The word is :" + randomWord);
                System.out.println("YOU WON");
            }
        }
    }

    public static void showGallow(int numberOfMistakes) {
        switch(numberOfMistakes) {
            case 4 -> {
                System.out.println(" ___");
                System.out.println("|/  |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println();
            }
            case 3 -> {
                System.out.println(" ___");
                System.out.println("|/  |");
                System.out.println("|   *");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println();
            }
            case 2 -> {
                System.out.println(" ___");
                System.out.println("|/  |");
                System.out.println("|   *");
                System.out.println("|  /||");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println();
            }
            case 1 -> {
                System.out.println(" ___");
                System.out.println("|/  |");
                System.out.println("|   *");
                System.out.println("|  /||");
                System.out.println("|   |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println();
            }
        }
    }
}
