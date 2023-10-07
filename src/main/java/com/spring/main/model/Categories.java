package com.spring.main.model;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoriesID")
    private int categoriesID;

    @Column(name = "categoriesName", nullable = false)
    private String categoriesName;


	public int getCategoriesID() {
		return categoriesID;
	}

	public void setCategoriesID(int categoriesID) {
		this.categoriesID = categoriesID;
	}

	public String getCategoriesName() {
		return categoriesName;
	}

	public void setCategoriesName(String categoriesName) {
		this.categoriesName = categoriesName;
	}
    
}
