package com.example.demo.service;

import com.example.demo.entity.Zone;
public interface ZoneService {

    Zone addZone(Zone z);
    Zone setZone(int id);
    Zone deletZone(int id);
}