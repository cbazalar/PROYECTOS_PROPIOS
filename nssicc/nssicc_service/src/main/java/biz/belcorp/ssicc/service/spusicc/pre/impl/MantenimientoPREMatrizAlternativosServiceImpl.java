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
import biz.belcorp.ssicc.dao.spusicc.pre.MantenimientoPREMatrizAlternativosDAO;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizAlternativo;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizAlternativoAuditoria;
import biz.belcorp.ssicc.dao.spusicc.pre.model.MatrizRecuperacion;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pre.MantenimientoPREMatrizAlternativosService;

/**
 * 
 * @author Sigcomt
 *
 */
@Service("spusicc.mantenimientoPREMatrizAlternativosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPREMatrizAlternativosServiceImpl extends BaseService implements MantenimientoPREMatrizAlternativosService{

	@Resource(name="spusicc.mantenimientoPREMatrizAlternativosDAO")
	MantenimientoPREMatrizAlternativosDAO mantenimientoPREMatrizAlternativosDAO; 	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.service.MantenimientoPREMatrizAlternativosService#getAlternativos(java.util.Map)
	 */
	public List getAlternativos(Map params) {
		return mantenimientoPREMatrizAlternativosDAO.getAlternativos(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.service.MantenimientoPREMatrizAlternativosService#updateAlternativo(biz.belcorp.ssicc.spusicc.pre.model.MatrizAlternativo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateAlternativo(MatrizAlternativo ma, Usuario usuario) {
		mantenimientoPREMatrizAlternativosDAO.updateAlternativo(ma,usuario);
		
		MatrizAlternativoAuditoria audi= new MatrizAlternativoAuditoria();
		audi.setOid(ma.getOid());
		
		if(StringUtils.equals(ma.getIndicadorActivo(), Constants.NUMERO_CERO))
		{
			audi.setAccion(Constants.TIPO_OPERACION_DESACTIVAR);
		}
		else if(StringUtils.equals(ma.getIndicadorActivo(), Constants.NUMERO_UNO))
		{
			audi.setAccion(Constants.TIPO_OPERACION_ACTIVAR);
		}
		
		mantenimientoPREMatrizAlternativosDAO.insertAlternativoAuditoria(audi,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pre.service.MantenimientoPREMatrizAlternativosService#insertAlternativo(biz.belcorp.ssicc.spusicc.pre.model.MatrizAlternativo, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertAlternativo(MatrizAlternativo matrizAlternativo, Usuario usuario) {
		
		List lista = mantenimientoPREMatrizAlternativosDAO.getAlternativosList(matrizAlternativo);
		
		if(lista != null && lista.size() > 0)
			throw new InvalidIdentifierException(MatrizRecuperacion.class, matrizAlternativo.getOidMatrizPrincipal());
		
		mantenimientoPREMatrizAlternativosDAO.insertAlternativo(matrizAlternativo, usuario);

	}	
}






















