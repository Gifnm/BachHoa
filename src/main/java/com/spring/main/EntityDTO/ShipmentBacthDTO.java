package com.spring.main.EntityDTO;

import com.spring.main.model.ProductPositioning;
import com.spring.main.model.ShipmentBatchDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ShipmentBacthDTO {
private ShipmentBatchDetail shipmentBatchDetail;
private ProductPositioning productPositioning;

}
