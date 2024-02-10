package bukpal.domain

import lombok.Getter
import java.time.LocalDateTime

class Account (
    val id : AccountId,
    val baselineBalance : Money,
    val activityWindow : ActivityWindow
){
    data class AccountId (
        val value : Long?
    )

    companion object {
        fun withoutId(baselineBalance: Money, activityWindow: ActivityWindow): Account {
            return Account(AccountId(null), baselineBalance, activityWindow)
        }
        fun withId(id: AccountId, baselineBalance: Money, activityWindow: ActivityWindow): Account {
            return Account(id, baselineBalance, activityWindow)
        }
    }
    fun getId() : AccountId? {
        return id
    }
    fun calculateBalance() : Money {
        return Money.add(baselineBalance, activityWindow.calculateBalance(id))
    }

    fun withdraw(money: Money, targetAccountId: AccountId) : Boolean {
        if(!mayWithdraw(money)) {
            return false
        }
        val withdrawal : Activity = Activity(
            ownerAccountId = id,
            sourceAccountId = id,
            targetAccountId = targetAccountId,
            timestamp = LocalDateTime.now(),
            money = money
        )
        activityWindow.addActivity(withdrawal)
        return true
    }

    private fun mayWithdraw(money: Money): Boolean {
        return Money.add(
            this.calculateBalance(),
            money.negate()).isPositiveOrZero()
    }
    fun deposit(money: Money, sourceAccountId: AccountId) : Boolean {
        val deposit : Activity = Activity(
            ownerAccountId = id,
            sourceAccountId = sourceAccountId,
            targetAccountId = id,
            timestamp = LocalDateTime.now(),
            money = money
        )
        activityWindow.addActivity(deposit)
        return true
    }
}
