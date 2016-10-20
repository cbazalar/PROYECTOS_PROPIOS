package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Yahir Rivas L.
 *
 */
public class Nivel extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String codigoNivel;
	private String nivel;
	private String nroPedidoInicial;
	private String nroPedidoFinal;
	private String nroPedidoTolerancia;
	private String gananciaCumplimiento;
	private String gananciaSobrecumplimiento;
	private String accion;
	private String estado;
    private String tolePedido;
    private String tolePorc;
    private String codigoTipoComi;
    private String porcComiPediCons;
    private String porcComiPediNCon;
    private String porcComiTole;
    private String montPediCons;
    private String montPediNCon;
    private String montTole;
    private String correlativo;
    private String montoVentaMinimo;
    private String montoVentaMaximo;
    private String montoVentaTolerancia;
    private String indicadorTipoMeta;
    
//    RCR PER-SICC-2015-0429
    private String ingresoMeta;
    
//    PER-SiCC-2015-0548
    private String condCapitalizacion;
    private String metaCapitalizacion;
    
       
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
	 * @return the nroPedidoInicial
	 */
	public String getNroPedidoInicial() {
		return nroPedidoInicial;
	}
	/**
	 * @param nroPedidoInicial the nroPedidoInicial to set
	 */
	public void setNroPedidoInicial(String nroPedidoInicial) {
		this.nroPedidoInicial = nroPedidoInicial;
	}
	/**
	 * @return the nroPedidoFinal
	 */
	public String getNroPedidoFinal() {
		return nroPedidoFinal;
	}
	/**
	 * @param nroPedidoFinal the nroPedidoFinal to set
	 */
	public void setNroPedidoFinal(String nroPedidoFinal) {
		this.nroPedidoFinal = nroPedidoFinal;
	}
	/**
	 * @return the nroPedidoTolerancia
	 */
	public String getNroPedidoTolerancia() {
		return nroPedidoTolerancia;
	}
	/**
	 * @param nroPedidoTolerancia the nroPedidoTolerancia to set
	 */
	public void setNroPedidoTolerancia(String nroPedidoTolerancia) {
		this.nroPedidoTolerancia = nroPedidoTolerancia;
	}
	/**
	 * @return the gananciaCumplimiento
	 */
	public String getGananciaCumplimiento() {
		return gananciaCumplimiento;
	}
	/**
	 * @param gananciaCumplimiento the gananciaCumplimiento to set
	 */
	public void setGananciaCumplimiento(String gananciaCumplimiento) {
		this.gananciaCumplimiento = gananciaCumplimiento;
	}
	/**
	 * @return the gananciaSobrecumplimiento
	 */
	public String getGananciaSobrecumplimiento() {
		return gananciaSobrecumplimiento;
	}
	/**
	 * @param gananciaSobrecumplimiento the gananciaSobrecumplimiento to set
	 */
	public void setGananciaSobrecumplimiento(String gananciaSobrecumplimiento) {
		this.gananciaSobrecumplimiento = gananciaSobrecumplimiento;
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
	 * @return the tolePedido
	 */
	public String getTolePedido() {
		return tolePedido;
	}
	/**
	 * @param tolePedido the tolePedido to set
	 */
	public void setTolePedido(String tolePedido) {
		this.tolePedido = tolePedido;
	}
	/**
	 * @return the tolePorc
	 */
	public String getTolePorc() {
		return tolePorc;
	}
	/**
	 * @param tolePorc the tolePorc to set
	 */
	public void setTolePorc(String tolePorc) {
		this.tolePorc = tolePorc;
	}
	/**
	 * @return the codigoTipoComi
	 */
	public String getCodigoTipoComi() {
		return codigoTipoComi;
	}
	/**
	 * @param codigoTipoComi the codigoTipoComi to set
	 */
	public void setCodigoTipoComi(String codigoTipoComi) {
		this.codigoTipoComi = codigoTipoComi;
	}
	/**
	 * @return the porcComiPediCons
	 */
	public String getPorcComiPediCons() {
		return porcComiPediCons;
	}
	/**
	 * @param porcComiPediCons the porcComiPediCons to set
	 */
	public void setPorcComiPediCons(String porcComiPediCons) {
		this.porcComiPediCons = porcComiPediCons;
	}
	/**
	 * @return the porcComiPediNCon
	 */
	public String getPorcComiPediNCon() {
		return porcComiPediNCon;
	}
	/**
	 * @param porcComiPediNCon the porcComiPediNCon to set
	 */
	public void setPorcComiPediNCon(String porcComiPediNCon) {
		this.porcComiPediNCon = porcComiPediNCon;
	}
	/**
	 * @return the porcComiTole
	 */
	public String getPorcComiTole() {
		return porcComiTole;
	}
	/**
	 * @param porcComiTole the porcComiTole to set
	 */
	public void setPorcComiTole(String porcComiTole) {
		this.porcComiTole = porcComiTole;
	}
	/**
	 * @return the montPediCons
	 */
	public String getMontPediCons() {
		return montPediCons;
	}
	/**
	 * @param montPediCons the montPediCons to set
	 */
	public void setMontPediCons(String montPediCons) {
		this.montPediCons = montPediCons;
	}
	/**
	 * @return the montPediNCon
	 */
	public String getMontPediNCon() {
		return montPediNCon;
	}
	/**
	 * @param montPediNCon the montPediNCon to set
	 */
	public void setMontPediNCon(String montPediNCon) {
		this.montPediNCon = montPediNCon;
	}
	/**
	 * @return the montTole
	 */
	public String getMontTole() {
		return montTole;
	}
	/**
	 * @param montTole the montTole to set
	 */
	public void setMontTole(String montTole) {
		this.montTole = montTole;
	}
	/**
	 * @return the nivel
	 */
	public String getNivel() {
		return nivel;
	}
	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoNivel == null) ? 0 : codigoNivel.hashCode());
		result = prime * result + ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result + ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime * result + ((codigoTipoComi == null) ? 0 : codigoTipoComi.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((gananciaCumplimiento == null) ? 0 : gananciaCumplimiento.hashCode());
		result = prime * result + ((gananciaSobrecumplimiento == null) ? 0 : gananciaSobrecumplimiento.hashCode());
		result = prime * result + ((montPediCons == null) ? 0 : montPediCons.hashCode());
		result = prime * result + ((montPediNCon == null) ? 0 : montPediNCon.hashCode());
		result = prime * result + ((montTole == null) ? 0 : montTole.hashCode());
		result = prime * result + ((nivel == null) ? 0 : nivel.hashCode());
		result = prime * result + ((nroPedidoFinal == null) ? 0 : nroPedidoFinal.hashCode());
		result = prime * result + ((nroPedidoInicial == null) ? 0 : nroPedidoInicial.hashCode());
		result = prime * result + ((nroPedidoTolerancia == null) ? 0 : nroPedidoTolerancia.hashCode());
		result = prime * result + ((porcComiPediCons == null) ? 0 : porcComiPediCons.hashCode());
		result = prime * result + ((porcComiPediNCon == null) ? 0 : porcComiPediNCon.hashCode());
		result = prime * result + ((porcComiTole == null) ? 0 : porcComiTole.hashCode());
		result = prime * result + ((tolePedido == null) ? 0 : tolePedido.hashCode());
		result = prime * result + ((tolePorc == null) ? 0 : tolePorc.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Nivel other = (Nivel) obj;
		if (codigoNivel == null) {
			if (other.codigoNivel != null)
				return false;
		} else if (!codigoNivel.equals(other.codigoNivel))
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
		if (codigoTipoComi == null) {
			if (other.codigoTipoComi != null)
				return false;
		} else if (!codigoTipoComi.equals(other.codigoTipoComi))
			return false;
		if (estado == null) {
			if (other.estado != null)
				return false;
		} else if (!estado.equals(other.estado))
			return false;
		if (gananciaCumplimiento == null) {
			if (other.gananciaCumplimiento != null)
				return false;
		} else if (!gananciaCumplimiento.equals(other.gananciaCumplimiento))
			return false;
		if (gananciaSobrecumplimiento == null) {
			if (other.gananciaSobrecumplimiento != null)
				return false;
		} else if (!gananciaSobrecumplimiento.equals(other.gananciaSobrecumplimiento))
			return false;
		if (montPediCons == null) {
			if (other.montPediCons != null)
				return false;
		} else if (!montPediCons.equals(other.montPediCons))
			return false;
		if (montPediNCon == null) {
			if (other.montPediNCon != null)
				return false;
		} else if (!montPediNCon.equals(other.montPediNCon))
			return false;
		if (montTole == null) {
			if (other.montTole != null)
				return false;
		} else if (!montTole.equals(other.montTole))
			return false;
		if (nivel == null) {
			if (other.nivel != null)
				return false;
		} else if (!nivel.equals(other.nivel))
			return false;
		if (nroPedidoFinal == null) {
			if (other.nroPedidoFinal != null)
				return false;
		} else if (!nroPedidoFinal.equals(other.nroPedidoFinal))
			return false;
		if (nroPedidoInicial == null) {
			if (other.nroPedidoInicial != null)
				return false;
		} else if (!nroPedidoInicial.equals(other.nroPedidoInicial))
			return false;
		if (nroPedidoTolerancia == null) {
			if (other.nroPedidoTolerancia != null)
				return false;
		} else if (!nroPedidoTolerancia.equals(other.nroPedidoTolerancia))
			return false;
		if (porcComiPediCons == null) {
			if (other.porcComiPediCons != null)
				return false;
		} else if (!porcComiPediCons.equals(other.porcComiPediCons))
			return false;
		if (porcComiPediNCon == null) {
			if (other.porcComiPediNCon != null)
				return false;
		} else if (!porcComiPediNCon.equals(other.porcComiPediNCon))
			return false;
		if (porcComiTole == null) {
			if (other.porcComiTole != null)
				return false;
		} else if (!porcComiTole.equals(other.porcComiTole))
			return false;
		if (tolePedido == null) {
			if (other.tolePedido != null)
				return false;
		} else if (!tolePedido.equals(other.tolePedido))
			return false;
		if (tolePorc == null) {
			if (other.tolePorc != null)
				return false;
		} else if (!tolePorc.equals(other.tolePorc))
			return false;
		return true;
	}
	public String getCorrelativo() {
		return correlativo;
	}
	public void setCorrelativo(String correlativo) {
		this.correlativo = correlativo;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/**
	 * @return the montoVentaMinimo
	 */
	public String getMontoVentaMinimo() {
		return montoVentaMinimo;
	}
	/**
	 * @param montoVentaMinimo the montoVentaMinimo to set
	 */
	public void setMontoVentaMinimo(String montoVentaMinimo) {
		this.montoVentaMinimo = montoVentaMinimo;
	}
	/**
	 * @return the montoVentaMaximo
	 */
	public String getMontoVentaMaximo() {
		return montoVentaMaximo;
	}
	/**
	 * @param montoVentaMaximo the montoVentaMaximo to set
	 */
	public void setMontoVentaMaximo(String montoVentaMaximo) {
		this.montoVentaMaximo = montoVentaMaximo;
	}
	/**
	 * @return the montoVentaTolerancia
	 */
	public String getMontoVentaTolerancia() {
		return montoVentaTolerancia;
	}
	/**
	 * @param montoVentaTolerancia the montoVentaTolerancia to set
	 */
	public void setMontoVentaTolerancia(String montoVentaTolerancia) {
		this.montoVentaTolerancia = montoVentaTolerancia;
	}
	/**
	 * @return the indicadorTipoMeta
	 */
	public String getIndicadorTipoMeta() {
		return indicadorTipoMeta;
	}
	/**
	 * @param indicadorTipoMeta the indicadorTipoMeta to set
	 */
	public void setIndicadorTipoMeta(String indicadorTipoMeta) {
		this.indicadorTipoMeta = indicadorTipoMeta;
	}
	
	public String getIngresoMeta() {
		return ingresoMeta;
	}
	
	public void setIngresoMeta(String ingresoMeta) {
		this.ingresoMeta = ingresoMeta;
	}
	
	public String getCondCapitalizacion() {
		return condCapitalizacion;
	}
	
	public void setCondCapitalizacion(String condCapitalizacion) {
		this.condCapitalizacion = condCapitalizacion;
	}
	
	public String getMetaCapitalizacion() {
		return metaCapitalizacion;
	}
	
	public void setMetaCapitalizacion(String metaCapitalizacion) {
		this.metaCapitalizacion = metaCapitalizacion;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Nivel [codigoPais=" + codigoPais + ", codigoPrograma="
				+ codigoPrograma + ", codigoNivel=" + codigoNivel + ", nivel="
				+ nivel + ", nroPedidoInicial=" + nroPedidoInicial
				+ ", nroPedidoFinal=" + nroPedidoFinal
				+ ", nroPedidoTolerancia=" + nroPedidoTolerancia
				+ ", gananciaCumplimiento=" + gananciaCumplimiento
				+ ", gananciaSobrecumplimiento=" + gananciaSobrecumplimiento
				+ ", accion=" + accion + ", estado=" + estado + ", tolePedido="
				+ tolePedido + ", tolePorc=" + tolePorc + ", codigoTipoComi="
				+ codigoTipoComi + ", porcComiPediCons=" + porcComiPediCons
				+ ", porcComiPediNCon=" + porcComiPediNCon + ", porcComiTole="
				+ porcComiTole + ", montPediCons=" + montPediCons
				+ ", montPediNCon=" + montPediNCon + ", montTole=" + montTole
				+ ", correlativo=" + correlativo + ", montoVentaMinimo="
				+ montoVentaMinimo + ", montoVentaMaximo=" + montoVentaMaximo
				+ ", montoVentaTolerancia=" + montoVentaTolerancia
				+ ", indicadorTipoMeta=" + indicadorTipoMeta + ", ingresoMeta="
				+ ingresoMeta + ", condCapitalizacion=" + condCapitalizacion
				+ ", metaCapitalizacion=" + metaCapitalizacion + "]";
	}
}