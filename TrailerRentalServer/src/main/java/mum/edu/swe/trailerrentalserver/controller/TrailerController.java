package mum.edu.swe.trailerrentalserver.controller;

import mum.edu.swe.trailerrentalserver.domain.Trailer;
import mum.edu.swe.trailerrentalserver.service.TrailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:9999", "http://localhost:8888" }, maxAge = 6000, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class TrailerController {

    @Autowired
    private TrailerService trailerService;

    @GetMapping(value ="/trailers")
    public List<Trailer> getAllTrailers(){
        System.out.println("getAllTrailers =====");
        return (List<Trailer>) trailerService.findAll();
    }

    @GetMapping(value ="/trailer/{id}")
    public Trailer getTrailerById(@PathVariable("id") Long Id){
        System.out.println("getTrailerById =====");
        return trailerService.findById(Id);
    }

    @PostMapping(value = "/trailer", produces = "application/json")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public Trailer save(@Valid @RequestBody Trailer trailer) {
        System.out.println("create / update =====");
        trailerService.save(trailer);
        return trailer;
    }

    @DeleteMapping(value ="/trailer/{id}")
    public Trailer delete(@PathVariable("id") Long id) {
        System.out.println("delete =====");
        Trailer trailer = trailerService.findById(id);
        trailerService.delete(id);
        return trailer;
    }

    @GetMapping("/trailers/search")
    public List<Trailer> search(@RequestParam("trailernumber") String number, @RequestParam("statusID") Integer statusID){
        System.out.println("search =====" + number + " - " + statusID);

        if(number == null)
            return trailerService.findAllByStatus(statusID);
        else if(statusID == -1)
            return trailerService.findAllByNumberContains(number);
        else
            return trailerService.findByNumberContainsAndStatus(number, statusID);
    }

    @GetMapping("/trailers/status/{status}")
    public List<Trailer> findAllByStatus(@PathVariable("status") Integer status){
        System.out.println("getAvailableTrailers =====");
        return (List<Trailer>) trailerService.findAllByStatus(status);
    }



}
