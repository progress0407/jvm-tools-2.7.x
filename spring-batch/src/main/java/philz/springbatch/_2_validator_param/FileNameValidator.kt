package philz.springbatch._2_validator_param

import org.springframework.batch.core.JobParameter
import org.springframework.batch.core.JobParameters
import org.springframework.batch.core.JobParametersInvalidException
import org.springframework.batch.core.JobParametersValidator
import org.springframework.lang.Nullable

class FileNameValidator : JobParametersValidator {

    override fun validate(parameters: JobParameters?) {
        parameters!!

        val fileName = parameters.getString("fileName")?: throw JobParametersInvalidException("file name should not be null")

        if (fileName.endsWith("csv").not()) {
            throw JobParametersInvalidException("file should be csv file")
        }
    }
}