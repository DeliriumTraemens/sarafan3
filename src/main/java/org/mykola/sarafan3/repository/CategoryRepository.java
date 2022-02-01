package org.mykola.sarafan3.repository;

import org.mykola.sarafan3.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
	List<Category> findByRoot(Boolean bool);
	List<Category> findByRootOrderById(Boolean root);
}
