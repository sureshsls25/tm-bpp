package com.ms.bpp.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UsersDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String mobile;

    @JsonProperty("address")
    private AddressDto address;
    private String type;

    private MentorProfileDto mentorProfile;
}
