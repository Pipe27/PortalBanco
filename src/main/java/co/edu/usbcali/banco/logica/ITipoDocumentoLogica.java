package co.edu.usbcali.banco.logica;

import java.util.List;

import co.edu.usbcali.banco.modelo.TipoDocumento;

public interface ITipoDocumentoLogica {
	
	public void grabar(TipoDocumento tipoDocumento)throws Exception;
	public void modificar(TipoDocumento tipoDocumento)throws Exception;
	public void borrar(TipoDocumento tipoDocumento)throws Exception;
	public TipoDocumento consultarPorId(long tdocId);
	public List<TipoDocumento> consultarTodo();

}
