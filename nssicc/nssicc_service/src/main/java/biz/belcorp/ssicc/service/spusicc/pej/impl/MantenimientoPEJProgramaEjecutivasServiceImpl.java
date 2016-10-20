package biz.belcorp.ssicc.service.spusicc.pej.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pej.MantenimientoPEJProgramaEjecutivasDAO;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Fase;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Grupo;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Nivel;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Porcentaje;
import biz.belcorp.ssicc.dao.spusicc.pej.model.ProgramaEjecutiva;
import biz.belcorp.ssicc.dao.spusicc.pej.model.Rango;
import biz.belcorp.ssicc.dao.spusicc.pej.model.TipoAbono;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;

/**
 * @author Sebastian Guerra
 *
 */
@Service("spusicc.mantenimientoPEJProgramaEjecutivasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoPEJProgramaEjecutivasServiceImpl extends BaseService implements MantenimientoPEJProgramaEjecutivasService {
	
	@Resource(name="spusicc.mantenimientoPEJProgramaEjecutivasDAO")
	private MantenimientoPEJProgramaEjecutivasDAO mantenimientoPEJProgramaEjecutivasDAO;

	public List getProgramasByCriteria(Map criteria) {
		return this.mantenimientoPEJProgramaEjecutivasDAO.getProgramasByCriteria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#getNextCodigoPrograma()
	 */
	public String getNextCodigoPrograma() {
		return mantenimientoPEJProgramaEjecutivasDAO.getNextCodigoPrograma();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#insertProgramaEjecutiva(biz.belcorp.ssicc.spusicc.pej.model.ProgramaEjecutiva, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertProgramaEjecutiva(ProgramaEjecutiva programaEjecutiva, Usuario usuario) throws Exception {
		log.debug("insertProgramaEjecutiva inicio");
		
		mantenimientoPEJProgramaEjecutivasDAO.insertProgramaEjecutiva(programaEjecutiva, usuario);
		
		Iterator it = programaEjecutiva.getFaseList().iterator();
		while(it.hasNext()){
			Fase fase = (Fase) it.next();
			mantenimientoPEJProgramaEjecutivasDAO.insertFase(fase, usuario);
		}
		
		it = programaEjecutiva.getNivelList().iterator();
		while(it.hasNext()){
			Nivel nivel = (Nivel) it.next();
			mantenimientoPEJProgramaEjecutivasDAO.insertNivel(nivel, usuario);
		}
		
		it = programaEjecutiva.getRangoList().iterator();
		while(it.hasNext()){
			Rango rango = (Rango) it.next();
			mantenimientoPEJProgramaEjecutivasDAO.insertRango(rango, usuario);
		}

		it = programaEjecutiva.getGrupoList().iterator();
		while(it.hasNext()){
			Grupo grupo = (Grupo) it.next();
			mantenimientoPEJProgramaEjecutivasDAO.insertGrupo(grupo, usuario);
		}
		
		it = programaEjecutiva.getPorcentajeList().iterator();
		while(it.hasNext()){
			Porcentaje porcentaje = (Porcentaje) it.next();
			BeanUtils.copyProperties(porcentaje, programaEjecutiva);
			mantenimientoPEJProgramaEjecutivasDAO.insertPorcentaje(porcentaje, usuario);
		}
		
		it = programaEjecutiva.getTipoAbonoList().iterator();
		while(it.hasNext()){
			TipoAbono tipoAbono = (TipoAbono) it.next();
			mantenimientoPEJProgramaEjecutivasDAO.insertTipoAbono(tipoAbono, usuario);
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#updateProgramaEjecutiva(biz.belcorp.ssicc.spusicc.pej.model.ProgramaEjecutiva, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateProgramaEjecutiva(ProgramaEjecutiva programaEjecutiva, Usuario usuario) {
		log.debug("updateProgramaEjecutiva inicio");
		
		mantenimientoPEJProgramaEjecutivasDAO.updateProgramaEjecutiva(programaEjecutiva, usuario);
		
		Map parametros = new HashMap();
		
		Iterator it = programaEjecutiva.getFaseList().iterator();
		while(it.hasNext()){
			Fase fase = (Fase) it.next();
			parametros.put("codigoPais", fase.getCodigoPais());
			parametros.put("codigoPrograma", fase.getCodigoPrograma());
			parametros.put("codigoFase", fase.getCodigo());
			
			List listaFase = mantenimientoPEJProgramaEjecutivasDAO.getFasePEJ(parametros);
			
			if(listaFase == null || listaFase.size() == 0){
				mantenimientoPEJProgramaEjecutivasDAO.insertFase(fase, usuario);
			}
		}
		
		it = programaEjecutiva.getNivelList().iterator();
		while(it.hasNext()){
			Nivel nivel = (Nivel) it.next();
			parametros.put("codigoPais", nivel.getCodigoPais());
			parametros.put("codigoPrograma", nivel.getCodigoPrograma());
			parametros.put("codigoNivel", nivel.getCodigo());
			
			List listaNivel = mantenimientoPEJProgramaEjecutivasDAO.getNivelPEJ(parametros);
			
			if(listaNivel == null || listaNivel.size() == 0){
				mantenimientoPEJProgramaEjecutivasDAO.insertNivel(nivel, usuario);
			}
		}
		
		it = programaEjecutiva.getRangoList().iterator();
		while(it.hasNext()){
			Rango rango = (Rango) it.next();
			parametros.put("codigoPais", rango.getCodigoPais());
			parametros.put("codigoPrograma", rango.getCodigoPrograma());
			parametros.put("codigoRango", rango.getCodigo());
			
			List listaRango = mantenimientoPEJProgramaEjecutivasDAO.getRangoPEJ(parametros);
			
			if(listaRango == null || listaRango.size() == 0){
				mantenimientoPEJProgramaEjecutivasDAO.insertRango(rango, usuario);
			}
		}
		
		it = programaEjecutiva.getGrupoList().iterator();
		while(it.hasNext()){
			Grupo grupo = (Grupo) it.next();
			parametros.put("codigoPais", grupo.getCodigoPais());
			parametros.put("codigoPrograma", grupo.getCodigoPrograma());
			parametros.put("codigoGrupo", grupo.getCodigo());
			
			List listaGrupo = mantenimientoPEJProgramaEjecutivasDAO.getGrupoPEJ(parametros);
			
			if(listaGrupo == null || listaGrupo.size() == 0){
				mantenimientoPEJProgramaEjecutivasDAO.insertGrupo(grupo, usuario);
			}
		}
		
		it = programaEjecutiva.getPorcentajeList().iterator();
		while(it.hasNext()){
			Porcentaje porcentaje = (Porcentaje) it.next();
			parametros.put("codigoPais", porcentaje.getCodigoPais());
			parametros.put("codigoPrograma", porcentaje.getCodigoPrograma());
			parametros.put("estadoPorcentaje", porcentaje.getEstadoPorcentaje());
			
			List listaPorcentaje = mantenimientoPEJProgramaEjecutivasDAO.getPorcentajePEJ(parametros);
			
			if(listaPorcentaje == null || listaPorcentaje.size() == 0){
				mantenimientoPEJProgramaEjecutivasDAO.insertPorcentaje(porcentaje, usuario);
			}
		}
		
		it = programaEjecutiva.getTipoAbonoList().iterator();
		while(it.hasNext()){
			TipoAbono tipoAbono = (TipoAbono) it.next();
			parametros.put("codigoPais", tipoAbono.getCodigoPais());
			parametros.put("codigoPrograma", tipoAbono.getCodigoPrograma());
			parametros.put("codigoTipoAbono", tipoAbono.getCodigo());
			
			List listaTipoAbono = mantenimientoPEJProgramaEjecutivasDAO.getTipoAbonoPEJ(parametros);
			
			if(listaTipoAbono == null || listaTipoAbono.size() == 0){
				mantenimientoPEJProgramaEjecutivasDAO.insertTipoAbono(tipoAbono, usuario);
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#getFasePEJ(java.util.Map)
	 */
	public List getFasePEJ(Map parametros) {
		return mantenimientoPEJProgramaEjecutivasDAO.getFasePEJ(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#getNivelPEJ(java.util.Map)
	 */
	public List getNivelPEJ(Map parametros) {
		return mantenimientoPEJProgramaEjecutivasDAO.getNivelPEJ(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#getRangoPEJ(java.util.Map)
	 */
	public List getRangoPEJ(Map parametros) {
		return mantenimientoPEJProgramaEjecutivasDAO.getRangoPEJ(parametros);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#getGrupoPEJ(java.util.Map)
	 */
	public List getGrupoPEJ(Map parametros) {
		return mantenimientoPEJProgramaEjecutivasDAO.getGrupoPEJ(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#getPorcentajePEJ(java.util.Map)
	 */
	public List getPorcentajePEJ(Map parametros) {
		return mantenimientoPEJProgramaEjecutivasDAO.getPorcentajePEJ(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#getTipoAbonoPEJ(java.util.Map)
	 */
	public List getTipoAbonoPEJ(Map parametros) {
		return mantenimientoPEJProgramaEjecutivasDAO.getTipoAbonoPEJ(parametros);
	}

	public String getExisteLiquidacionCampanya(Map parametros) {
		return mantenimientoPEJProgramaEjecutivasDAO.getExisteLiquidacionCampanya(parametros);
	}

	public void deleteProgramaEjecutiva(Map parametros) throws Exception {
		mantenimientoPEJProgramaEjecutivasDAO.deleteProgramaEjecutiva(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#getPeriodoDefault()
	 */
	public Map getPeriodoDefault() {
		return mantenimientoPEJProgramaEjecutivasDAO.getPeriodoDefault();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#getExisteDependenciaFase(java.util.Map)
	 */
	public String getExisteDependenciaFase(Map parametros) {
		return mantenimientoPEJProgramaEjecutivasDAO.getExisteDependenciaFase(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#getExisteDependenciaNivel(java.util.Map)
	 */
	public String getExisteDependenciaNivel(Map parametros) {
		return mantenimientoPEJProgramaEjecutivasDAO.getExisteDependenciaNivel(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#getExisteDependenciaRango(java.util.Map)
	 */
	public String getExisteDependenciaRango(Map parametros) {
		return mantenimientoPEJProgramaEjecutivasDAO.getExisteDependenciaRango(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#getExisteDependenciaGrupo(java.util.Map)
	 */
	public String getExisteDependenciaGrupo(Map parametros) {
		return mantenimientoPEJProgramaEjecutivasDAO.getExisteDependenciaGrupo(parametros);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pej.service.MantenimientoPEJProgramaEjecutivasService#getExisteDependenciaTipoAbono(java.util.Map)
	 */
	public String getExisteDependenciaTipoAbono(Map parametros) {
		return mantenimientoPEJProgramaEjecutivasDAO.getExisteDependenciaTipoAbono(parametros);
	}
	
}
