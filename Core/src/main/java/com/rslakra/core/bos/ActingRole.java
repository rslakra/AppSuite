package com.rslakra.core.bos;

/**
 * Reified relationship that connects an actor with the work in which he performs.
 *
 * @author Rohtash Lakra
 * @created 1/26/22 5:00 PM
 */
public class ActingRole extends CrewRole {

    // Connects an Actor to the reified Performance class.
    private ActingPerformance actingPerformanceIn;

    // Describes the credit type. E.g., CAMEO, VOICE, FEATURED, GUEST STAR.
    private ActorType actorType;

    // The name of a fictional character.
    private CharacterType characterName;

    // Connects an actor's performance with the fictional charactors he or she portrayed.
    private FictionalCharacter characterPortrayed;

    // The creditFor property connects the reified Crew class to the individuals involved with the production of a media work.
    private Crew creditFor;

    // The number of episodes in a television season
    private Integer numberEpisodes;

}
