package mum.edu.swe.trailerrentalserver.controller;

import com.sun.org.apache.xpath.internal.operations.Bool;
import mum.edu.swe.trailerrentalserver.domain.Rent;
import mum.edu.swe.trailerrentalserver.domain.RentList;
import mum.edu.swe.trailerrentalserver.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:9999", "http://localhost:8888" }, maxAge = 6000, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class RentController {

    @Autowired
    private RentService rentService;

    @GetMapping("/rents")
    public List<Rent> getAll(Model model){
        System.out.println("getAll ===== Rent");
        return (List<Rent>) rentService.findAll();
    }

    @GetMapping("/rent/{id}")
    public Rent getOne(@PathVariable("id") Long id){
        System.out.println("getOne ===== Rent: " + id);
        return rentService.findById(id);
    }

    @PostMapping("/rent")
    public Rent save(@Valid @RequestBody Rent rent){
        System.out.println("save ===== Rent: " + rent);
        return rentService.save(rent);
    }

    @DeleteMapping("/rent/{id}")
    public Rent delete(@PathVariable("id") Long id){
        Rent rent = rentService.findById(id);
        System.out.println("delete ===== Rent: " + rent);
        rentService.delete(id);
        return rent;
    }

    @GetMapping("/rents/trailerid/{trailerid}")
    public List<Rent> getAllByTrailerId(@PathVariable("trailerid") Long trailerid){
        return rentService.findRentsByTrailerId(trailerid);
    }

    @GetMapping("/rents/extrafields/trailerid/{trailerid}")
    public List<RentList> getAllFieldsByTrailerId(@PathVariable("trailerid") Long trailerid){
        return rentService.findRentList(trailerid);
    }

    @GetMapping("/rents/userid/{userid}")
    public List<Rent> getAllByUserId(@PathVariable("userid") Long userid){
        return rentService.findRentsByUserId(userid);
    }
}
