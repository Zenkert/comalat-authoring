package org.sakaiproject.authoring.logic;

/**
 * An interface to abstract the Samigo related API calls in a central
 * method that can be injected into our app.
 * 
 * @author Sascha Klein (sascha.klein@student.uni-siegen.de)
 * @author Johannes Zenkert (johannes.zenkert@uni-siegen.de)
 *
 */

public interface SamigoProxy {
	
	public String doSomething(String site);

	public String getRandomFinalLessonTest(String lesson, String site);

	public String getUserIdividualTest(String user, String site);

	public String getRandomAdditionalLessonTest(String lesson, String site);

}
