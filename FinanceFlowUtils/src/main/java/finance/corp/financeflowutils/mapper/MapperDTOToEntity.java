package finance.corp.financeflowutils.mapper;

import org.modelmapper.ModelMapper;

public class MapperDTOToEntity <T,E>{
    private static final ModelMapper mapper = new ModelMapper();
    public E mapToEntity(T dto, Class<E> entity){
        return mapper.map(dto,entity);
    }
}
