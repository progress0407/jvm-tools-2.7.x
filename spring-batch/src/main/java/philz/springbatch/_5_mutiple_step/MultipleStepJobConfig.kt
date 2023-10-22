package philz.springbatch._5_mutiple_step

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
data class MultipleStepJobConfig(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun multipleStepJob(
        multipleStep1: Step,
        multipleStep2: Step,
        multipleStep3: Step
    ): Job = jobBuilderFactory.get("multipleStepJob")
        .start(multipleStep1)
        .next(multipleStep2)
        .next(multipleStep3)
        .build()

    @JobScope
    @Bean
    fun multipleStep1(): Step {
        return stepBuilderFactory["multipleStep1"]
            .tasklet { contribution: StepContribution, chunkContext: ChunkContext ->
                println("step1")
                RepeatStatus.FINISHED
            }
            .build()
    }

    @JobScope
    @Bean
    fun multipleStep2(): Step {
        return stepBuilderFactory["multipleStep2"]
            .tasklet { contribution: StepContribution?, chunkContext: ChunkContext ->
                println("step2")

                val executionContext = chunkContext
                    .stepContext
                    .stepExecution
                    .jobExecution
                    .executionContext

                executionContext.put("some-key", "hello!!, this is stored value !! ><")


                RepeatStatus.FINISHED
            }
            .build()
    }

    @JobScope
    @Bean
    fun multipleStep3(): Step {
        return stepBuilderFactory["multipleStep3"]
            .tasklet { contribution: StepContribution?, chunkContext: ChunkContext ->

                println("step3")

                val executionContext = chunkContext
                    .stepContext
                    .stepExecution
                    .jobExecution
                    .executionContext

                println(executionContext["some-key"])

                RepeatStatus.FINISHED
            }
            .build()
    }
}
