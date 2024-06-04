package finance.corp.financeflowapplication.service.egreso.implementation;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.egreso.EgresoDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.egreso.CrearEgresoFacade;
import finance.corp.financeflowapplication.validator.egreso.CrearEgresoValidator;
import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.EgresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.egreso.CrearEgresoUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionException;

@Transactional
@Service
public class CrearEgresoFacadeImpl implements CrearEgresoFacade {
    private final CrearEgresoUseCase crearEgresoUseCase;
    private final CrearEgresoValidator crearEgresoValidator;
    MapperDTOToDomain<EgresoDTO, EgresoDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();
    MapperDTOToDomain<CategoriaDTO, CategoriaDomain> mapperDTOToDomainCategoria = new MapperDTOToDomain<>();
    public CrearEgresoFacadeImpl(CrearEgresoUseCase crearEgresoUseCase, CrearEgresoValidator crearEgresoValidator1) {
        this.crearEgresoUseCase = crearEgresoUseCase;
        this.crearEgresoValidator = crearEgresoValidator1;
    }

    @Override
    public void execute(EgresoDTO dto) {
        try{
            EgresoDomain egresoDomain = mapperDTOToDomain.mapToDomain(dto, EgresoDomain.class);
            egresoDomain.setUsuario(mapperDTOToDomainUsuario.mapToDomain(dto.getUsuario(),UsuarioDomain.class));
            CategoriaDomain categoriaDomain = mapperDTOToDomainCategoria.mapToDomain(dto.getCategoria(), CategoriaDomain.class);
            categoriaDomain.setUsuarioDomain(mapperDTOToDomainUsuario.mapToDomain(dto.getUsuario(), UsuarioDomain.class));
            egresoDomain.setCategoria(categoriaDomain);
            crearEgresoUseCase.execute(egresoDomain);
            System.out.println("egresoDomain = " + egresoDomain);


        } catch(AplicationCustomException exception){
            throw exception;
        } catch(DomainCustomException exception){
            throw AplicationCustomException.createTechnicalException(exception,exception.getMessage());
        } catch(TransactionException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Se produjo un error creando ");
        } catch(Exception e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error inesperado mm");
        }
    }
}
