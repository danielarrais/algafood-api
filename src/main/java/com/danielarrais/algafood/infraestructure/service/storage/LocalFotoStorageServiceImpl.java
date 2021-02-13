package com.danielarrais.algafood.infraestructure.service.storage;

import com.danielarrais.algafood.core.storage.StorageProperties;
import com.danielarrais.algafood.domain.service.FotoStorageService;
import com.danielarrais.algafood.infraestructure.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

//@Service
public class LocalFotoStorageServiceImpl implements FotoStorageService {

    @Autowired
    private final StorageProperties storageProperties;

    public LocalFotoStorageServiceImpl(StorageProperties storageProperties) {
        this.storageProperties = storageProperties;
    }

    @Override
    public void storage(Foto foto) {
        var filePath = pathFile(foto.getNomeArquivo());

        try {
            FileCopyUtils.copy(foto.getInputStream(), Files.newOutputStream(filePath));
        } catch (IOException e) {
            throw new StorageException("Não foi possível armazenar o arquivo", e);
        }
    }

    @Override
    public void delete(String fileName) {
        var filePath = pathFile(fileName);

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            throw new StorageException("Não foi possível remover o arquivo", e);
        }
    }

    @Override
    public FotoRecuperada recover(String fileName) {
        var filePath = pathFile(fileName);

        try {
            var inputStream = new FileInputStream(String.valueOf(filePath));
            return FotoRecuperada.builder().inputStream(inputStream).build();
        } catch (Exception e) {
            throw new StorageException("Não foi possível recuperar o arquivo", e);
        }
    }

    private Path pathFile(String fileName){
        var diretorioFotos = storageProperties.getLocal().getDiretorioFotos();
        return  diretorioFotos.resolve(Path.of(fileName));
    }
}
