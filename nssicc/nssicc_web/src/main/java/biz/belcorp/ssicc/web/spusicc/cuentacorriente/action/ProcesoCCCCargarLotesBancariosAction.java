package biz.belcorp.ssicc.web.spusicc.cuentacorriente.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import biz.belcorp.ssicc.dao.model.LabelArchivos;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Interfaz;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.InterfazService;
import biz.belcorp.ssicc.service.sisicc.framework.InterfazExecutionService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseProcesoForm;
import biz.belcorp.ssicc.service.spusicc.cuentacorriente.ProcesoCCCCargarLotesBancariosService;
import biz.belcorp.ssicc.web.framework.base.action.BaseProcesoAbstractAction;
import biz.belcorp.ssicc.web.spusicc.cuentacorriente.form.ProcesoCCCCargarLotesBancariosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProcesoCCCCargarLotesBancariosAction extends BaseProcesoAbstractAction
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5753456202387410154L;
	
	protected List<Interfaz> listaInterfazArchivos;
	protected TreeNode rootListaArchivosEntrada;
	

	@Override
	protected BaseProcesoForm devuelveFormProceso() throws Exception 
	{
		ProcesoCCCCargarLotesBancariosForm formInterfaz= new ProcesoCCCCargarLotesBancariosForm();
		return formInterfaz;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();	
				
		ProcesoCCCCargarLotesBancariosForm f = (ProcesoCCCCargarLotesBancariosForm) this.formProceso;
		
		f.setCodigoModulo(this.parametrosPantalla.get("codigoSistema"));
		f.setCodigoProceso(this.parametrosPantalla.get("codigoProcesoBatch"));
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		f.setCodigoInterfaz(this.parametrosPantalla.get("codigoInterfaz"));
		log.debug(f.getCodigoModulo());
		log.debug(f.getCodigoProceso());
		
		
		Map<String, Object> criteria = new HashMap<String, Object>();
		criteria.put("codigoPais", f.getCodigoPais());  
      	criteria.put("codigoSistema", f.getCodigoSistema());
		criteria.put("codigoInterfaz", f.getCodigoInterfaz());
		
		this.obtenerListaArchivosEntrada(criteria);
		this.organizarTreeListaArchivosEntrada();
		this.mostrarPanelAdicionalProceso = true;
	}
	

	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(Map params, BaseForm form) throws Exception 
	{
		params = super.prepareParamsBeforeExecute(params, form);
		ProcesoCCCCargarLotesBancariosForm f = (ProcesoCCCCargarLotesBancariosForm) this.formProceso;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String usuarioProceso = usuario.getNombres() + ' ' + usuario.getApellidos();
		
		params.put("descripcionPais", pais.getDescripcion());
		params.put("usuarioProceso", usuarioProceso);
		params.put("codigoModulo",f.getCodigoModulo());
		params.put("codigoProceso",f.getCodigoProceso());
		params.put("codigoUsuario", usuario.getLogin());
		
		log.debug(f.getCodigoModulo());
		log.debug(f.getCodigoProceso());
		
		log.debug("Los parametros del prepareParamsBeforeExecute son: " + params.toString());
		
		return params;
	}

	

	@Override
	protected Map<String, Object> executeProcess(Map<String, Object> params)
			throws Exception {
		log.debug("JFA Entrando executeProcess");
		ProcesoCCCCargarLotesBancariosService service = (ProcesoCCCCargarLotesBancariosService) 
														getBean("spusicc.procesoCCCCargarLotesBancariosService");
								
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("usuarioTemp", usuario);
		
		service.executeCargarLotesBancarios(params);
						
		log.debug(params.toString());				
		
		log.debug("JFA Finalizando executeProcess");
		return params;
	}
	
	
	/**
	 * Obtiene Lista de de Archivos de la Interfaz de Entrada y lo setea al Form
	 * @param criteria
	 */
	public void obtenerListaArchivosEntrada(Map<String, Object> criteria) {
		if (log.isWarnEnabled()) {
			log.warn("Entering 'obtenerListaArchivosEntrada' method");
		}
		String mensajeError = "";
		InterfazExecutionService interfazExecutionService = (InterfazExecutionService) getBean("sisicc.interfazExecutionService");
		try {
			criteria = interfazExecutionService.obtenerListaInterfaces(criteria);
			mensajeError = (String) criteria.get("mensajeError");
			if (StringUtils.isNotBlank(mensajeError)) {
				//this.addError("Error: ", mensajeError);
				return;
			}
			List<Interfaz> listaInterfazArchivos = (List<Interfaz>) criteria.get("listaInterfazArchivos"); 
			this.listaInterfazArchivos = listaInterfazArchivos;

	
		}
		catch(Exception e) {
			mensajeError = this.obtieneMensajeErrorException(e);
			//this.addError("Error: ", mensajeError);
			return;
		}
			
	
		if (log.isWarnEnabled()) {
			log.warn("Fin 'obtenerListaInterfaces' method");
		}
		
	}

	
	/**
	 * Organiza la lista de Archivos de Entrada en formato TREENODE para su visualizacion
	 */
	protected final void organizarTreeListaArchivosEntrada() {
		List<Interfaz> listaInterfaz = this.listaInterfazArchivos ;
		if (listaInterfaz.size() <= 0)  return;
		
		this.rootListaArchivosEntrada = new DefaultTreeNode("rootListaArchivosEntrada", null);
		for (int i=0; i <listaInterfaz.size(); i++) {
			Interfaz interfaz = listaInterfaz.get(i);
			
			LabelArchivos labelInterfaz = new LabelArchivos();
			labelInterfaz.setNombreArchivo(interfaz.getCodigo() + " - " + interfaz.getDescripcion());
			labelInterfaz.setEsArchivo(false);
			TreeNode tinterfaz = new DefaultTreeNode(labelInterfaz, rootListaArchivosEntrada);
			tinterfaz.setExpanded(true);
			
			List listaLabelArchivos = interfaz.getArchivos();
			for (int x=0; x <listaLabelArchivos.size(); x++) {
				LabelArchivos labelArchivos = (LabelArchivos) listaLabelArchivos.get(x);
				TreeNode tArchivos = new DefaultTreeNode(labelArchivos, tinterfaz);
				tArchivos.setExpanded(true);
			}
			
		}
	
	}
	
	
	
	
	
	/* GET - SET */
	/**
	 * @return the listaInterfazArchivos
	 */
	public List<Interfaz> getListaInterfazArchivos() {
		return listaInterfazArchivos;
	}

	/**
	 * @param listaInterfazArchivos the listaInterfazArchivos to set
	 */
	public void setListaInterfazArchivos(List<Interfaz> listaInterfazArchivos) {
		this.listaInterfazArchivos = listaInterfazArchivos;
	}

	/**
	 * @return the rootListaArchivosEntrada
	 */
	public TreeNode getRootListaArchivosEntrada() {
		return rootListaArchivosEntrada;
	}

	/**
	 * @param rootListaArchivosEntrada the rootListaArchivosEntrada to set
	 */
	public void setRootListaArchivosEntrada(TreeNode rootListaArchivosEntrada) {
		this.rootListaArchivosEntrada = rootListaArchivosEntrada;
	}
	

	
	
	

}
