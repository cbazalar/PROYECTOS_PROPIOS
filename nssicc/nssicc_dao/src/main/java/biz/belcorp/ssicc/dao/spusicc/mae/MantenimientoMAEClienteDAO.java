package biz.belcorp.ssicc.dao.spusicc.mae;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.mae.model.Cliente;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteAdicional;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteClasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteComunicacionOperadora;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteConcursoPremio;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteDireccion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteEncuesta;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoDatos;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteHistoricoEstatus;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteIdentificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.ClienteMarca;
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
import biz.belcorp.ssicc.dao.spusicc.mae.model.HistoricoClasificacion;
import biz.belcorp.ssicc.dao.spusicc.mae.model.SegmentoGrupoLove;

/**
 * @author peextsapaza
 *
 */
public interface MantenimientoMAEClienteDAO extends DAO {
	
public List getPeriodosVigentesByPaisMarcaCanal(Map criteria);
	
	public List getSubTiposClienteInsertar(Map criteria);

	public String getOidPais(Map criteria);
	
	
	public String getTipoClasificacionDefault(Map criteria);
	
	public String getClasificacionDefault(Map criteria);
	
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
	
	public List getUnidadesGeograficas(Map criteria);
	
	public List getNivelesGeograficos(Map criteria);

	public List getDocumentosCliente(Map criteria);
	
	public String getNumeroDocumentoPrincipal(String oidCliente);
	
	public List getMarcasTipoAbonoEntrada(Map criteria);
	
	public String getCuentasCastigadasCliente(Map criteria);
	
	public boolean esPaisModulo10(String codigoPais);
	public boolean esTipoDocumentoModulo10(Map criteria);
	
	public String getCodigoClienteAutomatico(Map criteria);
	
	public List getPesosModulo11();
	
	public String getUltimoNumeroSolicitud(Map criteria);
	
	public void updateUltimoNumeroSolicitud(Map criteria);
	
	public String getExisteZona(Map criteria);
	
	public String getExisteTerritorio(Map criteria);
	
	public String getExisteTerritorioEnZona(Map criteria);

	public String getExisteCodigoCliente(Map criteria);
	
	public List getTiposClasificaciones(Map criteria);
	
	public List getClasificaciones(Map criteria);
	
	public Long getOidCanal(String codigoCanal);
	public Long getOidMarca(String codigoMarca);
	public Long getOidFormaPagoPais(String oidPais);
	
	public Long getOidFormaPagoSubTipoCliente(Map criteria);
	public String getValorConfiCampoSubTipoCliente(Map criteria);
	
	public Long getOidTipoDireccion(String codigoTipoDireccion);
	public Long getOidTipoComunicacion(String codigoTipoComunicacion);
	public Long getOidTipoVinculo(Map criteria);
	public String getCriterioBusqueda1(String oidPais);
	public String getCriterioBusqueda2(String oidPais);
	
	public String getClienteVinculoDuplaCyzone(String codigoTipoVinculo, String oidClienteVinculante, String fechaActual);
	public void updateFechaHastaClienteVinculo(String oidClienteVinculo);
	public List getClienteSubTipo(String codigoTipoCliente, String oidCliente);
	public String getOidTipoClasificacion(String oidSubTipoCliente, String codigoTipoClasificacion);
	public String getOidClasificacion(String oidTipoClasificacion, String codigoClasificacion);
	public String getOidClienteClasificacion(String oidClienteSubTipo, String oidTipoClasificacion);
	public Map getCodigoUbigeo(String oidTerritorio);
	public void deleteMensajesDuplaCyzone(Map criteria);
	public Long getOidClienteTipoSubTipoMigracion(String oidPais);
	public String getLongitudTipoDocumento(String oidPais, String oidTipoDocumento);
	
	public Long getSecuenciaNextValueCliente();
	public Long getSecuenciaNextValueSubTipo();
	public Long getSecuenciaNextValueClasificacion();
	public Long getSecuenciaNextValueHistoricoStatus();
	
	public void insertCliente(Cliente cliente);
	public void insertClienteAdicional(ClienteAdicional clienteAdicional);
	public void insertClientePrimerContacto(ClientePrimerContacto clientePrimerContacto);
	public void insertClienteSubTipo(ClienteSubTipo clienteSubTipo);
	public void insertClienteClasificacion(ClienteClasificacion clienteClasificacion);
	public void insertClienteIdentificacion(ClienteIdentificacion clienteIdentificacion);
	public void insertClienteDireccion(ClienteDireccion clienteDireccion);
	public void insertClienteUnidadAdministrativa(ClienteUnidadAdministrativa clienteUnidadAdministrativa);
	public void insertClienteComunicacion(ClienteComunicacion clienteComunicacion);
	public void insertClienteVinculo(ClienteVinculo clienteVinculo);
	public void insertClienteMarca(ClienteMarca clienteMarca);
	public void insertClienteObservacion(ClienteObservacion clienteObservacion);
	public void insertClienteHistoricoEstatus(ClienteHistoricoEstatus clienteHistoricoEstatus);
	public void insertHistoricoClasificacion(HistoricoClasificacion historicoClasificacion);
	
		
	public List getClientesByCriteria(Map criteria);

	public List getDireccionesClientesByCriteria(Map criteria);

	public Cliente getExisteCodigoClienteByCodPais(Map criteria);
	
	public String getEdadMinima(Map criteria);
	
	public String getEdadMaxima(Map criteria);
	
	public String getExisteMensajeBuzon(Map criteria) ;
	public void insertBuzonMensajes(Map criteria);
	public List getConcursos(Map criteria);
	public List getPremios(Map criteria);
	public void insertRecomendante(ClienteConcursoPremio clienteConcursoPremio) ;	
	public String getOidRecomendante(Map criteria);
	public void insertRecomendado(ClienteConcursoPremio clienteConcursoPremio) ;
	public String getOidRecomendado(Map criteria) ;
	public List getSituaciones(Map criteria);
	/*
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
	 * Recupera la ultima fecha de actualizacion de la entidad Clientes Adicional
	 * 
	 * @param criteria
	 * @return
	 */
	public String getFechaUltimaActualizacionClienteAdicional(Map criteria);
	
	/**
	 * Recupera la ultima fecha de creacion de la entidad cliente nivel riesgo
	 * 
	 * @param criteria
	 * @return
	 */
	public String getFechaUltimaCreacionNivelRiesgoCliente(Map criteria);
	
	/**
	 * Inserta un nuevo registro en la entidad [Cliente Nivel Riesgo] 
	 *
	 * 
	 * @param criteria
	 */
	public void insertClienteNivelRiesgo(Map criteria);

	/**
	 * Actualiza el nivel de riesgo del Cliente en la entidad [Cliente Adicional]
	 * 
	 * @param criteria
	 */
	public void updateNivelRiesgoClienteAdicional(Map criteria);

	/**
	 * Obtiene la lista de zonas
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
	 * @param oidClienteSubTipo
	 * @return
	 */
	public List getListClasificacionCliente(String oidClienteSubTipo);

	/**
	 * Retorna Lista direccion Cliente
	 * @param oidCliente
	 * @return
	 */
	public List getListDireccionCliente(String oidCliente);
	
	/**
	 * Retorna Lista de comunicaciones
	 * @param oidCliente
	 * @return
	 */
	public List getListComunicacionCliente(String oidCliente);
	
	/**
	 * Retorna Unidad Administrativa
	 * @param oidCliente
	 * @return
	 */
	public ClienteUnidadAdministrativa getUnidadAdministrativaCliente(String oidCliente);
	
	/**
	 * Retorna Lista de Vinculos
	 * @param oidCliente
	 * @return
	 */
	public List getListVinculoCliente(String oidCliente);
	
	/**
	 * Retorna Lista de observaciones
	 * @param oidCliente
	 * @return
	 */
	public List getListObservacionCliente(String oidCliente);
	
	/**
	 * Actualiza datos basicos cliente
	 * @param cliente
	 */
	public void updateDatosBasicosCliente(Cliente cliente);
	
	/**
	 * Actualiza datos adicionales
	 * @param clienteAdiconal
	 */
	public void updateDatosAdicionalesCliente(ClienteAdicional clienteAdiconal);
	
	/**
	 * Actualiza primer contacto con el cliente
	 * @param clientePrimerContacto
	 */
	public void updatePrimerContactoCliente(ClientePrimerContacto clientePrimerContacto); 
	
	/**
	 * Actualiza Identificacion cliente
	 * @param clienteIdentificacion
	 */
	public void updateIdentificacionCliente(ClienteIdentificacion clienteIdentificacion);
	
	/**
	 * Elimina identificacion del cliente
	 * @param clienteIdentificacion
	 */
	public void deleteIdentificacionCliente(ClienteIdentificacion clienteIdentificacion);
	
	/**
	 * actualiza sub tipo cliente
	 * @param clienteSubTipo
	 */
	public void updateTipoSubtipoCliente(ClienteSubTipo clienteSubTipo);
	
	/**
	 * elimina subtipo cliente
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
	 * actualiza dirccion del cliente
	 * @param clienteDireccion
	 */
	public void updateDireccionCliente(ClienteDireccion clienteDireccion);

	/**
	 * elimina direccion del cliente
	 * @param clienteDireccion
	 */
	public void deleteDireccionCliente(ClienteDireccion clienteDireccion);
	
	/**
	 * actualiza comunicacion del cliente 
	 * @param clienteComunicacion
	 */
	public void updateComunicacionCliente(ClienteComunicacion clienteComunicacion);
	
	/**
	 * actualiza unidad administrativa cliente
	 * @param clienteUnidadAdministrativa
	 */
	public void updateUnidadAdministrativaCliente(ClienteUnidadAdministrativa clienteUnidadAdministrativa);
	
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
	 * devuelve oid en n campanhas
	 * @param map
	 * @return
	 */
	public String getDevuelveOidCampanha(Map map);

	/**
	 * elimina comunicacion cliente
	 * @param clienteComunicacion
	 */
	public void deleteComunicacionCliente(ClienteComunicacion clienteComunicacion);
	
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
	 * Obtiene el modulo correspondiente al tipo validacion seleccionado para el pais
	 * 
	 * @param codigoPais
	 * @param tipoValidacion
	 * @return
	 */
	public String getValorModuloxPaisTipoValidacion(String codigoPais, String tipoValidacion);

	/**
	 * Actualiza el premio del cliente recomendado
	 * 
	 * @param clienteConcursoPremio
	 */
	public void updateRecomendado(ClienteConcursoPremio clienteConcursoPremio);
		

	/**
	 * Retorna una lista con los vinculos del cliente
	 * @return
	 */
	public List getTipoVinculo();

	/**
	 * Inserta la referncia Familiar del cliente
	 * @param clienteReferencias
	 */
	public void insertClienteReferenciaFamiliar(ClienteReferencias clienteReferencias);

	/**
	 * Inssrta la referencia no familiar del cliente
	 * @param clienteReferencias
	 */
	public void insertClienteReferenciaNoFamiliar(ClienteReferencias clienteReferencias);

	/**
	 * Inserta el aval del cliente
	 * @param clienteReferencias
	 */
	public void insertClienteReferenciaAval(ClienteReferencias clienteReferencias);

	/**
	 * Obtenemos la lista de referencia del cliente
	 * @param map
	 * @return
	 */
	public List getListClienteReferencia(Map map);

	/**
	 * Actualiza la referencia familiar del cliente
	 * @param clienteReferencias
	 */
	public void updateReferenciasFamiliar(ClienteReferencias clienteReferencias);

	/**
	 * Actualiza la referencia no familiar del cliente
	 * @param clienteReferencias
	 */
	public void updateReferenciasNoFamiliar(ClienteReferencias clienteReferencias);

	/**
	 * Actualiza la referencia aval del cliente
	 * @param clienteReferencias
	 */
	public void updateReferenciasAval(ClienteReferencias clienteReferencias);

	/**
	 * Delete las referencias del cliente
	 * @param cliente
	 */
	public void deleteReferencias(ClienteReferencias clienteReferencias);

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
	 * Actualiza el indicador activo del cliente 
	 * 
	 * @param oidClienteVinculo
	 */
	public void updateIndicadorActivoClienteVinculo(String oidClienteVinculo);

	/**
	 * Recupera los Oids Recomendantes
	 * 
	 * @param criteria
	 * @return
	 */
	public List getOidsRecomendante(Map criteria);

	/**
	 * Elimina los registros de la entidad INC-Cliente-Recomendado
	 * 
	 * @param criteria
	 */
	public void deleteRecomendados(Map criteria);
	
	/**
	 * Calcula la cantidad de registros de la entidad INC-Cliente-Recomendado
	 * 
	 * @param oidRecomendante
	 * @return
	 */
	public String getTotalRecomendados(String oidRecomendante);
	
	/**
	 * Elimina el registro de la entidad INC-Cliente-Recomendante
	 * 
	 * @param oidRecomendante
	 */
	public void deleteRecomendante(String oidRecomendante);

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
	 * Actualizamos pedidos con nueva unidad administrativa
	 * 
	 * @param criteria
	 */
	public void updatePedidosConNuevaUnidadAdministrativa(Map criteria);
	
	/**
	 * Recuperamos Historico Estatus de acuerdo al cliente y periodo Fin
	 * 
	 * @param criteria
	 * @return
	 */
	public ClienteHistoricoEstatus getHistoricoEstatusPeriodoFin(Map criteria);
	
	/**
	 * Recuperamos Historico Estatus de acuerdo al cliente y periodo Inicio
	 * 
	 * @param criteria
	 * @return
	 */
	public ClienteHistoricoEstatus getHistoricoEstatusPeriodoInicio(Map criteria);
	
	/**
	 * Eliminamos los historicos de Clasificacion asociados al oid Historico Estatus
	 * 
	 * @param oidHistoricoEstatus
	 */
	public void deleteHistoricoClasificacion(String oidHistoricoEstatus);
	
	/**
	 * Eliminamos los historicos de estatus asociados al oid Historico Estatus
	 * 
	 * @param oidHistoricoEstatus
	 */
	public void deleteHistoricoEstatus(String oidHistoricoEstatus);
	
	/**
	 * Actualiza el estatus en la entidad de Historico Estatus
	 * 
	 * @param criteria
	 */
	public void updateHistoricoEstatus(Map criteria);
	
	/**
	 * Actualiza el estatus y numero de campañas sin pedido en la entidad Cliente Datos Adicionales
	 * 
	 * @param criteria
	 */
	public void updateDatosAdicionalesEstatus(Map criteria);

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
	 * Eliminamos los registros de Unidad Administrativa que comienza del periodo seleccionado
	 * 
	 * @param criteria
	 */
	public void deleteUnidadAdministrativaPeriodoInicio(String oidCliente, String oidPeriodo);

	/**
	 * Obtiene la unidad administrativa del cliente, ubicado por su periodo Fin
	 * 
	 * @param oidCliente
	 * @param oidPeriodoFin
	 * @return
	 */
	public ClienteUnidadAdministrativa getUnidadAdministrativaClientexPeriodoFin(String oidCliente, String oidPeriodoFin);

	/**
	 * Ejecuta el proceso que obtiene las consultras deudoras avaladas
	 * @param criteria
	 */
	public void executeValidacionDeudoraConsultoraAval(Map criteria);

	/**
	 * Retorna lista de cliente deudouras avaladas
	 * @param criteria
	 * @return
	 */
	public List getListConsultorasDeudorasAval(Map criteria);
	
	/**
	 * Obtiene el numero de pedidos que han sido enviados a pedidos
	 * @param map
	 * @return
	 */
	public Integer getSizePedidosEnviados(Map map);
	
	/**
	 * actualizamos las entidades de primer contacto, datos adicionales,
	 * historico de estatus, unidad administrativa del cliente, y borramos
	 * sus premios recomendados 
	 * 
	 * @param criteria
	 */
	public void executeRedifinirPeriodoIngreso(Map params);

	/**
	 * Verifica si el cliente es Potencial/Aval
	 * 
	 * @param criteria
	 * @return
	 */
	public Long getClientePotencialAval(String codigoCliente);

	/**
	 * Eliminamos las clasificaciones relacionados a un oidSubTipoCliente
	 * 
	 * @param oidSubTipoCliente
	 */
	public void deleteClasificaciones(Long oidSubTipoCliente);

	/**
	 * Eliminamos un oidSubTipoCliente
	 * 
	 * @param oidSubTipoCliente
	 */
	public void deleteSubTipoCliente(Long oidSubTipoCliente);
	
	/**
	 * Insert un registro en Historico de Clientes Avaladas
	 * 
	 * @param criteria
	 */
	public void insertClienteAval(Map criteria);

	/**
	 * Obtiene el tipo de Documento, para el segundo documento del Cliente
	 * 
	 * @param oidPais
	 * @return
	 */
	public String getSegundoTipoDocumento(String oidPais);
	
	/**
	 * Retorna objeto  si existe la preferencia d ecomunicacion nullo si no existe 
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
	 * Devuelve objeto si existe el tipo de operadora y nullo si no existe
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
	 * Retorna objeto si existe tipo de logro nullo si no existe tipo logro
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
	 * Recupera el nivel de Riesgo asociado al territorio Administrativo
	 * 
	 * @param criteria
	 * @return
	 */
	public String getNivelRiesgo(Map criteria);

	/**
	 * Obtiene los caracteres VALIDOS/NOVALIDOS relacionaods a un codigo de modulo de Validacion
	 * 
	 * @param criteria
	 * @return
	 */
	public List getCaracteresxModuloValidacion(Map criteria);

	/**
	 * actualizamos las entidades de unidad administrativa del cliente, y borramos
	 * sus premios recomendados
	 *  
	 * @param params
	 */
	public void executeRedifinirPeriodoIngresoRetiradas(Map params);

	/**
	 * Verificamos si la zona corresponde a uno de tipo Oficina
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean esZonaOficina(Map criteria);
	
	/**
	 * Verificamos si la zona está Inactiva
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean esZonaInactiva(Map criteria);

	/**
	 * Valida si el concurso de recomendacion de la Recomendada esta vigente
	 * 
	 * @param criteria
	 * @return
	 */
	public String getValidacionVigenciaRecomendacion(Map criteria);

	/**
	 * Se inserta varias registros en tablas de incentivos relacionados al Recomendante y a la Recomendada
	 * 
	 * @param criteria
	 */
	public void executeRehacerTablasIncentivos(Map criteria);

	/**
	 * Elimina los registros de la entidad INC-Cliente-Recomendado y sus dependencias
	 * 
	 * @param criteria
	 */
	public void executeDeleteRecomendados(Map criteria);

	/**
	 * Elimina el registro de la entidad INC-Cliente-Recomendante y sus dependencias
	 * 
	 * @param criteria
	 */
	public void executeDeleteRecomendante(String oidRecomendante);

	/**
	 * Obtenemos el Oid Periodo de Recomendacion del cliente
	 * 
	 * @param criteria
	 * @return
	 */
	public String getPeriodoRecomendacion(Map criteria);

	/**
	 * Devulve numero de documento identidad principal
	 * @param oidCliente
	 * @return
	 */
	public String getDocumentoIdentidad(String oidCliente);

	/**
	 * Devulve las zopnas activas del titular segun paramtro
	 * @param criteria
	 * @return
	 */
	public List getZonasDirectorio(Map criteria);
	
	/**
	 * Devulve las zopnas activas del titular segun paramtro
	 * @param criteria
	 * @return
	 */
	public List getZonasDirectorioCargo(Map criteria);

	/**
	 * Devulve las regiones activas del titular segun parametro
	 * @param criteria
	 * @return
	 */
	public List getRegionesDirectorio(Map criteria);
	
	/**
	 * Devulve las regiones activas del titular segun parametro ZON
	 * @param criteria
	 * @return
	 */
	public List getRegionesDirectorioCargo(Map criteria);
	
	/**
	 * Devulve las regiones activas que poseen zonas sin gerente asignado
	 * @param criteria
	 * @return
	 */
	public List getRegionesDirectorioCargoGZ(Map criteria);

	/**
	 * Obtiene lista de Cutis
	 * 
	 * @param criteria
	 * @return
	 */
	public List getTipoCutis(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	public List getRegionesDirectorioZON(Map criteria);
	
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
	 * Obtenemos el ultimo Historico Estatus de la Consultora
	 * 
	 * @param criteria
	 * @return
	 */
	public ClienteHistoricoEstatus getUltimoHistoricoEstatus(String oidCliente);

	/**
	 * Actualiza Primer Contacto de la Consultora 
	 * 
	 * @param oidCliente
	 */
	public void executeActualizarPrimerContacto(String oidCliente);

	/**
	 * Actualiza los niveles de riesgo
	 * @param params
	 */
	public void executeActualizarNivelesRiesgo(Map params);

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
	
	/* INI SA PER-SiCC-2012-0459 */
	/**
	 * Obtiene la lista de Ciudades
	 * 
	 * @param criteria
	 * @return
	 */
	public List getCiudades(Map criteria);
	/* FIN SA PER-SiCC-2012-0459 */

	/* INI SA PER-SiCC-2012-0265 */
	/**
	 * Verifica si el numero de Documento es valido para aplicar el MOD11V
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean validarNumeroDocumentoMod11V(String numeroDocumento);
	
	/**
	 * Se obtiene el oidTipoDocumento relacionado a un tipo de documento
	 * 
	 * @param criteria
	 * @return
	 */
	public String getOidTipoDocumento(Map criteria);
	/* FIN SA PER-SiCC-2012-0265 */
	
	/**
	 * Se obtiene el oidTipoDocumento relacionado a un tipo de documento
	 * 
	 * @param criteria
	 * @return
	 */
	public String getOidTipoDocumento1(Map criteria);

	/* INI SA PER-SiCC-2012-0535 */
	/**
	 * Obtiene la ultima Secuencia para la creacion del codigo de Cliente
	 * @param oidCliente
	 * @return
	 */
	public String getSecuenciaCodigoCliente(String codigoPais);
	/* FIN SA PER-SiCC-2012-0535 */

	/* INI SA PER-SiCC-2012-0580 */
	/**
	 * Valida si el cliente tiene pedidos en proceso
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean validaPedidosEnProceso(Map criteria);
	/* FIN SA PER-SiCC-2012-0580 */
	
	/* INI JJ PER-SiCC-2012-0329 */
	/**
	 * Valida si el codigo CUB ya existe
	 * @param criteria
	 * @return
	 */
	public String getExisteCodigoCUB(Map criteria);
	/* FIN JJ PER-SiCC-2012-0329 */

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
	 * Ejecuta Actualizacion de Clasificacion de Vacaciones a la consultora
	 * 
	 * @param criteria
	 */
	public void executeActualizacionClasificacionVacaciones(Map params);
	
	/**
	 * Ejecuta la Eliminacion de Clasificacion de Vacaciones a la consultora
	 * 
	 * @param criteria
	 */
	public void executeEliminarClasificacionVacaciones(Map params);

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

	/* INI SA PER-SiCC-2012-0138 */
	/**
	 * Valida si es posible el bloqueo/desbloqueo para un determinado cliente
	 * @param map
	 * @return
	 */
	public String getValidarBloqueoDesbloqueoCliente(Map map);
	
	/**
	 * Actualiza los datos de desbloqueo de un cliente
	 * @param params
	 */
	public void updateDesbloqueoCliente(Map params);
	/* FIN SA PER-SiCC-2012-0138 */

	/* INI SA PER-SiCC-2012-0367 */
	/**
	 * Inserta una solicitud Credito rechazado (STO)
	 * 
	 * @param params
	 */
	public void insertSolicitudCreditoRechazado(Map params);

	/**
	 * Inserta un Documento Digitacion (STO)
	 * 
	 * @param params
	 */
	public void insertDocumentoDigitacion(Map params);

	/**
	 * Inserta un Detalle Excepcion (STO)
	 * 
	 * @param params
	 */
	public void insertDetalleExcepcion(Map params);

	/**
	 * Realiza la Generacion de la Solicitud de Credito Rechazada para el caso de cliente Inactivo
	 * 
	 * @param criteria
	 */
	public void executeGenerarSolicitudCreditoRechazada(Map params);
	/* FIN SA PER-SiCC-2012-0367 */

	public boolean getObtieneTipoValidacion(Map params);

	public String getValidacionDocumentoIdentidad(String numeroIdentidad);

	/**
	 * @param criteria
	 * @return
	 */
	public Map getDatosCliente(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public Map getDatosEmprendedoraCliente(Map criteria);
	
	/**
	 * @param criteria
	 * @return
	 */
	public List getMotivosBaja(Map criteria);
	
	/**
	 * @param params
	 */
	public void executeBajaManualEmpresarias(Map params);
	
	/**
	 * @param map
	 * @return
	 */
	public String getZonaPeriodoCerrada(Map map);
	
	/**
	 * @param params
	 */
	public void executeReasignacionManualEmpresarias(Map params);

	/**
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
	 * Devuelve las zonas activas
	 * @param criteria
	 * @return
	 */
	public List getZonasDirectorioActivas(Map criteria);

	/**
	 * Devulve las regiones activas que poseen zonas sin gerente asignado, para los paises FOX
	 * @param criteria
	 * @return
	 */
	public List getRegionesDirectorioCargoGZFOX(Map criteria);

	/**
	 * Devulve las regiones activas del titular segun parametro ZON, para paises FOX
	 * @param criteria
	 * @return
	 */
	public List getRegionesDirectorioCargoFOX(Map criteria);

	/**
	 * Devuelve las zonas activas de los paises FOX
	 * @param criteria
	 * @return
	 */
	public List getZonasDirectorioActivasFOX(Map criteria);

	/**
	 * Devulve las zopnas activas del titular segun paramtro, para paises FOX
	 * @param criteria
	 * @return
	 */
	public List getZonasDirectorioCargoFOX(Map criteria);
	
	/**
	 * Devuelve la lista de palabras compuestas
	 * @param criteria
	 * @return
	 */
	public List getPalabrasCompuestasList(Map criteria);
	
	/**
	 * Devuelve la lista de palabras altisonantes
	 * @param criteria
	 * @return
	 */
	public List getPalabrasAltisonantesList(Map criteria);
	
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
	 * Permite generar el histórico de cambios de datos 
	 * 
	 * @param clienteHistoricoDatos
	 */
	public void executeInsercionHistoricoDatos(ClienteHistoricoDatos clienteHistoricoDatos);

	/**
	 * Valida si el cliente es Ejectuiva
	 * @param codigoCUB
	 * @return
	 */
	public boolean esEjecutiva(String oidCliente);

	/**
	 * Se realiza actualizacion de datos de la Ejecutiva
	 * 
	 * @param params
	 */
	public void executeActualizacionEjecutiva(Map params);

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
	 * Verifica si el numero de Documento es valido para aplicar el MOD10
	 * 
	 * @param criteria
	 * @return
	 */
	public String getValidarNumeroDocumentoMod10(String numeroDocumento);

	/**
	 * Ejecuta el proceso que Calcular Estatus de Cliente
	 * 
	 * @param criteria
	 */
	public void executeCalcularEstatusCliente(Map params);
	
	/**
	 * Obtiene Lista de Areas
	 * @param pais
	 * @return
	 */
	public List getAccesosTiposAreaByPais(String pais);
	
	/**
	 * Obtiene Descripcion de Tipo de Bloqueo
	 * @param criteria
	 * @return
	 */
	public String getDescripcionTipoBloqueo(Map criteria);
	
	/**
	 * Devuelve Descripcion del Bloqueo para la Cliente
	 * @param criteria
	 * @return
	 */
	public String getDevuelveBloqueoDesbloqueoCliente(Map criteria);
	
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
	 * Proceso que verifica si la Region o la Zona se encuentra cerrada en la campaa activa 
	 * @param params
	 * @return
	 */
	public Integer getVerificarRegionZonaCerradaCampannaActiva(Map params);

	/**
	 * Devuelve el resultado de las validaciones por regularizacion
	 * @param criteria
	 * @return
	 */
	public Integer getValidarRegularizacion(Map criteria);

	/**
	 * Actualiza la regularizacin del cliente
	 * @param criteria
	 */
	public void updateRegularizacion(Map criteria);
	
	
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
	 * Valida si el numero de ruc cumple con el modulo 11
	 * @param numeroDocumentoIdentidad
	 * @return
	 */
	public Integer validarNumeroRucModulo11(String numeroDocumentoIdentidad);
	
	/**
	 * Valida Carnet Identidad
	 * @param numeroDocumentoIdentidad
	 * @return
	 */
	public String validarNumeroCarnetIdentidad(String numeroDocumentoIdentidad);
	
	/**
	 * Obtiene los datos de los documentos de una consultora
	 * 
	 * @param docParmas
	 * @return
	 */
	public List getDocumentosConsultora(Map docParmas);

	/**
	 * Recupera el digito de control para el modulo PTR
	 * 
	 * @param criteria
	 * @return
	 */
	public String getDigitoControlModuloPTR(String codigo);
	
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
	 * Devuelve los Parametro por indBloqClienPreFacturacion
	 * @param criteria
	 * @return
	 */
	public String getBloqueoClientePreFacturacion(Map criteria);
	
	/**
	 * Devuelve si el cliente tiene procesos de compra en GP1, GP2, GP3
	 * @param criteria
	 * @return
	 */
	public String getClientePedido(Map criteria);
	
	/**
	 * Devuelve si existe el indicador de valida ingreso de telefono activo
	 * @param criteria
	 * @return
	 */
	public String getValidaIngresoTelefono(Map criteria);
	
	
	/**
	 * 
	 * @param oid
	 * @return
	 */
	public List getListPrimerContacto(Long oid);
	
	
	/**
	 * 
	 * @param oid
	 * @return
	 */
	public ClientePrimerContacto getPrimerContactoOid(Long oid); 
	
	/**
	 * 
	 * @param clienteSubTipo
	 */
	public void deletePrimerContactoHijaDupla(ClienteSubTipo clienteSubTipo);
	
	/**
	 * Valida si ya existe documento de indentidad sin importar el tipo
	 * @param criteria
	 * @return
	 */
	public String getValidaDocumentoIdentidad(Map criteria);

	
	public String getExisteCodigoProveedor(Map criteria);
	
	public void updatePagoLiderRegistarLider(Map criteria);

	public List getSubTiposDocumentoIdentidad(Map criteria);
	
	public String getMostrarSubTipoDocumentoIdentidad(Map criteria);
	
	public List getEntidadBancoMaeList(Map criteria);
	
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
