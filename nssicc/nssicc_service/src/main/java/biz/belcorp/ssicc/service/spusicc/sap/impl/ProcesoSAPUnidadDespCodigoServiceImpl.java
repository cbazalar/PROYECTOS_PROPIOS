package biz.belcorp.ssicc.service.spusicc.sap.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.UsuarioService;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.service.spusicc.sap.ProcesoSAPUnidadDespCodigoService;
import biz.belcorp.ssicc.service.spusicc.sap.ReporteSAPUnidaDespCodigosService;


/**
 * Service para el proceso de Reportes al termino de facturacin de Centros de Acopio
 * 
 * @author <a href="mailto:esanchez@sigcomt.com">Eduardo Snchez</a>
 */
@Service("spusicc.procesoSAPUnidadDespCodigoServiceImpl")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSAPUnidadDespCodigoServiceImpl extends BaseInterfazProcesoAbstractService implements ProcesoSAPUnidadDespCodigoService{
	
	@Resource(name="spusicc.reporteSAPUnidaDespCodigosService")
	private ReporteSAPUnidaDespCodigosService reporteSAPUnidaDespCodigosService;
	
	@Resource(name="usuarioService")
	private UsuarioService usuarioService;
	
	
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
		log.debug("inicio ProcesoSAPUnidadDespCodigoServiceImpl"	+ params);
		
		try {
			reporteSAPUnidaDespCodigosService.grabarReporte(params);
		} catch (Exception e) {
			e.printStackTrace(); 
		}
				
	}

}
