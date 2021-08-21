/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.TextController;

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
        fail("Not yet implemented");
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
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.TextController#down()}.
     */
    @Test
    void testDown() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.TextController#left()}.
     */
    @Test
    void testLeft() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.TextController#right()}.
     */
    @Test
    void testRight() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.TextController#choiceA()}.
     */
    @Test
    void testChoiceA() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.TextController#choiceB()}.
     */
    @Test
    void testChoiceB() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.TextController#choiceC()}.
     */
    @Test
    void testChoiceC() {
        fail("Not yet implemented");
    }

    /**
     * Test method for {@link model.TextController#choiceD()}.
     */
    @Test
    void testChoiceD() {
        fail("Not yet implemented");
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
