package model;

import java.util.List;
import java.util.Random;

import modelTay.SelectQuestions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Question implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2300715034378187712L;
    public enum QuestionNature{MULTIPLE, TRUE, SHORT}
    //I set Question fields to private.
    private String myQuestion;
    private String myCorrectAnswer;
    private String myType;
    private QuestionNature myQuestionNature;
    private String[] myWrongAnswers;
    private int myDifficulty;
    //He uses type in a different context than me.
    //QuestionType doesn't actually do anything.
    //myTopic used to be myType.
    public Question(String theType) {
        setQuestionOfType(theType);
    }
    private void setQuestionOfType(String theType) {
      SelectQuestions sq = new SelectQuestions();
      List<String> contents = sq.getQuestionOfType(theType);
      setUpQuestion(contents);
    }
    public Question() {
        setRandomQuestion();
    }
    //FORMAT OF LIST -> difficulty, questionType, questions
    private void setRandomQuestion() {
        //creating link to database helper method
        SelectQuestions sq = new SelectQuestions();
        //making call to database
        List<String> contents = sq.getRandomQuestion();
        //setting up the question fields
        setUpQuestion(contents);
        
    }
    /**
     * Sets up the question for the door.
     * @param theContents a specially formatted line from the database.
     */
    private void setUpQuestion(List<String> theContents) {
        myDifficulty = Integer.valueOf(theContents.get(0));
        myType = theContents.get(1);
        String[] questions = theContents.get(2).split(",");

        String[] wrongAnswers = new String[questions.length - 3];
        for (int i = 3; i < questions.length; i ++) {
            wrongAnswers[i - 3] = questions[i];
        }
        //By default it is multiple choice.
        myQuestion = questions[0];
        myCorrectAnswer = questions[1];
        myWrongAnswers = wrongAnswers;
        
        int rand = new Random().nextInt(3);
        myQuestionNature = QuestionNature.values()[rand];
        //Set up Short answer questions. Must be short, 
        if (getMyQuestionNature() == QuestionNature.SHORT) {
            if (myCorrectAnswer.length() < 10) {
                myCorrectAnswer.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
            } else {
                myQuestionNature = QuestionNature.MULTIPLE;
            }
        } else if (getMyQuestionNature() == QuestionNature.TRUE) {
            rand = new Random().nextInt(2);
            if (rand == 0) {
                myCorrectAnswer = "true";
                myQuestion = "True or False: " + questions[0] + "? Is it " + questions[1] + "?";
            } else {
                myCorrectAnswer = "false";
                rand = new Random().nextInt(wrongAnswers.length);
                myQuestion = "True or False: " + questions[0] + "? Is it " + wrongAnswers[rand] + "?";
            }
        }
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Question: ");
        s.append(getMyQuestion());
        s.append("\n");
        s.append("Answer: ");
        s.append(getMyCorrectAnswer());
        s.append("\n");
        s.append("Type: ");
        s.append(myType);
        s.append("\n");
        s.append("Difficulty: ");
        s.append(myDifficulty);
        s.append("\n");
        s.append("Elements: ");
        for (String elem: getMyWrongAnswers()) {
            s.append(elem);
            s.append("\t");
        }
        return s.toString();
    }
    public String[] getMyWrongAnswers() {
        return myWrongAnswers;
    }
    public String getMyCorrectAnswer() {
        return myCorrectAnswer;
    }
    public QuestionNature getMyQuestionNature() {
        return myQuestionNature;
    }
    public String getMyQuestion() {
        return myQuestion;
    }
}
