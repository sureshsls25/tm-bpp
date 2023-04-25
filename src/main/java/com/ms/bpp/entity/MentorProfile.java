package com.ms.bpp.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class MentorProfile {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AboutMentor aboutMentor;
    @OneToMany(mappedBy = "mentorProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Skill> skill;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Education> education;
    private String totalMeetings;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProfessionalExperience> professionalExperience;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserId")
    Users users;
}
