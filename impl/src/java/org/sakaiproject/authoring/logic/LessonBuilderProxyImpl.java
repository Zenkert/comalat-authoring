package org.sakaiproject.authoring.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.sakaiproject.lessonbuildertool.LessonBuilderAccessAPI;
import org.sakaiproject.lessonbuildertool.SimplePage;
import org.sakaiproject.lessonbuildertool.SimplePageItem;
import org.sakaiproject.lessonbuildertool.model.SimplePageToolDao;

import lombok.*;

public class LessonBuilderProxyImpl implements LessonBuilderProxy {

	
	@Getter @Setter 
	private LessonBuilderAccessAPI lessonBuilder;
	@Getter @Setter
	private SimplePageToolDao dao;
	
	@Override
	public String getStuff() {
		return lessonBuilder.getHttpAccess().toString();
	}
	
	public List<SimplePageItem> findItemsInSite(String siteId){
		return dao.findItemsInSite(siteId);
	}
	
	public Map<String,String> getStiteIdsWithName(String siteId){
		Map<String,String> map = new HashMap<String,String>();
		List<SimplePageItem> itemsFromSite = (ArrayList<SimplePageItem>) dao.findItemsInSite(siteId);
		for(SimplePageItem spi: itemsFromSite){
			map.put( dao.getPage(spi.getPageId()).getSiteId(),spi.getName());
		}
		return map;
	}

	public String getPageID(String arg0){
//		return dao.getPage(dao.findItemsInSite(arg0).getPageId()).getOwner();
		String out = "";
		for(SimplePage page : dao.getSitePages(arg0)){
			page.getToolId();
			out += "Site: "+page.getSiteId()+" ";
		}
		return out;
	}
	
	public String getLessonNames(String arg0){
		String out = "";
		
		//gibt lessonbuilder items aus
//		for(SimplePageItem spi : dao.findItemsInSite(arg0)){
//			
//		}
//		if(spi.getNextPage()){
//			out += "<p Item: "+dao.findItem(spi.getId()+1)+"/><br/>";
//		}
//		HashSet<String> toolIds = new HashSet<String>();
//		for(SimplePage page : dao.getSitePages(arg0)){
//			out += page.getPageId()+" "+page.getSiteId()+"\n";
//		}
//		for(String s : toolIds){
//			out += dao.getPage(dao.getTopLevelPageId(s)).getSiteId();
//		}
		
		out += dao.findItemsInSite(arg0).size()+"\n";
		
		for(SimplePageItem spiParent : dao.findItemsInSite(arg0)){
			out += spiParent.getName()+" "+spiParent.getSakaiId()+"\n";
			for(SimplePageItem spi : dao.findItemsInSite(spiParent.getSakaiId())){
				out +="Site: "+ dao.getPage(spi.getPageId()).getSiteId();
				out +=" ID: "+spi.getId();
				out +=" Next: "+spi.getGroups();
				out +=" Lesson: "+spiParent.getName();
				out +=" Name: "+spi.getName();
				out +=" SakaiID: "+spi.getSakaiId();
//				out +=" Tool UUID: "+(p!=null?p.getToolId():"null")+"\n";
			}
		}
		
		return out;
	}



}
