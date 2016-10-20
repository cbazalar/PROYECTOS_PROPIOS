package biz.belcorp.ssicc.service.spusicc.app.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.app.ProcesoAPPCargarSecuenciaZonaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.app.ProcesoAPPCargarSecuenciaZonaService;

/**  
 * <p>
 * <a href="ProcesoAPPCargarHomolYobelServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 */

/**
 * @author Jose Luis Rodriguez
 *
 */
@Service("spusicc.procesoAPPCargarSecuenciaZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoAPPCargarSecuenciaZonaServiceImpl extends BaseService implements ProcesoAPPCargarSecuenciaZonaService {

	@Resource(name="spusicc.procesoAPPCargarSecuenciaZonaDAO")
	private ProcesoAPPCargarSecuenciaZonaDAO procesoAPPCargarSecuenciaZonaDAO;
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPCargarSecuenciaZonaService#insertaSecuenciaZona(java.util.List)
	 */
	public void insertaSecuenciaZona(List lineas){

		for (int i = 0; i < lineas.size(); i++) {
			Map params = (Map)lineas.get(i);
			procesoAPPCargarSecuenciaZonaDAO.insertaSecuenciaZona(params);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPCargarSecuenciaZonaService#deleteTablaSecuenciaZona()
	 */
	public void deleteTablaSecuenciaZona(){
		procesoAPPCargarSecuenciaZonaDAO.deleteTablaSecuenciaZona();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.app.service.ProcesoAPPCargarSecuenciaZonaService#executeCargaSecuenciaZona(java.util.Map)
	 */
	public void executeCargaSecuenciaZona(Map criteria){
		procesoAPPCargarSecuenciaZonaDAO.executeCargaSecuenciaZona(criteria);
	}
	
	/**
	 * Obtiene message resource
	 * @param usuario
	 * @param messageResource
	 * @return
	 */
	public String getMessageResource(Usuario usuario, String messageResource) {
		return (this.messageSource).getMessage(messageResource, null, getLocale(usuario));
	}

	
}