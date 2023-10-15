package philz.springbatch._4_db_migration

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import philz.springbatch._4_db_migration.core.order.Order
import philz.springbatch._4_db_migration.core.order.OrderRepository
import java.time.LocalDate
import javax.annotation.PostConstruct

@Component
class DataInit(private val orderRepository: OrderRepository) {

    @PostConstruct
    fun initData() {
        orderRepository.saveAll(
            mutableListOf(
                Order("카카오 선물", 15000, LocalDate.of(2022, 3, 1)),
                Order("배달 주문", 18000, LocalDate.of(2022, 3, 1)),
                Order("교보문고", 14000, LocalDate.of(2022, 3, 2)),
                Order("아이스크림", 3800, LocalDate.of(2022, 3, 3)),
                Order("치킨", 21000, LocalDate.of(2022, 3, 4)),
                Order("커피", 4000, LocalDate.of(2022, 3, 4)),
                Order("교보 문고", 13800, LocalDate.of(2022, 3, 5)),
                Order("카카오 선물", 5500, LocalDate.of(2022, 3, 6)),
            )
        )
    }
}