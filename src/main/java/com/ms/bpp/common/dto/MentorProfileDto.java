package com.ms.bpp.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class MentorProfileDto {
    private AboutMentorDto aboutMentor;
    private List<SkillDto> skill;
    private List<EducationDto> education;
    private String totalMeetings;
    private List<ProfessionalExperienceDto> professionalExperience;
}
