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

import biz.belcorp.ssicc.dao.spusicc.cobranzas.ConsultaCOBEjecutivoDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CuentaCorrienteConsultora;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBEjecutivoService;


/**
 * Service que controla la Consulta de Ejecutivo
 *  
 * <p>
 * <a href="ConsultaCOBEjecutivoServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias</a>
 * 
 */
@Service("spusicc.consultaCOBEjecutivoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaCOBEjecutivoServiceImpl extends BaseService implements ConsultaCOBEjecutivoService {

	@Resource(name="spusicc.consultaCOBEjecutivoDAO")
	private ConsultaCOBEjecutivoDAO consultaCOBEjecutivoDAO;

	
	
	/* (non-Javadoc)
	 * Obtiene la Cartera del Ejecutivo de Cobranzas
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#getCarteraEjecutivoByFilter(java.util.Map)
	 */
	public List getCarteraEjecutivoByFilter(Map criteria){
		return consultaCOBEjecutivoDAO.getCarteraEjecutivoByFilter(criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#getCarteraLlamadaEntranteByFilter(java.util.Map)
	 */
	public List getCarteraLlamadaEntranteByFilter(Map criteria){
		return consultaCOBEjecutivoDAO.getCarteraLlamadaEntranteByFilter(criteria);
	}
	
	/* (non-Javadoc)
	 * Graba la gestion realizada
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#saveLlamadaConsultora(java.util.Map)
	 */
	public void saveGestionCartera(Map criteria){
		consultaCOBEjecutivoDAO.saveGestionCartera(criteria);
	}
	
	/* (non-Javadoc)
	 * Graba el compromiso de pago
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#saveCompromisoPago(java.util.Map)
	 */
	public void saveCompromisoPago(Map criteria){
		consultaCOBEjecutivoDAO.saveCompromisoPago(criteria);
	}
				
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#getListaGestionesCartera(java.util.Map)
	 */
	public List getListaGestionesCartera(Map criteria){
		return consultaCOBEjecutivoDAO.getListaGestionesCartera(criteria);
	}
	
	/* (non-Javadoc)
	 * Obtiene el detalle de la cuenta corriente de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#getReferencias(java.util.Map)
	 */
	public List getReferencias(Map criteria){
		return consultaCOBEjecutivoDAO.getReferencias(criteria);
	}
	
	/* (non-Javadoc)
	 * Obtiene el detalle de la cuenta corriente de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#getReferencias(java.util.Map)
	 */
	public List getReferenciasDeudora(Map criteria){
		return consultaCOBEjecutivoDAO.getReferenciasDeudora(criteria);
	}
	
	/* (non-Javadoc)
	 * Obtiene el detalle de la cuenta corriente de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#getReferencias(java.util.Map)
	 */
	public List getAvalesDeudora(Map criteria){
		return consultaCOBEjecutivoDAO.getAvalesDeudora(criteria);
	}
	
	/* (non-Javadoc)
	 * Obtiene el saldo actual de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#getDetalleConsultora(java.util.Map)
	 */
	public List getDetalleConsultora(Map criteria){
		List listaConsultora = new ArrayList();
		listaConsultora = consultaCOBEjecutivoDAO.getDetalleConsultora(criteria);		
		double saldo = 0;
		
		if(listaConsultora.size()!=0){	
			for (int i = 0; i < listaConsultora.size(); i++) {
				
				CuentaCorrienteConsultora cuentaCte = (CuentaCorrienteConsultora)listaConsultora.get(i); 
				if(i == 0){
					try {						
						saldo = Double.parseDouble(consultaCOBEjecutivoDAO.getSaldoInicial(criteria));
					} catch (Exception e) {
						// TODO: handle exception
					}					
				}
				else{
					
					CuentaCorrienteConsultora cuentaCteAnt = (CuentaCorrienteConsultora)listaConsultora.get(i-1);
					
					if(cuentaCteAnt.getCargo()!= ""){
						try {
							
						   saldo -= Double.parseDouble(cuentaCteAnt.getCargo());
							
																					
						} catch (Exception e) {
							// TODO: handle exception
						}						
					}
					if(cuentaCteAnt.getAbono()!= ""){
						try {
							
						   saldo += Double.parseDouble(cuentaCteAnt.getAbono());	
							
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
	 * Obtiene el detalle de un cargo de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#getDetalleMovimiento(java.util.Map)
	 */
	public List getDetalleMovimiento(Map criteria){
		return consultaCOBEjecutivoDAO.getDetalleMovimiento(criteria);		
	}
	
	/* (non-Javadoc)
	 *  Actualiza los telefonos de la deudora
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBEjecutivoService#actualizarTelefonoDeudora(java.util.Map)
	 */
	public void actualizarTelefonoDeudora(Map criteria){
		consultaCOBEjecutivoDAO.actualizarTelefonoDeudora(criteria);
	}		
	
}
