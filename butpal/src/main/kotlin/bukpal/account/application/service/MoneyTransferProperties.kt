package bukpal.account.application.service

import bukpal.account.domain.Money
import lombok.AllArgsConstructor
import lombok.Data
import lombok.Getter
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class MoneyTransferProperties(
    val maximumTransferThreshold: Money = Money.of(1_000_000L)

)
