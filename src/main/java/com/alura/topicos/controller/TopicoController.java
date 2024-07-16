package com.alura.topicos.controller;

import com.alura.topicos.model.Topico;
import com.alura.topicos.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    // Método GET para obtener todos los topicos
    @GetMapping
    public List<Topico> obtenerTodosLosTopicos() {
        return topicoRepository.findAll();
    }

    // Método POST para crear un nuevo topico
    @PostMapping
    public Topico crearTopico(@RequestBody Topico topico) {
        return topicoRepository.save(topico);
    }

    // Método PUT para actualizar un topico existente
    @PutMapping("/{id}")
    public Topico actualizarTopico(@PathVariable Long id, @RequestBody Topico topicoActualizado) {
        // Buscar el topico por ID
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Topico no encontrado con id " + id));

        // Actualizar los campos necesarios
        topico.setIdUsuario(topicoActualizado.getIdUsuario());
        topico.setMensaje(topicoActualizado.getMensaje());
        topico.setNombreCurso(topicoActualizado.getNombreCurso());
        topico.setTitulo(topicoActualizado.getTitulo());

        // Guardar el topico actualizado
        return topicoRepository.save(topico);
    }

    // Método DELETE para eliminar un topico
    @DeleteMapping("/{id}")
    public void eliminarTopico(@PathVariable Long id) {
        topicoRepository.deleteById(id);
    }

}
