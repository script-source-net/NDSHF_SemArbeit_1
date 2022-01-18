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
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class LearnmodeController extends AppController implements Initializable {

    boolean underCheck;                                             // Wird jeweils auf true gesetzt wenn Antworten geprüft werden

    int questionIndex = 0;                                          // Aktueller (Fragen) index 0-based
    int questionsCount = 0;                                         // Anzahl (max) Fragen gem. Selektion
    ArrayList<AnswerBox> answerBoxes = new ArrayList<>();           // AntwortBoxen FXML Elemente
    ArrayList<Question> selectedQuestions = new ArrayList<>();      // Liste der Auswahl an Fragen die getroffen wurde
    ArrayList<QuestionResult> questionResults = new ArrayList<>();  // Beinhaltet alle Resultate des aktuellen Durchlaufs
    ArrayList<Text> answerTextNodes = new ArrayList<>();            // TextNodes innerhalb der answerBoxen für direkten Zugriff im Loop (unten)


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
        // Alle AnswerBoxen zur Collection hinzufügen
        Collections.addAll(answerBoxes, answerBox0, answerBox1, answerBox2, answerBox3);
        Collections.addAll(answerTextNodes, answer0, answer1, answer2, answer3);
    }

    /* Custom Init */
    public void initLearnmode(int questionsCount, ArrayList<Question> questions){
        // setze questionsCount basierend auf Wert der Selektion & passe TextNode an
        this.questionsCount = questionsCount;
        actualQuestionNum.setText(Integer.toString(questionIndex+1));

        questionCountText.setText(Integer.toString(questionsCount));

        Collections.shuffle(questions);
        for (int i = 0; i < questionsCount; i++) {
            selectedQuestions.add(questions.get(i));
            Collections.shuffle(selectedQuestions.get(i).getAnswers());
        }
        System.out.println("Anzahl Fragen:" + selectedQuestions.size());
        // "Baue" Frage in die Scene ein:
        setNextQuestion(0);
    }

    private void setNextQuestion(int qindex){
        // Deaktiviere underCheck
        underCheck = false;
        // Publiziere die Frage
        questionText.setText(selectedQuestions.get(qindex).getQuestion());

        // Loop über alle AnswerBoxen um Styling (falsch / richtig & selected) zurückzusetzen
        for (AnswerBox answerBox:answerBoxes) {
            // CleanUp aller antworten
            // Alle zuvor gesetzten correct / false / selected Klassen entfernen (Styling)
            answerBox.getStyleClass().remove("correct");
            answerBox.getStyleClass().remove("false");
            answerBox.getStyleClass().remove("selected");

            // Zuerst alle answerBoxen sicher einblenden (Styling)
            answerBox.setVisible(true);

            // Alle zuvor selektierten Boxen zurücksetzen (Funktion)
            answerBox.setIsNotSelected();
        }


        for (int i = 0; i < selectedQuestions.get(qindex).getAnswers().size(); i++) {
            // Setze die Id der Antwort
            answerBoxes.get(i).setAnswerId(String.valueOf(selectedQuestions.get(qindex).getAnswers().get(i).getId()));
            // Setze ob die Antwort korrekt ist
            answerBoxes.get(i).setIsCorrectAnswer(selectedQuestions.get(qindex).getAnswers().get(i).getIsCorrect());
            // Setze Antworttext
            answerTextNodes.get(i).setText(selectedQuestions.get(qindex).getAnswers().get(i).getText());
        }

        // Blende "überflüssige" answerBoxes aus weil wir ggf. weniger als 4 antworten haben (Styling)
        if(selectedQuestions.get(qindex).getAnswers().size() < 4){
            for (int i = selectedQuestions.get(qindex).getAnswers().size(); i < 4 ; i++) {
                answerBoxes.get(i).setVisible(false);
            }
        }

        // Einblende Animationen
        fadeInTransition(questionText,1000,0);
        fadeInTransition(answersGrid, 1000,300);
    }

    public void clickedOnAnswer(MouseEvent mouseEvent) {
        //Prüfe ob wir im Check Modus sind (Antworten prüfen wurde aktiviert)
        if(!underCheck) {
            // Identifiziere das geklickte Element
            Node clickedElNode = (Node) mouseEvent.getTarget();
            // Prüfe ob klick auf AnswerBox direkt oder auf TextNode (child) ausgeführt wurde & identifiziere den entsprechenden Container
            AnswerBox container = clickedElNode instanceof Text ? ((AnswerBox) clickedElNode.getParent()) : ((AnswerBox) clickedElNode);

            // Toggle selectedStatus
            container.setIsSelected();

            // Styling anwenden
            if (container.getStyleClass().contains("selected")) {
                container.getStyleClass().remove("selected");
            } else {
                container.getStyleClass().add("selected");
            }
        }
    }

    public void checkAnswersClicked(ActionEvent actionEvent) {
        // Aktiviere underCheck
        underCheck = true;

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

        // Punkte Berechnen & ins Array übergeben:
        questionResults.add(calcQuestionPts(answerBoxes));

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

        setNextQuestion(questionIndex);

    }

    public void goToEndScreenClicked(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getTarget()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("learnmode-endcard.fxml"));
        try {
            Scene questioningScene = new Scene(loader.load());
            LearnmodeEndcardController sceneController = loader.getController();
            sceneController.initResults(questionResults);
            stage.setScene(questioningScene);
        }catch (IOException ioe){
            System.out.println("Could not load scene");
            ioe.printStackTrace();
        }
    }

    // Note: @Claudia Diese Methode kann getestet werden mittels Injection
    private QuestionResult calcQuestionPts(ArrayList<AnswerBox> answerBoxes){
        QuestionResult qr = new QuestionResult();
        for (AnswerBox a: answerBoxes) {
            if(a.isVisible()) {
                qr.countAnswers++;
                if (a.getIsCorrectAnswer()) {
                    qr.countCorrectAnswers++;
                    if (a.getIsSelected()) {
                        qr.pts++;
                    } else {
                        qr.pts--;
                        qr.fullyCorrect = false;
                    }
                } else {
                    qr.countWrongAnswers++;
                    if (a.getIsSelected()) {
                        qr.pts--;
                        qr.fullyCorrect = false;
                    } else {
                        qr.pts++;
                    }
                }
            }
            // Korrektur wenn mehr falsch als richtig selektiert:
            qr.pts = (qr.pts < 0) ? 0 : qr.pts;
        }

        System.out.println("Answers / Max Pts: "+qr.countAnswers);
        System.out.println("Korrekte Antworten: "+qr.countCorrectAnswers);
        System.out.println("Falsche Antworten: "+qr.countWrongAnswers);
        System.out.println("Errecihte Punkte: "+qr.pts);
        System.out.println("Voll Richtig? "+qr.fullyCorrect);

        return qr;
    }
}
