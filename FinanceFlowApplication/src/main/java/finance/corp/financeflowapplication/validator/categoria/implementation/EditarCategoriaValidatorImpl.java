package finance.corp.financeflowapplication.validator.categoria.implementation;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.validator.categoria.EditarCategoriaValidator;
import finance.corp.financeflowdomain.repository.categoria.CategoriaRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.stereotype.Component;

@Component
public class EditarCategoriaValidatorImpl implements EditarCategoriaValidator {
    private final CategoriaRepository categoriaRepository;

    public EditarCategoriaValidatorImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void isValid(CategoriaDTO dto) {
        if (!categoriaRepository.existsById(dto.getId())) {
            throw AplicationCustomException.createUserException("La categoria no existe.");
        }
    }
}
