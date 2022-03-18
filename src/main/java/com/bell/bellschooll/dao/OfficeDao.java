package com.bell.bellschooll.dao;

import com.bell.bellschooll.model.Office;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {
     Office getOfficeById(Integer id);
     void addOffice(Office office);
}
