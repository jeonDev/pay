package com.pay.core

import com.pay.core.domain.pay.request.PaySendRequest
import com.pay.core.domain.pay.request.PaySendReservationRequest
import com.pay.core.domain.pay.service.PaySendService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional
import java.math.BigInteger

@SpringBootTest
class PayApplicationTests {

    @Autowired
    private lateinit var paySendService: PaySendService

    @Transactional
    @Rollback(false)
    @Test
    fun contextLoads() {
        val request = PaySendRequest(
            amount = BigInteger.TEN,
            sendMemberSeq = 1L,
            receiveMemberSeq = 1L,
            couponStorageSeq = null,
            isReservation = true,
            paySendReservationRequest = PaySendReservationRequest(
                reservationDate = "20241124",
                reservationTime = "1100"
            )
        )
        paySendService.send(request)

        val list = paySendService.findByReservationTransaction("20241124", "1100")
        println(list.toString())

    }

}
