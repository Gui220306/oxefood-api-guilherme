package br.com.ifpe.oxefood.modelo.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ClienteService {
   
    @Autowired //serve para instanciar um objeto e associa-lo ao atributo
   private ClienteRepository repository;

   @Transactional //Quando colocada em cima de uma função, ela abre um escopo de transação no banco para a mesma (ou tudo funciona, ounada vai ser rodado)
   public Cliente save(Cliente cliente) {

       cliente.setHabilitado(Boolean.TRUE);
      
       Cliente c = repository.save(cliente); // chama a função salvar, gravando no banco. retorna o objeto que acabou de ser gravado no banco
       
       return c;
    }
    //select * from clienteS
   public List<Cliente> listarTodos() {
  
        return repository.findAll();
    }
   //select * from cliente where
    public Cliente obterPorID(Long id) {

        return repository.findById(id).get();
    }
//função alterar
@Transactional
   public void update(Long id, Cliente clienteAlterado) {

      Cliente cliente = repository.findById(id).get(); //vai colsultar o cliente no banco
      cliente.setNome(clienteAlterado.getNome());
      cliente.setDataNascimento(clienteAlterado.getDataNascimento());
      cliente.setCpf(clienteAlterado.getCpf());
      cliente.setFoneCelular(clienteAlterado.getFoneCelular());
      cliente.setFoneFixo(clienteAlterado.getFoneFixo());
	//acima é onde se faz a alteração
      repository.save(cliente);//como o cliente 
  }
@Transactional
   public void delete(Long id) {

       Cliente cliente = repository.findById(id).get();
       cliente.setHabilitado(Boolean.FALSE);

       repository.save(cliente);
   }



}
