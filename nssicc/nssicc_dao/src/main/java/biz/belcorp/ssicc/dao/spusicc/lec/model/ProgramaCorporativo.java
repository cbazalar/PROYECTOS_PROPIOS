package biz.belcorp.ssicc.dao.spusicc.lec.model;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Yahir Rivas L.
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
		
		//Ganancia
		private String montoDsctoToleranciaPedidos;
		private String indExigenciaPedidoWeb;
		private String porcentajePenalidad;
		
		private String indGananciaLiderNueva;
		private String numeroCampanasGracia;
		
		private String numeroCampanasCambiarNivel;
		private String numeroCampanasMantNivelGananciaPlus;
		
		private String tabSeleccion;
		
		private String campanyaPremioBuscar;

		/// Otros
		private String programaRecono;
		private String pedidoWeb;
		private String nroMinActSec;
		private String actCobranza;
		private String exigCursoCamb;
		private String  exigPedPersona;
		private String codTipoComi;
		//Inscripcion
		private String insPortalFFVV;
		private String insSistCome;
		private String insPosiLider;
		//Nivel Exito
		private String nivNroCampMante;
		private String nivNroCampTole;
		//Calendario Cobranza
		private String fechaFeriado;
		private String codigoAlcance;
		private String codigoRegion;
		private String codigoZona;
		private String tipoMedicionCob;
		// gestion desempenio
		private String numCampMediDese;
		private String numBajaAuto;
		private String numCampEval;
	
		private String codCamPerGrac;
		private String indicadorFeriado;		
		private String indicadorDespachoCanasta;
		private String indicadorConsiderarNuevas;
		private String indicadorGanaciaDePedido;
		private String indicadorConsiderarMetasIngresos;
		private String indicadorconsiderarCapitalizacion;
		private String indicadorExitoNuevas;

		
		public String getExigCursoCamb() {
			return exigCursoCamb;
		}
		public String getExigPedPersona() {
			return exigPedPersona;
		}
		public String getCodTipoComi() {
			return codTipoComi;
		}
		public void setExigCursoCamb(String exigCursoCamb) {
			this.exigCursoCamb = exigCursoCamb;
		}
		public void setExigPedPersona(String exigPedPersona) {
			this.exigPedPersona = exigPedPersona;
		}
		public void setCodTipoComi(String codTipoComi) {
			this.codTipoComi = codTipoComi;
		}
		public String getNumCampMediDese() {
			return numCampMediDese;
		}
		public String getNumBajaAuto() {
			return numBajaAuto;
		}
		public String getNumCampEval() {
			return numCampEval;
		}
		public void setNumCampMediDese(String numCampMediDese) {
			this.numCampMediDese = numCampMediDese;
		}
		public void setNumBajaAuto(String numBajaAuto) {
			this.numBajaAuto = numBajaAuto;
		}
		public void setNumCampEval(String numCampEval) {
			this.numCampEval = numCampEval;
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
		 * @return the tabSeleccion
		 */
		public String getTabSeleccion() {
			return tabSeleccion;
		}
		/**
		 * @param tabSeleccion the tabSeleccion to set
		 */
		public void setTabSeleccion(String tabSeleccion) {
			this.tabSeleccion = tabSeleccion;
		}
		/**
		 * @return the campanyaPremioBuscar
		 */
		public String getCampanyaPremioBuscar() {
			return campanyaPremioBuscar;
		}
		/**
		 * @param campanyaPremioBuscar the campanyaPremioBuscar to set
		 */
		public void setCampanyaPremioBuscar(String campanyaPremioBuscar) {
			this.campanyaPremioBuscar = campanyaPremioBuscar;
		}
		/**
		 * @return the programaRecono
		 */
		public String getProgramaRecono() {
			return programaRecono;
		}
		/**
		 * @param programaRecono the programaRecono to set
		 */
		public void setProgramaRecono(String programaRecono) {
			this.programaRecono = programaRecono;
		}
		/**
		 * @return the pedidoWeb
		 */
		public String getPedidoWeb() {
			return pedidoWeb;
		}
		/**
		 * @param pedidoWeb the pedidoWeb to set
		 */
		public void setPedidoWeb(String pedidoWeb) {
			this.pedidoWeb = pedidoWeb;
		}
		/**
		 * @return the nroMinActSec
		 */
		public String getNroMinActSec() {
			return nroMinActSec;
		}
		/**
		 * @param nroMinActSec the nroMinActSec to set
		 */
		public void setNroMinActSec(String nroMinActSec) {
			this.nroMinActSec = nroMinActSec;
		}
		/**
		 * @return the actCobranza
		 */
		public String getActCobranza() {
			return actCobranza;
		}
		/**
		 * @param actCobranza the actCobranza to set
		 */
		public void setActCobranza(String actCobranza) {
			this.actCobranza = actCobranza;
		}
		/**
		 * @return the insPortalFFVV
		 */
		public String getInsPortalFFVV() {
			return insPortalFFVV;
		}
		/**
		 * @param insPortalFFVV the insPortalFFVV to set
		 */
		public void setInsPortalFFVV(String insPortalFFVV) {
			this.insPortalFFVV = insPortalFFVV;
		}
		/**
		 * @return the insSistCome
		 */
		public String getInsSistCome() {
			return insSistCome;
		}
		/**
		 * @param insSistCome the insSistCome to set
		 */
		public void setInsSistCome(String insSistCome) {
			this.insSistCome = insSistCome;
		}
		/**
		 * @return the insPosiLider
		 */
		public String getInsPosiLider() {
			return insPosiLider;
		}
		/**
		 * @param insPosiLider the insPosiLider to set
		 */
		public void setInsPosiLider(String insPosiLider) {
			this.insPosiLider = insPosiLider;
		}
		/**
		 * @return the nivNroCampMante
		 */
		public String getNivNroCampMante() {
			return nivNroCampMante;
		}
		/**
		 * @param nivNroCampMante the nivNroCampMante to set
		 */
		public void setNivNroCampMante(String nivNroCampMante) {
			this.nivNroCampMante = nivNroCampMante;
		}
		/**
		 * @return the nivNroCampTole
		 */
		public String getNivNroCampTole() {
			return nivNroCampTole;
		}
		/**
		 * @param nivNroCampTole the nivNroCampTole to set
		 */
		public void setNivNroCampTole(String nivNroCampTole) {
			this.nivNroCampTole = nivNroCampTole;
		}
		/**
		 * @return the fechaFeriado
		 */
		public String getFechaFeriado() {
			return fechaFeriado;
		}
		/**
		 * @param fechaFeriado the fechaFeriado to set
		 */
		public void setFechaFeriado(String fechaFeriado) {
			this.fechaFeriado = fechaFeriado;
		}
		/**
		 * @return the codigoAlcance
		 */
		public String getCodigoAlcance() {
			return codigoAlcance;
		}
		/**
		 * @param codigoAlcance the codigoAlcance to set
		 */
		public void setCodigoAlcance(String codigoAlcance) {
			this.codigoAlcance = codigoAlcance;
		}
		/**
		 * @return the codigoRegion
		 */
		public String getCodigoRegion() {
			return codigoRegion;
		}
		/**
		 * @param codigoRegion the codigoRegion to set
		 */
		public void setCodigoRegion(String codigoRegion) {
			this.codigoRegion = codigoRegion;
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
		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((actCobranza == null) ? 0 : actCobranza.hashCode());
			result = prime * result + ((alcanceRecomendaciones == null) ? 0 : alcanceRecomendaciones.hashCode());
			result = prime * result + ((campanyaPremioBuscar == null) ? 0 : campanyaPremioBuscar.hashCode());
			result = prime * result + ((codigoAlcance == null) ? 0 : codigoAlcance.hashCode());
			result = prime * result + ((codigoPais == null) ? 0 : codigoPais.hashCode());
			result = prime * result + ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
			result = prime * result + ((codigoRegion == null) ? 0 : codigoRegion.hashCode());
			result = prime * result + ((codigoZona == null) ? 0 : codigoZona.hashCode());
			result = prime * result + ((descripcionPrograma == null) ? 0 : descripcionPrograma.hashCode());
			result = prime * result + ((fechaFeriado == null) ? 0 : fechaFeriado.hashCode());
			result = prime * result + ((indExigenciaPedidoWeb == null) ? 0 : indExigenciaPedidoWeb.hashCode());
			result = prime * result + ((indGananciaLiderNueva == null) ? 0 : indGananciaLiderNueva.hashCode());
			result = prime * result + ((indicadorActivoPremios == null) ? 0 : indicadorActivoPremios.hashCode());
			result = prime * result + ((indicadorActivoRangoNivel == null) ? 0 : indicadorActivoRangoNivel.hashCode());
			result = prime * result + ((indicadorActivoRangoRetencion == null) ? 0 : indicadorActivoRangoRetencion.hashCode());
			result = prime * result + ((indicadorActivoTramos == null) ? 0 : indicadorActivoTramos.hashCode());
			result = prime * result + ((insPortalFFVV == null) ? 0 : insPortalFFVV.hashCode());
			result = prime * result + ((insPosiLider == null) ? 0 : insPosiLider.hashCode());
			result = prime * result + ((insSistCome == null) ? 0 : insSistCome.hashCode());
			result = prime * result + ((montoDsctoToleranciaPedidos == null) ? 0 : montoDsctoToleranciaPedidos.hashCode());
			result = prime * result + ((nivNroCampMante == null) ? 0 : nivNroCampMante.hashCode());
			result = prime * result + ((nivNroCampTole == null) ? 0 : nivNroCampTole.hashCode());
			result = prime * result + ((nroMinActSec == null) ? 0 : nroMinActSec.hashCode());
			result = prime * result + ((numeroCampanasCambiarNivel == null) ? 0 : numeroCampanasCambiarNivel.hashCode());
			result = prime * result + ((numeroCampanasGracia == null) ? 0 : numeroCampanasGracia.hashCode());
			result = prime * result + ((numeroCampanasMantNivelGananciaPlus == null) ? 0 : numeroCampanasMantNivelGananciaPlus.hashCode());
			result = prime * result + ((numeroMinimoActSec == null) ? 0 : numeroMinimoActSec.hashCode());
			result = prime * result + ((numeroMinimoIngresos == null) ? 0 : numeroMinimoIngresos.hashCode());
			result = prime * result + ((pedidoWeb == null) ? 0 : pedidoWeb.hashCode());
			result = prime * result + ((periodoFin == null) ? 0 : periodoFin.hashCode());
			result = prime * result + ((periodoInicio == null) ? 0 : periodoInicio.hashCode());
			result = prime * result + ((porcentajeActividad == null) ? 0 : porcentajeActividad.hashCode());
			result = prime * result + ((porcentajePenalidad == null) ? 0 : porcentajePenalidad.hashCode());
			result = prime * result + ((programaRecono == null) ? 0 : programaRecono.hashCode());
			result = prime * result + ((tabSeleccion == null) ? 0 : tabSeleccion.hashCode());
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
			ProgramaCorporativo other = (ProgramaCorporativo) obj;
			if (actCobranza == null) {
				if (other.actCobranza != null)
					return false;
			} else if (!actCobranza.equals(other.actCobranza))
				return false;
			if (alcanceRecomendaciones == null) {
				if (other.alcanceRecomendaciones != null)
					return false;
			} else if (!alcanceRecomendaciones.equals(other.alcanceRecomendaciones))
				return false;
			if (campanyaPremioBuscar == null) {
				if (other.campanyaPremioBuscar != null)
					return false;
			} else if (!campanyaPremioBuscar.equals(other.campanyaPremioBuscar))
				return false;
			if (codigoAlcance == null) {
				if (other.codigoAlcance != null)
					return false;
			} else if (!codigoAlcance.equals(other.codigoAlcance))
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
			if (codigoRegion == null) {
				if (other.codigoRegion != null)
					return false;
			} else if (!codigoRegion.equals(other.codigoRegion))
				return false;
			if (codigoZona == null) {
				if (other.codigoZona != null)
					return false;
			} else if (!codigoZona.equals(other.codigoZona))
				return false;
			if (descripcionPrograma == null) {
				if (other.descripcionPrograma != null)
					return false;
			} else if (!descripcionPrograma.equals(other.descripcionPrograma))
				return false;
			if (fechaFeriado == null) {
				if (other.fechaFeriado != null)
					return false;
			} else if (!fechaFeriado.equals(other.fechaFeriado))
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
			if (indicadorActivoPremios == null) {
				if (other.indicadorActivoPremios != null)
					return false;
			} else if (!indicadorActivoPremios.equals(other.indicadorActivoPremios))
				return false;
			if (indicadorActivoRangoNivel == null) {
				if (other.indicadorActivoRangoNivel != null)
					return false;
			} else if (!indicadorActivoRangoNivel.equals(other.indicadorActivoRangoNivel))
				return false;
			if (indicadorActivoRangoRetencion == null) {
				if (other.indicadorActivoRangoRetencion != null)
					return false;
			} else if (!indicadorActivoRangoRetencion.equals(other.indicadorActivoRangoRetencion))
				return false;
			if (indicadorActivoTramos == null) {
				if (other.indicadorActivoTramos != null)
					return false;
			} else if (!indicadorActivoTramos.equals(other.indicadorActivoTramos))
				return false;
			if (insPortalFFVV == null) {
				if (other.insPortalFFVV != null)
					return false;
			} else if (!insPortalFFVV.equals(other.insPortalFFVV))
				return false;
			if (insPosiLider == null) {
				if (other.insPosiLider != null)
					return false;
			} else if (!insPosiLider.equals(other.insPosiLider))
				return false;
			if (insSistCome == null) {
				if (other.insSistCome != null)
					return false;
			} else if (!insSistCome.equals(other.insSistCome))
				return false;
			if (montoDsctoToleranciaPedidos == null) {
				if (other.montoDsctoToleranciaPedidos != null)
					return false;
			} else if (!montoDsctoToleranciaPedidos.equals(other.montoDsctoToleranciaPedidos))
				return false;
			if (nivNroCampMante == null) {
				if (other.nivNroCampMante != null)
					return false;
			} else if (!nivNroCampMante.equals(other.nivNroCampMante))
				return false;
			if (nivNroCampTole == null) {
				if (other.nivNroCampTole != null)
					return false;
			} else if (!nivNroCampTole.equals(other.nivNroCampTole))
				return false;
			if (nroMinActSec == null) {
				if (other.nroMinActSec != null)
					return false;
			} else if (!nroMinActSec.equals(other.nroMinActSec))
				return false;
			if (numeroCampanasCambiarNivel == null) {
				if (other.numeroCampanasCambiarNivel != null)
					return false;
			} else if (!numeroCampanasCambiarNivel.equals(other.numeroCampanasCambiarNivel))
				return false;
			if (numeroCampanasGracia == null) {
				if (other.numeroCampanasGracia != null)
					return false;
			} else if (!numeroCampanasGracia.equals(other.numeroCampanasGracia))
				return false;
			if (numeroCampanasMantNivelGananciaPlus == null) {
				if (other.numeroCampanasMantNivelGananciaPlus != null)
					return false;
			} else if (!numeroCampanasMantNivelGananciaPlus.equals(other.numeroCampanasMantNivelGananciaPlus))
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
			if (pedidoWeb == null) {
				if (other.pedidoWeb != null)
					return false;
			} else if (!pedidoWeb.equals(other.pedidoWeb))
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
			if (programaRecono == null) {
				if (other.programaRecono != null)
					return false;
			} else if (!programaRecono.equals(other.programaRecono))
				return false;
			if (tabSeleccion == null) {
				if (other.tabSeleccion != null)
					return false;
			} else if (!tabSeleccion.equals(other.tabSeleccion))
				return false;
			return true;
		}
				
		public String getTipoMedicionCob() {
			return tipoMedicionCob;
		}
		public void setTipoMedicionCob(String tipoMedicionCob) {
			this.tipoMedicionCob = tipoMedicionCob;
		}
		/**
		 * @return the codCamPerGrac
		 */
		public String getCodCamPerGrac() {
			return codCamPerGrac;
		}
		/**
		 * @param codCamPerGrac the codCamPerGrac to set
		 */
		public void setCodCamPerGrac(String codCamPerGrac) {
			this.codCamPerGrac = codCamPerGrac;
		}
		/**
		 * @return the indicadorFeriado
		 */
		public String getIndicadorFeriado() {
			return indicadorFeriado;
		}
		/**
		 * @param indicadorFeriado the indicadorFeriado to set
		 */
		public void setIndicadorFeriado(String indicadorFeriado) {
			this.indicadorFeriado = indicadorFeriado;
		}
		/**
		 * @return the indicadorDespachoCanasta
		 */
		public String getIndicadorDespachoCanasta() {
			return indicadorDespachoCanasta;
		}
		/**
		 * @param indicadorDespachoCanasta the indicadorDespachoCanasta to set
		 */
		public void setIndicadorDespachoCanasta(String indicadorDespachoCanasta) {
			this.indicadorDespachoCanasta = indicadorDespachoCanasta;
		}
		/**
		 * @return the indicadorConsiderarNuevas
		 */
		public String getIndicadorConsiderarNuevas() {
			return indicadorConsiderarNuevas;
		}
		/**
		 * @param indicadorConsiderarNuevas the indicadorConsiderarNuevas to set
		 */
		public void setIndicadorConsiderarNuevas(String indicadorConsiderarNuevas) {
			this.indicadorConsiderarNuevas = indicadorConsiderarNuevas;
		}
		/**
		 * @return the indicadorGanaciaDePedido
		 */
		public String getIndicadorGanaciaDePedido() {
			return indicadorGanaciaDePedido;
		}
		/**
		 * @param indicadorGanaciaDePedido the indicadorGanaciaDePedido to set
		 */
		public void setIndicadorGanaciaDePedido(String indicadorGanaciaDePedido) {
			this.indicadorGanaciaDePedido = indicadorGanaciaDePedido;
		}
		
		public String getIndicadorConsiderarMetasIngresos() {
			return indicadorConsiderarMetasIngresos;
		}
		
		public void setIndicadorConsiderarMetasIngresos(
				String indicadorConsiderarMetasIngresos) {
			this.indicadorConsiderarMetasIngresos = indicadorConsiderarMetasIngresos;
		}
		
		public String getIndicadorconsiderarCapitalizacion() {
			return indicadorconsiderarCapitalizacion;
		}
		
		public void setIndicadorconsiderarCapitalizacion(
				String indicadorconsiderarCapitalizacion) {
			this.indicadorconsiderarCapitalizacion = indicadorconsiderarCapitalizacion;
		}		
		
		public String getIndicadorExitoNuevas() {
			return indicadorExitoNuevas;
		}
		
		public void setIndicadorExitoNuevas(String indicadorExitoNuevas) {
			this.indicadorExitoNuevas = indicadorExitoNuevas;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return "ProgramaCorporativo [codigoPais=" + codigoPais
					+ ", codigoPrograma=" + codigoPrograma
					+ ", descripcionPrograma=" + descripcionPrograma
					+ ", periodoInicio=" + periodoInicio + ", periodoFin="
					+ periodoFin + ", indicadorActivoRangoNivel="
					+ indicadorActivoRangoNivel
					+ ", indicadorActivoRangoRetencion="
					+ indicadorActivoRangoRetencion
					+ ", indicadorActivoTramos=" + indicadorActivoTramos
					+ ", indicadorActivoPremios=" + indicadorActivoPremios
					+ ", numeroMinimoActSec=" + numeroMinimoActSec
					+ ", numeroMinimoIngresos=" + numeroMinimoIngresos
					+ ", porcentajeActividad=" + porcentajeActividad
					+ ", alcanceRecomendaciones=" + alcanceRecomendaciones
					+ ", montoDsctoToleranciaPedidos="
					+ montoDsctoToleranciaPedidos + ", indExigenciaPedidoWeb="
					+ indExigenciaPedidoWeb + ", porcentajePenalidad="
					+ porcentajePenalidad + ", indGananciaLiderNueva="
					+ indGananciaLiderNueva + ", numeroCampanasGracia="
					+ numeroCampanasGracia + ", numeroCampanasCambiarNivel="
					+ numeroCampanasCambiarNivel
					+ ", numeroCampanasMantNivelGananciaPlus="
					+ numeroCampanasMantNivelGananciaPlus + ", tabSeleccion="
					+ tabSeleccion + ", campanyaPremioBuscar="
					+ campanyaPremioBuscar + ", programaRecono="
					+ programaRecono + ", pedidoWeb=" + pedidoWeb
					+ ", nroMinActSec=" + nroMinActSec + ", actCobranza="
					+ actCobranza + ", exigCursoCamb=" + exigCursoCamb
					+ ", exigPedPersona=" + exigPedPersona + ", codTipoComi="
					+ codTipoComi + ", insPortalFFVV=" + insPortalFFVV
					+ ", insSistCome=" + insSistCome + ", insPosiLider="
					+ insPosiLider + ", nivNroCampMante=" + nivNroCampMante
					+ ", nivNroCampTole=" + nivNroCampTole + ", fechaFeriado="
					+ fechaFeriado + ", codigoAlcance=" + codigoAlcance
					+ ", codigoRegion=" + codigoRegion + ", codigoZona="
					+ codigoZona + ", tipoMedicionCob=" + tipoMedicionCob
					+ ", numCampMediDese=" + numCampMediDese + ", numBajaAuto="
					+ numBajaAuto + ", numCampEval=" + numCampEval
					+ ", codCamPerGrac=" + codCamPerGrac
					+ ", indicadorFeriado=" + indicadorFeriado
					+ ", indicadorDespachoCanasta=" + indicadorDespachoCanasta
					+ ", indicadorConsiderarNuevas="
					+ indicadorConsiderarNuevas + ", indicadorGanaciaDePedido="
					+ indicadorGanaciaDePedido
					+ ", indicadorConsiderarMetasIngresos="
					+ indicadorConsiderarMetasIngresos
					+ ", indicadorconsiderarCapitalizacion="
					+ indicadorconsiderarCapitalizacion
					+ ", indicadorExitoNuevas=" + indicadorExitoNuevas + "]";
		}
		

}