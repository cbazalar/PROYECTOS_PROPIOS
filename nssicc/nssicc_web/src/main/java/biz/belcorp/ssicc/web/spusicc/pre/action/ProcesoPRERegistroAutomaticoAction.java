package biz.belcorp.ssicc.web.spusicc.pre.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.MatrizFacturacion;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.pre.ProcesoPRERegistroAutomaticoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.pre.form.ProcesoPRERegistroAutomaticoForm;

@SessionScoped
@ManagedBean
public class ProcesoPRERegistroAutomaticoAction extends
		BaseProcesoAbstractAction {

	private static final long serialVersionUID = -8843847069733096901L;
	private List siccCatalogoList = new ArrayList();

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception {
		// TODO Auto-generated method stub
		ProcesoPRERegistroAutomaticoForm form = new ProcesoPRERegistroAutomaticoForm();
		return form;
	}

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		// TODO Auto-generated method stub
		Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoUsuario", usuario.getLogin());		
		log.debug("Los parametros del Reporte en el executeProcess son: "+ params.toString());
		
		ProcesoPRERegistroAutomaticoService service = (ProcesoPRERegistroAutomaticoService)getBean("spusicc.procesoPRERegistroAutomaticoService");
		service.executeRegistroAutomatico(params);
		
		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		ProcesoPRERegistroAutomaticoForm f = (ProcesoPRERegistroAutomaticoForm) this.formProceso;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccCatalogoList = reporteService.getListaGenerico("getOidCatalogoProductos", null);

		// Obtenemos la matriz seleccionada desde la session de usuario
		MatrizFacturacion matriz = this.mPantallaPrincipalBean.getPedMatrizSeleccionada();

		if (matriz != null) {
			f.setCodigoPeriodo(matriz.getCodigoPeriodo());
			f.setOidPeriodo(matriz.getOidPeriodo());
			f.setOidMatriz(matriz.getOidMatriz());
			
		} else {
			if(StringUtils.equals(this.mPantallaPrincipalBean.getCurrentMenu(),Constants.CODIGO_MENU_REGISTROAUTOMATICO)){	
				log.debug("Redireccionando a la pantalla de Seleccion de Matriz...");
				this.mPantallaPrincipalBean.setCodigoAccionRetorno(Constants.PED_CODIGO_PANTALLA_REGISTRO_AUTOMATICO);
				this.redireccionarPagina("mantenimientoPEDSeleccionMatrizFacturacionList");
			}	
			
		}
	}
	
	/**
	 * @throws Exception
	 */
	public void inicializarValores() throws Exception{
		
		ProcesoPRERegistroAutomaticoForm f = (ProcesoPRERegistroAutomaticoForm) this.formProceso;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccCatalogoList = reporteService.getListaGenerico(
				"getOidCatalogoProductos", null);

		// Obtenemos la matriz seleccionada desde la session de usuario
		MatrizFacturacion matriz = this.mPantallaPrincipalBean
				.getPedMatrizSeleccionada();

		if (matriz != null) {
			f.setCodigoPeriodo(matriz.getCodigoPeriodo());
			f.setOidPeriodo(matriz.getOidPeriodo());
			f.setOidMatriz(matriz.getOidMatriz());

		} else {
			log.debug("Redireccionando a la pantalla de Seleccion de Matriz...");
			this.mPantallaPrincipalBean
					.setCodigoAccionRetorno(Constants.PED_CODIGO_PANTALLA_REGISTRO_AUTOMATICO);
			this.redireccionarPagina("mantenimientoPEDSeleccionMatrizFacturacionList");
		}
	
	}

	/**
	 * @return the siccCatalogoList
	 */
	public List getSiccCatalogoList() {
		return siccCatalogoList;
	}

	/**
	 * @param siccCatalogoList the siccCatalogoList to set
	 */
	public void setSiccCatalogoList(List siccCatalogoList) {
		this.siccCatalogoList = siccCatalogoList;
	}
	
	

}
