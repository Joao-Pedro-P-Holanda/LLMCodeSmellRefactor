package org.example.studyplanner;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ToDo implements PlannerMaterial {

    private Integer id;
    private String title;
    private String description;
    private int priority;
    private List<LocalDateTime> executionTimes;

    public ToDo(Integer id, String title, String description, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.executionTimes = new ArrayList<>();
        validateInput(); // Call validation logic
    }

    @Override
    public String toString() {
        return MessageFormat.format("[(Priority:{3}) ToDo {0}: {1}, {2}]", id, title, description, priority);
    }

    // **Methods for formatted details and individual attributes**

    public String formatDetails() {
        return MessageFormat.format("{0}: {1}, {2}", title, description, priority);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        validateTitle(title); // Call specific validation
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        validateDescription(description); // Call specific validation
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
        validatePriority(priority); // Call specific validation
    }

    // **Execution time tracking**

    public void addExecutionTime() {
        executionTimes.add(LocalDateTime.now());
    }

    public List<String> getExecutionTimesAsString() {
        List<String> formattedTimes = new ArrayList<>();
        if (executionTimes.isEmpty()) {
            return Collections.singletonList("No tracks found");
        }
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        for (LocalDateTime ldt : executionTimes) {
            formattedTimes.add(formatter.format(ldt));
        }
        return formattedTimes;
    }

    // **Extracted validation methods**

    private void validateInput() {
        validateId(id);
        validateTitle(title);
        validateDescription(description);
        validatePriority(priority);
    }

    private void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID deve ser um número inteiro positivo.");
        }
    }

    private void validateTitle(String title) {
        if (Objects.isNull(title) || title.isBlank()) {
            throw new IllegalArgumentException("O título não pode ser nulo ou vazio.");
        }
    }

    private void validateDescription(String description) {
        if (Objects.isNull(description) || description.isBlank()) {
            throw new IllegalArgumentException("A descrição não pode ser nula ou vazia.");
        }
        // You can add more validations here, like maximum length, allowed characters, etc.
    }

    private void validatePriority(int priority) {
        if (priority < 1 || priority > 5) {
            throw new IllegalArgumentException("A prioridade deve estar entre 1 e 5.");
        }
    }
}