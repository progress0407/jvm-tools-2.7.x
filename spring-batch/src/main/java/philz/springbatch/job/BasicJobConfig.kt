package philz.springbatch.job

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BasicJobConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun helloWorldJob(basicStep: Step): Job {
        return jobBuilderFactory.get("helloWorldJob")
            .incrementer(RunIdIncrementer())
            .start(basicStep)
            .build()
    }

    @Bean
    fun helloWorldStep(basicTasklet: Tasklet): Step {
        return stepBuilderFactory.get("helloWorldStep")
            .tasklet(basicTasklet)
            .build()
    }

    @Bean
    fun helloWorldTasklet(): Tasklet {
        return Tasklet { contribution, chunkContext ->
            println("hello world! spring batch")
            return@Tasklet RepeatStatus.FINISHED
        }
    }
}