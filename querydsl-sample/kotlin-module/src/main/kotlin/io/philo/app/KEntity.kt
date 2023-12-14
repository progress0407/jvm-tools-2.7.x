package io.philo.app

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class KEntity(
    var description: String = ""
) {
    protected constructor() : this(description = "")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}