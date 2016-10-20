package biz.belcorp.ssicc.reportes.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

@Service("reportes.reporteMAVAtencionFechaCampanhaPorTipoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ReporteMAVAtencionFechaCampanhaPorTipoServiceImpl extends
		BaseReporteInterfaceImpl {

	@Resource(name = "scsicc.reporteService")
	private ReporteService reporteService;

	private String codigoTipoReporte;
	private String tipoReporte;
	private String codigoRadio;
	private String codigoTipoMav;
	private String codigoProducto;
	private String flagRangoFechas;
	private String codigoTipoCargo;
	private String indicadorTipoCargo;
	private String fechaInicio;
	private String fechaFin;
	private String codigoIndicadorEnvio;

	
	@Override
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams,
			BaseReporteForm formReporte) throws Exception {

		this.tipoReporte = "";
		this.codigoTipoReporte = "";
		this.codigoRadio = "";
		this.codigoTipoMav = "";
		this.flagRangoFechas = "";
		this.codigoTipoCargo = "";
		this.indicadorTipoCargo = "";

		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteMAVAtencionFechaCampanhaPorTipoServiceImpl.beforeExecuteReporte' method");
		}
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");

		this.tipoReporte = (String) params.get("formatoExportacion");
		this.codigoTipoReporte = (String) params.get("codigoTipoReporte");
		this.codigoRadio = (String) params.get("codigoRadio");
		this.codigoTipoMav = (String) params.get("codigoTipoMav");
		this.flagRangoFechas = (String) params.get("flagRangoFechas");
		this.codigoTipoCargo = (String) params.get("codigoTipoCargo");
		this.codigoProducto = (String) params.get("codigoProducto");
		this.fechaInicio = (String) params.get("fechaInicio");
		this.fechaFin = (String) params.get("fechaFin");
		this.indicadorTipoCargo = (String) params.get("indicadorTipoCargo");
		this.codigoIndicadorEnvio = (String) params.get("codigoIndicadorEnvio");

		log.debug("Los parametros del Reporte en el before son: "
				+ params.toString());
		this.reporteService.executeReporteMAVAtencionCampanha(params);
		return reporteParams;
	}

	public String generarNombreArchivoReporteOracle(Map parameterMap) {
		String nombreArchivo = null;
		if ("OCSV".equals(tipoReporte)) {
			if (StringUtils.equals(this.codigoTipoReporte,
					Constants.NUMERO_CERO)) {
				// Reporte de Atencion
				nombreArchivo = "reporteMAVAtencionFechaCampanha";
			} else if (StringUtils.equals(codigoTipoReporte,
					Constants.NUMERO_UNO)) {
				// Reporte de configuracion
				nombreArchivo = "reporteMAVConfiguracionFechaCampanha";
			} else {
				// Reporte de Envios
				nombreArchivo = "reporteMAVEnviosFechaCampanha";
			}
		}

		return nombreArchivo;
	}

	/**
	 * @param parameterMap
	 * @return
	 */
	public Map generarReporteOracle(Map parameterMap) {

		if ("OCSV".equals(this.tipoReporte)) {

			if (StringUtils.equals(codigoTipoReporte, Constants.NUMERO_CERO)) {
				if (StringUtils.equals(codigoRadio, Constants.NUMERO_CERO)) {
					if (StringUtils.equals(codigoTipoMav, Constants.NUMERO_DOS)) {
						if (StringUtils.equals(flagRangoFechas, "on")) {
							// Resumen - Consultora - Con Rango fechas
							if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GR)
									|| StringUtils.equals(codigoTipoCargo,
											Constants.MAV_CODIGO_TIPO_CARGO_EE)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
								// Agrupar por regiones
								return reporteService
										.generarReporteMAVAtenFechaCampPorConsultoraConFechaRegionCSV(parameterMap);// "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegionXLS";
							} else if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GZ)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
								// Agrupar por regiones y zonas
								return reporteService
										.generarReporteMAVAtenFechaCampPorConsultoraConFechaRegionZonaCSV(parameterMap); // "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegionZonaXLS";
							} else {
								return reporteService
										.generarReporteMAVAtenFechaCampPorConsultoraConFechaCSV(parameterMap);// "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaXLS";
							}
						} else {
							// Resumen - Consultora - Sin Rango fechas
							if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GR)
									|| StringUtils.equals(codigoTipoCargo,
											Constants.MAV_CODIGO_TIPO_CARGO_EE)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
								// Agrupar por regiones
								return reporteService
										.generarReporteMAVAtenFechaCampPorConsultoraSinFechaRegionCSV(parameterMap);// "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegionXLS";
							} else if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GZ)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
								// Agrupar por regiones y zonas
								return reporteService
										.generarReporteMAVAtenFechaCampPorConsultoraSinFechaRegionZonaCSV(parameterMap);// "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegionZonaXLS";
							} else {
								return reporteService
										.generarReporteMAVAtenFechaCampPorConsultoraSinFechaCSV(parameterMap);// "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaXLS";
							}
						}
					} else {
						if (StringUtils.equals(flagRangoFechas, "on")) {
							// Resumen - Gerente - Con Rango fechas
							if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GR)
									|| StringUtils.equals(codigoTipoCargo,
											Constants.MAV_CODIGO_TIPO_CARGO_EE)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
								// Agrupar por regiones
								return reporteService
										.generarReporteMAVAtenFechaCampPorGerenteConFechaRegionCSV(parameterMap);// "reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegionXLS";
							} else if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GZ)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
								// Agrupar por regiones y zonas
								return reporteService
										.generarReporteMAVAtenFechaCampPorGerenteConFechaRegionZonaCSV(parameterMap); // "reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegionZonaXLS";
							} else {
								return reporteService
										.generarReporteMAVAtenFechaCampPorGerenteConFechaCSV(parameterMap);// "reporteMAVAtencionFechaCampanhaPorGerenteConFechaXLS";
							}
						} else {
							// Resumen - Gerente - Sin Rango fechas
							if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GR)
									|| StringUtils.equals(codigoTipoCargo,
											Constants.MAV_CODIGO_TIPO_CARGO_EE)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
								// Agrupar por regiones
								return reporteService
										.generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionCSV(parameterMap);// "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegionXLS";
							} else if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GZ)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
								// Agrupar por regiones y zonas
								return reporteService
										.generarReporteMAVAtenFechaCampPorGerenteSinFechaRegionZonaCSV(parameterMap);// "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegionZonaXLS";
							} else {
								return reporteService
										.generarReporteMAVAtenFechaCampPorGerenteSinFechaCSV(parameterMap);// "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaXLS";
							}
						}
					}
				} else {
					if (StringUtils.equals(codigoTipoMav, Constants.NUMERO_DOS)) {
						// Detalle - Consultora - Con/Sin Rango fechas
						return reporteService
								.generarReporteMAVAtenFechaCampPorConsultoraDetalleConSinFechaCSV(parameterMap);// "reporteMAVAtencionFechaCampanhaPorConsultoraDetalleConSinFechaXLS";
					} else {
						// Detalle - Gerente - Con/Sin Rango fechas
						if (StringUtils.isNotBlank(codigoTipoCargo)) {
							return reporteService
									.generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaTipoCargoCSV(parameterMap); // "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaTipoCargoXLS";
						} else {
							if (StringUtils.equals(indicadorTipoCargo,
									Constants.SI))
								return reporteService
										.generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaTipoCargoCSV(parameterMap);// "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaTipoCargoXLS";
							else
								return reporteService
										.generarReporteMAVAtenFechaCampPorGerenteDetalleConSinFechaCSV(parameterMap);// "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaXLS";
						}
					}
				}
			} else if (StringUtils.equals(codigoTipoReporte,
					Constants.NUMERO_UNO)) {
				// Reporte de configuracion
				return null;// "reporteMAVConfiguracionFechaCampanhaConSinFechaXLS";
			} else {
				// Reporte de Envios
				return reporteService
						.generarReporteMAVEnviosFechaCampConSinFechaCSV(parameterMap);// "reporteMAVEnviosFechaCampanhaConSinFechaXLS";
			}

		}
		return null;
	}

	public String obtieneKeyTituloReporteOracle(Map parameterMap) {
		String formatoExportacion = (String) parameterMap
				.get("formatoExportacion");
		if ("OCSV".equals(formatoExportacion)) {

			if (StringUtils.equals(codigoTipoReporte, Constants.NUMERO_CERO)) {
				if (StringUtils.equals(codigoRadio, Constants.NUMERO_CERO)) {
					if (StringUtils.equals(codigoTipoMav, Constants.NUMERO_DOS)) {
						if (StringUtils.equals(flagRangoFechas, "on")) {
							// Resumen - Consultora - Con Rango fechas
							if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GR)
									|| StringUtils.equals(codigoTipoCargo,
											Constants.MAV_CODIGO_TIPO_CARGO_EE)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
								// Agrupar por regiones
								return "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegionForm.tituloReporteCSV";// "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegionXLS";
							} else if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GZ)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
								// Agrupar por regiones y zonas
								return "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegionZonaForm.tituloReporteCSV"; // "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaRegionZonaXLS";
							} else {
								return "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaForm.tituloReporteCSV";// "reporteMAVAtencionFechaCampanhaPorConsultoraConFechaXLS";
							}
						} else {
							// Resumen - Consultora - Sin Rango fechas
							if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GR)
									|| StringUtils.equals(codigoTipoCargo,
											Constants.MAV_CODIGO_TIPO_CARGO_EE)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
								// Agrupar por regiones
								return "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegionForm.tituloReporteCSV";// "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegionXLS";
							} else if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GZ)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
								// Agrupar por regiones y zonas
								return "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegionZonaForm.tituloReporteCSV";// "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaRegionZonaXLS";
							} else {
								return "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaForm.tituloReporteCSV";// "reporteMAVAtencionFechaCampanhaPorConsultoraSinFechaXLS";
							}
						}
					} else {
						if (StringUtils.equals(flagRangoFechas, "on")) {
							// Resumen - Gerente - Con Rango fechas
							if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GR)
									|| StringUtils.equals(codigoTipoCargo,
											Constants.MAV_CODIGO_TIPO_CARGO_EE)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
								// Agrupar por regiones
								return "reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegionForm.tituloReporteCSV";// "reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegionXLS";
							} else if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GZ)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
								// Agrupar por regiones y zonas
								return "reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegionZonaForm.tituloReporteCSV"; // "reporteMAVAtencionFechaCampanhaPorGerenteConFechaRegionZonaXLS";
							} else {
								return "reporteMAVAtencionFechaCampanhaPorGerenteConFechaForm.tituloReporteCSV";
							}
						} else {
							// Resumen - Gerente - Sin Rango fechas
							if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GR)
									|| StringUtils.equals(codigoTipoCargo,
											Constants.MAV_CODIGO_TIPO_CARGO_EE)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVR)) {
								// Agrupar por regiones
								return "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegionForm.tituloReporteCSV";// "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegionXLS";
							} else if (StringUtils.equals(codigoTipoCargo,
									Constants.MAV_CODIGO_TIPO_CARGO_GZ)
									|| StringUtils
											.equals(codigoTipoCargo,
													Constants.MAV_CODIGO_TIPO_CARGO_MVZ)) {
								// Agrupar por regiones y zonas
								return "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegionZonaForm.tituloReporteCSV";// "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaRegionZonaXLS";
							} else {
								return "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaForm.tituloReporteCSV";// "reporteMAVAtencionFechaCampanhaPorGerenteSinFechaXLS";
							}
						}
					}
				} else {
					if (StringUtils.equals(codigoTipoMav, Constants.NUMERO_DOS)) {
						// Detalle - Consultora - Con/Sin Rango fechas
						return "reporteMAVAtencionFechaCampanhaPorConsultoraDetalleConSinFechaForm.tituloReporteCSV";// "reporteMAVAtencionFechaCampanhaPorConsultoraDetalleConSinFechaXLS";
					} else {
						// Detalle - Gerente - Con/Sin Rango fechas
						if (StringUtils.isNotBlank(codigoTipoCargo)) {
							return "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaTipoCargoForm.tituloReporteCSV"; // "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaTipoCargoXLS";
						} else {
							if (StringUtils.equals(indicadorTipoCargo,
									Constants.SI))
								return "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaTipoCargoForm.tituloReporteCSV";// "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaTipoCargoXLS";
							else
								return "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaForm.tituloReporteCSV";// "reporteMAVAtencionFechaCampanhaPorGerenteDetalleConSinFechaXLS";
						}
					}
				}
			} else if (StringUtils.equals(codigoTipoReporte,
					Constants.NUMERO_UNO)) {
				// Reporte de configuracion
				return null;// "reporteMAVConfiguracionFechaCampanhaConSinFechaXLS";
			} else {
				// Reporte de Envios
				return "reporteMAVEnviosFechaCampanhaConSinFechaForm.tituloReporteCSV";// "reporteMAVEnviosFechaCampanhaConSinFechaXLS";
			}

		}
		return null;// "reporteMaestroHorizontalConfiguracionMAV";
	}

	/**
	 * @return the reporteService
	 */
	public ReporteService getReporteService() {
		return reporteService;
	}

	/**
	 * @param reporteService the reporteService to set
	 */
	public void setReporteService(ReporteService reporteService) {
		this.reporteService = reporteService;
	}

	/**
	 * @return the codigoTipoReporte
	 */
	public String getCodigoTipoReporte() {
		return codigoTipoReporte;
	}

	/**
	 * @param codigoTipoReporte the codigoTipoReporte to set
	 */
	public void setCodigoTipoReporte(String codigoTipoReporte) {
		this.codigoTipoReporte = codigoTipoReporte;
	}

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the codigoRadio
	 */
	public String getCodigoRadio() {
		return codigoRadio;
	}

	/**
	 * @param codigoRadio the codigoRadio to set
	 */
	public void setCodigoRadio(String codigoRadio) {
		this.codigoRadio = codigoRadio;
	}

	/**
	 * @return the codigoTipoMav
	 */
	public String getCodigoTipoMav() {
		return codigoTipoMav;
	}

	/**
	 * @param codigoTipoMav the codigoTipoMav to set
	 */
	public void setCodigoTipoMav(String codigoTipoMav) {
		this.codigoTipoMav = codigoTipoMav;
	}

	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the flagRangoFechas
	 */
	public String getFlagRangoFechas() {
		return flagRangoFechas;
	}

	/**
	 * @param flagRangoFechas the flagRangoFechas to set
	 */
	public void setFlagRangoFechas(String flagRangoFechas) {
		this.flagRangoFechas = flagRangoFechas;
	}

	/**
	 * @return the codigoTipoCargo
	 */
	public String getCodigoTipoCargo() {
		return codigoTipoCargo;
	}

	/**
	 * @param codigoTipoCargo the codigoTipoCargo to set
	 */
	public void setCodigoTipoCargo(String codigoTipoCargo) {
		this.codigoTipoCargo = codigoTipoCargo;
	}

	/**
	 * @return the indicadorTipoCargo
	 */
	public String getIndicadorTipoCargo() {
		return indicadorTipoCargo;
	}

	/**
	 * @param indicadorTipoCargo the indicadorTipoCargo to set
	 */
	public void setIndicadorTipoCargo(String indicadorTipoCargo) {
		this.indicadorTipoCargo = indicadorTipoCargo;
	}

	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the codigoIndicadorEnvio
	 */
	public String getCodigoIndicadorEnvio() {
		return codigoIndicadorEnvio;
	}

	/**
	 * @param codigoIndicadorEnvio the codigoIndicadorEnvio to set
	 */
	public void setCodigoIndicadorEnvio(String codigoIndicadorEnvio) {
		this.codigoIndicadorEnvio = codigoIndicadorEnvio;
	}

	
}
