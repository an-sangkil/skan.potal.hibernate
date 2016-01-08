package com.skan.potal.web.potal.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skan.potal.web.potal.address.model.HmAddressPhone;
import com.skan.potal.web.potal.address.model.HmAddressPhoneId;

@Repository
public interface HmAddressPhoneRepository extends JpaRepository<HmAddressPhone, HmAddressPhoneId> {

}
