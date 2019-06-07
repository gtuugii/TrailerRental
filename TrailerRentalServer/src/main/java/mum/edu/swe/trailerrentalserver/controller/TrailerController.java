package mum.edu.swe.trailerrentalserver.controller;

import mum.edu.swe.trailerrentalserver.domain.Trailer;
import mum.edu.swe.trailerrentalserver.service.TrailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = { "http://localhost:9999", "http://localhost:8888" }, maxAge = 6000, allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1/trailers")
public class TrailerController {

    @Autowired
    private TrailerService trailerService;

    @GetMapping(value ="/")
    public List<Trailer> getAllTrailers(){
        System.out.println("getAllTrailers =====");
        return (List<Trailer>) trailerService.findAll();
    }

    @GetMapping(value ="/{id}")
    public Trailer getTrailerById(@PathVariable("id") Long Id){
        System.out.println("getTrailerById =====");
        return trailerService.findById(Id);
    }

    @PostMapping(value = "/", produces = "application/json")
    //@ResponseStatus(HttpStatus.ACCEPTED)
    public Trailer save(@Valid @RequestBody Trailer trailer) {
        System.out.println("create / update =====");
        trailerService.save(trailer);
        return trailer;
    }

    @DeleteMapping(value ="/{id}")
    public boolean delete(@PathVariable("id") Long Id) {
        System.out.println("delete =====");
        return trailerService.delete(Id);
    }
}
