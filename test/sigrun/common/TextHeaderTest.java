package sigrun.common;

import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static org.junit.Assert.*;

/**
 * User: Mikhail Aksenov
 * github.com/mikhail-aksenov
 * mikhail.aksenov@halliburton.com
 * Date: 3/1/12
 * Time: 5:27 PM
 */
public class TextHeaderTest {
    @Test
    public void testAddPunchCard() throws Exception {
        final TextHeader textHeader = new TextHeader();
        for (int i = 0; i < TextHeader.TEXT_HEADER_LENGTH; i++) {
            textHeader.addPunchCard(new PunchCard(PunchCardTest.ENCODED_STRING_1.getBytes("Cp1252")));
            assertEquals(textHeader.getCard(i).toString(), PunchCardTest.DECODED_STRING_1);
        }

        assertEquals(TextHeader.TEXT_HEADER_LENGTH, textHeader.getSize().intValue());
    }

    @Test
    public void testAddPunchCardEx() throws UnsupportedEncodingException {
        final TextHeader textHeader = new TextHeader();

        try {
            for (int i = 0; i < TextHeader.TEXT_HEADER_LENGTH; i++) {
                textHeader.addPunchCard(new PunchCard(PunchCardTest.ENCODED_STRING_1.getBytes("Cp1252")));
            }

            textHeader.addPunchCard(new PunchCard(PunchCardTest.ENCODED_STRING_1.getBytes("Cp1252")));
            fail("ArrayIndexOutOfBoundsException should be thrown");
        } catch (ArrayIndexOutOfBoundsException ex) {
            assertTrue(true);
        }
    }

    @Test
    public void testGetCard() throws Exception {
        final TextHeader textHeader = new TextHeader();
        for (int i = 0; i < TextHeader.TEXT_HEADER_LENGTH; i++) {
            textHeader.addPunchCard(new PunchCard(PunchCardTest.ENCODED_STRING_1.getBytes("Cp1252")));
        }

        try {
            textHeader.getCard(TextHeader.TEXT_HEADER_LENGTH);
            fail("IndexOutOfBoundsException should be thrown");
        } catch (IndexOutOfBoundsException ex) {
        }
    }

}