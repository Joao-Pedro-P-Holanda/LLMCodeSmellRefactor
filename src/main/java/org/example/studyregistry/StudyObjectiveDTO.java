package org.example.studyregistry;

import java.time.LocalDate;

public record StudyObjectiveDTO(
        Integer id,
        Integer priority,
        Integer practicedDays,
        int day,
        int month,
        int year,
        String name,
        String title,
        String description,
        String topic,
        String objectiveInOneLine,
        String objectiveFullDescription,
        String motivation,
        Double duration,
        boolean isActive
) {

    // Método para verificar se o objetivo tem prioridade alta (por exemplo, prioridade >= 3)
    public boolean isHighPriority() {
        return priority != null && priority >= 3;
    }

    // Método para obter a data de início como LocalDate
    public LocalDate getStartDate() {
        return LocalDate.of(year, month, day);
    }

    // Método para verificar se o objetivo está atrasado (se a data atual for posterior à data prevista de conclusão)
    public boolean isOverdue() {
        LocalDate endDate = getStartDate().plusDays(duration.longValue());
        return LocalDate.now().isAfter(endDate);
    }

    // Método para obter o nome formatado (em maiúsculas)
    public String getFormattedName() {
        return name.toUpperCase();
    }
}