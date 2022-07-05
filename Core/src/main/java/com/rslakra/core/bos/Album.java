package com.rslakra.core.bos;

/**
 * A music album is a collection of related audio or music tracks distributed to the public. Attributes within this
 * class refer to the original recording. Other versions can be captured as music releases.
 * <p>
 * More general types MediaWork Thing Entity CreativeWork
 * <p>
 * Disjoint types BookSeries Composition Movie AlbumTrack Lyrics CompositionPart Book Poem MovieSeries VideoGameSeries
 * Song VideoGame Software TvEpisode TvSeries TvSpecial
 *
 * @author Rohtash Lakra (rlakra)
 * @created 1/27/22 10:49 AM
 */
public class Album {

    //    The albumTrack property connects an album release with the tracks that make up its constituent parts.
    private AlbumTrack albumTrack;

    //    Describes the recording's type. E.g., STUDIO, CONCERT.
//    Literal
    private AlbumType albumType;

    //    Connects a musical work to a particular performance.
//    MusicRole
    private MusicRole musicPerformance;

    //    Gives the first product form of a music album - single, album, EP, etc.
//    Literal	At most one
    private Object originalMusicProductForm;

    //    The number of tracks on the canonical version of the album.
    private Integer originalNumberOfTrack;

    //    The label property connects a music album with the record label that released the album.
//        RecordLabel
    private RecordLabel recordLabel;

    //    Location where an album or track or song was originally recorded. Should conform to ISO codes.
//        Place
    private Place recordingLocation;

    //    The number of times a particular piece of media has been released in its lifetime.
    private Integer releaseTotal;

    //    The sound recording copyright notice is a copyright for just the sound in the original release itself, and will not apply to any other rendition or version, even if performed by the same artist(s).
//    Literal
    private Object soundRecordingCopyright;

    //    connects a movie to its soundtrack album
//        Movie
    private Movie soundtrackOf;


}
