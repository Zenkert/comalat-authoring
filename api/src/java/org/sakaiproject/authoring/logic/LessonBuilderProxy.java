package org.sakaiproject.authoring.logic;

import java.util.List;
import java.util.Map;

import org.sakaiproject.lessonbuildertool.SimplePageItem;

/**
 * An interface to abstract the Lessonbuilder related API calls in a central
 * method that can be injected into our app.
 * 
 * @author Sascha Klein (sascha.klein@student.uni-siegen.de)
 * @author Johannes Zenkert (johannes.zenkert@uni-siegen.de)
 *
 */

public interface LessonBuilderProxy {

	public String getStuff();

	public List<SimplePageItem> findItemsInSite(String siteId);

	public Map<String,String> getStiteIdsWithName(String siteId);

	public String getLessonNames(String siteId);

}
