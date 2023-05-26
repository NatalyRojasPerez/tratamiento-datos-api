package com.example.demo.service;

import com.example.demo.dto.MascotaRepetida;
import com.example.demo.entidad.Mascota;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PetsService {

    @Autowired
    private RestTemplate restTemplate;

    public PetsService(){
    }

    public ResponseEntity<List<Mascota>> obtenerMascotasPorEstado(String estado) {
        restTemplate.getMessageConverters().add(new Jaxb2RootElementHttpMessageConverter());
        ResponseEntity<List<Mascota>> response = restTemplate.exchange("https://petstore.swagger.io/v2/pet/findByStatus?status=" + estado,
                HttpMethod.GET,null, new ParameterizedTypeReference<List<Mascota>>() {});
        return response;
    }

    public List<MascotaRepetida> obtenerMascotasRepetidas(List<Mascota> mascotas) {
        List<MascotaRepetida> mascotasRepetidas = new ArrayList<>();
        Map<String, Integer> contadorMascotas = new HashMap<>();

        for (Mascota mascota : mascotas) {
            String nombre = mascota.getName();
            if (nombre != null) {
                contadorMascotas.put(nombre, contadorMascotas.getOrDefault(nombre, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : contadorMascotas.entrySet()) {
            String nombreMascota = entry.getKey();
            int cantidad = entry.getValue();
            MascotaRepetida mascotaRepetida = new MascotaRepetida();
            mascotaRepetida.setNombreMascota(nombreMascota);
            mascotaRepetida.setCantidad(cantidad);
            mascotasRepetidas.add(mascotaRepetida);
        }

        return mascotasRepetidas;
    }
}
