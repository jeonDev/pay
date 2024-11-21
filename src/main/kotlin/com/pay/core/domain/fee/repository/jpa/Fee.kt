package com.pay.core.domain.fee.repository.jpa

import com.pay.core.domain.type.FeeType
import com.pay.core.domain.type.FeeWay
import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.math.BigDecimal

@DynamicUpdate
@Entity
data class Fee(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEE_SEQ")
    var id:Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "FEE_TYPE")
    val feeType:FeeType,

    @Enumerated(EnumType.STRING)
    @Column(name = "FEE_WAY")
    var feeWay:FeeWay,

    @Column(name = "FEE")
    var fee:BigDecimal,

    @Column(name = "USE_YN")
    val useYn: Boolean
) {

    fun update(feeWay: FeeWay, fee: BigDecimal) {
        this.feeWay = feeWay
        this.fee = fee
    }
}
