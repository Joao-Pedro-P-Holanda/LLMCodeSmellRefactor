package org.example.studyregistry;

import org.example.studymaterial.Reference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudyTaskManager {
    private static StudyTaskManager instance;
    private StudyMaterial studyMaterial = StudyMaterial.getStudyMaterial();
    List<Registry> registryList;
    List<String> weekResponsibilities = List.of();

    private StudyTaskManager(){
        this.registryList = new ArrayList<Registry>();
    }

    public static StudyTaskManager getStudyTaskManager(){
        if (instance == null) {
            instance = new StudyTaskManager();
        }
        return instance;
    }

    public List<String> getWeekResponsibilities() {
        return weekResponsibilities;
    }

    public void setUpWeek(WeekSetupDTO weekSetup) {
        this.weekResponsibilities = Stream.of(
                weekSetup.planName(),
                weekSetup.objectiveTitle(),
                weekSetup.objectiveDescription(),
                weekSetup.materialTopic(),
                weekSetup.materialFormat(),
                weekSetup.goal(),
                weekSetup.reminderTitle(),
                weekSetup.reminderDescription(),
                weekSetup.mainTaskTitle(),
                weekSetup.mainHabit(),
                weekSetup.mainCardStudy()
        ).collect(Collectors.toList());
    }

    public void handleSetUpWeek(List<String> stringProperties) {
        if (stringProperties.size() != 11) {
            throw new IllegalArgumentException("Invalid number of properties. Expected 11, found " + stringProperties.size());
        }
    
        WeekSetupDTO weekSetup = new WeekSetupDTO(
                stringProperties.get(0), // planName
                stringProperties.get(1), // objectiveTitle
                stringProperties.get(2), // objectiveDescription
                stringProperties.get(3), // materialTopic
                stringProperties.get(4), // materialFormat
                stringProperties.get(5), // goal
                stringProperties.get(6), // reminderTitle
                stringProperties.get(7), // reminderDescription
                stringProperties.get(8), // mainTaskTitle
                stringProperties.get(9), // mainHabit
                stringProperties.get(10) // mainCardStudy
        );
    
        setUpWeek(weekSetup);
    }


    public void addRegistry(Registry registry){
        registryList.add(registry);
    }
    public void removeRegistry(Registry registry){
        registryList.remove(registry);
    }
    public List<Registry> getRegistryList(){
        return registryList;
    }

    public List<String> searchInRegistries(String text){
        List<String> response = new ArrayList<>();
        for(Registry registry : registryList){
            String mix = (registry.getName() != null ? registry.getName() : "");
            if (mix.toLowerCase().contains(text.toLowerCase())){
                response.add(registry.getName());
            }
        }
        return response;
    }

}
