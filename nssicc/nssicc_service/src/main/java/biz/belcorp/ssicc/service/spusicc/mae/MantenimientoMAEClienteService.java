package biz.belcorp.ssicc.service.spusicc.mae;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteAdicional;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteClasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacionOperadora;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteDireccion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteEncuesta;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoDatos;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteIdentificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteObservacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClientePreferenciaComunicacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClientePrimerContacto;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteReferencias;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteSubTipo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteTipoLogro;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteUnidadAdministrativa;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteVinculo;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ExcencionFlete;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ExcencionSobreFlete;
import biz.belcorp.ssicc.dao.spusicc.mae.model.SegmentoGrupoLove;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoMAEClienteService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 */
public interface MantenimientoMAEClienteService extends Service {

public LabelValue[] getPeriodosVigentesByPaisMarcaCanal(Map criteria);
	
	public List getSubTiposClienteInsertar(Map criteria);
	
	public String getOidPais(Map criteria);
	
	public String getTipoClasificacionDefault(String codigoTipoCliente, String codigoSubTipoCliente);
	
	public String getClasificacionDefault(String codigoTipoCliente, String codigoSubTipoCliente);
	
	public String getLongitudCodigoCliente(Map criteria);
	
	public String getLongitudCodigoClien(Map criteria);
	
	/**
	 * Retorna la longitud del documento de identidad principal para un país 
	 * @param criteria
	 * @return
	 */
	public String getLongitudNumeroDocIdentidad(Map criteria);
	
	public List getEstadosCiviles(Map criteria);
	
	public List getTratamientos(Map criteria);
	
	public List getNivelEstudios(Map criteria);
	
	public List getNacionalidades(Map criteria);
	
	public List getTiposDocumentoIdentidad(Map criteria);
	
	public String getTipoDocumentoObligatorio(String oidPais);
	
	public String getTipoDocumentoDuplaCyzone(String oidPais);
	
	public List getSexos(Map criteria);
	
	public List getTiposVias(Map criteria); 

	public List getNivelesGeograficos(Map criteria);

	public List getTiposClasificaciones(Map criteria);
	
	public String validarDocumentoIdentidad(Map criteria);
	
	public boolean isCodigoClienteAutomatico(Map criteria);
	
	public Base getNuevoCodigoCliente(Map criteria) throws Exception;
	
	public List validarZonaTerritorio(Map criteria);
	public List validarDatosCliente(Map criteria);
	
	public String getNumeroDocumentoPrincipal(String oidCliente);
	public Long getOidMarca(String codigoMarca);
	public Long getOidCanal(String codigoCanal);
	public Long getOidFormaPagoPais(String oidPais);
	
	public Long getOidFormaPagoSubTipoCliente(String oidPais, String oidSubTipoCliente);
	public String getValorConfiCampoSubTipoCliente(String oidPais, String oidSubTipoCliente, String oidCampoConfiguracion);
	
	public Long getOidTipoDireccion(String codigoTipoDireccion);
	public Long getOidTipoComunicacion(String codigoTipoComunicacion);
	public Long getOidTipoVinculo(String oidPais, String codigoTipoVinculo);
	public String getCriterioBusqueda1(String oidPais);
	public String getCriterioBusqueda2(String oidPais);
	
	public void insertCliente(Cliente cliente) throws Exception;
	
	public List getClientesByCriteria(Map criteria);	
	
	public List getDireccionesClientesByCriteria(Map criteria);
	
	public String getEdadMinima(Map criteria);
	
	public String getEdadMaxima(Map criteria);
	
	public String getExisteMensajeBuzon(Map criteria);
	
	public void insertBuzonMensajes(Map criteria);
	
	public List getConcursos(Map criteria);
	public List getPremios(Map criteria);
	public void insertRecomendante(Map criteria);
	public String getOidRecomendante(Map criteria);
	public void insertRecomendado(Map criteria);
	public String getOidRecomendado(Map criteria);

	public Long getOidClienteTipoSubTipoMigracion(String oidPais);
	
	/**
	 * Se recupera los tipos de direccion
	 * 
	 * @param criteria
	 */	
	public List getTiposDireccion(Map criteria);
	
	/**
	 * Recupera los niveles de riesgo del sistema
	 * 
	 * @param criteria
	 * @return
	 */
	public List getNivelesRiesgo(Map criteria);

	/**
	 * Recupera el nivel de riesgo de un determinado cliente
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getNivelRiesgoCliente(Map criteria);

	/**
	 * Recupera la ultima fecha que se actualizo el nivel de riesgo del Cliente
	 * 
	 * @param criteria
	 * @return
	 */
	public String getFechaUltimaNivelRiesgoCliente(Map criteria); 
	
	/**
	 * se registra el nuevo nivel de riesgo del Cliente
	 * 
	 * @param criteria
	 */
	public void insertNivelRiesgoCliente(Map criteria);

	/**
	 * Obtine la lista de Zonas
	 * @param criteria
	 * @return
	 */
	public List getZonasByPaisMarcaCanal(Map criteria);
	
	
	/**
	 * Obtiene la lista de Territorios en funcion a la Zona 
	 * @param criteria
	 * @return
	 */
	public List getTerritoriosByPaisMarcaCanalZona(Map criteria);

	/**
	 * Retorna la Lista de clientes originada desde la consulta de Clientes
	 * @param criteria
	 * @return
	 */
	public List getListClientesByCriteria(Map criteria);
	
	
	/**
	 * Obtiene los datos basicos del cliente
	 * @param codigoPais
	 * @param codigoCliente
	 * @return
	 */
	public Cliente getDatosBasicosCliente(String codigoPais,String codigoCliente);
	
	/**
	 * Obtiene los datos adicionales del cliente
	 * @param oidCliente
	 * @return
	 */
	public ClienteAdicional getDatosAdicionalesCliente(String oidCliente);
	
	
	/**
	 * Obtiene el primer contacto
	 * @param oidCliente
	 * @return
	 */
	public ClientePrimerContacto getPrimerContactoCliente(String oidCliente); 
	
	/**
	 * Retorna una lista de Identificacion del Cliente
	 * @param oidCliente
	 * @return
	 */
	public List getListIdentificacionCliente(String oidCliente);
	
	/**
	 * Retorna una lista de Tipo Subtipo del Cliente
	 * @param oidCliente
	 * @return
	 */
	public List getListTipoSubtipoCliente(String oidCliente);
	
	/**
	 * Retorna una lista de Clasificacion del Cliente
	 * @param oidCliente
	 * @return
	 */
	public List getListClasificacionCliente(String oidClienteSubTipo);
	
	
	/**
	 * Retorna Lista de direcciones del cliente
	 * @param oidCliente
	 * @return
	 */
	public List getListDireccionCliente(String oidCliente);
	
	/**
	 * Retorna lista de comunicacion del cliente
	 * @param oidCliente
	 * @return
	 */
	public List getListComunicacionCliente(String oidCliente);
	
	/**
	 * Retorna unidad administrativa cliente
	 * @param oidCliente
	 * @return
	 */
	public ClienteUnidadAdministrativa getUnidadAdministrativaCliente(String oidCliente);
	
	/**
	 * Retorna Vinculo cliente
	 * @param oidCliente
	 * @return
	 */
	public List getListVinculoCliente(String oidCliente);
	
	/**
	 * Retorna observaciones cliente
	 * @param oidCliente
	 * @return
	 */
	public List getListObservacionCliente(String oidCliente);
	
	/**
	 * actualiza cliente
	 * @param cliente
	 */
	public void updateCliente(Cliente cliente) throws Exception;
	
	/**
	 * actualiza datos basicos cliente
	 * @param cliente
	 */
	public void updateDatosBasicosCliente(Cliente cliente) throws Exception;
	
	/**
	 * actualiza datos adicionales del cliente
	 * @param clienteAdiconal
	 */
	public void updateDatosAdicionalesCliente(ClienteAdicional clienteAdiconal);
	
	/**
	 * actualiza primer contacto con el cliente
	 * @param clientePrimerContacto
	 */
	public void updatePrimerContactoCliente(ClientePrimerContacto clientePrimerContacto); 
	
	/**
	 * actualiza identificacion del cliente
	 * @param clienteIdentificacion
	 */
	public void updateIdentificacionCliente(ClienteIdentificacion clienteIdentificacion);
	
	/**
	 * elimina identificacion del cliente
	 * @param clienteIdentificacion
	 */
	public void deleteIdentificacionCliente(ClienteIdentificacion clienteIdentificacion);
	
	/**
	 * actualiza tipo subtipo cliente
	 * @param clienteSubTipo
	 */
	public void updateTipoSubtipoCliente(ClienteSubTipo clienteSubTipo);
	
	/**
	 * elimina tipo subtipo cliente
	 * @param clienteSubTipo
	 */
	public void deleteTipoSubtipoCliente(ClienteSubTipo clienteSubTipo);
	
	/**
	 * actualiza clasificacion cliente
	 * @param clienteClasificacion
	 */
	public void updateClasificacionCliente(ClienteClasificacion clienteClasificacion);
	
	/**
	 * elimina clasificacion cliente
	 * @param clienteClasificacion
	 */
	public void deleteClasificacionCliente(ClienteClasificacion clienteClasificacion);
	
	/**
	 * actualiza direccion cliente
	 * @param clienteDireccion
	 */
	public void updateDireccionCliente(ClienteDireccion clienteDireccion);

	/**
	 * elimina direccion cliente
	 * @param clienteDireccion
	 */
	public void deleteDireccionCliente(ClienteDireccion clienteDireccion);
	/**
	 * actualiza cominicacion del cliente
	 * @param clienteComunicacion
	 */
	public void updateComunicacionCliente(ClienteComunicacion clienteComunicacion);
	
	/**
	 * actualiza vinculo cliente
	 * @param clienteVinculo
	 */
	public void updateVinculoCliente(ClienteVinculo clienteVinculo);
	
	/**
	 * actualiza observacion cliente
	 * @param ClienteObservacion
	 */
	public void updateObservacionCliente(ClienteObservacion clienteObservacion);
	
	/**
	 * Recupera todo los periodos x Campaña
	 * 
	 * @param criteria
	 * @return
	 */
	public List getPeriodosByPaisMarcaCanal(Map criteria);

	/**
	 * El tipo Documento corresponde al que contiene a una sigla=RUC
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean esTipoDocumentoSiglaRUC(Map criteria);

	/**
	 * Verifica si el cliente ha Facturado
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean esClienteHaFacturado(Map criteria);

	/**
	 * Verifica si la Region de la Zona ya proceso cierre de Region para la Campaña Actual
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean esRegionCerradaxZona(Map criteria);

	/**
	 * Recuperamos el siguiente periodo
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getSiguientePeriodo(Map criteria);

	/**
	 * Se Verifica si se recalcula el periodo de ingreso para un determinado pais
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean esPaisCalculaPeriodoIngreso(String codigoPais);

	/**
     * Obtiene la fecha de inicio de la campaña
     * 
     * @param criteria
     * @return
     */
    public String getFechaInicioPeriodo(Map criteria);
    
    /**
     * Actualiza los premio del cliente recomendado
     * 
     * @param listClienteConcursoPremio
     */
    public void updateClienteRecomendado(List listClienteConcursoPremio);    


    /**
     * @param codigoPais
     * @param tipoValidacion
     * @return
     */
    public String getValorModuloxPaisTipoValidacion(String codigoPais, String tipoValidacion);
	/**
	 * Retorna una lista con los vinculos del cliente
	 * @return
	 */
	public List getTipoVinculo();
	
	
	/**
	 * Registra las referencias del cliente
	 * @param clienteReferencias
	 */
	public void insertClienteReferencias(ClienteReferencias clienteReferencias) ;

	/**
	 * Obtenemos la lista de referencia del cliente
	 * @param map
	 * @return
	 */
	public List getListClienteReferencia(Map map);

	/**
	 * Verifica si la Zona ya proceso cierre de Zona para la Campaña Actual 
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean esZonaCerrada(Map criteria);

	/**
	 * Recupera el oid de Tipo Via 
	 * 
	 * @param codigoTipoVia
	 * @return
	 */
	public Long getOidTipoVia(String codigoTipoVia);

	/**
	 * Verifica si el cliente ha facturado en determinados periodos
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean esClienteHaFacturadoPeriodos(Map criteria);
	
	/**
	 * Recupera el ultimo periodo que ha facturado el cliente
	 * 
	 * @param oidCliente
	 * @return
	 */
	public String getUltimoPeriodoFacturado(String oidCliente);

	/**
	 * Recuperamos el periodo anterior
	 * 
	 * 
	 * @param criteria
	 * @return
	 */
	public Base getPeriodoAnterior(Map criteria);

	/**
	 * Verificamos si el periodo ha cerrado
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean esPeriodoCerrado(Map criteria);

	/**
	 * Retorna lista de cliente deudouras avaladas
	 * @param criteria
	 * @return
	 */
	public List getListConsultorasDeudorasAval(Map criteria);

	/**
	 * Ejecuta el proceso que obtiene las consultras deudoras avaladas
	 * @param criteria
	 */
	public void executeValidacionDeudoraConsultoraAval(Map criteria);

	/**
	 * Obtiene el numero de pedidos que han sido enviados a pedidos
	 * @param map
	 * @return
	 */
	public Integer getSizePedidosEnviados(Map map);

	/**
	 * Actualiza las entidades del cliente, que manejan el dato de periodo
	 * 
	 * @param params
	 * @throws Exception
	 */
	public void updateClientePeriodo(Map params) throws Exception;

	/**
	 * Recupera la zona de un cliente
	 * 
	 * @param codigoPais
	 * @param codigoCliente
	 * @return
	 */
	public String getZonaCliente(String codigoPais, String codigoCliente);

	/**
	 * Obtiene el tipo de Documento, para el segundo documento del Cliente
	 * 
	 * @param oidPais
	 * @return
	 */
	public String getSegundoTipoDocumento(String oidPais);
	
	/**
	 * Verificar si existe el tipo y numero de documento de identidad
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean verificarDocumentoIdentidad(Map criteria);

	/**
	 * Retorna objeto si existe la preferencia d ecomunicacion nulo si no existe 
	 * @param preferenciaComun
	 * @return
	 */
	public ClientePreferenciaComunicacion getExistePreferenciaComunicacion(
			ClientePreferenciaComunicacion preferenciaComun);

	/**
	 * Actualiza la preferencia de comunicacion
	 * @param preferenciaComun
	 * @param usuario
	 */
	public void updatePreferencia(
			ClientePreferenciaComunicacion preferenciaComun, Usuario usuario);

	/**
	 * Inserta la preferencia de comunicacion
	 * @param preferenciaComun
	 * @param usuario
	 */
	public void insertPreferencia(
			ClientePreferenciaComunicacion preferenciaComun, Usuario usuario);
	
	/**
	 * Retorna 1 si existe la preferencia del cleinte de comunicacion 0 si no existe 
	 * @param preferenciaComun
	 * @return
	 */
	public Integer getExistePreferenciaClienteComunicacion(
			ClientePreferenciaComunicacion preferenciaComun);

	/**
	 * Actualiza la preferencia cliente de comunicacion
	 * @param preferenciaComun
	 * @param usuario
	 */
	public void updatePreferenciaCliente(
			ClientePreferenciaComunicacion preferenciaComun, Usuario usuario);

	/**
	 * Inserta la preferencia de cliente de comunicacion
	 * @param preferenciaComun
	 * @param usuario
	 */
	public void insertPreferenciaCliente(
			ClientePreferenciaComunicacion preferenciaComun, Usuario usuario);

	/**
	 * Devuelve el objeto si existe el tipo de operadora y nulo si no existe
	 * @param comunicacionOperadora
	 * @return
	 */
	public ClienteComunicacionOperadora getExisteTipoOperadora(
			ClienteComunicacionOperadora comunicacionOperadora);

	/**
	 * actualiza el tipo de operadora
	 * @param comunicacionOperadora
	 * @param usuarioBean
	 */
	public void updateTipoOperadora(
			ClienteComunicacionOperadora comunicacionOperadora,
			Usuario usuarioBean);

	/**
	 * inserta el tipo de operadora
	 * @param comunicacionOperadora
	 * @param usuarioBean
	 */
	public void insertTipoOperadora(
			ClienteComunicacionOperadora comunicacionOperadora,
			Usuario usuarioBean);

	/**
	 * devuelve 1 si existe la comunicacion cliente 0 si no existe
	 * @param comunicacionOperadora
	 * @return
	 */
	public Integer getExisteComunicacionCliente(
			ClienteComunicacionOperadora comunicacionOperadora);

	/**
	 * Actualiza comunicacion cliente
	 * @param comunicacionOperadora
	 * @param usuarioBean
	 */
	public void updateComunicacionCliente(
			ClienteComunicacionOperadora comunicacionOperadora,
			Usuario usuarioBean);

	/**
	 * Inserta comunicacion cliente
	 * @param comunicacionOperadora
	 * @param usuarioBean
	 */
	public void insertComunicacionCliente(
			ClienteComunicacionOperadora comunicacionOperadora,
			Usuario usuarioBean);

	/**
	 * Retorna el objeto si existe tipo de logro null si no existe tipo logro
	 * @param clientTipoLogro
	 * @return
	 */
	public ClienteTipoLogro getExisteTipoLogro(ClienteTipoLogro clientTipoLogro);

	/**
	 * actualiza tipo logro
	 * @param clientTipoLogro
	 * @param usuarioBean
	 */
	public void updateTipoLogro(ClienteTipoLogro clientTipoLogro,
			Usuario usuarioBean);

	/**
	 * inserta tipo logro
	 * @param clientTipoLogro
	 * @param usuarioBean
	 */
	public void insertTipoLogro(ClienteTipoLogro clientTipoLogro,
			Usuario usuarioBean);

	/**
	 * Retorna el objetos si Existe GrupoLove y nullo si no existe
	 * @param segmentoGrupoLove
	 * @return
	 */
	public SegmentoGrupoLove getExisteGrupoLove(
			SegmentoGrupoLove segmentoGrupoLove);

	/**
	 * Actualiza grupo love
	 * @param segmentoGrupoLove
	 * @param usuario
	 */
	public void updateGrupoLove(SegmentoGrupoLove segmentoGrupoLove,
			Usuario usuario);

	/**
	 * inserta el grupo love
	 * @param segmentoGrupoLove
	 * @param usuario
	 */
	public void insertGrupoLove(SegmentoGrupoLove segmentoGrupoLove,
			Usuario usuario);

	/**
	 * Retorna objeto si exitse segmento de grupo love, nulo si no existe
	 * @param segmentoGrupoLove
	 * @return
	 */
	public SegmentoGrupoLove getExisteSegmentoGrupoLove(
			SegmentoGrupoLove segmentoGrupoLove);

	/**
	 * Actulaiza segmento grupo love
	 * @param segmentoGrupoLove
	 * @param usuario
	 */
	public void updateSegmentoGrupoLove(SegmentoGrupoLove segmentoGrupoLove,
			Usuario usuario);

	/**
	 * Inserta segmento grupo love
	 * @param segmentoGrupoLove
	 * @param usuario
	 */
	public void insertSegmentoGrupoLove(SegmentoGrupoLove segmentoGrupoLove,
			Usuario usuario);

	/**
	 * Retorna 1 si la encuesta existe 0 si no existe
	 * @param clienteEncuesta
	 * @return
	 */
	public Integer getExisteClienteEncuesta(ClienteEncuesta clienteEncuesta);

	/**
	 * Actualiza encuesta del cliente
	 * @param clienteEncuesta
	 * @param usuario
	 */
	public void updateClienteEncuesta(ClienteEncuesta clienteEncuesta,
			Usuario usuario);

	/**
	 * Inserta encuesta cliente
	 * @param clienteEncuesta
	 * @param usuario
	 */
	public void insertClienteEncuesta(ClienteEncuesta clienteEncuesta,
			Usuario usuario);

	/**
	 * Obtiene los caracteres VALIDOS/NOVALIDOS relacionaods a un codigo de modulo de Validacion
	 * 
	 * @param codigoModuloValidacion
	 * @param indicador
	 * @return
	 */
	public String getCaracteresxModuloValidacion(String codigoModuloValidacion, String indicador);

	/**
	 * Actualiza las entidades del cliente, que manejan el dato de periodo
	 * 
	 * @param params
	 * @throws Exception
	 */
	public void updateClientePeriodoRetiradas(Map params) throws Exception;

	/**
	 * Valida si el concurso de recomendacion de la Recomendada esta vigente
	 * 
	 * @param criteria
	 * @return
	 */
	public String getValidacionVigenciaRecomendacion(Map criteria);

	/**
	 * Obtenemos el Oid Periodo del primer pedido del cliente
	 * 
	 * @param oidPais
	 * @param oidCliente
	 * @return
	 */
	public String getPeriodoRecomendacion(String oidPais, String oidCliente);

	/**
	 * Obtiene lista de Cutis
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTipoCutis(Map criteria);

	/**
	 * Obtiene lista de Otras Marcas
	 * 
	 * @param criteria
	 * @return
	 */
	public List getOtrasMarcas(Map criteria);
	
	/**
	 * Obtiene lista de Tipo persona
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTipoPersona(Map criteria);
	
	/**
	 * Obtiene lista de Origen ingreso
	 * 
	 * @param criteria
	 * @return
	 */
	public List getOrigenIngreso(Map criteria);

	/**
	 * Valida la consultara que viene de web service
	 * @param map
	 * @return
	 */
	public Map getValidarOCRConsultora(Map map);

	/**
	 * se actualiza datos de riesgo, subtipos-clasificaciones y estatus del Cliente
	 * 
	 * @param params
	 */
	public void updateInformacionCliente(Map params);
	
	/**
	 * Obtiene un listado de clientes que tienen bloqueos o no en base a criterios
	 * @param criteria
	 * @return
	 */
	public List getClientesBloqueoDesbloqueoByCriteria(Map criteria);
	
	/**
	 * Obtiene un listado de tipos de bloqueo a los que tiene acceso el usuario
	 * @param usuario
	 * @return
	 */
	public List getAccesosTiposBloqueoByUsuario(final String usuario);

	/**
	 * Obtiene un listado de tipos de desbloqueo a los que tiene acceso el usuario
	 * @param usuario
	 * @return
	 */
	public List getAccesosTiposDesbloqueoByUsuario(final String usuario);
	
	/**
	 * Obtiene un listado de los bloqueos que tiene el cliente
	 * @param oidCliente
	 * @param tipoBloqueo
	 * @return
	 */
	public List getBloqueosClienteByTipoBloqueo(final Long oidCliente, final String tipoBloqueo);
	
	/**
	 * Registra un bloqueo de cliente
	 * @param params
	 */
	public void insertBloqueoCliente(Map params);
	
	/**
	 * Obtiene un listado de accesos a las que tiene el usuario por tipo de bloqueo
	 * @param codigoUsuario
	 * @param oidTipoBloqueo
	 * @return
	 */
	public List getAccesosTiposDesbloqueoByUsuario(final String codigoUsuario, final String oidTipoBloqueo);
	
	/**
	 * Obtiene los datos de un bloqueo de cliente
	 * @param oidBloqueo
	 * @return
	 */
	public Map getBloqueoCliente(final String oidBloqueo);
	
	/**
	 * Actualiza los datos de bloqueo de un cliente
	 * @param params
	 */
	public void updateBloqueoCliente(Map params);
	
	/**
	 * Obtiene el historico de bloqueos del cliente
	 * @param oidCliente
	 * @return
	 */
	public List getLogBloqueosCliente(final Long oidCliente);
	
	/**
	 * Obtiene el historico de desbloqueos del cliente
	 * @param oidCliente
	 * @return
	 */
	public List getLogDesbloqueosCliente(final Long oidCliente);
	
	/* INI SA PER-SiCC-2012-0580 */
	/**
	 * Valida si el cliente tiene pedidos en proceso
	 * 
	 * @param oidCliente
	 * @return
	 */
	public boolean validaPedidosEnProceso(Map criteria);
	/* FIN SA PER-SiCC-2012-0580 */

	/* INI SA PER-SiCC-2012-0365 */
	/**
	 * Obtiene la campaña inicio de Vacaciones
	 * 
	 * @param criteria
	 * @return
	 */
	public String getPeriodoInicioVacaciones(Map criteria);
	
	/**
	 * Obtiene la campaña fin de Vacaciones
	 * 
	 * @param criteria
	 * @return
	 */
	public String getPeriodoFinVacaciones(Map criteria);
	
	/**
	 * Verifica si la consultora tiene pedido facturado en periodo proceso
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean tienePedidoFacturado(Map criteria);
	
	/**
	 * Valida si el cliente tiene pedidos en proceso de GP2 a GP4 
	 * 
	 * @param criteria
	 * @return
	 */            
	public boolean tienePedidoEnProcesoFacturacion(Map criteria);
	/* FIN SA PER-SiCC-2012-0365 */

	/* INI SA PER-SiCC-2012-0367 */
	/**
	 * Inserta una solicitud Credito rechazado (STO)
	 * 
	 * @param params
	 */
	public void insertSolicitudCreditoRechazado(Map params);
	
	/**
	 * Realiza la Generacion de la Solicitud de Credito Rechazada para el caso de cliente Inactivo
	 * 
	 * @param criteria
	 */
	public void executeGenerarSolicitudCreditoRechazada(Map params);
	/* FIN SA PER-SiCC-2012-0367 */

	public boolean getObtieneTipoValidacion(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public Map getDatosCliente(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getMotivosBaja(Map criteria);
	
	/**
	 * @param params
	 */
	public void executeBajaManualEmpresarias(Map params) ;
	
	/**
	 * @param criteria
	 * @return
	 */
	public Map getDatosEmprendedoraCliente(Map criteria);
	
	/**
	 * @param map
	 * @return
	 */
	public String getZonaPeriodoCerrada(Map map);
	
	/**
	 * @param params
	 */
	public void executeReasignacionManualEmpresarias(Map params);

	/**Realiza la actualizacion de datos desde el portal
	 * @param map
	 */
	public void saveActualizacionDatosPortal(Map map);
	
	/**
	 * Elimina los registros procesados anteriormente en la tabla temporal
	 * @param codigoUsuario
	 */
	public void deleteReporteClienteTemporal(String codigoUsuario);
	
	/**
	 * Inserta a la tabla temporal la busqueda realizada
	 * @param resultado
	 * @param usuario
	 */
	public void insertReporteClienteTemporal(List resultado, Usuario usuario);
	
	/**
	 * Verifica si la Region de la Sección ya proceso cierre de Region para la Campaña Actual
	 * @param criteria
	 * @return
	 */
	public boolean esRegionCerradaxSeccion(Map criteria);

	/**
	 * Obtienen el codigo de Tipo de Documento
	 * @param tipoDocumentoIdentidad
	 * @return
	 */
	public String getCodigoTipoDocLegal(String tipoDocumentoIdentidad);
	
	/**
	 * Obtiene el oid de Estado Civil
	 * 
	 * @param codigoEstadoCivil
	 * @return
	 */
	public Long getOidEstadoCivil(String codigoEstadoCivil);
	
	/**
	 * Obtiene el oid de Grado de Instruccion
	 * 
	 * @param codigoGradoInstruccion
	 * @return
	 */
	public Long getOidGradoInstruccion(String codigoGradoInstruccion);
	
	/**
	 * Obtiene el oid de Nacionalidad
	 * 
	 * @param codigoNacionalidad
	 * @return
	 */
	public Long getOidNacionalidad(String codigoNacionalidad);
	
	/**
	 * Obtiene el oid de Tratamiento
	 * 
	 * @param codigoNacionalidad
	 * @return
	 */
	public Long getOidTratamiento(String codigoTratamiento);
	
	/**
	 * Obtiene el oid de TipoCliente
	 * 
	 * @param codigoTipoCliente
	 * @return
	 */
	public Long getOidTipoCliente(String codigoTipoCliente);
	
	/**
	 * Obtiene el oid de SubTipoCliente
	 * 
	 * @param oidTipoCliente
	 * @param codigoSubTipoCliente
	 * @return
	 */
	public Long getOidSubTipoCliente(String oidTipoCliente, String codigoSubTipoCliente);

	/**
	 * Se obtiene el oidTipoDocumento relacionado a un tipo de documento
	 * 
	 * @param criteria
	 * @return
	 */
	public String getOidTipoDocumento(String oidPais, String tipoDocumento);

	/**
	 * Se verifica si existe la zona
	 * 
	 * @param criteria
	 * @return
	 */
	public String getExisteZona(String oidPais, String codigoZona);
	
	/**
	 * Se verifica si existe el territorio
	 * 
	 * @param criteria
	 * @return
	 */
	public String getExisteTerritorio(String oidPais, String codigoTerritorio);

	/**
	 * Obtiene la longitud de un tipo de documento de identidad
	 * 
	 * @param oidPais
	 * @param oidTipoDocumento
	 * @return
	 */
	public String getLongitudTipoDocumento(String oidPais, String oidTipoDocumento);

	/**
	 * Obtiene el codigo de periodo por medio del oid de periodo
	 * @param oidPeriodo
	 * @return
	 */
	public String getCodigoPeriodoByOidPeriodo(String oidPeriodo);

	/**
	 * Valida si el codigo CUB ya existe como Cliente
	 * @param codigoCUB
	 * @return
	 */
	public String getExisteClienteCUB(String codigoCUB);

	/**
	 * Devulve el valor y el estado concatenado.
	 * @param codigoPais , tipoValidacion
	 * @return
	 */
	public String getValorByPaisAndTipo(String codigoPais, String tipoValidacion);
	
	/**
	 * Actualiza Direccion y Unidad Administrativa de la pantalla de HiperConsulta
	 * 
	 * @param cliente
	 */
	public void updateDireccionClienteHiperConsulta(Cliente cliente) throws Exception;

	
	/**
	 * Permite generar el histórico de cambios de datos
	 * 
	 * @param clienteHistoricoDatos
	 */
	public void updateHistoricoDatosCliente(ClienteHistoricoDatos clienteHistoricoDatos) throws Exception;

	/**
	 * Valida si el cliente es Ejectuiva
	 * @param codigoCUB
	 * @return
	 */
	public boolean esEjecutiva(String oidCliente);

	/**
	 * Obtener el codigo de Region y Seccion relacionado a una Zona y Territorio
	 * 
	 * @param criteria
	 * @return
	 */
	public Map getCodigoRegionySeccion(String codigoZona, String codigoTerritorio);

	/**
	 * Valida si cliente ya existe
	 * 
	 * @param criteria
	 * @return
	 */
	public String getExisteCliente(Map criteria);

	/**
	 * Obtiene datos del cambio de UA, como el periodo Inicio y si paso Pedido la Consultora
	 * 
	 * @param codigoPais
	 * @param codigoMarca
	 * @param codigoCanal
	 * @param oidCliente
	 * @param codigoZonaOrigen
	 * @param codigoZonaDestino
	 * @return
	 */
	public Map obtenerDatosCambioUA(String codigoPais, String codigoMarca, String codigoCanal, String oidCliente, 
			String codigoZonaOrigen, String codigoZonaDestino);
	
	/**
	 * Obtiene Lista de Areas
	 * @param pais
	 * @return
	 */
	public List getAccesosTiposAreaByPais(String pais);

	/**
	 * Valida si Existe Pedidos que cumplan para ver si puede redifinir vigencia UA
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean existePedidosRedifinirVigenciaUA(Map criteria);

	/**
	 * Ejecuta el proceso que redifine vigencia de la Unidad Administrativa del Cliente
	 * 
	 * @param criteria
	 */
	public void executeRedifinirVigenciaUA(Map params);
	
	
	/**
	 * Obtiene Datos de Consultora
	 * @param criteria
	 * @return
	 */
	public List getObtenerBuscarConsultora(Map criteria);
	
	/**
	 * Obtiene Direcciones de la Consultora
	 * @param criteria
	 * @return
	 */
	public List getObtenerDireccionesConsultora(Map criteria);

	/**
	 * Obtiene los datos de los documentos de una consultora
	 * 
	 * @param docParmas
	 * @return
	 */
	public List getDocumentosConsultora(Map docParmas);
	
	/**
	 * Verifica si existe Lider
	 * @param criteria
	 * @return
	 */
	public Integer getExisteLider(Map criteria);
	
	
	/**
	 * Inserta Registro en la tabla de Lideres
	 * @param criteria
	 */
	public void insertLider(Map criteria);
	
	/**
	 * Verifica si existe Pago Lider
	 * @param criteria
	 * @return
	 */
	public Integer getExistePagoLider(Map criteria);
	
	/**
	 * Actualizar Registro en la tabla de Pago Lideres
	 * @param criteria
	 */
	public void updatePagoLiderContabilizarPago(Map criteria);
	
	public void updatePagoLiderContabilizarPagoSinMonto(Map criteria);

	/**
	 * Obtiene el numero de Periodos de Retiradas de un determinado pais
	 * 
	 * @param criteria
	 * @return
	 */
	public String getPeriodosRetiradas(String codigoPais);
	
	/**
	 * Actualiza el numero de Periodos de Retiradas de un determinado pais
	 * 
	 * @param params
	 */
	public void updatePeriodosRetiradas(Map params);
	
	/**
	 * Obtiene la lista de excenciones en base a los criterios de búsqueda
	 * @param params
	 * @return
	 */
	public List getExcencionesFletesByCriteria(Map params);

	/**
	 * Obtiene un registro en base a id 
	 * 
	 * @param id
	 * @return
	 */
	public ExcencionFlete getExcencionFlete(String id);

	/**
	 * Inserta un registro de excencion en la bd
	 * @param excencionFlete
	 * @param usuario
	 */
	public void insertExcencionFlete(ExcencionFlete excencionFlete, Usuario usuario);

	/**
	 * Actualiza un registro de excencion en la base de datos 
	 * @param excencionFlete
	 * @param usuario
	 */
	public void updateExcencionFlete(ExcencionFlete excencionFlete, Usuario usuario);
	
	/**
	 * Obtiene la lista de excenciones sobreflete en base a los criterios de búsqueda
	 * @param params
	 * @return
	 */
	public List getExcencionesSobreFletesByCriteria(Map params);

	/**
	 * Obtiene un registro en base a id
	 * @param id
	 * @return
	 */
	public ExcencionSobreFlete getExcencionSobreFlete(String id);
	
	/**
	 * Inserta un registro de excencion en la bd
	 * @param excencionSobreFlete
	 * @param usuario
	 */
	public void insertExcencionSobreFlete(ExcencionSobreFlete excencionSobreFlete, Usuario usuario);

	/**
	 * Actualiza un registro de excencion sobreflete en la base de datos 
	 * @param excencionSobreFlete
	 * @param usuario
	 */
	public void updateExcencionSobreFlete(ExcencionSobreFlete excencionSobreFlete, Usuario usuario);
	
	/**
	 * Valida si el cliente presenta pedidos en GP1, GP2 y GP3
	 * @param params
	 * @return
	 */
	public boolean validaClientePedido(Map params);
	
	/**
	 * Valida se debe dar el ingreso de telefono
	 * @param criteria
	 * @return
	 */
	public boolean validaIngresoTelefono(Map criteria);
	
	/**
	 * Valida si ya existe documento de indentidad sin importar el tipo
	 * @param criteria
	 * @return
	 */
	public String getValidaDocumentoIdentidad(Map criteria);
	
	
	public String getExisteCodigoProveedor(Map criteria);
	
	public void updatePagoLiderRegistarLider(Map criteria);

	public List getSubTiposDocumentoIdentidad(Map criteria);
	
	public boolean getMostrarSubTipoDocumentoIdentidad(Map criteria);
	/**
	 * Actualiza Datos de Direccion de Carga Masiva de Direcciones
	 * 
	 * @param cliente
	 * @throws Exception
	 */
	public void updateDireccionCargaMasiva(Cliente cliente) throws Exception;
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getEntidadBancoMaeList(Map criteria);
	
	/**
	 * @return
	 */
	public String getMostrarBancoCuentaCorriente(Map criteria);
	
	/**
	 * actualiza cliente Lider
	 * @param cliente
	 */
	public void updateClienteLider(Map criteria);
	
	public List getCodigosTerritorialCorresponde(Map criteria); 
	
	public List getBancos(Map criteria);
	
	public List getTiposCuenta(Map criteria);
	
	public String getMostrarTipoCuentaCuentaCorriente(Map criteria);
	
	/**
	 * Retorna Lista de direcciones del cliente
	 * @param oidCliente
	 * @return
	 */
	public List getListDireccionClienteCamposAdicionales(String oidCliente);
	
}
