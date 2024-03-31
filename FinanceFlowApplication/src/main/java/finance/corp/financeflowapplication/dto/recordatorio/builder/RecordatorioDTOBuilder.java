package finance.corp.financeflowapplication.dto.recordatorio.builder;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.dto.recordatorio.RecordatorioDTO;

import java.util.Date;
import java.util.UUID;

/**
 * This class is a builder for the RecordatorioDTO class.
 * It follows the Builder design pattern to construct instances of RecordatorioDTO.
 */
public class RecordatorioDTOBuilder implements RecordatorioBuilder{
    private UUID id;
    private Date fecha;
    private int periodicidad;
    private MetaDTO meta;

    /**
     * Private constructor to prevent direct instantiation.
     */
    private RecordatorioDTOBuilder() {
        super();
    }

    /**
     * Static factory method to get an instance of RecordatorioDTOBuilder.
     *
     * @return A new instance of RecordatorioDTOBuilder.
     */
    public static RecordatorioDTOBuilder getRecordatorioDTOBuilder() {
        return new RecordatorioDTOBuilder();
    }

    /**
     * Setter for the id.
     *
     * @param id The UUID to set.
     * @return The current instance of RecordatorioDTOBuilder.
     */
    @Override
    public RecordatorioDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Setter for the fecha.
     *
     * @param fecha The date to set.
     * @return The current instance of RecordatorioDTOBuilder.
     */
    @Override
    public RecordatorioDTOBuilder setFecha(Date fecha) {
        this.fecha = fecha;
        return this;
    }

    /**
     * Setter for the periodicidad.
     *
     * @param periodicidad The periodicity to set.
     * @return The current instance of RecordatorioDTOBuilder.
     */
    @Override
    public RecordatorioDTOBuilder setPeriodicidad(int periodicidad) {
        this.periodicidad = periodicidad;
        return this;
    }

    /**
     * Setter for the meta.
     *
     * @param meta The MetaDTO to set.
     * @return The current instance of RecordatorioDTOBuilder.
     */
    @Override
    public RecordatorioDTOBuilder setMeta(MetaDTO meta) {
        this.meta = meta;
        return this;
    }

    /**
     * Builds an instance of RecordatorioDTO with the set properties.
     *
     * @return A new instance of RecordatorioDTO.
     */
    public RecordatorioDTO build() {
        return RecordatorioDTO.create(id, fecha, periodicidad, meta);
    }

    /**
     * Builds an instance of RecordatorioDTO with default values.
     *
     * @return A new instance of RecordatorioDTO.
     */
    public RecordatorioDTO buildDefault() {
        return RecordatorioDTO.create();
    }
}