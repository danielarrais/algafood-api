#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

import com.danielarrais.algafood.domain.model.${MODEL_NAME};
import com.danielarrais.algafood.domain.repository.${MODEL_NAME}Repository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return ${MODEL_NAME_CAMEL_CASE}Repository.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<${MODEL_NAME}> buscar(@PathVariable Long id) {
        ${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE} = ${MODEL_NAME_CAMEL_CASE}Repository.buscar(id);

        return Optional
                .ofNullable(${MODEL_NAME_CAMEL_CASE})
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody ${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}) {
        ${MODEL_NAME_CAMEL_CASE}Repository.adicionar(${MODEL_NAME_CAMEL_CASE});
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}) {
        return Optional
                .ofNullable(${MODEL_NAME_CAMEL_CASE}Repository.buscar(id))
                .map(${MODEL_NAME_CAMEL_CASE}Atual -> {
                    BeanUtils.copyProperties(${MODEL_NAME_CAMEL_CASE}, ${MODEL_NAME_CAMEL_CASE}Atual, "id");
                    ${MODEL_NAME_CAMEL_CASE}Repository.adicionar(${MODEL_NAME_CAMEL_CASE}Atual);

                    return ResponseEntity.ok((Void) null);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        return Optional
                .ofNullable(${MODEL_NAME_CAMEL_CASE}Repository.buscar(id))
                .map(${MODEL_NAME_CAMEL_CASE} -> {
                    ${MODEL_NAME_CAMEL_CASE}Repository.remover(${MODEL_NAME_CAMEL_CASE});

                    return ResponseEntity.status(HttpStatus.NO_CONTENT).body((Void) null);
                }).orElse(ResponseEntity.notFound().build());
    }
}

