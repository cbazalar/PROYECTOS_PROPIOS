package biz.belcorp.ssicc.service.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.service.framework.Service;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.RegionZonaBoletaRecojoInteligente;

/**
 * @author 
 *
 */
public interface MantenimientoRECRegionZonaBoletaRecojoInteligenteService extends Service {

	/**
	 * @param criteria
	 * @return
	 * Devuelve las operaciones de Reclamos
	 */
	public List getRegionZonaBoletaRecojoInteligenteList(Map criteria);

	public void insertRegionZonaBoletaRecojoInteligente(RegionZonaBoletaRecojoInteligente regionZonaBoletaRecojoInteligente, Usuario usuario);

	public void deleteRegionZonaBoletaRecojoInteligente(Map criteria);
	
	public boolean getExisteRegionZonaBoletaRecojoInteligenteByCriteria(RegionZonaBoletaRecojoInteligente regionZonaBoletaRecojoInteligente);
	
	public void updateRegionZonaBoletaRecojoInteligente(Map criteria);
	
	public boolean existeRegionAsociada(RegionZonaBoletaRecojoInteligente regionZonaBoletaRecojoInteligente);

}