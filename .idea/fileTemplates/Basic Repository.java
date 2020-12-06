#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

import com.danielarrais.algafood.domain.model.${MODEL_NAME};
import java.util.List;

#parse("File Header.java")
public interface ${MODEL_NAME}Repository {
    List<${MODEL_NAME}> all();
    ${MODEL_NAME} find(Long id);
    ${MODEL_NAME} add(${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE});
    void remove(${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE});
}
