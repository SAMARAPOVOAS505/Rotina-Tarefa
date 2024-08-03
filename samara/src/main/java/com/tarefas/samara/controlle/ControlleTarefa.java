package com.tarefas.samara.controlle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarefas.samara.model.Tarefa;
import com.tarefas.samara.repository.RepositoryTarefa;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/routine")
public class ControlleTarefa {

    @Autowired
    private RepositoryTarefa repositoryTarefa;

    @GetMapping
    public ResponseEntity<List<Tarefa>> listarRoutine(){
        List<Tarefa> routine = repositoryTarefa.findAll();
        return ResponseEntity.ok(routine);
    }

    @PostMapping
    public ResponseEntity<Tarefa> adicionarTarefa(@RequestBody Tarefa tasks){
        Tarefa novTarefa = repositoryTarefa.save(tasks);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(novTarefa);
    }

     @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizarTarefa(@PathVariable Long id, @RequestBody Tarefa tasks) {
    Tarefa tarefaExistente = repositoryTarefa.findById(id).orElse(null);
            if (tarefaExistente != null) {
                tarefaExistente.setHora(tasks.getHora());
                tarefaExistente.setRotina(tasks.getRotina());
                tarefaExistente.setEquipeTi(tasks.getEquipeTi());
                tarefaExistente.setStatus(tasks.getStatus());
            

                repositoryTarefa.save(tarefaExistente);
                return ResponseEntity.ok(tarefaExistente);
        } else {
           return ResponseEntity.notFound().build();
        }
    }
    
     @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirTarefa(@PathVariable Long id) {
        Tarefa tarefaExistente = repositoryTarefa.findById(id).orElse(null);
        if (tarefaExistente != null) {
            repositoryTarefa.delete(tarefaExistente);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    
    
}
