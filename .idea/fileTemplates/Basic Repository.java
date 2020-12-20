#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

import com.danielarrais.algafood.domain.model.${MODEL_NAME};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

#parse("File Header.java")
public interface ${MODEL_NAME}Repository  extends JpaRepository<${MODEL_NAME}, Long> {
}

