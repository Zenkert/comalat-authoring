package org.sakaiproject.authoring.model;

import java.io.Serializable;

/**
 * The Model for the COMALAT Grade Threshold
 *
 * @author Baris Watzke (baris.watzke@student.uni-siegen.de)
 */

public class ComalatGradeThreshold implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long comalatGradeThresholdId; // primary key - auto-generated
    private String language;
    private Double percentageActivity;
    private Double percentageFuzzyGrading;

    public Long getComalatGradeThresholdId() {
        return comalatGradeThresholdId;
    }

    public void setComalatGradeThresholdId(Long comalatGradeThresholdId) {
        this.comalatGradeThresholdId = comalatGradeThresholdId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Double getPercentageActivity() {
        return percentageActivity;
    }

    public void setPercentageActivity(Double percentageActivity) {
        this.percentageActivity = percentageActivity;
    }

    public Double getPercentageFuzzyGrading() {
        return percentageFuzzyGrading;
    }

    public void setPercentageFuzzyGrading(Double percentageFuzzyGrading) {
        this.percentageFuzzyGrading = percentageFuzzyGrading;
    }
}
