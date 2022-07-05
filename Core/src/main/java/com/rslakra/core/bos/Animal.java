package com.rslakra.core.bos;

/**
 * An animal, i.e. a mostly multicellular, eukaryotic organism from the biological kingdom Animalia.
 * <p>
 * More general types Thing Entity BiologicalOrganism
 * <p>
 * Disjoint types Plant
 *
 * @author Rohtash Lakra (rlakra)
 * @created 1/27/22 11:01 AM
 */
public class Animal {

    //    The credit property connects individuals involved with the production of a media work to the reified Crew class.
//    ActingRole or CompanyRole
    private ActingRole credit;

    //    Amount an animal sleeps in a 24 hours period.
    private String dailySleep;

    //    The time interval of a pregnancy is called the gestation period. Average time of an animal's gestation.
//    Literal
    private GestationPeriod gestationPeriod;

    //    The speed at which an animal travels.
//        Literal
    private AnimalSpeed speed;


}
