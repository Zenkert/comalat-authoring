package org.sakaiproject.authoring.tool.pages;

import codetroopers.wicket.web.datagrid.EditableGrid;
import codetroopers.wicket.web.datagrid.column.*;
import codetroopers.wicket.web.datagrid.provider.EditableListDataProvider;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.sakaiproject.authoring.model.ComalatActivity;
import org.sakaiproject.authoring.model.ComalatAssessment;
import org.sakaiproject.authoring.model.ComalatMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * The Administration Page to CRUD comalat activity information.
 * Instructor role only.
 *
 * @author Baris Watzke (baris.watzke@student.uni-siegen.de)
 * @author Johannes Zenkert (johannes.zenkert@uni-siegen.de)
 */

public class ActivityPage extends BasePage {

    private static final long serialVersionUID = 1L;
    private static List<String> LESSONS;
    private static List<String> PATHS;
    private static List<String> COMPETENCES;
    private static List<String> TYPES;
    private static List<String> WEIGHTINGS;
    private static List<String> METADATA;
    private static List<String> ASSESSMENTS;

    private String siteLanguage;
    private String selectedLesson = "";
    private String selectedPath = "";
    private String selectedCompetence = "";
    private String selectedType = "";
    private String selectedWeighting = "";
    private String selectedMetadata = "";

    private List<ComalatActivity> filteredActivities = new ArrayList<>();
    private EditableGrid<ComalatActivity, String> editableGrid;
    private ActivityPage instance = null;

    public ActivityPage() {
        super();
        disableLink(firstLink);
        feedbackPanel.setOutputMarkupPlaceholderTag(true);

        siteLanguage = retrieveSiteLanguage();

        initGridArrays();
        METADATA = retrieveMetadataTags();
        ASSESSMENTS = retrieveAssessmentIDs();

        DropDownChoice<String> listLessons = new DropDownChoice<>("listLessons", new PropertyModel<>(this, "selectedLesson"), LESSONS);
        DropDownChoice<String> listPaths = new DropDownChoice<>("listPaths", new PropertyModel<>(this, "selectedPath"), PATHS);
        DropDownChoice<String> listCompetences = new DropDownChoice<>("listCompetences", new PropertyModel<>(this, "selectedCompetence"), COMPETENCES);
        DropDownChoice<String> listTypes = new DropDownChoice<>("listTypes", new PropertyModel<>(this, "selectedType"), TYPES);
        DropDownChoice<String> listWeightings = new DropDownChoice<>("listWeightings", new PropertyModel<>(this, "selectedWeighting"), WEIGHTINGS);
        DropDownChoice<String> listMetadata = new DropDownChoice<>("listMetadata", new PropertyModel<>(this, "selectedMetadata"), METADATA);

        listLessons.setNullValid(true);
        listPaths.setNullValid(true);
        listCompetences.setNullValid(true);
        listTypes.setNullValid(true);
        listWeightings.setNullValid(true);
        listMetadata.setNullValid(true);

        Form form = new Form("form") {
            @Override
            protected void onSubmit() {
                filterActivities();
                instance.remove("grid");
                createEditableGrid(true);
                instance.add(editableGrid);

            }
        };

        Button resetButton = new Button("resetButton") {
            @Override
            public void onSubmit() {
                selectedLesson = null;
                selectedPath = null;
                selectedCompetence = null;
                selectedType = null;
                selectedWeighting = null;
                selectedMetadata = null;

                instance.remove("grid");
                createEditableGrid(false);
                instance.add(editableGrid);
            }
        };

        add(form);
        form.add(resetButton);
        form.add(listLessons);
        form.add(listPaths);
        form.add(listCompetences);
        form.add(listTypes);
        form.add(listWeightings);
        form.add(listMetadata);
        form.setOutputMarkupPlaceholderTag(true);

        createEditableGrid(false);
        add(editableGrid);
        instance = this;
    }

    /**
     * Creates arrays for editable grid
     */
    private void initGridArrays() {
        LESSONS = Arrays.asList("L01", "L02", "L03", "L04", "L05", "L06", "L07", "L08", "L09", "L10",
                "L11", "L12", "L13", "L14", "L15", "L16", "L17", "L18", "L19", "L20");
        PATHS = Arrays.asList("N", "EX", "F", "L", "C");

        COMPETENCES = Arrays.asList("G", "R", "V", "L");
        TYPES = Arrays.asList("P", "A");
        WEIGHTINGS = Arrays.asList("Very Important", "Important", "Normal", "Less Important");
    }

    /**
     * Creates grid with activities
     *
     * @param filtered - true: filtered activities | false: all activities
     */
    private void createEditableGrid(boolean filtered) {
        List<ComalatActivity> activities = filtered ? filteredActivities : retrieveActivities();

        editableGrid = new EditableGrid<ComalatActivity, String>("grid", getColumns(),
                new EditableListDataProvider<>(activities), 500, ComalatActivity.class) {
            private static final long serialVersionUID = 1L;

            @Override
            protected void onError(AjaxRequestTarget target) {
                target.add(feedbackPanel);
            }

            @Override
            protected void onCancel(AjaxRequestTarget target) {
                target.add(feedbackPanel);
            }

            @Override
            protected void onDelete(AjaxRequestTarget target, IModel<ComalatActivity> rowModel) {
                target.add(feedbackPanel);
                projectLogic.deleteComalatActivity(rowModel.getObject());
            }

            @Override
            protected void onSave(AjaxRequestTarget target, IModel<ComalatActivity> rowModel) {
                target.add(feedbackPanel);
                String identifier = createIdentifier(rowModel.getObject());
                rowModel.getObject().setComalatIdentifier(identifier);

                projectLogic.saveComalatActivity(rowModel.getObject());
            }

            @Override
            protected void onAdd(AjaxRequestTarget target, ComalatActivity rowModel) {
                target.add(feedbackPanel);
                String identifier = createIdentifier(rowModel);
                rowModel.setComalatIdentifier(identifier);
                rowModel.setLanguage(siteLanguage);

                if (rowModel.getLesson() == null || rowModel.getPath() == null || rowModel.getCompetence() == null
                        || rowModel.getType() == null || rowModel.getMetadataTag() == null) {
                    //TODO call JavaScript Alert to inform about missing attributes
                } else projectLogic.saveComalatActivity(rowModel);
            }
        };
    }

    /**
     * @return necessary attributes for the activity grid
     */
    private List<AbstractEditablePropertyColumn<ComalatActivity, String>> getColumns() {
        List<AbstractEditablePropertyColumn<ComalatActivity, String>> columns = new ArrayList<>();

        columns.add(new EditableTextFieldPropertyColumn<>(new Model<>("Identifier"), "comalatIdentifier", false));
        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Name"), "activityName"));
        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Number"), "numberAsString"));
        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Score"), "score"));
        columns.add(new AbstractEditablePropertyColumn<ComalatActivity, String>(new Model<>("Lesson"), "lesson") {
            private static final long serialVersionUID = 1L;

            public EditableCellPanel getEditableCellPanel(String componentId) {
                return new EditableRequiredDropDownCellPanel<>(componentId, this, LESSONS);
            }

        });
        columns.add(new AbstractEditablePropertyColumn<ComalatActivity, String>(new Model<>("Path"), "path") {
            private static final long serialVersionUID = 1L;

            public EditableCellPanel getEditableCellPanel(String componentId) {
                return new EditableRequiredDropDownCellPanel<>(componentId, this, PATHS);
            }

        });
        columns.add(new AbstractEditablePropertyColumn<ComalatActivity, String>(new Model<>("Competence"), "competence") {
            private static final long serialVersionUID = 1L;

            public EditableCellPanel getEditableCellPanel(String componentId) {
                return new EditableRequiredDropDownCellPanel<>(componentId, this, COMPETENCES);
            }

        });
        columns.add(new AbstractEditablePropertyColumn<ComalatActivity, String>(new Model<>("Type"), "type") {
            private static final long serialVersionUID = 1L;

            public EditableCellPanel getEditableCellPanel(String componentId) {
                return new EditableRequiredDropDownCellPanel<>(componentId, this, TYPES);
            }

        });
        columns.add(new AbstractEditablePropertyColumn<ComalatActivity, String>(new Model<>("Metadata"), "metadataTag") {
            private static final long serialVersionUID = 1L;

            public EditableCellPanel getEditableCellPanel(String componentId) {
                return new EditableRequiredDropDownCellPanel<>(componentId, this, METADATA);
            }

        });
        columns.add(new AbstractEditablePropertyColumn<ComalatActivity, String>(new Model<>("DP"), "decisionPoint") {
            private static final long serialVersionUID = 1L;

            public EditableCellPanel getEditableCellPanel(String componentId) {
                return new EditableRequiredDropDownCellPanel<>(componentId, this, Arrays.asList("false", "true"));
            }

        });
        columns.add(new AbstractEditablePropertyColumn<ComalatActivity, String>(new Model<>("NA"), "nonAssessed") {
            private static final long serialVersionUID = 1L;

            public EditableCellPanel getEditableCellPanel(String componentId) {
                return new EditableRequiredDropDownCellPanel<>(componentId, this, Arrays.asList("false", "true"));
            }

        });
        columns.add(new AbstractEditablePropertyColumn<ComalatActivity, String>(new Model<>("Weighting"), "weighting") {
            private static final long serialVersionUID = 1L;

            public EditableCellPanel getEditableCellPanel(String componentId) {
                return new EditableRequiredDropDownCellPanel<>(componentId, this, WEIGHTINGS);
            }

        });

        columns.add(new EditableTextFieldPropertyColumn<>(new Model<>("Assessment"), "assessmentIdentifier"));
        columns.add(new EditableTextFieldPropertyColumn<>(new Model<>("Extra Activities"), "assignedExtraActivity"));
        columns.add(new EditableTextFieldPropertyColumn<>(new Model<>("Next Step"), "nextStep"));
        columns.add(new EditableTextFieldPropertyColumn<>(new Model<>("Group Fail"), "assignedGroupFail"));

        return columns;
    }

    /**
     * @param comalatActivity as activity
     * @return a unique identity for an activity
     */
    private String createIdentifier(ComalatActivity comalatActivity) {
        String lesson = comalatActivity.getLesson();
        String path = comalatActivity.getPath();
        String competence = comalatActivity.getCompetence();
        String type = comalatActivity.getType();
        String metadata = comalatActivity.getMetadataTag();
        String numberAsString = comalatActivity.getNumberAsString();

        StringJoiner joiner = new StringJoiner("-");
        joiner.add(siteLanguage).add(lesson).add(path).add(competence).add(type).add(metadata).add(numberAsString);

        return joiner.toString();
    }

    /**
     * @return all metadata tags that belong to the same language as the current site
     */
    private List<String> retrieveMetadataTags() {
        List<ComalatMetadata> comalatMetadataTags = projectLogic.getAllComalatMetadataTags();
        List<ComalatMetadata> filteredMetadataTags = comalatMetadataTags.stream().
                filter(p -> p.getLanguage().equals(retrieveSiteLanguage())).collect(Collectors.toList());

        List<String> result = new ArrayList<>();
        for (ComalatMetadata tag : filteredMetadataTags) {
            result.add(tag.getMetadataTag());
        }
        return result;
    }

    /**
     * @return all assessment ids that belong to the same language as the current site
     */
    private List<String> retrieveAssessmentIDs() {
        List<ComalatAssessment> comalatAssessments = projectLogic.getAllComalatAssessments();
        List<ComalatAssessment> filteredAssessments =
                comalatAssessments.stream().filter(p -> p.getLanguage().equals(siteLanguage)).collect(Collectors.toList());

        List<String> result = new ArrayList<>();
        for (ComalatAssessment assessment : filteredAssessments) {
            result.add(assessment.getComalatIdentifier());
        }
        return result;
    }

    /**
     * @return all activities that are in the same language as the current site
     */
    private List<ComalatActivity> retrieveActivities() {
        List<ComalatActivity> activityList = projectLogic.getAllComalatActivities();
        return activityList.stream().filter(p -> p.getLanguage().equals(siteLanguage)).collect(Collectors.toList());
    }

    /**
     * Filters activities in dependence of requested attributes
     */
    private void filterActivities() {
        filteredActivities = retrieveActivities();

        if (selectedLesson != null) {
            filteredActivities = filteredActivities.stream().filter(p -> p.getLesson().equals(selectedLesson)).collect(Collectors.toList());
        }
        if (selectedPath != null) {
            filteredActivities = filteredActivities.stream().filter(p -> p.getPath().equals(selectedPath)).collect(Collectors.toList());
        }
        if (selectedCompetence != null) {
            filteredActivities = filteredActivities.stream().filter(p -> p.getCompetence().equals(selectedCompetence)).collect(Collectors.toList());
        }
        if (selectedType != null) {
            filteredActivities = filteredActivities.stream().filter(p -> p.getType().equals(selectedType)).collect(Collectors.toList());
        }
        if (selectedWeighting != null) {
            filteredActivities = filteredActivities.stream().filter(p -> p.getWeighting().equals(selectedWeighting)).collect(Collectors.toList());
        }
        if (selectedMetadata != null) {
            filteredActivities = filteredActivities.stream().filter(p -> p.getMetadataTag().equals(selectedMetadata)).collect(Collectors.toList());
        }
    }
}