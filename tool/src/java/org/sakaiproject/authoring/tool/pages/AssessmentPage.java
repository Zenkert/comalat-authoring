package org.sakaiproject.authoring.tool.pages;

import codetroopers.wicket.web.datagrid.EditableGrid;
import codetroopers.wicket.web.datagrid.column.*;
import codetroopers.wicket.web.datagrid.provider.EditableListDataProvider;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.sakaiproject.authoring.model.ComalatAssessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * The Administration Page to CRUD comalat assessment information.
 * An assessment gathers multiple activities.
 *
 * @author Baris Watzke (baris.watzke@student.uni-siegen.de)
 * @author Johannes Zenkert (johannes.zenkert@uni-siegen.de)
 */

public class AssessmentPage extends BasePage {

    private static List<String> LESSONS;
    private static List<String> PATHS;
    private String siteLanguage;

    public AssessmentPage() {
        super();
        disableLink(secondLink);
        feedbackPanel.setOutputMarkupPlaceholderTag(true);

        initGridArrays();
        siteLanguage = retrieveSiteLanguage();

        EditableGrid<ComalatAssessment, String> editableGrid = new EditableGrid<ComalatAssessment, String>("grid", getColumns(),
                new EditableListDataProvider<>(retrieveAssessments()), 100, ComalatAssessment.class) {
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
            protected void onDelete(AjaxRequestTarget target, IModel<ComalatAssessment> rowModel) {
                target.add(feedbackPanel);
                projectLogic.deleteComalatAssessment(rowModel.getObject());
            }

            @Override
            protected void onSave(AjaxRequestTarget target, IModel<ComalatAssessment> rowModel) {
                target.add(feedbackPanel);

                String identifier = createIdentifier(rowModel.getObject());
                rowModel.getObject().setComalatIdentifier(identifier);

                projectLogic.saveComalatAssessment(rowModel.getObject());
            }

            @Override
            protected void onAdd(AjaxRequestTarget target, ComalatAssessment rowModel) {
                target.add(feedbackPanel);

                String identifier = createIdentifier(rowModel);
                rowModel.setComalatIdentifier(identifier);
                rowModel.setLanguage(siteLanguage);

                if (rowModel.getLesson() == null || rowModel.getPath() == null) {
                    //TODO call JavaScript Alert to inform about missing attributes
                } else projectLogic.saveComalatAssessment(rowModel);
            }
        };
        add(editableGrid);
        editableGrid.setOutputMarkupPlaceholderTag(true);
    }

    /**
     * Creates arrays for editable grid
     */
    private void initGridArrays() {
        LESSONS = Arrays.asList("L01", "L02", "L03", "L04", "L05", "L06", "L07", "L08", "L09", "L10",
                "L11", "L12", "L13", "L14", "L15", "L16", "L17", "L18", "L19", "L20");
        PATHS = Arrays.asList("N", "EX", "F", "L", "C");
    }

    /**
     * @return necessary attributes for the assessment grid
     */
    private List<AbstractEditablePropertyColumn<ComalatAssessment, String>> getColumns() {
        List<AbstractEditablePropertyColumn<ComalatAssessment, String>> columns = new ArrayList<>();

        columns.add(new EditableTextFieldPropertyColumn<>(new Model<>("Identifier"), "comalatIdentifier", false));
        columns.add(new EditableTextFieldPropertyColumn<>(new Model<>("Name"), "assessmentName"));
        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Number"), "number"));
        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Score"), "score"));
        columns.add(new EditableTextFieldPropertyColumn<>(new Model<>("Next Step"), "nextStep"));
        columns.add(new EditableTextFieldPropertyColumn<>(new Model<>("Group Fail"), "assignedGroupFail"));

        columns.add(new AbstractEditablePropertyColumn<ComalatAssessment, String>(new Model<>("Lesson"), "lesson") {

            private static final long serialVersionUID = 1L;

            public EditableCellPanel getEditableCellPanel(String componentId) {
                return new EditableRequiredDropDownCellPanel<>(componentId, this, LESSONS);
            }

        });
        columns.add(new AbstractEditablePropertyColumn<ComalatAssessment, String>(new Model<>("Path"), "path") {

            private static final long serialVersionUID = 1L;

            public EditableCellPanel getEditableCellPanel(String componentId) {
                return new EditableRequiredDropDownCellPanel<>(componentId, this, PATHS);
            }

        });
        return columns;
    }

    /**
     * @param comalatAssessment as assessment
     * @return a unique identity for an assessment
     */
    private String createIdentifier(ComalatAssessment comalatAssessment) {
        String lesson = comalatAssessment.getLesson();
        String path = comalatAssessment.getPath();
        int number = comalatAssessment.getNumber();

        StringJoiner joiner = new StringJoiner("-");
        joiner.add(siteLanguage).add(lesson).add(path).add(String.valueOf(number));

        return joiner.toString();
    }


    /**
     * @return all assessments that are in the same language as the current site
     */
    private List<ComalatAssessment> retrieveAssessments() {
        List<ComalatAssessment> assessmentList = projectLogic.getAllComalatAssessments();
        return assessmentList.stream().filter(p -> p.getLanguage().equals(siteLanguage)).collect(Collectors.toList());
    }
}
