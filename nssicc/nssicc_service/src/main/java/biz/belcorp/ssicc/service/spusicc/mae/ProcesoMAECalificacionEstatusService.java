package biz.belcorp.ssicc.service.spusicc.mae;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoMAECalificacionEstatusService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 */
public interface ProcesoMAECalificacionEstatusService extends Service {

	public boolean verificarCargaInicialEstatus(String codigoPais);
	
	public void executeCalificacionEstatusCargaInicial(Map criteria);
	
	public void executeCalificacionEstatusFacturacionDiaria(Map criteria) throws Exception;

	public void executeCalificacionEstatusCierreCampana(Map criteria) throws Exception;

	public List getRegionesCerradas(Map criteria);
	
	public void executeCalificacionEstatusCierreRegion(Map criteria) throws Exception;
	
	public List getTipoProcesoList();
	
	public List getClasificacionEstatusList(Map params);
	
	public void executeProcesoAtendidos(Map params);
	
	public void deleteClasificacionEstatusList(Map params);
	
	public void enviarCorreoAdjunto(Map criteria) throws Exception;
	
	
}
