package org.mykola.sarafan3.repository;

import org.mykola.sarafan3.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category, Long> {
}
