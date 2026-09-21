package com.gabrielenzo.biblioteca.repository;

import com.gabrielenzo.biblioteca.model.ItemEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEmprestimoRepository extends JpaRepository<ItemEmprestimo, Long> {
}