package com.skan.potal.web.potal.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skan.potal.web.potal.address.model.HmMngAddress;


@Repository
public interface HmMngAddressRepository extends JpaRepository<HmMngAddress, Long> {

}
