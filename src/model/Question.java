package model;

import java.io.Serializable;

public class Question {
    public enum Answer {A, B, C, D}
    public String myQuestion;
    public Answer myAnswer;
    public int myDifficulty;
    
    public Question() {
        myAnswer = Answer.A;
    }
    public Answer getMyAnswer() {
        return myAnswer;
    }
}
