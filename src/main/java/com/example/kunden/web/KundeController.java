package com.example.kunden.web;

import com.example.kunden.domain.kunde.Kunde;
import com.example.kunden.domain.kunde.KundeRepository;
import lombok.RequiredArgsConstructor;
import java.util.Objects;
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
    public Kunde get(@PathVariable("id") long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Kunde nicht gefunden"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @SuppressWarnings("null")
    public Kunde create(@RequestBody Kunde kunde) {
        return Objects.requireNonNull(repo.save(kunde));
    }

    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    @SuppressWarnings("null")
    public List<Kunde> createBulk(@RequestBody List<Kunde> kunden) {
        return Objects.requireNonNull(repo.saveAll(kunden));
    }

    @PutMapping("/{id}")
    @SuppressWarnings("null")
    public Kunde update(@PathVariable("id") long id, @RequestBody Kunde incoming) {
        var kunde = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Kunde nicht gefunden"));

        if (incoming.getVorname() != null)        kunde.setVorname(incoming.getVorname());
        if (incoming.getNachname() != null)       kunde.setNachname(incoming.getNachname());
        if (incoming.getFirma() != null)          kunde.setFirma(incoming.getFirma());
        if (incoming.getGeburtsdatum() != null)   kunde.setGeburtsdatum(incoming.getGeburtsdatum());
        if (incoming.getEmail() != null)          kunde.setEmail(incoming.getEmail());
        if (incoming.getTelefonnummer() != null)  kunde.setTelefonnummer(incoming.getTelefonnummer());
        if (incoming.getAdresse() != null)        kunde.setAdresse(incoming.getAdresse());

        return Objects.requireNonNull(repo.save(kunde));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        repo.deleteById(id);
    }
}
