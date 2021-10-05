package com.spend.track.transaction.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryMapping, Long> {
    public Optional<CategoryMapping> findByDescription(String desc);
}
