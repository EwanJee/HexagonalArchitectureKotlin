package bukpal.application

import bukpal.application.port.`in`.SendMoneyUseCase
import bukpal.application.port.out.LoadAccountPort
import bukpal.application.port.out.UpdateAccountStatePort

class SendMoneyService (private val loadAccountPort: LoadAccountPort, private val updateAccountStatePort: UpdateAccountStatePort) : SendMoneyUseCase{
}
/**
 * 질문 :
 * 1. (구현체가 있다고 가정하고) updateAccountStatePortImpl 에서는 어떻게 계좌 잔고를 업데이트 하는가? 업데이트를 하기 위해서는 계좌 잔고를 조회해야하는데, 이것은 loadAccountPortImpl에서 해야하는 일이 아닌가?
 * 2. 유효성 검증
 *                (UseCase 클래스 안에서)
 *              fun someFunction(request: Request) : Response
 *
 * 3. 풍부한 도메인 모델 vs 빈약한 도메일 모델
 *     빈약한 도메인 모델은 도메인 클래스안에 로직이 없고 로직은 서비스 클래스에 구현이 된다.
 *     풍부한 도메인 모델은 도메인 클래스안에 로직이 있고 서비스 클래스는 도메인 클래스의 로직을 사용한다.?
 *
 */