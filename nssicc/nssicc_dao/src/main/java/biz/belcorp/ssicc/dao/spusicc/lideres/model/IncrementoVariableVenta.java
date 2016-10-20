package biz.belcorp.ssicc.dao.spusicc.lideres.model;

import java.io.Serializable;

public class IncrementoVariableVenta implements Serializable {
	
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codPais; 
    private String codMarca;         
    private String codPeriodo;         
   	private String codVariableVenta;         
   	private String tipoIncremento;
   	private String valorIncremento;
	/**
	 * @return Returns the codMarca.
	 */
	public String getCodMarca() {
		return codMarca;
	}
	/**
	 * @param codMarca The codMarca to set.
	 */
	public void setCodMarca(String codMarca) {
		this.codMarca = codMarca;
	}
	/**
	 * @return Returns the codPais.
	 */
	public String getCodPais() {
		return codPais;
	}
	/**
	 * @param codPais The codPais to set.
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	/**
	 * @return Returns the codPeriodo.
	 */
	public String getCodPeriodo() {
		return codPeriodo;
	}
	/**
	 * @param codPeriodo The codPeriodo to set.
	 */
	public void setCodPeriodo(String codPeriodo) {
		this.codPeriodo = codPeriodo;
	}
	/**
	 * @return Returns the codVariableVenta.
	 */
	public String getCodVariableVenta() {
		return codVariableVenta;
	}
	/**
	 * @param codVariableVenta The codVariableVenta to set.
	 */
	public void setCodVariableVenta(String codVariableVenta) {
		this.codVariableVenta = codVariableVenta;
	}
	/**
	 * @return Returns the tipoIncremento.
	 */
	public String getTipoIncremento() {
		return tipoIncremento;
	}
	/**
	 * @param tipoIncremento The tipoIncremento to set.
	 */
	public void setTipoIncremento(String tipoIncremento) {
		this.tipoIncremento = tipoIncremento;
	}
	/**
	 * @return Returns the valorIncremento.
	 */
	public String getValorIncremento() {
		return valorIncremento;
	}
	/**
	 * @param valorIncremento The valorIncremento to set.
	 */
	public void setValorIncremento(String valorIncremento) {
		this.valorIncremento = valorIncremento;
	}
	
	
	
}
