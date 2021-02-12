package com.danielarrais.algafood.domain.model;

import com.danielarrais.algafood.infraestructure.service.storage.FotoStorageServiceImpl;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {
    void storage(FotoStorageServiceImpl.Foto foto);

    default String gerarNomeArquivo(String nomeOriginal) {
        return StringUtils.join(UUID.randomUUID().toString(), "_", nomeOriginal);
    }

    void delete(String fileName);

    @Data
    @Builder
    class Foto {
        private String nomeArquivo;
        private InputStream inputStream;
    }
}
