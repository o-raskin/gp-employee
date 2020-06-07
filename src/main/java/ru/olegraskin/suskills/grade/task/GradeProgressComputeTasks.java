package ru.olegraskin.suskills.grade.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.olegraskin.suskills.grade.domain.Grade;
import ru.olegraskin.suskills.user.domain.User;
import ru.olegraskin.suskills.user.service.UserService;
import ru.olegraskin.suskills.userskill.domain.UserSkill;
import ru.olegraskin.suskills.userskill.service.UserSkillService;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class GradeProgressComputeTasks {

    private final UserSkillService userSkillService;
    private final UserService userService;

    /**
     * Invocation computation of grade progress each 30 seconds.
     * Time can be changed.
     */
    @Scheduled(fixedRate = 60000)
    public void computeGradeProgress() {
        Set<User> users = userService.getAll();
        for (User user : users) {

            if (user.getGrade() == null) {
                continue;
            }

            Grade nextGrade = user.getGrade().getNextGrade();
            if (nextGrade != null) {

                // get skills for next grade && userSkillData.status !== APPROVED
                double nextGradeUserSkillsCount = userSkillService.getAllSkillsByUserId(user.getId()).stream()
                        .filter(us -> us.getSkill().getGrade().equals(nextGrade) &&
                                us.getStatus().equals(UserSkill.Status.APPROVED))
                        .map(UserSkill::getSkill)
                        .count();
                double gradeRequiredSkillsCount = nextGrade.getSkills().size();
                int computedProgress = (int)(nextGradeUserSkillsCount / gradeRequiredSkillsCount * 100);

                // if in some case we can get value below 0 or higher that 100, then handle it
                int handledValue = Math.min(Math.max(computedProgress, 0), 100);

                user.setGradeProgress(handledValue);
                userService.update(user);
            }
        }
    }
}
