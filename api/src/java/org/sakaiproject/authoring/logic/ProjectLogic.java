package org.sakaiproject.authoring.logic;

import org.sakaiproject.authoring.model.ComalatActivity;
import org.sakaiproject.authoring.model.ComalatAssessment;
import org.sakaiproject.authoring.model.ComalatGradeThreshold;
import org.sakaiproject.authoring.model.ComalatMetadata;
import org.sakaiproject.authoring.model.ComalatFeedback;

import java.util.List;

/**
 * The logic interface for the COMALAT authoring tool
 * 
 * @author Johannes Zenkert (johannes.zenkert@uni-siegen.de)
 * @author Baris Watzke (baris.watzke@student.uni-siegen.de)
 *
 */
public interface ProjectLogic {
	
	public ComalatAssessment getComalatAssessmentById(Long comalatAssessmentId);

	public boolean saveComalatAssessment(ComalatAssessment comalatAssessment);

	public void saveComalatAssessmentList(List<ComalatAssessment> comalatAssessments);

	public void deleteComalatAssessment(ComalatAssessment comalatAssessment);

	public void deleteComalatAssessmentList(List<ComalatAssessment> comalatAssessments);

	public ComalatAssessment getComalatAssessmentBySakaiAssessmentId(String sakaiAssessmentId);

	public List<ComalatAssessment> getAllComalatAssessments();

	public ComalatActivity getComalatActivityById(Long comalatActivityId);

	public boolean saveComalatActivity(ComalatActivity comalatActivity);

	public void saveComalatActivityList(List<ComalatActivity> comalatActivities);

	public void deleteComalatActivity(ComalatActivity comalatActivity);

	public void deleteComalatActivityList(List<ComalatActivity> comalatActivities);

	public ComalatActivity getComalatActivityBySamigoQuestionId(String samigoItemId);

	public List<ComalatActivity> getAllComalatActivities();

	ComalatMetadata getComalatMetadataById(Long comalatMetadataId);

	boolean saveComalatMetadata(ComalatMetadata comalatMetadata);

	void saveComalatMetadataList(List<ComalatMetadata> comalatMetadata);

	void deleteComalatMetadata(ComalatMetadata comalatMetadata);

	void deleteComalatMetadataList(List<ComalatMetadata> comalatMetadata);

	List<ComalatMetadata> getAllComalatMetadataTags();

	ComalatGradeThreshold getComalatGradeThresholdById(Long comalatGradeThresholdId);

	boolean saveComalatGradeThreshold(ComalatGradeThreshold comalatGradeThreshold);

	void saveComalatGradeThresholdList(List<ComalatGradeThreshold> comalatGradeThreshold);

	void deleteComalatGradeThreshold(ComalatGradeThreshold comalatGradeThreshold);

	void deleteComalatGradeThresholdList(List<ComalatGradeThreshold> comalatGradeThreshold);

	List<ComalatGradeThreshold> getAllComalatGradeThresholds();

	ComalatFeedback getComalatFeedbackById(Long comalatFeedbackId);

	boolean saveComalatFeedback(ComalatFeedback comalatFeedback);

	void saveComalatFeedbackList(List<ComalatFeedback> comalatFeedback);

	void deleteComalatFeedback(ComalatFeedback comalatFeedback);

	void deleteComalatFeedbackList(List<ComalatFeedback> comalatFeedback);

	List<ComalatFeedback> getAllComalatFeedback();

}
