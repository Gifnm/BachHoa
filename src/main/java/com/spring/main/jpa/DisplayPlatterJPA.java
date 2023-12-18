package com.spring.main.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spring.main.model.DisplayPlatter;

public interface DisplayPlatterJPA extends JpaRepository<DisplayPlatter, Integer>{
	@Query("SELECT o FROM DisplayPlatter o WHERE o.store.storeID = :storeID and o.disPlaID != 0")
	List<DisplayPlatter> getByStoID(@Param("storeID") int storeID);

}
