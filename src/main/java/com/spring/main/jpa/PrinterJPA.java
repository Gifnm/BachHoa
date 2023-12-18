package com.spring.main.jpa;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.spring.main.model.Printers;

public interface PrinterJPA extends JpaRepository<Printers, String> {
	@Query("SELECT o FROM Printers o WHERE o.store.storeID = :storeID")
	List<Printers> getAllByStoreID(@Param("storeID") int storeID);
}
