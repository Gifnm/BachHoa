package com.spring.main.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "workShift")
public class WorkShift {
	@Id
	@Column(name = "workShiftID")
	private String workShiftID;

	@Column(name = "workShift")
	private String workShift;

	@Column(name = "startTime")
	private Date startTime;

	@Column(name = "endTime")
	private Date endTime;

	public String getWorkShiftID() {
		return workShiftID;
	}

	public void setWorkShiftID(String workShiftID) {
		this.workShiftID = workShiftID;
	}

	public String getWorkShift() {
		return workShift;
	}

	public void setWorkShift(String workShift) {
		this.workShift = workShift;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
