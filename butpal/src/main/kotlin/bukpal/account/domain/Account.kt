package bukpal.account.domain

import lombok.Value
import java.time.LocalDateTime

class Account (
    private val id : AccountId,
    private val baselineBalance : Money, // 첫번째 활동 바로 전의 잔고
    private val activityWindow : ActivityWindow // 지난 며칠 혹은 몇 주간의 범위에 해당하는 활동만 보유
){
    @Value
    class AccountId (
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
    fun getId() : AccountId {
        return id
    }
    fun calculateBalance() : Money { // 현재 총 잔고
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
