package finance.corp.financeflowdomain.repository.usuario;

import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowutils.exception.domain.DomainCustomException;
import finance.corp.financeflowutils.helper.StringHelper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioRepositoryCustomImpl implements UsuarioRepositoryCustom{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UsuarioEntity> findCustom(UsuarioEntity usuarioEntity) {
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UsuarioEntity> query = criteriaBuilder.createQuery(UsuarioEntity.class);
            Root<UsuarioEntity> usuarioEntityRoot = query.from(UsuarioEntity.class);
            List<Predicate> predicates = new ArrayList<>();

            if(!Objects.isNull(usuarioEntity)){
                if(!StringHelper.isEmpty(usuarioEntity.getNombre())){
                    predicates.add((criteriaBuilder.equal(usuarioEntityRoot.get("nombre"),usuarioEntity.getNombre())));
                }
                if(!StringHelper.isEmpty(usuarioEntity.getApellido())){
                    predicates.add((criteriaBuilder.equal(usuarioEntityRoot.get("apellido"),usuarioEntity.getApellido())));
                }
                if(!StringHelper.isEmpty(usuarioEntity.getCorreo())){
                    predicates.add((criteriaBuilder.equal(usuarioEntityRoot.get("correo"),usuarioEntity.getCorreo())));
                }
            }
            query.select(usuarioEntityRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            return entityManager.createQuery(query).getResultList();
        } catch (Exception exception){
            throw DomainCustomException.createTechnicalException(exception,"Ocurrio un error creando el query para la consulta");
        }
    }
}
