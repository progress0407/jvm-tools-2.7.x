package io.philo.app

import org.springframework.data.jpa.repository.JpaRepository

interface KEntityRepository : JpaRepository<KEntity, Long>{
}