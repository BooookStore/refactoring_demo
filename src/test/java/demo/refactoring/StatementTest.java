package demo.refactoring;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class StatementTest {

    @Test
    public void printStatementTest() throws Exception {
        // setup
        Map<String, Play> plays = new HashMap<>();
        plays.put("hamlet", new Play("Hamlet", "tragedy"));
        plays.put("as-like", new Play("As You Like It", "comedy"));
        plays.put("othello", new Play("Othello", "tragedy"));

        Invoice invoice = new Invoice("Martin Fowler", Arrays.asList(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40))
        );

        // execute
        String print = Statement.print(invoice, plays);

        // verify
        assertEquals("" +
                "Statement for Martin Fowler\n" +
                "  Hamlet: 650 (55 seats)\n" +
                "  As You Like It: 580 (35 seats)\n" +
                "  Othello: 500 (40 seats)\n" +
                "Amount owed is 1730\n" +
                "You earned 40 credits"
        , print);
    }

}