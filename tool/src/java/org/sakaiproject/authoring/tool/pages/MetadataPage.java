package org.sakaiproject.authoring.tool.pages;

import codetroopers.wicket.web.datagrid.EditableGrid;
import codetroopers.wicket.web.datagrid.column.AbstractEditablePropertyColumn;
import codetroopers.wicket.web.datagrid.column.RequiredEditableTextFieldColumn;
import codetroopers.wicket.web.datagrid.provider.EditableListDataProvider;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.sakaiproject.authoring.model.ComalatMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Administration Page to CRUD comalat metadata information.
 * Metadata is a crucial attribute for an activity.
 *
 * @author Baris Watzke (baris.watzke@student.uni-siegen.de)
 * @author Johannes Zenkert (johannes.zenkert@uni-siegen.de)
 */

public class MetadataPage extends BasePage {
    private static final long serialVersionUID = 1L;

    public MetadataPage() {
        super();
        disableLink(thirdLink);

        feedbackPanel.setOutputMarkupPlaceholderTag(true);

        EditableGrid<ComalatMetadata, String> editableGrid = new EditableGrid<ComalatMetadata, String>("grid", getColumns(),
                new EditableListDataProvider<>(retrieveMetadata()), 500, ComalatMetadata.class) {
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
            protected void onDelete(AjaxRequestTarget target, IModel<ComalatMetadata> rowModel) {
                target.add(feedbackPanel);
                projectLogic.deleteComalatMetadata(rowModel.getObject());
            }

            @Override
            protected void onSave(AjaxRequestTarget target, IModel<ComalatMetadata> rowModel) {
                target.add(feedbackPanel);
                rowModel.getObject().setLanguage(retrieveSiteLanguage());
                projectLogic.saveComalatMetadata(rowModel.getObject());
            }

            @Override
            protected void onAdd(AjaxRequestTarget target, ComalatMetadata rowModel) {
                target.add(feedbackPanel);
                rowModel.setLanguage(retrieveSiteLanguage());
                projectLogic.saveComalatMetadata(rowModel);
            }
        };
        add(editableGrid);
        editableGrid.setOutputMarkupPlaceholderTag(true);
    }

    /**
     * @return necessary attributes for the metadata grid
     */
    private List<AbstractEditablePropertyColumn<ComalatMetadata, String>> getColumns() {
        List<AbstractEditablePropertyColumn<ComalatMetadata, String>> columns = new ArrayList<>();

        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Metadata Tag"), "metadataTag"));
        columns.add(new RequiredEditableTextFieldColumn<>(new Model<>("Metadata Name"), "metadataName"));

        return columns;
    }

    /**
     * @return all metadata that are in the same language as the current site
     */
    private List<ComalatMetadata> retrieveMetadata() {
        List<ComalatMetadata> allMetadata = projectLogic.getAllComalatMetadataTags();

        return allMetadata.stream().
                filter(p -> p.getLanguage().equals(retrieveSiteLanguage())).collect(Collectors.toList());
    }
}
