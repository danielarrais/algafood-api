#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

import com.danielarrais.algafood.domain.model.${MODEL_NAME};
import com.danielarrais.algafood.domain.repository.custom.BaseSimpleJpaRepository;
import org.springframework.stereotype.Repository;

#parse("File Header.java")
public interface ${MODEL_NAME}Repository  extends BaseSimpleJpaRepository<${MODEL_NAME}, Long> {
}

