package biz.belcorp.ssicc.service.spusicc.pedidos.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDEnviarReporteSeguimientoConsultoraService;
import biz.belcorp.ssicc.service.spusicc.pedidos.ReportePEDEnviarReporteSeguimientoConsultoraService;

@Service("spusicc.procesoPEDEnviarReporteSeguimientoConsultoraService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoPEDEnviarReporteSeguimientoConsultoraServiceImpl extends BaseService implements ProcesoPEDEnviarReporteSeguimientoConsultoraService{

	@Resource(name="spusicc.reportePEDEnviarReporteSeguimientoConsultoraService")
	private ReportePEDEnviarReporteSeguimientoConsultoraService reportePEDEnviarReporteSeguimientoConsultoraService;
	
	@Resource(name="ajaxService")
	private AjaxService ajaxService;
	
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.lec.service.ProcesoLECEnviarReporteProyeccionService#executeEnviarReporteProyeccion(java.util.Map)
	 */
	public void executeEnviarReporteSeguimientoConsultora(Map params) throws Exception {
			String codigoUsuario = MapUtils.getString(params, "codigoUsuario");
			
			params.put("usuarioTemp", usuarioService.getUsuarioByUsername(codigoUsuario));
			params.put("formatoExportacion", "XLS");
			
			String []regiones = (String [])params.get("regiones");
			String []zonas = (String [])params.get("zonas");
			LabelValue[] zonaList = null;	
			
			if(regiones == null && zonas == null){
				zonaList = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(MapUtils.getString(params, "codigoPais"), "T", "VD", null, "");
			}else{
				String codigoRegion = regiones[0];
				if(zonas == null){
					
					if(StringUtils.equals(codigoRegion, "Todos")){
						zonaList = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(MapUtils.getString(params, "codigoPais"), "T", "VD", null, "");
					}else{
						zonaList = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(MapUtils.getString(params, "codigoPais"), "T", "VD", regiones, "");
					}
					
				}else{
					if(StringUtils.equals(zonas[0], "Todos")){
						zonaList = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(MapUtils.getString(params, "codigoPais"), "T", "VD", regiones, "");
					}
				}
			}
				
			if(zonaList != null && zonaList.length>0){
				for(int i=0;i<zonaList.length;i++){
					params.put("codigoZona", zonaList[i].getValue());
					reportePEDEnviarReporteSeguimientoConsultoraService.grabarReporte(params);
				}
			}else{
				for(int i=0;i<zonas.length;i++){
					params.put("codigoZona", zonas[i]);
					reportePEDEnviarReporteSeguimientoConsultoraService.grabarReporte(params);
				}
			}
			
	}


	
}
