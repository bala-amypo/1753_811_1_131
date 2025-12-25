package com.example.demo.service.impl;

import com.example.demo.entity.ZoneRestorationRecord;
import com.example.demo.repository.ZoneRestorationRepository;
import org.springframework.stereotype.Service;

import java.util.List;   // ✅ VERY IMPORTANT IMPORT

@Service
public class ZoneRestorationServiceImpl {

    private final ZoneRestorationRepository repo;   // ✅ repo declared

    public ZoneRestorationServiceImpl(ZoneRestorationRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public ZoneRestorationRecord save(ZoneRestorationRecord record) {
        return repo.save(record);
    }

    // READ ALL
    public List<ZoneRestorationRecord> getAll() {
        return repo.findAll();
    }

    // READ BY ID
    public ZoneRestorationRecord getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
