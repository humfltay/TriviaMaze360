package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;

public class InsertQuestion {
    private static int myQuestionCount = 0;
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
     * Insert a new row into the questions table
     *
     */
    public void insert(int theDifficulty, String theQuestionType, String theQuestions) {
        String sql = "INSERT INTO questions(questionIndex,difficulty,questionType,questions) VALUES(?,?,?,?)";
        
        try (Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, myQuestionCount);
            myQuestionCount++;
            pstmt.setInt(2, theDifficulty);
            pstmt.setString(3, theQuestionType);
            pstmt.setString(4, theQuestions);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
