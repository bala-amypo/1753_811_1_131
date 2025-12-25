package com.example.demo.service;

import com.example.demo.entity.ZoneRestorationRecord;

public interface ZoneRestorationService {

    ZoneRestorationRecord restoreZone(Long eventId);
}
