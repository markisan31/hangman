package sample;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.*;
import java.util.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.*;


public class Controller {

    Random rnd = new Random();
    int wordNumberFromList = rnd.nextInt(30); //maximum 5459
    int wrongTries = 0;
    private List<String> words = new ArrayList<String>();
    private List<String> definitions = new ArrayList<String>();
    private List<TextField> letterFields = new ArrayList<TextField>();
    private List<Shape> figure = new ArrayList<Shape>();
    private List<String> usedLetters = new ArrayList<String>();
    private AlertBox answerBox = new AlertBox();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField first_letter;

    @FXML
    private TextField second_letter;

    @FXML
    private TextField fourth_letter;

    @FXML
    private TextField seventh_letter;

    @FXML
    private TextField fifth_letter;

    @FXML
    private TextField ninth_letter;

    @FXML
    private TextField eighth_letter;

    @FXML
    private TextField third_letter;

    @FXML
    private TextField sixth_letter;

    @FXML
    private TextField ninth_letter1;

    @FXML
    private TextField eleven_letter;

    @FXML
    private TextField twelve_letter;

    @FXML
    private TextField thirt_letter;

    @FXML
    private TextField fourt_letter;

    @FXML
    private TextField sixt_letter;

    @FXML
    private TextField fivet_letter;

    @FXML
    private TextField sixt_letter1;

    @FXML
    private TextField sixt_letter2;

    @FXML
    private TextField sixt_letter3;

    @FXML
    private TextField sixt_letter4;

    @FXML
    private TextField sixt_letter5;

    @FXML
    private TextField sixt_letter6;

    @FXML
    private Button newGameButton;

    @FXML
    private Button resetButton;

    @FXML
    private Button checkLetterButton;

    @FXML
    private TextField checkLetterField;

    @FXML
    private Line standVertical;

    @FXML
    private Line standHorizontal;

    @FXML
    private Line rope;

    @FXML
    private Circle head;

    @FXML
    private Line body;

    @FXML
    private Line rightHand;

    @FXML
    private Line leftHand;

    @FXML
    private Line leftLeg;

    @FXML
    private Line rightLeg;

    @FXML
    private Label usedLettersLabel;

    public EventHandler<KeyEvent> maxLength(final Integer i) {
        return new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent arg0) {

                TextField tx = (TextField) arg0.getSource();
                if (tx.getText().length() >= i) {
                    arg0.consume();
                }

            }

        };

    }

    @FXML
    void initialize() {

        appStart();

        newGameButton.setVisible(false);

        resetButton.setOnAction(actionEvent -> {
            resetGame();

        });

        checkLetterButton.setOnAction(event -> {
            checkLetter(words.get(wordNumberFromList).toUpperCase(), checkLetterField.getText().toUpperCase());
            checkWin();
        });

        checkLetterField.setOnMouseClicked(mouseEvent -> {
            checkLetterField.setText("");
        });

        checkLetterField.addEventFilter(KeyEvent.KEY_TYPED, maxLength(1));

    }

    void checkLetter(String word, String letter) {


        try {
            for (int i = 0; i < word.length(); i++) {

                if (word.charAt(i) == letter.charAt(0)) {
                    letterFields.get(i).setText(String.valueOf(letter.charAt(0)));
                } else if (word.equals("")) {
                    System.out.println("Please write smthng");
                }


            }
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("wrong");
        }
//        if (!usedLetters.contains(letter)) {
//            usedLetters.add(letter.toUpperCase());
//        }
        processMistakes();
        showUsedLetters();
        checkLetterField.setText("");

    }

    void setRightNumberOfLetterFields(String word) {
        for (int i = 0; i < word.length(); i++) {
            letterFields.get(i).setVisible(true);
        }
        for (int i = word.length(); i < letterFields.size(); i++) {
            letterFields.get(i).setVisible(false);
        }
    }

    void appStart() {

        letterFields.add(first_letter);
        letterFields.add(second_letter);
        letterFields.add(third_letter);
        letterFields.add(fourth_letter);
        letterFields.add(fifth_letter);
        letterFields.add(sixth_letter);
        letterFields.add(seventh_letter);
        letterFields.add(eighth_letter);
        letterFields.add(ninth_letter);
        letterFields.add(ninth_letter1);
        letterFields.add(eleven_letter);
        letterFields.add(twelve_letter);
        letterFields.add(thirt_letter);
        letterFields.add(fourt_letter);
        letterFields.add(fivet_letter);
        letterFields.add(sixt_letter);
        letterFields.add(sixt_letter1);
        letterFields.add(sixt_letter2);
        letterFields.add(sixt_letter3);
        letterFields.add(sixt_letter4);
        letterFields.add(sixt_letter5);
        letterFields.add(sixt_letter6);


        figure.add(standVertical);
        figure.add(standHorizontal);
        figure.add(head);
        figure.add(body);
        figure.add(rightHand);
        figure.add(leftHand);
        figure.add(rightLeg);
        figure.add(leftLeg);
        figure.add(rope);

        for (Shape shape : figure) {
            shape.setVisible(false);
        }


        try {
            addWordsToList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        setRightNumberOfLetterFields(words.get(wordNumberFromList));

    }

//    void addWordsToList() throws FileNotFoundException {
//        Scanner sc = new Scanner(new FileReader("src/sample/Assets/10000medium.txt"));
//
//        while (sc.hasNextLine()) {
//            words.add(sc.nextLine().toUpperCase());
//        }
//
//        sc.close();
//    }

    void addWordsToList() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("src/sample/Assets/words.txt"));
        Scanner sc2 = new Scanner(new FileReader("src/sample/Assets/definition.txt"));

        while (sc.hasNextLine()) {
            words.add(sc.nextLine().toUpperCase());
        }
        while (sc2.hasNextLine()) {
            definitions.add(sc2.nextLine());
        }

        sc2.close();
        sc.close();
    }

    void processMistakes() {
        final String text = checkLetterField.getText().toUpperCase();

        if (!usedLetters.contains(text)){
            usedLetters.add(text);
            if (!words.get(wordNumberFromList).contains(text)){
                System.out.println(words.get(wordNumberFromList));
                wrongTries++;
                System.out.println(wrongTries);
                figure.get(wrongTries - 1).setVisible(true);
            }

        }
        showInfo();
    }

    void resetGame() {
        wordNumberFromList = rnd.nextInt(30);
        setRightNumberOfLetterFields(words.get(wordNumberFromList));
        for (TextField field : letterFields) {
            field.clear();
        }
        for (Shape shape : figure) {
            shape.setVisible(false);
        }
        usedLetters.clear();
        usedLettersLabel.setText("");
        checkLetterField.setText("");
        wrongTries = 0;

    }

    void checkWin(){
        int needToWin = words.get(wordNumberFromList).length();
        int currentCount = 0;

        for (TextField a : letterFields){
            if (!a.getText().isEmpty()) currentCount++;
        }
        if (currentCount == needToWin){
//            resetGame();

            answerBox.display(true, words.get(wordNumberFromList), definitions.get(wordNumberFromList));
            System.out.println("You win");
        }

//        System.out.println(currentCount);
    }

    void showInfo() {
        if (wrongTries == 9) {
            answerBox.display(false, words.get(wordNumberFromList), definitions.get(wordNumberFromList));
            System.out.println("You lose");
            resetGame();
            wrongTries = 0;
            for (Shape shape : figure) {
                shape.setVisible(false);
            }
        }
    }

    void showUsedLetters() {
        String letters = "";
        for (String letter : usedLetters) {
            letters += String.format("%s ", letter);
            usedLettersLabel.setText(letters);
        }
    }
}

