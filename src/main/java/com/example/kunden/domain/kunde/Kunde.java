package com.example.kunden.domain.kunde;

import com.example.kunden.domain.adresse.Adresse;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "kunde")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "adresse")
public class Kunde {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kunden_id")
    private Long kundenId;

    @NotBlank(message = "Vorname darf nicht leer sein")
    @Column(nullable = false)
    private String vorname;

    @NotBlank(message = "Nachname darf nicht leer sein")
    @Column(nullable = false)
    private String nachname;

    private String firma;

    private LocalDate geburtsdatum;

    @Email(message = "Ungültige E-Mail-Adresse")
    @Column(name = "e_mail", unique = true)
    private String email;

    private String telefonnummer;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(
        name = "adresse_id",
        foreignKey = @ForeignKey(name = "fk_kunde_adresse")
    )
    private Adresse adresse;

    // Lombok will generate getters/setters at compile time
}
