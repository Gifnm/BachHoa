package com.spring.main.EntityDTO;

import java.util.List;

public class DeliveryNoteDTO {
    private int storeID;
    private int employeeID;
    private List<DetailedDeliveryNoteDTO> detailedDeliveryNotes;

    public int getStoreID() {
        return this.storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getEmployeeID() {
        return this.employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public List<DetailedDeliveryNoteDTO> getDetailedDeliveryNotes() {
        return this.detailedDeliveryNotes;
    }

    public void setDetailedDeliveryNotes(List<DetailedDeliveryNoteDTO> detailedDeliveryNotes) {
        this.detailedDeliveryNotes = detailedDeliveryNotes;
    }

}
