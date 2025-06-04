package br.com.ifpe.oxefood.api.entregador;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // gera get e set simultâneamente
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorRequest {
    
   private String nome;

   private String cpf;

   private String rg;
   
    @JsonFormat(pattern = "dd/MM/yyyy")
   private LocalDate dataNascimento;

   private String foneCelular;

   private String foneFixo;

   private int quanCorridas;

   private double valorFrete;

   private String rua;

   private int numero;

   private String bairro;

   private String cidade;

   private String cep;

   private String uf;

   private String complemento;

   private boolean ativo;

   public Entregador build() {

       Entregador e = Entregador.builder() // tem a função de criar objetos com os seguintes atributos
           .nome(nome)
           .cpf(cpf)
           .rg(rg)
           .dataNascimento(dataNascimento)
           .foneCelular(foneCelular)
           .foneFixo(foneFixo)
           .quanCorridas(quanCorridas)
           .valorFrete(valorFrete)
           .rua(rua)
           .numero(numero)
           .bairro(bairro)
           .cidade(cidade)
           .cep(cep)
           .uf(uf)
           .complemento(complemento)
           .ativo(ativo)
           .build();

           return e;
   }

}
