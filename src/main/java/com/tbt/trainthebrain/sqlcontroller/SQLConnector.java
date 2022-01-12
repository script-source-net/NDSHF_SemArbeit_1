package com.tbt.trainthebrain.sqlcontroller;

import com.tbt.trainthebrain.Question;
import com.tbt.trainthebrain.controllers.QuestionController;

import java.sql.*;
import java.util.ArrayList;

public class SQLConnector implements SQLStatements{
    public SQLConnector(){
        try (Connection con = DriverManager.getConnection(
                SQLConnectionData.getURL(),
                SQLConnectionData.getUSER(),
                SQLConnectionData.getPASSWORD())
        ){

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertQuestion(String questionText) {
        try (Connection con = DriverManager.getConnection(
                SQLConnectionData.getURL(),
                SQLConnectionData.getUSER(),
                SQLConnectionData.getPASSWORD())
        ){
            PreparedStatement ps = con.prepareStatement(insertQuestion);
            ps.setString(1,questionText);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertAnswer(String answerText, Boolean answerFalseTrue, int questionID){

    }


    public ArrayList selectQuestions(String selectQuestions){
        ArrayList<QuestionController> returnQuestions = new ArrayList<>();
        ResultSet rs = null;
        try (Connection con = DriverManager.getConnection(
                SQLConnectionData.getURL(),
                SQLConnectionData.getUSER(),
                SQLConnectionData.getPASSWORD()
        )) {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(selectQuestions);
            while (rs.next()){
                returnQuestions.add(new QuestionController(rs.getInt("question_id"),
                        rs.getString("question_text")));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return returnQuestions;
    }

}
