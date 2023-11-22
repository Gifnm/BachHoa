package com.spring.main.EntityDTO;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryNoteWithTotalAmountDTO {
    private String id;
    private Timestamp timeCreate;
    private Timestamp timeCompleted;
    private Double totalAmount;
    private Integer employeeId;
    private String employeeName;
    private String status;
    private Integer productCount;
}
