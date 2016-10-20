/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.service.scsicc.ws.beans;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="VentasRechazadasWebService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class VentasRechazadasWebService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoVenta;
	private String unidadesSolicitadas;
	private String indiceDocumento;
	private String numeroPreimpreso;
	/**
	 * @return the unidadesSolicitadas
	 */
	public String getUnidadesSolicitadas() {
		return unidadesSolicitadas;
	}
	/**
	 * @param unidadesSolicitadas the unidadesSolicitadas to set
	 */
	public void setUnidadesSolicitadas(String unidadesSolicitadas) {
		this.unidadesSolicitadas = unidadesSolicitadas;
	}

	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}
	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	
	/**
	 * @return the indiceDocumento
	 */
	public String getIndiceDocumento() {
		return indiceDocumento;
	}
	/**
	 * @param indiceDocumento the indiceDocumento to set
	 */
	public void setIndiceDocumento(String indiceDocumento) {
		this.indiceDocumento = indiceDocumento;
	}
	/**
	 * @return the numeroPreimpreso
	 */
	public String getNumeroPreimpreso() {
		return numeroPreimpreso;
	}
	/**
	 * @param numeroPreimpreso the numeroPreimpreso to set
	 */
	public void setNumeroPreimpreso(String numeroPreimpreso) {
		this.numeroPreimpreso = numeroPreimpreso;
	}
	
}
