package br.com.ifpe.oxefood.modelo.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CategoriaProdutoService {
    @Autowired //serve para instanciar um objeto e associa-lo ao atributo
   private CategoriaProdutoRepository repository;

   @Transactional //Quando colocada em cima de uma função, ela abre um escopo de transação no banco para a mesma (ou tudo funciona, ounada vai ser rodado)
   public CategoriaProduto save(CategoriaProduto categoria) {

       categoria.setHabilitado(Boolean.TRUE);
      
       CategoriaProduto c = repository.save(categoria); // chama a função salvar, gravando no banco. retorna o objeto que acabou de ser gravado no banco
       
       return c;
   }

   //select * from produto
   public List<CategoriaProduto> listarTodos() {
  
        return repository.findAll();
    }
   //select * from produto where
    public CategoriaProduto obterPorID(Long id) {

        return repository.findById(id).get();
    }

  @Transactional
   public void update(Long id, CategoriaProduto categoriaAlterada) {

      CategoriaProduto categoria = repository.findById(id).get(); //vai colsultar o cliente no banco
      categoria.setDescricao(categoriaAlterada.getDescricao());
	//acima é onde se faz a alteração
      repository.save(categoria);//como o cliente 
  }
  //torna o setHabilitado em falso
@Transactional
   public void delete(Long id) {

       CategoriaProduto categoria = repository.findById(id).get();
       categoria.setHabilitado(Boolean.FALSE);

       repository.save(categoria);
   }
}
