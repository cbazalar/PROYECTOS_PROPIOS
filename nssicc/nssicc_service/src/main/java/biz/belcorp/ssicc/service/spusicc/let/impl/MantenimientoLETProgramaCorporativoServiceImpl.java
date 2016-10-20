package biz.belcorp.ssicc.service.spusicc.let.impl;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.let.MantenimientoLETProgramaCorporativoDAO;
import biz.belcorp.ssicc.dao.spusicc.let.model.Premios;
import biz.belcorp.ssicc.dao.spusicc.let.model.ProgramaCorporativo;
import biz.belcorp.ssicc.dao.spusicc.let.model.RangoNivel;
import biz.belcorp.ssicc.dao.spusicc.let.model.RangoRetencion;
import biz.belcorp.ssicc.dao.spusicc.let.model.Tramos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.let.MantenimientoLETProgramaCorporativoService;



/**
 * <p>
 * <a href="MantenimientoLETProgramaCorporativoServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *         
 */
@Service("spusicc.mantenimientoLETProgramaCorporativoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoLETProgramaCorporativoServiceImpl extends BaseService implements MantenimientoLETProgramaCorporativoService {
	@Resource(name="spusicc.mantenimientoLETProgramaCorporativoDAO")
	private MantenimientoLETProgramaCorporativoDAO mantenimientoLETProgramaCorporativoDAO;
	

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#getProgramaCorporativoList(java.util.Map)
	 */
	public List getProgramaCorporativoList(Map criteria) {
		List lista = mantenimientoLETProgramaCorporativoDAO.getProgramaCorporativoList(criteria);
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#getNextCodigoProgramaCorporativo()
	 */
	public String getNextCodigoProgramaCorporativo() {
		return mantenimientoLETProgramaCorporativoDAO.getNextCodigoProgramaCorporativo();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#getTipoNivelExito()
	 */
	public List getTipoNivelExito() {
		return mantenimientoLETProgramaCorporativoDAO.getTipoNivelExito();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#insertProgramaCorporativo(java.util.Map)
	 */
	public void insertProgramaCorporativo(ProgramaCorporativo programaCorporativo, Usuario usuario) {
		String codigoPrograma = mantenimientoLETProgramaCorporativoDAO.getNextCodigoProgramaCorporativo();
		programaCorporativo.setCodigoPrograma(codigoPrograma);
		mantenimientoLETProgramaCorporativoDAO.insertProgramaCorporativo(programaCorporativo, usuario);
		
		Iterator it = programaCorporativo.getRangoNivelList().iterator();
		while(it.hasNext()){
			RangoNivel rangoNivel = (RangoNivel) it.next();
			rangoNivel.setCodigoPais(programaCorporativo.getCodigoPais());
			rangoNivel.setCodigoPrograma(programaCorporativo.getCodigoPrograma());
			mantenimientoLETProgramaCorporativoDAO.insertRangoNivel(rangoNivel, usuario);
		}
		
		it = programaCorporativo.getRangoRetencionList().iterator();
		while(it.hasNext()){
			RangoRetencion rangoRetencion = (RangoRetencion) it.next();
			rangoRetencion.setCodigoPais(programaCorporativo.getCodigoPais());
			rangoRetencion.setCodigoPrograma(programaCorporativo.getCodigoPrograma());
			mantenimientoLETProgramaCorporativoDAO.insertRangoRetencion(rangoRetencion, usuario);
		}
		
		it = programaCorporativo.getTramosList().iterator();
		while(it.hasNext()){
			Tramos tramos = (Tramos) it.next();
			tramos.setCodigoPais(programaCorporativo.getCodigoPais());
			tramos.setCodigoPrograma(programaCorporativo.getCodigoPrograma());
			
			if(tramos.getEstado() != Constants.ESTADO_INACTIVO)
				mantenimientoLETProgramaCorporativoDAO.insertTramos(tramos, usuario);
		}
		
		it = programaCorporativo.getPremiosList().iterator();
		while(it.hasNext()){
			Premios premios = (Premios) it.next();
			premios.setCodigoPais(programaCorporativo.getCodigoPais());
			premios.setCodigoPrograma(programaCorporativo.getCodigoPrograma());
			
			if(premios.getEstado() != Constants.ESTADO_INACTIVO)
				mantenimientoLETProgramaCorporativoDAO.insertPremios(premios, usuario);
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#updateProgramaCorporativo(java.util.Map)
	 */
	public void updateProgramaCorporativo(ProgramaCorporativo programaCorporativo, Usuario usuario) {
		mantenimientoLETProgramaCorporativoDAO.updateProgramaCorporativo(programaCorporativo, usuario);
		
		Map parametros = new HashMap();
		parametros.put("codigoPais", programaCorporativo.getCodigoPais());
		parametros.put("codigoPrograma", programaCorporativo.getCodigoPrograma());
		
		Iterator it = programaCorporativo.getRangoNivelList().iterator();
		
		if(StringUtils.equals(programaCorporativo.getIndicadorActivoRangoNivel(), Constants.NUMERO_UNO)){
			mantenimientoLETProgramaCorporativoDAO.deleteRangoNivel(parametros);
			
			while(it.hasNext()){
				RangoNivel rangoNivel = (RangoNivel) it.next();
				rangoNivel.setCodigoPais(programaCorporativo.getCodigoPais());
				rangoNivel.setCodigoPrograma(programaCorporativo.getCodigoPrograma());
				
				parametros.put("codigoNivel", rangoNivel.getCodigoNivel());
				//parametros.put("estado", Constants.ESTADO_ACTIVO);
				
				//List listaRangoNivel = mantenimientoLETProgramaCorporativoDAO.getRangoNivelLET(parametros);
				
				mantenimientoLETProgramaCorporativoDAO.insertRangoNivel(rangoNivel, usuario);
				
				/*if(listaRangoNivel != null && listaRangoNivel.size() > 0){ //UPDATE
					mantenimientoLETProgramaCorporativoDAO.updateRangoNivel(rangoNivel, usuario);
				} else{ //INSERT
					mantenimientoLETProgramaCorporativoDAO.insertRangoNivel(rangoNivel, usuario);
				}*/
			}
		}
		
		it = programaCorporativo.getRangoRetencionList().iterator();
		if(StringUtils.equals(programaCorporativo.getIndicadorActivoRangoRetencion(), Constants.NUMERO_UNO)){
			mantenimientoLETProgramaCorporativoDAO.deleteRangoRetencion(parametros);
			
			while(it.hasNext()){
				RangoRetencion rangoRetencion = (RangoRetencion) it.next();
				rangoRetencion.setCodigoPais(programaCorporativo.getCodigoPais());
				rangoRetencion.setCodigoPrograma(programaCorporativo.getCodigoPrograma());
				
				parametros.put("codigoRetencion", rangoRetencion.getCodigoRetencion());
				
				//List listaRangoRetencion = mantenimientoLETProgramaCorporativoDAO.getRangoRetencionLET(parametros);
				
				mantenimientoLETProgramaCorporativoDAO.insertRangoRetencion(rangoRetencion, usuario);
				
				/*if(listaRangoRetencion != null && listaRangoRetencion.size() > 0){ //UPDATE
					mantenimientoLETProgramaCorporativoDAO.updateRangoRetencion(rangoRetencion, usuario);
				} else{ //INSERT
					mantenimientoLETProgramaCorporativoDAO.insertRangoRetencion(rangoRetencion, usuario);
				}*/
			}
		}
		
		it = programaCorporativo.getTramosList().iterator();
		if(StringUtils.equals(programaCorporativo.getIndicadorActivoTramos(), Constants.NUMERO_UNO)){
			while(it.hasNext()){
				Tramos tramos = (Tramos) it.next();
				tramos.setCodigoPais(programaCorporativo.getCodigoPais());
				tramos.setCodigoPrograma(programaCorporativo.getCodigoPrograma());
				
				parametros.put("codigoTramo", tramos.getCodigoTramo());
				
				List listaTramos = mantenimientoLETProgramaCorporativoDAO.getTramosLET(parametros);
				
				if(listaTramos != null && listaTramos.size() > 0){ //UPDATE
					if(tramos.getEstado() == Constants.ESTADO_INACTIVO)
						mantenimientoLETProgramaCorporativoDAO.deleteTramos(parametros);
					else
						mantenimientoLETProgramaCorporativoDAO.updateTramos(tramos, usuario);
				} else{ //INSERT
					if(tramos.getEstado() != Constants.ESTADO_INACTIVO)
						mantenimientoLETProgramaCorporativoDAO.insertTramos(tramos, usuario);
				}
			}
		}
		
		it = programaCorporativo.getPremiosList().iterator();
		if(StringUtils.equals(programaCorporativo.getIndicadorActivoPremios(), Constants.NUMERO_UNO)){
			while(it.hasNext()){
				Premios premios = (Premios) it.next();
				premios.setCodigoPais(programaCorporativo.getCodigoPais());
				premios.setCodigoPrograma(programaCorporativo.getCodigoPrograma());
				
				parametros.put("campanyaPremio", premios.getCampanyaPremio());
				parametros.put("codigoNivelPremio", premios.getCodigoNivelPremio());
				parametros.put("variablePremio", premios.getVariablePremio());
				parametros.put("codigoPremioAnterior", premios.getCodigoPremioAnterior());
				
				List listaPremios = mantenimientoLETProgramaCorporativoDAO.getPremiosLET(parametros);
				
				if(listaPremios != null && listaPremios.size() > 0){ //UPDATE
					if(premios.getEstado() == Constants.ESTADO_INACTIVO){
						parametros.put("codigoPremio", premios.getCodigoPremio());
						mantenimientoLETProgramaCorporativoDAO.deletePremios(parametros);
					}else{
						if(StringUtils.isBlank(premios.getCodigoPremioAnterior()) || premios.getCodigoPremioAnterior() == null)
							mantenimientoLETProgramaCorporativoDAO.insertPremios(premios, usuario);
						else
							mantenimientoLETProgramaCorporativoDAO.updatePremios(premios, usuario); //OK
					}
				} else{ //INSERT
					if(premios.getEstado() != Constants.ESTADO_INACTIVO)
						mantenimientoLETProgramaCorporativoDAO.insertPremios(premios, usuario);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#deleteProgramaCorporativo(java.util.Map)
	 */
	public void deleteProgramaCorporativo(Map criteria) {
		mantenimientoLETProgramaCorporativoDAO.deleteProgramaCorporativo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#getRangoNivelLET(java.util.Map)
	 */
	public List getRangoNivelLET(Map parametros) {
		return mantenimientoLETProgramaCorporativoDAO.getRangoNivelLET(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#getRangoRetencionLET(java.util.Map)
	 */
	public List getRangoRetencionLET(Map parametros) {
		return mantenimientoLETProgramaCorporativoDAO.getRangoRetencionLET(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#getTramosLET(java.util.Map)
	 */
	public List getTramosLET(Map parametros) {
		return mantenimientoLETProgramaCorporativoDAO.getTramosLET(parametros);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#getPremiosLET(java.util.Map)
	 */
	public List getPremiosLET(Map parametros) {
		return mantenimientoLETProgramaCorporativoDAO.getPremiosLET(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#getExisteObjetivosPedidosPorPrograma(java.util.Map)
	 */
	public String getExisteObjetivosPedidosPorPrograma(Map parametros) {
		return mantenimientoLETProgramaCorporativoDAO.getExisteObjetivosPedidosPorPrograma(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#getExisteProgramaCampanaFinNulo(java.util.Map)
	 */
	public String getExisteProgramaCampanaFinNulo(Map parametros) {
		return mantenimientoLETProgramaCorporativoDAO.getExisteProgramaCampanaFinNulo(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#getMaximaCampanaFinPrograma()
	 */
	public String getMaximaCampanaFinPrograma() {
		return mantenimientoLETProgramaCorporativoDAO.getMaximaCampanaFinPrograma();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#getPremiosPorCampanaList(java.util.Map)
	 */
	public List getPremiosPorCampanaList(Map criteria) {
		List lista = mantenimientoLETProgramaCorporativoDAO.getPremiosPorCampanaList(criteria);
		return lista;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.service.MantenimientoLETProgramaCorporativoService#getNivelesCalculadosByCampanha(java.util.Map)
	 */
	public String getNivelesCalculadosByCampanha(Map criteria) {
		return mantenimientoLETProgramaCorporativoDAO.getNivelesCalculadosByCampanha(criteria);
	}

}