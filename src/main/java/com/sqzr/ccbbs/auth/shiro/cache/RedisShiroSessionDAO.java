package com.sqzr.ccbbs.auth.shiro.cache;


import com.sqzr.ccbbs.core.cached.ICached;
import com.sqzr.ccbbs.core.util.SerializeUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class RedisShiroSessionDAO extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisShiroSessionDAO.class);
    private ICached cached;
    private String sessionprefix = "ss-";

    public RedisShiroSessionDAO() {
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        try {
            cached.updateCached(session.getId().toString().getBytes(), SerializeUtil.serialize(session), session.getTimeout() / 1000);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Override
    public void delete(Session session) {
        try {
            cached.deleteCached(session.getId().toString().getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Override
    public Collection<Session> getActiveSessions() {
        String keys = sessionprefix + "*";
        List<Session> list = null;
        try {
            list = (List<Session>) cached.getKeys(keys.getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = session.getId();
        try {
            super.assignSessionId(session, sessionprefix + super.generateSessionId(session));
            update(session);
            sessionId = session.getId();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return sessionId;

    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = null;
        try {
            session = (Session) cached.getCached(sessionId.toString().getBytes());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return session;


    }

    public ICached getCached() {
        return cached;
    }

    public void setCached(ICached cached) {
        this.cached = cached;
    }

    public String getSessionprefix() {
        return sessionprefix;
    }

    public void setSessionprefix(String sessionprefix) {
        this.sessionprefix = sessionprefix;
    }

}