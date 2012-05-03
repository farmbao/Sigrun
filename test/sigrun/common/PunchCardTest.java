package sigrun.common;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import static java.lang.System.out;
import static org.junit.Assert.assertEquals;

/**
 * User: Mikhail Aksenov
 * github.com/mikhail-aksenov
 * mikhail.aksenov@halliburton.com
 * Date: 3/1/12
 * Time: 5:12 PM
 */
public class PunchCardTest {
    public static final String ENCODED_STRING_1 =
            "Ãðñ@ÃÓÉÅÕã@ÕÁÔÅ@@@@@@@@@@@@@@@@@@zÇÁé×ÙÖÔÕÅÆã`ÕÖèÁÂÙâÒÕÅÆãÅÇÁé@@@@@@@@@@@@@@@@@@";
    public static final String DECODED_STRING_1 =
            "C01 CLIENT NAME                  :GAZPROMNEFT-NOYABRSKNEFTEGAZ                  ";

    private static final int PUNCH_CARD_LENGTH = 80;
    private static final int TEXT_HEADER_LENGTH = 3200;

    public static void main(String[] argv) {
        String filePath = argv[0];
        byte[] textHeader = new byte[TEXT_HEADER_LENGTH];

        try {
            FileInputStream in = new FileInputStream(filePath);
            in.read(textHeader, 0, TEXT_HEADER_LENGTH);
            for (int i = 0; i < TEXT_HEADER_LENGTH / PUNCH_CARD_LENGTH; i++) {
                byte[] buf = Arrays.copyOfRange(textHeader, i * PUNCH_CARD_LENGTH, (i + 1) * PUNCH_CARD_LENGTH);
                PunchCard p = new PunchCard(buf);
                out.printf("%s -> ", new String(buf, 0, buf.length, "Cp1252"));
                out.printf("%s%s\n", p.getCardNumber(), p.getCardContent());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetCardNumber() throws Exception {
        PunchCard card = new PunchCard(ENCODED_STRING_1.getBytes("Cp1252"));
        final String cardHeader = "C01";
        assertEquals(cardHeader, card.getCardNumber());
    }

    @Test
    public void testGetCardContent() throws Exception {
        PunchCard card = new PunchCard(ENCODED_STRING_1.getBytes("Cp1252"));
        final String cardContent = " CLIENT NAME                  :GAZPROMNEFT-NOYABRSKNEFTEGAZ                  ";
        assertEquals(cardContent, card.getCardContent());
    }

    @Test
    public void testToString() throws Exception {
        PunchCard card = new PunchCard(ENCODED_STRING_1.getBytes("Cp1252"));
        assertEquals(DECODED_STRING_1, card.toString());
    }
}
