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
import org.sakaiproject.authoring.model.ComalatFeedback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Administration Page to comalat feedback information.
 * Feedback is referring to a comalat grade.
 *
 * @author Baris Watzke (baris.watzke@student.uni-siegen.de)
 * @author Johannes Zenkert (johannes.zenkert@uni-siegen.de)
 */

public class FeedbackPage extends BasePage {
    private static final long serialVersionUID = 1L;

    public FeedbackPage() {
        super();
        disableLink(fifthLink);

        feedbackPanel.setOutputMarkupPlaceholderTag(true);

        EditableGrid<ComalatFeedback, String> editableGrid = new EditableGrid<ComalatFeedback, String>("grid", getColumns(),
                new EditableListDataProvider<>(retrieveFeedback()), 100, ComalatFeedback.class) {
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
            protected void onDelete(AjaxRequestTarget target, IModel<ComalatFeedback> rowModel) {
                target.add(feedbackPanel);
                projectLogic.deleteComalatFeedback(rowModel.getObject());
            }

            @Override
            protected void onSave(AjaxRequestTarget target, IModel<ComalatFeedback> rowModel) {
                target.add(feedbackPanel);
                projectLogic.saveComalatFeedback(rowModel.getObject());
            }

            @Override
            protected void onAdd(AjaxRequestTarget target, ComalatFeedback rowModel) {
                target.add(feedbackPanel);
                projectLogic.saveComalatFeedback(rowModel);
            }
        };
        add(editableGrid);
        editableGrid.setOutputMarkupPlaceholderTag(true);
    }

    /**
     * @return necessary attributes for the feedback grid
     */
    private List<AbstractEditablePropertyColumn<ComalatFeedback, String>> getColumns() {
        List<AbstractEditablePropertyColumn<ComalatFeedback, String>> columns = new ArrayList<>();

        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Lower Percent"),
                "lowerPercent"));
        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Upper Percent"),
                "upperPercent"));
        columns.add(new RequiredEditableTextFieldColumn<ComalatFeedback, String>(new Model<>("Decision Point"),
                "decisionPoint") {
            private static final long serialVersionUID = 1L;
            public EditableCellPanel getEditableCellPanel(String componentId) {
                return new EditableRequiredDropDownCellPanel<>(componentId, this,
                        Arrays.asList("false", "true"));
            }
        });
        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Feedback (DE)"), "feedbackDE"));
        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Feedback (EN)"), "feedbackEN"));
        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Feedback (ES)"), "feedbackES"));
        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Feedback (AR)"), "feedbackAR"));
        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Feedback (KU)"), "feedbackKU"));

        return columns;
    }

    /**
     * @return all ComalatFeedback from database
     */
    private List<ComalatFeedback> retrieveFeedback() {
        List<ComalatFeedback> allFeedback = projectLogic.getAllComalatFeedback();

        return allFeedback;
    }
}
