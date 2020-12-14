#if (${PACKAGE_NAME} && ${PACKAGE_NAME} != "")package ${PACKAGE_NAME};#end
#set($MODEL_NAME_CAMEL_CASE = $MODEL_NAME.substring(0, 1).toLowerCase() + $MODEL_NAME.substring(1))

import com.danielarrais.algafood.domain.exception.EntidadeEmUsoException;
import com.danielarrais.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.danielarrais.algafood.domain.model.${MODEL_NAME};
import com.danielarrais.algafood.domain.service.${MODEL_NAME}Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/${MODEL_NAME_CAMEL_CASE}s")
#parse("File Header.java")
public class ${MODEL_NAME}Controller {
    private final ${MODEL_NAME}Service ${MODEL_NAME_CAMEL_CASE}Service;

    public ${MODEL_NAME}Controller(${MODEL_NAME}Service ${MODEL_NAME_CAMEL_CASE}Service) {
        this.${MODEL_NAME_CAMEL_CASE}Service = ${MODEL_NAME_CAMEL_CASE}Service;
    }

    @GetMapping()
    public List<${MODEL_NAME}> listar() {
        return ${MODEL_NAME_CAMEL_CASE}Service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<${MODEL_NAME}> buscar(@PathVariable Long id) {
        ${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE} = ${MODEL_NAME_CAMEL_CASE}Service.buscar(id);

        return Optional
                .ofNullable(${MODEL_NAME_CAMEL_CASE})
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@RequestBody ${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}) {
        ${MODEL_NAME_CAMEL_CASE}Service.salvar(${MODEL_NAME_CAMEL_CASE});
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> atualizar(@PathVariable Long id, @RequestBody ${MODEL_NAME} ${MODEL_NAME_CAMEL_CASE}) {
        try {
            ${MODEL_NAME_CAMEL_CASE}Service.atualizar(id, ${MODEL_NAME_CAMEL_CASE});
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException exception) {
            return exception.isDependencia() ?
                    ResponseEntity.badRequest().body(exception.getMessage()) :
                    ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> atualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> valores) {
        try {
            ${MODEL_NAME_CAMEL_CASE}Service.atualizar(id, valores);
            return ResponseEntity.noContent().build();
        } catch (EntidadeNaoEncontradaException exception) {
            return exception.isDependencia() ?
                    ResponseEntity.badRequest().body(exception.getMessage()) :
                    ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            ${MODEL_NAME_CAMEL_CASE}Service.remover(id);
            return ResponseEntity.noContent().build();
        } catch (EntidadeEmUsoException exception) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (EntidadeNaoEncontradaException exception) {
            return ResponseEntity.notFound().build();
        }
    }
}