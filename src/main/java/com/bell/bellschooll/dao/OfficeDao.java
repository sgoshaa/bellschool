package com.bell.bellschooll.dao;

import com.bell.bellschooll.dto.request.OfficeInListDto;
import com.bell.bellschooll.model.Office;
import com.bell.bellschooll.model.Organization;

import java.util.List;

/**
 * DAO для работы с Office
 */
public interface OfficeDao {
    Office getOfficeById(Integer id);

    void addOffice(Office office);

    List<Office> getListOffice(OfficeInListDto office, Organization organization);

    void updateOffice(Office office);
}
