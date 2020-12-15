#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

import com.danielarrais.algafood.domain.exception.EntidadeEmUsoException;
import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.${MODEL_NAME};
import com.danielarrais.algafood.domain.repository.${MODEL_NAME}Repository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
#parse("File Header.java")
public class ${MODEL_NAME}Service {
    private final ${MODEL_NAME}Repository ${MODEL_NAME_CAMEL_CASE}Repository;

    public ${MODEL_NAME}Service(${MODEL_NAME}Repository ${MODEL_NAME_CAMEL_CASE}Repository) {
        this.${MODEL_NAME_CAMEL_CASE}Repository = ${MODEL_NAME_CAMEL_CASE}Repository;
    }

    public List<${MODEL_NAME}> listar() {
        return ${MODEL_NAME_CAMEL_CASE}Repository.listar();
    }

    public ${MODEL_NAME} buscar(long ${MODEL_NAME_CAMEL_CASE}Id) {
        return ${MODEL_NAME_CAMEL_CASE}Repository.buscar(${MODEL_NAME_CAMEL_CASE}Id);
    }

    public void salvar(${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}) {
        try {
            ${MODEL_NAME_CAMEL_CASE}Repository.salvar(${MODEL_NAME_CAMEL_CASE});
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(${MODEL_NAME_CAMEL_CASE}.getId());
        }
    }

    public void atualizar(Long id, ${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}) {
        ${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}Atual = buscar(id);

        if (Objects.isNull(${MODEL_NAME_CAMEL_CASE}Atual)) {
            throw new EntidadeNaoEncontradaException(id);
        }

        BeanUtils.copyProperties(${MODEL_NAME_CAMEL_CASE}, ${MODEL_NAME_CAMEL_CASE}Atual, "id");

        salvar(${MODEL_NAME_CAMEL_CASE}Atual);
    }
    
    public void atualizar(Long id, Map<String, Object> propertiesAndValues) {
        ${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}Atual = buscar(id);

        if (Objects.isNull(${MODEL_NAME_CAMEL_CASE}Atual)) {
            throw new EntidadeNaoEncontradaException(id);
        }

        CustomBeansUtils.mergeValues(propertiesAndValues, ${MODEL_NAME_CAMEL_CASE}Atual);

        salvar(${MODEL_NAME_CAMEL_CASE}Atual);
    }

    public void remover(Long id) {
        try {
            ${MODEL_NAME_CAMEL_CASE}Repository.remover(id);
        } catch (EmptyResultDataAccessException exception) {
            throw new EntidadeNaoEncontradaException(id);
        } catch (DataIntegrityViolationException exception) {
            throw new EntidadeEmUsoException(id);
        }
    }
}