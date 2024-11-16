package com.example.trabalhoKanban.repository;

import com.example.trabalhoKanban.model.Prioridade;
import com.example.trabalhoKanban.model.Status;
import com.example.trabalhoKanban.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface tarefaRepository extends JpaRepository<Tarefa, Long> {
    List<Tarefa> findByStatus(Status status);
    List<Tarefa> findByPriority(Prioridade priority);
    List<Tarefa> findByDueDateBeforeAndStatusNot(LocalDateTime dueDate, Status status);
}