package com.tbt.trainthebrain;

import com.tbt.trainthebrain.sqlcontroller.DBTasks;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class QuestionEditController implements Initializable {

    int limitForQuestionText = 150;
    int limitForAnswerText = 100;

    @FXML
    private TextField id, answerId0, answerId1, answerId2, answerId3;
    @FXML
    private TextArea questionText, answerText0, answerText1, answerText2, answerText3;
    @FXML
    private CheckBox answerCorrect0, answerCorrect1, answerCorrect2, answerCorrect3;
    @FXML
    private Text questionTextCounterText, questionTextLimitText;
    @FXML
    private Text answer0TextLimitText, answer1TextLimitText, answer2TextLimitText, answer3TextLimitText;
    @FXML
    private Text answer0TextCounterText, answer1TextCounterText, answer2TextCounterText, answer3TextCounterText;

    private final ArrayList<TextArea> answerTextsArray = new ArrayList<>();

    private final ArrayList<TextField> answerIdsArray = new ArrayList<>();

    private final ArrayList<CheckBox> isCorrectCheckBoxesArray = new ArrayList<>();

    public void cancelQuestionEditClick(ActionEvent actionEvent) {
        // Back to List of Questions Edit screen
        switchToEditQuestionsClick(actionEvent);
    }

    public void saveQuestionEditClick(ActionEvent actionEvent) {
        handleQuestionInDb();
        // Back to List of Questions Edit screen
        switchToEditQuestionsClick(actionEvent);
    }

    /**
     * Methode prüft ob Frage neu ist, erstellt, aktualisiert oder löscht sie
     * und zugehörige Answers in der Datenbank.
     */
    private void handleQuestionInDb() {
        //Wenn question id = 0 und Text hat Länge = insert Questiontext (wenn erzeugt brauche id von Question für Answers)
        int createdQuestionId;
        if (id.getText().isEmpty()) {
            id.setText("0");
        }
        int questionId = Integer.parseInt(id.getText());
        String question = questionText.getText().trim();
        DBTasks con = new DBTasks();
        if (questionId == 0 && !question.isEmpty()) {
            if (verifyIfMinTwoAnswers()) {
                if (verifyMinOneAnswerIsTrue()) {
                    createdQuestionId = con.insertNewQuestion(question);
                    addAnswerFromCreatedQuestion(createdQuestionId);
                }
            }
        } else if (questionId > 0 && !question.isEmpty()) {
            //Question wird geupdatet und Answers werden geupdatet gelöscht oder neue hinzugefügt
            con.UpdateQuestion(questionId, question);
            editNewAnswer(questionId);
            System.out.println("QuestionId > 0 = Question Update in DB");

        } else if (questionId > 0) {
            //Question und dazugehörige answers werden gelöscht
            con.deleteQuestionAndAnswersInDb(questionId);
            System.out.println("QuestionId > 0 und text ist leer = Question löschen und dazugehörige Answer werden gelöscht in DB");
        }


    }

    public void editNewAnswer(int questionId) {
        //erstellt neue Answers zu den bereits erstellten Questions
        //ändert bestehende Answers
        //löscht bestehende Answers, wenn kein Text vorhanden ist.
        ArrayList<Answer> answers = createNewAnswerObjects(questionId);
        DBTasks con = new DBTasks();
        if (verifyIfMinTwoAnswers()) {
            if (verifyMinOneAnswerIsTrue()) {
                for (Answer answer : answers) {
                    // erstellt, aktualisiert oder löscht Answer in DB
                    if (answer.getId() == 0 && !answer.getText().isEmpty()) {
                        con.addOneAnswerInDB(answer);
                    } else if (answer.getId() > 0 && !answer.getText().isEmpty()) {
                        con.updateAnswers(answer);
                    } else if (answer.getId() > 0 && answer.getText().isEmpty()) {
                        if (answer.getIsCorrect()) {
                            answer.setCorrect(false);
                            if (checkMinOneAnswerIsTrue(answers)){
                                con.deleteAnswerInDb(answer.getQuestionId(), answer.getId());
                            }
                        } else {
                            con.deleteAnswerInDb(answer.getQuestionId(), answer.getId());
                        }
                    }
                }
            } else {
                System.out.println("Mindestens eine Antwort muss als korrekt markiert sein!");
            }
        } else {
            System.out.println("Mindestens 2 Antworten müssen eingetragen werden!");
        }
    }

    public void addAnswerFromCreatedQuestion(int newCreatedQuestionId) {
        // Erstellt Answers für neu erstellte Question mit neu erstellter Questionid
        //Diese Methode wird nur ausgeführt, wenn neue question erstellt wird.
        // Array von Answers mit answerObjekten, die questionId enthält von neu erstellter Question
        ArrayList<Answer> addAnswers = createNewAnswerObjects(newCreatedQuestionId);
        ArrayList<Integer> intAnswerIds = parsingAnswerIdIntoInteger();
        DBTasks con = new DBTasks();
        //wenn Answer id = 0 und Text hat Länge = neue Answer in Db hinzufügen (insert mit questionid und answerText)
        for (int i = 0; i < intAnswerIds.size(); i++) {
            if (intAnswerIds.get(i) == 0 && !answerTextsArray.get(i).getText().trim().isEmpty()) {
                con.addOneAnswerInDB(addAnswers.get(i));
            }
        }
    }

    // Verifiziert ob mindestens eine Answer als korrekt markiert wurde.
    public boolean verifyMinOneAnswerIsTrue() {
        boolean verify = false;
        for (CheckBox checkBox : isCorrectCheckBoxesArray) {
            if (checkBox.isSelected()) {
                verify = true;
                break;
            }
        }
        return verify;
    }

    // Verifiziert ob mindestens ein Answerobjekt als korrekt deklariert wurde.
    public boolean checkMinOneAnswerIsTrue(ArrayList<Answer> answers) {
        boolean verify = false;
        for (Answer answer : answers) {
            if (answer.getIsCorrect()) {
                verify = true;
                break;
            }
        }
        return verify;
    }

    //Verifiziert ob mindestens 2 Answers ausgefüllt wurden (Alle mögliche Kombinationen getestet)
    public boolean verifyIfMinTwoAnswers() {
        boolean verify = false;
        int answersCount = 0;
        /* Check mit for Schleife und möglichem Abbruch */
        for (int i = 0; i < 3; i++) {
            if (!answerTextsArray.get(i).getText().trim().isEmpty()) {
                answersCount++;
            }
            if (answersCount == 2) {
                verify = true;
                break;
            }
        }

        return verify;
    }

    public ArrayList<Answer> createNewAnswerObjects(int questionID) {
        //erzeugt neue Answerobjekte befüllt sie mit answerId, text, isCorrect, question id von zugehöriger Frage
        //Füllt alle Answerojekte in eine Answer ArrayList
        ArrayList<Answer> answerAddList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            answerAddList.add(new Answer(
                    parsingAnswerIdIntoInteger().get(i),
                    answerTextsArray.get(i).getText().trim(),
                    isCorrectCheckBoxesArray.get(i).isSelected(),
                    questionID)
            );
        }
        return answerAddList;
    }

    public void switchToEditQuestionsClick(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("question-edit-overview.fxml"));
        try {
            Scene questioningScene = new Scene(loader.load());
            stage.setScene(questioningScene);
        } catch (IOException ioe) {
            System.out.println("Could not load scene");
            ioe.printStackTrace();
        }
    }

    public void fillAnswerTextAndAnswerIdsCheckboxesInArray() {
        //AnswerText in ArrayList abgefüllt
        Collections.addAll(answerTextsArray, answerText0,answerText1,answerText2,answerText3);

        //AnswerId's in ArrayList abgefüllt
        Collections.addAll(answerIdsArray, answerId0,answerId1,answerId2,answerId3);

        //Checkboxes in ArrayList abgefüllt
        Collections.addAll(isCorrectCheckBoxesArray, answerCorrect0,answerCorrect1,answerCorrect2,answerCorrect3);
    }

    public ArrayList<Integer> parsingAnswerIdIntoInteger() {
        ArrayList<Integer> intAnswerIdsArray = new ArrayList<>();
        for (int i = 0; i < answerIdsArray.size(); i++) {
            int id = 0;
            if (!answerIdsArray.get(i).getText().trim().isEmpty()) {
                id = Integer.parseInt(answerIdsArray.get(i).getText());
            }
            intAnswerIdsArray.add(i, id);
        }
        return intAnswerIdsArray;
    }

    public void updateQuestionTextCounterListener(KeyEvent keyEvent) {
        TextArea ta = ((TextArea) keyEvent.getTarget());
        updateTextCounter(ta, limitForQuestionText, questionTextCounterText);
    }

    public void updateAnswerTextCounterListener(KeyEvent keyEvent) {
        TextArea ta = ((TextArea) keyEvent.getTarget());
        Text counter = new Text("Error");
        // Find Counter Text for this answer
        VBox parent = (VBox) ta.getParent();
        // Get Child of type HBox (that contains our Text Nodes)
        for (Node child: parent.getChildren()) {
                if(child instanceof HBox){
                    counter = (Text)((HBox) child).getChildren().get(0);
                    break;
                }
        }
        updateTextCounter(ta, limitForAnswerText, counter);
    }

    public void updateTextCounter(TextArea textArea, int limit, Text counterText){
        String string = textArea.getText();
        int currentLength = textArea.getLength();

        if (currentLength > limit) {
            textArea.setText(string.substring(0, limit));
            textArea.positionCaret(string.length());
        }

        counterText.setText(Integer.toString(Math.min(limit, currentLength)));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Question edit Init called");

        fillAnswerTextAndAnswerIdsCheckboxesInArray();

        // Set question-text-length limits based on Params
        questionTextLimitText.setText(Integer.toString(limitForQuestionText));
        answer0TextLimitText.setText(Integer.toString(limitForAnswerText));
        answer1TextLimitText.setText(Integer.toString(limitForAnswerText));
        answer2TextLimitText.setText(Integer.toString(limitForAnswerText));
        answer3TextLimitText.setText(Integer.toString(limitForAnswerText));

    }

    public void initWithData(Question question) {
        id.setText(Integer.toString(question.getId()));
        questionText.setText(question.getQuestion());
        ArrayList<Answer> answers = question.getAnswers();
        //answers werden den answerText zugeteilt, wenn nur 3 answers bleibt 4. textarea einfach leer.
        for (int i = 0; i < answers.size(); i++) {
            answerTextsArray.get(i).setText(answers.get(i).getText());
            answerIdsArray.get(i).setText(String.valueOf(answers.get(i).getId()));
            isCorrectCheckBoxesArray.get(i).setSelected(answers.get(i).getIsCorrect());
        }

        // Set used length based on content in Question
        updateTextCounter(questionText,limitForQuestionText,questionTextCounterText);

        // Set used length based on content in Answers
        updateTextCounter(answerText0,limitForAnswerText,answer0TextCounterText);
        updateTextCounter(answerText1,limitForAnswerText,answer1TextCounterText);
        updateTextCounter(answerText2,limitForAnswerText,answer2TextCounterText);
        updateTextCounter(answerText3,limitForAnswerText,answer3TextCounterText);
    }


}
