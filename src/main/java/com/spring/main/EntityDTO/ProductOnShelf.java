package com.spring.main.EntityDTO;

import com.spring.main.model.ProductPositioning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOnShelf {
	private ProductPositioning productPositioning;
	private int numberOnShelf;
	private int status;
}
