package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.Zone;


@Repository
public interface  ZoneRepository extends JpaRepository<Zone,Long>{

}