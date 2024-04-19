package finance.corp.financeflowapplication.validator.categoria.implementation;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.usuario.builder.UsuarioDTOBuilder;
import finance.corp.financeflowapplication.validator.categoria.CrearCategoriaValidator;
import finance.corp.financeflowdomain.repository.categoria.CategoriaRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.stereotype.Component;

import static finance.corp.financeflowutils.helper.UUIDHelper.isDefaultUUID;
import static finance.corp.financeflowutils.helper.StringHelper.isEmpty;

@Component
public class CrearCategoriaValidatorImpl implements CrearCategoriaValidator {
    private final CategoriaRepository categoriaRepository;

    public CrearCategoriaValidatorImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void isValid(CategoriaDTO dto) {
        if(verifyMandatoryCategoryFields(dto)){
            throw AplicationCustomException.createUserException("Todos los campos son obligatorios");
        }
        if(categoriaRepository.existsById(dto.getId())){
            throw AplicationCustomException.createUserException("Ocurrio un error, por favor intente nuevamente");
        }
    }

    private boolean verifyMandatoryCategoryFields(CategoriaDTO dto) {
        return isDefaultUUID(dto.getId()) || isEmpty(dto.getNombre()) || isEmpty(dto.getDescripcion()) || dto.getUsuarioDTO().equals(UsuarioDTOBuilder.getUsuarioDTOBuilder().buildDefault());
    }
}
