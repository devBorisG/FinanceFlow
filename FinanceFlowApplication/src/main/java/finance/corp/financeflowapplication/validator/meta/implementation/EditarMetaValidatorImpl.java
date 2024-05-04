package finance.corp.financeflowapplication.validator.meta.implementation;

import finance.corp.financeflowapplication.dto.meta.MetaDTO;
import finance.corp.financeflowapplication.validator.meta.EditarMetaValidator;
import finance.corp.financeflowdomain.repository.meta.MetaRepository;
import finance.corp.financeflowutils.exception.aplication.AplicationCustomException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static finance.corp.financeflowutils.helper.StringHelper.isEmpty;
import static finance.corp.financeflowutils.helper.UUIDHelper.isDefaultUUID;

@Component
public class EditarMetaValidatorImpl implements EditarMetaValidator {
    private final MetaRepository metaRepository;

    public EditarMetaValidatorImpl(MetaRepository metaRepository) {
        this.metaRepository = metaRepository;
    }

    @Override
    public void isValid(MetaDTO dto) {
        if (ObjectUtils.isEmpty(metaRepository.existsById(dto.getId()))){
            throw AplicationCustomException.createUserException("No se esta resibiendo ningun egreso");
        }
        if (verifyMandatoryEgresoAttributes(dto)){
            throw AplicationCustomException.createUserException("Algunos datos de meta estan vacios");
        }
        if (dto.getMonto() <= 0 && isValidNumber(dto.getMonto())){
            throw AplicationCustomException.createUserException("Monto invalido debe ser positivo y mayor a 0 o el campo debe solo contener numeros");
        }
        if (dto.getFechaFin()== null && dto.getFechaInicio()== null){
            throw AplicationCustomException.createUserException("Las fechas son obligatorias.");
        }
    }
    private boolean isValidNumber(double number) {
        String numberString = String.valueOf(number);
        String numberRegex = "^\\d+(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(numberRegex);
        Matcher matcher = pattern.matcher(numberString);
        return matcher.matches();
    }
    private boolean verifyMandatoryEgresoAttributes(MetaDTO metaDTO) {
        return isDefaultUUID(metaDTO.getId()) || isEmpty(metaDTO.getDescripcion()) || isEmpty(metaDTO.getNombre()) ||
                metaDTO.getMonto()==0.0;
    }
}
