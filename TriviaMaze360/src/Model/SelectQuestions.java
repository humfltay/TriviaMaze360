package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void selectAll(){
        String sql = "SELECT questionIndex, difficulty, questionType, questions FROM questions";
        
        try (Connection conn = this.connect();
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
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
