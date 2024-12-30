package org.example.studyplanner;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class KanbanViewTest {
    KanbanView kanbanView = null;
    HabitTracker habitTracker = null;
    TodoTracker todoTracker = null;

    List<Integer> toDoIds;
    List<Integer> habitIds;


    @BeforeEach
    void setUp() {
        habitTracker = HabitTracker.getHabitTracker();
        todoTracker = TodoTracker.getInstance();
        toDoIds = new ArrayList<>();
        habitIds = new ArrayList<>();
        kanbanView = new KanbanView(habitTracker, todoTracker);
    }

    @Test
    @DisplayName("Remove Habit Empty Test")
    @Order(1)
    void removeHabitEmpty() {
        try {
            kanbanView.removeHabitFromKanban(KanbanView.State.TODO, 1);
        } catch (Exception e){
            assertNotNull(e);
            assertEquals("No habit found with id: 1", e.getMessage());
        }

    }

    @Test
    @DisplayName("Remove ToDo Empty Test")
    @Order(2)
    void removeToDoEmpty() {
        try {
            kanbanView.removeToDoFromKanban(KanbanView.State.TODO, 1);
        } catch (Exception e){
            assertNotNull(e);
            assertEquals("No todo found with id: 1", e.getMessage());
        }
    }

    @Test
    @DisplayName("Add Habit Don't Exist Test")
    @Order(3)
    void addHabitDontExistToKanban() {
        try {
            kanbanView.addHabitToKanban(KanbanView.State.TODO, -1);
        } catch (Exception e){
            assertNotNull(e);
            assertEquals("Habit not found with id: " + -1, e.getMessage());
        }
    }

    @Test
    @DisplayName("Add ToDo Don't Exist Test")
    @Order(3)
    void addToDoDontExistToKanban() {
        try {
            kanbanView.addToDoToKanban(KanbanView.State.TODO, -1);
        } catch (Exception e){
            assertNotNull(e);
            assertEquals("ToDo not found with id: " + -1, e.getMessage());
        }
    }

    @Test
    @DisplayName("Add Habit Test")
    @Order(4)
    void addHabitToKanban() {
        try {
            int id = habitTracker.addHabit("Habit 1", "Habit Motivation 1");
            Habit habit = habitTracker.getHabitById(id);
            kanbanView.addHabitToKanban(KanbanView.State.TODO, id);
            List<PlannerMaterial> materials = kanbanView.getKanbanByState(KanbanView.State.TODO);
            assertTrue(materials.contains(habit));
        } catch (Exception e){
            fail();
        }
    }

    @Test
    @DisplayName("Add ToDo Test")
    @Order(5)
    void addToDoToKanban() {
        try {
            int id = todoTracker.addToDo("Test Title 1", "Test Description 1", 1);
            ToDo todo = todoTracker.getToDoById(id);
            kanbanView.addToDoToKanban(KanbanView.State.TODO, id);
            List<PlannerMaterial> materials = kanbanView.getKanbanByState(KanbanView.State.TODO);
            assertTrue(materials.contains(todo));
        } catch (Exception e){
            fail();
        }
    }

    @Test
    @DisplayName("Remove Habit Test")
    @Order(6)
    void removeHabitKanban() {
        try {
            kanbanView.addHabitToKanban(KanbanView.State.TODO, 1);
            List<PlannerMaterial> materials = kanbanView.getKanbanByState(KanbanView.State.TODO);
            assertEquals(1, materials.size());
            kanbanView.removeHabitFromKanban(KanbanView.State.TODO, 1);
            materials = kanbanView.getKanbanByState(KanbanView.State.TODO);
            assertTrue(materials.isEmpty());
        } catch (Exception e){
            fail();
        }
    }

    @Test
    @DisplayName("Remove ToDo Test")
    @Order(7)
    void removeToDoKanban() {
        try {
            kanbanView.removeToDoFromKanban(KanbanView.State.TODO, 1);
            List<PlannerMaterial> materials = kanbanView.getKanbanByState(KanbanView.State.TODO);
            assertTrue(materials.isEmpty());
        } catch (Exception e){
            fail();
        }
    }

    @Test
    @DisplayName("Habit View Empty Test")
    @Order(8)
    void habitEmptyView() throws Exception {
        try{
            String response = kanbanView.kanbanView();
            List<String> splitResponse= List.of(response.split(System.lineSeparator()));
            int count = 0;
            for(String str : splitResponse){
                if(str.contains("No material found")){
                    count++;
                }
            }
            assertEquals(3, count);
            assertTrue(response.contains("No material found"));
        } catch (Exception e){
            fail();
        }
    }

    void addMaterials(){
        toDoIds.add(todoTracker.addToDo("Test Title 1", "Test Description 1", 2));
        toDoIds.add(todoTracker.addToDo("Test Title 2", "Test Description 2", 2));
        toDoIds.add(todoTracker.addToDo("Test Title 3", "Test Description 3", 3));

        habitIds.add(habitTracker.addHabit("Habit 1", "Habit Motivation 1"));
        habitIds.add(habitTracker.addHabit("Habit 2", "Habit Motivation 2"));
        habitIds.add(habitTracker.addHabit("Habit 3", "Habit Motivation 3"));

    }

    void addMaterialsToKanban() throws Exception {
        kanbanView.addToDoToKanban(KanbanView.State.TODO, toDoIds.get(0));
        kanbanView.addToDoToKanban(KanbanView.State.DOING, toDoIds.get(1));
        kanbanView.addToDoToKanban(KanbanView.State.DONE, toDoIds.get(2));
        kanbanView.addHabitToKanban(KanbanView.State.TODO, habitIds.get(0));
        kanbanView.addHabitToKanban(KanbanView.State.DOING, habitIds.get(1));
        kanbanView.addHabitToKanban(KanbanView.State.DONE, habitIds.get(2));
    }

    void verifyHabitViewAssertionsHabit(String response){
        assertTrue(response.contains("Habit 1"));
        assertTrue(response.contains("Habit 2"));
        assertTrue(response.contains("Habit 3"));
        assertTrue(response.contains("Habit Motivation 1"));
        assertTrue(response.contains("Habit Motivation 2"));
        assertTrue(response.contains("Habit Motivation 3"));
    }
    void verifyHabitViewAssertionsToDo(String response){
        assertTrue(response.contains("Test Title 1"));
        assertTrue(response.contains("Test Title 2"));
        assertTrue(response.contains("Test Title 3"));
        assertTrue(response.contains("Test Description 1"));
        assertTrue(response.contains("Test Description 2"));
        assertTrue(response.contains("Test Description 3"));
    }

    @Test
    @DisplayName("Habit View Test") // not working
    @Order(8)
    void habitView() throws Exception {
        addMaterials();
        try {
            addMaterialsToKanban();
            String response = kanbanView.kanbanView();
            verifyHabitViewAssertionsHabit(response);
            verifyHabitViewAssertionsToDo(response);
        } catch (Exception e){
            fail();
        }
    }
}