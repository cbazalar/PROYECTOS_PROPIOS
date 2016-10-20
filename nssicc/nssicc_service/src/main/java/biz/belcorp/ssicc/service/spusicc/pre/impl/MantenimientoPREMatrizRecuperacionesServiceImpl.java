/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.pre.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREMatrizRecuperacionesDAO;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizRecuperacion;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizRecuperacionAuditoria;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPREMatrizRecuperacionesService;

/**
 * @author Sigcomt
 *
 */
@Service("spusicc.mantenimientoPREMatrizRecuperacionesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPREMatrizRecuperacionesServiceImpl extends BaseService implements MantenimientoPREMatrizRecuperacionesService {

	@Resource(name="spusicc.mantenimientoPREMatrizRecuperacionesDAO")
	MantenimientoPREMatrizRecuperacionesDAO mantenimientoPREMatrizRecuperacionesDAO;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.service.MantenimientoPREMatrizRecuperacionesService#getRecuperaciones(java.util.Map)
	 */
	public List getRecuperaciones(Map params) {
		return mantenimientoPREMatrizRecuperacionesDAO.getRecuperaciones(params);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.service.MantenimientoPREMatrizRecuperacionesService#updateRecuperacion(biz.belcorp.ssicc.spusicc.pre.model.MatrizRecuperacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRecuperacion(MatrizRecuperacion mr, Usuario usuario) {
		mantenimientoPREMatrizRecuperacionesDAO.updateRecuperacion(mr, usuario);
		
		MatrizRecuperacionAuditoria audi = new MatrizRecuperacionAuditoria();
		audi.setOid(mr.getOid());
		
		if(StringUtils.equals(mr.getIndicadorActivo(), Constants.NUMERO_CERO))
		{
			audi.setAccion(Constants.TIPO_OPERACION_DESACTIVAR);
		}
		else if(StringUtils.equals(mr.getIndicadorActivo(), Constants.NUMERO_UNO))
		{
			audi.setAccion(Constants.TIPO_OPERACION_ACTIVAR);
		}
		
		mantenimientoPREMatrizRecuperacionesDAO.insertRecuperacionAuditoria(audi, usuario);
		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.service.MantenimientoPREMatrizRecuperacionesService#insertRecuperacion(biz.belcorp.ssicc.spusicc.pre.model.MatrizRecuperacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRecuperacion(MatrizRecuperacion matrizRecuperacion, Usuario usuario) {
		
		List lista = mantenimientoPREMatrizRecuperacionesDAO.getRecuperacionList(matrizRecuperacion);
		
		if(lista != null && lista.size() > 0)
			throw new InvalidIdentifierException(MatrizRecuperacion.class, matrizRecuperacion.getOidMatrizRecuperacion());
		
		mantenimientoPREMatrizRecuperacionesDAO.insertRecuperacion(matrizRecuperacion, usuario);
	}

}
