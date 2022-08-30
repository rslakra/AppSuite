package com.rslakra.core.bos;

/**
 * An individual who competes in amateur or professional sport. E.g., "Lebron James", "Usain Bolt", "Serena Williams",
 * etc.
 * <p>
 * More general types Person Thing Entity
 *
 * @author Rohtash Lakra
 * @created 1/27/22 6:06 PM
 */
public class Athlete {

    // Connects an Athlete to its sports competition instances via a AthleteCompetitionInstanceRole reified class.
    private AthleteCompetitionInstanceRole athleteCompetitionInstanceRole;

    // Connects an athlete to sports game through a reified AthleteGameRole class.
    private AthleteGameRole athleteGameRole;

    // Connects a game to the athletes that play in it.
    private SportGame competitorsOf;

    // Connects a draft with the athletes who participated in it.
    private DraftPick draftPick;

    // Indicates whether an athlete or other person is currently injured.
    private boolean injuryFlag;

    // Text describing an injury currently affecting the athlete.
// Literal
    private String injuryReport;

    // Designates whether an athlete is right or left legged.
    private LegType legged;

    // Connects a person to the other person they feel is a personal or professional rival
    private Athlete rival;

    // A person who is the focus of another person's professional or personal competitiveness.
    private Athlete rivalOf;

    // Connects a entity (sports team, athlete, etc) to the sport competition instances it competes in, via a SportCompetitionInstanceRole reified relationship.
    private SportCompetitionInstanceRole sportCompetitionInstanceRole;

    // Connects a participant to a sports competition through a reified SportCompetitionRole class.
    private SportCompetitionRole sportCompetitionRole;

    // Connects an athlete or sport competition instance to its sport disciplines.
    private SportDiscipline sportDisciplinePlayed;

    // Connects an athlete, team, or division with the sport(s) s/he plays.
    private Sport sportPlayed;

    // Connects a sport person, group or role its relevant sport statistics.
    private SportStat sportStat;

    // Connects an athlete to her sports team via a TeamAffiliation relationship.
    private TeamAffiliation teamAffiliation;

    // Connects a captain to her team via a TeamCaptainRole relationship.
    private TeamCaptainRole teamCaptainRole;


}
