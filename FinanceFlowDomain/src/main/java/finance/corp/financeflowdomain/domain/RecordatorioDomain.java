package finance.corp.financeflowdomain.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class RecordatorioDomain {
    private UUID id;
    private Date fecha;
    private int periodicidad;
    private MetaDomain meta;
}
