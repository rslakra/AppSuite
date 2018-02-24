/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 * 
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 * 	  notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *      
 * Devamatre reserves the right to modify the technical specifications and or 
 * features without any prior notice.
 *****************************************************************************/
package com.rslakra.java.enums;

/**
 * The <code>Genre<code> enumeration contains the common genre types.
 * <code>
 * 0 - Blues
 * 1 - Classic Rock
 * 2 - Country
 * 3 - Dance
 * 4 - Disco
 * 5 - Funk
 * 6 - Grunge
 * 7 - Hip-Hop
 * 8 - Jazz
 * 9 - Metal
 * 10 - New Age
 * 11 - Oldies
 * 12 - Other
 * 13 - Pop
 * 14 - R&B
 * 15 - Rap
 * 16 - Reggae
 * 17 - Rock
 * 18 - Techno
 * 19 - Industrial
 * 20 - Alternative
 * 21 - Ska
 * 22 - Death Metal
 * 23 - Pranks
 * 24 - Soundtrack
 * 25 - Euro-Techno
 * 26 - Ambient
 * 27 - Trip-Hop
 * 28 - Vocal
 * 29 - Jazz+Funk
 * 30 - Fusion
 * 31 - Trance
 * 32 - Classical
 * 33 - Instrumental
 * 34 - Acid
 * 35 - House
 * 36 - Game
 * 37 - Sound Clip
 * 38 - Gospel
 * 39 - Noise
 * 40 - Alternative Rock
 * 41 - Bass
 * 43 - Punk
 * 44 - Space
 * 45 - Meditative
 * 46 - Instrumental Pop
 * 47 - Instrumental Rock
 * 48 - Ethnic
 * 49 - Gothic
 * 50 - Darkwave
 * 51 - Techno-Industrial
 * 52 - Electronic
 * 53 - Pop-Folk
 * 54 - Eurodance
 * 55 - Dream
 * 56 - Southern Rock
 * 57 - Comedy
 * 58 - Cult
 * 59 - Gangsta
 * 60 - Top 40
 * 61 - Christian Rap
 * 62 - Pop/Funk
 * 63 - Jungle
 * 64 - Native US
 * 65 - Cabaret
 * 66 - New Wave
 * 67 - Psychedelic
 * 68 - Rave
 * 69 - Showtunes
 * 70 - Trailer
 * 71 - Lo-Fi
 * 72 - Tribal
 * 73 - Acid Punk
 * 74 - Acid Jazz
 * 75 - Polka
 * 76 - Retro
 * 77 - Musical
 * 78 - Rock & Roll
 * 79 - Hard Rock
 * 80 - Folk
 * 81 - Folk-Rock
 * 82 - National Folk
 * 83 - Swing
 * 84 - Fast Fusion
 * 85 - Bebob
 * 86 - Latin
 * 87 - Revival
 * 88 - Celtic
 * 89 - Bluegrass
 * 90 - Avantgarde
 * 91 - Gothic Rock
 * 92 - Progressive Rock
 * 93 - Psychedelic Rock
 * 94 - Symphonic Rock
 * 95 - Slow Rock
 * 96 - Big Band
 * 97 - Chorus
 * 98 - Easy Listening
 * 99 - Acoustic  100 - Humour
 * 101 - Speech
 * 102 - Chanson
 * 103 - Opera
 * 104 - Chamber Music
 * 105 - Sonata
 * 106 - Symphony
 * 107 - Booty Bass
 * 108 - Primus
 * 109 - Porn Groove
 * 110 - Satire
 * 111 - Slow Jam
 * 112 - Club
 * 113 - Tango
 * 114 - Samba
 * 115 - Folklore
 * 116 - Ballad
 * 117 - Power Ballad
 * 118 - Rhythmic Soul
 * 119 - Freestyle
 * 120 - Duet
 * 121 - Punk Rock
 * 122 - Drum Solo
 * 123 - A Cappella
 * 124 - Euro-House
 * 125 - Dance Hall
 * 126 - Goa
 * 127 - Drum & Bass
 * 128 - Club-House
 * 129 - Hardcore
 * 130 - Terror
 * 131 - Indie
 * 132 - BritPop
 * 133 - Negerpunk
 * 134 - Polsk Punk
 * 135 - Beat
 * 136 - Christian Gangsta
 * 137 - Heavy Metal
 * 138 - Black Metal
 * 139 - Crossover
 * 140 - Contemporary Christian
 * 141 - Christian Rock
 * 142 - Merengue
 * 143 - Salsa
 * 144 - Thrash Metal
 * 145 - Anime
 * 146 - JPop
 * 147 - SynthPop
 * </code> These genres can be modified in future.
 * 
 * @author Rohtash Singh (rosingh@devamatre.com)
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Genre {

	/** Default Genres */
	/* Blues */
	BLUES("Blues"),
	/* Classic Rock */
	CLASSIC_ROCK("Classic Rock"),
	/* Country */
	COUNTRY("Country"),
	/* Dance */
	DANCE("Dance"),
	/* Disco */
	DISCO("Disco"),
	/* Funk */
	FUNK("Funk"),
	/* Grunge */
	GRUNGE("Grunge"),
	/* Hip-Hop */
	HIP_HOP("Hip-Hop"),
	/* Jazz */
	JAZZ("Jazz"),
	/* Metal */
	METAL("Metal"),
	/* New Age */
	NEW_AGE("New Age"),
	/* Oldies */
	OLDIES("Oldies"),
	/* Other */
	OTHER("Other"),
	/* Pop */
	POP("Pop"),
	/* R&B */
	R_B("R&B"),
	/* Rap */
	RAP("Rap"),
	/* Reggae */
	REGGAE("Reggae"),
	/* Techno */
	TECHNO("Techno"),
	/* Industrial */
	INDUSTRIAL("Industrial"),
	/* Alternative */
	ALTERNATIVE("Alternative"),
	/* Ska */
	SKA("Ska"),
	/* Death Metal */
	DEATH_METAL("Death Metal"),
	/* Pranks */
	PRANKS("Pranks"),
	/* Soundtrack */
	SOUNDTRACK("Soundtrack"),
	/* Euro-Techno */
	EURO_TECHNO("Euro-Techno"),
	/* Ambient */
	AMBIENT("Ambient"),
	/* Trip-Hop */
	TRIP_HOP("Trip-Hop"),
	/* Vocal */
	VOCAL("Vocal"),
	/* Jazz+Funk */
	JAZZ_FUNK("Jazz+Funk"),
	/* Fusion */
	FUSION("Fusion"),
	/* Trance */
	TRANCE("Trance"),
	/* Classical */
	CLASSICAL("Classical"),
	/* Instrumental */
	INSTRUMENTAL("Instrumental"),
	/* Acid */
	ACID("Acid"),
	/* House */
	HOUSE("House"),
	/* Game */
	GAME("Game"),
	/* Sound Clip */
	SOUND_CLIP("Sound Clip"),
	/* Gospel */
	GOSPEL("Gospel"),
	/* Noise */
	NOISE("Noise"),
	/* Alternative Rock */
	ALTERNATIVE_ROCK("Alternative Rock"),
	/* Bass */
	BASS("Bass"),
	/* Punk */
	PUNK("Punk"),
	/* Space */
	SPACE("Space"),
	/* Meditative */
	MEDITATIVE("Meditative"),
	/* Instrumental Pop */
	INSTRUMENTAL_POP("Instrumental Pop"),
	/* Instrumental Rock */
	INSTRUMENTAL_ROCK("Instrumental Rock"),
	/* Ethnic */
	ETHNIC("Ethnic"),
	/* Gothic */
	GOTHIC("Gothic"),
	/* Darkwave */
	DARKWAVE("Darkwave"),
	/* Techno-Industrial */
	TECHNO_INDUSTRIAL("Techno-Industrial"),
	/* Electronic */
	ELECTRONIC("Electronic"),
	/* Pop-Folk */
	POP_FOLK("Pop-Folk"),
	/* Eurodance */
	EURODANCE("Eurodance"),
	/* Dream */
	DREAM("Dream"),
	/* Southern Rock */
	SOUTHERN_ROCK("Southern Rock"),
	/* Comedy */
	COMEDY("Comedy"),
	/* Cult */
	CULT("Cult"),
	/* Gangsta */
	GANSTA("Gangsta"),
	/* Top 40 */
	TOP40("Top 40"),
	/* Christian Rap */
	CHRISTIAN_RAP("Christian Rap"),
	/* Pop/Funk */
	POP_FUNK("Pop/Funk"),
	/* Jungle */
	JUNGLE("Jungle"),
	/* Native US */
	NATIVE_US("Native US"),
	/* Cabaret */
	CABARET("Cabaret"),
	/* New Wave */
	NEW_WAVE("New Wave"),
	/* Psychedelic */
	PSYCHEDELIC("Psychedelic"),
	/* Rave */
	RAVE("Rave"),
	/* Showtunes */
	SHOWTUNES("Showtunes"),
	/* Trailer */
	TRAILER("Trailer"),
	/* Lo-Fi */
	LO_FI("Lo-Fi"),
	/* Tribal */
	TRIBAL("Tribal"),
	/* Acid Punk */
	ACID_PUNK("Acid Punk"),
	/* Acid Jazz */
	ACID_JAZZ("Acid Jazz"),
	/* Polka */
	POLKA("Polka"),
	/* Retro */
	RETRO("Retro"),
	/* Musical */
	MUSICAL("Musical"),
	/* Rock & Roll */
	ROCK_ROLL("Rock & Roll"),
	/* Hard Rock */
	HARD_ROCK("Hard Rock"),
	/* Folk */
	FOLK("Folk"),
	/* Folk-Rock */
	FOLK_ROCK("Folk-Rock"),
	/* National Folk */
	NATIONAL_FOLK("National Folk"),
	/* Swing */
	SWING("Swing"),
	/* Fast Fusion */
	FAST_FUSION("Fast Fusion"),
	/* Bebob */
	BEBOB("Bebob"),
	/* Latin */
	LATIN("Latin"),
	/* Revival */
	REVIVAL("Revival"),
	/* Celtic */
	CELTIC("Celtic"),
	/* Bluegrass */
	BLUEGRASS("Bluegrass"),
	/* Avantgarde */
	AVANTGRADE("Avantgarde"),
	/* Gothic Rock */
	GOTHIC_ROCK("Gothic Rock"),
	/* Progressive Rock */
	PROGRESSIVE_ROCK("Progressive Rock"),
	/* Psychedelic Rock */
	PSYCHEDELIC_ROCK("Psychedelic Rock"),
	/* Symphonic Rock */
	SYMPHONIC_ROCK("Symphonic Rock"),
	/* Slow Rock */
	SLOW_ROCK("Slow Rock"),
	/* Big Band */
	BIG_BAND("Big Band"),
	/* Chorus */
	CHORUS("Chorus"),
	/* Easy Listening */
	EASY_LISTENING("Easy Listening"),
	/* Acoustic */
	ACOUSTIC("Acoustic"),
	/* Humour */
	HUMOUR("Humour"),
	/* Speech */
	SPEECH("Speech"),
	/* Chanson */
	CHANSON("Chanson"),
	/* Opera */
	OPERA("Opera"),
	/* Chamber Music */
	CHAMBER_MUSIC("Chamber Music"),
	/* Sonata */
	SONATA("Sonata"),
	/* Symphony */
	SYMPHONY("Symphony"),
	/* Booty Bass */
	BOOTY_BASS("Booty Bass"),
	/* Primus */
	PRIMUS("Primus"),
	/* Porn Groove */
	PORN_GROOVE("Porn Groove"),
	/* Satire */
	SATIRE("Satire"),
	/* Slow Jam */
	SLOW_JAM("Slow Jam"),
	/* Club */
	CLUB("Club"),
	/* Tango */
	TANGO("Tango"),
	/* Samba */
	SAMBA("Samba"),
	/* Folklore */
	FOLKLORE("Folklore"),
	/* Ballad */
	BALLAD("Ballad"),
	/* Power Ballad */
	POWER_BALLAD("Power Ballad"),
	/* Rhythmic Soul */
	RGYTHMIC_SOUL("Rhythmic Soul"),
	/* Freestyle */
	FREESTYLE("Freestyle"),
	/* Duet */
	DUET("Duet"),
	/* Punk Rock */
	PUNK_ROCK("Punk Rock"),
	/* Drum Solo */
	DRUM_SOLO("Drum Solo"),
	/* A Cappella */
	A_CAPPELLA("A Cappella"),
	/* Euro-House */
	EURO_HOUSE("Euro-House"),
	/* Dance Hall */
	DANCE_HALL("Dance Hall"),
	/* Goa */
	GOA("Goa"),
	/* Drum & Bass */
	DRUM_BASS("Drum & Bass"),
	/* Club-House */
	CLUB_HOUSE("Club-House"),
	/* Hardcore */
	HARDCORE("Hardcore"),
	/* Terror */
	TERROR("Terror"),
	/* Indie */
	INDIE("Indie"),
	/* BritPop */
	BRITPOP("BritPop"),
	/* Negerpunk */
	BEWGERPUNK("Negerpunk"),
	/* Polsk Punk */
	POLSK_PUNK("Polsk Punk"),
	/* Beat */
	BEAT("Beat"),
	/* Christian Gangsta */
	CHRISTIAN_GANGSTA("Christian Gangsta"),
	/* Heavy Metal */
	HEAVY_METAL("Heavy Metal"),
	/* Black Metal */
	BLACK_METAL("Black Metal"),
	/* Crossover */
	CROSSOVER("Crossover"),
	/* Contemporary Christian */
	CONTEMPORARY_CHRISTIAN("Contemporary Christian"),
	/* Christian Rock */
	CHRISTIAN_ROCK("Christian Rock"),
	/* Merengue */
	MERENGUE("Merengue"),
	/* Salsa */
	SALSA("Salsa"),
	/* Thrash Metal */
	THRASH_METAL("Thrash Metal"),
	/* Anime */
	ANIME("Anime"),
	/* JPop */
	JPOP("JPop"),
	/* SynthPop */
	SYNTHPOP("SynthPop");

	/**
	 * 
	 * @param name
	 */
	private Genre(String name) {
		this.name = name;
	}

	/**
	 * Returns the name of the genre.
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/** Genre Name. */
	private String name;
}