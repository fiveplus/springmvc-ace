package com.fiveplus.cache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.ExpiredSessionException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import java.util.Collection;
import java.util.Iterator;

public class SimpleWebSessionManager extends DefaultWebSessionManager{
	private static final String SESSION_USER_KEY = "user";
	private CacheManager cacheManager;
	private final static Log log = LogFactory.getLog(SimpleWebSessionManager.class);
	
	public SimpleWebSessionManager() {
		super();
	}
	
	public void validateSessions() {
		if(log.isInfoEnabled()){
			log.info("Validating all active sessions...");
		}
		int invalidCount = 0;
		Collection<?> activeSessions = getActiveSessions();
		if(activeSessions != null && !activeSessions.isEmpty()){
			for(Iterator<?> it = activeSessions.iterator();it.hasNext();){
				Session session = (Session)it.next();
				try {
					SessionKey key = new DefaultSessionKey(session.getId());
					validate(session, key);					
				} catch (InvalidSessionException e) {
					if(cacheManager != null){
						SimpleSession s = (SimpleSession)session;
						if(s.getAttribute(SESSION_USER_KEY) != null){
							cacheManager.getCache(null).remove(s.getAttribute(SESSION_USER_KEY));
						}
						if(log.isDebugEnabled()){  
							boolean expired = e instanceof ExpiredSessionException;  
		                    String msg = (new StringBuilder()).append(  
		                    			"Invalidated session with id [").append(  
		                                session.getId()).append("]").append(  
		                                expired ? " (expired)" : " (stopped)")
		                                .toString();  
		                    log.debug(msg);  
						}  
						invalidCount++;  
					}
				}
			}
			if(log.isInfoEnabled()){
				String msg = "Finished session validation.";  
	            if(invalidCount > 0){
	                msg = (new StringBuilder()).append(msg).append("  [").append(  
	                        invalidCount).append("] sessions were stopped.")  
	                        .toString();  
	            }else{  
	                msg = (new StringBuilder()).append(msg).append(  
	                        "  No sessions were stopped.").toString();  
	            }
	            log.info(msg);
			}
		}
	}
	
	public void setCacheManager(CacheManager cacheManager) {  
        this.cacheManager = cacheManager;  
    }  
	
}
