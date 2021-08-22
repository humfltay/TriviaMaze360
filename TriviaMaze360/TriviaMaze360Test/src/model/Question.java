package model;

import java.util.List;
import java.util.Random;

import model.SelectQuestions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Question implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2300715034378187712L;
    public enum QuestionNature{MULTIPLE, TRUE, SHORT, ALL}
    //I set Question fields to private.
    private String myQuestionText;
    private String myCorrectAnswer;
    private String myType;
    private QuestionNature myQuestionNature;
    private String[] myWrongAnswers;
    private int myDifficulty;
    private static final int MAXDIFFICULTY = 4;
    private static final int SHORTLENGTH = 12;
    /**
     * 
     * @param theType
     */
    public Question(String theType) {
        setQuestionOfType(theType);
    }
    private void setQuestionOfType(String theType) {
      SelectQuestions sq = new SelectQuestions();
      List<String> contents = sq.getQuestionOfType(theType);
      setUpQuestion(contents);
    }
    /**
     * Initializes a Question of specific difficulty.
     * If the difficulty exceeds bounds, create random question.
     * @param theDifficulty the level of the question.
     */
    public Question(final int theDifficulty) {
        if (theDifficulty <= MAXDIFFICULTY)
            setQuestionOfDifficulty(theDifficulty);
        else setRandomQuestion();
    }
    /**
     * parameterless constructor for the Question class.
     */
    public Question() {
        setRandomQuestion();
    }
    /**
     * Creates a random question.
     */
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
     * Creates a question of specific difficulty.
     * @param theDifficulty the level of the question.
     */
    private void setQuestionOfDifficulty(final int theDifficulty) {
        SelectQuestions sq = new SelectQuestions();
        List<String> contents = sq.getQuestionOfDifficulty(theDifficulty);
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
        //Start at 2 for wrong answers.
        String[] wrongAnswers = new String[questions.length - 2];
        for (int i = 2; i < questions.length; i ++) {
            wrongAnswers[i - 2] = questions[i];
        }
        //By default it is multiple choice.
        myQuestionText = questions[0];
        myCorrectAnswer = questions[1];
        myWrongAnswers = wrongAnswers;
        
        int rand = new Random().nextInt(3);
        myQuestionNature = QuestionNature.values()[rand];
        //Set up Short answer questions. Must be short, 
        if (getMyQuestionNature() == QuestionNature.SHORT) {
            if (myCorrectAnswer.length() < SHORTLENGTH) {
                myCorrectAnswer.replaceAll("[^a-zA-Z0-9 ]", "").toLowerCase();
            } else {
                myQuestionNature = QuestionNature.MULTIPLE;
            }
        } else if (getMyQuestionNature() == QuestionNature.TRUE) {
            rand = new Random().nextInt(2);
            if (rand == 0) {
                myCorrectAnswer = "true";
                myQuestionText = "True or False: " + questions[0] + "? Is it " + questions[1] + "?";
            } else {
                myCorrectAnswer = "false";
                rand = new Random().nextInt(wrongAnswers.length);
                myQuestionText = "True or False: " + questions[0] + "? Is it " + wrongAnswers[rand] + "?";
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
        return myQuestionText;
    }
}
