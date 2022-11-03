package kz.bitlab.spring.four.one.spring.demo.four.one.db;

import kz.bitlab.spring.four.one.spring.demo.four.one.models.Task;

import java.util.ArrayList;

public class DBManager {

    static ArrayList<Task> tasks = new ArrayList<>();

    static {
        tasks.add(new Task(1L, "First Task", "This is 1st task", "2020-10-23", true));
        tasks.add(new Task(2L, "Second Task", "This is 2nd task","2020-10-25", true));
        tasks.add(new Task(3L, "Third Task", "This is 3rd task", "2020-10-28", false));
        tasks.add(new Task(4L, "Fourth Task", "This is 4th task","2020-12-12", false));
        tasks.add(new Task(5L, "Fifth Task", "This is 5th task", "2021-05-01", false));
    }

    private static Long id = 6L;

    public static void addTask(Task task){
        task.setId(id);
        tasks.add(task);
        id++;
    }

    public static ArrayList<Task> getAllTasks(){ return tasks; }

    public static Task getTask(Long id){
        for(Task task : tasks){
            if(task.getId() == id){
                return task;
            }
        }
        return null;
    }

    public static void updateTask(Task task){
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).equals(task)){
                tasks.set(i, task);
                break;
            }
        }
    }

    public static void deleteTask(Task task){
        for(int i = 0; i < tasks.size(); i++){
            if(tasks.get(i).equals(task)){
                tasks.remove(task);
                id--;
                for (int j = Math.toIntExact(task.getId()) - 1; j < tasks.size(); j++) { //меняет id всем объектам, находящихся после данного
                    tasks.get(j).setId(tasks.get(j).getId() - 1);
                }
                break;
            }
        }
    }
}
