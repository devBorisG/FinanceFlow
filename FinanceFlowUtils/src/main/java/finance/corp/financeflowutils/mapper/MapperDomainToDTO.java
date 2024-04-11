package finance.corp.financeflowutils.mapper;

import org.modelmapper.ModelMapper;

public class MapperDomainToDTO<D,T> {
    private static final ModelMapper mapper = new ModelMapper();
    public T mapToDomain(D domain, Class<T> dto){
        return mapper.map(domain,dto);
    }
}
