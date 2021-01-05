package co.edu.usbcali.banco.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.dto.ClienteDTO;
import co.edu.usbcali.banco.logica.ITipoDocumentoLogica;
import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;

@Component
@Scope("singleton")
public class ClienteMapper implements IClienteMapper {

	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	@Override
	@Transactional(readOnly=true)
	public ClienteDTO clienteToClienteDTO(Cliente cliente) {
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setActivo(cliente.getActivo());
		clienteDTO.setClieId(cliente.getClieId());
		clienteDTO.setDireccion(cliente.getDireccion());
		clienteDTO.setEmail(cliente.getEmail());
		clienteDTO.setNombre(cliente.getNombre());
		clienteDTO.setTelefono(cliente.getTelefono());
		
		TipoDocumento tipoDocumento = tipoDocumentoLogica.consultarPorId(cliente.getTipoDocumento().getTdocId());
		clienteDTO.setIdTipoDocumento(tipoDocumento.getTdocId());
		clienteDTO.setNombreTipoDocumento(tipoDocumento.getNombre());
		return clienteDTO;
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente clienteDTOToCliente(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setActivo(clienteDTO.getActivo());
		cliente.setClieId(clienteDTO.getClieId());
		cliente.setDireccion(clienteDTO.getDireccion());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setTelefono(clienteDTO.getTelefono());
		
		TipoDocumento tipoDocumento = tipoDocumentoLogica.consultarPorId(clienteDTO.getIdTipoDocumento());
		cliente.setTipoDocumento(tipoDocumento);
		return cliente;
	}

	@Override
	@Transactional(readOnly=true)
	public List<ClienteDTO> listaClienteToListaClienteDTO(List<Cliente> listaCliente) {
		List<ClienteDTO> listaClienteDTO=new ArrayList<>();
		for (Cliente cliente : listaCliente) {
			listaClienteDTO.add(clienteToClienteDTO(cliente));
		}
		return listaClienteDTO;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cliente> listaClienteDTOToListaCliente(List<ClienteDTO> listaClienteDTO) {
		List<Cliente> listaCliente=new ArrayList<>();
		for (ClienteDTO clienteDTO : listaClienteDTO) {
			listaCliente.add(clienteDTOToCliente(clienteDTO));
		}
		return listaCliente;
	}

}
