package ru.bootcamp.brt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private double balance;

    @Column(name = "romashka_operator")
    private boolean RomashkaOperator;

    public Abonent(String telNumber, Tariff tariff, double balance, boolean romashkaOperator) {
        this.telNumber = telNumber;
        this.tariff = tariff;
        this.balance = balance;
        this.RomashkaOperator = romashkaOperator;
    }
}
