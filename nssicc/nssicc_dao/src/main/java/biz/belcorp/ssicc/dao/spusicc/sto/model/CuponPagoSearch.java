package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;


/* @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 */

public class CuponPagoSearch implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
    private String codigoCliente;
    private String nombreCliente;
    private String valorPagado; 
    private String fechaRegistro;
    private String indRechazoSello;
    private String numDocumento;
    private String numeroDocumentoIdentidad;
    
	public String getCodigoPais() {
		return codigoPais;
	}
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	public String getValorPagado() {
		return valorPagado;
	}
	public void setValorPagado(String valorPagado) {
		this.valorPagado = valorPagado;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getIndRechazoSello() {
		return indRechazoSello;
	}
	public void setIndRechazoSello(String indRechazoSello) {
		this.indRechazoSello = indRechazoSello;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	/**
	 * @return the numeroDocumentoIdentidad
	 */
	public String getNumeroDocumentoIdentidad() {
		return numeroDocumentoIdentidad;
	}
	/**
	 * @param numeroDocumentoIdentidad the numeroDocumentoIdentidad to set
	 */
	public void setNumeroDocumentoIdentidad(String numeroDocumentoIdentidad) {
		this.numeroDocumentoIdentidad = numeroDocumentoIdentidad;
	}
	
}
