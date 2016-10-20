package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.MotivoDevolucion;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.MotivoDevolucionDet;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECMotivoDevolucionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECMotivoDevolucionForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECMotivoDevolucionSearchForm;

@ManagedBean
@SessionScoped
public class MantenimientoRECMotivoDevolucionSearchAction extends BaseMantenimientoSearchAbstractAction{

	private static final long serialVersionUID = -8521842414925831834L;	
	
	private boolean indModificar=false;
	private boolean indCalidad=false;

	@Override
	protected String getSalirForward() {		
		return "mantenimientoRECMotivoDevolucionSearchForm";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {		
		return "mantenimientoRECMotivoDevolucionForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECMotivoDevolucionSearchForm searchForm = new MantenimientoRECMotivoDevolucionSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		MantenimientoRECMotivoDevolucionSearchForm f = (MantenimientoRECMotivoDevolucionSearchForm) this.formBusqueda;		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECMotivoDevolucionService mantenimientoRECMotivoDevolucionService = 
				(MantenimientoRECMotivoDevolucionService) getBean("spusicc.mantenimientoRECMotivoDevolucionService");
		f.setCodigoPais(pais.getCodigo());
		Map map = BeanUtils.describe(f);		
		map.put("idioma", pais.getCodigoIdiomaIso());
		// lleno la lista para el tipo de operacion
		List lista = mantenimientoRECMotivoDevolucionService.getMovitoDevolucionList(map);				
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {		
		MantenimientoRECMotivoDevolucionSearchForm f = (MantenimientoRECMotivoDevolucionSearchForm) this.formBusqueda;
		MantenimientoRECMotivoDevolucionService service = (MantenimientoRECMotivoDevolucionService) getBean("spusicc.mantenimientoRECMotivoDevolucionService");
		MotivoDevolucion motivo = new MotivoDevolucion();	
		BeanUtils.copyProperties(motivo, f); //copiar
		
		MotivoDevolucionDet sistemaBusqueda= (MotivoDevolucionDet)this.beanRegistroSeleccionado;			
		String id=sistemaBusqueda.getIdMotivoDevolucion();		
		MotivoDevolucion motivoNuevo =service.getMovitoDevolucion(id);
		
		if(StringUtils.isEmpty(motivoNuevo.getIndRegMod()) && !StringUtils.equals(motivoNuevo.getIndRegMod(), Constants.NUMERO_UNO)){			
			service.deleteMotivoDevolucion(id);			
		}else{
			throw new Exception(this.getResourceMessage("mantenimientoRECMotivoDevolucionSearchForm.deleted.imposible"));			
		}
		return true;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {
		MantenimientoRECMotivoDevolucionForm f = (MantenimientoRECMotivoDevolucionForm) this.formMantenimiento;
		MantenimientoRECMotivoDevolucionService service = (MantenimientoRECMotivoDevolucionService) getBean("spusicc.mantenimientoRECMotivoDevolucionService");

		Pais pais =this.mPantallaPrincipalBean.getCurrentCountry();
		MotivoDevolucion motivo = new MotivoDevolucion();
		if(this.isIndModificar())			
			f.setIndRegMod(Constants.NUMERO_UNO);
		else
			f.setIndRegMod(Constants.NUMERO_CERO);
		if(this.isIndCalidad())
			f.setIndicadorCalidad(Constants.NUMERO_UNO);
		else
			f.setIndicadorCalidad(Constants.NUMERO_CERO);
		
		if(f.getIndicadorTipoDevolucion()!=null && StringUtils.equals(f.getIndicadorTipoDevolucion(), "true"))
			f.setIndicadorTipoDevolucion(Constants.NUMERO_UNO);
		else
			f.setIndicadorTipoDevolucion(Constants.NUMERO_CERO);
		
		BeanUtils.copyProperties(motivo, f); // copiar
		motivo.setCodigoPais(pais.getCodigo());
		motivo.setIdioma(pais.getCodigoIdiomaIso());
		if (f.isNewRecord()) {
			try {
				// insertar
				service.insertMotivoDevolucion(motivo);
			} catch (Exception e) {				
				throw new Exception(this.getResourceMessage("mantenimientoRECMotivoDevolucionForm.existe"));
			}
		} else {
			// actualizar
			service.updateMotivoDevolucion(motivo);			
			motivo.setIndicadorCalidad(f.getIndicadorCalidad());			
			service.updateMotivoDevolucionIndicador(motivo);
		}
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		MantenimientoRECMotivoDevolucionForm f = new MantenimientoRECMotivoDevolucionForm();
		this.indModificar=false;
		this.indCalidad=false;
		if (!this.accion.equals(this.ACCION_NUEVO)) {
			MotivoDevolucionDet sistemaBusqueda= (MotivoDevolucionDet)this.beanRegistroSeleccionado;			
			String codigo=sistemaBusqueda.getIdMotivoDevolucion();
			String indDevolucion=sistemaBusqueda.getIndicadorDevolucion();
			
			String codigoPais=pais.getCodigo();
			if (codigo != null && codigoPais != null) {
				if (log.isDebugEnabled()) {
					log.debug("Id seleccionado de la lista: " + codigo + " "+ codigoPais);
				}				
				MantenimientoRECMotivoDevolucionService service = (MantenimientoRECMotivoDevolucionService) getBean("spusicc.mantenimientoRECMotivoDevolucionService");
						
				MotivoDevolucion motivo = service.getMovitoDevolucion(codigo);
				
				if(StringUtils.isNotBlank(motivo.getIndicadorCalidad())){
					if(StringUtils.equals(motivo.getIndicadorCalidad(),"C") ||StringUtils.equals(motivo.getIndicadorCalidad(),"1"))
						motivo.setIndicadorCalidad(Constants.NUMERO_UNO);
				}
				if(StringUtils.isBlank(motivo.getIndicadorCalidad())||(StringUtils.equals(motivo.getIndicadorCalidad(),"1")==false))
				{
					motivo.setIndicadorCalidad(Constants.NUMERO_CERO);
				}
				if(StringUtils.isBlank(motivo.getIndRegMod())|| StringUtils.equals(motivo.getIndRegMod(),"C"))					
					motivo.setIndRegMod(Constants.NUMERO_CERO);				
								
				BeanUtils.copyProperties(f, motivo);
				f.setCodigoPais(codigoPais);
				f.setNewRecord(false);				
				
				if(StringUtils.equals(f.getIndRegMod(),Constants.NUMERO_UNO))
					this.indModificar=true;
				else
					this.indModificar=false;
				if(StringUtils.equals(indDevolucion,"C"))
					this.indCalidad=true;
				else
					this.indCalidad=false;
				
				if(f.getIndicadorTipoDevolucion()!=null && StringUtils.equals(f.getIndicadorTipoDevolucion(), Constants.NUMERO_UNO))
					f.setIndicadorTipoDevolucion("true");
				else
					f.setIndicadorTipoDevolucion("false");
			}
		}		
		return f;
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		MantenimientoRECMotivoDevolucionForm f = (MantenimientoRECMotivoDevolucionForm) this.formMantenimiento;
		if(f.isNewRecord())
			return "mantenimientoRECMotivoDevolucionForm.cabecera.insert";
		else
			return "mantenimientoRECMotivoDevolucionForm.update";
	}	


	@Override
	protected void setViewAtributes() throws Exception {		
		this.mostrarBotonConsultar=false;		
	}
	
	public boolean isIndModificar() {
		return indModificar;
	}

	public void setIndModificar(boolean indModificar) {
		this.indModificar = indModificar;
	}

	public boolean isIndCalidad() {
		return indCalidad;
	}

	public void setIndCalidad(boolean indCalidad) {
		this.indCalidad = indCalidad;
	}

	public void mostrarCampos(ValueChangeEvent a){
		MantenimientoRECMotivoDevolucionForm f = (MantenimientoRECMotivoDevolucionForm) this.formMantenimiento;
		f.setCodMotiDevo((String)a.getNewValue());
	}	
}
