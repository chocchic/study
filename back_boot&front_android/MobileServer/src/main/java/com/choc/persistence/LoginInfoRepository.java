package com.choc.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.choc.model.LoginInfo;

public interface LoginInfoRepository extends JpaRepository<LoginInfo, Long>{

}
