package biz.belcorp.ssicc.service.edu.gen.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.edu.MantenimientoEDUGenericoDAO;
import biz.belcorp.ssicc.dao.edu.gen.GenericoEDUComercialDAO;
import biz.belcorp.ssicc.dao.edu.model.ConexionExterna;
import biz.belcorp.ssicc.service.edu.gen.GenericoEDUFacadeService;
import biz.belcorp.ssicc.service.framework.impl.BaseService;

/**
 * 
 * @author David Hinostroza Vintes
 * 
 */
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class GenericoEDUFacadeServiceImpl extends BaseService implements GenericoEDUFacadeService {

	private Map genericoImplementations;

	private Map dataSourcePaises;

	private MantenimientoEDUGenericoDAO mantenimientoEDUGenericoDAO;
	
	protected GenericoEDUComercialDAO genericoImpl;
	
	protected String fuenteDatos;

	protected transient final Log log = LogFactory.getLog(getClass());
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getPedidosComercial(java.lang.String, java.util.Map)
	 */
	public List getPedidosComercial(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return genericoImpl.getPedidosComercial(fuenteDatos, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getPedidosCursosFacturados(java.lang.String, java.util.Map)
	 */
	public List getPedidosCursosFacturados(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return genericoImpl.getPedidosCursosFacturados(fuenteDatos, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getPedidosCursosNoFacturados(java.lang.String, java.util.Map)
	 */
	public List getPedidosCursosNoFacturados(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return genericoImpl.getPedidosCursosNoFacturados(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getPedidosComercialFacturados(java.lang.String, java.util.Map)
	 */
	public List getPedidosComercialFacturados(String codigoPais, Map params)throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return genericoImpl.getPedidosComercialFacturados(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getConsultorasNuevas(java.lang.String, java.util.Map)
	 */
	public List getConsultorasNuevas(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return genericoImpl.getConsultorasNuevas(fuenteDatos, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getDetalleProducto(java.lang.String, java.util.Map)
	 */
	public List getDetalleProducto(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return  genericoImpl.getDetalleProducto(fuenteDatos, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getMatrizProducto(java.lang.String, java.util.Map)
	 */
	public List getMatrizProducto(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return  genericoImpl.getMatrizProducto(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getRegionesComercial(java.lang.String, java.util.Map)
	 */
	public List getRegionesComercial(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		log.debug("params after "+params);
		return genericoImpl.getRegionesComercial(fuenteDatos, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getZonasComercial(java.lang.String, java.util.Map)
	 */
	public List getZonasComercial(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		log.debug("params after "+params);
		return genericoImpl.getZonasComercial(fuenteDatos, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getControlFacturacionComercial(java.lang.String, java.util.Map)
	 */
	public List getControlFacturacionComercial(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		log.debug("params after "+params);
		return genericoImpl.getControlFacturacionComercial(fuenteDatos, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertConsultorasAptas(java.lang.String, java.util.List, java.lang.String, java.util.Map)
	 */
	public void insertConsultorasAptas(String codigoPais, final List list, String numeroLote, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.insertConsultorasAptas(fuenteDatos, list,numeroLote,params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertConsultorasAptasCosto(java.lang.String, java.util.List, java.lang.String, java.util.Map)
	 */
	public void insertConsultorasAptasCosto(String codigoPais, final List list, String numeroLote, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.insertConsultorasAptasCosto(fuenteDatos, list,numeroLote,params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertConsultorasAptasporProgramar(java.lang.String, java.util.List, java.lang.String, java.lang.String, java.util.Map)
	 */
	public void insertConsultorasAptasporProgramar(String codigoPais, final List list,String numeroLote, String periodo,Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.insertConsultorasAptasporProgramar(fuenteDatos, list,numeroLote, periodo,params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getConsultorasAptasporProgramar(java.lang.String, java.util.Map)
	 */
	public List getConsultorasAptasporProgramar(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return genericoImpl.getConsultorasAptasporProgramar(fuenteDatos, params);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertParametrosCursoCapacitacion(java.lang.String, java.util.Map)
	 */
	public void insertParametrosCursoCapacitacion(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		
		genericoImpl.insertParametrosCursoCapacitacion(fuenteDatos, params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#updateParametrosCursoCapacitacion(java.lang.String, java.util.Map)
	 */
	public void updateParametrosCursoCapacitacion(String codigoPais, Map params)throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.updateParametrosCursoCapacitacion(fuenteDatos, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#deleteConsultorasAptas(java.lang.String, java.util.Map)
	 */
	public void deleteConsultorasAptas(String codigoPais, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.deleteConsultorasAptas(fuenteDatos, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#deleteConsultorasAptasCosto(java.lang.String, java.util.Map)
	 */
	public void deleteConsultorasAptasCosto(String codigoPais, Map params) {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.deleteConsultorasAptasCosto(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#deleteConsultorasAptasProgramar(java.lang.String, java.util.Map)
	 */
	public void deleteConsultorasAptasProgramar(String codigoPais, Map params) {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.deleteConsultorasAptasProgramar(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertAptasHistoricas(java.lang.String, java.util.List, java.util.Map)
	 */
	public void insertAptasHistoricas(String codigoPais,final List list,Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.insertAptasHistoricas(fuenteDatos, list,params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#deleteAptasHistoricas(java.lang.String, java.util.Map)
	 */
	public void deleteAptasHistoricas(String codigoPais, Map params)throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.deleteAptasHistoricas(fuenteDatos, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#deleteBeneficiosCapacitadas(java.lang.String, java.util.Map)
	 */
	public void deleteBeneficiosCapacitadas(String codigoPais, Map params)throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
	//	genericoImpl.deleteBeneficiosCapacitadas(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertMantenimientoClasificacion(java.lang.String, java.util.Map)
	 */
	public void insertMantenimientoClasificacion(String dataSource, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.insertMantenimientoClasificacion(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertBeneficiosCapacitadas(java.lang.String, java.util.Map)
	 */
	public void insertBeneficiosCapacitadas(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.insertBeneficiosCapacitadas(fuenteDatos, params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getExisteEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public Integer getExisteEmpresaComercializadora(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		Integer contador =  genericoImpl.getExisteEmpresaComercializadora(fuenteDatos, params);
		return contador; 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public void insertEmpresaComercializadora(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.insertEmpresaComercializadora(fuenteDatos, params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#updateEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public void updateEmpresaComercializadora(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.updateEmpresaComercializadora(fuenteDatos, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#deleteEmpresaComercializadora(java.lang.String, java.util.Map)
	 */
	public void deleteEmpresaComercializadora(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.deleteEmpresaComercializadora(fuenteDatos, params);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getExisteMensajeEducacion(java.lang.String, java.util.Map)
	 */
	public Integer getExisteMensajeEducacion(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		Integer contador =  genericoImpl.getExisteMensajeEducacion(fuenteDatos, params);
		return contador; 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertMensajeEducacion(java.lang.String, java.util.Map)
	 */
	public void insertMensajeEducacion(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.insertMensajeEducacion(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#updateMensajeEducacion(java.lang.String, java.util.Map)
	 */
	public void updateMensajeEducacion(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.updateMensajeEducacion(fuenteDatos, params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#deleteMensajeEducacion(java.lang.String, java.util.Map)
	 */
	public void deleteMensajeEducacion(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.deleteMensajeEducacion(fuenteDatos, params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertBloqueoConsultora(java.lang.String, java.util.List)
	 */
	public void insertBloqueoConsultora(String dataSource, final List list,Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.insertBloqueoConsultora(fuenteDatos, list,params);
	}
	
	public void deleteBloqueoConsultora(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.deleteBloqueoConsultora(fuenteDatos, params);
	}

	
	public GenericoEDUComercialDAO getGenericoImplementations(String codigo) {
		Map params = new HashMap();
		params.put("codigoPais", codigo);
		ConexionExterna conexion = mantenimientoEDUGenericoDAO.getConexionExternaByCriteria(params);
		return (GenericoEDUComercialDAO) genericoImplementations.get(conexion.getTipoBaseDatosExterna());
	}

	//sb
	public GenericoEDUComercialDAO getGenericoImplementations(String codigo,Map params) {
		log.debug("ini getGenericoImplementations");
		if(params==null) params = new HashMap();
		params.remove("codigoPais");
		params.put("codigoPais", codigo);
		
		//se obtiene conexion : ORA,FOX,AS4
		ConexionExterna conexion = mantenimientoEDUGenericoDAO.getConexionExternaByCriteria(params);
		params.put("tipoConexion",conexion.getTipoBaseDatosExterna());
		
		genericoImpl=(GenericoEDUComercialDAO)genericoImplementations.get(conexion.getTipoBaseDatosExterna());
		setParams(genericoImpl,params);
		return genericoImpl;
	}
	
	public void setGenericoImplementations(Map genericoImplementations) {
		this.genericoImplementations = genericoImplementations;
	}

	public String getDataSourcePaises(String codigo) {
		return (String) dataSourcePaises.get(codigo);
	}

	public void setDataSourcePaises(Map dataSourcePaises) {
		this.dataSourcePaises = dataSourcePaises;
	}

	/**
	 * Efectua la conexion adecuada a la Base de Datos respectiva en base al
	 * Pais indicado
	 * 
	 * @param codigoPais
	 */
	protected void cargarConexionBaseDatos(String codigoPais) {
		this.fuenteDatos = getDataSourcePaises(codigoPais);
		this.genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getBeneficiosCapacitadas(java.lang.String, java.util.Map)
	 */
	public List getBeneficiosCapacitadas(String codigoPais, Map params)throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return genericoImpl.getBeneficiosCapacitadas(fuenteDatos, params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertBeneficiosCapacitadas(java.lang.String, java.util.List, java.util.Map)
	 */
	public void insertBeneficiosCapacitadas(String codigoPais,final List list,Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.insertBeneficiosCapacitadas(fuenteDatos, list,params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#updateBeneficiosCapacitadas(java.lang.String, java.util.Map)
	 */
	public void updateBeneficiosCapacitadas(String codigoPais, Map params){
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.updateBeneficiosCapacitadas(fuenteDatos, params);
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertMantenimientoCodVenta(java.lang.String, java.util.Map)
	 */
	public void insertMantenimientoCodVenta(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.insertMantenimientoCodVenta(fuenteDatos, params);
		
	}

	/*******************Mantinimiento de la Tabla COMERFTL.EDPARCLA *********************/
	
	public void insertMantenimientoClasificacion(String codigoPais,final List list,Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.insertMantenimientoClasificacion(fuenteDatos, list,params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getMantenimientoClasificacion(java.lang.String, java.util.Map)
	 */
	public List getMantenimientoClasificacion(String codigoPais, Map params)throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return genericoImpl.getMantenimientoClasificacion(fuenteDatos, params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#updateMantenimientoClasificacion(java.lang.String, java.util.Map)
	 */
	public void updateMantenimientoClasificacion(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.updateMantenimientoClasificacion(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#deleteMantenimientoClasificacion(java.lang.String, java.util.Map)
	 */
	public void deleteMantenimientoClasificacion(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.deleteMantenimientoClasificacion(fuenteDatos, params);
	}

	
	/*******************Mantinimiento de la Tabla INVITACIONES *********************/	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertMantenimientoClasificacionInvi(java.lang.String, java.util.Map)
	 */
	public void insertMantenimientoClasificacionInvi(String dataSource, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.insertMantenimientoClasificacionInvi(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getMantenimientoClasificacionInvi(java.lang.String, java.util.Map)
	 */
	public List getMantenimientoClasificacionInvi(String codigoPais, Map params)throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return genericoImpl.getMantenimientoClasificacionInvi(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#updateMantenimientoClasificacionInvi(java.lang.String, java.util.Map)
	 */
	public void updateMantenimientoClasificacionInvi(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.updateMantenimientoClasificacionInvi(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#deleteMantenimientoClasificacionInvi(java.lang.String, java.util.Map)
	 */
	public void deleteMantenimientoClasificacionInvi(String codigoPais, Map params) throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.deleteMantenimientoClasificacionInvi(fuenteDatos, params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getRecodificacionConsultora(java.lang.String, java.util.Map)
	 */
	public List getRecodificacionConsultora(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		return genericoImpl.getRecodificacionConsultora(fuenteDatos, params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getExisteCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public Integer getExisteCronogramaDictado(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		Integer contador =  genericoImpl.getExisteCronogramaDictado(fuenteDatos, params);
		return contador; 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void insertCronogramaDictado(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.insertCronogramaDictado(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#updateCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void updateCronogramaDictado(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.updateCronogramaDictado(fuenteDatos, params);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertGenerarPlanillaProgramacion(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertGenerarPlanillaProgramacion(String dataSource, Map params, List lista) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.insertGenerarPlanillaProgramacion(fuenteDatos, params, lista);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#deleteCronogramaDictado(java.lang.String, java.util.Map)
	 */
	public void deleteCronogramaDictado(String codigoPais, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.deleteCronogramaDictado(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertGenerarPlanillaProgramacion(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertEnvioCronogramaDictado(String dataSource, Map params, List lista) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.insertEnvioCronogramaDictado(fuenteDatos, params, lista);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getMigracionComercialConsultora(java.lang.String, java.util.Map)
	 */
	public List getMigracionComercialConsultora(String dataSource, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		return genericoImpl.getMigracionComercialConsultora(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#limpiarCronogramaDictado(java.lang.String, java.util.Map, java.util.List)
	 */
	public void limpiarCronogramaDictado(String dataSource, Map params) throws Exception {
		fuenteDatos  = getDataSourcePaises(dataSource);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(dataSource,params);
		genericoImpl.limpiarCronogramaDictado(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertEnvioStatusConsultora(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertEnvioStatusConsultora(String codigoPais, Map params, List lista) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.insertEnvioStatusConsultora(fuenteDatos, params, lista);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getExisteCursoCapacitacion(java.lang.String, java.util.Map)
	 */
	public Integer getExisteCursoCapacitacion(String codigoPais, Map params)throws Exception  {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		Integer contador =  genericoImpl.getExisteCursoCapacitacion(fuenteDatos, params);
		return contador; 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getTipoClasificacionEquivalencia(java.lang.String, java.util.Map)
	 */
	public List getTipoClasificacionEquivalencia(String codigoPais, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		List lista =  genericoImpl.getTipoClasificacionEquivalencia(fuenteDatos, params);
		return lista; 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getCodigoClasificacionEquivalencia(java.lang.String, java.util.Map)
	 */
	public List getCodigoClasificacionEquivalencia(String codigoPais, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		List lista =  genericoImpl.getCodigoClasificacionEquivalencia(fuenteDatos, params);
		return lista; 
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#executeEnvioEquivalenciaClasificacion(java.lang.String, java.util.Map)
	 */
	public void executeEnvioEquivalenciaClasificacion(String codigoPais, Map params)throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.executeEnvioEquivalenciaClasificacion(fuenteDatos, params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertConsultorasAptasMixtoBloqueo(java.lang.String, java.util.List, java.lang.String, java.util.Map)
	 */
	public void insertConsultorasAptasMixtoBloqueo(String codigoPais, final List list, String numeroLote,Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.insertConsultorasAptasCosto(fuenteDatos, list,numeroLote,params);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#executeBorrarEquivalenciaMensaje(java.lang.String, java.util.Map)
	 */
	public void executeBorrarEquivalenciaMensaje(String codigoPais, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.executeBorrarEquivalenciaMensaje(fuenteDatos, params);
	}
	
	
	/**
	 * @return Returns the mantenimientoEDUGenericoDAO.
	 */
	public MantenimientoEDUGenericoDAO getMantenimientoEDUGenericoDAO() {
		return mantenimientoEDUGenericoDAO;
	}

	/**
	 * @param mantenimientoEDUGenericoDAO The mantenimientoEDUGenericoDAO to set.
	 */
	public void setMantenimientoEDUGenericoDAO(
			MantenimientoEDUGenericoDAO mantenimientoEDUGenericoDAO) {
		this.mantenimientoEDUGenericoDAO = mantenimientoEDUGenericoDAO;
	}
	
	
	/**
	 * @param genericoImpl
	 * @param params
	 */
	private void setParams(GenericoEDUComercialDAO genericoImpl, Map params) {
		 log.debug("setParams");
		 List listParam=mantenimientoEDUGenericoDAO.getParamConexionExterna(params);
		 Iterator it = listParam.iterator();
		 Map paramConexion = new HashMap();
		 while(it.hasNext()){
			 Map map = (Map) it.next();
			 paramConexion.put(map.get("paramConexion"),map.get("valorConexion"));
			 params.put(map.get("paramConexion"),map.get("valorConexion"));
		 }
		 genericoImpl.setParams(paramConexion);
		 //solo se usa para casos en donde el param no es pasado como parametro
		 //en ese caso se utiliza el populateMap
	}

	public List getCodigoMensajeEquivalencia(String codigoPais,Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		List lista =  genericoImpl.getCodigoMensajeEquivalencia(fuenteDatos,params);
		return lista; 
	}

	public void insertConsultorasConfirmanCursoCosto(String codigoPais, List list, String numeroLote, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.insertConsultorasConfirmanCursoCosto(fuenteDatos, list,numeroLote,params);
		
	}

	public void deleteConsultorasConfirmanCursoCosto(String codigoPais, Map params) {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		genericoImpl.deleteConsultorasConfirmanCursoCosto(fuenteDatos, params);
		
	}

	public String executeFakeProcesoCursoNoFacturados(String codigoPais, Map params) {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return genericoImpl.executeFakeProcesoCursoNoFacturados(fuenteDatos, params);
	}

	public List getPedidosComercialCUV(String codigoPais, Map params)  throws Exception{
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return genericoImpl.getPedidosComercialCUV(fuenteDatos, params);
	}

	public List getListRegionesACerrar(String codigoPais, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return  genericoImpl.getListRegionesACerrar(fuenteDatos,params);
	}

		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getZONRegionesComercial(java.lang.String, java.util.Map)
	 */
	public List getZONRegionesComercial(String codigoPais, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return  genericoImpl.getZONRegionesComercial(fuenteDatos,params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getZONZonasComercial(java.lang.String, java.util.Map)
	 */
	public List getZONZonasComercial(String codigoPais, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return  genericoImpl.getZONZonasComercial(fuenteDatos,params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getZONControlFacturacionComercial(java.lang.String, java.util.Map)
	 */
	public List getZONControlFacturacionComercial(String codigoPais, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return  genericoImpl.getZONControlFacturacionComercial(fuenteDatos,params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getZONCampanhaComercial(java.lang.String, java.util.Map)
	 */
	public List getZONCampanhaComercial(String codigoPais, Map params) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return  genericoImpl.getZONCampanhaComercial(fuenteDatos,params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#getZONConsultorasComercial(java.lang.String, java.util.Map)
	 */
	public List getZONConsultorasComercial(String codigoPais, Map params, int skip, int max) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		return  genericoImpl.getZONConsultorasComercial(fuenteDatos, params, skip, max);
	}
	
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertZONRegionesComercial(java.lang.String, java.util.Map, java.util.List)
     */
    public void insertZONRegionesComercial(String codigoPais, final Map params, final List list) throws Exception {
    	fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		this.genericoImpl.insertZONRegionesComercial(fuenteDatos,params, list);
    }
	
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertZONZonasComercial(java.lang.String, java.util.Map, java.util.List)
     */
    public void insertZONZonasComercial(String codigoPais, final Map params, final List list) throws Exception {
    	fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		this.genericoImpl.insertZONZonasComercial(fuenteDatos,params, list);
    	
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.service.gen.GenericoEDUFacadeService#insertZONHistorialGerentesComercial(java.lang.String, java.util.Map, java.util.List)
	 */
	public void insertZONHistorialGerentesComercial(String codigoPais, final Map params, final List list) throws Exception {
		fuenteDatos = getDataSourcePaises(codigoPais);
		genericoImpl = (GenericoEDUComercialDAO) getGenericoImplementations(codigoPais,params);
		this.genericoImpl.insertZONHistorialGerentesComercial(fuenteDatos,params, list);
		
	}
	
	
	
}
