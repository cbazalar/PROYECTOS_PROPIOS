/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.flx.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.scsicc.ReporteDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.service.spusicc.flx.ReporteFLXDetalladoConsultorasHabilesService;

/**
 * Service para el proceso de Reporte Detallado de Consultoras Habiles
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
@Service("spusicc.procesoFLXDetalladoConsultorasHabilesServiceImpl")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoFLXDetalladoConsultorasHabilesServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name = "spusicc.reporteFLXDetalladoConsultorasHabilesService")
	private ReporteFLXDetalladoConsultorasHabilesService reporteFLXDetalladoConsultorasHabilesService;
	
	@Resource(name = "usuarioService")
	private UsuarioService usuarioService;
	
	@Resource(name = "scsicc.reporteDAO")
	private ReporteDAO reporteDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		params.put("usuarioTemp", usuarioService.getUsuarioByUsername((String)params.get("codigoUsuario")));
		params.put("formatoExportacion", "VPDF");
		log.debug("inicio ProcesoFLXDetalladoConsultorasHabilesServiceImpl"	+ params);
		
		try {
			//obtenemos las zonas de cada region
			Map criteria = new HashMap();
			criteria.put("codigoPais", MapUtils.getString(params, "codigoPais"));
			criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
			criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
			criteria.put("codigoSistema", Constants.FLX_CODIGO_SISTEMA);
			criteria.put("codigoParam", Constants.FLX_PARAMETRO_INDICADOR_CLIENTE_CEDULA);
			
			String indClienteCedula = reporteDAO.getIndicadorClienteCedula(criteria);
			List zonas = reporteDAO.getListaGenerico("getZonasMultipleByPaisMarcaCanal", criteria);
			for(int j=0;j<zonas.size();j++){
				String[] zonaList = new String[1];
				zonaList[0] = ((Base)zonas.get(j)).getCodigo();
				String condicionZona = this.obtieneCondicion(zonaList, "ZZ.COD_ZONA", "'");
				params.put("condicionRegion", "");
				params.put("condicionZona", condicionZona);
				params.put("condicionSeccion", "");
				params.put("codigoZona", zonaList[0]);
				params.put("indClienteCedula", indClienteCedula);
				reporteFLXDetalladoConsultorasHabilesService.grabarReporte(params);
			}
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}

	}

	public String obtieneCondicion(String[] lista, String parametro,
			String comilla) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isEmpty(dato) || StringUtils.equals(dato, "Todos"))
					return "";
				if (i == 0)
					resultado = resultado + "(" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}
			resultado = resultado + ")";
			resultado = " AND " + parametro + " IN " + resultado;
			return resultado;
		}
	} 
	
	/**
	 * @param reporteFLXDetalladoConsultorasHabilesService the reporteFLXDetalladoConsultorasHabilesService to set
	 */
	public void setReporteFLXDetalladoConsultorasHabilesService(ReporteFLXDetalladoConsultorasHabilesService reporteFLXDetalladoConsultorasHabilesService) {
		this.reporteFLXDetalladoConsultorasHabilesService = reporteFLXDetalladoConsultorasHabilesService;
	}


	
}
