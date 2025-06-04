package br.com.ifpe.oxefood.modelo.entregador;

import java.time.LocalDate;

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
@Table(name = "Entregador") // especifica ue essa classe vai ser convertida em uma tabela no banco
@SQLRestriction("habilitado = true") // serve para toda consulta feita nessa tabela, ser filtrada com o requisito mencionado (acrescenta uma clausula where)


@Builder
@Getter //adicona metodo get para cada atributo abaixo
@Setter // adicona metodo set para cada atributo abaixo
@AllArgsConstructor
@NoArgsConstructor

public class Entregador extends EntidadeAuditavel{
    @Column // cria uma coluna na tabela para esse atributo
   private String nome;

   @Column
   private String cpf;

    @Column
   private String rg;
   
   @Column (name= "dt_nasc") //altera o nome da coluna
   private LocalDate dataNascimento;

   @Column
   private String foneCelular;

   @Column
   private String foneFixo;

   @Column
   private int quanCorridas;

   @Column
   private double valorFrete;

   @Column
   private String rua;

   @Column
   private int numero;

   @Column
   private String bairro;

   @Column
   private String cidade;

   @Column
   private String cep;

   @Column
   private String uf;

   @Column
   private String complemento;

   @Column
   private boolean ativo;

}
