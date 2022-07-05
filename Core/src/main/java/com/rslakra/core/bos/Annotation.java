package com.rslakra.core.bos;

/**
 * Describes a stand-off annotation associated with an entity or set of entities.
 * <p>
 * More general types Intangible Thing
 * <p>
 * More specific types VideoAnnotation
 *
 * @author Rohtash Lakra (rlakra)
 * @created 1/27/22 5:52 PM
 */
public class Annotation {

    // Connects a stand-off annotation to the annotated creative work.
    // CreativeWork
    private CreativeWork annotationOf;

    //    Connects the creative work, event or annotation to its main topic(s). E.g., a travel guide about New York City to New York City or a video annotation about LeBron James and Anthony Davis to both of them.
//    Entity or Concept
    private Entity mainTopic;

}
