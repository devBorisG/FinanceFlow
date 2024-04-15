package finance.corp.financeflowapplication.service.categoria.implementation;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.service.categoria.CrearCategoriaFacade;
import finance.corp.financeflowdomain.domain.CategoriaDomain;
import finance.corp.financeflowdomain.port.input.categoria.CrearCategoriaUseCase;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.mapper.MapperDTOToDomain;
import jakarta.transaction.Transactional;
import org.hibernate.TransactionException;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class CrearCategoriaFacadeImpl implements CrearCategoriaFacade {
    MapperDTOToDomain<CategoriaDTO, CategoriaDomain> mapperDTOToDomain = new MapperDTOToDomain<>();
    private final CrearCategoriaUseCase crearCategoriaUseCase;

    public CrearCategoriaFacadeImpl(CrearCategoriaUseCase crearCategoriaUseCase) {
        this.crearCategoriaUseCase = crearCategoriaUseCase;
    }

    @Override
    public void execute(CategoriaDTO dto) {
        //TODO: Crear validator para categoria
        try{
            CategoriaDomain categoriaDomain = mapperDTOToDomain.mapToDomain(dto, CategoriaDomain.class);
            crearCategoriaUseCase.execute(categoriaDomain);
        } catch (AplicationCustomException e){
            throw e;
        }catch (DomainCustomException e){
            throw AplicationCustomException.createTechnicalException(e, e.getMessage());
        }catch (TransactionException e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error en la transaccion para crear una categoria.");
        } catch (Exception e){
            throw AplicationCustomException.createTechnicalException(e,"Ocurrio un error inesperado ejecutando la transaccion para crear una cat.");
        }
    }
}
