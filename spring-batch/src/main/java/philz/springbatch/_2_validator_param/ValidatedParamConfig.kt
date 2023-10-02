package philz.springbatch._2_validator_param

import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.job.CompositeJobParametersValidator
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ValidatedParamConfig(
    private val jobBuilderFactory: JobBuilderFactory,
    private val stepBuilderFactory: StepBuilderFactory
) {

    @Bean
    fun validatedParamJob(validatedParamStep: Step): Job {
        return jobBuilderFactory.get("validatedParamJob")
            .incrementer(RunIdIncrementer())
//            .validator(FileNameValidator())
            .validator(multipleValidator())
            .start(validatedParamStep)
            .build()
    }

    private fun multipleValidator() : CompositeJobParametersValidator {
        val validator = CompositeJobParametersValidator()
        validator.setValidators(listOf(FileNameValidator()))
        return validator
    }

    @Bean
    fun validatedParamStep(validatedParamTasklet: Tasklet): Step {
        return stepBuilderFactory.get("validatedParamStep")
            .tasklet(validatedParamTasklet)
            .build()
    }

    @Bean
    fun validatedParamTasklet(): Tasklet {
        return Tasklet { contribution, chunkContext ->
            println("validated param tasklet")
            return@Tasklet RepeatStatus.FINISHED
        }
    }
}