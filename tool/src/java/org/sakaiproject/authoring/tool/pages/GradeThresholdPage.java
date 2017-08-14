package org.sakaiproject.authoring.tool.pages;

import codetroopers.wicket.web.datagrid.EditableGrid;
import codetroopers.wicket.web.datagrid.column.AbstractEditablePropertyColumn;
import codetroopers.wicket.web.datagrid.column.EditableCellPanel;
import codetroopers.wicket.web.datagrid.column.EditableRequiredDropDownCellPanel;
import codetroopers.wicket.web.datagrid.column.RequiredEditableTextFieldColumn;
import codetroopers.wicket.web.datagrid.provider.EditableListDataProvider;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.sakaiproject.authoring.model.ComalatGradeThreshold;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Grade Threshold Page to comalat grading information.
 * A grade contains percentage values for grade calculation purposes.
 *
 * @author Baris Watzke (baris.watzke@student.uni-siegen.de)
 */

public class GradeThresholdPage extends BasePage {
    private static final long serialVersionUID = 1L;

    private static List<String> LANGUAGES = Arrays.asList("English", "German", "Spanish", "DAF", "DAF1617");

    public GradeThresholdPage() {
        super();
        disableLink(fourthLink);

        feedbackPanel.setOutputMarkupPlaceholderTag(true);

        EditableGrid<ComalatGradeThreshold, String> editableGrid = new EditableGrid<ComalatGradeThreshold, String>("grid", getColumns(),
                new EditableListDataProvider<>(projectLogic.getAllComalatGradeThresholds()), 100, ComalatGradeThreshold.class) {
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
            protected void onDelete(AjaxRequestTarget target, IModel<ComalatGradeThreshold> rowModel) {
                target.add(feedbackPanel);
                projectLogic.deleteComalatGradeThreshold(rowModel.getObject());
            }

            @Override
            protected void onSave(AjaxRequestTarget target, IModel<ComalatGradeThreshold> rowModel) {
                target.add(feedbackPanel);
                String language = rowModel.getObject().getLanguage();
                rowModel.getObject().setLanguage(createLanguageIdentifier(language));
                projectLogic.saveComalatGradeThreshold(rowModel.getObject());

            }

            @Override
            protected void onAdd(AjaxRequestTarget target, ComalatGradeThreshold rowModel) {
                target.add(feedbackPanel);
                String language = rowModel.getLanguage();
                rowModel.setLanguage(createLanguageIdentifier(language));
                projectLogic.saveComalatGradeThreshold(rowModel);
            }
        };
        add(editableGrid);
        editableGrid.setOutputMarkupPlaceholderTag(true);
    }

    /**
     * @param language as learning language
     * @return language identifier for gradeThreshold database entry
     */
    private String createLanguageIdentifier(String language) {
        switch (language) {
            case "English":
                return "EN";
            case "German":
                return "DE";
            case "Spanish":
                return "ES";
            case "DAF":
                return "DAF";
            case "DAF1617":
                return "DAF1617";
            default:
                throw new IllegalArgumentException("Invalid language: " + language);
        }
    }

    /**
     * @return necessary attributes for the grade threshold grid
     */
    private List<AbstractEditablePropertyColumn<ComalatGradeThreshold, String>> getColumns() {
        List<AbstractEditablePropertyColumn<ComalatGradeThreshold, String>> columns = new ArrayList<>();


        columns.add(new RequiredEditableTextFieldColumn<ComalatGradeThreshold, String>(new Model<>("Language"), "language") {
            private static final long serialVersionUID = 1L;

            public EditableCellPanel getEditableCellPanel(String componentId) {
                return new EditableRequiredDropDownCellPanel<>(componentId, this, LANGUAGES);
            }

        });

        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Activity Percentage"), "percentageActivity"));
        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Fuzzy Grade Percentage"), "percentageFuzzyGrading"));

        return columns;
    }
}
