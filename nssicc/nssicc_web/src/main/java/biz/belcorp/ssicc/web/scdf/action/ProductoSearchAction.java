package biz.belcorp.ssicc.web.scdf.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.scdf.model.Producto;
import biz.belcorp.ssicc.service.exception.InvalidDescriptionException;
import biz.belcorp.ssicc.service.exception.InvalidIdentifierException;
import biz.belcorp.ssicc.service.scdf.InterfazSiCCService;
import biz.belcorp.ssicc.service.scdf.ProductoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseMantenimientoSearchAbstractAction;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;
import biz.belcorp.ssicc.web.framework.util.DataTableModel;
import biz.belcorp.ssicc.web.scdf.form.ProductoForm;
import biz.belcorp.ssicc.web.scdf.form.ProductoSearchForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({"rawtypes","unchecked"})
public class ProductoSearchAction extends BaseMantenimientoSearchAbstractAction  
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2057856765245873806L;
	

	@Override
	protected String getSalirForward()
	{
		return "productoList";
	}

	@Override
	protected String getPaginaMantenimiento() throws Exception 
	{
		return "productoForm";
	}

	@Override
	protected BaseSearchForm devuelveFormBusqueda() throws Exception 
	{
		ProductoSearchForm searchForm = new ProductoSearchForm();
		return searchForm;
	}

	@Override
	protected List setFindAttributes() throws Exception 
	{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'search' method");
		}
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ProductoSearchForm searchForm = (ProductoSearchForm) this.formBusqueda;

		// Obtenemos las propiedades del bean como un 'Map'
		Map criteria = BeanUtils.describe(searchForm);
		
		// Modificamos los valores que requieren el caracter '%'

		criteria.put("codigoPais", pais.getCodigo());
		if (StringUtils.isNotBlank(searchForm.getCodigoProducto())) {
			criteria.put("codigoProducto", searchForm.getCodigoProducto());
		}

		if (StringUtils.isNotBlank(searchForm.getDescripcionProducto())) {
			criteria.put("descripcionProducto", searchForm.getDescripcionProducto() + "%");
		}

		if (log.isDebugEnabled()) {
			log.debug(criteria.toString());
		}

		ProductoService service = (ProductoService) getBean("scdf.productoService");

		List lista = service.getProductosByCriteria(criteria);

		return lista;
	}

	@Override
	protected boolean setDeleteAttributes() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean setSaveAttributes() throws Exception 
	{	
		if (log.isDebugEnabled()) {
			log.debug("Entering 'setSaveAttributes' method");
		}

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		// Extraemos atributos y parámetros a usar
		ProductoForm productoForm = (ProductoForm) this.formMantenimiento;

		// Extreamos el usuario de la sesión
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();

		// Creamos la instancia del servicio y le asignamos
		// el usuario que va a realizar las operaciones
		ProductoService service = (ProductoService) getBean("scdf.productoService");

		Producto producto = new Producto();
		BeanUtils.copyProperties(producto, productoForm);
		producto.setCodigoPais(pais.getCodigo());

		try {
			service.updateProducto(producto, usuario);
			
		} catch (InvalidIdentifierException iie) 
		{
			String codigo = iie.getIdentifier().toString();
			throw new Exception(this.getResourceMessage("errors.invalid.id", new Object[]{codigo}));
			
		} catch (InvalidDescriptionException ide) 
		{
			String descripcion = ide.getDescription();
			throw new Exception(this.getResourceMessage("errors.invalid.description", new Object[]{descripcion}));
		}
		
		return true;
	}

	@Override
	protected BaseEditForm setObtenerRegistroAttributes() throws Exception 
	{
		// TODO Auto-generated method stub
//		return null;
		
		ProductoForm productoForm = new ProductoForm();
		Producto productobusqueda = (Producto)this.beanRegistroSeleccionado;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		// Si el id ha sido enviado, buscamos la informacion
        // en caso contrario, no hacemos nada, se esta insertando
        // un nuevo registro a la aplicación
		if (productobusqueda.getCodigo() != null) {
            if (log.isDebugEnabled()) {
                log.debug("Id seleccionado de la lista: " + productobusqueda.getCodigo());
            }
            Producto parametro = new Producto();
            parametro.setCodigo(productobusqueda.getCodigo());
            parametro.setCodigoPais(pais.getCodigo());
            ProductoService service = (ProductoService) getBean("scdf.productoService");
            Producto producto = (Producto) service.getProducto(parametro);
            BeanUtils.copyProperties(productoForm, producto);
          
            productoForm.setNewRecord(false);
        }
		
		return productoForm;
	}

	@Override
	protected void setViewAtributes() throws Exception
	{		
		this.mostrarBotonConsultar = false;	
		this.mostrarBotonNuevo = false;
		this.mostrarBotonEliminar = false;
		
	    this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
	    this.salirGrabarPantallaPadre = true; 
	}
	
	@Override
	protected String devuelveMensajeKeySaveOK() {
		ProductoForm productoForm = (ProductoForm) this.formMantenimiento;
		boolean isNew = productoForm.isNewRecord();
		if(isNew){
			return "";
		}else{
			return "producto.updated";
		}	
	}
	
	public void refrescar(ActionEvent event)
	{
		try {
			 if (log.isDebugEnabled()) {
		            log.debug("Entering 'refresh' method");
		        }

		        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		        // Extreamos el usuario de la sesión
		        Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		        
		        InterfazSiCCService service = (InterfazSiCCService) getBean("scdf.interfazSiCCService");
		        service.executeCargaProductos(pais.getCodigo(), usuario.getLogin());
		        
		        this.listaBusqueda = this.setFindAttributes();
		        this.datatableBusqueda = new DataTableModel(this.listaBusqueda);
		        
		        this.addInfo("", this.getResourceMessage("producto.refreshed"));
		} catch (Exception e) {
			this.addError("Error: ", this.obtieneMensajeErrorException(e));		
		}
	}
	
	@Override
	public String setValidarConfirmar(String accion) {
		return "";
	}

}
