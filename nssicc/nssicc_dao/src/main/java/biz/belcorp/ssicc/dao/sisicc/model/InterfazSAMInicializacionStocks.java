/*
 * Created on 10-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazSAMInicializacionStocks.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazSAMInicializacionStocks implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2923254382285285066L;
	private String codigoPais;
	private String codigoAlmacen;
	private String codigoProducto;
	private Long variacionInventario;

	
	/**
	 * @return Returns the codigoAlmacen.
	 */
	public String getCodigoAlmacen() {
		return codigoAlmacen;
	}
	/**
	 * @param codigoAlmacen The codigoAlmacen to set.
	 */
	public void setCodigoAlmacen(String codigoAlmacen) {
		this.codigoAlmacen = codigoAlmacen;
	}
	/**
	 * @return Returns the codigoPais.
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
	 * @return Returns the codigoProducto.
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto The codigoProducto to set.
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	/**
	 * @return Returns the variacionInventario.
	 */
	public Long getVariacionInventario() {
		return variacionInventario;
	}
	/**
	 * @param variacionInventario The variacionInventario to set.
	 */
	public void setVariacionInventario(Long variacionInventario) {
		this.variacionInventario = variacionInventario;
	}
}
