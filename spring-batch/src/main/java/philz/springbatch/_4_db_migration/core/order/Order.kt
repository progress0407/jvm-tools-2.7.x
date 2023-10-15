package philz.springbatch._4_db_migration.core.order

import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "orders")
open class Order protected constructor(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,
    open var orderItem: String = "",
    open var price: Int = 0,
    open var orderDate: LocalDate = LocalDate.now()
) {
    protected constructor() : this(id = null)

    constructor(orderItem: String, price: Int, orderDate: LocalDate):
            this(null, orderItem, price, orderDate)

    override fun toString(): String {
        return "Order(orderItem='$orderItem', price=$price, orderDate=$orderDate)"
    }
}