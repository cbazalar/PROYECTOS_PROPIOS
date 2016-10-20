package biz.belcorp.ssicc.web.spusicc.let.form;

import java.io.Serializable;
import java.util.Arrays;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoLETProgramaCorporativoForm extends BaseEditForm{
	
		private static final long serialVersionUID = -4212609463599289256L;
		
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
		
		//Sección
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
		
		private String tabSeleccion;
		
		private String campanyaPremioBuscar;
		
		protected String[] selectedItemsRangoNivel = {};
		protected String selectedItemRangoNivel = null;
		
		protected String[] selectedItemsRangoRetencion = {};
		protected String selectedItemRangoRetencion = null;
		
		protected String[] selectedItemsTramos = {};
		protected String selectedItemTramos = null;
		
		protected String[] selectedItemsPremios = {};
		protected String selectedItemPremios = null;
		
		
		
		public String getCodigoPais() {
			return codigoPais;
		}
		/**
		 * @param codigoPais the codigoPais to set
		 * @struts.validator type = "required"
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
		 * @struts.validator type = "required"
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
		 * @struts.validator type = "required"
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
		 * @struts.validator type = "required"
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
		 * @struts.validator type = "required"
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
		 * @struts.validator type = "required"
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
		 * @struts.validator type = "required"
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
		 * @struts.validator type = "required"
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
		public void setNumeroCampanasMantNivelGananciaPlus(String numeroCampanasMantNivelGananciaPlus) {
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
		 * @return the selectedItemsRangoNivel
		 */
		public String[] getSelectedItemsRangoNivel() {
			return selectedItemsRangoNivel;
		}

		/**
		 * @param selectedItemsRangoNivel the selectedItemsRangoNivel to set
		 */
		public void setSelectedItemsRangoNivel(String[] selectedItemsRangoNivel) {
			this.selectedItemsRangoNivel = selectedItemsRangoNivel;
		}

		/**
		 * @return the selectedItemRangoNivel
		 */
		public String getSelectedItemRangoNivel() {
			return selectedItemRangoNivel;
		}

		/**
		 * @param selectedItemRangoNivel the selectedItemRangoNivel to set
		 */
		public void setSelectedItemRangoNivel(String selectedItemRangoNivel) {
			this.selectedItemRangoNivel = selectedItemRangoNivel;
		}

		/**
		 * @return the selectedItemsRangoRetencion
		 */
		public String[] getSelectedItemsRangoRetencion() {
			return selectedItemsRangoRetencion;
		}

		/**
		 * @param selectedItemsRangoRetencion the selectedItemsRangoRetencion to set
		 */
		public void setSelectedItemsRangoRetencion(String[] selectedItemsRangoRetencion) {
			this.selectedItemsRangoRetencion = selectedItemsRangoRetencion;
		}

		/**
		 * @return the selectedItemRangoRetencion
		 */
		public String getSelectedItemRangoRetencion() {
			return selectedItemRangoRetencion;
		}

		/**
		 * @param selectedItemRangoRetencion the selectedItemRangoRetencion to set
		 */
		public void setSelectedItemRangoRetencion(String selectedItemRangoRetencion) {
			this.selectedItemRangoRetencion = selectedItemRangoRetencion;
		}

		/**
		 * @return the selectedItemsTramos
		 */
		public String[] getSelectedItemsTramos() {
			return selectedItemsTramos;
		}

		/**
		 * @param selectedItemsTramos the selectedItemsTramos to set
		 */
		public void setSelectedItemsTramos(String[] selectedItemsTramos) {
			this.selectedItemsTramos = selectedItemsTramos;
		}

		/**
		 * @return the selectedItemTramos
		 */
		public String getSelectedItemTramos() {
			return selectedItemTramos;
		}

		/**
		 * @param selectedItemTramos the selectedItemTramos to set
		 */
		public void setSelectedItemTramos(String selectedItemTramos) {
			this.selectedItemTramos = selectedItemTramos;
		}

		/**
		 * @return the selectedItemsPremios
		 */
		public String[] getSelectedItemsPremios() {
			return selectedItemsPremios;
		}

		/**
		 * @param selectedItemsPremios the selectedItemsPremios to set
		 */
		public void setSelectedItemsPremios(String[] selectedItemsPremios) {
			this.selectedItemsPremios = selectedItemsPremios;
		}

		/**
		 * @return the selectedItemPremios
		 */
		public String getSelectedItemPremios() {
			return selectedItemPremios;
		}

		/**
		 * @param selectedItemPremios the selectedItemPremios to set
		 */
		public void setSelectedItemPremios(String selectedItemPremios) {
			this.selectedItemPremios = selectedItemPremios;
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

		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return "MantenimientoLETProgramaCorporativoForm [codigoPais="
					+ codigoPais + ", codigoPrograma=" + codigoPrograma
					+ ", descripcionPrograma=" + descripcionPrograma
					+ ", periodoInicio=" + periodoInicio + ", periodoFin="
					+ periodoFin + ", indicadorActivoRangoNivel="
					+ indicadorActivoRangoNivel
					+ ", indicadorActivoRangoRetencion="
					+ indicadorActivoRangoRetencion + ", indicadorActivoTramos="
					+ indicadorActivoTramos + ", indicadorActivoPremios="
					+ indicadorActivoPremios + ", numeroMinimoActSec="
					+ numeroMinimoActSec + ", numeroMinimoIngresos="
					+ numeroMinimoIngresos + ", porcentajeActividad="
					+ porcentajeActividad + ", alcanceRecomendaciones="
					+ alcanceRecomendaciones + ", numeroCampanasMantNivelExito="
					+ numeroCampanasMantNivelExito + ", evaluacionNivelExito="
					+ evaluacionNivelExito + ", numeroCampanasBajaAuto="
					+ numeroCampanasBajaAuto + ", montoDsctoToleranciaPedidos="
					+ montoDsctoToleranciaPedidos + ", indExigenciaPedidoWeb="
					+ indExigenciaPedidoWeb + ", porcentajePenalidad="
					+ porcentajePenalidad + ", indGananciaLiderNueva="
					+ indGananciaLiderNueva + ", numeroCampanasGracia="
					+ numeroCampanasGracia + ", numeroCampanasCambiarNivel="
					+ numeroCampanasCambiarNivel
					+ ", numeroCampanasMantNivelGananciaPlus="
					+ numeroCampanasMantNivelGananciaPlus + ", tabSeleccion="
					+ tabSeleccion + ", campanyaPremioBuscar="
					+ campanyaPremioBuscar + ", selectedItemsRangoNivel="
					+ Arrays.toString(selectedItemsRangoNivel)
					+ ", selectedItemRangoNivel=" + selectedItemRangoNivel
					+ ", selectedItemsRangoRetencion="
					+ Arrays.toString(selectedItemsRangoRetencion)
					+ ", selectedItemRangoRetencion=" + selectedItemRangoRetencion
					+ ", selectedItemsTramos="
					+ Arrays.toString(selectedItemsTramos)
					+ ", selectedItemTramos=" + selectedItemTramos
					+ ", selectedItemsPremios="
					+ Arrays.toString(selectedItemsPremios)
					+ ", selectedItemPremios=" + selectedItemPremios + "]";
		}

}
