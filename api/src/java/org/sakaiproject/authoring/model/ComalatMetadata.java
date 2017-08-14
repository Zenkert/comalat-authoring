package org.sakaiproject.authoring.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The Model for the COMALAT Metadata
 *
 * Created by Johannes on 10.08.2016.
 * @author Johannes Zenkert (johannes.zenkert@uni-siegen.de)
 * @author Baris Watzke (baris.watzke@student.uni-siegen.de)
 */

public class ComalatMetadata implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long comalatMetadataId; // primary key - auto-generated
    private String metadataTag;
    private String metadataName;
    private String language;
    private Set<ComalatActivity> comalatActivities = new HashSet<>(0);

    public ComalatMetadata() {

    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getMetadataName() {
        return metadataName;
    }

    public void setMetadataName(String metadataName) {
        this.metadataName = metadataName;
    }

    public String getMetadataTag() {
        return metadataTag;
    }

    public void setMetadataTag(String metadataTag) {
        this.metadataTag = metadataTag;
    }

    public Long getComalatMetadataId() {
        return comalatMetadataId;
    }

    public void setComalatMetadataId(Long comalatMetadataId) {
        this.comalatMetadataId = comalatMetadataId;
    }

    public Set<ComalatActivity> getComalatActivities() {
        return comalatActivities;
    }

    public void setComalatActivities(Set<ComalatActivity> comalatActivities) {
        this.comalatActivities = comalatActivities;
    }
}
