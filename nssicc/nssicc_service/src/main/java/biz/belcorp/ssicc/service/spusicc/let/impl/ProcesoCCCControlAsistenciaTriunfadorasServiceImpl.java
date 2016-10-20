package biz.belcorp.ssicc.service.spusicc.let.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.scsicc.ReporteDAO;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.service.spusicc.let.ReporteCOLControlAsistenciaTriunfadorasService;


/**
 * Service para el proceso de Reportes al termino de facturacin de Centros de Acopio
 * 
 * @author <a href="mailto:esanchez@sigcomt.com">Eduardo Snchez</a>
 */
@Service("spusicc.procesoCCCControlAsistenciaTriunfadorasServiceImpl")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoCCCControlAsistenciaTriunfadorasServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.reporteCOLControlAsistenciaTriunfadorasService")
	private ReporteCOLControlAsistenciaTriunfadorasService reporteCOLControlAsistenciaTriunfadorasService;
	
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	
	private String[] listaRegiones;
	private String codigoZona;
	private String codigoRegion;
	
	@Resource(name="scsicc.reporteDAO")
	private ReporteDAO reporteDAO;

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService
	 * #executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		params.put("usuarioTemp", usuarioService.getUsuarioByUsername((String)params.get("codigoUsuario")));
		params.put("formatoExportacion", "VPDF");
		log.debug("inicio ProcesoCCCControlAsistenciaTriunfadorasServiceImpl"	+ params);
		
		try {
			
			//obtenemos la lista de regiones
			listaRegiones= (String[])params.get("codigoRegionList");
			if (listaRegiones != null) 
				for(int i=0;i<listaRegiones.length;i++){
					codigoRegion=listaRegiones[i];
					String[] codigosRegion = new String[1];
					codigosRegion[0]=codigoRegion;
					//obtenemos las zonas de cada region
					Map criteria = new HashMap();
					criteria.put("codigoPais", MapUtils.getString(params, "codigoPais"));
					criteria.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
					criteria.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
					criteria.put("codigoRegion", codigosRegion);
					
					List zonasRegion = reporteDAO.getListaGenerico("getZonasMultipleByPaisMarcaCanalRegion", criteria);
					for(int j=0;j<zonasRegion.size();j++){
						codigoZona= ((Base)zonasRegion.get(j)).getCodigo();
						params.put("codigoZona", codigoZona);
					    params.put("codigoRegion", codigoRegion);
						reporteCOLControlAsistenciaTriunfadorasService.grabarReporte(params);
					}
				}
			
		} catch (Exception e) {
			e.printStackTrace(); 
		}
				
	}


}
