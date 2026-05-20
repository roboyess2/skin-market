package com.skinmarket.dao;

import com.skinmarket.model.ModerationLog;
import java.util.List;

public interface ModerationLogDao {
    List<ModerationLog> findByModerator(int moderatorId);
    void save(ModerationLog log);
}