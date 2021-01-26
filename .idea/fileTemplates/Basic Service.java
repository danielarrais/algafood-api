#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

import com.danielarrais.algafood.domain.exception.RegistroEmUsoException;
import com.danielarrais.algafood.domain.exception.RegistroNaoEncontradoException;
import com.danielarrais.algafood.domain.model.${MODEL_NAME};
import com.danielarrais.algafood.domain.repository.${MODEL_NAME}Repository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.danielarrais.algafood.util.CustomBeansUtils.copyNonNullValues;
import static com.danielarrais.algafood.util.CustomBeansUtils.mergeValues;

@Service
#parse("File Header.java")
public class ${MODEL_NAME}Service {
    private final ${MODEL_NAME}Repository ${MODEL_NAME_CAMEL_CASE}Repository;

    public ${MODEL_NAME}Service(${MODEL_NAME}Repository ${MODEL_NAME_CAMEL_CASE}Repository) {
        this.${MODEL_NAME_CAMEL_CASE}Repository = ${MODEL_NAME_CAMEL_CASE}Repository;
    }

    public List<${MODEL_NAME}> listar() {
        return ${MODEL_NAME_CAMEL_CASE}Repository.findAll();
    }

    public Optional<${MODEL_NAME}> buscar(long ${MODEL_NAME_CAMEL_CASE}Id) {
        return ${MODEL_NAME_CAMEL_CASE}Repository.findById(${MODEL_NAME_CAMEL_CASE}Id);
    }

    public ${MODEL_NAME} buscarObrigatorio(long ${MODEL_NAME_CAMEL_CASE}Id) {
        return buscar(${MODEL_NAME_CAMEL_CASE}Id).orElseThrow(() -> {
            throw new RegistroNaoEncontradoException("${MODEL_NAME}", ${MODEL_NAME_CAMEL_CASE}Id);
        });
    }

    @Transactional
    public ${MODEL_NAME} salvar(${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}) {
        return ${MODEL_NAME_CAMEL_CASE}Repository.save(${MODEL_NAME_CAMEL_CASE});
    }

    @Transactional
    public void atualizar(Long id, ${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}) {
        var ${MODEL_NAME_CAMEL_CASE}Atual = buscarObrigatorio(id);

        copyNonNullValues(${MODEL_NAME_CAMEL_CASE}, ${MODEL_NAME_CAMEL_CASE}Atual);
        salvar(${MODEL_NAME_CAMEL_CASE}Atual);
    }

    @Transactional
    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        var ${MODEL_NAME_CAMEL_CASE}Atual = buscarObrigatorio(id);

        mergeValues(propertiesAndValues, ${MODEL_NAME_CAMEL_CASE}Atual);
        salvar(${MODEL_NAME_CAMEL_CASE}Atual);
    }

    @Transactional
    public void remover(Long id) {
        var ${MODEL_NAME_CAMEL_CASE} = buscarObrigatorio(id);

        try {
            ${MODEL_NAME_CAMEL_CASE}Repository.delete(${MODEL_NAME_CAMEL_CASE});
            ${MODEL_NAME_CAMEL_CASE}Repository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw new RegistroEmUsoException(id);
        }
    }
}