package com.noworknoplay.crystalball;

import android.widget.FrameLayout;

import java.util.Random;

/**
 * Created by Dave on 10/27/13.
 */
public class CrystallBall {
    // Member variables (properties about the object)
    public String[] mAnswers = {
        "It is certain",
                "It is decidedly so",
                "All signs say Yes",
                "The stars are not aligned",
                "My reply is no",
                "It is doubtful",
                "Better not tell you now",
                "Concentrate and ask again",
                "Unable to answer now"
    };

    // Methods (abilities: things the object can do)
    public String getAnAnswer() {

        String answer = "";

        // Randomly select one of 3 answers: Yes, No, or Maybe
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(mAnswers.length);

        answer = mAnswers[randomNumber];

        return answer;

    }


}
