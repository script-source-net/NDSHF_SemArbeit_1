package com.tbt.trainthebrain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class LearnmodeController extends AppController implements Initializable {

    int questionIndex = 0;                                      // Aktueller (Fragen) index 0-based
    int questionsCount = 0;                                     // Anzahl (max) Fragen gem. Selektion
    ArrayList<AnswerBox> answerBoxes = new ArrayList<>();       // AntwortBoxen FXML Elemente

    @FXML
    AnswerBox answerBox0,answerBox1,answerBox2,answerBox3;

    @FXML
    Button cancelLearnModeBtn,checkBtn,nextQuestBtn,endcardBtn;

    @FXML
    Text actualQuestionNum, questionCountText, questionText, answer0,answer1,answer2,answer3;

    @FXML
    GridPane answersGrid;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        System.out.println(questions);
    }

    private ArrayList<Question> getQuestionsFromDatabase(int limit){
        ArrayList<Question> questions = new ArrayList<>();

        // TODO: SQL request to get questions from database

        // --> TEMP SOLUTION OBJEKTE HARDCODED
        ArrayList<Answer> tempA = new ArrayList<>();
        tempA.add(new Answer(0,"Das ist meine erste Antwort", true,1));
        tempA.add(new Answer(1,"Das ist meine 2. Antwort", false,1));
        tempA.add(new Answer(2,"Das ist die 3. Option", true,1));

        ArrayList<Answer> tempB = new ArrayList<>();
        tempB.add(new Answer(0,"Robin", false,1));
        tempB.add(new Answer(1,"Marshall Bruce Mathers III", true,1));
        tempB.add(new Answer(2,"Van Gogh", false,1));
        tempB.add(new Answer(3,"Brian Tillmore", false,1));

        Collections.shuffle(tempB);

        questions.add(new Question(2,"Batman verhält sich zu Bruce Wayne wie Eminem zu?", tempB));
        questions.add(new Question(0,"Eine Frage die ich stellen muss?", tempA));
        Collections.shuffle(tempA);
        questions.add(new Question(1,"Eine andere Question of interest die ich stellen muss?", tempA));
        // TEMP <---
        return questions;
    }

    private ArrayList<Question> getAnswersForQuestionFromDatabase(int questionId){
        ArrayList<Question> answers = new ArrayList<>();

        // TODO: SQL request to get answers vor question from database

        return answers;
    }

    /* Custom Init */
    public void initLearnmode(int questionsCount){
        // setze questionsCount basierend auf Wert der Selektion & passe TextNode an
        this.questionsCount = questionsCount;
        actualQuestionNum.setText(Integer.toString(questionIndex+1));

        questionCountText.setText(Integer.toString(questionsCount));
        questions = getQuestionsFromDatabase(questionsCount);
        // "Baue" Frage in die Scene ein:
        setNextQuestion(0, questions);
    }

    private void setNextQuestion(int qindex, ArrayList<Question> questions){
        // Publiziere die Frage
        questionText.setText(questions.get(qindex).getQuestion());
        // Lösche alle AnswerBoxen aus der ArrayList (nötig auf Seite 2 <) und adde alle 4 Boxen
        if(qindex > 0) answerBoxes.clear();

        answerBoxes.add(answerBox0);
        answerBoxes.add(answerBox1);
        answerBoxes.add(answerBox2);
        answerBoxes.add(answerBox3);

        // TextNodes innerhalb der answerBoxen für direkten Zugriff im Loop (unten)
        ArrayList<Text> answerTextNodes = new ArrayList<>();
        answerTextNodes.add(answer0);
        answerTextNodes.add(answer1);
        answerTextNodes.add(answer2);
        answerTextNodes.add(answer3);

        // Loop über alle Answers
        for (int i = 0; i < questions.get(qindex).getAnswers().size(); i++) {
            // Setze die Id der Antwort
            answerBoxes.get(i).setAnswerId(String.valueOf(questions.get(qindex).getAnswers().get(i).getId()));
            // Setze ob die Antwort korrekt ist
            answerBoxes.get(i).setIsCorrectAnswer(questions.get(qindex).getAnswers().get(i).getIsCorrect());
            // Setze Antworttext
            answerTextNodes.get(i).setText(questions.get(qindex).getAnswers().get(i).getText());

            // CleanUp aller antworten
            // Alle zuvor gesetzten correct / false / selected Klassen entfernen (Styling)
            answerBoxes.get(i).getStyleClass().remove("correct");
            answerBoxes.get(i).getStyleClass().remove("false");
            answerBoxes.get(i).getStyleClass().remove("selected");

            // Alle zuvor selektierten Boxen zurücksetzen (Funktion)
            answerBoxes.get(i).setIsNotSelected();

            // Zuerst alle answerBoxen sicher einblenden (Styling)
            answerBoxes.get(i).setVisible(true);
        }

        // Blende "überflüssige" answerBoxes aus weil wir ggf. weniger als 4 antworten haben (Styling)
        if(questions.get(qindex).getAnswers().size() < 4){
            for (int i = questions.get(qindex).getAnswers().size(); i < 4 ; i++) {
                answerBoxes.get(i).setVisible(false);
            }
        }

        // Einblende Animationen
        fadeInTransition(questionText,1000,0);
        fadeInTransition(answersGrid, 1000,300);

    };



    public void clickedOnAnswer(MouseEvent mouseEvent) {
        // Identifiziere das geklickte Element
        Node clickedElNode = (Node) mouseEvent.getTarget();
        // Prüfe ob klick auf AnswerBox direkt oder auf TextNode (child) ausgeführt wurde & identifiziere den entsprechenden Container
        AnswerBox container = clickedElNode instanceof Text ? ((AnswerBox) clickedElNode.getParent()) : ((AnswerBox)clickedElNode);

        // Toggle selectedStatus
        container.setIsSelected();

        // Styling anwenden
        if(container.getStyleClass().contains("selected")){
            container.getStyleClass().remove("selected");
        }else{
            container.getStyleClass().add("selected");
        }

    }

    public void checkAnswersClicked(ActionEvent actionEvent) {
        // Jede AnswerBox auf Status prüfen
        for (AnswerBox answerBox: answerBoxes) {
            if (answerBox.isSelected) {
                if (answerBox.isCorrect) {
                    answerBox.getStyleClass().add("correct");
                } else {
                    answerBox.getStyleClass().add("false");
                }
            }else{
                if (answerBox.isCorrect) {
                    answerBox.getStyleClass().add("false");
                }
            }
        }
        // Den CheckBtn nun ausblenden
        checkBtn.setVisible(false);

        // Wenn wir bei der letzten Frage angekommen sind zeigen wir den Button zur Endcard sonst Next Button
        if(questionIndex+1 < questionsCount){
            nextQuestBtn.setVisible(true);
        }else{
            endcardBtn.setVisible(true);
        }
    }

    public void switchQuestionClicked(ActionEvent actionEvent) {
        checkBtn.setVisible(true);
        nextQuestBtn.setVisible(false);
        questionIndex++;

        actualQuestionNum.setText(Integer.toString(questionIndex+1));

        setNextQuestion(questionIndex, questions);

    }

    public void goToEndScreenClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("learnmode-endcard.fxml"));
        try {
            Scene questioningScene = new Scene(loader.load());
            AppController sceneController = loader.getController();
            stage.setScene(questioningScene);
        }catch (IOException ioe){
            System.out.println("Could not load scene");
            ioe.printStackTrace();
        }

    }
}
