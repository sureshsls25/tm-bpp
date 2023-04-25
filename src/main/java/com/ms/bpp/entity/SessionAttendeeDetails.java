package com.ms.bpp.entity;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class SessionAttendeeDetails {

    private String name;
    private String age;
    private String gender;
    private String education;
    private String skills;
    private String schoolOrInstitution;
    private String email;
}
