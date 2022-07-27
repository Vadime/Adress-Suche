package de.novi;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RegexTest {

    @Test
    public void testRegex() {
        String sprache = "a|b|abba";
        String[] wörter = {"abba", "a", "b"};
        String[] wörter2 = {"ab", "c"};

        for(String wort : wörter) {
            assertTrue("\"" + wort + "\" liegt nicht in der Sprache.", wort.matches(sprache));
        }

        for(String wort : wörter2) {
            assertTrue("\"" + wort + "\" liegt in der Sprache.", !wort.matches(sprache));
        }

    }
}
