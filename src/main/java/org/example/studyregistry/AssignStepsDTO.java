package org.example.studyregistry;

import java.time.LocalDateTime;

public record AssignStepsDTO(
    String firstStep,
    String resetStudyMechanism,
    String consistentStep,
    String seasonalSteps,
    String basicSteps,
    Integer numberOfSteps,
    boolean isImportant,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String mainObjectiveTitle,
    String mainGoalTitle,
    String mainMaterialTopic,
    String mainTask
) { }