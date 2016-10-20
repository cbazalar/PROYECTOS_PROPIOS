package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

public class Pedido implements Serializable{
	private String codigoVta;
    private String descripcion;
    private String precioCat;
    private String unidades;
    private String total;
	/**
	 * @return Returns the codigoVta.
	 */
	public String getCodigoVta() {
		return codigoVta;
	}
	/**
	 * @param codigoVta The codigoVta to set.
	 */
	public void setCodigoVta(String codigoVta) {
		this.codigoVta = codigoVta;
	}
	/**
	 * @return Returns the descripcion.
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion The descripcion to set.
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return Returns the precioCat.
	 */
	public String getPrecioCat() {
		return precioCat;
	}
	/**
	 * @param precioCat The precioCat to set.
	 */
	public void setPrecioCat(String precioCat) {
		this.precioCat = precioCat;
	}
	/**
	 * @return Returns the total.
	 */
	public String getTotal() {
		return total;
	}
	/**
	 * @param total The total to set.
	 */
	public void setTotal(String total) {
		this.total = total;
	}
	/**
	 * @return Returns the unidades.
	 */
	public String getUnidades() {
		return unidades;
	}
	/**
	 * @param unidades The unidades to set.
	 */
	public void setUnidades(String unidades) {
		this.unidades = unidades;
	}
    
}
