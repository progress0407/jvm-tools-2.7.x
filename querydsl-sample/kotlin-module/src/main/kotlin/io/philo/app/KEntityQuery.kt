package io.philo.app

import com.querydsl.jpa.impl.JPAQueryFactory
import io.philo.app.QKEntity.kEntity
import org.springframework.stereotype.Component
import javax.persistence.EntityManager

@Component
class KEntityQuery(entityManager: EntityManager) {

    lateinit var jpaQueryFactory: JPAQueryFactory

    init {
        jpaQueryFactory = JPAQueryFactory(entityManager)
    }

    fun searchAll(): List<KEntityDto> {

        val dtos = jpaQueryFactory.select(QKEntityDto(kEntity))
            .from(kEntity)
            .fetch()

        return dtos
    }
}