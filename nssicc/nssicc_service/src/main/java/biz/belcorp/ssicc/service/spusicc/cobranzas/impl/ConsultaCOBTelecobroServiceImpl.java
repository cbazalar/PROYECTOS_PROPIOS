package biz.belcorp.ssicc.service.spusicc.cobranzas.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ConsultaCOBTelecobroDAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.CuentaCorrienteConsultora;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.spusicc.cobranzas.ConsultaCOBTelecobroService;

/**
 * Service que controla la Consulta de Telecobro
 *  
 * <p>
 * <a href="ConsultaCOBTelecobroServiceImpl.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 * 
 */

@Service("spusicc.consultaCOBTelecobroService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ConsultaCOBTelecobroServiceImpl extends BaseService implements ConsultaCOBTelecobroService {

	@Resource(name="spusicc.consultaCOBTelecobroDAO")
	ConsultaCOBTelecobroDAO consultaCOBTelecobroDAO;

		
	public List getConsultorasByFilter(Map criteria){
		return consultaCOBTelecobroDAO.getConsultorasByFilter(criteria);
	}
	
	public void saveLlamadaConsultora(Map criteria){
		consultaCOBTelecobroDAO.saveLlamadaConsultora(criteria);
	}
	
	public void saveCompromisoPago(Map criteria){
		consultaCOBTelecobroDAO.saveCompromisoPago(criteria);
	}
	
	public List getListaLlamadas(Map criteria){
		return consultaCOBTelecobroDAO.getListaLlamadas(criteria);
	}
	
	public List getReferencias(Map criteria){
		return consultaCOBTelecobroDAO.getReferencias(criteria);
	}
	
	public List getDetalleConsultora(Map criteria){
		
		List listaConsultora = new ArrayList();
		Integer validarIncMig = consultaCOBTelecobroDAO.getValidarConsultoraIncobrablesMigradas(criteria);
		int inicio = 0;
		
		if (validarIncMig.intValue() == 1 ){
			listaConsultora = consultaCOBTelecobroDAO.getCuentaCorrienteConsultoraIncobrablesMigradas(criteria);
			inicio = 1;
		}else
		listaConsultora = consultaCOBTelecobroDAO.getDetalleConsultora(criteria);		
		
		double saldo = 0;
		
		if(listaConsultora.size()!=0){	
			for (int i = inicio; i < listaConsultora.size(); i++) {
				
				CuentaCorrienteConsultora cuentaCte = (CuentaCorrienteConsultora)listaConsultora.get(i); 
				if(i == inicio){
					try {						
						
						//Si el tipo de movimiento es blanco && descripcion es "Abono en proceso CDR", restar el abono
						saldo = Double.parseDouble(consultaCOBTelecobroDAO.getSaldoInicial(criteria));
						if(StringUtils.isBlank(cuentaCte.getCodigoTipoMovimiento()) && StringUtils.equalsIgnoreCase(cuentaCte.getTipoMovimiento(), Constants.HIP_CCC_DESCRIPCION_TIPO_MOVIMIENTO_APRONO_EN_PROCESO_CDR))
						{
							saldo = saldo - Double.parseDouble(cuentaCte.getAbono());
						}						
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
//							log.debug("error cargo "+e.getLocalizedMessage());
//							e.printStackTrace();
						}						
					}
					if(cuentaCteAnt.getAbono()!= ""){
						try {
							
						   saldo += Double.parseDouble(cuentaCteAnt.getAbono());	
							
						} catch (Exception e) {
//							log.debug("error abono "+e.getLocalizedMessage());
//							e.printStackTrace();
						}						
					}
				}
				
//				NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH); 
//			    DecimalFormat form = (DecimalFormat)nf; 
//			    form.applyPattern("#,###.00"); 			    

				//cuentaCte.setSaldo(form.format(saldo).toString());
				//log.debug("saldo "+saldo);
				saldo = Precision.round(saldo, 2);
				cuentaCte.setSaldo(String.valueOf(saldo));

				listaConsultora.set(i,cuentaCte);
			}

		}
		
		return listaConsultora;
	}
	
	public List getDetalleMovimiento(Map criteria){
		return consultaCOBTelecobroDAO.getDetalleMovimiento(criteria);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBTelecobroService#getCabeceraMovimientoBanco(java.util.Map)
	 */
	public List getCabeceraMovimientoBanco(Map criteria) {
		return consultaCOBTelecobroDAO.getCabeceraMovimientoBanco(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBTelecobroService#getDetalleMovimientoBanco(java.util.Map)
	 */
	public List getDetalleMovimientoBanco(Map criteria) {
		return consultaCOBTelecobroDAO.getDetalleMovimientoBanco(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBTelecobroService#getDetalleMovimientoCuenta(java.util.Map)
	 */
	public List getDetalleMovimientoCuenta(Map criteria) {
		return consultaCOBTelecobroDAO.getDetalleMovimientoCuenta(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBTelecobroService#getDetalleConsultoraCampanha(java.util.Map)
	 */
	public Map getDetalleConsultoraCampanha(Map criteria) {
		
		Map retorno = new HashMap();
		
		consultaCOBTelecobroDAO.executeCtaCteUltimasCampanhas(criteria);
		
		List lista =  consultaCOBTelecobroDAO.getCtaCteUltimasNCampanhas(criteria);		
		List listaCabecera = consultaCOBTelecobroDAO.getCtaCteUltimasNCampanhasCabecera(criteria);
		
		//Abono en proceso CDR
		List mapAbono = consultaCOBTelecobroDAO.getAbonoProcesoCDR(criteria);
		Map registroAbonoCDR = new HashMap();
		if(mapAbono != null && mapAbono.size() > 0)
			registroAbonoCDR.put("descripcionMovimiento", ((Map)mapAbono.get(0)).get("descripcionMovimiento"));
		
		List listaDetalle = new ArrayList();
		
		if(lista != null && lista.size() > 0)
		{
			for(int i=0; i<lista.size(); i++)
			{
				
				Map fila = (Map)lista.get(i);
				
				String campania = MapUtils.getString(fila, "campaniaCalculada", "");
				
				double abono =  MapUtils.getDouble(fila, "importeAbono", new Double(0)).doubleValue();
				double cargo =  MapUtils.getDouble(fila, "importeCargo", new Double(0)).doubleValue();
				
				double valor = cargo;
								
				if(abono != 0)
					valor = abono;
				
				fila.put(campania, valor);
				
				listaDetalle.add(fila);
			}
		}
		
		//CALCULAMOS LOS SALDOS POR CAMPAÑA
		double saldoTotal = MapUtils.getDouble(criteria, "saldoTotal", new Double(0)).doubleValue();
		
		Map registroInicial = new HashMap();
		Map registroFinal = new HashMap();
		
		registroInicial.put("fechaEmision", "");
		registroInicial.put("descripcionMovimiento", "Saldo Inicial");			
		registroFinal.put("fechaEmision", "");
		registroFinal.put("descripcionMovimiento", "Saldo Final");			
		
		double saldoInicial = 0;
		double saldoFinal = 0;
		
		for(int  i = listaCabecera.size()-1;i >=0;  i--)
		{
			double sumaMontoCampania =  MapUtils.getDouble((Map)listaCabecera.get(i), "sumaMontoCampania", new Double(0)).doubleValue();
			String campanya =  MapUtils.getString((Map)listaCabecera.get(i), "codigo", "");
						
			if(i == listaCabecera.size()-1)
			{
				saldoFinal = saldoTotal;
				saldoInicial = saldoTotal - sumaMontoCampania;
				
				if(mapAbono != null && mapAbono.size() > 0){
					registroAbonoCDR.put(campanya, ((Map)mapAbono.get(0)).get("abono"));
					saldoFinal -= Double.parseDouble(((Map)mapAbono.get(0)).get("abono").toString());
				}
			}
			else
			{
				saldoFinal = saldoInicial;
				saldoInicial = saldoFinal - sumaMontoCampania;
			}
						
			registroInicial.put(campanya, redondear(saldoInicial, 2));			
			registroFinal.put(campanya,  redondear(saldoFinal, 2));
		}
				
		listaDetalle.add(0, registroInicial);
		listaDetalle.add(registroFinal);
		//
		//Agregamos penultima columna
		if(mapAbono != null && mapAbono.size() > 0)
			listaDetalle.add(listaDetalle.size()-1,registroAbonoCDR);
		
		retorno.put("cabecera", listaCabecera);
		retorno.put("detalle", listaDetalle);
		
		return retorno;
	}
	
	/**
	 * Metodo que redondea un numero double
	 * @param doubleNumber
	 * @param decimales
	 * @return
	 */
	public double redondear(double doubleNumber, int decimales) {
		double result;
		BigDecimal bd = new BigDecimal(doubleNumber);
		bd = bd.setScale(decimales, BigDecimal.ROUND_HALF_UP);
		result = bd.doubleValue();
		return result;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBTelecobroService#insertReporteHIPCuentaCorrientesCampanha(java.util.List, java.lang.String)
	 */
	public void insertReporteHIPCuentaCorrientesCampanha(List listaReporte, String codigoUsuario) {
		
		consultaCOBTelecobroDAO.deleteReporteHIPCuentaCorrientesCampanha(codigoUsuario);
		
		if(listaReporte != null)
		{
			for(int i=0; i<listaReporte.size(); i++)
			{
				Map registro = (Map)listaReporte.get(i);
				registro.put("codigoUsuario", codigoUsuario);
				registro.put("numeroRegistro", i);
				consultaCOBTelecobroDAO.insertReporteHIPCuentaCorrientesCampanha(registro);
			}			
		}
	}
			
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBTelecobroService#getPedidoCampanhaProceso(java.util.Map)
	 */
	public Integer getPedidoCampanhaProceso(Map criteria) {
		return consultaCOBTelecobroDAO.getPedidoCampanhaProceso(criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.service.ConsultaCOBTelecobroService#getCierreRegionCampanhaProceso(java.util.Map)
	 */
	public Integer getCierreRegionCampanhaProceso(Map criteria) {
		return consultaCOBTelecobroDAO.getCierreRegionCampanhaProceso(criteria);
	}	

//	@Override
//	public List getAbonoProcesoCDR(Map criteria) {
//		// TODO Auto-generated method stub
//		return consultaCOBTelecobroDAO.getAbonoProcesoCDR(criteria);
//	}
	
}
