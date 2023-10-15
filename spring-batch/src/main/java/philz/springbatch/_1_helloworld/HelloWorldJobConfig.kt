package philz.springbatch._1_helloworld

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

//@Configuration
class HelloWorldJobConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun helloWorldJob(helloWorldStep: Step): Job {
        return jobBuilderFactory.get("helloWorldJob")
            .incrementer(RunIdIncrementer())
            .start(helloWorldStep)
            .build()
    }

    @Bean
    fun helloWorldStep(helloWorldTasklet: Tasklet): Step {
        return stepBuilderFactory.get("helloWorldStep")
            .tasklet(helloWorldTasklet)
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