package ru.olegraskin.suskills.userskill.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.grade.domain.Grade;
import ru.olegraskin.suskills.grade.service.GradeService;
import ru.olegraskin.suskills.skill.domain.Skill;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.user.service.UserService;
import ru.olegraskin.suskills.userskill.domain.UserSkill;
import ru.olegraskin.suskills.userskill.repository.UserSkillRepository;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserSkillStatusComputeTasks {

    private final UserSkillRepository userSkillRepository;
    private final GradeService gradeService;

    public void approveAllLearnedSkills(User user) {

        Grade currentUserGrade = user.getGrade();
        if (currentUserGrade != null) {

            Set<Skill> skills = gradeService.getAllSkillsNeedToKnowForGrade(currentUserGrade);
            Set<Skill> userLearnedApprovedSkills = user.getSkills().stream()
                    .filter(s -> s.getStatus().equals(UserSkill.Status.APPROVED))
                    .map(UserSkill::getSkill)
                    .collect(Collectors.toSet());
            skills.removeAll(userLearnedApprovedSkills);

            Set<UserSkill> needToUpdateSkills = skills.stream()
                    .map(s -> {
                        UserSkill userSkill = new UserSkill(user, s);
                        if (userSkill.getStartDate() == null) {
                            userSkill.setStartDate(LocalDate.now());
                        }
                        userSkill.setEndDate(LocalDate.now());
                        userSkill.setStatus(UserSkill.Status.APPROVED);
                        userSkill.setLastEditDate(LocalDate.now());

                        // System id
                        userSkill.setEditorId(0L);
                        return userSkill;
                    })
                    .collect(Collectors.toSet());

            userSkillRepository.saveAll(needToUpdateSkills);
        }
    }
}
