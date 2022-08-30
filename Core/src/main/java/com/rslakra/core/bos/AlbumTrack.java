package com.rslakra.core.bos;

/**
 * Describes an album track, i.e. the specific version of an audio performance that appears on an album.
 * <p>
 * More general types Thing Entity MediaRelease
 * <p>
 * Disjoint types BookSeries Composition Movie Lyrics CompositionPart Book MovieSeries Song VideoGame Album Software
 * TvEpisode TvSeries TvSpecial
 *
 * @author Rohtash Lakra
 * @created 1/27/22 10:50 AM
 */
public class AlbumTrack {

    // The albumTrackOf property connects a song (musical track) with the album release on which it appears.
// Album or MusicRelease
    private Object albumTrackOf;

    // Describes the recording's type. E.g., STUDIO, CONCERT.
    private AlbumType albumType;

    // Indicates the disc on which a media product component appears. Use as an interpretation of the disc order in cases where non-integer "numbers" appear (e.g. disc a = disc 1)
    private Integer discNumber;

    // The International Standard Recording Code (ISRC) for the recording. ISRC is an international standard code for uniquely identifying sound recordings and music video recordings.
    private String isrcCode;

    // Attaches the lyrics class to its song or performance.
// Lyrics
    private Lyrics lyrics;

    // Connects a musical work to a particular performance.
// MusicRole
    private MusicRole musicPerformance;

    // Parental advisory warning detailing thematic elements present in an audio media work.
// Literal
    private Object parentalAdvisory;

    // The track number of a song in physical media. For instance, a song might be #16 total, but #4 on Disc 2.
    private Object physicalTrackNumber;

    // Location where an album or track or song was originally recorded. Should conform to ISO codes.
// Place
    private Object recordingLocation;

    // Connects a song (as creative work) to a track on an album.
// Song or CompositionPart
    private Object songTrackOf;

    // The sound recording copyright notice is a copyright for just the sound in the original release itself, and will not apply to any other rendition or version, even if performed by the same artist(s).
// Literal
    private Object soundRecordingCopyright;

    // The number of the audio track, which reflects the order of the tracks on the disc.
    private Integer trackNumber;

    // Connects a composition performance to the album track it is on.
// CompositionPerformance
    private CompositionPerformance trackPerformance;

}
