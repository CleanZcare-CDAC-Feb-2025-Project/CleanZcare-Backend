package com.homecleaning.Repository;


import com.homecleaning.Entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PageRepository extends JpaRepository<Page, Long> {

	boolean findByTitle(String page);
}
