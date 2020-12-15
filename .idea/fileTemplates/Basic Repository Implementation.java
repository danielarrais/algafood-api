#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

import com.danielarrais.algafood.domain.model.${MODEL_NAME};
import com.danielarrais.algafood.domain.repository.${MODEL_NAME}Repository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
#parse("File Header.java")
public class  ${MODEL_NAME}RepositoryImpl implements  ${MODEL_NAME}Repository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<${MODEL_NAME}> listar() {
        return entityManager.createQuery("select c from ${MODEL_NAME} as c", ${MODEL_NAME}.class).getResultList();
    }

    @Transactional
    public void salvar(${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}) {
        entityManager.merge(${MODEL_NAME_CAMEL_CASE});
    }

    public ${MODEL_NAME} buscar(Long id) {
        return entityManager.find(${MODEL_NAME}.class, id);
    }

    @Transactional
    public void remover(Long id) {
        ${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE} = Optional
                .ofNullable(buscar(id))
                .orElseThrow(() -> {
                    // Esperava a existência de 1 cozinha
                    throw new EmptyResultDataAccessException(1);
                });

        entityManager.remove(${MODEL_NAME_CAMEL_CASE});
    }
}
