package com.spring.main.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.spring.main.model.Authority;

public interface AuthorityJPA extends JpaRepository<Authority, Integer> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM authorities WHERE roleID = ?1 AND employeeID = ?2", nativeQuery = true)
	void deleteByRoleAndEmployeeID(String roleID, Integer employeeID);
}
