package br.com.ifpe.oxefood.modelo.cliente;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity //transforma a classe em uma entidade persistível pela jpa, através dessa anotação consigo usar objetos para gravar, alterar no banco de dados 
@Table(name = "Cliente") // especifica ue essa classe vai ser convertida em uma tabela no banco
@SQLRestriction("habilitado = true") // serve para toda consulta feita nessa tabela, ser filtrada com o requisito mencionado (acrescenta uma clausula where)


@Builder
@Getter //adicona metodo get para cada atributo abaixo
@Setter // adicona metodo set para cada atributo abaixo
@AllArgsConstructor
@NoArgsConstructor
//Lembre-se de conectar o docker, o comando é: 
public class Cliente extends EntidadeAuditavel {
    
    @OneToOne
   @JoinColumn(nullable = false) // Faz um join entre a o usuário e o cliente
   private Usuario usuario;

    

   @OneToMany(mappedBy = "cliente", orphanRemoval = true, fetch = FetchType.EAGER)
   @Fetch(FetchMode.SUBSELECT)
   private List<EnderecoCliente> enderecos;

                            //length só serve para o tipo String
   @Column (nullable = false, length = 100) // cria uma coluna na tabela para esse atributo e determina que não pode ser nulo e o tamanho máximo é 100 caracteres
   private String nome;

   @Column (name= "dt_nasc") //altera o nome da coluna
   private LocalDate dataNascimento;

   @Column (unique = true) //determina que o campo não pode ser repetido
   private String cpf;

   @Column
   private String foneCelular;

   @Column
   private String foneFixo;

}
