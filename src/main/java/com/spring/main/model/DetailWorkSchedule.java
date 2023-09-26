package com.spring.main.model;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "detailWorkSchedule")
public class DetailWorkSchedule {
    @Id
    @Column(name = "workShiftID")
    private String iworkShiftIDd;

    @ManyToOne
    @MapsId("workShiftID")
    @JoinColumn(name = "workShiftID")
    private WorkShift workShift;

    @ManyToOne
    @MapsId("employeeID")
    @JoinColumn(name = "employeeID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "storeID")
    private Store store;

    @Column(name = "clockIn")
    private Date clockIn;

    @Column(name = "clockOut")
    private Date clockOut;

    @Column(name = "confirm")
    private boolean confirm;

    @Column(name = "shiftAllocationDate")
    private Date shiftAllocationDate;

	public String getId() {
		return iworkShiftIDd;
	}

	public void setId(String id) {
		this.iworkShiftIDd = id;
	}

	public WorkShift getWorkShift() {
		return workShift;
	}

	public void setWorkShift(WorkShift workShift) {
		this.workShift = workShift;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Date getClockIn() {
		return clockIn;
	}

	public void setClockIn(Date clockIn) {
		this.clockIn = clockIn;
	}

	public Date getClockOut() {
		return clockOut;
	}

	public void setClockOut(Date clockOut) {
		this.clockOut = clockOut;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public Date getShiftAllocationDate() {
		return shiftAllocationDate;
	}

	public void setShiftAllocationDate(Date shiftAllocationDate) {
		this.shiftAllocationDate = shiftAllocationDate;
	}

    // Getter và setter
    
}
