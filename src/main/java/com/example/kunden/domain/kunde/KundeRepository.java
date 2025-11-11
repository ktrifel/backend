package com.example.kunden.domain.kunde;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KundeRepository extends JpaRepository<Kunde, Long> {

    @Override
    @EntityGraph(attributePaths = "adresse")
    List<Kunde> findAll();

    @Override
    @EntityGraph(attributePaths = "adresse")
    Optional<Kunde> findById(Long id);

    Optional<Kunde> findByEmail(String email);
}
