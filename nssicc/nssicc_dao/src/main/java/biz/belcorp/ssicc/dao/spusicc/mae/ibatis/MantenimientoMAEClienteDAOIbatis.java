package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteAdicional;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteClasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacionOperadora;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteConcursoPremio;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteDireccion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteEncuesta;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoDatos;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoEstatus;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteIdentificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteMarca;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteObservacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClientePreferenciaComunicacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClientePrimerContacto;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteReferencias;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteSubTipo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteTipoLogro;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteUnidadAdministrativa;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteVinculo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ExcencionFlete;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ExcencionSobreFlete;
import biz.belcorp.ssicc.dao.spusicc.mae.model.HistoricoClasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.SegmentoGrupoLove;

/**
 * Implementacion del DAO que ejecutara los metodos de insercion de Clientes
 * <p>
 * <a href="MantenimientoMAEClienteDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("spusicc.mantenimientoMAEClienteDAO")
public class MantenimientoMAEClienteDAOIbatis extends BaseDAOiBatis implements MantenimientoMAEClienteDAO {

	public List getPeriodosVigentesByPaisMarcaCanal(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getPeriodosVigentesByPaisMarcaCanal", criteria);
		
	}

	public List getSubTiposClienteInsertar(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getSubTiposClienteInsertar", criteria);
		
	}
	
    public String getTipoClasificacionDefault(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getTipoClasificacionDefault", criteria);
    }
    
    public String getClasificacionDefault(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getClasificacionDefault", criteria);
    }
    
    public String getOidPais(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getOidPais", criteria);
    }	

    public String getLongitudCodigoCliente(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getLongitudCodigoCliente", criteria);
    }		

    public String getLongitudCodigoClien(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getLongitudCodigoClien", criteria);
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getLongitudNumeroDocIdentidad(java.util.Map)
     */
    public String getLongitudNumeroDocIdentidad(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getLongitudNumeroDocIdentidad", criteria);
    }

	public List getEstadosCiviles(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getEstadosCiviles", criteria);
		
	}

	public List getTratamientos(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getTratamientos", criteria);
	}

	public List getNivelEstudios(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getNivelEstudios", criteria);
	}

	public List getNacionalidades(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getNacionalidades", criteria);
	}
	
	public List getTiposDocumentoIdentidad(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getTiposDocumentoIdentidad", criteria);
		
	}

    public String getTipoDocumentoObligatorio(String oidPais) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getTipoDocumentoObligatorio", oidPais);
    }	

    public String getTipoDocumentoDuplaCyzone(String oidPais) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getTipoDocumentoDuplaCyzone", oidPais);
    }	
    
	public List getSexos(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getSexos", criteria);
		
	}

	public List getTiposVias(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getTiposVias", criteria);
		
	}
	
	public List getUnidadesGeograficas(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getUnidadesGeograficas", criteria);
		
	}
	
	public List getNivelesGeograficos(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getNivelesGeograficos", criteria);
		
	}

	public List getDocumentosCliente(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getDocumentosCliente", criteria);
		
	}

    public String getNumeroDocumentoPrincipal(String oidCliente) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getNumeroDocumentoPrincipal", oidCliente);
    }	
	
	public List getMarcasTipoAbonoEntrada(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getMarcasTipoAbonoEntrada", criteria);
	}
	
    public String getCuentasCastigadasCliente(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getCuentasCastigadasCliente", criteria);
    }	

    public boolean esPaisModulo10(String codigoPais) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.MantenimientoMAESQL.getPaisModulo10", codigoPais);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }	

    public boolean esTipoDocumentoModulo10(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.MantenimientoMAESQL.getTipoDocumentoModulo10", criteria);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }	
    
    public String getCodigoClienteAutomatico(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getCodigoClienteAutomatico", criteria);
    }	
    
	public List getPesosModulo11() {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getPesosModulo11");
	}
    
    public String getUltimoNumeroSolicitud(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getUltimoNumeroSolicitud", criteria);
    }	

	public void updateUltimoNumeroSolicitud(Map criteria) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateUltimoNumeroSolicitud", criteria);
		
	}

	public String getExisteZona(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getExisteZona", criteria);

	}
	
	public String getExisteTerritorio(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getExisteTerritorio", criteria);
	}

	public String getExisteTerritorioEnZona(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getExisteTerritorioEnZona", criteria);
	}

	public String getExisteCodigoCliente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getExisteCodigoCliente", criteria);
	}

	public List getTiposClasificaciones(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getTiposClasificaciones", criteria);
	}

	public List getClasificaciones(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getClasificaciones", criteria);
	}

	public Long getOidMarca(String codigoMarca) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidMarca", codigoMarca);
	}

	public Long getOidCanal(String codigoCanal) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidCanal", codigoCanal);
	}
	
	public Long getOidFormaPagoPais(String oidPais) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidFormaPagoPais", oidPais);
	}

	public Long getOidFormaPagoSubTipoCliente(Map criteria) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidFormaPagoSubTipoCliente", criteria);
	}
	
	public String getValorConfiCampoSubTipoCliente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getValorConfiCampoSubTipoCliente", criteria);
	}
	
	public Long getOidTipoDireccion(String codigoTipoDireccion) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidTipoDireccion", codigoTipoDireccion);
	}
	
	public Long getOidTipoComunicacion(String codigoTipoComunicacion) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidTipoComunicacion", codigoTipoComunicacion);
	}

	public Long getOidTipoVinculo(Map criteria) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidTipoVinculo", criteria);
	}
	
	public String getCriterioBusqueda1(String oidPais) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getCriterioBusqueda1", oidPais);
	}

	public String getCriterioBusqueda2(String oidPais) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getCriterioBusqueda2", oidPais);
	}

	public String getClienteVinculoDuplaCyzone(String codigoTipoVinculo, String oidClienteVinculante, String fechaActual) {
		Map criteria = new HashMap();
		criteria.put("codigoTipoVinculo", codigoTipoVinculo);
		criteria.put("oidClienteVinculante", oidClienteVinculante);
		criteria.put("fechaActual", fechaActual);
		
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getClienteVinculoDuplaCyzone", criteria);
	}
	
	public void updateFechaHastaClienteVinculo(String oidClienteVinculo) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateFechaHastaClienteVinculo", oidClienteVinculo);
	}
	
	public List getClienteSubTipo(String codigoTipoCliente, String oidCliente) {
		Map criteria = new HashMap();
		criteria.put("codigoTipoCliente", codigoTipoCliente);
		criteria.put("oidCliente", oidCliente);
		
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getClienteSubTipo", criteria);
	}

	public String getOidTipoClasificacion(String oidSubTipoCliente, String codigoTipoClasificacion) {
		Map criteria = new HashMap();
		criteria.put("oidSubTipoCliente", oidSubTipoCliente);
		criteria.put("codigoTipoClasificacion", codigoTipoClasificacion);
		
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidTipoClasificacion", criteria);
	}

	public String getOidClasificacion(String oidTipoClasificacion, String codigoClasificacion) {
		Map criteria = new HashMap();
		criteria.put("oidTipoClasificacion", oidTipoClasificacion);
		criteria.put("codigoClasificacion", codigoClasificacion);
		
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidClasificacion", criteria);
	}

	public String getOidClienteClasificacion(String oidClienteSubTipo, String oidTipoClasificacion) {
		Map criteria = new HashMap();
		criteria.put("oidClienteSubTipo", oidClienteSubTipo);
		criteria.put("oidTipoClasificacion", oidTipoClasificacion);
		
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidClienteClasificacion", criteria);
	}
	
	public Map getCodigoUbigeo(String oidTerritorio) {
		List resultado = getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getCodigoUbigeo",oidTerritorio);
		return (Map)resultado.get(0);
	}	

	public Long getOidClienteTipoSubTipoMigracion(String oidPais) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidClienteTipoSubTipoMigracion", oidPais);
	}

	public String getLongitudTipoDocumento(String oidPais, String oidTipoDocumento) {
		Map criteria = new HashMap();
		criteria.put("oidPais", oidPais);
		criteria.put("oidTipoDocumento", oidTipoDocumento);
		
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getLongitudTipoDocumento", criteria);
	}
	
	public Long getSecuenciaNextValueCliente() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getSecuenciaNextValueCliente");
	}

	public Long getSecuenciaNextValueSubTipo() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getSecuenciaNextValueSubTipo");
	}

	public Long getSecuenciaNextValueClasificacion() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getSecuenciaNextValueClasificacion");
	}

	public Long getSecuenciaNextValueHistoricoStatus() {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getSecuenciaNextValueHistoricoStatus");
	}

	public void deleteMensajesDuplaCyzone(Map criteria){
		 getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteMensajesDuplaCyzone", criteria);
	}
	
	public void insertCliente(Cliente cliente) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertCliente", cliente);
	}

	public void insertClienteAdicional(ClienteAdicional clienteAdicional) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClienteAdicional", clienteAdicional);
	}

	public void insertClientePrimerContacto(ClientePrimerContacto clientePrimerContacto) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClientePrimerContacto", clientePrimerContacto);
	}

	public void insertClienteSubTipo(ClienteSubTipo clienteSubTipo) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClienteSubTipo", clienteSubTipo);
	}

	public void insertClienteClasificacion(ClienteClasificacion clienteClasificacion) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClienteClasificacion", clienteClasificacion);
	}

	public void insertClienteIdentificacion(ClienteIdentificacion clienteIdentificacion) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClienteIdentificacion", clienteIdentificacion);
	}

	public void insertClienteDireccion(ClienteDireccion clienteDireccion) {
		String indicadorCamposAdicionales = clienteDireccion.getIndicadorCamposAdicionales();		
		if (indicadorCamposAdicionales == null || indicadorCamposAdicionales.equals(Constants.NO))
			getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClienteDireccion", clienteDireccion);
		else		
			getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClienteDireccionCamposAdicionalesActivo", clienteDireccion);
		
	}

	public void insertClienteUnidadAdministrativa(ClienteUnidadAdministrativa clienteUnidadAdministrativa) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClienteUnidadAdministrativa", clienteUnidadAdministrativa);
	}

	public void insertClienteComunicacion(ClienteComunicacion clienteComunicacion) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClienteComunicacion", clienteComunicacion);
	}

	public void insertClienteVinculo(ClienteVinculo clienteVinculo) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClienteVinculo", clienteVinculo);
	}

	public void insertClienteMarca(ClienteMarca clienteMarca) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClienteMarca", clienteMarca);
	}

	public void insertClienteObservacion(ClienteObservacion clienteObservacion) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClienteObservacion", clienteObservacion);
	}

	public void insertClienteHistoricoEstatus(ClienteHistoricoEstatus clienteHistoricoEstatus) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertClienteHistoricoEstatus", clienteHistoricoEstatus);
	}

	public void insertHistoricoClasificacion(HistoricoClasificacion historicoClasificacion) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertHistoricoClasificacion", historicoClasificacion);
	}
	
	public List getClientesByCriteria(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getClientesByCriteria", criteria);
	}

	public List getDireccionesClientesByCriteria(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getDireccionesClientesByCriteria", criteria);
	}

	public Cliente getExisteCodigoClienteByCodPais(Map criteria) {
		return (Cliente) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getExisteCodigoClienteByCodPais", criteria);
	}
	
	public String getEdadMinima(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getEdadMinima", criteria);
	}
	
	public String getEdadMaxima(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getEdadMaxima", criteria);
	}
	
	public String getExisteMensajeBuzon(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getExisteMensajeBuzon", criteria);
	}
	
	public void insertBuzonMensajes(Map criteria) {
	    getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertBuzonMensajes", criteria);
	}

	public List getConcursos(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getConcursos", criteria);
	}
	
	public List getPremios(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getPremios", criteria);
	}
	
	public void insertRecomendante(ClienteConcursoPremio clienteConcursoPremio) {
	    getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertRecomendante", clienteConcursoPremio);
	}
	
	public String getOidRecomendante(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidRecomendante", criteria);
	}
	
	public void insertRecomendado(ClienteConcursoPremio clienteConcursoPremio) {
	    getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertRecomendado", clienteConcursoPremio);
	}
	
	public String getOidRecomendado(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidRecomendado", criteria);
	}
	
	public List getSituaciones(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getSituaciones", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getTiposDireccion(java.util.Map)
	 */
	public List getTiposDireccion(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getTiposDireccion", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getNivelesRiesgo(java.util.Map)
	 */
	public List getNivelesRiesgo(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getNivelesRiesgo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getNivelRiesgoCliente(java.util.Map)
	 */
	public Map getNivelRiesgoCliente(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getNivelRiesgoCliente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getFechaUltimaActualizacionClienteAdicional(java.util.Map)
	 */
	public String getFechaUltimaActualizacionClienteAdicional(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getFechaUltimaActualizacionClienteAdicional", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getFechaUltimaCreacionNivelRiesgoCliente(java.util.Map)
	 */
	public String getFechaUltimaCreacionNivelRiesgoCliente(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getFechaUltimaCreacionNivelRiesgoCliente", criteria);
	}

	/**
	 * @param criteria
	 */
	public void insertClienteNivelRiesgo(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertClienteNivelRiesgo", criteria);
	}

	/**
	 * @param criteria
	 */
	public void updateNivelRiesgoClienteAdicional(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateNivelRiesgoClienteAdicional", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getZonasByPaisMarcaCanal(java.util.Map)
	 */
	public List getZonasByPaisMarcaCanal(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getZonasByPaisMarcaCanal", criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getTerritoriosByPaisMarcaCanalZona(java.util.Map)
	 */
	public List getTerritoriosByPaisMarcaCanalZona(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getTerritoriosByPaisMarcaCanalZona", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getListClientesByCriteria(java.util.Map)
	 */
	public List getListClientesByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getListClientesByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getDatosBasicosCliente(java.lang.String, java.lang.String)
	 */
	public Cliente getDatosBasicosCliente(String codigoPais,
			String codigoCliente) {
		Map map = new HashMap();
		map.put("codigoPais",codigoPais);
		map.put("codigoCliente", codigoCliente);
		return (Cliente) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getDatosBasicosCliente",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getDatosAdicionalesCliente(java.lang.String)
	 */
	public ClienteAdicional getDatosAdicionalesCliente(String oidCliente) {
		return (ClienteAdicional) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getDatosAdicionalesCliente", oidCliente);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getPrimerContactoCliente(java.lang.String)
	 */
	public ClientePrimerContacto getPrimerContactoCliente(String oidCliente) {
		return (ClientePrimerContacto) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getPrimerContactoCliente", oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getListClasificacionCliente(java.lang.String)
	 */
	public List getListClasificacionCliente(String oidClienteSubTipo) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getListClasificacionCliente", oidClienteSubTipo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getListIdentificacionCliente(java.lang.String)
	 */
	public List getListIdentificacionCliente(String oidCliente) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getListIdentificacionCliente", oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getListTipoSubtipoCliente(java.lang.String)
	 */
	public List getListTipoSubtipoCliente(String oidCliente) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getListTipoSubtipoCliente", oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getComunicacionCliente(java.lang.String)
	 */
	public List getListComunicacionCliente(String oidCliente) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getListComunicacionCliente", oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getListDireccionCliente(java.lang.String)
	 */
	public List getListDireccionCliente(String oidCliente) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getListDireccionCliente", oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getListObservacionCliente(java.lang.String)
	 */
	public List getListObservacionCliente(String oidCliente) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getListObservacionCliente", oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getListVinculoCliente(java.lang.String)
	 */
	public List getListVinculoCliente(String oidCliente) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getListVinculoCliente", oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getUnidadAdministrativaCliente(java.lang.String)
	 */
	public ClienteUnidadAdministrativa getUnidadAdministrativaCliente(
			String oidCliente) {
		return (ClienteUnidadAdministrativa) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getUnidadAdministrativaCliente", oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateDatosAdicionalesCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteAdicional)
	 */
	public void updateDatosAdicionalesCliente(ClienteAdicional clienteAdicional) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDatosAdicionalesCliente",clienteAdicional);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateDatosBasicosCliente(biz.belcorp.ssicc.spusicc.mae.model.Cliente)
	 */
	public void updateDatosBasicosCliente(Cliente cliente) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDatosBasicosCliente",cliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updatePrimerContactoCliente(biz.belcorp.ssicc.spusicc.mae.model.ClientePrimerContacto)
	 */
	public void updatePrimerContactoCliente(
			ClientePrimerContacto clientePrimerContacto) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updatePrimerContactoCliente",clientePrimerContacto);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#deleteClasificacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteClasificacion)
	 */
	public void deleteClasificacionCliente(
			ClienteClasificacion clienteClasificacion) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteClasificacionCliente",clienteClasificacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#deleteIdentificacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteIdentificacion)
	 */
	public void deleteIdentificacionCliente(
			ClienteIdentificacion clienteIdentificacion) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteIdentificacionCliente",clienteIdentificacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#deleteTipoSubtipoCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteSubTipo)
	 */
	public void deleteTipoSubtipoCliente(ClienteSubTipo clienteSubTipo) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteTipoSubtipoCliente",clienteSubTipo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateClasificacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteClasificacion)
	 */
	public void updateClasificacionCliente(
			ClienteClasificacion clienteClasificacion) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateClasificacionCliente",clienteClasificacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateIdentificacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteIdentificacion)
	 */
	public void updateIdentificacionCliente(
			ClienteIdentificacion clienteIdentificacion) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateIdentificacionCliente",clienteIdentificacion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateTipoSubtipoCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteSubTipo)
	 */
	public void updateTipoSubtipoCliente(ClienteSubTipo clienteSubTipo) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateTipoSubtipoCliente",clienteSubTipo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#deleteDireccionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteDireccion)
	 */
	public void deleteDireccionCliente(ClienteDireccion clienteDireccion) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.deleteDireccionCliente",clienteDireccion);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateComunicacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacion)
	 */
	public void updateComunicacionCliente(
			ClienteComunicacion clienteComunicacion) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateComunicacionCliente",clienteComunicacion);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateDireccionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteDireccion)
	 */
	public void updateDireccionCliente(ClienteDireccion clienteDireccion) {
		String indicadorCamposAdicionales = clienteDireccion.getIndicadorCamposAdicionales();
		if (indicadorCamposAdicionales == null || indicadorCamposAdicionales.equals(Constants.NO) )
			getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDireccionCliente",clienteDireccion);	
		else
			getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDireccionClienteCamposAdicionales",clienteDireccion);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateObservacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteObservacion)
	 */
	public void updateObservacionCliente(ClienteObservacion clienteObservacion) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateObservacionCliente",clienteObservacion);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateUnidadAdministrativaCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteUnidadAdministrativa)
	 */
	public void updateUnidadAdministrativaCliente(
			ClienteUnidadAdministrativa clienteUnidadAdministrativa) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateUnidadAdministrativaCliente",clienteUnidadAdministrativa);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateVinculoCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteVinculo)
	 */
	public void updateVinculoCliente(ClienteVinculo clienteVinculo) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateVinculoCliente",clienteVinculo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getDevuelveOidCampanha(java.util.Map)
	 */
	public String getDevuelveOidCampanha(Map map) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getDevuelveOidCampanha", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#deleteComunicacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacion)
	 */
	public void deleteComunicacionCliente(ClienteComunicacion clienteComunicacion) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteComunicacionCliente",clienteComunicacion);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public List getPeriodosByPaisMarcaCanal(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getPeriodosByPaisMarcaCanal", criteria);
		
	}

    /**
     * @param criteria
     * @return
     */
    public boolean esTipoDocumentoSiglaRUC(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.MantenimientoMAESQL.getTipoDocumentoSiglaRUC", criteria);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }	

    /**
     * @param criteria
     * @return
     */
    public boolean esClienteHaFacturado(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.MantenimientoMAESQL.getClienteHaFacturado", criteria);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }	

    /**
     * @param criteria
     * @return
     */
    public boolean esRegionCerradaxZona(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.MantenimientoMAESQL.getRegionCerradaxZona", criteria);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }	

    /**
     * @param criteria
     * @return
     */
    public Base getSiguientePeriodo(Map criteria) {
        return (Base) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getSiguientePeriodo", criteria);
    }	

    /**
     * @param criteria
     * @return
     */
    public boolean esPaisCalculaPeriodoIngreso(String codigoPais) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.MantenimientoMAESQL.getPaisCalculaPeriodoIngreso", codigoPais);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }	

    /**
     * @param criteria
     * @return
     */
    public String getFechaInicioPeriodo(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getFechaInicioPeriodo", criteria);
    }	
    
	/**
	 * @param codigoPais
	 * @param tipoValidacion
	 * @return
	 */
	public String getValorModuloxPaisTipoValidacion(String codigoPais, String tipoValidacion) {
		Map criteria = new HashMap();
		criteria.put("codigoPais",codigoPais);
		criteria.put("tipoValidacion", tipoValidacion);
		
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getValorModuloxPaisTipoValidacion",criteria);
	}
	/**
	 * @param clienteConcursoPremio
	 */
	public void updateRecomendado(ClienteConcursoPremio clienteConcursoPremio) {
	    getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.updateRecomendado", clienteConcursoPremio);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getTipoVinculo()
	 */
	public List getTipoVinculo() {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getTipoVinculo");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertClienteReferenciaAval(biz.belcorp.ssicc.spusicc.mae.model.ClienteReferencias)
	 */
	public void insertClienteReferenciaAval(ClienteReferencias clienteReferencias) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertClienteReferenciaAval", clienteReferencias);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertClienteReferenciaFamiliar(biz.belcorp.ssicc.spusicc.mae.model.ClienteReferencias)
	 */
	public void insertClienteReferenciaFamiliar(ClienteReferencias clienteReferencias) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertClienteReferenciaFamiliar", clienteReferencias);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertClienteReferenciaNoFamiliar(biz.belcorp.ssicc.spusicc.mae.model.ClienteReferencias)
	 */
	public void insertClienteReferenciaNoFamiliar(ClienteReferencias clienteReferencias) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertClienteReferenciaNoFamiliar", clienteReferencias);		
	}

	public List getListClienteReferencia(Map map) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getListClienteReferencia",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateReferenciasFamiliar(biz.belcorp.ssicc.spusicc.mae.model.ClienteReferencias)
	 */
	public void updateReferenciasFamiliar(ClienteReferencias clienteReferencias) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateReferenciasFamiliar",clienteReferencias);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateReferenciasNoFamiliar(biz.belcorp.ssicc.spusicc.mae.model.ClienteReferencias)
	 */
	public void updateReferenciasNoFamiliar(ClienteReferencias clienteReferencias) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateReferenciasNoFamiliar",clienteReferencias);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateReferenciasAval(biz.belcorp.ssicc.spusicc.mae.model.ClienteReferencias)
	 */
	public void updateReferenciasAval(ClienteReferencias clienteReferencias) {
			getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateReferenciasAval",clienteReferencias);		
	}

	/**
	 * @param clienteReferencias
	 */
	public void deleteReferencias(ClienteReferencias clienteReferencias) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteReferencias",clienteReferencias);
	}

    /**
     * @param criteria
     * @return
     */
    public boolean esZonaCerrada(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.MantenimientoMAESQL.getZonaCerrada", criteria);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }	

	/**
	 * @param codigoTipoVia
	 * @return
	 */
	public Long getOidTipoVia(String codigoTipoVia) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidTipoVia", codigoTipoVia);
	}

	/**
	 * @param oidClienteVinculo
	 */
	public void updateIndicadorActivoClienteVinculo(String oidClienteVinculo) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateIndicadorActivoClienteVinculo", oidClienteVinculo);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public List getOidsRecomendante(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getOidsRecomendante", criteria);
	}

	/**
	 * @param criteria
	 */
	public void deleteRecomendados(Map criteria){
		 getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteRecomendados", criteria);
	}

	/**
	 * @param oidRecomendante
	 * @return
	 */
	public String getTotalRecomendados(String oidRecomendante) {
        return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getTotalRecomendados", oidRecomendante);
    }
	
	/**
	 * @param oidRecomendante
	 */
	public void deleteRecomendante(String oidRecomendante){
		 getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteRecomendante", oidRecomendante);
	}

    /**
     * @param criteria
     * @return
     */
    public boolean esClienteHaFacturadoPeriodos(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.MantenimientoMAESQL.getClienteHaFacturadoPeriodos", criteria);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }	

	/**
	 * @param oidCliente
	 * @return
	 */
	public String getUltimoPeriodoFacturado(String oidCliente) {
        return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getUltimoPeriodoFacturado", oidCliente);
    }

	/**
	 * @param criteria
	 */
	public void updatePedidosConNuevaUnidadAdministrativa(Map criteria) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updatePedidosConNuevaUnidadAdministrativa", criteria);
	}
	
	/**
	 * @param criteria
	 * @return
	 */
	public ClienteHistoricoEstatus getHistoricoEstatusPeriodoFin(Map criteria) {
		return (ClienteHistoricoEstatus) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getHistoricoEstatusPeriodoFin", criteria);
	}

	/**
	 * @param criteria
	 * @return
	 */
	public ClienteHistoricoEstatus getHistoricoEstatusPeriodoInicio(Map criteria) {
		return (ClienteHistoricoEstatus) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getHistoricoEstatusPeriodoInicio", criteria);
	}

	/**
	 * @param oidHistoricoEstatus
	 */
	public void deleteHistoricoClasificacion(String oidHistoricoEstatus) {
		 getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteHistoricoClasificacion", oidHistoricoEstatus);
	}
	
	/**
	 * @param oidHistoricoEstatus
	 */
	public void deleteHistoricoEstatus(String oidHistoricoEstatus) {
		 getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteHistoricoEstatus", oidHistoricoEstatus);
	}
	
	/**
	 * @param criteria
	 */
	public void updateHistoricoEstatus(Map criteria) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateHistoricoEstatus", criteria);
	}

	/**
	 * @param criteria
	 */
	public void updateDatosAdicionalesEstatus(Map criteria) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDatosAdicionalesEstatus", criteria);
	}

    /**
     * @param criteria
     * @return
     */
    public Base getPeriodoAnterior(Map criteria) {
        return (Base) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getPeriodoAnterior", criteria);
    }	
    
    /**
     * @param criteria
     * @return
     */
    public boolean esPeriodoCerrado(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.MantenimientoMAESQL.getPeriodoCerrado", criteria);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }	

	/**
	 * @param criteria
	 */
	public void deleteUnidadAdministrativaPeriodoInicio(String oidCliente, String oidPeriodo) {
		Map criteria = new HashMap();
		criteria.put("oidCliente", oidCliente);
		criteria.put("oidPeriodo", oidPeriodo);
		
		getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteUnidadAdministrativaPeriodoInicio", criteria);
	}

	/**
	 * @param oidCliente
	 * @param oidPeriodoFin
	 * @return
	 */
	public ClienteUnidadAdministrativa getUnidadAdministrativaClientexPeriodoFin(String oidCliente, String oidPeriodoFin) {
		Map criteria = new HashMap();
		criteria.put("oidCliente", oidCliente);
		criteria.put("oidPeriodoFin", oidPeriodoFin);

		return (ClienteUnidadAdministrativa) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getUnidadAdministrativaClientexPeriodoFin", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeValidacionDeudoraConsultoraAval(java.util.Map)
	 */
	public void executeValidacionDeudoraConsultoraAval(Map criteria) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeValidacionDeudoraConsultoraAval", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getListConsultorasDeudorasAval(java.util.Map)
	 */
	public List getListConsultorasDeudorasAval(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getListConsultorasDeudorasAval", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getSizePedidosEnviados(java.util.Map)
	 */
	public Integer getSizePedidosEnviados(Map map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getSizePedidosEnviados", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeRedifinirPeriodoIngreso(java.util.Map)
	 */
	public void executeRedifinirPeriodoIngreso(Map params) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeRedifinirPeriodoIngreso",params);
		
	}

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#esClientePotencialAval(java.lang.String)
     */
    public Long getClientePotencialAval(String codigoCliente) {
        Long result = (Long) getSqlMapClientTemplate().queryForObject(
        							"spusicc.MantenimientoMAESQL.getClientePotencialAval", codigoCliente);
        
       	return result;
    }	

	public void deleteClasificaciones(Long oidSubTipoCliente) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteClasificaciones",oidSubTipoCliente);
	}

	public void deleteSubTipoCliente(Long oidSubTipoCliente) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteSubTipoCliente",oidSubTipoCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertClienteAval(java.util.Map)
	 */
	public void insertClienteAval(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertClienteAval", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getSegundoTipoDocumento(java.lang.String)
	 */
	public String getSegundoTipoDocumento(String oidPais) {
		return (String) getSqlMapClientTemplate().queryForObject(
    		"spusicc.MantenimientoMAESQL.getSegundoTipoDocumento", oidPais);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExistePreferenciaComunicacion(biz.belcorp.ssicc.spusicc.mae.model.ClientePreferenciaComunicacion)
	 */
	public ClientePreferenciaComunicacion getExistePreferenciaComunicacion(
			ClientePreferenciaComunicacion preferenciaComun) {
		return (ClientePreferenciaComunicacion) getSqlMapClientTemplate().queryForObject(
	    		"spusicc.MantenimientoMAESQL.getExistePreferenciaComunicacion", preferenciaComun);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertPreferencia(biz.belcorp.ssicc.spusicc.mae.model.ClientePreferenciaComunicacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertPreferencia(
			ClientePreferenciaComunicacion preferenciaComun, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertPreferencia", preferenciaComun);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updatePreferencia(biz.belcorp.ssicc.spusicc.mae.model.ClientePreferenciaComunicacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePreferencia(
			ClientePreferenciaComunicacion preferenciaComun, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updatePreferencia", preferenciaComun);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExistePreferenciaClienteComunicacion(biz.belcorp.ssicc.spusicc.mae.model.ClientePreferenciaComunicacion)
	 */
	public Integer getExistePreferenciaClienteComunicacion(
			ClientePreferenciaComunicacion preferenciaComun) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
	    		"spusicc.MantenimientoMAESQL.getExistePreferenciaClienteComunicacion", preferenciaComun);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertPreferenciaCliente(biz.belcorp.ssicc.spusicc.mae.model.ClientePreferenciaComunicacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertPreferenciaCliente(
			ClientePreferenciaComunicacion preferenciaComun, Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertPreferenciaCliente", preferenciaComun);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updatePreferenciaCliente(biz.belcorp.ssicc.spusicc.mae.model.ClientePreferenciaComunicacion, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updatePreferenciaCliente(
			ClientePreferenciaComunicacion preferenciaComun, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updatePreferenciaCliente", preferenciaComun);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExisteComunicacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacionOperadora)
	 */
	public Integer getExisteComunicacionCliente(
			ClienteComunicacionOperadora comunicacionOperadora) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
	    		"spusicc.MantenimientoMAESQL.getExisteComunicacionCliente", comunicacionOperadora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExisteTipoOperadora(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacionOperadora)
	 */
	public ClienteComunicacionOperadora getExisteTipoOperadora(
			ClienteComunicacionOperadora comunicacionOperadora) {
		return (ClienteComunicacionOperadora) getSqlMapClientTemplate().queryForObject(
	    		"spusicc.MantenimientoMAESQL.getExisteTipoOperadora", comunicacionOperadora);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertComunicacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacionOperadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertComunicacionCliente(
			ClienteComunicacionOperadora comunicacionOperadora,
			Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertComunicacionCliente", comunicacionOperadora);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertTipoOperadora(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacionOperadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertTipoOperadora(
			ClienteComunicacionOperadora comunicacionOperadora,
			Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertTipoOperadora", comunicacionOperadora);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateComunicacionCliente(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacionOperadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateComunicacionCliente(
			ClienteComunicacionOperadora comunicacionOperadora,
			Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateComunicacionClienteOperadora", comunicacionOperadora);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateTipoOperadora(biz.belcorp.ssicc.spusicc.mae.model.ClienteComunicacionOperadora, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateTipoOperadora(
			ClienteComunicacionOperadora comunicacionOperadora,
			Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateTipoOperadora", comunicacionOperadora);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExisteTipoLogro(biz.belcorp.ssicc.spusicc.mae.model.ClienteTipoLogro)
	 */
	public ClienteTipoLogro getExisteTipoLogro(ClienteTipoLogro clientTipoLogro) {
		return (ClienteTipoLogro) getSqlMapClientTemplate().queryForObject(
	    		"spusicc.MantenimientoMAESQL.getExisteTipoLogro", clientTipoLogro);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertTipoLogro(biz.belcorp.ssicc.spusicc.mae.model.ClienteTipoLogro, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertTipoLogro(ClienteTipoLogro clientTipoLogro,
			Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertTipoLogro", clientTipoLogro);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateTipoLogro(biz.belcorp.ssicc.spusicc.mae.model.ClienteTipoLogro, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateTipoLogro(ClienteTipoLogro clientTipoLogro,
			Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateTipoLogro", clientTipoLogro);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExisteGrupoLove(biz.belcorp.ssicc.spusicc.mae.model.SegmentoGrupoLove)
	 */
	public SegmentoGrupoLove getExisteGrupoLove(
			SegmentoGrupoLove segmentoGrupoLove) {
		return (SegmentoGrupoLove) getSqlMapClientTemplate().queryForObject(
	    		"spusicc.MantenimientoMAESQL.getExisteGrupoLove", segmentoGrupoLove);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExisteSegmentoGrupoLove(biz.belcorp.ssicc.spusicc.mae.model.SegmentoGrupoLove)
	 */
	public SegmentoGrupoLove getExisteSegmentoGrupoLove(
			SegmentoGrupoLove segmentoGrupoLove) {
		return (SegmentoGrupoLove) getSqlMapClientTemplate().queryForObject(
	    		"spusicc.MantenimientoMAESQL.getExisteSegmentoGrupoLove", segmentoGrupoLove);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertGrupoLove(biz.belcorp.ssicc.spusicc.mae.model.SegmentoGrupoLove, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertGrupoLove(SegmentoGrupoLove segmentoGrupoLove,
			Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertGrupoLove", segmentoGrupoLove);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertSegmentoGrupoLove(biz.belcorp.ssicc.spusicc.mae.model.SegmentoGrupoLove, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertSegmentoGrupoLove(SegmentoGrupoLove segmentoGrupoLove,
			Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertSegmentoGrupoLove", segmentoGrupoLove);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateGrupoLove(biz.belcorp.ssicc.spusicc.mae.model.SegmentoGrupoLove, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateGrupoLove(SegmentoGrupoLove segmentoGrupoLove,
			Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateGrupoLove", segmentoGrupoLove);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateSegmentoGrupoLove(biz.belcorp.ssicc.spusicc.mae.model.SegmentoGrupoLove, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateSegmentoGrupoLove(SegmentoGrupoLove segmentoGrupoLove,
			Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateSegmentoGrupoLove", segmentoGrupoLove);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExisteClienteEncuesta(biz.belcorp.ssicc.spusicc.mae.model.ClienteEncuesta)
	 */
	public Integer getExisteClienteEncuesta(ClienteEncuesta clienteEncuesta) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
	    		"spusicc.MantenimientoMAESQL.getExisteClienteEncuesta", clienteEncuesta);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertClienteEncuesta(biz.belcorp.ssicc.spusicc.mae.model.ClienteEncuesta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertClienteEncuesta(ClienteEncuesta clienteEncuesta,
			Usuario usuario) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertClienteEncuesta", clienteEncuesta);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateClienteEncuesta(biz.belcorp.ssicc.spusicc.mae.model.ClienteEncuesta, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateClienteEncuesta(ClienteEncuesta clienteEncuesta,
			Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateClienteEncuesta", clienteEncuesta);
		
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getNivelRiesgo(java.util.Map)
	 */
	public String getNivelRiesgo(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getNivelRiesgo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getCaracteresxModuloValidacion(java.util.Map)
	 */
	public List getCaracteresxModuloValidacion(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getCaracteresxModuloValidacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeRedifinirPeriodoIngresoRetiradas(java.util.Map)
	 */
	public void executeRedifinirPeriodoIngresoRetiradas(Map params) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeRedifinirPeriodoIngresoRetiradas",params);
		
	}

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#esZonaOficina(java.util.Map)
     */
    public boolean esZonaOficina(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.MantenimientoMAESQL.getZonaOficina", criteria);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }	
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#esZonaActiva(java.util.Map)
     */
    public boolean esZonaInactiva(Map criteria) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.MantenimientoMAESQL.getZonaInactiva", criteria);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getValidacionVigenciaRecomendacion(java.util.Map)
	 */
	public String getValidacionVigenciaRecomendacion(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getValidacionVigenciaRecomendacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeRehacerTablasIncentivos(java.util.Map)
	 */
	public void executeRehacerTablasIncentivos(Map criteria) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeRehacerTablasIncentivos", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeDeleteRecomendados(java.util.Map)
	 */
	public void executeDeleteRecomendados(Map criteria) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeDeleteRecomendados", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeDeleteRecomendante(java.lang.String)
	 */
	public void executeDeleteRecomendante(String oidRecomendante) {
		 Map criteria = new HashMap();
		 criteria.put("oidClienteRecomendante", oidRecomendante);
		 
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeDeleteRecomendante", criteria);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getPeriodoRecomendacion(java.util.Map)
	 */
	public String getPeriodoRecomendacion(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getPeriodoRecomendacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getDocumentoIdentidad(java.lang.String)
	 */
	public String getDocumentoIdentidad(String oidCliente) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getDocumentoIdentidad", oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getRegionesDirectorio(java.util.Map)
	 */
	public List getRegionesDirectorio(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getRegionesDirectorio", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getRegionesDirectorioCargo(java.util.Map)
	 */
	public List getRegionesDirectorioCargo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getRegionesDirectorioCargo", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getRegionesDirectorioCargoGZ(java.util.Map)
	 */
	public List getRegionesDirectorioCargoGZ(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getRegionesDirectorioCargoGZ", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getZonasDirectorio(java.util.Map)
	 */
	public List getZonasDirectorio(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getZonasDirectorio", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getZonasDirectorioCargo(java.util.Map)
	 */
	public List getZonasDirectorioCargo(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getZonasDirectorioCargo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getTipoCutis(java.util.Map)
	 */
	public List getTipoCutis(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getTipoCutis", criteria);
	}

	public List getRegionesDirectorioZON(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getRegionesDirectorioZON", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getOtrasMarcas(java.util.Map)
	 */
	public List getOtrasMarcas(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getOtrasMarcas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getTipoPersona(java.util.Map)
	 */
	public List getTipoPersona(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getTipoPersona", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getOrigenIngreso(java.util.Map)
	 */
	public List getOrigenIngreso(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getOrigenIngreso", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getValidarOCRConsultora(java.util.Map)
	 */
	public Map getValidarOCRConsultora(Map map) {
		return (Map)getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getValidarOCRConsultora", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getUltimoHistoricoEstatus(java.util.Map)
	 */
	public ClienteHistoricoEstatus getUltimoHistoricoEstatus(String oidCliente) {
		return (ClienteHistoricoEstatus) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getUltimoHistoricoEstatus", oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeActualizarPrimerContacto(java.lang.String)
	 */
	public void executeActualizarPrimerContacto(String oidCliente) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeActualizarPrimerContacto", oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeActualizarNivelesRiesgo(java.util.Map)
	 */
	public void executeActualizarNivelesRiesgo(Map params) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeActualizarNivelesRiesgo");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getClientesBloqueoDesbloqueoByCriteria(java.util.Map)
	 */
	public List getClientesBloqueoDesbloqueoByCriteria(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getClientesBloqueoDesbloqueoByCriteria", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getAccesosTiposBloqueoByUsuario(java.lang.String)
	 */
	public List getAccesosTiposBloqueoByUsuario(String usuario) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getAccesosTiposBloqueoByUsuario", usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getAccesosTiposDesbloqueoByUsuario(java.lang.String)
	 */
	public List getAccesosTiposDesbloqueoByUsuario(String usuario) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getAccesosTiposDesbloqueoByUsuario", usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getBloqueosClienteByTipoBloqueo(java.lang.Long, java.lang.String)
	 */
	public List getBloqueosClienteByTipoBloqueo(Long oidCliente,
			String tipoBloqueo) {
		Map criteria = new HashMap();
		criteria.put("oidCliente", oidCliente);
		criteria.put("tipoBloqueo", tipoBloqueo);
		
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getBloqueosClienteByTipoBloqueo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertBloqueoCliente(java.util.Map)
	 */
	public void insertBloqueoCliente(Map params) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertBloqueoCliente", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getAccesosTiposDesbloqueoByUsuario(java.lang.String, java.lang.String)
	 */
	public List getAccesosTiposDesbloqueoByUsuario(String codigoUsuario,
			String oidTipoBloqueo) {
		Map criteria = new HashMap();
		criteria.put("codigoUsuario", codigoUsuario);
		criteria.put("oidTipoBloqueo", oidTipoBloqueo);
		
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getAccesosTiposDesbloqueoByUsuarioTipo", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getBloqueoCliente(java.lang.String)
	 */
	public Map getBloqueoCliente(String oidBloqueo) {
		return (Map)getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getBloqueoCliente", oidBloqueo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateBloqueoCliente(java.util.Map)
	 */
	public void updateBloqueoCliente(Map params) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateBloqueoCliente", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getLogBloqueosCliente(java.lang.Long)
	 */
	public List getLogBloqueosCliente(Long oidCliente) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getLogBloqueosCliente", oidCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getLogDesbloqueosCliente(java.lang.Long)
	 */
	public List getLogDesbloqueosCliente(Long oidCliente) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getLogDesbloqueosCliente", oidCliente);
	}
	
	/* INI SA PER-SiCC-2012-0459 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getCiudades(java.util.Map)
	 */
	public List getCiudades(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getCiudades", criteria);
	}
	/* FIN SA PER-SiCC-2012-0459 */

	/* INI SA PER-SiCC-2012-0265 */
	public boolean validarNumeroDocumentoMod11V(String numeroDocumento) {
        String result = (String) getSqlMapClientTemplate().queryForObject(
        							"spusicc.MantenimientoMAESQL.getValidarNumeroDocumentoMod11V", numeroDocumento);
        
        if(Integer.parseInt(result)>0)
        	return true;
        else
        	return false;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getOidTipoDocumento(java.util.Map)
	 */
	public String getOidTipoDocumento(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidTipoDocumento", criteria);
	}
	/* FIN SA PER-SiCC-2012-0265 */

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getOidTipoDocumento1(java.util.Map)
	 */
	public String getOidTipoDocumento1(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getOidTipoDocumento1", criteria);
	}
	/* INI SA PER-SiCC-2012-0535 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getSecuenciaCodigoCliente(java.lang.String)
	 */
	public String getSecuenciaCodigoCliente(String codigoPais) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getSecuenciaCodigoCliente", codigoPais);
	}
	/* FIN SA PER-SiCC-2012-0535 */

	/* INI SA PER-SiCC-2012-0580 */
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#validaPedidosEnProceso(java.util.Map)
     */
    public boolean validaPedidosEnProceso(Map criteria) {
    	String result = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getPedidosEnProceso", criteria);

    	if(Integer.parseInt(result)>0)
    		return true;
    	else
    		return false;
    }
    /* FIN SA PER-SiCC-2012-0580 */
    
    /* INI JJ PER-SiCC-2012-0329 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExisteCodigoCUB(java.util.Map)
	 */
	public String getExisteCodigoCUB(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExisteCodigoCUB", criteria);
	}   
	/* FIN JJ PER-SiCC-2012-0329 */
	
	/* INI SA PER-SiCC-2012-0365 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getPeriodoInicioVacaciones(java.util.Map)
	 */
	public String getPeriodoInicioVacaciones(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getPeriodoInicioVacaciones", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getPeriodoFinVacaciones(java.util.Map)
	 */
	public String getPeriodoFinVacaciones(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getPeriodoFinVacaciones", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeActualizacionClasificacionVacaciones(java.util.Map)
	 */
	public void executeActualizacionClasificacionVacaciones(Map params) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeActualizacionClasificacionVacaciones", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeEliminarClasificacionVacaciones(java.util.Map)
	 */
	public void executeEliminarClasificacionVacaciones(Map params) {
		 getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeEliminarClasificacionVacaciones", params);
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#tienePedidoFacturado(java.util.Map)
     */
    public boolean tienePedidoFacturado(Map criteria) {
    	String result = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getTienePedidoFacturado", criteria);

    	if(Integer.parseInt(result)>0)
    		return true;
    	else
    		return false;
    }              
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#tienePedidoEnProcesoFacturacion(java.util.Map)
     */
    public boolean tienePedidoEnProcesoFacturacion(Map criteria) {
    	String result = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getTienePedidoEnProcesoFacturacion", criteria);

    	if(Integer.parseInt(result)>0)
    		return true;
    	else
    		return false;
    }
	/* FIN SA PER-SiCC-2012-0365 */
    
    /* INI SA PER-SiCC-2012-0138 */
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getValidarBloqueoDesbloqueoCliente(java.util.Map)
     */
    public String getValidarBloqueoDesbloqueoCliente(Map criteria) {
    	return (String)getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getValidarBloqueoDesbloqueoCliente", criteria);   	
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateDesbloqueoCliente(java.util.Map)
     */
    public void updateDesbloqueoCliente(Map params) {
    	getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateDesbloqueoCliente", params);   	
    }
    /* FIN SA PER-SiCC-2012-0138 */

    /* INI SA PER-SiCC-2012-0367 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertSolicitudCreditoRechazado(java.util.Map)
	 */
	public void insertSolicitudCreditoRechazado(Map params) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertSolicitudCreditoRechazado", params);
	}

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertDocumentoDigitacion(java.util.Map)
     */
    public void insertDocumentoDigitacion(Map params) {
		getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertDocumentoDigitacion", params);
	}
 
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertDetalleExcepcion(java.util.Map)
     */
    public void insertDetalleExcepcion(Map params) {
    	getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertDetalleExcepcion", params);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeGenerarSolicitudCreditoRechazada(java.util.Map)
     */
    public void executeGenerarSolicitudCreditoRechazada(Map params) {
    	getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeGenerarSolicitudCreditoRechazada", params);   	
    }
    /* FIN SA PER-SiCC-2012-0367 */

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getObtieneTipoValidacion(java.util.Map)
	 */
	public boolean getObtieneTipoValidacion(Map params) {
		String booleano = (String)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getObtieneTipoValidacion", params);
		
		if (booleano==null || StringUtils.equalsIgnoreCase(booleano, "false")) 
			return false;
		else
			return true;  
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getValidacionDocumentoIdentidad(java.lang.String)
	 */
	public String getValidacionDocumentoIdentidad(String numeroIdentidad) {
		String mensaje = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getValidacionDocumentoIdentidad", numeroIdentidad);
		
		return mensaje;
	}
 
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getDatosCliente(java.util.Map)
	 */
	public Map getDatosCliente(Map criteria) {
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getDatosCliente", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getMotivosBaja(java.util.Map)
	 */
	public List getMotivosBaja(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getMotivosBaja", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeBajaManualEmpresarias(java.util.Map)
	 */
	public void executeBajaManualEmpresarias(Map params) {
    	getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeBajaManualEmpresarias", params);   	
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getDatosEmprendedoraCliente(java.util.Map)
	 */
	public Map getDatosEmprendedoraCliente(Map criteria){
		return (HashMap) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getDatosEmprendedoraCliente", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getZonaPeriodoCerrada(java.util.Map)
	 */
	public String getZonaPeriodoCerrada(Map map) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getZonaPeriodoCerrada", map);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeReasignacionManualEmpresarias(java.util.Map)
	 */
	public void executeReasignacionManualEmpresarias(Map params) {
    	getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeReasignacionManualEmpresarias", params);   	
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#saveActualizacionDatosPortal(java.util.Map)
	 */
	public void saveActualizacionDatosPortal(Map map) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeActualizacionDatosPortal", map);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#deleteReporteClienteTemporal(java.lang.String)
	 */
	public void deleteReporteClienteTemporal(String codigoUsuario) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deleteReporteClienteTemporal", codigoUsuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertReporteClienteTemporal(java.util.List)
	 */
	public void insertReporteClienteTemporal(List resultado) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertReporteClienteTemporal", resultado);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertReporteClienteTemporal(java.util.List, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertReporteClienteTemporal(List resultado, Usuario usuario) {
		Map criteria;
		String login = usuario.getLogin();
		
        for(int i = 0; i < resultado.size(); i++) {
        	criteria = (Map) resultado.get(i);
           	criteria.put("login", login);
           	getSqlMapClientTemplate().insert("spusicc.MantenimientoMAESQL.insertReporteClienteTemporal", criteria);
        }
	}

	public boolean esRegionCerradaxSeccion(Map criteria) {
		String result = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getRegionCerradaxSeccion", criteria);
		
		if(Integer.parseInt(result)>0)
			return true;
		else
			return false;
	}
	

	public String getCodigoTipoDocLegal(String tipoDocumentoIdentidad){

		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getCodigoTipoDocLegal", tipoDocumentoIdentidad);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getZonasDirectorioActivas(java.util.Map)
	 */
	public List getZonasDirectorioActivas(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getZonasDirectorioActivas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getPalabrasCompuestasList(java.util.Map)
	 */
	public List getPalabrasCompuestasList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getPalabrasCompuestasList", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getPalabrasAltisonantesList(java.util.Map)
	 */
	public List getPalabrasAltisonantesList(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getPalabrasAltisonantesList", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getRegionesDirectorioCargoFOX(java.util.Map)
	 */
	public List getRegionesDirectorioCargoFOX(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getRegionesDirectorioCargoFOX", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getRegionesDirectorioCargoGZFOX(java.util.Map)
	 */
	public List getRegionesDirectorioCargoGZFOX(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getRegionesDirectorioCargoGZFOX", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getZonasDirectorioActivasFOX(java.util.Map)
	 */
	public List getZonasDirectorioActivasFOX(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getZonasDirectorioActivasFOX", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getZonasDirectorioCargoFOX(java.util.Map)
	 */
	public List getZonasDirectorioCargoFOX(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getZonasDirectorioCargoFOX", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getOidEstadoCivil(java.lang.String)
	 */
	public Long getOidEstadoCivil(String codigoEstadoCivil) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidEstadoCivil", codigoEstadoCivil);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getOidGradoInstruccion(java.lang.String)
	 */
	public Long getOidGradoInstruccion(String codigoGradoInstruccion) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidGradoInstruccion", codigoGradoInstruccion);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getOidNacionalidad(java.lang.String)
	 */
	public Long getOidNacionalidad(String codigoNacionalidad) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidNacionalidad", codigoNacionalidad);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getOidTratamiento(java.lang.String)
	 */
	public Long getOidTratamiento(String codigoTratamiento) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidTratamiento", codigoTratamiento);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getOidTipoCliente(java.lang.String)
	 */
	public Long getOidTipoCliente(String codigoTipoCliente) {
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidTipoCliente", codigoTipoCliente);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getOidSubTipoCliente(java.lang.String, java.lang.String)
	 */
	public Long getOidSubTipoCliente(String oidTipoCliente, String codigoSubTipoCliente) {
		Map criteria = new HashMap();
		criteria.put("oidTipoCliente", oidTipoCliente);
		criteria.put("codigoSubTipoCliente", codigoSubTipoCliente);
		
		return (Long) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getOidSubTipoCliente", criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getCodigoPeriodoByOidPeriodo(java.lang.String)
	 */
	public String getCodigoPeriodoByOidPeriodo(String oidPeriodo) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getCodigoPeriodoByOidPeriodo", oidPeriodo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExisteClienteCUB(java.lang.String)
	 */
	public String getExisteClienteCUB(String codigoCUB) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getExisteClienteCUB", codigoCUB);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getValorByPaisAndTipo(java.lang.String,java.lang.String)
	 */
	public String getValorByPaisAndTipo(String codigoPais, String tipoValidacion) {
		Map criteria = new HashMap();
		criteria.put("codigoPais",codigoPais);
		criteria.put("tipoValidacion", tipoValidacion);
		
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getValorByPaisAndTipo",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeInsercionHistoricoDatos(biz.belcorp.ssicc.spusicc.mae.model.ClienteHistoricoDatos)
	 */
	public void executeInsercionHistoricoDatos(ClienteHistoricoDatos clienteHistoricoDatos) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeInsercionHistoricoDatos", clienteHistoricoDatos);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#esEjecutiva(java.lang.String)
	 */
	public boolean esEjecutiva(String oidCliente) {
		String result = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getEsEjecutiva", oidCliente);
		
		if(Integer.parseInt(result)>0)
			return true;
		else
			return false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeActualizacionEjecutiva(java.util.Map)
	 */
	public void executeActualizacionEjecutiva(Map params) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeActualizacionEjecutiva", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getCodigoRegionySeccion(java.lang.String, java.lang.String)
	 */
	public Map getCodigoRegionySeccion(String codigoZona, String codigoTerritorio) {
		Map criteria = new HashMap();
		criteria.put("codigoZona",codigoZona);
		criteria.put("codigoTerritorio", codigoTerritorio);
		
		return (Map) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getCodigoRegionySeccion",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExisteCliente(java.util.Map)
	 */
	public String getExisteCliente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getExisteCliente", criteria);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getValidarNumeroDocumentoMod10(java.lang.String)
	 */
	public String getValidarNumeroDocumentoMod10(String numeroDocumento) {
		return (String) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getValidarNumeroDocumentoMod10", numeroDocumento);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeCalcularEstatusCliente(java.util.Map)
	 */
	public void executeCalcularEstatusCliente(Map params) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeCalcularEstatusCliente", params);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getAccesosTiposAreaByUsuario(java.lang.String)
	 */
	public List getAccesosTiposAreaByPais(String usuario) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getAccesosTiposAreaByPais", usuario);	
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getDescripcionTipoBloqueo(java.util.Map)
	 */
	public String getDescripcionTipoBloqueo(Map criteria) {
    	return (String)getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getDescripcionTipoBloqueo", criteria);   	
    }
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getValidarBloqueoDesbloqueoCliente(java.util.Map)
	 */
	public String getDevuelveBloqueoDesbloqueoCliente(Map criteria) {
    	return (String)getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getDevuelveBloqueoDesbloqueoCliente", criteria);   	
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#existePedidosRedifinirVigenciaUA(java.util.Map)
	 */
	public boolean existePedidosRedifinirVigenciaUA(Map criteria) {
		String result = (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getPedidosRedifinirVigenciaUA", criteria);

    	if(Integer.parseInt(result)>0)
    		return true;
    	else
    		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#executeRedifinirVigenciaUA(java.util.Map)
	 */
	public void executeRedifinirVigenciaUA(Map params) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.executeRedifinirVigenciaUA", params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getVerificarRegionZonaCerradaCampannaActiva(java.util.Map)
	 */
	public Integer getVerificarRegionZonaCerradaCampannaActiva(Map params) {
		Integer retorno = (Integer) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getVerificarRegionCerradaCampannaActiva",params);
		if (retorno.intValue() <= 0 )
			retorno = (Integer) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getVerificarZonaCerradaCampannaActiva",params);
	    if (retorno == null ) retorno = new Integer (0);
		return retorno;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getObtenerBuscarConsultoraList(java.util.Map)
	 */
	public List getObtenerBuscarConsultora(Map criteria) {
		List listaRetorno = getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAESQL.getObtenerBuscarConsultora", criteria);	
		if (listaRetorno == null ||  listaRetorno.size() == 0)
			listaRetorno = getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAESQL.getObtenerBuscarConsultoraDesactivadas", criteria);
		return listaRetorno;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getObtenerDireccionesConsultora(java.util.Map)
	 */
	public List getObtenerDireccionesConsultora(Map criteria) {
		List listaRetorno = getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAESQL.getObtenerDireccionesConsultora", criteria);	
		return listaRetorno;
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getValidarRegularizacion(java.util.Map)
	 */
	public Integer getValidarRegularizacion(Map criteria) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getValidarRegularizacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateRegularizacion(java.util.Map)
	 */
	public void updateRegularizacion(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateRegularizacion", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#validarNumeroRucModulo11(java.lang.String)
	 */
	public Integer validarNumeroRucModulo11(String numeroDocumentoIdentidad) {
		return (Integer) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getValidacionNumeroRucModulo11", numeroDocumentoIdentidad);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#validarNumeroCarnetIdentidad(java.lang.String)
	 */
	public String validarNumeroCarnetIdentidad(String numeroDocumentoIdentidad) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getValidarNumeroCarnetIdentidad", numeroDocumentoIdentidad);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getDocumentosConsultora(java.util.Map)
	 */
	public List getDocumentosConsultora(Map docParmas) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getDocumentosConsultora", docParmas);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getDigitoControlModuloPTR(java.lang.String)
	 */
	public String getDigitoControlModuloPTR(String codigo) {
		return (String)getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getDigitoControlModuloPTR", codigo);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExisteLider(java.util.Map)
	 */
	public Integer getExisteLider(Map criteria) {
		return (Integer)  getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAESQL.getExisteLider", criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertLider(java.util.Map)
	 */
	public void insertLider(Map criteria) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosMAESQL.insertLider", criteria);
		return;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExistePagoLider(java.util.Map)
	 */
	public Integer getExistePagoLider(Map criteria) {
		return (Integer)  getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAESQL.getExistePagoLider", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updatePagoLiderContabilizarPago(java.util.Map)
	 */
	public void updatePagoLiderContabilizarPago(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.updatePagoLiderContabilizarPago", criteria);
		return;	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO#updatePagoLiderContabilizarPagoSinMonto(java.util.Map)
	 */
	public void updatePagoLiderContabilizarPagoSinMonto(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.updatePagoLiderContabilizarPagoSinMonto", criteria);
		return;	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getPeriodosRetiradas(java.lang.String)
	 */
	public String getPeriodosRetiradas(String codigoPais) {
		return (String)getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getPeriodosRetiradas", codigoPais);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updatePeriodosRetiradas(java.util.Map)
	 */
	public void updatePeriodosRetiradas(Map params) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updatePeriodosRetiradas", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExcencionesFletesByCriteria(java.util.Map)
	 */
	public List getExcencionesFletesByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getExcencionesFletesByCriteria", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExcencionFlete(java.lang.String)
	 */
	public ExcencionFlete getExcencionFlete(String id) {
		return (ExcencionFlete)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExcencionFlete", id);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertExcencionFlete(biz.belcorp.ssicc.spusicc.mae.model.ExcencionFlete, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertExcencionFlete(ExcencionFlete excencionFlete, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertExcencionFlete", excencionFlete);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateExcencionFlete(biz.belcorp.ssicc.spusicc.mae.model.ExcencionFlete, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateExcencionFlete(ExcencionFlete excencionFlete, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateExcencionFlete", excencionFlete);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExcencionesSobreFletesByCriteria(java.util.Map)
	 */
	public List getExcencionesSobreFletesByCriteria(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getExcencionesSobreFletesByCriteria", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExcencionSobreFlete(java.lang.String)
	 */
	public ExcencionSobreFlete getExcencionSobreFlete(String id) {
		return (ExcencionSobreFlete)getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getExcencionSobreFlete", id);	
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#insertExcencionSobreFlete(biz.belcorp.ssicc.spusicc.mae.model.ExcencionSobreFlete, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertExcencionSobreFlete(
			ExcencionSobreFlete excencionSobreFlete, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.insertExcencionSobreFlete", excencionSobreFlete);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updateExcencionSobreFlete(biz.belcorp.ssicc.spusicc.mae.model.ExcencionSobreFlete, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateExcencionSobreFlete(
			ExcencionSobreFlete excencionSobreFlete, Usuario usuario) {
		getSqlMapClientTemplate().update("spusicc.MantenimientoMAESQL.updateExcencionSobreFlete", excencionSobreFlete);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getBloqueoClientePreFacturacion(java.util.Map)
	 */
	public String getBloqueoClientePreFacturacion(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getBloqueoClientePreFacturacion", criteria);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getClientePedido(java.util.Map)
	 */
	public String getClientePedido(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getClientePedido", criteria);
    }	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getValidaIngresoTelefono(java.util.Map)
	 */
	public String getValidaIngresoTelefono(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getValidaIngresoTelefono", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getListPrimerContacto(java.lang.Long)
	 */
	public List getListPrimerContacto(Long oid) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getListPrimerContacto",oid);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getPrimerContactoOid(java.lang.Long)
	 */
	public ClientePrimerContacto getPrimerContactoOid(Long oid) {
		return (ClientePrimerContacto) getSqlMapClientTemplate().queryForObject(
				"spusicc.MantenimientoMAESQL.getPrimerContactoOid", oid);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#deletePrimerContactoHijaDupla(biz.belcorp.ssicc.spusicc.mae.model.ClienteSubTipo)
	 */
	public void deletePrimerContactoHijaDupla(ClienteSubTipo clienteSubTipo) {
		getSqlMapClientTemplate().delete("spusicc.MantenimientoMAESQL.deletePrimerContactoHijaDupla",clienteSubTipo);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getValidaDocumentoIdentidad(java.util.Map)
	 */
	public String getValidaDocumentoIdentidad(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getValidaDocumentoIdentidad", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#getExisteCodigoProveedor(java.util.Map)
	 */
	public String getExisteCodigoProveedor(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.ProcesosMAESQL.getExisteCodigoProveedor", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.MantenimientoMAEClienteDAO#updatePagoLiderRegistarLider(java.util.Map)
	 */
	public void updatePagoLiderRegistarLider(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.updatePagoLiderRegistarLider", criteria);
		return;	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO#getSubTiposDocumentoIdentidad(java.util.Map)
	 */
	public List getSubTiposDocumentoIdentidad(Map criteria){
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getSubTiposDocumentoIdentidad", criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO#getMostrarSubTipoDocumentoIdentidad(java.util.Map)
	 */
	public String getMostrarSubTipoDocumentoIdentidad(Map criteria) {
        return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getMostrarSubTipoDocumentoIdentidad", criteria);
    }


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO#getEntidadBancoMaeList(java.util.Map)
	 */
	public List getEntidadBancoMaeList(Map criteria) {
		return getSqlMapClientTemplate().queryForList(
				"spusicc.MantenimientoMAESQL.getEntidadBancoMaeList", criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO#getMostrarBancoCuentaCorriente(java.util.Map)
	 */
	public String getMostrarBancoCuentaCorriente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject(
        		"spusicc.MantenimientoMAESQL.getMostrarBancoCuentaCorriente", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO#updateClienteLider(java.util.Map)
	 */
	public void updateClienteLider(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.updateClienteLider", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO#getCodigosTerritorialCorresponde(java.util.Map)
	 */
	@Override
	public List getCodigosTerritorialCorresponde(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getCodigosTerritorialCorresponde", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO#getBancos(java.util.Map)
	 */
	@Override
	public List getBancos(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getBancos", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO#getTiposCuenta(java.util.Map)
	 */
	@Override
	public List getTiposCuenta(Map criteria) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getTiposCuenta", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO#getMostrarTipoCuentaCuentaCorriente(java.util.Map)
	 */
	@Override
	public String getMostrarTipoCuentaCuentaCorriente(Map criteria) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.MantenimientoMAESQL.getMostrarTipoCuentaCuentaCorriente", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.MantenimientoMAEClienteDAO#getListDireccionClienteCamposAdicionales(java.lang.String)
	 */
	public List getListDireccionClienteCamposAdicionales(String oidCliente) {
		return getSqlMapClientTemplate().queryForList("spusicc.MantenimientoMAESQL.getListDireccionClienteCamposAdicionales", oidCliente);
	}
}