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
    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        questionRandom = new Question();
        questionDifficulty = new Question(2);
    }

    /**
     * Test method for {@link model.Question#toString()}.
     */
    @Test
    void testToString() {
        //fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.Question#getMyWrongAnswers()}.
     */
    @Test
    void testGetMyWrongAnswers() {
        //assertEquals()
    }

    /**
     * Test method for {@link model.Question#getMyCorrectAnswer()}.
     */
    @Test
    void testGetMyCorrectAnswer() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.Question#getMyQuestionNature()}.
     */
    @Test
    void testGetMyQuestionNature() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.Question#getMyQuestion()}.
     */
    @Test
    void testGetMyQuestion() {
        fail("Not yet implemented");
    }

}
