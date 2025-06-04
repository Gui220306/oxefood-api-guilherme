package br.com.ifpe.oxefood.modelo.entregador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class EntregadorService {
     @Autowired //serve para instanciar um objeto e associa-lo ao atributo
   private EntregadorRepository repository;

   @Transactional //Quando colocada em cima de uma função, ela abre um escopo de transação no banco para a mesma (ou tudo funciona, ounada vai ser rodado)
   public Entregador save(Entregador entregador) {

       entregador.setHabilitado(Boolean.TRUE);
      
       Entregador e = repository.save(entregador); // chama a função salvar, gravando no banco. retorna o objeto que acabou de ser gravado no banco
       
       return e;
   }
   
   //select * from entregador
   public List<Entregador> listarTodos() {
  
        return repository.findAll();
    }
   //select * from entregador where
    public Entregador obterPorID(Long id) {

        return repository.findById(id).get();
    }

    @Transactional
   public void update(Long id, Entregador entregadorAlterado) {

      Entregador entregador = repository.findById(id).get(); //vai colsultar o cliente no banco
      entregador.setNome(entregadorAlterado.getNome());
      entregador.setRg(entregadorAlterado.getRg());
      entregador.setCpf(entregadorAlterado.getCpf());
      entregador.setDataNascimento(entregadorAlterado.getDataNascimento());
      entregador.setFoneCelular(entregadorAlterado.getFoneCelular());
      entregador.setFoneFixo(entregadorAlterado.getFoneFixo());
      entregador.setQuanCorridas(entregadorAlterado.getQuanCorridas());
      entregador.setValorFrete(entregadorAlterado.getValorFrete());
      entregador.setRua(entregadorAlterado.getRua());
      entregador.setNumero(entregadorAlterado.getNumero());
      entregador.setBairro(entregadorAlterado.getBairro());
      entregador.setCidade(entregadorAlterado.getCidade());
      entregador.setCep(entregadorAlterado.getCep());
      entregador.setUf(entregadorAlterado.getUf());
      entregador.setComplemento(entregadorAlterado.getComplemento());
      entregador.setAtivo(entregadorAlterado.isAtivo());
      

	//acima é onde se faz a alteração
      repository.save(entregador);//como o cliente 
  }

@Transactional
   public void delete(Long id) {

       Entregador entregador = repository.findById(id).get();
       entregador.setHabilitado(Boolean.FALSE);

       repository.save(entregador);
   }
}
