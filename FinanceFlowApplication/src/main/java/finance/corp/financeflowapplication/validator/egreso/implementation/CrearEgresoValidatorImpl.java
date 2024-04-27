package finance.corp.financeflowapplication.validator.egreso.implementation;

import finance.corp.financeflowapplication.dto.categoria.CategoriaDTO;
import finance.corp.financeflowapplication.dto.egreso.EgresoDTO;
import finance.corp.financeflowapplication.dto.usuario.UsuarioDTO;
import finance.corp.financeflowapplication.validator.egreso.CrearEgresoValidator;
import finance.corp.financeflowdomain.entity.CategoriaEntity;
import finance.corp.financeflowdomain.repository.categoria.CategoriaRepository;
import finance.corp.financeflowdomain.repository.egreso.EgresoRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static finance.corp.financeflowutils.helper.StringHelper.isEmpty;
import static finance.corp.financeflowutils.helper.UUIDHelper.isDefaultUUID;

@Component
public class CrearEgresoValidatorImpl implements CrearEgresoValidator {
    private final EgresoRepository egresoRepository;
    private final CategoriaRepository categoriaRepository;

    public CrearEgresoValidatorImpl(EgresoRepository egresoRepository, CategoriaRepository categoriaRepository) {
        this.egresoRepository = egresoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void isValid(EgresoDTO dto) {

        if (egresoRepository.existsById(dto.getId())){
            throw AplicationCustomException.createUserException("Ocurrio un error, por favor intente nuevamente");
        }
        if (isValidNumber(dto.getMonto())){
            throw AplicationCustomException.createUserException("Nombres y apellidos solo pueden contener letras y espacios");
        }
    }
    private boolean isValidNumber(double number) {
        String numberString = String.valueOf(number);
        String numberRegex = "^\\d+$";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(numberString);

        return matcher.matches();
    }

    private boolean verifyCategoria(EgresoDTO egresoDTO) {
        CategoriaDTO categoriaDTO = new CategoriaDTO();
        CategoriaEntity categoriaEntity = new CategoriaEntity();
        categoriaEntity.setNombre(egresoDTO.getNombre());
        BeanUtils.copyProperties(categoriaDTO, categoriaEntity);
        return true;
    }
}
