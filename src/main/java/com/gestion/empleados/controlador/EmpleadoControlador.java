package com.gestion.empleados.controlador;

import com.gestion.empleados.modelo.Empleado;
import com.gestion.empleados.repositorio.EmpleadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
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

    //Guarda el Empleado
    @PostMapping("/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        return repositorio.save(empleado);
    }

}
