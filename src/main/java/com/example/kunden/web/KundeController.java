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
    public Kunde get(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Kunde nicht gefunden"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kunde create(@RequestBody Kunde kunde) {
        return repo.save(kunde);
    }

    @PutMapping("/{id}")
    public Kunde update(@PathVariable Long id, @RequestBody Kunde incoming) {
        var kunde = repo.findById(id).orElseThrow(() -> new RuntimeException("Kunde nicht gefunden"));
        kunde.setVorname(incoming.getVorname());
        kunde.setNachname(incoming.getNachname());
        kunde.setFirma(incoming.getFirma());
        kunde.setGeburtsdatum(incoming.getGeburtsdatum());
        kunde.setEMail(incoming.getEMail());
        kunde.setTelefonnummer(incoming.getTelefonnummer());
        kunde.setAdresse(incoming.getAdresse());
        return repo.save(kunde);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}