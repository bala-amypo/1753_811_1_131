package com.example.demo.repository;

import com.example.demo.entity.ZoneRestorationRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRestorationRepository
        extends JpaRepository<ZoneRestorationRecord, Long> {
}
