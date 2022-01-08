package com.tbt.trainthebrain;

public class Answer {
    int id;
    String text;
    boolean isCorrect;

    public Answer(int id, String text, boolean isCorrect) {
        this.id = id;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}
