/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.cuentacorriente.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author pejflorencio
 *
 */
public class PagoChequeDetalle  extends AuditableBaseObject implements
Serializable {

/**
* 
*/
	private static final long serialVersionUID = 1L;

	private String tipoDocumento;
	private String documentoIdentidad;
	private String codigoConsultora;
	private String nombreConsultora;
	private String tipoCheque;
	private String fechaEmision;
	private String fechaCobro;
	private String banco;
	private String sucursal;
	private String importePago;
	private int numeroLinea;
	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the documentoIdentidad
	 */
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}
	/**
	 * @param documentoIdentidad the documentoIdentidad to set
	 */
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}
	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}
	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}
	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}
	/**
	 * @param nombreConsultora the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}
	/**
	 * @return the tipoCheque
	 */
	public String getTipoCheque() {
		return tipoCheque;
	}
	/**
	 * @param tipoCheque the tipoCheque to set
	 */
	public void setTipoCheque(String tipoCheque) {
		this.tipoCheque = tipoCheque;
	}
	
	/**
	 * @return the fechaEmision
	 */
	public String getFechaEmision() {
		return fechaEmision;
	}
	/**
	 * @param fechaCobro the fechaEmision to set
	 */
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
	/**
	 * @return the fechaCobro
	 */
	public String getFechaCobro() {
		return fechaCobro;
	}
	/**
	 * @param fechaCobro the fechaCobro to set
	 */
	public void setFechaCobro(String fechaCobro) {
		this.fechaCobro = fechaCobro;
	}
	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}
	/**
	 * @param banco the banco to set
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}
	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}
	/**
	 * @param sucursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	/**
	 * @return the importePago
	 */
	public String getImportePago() {
		return importePago;
	}
	/**
	 * @param importePago the importePago to set
	 */
	public void setImportePago(String importePago) {
		this.importePago = importePago;
	}
	/**
	 * @return the numeroLinea
	 */
	public int getNumeroLinea() {
		return numeroLinea;
	}
	/**
	 * @param numeroLinea the numeroLinea to set
	 */
	public void setNumeroLinea(int numeroLinea) {
		this.numeroLinea = numeroLinea;
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PagoChequeDetalle [tipoDocumento=" + tipoDocumento
				+ ", documentoIdentidad=" + documentoIdentidad
				+ ", codigoConsultora=" + codigoConsultora
				+ ", nombreConsultora=" + nombreConsultora + ", tipoCheque="
				+ tipoCheque + ", fechaEmision=" + fechaEmision
				+ ", fechaCobro=" + fechaCobro + ", banco=" + banco
				+ ", sucursal=" + sucursal + ", importePago=" + importePago
				+ ", numeroLinea=" + numeroLinea + "]";
	}	
}
