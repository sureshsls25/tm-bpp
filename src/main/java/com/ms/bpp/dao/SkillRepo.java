package com.ms.bpp.dao;

import com.ms.bpp.entity.Skill;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepo extends CrudRepository<Skill, String> {

    List<Skill> getSkillByNameIgnoreCaseContaining(String categoryId);

    List<Skill> findAll(Specification specification);
}
