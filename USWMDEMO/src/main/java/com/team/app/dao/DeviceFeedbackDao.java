package com.team.app.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.app.domain.TblDeviceFeedback;

public interface DeviceFeedbackDao extends JpaRepository<TblDeviceFeedback, Serializable> {

}
