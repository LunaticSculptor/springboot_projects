package com.example.myPractice.service;

import com.example.myPractice.exception.IdNotFoundException;
import com.example.myPractice.model.Task;
import com.example.myPractice.listner.TaskCompletionListner;
import com.example.myPractice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private TaskCompletionListner taskCompletionListner;
    @Autowired
    public TaskService(TaskRepository taskRepository, TaskCompletionListner taskCompletionListner) {
        this.taskRepository = taskRepository;
        this.taskCompletionListner = taskCompletionListner;
    }


//    public List<Task> getTasks() {
//        return taskRepository.getTasks();
//    }
//
//    public Task getTaskById(int id) {
//        return taskRepository.getTaskById(id);
//    }
//
//    public Task saveTask(Task task) {
//        return taskRepository.saveTask(task);
//    }
//
//    public Task updateTask(Task task, int id) {
//        return taskRepository.updateTask(task, id);
//    }
//
//    public String deleteId(int id) {
//        return taskRepository.deleteId(id);
//    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(int id) {
        return taskRepository.findById(id).orElseThrow(() -> new IdNotFoundException("The id is not found: " + id));
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Task task, int id) {
        Task tasktemp = taskRepository.findById(id).orElseThrow(() -> new IdNotFoundException("The id is not found: " + id));
        tasktemp.setStatus(task.getStatus());
        tasktemp.setDescription(task.getDescription());
        tasktemp.setTitle(task.getTitle());
        tasktemp.setDate(task.getDate());
        if(task.getStatus().equalsIgnoreCase("COMPLETED")) {
            taskCompletionListner.publishEvent(id);
        }
        return taskRepository.save(tasktemp);
    }

    public String deleteId(int id) {
        Task tasktemp = taskRepository.findById(id).orElseThrow(() -> new IdNotFoundException("The id is not found: " + id));
        taskRepository.deleteById(id);
        return "deleted: " + id;
    }
}
