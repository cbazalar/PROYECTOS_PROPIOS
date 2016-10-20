package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.service.spusicc.reclamos.ConsultaRECCDRsDigitadosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseConsultaAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.scsicc.form.ConsultaRECSecuenciasZonasDiariasForm;

/**
 * @author peextrramirez - Rosalvina Ramirez Guardia
 * 
 */
@ManagedBean
@SessionScoped
public class ConsultaRECSecuenciasZonasDiariasAction extends BaseConsultaAbstractAction{
	
	private static final long serialVersionUID = 1525532707012208637L;
	
	private List recSecuenciaZonaList;

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		
		return new ConsultaRECSecuenciasZonasDiariasForm();
	}
	
	@Override
	protected void setViewAtributes() throws Exception {}
	

	@Override
	protected List setFindAttributes() throws Exception {
		

		ConsultaRECCDRsDigitadosService service = (ConsultaRECCDRsDigitadosService) getBean("spusicc.consultaRECCDRsDigitadosService");
		
		List resultado = service.getSecuenciaZonaDiaria();		
		
		recSecuenciaZonaList = resultado;									
	
		return resultado;
	}
	
	
	
	
}
