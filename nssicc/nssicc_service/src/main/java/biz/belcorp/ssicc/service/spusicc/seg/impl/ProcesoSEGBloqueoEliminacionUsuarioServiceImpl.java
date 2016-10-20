/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.seg.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.GenericoDAO;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.seg.ProcesoSEGBloqueoEliminacionUsuarioService;

/**
 * <p>
 * <a href="ProcesoGENReactivacionesGerentesDirectorioServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalarlarosa@gmail.com"> </a>
 */
@Service("spusicc.procesoSEGBloqueoEliminacionUsuarioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSEGBloqueoEliminacionUsuarioServiceImpl extends BaseService implements ProcesoSEGBloqueoEliminacionUsuarioService {

	private static final String USUARIO_QUARTZ = "USUQUARTZ";
	
	
	@Resource(name = "genericoDAO")
	private GenericoDAO genericoDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.seg.ProcesoSEGBloqueoEliminacionUsuarioService#executeProcesoBloqueoEliminacionUsuarioMasiva()
	 */
	public void executeProcesoBloqueoEliminacionUsuarioMasiva() {
        log.debug("inicio job executeProcesoBloqueoEliminacionUsuario");
        
        String codigoPais = obtenerParametroPais("", Constants.SISTEMA_GEN, Constants.GEN_CODIGO_PARAMETRO_PAIS_DEFAULT, null);	
        Map criteria = new HashMap();
        criteria.put("codigoPais", codigoPais);
		this.genericoDAO.executeProcesoBloqueoEliminacionUsuarioMasiva(criteria);
		log.debug("fin job executeProcesoBloqueoEliminacionUsuario");
		
	}
	
	
	/**
	 * @param codigoPais
	 * @param codigoSistema
	 * @param codigoParametro
	 * @param nombreParametro
	 * @return
	 */
	private String obtenerParametroPais(String codigoPais, String codigoSistema, String codigoParametro, String nombreParametro) {
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(codigoPais);
		parametroPais.setCodigoSistema(codigoSistema);
		if (StringUtils.isNotBlank(codigoParametro))
			parametroPais.setCodigoParametro(codigoParametro);
		
		if (StringUtils.isNotBlank(nombreParametro))
			parametroPais.setNombreParametro(nombreParametro);
		ParametroPais pPais = (ParametroPais)genericoDAO.getParametrosPais(parametroPais).get(0);
		String valorParametro = pPais.getValorParametro();
		
		return valorParametro;
	}
	
	
	
}
