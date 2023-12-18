package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.spring.main.model.DisplayShelves;

public interface DisplayShelvesJPA extends JpaRepository<DisplayShelves, Integer> {
	@Query(value = "SELECT * FROM display_shelves WHERE storeID = ?1 and disSheID != 0", nativeQuery = true)
	List<DisplayShelves> getByStoreID(int storeID);

}
