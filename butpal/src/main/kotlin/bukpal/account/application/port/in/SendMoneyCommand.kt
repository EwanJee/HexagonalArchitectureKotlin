package bukpal.account.application.port.`in`

import bukpal.account.domain.Account
import bukpal.account.domain.Money
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.Value
import org.jetbrains.annotations.NotNull

@Value // 생성자, 게터, toString, equals, hasCode 를 자동으로 생성
@EqualsAndHashCode(callSuper = false) // 해당 클래스 필드를 기반으로 equals와 hashCode 메서드 자동생성. callSuper = false로 부모클래스 메서드 호출 X
@Getter
class SendMoneyCommand(
    @NotNull
    val sourceAccountId : Account.AccountId,
    @NotNull
    val targetAccountId: Account.AccountId,
    @NotNull
    val money : Money
)
{
}