package csd230.s26.lab1.repositories;

import csd230.s26.lab1.entities.NotebookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotebookRepository extends JpaRepository<NotebookEntity, Long> {
}