package model;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 2300715034378187712L;
    public enum QuestionType{TRUE, MULTIPLE, SHORT}
    public String myQuestion;
    public String myCorrectAnswer;
    public String myTopic;
    public QuestionType myQuestionType;
    public String[] myWrongAnswers;
    public int myDifficulty;
    
    public Question(String theType) {
        //not yet implement
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
    private void setUpQuestion(List<String> theContents) {
        myDifficulty = Integer.valueOf(theContents.get(0));
        myTopic = theContents.get(1);
        String[] questions = theContents.get(2).split(",");
        myQuestion = questions[0];
        myCorrectAnswer = questions[1];
        String[] wrongAnswers = new String[questions.length - 3];
        for (int i = 3; i < questions.length; i ++) {
            wrongAnswers[i - 3] = questions[i];
        }
        myWrongAnswers = wrongAnswers;
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Question: ");
        s.append(myQuestion);
        s.append("\n");
        s.append("Answer: ");
        s.append(myCorrectAnswer);
        s.append("\n");
        s.append("Type: ");
        s.append(myTopic);
        s.append("\n");
        s.append("Difficulty: ");
        s.append(myDifficulty);
        s.append("\n");
        s.append("Elements: ");
        for (String elem: myWrongAnswers) {
            s.append(elem);
            s.append("\t");
        }
        return s.toString();
    }
}
