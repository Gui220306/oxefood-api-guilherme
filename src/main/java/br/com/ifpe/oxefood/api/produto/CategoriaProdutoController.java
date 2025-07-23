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

import br.com.ifpe.oxefood.modelo.produto.CategoriaProduto;
import br.com.ifpe.oxefood.modelo.produto.CategoriaProdutoService;

@RestController //determina a classe como controller
@RequestMapping("/api/categoria") // especifica o endereço do controller
@CrossOrigin
public class CategoriaProdutoController {
              @Autowired //vai instanciar objetos do tipo ClienteService e colocar dentro da variável, para que ela seja utilizada em  todas funções do controller 
   private CategoriaProdutoService categoriaProdutoService;

   @PostMapping//especifica que essa função vai receber requisição post
   public ResponseEntity<CategoriaProduto> save(@RequestBody CategoriaProdutoRequest request) {

       CategoriaProduto categoria = categoriaProdutoService.save(request.build());
       return new ResponseEntity<CategoriaProduto>(categoria, HttpStatus.CREATED);
   }
   @GetMapping
    public List<CategoriaProduto> listarTodos() {
        return categoriaProdutoService.listarTodos();
    }

    @GetMapping("/{id}")
    public CategoriaProduto obterPorID(@PathVariable Long id) {
        return categoriaProdutoService.obterPorID(id);
    }
   
   @PutMapping("/{id}") //informar via url o id do produto | abaixo ele passa os dados do produto alterado no corpo da requisição
 public ResponseEntity<CategoriaProduto> update(@PathVariable("id") Long id, @RequestBody CategoriaProdutoRequest request) {
  //passando os dados para a função update
       categoriaProdutoService.update(id, request.build());
       return ResponseEntity.ok().build();
 }
@DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       categoriaProdutoService.delete(id);
       return ResponseEntity.ok().build();
   }
}
