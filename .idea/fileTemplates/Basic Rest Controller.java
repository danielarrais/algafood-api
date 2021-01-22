#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

import com.danielarrais.algafood.api.dto.input.${MODEL_NAME_CAMEL_CASE}.${MODEL_NAME}Input;
import com.danielarrais.algafood.api.dto.output.${MODEL_NAME_CAMEL_CASE}.${MODEL_NAME}Output;
import com.danielarrais.algafood.domain.model.${MODEL_NAME};
import com.danielarrais.algafood.domain.service.${MODEL_NAME}Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.danielarrais.algafood.util.ControllerUtils.mapper;

@RestController
@RequestMapping("/${MODEL_NAME_CAMEL_CASE}s")
#parse("File Header.java")
public class ${MODEL_NAME}Controller {
    private final ${MODEL_NAME}Service ${MODEL_NAME_CAMEL_CASE}Service;

    public ${MODEL_NAME}Controller(${MODEL_NAME}Service ${MODEL_NAME_CAMEL_CASE}Service) {
        this.${MODEL_NAME_CAMEL_CASE}Service = ${MODEL_NAME_CAMEL_CASE}Service;
    }

    @GetMapping()
    public List<${MODEL_NAME}Output> listar() {
        var ${MODEL_NAME_CAMEL_CASE}s = ${MODEL_NAME_CAMEL_CASE}Service.listar();
        return mapper(${MODEL_NAME_CAMEL_CASE}s, ${MODEL_NAME}Output.class);
    }

    @GetMapping("/{id}")
    public ${MODEL_NAME}Output buscar(@PathVariable Long id) {
        var ${MODEL_NAME_CAMEL_CASE} = ${MODEL_NAME_CAMEL_CASE}Service.buscarObrigatorio(id);
        return mapper(${MODEL_NAME_CAMEL_CASE}, ${MODEL_NAME}Output.class);
    }

    @PostMapping
    public void adicionar(@RequestBody @Valid ${MODEL_NAME}Input ${MODEL_NAME_CAMEL_CASE}Input) {
        var ${MODEL_NAME_CAMEL_CASE} = mapper(${MODEL_NAME_CAMEL_CASE}Input, ${MODEL_NAME}.class);
        ${MODEL_NAME_CAMEL_CASE}Service.salvar(${MODEL_NAME_CAMEL_CASE});
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizar(@PathVariable Long id, @RequestBody @Valid ${MODEL_NAME}Input ${MODEL_NAME_CAMEL_CASE}Input) {
        var ${MODEL_NAME_CAMEL_CASE} = mapper(${MODEL_NAME_CAMEL_CASE}Input, ${MODEL_NAME}.class);
        ${MODEL_NAME_CAMEL_CASE}Service.atualizar(id, ${MODEL_NAME_CAMEL_CASE});
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void atualizarParcial(@PathVariable Long id, @RequestBody @Valid Map<String, Object> valores) {
        ${MODEL_NAME_CAMEL_CASE}Service.atualizar(id, valores);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        ${MODEL_NAME_CAMEL_CASE}Service.remover(id);
    }
}