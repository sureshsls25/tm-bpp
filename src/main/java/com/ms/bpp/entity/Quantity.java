/*
package com.ms.bpp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "quantity_id")
public class Quantity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String quantity_id;
    @Column(name = "quantity_available")
    private int available;
    @Column(name = "quantity_allocated")
    private int allocated;
    @OneToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "items_id")
    private Items items;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity)) return false;
        Quantity quantity = (Quantity) o;
        return available == quantity.available && allocated == quantity.allocated && Objects.equals(quantity_id, quantity.quantity_id) && Objects.equals(items, quantity.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(quantity_id, available, allocated, items);
    }
}
*/
