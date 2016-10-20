package biz.belcorp.ssicc.web.spncd.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spncd.MantenimientoCUPProgDsctoService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spncd.form.ProcesoCUPGenerarCCCForm;

@ManagedBean
@SessionScoped
public class ProcesoCUPGenerarCCCAction extends BaseProcesoAbstractAction {
	
	private static final long serialVersionUID = 4719206813800027888L;
	private List cupProgramasList; 

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setViewAttributes' method");
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
        criteria.put("estadoCampanha",Constants.NUMERO_CERO); // Indica Campanha Activa 
        criteria.put("indicadorActiva",Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente  
        
        ProcesoCUPGenerarCCCForm generarCCCForm = (ProcesoCUPGenerarCCCForm)this.formProceso ; 
        MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        PedidoControlFacturacion controlFacturacion = service.getControlFacturacionById(criteria);
		
		// Carga Fecha y Periodo
        generarCCCForm.setFechaFact(controlFacturacion.getFechaProceso());
        generarCCCForm.setPeriodo(controlFacturacion.getCodigoPeriodo());
        generarCCCForm.setFechaFactD(DateUtil.convertStringToDate(generarCCCForm.getFechaFact()));
        
        MantenimientoCUPProgDsctoService dsctoService = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
		Map map = new HashMap();
		map.put("codigoPais", pais.getCodigo());
		cupProgramasList = dsctoService.getProgramasDescuentosbyPais(map);
        
		/* INI JJ PER-SiCC-2012-0167 */
		/*
		Pais pais = getPais(session);
		
		List lstProcesos = new ArrayList();
		Map mapProceso1 = new HashMap();
		mapProceso1.put("proceso", "1. CIERRE DE FACTURACION");
		Map mapProceso2 = new HashMap();
		mapProceso2.put("proceso", "2. GENERAR CTA. CTE CUPONES");
		Map mapProceso3 = new HashMap();
		mapProceso3.put("proceso", "3. EVALUA ESTATUS DE CONSULTORAS");
		
		lstProcesos.add(mapProceso1);
		lstProcesos.add(mapProceso2);
		lstProcesos.add(mapProceso3);
		
		request.getSession().setAttribute(Constants.SICC_PROCESO_LIST, lstProcesos);
		*/
		/* FIN JJ PER-SiCC-2012-0167 */
//		String codigoProceso = request.getParameter("codigoProcesoBatch");
//		String codigoSistema = request.getParameter("codigoSistema");
//		session.setAttribute("codigoProcesoBatch", codigoProceso);	
//		session.setAttribute("codigoSistema", codigoSistema);		
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		log.debug("Los parametros del Proceso en el executeProcess son: "
				+ params.toString());
		ProcesoCUPGenerarCCCForm form = (ProcesoCUPGenerarCCCForm) this.formProceso;
		form.setFechaFact(DateUtil.convertDateToString(form.getFechaFactD()));
		
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoOCRPedidoControlFacturacionService controlFacturacionService = (MantenimientoOCRPedidoControlFacturacionService)getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");        
        //Map parametros = BeanUtils.describe(params);
        controlFacturacionService.executeProcesoOCRActualizaGP2(params);
        /* INI JJ PER-SiCC-2012-0167 */
        /*
        MantenimientoCUPProgDsctoService dsctoService = (MantenimientoCUPProgDsctoService) getBean("spncd.mantenimientoCUPProgDsctoService");
        parametros.put("codigoUsuario", usuario.getLogin());
        dsctoService.executeProcesoCUPCierreFacturacion(parametros);

        MantenimientoSSEProductoPeriodoService productoPeriodoService = (MantenimientoSSEProductoPeriodoService)getBean("spusicc.sessionexperte.mantenimientoSSEProductoPeriodoService");        
        Map paramsSesion = BeanUtils.describe(form);
        paramsSesion.put("usuario", usuario.getLogin());
        productoPeriodoService.executeProcesoSSEProcesaCierreConsultoraSessionExperte(paramsSesion);
		*/
        /* FIN JJ PER-SiCC-2012-0167 */
        //Calificacion Estatus por Facturaci�n Diaria
       /* ProcesoMAECalificacionEstatusService service = (ProcesoMAECalificacionEstatusService)getBean("spusicc.procesoMAECalificacionEstatusService");
        Map paramsEstatus = BeanUtils.describe(form);
        paramsEstatus.put("codigoPeriodo", paramsEstatus.get("periodo"));
        paramsEstatus.put("fechaFacturacion", paramsEstatus.get("fechaFact"));
        
        service.executeCalificacionEstatusFacturacionDiaria(paramsEstatus);
        
        //Env�o de correo con reporte adjunto
        ReporteExecutionService reporteExecutionService = (ReporteExecutionService)getBean("scsicc.reporteExecutionService");
        ReporteParams reportParams = new ReporteParams();
        reportParams.setJasperFileName("reporteSACIndicadoresFNA.jasper");
        Map queryParams = new HashMap();
        queryParams.put("codigoPeriodo",paramsEstatus.get("periodo"));
        queryParams.put("fechaHasta",paramsEstatus.get("fechaFact"));
        reportParams.setQueryParams(queryParams);
        ReporteResult reporteResult = reporteExecutionService.executeReporte(reportParams);
        
		String outfile = new String("");// archivo de salida
		outfile += "reporteGenerico.xls";
		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, reporteResult.getJasperPrint());
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outfile );
		exporter.exportReport();
        
		paramsEstatus.put("fileAttachment", new File(outfile));		
        service.enviarCorreoAdjunto(paramsEstatus);		
		*/
		return params;
	}

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoCUPGenerarCCCForm form = new ProcesoCUPGenerarCCCForm();
		return form;
	}

	/**
	 * @return the cupProgramasList
	 */
	public List getCupProgramasList() {
		return cupProgramasList;
	}

	/**
	 * @param cupProgramasList the cupProgramasList to set
	 */
	public void setCupProgramasList(List cupProgramasList) {
		this.cupProgramasList = cupProgramasList;
	}
}
