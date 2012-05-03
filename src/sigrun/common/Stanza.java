package sigrun.common;

/**
 * User: Mikhail Aksenov
 * github.com/mikhail-aksenov
 * mikhail.aksenov@halliburton.com
 * Date: 3/27/12
 * Time: 10:32 AM
 */
public class Stanza {
    public static final Stanza END_STANZA = new Stanza("((SEG: EndText))");

    protected final String stanzaHeader;

    public Stanza(String stanzaHeader) {
        this.stanzaHeader = stanzaHeader;
    }
}