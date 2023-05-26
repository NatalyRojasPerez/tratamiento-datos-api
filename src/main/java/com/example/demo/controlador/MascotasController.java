package com.example.demo.controlador;

import com.example.demo.dto.MascotaRepetida;
import com.example.demo.entidad.Mascota;
import com.example.demo.service.PetsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
public class MascotasController {

    private final PetsService petsService;

    public MascotasController(PetsService petsService){
        this.petsService = petsService;
    }

    @GetMapping("/vendidas")
    public ResponseEntity<?> mascotasVendidas(){
        ResponseEntity<List<Mascota>> mascotaList = petsService.obtenerMascotasPorEstado("sold");
        return mascotaList;
    }

    @GetMapping("/repetidas")
    public List<MascotaRepetida> mascotasRepetidas(){
        ResponseEntity<List<Mascota>> mascotaList = petsService.obtenerMascotasPorEstado("sold");
        List<Mascota> mascotas = mascotaList.getBody();
        return petsService.obtenerMascotasRepetidas(mascotas);


    }

    @GetMapping("/disponibles")
    public ResponseEntity<?> mascotasDisponibles(){
        ResponseEntity<List<Mascota>> mascotaList = petsService.obtenerMascotasPorEstado("available");
        return mascotaList;
    }
}
