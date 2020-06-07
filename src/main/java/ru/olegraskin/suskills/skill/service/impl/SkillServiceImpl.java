package ru.olegraskin.suskills.skill.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.skill.repository.SkillRepository;
import ru.olegraskin.suskills.skill.service.SkillService;
import ru.olegraskin.suskills.exception.SkillNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    public Set<Skill> getAll() {
        return new HashSet<>(skillRepository.findAll());
    }

    public Set<Skill> getRoots() {
        return new HashSet<>(skillRepository.findAllRoots());
    }

    @Override
    public Skill getById(@NonNull Long id) {
        Optional<Skill> optional = skillRepository.findById(id);
        return optional.orElseThrow(() -> new SkillNotFoundException(id));
    }

    @Transactional
    @Override
    public Skill save(@NonNull Skill skill) {
        return skillRepository.save(skill);
    }

    @Transactional
    @Override
    public Skill update(@NonNull Skill skill) {
        Optional<Skill> optionalSkill = skillRepository.findById(skill.getId());
        Skill storedSkill = optionalSkill.orElseThrow(() -> new SkillNotFoundException(skill.getId()));

//        Skill.Status status = skill.getStatus();
//        if (status.equals(Skill.Status.PENDING) || status.equals(Skill.Status.APPROVED)) {
//            checkSkillIsCompleted(skill);
//        }
//        storedSkill.setStatus(skill.getStatus());

        storedSkill.setName(skill.getName());
        storedSkill.setDescription(skill.getDescription());
//        storedSkill.setEndDate(skill.getEndDate());
//        storedSkill.setStartDate(skill.getStartDate());
        storedSkill.setChildren(skill.getChildren());
        storedSkill.setParent(skill.getParent());
        return skillRepository.save(storedSkill);
    }

    @Transactional
    @Override
    public void delete(@NonNull Long id) {
        Optional<Skill> skill = skillRepository.findById(id);
        Skill storedSkill = skill.orElseThrow(() -> new SkillNotFoundException(id));
        skillRepository.delete(storedSkill);
    }
}
