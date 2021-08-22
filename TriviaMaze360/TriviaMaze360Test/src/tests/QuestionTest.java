/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Question;

/**
 * @author 
 */
class QuestionTest {
    private static Question questionDifficulty;
    private static Question questionRandom;
    private static Question questionOfType;
    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        questionRandom = new Question();
        questionDifficulty = new Question(2);
        questionOfType = new Question("Gaming");
    }

    /**
     * Test method for {@link model.Question#toString()}.
     */
    @Test
    void testToString() {
        assertNotNull(questionRandom.toString(), "Checking if toString returns correctly");
    }

    /**
     * Test method for {@link model.Question#getMyWrongAnswers()}.
     */
    @Test
    void testGetMyWrongAnswers() {
        String[] wrongans = questionRandom.getMyWrongAnswers();
        assertNotNull(wrongans, "Making sure wrong answers is not null");
        for (int i = 0; i <= 10; i++) {
            questionRandom = new Question();
            wrongans = questionRandom.getMyWrongAnswers();
            assertTrue(wrongans.length > 1, "Should always have more than one wrong answer");
        }
        
    }

    /**
     * Test method for {@link model.Question#getMyCorrectAnswer()}.
     */
    @Test
    void testGetMyCorrectAnswer() {
        String rightAns = questionRandom.getMyCorrectAnswer();
        assertNotNull(rightAns, "Making sure correct answers is not null");
        for (int i = 0; i <= 10; i++) {
            questionRandom = new Question();
            rightAns = questionRandom.getMyCorrectAnswer();
            assertTrue(!rightAns.isBlank(), "Should always not be blank");
        }
        
    }

}
