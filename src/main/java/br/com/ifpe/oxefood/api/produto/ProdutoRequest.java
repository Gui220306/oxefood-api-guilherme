package br.com.ifpe.oxefood.api.produto;

import br.com.ifpe.oxefood.modelo.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // gera get e set simultâneamente
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoRequest {
   
   private Long idCategoria;
  
   private String titulo;

   private String codigoProduto;

   private String descricao;

   private double valorUnitario;

   private int tempoMinimo;

   private int tempoMaximo;

   public Produto build() {

       Produto p = Produto.builder() // tem a função de criar objetos com os seguintes atributos
           .titulo(titulo)
           .codigoProduto(codigoProduto)
           .descricao(descricao)
           .valorUnitario(valorUnitario)
           .tempoMinimo(tempoMinimo)
           .tempoMaximo(tempoMaximo)
           .build();

           return p;

   }
}
