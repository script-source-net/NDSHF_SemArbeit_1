package com.tbt.trainthebrain.sqlcontroller;

public interface SQLStatements {
    String insertQuestion = "INSERT INTO tbl_questions (question_text) " +
            "VALUES (?)";

    String insertAnswer = "INSERT INTO tbl_answers (answer_text, answer_correct, question_id) " +
            "VALUES (?,?,?);";

    String selectQuestions = "SELECT * FROM tbl_questions;";
}
