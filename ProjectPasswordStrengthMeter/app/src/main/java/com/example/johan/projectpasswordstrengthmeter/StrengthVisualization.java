package com.example.johan.projectpasswordstrengthmeter;

/**
 * Created by johan on 2016-01-12.
 */
public class StrengthVisualization {
    private boolean hasBar;
    private boolean hasText;

    //Doesnt do much now since the visualization is very simple.
    public StrengthVisualization() {
        hasBar = true;
        hasText = true;
    }

    public boolean hasStrengthBar() {
        return hasBar;
    }

    public boolean hasStrengthText() {
        return hasText;
    }
}
