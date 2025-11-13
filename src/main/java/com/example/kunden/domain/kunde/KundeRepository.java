package com.example.kunden.domain.kunde;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface KundeRepository extends JpaRepository<Kunde, Long> {

    @Override
    @NonNull
    @EntityGraph(attributePaths = "adresse")
    List<Kunde> findAll();

    @Override
    @NonNull
    @EntityGraph(attributePaths = "adresse")
    Optional<Kunde> findById(@NonNull Long id);
}
