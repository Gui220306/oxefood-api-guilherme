package br.com.ifpe.oxefood.modelo.cliente;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity //transforma a classe em uma entidade persistível pela jpa, através dessa anotação consigo usar objetos para gravar, alterar no banco de dados 
@Table(name = "Endereço") // especifica ue essa classe vai ser convertida em uma tabela no banco
@SQLRestriction("habilitado = true") // serve para toda consulta feita nessa tabela, ser filtrada com o requisito mencionado (acrescenta uma clausula where)


@Builder
@Getter //adicona metodo get para cada atributo abaixo
@Setter // adicona metodo set para cada atributo abaixo
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoCliente extends EntidadeAuditavel {
    @JsonIgnore
   @ManyToOne
   private Cliente cliente;

   @Column
   private String rua;

   @Column
   private String numero;

   @Column
   private String bairro;
   
   @Column
   private String cep;

   @Column
   private String cidade;

   @Column
   private String estado;

   @Column
   private String complemento;
  
}
