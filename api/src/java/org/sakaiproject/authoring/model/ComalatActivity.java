package org.sakaiproject.authoring.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The Model for the COMALAT Activity
 *
 * @author Johannes Zenkert (johannes.zenkert@uni-siegen.de)
 * @author Baris Watzke (baris.watzke@student.uni-siegen.de)
 */

public class ComalatActivity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long comalatActivityId; // primary key - auto-generated
    private String activityName;

    private String comalatIdentifier;
    private String language;
    private String lesson;
    private String path;

    private String numberAsString;
    private String competence;
    private String type;
    private String metadataTag;
    private String assessmentIdentifier;

    private String presentationType;
    private String questionType;
    private Double score;

    private boolean nonAssessed = false;
    private boolean decisionPoint = false;
    private String weighting;

    private boolean hasExtraActivity = false;
    private String assignedExtraActivity;
    private String nextStep;
    private String assignedGroupFail;
    private String createdBy;
    private Timestamp createdDate;
    private String lastModifiedBy;
    private Timestamp lastModifiedDate;
    public ComalatActivity() {

    }
    public ComalatActivity(String identifier) {
        comalatIdentifier = identifier;
    }
    public ComalatActivity(String identifier, String lesson, String competence) {
        comalatIdentifier = identifier;
        this.lesson = lesson;
        this.competence = competence;
    }

    public String getWeighting() {
        return weighting;
    }

    public void setWeighting(String weighting) {
        this.weighting = weighting;
    }

    public boolean isNonAssessed() {
        return nonAssessed;
    }

    public void setNonAssessed(boolean nonAssessed) {
        this.nonAssessed = nonAssessed;
    }

    public boolean isDecisionPoint() {
        return decisionPoint;
    }

    public void setDecisionPoint(boolean decisionPoint) {
        this.decisionPoint = decisionPoint;
    }

    public boolean isHasExtraActivity() {
        return hasExtraActivity;
    }

    public void setHasExtraActivity(boolean hasExtraActivity) {
        this.hasExtraActivity = hasExtraActivity;
    }

    public Long getComalatActivityId() {
        return comalatActivityId;
    }

    public void setComalatActivityId(Long comalatActivityId) {
        this.comalatActivityId = comalatActivityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getComalatIdentifier() {
        return comalatIdentifier;
    }

    public void setComalatIdentifier(String comalatIdentifier) {
        this.comalatIdentifier = comalatIdentifier;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNumberAsString() {
        return numberAsString;
    }

    public void setNumberAsString(String numberAsString) {
        this.numberAsString = numberAsString;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMetadataTag() {
        return metadataTag;
    }

    public void setMetadataTag(String metadataTag) {
        this.metadataTag = metadataTag;
    }

    public String getAssessmentIdentifier() {
        return assessmentIdentifier;
    }

    public void setAssessmentIdentifier(String assessmentIdentifier) {
        this.assessmentIdentifier = assessmentIdentifier;
    }

    public String getPresentationType() {
        return presentationType;
    }

    public void setPresentationType(String presentationType) {
        this.presentationType = presentationType;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }


    public String getAssignedExtraActivity() {
        return assignedExtraActivity;
    }

    public void setAssignedExtraActivity(String assignedExtraActivity) {
        this.assignedExtraActivity = assignedExtraActivity;
    }

    public String getNextStep() {
        return nextStep;
    }

    public void setNextStep(String nextStep) {
        this.nextStep = nextStep;
    }

    public String getAssignedGroupFail() {
        return assignedGroupFail;
    }

    public void setAssignedGroupFail(String assignedGroupFail) {
        this.assignedGroupFail = assignedGroupFail;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Timestamp getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Timestamp lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /*public ComalatMetadata getComalatMetadata() {
        return comalatMetadata;
    }

    public void setComalatMetadata(ComalatMetadata comalatMetadata) {
        this.comalatMetadata = comalatMetadata;
    }

    public ComalatAssessment getComalatAssessment() {
        return comalatAssessment;
    }

    public void setComalatAssessment(ComalatAssessment comalatAssessment) {
        this.comalatAssessment = comalatAssessment;
    }*/
}
