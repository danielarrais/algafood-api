package com.danielarrais.algafood.infraestructure.service.storage;

import com.danielarrais.algafood.domain.model.FotoStorageService;
import com.danielarrais.algafood.infraestructure.exceptions.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FotoStorageServiceImpl implements FotoStorageService {

    @Value("${algafood.storage.local.path-fotos}")
    private Path diretorioFotos;

    @Override
    public void storage(Foto foto) {
        var arquivoPath = diretorioFotos.resolve(Path.of(foto.getNomeArquivo()));

        try {
            FileCopyUtils.copy(foto.getInputStream(), Files.newOutputStream(arquivoPath));
        } catch (IOException e) {
            throw new StorageException("Não foi possível armazenar o arquivo", e);
        }
    }

    @Override
    public void delete(String fileName) {
        var arquivoPath = diretorioFotos.resolve(Path.of(fileName));

        try {
            Files.deleteIfExists(arquivoPath);
        } catch (IOException e) {
            throw new StorageException("Não foi possível remover o arquivo", e);
        }
    }
}
