package com.example.demo.service.impl ;

import java.util.HashMap;
import java.util.Map;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Zone;

@Service
public class ZoneServiceImpl implements ZoneService {
    private Map<Integer, Zone> data = new HashMap<>();

    @Override
    public Zone addZone(Zone z) {
        data.put(z.getId().intValue(), z);
        return z;
    }

    @Override
    public Zone setZone(int id) {
        return data.get(id);
    }

    @Override
    public Zone deletZone(int id) {
        return data.remove(id);
    }
}
