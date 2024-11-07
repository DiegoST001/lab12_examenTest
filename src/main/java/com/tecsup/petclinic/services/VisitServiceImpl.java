package com.tecsup.petclinic.services;

import com.tecsup.petclinic.entities.Visit;
import com.tecsup.petclinic.exception.VisitNotFoundException;
import com.tecsup.petclinic.repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public Visit create(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Visit update(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public void delete(Long id) throws VisitNotFoundException {
        if (!visitRepository.existsById(id)) {
            throw new VisitNotFoundException("Visit not found with id " + id);
        }
        visitRepository.deleteById(id);
    }

    @Override
    public Visit findById(Long id) throws VisitNotFoundException {
        return visitRepository.findById(id)
                .orElseThrow(() -> new VisitNotFoundException("Visit not found with id " + id));
    }

    @Override
    public List<Visit> findByName(String name) {
        return visitRepository.findByName(name);
    }

    @Override
    public List<Visit> findAll() {
        return visitRepository.findAll();
    }
}
