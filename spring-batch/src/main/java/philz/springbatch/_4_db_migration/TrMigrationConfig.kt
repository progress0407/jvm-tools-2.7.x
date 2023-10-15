package philz.springbatch._4_db_migration

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.data.RepositoryItemReader
import org.springframework.batch.item.data.RepositoryItemWriter
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort
import philz.springbatch._3_job_listener.JobLoggerListener
import philz.springbatch._4_db_migration.core.account.Account
import philz.springbatch._4_db_migration.core.account.AccountRepository
import philz.springbatch._4_db_migration.core.order.Order
import philz.springbatch._4_db_migration.core.order.OrderRepository
import java.time.LocalDate
import java.util.Collections.singletonMap
import javax.annotation.PostConstruct

@Configuration
class TrMigrationConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory,

    private val orderRepository: OrderRepository,
    private val accountRepository: AccountRepository
) {

    @Bean
    fun trMigrationJob(trMigrationStep: Step): Job {
        return jobBuilderFactory.get("trMigrationJob")
            .incrementer(RunIdIncrementer())
            .listener(JobLoggerListener())
            .start(trMigrationStep)
            .build()
    }

    @JobScope
    @Bean
    fun trMigrationStep(
        trOrderReader: RepositoryItemReader<Order>,
        trOrderProcessor: ItemProcessor<Order, Account>,
        trOrWriter: RepositoryItemWriter<Account>
    ): Step {
        return stepBuilderFactory.get("trMigrationStep")
            .chunk<Order, Account>(5)
            .reader(trOrderReader)
            .processor(trOrderProcessor)
            .writer(trOrWriter)
            .build()
    }

    // 배치를 편히 사용하기 위한 래퍼 리포지토리
    @StepScope
    @Bean
    fun trOrderReader(): RepositoryItemReader<Order> {
        return RepositoryItemReaderBuilder<Order>()
            .name("trOrderReader")
            .repository(orderRepository)
            .methodName("findAll")
            .pageSize(5)
            .arguments(emptyList<Any>())
            .sorts(singletonMap("id", Sort.Direction.ASC))
            .build()
    }

    @StepScope
    @Bean
    fun trOrderProcessor(): ItemProcessor<Order, Account> {
        return ItemProcessor<Order, Account> { order -> Account(order) }
    }

    @StepScope
    @Bean
    fun trOrderWriter(): RepositoryItemWriter<Account> {
        return RepositoryItemWriterBuilder<Account>()
            .repository(accountRepository)
            .build();
    }
}