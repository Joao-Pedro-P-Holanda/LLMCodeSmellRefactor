package org.example.studyplanner;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// Parameter Object

public class HabitTracker {
    private List<Habit> habits;
    private Map<Integer, List<LocalDateTime>> tracker;
    private Integer nextId;

    private static HabitTracker instance;

    public static HabitTracker getHabitTracker() {
        if (instance == null) {
            instance = new HabitTracker();
        }
        return instance;
    }

    private HabitTracker(){
        this.habits = new ArrayList<>();
        this.tracker = new HashMap<>();
        this.nextId = 1;
    }

    public String getHabitDateViewAll() {
        StringBuilder response = new StringBuilder();
        for(Habit habit : getHabits()){
            response.append(getHabitViewString(habit));
        }
        return response.toString();
    }
    private String getHabitViewString(Habit habit) {
        return "[ Habit: " + habit.getName() + ". Records: " + getHabitRecordDatesAsString(habit) + "]";
    }

    private String getHabitRecordDatesAsString(Habit habit) {
        List<LocalDateTime> records = getHabitRecords(habit.getId());
        StringBuilder recordDates = new StringBuilder();
        for(LocalDateTime record : records){
            recordDates.append(formatHabitDate(record)).append(", ");
        }
        return recordDates.toString();
    }

    @Override
    public String toString() {
        StringBuilder response = new StringBuilder();
        for (Habit habit : habits) {
            response.append(habit.toString()).append(", ");
        }
        return "Habits: " + response.toString();
    }

    public Habit getHabitById(Integer id){
        return this.habits.stream()
                .filter(habit -> Objects.equals(habit.getId(), id))
                .findFirst().orElse(null);
    }

    public List<Habit> getHabits() {
        return this.habits;
    }

    public String formatHabitDate(LocalDateTime date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return date.format(formatter);
    }

    public List<Integer> getTrackerKeys(){
        return this.tracker.keySet().stream().toList();
    }

    public int addHabit(String name, String motivation, LocalTime dailyDedication, LocalDateTime startDate, boolean isConcluded) {
        Habit habit = new Habit(name, motivation, dailyDedication, nextId, startDate, isConcluded);
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
      }
    
      public int handleAddHabitAdapter(List<String> stringProperties, List<Integer> intProperties, boolean isConcluded) {
        if (intProperties.size() < 8) {
            throw new IllegalArgumentException("intProperties list must contain at least 8 elements.");
        }
    
        LocalTime dailyDedication = LocalTime.of(intProperties.get(1), intProperties.get(0)); 
        LocalDateTime startDate = LocalDateTime.of(intProperties.get(2), intProperties.get(3), intProperties.get(4), intProperties.get(5), intProperties.get(6), intProperties.get(7));
        return addHabit(stringProperties.get(0), stringProperties.get(1), dailyDedication, startDate, isConcluded);
      }

    public int addHabit(String name, String motivation) {

        Habit habit = new Habit(name, motivation, this.nextId);
        this.habits.add(habit);
        int response = nextId;
        this.tracker.put(nextId, new ArrayList<>());
        this.nextId++;
        return response;
    }

    public void addHabitRecord(Integer id){
        tracker.get(id).add(LocalDateTime.now());
    }

    public void toggleConcludeHabit(Integer id) {
        for (Habit habit : this.habits) {
            if (habit.getId().equals(id)) {
                habit.setIsConcluded(!habit.getIsConcluded());
            }
        }
    }

    public void removeHabit(Integer id) {
        this.habits.removeIf(habit -> habit.getId().equals(id));
        this.tracker.remove(id);
    }

    public List<LocalDateTime> getHabitRecords(Integer id) {
        return this.tracker.get(id);
    }

    public List<String> searchInHabits(String search){
        List<String> habits = new ArrayList<>();
        for (Habit habit : this.habits) {
            if (habit.getName().toLowerCase().contains(search.toLowerCase()) || habit.getMotivation().toLowerCase().contains(search.toLowerCase())) {
                habits.add(habit.toString());
            }
        }
        return habits;
    }

}
