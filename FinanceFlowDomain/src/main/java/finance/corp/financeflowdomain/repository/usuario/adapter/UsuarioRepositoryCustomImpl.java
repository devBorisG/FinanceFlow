package finance.corp.financeflowdomain.repository.usuario.adapter;

import finance.corp.financeflowdomain.entity.UsuarioEntity;
import finance.corp.financeflowdomain.repository.usuario.port.UsuarioRepositoryCustom;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import static finance.corp.financeflowutils.helper.ObjectHelper.isNull;
import static finance.corp.financeflowutils.helper.StringHelper.isEmpty;


import java.util.ArrayList;
import java.util.List;

public class UsuarioRepositoryCustomImpl implements UsuarioRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UsuarioEntity> findCustom(UsuarioEntity usuarioEntity) {
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<UsuarioEntity> query = criteriaBuilder.createQuery(UsuarioEntity.class);
            Root<UsuarioEntity> usuarioEntityRoot = query.from(UsuarioEntity.class);
            List<Predicate> predicates = new ArrayList<>();
            if (!isNull(usuarioEntity)) {
                if (!isEmpty(usuarioEntity.getNombre())) {
                    predicates.add((criteriaBuilder.equal(usuarioEntityRoot.get("nombre"), usuarioEntity.getNombre())));
                }
                if (!isEmpty(usuarioEntity.getApellido())) {
                    predicates.add(criteriaBuilder.equal(usuarioEntityRoot.get("apellido"), usuarioEntity.getApellido()));
                }
                if (!isEmpty(usuarioEntity.getCorreo())) {
                    predicates.add(criteriaBuilder.equal(usuarioEntityRoot.get("correo"), usuarioEntity.getCorreo()));
                }
            }
            query.select(usuarioEntityRoot).where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
            return entityManager.createQuery(query).getResultList();
        } catch (TransactionRequiredException e) {
            throw new TransactionRequiredException("Se requiere una transacción para esta operación", e);
        } catch (OptimisticLockException e) {
            throw new OptimisticLockException("Conflicto de bloqueo optimista durante la operación de base de datos", e);
        } catch (LockTimeoutException e) {
            throw new LockTimeoutException("Tiempo de espera de bloqueo durante la operación de base de datos", e);
        } catch (QueryTimeoutException e) {
            throw new QueryTimeoutException("Tiempo de espera de consulta durante la operación de base de datos", e);
        }catch (PersistenceException e) {
            throw new PersistenceException("Error durante la operación de persistencia", e);
        } catch (Exception e) {
            throw new RuntimeException("Error desconocido al buscar usuario", e);
        }
    }
}
