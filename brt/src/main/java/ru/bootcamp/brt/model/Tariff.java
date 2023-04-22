package ru.bootcamp.brt.model;

import javax.persistence.*;
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

    @Column(name = "tariff_string_id")
    private String tariffId;

    @Column(name = "name")
    private String name;

    @Column(name = "fixed_price")
    private Long fixedPrice;

    @Column(name = "fixed_included_minutes")
    private Long fixedIncludedMinutes;

    @Column(name = "included_price_for_minute")
    private Float includedPriceForMinute;

    @Column(name = "price_for_minute")
    private Float priceForMinute;

    @Column(name = "incoming_paid")
    private Boolean incomingPaid;

    @Column(name = "outgoing_paid")
    private Boolean outgoingPaid;

    @Column(name = "inside_operator_paid")
    private Boolean insideOperatorPaid;

    public Tariff(String tariffId, String name, Long fixedPrice,
                  Long fixedIncludedMinutes,Float includedPriceForMinute, Float priceForMinute, Boolean incomingPaid,
                  Boolean outgoingPaid, Boolean insideOperatorPaid) {
        this.tariffId = tariffId;
        this.name = name;
        this.fixedPrice = fixedPrice;
        this.fixedIncludedMinutes = fixedIncludedMinutes;
        this.includedPriceForMinute = includedPriceForMinute;
        this.priceForMinute = priceForMinute;
        this.incomingPaid = incomingPaid;
        this.outgoingPaid = outgoingPaid;
        this.insideOperatorPaid = insideOperatorPaid;
    }
}
