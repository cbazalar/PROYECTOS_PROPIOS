package biz.belcorp.ssicc.dao.spusicc.comision.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextllizana
 *
 */
public class CalificacionComisionDetalle extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoNivel;
	private String descripcionNivel;
	private String codigoCalificacion;
	private Integer numeroPedidosDesde;
	private Integer numeroPedidosHasta;
	private Integer numeroIngresosDesde;
	private Integer numeroIngresosHasta;
	
	private String oidSubGerencia;
	private String codigoSubGerencia;
	private String descripcionSubGerencia;
	private String codigoRegion;
	private String codigoZona;
	private String descripcionRegion;
	
	private String codigoPorcentaje;
	
	private String codigoComision;
	private String descripcionComision;
	
	private String codigoMarca;
	private String codigoCanal;
	
	private String codigoTipoComisionista;
	private String campaniaDesde;
	private String campaniaHasta;
	
	private Integer numeroItem;
	private String montoDesde;
	private String montoHasta;
	private String valorPorc;
	
	private Integer numDiasRecuAspi;
	private String valPorcRecuAspi;
	private String valPorcComiAspi;
	private Integer numDiasRecuEjec;
	private String valPorcRecuEjec;
	private String valToleRecu;
	

	
	
	
	
	public Integer getNumeroItem() {
		return numeroItem;
	}

	public void setNumeroItem(Integer numeroItem) {
		this.numeroItem = numeroItem;
	}

	public String getMontoDesde() {
		return montoDesde;
	}

	public void setMontoDesde(String montoDesde) {
		this.montoDesde = montoDesde;
	}

	public String getMontoHasta() {
		return montoHasta;
	}

	public void setMontoHasta(String montoHasta) {
		this.montoHasta = montoHasta;
	}

	public String getValorPorc() {
		return valorPorc;
	}

	public void setValorPorc(String valorPorc) {
		this.valorPorc = valorPorc;
	}

	public CalificacionComisionDetalle() {
	
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}


	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CalificacionComisionDetalle [codigoPais=" + codigoPais
				+ ", codigoNivel=" + codigoNivel + ", descripcionNivel="
				+ descripcionNivel + ", codigoCalificacion="
				+ codigoCalificacion + ", numeroPedidosDesde="
				+ numeroPedidosDesde + ", numeroPedidosHasta="
				+ numeroPedidosHasta + ", numeroIngresosDesde="
				+ numeroIngresosDesde + ", numeroIngresosHasta="
				+ numeroIngresosHasta + ", oidSubGerencia=" + oidSubGerencia
				+ ", codigoSubGerencia=" + codigoSubGerencia
				+ ", descripcionSubGerencia=" + descripcionSubGerencia
				+ ", codigoRegion=" + codigoRegion + ", codigoZona="
				+ codigoZona + ", descripcionRegion=" + descripcionRegion
				+ ", codigoPorcentaje=" + codigoPorcentaje
				+ ", codigoComision=" + codigoComision
				+ ", descripcionComision=" + descripcionComision
				+ ", codigoMarca=" + codigoMarca + ", codigoCanal="
				+ codigoCanal + ", codigoTipoComisionista="
				+ codigoTipoComisionista + ", campaniaDesde=" + campaniaDesde
				+ ", campaniaHasta=" + campaniaHasta + ", numeroItem="
				+ numeroItem + ", montoDesde=" + montoDesde + ", montoHasta="
				+ montoHasta + ", valorPorc=" + valorPorc
				+ ", numDiasRecuAspi=" + numDiasRecuAspi + ", valPorcRecuAspi="
				+ valPorcRecuAspi + ", valPorcComiAspi=" + valPorcComiAspi
				+ ", numDiasRecuEjec=" + numDiasRecuEjec + ", valPorcRecuEjec="
				+ valPorcRecuEjec + ", valToleRecu=" + valToleRecu + "]";
	}

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
	 * @return the codigoNivel
	 */
	public String getCodigoNivel() {
		return codigoNivel;
	}

	/**
	 * @param codigoNivel the codigoNivel to set
	 */
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}
	
	/**
	 * @return the codigoTipoComisionista
	 */
	public String getCodigoTipoComisionista() {
		return codigoTipoComisionista;
	}

	/**
	 * @param codigoTipoComisionista the codigoTipoComisionista to set
	 */
	public void setCodigoTipoComisionista(String codigoTipoComisionista) {
		this.codigoTipoComisionista = codigoTipoComisionista;
	}

	/**
	 * @return the numeroPedidosDesde
	 */
	public Integer getNumeroPedidosDesde() {
		return numeroPedidosDesde;
	}

	/**
	 * @param numeroPedidosDesde the numeroPedidosDesde to set
	 */
	public void setNumeroPedidosDesde(Integer numeroPedidosDesde) {
		this.numeroPedidosDesde = numeroPedidosDesde;
	}

	/**
	 * @return the numeroPedidosHasta
	 */
	public Integer getNumeroPedidosHasta() {
		return numeroPedidosHasta;
	}

	/**
	 * @param numeroPedidosHasta the numeroPedidosHasta to set
	 */
	public void setNumeroPedidosHasta(Integer numeroPedidosHasta) {
		this.numeroPedidosHasta = numeroPedidosHasta;
	}

	/**
	 * @return the numeroIngresosDesde
	 */
	public Integer getNumeroIngresosDesde() {
		return numeroIngresosDesde;
	}

	/**
	 * @param numeroIngresosDesde the numeroIngresosDesde to set
	 */
	public void setNumeroIngresosDesde(Integer numeroIngresosDesde) {
		this.numeroIngresosDesde = numeroIngresosDesde;
	}

	/**
	 * @return the numeroIngersosHasta
	 */
	public Integer getNumeroIngresosHasta() {
		return numeroIngresosHasta;
	}

	/**
	 * @param numeroIngersosHasta the numeroIngersosHasta to set
	 */
	public void setNumeroIngresosHasta(Integer numeroIngersosHasta) {
		this.numeroIngresosHasta = numeroIngersosHasta;
	}

	/**
	 * @return the codigoCalificacion
	 */
	public String getCodigoCalificacion() {
		return codigoCalificacion;
	}

	/**
	 * @param codigoCalificacion the codigoCalificacion to set
	 */
	public void setCodigoCalificacion(String codigoCalificacion) {
		this.codigoCalificacion = codigoCalificacion;
	}

	/**
	 * @return the descripcionNivel
	 */
	public String getDescripcionNivel() {
		return descripcionNivel;
	}

	/**
	 * @param descripcionNivel the descripcionNivel to set
	 */
	public void setDescripcionNivel(String descripcionNivel) {
		this.descripcionNivel = descripcionNivel;
	}
	
	/**
	 * @return the codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca the codigoMarca to set
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return the codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal the codigoCanal to set
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		if(codigoRegion == null) 
			this.codigoRegion = "";
		else
			this.codigoRegion = codigoRegion;
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
		if(codigoZona == null)
			this.codigoZona = "";
		else
			this.codigoZona = codigoZona;
	}

	/**
	 * @return Returns the descripcionRegion.
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	/**
	 * @param descripcionRegion The descripcionRegion to set.
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	/**
	 * @return the codigoPorcentaje
	 */
	public String getCodigoPorcentaje() {
		return codigoPorcentaje;
	}

	/**
	 * @param codigoPorcentaje the codigoPorcentaje to set
	 */
	public void setCodigoPorcentaje(String codigoPorcentaje) {
		this.codigoPorcentaje = codigoPorcentaje;
	}
	
	/**
	 * @return the codigoComision
	 */
	public String getCodigoComision() {
		return codigoComision;
	}

	/**
	 * @param codigoComision the codigoComision to set
	 */
	public void setCodigoComision(String codigoComision) {
		this.codigoComision = codigoComision;
	}
	
	/**
	 * @return the descripcionComision
	 */
	public String getDescripcionComision() {
		return descripcionComision;
	}

	/**
	 * @param descripcionComision the descripcionComision to set
	 */
	public void setDescripcionComision(String descripcionComision) {
		this.descripcionComision = descripcionComision;
	}
	
	
	public Integer getNumDiasRecuAspi() {
		return numDiasRecuAspi;
	}

	public void setNumDiasRecuAspi(Integer numDiasRecuAspi) {
		this.numDiasRecuAspi = numDiasRecuAspi;
	}

	public String getValPorcRecuAspi() {
		return valPorcRecuAspi;
	}

	public void setValPorcRecuAspi(String valPorcRecuAspi) {
		this.valPorcRecuAspi = valPorcRecuAspi;
	}

	public String getValPorcComiAspi() {
		return valPorcComiAspi;
	}

	public void setValPorcComiAspi(String valPorcComiAspi) {
		this.valPorcComiAspi = valPorcComiAspi;
	}

	public Integer getNumDiasRecuEjec() {
		return numDiasRecuEjec;
	}

	public void setNumDiasRecuEjec(Integer numDiasRecuEjec) {
		this.numDiasRecuEjec = numDiasRecuEjec;
	}

	public String getValPorcRecuEjec() {
		return valPorcRecuEjec;
	}

	public void setValPorcRecuEjec(String valPorcRecuEjec) {
		this.valPorcRecuEjec = valPorcRecuEjec;
	}
	
	public String getValToleRecu() {
		return valToleRecu;
	}

	public void setValToleRecu(String valToleRecu) {
		this.valToleRecu = valToleRecu;
	}

	public String getCodigoSubGerencia() {
		return codigoSubGerencia;
	}

	public void setCodigoSubGerencia(String codigoSubGerencia) {
		this.codigoSubGerencia = codigoSubGerencia;
	}
	
	public String getOidSubGerencia() {
		return oidSubGerencia;
	}

	public void setOidSubGerencia(String oidSubGerencia) {
		this.oidSubGerencia = oidSubGerencia;
	}
	
	public String getDescripcionSubGerencia() {
		return descripcionSubGerencia;
	}

	public void setDescripcionSubGerencia(String descripcionSubGerencia) {
		this.descripcionSubGerencia = descripcionSubGerencia;
	}
	/**
	 * @return the campaniaDesde
	 */
	public String getCampaniaDesde() {
		return campaniaDesde;
	}

	/**
	 * @param campaniaDesde the campaniaDesde to set
	 */
	public void setCampaniaDesde(String campaniaDesde) {
		this.campaniaDesde = campaniaDesde;
	}

	/**
	 * @return the campaniaHasta
	 */
	public String getCampaniaHasta() {
		return campaniaHasta;
	}

	/**
	 * @param campaniaHasta the campaniaHasta to set
	 */
	public void setCampaniaHasta(String campaniaHasta) {
		this.campaniaHasta = campaniaHasta;
	}
	
}
