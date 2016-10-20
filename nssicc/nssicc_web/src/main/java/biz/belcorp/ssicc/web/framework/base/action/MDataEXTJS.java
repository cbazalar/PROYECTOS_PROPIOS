package biz.belcorp.ssicc.web.framework.base.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import biz.belcorp.ssicc.dao.sisicc.model.HistoricoReporte;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.HistoricoReporteService;

import com.google.gson.Gson;

/**
 * @author cbazalar
 *
 */
@ManagedBean  
@RequestScoped
public class MDataEXTJS extends MBaseSistemaAbstractJSF  {

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
	}

	//Map que contendra los parametros ingresados por request a la pantalla la cual se cargaran en el
	//Post constructor del manage
	protected Map<String,String> parametrosPantalla;
	protected String dataJson;
	
	/**
	 * Metodo que se ejecuta luego que se ejecuta el Constructor de la clase
	 */
	@PostConstruct
	public void view() {
		try {
			
			this.parametrosPantalla = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();				
			this.dataJson = (String)this.parametrosPantalla.get("dataJson");
			this.dataJson = new String("");
			
			HistoricoReporteService historicoReporteService = (HistoricoReporteService)this.getBeanService("sisicc.historicoReporteService");
			
			Map criteria = new HashMap();
			criteria.put("codigoUsuario", this.mPantallaPrincipalBean.getCurrentUser().getLogin());						
			criteria.put("fechaInicio", DateUtil.getDate(new Date()));	
			List<HistoricoReporte> listaHistorico = (List<HistoricoReporte>)historicoReporteService.getHistoricoReporteByUser(criteria);
			
			if(listaHistorico != null && listaHistorico.size() > 0){
				Gson gson = new Gson();
				this.dataJson = gson.toJson(listaHistorico);	
				log.debug("JSON Reporte: " + this.dataJson);
			}	
			
			
		}
		catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}

	public String getDataJson() {
		return dataJson;
	}

	public void setDataJson(String dataJson) {
		this.dataJson = dataJson;
	}
	
	
	
	
}
