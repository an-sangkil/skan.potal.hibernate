package com.skan.potal.web.potal.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skan.potal.web.potal.address.model.HmAddressInfo;
import com.skan.potal.web.potal.address.model.HmAddressInfoId;

@Repository
public interface HmAddressInfoRepository extends JpaRepository<HmAddressInfo, HmAddressInfoId> {

}
