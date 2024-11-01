package com.cesarschool.autolinehonda.controller;

import com.cesarschool.autolinehonda.model.Carro;
import com.cesarschool.autolinehonda.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
public class CarroController
{
    private final CarroService carroService;

    public CarroController(CarroService carroService)
    {
        this.carroService = carroService;
    }

    @GetMapping
    public List<Carro> getAllCarros()
    {
        return carroService.findAll();
    }

    @GetMapping("/{chassi}")
    public ResponseEntity<Carro> getCarroById(@PathVariable String chassi)
    {
        Carro carro = carroService.findById(chassi);
        return carro != null ? ResponseEntity.ok(carro) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Carro> createCarro(@RequestBody Carro carro)
    {
        carroService.save(carro);
        return ResponseEntity.status(HttpStatus.CREATED).body(carro);
    }

    @PutMapping("/{chassi}")
    public ResponseEntity<Carro> updateCarro(@PathVariable String chassi, @RequestBody Carro carro)
    {
        Carro existingCarro = carroService.findById(chassi);
        if (existingCarro != null)
        {
            carroService.update(carro);
            return ResponseEntity.ok(carro);
        }
        else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{chassi}")
    public ResponseEntity<Void> deleteCarro(@PathVariable String chassi)
    {
        carroService.deleteById(chassi);
        return ResponseEntity.noContent().build();
    }
}
