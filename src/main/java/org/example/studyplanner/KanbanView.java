package org.example.studyplanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KanbanView {
    public enum State {
        TODO, DOING, DONE;
    }

    HabitTracker habitTracker = null;
    TodoTracker todoTracker = null;
    Map<State, List<PlannerMaterial>> kanban = null;

    public KanbanView(HabitTracker habitTracker, TodoTracker todoTracker) {
        this.habitTracker = habitTracker;
        this.todoTracker = todoTracker;
        this.kanban = new HashMap<>();
        this.kanban.put(State.TODO, new ArrayList<>());
        this.kanban.put(State.DOING, new ArrayList<>());
        this.kanban.put(State.DONE, new ArrayList<>());
    }

    public List<PlannerMaterial> getKanbanByState(State state) {
        return kanban.get(state);
    }

    private void addToKanban(State state, Integer id, Class<? extends PlannerMaterial> materialType) throws Exception {
        try {
            PlannerMaterial toAdd = retrieveMaterial(id, materialType);
            if (toAdd == null) {
                throw new Exception(materialType.getSimpleName() + " not found with id: " + id);
            }
            kanban.get(state).add(toAdd);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void addHabitToKanban(State state, Integer id) throws Exception {
        addToKanban(state, id, Habit.class);
    }

    public void addToDoToKanban(State state, Integer id) throws Exception {
        addToKanban(state, id, ToDo.class);
    }

    private PlannerMaterial retrieveMaterial(Integer id, Class<? extends PlannerMaterial> materialType) throws Exception {
        if (materialType.equals(Habit.class)) {
            return habitTracker.getHabitById(id);
        } else if (materialType.equals(ToDo.class)) {
            return todoTracker.getToDoById(id);
        } else {
            throw new Exception("Unsupported material type: " + materialType.getSimpleName());
        }
    }

    public void removeHabitFromKanban(State state, Integer id) throws Exception {
        try {
            Habit toRemove = this.habitTracker.getHabitById(id);
            if (toRemove == null) {
                throw new Exception("No habit found with id: " + id);
            }
            kanban.get(state).remove(toRemove);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void removeToDoFromKanban(State state, Integer id) throws Exception {
        try {
            ToDo toRemove = this.todoTracker.getToDoById(id);
            if (toRemove == null) {
                throw new Exception("No todo found with id: " + id);
            }
            kanban.get(state).remove(toRemove);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String kanbanView() throws Exception {
        try {
            if (kanban.isEmpty()) {
                throw new Exception("No material found");
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            buildKanbanViewBody(sb);
            sb.append("]");
            return sb.toString();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void buildKanbanViewBody(StringBuilder sb) {
        appendKanbanState(sb, State.TODO, "Material ToDo:", "No material found");
        appendKanbanState(sb, State.DOING, "Material in progress:", "No material found");
        appendKanbanState(sb, State.DONE, "Material completed:", "No material found");
    }

    private StringBuilder appendKanbanState(StringBuilder sb, State state, String stateTitle, String noMaterialMessage) {
        sb.append(stateTitle);
        sb.append(System.lineSeparator());
        if (kanban.get(state).isEmpty()) {
            sb.append(noMaterialMessage);
        } else {
            for (PlannerMaterial material : kanban.get(state)) {
                sb.append(", ").append(material.toString());
            }
        }
        sb.append(System.lineSeparator());
        return sb;
    }
}