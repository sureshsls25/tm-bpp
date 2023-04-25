package com.ms.bpp.builder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ms.bpp.entity.Users;
import com.ms.common.dto.*;
import com.ms.common.enums.AckStatus;
import com.ms.common.model.common.*;
import com.ms.common.model.response.Response;
import com.ms.common.model.response.ResponseMessage;
import com.ms.common.model.search.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResponseBuilder {

    @Value("${beckn.seller.url}")
    private String sellerUrl;

    @Autowired
    ObjectMapper objectMapper;

    public Context buildContext(Context context, String action) {
        context.setAction(action);
        context.setBppUri(this.sellerUrl);
        return context;
    }


    public ResponseEntity<String> sendAck(SearchRequest searchRequest) throws JsonProcessingException {
        Response response = new Response();
        ResponseMessage resMsg = new ResponseMessage();
        resMsg.setAck(new Ack(AckStatus.ACK));
        response.setMessage(resMsg);
        return new ResponseEntity<>(objectMapper.writeValueAsString(response), HttpStatus.OK);
    }

    public List<Provider> getUsersDetailsForSearch(List<Users> usersList) {
        List<Provider> listProviders = new ArrayList<>();
        usersList.forEach(users -> {
            Provider provider = new Provider();
            UsersDTO usersDTO = new UsersDTO();
            usersDTO.setId(users.getUserId());
            usersDTO.setFirstName(users.getFirstName());
            usersDTO.setLastName(users.getLastName());
            MentorProfileDto mentorProfileDto = new MentorProfileDto();
            AboutMentorDto aboutMentorDto = new AboutMentorDto();
            aboutMentorDto.setShortDesc(users.getMentorProfile().getAboutMentor().getShortDesc());
            mentorProfileDto.setAboutMentor(aboutMentorDto);
            List<SkillDto> skillDtos = new ArrayList<>();
            users.getMentorProfile().getSkill().forEach(skill -> {
                SkillDto skillDto = new SkillDto();
                skillDto.setName(skill.getName());
                skillDtos.add(skillDto);
            });
            mentorProfileDto.setSkill(skillDtos);
            AddressDto addressDto = new AddressDto();
            addressDto.setCity(users.getAddress().getCity());
            usersDTO.setMentorProfile(mentorProfileDto);
            usersDTO.setAddress(addressDto);

            provider.setSkill(usersDTO.getMentorProfile().getSkill());
            provider.setAboutMentor(usersDTO.getMentorProfile().getAboutMentor());
            Agent agent = new Agent();
            Person person = new Person();
            person.setName(usersDTO.getFirstName() + " " + usersDTO.getLastName());
            person.setId(usersDTO.getId());
            agent.setPerson(person);
            AddressDto dto = new AddressDto();
            dto.setCity(usersDTO.getAddress().getCity());
            agent.setAddress(dto);
            provider.setAgent(agent);
            listProviders.add(provider);
        });
        return listProviders;
    }

    public Provider getFullMentorDetails(Optional<Users> users) {
        Provider provider = new Provider();
        if (users.isPresent()) {
            UsersDTO usersDTO = new UsersDTO();
            usersDTO.setId(users.get().getUserId());
            usersDTO.setFirstName(users.get().getFirstName());
            usersDTO.setLastName(users.get().getLastName());
            MentorProfileDto mentorProfileDto = new MentorProfileDto();
            AboutMentorDto aboutMentorDto = new AboutMentorDto();
            aboutMentorDto.setShortDesc(users.get().getMentorProfile().getAboutMentor().getShortDesc());
            aboutMentorDto.setLongDesc(users.get().getMentorProfile().getAboutMentor().getLongDesc());
            mentorProfileDto.setAboutMentor(aboutMentorDto);
            List<SkillDto> skillDtos = new ArrayList<>();
            users.get().getMentorProfile().getSkill().forEach(skill -> {
                SkillDto skillDto = new SkillDto();
                skillDto.setName(skill.getName());
                skillDtos.add(skillDto);
            });
            mentorProfileDto.setSkill(skillDtos);
            AddressDto addressDto = new AddressDto();
            addressDto.setCity(users.get().getAddress().getCity());
            usersDTO.setMentorProfile(mentorProfileDto);
            usersDTO.setAddress(addressDto);

            provider.setSkill(usersDTO.getMentorProfile().getSkill());
            provider.setAboutMentor(usersDTO.getMentorProfile().getAboutMentor());
            EducationDto educationDto = new EducationDto();
            List<EducationDto> educationDtoList = new ArrayList<>();
            users.get().getMentorProfile().getEducation().forEach(education -> {
                educationDto.setInstitutionName(education.getInstitutionName());
                educationDto.setQualification(education.getQualification());
                educationDtoList.add(educationDto);
            });
            provider.setEducation(educationDtoList);
            List<ProfessionalExperienceDto> professionalExperienceDtos = new ArrayList<>();
            users.get().getMentorProfile().getProfessionalExperience().forEach(professionalExperience -> {
                ProfessionalExperienceDto professionalExperienceDto = new ProfessionalExperienceDto();
                professionalExperienceDto.setExperience(professionalExperience.getExperience());
                professionalExperienceDtos.add(professionalExperienceDto);
            });
            provider.setProfessionalExperience(professionalExperienceDtos);
            Agent agent = new Agent();
            Person person = new Person();
            person.setName(usersDTO.getFirstName() + " " + usersDTO.getLastName());
            person.setId(usersDTO.getId());
            agent.setPerson(person);
            AddressDto dto = new AddressDto();
            dto.setCity(usersDTO.getAddress().getCity());
            agent.setAddress(dto);
            provider.setAgent(agent);
        }
        return provider;
    }
}
