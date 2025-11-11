package com.example.kunden.web;

import com.example.kunden.domain.kunde.Kunde;
import com.example.kunden.domain.kunde.KundeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/kunden")
public class KundeController {

    private final KundeRepository repo;

    // -------------------------------------------------
    // GET: Alle Kunden abrufen
    // -------------------------------------------------
    @GetMapping
    public List<Kunde> list() {
        return repo.findAll();
    }

    // -------------------------------------------------
    // GET: Einzelnen Kunden per ID abrufen
    // -------------------------------------------------
    @GetMapping("/{id}")
    public Kunde get(@PathVariable long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde mit ID " + id + " nicht gefunden"));
    }

    // -------------------------------------------------
    // POST: Einzelnen Kunden anlegen
    // -------------------------------------------------
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kunde create(@RequestBody Kunde kunde) {
        return repo.save(kunde);
    }

    // -------------------------------------------------
    // POST: Mehrere Kunden gleichzeitig anlegen
    // -------------------------------------------------
    @PostMapping("/bulk")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Kunde> bulkCreate(@RequestBody List<Kunde> kunden) {
        return repo.saveAll(kunden);
    }

    // -------------------------------------------------
    // PUT: Kunden aktualisieren
    // -------------------------------------------------
    @PutMapping("/{id}")
    public Kunde update(@PathVariable long id, @RequestBody Kunde incoming) {
        var kunde = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde mit ID " + id + " nicht gefunden"));

        kunde.setVorname(incoming.getVorname());
        kunde.setNachname(incoming.getNachname());
        kunde.setFirma(incoming.getFirma());
        kunde.setGeburtsdatum(incoming.getGeburtsdatum());
        kunde.setEmail(incoming.getEmail());
        kunde.setTelefonnummer(incoming.getTelefonnummer());
        kunde.setAdresse(incoming.getAdresse());

        return repo.save(kunde);
    }

    // -------------------------------------------------
    // DELETE: Kunden löschen
    // -------------------------------------------------
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Kunde mit ID " + id + " nicht gefunden");
        }
        repo.deleteById(id);
    }
}
