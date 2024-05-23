package finance.corp.financeflowapplication.service.ingreso.implementation;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.ingreso.IngresoDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.service.ingreso.CrearIngresoFacade;
import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.domain.IngresoDomain;
import finance.corp.financeflowdomain.domain.UsuarioDomain;
import finance.corp.financeflowdomain.port.input.ingreso.CrearIngresoUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import jakarta.transaction.Transactional;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CrearIngresoFacadeImpl implements CrearIngresoFacade {

    MapperDTOToDomain<IngresoDTO, IngresoDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    MapperDTOToDomain<UsuarioDTO, UsuarioDomain> mapperDTOToDomainUsuario = new MapperDTOToDomain<>();
    MapperDTOToDomain<CategoriaDTO, CategoriaDomain> mapperDTOToDomainCategoria = new MapperDTOToDomain<>();
    private final CrearIngresoUseCase crearIngresoUseCase;

    public CrearIngresoFacadeImpl(CrearIngresoUseCase crearIngresoUseCase) {
        this.crearIngresoUseCase = crearIngresoUseCase;
    }

    @Override
    public void execute(IngresoDTO dto) {
        try{
            System.out.println("Entra en la fachada");
            IngresoDomain ingresoDomain = mapperDTOToDomain.mapToDomain(dto, IngresoDomain.class);
            System.out.println("ingreso domain");
            ingresoDomain.setUsuario(mapperDTOToDomainUsuario.mapToDomain(dto.getUsuario(),UsuarioDomain.class));
            CategoriaDomain categoriaDomain = mapperDTOToDomainCategoria.mapToDomain(dto.getCategoria(),CategoriaDomain.class);
            categoriaDomain.setUsuarioDomain(mapperDTOToDomainUsuario.mapToDomain(dto.getUsuario(),UsuarioDomain.class));
            ingresoDomain.setCategoria(categoriaDomain);
            System.out.println(ingresoDomain);
            crearIngresoUseCase.execute(ingresoDomain);
        } catch(AplicationCustomException exception){
            throw exception;
        } catch(DomainCustomException exception){
            throw AplicationCustomException.createTechnicalException(exception,exception.getMessage());
        } catch(TransactionException exception){
            throw AplicationCustomException.createTechnicalException(exception,"Ocurrio un error creando el ingreso");
        } catch(Exception e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error inesperado en la creación del ingreso");
        }

    }
}
