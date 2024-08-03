package com.tarefas.samara.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tarefas.samara.model.Tarefa;

@Repository
public interface RepositoryTarefa extends JpaRepository<Tarefa, Long > {

    
} 
