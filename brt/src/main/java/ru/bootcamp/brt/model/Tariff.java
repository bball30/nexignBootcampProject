package ru.bootcamp.brt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tariffs")
public class Tariff {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tariffId")
    private String tariffId;

    @Column(name = "name")
    private String name;

    @Column(name = "fixed_price")
    private Long fixedPrice;

    @Column(name = "included_minutes")
    private Integer includedMinutes;

    @Column(name = "price_for_minute")
    private Long priceForMinute;

    @Column(name = "incoming_paid")
    private Boolean incomingPaid;

    @Column(name = "outgoing_paid")
    private Boolean outgoingPaid;

    @Column(name = "inside_operator_paid")
    private Boolean insideOperatorPaid;

}
