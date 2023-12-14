package io.philo

import io.kotest.matchers.shouldBe
import io.philo.app.KEntity
import io.philo.app.KEntityQuery
import io.philo.app.KEntityRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class QueryIntegrationTest {

    @Autowired
    lateinit var query: KEntityQuery

    @Autowired
    lateinit var repository: KEntityRepository

    @Test
    fun query() {

        repository.save(KEntity("some entity"))

        val dtos = query.searchAll()

        dtos.size shouldBe 1
        dtos[0].description shouldBe "some entity"
    }
}