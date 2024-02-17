package bukpal.account.domain

import lombok.NonNull
import lombok.RequiredArgsConstructor
import java.time.LocalDateTime

@RequiredArgsConstructor
data class Activity(
    val id : ActivityId? = null,
    @NonNull
    val ownerAccountId: Account.AccountId,
    @NonNull
    val sourceAccountId: Account.AccountId,
    @NonNull
    val targetAccountId: Account.AccountId,
    @NonNull
    val timestamp: LocalDateTime,
    @NonNull
    val money: Money
) {
    data class ActivityId(
        val value: Long
    )
}