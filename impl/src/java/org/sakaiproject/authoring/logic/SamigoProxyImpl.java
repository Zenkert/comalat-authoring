package org.sakaiproject.authoring.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.sakaiproject.memory.api.Cache;
import org.sakaiproject.memory.api.MemoryService;
import org.sakaiproject.tool.assessment.data.dao.assessment.PublishedAssessmentData;
import org.sakaiproject.tool.assessment.data.ifc.assessment.AssessmentIfc;
import org.sakaiproject.tool.assessment.data.ifc.assessment.AssessmentMetaDataIfc;
import org.sakaiproject.tool.assessment.facade.PublishedAssessmentFacade;
import org.sakaiproject.tool.assessment.facade.PublishedAssessmentFacadeQueriesAPI;
import org.sakaiproject.tool.assessment.services.assessment.AssessmentService;
import org.sakaiproject.tool.assessment.services.assessment.PublishedAssessmentService;

import lombok.Getter;
import lombok.Setter;


public class SamigoProxyImpl implements SamigoProxy{
	
	private PublishedAssessmentService pService = new PublishedAssessmentService();
    private AssessmentService assessmentService = new AssessmentService();
    private static Cache assessmentCache = null;
    private static Cache finalLessonTests = null;
   
    @Setter
    MemoryService memService = null;
    
    
    @Getter @Setter
    PublishedAssessmentFacadeQueriesAPI publishedAssessmentFacadeQueries;
    @Getter @Setter
    SakaiProxy sakai;
    
//    public String getAssessmentAlias(Long publishedId) {
//    	try {
//    	    PublishedAssessmentData a = getPublishedAssessment(publishedId);
//    	    if (a == null)
//    		return null;
//    	    else 
//    		return a.getAssessmentMetaDataByLabel("ALIAS");
//    	} catch (Exception ex) {
//    	    System.out.println("exception " + ex);
//    	    return null;
//    	}
//        }
//
//        public String getUrl() {
//            return "/samigo-app/servlet/Login?id=" + getAssessmentAlias(id);
//        }
//     
    
    public void init(){
    	finalLessonTests = memService.getCache("org.sakaiproject.authoring.logic.finalLessonTests");
    	assessmentCache = memService.getCache("org.sakaiproject.authoring.logic.SamProxy");
    }
        
    public String doSomething(String site){
    	String out ="";
    	PublishedAssessmentData data = null;
    	ArrayList<PublishedAssessmentFacade> plist = pService.getBasicInfoOfAllPublishedAssessments2("title", true, site);
    	
    	for(PublishedAssessmentFacade a : plist){
    		if(a.getStatus()==AssessmentIfc.ACTIVE_STATUS){
    			out += "Aktive!\n";
    			out += "Title: " + a.getTitle() +"ID: "+a.getPublishedAssessmentId()+ "\nMetadata: \n";
    			data = publishedAssessmentFacadeQueries.loadPublishedAssessment(a.getPublishedAssessmentId());
				for(Object m:data.getAssessmentMetaDataSet()){
        			AssessmentMetaDataIfc md = (AssessmentMetaDataIfc) m;
        			out += md.getLabel()+": "+md.getEntry()+"\n";
        		}
        		out += "Displayname: "+" Comments: "+a.getComments()+"\n";
        		out += "Description: "+a.getDescription()+"ID: "+a.getAssessmentId()+"\n";
        		out += "Url: /samigo-app/servlet/Login?id="+(data==null?null:data.getAssessmentMetaDataByLabel("ALIAS"));
    		}else{
    			out += "Inaktive \n";
    			out += "Title: " + a.getTitle() +"ID: "+a.getPublishedAssessmentId()+ "\nMetadata: \n";
    			data = publishedAssessmentFacadeQueries.loadPublishedAssessment(a.getPublishedAssessmentId());
        		out += "Displayname: "+" Comments: "+a.getComments()+"\n";
        		if(a.getAssessmentMetaDataSet()!= null){
    				for(Object m:a.getAssessmentMetaDataSet()){
            			AssessmentMetaDataIfc md = (AssessmentMetaDataIfc) m;
            			out += md.getLabel()+": "+md.getEntry()+"\n";
            		}
    			}
        		out += "Description: "+a.getDescription()+"ID: "+a.getAssessmentId()+"\n";
        		out += "Url: /samigo-app/servlet/Login?id="+data==null?null:data.getAssessmentMetaDataByLabel("ALIAS");
    		}
    	}
//    	for(Object o:pService.getAllInActivePublishedAssessments("title")){
//    		PublishedAssessmentFacade a = (PublishedAssessmentFacade) o;
//    		out += "Title: " + a.getTitle() + "\nMetadata: \n";
//    		for(Object m:a.getAssessmentMetaDataSet()){
//    			AssessmentMetaDataIfc md = (AssessmentMetaDataIfc) m;
//    			out += md.getLabel()+": "+md.getEntry()+"\n";
//    		}
//    		a.setActiveStatus(true);
//    		out += "Displayname: "+" Comments: "+a.getComments()+"\n";
//    		out += "Description: "+a.getDescription()+"ID: "+a.getAssessmentId()+"\n";
//    		out += "Url: /samigo-app/servlet/Login?id="+a.getAssessmentMetaDataByLabel("ALIAS");
//    	}
    	
    	return out;
    }
    
    public List getStaticTests(String language){
    	//use cache
    	return null;
    }
    public List getUserTests(String language,String User){
    	return null;
    }

	@Override
	public String getRandomFinalLessonTest(String lesson, String site) {
		// TODO Auto-generated method stub
		
		return getRdmTest(lesson, site, true);
	}
	
	private String getRdmTest(String lesson, String site, boolean useCache){
		ArrayList<String> out  =new ArrayList<String>();
    	PublishedAssessmentData data = null;

   		ArrayList<PublishedAssessmentFacade> plist = pService.getBasicInfoOfAllPublishedAssessments2("title", true, site);

    	
    	for(PublishedAssessmentFacade a : plist){
    		if(a.getStatus()==AssessmentIfc.ACTIVE_STATUS){
				data = getAssessmentData(a.getPublishedAssessmentId(), useCache);
				if(data!= null){
					if(retrieveComalatMetaData(data.getAssessmentMetaDataByLabel("ASSESSMENT_RUBRICS")).get("lesson").equals(lesson)){
						out.add("/samigo-app/servlet/Login?id="+(data==null?null:data.getAssessmentMetaDataByLabel("ALIAS")));
					}
					
				}
        		
    		}
    	}
		Random r = new Random();
    	
    	return out.get(r.nextInt(out.size()));
		
		
	}
	
	private Map<String,String> retrieveComalatMetaData(String data){
		HashMap<String, String> ret = new HashMap<String,String>();
		String[] tupel=data.split(",");
		for(String s : tupel){
			String [] kv = s.trim().split(":");
			ret.put(kv[0], kv[1]);
		}
		return ret;
	}
	
	private PublishedAssessmentData getAssessmentData(Long id, boolean useCache){
		PublishedAssessmentData ret = (PublishedAssessmentData)assessmentCache.get(id.toString());

		if (useCache && ret != null) {
		    return ret;
		}

		try {
		    ret = publishedAssessmentFacadeQueries.loadPublishedAssessment(id);
		    // this will ignore retracted. I think that's right. Students
		    // we show dead and inactive, just not deleted
		    if (ret.getStatus().equals(PublishedAssessmentFacade.DEAD_STATUS)) {
			return null;
		    }
		} catch (Exception e) {
		    return null;
		}

		if (ret != null) {
		    ret.setComments(null);
		    assessmentCache.put(id.toString(), ret);
		}

		return ret;
		
	}

	@Override
	public String getUserIdividualTest(String user, String langauge) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRandomAdditionalLessonTest(String lesson, String siteId) {
		// TODO Auto-generated method stub
		return getRdmTest(lesson, siteId, true);
	}

}
