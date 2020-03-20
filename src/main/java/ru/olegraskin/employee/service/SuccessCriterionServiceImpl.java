package ru.olegraskin.employee.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.olegraskin.employee.domain.Skill;
import ru.olegraskin.employee.domain.SuccessCriterion;
import ru.olegraskin.employee.repository.SkillRepository;
import ru.olegraskin.employee.repository.SuccessCriterionRepository;
import ru.olegraskin.employee.service.exception.SkillNotFoundException;
import ru.olegraskin.employee.service.exception.SuccessCriteriaNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SuccessCriterionServiceImpl implements SuccessCriterionService {

    private final SuccessCriterionRepository successCriterionRepository;

    @Override
    public SuccessCriterion update(@NonNull SuccessCriterion sc) {
        Optional<SuccessCriterion> optional = successCriterionRepository.findById(sc.getId());
        SuccessCriterion stored = optional.orElseThrow(() -> new SuccessCriteriaNotFoundException(sc.getId()));
        stored.setAchieved(sc.isAchieved());
        stored.setName(sc.getName());
        stored.setDescription(sc.getDescription());
        stored.setFinishDate(sc.getFinishDate());
        successCriterionRepository.save(stored);
        return stored;
    }

}
