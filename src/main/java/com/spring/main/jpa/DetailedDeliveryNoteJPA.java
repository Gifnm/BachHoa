package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.main.model.DeliveryNote;
import com.spring.main.model.DetailedDeliveryNote;
import com.spring.main.model.DetailedDeliveryNoteID;

public interface DetailedDeliveryNoteJPA extends JpaRepository<DetailedDeliveryNote, DetailedDeliveryNoteID>{
	@Query("Select o from DetailedDeliveryNote o where o.id =:id  ORDER BY o.indext ASC")
	List<DetailedDeliveryNote> getAllByID(@Param("id") String id);
	
	List<DetailedDeliveryNote> findByDeliveryNote(DeliveryNote deliveryNote);

}
