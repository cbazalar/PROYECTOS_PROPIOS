package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.cronograma.MantenimientoCRAGrupoZonaService;
import biz.belcorp.ssicc.service.spusicc.mae.MantenimientoMAEClienteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCRATotalGrupoForm;

@ManagedBean
@SessionScoped
public class ReporteCRATotalGrupoAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8128898934380399793L;
	private String formatoReporte;
	private List siccGrupoFacturacionList;
	private List siccActividadList;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCRATotalGrupoForm reporteForm = new ReporteCRATotalGrupoForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteCRATotalGrupoAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteCRATotalGrupoForm f = (ReporteCRATotalGrupoForm) this.formReporte;

		// obteniendo las lista de grupode facturacion y actividad
		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		Long oidMarca = clienteService
				.getOidMarca(Constants.CODIGO_MARCA_DEFAULT);
		Long oidCanal = clienteService
				.getOidCanal(Constants.CODIGO_CANAL_DEFAULT);

		Map params = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		params.put("codigoPais", pais.getCodigo());
		params.put(
				"oidPais",
				new Long(reporteService.getOidString("getOidPaisByCodigoPais",
						params)));
		params.put("oidMarca", oidMarca);
		params.put("oidCanal", oidCanal);

		this.siccGrupoFacturacionList = reporteService
				.getGrupoFacturacion(params);
		this.siccActividadList = reporteService.getActividad(params);

		log.debug("Todo Ok: Redireccionando");

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
//		if ("XLS".equals(formatoReporte))
			return "reporteMaestroHorizontal";
//		else
//			return " ";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		
//		if ("PDF".equals(formatoReporte))
			return "reporteCRATotalGrupoZonaPDF";
//		else
//			return " ";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}

		MantenimientoMAEClienteService clienteService = (MantenimientoMAEClienteService) getBean("spusicc.mantenimientoMAEClienteService");
		MantenimientoCRAGrupoZonaService serviceGrupo = (MantenimientoCRAGrupoZonaService)getBean("spusicc.mantenimientoCRAGrupoZonaService");				
		
		ReporteCRATotalGrupoForm reporteCRAForm = (ReporteCRATotalGrupoForm) this.formReporte;
		formatoReporte = reporteCRAForm.getFormatoExportacion();
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		
		params.put("oidPais", new Long(reporteService.getOidString("getOidPaisByCodigoPais", params)));
        //OBTENIENDO LOS OID D ELOS PERIODOS
		Map criteria = new HashMap();
		criteria.put("codigoPais", reporteCRAForm.getCodigoPais());
		criteria.put("oidGrupo", reporteCRAForm.getGrupoFacturacion());
		
		List list = new ArrayList();
		list = serviceGrupo.getGrupos(criteria);
		Map datos = (Map) list.get(0);
		String desGrupo = "";
		
		if (list.size()>0)								
			desGrupo = (String)datos.get("descripcion");	
		
		params.put("anhio", new Long(reporteCRAForm.getAnio()));

		params.put("NroReporte", " ");
		params.put("titulo",getResourceMessage("reporteCRATotalGrupoForm.titulo")+"\n"+
				reporteCRAForm.getAnio()+" - "+desGrupo);
		
		String condicionActividad = obtieneCondicion(reporteCRAForm.getActividad(),"ca.oid_acti", "'");		
		params.put("condicionActividad",condicionActividad );						 		
		
		//grupo facturacion
		params.put("oidGrupoZona", new Long(reporteCRAForm.getGrupoFacturacion()));
		
		//OBTENIENDO LAS ZONAS QUE COMPONEN EL GRUPO
		/*List listZonas=reporteService.getZonasGrupo(reporteCRAForm.getGrupoFacturacion());
		Iterator it = listZonas.iterator();
		String zonaGrupo="";
		while(it.hasNext()){
			String zona=(String)it.next();
			zonaGrupo+=zona+",";
		}
		//
		//log.debug(" zonaGrupo "+zonaGrupo);
		params.put("zonaGrupo",zonaGrupo.substring(0, zonaGrupo.length()-1) );*/
	
		return params;
	}

	/**
	 * @return
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccGrupoFacturacionList
	 */
	public List getSiccGrupoFacturacionList() {
		return siccGrupoFacturacionList;
	}

	/**
	 * @param siccGrupoFacturacionList the siccGrupoFacturacionList to set
	 */
	public void setSiccGrupoFacturacionList(List siccGrupoFacturacionList) {
		this.siccGrupoFacturacionList = siccGrupoFacturacionList;
	}

	/**
	 * @return the siccActividadList
	 */
	public List getSiccActividadList() {
		return siccActividadList;
	}

	/**
	 * @param siccActividadList the siccActividadList to set
	 */
	public void setSiccActividadList(List siccActividadList) {
		this.siccActividadList = siccActividadList;
	}
	
	

}
