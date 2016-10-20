package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.ConsultaCOBSupervisorDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CuentaCorrienteConsultora;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBSupervisorService;


/**
 * Service que controla la Consulta de Telecobro
 *  
 * <p>
 * <a href="ConsultaCOBSupervisorServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 */
@Service("spusicc.consultaCOBSupervisorService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaCOBSupervisorServiceImpl extends BaseService implements ConsultaCOBSupervisorService {

	@Resource(name="spusicc.consultaCOBSupervisorDAO")
	private ConsultaCOBSupervisorDAO consultaCOBSupervisorDAO;

	
	/* (non-Javadoc)
	 * Obtiene la Cartera del Supervisor
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBSupervisorService#getCarteraSupervisorByFilter(java.util.Map)
	 */
	public List getCarteraSupervisorByFilter(Map criteria){
		return consultaCOBSupervisorDAO.getCarteraSupervisorByFilter(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBSupervisorService#getConsultoraByFilter(java.util.Map)
	 */
	public List getConsultoraSupervisorByFilter(Map criteria){
		return consultaCOBSupervisorDAO.getConsultoraSupervisorByFilter(criteria);
	}
	
/* (non-Javadoc)
	 * Graba la gestion realizada
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#saveLlamadaConsultora(java.util.Map)
	 */
	public void saveGestionCartera(Map criteria){
		consultaCOBSupervisorDAO.saveGestionCartera(criteria);
	}
	
	/* (non-Javadoc)
	 *  Graba las gestiones con compromiso de pago
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBSupervisorService#saveCompromisoPago(java.util.Map)
	 */
	public void saveCompromisoPago(Map criteria){
		consultaCOBSupervisorDAO.saveCompromisoPago(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#getListaGestionesCartera(java.util.Map)
	 */
	public List getListaGestionesCartera(Map criteria){
		return consultaCOBSupervisorDAO.getListaGestionesCartera(criteria);
	}
	
		/* (non-Javadoc)
	 * Obtiene el detalle de la cuenta corriente de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#getReferencias(java.util.Map)
	 */
	public List getReferencias(Map criteria){
		return consultaCOBSupervisorDAO.getReferencias(criteria);
	}
	
	/* (non-Javadoc)
	 * Obtiene el detalle de la cuenta corriente de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#getReferencias(java.util.Map)
	 */
	public List getReferenciasDeudora(Map criteria){
		return consultaCOBSupervisorDAO.getReferenciasDeudora(criteria);
	}
	
	/* (non-Javadoc)
	 * Obtiene el detalle de la cuenta corriente de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#getReferencias(java.util.Map)
	 */
	public List getAvalesDeudora(Map criteria){
		return consultaCOBSupervisorDAO.getAvalesDeudora(criteria);
	}
	
	/* (non-Javadoc)
	 * Obtiene el detalle de la consultora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBSupervisorService#getDetalleConsultora(java.util.Map)
	 */
	public List getDetalleConsultora(Map criteria){
		List listaConsultora = new ArrayList();
		listaConsultora = consultaCOBSupervisorDAO.getDetalleConsultora(criteria);		
		double saldo = 0;
		
		if(listaConsultora.size()!=0){	
			for (int i = 0; i < listaConsultora.size(); i++) {
				CuentaCorrienteConsultora cuentaCte = (CuentaCorrienteConsultora)listaConsultora.get(i);
				if(i == 0){
					try {						
						saldo = Double.parseDouble(consultaCOBSupervisorDAO.getSaldoInicial(criteria));
					} catch (Exception e) {
						// TODO: handle exception
					}					
				}
				else{
					if(cuentaCte.getCargo()!= ""){
						try {
							saldo += Double.parseDouble(cuentaCte.getCargo());
						} catch (Exception e) {
							// TODO: handle exception
						}						
					}
					if(cuentaCte.getAbono()!= ""){
						try {
							saldo -= Double.parseDouble(cuentaCte.getAbono());
						} catch (Exception e) {
							// TODO: handle exception
						}						
					}
				}
				
				NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH); 
			    DecimalFormat form = (DecimalFormat)nf; 
			    form.applyPattern("#,###.00"); 			    

				cuentaCte.setSaldo(form.format(saldo).toString());
				listaConsultora.set(i,cuentaCte);
			}
		}
		
		return listaConsultora;
	}
	
	/* (non-Javadoc)
	 * Obtiene el detalle de la cuenta corriente de la consultora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBSupervisorService#getDetalleMovimiento(java.util.Map)
	 */
	public List getDetalleMovimiento(Map criteria){
		return consultaCOBSupervisorDAO.getDetalleMovimiento(criteria);		
	}
	
	/* (non-Javadoc)
	 * Actualiza el telefono de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBSupervisorService#actualizarTelefonoDeudora(java.util.Map)
	 */
	public void actualizarTelefonoDeudora(Map criteria){
		consultaCOBSupervisorDAO.actualizarTelefonoDeudora(criteria);
	}		
	
	/* (non-Javadoc)
	 * Rebaja la cartera de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBSupervisorService#rebajarCarteraDeudora(java.util.Map)
	 */
	public void rebajarTemporalCarteraDeudora(Map criteria){
		consultaCOBSupervisorDAO.rebajarTemporalCarteraDeudora(criteria);
	}
	
	/* (non-Javadoc)
	 * Rebaja la cartera de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBSupervisorService#rebajarCarteraDeudora(java.util.Map)
	 */
	public void rebajarDefinitivoCarteraDeudora(Map criteria){
		consultaCOBSupervisorDAO.rebajarDefinitivoCarteraDeudora(criteria);
	}
	
	/* (non-Javadoc)
	 * Bloquea financieramente una consultora.
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBSupervisorService#bloquearFinancieroDeudora(java.util.Map)
	 */
	public void bloquearFinancieroDeudora(Map criteria){
		consultaCOBSupervisorDAO.bloquearFinancieroDeudora(criteria);
	}
	
	
}
