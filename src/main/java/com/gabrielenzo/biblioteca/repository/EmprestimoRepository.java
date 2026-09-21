package com.gabrielenzo.biblioteca.repository;

import com.gabrielenzo.biblioteca.model.Emprestimo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    @Query ("""
    SELECT DISTINCT e
    FROM Emprestimo e
    JOIN FETCH e.itens i
    JOIN FETCH i.livro
    WHERE e.usuario.id = :usuarioId
      AND e.status = com.gabrielenzo.biblioteca.model.StatusEmprestimo.ATIVO
""")
List<Emprestimo> findEmprestimosAtivosPorUsuario(Long usuarioId);

@Query("""
    SELECT DISTINCT e
    FROM Emprestimo e
    JOIN FETCH e.usuario
    JOIN FETCH e.itens i
    JOIN FETCH i.livro
    WHERE e.dataDevolucaoPrevista < CURRENT_DATE
      AND e.status = com.gabrielenzo.biblioteca.model.StatusEmprestimo.ATIVO
""")
List<Emprestimo> findEmprestimosAtrasados();
}