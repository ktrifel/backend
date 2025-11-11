package com.example.kunden.domain.adresse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name = "adresse")
@Getter @Setter @NoArgsConstructor
public class Adresse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adresse_id")
    private Long adresseId;

    @Column(nullable = false)
    private String strasse;

    @Column(nullable = false)
    private String hausnummer;

    @Column(nullable = false, length = 10)
    private String plz;

    @Column(nullable = false)
    private String stadt;

    @Column(nullable = false)
    private String land;
}