package com.danielarrais.algafood.infraestructure.service.storage;

import com.danielarrais.algafood.domain.service.FotoStorageService;
import com.danielarrais.algafood.infraestructure.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//@Service
public class LocalFotoStorageServiceImpl implements FotoStorageService {

    @Value("${algafood.storage.local.path-fotos}")
    private Path diretorioFotos;

    @Override
    public void storage(Foto foto) {
        var filePath = diretorioFotos.resolve(Path.of(foto.getNomeArquivo()));

        try {
            FileCopyUtils.copy(foto.getInputStream(), Files.newOutputStream(filePath));
        } catch (IOException e) {
            throw new StorageException("Não foi possível armazenar o arquivo", e);
        }
    }

    @Override
    public void delete(String fileName) {
        var filePath = diretorioFotos.resolve(Path.of(fileName));

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new StorageException("Não foi possível remover o arquivo", e);
        }
    }

    @Override
    public FotoRecuperada recover(String fileName) {
        var filePath = diretorioFotos.resolve(Path.of(fileName));

        try {
            var inputStream = new FileInputStream(String.valueOf(filePath));
            return FotoRecuperada.builder().inputStream(inputStream).build();
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar o arquivo", e);
        }
    }
}
