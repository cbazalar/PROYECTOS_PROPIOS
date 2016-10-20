package biz.belcorp.ssicc.web.spusicc.mav.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.spusicc.mav.form.ReporteMAVConfiguracionForm;

@ManagedBean
@SessionScoped
public class ReporteMAVConfiguracionAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3486479677709692666L;
	private String formatoReporte;
	protected static final String RUTA_JASPER = "/biz/belcorp/ssicc/reportes/jasper/";
	
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteMAVConfiguracionForm form = new ReporteMAVConfiguracionForm();
		return form;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteMAVConfiguracionPDF";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCConfiguracionConcursoAction.prepareParameterMap' method");
		}
		ReporteMAVConfiguracionForm f = (ReporteMAVConfiguracionForm) this.formReporte;
				
		if(log.isDebugEnabled())
			log.debug("correlativo MAV = " + f.getCorrelativo());
		
		params.put("correlativo", f.getCorrelativo());
		
		//Cargamos los subreportes				
		try {
			ClassPathResource resource1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteMAVConfiguracionConsideraciones" + JASPER_EXTENSION);
			ClassPathResource resource2 = new ClassPathResource(Constants.JASPER_DIRECTORIO + "subReporteMAVConfiguracionRestricciones" + JASPER_EXTENSION);
			
			params.put("SUBREPORT_DIR1", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));
			params.put("SUBREPORT_DIR2", (JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource2.getPath() )));
			
		} catch(Exception e){
			e.printStackTrace();
		}
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		params.put("descripcionPais", pais.getDescripcion());
		
		params.put("titulo", this.getResourceMessage("reporteMAVConfiguracion.titulo") + f.getCorrelativo());
		
		return params;	
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCConfiguracionConcursoAction.setViewAtributes' method");
		}
		
		this.mostrarReporteXLS = true;
		
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		ReporteMAVConfiguracionForm form = (ReporteMAVConfiguracionForm) this.formReporte;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		form.setCodigoPais(pais.getCodigo());
	}
	
	
	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}
	
}
