package com.tbt.trainthebrain;

public class Answer {

    private int id;
    private String text;
    private boolean isCorrect;
    private int questionId;


    public Answer(int id, String text, boolean isCorrect, int questionId) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
    }
    public Answer(){}



    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public int getQuestionId() {
        return questionId;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", isCorrect=" + isCorrect +
                ", questionId=" + questionId +
                '}';
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }
}
