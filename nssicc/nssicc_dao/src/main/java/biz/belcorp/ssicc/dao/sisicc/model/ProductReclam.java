/*
 * Created on 19-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Representa al negocio de Productos Reclamados
 * 
 * <p>
 * <a href="Interfaz.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva Moreno</a>
 * 
 */
public class ProductReclam extends AuditableBaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2646932537112030650L;
	private String       codigoLinea	  			;
	private String       tipoIngreso	  ;      
	private String       subacceso		  ;      
	private String       region;               
	private String       fechareclamo	;        
	private String       codoperacion ;       
	private String       documentoref	;        
	private String       cliente			  ;      
	private String       codigoSap		  ;      
	private String       codigoVenta	  ;      
	private String       descProducto	;        
	private String       campanhaRef	  ;      
	private String       unidadesRec	  ;      
	private String       unidadesDev 	;        
	private String       unidadesADevo ;       
	
	public String getCampanhaRef() {
		return campanhaRef;
	}
	public void setCampanhaRef(String campanhaRef) {
		this.campanhaRef = campanhaRef;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getCodigoLinea() {
		return codigoLinea;
	}
	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}
	public String getCodigoSap() {
		return codigoSap;
	}
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
	public String getCodigoVenta() {
		return codigoVenta;
	}
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public String getDescProducto() {
		return descProducto;
	}
	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}
	public String getDocumentoref() {
		return documentoref;
	}
	public void setDocumentoref(String documentoref) {
		this.documentoref = documentoref;
	}
	public String getFechareclamo() {
		return fechareclamo;
	}
	public void setFechareclamo(String fechareclamo) {
		this.fechareclamo = fechareclamo;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getSubacceso() {
		return subacceso;
	}
	public void setSubacceso(String subacceso) {
		this.subacceso = subacceso;
	}
	public String getTipoIngreso() {
		return tipoIngreso;
	}
	public void setTipoIngreso(String tipoIngreso) {
		this.tipoIngreso = tipoIngreso;
	}
	public String getUnidadesADevo() {
		return unidadesADevo;
	}
	public void setUnidadesADevo(String unidadesADevo) {
		this.unidadesADevo = unidadesADevo;
	}
	public String getUnidadesDev() {
		return unidadesDev;
	}
	public void setUnidadesDev(String unidadesDev) {
		this.unidadesDev = unidadesDev;
	}
	public String getUnidadesRec() {
		return unidadesRec;
	}
	public void setUnidadesRec(String unidadesRec) {
		this.unidadesRec = unidadesRec;
	}
	/* 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	/* 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/*
	 *  (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("codigoLinea", this.codigoLinea).append(
						"unidadesADevo", this.unidadesADevo).append(
						"descProducto", this.descProducto).append("subacceso",
						this.subacceso).append("auditInfo", this.auditInfo)
				.append("cliente", this.cliente).append("tipoIngreso",
						this.tipoIngreso).append("codoperarcion",
						this.codoperacion).append("codigoVenta",
						this.codigoVenta).append("campanhaRef",
						this.campanhaRef).append("region", this.region).append(
						"unidadesDev", this.unidadesDev).append("unidadesRec",
						this.unidadesRec).append("documentoref",
						this.documentoref).append("codigoSap", this.codigoSap)
				.append("fechareclamo", this.fechareclamo).toString();
	}
	public String getCodoperacion() {
		return codoperacion;
	}
	public void setCodoperacion(String codoperacion) {
		this.codoperacion = codoperacion;
	}
	
}
