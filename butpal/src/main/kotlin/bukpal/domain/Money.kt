package bukpal.domain

import lombok.Value
import java.math.BigDecimal
@Value
class Money(
    val amount: BigDecimal
){
    companion object {
        val ZERO = Money.of(0L)
        fun of(value: Long): Money {
            return Money(value.toBigDecimal())
        }
        fun add(a : Money, b : Money): Money {
            return Money(a.amount + b.amount)
        }
        fun subtract(a : Money, b : Money): Money {
            return Money(a.amount - b.amount)
        }
    }
    fun isPositiveOrZero(): Boolean {
        return this.amount >= BigDecimal.ZERO
    }
    fun isNegative(): Boolean {
        return this.amount < BigDecimal.ZERO
    }
    fun isPositive(): Boolean {
        return this.amount > BigDecimal.ZERO
    }
    fun isGreaterThanOrEqualTo(money : Money): Boolean {
        return this.amount >= money.amount
    }
    fun isGreaterThan(money : Money): Boolean {
        return this.amount > money.amount
    }
    fun minus(money: Money): Money {
        return Money(this.amount.subtract(money.amount))
    }
    fun plus(money: Money): Money {
        return Money(this.amount.add(money.amount))
    }
    fun negate(): Money {
        return Money(this.amount.negate())
    }



}
