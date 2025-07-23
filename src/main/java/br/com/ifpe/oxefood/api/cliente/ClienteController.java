package br.com.ifpe.oxefood.api.cliente;

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

import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import br.com.ifpe.oxefood.modelo.cliente.EnderecoCliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController //determina a classe como controller
@RequestMapping("/api/cliente") // especifica o endereço do controller
@CrossOrigin //serve para o controller receber requisições do react
@Tag(
    name = "API Cliente",
    description = "API responsável pelos servidos de cliente no sistema"
)

public class ClienteController {
     @Autowired // vai instanciar objetos do tipo ClienteService e colocar dentro da variável, para que ela seja utilizada em todas funções do controller 
    private ClienteService clienteService;

     @Autowired
    private UsuarioService usuarioService;

s
@Operation(
       summary = "Serviço responsável por salvar um cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
   )

    @PostMapping // especifica que essa função vai receber requisição POST
    public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteRequest clienteRequest, HttpServletRequest request) {
        Cliente cliente = clienteService.save(clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }
@Operation(
       summary = "Serviço responsável por salvar um cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
   )
    @GetMapping // requisita todos os clientes
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }
@Operation(
       summary = "Serviço responsável por salvar um cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
   )
    @GetMapping("/{id}") // requisita cliente específico pelo ID
    public Cliente obterPorID(@PathVariable Long id) {
        return clienteService.obterPorID(id);
    }
@Operation(
       summary = "Serviço responsável por salvar um cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
   )
    @PutMapping("/{id}") // informar via URL o id do cliente | abaixo ele passa os dados do cliente alterado no corpo da requisição
    public ResponseEntity<Void> update(@PathVariable("id") Long id, @RequestBody @Valid ClienteRequest clienteRequest, HttpServletRequest request) {
        clienteService.update(id, clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
         //retorna 200 OK
        return ResponseEntity.ok().build();
    }
@Operation(
       summary = "Serviço responsável por salvar um cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
   )
    @DeleteMapping("/{id}") // desativa o cliente com base no ID
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }
    @Operation(
       summary = "Serviço responsável por salvar um cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
   )

    @PostMapping("/endereco/{clienteId}")
   public ResponseEntity<EnderecoCliente> adicionarEnderecoCliente(@PathVariable("clienteId") Long clienteId, @RequestBody @Valid EnderecoClienteRequest request) {

       EnderecoCliente endereco = clienteService.adicionarEnderecoCliente(clienteId, request.build());
       return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.CREATED);
   }
   @Operation(
       summary = "Serviço responsável por salvar um cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
   )

   @PutMapping("/endereco/{enderecoId}")
   public ResponseEntity<EnderecoCliente> atualizarEnderecoCliente(@PathVariable("enderecoId") Long enderecoId, @RequestBody EnderecoClienteRequest request) {

       EnderecoCliente endereco = clienteService.atualizarEnderecoCliente(enderecoId, request.build());
       return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.OK);
   }
    @Operation(
       summary = "Serviço responsável por salvar um cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
   )
   @DeleteMapping("/endereco/{enderecoId}")
   public ResponseEntity<Void> removerEnderecoCliente(@PathVariable("enderecoId") Long enderecoId) {

       clienteService.removerEnderecoCliente(enderecoId);
       return ResponseEntity.noContent().build();
   }


}
