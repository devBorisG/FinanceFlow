package finance.corp.financeflowapplication.validator.egreso.implementation;

import finance.corp.financeflowapplication.dto.egreso.EgresoDTO;
import finance.corp.financeflowapplication.validator.egreso.EditarEgresoValidator;
import finance.corp.financeflowdomain.repository.egreso.EgresoRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static finance.corp.financeflowutils.helper.StringHelper.isEmpty;
import static finance.corp.financeflowutils.helper.UUIDHelper.isDefaultUUID;

@Component
public class EditarEgresoValidatorImpl implements EditarEgresoValidator {
    private final EgresoRepository egresoRepository;

    public EditarEgresoValidatorImpl(EgresoRepository egresoRepository) {
        this.egresoRepository = egresoRepository;
    }

    @Override
    public void isValid(EgresoDTO dto) {
        if (ObjectUtils.isEmpty(egresoRepository.existsById(dto.getId()))){
            throw AplicationCustomException.createUserException("No se esta resibiendo ningun egreso");
        }
        if (verifyMandatoryEgresoAttributes(dto)){
            throw AplicationCustomException.createUserException("Algunos datos del egreso estan vacios");
        }
        if (dto.getMonto() <= 0 && isValidNumber(dto.getMonto())){
            throw AplicationCustomException.createUserException("Monto invalido debe ser positivo y mayor a 0 o el campo debe solo contener numeros");
        }

    }
    private boolean verifyMandatoryEgresoAttributes(EgresoDTO egresoDTO) {
        return isDefaultUUID(egresoDTO.getId()) || isEmpty(egresoDTO.getDescripcion()) || isEmpty(egresoDTO.getNombre()) ||
                egresoDTO.getMonto()==0.0;
    }
    private boolean isValidNumber(double number) {
        String numberString = String.valueOf(number);
        String numberRegex = "^\\d+(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(numberString);
        return matcher.matches();
    }
}
