package biz.belcorp.ssicc.service.spusicc.mae.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.scsicc.ReporteDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECalificacionEstatusDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.sisicc.framework.BaseMailService;
import biz.belcorp.ssicc.service.spusicc.mae.ProcesoMAECalificacionEstatusService;

/**
 * Service que executa las metodos de Evaluacion de Estatus de Clientes
 * 
 * <p>
 * <a href="ProcesoMAECalificacionEstatusServiceImpl.java.html"> <i>View Source</i>
 * </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
@Service("spusicc.procesoMAECalificacionEstatusService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoMAECalificacionEstatusServiceImpl extends BaseService
		implements ProcesoMAECalificacionEstatusService {

	@Resource(name="spusicc.procesoMAECalificacionEstatusDAO")
	private ProcesoMAECalificacionEstatusDAO procesoMAECalificacionEstatusDAO;
	
	@Resource(name="scsicc.reporteDAO")
	private ReporteDAO reporteDAO;	
	
	@Resource(name="sisicc.mailUtil")
	private BaseMailService mailUtil;

	public boolean verificarCargaInicialEstatus(String codigoPais) {
		return procesoMAECalificacionEstatusDAO
				.verificarCargaInicialEstatus(codigoPais);
	}

	public void executeCalificacionEstatusCargaInicial(Map criteria) {
		procesoMAECalificacionEstatusDAO
				.executeCalificacionEstatusCargaInicial(criteria);
	}

	public void executeCalificacionEstatusFacturacionDiaria(Map criteria) throws Exception {
		String totalErrores="";
		Map paramReporte = null;
		
		try {
			//Ejecucion del proceso de Facturacion Diaria de Calificacion de Estatus
			procesoMAECalificacionEstatusDAO.executeCalificacionEstatusFacturacionDiaria(criteria);

			//recuperamos los parametros de estatus de cliente, en este caso de los correos a informar de la ejecucion del proceso
			criteria.put("nombreReporte","procesoMAECalificacionEstatusFacturacionDiaria");
			paramReporte =  reporteDAO.getParametrosReporte(criteria);

			if(paramReporte!=null){ 
				mailUtil.enviarMail((String)paramReporte.get("correoOrigen"), (String)paramReporte.get("correoCC"),
									(String)paramReporte.get("subject"), (String)paramReporte.get("bodyTxt"),
									criteria);
			}
			
		}catch(Exception ex) {
			String mensajeError = "ERROR (" + criteria.get("codigoPeriodo") + "," + criteria.get("fechaFacturacion") + ")";
			if(paramReporte != null)
				mailUtil.enviarMail((String)paramReporte.get("correoOrigen"), (String)paramReporte.get("correoCC"),
					(String)paramReporte.get("subject"), mensajeError);

			throw ex;
		}
	}

	public List getRegionesCerradas(Map criteria) {
		String fechaUltimoProceso = procesoMAECalificacionEstatusDAO.getUltimaFechaCierreRegion(criteria);
		
		if(fechaUltimoProceso != null)
			criteria.put("fechaUltimoProceso", fechaUltimoProceso);
		
		return procesoMAECalificacionEstatusDAO.getRegionesCerradas(criteria);
	}
	
	public void executeCalificacionEstatusCierreRegion(Map criteria) throws Exception {
		String totalErrores="";
		Map paramReporte = null;
		
		try {
			//Ejecucion del proceso de Cierre de Region de Calificacion de Estatus
			procesoMAECalificacionEstatusDAO.executeCalificacionEstatusCierreRegion(criteria);
			
			//recuperamos los parametros de estatus de cliente, en este caso de los correos a informar de la ejecucion del proceso
			criteria.put("nombreReporte","procesoMAECalificacionEstatusCierreRegion");
			paramReporte =  reporteDAO.getParametrosReporte(criteria);
			
			if(paramReporte!=null){ 
				mailUtil.enviarMail((String)paramReporte.get("correoOrigen"), (String)paramReporte.get("correoCC"),
									(String)paramReporte.get("subject"), (String)paramReporte.get("bodyTxt"),
									criteria);
			} 
			
		}catch(Exception ex) {
			String mensajeError = "ERROR (" + criteria.get("codigoPeriodo") + "," + criteria.get("regionesCerradas") + ")";
			if(paramReporte != null)
				mailUtil.enviarMail((String)paramReporte.get("correoOrigen"), (String)paramReporte.get("correosDestinos"),
					(String)paramReporte.get("subject"), mensajeError);

			throw ex;
		}
	}

	public void executeCalificacionEstatusCierreCampana(Map criteria) throws Exception {
		String totalErrores="";
		Map paramReporte = null;
		
		try {
			//Ejecucion del proceso de Cierre de Campaa de Calificacion de Estatus
			procesoMAECalificacionEstatusDAO.executeCalificacionEstatusCierreCampana(criteria);
			
			//recuperamos los parametros de estatus de cliente, en este caso de los correos a informar de la ejecucion del proceso
			criteria.put("nombreReporte","procesoMAECalificacionEstatusCierreCampana");
			paramReporte =  reporteDAO.getParametrosReporte(criteria);
			
			if(paramReporte!=null){ 
				mailUtil.enviarMail((String)paramReporte.get("correoOrigen"), (String)paramReporte.get("correoCC"),
									(String)paramReporte.get("subject"), (String)paramReporte.get("bodyTxt"),
									criteria);
			} 
			
		}catch(Exception ex) {
			String mensajeError = "ERROR (" + criteria.get("codigoPeriodo") + ")";
			if(paramReporte != null)
				mailUtil.enviarMail((String)paramReporte.get("correoOrigen"), (String)paramReporte.get("correoCC"),
					(String)paramReporte.get("subject"), mensajeError);

			throw ex;
		}
	}
	

	public List getTipoProcesoList() {
		 return procesoMAECalificacionEstatusDAO.getTipoProcesoList();
	}

	public List getClasificacionEstatusList(Map params) {
		 return procesoMAECalificacionEstatusDAO.getClasificacionEstatusList(params);
	}

	public void executeProcesoAtendidos(Map params) {
		procesoMAECalificacionEstatusDAO.executeProcesoAtendidos(params);
	}

	public void deleteClasificacionEstatusList(Map params) {
		procesoMAECalificacionEstatusDAO.deleteClasificacionEstatusList(params);
	}
	
	public void enviarCorreoAdjunto(Map criteria) throws Exception {
		String totalErrores="";
		Map paramReporte = null;
		
		try {
			//recuperamos los parametros de estatus de cliente, en este caso de los correos a informar de la ejecucion del proceso
			criteria.put("nombreReporte","reporteSACIndicadoresFNA");
			paramReporte =  reporteDAO.getParametrosReporte(criteria);

			if(paramReporte!=null){ 
				System.out.println("correoOrigen:"+paramReporte.get("correoOrigen"));
				System.out.println("correoDefault:"+paramReporte.get("correoDefault"));
				System.out.println("correoCC:"+paramReporte.get("correoCC"));
				System.out.println("subject:"+paramReporte.get("subject"));
				System.out.println("bodyTxt:"+paramReporte.get("bodyTxt"));
				System.out.println("bodyHtml:"+paramReporte.get("bodyHtml"));
				criteria.put("bodyHtml", paramReporte.get("bodyHtml"));
				mailUtil.enviarMailAdjunto((String)paramReporte.get("correoOrigen"), (String)paramReporte.get("correoDefault")+","+(String)paramReporte.get("correoCC"),
									(String)paramReporte.get("subject"), (String)paramReporte.get("bodyTxt"), (String)paramReporte.get("bodyHtml"),
									criteria);
			}
			
			criteria.put("nombreArchivoReporte", this.getNombreArchivoReporte((String)paramReporte.get("prefijoArchivo"),"reporteSACIndicadoresFNA"));
			
		}catch(Exception ex) {
			String mensajeError = "ERROR (" + criteria.get("codigoPeriodo") + "," + criteria.get("fechaFacturacion") + ")";
			if(paramReporte != null)
				mailUtil.enviarMail((String)paramReporte.get("correoOrigen"), (String)paramReporte.get("correoDefault")+","+(String)paramReporte.get("correoCC"),
					(String)paramReporte.get("subject"), mensajeError);

			throw ex;
		}
	}
	
	protected String getNombreArchivoReporte(String prefijoArchivo, String nombreReporte) {
		String nombreArchivoReporte;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		if (!StringUtils.isBlank(prefijoArchivo)) {
			nombreArchivoReporte = prefijoArchivo + "_"
				+ sdf.format(new Date(System.currentTimeMillis()));
		} else {
			nombreArchivoReporte = nombreReporte + "_"
				+ sdf.format(new Date(System.currentTimeMillis()));
		}
		return nombreArchivoReporte;
	}
	

}
