package biz.belcorp.ssicc.service.spusicc.flexipago.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoDAO;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.EstatusRechazoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.EstatusRecomendacionFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoRegionFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.GrupoVariableFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.MotivoRechazoFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.MotivoRecomendacionFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.ParametroFLX;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.RangoLDC;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoService;
import biz.belcorp.ssicc.service.util.ExcelUtil;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
@Service("spusicc.mantenimientoFLXModeloOtorgamientoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoFLXModeloOtorgamientoServiceImpl extends BaseService implements MantenimientoFLXModeloOtorgamientoService {
	
	@Resource(name = "spusicc.mantenimientoFLXModeloOtorgamientoDAO")
	private MantenimientoFLXModeloOtorgamientoDAO mantenimientoFLXModeloOtorgamientoDAO;



	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getVariables(java.lang.String)
	 */
	public List getVariables(String tipoVariable) {
		return this.mantenimientoFLXModeloOtorgamientoDAO.getVariables(tipoVariable);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#insertGrupo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.GrupoFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertGrupo(GrupoFLX grupo, Usuario usuario)  {
		//Validamos que no existe uno igual descripcion
    	List grupos = mantenimientoFLXModeloOtorgamientoDAO.getGrupos(grupo.getDescripcion());
    	
        if (grupos != null && grupos.size() > 0) {
            throw new InvalidDescriptionException(GrupoFLX.class, grupo.getDescripcion());
        }
		//
		
		mantenimientoFLXModeloOtorgamientoDAO.insertGrupo(grupo, usuario);
		String codigoGrupo = mantenimientoFLXModeloOtorgamientoDAO.getIdGrupo();
		
		List variables = grupo.getVariables();
		
		if(variables != null && variables.size() > 0)
		{
			for(int i=0; i<variables.size(); i++)
			{
				GrupoVariableFLX grupoVariable = (GrupoVariableFLX)variables.get(i);
				grupoVariable.setCodigoGrupo(codigoGrupo);
				mantenimientoFLXModeloOtorgamientoDAO.insertGrupoVariable(grupoVariable, usuario);
			}
		}
		
		//Versionamos el modelo
		updateVersionModelo(usuario);
		//
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#updateGrupo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.GrupoFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateGrupo(GrupoFLX grupo, Usuario usuario) {
		
		boolean flagVersionar = false;
		
		//Validamos que no se este sobreescribiendo otro grupo
    	List grupos = mantenimientoFLXModeloOtorgamientoDAO.getGrupos(grupo.getDescripcion());
        
        if (grupos != null && grupos.size() > 0) {
            for (int i = 0; i < grupos.size(); i++) {
                GrupoFLX o = (GrupoFLX) grupos.get(i);
                if (!o.getCodigo().equals(grupo.getCodigo())) {
                    throw new InvalidDescriptionException(GrupoFLX.class, grupo.getDescripcion());
                }
            }
        }
		//
		
        GrupoFLX grupoExistente = mantenimientoFLXModeloOtorgamientoDAO.getGrupo(grupo.getCodigo());
        double valorExistente = Double.parseDouble(grupoExistente.getValorConstante());
        double valorNuevo = Double.parseDouble(grupo.getValorConstante());
        
        if(!StringUtils.equals(grupoExistente.getDescripcion(), grupo.getDescripcion()) || valorExistente != valorNuevo)
        {
        	flagVersionar = true;
            
    		mantenimientoFLXModeloOtorgamientoDAO.updateGrupo(grupo, usuario);
        }
		
		List variables = grupo.getVariables();
		
		if(variables != null && variables.size() > 0)
		{
			for(int i=0; i<variables.size(); i++)
			{
				GrupoVariableFLX grupoVariable = (GrupoVariableFLX)variables.get(i);
				
				if(StringUtils.isNotBlank(grupoVariable.getCodigo())){
					
					GrupoVariableFLX gvExistente = mantenimientoFLXModeloOtorgamientoDAO.getGrupoVariable(grupoVariable.getCodigo());
					
					valorExistente = Double.parseDouble(gvExistente.getValorPeso());
					valorNuevo = Double.parseDouble(grupoVariable.getValorPeso());
					
					if(!StringUtils.equals(gvExistente.getValorPeso(), grupoVariable.getValorPeso()))
					{
						flagVersionar = true;
						mantenimientoFLXModeloOtorgamientoDAO.updateGrupoVariable(grupoVariable, usuario);
					}					
					
				}else{
					flagVersionar = true;
					mantenimientoFLXModeloOtorgamientoDAO.insertGrupoVariable(grupoVariable, usuario);
				}
			}
		}
		
		if(flagVersionar)
		{
			//Versionamos el modelo
			updateVersionModelo(usuario);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getGrupos(java.lang.String)
	 */
	public List getGrupos(String descripcion) {
		return this.mantenimientoFLXModeloOtorgamientoDAO.getGrupos(descripcion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getGrupo(java.lang.String)
	 */
	public GrupoFLX getGrupo(String id) {		
		GrupoFLX grupo = this.mantenimientoFLXModeloOtorgamientoDAO.getGrupo(id);
		List variables = this.mantenimientoFLXModeloOtorgamientoDAO.getVariablesGrupo(Constants.FLX_TIPO_VARIABLE_CALCULO_PROBABILIDAD_INCUMPLIMIENTO, grupo.getCodigo());
		grupo.setVariables(variables);
		
		return grupo;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#removeGrupo(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeGrupo(String id, Usuario usuario) {
		GrupoFLX grupo = this.mantenimientoFLXModeloOtorgamientoDAO.getGrupo(id);
		grupo.setEstado(Constants.ESTADO_INACTIVO);
		mantenimientoFLXModeloOtorgamientoDAO.updateGrupo(grupo, usuario);
		
		updateVersionModelo(usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getGruposRegiones(java.lang.String)
	 */
	public List getGruposRegiones(String codigoGrupo) {
		return this.mantenimientoFLXModeloOtorgamientoDAO.getGruposRegiones(codigoGrupo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getRegionesAgrupadas(java.lang.String, boolean)
	 */
	public List getRegionesAgrupadas(String codigoGrupo, boolean flagAgrupadas) {
		return this.mantenimientoFLXModeloOtorgamientoDAO.getRegionesAgrupadas(codigoGrupo, flagAgrupadas);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#updateRegionesGrupo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.GrupoFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRegionesGrupo(GrupoFLX grupo, Usuario usuario) {

		boolean flagVersionar = false;
		
		//Obtenemos la cantidad de regiones que hay en el grupoactualmente
		List regionesActuales = mantenimientoFLXModeloOtorgamientoDAO.getRegionesByGrupo(grupo.getCodigo());

		List regiones = grupo.getRegiones();

		if(regionesActuales == null)
			regionesActuales = new ArrayList();
		if(regiones  == null)
			regiones = new ArrayList();
		
		if(regiones.size() != regionesActuales.size())
			flagVersionar = true;
		
		//Eliminamos todas las regiones asignadas al grupo
		mantenimientoFLXModeloOtorgamientoDAO.removeRegionesGrupo(grupo.getCodigo(), usuario);
		
		//Insertamos las nuevas regiones			
		if(regiones != null && regiones.size() > 0)
		{
			for(int i=0; i< regiones.size(); i++)
			{
				GrupoRegionFLX grupoRegion = (GrupoRegionFLX)regiones.get(i);
				grupoRegion.setEstado(Constants.ESTADO_ACTIVO);
				mantenimientoFLXModeloOtorgamientoDAO.insertRegionGrupo(grupoRegion, usuario);
			}
		}
		
		if(flagVersionar)
			updateVersionModelo(usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getParametrosByGrupo(java.lang.String)
	 */
	public List getParametrosByGrupo(String codigoGrupo) {
		return mantenimientoFLXModeloOtorgamientoDAO.getParametrosByGrupo(codigoGrupo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#updateParametros(java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateParametros(List parametros, Usuario usuario) {
		
		boolean flagVersionar = false;
		
		if(parametros != null && parametros.size() > 0){
			for (int i = 0; i < parametros.size(); i++) {
				ParametroFLX parametro = (ParametroFLX)parametros.get(i); 
				ParametroFLX parametroExistente = mantenimientoFLXModeloOtorgamientoDAO.getParametro(parametro.getCodigo());
				
				if(!StringUtils.equals(parametro.getValorParametro(), parametroExistente.getValorParametro()))
				{
					flagVersionar = true;
					
					mantenimientoFLXModeloOtorgamientoDAO.updateParametro(parametro, usuario);
				}
			}
		}
		
		if(flagVersionar)
		{
			updateVersionModelo(usuario);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getMotivosRechazoByCriteria(java.util.Map)
	 */
	public List getMotivosRechazoByCriteria(Map criteria) {
		return mantenimientoFLXModeloOtorgamientoDAO.getMotivosRechazoByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#removeMotivoRechazo(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeMotivoRechazo(String codigo, Usuario usuario) {
		MotivoRechazoFLX motivo = mantenimientoFLXModeloOtorgamientoDAO.getMotivoRechazo(codigo);
		motivo.setEstado(Constants.ESTADO_INACTIVO);
		mantenimientoFLXModeloOtorgamientoDAO.updateMotivoRechazo(motivo, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getMotivoRechazo(java.lang.String)
	 */
	public MotivoRechazoFLX getMotivoRechazo(String id) {
		return mantenimientoFLXModeloOtorgamientoDAO.getMotivoRechazo(id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#insertMotivoRechazo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.MotivoRechazoFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMotivoRechazo(MotivoRechazoFLX motivo, Usuario usuario) {
        // Verificamos que no exista un motivo con el mismo codigo
    	Map criteria = new HashMap();
    	criteria.put("codigoMotivo", motivo.getCodigoMotivo());
    	
    	List motivos = mantenimientoFLXModeloOtorgamientoDAO.getMotivosRechazoByCriteria(criteria);
        
    	if(motivos != null && motivos.size() > 0)
    		throw new InvalidIdentifierException(MotivoRechazoFLX.class, motivo.getCodigoMotivo());
        
        // Verificamos que no exista un motivo con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
    	criteria.clear();
    	criteria.put("descripcion", motivo.getDescripcion());

    	motivos = mantenimientoFLXModeloOtorgamientoDAO.getMotivosRechazoByCriteria(criteria);
    	
        if (motivos != null && motivos.size() > 0) {
            throw new InvalidDescriptionException(MotivoRechazoFLX.class, motivo.getDescripcion());
        }
        // Seteamos los valores por defecto
        
        mantenimientoFLXModeloOtorgamientoDAO.insertMotivoRechazo(motivo, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#updateMotivoRechazo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.MotivoRechazoFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMotivoRechazo(MotivoRechazoFLX motivo, Usuario usuario) {
        // Verificamos que no exista un motivo con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
		
    	Map criteria = new HashMap();
    	criteria.put("descripcion", motivo.getDescripcion());

    	List motivos = mantenimientoFLXModeloOtorgamientoDAO.getMotivosRechazoByCriteria(criteria);
                
        if (motivos != null && motivos.size() > 0) {
            for (int i = 0; i < motivos.size(); i++) {
                MotivoRechazoFLX o = (MotivoRechazoFLX) motivos.get(i);
                if (!o.getCodigoMotivo().equals(motivo.getCodigoMotivo())) {
                    throw new InvalidDescriptionException(MotivoRechazoFLX.class, motivo.getDescripcion());
                }
            }
        }
        
        motivo.setEstado(Constants.ESTADO_ACTIVO);
        mantenimientoFLXModeloOtorgamientoDAO.updateMotivoRechazo(motivo, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getMotivosRecomendacionByCriteria(java.util.Map)
	 */
	public List getMotivosRecomendacionByCriteria(Map criteria) {
		return mantenimientoFLXModeloOtorgamientoDAO.getMotivosRecomendacionByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#removeMotivoRecomendacion(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeMotivoRecomendacion(String codigo, Usuario usuario) {
		MotivoRecomendacionFLX motivo = mantenimientoFLXModeloOtorgamientoDAO.getMotivoRecomendacion(codigo);
		motivo.setEstado(Constants.ESTADO_INACTIVO);
		mantenimientoFLXModeloOtorgamientoDAO.updateMotivoRecomendacion(motivo, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getMotivoRecomendacion(java.lang.String)
	 */
	public MotivoRecomendacionFLX getMotivoRecomendacion(String id) {
		return mantenimientoFLXModeloOtorgamientoDAO.getMotivoRecomendacion(id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#insertMotivoRecomendacion(biz.belcorp.ssicc.spusicc.flexipago.dao.model.MotivoRecomendacionFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertMotivoRecomendacion(MotivoRecomendacionFLX motivo, Usuario usuario) {
		// Verificamos que no exista un motivo con el mismo codigo
    	Map criteria = new HashMap();
    	criteria.put("codigoMotivo", motivo.getCodigoMotivo());
    	
    	List motivos = mantenimientoFLXModeloOtorgamientoDAO.getMotivosRecomendacionByCriteria(criteria);
        
    	if(motivos != null && motivos.size() > 0)
    		throw new InvalidIdentifierException(MotivoRecomendacionFLX.class, motivo.getCodigoMotivo());
        
        // Verificamos que no exista un motivo con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
    	criteria.clear();
    	criteria.put("descripcion", motivo.getDescripcion());

    	motivos = mantenimientoFLXModeloOtorgamientoDAO.getMotivosRecomendacionByCriteria(criteria);
    	
        if (motivos != null && motivos.size() > 0) {
            throw new InvalidDescriptionException(MotivoRecomendacionFLX.class, motivo.getDescripcion());
        }
        // Seteamos los valores por defecto
        
        mantenimientoFLXModeloOtorgamientoDAO.insertMotivoRecomendacion(motivo, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#updateMotivoRecomendacion(biz.belcorp.ssicc.spusicc.flexipago.dao.model.MotivoRecomendacionFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateMotivoRecomendacion(MotivoRecomendacionFLX motivo, Usuario usuario) {
		// Verificamos que no exista un motivo con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
		
    	Map criteria = new HashMap();
    	criteria.put("descripcion", motivo.getDescripcion());

    	List motivos = mantenimientoFLXModeloOtorgamientoDAO.getMotivosRecomendacionByCriteria(criteria);
                
        if (motivos != null && motivos.size() > 0) {
            for (int i = 0; i < motivos.size(); i++) {
                MotivoRecomendacionFLX o = (MotivoRecomendacionFLX) motivos.get(i);
                if (!o.getCodigoMotivo().equals(motivo.getCodigoMotivo())) {
                    throw new InvalidDescriptionException(MotivoRecomendacionFLX.class, motivo.getDescripcion());
                }
            }
        }
        
        motivo.setEstado(Constants.ESTADO_ACTIVO);
        mantenimientoFLXModeloOtorgamientoDAO.updateMotivoRecomendacion(motivo, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getEstatusRecomendacionByCriteria(java.util.Map)
	 */
	public List getEstatusRecomendacionByCriteria(Map criteria) {
		return mantenimientoFLXModeloOtorgamientoDAO.getEstatusRecomendacionByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#removeEstatusRecomendacion(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeEstatusRecomendacion(String codigo, Usuario usuario) {
		EstatusRecomendacionFLX estatus = mantenimientoFLXModeloOtorgamientoDAO.getEstatusRecomendacion(codigo);
		estatus.setEstado(Constants.ESTADO_INACTIVO);
		mantenimientoFLXModeloOtorgamientoDAO.updateEstatusRecomendacion(estatus, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getEstatusRecomendacion(java.lang.String)
	 */
	public EstatusRecomendacionFLX getEstatusRecomendacion(String id) {
		return mantenimientoFLXModeloOtorgamientoDAO.getEstatusRecomendacion(id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#insertEstatusRecomendacion(biz.belcorp.ssicc.spusicc.flexipago.dao.model.EstatusRecomendacionFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEstatusRecomendacion(EstatusRecomendacionFLX estatus, Usuario usuario) {
		// Verificamos que no exista un estatus con el mismo codigo
    	Map criteria = new HashMap();
    	criteria.put("codigoEstatus", estatus.getCodigoEstatus());
    	
    	List estatusList = mantenimientoFLXModeloOtorgamientoDAO.getEstatusRecomendacionByCriteria(criteria);
        
    	if(estatusList != null && estatusList.size() > 0)
    		throw new InvalidIdentifierException(EstatusRecomendacionFLX.class, estatus.getCodigoEstatus());
        
        // Verificamos que no exista un estatus con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
    	criteria.clear();
    	criteria.put("descripcion", estatus.getDescripcion());

    	estatusList = mantenimientoFLXModeloOtorgamientoDAO.getEstatusRecomendacionByCriteria(criteria);
    	
        if (estatusList != null && estatusList.size() > 0) {
            throw new InvalidDescriptionException(EstatusRecomendacionFLX.class, estatus.getDescripcion());
        }
        // Seteamos los valores por defecto
        
        mantenimientoFLXModeloOtorgamientoDAO.insertEstatusRecomendacion(estatus, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#updateEstatusRecomendacion(biz.belcorp.ssicc.spusicc.flexipago.dao.model.EstatusRecomendacionFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstatusRecomendacion(EstatusRecomendacionFLX estatus, Usuario usuario) {
		// Verificamos que no exista un estatus con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
		
    	Map criteria = new HashMap();
    	criteria.put("descripcion", estatus.getDescripcion());

    	List estatusList = mantenimientoFLXModeloOtorgamientoDAO.getEstatusRecomendacionByCriteria(criteria);
                
        if (estatusList != null && estatusList.size() > 0) {
            for (int i = 0; i < estatusList.size(); i++) {
            	EstatusRecomendacionFLX o = (EstatusRecomendacionFLX) estatusList.get(i);
                if (!o.getCodigoEstatus().equals(estatus.getCodigoEstatus())) {
                    throw new InvalidDescriptionException(EstatusRecomendacionFLX.class, estatus.getDescripcion());
                }
            }
        }
        
        estatus.setEstado(Constants.ESTADO_ACTIVO);
        mantenimientoFLXModeloOtorgamientoDAO.updateEstatusRecomendacion(estatus, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getEstatusRechazoByCriteria(java.util.Map)
	 */
	public List getEstatusRechazoByCriteria(Map criteria) {
		return mantenimientoFLXModeloOtorgamientoDAO.getEstatusRechazoByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#removeEstatusRechazo(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void removeEstatusRechazo(String codigo, Usuario usuario) {
		EstatusRechazoFLX estatus = mantenimientoFLXModeloOtorgamientoDAO.getEstatusRechazo(codigo);
		estatus.setEstado(Constants.ESTADO_INACTIVO);
		mantenimientoFLXModeloOtorgamientoDAO.updateEstatusRechazo(estatus, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getEstatusRechazo(java.lang.String)
	 */
	public EstatusRechazoFLX getEstatusRechazo(String id) {
		return mantenimientoFLXModeloOtorgamientoDAO.getEstatusRechazo(id);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#insertEstatusRechazo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.EstatusRechazoFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertEstatusRechazo(EstatusRechazoFLX estatus, Usuario usuario) {
		// Verificamos que no exista un estatus con el mismo codigo
    	Map criteria = new HashMap();
    	criteria.put("codigoEstatus", estatus.getCodigoEstatus());
    	
    	List estatusList = mantenimientoFLXModeloOtorgamientoDAO.getEstatusRechazoByCriteria(criteria);
        
    	if(estatusList != null && estatusList.size() > 0)
    		throw new InvalidIdentifierException(EstatusRechazoFLX.class, estatus.getCodigoEstatus());
        
        // Verificamos que no exista un estatus con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
    	criteria.clear();
    	criteria.put("descripcion", estatus.getDescripcion());

    	estatusList = mantenimientoFLXModeloOtorgamientoDAO.getEstatusRechazoByCriteria(criteria);
    	
        if (estatusList != null && estatusList.size() > 0) {
            throw new InvalidDescriptionException(EstatusRechazoFLX.class, estatus.getDescripcion());
        }
        // Seteamos los valores por defecto
        
        mantenimientoFLXModeloOtorgamientoDAO.insertEstatusRechazo(estatus, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#updateEstatusRechazo(biz.belcorp.ssicc.spusicc.flexipago.dao.model.EstatusRechazoFLX, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateEstatusRechazo(EstatusRechazoFLX estatus, Usuario usuario) {
		// Verificamos que no exista un estatus con la misma descripcin
        // Creamos el bean que nos servir como criterio de busqueda
		
    	Map criteria = new HashMap();
    	criteria.put("descripcion", estatus.getDescripcion());

    	List estatusList = mantenimientoFLXModeloOtorgamientoDAO.getEstatusRechazoByCriteria(criteria);
                
        if (estatusList != null && estatusList.size() > 0) {
            for (int i = 0; i < estatusList.size(); i++) {
            	EstatusRechazoFLX o = (EstatusRechazoFLX) estatusList.get(i);
                if (!o.getCodigoEstatus().equals(estatus.getCodigoEstatus())) {
                    throw new InvalidDescriptionException(EstatusRechazoFLX.class, estatus.getDescripcion());
                }
            }
        }
        
        estatus.setEstado(Constants.ESTADO_ACTIVO);
        mantenimientoFLXModeloOtorgamientoDAO.updateEstatusRechazo(estatus, usuario);
	}
	
	private void updateVersionModelo(Usuario usuario)
	{
		if(log.isDebugEnabled())
			log.debug("Generando Nueva Version del Modelo...");
		
		ParametroFLX parametro = mantenimientoFLXModeloOtorgamientoDAO.getParametro(Constants.FLX_CODIGO_PARAMETRO_VERSION);
					
		Double version = Double.parseDouble(parametro.getValorParametro());
		version = version + 0.1;			
		BigDecimal bd = BigDecimal.valueOf(version);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
		parametro.setValorParametro(bd.toString());

		if(log.isDebugEnabled())
			log.debug("Version: " + parametro.getValorParametro());
		
		mantenimientoFLXModeloOtorgamientoDAO.updateParametro(parametro, usuario);
		
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getZonasExcluidasByCriteria(java.util.Map)
	 */
	public List getZonasExcluidasByCriteria(Map params) {
		return mantenimientoFLXModeloOtorgamientoDAO.getZonasExcluidasByCriteria(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#removeZonaExcluida(java.util.Map)
	 */
	public void removeZonaExcluida(Map params) {
		mantenimientoFLXModeloOtorgamientoDAO.removeZonaExcluida(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#insertZonaExcluida(java.util.Map, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertZonaExcluida(Map zonaExcluida, Usuario usuario) {
		
		String zonas[] = (String[])MapUtils.getObject(zonaExcluida, "codigoZona");
		
		if(zonas != null)
		{
			for(int i=0; i<zonas.length; i++)
			{
				zonaExcluida.put("codigoZona", zonas[i]);
				
		    	List zonasExistentes = mantenimientoFLXModeloOtorgamientoDAO.getZonasExcluidasByCriteria(zonaExcluida);
		    	
		        if (zonasExistentes != null && zonasExistentes.size() > 0) {
		            throw new InvalidDescriptionException(Base.class, zonas[i]);
		        }
				
		        zonaExcluida.put("codigoUsuario", usuario.getLogin());
		        
				mantenimientoFLXModeloOtorgamientoDAO.insertZonaExcluida(zonaExcluida);
			}
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#getRangosLDC()
	 */
	public List getRangosLDC() {
		return mantenimientoFLXModeloOtorgamientoDAO.getRangosLDC();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#updateRangosLDC(java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateRangosLDC(List rangos, Usuario usuario) {
		boolean flagVersionar = false;
		
		if(rangos != null && rangos.size() > 0){
			for (int i = 0; i < rangos.size(); i++) {
				RangoLDC rangoLDC = (RangoLDC)rangos.get(i);
				
				RangoLDC rangoExistente = mantenimientoFLXModeloOtorgamientoDAO.getRangoLDC(rangoLDC.getOid());
				
				if(!StringUtils.equals(rangoLDC.getValorDesde(), rangoExistente.getValorDesde()) ||
						!StringUtils.equals(rangoLDC.getValorHasta(), rangoExistente.getValorHasta()) ||
						!StringUtils.equals(rangoLDC.getValorFactor(), rangoExistente.getValorFactor()))
				{
					flagVersionar = true;
					
					mantenimientoFLXModeloOtorgamientoDAO.updateRangoLDC(rangoLDC, usuario);
				}
			}
		}
		
		if(flagVersionar)
		{
			updateVersionModelo(usuario);
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#insertRangoLDC(biz.belcorp.ssicc.spusicc.flexipago.dao.model.RangoLDC, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRangoLDC(RangoLDC rango, Usuario usuario) {
		rango.setEstado(Constants.ESTADO_ACTIVO);
		mantenimientoFLXModeloOtorgamientoDAO.insertRangoLDC(rango, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#deleteRangoLDC(java.lang.String, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteRangoLDC(String oidRango, Usuario usuario) {
		RangoLDC rangoExistente = mantenimientoFLXModeloOtorgamientoDAO.getRangoLDC(oidRango);
		rangoExistente.setEstado(Constants.ESTADO_INACTIVO);
		mantenimientoFLXModeloOtorgamientoDAO.updateRangoLDC(rangoExistente, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#cargarArchivoLineasCreditoXLS(java.util.Map)
	 */
	public int cargarArchivoLineasCreditoXLS(Map criteria) throws Exception {
        String directorioTemporal = (String)criteria.get("directorioTemporal");
        String nombreArchivo = (String)criteria.get("nombreArchivo");
        Usuario usuario = (Usuario)criteria.get("usuario");
        
        mantenimientoFLXModeloOtorgamientoDAO.deleteTemporalLineasCredito(usuario.getLogin());
        
        ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
        excelUtil.initSheet(0);
                
        int fila = 0;
        Map params = new HashMap();
        params.put("codigoUsuario", usuario.getLogin());
        params.put("codigoPais", MapUtils.getString(criteria, "codigoPais"));
        params.put("codigoPeriodo", MapUtils.getString(criteria, "codigoPeriodo"));
        
        while(excelUtil.hasNext()){
            Map mapRow = excelUtil.next();
            fila++;
            
            if(fila == 1)
            	mapRow = excelUtil.next();
            
            String codigoCliente = StringUtils.remove((String)mapRow.get("0"), ".0");
            String lineaCredito = (String)mapRow.get("1");
                        
            params.put("codigoCliente", StringUtils.trim(codigoCliente));
            params.put("lineaCredito", StringUtils.trim(lineaCredito));
            params.put("numeroFila", fila);
            
            mantenimientoFLXModeloOtorgamientoDAO.insertTemporalLineasCredito(params);
        }
        excelUtil.cerrar();
        
        return fila;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#executeValidarArchivoLineasCredito(java.util.Map)
	 */
	public List executeValidarArchivoLineasCredito(Map params) {
		mantenimientoFLXModeloOtorgamientoDAO.executeValidarArchivoLineasCredito(params);
		List lista = mantenimientoFLXModeloOtorgamientoDAO.getErroresArchivoLineasCredito(params);
				
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#executeProcesarArchivoLineasCredito(java.util.Map)
	 */
	public void executeProcesarArchivoLineasCredito(Map criteria) {
		mantenimientoFLXModeloOtorgamientoDAO.executeProcesarArchivoLineasCredito(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#cargarArchivoConsultorasDeshabilitarXLS(java.util.Map)
	 */
	public int cargarArchivoConsultorasDeshabilitarXLS(Map criteria) throws Exception{
        String directorioTemporal = (String)criteria.get("directorioTemporal");
        String nombreArchivo = (String)criteria.get("nombreArchivo");
        Usuario usuario = (Usuario)criteria.get("usuario");
        
        mantenimientoFLXModeloOtorgamientoDAO.deleteTemporalConsultorasDeshabilitar(usuario.getLogin());
        
        ExcelUtil excelUtil = new ExcelUtil(directorioTemporal, nombreArchivo);
        excelUtil.initSheet(0);
                
        int fila = 0;
        Map params = new HashMap();
        params.put("codigoUsuario", usuario.getLogin());
        params.put("codigoPais", MapUtils.getString(criteria, "codigoPais"));
        params.put("codigoPeriodo", MapUtils.getString(criteria, "codigoPeriodo"));
        
        while(excelUtil.hasNext()){
            Map mapRow = excelUtil.next();
            fila++;
            
            if(fila == 1)
            	mapRow = excelUtil.next();
            
            String codigoCliente = StringUtils.remove((String)mapRow.get("0"), ".0");
            
            params.put("codigoCliente", StringUtils.trim(codigoCliente));
            params.put("numeroFila", fila);
            
            mantenimientoFLXModeloOtorgamientoDAO.insertTemporalConsultorasDeshabilitar(params);
        }
        excelUtil.cerrar();
        
        return fila;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#executeValidarArchivoConsultorasDeshabilitar(java.util.Map)
	 */
	public List executeValidarArchivoConsultorasDeshabilitar(Map params) {
		mantenimientoFLXModeloOtorgamientoDAO.executeValidarArchivoConsultorasDeshabilitar(params);
		List lista = mantenimientoFLXModeloOtorgamientoDAO.getErroresArchivoConsultorasDeshabilitar(params);
				
		return lista;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.flexipago.service.MantenimientoFLXModeloOtorgamientoService#executeProcesarArchivoConsultorasDeshabilitar(java.util.Map)
	 */
	public void executeProcesarArchivoConsultorasDeshabilitar(Map criteria) {
		mantenimientoFLXModeloOtorgamientoDAO.executeProcesarArchivoConsultorasDeshabilitar(criteria);
	}

}