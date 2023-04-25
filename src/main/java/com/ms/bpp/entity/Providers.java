/*
package com.ms.bpp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "providers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Providers {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "provider_id")
    private String id;
    @Column(name = "provider_code")
    private String code;
    @Column(name = "provider_name")
    private String name;
    @Column(name = "short_desc")
    private String shortDesc;
    @Column(name = "long_desc")
    private String longDesc;
    private MentorProfile mentor;
   // @JsonManagedReference
    @OneToMany(mappedBy = "providers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Categories> categories;
   // @JsonManagedReference
    @OneToMany(mappedBy = "providers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Items> items;
    @OneToMany(mappedBy = "providers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   // @JsonManagedReference
    private List<Fulfillments> fulfillments;
    private String mentorName;
    private String mentorId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Providers)) return false;
        Providers providers = (Providers) o;
        return Objects.equals(id, providers.id) && Objects.equals(code, providers.code) && Objects.equals(name, providers.name) && Objects.equals(shortDesc, providers.shortDesc) && Objects.equals(longDesc, providers.longDesc) && Objects.equals(categories, providers.categories) && Objects.equals(items, providers.items) && Objects.equals(fulfillments, providers.fulfillments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, shortDesc, longDesc, categories, items, fulfillments);
    }
}
*/
