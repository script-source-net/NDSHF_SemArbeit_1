package com.tbt.trainthebrain.sqlcontroller;

import com.tbt.trainthebrain.Answer;

import java.sql.*;

public class DBTasks {

    public void addOneAnswerInDB(Answer answer){

        String query = "INSERT INTO answers (answer_text, answer_correct, question_id) VALUE ('" + answer.getText() + "',"
                + answer.getIsCorrect() + "," + answer.getQuestionId() + ");";

        try (Connection con = DriverManager.getConnection(SQLConnectionData.getURL(), SQLConnectionData.getUSER(), SQLConnectionData.getPASSWORD());
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Connection NOT OK");
            ex.printStackTrace();

        }

    }


    public void updateAnswers(int questionId, String answerText, boolean isCorrect, int answerId){
        String query = "UPDATE answers SET answer_text='" + answerText + "', answer_correct=" + isCorrect + "" +
                " WHERE question_id=" + questionId + " AND answer_id=" + answerId;

        try (Connection con = DriverManager.getConnection(SQLConnectionData.getURL(), SQLConnectionData.getUSER(), SQLConnectionData.getPASSWORD());
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Connection NOT OK");
            ex.printStackTrace();

        }

    }

    public void deleteAnswerInDb(int questionId, int answerId){
        String query = "DELETE FROM answers WHERE answer_id=" + answerId +
                " AND question_id=" + questionId;

        try (Connection con = DriverManager.getConnection(SQLConnectionData.getURL(), SQLConnectionData.getUSER(), SQLConnectionData.getPASSWORD());
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query);
        ){


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Connection NOT OK");
            ex.printStackTrace();

        }

    }

    public int insertNewQuestion(String questionText) {
        String query = "INSERT INTO questions (question_text) VALUE ('" + questionText + "') RETURNING question_id;";
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
            System.out.println("Connection NOT OK");
            ex.printStackTrace();


        }
        return lastCreatetQuestionId;
    }

    public void UpdateQuestion(int questionId, String questionText) {
        String query = "UPDATE questions SET question_text='" + questionText + "' WHERE question_id=" + questionId;

        try (Connection con = DriverManager.getConnection(SQLConnectionData.getURL(), SQLConnectionData.getUSER(), SQLConnectionData.getPASSWORD());
             Statement statement = con.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Connection NOT OK");
            ex.printStackTrace();

        }
    }

    public void deleteQuestionAndAnswersInDb(int questionId){
        String query = "DELETE FROM questions WHERE question_id =" + questionId;
        String query2 = "DELETE FROM answers WHERE question_id=" + questionId;


        try (Connection con = DriverManager.getConnection(SQLConnectionData.getURL(), SQLConnectionData.getUSER(), SQLConnectionData.getPASSWORD());
             Statement statement = con.createStatement();
             Statement statement2 = con.createStatement();

             ResultSet rs = statement.executeQuery(query2)) {
            ResultSet rs2 = statement2.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Connection NOT OK");
            ex.printStackTrace();

        }
    }
}