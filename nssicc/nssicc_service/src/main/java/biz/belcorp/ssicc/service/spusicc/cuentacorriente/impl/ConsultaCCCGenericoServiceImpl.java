package biz.belcorp.ssicc.service.spusicc.cuentacorriente.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CuentaCorrienteConsultora;
import biz.belcorp.ssicc.dao.spusicc.cuentacorriente.ConsultaCCCGenericoDAO;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService;

/**
 * Service que controla la Consulta de Telecobro
 * 
 * <p>
 * <a href="ConsultaCCCGenericoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 */
@Service("spusicc.consultaCCCGenericoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaCCCGenericoServiceImpl extends BaseService implements
		ConsultaCCCGenericoService {

	@Resource(name = "spusicc.consultaCCCGenericoDAO")
	private ConsultaCCCGenericoDAO consultaCCCGenericoDAO;

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService
	 * #getParametroPais(java.util.Map)
	 */
	public String getParametroPais(Map criteria) {
		return consultaCCCGenericoDAO.getParametroPais(criteria);
	}

	public String getParametroPaisbyCodigo(String codigoParametro) {		
		return consultaCCCGenericoDAO.getParametroPaisbyCodigo(codigoParametro);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBGenericoService#getHistorialGestionesCobranza(java.util.Map)
	 */
	public List getTiposBloqueo(Map criteria) {
		return consultaCCCGenericoDAO.getTiposBloqueo(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getTiposLoteBancarioList()
	 */
	public List getTiposLoteBancarioList() {
		return consultaCCCGenericoDAO.getTiposLoteBancarioList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getEstadosLoteBancarioList()
	 */
	public List getEstadosLoteBancarioList() {
		return consultaCCCGenericoDAO.getEstadosLoteBancarioList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getEstadosPagoBancarioList()
	 */
	public List getEstadosPagoBancarioList() {
		return consultaCCCGenericoDAO.getEstadosPagoBancarioList();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getTiposErrorPagoBancarioList()
	 */
	public List getTiposErrorPagoBancarioList(){
		return consultaCCCGenericoDAO.getTiposErrorPagoBancarioList(); 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getDetalleCargaLoteBancario(java.util.Map)
	 */
	public List getDetalleCargaLoteBancario(Map criteria) {
		return consultaCCCGenericoDAO.getDetalleCargaLoteBancario(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getNumeroLote(java.util.Map)
	 */
	public void getNumeroLote(Map criteria) {
		this.consultaCCCGenericoDAO.getNumeroLote(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getSaldoUnico(java.util.Map)
	 */
	public String getSaldoUnico(Map criteria) {
		return consultaCCCGenericoDAO.getSaldoUnico(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getSaldoVencido(java.util.Map)
	 */
	public String getSaldoVencido(Map criteria) {
		return consultaCCCGenericoDAO.getSaldoVencido(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getSaldoHistoricoTotal(java.util.Map)
	 */
	public String getSaldoHistoricoTotal(Map criteria) {
		return consultaCCCGenericoDAO.getSaldoHistoricoTotal(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getTipoCADDocumentoLegalList(java.util.Map)
	 */
	public List getTipoCADDocumentoLegalList(Map criteria) {
		return consultaCCCGenericoDAO.getTipoCADDocumentoLegalList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getCuentasCorrientesBancariasList(java.util.Map)
	 */
	public List getCuentasCorrientesBancariasList(Map criteria) {
		return consultaCCCGenericoDAO.getCuentasCorrientesBancariasList(criteria);
	}

	
	
	public List getBancosDigitablesList(Map criteria) {
		return consultaCCCGenericoDAO.getBancosDigitablesList(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getCuentasCorrientesBancariasExternasList(java.util.Map)
	 */
	public List getCuentasCorrientesBancariasExternasList(Map criteria) {
		return consultaCCCGenericoDAO.getCuentasCorrientesBancariasExternasList(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getBancosCheques()
	 */
	public List getBancosCheques() {
		return consultaCCCGenericoDAO.getBancosCheques();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getCuentaCorrienteHistoricaConsultoraList(java.util.Map)
	 */
	public List getCuentaCorrienteHistoricaConsultoraList(Map criteria) {
		List listaConsultora = new ArrayList();
		listaConsultora = consultaCCCGenericoDAO.getCuentaCorrienteHistoricaConsultoraList(criteria);		
		double saldo = 0;

		if (listaConsultora.size() != 0) {
			for (int i = 0; i < listaConsultora.size(); i++) {

				CuentaCorrienteConsultora cuentaCte = (CuentaCorrienteConsultora)listaConsultora.get(i); 
				if (i == 0) {
					try {
						saldo = Double.parseDouble(consultaCCCGenericoDAO.getSaldoHistoricoTotal(criteria));
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				else{

					CuentaCorrienteConsultora cuentaCteAnt = (CuentaCorrienteConsultora)listaConsultora.get(i-1);

					if (cuentaCteAnt.getCargo() != "") {
						try {

						   saldo -= Double.parseDouble(cuentaCteAnt.getCargo());
							

						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					if (cuentaCteAnt.getAbono() != "") {
						try {

						   saldo += Double.parseDouble(cuentaCteAnt.getAbono());	

						} catch (Exception e) {
							// TODO: handle exception
						}
					}
				}

//				NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH); 
//			    DecimalFormat form = (DecimalFormat)nf; 
//			    form.applyPattern("#,###.00"); 			    
//
//				cuentaCte.setSaldo(form.format(saldo).toString());
				cuentaCte.setSaldo(String.valueOf(saldo));
				listaConsultora.set(i, cuentaCte);
			}
		}

		return listaConsultora;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getDetalleCuponTramiteDepur(java.util.Map)
	 */
	public List getDetalleCuponTramiteDepur(Map criteria) {
		return consultaCCCGenericoDAO.getDetalleCuponTramiteDepur(criteria);
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getTipoCargosDirectos()
	 */
	public List getTipoCargosDirectos() {
		return consultaCCCGenericoDAO.getTipoCargosDirectos();
	}

		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getTipoAbonosDirectos()
	 */
	public List getTipoAbonosDirectos() {
		return consultaCCCGenericoDAO.getTipoAbonosDirectos();
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getTipoCargosDirectosDigitables()
	 */
	public List getTipoCargosDirectosDigitables() {
		return consultaCCCGenericoDAO.getTipoCargosDirectosDigitables();
	}

		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getTipoAbonosDirectosDigitables()
	 */
	public List getTipoAbonosDirectosDigitables() {
		return consultaCCCGenericoDAO.getTipoAbonosDirectosDigitables();
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cuentacorriente.service.ConsultaCCCGenericoService#getTipoOrigenLotesBancarios()
	 */
	public List getTipoOrigenLotesBancarios(){
		return consultaCCCGenericoDAO.getTipoOrigenLotesBancarios();
	}
	
	public void generarCabeceraLoteBancario(Map criteria) {
		this.consultaCCCGenericoDAO.generarCabeceraLoteBancario(criteria);
	}

	/**
	 * Genera el reporte contable saldos por campaas.
	 *
	 * @param criteria the criteria
	 */
	public void generarContSaldosCampanias(Map criteria){
		this.consultaCCCGenericoDAO.generarContSaldosCampanias(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.service.spusicc.cuentacorriente.ConsultaCCCGenericoService#getTotalFacturaActiva(java.util.Map)
	 */
	@Override
	public String getTotalFacturaActiva(Map criteria) {
		return consultaCCCGenericoDAO.getTotalFacturaActiva(criteria);
	}
}
