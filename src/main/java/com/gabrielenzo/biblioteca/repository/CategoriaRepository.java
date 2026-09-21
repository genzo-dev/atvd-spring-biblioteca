package com.gabrielenzo.biblioteca.repository;

import com.gabrielenzo.biblioteca.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoriaRepository
        extends JpaRepository<Categoria, Long> {

    @Query("""
        SELECT c.nome AS nome, COUNT(l.id) AS quantidade
        FROM Categoria c
        LEFT JOIN Livro l ON l.categoria.id = c.id
        GROUP BY c.id, c.nome
        ORDER BY COUNT(l.id) DESC
    """)
    List<CategoriaQuantidade> contarLivrosPorCategoria();
}