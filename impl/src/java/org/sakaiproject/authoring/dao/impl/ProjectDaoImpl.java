package org.sakaiproject.authoring.dao.impl;

import org.apache.log4j.Logger;
import org.sakaiproject.authoring.dao.ProjectDao;
import org.sakaiproject.genericdao.hibernate.HibernateGeneralGenericDao;

/**
 * Implementation of ProjectDao from COMALAT Authoring Tool
 * 
 * @author Johannes Zenkert (johannes.zenkert@uni-siegen.de)
 *
 */
public class ProjectDaoImpl extends HibernateGeneralGenericDao implements ProjectDao {

	private static final Logger log = Logger.getLogger(ProjectDaoImpl.class);
	
	public void init() {
		log.info("init()");
	}

	

}
