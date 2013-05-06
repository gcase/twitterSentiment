package com.sdg.ts.model;

import com.google.common.base.Objects;

/**
 * Created with IntelliJ IDEA.
 * User: Greg
 * Date: 5/1/13
 * Time: 11:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class Sentiment {

    private Mood mood;
    private float confidence;

    public Sentiment(Mood mood, float confidence) {
        this.mood = mood;
        this.confidence = confidence;
    }

    public Mood getMood() {
        return mood;
    }

    public float getConfidence() {
        return confidence;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mood", mood)
                .add("confidence", confidence)
                .toString();
    }
}
