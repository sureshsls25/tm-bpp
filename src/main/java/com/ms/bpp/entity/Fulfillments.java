/*
package com.ms.bpp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "fulfillments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
public class Fulfillments {
    @Id
    @Column(name = "fulfilments_id")
    private String id;
    @ElementCollection(targetClass = String.class)
    private List<String> language;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "start_end")
    private String startEnd;
    private String label;
    private String type;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tags> tags;
    private String agentPersonName;
    private String agentPersonId;
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "provider_id",referencedColumnName = "provider_id")
   // @JsonBackReference
    private Providers providers;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fulfillments)) return false;
        Fulfillments that = (Fulfillments) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
*/
