package kz.bitlab.spring.four.one.spring.demo.four.one.controllers;

import kz.bitlab.spring.four.one.spring.demo.four.one.db.DBManager;
import kz.bitlab.spring.four.one.spring.demo.four.one.models.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class MainController {

    @GetMapping(value = "/")
    public String getIndex(Model model) {
        ArrayList<Task> tasks = DBManager.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasklist";
    }

    @GetMapping(value = "/details/{id}")
    public String getDetails(Model model,
                             @PathVariable(name = "id") Long id) {
        Task task = DBManager.getTask(id);
        model.addAttribute("task", task);
        return "details";
    }

    @PostMapping(value = "/addtask")
    public String addTask(@RequestParam(name = "name") String name,
                          @RequestParam(name = "description") String description,
                          @RequestParam(name = "deadlineDate") String deadlineDate) {

        DBManager.addTask(new Task(null, name, description, deadlineDate, false));
        return "redirect:/";
    }

    @PostMapping(value = "/details/{id}/updatetask")
    public String updateTask(@PathVariable(name = "id") Long id,
                             @RequestParam(name = "name") String name,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "deadlineDate") String deadlineDate,
                             @RequestParam(name = "isCompleted") boolean isCompleted) {
        DBManager.updateTask(new Task(id, name, description, deadlineDate, isCompleted));
        return "redirect:/";
    }

    @PostMapping(value = "/deletetask")
    public String deleteTask() {
        return "redirect:/";
    }

}
