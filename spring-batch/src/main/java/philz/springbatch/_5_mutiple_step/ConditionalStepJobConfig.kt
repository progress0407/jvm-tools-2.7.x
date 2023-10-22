package philz.springbatch._5_mutiple_step

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
data class ConditionalStepJobConfig(
    val jobBuilderFactory: JobBuilderFactory,
    val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun conditionalStepJob(
        conditionalStartStep: Step,
        conditionalFailStep: Step,
        conditionalAllStep: Step,
        conditionalCompletedStep: Step
    ): Job =
        jobBuilderFactory.get("conditionalStepJob")
            .incrementer(RunIdIncrementer())
            .start(conditionalStartStep)
            .on("FAILED").to(conditionalFailStep)
            .from(conditionalStartStep)
            .on("COMPLETED").to(conditionalCompletedStep)
            .from(conditionalStartStep)
            .on("*").to(conditionalAllStep)
            .end()
            .build()

    @JobScope
    @Bean
    fun conditionalStartStep(): Step =
        stepBuilderFactory.get("conditionalStartStep")
            .tasklet { contribution, chunkContect ->
                println("Start Step!")
                RepeatStatus.FINISHED
//                throw Exception("Exception!")
            }
            .build()

    @JobScope
    @Bean
    fun conditionalAllStep(): Step {
        return stepBuilderFactory["conditionalAllStep"]
            .tasklet { contribution, chunkContext ->
                println("conditional All Step")
                RepeatStatus.FINISHED
            }
            .build()
    }

    @JobScope
    @Bean
    fun conditionalFailStep(): Step {
        return stepBuilderFactory["conditionalFailStep"]
            .tasklet { contribution, chunkContext ->
                println("conditional Fail Step")
                RepeatStatus.FINISHED
            }
            .build()
    }

    @JobScope
    @Bean
    fun conditionalCompletedStep(): Step {
        return stepBuilderFactory["conditionalCompletedStep"]
            .tasklet { contribution, chunkContext ->
                println("conditional Completed Step")
                RepeatStatus.FINISHED
            }
            .build()
    }
}
