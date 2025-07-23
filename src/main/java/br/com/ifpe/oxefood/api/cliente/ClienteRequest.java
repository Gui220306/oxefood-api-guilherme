package br.com.ifpe.oxefood.api.cliente;

import java.time.LocalDate;
import java.util.Arrays;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.ifpe.oxefood.modelo.acesso.Perfil;
import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // gera get e set simultâneamente
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {
    
     @NotBlank(message = "O e-mail é de preenchimento obrigatório")
    @Email
    private String email;

    @NotBlank(message = "A senha é de preenchimento obrigatório")
    private String password;

    
    //anotação para validação de entrada de requisições
    @NotNull(message = "O Nome é de preenchimento obrigatório") //verifica se foi preenchido
   @NotEmpty(message = "O Nome é de preenchimento obrigatório")//verifica se não está vazio
   @Length(max = 100, message = "O Nome deverá ter no máximo {max} caracteres")//verifica se o tamanho máximo é 100 caracteres
   private String nome;

    @JsonFormat(pattern = "dd/MM/yyyy") // especificar o formato da data
   private LocalDate dataNascimento;
   
   @NotBlank(message = "O CPF é de preenchimento obrigatório") //verifica se não está vazio e se foi preenc
   @CPF
   private String cpf; // validação de CPF
   
   @Length(min = 8, max = 20, message = "O campo Fone tem que ter entre {min} e {max} caracteres") //Determina um tamanho mínimo e máximo para o campo
   private String foneCelular;

   private String foneFixo;
//novo(build usuário)
   public Usuario buildUsuario() {
       return Usuario.builder()
           .username(email)
           .password(password)
           .roles(Arrays.asList(new Perfil(Perfil.ROLE_CLIENTE)))
           .build();
   }


   public Cliente build() {

       Cliente c = Cliente.builder() // tem a função de criar objetos com os seguintes atributos
           .usuario(buildUsuario())
           .nome(nome)
           .dataNascimento(dataNascimento)
           .cpf(cpf)
           .foneCelular(foneCelular)
           .foneFixo(foneFixo)
           .build();

           return c;
   }

}
