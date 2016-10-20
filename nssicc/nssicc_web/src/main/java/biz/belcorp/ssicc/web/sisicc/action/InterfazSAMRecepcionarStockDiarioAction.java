package biz.belcorp.ssicc.web.sisicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.xml.rpc.holders.StringHolder;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazExecutionResult;
import biz.belcorp.ssicc.service.spusicc.MantenimientoPRECambioCodigoVentaService;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROL;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLLocator;
import biz.belcorp.ssicc.service.spusicc.pedido.ws.axis.WsPortalesPROLSoap;
import biz.belcorp.ssicc.service.spusicc.pedidos.ProcesoPEDService;
import biz.belcorp.ssicc.web.framework.base.action.BaseInterfazAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;
import biz.belcorp.ssicc.web.sisicc.form.InterfazSAMRecepcionarStockDiarioForm;

@SessionScoped
@ManagedBean
public class InterfazSAMRecepcionarStockDiarioAction extends
		BaseInterfazAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4447728403528401637L;
	private List samTipoCargaList;

	@Override
	protected BaseInterfazForm devuelveFormInterfaz() throws Exception {
		// TODO Auto-generated method stub
		InterfazSAMRecepcionarStockDiarioForm form = new InterfazSAMRecepcionarStockDiarioForm();
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub

		// Se obtiene el indicador de de actividad para PROL para la campana
		// activa
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();
		InterfazSAMRecepcionarStockDiarioForm f = (InterfazSAMRecepcionarStockDiarioForm) this.formInterfaz;
		MantenimientoPRECambioCodigoVentaService service = (MantenimientoPRECambioCodigoVentaService) getBean("spusicc.mantenimientoPRECambioCodigoVentaService");
		ProcesoPEDService servicePed = (ProcesoPEDService) getBean("spusicc.procesoPEDService");
		Map criteria = new HashMap();

		criteria.put("codigoPais", codigoPais);

		String codigoPeriodo = service.getPeriodoActivo(criteria);
		f.setCodigoPeriodo(codigoPeriodo);

		criteria.put("codigoPeriodo", codigoPeriodo);
		String indicadorPROL = servicePed.getIndicadorActividadPROL(criteria);
		f.setIndicadorPROL(indicadorPROL);

		List resultado = new ArrayList();
		resultado = servicePed.getTiposCargaStock();
		this.samTipoCargaList = resultado;

		String codigoInterfaz = getRequest().getParameter("codigoInterfaz");
		f.setTipoCarga(codigoInterfaz);
	}
	
	/**
	 * @param val
	 */
	public void showCargarInterfaz(ValueChangeEvent val) {

		log.debug(">>showCargarInterfaz ");
		String codigoInterfaz =  val.getNewValue().toString();
		InterfazSAMRecepcionarStockDiarioForm f = (InterfazSAMRecepcionarStockDiarioForm) this.formInterfaz;
		
		this.parametrosPantalla = new HashMap();
		this.parametrosPantalla.put("codigoInterfaz", codigoInterfaz);
		this.parametrosPantalla.put("tipoRecepcion", codigoInterfaz);
		this.parametrosPantalla.put("codigoProcesoBatch", f.getCodigoProcesoBatch());
		try {
			this.setBeforeViewAtributes();
			this.setViewAtributes();
		}
		catch(Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.action.BaseInterfazAbstractAction#afterExecuteInterfaz(java.util.Map, biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazExecutionResult)
	 */
	protected void afterExecuteInterfaz(Map params,	InterfazExecutionResult interfazExecutionResult) throws Exception{
    	super.afterExecuteInterfaz(params, interfazExecutionResult);
    	log.debug("Entering 'reload' method");

    	ProcesoPEDService servicePed = (ProcesoPEDService) getBean("spusicc.procesoPEDService");

    	String codigoPais = (String)params.get("codigoPais");
    	String indicadorPROL = (String)params.get("indicadorPROL");
    	String codigoPeriodo = (String)params.get("codigoPeriodo");
    	String tipoCarga = (String)params.get("tipoCarga");
    	Map criteria = new HashMap();

    	criteria.put("codigoPais", codigoPais);
    	criteria.put("codigoPeriodo", codigoPeriodo);

    	//Se actualiza el indicador de activa prol a cero
    	if (Constants.SAM_TIPO_CARGA_BATCH.equals(tipoCarga) &&
    	    Constants.NUMERO_TRES.equals(indicadorPROL)){
    			criteria.put("indicador", Constants.NUMERO_CUATRO);
    			servicePed.executeActualizaIndicadorPROL(criteria);
    	}

    	if (Constants.SAM_TIPO_CARGA_PROL.equals(tipoCarga)){

        	servicePed.executeInicializaStock();

        	//Se actualiza el indicador de activa prol a uno
        	if (Constants.NUMERO_CERO.equals(indicadorPROL)){
        		criteria.put("indicador", Constants.NUMERO_UNO);
        		servicePed.executeActualizaIndicadorPROL(criteria);
        	}
        	/*INICIO CAMBIO LOG*/
        	//Invocando al WebService
        	GenericoService serviceGen = (GenericoService) getBean("genericoService");
        	String indActivaWS = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_PED, Constants.PED_IND_ACTIVA_WS_PROL); 
        	log.debug("indActivaWS : "+indActivaWS);
        	if (Constants.NUMERO_UNO.equals(indActivaWS)){
        		 try{
        			 log.info("ActivacioPROL - Invocando al Web Service ");
             		 String codPaisPROL = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_PED, Constants.PED_PAIS_PROL);
             		 String codMarcaPROL = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_PED, Constants.PED_MARCA_PROL);
             		 String urlWSPROL = serviceGen.getParametroPais(codigoPais, Constants.SISTEMA_PED, Constants.PED_URL_WS_PROL);
             		 WsPortalesPROL locator = new WsPortalesPROLLocator();

             		 WsPortalesPROLSoap service =locator.getwsPortalesPROLSoap(new java.net.URL(urlWSPROL));
         			 StringHolder indicador = new StringHolder("");
         		 	 StringHolder mensaje = new StringHolder("");
         		 	 log.debug("ActivacioPROL - Parametros: codPaisPROL="+codPaisPROL+ " codMarcaPROL="+ codMarcaPROL);
         			 service.activacionPROL(codPaisPROL, codMarcaPROL,indicador ,mensaje);
         			 log.debug("ActivacioPROL - Resouesta: indicador="+indicador+ " mensaje="+ mensaje);
        		 }catch(Exception e ){
        			 log.error("ActivacioPROL - " + e);
        			 e.printStackTrace();
        		 }
        	}
        	/*FIN CAMBIO LOG*/
    	}

    }
	
	/**
	 * @return the samTipoCargaList
	 */
	public List getSamTipoCargaList() {
		return samTipoCargaList;
	}

	/**
	 * @param samTipoCargaList
	 *            the samTipoCargaList to set
	 */
	public void setSamTipoCargaList(List samTipoCargaList) {
		this.samTipoCargaList = samTipoCargaList;
	}

}
