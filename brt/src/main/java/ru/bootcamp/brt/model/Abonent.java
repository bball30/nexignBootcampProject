package ru.bootcamp.brt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "abonents")
public class Abonent {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tel_number")
    private String telNumber;

    @ManyToOne
    @JoinColumn(name = "tariff_id", referencedColumnName = "id")
    private Tariff tariff;

    @Column(name = "balance")
    private Long balance;

    public Abonent(String telNumber, Tariff tariff, Long balance) {
        this.telNumber = telNumber;
        this.tariff = tariff;
        this.balance = balance;
    }
}