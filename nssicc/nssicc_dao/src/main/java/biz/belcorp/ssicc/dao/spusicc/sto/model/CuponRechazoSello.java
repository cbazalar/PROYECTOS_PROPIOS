package biz.belcorp.ssicc.dao.spusicc.sto.model;

import java.io.Serializable;



/* @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 */

public class CuponRechazoSello implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPais;
    private String codigoCliente;
    private String numDocumento;
    private String valor; 
    private String indicadorRechazo;
   
   
    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return Returns the indicadorRechazo.
	 */
	public String getIndicadorRechazo() {
		return indicadorRechazo;
	}

	/**
	 * @param indicadorRechazo The indicadorRechazo to set.
	 */
	public void setIndicadorRechazo(String indicadorRechazo) {
		this.indicadorRechazo = indicadorRechazo;
	}

	/**
	 * @return Returns the numDocumento.
	 */
	public String getNumDocumento() {
		return numDocumento;
	}

	/**
	 * @param numDocumento The numDocumento to set.
	 */
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	/**
	 * @return Returns the valor.
	 */
	public String getValor() {
		return valor;
	}

	/**
	 * @param valor The valor to set.
	 */
	public void setValor(String valor) {
		this.valor = valor;
	}

   
   
   

   
}
