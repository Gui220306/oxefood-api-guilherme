package br.com.ifpe.oxefood.util.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.ifpe.oxefood.modelo.acesso.Usuario;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// essa classe só serve para reaproveitar atributos
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class) // acrescenta anotações de auditoria

public abstract class EntidadeAuditavel extends EntidadeNegocio {
    
     @JsonIgnore //quando eu consultar o json do registro, ele não vai retornar essa informação
   @Version //incremneta uma versão, a cada alteração de registro
    private Long versao;

     @JsonIgnore
   @CreatedDate // criado um objeto, ele preenche esse campo automaticamente com a data e hora em q o objeto foi criado
    private LocalDate dataCriacao;

    @JsonIgnore
   @LastModifiedDate //toda vez que a pessoa altera o registro, ele preenche automaticamente esse campo
    private LocalDate dataUltimaModificacao;

    @JsonIgnore
    //@CreatedBy //quando o objeto é criado, ele preenche automaticamente com o id do usuário que criou
    @ManyToOne
    @JoinColumn
    private Usuario criadoPor; // Id do usuário que o criou

    @JsonIgnore
    @ManyToOne
    @JoinColumn
    private Usuario ultimaModificacaoPor; // Id do usuário que fez a última alteração

}

