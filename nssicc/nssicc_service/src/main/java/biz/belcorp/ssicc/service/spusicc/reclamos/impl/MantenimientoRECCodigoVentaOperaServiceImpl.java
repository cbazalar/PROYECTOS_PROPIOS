/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.reclamos.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECCodigoVentaOperaDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECCodigoVentaOperaService;

/**
 * @author peextcroman
 *
 */
@Service("spusicc.mantenimientoRECCodigoVentaOperaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoRECCodigoVentaOperaServiceImpl extends BaseService implements MantenimientoRECCodigoVentaOperaService {
	
	@Resource(name="spusicc.mantenimientoRECCodigoVentaOperaDAO")
	MantenimientoRECCodigoVentaOperaDAO mantenimientoRECCodigoVentaOperaDAO;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECCodigoVentaOperaService#getTipoOfertaList(java.util.Map)
	 */
	public List getTipoOfertaList(Map map){
		return mantenimientoRECCodigoVentaOperaDAO.getTipoOfertaList(map);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECCodigoVentaOperaService#getCodigoCatalogoList(java.util.Map)
	 */
	public List getCodigoCatalogoList(Map map){
		return mantenimientoRECCodigoVentaOperaDAO.getCodigoCatalogoList(map);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECCodigoVentaOperaService#getCodigoVentaOperaList(java.util.Map)
	 */
	public List getCodigoVentaOperaList(Map map){
		return mantenimientoRECCodigoVentaOperaDAO.getCodigoVentaOperaList(map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECCodigoVentaOperaService#deleteCodigoVentaOpera(java.util.Map, java.lang.String[])
	 */
	public void deleteCodigoVentaOpera(Map map, String[] items){
		
		for(int i = 0; i < items.length; i++){
			
			String[] id_split = items[i].split(";");
			
			try{
				//-- Crear Pojo
				Map criteria = new HashMap();
				//log.debug("Split "+id_split);

				log.debug("Codigo Pais---->"+map.get("codigoPais"));
				criteria.put("codigoPais", map.get("codigoPais"));
				//log.debug("codigoPerido----->"+id_split[0]);
				//log.debug("codigoOperacion----->"+id_split[1]);
				//log.debug("codigoTipoOperacion----->"+id_split[2]);
				
				criteria.put("codigoPeriodo", id_split[0]);
				criteria.put("codigoOperacion", id_split[1]);
				criteria.put("codigoTipoOperacion", id_split[2]);

				if (id_split.length==4){
					criteria.put("codigoVenta", id_split[3]);
				}else{
					criteria.put("codigoVenta", "");
	}
	
				this.mantenimientoRECCodigoVentaOperaDAO.deleteCodigoVentaOpera(criteria);
				
			}catch(Exception e){
				log.debug("Exception :");
				e.printStackTrace();
			}
			
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECCodigoVentaOperaService#insertCodigoVentaOpera(java.util.Map)
	 */
	public void insertCodigoVentaOpera(Map map){
		log.info("Entro a MantenimientoRECCodigoVentaOperaServiceImpl - insertCodigoVentaOpera(java.util.Map)");
		
		List listaCodigosVenta = (List)map.get("listaCodigosVenta");
		String[] listaTipoOperacion = (String[])map.get("codigoTipoOperacion");
		String[] codigos = null;
		
		for (int i = 0; i < listaTipoOperacion.length; i++) {
			codigos = listaTipoOperacion[i].split("-");
			map.put("codigoOperacion", codigos[0]);
			map.put("codigoTipoOperacion", codigos[1]);
			if(listaCodigosVenta != null && !listaCodigosVenta.isEmpty()){
				for(int j=0;j<listaCodigosVenta.size();j++){
					map.put("codigoVenta", (String)listaCodigosVenta.get(j));
					if(mantenimientoRECCodigoVentaOperaDAO.getValidaCodigoVentaOpera(map) == 0)
						mantenimientoRECCodigoVentaOperaDAO.insertCodigoVentaOpera(map);
				}
			}else{
				if(mantenimientoRECCodigoVentaOperaDAO.getValidaCodigoVentaOpera(map) == 0)
					mantenimientoRECCodigoVentaOperaDAO.insertCodigoVentaOpera(map);
			}
		}
		
		log.info("Salio a MantenimientoRECCodigoVentaOperaServiceImpl - insertCodigoVentaOpera(java.util.Map)");
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.service.MantenimientoRECCodigoVentaOperaService#updateCodigoVentaOpera(java.util.Map)
	 */
	public void updateCodigoVentaOpera(Map map){
		mantenimientoRECCodigoVentaOperaDAO.updateCodigoVentaOpera(map);
	}
}
