package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.infraestructure.service.storage.LocalFotoStorageServiceImpl;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {
    void storage(LocalFotoStorageServiceImpl.Foto foto);

    default String gerarNomeArquivo(String nomeOriginal) {
        return StringUtils.join(UUID.randomUUID().toString(), "_", nomeOriginal);
    }

    void delete(String fileName);
    FotoRecuperada recover(String fileName);

    @Getter
    @Builder
    class Foto {
        private final String nomeArquivo;
        private final String contentType;
        private final InputStream inputStream;
    }

    @Getter
    @Builder
    class FotoRecuperada {
        private final String url;
        private final InputStream inputStream;

        public boolean temUrl() {
            return StringUtils.isNotBlank(url);
        }
    }
}
