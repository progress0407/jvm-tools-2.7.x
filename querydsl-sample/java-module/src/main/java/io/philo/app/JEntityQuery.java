package io.philo.app;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class JEntityQuery {

    private final JPAQueryFactory queryBuilder;

    public JEntityQuery(EntityManager entityManager) {
        this.queryBuilder = new JPAQueryFactory(entityManager);
    }

    public void someQuery() {

//        List<T> dtos = queryBuilder
//                .selectFrom(new JEntityDto(jEntity))
//                .fetch();
    }
}
