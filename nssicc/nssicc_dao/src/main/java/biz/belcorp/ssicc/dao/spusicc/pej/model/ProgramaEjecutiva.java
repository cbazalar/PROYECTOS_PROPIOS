package biz.belcorp.ssicc.dao.spusicc.pej.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author jaltamirano
 *
 */
public class ProgramaEjecutiva extends AuditableBaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String descripcion;
	private String campanyaInicio;
	private String campanyaFinal;
	private String estado;
	
	private String campanyaEtapa;
	
	private List etapaList = new ArrayList();
	private List nivelList = new ArrayList();
	private List rangoList = new ArrayList();
	private List porcentajeList = new ArrayList();
	private List diferenciaPedidoList = new ArrayList();
	private List tipoPremioList = new ArrayList();
	private List cupList = new ArrayList();
	
	private List faseList = new ArrayList();
	private List grupoList = new ArrayList();
	private List tipoAbonoList = new ArrayList();
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the campanyaInicio
	 */
	public String getCampanyaInicio() {
		return campanyaInicio;
	}

	/**
	 * @param campanyaInicio the campanyaInicio to set
	 */
	public void setCampanyaInicio(String campanyaInicio) {
		this.campanyaInicio = campanyaInicio;
	}

	/**
	 * @return the campanyaFinal
	 */
	public String getCampanyaFinal() {
		return campanyaFinal;
	}

	/**
	 * @param campanyaFinal the campanyaFinal to set
	 */
	public void setCampanyaFinal(String campanyaFinal) {
		this.campanyaFinal = campanyaFinal;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the campanyaEtapa
	 */
	public String getCampanyaEtapa() {
		return campanyaEtapa;
	}

	/**
	 * @param campanyaEtapa the campanyaEtapa to set
	 */
	public void setCampanyaEtapa(String campanyaEtapa) {
		this.campanyaEtapa = campanyaEtapa;
	}
	
	/**
	 * @return the etapaList
	 */
	public List getEtapaList() {
		return etapaList;
	}

	/**
	 * @param etapaList the etapaList to set
	 */
	public void setEtapaList(List etapaList) {
		this.etapaList = etapaList;
	}

	/**
	 * @return the nivelList
	 */
	public List getNivelList() {
		return nivelList;
	}

	/**
	 * @param nivelList the nivelList to set
	 */
	public void setNivelList(List nivelList) {
		this.nivelList = nivelList;
	}

	/**
	 * @return the rangoList
	 */
	public List getRangoList() {
		return rangoList;
	}

	/**
	 * @param rangoList the rangoList to set
	 */
	public void setRangoList(List rangoList) {
		this.rangoList = rangoList;
	}

	/**
	 * @return the porcentajeList
	 */
	public List getPorcentajeList() {
		return porcentajeList;
	}

	/**
	 * @param porcentajeList the porcentajeList to set
	 */
	public void setPorcentajeList(List porcentajeList) {
		this.porcentajeList = porcentajeList;
	}

	/**
	 * @return the diferenciaPedidoList
	 */
	public List getDiferenciaPedidoList() {
		return diferenciaPedidoList;
	}

	/**
	 * @param diferenciaPedidoList the diferenciaPedidoList to set
	 */
	public void setDiferenciaPedidoList(List diferenciaPedidoList) {
		this.diferenciaPedidoList = diferenciaPedidoList;
	}

	/**
	 * @return the tipoPremioList
	 */
	public List getTipoPremioList() {
		return tipoPremioList;
	}

	/**
	 * @param tipoPremioList the tipoPremioList to set
	 */
	public void setTipoPremioList(List tipoPremioList) {
		this.tipoPremioList = tipoPremioList;
	}

	/**
	 * @return the cupList
	 */
	public List getCupList() {
		return cupList;
	}

	/**
	 * @param cupList the cupList to set
	 */
	public void setCupList(List cupList) {
		this.cupList = cupList;
	}

	/**
	 * @return the faseList
	 */
	public List getFaseList() {
		return faseList;
	}

	/**
	 * @param faseList the faseList to set
	 */
	public void setFaseList(List faseList) {
		this.faseList = faseList;
	}

	/**
	 * @return the grupoList
	 */
	public List getGrupoList() {
		return grupoList;
	}

	/**
	 * @param grupoList the grupoList to set
	 */
	public void setGrupoList(List grupoList) {
		this.grupoList = grupoList;
	}

	/**
	 * @return the tipoAbonoList
	 */
	public List getTipoAbonoList() {
		return tipoAbonoList;
	}

	/**
	 * @param tipoAbonoList the tipoAbonoList to set
	 */
	public void setTipoAbonoList(List tipoAbonoList) {
		this.tipoAbonoList = tipoAbonoList;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProgramaEjecutiva [campanyaEtapa=" + campanyaEtapa
				+ ", campanyaFinal=" + campanyaFinal + ", campanyaInicio="
				+ campanyaInicio + ", codigoPais=" + codigoPais
				+ ", codigoPrograma=" + codigoPrograma + ", cupList=" + cupList
				+ ", descripcion=" + descripcion + ", diferenciaPedidoList="
				+ diferenciaPedidoList + ", estado=" + estado + ", etapaList="
				+ etapaList + ", faseList=" + faseList + ", grupoList="
				+ grupoList + ", nivelList=" + nivelList + ", porcentajeList="
				+ porcentajeList + ", rangoList=" + rangoList
				+ ", tipoAbonoList=" + tipoAbonoList + ", tipoPremioList="
				+ tipoPremioList + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((campanyaEtapa == null) ? 0 : campanyaEtapa.hashCode());
		result = prime * result
				+ ((campanyaFinal == null) ? 0 : campanyaFinal.hashCode());
		result = prime * result
				+ ((campanyaInicio == null) ? 0 : campanyaInicio.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result + ((cupList == null) ? 0 : cupList.hashCode());
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime
				* result
				+ ((diferenciaPedidoList == null) ? 0 : diferenciaPedidoList
						.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((etapaList == null) ? 0 : etapaList.hashCode());
		result = prime * result
				+ ((faseList == null) ? 0 : faseList.hashCode());
		result = prime * result
				+ ((grupoList == null) ? 0 : grupoList.hashCode());
		result = prime * result
				+ ((nivelList == null) ? 0 : nivelList.hashCode());
		result = prime * result
				+ ((porcentajeList == null) ? 0 : porcentajeList.hashCode());
		result = prime * result
				+ ((rangoList == null) ? 0 : rangoList.hashCode());
		result = prime * result
				+ ((tipoAbonoList == null) ? 0 : tipoAbonoList.hashCode());
		result = prime * result
				+ ((tipoPremioList == null) ? 0 : tipoPremioList.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj==null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgramaEjecutiva other = (ProgramaEjecutiva) obj;
		if (campanyaEtapa == null) {
			if (other.campanyaEtapa != null)
				return false;
		} else if (!campanyaEtapa.equals(other.campanyaEtapa))
			return false;
		if (campanyaFinal == null) {
			if (other.campanyaFinal != null)
				return false;
		} else if (!campanyaFinal.equals(other.campanyaFinal))
			return false;
		if (campanyaInicio == null) {
			if (other.campanyaInicio != null)
				return false;
		} else if (!campanyaInicio.equals(other.campanyaInicio))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (codigoPrograma == null) {
			if (other.codigoPrograma != null)
				return false;
		} else if (!codigoPrograma.equals(other.codigoPrograma))
			return false;
		if (cupList == null) {
			if (other.cupList != null)
				return false;
		} else if (!cupList.equals(other.cupList))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (diferenciaPedidoList == null) {
			if (other.diferenciaPedidoList != null)
				return false;
		} else if (!diferenciaPedidoList.equals(other.diferenciaPedidoList))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (etapaList == null) {
			if (other.etapaList != null)
				return false;
		} else if (!etapaList.equals(other.etapaList))
			return false;
		if (faseList == null) {
			if (other.faseList != null)
				return false;
		} else if (!faseList.equals(other.faseList))
			return false;
		if (grupoList == null) {
			if (other.grupoList != null)
				return false;
		} else if (!grupoList.equals(other.grupoList))
			return false;
		if (nivelList == null) {
			if (other.nivelList != null)
				return false;
		} else if (!nivelList.equals(other.nivelList))
			return false;
		if (porcentajeList == null) {
			if (other.porcentajeList != null)
				return false;
		} else if (!porcentajeList.equals(other.porcentajeList))
			return false;
		if (rangoList == null) {
			if (other.rangoList != null)
				return false;
		} else if (!rangoList.equals(other.rangoList))
			return false;
		if (tipoAbonoList == null) {
			if (other.tipoAbonoList != null)
				return false;
		} else if (!tipoAbonoList.equals(other.tipoAbonoList))
			return false;
		if (tipoPremioList == null) {
			if (other.tipoPremioList != null)
				return false;
		} else if (!tipoPremioList.equals(other.tipoPremioList))
			return false;
		return true;
	}

}