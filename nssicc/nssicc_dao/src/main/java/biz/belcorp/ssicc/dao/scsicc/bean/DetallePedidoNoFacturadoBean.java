/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.dao.scsicc.bean;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="DetallePedidoNoFacturadoBean.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class DetallePedidoNoFacturadoBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8416530485104638466L;
	private String codigoVenta;
	private String descripcionProducto;
	private String unidadesDemandada;
	private String limiteVenta;
	private String precioCatalogoUnitario;
	private String precioCatalogoTotal;
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
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	/**
	 * @param descripcionProducto the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	/**
	 * @return the unidadesDemandada
	 */
	public String getUnidadesDemandada() {
		return unidadesDemandada;
	}
	/**
	 * @param unidadesDemandada the unidadesDemandada to set
	 */
	public void setUnidadesDemandada(String unidadesDemandada) {
		this.unidadesDemandada = unidadesDemandada;
	}
	/**
	 * @return the limiteVenta
	 */
	public String getLimiteVenta() {
		return limiteVenta;
	}
	/**
	 * @param limiteVenta the limiteVenta to set
	 */
	public void setLimiteVenta(String limiteVenta) {
		this.limiteVenta = limiteVenta;
	}
	/**
	 * @return the precioCatalogoUnitario
	 */
	public String getPrecioCatalogoUnitario() {
		return precioCatalogoUnitario;
	}
	/**
	 * @param precioCatalogoUnitario the precioCatalogoUnitario to set
	 */
	public void setPrecioCatalogoUnitario(String precioCatalogoUnitario) {
		this.precioCatalogoUnitario = precioCatalogoUnitario;
	}
	/**
	 * @return the precioCatalogoTotal
	 */
	public String getPrecioCatalogoTotal() {
		return precioCatalogoTotal;
	}
	/**
	 * @param precioCatalogoTotal the precioCatalogoTotal to set
	 */
	public void setPrecioCatalogoTotal(String precioCatalogoTotal) {
		this.precioCatalogoTotal = precioCatalogoTotal;
	}
		
}
