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

    // Explicit getters/setters for IDEs that don't run Lombok annotation processing
    public Long getKundenId() {
        return this.kundenId;
    }

    public void setKundenId(Long kundenId) {
        this.kundenId = kundenId;
    }

    public String getVorname() {
        return this.vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return this.nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getFirma() {
        return this.firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

    public LocalDate getGeburtsdatum() {
        return this.geburtsdatum;
    }

    public void setGeburtsdatum(LocalDate geburtsdatum) {
        this.geburtsdatum = geburtsdatum;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefonnummer() {
        return this.telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public Adresse getAdresse() {
        return this.adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }
}
