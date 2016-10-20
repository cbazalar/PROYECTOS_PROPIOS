package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;



public class MantenimientoSTOCuponForm extends BaseEditForm  implements Serializable {
			 
	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoCliente;
	private String fechaRegistro;
	private Date fechaRegistroD;
	private String valorPagado;
	private String indRechazoSello;
	private String numDocumento;
	private String codRegion;
	private String codZona;
	private String numLote;
	private String nunSecuencia;
	private String indMotivoRechazo;
	private String oidPais;
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return the fechaRegistro
	 */
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	/**
	 * @return the valorPagado
	 */
	public String getValorPagado() {
		return valorPagado;
	}
	/**
	 * @param valorPagado the valorPagado to set
	 */
	public void setValorPagado(String valorPagado) {
		this.valorPagado = valorPagado;
	}
	/**
	 * @return the indRechazoSello
	 */
	public String getIndRechazoSello() {
		return indRechazoSello;
	}
	/**
	 * @param indRechazoSello the indRechazoSello to set
	 */
	public void setIndRechazoSello(String indRechazoSello) {
		this.indRechazoSello = indRechazoSello;
	}
	/**
	 * @return the numDocumento
	 */
	public String getNumDocumento() {
		return numDocumento;
	}
	/**
	 * @param numDocumento the numDocumento to set
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	/**
	 * @return the codRegion
	 */
	public String getCodRegion() {
		return codRegion;
	}
	/**
	 * @param codRegion the codRegion to set
	 */
	public void setCodRegion(String codRegion) {
		this.codRegion = codRegion;
	}
	/**
	 * @return the codZona
	 */
	public String getCodZona() {
		return codZona;
	}
	/**
	 * @param codZona the codZona to set
	 */
	public void setCodZona(String codZona) {
		this.codZona = codZona;
	}
	/**
	 * @return the numLote
	 */
	public String getNumLote() {
		return numLote;
	}
	/**
	 * @param numLote the numLote to set
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	/**
	 * @return the nunSecuencia
	 */
	public String getNunSecuencia() {
		return nunSecuencia;
	}
	/**
	 * @param nunSecuencia the nunSecuencia to set
	 */
	public void setNunSecuencia(String nunSecuencia) {
		this.nunSecuencia = nunSecuencia;
	}
	/**
	 * @return the indMotivoRechazo
	 */
	public String getIndMotivoRechazo() {
		return indMotivoRechazo;
	}
	/**
	 * @param indMotivoRechazo the indMotivoRechazo to set
	 */
	public void setIndMotivoRechazo(String indMotivoRechazo) {
		this.indMotivoRechazo = indMotivoRechazo;
	}
	/**
	 * @return the oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}
	/**
	 * @param oidPais the oidPais to set
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}
	/**
	 * @return the fechaRegistroD
	 */
	public Date getFechaRegistroD() {
		return fechaRegistroD;
	}
	/**
	 * @param fechaRegistroD the fechaRegistroD to set
	 */
	public void setFechaRegistroD(Date fechaRegistroD) {
		this.fechaRegistroD = fechaRegistroD;
	}
}