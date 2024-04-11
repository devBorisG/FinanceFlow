package finance.corp.financeflowdomain.repository.token;

import finance.corp.financeflowdomain.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<TokenEntity, String> {
}
