package biz.belcorp.ssicc.service.sisicc.framework.beans;

import java.util.Map;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;

/**
 * Clase que encapsula los parametros para la ejecucion de los envios de Correos
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
public class MailParams {

	private Usuario usuario;
	
	private Pais pais;

	private Map queryParams;

	private InterfazException interfazException;



	public MailParams() {
		
	}
	
	public Map getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(Map queryParams) {
		this.queryParams = queryParams;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public InterfazException getInterfazException() {
		return interfazException;
	}

	public void setInterfazException(InterfazException interfazException) {
		this.interfazException = interfazException;
	}

	/**
	 * @return Returns the pais.
	 */
	public Pais getPais() {
		return pais;
	}

	/**
	 * @param pais The pais to set.
	 */
	public void setPais(Pais pais) {
		this.pais = pais;
	}
			
	

		
}
