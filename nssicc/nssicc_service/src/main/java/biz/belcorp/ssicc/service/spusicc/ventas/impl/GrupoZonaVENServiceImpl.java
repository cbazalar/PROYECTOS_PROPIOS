package biz.belcorp.ssicc.service.spusicc.ventas.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.ventas.GrupoZonaVenDAO;
import biz.belcorp.ssicc.dao.spusicc.ventas.model.GrupoZona;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ventas.GrupoZonaVENService;

/**
 * Clase de implementacion de la capa logica referente a Grupo Zonal
 * @author peextjnunez
 *
 */
@Service("spusicc.grupoZonaVENService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class GrupoZonaVENServiceImpl extends BaseService implements GrupoZonaVENService{
	
	@Resource(name="spusicc.grupoZonaVenDAO")
	private GrupoZonaVenDAO grupoZonaVenDAO;/**Atributo DAO*/	
	
	/**
	 * Metodo que retorna  un listado que grupos zonales
	 * @param bgrupozona  - objeto que contiene los criterios de busqueda
	 * @return Lista - Listado de retorno
	 */
	public List getGrupoZona(GrupoZona bgrupozona){
		return grupoZonaVenDAO.getGrupoZona(bgrupozona);
	}

	/**
	 * Metodo que retorna  un listado que grupos zonales
	 * @param bgrupozona  - objeto que contiene los criterios de busqueda
	 * @return Lista - Listado de retorno
	 */
	public List getGrupoZonaZonas(GrupoZona bgrupozona){
		return grupoZonaVenDAO.getGrupoZonaZonas(bgrupozona);
	}
	
	
	/**
	 * @return Returns the grupoZonaVenDAO.
	 */
	public GrupoZonaVenDAO getGrupoZonaVenDAO() {
		return grupoZonaVenDAO;
	}

	/**
	 * @param grupoZonaVenDAO The grupoZonaVenDAO to set.
	 */
	public void setGrupoZonaVenDAO(GrupoZonaVenDAO grupoZonaVenDAO) {
		this.grupoZonaVenDAO = grupoZonaVenDAO;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.service.GrupoZonaVENService#updateGrupoZona(biz.belcorp.ssicc.spusicc.ventas.model.GrupoZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateGrupoZona(GrupoZona bgrupozona, Usuario usuario){
		this.grupoZonaVenDAO.updateGrupoZona(bgrupozona,usuario);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.service.GrupoZonaVENService#insertGrupoZona(biz.belcorp.ssicc.spusicc.ventas.model.GrupoZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertGrupoZona(GrupoZona bgrupozona, Usuario usuario){
		this.grupoZonaVenDAO.insertGrupoZona(bgrupozona,usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.service.GrupoZonaVENService#deleteGrupoZona(biz.belcorp.ssicc.spusicc.ventas.model.GrupoZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteGrupoZona(GrupoZona bgrupozona, Usuario usuario){
		this.grupoZonaVenDAO.deleteGrupoZona(bgrupozona,usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.service.GrupoZonaVENService#insertZonaGrupoZona(biz.belcorp.ssicc.spusicc.ventas.model.GrupoZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertZonaGrupoZona(GrupoZona bgrupozona, Usuario usuario){
		this.grupoZonaVenDAO.insertZonaGrupoZona(bgrupozona,usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.service.GrupoZonaVENService#deleteZonaGrupoZona(biz.belcorp.ssicc.spusicc.ventas.model.GrupoZona, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteZonaGrupoZona(GrupoZona bgrupozona, Usuario usuario){
		this.grupoZonaVenDAO.deleteZonaGrupoZona(bgrupozona,usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.service.GrupoZonaVENService#getZona(biz.belcorp.ssicc.spusicc.ventas.model.GrupoZona)
	 */
	public List getZona(GrupoZona bgrupozona){
		return grupoZonaVenDAO.getZona(bgrupozona);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.service.GrupoZonaVENService#getListaAgrupadoGrupoZona()
	 */
	public List getListaAgrupadoGrupoZona() {
		return grupoZonaVenDAO.getListaAgrupadoGrupoZona();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ventas.service.GrupoZonaVENService#getRegion(java.util.HashMap)
	 */
	public List getRegion(HashMap criteria){
		return grupoZonaVenDAO.getRegion(criteria);
	}

}
