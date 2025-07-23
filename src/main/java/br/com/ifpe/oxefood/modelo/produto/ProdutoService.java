package br.com.ifpe.oxefood.modelo.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.oxefood.util.exception.ProdutoException;
import jakarta.transaction.Transactional;

@Service
public class ProdutoService {
     @Autowired //serve para instanciar um objeto e associa-lo ao atributo
   private ProdutoRepository repository;

   @Transactional //Quando colocada em cima de uma função, ela abre um escopo de transação no banco para a mesma (ou tudo funciona, ounada vai ser rodado)
   public Produto save(Produto produto) {
    if (produto.getValorUnitario() < 10) {
        //interrompe a execução do código e exibe a mensagem
	    throw new ProdutoException(ProdutoException.MSG_VALOR_MINIMO_PRODUTO);
	}


       produto.setHabilitado(Boolean.TRUE);
      
       Produto p = repository.save(produto); // chama a função salvar, gravando no banco. retorna o objeto que acabou de ser gravado no banco
       
       return p;
   }

   //select * from produto
   public List<Produto> listarTodos() {
  
        return repository.findAll();
    }
   //select * from produto where
    public Produto obterPorID(Long id) {

        return repository.findById(id).get();
    }

  @Transactional
   public void update(Long id, Produto produtoAlterado) {

      Produto produto = repository.findById(id).get(); //vai colsultar o cliente no banco
      produto.setTitulo(produtoAlterado.getTitulo());
      produto.setCategoria(produtoAlterado.getCategoria());
      produto.setCodigoProduto(produtoAlterado.getCodigoProduto());
      produto.setDescricao(produtoAlterado.getDescricao());
      produto.setValorUnitario(produtoAlterado.getValorUnitario());
      produto.setTempoMinimo(produtoAlterado.getTempoMinimo());
       produto.setTempoMaximo(produtoAlterado.getTempoMaximo());
	//acima é onde se faz a alteração
      repository.save(produto);//como o cliente 
  }
  //torna o setHabilitado em falso
@Transactional
   public void delete(Long id) {

       Produto produto = repository.findById(id).get();
       produto.setHabilitado(Boolean.FALSE);

       repository.save(produto);
   }
}

