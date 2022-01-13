package com.tbt.trainthebrain.sqlcontroller;

import com.tbt.trainthebrain.Answer;
import com.tbt.trainthebrain.Question;

import java.sql.*;
import java.util.ArrayList;

public class DBTasks {

    /* Methoden für QuestionEditOverviewController */

    public ArrayList<Question> getAllQuestionsFromDb(){
        ArrayList<Question> questions = new ArrayList<>();
        String query =  "SELECT * FROM tbl_questions";

        try (
                Connection con = DriverManager.getConnection(SQLConnectionData.getURL(), SQLConnectionData.getUSER(), SQLConnectionData.getPASSWORD());
                Statement statement = con.createStatement();
                ) {

            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                ArrayList<Answer> answers = getAllAnswersForQuestion(rs.getInt("question_id"));
                questions.add(
                        new Question(
                            rs.getInt("question_id"),
                            rs.getString("question_text"),
                            answers
                        )
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Fehler beim laden aller Question aus der Datenbank:");
            ex.printStackTrace();
        }
        return questions;
    }

    private ArrayList<Answer> getAllAnswersForQuestion(int questionId){
        ArrayList<Answer> answers = new ArrayList<>();
        String query =  "SELECT * FROM tbl_answers WHERE question_id = '"+questionId+"'";
        try (
                Connection con = DriverManager.getConnection(SQLConnectionData.getURL(), SQLConnectionData.getUSER(), SQLConnectionData.getPASSWORD());
                Statement statement = con.createStatement();
        ) {

            ResultSet rs = statement.executeQuery(query);
            while(rs.next()){
                answers.add(
                        new Answer(
                                rs.getInt("answer_id"),
                                rs.getString("answer_text"),
                                rs.getBoolean("answer_correct"),
                                questionId
                        )
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("UpdateAnswers hat Fehler geworfen:");
            ex.printStackTrace();

        }
        return answers;
    }

    /* Methoden für QuestionEditController */

    public void addOneAnswerInDB(Answer answer){

        String query = "INSERT INTO tbl_answers (answer_text, answer_correct, question_id) VALUE ('" + answer.getText() + "'," + answer.getIsCorrect() + "," + answer.getQuestionId() + ");";

        try (Connection con = DriverManager.getConnection(SQLConnectionData.getURL(), SQLConnectionData.getUSER(), SQLConnectionData.getPASSWORD());
             Statement statement = con.createStatement()) {

            Boolean status = statement.execute(query);
            if(status){
                System.out.println("Neue Antwort für QID:"+answer.getQuestionId());
            }else{
                System.out.println("Hinzufügen der Antwort für QID:" + answer.getQuestionId() + " gescheitert!");
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("addOneAnswerInDB hat Fehler geworfen:");
            ex.printStackTrace();

        }

    }

    public void updateAnswers(int questionId, String answerText, boolean isCorrect, int answerId){
        String query = "UPDATE tbl_answers SET answer_text='" + answerText + "', answer_correct=" + isCorrect + "" + " WHERE question_id=" + questionId + " AND answer_id=" + answerId;

        try (Connection con = DriverManager.getConnection(SQLConnectionData.getURL(), SQLConnectionData.getUSER(), SQLConnectionData.getPASSWORD());
             Statement statement = con.createStatement()) {
            Boolean status = statement.execute(query);
            if(status){
                System.out.println("Antwort ("+answerId+") für Frage "+ questionId + " aktualisiert");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("UpdateAnswers hat Fehler geworfen:");
            ex.printStackTrace();

        }

    }

    public void deleteAnswerInDb(int questionId, int answerId){
        String query = "DELETE FROM tbl_answers WHERE answer_id=" + answerId + " AND question_id=" + questionId;

        try (Connection con = DriverManager.getConnection(SQLConnectionData.getURL(), SQLConnectionData.getUSER(), SQLConnectionData.getPASSWORD());
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query);
        ){


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("DeleteAnswerInDb hat Fehler geworfen:");
            ex.printStackTrace();

        }

    }

    public int insertNewQuestion(String questionText) {
        String query = "INSERT INTO tbl_questions (question_text) VALUE ('" + questionText + "') RETURNING question_id;";
        //String query2 = "SELECT LAST_INSERT_ID();";
        int lastCreatetQuestionId = 0;
        try (Connection con = DriverManager.getConnection(SQLConnectionData.getURL(), SQLConnectionData.getUSER(), SQLConnectionData.getPASSWORD());
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            // ResultSet rs2 = statement.executeQuery(query2);

            while (rs.next()) {
                lastCreatetQuestionId = rs.getInt("question_id");
            }
            System.out.println("letze generierte question_ID " + lastCreatetQuestionId);


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("insertNewQuestion hat einen Fehler geworfen:");
            ex.printStackTrace();


        }
        return lastCreatetQuestionId;
    }

    public void UpdateQuestion(int questionId, String questionText) {

        System.out.println(questionId);
        System.out.println(questionText);
        System.out.println("-------");
        String query = "UPDATE tbl_questions SET question_text='" + questionText + "' WHERE question_id=" + questionId;

        try (Connection con = DriverManager.getConnection(SQLConnectionData.getURL(), SQLConnectionData.getUSER(), SQLConnectionData.getPASSWORD());
             Statement statement = con.createStatement()){

            Boolean status = statement.execute(query);
            System.out.println("Question "+questionId+ "updated: "+status);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("UpdateQuestion Methode hat einen Fehler geworfen:");
            ex.printStackTrace();
        }
    }

    public void deleteQuestionAndAnswersInDb(int questionId){
        String query = "DELETE FROM tbl_questions WHERE question_id =" + questionId;
        String query2 = "DELETE FROM tbl_answers WHERE question_id=" + questionId;


        try (Connection con = DriverManager.getConnection(SQLConnectionData.getURL(), SQLConnectionData.getUSER(), SQLConnectionData.getPASSWORD());
             Statement statement = con.createStatement();
             Statement statement2 = con.createStatement();

             ResultSet rs = statement.executeQuery(query2)) {
            ResultSet rs2 = statement2.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("deleteQuestionAndAnswersInDb hat Fehler geworfen:");
            ex.printStackTrace();

        }
    }
}