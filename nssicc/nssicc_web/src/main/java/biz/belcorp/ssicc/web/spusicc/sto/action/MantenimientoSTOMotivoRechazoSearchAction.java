package biz.belcorp.ssicc.web.spusicc.sto.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.framework.model.AuditInfo;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.sto.model.MotivoRechazo;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOMotivoRechazoService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOSecuenciaValidacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOMotivoRechazoForm;
import biz.belcorp.ssicc.web.spusicc.sto.form.MantenimientoSTOMotivoRechazoSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class MantenimientoSTOMotivoRechazoSearchAction extends BaseMantenimientoSearchAbstractAction
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8173600064267533268L;
	
	private List stoDocumentoList;
	private Boolean indRechazo;
	private MotivoRechazo registroAnterior;

	@Override
	protected String getSalirForward() {
		return "mantenimientoSTOMotivoRechazoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoSTOMotivoRechazoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoSTOMotivoRechazoSearchForm busqueda = new MantenimientoSTOMotivoRechazoSearchForm(); 
		return busqueda;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		MantenimientoSTOMotivoRechazoSearchForm f = (MantenimientoSTOMotivoRechazoSearchForm) this.formBusqueda;
		MantenimientoSTOMotivoRechazoService service = (MantenimientoSTOMotivoRechazoService)getBean("spusicc.mantenimientoSTOMotivoRechazoService");
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("tipoDocumento", f.getTipoDocumento());
		
		List lista = service.getMotivoRechazoList(criteria);
		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception 
	{
		// Creamos las instancias de los objetos a usar
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		MantenimientoSTOMotivoRechazoService service = (MantenimientoSTOMotivoRechazoService)getBean("spusicc.mantenimientoSTOMotivoRechazoService");
		MotivoRechazo rechazo = (MotivoRechazo) this.beanRegistroSeleccionado;
		
		Map criteria = new HashMap();
		criteria = BeanUtils.describe(rechazo);
		service.deleteMotivoRechazo(criteria);
				
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{
		MantenimientoSTOMotivoRechazoForm f = (MantenimientoSTOMotivoRechazoForm) this.formMantenimiento;
		MantenimientoSTOMotivoRechazoService service = (MantenimientoSTOMotivoRechazoService) getBean("spusicc.mantenimientoSTOMotivoRechazoService");

		// Extreamos el usuario de la sesion
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		boolean isNew = f.isNewRecord();
		MotivoRechazo rechazoActual = new MotivoRechazo();

		if (this.indRechazo)
			f.setIndRechazo("1");
		else
			f.setIndRechazo("0");

		BeanUtils.copyProperties(rechazoActual, f);

		try {
			if (isNew) 
			{
				// insert
				// validamos que no exista un registro con la misma clave
				Map criteria = new HashMap();
				criteria.put("codigoPais", f.getCodigoPais());
				criteria.put("tipoDocumento", f.getTipoDocumento());
				criteria.put("codigoRechazo", f.getCodigoMotRechazo());
				criteria.put("codigoModulo", f.getCodigoModulo());

				MotivoRechazo registro = service.getMotivoRechazo(criteria);
				if (registro != null) {
					this.addError("", this.getResourceMessage("mantenimientoSTOMotivoRechazoForm.errorExisteRegistro"));
					return false;
				}

				service.insertMotivoRechazo(rechazoActual, usuario);
			} else 
			{
				// validamos que no exista un registro con la misma clave
				if (!(f.getCodigoPais().equals(this.registroAnterior.getCodigoPais())
						&& f.getTipoDocumento().equals(this.registroAnterior.getTipoDocumento())
						&& f.getCodigoMotRechazo().equals(this.registroAnterior.getCodigoMotRechazo()) 
						&& f.getCodigoModulo().equals(this.registroAnterior.getCodigoModulo()))) 
				{
					Map criteria = new HashMap();
					criteria.put("codigoPais", f.getCodigoPais());
					criteria.put("tipoDocumento", f.getTipoDocumento());
					criteria.put("codigoRechazo", f.getCodigoMotRechazo());
					criteria.put("codigoModulo", f.getCodigoModulo());

					MotivoRechazo registro = service.getMotivoRechazo(criteria);
					if (registro != null) {
						this.addError("", this.getResourceMessage("mantenimientoSTOMotivoRechazoForm.errorExisteRegistro"));
						return false;
					}
				}

				// update
				Map criteria = new HashMap();
				criteria = BeanUtils.describe(f);
				criteria.put("tipoDocumentoAnterior", this.registroAnterior.getTipoDocumento());
				criteria.put("codigoModuloAnterior", this.registroAnterior.getCodigoModulo());
				criteria.put("usuarioModi", usuario.getLogin());
				service.updateMotivoRechazo(criteria, usuario);
			}
		} catch (Exception e) {
			throw new Exception(this.obtieneMensajeErrorException(e));
		}

		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		MantenimientoSTOMotivoRechazoForm f = new MantenimientoSTOMotivoRechazoForm();
		MantenimientoSTOMotivoRechazoService service = (MantenimientoSTOMotivoRechazoService)getBean("spusicc.mantenimientoSTOMotivoRechazoService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		f.setIndRechazo("0");
		this.indRechazo = false;
		
		
		if(!this.accion.equals(this.ACCION_NUEVO))
		{
			MotivoRechazo rechazo = (MotivoRechazo) this.beanRegistroSeleccionado;
			
			Map criteria = new HashMap();
			criteria.put("codigoPais", f.getCodigoPais());
			criteria.put("tipoDocumento", rechazo.getTipoDocumento());
			criteria.put("codigoRechazo", rechazo.getCodigoMotRechazo());
			criteria.put("codigoModulo", rechazo.getCodigoModulo());
						
			this.registroAnterior = new MotivoRechazo();
			this.registroAnterior = service.getMotivoRechazo(criteria);
			
			BeanUtils.copyProperties(f, this.registroAnterior);			
			
			
			if(StringUtils.isNotBlank(f.getIndRechazo()) && f.getIndRechazo().equals("1"))
				this.indRechazo = true;
			else
				this.indRechazo = false;
			
			f.setNewRecord(false);
		}
		
		return f;
	}

	@Override
	protected void setViewAtributes() throws Exception 
	{
		this.mostrarBotonConsultar = false;
		
		MantenimientoSTOMotivoRechazoSearchForm f = (MantenimientoSTOMotivoRechazoSearchForm) this.formBusqueda;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map params = new HashMap();
		MantenimientoSTOSecuenciaValidacionService service = (MantenimientoSTOSecuenciaValidacionService) getBean("sto.mantenimientoSTOSecuenciaValidacionService");
		
		f.setCodigoPais(pais.getCodigo());	
		
		params.put("codigoPais", f.getCodigoPais());
		
		List tiposDocumento = (List)service.getTipoDocumentoList(params);
		this.stoDocumentoList = tiposDocumento;		
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() 
	{
		MantenimientoSTOMotivoRechazoForm f = (MantenimientoSTOMotivoRechazoForm)this.formMantenimiento;
		
		if(f.isNewRecord())
			return "mantenimientoSTOMotivoRechazoForm.insertRegistro";
		else
			return "mantenimientoSTOMotivoRechazoForm.updateRegistro";
	}

	public List getStoDocumentoList() {
		return stoDocumentoList;
	}

	public void setStoDocumentoList(List stoDocumentoList) {
		this.stoDocumentoList = stoDocumentoList;
	}

	public Boolean getIndRechazo() {
		return indRechazo;
	}

	public void setIndRechazo(Boolean indRechazo) {
		this.indRechazo = indRechazo;
	}

	public MotivoRechazo getRegistroAnterior() {
		return registroAnterior;
	}

	public void setRegistroAnterior(MotivoRechazo registroAnterior) {
		this.registroAnterior = registroAnterior;
	}
	
}
