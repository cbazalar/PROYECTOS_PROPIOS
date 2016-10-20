package biz.belcorp.ssicc.dao.edu.gen.ibatis;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.gen.GenericoEDUComercialDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.model.Usuario;

@Repository("edu.genericoEDUComercialORACLEDAO")
public class GenericoEDUComercialORACLEDAOiBatis extends BaseDAOiBatis implements
		GenericoEDUComercialDAO {

	List listComercial = new ArrayList();

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getPedidosComercial(java.lang.String, java.util.Map)
	 */
	public List getPedidosComercial(String dataSource, Map params) throws Exception  {
		try {
			String indicadorNombreCompleto = (String) params.get("indicadorNombreCompleto");
			if (Constants.EDU_INDICADOR_NOMBRE_COMPLETO.equals(indicadorNombreCompleto))
				listComercial = getSqlMapClientTemplate().queryForList(
						"edu.GenericoEDUComercialORACLESQL.getPedidosConsultoraNombreCompleto",
						params);
			else	
				listComercial = getSqlMapClientTemplate().queryForList(
						"edu.GenericoEDUComercialORACLESQL.getPedidosConsultora",
						params);
			return listComercial;
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.pedidoConsultora", (Usuario)params.get("usuario"));
			throw new Exception(mensajeError + "" + e.getMessage());
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getPedidosComercialFacturados(java.lang.String, java.util.Map)
	 */
	public List getPedidosComercialFacturados(String dataSource, Map params) throws Exception {
		try {
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialORACLESQL.getPedidosConsultoraFacturados",
							params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.pedidoConsultoraFacturadas", (Usuario)params.get("usuario"));
			throw new Exception(mensajeError+""					
							+ e.getMessage());
		} 
		return listComercial;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getPedidosCursosFacturados(java.lang.String, java.util.Map)
	 */
	public List getPedidosCursosFacturados(String dataSource, Map params)throws Exception {
		try {
			log.debug("params " + params);
			//se realiza un proceso que llene un GTT , con la informacion de las consultora que han comprado el curso
			getSqlMapClientTemplate().update("edu.GenericoEDUComercialORACLESQL.executeProcesoCursoFacturados", 
					params);	
			//luego se realiza el select al GTT			
			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialORACLESQL.getPedidosCursosFacturados",
							params);
		} 
		catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.pedidoConsultoraCursosFacturados", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} 
		return listComercial; 
		
	}
	
	public List getPedidosCursosNoFacturados(String dataSource, Map params)
			throws Exception {
		return null;
	}
	
	public List getConsultorasNuevas(String dataSource, Map params) {
		return null;
	}

	public List getDetalleProducto(String dataSource, Map params) {
		return null;
	}

	public List getMatrizProducto(String dataSource, Map params)throws Exception {
		List matrizProducto = null;
		try {
		
			matrizProducto = getSqlMapClientTemplate().queryForList(
					"edu.GenericoEDUComercialORACLESQL.getMatrizProducto",
					params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.MatrizProducto", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		} 
		return matrizProducto;
	}
	
	public List getRegionesComercial(String codigoPais, Map params) throws Exception {
		List listComercial=new ArrayList();
		try {
			listComercial = getSqlMapClientTemplate().queryForList(
					"edu.GenericoEDUComercialORACLESQL.getRegionesComercial",
					params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.RegionesComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}
		return listComercial;
	}
	
	public List getZonasComercial(String codigoPais, Map params) throws Exception{
		List listComercial=new ArrayList();
		try {
			listComercial = getSqlMapClientTemplate().queryForList(
					"edu.GenericoEDUComercialORACLESQL.getZonasComercial",
					params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.ZonasComercial", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}
		return listComercial;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getControlFacturacionComercial(java.lang.String, java.util.Map)
	 */
	public List getControlFacturacionComercial(String codigoPais, Map params) throws Exception{
		List listComercial=new ArrayList();
		try {

			listComercial = getSqlMapClientTemplate()
					.queryForList(
							"edu.GenericoEDUComercialORACLESQL.getControlFacturacionComercial",
							params);
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.ControlFacturacion", (Usuario)params.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}

		return listComercial;
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getTipoClasificacionEquivalencia(java.lang.String, java.util.Map)
	 */
	public List getTipoClasificacionEquivalencia(String fuenteDatos, Map params) throws Exception {
		List listComercial=new ArrayList();
		listComercial = getSqlMapClientTemplate().queryForList(
						"edu.GenericoEDUComercialORACLESQL.getTipoClasificacionEquivalencia", params);
		return listComercial;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getCodigoClasificacionEquivalencia(java.lang.String, java.util.Map)
	 */
	public List getCodigoClasificacionEquivalencia(String fuenteDatos, Map params) throws Exception {
		List listComercial=new ArrayList();
		listComercial = getSqlMapClientTemplate().queryForList(
				"edu.GenericoEDUComercialORACLESQL.getCodigoClasificacionEquivalencia", params);
		return listComercial;
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertConsultorasAptas(java.lang.String, java.util.List, java.lang.String)
	 */
	public void insertConsultorasAptas(String codigoPais, final List list, final String numeroLote,Map params) throws Exception {
		Map dataInsert=null;
		try {
			final Timestamp timestamp = new Timestamp(System
					.currentTimeMillis());
			
					Iterator listIterator = list.iterator();
					while (listIterator.hasNext()) {
						dataInsert = (Map) listIterator.next();
						String codigoMensaje;
						logger.debug("dataInsewrt " + dataInsert);
						
						dataInsert.put("numeroLote", numeroLote);
						dataInsert.put("tipoDespacho",
								Constants.EDU_TIPO_DESPACHO_INVITACION);
						
						/* Obteniendo Mensaje General */
						codigoMensaje = (String) dataInsert.get("codigoMensajeGen");
						if (StringUtils.isNotBlank(codigoMensaje)) {
							dataInsert.put("codigoMensaje", codigoMensaje);	
							getSqlMapClientTemplate()
									.insert(
											"edu.GenericoEDUComercialORACLESQL.insertarAptasMensaje",
											dataInsert);
						}
						/* Obteniendo Mensaje Especifico */
						codigoMensaje = (String) dataInsert.get("codigoMensajeEsp");
						if (StringUtils.isNotBlank(codigoMensaje)) {
							dataInsert.put("codigoMensaje", codigoMensaje);	
							getSqlMapClientTemplate()
									.insert(
											"edu.GenericoEDUComercialORACLESQL.insertarAptasMensaje",
											dataInsert);
						}
						
						/* Enviando lista de Consultoras Aptas a tabla Temporal */
						getSqlMapClientTemplate()
							.insert(
									"edu.GenericoEDUComercialORACLESQL.insertarAptas",
									dataInsert);
						
						
					}
					logger.debug("Inicio->" + timestamp.toString()
							+ "Fin-->"
							+ new Timestamp(System.currentTimeMillis()));

	
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.InsertConsultoras", (Usuario)dataInsert.get("usuario"));
			throw new Exception(
					mensajeError+""
							+ e.getMessage());
		}
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteConsultorasAptas(java.lang.String, java.util.Map)
	 */
	public void deleteConsultorasAptas(String dataSource, Map params) throws Exception {
		getSqlMapClientTemplate().delete(
				"edu.GenericoEDUComercialORACLESQL.deleteConsultorasAptas", params);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#executeEnvioEquivalenciaClasificacion(java.lang.String, java.util.Map)
	 */
	public void executeEnvioEquivalenciaClasificacion(String dataSource, Map params) throws Exception {
		getSqlMapClientTemplate().update("edu.GenericoEDUComercialORACLESQL.executeEnvioEquivalenciaClasificacion", 
				params);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#executeBorrarEquivalenciaMensaje(java.lang.String, java.util.Map)
	 */
	public void executeBorrarEquivalenciaMensaje(String fuenteDatos, Map params) throws Exception {
		getSqlMapClientTemplate().delete("edu.GenericoEDUComercialORACLESQL.executeBorrarEquivalenciaMensaje", 
				params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertConsultorasAptasCosto(java.lang.String, java.util.List, java.lang.String)
	 */
	public void insertConsultorasAptasCosto(String codigoPais,final List list, final String numeroLote,Map params) throws Exception {
		this.insertConsultorasAptas(codigoPais,list,numeroLote,params);
	}
	
	public void insertConsultorasAptasporProgramar(String dataSource, final List list,String numeroLote, final String periodo,Map params) {
		
	}
	
	public List getConsultorasAptasporProgramar(String dataSource, Map params)
	{
		return null;
	}

	public void insertParametrosCursoCapacitacion(String dataSource, final Map params)  throws Exception{
		
	}
	
	public void updateParametrosCursoCapacitacion(String dataSource,Map params)	 throws Exception{
		
	}

	public void deleteConsultorasAptasCosto(String dataSource, Map params) {
		try{
			this.deleteConsultorasAptas(dataSource,params);
		}catch(Exception e){
			logger
			.debug("Error GenericoEDUComercialOracleDAOiBatis--deleteConsultorasAptasCosto : "
					+ e.getMessage());
		}
	}
	
	public void deleteConsultorasAptasProgramar(String dataSource, Map params){
		
	}

	public void insertAptasHistoricas(String dataSource,final List list,Map params) throws Exception	{
		
	}
	
	public void deleteAptasHistoricas(String dataSource, Map params){
	}

	public void deleteBeneficiosCapacitadas(String dataSource, Map params){
	}
	
	public List getBeneficiosCapacitadas(String dataSource, Map params)throws Exception{
		
		return null;
	}
	public void insertBeneficiosCapacitadas(String dataSource,final List list,Map params) throws Exception{
		
	}
	public void updateBeneficiosCapacitadas(String dataSource, Map params){
		
	}

	public void insertMantenimientoClasificacion(String dataSource,final List list,Map params) throws Exception{
		
	}
	
	public List getMantenimientoClasificacion(String dataSource, Map params)throws Exception{
	
		return null;
	}
	
	public void insertMantenimientoCodVenta(String dataSource, Map params) {
		
	}
	
	public void updateMantenimientoClasificacion(String dataSource, Map params){
		
	}

	public void insertMantenimientoClasificacion(String dataSource, Map params){
		
	}
	public void insertBeneficiosCapacitadas(String dataSource, Map params) {
		
	}

	public void deleteMantenimientoClasificacion(String dataSource, Map params){
		
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getExisteMensajeEducacion(java.lang.String, java.util.Map)
     */
    public Integer getExisteMensajeEducacion(String dataSource, Map params) throws Exception{
    	return null;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertMensajeEducacion(java.lang.String, java.util.Map)
	 */
	public void insertMensajeEducacion(String dataSource, Map params) throws Exception {
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#updateMensajeEducacion(java.lang.String, java.util.Map)
	 */
	public void updateMensajeEducacion(String dataSource, Map params) throws Exception {
		
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteMensajeEducacion(java.lang.String, java.util.Map)
     */
    public void deleteMensajeEducacion(String dataSource, Map params) throws Exception {
		
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getExisteEmpresaComercializadora(java.lang.String, java.util.Map)
     */
    public Integer getExisteEmpresaComercializadora(String dataSource, Map params) throws Exception {
    	return null;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public void insertEmpresaComercializadora(String dataSource, Map params) throws Exception {
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#updateEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public void updateEmpresaComercializadora(String dataSource, Map params) throws Exception {
		
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteEmpresaComercializadora(java.lang.String, java.util.Map)
     */
    public void deleteEmpresaComercializadora(String dataSource, Map params) throws Exception {
		
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getExisteEmpresaComercializadora(java.lang.String, java.util.Map)
     */
    public Integer getBloqueoConsultora(String dataSource, Map params) throws Exception {
    	return null;
    }
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertBloqueoConsultora(java.lang.String, java.util.List)
     */
    public void insertBloqueoConsultora(String dataSource,final List list, Map params) throws Exception	{
    	  //la lista aca nose utilza
//    	  getSqlMapClientTemplate().update(
//					"edu.GenericoEDUComercialORACLESQL.executeEnvioBloqueoConsultora",params);
    	
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteBloqueoConsultora(java.lang.String, java.util.Map)
     */
    public void deleteBloqueoConsultora(String dataSource, Map params) throws Exception {
    	return;
    }
    
    
	public List getMantenimientoClasificacionInvi(String dataSource, Map params)throws Exception{
		
		return null;
	}
	
	public void updateMantenimientoClasificacionInvi(String dataSource, Map params){
		
	}

	public void insertMantenimientoClasificacionInvi(String dataSource, Map params){
		
	}
	
	public void deleteMantenimientoClasificacionInvi(String dataSource, Map params){
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getRecodificacionConsultora(java.lang.String, java.util.Map)
	 */
	public List getRecodificacionConsultora(String dataSource, Map params) throws Exception {
		return null;
	}
	
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getExisteCronogramaDictado(java.lang.String, java.util.Map)
     */
    public Integer getExisteCronogramaDictado(String dataSource, Map params) throws Exception {
		return new Integer(0); 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void insertCronogramaDictado(String dataSource, Map params) throws Exception {
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#updateCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void updateCronogramaDictado(String dataSource, Map params) throws Exception {
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getEnvioExisteCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public Integer getEnvioExisteCronogramaDictado(String dataSource, Map params) throws Exception {
		Integer contador = new Integer(0);
		return contador;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void deleteCronogramaDictado(String dataSource, Map params) throws Exception {
		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertEnvioCronogramaDictado(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertEnvioCronogramaDictado(String dataSource, Map params, final List lista) throws Exception {
		return;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertGenerarPlanillaProgramacion(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertGenerarPlanillaProgramacion(String dataSource, Map params, final List lista) throws Exception {
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#deleteEnvioCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void deleteEnvioCronogramaDictado(String dataSource,  Map params) throws Exception {
		
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#limpiarCronogramaDictado(java.lang.String, java.util.Map, java.util.List)
	 */
	public void limpiarCronogramaDictado(String dataSource, Map params) throws Exception {
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getMigracionComercialConsultora(java.lang.String, java.util.Map)
	 */
	public List getMigracionComercialConsultora(String dataSource, Map params) throws Exception {
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertEnvioStatusConsultora(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertEnvioStatusConsultora(String dataSource, Map params, final List lista) throws Exception {
		
	}

	public void setParams(Map params) {
		
	}

	public Integer getExisteCursoCapacitacion(String fuenteDatos, Map params) {
		return new Integer(0);
	}



	public List getCodigoMensajeEquivalencia(String fuenteDatos, Map params) throws Exception {
		List listMensajeEquiv = getSqlMapClientTemplate().queryForList(
				"edu.GenericoEDUComercialORACLESQL.getCodigoMensajeEquivalencia",params);
		return listMensajeEquiv;
	}

	public void insertConsultorasConfirmanCursoCosto(String fuenteDatos, List list, String numeroLote,Map params) throws Exception {
		//implementado en paises en sicc se relaiza un proceso de confirmacion despues del envio de	costo
	}

	public void deleteConsultorasConfirmanCursoCosto(String fuenteDatos, Map params) {
		// TODO Auto-generated method stub
		
	}

	public String executeFakeProcesoCursoNoFacturados(String fuenteDatos, Map params) {
		return "1";
	}

	public List getPedidosComercialCUV(String fuenteDatos, Map params)  throws Exception{
		try {
			//SE OBTIENE LA LISTA DE CUV (codigos equivalentes de venta )REGISTRADOS EN EDUCACION
			String [] listCuv =getListaCuv(params);
			params.put("listCuv",listCuv);
			String indicadorNombreCompleto = (String) params.get("indicadorNombreCompleto");
			if (Constants.EDU_INDICADOR_NOMBRE_COMPLETO.equals(indicadorNombreCompleto))
				listComercial = getSqlMapClientTemplate().queryForList(
						"edu.GenericoEDUComercialORACLESQL.getPedidosConsultoraNombreCompletoCUV",
						params);
			else	
				listComercial = getSqlMapClientTemplate().queryForList(
						"edu.GenericoEDUComercialORACLESQL.getPedidosConsultoraCUV",
						params);
			return listComercial;
		} catch (Exception e) {
			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.pedidoConsultoraCUV", (Usuario)params.get("usuario"));
			throw new Exception(mensajeError + "" + e.getMessage());
		}
	}

	private String[] getListaCuv(Map params) throws Exception {
		String [] listCuv = new String[0];
		List list = getSqlMapClientTemplate().queryForList(
				"edu.GenericoEDUComercialORACLESQL.getListaCuv",
				params);
		log.debug(" getListaCuv "+ list.size());
		
		Iterator it = list.iterator();
		int i=0;
		listCuv = new String[list.size()];
		while(it.hasNext()){
			String codigoCuv = (String)it.next();
			listCuv[i++]=codigoCuv;
		}
		return listCuv;
	}

	public List getListRegionesACerrar(String fuenteDatos, Map params) throws Exception {
		List listComercial=null;
		try {
			listComercial = getSqlMapClientTemplate().queryForList(
						"edu.GenericoEDUComercialORACLESQL.getListRegionesACerrar",
						params);
		
		} catch (Exception e) {
			 log.debug("error " + e.getMessage());
//			String mensajeError = this.getKeyMessage("genericoEDUComercial.error.pedidoConsultoraCUV", (Usuario)params.get("usuario"));
//			throw new Exception(mensajeError + "" + e.getMessage());
		}
		return listComercial;
	}
	
	
	
	 /* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONRegionesComercial(java.lang.String, java.util.Map)
	 */
	public List getZONRegionesComercial(String dataSource, Map params) throws Exception  {
	    	return null;
	    }
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONZonasComercial(java.lang.String, java.util.Map)
	 */
	public List getZONZonasComercial(String dataSource, Map params) throws Exception {
    	return null;
    }

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONControlFacturacionComercial(java.lang.String, java.util.Map)
	 */
	public List getZONControlFacturacionComercial(String dataSource, Map params) throws Exception {
    	return null;
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONCampanhaComercial(java.lang.String, java.util.Map)
	 */
	public List getZONCampanhaComercial(String dataSource, Map params) throws Exception {
    	return null;
    }
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#getZONConsultorasComercial(java.lang.String, java.util.Map)
	 */
	public List getZONConsultorasComercial(String dataSource, Map params, int skip, int max) throws Exception {
    	return null;
    }
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.gen.GenericoEDUComercialDAO#insertZONRegionesComercial(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertZONRegionesComercial(String dataSource, final Map params, final List list) throws Exception {
    	
    }
	
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertZONZonasComercial(java.lang.String, java.util.Map, java.util.List)
     */
    public void insertZONZonasComercial(String dataSource, final Map params, final List list) throws Exception {
    	
    	
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertZONHistorialGerentesComercial(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertZONHistorialGerentesComercial(String dataSource, final Map params, final List list) throws Exception {
		
		
	}
}