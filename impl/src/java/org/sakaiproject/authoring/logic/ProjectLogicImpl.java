package org.sakaiproject.authoring.logic;

import org.apache.log4j.Logger;
import org.sakaiproject.authoring.dao.ProjectDao;
import org.sakaiproject.authoring.model.ComalatActivity;
import org.sakaiproject.authoring.model.ComalatAssessment;
import org.sakaiproject.authoring.model.ComalatGradeThreshold;
import org.sakaiproject.authoring.model.ComalatMetadata;
import org.sakaiproject.authoring.model.ComalatFeedback;
import org.sakaiproject.genericdao.api.search.Restriction;
import org.sakaiproject.genericdao.api.search.Search;

import java.util.List;

/**
 * Implementation of ProjectLogic for COMALAT Authoring Tool
 *
 * @author Johannes Zenkert (johannes.zenkert@uni-siegen.de)
 * @author Baris Watzke (baris.watzke@student.uni-siegen.de)
 *
 */
public class ProjectLogicImpl implements ProjectLogic {

    private static final Logger log = Logger.getLogger(ProjectLogicImpl.class);


    private ProjectDao dao;

    public void setDao(ProjectDao dao) {
        this.dao = dao;
    }

    @Override
    public ComalatAssessment getComalatAssessmentById(Long comalatAssessmentId) {
        if (comalatAssessmentId == null) {
            throw new IllegalArgumentException("comalatAssessmentId cannot be null when getting comalatAssessment");
        }
        Search search = new Search(new Restriction("comalatAssessmentId", comalatAssessmentId));
        ComalatAssessment comalatAssessment = dao.findOneBySearch(ComalatAssessment.class, search);
        return comalatAssessment;
    }

    @Override
    public ComalatAssessment getComalatAssessmentBySakaiAssessmentId(String sakaiAssessmentId) {
        if (sakaiAssessmentId == null) {
            throw new IllegalArgumentException("comalatAssessmentId cannot be null when getting comalatAssessment");
        }
        Search search =
                new Search(new Restriction("sakaiAssessmentId", sakaiAssessmentId));
        ComalatAssessment comalatAssessment = dao.findOneBySearch(ComalatAssessment.class, search);
        return comalatAssessment;
    }

    @Override
    public boolean saveComalatAssessment(ComalatAssessment comalatAssessment) {
        dao.save(comalatAssessment);
        log.debug(" ComalatAssessment  " + comalatAssessment.getComalatAssessmentId() +
                " successfuly saved");
        return true;
    }

    @Override
    public void saveComalatAssessmentList(List<ComalatAssessment> comalatAssessments) {
        for (ComalatAssessment comalatAssessment : comalatAssessments) {
            saveComalatAssessment(comalatAssessment);
        }
    }

    @Override
    public void deleteComalatAssessment(ComalatAssessment comalatAssessment) {
        dao.delete(comalatAssessment);
    }

    @Override
    public void deleteComalatAssessmentList(List<ComalatAssessment> comalatAssessments) {
        for (ComalatAssessment comalatAssessment : comalatAssessments) {
            deleteComalatAssessment(comalatAssessment);
        }
    }

    @Override
    public List<ComalatAssessment> getAllComalatAssessments() {
        List<ComalatAssessment> comalatAssessmentList = dao.findAll(ComalatAssessment.class);
        return comalatAssessmentList;
    }

    @Override
    public ComalatActivity getComalatActivityById(Long comalatActivityId) {
        if (comalatActivityId == null) {
            throw new IllegalArgumentException("comalatActivityId cannot be null when getting comalatActivity");
        }
        Search search = new Search(new Restriction("comalatActivityId", comalatActivityId));
        ComalatActivity comalatActivity = dao.findOneBySearch(ComalatActivity.class, search);
        return comalatActivity;
    }

    @Override
    public ComalatActivity getComalatActivityBySamigoQuestionId(String samigoItemId) {
        if (samigoItemId == null) {
            throw new IllegalArgumentException("comalatActivityId cannot be null when getting comalatActivity");
        }
        Search search =
                new Search(new Restriction("samigoItemId", samigoItemId));
        ComalatActivity comalatActivity = dao.findOneBySearch(ComalatActivity.class, search);
        return comalatActivity;
    }

    @Override
    public boolean saveComalatActivity(ComalatActivity comalatActivity) {
        dao.save(comalatActivity);
        log.debug(" ComalatActivity  " + comalatActivity.getComalatActivityId() +
                " successfuly saved");
        return true;
    }

    @Override
    public void saveComalatActivityList(List<ComalatActivity> comalatActivities) {
        for (ComalatActivity comalatActivity : comalatActivities) {
            saveComalatActivity(comalatActivity);
        }
    }

    @Override
    public void deleteComalatActivity(ComalatActivity comalatActivity) {
        dao.delete(comalatActivity);
    }

    @Override
    public void deleteComalatActivityList(List<ComalatActivity> comalatActivities) {
        for (ComalatActivity comalatActivity : comalatActivities) {
            deleteComalatActivity(comalatActivity);
        }
    }

    @Override
    public List<ComalatActivity> getAllComalatActivities() {
        List<ComalatActivity> comalatActivityList = dao.findAll(ComalatActivity.class);
        return comalatActivityList;
    }


    @Override
    public ComalatMetadata getComalatMetadataById(Long comalatMetadataId) {
        if (comalatMetadataId == null) {
            throw new IllegalArgumentException("comalatMetadataId cannot be null when getting comalatMetadata");
        }
        Search search = new Search(new Restriction("comalatMetadataId", comalatMetadataId));
        ComalatMetadata comalatMetadata = dao.findOneBySearch(ComalatMetadata.class, search);
        return comalatMetadata;
    }

    @Override
    public boolean saveComalatMetadata(ComalatMetadata comalatMetadata) {
        dao.save(comalatMetadata);
        log.debug(" ComalatMetadata  " + comalatMetadata.getComalatMetadataId() +
                " successfuly saved");
        return true;
    }

    @Override
    public void saveComalatMetadataList(List<ComalatMetadata> comalatMetadataTags) {
        for (ComalatMetadata comalatMetadata : comalatMetadataTags) {
            saveComalatMetadata(comalatMetadata);
        }
    }

    @Override
    public void deleteComalatMetadata(ComalatMetadata comalatMetadata) {
        dao.delete(comalatMetadata);
    }

    @Override
    public void deleteComalatMetadataList(List<ComalatMetadata> comalatMetadataTags) {
        for (ComalatMetadata comalatMetadata : comalatMetadataTags) {
            deleteComalatMetadata(comalatMetadata);
        }
    }

    @Override
    public List<ComalatMetadata> getAllComalatMetadataTags() {
        List<ComalatMetadata> comalatMetadataList = dao.findAll(ComalatMetadata.class);
        return comalatMetadataList;
    }

    @Override
    public ComalatGradeThreshold getComalatGradeThresholdById(Long comalatGradeThresholdId) {
        if (comalatGradeThresholdId == null) {
            throw new IllegalArgumentException("comalatGradeThresholdId cannot be null when getting comalatMetadata");
        }
        Search search = new Search(new Restriction("comalatGradeThresholdId", comalatGradeThresholdId));
        ComalatGradeThreshold comalatGradeThreshold = dao.findOneBySearch(ComalatGradeThreshold.class, search);
        return comalatGradeThreshold;
    }

    @Override
    public boolean saveComalatGradeThreshold(ComalatGradeThreshold comalatGradeThreshold) {
        dao.save(comalatGradeThreshold);
        log.debug(" ComalatGradeThreshold  " + comalatGradeThreshold.getComalatGradeThresholdId() +
                " successfuly saved");
        return true;
    }

    @Override
    public void saveComalatGradeThresholdList(List<ComalatGradeThreshold> comalatGradeThresholdTags) {
        for (ComalatGradeThreshold comalatGradeThreshold : comalatGradeThresholdTags) {
            saveComalatGradeThreshold(comalatGradeThreshold);
        }
    }

    @Override
    public void deleteComalatGradeThreshold(ComalatGradeThreshold comalatGradeThreshold) {
        dao.delete(comalatGradeThreshold);
    }

    @Override
    public void deleteComalatGradeThresholdList(List<ComalatGradeThreshold> comalatGradeThresholdTags) {
        for (ComalatGradeThreshold comalatGradeThreshold : comalatGradeThresholdTags) {
            deleteComalatGradeThreshold(comalatGradeThreshold);
        }
    }

    @Override
    public List<ComalatGradeThreshold> getAllComalatGradeThresholds() {
        List<ComalatGradeThreshold> comalatGradeThresholdList = dao.findAll(ComalatGradeThreshold.class);
        return comalatGradeThresholdList;
    }

    @Override
    public ComalatFeedback getComalatFeedbackById(Long comalatFeedbackId) {
        if (comalatFeedbackId == null) {
            throw new IllegalArgumentException("comalatFeedbackId cannot be null when getting comalatFeedback");
        }
        Search search = new Search(new Restriction("comalatFeedbackId", comalatFeedbackId));
        ComalatFeedback comalatFeedback = dao.findOneBySearch(ComalatFeedback.class, search);
        return comalatFeedback;
    }

    @Override
    public boolean saveComalatFeedback(ComalatFeedback comalatFeedback) {
        dao.save(comalatFeedback);
        log.debug(" ComalatFeedback  " + comalatFeedback.getComalatFeedbackId() +
                " successfuly saved");
        return true;
    }

    @Override
    public void saveComalatFeedbackList(List<ComalatFeedback> comalatFeedbackTags) {
        for (ComalatFeedback comalatFeedback : comalatFeedbackTags) {
            saveComalatFeedback(comalatFeedback);
        }
    }

    @Override
    public void deleteComalatFeedback(ComalatFeedback comalatFeedback) {
        dao.delete(comalatFeedback);
    }

    @Override
    public void deleteComalatFeedbackList(List<ComalatFeedback> comalatFeedbackTags) {
        for (ComalatFeedback comalatFeedback : comalatFeedbackTags) {
            deleteComalatFeedback(comalatFeedback);
        }
    }

    @Override
    public List<ComalatFeedback> getAllComalatFeedback() {
        List<ComalatFeedback> comalatFeedbackList = dao.findAll(ComalatFeedback.class);
        return comalatFeedbackList;
    }
}
