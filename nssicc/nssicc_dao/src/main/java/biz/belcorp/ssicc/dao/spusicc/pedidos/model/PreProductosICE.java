/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

/**
 * @author peextcroman
 *
 */
public class PreProductosICE implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8624669486054807956L;
	private String oidImpuesto;
	private String codigoSAP;
	private String oidProducto;
	private String valorImpuesto;
	private String fechaCarga;
	
	public String getOidImpuesto() {
		return oidImpuesto;
	}
	public void setOidImpuesto(String oidImpuesto) {
		this.oidImpuesto = oidImpuesto;
	}
	public String getCodigoSAP() {
		return codigoSAP;
	}
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}
	public String getOidProducto() {
		return oidProducto;
	}
	public void setOidProducto(String oidProducto) {
		this.oidProducto = oidProducto;
	}
	public String getValorImpuesto() {
		return valorImpuesto;
	}
	public void setValorImpuesto(String valorImpuesto) {
		this.valorImpuesto = valorImpuesto;
	}
	public String getFechaCarga() {
		return fechaCarga;
	}
	public void setFechaCarga(String fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	
	
	
}
