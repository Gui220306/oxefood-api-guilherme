package br.com.ifpe.oxefood.api.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.oxefood.modelo.produto.Produto;
import br.com.ifpe.oxefood.modelo.produto.ProdutoService;

@RestController //determina a classe como controller
@RequestMapping("/api/produto") // especifica o endereço do controller
@CrossOrigin
public class ProdutoController {
          @Autowired //vai instanciar objetos do tipo ClienteService e colocar dentro da variável, para que ela seja utilizada em  todas funções do controller 
   private ProdutoService produtoService;

   @PostMapping//especifica que essa função vai receber requisição post
   public ResponseEntity<Produto> save(@RequestBody ProdutoRequest request) {

       Produto produto = produtoService.save(request.build());
       return new ResponseEntity<Produto>(produto, HttpStatus.CREATED);
   }
   @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto obterPorID(@PathVariable Long id) {
        return produtoService.obterPorID(id);
    }
   
   @PutMapping("/{id}") //informar via url o id do produto | abaixo ele passa os dados do produto alterado no corpo da requisição
 public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody ProdutoRequest request) {
  //passando os dados para a função update
       produtoService.update(id, request.build());
       return ResponseEntity.ok().build();
 }
@DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       produtoService.delete(id);
       return ResponseEntity.ok().build();
   }
}
