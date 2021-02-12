package com.danielarrais.algafood.domain.service;

import com.danielarrais.algafood.infraestructure.service.storage.LocalFotoStorageServiceImpl;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

public interface FotoStorageService {
    void storage(LocalFotoStorageServiceImpl.Foto foto);

    default String gerarNomeArquivo(String nomeOriginal) {
        return StringUtils.join(UUID.randomUUID().toString(), "_", nomeOriginal);
    }

    void delete(String fileName);
    FileInputStream recover(String fileName);

    @Data
    @Builder
    class Foto {
        private String nomeArquivo;
        private String contentType;
        private InputStream inputStream;
    }
}
