/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECRegionZonaBoletaRecojoInteligenteDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.RegionZonaBoletaRecojoInteligente;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECRegionZonaBoletaRecojoInteligenteService;


/**
 * @author Gonzalo Huertas
 *
 */
@Service("spusicc.mantenimientoRECRegionZonaBoletaRecojoInteligenteService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECRegionZonaBoletaRecojoInteligenteServiceImpl extends BaseService implements MantenimientoRECRegionZonaBoletaRecojoInteligenteService {
	
	@Resource(name="spusicc.mantenimientoRECRegionZonaBoletaRecojoInteligenteDAO")
	MantenimientoRECRegionZonaBoletaRecojoInteligenteDAO mantenimientoRECRegionZonaBoletaRecojoInteligenteDAO;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECRegionZonaBoletaRecojoInteligenteService#getRegionZonaBoletaRecojoInteligente(java.util.Map)
	 */
	public List getRegionZonaBoletaRecojoInteligenteList(Map criteria) {
		return mantenimientoRECRegionZonaBoletaRecojoInteligenteDAO.getRegionZonaBoletaRecojoInteligenteList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECRegionZonaBoletaRecojoInteligenteService#insertRegionZonaBoletaRecojoInteligente(biz.belcorp.ssicc.spusicc.reclamos.model.RegionZonaBoletaRecojoInteligente)
	 */
	public void insertRegionZonaBoletaRecojoInteligente(RegionZonaBoletaRecojoInteligente regionZonaBoletaRecojoInteligente, Usuario usuario) {
		if(StringUtils.isNotBlank(regionZonaBoletaRecojoInteligente.getCodigoRegion())&& StringUtils.isNotBlank(regionZonaBoletaRecojoInteligente.getCodigoZona()))
		{
			regionZonaBoletaRecojoInteligente.setCodigoRegion(null);
		}
		mantenimientoRECRegionZonaBoletaRecojoInteligenteDAO.insertRegionZonaBoletaRecojoInteligente(regionZonaBoletaRecojoInteligente,usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECRegionZonaBoletaRecojoInteligenteService#deleteRegionZonaBoletaRecojoInteligente(java.lang.String)
	 */
	public void deleteRegionZonaBoletaRecojoInteligente(Map criteria) {
		mantenimientoRECRegionZonaBoletaRecojoInteligenteDAO.deleteRegionZonaBoletaRecojoInteligente(criteria);
		
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECRegionZonaBoletaRecojoInteligenteService#getExisteRegionZonaBoletaRecojoInteligenteByCriteria(biz.belcorp.ssicc.spusicc.reclamos.model.RegionZonaBoletaRecojoInteligente)
	 */
	public boolean getExisteRegionZonaBoletaRecojoInteligenteByCriteria(
			RegionZonaBoletaRecojoInteligente regionZonaBoletaRecojoInteligente) {
		// TODO Auto-generated method stub
		return StringUtils.equalsIgnoreCase(mantenimientoRECRegionZonaBoletaRecojoInteligenteDAO.getExisteRegionZonaBoletaRecojoInteligenteByCriteria(regionZonaBoletaRecojoInteligente), Constants.NUMERO_CERO);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECRegionZonaBoletaRecojoInteligenteService#updateRegionZonaBoletaRecojoInteligente(biz.belcorp.ssicc.spusicc.reclamos.model.RegionZonaBoletaRecojoInteligente)
	 */
	public void updateRegionZonaBoletaRecojoInteligente(Map criteria) {
		mantenimientoRECRegionZonaBoletaRecojoInteligenteDAO.updateRegionZonaBoletaRecojoInteligente(criteria);
		
	}
	
	public boolean existeRegionAsociada(RegionZonaBoletaRecojoInteligente regionZonaBoletaRecojoInteligente){
		boolean existe=false;
		List regiones= mantenimientoRECRegionZonaBoletaRecojoInteligenteDAO.getRegionByZonaList(regionZonaBoletaRecojoInteligente);
		String codigoRegion = regionZonaBoletaRecojoInteligente.getCodigoRegion();
		if(regiones!=null && regiones.size()>0){
			for (int i=0; i<regiones.size();i++){
				if(StringUtils.equalsIgnoreCase(codigoRegion, MapUtils.getString(((Map)regiones.get(i)), "codigoRegion")))
				{
					existe = true;
				}
			}
		}
		return existe;
	}
}
