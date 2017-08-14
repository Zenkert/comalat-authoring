package org.sakaiproject.authoring.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * The Model for the COMALAT Assessment
 *
 * @author Johannes Zenkert (johannes.zenkert@uni-siegen.de)
 * @author Baris Watzke (baris.watzke@student.uni-siegen.de)
 */

public class ComalatAssessment implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long comalatAssessmentId; // primary key - auto-generated
    private String assessmentName;
    private String assessmentIdentifier;
    private String language;
    private String lesson;
    private String path;
    private int number;
    private Double score;
    private String createdBy;
    private Timestamp createdDate;
    private String lastModifiedBy;
    private Timestamp lastModifiedDate;
    private String nextStep;
    private String assignedGroupFail;

    private Set<ComalatActivity> comalatActivities = new HashSet<>(0);


    public ComalatAssessment() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
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

    public String getComalatIdentifier() {
        return assessmentIdentifier;
    }

    public void setComalatIdentifier(String assessmentIdentifier) {
        this.assessmentIdentifier = assessmentIdentifier;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public Long getComalatAssessmentId() {
        return comalatAssessmentId;
    }

    public void setComalatAssessmentId(Long comalatAssessmentId) {
        this.comalatAssessmentId = comalatAssessmentId;
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

    public Set<ComalatActivity> getComalatActivities() {
        return comalatActivities;
    }

    public void setComalatActivities(Set<ComalatActivity> comalatActivities) {
        this.comalatActivities = comalatActivities;
    }
}
