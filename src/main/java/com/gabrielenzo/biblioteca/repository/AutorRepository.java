package com.gabrielenzo.biblioteca.repository;

import com.gabrielenzo.biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}