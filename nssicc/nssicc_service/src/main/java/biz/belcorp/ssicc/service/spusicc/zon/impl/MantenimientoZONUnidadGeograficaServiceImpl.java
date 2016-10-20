package biz.belcorp.ssicc.service.spusicc.zon.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.zon.MantenimientoZONUnidadGeograficaDAO;
import biz.belcorp.ssicc.dao.spusicc.zon.model.UnidadGeografica;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.zon.MantenimientoZONUnidadGeograficaService;

/**
 * @author Aurelio Oviedo
 *
 */
@Service("spusicc.mantenimientoZONUnidadGeograficaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoZONUnidadGeograficaServiceImpl extends BaseService implements MantenimientoZONUnidadGeograficaService {
	
	@Resource(name="spusicc.mantenimientoZONUnidadGeograficaDAO")
	private MantenimientoZONUnidadGeograficaDAO mantenimientoZONUnidadGeograficaDAO;

	public List getUnidadesGeograficasList(Map criteria) {
		return mantenimientoZONUnidadGeograficaDAO.getUnidadesGeograficasList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadGeograficaService#getUnidadesGeograficasByCriteria(java.util.Map)
	 */
	public List getUnidadesGeograficasByCriteria(Map criteria) {
		return mantenimientoZONUnidadGeograficaDAO.getUnidadesGeograficasByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadGeograficaService#getEstructuraGeopoliticaList()
	 */
	public List getEstructuraGeopoliticaList() {
		return mantenimientoZONUnidadGeograficaDAO.getEstructuraGeopoliticaList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadGeograficaService#insertUnidadGeografica(biz.belcorp.ssicc.spusicc.zon.model.UnidadGeografica, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertUnidadGeografica(UnidadGeografica ug, Usuario usuario) throws Exception{
		
		Map criteria = new HashMap();
		
		// Validaciones de precedencia
		if(StringUtils.isNotBlank(ug.getNivel2()))
		{			
			criteria.put("orde1", ug.getNivel1());
			
			//Verificar que exista el registro padre
			List lista = getUnidadesGeograficasByCriteria(criteria);
			
			if(lista == null || lista.size() == 0)
				throw new InvalidIdentifierException(UnidadGeografica.class, this.getKeyMessage("unidadGeografica.error.padre.noexiste"));
		}

		if(StringUtils.isNotBlank(ug.getNivel3()))
		{			
			criteria.put("orde1", ug.getNivel1());
			criteria.put("orde2", ug.getNivel2());
			
			//Verificar que exista el registro padre
			List lista = getUnidadesGeograficasByCriteria(criteria);
			
			if(lista == null || lista.size() == 0)
				throw new InvalidIdentifierException(UnidadGeografica.class, this.getKeyMessage("unidadGeografica.error.padre.noexiste"));
		}

		if(StringUtils.isNotBlank(ug.getNivel4()))
		{			
			criteria.put("orde1", ug.getNivel1());
			criteria.put("orde2", ug.getNivel2());
			criteria.put("orde3", ug.getNivel3());
			
			//Verificar que exista el registro padre
			List lista = getUnidadesGeograficasByCriteria(criteria);
			
			if(lista == null || lista.size() == 0)
				throw new InvalidIdentifierException(UnidadGeografica.class, this.getKeyMessage("unidadGeografica.error.padre.noexiste"));
		}
		//
		
		//Validaciones de codigos duplicados
		criteria.put("orde1", ug.getNivel1());
		criteria.put("orde2", ug.getNivel2());
		criteria.put("orde3", ug.getNivel3());
		criteria.put("orde4", ug.getNivel4());
		
		List lista = getUnidadesGeograficasByCriteria(criteria);
		
		if(lista != null && lista.size() > 0)
			throw new InvalidIdentifierException(UnidadGeografica.class, this.getKeyMessage("unidadGeografica.error.codigo.yaexiste"));			
		//
		ug.setIndicadorActualizar("");
		validarDescripcionesDuplicadas(ug);
		
		Integer codigo = mantenimientoZONUnidadGeograficaDAO.getCodigoSiguienteUnidadGeografica();
		ug.setCodigoOrdenEstruGeopo(codigo.toString());
		mantenimientoZONUnidadGeograficaDAO.insertUnidadGeografica(ug, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadGeograficaService#updateUnidadGeografica(biz.belcorp.ssicc.spusicc.zon.model.UnidadGeografica, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateUnidadGeografica(UnidadGeografica ug, Usuario usuario) throws Exception{
		ug.setIndicadorActualizar(Constants.SI);
		String descripcion = ug.getDescripcion();
		String descripcionActual = ug.getDescripcionActual();
		if (StringUtils.isBlank(descripcionActual)) {
			validarDescripcionesDuplicadas(ug);
		}
		else {
			if (!StringUtils.equals(descripcion, descripcionActual)) {
				validarDescripcionesDuplicadas(ug);
			}
		}
		mantenimientoZONUnidadGeograficaDAO.updateUnidadGeografica(ug, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadGeograficaService#deleteUnidadGeografica(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteUnidadGeografica(String oid, Usuario usuario)
			throws Exception {
		
		Map criteria = new HashMap();
		criteria.put("oid", oid);
		List lista = getUnidadesGeograficasByCriteria(criteria);
		
		if(lista != null && lista.size() > 0)
		{
			UnidadGeografica ug = (UnidadGeografica)lista.get(0);
			int nroRegistros = 0;
			if(StringUtils.isNotBlank(ug.getNivel1()) && StringUtils.isBlank(ug.getNivel2()))
			{
				//Validar si hay registros de nivel2
				criteria.put("nivel", Constants.NUMERO_DOS);
				criteria.put("nivel1", ug.getNivel1());
				
				nroRegistros = mantenimientoZONUnidadGeograficaDAO.getCantidadUnidadesGeograficasByNivel(criteria);
			}
			else if(StringUtils.isNotBlank(ug.getNivel1()) && StringUtils.isNotBlank(ug.getNivel2()) && StringUtils.isBlank(ug.getNivel3()))
			{
				//Validar si hay registros de nivel3
				criteria.put("nivel", Constants.NUMERO_TRES);
				criteria.put("nivel1", ug.getNivel1());
				criteria.put("nivel2", ug.getNivel2());
				
				nroRegistros = mantenimientoZONUnidadGeograficaDAO.getCantidadUnidadesGeograficasByNivel(criteria);
			}
			else if(StringUtils.isNotBlank(ug.getNivel1()) && StringUtils.isNotBlank(ug.getNivel2()) && StringUtils.isNotBlank(ug.getNivel3()) && StringUtils.isBlank(ug.getNivel4()))
			{
				//Validar si hay registros de nivel4
				criteria.put("nivel", Constants.NUMERO_CUATRO);
				criteria.put("nivel1", ug.getNivel1());
				criteria.put("nivel2", ug.getNivel2());
				criteria.put("nivel3", ug.getNivel3());
				
				nroRegistros = mantenimientoZONUnidadGeograficaDAO.getCantidadUnidadesGeograficasByNivel(criteria);
			}
			else if(StringUtils.isNotBlank(ug.getNivel1()) && StringUtils.isNotBlank(ug.getNivel2()) && StringUtils.isNotBlank(ug.getNivel3()) && StringUtils.isNotBlank(ug.getNivel4()))
			{
				//Validar si hay registros de nivel5
				criteria.put("nivel", Constants.NUMERO_CINCO);
				criteria.put("nivel1", ug.getNivel1());
				criteria.put("nivel2", ug.getNivel2());
				criteria.put("nivel3", ug.getNivel3());
				criteria.put("nivel4", ug.getNivel4());

				nroRegistros = mantenimientoZONUnidadGeograficaDAO.getCantidadUnidadesGeograficasByNivel(criteria);
			}
			
			if(nroRegistros > 0)
				throw new InvalidIdentifierException(UnidadGeografica.class, "unidadGeografica.error.existen.niveles.inferiores");
			
			
			
			//Validar si tiene territorios
			List listaTerritorio = mantenimientoZONUnidadGeograficaDAO.getCantidadTerritoriosByUnidadGeografica(ug.getOidValorEstructuraGeo());
			
			if (listaTerritorio != null && listaTerritorio.size()>0) {
				
				for (int i = 0; i < listaTerritorio.size(); i++) {
					Base b = (Base)listaTerritorio.get(i);

					if (StringUtils.equals(b.getDescripcion(), Constants.NUMERO_UNO)) {
						//Eliminacin Lgica
						mantenimientoZONUnidadGeograficaDAO.removeUnidadGeografica(ug, usuario);
					}else {
						throw new InvalidIdentifierException(UnidadGeografica.class, "unidadGeografica.error.existen.territorios");
					}
				}
				
			}else {
				//Eliminacin Fisica
				mantenimientoZONUnidadGeograficaDAO.deleteUnidadGeografica(ug, usuario);
			}
			
		}		
	}
	
	/**
	 * Realiza el proceso de validaciones de las Descripciones
	 * @param ug
	 * @throws Exception
	 */
	private void validarDescripcionesDuplicadas(UnidadGeografica ug) throws Exception
	{
		Map criteria = new HashMap();
		List lista = null;
		criteria.put("estado", Constants.ZON_ESTADO_ACTIVO);
		
		if (StringUtils.isNotBlank(ug.getIndicadorActualizar())) {
			criteria.put("indicadorActualizar",ug.getIndicadorActualizar() );
			criteria.put("oidUnidadGeografica", ug.getOidValorEstructuraGeo());
		}
		
		//Validaciones de descripciones duplicadas
		if(StringUtils.isNotBlank(ug.getNivel1()) && StringUtils.isBlank(ug.getNivel2()) && StringUtils.isBlank(ug.getNivel3()) && StringUtils.isBlank(ug.getNivel4())){
			criteria.put("descripcionNivel1", ug.getDescripcion());
			
			lista = getUnidadesGeograficasByCriteria(criteria);

			if(lista != null && lista.size() > 0)
				throw new InvalidIdentifierException(UnidadGeografica.class, this.getKeyMessage("unidadGeografica.error.descripcion.yaexiste"));
		}
		else if(StringUtils.isNotBlank(ug.getNivel1()) && StringUtils.isNotBlank(ug.getNivel2()) && StringUtils.isBlank(ug.getNivel3()) && StringUtils.isBlank(ug.getNivel4())){
			criteria.put("descripcionNivel2", ug.getDescripcion());
			criteria.put("nivel1", ug.getNivel1());
			criteria.put("nivel2", ug.getNivel2());
			lista = this.getUnidadesGeograficasValidar(criteria);

			if(lista != null && lista.size() > 0)
				throw new InvalidIdentifierException(UnidadGeografica.class, this.getKeyMessage("unidadGeografica.error.descripcion.yaexiste"));
		}
		else if(StringUtils.isNotBlank(ug.getNivel1()) && StringUtils.isNotBlank(ug.getNivel2()) && StringUtils.isNotBlank(ug.getNivel3()) && StringUtils.isBlank(ug.getNivel4())){
			criteria.put("descripcionNivel3", ug.getDescripcion());
			criteria.put("nivel1", ug.getNivel1());
			criteria.put("nivel2", ug.getNivel2());
			criteria.put("nivel3", ug.getNivel3());
			
			lista = this.getUnidadesGeograficasValidar(criteria);

			if(lista != null && lista.size() > 0)
				throw new InvalidIdentifierException(UnidadGeografica.class, this.getKeyMessage("unidadGeografica.error.descripcion.yaexiste"));
		}
		else if(StringUtils.isNotBlank(ug.getNivel1()) && StringUtils.isNotBlank(ug.getNivel2()) && StringUtils.isNotBlank(ug.getNivel3()) && StringUtils.isNotBlank(ug.getNivel4())){
			criteria.put("descripcionNivel4", ug.getDescripcion());
			criteria.put("nivel1", ug.getNivel1());
			criteria.put("nivel2", ug.getNivel2());
			criteria.put("nivel3", ug.getNivel3());
			criteria.put("nivel4", ug.getNivel4());
			
			lista = this.getUnidadesGeograficasValidar(criteria);

			if(lista != null && lista.size() > 0)
				throw new InvalidIdentifierException(UnidadGeografica.class, this.getKeyMessage("unidadGeografica.error.descripcion.yaexiste"));
			
		}
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.zon.service.MantenimientoZONUnidadGeograficaService#getUnidadesGeograficasValidar(java.util.Map)
	 */
	public List getUnidadesGeograficasValidar(Map criteria) {
		return mantenimientoZONUnidadGeograficaDAO.getUnidadesGeograficasValidar(criteria);
	}
}