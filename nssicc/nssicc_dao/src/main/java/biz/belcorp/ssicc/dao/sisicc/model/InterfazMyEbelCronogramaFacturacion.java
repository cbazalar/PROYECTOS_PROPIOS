/**
 * Created on 17-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazMyEbelCronogramaFacturacion.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class InterfazMyEbelCronogramaFacturacion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7763833418849663736L;
	private String codigoPais;
	private String codigoZona;
	private String codigoCampanya;
	private Timestamp fechaFacturacion;
	private Timestamp fechaRefacturacion;
	
	/**
	 * @return Returns the codigoCampanya.
	 */
	public String getCodigoCampanya() {
		return codigoCampanya;
	}
	/**
	 * @param codigoCampanya The codigoCampanya to set.
	 */
	public void setCodigoCampanya(String codigoCampanya) {
		this.codigoCampanya = codigoCampanya;
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
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return Returns the fechaFacturacion.
	 */
	public Timestamp getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion The fechaFacturacion to set.
	 */
	public void setFechaFacturacion(Timestamp fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @return Returns the fechaRefacturacion.
	 */
	public Timestamp getFechaRefacturacion() {
		return fechaRefacturacion;
	}
	/**
	 * @param fechaRefacturacion The fechaRefacturacion to set.
	 */
	public void setFechaRefacturacion(Timestamp fechaRefacturacion) {
		this.fechaRefacturacion = fechaRefacturacion;
	}
}
