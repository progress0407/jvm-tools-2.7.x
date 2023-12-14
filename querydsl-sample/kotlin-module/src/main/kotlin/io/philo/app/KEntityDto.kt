package io.philo.app

import com.querydsl.core.annotations.QueryProjection

data class KEntityDto(val description: String) {

    @QueryProjection
    constructor(kEntity: KEntity) : this(description = kEntity.description)
}