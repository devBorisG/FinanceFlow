package finance.corp.financeflowdomain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"Recordatorio\"", schema = "public")
public class RecordatorioEntity {
    @Id
    UUID id;
    Date fecha;
    int periodicidad;
    @OneToOne
    @JoinColumn(name = "meta_id")
    MetaEntity meta;
}
