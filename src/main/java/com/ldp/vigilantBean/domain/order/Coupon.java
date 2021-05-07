package com.ldp.vigilantBean.domain.order;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "coupon")
@NamedQueries(
        @NamedQuery(
                name = Coupon.GET_COUPON_BY_VALUE,
                query = "from Coupon c where c.couponValue = :couponValue"
        )
)
public class Coupon {

    public static final String GET_COUPON_BY_VALUE = "Coupon.getCouponByValue";

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Long couponId;

    @Column(name = "coupon_value")
    @NotBlank(message = "validation.coupon")
    private String couponValue;

    @Column(name = "discount_percentage")
    private Byte discountPercentage;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date")
    private Date creationDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiry_date")
    private Date expiryDate;

    @AssertFalse(message = "validation.coupon.isExpired")
    public boolean isExpired() {

        if (this.expiryDate == null) return false;

        return this.expiryDate.before(new Date());
    }
}
