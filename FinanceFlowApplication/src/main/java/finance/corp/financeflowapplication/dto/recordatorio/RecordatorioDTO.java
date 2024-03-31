package finance.corp.financeflowapplication.dto.recordatorio;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.dto.meta.builder.MetaDTOBuilder;

import static finance.corp.financeflowutils.constant.Constants.DEFAULT_UUID;
import static finance.corp.financeflowutils.constant.Constants.DEFAULT_DATE;
import static finance.corp.financeflowutils.constant.Constants.ZERO;
import static finance.corp.financeflowutils.helper.DateHelper.getDefaultDate;
import static finance.corp.financeflowutils.helper.NumberHelper.getDefaultInteger;
import static finance.corp.financeflowutils.helper.ObjectHelper.getDefaultIfNull;
import static finance.corp.financeflowutils.helper.UUIDHelper.getDefaultUUID;

import java.util.Date;
import java.util.UUID;

/**
 * This class represents a DTO (Data Transfer Object) for the Recordatorio entity.
 * It is used to transfer data between processes or across network connections.
 */
public class RecordatorioDTO {
    private UUID id;
    private Date fecha;
    private int periodicidad;
    private MetaDTO meta;

    /**
     * Default constructor that initializes the object with default values.
     */
    public RecordatorioDTO(){
        setId(DEFAULT_UUID);
        setFecha(DEFAULT_DATE);
        setPeriodicidad(ZERO);
        setMeta(MetaDTOBuilder.getMetaDTOBuilder().buildDefault());
    }

    /**
     * Constructor that initializes the object with provided values.
     *
     * @param id The UUID of the Recordatorio.
     * @param fecha The date of the Recordatorio.
     * @param periodicidad The periodicity of the Recordatorio.
     * @param meta The MetaDTO associated with the Recordatorio.
     */
    public RecordatorioDTO(final UUID id,final Date fecha,final int periodicidad,final MetaDTO meta){
        setId(id);
        setFecha(fecha);
        setPeriodicidad(periodicidad);
        setMeta(meta);
    }

    /**
     * Factory method to create a new instance of RecordatorioDTO with provided values.
     *
     * @param id The UUID of the Recordatorio.
     * @param fecha The date of the Recordatorio.
     * @param periodicidad The periodicity of the Recordatorio.
     * @param meta The MetaDTO associated with the Recordatorio.
     * @return A new instance of RecordatorioDTO.
     */
    public static RecordatorioDTO create(final UUID id,final Date fecha,final int periodicidad,final MetaDTO meta){
        return new RecordatorioDTO(id,fecha,periodicidad,meta);
    }

    /**
     * Factory method to create a new instance of RecordatorioDTO with default values.
     *
     * @return A new instance of RecordatorioDTO.
     */
    public static RecordatorioDTO create(){
        return new RecordatorioDTO();
    }

    /**
     * Getter for the id.
     *
     * @return The UUID of the Recordatorio.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Setter for the id.
     *
     * @param id The UUID to set.
     */
    public void setId(final UUID id) {
        this.id = getDefaultUUID(id);
    }

    /**
     * Getter for the fecha.
     *
     * @return The date of the Recordatorio.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Setter for the fecha.
     *
     * @param fecha The date to set.
     */
    public void setFecha(final Date fecha) {
        this.fecha = getDefaultDate(fecha);
    }

    /**
     * Getter for the periodicidad.
     *
     * @return The periodicity of the Recordatorio.
     */
    public int getPeriodicidad() {
        return periodicidad;
    }

    /**
     * Setter for the periodicidad.
     *
     * @param periodicidad The periodicity to set.
     */
    public void setPeriodicidad(final int periodicidad) {
        this.periodicidad = getDefaultInteger(periodicidad);
    }

    /**
     * Getter for the meta.
     *
     * @return The MetaDTO associated with the Recordatorio.
     */
    public MetaDTO getMeta() {
        return meta;
    }

    /**
     * Setter for the meta.
     *
     * @param meta The MetaDTO to set.
     */
    public void setMeta(final MetaDTO meta) {
        this.meta = getDefaultIfNull(meta, MetaDTOBuilder.getMetaDTOBuilder().buildDefault());
    }
}