/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos.ibatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.DatosConsultora;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECDigitacionBoletasRecojoDAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoCabecera;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.BoletaRecojoDetalle;


/**
 * @author Jose A. cairampoma
 * 
 */
@Repository("spusicc.mantenimientoRECDigitacionBoletasRecojoDAO")
public class MantenimientoRECDigitacionBoletasRecojoDAOIbatis extends		BaseDAOiBatis implements MantenimientoRECDigitacionBoletasRecojoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.comision.web.action.MantenimientoCOMCalificacionComisionDAO#getCalificacionesComisionesList(biz.belcorp.ssicc.spusicc.comision.web.form.MantenimientoCOMCalificacionComisionSearchForm)
	 */
	public List getBoletasRecojoCabeceraList(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getBoletasRecojoCabeceraList",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#getBoletaRecojoDetalleList(biz.belcorp.ssicc.spusicc.reclamos.model.BoletaRecojoCabecera)
	 */
	public List getBoletaRecojoDetalleList(	BoletaRecojoCabecera boletaRecojoCabecera) {		
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getBoletaRecojoDetalleList",boletaRecojoCabecera);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#getProductosByCriteria(java.util.Map)
	 */
	public List getProductosByCriteria(Map params){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getProductosByCriteria",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#getBoletaRecojoDetalleDiscrepanteList(biz.belcorp.ssicc.spusicc.reclamos.model.BoletaRecojoDetalle)
	 */
	public List getBoletaRecojoDetalleDiscrepanteList(BoletaRecojoDetalle boletaRecojoDetalle) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getBoletaRecojoDetalleDiscrepanteList",boletaRecojoDetalle);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#updateBoletaRecojoCabeceraDetalle(biz.belcorp.ssicc.spusicc.reclamos.model.BoletaRecojoCabecera, java.util.List, java.util.List, java.util.List)
	 */
	public void updateBoletaRecojoCabeceraDetalle(BoletaRecojoCabecera boletaRecojoCabecera, List detalList,List detalElimList, Map detalDiscList, Usuario usuario) {
		
		//Borramos los Detalles 
		for (int i = 0; i < detalElimList.size(); i++) {
			BoletaRecojoDetalle boletaRecojoDetalle = (BoletaRecojoDetalle)detalElimList.get(i);
			getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteBoletaRecojoDetalle", boletaRecojoDetalle);
			getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.resetBoletaRecojoDetalle", boletaRecojoDetalle);
		}
		 
		int totalUnidades = 0;
		
		//Inserta los detalles y actualiza unidades recogidas en la Cabeceras
		for (int i = 0; i < detalList.size(); i++) {
			
			BoletaRecojoDetalle boletaRecojoDetalle = (BoletaRecojoDetalle)detalList.get(i);
			boletaRecojoDetalle  = devuelveBoletaRecojoDetalle(boletaRecojoDetalle,boletaRecojoCabecera);			
			
			//Si la linea tiene discrepancia Actualiza e Inserta Discrepantes
			if (boletaRecojoDetalle.getIndicadorDiscrepante().equals("1")){
				List detalDiscrepanteList = (List)detalDiscList.get(boletaRecojoDetalle.getCodigoDetalle());
								
				for (int j = 0; j < detalDiscrepanteList.size(); j++) {
					
					BoletaRecojoDetalle boletaRecojoDetalleDiscrepante = (BoletaRecojoDetalle)detalDiscrepanteList.get(j);
					boletaRecojoDetalle.setCodigoProductoDiscrepante(boletaRecojoDetalleDiscrepante.getCodigoProductoDiscrepante());
					boletaRecojoDetalle.setDescripcionProducto(boletaRecojoDetalleDiscrepante.getDescripcionProductoDisc());
					boletaRecojoDetalle.setUnidadesRecogidas(boletaRecojoDetalleDiscrepante.getUnidadesRecogidas());
					boletaRecojoDetalle.setIndicadorDiscrepante(boletaRecojoDetalleDiscrepante.getIndicadorDiscrepante());
					
					boletaRecojoDetalle.setCodigoVentaDiscrepante(boletaRecojoDetalleDiscrepante.getCodigoVentaDiscrepante());
					boletaRecojoDetalle.setDescripcionProductoDisc(boletaRecojoDetalleDiscrepante.getCodigoVentaDiscrepante());
					
					boletaRecojoDetalle.setPeriodoDiscrepante(boletaRecojoDetalleDiscrepante.getPeriodoDiscrepante());
					boletaRecojoDetalle.setPrecioDiscrepante(boletaRecojoDetalleDiscrepante.getPrecioDiscrepante());
					
					if (j==0){
						getSqlMapClientTemplate().delete("spusicc.reclamos.ReclamosSQL.deleteBoletaRecojoDetalle", boletaRecojoDetalle);
						getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateBoletaRecojoDetalle", boletaRecojoDetalle);
						totalUnidades = totalUnidades + Integer.parseInt(boletaRecojoDetalle.getUnidadesRecogidas());
					}
					else{
						String secuencia = (String) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getNestSecuenciaBoletaRecojoDetalleById", boletaRecojoCabecera);
						boletaRecojoDetalle.setNumeroSecuencia(secuencia);
						getSqlMapClientTemplate().insert("spusicc.reclamos.ReclamosSQL.insertBoletaRecojoDetalle", boletaRecojoDetalle);
						totalUnidades = totalUnidades + Integer.parseInt(boletaRecojoDetalle.getUnidadesRecogidas());
					}
				}
			}
			else{
				getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateBoletaRecojoDetalle", boletaRecojoDetalle);
				totalUnidades = totalUnidades + Integer.parseInt(boletaRecojoDetalle.getUnidadesRecogidas());
			}
		}
		
		//Actualiza el numero de unidades Recogidas de la cabecera
		boletaRecojoCabecera = devuelveBoletaRecojoCabecera(boletaRecojoCabecera);
		boletaRecojoCabecera.setTotalUnidadesRecogidas(new Integer(totalUnidades));
		
		boletaRecojoCabecera.setCodigoUsuario(usuario.getLogin());
		
		getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.updateBoletaRecojoCabecera", boletaRecojoCabecera); 
		
		String codigoEstado  = boletaRecojoCabecera.getCodigoEstado();
		String codigoResultado  = boletaRecojoCabecera.getCodigoResultado();
		String codigoMotivoRecojo2 = boletaRecojoCabecera.getCodigoMotivoNoRecojo2();
		
		if (codigoEstado.equals("CE")){
			
			Map paramsCargoAbono = new HashMap();
			paramsCargoAbono.put("codigoPais",boletaRecojoCabecera.getCodigoPais());
			paramsCargoAbono.put("codigoCabecera",boletaRecojoCabecera.getCodigoCabecera());
			
			getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.executeCalculoCargosAbonos", paramsCargoAbono);
			
			if (codigoEstado.equals("CE") && codigoResultado.equals("NX") ) {
				
				/*Agregar esta condicicin - Si motivo no recojo 2 no es nulo y es diferente de 05 entonces*/
				if (codigoMotivoRecojo2 !=null && codigoMotivoRecojo2.equals("05")){
					String codigoCliente  = boletaRecojoCabecera.getCodigoCliente();
					String loginUsuario =  usuario.getLogin(); 
					Map paramsBloqueo = new HashMap();
					paramsBloqueo.put("codigoCliente",codigoCliente);
					paramsBloqueo.put("loginUsuario",loginUsuario);
					
					getSqlMapClientTemplate().update("spusicc.reclamos.ReclamosSQL.executeBloqueoConsultoraBoletaRecojoNoExitosa", paramsBloqueo);
				}
				
			}
			
		}
	}

	/**
	 * Carga los valores del detalle que seran registrados en base a los datos capturados de la pantalla de digitacion de BR's 
	 * @param boletaRecojoDetalle
	 * @param boletaRecojoCabecera
	 * @return
	 */
	private BoletaRecojoDetalle devuelveBoletaRecojoDetalle(BoletaRecojoDetalle boletaRecojoDetalle, BoletaRecojoCabecera boletaRecojoCabecera) {
		
		if (boletaRecojoCabecera.getNumeroRecojo().equals("1")) boletaRecojoDetalle.setIndicadorRegresoYobel1("1");
		else boletaRecojoDetalle.setIndicadorRegresoYobel2("1");
		
		String resultado = boletaRecojoCabecera.getCodigoResultado();
		
//		if (resultado.equals(Constants.REC_BOREC_EXITOSA)){
//		boletaRecojoDetalle.setUnidadesRecogidas(boletaRecojoDetalle.getUnidadesReclamadas());
//		
//	}
		if (resultado.equals(Constants.REC_BOREC_NO_EXITOSA) || resultado.equals(Constants.REC_BOREC_NO_ENTREGADA) ){
			boletaRecojoDetalle.setUnidadesRecogidas("0");
		}			
		
		return boletaRecojoDetalle;
	}

	/**
	 * Carga los valores de la cabecera que seran registrados en base a los datos capturados de la pantalla de digitacion de BR's
	 * @param boletaRecojoCabecera
	 * @return
	 */
	private BoletaRecojoCabecera devuelveBoletaRecojoCabecera(BoletaRecojoCabecera boletaRecojoCabecera) {
		
		BoletaRecojoCabecera boletaRecojoCabeceraDB = (BoletaRecojoCabecera) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getBoletasRecojoCabeceraById", boletaRecojoCabecera);
		
		String numeroRecojo = boletaRecojoCabecera.getNumeroRecojo();
		
		String codigoResultado = boletaRecojoCabecera.getCodigoResultado();
		
		String estado1 = boletaRecojoCabeceraDB.getCodigoEstado(); 
		
		String motivoNoRecojo1 = boletaRecojoCabecera.getCodigoMotivoNoRecojo1();
		
		boletaRecojoCabeceraDB.setEstado(numeroRecojo); 
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", boletaRecojoCabecera.getCodigoPais());
		String lnparamnumreco = getNumeroRecojoParam(criteria);
		int paramnumreco = StringUtils.isNotBlank(lnparamnumreco)? Integer.valueOf(lnparamnumreco): 0;
		
		
		if (numeroRecojo.equals("1")) {
			
			if (codigoResultado.equals(Constants.REC_BOREC_EXITOSA) ||
			    codigoResultado.equals(Constants.REC_BOREC_DISCREPANTE) ||
			    codigoResultado.equals(Constants.REC_BOREC_NO_ENTREGADA) || 
			    (codigoResultado.equals(Constants.REC_BOREC_NO_EXITOSA) && StringUtils.isNotBlank(motivoNoRecojo1) 
			    		&& motivoNoRecojo1.equals("05") ) ) {
				estado1 =Constants.REC_BOREC_CERRADA;
				
			}			
			else if (codigoResultado.equals(Constants.REC_BOREC_NO_EXITOSA)) {
				 if ( paramnumreco > 1) {
                     estado1 =Constants.REC_BOREC_GESTION;           
                      numeroRecojo="2";
                     }
                       else {
                     estado1 =Constants.REC_BOREC_CERRADA;          
                      numeroRecojo="1";
                     }
			}
			boletaRecojoCabeceraDB.setNumeroRecojo(numeroRecojo);
			boletaRecojoCabeceraDB.setCodigoEstado(estado1);
			boletaRecojoCabeceraDB.setCodigoResultado(codigoResultado);
			boletaRecojoCabeceraDB.setCodigoMotivoNoRecojo1(boletaRecojoCabecera.getCodigoMotivoNoRecojo1());
			boletaRecojoCabeceraDB.setFechaEmision1(boletaRecojoCabecera.getFechaEmision1());
			boletaRecojoCabeceraDB.setFechaRecojo1(boletaRecojoCabecera.getFechaRecojo1());
			boletaRecojoCabeceraDB.setHoraRecojo1(boletaRecojoCabecera.getHoraRecojo1());
			boletaRecojoCabeceraDB.setNombreTercero1(boletaRecojoCabecera.getNombreTercero1());
			boletaRecojoCabeceraDB.setNumeroRecojoInicio(boletaRecojoCabecera.getNumeroRecojoInicio());
		
		} else{
			
			
			if (codigoResultado.equals(Constants.REC_BOREC_EXITOSA) ||
				    codigoResultado.equals(Constants.REC_BOREC_DISCREPANTE) ||
				    codigoResultado.equals(Constants.REC_BOREC_NO_ENTREGADA) || 
				    (codigoResultado.equals(Constants.REC_BOREC_NO_EXITOSA) && motivoNoRecojo1.equals("05") ) ) {
					
				boletaRecojoCabecera.setCodigoMotivoNoRecojo2(null);
			}
			
			estado1 =Constants.REC_BOREC_CERRADA;
			
			boletaRecojoCabeceraDB.setNumeroRecojo(numeroRecojo);
			boletaRecojoCabeceraDB.setCodigoEstado(estado1);
			boletaRecojoCabeceraDB.setCodigoResultado(codigoResultado);
			boletaRecojoCabeceraDB.setCodigoMotivoNoRecojo2(boletaRecojoCabecera.getCodigoMotivoNoRecojo2());
			boletaRecojoCabeceraDB.setFechaEmision2(boletaRecojoCabecera.getFechaEmision2());
			boletaRecojoCabeceraDB.setFechaRecojo2(boletaRecojoCabecera.getFechaRecojo2());
			boletaRecojoCabeceraDB.setHoraRecojo2(boletaRecojoCabecera.getHoraRecojo2());
			boletaRecojoCabeceraDB.setNombreTercero2(boletaRecojoCabecera.getNombreTercero2());
		}
		return boletaRecojoCabeceraDB;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#getCabeceraBoletaRecojo(java.util.Map)
	 */
	public List getCabeceraBoletaRecojo(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCabeceraBoletaRecojo",criteria);
	}
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#getBloqueoConsultora(java.util.Map)
	 */
	public String getBloqueoConsultora(Map criteria){		
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getBloqueoConsultora", criteria).get(0).toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#getDatosConsultora(java.util.Map)
	 */
	public DatosConsultora getDatosConsultora(Map criteria){
		List l = new ArrayList(); 
		DatosConsultora lb = new DatosConsultora();
		l=getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getDatosConsultora", criteria);		
		if(l.size()!=0)
			lb = (DatosConsultora)l.get(0);		
		return lb;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#getDetallesBoletaRecojo(java.util.Map)
	 */
	public List getDetallesBoletaRecojo(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getDetallesBoletaRecojo",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#getDatosBoletaRecojoSimplificada(java.util.Map)
	 */
	public List getDatosBoletaRecojoSimplificada(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getDatosBoletaRecojoSimplificada",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#getNovedadesRecojo(java.util.Map)
	 */
	public List getNovedadesRecojo(Map criteria){
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getNovedadesRecojo",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#getCodigoCabeceraBoletaRecojo(java.util.Map)
	 */
	public String getCodigoCabeceraBoletaRecojo(Map criteria){		
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getCodigoCabeceraBoletaRecojo", criteria).get(0).toString();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#getCabeceraBoletaRecojoById(biz.belcorp.ssicc.spusicc.reclamos.model.BoletaRecojoCabecera)
	 */
	public BoletaRecojoCabecera getCabeceraBoletaRecojoById(BoletaRecojoCabecera boletaRecojoCabecera){
		return (BoletaRecojoCabecera) getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getBoletasRecojoCabeceraById", boletaRecojoCabecera);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#activarBoletaRecojoProcess(java.util.Map)
	 */
	public void activarBoletaRecojoProcess(Map params) {		
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.activarBoletaRecojoProcess",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#eliminarBoletaRecojoProcess(java.util.Map)
	 */
	public void eliminarBoletaRecojoProcess(Map params) {
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.eliminarBoletaRecojoProcess",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.reclamos.dao.MantenimientoRECDigitacionBoletasRecojoDAO#getConsultaRECBoletaRecojoDetail(java.lang.String)
	 */
	public List getConsultaRECBoletaRecojoDetail(String numeroBoleta) {
		return getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.getConsultaRECBoletaRecojoDetail",numeroBoleta);
	}

	@Override
	public void executeReclamoProcesoAbono(Map params) {
		getSqlMapClientTemplate().queryForList("spusicc.reclamos.ReclamosSQL.executeReclamoProcesoAbono", params);
	}
	
	public String getNumeroRecojoParam(Map criteria){		
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.reclamos.ReclamosSQL.getNumeroRecojoParam", criteria);
	}  
}
