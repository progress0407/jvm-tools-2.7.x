package io.philo.app;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JEntityRepository extends JpaRepository<JEntity, Long> {
}
