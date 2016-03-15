package com.skan.potal.web.potal.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skan.potal.web.potal.address.model.HmEmailInfo;
import com.skan.potal.web.potal.address.model.HmEmailInfoId;


@Repository
public interface HmEmailInfoRepository extends JpaRepository<HmEmailInfo, HmEmailInfoId> {

}
