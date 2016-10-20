package biz.belcorp.ssicc.dao.spusicc.let.model;

import java.util.ArrayList;
import java.util.List;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author aoviedo
 *
 */
public class ProgramaCorporativo extends AuditableBaseObject {
	
	private static final long serialVersionUID = 1L;
	
	//Datos Generales
	private String codigoPais;
	private String codigoPrograma;
	private String descripcionPrograma;
	private String periodoInicio;
	private String periodoFin;
	
	private String indicadorActivoRangoNivel;
	private String indicadorActivoRangoRetencion;
	private String indicadorActivoTramos;
	private String indicadorActivoPremios;
	
	//Seccin
	private String numeroMinimoActSec;
	private String numeroMinimoIngresos;
	private String porcentajeActividad;
	private String alcanceRecomendaciones;
	
	//Nivel Exito
	private String numeroCampanasMantNivelExito;
	private String evaluacionNivelExito;
	private String numeroCampanasBajaAuto;
	
	//Ganancia
	private String montoDsctoToleranciaPedidos;
	private String indExigenciaPedidoWeb;
	private String porcentajePenalidad;
	
	private String indGananciaLiderNueva;
	private String numeroCampanasGracia;
	
	private String numeroCampanasCambiarNivel;
	private String numeroCampanasMantNivelGananciaPlus;
	
	private List rangoNivelList = new ArrayList();
	private List rangoRetencionList = new ArrayList();
	private List tramosList = new ArrayList();
	private List premiosList = new ArrayList();
	
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
	 * @return the descripcionPrograma
	 */
	public String getDescripcionPrograma() {
		return descripcionPrograma;
	}
	/**
	 * @param descripcionPrograma the descripcionPrograma to set
	 */
	public void setDescripcionPrograma(String descripcionPrograma) {
		this.descripcionPrograma = descripcionPrograma;
	}
	/**
	 * @return the periodoInicio
	 */
	public String getPeriodoInicio() {
		return periodoInicio;
	}
	/**
	 * @param periodoInicio the periodoInicio to set
	 */
	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
	}
	/**
	 * @return the periodoFin
	 */
	public String getPeriodoFin() {
		return periodoFin;
	}
	/**
	 * @param periodoFin the periodoFin to set
	 */
	public void setPeriodoFin(String periodoFin) {
		this.periodoFin = periodoFin;
	}
	/**
	 * @return the numeroMinimoActSec
	 */
	public String getNumeroMinimoActSec() {
		return numeroMinimoActSec;
	}
	/**
	 * @param numeroMinimoActSec the numeroMinimoActSec to set
	 */
	public void setNumeroMinimoActSec(String numeroMinimoActSec) {
		this.numeroMinimoActSec = numeroMinimoActSec;
	}
	/**
	 * @return the numeroMinimoIngresos
	 */
	public String getNumeroMinimoIngresos() {
		return numeroMinimoIngresos;
	}
	/**
	 * @param numeroMinimoIngresos the numeroMinimoIngresos to set
	 */
	public void setNumeroMinimoIngresos(String numeroMinimoIngresos) {
		this.numeroMinimoIngresos = numeroMinimoIngresos;
	}
	/**
	 * @return the porcentajeActividad
	 */
	public String getPorcentajeActividad() {
		return porcentajeActividad;
	}
	/**
	 * @param porcentajeActividad the porcentajeActividad to set
	 */
	public void setPorcentajeActividad(String porcentajeActividad) {
		this.porcentajeActividad = porcentajeActividad;
	}
	/**
	 * @return the alcanceRecomendaciones
	 */
	public String getAlcanceRecomendaciones() {
		return alcanceRecomendaciones;
	}
	/**
	 * @param alcanceRecomendaciones the alcanceRecomendaciones to set
	 */
	public void setAlcanceRecomendaciones(String alcanceRecomendaciones) {
		this.alcanceRecomendaciones = alcanceRecomendaciones;
	}
	/**
	 * @return the numeroCampanasMantNivelExito
	 */
	public String getNumeroCampanasMantNivelExito() {
		return numeroCampanasMantNivelExito;
	}
	/**
	 * @param numeroCampanasMantNivelExito the numeroCampanasMantNivelExito to set
	 */
	public void setNumeroCampanasMantNivelExito(String numeroCampanasMantNivelExito) {
		this.numeroCampanasMantNivelExito = numeroCampanasMantNivelExito;
	}
	/**
	 * @return the evaluacionNivelExito
	 */
	public String getEvaluacionNivelExito() {
		return evaluacionNivelExito;
	}
	/**
	 * @param evaluacionNivelExito the evaluacionNivelExito to set
	 */
	public void setEvaluacionNivelExito(String evaluacionNivelExito) {
		this.evaluacionNivelExito = evaluacionNivelExito;
	}
	/**
	 * @return the numeroCampanasBajaAuto
	 */
	public String getNumeroCampanasBajaAuto() {
		return numeroCampanasBajaAuto;
	}
	/**
	 * @param numeroCampanasBajaAuto the numeroCampanasBajaAuto to set
	 */
	public void setNumeroCampanasBajaAuto(String numeroCampanasBajaAuto) {
		this.numeroCampanasBajaAuto = numeroCampanasBajaAuto;
	}
	/**
	 * @return the montoDsctoToleranciaPedidos
	 */
	public String getMontoDsctoToleranciaPedidos() {
		return montoDsctoToleranciaPedidos;
	}
	/**
	 * @param montoDsctoToleranciaPedidos the montoDsctoToleranciaPedidos to set
	 */
	public void setMontoDsctoToleranciaPedidos(String montoDsctoToleranciaPedidos) {
		this.montoDsctoToleranciaPedidos = montoDsctoToleranciaPedidos;
	}
	/**
	 * @return the indExigenciaPedidoWeb
	 */
	public String getIndExigenciaPedidoWeb() {
		return indExigenciaPedidoWeb;
	}
	/**
	 * @param indExigenciaPedidoWeb the indExigenciaPedidoWeb to set
	 */
	public void setIndExigenciaPedidoWeb(String indExigenciaPedidoWeb) {
		this.indExigenciaPedidoWeb = indExigenciaPedidoWeb;
	}
	/**
	 * @return the porcentajePenalidad
	 */
	public String getPorcentajePenalidad() {
		return porcentajePenalidad;
	}
	/**
	 * @param porcentajePenalidad the porcentajePenalidad to set
	 */
	public void setPorcentajePenalidad(String porcentajePenalidad) {
		this.porcentajePenalidad = porcentajePenalidad;
	}
	/**
	 * @return the indGananciaLiderNueva
	 */
	public String getIndGananciaLiderNueva() {
		return indGananciaLiderNueva;
	}
	/**
	 * @param indGananciaLiderNueva the indGananciaLiderNueva to set
	 */
	public void setIndGananciaLiderNueva(String indGananciaLiderNueva) {
		this.indGananciaLiderNueva = indGananciaLiderNueva;
	}
	/**
	 * @return the numeroCampanasGracia
	 */
	public String getNumeroCampanasGracia() {
		return numeroCampanasGracia;
	}
	/**
	 * @param numeroCampanasGracia the numeroCampanasGracia to set
	 */
	public void setNumeroCampanasGracia(String numeroCampanasGracia) {
		this.numeroCampanasGracia = numeroCampanasGracia;
	}
	/**
	 * @return the numeroCampanasCambiarNivel
	 */
	public String getNumeroCampanasCambiarNivel() {
		return numeroCampanasCambiarNivel;
	}
	/**
	 * @param numeroCampanasCambiarNivel the numeroCampanasCambiarNivel to set
	 */
	public void setNumeroCampanasCambiarNivel(String numeroCampanasCambiarNivel) {
		this.numeroCampanasCambiarNivel = numeroCampanasCambiarNivel;
	}
	/**
	 * @return the numeroCampanasMantNivelGananciaPlus
	 */
	public String getNumeroCampanasMantNivelGananciaPlus() {
		return numeroCampanasMantNivelGananciaPlus;
	}
	/**
	 * @param numeroCampanasMantNivelGananciaPlus the numeroCampanasMantNivelGananciaPlus to set
	 */
	public void setNumeroCampanasMantNivelGananciaPlus(
			String numeroCampanasMantNivelGananciaPlus) {
		this.numeroCampanasMantNivelGananciaPlus = numeroCampanasMantNivelGananciaPlus;
	}
	/**
	 * @return the rangoNivelList
	 */
	public List getRangoNivelList() {
		return rangoNivelList;
	}
	/**
	 * @param rangoNivelList the rangoNivelList to set
	 */
	public void setRangoNivelList(List rangoNivelList) {
		this.rangoNivelList = rangoNivelList;
	}
	/**
	 * @return the rangoRetencionList
	 */
	public List getRangoRetencionList() {
		return rangoRetencionList;
	}
	/**
	 * @param rangoRetencionList the rangoRetencionList to set
	 */
	public void setRangoRetencionList(List rangoRetencionList) {
		this.rangoRetencionList = rangoRetencionList;
	}
	/**
	 * @return the tramosList
	 */
	public List getTramosList() {
		return tramosList;
	}
	/**
	 * @param tramosList the tramosList to set
	 */
	public void setTramosList(List tramosList) {
		this.tramosList = tramosList;
	}
	/**
	 * @return the premiosList
	 */
	public List getPremiosList() {
		return premiosList;
	}
	/**
	 * @param premiosList the premiosList to set
	 */
	public void setPremiosList(List premiosList) {
		this.premiosList = premiosList;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProgramaCorporativo [alcanceRecomendaciones="
				+ alcanceRecomendaciones + ", codigoPais=" + codigoPais
				+ ", codigoPrograma=" + codigoPrograma
				+ ", descripcionPrograma=" + descripcionPrograma
				+ ", evaluacionNivelExito=" + evaluacionNivelExito
				+ ", indExigenciaPedidoWeb=" + indExigenciaPedidoWeb
				+ ", indGananciaLiderNueva=" + indGananciaLiderNueva
				+ ", montoDsctoToleranciaPedidos="
				+ montoDsctoToleranciaPedidos + ", numeroCampanasBajaAuto="
				+ numeroCampanasBajaAuto + ", numeroCampanasCambiarNivel="
				+ numeroCampanasCambiarNivel + ", numeroCampanasGracia="
				+ numeroCampanasGracia + ", numeroCampanasMantNivelExito="
				+ numeroCampanasMantNivelExito
				+ ", numeroCampanasMantNivelGananciaPlus="
				+ numeroCampanasMantNivelGananciaPlus + ", numeroMinimoActSec="
				+ numeroMinimoActSec + ", numeroMinimoIngresos="
				+ numeroMinimoIngresos + ", periodoFin=" + periodoFin
				+ ", periodoInicio=" + periodoInicio + ", porcentajeActividad="
				+ porcentajeActividad + ", porcentajePenalidad="
				+ porcentajePenalidad + ", premiosList=" + premiosList
				+ ", rangoNivelList=" + rangoNivelList
				+ ", rangoRetencionList=" + rangoRetencionList
				+ ", tramosList=" + tramosList + "]";
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((alcanceRecomendaciones == null) ? 0
						: alcanceRecomendaciones.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		result = prime
				* result
				+ ((descripcionPrograma == null) ? 0 : descripcionPrograma
						.hashCode());
		result = prime
				* result
				+ ((evaluacionNivelExito == null) ? 0 : evaluacionNivelExito
						.hashCode());
		result = prime
				* result
				+ ((indExigenciaPedidoWeb == null) ? 0 : indExigenciaPedidoWeb
						.hashCode());
		result = prime
				* result
				+ ((indGananciaLiderNueva == null) ? 0 : indGananciaLiderNueva
						.hashCode());
		result = prime
				* result
				+ ((montoDsctoToleranciaPedidos == null) ? 0
						: montoDsctoToleranciaPedidos.hashCode());
		result = prime
				* result
				+ ((numeroCampanasBajaAuto == null) ? 0
						: numeroCampanasBajaAuto.hashCode());
		result = prime
				* result
				+ ((numeroCampanasCambiarNivel == null) ? 0
						: numeroCampanasCambiarNivel.hashCode());
		result = prime
				* result
				+ ((numeroCampanasGracia == null) ? 0 : numeroCampanasGracia
						.hashCode());
		result = prime
				* result
				+ ((numeroCampanasMantNivelExito == null) ? 0
						: numeroCampanasMantNivelExito.hashCode());
		result = prime
				* result
				+ ((numeroCampanasMantNivelGananciaPlus == null) ? 0
						: numeroCampanasMantNivelGananciaPlus.hashCode());
		result = prime
				* result
				+ ((numeroMinimoActSec == null) ? 0 : numeroMinimoActSec
						.hashCode());
		result = prime
				* result
				+ ((numeroMinimoIngresos == null) ? 0 : numeroMinimoIngresos
						.hashCode());
		result = prime * result
				+ ((periodoFin == null) ? 0 : periodoFin.hashCode());
		result = prime * result
				+ ((periodoInicio == null) ? 0 : periodoInicio.hashCode());
		result = prime
				* result
				+ ((porcentajeActividad == null) ? 0 : porcentajeActividad
						.hashCode());
		result = prime
				* result
				+ ((porcentajePenalidad == null) ? 0 : porcentajePenalidad
						.hashCode());
		result = prime * result
				+ ((premiosList == null) ? 0 : premiosList.hashCode());
		result = prime * result
				+ ((rangoNivelList == null) ? 0 : rangoNivelList.hashCode());
		result = prime
				* result
				+ ((rangoRetencionList == null) ? 0 : rangoRetencionList
						.hashCode());
		result = prime * result
				+ ((tramosList == null) ? 0 : tramosList.hashCode());
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
		ProgramaCorporativo other = (ProgramaCorporativo) obj;
		if (alcanceRecomendaciones == null) {
			if (other.alcanceRecomendaciones != null)
				return false;
		} else if (!alcanceRecomendaciones.equals(other.alcanceRecomendaciones))
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
		if (descripcionPrograma == null) {
			if (other.descripcionPrograma != null)
				return false;
		} else if (!descripcionPrograma.equals(other.descripcionPrograma))
			return false;
		if (evaluacionNivelExito == null) {
			if (other.evaluacionNivelExito != null)
				return false;
		} else if (!evaluacionNivelExito.equals(other.evaluacionNivelExito))
			return false;
		if (indExigenciaPedidoWeb == null) {
			if (other.indExigenciaPedidoWeb != null)
				return false;
		} else if (!indExigenciaPedidoWeb.equals(other.indExigenciaPedidoWeb))
			return false;
		if (indGananciaLiderNueva == null) {
			if (other.indGananciaLiderNueva != null)
				return false;
		} else if (!indGananciaLiderNueva.equals(other.indGananciaLiderNueva))
			return false;
		if (montoDsctoToleranciaPedidos == null) {
			if (other.montoDsctoToleranciaPedidos != null)
				return false;
		} else if (!montoDsctoToleranciaPedidos
				.equals(other.montoDsctoToleranciaPedidos))
			return false;
		if (numeroCampanasBajaAuto == null) {
			if (other.numeroCampanasBajaAuto != null)
				return false;
		} else if (!numeroCampanasBajaAuto.equals(other.numeroCampanasBajaAuto))
			return false;
		if (numeroCampanasCambiarNivel == null) {
			if (other.numeroCampanasCambiarNivel != null)
				return false;
		} else if (!numeroCampanasCambiarNivel
				.equals(other.numeroCampanasCambiarNivel))
			return false;
		if (numeroCampanasGracia == null) {
			if (other.numeroCampanasGracia != null)
				return false;
		} else if (!numeroCampanasGracia.equals(other.numeroCampanasGracia))
			return false;
		if (numeroCampanasMantNivelExito == null) {
			if (other.numeroCampanasMantNivelExito != null)
				return false;
		} else if (!numeroCampanasMantNivelExito
				.equals(other.numeroCampanasMantNivelExito))
			return false;
		if (numeroCampanasMantNivelGananciaPlus == null) {
			if (other.numeroCampanasMantNivelGananciaPlus != null)
				return false;
		} else if (!numeroCampanasMantNivelGananciaPlus
				.equals(other.numeroCampanasMantNivelGananciaPlus))
			return false;
		if (numeroMinimoActSec == null) {
			if (other.numeroMinimoActSec != null)
				return false;
		} else if (!numeroMinimoActSec.equals(other.numeroMinimoActSec))
			return false;
		if (numeroMinimoIngresos == null) {
			if (other.numeroMinimoIngresos != null)
				return false;
		} else if (!numeroMinimoIngresos.equals(other.numeroMinimoIngresos))
			return false;
		if (periodoFin == null) {
			if (other.periodoFin != null)
				return false;
		} else if (!periodoFin.equals(other.periodoFin))
			return false;
		if (periodoInicio == null) {
			if (other.periodoInicio != null)
				return false;
		} else if (!periodoInicio.equals(other.periodoInicio))
			return false;
		if (porcentajeActividad == null) {
			if (other.porcentajeActividad != null)
				return false;
		} else if (!porcentajeActividad.equals(other.porcentajeActividad))
			return false;
		if (porcentajePenalidad == null) {
			if (other.porcentajePenalidad != null)
				return false;
		} else if (!porcentajePenalidad.equals(other.porcentajePenalidad))
			return false;
		if (premiosList == null) {
			if (other.premiosList != null)
				return false;
		} else if (!premiosList.equals(other.premiosList))
			return false;
		if (rangoNivelList == null) {
			if (other.rangoNivelList != null)
				return false;
		} else if (!rangoNivelList.equals(other.rangoNivelList))
			return false;
		if (rangoRetencionList == null) {
			if (other.rangoRetencionList != null)
				return false;
		} else if (!rangoRetencionList.equals(other.rangoRetencionList))
			return false;
		if (tramosList == null) {
			if (other.tramosList != null)
				return false;
		} else if (!tramosList.equals(other.tramosList))
			return false;
		return true;
	}
	/**
	 * @return the indicadorActivoRangoNivel
	 */
	public String getIndicadorActivoRangoNivel() {
		return indicadorActivoRangoNivel;
	}
	/**
	 * @param indicadorActivoRangoNivel the indicadorActivoRangoNivel to set
	 */
	public void setIndicadorActivoRangoNivel(String indicadorActivoRangoNivel) {
		this.indicadorActivoRangoNivel = indicadorActivoRangoNivel;
	}
	/**
	 * @return the indicadorActivoRangoRetencion
	 */
	public String getIndicadorActivoRangoRetencion() {
		return indicadorActivoRangoRetencion;
	}
	/**
	 * @param indicadorActivoRangoRetencion the indicadorActivoRangoRetencion to set
	 */
	public void setIndicadorActivoRangoRetencion(
			String indicadorActivoRangoRetencion) {
		this.indicadorActivoRangoRetencion = indicadorActivoRangoRetencion;
	}
	/**
	 * @return the indicadorActivoTramos
	 */
	public String getIndicadorActivoTramos() {
		return indicadorActivoTramos;
	}
	/**
	 * @param indicadorActivoTramos the indicadorActivoTramos to set
	 */
	public void setIndicadorActivoTramos(String indicadorActivoTramos) {
		this.indicadorActivoTramos = indicadorActivoTramos;
	}
	/**
	 * @return the indicadorActivoPremios
	 */
	public String getIndicadorActivoPremios() {
		return indicadorActivoPremios;
	}
	/**
	 * @param indicadorActivoPremios the indicadorActivoPremios to set
	 */
	public void setIndicadorActivoPremios(String indicadorActivoPremios) {
		this.indicadorActivoPremios = indicadorActivoPremios;
	}
}