/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sessionexperte.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.MantenimientoSSECicloDeProgramaDAO;
import biz.belcorp.ssicc.dao.spusicc.sessionexperte.model.CicloDePrograma;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.sessionexperte.MantenimientoSSECicloDeProgramaService;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="MantenimientoSSECicloDeProgramaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author peextrramirez
 * 
 */
@Service("spusicc.mantenimientoSSECicloDeProgramaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoSSECicloDeProgramaServiceImpl extends BaseService implements MantenimientoSSECicloDeProgramaService {
	
	@Resource(name="spusicc.sessionexperte.mantenimientoSSECicloDeProgramaDAO")
	private MantenimientoSSECicloDeProgramaDAO mantenimientoDAO;
	
	/**
	 * Obtiene un listado de los productos del Programa Session Experte indicado.
	 * @param producto Objeto de tipo Programa session experte.
	 * @return Lista de objetos de tipo ProductoDeProgramaSessionExperte.
	 * */
	public List getCiclosDeProgramaSessionExperteByCriteria(CicloDePrograma ciclo){
		return this.mantenimientoDAO.getCiclosDeProgramaSessionExperteByCriteria(ciclo);
	}
	
	public List getTipoCiclosSessionExperte(){
		return this.mantenimientoDAO.getTipoCiclosSessionExperte();
	}

	/**
	 * Inserta un Ciclo de Programa SessionExperte
	 * @param ciclo Ciclo de Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void insertCicloDeProgramaSessionExperte(CicloDePrograma ciclo, Usuario usuario){
		 this.mantenimientoDAO.insertCicloDeProgramaSessionExperte(ciclo,usuario);
	}
	
	/**
	 * Actualiza un Ciclo de Programa SessionExperte
	 * @param ciclo Ciclo de Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void updateCicloDeProgramaSessionExperte(CicloDePrograma ciclo, Usuario usuario){
		this.mantenimientoDAO.updateCicloDeProgramaSessionExperte(ciclo,usuario);
	}

	/**
	 * Delete un Ciclo de Programa SessionExperte
	 * @param ciclo Ciclo de Programa SessionExperte.
	 * @param usuario Usuario que inserta.
	 */
	public void deleteCicloDeProgramaSessionExperte(CicloDePrograma ciclo, Usuario usuario){
		ciclo.setEstadoRegistro(Constants.ESTADO_INACTIVO);
		this.mantenimientoDAO.deleteCicloDeProgramaSessionExperte(ciclo, usuario);
	}
	

	/**
	 * Consigue un Ciclo de Programa SessionExperte
	 * @param ciclo Ciclo de Programa SessionExperte.
	 */
	public CicloDePrograma getFirstFromCiclosDeProgramaSessionExperteByCriteria(CicloDePrograma ciclo){
		return this.mantenimientoDAO.getFirstFromCiclosDeProgramaSessionExperteByCriteria(ciclo);
	}
	
	public String getNextCodigoCiclo(CicloDePrograma ciclo){
		return this.mantenimientoDAO.getCodigoCicloByAnhoTipoCiclo(ciclo);
	}
}
