#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

import com.danielarrais.algafood.domain.model. ${MODEL_NAME};
import com.danielarrais.algafood.domain.repository. ${MODEL_NAME}Repository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Component
#parse("File Header.java")
public class  ${MODEL_NAME}RepositoryImpl implements  ${MODEL_NAME}Repository {

    @PersistenceContext
    private EntityManager entityManager;

    public List<${MODEL_NAME}> all() {
        return entityManager.createQuery("select c from ${MODEL_NAME} as c", ${MODEL_NAME}.class).getResultList();
    }

    @Transactional
    public ${MODEL_NAME} add(${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}) {
        return entityManager.merge(${MODEL_NAME_CAMEL_CASE});
    }

    public ${MODEL_NAME} find(Long id) {
        return entityManager.find(${MODEL_NAME}.class, id);
    }

    @Transactional
    public void remove(${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}) {
        ${MODEL_NAME_CAMEL_CASE} = find(${MODEL_NAME_CAMEL_CASE}.getId());
        entityManager.remove(${MODEL_NAME_CAMEL_CASE});
    }
}
