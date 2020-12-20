#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end

import com.danielarrais.algafood.domain.model.${MODEL_NAME};

#parse("File Header.java")
public interface ${MODEL_NAME}RepositoryCustomQueries {
}
