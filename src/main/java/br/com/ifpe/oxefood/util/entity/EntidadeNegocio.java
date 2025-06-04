package br.com.ifpe.oxefood.util.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode(of = { "id" })
@MappedSuperclass
public abstract class EntidadeNegocio implements Serializable {

    @Id //transforma atributo em chave primária
   @GeneratedValue(strategy = GenerationType.SEQUENCE) //inserido uma iformação no banco, ele auto incrementa a variável, ela nunca zera
    private Long id;

    @JsonIgnore
   @Column
    private Boolean habilitado;
    
}

