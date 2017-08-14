package org.sakaiproject.authoring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The Model for the COMALAT Feedback
 *
 * Created by Johannes on 26.06.2017.
 * @author Johannes Zenkert (johannes.zenkert@uni-siegen.de)
 */

public class ComalatFeedback implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long comalatFeedbackId; // primary key - auto-generated
    private Integer lowerPercent;
    private Integer upperPercent;
    private boolean decisionPoint;
    private String feedbackDE;
    private String feedbackEN;
    private String feedbackES;
    private String feedbackAR;
    private String feedbackKU;

    public ComalatFeedback() {

    }

    public Long getComalatFeedbackId() {
        return comalatFeedbackId;
    }

    public void setComalatFeedbackId(Long comalatFeedbackId) {
        this.comalatFeedbackId = comalatFeedbackId;
    }

    public Integer getLowerPercent() {
        return lowerPercent;
    }

    public void setLowerPercent(Integer lowerPercent) {
        this.lowerPercent = lowerPercent;
    }

    public Integer getUpperPercent() {
        return upperPercent;
    }

    public boolean isDecisionPoint() {
        return decisionPoint;
    }

    public void setDecisionPoint(boolean decisionPoint) {
        this.decisionPoint = decisionPoint;
    }

    public void setUpperPercent(Integer upperPercent) {
        this.upperPercent = upperPercent;
    }

    public String getFeedbackDE() {
        return feedbackDE;
    }

    public void setFeedbackDE(String feedbackDE) {
        this.feedbackDE = feedbackDE;
    }

    public String getFeedbackEN() {
        return feedbackEN;
    }

    public void setFeedbackEN(String feedbackEN) {
        this.feedbackEN = feedbackEN;
    }

    public String getFeedbackES() {
        return feedbackES;
    }

    public void setFeedbackES(String feedbackES) {
        this.feedbackES = feedbackES;
    }

    public String getFeedbackAR() {
        return feedbackAR;
    }

    public void setFeedbackAR(String feedbackAR) {
        this.feedbackAR = feedbackAR;
    }

    public String getFeedbackKU() {
        return feedbackKU;
    }

    public void setFeedbackKU(String feedbackKU) {
        this.feedbackKU = feedbackKU;
    }
}
