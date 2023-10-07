package philz.springbatch._3_job_listener

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.JobScope
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepScope
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JobListenerConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun jobListenerJob(jobListenerStep: Step): Job {
        return jobBuilderFactory.get("jobListenerJob")
            .incrementer(RunIdIncrementer())
            .listener(JobLoggerListener())
            .start(jobListenerStep)
            .build()
    }

    @JobScope
    @Bean
    fun jobListenerStep(jobListenerTasklet: Tasklet): Step {
        return stepBuilderFactory.get("jobListenerStep")
            .tasklet(jobListenerTasklet)
            .build()
    }


    @StepScope
    @Bean
    fun jobListenerTasklet(): Tasklet {
        return Tasklet { contribution, chunkContext ->
            println(".")
            throw RuntimeException("some error")
//            return@Tasklet RepeatStatus.FINISHED
        }
    }
}