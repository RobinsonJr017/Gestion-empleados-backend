package com.gestion.empleados.controlador;

import com.gestion.empleados.excepciones.ResourceNotFoundException;
import com.gestion.empleados.modelo.Empleado;
import com.gestion.empleados.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoControlador {

    @Autowired
    private EmpleadoRepositorio repositorio;

    //Lista todos los empleados
    @GetMapping("/empleados")
    public List<Empleado> listarTodosLosEmpleados(){
        return repositorio.findAll();
    }

    //Guarda el Empleado (Metodo)
    @PostMapping("/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        return repositorio.save(empleado);
    }

    //Busca un empleado por Id (Metodo)
    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id) {
        Empleado empleado = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el id: " + id));
        return ResponseEntity.ok(empleado);
    }

    //Aactualiza el empleado (Metodo)
    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Long id, @RequestBody Empleado detallesEmpleado) {
        Empleado empleado = repositorio.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el id: " + id));
        empleado.setNombre(detallesEmpleado.getNombre());
        empleado.setApellido(detallesEmpleado.getApellido());
        empleado.setEmail(detallesEmpleado.getEmail());

        Empleado empleadoActualizado = repositorio.save(empleado);
        return ResponseEntity.ok(empleadoActualizado);
    }

}
