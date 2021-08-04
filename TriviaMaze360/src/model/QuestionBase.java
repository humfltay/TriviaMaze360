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
          iq.insert(1, "Geography", "What is the capitol of Washington,Olympia,Tacoma,Westchester,Miami,Seattle,Everett,Portland");
          iq.insert(3, "History", "What is the year the Civil war ended,1865,1920,1843,1390,1200BC,Its still going,2003,2030");
          iq.insert(1, "Comedy", "What object was used by the Greeks to trick their adversaries into letting down their defenses"
              + ",Trojan Horse,Hidey Spidey,Public nudity,Cannabis,Worm hole,Ancient Mermaid energy");
          iq.insert(2, "Comedy", "Dude, Where's My ...?,Car,Rare fedora,Magic Powder,Million dollar scholar,Yoyo championship trophy");
          iq.insert(2, "Which popular handy man spent several years airing on popular kids network the Disney Channel", 
              "Handy Mandy,GaryFixAFlat,Paublo,Sam Builder,Mickey McFixStuff");
          iq.insert(4, "Comedy","Actor famous for playing popular children's entertainment show Barney now holds is employed as...,"
              + "Sex Specialist,Local Politician,FBI plant,Champion Bowler,Charity Worker,Warehouse Manager");
          iq.insert(4, "Comedy","Local crazy person / conspiracy theorist Alex Jones is known for saying which phrase,"
              + "The water is turning the frigging frogs gay,5G is the path to controlling WEAK MINDS,"
              + "Amazon is building robots to replace us GODDAMIT");
          iq.insert(3, "Comedy","What is the name of the Tomato in popular children's show Veggie Tales,"
              + "Bob,Big Red,Rosy O'Donald");
          SelectQuestions sq = new SelectQuestions();
          Question q = new Question();
          System.out.println(q);
          //sq.selectAll();
          //System.out.println();
          //sq.getQuestionsHarderThan(1);
         // System.out.println();
          //sq.getQuestionsOfDifficulty(2);
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

