package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.main.model.DisplayPlatter;
import com.spring.main.model.DisplayShelves;



public interface DisplayShelvesJPA extends JpaRepository<DisplayShelves, Integer> {
	@Query(value = "SELECT * FROM displayshelves WHERE storeID = ?1", nativeQuery = true)
	List<DisplayShelves> getByStoreID(int storeID);

}
