package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class SelectQuestions {
    /**
     * Making connection to SQLite database.
     * 
     * @return connection by url
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:QuestionDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    
    /**
     * Takes call to database and converts to single
     * question.
     * 
     * @return random question
     */
    public List<String> getRandomQuestion(){
        ArrayList<List<String>> questions = new ArrayList<List<String>>();
        ArrayList<String> question = null;
        String sql = "SELECT questionIndex, difficulty, questionType, questions FROM questions";
        Random ran = new Random();
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
               question = new ArrayList<String>();
                question.add(rs.getInt("difficulty") + "");
                question.add(rs.getString("questionType"));
                question.add(rs.getString("questions") + "");
                questions.add(question);
                
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return questions.get(ran.nextInt(questions.size()));
    }
    /**
     * Returns questions harder than specified difficulty
     * 
     * @param theDifficulty param sent to specify difficulty
     */
    public List<String> getQuestionsHarderThan(int theDifficulty){
        ArrayList<List<String>> questions = new ArrayList<List<String>>();
        ArrayList<String> question = null;
        Random ran = new Random();
        String sql = "SELECT questionIndex, difficulty, questionType, questions "
                   + "FROM questions WHERE difficulty > ?";
         try (Connection conn = this.connect();
              PreparedStatement pstmt  = conn.prepareStatement(sql)){
             
             // set the value
             pstmt.setInt(1,theDifficulty);
             //
             ResultSet rs  = pstmt.executeQuery();
             
             // loop through the result set
             
             while (rs.next()) {
                 question = new ArrayList<String>();
                 question.add(rs.getInt("difficulty") + "");
                 question.add(rs.getString("questionType"));
                 question.add(rs.getString("questions") + "");
                 questions.add(question);
             }
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
         return questions.get(ran.nextInt(questions.size()));
    }
    /**
     * Returns a random question of specified difficulty
     * 
     * @param theDifficulty
     * @return Question
     */
    public List<String> getQuestionOfDifficulty(final int theDifficulty){
        ArrayList<List<String>> questions = new ArrayList<List<String>>();
        ArrayList<String> question = null;
        Random ran = new Random();
        String sql = "SELECT questionIndex, difficulty, questionType, questions "
                   + "FROM questions WHERE difficulty = ?";
        try (Connection conn = this.connect();
              PreparedStatement pstmt  = conn.prepareStatement(sql)){
             
             // set the value
             pstmt.setInt(1,theDifficulty);
             //
             ResultSet rs  = pstmt.executeQuery();
             
             // loop through the result set
             while (rs.next()) {
                 question = new ArrayList<String>();
                 question.add(rs.getInt("difficulty") + "");
                 question.add(rs.getString("questionType"));
                 question.add(rs.getString("questions") + "");
                 questions.add(question);
             }
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
        return questions.get(ran.nextInt(questions.size()));
    }
    /**
     * Creates questions of a specific type.
     * @param theType of question being grabbed.
     * @return a question of a specific type.
     */
    public List<String> getQuestionOfType(final String theType){
        ArrayList<List<String>> questions = new ArrayList<List<String>>();
        ArrayList<String> question = null;
        Random ran = new Random();
        String sql = "SELECT questionIndex, difficulty, questionType, questions "
                   + "FROM questions WHERE questionType = ?";
         try (Connection conn = this.connect();
              PreparedStatement pstmt  = conn.prepareStatement(sql)){
             
             // set the value
             pstmt.setString(1,theType);
             //
             ResultSet rs  = pstmt.executeQuery();
             
             // loop through the result set
             while (rs.next()) {
                 question = new ArrayList<String>();
                 question.add(rs.getInt("difficulty") + "");
                 question.add(rs.getString("questionType"));
                 question.add(rs.getString("questions") + "");
                 questions.add(question);
             }
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
         return questions.get(ran.nextInt(questions.size()));
    }
}