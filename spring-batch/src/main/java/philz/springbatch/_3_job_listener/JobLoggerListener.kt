package philz.springbatch._3_job_listener

import mu.KotlinLogging
import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.BatchStatus.FAILED
import org.springframework.batch.core.JobExecution
import org.springframework.batch.core.JobExecutionListener

class JobLoggerListener: JobExecutionListener {

    val log = KotlinLogging.logger {}

    override fun beforeJob(jobExecution: JobExecution) {
        val jobName = jobExecution.jobInstance.jobName
        log.info { "$jobName Job is Running" }
    }

    override fun afterJob(jobExecution: JobExecution) {
        val jobName = jobExecution.jobInstance.jobName
        val jobStatus = jobExecution.status
        log.info { "${jobName} Job is Done. (Status: ${jobStatus})" }

        if (jobStatus === FAILED) {
            log.error { "Job is Failed"}
        }
    }


}