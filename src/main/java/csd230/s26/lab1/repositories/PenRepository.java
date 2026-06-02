package csd230.s26.lab1.repositories;

import csd230.s26.lab1.entities.PenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PenRepository extends JpaRepository<PenEntity, Long> {
}
