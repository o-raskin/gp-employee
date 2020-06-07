package ru.olegraskin.suskills.successcriterion.service.impl;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.olegraskin.suskills.successcriterion.domain.SuccessCriterion;
import ru.olegraskin.suskills.exception.ResourceNotFoundException;
import ru.olegraskin.suskills.successcriterion.repository.SuccessCriterionRepository;
import ru.olegraskin.suskills.successcriterion.service.SuccessCriterionService;
import ru.olegraskin.suskills.exception.SuccessCriteriaNotFoundException;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SuccessCriterionServiceImpl implements SuccessCriterionService {

    private final SuccessCriterionRepository successCriterionRepository;

    @Transactional
    @Override
    public SuccessCriterion update(@NonNull SuccessCriterion sc) {
        Optional<SuccessCriterion> optional = successCriterionRepository.findById(sc.getId());
        SuccessCriterion stored = optional.orElseThrow(() -> new SuccessCriteriaNotFoundException(sc.getId()));
        stored.setName(sc.getName());
        stored.setDescription(sc.getDescription());
        successCriterionRepository.save(stored);
        return stored;
    }

    @Override
    public Set<SuccessCriterion> getAllBySkillId(Long skillId) {
        return successCriterionRepository.findAllBySkillId(skillId);
    }

    @Override
    public SuccessCriterion getById(Long id) {
        return successCriterionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Success criterion", "id", id));
    }

}
