package br.com.ifpe.oxefood.api.cliente;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // gera get e set simultâneamente
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

   private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy") // especificar o formato da data
   private LocalDate dataNascimento;

   private String cpf;

   private String foneCelular;

   private String foneFixo;

   public Cliente build() {

       Cliente c = Cliente.builder() // tem a função de criar objetos com os seguintes atributos
           .nome(nome)
           .dataNascimento(dataNascimento)
           .cpf(cpf)
           .foneCelular(foneCelular)
           .foneFixo(foneFixo)
           .build();

           return c;
   }

}
