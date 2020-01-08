package net.javaguides.springboot.tutorial.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.javaguides.springboot.tutorial.entity.Klientas;

@Repository
public interface KlientasRepository extends CrudRepository<Klientas, Long> {
    
    List<Klientas> findByName(String name);
    
}
