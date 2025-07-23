package br.com.ifpe.oxefood.api.produto;

import br.com.ifpe.oxefood.modelo.produto.CategoriaProduto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // gera get e set simultâneamente
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaProdutoRequest {

   private String descricao;

   public CategoriaProduto build() {

       CategoriaProduto c = CategoriaProduto.builder() // tem a função de criar objetos com os seguintes atributos
           .descricao(descricao)
           .build();

           return c;

   }
}
