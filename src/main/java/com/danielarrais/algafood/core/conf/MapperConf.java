package com.danielarrais.algafood.core.conf;


import com.danielarrais.algafood.api.v1.dto.output.restaurante.EnderecoOutput;
import com.danielarrais.algafood.api.v2.dto.input.cidade.CidadeInputV2;
import com.danielarrais.algafood.domain.model.Cidade;
import com.danielarrais.algafood.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConf {

    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();

        modelMapper.createTypeMap(CidadeInputV2.class, Cidade.class)
                .addMappings(mapper -> mapper.skip(Cidade::setId));

        var enderecoToOutputTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoOutput.class);

        enderecoToOutputTypeMap
                .addMapping(src -> src.getCidade().getEstado().getNome(),
                        (destino, valor) -> destino.getCidade().setEstado((String) valor));

        return modelMapper;
    }
}
