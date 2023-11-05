package com.spring.main.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(DetailedDeliveryNoteID.class)
@EqualsAndHashCode
@Table(name = "detailed_delivery_note")
public class DetailedDeliveryNote {
	@Id
	private String id;
	@Id
	private String productID;

	@ManyToOne
	@MapsId("id")
	@JoinColumn(name = "id")
	private DeliveryNote deliveryNote;

	@ManyToOne
	@MapsId("productID")
	@JoinColumn(name = "productID")
	private Product product;
	@Column(name = "quantity")
	private int quantity;
}
