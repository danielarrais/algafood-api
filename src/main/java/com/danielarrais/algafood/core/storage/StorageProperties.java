package com.danielarrais.algafood.core.storage;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Data
@Component
@Configuration("algafood.storage")
public class StorageProperties {
    private Local local;

    @Data
    static class Local {
        private Path diretorioFotos;
    }

    @Data
    static class S3 {
        private String bucket;
        private String regiao;
        private String chaveAcesso;
        private String idChaveAcesso;
        private String diretorioFotos;
    }
}
