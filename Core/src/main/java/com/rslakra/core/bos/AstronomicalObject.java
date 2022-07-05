package com.rslakra.core.bos;

import java.math.BigDecimal;
import java.util.Date;

/**
 * An astronomical object in outer space. E.g., planetary systems, star clusters, nebulae, galaxies, asteroids, moons,
 * planets, stars.
 * <p>
 * More general types Thing Entity
 *
 * @author Rohtash Lakra (rlakra)
 * @created 1/27/22 6:00 PM
 */
public class AstronomicalObject {

    //    For stars, absolute magnitude measures its brightness as seen by an observer at 10 parsecs (pc) from the object, assuming no astronomical extinction of starlight. For planets, the brightness is measured assuming both the Sun and the observer are 1 astronomical unit (AU) away.
    private BigDecimal absoluteMagnitude;
    //    An astronomical object's brightness as seen by an observer on Earth, adjusted to the value it would have in the absence of the atmosphere.
    private BigDecimal apparentMagnitude;
    //    The time elapsed since the birth of an astronomical object.
    private BigDecimal astronomicalAge;
    //    Which partition of the celestial sphere where this astronimical object is in. The partitions (constellations) are defined by IAU.
//    Literal	At most one
    private Constellation constellation;
    //    Connects a person to the entity or entities he or she discovered.
//    Person
    private Person discoveredBy;
    //    The date that an object is discovered.
    private Date discoveryDate;
    //    The distance between the object and earth (for stars not in The Solar System).
    private BigDecimal distanceToEarth;
    //    The mean distance from this object (planet/satellite) to its host stars/planets. This is the length of the semi-major axis of the elliptical orbital trajectory.
    private BigDecimal distanceToSun;
    //    The mass of an object.
    private BigDecimal mass;
    //    The next time when this astronomical object comes closest to the sun.
    private Date nextPerihelion;
    //    The number of satellites orbiting this planet.
    private Integer numSatellites;
    //    The time for this object (planet) to make one complete orbit around its sun.
    private BigDecimal orbitalPeriod;
    //    Connects an astronomical object to the object or objects that orbit it.
//    AstronomicalObject
    private AstronomicalObject orbitedBy;
    //    Connects an astronomical object or objects to the astronomical object it or they orbit.
//    AstronomicalObject
    private AstronomicalObject orbits;
    //    The mean distance from this object (planet/satellite) to its host stars/planets. This is the length of the semi-major axis of the elliptical orbital trajectory.
    private BigDecimal radius;
    //    The time for this object (planet) to make a single rotation with respect to its vernal equinox.
    private BigDecimal siderealDay;
    //    The gravitational acceleration experienced at the object's surface.
    private BigDecimal surfaceGravity;
    //    The temperature of an object. For planets, it is the surface temperature. For stars, it is the effective temperature.
    private BigDecimal temperature;
}
