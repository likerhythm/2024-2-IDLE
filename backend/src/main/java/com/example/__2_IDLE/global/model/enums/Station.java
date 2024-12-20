package com.example.__2_IDLE.global.model.enums;

import com.example.__2_IDLE.global.exception.RestApiException;
import com.example.__2_IDLE.global.model.Pose;
import com.example.__2_IDLE.task_allocator.model.PickingOrder;
import com.example.__2_IDLE.task_allocator.model.PickingTask;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.__2_IDLE.global.exception.errorcode.TaskErrorCode.STATION_NOT_FOUND;

@Getter
public enum Station {
    STATION_A(1L, "Station A", new Pose(5, -4.5)),
    STATION_B(2L, "Station B", new Pose(-0.5, -4.5)),
    STATION_C(3L, "Station C", new Pose(-6.5, -4.5));

    private final Long id;
    private final String name;
    private Pose pose;
    private List<PickingTask> tasks = new ArrayList<>();

    Station(Long id, String name, Pose pose) {
        this.id = id;
        this.name = name;
        this.pose = pose;
    }

    public static Station getById(Long id) {
        return Arrays.stream(Station.values())
                .filter(station -> station.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RestApiException(STATION_NOT_FOUND));
    }

    public String orderIdsToString() {
        StringBuilder sb = new StringBuilder();
        for (PickingTask pickingTask : tasks) {
            long orderId = pickingTask.getOrderId();
            sb.append(orderId).append(",");
        }
        return sb.toString();
    }

    public void allocateOrder(PickingOrder pickingOrder) {
        tasks.addAll(pickingOrder.getPickingTasks());
    }

    public List<PickingTask> getTasksByItem(Item targetItem) {
        return tasks.stream()
                .filter(task -> task.getItem() == targetItem)
                .filter(task -> !task.isAllocated())
                .collect(Collectors.toList());
    }

    public boolean hasTask(PickingTask task) { // todo: unallocated가 아니라 전체 작업인 Container 대상으로
        return tasks.contains(task);
    }

    public int countUnallocatedTask() {
        return (int) tasks.stream()
                .filter(task -> !task.isAllocated())
                .count();
    }

    public Optional<PickingTask> pickOneUnallocatedTask() {
        return tasks.stream()
                .filter(task -> !task.isAllocated())
                .findFirst();
    }

    public List<PickingTask> completeTask(PickingTask pickingTask) {
        List<PickingTask> removedTasks = tasks.stream()
                .filter(task -> task.getItem().equals(pickingTask.getItem()))
                .filter(PickingTask::isAllocated)
                .toList();

        tasks.removeIf(task -> task.getItem().equals(pickingTask.getItem()));

        return removedTasks;
    }
}
