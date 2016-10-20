/**
 * 
 */
package biz.belcorp.ssicc.web.spusicc.reclamos.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.aspectj.apache.bcel.classfile.Constant;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.OperacionReclamo;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.TipoOperacion;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.reclamos.MantenimientoRECOperacionesReclamoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECOperacionesReclamoForm;
import biz.belcorp.ssicc.web.spusicc.reclamos.form.MantenimientoRECOperacionesReclamoSearchForm;

/**
 * @author Sigcomt
 *
 */
@ManagedBean
@SessionScoped
public class MantenimientoRECOperacionesReclamoSearchAction extends BaseMantenimientoSearchAbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1896616653029952985L;
	
	private List siccOperacionesList = new ArrayList();
	private List recOperacionesReclamoList = new ArrayList();
	private String indicadorSi = Constants.REC_SI;
	private String indicadorNo = Constants.REC_NO;
	private String indicadorUno = Constants.NUMERO_UNO;
	private String indicadorCero = Constants.NUMERO_CERO;
	private String numero_uno = Constants.NUMERO_UNO;
	private String numero_dos = Constants.NUMERO_DOS;
	private String tipoMatrizPrecios = Constants.MATRIZ_PRECIOS;
	private String tipoMatrizPremios = Constants.MATRIZ_PREMIOS;
	private String tipoFactura = Constants.FACTURA;
	private String tipoCatalogo = Constants.CATALOGO;
	private List recMotivosBloqueoList = new ArrayList();
	private boolean boolIndAnulacion;
	private boolean boolIndDevFisicoFact;
	private boolean boolIndFaltanteMercaderia;
	private boolean boolIndEnvia;
	private boolean boolIndEnviaGeneraDev;
	private boolean boolIndDevuelve;
	private List recTiposSolicitudGeneraList = new ArrayList();
	private List recAlmacenList = new ArrayList();
	private List recMovimientoAlmacenList = new ArrayList();
	private boolean boolIndDevuelveGeneraEnv;
	private List recMotivosRechazoDesbloqueoList = new ArrayList();
	private List recTiposSolicitudList = new ArrayList();
	private boolean boolIndCampReferenciaUnica;
	private boolean boolIndInfoBelcorpNoticias;
	private boolean boolIndDevuelveEstaFactura;
	private boolean boolIndEnviaEstaFactura;
	
	
	private List recTiposOperacionesList = new ArrayList();
	private DataTableModel listaModelRecTiposOperacionesList = new DataTableModel();
	private TipoOperacion columnaSeleccionada = new TipoOperacion();
	
	@Override
	protected String getSalirForward() {
		return "mantenimientoRECOperacionesReclamoList.xhtml";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception {
		return "mantenimientoRECOperacionesReclamoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception {
		MantenimientoRECOperacionesReclamoSearchForm form = new MantenimientoRECOperacionesReclamoSearchForm();
		return form;
	}

	@Override
	protected List setFindAttributes() throws Exception {
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		MantenimientoRECOperacionesReclamoSearchForm f = (MantenimientoRECOperacionesReclamoSearchForm) this.formBusqueda;
		MantenimientoRECOperacionesReclamoService service = (MantenimientoRECOperacionesReclamoService) getBean("spusicc.mantenimientoRECOperacionesReclamoService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		//Map map = BeanUtils.describe(f);
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoOperacion", (f.getCodigoOperacion() == null) ? new ArrayList(): Arrays.asList(f.getCodigoOperacion()));
		
		List listOp = interfazSiCCService.getOperacionesByCodigoPais(criteria);
		
		this.siccOperacionesList = null;
		
		this.siccOperacionesList = listOp;
		
		List list = service.getOperacionesReclamoList(criteria);

		this.recOperacionesReclamoList = list;
		
		return list;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		MantenimientoRECOperacionesReclamoService service = (MantenimientoRECOperacionesReclamoService) getBean("spusicc.mantenimientoRECOperacionesReclamoService");

		Map seleccionado = (HashMap) this.beanRegistroSeleccionado;
		String id = MapUtils.getString(seleccionado, "oidOperacionReclamo");	
		log.debug("ID Operacion Reclamo: "+ id);
		
		Map criteria = new HashMap();
		criteria.put("oidOperacionReclamo", id);
				
		service.deleteTipoOperacion(criteria);
		service.deleteOperacionReclamo(id);	
		
		return true;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#devuelveMensajeKeyEliminarOK()
	 */
	@Override
	protected String devuelveMensajeKeyEliminarOK() {
		return "mantenimientoRECOperacionesReclamoForm.deleted";
	}

	@Override
	protected boolean setSaveAttributes() throws Exception {

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		OperacionReclamo or = new OperacionReclamo();
		MantenimientoRECOperacionesReclamoForm f = (MantenimientoRECOperacionesReclamoForm) this.formMantenimiento;
		MantenimientoRECOperacionesReclamoService service = (MantenimientoRECOperacionesReclamoService) getBean("spusicc.mantenimientoRECOperacionesReclamoService");

		if(this.boolIndAnulacion == true)
			f.setIndAnulacion(Constants.NUMERO_UNO);	
		else
			f.setIndAnulacion(Constants.NUMERO_CERO);			
		
		if(this.boolIndDevFisicoFact == true)
			f.setIndDevFisicoFact(Constants.NUMERO_UNO);
		else
			f.setIndDevFisicoFact(Constants.NUMERO_CERO);
		
		if(this.boolIndFaltanteMercaderia == true)
			f.setIndFaltanteMercaderia(Constants.NUMERO_UNO);
		else
			f.setIndFaltanteMercaderia(Constants.NUMERO_CERO);			
		
		if(this.boolIndEnvia == true)
			f.setIndEnvia(Constants.NUMERO_UNO);
		else
			f.setIndEnvia(Constants.NUMERO_CERO);
					
		if(this.boolIndEnviaGeneraDev)
			f.setIndEnviaGeneraDev(Constants.NUMERO_UNO);
		else
			f.setIndEnviaGeneraDev(Constants.NUMERO_CERO);
		
		if(this.boolIndDevuelve == true)
			f.setIndDevuelve(Constants.NUMERO_UNO);
		else
			f.setIndDevuelve(Constants.NUMERO_CERO);
					
		if(this.boolIndDevuelveGeneraEnv == true)
			f.setIndDevuelveGeneraEnv(Constants.NUMERO_UNO);
		else
			f.setIndDevuelveGeneraEnv(Constants.NUMERO_CERO);
		
		BeanUtils.copyProperties(or, f);
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoOperacion", f.getCodigoOperacion());
		
		int existeOperacionReclamo = 0;
		int nextOidOperacionReclamo = 0;
		int existeTipoOperacion = 0;
		int nextOidTipoOperacion = 0;
		List listTipoOperaciones = this.recTiposOperacionesList;
		
		if (f.isNewRecord()) {
		
				nextOidOperacionReclamo = service.getNextOidOperacionReclamo(criteria);
				or.setOidOperacionReclamo(Integer.toString(nextOidOperacionReclamo));
				
				service.insertOperacionReclamo(or);
				
				Iterator it = listTipoOperaciones.iterator();
				while(it.hasNext()){
//					Map hmap =(Map)it.next();
					TipoOperacion tipoOperacion = (TipoOperacion)it.next();
//					BeanUtils.copyProperties(tipoOperacion, hmap);

//					String indicadorAccion = (String)hmap.get("indicadorAccion");
//					String indicadorModificacion = (String)hmap.get("indicadorModificacion");
					String indicadorAccion = tipoOperacion.getIndicadorAccion();
					//hmap.put("codigoPoliza", codigoPoliza);
					
					if(Constants.NUMERO_CERO.equals(indicadorAccion)){
						nextOidTipoOperacion = service.getNextOidTipoOperacion(criteria);
					
						tipoOperacion.setOidTipoOperacion(String.valueOf(nextOidTipoOperacion));
						tipoOperacion.setOidOperacionReclamo(String.valueOf(nextOidOperacionReclamo));
						
						service.insertTipoOperacion(tipoOperacion);
					}
				}

		}else {
			service.updateOperacionReclamo(or);
			
			Iterator it = listTipoOperaciones.iterator();
			while(it.hasNext()){
//				Map hmap =(Map)it.next();
				TipoOperacion tipoOperacion = (TipoOperacion)it.next();
//				BeanUtils.copyProperties(tipoOperacion, hmap);
				
//				String indicadorAccion = (String)hmap.get("indicadorAccion");
//				String indicadorModificacion = (String)hmap.get("indicadorModificacion");
				String indicadorAccion = tipoOperacion.getIndicadorAccion();
				//hmap.put("codigoPoliza", codigoPoliza);
				
				if(Constants.NUMERO_CERO.equals(indicadorAccion)){
					Map criteriaExisteTO = new HashMap();
					criteriaExisteTO.put("oidOperacionReclamo", f.getOidOperacionReclamo());
					criteriaExisteTO.put("codigoTipoOperacion", tipoOperacion.getCodigoTipoOperacion());
					
					existeTipoOperacion = service.getExisteTipoOperacionByCodigo(criteriaExisteTO);
					
					if(existeTipoOperacion == 0){
						nextOidTipoOperacion = service.getNextOidTipoOperacion(criteria);
						tipoOperacion.setOidTipoOperacion(String.valueOf(nextOidTipoOperacion));
						
						service.insertTipoOperacion(tipoOperacion);
					}
				}
				
				if(Constants.NUMERO_DOS.equals(indicadorAccion)){
					criteria.put("oidOperacionReclamo", f.getOidOperacionReclamo());
					criteria.put("oidTipoOperacion", tipoOperacion.getOidTipoOperacion());
					
					service.deleteTipoOperacion(criteria);
				}
			}
		}	
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#devuelveMensajeKeySaveOK()
	 */
	@Override
	protected String devuelveMensajeKeySaveOK() {
		String mensaje = null;		
		MantenimientoRECOperacionesReclamoForm f = (MantenimientoRECOperacionesReclamoForm) this.formMantenimiento;
		if(f.isNewRecord()){
			mensaje = "mantenimientoRECOperacionesReclamoForm.insert";
		}else{
			mensaje = "mantenimientoRECOperacionesReclamoForm.update";
		}
		
		return mensaje;

		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setValidarMantenimiento()
	 */
	@Override
	public String setValidarMantenimiento() {

		String mensaje = null;		
		MantenimientoRECOperacionesReclamoForm f = (MantenimientoRECOperacionesReclamoForm) this.formMantenimiento;
		MantenimientoRECOperacionesReclamoService service = (MantenimientoRECOperacionesReclamoService) getBean("spusicc.mantenimientoRECOperacionesReclamoService");
		
		int existeOperacionReclamo = 0;
		List listTipoOperaciones = this.recTiposOperacionesList;
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoOperacion", f.getCodigoOperacion());
		
		existeOperacionReclamo = service.getExisteOperacionReclamoByCodigo(criteria);
		
		if(listTipoOperaciones.size() <= 0 || listTipoOperaciones == null){
			f.setIndicadorNuevoTipoOperacion(Constants.NUMERO_UNO);
			mensaje = this.getResourceMessage("mantenimientoRECOperacionesReclamoForm.tipoOperacion.vacia");
			return mensaje;
		}		
		
		if(f.isNewRecord()){
			if(existeOperacionReclamo == 1){
				f.setIndicadorNuevoTipoOperacion(Constants.NUMERO_UNO);
				mensaje = this.getResourceMessage("mantenimientoRECOperacionesReclamoForm.existe.operacionReclamo");
				return mensaje;
			}
		}
		
		return mensaje;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction#setSaveFindAttributes()
	 */
	@Override
	public void setSaveFindAfterAttributes() throws Exception {

		this.beanRegistroSeleccionado = new ArrayList();
		
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception {
		
		MantenimientoRECOperacionesReclamoForm form = new MantenimientoRECOperacionesReclamoForm();
		MantenimientoRECOperacionesReclamoService service = (MantenimientoRECOperacionesReclamoService) getBean("spusicc.mantenimientoRECOperacionesReclamoService");
		
		form.setOidOperacionReclamo("");
		form.setCodigoOperacion("");
		form.setDescripcion("");
		form.setIndAnulacion(Constants.NUMERO_CERO);
		this.boolIndAnulacion = false;
		form.setIndDevFisicoFact(Constants.NUMERO_CERO);
		this.boolIndDevFisicoFact = false;
		form.setNroCampHistoria("");
		form.setTipoPrecio("");
		form.setTipoPrecioEnvia("");
		form.setCodigoMotivoBloqueo("");
		form.setIndFaltanteMercaderia(Constants.NUMERO_CERO);
		this.boolIndFaltanteMercaderia = false;
		form.setIndEnvia(Constants.NUMERO_CERO);
		this.boolIndEnvia = false;
		form.setIndEnviaGeneraDev(Constants.NUMERO_CERO);
		this.boolIndEnviaGeneraDev = false;
		form.setIndDevuelve(Constants.NUMERO_CERO);
		this.boolIndDevuelve = false;
		form.setIndDevuelveGeneraEnv(Constants.NUMERO_CERO);
		this.boolIndDevuelveGeneraEnv = false;
		form.setCodigoMovimientoAlmacen("");
		form.setCodigoTipoOperacion("");
		form.setIndCampReferenciaUnica(Constants.NUMERO_CERO);
		this.boolIndCampReferenciaUnica = false;
		form.setNumDiasHaciaAtras("");
		form.setIndInfoBelcorpNoticias(Constants.NUMERO_CERO);
		this.boolIndInfoBelcorpNoticias = false;
		form.setIndDevuelveEstaFactura(Constants.NUMERO_CERO);
		this.boolIndDevuelveEstaFactura = false;
		form.setIndEnviaEstaFactura(Constants.NUMERO_CERO);
		this.boolIndEnviaEstaFactura = false;
		form.setCodigoMotivoRechazo("");
		form.setIndicadorNuevoTipoOperacion("");
		form.setTipoSolicitud("");
		form.setTipoSolicitudGenera("");
		form.setAlmacen("");
		form.setNewRecord(true);

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		
		List listMotivosBloqueo = service.getMotivosBloqueoList(criteria);
		List listMovimientosAlmacen = service.getMovimientosAlmacenList(criteria);
		List listMotivosRechazoDesbloqueo = service.getMotivosRechazoDesbloqueoList(criteria);
		List listTiposSolicitud = service.getTiposSolicitudList();
		List listTiposSolicitudGenera = service.getTiposSolicitudGeneraList();
		List listAlmacen = service.getAlmacenList(criteria);
		
		form.setIndicadorNuevoTipoOperacion(Constants.NUMERO_UNO);
	
		this.recMotivosBloqueoList = listMotivosBloqueo;
		this.recMovimientoAlmacenList = listMovimientosAlmacen;
		this.recMotivosRechazoDesbloqueoList = listMotivosRechazoDesbloqueo;
		this.recTiposSolicitudList = listTiposSolicitud;
		this.recTiposSolicitudGeneraList = listTiposSolicitudGenera;
		this.recAlmacenList = listAlmacen;
		
		//lista de la segunda pantalla
		this.recTiposOperacionesList = new ArrayList();
		this.listaModelRecTiposOperacionesList = null;
		
		if(StringUtils.equals(this.accion, this.ACCION_MODIFICAR)){
			form.setNewRecord(false);
			Map seleccionado = (HashMap)this.beanRegistroSeleccionado;
			String id = MapUtils.getString(seleccionado, "oidOperacionReclamo");
			log.debug("ID Operacion Reclamo: " + id);

			if(StringUtils.isNotBlank(id)){
				Map criteriaEdit = new HashMap();
				criteriaEdit.put("oidOperacionReclamo", id);
				OperacionReclamo or = service.getOperacionReclamo(criteriaEdit);
				
				if(StringUtils.isBlank(or.getIndAnulacion()) || StringUtils.equals(or.getIndAnulacion(), Constants.NUMERO_CERO)){
					or.setIndAnulacion(Constants.NUMERO_CERO);
					this.boolIndAnulacion = false;
				}else{
					or.setIndAnulacion(Constants.NUMERO_UNO);
					this.boolIndAnulacion = true;
				}
				
				if(StringUtils.isBlank(or.getIndDevFisicoFact()) || StringUtils.equals(or.getIndDevFisicoFact(), Constants.NUMERO_CERO)){
					or.setIndDevFisicoFact(Constants.NUMERO_CERO);
					this.boolIndDevFisicoFact = false;
				}else{
					or.setIndDevFisicoFact(Constants.NUMERO_UNO);
					this.boolIndDevFisicoFact = true;
				}
				
				if(StringUtils.isBlank(or.getIndFaltanteMercaderia()) || StringUtils.equals(or.getIndFaltanteMercaderia(), Constants.NUMERO_CERO)){
					or.setIndFaltanteMercaderia(Constants.NUMERO_CERO);
					this.boolIndFaltanteMercaderia = false;
				}else{
					or.setIndFaltanteMercaderia(Constants.NUMERO_UNO);
					this.boolIndFaltanteMercaderia = true;
				}
				
				if(StringUtils.isBlank(or.getIndEnvia()) || StringUtils.equals(or.getIndEnvia(), Constants.NUMERO_CERO)){
					or.setIndEnvia(Constants.NUMERO_CERO);
					this.boolIndEnvia = false;
				}else{
					or.setIndEnvia(Constants.NUMERO_UNO);
					this.boolIndEnvia = true;
				}
				
				if(StringUtils.isBlank(or.getIndEnviaGeneraDev()) || StringUtils.equals(or.getIndEnviaGeneraDev(),  Constants.NUMERO_CERO)){
					or.setIndEnviaGeneraDev(Constants.NUMERO_CERO);
					this.boolIndEnviaGeneraDev = false;
				}else{
					or.setIndEnviaGeneraDev(Constants.NUMERO_UNO);
					this.boolIndEnviaGeneraDev = true;
				}
				
				if(StringUtils.isBlank(or.getIndDevuelve()) || StringUtils.equals(or.getIndDevuelve(), Constants.NUMERO_CERO)){
					or.setIndDevuelve(Constants.NUMERO_CERO);
					this.boolIndDevuelve = false;
				}else{
					or.setIndDevuelve(Constants.NUMERO_UNO);
					this.boolIndDevuelve = true;
				}
				
				if(StringUtils.isBlank(or.getIndDevuelveGeneraEnv()) || StringUtils.equals(or.getIndDevuelveGeneraEnv(), Constants.NUMERO_CERO)){
					or.setIndDevuelveGeneraEnv(Constants.NUMERO_CERO);
					this.boolIndDevuelveGeneraEnv = false;
				}else{
					or.setIndDevuelveGeneraEnv(Constants.NUMERO_UNO);
					this.boolIndDevuelveGeneraEnv = true;
				}
				
				List listTiposOperacion = service.getTipoOperacionListByOperacion(criteriaEdit);
				List listTiposOperacion2 = new ArrayList();
				
				if (listTiposOperacion != null && listTiposOperacion.size() > 0) {
		            for (int i = 0; i < listTiposOperacion.size(); i++) {
		            	TipoOperacion to = new TipoOperacion();
		                BeanUtils.copyProperties(to, listTiposOperacion.get(i));
		                listTiposOperacion2.add(to);
					}
		        }
				
				this.recTiposOperacionesList = listTiposOperacion2;
				this.listaModelRecTiposOperacionesList = new DataTableModel(this.recTiposOperacionesList);
				
//				form.setIndicadorNuevoTipoOperacion(Constants.NUMERO_UNO);
				
				BeanUtils.copyProperties(form, or);
			}
		}		
		return form;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		MantenimientoRECOperacionesReclamoSearchForm f = (MantenimientoRECOperacionesReclamoSearchForm) this.formBusqueda;
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setCodigoPais(pais.getCodigo());
		f.setCodigoOperacion(null);
		
		Map criteria = new HashMap();
		criteria.put("codigoPais", f.getCodigoPais());
		
		List list = interfazSiCCService.getOperacionesByCodigoPais(criteria);
				
		this.siccOperacionesList = null;
		this.siccOperacionesList = list;
		
		this.recOperacionesReclamoList = null;	
		
		this.mostrarBotonConsultar = false;
		
		this.salirGrabarPantallaPadre = true; 
	}

	/**
	 * @param event
	 */
	public void insertTipoOperacion(ActionEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'insertTipoOperacion' method");
		}
		
		try {
			
			MantenimientoRECOperacionesReclamoForm f = (MantenimientoRECOperacionesReclamoForm) this.formMantenimiento;
			String mensaje = null;
			
			if(StringUtils.isNotBlank(validacionInsertTipoOperacionJS())){
				mensaje = validacionInsertTipoOperacionJS();
				this.addError("Error: ", this.getResourceMessage(mensaje));
				return;
			}
			
			f.setIndicadorNuevoTipoOperacion(Constants.NRO_UNO);
//			Map map = BeanUtils.describe(f);
			
			if(this.boolIndCampReferenciaUnica == true)
				f.setIndCampReferenciaUnica(Constants.NUMERO_UNO);
			
			if(this.boolIndInfoBelcorpNoticias == true)
				f.setIndInfoBelcorpNoticias(Constants.NUMERO_UNO);
			
			if(this.boolIndDevuelveEstaFactura == true)
				f.setIndDevuelveEstaFactura(Constants.NUMERO_UNO);
			
			if(this.boolIndEnviaEstaFactura == true)
				f.setIndEnviaEstaFactura(Constants.NUMERO_UNO);
			
			
			TipoOperacion to = new TipoOperacion();
			BeanUtils.copyProperties(to, f);
			
			List list = this.recTiposOperacionesList;
			if(list == null || list.size() == 0)
				list = new ArrayList();
			
//			map.put("indicadorAccion", Constants.NUMERO_CERO);//0:INSERTAR 1:ACTUALIZA 2:ELIMINAS
			to.setIndicadorAccion(Constants.NUMERO_CERO);
			
			//Obtener descripcion Motivo Rechazo / Desbloqueo
			List listMRD = this.recMotivosRechazoDesbloqueoList;
			for (int i = 0; i < listMRD.size(); i++) {
				Base b = (Base) listMRD.get(i);
				if(to.getCodigoMotivoRechazo().equals(b.getCodigo())){
					to.setDescMotivoRechazo(b.getDescripcion());
				}
			}
			
			if(isValidoTipoOperacion(to.getCodigoTipoOperacion(), list, "")){//es registro valido cuando no se encuntra en la lista o se encuentra como eliminado			
				list.add(to);
			}else{
				mensaje = "mantenimientoRECOperacionesReclamoForm.existe.tipoOperacion.registro";
				this.addError("Error: ", this.getResourceMessage(mensaje));
				return;
			}
			
			f.setCodigoTipoOperacion("");
			f.setIndCampReferenciaUnica(Constants.NUMERO_CERO);
			this.setBoolIndCampReferenciaUnica(false);
			f.setNumDiasHaciaAtras("");
			f.setIndInfoBelcorpNoticias(Constants.NUMERO_CERO);
			this.setBoolIndInfoBelcorpNoticias(false);
			f.setIndDevuelveEstaFactura(Constants.NUMERO_CERO);
			this.setBoolIndDevuelveEstaFactura(false);
			f.setIndEnviaEstaFactura(Constants.NUMERO_CERO);
			this.setBoolIndEnviaEstaFactura(false);
			f.setCodigoMotivoRechazo("");
			
			this.recTiposOperacionesList = list;
			this.listaModelRecTiposOperacionesList = new DataTableModel(this.recTiposOperacionesList);
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}
	}
	
	/**
	 * @param event
	 */
	public void deleteTipoOperacion(ActionEvent event){
		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'deleteTipoOperacion' method");
		}
		
		try {
			
			String mensaje = null;
			MantenimientoRECOperacionesReclamoForm f = (MantenimientoRECOperacionesReclamoForm) this.formMantenimiento;
//			String id = request.getParameter("id");
//			log.debug("Row id a Eliminar: " + id);
			
			f.setIndicadorNuevoTipoOperacion(Constants.NRO_UNO);	
			
			if (this.columnaSeleccionada != null) {
				try {															
					List list = this.recTiposOperacionesList;
					
//					Map bean = (Map)list.get(Integer.parseInt(id)-1);
//					bean.put("indicadorAccion", Constants.NUMERO_DOS);//0:INSERTAR 1:ACTUALIZA 2:ELIMINAS
					TipoOperacion to =	(TipoOperacion) this.columnaSeleccionada;	 //(TipoOperacion) list.get(Integer.parseInt(id)-1);
					to.setIndicadorAccion(Constants.NUMERO_DOS);
					
					if(StringUtils.isBlank(to.getOidTipoOperacion())){
						for (int i = 0; i < list.size(); i++) {
							TipoOperacion toList = (TipoOperacion) list.get(i);
							if(toList.getCodigoTipoOperacion() == to.getCodigoTipoOperacion()){
								list.remove(i);
//								list.remove(this.columnaSeleccionada);	
								break;
							}
						
						}
					}

					this.recTiposOperacionesList = list;
					
				}catch (Exception e) {
					String error = e.getMessage();
					if (StringUtils.isBlank(error)) 
					error = e.getLocalizedMessage();
					this.addError("Error: ", this.getResourceMessage("errors.detail", new Object[]{error}));
				}
			}	
			
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));
		}	
	}
	
	/**
	 * @return
	 */
	private String validacionInsertTipoOperacionJS(){
		String mensaje = null;
		MantenimientoRECOperacionesReclamoForm form = (MantenimientoRECOperacionesReclamoForm) this.formMantenimiento;
		if(StringUtils.isBlank(form.getCodigoTipoOperacion())){
			mensaje = "mantenimientoRECOperacionesReclamoForm.codigoTipoOperacion.requerido";
			return mensaje;
		}
			
		if(StringUtils.isBlank(form.getCodigoMotivoRechazo())){
			mensaje = "mantenimientoRECOperacionesReclamoForm.codigoMotivoRechazo.requerido";
			return mensaje;
		}
		return mensaje;
	}
	
	/**
	 * @param codigoTipoOperacion
	 * @param list
	 * @param idTipoOperacion
	 * @return
	 */
	private boolean isValidoTipoOperacion(String codigoTipoOperacion, List list, String idTipoOperacion) {
		Iterator it = list.iterator();
		
//		String codigoTipoOperacion = (String)map.get("codigoTipoOperacion");
		
		while(it.hasNext()){
//			Map mapAux = (Map)it.next();
			TipoOperacion to = (TipoOperacion) it.next();

//			String codigoTipoOperacionAux = mapAux.get("codigoTipoOperacion").toString();
//			String indicadorAccionAux = (String)mapAux.get("indicadorAccion");
			
			String codigoTipoOperacionAux = to.getCodigoTipoOperacion();
			String indicadorAccionAux = to.getIndicadorAccion();
			
			if(codigoTipoOperacion.equals(codigoTipoOperacionAux) && (indicadorAccionAux.equals(Constants.NUMERO_CERO) || indicadorAccionAux.equals(Constants.NUMERO_UNO))) 
				return false;
		}
		
		return true;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setValidarConfirmar(java.lang.String)
	 */
	@Override
	public String setValidarConfirmar(String accion) {

		String mensaje = null;
		MantenimientoRECOperacionesReclamoForm form = (MantenimientoRECOperacionesReclamoForm) this.formMantenimiento;
		
		if(StringUtils.equals(this.accion, "ELIMINAR_TIPO_OPERACION")){
			
			if(this.recTiposOperacionesList == null || this.recTiposOperacionesList.size() == 0){
				mensaje = this.getResourceMessage("msg.errors.sin.registros");
				return mensaje;						
			}
			
//			if(this.columnaSeleccionada == null || this.columnaSeleccionada.size() == 0){
			if(this.columnaSeleccionada == null){
				mensaje = this.getResourceMessage("errors.select.item");
				return mensaje;	
			}
			
		}
		
		return mensaje;
	}

	/**
	 * @return the siccOperacionesList
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList the siccOperacionesList to set
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	/**
	 * @return the recOperacionesReclamoList
	 */
	public List getRecOperacionesReclamoList() {
		return recOperacionesReclamoList;
	}

	/**
	 * @param recOperacionesReclamoList the recOperacionesReclamoList to set
	 */
	public void setRecOperacionesReclamoList(List recOperacionesReclamoList) {
		this.recOperacionesReclamoList = recOperacionesReclamoList;
	}

	/**
	 * @return the indicadorSi
	 */
	public String getIndicadorSi() {
		return indicadorSi;
	}

	/**
	 * @param indicadorSi the indicadorSi to set
	 */
	public void setIndicadorSi(String indicadorSi) {
		this.indicadorSi = indicadorSi;
	}

	/**
	 * @return the indicadorNo
	 */
	public String getIndicadorNo() {
		return indicadorNo;
	}

	/**
	 * @param indicadorNo the indicadorNo to set
	 */
	public void setIndicadorNo(String indicadorNo) {
		this.indicadorNo = indicadorNo;
	}

	/**
	 * @return the indicadorUno
	 */
	public String getIndicadorUno() {
		return indicadorUno;
	}

	/**
	 * @param indicadorUno the indicadorUno to set
	 */
	public void setIndicadorUno(String indicadorUno) {
		this.indicadorUno = indicadorUno;
	}

	/**
	 * @return the indicadorCero
	 */
	public String getIndicadorCero() {
		return indicadorCero;
	}

	/**
	 * @param indicadorCero the indicadorCero to set
	 */
	public void setIndicadorCero(String indicadorCero) {
		this.indicadorCero = indicadorCero;
	}

	/**
	 * @return the numero_uno
	 */
	public String getNumero_uno() {
		return numero_uno;
	}

	/**
	 * @param numero_uno the numero_uno to set
	 */
	public void setNumero_uno(String numero_uno) {
		this.numero_uno = numero_uno;
	}

	/**
	 * @return the numero_dos
	 */
	public String getNumero_dos() {
		return numero_dos;
	}

	/**
	 * @param numero_dos the numero_dos to set
	 */
	public void setNumero_dos(String numero_dos) {
		this.numero_dos = numero_dos;
	}

	/**
	 * @return the tipoMatrizPrecios
	 */
	public String getTipoMatrizPrecios() {
		return tipoMatrizPrecios;
	}

	/**
	 * @param tipoMatrizPrecios the tipoMatrizPrecios to set
	 */
	public void setTipoMatrizPrecios(String tipoMatrizPrecios) {
		this.tipoMatrizPrecios = tipoMatrizPrecios;
	}

	/**
	 * @return the tipoMatrizPremios
	 */
	public String getTipoMatrizPremios() {
		return tipoMatrizPremios;
	}

	/**
	 * @param tipoMatrizPremios the tipoMatrizPremios to set
	 */
	public void setTipoMatrizPremios(String tipoMatrizPremios) {
		this.tipoMatrizPremios = tipoMatrizPremios;
	}

	/**
	 * @return the tipoFactura
	 */
	public String getTipoFactura() {
		return tipoFactura;
	}

	/**
	 * @param tipoFactura the tipoFactura to set
	 */
	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	/**
	 * @return the tipoCatalogo
	 */
	public String getTipoCatalogo() {
		return tipoCatalogo;
	}

	/**
	 * @param tipoCatalogo the tipoCatalogo to set
	 */
	public void setTipoCatalogo(String tipoCatalogo) {
		this.tipoCatalogo = tipoCatalogo;
	}

	/**
	 * @return the recMotivosBloqueoList
	 */
	public List getRecMotivosBloqueoList() {
		return recMotivosBloqueoList;
	}

	/**
	 * @param recMotivosBloqueoList the recMotivosBloqueoList to set
	 */
	public void setRecMotivosBloqueoList(List recMotivosBloqueoList) {
		this.recMotivosBloqueoList = recMotivosBloqueoList;
	}

	/**
	 * @return the boolIndAnulacion
	 */
	public boolean isBoolIndAnulacion() {
		return boolIndAnulacion;
	}

	/**
	 * @param boolIndAnulacion the boolIndAnulacion to set
	 */
	public void setBoolIndAnulacion(boolean boolIndAnulacion) {
		this.boolIndAnulacion = boolIndAnulacion;
	}

	/**
	 * @return the boolIndDevFisicoFact
	 */
	public boolean isBoolIndDevFisicoFact() {
		return boolIndDevFisicoFact;
	}

	/**
	 * @param boolIndDevFisicoFact the boolIndDevFisicoFact to set
	 */
	public void setBoolIndDevFisicoFact(boolean boolIndDevFisicoFact) {
		this.boolIndDevFisicoFact = boolIndDevFisicoFact;
	}

	/**
	 * @return the boolIndFaltanteMercaderia
	 */
	public boolean isBoolIndFaltanteMercaderia() {
		return boolIndFaltanteMercaderia;
	}

	/**
	 * @param boolIndFaltanteMercaderia the boolIndFaltanteMercaderia to set
	 */
	public void setBoolIndFaltanteMercaderia(boolean boolIndFaltanteMercaderia) {
		this.boolIndFaltanteMercaderia = boolIndFaltanteMercaderia;
	}

	/**
	 * @return the boolIndEnvia
	 */
	public boolean isBoolIndEnvia() {
		return boolIndEnvia;
	}

	/**
	 * @param boolIndEnvia the boolIndEnvia to set
	 */
	public void setBoolIndEnvia(boolean boolIndEnvia) {
		this.boolIndEnvia = boolIndEnvia;
	}

	/**
	 * @return the boolIndEnviaGeneraDev
	 */
	public boolean isBoolIndEnviaGeneraDev() {
		return boolIndEnviaGeneraDev;
	}

	/**
	 * @param boolIndEnviaGeneraDev the boolIndEnviaGeneraDev to set
	 */
	public void setBoolIndEnviaGeneraDev(boolean boolIndEnviaGeneraDev) {
		this.boolIndEnviaGeneraDev = boolIndEnviaGeneraDev;
	}

	/**
	 * @return the boolIndDevuelve
	 */
	public boolean isBoolIndDevuelve() {
		return boolIndDevuelve;
	}

	/**
	 * @param boolIndDevuelve the boolIndDevuelve to set
	 */
	public void setBoolIndDevuelve(boolean boolIndDevuelve) {
		this.boolIndDevuelve = boolIndDevuelve;
	}

	/**
	 * @return the recTiposSolicitudGeneraList
	 */
	public List getRecTiposSolicitudGeneraList() {
		return recTiposSolicitudGeneraList;
	}

	/**
	 * @param recTiposSolicitudGeneraList the recTiposSolicitudGeneraList to set
	 */
	public void setRecTiposSolicitudGeneraList(List recTiposSolicitudGeneraList) {
		this.recTiposSolicitudGeneraList = recTiposSolicitudGeneraList;
	}

	/**
	 * @return the recAlmacenList
	 */
	public List getRecAlmacenList() {
		return recAlmacenList;
	}

	/**
	 * @param recAlmacenList the recAlmacenList to set
	 */
	public void setRecAlmacenList(List recAlmacenList) {
		this.recAlmacenList = recAlmacenList;
	}

	/**
	 * @return the recMovimientoAlmacenList
	 */
	public List getRecMovimientoAlmacenList() {
		return recMovimientoAlmacenList;
	}

	/**
	 * @param recMovimientoAlmacenList the recMovimientoAlmacenList to set
	 */
	public void setRecMovimientoAlmacenList(List recMovimientoAlmacenList) {
		this.recMovimientoAlmacenList = recMovimientoAlmacenList;
	}

	/**
	 * @return the boolIndDevuelveGeneraEnv
	 */
	public boolean isBoolIndDevuelveGeneraEnv() {
		return boolIndDevuelveGeneraEnv;
	}

	/**
	 * @param boolIndDevuelveGeneraEnv the boolIndDevuelveGeneraEnv to set
	 */
	public void setBoolIndDevuelveGeneraEnv(boolean boolIndDevuelveGeneraEnv) {
		this.boolIndDevuelveGeneraEnv = boolIndDevuelveGeneraEnv;
	}

	/**
	 * @return the recMotivosRechazoDesbloqueoList
	 */
	public List getRecMotivosRechazoDesbloqueoList() {
		return recMotivosRechazoDesbloqueoList;
	}

	/**
	 * @param recMotivosRechazoDesbloqueoList the recMotivosRechazoDesbloqueoList to set
	 */
	public void setRecMotivosRechazoDesbloqueoList(
			List recMotivosRechazoDesbloqueoList) {
		this.recMotivosRechazoDesbloqueoList = recMotivosRechazoDesbloqueoList;
	}

	/**
	 * @return the recTiposSolicitudList
	 */
	public List getRecTiposSolicitudList() {
		return recTiposSolicitudList;
	}

	/**
	 * @param recTiposSolicitudList the recTiposSolicitudList to set
	 */
	public void setRecTiposSolicitudList(List recTiposSolicitudList) {
		this.recTiposSolicitudList = recTiposSolicitudList;
	}

	/**
	 * @return the recTiposOperacionesList
	 */
	public List getRecTiposOperacionesList() {
		return recTiposOperacionesList;
	}

	/**
	 * @param recTiposOperacionesList the recTiposOperacionesList to set
	 */
	public void setRecTiposOperacionesList(List recTiposOperacionesList) {
		this.recTiposOperacionesList = recTiposOperacionesList;
	}

	/**
	 * @return the boolIndCampReferenciaUnica
	 */
	public boolean isBoolIndCampReferenciaUnica() {
		return boolIndCampReferenciaUnica;
	}

	/**
	 * @param boolIndCampReferenciaUnica the boolIndCampReferenciaUnica to set
	 */
	public void setBoolIndCampReferenciaUnica(boolean boolIndCampReferenciaUnica) {
		this.boolIndCampReferenciaUnica = boolIndCampReferenciaUnica;
	}

	/**
	 * @return the boolIndInfoBelcorpNoticias
	 */
	public boolean isBoolIndInfoBelcorpNoticias() {
		return boolIndInfoBelcorpNoticias;
	}

	/**
	 * @param boolIndInfoBelcorpNoticias the boolIndInfoBelcorpNoticias to set
	 */
	public void setBoolIndInfoBelcorpNoticias(boolean boolIndInfoBelcorpNoticias) {
		this.boolIndInfoBelcorpNoticias = boolIndInfoBelcorpNoticias;
	}

	/**
	 * @return the boolIndDevuelveEstaFactura
	 */
	public boolean isBoolIndDevuelveEstaFactura() {
		return boolIndDevuelveEstaFactura;
	}

	/**
	 * @param boolIndDevuelveEstaFactura the boolIndDevuelveEstaFactura to set
	 */
	public void setBoolIndDevuelveEstaFactura(boolean boolIndDevuelveEstaFactura) {
		this.boolIndDevuelveEstaFactura = boolIndDevuelveEstaFactura;
	}

	/**
	 * @return the boolIndEnviaEstaFactura
	 */
	public boolean isBoolIndEnviaEstaFactura() {
		return boolIndEnviaEstaFactura;
	}

	/**
	 * @param boolIndEnviaEstaFactura the boolIndEnviaEstaFactura to set
	 */
	public void setBoolIndEnviaEstaFactura(boolean boolIndEnviaEstaFactura) {
		this.boolIndEnviaEstaFactura = boolIndEnviaEstaFactura;
	}

	/**
	 * @return the listaModelRecTiposOperacionesList
	 */
	public DataTableModel getListaModelRecTiposOperacionesList() {
		return listaModelRecTiposOperacionesList;
	}

	/**
	 * @param listaModelRecTiposOperacionesList the listaModelRecTiposOperacionesList to set
	 */
	public void setListaModelRecTiposOperacionesList(
			DataTableModel listaModelRecTiposOperacionesList) {
		this.listaModelRecTiposOperacionesList = listaModelRecTiposOperacionesList;
	}

	/**
	 * @return the columnaSeleccionada
	 */
	public TipoOperacion getColumnaSeleccionada() {
		return columnaSeleccionada;
	}

	/**
	 * @param columnaSeleccionada the columnaSeleccionada to set
	 */
	public void setColumnaSeleccionada(TipoOperacion columnaSeleccionada) {
		this.columnaSeleccionada = columnaSeleccionada;
	}	
	
	
}
