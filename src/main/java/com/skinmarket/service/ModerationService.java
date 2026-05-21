package com.skinmarket.service;

import com.skinmarket.dao.ModerationLogDao;
import com.skinmarket.dao.ModerationLogDaoImpl;
import com.skinmarket.model.ModerationLog;

public class ModerationService {
    private final ModerationLogDao logDao;

    public ModerationService() {
        this.logDao = new ModerationLogDaoImpl();
    }

    public void log(int moderatorId, Integer listingId, Integer articleId, String action, String reason) {
        ModerationLog log = new ModerationLog();
        log.setModeratorId(moderatorId);
        log.setListingId(listingId);
        log.setArticleId(articleId);
        log.setAction(action);
        log.setReason(reason);
        logDao.save(log);
    }
}