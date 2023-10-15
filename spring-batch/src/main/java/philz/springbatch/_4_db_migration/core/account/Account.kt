package philz.springbatch._4_db_migration.core.account

import philz.springbatch._4_db_migration.core.order.Order
import java.time.LocalDate
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
open class Account(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = null,
    open var orderItem: String = "",
    open var price: Int = 0,
    open var orderDate: LocalDate = LocalDate.now(),
    open var accountDate: LocalDate = LocalDate.now()
) {

    protected constructor() : this(id = null)

    constructor(order: Order) : this(null, order.orderItem, order.price, order.orderDate)

    override fun toString(): String {
        return "Account(orderItem='$orderItem', price=$price, orderDate=$orderDate, accountDate=$accountDate)"
    }
}