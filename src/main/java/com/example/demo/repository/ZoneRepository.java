package com.example.demo.repository;

import com.example.demo.entity.Zone;
import java.util.*;

public interface ZoneRepository {

    Zone save(Zone zone);

    Optional<Zone> findById(Long id);

    List<Zone> findAll();
}
