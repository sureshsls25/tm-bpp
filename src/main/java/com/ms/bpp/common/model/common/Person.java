package com.ms.bpp.common.model.common;

import lombok.Data;

import java.util.List;

@Data
public class Person {
	private String id;
    private String mailid;
	private String url;
    private String name;
    private Image image;
    private String age;
    private String dob;
    private String gender;
    private String schoolOrInstitution;
    private List<Credential> creds;
    private List<Language> languages;
    private List<Skill> skills;
    private List<TagGroup> tags;
}
