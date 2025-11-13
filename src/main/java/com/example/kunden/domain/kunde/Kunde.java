package com.example.kunden.domain.kunde;

import com.example.kunden.domain.adresse.Adresse;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "kunde")
@Getter @Setter @NoArgsConstructor
@ToString(exclude = "adresse")
public class Kunde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kunden_id")
    private Long kundenId;

    @Column(nullable = false)
    private String vorname;

    @Column(nullable = false)
    private String nachname;

    private String firma;

    private LocalDate geburtsdatum;

    @Column(name = "e_mail", unique = true)
    private String email;

    private String telefonnummer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "adresse_id",
            foreignKey = @ForeignKey(name = "fk_kunde_adresse"))
    private Adresse adresse;
}
