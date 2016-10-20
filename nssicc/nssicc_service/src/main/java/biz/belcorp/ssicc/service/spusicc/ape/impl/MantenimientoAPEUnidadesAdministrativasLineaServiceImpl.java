package biz.belcorp.ssicc.service.spusicc.ape.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.ape.MantenimientoAPEUnidadesAdministrativasDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.ape.MantenimientoAPEUnidadesAdministrativasLineaService;

/**
 *  
 * <p>
 * <a href="MantenimientoAPEUnidadesAdministrativasLineaServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Nicols Lpez</a>
 * 
 */
@Service("spusicc.mantenimientoAPEUnidadesAdministrativasLineaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoAPEUnidadesAdministrativasLineaServiceImpl extends
		BaseService implements MantenimientoAPEUnidadesAdministrativasLineaService {

	@Resource(name="spusicc.mantenimientoAPEUnidadesAdministrativasDAO")
	private MantenimientoAPEUnidadesAdministrativasDAO mantenimientoAPEUnidadesAdministrativasDAO;
	
	public List getCodigoCentroDistribucionList(Map criteria) {
		return this.mantenimientoAPEUnidadesAdministrativasDAO.getCodigoCentroDistribucionList(criteria);
	}
	


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEUnidadesAdministrativasLineaService#getCodigoCDDefecto(java.util.Map)
	 */
	public String getCodigoCDDefecto(Map criteria) {
		return this.mantenimientoAPEUnidadesAdministrativasDAO.getCodigoCDDefecto(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEUnidadesAdministrativasLineaService#getLineaArmadoListar(java.util.Map)
	 */
	public List getLineaArmadoListar(Map criteria) {
		return this.mantenimientoAPEUnidadesAdministrativasDAO.getLineaArmadoListar(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEUnidadesAdministrativasLineaService#getCodLineaArmadaDefecto(java.util.Map)
	 */
	public String getCodLineaArmadaDefecto(Map criteria){
		return this.mantenimientoAPEUnidadesAdministrativasDAO.getCodLineaArmadaDefecto(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEUnidadesAdministrativasLineaService#getOidCentroDistribucionPais(java.util.Map)
	 */
	public String getOidCentroDistribucionPais(Map criteria){
		return this.mantenimientoAPEUnidadesAdministrativasDAO.getOidCentroDistribucionPais(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEUnidadesAdministrativasLineaService#getOidLineaArmado(java.util.Map)
	 */
	public String getOidLineaArmado(Map criteria) {
		return this.mantenimientoAPEUnidadesAdministrativasDAO.getOidLineaArmado(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEUnidadesAdministrativasLineaService#insertUnidadAdministrativaLinea(java.util.Map)
	 */
	public void insertUnidadAdministrativaLinea(Map criteria, String[] arrCodRegion, String[] arrCodZona, String[] arrCodSeccion) {
		
		if (criteria.get("oidAAFP").equals("2")){
			
			for (int x=0;x<arrCodRegion.length;x++){
				criteria.remove("codigoRegion");
				criteria.remove("codigoZona");
				criteria.remove("codigoSeccion");
				criteria.put("codigoRegion", arrCodRegion[x].toString());
				criteria.put("codigoZona", null);
				criteria.put("codigoSeccion", null);
				this.mantenimientoAPEUnidadesAdministrativasDAO.insertUnidadAdministrativaLinea(criteria);
			}
			
		}else if(criteria.get("oidAAFP").equals("3")){
			
			for (int a=0;a<arrCodRegion.length;a++){
				for (int y=0;y<arrCodZona.length;y++){
					criteria.remove("codigoRegion");
					criteria.remove("codigoZona");
					criteria.remove("codigoSeccion");					
					criteria.put("codigoRegion", arrCodRegion[a].toString());
					criteria.put("codigoZona", arrCodZona[y].toString());
					criteria.put("codigoSeccion", null);
					this.mantenimientoAPEUnidadesAdministrativasDAO.insertUnidadAdministrativaLinea(criteria);
				}
			}
			
		}else if(criteria.get("oidAAFP").equals("1")){

			for (int b=0;b<arrCodRegion.length;b++){
				for (int c=0;c<arrCodZona.length;c++){
					for (int z=0;z<arrCodSeccion.length;z++){
						criteria.remove("codigoRegion");
						criteria.remove("codigoZona");
						criteria.remove("codigoSeccion");
						criteria.put("codigoRegion", arrCodRegion[b].toString());
						criteria.put("codigoZona", arrCodZona[c].toString());
						criteria.put("codigoSeccion", arrCodSeccion[z].toString());
						this.mantenimientoAPEUnidadesAdministrativasDAO.insertUnidadAdministrativaLinea(criteria);
					}
				}
			}
			
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEUnidadesAdministrativasLineaService#getValidaExisteUadmLineaAPE(java.util.Map)
	 */
	public String getValidaExisteUadmLineaAPE(Map criteria, String[] arrCodRegion, String[] arrCodZona, String[] arrCodSeccion) {

		int flagValida = 0;
		boolean flag = false;
		String valor = "";
		
		if (criteria.get("oidAAFP").equals("2")){
			
			for (int x=0;x<arrCodRegion.length;x++){
				criteria.put("codigoRegion", arrCodRegion[x].toString());
				criteria.put("codigoZona", null);
				criteria.put("codigoSeccion", null);
				flagValida = Integer.parseInt(this.mantenimientoAPEUnidadesAdministrativasDAO.getValidaExisteUadmLineaAPE(criteria));
				if (flagValida > 0){
					flag = true;
				}
			}
			
		}else if(criteria.get("oidAAFP").equals("3")){
			
			for (int x=0;x<arrCodRegion.length;x++){
				for (int y=0;y<arrCodZona.length;y++){
					criteria.put("codigoRegion", arrCodRegion[x].toString());
					criteria.put("codigoZona", arrCodZona[y].toString());
					criteria.put("codigoSeccion", null);
					flagValida = Integer.parseInt(this.mantenimientoAPEUnidadesAdministrativasDAO.getValidaExisteUadmLineaAPE(criteria));
					if (flagValida > 0){
						flag = true;
					}
				}
			}
			
		}else if(criteria.get("oidAAFP").equals("1")){

			for (int x=0;x<arrCodRegion.length;x++){
				for (int y=0;y<arrCodZona.length;y++){
					for (int z=0;z<arrCodSeccion.length;z++){
						criteria.put("codigoRegion", arrCodRegion[x].toString());
						criteria.put("codigoZona", arrCodZona[y].toString());
						criteria.put("codigoSeccion", arrCodSeccion[z].toString());
						flagValida = Integer.parseInt(this.mantenimientoAPEUnidadesAdministrativasDAO.getValidaExisteUadmLineaAPE(criteria));
						if (flagValida > 0){
							flag = true;
						}
					}
				}
			}
			
		}
		
		if (flag){
			valor = "1";
		}else{
			valor = "0";
		}
		return valor;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEUnidadesAdministrativasLineaService#getUnidadAdministrativaLineaList(java.util.Map)
	 */
	public List getUnidadAdministrativaLineaList(Map criteria) {
		return this.mantenimientoAPEUnidadesAdministrativasDAO.getUnidadAdministrativaLineaList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEUnidadesAdministrativasLineaService#deleteUnidadAdministrativa(java.util.Map)
	 */
	public void deleteUnidadAdministrativa(Map criteria,String[] items) {
		
		for(int i = 0; i < items.length; i++){
			
			String id = items[i];
	
			criteria.put("oidLineUadm", id);
			
			this.mantenimientoAPEUnidadesAdministrativasDAO.deleteUnidadAdministrativa(criteria);	
			
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEUnidadesAdministrativasLineaService#getOidNivelAgrupacionOlas(java.util.Map)
	 */
	public String getOidNivelAgrupacionOlas(Map criteria) {
		return this.mantenimientoAPEUnidadesAdministrativasDAO.getOidNivelAgrupacionOlas(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.ape.service.MantenimientoAPEUnidadesAdministrativasLineaService#getObtenerNivelOlas(java.util.Map)
	 */
	public List getObtenerNivelOlas(Map criteria) {
		return this.mantenimientoAPEUnidadesAdministrativasDAO.getObtenerNivelOlas(criteria);
	}
	
}
