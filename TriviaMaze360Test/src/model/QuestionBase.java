package model;
import java.sql.*;

import model.Question;

/**
 * QuestionBase acts as a local database using SQLite in order to hold questions for a Trivia Maze
 * 
 * @author Taylor Humfleet UWID: humfltay
 *
 */
public class QuestionBase {

    
    private static void main(String args[]) {
        createTable();
        System.out.println("made it here");
        InsertQuestion iq = new InsertQuestion();
        //iq.insert(1, "Comics","Which city exists in the popular series Batman,Gotham,Bangladale,BravelDavel,Wildamite,Duberville,Lorry,Bat City");
        //iq.insert(1, "Comics","What is supermans biggest weakness,Kryptonite,Amusement park lines,Vehicles,The Duchess of London,Dan Dan slayer of man,Gustavo");
        //iq.insert(1, "Comics","Which hero is known for his small stature,Ant Man,Bugs Bunny,Kung Fu Panda,Superman,Charles Barkley,Santa Claus");
        
        //iq.insert(2, "Gaming","What is the greatest selling game console of all time,PlayStation 2,Xbox 360,The friends we made along the way,Sega Dreamcast,Nintendo WII,Gameboy Advance");
        //iq.insert(2, "Gaming","Which FPS title is known for it's simple yet addictive gameplay,Golden Eye,Tim Duncan Hunting,GTA V,Martha Stewart Online,Paper Mario,Project Storm");
        //iq.insert(2, "Gaming","Which game holds the title of being incredibly difficult for first time players,Dark Souls,Halo 4,Ricks Dodgeball Stadium,Mario Party 8,Pokemon Red,Fusion Frenzy");
        
        //iq.insert(3, "Geography", "What is the capitol of Washington,Olympia,Tacoma,Westchester,Miami,Seattle,Everett,Portland");
        //iq.insert(3, "Geography", "Washington is the … state,42nd,1st,47th,39th,46th,51st,Not a real state");
        //iq.insert(3, "Geography", "What is the country directly to the north of the United States,Canada,South Vietnam,North United States,North America,North North America,Narnia,North Korea,Oregon,The Moon,Saturn");
          SelectQuestions sq = new SelectQuestions();
          //Question q = new Question();
          //System.out.println(q);
          //He set it up to ask by type.
          //Question query = new Question("Comedy");
          //System.out.println(query);
          //sq.selectAll();
          //System.out.println();
          //sq.getQuestionsHarderThan(1);
          // System.out.println();
          //sq.getQuestionsOfDifficulty(2);
          ///Maze m = null;
          // mazeGen = new MazeGenerator();
          //m = mazeGen.getMaze();
          //System.out.println(m);
       
    }
    private static void createTable() {
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

