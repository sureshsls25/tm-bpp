/*
package com.ms.bpp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Items {
    @Column(name = "item_id")
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @Column(name = "categories_id")
    @ElementCollection(targetClass = String.class)
    private List<String> categories;
    @Column(name = "item_code")
    private String code;
    @Column(name = "item_name")
    private String name;
    @Column(name = "item_short_desc")
    private String shortDesc;
    @Column(name = "item_long_desc")
    private String longDesc;
    @OneToOne(mappedBy = "items",cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Quantity.class)
    Quantity quantity;
    private String price;
    @Column(name = "fulfillment_ids")
    @ElementCollection(targetClass = String.class)
    private List<String> fulfillments;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   // @JoinColumn(name = "tags_id",referencedColumnName = "item_id")
    private List<Tags> tags;
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_id")
  //  @JsonBackReference
    private Providers providers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Items)) return false;
        Items items = (Items) o;
        return Objects.equals(id, items.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
*/
