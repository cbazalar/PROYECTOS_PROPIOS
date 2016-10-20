/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sto.framework.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.HistoricoTipoDocumento;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;

/**
 * @author USER
 *
 */
public class DocumentoSTOParams implements Cloneable{
	private TipoDocumentoDigitado tipoDocumentoDigitado;

	private HistoricoTipoDocumento historicoTipoDocumento;

	private Usuario usuario;

	private Map queryParams;	

	private StringBuffer logBuffer;
	
	private List stoList;
	
//	private InterfazException interfazException;

	
	/**
	 * @return the tipoDocumentoDigitado
	 */
	public TipoDocumentoDigitado getTipoDocumentoDigitado() {
		return tipoDocumentoDigitado;
	}
	
	public DocumentoSTOParams() {
		super();
	}

	public DocumentoSTOParams(TipoDocumentoDigitado tipoDocumentoDigitado,
		HistoricoTipoDocumento historicoTipoDocumento, Usuario usuario,
		Map queryParams, List stoList) {
	super();
	this.tipoDocumentoDigitado = tipoDocumentoDigitado;
	this.historicoTipoDocumento = historicoTipoDocumento;
	this.usuario = usuario;
	this.queryParams = queryParams;
	this.stoList = stoList;
	prepareQueryParams();
}
	
	 public Object clone() throws CloneNotSupportedException{
         
		 DocumentoSTOParams obj = null;
		 
		 obj = (DocumentoSTOParams)super.clone();
		 obj.historicoTipoDocumento = (HistoricoTipoDocumento)obj.historicoTipoDocumento.clone();
//		 obj.usuario = (Usuario)obj.usuario.clone();
		 obj.queryParams = new HashMap();
//		 obj.stoList = (List)obj.stoLi

         return obj;
     }


	 /**
		 * Devuelve el Map de parametros del query. Se puede extender y sobrescribir
		 * este metodo en caso se requieran mas parametros.
		 * 
		 * @param documentoSTOParams
		 *            parametros STO
		 * @return Map con parametros del query
		 */
		public void prepareQueryParams(){
			queryParams.put("tipoDocumento", tipoDocumentoDigitado);
			queryParams.put("historico", historicoTipoDocumento);
			queryParams.put("usuario", usuario);
			queryParams.put("tipoDocumentoDigitado", tipoDocumentoDigitado.getCodTipoDocu());
		}
		
	/**
	 * @param tipoDocumentoDigitado the tipoDocumentoDigitado to set
	 */
	public void setTipoDocumentoDigitado(TipoDocumentoDigitado tipoDocumentoDigitado) {
		this.tipoDocumentoDigitado = tipoDocumentoDigitado;
	}

	/**
	 * @return the historicoTipoDocumento
	 */
	public HistoricoTipoDocumento getHistoricoTipoDocumento() {
		return historicoTipoDocumento;
	}

	/**
	 * @param historicoTipoDocumento the historicoTipoDocumento to set
	 */
	public void setHistoricoTipoDocumento(
			HistoricoTipoDocumento historicoTipoDocumento) {
		this.historicoTipoDocumento = historicoTipoDocumento;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the queryParams
	 */
	public Map getQueryParams() {
		return queryParams;
	}

	/**
	 * @param queryParams the queryParams to set
	 */
	public void setQueryParams(Map queryParams) {
		this.queryParams = queryParams;
	}

	/**
	 * @return the logBuffer
	 */
	public StringBuffer getLogBuffer() {
		return logBuffer;
	}

	/**
	 * @param logBuffer the logBuffer to set
	 */
	public void setLogBuffer(StringBuffer logBuffer) {
		this.logBuffer = logBuffer;
	}
	
	/**
	 * @return the stoList
	 */
	public List getStoList() {
		return stoList;
	}

	/**
	 * @param stoList the stoList to set
	 */
	public void setStoList(List stoList) {
		this.stoList = stoList;
	}

}
