package com.danielarrais.algafood.core.storage;

import com.amazonaws.regions.Regions;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Data
@Component
@ConfigurationProperties("algafood.storage")
public class StorageProperties {
    private Local local;
    private S3 s3;
    private TipoStorage tipoStorage = TipoStorage.S3;

    public enum TipoStorage {
        S3, LOCAL
    }

    @Data
    public static class Local {
        private Path diretorioFotos;
    }

    @Data
    public static class S3 {
        private String bucket;
        private Regions regiao;
        private String chaveAcesso;
        private String idChaveAcesso;
        private String diretorioFotos;
    }
}
