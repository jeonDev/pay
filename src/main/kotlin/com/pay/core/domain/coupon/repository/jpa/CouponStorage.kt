package com.pay.core.domain.coupon.repository.jpa

import com.pay.core.domain.member.repository.jpa.Member
import com.pay.core.domain.type.CouponType
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class CouponStorage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COUPON_STORAGE_SEQ")
    var id:Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "COUPON_TYPE", nullable = false)
    val couponType: CouponType,

    @Column(name = "CREATE_DT", nullable = false)
    val createDt: LocalDateTime,

    @Column(name = "USE_YN")
    var useYn:Boolean

) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_SEQ")
    lateinit var member: Member

    fun couponUse() {
        if (this.useYn) throw RuntimeException()
        this.useYn = true
    }
}
