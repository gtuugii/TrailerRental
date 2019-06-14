package mum.edu.swe.trailerrentalserver.controller;

import mum.edu.swe.trailerrentalserver.domain.Maintenance;
import mum.edu.swe.trailerrentalserver.service.MaintenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = { "http://localhost:9999", "http://localhost:8888" }, maxAge = 6000, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping("/maintenances")
    public List<Maintenance> getAll(){
        return maintenanceService.findAll();
    }

    @GetMapping("/maintenance/{id}")
    public Maintenance getOne(@PathVariable("id") Long id){
        return maintenanceService.findById(id);
    }

    @PostMapping("/maintenance")
    public Maintenance save(@RequestBody Maintenance maintenance){
        return maintenanceService.save(maintenance);
    }

    @DeleteMapping("maintenance/{id}")
    public Maintenance delete(@PathVariable("id") Long id){
        Maintenance maintenance = maintenanceService.findById(id);
        maintenanceService.delete(id);
        return maintenance;
    }

}
