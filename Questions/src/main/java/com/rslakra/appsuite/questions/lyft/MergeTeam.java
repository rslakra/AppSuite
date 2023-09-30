package com.rslakra.appsuite.questions.lyft;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Rohtash Lakra
 * @created 9/27/23 3:01 PM
 */
public class MergeTeam {

    private static final Logger LOGGER = LoggerFactory.getLogger(MergeTeam.class);

    static enum Action {
        INSERT, SEARCH;
    }

    private List<Set<String>> teams = new ArrayList<>();
    private Map<String, Integer> playerIndex = new HashMap<>();

    private Integer getTeamIndex(String name) {
        LOGGER.debug("+getTeamIndex({})", name);
        Integer index = playerIndex.get(name);
        LOGGER.debug("-getTeamIndex(), index:{}", index);
        return index;
    }

    public void insertPlayers(String player1, String player2) {
        LOGGER.debug("+insertPlayers({}, {})", player1, player2);
        Integer firstIndex = getTeamIndex(player1);
        Integer secondIndex = getTeamIndex(player2);
        LOGGER.debug("firstIndex:{}, secondIndex:{}", firstIndex, secondIndex);

        if (firstIndex != null && secondIndex != null && firstIndex != secondIndex) {
            mergeTeams(player1, player2);
        } else if (firstIndex != null && secondIndex == null) {
            teams.get(firstIndex).add(player2);
            playerIndex.put(player2, firstIndex);
        } else if (firstIndex == null && secondIndex != null) {
            teams.get(secondIndex).add(player1);
            playerIndex.put(player1, secondIndex);
        } else if (firstIndex == null && secondIndex == null) {
            Set<String> team = new HashSet<>();
            team.add(player1);
            team.add(player2);
            int count = teams.size();
            playerIndex.put(player1, count);
            playerIndex.put(player2, count);
            teams.add(team);
        }

        LOGGER.debug("-insertPlayers(), teams:{}, playerIndex:{}", teams, playerIndex);
    }

    private void mergeTeams(String player1, String player2) {
        LOGGER.debug("+mergeTeams({}, {})", player1, player2);
        Integer firstIndex = getTeamIndex(player1);
        Integer secondIndex = getTeamIndex(player2);
        LOGGER.debug("firstIndex:{}, secondIndex:{}", firstIndex, secondIndex);
        Set<String> team1 = teams.get(firstIndex);
        LOGGER.debug("team1:{}", team1);
        for (String s : team1) {
            teams.get(secondIndex).add(s);
            playerIndex.put(s, secondIndex);
        }
        teams.add(firstIndex, new HashSet<>());
        LOGGER.debug("-mergeTeams(), teams:{}, playerIndex:{}", teams, playerIndex);
    }

    public boolean searchTeam(String player1, String player2) {
        LOGGER.debug("+searchTeam({}, {})", player1, player2);
        Integer ind1 = getTeamIndex(player1);
        Integer ind2 = getTeamIndex(player2);
        LOGGER.debug("ind1:{}, ind2:{}", ind1, ind2);
        return (ind1 != null && ind1.equals(ind2));
    }

}
