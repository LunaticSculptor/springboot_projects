package com.example.myPractice.repository;

import com.example.myPractice.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

//    public final List<Task> taskList = new ArrayList<>();
//
//    public List<Task> getTasks() {
//        return taskList;
//    }
//
//    public Task getTaskById(int id) {
//        Task tempTask = new Task();
//        for(Task tk: taskList) {
//            if(tk.getId() == id){
//                return tk;
//            }
//        }
//        return tempTask;
//    }
//
//    public Task saveTask(Task task) {
//        Task tempTask = new Task();
//        tempTask.setId(task.getId());
//        tempTask.setDescription(task.getDescription());
//        tempTask.setDate(task.getDate());
//        tempTask.setTitle(task.getTitle());
//        tempTask.setStatus(task.getStatus());
//
//        taskList.add(tempTask);
//        return tempTask;
//    }
//
//    public Task updateTask(Task task, int id) {
//        for(Task tk: taskList) {
//            if(tk.getId() == id) {
//                tk.setDescription(task.getDescription());
//                tk.setDate(task.getDate());
//                tk.setTitle(task.getTitle());
//                tk.setStatus(task.getStatus());
//                return tk;
//            }
//        }return new Task();
//    }
//
//    public String deleteId(int id) {
//        for(Task tk: taskList) {
//            if(tk.getId() == id){
//                taskList.remove(tk);
//                return "Deleted task with id: " + id;
//            }
//        }return "Incorrect id";
//    }

}
