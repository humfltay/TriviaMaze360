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
     * Select all rows in the questions table
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
//                System.out.println(rs.getInt("questionIndex") +  "\t" + 
//                        rs.getInt("difficulty") +  "\t" +
//                        rs.getString("questionType") + "\t" +
//                        rs.getString("questions"));
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
    public void getQuestionsHarderThan(int theDifficulty){
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
                 System.out.println(rs.getInt("questionIndex") +  "\t" + 
                                    rs.getInt("difficulty") +  "\t" +
                                    rs.getString("questionType") + "\t" +
                                    rs.getString("questions"));
                 //rs.getInt(theDifficulty);
                 //LATER
             }
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
    }
    public void getQuestionsOfDifficulty(int theDifficulty){
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
                 System.out.println(rs.getInt("questionIndex") +  "\t" + 
                                    rs.getInt("difficulty") +  "\t" +
                                    rs.getString("questionType") + "\t" +
                                    rs.getString("questions"));
             }
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
    }
}
