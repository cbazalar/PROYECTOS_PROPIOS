/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import biz.belcorp.ssicc.dao.framework.model.BaseObject;


/**
 * TODO Representa a la llave primaria de la clase Interfaz.
 * 
 * <p>
 * <a href="InterfazPK.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 */

public class InterfazPK extends BaseObject implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 5419518198297313686L;

	/**
     * Codigo del pais
     */
	private String codigoPais;

    /**
     * Codigo del Sistema
     */
	private String codigoSistema;

    /**
     * Codigo de la interfaz
     */
	private String codigo;

    /**
     * Codigo de la interfaz paquete
     * @deprecated Ya no forma parte de la llave primaria
     */
	private String codigoInterfazEmpaquetada;
	
	
	private String[] listaInterfaces;
	private String[] listaInterfacesSeleccionadas;

	/**
	 * Constructor por defecto.
	 */
	public InterfazPK() {
		super();
	}

	/**
	 * Constructor para INTERFACES UNITARIAS
	 * 
	 * @param codigoPais
	 * @param codigoSistema
	 * @param codigo
	 */
	public InterfazPK(String codigoPais, String codigoSistema, String codigo) {
		super();
		this.codigoPais = codigoPais;
		this.codigoSistema = codigoSistema;
		this.codigo = codigo;
	}

	/**
	 * Constructor para INTERFACES TIPO PAQUETE
	 * 
	 * @param codigoPais
	 * @param codigoSistema
	 * @param codigo
	 * @param codigoInterfazEmpaquetada
     * 
     * @deprecated El codigo de paquete ya no forma parte de la llave primaria
	 */
	public InterfazPK(String codigoPais, String codigoSistema, String codigo,
			String codigoInterfazEmpaquetada) {
		super();
		this.codigoPais = codigoPais;
		this.codigoSistema = codigoSistema;
		this.codigo = codigo;
		this.codigoInterfazEmpaquetada = codigoInterfazEmpaquetada;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof InterfazPK)) {
			return false;
		}
		InterfazPK rhs = (InterfazPK) object;
		return new EqualsBuilder().append(this.codigoPais, rhs.codigoPais)
				.append(this.codigoSistema, rhs.codigoSistema).append(
						this.codigo, rhs.codigo).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-546492501, -1360957971).append(
				this.codigoPais).append(this.codigoSistema).append(this.codigo)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("codigoPais", this.codigoPais).append("codigo",
						this.codigo)
				.append("codigoSistema", this.codigoSistema).toString();
	}

	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return Returns the codigoInterfazEmpaquetada.
	 */
	public String getCodigoInterfazEmpaquetada() {
		return codigoInterfazEmpaquetada;
	}

	/**
	 * @param codigoInterfazEmpaquetada
	 *            The codigoInterfazEmpaquetada to set.
	 */
	public void setCodigoInterfazEmpaquetada(String codigoInterfazEmpaquetada) {
		this.codigoInterfazEmpaquetada = codigoInterfazEmpaquetada;
	}

	/**
	 * @param codigo
	 *            The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoSistema.
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema
	 *            The codigoSistema to set.
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * @return the listaInterfaces
	 */
	public String[] getListaInterfaces() {
		return listaInterfaces;
	}

	/**
	 * @param listaInterfaces the listaInterfaces to set
	 */
	public void setListaInterfaces(String[] listaInterfaces) {
		this.listaInterfaces = listaInterfaces;
	}

	/**
	 * @return the listaInterfacesSeleccionadas
	 */
	public String[] getListaInterfacesSeleccionadas() {
		return listaInterfacesSeleccionadas;
	}

	/**
	 * @param listaInterfacesSeleccionadas the listaInterfacesSeleccionadas to set
	 */
	public void setListaInterfacesSeleccionadas(
			String[] listaInterfacesSeleccionadas) {
		this.listaInterfacesSeleccionadas = listaInterfacesSeleccionadas;
	}
	
	
}