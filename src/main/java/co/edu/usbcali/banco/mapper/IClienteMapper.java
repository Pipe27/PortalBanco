package co.edu.usbcali.banco.mapper;

import java.util.List;

import co.edu.usbcali.banco.dto.ClienteDTO;
import co.edu.usbcali.banco.modelo.Cliente;

public interface IClienteMapper {
	public ClienteDTO clienteToClienteDTO(Cliente cliente);
	public Cliente clienteDTOToCliente(ClienteDTO clienteDTO);
	
	public List<ClienteDTO> listaClienteToListaClienteDTO(List<Cliente> listaCliente);
	public List<Cliente> listaClienteDTOToListaCliente(List<ClienteDTO> listaClienteDTO);
}
