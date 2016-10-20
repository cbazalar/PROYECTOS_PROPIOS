package biz.belcorp.ssicc.web.spusicc.flx.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flx.model.InteresFlexipago;
import biz.belcorp.ssicc.service.spusicc.flx.MantenimientoFLXConsultoraService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.flx.form.MantenimientoFLXInteresFlexipagoSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoFLXInteresFlexipagoAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -150074966147559356L;
	
	private List<InteresFlexipago> insertaInteresFlxList;
	private DataTableModel insertaInteresFlxModel;	
	private Object beanRegistroFlexipago;
	private int cantDecimales;

	@Override
	protected String getSalirForward() {		
		return "mantenimientoFLXInteresFlexipagoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {	
		return "mantenimientoFLXInteresFlexipagoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoFLXInteresFlexipagoSearchForm form = new MantenimientoFLXInteresFlexipagoSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
		List lista = service.getInteresFlexipagoList();
		this.insertaInteresFlxList=lista;
		this.insertaInteresFlxModel = new DataTableModel(lista);
		return lista;
		
	}

	@Override
	protected void setViewAtributes() throws Exception {
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
		Pais pais=this.mPantallaPrincipalBean.getCurrentCountry();		
		Map criteria= new HashMap();
		criteria.put("oidPais", pais.getOidPais());
		this.cantDecimales=service.getCantDecimalesxPais(criteria);		
		this.mostrarBotonNuevo=false;
		this.mostrarBotonModificar=false;
		this.mostrarBotonConsultar=false;
		this.mostrarBotonBuscar=false;
		this.mostrarBotonEliminar=false;
		this.mostrarCriteriosBusqueda=false;
		this.mostrarListaBusqueda=false;
		this.insertaInteresFlxList= new ArrayList<InteresFlexipago>();
		this.insertaInteresFlxModel = new DataTableModel();
		this.find();		
	}
	
	//Inserta temporalmente
	public void insertarInteresFlx(ActionEvent actionEvent) {
		try {
			MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
			MantenimientoFLXInteresFlexipagoSearchForm f = (MantenimientoFLXInteresFlexipagoSearchForm) this.formBusqueda;
			if(StringUtils.isNotBlank(this.validarPeriodos())){
				this.addError("ERRROR: ", this.validarPeriodos());
				return;
			}
			this.insertaInteresFlxModel = new DataTableModel();
			BigDecimal rangoHasta=new BigDecimal("0");
			BigDecimal resta=new BigDecimal("0");
			BigDecimal valor=new BigDecimal("0");
			if(this.cantDecimales==2)
				valor=new BigDecimal("0.01");
			else
				valor=new BigDecimal("1");
			
			if(f.getRangoImpoDesde().compareTo(f.getRangoImpoHasta())==-1){
			
				if(this.insertaInteresFlxList.size()==0){
					InteresFlexipago bean=new InteresFlexipago();
					BeanUtils.copyProperties(bean, f);
					this.insertaInteresFlxList.add(bean);
					List list=this.insertaInteresFlxList;
					this.insertaInteresFlxModel = new DataTableModel(list);
					limpiarFlexipago();
					this.addInfo("INFO: ", "Registro Insertado Satisfactoriamente");
				}else{
					int indice=this.insertaInteresFlxList.size()-1;
					rangoHasta=this.insertaInteresFlxList.get(indice).getRangoImpoHasta();				
					resta=f.getRangoImpoDesde().subtract(rangoHasta);
					if(resta.compareTo(valor)==0){
						InteresFlexipago bean=new InteresFlexipago();
						BeanUtils.copyProperties(bean, f);
						this.insertaInteresFlxList.add(bean);
						List list=this.insertaInteresFlxList;
						this.insertaInteresFlxModel = new DataTableModel(list);
						limpiarFlexipago();
						this.addInfo("INFO: ", "Registro Insertado Satisfactoriamente");				
					}else{
						this.addError("ERROR: ", "Los Valores Rango Desde y Rango Hasta deben ser contínuos");
						List list=this.insertaInteresFlxList;
						this.insertaInteresFlxModel = new DataTableModel(list);
						return;
					}	
				}
			}else{
				this.addError("ERROR: ", "Rango Desde debe ser menor que Rango Hasta");
				return;				
			}			
		} catch (Exception e) {
			this.obtieneMensajeErrorException(e);
		}
		
	}
	
	public void guardarInteresFlx(ActionEvent actionEvent) {
		try {
			Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();		
			MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");			
			service.deleteTotalInteresesFlexipago();
			for(int i=0;i<this.insertaInteresFlxList.size();i++){
				InteresFlexipago bean= this.insertaInteresFlxList.get(i);
				service.insertInteresFlexipago(bean, usuario);
			}
			this.addInfo("INFO: ", "Registros actualizados satisfactorimente");
			this.find();
			
		} catch (Exception e) {
			this.obtieneMensajeErrorException(e);
		}
		
	}
	
	public String modificarInteres(){
		BigDecimal rangoHasta=new BigDecimal("0");
		BigDecimal rangoDesde=new BigDecimal("0");
		BigDecimal rangoDesdeSgte=new BigDecimal("0");
		BigDecimal resta=new BigDecimal("0");
		BigDecimal valor=new BigDecimal("0");
		if(this.cantDecimales==2)
			valor=new BigDecimal("0.01");
		else
			valor=new BigDecimal("1");
		
		for(int i=0;i<this.insertaInteresFlxList.size()-1;i++){			
			rangoDesde=this.insertaInteresFlxList.get(i).getRangoImpoDesde();
			rangoHasta=this.insertaInteresFlxList.get(i).getRangoImpoHasta();	
			rangoDesdeSgte=this.insertaInteresFlxList.get(i+1).getRangoImpoDesde();
			if(rangoDesde.compareTo(rangoHasta)==-1){
				resta=rangoDesdeSgte.subtract(rangoHasta);
				if(resta.compareTo(valor)!=0){
					return "Los Valores Rango Desde y Rango Hasta deben ser contínuos.";
				}				
			}else
				return "Rango Desde debe ser menor que Rango Hasta";		
			
		}
		return null;
	}
	
	public String setValidarConfirmar(String accion) {
		if (accion.equals("ELIMINAR_FLX")) {
			if (this.beanRegistroFlexipago == null)
				return this.getResourceMessage("errors.select.item");
			String mensaje=validarEliminar();			
			if(StringUtils.isNotBlank(mensaje))
				return mensaje;	
		}
		
		if (accion.equals("GUARDAR_FLX")) {
			String msje=modificarInteres();
			if(StringUtils.isNotBlank(msje))
				return msje;			
		}
		
		return null;
	}
	
	public void eliminarInteresFlx(ActionEvent actionEvent) {
		try {
			Usuario usuario=this.mPantallaPrincipalBean.getCurrentUser();
			MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
			InteresFlexipago bean=(InteresFlexipago)this.beanRegistroFlexipago;		
			service.deleteInteresesFlexipago(bean, usuario);
			this.find();
			this.addInfo("INFO: ", "Registro eliminado satisfactorimente");
		} catch (Exception e) {
			this.obtieneMensajeErrorException(e);
		}
		
	}
	
	public String validarEliminar(){
		MantenimientoFLXConsultoraService service = (MantenimientoFLXConsultoraService)getBean("spusicc.mantenimientoFLXConsultoraService");
		InteresFlexipago bean=(InteresFlexipago)this.beanRegistroFlexipago;			
		String valor=service.getRangoMaxHasta();
		BigDecimal rangoMaximo=new BigDecimal(valor);
		BigDecimal rangohasta=new BigDecimal("0");
		rangohasta=bean.getRangoImpoHasta();		
		if(rangoMaximo.compareTo(rangohasta)!=0)
			return "El registro no se puede Eliminar: Los Rangos son continuos";
		return null;
		
	}
	
	
	public String validarPeriodos(){
		MantenimientoFLXInteresFlexipagoSearchForm f = (MantenimientoFLXInteresFlexipagoSearchForm) this.formBusqueda;
		if(StringUtils.isNotBlank(f.getPeriodoFin())){
			Integer fecha1, fecha2;		
			fecha1 = Integer.parseInt(f.getPeriodoInicio());
			fecha2 = Integer.parseInt(f.getPeriodoFin());
			if (fecha1 > fecha2) {
				String mensaje = "Campaña Hasta debe ser mayor o igual a Campaña Desde";
				return mensaje;
			}
		}
		return "";
	}
	
	public void limpiarFlexipago(){
		MantenimientoFLXInteresFlexipagoSearchForm f = (MantenimientoFLXInteresFlexipagoSearchForm) this.formBusqueda;	
		f.setRangoImpoDesde(null);
		f.setRangoImpoHasta(null);
		f.setValorCosto(null);
		
	}
	
	@Override
	protected boolean setDeleteAttributes() throws Exception {				
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		return null;
	}

	/**
	 * @return the insertaInteresFlxList
	 */
	public List<InteresFlexipago> getInsertaInteresFlxList() {
		return insertaInteresFlxList;
	}

	/**
	 * @param insertaInteresFlxList the insertaInteresFlxList to set
	 */
	public void setInsertaInteresFlxList(
			List<InteresFlexipago> insertaInteresFlxList) {
		this.insertaInteresFlxList = insertaInteresFlxList;
	}

	/**
	 * @return the insertaInteresFlxModel
	 */
	public DataTableModel getInsertaInteresFlxModel() {
		return insertaInteresFlxModel;
	}

	/**
	 * @param insertaInteresFlxModel the insertaInteresFlxModel to set
	 */
	public void setInsertaInteresFlxModel(DataTableModel insertaInteresFlxModel) {
		this.insertaInteresFlxModel = insertaInteresFlxModel;
	}

	/**
	 * @return the beanRegistroFlexipago
	 */
	public Object getBeanRegistroFlexipago() {
		return beanRegistroFlexipago;
	}

	/**
	 * @param beanRegistroFlexipago the beanRegistroFlexipago to set
	 */
	public void setBeanRegistroFlexipago(Object beanRegistroFlexipago) {
		this.beanRegistroFlexipago = beanRegistroFlexipago;
	}

	/**
	 * @return the cantDecimales
	 */
	public int getCantDecimales() {
		return cantDecimales;
	}

	/**
	 * @param cantDecimales the cantDecimales to set
	 */
	public void setCantDecimales(int cantDecimales) {
		this.cantDecimales = cantDecimales;
	}
	
	

}
