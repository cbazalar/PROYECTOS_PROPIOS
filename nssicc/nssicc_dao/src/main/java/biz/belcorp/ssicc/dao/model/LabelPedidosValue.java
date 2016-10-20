package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * A simple JavaBean to represent label-value pairs. This is most commonly used
 * when constructing user interface elements which have a label to be displayed
 * to the user, and a corresponding value to be returned to the server. One
 * example is the <code>&lt;html:options&gt;</code> tag. <p/><p/>Note: this
 * class has a natural ordering that is inconsistent with equals.
 * </p>
 * 
 * @see org.apache.struts.util.LabelValueBean
 */
public class LabelPedidosValue extends AuditableBaseObject implements Serializable {
	
	private String codigoVta = null;
    private String descripcion = null;
    private String precioCat = null;
    private String unidades = null;
    private String total = null;
    private String codPais = null;
    private String codPeriodo = null;
    private String codCliente = null;
    private String fechaSolicitud = null;
    private String numPosicion = null;
    
    private String numLote = null;
    
    private String indicadorOCS;
    
    //***
    private String nuevoUnidades;
    private String nuevoCodigoVta;
    private String codigoUsuario;
    //***
    
    private String nombreCliente;
    private String codigoZona;
    private String posicion;
        
    private String precioCatalogo;
    
	/**
	 * @return the posicion
	 */
	public String getPosicion() {
		return posicion;
	}
	/**
	 * @param posicion the posicion to set
	 */
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public LabelPedidosValue() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LabelPedidosValue(String codigoVta, String descripcion, String precioCat, String unidades, String total) {
		super();
		// TODO Auto-generated constructor stub
		this.codigoVta = codigoVta;
		this.descripcion = descripcion;
		this.precioCat = precioCat;
		this.unidades = unidades;
		this.total = total;
	}
	public LabelPedidosValue(String codigoVta, String descripcion, String precioCat, String unidades, String total, String indicadorOCS) {
		super();
		// TODO Auto-generated constructor stub
		this.codigoVta = codigoVta;
		this.descripcion = descripcion;
		this.precioCat = precioCat;
		this.unidades = unidades;
		this.total = total;
		this.indicadorOCS = indicadorOCS;
	}
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
	/**
	 * @return Returns the codCliente.
	 */
	public String getCodCliente() {
		return codCliente;
	}
	/**
	 * @param codCliente The codCliente to set.
	 */
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
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
	 * @return Returns the fechaSolicitud.
	 */
	public String getFechaSolicitud() {
		return fechaSolicitud;
	}
	/**
	 * @param fechaSolicitud The fechaSolicitud to set.
	 */
	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	/**
	 * @return Returns the numPosicion.
	 */
	public String getNumPosicion() {
		return numPosicion;
	}
	/**
	 * @param numPosicion The numPosicion to set.
	 */
	public void setNumPosicion(String numPosicion) {
		this.numPosicion = numPosicion;
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof LabelPedidosValue)) {
			return false;
		}
		LabelPedidosValue rhs = (LabelPedidosValue) object;
		return new EqualsBuilder().append(
				this.total, rhs.total).append(this.codigoVta, rhs.codigoVta)
				.append(this.codPais, rhs.codPais).append(this.descripcion,
						rhs.descripcion).append(this.numPosicion,
						rhs.numPosicion)
				.append(this.codCliente, rhs.codCliente).append(this.unidades,
						rhs.unidades).append(this.precioCat, rhs.precioCat)
				.append(this.auditInfo, rhs.auditInfo).append(
						this.fechaSolicitud, rhs.fechaSolicitud).append(
						this.codPeriodo, rhs.codPeriodo).isEquals();
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1023245827, -1771860689).append(this.total).append(this.codigoVta)
				.append(this.codPais).append(this.descripcion).append(
						this.numPosicion).append(this.codCliente).append(
						this.unidades).append(this.precioCat).append(
						this.auditInfo).append(this.fechaSolicitud).append(
						this.codPeriodo).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("numPosicion", this.numPosicion).append("codPeriodo",
						this.codPeriodo).append("codCliente", this.codCliente)
				.append("unidades", this.unidades).append("auditInfo",
						this.auditInfo).append("codigoVta", this.codigoVta)
				.append("fechaSolicitud", this.fechaSolicitud).append(
						"codPais", this.codPais).append("total", this.total)
				.append("precioCat", this.precioCat).append("descripcion",
						this.descripcion).toString();
	}
	/**
	 * @return Returns the numLote.
	 */
	public String getNumLote() {
		return numLote;
	}
	/**
	 * @param numLote The numLote to set.
	 */
	public void setNumLote(String numLote) {
		this.numLote = numLote;
	}
	/**
	 * @return Returns the indicador.
	 */
	/**
	 * @return Returns the indicadorOCS.
	 */
	public String getIndicadorOCS() {
		return indicadorOCS;
	}
	/**
	 * @param ind_ocs The indicadorOCS to set.
	 */
	public void setIndicadorOCS(String indicadorOCS) {
		this.indicadorOCS = indicadorOCS;
	}
	public String getNuevoCodigoVta() {
		return nuevoCodigoVta;
	}
	public void setNuevoCodigoVta(String nuevoCodigoVta) {
		this.nuevoCodigoVta = nuevoCodigoVta;
	}
	public String getNuevoUnidades() {
		return nuevoUnidades;
	}
	public void setNuevoUnidades(String nuevoUnidades) {
		this.nuevoUnidades = nuevoUnidades;
	}
	public String getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	/**
	 * @return the nombreCliente
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}
	/**
	 * @param nombreCliente the nombreCliente to set
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the precioCatalogo
	 */
	public String getPrecioCatalogo() {
		return precioCatalogo;
	}
	/**
	 * @param precioCatalogo the precioCatalogo to set
	 */
	public void setPrecioCatalogo(String precioCatalogo) {
		this.precioCatalogo = precioCatalogo;
	}
	
}