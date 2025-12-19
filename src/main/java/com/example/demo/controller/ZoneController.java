package com.example.demo.controller;
import com.example.demo.entity.Zone;
import com.example.demo.service.ZoneService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ZoneController  {
    @Autowired
    ZoneService ser;
    @PostMapping("/PostZone")
    public Zone addZone(@RequestBody Zone Z){
        return ser.addZone(Z);
    }
    @GetMapping("/getZone/{id}")
    public Zone  setZone(@PathVariable int id){
        return ser.setZone(id);
    }
    @DeleteMapping("/deleteZone/{id}")
    public Zone deletZone(@PathVariable int id){
        return ser.deletZone(id);
    }

}



