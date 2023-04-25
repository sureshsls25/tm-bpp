/*
package com.ms.bpp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tags")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Tags {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "tags_id",unique = true)
    private String id;
    boolean display;
    @Column(name = "tags_ds_code")
    private String code;
    @Column(name = "tags_ds_name")
    private String name;
    @OneToMany(mappedBy = "tags",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   // @JsonManagedReference
    private List<ListTagsDescriptor> listTagsDescriptor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tags)) return false;
        Tags tags = (Tags) o;
        return Objects.equals(id, tags.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
*/
