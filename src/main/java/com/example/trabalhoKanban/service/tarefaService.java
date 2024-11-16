package com.example.trabalhoKanban.service;

import com.example.trabalhoKanban.model.Prioridade;
import com.example.trabalhoKanban.model.Status;
import com.example.trabalhoKanban.model.Tarefa;
import com.example.trabalhoKanban.repository.tarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class tarefaService {
    @Autowired
    private tarefaRepository TarefaRepository;

    public Tarefa createTask(Tarefa task) {
        task.setCreationDate(LocalDateTime.now());
        task.setStatus(Status.TO_DO);
        return TarefaRepository.save(task);
    }

    public List<Tarefa> getTasksByStatus(Status status) {
        return TarefaRepository.findByStatus(status);
    }

    public Tarefa moveTask(Long taskId) {
        Tarefa task = TarefaRepository.findById(taskId).orElseThrow();
        task.setStatus(task.getStatus() == Status.TO_DO ? Status.IN_PROGRESS : Status.DONE);
        return TarefaRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        TarefaRepository.deleteById(taskId);
    }

    public List<Tarefa> getTasksByPriority(Prioridade priority) {
        return TarefaRepository.findByPriority(priority);
    }

    public List<Tarefa> getOverdueTasks() {
        return  TarefaRepository.findByDueDateBeforeAndStatusNot(LocalDateTime.now(), Status.DONE);
    }
    
}
