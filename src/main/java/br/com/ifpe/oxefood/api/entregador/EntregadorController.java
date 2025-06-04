package br.com.ifpe.oxefood.api.entregador;

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

import br.com.ifpe.oxefood.modelo.entregador.Entregador;
import br.com.ifpe.oxefood.modelo.entregador.EntregadorService;

@RestController //determina a classe como controller
@RequestMapping("/api/entregador") // especifica o endereço do controller
@CrossOrigin 
public class EntregadorController {
     @Autowired //vai instanciar objetos do tipo ClienteService e colocar dentro da variável, para que ela seja utilizada em  todas funções do controller 
   private EntregadorService entregadorService;

   @PostMapping//especifica que essa função vai receber requisição post
   public ResponseEntity <Entregador> save(@RequestBody EntregadorRequest request) {

       Entregador entregador = entregadorService.save(request.build());
       return new ResponseEntity<Entregador>(entregador, HttpStatus.CREATED);
   }
   @GetMapping //vai retornar uma lista com todos os clientes
    public List<Entregador> listarTodos() {

        return entregadorService.listarTodos();
    }

  
    @GetMapping("/{id}") //retorna o cliente pesquisado pelo id
    public Entregador obterPorID(@PathVariable Long id) {
        return entregadorService.obterPorID(id);
    }

     @PutMapping("/{id}") //informar via url o id do produto | abaixo ele passa os dados do produto alterado no corpo da requisição
 public ResponseEntity <Entregador> update(@PathVariable("id") Long id, @RequestBody EntregadorRequest request) {
  //passando os dados para a função update
       entregadorService.update(id, request.build());
       return ResponseEntity.ok().build();
 }
@DeleteMapping("/{id}")
   public ResponseEntity<Void> delete(@PathVariable Long id) {

       entregadorService.delete(id);
       return ResponseEntity.ok().build();
   }
}


