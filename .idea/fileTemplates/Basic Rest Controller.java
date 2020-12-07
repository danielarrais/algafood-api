#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

import com.danielarrais.algafood.domain.model.${MODEL_NAME};
import com.danielarrais.algafood.domain.repository.${MODEL_NAME}Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/${MODEL_NAME_CAMEL_CASE}s")
#parse("File Header.java")
public class ${MODEL_NAME}Controller {
    private final ${MODEL_NAME}Repository ${MODEL_NAME_CAMEL_CASE}Repository;

    public ${MODEL_NAME}Controller(${MODEL_NAME}Repository ${MODEL_NAME_CAMEL_CASE}Repository) {
        this.${MODEL_NAME_CAMEL_CASE}Repository = ${MODEL_NAME_CAMEL_CASE}Repository;
    }

    @GetMapping()
    public List<${MODEL_NAME}> listar() {
        return ${MODEL_NAME_CAMEL_CASE}Repository.all();
    }
}

