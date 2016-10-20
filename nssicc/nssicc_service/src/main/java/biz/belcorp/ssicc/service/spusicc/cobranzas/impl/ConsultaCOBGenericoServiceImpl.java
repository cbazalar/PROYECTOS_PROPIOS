package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.ConsultaCOBGenericoDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.FtpCobrador;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBGenericoService;


/**
 * Service que controla la Consulta de Telecobro
 *  
 * <p>
 * <a href="ConsultaCOBGenericoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 */
/**
 * @author pejflorencio
 *
 */
/**
 * @author pejflorencio
 *
 */
@Service("spusicc.consultaCOBGenericoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaCOBGenericoServiceImpl extends BaseService implements ConsultaCOBGenericoService {

	@Resource(name="spusicc.consultaCOBGenericoDAO")
	private ConsultaCOBGenericoDAO consultaCOBGenericoDAO;


	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBGenericoService#getParametroPais(java.lang.String)
     */
    public String getParametroPais(Map criteria) {
		
		return consultaCOBGenericoDAO.getParametroPais(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBGenericoService#getEtapasDeuda()
	 */
	public List getEtapasDeuda() {
		return consultaCOBGenericoDAO.getEtapasDeuda();
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBGenericoService#getHistorialGestionesCobranza(java.util.Map)
	 */
	public List getHistorialGestionesCobranza(Map criteria){
		return consultaCOBGenericoDAO.getHistorialGestionesCobranza(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBGenericoService#getPorcentajeMetaEtapaVentas(java.util.Map)
	 */
	public Integer getPorcentajeMetaEtapaVentas(Map criteria){
		return consultaCOBGenericoDAO.getPorcentajeMetaEtapaVentas(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBGenericoService#getCarterasConsultoraList(java.util.Map)
	 */
	public List getCarterasConsultoraList(Map criteria){
		return consultaCOBGenericoDAO.getCarterasConsultoraList(criteria);
	};
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBGenericoService#getGestionesCobranzaConsultora(java.util.Map)
	 */
	public List getGestionesCobranzaConsultora(Map criteria){
		return consultaCOBGenericoDAO.getGestionesCobranzaConsultora(criteria);
	};
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBGenericoService#getCobradoresActivos()
	 */
	public List getCobradoresActivos(){
		return consultaCOBGenericoDAO.getCobradoresActivos();
	};	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBGenericoService#getSupervisores()
	 */
	public List getSupervisores(){
		return consultaCOBGenericoDAO.getSupervisores();
	};	
		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBGenericoService#getSCCarteraList(java.util.Map)
	 */
	public List getSCCarteraList(Map criteria){
		return consultaCOBGenericoDAO.getSCCarteraList(criteria);
	};	
	
	public FtpCobrador getFTPCobrador(String codigoCobrador){
		return consultaCOBGenericoDAO.getFTPCobrador(codigoCobrador);
	};	
	
	public String getDirectorioTemporal(){
		return consultaCOBGenericoDAO.getDirectorioTemporal();
	}
	
	/**
	 * Genera el reporte comision abogados.
	 *
	 * @param criteria the criteria
	 */
	public void generarComisionAbogados(Map criteria){
		this.consultaCOBGenericoDAO.generarComisionAbogados(criteria);
	}
}
