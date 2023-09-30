package com.rslakra.appsuite.questions.lyft;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 9/27/23 3:02 PM
 */
public class MergeTeamTest {

    /**
     * @return
     */
    @DataProvider
    public Iterator<Object[]> mergeTeamData() {
        List<Object[]> inputs = new ArrayList<>();

        // 1st team
        inputs.add(new Object[]{MergeTeam.Action.INSERT, "a", "b", null});
        inputs.add(new Object[]{MergeTeam.Action.SEARCH, "a", "b", Boolean.TRUE});

        // 2nd team
        inputs.add(new Object[]{MergeTeam.Action.SEARCH, "c", "d", Boolean.FALSE});
        inputs.add(new Object[]{MergeTeam.Action.INSERT, "c", "d", null});
        inputs.add(new Object[]{MergeTeam.Action.SEARCH, "c", "d", Boolean.TRUE});

        // 3rd team
        inputs.add(new Object[]{MergeTeam.Action.INSERT, "a", "e", null});
        inputs.add(new Object[]{MergeTeam.Action.INSERT, "f", "e", null});
        inputs.add(new Object[]{MergeTeam.Action.SEARCH, "b", "e", Boolean.TRUE});
        inputs.add(new Object[]{MergeTeam.Action.SEARCH, "b", "f", Boolean.TRUE});
        inputs.add(new Object[]{MergeTeam.Action.SEARCH, "b", "c", Boolean.FALSE});

        return inputs.iterator();
    }

//    /**
//     * @param player1
//     * @param player2
//     * @param result
//     */
//    @Test(dataProvider = "mergeTeamData")
//    public void testMergeTeam(MergeTeam.Action action, String player1, String player2, Boolean result) {
//        MergeTeam instance = new MergeTeam();
//        if (MergeTeam.Action.INSERT == action) {
//            instance.insertPlayers(player1, player2);
//        }
//
//        if (MergeTeam.Action.SEARCH == action) {
//            assertEquals(result, instance.searchTeam(player1, player2));
//        }
//    }

    @Test
    public void testMergeTeam() {
        MergeTeam instance = new MergeTeam();

        // 1st player
        String player1 = "a";
        String player2 = "b";
        instance.insertPlayers(player1, player2);
        assertEquals(true, instance.searchTeam(player1, player2));

        // 2nd team
        player1 = "c";
        player2 = "d";
        assertEquals(false, instance.searchTeam(player1, player2));
        instance.insertPlayers(player1, player2);
        assertEquals(true, instance.searchTeam(player1, player2));

        // 3rd team
        instance.insertPlayers("e", "f");
        instance.insertPlayers("b", "f");
//        instance.insertPlayers("f", "e");
        assertEquals(true, instance.searchTeam("b", "e"));
        assertEquals(true, instance.searchTeam("b", "f"));
        assertEquals(false, instance.searchTeam("b", "c"));
    }
}
