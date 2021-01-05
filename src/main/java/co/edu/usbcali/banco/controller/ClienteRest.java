package co.edu.usbcali.banco.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.banco.dto.ClienteDTO;
import co.edu.usbcali.banco.logica.IClienteLogica;
import co.edu.usbcali.banco.mapper.IClienteMapper;
import co.edu.usbcali.banco.modelo.Cliente;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins="*")
public class ClienteRest {
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private IClienteMapper clienteMapper;
	
	@GetMapping("/consultarClientePorId/{id}")
	public ClienteDTO consultarPorId(@PathVariable("id") BigDecimal id) {
		Cliente cliente = clienteLogica.consultarPorId(id);
		ClienteDTO clienteDTO = clienteMapper.clienteToClienteDTO(cliente);
		return clienteDTO;
	}
	
	@GetMapping("/consultarTodos/")
	public List<ClienteDTO> consultarTodos() {
		List<Cliente> listaCliente = clienteLogica.consultarTodo();
		List<ClienteDTO> listaClienteDTO = clienteMapper.listaClienteToListaClienteDTO(listaCliente);
		return listaClienteDTO;
	}
	
	@PostMapping("/crear")
	public void crear(@RequestBody ClienteDTO clienteDTO)throws Exception{
		Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO);
		clienteLogica.grabar(cliente);
	}
	
	@PutMapping("/modificar")
	public void modificar(@RequestBody ClienteDTO clienteDTO)throws Exception{
		Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO);
		clienteLogica.modificar(cliente);
	}
	
	@DeleteMapping("/borrar/{id}")
	private void borrar(@PathVariable("id")BigDecimal id)throws Exception{
		Cliente cliente = clienteLogica.consultarPorId(id);
		clienteLogica.borrar(cliente);
		

	}
}
