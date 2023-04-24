/******************************************************************************
 * Copyright (C) Devamatre Inc 2009-2018. All rights reserved.
 *
 * This code is licensed to Devamatre under one or more contributor license 
 * agreements. The reproduction, transmission or use of this code, in source 
 * and binary forms, with or without modification, are permitted provided 
 * that the following conditions are met:
 * <pre>
 *  1. Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *  this list of conditions and the following disclaimer in the documentation
 *  and/or other materials provided with the distribution.
 * </pre>
 * <p/>
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
package com.rslakra.appsuite.jdk.net.echo;

public class EchoProtocol {
    private static final int WAITING = 0;
    private static final int SENTECHO = 1;
    private static final int SENTCLUE = 2;
    private static final int ANOTHER = 3;
    private static final int NUMJOKES = 5;

    private int state = WAITING;
    private int currentJoke = 0;

    private String[] clues = {"Rohtash", "Little Old Lady", "Atch", "Who", "Who"};

    private String[] answers = {"Rohtash the heat, it's cold in here!", "I didn't know you could yodel!", "Bless you!",
            "Is there an owl in here?", "Is there an echo in here?"};

    /**
     * @param theInput
     * @return
     */
    public String processInput(String theInput) {
        String theOutput = null;
        System.out.println("state : " + state);
        switch (state) {
            case WAITING:
                theOutput = "Echo! Echo!";
                state = SENTECHO;
                break;
            case SENTECHO:
                if (theInput.equalsIgnoreCase("Who's there?")) {
                    theOutput = clues[currentJoke];
                    state = SENTCLUE;
                } else {
                    theOutput = "You're supposed to say \"Who's there?\"! Try again. Echo! Echo!";
                }
                break;
            case SENTCLUE:
                if (theInput.equalsIgnoreCase(clues[currentJoke] + " who?")) {
                    theOutput = answers[currentJoke] + " Want another? (y/n)";
                    state = ANOTHER;
                } else {
                    theOutput = "You're supposed to say \"" + clues[currentJoke] + " who?\" ! Try again. Echo! Echo!";
                    state = SENTECHO;
                }
                break;
            case ANOTHER:
                if (theInput.equalsIgnoreCase("y")) {
                    theOutput = "Echo! Echo!";
                    if (currentJoke == (NUMJOKES - 1))
                        currentJoke = 0;
                    else
                        currentJoke++;
                    state = SENTECHO;
                } else {
                    theOutput = "Bye.";
                    state = WAITING;
                }
                break;
        }
        return theOutput;
    }
}
