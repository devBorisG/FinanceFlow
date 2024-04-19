package finance.corp.financeflowapplication.dto.egreso;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.categoria.builder.CategoriaDTOBuilder;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.dto.usuario.builder.UsuarioDTOBuilder;

import java.util.UUID;

import static finance.corp.financeflowutils.constant.Constants.*;
import static finance.corp.financeflowutils.constant.Constants.ZERO;
import static finance.corp.financeflowutils.helper.NumberHelper.getDefaultDouble;
import static finance.corp.financeflowutils.helper.NumberHelper.getDefaultInteger;
import static finance.corp.financeflowutils.helper.ObjectHelper.getDefaultIfNull;
import static finance.corp.financeflowutils.helper.StringHelper.applyTrim;
import static finance.corp.financeflowutils.helper.UUIDHelper.getDefaultUUID;

public class EgresoDTO {
    private UUID id;
    private String nombre;
    private String descripcion;
    private double monto;
    private int periodicidad;
    private UsuarioDTO usuario;
    private CategoriaDTO categoria;

    public EgresoDTO(){
        setId(DEFAULT_UUID);
        setNombre(EMPTY_STRING);
        setDescripcion(EMPTY_STRING);
        setMonto(ZERO_DOUBLE);
        setPeriodicidad(ZERO);
        setUsuario(UsuarioDTOBuilder.getUsuarioDTOBuilder().buildDefault());
        setCategoria(CategoriaDTOBuilder.getCategoriaDTOBuilder().buildDefault());
    }

    public EgresoDTO(final UUID id,final String nombre,final String descripcion,final double monto,final int periodicidad,final UsuarioDTO usuario,final CategoriaDTO categoria){
        setId(id);
        setNombre(nombre);
        setDescripcion(descripcion);
        setMonto(monto);
        setPeriodicidad(periodicidad);
        setUsuario(usuario);
        setCategoria(categoria);
    }

    public static EgresoDTO create(final UUID id, final String nombre, final String descripcion, final double monto, final int periodicidad, final UsuarioDTO usuario, final CategoriaDTO categoria){
        return new EgresoDTO(id,nombre,descripcion,monto,periodicidad,usuario,categoria);
    }

    public static EgresoDTO create(){
        return new EgresoDTO();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = getDefaultUUID(id);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = applyTrim(nombre);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = applyTrim(descripcion);
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(final double monto) {
        this.monto = getDefaultDouble(monto);
    }

    public int getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(final int periodicidad) {
        this.periodicidad = getDefaultInteger(periodicidad);
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(final UsuarioDTO usuario) {
        this.usuario = getDefaultIfNull(usuario, UsuarioDTOBuilder.getUsuarioDTOBuilder().buildDefault());
    }

    public CategoriaDTO getCategoria() {
        return categoria;
    }

    public void setCategoria(final CategoriaDTO categoria) {
        this.categoria = getDefaultIfNull(categoria, CategoriaDTOBuilder.getCategoriaDTOBuilder().buildDefault());
    }
}
