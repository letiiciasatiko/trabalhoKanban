package com.example.trabalhoKanban.controller;

import com.example.trabalhoKanban.model.Status;
import com.example.trabalhoKanban.model.Tarefa;
import com.example.trabalhoKanban.service.tarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class tarefaController {
    @Autowired
    private tarefaService TarefaService;

    @PostMapping
    public Tarefa createTask(@RequestBody Tarefa task) {
        return TarefaService.createTask(task);
    }

    @GetMapping
    public Map<Status, List<Tarefa>> getAllTasks() {
        return Map.of(
                Status.TO_DO, TarefaService.getTasksByStatus(Status.TO_DO),
                Status.IN_PROGRESS, TarefaService.getTasksByStatus(Status.IN_PROGRESS),
                Status.DONE, TarefaService.getTasksByStatus(Status.DONE)
        );
    }

    @PutMapping("/{id}/move")
    public Tarefa moveTask(@PathVariable Long id) {
        return TarefaService.moveTask(id);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) {
        TarefaService.deleteTask(id);
    }
}

