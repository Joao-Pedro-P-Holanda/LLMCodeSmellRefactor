package org.example.studyregistry;

import java.time.LocalDateTime;
import java.util.List;

public class StudyObjective extends Registry{
    private String title;
    private String description;
    private String topic;
    private Integer practicedDays;
    private LocalDateTime startDate;
    private Double duration;
    private String objectiveInOneLine;
    private String objectiveFullDescription;

    public String getTitle() {
        return title;
    }

    public String getTopic() {
        return topic;
    }

    public Integer getPracticedDays() {
        return practicedDays;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Double getDuration() {
        return duration;
    }

    public String getObjectiveInOneLine() {
        return objectiveInOneLine;
    }

    public String getObjectiveFullDescription() {
        return objectiveFullDescription;
    }

    public String getMotivation() {
        return motivation;
    }

    private String motivation;

    @Override
    public String toString(){
        return "StudyObjective [title:" + title + ", description:" + description + (topic != null ? ", topic:" + topic : "")
                + (practicedDays != null ? ", practicedDays:" + practicedDays : "") + (duration != null ? ", duration:" + duration : "")
                + (objectiveInOneLine != null ? ", objective summary:" + objectiveInOneLine : "") + (objectiveFullDescription != null ? ", objective full description:" + objectiveFullDescription : "")
                + (motivation != null ? ", motivation:" + motivation : "") + "]";
    }
    public StudyObjective(String title, String description) {
        this.title = title;
        this.description = description;
        this.name = title;
    }

    public void handleSetRegistry(Integer id, String name, Integer priority, boolean isActive){
        this.id=id;
        this.name=name;
        this.priority=priority;
        this.isActive=isActive;
    }

    public void handleSetTextualInfo(String title, String description, String topic,String objectiveInOneLine, String objectiveFullDescription, String motivation){
        this.title=title;
        this.description=description;
        this.topic=topic;
        this.objectiveInOneLine=objectiveInOneLine;
        this.objectiveFullDescription=objectiveFullDescription;
        this.motivation=motivation;
    }

    public void handleSetTime(Integer practicedDays, int day, int month, int year, Double duration){
        this.practicedDays=practicedDays;
        this.duration=duration;
        this.startDate= LocalDateTime.of(year, month, day, 0, 0);
    }

    public void handleSetObjective(StudyObjectiveDTO dto) {
        handleSetRegistry(dto.id(), dto.name(), dto.priority(), dto.isActive());
        handleSetTextualInfo(dto.title(), dto.description(), dto.topic(),
                             dto.objectiveInOneLine(), dto.objectiveFullDescription(),
                             dto.motivation());
        handleSetTime(dto.practicedDays(), dto.day(), dto.month(), dto.year(), dto.duration());
    }

    public int handleSetObjectiveAdapter(List<Integer> intProperties, List<String> stringProperties, Double duration, boolean isActive) {
        StudyObjectiveDTO dto = new StudyObjectiveDTO(
                intProperties.get(0), // id
                intProperties.get(1), // priority
                intProperties.get(2), // practicedDays
                intProperties.get(3), // day
                intProperties.get(4), // month
                intProperties.get(5), // year
                stringProperties.get(0), // name
                stringProperties.get(1), // title
                stringProperties.get(2), // description
                stringProperties.get(3), // topic
                stringProperties.get(4), // objectiveInOneLine
                stringProperties.get(5), // objectiveFullDescription
                stringProperties.get(6), // motivation
                duration,
                isActive
        );
    
        handleSetObjective(dto);
        return intProperties.get(0);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
