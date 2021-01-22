#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

import com.danielarrais.algafood.domain.model.${MODEL_NAME};
import com.danielarrais.algafood.domain.repository.custom.${MODEL_NAME}RepositoryCustomQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
#parse("File Header.java")
public class ${MODEL_NAME}RepositoryImpl extends SimpleJpaRepository<${MODEL_NAME}, Long> implements ${MODEL_NAME}RepositoryCustomQueries {
    private final EntityManager entityManager;

    @Autowired
    public ${MODEL_NAME}RepositoryImpl(EntityManager entityManager) {
        super(${MODEL_NAME}.class, entityManager);
        this.entityManager = entityManager;
    }
}