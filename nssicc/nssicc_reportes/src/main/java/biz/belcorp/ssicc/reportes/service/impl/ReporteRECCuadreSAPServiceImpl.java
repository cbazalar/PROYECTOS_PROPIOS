/**
 * 
 */
package biz.belcorp.ssicc.reportes.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.axis2.client.Options;
import org.apache.axis2.transport.http.HTTPConstants;
import org.apache.axis2.transport.http.HttpTransportProperties;
import org.apache.axis2.transport.http.HttpTransportProperties.Authenticator;
import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl;
import biz.belcorp.ssicc.reportes.ws.client.stub.sap.rec.cuadre.ZMM_RPT_SALDOS_SICCServiceStub;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

/**
 * @author Ivan Tocto
 *
 */
@Service("reportes.reporteRECCuadreSAPService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ReporteRECCuadreSAPServiceImpl extends BaseReporteInterfaceImpl {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4390094424857704111L;
	
	@Resource(name="scsicc.reporteService")
	private ReporteService reporteService;

	@Resource(name="genericoService")
	private GenericoService genericoService;
		
	public static final String REC_URL_WS_SAP = "urlWSSAP";
	public static final String REC_USUARIO_WS_SAP = "usuarioWSSAP";
	public static final String REC_PASSWORD_WS_SAP = "passwordWSSAP";
	public static final String REC_ISO_WS_SAP = "codigoISOWSSAP";
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.impl.BaseReporteInterfaceImpl#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'beforeExecuteReporte' method");
		}
		
		Map params = (Map) reporteParams.getQueryParams().get("parameterMap");
		
		//Nos conectamos al WebService de SAP y se recupera una lista de productos
		//que lo insertaremos en una tabla temporal
		List items = obtenerListaSAP(MapUtils.getString(params, "codigoPais", ""));
		
		Map paramsSAP = new HashMap();
		paramsSAP.put("codigoPais", MapUtils.getString(params, "codigoPais", ""));
		paramsSAP.put("items", items);
		
		
		reporteService.executeCargaReporteRECCuadreSAP(params);
		
		reporteParams.setQueryParams(params);
		
		return reporteParams;
		
	}
	
	/**
	 * 
	 * @param codigoPais
	 * @return
	 * @throws Exception
	 */
	private List obtenerListaSAP(String codigoPais) throws Exception {
		String targetEndpoint = obtenerValorParametro(codigoPais, REC_URL_WS_SAP);
		String usuario = obtenerValorParametro(codigoPais, REC_USUARIO_WS_SAP);;
		String password = obtenerValorParametro(codigoPais, REC_PASSWORD_WS_SAP);;
		String iso = obtenerValorParametro(codigoPais, REC_ISO_WS_SAP);;
		List list = new ArrayList();
		 
		ZMM_RPT_SALDOS_SICCServiceStub stub = new ZMM_RPT_SALDOS_SICCServiceStub(targetEndpoint);

	 	Options opt = stub._getServiceClient().getOptions();

	 	opt.setProperty(HTTPConstants.SO_TIMEOUT, new Integer(300000));
	    HttpTransportProperties.Authenticator authenticator = new HttpTransportProperties.Authenticator();
        List auth = new ArrayList();
        auth.add(Authenticator.BASIC);
        authenticator.setAuthSchemes(auth);
        authenticator.setUsername(usuario);
        authenticator.setPassword(password);
        authenticator.setPreemptiveAuthentication(true);
        opt.setProperty(HTTPConstants.AUTHENTICATE, authenticator);
        stub._getServiceClient().setOptions(opt);

        Options opt1 = stub._getServiceClient().getOptions();

        if(opt1.getProperty(HTTPConstants.AUTHENTICATE)!=null){
            Authenticator authenticator1=(Authenticator)opt1.getProperty(HTTPConstants.AUTHENTICATE);
            log.info(authenticator1.getUsername()+" : "+authenticator1.getPassword());
        }
        else {
          	log.info("opt1.getProperty(HTTPConstants.AUTHENTICATE); is null ");
        }  	

        ZMM_RPT_SALDOS_SICCServiceStub.ZmmRptSaldosSicc zmmRptSaldosSicc4 = new ZMM_RPT_SALDOS_SICCServiceStub.ZmmRptSaldosSicc();
        ZMM_RPT_SALDOS_SICCServiceStub.TableOfZmmEstSaldosSicc table =  new ZMM_RPT_SALDOS_SICCServiceStub.TableOfZmmEstSaldosSicc();
        ZMM_RPT_SALDOS_SICCServiceStub.Char50 iw = new ZMM_RPT_SALDOS_SICCServiceStub.Char50();
		iw.setChar50(iso);
		zmmRptSaldosSicc4.setIWerks(iw );
		zmmRptSaldosSicc4.setTDatosMaterial(table);
		
		ZMM_RPT_SALDOS_SICCServiceStub.ZmmRptSaldosSiccResponse response =stub.zmmRptSaldosSicc(zmmRptSaldosSicc4);
		ZMM_RPT_SALDOS_SICCServiceStub.ZmmEstSaldosSicc[] item =response.getTDatosMaterial().getItem();

		if(item != null)
			log.info("total de SAP recuperados : " + item.length);
		else
			log.info("total de SAP recuperados : null");

		if(item != null && item.length> 0){
			for(int i=0; i<item.length; i++) {
				Map mapItem = new HashMap();
				mapItem.put("codigoSAP", item[i].getMatnr().getChar50());
				mapItem.put("descripcion", item[i].getMaktx().getChar50());
				mapItem.put("unidades", item[i].getLabst().getChar50());
				list.add(mapItem);
			}	
		}
		
		return list;
	}
	
	/**
	 * @param codigoPais
	 * @param parametro
	 * @return
	 */
	private String obtenerValorParametro(String codigoPais, String parametro) {
		String valorParametro = "";
	
		ParametroPais parametroPais = new ParametroPais();
		parametroPais.setCodigoPais(codigoPais);
		parametroPais.setCodigoSistema("REC");
		parametroPais.setNombreParametro(parametro);
		parametroPais.setIndicadorActivo(Constants.NUMERO_UNO);
	
		List lstParametros = genericoService.getParametrosPais(parametroPais);
	
		if(lstParametros != null && lstParametros.size() > 0){			
			ParametroPais ps = (ParametroPais)lstParametros.get(0);					
			valorParametro = ps.getValorParametro();
		}
		
		return valorParametro;
	}
	
}
