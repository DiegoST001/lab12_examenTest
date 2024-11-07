package com.tecsup.petclinic.webs;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visits")
public class VisitController {

    @Autowired
    private VisitService visitService;

    @PostMapping
    public ResponseEntity<Visit> createVisit(@RequestBody Visit visit) {
        Visit createdVisit = visitService.create(visit);
        return ResponseEntity.ok(createdVisit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visit> updateVisit(@PathVariable Long id, @RequestBody Visit visit) {
        visit.setId(id);
        Visit updatedVisit = visitService.update(visit);
        return ResponseEntity.ok(updatedVisit);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable Long id) {
        visitService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visit> getVisitById(@PathVariable Long id) {
        Visit visit = visitService.findById(id);
        if (visit != null) {
            return ResponseEntity.ok(visit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Visit>> getAllVisits() {
        List<Visit> visits = visitService.findAll();
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Visit>> getVisitsByName(@RequestParam String name) {
        List<Visit> visits = visitService.findByName(name);
        return ResponseEntity.ok(visits);
    }
}
