package model;
import java.sql.*;

/**
 * QuestionBase acts as a local database using SQLite in order to hold questions for a Trivia Maze
 * 
 * @author Taylor Humfleet UWID: humfltay
 *
 */
public class QuestionBase {

    
    public static void main(String args[]) {
          createTable();
          System.out.println("made it here");
          InsertQuestion iq = new InsertQuestion();
          //iq.insert(2, "Geography", "Walk,Jog,Talk");
          //iq.insert(1, "Geography", "What is the capitol of Washington,Olympia,Tacoma,Westchester,Miami,Seattle,Everett,Portland");
          //iq.insert(3, "Geography", "What is the capitol of Washington,Olympia,Tacoma,Westchester,Miami,Seattle,Everett,Portland");
          SelectQuestions sq = new SelectQuestions();
          sq.selectAll();
          System.out.println();
          sq.getQuestionsHarderThan(1);
          System.out.println();
          sq.getQuestionsOfDifficulty(2);
    }
    public static void createTable() {
        String url = "jdbc:sqlite:QuestionDB.db";
        StringBuilder sqlBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS questions");
        sqlBuilder.append(" (\n");
        sqlBuilder.append("   questionIndex integer PRIMARY KEY,\n");
        sqlBuilder.append("   difficulty integer NOT NULL,\n");
        sqlBuilder.append("   questionType text NOT NULL,\n");
        sqlBuilder.append("   questions text NOT NULL\n");
        sqlBuilder.append(");");
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            String sql = sqlBuilder.toString();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}

