package biz.belcorp.ssicc.service.spusicc.sgr.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.spusicc.sgr.ProcesoSGRGenericoDAO;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;

/**
 * Service para el Proceso que permite Actualizar el historico de cobertura de polizas
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
@Service("spusicc.procesoSGRActualizarHistoricoCoberturaPolizaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSGRActualizarHistoricoCoberturaPolizaServiceImpl extends BaseInterfazProcesoAbstractService {

	@Resource(name="spusicc.procesoSGRGenericoDAO")
	private ProcesoSGRGenericoDAO procesoSGRGenericoDAO;
	
	@Resource(name="genericoDAO")
	private GenericoDAO genericoDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazProcesoAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) throws InterfazException, Exception {
		
		/*//Verificamos si va ejecutr o no el proceso
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(MapUtils.getString(params, "codigoPais"));
		parametroPais.setCodigoSistema(Constants.SGR_CODIGO_SISTEMA);
		parametroPais.setCodigoParametro(Constants.SGR_CODIGO_PARAMETRO_FAMILIA_PROTEGIDA);
		parametroPais.setNombreParametro(Constants.HIP_NOMBRE_PARAMETRO_FAMILIA_PROTEGIDA);
		parametroPais.setIndicadorActivo(Constants.ESTADO_ACTIVO);
		
		List lstParametros = genericoDAO.getParametrosPais(parametroPais);
		
		String ejecutarProceso = Constants.ESTADO_INACTIVO;
		
		if(lstParametros != null && lstParametros.size() > 0){			
			ParametroPais ps = (ParametroPais)lstParametros.get(0);
			
			ejecutarProceso = ps.getValorParametro();
		}		
		//
		
		if(StringUtils.equals(ejecutarProceso, Constants.ESTADO_ACTIVO))
		{
			procesoSGRGenericoDAO.executeActualizarHistoricoCoberturaPoliza(params);
		}
		else
		{
			log.warn("No se ejecuta el proceso, revisar configuracion de ejecucion en la BAS_PARAM_PAIS");
		}*/
		
		procesoSGRGenericoDAO.executeActualizarHistoricoCoberturaPoliza(params);
		
	}

}
