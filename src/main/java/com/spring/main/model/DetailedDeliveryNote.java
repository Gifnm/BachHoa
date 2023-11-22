package com.spring.main.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Entity
@IdClass(DetailedDeliveryNoteID.class)
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

	@OneToOne
	@MapsId("productID")
	@JoinColumn(name = "productID")
	private Product product;

	@Column(name = "quantity")
	private int quantity;

	@Column(name = "`index`")
	private int index;

	@Column(name = "count")
	private int count;
}
