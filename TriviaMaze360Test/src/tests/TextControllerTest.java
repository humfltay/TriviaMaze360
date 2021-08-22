/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Answer;
import model.TextController;
import model.Question.QuestionNature;

/**
 * @author Cordel
 *
 */
class TextControllerTest {
    private static TextController text1;
    private static TextController text2;
    /**
     * @throws java.lang.Exception
     */
    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        text1 = new TextController(1, 0);
        text2 = new TextController(3, 10);
    }
    /**
     * Test method for {@link model.TextController#setState(model.TextController)}.
     */
    @Test
    void testSetState() {
        text2.right();
        text1.setState(text2);
        assertEquals(text1.getMyScore(), text2.getMyScore(), "setState failed.");
        assertEquals(text1.getMyDifficulty(), text2.getMyDifficulty(), "setState failed.");
        assertEquals(text1.getMyUser(), text2.getMyUser(), "setState failed.");
        assertEquals(text1.getMyMonster(), text2.getMyMonster(), "setState failed");
        assertEquals(text1.getMyDoor(), text2.getMyDoor(), "setState failed.");
        assertEquals(text1.getMyQuestion(), text2.getMyQuestion(), "setState failed.");
        assertEquals(text1.getMyChoices(), text2.getMyChoices(), "setState failed.");
    }

    /**
     * Test method for {@link model.TextController#answerQuestion(model.Answer, java.lang.String)}.
     */
    @Test
    void testAnswerQuestion() {
        assertFalse(text1.answerQuestion(Answer.A, ""), "answerQuestion failed.");
    //    text1.right();
      //  if (text1.getMyQuestion().getMyQuestionNature() == QuestionNature.TRUE) {
       //     assertEquals((Answer.A).equals(text1.getMyQuestion().getMyCorrectAnswer()),
         //           text1.answerQuestion(Answer.A, ""), "answerQuestion failed.");
    }

    /**
     * Test method for {@link model.TextController#askQuestion(model.RealDoor)}.
     */
    @Test
    void testAskQuestion() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.TextController#up()}.
     */
    @Test
    void testUp() {
        assertEquals(text1.askQuestion(text1.getMyDoor()), text1.up(), "up failed.");    }

    /**
     * Test method for {@link model.TextController#down()}.
     */
    @Test
    void testDown() {
        assertEquals(text1.askQuestion(text1.getMyDoor()), text1.down(), "down failed.");    }

    /**
     * Test method for {@link model.TextController#left()}.
     */
    @Test
    void testLeft() {
        assertEquals(text1.askQuestion(text1.getMyDoor()), text1.left(), "left failed.");    }

    /**
     * Test method for {@link model.TextController#right()}.
     */
    @Test
    void testRight() {
        assertEquals(text1.askQuestion(text1.getMyDoor()), text1.right(), "right failed.");
    }

    /**
     * Test method for {@link model.TextController#choiceA()}.
     */
    @Test
    void testChoiceA() {
        assertEquals(text2.answerQuestion(Answer.A, ""), text2.choiceA(), "choiceA failed.");
    }

    /**
     * Test method for {@link model.TextController#choiceB()}.
     */
    @Test
    void testChoiceB() {
        assertEquals(text2.answerQuestion(Answer.B, ""), text2.choiceB(), "choiceB failed.");
    }

    /**
     * Test method for {@link model.TextController#choiceC()}.
     */
    @Test
    void testChoiceC() {
        assertEquals(text2.answerQuestion(Answer.C, ""), text2.choiceC(), "choiceC failed.");
    }

    /**
     * Test method for {@link model.TextController#choiceD()}.
     */
    @Test
    void testChoiceD() {
        assertEquals(text2.answerQuestion(Answer.D, ""), text2.choiceD(), "choiceD failed.");
    }

    /**
     * Test method for {@link model.TextController#setMyScore(int)}.
     */
    @Test
    void testSetMyScore() {
        text2.setMyScore(15);
        assertEquals(15, text2.getMyScore());
    }

    /**
     * Test method for {@link model.TextController#monsterHandler()}.
     */
    @Test
    void testMonsterHandler() {
        //The monster waits twice before moving.
        assertFalse(text2.monsterHandler(), "monsterHandler failed.");
        assertFalse(text2.monsterHandler(), "monsterHandler failed.");
        assertTrue(text2.monsterHandler(), "monsterHandler failed.");
    }

    /**
     * Test method for {@link model.TextController#toString()}.
     */
    @Test
    void testToString() {
        text1.setState(text2);
        assertEquals(text1.toString(), text2.toString(), "toString() failed.");
    }

}
