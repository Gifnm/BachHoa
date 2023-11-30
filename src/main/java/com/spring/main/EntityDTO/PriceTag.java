package com.spring.main.EntityDTO;

import com.spring.main.model.DiscountDetails;
import com.spring.main.model.ProductPositioning;

import lombok.Data;
@Data
public class PriceTag {
private DiscountDetails discountDetails;
private ProductPositioning productPositioning;
}
