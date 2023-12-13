package io.philo;

import io.philo.app.JEntity;
import io.philo.app.JEntityDto;
import io.philo.app.JEntityQuery;
import io.philo.app.JEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class QueryIntegrationTest {

    @Autowired
    JEntityQuery query;

    @Autowired
    JEntityRepository repository;

    @Test
    void query() {

        repository.save(new JEntity("some entity"));

        List<JEntityDto> dtos = query.searchAll();

        assertAll(
                () -> assertEquals(dtos.size(), 1),
                () -> assertEquals(dtos.get(0).getDescription(), "some entity")
        );
    }
}
