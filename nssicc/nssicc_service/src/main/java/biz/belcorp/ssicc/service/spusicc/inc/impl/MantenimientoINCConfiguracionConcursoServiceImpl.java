package biz.belcorp.ssicc.service.spusicc.inc.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.EstatusRechazoFLX;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.inc.MantenimientoINCConfiguracionConcursoDAO;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINC;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINCDetalle;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoAmbitoGeografico;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoArticuloLote;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoArticuloLoteDescuento;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoBonificacionPeriodo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoClasificacionParticipante;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoDespachoPremios;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoEstatusVenta;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoLotePremioArticulo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoMontoVentas;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoNivelPremiacion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoObtencionPuntos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoParametrosConsultoras;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoParametrosGenerales;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoParametrosPremiacion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoPeriodoDespacho;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoPremioArticulo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosBonificados;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosExcluidos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosExigidos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoProductosValidos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoPuntajeExigido;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoRecomendadaPeriodo;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoRequisitoPremiacion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConcursoVersion;
import biz.belcorp.ssicc.dao.spusicc.inc.model.NuevaConstanciaProgramaPuntos;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService;
import biz.belcorp.ssicc.dao.spusicc.inc.model.ConstanciaProgramaPuntos;
import biz.belcorp.ssicc.dao.spusicc.inc.model.RangoConstanciaProgramaPuntos;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Service("spusicc.mantenimientoINCConfiguracionConcursoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class MantenimientoINCConfiguracionConcursoServiceImpl extends BaseService implements
	MantenimientoINCConfiguracionConcursoService {

	@Resource(name="spusicc.mantenimientoINCConfiguracionConcursoDAO")
	MantenimientoINCConfiguracionConcursoDAO mantenimientoINCConfiguracionConcursoDAO;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getClasificacionesConcurso(java.util.Map)
	 */
	public List getClasificacionesConcurso() {
		return mantenimientoINCConfiguracionConcursoDAO.getClasificacionesConcurso();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getConcursosByCriteria(java.util.Map)
	 */
	public List getConcursosByCriteria(Map criteria) {
		return mantenimientoINCConfiguracionConcursoDAO.getConcursosByCriteria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#deleteConfiguracionConcurso(java.util.Map)
	 */
	public void deleteConfiguracionConcurso(Map params) {
		mantenimientoINCConfiguracionConcursoDAO.deleteConcurso(params);
		mantenimientoINCConfiguracionConcursoDAO.deleteConcursoVersion(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getTiposPrograma()
	 */
	public List getTiposPrograma() {
		return mantenimientoINCConfiguracionConcursoDAO.getTiposPrograma();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getBaseCalculo()
	 */
	public List getBaseCalculo() {
		return mantenimientoINCConfiguracionConcursoDAO.getBaseCalculo();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getDirigidos()
	 */
	public List getDirigidos() {
		return mantenimientoINCConfiguracionConcursoDAO.getDirigidos();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getTipoVenta()
	 */
	public List getTipoVenta() {
		return mantenimientoINCConfiguracionConcursoDAO.getTipoVenta();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getTipoExigencia()
	 */
	public List getTipoExigencia() {
		return mantenimientoINCConfiguracionConcursoDAO.getTipoExigencia();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getConcursosRecomendadas()
	 */
	public List getConcursosRecomendadas() {
		return mantenimientoINCConfiguracionConcursoDAO.getConcursosRecomendadas();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getEstatusCliente()
	 */
	public List getEstatusCliente() {
		return mantenimientoINCConfiguracionConcursoDAO.getEstatusCliente();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getClasificacionesParticipante()
	 */
	public List getClasificacionesParticipante() {
		return mantenimientoINCConfiguracionConcursoDAO.getClasificacionesParticipante();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getTiposPremiacion()
	 */
	public List getTiposPremiacion() {
		return mantenimientoINCConfiguracionConcursoDAO.getTiposPremiacion();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getTiposEleccion()
	 */
	public List getTiposEleccion() {
		return mantenimientoINCConfiguracionConcursoDAO.getTiposEleccion();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getCentrosServicio()
	 */
	public List getCentrosServicio() {
		return mantenimientoINCConfiguracionConcursoDAO.getCentrosServicio();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getTiposProducto()
	 */
	public List getTiposProducto() {
		return mantenimientoINCConfiguracionConcursoDAO.getTiposProducto();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getTiposOferta()
	 */
	public List getTiposOferta() {
		return mantenimientoINCConfiguracionConcursoDAO.getTiposOferta();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getTiposAgrupacion()
	 */
	public List getTiposAgrupacion() {
		return mantenimientoINCConfiguracionConcursoDAO.getTiposAgrupacion();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getUnidadesNegocio()
	 */
	public List getUnidadesNegocio() {
		return mantenimientoINCConfiguracionConcursoDAO.getUnidadesNegocio();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getSuperGenericosByCriteria(java.util.Map)
	 */
	public List getSuperGenericosByCriteria(Map criteria) {
		return mantenimientoINCConfiguracionConcursoDAO.getSuperGenericosByCriteria(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getGenericosByCriteria(java.util.Map)
	 */
	public List getGenericosByCriteria(Map criteria) {
		return mantenimientoINCConfiguracionConcursoDAO.getGenericosByCriteria(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getCiclosVida()
	 */
	public List getCiclosVida() {
		return mantenimientoINCConfiguracionConcursoDAO.getCiclosVida();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getNegocios()
	 */
	public List getNegocios() {
		return mantenimientoINCConfiguracionConcursoDAO.getNegocios();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getMarcaProductos()
	 */
	public List getMarcaProductos() {
		return mantenimientoINCConfiguracionConcursoDAO.getMarcaProductos();
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#insertConfiguracionConcurso(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoParametrosGenerales, biz.belcorp.ssicc.model.Usuario)
     */
    public void insertConfiguracionConcurso(ConcursoParametrosGenerales concursoParametrosGenerales, Usuario usuario) throws Exception {
    	Long oidConcurso = mantenimientoINCConfiguracionConcursoDAO.getSecuenciaConcursoParametrosGenerales();
    	
    	//Insertamos registro en la entidad Concurso Parametros Generales
    	concursoParametrosGenerales.setOid(oidConcurso);
    	concursoParametrosGenerales.setNumeroConcurso(mantenimientoINCConfiguracionConcursoDAO.getUltimoNumeroConcurso());
    	mantenimientoINCConfiguracionConcursoDAO.insertConcursoParametrosGenerales(concursoParametrosGenerales, usuario);
    	
    	//Insertamos registro en la entidad Concurso Version
    	ConcursoVersion concursoVersion = concursoParametrosGenerales.getConcursoVersion();
    	concursoVersion.setOidConcurso(oidConcurso);
    	concursoVersion.setOidConcursoOrigen(oidConcurso);
    	concursoVersion.setNumeroConcurso(concursoParametrosGenerales.getNumeroConcurso());
    	mantenimientoINCConfiguracionConcursoDAO.insertConcursoVersion(concursoVersion, usuario);
    	
    	//Insertamos registro en la entidad Concurso Obtencion Puntos
    	ConcursoObtencionPuntos concursoObtencionPuntos = concursoParametrosGenerales.getConcursoObtencionPuntos();
    	concursoObtencionPuntos.setOidConcurso(oidConcurso);
    	concursoObtencionPuntos.setIndicadorPuntajeAcumulativo(new Integer(1));
    	mantenimientoINCConfiguracionConcursoDAO.insertConcursoObtencionPuntos(concursoObtencionPuntos, usuario);

    	//Insertamos registro en la entidad Concurso Despacho Premios
    	ConcursoDespachoPremios concursoDespachoPremios = concursoParametrosGenerales.getConcursoDespachoPremios();
    	concursoDespachoPremios.setOidConcurso(oidConcurso);
    	mantenimientoINCConfiguracionConcursoDAO.insertConcursoDespachoPremios(concursoDespachoPremios, usuario);
    	
    	//Insertamos la lista de registros en la entidad Concurso Ambito Geografico
    	if(concursoParametrosGenerales.getListConcursoAmbitoGeografico() != null) {
	    	List listConcursoAmbitoGeografico = concursoParametrosGenerales.getListConcursoAmbitoGeografico();
	    	for(int i=0; i<listConcursoAmbitoGeografico.size(); i++) {
	    		ConcursoAmbitoGeografico concursoAmbitoGeografico = (ConcursoAmbitoGeografico)listConcursoAmbitoGeografico.get(i);
	    		
	    		concursoAmbitoGeografico.setOidConcurso(oidConcurso);
	        	mantenimientoINCConfiguracionConcursoDAO.insertConcursoAmbitoGeografico(concursoAmbitoGeografico, usuario);
	    	}
    	}
    	
    	//Insertamos registro en la entidad Concurso Parametros Consultoras
    	ConcursoParametrosConsultoras concursoParametrosConsultoras = concursoParametrosGenerales.getConcursoParametrosConsultoras();
    	if(concursoParametrosConsultoras != null) {
    		concursoParametrosConsultoras.setOidConcurso(oidConcurso);
    		
    		//Se crea el concurso de Constancia
			if(concursoParametrosConsultoras.getOidConcursoPuntajeRecomendada()==null && 
					concursoParametrosConsultoras.getConcursoRecomendada()!=null) {
				
				ConcursoParametrosGenerales concursoRecomendada = concursoParametrosConsultoras.getConcursoRecomendada();
				
				insertConfiguracionConcurso(concursoRecomendada, usuario);
				concursoParametrosConsultoras.setOidConcursoPuntajeRecomendada(concursoRecomendada.getOid());
			}

    		mantenimientoINCConfiguracionConcursoDAO.insertConcursoParametrosConsultoras(concursoParametrosConsultoras, usuario);
    	}	

    	//Insertamos la lista de registros en la entidad Concurso Monto Ventas
    	if(concursoParametrosGenerales.getListConcursoMontoVentas() != null) {
	    	List listConcursoMontoVentas = concursoParametrosGenerales.getListConcursoMontoVentas();
	    	for(int i=0; i<listConcursoMontoVentas.size(); i++) {
	    		ConcursoMontoVentas concursoMontoVentas = (ConcursoMontoVentas)listConcursoMontoVentas.get(i);
	    		
	    		concursoMontoVentas.setOidConcurso(oidConcurso);
	        	mantenimientoINCConfiguracionConcursoDAO.insertConcursoMontoVentas(concursoMontoVentas, usuario);
	    	}
    	}
    	
    	//Insertamos la lista de registros en la entidad Concurso Estatus Venta
    	if(concursoParametrosGenerales.getListConcursoEstatusVenta() != null) {
	    	List listConcursoEstatusVenta = concursoParametrosGenerales.getListConcursoEstatusVenta();
	    	for(int i=0; i<listConcursoEstatusVenta.size(); i++) {
	    		ConcursoEstatusVenta concursoEstatusVenta = (ConcursoEstatusVenta)listConcursoEstatusVenta.get(i);
	    		
	    		concursoEstatusVenta.setOidConcurso(oidConcurso);
	        	mantenimientoINCConfiguracionConcursoDAO.insertConcursoEstatusVenta(concursoEstatusVenta, usuario);
	    	}
    	}
    	
    	//Insertamos la lista de registros en la entidad Concurso Clasificacion Participantes
    	if(concursoParametrosGenerales.getListConcursoClasificacionParticipante() != null) {
	    	List listConcursoClasificacionParticipante = concursoParametrosGenerales.getListConcursoClasificacionParticipante();
	    	for(int i=0; i<listConcursoClasificacionParticipante.size(); i++) {
	    		ConcursoClasificacionParticipante concursoClasificacionParticipante = 
	    						(ConcursoClasificacionParticipante)listConcursoClasificacionParticipante.get(i);
	    		
	    		concursoClasificacionParticipante.setOidConcurso(oidConcurso);
	        	mantenimientoINCConfiguracionConcursoDAO.insertConcursoClasificacionParticipante(concursoClasificacionParticipante, usuario);
	    	}
    	}

    	//Insertamos la lista de registros en la entidad Concurso Recomendada Periodo
    	if(concursoParametrosGenerales.getListConcursoRecomendadaPeriodo() != null) {
	    	List listConcursoRecomendadaPeriodo = concursoParametrosGenerales.getListConcursoRecomendadaPeriodo();
	    	for(int i=0; i<listConcursoRecomendadaPeriodo.size(); i++) {
	    		ConcursoRecomendadaPeriodo concursoRecomendadaPeriodo = (ConcursoRecomendadaPeriodo)listConcursoRecomendadaPeriodo.get(i);
	    		
	    		concursoRecomendadaPeriodo.setNumeroConcurso(concursoParametrosGenerales.getNumeroConcurso());
	        	mantenimientoINCConfiguracionConcursoDAO.insertConcursoRecomendadaPeriodo(concursoRecomendadaPeriodo, usuario);
	    	}
    	}

    	//Insertamos la lista de registros en la entidad Concurso Bonificacion Periodo
    	if(concursoParametrosGenerales.getListConcursoBonificacionPeriodo() != null) {
	    	List listConcursoBonificacionPeriodo = concursoParametrosGenerales.getListConcursoBonificacionPeriodo();
	    	for(int i=0; i<listConcursoBonificacionPeriodo.size(); i++) {
	    		ConcursoBonificacionPeriodo concursoBonificacionPeriodo = (ConcursoBonificacionPeriodo)listConcursoBonificacionPeriodo.get(i);
	    		
	    		concursoBonificacionPeriodo.setNumeroConcurso(concursoParametrosGenerales.getNumeroConcurso());
	        	mantenimientoINCConfiguracionConcursoDAO.insertConcursoBonificacionPeriodo(concursoBonificacionPeriodo, usuario);
	    	}
    	}
    	
    	//Insertamos la lista de registros en la entidad Concurso Periodo Despacho
    	if(concursoParametrosGenerales.getListConcursoPeriodoDespacho() != null) {
	    	List listConcursoPeriodoDespacho = concursoParametrosGenerales.getListConcursoPeriodoDespacho();
	    	for(int i=0; i<listConcursoPeriodoDespacho.size(); i++) {
	    		ConcursoPeriodoDespacho concursoPeriodoDespacho = (ConcursoPeriodoDespacho)listConcursoPeriodoDespacho.get(i);
	    		concursoPeriodoDespacho.setNumeroConcurso(concursoParametrosGenerales.getNumeroConcurso());
	        	mantenimientoINCConfiguracionConcursoDAO.insertConcursoPeriodoDespacho(concursoPeriodoDespacho, usuario);
	    	}
    	}
    	
    	//Insertamos la lista de registros en la entidad Parmetros Nivel Premio Puntaje Exigido
    	ConcursoPuntajeExigido concursoPuntajeExigidoDelete = new ConcursoPuntajeExigido();
    	concursoPuntajeExigidoDelete.setCodigoPais(concursoParametrosGenerales.getCodigoPais());
    	concursoPuntajeExigidoDelete.setNumeroConcurso(concursoParametrosGenerales.getNumeroConcurso());
    	mantenimientoINCConfiguracionConcursoDAO.deleteConcursoPuntajeExigido(concursoPuntajeExigidoDelete, usuario);
    	if(concursoParametrosGenerales.getListConcursoPuntajeExigido() != null) {
	    	List listConcursoPuntajeExigido = concursoParametrosGenerales.getListConcursoPuntajeExigido();
	    	for(int i=0; i<listConcursoPuntajeExigido.size(); i++) {
	    		ConcursoPuntajeExigido concursoPuntajeExigido = (ConcursoPuntajeExigido)listConcursoPuntajeExigido.get(i);
	    		concursoPuntajeExigido.setNumeroConcurso(concursoParametrosGenerales.getNumeroConcurso());
	        	mantenimientoINCConfiguracionConcursoDAO.insertConcursoPuntajeExigido(concursoPuntajeExigido, usuario);
	    	}
    	}

    	//Insertamos registro en la entidad Concurso Requisitos Premiacion
    	ConcursoRequisitoPremiacion concursoRequisitoPremiacion = concursoParametrosGenerales.getConcursoRequisitoPremiacion();
    	if(concursoRequisitoPremiacion != null) {
    		concursoRequisitoPremiacion.setOidConcurso(oidConcurso);
    		mantenimientoINCConfiguracionConcursoDAO.insertConcursoRequisitoPremiacion(concursoRequisitoPremiacion, usuario);
    	}
    	
    	//Insertamos registros en las entidades de Premiacion: parametros, niveles, premio, lotes, articulos
    	ConcursoParametrosPremiacion concursoParametrosPremiacion = concursoParametrosGenerales.getConcursoParametrosPremiacion();
    	if(concursoParametrosPremiacion != null) {
    		Long oidPremiacion = mantenimientoINCConfiguracionConcursoDAO.getSecuenciaConcursoParametrosPremiacion();
    		
    		concursoParametrosPremiacion.setOid(oidPremiacion);
    		concursoParametrosPremiacion.setOidConcurso(oidConcurso);
    		mantenimientoINCConfiguracionConcursoDAO.insertConcursoParametrosPremiacion(concursoParametrosPremiacion, usuario);
    		
        	//Insertamos registros en las entidades de niveles de premio, lotes, articulos
    		insertNivelesPremiacion(concursoParametrosPremiacion, usuario);
    	}
    	
    	//Insertamos registros en las entidades de Productos
    	ConcursoProductos concursoProductos = concursoParametrosGenerales.getConcursoProductos();
    	if(concursoProductos != null) {
    		Long oidProductos = mantenimientoINCConfiguracionConcursoDAO.getSecuenciaConcursoProductos();
    		
    		concursoProductos.setOid(oidProductos);
    		concursoProductos.setOidConcurso(oidConcurso);
    		mantenimientoINCConfiguracionConcursoDAO.insertConcursoProductos(concursoProductos, usuario);
    		
    		//Insertamos registros en las entidades de Productos Validos, Productos Bonificados,
        	//Productos Excluidos y Productos Exigidos
    		insertProductos(concursoProductos, usuario);
    	}
    	Map criteria = new HashMap();
    	criteria.put("valorServidor", "--");
    	mantenimientoINCConfiguracionConcursoDAO.deleteUltimaActualizacionConcurso(criteria);
    	mantenimientoINCConfiguracionConcursoDAO.insertUltimaActualizacionConcurso(criteria);
    }
    
    private void insertNivelesPremiacion(ConcursoParametrosPremiacion concursoParametrosPremiacion, Usuario usuario) {
    	Long oidPremiacion = concursoParametrosPremiacion.getOid();
    	
    	if(concursoParametrosPremiacion.getListConcursoNivelPremiacion() != null) {
	    	List listConcursoNivelPremiacion = concursoParametrosPremiacion.getListConcursoNivelPremiacion();

    		if(concursoParametrosPremiacion.isIndRedefinirNivelPremiacion()) {
    			Map params = new HashMap();
    			params.put("oidPremiacion", concursoParametrosPremiacion.getOid().toString());
    			params.put("numeroNiveles", String.valueOf(listConcursoNivelPremiacion.size()));
    			mantenimientoINCConfiguracionConcursoDAO.executeEliminarNivelesPremiacion(params);
    		}
    		
	    	//Insertamos registros en la entidad Niveles de Premiacion y Premio Articulo
        	for(int i=0; i<listConcursoNivelPremiacion.size(); i++) {
        		ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion)listConcursoNivelPremiacion.get(i);
        		ConcursoPremioArticulo concursoPremioArticulo = concursoNivelPremiacion.getConcursoPremioArticulo();
        		Long oidNivelPremiacion = concursoNivelPremiacion.getOid();
        		Long oidPremioArticulo = concursoPremioArticulo.getOid();
        		
        		if(concursoParametrosPremiacion.isIndRedefinirNivelPremiacion()) {
        			oidNivelPremiacion = mantenimientoINCConfiguracionConcursoDAO.getSecuenciaConcursoNivelPremiacion();
            		oidPremioArticulo = mantenimientoINCConfiguracionConcursoDAO.getSecuenciaConcursoPremioArticulo();
            		
            		if(concursoNivelPremiacion.getOid()==null) {
                		concursoNivelPremiacion.setOid(oidNivelPremiacion);
                		concursoNivelPremiacion.setOidPremiacion(oidPremiacion);
                		
            			mantenimientoINCConfiguracionConcursoDAO.insertConcursoNivelPremiacion(concursoNivelPremiacion, usuario);
            		} else
            			mantenimientoINCConfiguracionConcursoDAO.updateConcursoNivelPremiacion(concursoNivelPremiacion, usuario);
            		
            		if(concursoPremioArticulo.getOid()==null) {
                		concursoPremioArticulo.setOid(oidPremioArticulo);
                		concursoPremioArticulo.setOidNivelPremiacion(oidNivelPremiacion);
            			
            			mantenimientoINCConfiguracionConcursoDAO.insertConcursoPremioArticulo(concursoPremioArticulo, usuario);
            		} else
            			mantenimientoINCConfiguracionConcursoDAO.updateConcursoPremioArticulo(concursoPremioArticulo, usuario);
            		
                	if(concursoNivelPremiacion.getListConcursoLotePremioArticulo() != null) {
                		List listConcursoLotePremioArticulo = concursoNivelPremiacion.getListConcursoLotePremioArticulo();
                		
                		Map paramsEliminar = new HashMap();
                		//Eliminamos los lotes que no estan siendo enviados de la pantalla
                		String lotesActuales = "0";
                		for(int j=0; j<listConcursoLotePremioArticulo.size(); j++) {
                			ConcursoLotePremioArticulo concursoLotePremioArticulo = 
            						(ConcursoLotePremioArticulo)listConcursoLotePremioArticulo.get(j);
                			
                			if(concursoLotePremioArticulo.getOid()!=null) {
                				if(lotesActuales.length()==0) 
                					lotesActuales = concursoLotePremioArticulo.getOid().toString();
                				else	
                					lotesActuales = lotesActuales + "__" + concursoLotePremioArticulo.getOid().toString();
                			}
                		}
                		if(lotesActuales.length()>0) {
                			String oidLotePremio[] = lotesActuales.split("__");
                			paramsEliminar.put("oidPremioArticulo", concursoPremioArticulo.getOid());
                			paramsEliminar.put("oidLotePremio", oidLotePremio);
                			
                			mantenimientoINCConfiguracionConcursoDAO.deleteArticulosLoteEliminado(paramsEliminar);
                			mantenimientoINCConfiguracionConcursoDAO.deleteLotesPremioArticulo(paramsEliminar);
                		} 
                		
                	    //Insertamos registros en la entidad Lotes de Premio de Articulo
                    	for(int j=0; j<listConcursoLotePremioArticulo.size(); j++) {
                    		ConcursoLotePremioArticulo concursoLotePremioArticulo = 
                    						(ConcursoLotePremioArticulo)listConcursoLotePremioArticulo.get(j);
                    		
                    		if(concursoLotePremioArticulo.getListConcursoArticuloLote().size()>0){
                    			ConcursoArticuloLote indPremWeb = (ConcursoArticuloLote)concursoLotePremioArticulo.getListConcursoArticuloLote().get(0);
                        		concursoLotePremioArticulo.setIndicadorPremiosWeb(indPremWeb.getIndicadorPremiosWeb());
                    		}
                    		
                    		if(concursoLotePremioArticulo.getOid()==null) {
	                    		Long oidLotePremioArticulo = mantenimientoINCConfiguracionConcursoDAO.getSecuenciaConcursoLotePremioArticulo();
	
	                			concursoLotePremioArticulo.setOid(oidLotePremioArticulo);
	                			concursoLotePremioArticulo.setOidPremioArticulo(concursoPremioArticulo.getOid());
	                    		
	                			mantenimientoINCConfiguracionConcursoDAO.insertConcursoLotePremioArticulo(concursoLotePremioArticulo, usuario);
                    		}
                    		else
                    			mantenimientoINCConfiguracionConcursoDAO.updateConcursoLotePremioArticulo(concursoLotePremioArticulo, usuario);
                    			
                    		if(concursoLotePremioArticulo.getListConcursoArticuloLote() != null) {
                    			List listConcursoArticuloLote = concursoLotePremioArticulo.getListConcursoArticuloLote();
                    			
                    			//Eliminamos los articulos que no estan siendo enviados de la pantalla
                        		String articulosActuales = "0";
                        		for(int k=0; k<listConcursoArticuloLote.size(); k++) {
                        			ConcursoArticuloLote concursoArticuloLote = 
                    						(ConcursoArticuloLote)listConcursoArticuloLote.get(k);
                        			
                        			if(concursoArticuloLote.getOid()!=null) {
                        				if(articulosActuales.length()==0) 
                        					articulosActuales = concursoArticuloLote.getOid().toString();
                        				else
                        					articulosActuales = articulosActuales + "__" + concursoArticuloLote.getOid().toString();
                        			}
                        		}
                        		if(articulosActuales.length()>0) {
                        			String oidArticuloLote[] = articulosActuales.split("__");
                        			paramsEliminar.put("oidLotePremio", concursoLotePremioArticulo.getOid());
                        			paramsEliminar.put("oidArticuloLote", oidArticuloLote);
                        			
                        			mantenimientoINCConfiguracionConcursoDAO.deleteArticulosLote(paramsEliminar);
                        		}
                        		
                        	    //Insertamos registros en la entidad Articulos de Lote
                            	for(int k=0; k<listConcursoArticuloLote.size(); k++) {
                            		ConcursoArticuloLote concursoArticuloLote = 
                            						(ConcursoArticuloLote)listConcursoArticuloLote.get(k);
                            		
                            		concursoArticuloLote.setOidLotePremioArticulo(concursoLotePremioArticulo.getOid());
                            		
                            		if(StringUtils.isEmpty(concursoArticuloLote.getCodigoVentaFicticio())) {
                            			String codigoVenta = mantenimientoINCConfiguracionConcursoDAO.getUltimoContadorArticulo();
                            			concursoArticuloLote.setCodigoVentaFicticio(codigoVenta);
                            		}
                            		
                            		if(concursoArticuloLote.getOid()==null) 
                            			mantenimientoINCConfiguracionConcursoDAO.insertConcursoArticuloLote(concursoArticuloLote, usuario);
                           			else
                            			mantenimientoINCConfiguracionConcursoDAO.updateConcursoArticuloLote(concursoArticuloLote, usuario);	
                            	}		
                    		}
                    		
                    		//concursoArticuloLoteDescuento insertamos
                    		if(concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento() != null) {
                    			List listConcursoArticuloLoteDescuento= concursoLotePremioArticulo.getListConcursoArticuloLoteDescuento();
                    			
                    			//Eliminamos los articulos que no estan siendo enviados de la pantalla
                        		String articulosActualesDescuento = "0";
                        		for(int k=0; k<listConcursoArticuloLoteDescuento.size(); k++) {
                        			ConcursoArticuloLoteDescuento concursoArticuloLoteDescuento = 
                    						(ConcursoArticuloLoteDescuento)listConcursoArticuloLoteDescuento.get(k);
                        			
                        			if(concursoArticuloLoteDescuento.getOid()!=null) {
                        				if(articulosActualesDescuento.length()==0) 
                        					articulosActualesDescuento = concursoArticuloLoteDescuento.getOid().toString();
                        				else
                        					articulosActualesDescuento = articulosActualesDescuento + "__" + concursoArticuloLoteDescuento.getOid().toString();
                        			}
                        		}
                        		
                        		if(articulosActualesDescuento.length()>0) {
                        			String oidArticuloLote[] = articulosActualesDescuento.split("__");
                        			paramsEliminar.put("oidLotePremio", concursoLotePremioArticulo.getOid());
                        			paramsEliminar.put("oidArticuloLote", oidArticuloLote);
                        			
                        			mantenimientoINCConfiguracionConcursoDAO.deleteArticulosLoteDescuento(paramsEliminar);
                        		}
                        		
                        		 //Insertamos registros en la entidad Articulos de Lote Descuento
                            	for(int k=0; k<listConcursoArticuloLoteDescuento.size(); k++) {
                            		ConcursoArticuloLoteDescuento concursoArticuloLoteDescuento = 
                            						(ConcursoArticuloLoteDescuento)listConcursoArticuloLoteDescuento.get(k);
                            		
                            		concursoArticuloLoteDescuento.setOidLotePremioArticulo(concursoLotePremioArticulo.getOid());
                            		
                            		if(StringUtils.isEmpty(concursoArticuloLoteDescuento.getCodigoVentaFicticio())) {
                            			String codigoVenta = mantenimientoINCConfiguracionConcursoDAO.getUltimoContadorArticulo();
                            			concursoArticuloLoteDescuento.setCodigoVentaFicticio(codigoVenta);
                            		}
                            		
                            		if(concursoArticuloLoteDescuento.getOid()==null) 
                            			mantenimientoINCConfiguracionConcursoDAO.insertConcursoArticuloLoteDescuento(concursoArticuloLoteDescuento, usuario);
                           			else
                            			mantenimientoINCConfiguracionConcursoDAO.updateConcursoArticuloLoteDescuento(concursoArticuloLoteDescuento, usuario);	
                            	}	
                    		}
                    	}
                	}

        		}
        		else {
            		concursoNivelPremiacion.setOidPremiacion(oidPremiacion);
            		mantenimientoINCConfiguracionConcursoDAO.updateConcursoNivelPremiacion(concursoNivelPremiacion, usuario);
            		
            		concursoPremioArticulo.setOidNivelPremiacion(oidNivelPremiacion);
            		mantenimientoINCConfiguracionConcursoDAO.updateConcursoPremioArticulo(concursoPremioArticulo, usuario);
        		}
        			
        	}
    	}
    }

    private void insertProductos(ConcursoProductos concursoProductos, Usuario usuario) {
    	if(concursoProductos.getListConcursoProductosValidos()!=null) {
    		if(concursoProductos.isIndRedefinirProductosValidos()) {
    			mantenimientoINCConfiguracionConcursoDAO.deleteConcursoProductosValidos(concursoProductos.getOid().toString());
    		}
    		
    		List listConcursoProductosValidos = concursoProductos.getListConcursoProductosValidos();
    		for(int i=0; i<listConcursoProductosValidos.size(); i++) {
    			ConcursoProductosValidos concursoProductosValidos = (ConcursoProductosValidos)listConcursoProductosValidos.get(i);
    			
    			if(concursoProductos.isIndRedefinirProductosValidos()) {
    				concursoProductosValidos.setOidProductos(concursoProductos.getOid());
    				mantenimientoINCConfiguracionConcursoDAO.insertConcursoProductosValidos(concursoProductosValidos, usuario);
    			} else {
    				mantenimientoINCConfiguracionConcursoDAO.updateConcursoProductosValidos(concursoProductosValidos, usuario);
    			}
    		}
		}
    	
    	if(concursoProductos.getListConcursoProductosBonificados()!=null) {
    		if(concursoProductos.isIndRedefinirProductosBonificados()) {
    			mantenimientoINCConfiguracionConcursoDAO.deleteConcursoProductosBonificados(concursoProductos.getOid().toString());
    		}
    		
    		List listConcursoProductosBonificados = concursoProductos.getListConcursoProductosBonificados();
    		for(int i=0; i<listConcursoProductosBonificados.size(); i++) {
    			ConcursoProductosBonificados concursoProductosBonificados = (ConcursoProductosBonificados)listConcursoProductosBonificados.get(i);
    			
    			if(concursoProductos.isIndRedefinirProductosBonificados()) {
    				concursoProductosBonificados.setOidProductos(concursoProductos.getOid());
    				mantenimientoINCConfiguracionConcursoDAO.insertConcursoProductosBonificados(concursoProductosBonificados, usuario);
    			} else {
    				mantenimientoINCConfiguracionConcursoDAO.updateConcursoProductosBonificados(concursoProductosBonificados, usuario);
    			}
    		}
		}
    	
    	if(concursoProductos.getListConcursoProductosExcluidos()!=null) {
    		if(concursoProductos.isIndRedefinirProductosExcluidos()) {
    			mantenimientoINCConfiguracionConcursoDAO.deleteConcursoProductosExcluidos(concursoProductos.getOid().toString());
    		}
    		
    		List listConcursoProductosExcluidos = concursoProductos.getListConcursoProductosExcluidos();
    		for(int i=0; i<listConcursoProductosExcluidos.size(); i++) {
    			ConcursoProductosExcluidos concursoProductosExcluidos = (ConcursoProductosExcluidos)listConcursoProductosExcluidos.get(i);
    			
    			if(concursoProductos.isIndRedefinirProductosExcluidos()) {
    				concursoProductosExcluidos.setOidProductos(concursoProductos.getOid());
    				mantenimientoINCConfiguracionConcursoDAO.insertConcursoProductosExcluidos(concursoProductosExcluidos, usuario);
    			} else {
    				mantenimientoINCConfiguracionConcursoDAO.updateConcursoProductosExcluidos(concursoProductosExcluidos, usuario);
    			}
    		}
		}
    	
    	if(concursoProductos.getListConcursoProductosExigidos()!=null) {
    		if(concursoProductos.isIndRedefinirProductosExigidos()) {
    			mantenimientoINCConfiguracionConcursoDAO.deleteConcursoProductosExigidos(concursoProductos.getOid().toString());
    		}
    		
    		List listConcursoProductosExigidos = concursoProductos.getListConcursoProductosExigidos();
    		for(int i=0; i<listConcursoProductosExigidos.size(); i++) {
    			ConcursoProductosExigidos concursoProductosExigidos = (ConcursoProductosExigidos)listConcursoProductosExigidos.get(i);
    			
    			if(concursoProductos.isIndRedefinirProductosExigidos()) {
    				concursoProductosExigidos.setOidProductos(concursoProductos.getOid());
    				mantenimientoINCConfiguracionConcursoDAO.insertConcursoProductosExigidos(concursoProductosExigidos, usuario);
    			} else {
    				mantenimientoINCConfiguracionConcursoDAO.updateConcursoProductosExigidos(concursoProductosExigidos, usuario);
    			}
    		}
		}
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getConfiguracionConcurso(java.lang.String)
     */
    public ConcursoParametrosGenerales getConfiguracionConcurso(String oidConcurso) {
    	//Obtenemos un registro de la entidad Concurso Parametros Generales
    	ConcursoParametrosGenerales concursoParametrosGenerales = 
    							mantenimientoINCConfiguracionConcursoDAO.getConcursoParametrosGenerales(oidConcurso);
    	
    	concursoParametrosGenerales.setDescripcionPrograma(mantenimientoINCConfiguracionConcursoDAO.getDescripcionProgramaEditar(oidConcurso));
    	
    	String numeroConcurso = concursoParametrosGenerales.getNumeroConcurso();
    	
    	//Obtenemos un registro de la entidad Concurso Version
    	ConcursoVersion concursoVersion = mantenimientoINCConfiguracionConcursoDAO.getConcursoVersion(oidConcurso);
    	concursoParametrosGenerales.setConcursoVersion(concursoVersion);
    	
    	//Obtenemos un registro de la entidad Concurso Obtencion Puntos
    	ConcursoObtencionPuntos concursoObtencionPuntos = 
    							mantenimientoINCConfiguracionConcursoDAO.getConcursoObtencionPuntos(oidConcurso);
    	concursoParametrosGenerales.setConcursoObtencionPuntos(concursoObtencionPuntos);
    	
    	//Obtenemos un registro de la entidad Concurso Despacho Premios
    	ConcursoDespachoPremios concursoDespachoPremios = 
    							mantenimientoINCConfiguracionConcursoDAO.getConcursoDespachoPremios(oidConcurso);
    	concursoParametrosGenerales.setConcursoDespachoPremios(concursoDespachoPremios);

    	//Obtenemos una lista de registros de la entidad Concurso Ambito Geografico
    	List listConcursoAmbitoGeografico = 
    							mantenimientoINCConfiguracionConcursoDAO.getListConcursoAmbitoGeografico(oidConcurso);
    	concursoParametrosGenerales.setListConcursoAmbitoGeografico(listConcursoAmbitoGeografico);

    	//Obtenemos un registro de la entidad Concurso Parametros Consultoras
    	ConcursoParametrosConsultoras concursoParametrosConsultoras = 
    							mantenimientoINCConfiguracionConcursoDAO.getConcursoParametrosConsultoras(oidConcurso);
    	concursoParametrosGenerales.setConcursoParametrosConsultoras(concursoParametrosConsultoras);

    	//Obtenemos una lista de registros de la entidad Concurso Monto Ventas
    	List listConcursoMontoVentas = 	mantenimientoINCConfiguracionConcursoDAO.getListConcursoMontoVentas(oidConcurso);
    	concursoParametrosGenerales.setListConcursoMontoVentas(listConcursoMontoVentas);

    	//Obtenemos una lista de registros de la entidad Concurso Estatus Venta
    	List listConcursoEstatusVenta = mantenimientoINCConfiguracionConcursoDAO.getListConcursoEstatusVenta(oidConcurso);
    	concursoParametrosGenerales.setListConcursoEstatusVenta(listConcursoEstatusVenta);

    	//Obtenemos una lista de registros de la entidad Concurso Clasificacion Participante
    	List listConcursoClasificacionParticipante = 
    					mantenimientoINCConfiguracionConcursoDAO.getListConcursoClasificacionParticipante(oidConcurso);
    	concursoParametrosGenerales.setListConcursoClasificacionParticipante(listConcursoClasificacionParticipante);

    	//Obtenemos una lista de registros de la entidad Concurso Recomendada Periodo
    	List listConcursoRecomendadaPeriodo = mantenimientoINCConfiguracionConcursoDAO.getListConcursoRecomendadaPeriodo(numeroConcurso);
    	concursoParametrosGenerales.setListConcursoRecomendadaPeriodo(listConcursoRecomendadaPeriodo);

    	//Obtenemos una lista de registros de la entidad Concurso Bonificacion Periodo
    	List listConcursoBonificacionPeriodo = mantenimientoINCConfiguracionConcursoDAO.getListConcursoBonificacionPeriodo(numeroConcurso);
    	concursoParametrosGenerales.setListConcursoBonificacionPeriodo(listConcursoBonificacionPeriodo);

    	//Obtenemos una lista de registros de la entidad Concurso Periodo Despacho
    	List listConcursoPeriodoDespacho = mantenimientoINCConfiguracionConcursoDAO.getListConcursoPeriodoDespacho(numeroConcurso);
    	concursoParametrosGenerales.setListConcursoPeriodoDespacho(listConcursoPeriodoDespacho);
    	
    	//Obtenemos una lista de registros de la entidad Puntaje Exigido
    	List listConcursoPuntajeExigido = mantenimientoINCConfiguracionConcursoDAO.getListConcursoPuntajeExigido(numeroConcurso);
    	concursoParametrosGenerales.setListConcursoPuntajeExigido(listConcursoPuntajeExigido);
    	

    	//Obtenemos un registro de la entidad Concurso Requisitos Premiacion
    	ConcursoRequisitoPremiacion concursoRequisitoPremiacion = 
    							mantenimientoINCConfiguracionConcursoDAO.getConcursoRequisitoPremiacion(oidConcurso);
    	concursoParametrosGenerales.setConcursoRequisitoPremiacion(concursoRequisitoPremiacion);

    	//Obtenemos un registro de la entidad Concurso Parametros Premiacion
    	ConcursoParametrosPremiacion concursoParametrosPremiacion = getConcursoParametrosPremiacion(oidConcurso);
    	concursoParametrosGenerales.setConcursoParametrosPremiacion(concursoParametrosPremiacion);

    	//Obtenemos un registro de la entidad Concurso Productos
    	ConcursoProductos concursoProductos = mantenimientoINCConfiguracionConcursoDAO.getConcursoProductos(oidConcurso);
    	if(concursoProductos != null) {
    		Map criteriaBusqueda = new HashMap();
			    		
    		List listConcursoProductosValidos =	mantenimientoINCConfiguracionConcursoDAO.getListConcursoProductosValidos(
    				concursoProductos.getOid().toString());
    		for(int i=0; i<listConcursoProductosValidos.size();i++) {
    			ConcursoProductosValidos cpv = (ConcursoProductosValidos)listConcursoProductosValidos.get(i);
    			if(cpv.getOidDetalleOferta()!=null) {
    				criteriaBusqueda.put("oidDetalleOferta", cpv.getOidDetalleOferta());
    				Map mapDetalleOferta = getDetalleOfertaCUV(criteriaBusqueda);
    				
    				cpv.setCodigoSAP((String)mapDetalleOferta.get("codigoSAP"));
    				cpv.setDescripcionTipoOferta((String)mapDetalleOferta.get("descripcionTipoOferta"));
    				cpv.setDescripcionProducto((String)mapDetalleOferta.get("descripcionProducto"));
    			}
    		}
    		concursoProductos.setListConcursoProductosValidos(listConcursoProductosValidos);
    		
    		List listConcursoProductosBonificados =	mantenimientoINCConfiguracionConcursoDAO.getListConcursoProductosBonificados(
    				concursoProductos.getOid().toString());
    		for(int i=0; i<listConcursoProductosBonificados.size();i++) {
    			ConcursoProductosBonificados cpb = (ConcursoProductosBonificados)listConcursoProductosBonificados.get(i);
    			if(cpb.getOidDetalleOferta()!=null) {
    				criteriaBusqueda.put("oidDetalleOferta", cpb.getOidDetalleOferta());
    				Map mapDetalleOferta = getDetalleOfertaCUV(criteriaBusqueda);
    				
    				cpb.setCodigoSAP((String)mapDetalleOferta.get("codigoSAP"));
    				cpb.setDescripcionTipoOferta((String)mapDetalleOferta.get("descripcionTipoOferta"));
    				cpb.setDescripcionProducto((String)mapDetalleOferta.get("descripcionProducto"));
    			}
    		}
    		concursoProductos.setListConcursoProductosBonificados(listConcursoProductosBonificados);
    		
    		List listConcursoProductosExcluidos = mantenimientoINCConfiguracionConcursoDAO.getListConcursoProductosExcluidos(
    				concursoProductos.getOid().toString());
    		for(int i=0; i<listConcursoProductosExcluidos.size();i++) {
    			ConcursoProductosExcluidos cpe = (ConcursoProductosExcluidos)listConcursoProductosExcluidos.get(i);
    			if(cpe.getOidDetalleOferta()!=null) {
    				criteriaBusqueda.put("oidDetalleOferta", cpe.getOidDetalleOferta());
    				Map mapDetalleOferta = getDetalleOfertaCUV(criteriaBusqueda);
    				
    				cpe.setCodigoSAP((String)mapDetalleOferta.get("codigoSAP"));
    				cpe.setDescripcionTipoOferta((String)mapDetalleOferta.get("descripcionTipoOferta"));
    				cpe.setDescripcionProducto((String)mapDetalleOferta.get("descripcionProducto"));
    			}
    		}
    		concursoProductos.setListConcursoProductosExcluidos(listConcursoProductosExcluidos);
    		
    		List listConcursoProductosExigidos = mantenimientoINCConfiguracionConcursoDAO.getListConcursoProductosExigidos(
    				concursoProductos.getOid().toString());
    		for(int i=0; i<listConcursoProductosExigidos.size();i++) {
    			ConcursoProductosExigidos cpe = (ConcursoProductosExigidos)listConcursoProductosExigidos.get(i);
    			if(cpe.getOidDetalleOferta()!=null) {
    				criteriaBusqueda.put("oidDetalleOferta", cpe.getOidDetalleOferta());
    				Map mapDetalleOferta = getDetalleOfertaCUV(criteriaBusqueda);
    				
    				cpe.setCodigoSAP((String)mapDetalleOferta.get("codigoSAP"));
    				cpe.setDescripcionTipoOferta((String)mapDetalleOferta.get("descripcionTipoOferta"));
    				cpe.setDescripcionProducto((String)mapDetalleOferta.get("descripcionProducto"));
    			}
    		}
    		concursoProductos.setListConcursoProductosExigidos(listConcursoProductosExigidos);
    	}
    	concursoParametrosGenerales.setConcursoProductos(concursoProductos);
    	
    	if(StringUtils.isNotBlank(concursoParametrosGenerales.getOidCPP()))
    	{
        	ConcursoParametrosGenerales cpg = mantenimientoINCConfiguracionConcursoDAO.getConcursoParametrosGenerales(concursoParametrosGenerales.getOidCPP());
    		
        	if(cpg != null)
        	{
        		concursoParametrosGenerales.setCodigoCPP(cpg.getNumeroConcurso());
        		concursoParametrosGenerales.setDescripcionCPP(cpg.getNombreConcurso());
        	}
    	}
    	
    	return concursoParametrosGenerales;
    }
    
    private ConcursoParametrosPremiacion getConcursoParametrosPremiacion(String oidConcurso) {
    	ConcursoParametrosPremiacion concursoParametrosPremiacion = 
    						mantenimientoINCConfiguracionConcursoDAO.getConcursoParametrosPremiacion(oidConcurso);
    	    	
    	if(concursoParametrosPremiacion != null) {
    		List listConcursoNivelPremiacion = 	mantenimientoINCConfiguracionConcursoDAO.getListConcursoNivelPremiacion(
    															concursoParametrosPremiacion.getOid().toString());
    		concursoParametrosPremiacion.setListConcursoNivelPremiacion(listConcursoNivelPremiacion);
    		
    		if(listConcursoNivelPremiacion != null) {
    			for(int i=0; i<listConcursoNivelPremiacion.size(); i++) {
    				ConcursoNivelPremiacion concursoNivelPremiacion = (ConcursoNivelPremiacion)listConcursoNivelPremiacion.get(i);
    				
    				ConcursoPremioArticulo concursoPremioArticulo = mantenimientoINCConfiguracionConcursoDAO.
    											getConcursoPremioArticulo(concursoNivelPremiacion.getOid().toString());
    				
    				concursoNivelPremiacion.setConcursoPremioArticulo(concursoPremioArticulo);
    				
    				List listConcursoLotePremioArticulo = 	mantenimientoINCConfiguracionConcursoDAO.
    									getListConcursoLotePremioArticulo(concursoPremioArticulo.getOid().toString());
    				
    				concursoNivelPremiacion.setListConcursoLotePremioArticulo(listConcursoLotePremioArticulo);
    				
    				if(listConcursoLotePremioArticulo != null) {
    	    			for(int j=0; j<listConcursoLotePremioArticulo.size(); j++) {
    	    				ConcursoLotePremioArticulo concursoLotePremioArticulo = (ConcursoLotePremioArticulo)
    	    																listConcursoLotePremioArticulo.get(j);
    	    				
    	    				List listConcursoArticuloLote = mantenimientoINCConfiguracionConcursoDAO.
    	    									getListConcursoArticuloLote(concursoLotePremioArticulo.getOid().toString());
    	    				
    	    				for(int k=0; k<listConcursoArticuloLote.size(); k++) {
    	    					ConcursoArticuloLote concursoArticuloLote = (ConcursoArticuloLote)listConcursoArticuloLote.get(k);
    	    					concursoArticuloLote.setNumeroNivel(concursoNivelPremiacion.getNumeroNivel().toString());
    	    					concursoArticuloLote.setDescripcionLote(concursoLotePremioArticulo.getDescripcionLote());
    	    					concursoArticuloLote.setNumeroPremio(concursoLotePremioArticulo.getNumeroPremio().toString());
    	    					concursoArticuloLote.setIndicadorPremiosWeb(concursoLotePremioArticulo.getIndicadorPremiosWeb());
    	    				}
    	    				
    	    				concursoLotePremioArticulo.setListConcursoArticuloLote(listConcursoArticuloLote);
    	    				
    	    				 // Definir Premio Descuento
    	    				List listConcursoArticuloLoteDescuento =  mantenimientoINCConfiguracionConcursoDAO.
									getListConcursoArticuloLoteDescuento(concursoLotePremioArticulo.getOid().toString());
    	    				
    	    				for(int k=0; k<listConcursoArticuloLoteDescuento.size(); k++) {
    	    					ConcursoArticuloLoteDescuento concursoArticuloLoteDescuento = (ConcursoArticuloLoteDescuento)listConcursoArticuloLoteDescuento.get(k);
    	    					concursoArticuloLoteDescuento.setNumeroNivel(concursoNivelPremiacion.getNumeroNivel().toString());
    	    					concursoArticuloLoteDescuento.setDescripcionLote(concursoLotePremioArticulo.getDescripcionLote());
    	    					concursoArticuloLoteDescuento.setNumeroPremio(concursoLotePremioArticulo.getNumeroPremio().toString());
    	    				}
    	    				
    	    				concursoLotePremioArticulo.setListConcursoArticuloLoteDescuento(listConcursoArticuloLoteDescuento);
    	    			}		
    				}
    			}
    		}
    	}
    	
    	return concursoParametrosPremiacion;
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#updateConfiguracionConcurso(biz.belcorp.ssicc.spusicc.inc.dao.model.ConcursoParametrosGenerales, biz.belcorp.ssicc.model.Usuario)
     */
    public void updateConfiguracionConcurso(ConcursoParametrosGenerales concursoParametrosGenerales, Usuario usuario) throws Exception {
    	Long oidConcurso = concursoParametrosGenerales.getOid();
    	String numeroConcurso = concursoParametrosGenerales.getNumeroConcurso();
    	
    	//Actualizamos registro en la entidad Concurso Parametros Generales
    	mantenimientoINCConfiguracionConcursoDAO.updateConcursoParametrosGenerales(concursoParametrosGenerales, usuario);
    	
    	//Actualizamos registro en la entidad Concurso Version
    	ConcursoVersion concursoVersion = concursoParametrosGenerales.getConcursoVersion();
    	mantenimientoINCConfiguracionConcursoDAO.updateConcursoVersion(concursoVersion, usuario);
    	
    	//Actualizamos registro en la entidad Concurso Obtencion Puntos
    	ConcursoObtencionPuntos concursoObtencionPuntos = concursoParametrosGenerales.getConcursoObtencionPuntos();
    	if(concursoObtencionPuntos != null) {
    		concursoObtencionPuntos.setIndicadorPuntajeAcumulativo(new Integer(1));
	    	if(concursoObtencionPuntos.getOid() != null)
	    		mantenimientoINCConfiguracionConcursoDAO.updateConcursoObtencionPuntos(concursoObtencionPuntos, usuario);
	    	else {
	    		concursoObtencionPuntos.setOidConcurso(oidConcurso);
	    		mantenimientoINCConfiguracionConcursoDAO.insertConcursoObtencionPuntos(concursoObtencionPuntos, usuario);
	    	}	
    	}
    	
    	//Actualizamos registro en la entidad Concurso Despacho Premios
    	ConcursoDespachoPremios concursoDespachoPremios = concursoParametrosGenerales.getConcursoDespachoPremios();
    	if(concursoDespachoPremios != null) {
	    	if(concursoDespachoPremios.getOid() != null)
	    		mantenimientoINCConfiguracionConcursoDAO.updateConcursoDespachoPremios(concursoDespachoPremios, usuario);
	    	else {
	    		concursoDespachoPremios.setOidConcurso(oidConcurso);
	    		mantenimientoINCConfiguracionConcursoDAO.insertConcursoDespachoPremios(concursoDespachoPremios, usuario);
	    	}	
    	}
    	
    	//Actualizamos la lista de registros en la entidad Concurso Ambito Geografico
    	if(concursoParametrosGenerales.getListConcursoAmbitoGeografico() != null) {
	    	List listConcursoAmbitoGeografico = concursoParametrosGenerales.getListConcursoAmbitoGeografico();
	    	if(concursoParametrosGenerales.isIndActualizarAmbitoGeografico()) {
	    		mantenimientoINCConfiguracionConcursoDAO.deleteConcursoAmbitoGeografico(oidConcurso.toString());
	    		
	        	for(int i=0; i<listConcursoAmbitoGeografico.size(); i++) {
	        		ConcursoAmbitoGeografico concursoAmbitoGeografico = (ConcursoAmbitoGeografico)listConcursoAmbitoGeografico.get(i);
	        		
	        		concursoAmbitoGeografico.setOidConcurso(oidConcurso);
	            	mantenimientoINCConfiguracionConcursoDAO.insertConcursoAmbitoGeografico(concursoAmbitoGeografico, usuario);
	        	}
	    	}
    	}
    	
    	//Actualizamos registro en la entidad Concurso Parametros Consultoras
    	ConcursoParametrosConsultoras concursoParametrosConsultoras = concursoParametrosGenerales.getConcursoParametrosConsultoras();
    	if(concursoParametrosConsultoras != null) {
    		//Se crea el concurso de Constancia
			if(concursoParametrosConsultoras.getOidConcursoPuntajeRecomendada()==null && 
					concursoParametrosConsultoras.getConcursoRecomendada()!=null) {
				
				ConcursoParametrosGenerales concursoRecomendada = concursoParametrosConsultoras.getConcursoRecomendada();
				
				insertConfiguracionConcurso(concursoRecomendada, usuario);
				concursoParametrosConsultoras.setOidConcursoPuntajeRecomendada(concursoRecomendada.getOid());
			}

	    	if(concursoParametrosConsultoras.getOid() != null)
	    		mantenimientoINCConfiguracionConcursoDAO.updateConcursoParametrosConsultoras(concursoParametrosConsultoras, usuario);
	    	else {
	    		concursoParametrosConsultoras.setOidConcurso(oidConcurso);
	    		mantenimientoINCConfiguracionConcursoDAO.insertConcursoParametrosConsultoras(concursoParametrosConsultoras, usuario);
	    	}	
    	}
    	
    	//Actualizamos la lista de registros en la entidad Concurso Monto Ventas
    	if(concursoParametrosGenerales.getListConcursoMontoVentas() != null) {
	    	List listConcursoMontoVentas = concursoParametrosGenerales.getListConcursoMontoVentas();
    		
        	for(int i=0; i<listConcursoMontoVentas.size(); i++) {
        		ConcursoMontoVentas concursoMontoVentas = (ConcursoMontoVentas)listConcursoMontoVentas.get(i);
        		
        		if(concursoMontoVentas.getOid() != null)
        			mantenimientoINCConfiguracionConcursoDAO.updateConcursoMontoVentas(concursoMontoVentas, usuario);
        		else {
        			concursoMontoVentas.setOidConcurso(oidConcurso);
        			mantenimientoINCConfiguracionConcursoDAO.insertConcursoMontoVentas(concursoMontoVentas, usuario);
        		}	
        	}
    	}

    	//Actualizamos la lista de registros en la entidad Concurso Estatus Venta
    	if(concursoParametrosGenerales.getListConcursoEstatusVenta() != null) {
	    	List listConcursoEstatusVenta = concursoParametrosGenerales.getListConcursoEstatusVenta();
	    	
	    	if(concursoParametrosGenerales.isIndActualizarEstatusVenta()) {
	    		mantenimientoINCConfiguracionConcursoDAO.deleteConcursoEstatusVenta(oidConcurso.toString());
	    		
		    	for(int i=0; i<listConcursoEstatusVenta.size(); i++) {
		    		ConcursoEstatusVenta concursoEstatusVenta = (ConcursoEstatusVenta)listConcursoEstatusVenta.get(i);
		    		
		    		concursoEstatusVenta.setOidConcurso(oidConcurso);
	    	        mantenimientoINCConfiguracionConcursoDAO.insertConcursoEstatusVenta(concursoEstatusVenta, usuario);
		    	}
	    	}	
    	}
    	
    	//Actualizamos la lista de registros en la entidad Concurso Clasificacion Participantes
    	if(concursoParametrosGenerales.getListConcursoClasificacionParticipante() != null) {
	    	List listConcursoClasificacionParticipante = concursoParametrosGenerales.getListConcursoClasificacionParticipante();
	    	
	    	if(concursoParametrosGenerales.isIndActualizarClasificacionParticipantes()) {
	    		mantenimientoINCConfiguracionConcursoDAO.deleteConcursoClasificacionParticipante(oidConcurso.toString());
	    		
		    	for(int i=0; i<listConcursoClasificacionParticipante.size(); i++) {
		    		ConcursoClasificacionParticipante concursoClasificacionParticipante = 
		    						(ConcursoClasificacionParticipante)listConcursoClasificacionParticipante.get(i);

		    		concursoClasificacionParticipante.setOidConcurso(oidConcurso);
	    	        mantenimientoINCConfiguracionConcursoDAO.insertConcursoClasificacionParticipante(concursoClasificacionParticipante, usuario);
		    	}
	    	}	
    	}
    	
    	//Actualizamos la lista de registros en la entidad Concurso Recomendada Periodo
    	if(concursoParametrosGenerales.getListConcursoRecomendadaPeriodo() != null) {
	    	List listConcursoRecomendadaPeriodo = concursoParametrosGenerales.getListConcursoRecomendadaPeriodo();
	    	
	    	if(concursoParametrosGenerales.isIndActualizarRecomendadaPeriodo()) {
	    		mantenimientoINCConfiguracionConcursoDAO.deleteConcursoRecomendadaPeriodo(numeroConcurso);
	    		
		    	for(int i=0; i<listConcursoRecomendadaPeriodo.size(); i++) {
		    		ConcursoRecomendadaPeriodo concursoRecomendadaPeriodo = (ConcursoRecomendadaPeriodo)listConcursoRecomendadaPeriodo.get(i);
		    		
		    		concursoRecomendadaPeriodo.setNumeroConcurso(numeroConcurso);
	    	        mantenimientoINCConfiguracionConcursoDAO.insertConcursoRecomendadaPeriodo(concursoRecomendadaPeriodo, usuario);
		    	}
	    	}	
    	}

    	//Actualizamos la lista de registros en la entidad Concurso Bonificacion Periodo
    	if(concursoParametrosGenerales.getListConcursoBonificacionPeriodo() != null) {
	    	List listConcursoBonificacionPeriodo = concursoParametrosGenerales.getListConcursoBonificacionPeriodo();
	    	
	    	if(concursoParametrosGenerales.isIndActualizarBonificacionPeriodo()) {
	    		mantenimientoINCConfiguracionConcursoDAO.deleteConcursoBonificacionPeriodo(numeroConcurso);
	    		
		    	for(int i=0; i<listConcursoBonificacionPeriodo.size(); i++) {
		    		ConcursoBonificacionPeriodo concursoBonificacionPeriodo = (ConcursoBonificacionPeriodo)listConcursoBonificacionPeriodo.get(i);
		    		
		    		concursoBonificacionPeriodo.setNumeroConcurso(numeroConcurso);
	    	        mantenimientoINCConfiguracionConcursoDAO.insertConcursoBonificacionPeriodo(concursoBonificacionPeriodo, usuario);
		    	}
	    	}	
    	}

    	Long oidTipoPremiacion = concursoParametrosGenerales.getConcursoParametrosPremiacion().getOidTipoPremiacion();
    	//Actualizamos la lista de registros en la entidad Concurso Periodo Despacho
    	if(concursoParametrosGenerales.getListConcursoPeriodoDespacho() != null) {
	    	List listConcursoPeriodoDespacho = concursoParametrosGenerales.getListConcursoPeriodoDespacho();
	    	
	    	if(!oidTipoPremiacion.toString().equals(Constants.INC_TIPO_PREMIACION_BOLSA_PREMIOS)){
	    		mantenimientoINCConfiguracionConcursoDAO.deleteConcursoPeriodoDespacho(numeroConcurso);
	    	}
	    	
	    	if(concursoParametrosGenerales.isIndActualizarPeriodoDespacho()) {
	    		mantenimientoINCConfiguracionConcursoDAO.deleteConcursoPeriodoDespacho(numeroConcurso);
	    		
		    	for(int i=0; i<listConcursoPeriodoDespacho.size(); i++) {
		    		ConcursoPeriodoDespacho concursoPeriodoDespacho = (ConcursoPeriodoDespacho)listConcursoPeriodoDespacho.get(i);
		    		
		    		concursoPeriodoDespacho.setNumeroConcurso(numeroConcurso);
	    	        mantenimientoINCConfiguracionConcursoDAO.insertConcursoPeriodoDespacho(concursoPeriodoDespacho, usuario);
		    	}
	    	}	
    	}

    	//Actualizamos registro en la entidad Concurso Requisitos Premiacion
    	ConcursoRequisitoPremiacion concursoRequisitoPremiacion = concursoParametrosGenerales.getConcursoRequisitoPremiacion();
    	if(concursoRequisitoPremiacion != null) {
    		if(concursoRequisitoPremiacion.getOid() != null)
	    		mantenimientoINCConfiguracionConcursoDAO.updateConcursoRequisitoPremiacion(concursoRequisitoPremiacion, usuario);
	    	else {
	    		concursoRequisitoPremiacion.setOidConcurso(oidConcurso);
	    		mantenimientoINCConfiguracionConcursoDAO.insertConcursoRequisitoPremiacion(concursoRequisitoPremiacion, usuario);
	    	}
    	}
    	
    	//Actualizamos registros en las entidades de Premiacion: parametros, niveles, premio, lotes, articulos
    	ConcursoParametrosPremiacion concursoParametrosPremiacion = concursoParametrosGenerales.getConcursoParametrosPremiacion();
    	if(concursoParametrosPremiacion != null) {
    		if(concursoParametrosPremiacion.getOid() != null)
	    		mantenimientoINCConfiguracionConcursoDAO.updateConcursoParametrosPremiacion(concursoParametrosPremiacion, usuario);
	    	else {
	    		Long oidPremiacion = mantenimientoINCConfiguracionConcursoDAO.getSecuenciaConcursoParametrosPremiacion();
	    		concursoRequisitoPremiacion.setOid(oidPremiacion);
	    		concursoRequisitoPremiacion.setOidConcurso(oidConcurso);
	    		
	    		mantenimientoINCConfiguracionConcursoDAO.insertConcursoParametrosPremiacion(concursoParametrosPremiacion, usuario);
	    	}
    		
    		//Insertamos registros en las entidades de niveles de premio, lotes, articulos
    		insertNivelesPremiacion(concursoParametrosPremiacion, usuario);
    	}

    	//Actualizamos registros en las entidades de Productos
    	ConcursoProductos concursoProductos = concursoParametrosGenerales.getConcursoProductos();
    	if(concursoProductos != null) {
    		if(concursoProductos.getOid() != null)
	    		mantenimientoINCConfiguracionConcursoDAO.updateConcursoProductos(concursoProductos, usuario);
	    	else {
	    		Long oidProductos = mantenimientoINCConfiguracionConcursoDAO.getSecuenciaConcursoProductos();
	    		
	    		concursoProductos.setOid(oidProductos);
	    		concursoProductos.setOidConcurso(oidConcurso);
	    		mantenimientoINCConfiguracionConcursoDAO.insertConcursoProductos(concursoProductos, usuario);
	    	}
    		
    		//Insertamos registros en las entidades de Productos Validos, Productos Bonificados,
        	//Productos Excluidos y Productos Exigidos
    		insertProductos(concursoProductos, usuario);
    	}
    	
    	//Insertamos la lista de registros en la entidad Parmetros Nivel Premio Puntaje Exigido
    	ConcursoPuntajeExigido concursoPuntajeExigidoDelete = new ConcursoPuntajeExigido();
    	concursoPuntajeExigidoDelete.setCodigoPais(concursoParametrosGenerales.getCodigoPais());
    	concursoPuntajeExigidoDelete.setNumeroConcurso(concursoParametrosGenerales.getNumeroConcurso());
    	mantenimientoINCConfiguracionConcursoDAO.deleteConcursoPuntajeExigido(concursoPuntajeExigidoDelete, usuario);
    	if(concursoParametrosGenerales.getListConcursoPuntajeExigido() != null) {
	    	List listConcursoPuntajeExigido = concursoParametrosGenerales.getListConcursoPuntajeExigido();
	    	for(int i=0; i<listConcursoPuntajeExigido.size(); i++) {
	    		ConcursoPuntajeExigido concursoPuntajeExigido = (ConcursoPuntajeExigido)listConcursoPuntajeExigido.get(i);
	    		concursoPuntajeExigido.setNumeroConcurso(concursoParametrosGenerales.getNumeroConcurso());
	        	mantenimientoINCConfiguracionConcursoDAO.insertConcursoPuntajeExigido(concursoPuntajeExigido, usuario);
	    	}
    	}
    	
    	Map criteria = new HashMap();
    	criteria.put("valorServidor", "--");
    	mantenimientoINCConfiguracionConcursoDAO.deleteUltimaActualizacionConcurso(criteria);
    	mantenimientoINCConfiguracionConcursoDAO.insertUltimaActualizacionConcurso(criteria);
    }    

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#executeReplicarConfiguracionConcurso(java.util.Map)
     */
    public void executeReplicarConfiguracionConcurso(Map params) {
    	mantenimientoINCConfiguracionConcursoDAO.executeReplicarConfiguracionConcurso(params);    	
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#validarEliminacionConcurso(java.lang.String)
     */
    public boolean validarEliminacionConcurso(String oidConcurso) {
    	boolean validacion = false;
    	
    	validacion = mantenimientoINCConfiguracionConcursoDAO.validaDespachoPremios(oidConcurso);
    	if(validacion) {
    		validacion = mantenimientoINCConfiguracionConcursoDAO.validaPuntajeConcurso(oidConcurso);
    	}
    	
    	return validacion;
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getCodigoSAPRecomendacion()
     */
    public String getCodigoSAPRecomendacion() {
    	return mantenimientoINCConfiguracionConcursoDAO.getCodigoSAPRecomendacion();   
    }

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getEstados()
     */
    public List getEstados() {
    	return mantenimientoINCConfiguracionConcursoDAO.getEstados();
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getVigencias()
	 */
	public List getVigencias() {
		return mantenimientoINCConfiguracionConcursoDAO.getVigencias();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getIndicadoresTipoRecomendada()
	 */
	public List getIndicadoresTipoRecomendada() {
		return mantenimientoINCConfiguracionConcursoDAO.getIndicadoresTipoRecomendada();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getDetalleOfertaCUV(java.util.Map)
	 */
	public Map getDetalleOfertaCUV(Map criteria) {
		return mantenimientoINCConfiguracionConcursoDAO.getDetalleOfertaCUV(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getFaltantesPendientesConcurso(java.util.Map)
	 */
	public List getFaltantesPendientesConcurso(Map criteria) {
		return mantenimientoINCConfiguracionConcursoDAO.getFaltantesPendientesConcurso(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getPremiosConcurso(java.util.Map)
	 */
	public List getPremiosConcurso(Map criteria) {
		return mantenimientoINCConfiguracionConcursoDAO.getPremiosConcurso(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getFaltantesConcurso(java.util.Map)
	 */
	public List getFaltantesConcurso(Map criteria) {
		return mantenimientoINCConfiguracionConcursoDAO.getFaltantesConcurso(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#insertReemplazoPremio(java.util.Map)
	 */
	public void insertReemplazoPremio(Map params) {
		String codigoVenta = mantenimientoINCConfiguracionConcursoDAO.getUltimoContadorArticulo();
		params.put("codigoVenta", codigoVenta);

		Long oid = mantenimientoINCConfiguracionConcursoDAO.getSecuenciaReemplazoPremio();
		params.put("oid", oid);
		
		mantenimientoINCConfiguracionConcursoDAO.insertReemplazoPremio(params);

		List listAmbitos = (List)params.get("listAmbitos");
		if(listAmbitos != null) {
			for(int i=0; i<listAmbitos.size(); i++) {
				Map mapAmbito = (Map)listAmbitos.get(i);
				mapAmbito.put("oidReemplazo", oid);
				mantenimientoINCConfiguracionConcursoDAO.insertReemplazoPremioAmbito(mapAmbito);
			}
		}	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#updateReemplazoPremio(java.util.Map)
	 */
	public void updateReemplazoPremio(Map params) {
		mantenimientoINCConfiguracionConcursoDAO.updateReemplazoPremio(params);
		String indActAmbitos = (String)params.get("indActAmbitos");
		
		if(Constants.NUMERO_UNO.equals(indActAmbitos)) {
			String oid = (String)params.get("oid");
			
			mantenimientoINCConfiguracionConcursoDAO.deleteReemplazoPremioAmbito(oid);
			
			List listAmbitos = (List)params.get("listAmbitos");
			for(int i=0; i<listAmbitos.size(); i++) {
				Map mapAmbito = (Map)listAmbitos.get(i);
				mapAmbito.put("oidReemplazo", params.get("oid"));
				mantenimientoINCConfiguracionConcursoDAO.insertReemplazoPremioAmbito(mapAmbito);
			}
		}
		
		mantenimientoINCConfiguracionConcursoDAO.updateReemplazoPremioCompuesto(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#deleteReemplazoPremio(java.lang.String)
	 */
	public void deleteReemplazoPremio(String oidReemplazo) {
		mantenimientoINCConfiguracionConcursoDAO.deleteReemplazoPremioAmbito(oidReemplazo);
		mantenimientoINCConfiguracionConcursoDAO.deleteReemplazoPremioCompuesto(oidReemplazo);
		mantenimientoINCConfiguracionConcursoDAO.deleteReemplazoPremio(oidReemplazo);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getListCriterioDeReemplazo(java.util.Map)
	 */
	public List getListCriterioDeReemplazo(Map criteria) {
		return mantenimientoINCConfiguracionConcursoDAO.getListCriterioDeReemplazo(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#validaEjecucionProcesoBatch(java.util.Map)
	 */
	public boolean validaEjecucionProcesoBatch(Map criteria) {
		return mantenimientoINCConfiguracionConcursoDAO.validaEjecucionProcesoBatch(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getListReemplazoPremioAmbito(java.lang.String)
	 */
	public List getListReemplazoPremioAmbito(String oidReemplazo) {
		return mantenimientoINCConfiguracionConcursoDAO.getListReemplazoPremioAmbito(oidReemplazo);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#validaPremioAtendido(java.util.Map)
	 */
	public boolean validaPremioAtendido(Map criteria) {
		return mantenimientoINCConfiguracionConcursoDAO.validaPremioAtendido(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getListConcursosVigentesPuntos()
	 */
	public List getListConcursosVigentesPuntos() {
		return mantenimientoINCConfiguracionConcursoDAO.getListConcursosVigentesPuntos();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getProgramasConstanciaProgramaPuntosByCriteria(java.util.Map)
	 */
	public List getProgramasConstanciaProgramaPuntosByCriteria(Map params) {
		return mantenimientoINCConfiguracionConcursoDAO.getProgramasConstanciaProgramaPuntosByCriteria(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConstanciaProgramaPuntos)
	 */
	public ConstanciaProgramaPuntos getConstanciaProgramaPuntos(ConstanciaProgramaPuntos cpp) {
		return mantenimientoINCConfiguracionConcursoDAO.getConstanciaProgramaPuntos(cpp);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#insertConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConstanciaProgramaPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertConstanciaProgramaPuntos(ConstanciaProgramaPuntos cpp, Usuario usuario) {
		// Verificamos que no exista un estatus con el mismo codigo
    	Map criteria = new HashMap();
    	criteria.put("codigoPais", cpp.getCodigoPais());
    	criteria.put("codigo", cpp.getCodigo());
    	
    	List constanciaList = mantenimientoINCConfiguracionConcursoDAO.getProgramasConstanciaProgramaPuntosByCriteria(criteria);
        
    	if(constanciaList != null && constanciaList.size() > 0)
    		throw new InvalidIdentifierException(EstatusRechazoFLX.class, cpp.getCodigo());
        
        mantenimientoINCConfiguracionConcursoDAO.insertConstanciaProgramaPuntos(cpp, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#updateConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.ConstanciaProgramaPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void updateConstanciaProgramaPuntos(ConstanciaProgramaPuntos cpp, Usuario usuario) {
        mantenimientoINCConfiguracionConcursoDAO.updateConstanciaProgramaPuntos(cpp, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#insertRangoConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.RangoConstanciaProgramaPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertRangoConstanciaProgramaPuntos(RangoConstanciaProgramaPuntos rango, Usuario usuario) throws Exception {
		
		Map params = BeanUtils.describe(rango);		
		List rangos = mantenimientoINCConfiguracionConcursoDAO.getRangoConstanciaProgramaPuntosList(params);
		
		if(rangos !=  null && rangos.size() > 0)
			throw new InvalidIdentifierException(ConstanciaProgramaPuntos.class, rango.getRangoFinal());
		
		mantenimientoINCConfiguracionConcursoDAO.insertRangoConstanciaProgramaPuntos(rango, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getRangoConstanciaProgramaPuntosList(java.util.Map)
	 */
	public List getRangoConstanciaProgramaPuntosList(Map params) {
		return mantenimientoINCConfiguracionConcursoDAO.getRangoConstanciaProgramaPuntosList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#deleteRangoConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.RangoConstanciaProgramaPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteRangoConstanciaProgramaPuntos(RangoConstanciaProgramaPuntos rango, Usuario usuario) {
		mantenimientoINCConfiguracionConcursoDAO.deleteRangoConstanciaProgramaPuntos(rango, usuario);
	}

	public Integer getExistePremiosDescuento(Map criteria) {
		// TODO Auto-generated method stub
		return mantenimientoINCConfiguracionConcursoDAO.getExistePremiosDescuento(criteria);
	}

	@Override
	public String getcodigoProductoDescuento() {
		return mantenimientoINCConfiguracionConcursoDAO.getcodigoProductoDescuento();
	}

	@Override
	public Integer getExistePremiosArticulo(Map criteria) {
		return mantenimientoINCConfiguracionConcursoDAO.getExistePremiosArticulo(criteria);
	}

	@Override
	public Integer getNivelSelectivo(Map criteria) {
		return mantenimientoINCConfiguracionConcursoDAO.getNivelSelectivo(criteria);
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#getClasificacionParaINCByCriteria(java.util.Map)
	 */
	public List getClasificacionParaINCByCriteria(Map params) {
		return mantenimientoINCConfiguracionConcursoDAO.getClasificacionParaINCByCriteria(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#getClasificacionParaINC(java.util.Map)
	 */
	public ClasificacionParaINC getClasificacionParaINC(Map params) {
		return mantenimientoINCConfiguracionConcursoDAO.getClasificacionParaINC(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#insertClasificacionParaINC(biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINC, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void insertClasificacionParaINC(ClasificacionParaINC bean, Usuario usuario) {
		mantenimientoINCConfiguracionConcursoDAO.insertClasificacionParaINC(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#updateClasificacionParaINC(biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINC, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void updateClasificacionParaINC(ClasificacionParaINC bean, Usuario usuario) {
		mantenimientoINCConfiguracionConcursoDAO.updateClasificacionParaINC(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#deleteClasificacionParaINC(biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINC, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void deleteClasificacionParaINC(ClasificacionParaINC bean, Usuario usuario) {
		mantenimientoINCConfiguracionConcursoDAO.deleteClasificacionParaINC(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#insertClasificacionParaINCDetalle(biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINCDetalle, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void insertClasificacionParaINCDetalle(ClasificacionParaINCDetalle bean, Usuario usuario) {
		mantenimientoINCConfiguracionConcursoDAO.insertClasificacionParaINCDetalle(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#updateClasificacionParaINCDetalle(biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINCDetalle, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void updateClasificacionParaINCDetalle(ClasificacionParaINCDetalle bean, Usuario usuario) {
		mantenimientoINCConfiguracionConcursoDAO.updateClasificacionParaINCDetalle(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#deleteClasificacionParaINCDetalle(biz.belcorp.ssicc.dao.spusicc.inc.model.ClasificacionParaINCDetalle, biz.belcorp.ssicc.dao.model.Usuario)
	 */
	public void deleteClasificacionParaINCDetalle(ClasificacionParaINCDetalle bean, Usuario usuario) {
		mantenimientoINCConfiguracionConcursoDAO.deleteClasificacionParaINCDetalle(bean, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#getClasificacionParaINCDetalleByCriteria(java.util.Map)
	 */
	public List getClasificacionParaINCDetalleByCriteria(Map params) {
		return mantenimientoINCConfiguracionConcursoDAO.getClasificacionParaINCDetalleByCriteria(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#getClasificacionParaINCDetalle(java.util.Map)
	 */
	public ClasificacionParaINCDetalle getClasificacionParaINCDetalle(Map params) {
		return mantenimientoINCConfiguracionConcursoDAO.getClasificacionParaINCDetalle(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#getTipoClienteList(java.util.Map)
	 */
	public List getTipoClienteList(Map params) {
		return mantenimientoINCConfiguracionConcursoDAO.getTipoClienteList(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#getSubTipoClienteList(java.util.Map)
	 */
	public List getSubTipoClienteList(Map params) {
		return mantenimientoINCConfiguracionConcursoDAO.getSubTipoClienteList(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#getTipoClasificacionList(java.util.Map)
	 */
	public List getTipoClasificacionList(Map params) {
		return mantenimientoINCConfiguracionConcursoDAO.getTipoClasificacionList(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#getClasificacionList(java.util.Map)
	 */
	public List getClasificacionList(Map params) {
		return mantenimientoINCConfiguracionConcursoDAO.getClasificacionList(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#getExisteRegistroClasificacionPartiConcu(java.util.Map)
	 */
	public Integer getExisteRegistroClasificacionPartiConcu(Map params) {
		return mantenimientoINCConfiguracionConcursoDAO.getExisteRegistroClasificacionPartiConcu(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCConfiguracionConcursoService#getExisteRegistroClasificacionDetalle(java.util.Map)
	 */
	public Integer getExisteRegistroClasificacionDetalle(Map params) {
		return mantenimientoINCConfiguracionConcursoDAO.getExisteRegistroClasificacionDetalle(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#insertNuevaConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.NuevaConstanciaProgramaPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void insertNuevaConstanciaProgramaPuntos(NuevaConstanciaProgramaPuntos nueva, Usuario usuario) throws Exception {
		
		Map params = BeanUtils.describe(nueva);		
		List rangos = mantenimientoINCConfiguracionConcursoDAO.getNuevaConstanciaProgramaPuntosList(params);
		
		if(rangos !=  null && rangos.size() > 0)
			throw new InvalidIdentifierException(ConstanciaProgramaPuntos.class, nueva.getPeriodosExigidos());
		
		mantenimientoINCConfiguracionConcursoDAO.insertNuevaConstanciaProgramaPuntos(nueva, usuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getNuevaConstanciaProgramaPuntosList(java.util.Map)
	 */
	public List getNuevaConstanciaProgramaPuntosList(Map params) {
		return mantenimientoINCConfiguracionConcursoDAO.getNuevaConstanciaProgramaPuntosList(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#deleteNuevaConstanciaProgramaPuntos(biz.belcorp.ssicc.spusicc.inc.dao.model.NuevaConstanciaProgramaPuntos, biz.belcorp.ssicc.model.Usuario)
	 */
	public void deleteNuevaConstanciaProgramaPuntos(NuevaConstanciaProgramaPuntos nueva, Usuario usuario) {
		mantenimientoINCConfiguracionConcursoDAO.deleteNuevaConstanciaProgramaPuntos(nueva, usuario);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.service.MantenimientoINCConfiguracionConcursoService#getListConcursosTipoProgramaPuntos()
	 */
	public List getListConcursosTipoProgramaPuntos() {
		return mantenimientoINCConfiguracionConcursoDAO.getListConcursosTipoProgramaPuntos();
	}
}
