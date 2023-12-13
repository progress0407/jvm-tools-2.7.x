package io.philo.app;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

import static io.philo.app.QJEntity.jEntity;

@Component
public class JEntityQuery {

    private final JPAQueryFactory queryBuilder;

    public JEntityQuery(EntityManager entityManager) {
        this.queryBuilder = new JPAQueryFactory(entityManager);
    }

    public List<JEntityDto> searchAll() {

        List<JEntityDto> dtos = queryBuilder
                .select(new QJEntityDto(jEntity))
                .from(jEntity)
                .fetch();

        return dtos;
    }
}
