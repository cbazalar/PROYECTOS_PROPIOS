package biz.belcorp.ssicc.web.spusicc.flexipago.action;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.flexipago.model.RangoLDC;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.spusicc.flexipago.MantenimientoFLXModeloOtorgamientoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.MantenimientoFLXRangosLDCForm;
import biz.belcorp.ssicc.web.spusicc.flexipago.form.RangoLDCForm;

@SuppressWarnings({"rawtypes","unchecked"})
@ManagedBean
@SessionScoped
public class MantenimientoFLXRangosLDCAction extends BaseMantenimientoSearchAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5599754242241995055L;
	
	private List rangoLDCList;
	private DataTableModel tableModel;
	private RangoLDCForm seleccionTabla;
	
	@Override
	protected String getSalirForward() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		
		MantenimientoFLXRangosLDCForm f = new MantenimientoFLXRangosLDCForm();
		return f;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		// TODO Auto-generated method stub
		
		this.mostrarBotonNuevo = false;
		this.mostrarBotonConsultar = false;
		this.mostrarBotonEliminar = false;
		this.mostrarBotonModificar = false;
		this.mostrarListaBusqueda = false;
		this.mostrarBotonBuscar = false;
		
		MantenimientoFLXRangosLDCForm f = (MantenimientoFLXRangosLDCForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		f.setSelectedItems(null);
		
		List rangos = service.getRangosLDC();
		this.rangoLDCList = new ArrayList(); 
		
		RangoLDCForm rangosLDCForm[] = new RangoLDCForm[rangos.size()]; 
		
		try {
			for (int i = 0; i < rangos.size(); i++) {
				rangosLDCForm[i] = new RangoLDCForm();
				BeanUtils.copyProperties(rangosLDCForm[i], rangos.get(i));
				this.rangoLDCList.add(rangosLDCForm[i]); 
			}
			f.setRangosLDCForm(rangosLDCForm);			
			this.tableModel = new DataTableModel(this.rangoLDCList);	
			
		} catch (Exception e) {
			log.error(e);
		}
		this.seleccionTabla = new RangoLDCForm();
	}
	
	/**
	 * Guarda la grilla
	 * 
	 * @param event
	 */
	public void guardarCambiosRango(ActionEvent event){
		
		try{
		MantenimientoFLXRangosLDCForm f = (MantenimientoFLXRangosLDCForm) this.formBusqueda;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		//esta para arreglar
		List rangos = new ArrayList();
		
		 
		
		for (int i = 0; i < this.rangoLDCList.size(); i++) {
			RangoLDCForm rangoLDCForm = (RangoLDCForm) this.rangoLDCList.get(i);
			
			if(StringUtils.isBlank(rangoLDCForm.getNumeroSegmento())){
				this.addWarn("Advertencia: ", this.getResourceMessage("mantenimientoFLXRangosLDCForm.requerido.grilla.numero.segmento"));
				this.getRequestContext().execute("PrimeFaces.focus('dataGrilla:"+(i)+":numSegmento')");
				return;
			}
			
			if(StringUtils.isBlank(rangoLDCForm.getValorDesde())){
				this.addWarn("Advertencia: ", this.getResourceMessage("mantenimientoFLXRangosLDCForm.requerido.grilla.desde"));
				this.getRequestContext().execute("PrimeFaces.focus('dataGrilla:"+(i)+":valDes')");
				return;
			}
			
			if(StringUtils.isBlank(rangoLDCForm.getValorHasta())){
				this.addWarn("Advertencia: ", this.getResourceMessage("mantenimientoFLXRangosLDCForm.requerido.grilla.hasta"));
				this.getRequestContext().execute("PrimeFaces.focus('dataGrilla:"+(i)+":valHas')");
				return;
			}
			
			if(StringUtils.isBlank(rangoLDCForm.getValorFactor())){
				this.addWarn("Advertencia: ", this.getResourceMessage("mantenimientoFLXRangosLDCForm.requerido.grilla.factor"));
				this.getRequestContext().execute("PrimeFaces.focus('dataGrilla:"+(i)+":valFact')");
				return;
			}
			
		}
		
		
		for (int i = 0; i < this.rangoLDCList.size(); i++) {
			RangoLDC rango = new RangoLDC();
			BeanUtils.copyProperties(rango, this.rangoLDCList.get(i));
			rangos.add(rango);
		}
		
		service.updateRangosLDC(rangos, usuario);
		addInfo("Mensaje: ", getResourceMessage("mantenimientoFLXRangosLDCForm.updated"));
		this.setViewAtributes();
		}
		catch (Exception e)
		{
			log.error(e);
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
		
	}
	
	/**
	 * Inserta una fila
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * 
	 * @throws Exception
	 */
	public void agregarRango(ActionEvent event) throws Exception 
	{
		try {
			
			Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
			
			MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
			MantenimientoFLXRangosLDCForm f = (MantenimientoFLXRangosLDCForm) this.formBusqueda;
			
			if(StringUtils.isBlank(f.getNumeroSegmento()))
				{	addInfo("Mensaje", "Por favor ingrese el numero de segmento");
					return;
				}
			
			if(StringUtils.isBlank(f.getValorDesde()))
				{	addInfo("Mensaje", "Por favor ingrese el valor desde");
					return;
				}
			
			if(StringUtils.isBlank(f.getValorHasta()))
			{	addInfo("Mensaje", "Por favor ingrese el valor hasta");
				return;
			}
			
			if(StringUtils.isBlank(f.getValorFactor()))
			{	addInfo("Mensaje", "Por favor ingrese el valor del factor");
				return;
			}
			
			
			RangoLDC rango = new RangoLDC();
			
			BeanUtils.copyProperties(rango, f);		

			
			//Insertamos en BD
			service.insertRangoLDC(rango, usuario);
			//

			f.setNumeroSegmento("");
			f.setValorDesde("");
			f.setValorHasta("");
			f.setValorFactor("");
			
			addInfo("Mensaje: ", getResourceMessage("mantenimientoFLXRangosLDCForm.rango.agregado"));
			this.setViewAtributes();
			
		} catch (InvalidIdentifierException iie) {
			this.addError("Error: ", this.getResourceMessage("mantenimientoFLXRangosLDCForm.error.rango.existe"));
			
		} 	
	}
	
	/**
	 * Elimina fila
	 * 
	 * @param event
	 */
	public void eliminarRango(ActionEvent event) 
	{

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		MantenimientoFLXRangosLDCForm f = (MantenimientoFLXRangosLDCForm) this.formBusqueda;
		
		try{
		if(this.seleccionTabla != null)
		{
			String oid =  this.seleccionTabla.getOid();
			String[] seleccionados = new String[]{oid};
			
			String oidRango = seleccionados[0];
			
			if(StringUtils.isNotBlank(oidRango))
			{
				if(log.isDebugEnabled())
					log.debug("oidRango: " + oidRango);
								
				service.deleteRangoLDC(oidRango, usuario);			
				this.setViewAtributes();
			}
			
			addInfo("Mensaje: ", getResourceMessage("mantenimientoFLXRangosLDCForm.rango.eliminado"));
		}			
		else{
			addWarn("Mensaje: ", this.getResourceMessage("errors.select.item"));
			return;
		}
		
		}
		catch(Exception e)
		{
			log.error(e);
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	
	
	
	/**
	 * Calcula el valor segmento de la grilla
	 * 
	 */
	public void calcularValorSegmento()
	{
		MantenimientoFLXRangosLDCForm f = (MantenimientoFLXRangosLDCForm) this.formBusqueda;
		MantenimientoFLXModeloOtorgamientoService service = (MantenimientoFLXModeloOtorgamientoService)getBean("spusicc.mantenimientoFLXModeloOtorgamientoService");
		try{
			
			
		String value = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("index");
		int indice = Integer.parseInt(value);
		RangoLDCForm rangosLDCForm = (RangoLDCForm) this.rangoLDCList.get(indice);	
		
		String valorDesde = rangosLDCForm.getValorDesde();
		String valorHasta = rangosLDCForm.getValorHasta();
		String valorFactor = rangosLDCForm.getValorFactor();
		
		BigDecimal valorDesdeFloat = new BigDecimal(0);
		BigDecimal valorHastaFloat =  new BigDecimal(0);
		BigDecimal valorFactorFloat =  new BigDecimal(0);
		BigDecimal valorSegmento = new BigDecimal(0);
		
		
		if(!StringUtils.isBlank(valorDesde))
			valorDesdeFloat = new BigDecimal(valorDesde);
			
		if(!StringUtils.isBlank(valorHasta))
			valorHastaFloat = new BigDecimal(valorHasta);
		
		if(!StringUtils.isBlank(valorFactor))
			valorFactorFloat = new BigDecimal(valorFactor); 
		
		valorSegmento = (valorHastaFloat.subtract(valorDesdeFloat)).multiply(valorFactorFloat).setScale(6, BigDecimal.ROUND_HALF_UP); 
		
		RangoLDCForm rangoSeteado = new RangoLDCForm();
		rangosLDCForm.setValorSegmento(String.valueOf(valorSegmento));
		BeanUtils.copyProperties(rangoSeteado, rangosLDCForm);
		this.rangoLDCList.remove(indice);
		this.rangoLDCList.add(indice, rangoSeteado);
		this.tableModel = new DataTableModel(this.rangoLDCList);
					
		}
		catch(Exception e)
		{
			log.error(e);
		}
		
	}

	public List getRangoLDCList() {
		return rangoLDCList;
	}

	public void setRangoLDCList(List rangoLDCList) {
		this.rangoLDCList = rangoLDCList;
	}

	public DataTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DataTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public RangoLDCForm getSeleccionTabla() {
		return seleccionTabla;
	}

	public void setSeleccionTabla(RangoLDCForm seleccionTabla) {
		this.seleccionTabla = seleccionTabla;
	}
	
}
