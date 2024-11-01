package com.cesarschool.autolinehonda.service;

import com.cesarschool.autolinehonda.model.Carro;
import com.cesarschool.autolinehonda.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService
{
    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository)
    {
        this.carroRepository = carroRepository;
    }

    public List<Carro> findAll()
    {
        return carroRepository.findAll();
    }

    public Carro findById(String chassi)
    {
        return carroRepository.findById(chassi);
    }

    public void save(Carro carro)
    {
        carroRepository.save(carro);
    }

    public void update(Carro carro)
    {
        carroRepository.update(carro);
    }

    public void deleteById(String chassi)
    {
        carroRepository.deleteById(chassi);
    }
}
