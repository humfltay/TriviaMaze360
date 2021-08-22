/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.SelectQuestions;

/**
 * @author 
 *
 */
class SelectQuestionsTest {
    SelectQuestions sq;
    /**
     * @throws java.lang.Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        sq = new SelectQuestions();
    }

    /**
     * Test method for {@link model.SelectQuestions#getRandomQuestion()}.
     */
    @Test
    void testGetRandomQuestion() {
        
        for (int i = 1; i <= 100; i++) {
            List<String> question = sq.getRandomQuestion();
            assertNotNull(question, "Checking to see if database will return a null question");
        }
    }

    /**
     * Test method for {@link model.SelectQuestions#getQuestionsHarderThan(int)}.
     */
    @Test
    void testGetQuestionsHarderThan() {
        for (int i = 1; i <= 10; i++) {
            List<String> question = sq.getQuestionsHarderThan(0);
            assertNotNull(question, "Checking to see if database will return a null question");
        }
        for (int i = 1; i <= 10; i++) {
            List<String> question = sq.getQuestionsHarderThan(1);
            assertNotNull(question, "Checking to see if database will return a null question");
        }
        for (int i = 1; i <= 10; i++) {
            List<String> question = sq.getQuestionsHarderThan(2);
            assertNotNull(question, "Checking to see if database will return a null question");
        }
    }

    /**
     * Test method for {@link model.SelectQuestions#getQuestionOfDifficulty(int)}.
     */
    @Test
    void testGetQuestionOfDifficulty() {
        for (int i = 1; i <= 10; i++) {
            List<String> question = sq.getQuestionOfDifficulty(1);
            assertNotNull(question, "Checking to see if database will return a null question");
        }
        for (int i = 1; i <= 10; i++) {
            List<String> question = sq.getQuestionOfDifficulty(2);
            assertNotNull(question, "Checking to see if database will return a null question");
        }
        for (int i = 1; i <= 10; i++) {
            List<String> question = sq.getQuestionOfDifficulty(3);
            assertNotNull(question, "Checking to see if database will return a null question");
        }
    }

    /**
     * Test method for {@link model.SelectQuestions#getQuestionOfType(java.lang.String)}.
     */
    @Test
    void testGetQuestionOfType() {
        for (int i = 1; i <= 10; i++) {
            List<String> question = sq.getQuestionOfType("Gaming");
            assertNotNull(question, "Checking to see if database will return a null question");
        }
        for (int i = 1; i <= 10; i++) {
            List<String> question = sq.getQuestionOfType("Comics");
            assertNotNull(question, "Checking to see if database will return a null question");
        }
    }

}
