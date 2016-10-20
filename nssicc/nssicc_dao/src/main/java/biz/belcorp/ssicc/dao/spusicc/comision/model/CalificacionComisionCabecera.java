package biz.belcorp.ssicc.dao.spusicc.comision.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextllizana
 *
 */
public class CalificacionComisionCabecera extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoNivel;
	private String codigoCalificacion;
	private String descripcion;
	private String descripcionNivel;
	private String codigoTipoComisionista;
	private String codigoMarca;
	private String codigoCanal;
	
	private String oidSubGerencia;
	private String codigoSubGerencia;
	private String descripcionSubGerencia;
	private String codigoRegion;
	private String codigoZona;
	private String descripcionRegion;
	
	
	

	private String desTipoComisionista;
	private String campaniaDesde;
	private String campaniaHasta;
	private Integer comodin;
	
	private String codigoPorcentaje;
	private String codigoComision;
	private String descripcionComision;
	
	private Integer numDiasRecuAspi;
	private String valPorcRecuAspi;
	private String valPorcComiAspi;
	private Integer numDiasRecuEjec;
	private String valPorcRecuEjec;
	private String valToleRecu;
	
	private String montoDesde;
	private String montoHasta;
	private String valorPorc;
	private Integer numeroItem;
	
	private Integer numeroPedidosDesde;
	private Integer numeroPedidosHasta;
	private Integer numeroIngresosDesde;
	private Integer numeroIngresosHasta;
	
	private Integer comodinRecuperacion;
	
	private Integer indicadorPasePedido;
	
	/* INI JJ PER-SiCC-2012-0530*/
	private Integer comodinRecuperacionAspirantes;		
	
	/**
	 * @return
	 */
	public Integer getComodinRecuperacionAspirantes() {
		return comodinRecuperacionAspirantes;
	}

	/**
	 * @param comodinRecuperacionAspirantes
	 */
	public void setComodinRecuperacionAspirantes(Integer comodinRecuperacionAspirantes) {
		this.comodinRecuperacionAspirantes = comodinRecuperacionAspirantes;
	}
	/* FIN JJ PER-SiCC-2012-0530*/
	
	public CalificacionComisionCabecera() {
	
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
		return "CalificacionComisionCabecera [codigoPais=" + codigoPais
				+ ", codigoNivel=" + codigoNivel + ", codigoCalificacion="
				+ codigoCalificacion + ", descripcion=" + descripcion
				+ ", descripcionNivel=" + descripcionNivel
				+ ", codigoTipoComisionista=" + codigoTipoComisionista
				+ ", codigoMarca=" + codigoMarca + ", codigoCanal="
				+ codigoCanal + ", oidSubGerencia=" + oidSubGerencia
				+ ", codigoSubGerencia=" + codigoSubGerencia
				+ ", descripcionSubGerencia=" + descripcionSubGerencia
				+ ", codigoRegion=" + codigoRegion + ", codigoZona="
				+ codigoZona + ", descripcionRegion=" + descripcionRegion
				+ ", desTipoComisionista=" + desTipoComisionista
				+ ", campaniaDesde=" + campaniaDesde + ", campaniaHasta="
				+ campaniaHasta + ", comodin=" + comodin
				+ ", codigoPorcentaje=" + codigoPorcentaje
				+ ", codigoComision=" + codigoComision
				+ ", descripcionComision=" + descripcionComision
				+ ", numDiasRecuAspi=" + numDiasRecuAspi + ", valPorcRecuAspi="
				+ valPorcRecuAspi + ", valPorcComiAspi=" + valPorcComiAspi
				+ ", numDiasRecuEjec=" + numDiasRecuEjec + ", valPorcRecuEjec="
				+ valPorcRecuEjec + ", valToleRecu=" + valToleRecu
				+ ", montoDesde=" + montoDesde + ", montoHasta=" + montoHasta
				+ ", valorPorc=" + valorPorc + ", numeroItem=" + numeroItem
				+ ", numeroPedidosDesde=" + numeroPedidosDesde
				+ ", numeroPedidosHasta=" + numeroPedidosHasta
				+ ", numeroIngresosDesde=" + numeroIngresosDesde
				+ ", numeroIngresosHasta=" + numeroIngresosHasta
				+ ", comodinRecuperacion=" + comodinRecuperacion
				+ ", indicadorPasePedido=" + indicadorPasePedido
				+ ", comodinRecuperacionAspirantes="
				+ comodinRecuperacionAspirantes + "]";
	}

	/**
	 * @return
	 */
	public Integer getIndicadorPasePedido() {
		return indicadorPasePedido;
	}

	/**
	 * @param indicadorPasePedido
	 */
	public void setIndicadorPasePedido(Integer indicadorPasePedido) {
		this.indicadorPasePedido = indicadorPasePedido;
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
	
	
	public Integer getNumeroItem() {
		return numeroItem;
	}

	public void setNumeroItem(Integer numeroItem) {
		this.numeroItem = numeroItem;
	}
	
	public String getValorPorc() {
		return valorPorc;
	}

	public void setValorPorc(String valorPorc) {
		this.valorPorc = valorPorc;
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
	 * @return the desTipoComisionista
	 */
	public String getDesTipoComisionista() {
		return desTipoComisionista;
	}

	/**
	 * @param desTipoComisionista the desTipoComisionista to set
	 */
	public void setDesTipoComisionista(String desTipoComisionista) {
		this.desTipoComisionista = desTipoComisionista;
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
	 * @return the comodin
	 */
	public Integer getComodin() {
		return comodin;
	}

	/**
	 * @param comodin the comodin to set
	 */
	public void setComodin(Integer comodin) {
		this.comodin = comodin;
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

	public Integer getNumeroPedidosDesde() {
		return numeroPedidosDesde;
	}

	public void setNumeroPedidosDesde(Integer numeroPedidosDesde) {
		this.numeroPedidosDesde = numeroPedidosDesde;
	}

	public Integer getNumeroPedidosHasta() {
		return numeroPedidosHasta;
	}

	public void setNumeroPedidosHasta(Integer numeroPedidosHasta) {
		this.numeroPedidosHasta = numeroPedidosHasta;
	}

	public Integer getNumeroIngresosDesde() {
		return numeroIngresosDesde;
	}

	public void setNumeroIngresosDesde(Integer numeroIngresosDesde) {
		this.numeroIngresosDesde = numeroIngresosDesde;
	}

	public Integer getNumeroIngresosHasta() {
		return numeroIngresosHasta;
	}

	public void setNumeroIngresosHasta(Integer numeroIngresosHasta) {
		this.numeroIngresosHasta = numeroIngresosHasta;
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

	public String getOidSubGerencia() {
		return oidSubGerencia;
	}

	public void setOidSubGerencia(String oidSubGerencia) {
		this.oidSubGerencia = oidSubGerencia;
	}

	public String getCodigoSubGerencia() {
		return codigoSubGerencia;
	}

	public void setCodigoSubGerencia(String codigoSubGerencia) {
		this.codigoSubGerencia = codigoSubGerencia;
	}

	public String getDescripcionSubGerencia() {
		return descripcionSubGerencia;
	}

	public void setDescripcionSubGerencia(String descripcionSubGerencia) {
		this.descripcionSubGerencia = descripcionSubGerencia;
	}

	public String getCodigoRegion() {
		return codigoRegion;
	}

	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	public String getCodigoZona() {
		return codigoZona;
	}

	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	public String getDescripcionRegion() {
		return descripcionRegion;
	}

	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}

	/**
	 * @return the comodinRecuperacion
	 */
	public Integer getComodinRecuperacion() {
		return comodinRecuperacion;
	}

	/**
	 * @param comodinRecuperacion the comodinRecuperacion to set
	 */
	public void setComodinRecuperacion(Integer comodinRecuperacion) {
		this.comodinRecuperacion = comodinRecuperacion;
	}
	
}
