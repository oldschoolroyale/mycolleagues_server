package com.brm.mycollegues.repo;

import com.brm.mycollegues.entity.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MonthRepository extends JpaRepository<Month, Long> {
    Optional<Month>findByMonthNumberAndYearNumber(Integer month, Integer year);
}
