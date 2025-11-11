package com.example.kunden.web;

import com.example.kunden.domain.kunde.Kunde;
import com.example.kunden.domain.kunde.KundeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kunden")
public class KundeController {

    private final KundeRepository repo;

    @GetMapping
    public List<Kunde> list() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    @SuppressWarnings("null")
    public Kunde get(@PathVariable long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Kunde nicht gefunden"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SuppressWarnings("null")
    public Kunde create(@RequestBody Kunde kunde) {
        return repo.save(kunde);
    }

    @PutMapping("/{id}")
    @SuppressWarnings("null")
    public Kunde update(@PathVariable long id, @RequestBody Kunde incoming) {
        var kunde = repo.findById(id).orElseThrow(() -> new RuntimeException("Kunde nicht gefunden"));
        kunde.setVorname(incoming.getVorname());
        kunde.setNachname(incoming.getNachname());
        kunde.setFirma(incoming.getFirma());
    kunde.setGeburtsdatum(incoming.getGeburtsdatum());
    kunde.setEmail(incoming.getEmail());
        kunde.setTelefonnummer(incoming.getTelefonnummer());
        kunde.setAdresse(incoming.getAdresse());
        return repo.save(kunde);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @SuppressWarnings("null")
    public void delete(@PathVariable long id) {
        repo.deleteById(id);
    }
}