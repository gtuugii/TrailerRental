package mum.edu.swe.trailerrentalserver.controller;

import mum.edu.swe.trailerrentalserver.domain.Rent;
import mum.edu.swe.trailerrentalserver.observe.LoggerObserver;
import mum.edu.swe.trailerrentalserver.observe.SaveObserver;
import mum.edu.swe.trailerrentalserver.observe.SendMailObserver;
import mum.edu.swe.trailerrentalserver.observe.Subject;
import mum.edu.swe.trailerrentalserver.service.TrailerService;
import mum.edu.swe.trailerrentalserver.service.observer.AddToRent;
import mum.edu.swe.trailerrentalserver.service.observer.RentCart;
import mum.edu.swe.trailerrentalserver.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:9999", "http://localhost:8888" }, maxAge = 6000, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
@Transactional
public class RentController {

    @Autowired
    private RentService rentService;

    @Autowired
    private TrailerService trailerService;

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

        //observer
        Subject subject = new Subject();
        new SaveObserver(subject);
        new SendMailObserver(subject);
        new LoggerObserver(subject);
        RentCart rentCart = new RentCart(rent);
        AddToRent addToRent = new AddToRent(rentCart);
        subject.setState(addToRent);

        trailerService.updateTrailerStatus(rent.getTrailerId().getTrailerId(), 3);

        return rentService.save(rent);
    }

    @DeleteMapping("/rent/{id}")
    public Rent delete(@PathVariable("id") Long id){
        Rent rent = rentService.findById(id);
        System.out.println("close ===== Rent: " + rent);
        trailerService.updateTrailerStatus(rent.getTrailerId().getTrailerId(), 1);

        //close - rent
        rentService.updateRentStatus(id, 0);
        //rentService.delete(id);
        return rent;
    }

    @GetMapping("/rents/trailerid/{trailerid}")
    public List<Rent> getAllByTrailerId(@PathVariable("trailerid") Long trailerid){
        return rentService.findRentsByTrailerId(trailerid);
    }

    @GetMapping("/rents/userid/{userid}")
    public List<Rent> getAllByUserId(@PathVariable("userid") Long userid){
        return rentService.findRentsByUserId(userid);
    }

    @GetMapping("/rents/search")
    public List<Rent> search(@RequestParam("trailernumber") String number, @RequestParam("statusID") Integer statusID){
        System.out.println("search =====" + number + " - " + statusID);

        if(number == null)
            return rentService.findAllByStatus(statusID);
        else if(statusID == -1)
            return rentService.findAllByNumberContains(number);
        else
            return rentService.findByNumberContainsAndStatus(number, statusID);
    }

    @GetMapping("/rents/status/{status}")
    public List<Rent> findAllByStatus(@PathVariable("status") Integer status){
        System.out.println("findAllByStatus =====");
        return (List<Rent>) rentService.findAllByStatus(status);
    }

}
