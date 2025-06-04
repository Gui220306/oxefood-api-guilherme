package br.com.ifpe.oxefood.modelo.produto;

import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity //transforma a classe em uma entidade persistível pela jpa, através dessa anotação consigo usar objetos para gravar, alterar no banco de dados 
@Table(name = "Produto") // especifica ue essa classe vai ser convertida em uma tabela no banco
@SQLRestriction("habilitado = true") // serve para toda consulta feita nessa tabela, ser filtrada com o requisito mencionado (acrescenta uma clausula where)


@Builder
@Getter //adicona metodo get para cada atributo abaixo
@Setter // adicona metodo set para cada atributo abaixo
@AllArgsConstructor
@NoArgsConstructor
public class Produto extends EntidadeAuditavel {
     @Column // cria uma coluna na tabela para esse atributo
   private String titulo;

   @Column (name= "Codigo") //altera o nome da coluna
   private String codigoProduto;

   @Column
   private String descricao;

   @Column
   private Double valorUnitario;

   @Column
   private int tempoMinimo;
  
   @Column
   private int tempoMaximo;
}
