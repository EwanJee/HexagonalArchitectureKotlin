package bukpal.domain

import java.time.LocalDateTime

class ActivityWindow(
    private val activities : List<Activity>
) {
    constructor(vararg activities : Activity) : this(activities.toList())
    fun getStartTimestamp() : LocalDateTime {
        return activities.minByOrNull { it.timestamp }
            ?.timestamp?: throw IllegalStateException("Activity window is empty")
    }
    fun getEndTimestamp() : LocalDateTime {
        return activities.maxByOrNull { it.timestamp }
            ?.timestamp?: throw IllegalStateException("Activity window is empty")
    }
    fun calculateBalance(accountId: Account.AccountId) : Money {
        val depositBalance : Money = activities
            .filter{ it.targetAccountId == accountId }
            .map { it.money }
            .fold(Money.ZERO) {sum, money -> Money.add(sum, money)}
        val withdrawalBalance : Money = activities
            .filter{ it.sourceAccountId == accountId }
            .map { it.money }
            .fold(Money.ZERO) {sum, money -> Money.add(sum, money)}
        return Money.add(depositBalance, withdrawalBalance.negate())
    }
    fun addActivity(activity: Activity) : Unit {
        this.activities.addLast(activity)
    }
}
