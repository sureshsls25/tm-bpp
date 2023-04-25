/*
package com.ms.bpp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "list_tags")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class ListTagsDescriptor {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "list_tags_ds_id")
    private String id;
    @Column(name = "list_tags_ds_code")
    private String code;
    @Column(name = "list_tags_ds_name",length = 2000)
    private String name;
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "tags_id")
  //  @JsonBackReference
    private Tags tags;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ListTagsDescriptor)) return false;
        ListTagsDescriptor that = (ListTagsDescriptor) o;
        return Objects.equals(id, that.id) && Objects.equals(code, that.code) && Objects.equals(name, that.name) && Objects.equals(tags, that.tags);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, tags);
    }
}
*/
