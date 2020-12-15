#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.${MODEL_NAME};
import com.danielarrais.algafood.domain.repository.${MODEL_NAME}Repository;
import com.danielarrais.algafood.util.CustomBeansUtils;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @SneakyThrows
    public void salvar(${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}) {
        ${MODEL_NAME_CAMEL_CASE}Repository.save(${MODEL_NAME_CAMEL_CASE});
    }

    public void atualizar(Long id, ${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}) {
        buscar(id).map(${MODEL_NAME_CAMEL_CASE}Atual -> {
            BeanUtils.copyProperties(${MODEL_NAME_CAMEL_CASE}, ${MODEL_NAME_CAMEL_CASE}Atual, "id");
            return ${MODEL_NAME_CAMEL_CASE}Repository.save(${MODEL_NAME_CAMEL_CASE}Atual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        buscar(id).map(${MODEL_NAME_CAMEL_CASE}Atual -> {
            CustomBeansUtils.mergeValues(propertiesAndValues, ${MODEL_NAME_CAMEL_CASE}Atual);
            return ${MODEL_NAME_CAMEL_CASE}Repository.save(${MODEL_NAME_CAMEL_CASE}Atual);
        }).orElseThrow(() -> {
            throw new EntidadeNaoEncontradaException(id);
        });
    }

    public void remover(Long id) {
        try {
            ${MODEL_NAME_CAMEL_CASE}Repository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(id);
        }
    }
}