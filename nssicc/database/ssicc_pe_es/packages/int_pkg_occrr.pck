CREATE OR REPLACE PACKAGE "INT_PKG_OCCRR" IS
  /* Declaracion de variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  w_filas    NUMBER := 5000;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Maestros de Clientes de OCR
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_maest_clie
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Matriz de Facturacion
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Matriz de Facturacion
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_matri_factu
  (
    pscodigopais         VARCHAR2,
    pscodigosistema      VARCHAR2,
    pscodigointerfaz     VARCHAR2,
    psnombrearchivo      VARCHAR2,
    psnumerolote         VARCHAR2,
    pscodigomarca        VARCHAR2,
    pscodigocanal        VARCHAR2,
    pscodigoperiododesde VARCHAR2,
    pscodigoperiodohasta VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Rangos de Periodo de Vigente
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_rango_perio
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Tabla de Regiones
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_tabla_regio
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Tabla de Zonas
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_tabla_zonas
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Devuelve le codigo de Compa?ia
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_ocr_devue_codig_compa(pscodigopais VARCHAR2)
    RETURN VARCHAR2;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Cabeceras de OCR
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_conso_cabec
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Detalle de OCR
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_conso_detal
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
  );
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Cupon de pago
  Fecha Creacion    : 28/03/2008
  Autor             : Dennys Oliva Iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_cupon_pago
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  );
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de DWH OCR
  Fecha Creacion    : 28/03/2008
  Autor             : Dennys Oliva Iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_dwh_ocr
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Ficha de inscripcion Privilege
  Fecha Creacion    : 28/03/2008
  Autor             : Dennys Oliva Iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_ficha_privi
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Cabeceras de Servicios Postventas
  Fecha Creacion    : 28/03/2008
  Autor             : Dennys Oliva Iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_posve_cabec
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psnumerolote     VARCHAR2
  );
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Detalles de Servicios Postventas
  Fecha Creacion    : 28/03/2008
  Autor             : Dennys Oliva Iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_posve_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psnumerolote     VARCHAR2
  );
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion Solicitud de Credito
  Fecha Creacion    : 31/03/2008
  Autor             : Leonardo Lizana
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_solic_credi
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  );
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion Seguimiento al Pedido
  Fecha Creacion    : 31/03/2008
  Autor             : Leonardo Lizana
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_segui_pedid
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion Arribo de Zonas
  Fecha Creacion    : 31/03/2008
  Autor             : Leonardo Lizana
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_arrib_zonas
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion Solicitud Premios Privilege Detalle
  Fecha Creacion    : 31/03/2008
  Autor             : Leonardo Lizana
                      SOLPP
                      -----
                      SOL ICITUD
                      P REMIOS
                      P RIVILEGE
  **************************************************************************************/

  PROCEDURE int_pr_ocr_recep_solpp_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Actualizacion
                      de Datos
  Fecha Creacion    : 01/04/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_actua_datos
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Pagare
  Fecha Creacion    : 01/04/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_pagar
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Dupla Cyzone
  Fecha Creacion    : 01/04/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_dupla_cyzon
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo Control envio OCR
                      a SICC
  Fecha Creacion    : 01/04/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_multi_ctrl
  (
    pscodigopais              VARCHAR2,
    pscodigosistema           VARCHAR2,
    pscodigointerfaz          VARCHAR2,
    psnombrearchivo           VARCHAR2,
    psnumlotearchivo          VARCHAR2,
    psnumloteinterfaz         VARCHAR2,
    pscodigointerfazcompuesta VARCHAR2,
    psoidproceso              VARCHAR2,
    pscodigousuario           VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Solicitud Premios Privilege Cabecera
  Fecha Creacion    : 01/04/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_solpp_cabec
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Solicitudes de Credito
  Fecha Creacion    : 28/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_solic_credi
  (
    pscodigopais      VARCHAR2,
    pscodigousuario   VARCHAR2,
    psnumlote         VARCHAR2,
    pscodigodocumento VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Servicio Postventas
  Fecha Creacion    : 30/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_post_venta_cabec
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Consolida el detalle de Servicio Postventas
  Fecha Creacion    : 30/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_post_venta_detal
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Actualizacion de Datos
  Fecha Creacion    : 17/06/2008
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_actua_datos
  (
    pscodigopais      VARCHAR2,
    pscodigousuario   VARCHAR2,
    psnumlote         VARCHAR2,
    pscodigodocumento VARCHAR2
    
  );
  /***************************************************************************
  Descripcion       : Devuelve una fecha en formato date
                      si no tiene el formato devuelve null
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_fecha
  (
    psfecha   VARCHAR2,
    psformato VARCHAR2
  ) RETURN DATE;

  /***************************************************************************
  Descripcion       : Genera Interfase de OCS Web/DD Cabeceras
  Fecha Creacion    : 27/01/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_webdd_cabec
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pstiposolicitud   VARCHAR2,
    pscodigosubacceso VARCHAR2,
    pscodigoacceso    VARCHAR2,
    pstipodespacho    VARCHAR2,
    psstatusproceso   VARCHAR2,
    psfechademanda    VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interface de OCS Web/DD Cabeceras
  Fecha Creacion    : 27/01/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_wdpro_cabec
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pstiposolicitud   VARCHAR2,
    pscodigosubacceso VARCHAR2,
    pscodigoacceso    VARCHAR2,
    pstipodespacho    VARCHAR2,
    psstatusproceso   VARCHAR2,
    psfechademanda    VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psIndicadorDA     VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfase de OCS Web/DD Detalles
  Fecha Creacion    : 27/01/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_webdd_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pstipoposicion   VARCHAR2,
    psstatusproceso  VARCHAR2,
    psnumerolote     VARCHAR2,
    psnumerolotesto  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Dupla Cyzone
  Fecha Creacion    : 04/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_dupla_cyzon
  (
    pscodigopais      VARCHAR2,
    pscodigousuario   VARCHAR2,
    psnumlote         VARCHAR2,
    pscodigodocumento VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Consolida los pedidos WEB- DD - OCR
  Fecha Creacion    : 25/10/2011
  Parameters        :
    pscodigopais      Pais
    pscodigodocumento Documento STO 'OCC' Orden de Compra
    pscodigousuario   Usuario
    psnumeroLoteSTO   Lote Generado por de Carga STO
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_solic_conso_pedid
  (
    pscodigopais      VARCHAR2,
    pscodigodocumento VARCHAR2,
    pscodigousuario   VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Registra laos pedidos antes de consolidarlos
  Fecha Creacion    : 06/02/2008
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_pedid_befor_conso
  (
    pscodigopais    VARCHAR2,
    pscodigousuario VARCHAR2,
    psnumerolotesto VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Cupon Pago
  Fecha Creacion    : 16/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_cupon_pago
  (
    pscodigopais          VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2,
    pscodigotipodocumento VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Consolida las Solicitudes Servicio Postventas
  Fecha Creacion    : 23/02/2009
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_post_venta
  (
    pscodigopais      VARCHAR2,
    pscodigousuario   VARCHAR2,
    psnumlote         VARCHAR2,
    pscodigodocumento VARCHAR2
  );
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion Solicitud de Credito Corporativa
  Fecha Creacion    : 06/04/2009
  Autor             : Dennys Oliva iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_socre_corpo
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  );
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion Solicitud de Credito Web
  Fecha Creacion    : 07/04/2009
  Autor             : Dennys Oliva iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_socre_web
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Actualizacion de Datos Corporativa
                      de Datos
  Fecha Creacion    : 13/04/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_acdat_corpo
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  );
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Detalles Accion de Servicios Postventas
  Fecha Creacion    : 16/04/2009
  Autor             : Cristhian Roman
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_posve_deacc
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psnumerolote     VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Actualizacion de Datos Web
                      de Datos
  Fecha Creacion    : 21/04/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_acdat_web
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de cliente a web
  Fecha Creacion    : 19/06/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_clien_web
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de territorios web
  Fecha Creacion    : 15/09/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_terri_web
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de  segmentos web
  Fecha Creacion    : 15/09/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_segme_web
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Actualiza el codigo de cliente de las tablas INT_SOLIC_CABEC
                      y  INT_SOLIC_POSIC si existen en MAE_CLIEN, la comparacion
                      de codigos es numerica
  Fecha Creacion    : 31/01/2012
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_actua_clien
  (
    pscodigopais      VARCHAR2,
    psindactuacliente VARCHAR2,
    psnumerolotesto   VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Actualiza el codigo de cliente de las tablas INT_SOLIC_CABEC
                      e INT_SOLIC_POSIC si existen en un preimpreso anterior o
                      si existe el documento de identidad
  Fecha Creacion    : 29/09/2010
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_actua_clien_valid_pedid
  (
    pscodigopais      VARCHAR2,
    psindactuacliente VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Devuelve le codigo de Compa?ia de la tabla BAS_PAIS_COMPA
  Fecha Creacion    : 29/09/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION int_fn_ocr_devue_compa_basco(pscodigopais VARCHAR2)
    RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Procedimiento que consolida los detalles del pedido
                      considerando los detalles del lote anteriormente cargado
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
  Fecha Creacion    : 20/04/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ocr_conso_detal_lotes
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psnumerolotesto VARCHAR2
  );

  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Codigos de venta rechazados
  Fecha Creacion    : 13/07/2010
  Autor             : Dennys Oliva Iriarte
  **************************************************************************************/
  PROCEDURE int_pr_recep_codig_recha_ocs
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psusuario        VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Interface Recepcion de Ordenes de Transporte
  Fecha Creacion    : 07/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_orden_trans
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Consolida las Ordenes de Transporte
  Fecha Creacion    : 07/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_orden_trans
  (
    pscodigopais          VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2,
    pscodigotipodocumento VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Ordenes de transporte del
                      proceso de facturacion
  Fecha Creacion    : 14/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_envi_orden_trans_factu
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psfechafacturacion VARCHAR2,
    pscodigoperiodo    VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Ordenes de transporte
                      anuladas
  Fecha Creacion    : 14/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_envi_orden_trans_anula
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psfechafacturacion VARCHAR2,
    pscodigoperiodo    VARCHAR2
  );
  /***************************************************************************
  Descripcion         : Genera interface de retorno de los codigos asignados
    Fecha Creacion    : 24/02/2011
  Autor               : Christian Luque
  ***************************************************************************/
  PROCEDURE ocr_pr_envio_retor_codas
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Ingreso
                      de Metas
  Fecha Creacion    : 01/03/2011
  Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_ingre_metas
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Ingreso de Metas
  Fecha Creacion    : 01/03/2011
  Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_ingre_metas
  (
    pscodigopais          VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2,
    pscodigotipodocumento VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Familia Segura
  Fecha Creacion    : 02/05/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_famil_segur
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  );

  /******************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Familia Segura WEB
  Fecha Creacion    : 20/10/2012
  Autor             : Jorge Florencio
  *********************************************************************************/
  PROCEDURE int_pr_web_recep_famil_segur
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Familia Segura
  Fecha Creacion    : 02/05/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_famil_segur
  (
    pscodigopais          VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2,
    pscodigotipodocumento VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de MICA Web
  Fecha Creacion    : 22/06/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_micaw_cabec
  (
    pscodigopais      VARCHAR2,
    pscodigodocumento VARCHAR2,
    pscodigousuario   VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Consolida los Detalles de MICA Web
  Fecha Creacion    : 22/06/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_micaw_detal
  (
    pscodigopais      VARCHAR2,
    pscodigodocumento VARCHAR2,
    pscodigousuario   VARCHAR2
  );
  /**************************************************************************
    Descripcion       : ocr_pr_carga_tempo_pedid_digit
                        Inserta Cabeceras Detalles hacia temporales de Pedidos
    Fecha Creacion    : 26/02/2008
    Parametros Entrada:
        psCodigoPais : Codigo de pais
        psCodigoPeriodo : Codigo de periodo
        psUsuario : Codigo de Usuario
        psnumerolotesto   Lote generado por STO,
        psindicadororigen Origen (DIGITADO ->G),
        pscodigointerfaz  codigo de Interfaz,
        psnumerolote      numero Lote de interfaz.
    Autor             : Jose Cairampoma
  ****************************************************************************/
  PROCEDURE ocr_pr_carga_tempo_pedid_digit
  (
    pscodigopais      VARCHAR2,
    pscodigoperiodo   VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnumerolote      VARCHAR2
  );
  /**************************************************************************
    Descripcion       : ocr_pr_marca_pedid_digit
                        Marca los Pedidos Digitados que fueron Consolidados
    Fecha Creacion    : 27/02/2008
    Parametros Entrada:
        psCodigoPeriodo : Codigo de periodo
        psnumerolotesto   Lote generado por STO,
  
    Autor             : Jose Cairampoma
  ****************************************************************************/
  PROCEDURE ocr_pr_marca_pedid_digit
  (
    pscodigoperiodo VARCHAR2,
    psnumerolotesto VARCHAR2
  );

  /**************************************************************************
    Descripcion       : Elimina los pedidos duplicados que llegan por WEB y DD
    Fecha Creacion    : 09/02/2012
    Autor             : Jose Luis Rodriguez
  ****************************************************************************/
  PROCEDURE ocr_pr_elimi_pedid_dupli_webdd
  (
    pscodigopais    VARCHAR2,
    psnumerolotesto VARCHAR2
  );
  /**************************************************************************
    Descripcion       : Elimina los pedidos con preimpreso duplicado
    Fecha Creacion    : 22/04/2013
    Autor             : Jose Cairampoma
  ****************************************************************************/
  PROCEDURE ocr_pr_elimi_pedid_dupli_preim
  (
    pscodigopais    VARCHAR2,
    psnumerolotesto VARCHAR2
  );
  /***************************************************************************
    Descripcion       : Genera Interfaz de Recepcion de Cabeceras de OCR
                        Flexipago
    Fecha Creacion    : 21/05/2012
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_conso_cabec_flexi
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Genera el Numero de documento correlativo
                        para la cabecera y el detalle de post venta
                        Flexipago
    Fecha Creacion    : 11/06/2012
    Autor             : Jorge Luis Velasquez
  ***************************************************************************/
  PROCEDURE ocr_pr_numdocu_post_venta(psnumlote VARCHAR2);

  /***************************************************************************
    Descripcion       : Genera el envio de correo
    Fecha Creacion    : 16/07/2012
    Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE ocr_pr_envio_mail
  (
    pscodigopais     VARCHAR2,
    pscodigointerfaz VARCHAR2,
    pscompletado     VARCHAR2,
    mensaje          VARCHAR2,
    psnumerolote     VARCHAR2,
    codigousuario    VARCHAR2,
    psnomreporte     VARCHAR2
  );

  /***************************************************************************
   Archivo           : 1. MAE_PRODU.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_maest_produ_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Archivo           : 2. GEN_I18N_SICC_PAIS.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_gen_sicc_pais_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
    Archivo           : 3. PRE_OFERT_DETAL.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/

  PROCEDURE int_pr_ocr_pre_ofer_deta_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Archivo           : 4. PRE_OFERT.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_pre_ofer_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Archivo           : 4. PRE_CATAL.TXT
   Fecha Creacion    : 26/04/2013
  Autor             : Sebastian Guerra
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_prec_cata_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Archivo           : 5. PRE_MATRI_FACTU.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_matri_factu_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Archivo           : 6. PRE_RANGO_PROMO.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_rango_promo_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Archivo           : 7. PRE_GRUPO_OFERT.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_grupo_ofert_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Archivo           : 8. PRE_MATRI_REEMP.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_matri_reemp_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Archivo           : 9. PRE_PROMO.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_pre_promo_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Archivo           : 10. CRA_PERIO.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_cra_perio_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Archivo           : 11. PRE_MATRI_FACTU_CABEC.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_factu_cabec_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Archivo           : 12. PED_GESTI_STOCK.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_gesti_stock_prol
  
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Archivo           : 13. LIMITE_VENTA.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_limite_venta_prol
  
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /******************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Contrato Ejecutivas OCR-76
  Fecha Creacion    : 28/01/2013
  Autor             : Sergio Buchelli
  *********************************************************************************/
  PROCEDURE int_pr_ocr_contr_ejecu
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Consolida Contrato ejecutivas
  Fecha Creacion    : 29/01/2013
  Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE int_pr_ocr_conso_ejecu
  (
    pscodigopais      VARCHAR2,
    pscodigousuario   VARCHAR2,
    psnumlote         VARCHAR2,
    pscodigodocumento VARCHAR2
  );
  /***************************************************************************
   Archivo           : Envio de Informacion de Nuevos Faltantes
   Fecha Creacion    : 05/02/2013
   Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envia_nuevo_falta
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Descripcion       : Envio de Cupones - Prol
   Fecha Creacion    : 08/05/2013
   Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_cup_cupon_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Descripcion       : Envio de Cupones de Homologacion - Prol
   Fecha Creacion    : 08/05/2013
   Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_cup_homol_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
   Descripcion       : Envio de Cupones de Participantes - Prol
   Fecha Creacion    : 08/05/2013
   Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_cup_parti_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /*****************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Carta Invitacion Flexipago
  Fecha Creacion    : 09/05/2013
  Autor             : Sebastian Guerra
  *****************************************************************************/
  PROCEDURE int_pr_ocr_recep_carta_flexi
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psnumerolote     VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Consolida las Cartas de Invitación Flexipago
  Fecha Creacion    : 10/05/2013
  Autor             : Sebastian Guerra
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_carta_flexi
  (
    pscodigopais          VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2,
    pscodigotipodocumento VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Boletas de Entrega
  Fecha Creacion    : 18/06/2013
  Autor             : Ivan Tocto Jaimes
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_bolet_entre
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Boletas de Recojo
  Fecha Creacion    : 18/06/2013
  Autor             : Ivan Tocto Jaimes
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_bolet_recoj
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  );

END int_pkg_occrr;
/
CREATE OR REPLACE PACKAGE BODY "INT_PKG_OCCRR" IS
  /***************************************************************************
  INT_SOLIC_WEBDD_DETAL Clientes de OCR
    Fecha Creacion    : 09/03/2008INT_SOLIC_WEBDD_DETAL
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_maest_clie
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz
    (
      vscodigocia     VARCHAR2,
      vscodigopaisocr VARCHAR2
    ) IS
      SELECT vscodigopaisocr codigopais,
             vscodigocia codigocia,
             c.cod_clie codigocliente,
             c.val_ape1 apellido1,
             c.val_ape2 apellido2,
             c.val_nom1 nombre1,
             c.val_nom2 nombre2,
             gen_pkg_gener.gen_fn_clien_datos_oid(c.oid_clie,
                                                  'COD_SUBG_VENT') codigosubgerencia,
             gen_pkg_gener.gen_fn_clien_datos_oid(c.oid_clie, 'COD_REGI') codigoregion,
             gen_pkg_gener.gen_fn_clien_datos_oid(c.oid_clie, 'COD_ZONA') codigozona,
             gen_pkg_gener.gen_fn_clien_datos_oid(c.oid_clie, 'COD_TERR') codigoterritorio,
             fin_pkg_gener.fin_fn_obtie_numer_docum_ident(c.oid_clie) dociden
      
        FROM mae_clien             c,
             mae_clien_unida_admin u,
             mae_clien_datos_adici a
       WHERE c.oid_clie = u.clie_oid_clie
         AND a.clie_oid_clie = c.oid_clie
         AND u.ind_acti = 1
         AND a.ind_acti = 1
         AND a.esta_oid_esta_clie <> 7;
  
    TYPE interfazrec IS RECORD(
      codigopais         seg_pais.cod_pais%TYPE,
      codigocia          int_ivr_param_compa.cod_comp%TYPE,
      codigocliente      mae_clien.cod_clie%TYPE,
      apellido1          mae_clien.val_ape1%TYPE,
      apellido2          mae_clien.val_ape2%TYPE,
      nombre1            mae_clien.val_nom1%TYPE,
      nombre2            mae_clien.val_nom2%TYPE,
      codigosubgerencia  zon_sub_geren_venta.cod_subg_vent%TYPE,
      codigoregion       zon_regio.cod_regi%TYPE,
      codigozona         zon_zona.cod_zona%TYPE,
      codigoterritorio   zon_terri.cod_terr%TYPE,
      documentoidentidad mae_clien_ident.num_docu_iden%TYPE);
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
    lscodigocia     bas_pais_compa.cod_comp%TYPE;
    lscodigopaisocr bas_pais_compa.cod_pais_ocr%TYPE;
    lbabrirutlfile  BOOLEAN;
  BEGIN
  
    SELECT cod_comp,
           cod_pais_ocr
      INTO lscodigocia,
           lscodigopaisocr
      FROM bas_pais_compa
     WHERE cod_pais = pscodigopais;
  
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz(lscodigocia, lscodigopaisocr);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigopais || ';' || interfazrecord(x).codigocia || ';' || interfazrecord(x)
                     .codigocliente || ';' || interfazrecord(x).apellido1 || ';' || interfazrecord(x)
                     .apellido2 || ';' || interfazrecord(x).nombre1 || ';' || interfazrecord(x)
                     .nombre2 || ';' || interfazrecord(x).codigosubgerencia || ';' || interfazrecord(x)
                     .codigoregion || ';' || interfazrecord(x).codigozona || ';' || interfazrecord(x)
                     .codigoterritorio || ';' || interfazrecord(x)
                     .documentoidentidad;
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_ENVI_MAEST_CLIE: ' ||
                              ls_sqlerrm);
  END int_pr_ocr_envi_maest_clie;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Matriz de Facturacion
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_matri_factu
  (
    pscodigopais         VARCHAR2,
    pscodigosistema      VARCHAR2,
    pscodigointerfaz     VARCHAR2,
    psnombrearchivo      VARCHAR2,
    psnumerolote         VARCHAR2,
    pscodigomarca        VARCHAR2,
    pscodigocanal        VARCHAR2,
    pscodigoperiododesde VARCHAR2,
    pscodigoperiodohasta VARCHAR2
  ) IS
    CURSOR c_interfaz
    (
      oidperiododesde VARCHAR2,
      oidperiodohasta VARCHAR2,
      oidpais         VARCHAR2,
      oidmarca        VARCHAR2,
      oidcanal        VARCHAR2,
      vscodigocia     VARCHAR2,
      vscodigopaisocr VARCHAR2
    ) IS
      SELECT vscodigopaisocr           cod_pais,
             vscodigocia               codigocia,
             perio_corpo.cod_peri      AS cod_peri,
             ofert_detal.val_codi_vent AS val_codi_vent
        FROM cra_perio             perio,
             cra_perio             ini,
             cra_perio             fin,
             pre_matri_factu_cabec factu_cabec,
             pre_ofert             ofert,
             pre_ofert_detal       ofert_detal,
             seg_pais              pais,
             seg_perio_corpo       perio_corpo,
             seg_subac             subac
       WHERE ini.pais_oid_pais = oidpais
         AND ini.marc_oid_marc = oidmarca
         AND ini.cana_oid_cana = oidcanal
         AND fin.pais_oid_pais = oidpais
         AND fin.marc_oid_marc = oidmarca
         AND fin.cana_oid_cana = oidcanal
         AND perio.pais_oid_pais = oidpais
         AND perio.marc_oid_marc = oidmarca
         AND perio.cana_oid_cana = oidcanal
         AND pais.oid_pais = perio.pais_oid_pais
         AND perio_corpo.oid_peri = perio.peri_oid_peri
         AND ini.fec_inic <= perio.fec_inic
         AND fin.fec_fina >= perio.fec_fina
         AND factu_cabec.perd_oid_peri = perio.oid_peri
         AND ofert.mfca_oid_cabe = factu_cabec.oid_cabe
         AND ofert_detal.ofer_oid_ofer = ofert.oid_ofer
         AND ofert_detal.ind_digi = 1
         AND ini.oid_peri = oidperiododesde
         AND fin.oid_peri = oidperiodohasta
         AND ofert_detal.val_codi_vent IS NOT NULL
         AND ofert.sbac_oid_sbac = subac.oid_sbac(+)
         AND (ofert.sbac_oid_sbac IS NULL OR subac.cod_sbac = '000')
      UNION
      SELECT vscodigopaisocr          cod_pais,
             vscodigocia              codigocia,
             cup_equiv_matr.cod_peri  cod_peri,
             cup_equiv_matr.cod_cupon cod_venta
        FROM cup_equiv_matr
       WHERE cup_equiv_matr.cod_pais = pscodigopais
         AND cup_equiv_matr.cod_peri <= pscodigoperiodohasta
         AND cup_equiv_matr.cod_peri >= pscodigoperiododesde
      UNION
      SELECT vscodigopaisocr AS cod_pais,
             vscodigocia     codigocia,
             perio.cod_peri,
             a.cod_vent_fict
        FROM inc_artic_lote a,
             inc_lote_premi_artic b,
             inc_premi_artic c,
             inc_param_nivel_premi d,
             inc_param_gener_premi e,
             inc_concu_param_gener f,
             (SELECT a.cod_peri,
                     b.oid_peri
                FROM seg_perio_corpo a,
                     cra_perio       b
               WHERE (a.cod_peri >= pscodigoperiododesde AND
                     a.cod_peri <= pscodigoperiodohasta)
                 AND b.marc_oid_marc = oidmarca
                 AND b.cana_oid_cana = oidcanal
                 AND a.oid_peri = b.peri_oid_peri) perio
       WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
         AND b.prar_oid_prem_arti = c.oid_prem_arti
         AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
         AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
         AND e.copa_oid_para_gral = f.oid_para_gral
         AND f.ind_acti = 1
         AND f.bcal_oid_base_calc IN (1, 2)
         AND length(a.cod_vent_fict) = 5
         AND e.ind_prem_elec = 1
         AND f.perd_oid_peri_desd <= perio.oid_peri
         AND e.perd_oid_peri >= perio.oid_peri;
  
    ls_oidperiododesde seg_perio_corpo.oid_peri%TYPE;
    ls_oidperiodohasta seg_perio_corpo.oid_peri%TYPE;
    ls_oidpais         seg_pais.oid_pais%TYPE;
    ls_oidmarca        seg_marca.oid_marc%TYPE;
    ls_oidcanal        seg_canal.oid_cana%TYPE;
    TYPE interfazrec IS RECORD(
      codigopais    seg_pais.cod_pais%TYPE,
      codigocia     int_ivr_param_compa.cod_comp%TYPE,
      codigoperiodo seg_perio_corpo.cod_peri%TYPE,
      codigoventa   pre_ofert_detal.val_codi_vent%TYPE);
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lscodigocia     bas_pais_compa.cod_comp%TYPE;
    lscodigopaisocr bas_pais_compa.cod_pais_ocr%TYPE;
    lbabrirutlfile  BOOLEAN;
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
    ls_oidpais         := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);
    ls_oidmarca        := gen_pkg_gener.gen_fn_devuelve_id_marca(pscodigomarca);
    ls_oidcanal        := gen_pkg_gener.gen_fn_devuelve_id_canal(pscodigocanal);
    ls_oidperiododesde := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiododesde,
                                                                     ls_oidmarca,
                                                                     ls_oidcanal);
    ls_oidperiodohasta := gen_pkg_gener.gen_fn_devuelve_id_cra_perio(pscodigoperiodohasta,
                                                                     ls_oidmarca,
                                                                     ls_oidcanal);
    SELECT cod_comp,
           cod_pais_ocr
      INTO lscodigocia,
           lscodigopaisocr
      FROM bas_pais_compa
     WHERE cod_pais = pscodigopais;
  
    lbabrirutlfile := TRUE;
    OPEN c_interfaz(ls_oidperiododesde,
                    ls_oidperiodohasta,
                    ls_oidpais,
                    ls_oidmarca,
                    ls_oidcanal,
                    lscodigocia,
                    lscodigopaisocr);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigopais || ';' || interfazrecord(x).codigocia || ';' || interfazrecord(x)
                     .codigoperiodo || ';' || interfazrecord(x).codigoventa;
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_ENVI_MATRI_FACTU: ' ||
                              ls_sqlerrm);
  END int_pr_ocr_envi_matri_factu;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Rangos de Periodo de Vigente
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_rango_perio
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigoperiodo  VARCHAR2
  ) IS
    CURSOR c_interfaz
    (
      vscodigocia     VARCHAR2,
      vscodigopaisocr VARCHAR2
    ) IS
      SELECT DISTINCT vscodigopaisocr codigopais,
                      vscodigocia codigocia,
                      pscodigoperiodo codigoperiodoinicio,
                      pscodigoperiodo codigoperiodofin,
                      to_char(SYSDATE, 'YYYYMMDD') fecha
        FROM dual;
  
    TYPE interfazrec IS RECORD(
      codigopais          seg_pais.cod_pais%TYPE,
      codigocia           int_ivr_param_compa.cod_comp%TYPE,
      codigoperiodoinicio seg_perio_corpo.cod_peri%TYPE,
      codigoperiodofin    seg_perio_corpo.cod_peri%TYPE,
      fecha               VARCHAR2(10));
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lscodigocia     bas_pais_compa.cod_comp%TYPE;
    lscodigopaisocr bas_pais_compa.cod_pais_ocr%TYPE;
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);
  
    SELECT cod_comp,
           cod_pais_ocr
      INTO lscodigocia,
           lscodigopaisocr
      FROM bas_pais_compa
     WHERE cod_pais = pscodigopais;
    /* Generando Archivo de Texto (Detalle) */
  
    OPEN c_interfaz(lscodigocia, lscodigopaisocr);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigopais || ';' || interfazrecord(x).codigocia || ';' || interfazrecord(x)
                     .codigoperiodoinicio || ';' || interfazrecord(x)
                     .codigoperiodofin || ';' || interfazrecord(x).fecha;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    utl_file.fclose(v_handle);
  
    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_ENVI_RANGO_PERIO: ' ||
                              ls_sqlerrm);
  END int_pr_ocr_envi_rango_perio;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Tabla de Regiones
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_tabla_regio
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2
  ) IS
    CURSOR c_interfaz
    (
      vscodigocia     VARCHAR2,
      vscodigopaisocr VARCHAR2
    ) IS
      SELECT vscodigopaisocr codigopais,
             vscodigocia codigocia,
             nvl(zon_regio.cod_regi, ' ') codigoregion,
             upper(nvl(zon_regio.des_regi, ' ')) descripcionregion
        FROM seg_pais,
             seg_marca,
             seg_canal,
             zon_regio
       WHERE (seg_pais.cod_pais = pscodigopais)
         AND (zon_regio.ind_acti = 1)
         AND (seg_marca.cod_marc = pscodigomarca)
         AND (seg_canal.cod_cana = pscodigocanal)
         AND (seg_pais.oid_pais = zon_regio.pais_oid_pais)
         AND (seg_marca.oid_marc = zon_regio.marc_oid_marc)
         AND (seg_canal.oid_cana = zon_regio.cana_oid_cana)
       ORDER BY 3;
  
    TYPE interfazrec IS RECORD(
      codigopais        seg_pais.cod_pais%TYPE,
      codigocia         int_ivr_param_compa.cod_comp%TYPE,
      codigoregion      zon_regio.cod_regi%TYPE,
      descripcionregion VARCHAR2(50));
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lscodigocia     bas_pais_compa.cod_comp%TYPE;
    lscodigopaisocr bas_pais_compa.cod_pais_ocr%TYPE;
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);
  
    SELECT cod_comp,
           cod_pais_ocr
      INTO lscodigocia,
           lscodigopaisocr
      FROM bas_pais_compa
     WHERE cod_pais = pscodigopais;
    /* Generando Archivo de Texto (Detalle) */
  
    OPEN c_interfaz(lscodigocia, lscodigopaisocr);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigopais || ';' || interfazrecord(x).codigocia || ';' || interfazrecord(x)
                     .codigoregion || ';' || interfazrecord(x)
                     .descripcionregion;
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    utl_file.fclose(v_handle);
  
    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_ENVI_RANGO_PERIO: ' ||
                              ls_sqlerrm);
  END int_pr_ocr_envi_tabla_regio;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Tabla de Zonas
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_tabla_zonas
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2
  ) IS
    CURSOR c_interfaz
    (
      vscodigocia     VARCHAR2,
      vscodigopaisocr VARCHAR2
    ) IS
      SELECT vscodigopaisocr codigopais,
             vscodigocia codigocia,
             nvl(zon_regio.cod_regi, ' ') AS codigoregion,
             nvl(zon_zona.cod_zona, ' ') AS codigozona
        FROM seg_marca,
             seg_canal,
             seg_pais,
             zon_regio,
             zon_zona
       WHERE (seg_pais.cod_pais = pscodigopais)
         AND (zon_zona.ind_acti = 1)
         AND (seg_marca.cod_marc = pscodigomarca)
         AND (seg_canal.cod_cana = pscodigocanal)
         AND (seg_marca.oid_marc = zon_regio.marc_oid_marc)
         AND (seg_canal.oid_cana = zon_regio.cana_oid_cana)
         AND (seg_pais.oid_pais = zon_regio.pais_oid_pais)
         AND (seg_marca.oid_marc = zon_zona.marc_oid_marc)
         AND (seg_canal.oid_cana = zon_zona.cana_oid_cana)
         AND (seg_pais.oid_pais = zon_zona.pais_oid_pais)
         AND (zon_regio.oid_regi = zon_zona.zorg_oid_regi)
       ORDER BY 3;
  
    TYPE interfazrec IS RECORD(
      codigopais   seg_pais.cod_pais%TYPE,
      codigocia    int_ivr_param_compa.cod_comp%TYPE,
      codigoregion zon_regio.cod_regi%TYPE,
      codigozona   zon_zona.cod_zona%TYPE);
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lscodigocia     bas_pais_compa.cod_comp%TYPE;
    lscodigopaisocr bas_pais_compa.cod_pais_ocr%TYPE;
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                           pscodigosistema,
                                           pscodigointerfaz,
                                           psnombrearchivo,
                                           lsdirtempo,
                                           lsnombrearchivo,
                                           v_handle);
  
    SELECT cod_comp,
           cod_pais_ocr
      INTO lscodigocia,
           lscodigopaisocr
      FROM bas_pais_compa
     WHERE cod_pais = pscodigopais;
    /* Generando Archivo de Texto (Detalle) */
  
    OPEN c_interfaz(lscodigocia, lscodigopaisocr);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigopais || ';' || interfazrecord(x).codigocia || ';' || interfazrecord(x)
                     .codigoregion || ';' || interfazrecord(x).codigozona;
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    utl_file.fclose(v_handle);
  
    /* Procedimiento final para generar interfaz */
    gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                           lsdirtempo,
                                           psnombrearchivo,
                                           lsnombrearchivo);
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_ENVI_TABLA_ZONAS: ' ||
                              ls_sqlerrm);
  END int_pr_ocr_envi_tabla_zonas;
  /***************************************************************************
  Descripcion       : Devuelve le codigo de Compa?ia
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_ocr_devue_codig_compa(pscodigopais VARCHAR2)
    RETURN VARCHAR2 IS
  
    lscodigocia VARCHAR2(2);
  
  BEGIN
  
    SELECT int_ivr_param_compa.cod_comp
      INTO lscodigocia
      FROM int_ivr_param_compa
     WHERE int_ivr_param_compa.cod_pais = pscodigopais;
  
    RETURN lscodigocia;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_FN_OCR_DEVUE_CODIG_COMPA: ' ||
                              ls_sqlerrm);
    
  END int_fn_ocr_devue_codig_compa;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Cabeceras de OCR
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_conso_cabec
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codpais IS TABLE OF int_solic_cabec.cod_pais%TYPE;
    TYPE t_codcia IS TABLE OF int_solic_cabec.cod_comp%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_cabec.num_docu%TYPE;
    TYPE t_camsoli IS TABLE OF int_solic_cabec.cam_soli%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_cabec.cod_clie%TYPE;
    TYPE t_numclie IS TABLE OF int_solic_cabec.num_clie%TYPE;
    TYPE t_tipsoli IS TABLE OF int_solic_cabec.tip_soli%TYPE;
    TYPE t_codsuba IS TABLE OF int_solic_cabec.cod_suba%TYPE;
    TYPE t_codacce IS TABLE OF int_solic_cabec.cod_acce%TYPE;
    TYPE t_tipdesp IS TABLE OF int_solic_cabec.tip_desp%TYPE;
    TYPE t_fecsoli IS TABLE OF int_solic_cabec.fec_soli%TYPE;
    TYPE t_regarri IS TABLE OF int_solic_cabec.cod_regi_arri%TYPE;
    TYPE t_zonarri IS TABLE OF int_solic_cabec.cod_zona_arri%TYPE;
    TYPE t_staproc IS TABLE OF VARCHAR2(2);
    TYPE t_motrech IS TABLE OF int_solic_cabec.cod_moti_rech%TYPE;
  
    v_codpais t_codpais := t_codpais();
    v_codcia  t_codcia := t_codcia();
    v_numdocu t_numdocu := t_numdocu();
    v_camsoli t_camsoli := t_camsoli();
    v_codclie t_codclie := t_codclie();
    v_numclie t_numclie := t_numclie();
    v_tipsoli t_tipsoli := t_tipsoli();
    v_codsuba t_codsuba := t_codsuba();
    v_codacce t_codacce := t_codacce();
    v_tipdesp t_tipdesp := t_tipdesp();
    v_fecsoli t_fecsoli := t_fecsoli();
    v_regarri t_regarri := t_regarri();
    v_zonarri t_zonarri := t_zonarri();
    v_staproc t_staproc := t_staproc();
    v_motrech t_motrech := t_motrech();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    i               BINARY_INTEGER := 0;
    posicion        NUMBER := 0;
    longitud        NUMBER := 0;
    inicio          NUMBER := 0;
  
    v_error_carga  NUMBER := 0;
    v_error_status NUMBER := 0;
  
    v_camactiva int_solic_cabec.cam_soli%TYPE;
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    --Obteniendo la periodo activa
    SELECT c.cod_peri
      INTO v_camactiva
      FROM bas_ctrl_fact c
     WHERE c.cod_pais = pscodigopais
       AND c.ind_camp_act = 1
       AND c.sta_camp = '0';
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          v_error_carga  := 0;
          v_error_status := 0;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_codpais(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '00');
                  -- Se valida que el pais no sea nulo si lo es se pone el pais por defecto
                  IF (v_codpais(i) = '00') THEN
                    v_codpais(i) := pscodigopais;
                    v_error_carga := 1;
                  END IF;
                
                ELSIF (posicion = 2) THEN
                  v_codcia.extend;
                  v_codcia(i) := TRIM(substr(lslinea, inicio, longitud));
                
                  v_codpais(i) := nvl(sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codpais(i),
                                                                                  v_codcia(i)),
                                      '00');
                
                  -- Se valida que el pais no sea nulo si lo es se pone el pais por defecto
                  IF (v_codpais(i) = '00') THEN
                    v_codpais(i) := pscodigopais;
                    v_error_carga := 1;
                  END IF;
                
                ELSIF (posicion = 3) THEN
                  v_numdocu.extend;
                  v_numdocu(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_camsoli.extend;
                  v_camsoli(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                
                  -- Se valida que la periodo no sea nulo si lo es se pone la periodo activa
                  IF (v_camsoli(i) = '0') THEN
                    v_camsoli(i) := v_camactiva;
                    v_error_carga := 1;
                  ELSE
                    BEGIN
                      SELECT cod_peri
                        INTO v_camsoli(i)
                        FROM bas_ctrl_fact
                       WHERE cod_pais = v_codpais(i)
                         AND cod_peri = v_camsoli(i);
                    EXCEPTION
                      WHEN no_data_found THEN
                        BEGIN
                          SELECT cod_peri
                            INTO v_camsoli(i)
                            FROM seg_perio_corpo
                           WHERE cod_peri = v_camsoli(i);
                        
                          /*INSERTA LA periodo EN CASO NO EXISTA EN EL ARCHIVO DE CONTROL*/
                          INSERT INTO bas_ctrl_fact
                            (cod_pais,
                             cod_peri,
                             fec_proc,
                             val_mnt_min_fact,
                             val_mnt_min_acept,
                             val_mnt_max,
                             val_unid_max,
                             sta_camp,
                             usu_digi,
                             fec_digi,
                             cod_marc,
                             des_marc,
                             cod_cana,
                             des_cana,
                             val_mnt_min_deud,
                             ind_camp_act,
                             num_lote)
                            SELECT cod_pais,
                                   v_camsoli(i),
                                   fec_proc,
                                   val_mnt_min_fact,
                                   val_mnt_min_acept,
                                   val_mnt_max,
                                   val_unid_max,
                                   '1',
                                   USER,
                                   SYSDATE,
                                   cod_marc,
                                   des_marc,
                                   cod_cana,
                                   des_cana,
                                   val_mnt_min_deud,
                                   '0',
                                   num_lote
                              FROM bas_ctrl_fact c
                             WHERE c.cod_pais = pscodigopais
                               AND c.ind_camp_act = '1'
                               AND c.sta_camp = '0';
                        
                        EXCEPTION
                          WHEN no_data_found THEN
                            v_camsoli(i) := v_camactiva;
                            v_error_carga := 1;
                        END;
                    END;
                  
                  END IF;
                
                ELSIF (posicion = 5) THEN
                  v_codclie.extend;
                  v_codclie(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                ELSIF (posicion = 6) THEN
                  v_numclie.extend;
                  v_numclie(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      0);
                ELSIF (posicion = 7) THEN
                  v_tipsoli.extend;
                  v_tipsoli(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 8) THEN
                  v_codsuba.extend;
                  v_codsuba(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 9) THEN
                  v_codacce.extend;
                  v_codacce(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 10) THEN
                  v_tipdesp.extend;
                  v_tipdesp(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 11) THEN
                  v_fecsoli.extend;
                  v_fecsoli(i) := to_date(substr(lslinea, inicio, longitud),
                                          'yyyyMMdd');
                ELSIF (posicion = 12) THEN
                  v_regarri.extend;
                  v_regarri(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 13) THEN
                  v_zonarri.extend;
                  v_zonarri(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 14) THEN
                  v_staproc.extend;
                  v_staproc(i) := TRIM(substr(lslinea, inicio, longitud));
                  -- Se existe error en el cod pais, compañia o periodo y ademas el status del proceso
                  -- es Aprobado (01) se cambia el status a Rechazado (02) y el motivo a Error de Carga (20)
                  IF (v_error_carga = 1 AND v_staproc(i) = '01') THEN
                    v_staproc(i) := '02';
                    v_error_status := 1;
                  END IF;
                
                ELSIF (posicion = 15) THEN
                  v_motrech.extend;
                  v_motrech(i) := TRIM(substr(lslinea, inicio, longitud));
                
                  IF (v_error_status = 1) THEN
                    v_motrech(i) := '20';
                  END IF;
                
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codpais.count
      INSERT INTO int_solic_cabec
        (cod_pais,
         cam_soli,
         cod_clie,
         oid_cab,
         num_lote,
         num_clie,
         tip_soli,
         cod_comp,
         num_docu,
         cod_regi_arri,
         cod_zona_arri,
         cod_suba,
         cod_acce,
         tip_desp,
         fec_soli,
         sta_proc,
         cod_moti_rech,
         cod_inte,
         num_lote_inte,
         num_lote_sto,
         ind_proc)
      VALUES
        (v_codpais(i),
         v_camsoli(i),
         v_codclie(i),
         seq_solic_cab.nextval,
         (SELECT bas.num_lote
            FROM bas_ctrl_fact bas
           WHERE bas.cod_pais = v_codpais(i)
             AND bas.cod_peri = v_camsoli(i)),
         v_numclie(i),
         v_tipsoli(i),
         v_codcia(i),
         v_numdocu(i),
         v_regarri(i),
         v_zonarri(i),
         v_codsuba(i),
         v_codacce(i),
         v_tipdesp(i),
         v_fecsoli(i),
         decode(v_staproc(i), '01', 'A', 'R'),
         v_motrech(i),
         pscodigointerfaz,
         psnumerolote,
         psnumerolotesto,
         psindicadororigen);
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_RECEP_CONSO_CABEC: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END ocr_pr_recep_conso_cabec;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Detalle de OCR
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_conso_detal
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codpais IS TABLE OF int_solic_posic.cod_pais%TYPE;
    TYPE t_codcia IS TABLE OF int_solic_posic.cod_comp%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_posic.num_docu%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_posic.cod_clie%TYPE;
    TYPE t_tipposi IS TABLE OF int_solic_posic.tip_posi%TYPE;
    TYPE t_codprod IS TABLE OF int_solic_posic.cod_prod%TYPE;
    TYPE t_unidema IS TABLE OF int_solic_posic.uni_dema%TYPE;
    TYPE t_sta_proc IS TABLE OF VARCHAR2(2);
    TYPE t_motrech IS TABLE OF int_solic_posic.cod_moti_rech%TYPE;
    TYPE t_numline IS TABLE OF int_solic_posic.num_line_oc%TYPE;
  
    v_codpais t_codpais := t_codpais();
    v_codcia  t_codcia := t_codcia();
    v_numdocu t_numdocu := t_numdocu();
  
    v_codclie  t_codclie := t_codclie();
    v_tipposi  t_tipposi := t_tipposi();
    v_codprod  t_codprod := t_codprod();
    v_unidema  t_unidema := t_unidema();
    v_sta_proc t_sta_proc := t_sta_proc();
    v_motrech  t_motrech := t_motrech();
    v_numline  t_numline := t_numline();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    v_error_carga  NUMBER := 0;
    v_error_status NUMBER := 0;
  
    w_filas NUMBER := 10000; --c1
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
        
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          v_error_carga  := 0;
          v_error_status := 0;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_codpais(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '00');
                  -- Se valida que el pais no sea nulo si lo es se pone el pais por defecto
                  IF (v_codpais(i) = '00') THEN
                    v_codpais(i) := pscodigopais;
                    v_error_carga := 1;
                  END IF;
                
                ELSIF (posicion = 2) THEN
                  v_codcia.extend;
                  v_codcia(i) := TRIM(substr(lslinea, inicio, longitud));
                
                  v_codpais(i) := nvl(sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codpais(i),
                                                                                  v_codcia(i)),
                                      '00');
                
                  -- Se valida que el pais no sea nulo si lo es se pone el pais por defecto
                  IF (v_codpais(i) = '00') THEN
                    v_codpais(i) := pscodigopais;
                    v_error_carga := 1;
                  END IF;
                
                ELSIF (posicion = 3) THEN
                  v_numdocu.extend;
                  v_numdocu(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_codclie.extend;
                  v_codclie(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                ELSIF (posicion = 5) THEN
                  v_tipposi.extend;
                  v_tipposi(i) := TRIM(substr(lslinea, inicio, longitud));
                
                ELSIF (posicion = 6) THEN
                  v_codprod.extend;
                  v_codprod(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                
                ELSIF (posicion = 7) THEN
                  v_unidema.extend;
                  v_unidema(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      0);
                ELSIF (posicion = 8) THEN
                  v_sta_proc.extend;
                  v_sta_proc(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       0);
                
                  -- Se existe error en el cod pais, compa?ia o periodo y ademas el status del proceso
                  -- es Aprobado (01) se cambia el status a Rechazado (02) y el motivo a Error de Carga (20)
                  IF (v_error_carga = 1 AND v_sta_proc(i) = '01') THEN
                    v_sta_proc(i) := '02';
                    v_error_status := 1;
                  END IF;
                
                ELSIF (posicion = 9) THEN
                  v_motrech.extend;
                  v_motrech(i) := TRIM(substr(lslinea, inicio, longitud));
                
                  IF (v_error_status = 1) THEN
                    v_motrech(i) := '20';
                  END IF;
                
                ELSIF (posicion = 10) THEN
                  v_numline.extend;
                  v_numline(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codpais.count
      INSERT INTO int_solic_posic
        (cod_pais,
         cam_soli,
         cod_clie,
         cod_prod,
         oid_posic,
         num_lote,
         cod_comp,
         num_docu,
         tip_posi,
         uni_dema,
         sta_proc,
         cod_moti_rech,
         num_line_oc,
         cod_inte,
         num_lote_inte,
         num_lote_sto,
         ind_proc)
        SELECT v_codpais(i),
               c.cam_soli,
               v_codclie(i),
               v_codprod(i),
               seq_solic_pos.nextval,
               c.num_lote,
               v_codcia(i),
               v_numdocu(i),
               v_tipposi(i),
               v_unidema(i),
               decode(v_sta_proc(i), '01', 'A', 'R'),
               v_motrech(i),
               v_numline(i),
               pscodigointerfaz,
               psnumerolote,
               psnumerolotesto,
               psindicadororigen
          FROM int_solic_cabec c
         WHERE c.cod_pais = v_codpais(i)
           AND c.cod_clie = v_codclie(i)
           AND c.num_docu = v_numdocu(i)
           AND c.num_lote_sto = psnumerolotesto
           AND rownum = 1;
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_RECEP_CONSO_DETAL: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_recep_conso_detal;

  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Cupon de pago
  Fecha Creacion    : 28/03/2008
  Autor             : Dennys Oliva Iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_cupon_pago
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codigopais IS TABLE OF int_ocr_cupon_pago.cod_pais%TYPE;
    TYPE t_codigocia IS TABLE OF int_ocr_cupon_pago.cod_cia %TYPE;
    TYPE t_numerodocumento IS TABLE OF int_ocr_cupon_pago.num_docu %TYPE;
    TYPE t_codigoreferenciapago IS TABLE OF int_ocr_cupon_pago.cod_refe_pago%TYPE;
    TYPE t_valorcancelado IS TABLE OF int_ocr_cupon_pago.val_canc%TYPE;
    TYPE t_fechaproceso IS TABLE OF int_ocr_cupon_pago.fec_proc_doc%TYPE;
    TYPE t_codigoregion IS TABLE OF int_ocr_cupon_pago.cod_regi_arri%TYPE;
    TYPE t_codigozona IS TABLE OF int_ocr_cupon_pago.cod_zona_arri%TYPE;
    TYPE t_statusproceso IS TABLE OF int_ocr_cupon_pago.sta_proc%TYPE;
    TYPE t_motivorechazo IS TABLE OF int_ocr_cupon_pago.cod_moti_rech%TYPE;
    ----------------------
    v_codigopais           t_codigopais := t_codigopais();
    v_codigocia            t_codigocia := t_codigocia();
    v_numerodocumento      t_numerodocumento := t_numerodocumento();
    v_codigoreferenciapago t_codigoreferenciapago := t_codigoreferenciapago();
    v_valorcancelado       t_valorcancelado := t_valorcancelado();
    v_fechaproceso         t_fechaproceso := t_fechaproceso();
    v_codigoregion         t_codigoregion := t_codigoregion();
    v_codigozona           t_codigozona := t_codigozona();
    v_statusproceso        t_statusproceso := t_statusproceso();
    v_motivorechazo        t_motivorechazo := t_motivorechazo();
    ----------------------
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  
    -------------------------
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codigopais.extend;
                  v_codigopais(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 2) THEN
                  v_codigocia.extend;
                  v_codigocia(i) := TRIM(translate(substr(lslinea,
                                                          inicio,
                                                          longitud),
                                                   lscadena,
                                                   lsreplace));
                  v_codigopais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codigopais(i),
                                                                                 v_codigocia(i));
                ELSIF (posicion = 3) THEN
                  v_numerodocumento.extend;
                  v_numerodocumento(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 4) THEN
                  v_codigoreferenciapago.extend;
                  v_codigoreferenciapago(i) := TRIM(translate(substr(lslinea,
                                                                     inicio,
                                                                     longitud),
                                                              lscadena,
                                                              lsreplace));
                ELSIF (posicion = 5) THEN
                  v_valorcancelado.extend;
                  v_valorcancelado(i) := nvl(TRIM(substr(lslinea,
                                                         inicio,
                                                         longitud)),
                                             0);
                ELSIF (posicion = 6) THEN
                  v_fechaproceso.extend;
                  v_fechaproceso(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 7) THEN
                  v_codigoregion.extend;
                  v_codigoregion(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 8) THEN
                  v_codigozona.extend;
                  v_codigozona(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 9) THEN
                  v_statusproceso.extend;
                  v_statusproceso(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 10) THEN
                  v_motivorechazo.extend;
                  v_motivorechazo(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codigopais.count
    
      INSERT INTO int_ocr_cupon_pago
        (cod_pais,
         cod_cia,
         num_docu,
         cod_refe_pago,
         val_canc,
         fec_proc_doc,
         cod_regi_arri,
         cod_zona_arri,
         sta_proc,
         cod_moti_rech,
         ind_orig)
      VALUES
        (v_codigopais(i),
         v_codigocia(i),
         v_numerodocumento(i),
         v_codigoreferenciapago(i),
         to_number(v_valorcancelado(i)),
         v_fechaproceso(i),
         v_codigoregion(i),
         v_codigozona(i),
         v_statusproceso(i),
         v_motivorechazo(i),
         psindicadororigen);
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_CUPON_PAGO: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_cupon_pago;
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de DWH OCR
  Fecha Creacion    : 28/03/2008
  Autor             : Dennys Oliva Iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_dwh_ocr
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codigopais IS TABLE OF int_ocr_dwh.cod_pais%TYPE;
    TYPE t_codigocia IS TABLE OF int_ocr_dwh.cod_cia %TYPE;
    TYPE t_numerosemestre IS TABLE OF int_ocr_dwh.num_seme%TYPE;
    TYPE t_anocampana IS TABLE OF int_ocr_dwh.ano_camp%TYPE;
    TYPE t_codigocampana IS TABLE OF int_ocr_dwh.cod_camp%TYPE;
    TYPE t_codigoregion IS TABLE OF int_ocr_dwh.cod_regi%TYPE;
    TYPE t_codigozona IS TABLE OF int_ocr_dwh.cod_zona%TYPE;
    TYPE t_fechaproceso IS TABLE OF int_ocr_dwh.fec_proc%TYPE;
    TYPE t_tipodocumento IS TABLE OF int_ocr_dwh.tip_docu %TYPE;
    TYPE t_codigoestado IS TABLE OF int_ocr_dwh.cod_esta%TYPE;
    TYPE t_codigousuario IS TABLE OF int_ocr_dwh.cod_usua%TYPE;
    TYPE t_codigomotivorechazo IS TABLE OF int_ocr_dwh.cod_moti_rech%TYPE;
    TYPE t_numeroindicador IS TABLE OF int_ocr_dwh.num_indi%TYPE;
    TYPE t_valorindicador IS TABLE OF int_ocr_dwh.val_indi%TYPE;
    ----------------------
    v_codigopais          t_codigopais := t_codigopais();
    v_codigocia           t_codigocia := t_codigocia();
    v_numerosemestre      t_numerosemestre := t_numerosemestre();
    v_anocampana          t_anocampana := t_anocampana();
    v_codigocampana       t_codigocampana := t_codigocampana();
    v_codigoregion        t_codigoregion := t_codigoregion();
    v_codigozona          t_codigozona := t_codigozona();
    v_fechaproceso        t_fechaproceso := t_fechaproceso();
    v_tipodocumento       t_tipodocumento := t_tipodocumento();
    v_codigoestado        t_codigoestado := t_codigoestado();
    v_codigousuario       t_codigousuario := t_codigousuario();
    v_codigomotivorechazo t_codigomotivorechazo := t_codigomotivorechazo();
    v_numeroindicador     t_numeroindicador := t_numeroindicador();
    v_valorindicador      t_valorindicador := t_valorindicador();
    ----------------------
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
    -------------------------
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codigopais.extend;
                  v_codigopais(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 2) THEN
                  v_codigocia.extend;
                  v_codigocia(i) := TRIM(substr(lslinea, inicio, longitud));
                  v_codigopais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codigopais(i),
                                                                                 v_codigocia(i));
                ELSIF (posicion = 3) THEN
                  v_numerosemestre.extend;
                  v_numerosemestre(i) := nvl(TRIM(substr(lslinea,
                                                         inicio,
                                                         longitud)),
                                             0);
                ELSIF (posicion = 4) THEN
                  v_anocampana.extend;
                  v_anocampana(i) := nvl(TRIM(substr(lslinea,
                                                     inicio,
                                                     longitud)),
                                         0);
                ELSIF (posicion = 5) THEN
                  v_codigocampana.extend;
                  v_codigocampana(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 6) THEN
                  v_codigoregion.extend;
                  v_codigoregion(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 7) THEN
                  v_codigozona.extend;
                  v_codigozona(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 8) THEN
                  v_fechaproceso.extend;
                  v_fechaproceso(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 9) THEN
                  v_tipodocumento.extend;
                  v_tipodocumento(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 10) THEN
                  v_codigoestado.extend;
                  v_codigoestado(i) := TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud));
                ELSIF (posicion = 11) THEN
                  v_codigousuario.extend;
                  v_codigousuario(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 12) THEN
                  v_codigomotivorechazo.extend;
                  v_codigomotivorechazo(i) := substr(lslinea,
                                                     inicio,
                                                     longitud);
                ELSIF (posicion = 13) THEN
                  v_numeroindicador.extend;
                  v_numeroindicador(i) := nvl(TRIM(substr(lslinea,
                                                          inicio,
                                                          longitud)),
                                              0);
                ELSIF (posicion = 14) THEN
                  v_valorindicador.extend;
                  v_valorindicador(i) := nvl(TRIM(substr(lslinea,
                                                         inicio,
                                                         longitud)),
                                             0);
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codigopais.count
    
      INSERT INTO int_ocr_dwh
        (cod_pais,
         cod_cia,
         num_seme,
         ano_camp,
         cod_camp,
         cod_regi,
         cod_zona,
         fec_proc,
         tip_docu,
         cod_esta,
         cod_usua,
         cod_moti_rech,
         num_indi,
         val_indi)
      VALUES
        (v_codigopais(i),
         v_codigocia(i),
         v_numerosemestre(i),
         v_anocampana(i),
         v_codigocampana(i),
         v_codigoregion(i),
         v_codigozona(i),
         v_fechaproceso(i),
         v_tipodocumento(i),
         v_codigoestado(i),
         v_codigousuario(i),
         v_codigomotivorechazo(i),
         v_numeroindicador(i),
         v_valorindicador(i));
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_DWH_OCR: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_dwh_ocr;
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Ficha de inscripcion Privilege
  Fecha Creacion    : 28/03/2008
  Autor             : Dennys Oliva Iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_ficha_privi
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codigopais IS TABLE OF int_ocr_ficha_inscr_privi.cod_pais%TYPE;
    TYPE t_codigocia IS TABLE OF int_ocr_ficha_inscr_privi.cod_cia%TYPE;
    TYPE t_numerodocumento IS TABLE OF int_ocr_ficha_inscr_privi.num_docu%TYPE;
    TYPE t_indicadorinscripcion IS TABLE OF int_ocr_ficha_inscr_privi.cam_marc_insc%TYPE;
    TYPE t_indicadoractualizaciondatos IS TABLE OF int_ocr_ficha_inscr_privi.cam_marc_actu_dato%TYPE;
    TYPE t_primerapellido IS TABLE OF int_ocr_ficha_inscr_privi.pri_apel%TYPE;
    TYPE t_segundoapellido IS TABLE OF int_ocr_ficha_inscr_privi.sec_apel%TYPE;
    TYPE t_primernombre IS TABLE OF int_ocr_ficha_inscr_privi.pri_nomb%TYPE;
    TYPE t_segundonombre IS TABLE OF int_ocr_ficha_inscr_privi.sec_nomb%TYPE;
    TYPE t_codigoclienteprivilege IS TABLE OF int_ocr_ficha_inscr_privi.cod_clie_priv%TYPE;
    TYPE t_direccioncliente IS TABLE OF int_ocr_ficha_inscr_privi.dir_clie%TYPE;
    TYPE t_urbanizacion IS TABLE OF int_ocr_ficha_inscr_privi.val_urba%TYPE;
    TYPE t_distrito IS TABLE OF int_ocr_ficha_inscr_privi.val_dist%TYPE;
    TYPE t_provincia IS TABLE OF int_ocr_ficha_inscr_privi.val_prov%TYPE;
    TYPE t_telefonoclienteprivilege IS TABLE OF int_ocr_ficha_inscr_privi.tel_clie_priv%TYPE;
    TYPE t_otrotelefono IS TABLE OF int_ocr_ficha_inscr_privi.otr_tele%TYPE;
    TYPE t_email IS TABLE OF int_ocr_ficha_inscr_privi.cor_elec%TYPE;
    TYPE t_fechanacimiento IS TABLE OF int_ocr_ficha_inscr_privi.fec_naci%TYPE;
    TYPE t_condicion IS TABLE OF int_ocr_ficha_inscr_privi.val_cond%TYPE;
    TYPE t_codigocliente IS TABLE OF int_ocr_ficha_inscr_privi.cod_clie%TYPE;
    TYPE t_unidadadm IS TABLE OF int_ocr_ficha_inscr_privi.uni_admi%TYPE;
    TYPE t_indicadornormalseco IS TABLE OF int_ocr_ficha_inscr_privi.ind_norm_seco%TYPE;
    TYPE t_indicadornormalgraso IS TABLE OF int_ocr_ficha_inscr_privi.ind_norm_gras%TYPE;
    TYPE t_indicadorgrasa IS TABLE OF int_ocr_ficha_inscr_privi.ind_gras%TYPE;
    TYPE t_indicadorarrugasgest IS TABLE OF int_ocr_ficha_inscr_privi.ind_arru_gest%TYPE;
    TYPE t_indicadorarrugaspronun IS TABLE OF int_ocr_ficha_inscr_privi.ind_arru_pron%TYPE;
    TYPE t_indicadormanchas IS TABLE OF int_ocr_ficha_inscr_privi.ind_manc%TYPE;
    TYPE t_indicadorimperfecciones IS TABLE OF int_ocr_ficha_inscr_privi.ind_impe%TYPE;
    TYPE t_indicadorflacidez IS TABLE OF int_ocr_ficha_inscr_privi.ind_flac%TYPE;
    TYPE t_indicadorenvejecimiento IS TABLE OF int_ocr_ficha_inscr_privi.ind_enve%TYPE;
    TYPE t_codigoregion IS TABLE OF int_ocr_ficha_inscr_privi.cod_regi_arri%TYPE;
    TYPE t_codigozona IS TABLE OF int_ocr_ficha_inscr_privi.cod_zona_arri%TYPE;
    TYPE t_fechaproceso IS TABLE OF int_ocr_ficha_inscr_privi.fec_proc_docu%TYPE;
    TYPE t_statusproceso IS TABLE OF int_ocr_ficha_inscr_privi.sta_proc%TYPE;
    TYPE t_motivorechazo IS TABLE OF int_ocr_ficha_inscr_privi.cod_moti_rech%TYPE;
    ----------------------
    v_codigopais                  t_codigopais := t_codigopais();
    v_codigocia                   t_codigocia := t_codigocia();
    v_numerodocumento             t_numerodocumento := t_numerodocumento();
    v_indicadorinscripcion        t_indicadorinscripcion := t_indicadorinscripcion();
    v_indicadoractualizaciondatos t_indicadoractualizaciondatos := t_indicadoractualizaciondatos();
    v_primerapellido              t_primerapellido := t_primerapellido();
    v_segundoapellido             t_segundoapellido := t_segundoapellido();
    v_primernombre                t_primernombre := t_primernombre();
    v_segundonombre               t_segundonombre := t_segundonombre();
    v_codigoclienteprivilege      t_codigoclienteprivilege := t_codigoclienteprivilege();
    v_direccioncliente            t_direccioncliente := t_direccioncliente();
    v_urbanizacion                t_urbanizacion := t_urbanizacion();
    v_distrito                    t_distrito := t_distrito();
    v_provincia                   t_provincia := t_provincia();
    v_telefonoclienteprivilege    t_telefonoclienteprivilege := t_telefonoclienteprivilege();
    v_otrotelefono                t_otrotelefono := t_otrotelefono();
    v_email                       t_email := t_email();
    v_fechanacimiento             t_fechanacimiento := t_fechanacimiento();
    v_condicion                   t_condicion := t_condicion();
    v_codigocliente               t_codigocliente := t_codigocliente();
    v_unidadadm                   t_unidadadm := t_unidadadm();
    v_indicadornormalseco         t_indicadornormalseco := t_indicadornormalseco();
    v_indicadornormalgraso        t_indicadornormalgraso := t_indicadornormalgraso();
    v_indicadorgrasa              t_indicadorgrasa := t_indicadorgrasa();
    v_indicadorarrugasgest        t_indicadorarrugasgest := t_indicadorarrugasgest();
    v_indicadorarrugaspronun      t_indicadorarrugaspronun := t_indicadorarrugaspronun();
    v_indicadormanchas            t_indicadormanchas := t_indicadormanchas();
    v_indicadorimperfecciones     t_indicadorimperfecciones := t_indicadorimperfecciones();
    v_indicadorflacidez           t_indicadorflacidez := t_indicadorflacidez();
    v_indicadorenvejecimiento     t_indicadorenvejecimiento := t_indicadorenvejecimiento();
    v_codigoregion                t_codigoregion := t_codigoregion();
    v_codigozona                  t_codigozona := t_codigozona();
    v_fechaproceso                t_fechaproceso := t_fechaproceso();
    v_statusproceso               t_statusproceso := t_statusproceso();
    v_motivorechazo               t_motivorechazo := t_motivorechazo();
    ----------------------
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
    -------------------------
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codigopais.extend;
                  v_codigopais(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 2) THEN
                  v_codigocia.extend;
                  v_codigocia(i) := TRIM(substr(lslinea, inicio, longitud));
                  v_codigopais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codigopais(i),
                                                                                 v_codigocia(i));
                ELSIF (posicion = 3) THEN
                  v_numerodocumento.extend;
                  v_numerodocumento(i) := TRIM(substr(lslinea,
                                                      inicio,
                                                      longitud));
                ELSIF (posicion = 4) THEN
                  v_indicadorinscripcion.extend;
                  v_indicadorinscripcion(i) := TRIM(substr(lslinea,
                                                           inicio,
                                                           longitud));
                ELSIF (posicion = 5) THEN
                  v_indicadoractualizaciondatos.extend;
                  v_indicadoractualizaciondatos(i) := substr(lslinea,
                                                             inicio,
                                                             longitud);
                ELSIF (posicion = 6) THEN
                  v_primerapellido.extend;
                  v_primerapellido(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 7) THEN
                  v_segundoapellido.extend;
                  v_segundoapellido(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 8) THEN
                  v_primernombre.extend;
                  v_primernombre(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 9) THEN
                  v_segundonombre.extend;
                  v_segundonombre(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 10) THEN
                  v_codigoclienteprivilege.extend;
                  v_codigoclienteprivilege(i) := TRIM(substr(lslinea,
                                                             inicio,
                                                             longitud));
                ELSIF (posicion = 11) THEN
                  v_direccioncliente.extend;
                  v_direccioncliente(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 12) THEN
                  v_urbanizacion.extend;
                  v_urbanizacion(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 13) THEN
                  v_distrito.extend;
                  v_distrito(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 14) THEN
                  v_provincia.extend;
                  v_provincia(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 15) THEN
                  v_telefonoclienteprivilege.extend;
                  v_telefonoclienteprivilege(i) := TRIM(substr(lslinea,
                                                               inicio,
                                                               longitud));
                ELSIF (posicion = 16) THEN
                  v_otrotelefono.extend;
                  v_otrotelefono(i) := TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud));
                ELSIF (posicion = 17) THEN
                  v_email.extend;
                  v_email(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 18) THEN
                  v_fechanacimiento.extend;
                  v_fechanacimiento(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 19) THEN
                  v_condicion.extend;
                  v_condicion(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 20) THEN
                  v_codigocliente.extend;
                  v_codigocliente(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 21) THEN
                  v_unidadadm.extend;
                  v_unidadadm(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 22) THEN
                  v_indicadornormalseco.extend;
                  v_indicadornormalseco(i) := TRIM(substr(lslinea,
                                                          inicio,
                                                          longitud));
                ELSIF (posicion = 23) THEN
                  v_indicadornormalgraso.extend;
                  v_indicadornormalgraso(i) := TRIM(substr(lslinea,
                                                           inicio,
                                                           longitud));
                ELSIF (posicion = 24) THEN
                  v_indicadorgrasa.extend;
                  v_indicadorgrasa(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 25) THEN
                  v_indicadorarrugasgest.extend;
                  v_indicadorarrugasgest(i) := substr(lslinea,
                                                      inicio,
                                                      longitud);
                ELSIF (posicion = 26) THEN
                  v_indicadorarrugaspronun.extend;
                  v_indicadorarrugaspronun(i) := TRIM(substr(lslinea,
                                                             inicio,
                                                             longitud));
                ELSIF (posicion = 27) THEN
                  v_indicadormanchas.extend;
                  v_indicadormanchas(i) := TRIM(substr(lslinea,
                                                       inicio,
                                                       longitud));
                ELSIF (posicion = 28) THEN
                  v_indicadorimperfecciones.extend;
                  v_indicadorimperfecciones(i) := substr(lslinea,
                                                         inicio,
                                                         longitud);
                ELSIF (posicion = 29) THEN
                  v_indicadorflacidez.extend;
                  v_indicadorflacidez(i) := TRIM(substr(lslinea,
                                                        inicio,
                                                        longitud));
                ELSIF (posicion = 30) THEN
                  v_indicadorenvejecimiento.extend;
                  v_indicadorenvejecimiento(i) := TRIM(substr(lslinea,
                                                              inicio,
                                                              longitud));
                ELSIF (posicion = 31) THEN
                  v_codigoregion.extend;
                  v_codigoregion(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 32) THEN
                  v_codigozona.extend;
                  v_codigozona(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 33) THEN
                  v_fechaproceso.extend;
                  v_fechaproceso(i) := TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud));
                ELSIF (posicion = 34) THEN
                  v_statusproceso.extend;
                  v_statusproceso(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 35) THEN
                  v_motivorechazo.extend;
                  v_motivorechazo(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codigopais.count
    
      INSERT INTO int_ocr_ficha_inscr_privi
        (cod_pais,
         cod_cia,
         num_docu,
         cam_marc_insc,
         cam_marc_actu_dato,
         pri_apel,
         sec_apel,
         pri_nomb,
         sec_nomb,
         cod_clie_priv,
         dir_clie,
         val_urba,
         val_dist,
         val_prov,
         tel_clie_priv,
         otr_tele,
         cor_elec,
         fec_naci,
         val_cond,
         cod_clie,
         uni_admi,
         ind_norm_seco,
         ind_norm_gras,
         ind_gras,
         ind_arru_gest,
         ind_arru_pron,
         ind_manc,
         ind_impe,
         ind_flac,
         ind_enve,
         cod_regi_arri,
         cod_zona_arri,
         fec_proc_docu,
         sta_proc,
         cod_moti_rech)
      VALUES
        (v_codigopais(i),
         v_codigocia(i),
         v_numerodocumento(i),
         v_indicadorinscripcion(i),
         v_indicadoractualizaciondatos(i),
         v_primerapellido(i),
         v_segundoapellido(i),
         v_primernombre(i),
         v_segundonombre(i),
         v_codigoclienteprivilege(i),
         v_direccioncliente(i),
         v_urbanizacion(i),
         v_distrito(i),
         v_provincia(i),
         v_telefonoclienteprivilege(i),
         v_otrotelefono(i),
         v_email(i),
         v_fechanacimiento(i),
         v_condicion(i),
         v_codigocliente(i),
         v_unidadadm(i),
         v_indicadornormalseco(i),
         v_indicadornormalgraso(i),
         v_indicadorgrasa(i),
         v_indicadorarrugasgest(i),
         v_indicadorarrugaspronun(i),
         v_indicadormanchas(i),
         v_indicadorimperfecciones(i),
         v_indicadorflacidez(i),
         v_indicadorenvejecimiento(i),
         v_codigoregion(i),
         v_codigozona(i),
         v_fechaproceso(i),
         v_statusproceso(i),
         v_motivorechazo(i));
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_FICHA_PRIVI: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_ficha_privi;
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Cabeceras de Servicios Postventas
  Fecha Creacion    : 28/03/2008
  Autor             : Dennys Oliva Iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_posve_cabec
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psnumerolote     VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codigopais IS TABLE OF int_ocr_cabec_servi_postv.cod_pais%TYPE;
    TYPE t_codigocia IS TABLE OF int_ocr_cabec_servi_postv.cod_cia%TYPE;
    TYPE t_numerodocumento IS TABLE OF int_ocr_cabec_servi_postv.num_docu%TYPE;
    TYPE t_periodo IS TABLE OF int_ocr_cabec_servi_postv.cod_peri%TYPE;
    TYPE t_codigocliente IS TABLE OF int_ocr_cabec_servi_postv.cod_clie%TYPE;
    TYPE t_numerodocumentocruce IS TABLE OF int_ocr_cabec_servi_postv.num_docu_cruc%TYPE;
    TYPE t_tiposolicitud IS TABLE OF int_ocr_cabec_servi_postv.tip_soli%TYPE;
    TYPE t_codigosubacceso IS TABLE OF int_ocr_cabec_servi_postv.cod_sub_acces%TYPE;
    TYPE t_accesofisico IS TABLE OF int_ocr_cabec_servi_postv.acc_fisi%TYPE;
    TYPE t_fechaproceso IS TABLE OF int_ocr_cabec_servi_postv.fec_proc_doc%TYPE;
    TYPE t_codigoregion IS TABLE OF int_ocr_cabec_servi_postv.cod_regi_arri%TYPE;
    TYPE t_codigozona IS TABLE OF int_ocr_cabec_servi_postv.cod_zona_arri%TYPE;
    TYPE t_statusproceso IS TABLE OF int_ocr_cabec_servi_postv.sta_proc%TYPE;
    TYPE t_motivorechazo IS TABLE OF int_ocr_cabec_servi_postv.cod_moti_rech%TYPE;
    ----------------------
    v_codigopais           t_codigopais := t_codigopais();
    v_codigocia            t_codigocia := t_codigocia();
    v_numerodocumento      t_numerodocumento := t_numerodocumento();
    v_periodo              t_periodo := t_periodo();
    v_codigocliente        t_codigocliente := t_codigocliente();
    v_numerodocumentocruce t_numerodocumentocruce := t_numerodocumentocruce();
    v_tiposolicitud        t_tiposolicitud := t_tiposolicitud();
    v_codigosubacceso      t_codigosubacceso := t_codigosubacceso();
    v_accesofisico         t_accesofisico := t_accesofisico();
    v_fechaproceso         t_fechaproceso := t_fechaproceso();
    v_codigoregion         t_codigoregion := t_codigoregion();
    v_codigozona           t_codigozona := t_codigozona();
    v_statusproceso        t_statusproceso := t_statusproceso();
    v_motivorechazo        t_motivorechazo := t_motivorechazo();
    ----------------------
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20) || '+';
    lsreplace VARCHAR2(100) := 'a          ';
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codigopais.extend;
                  v_codigopais(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 2) THEN
                  v_codigocia.extend;
                  v_codigocia(i) := TRIM(translate(substr(lslinea,
                                                          inicio,
                                                          longitud),
                                                   lscadena,
                                                   lsreplace));
                  v_codigopais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codigopais(i),
                                                                                 v_codigocia(i));
                ELSIF (posicion = 3) THEN
                  v_numerodocumento.extend;
                  v_numerodocumento(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 4) THEN
                  v_periodo.extend;
                  v_periodo(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 5) THEN
                  v_codigocliente.extend;
                  v_codigocliente(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 6) THEN
                  v_numerodocumentocruce.extend;
                  v_numerodocumentocruce(i) := TRIM(translate(substr(lslinea,
                                                                     inicio,
                                                                     longitud),
                                                              lscadena,
                                                              lsreplace));
                ELSIF (posicion = 7) THEN
                  v_tiposolicitud.extend;
                  v_tiposolicitud(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 8) THEN
                  v_codigosubacceso.extend;
                  v_codigosubacceso(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 9) THEN
                  v_accesofisico.extend;
                  v_accesofisico(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 10) THEN
                  v_fechaproceso.extend;
                  v_fechaproceso(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 11) THEN
                  v_codigoregion.extend;
                  v_codigoregion(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 12) THEN
                  v_codigozona.extend;
                  v_codigozona(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 13) THEN
                  v_statusproceso.extend;
                  v_statusproceso(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 14) THEN
                  v_motivorechazo.extend;
                  v_motivorechazo(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codigopais.count
    
      INSERT INTO int_ocr_cabec_servi_postv
        (cod_pais,
         cod_cia,
         num_docu,
         cod_peri,
         cod_clie,
         num_docu_cruc,
         tip_soli,
         cod_sub_acces,
         acc_fisi,
         fec_proc_doc,
         cod_regi_arri,
         cod_zona_arri,
         sta_proc,
         cod_moti_rech,
         ind_orig,
         num_lote)
      VALUES
        (v_codigopais(i),
         v_codigocia(i),
         v_numerodocumento(i),
         v_periodo(i),
         v_codigocliente(i),
         v_numerodocumentocruce(i),
         v_tiposolicitud(i),
         v_codigosubacceso(i),
         v_accesofisico(i),
         v_fechaproceso(i),
         v_codigoregion(i),
         v_codigozona(i),
         v_statusproceso(i),
         v_motivorechazo(i),
         '1',
         psnumerolote);
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_POSVE_CABEC: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_posve_cabec;
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Detalles de Servicios Postventas
  Fecha Creacion    : 28/03/2008
  Autor             : Dennys Oliva Iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_posve_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psnumerolote     VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codigopais IS TABLE OF int_ocr_detal_servi_postv.cod_pais%TYPE;
    TYPE t_codigocompania IS TABLE OF int_ocr_detal_servi_postv.cod_cia%TYPE;
    TYPE t_numerodocumento IS TABLE OF int_ocr_detal_servi_postv.num_docu%TYPE;
    TYPE t_codigocliente IS TABLE OF int_ocr_detal_servi_postv.cod_clie%TYPE;
    TYPE t_tiporeferencia IS TABLE OF int_ocr_detal_servi_postv.tip_refe%TYPE;
    TYPE t_codigovtaproddevuelve IS TABLE OF int_ocr_detal_servi_postv.cod_vent_devu%TYPE;
    TYPE t_codigovtaproddesea IS TABLE OF int_ocr_detal_servi_postv.cod_vent_dese%TYPE;
    TYPE t_cantproddevuelve IS TABLE OF int_ocr_detal_servi_postv.can_prod_devu%TYPE;
    TYPE t_cantproddesea IS TABLE OF int_ocr_detal_servi_postv.can_prod_dese%TYPE;
    TYPE t_statusproceso IS TABLE OF int_ocr_detal_servi_postv.sta_proc%TYPE;
    TYPE t_motivorechazo IS TABLE OF int_ocr_detal_servi_postv.cod_moti_rech%TYPE;
    TYPE t_motivospv IS TABLE OF int_ocr_detal_servi_postv.mot_spv%TYPE;
  
    v_codigopais            t_codigopais := t_codigopais();
    v_codigocompania        t_codigocompania := t_codigocompania();
    v_numerodocumento       t_numerodocumento := t_numerodocumento();
    v_codigocliente         t_codigocliente := t_codigocliente();
    v_tiporeferencia        t_tiporeferencia := t_tiporeferencia();
    v_codigovtaproddevuelve t_codigovtaproddevuelve := t_codigovtaproddevuelve();
    v_codigovtaproddesea    t_codigovtaproddesea := t_codigovtaproddesea();
    v_cantproddevuelve      t_cantproddevuelve := t_cantproddevuelve();
    v_cantproddesea         t_cantproddesea := t_cantproddesea();
    v_statusproceso         t_statusproceso := t_statusproceso();
    v_motivorechazo         t_motivorechazo := t_motivorechazo();
    v_motivospv             t_motivospv := t_motivospv();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio    NUMBER := 0;
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20) || '+';
    lsreplace VARCHAR2(100) := 'a          ';
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
          /*
          translate(substr(lslinea,
                                                    inicio,
                                                    longitud),
                                             lscadena,
                                             lsreplace));*/
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codigopais.extend;
                  v_codigopais(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 2) THEN
                  v_codigocompania.extend;
                  v_codigocompania(i) := TRIM(translate(substr(lslinea,
                                                               inicio,
                                                               longitud),
                                                        lscadena,
                                                        lsreplace));
                  v_codigopais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codigopais(i),
                                                                                 v_codigocompania(i));
                ELSIF (posicion = 3) THEN
                  v_numerodocumento.extend;
                  v_numerodocumento(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 4) THEN
                  v_codigocliente.extend;
                  v_codigocliente(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 5) THEN
                  v_tiporeferencia.extend;
                  v_tiporeferencia(i) := TRIM(translate(substr(lslinea,
                                                               inicio,
                                                               longitud),
                                                        lscadena,
                                                        lsreplace));
                ELSIF (posicion = 6) THEN
                  v_codigovtaproddevuelve.extend;
                  v_codigovtaproddevuelve(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                ELSIF (posicion = 7) THEN
                  v_codigovtaproddesea.extend;
                  v_codigovtaproddesea(i) := TRIM(translate(substr(lslinea,
                                                                   inicio,
                                                                   longitud),
                                                            lscadena,
                                                            lsreplace));
                ELSIF (posicion = 8) THEN
                  v_cantproddevuelve.extend;
                  v_cantproddevuelve(i) := nvl(TRIM(translate(substr(lslinea,
                                                                     inicio,
                                                                     longitud),
                                                              lscadena,
                                                              lsreplace)),
                                               0);
                ELSIF (posicion = 9) THEN
                  v_cantproddesea.extend;
                  v_cantproddesea(i) := nvl(TRIM(translate(substr(lslinea,
                                                                  inicio,
                                                                  longitud),
                                                           lscadena,
                                                           lsreplace)),
                                            0);
                ELSIF (posicion = 10) THEN
                  v_statusproceso.extend;
                  v_statusproceso(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 11) THEN
                  v_motivorechazo.extend;
                  v_motivorechazo(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 12) THEN
                  v_motivospv.extend;
                  v_motivospv(i) := TRIM(translate(substr(lslinea,
                                                          inicio,
                                                          longitud),
                                                   lscadena,
                                                   lsreplace));
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codigopais.count
    
      INSERT INTO int_ocr_detal_servi_postv
        (cod_pais,
         cod_cia,
         num_docu,
         cod_clie,
         tip_refe,
         cod_vent_devu,
         cod_vent_dese,
         can_prod_devu,
         can_prod_dese,
         sta_proc,
         cod_moti_rech,
         mot_spv,
         cod_zona,
         cod_regi,
         num_lote)
      VALUES
        (v_codigopais(i),
         v_codigocompania(i),
         v_numerodocumento(i),
         v_codigocliente(i),
         v_tiporeferencia(i),
         v_codigovtaproddevuelve(i),
         v_codigovtaproddesea(i),
         v_cantproddevuelve(i),
         v_cantproddesea(i),
         v_statusproceso(i),
         v_motivorechazo(i),
         v_motivospv(i),
         (SELECT cod_zona_arri
            FROM int_ocr_cabec_servi_postv
           WHERE cod_pais = v_codigopais(i)
             AND cod_clie = v_codigocliente(i)
             AND num_docu = v_numerodocumento(i)
             AND rownum = 1),
         (SELECT cod_regi_arri
            FROM int_ocr_cabec_servi_postv
           WHERE cod_pais = v_codigopais(i)
             AND cod_clie = v_codigocliente(i)
             AND num_docu = v_numerodocumento(i)
             AND rownum = 1),
         psnumerolote);
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_POSVE_DETAL: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_posve_detal;
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion Solicitud de Credito
  Fecha Creacion    : 31/03/2008
  Autor             : Leonardo Lizana
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_solic_credi
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9 --anulado = 9; activo =1
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codpais IS TABLE OF int_ocr_solic_credi.cod_pais %TYPE;
    TYPE t_codcomp IS TABLE OF int_ocr_solic_credi.cod_comp %TYPE;
    TYPE t_codclie IS TABLE OF int_ocr_solic_credi.cod_clie %TYPE;
    TYPE t_numdocu IS TABLE OF int_ocr_solic_credi.num_docu %TYPE;
    TYPE t_fecproc IS TABLE OF int_ocr_solic_credi.fec_proc %TYPE;
    TYPE t_uniadmi IS TABLE OF int_ocr_solic_credi.uni_admi %TYPE;
    TYPE t_tipingr IS TABLE OF int_ocr_solic_credi.tip_ingr %TYPE;
    TYPE t_codperi IS TABLE OF int_ocr_solic_credi.cod_peri %TYPE;
    TYPE t_codclierete IS TABLE OF int_ocr_solic_credi.cod_clie_rete %TYPE;
    TYPE t_codprem IS TABLE OF int_ocr_solic_credi.cod_prem %TYPE;
    TYPE t_valape1 IS TABLE OF int_ocr_solic_credi.val_ape1 %TYPE;
    TYPE t_valape2 IS TABLE OF int_ocr_solic_credi.val_ape2 %TYPE;
    TYPE t_valnom1 IS TABLE OF int_ocr_solic_credi.val_nom1 %TYPE;
    TYPE t_valnom2 IS TABLE OF int_ocr_solic_credi.val_nom2 %TYPE;
    TYPE t_fecnaci IS TABLE OF int_ocr_solic_credi.fec_naci %TYPE;
    TYPE t_tipdocu IS TABLE OF int_ocr_solic_credi.tip_docu %TYPE;
    TYPE t_numdocuiden IS TABLE OF int_ocr_solic_credi.num_docu_iden %TYPE;
    TYPE t_numruc IS TABLE OF int_ocr_solic_credi.num_ruc %TYPE;
    TYPE t_indestacivi IS TABLE OF int_ocr_solic_credi.ind_esta_civi %TYPE;
    TYPE t_indniveeduc IS TABLE OF int_ocr_solic_credi.ind_nive_educ %TYPE;
    TYPE t_valdireclie IS TABLE OF int_ocr_solic_credi.val_dire_clie %TYPE;
    TYPE t_valbarrclie IS TABLE OF int_ocr_solic_credi.val_barr_clie %TYPE;
    TYPE t_valciudclie IS TABLE OF int_ocr_solic_credi.val_ciud_clie %TYPE;
    TYPE t_valdepaclie IS TABLE OF int_ocr_solic_credi.val_depa_clie %TYPE;
    TYPE t_valtelfclie IS TABLE OF int_ocr_solic_credi.val_telf_clie %TYPE;
    TYPE t_valceluclie IS TABLE OF int_ocr_solic_credi.val_celu_clie %TYPE;
    TYPE t_valtelf_trab IS TABLE OF int_ocr_solic_credi.val_telf_trab %TYPE;
    TYPE t_indventdire IS TABLE OF int_ocr_solic_credi.ind_vent_dire %TYPE;
    TYPE t_valmailclie IS TABLE OF int_ocr_solic_credi.val_mail_clie %TYPE;
    TYPE t_tipdocufiad IS TABLE OF int_ocr_solic_credi.tip_docu_fiad %TYPE;
    TYPE t_coddocuidfi IS TABLE OF int_ocr_solic_credi.cod_docu_idfi %TYPE;
    TYPE t_valape1fiad IS TABLE OF int_ocr_solic_credi.val_ape1_fiad %TYPE;
    TYPE t_valape2fiad IS TABLE OF int_ocr_solic_credi.val_ape2_fiad %TYPE;
    TYPE t_valnom1fiad IS TABLE OF int_ocr_solic_credi.val_nom1_fiad %TYPE;
    TYPE t_valnom2fiad IS TABLE OF int_ocr_solic_credi.val_nom2_fiad %TYPE;
    TYPE t_valdirefiad IS TABLE OF int_ocr_solic_credi.val_dire_fiad %TYPE;
    TYPE t_valbarrfiad IS TABLE OF int_ocr_solic_credi.val_barr_fiad %TYPE;
    TYPE t_valciudfiad IS TABLE OF int_ocr_solic_credi.val_ciud_fiad %TYPE;
    TYPE t_valdepafiad IS TABLE OF int_ocr_solic_credi.val_depa_fiad %TYPE;
    TYPE t_valteflfiad IS TABLE OF int_ocr_solic_credi.val_tefl_fiad %TYPE;
    TYPE t_valcelufiad IS TABLE OF int_ocr_solic_credi.val_celu_fiad %TYPE;
    TYPE t_valtelftrfi IS TABLE OF int_ocr_solic_credi.val_telf_trfi %TYPE;
    TYPE t_valregiarri IS TABLE OF int_ocr_solic_credi.val_regi_arri %TYPE;
    TYPE t_valzonaarri IS TABLE OF int_ocr_solic_credi.val_zona_arri %TYPE;
    TYPE t_indstatproc IS TABLE OF int_ocr_solic_credi.ind_stat_proc %TYPE;
    TYPE t_indmotirech IS TABLE OF int_ocr_solic_credi.ind_moti_rech %TYPE;
    TYPE t_tipviaclie IS TABLE OF int_ocr_solic_credi.tip_via_clie %TYPE;
    TYPE t_valnombvicl IS TABLE OF int_ocr_solic_credi.val_nomb_vicl %TYPE;
    TYPE t_numdireclie IS TABLE OF int_ocr_solic_credi.num_dire_clie %TYPE;
    TYPE t_coddepaclie IS TABLE OF int_ocr_solic_credi.cod_depa_clie %TYPE;
    TYPE t_codprovclie IS TABLE OF int_ocr_solic_credi.cod_prov_clie %TYPE;
    TYPE t_coddistclie IS TABLE OF int_ocr_solic_credi.cod_dist_clie %TYPE;
    TYPE t_codsectclie IS TABLE OF int_ocr_solic_credi.cod_sect_clie %TYPE;
    TYPE t_tipviafiad IS TABLE OF int_ocr_solic_credi.tip_via_fiad %TYPE;
    TYPE t_valnombvifi IS TABLE OF int_ocr_solic_credi.val_nomb_vifi %TYPE;
    TYPE t_numdirefiad IS TABLE OF int_ocr_solic_credi.num_dire_fiad %TYPE;
    TYPE t_coddepafiad IS TABLE OF int_ocr_solic_credi.cod_depa_fiad %TYPE;
    TYPE t_codprovfiad IS TABLE OF int_ocr_solic_credi.cod_prov_fiad %TYPE;
    TYPE t_coddistfiad IS TABLE OF int_ocr_solic_credi.cod_dist_fiad %TYPE;
    TYPE t_codsectfiad IS TABLE OF int_ocr_solic_credi.cod_sect_fiad %TYPE;
  
    ----------------------
    v_codpais      t_codpais := t_codpais();
    v_codcomp      t_codcomp := t_codcomp();
    v_codclie      t_codclie := t_codclie();
    v_numdocu      t_numdocu := t_numdocu();
    v_fecproc      t_fecproc := t_fecproc();
    v_uniadmi      t_uniadmi := t_uniadmi();
    v_tipingr      t_tipingr := t_tipingr();
    v_codperi      t_codperi := t_codperi();
    v_codclierete  t_codclierete := t_codclierete();
    v_codprem      t_codprem := t_codprem();
    v_valape1      t_valape1 := t_valape1();
    v_valape2      t_valape2 := t_valape2();
    v_valnom1      t_valnom1 := t_valnom1();
    v_valnom2      t_valnom2 := t_valnom2();
    v_fecnaci      t_fecnaci := t_fecnaci();
    v_tipdocu      t_tipdocu := t_tipdocu();
    v_numdocuiden  t_numdocuiden := t_numdocuiden();
    v_numruc       t_numruc := t_numruc();
    v_indestacivi  t_indestacivi := t_indestacivi();
    v_indniveeduc  t_indniveeduc := t_indniveeduc();
    v_valdireclie  t_valdireclie := t_valdireclie();
    v_valbarrclie  t_valbarrclie := t_valbarrclie();
    v_valciudclie  t_valciudclie := t_valciudclie();
    v_valdepaclie  t_valdepaclie := t_valdepaclie();
    v_valtelfclie  t_valtelfclie := t_valtelfclie();
    v_valceluclie  t_valceluclie := t_valceluclie();
    v_valtelf_trab t_valtelf_trab := t_valtelf_trab();
    v_indventdire  t_indventdire := t_indventdire();
    v_valmailclie  t_valmailclie := t_valmailclie();
    v_tipdocufiad  t_tipdocufiad := t_tipdocufiad();
    v_coddocuidfi  t_coddocuidfi := t_coddocuidfi();
    v_valape1fiad  t_valape1fiad := t_valape1fiad();
    v_valape2fiad  t_valape2fiad := t_valape2fiad();
    v_valnom1fiad  t_valnom1fiad := t_valnom1fiad();
    v_valnom2fiad  t_valnom2fiad := t_valnom2fiad();
    v_valdirefiad  t_valdirefiad := t_valdirefiad();
    v_valbarrfiad  t_valbarrfiad := t_valbarrfiad();
    v_valciudfiad  t_valciudfiad := t_valciudfiad();
    v_valdepafiad  t_valdepafiad := t_valdepafiad();
    v_valteflfiad  t_valteflfiad := t_valteflfiad();
    v_valcelufiad  t_valcelufiad := t_valcelufiad();
    v_valtelftrfi  t_valtelftrfi := t_valtelftrfi();
    v_valregiarri  t_valregiarri := t_valregiarri();
    v_valzonaarri  t_valzonaarri := t_valzonaarri();
    v_indstatproc  t_indstatproc := t_indstatproc();
    v_indmotirech  t_indmotirech := t_indmotirech();
    v_tipviaclie   t_tipviaclie := t_tipviaclie();
    v_valnombvicl  t_valnombvicl := t_valnombvicl();
    v_numdireclie  t_numdireclie := t_numdireclie();
    v_coddepaclie  t_coddepaclie := t_coddepaclie();
    v_codprovclie  t_codprovclie := t_codprovclie();
    v_coddistclie  t_coddistclie := t_coddistclie();
    v_codsectclie  t_codsectclie := t_codsectclie();
    v_tipviafiad   t_tipviafiad := t_tipviafiad();
    v_valnombvifi  t_valnombvifi := t_valnombvifi();
    v_numdirefiad  t_numdirefiad := t_numdirefiad();
    v_coddepafiad  t_coddepafiad := t_coddepafiad();
    v_codprovfiad  t_codprovfiad := t_codprovfiad();
    v_coddistfiad  t_coddistfiad := t_coddistfiad();
    v_codsectfiad  t_codsectfiad := t_codsectfiad();
  
    ----------------------
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio                NUMBER := 0;
    lscodigotipodocumento VARCHAR2(4);
    lstipodocu            VARCHAR2(2);
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  
    /*lscadena VARCHAR2(4):='<>;''|||char(13)';
    lsreplace VARCHAR2(4):='     ';*/
    -------------------------
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle,
                                                 4000);
  
    SELECT t.cod_tipo_docu
      INTO lscodigotipodocumento
      FROM sto_tipo_docum_digit t
     WHERE t.inte_cod_inte = pscodigointerfaz;
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_codpais(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 2) THEN
                  v_codcomp.extend;
                  v_codcomp(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                  v_codpais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codpais(i),
                                                                              v_codcomp(i));
                ELSIF (posicion = 3) THEN
                  v_codclie.extend;
                  v_codclie(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 4) THEN
                  v_numdocu.extend;
                  v_numdocu(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 5) THEN
                  v_fecproc.extend;
                
                  v_fecproc(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                
                ELSIF (posicion = 6) THEN
                  v_uniadmi.extend;
                  v_uniadmi(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 7) THEN
                  v_tipingr.extend;
                  v_tipingr(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 8) THEN
                  v_codperi.extend;
                  v_codperi(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 9) THEN
                  v_codclierete.extend;
                  v_codclierete(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 10) THEN
                  v_codprem.extend;
                  v_codprem(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 11) THEN
                  v_valape1.extend;
                  v_valape1(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 12) THEN
                  v_valape2.extend;
                  v_valape2(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 13) THEN
                  v_valnom1.extend;
                  v_valnom1(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 14) THEN
                  v_valnom2.extend;
                  v_valnom2(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 15) THEN
                  v_fecnaci.extend;
                  v_fecnaci(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 16) THEN
                  v_tipdocu.extend;
                  v_tipdocu(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 17) THEN
                  v_numdocuiden.extend;
                  v_numdocuiden(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 18) THEN
                  v_numruc.extend;
                  v_numruc(i) := TRIM(translate(substr(lslinea,
                                                       inicio,
                                                       longitud),
                                                lscadena,
                                                lsreplace));
                ELSIF (posicion = 19) THEN
                  v_indestacivi.extend;
                  v_indestacivi(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 20) THEN
                  v_indniveeduc.extend;
                  v_indniveeduc(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 21) THEN
                  v_valdireclie.extend;
                  v_valdireclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 22) THEN
                  v_valbarrclie.extend;
                  v_valbarrclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 23) THEN
                  v_valciudclie.extend;
                  v_valciudclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 24) THEN
                  v_valdepaclie.extend;
                  v_valdepaclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 25) THEN
                  v_valtelfclie.extend;
                  v_valtelfclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 26) THEN
                  v_valceluclie.extend;
                  v_valceluclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 27) THEN
                  v_valtelf_trab.extend;
                  v_valtelf_trab(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 28) THEN
                  v_indventdire.extend;
                  v_indventdire(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 29) THEN
                  v_valmailclie.extend;
                  v_valmailclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 30) THEN
                  v_tipdocufiad.extend;
                  v_tipdocufiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 31) THEN
                  v_coddocuidfi.extend;
                  v_coddocuidfi(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 32) THEN
                  v_valape1fiad.extend;
                  v_valape1fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 33) THEN
                  v_valape2fiad.extend;
                  v_valape2fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 34) THEN
                  v_valnom1fiad.extend;
                  v_valnom1fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 35) THEN
                  v_valnom2fiad.extend;
                  v_valnom2fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 36) THEN
                  v_valdirefiad.extend;
                  v_valdirefiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 37) THEN
                  v_valbarrfiad.extend;
                  v_valbarrfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 38) THEN
                  v_valciudfiad.extend;
                  v_valciudfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 39) THEN
                  v_valdepafiad.extend;
                  v_valdepafiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 40) THEN
                  v_valteflfiad.extend;
                  v_valteflfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 41) THEN
                  v_valcelufiad.extend;
                  v_valcelufiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 42) THEN
                  v_valtelftrfi.extend;
                  v_valtelftrfi(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 43) THEN
                  v_valregiarri.extend;
                  v_valregiarri(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 44) THEN
                  v_valzonaarri.extend;
                  v_valzonaarri(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 45) THEN
                  v_indstatproc.extend;
                  v_indstatproc(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 46) THEN
                  v_indmotirech.extend;
                  v_indmotirech(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 47) THEN
                  v_tipviaclie.extend;
                  v_tipviaclie(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 48) THEN
                  v_valnombvicl.extend;
                  v_valnombvicl(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 49) THEN
                  v_numdireclie.extend;
                  v_numdireclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 50) THEN
                  v_coddepaclie.extend;
                  v_coddepaclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 51) THEN
                  v_codprovclie.extend;
                  v_codprovclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 52) THEN
                  v_coddistclie.extend;
                  v_coddistclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 53) THEN
                  v_codsectclie.extend;
                  v_codsectclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 54) THEN
                  v_tipviafiad.extend;
                  v_tipviafiad(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 55) THEN
                  v_valnombvifi.extend;
                  v_valnombvifi(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 56) THEN
                  v_numdirefiad.extend;
                  v_numdirefiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 57) THEN
                  v_coddepafiad.extend;
                  v_coddepafiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 58) THEN
                  v_codprovfiad.extend;
                  v_codprovfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 59) THEN
                  v_coddistfiad.extend;
                  v_coddistfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 60) THEN
                  v_codsectfiad.extend;
                  v_codsectfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    lstipodocu := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                       'STO_TIPO_DOCU');
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codpais.count
    
      INSERT INTO int_ocr_solic_credi
        (cod_pais,
         cod_comp,
         cod_clie,
         num_docu,
         fec_proc,
         uni_admi,
         tip_ingr,
         cod_peri,
         cod_clie_rete,
         cod_prem,
         val_ape1,
         val_ape2,
         val_nom1,
         val_nom2,
         fec_naci,
         tip_docu,
         num_docu_iden,
         num_ruc,
         ind_esta_civi,
         ind_nive_educ,
         val_dire_clie,
         val_barr_clie,
         val_ciud_clie,
         val_depa_clie,
         val_telf_clie,
         val_celu_clie,
         val_telf_trab,
         ind_vent_dire,
         val_mail_clie,
         tip_docu_fiad,
         cod_docu_idfi,
         val_ape1_fiad,
         val_ape2_fiad,
         val_nom1_fiad,
         val_nom2_fiad,
         val_dire_fiad,
         val_barr_fiad,
         val_ciud_fiad,
         val_depa_fiad,
         val_tefl_fiad,
         val_celu_fiad,
         val_telf_trfi,
         val_regi_arri,
         val_zona_arri,
         ind_stat_proc,
         ind_moti_rech,
         tip_via_clie,
         val_nomb_vicl,
         num_dire_clie,
         cod_depa_clie,
         cod_prov_clie,
         cod_dist_clie,
         cod_sect_clie,
         tip_via_fiad,
         val_nomb_vifi,
         num_dire_fiad,
         cod_depa_fiad,
         cod_prov_fiad,
         cod_dist_fiad,
         cod_sect_fiad,
         ind_orig)
      VALUES
        (v_codpais(i),
         v_codcomp(i),
         v_codclie(i),
         v_numdocu(i),
         v_fecproc(i),
         v_uniadmi(i),
         v_tipingr(i),
         v_codperi(i),
         v_codclierete(i),
         v_codprem(i),
         v_valape1(i),
         v_valape2(i),
         v_valnom1(i),
         v_valnom2(i),
         v_fecnaci(i),
         nvl(TRIM(v_tipdocu(i)), lstipodocu),
         v_numdocuiden(i),
         v_numruc(i),
         v_indestacivi(i),
         v_indniveeduc(i),
         v_valdireclie(i),
         v_valbarrclie(i),
         v_valciudclie(i),
         v_valdepaclie(i),
         v_valtelfclie(i),
         v_valceluclie(i),
         v_valtelf_trab(i),
         v_indventdire(i),
         v_valmailclie(i),
         v_tipdocufiad(i),
         v_coddocuidfi(i),
         v_valape1fiad(i),
         v_valape2fiad(i),
         v_valnom1fiad(i),
         v_valnom2fiad(i),
         v_valdirefiad(i),
         v_valbarrfiad(i),
         v_valciudfiad(i),
         v_valdepafiad(i),
         v_valteflfiad(i),
         v_valcelufiad(i),
         v_valtelftrfi(i),
         v_valregiarri(i),
         v_valzonaarri(i),
         v_indstatproc(i),
         v_indmotirech(i),
         v_tipviaclie(i),
         v_valnombvicl(i),
         v_numdireclie(i),
         v_coddepaclie(i),
         v_codprovclie(i),
         v_coddistclie(i),
         v_codsectclie(i),
         v_tipviafiad(i),
         v_valnombvifi(i),
         v_numdirefiad(i),
         v_coddepafiad(i),
         v_codprovfiad(i),
         v_coddistfiad(i),
         v_codsectfiad(i),
         psindicadororigen);
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_DWH_OCR: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_solic_credi;
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion Seguimiento al Pedido
  Fecha Creacion    : 31/03/2008
  Autor             : Leonardo Lizana
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_segui_pedid
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9 --anulado = 9; activo =1
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_segui_pedid.cod_pais %TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_segui_pedid.cod_comp %TYPE;
    TYPE t_cod_regi IS TABLE OF int_ocr_segui_pedid.cod_regi %TYPE;
    TYPE t_cod_zona IS TABLE OF int_ocr_segui_pedid.cod_zona %TYPE;
    TYPE t_cod_etap IS TABLE OF int_ocr_segui_pedid.cod_etap %TYPE;
    TYPE t_cod_clie IS TABLE OF int_ocr_segui_pedid.cod_clie %TYPE;
    TYPE t_tip_docu IS TABLE OF int_ocr_segui_pedid.tip_docu %TYPE;
    TYPE t_num_docu IS TABLE OF int_ocr_segui_pedid.num_docu %TYPE;
    TYPE t_fec_ejec IS TABLE OF int_ocr_segui_pedid.fec_ejec %TYPE;
    TYPE t_hor_ejec IS TABLE OF int_ocr_segui_pedid.hor_ejec %TYPE;
    TYPE t_cod_nove IS TABLE OF int_ocr_segui_pedid.cod_nove %TYPE;
  
    ----------------------
    v_codpais t_cod_pais := t_cod_pais();
    v_codcomp t_cod_comp := t_cod_comp();
    v_codregi t_cod_regi := t_cod_regi();
    v_codzona t_cod_zona := t_cod_zona();
    v_codetap t_cod_etap := t_cod_etap();
    v_codclie t_cod_clie := t_cod_clie();
    v_tipdocu t_tip_docu := t_tip_docu();
    v_numdocu t_num_docu := t_num_docu();
    v_fecejec t_fec_ejec := t_fec_ejec();
    v_horejec t_hor_ejec := t_hor_ejec();
    v_codnove t_cod_nove := t_cod_nove();
  
    ----------------------
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
    -------------------------
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_codpais(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 2) THEN
                  v_codcomp.extend;
                  v_codcomp(i) := TRIM(substr(lslinea, inicio, longitud));
                  v_codpais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codpais(i),
                                                                              v_codcomp(i));
                ELSIF (posicion = 3) THEN
                  v_codregi.extend;
                  v_codregi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_codzona.extend;
                  v_codzona(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 5) THEN
                  v_codetap.extend;
                  v_codetap(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 6) THEN
                  v_codclie.extend;
                  v_codclie(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 7) THEN
                  v_tipdocu.extend;
                  v_tipdocu(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 8) THEN
                  v_numdocu.extend;
                  v_numdocu(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 9) THEN
                  v_fecejec.extend;
                  v_fecejec(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 10) THEN
                  v_horejec.extend;
                  v_horejec(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 11) THEN
                  v_codnove.extend;
                  v_codnove(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codpais.count
      INSERT INTO int_ocr_segui_pedid
        (cod_pais,
         cod_comp,
         cod_regi,
         cod_zona,
         cod_etap,
         cod_clie,
         tip_docu,
         num_docu,
         fec_ejec,
         hor_ejec,
         cod_nove)
      VALUES
        (v_codpais(i),
         v_codcomp(i),
         v_codregi(i),
         v_codzona(i),
         v_codetap(i),
         v_codclie(i),
         v_tipdocu(i),
         v_numdocu(i),
         v_fecejec(i),
         v_horejec(i),
         v_codnove(i));
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_DWH_OCR: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_segui_pedid;
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion Arribo de Zonas
  Fecha Creacion    : 31/03/2008
  Autor             : Leonardo Lizana
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_arrib_zonas
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9 --anulado = 9; activo =1
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_arrib_zonas.cod_pais %TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_arrib_zonas.cod_comp %TYPE;
    TYPE t_cod_regi IS TABLE OF int_ocr_arrib_zonas.cod_regi %TYPE;
    TYPE t_cod_zona IS TABLE OF int_ocr_arrib_zonas.cod_zona %TYPE;
    TYPE t_fec_proc IS TABLE OF int_ocr_arrib_zonas.fec_proc %TYPE;
    TYPE t_cod_peri IS TABLE OF int_ocr_arrib_zonas.cod_peri %TYPE;
    TYPE t_hor_proc IS TABLE OF int_ocr_arrib_zonas.hor_proc %TYPE;
  
    ----------------------
    v_codpais t_cod_pais := t_cod_pais();
    v_codcomp t_cod_comp := t_cod_comp();
    v_codregi t_cod_regi := t_cod_regi();
    v_codzona t_cod_zona := t_cod_zona();
    v_fecproc t_fec_proc := t_fec_proc();
    v_codperi t_cod_peri := t_cod_peri();
    v_horproc t_hor_proc := t_hor_proc();
  
    ----------------------
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
    -------------------------
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_codpais(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 2) THEN
                  v_codcomp.extend;
                  v_codcomp(i) := TRIM(substr(lslinea, inicio, longitud));
                  v_codpais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codpais(i),
                                                                              v_codcomp(i));
                ELSIF (posicion = 3) THEN
                  v_codregi.extend;
                  v_codregi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_codzona.extend;
                  v_codzona(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 5) THEN
                  v_fecproc.extend;
                  v_fecproc(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 6) THEN
                  v_codperi.extend;
                  v_codperi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 7) THEN
                  v_horproc.extend;
                  v_horproc(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codpais.count
      INSERT INTO int_ocr_arrib_zonas
        (cod_pais,
         cod_comp,
         cod_regi,
         cod_zona,
         fec_proc,
         cod_peri,
         hor_proc)
      VALUES
        (v_codpais(i),
         v_codcomp(i),
         v_codregi(i),
         v_codzona(i),
         v_fecproc(i),
         v_codperi(i),
         v_horproc(i));
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_DWH_OCR: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_arrib_zonas;
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion Solicitud Premios Privilege Detalle
  Fecha Creacion    : 31/03/2008
  Autor             : Leonardo Lizana
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_solpp_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9 --anulado = 9; activo =1
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_solpp_detal.cod_pais %TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_solpp_detal.cod_comp %TYPE;
    TYPE t_num_docu IS TABLE OF int_ocr_solpp_detal.num_docu %TYPE;
    TYPE t_id_prin IS TABLE OF int_ocr_solpp_detal.id_prin %TYPE;
    TYPE t_cod_clie_priv IS TABLE OF int_ocr_solpp_detal.cod_clie_priv %TYPE;
    TYPE t_cod_clie IS TABLE OF int_ocr_solpp_detal.cod_clie %TYPE;
    TYPE t_cod_prod IS TABLE OF int_ocr_solpp_detal.cod_prod %TYPE;
    TYPE t_val_cant IS TABLE OF int_ocr_solpp_detal.val_cant %TYPE;
  
    ----------------------
    v_codpais     t_cod_pais := t_cod_pais();
    v_codcomp     t_cod_comp := t_cod_comp();
    v_numdocu     t_num_docu := t_num_docu();
    v_idprin      t_id_prin := t_id_prin();
    v_codcliepriv t_cod_clie_priv := t_cod_clie_priv();
    v_codclie     t_cod_clie := t_cod_clie();
    v_codprod     t_cod_prod := t_cod_prod();
    v_valcant     t_val_cant := t_val_cant();
  
    ----------------------
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
    -------------------------
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_codpais(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 2) THEN
                  v_codcomp.extend;
                  v_codcomp(i) := TRIM(substr(lslinea, inicio, longitud));
                  v_codpais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codpais(i),
                                                                              v_codcomp(i));
                ELSIF (posicion = 3) THEN
                  v_numdocu.extend;
                  v_numdocu(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_idprin.extend;
                  v_idprin(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 5) THEN
                  v_codcliepriv.extend;
                  v_codcliepriv(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 6) THEN
                  v_codclie.extend;
                  v_codclie(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 7) THEN
                  v_codprod.extend;
                  v_codprod(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 8) THEN
                  v_valcant.extend;
                  v_valcant(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codpais.count
      INSERT INTO int_ocr_solpp_detal
        (cod_pais,
         cod_comp,
         num_docu,
         id_prin,
         cod_clie_priv,
         cod_clie,
         cod_prod,
         val_cant)
      VALUES
        (v_codpais(i),
         v_codcomp(i),
         v_numdocu(i),
         v_idprin(i),
         v_codcliepriv(i),
         v_codclie(i),
         v_codprod(i),
         v_valcant(i));
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_DWH_OCR: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_solpp_detal;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Actualizacion
                      de Datos
  Fecha Creacion    : 01/04/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_actua_datos
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_actua_datos.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_actua_datos.cod_comp%TYPE;
    TYPE t_cod_clie IS TABLE OF int_ocr_actua_datos.cod_clie%TYPE;
    TYPE t_num_docu IS TABLE OF int_ocr_actua_datos.num_docu%TYPE;
    TYPE t_fec_proc IS TABLE OF int_ocr_actua_datos.fec_proc%TYPE;
    TYPE t_cod_unid_admi IS TABLE OF int_ocr_actua_datos.cod_unid_admi%TYPE;
    TYPE t_cod_peri IS TABLE OF int_ocr_actua_datos.cod_peri%TYPE;
    TYPE t_val_ape1 IS TABLE OF int_ocr_actua_datos.val_ape1%TYPE;
    TYPE t_val_ape2 IS TABLE OF int_ocr_actua_datos.val_ape2%TYPE;
    TYPE t_val_nom1 IS TABLE OF int_ocr_actua_datos.val_nom1%TYPE;
    TYPE t_val_nom2 IS TABLE OF int_ocr_actua_datos.val_nom2%TYPE;
    TYPE t_cod_tipo_docu IS TABLE OF int_ocr_actua_datos.cod_tipo_docu%TYPE;
    TYPE t_val_docu_nuev IS TABLE OF int_ocr_actua_datos.num_docu_iden%TYPE;
    TYPE t_val_dire_nuev IS TABLE OF int_ocr_actua_datos.val_dire_nuev%TYPE;
    TYPE t_des_banc_nuev IS TABLE OF int_ocr_actua_datos.des_banc_nuev%TYPE;
    TYPE t_des_ciud_nuev IS TABLE OF int_ocr_actua_datos.des_ciud_nuev%TYPE;
    TYPE t_des_depa_nuev IS TABLE OF int_ocr_actua_datos.des_depa_nuev%TYPE;
    TYPE t_val_tele_nuev IS TABLE OF int_ocr_actua_datos.val_tele_nuev%TYPE;
    TYPE t_val_celu_nuev IS TABLE OF int_ocr_actua_datos.val_celu_nuev%TYPE;
    TYPE t_val_tele_cetr IS TABLE OF int_ocr_actua_datos.val_tele_cetr%TYPE;
    TYPE t_val_mail_nuev IS TABLE OF int_ocr_actua_datos.val_mail_nuev%TYPE;
    TYPE t_cod_regi IS TABLE OF int_ocr_actua_datos.cod_regi%TYPE;
    TYPE t_cod_zona IS TABLE OF int_ocr_actua_datos.cod_zona%TYPE;
    TYPE t_cod_stat_proc IS TABLE OF int_ocr_actua_datos.cod_stat_proc%TYPE;
    TYPE t_cod_moti_rech IS TABLE OF int_ocr_actua_datos.cod_moti_rech%TYPE;
    TYPE t_cod_tipo_via IS TABLE OF int_ocr_actua_datos.cod_tipo_via%TYPE;
    TYPE t_val_nomb_via IS TABLE OF int_ocr_actua_datos.val_nomb_via%TYPE;
    TYPE t_num_dire IS TABLE OF int_ocr_actua_datos.num_dire%TYPE;
    TYPE t_cod_depa IS TABLE OF int_ocr_actua_datos.cod_depa%TYPE;
    TYPE t_cod_prov IS TABLE OF int_ocr_actua_datos.cod_prov%TYPE;
    TYPE t_cod_dist IS TABLE OF int_ocr_actua_datos.cod_dist%TYPE;
    TYPE t_cod_cent_pobl IS TABLE OF int_ocr_actua_datos.cod_cent_pobl%TYPE;
  
    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_comp      t_cod_comp := t_cod_comp();
    v_cod_clie      t_cod_clie := t_cod_clie();
    v_num_docu      t_num_docu := t_num_docu();
    v_fec_proc      t_fec_proc := t_fec_proc();
    v_cod_unid_admi t_cod_unid_admi := t_cod_unid_admi();
    v_cod_peri      t_cod_peri := t_cod_peri();
    v_val_ape1      t_val_ape1 := t_val_ape1();
    v_val_ape2      t_val_ape2 := t_val_ape2();
    v_val_nom1      t_val_nom1 := t_val_nom1();
    v_val_nom2      t_val_nom2 := t_val_nom2();
    v_cod_tipo_docu t_cod_tipo_docu := t_cod_tipo_docu();
    v_val_docu_nuev t_val_docu_nuev := t_val_docu_nuev();
    v_val_dire_nuev t_val_dire_nuev := t_val_dire_nuev();
    v_des_banc_nuev t_des_banc_nuev := t_des_banc_nuev();
    v_des_ciud_nuev t_des_ciud_nuev := t_des_ciud_nuev();
    v_des_depa_nuev t_des_depa_nuev := t_des_depa_nuev();
    v_val_tele_nuev t_val_tele_nuev := t_val_tele_nuev();
    v_val_celu_nuev t_val_celu_nuev := t_val_celu_nuev();
    v_val_tele_cetr t_val_tele_cetr := t_val_tele_cetr();
    v_val_mail_nuev t_val_mail_nuev := t_val_mail_nuev();
    v_cod_regi      t_cod_regi := t_cod_regi();
    v_cod_zona      t_cod_zona := t_cod_zona();
    v_cod_stat_proc t_cod_stat_proc := t_cod_stat_proc();
    v_cod_moti_rech t_cod_moti_rech := t_cod_moti_rech();
    v_cod_tipo_via  t_cod_tipo_via := t_cod_tipo_via();
    v_val_nomb_via  t_val_nomb_via := t_val_nomb_via();
    v_num_dire      t_num_dire := t_num_dire();
    v_cod_depa      t_cod_depa := t_cod_depa();
    v_cod_prov      t_cod_prov := t_cod_prov();
    v_cod_dist      t_cod_dist := t_cod_dist();
    v_cod_cent_pobl t_cod_cent_pobl := t_cod_cent_pobl();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
        
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                  v_cod_pais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_cod_pais(i),
                                                                               v_cod_comp(i));
                ELSIF (posicion = 3) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 4) THEN
                  v_num_docu.extend;
                  v_num_docu(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 5) THEN
                  v_fec_proc.extend;
                  v_fec_proc(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 6) THEN
                  v_cod_unid_admi.extend;
                  v_cod_unid_admi(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 7) THEN
                  v_cod_peri.extend;
                  v_cod_peri(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 8) THEN
                  v_val_ape1.extend;
                  v_val_ape1(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 9) THEN
                  v_val_ape2.extend;
                  v_val_ape2(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 10) THEN
                  v_val_nom1.extend;
                  v_val_nom1(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 11) THEN
                  v_val_nom2.extend;
                  v_val_nom2(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 12) THEN
                  v_cod_tipo_docu.extend;
                  v_cod_tipo_docu(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 13) THEN
                  v_val_docu_nuev.extend;
                  v_val_docu_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 14) THEN
                  v_val_dire_nuev.extend;
                  v_val_dire_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 15) THEN
                  v_des_banc_nuev.extend;
                  v_des_banc_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 16) THEN
                  v_des_ciud_nuev.extend;
                  v_des_ciud_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 17) THEN
                  v_des_depa_nuev.extend;
                  v_des_depa_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 18) THEN
                  v_val_tele_nuev.extend;
                  v_val_tele_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 19) THEN
                  v_val_celu_nuev.extend;
                  v_val_celu_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 20) THEN
                  v_val_tele_cetr.extend;
                  v_val_tele_cetr(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 21) THEN
                  v_val_mail_nuev.extend;
                  v_val_mail_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 22) THEN
                  v_cod_regi.extend;
                  v_cod_regi(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 23) THEN
                  v_cod_zona.extend;
                  v_cod_zona(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 24) THEN
                  v_cod_stat_proc.extend;
                  v_cod_stat_proc(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 25) THEN
                  v_cod_moti_rech.extend;
                  v_cod_moti_rech(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 26) THEN
                  v_cod_tipo_via.extend;
                  v_cod_tipo_via(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 27) THEN
                  v_val_nomb_via.extend;
                  v_val_nomb_via(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 28) THEN
                  v_num_dire.extend;
                  v_num_dire(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 29) THEN
                  v_cod_depa.extend;
                  v_cod_depa(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 30) THEN
                  v_cod_prov.extend;
                  v_cod_prov(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 31) THEN
                  v_cod_dist.extend;
                  v_cod_dist(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 32) THEN
                  v_cod_cent_pobl.extend;
                  v_cod_cent_pobl(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_cod_pais.count
      INSERT INTO int_ocr_actua_datos
        (cod_pais,
         cod_comp,
         cod_clie,
         num_docu,
         fec_proc,
         cod_unid_admi,
         cod_peri,
         val_ape1,
         val_ape2,
         val_nom1,
         val_nom2,
         cod_tipo_docu,
         num_docu_iden,
         val_dire_nuev,
         des_banc_nuev,
         des_ciud_nuev,
         des_depa_nuev,
         val_tele_nuev,
         val_celu_nuev,
         val_tele_cetr,
         val_mail_nuev,
         cod_regi,
         cod_zona,
         cod_stat_proc,
         cod_moti_rech,
         cod_tipo_via,
         val_nomb_via,
         num_dire,
         cod_depa,
         cod_prov,
         cod_dist,
         cod_cent_pobl,
         ind_orig)
      VALUES
        (v_cod_pais(i),
         v_cod_comp(i),
         v_cod_clie(i),
         v_num_docu(i),
         v_fec_proc(i),
         v_cod_unid_admi(i),
         v_cod_peri(i),
         v_val_ape1(i),
         v_val_ape2(i),
         v_val_nom1(i),
         v_val_nom2(i),
         v_cod_tipo_docu(i),
         v_val_docu_nuev(i),
         v_val_dire_nuev(i),
         v_des_banc_nuev(i),
         v_des_ciud_nuev(i),
         v_des_depa_nuev(i),
         v_val_tele_nuev(i),
         v_val_celu_nuev(i),
         v_val_tele_cetr(i),
         v_val_mail_nuev(i),
         v_cod_regi(i),
         v_cod_zona(i),
         v_cod_stat_proc(i),
         v_cod_moti_rech(i),
         v_cod_tipo_via(i),
         v_val_nomb_via(i),
         v_num_dire(i),
         v_cod_depa(i),
         v_cod_prov(i),
         v_cod_dist(i),
         v_cod_cent_pobl(i),
         psindicadororigen);
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_ACTUA_DATOS: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_recep_actua_datos;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Pagare
  Fecha Creacion    : 01/04/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_pagar
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_pagar.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_pagar.cod_comp%TYPE;
    TYPE t_num_docu IS TABLE OF int_ocr_pagar.num_docu%TYPE;
    TYPE t_cod_regi_arri IS TABLE OF int_ocr_pagar.cod_regi_arri%TYPE;
    TYPE t_cod_zona_arri IS TABLE OF int_ocr_pagar.cod_zona_arri%TYPE;
    TYPE t_fec_proc IS TABLE OF int_ocr_pagar.fec_proc%TYPE;
    TYPE t_cod_stat_proc IS TABLE OF int_ocr_pagar.cod_stat_proc%TYPE;
    TYPE t_cod_moti_rech IS TABLE OF int_ocr_pagar.cod_moti_rech%TYPE;
  
    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_comp      t_cod_comp := t_cod_comp();
    v_num_docu      t_num_docu := t_num_docu();
    v_cod_regi_arri t_cod_regi_arri := t_cod_regi_arri();
    v_cod_zona_arri t_cod_zona_arri := t_cod_zona_arri();
    v_fec_proc      t_fec_proc := t_fec_proc();
    v_cod_stat_proc t_cod_stat_proc := t_cod_stat_proc();
    v_cod_moti_rech t_cod_moti_rech := t_cod_moti_rech();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
  BEGIN
  
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
        
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := substr(lslinea, inicio, longitud);
                  v_cod_pais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_cod_pais(i),
                                                                               v_cod_comp(i));
                ELSIF (posicion = 3) THEN
                  v_num_docu.extend;
                  v_num_docu(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 4) THEN
                  v_cod_regi_arri.extend;
                  v_cod_regi_arri(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 5) THEN
                  v_cod_zona_arri.extend;
                  v_cod_zona_arri(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 6) THEN
                  v_fec_proc.extend;
                  v_fec_proc(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 7) THEN
                  v_cod_stat_proc.extend;
                  v_cod_stat_proc(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 8) THEN
                  v_cod_moti_rech.extend;
                  v_cod_moti_rech(i) := substr(lslinea, inicio, longitud);
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_cod_pais.count
      INSERT INTO int_ocr_pagar
        (cod_pais,
         cod_comp,
         num_docu,
         cod_regi_arri,
         cod_zona_arri,
         fec_proc,
         cod_stat_proc,
         cod_moti_rech)
      VALUES
        (v_cod_pais(i),
         v_cod_comp(i),
         v_num_docu(i),
         v_cod_regi_arri(i),
         v_cod_zona_arri(i),
         v_fec_proc(i),
         v_cod_stat_proc(i),
         v_cod_moti_rech(i));
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_PAGAR: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_recep_pagar;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Dupla Cyzone
  Fecha Creacion    : 01/04/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_dupla_cyzon
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_dupla_cyzon.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_dupla_cyzon.cod_comp%TYPE;
    TYPE t_cod_clie IS TABLE OF int_ocr_dupla_cyzon.cod_clie%TYPE;
    TYPE t_num_docu IS TABLE OF int_ocr_dupla_cyzon.num_docu%TYPE;
    TYPE t_val_ape1 IS TABLE OF int_ocr_dupla_cyzon.val_ape1%TYPE;
    TYPE t_val_ape2 IS TABLE OF int_ocr_dupla_cyzon.val_ape2%TYPE;
    TYPE t_val_nom1 IS TABLE OF int_ocr_dupla_cyzon.val_nom1%TYPE;
    TYPE t_val_nom2 IS TABLE OF int_ocr_dupla_cyzon.val_nom2%TYPE;
    TYPE t_fec_naci IS TABLE OF int_ocr_dupla_cyzon.fec_naci%TYPE;
    TYPE t_val_mail_dupl IS TABLE OF int_ocr_dupla_cyzon.val_mail_dupl%TYPE;
    TYPE t_val_tele_casa IS TABLE OF int_ocr_dupla_cyzon.val_tele_casa%TYPE;
    TYPE t_val_tele_celu IS TABLE OF int_ocr_dupla_cyzon.val_tele_celu%TYPE;
    TYPE t_val_dupl_nuev IS TABLE OF int_ocr_dupla_cyzon.val_dupl_nuev%TYPE;
    TYPE t_val_actu_dato IS TABLE OF int_ocr_dupla_cyzon.val_actu_dato%TYPE;
    TYPE t_ind_envi IS TABLE OF int_ocr_dupla_cyzon.ind_envi%TYPE;
    TYPE t_cod_regi_arri IS TABLE OF int_ocr_dupla_cyzon.cod_regi_arri%TYPE;
    TYPE t_cod_zona_arri IS TABLE OF int_ocr_dupla_cyzon.cod_zona_arri%TYPE;
    TYPE t_fec_proc IS TABLE OF int_ocr_dupla_cyzon.fec_proc%TYPE;
    TYPE t_cod_stat_proc IS TABLE OF int_ocr_dupla_cyzon.cod_stat_proc%TYPE;
    TYPE t_cod_moti_rech IS TABLE OF int_ocr_dupla_cyzon.cod_moti_rech%TYPE;
  
    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_comp      t_cod_comp := t_cod_comp();
    v_cod_clie      t_cod_clie := t_cod_clie();
    v_num_docu      t_num_docu := t_num_docu();
    v_val_ape1      t_val_ape1 := t_val_ape1();
    v_val_ape2      t_val_ape2 := t_val_ape2();
    v_val_nom1      t_val_nom1 := t_val_nom1();
    v_val_nom2      t_val_nom2 := t_val_nom2();
    v_fec_naci      t_fec_naci := t_fec_naci();
    v_val_mail_dupl t_val_mail_dupl := t_val_mail_dupl();
    v_val_tele_casa t_val_tele_casa := t_val_tele_casa();
    v_val_tele_celu t_val_tele_celu := t_val_tele_celu();
    v_val_dupl_nuev t_val_dupl_nuev := t_val_dupl_nuev();
    v_val_actu_dato t_val_actu_dato := t_val_actu_dato();
    v_ind_envi      t_ind_envi := t_ind_envi();
    v_cod_regi_arri t_cod_regi_arri := t_cod_regi_arri();
    v_cod_zona_arri t_cod_zona_arri := t_cod_zona_arri();
    v_fec_proc      t_fec_proc := t_fec_proc();
    v_cod_stat_proc t_cod_stat_proc := t_cod_stat_proc();
    v_cod_moti_rech t_cod_moti_rech := t_cod_moti_rech();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  
  BEGIN
  
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
        
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                  v_cod_pais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_cod_pais(i),
                                                                               v_cod_comp(i));
                ELSIF (posicion = 3) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 4) THEN
                  v_num_docu.extend;
                  v_num_docu(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 5) THEN
                  v_val_ape1.extend;
                  v_val_ape1(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 6) THEN
                  v_val_ape2.extend;
                  v_val_ape2(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 7) THEN
                  v_val_nom1.extend;
                  v_val_nom1(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 8) THEN
                  v_val_nom2.extend;
                  v_val_nom2(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 9) THEN
                  v_fec_naci.extend;
                  v_fec_naci(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 10) THEN
                  v_val_mail_dupl.extend;
                  v_val_mail_dupl(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 11) THEN
                  v_val_tele_casa.extend;
                  v_val_tele_casa(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 12) THEN
                  v_val_tele_celu.extend;
                  v_val_tele_celu(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 13) THEN
                  v_val_dupl_nuev.extend;
                  v_val_dupl_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 14) THEN
                  v_val_actu_dato.extend;
                  v_val_actu_dato(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 15) THEN
                  v_ind_envi.extend;
                  v_ind_envi(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 16) THEN
                  v_cod_regi_arri.extend;
                  v_cod_regi_arri(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 17) THEN
                  v_cod_zona_arri.extend;
                  v_cod_zona_arri(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 18) THEN
                  v_fec_proc.extend;
                  v_fec_proc(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 19) THEN
                  v_cod_stat_proc.extend;
                  v_cod_stat_proc(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 20) THEN
                  v_cod_moti_rech.extend;
                  v_cod_moti_rech(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_cod_pais.count
      INSERT INTO int_ocr_dupla_cyzon
        (cod_pais,
         cod_comp,
         cod_clie,
         num_docu,
         val_ape1,
         val_ape2,
         val_nom1,
         val_nom2,
         fec_naci,
         val_mail_dupl,
         val_tele_casa,
         val_tele_celu,
         val_dupl_nuev,
         val_actu_dato,
         ind_envi,
         cod_regi_arri,
         cod_zona_arri,
         fec_proc,
         cod_stat_proc,
         cod_moti_rech,
         ind_orig)
      VALUES
        (v_cod_pais(i),
         v_cod_comp(i),
         v_cod_clie(i),
         v_num_docu(i),
         v_val_ape1(i),
         v_val_ape2(i),
         v_val_nom1(i),
         v_val_nom2(i),
         v_fec_naci(i),
         v_val_mail_dupl(i),
         v_val_tele_casa(i),
         v_val_tele_celu(i),
         v_val_dupl_nuev(i),
         v_val_actu_dato(i),
         v_ind_envi(i),
         v_cod_regi_arri(i),
         v_cod_zona_arri(i),
         v_fec_proc(i),
         v_cod_stat_proc(i),
         v_cod_moti_rech(i),
         psindicadororigen);
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_DUPLA_CYZON: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_recep_dupla_cyzon;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo Control envio OCR
                      a SICC
  Fecha Creacion    : 01/04/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_multi_ctrl
  (
    pscodigopais              VARCHAR2,
    pscodigosistema           VARCHAR2,
    pscodigointerfaz          VARCHAR2,
    psnombrearchivo           VARCHAR2,
    psnumlotearchivo          VARCHAR2,
    psnumloteinterfaz         VARCHAR2,
    pscodigointerfazcompuesta VARCHAR2,
    psoidproceso              VARCHAR2,
    pscodigousuario           VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_multi_ctrl.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_multi_ctrl.cod_comp%TYPE;
    TYPE t_cod_tipo_docu IS TABLE OF int_ocr_multi_ctrl.cod_tipo_docu%TYPE;
    TYPE t_num_regi IS TABLE OF int_ocr_multi_ctrl.num_regi%TYPE;
    TYPE t_fec_proc IS TABLE OF int_ocr_multi_ctrl.fec_proc%TYPE;
    TYPE t_flg_cntr IS TABLE OF VARCHAR2(1);
  
    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_comp      t_cod_comp := t_cod_comp();
    v_cod_tipo_docu t_cod_tipo_docu := t_cod_tipo_docu();
    v_num_regi      t_num_regi := t_num_regi();
    v_fec_proc      t_fec_proc := t_fec_proc();
    v_flg_cntr      t_flg_cntr := t_flg_cntr();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio          NUMBER := 0;
    lsobservaciones VARCHAR2(100);
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
        
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := substr(lslinea, inicio, longitud);
                  v_cod_pais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_cod_pais(i),
                                                                               v_cod_comp(i));
                ELSIF (posicion = 3) THEN
                  v_cod_tipo_docu.extend;
                  v_cod_tipo_docu(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 4) THEN
                  v_num_regi.extend;
                  v_num_regi(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 5) THEN
                  v_fec_proc.extend;
                  v_fec_proc(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 6) THEN
                  v_flg_cntr.extend;
                  v_flg_cntr(i) := substr(lslinea, inicio, longitud);
                
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    --obtenemos num lote del archiv
  
    -- Bulk bind of data in memory table...
    FOR i IN 1 .. v_cod_pais.count
    LOOP
      IF (v_flg_cntr(i) = 'L' AND
         to_number(v_num_regi(i), '999999999999999') <> 0) THEN
        INSERT INTO int_archi_cntrl --int_ocr_multi_ctrl
          (oid_arcn,
           cod_pais,
           cod_comp,
           cod_tipo_docu,
           num_regi,
           fec_proc,
           num_lote_arch,
           num_lote_inte,
           cod_inpa,
           ind_carg,
           usu_digi,
           fec_digi,
           id_proc_batc)
        VALUES
          (int_seq_arcn.nextval,
           v_cod_pais(i),
           v_cod_comp(i),
           TRIM(v_cod_tipo_docu(i)),
           to_number(v_num_regi(i)),
           v_fec_proc(i),
           psnumlotearchivo,
           psnumloteinterfaz,
           pscodigointerfazcompuesta,
           '0',
           pscodigousuario,
           SYSDATE,
           psoidproceso);
      
        lsobservaciones := 'tipoLote=L,fechaProceso=' || v_fec_proc(i);
      END IF;
    
      IF (v_flg_cntr(i) = 'C') THEN
        lsobservaciones := 'tipoLote=C,fechaProceso=' || v_fec_proc(i);
      END IF;
    
    END LOOP;
  
    --Actualizamos con el tipo de Lote y Fecha de Proceso en BAS_HISTO_LOTES
    UPDATE bas_histo_lotes
       SET des_info_ctrl = lsobservaciones
     WHERE id_proc_batc = psoidproceso
       AND sist_cod_sist = pscodigosistema
       AND inte_cod_inte = pscodigointerfaz
       AND num_lote = psnumloteinterfaz;
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_MULTI_CTRL: ' || ls_sqlerrm);
    
  END int_pr_ocr_multi_ctrl;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Solicitud Premios Privilege Cabecera
  Fecha Creacion    : 01/04/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE int_pr_ocr_solpp_cabec
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_solpp_cabec.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_solpp_cabec.cod_comp%TYPE;
    TYPE t_num_docu IS TABLE OF int_ocr_solpp_cabec.num_docu%TYPE;
    TYPE t_cod_clie_priv IS TABLE OF int_ocr_solpp_cabec.cod_clie_priv%TYPE;
    TYPE t_cod_clie IS TABLE OF int_ocr_solpp_cabec.cod_clie%TYPE;
    TYPE t_fec_soli_prem IS TABLE OF int_ocr_solpp_cabec.fec_soli_prem%TYPE;
    TYPE t_cod_peri IS TABLE OF int_ocr_solpp_cabec.cod_peri%TYPE;
    TYPE t_cod_bar1 IS TABLE OF int_ocr_solpp_cabec.cod_bar1%TYPE;
    TYPE t_cod_bar2 IS TABLE OF int_ocr_solpp_cabec.cod_bar2%TYPE;
    TYPE t_cod_bar3 IS TABLE OF int_ocr_solpp_cabec.cod_bar3%TYPE;
    TYPE t_cod_bar4 IS TABLE OF int_ocr_solpp_cabec.cod_bar4%TYPE;
    TYPE t_cod_bar5 IS TABLE OF int_ocr_solpp_cabec.cod_bar5%TYPE;
    TYPE t_cod_bar6 IS TABLE OF int_ocr_solpp_cabec.cod_bar6%TYPE;
    TYPE t_cod_bar7 IS TABLE OF int_ocr_solpp_cabec.cod_bar7%TYPE;
    TYPE t_cod_bar8 IS TABLE OF int_ocr_solpp_cabec.cod_bar8%TYPE;
    TYPE t_cod_bar9 IS TABLE OF int_ocr_solpp_cabec.cod_bar9%TYPE;
    TYPE t_cod_bar10 IS TABLE OF int_ocr_solpp_cabec.cod_bar10%TYPE;
    TYPE t_cod_bar11 IS TABLE OF int_ocr_solpp_cabec.cod_bar11%TYPE;
    TYPE t_cod_regi_arri IS TABLE OF int_ocr_solpp_cabec.cod_regi_arri%TYPE;
    TYPE t_cod_zona_arri IS TABLE OF int_ocr_solpp_cabec.cod_zona_arri%TYPE;
    TYPE t_fec_proc IS TABLE OF int_ocr_solpp_cabec.fec_proc%TYPE;
    TYPE t_cod_stat_proc IS TABLE OF int_ocr_solpp_cabec.cod_stat_proc%TYPE;
    TYPE t_cod_moti_rech IS TABLE OF int_ocr_solpp_cabec.cod_moti_rech%TYPE;
  
    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_comp      t_cod_comp := t_cod_comp();
    v_num_docu      t_num_docu := t_num_docu();
    v_cod_clie_priv t_cod_clie_priv := t_cod_clie_priv();
    v_cod_clie      t_cod_clie := t_cod_clie();
    v_fec_soli_prem t_fec_soli_prem := t_fec_soli_prem();
    v_cod_peri      t_cod_peri := t_cod_peri();
    v_cod_bar1      t_cod_bar1 := t_cod_bar1();
    v_cod_bar2      t_cod_bar2 := t_cod_bar2();
    v_cod_bar3      t_cod_bar3 := t_cod_bar3();
    v_cod_bar4      t_cod_bar4 := t_cod_bar4();
    v_cod_bar5      t_cod_bar5 := t_cod_bar5();
    v_cod_bar6      t_cod_bar6 := t_cod_bar6();
    v_cod_bar7      t_cod_bar7 := t_cod_bar7();
    v_cod_bar8      t_cod_bar8 := t_cod_bar8();
    v_cod_bar9      t_cod_bar9 := t_cod_bar9();
    v_cod_bar10     t_cod_bar10 := t_cod_bar10();
    v_cod_bar11     t_cod_bar11 := t_cod_bar11();
    v_cod_regi_arri t_cod_regi_arri := t_cod_regi_arri();
    v_cod_zona_arri t_cod_zona_arri := t_cod_zona_arri();
    v_fec_proc      t_fec_proc := t_fec_proc();
    v_cod_stat_proc t_cod_stat_proc := t_cod_stat_proc();
    v_cod_moti_rech t_cod_moti_rech := t_cod_moti_rech();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
  BEGIN
  
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
        
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := substr(lslinea, inicio, longitud);
                  v_cod_pais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_cod_pais(i),
                                                                               v_cod_comp(i));
                ELSIF (posicion = 3) THEN
                  v_num_docu.extend;
                  v_num_docu(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 4) THEN
                  v_cod_clie_priv.extend;
                  v_cod_clie_priv(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 5) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 6) THEN
                  v_fec_soli_prem.extend;
                  v_fec_soli_prem(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 7) THEN
                  v_cod_peri.extend;
                  v_cod_peri(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 8) THEN
                  v_cod_bar1.extend;
                  v_cod_bar1(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 9) THEN
                  v_cod_bar2.extend;
                  v_cod_bar2(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 10) THEN
                  v_cod_bar3.extend;
                  v_cod_bar3(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 11) THEN
                  v_cod_bar4.extend;
                  v_cod_bar4(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 12) THEN
                  v_cod_bar5.extend;
                  v_cod_bar5(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 13) THEN
                  v_cod_bar6.extend;
                  v_cod_bar6(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 14) THEN
                  v_cod_bar7.extend;
                  v_cod_bar7(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 15) THEN
                  v_cod_bar8.extend;
                  v_cod_bar8(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 16) THEN
                  v_cod_bar9.extend;
                  v_cod_bar9(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 17) THEN
                  v_cod_bar10.extend;
                  v_cod_bar10(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 18) THEN
                  v_cod_bar11.extend;
                  v_cod_bar11(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 19) THEN
                  v_cod_regi_arri.extend;
                  v_cod_regi_arri(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 20) THEN
                  v_cod_zona_arri.extend;
                  v_cod_zona_arri(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 21) THEN
                  v_fec_proc.extend;
                  v_fec_proc(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 22) THEN
                  v_cod_stat_proc.extend;
                  v_cod_stat_proc(i) := substr(lslinea, inicio, longitud);
                ELSIF (posicion = 23) THEN
                  v_cod_moti_rech.extend;
                  v_cod_moti_rech(i) := substr(lslinea, inicio, longitud);
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_cod_pais.count
      INSERT INTO int_ocr_solpp_cabec
        (cod_pais,
         cod_comp,
         num_docu,
         cod_clie_priv,
         cod_clie,
         fec_soli_prem,
         cod_peri,
         cod_bar1,
         cod_bar2,
         cod_bar3,
         cod_bar4,
         cod_bar5,
         cod_bar6,
         cod_bar7,
         cod_bar8,
         cod_bar9,
         cod_bar10,
         cod_bar11,
         cod_regi_arri,
         cod_zona_arri,
         fec_proc,
         cod_stat_proc,
         cod_moti_rech)
      VALUES
        (v_cod_pais(i),
         v_cod_comp(i),
         v_num_docu(i),
         v_cod_clie_priv(i),
         v_cod_clie(i),
         v_fec_soli_prem(i),
         v_cod_peri(i),
         v_cod_bar1(i),
         v_cod_bar2(i),
         v_cod_bar3(i),
         v_cod_bar4(i),
         v_cod_bar5(i),
         v_cod_bar6(i),
         v_cod_bar7(i),
         v_cod_bar8(i),
         v_cod_bar9(i),
         v_cod_bar10(i),
         v_cod_bar11(i),
         v_cod_regi_arri(i),
         v_cod_zona_arri(i),
         v_fec_proc(i),
         v_cod_stat_proc(i),
         v_cod_moti_rech(i));
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_SOLPP_CABEC: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_solpp_cabec;
  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Solicitudes de Credito
  Fecha Creacion    : 28/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_solic_credi
  (
    pscodigopais      VARCHAR2,
    pscodigousuario   VARCHAR2,
    psnumlote         VARCHAR2,
    pscodigodocumento VARCHAR2
  ) IS
  
    ---------------------------------------
    -- Patron caractrese extra?os 1
    CURSOR cur_cad IS
      SELECT crt_vali
        FROM mae_contr_carac
       WHERE cod_modu_vali = 'VAL_CRT_NV1'
         AND crt_indi = 'N';
    -- Patron caractrese extra?os 3
    CURSOR cur_cad2 IS
      SELECT crt_vali
        FROM mae_contr_carac
       WHERE cod_modu_vali = 'VAL_CRT_NV3'
         AND crt_indi = 'N';
  
    v_cur_cad  cur_cad%ROWTYPE;
    v_cur_cad2 cur_cad2%ROWTYPE;
  
    patron1 VARCHAR2(1000);
    patron2 VARCHAR2(1000);
  
    patron3 VARCHAR2(100) := '0123456789ABCDEFGHIJKLMNÑOPQRSTUVWXYabcdefghijklmnñopqrstuvwxy@\/*-;!"·$%&/()=?¿^`+´Ç_:,.'' ';
    ---------------------------------------
  
    CURSOR curinsconsolcredi
    (
      vsformatofecha     VARCHAR2,
      vsindactpreimpreso VARCHAR2,
      vpatron1           VARCHAR2,
      vpatron2           VARCHAR2,
      vpatron3           VARCHAR2
    ) IS
      SELECT cred.cod_pais cod_pais,
             TRIM(translate(cred.cod_comp,
                            vpatron1,
                            lpad(' ', length(cred.cod_comp)))) cod_comp,
             TRIM(translate(cred.cod_clie,
                            vpatron1,
                            lpad(' ', length(cred.cod_clie)))) cod_clie,
             TRIM(translate((decode(vsindactpreimpreso,
                                    'S',
                                    decode(ltrim(cred.num_docu, '0'),
                                           NULL,
                                           '0',
                                           ltrim(cred.num_docu, '0')),
                                    cred.num_docu)),
                            vpatron1,
                            lpad(' ',
                                 length((decode(vsindactpreimpreso,
                                                'S',
                                                decode(ltrim(cred.num_docu,
                                                             '0'),
                                                       NULL,
                                                       '0',
                                                       ltrim(cred.num_docu, '0')),
                                                cred.num_docu)))))) num_docu,
             int_pkg_occrr.int_fn_devue_fecha(cred.fec_proc, 'YYYYMMDD') fec_proc,
             TRIM(translate(cred.uni_admi,
                            vpatron1,
                            lpad(' ', length(cred.uni_admi)))) uni_admi,
             TRIM(translate(cred.tip_ingr,
                            vpatron1,
                            lpad(' ', length(cred.tip_ingr)))) tip_ingr,
             TRIM(translate(cred.cod_peri,
                            vpatron1,
                            lpad(' ', length(cred.cod_peri)))) cod_peri,
             TRIM(translate(cred.cod_clie_rete,
                            vpatron1,
                            lpad(' ', length(cred.cod_clie_rete)))) cod_clie_rete,
             TRIM(translate(cred.cod_prem,
                            vpatron1,
                            lpad(' ', length(cred.cod_prem)))) cod_prem,
             TRIM(translate(cred.val_ape1,
                            vpatron1,
                            lpad(' ', length(cred.val_ape1)))) val_ape1,
             TRIM(translate(cred.val_ape2,
                            vpatron1,
                            lpad(' ', length(cred.val_ape2)))) val_ape2,
             TRIM(translate(cred.val_nom1,
                            vpatron1,
                            lpad(' ', length(cred.val_nom1)))) val_nom1,
             TRIM(translate(cred.val_nom2,
                            vpatron1,
                            lpad(' ', length(cred.val_nom2)))) val_nom2,
             int_pkg_occrr.int_fn_devue_fecha(cred.fec_naci, vsformatofecha) fec_naci,
             cred.tip_docu tip_docu,
             cred.num_docu_iden num_docu_iden,
             TRIM(translate(TRIM(translate(cred.num_ruc,
                                           vpatron1,
                                           lpad(' ', length(cred.num_ruc)))),
                            patron3,
                            '0123456789')) num_ruc,
             TRIM(translate(cred.ind_esta_civi,
                            vpatron1,
                            lpad(' ', length(cred.ind_esta_civi)))) ind_esta_civi,
             TRIM(translate(cred.ind_nive_educ,
                            vpatron1,
                            lpad(' ', length(cred.ind_nive_educ)))) ind_nive_educ,
             TRIM(translate(cred.val_dire_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_dire_clie)))) val_dire_clie,
             TRIM(translate(cred.val_barr_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_barr_clie)))) val_barr_clie,
             TRIM(translate(cred.val_ciud_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_ciud_clie)))) val_ciud_clie,
             TRIM(translate(cred.val_depa_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_depa_clie)))) val_depa_clie,
             cred.val_telf_clie val_telf_clie,
             cred.val_celu_clie val_celu_clie,
             cred.val_telf_trab val_telf_trab,
             TRIM(translate(cred.ind_vent_dire,
                            vpatron1,
                            lpad(' ', length(cred.ind_vent_dire)))) ind_vent_dire,
             TRIM(translate(cred.val_mail_clie,
                            vpatron2,
                            lpad(' ', length(cred.val_mail_clie)))) val_mail_clie,
             cred.tip_docu_fiad tip_docu_fiad,
             -- Elimina los caracteres no numericos
             TRIM(translate(TRIM(translate(cred.cod_docu_idfi,
                                           vpatron1,
                                           lpad(' ',
                                                length(cred.cod_docu_idfi)))),
                            patron3,
                            '0123456789')) cod_docu_idfi,
             TRIM(translate(cred.val_ape1_fiad,
                            vpatron1,
                            lpad(' ', length(cred.val_ape1_fiad)))) val_ape1_fiad,
             TRIM(translate(cred.val_ape2_fiad,
                            vpatron1,
                            lpad(' ', length(cred.val_ape2_fiad)))) val_ape2_fiad,
             TRIM(translate(cred.val_nom1_fiad,
                            vpatron1,
                            lpad(' ', length(cred.val_nom1_fiad)))) val_nom1_fiad,
             TRIM(translate(cred.val_nom2_fiad,
                            vpatron1,
                            lpad(' ', length(cred.val_nom2_fiad)))) val_nom2_fiad,
             TRIM(translate(cred.val_dire_fiad,
                            vpatron1,
                            lpad(' ', length(cred.val_dire_fiad)))) val_dire_fiad,
             TRIM(translate(cred.val_barr_fiad,
                            vpatron1,
                            lpad(' ', length(cred.val_barr_fiad)))) val_barr_fiad,
             TRIM(translate(cred.val_ciud_fiad,
                            vpatron1,
                            lpad(' ', length(cred.val_ciud_fiad)))) val_ciud_fiad,
             TRIM(translate(cred.val_depa_fiad,
                            vpatron1,
                            lpad(' ', length(cred.val_depa_fiad)))) val_depa_fiad,
             cred.val_tefl_fiad val_tefl_fiad,
             cred.val_celu_fiad val_celu_fiad,
             cred.val_telf_trfi val_telf_trfi,
             TRIM(translate(cred.val_regi_arri,
                            vpatron1,
                            lpad(' ', length(cred.val_regi_arri)))) val_regi_arri,
             TRIM(translate(cred.val_zona_arri,
                            vpatron1,
                            lpad(' ', length(cred.val_zona_arri)))) val_zona_arri,
             cred.ind_stat_proc ind_stat_proc,
             cred.ind_moti_rech ind_moti_rech,
             cred.tip_via_clie tip_via_clie,
             TRIM(translate(cred.val_nomb_vicl,
                            vpatron1,
                            lpad(' ', length(cred.val_nomb_vicl)))) val_nomb_vicl,
             TRIM(translate(cred.num_dire_clie,
                            vpatron1,
                            lpad(' ', length(cred.num_dire_clie)))) num_dire_clie,
             TRIM(translate(cred.cod_depa_clie,
                            vpatron1,
                            lpad(' ', length(cred.cod_depa_clie)))) cod_depa_clie,
             cred.cod_prov_clie cod_prov_clie,
             cred.cod_dist_clie cod_dist_clie,
             cred.cod_sect_clie cod_sect_clie,
             cred.tip_via_fiad tip_via_fiad,
             TRIM(translate(cred.val_nomb_vifi,
                            vpatron1,
                            lpad(' ', length(cred.val_nomb_vifi)))) val_nomb_vifi,
             TRIM(translate(cred.num_dire_fiad,
                            vpatron1,
                            lpad(' ', length(cred.num_dire_fiad)))) num_dire_fiad,
             cred.cod_depa_fiad cod_depa_fiad,
             cred.cod_prov_fiad cod_prov_fiad,
             cred.cod_dist_fiad cod_dist_fiad,
             cred.cod_sect_fiad cod_sect_fiad,
             NULL oid_pais,
             NULL oid_terr,
             NULL oid_terr_admi,
             NULL oid_peri,
             NULL oid_para_nive_prem,
             NULL num_premi,
             psnumlote num_lote,
             seq_docu_sto.nextval sec_nume_docu,
             NULL oid_para_gral,
             NULL cod_fiad,
             TRIM(translate(cred.val_dele_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_dele_clie)))) val_dele_clie,
             TRIM(translate(cred.val_codi_post_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_codi_post_clie)))) val_codi_post_clie,
             TRIM(translate(cred.val_dire_entre_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_codi_post_clie)))) val_codi_post_clie,
             TRIM(translate(cred.val_barr_entre_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_barr_entre_clie)))) val_barr_entre_clie,
             TRIM(translate(cred.val_dele_entre_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_dele_entre_clie)))) val_dele_entre_clie,
             TRIM(translate(cred.val_depa_entre_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_depa_entre_clie)))) val_depa_entre_clie,
             cred.val_tele_entre_clie,
             cred.val_celu_entre_clie,
             cred.val_codi_post_entre_clie,
             TRIM(translate(cred.val_ape1_refe_fami_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_ape1_refe_fami_clie)))) val_ape1_refe_fami_clie,
             TRIM(translate(cred.val_nom1_refe_fami_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_nom1_refe_fami_clie)))) val_nom1_refe_fami_clie,
             TRIM(translate(cred.val_dire_refe_fami_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_nom1_refe_fami_clie)))) val_nom1_refe_fami_clie,
             TRIM(translate(cred.val_barr_refe_fami_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_barr_refe_fami_clie)))) val_barr_refe_fami_clie,
             TRIM(translate(cred.val_dele_refe_fami_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_dele_refe_fami_clie)))) val_dele_refe_fami_clie,
             TRIM(translate(cred.val_ciud_refe_fami_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_ciud_refe_fami_clie)))) val_ciud_refe_fami_clie,
             TRIM(translate(cred.val_depa_refe_fami_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_depa_refe_fami_clie)))) val_depa_refe_fami_clie,
             cred.val_tele_refe_fami_clie,
             cred.val_celu_refe_fami_clie,
             TRIM(translate(cred.val_tipo_vinc_refe_fami_clie,
                            vpatron1,
                            lpad(' ',
                                 length(cred.val_tipo_vinc_refe_fami_clie)))) val_tipo_vinc_refe_fami_clie,
             TRIM(translate(cred.val_ape1_refe_nofa_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_ape1_refe_nofa_clie)))) val_ape1_refe_nofa_clie,
             TRIM(translate(cred.val_nom1_refe_nofa_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_nom1_refe_nofa_clie)))) val_nom1_refe_nofa_clie,
             cred.val_tele_refe_nofa_clie,
             cred.val_celu_refe_nofa_clie,
             TRIM(translate(cred.val_tipo_vinc_refe_nofa_clie,
                            vpatron1,
                            lpad(' ',
                                 length(cred.val_tipo_vinc_refe_nofa_clie)))) val_tipo_vinc_refe_nofa_clie,
             TRIM(translate(cred.val_nomb_empr_fiad,
                            vpatron1,
                            lpad(' ', length(cred.val_nomb_empr_fiad)))) val_nomb_empr_fiad,
             TRIM(translate(cred.val_dire_empr_fiad,
                            vpatron1,
                            lpad(' ', length(cred.val_dire_empr_fiad)))) val_dire_empr_fiad,
             TRIM(translate(cred.val_carg_fiad,
                            vpatron1,
                            lpad(' ', length(cred.val_carg_fiad)))) val_carg_fiad,
             TRIM(translate(cred.val_tipo_vinc_fiad,
                            vpatron1,
                            lpad(' ', length(cred.val_tipo_vinc_fiad)))) val_tipo_vinc_fiad,
             TRIM(translate(cred.ind_requ_fact,
                            vpatron1,
                            lpad(' ', length(cred.ind_requ_fact)))) ind_requ_fact,
             TRIM(translate(cred.val_dire_cont,
                            vpatron1,
                            lpad(' ', length(cred.val_dire_cont)))) val_dire_cont,
             TRIM(translate(cred.val_barr_cont,
                            vpatron1,
                            lpad(' ', length(cred.val_barr_cont)))) val_barr_cont,
             TRIM(translate(cred.val_dele_cont,
                            vpatron1,
                            lpad(' ', length(cred.val_dele_cont)))) val_dele_cont,
             TRIM(translate(cred.val_ciud_cont,
                            vpatron1,
                            lpad(' ', length(cred.val_ciud_cont)))) val_ciud_cont,
             TRIM(translate(cred.val_depa_cont,
                            vpatron1,
                            lpad(' ', length(cred.val_depa_cont)))) val_depa_cont,
             TRIM(translate(cred.val_codi_post_cont,
                            vpatron1,
                            lpad(' ', length(cred.val_codi_post_cont)))) val_codi_post_cont,
             TRIM(translate(cred.val_nume_cont,
                            vpatron1,
                            lpad(' ', length(cred.val_nume_cont)))) val_nume_cont,
             TRIM(translate(cred.val_dire_refe_nofa_clie,
                            vpatron1,
                            lpad(' ', length(cred.val_dire_refe_nofa_clie)))) val_dire_refe_nofa_clie,
             cred.val_codi_vent_dire,
             cred.val_codi_segm,
             '0', --cred.IND_TELE_OK                ,
             '0', --cred.IND_SITU_CRED              ,
             '0', --cred.IND_SIN_SALD_AMBA_MARC     ,
             NULL, --cred.VAL_OBSE_RECH_DEFI,
             '1' ind_envi_sto,
             NULL ind_envi_ocr,
             cred.fec_hora_crea_soli,
             cod_sexo,
             ciud_cod_ciud_domi,
             ciud_cod_ugeo_regi_domi,
             des_villa_pobl_domi,
             NULL ciud_cod_ciud_entr,
             NULL ciud_cod_ugeo_regi_entr,
             NULL des_villa_pobl_entr,
             NULL val_mont_info_come,
             NULL val_esta_info_come,
             NULL ind_erro_info_come,
             NULL val_nomb_info_come,
             NULL val_expl_info_come,
             ind_orig,
             tip_meta,
             val_mnto_meta,
             cod_camp_inic,
             des_meta,
             ind_vend_mar1,
             ind_vend_mar2,
             ind_vend_mar3,
             ind_vend_mar4,
             ind_vend_mar5,
             ind_vend_mar6,
             ind_vend_mar7,
             cod_marc_vema,
             NULL,
             ind_requ_impr_bole,
             cod_lide_reco,
             val_obse,
             oid_naci,
             TRIM(translate(cred.cod_naci,
                            vpatron1,
                            lpad(' ', length(cred.cod_naci)))) cod_naci,                             
             cod_tipo_pers,
             null,--oid_tipo_pers,
             cod_orig_ingr,
             null,--oid_orig_ingr, 
             cod_terr_corr, 
             ind_dird_dire,       
             TRIM(translate(cred.dom_manz,
              vpatron1,
              lpad(' ', length(cred.dom_manz)))) dom_manz,    
             TRIM(translate(cred.dom_etap,
              vpatron1,
              lpad(' ', length(cred.dom_etap)))) dom_etap, 
             TRIM(translate(cred.dom_call_prin,
              vpatron1,
              lpad(' ', length(cred.dom_call_prin)))) dom_call_prin,       
             TRIM(translate(cred.dom_call_secu,
              vpatron1,
              lpad(' ', length(cred.dom_call_secu)))) dom_call_secu,
             TRIM(translate(cred.dom_num,
              vpatron1,
              lpad(' ', length(cred.dom_num)))) dom_num, 
             TRIM(translate(cred.dom_refe,
              vpatron1,
              lpad(' ', length(cred.dom_refe)))) dom_refe,            
             TRIM(translate(cred.ent_manz,
              vpatron1,
              lpad(' ', length(cred.ent_manz)))) ent_manz,                    
             TRIM(translate(cred.ent_etap,
              vpatron1,
              lpad(' ', length(cred.ent_etap)))) ent_etap,  
             TRIM(translate(cred.ent_call_prin,
              vpatron1,
              lpad(' ', length(cred.ent_call_prin)))) ent_call_prin,                   
             TRIM(translate(cred.ent_call_secu,
              vpatron1,
              lpad(' ', length(cred.ent_call_secu)))) ent_call_secu, 
             TRIM(translate(cred.ent_num,
              vpatron1,
              lpad(' ', length(cred.ent_num)))) ent_num, 
             TRIM(translate(cred.ent_refe,
              vpatron1,
              lpad(' ', length(cred.ent_refe)))) ent_refe   
        FROM int_ocr_solic_credi cred
       WHERE cred.cod_pais = pscodigopais
         AND ((vsindactpreimpreso = 'S' AND
             ((ltrim(cred.num_docu, '0') IS NULL AND
             cred.num_docu_iden IS NOT NULL) OR
             (ltrim(cred.num_docu, '0') IS NOT NULL))) OR
             (vsindactpreimpreso = 'N' AND TRIM(cred.num_docu) IS NOT NULL));
  
    TYPE solic_credi_tab_t IS TABLE OF int_solic_conso_credi%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_credi_tab solic_credi_tab_t; -- In-memory table
  
    i BINARY_INTEGER := 0;
  
    lsformatofecha sto_param_gener_occrr.val_param%TYPE;
  
    lsindactpreimpreso sto_param_gener_occrr.val_param%TYPE;
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t; -- In-memory table
  
  BEGIN
  
    lsformatofecha := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_FORMA_FEC_NACI');
  
    lsindactpreimpreso := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_SCC_ACTU_PREI');
    ---------------------------------------------
    OPEN cur_cad;
    LOOP
      FETCH cur_cad
        INTO v_cur_cad;
      EXIT WHEN cur_cad%NOTFOUND;
      patron1 := patron1 || v_cur_cad.crt_vali;
    END LOOP;
    CLOSE cur_cad;
  
    OPEN cur_cad2;
    LOOP
      FETCH cur_cad2
        INTO v_cur_cad2;
      EXIT WHEN cur_cad2%NOTFOUND;
      patron2 := patron2 || v_cur_cad2.crt_vali;
    END LOOP;
    CLOSE cur_cad2;
    ---------------------------------------------
  
    OPEN curinsconsolcredi(lsformatofecha,
                           lsindactpreimpreso,
                           patron1,
                           patron2,
                           patron3);
    LOOP
    
      FETCH curinsconsolcredi BULK COLLECT
        INTO sol_credi_tab LIMIT w_filas;
      EXIT WHEN sol_credi_tab.count = 0;
    
      FOR j IN sol_credi_tab.first .. sol_credi_tab.last
      LOOP
      
        sto_tab(j).cod_pais := sol_credi_tab(j).cod_pais;
        sto_tab(j).cod_tipo_docu := pscodigodocumento;
        sto_tab(j).num_lote := sol_credi_tab(j).num_lote;
        sto_tab(j).sec_nume_docu := sol_credi_tab(j).sec_nume_docu;
        sto_tab(j).num_docu := sol_credi_tab(j).num_docu;
        sto_tab(j).ind_envi := '0';
        sto_tab(j).ind_rech := '0';
        sto_tab(j).fec_digi := SYSDATE;
        sto_tab(j).usu_digi := pscodigousuario;
        sto_tab(j).fec_modi := SYSDATE;
        sto_tab(j).usu_modi := pscodigousuario;
        sto_tab(j).cod_zona := sol_credi_tab(j).val_zona_arri;
        sto_tab(j).cod_clie := sol_credi_tab(j).cod_clie;
        -- sto_tab(j).cod_regi := sol_credi_tab(j).cod_regi;
        sto_tab(j).cod_peri := sol_credi_tab(j).cod_peri;
        --sto_tab(j).COD_MOTI_RECH      :=
        sto_tab(j).val_obse_rech_defi := sol_credi_tab(j).val_obse_rech_defi;
        sto_tab(j).ind_rece_ocr := '0';
        sto_tab(j).ind_rece_web := '0';
        sto_tab(j).ind_rece_dd := '0';
        sto_tab(j).ind_rece_digi := '0';
        sto_tab(j).ind_rece_cc := '0';
        sto_tab(j).ind_rece_mens := '0';
        sto_tab(j).ind_elim := '0';
        sto_tab(j).cod_zona_arri := sol_credi_tab(j).val_zona_arri;
        sto_tab(j).ind_rece_onli := '0';
        sto_tab(j).ind_rece_ivr := '0';
      
        IF sol_credi_tab(j).ind_orig = 'O' THEN
          sto_tab(j).ind_rece_ocr := '1';
        ELSIF sol_credi_tab(j).ind_orig = 'W' THEN
          sto_tab(j).ind_rece_web := '1';
        END IF;
      
      END LOOP;
    
      FORALL i IN sol_credi_tab.first .. sol_credi_tab.last
        INSERT INTO int_solic_conso_credi VALUES sol_credi_tab (i);
    
      FORALL j IN sol_credi_tab.first .. sol_credi_tab.last
        INSERT INTO sto_docum_digit VALUES sto_tab (j);
    END LOOP;
    CLOSE curinsconsolcredi;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_SOLIC_CREDI: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_solic_credi;
  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Servicio Postventas
  Fecha Creacion    : 29/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_post_venta_cabec
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2
    
  ) IS
    CURSOR curinsconsolpostventa IS
    
      SELECT pscodigopais,
             cod_peri,
             nvl(cod_clie, '0'),
             psnumlote,
             pscodigotipodocumento docu_cod_tipo_docu,
             nvl(num_docu, 0),
             NULL sec_nume_docu,
             MIN(cod_cia),
             MIN(num_docu_cruc),
             MIN(tip_soli),
             MIN(cod_sub_acces) cod_sub_acce,
             MIN(acc_fisi),
             MIN(to_date(fec_proc_doc, 'YYYYMMDD')) fec_proc_docu,
             MIN(cod_regi_arri),
             MIN(cod_zona_arri),
             MIN(p.sta_proc),
             MIN(p.cod_moti_rech),
             NULL oid_pais,
             NULL oid_peri,
             NULL oid_clie,
             NULL oid_cabe,
             NULL sbti_oid_subt_clie,
             NULL ticl_oid_tipo_clie,
             NULL oid_peri_refe,
             NULL fec_refe,
             NULL tido_oid_tipo_docu,
             NULL ztad_oid_terr_admi,
             NULL oid_peri_recl,
             NULL ind_bloq_solo_devu,
             '1' ind_envi_sto,
             NULL des_obse,
             pscodigousuario,
             SYSDATE fec_digi,
             pscodigousuario,
             SYSDATE fec_modi,
             MIN(ind_orig),
             MIN(ind_expr),
             NULL oid_cabe_recl_refe,
             -- INI JR PER-SiCC-2012-0304
             nvl(p.ind_cdr_rech, '0') ind_cdr_rech,
             p.val_obse_rech_defi,
             -- FIN JR PER-SiCC-2012-0304
             ----NULL COD_MOT_RECH_DEFI --PER-SiCC-2012-0642
             cod_mot_rech_defi cod_mot_rech_defi, --PER-SiCC-2012-0642
             NULL              oid_peri_regi
        FROM int_ocr_cabec_servi_postv p
       WHERE p.cod_moti_rech IS NULL
         AND p.sta_proc = '01' -- REGISTROS VALIDOS OCR
         AND p.num_lote = psnumlote
       GROUP BY cod_pais,
                cod_peri,
                nvl(cod_clie, '0'),
                nvl(num_docu, 0),
                p.ind_cdr_rech,
                p.val_obse_rech_defi,
                cod_mot_rech_defi;
  
    TYPE solic_post_venta_tab_t IS TABLE OF int_solic_conso_poven_cabec%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_post_venta_tab solic_post_venta_tab_t; -- In-memory table
  
    j BINARY_INTEGER := 0;
  
    lnidperiodoregi int_solic_conso_poven_cabec.oid_peri_regi%TYPE;
  
    lscodigoperiodoactivo seg_perio_corpo.cod_peri%TYPE;
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t; -- In-memory table
  
  BEGIN
  
    /*   SELECT a.cod_peri
     INTO lscodigoperiodoactivo
     FROM bas_ctrl_fact a
    WHERE a.ind_camp_act = '1'
      AND a.sta_camp = '0'; */
  
    SELECT c.oid_peri,
           a.cod_peri
      INTO lnidperiodoregi,
           lscodigoperiodoactivo
      FROM bas_ctrl_fact   a,
           seg_perio_corpo b,
           cra_perio       c
     WHERE a.cod_peri = b.cod_peri
       AND b.oid_peri = c.peri_oid_peri
       AND a.sta_camp = 0
       AND a.ind_camp_act = 1;
  
    --ACTUALIZA EL PERIODO ACTIVO A LOS CDR's QUE FUERON CARGADOS POR OCR
    UPDATE int_ocr_cabec_servi_postv
       SET cod_peri = lscodigoperiodoactivo
     WHERE ind_orig = '1'
       AND num_lote = psnumlote;
  
    OPEN curinsconsolpostventa;
    LOOP
      FETCH curinsconsolpostventa BULK COLLECT
        INTO sol_post_venta_tab LIMIT w_filas;
    
      EXIT WHEN sol_post_venta_tab.count = 0;
      FOR j IN sol_post_venta_tab.first .. sol_post_venta_tab.last
      LOOP
        sol_post_venta_tab(j).sec_nume_docu := seq_docu_sto.nextval;
      
        sol_post_venta_tab(j).oid_peri_regi := lnidperiodoregi;
      
        sto_tab(j).cod_pais := sol_post_venta_tab(j).cod_pais;
        sto_tab(j).cod_tipo_docu := pscodigotipodocumento;
        sto_tab(j).num_lote := sol_post_venta_tab(j).num_lote;
        sto_tab(j).sec_nume_docu := sol_post_venta_tab(j).sec_nume_docu;
        sto_tab(j).num_docu := sol_post_venta_tab(j).num_docu;
        sto_tab(j).ind_envi := '0';
        sto_tab(j).ind_rech := '0';
        sto_tab(j).fec_digi := sol_post_venta_tab(j).fec_digi;
        sto_tab(j).usu_digi := sol_post_venta_tab(j).usu_digi;
        sto_tab(j).fec_modi := sol_post_venta_tab(j).fec_modi;
        sto_tab(j).usu_modi := sol_post_venta_tab(j).usu_modi;
        sto_tab(j).cod_zona := sol_post_venta_tab(j).cod_zona_arri;
        sto_tab(j).cod_clie := sol_post_venta_tab(j).cod_clie;
        sto_tab(j).cod_regi := sol_post_venta_tab(j).cod_regi_arri;
        sto_tab(j).cod_peri := sol_post_venta_tab(j).cod_peri;
        --sto_tab(j).COD_MOTI_RECH      :=
        sto_tab(j).val_obse_rech_defi := sol_post_venta_tab(j)
                                         .val_obse_rech_defi;
        sto_tab(j).ind_rece_ocr := '0';
        sto_tab(j).ind_rece_web := '0';
        sto_tab(j).ind_rece_dd := '0';
        sto_tab(j).ind_rece_digi := '0';
        sto_tab(j).ind_rece_cc := '0';
        sto_tab(j).ind_rece_mens := '0';
        sto_tab(j).ind_elim := '0';
        sto_tab(j).cod_zona_arri := sol_post_venta_tab(j).cod_zona_arri;
        sto_tab(j).ind_rece_onli := '0';
        sto_tab(j).ind_rece_ivr := '0';
      
        IF sol_post_venta_tab(j).ind_orig = '1' THEN
          sto_tab(j).ind_rece_ocr := '1';
        ELSIF sol_post_venta_tab(j).ind_orig = '2' THEN
          sto_tab(j).ind_rece_digi := '1';
        ELSIF sol_post_venta_tab(j).ind_orig = 'C' THEN
          sto_tab(j).ind_rece_cc := '1';
        END IF;
      
      END LOOP;
    
      FORALL j IN sol_post_venta_tab.first .. sol_post_venta_tab.last
        INSERT INTO int_solic_conso_poven_cabec
        VALUES sol_post_venta_tab
          (j);
    
      FORALL j IN sol_post_venta_tab.first .. sol_post_venta_tab.last
        INSERT INTO sto_docum_digit VALUES sto_tab (j);
    
    END LOOP;
    CLOSE curinsconsolpostventa;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_POST_VENTA: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_post_venta_cabec;
  /***************************************************************************
  Descripcion       : Consolida el detalle de Servicio Postventas
  Fecha Creacion    : 30/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_post_venta_detal
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2
  ) IS
    CURSOR curinsconsolpostventa IS
      SELECT det.cod_pais cod_pais,
             cab.cod_peri cod_peri,
             nvl(det.cod_clie, '0') cod_clie,
             psnumlote num_lote,
             pscodigotipodocumento docu_cod_tipo_docu,
             nvl(det.num_docu, 0) num_docu,
             det.cod_cia cod_cia,
             det.tip_refe tip_refe,
             decode(det.tip_refe,
                    '84',
                    det.cod_vent_dese,
                    det.cod_vent_devu) cod_vent_devu,
             decode(det.tip_refe, '84', NULL, det.cod_vent_dese) cod_vent_dese,
             decode(det.tip_refe,
                    '84',
                    det.can_prod_dese,
                    det.can_prod_devu) can_vent_devu,
             decode(det.tip_refe, '84', 0, det.can_prod_dese) can_prod_dese,
             det.sta_proc sta_proc,
             det.cod_moti_rech cod_moti_rech,
             det.mot_spv mot_spv,
             det.cod_oper cod_oper,
             det.cod_tipo_oper cod_tipo_oper,
             NULL oid_pais,
             NULL oid_oper,
             NULL oid_tipo_oper,
             NULL ind_ingr_envi,
             NULL ind_ingr_devu,
             NULL ind_devu_fisi,
             NULL ind_envi_gener_devu,
             NULL ind_devu_gener_envi,
             NULL ind_envi_fact,
             NULL ind_devu_fact,
             NULL cod_prec_envi,
             NULL cod_prec,
             NULL val_prec_cata_envi,
             NULL val_prec_cont_envi,
             NULL val_prec_cata_devu,
             NULL val_prec_cont_devu,
             NULL panp_oid_para_nive_prem_envia,
             NULL lopa_oid_lote_prem_artic_envia,
             NULL copa_oid_para_gene_envia,
             NULL panp_oid_para_nive_prem_devu,
             NULL lopa_oid_lote_prem_artic_devu,
             NULL copa_oid_para_gene_devu,
             NULL tofe_oid_envi,
             NULL mafa_oid_envi,
             NULL prod_oid_prod_envia,
             NULL tofe_oid_devu,
             NULL mafa_oid_devu,
             NULL prod_oid_prod_devu,
             NULL tspa_oid_tipo_soli_pais_envu,
             NULL tspa_oid_tipo_soli_pais_devu,
             NULL oid_soli_posi_envi,
             NULL oid_soli_posi_devu,
             NULL ind_cent_gara,
             NULL cese_oid_cese_gara,
             NULL num_dias_atra,
             NULL esta_oid_esta_clien,
             NULL val_revi_cheq,
             NULL num_mes_gara,
             NULL ind_gara_prem,
             NULL ind_true_prem,
             NULL sec_nume_docu,
             det.cod_zona cod_zona,
             det.cod_regi cod_regi,
             '1' ind_envi_sto,
             NULL des_obse,
             pscodigousuario usu_digi,
             SYSDATE fec_digi,
             pscodigousuario usu_modi,
             SYSDATE fec_modi,
             NULL num_line,
             det.ind_acci ind_acci,
             1 val_fact_repe,
             NULL des_cent_serv,
             NULL ind_apro_fnne,
             NULL ind_nume_vece_pedi,
             cab.sec_nume_docu,
             NULL cod_moti_real,
             '0' ind_list_blan_prod
        FROM int_ocr_detal_servi_postv   det,
             int_solic_conso_poven_cabec cab
       WHERE cab.num_docu = nvl(det.num_docu, 0) ---- det.num_docu
         AND cab.cod_clie = nvl(det.cod_clie, '0') ---- det.cod_clie
         AND cab.cod_pais = det.cod_pais
         AND det.cod_pais = pscodigopais
         AND cab.sta_proc = '01' -- REGISTROS VALIDOS OCR
         AND det.sta_proc = '01' -- REGISTROS VALIDOS OCR
         AND cab.num_lote = psnumlote
         AND cab.cod_moti_rech IS NULL
         AND det.cod_moti_rech IS NULL
         AND det.num_lote = psnumlote
       ORDER BY cod_pais,
                cod_peri,
                cod_clie,
                num_docu,
                num_lote;
  
    TYPE solic_post_venta_tab_t IS TABLE OF int_solic_conso_poven_detal%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_post_venta_tab solic_post_venta_tab_t; -- In-memory table
  
    j BINARY_INTEGER := 0;
  
    lspkactual   VARCHAR2(200) := NULL;
    lspkanterior VARCHAR2(200) := NULL;
    lnnumline    int_solic_conso_poven_detal.num_line%TYPE := 0;
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t; -- In-memory table
  
  BEGIN
  
    OPEN curinsconsolpostventa;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsolpostventa BULK COLLECT
        INTO sol_post_venta_tab LIMIT w_filas;
      EXIT WHEN sol_post_venta_tab.count = 0;
    
      FOR j IN sol_post_venta_tab.first .. sol_post_venta_tab.last
      LOOP
      
        lspkactual := sol_post_venta_tab(j)
                      .cod_pais || sol_post_venta_tab(j).cod_peri || sol_post_venta_tab(j)
                      .cod_clie || sol_post_venta_tab(j).num_docu || sol_post_venta_tab(j)
                      .num_lote;
      
        IF lspkanterior IS NULL OR lspkactual <> lspkanterior THEN
          lnnumline := 0;
        END IF;
      
        lnnumline := lnnumline + 1;
        lspkanterior := lspkactual;
        sol_post_venta_tab(j).num_line := lnnumline;
        sol_post_venta_tab(j).sec_nume_docu := seq_docu_sto.nextval;
      
        sto_tab(j).cod_pais := sol_post_venta_tab(j).cod_pais;
        sto_tab(j).cod_tipo_docu := pscodigotipodocumento;
        sto_tab(j).num_lote := sol_post_venta_tab(j).num_lote;
        sto_tab(j).sec_nume_docu := sol_post_venta_tab(j).sec_nume_docu;
        sto_tab(j).num_docu := sol_post_venta_tab(j).num_docu;
        sto_tab(j).ind_envi := '0';
        sto_tab(j).ind_rech := '0';
        sto_tab(j).fec_digi := sol_post_venta_tab(j).fec_digi;
        sto_tab(j).usu_digi := sol_post_venta_tab(j).usu_digi;
        sto_tab(j).fec_modi := sol_post_venta_tab(j).fec_modi;
        sto_tab(j).usu_modi := sol_post_venta_tab(j).usu_modi;
        -- sto_tab(j).cod_zona := sol_post_venta_tab(j).cod_zona_arri;
        sto_tab(j).cod_clie := sol_post_venta_tab(j).cod_clie;
        -- sto_tab(j).cod_regi := sol_post_venta_tab(j).cod_regi_arri;
        sto_tab(j).sec_nume_docu_cabe := sol_post_venta_tab(j)
                                         .sec_nume_docu_cabe;
        sto_tab(j).cod_peri := sol_post_venta_tab(j).cod_peri;
        --sto_tab(j).COD_MOTI_RECH      :=
        -- sto_tab(j).val_obse_rech_defi := sol_post_venta_tab(j).val_obse_rech_defi;
        sto_tab(j).ind_rece_ocr := '0';
        sto_tab(j).ind_rece_web := '0';
        sto_tab(j).ind_rece_dd := '0';
        sto_tab(j).ind_rece_digi := '0';
        sto_tab(j).ind_rece_cc := '0';
        sto_tab(j).ind_rece_mens := '0';
        sto_tab(j).ind_elim := '0';
        --   sto_tab(j).cod_zona_arri := sol_post_venta_tab(j).cod_zona_arri;
        sto_tab(j).ind_rece_onli := '0';
        sto_tab(j).ind_rece_ivr := '0';
      
        SELECT cod_zona,
               cod_regi,
               val_obse_rech_defi,
               cod_zona_arri,
               ind_rece_ocr,
               ind_rece_digi,
               ind_rece_cc
          INTO sto_tab(j).cod_zona,
               sto_tab(j).cod_regi,
               sto_tab(j).val_obse_rech_defi,
               sto_tab(j).cod_zona_arri,
               sto_tab(j).ind_rece_ocr,
               sto_tab(j).ind_rece_digi,
               sto_tab(j).ind_rece_cc
          FROM sto_docum_digit
         WHERE sec_nume_docu = sol_post_venta_tab(j).sec_nume_docu_cabe
           AND num_lote = sol_post_venta_tab(j).num_lote;
      
      END LOOP;
    
      FORALL j IN sol_post_venta_tab.first .. sol_post_venta_tab.last
        INSERT INTO int_solic_conso_poven_detal
        VALUES sol_post_venta_tab
          (j);
    
      FORALL j IN sol_post_venta_tab.first .. sol_post_venta_tab.last
        INSERT INTO sto_docum_digit VALUES sto_tab (j);
    
    END LOOP;
    CLOSE curinsconsolpostventa;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_POST_VENTA_DETAL: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_post_venta_detal;
  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Actualizacion de Datos
  Fecha Creacion    : 17/06/2008
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_actua_datos
  (
    pscodigopais      VARCHAR2,
    pscodigousuario   VARCHAR2,
    psnumlote         VARCHAR2,
    pscodigodocumento VARCHAR2
  ) IS
  
    ---------------------------------------
    -- Patron caractrese extra?os 1
    CURSOR cur_cad IS
      SELECT crt_vali
        FROM mae_contr_carac
       WHERE cod_modu_vali = 'VAL_CRT_NV1'
         AND crt_indi = 'N';
    -- Patron caractrese extra?os 3
    CURSOR cur_cad2 IS
      SELECT crt_vali
        FROM mae_contr_carac
       WHERE cod_modu_vali = 'VAL_CRT_NV3'
         AND crt_indi = 'N';
  
    v_cur_cad  cur_cad%ROWTYPE;
    v_cur_cad2 cur_cad2%ROWTYPE;
  
    patron1 VARCHAR2(1000);
    patron2 VARCHAR2(1000);
    ---------------------------------------
  
    CURSOR curinsconsolcredi
    (
      vpatron1 VARCHAR2,
      vpatron2 VARCHAR2
    ) IS
      SELECT cred.cod_pais cod_pais,
             TRIM(translate(cred.cod_comp,
                            patron1,
                            lpad(' ', length(cred.cod_comp)))) cod_comp,
             -- Cambio para no insertar el codigo 000000000
             decode(REPLACE(cred.cod_clie, '0', ''),
                    NULL,
                    NULL,
                    (TRIM(translate(cred.cod_clie,
                                    patron1,
                                    lpad(' ', length(cred.cod_clie)))))) cod_clie,
             
             TRIM(translate(cred.num_docu,
                            patron1,
                            lpad(' ', length(cred.num_docu)))) num_docu,
             trunc(SYSDATE) fec_proc,
             TRIM(translate(cred.cod_unid_admi,
                            patron1,
                            lpad(' ', length(cred.cod_unid_admi)))) uni_admi,
             TRIM(translate(cred.cod_peri,
                            patron1,
                            lpad(' ', length(cred.cod_peri)))) cod_peri,
             TRIM(translate(cred.val_ape1,
                            patron1,
                            lpad(' ', length(cred.val_ape1)))) val_ape1,
             TRIM(translate(cred.val_ape2,
                            patron1,
                            lpad(' ', length(cred.val_ape2)))) val_ape2,
             TRIM(translate(cred.val_nom1,
                            patron1,
                            lpad(' ', length(cred.val_nom1)))) val_nom1,
             TRIM(translate(cred.val_nom2,
                            patron1,
                            lpad(' ', length(cred.val_nom2)))) val_nom2,
             cred.cod_tipo_docu tip_docu,
             TRIM(translate(cred.num_docu_iden,
                            patron1,
                            lpad(' ', length(cred.num_docu_iden)))) num_docu_iden,
             NULL num_ruc,
             TRIM(translate(cred.val_dire_nuev,
                            patron1,
                            lpad(' ', length(cred.val_dire_nuev)))) val_dire_clie,
             TRIM(translate(cred.des_banc_nuev,
                            patron1,
                            lpad(' ', length(cred.des_banc_nuev)))) val_barr_clie,
             TRIM(translate(cred.des_ciud_nuev,
                            patron1,
                            lpad(' ', length(cred.des_ciud_nuev)))) val_ciud_clie,
             TRIM(translate(cred.des_depa_nuev,
                            patron1,
                            lpad(' ', length(cred.des_depa_nuev)))) val_depa_clie,
             cred.val_tele_nuev val_telf_clie,
             cred.val_celu_nuev val_celu_clie,
             cred.val_tele_cetr val_telf_trab,
             TRIM(translate(cred.val_mail_nuev,
                            patron2,
                            lpad(' ', length(cred.val_mail_nuev)))) val_mail_clie,
             TRIM(translate(cred.cod_regi,
                            patron1,
                            lpad(' ', length(cred.cod_regi)))) val_barr_arri,
             TRIM(translate(cred.cod_zona,
                            patron1,
                            lpad(' ', length(cred.cod_zona)))) val_zona_arri,
             cred.cod_stat_proc ind_stat_proc,
             cred.cod_moti_rech ind_moti_rech,
             cred.tip_via_clie tip_via_clie,
             TRIM(translate(cred.val_nomb_via,
                            patron1,
                            lpad(' ', length(cred.val_nomb_via)))) val_nomb_vicl,
             TRIM(translate(cred.num_dire,
                            patron1,
                            lpad(' ', length(cred.num_dire)))) num_dire_clie,
             cred.cod_depa cod_depa_clie,
             cred.cod_prov cod_prov_clie,
             cred.cod_dist cod_dist_clie,
             cred.cod_cent_pobl cod_sect_clie,
             NULL oid_pais,
             NULL oid_terr,
             NULL oid_terr_admi,
             NULL oid_peri,
             pscodigodocumento docu_cod_tipo_docu,
             psnumlote num_lote,
             seq_docu_sto.nextval sec_nume_docu,
             cred.val_tipo_dire_clie val_tipo_dire_clie, --nuevos campo
             val_codi_post_clie val_codi_post_clie, --nuevo campo,
             val_dire_entre_clie val_dire_entre_clie,
             val_barr_entre_clie val_barr_entre_clie,
             val_dele_entre_clie val_dele_entre_clie,
             val_depa_entre_clie val_depa_entre_clie,
             val_tele_entre_clie val_tele_entre_clie,
             val_celu_entre_clie val_celu_entre_clie,
             val_dele_clie val_dele_clie,
             NULL,
             '0',
             NULL,
             NULL,
             NULL,
             NULL,
             cred.fec_hora_crea_soli,
             ciud_cod_ciud_domi,
             ciud_cod_ugeo_regi_domi,
             des_villa_pobl_domi,
             NULL,
             NULL,
             NULL,
             ind_orig,
             val_obse,
             oid_naci,
             cod_prem,
             val_ape2_fiad,
             val_nom2_fiad,
             cod_sexo,
             nvl(trim(oid_naci),sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais, 'STO_DEF_NACIO_SCC')),  --cod_naci,      
             cod_terr_corr, 
             ind_dird_dire,    
             TRIM(translate(cred.dom_manz,
              vpatron1,
              lpad(' ', length(cred.dom_manz)))) dom_manz,    
             TRIM(translate(cred.dom_etap,
              vpatron1,
              lpad(' ', length(cred.dom_etap)))) dom_etap, 
             TRIM(translate(cred.dom_call_prin,
              vpatron1,
              lpad(' ', length(cred.dom_call_prin)))) dom_call_prin,       
             TRIM(translate(cred.dom_call_secu,
              vpatron1,
              lpad(' ', length(cred.dom_call_secu)))) dom_call_secu,
             TRIM(translate(cred.dom_num,
              vpatron1,
              lpad(' ', length(cred.dom_num)))) dom_num, 
             TRIM(translate(cred.dom_refe,
              vpatron1,
              lpad(' ', length(cred.dom_refe)))) dom_refe,       
             TRIM(translate(cred.ent_manz,
              vpatron1,
              lpad(' ', length(cred.ent_manz)))) ent_manz,                    
             TRIM(translate(cred.ent_etap,
              vpatron1,
              lpad(' ', length(cred.ent_etap)))) ent_etap,  
             TRIM(translate(cred.ent_call_prin,
              vpatron1,
              lpad(' ', length(cred.ent_call_prin)))) ent_call_prin,                   
             TRIM(translate(cred.ent_call_secu,
              vpatron1,
              lpad(' ', length(cred.ent_call_secu)))) ent_call_secu, 
             TRIM(translate(cred.ent_num,
              vpatron1,
             lpad(' ', length(cred.ent_num)))) ent_num, 
             TRIM(translate(cred.ent_refe,
              vpatron1,
              lpad(' ', length(cred.ent_refe)))) ent_refe        
        FROM int_ocr_actua_datos cred
       WHERE cred.cod_pais = pscodigopais;
  
    TYPE actua_datos_tab_t IS TABLE OF int_solic_conso_actua_datos%ROWTYPE INDEX BY BINARY_INTEGER;
    actua_datos_tab actua_datos_tab_t; -- In-memory table
  
    j BINARY_INTEGER := 0;
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t; -- In-memory table
  
  BEGIN
  
    ---------------------------------------------
    OPEN cur_cad;
    LOOP
      FETCH cur_cad
        INTO v_cur_cad;
      EXIT WHEN cur_cad%NOTFOUND;
      patron1 := patron1 || v_cur_cad.crt_vali;
    END LOOP;
    CLOSE cur_cad;
  
    OPEN cur_cad2;
    LOOP
      FETCH cur_cad2
        INTO v_cur_cad2;
      EXIT WHEN cur_cad2%NOTFOUND;
      patron2 := patron2 || v_cur_cad2.crt_vali;
    END LOOP;
    CLOSE cur_cad2;
    ---------------------------------------------
  
    OPEN curinsconsolcredi(patron1, patron2);
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsolcredi BULK COLLECT

        INTO actua_datos_tab LIMIT w_filas;
      EXIT WHEN actua_datos_tab.count = 0;
    
      FOR j IN actua_datos_tab.first .. actua_datos_tab.last
      LOOP
      
        sto_tab(j).cod_pais := actua_datos_tab(j).cod_pais;
        sto_tab(j).cod_tipo_docu := pscodigodocumento;
        sto_tab(j).num_lote := actua_datos_tab(j).num_lote;
        sto_tab(j).sec_nume_docu := actua_datos_tab(j).sec_nume_docu;
        sto_tab(j).num_docu := actua_datos_tab(j).num_docu;
        sto_tab(j).ind_envi := '0';
        sto_tab(j).ind_rech := '0';
        sto_tab(j).fec_digi := SYSDATE;
        sto_tab(j).usu_digi := pscodigousuario;
        sto_tab(j).fec_modi := SYSDATE;
        sto_tab(j).usu_modi := pscodigousuario;
        -- sto_tab(i).cod_zona := sol_post_venta_tab(j).cod_zona_arri;
        sto_tab(j).cod_clie := actua_datos_tab(j).cod_clie;
        -- sto_tab(i).cod_regi := sol_post_venta_tab(j).cod_regi_arri;
        --        sto_tab(i).sec_nume_docu_cabe := sol_credi_tab(i).sec_nume_docu_cabe;
        sto_tab(j).cod_peri := actua_datos_tab(j).cod_peri;
        --sto_tab(i).COD_MOTI_RECH      :=
        -- sto_tab(i).val_obse_rech_defi := sol_post_venta_tab(j).val_obse_rech_defi;
        sto_tab(j).ind_rece_ocr := '0';
        sto_tab(j).ind_rece_web := '0';
        sto_tab(j).ind_rece_dd := '0';
        sto_tab(j).ind_rece_digi := '0';
        sto_tab(j).ind_rece_cc := '0';
        sto_tab(j).ind_rece_mens := '0';
        sto_tab(j).ind_elim := '0';
        sto_tab(j).cod_zona_arri := actua_datos_tab(j).val_zona_arri;
        sto_tab(j).ind_rece_onli := '0';
        sto_tab(j).ind_rece_ivr := '0';
        IF actua_datos_tab(j).ind_orig = 'O' THEN
          sto_tab(j).ind_rece_ocr := '1';
        ELSIF actua_datos_tab(j).ind_orig = 'W' THEN
          sto_tab(j).ind_rece_web := '1';
        END IF;
      END LOOP;
    
      FORALL j IN actua_datos_tab.first .. actua_datos_tab.last
        INSERT INTO int_solic_conso_actua_datos VALUES actua_datos_tab (j);
    
      FORALL j IN actua_datos_tab.first .. actua_datos_tab.last
        INSERT INTO sto_docum_digit VALUES sto_tab (j);
    END LOOP;
    CLOSE curinsconsolcredi;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_ACTUA_DATOS: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_actua_datos;
  /***************************************************************************
  Descripcion       : Devuelve una fecha en formato date
                      si no tiene el formato devuelve null
  Fecha Creacion    : 09/03/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  FUNCTION int_fn_devue_fecha
  (
    psfecha   VARCHAR2,
    psformato VARCHAR2
  ) RETURN DATE IS
    ldfecha DATE;
  
  BEGIN
    ldfecha := to_date(psfecha, psformato);
    RETURN ldfecha;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END int_fn_devue_fecha;

  /***************************************************************************
  Descripcion       : Genera Interface de OCS Web/DD Cabeceras
  Fecha Creacion    : 27/01/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_webdd_cabec
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pstiposolicitud   VARCHAR2,
    pscodigosubacceso VARCHAR2,
    pscodigoacceso    VARCHAR2,
    pstipodespacho    VARCHAR2,
    psstatusproceso   VARCHAR2,
    psfechademanda    VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codpais IS TABLE OF int_solic_cabec.cod_pais%TYPE;
    TYPE t_camsoli IS TABLE OF int_solic_cabec.cam_soli%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_cabec.cod_clie%TYPE;
    TYPE t_numclie IS TABLE OF int_solic_cabec.num_clie%TYPE;
    TYPE t_fecproc IS TABLE OF int_solic_cabec.fec_proc%TYPE;
    TYPE t_fecfact IS TABLE OF int_solic_cabec.fec_fact%TYPE;
    TYPE t_codregi IS TABLE OF int_solic_cabec.cod_regi_arri%TYPE;
    TYPE t_codzona IS TABLE OF int_solic_cabec.cod_zona_arri%TYPE;
    TYPE t_numlotedd IS TABLE OF int_solic_cabec.num_lote_dd%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_cabec.num_docu%TYPE;
    TYPE t_indproc IS TABLE OF int_solic_cabec.ind_proc%TYPE;
    TYPE t_staproc IS TABLE OF VARCHAR2(1);
    TYPE t_motrech IS TABLE OF int_solic_cabec.cod_moti_rech%TYPE;
  
    v_codpais   t_codpais := t_codpais();
    v_camsoli   t_camsoli := t_camsoli();
    v_codclie   t_codclie := t_codclie();
    v_numclie   t_numclie := t_numclie();
    v_fecproc   t_fecproc := t_fecproc();
    v_fecfact   t_fecfact := t_fecfact();
    v_codregi   t_codregi := t_codregi();
    v_codzona   t_codzona := t_codzona();
    v_numlotedd t_numlotedd := t_numlotedd();
    v_numdocu   t_numdocu := t_numdocu();
    v_indproc   t_indproc := t_indproc();
    v_staproc   t_staproc := t_staproc();
    v_motrech   t_motrech := t_motrech();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    v_camactiva int_solic_cabec.cam_soli%TYPE;
  
    vn_contador_pais NUMBER;
  
  BEGIN
  
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
    --Obteniendo la periodo activa
    SELECT c.cod_peri
      INTO v_camactiva
      FROM bas_ctrl_fact c
     WHERE c.cod_pais = pscodigopais
       AND c.ind_camp_act = 1
       AND c.sta_camp = '0';
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_staproc.extend;
                  v_motrech.extend;
                
                  v_codpais(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '00');
                
                  v_staproc(i) := psstatusproceso;
                  v_motrech(i) := '';
                
                  -- Se valida que el pais no sea nulo si lo es se pone el pais por defecto
                  IF (v_codpais(i) = '00') THEN
                    v_codpais(i) := pscodigopais;
                    v_staproc(i) := 'R';
                    v_motrech(i) := '20';
                  END IF;
                
                  IF (v_staproc(i) <> 'R') THEN
                    -- Se valida que el pais este registrado
                    SELECT COUNT(1)
                      INTO vn_contador_pais
                      FROM bas_pais b
                     WHERE b.cod_pais = v_codpais(i);
                  
                    IF (vn_contador_pais = 0) THEN
                      v_codpais(i) := pscodigopais;
                      v_staproc(i) := 'R';
                      v_motrech(i) := '20';
                    END IF;
                  END IF;
                
                ELSIF (posicion = 2) THEN
                  v_camsoli.extend;
                  v_camsoli(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                
                  -- Se valida que la periodo no sea nulo si lo es se pone la periodo activa
                  IF (v_camsoli(i) = '0') THEN
                    v_camsoli(i) := v_camactiva;
                    v_staproc(i) := 'R';
                    v_motrech(i) := '20';
                  ELSE
                  
                    BEGIN
                      SELECT cod_peri
                        INTO v_camsoli(i)
                        FROM bas_ctrl_fact
                       WHERE cod_pais = v_codpais(i)
                         AND cod_peri = v_camsoli(i);
                    
                    EXCEPTION
                      WHEN no_data_found THEN
                        BEGIN
                        
                          SELECT cod_peri
                            INTO v_camsoli(i)
                            FROM seg_perio_corpo
                           WHERE cod_peri = v_camsoli(i);
                          /*INSERTA LA periodo EN CASO NO EXISTA EN EL ARCHIVO DE CONTROL*/
                          INSERT INTO bas_ctrl_fact
                            (cod_pais,
                             cod_peri,
                             fec_proc,
                             val_mnt_min_fact,
                             val_mnt_min_acept,
                             val_mnt_max,
                             val_unid_max,
                             sta_camp,
                             usu_digi,
                             fec_digi,
                             cod_marc,
                             des_marc,
                             cod_cana,
                             des_cana,
                             val_mnt_min_deud,
                             ind_camp_act,
                             num_lote)
                            SELECT cod_pais,
                                   v_camsoli(i),
                                   fec_proc,
                                   val_mnt_min_fact,
                                   val_mnt_min_acept,
                                   val_mnt_max,
                                   val_unid_max,
                                   '1',
                                   USER,
                                   SYSDATE,
                                   cod_marc,
                                   des_marc,
                                   cod_cana,
                                   des_cana,
                                   val_mnt_min_deud,
                                   '0',
                                   num_lote
                              FROM bas_ctrl_fact c
                             WHERE c.cod_pais = pscodigopais
                               AND c.ind_camp_act = '1'
                               AND c.sta_camp = '0';
                        
                        EXCEPTION
                          WHEN no_data_found THEN
                            v_camsoli(i) := v_camactiva;
                          
                            IF v_staproc(i) <> 'R' THEN
                              v_staproc(i) := 'R';
                              v_motrech(i) := '20';
                            END IF;
                        END;
                    END;
                  
                  END IF;
                
                ELSIF (posicion = 3) THEN
                  v_codclie.extend;
                  v_codclie(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                ELSIF (posicion = 4) THEN
                  v_numdocu.extend;
                  v_numdocu(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 5) THEN
                  v_numclie.extend;
                  v_numclie(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      0);
                ELSIF (posicion = 6) THEN
                  v_fecproc.extend;
                  v_fecproc(i) := to_date(TRIM(substr(lslinea,
                                                      inicio,
                                                      longitud)),
                                          'yyyyMMdd');
                ELSIF (posicion = 7) THEN
                  v_fecfact.extend;
                  v_fecfact(i) := to_date(nvl(psfechademanda,
                                              TRIM(substr(lslinea,
                                                          inicio,
                                                          longitud))),
                                          'yyyyMMdd');
                ELSIF (posicion = 8) THEN
                  v_codregi.extend;
                  v_codregi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 9) THEN
                  v_codzona.extend;
                  v_codzona(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 10) THEN
                  v_numlotedd.extend;
                  v_numlotedd(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 11) THEN
                  v_indproc.extend;
                  v_indproc(i) := TRIM(substr(lslinea, inicio, longitud));
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codpais.count
      INSERT INTO int_solic_cabec
        (cod_pais,
         cam_soli,
         cod_clie,
         oid_cab,
         num_lote,
         num_clie,
         tip_soli,
         cod_comp,
         num_docu,
         cod_regi_arri,
         cod_zona_arri,
         cod_suba,
         cod_acce,
         tip_desp,
         fec_soli,
         sta_proc,
         ind_proc,
         fec_proc,
         fec_fact,
         num_lote_dd,
         cod_moti_rech,
         cod_inte,
         num_lote_inte,
         num_lote_sto)
      VALUES
        (v_codpais(i),
         v_camsoli(i),
         v_codclie(i),
         seq_solic_cab.nextval,
         (SELECT bas.num_lote
            FROM bas_ctrl_fact bas
           WHERE bas.cod_pais = v_codpais(i)
             AND bas.cod_peri = v_camsoli(i)),
         v_numclie(i),
         pstiposolicitud,
         (SELECT cod_comp FROM bas_pais_compa WHERE cod_pais = v_codpais(i)),
         v_numdocu(i),
         v_codregi(i),
         v_codzona(i),
         pscodigosubacceso,
         pscodigoacceso,
         pstipodespacho,
         v_fecfact(i),
         v_staproc(i),
         v_indproc(i),
         v_fecproc(i),
         v_fecfact(i),
         v_numlotedd(i),
         v_motrech(i),
         pscodigointerfaz,
         psnumerolote,
         psnumerolotesto);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_RECEP_WEBDD_CABEC: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END ocr_pr_recep_webdd_cabec;

  /***************************************************************************
  Descripcion       : Genera Interface de OCS Web/DD Cabeceras
  Fecha Creacion    : 27/01/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_wdpro_cabec
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    pstiposolicitud   VARCHAR2,
    pscodigosubacceso VARCHAR2,
    pscodigoacceso    VARCHAR2,
    pstipodespacho    VARCHAR2,
    psstatusproceso   VARCHAR2,
    psfechademanda    VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psIndicadorDA     VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codpais IS TABLE OF int_solic_cabec.cod_pais%TYPE;
    TYPE t_camsoli IS TABLE OF int_solic_cabec.cam_soli%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_cabec.cod_clie%TYPE;
    TYPE t_numclie IS TABLE OF int_solic_cabec.num_clie%TYPE;
    TYPE t_fecproc IS TABLE OF int_solic_cabec.fec_proc%TYPE;
    TYPE t_fecfact IS TABLE OF int_solic_cabec.fec_fact%TYPE;
    TYPE t_codregi IS TABLE OF int_solic_cabec.cod_regi_arri%TYPE;
    TYPE t_codzona IS TABLE OF int_solic_cabec.cod_zona_arri%TYPE;
    TYPE t_numlotedd IS TABLE OF int_solic_cabec.num_lote_dd%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_cabec.num_docu%TYPE;
    TYPE t_indproc IS TABLE OF int_solic_cabec.ind_proc%TYPE;
    TYPE t_staproc IS TABLE OF VARCHAR2(1);
    TYPE t_motrech IS TABLE OF int_solic_cabec.cod_moti_rech%TYPE;
    TYPE t_indvaliprol IS TABLE OF int_solic_cabec.ind_vali_prol%TYPE;
    TYPE t_indda       IS TABLE OF int_solic_cabec.IND_VALI_DEAN%TYPE;
  
    v_codpais     t_codpais := t_codpais();
    v_camsoli     t_camsoli := t_camsoli();
    v_codclie     t_codclie := t_codclie();
    v_numclie     t_numclie := t_numclie();
    v_fecproc     t_fecproc := t_fecproc();
    v_fecfact     t_fecfact := t_fecfact();
    v_codregi     t_codregi := t_codregi();
    v_codzona     t_codzona := t_codzona();
    v_numlotedd   t_numlotedd := t_numlotedd();
    v_numdocu     t_numdocu := t_numdocu();
    v_indproc     t_indproc := t_indproc();
    v_staproc     t_staproc := t_staproc();
    v_motrech     t_motrech := t_motrech();
    v_indvaliprol t_indvaliprol := t_indvaliprol();
    v_indda       t_indda := t_indda();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    v_camactiva int_solic_cabec.cam_soli%TYPE;
  
    vn_contador_pais NUMBER;
    valorIndda  int_solic_cabec.IND_VALI_DEAN%TYPE;  
  BEGIN
  
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
    --Obteniendo la periodo activa
    SELECT c.cod_peri
      INTO v_camactiva
      FROM bas_ctrl_fact c
     WHERE c.cod_pais = pscodigopais
       AND c.ind_camp_act = 1
       AND c.sta_camp = '0';
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_staproc.extend;
                  v_motrech.extend;
                
                  v_codpais(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '00');
                
                  v_staproc(i) := psstatusproceso;
                  v_motrech(i) := '';
                
                  -- Se valida que el pais no sea nulo si lo es se pone el pais por defecto
                  IF (v_codpais(i) = '00') THEN
                    v_codpais(i) := pscodigopais;
                    v_staproc(i) := 'R';
                    v_motrech(i) := '20';
                  END IF;
                
                  IF (v_staproc(i) <> 'R') THEN
                    -- Se valida que el pais este registrado
                    SELECT COUNT(1)
                      INTO vn_contador_pais
                      FROM bas_pais b
                     WHERE b.cod_pais = v_codpais(i);
                  
                    IF (vn_contador_pais = 0) THEN
                      v_codpais(i) := pscodigopais;
                      v_staproc(i) := 'R';
                      v_motrech(i) := '20';
                    END IF;
                  END IF;
                
                ELSIF (posicion = 2) THEN
                  v_camsoli.extend;
                  v_camsoli(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                
                  -- Se valida que la periodo no sea nulo si lo es se pone la periodo activa
                  IF (v_camsoli(i) = '0') THEN
                    v_camsoli(i) := v_camactiva;
                    v_staproc(i) := 'R';
                    v_motrech(i) := '20';
                  ELSE
                  
                    BEGIN
                      SELECT cod_peri
                        INTO v_camsoli(i)
                        FROM bas_ctrl_fact
                       WHERE cod_pais = v_codpais(i)
                         AND cod_peri = v_camsoli(i);
                    
                    EXCEPTION
                      WHEN no_data_found THEN
                        BEGIN
                        
                          SELECT cod_peri
                            INTO v_camsoli(i)
                            FROM seg_perio_corpo
                           WHERE cod_peri = v_camsoli(i);
                          /*INSERTA LA periodo EN CASO NO EXISTA EN EL ARCHIVO DE CONTROL*/
                          INSERT INTO bas_ctrl_fact
                            (cod_pais,
                             cod_peri,
                             fec_proc,
                             val_mnt_min_fact,
                             val_mnt_min_acept,
                             val_mnt_max,
                             val_unid_max,
                             sta_camp,
                             usu_digi,
                             fec_digi,
                             cod_marc,
                             des_marc,
                             cod_cana,
                             des_cana,
                             val_mnt_min_deud,
                             ind_camp_act,
                             num_lote)
                            SELECT cod_pais,
                                   v_camsoli(i),
                                   fec_proc,
                                   val_mnt_min_fact,
                                   val_mnt_min_acept,
                                   val_mnt_max,
                                   val_unid_max,
                                   '1',
                                   USER,
                                   SYSDATE,
                                   cod_marc,
                                   des_marc,
                                   cod_cana,
                                   des_cana,
                                   val_mnt_min_deud,
                                   '0',
                                   num_lote
                              FROM bas_ctrl_fact c
                             WHERE c.cod_pais = pscodigopais
                               AND c.ind_camp_act = '1'
                               AND c.sta_camp = '0';
                        
                        EXCEPTION
                          WHEN no_data_found THEN
                            v_camsoli(i) := v_camactiva;
                          
                            IF v_staproc(i) <> 'R' THEN
                              v_staproc(i) := 'R';
                              v_motrech(i) := '20';
                            END IF;
                        END;
                    END;
                  
                  END IF;
                
                ELSIF (posicion = 3) THEN
                  v_codclie.extend;
                  v_codclie(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                ELSIF (posicion = 4) THEN
                  v_numdocu.extend;
                  v_numdocu(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 5) THEN
                  v_numclie.extend;
                  v_numclie(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      0);
                ELSIF (posicion = 6) THEN
                  v_fecproc.extend;
                  v_fecproc(i) := to_date(TRIM(substr(lslinea,
                                                      inicio,
                                                      longitud)),
                                          'yyyyMMdd');
                ELSIF (posicion = 7) THEN
                  v_fecfact.extend;
                  v_fecfact(i) := to_date(nvl(psfechademanda,
                                              TRIM(substr(lslinea,
                                                          inicio,
                                                          longitud))),
                                          'yyyyMMdd');
                ELSIF (posicion = 8) THEN
                  v_codregi.extend;
                  v_codregi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 9) THEN
                  v_codzona.extend;
                  v_codzona(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 10) THEN
                  v_numlotedd.extend;
                  v_numlotedd(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 11) THEN
                  v_indproc.extend;
                  v_indproc(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 12) THEN
                  v_indvaliprol.extend;
                  v_indvaliprol(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 13) THEN
                  v_indda.extend;
                  BEGIN
                     v_indda(i) := TRIM(substr(lslinea, inicio, longitud));
                  EXCEPTION
                    WHEN OTHERS THEN
                       v_indda(i) := '';
                  END;
                END IF;
                inicio := inicio + longitud;
             END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
    
    FOR x IN v_indda.first .. v_indda.last LOOP
        valorIndda := v_indda(x);
        IF (valorIndda IS NULL) THEN
            IF (valorIndda != '0' AND valorIndda != '1') THEN
                valorIndda := NULL;
            END IF;
        END IF;
        IF (psIndicadorDA IS NOT NULL) THEN
            valorIndda := psIndicadorDA;
        END IF;
        v_indda(x) := valorIndda ;
    END LOOP;
    
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codpais.count
      INSERT INTO int_solic_cabec
        (cod_pais,
         cam_soli,
         cod_clie,
         oid_cab,
         num_lote,
         num_clie,
         tip_soli,
         cod_comp,
         num_docu,
         cod_regi_arri,
         cod_zona_arri,
         cod_suba,
         cod_acce,
         tip_desp,
         fec_soli,
         sta_proc,
         ind_proc,
         fec_proc,
         fec_fact,
         num_lote_dd,
         cod_moti_rech,
         cod_inte,
         num_lote_inte,
         num_lote_sto,
         ind_vali_prol,
         IND_VALI_DEAN)
      VALUES
        (v_codpais(i),
         v_camsoli(i),
         v_codclie(i),
         seq_solic_cab.nextval,
         (SELECT bas.num_lote
            FROM bas_ctrl_fact bas
           WHERE bas.cod_pais = v_codpais(i)
             AND bas.cod_peri = v_camsoli(i)),
         v_numclie(i),
         pstiposolicitud,
         (SELECT cod_comp FROM bas_pais_compa WHERE cod_pais = v_codpais(i)),
         v_numdocu(i),
         v_codregi(i),
         v_codzona(i),
         pscodigosubacceso,
         pscodigoacceso,
         pstipodespacho,
         v_fecfact(i),
         v_staproc(i),
         v_indproc(i),
         v_fecproc(i),
         v_fecfact(i),
         v_numlotedd(i),
         v_motrech(i),
         pscodigointerfaz,
         psnumerolote,
         psnumerolotesto,
         v_indvaliprol(i),
         v_indda(i) );
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_RECEP_WDPRO_CABEC: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END ocr_pr_recep_wdpro_cabec;
  /***************************************************************************
  Descripcion       : Genera Interfase de OCS Web/DD Detalles
  Fecha Creacion    : 27/01/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_webdd_detal
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    pstipoposicion   VARCHAR2,
    psstatusproceso  VARCHAR2,
    psnumerolote     VARCHAR2,
    psnumerolotesto  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codpais IS TABLE OF int_solic_posic.cod_pais%TYPE;
    TYPE t_camsoli IS TABLE OF int_solic_posic.cam_soli%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_posic.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_posic.num_docu%TYPE;
    TYPE t_codvent IS TABLE OF int_solic_posic.cod_vent%TYPE;
    TYPE t_unidema IS TABLE OF int_solic_posic.uni_dema%TYPE;
    TYPE t_codprod IS TABLE OF int_solic_posic.cod_prod%TYPE;
    TYPE t_numlotedd IS TABLE OF int_solic_posic.num_lote_dd%TYPE;
    TYPE t_staproc IS TABLE OF VARCHAR2(1);
  
    v_codpais   t_codpais := t_codpais();
    v_camsoli   t_camsoli := t_camsoli();
    v_codclie   t_codclie := t_codclie();
    v_numdocu   t_numdocu := t_numdocu();
    v_codvent   t_codvent := t_codvent();
    v_unidema   t_unidema := t_unidema();
    v_codprod   t_codprod := t_codprod();
    v_numlotedd t_numlotedd := t_numlotedd();
    v_staproc   t_staproc := t_staproc();
  
    -- Variables usadas para la ejecucion del PL/SQL Dinamico.
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    v_camactiva      int_solic_cabec.cam_soli%TYPE;
    v_existe_campana NUMBER;
    vn_contador_pais NUMBER;
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          --Obteniendo la periodo activa
          SELECT c.cod_peri
            INTO v_camactiva
            FROM bas_ctrl_fact c
           WHERE c.cod_pais = pscodigopais
             AND c.ind_camp_act = 1
             AND c.sta_camp = '0';
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_staproc.extend;
                
                  v_codpais(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '00');
                
                  v_staproc(i) := psstatusproceso;
                
                  -- Se valida que el pais no sea nulo si lo es se pone el pais por defecto
                  IF (v_codpais(i) = '00') THEN
                    v_codpais(i) := pscodigopais;
                    v_staproc(i) := 'R';
                  END IF;
                
                  IF (v_staproc(i) <> 'R') THEN
                    -- Se valida que el pais este registrado
                    SELECT COUNT(1)
                      INTO vn_contador_pais
                      FROM bas_pais b
                     WHERE b.cod_pais = v_codpais(i);
                  
                    IF (vn_contador_pais = 0) THEN
                      v_codpais(i) := pscodigopais;
                    END IF;
                  END IF;
                
                ELSIF (posicion = 2) THEN
                  v_camsoli.extend;
                  v_camsoli(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                
                  -- Se valida que la periodo no sea nulo si lo es se pone la periodo activa
                  IF (v_camsoli(i) = '0') THEN
                    v_camsoli(i) := v_camactiva;
                    v_staproc(i) := 'R';
                  END IF;
                
                  -- Se valida que exista la periodo en la tabla bas_ctrl_fact
                  SELECT COUNT(1)
                    INTO v_existe_campana
                    FROM bas_ctrl_fact
                   WHERE cod_pais = v_codpais(i)
                     AND cod_peri = v_camsoli(i);
                
                  IF (v_existe_campana = 0) THEN
                    v_camsoli(i) := v_camactiva;
                  END IF;
                
                  IF ((v_existe_campana = 0) AND (v_staproc(i) <> 'R')) THEN
                    v_staproc(i) := 'R';
                  END IF;
                
                ELSIF (posicion = 3) THEN
                  v_codclie.extend;
                  v_codclie(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                ELSIF (posicion = 4) THEN
                  v_numdocu.extend;
                  v_numdocu(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 5) THEN
                  v_codvent.extend;
                  v_codvent(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                ELSIF (posicion = 6) THEN
                  v_unidema.extend;
                  v_unidema(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      0);
                ELSIF (posicion = 7) THEN
                  v_codprod.extend;
                  v_codprod(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                ELSIF (posicion = 8) THEN
                  v_numlotedd.extend;
                  v_numlotedd(i) := substr(lslinea, inicio, longitud);
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codpais.count
      INSERT INTO int_solic_posic
        (cod_pais,
         cam_soli,
         cod_clie,
         cod_prod,
         oid_posic,
         num_lote,
         cod_comp,
         num_docu,
         tip_posi,
         uni_dema,
         sta_proc,
         ind_proc,
         cod_vent,
         num_lote_dd,
         cod_inte,
         num_lote_inte,
         num_lote_sto)
        SELECT v_codpais(i),
               v_camsoli(i),
               v_codclie(i),
               v_codprod(i),
               seq_solic_pos.nextval,
               c.num_lote,
               c.cod_comp,
               v_numdocu(i),
               pstipoposicion,
               v_unidema(i),
               v_staproc(i),
               c.ind_proc,
               v_codvent(i),
               v_numlotedd(i),
               pscodigointerfaz,
               psnumerolote,
               psnumerolotesto
          FROM int_solic_cabec c
         WHERE c.cod_pais = v_codpais(i)
           AND c.cam_soli = v_camsoli(i)
           AND c.cod_clie = v_codclie(i)
           AND c.num_docu = v_numdocu(i)
           AND c.num_lote_sto = psnumerolotesto
           AND rownum = 1;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_RECEP_WEBDD_DETAL: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_recep_webdd_detal;
  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Dupla Cyzone
  Fecha Creacion    : 04/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_dupla_cyzon
  (
    pscodigopais      VARCHAR2,
    pscodigousuario   VARCHAR2,
    psnumlote         VARCHAR2,
    pscodigodocumento VARCHAR2
  ) IS
    CURSOR curinscondupcyz IS
      SELECT dupla.cod_pais cod_pais,
             dupla.cod_comp cod_comp,
             dupla.cod_clie cod_clie,
             dupla.num_docu num_docu,
             NULL cod_peri,
             dupla.val_ape1 val_ape1,
             dupla.val_ape2 val_ape2,
             dupla.val_nom1 val_nom1,
             dupla.val_nom2 val_nom2,
             int_pkg_occrr.int_fn_devue_fecha(dupla.fec_naci, 'YYYYMMDD') fec_naci,
             dupla.val_tele_casa val_telf_clie,
             dupla.val_tele_celu val_celu_clie,
             dupla.val_mail_dupl val_mail_clie,
             dupla.val_dupl_nuev ind_dupl_nuev,
             dupla.val_actu_dato ind_actu_dato,
             dupla.ind_envi ind_envi,
             dupla.cod_regi_arri cod_regi_arri,
             dupla.cod_zona_arri cod_zona_arri,
             int_pkg_occrr.int_fn_devue_fecha(dupla.fec_proc, 'YYYYMMDD') fec_proc,
             dupla.cod_stat_proc cod_stat_proc,
             dupla.cod_moti_rech cod_moti_rech,
             NULL oid_pais,
             NULL oid_terr,
             NULL oid_terr_admi,
             NULL oid_peri,
             NULL oid_clie_madr,
             NULL cod_clie_dupl,
             pscodigodocumento docu_cod_tipo_docu,
             psnumlote num_lote,
             seq_docu_sto.nextval sec_nume_docu,
             ind_orig
        FROM int_ocr_dupla_cyzon dupla
       WHERE dupla.cod_pais = pscodigopais;
  
    TYPE dupla_cyzon_tab_t IS TABLE OF int_solic_conso_dupla_cyzon%ROWTYPE INDEX BY BINARY_INTEGER;
    dupla_cyzon_tab dupla_cyzon_tab_t; -- In-memory table
  
    j BINARY_INTEGER := 0;
  
    v_periodo VARCHAR2(50);
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t; -- In-memory table
  
  BEGIN
  
    v_periodo := gen_pkg_gener.gen_fn_devuelve_perio_actu(gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais),
                                                          'T',
                                                          gen_pkg_gener.gen_fn_devuelve_id_canal('VD'));
  
    OPEN curinscondupcyz;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinscondupcyz BULK COLLECT
        INTO dupla_cyzon_tab LIMIT w_filas;
      EXIT WHEN dupla_cyzon_tab.count = 0;
    
      FOR j IN dupla_cyzon_tab.first .. dupla_cyzon_tab.last
      LOOP
      
        dupla_cyzon_tab(j).cod_peri := v_periodo;
      
        sto_tab(j).cod_pais := dupla_cyzon_tab(j).cod_pais;
        sto_tab(j).cod_tipo_docu := pscodigodocumento;
        sto_tab(j).num_lote := dupla_cyzon_tab(j).num_lote;
        sto_tab(j).sec_nume_docu := dupla_cyzon_tab(j).sec_nume_docu;
        sto_tab(j).num_docu := dupla_cyzon_tab(j).num_docu;
        sto_tab(j).ind_envi := '0';
        sto_tab(j).ind_rech := '0';
        sto_tab(j).fec_digi := SYSDATE;
        sto_tab(j).usu_digi := pscodigousuario;
        sto_tab(j).fec_modi := SYSDATE;
        sto_tab(j).usu_modi := pscodigousuario;
        --sto_tab(j).cod_zona := dupla_cyzon_tab(j).val_zona_arri;
        sto_tab(j).cod_clie := dupla_cyzon_tab(j).cod_clie;
        -- sto_tab(j).cod_regi := sol_credi_tab(j).cod_regi;
        sto_tab(j).cod_peri := dupla_cyzon_tab(j).cod_peri;
        --sto_tab(j).COD_MOTI_RECH      :=
        --        sto_tab(j).val_obse_rech_defi := dupla_cyzon_tab(j).val_obse_rech_defi;
        sto_tab(j).ind_rece_ocr := '0';
        sto_tab(j).ind_rece_web := '0';
        sto_tab(j).ind_rece_dd := '0';
        sto_tab(j).ind_rece_digi := '0';
        sto_tab(j).ind_rece_cc := '0';
        sto_tab(j).ind_rece_mens := '0';
        sto_tab(j).ind_elim := '0';
        sto_tab(j).cod_zona_arri := dupla_cyzon_tab(j).cod_zona_arri;
        sto_tab(j).ind_rece_onli := '0';
        sto_tab(j).ind_rece_ivr := '0';
      
        IF dupla_cyzon_tab(j).ind_orig = 'O' THEN
          sto_tab(j).ind_rece_ocr := '1';
        ELSIF dupla_cyzon_tab(j).ind_orig = 'W' THEN
          sto_tab(j).ind_rece_web := '1';
        END IF;
      
      END LOOP;
    
      FORALL j IN dupla_cyzon_tab.first .. dupla_cyzon_tab.last
        INSERT INTO int_solic_conso_dupla_cyzon VALUES dupla_cyzon_tab (j);
    
      FORALL j IN dupla_cyzon_tab.first .. dupla_cyzon_tab.last
        INSERT INTO sto_docum_digit VALUES sto_tab (j);
    END LOOP;
    CLOSE curinscondupcyz;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_DUPLA_CYZON: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_dupla_cyzon;
  /***************************************************************************
  Descripcion       : Consolida los pedidos WEB- DD - OCR
  Fecha Creacion    : 25/10/2011
  Parameters        :
    pscodigopais      Pais
    pscodigodocumento Documento STO 'OCC' Orden de Compra
    pscodigousuario   Usuario
    psnumeroLoteSTO   Lote Generado por de Carga STO
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_solic_conso_pedid
  (
    pscodigopais      VARCHAR2,
    pscodigodocumento VARCHAR2,
    pscodigousuario   VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
  
    lsindactufechafact bas_pais.ind_actu_fech_fact%TYPE;
    lsindactuacliente  bas_pais.ind_actu_clie_cedu%TYPE;
  
    CURSOR c_periodos IS
      SELECT cb.cam_soli,
             trunc(MAX(cb.fec_soli)) fec_soli
        FROM int_solic_cabec cb
       WHERE cb.num_lote_sto = psnumerolotesto
       GROUP BY cb.cam_soli;
  
    TYPE t_codperi IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_fecsoli IS TABLE OF int_solic_conso_cabec.fec_soli%TYPE;
  
    v_codperi t_codperi;
    v_fecsoli t_fecsoli;
  
    i BINARY_INTEGER := 0;
  
    v_ind_cons_mult_lote bas_pais.ind_cons_pedi_mult_lote%TYPE;
  
    lsperiodoactual seg_perio_corpo.cod_peri%TYPE;
  
    lsindactuaperiodo bas_param_pais.val_para%TYPE;
  
    lsindresumprefac bas_param_pais.val_para%TYPE;
  
    lsindvaliduplweb bas_param_pais.val_para%TYPE;
  
    lsindvaliduplpreimp bas_param_pais.val_para%TYPE;
  
    lsnuevolote bas_ctrl_fact.num_lote%TYPE;
  
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
  BEGIN
  
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigodocumento);
  
    /*periodo Actual*/
    SELECT MIN(cod_peri)
      INTO lsperiodoactual
      FROM bas_ctrl_fact bas
     WHERE bas.sta_camp = '0'
       AND bas.ind_camp_act = '1';
  
    SELECT ind_actu_fech_fact,
           ind_actu_clie_cedu,
           ind_cons_pedi_mult_lote
      INTO lsindactufechafact,
           lsindactuacliente,
           v_ind_cons_mult_lote
      FROM bas_pais
     WHERE cod_pais = pscodigopais;
  
    lsindactuaperiodo := gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                         'OCR',
                                                         '012');
    lsindresumprefac  := gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                         'PED',
                                                         '004');
    lsindvaliduplweb  := gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                         'PED',
                                                         '010');
  
    lsindvaliduplpreimp := gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                           'PED',
                                                           '014');
  
    IF psindicadororigen != 'L' THEN
    
      /*ACTUALIZA CODIGOS DE CONSULTORA ANTES DE CONSOLIDARLOS*/
      ocr_actua_clien(pscodigopais, lsindactuacliente, psnumerolotesto);
    
      ocr_actua_clien_valid_pedid(pscodigopais, lsindactuacliente);
    
    END IF;
    IF psindicadororigen = 'O' AND lsindvaliduplpreimp = 'S' THEN
      --Verificando si existen pedidos duplicados para los WEB y DD
      ocr_pr_elimi_pedid_dupli_preim(pscodigopais, psnumerolotesto);
    END IF;
    IF lsindvaliduplweb = 'S' THEN
      --Verificando si existen pedidos duplicados para los WEB y DD
      IF (psindicadororigen = 'W' OR psindicadororigen = 'D') THEN
        ocr_pr_elimi_pedid_dupli_webdd(pscodigopais, psnumerolotesto);
      END IF;
    END IF;
    /*GUARDA LOS INFORMACION DE LOS PEDIDOS ANTES DE CONSOLIDARLOS*/
    ocr_pr_pedid_befor_conso(pscodigopais,
                             pscodigousuario,
                             psnumerolotesto);
  
    /*REVIERTE LOS PEDIDOS QUE SE ENCUENTRAN HASTA UN GP PARAMETRIZADO*/
    sto_pkg_proce_gener.sto_pr_rever_pedgp(pscodigopais,
                                           pscodigodocumento,
                                           pscodigousuario,
                                           psnumerolotesto);
  
    OPEN c_periodos;
    LOOP
      FETCH c_periodos BULK COLLECT
        INTO v_codperi,
             v_fecsoli LIMIT w_filas;
    
      IF v_codperi.count > 0 THEN
      
        FOR i IN v_codperi.first .. v_codperi.last
        LOOP
        
          IF lsindactufechafact != 'S' THEN
            v_fecsoli(i) := NULL;
          END IF;
          /*GENERA UN NUEVO LOTE PARA EL PERIODO*/
          gen_pkg_gener.gen_pr_actua_lote_fecha_perio(pscodigopais,
                                                      v_codperi(i),
                                                      v_fecsoli(i),
                                                      lsnuevolote);
        
          /*PREPARA LOS PEDIDOS PARA QUE SE CONSOLIDEN POR MULTILOTE*/
          IF psindicadororigen != 'L' THEN
            IF v_ind_cons_mult_lote = 'S' THEN
              int_pr_ocr_conso_detal_lotes(pscodigopais,
                                           v_codperi(i),
                                           psnumerolotesto);
            END IF;
          END IF;
        
          ocr_solic_pedidos.ocr_pr_solic_conso_cabec(pscodigopais,
                                                     pscodigodocumento,
                                                     v_codperi(i),
                                                     pscodigousuario,
                                                     psnumerolotesto);
        
          ocr_solic_pedidos.ocr_pr_solic_conso_detal(pscodigopais,
                                                     lscodigodocumentodetalle,
                                                     v_codperi(i),
                                                     pscodigousuario,
                                                     psnumerolotesto);
        
          IF psindicadororigen = 'G' OR psindicadororigen = 'L' THEN
            ocr_pr_marca_pedid_digit(v_codperi(i), psnumerolotesto);
          END IF;
        
          IF lsindresumprefac = 'S' THEN
            evi_pkg_ejecu_virtu.evi_pr_carga_resum_pre_factu;
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_periodos%NOTFOUND;
    
    END LOOP;
    CLOSE c_periodos;
  
    IF lsindactuaperiodo = 'N' AND lsperiodoactual IS NOT NULL THEN
      gen_pkg_gener.gen_pr_actua_perio_actua(pscodigopais, lsperiodoactual);
    END IF;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_SOLIC_CONSO_PEDID: ' ||
                              ls_sqlerrm);
  END ocr_pr_solic_conso_pedid;

  /***************************************************************************
  Descripcion       : Registra laos pedidos antes de consolidarlos
  Fecha Creacion    : 06/02/2008
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_pr_pedid_befor_conso
  (
    pscodigopais    VARCHAR2,
    pscodigousuario VARCHAR2,
    psnumerolotesto VARCHAR2
  ) IS
  
  BEGIN
  
    INSERT INTO int_solic_pedid_detal
      (cod_pais,
       cod_peri,
       num_lote,
       cod_clie,
       num_docu,
       fec_soli,
       num_deta,
       fec_digi,
       usu_digi,
       cod_acce,
       sta_proc,
       ind_proc,
       num_lote_sto,
       cod_inte,
       num_lote_inte)
      SELECT cod_pais,
             cam_soli,
             num_lote,
             cod_clie,
             num_docu,
             fec_soli,
             (SELECT COUNT(1)
                FROM int_solic_posic det
               WHERE det.cod_pais = cab.cod_pais
                 AND det.cam_soli = cab.cam_soli
                 AND det.cod_clie = cab.cod_clie
                 AND det.num_lote = cab.num_lote
                 AND nvl(det.num_docu, '0') = nvl(cab.num_docu, '0')
                 AND det.num_lote_sto = cab.num_lote_sto) num_deta,
             SYSDATE,
             pscodigousuario,
             cod_acce,
             sta_proc,
             ind_proc,
             num_lote_sto,
             cod_inte,
             num_lote_inte
        FROM int_solic_cabec cab
       WHERE cab.cod_pais = pscodigopais
         AND cab.num_lote_sto = psnumerolotesto;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_PEDID_BEFOR_CONSO: ' ||
                              ls_sqlerrm);
  END ocr_pr_pedid_befor_conso;
  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Cupon Pago
  Fecha Creacion    : 16/02/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_cupon_pago
  (
    pscodigopais          VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2,
    pscodigotipodocumento VARCHAR2
  ) IS
    CURSOR curinsconcupon IS
      SELECT cupon.cod_pais cod_pais,
             cupon.cod_cia cod_comp,
             cupon.num_docu num_docu,
             cupon.cod_refe_pago cod_refe_pago,
             cupon.cod_refe_pago cod_clie,
             cupon.val_canc imp_valo,
             trunc(SYSDATE) fec_proc,
             cupon.cod_regi_arri cod_regi,
             cupon.cod_zona_arri cod_zona,
             cupon.sta_proc sta_proc,
             cupon.cod_moti_rech cod_moti_rech,
             NULL cod_peri,
             NULL oid_pais,
             NULL oid_terr,
             NULL oid_terr_admi,
             NULL oid_peri,
             NULL oid_zona,
             NULL val_deud,
             '1' sta_cupo,
             NULL ind_rech_sell,
             pscodigotipodocumento docu_cod_tipo_docu,
             psnumlote num_lote,
             seq_docu_sto.nextval sec_nume_docu,
             NULL cod_veri,
             cupon.cod_zona_arri,
             ind_orig
        FROM int_ocr_cupon_pago cupon
       WHERE cupon.cod_pais = pscodigopais;
  
    TYPE cupon_pago_tab_t IS TABLE OF int_solic_conso_cupon_pago%ROWTYPE INDEX BY BINARY_INTEGER;
    cupon_pago_tab cupon_pago_tab_t; -- In-memory table
  
    j BINARY_INTEGER := 0;
  
    v_periodo VARCHAR2(50);
  
    lsfactordescomposicion sto_param_gener_occrr.val_param%TYPE;
    lsfactordivision       sto_param_gener_occrr.val_param%TYPE;
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t; -- In-memory table
  
  BEGIN
  
    lsfactordescomposicion := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                   'STO_FACTOR_DESCOM');
  
    IF (lsfactordescomposicion IS NULL) THEN
      lsfactordescomposicion := '0';
    END IF;
  
    lsfactordivision := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_FACTOR_DIVISION');
    IF (lsfactordivision IS NULL OR lsfactordivision = '0') THEN
      lsfactordivision := '1';
    END IF;
  
    v_periodo := gen_pkg_gener.gen_fn_devuelve_perio_actu(gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais),
                                                          'T',
                                                          gen_pkg_gener.gen_fn_devuelve_id_canal('VD'));
  
    OPEN curinsconcupon;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconcupon BULK COLLECT
        INTO cupon_pago_tab LIMIT w_filas;
      EXIT WHEN cupon_pago_tab.count = 0;
    
      FOR j IN cupon_pago_tab.first .. cupon_pago_tab.last
      LOOP
      
        cupon_pago_tab(j).cod_peri := v_periodo;
      
        cupon_pago_tab(j).cod_clie := substr(cupon_pago_tab(j).cod_refe_pago,
                                             0,
                                             length(cupon_pago_tab(j)
                                                    .cod_refe_pago) -
                                             to_number(lsfactordescomposicion));
      
        cupon_pago_tab(j).cod_veri := substr(cupon_pago_tab(j).cod_refe_pago,
                                             length(cupon_pago_tab(j)
                                                    .cod_refe_pago) -
                                             to_number(lsfactordescomposicion) + 1,
                                             to_number(lsfactordescomposicion));
      
        cupon_pago_tab(j).imp_valo := (cupon_pago_tab(j).imp_valo) /
                                      to_number(lsfactordivision);
      
        /*STO*/
      
        sto_tab(j).cod_pais := cupon_pago_tab(j).cod_pais;
        sto_tab(j).cod_tipo_docu := pscodigotipodocumento;
        sto_tab(j).num_lote := cupon_pago_tab(j).num_lote;
        sto_tab(j).sec_nume_docu := cupon_pago_tab(j).sec_nume_docu;
        sto_tab(j).num_docu := cupon_pago_tab(j).num_docu;
        sto_tab(j).ind_envi := '0';
        sto_tab(j).ind_rech := '0';
        sto_tab(j).fec_digi := SYSDATE;
        sto_tab(j).usu_digi := pscodigousuario;
        sto_tab(j).fec_modi := SYSDATE;
        sto_tab(j).usu_modi := pscodigousuario;
        sto_tab(j).cod_zona := nvl(gen_pkg_gener.gen_fn_clien_datos(cupon_pago_tab(j)
                                                                    .cod_clie,
                                                                    'COD_ZONA'),
                                   cupon_pago_tab(j).cod_zona);
        sto_tab(j).cod_clie := cupon_pago_tab(j).cod_clie;
        sto_tab(j).cod_regi := nvl(gen_pkg_gener.gen_fn_clien_datos(cupon_pago_tab(j)
                                                                    .cod_clie,
                                                                    'COD_REGI'),
                                   cupon_pago_tab(j).cod_regi);
        sto_tab(j).cod_peri := cupon_pago_tab(j).cod_peri;
        --sto_tab(j).COD_MOTI_RECH      :=
        --sto_tab(j).val_obse_rech_defi := cupon_pago_tab(j).val_obse_rech_defi;
        sto_tab(j).ind_rece_ocr := '0';
        sto_tab(j).ind_rece_web := '0';
        sto_tab(j).ind_rece_dd := '0';
        sto_tab(j).ind_rece_digi := '0';
        sto_tab(j).ind_rece_cc := '0';
        sto_tab(j).ind_rece_mens := '0';
        sto_tab(j).ind_elim := '0';
        sto_tab(j).cod_zona_arri := cupon_pago_tab(j).cod_zona_arri;
        sto_tab(j).ind_rece_onli := '0';
        sto_tab(j).ind_rece_ivr := '0';
      
        IF cupon_pago_tab(j).ind_orig = 'O' THEN
          sto_tab(j).ind_rece_ocr := '1';
        END IF;
      
      END LOOP;
    
      FORALL j IN cupon_pago_tab.first .. cupon_pago_tab.last
        INSERT INTO int_solic_conso_cupon_pago VALUES cupon_pago_tab (j);
    
      FORALL j IN cupon_pago_tab.first .. cupon_pago_tab.last
        INSERT INTO sto_docum_digit VALUES sto_tab (j);
    
    END LOOP;
    CLOSE curinsconcupon;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_CUPON_PAGO: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_cupon_pago;
  /***************************************************************************
  Descripcion       : Consolida las Solicitudes Servicio Postventas
  Fecha Creacion    : 23/02/2009
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_post_venta
  (
    pscodigopais      VARCHAR2,
    pscodigousuario   VARCHAR2,
    psnumlote         VARCHAR2,
    pscodigodocumento VARCHAR2
  ) IS
  
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
  BEGIN
  
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigodocumento);
  
    int_pkg_occrr.ocr_pr_conso_post_venta_cabec(pscodigopais,
                                                pscodigodocumento,
                                                pscodigousuario,
                                                psnumlote);
  
    int_pkg_occrr.ocr_pr_conso_post_venta_detal(pscodigopais,
                                                lscodigodocumentodetalle,
                                                pscodigousuario,
                                                psnumlote);
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_POST_VENTA: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_post_venta;
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion Solicitud de Credito Corporativa
  Fecha Creacion    : 06/04/2009
  Autor             : Dennys Oliva iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_socre_corpo
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
    
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9 --anulado = 9; activo =1
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codpais IS TABLE OF int_ocr_solic_credi.cod_pais %TYPE;
    TYPE t_codcomp IS TABLE OF int_ocr_solic_credi.cod_comp %TYPE;
    TYPE t_codclie IS TABLE OF int_ocr_solic_credi.cod_clie %TYPE;
    TYPE t_numdocu IS TABLE OF int_ocr_solic_credi.num_docu %TYPE;
    TYPE t_fecproc IS TABLE OF int_ocr_solic_credi.fec_proc %TYPE;
    TYPE t_uniadmi IS TABLE OF int_ocr_solic_credi.uni_admi %TYPE;
    TYPE t_tipingr IS TABLE OF int_ocr_solic_credi.tip_ingr %TYPE;
    TYPE t_codperi IS TABLE OF int_ocr_solic_credi.cod_peri %TYPE;
    TYPE t_codclierete IS TABLE OF int_ocr_solic_credi.cod_clie_rete %TYPE;
    TYPE t_codprem IS TABLE OF int_ocr_solic_credi.cod_prem %TYPE;
    TYPE t_valape1 IS TABLE OF int_ocr_solic_credi.val_ape1 %TYPE;
    TYPE t_valape2 IS TABLE OF int_ocr_solic_credi.val_ape2 %TYPE;
    TYPE t_valnom1 IS TABLE OF int_ocr_solic_credi.val_nom1 %TYPE;
    TYPE t_valnom2 IS TABLE OF int_ocr_solic_credi.val_nom2 %TYPE;
    TYPE t_fecnaci IS TABLE OF int_ocr_solic_credi.fec_naci %TYPE;
    TYPE t_tipdocu IS TABLE OF int_ocr_solic_credi.tip_docu %TYPE;
    TYPE t_numdocuiden IS TABLE OF int_ocr_solic_credi.num_docu_iden %TYPE;
    TYPE t_numruc IS TABLE OF int_ocr_solic_credi.num_ruc %TYPE;
    TYPE t_indestacivi IS TABLE OF int_ocr_solic_credi.ind_esta_civi %TYPE;
    TYPE t_indniveeduc IS TABLE OF int_ocr_solic_credi.ind_nive_educ %TYPE;
    TYPE t_valdireclie IS TABLE OF int_ocr_solic_credi.val_dire_clie %TYPE;
    TYPE t_valbarrclie IS TABLE OF int_ocr_solic_credi.val_barr_clie %TYPE;
    TYPE t_valdeleclie IS TABLE OF int_ocr_solic_credi.val_dele_clie %TYPE; --Delegacion cliente
    TYPE t_valciudclie IS TABLE OF int_ocr_solic_credi.val_ciud_clie %TYPE;
    TYPE t_valdepaclie IS TABLE OF int_ocr_solic_credi.val_depa_clie %TYPE;
    TYPE t_valcodipostclie IS TABLE OF int_ocr_solic_credi.val_codi_post_clie %TYPE; --Codigo postal cliente
    TYPE t_valtelfclie IS TABLE OF int_ocr_solic_credi.val_telf_clie %TYPE;
    TYPE t_valceluclie IS TABLE OF int_ocr_solic_credi.val_celu_clie %TYPE;
    TYPE t_valtelf_trab IS TABLE OF int_ocr_solic_credi.val_telf_trab %TYPE;
    TYPE t_indventdire IS TABLE OF int_ocr_solic_credi.ind_vent_dire %TYPE;
    TYPE t_valmailclie IS TABLE OF int_ocr_solic_credi.val_mail_clie %TYPE;
    TYPE t_valdireentrclie IS TABLE OF int_ocr_solic_credi.val_dire_entre_clie %TYPE; --Direccion de entrega cliente
    TYPE t_valbarrentrclie IS TABLE OF int_ocr_solic_credi.val_barr_entre_clie %TYPE; --Barrio de entrega cliente
    TYPE t_valdeleentrclie IS TABLE OF int_ocr_solic_credi.val_dele_entre_clie %TYPE; --Delegacion de entrega cliente
    TYPE t_valdepaentrclie IS TABLE OF int_ocr_solic_credi.val_depa_entre_clie %TYPE; --Departamento de entrega cliente
    TYPE t_valteleentrclie IS TABLE OF int_ocr_solic_credi.val_tele_entre_clie %TYPE; --Telefono de entrega cliente
    TYPE t_valceluentrclie IS TABLE OF int_ocr_solic_credi.val_celu_entre_clie %TYPE; --Celular de entrega cliente
    TYPE t_valcodipostentrclie IS TABLE OF int_ocr_solic_credi.val_codi_post_entre_clie %TYPE; --Codigo postal de entrega cliente
    TYPE t_valape1refefamiclie IS TABLE OF int_ocr_solic_credi.val_ape1_refe_fami_clie %TYPE; --Apellido 1 de referencia familiar cliente
    TYPE t_valnom1refefamiclie IS TABLE OF int_ocr_solic_credi.val_nom1_refe_fami_clie %TYPE; --Nombre 1 de referencia familiar cliente
    TYPE t_valdirerefefamiclie IS TABLE OF int_ocr_solic_credi.val_dire_refe_fami_clie %TYPE; --Direccion de referencia familiar cliente
    TYPE t_valbarrrefefamiclie IS TABLE OF int_ocr_solic_credi.val_barr_refe_fami_clie %TYPE; --Barrio de referencia familiar cliente
    TYPE t_valdelerefefamiclie IS TABLE OF int_ocr_solic_credi.val_dele_refe_fami_clie %TYPE; --Delegacion de referencia familiar cliente
    TYPE t_valciudrefefamiclie IS TABLE OF int_ocr_solic_credi.val_ciud_refe_fami_clie %TYPE; --Ciudad de referencia familiar cliente
    TYPE t_valdeparefefamiclie IS TABLE OF int_ocr_solic_credi.val_depa_refe_fami_clie %TYPE; --Departamento de referencia familiar cliente
    TYPE t_valtelerefefamiclie IS TABLE OF int_ocr_solic_credi.val_tele_refe_fami_clie %TYPE; --Telefono de referencia familiar cliente
    TYPE t_valcelurefefamiclie IS TABLE OF int_ocr_solic_credi.val_celu_refe_fami_clie %TYPE; --Celular de referencia familiar cliente
    TYPE t_valtipovincrefefamiclie IS TABLE OF int_ocr_solic_credi.val_tipo_vinc_refe_fami_clie %TYPE; --Tipo vinculo de referencia familiar cliente
    TYPE t_valape1refenofamiclie IS TABLE OF int_ocr_solic_credi.val_ape1_refe_nofa_clie %TYPE; --Apellido 1 de referencia familiar cliente
    TYPE t_valnom1refenofamiclie IS TABLE OF int_ocr_solic_credi.val_nom1_refe_nofa_clie %TYPE; --Nombre 1 de referencia no familiar cliente
    TYPE t_valtelerefenofamiclie IS TABLE OF int_ocr_solic_credi.val_tele_refe_nofa_clie %TYPE; --Telefono de referencia no familiar cliente
    TYPE t_valcelurefenofamiclie IS TABLE OF int_ocr_solic_credi.val_celu_refe_nofa_clie %TYPE; --Celular de referencia no familiar cliente
    TYPE t_valtipovincrefenofamiclie IS TABLE OF int_ocr_solic_credi.val_tipo_vinc_refe_nofa_clie %TYPE; --Tipo vinculo de referencia no familiar cliente
    TYPE t_tipdocufiad IS TABLE OF int_ocr_solic_credi.tip_docu_fiad %TYPE;
    TYPE t_coddocuidfi IS TABLE OF int_ocr_solic_credi.cod_docu_idfi %TYPE;
    TYPE t_valape1fiad IS TABLE OF int_ocr_solic_credi.val_ape1_fiad %TYPE;
    TYPE t_valape2fiad IS TABLE OF int_ocr_solic_credi.val_ape2_fiad %TYPE;
    TYPE t_valnom1fiad IS TABLE OF int_ocr_solic_credi.val_nom1_fiad %TYPE;
    TYPE t_valnom2fiad IS TABLE OF int_ocr_solic_credi.val_nom2_fiad %TYPE;
    TYPE t_valdirefiad IS TABLE OF int_ocr_solic_credi.val_dire_fiad %TYPE;
    TYPE t_valbarrfiad IS TABLE OF int_ocr_solic_credi.val_barr_fiad %TYPE;
    TYPE t_valciudfiad IS TABLE OF int_ocr_solic_credi.val_ciud_fiad %TYPE;
    TYPE t_valdepafiad IS TABLE OF int_ocr_solic_credi.val_depa_fiad %TYPE;
    TYPE t_valteflfiad IS TABLE OF int_ocr_solic_credi.val_tefl_fiad %TYPE;
    TYPE t_valcelufiad IS TABLE OF int_ocr_solic_credi.val_celu_fiad %TYPE;
    TYPE t_valtelftrfi IS TABLE OF int_ocr_solic_credi.val_telf_trfi %TYPE;
    TYPE t_valnombemprfiad IS TABLE OF int_ocr_solic_credi.val_nomb_empr_fiad %TYPE; --Nombre empresa fiador
    TYPE t_valdireemprfiad IS TABLE OF int_ocr_solic_credi.val_dire_empr_fiad %TYPE; --Direccion empresa fiador
    TYPE t_valcargfiad IS TABLE OF int_ocr_solic_credi.val_carg_fiad %TYPE; --Cargo fiador
    TYPE t_valtipovincfiad IS TABLE OF int_ocr_solic_credi.val_tipo_vinc_fiad %TYPE; --Tipo vinculo fiador
    TYPE t_valregiarri IS TABLE OF int_ocr_solic_credi.val_regi_arri %TYPE;
    TYPE t_valzonaarri IS TABLE OF int_ocr_solic_credi.val_zona_arri %TYPE;
    TYPE t_indstatproc IS TABLE OF int_ocr_solic_credi.ind_stat_proc %TYPE;
    TYPE t_indmotirech IS TABLE OF int_ocr_solic_credi.ind_moti_rech %TYPE;
    TYPE t_tipviaclie IS TABLE OF int_ocr_solic_credi.tip_via_clie %TYPE;
    TYPE t_valnombvicl IS TABLE OF int_ocr_solic_credi.val_nomb_vicl %TYPE;
    TYPE t_numdireclie IS TABLE OF int_ocr_solic_credi.num_dire_clie %TYPE;
    TYPE t_coddepaclie IS TABLE OF int_ocr_solic_credi.cod_depa_clie %TYPE;
    TYPE t_codprovclie IS TABLE OF int_ocr_solic_credi.cod_prov_clie %TYPE;
    TYPE t_coddistclie IS TABLE OF int_ocr_solic_credi.cod_dist_clie %TYPE;
    TYPE t_codsectclie IS TABLE OF int_ocr_solic_credi.cod_sect_clie %TYPE;
    TYPE t_tipviafiad IS TABLE OF int_ocr_solic_credi.tip_via_fiad %TYPE;
    TYPE t_valnombvifi IS TABLE OF int_ocr_solic_credi.val_nomb_vifi %TYPE;
    TYPE t_numdirefiad IS TABLE OF int_ocr_solic_credi.num_dire_fiad %TYPE;
    TYPE t_coddepafiad IS TABLE OF int_ocr_solic_credi.cod_depa_fiad %TYPE;
    TYPE t_codprovfiad IS TABLE OF int_ocr_solic_credi.cod_prov_fiad %TYPE;
    TYPE t_coddistfiad IS TABLE OF int_ocr_solic_credi.cod_dist_fiad %TYPE;
    TYPE t_codsectfiad IS TABLE OF int_ocr_solic_credi.cod_sect_fiad %TYPE;
    TYPE t_valindirequfact IS TABLE OF int_ocr_solic_credi.ind_requ_fact %TYPE; -- Indicador requiere factura
    TYPE t_valdirecontr IS TABLE OF int_ocr_solic_credi.val_dire_cont %TYPE; -- Direccion contribuyente
    TYPE t_valbarrcontr IS TABLE OF int_ocr_solic_credi.val_barr_cont %TYPE; -- Barrio contribuyente
    TYPE t_valdelecontr IS TABLE OF int_ocr_solic_credi.val_dele_cont %TYPE; -- Delegacion contribuyente
    TYPE t_valciudcontr IS TABLE OF int_ocr_solic_credi.val_ciud_cont %TYPE; -- Ciudad contribuyente
    TYPE t_valdepacontr IS TABLE OF int_ocr_solic_credi.val_depa_cont %TYPE; -- Departamento contribuyente
    TYPE t_valcodipostcontr IS TABLE OF int_ocr_solic_credi.val_codi_post_cont %TYPE; -- Codigo postal contribuyente
    TYPE t_valnumecontr IS TABLE OF int_ocr_solic_credi.val_nume_cont %TYPE; -- Numero contribuyente
  
    TYPE t_tipmeta IS TABLE OF int_ocr_solic_credi.tip_meta %TYPE;
    TYPE t_valmntometa IS TABLE OF int_ocr_solic_credi.val_mnto_meta %TYPE;
    TYPE t_desmeta IS TABLE OF int_ocr_solic_credi.des_meta %TYPE;
    TYPE t_indvendmar1 IS TABLE OF int_ocr_solic_credi.ind_vend_mar1 %TYPE;
    TYPE t_indvendmar2 IS TABLE OF int_ocr_solic_credi.ind_vend_mar2 %TYPE;
    TYPE t_indvendmar3 IS TABLE OF int_ocr_solic_credi.ind_vend_mar3 %TYPE;
    TYPE t_indvendmar4 IS TABLE OF int_ocr_solic_credi.ind_vend_mar4 %TYPE;
    TYPE t_indvendmar5 IS TABLE OF int_ocr_solic_credi.ind_vend_mar5 %TYPE;
    TYPE t_indvendmar6 IS TABLE OF int_ocr_solic_credi.ind_vend_mar6 %TYPE;
    TYPE t_indvendmar7 IS TABLE OF int_ocr_solic_credi.ind_vend_mar7 %TYPE;
    TYPE t_codmarcvema IS TABLE OF int_ocr_solic_credi.cod_marc_vema %TYPE;
  
    TYPE t_copiaimpresaboleta IS TABLE OF int_ocr_solic_credi.ind_requ_impr_bole %TYPE; -- indicador si requiere copia impresa de boleta
    TYPE t_liderrecomienda IS TABLE OF int_ocr_solic_credi.cod_lide_reco %TYPE; -- lider que recomienda
    TYPE t_cod_sexo       IS TABLE OF int_ocr_solic_credi.cod_sexo%TYPE;     
    TYPE t_cod_naci       IS TABLE OF int_ocr_solic_credi.cod_naci%TYPE;    
    TYPE t_cod_tipo_pers  IS TABLE OF int_ocr_solic_credi.cod_tipo_pers%TYPE;
    TYPE t_cod_orig_ingr  IS TABLE OF int_ocr_solic_credi.cod_orig_ingr%TYPE;
    TYPE t_cod_terr_corr  IS TABLE OF int_ocr_solic_credi.cod_terr_corr%TYPE;
    TYPE t_ind_dird_dire  IS TABLE OF int_ocr_solic_credi.ind_dird_dire%TYPE;
    TYPE t_dom_cant       IS TABLE OF int_ocr_solic_credi.dom_cant%TYPE;
    TYPE t_dom_parr       IS TABLE OF int_ocr_solic_credi.dom_parr%TYPE;
    TYPE t_dom_manz       IS TABLE OF int_ocr_solic_credi.dom_manz%TYPE;
    TYPE t_dom_etap       IS TABLE OF int_ocr_solic_credi.dom_etap%TYPE;
    TYPE t_dom_call_prin  IS TABLE OF int_ocr_solic_credi.dom_call_prin%TYPE;
    TYPE t_dom_call_secu  IS TABLE OF int_ocr_solic_credi.dom_call_secu%TYPE;
    TYPE t_dom_num        IS TABLE OF int_ocr_solic_credi.dom_num%TYPE;
    TYPE t_dom_refe       IS TABLE OF int_ocr_solic_credi.dom_refe%TYPE;
    TYPE t_ent_manz       IS TABLE OF int_ocr_solic_credi.ent_manz%TYPE;
    TYPE t_ent_etap       IS TABLE OF int_ocr_solic_credi.ent_etap%TYPE;
    TYPE t_ent_call_prin  IS TABLE OF int_ocr_solic_credi.ent_call_prin%TYPE;
    TYPE t_ent_call_secu  IS TABLE OF int_ocr_solic_credi.ent_call_secu%TYPE;      
    TYPE t_ent_num        IS TABLE OF int_ocr_solic_credi.ent_num%TYPE;
    TYPE t_ent_refe       IS TABLE OF int_ocr_solic_credi.ent_refe%TYPE;  
     
  
    ----------------------
    v_codpais         t_codpais := t_codpais();
    v_codcomp         t_codcomp := t_codcomp();
    v_codclie         t_codclie := t_codclie();
    v_numdocu         t_numdocu := t_numdocu();
    v_fecproc         t_fecproc := t_fecproc();
    v_uniadmi         t_uniadmi := t_uniadmi();
    v_tipingr         t_tipingr := t_tipingr();
    v_codperi         t_codperi := t_codperi();
    v_codclierete     t_codclierete := t_codclierete();
    v_codprem         t_codprem := t_codprem();
    v_valape1         t_valape1 := t_valape1();
    v_valape2         t_valape2 := t_valape2();
    v_valnom1         t_valnom1 := t_valnom1();
    v_valnom2         t_valnom2 := t_valnom2();
    v_fecnaci         t_fecnaci := t_fecnaci();
    v_tipdocu         t_tipdocu := t_tipdocu();
    v_numdocuiden     t_numdocuiden := t_numdocuiden();
    v_numruc          t_numruc := t_numruc();
    v_indestacivi     t_indestacivi := t_indestacivi();
    v_indniveeduc     t_indniveeduc := t_indniveeduc();
    v_valdireclie     t_valdireclie := t_valdireclie();
    v_valbarrclie     t_valbarrclie := t_valbarrclie();
    v_valdeleclie     t_valdeleclie := t_valdeleclie(); --Delegacion cliente
    v_valciudclie     t_valciudclie := t_valciudclie();
    v_valdepaclie     t_valdepaclie := t_valdepaclie();
    v_valcodipostclie t_valcodipostclie := t_valcodipostclie(); --Codigo postal cliente
    v_valtelfclie     t_valtelfclie := t_valtelfclie();
    v_valceluclie     t_valceluclie := t_valceluclie();
    v_valtelf_trab    t_valtelf_trab := t_valtelf_trab();
    v_indventdire     t_indventdire := t_indventdire();
    v_valmailclie     t_valmailclie := t_valmailclie();
  
    v_valdireentrclie           t_valdireentrclie := t_valdireentrclie(); --Direccion de entrega cliente
    v_valbarrentrclie           t_valbarrentrclie := t_valbarrentrclie(); --Barrio de entrega cliente
    v_valdeleentrclie           t_valdeleentrclie := t_valdeleentrclie(); --Delegacion de entrega cliente
    v_valdepaentrclie           t_valdepaentrclie := t_valdepaentrclie(); --Departamento de entrega cliente
    v_valteleentrclie           t_valteleentrclie := t_valteleentrclie(); --Telefono de entrega cliente
    v_valceluentrclie           t_valceluentrclie := t_valceluentrclie(); --Celular de entrega cliente
    v_valcodipostentrclie       t_valcodipostentrclie := t_valcodipostentrclie(); --Codigo postal de entrega cliente
    v_valape1refefamiclie       t_valape1refefamiclie := t_valape1refefamiclie(); --Apellido 1 de referencia familiar cliente
    v_valnom1refefamiclie       t_valnom1refefamiclie := t_valnom1refefamiclie(); --Nombre 1 de referencia familiar cliente
    v_valdirerefefamiclie       t_valdirerefefamiclie := t_valdirerefefamiclie(); --Direccion de referencia familiar cliente
    v_valbarrrefefamiclie       t_valbarrrefefamiclie := t_valbarrrefefamiclie(); --Barrio de referencia familiar cliente
    v_valdelerefefamiclie       t_valdelerefefamiclie := t_valdelerefefamiclie(); --Delegacion de referencia familiar cliente
    v_valciudrefefamiclie       t_valciudrefefamiclie := t_valciudrefefamiclie(); --Ciudad de referencia familiar cliente
    v_valdeparefefamiclie       t_valdeparefefamiclie := t_valdeparefefamiclie(); --Departamento de referencia familiar cliente
    v_valtelerefefamiclie       t_valtelerefefamiclie := t_valtelerefefamiclie(); --Telefono de referencia familiar cliente
    v_valcelurefefamiclie       t_valcelurefefamiclie := t_valcelurefefamiclie(); --Celular de referencia familiar cliente
    v_valtipovincrefefamiclie   t_valtipovincrefefamiclie := t_valtipovincrefefamiclie(); --Tipo vinculo de referencia familiar cliente
    v_valape1refenofamiclie     t_valape1refenofamiclie := t_valape1refenofamiclie(); --Apellido 1 de referencia familiar cliente
    v_valnom1refenofamiclie     t_valnom1refenofamiclie := t_valnom1refenofamiclie(); --Nombre 1 de referencia no familiar cliente
    v_valtelerefenofamiclie     t_valtelerefenofamiclie := t_valtelerefenofamiclie(); --Telefono de referencia no familiar cliente
    v_valcelurefenofamiclie     t_valcelurefenofamiclie := t_valcelurefenofamiclie(); --Celular de referencia no familiar cliente
    v_valtipovincrefenofamiclie t_valtipovincrefenofamiclie := t_valtipovincrefenofamiclie(); --Tipo vinculo de referencia no familiar cliente
  
    v_tipdocufiad t_tipdocufiad := t_tipdocufiad();
    v_coddocuidfi t_coddocuidfi := t_coddocuidfi();
    v_valape1fiad t_valape1fiad := t_valape1fiad();
    v_valape2fiad t_valape2fiad := t_valape2fiad();
    v_valnom1fiad t_valnom1fiad := t_valnom1fiad();
    v_valnom2fiad t_valnom2fiad := t_valnom2fiad();
    v_valdirefiad t_valdirefiad := t_valdirefiad();
    v_valbarrfiad t_valbarrfiad := t_valbarrfiad();
    v_valciudfiad t_valciudfiad := t_valciudfiad();
    v_valdepafiad t_valdepafiad := t_valdepafiad();
    v_valteflfiad t_valteflfiad := t_valteflfiad();
    v_valcelufiad t_valcelufiad := t_valcelufiad();
    v_valtelftrfi t_valtelftrfi := t_valtelftrfi();
  
    v_valnombemprfiad t_valnombemprfiad := t_valnombemprfiad(); --Nombre empresa fiador
    v_valdireemprfiad t_valdireemprfiad := t_valdireemprfiad(); --Direccion empresa fiador
    v_valcargfiad     t_valcargfiad := t_valcargfiad(); --Cargo fiador
    v_valtipovincfiad t_valtipovincfiad := t_valtipovincfiad(); --Tipo vinculo fiador
  
    v_valregiarri t_valregiarri := t_valregiarri();
    v_valzonaarri t_valzonaarri := t_valzonaarri();
    v_indstatproc t_indstatproc := t_indstatproc();
    v_indmotirech t_indmotirech := t_indmotirech();
    v_tipviaclie  t_tipviaclie := t_tipviaclie();
    v_valnombvicl t_valnombvicl := t_valnombvicl();
    v_numdireclie t_numdireclie := t_numdireclie();
    v_coddepaclie t_coddepaclie := t_coddepaclie();
    v_codprovclie t_codprovclie := t_codprovclie();
    v_coddistclie t_coddistclie := t_coddistclie();
    v_codsectclie t_codsectclie := t_codsectclie();
    v_tipviafiad  t_tipviafiad := t_tipviafiad();
    v_valnombvifi t_valnombvifi := t_valnombvifi();
    v_numdirefiad t_numdirefiad := t_numdirefiad();
    v_coddepafiad t_coddepafiad := t_coddepafiad();
    v_codprovfiad t_codprovfiad := t_codprovfiad();
    v_coddistfiad t_coddistfiad := t_coddistfiad();
    v_codsectfiad t_codsectfiad := t_codsectfiad();
  
    v_valindirequfact  t_valindirequfact := t_valindirequfact(); -- Indicador requiere factura
    v_valdirecontr     t_valdirecontr := t_valdirecontr(); -- Direccion contribuyente
    v_valbarrcontr     t_valbarrcontr := t_valbarrcontr(); -- Barrio contribuyente
    v_valdelecontr     t_valdelecontr := t_valdelecontr(); -- Delegacion contribuyente
    v_valciudcontr     t_valciudcontr := t_valciudcontr(); -- Ciudad contribuyente
    v_valdepacontr     t_valdepacontr := t_valdepacontr(); -- Departamento contribuyente
    v_valcodipostcontr t_valcodipostcontr := t_valcodipostcontr(); -- Codigo postal contribuyente
    v_valnumecontr     t_valnumecontr := t_valnumecontr(); -- Numero contribuyente
  
    v_tipmeta     t_tipmeta := t_tipmeta();
    v_valmntometa t_valmntometa := t_valmntometa();
    v_desmeta     t_desmeta := t_desmeta();
    v_indvendmar1 t_indvendmar1 := t_indvendmar1();
    v_indvendmar2 t_indvendmar2 := t_indvendmar2();
    v_indvendmar3 t_indvendmar3 := t_indvendmar3();
    v_indvendmar4 t_indvendmar4 := t_indvendmar4();
    v_indvendmar5 t_indvendmar5 := t_indvendmar5();
    v_indvendmar6 t_indvendmar6 := t_indvendmar6();
    v_indvendmar7 t_indvendmar7 := t_indvendmar7();
    v_codmarcvema t_codmarcvema := t_codmarcvema();
  
    v_copiaimpresaboleta t_copiaimpresaboleta := t_copiaimpresaboleta(); -- indicador si requiere copia impresa boleta
    v_liderrecomienda    t_liderrecomienda := t_liderrecomienda(); -- lider que recomienda
    v_cod_sexo           t_cod_sexo        := t_cod_sexo();
    v_cod_naci           t_cod_naci        := t_cod_naci();       
    v_cod_tipo_pers      t_cod_tipo_pers   := t_cod_tipo_pers();  
    v_cod_orig_ingr      t_cod_orig_ingr   := t_cod_orig_ingr();  
    v_cod_terr_corr      t_cod_terr_corr   := t_cod_terr_corr();      
    v_ind_dird_dire      t_ind_dird_dire   := t_ind_dird_dire();  
    v_dom_cant           t_dom_cant        := t_dom_cant();       
    v_dom_parr           t_dom_parr        := t_dom_parr();       
    v_dom_manz           t_dom_manz        := t_dom_manz();       
    v_dom_etap           t_dom_etap        := t_dom_etap();       
    v_dom_call_prin      t_dom_call_prin   := t_dom_call_prin();  
    v_dom_call_secu      t_dom_call_secu   := t_dom_call_secu();  
    v_dom_num            t_dom_num         := t_dom_num();        
    v_dom_refe           t_dom_refe        := t_dom_refe();       
    v_ent_manz           t_ent_manz        := t_ent_manz();       
    v_ent_etap           t_ent_etap        := t_ent_etap();       
    v_ent_call_prin      t_ent_call_prin   := t_ent_call_prin();  
    v_ent_call_secu      t_ent_call_secu   := t_ent_call_secu();  
    v_ent_num            t_ent_num         := t_ent_num();        
    v_ent_refe           t_ent_refe        := t_ent_refe(); 
  
    ----------------------
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena           VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) ||
                                        chr(20);
    lsreplace          VARCHAR2(100) := 'a        ';
    lsparalongdocuiden sto_param_gener_occrr.val_param%TYPE;
    lnlongdociden      NUMBER := 0;
  
    lstipodocu VARCHAR2(2);
  
    lsperiodoactivo int_ocr_solic_credi.cod_camp_inic%TYPE;
  
    /*lscadena VARCHAR2(4):='<>;''|||char(13)';
    lsreplace VARCHAR2(4):='     ';*/
    -------------------------
  BEGIN
  
    lsparalongdocuiden := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_LONG_DOCU_IDEN');
    lnlongdociden      := to_number(lsparalongdocuiden);
  
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle,
                                                 4000);
  
    /*  SELECT t.cod_tipo_docu
     INTO lscodigotipodocumento
     FROM sto_tipo_docum_digit t
    WHERE t.inte_cod_inte = pscodigointerfaz;*/
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_codpais(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 2) THEN
                  v_codcomp.extend;
                  v_codcomp(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                  v_codpais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codpais(i),
                                                                              v_codcomp(i));
                ELSIF (posicion = 3) THEN
                  v_codclie.extend;
                  v_codclie(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 4) THEN
                  v_numdocu.extend;
                  v_numdocu(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 5) THEN
                  v_fecproc.extend;
                
                  v_fecproc(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                
                ELSIF (posicion = 6) THEN
                  v_uniadmi.extend;
                  v_uniadmi(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 7) THEN
                  v_tipingr.extend;
                  v_tipingr(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 8) THEN
                  v_codperi.extend;
                  v_codperi(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 9) THEN
                  v_codclierete.extend;
                  v_codclierete(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 10) THEN
                  v_codprem.extend;
                  v_codprem(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 11) THEN
                  v_valape1.extend;
                  v_valape1(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 12) THEN
                  v_valape2.extend;
                  v_valape2(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 13) THEN
                  v_valnom1.extend;
                  v_valnom1(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 14) THEN
                  v_valnom2.extend;
                  v_valnom2(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 15) THEN
                  v_fecnaci.extend;
                  v_fecnaci(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 16) THEN
                  v_tipdocu.extend;
                  v_tipdocu(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 17) THEN
                  v_numdocuiden.extend;
                  v_numdocuiden(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 18) THEN
                  v_numruc.extend;
                  v_numruc(i) := TRIM(translate(substr(lslinea,
                                                       inicio,
                                                       longitud),
                                                lscadena,
                                                lsreplace));
                ELSIF (posicion = 19) THEN
                  v_indestacivi.extend;
                  v_indestacivi(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 20) THEN
                  v_indniveeduc.extend;
                  v_indniveeduc(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 21) THEN
                  v_valdireclie.extend;
                  v_valdireclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 22) THEN
                  v_valbarrclie.extend;
                  v_valbarrclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 23) THEN
                  v_valdeleclie.extend;
                  v_valdeleclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                
                ELSIF (posicion = 24) THEN
                  v_valciudclie.extend;
                  v_valciudclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                
                ELSIF (posicion = 25) THEN
                  v_valdepaclie.extend;
                  v_valdepaclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                
                ELSIF (posicion = 26) THEN
                  v_valcodipostclie.extend;
                  v_valcodipostclie(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                
                ELSIF (posicion = 27) THEN
                  v_valtelfclie.extend;
                  v_valtelfclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 28) THEN
                  v_valceluclie.extend;
                  v_valceluclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 29) THEN
                  v_valtelf_trab.extend;
                  v_valtelf_trab(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 30) THEN
                  v_indventdire.extend;
                  v_indventdire(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 31) THEN
                  v_valmailclie.extend;
                  v_valmailclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                  -----
                ELSIF (posicion = 32) THEN
                  v_valdireentrclie.extend;
                  v_valdireentrclie(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 33) THEN
                  v_valbarrentrclie.extend;
                  v_valbarrentrclie(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 34) THEN
                  v_valdeleentrclie.extend;
                  v_valdeleentrclie(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 35) THEN
                  v_valdepaentrclie.extend;
                  v_valdepaentrclie(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 36) THEN
                  v_valteleentrclie.extend;
                  v_valteleentrclie(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 37) THEN
                  v_valceluentrclie.extend;
                  v_valceluentrclie(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 38) THEN
                  v_valcodipostentrclie.extend;
                  v_valcodipostentrclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 39) THEN
                  v_valape1refefamiclie.extend;
                  v_valape1refefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 40) THEN
                  v_valnom1refefamiclie.extend;
                  v_valnom1refefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 41) THEN
                  v_valdirerefefamiclie.extend;
                  v_valdirerefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 42) THEN
                  v_valbarrrefefamiclie.extend;
                  v_valbarrrefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 43) THEN
                  v_valdelerefefamiclie.extend;
                  v_valdelerefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 44) THEN
                  v_valciudrefefamiclie.extend;
                  v_valciudrefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 45) THEN
                  v_valdeparefefamiclie.extend;
                  v_valdeparefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 46) THEN
                  v_valtelerefefamiclie.extend;
                  v_valtelerefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 47) THEN
                  v_valcelurefefamiclie.extend;
                  v_valcelurefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 48) THEN
                  v_valtipovincrefefamiclie.extend;
                  v_valtipovincrefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                        inicio,
                                                                        longitud),
                                                                 lscadena,
                                                                 lsreplace));
                ELSIF (posicion = 49) THEN
                  v_valape1refenofamiclie.extend;
                  v_valape1refenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                ELSIF (posicion = 50) THEN
                  v_valnom1refenofamiclie.extend;
                  v_valnom1refenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                ELSIF (posicion = 51) THEN
                  v_valtelerefenofamiclie.extend;
                  v_valtelerefenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                ELSIF (posicion = 52) THEN
                  v_valcelurefenofamiclie.extend;
                  v_valcelurefenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                ELSIF (posicion = 53) THEN
                  v_valtipovincrefenofamiclie.extend;
                  v_valtipovincrefenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                          inicio,
                                                                          longitud),
                                                                   lscadena,
                                                                   lsreplace));
                  -----
                ELSIF (posicion = 54) THEN
                  v_tipdocufiad.extend;
                  v_tipdocufiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 55) THEN
                  v_coddocuidfi.extend;
                  v_coddocuidfi(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 56) THEN
                  v_valape1fiad.extend;
                  v_valape1fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 57) THEN
                  v_valape2fiad.extend;
                  v_valape2fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 58) THEN
                  v_valnom1fiad.extend;
                  v_valnom1fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 59) THEN
                  v_valnom2fiad.extend;
                  v_valnom2fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 60) THEN
                  v_valdirefiad.extend;
                  v_valdirefiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 61) THEN
                  v_valbarrfiad.extend;
                  v_valbarrfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 62) THEN
                  v_valciudfiad.extend;
                  v_valciudfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 63) THEN
                  v_valdepafiad.extend;
                  v_valdepafiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 64) THEN
                  v_valteflfiad.extend;
                  v_valteflfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 65) THEN
                  v_valcelufiad.extend;
                  v_valcelufiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 66) THEN
                  v_valtelftrfi.extend;
                  v_valtelftrfi(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                  --
                ELSIF (posicion = 67) THEN
                  v_valnombemprfiad.extend;
                  v_valnombemprfiad(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 68) THEN
                  v_valdireemprfiad.extend;
                  v_valdireemprfiad(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 69) THEN
                  v_valcargfiad.extend;
                  v_valcargfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 70) THEN
                  v_valtipovincfiad.extend;
                  v_valtipovincfiad(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                  --
                ELSIF (posicion = 71) THEN
                  v_valregiarri.extend;
                  v_valregiarri(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 72) THEN
                  v_valzonaarri.extend;
                  v_valzonaarri(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 73) THEN
                  v_indstatproc.extend;
                  v_indstatproc(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 74) THEN
                  v_indmotirech.extend;
                  v_indmotirech(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 75) THEN
                  v_tipviaclie.extend;
                  v_tipviaclie(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 76) THEN
                  v_valnombvicl.extend;
                  v_valnombvicl(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 77) THEN
                  v_numdireclie.extend;
                  v_numdireclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 78) THEN
                  v_coddepaclie.extend;
                  v_coddepaclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 79) THEN
                  v_codprovclie.extend;
                  v_codprovclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 80) THEN
                  v_coddistclie.extend;
                  v_coddistclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 81) THEN
                  v_codsectclie.extend;
                  v_codsectclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 82) THEN
                  v_tipviafiad.extend;
                  v_tipviafiad(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 83) THEN
                  v_valnombvifi.extend;
                  v_valnombvifi(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 84) THEN
                  v_numdirefiad.extend;
                  v_numdirefiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 85) THEN
                  v_coddepafiad.extend;
                  v_coddepafiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 86) THEN
                  v_codprovfiad.extend;
                  v_codprovfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 87) THEN
                  v_coddistfiad.extend;
                  v_coddistfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 88) THEN
                  -- posicion que se reutiliza para indicador si requiere boleta PERU
                  -- OCR confirma que el dato origina no estaba en uso y se deja en NULL
                  v_codsectfiad.extend;
                  v_codsectfiad(i) := NULL;
                
                  v_copiaimpresaboleta.extend;
                  v_copiaimpresaboleta(i) := TRIM(translate(substr(lslinea,
                                                                   inicio,
                                                                   longitud),
                                                            lscadena,
                                                            lsreplace));
                  --
                ELSIF (posicion = 89) THEN
                  v_valindirequfact.extend;
                  v_valindirequfact(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 90) THEN
                  v_valdirecontr.extend;
                  v_valdirecontr(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 91) THEN
                  v_valbarrcontr.extend;
                  v_valbarrcontr(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 92) THEN
                  v_valdelecontr.extend;
                  v_valdelecontr(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 93) THEN
                  v_valciudcontr.extend;
                  v_valciudcontr(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 94) THEN
                  v_valdepacontr.extend;
                  v_valdepacontr(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 95) THEN
                  v_valcodipostcontr.extend;
                  v_valcodipostcontr(i) := TRIM(translate(substr(lslinea,
                                                                 inicio,
                                                                 longitud),
                                                          lscadena,
                                                          lsreplace));
                ELSIF (posicion = 96) THEN
                  -- posicion que se reutiliza para el codigo de la lider que recomienda EC
                  -- OCR confirma que el dato origina no estaba en uso y se deja en NULL
                
                  v_valnumecontr.extend;
                  v_valnumecontr(i) := NULL;
                
                  v_liderrecomienda.extend;
                  v_liderrecomienda(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 97) THEN
                  v_tipmeta.extend;
                  v_tipmeta(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 98) THEN
                  v_valmntometa.extend;
                  v_valmntometa(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 99) THEN
                  v_desmeta.extend;
                  v_desmeta(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 100) THEN
                  v_indvendmar1.extend;
                  v_indvendmar1(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 101) THEN
                  v_indvendmar2.extend;
                  v_indvendmar2(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 102) THEN
                  v_indvendmar3.extend;
                  v_indvendmar3(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 103) THEN
                  v_indvendmar4.extend;
                  v_indvendmar4(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 104) THEN
                  v_indvendmar5.extend;
                  v_indvendmar5(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 105) THEN
                  v_indvendmar6.extend;
                  v_indvendmar6(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 106) THEN
                  v_indvendmar7.extend;
                  v_indvendmar7(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 107) THEN
                  v_codmarcvema.extend;
                  v_codmarcvema(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 108) THEN
                  v_cod_terr_corr.extend;
                  v_cod_terr_corr(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 109) THEN
                  v_cod_naci.extend;
                  v_cod_naci(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 110) THEN
                  v_cod_tipo_pers.extend;
                  v_cod_tipo_pers(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 111) THEN
                  v_cod_sexo.extend;
                  v_cod_sexo(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 112) THEN
                  v_cod_orig_ingr.extend;
                  v_cod_orig_ingr(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 113) THEN
                  v_dom_cant.extend;
                  v_dom_cant(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 114) THEN
                  v_dom_parr.extend;
                  v_dom_parr(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 115) THEN
                  v_dom_manz.extend;
                  v_dom_manz(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 116) THEN
                  v_dom_etap.extend;
                  v_dom_etap(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 117) THEN
                  v_dom_call_prin.extend;
                  v_dom_call_prin(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 118) THEN
                  v_dom_call_secu.extend;
                  v_dom_call_secu(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 119) THEN
                  v_dom_num.extend;
                  v_dom_num(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 120) THEN
                  v_dom_refe.extend;
                  v_dom_refe(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 121) THEN
                  v_ind_dird_dire.extend;
                  v_ind_dird_dire(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 122) THEN
                  v_ent_manz.extend;
                  v_ent_manz(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 123) THEN
                  v_ent_etap.extend;
                  v_ent_etap(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 124) THEN
                  v_ent_call_prin.extend;
                  v_ent_call_prin(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 125) THEN
                  v_ent_call_secu.extend;
                  v_ent_call_secu(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 126) THEN
                  v_ent_num.extend;
                  v_ent_num(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 127) THEN
                  v_ent_refe.extend;
                  v_ent_refe(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
                                                                                                          
                
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    lstipodocu := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                       'STO_TIPO_DOCU');
    
    
  
    BEGIN
      SELECT c.cod_peri
        INTO lsperiodoactivo
        FROM bas_ctrl_fact c
       WHERE c.sta_camp = '0'
         AND c.ind_camp_act = '1'
         AND c.cod_pais = pscodigopais;
    EXCEPTION
      WHEN no_data_found THEN
        lsperiodoactivo := NULL;
    END;
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codpais.count
    
      INSERT INTO int_ocr_solic_credi
        (cod_pais,
         cod_comp,
         cod_clie,
         num_docu,
         fec_proc,
         uni_admi,
         tip_ingr,
         cod_peri,
         cod_clie_rete,
         cod_prem,
         val_ape1,
         val_ape2,
         val_nom1,
         val_nom2,
         fec_naci,
         tip_docu,
         num_docu_iden,
         num_ruc,
         ind_esta_civi,
         ind_nive_educ,
         val_dire_clie,
         val_barr_clie,
         val_ciud_clie,
         val_depa_clie,
         val_telf_clie,
         val_celu_clie,
         val_telf_trab,
         ind_vent_dire,
         val_mail_clie,
         tip_docu_fiad,
         cod_docu_idfi,
         val_ape1_fiad,
         val_ape2_fiad,
         val_nom1_fiad,
         val_nom2_fiad,
         val_dire_fiad,
         val_barr_fiad,
         val_ciud_fiad,
         val_depa_fiad,
         val_tefl_fiad,
         val_celu_fiad,
         val_telf_trfi,
         val_regi_arri,
         val_zona_arri,
         ind_stat_proc,
         ind_moti_rech,
         tip_via_clie,
         val_nomb_vicl,
         num_dire_clie,
         cod_depa_clie,
         cod_prov_clie,
         cod_dist_clie,
         cod_sect_clie,
         tip_via_fiad,
         val_nomb_vifi,
         num_dire_fiad,
         cod_depa_fiad,
         cod_prov_fiad,
         cod_dist_fiad,
         cod_sect_fiad,
         val_dele_clie,
         val_codi_post_clie,
         val_dire_entre_clie,
         val_barr_entre_clie,
         val_dele_entre_clie,
         val_depa_entre_clie,
         val_tele_entre_clie,
         val_celu_entre_clie,
         val_codi_post_entre_clie,
         val_ape1_refe_fami_clie,
         val_nom1_refe_fami_clie,
         val_dire_refe_fami_clie,
         val_barr_refe_fami_clie,
         val_dele_refe_fami_clie,
         val_ciud_refe_fami_clie,
         val_depa_refe_fami_clie,
         val_tele_refe_fami_clie,
         val_celu_refe_fami_clie,
         val_tipo_vinc_refe_fami_clie,
         val_ape1_refe_nofa_clie,
         val_nom1_refe_nofa_clie,
         val_tele_refe_nofa_clie,
         val_celu_refe_nofa_clie,
         val_tipo_vinc_refe_nofa_clie,
         val_nomb_empr_fiad,
         val_dire_empr_fiad,
         val_carg_fiad,
         val_tipo_vinc_fiad,
         ind_requ_fact,
         val_dire_cont,
         val_barr_cont,
         val_dele_cont,
         val_ciud_cont,
         val_depa_cont,
         val_codi_post_cont,
         val_nume_cont,
         ind_orig,
         tip_meta,
         val_mnto_meta,
         cod_camp_inic,
         des_meta,
         ind_vend_mar1,
         ind_vend_mar2,
         ind_vend_mar3,
         ind_vend_mar4,
         ind_vend_mar5,
         ind_vend_mar6,
         ind_vend_mar7,
         cod_marc_vema,
         ind_requ_impr_bole,
         cod_lide_reco,
         cod_terr_corr ,
         cod_naci      ,
         cod_tipo_pers ,
         cod_sexo      ,
         cod_orig_ingr ,
         dom_cant      ,
         dom_parr      ,
         dom_manz      ,
         dom_etap      ,
         dom_call_prin ,
         dom_call_secu ,
         dom_num       ,
         dom_refe      ,
         ind_dird_dire ,
         ent_manz      ,
         ent_etap      ,
         ent_call_prin ,
         ent_call_secu ,
         ent_num       ,
         ent_refe          
         )
      VALUES
        (v_codpais(i),
         v_codcomp(i),
         v_codclie(i),
         v_numdocu(i),
         v_fecproc(i),
         v_uniadmi(i),
         v_tipingr(i),
         v_codperi(i),
         v_codclierete(i),
         v_codprem(i),
         v_valape1(i),
         v_valape2(i),
         v_valnom1(i),
         v_valnom2(i),
         v_fecnaci(i),
         nvl(TRIM(v_tipdocu(i)), lstipodocu),
         (SELECT CASE
                   WHEN length(v_numdocuiden(i)) > lnlongdociden THEN
                    substr(v_numdocuiden(i), -lnlongdociden, lnlongdociden)
                   ELSE
                    v_numdocuiden(i)
                 END
            FROM dual),
         v_numruc(i),
         nvl(trim(v_indestacivi(i)),sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais, 'STO_DEF_EST_CIV_SCC')),
         v_indniveeduc(i),
         v_valdireclie(i),
         v_valbarrclie(i),
         v_valciudclie(i),
         v_valdepaclie(i),
         v_valtelfclie(i),
         v_valceluclie(i),
         v_valtelf_trab(i),
         v_indventdire(i),
         v_valmailclie(i),
         nvl(TRIM(v_tipdocufiad(i)), lstipodocu),
         v_coddocuidfi(i),
         v_valape1fiad(i),
         v_valape2fiad(i),
         v_valnom1fiad(i),
         v_valnom2fiad(i),
         v_valdirefiad(i),
         v_valbarrfiad(i),
         v_valciudfiad(i),
         v_valdepafiad(i),
         v_valteflfiad(i),
         v_valcelufiad(i),
         v_valtelftrfi(i),
         v_valregiarri(i),
         v_valzonaarri(i),
         v_indstatproc(i),
         v_indmotirech(i),
         v_tipviaclie(i),
         v_valnombvicl(i),
         v_numdireclie(i),
         v_coddepaclie(i),
         v_codprovclie(i),
         v_coddistclie(i),
         v_codsectclie(i),
         v_tipviafiad(i),
         v_valnombvifi(i),
         v_numdirefiad(i),
         v_coddepafiad(i),
         v_codprovfiad(i),
         v_coddistfiad(i),
         v_codsectfiad(i),
         v_valdeleclie(i),
         v_valcodipostclie(i),
         v_valdireentrclie(i),
         v_valbarrentrclie(i),
         v_valdeleentrclie(i),
         v_valdepaentrclie(i),
         v_valteleentrclie(i),
         v_valceluentrclie(i),
         v_valcodipostentrclie(i),
         v_valape1refefamiclie(i),
         v_valnom1refefamiclie(i),
         v_valdirerefefamiclie(i),
         v_valbarrrefefamiclie(i),
         v_valdelerefefamiclie(i),
         v_valciudrefefamiclie(i),
         v_valdeparefefamiclie(i),
         v_valtelerefefamiclie(i),
         v_valcelurefefamiclie(i),
         v_valtipovincrefefamiclie(i),
         v_valape1refenofamiclie(i),
         v_valnom1refenofamiclie(i),
         v_valtelerefenofamiclie(i),
         v_valcelurefenofamiclie(i),
         v_valtipovincrefenofamiclie(i),
         v_valnombemprfiad(i),
         v_valdireemprfiad(i),
         v_valcargfiad(i),
         v_valtipovincfiad(i),
         v_valindirequfact(i),
         v_valdirecontr(i),
         v_valbarrcontr(i),
         v_valdelecontr(i),
         v_valciudcontr(i),
         v_valdepacontr(i),
         v_valcodipostcontr(i),
         v_valnumecontr(i),
         psindicadororigen,
         v_tipmeta(i),
         v_valmntometa(i),
         lsperiodoactivo,
         v_desmeta(i),
         v_indvendmar1(i),
         v_indvendmar2(i),
         v_indvendmar3(i),
         v_indvendmar4(i),
         v_indvendmar5(i),
         v_indvendmar6(i),
         v_indvendmar7(i),
         v_codmarcvema(i),
         v_copiaimpresaboleta(i),
         v_liderrecomienda(i),
         nvl(trim(v_cod_terr_corr(i)),sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais, 'STO_COD_TERR_SCC')),
         nvl(trim(v_cod_naci(i)),sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais, 'STO_DEF_NACIO_SCC')),      
         nvl(trim(v_cod_tipo_pers(i)),sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais, 'STO_DEF_TIP_PER_SCC')),
         nvl(trim(v_cod_sexo(i)),sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais, 'STO_DEF_SEX_SCC')),
         nvl(trim(v_cod_orig_ingr(i)),sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais, 'STO_DEF_ORI_ING_SCC')),
         v_dom_cant(i),
         v_dom_parr(i),
         v_dom_manz(i),
         v_dom_etap(i),
         v_dom_call_prin(i),
         v_dom_call_secu(i),
         v_dom_num(i),
         v_dom_refe(i),
         nvl(trim(v_ind_dird_dire(i)),sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais, 'STO_DDOM_ENT_SCC')),
         v_ent_manz(i),
         v_ent_etap(i),
         v_ent_call_prin(i),
         v_ent_call_secu(i),
         v_ent_num(i),
         v_ent_refe(i)      
         );
         
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_recep_solic_credi_corpo: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_socre_corpo;
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion Solicitud de Credito Web
  Fecha Creacion    : 07/04/2009
  Autor             : Dennys Oliva iriarte
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_socre_web
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9 --anulado = 9; activo =1
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codpais IS TABLE OF int_ocr_solic_credi.cod_pais %TYPE;
    TYPE t_codclie IS TABLE OF int_ocr_solic_credi.cod_clie %TYPE;
    TYPE t_uniadmi IS TABLE OF int_ocr_solic_credi.uni_admi %TYPE;
    TYPE t_tipingr IS TABLE OF int_ocr_solic_credi.tip_ingr %TYPE;
    TYPE t_codclierete IS TABLE OF int_ocr_solic_credi.cod_clie_rete %TYPE;
    TYPE t_valape1 IS TABLE OF int_ocr_solic_credi.val_ape1 %TYPE;
    TYPE t_valape2 IS TABLE OF int_ocr_solic_credi.val_ape2 %TYPE;
    TYPE t_valnom1 IS TABLE OF int_ocr_solic_credi.val_nom1 %TYPE;
    TYPE t_valnom2 IS TABLE OF int_ocr_solic_credi.val_nom2 %TYPE;
    TYPE t_tipdocu IS TABLE OF int_ocr_solic_credi.tip_docu %TYPE;
    TYPE t_numdocuiden IS TABLE OF int_ocr_solic_credi.num_docu_iden %TYPE;
    TYPE t_fecnaci IS TABLE OF int_ocr_solic_credi.fec_naci %TYPE;
    TYPE t_indestacivi IS TABLE OF int_ocr_solic_credi.ind_esta_civi %TYPE;
    TYPE t_indniveeduc IS TABLE OF int_ocr_solic_credi.ind_nive_educ %TYPE;
    TYPE t_valcodisegm IS TABLE OF int_ocr_solic_credi.val_codi_segm %TYPE;
    TYPE t_valdireclie IS TABLE OF int_ocr_solic_credi.val_dire_clie %TYPE;
    TYPE t_valtelfclie IS TABLE OF int_ocr_solic_credi.val_telf_clie %TYPE;
    TYPE t_valceluclie IS TABLE OF int_ocr_solic_credi.val_celu_clie %TYPE;
    TYPE t_valcodiventdire IS TABLE OF int_ocr_solic_credi.val_codi_vent_dire %TYPE;
    TYPE t_valmailclie IS TABLE OF int_ocr_solic_credi.val_mail_clie %TYPE;
    TYPE t_valdireentrclie IS TABLE OF int_ocr_solic_credi.val_dire_entre_clie %TYPE;
    TYPE t_valteleentrclie IS TABLE OF int_ocr_solic_credi.val_tele_entre_clie %TYPE;
    TYPE t_valceluentrclie IS TABLE OF int_ocr_solic_credi.val_celu_entre_clie %TYPE;
    TYPE t_valnom1refefamiclie IS TABLE OF int_ocr_solic_credi.val_nom1_refe_fami_clie %TYPE;
    TYPE t_valape1refefamiclie IS TABLE OF int_ocr_solic_credi.val_ape1_refe_fami_clie %TYPE;
    TYPE t_valdirerefefamiclie IS TABLE OF int_ocr_solic_credi.val_dire_refe_fami_clie %TYPE;
    TYPE t_valtelerefefamiclie IS TABLE OF int_ocr_solic_credi.val_tele_refe_fami_clie %TYPE;
    TYPE t_valcelurefefamiclie IS TABLE OF int_ocr_solic_credi.val_celu_refe_fami_clie %TYPE;
    TYPE t_valtipovincrefefamiclie IS TABLE OF int_ocr_solic_credi.val_tipo_vinc_refe_fami_clie %TYPE;
    TYPE t_valnom1refenofamiclie IS TABLE OF int_ocr_solic_credi.val_nom1_refe_nofa_clie %TYPE;
    TYPE t_valape1refenofamiclie IS TABLE OF int_ocr_solic_credi.val_ape1_refe_nofa_clie %TYPE;
    TYPE t_valdirerefenofamiclie IS TABLE OF int_ocr_solic_credi.val_dire_refe_nofa_clie %TYPE;
    TYPE t_valtelerefenofamiclie IS TABLE OF int_ocr_solic_credi.val_tele_refe_nofa_clie %TYPE;
    TYPE t_valcelurefenofamiclie IS TABLE OF int_ocr_solic_credi.val_celu_refe_nofa_clie %TYPE;
    TYPE t_valtipovincrefenofamiclie IS TABLE OF int_ocr_solic_credi.val_tipo_vinc_refe_nofa_clie %TYPE;
    TYPE t_valnom1fiad IS TABLE OF int_ocr_solic_credi.val_nom1_fiad %TYPE;
    TYPE t_valape1fiad IS TABLE OF int_ocr_solic_credi.val_ape1_fiad %TYPE;
    TYPE t_valdirefiad IS TABLE OF int_ocr_solic_credi.val_dire_fiad %TYPE;
    TYPE t_valteflfiad IS TABLE OF int_ocr_solic_credi.val_tefl_fiad %TYPE;
    TYPE t_valcelufiad IS TABLE OF int_ocr_solic_credi.val_celu_fiad %TYPE;
    TYPE t_tipdocufiad IS TABLE OF int_ocr_solic_credi.tip_docu_fiad %TYPE;
    TYPE t_coddocuidfi IS TABLE OF int_ocr_solic_credi.cod_docu_idfi %TYPE;
    TYPE t_valtipovincfiad IS TABLE OF int_ocr_solic_credi.val_tipo_vinc_fiad %TYPE;
    TYPE t_numdocu IS TABLE OF int_ocr_solic_credi.num_docu %TYPE;
    TYPE t_fecingreso IS TABLE OF int_ocr_solic_credi.fec_hora_crea_soli %TYPE;
  
    TYPE t_codprem IS TABLE OF int_ocr_solic_credi.cod_prem %TYPE;
    TYPE t_codperi IS TABLE OF int_ocr_solic_credi.cod_peri %TYPE;
    TYPE t_valape2fiad IS TABLE OF int_ocr_solic_credi.val_ape2_fiad %TYPE;
    TYPE t_valnom2fiad IS TABLE OF int_ocr_solic_credi.val_nom2_fiad %TYPE;
    TYPE t_tipviaclie IS TABLE OF int_ocr_solic_credi.tip_via_clie %TYPE;
    TYPE t_valobse IS TABLE OF int_ocr_solic_credi.val_obse%TYPE;
    TYPE t_oidnaci IS TABLE OF int_ocr_solic_credi.oid_naci%TYPE;
    TYPE t_codsexo IS TABLE OF int_ocr_solic_credi.cod_sexo%TYPE;
    TYPE t_ciudcodciuddomi IS TABLE OF int_ocr_solic_credi.ciud_cod_ciud_domi%TYPE;
    TYPE t_desvillapobldomi IS TABLE OF int_ocr_solic_credi.des_villa_pobl_domi%TYPE;
  
    TYPE t_val_codi_post_clie IS TABLE OF int_ocr_solic_credi.val_codi_post_clie%TYPE;
  
    TYPE t_val_tipo_meta IS TABLE OF int_ocr_solic_credi.tip_meta%TYPE;
    TYPE t_val_monto_meta IS TABLE OF int_ocr_solic_credi.val_mnto_meta%TYPE;
    TYPE t_val_des_meta IS TABLE OF int_ocr_solic_credi.des_meta%TYPE;
  
    ----------------------
    v_codpais                   t_codpais := t_codpais();
    v_codclie                   t_codclie := t_codclie();
    v_uniadmi                   t_uniadmi := t_uniadmi();
    v_tipingr                   t_tipingr := t_tipingr();
    v_codclierete               t_codclierete := t_codclierete();
    v_valape1                   t_valape1 := t_valape1();
    v_valape2                   t_valape2 := t_valape2();
    v_valnom1                   t_valnom1 := t_valnom1();
    v_valnom2                   t_valnom2 := t_valnom2();
    v_tipdocu                   t_tipdocu := t_tipdocu();
    v_numdocuiden               t_numdocuiden := t_numdocuiden();
    v_fecnaci                   t_fecnaci := t_fecnaci();
    v_indestacivi               t_indestacivi := t_indestacivi();
    v_indniveeduc               t_indniveeduc := t_indniveeduc();
    v_valcodisegm               t_valcodisegm := t_valcodisegm();
    v_valdireclie               t_valdireclie := t_valdireclie();
    v_valtelfclie               t_valtelfclie := t_valtelfclie();
    v_valceluclie               t_valceluclie := t_valceluclie();
    v_valcodiventdire           t_valcodiventdire := t_valcodiventdire();
    v_valmailclie               t_valmailclie := t_valmailclie();
    v_valdireentrclie           t_valdireentrclie := t_valdireentrclie();
    v_valteleentrclie           t_valteleentrclie := t_valteleentrclie();
    v_valceluentrclie           t_valceluentrclie := t_valceluentrclie();
    v_valnom1refefamiclie       t_valnom1refefamiclie := t_valnom1refefamiclie();
    v_valape1refefamiclie       t_valape1refefamiclie := t_valape1refefamiclie();
    v_valdirerefefamiclie       t_valdirerefefamiclie := t_valdirerefefamiclie();
    v_valtelerefefamiclie       t_valtelerefefamiclie := t_valtelerefefamiclie();
    v_valcelurefefamiclie       t_valcelurefefamiclie := t_valcelurefefamiclie();
    v_valtipovincrefefamiclie   t_valtipovincrefefamiclie := t_valtipovincrefefamiclie();
    v_valnom1refenofamiclie     t_valnom1refenofamiclie := t_valnom1refenofamiclie();
    v_valape1refenofamiclie     t_valape1refenofamiclie := t_valape1refenofamiclie();
    v_valdirerefenofamiclie     t_valdirerefenofamiclie := t_valdirerefenofamiclie();
    v_valtelerefenofamiclie     t_valtelerefenofamiclie := t_valtelerefenofamiclie();
    v_valcelurefenofamiclie     t_valcelurefenofamiclie := t_valcelurefenofamiclie();
    v_valtipovincrefenofamiclie t_valtipovincrefenofamiclie := t_valtipovincrefenofamiclie();
    v_valnom1fiad               t_valnom1fiad := t_valnom1fiad();
    v_valape1fiad               t_valape1fiad := t_valape1fiad();
    v_valdirefiad               t_valdirefiad := t_valdirefiad();
    v_valteflfiad               t_valteflfiad := t_valteflfiad();
    v_valcelufiad               t_valcelufiad := t_valcelufiad();
    v_tipdocufiad               t_tipdocufiad := t_tipdocufiad();
    v_coddocuidfi               t_coddocuidfi := t_coddocuidfi();
    v_valtipovincfiad           t_valtipovincfiad := t_valtipovincfiad();
    v_numdocu                   t_numdocu := t_numdocu();
    v_fecingreso                t_fecingreso := t_fecingreso();
  
    v_codperi          t_codperi := t_codperi();
    v_codprem          t_codprem := t_codprem();
    v_valobse          t_valobse := t_valobse();
    v_valape2fiad      t_valape2fiad := t_valape2fiad();
    v_valnom2fiad      t_valnom2fiad := t_valnom2fiad();
    v_oidnaci          t_oidnaci := t_oidnaci();
    v_codsexo          t_codsexo := t_codsexo();
    v_ciudcodciuddomi  t_ciudcodciuddomi := t_ciudcodciuddomi();
    v_tipviaclie       t_tipviaclie := t_tipviaclie();
    v_desvillapobldomi t_desvillapobldomi := t_desvillapobldomi();
  
    v_val_codi_post_clie t_val_codi_post_clie := t_val_codi_post_clie();
  
    v_val_tipo_meta  t_val_tipo_meta := t_val_tipo_meta();
    v_val_monto_meta t_val_monto_meta := t_val_monto_meta();
    v_val_des_meta   t_val_des_meta := t_val_des_meta();
  
    ----------------------
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  
    /*lscadena VARCHAR2(4):='<>;''|||char(13)';
    lsreplace VARCHAR2(4):='     ';*/
    -------------------------
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle,
                                                 4000);
  
    /*SELECT t.cod_tipo_docu
        INTO lscodigotipodocumento
        FROM sto_tipo_docum_digit t
       WHERE t.inte_cod_inte = pscodigointerfaz;
    */
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_codpais(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 2) THEN
                  v_codclie.extend;
                  v_codclie(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 3) THEN
                  v_uniadmi.extend;
                  v_uniadmi(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 4) THEN
                  v_tipingr.extend;
                
                  v_tipingr(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 5) THEN
                  v_codclierete.extend;
                  v_codclierete(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 6) THEN
                  v_valape1.extend;
                  v_valape1(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 7) THEN
                  v_valape2.extend;
                  v_valape2(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 8) THEN
                  v_valnom1.extend;
                  v_valnom1(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 9) THEN
                  v_valnom2.extend;
                  v_valnom2(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 10) THEN
                  v_tipdocu.extend;
                  v_tipdocu(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 11) THEN
                  v_numdocuiden.extend;
                  v_numdocuiden(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 12) THEN
                  v_fecnaci.extend;
                  v_fecnaci(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 13) THEN
                  v_indestacivi.extend;
                  v_indestacivi(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 14) THEN
                  v_indniveeduc.extend;
                  v_indniveeduc(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 15) THEN
                  v_valcodisegm.extend;
                  v_valcodisegm(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 16) THEN
                  v_valdireclie.extend;
                  v_valdireclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 17) THEN
                  v_valtelfclie.extend;
                  v_valtelfclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 18) THEN
                  v_valceluclie.extend;
                  v_valceluclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 19) THEN
                  v_valcodiventdire.extend;
                  v_valcodiventdire(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 20) THEN
                  v_valmailclie.extend;
                  v_valmailclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 21) THEN
                  v_valdireentrclie.extend;
                  v_valdireentrclie(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 22) THEN
                  v_valteleentrclie.extend;
                  v_valteleentrclie(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 23) THEN
                  v_valceluentrclie.extend;
                  v_valceluentrclie(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 24) THEN
                  v_valnom1refefamiclie.extend;
                  v_valnom1refefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 25) THEN
                  v_valape1refefamiclie.extend;
                  v_valape1refefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 26) THEN
                  v_valdirerefefamiclie.extend;
                  v_valdirerefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 27) THEN
                  v_valtelerefefamiclie.extend;
                  v_valtelerefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 28) THEN
                  v_valcelurefefamiclie.extend;
                  v_valcelurefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 29) THEN
                  v_valtipovincrefefamiclie.extend;
                  v_valtipovincrefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                        inicio,
                                                                        longitud),
                                                                 lscadena,
                                                                 lsreplace));
                ELSIF (posicion = 30) THEN
                  v_valnom1refenofamiclie.extend;
                  v_valnom1refenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                ELSIF (posicion = 31) THEN
                  v_valape1refenofamiclie.extend;
                  v_valape1refenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                
                ELSIF (posicion = 32) THEN
                  v_valdirerefenofamiclie.extend;
                  v_valdirerefenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                
                ELSIF (posicion = 33) THEN
                  v_valtelerefenofamiclie.extend;
                  v_valtelerefenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                ELSIF (posicion = 34) THEN
                  v_valcelurefenofamiclie.extend;
                  v_valcelurefenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                ELSIF (posicion = 35) THEN
                  v_valtipovincrefenofamiclie.extend;
                  v_valtipovincrefenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                          inicio,
                                                                          longitud),
                                                                   lscadena,
                                                                   lsreplace));
                ELSIF (posicion = 36) THEN
                  v_valnom1fiad.extend;
                  v_valnom1fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 37) THEN
                  v_valape1fiad.extend;
                  v_valape1fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 38) THEN
                  v_valdirefiad.extend;
                  v_valdirefiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 39) THEN
                  v_valteflfiad.extend;
                  v_valteflfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 40) THEN
                  v_valcelufiad.extend;
                  v_valcelufiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 41) THEN
                  v_tipdocufiad.extend;
                  v_tipdocufiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 42) THEN
                  v_coddocuidfi.extend;
                  v_coddocuidfi(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 43) THEN
                  v_valtipovincfiad.extend;
                  v_valtipovincfiad(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 44) THEN
                  v_numdocu.extend;
                  v_numdocu(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 45) THEN
                  v_fecingreso.extend;
                  v_fecingreso(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 46) THEN
                  v_codperi.extend;
                  v_codperi(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 47) THEN
                  v_codprem.extend;
                  v_codprem(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 48) THEN
                  v_valobse.extend;
                  v_valobse(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 49) THEN
                  v_valape2fiad.extend;
                  v_valape2fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 50) THEN
                  v_valnom2fiad.extend;
                  v_valnom2fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                
                ELSIF (posicion = 51) THEN
                  v_oidnaci.extend;
                  v_oidnaci(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 52) THEN
                  v_codsexo.extend;
                  v_codsexo(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 53) THEN
                  v_ciudcodciuddomi.extend;
                  v_ciudcodciuddomi(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 54) THEN
                  v_tipviaclie.extend;
                  v_tipviaclie(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 55) THEN
                  v_desvillapobldomi.extend;
                  v_desvillapobldomi(i) := TRIM(translate(substr(lslinea,
                                                                 inicio,
                                                                 longitud),
                                                          lscadena,
                                                          lsreplace));
                ELSIF (posicion = 56) THEN
                  v_val_codi_post_clie.extend;
                  v_val_codi_post_clie(i) := TRIM(translate(substr(lslinea,
                                                                   inicio,
                                                                   longitud),
                                                            lscadena,
                                                            lsreplace));
                
                ELSIF (posicion = 57) THEN
                  v_val_tipo_meta.extend;
                  v_val_tipo_meta(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                
                ELSIF (posicion = 58) THEN
                  v_val_monto_meta.extend;
                  v_val_monto_meta(i) := TRIM(translate(substr(lslinea,
                                                               inicio,
                                                               longitud),
                                                        lscadena,
                                                        lsreplace));
                
                ELSIF (posicion = 59) THEN
                  v_val_des_meta.extend;
                  v_val_des_meta(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codpais.count
    
      INSERT INTO int_ocr_solic_credi
        (cod_pais,
         cod_comp,
         cod_clie,
         uni_admi,
         tip_ingr,
         cod_clie_rete,
         val_ape1,
         val_ape2,
         val_nom1,
         val_nom2,
         fec_naci,
         num_docu_iden,
         ind_esta_civi,
         ind_nive_educ,
         val_dire_clie,
         val_telf_clie,
         val_celu_clie,
         ind_vent_dire,
         val_nom1_refe_fami_clie,
         val_ape1_refe_fami_clie,
         val_dire_refe_fami_clie,
         val_tele_refe_fami_clie,
         val_tipo_vinc_refe_fami_clie,
         val_nom1_refe_nofa_clie,
         val_ape1_refe_nofa_clie,
         val_dire_refe_nofa_clie,
         val_tele_refe_nofa_clie,
         val_tipo_vinc_refe_nofa_clie,
         val_nom1_fiad,
         val_ape1_fiad,
         val_dire_fiad,
         val_tefl_fiad,
         val_tipo_vinc_fiad,
         cod_docu_idfi,
         val_dire_entre_clie,
         val_tele_entre_clie,
         val_mail_clie,
         val_codi_segm,
         val_celu_refe_fami_clie,
         val_celu_refe_nofa_clie,
         val_celu_fiad,
         val_celu_entre_clie,
         tip_docu_fiad,
         tip_docu,
         num_docu,
         fec_hora_crea_soli,
         fec_proc,
         cod_depa_clie,
         cod_prov_clie,
         cod_dist_clie,
         cod_sect_clie,
         ind_orig,
         cod_peri,
         cod_prem,
         val_obse,
         val_ape2_fiad,
         val_nom2_fiad,
         oid_naci,
         cod_sexo,
         ciud_cod_ciud_domi,
         ciud_cod_ugeo_regi_domi,
         tip_via_clie,
         des_villa_pobl_domi,
         val_codi_post_clie,
         tip_meta,
         val_mnto_meta,
         des_meta)
      VALUES
        (v_codpais(i),
         (SELECT decode(b.ind_pais_marc, 'ES', '01', '02') marca
            FROM seg_pais a,
                 bas_pais b
           WHERE a.cod_pais = b.cod_pais
             AND a.cod_pais = pscodigopais),
         v_codclie(i),
         v_uniadmi(i),
         v_tipingr(i),
         v_codclierete(i),
         v_valape1(i),
         v_valape2(i),
         v_valnom1(i),
         v_valnom2(i),
         v_fecnaci(i),
         v_numdocuiden(i),
         v_indestacivi(i),
         v_indniveeduc(i),
         v_valdireclie(i),
         v_valtelfclie(i),
         v_valceluclie(i),
         v_valcodiventdire(i),
         v_valnom1refefamiclie(i),
         v_valape1refefamiclie(i),
         v_valdirerefefamiclie(i),
         v_valtelerefefamiclie(i),
         v_valtipovincrefefamiclie(i),
         v_valnom1refenofamiclie(i),
         v_valape1refenofamiclie(i),
         v_valdirerefenofamiclie(i),
         v_valtelerefenofamiclie(i),
         v_valtipovincrefenofamiclie(i),
         v_valnom1fiad(i),
         v_valape1fiad(i),
         v_valdirefiad(i),
         v_valteflfiad(i),
         v_valtipovincfiad(i),
         v_coddocuidfi(i),
         v_valdireentrclie(i),
         v_valteleentrclie(i),
         v_valmailclie(i),
         v_valcodisegm(i),
         v_valcelurefefamiclie(i),
         v_valcelurefenofamiclie(i),
         v_valcelufiad(i),
         v_valceluentrclie(i),
         v_tipdocufiad(i),
         v_tipdocu(i),
         v_numdocu(i),
         v_fecingreso(i),
         to_char(SYSDATE, 'YYYYMMDD'),
         substr(v_valcodisegm(i), 1, 6),
         substr(v_valcodisegm(i), 7, 6),
         substr(v_valcodisegm(i), 13, 6),
         substr(v_valcodisegm(i), 19, 6),
         psindicadororigen,
         v_codperi(i),
         v_codprem(i),
         v_valobse(i),
         v_valape2fiad(i),
         v_valnom2fiad(i),
         v_oidnaci(i),
         v_codsexo(i),
         v_ciudcodciuddomi(i),
         substr(v_uniadmi(i), 1, 2),
         v_tipviaclie(i),
         v_desvillapobldomi(i),
         v_val_codi_post_clie(i),
         v_val_tipo_meta(i),
         v_val_monto_meta(i),
         v_val_des_meta(i));
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_recep_socre_web: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_socre_web;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Actualizacion de Datos Corporativa
                      de Datos
  Fecha Creacion    : 13/04/2009
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_acdat_corpo
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_actua_datos.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_actua_datos.cod_comp%TYPE;
    TYPE t_cod_clie IS TABLE OF int_ocr_actua_datos.cod_clie%TYPE;
    TYPE t_num_docu IS TABLE OF int_ocr_actua_datos.num_docu%TYPE;
    TYPE t_fec_proc IS TABLE OF int_ocr_actua_datos.fec_proc%TYPE;
    TYPE t_cod_unid_admi IS TABLE OF int_ocr_actua_datos.cod_unid_admi%TYPE;
    TYPE t_cod_peri IS TABLE OF int_ocr_actua_datos.cod_peri%TYPE;
    TYPE t_val_ape1 IS TABLE OF int_ocr_actua_datos.val_ape1%TYPE;
    TYPE t_val_ape2 IS TABLE OF int_ocr_actua_datos.val_ape2%TYPE;
    TYPE t_val_nom1 IS TABLE OF int_ocr_actua_datos.val_nom1%TYPE;
    TYPE t_val_nom2 IS TABLE OF int_ocr_actua_datos.val_nom2%TYPE;
    TYPE t_cod_tipo_docu IS TABLE OF int_ocr_actua_datos.cod_tipo_docu%TYPE;
    TYPE t_val_docu_nuev IS TABLE OF int_ocr_actua_datos.num_docu_iden%TYPE;
    TYPE t_val_tipo_dire IS TABLE OF int_ocr_actua_datos.val_tipo_dire_clie%TYPE;
    TYPE t_val_dire_nuev IS TABLE OF int_ocr_actua_datos.val_dire_nuev%TYPE;
    TYPE t_des_banc_nuev IS TABLE OF int_ocr_actua_datos.des_banc_nuev%TYPE;
    TYPE t_des_dele_clie IS TABLE OF int_ocr_actua_datos.val_dele_clie%TYPE;
    TYPE t_des_ciud_nuev IS TABLE OF int_ocr_actua_datos.des_ciud_nuev%TYPE;
    TYPE t_des_depa_nuev IS TABLE OF int_ocr_actua_datos.des_depa_nuev%TYPE;
    TYPE t_val_tele_nuev IS TABLE OF int_ocr_actua_datos.val_tele_nuev%TYPE;
    TYPE t_val_celu_nuev IS TABLE OF int_ocr_actua_datos.val_celu_nuev%TYPE;
    TYPE t_val_tele_cetr IS TABLE OF int_ocr_actua_datos.val_tele_cetr%TYPE;
    TYPE t_val_mail_nuev IS TABLE OF int_ocr_actua_datos.val_mail_nuev%TYPE;
    TYPE t_cod_regi IS TABLE OF int_ocr_actua_datos.cod_regi%TYPE;
    TYPE t_cod_zona IS TABLE OF int_ocr_actua_datos.cod_zona%TYPE;
    TYPE t_cod_stat_proc IS TABLE OF int_ocr_actua_datos.cod_stat_proc%TYPE;
    TYPE t_cod_moti_rech IS TABLE OF int_ocr_actua_datos.cod_moti_rech%TYPE;
    TYPE t_cod_tipo_via IS TABLE OF int_ocr_actua_datos.cod_tipo_via%TYPE;
    TYPE t_val_nomb_via IS TABLE OF int_ocr_actua_datos.val_nomb_via%TYPE;
    TYPE t_num_dire IS TABLE OF int_ocr_actua_datos.num_dire%TYPE;
    TYPE t_val_codi_post IS TABLE OF int_ocr_actua_datos.val_codi_post_clie%TYPE;
    TYPE t_cod_depa IS TABLE OF int_ocr_actua_datos.cod_depa%TYPE;
    TYPE t_cod_prov IS TABLE OF int_ocr_actua_datos.cod_prov%TYPE;
    TYPE t_cod_dist IS TABLE OF int_ocr_actua_datos.cod_dist%TYPE;
    TYPE t_cod_cent_pobl IS TABLE OF int_ocr_actua_datos.cod_cent_pobl%TYPE;
    TYPE t_cod_terr_corr IS TABLE OF int_ocr_actua_datos.cod_terr_corr%TYPE;
    TYPE t_dom_cant      IS TABLE OF int_ocr_actua_datos.dom_cant%TYPE;            
    TYPE t_dom_parr      IS TABLE OF int_ocr_actua_datos.dom_parr%TYPE;            
    TYPE t_dom_manz      IS TABLE OF int_ocr_actua_datos.dom_manz%TYPE;            
    TYPE t_dom_etap      IS TABLE OF int_ocr_actua_datos.dom_etap%TYPE;            
    TYPE t_dom_call_prin IS TABLE OF int_ocr_actua_datos.dom_call_prin%TYPE;       
    TYPE t_dom_call_secu IS TABLE OF int_ocr_actua_datos.dom_call_secu%TYPE;       
    TYPE t_dom_num       IS TABLE OF int_ocr_actua_datos.dom_num%TYPE;             
    TYPE t_dom_refe      IS TABLE OF int_ocr_actua_datos.dom_refe%TYPE;            
    TYPE t_ind_dird_dire IS TABLE OF int_ocr_actua_datos.ind_dird_dire%TYPE;                
    TYPE t_ent_manz      IS TABLE OF int_ocr_actua_datos.ent_manz%TYPE;            
    TYPE t_ent_etap      IS TABLE OF int_ocr_actua_datos.ent_etap%TYPE;            
    TYPE t_ent_call_prin IS TABLE OF int_ocr_actua_datos.ent_call_prin%TYPE;       
    TYPE t_ent_call_secu IS TABLE OF int_ocr_actua_datos.ent_call_secu%TYPE;       
    TYPE t_ent_num       IS TABLE OF int_ocr_actua_datos.ent_num%TYPE;             
    TYPE t_ent_refe      IS TABLE OF int_ocr_actua_datos.ent_refe%TYPE;            

  
    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_comp      t_cod_comp := t_cod_comp();
    v_cod_clie      t_cod_clie := t_cod_clie();
    v_num_docu      t_num_docu := t_num_docu();
    v_fec_proc      t_fec_proc := t_fec_proc();
    v_cod_unid_admi t_cod_unid_admi := t_cod_unid_admi();
    v_cod_peri      t_cod_peri := t_cod_peri();
    v_val_ape1      t_val_ape1 := t_val_ape1();
    v_val_ape2      t_val_ape2 := t_val_ape2();
    v_val_nom1      t_val_nom1 := t_val_nom1();
    v_val_nom2      t_val_nom2 := t_val_nom2();
    v_cod_tipo_docu t_cod_tipo_docu := t_cod_tipo_docu();
    v_val_docu_nuev t_val_docu_nuev := t_val_docu_nuev();
    v_val_tipo_dire t_val_tipo_dire := t_val_tipo_dire();
    v_val_dire_nuev t_val_dire_nuev := t_val_dire_nuev();
    v_des_banc_nuev t_des_banc_nuev := t_des_banc_nuev();
    v_des_dele_clie t_des_dele_clie := t_des_dele_clie();
    v_des_ciud_nuev t_des_ciud_nuev := t_des_ciud_nuev();
    v_des_depa_nuev t_des_depa_nuev := t_des_depa_nuev();
    v_val_tele_nuev t_val_tele_nuev := t_val_tele_nuev();
    v_val_celu_nuev t_val_celu_nuev := t_val_celu_nuev();
    v_val_tele_cetr t_val_tele_cetr := t_val_tele_cetr();
    v_val_mail_nuev t_val_mail_nuev := t_val_mail_nuev();
    v_cod_regi      t_cod_regi := t_cod_regi();
    v_cod_zona      t_cod_zona := t_cod_zona();
    v_cod_stat_proc t_cod_stat_proc := t_cod_stat_proc();
    v_cod_moti_rech t_cod_moti_rech := t_cod_moti_rech();
    v_cod_tipo_via  t_cod_tipo_via := t_cod_tipo_via();
    v_val_nomb_via  t_val_nomb_via := t_val_nomb_via();
    v_num_dire      t_num_dire := t_num_dire();
    v_val_codi_post t_val_codi_post := t_val_codi_post();
    v_cod_depa      t_cod_depa := t_cod_depa();
    v_cod_prov      t_cod_prov := t_cod_prov();
    v_cod_dist      t_cod_dist := t_cod_dist();
    v_cod_cent_pobl t_cod_cent_pobl := t_cod_cent_pobl();
    v_cod_terr_corr      t_cod_terr_corr     := t_cod_terr_corr();
    v_dom_cant           t_dom_cant          := t_dom_cant();     
    v_dom_parr           t_dom_parr          := t_dom_parr();     
    v_dom_manz           t_dom_manz          := t_dom_manz();     
    v_dom_etap           t_dom_etap          := t_dom_etap();     
    v_dom_call_prin      t_dom_call_prin     := t_dom_call_prin();
    v_dom_call_secu      t_dom_call_secu     := t_dom_call_secu();
    v_dom_num            t_dom_num           := t_dom_num();      
    v_dom_refe           t_dom_refe          := t_dom_refe();     
    v_ind_dird_dire      t_ind_dird_dire     := t_ind_dird_dire();    
    v_ent_manz           t_ent_manz          := t_ent_manz();     
    v_ent_etap           t_ent_etap          := t_ent_etap();     
    v_ent_call_prin      t_ent_call_prin     := t_ent_call_prin();
    v_ent_call_secu      t_ent_call_secu     := t_ent_call_secu();
    v_ent_num            t_ent_num           := t_ent_num();      
    v_ent_refe           t_ent_refe          := t_ent_refe();    
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
        
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                  v_cod_pais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_cod_pais(i),
                                                                               v_cod_comp(i));
                ELSIF (posicion = 3) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 4) THEN
                  v_num_docu.extend;
                  v_num_docu(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 5) THEN
                  v_fec_proc.extend;
                  v_fec_proc(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 6) THEN
                  v_cod_unid_admi.extend;
                  v_cod_unid_admi(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 7) THEN
                  v_cod_peri.extend;
                  v_cod_peri(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 8) THEN
                  v_val_ape1.extend;
                  v_val_ape1(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 9) THEN
                  v_val_ape2.extend;
                  v_val_ape2(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 10) THEN
                  v_val_nom1.extend;
                  v_val_nom1(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 11) THEN
                  v_val_nom2.extend;
                  v_val_nom2(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 12) THEN
                  v_cod_tipo_docu.extend;
                  v_cod_tipo_docu(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 13) THEN
                  v_val_docu_nuev.extend;
                  v_val_docu_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 14) THEN
                  v_val_tipo_dire.extend;
                  v_val_tipo_dire(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 15) THEN
                  v_val_dire_nuev.extend;
                  v_val_dire_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 16) THEN
                  v_des_banc_nuev.extend;
                  v_des_banc_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 17) THEN
                  v_des_dele_clie.extend;
                  v_des_dele_clie(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 18) THEN
                  v_des_ciud_nuev.extend;
                  v_des_ciud_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 19) THEN
                  v_des_depa_nuev.extend;
                  v_des_depa_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 20) THEN
                  v_val_tele_nuev.extend;
                  v_val_tele_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 21) THEN
                  v_val_celu_nuev.extend;
                  v_val_celu_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 22) THEN
                  v_val_tele_cetr.extend;
                  v_val_tele_cetr(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 23) THEN
                  v_val_mail_nuev.extend;
                  v_val_mail_nuev(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 24) THEN
                  v_cod_regi.extend;
                  v_cod_regi(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 25) THEN
                  v_cod_zona.extend;
                  v_cod_zona(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 26) THEN
                  v_cod_stat_proc.extend;
                  v_cod_stat_proc(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 27) THEN
                  v_cod_moti_rech.extend;
                  v_cod_moti_rech(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 28) THEN
                  v_cod_tipo_via.extend;
                  v_cod_tipo_via(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 29) THEN
                  v_val_nomb_via.extend;
                  v_val_nomb_via(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 30) THEN
                  v_num_dire.extend;
                  v_num_dire(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 31) THEN
                  v_val_codi_post.extend;
                  v_val_codi_post(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 32) THEN
                  v_cod_depa.extend;
                  v_cod_depa(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 33) THEN
                  v_cod_prov.extend;
                  v_cod_prov(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 34) THEN
                  v_cod_dist.extend;
                  v_cod_dist(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 35) THEN
                  v_cod_cent_pobl.extend;
                  v_cod_cent_pobl(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 36) THEN
                  v_cod_terr_corr.extend;
                  v_cod_terr_corr(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 37) THEN
                  v_dom_cant.extend;
                  v_dom_cant(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 38) THEN
                  v_dom_parr.extend;
                  v_dom_parr(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 39) THEN
                  v_dom_manz.extend;
                  v_dom_manz(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 40) THEN
                  v_dom_etap.extend;
                  v_dom_etap(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 41) THEN
                  v_dom_call_prin.extend;
                  v_dom_call_prin(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 42) THEN
                  v_dom_call_secu.extend;
                  v_dom_call_secu(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 43) THEN
                  v_dom_num.extend;
                  v_dom_num(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 44) THEN
                  v_dom_refe.extend;
                  v_dom_refe(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 45) THEN
                  v_ind_dird_dire.extend;
                  v_ind_dird_dire(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));

                ELSIF (posicion = 46) THEN
                  v_ent_manz.extend;
                  v_ent_manz(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 47) THEN
                  v_ent_etap.extend;
                  v_ent_etap(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 48) THEN
                  v_ent_call_prin.extend;
                  v_ent_call_prin(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 49) THEN
                  v_ent_call_secu.extend;
                  v_ent_call_secu(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 50) THEN
                  v_ent_num.extend;
                  v_ent_num(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));   
                ELSIF (posicion = 51) THEN
                  v_ent_refe.extend;
                  v_ent_refe(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_cod_pais.count
      INSERT INTO int_ocr_actua_datos
        (cod_pais,
         cod_comp,
         cod_clie,
         num_docu,
         fec_proc,
         cod_unid_admi,
         cod_peri,
         val_ape1,
         val_ape2,
         val_nom1,
         val_nom2,
         cod_tipo_docu,
         num_docu_iden,
         val_dire_nuev,
         des_banc_nuev,
         val_dele_clie,
         des_ciud_nuev,
         des_depa_nuev,
         val_tele_nuev,
         val_celu_nuev,
         val_tele_cetr,
         val_mail_nuev,
         cod_regi,
         cod_zona,
         cod_stat_proc,
         cod_moti_rech,
         cod_tipo_via,
         val_nomb_via,
         num_dire,
         cod_depa,
         cod_prov,
         cod_dist,
         cod_cent_pobl,
         val_tipo_dire_clie,
         val_codi_post_clie,
         ind_orig,
         cod_terr_corr,   
         dom_cant,        
         dom_parr,        
         dom_manz,        
         dom_etap,        
         dom_call_prin,   
         dom_call_secu,   
         dom_num,         
         dom_refe,        
         ind_dird_dire,          
         ent_manz,        
         ent_etap,        
         ent_call_prin,   
         ent_call_secu,   
         ent_num,         
         ent_refe        
          )
      VALUES
        (v_cod_pais(i),
         v_cod_comp(i),
         decode(v_cod_clie(i), NULL, '0', v_cod_clie(i)),
         v_num_docu(i),
         v_fec_proc(i),
         v_cod_unid_admi(i),
         v_cod_peri(i),
         v_val_ape1(i),
         v_val_ape2(i),
         v_val_nom1(i),
         v_val_nom2(i),
         v_cod_tipo_docu(i),
         v_val_docu_nuev(i),
         v_val_dire_nuev(i),
         v_des_banc_nuev(i),
         v_des_dele_clie(i),
         v_des_ciud_nuev(i),
         v_des_depa_nuev(i),
         v_val_tele_nuev(i),
         v_val_celu_nuev(i),
         v_val_tele_cetr(i),
         v_val_mail_nuev(i),
         v_cod_regi(i),
         v_cod_zona(i),
         v_cod_stat_proc(i),
         v_cod_moti_rech(i),
         v_cod_tipo_via(i),
         v_val_nomb_via(i),
         v_num_dire(i),
         v_cod_depa(i),
         v_cod_prov(i),
         v_cod_dist(i),
         v_cod_cent_pobl(i),
         v_val_tipo_dire(i),
         v_val_codi_post(i),
         psindicadororigen,
         nvl(trim(v_cod_terr_corr(i)),sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais, 'STO_COD_TERR_SCC')),
         v_dom_cant(i),     
         v_dom_parr(i),     
         v_dom_manz(i),     
         v_dom_etap(i),     
         v_dom_call_prin(i),
         v_dom_call_secu(i),
         v_dom_num(i),      
         v_dom_refe(i),      
         nvl(trim(v_ind_dird_dire(i)),sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais, 'STO_DDOM_ENT_SCC')),  
         v_ent_manz(i),     
         v_ent_etap(i),     
         v_ent_call_prin(i),
         v_ent_call_secu(i),
         v_ent_num(i),      
         v_ent_refe(i)     
          );
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_ACDAT_CORPO: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_recep_acdat_corpo;
  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Detalles Accion de Servicios Postventas
  Fecha Creacion    : 16/04/2009
  Autor             : Cristhian Roman
  **************************************************************************************/
  PROCEDURE int_pr_ocr_recep_posve_deacc
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psnumerolote     VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codigopais IS TABLE OF int_ocr_detal_servi_postv.cod_pais%TYPE;
    TYPE t_codigocompania IS TABLE OF int_ocr_detal_servi_postv.cod_cia%TYPE;
    TYPE t_numerodocumento IS TABLE OF int_ocr_detal_servi_postv.num_docu%TYPE;
    TYPE t_codigocliente IS TABLE OF int_ocr_detal_servi_postv.cod_clie%TYPE;
    TYPE t_tiporeferencia IS TABLE OF int_ocr_detal_servi_postv.tip_refe%TYPE;
    TYPE t_codigovtaproddevuelve IS TABLE OF int_ocr_detal_servi_postv.cod_vent_devu%TYPE;
    TYPE t_codigovtaproddesea IS TABLE OF int_ocr_detal_servi_postv.cod_vent_dese%TYPE;
    TYPE t_cantproddevuelve IS TABLE OF int_ocr_detal_servi_postv.can_prod_devu%TYPE;
    TYPE t_cantproddesea IS TABLE OF int_ocr_detal_servi_postv.can_prod_dese%TYPE;
    TYPE t_statusproceso IS TABLE OF int_ocr_detal_servi_postv.sta_proc%TYPE;
    TYPE t_motivorechazo IS TABLE OF int_ocr_detal_servi_postv.cod_moti_rech%TYPE;
    TYPE t_motivospv IS TABLE OF int_ocr_detal_servi_postv.mot_spv%TYPE;
    TYPE t_indaccion IS TABLE OF int_ocr_detal_servi_postv.ind_acci%TYPE;
  
    v_codigopais            t_codigopais := t_codigopais();
    v_codigocompania        t_codigocompania := t_codigocompania();
    v_numerodocumento       t_numerodocumento := t_numerodocumento();
    v_codigocliente         t_codigocliente := t_codigocliente();
    v_tiporeferencia        t_tiporeferencia := t_tiporeferencia();
    v_codigovtaproddevuelve t_codigovtaproddevuelve := t_codigovtaproddevuelve();
    v_codigovtaproddesea    t_codigovtaproddesea := t_codigovtaproddesea();
    v_cantproddevuelve      t_cantproddevuelve := t_cantproddevuelve();
    v_cantproddesea         t_cantproddesea := t_cantproddesea();
    v_statusproceso         t_statusproceso := t_statusproceso();
    v_motivorechazo         t_motivorechazo := t_motivorechazo();
    v_motivospv             t_motivospv := t_motivospv();
    v_indaccion             t_indaccion := t_indaccion();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20) || '+';
    lsreplace VARCHAR2(100) := 'a          ';
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codigopais.extend;
                  v_codigopais(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 2) THEN
                  v_codigocompania.extend;
                  v_codigocompania(i) := TRIM(translate(substr(lslinea,
                                                               inicio,
                                                               longitud),
                                                        lscadena,
                                                        lsreplace));
                  v_codigopais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codigopais(i),
                                                                                 v_codigocompania(i));
                ELSIF (posicion = 3) THEN
                  v_numerodocumento.extend;
                  v_numerodocumento(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 4) THEN
                  v_codigocliente.extend;
                  v_codigocliente(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 5) THEN
                  v_tiporeferencia.extend;
                  v_tiporeferencia(i) := TRIM(translate(substr(lslinea,
                                                               inicio,
                                                               longitud),
                                                        lscadena,
                                                        lsreplace));
                ELSIF (posicion = 6) THEN
                  v_codigovtaproddevuelve.extend;
                  v_codigovtaproddevuelve(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                ELSIF (posicion = 7) THEN
                  v_codigovtaproddesea.extend;
                  v_codigovtaproddesea(i) := TRIM(translate(substr(lslinea,
                                                                   inicio,
                                                                   longitud),
                                                            lscadena,
                                                            lsreplace));
                ELSIF (posicion = 8) THEN
                  v_cantproddevuelve.extend;
                  v_cantproddevuelve(i) := nvl(TRIM(translate(substr(lslinea,
                                                                     inicio,
                                                                     longitud),
                                                              lscadena,
                                                              lsreplace)),
                                               0);
                ELSIF (posicion = 9) THEN
                  v_cantproddesea.extend;
                  v_cantproddesea(i) := nvl(TRIM(translate(substr(lslinea,
                                                                  inicio,
                                                                  longitud),
                                                           lscadena,
                                                           lsreplace)),
                                            0);
                ELSIF (posicion = 10) THEN
                  v_statusproceso.extend;
                  v_statusproceso(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 11) THEN
                  v_motivorechazo.extend;
                  v_motivorechazo(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 12) THEN
                  v_motivospv.extend;
                  v_motivospv(i) := TRIM(translate(substr(lslinea,
                                                          inicio,
                                                          longitud),
                                                   lscadena,
                                                   lsreplace));
                
                ELSIF (posicion = 13) THEN
                  v_indaccion.extend;
                  v_indaccion(i) := TRIM(translate(substr(lslinea,
                                                          inicio,
                                                          longitud),
                                                   lscadena,
                                                   lsreplace));
                
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codigopais.count
    
      INSERT INTO int_ocr_detal_servi_postv
        (cod_pais,
         cod_cia,
         num_docu,
         cod_clie,
         tip_refe,
         cod_vent_devu,
         cod_vent_dese,
         can_prod_devu,
         can_prod_dese,
         sta_proc,
         cod_moti_rech,
         mot_spv,
         cod_zona,
         cod_regi,
         ind_acci,
         num_lote)
      VALUES
        (v_codigopais(i),
         v_codigocompania(i),
         v_numerodocumento(i),
         v_codigocliente(i),
         v_tiporeferencia(i),
         v_codigovtaproddevuelve(i),
         v_codigovtaproddesea(i),
         v_cantproddevuelve(i),
         v_cantproddesea(i),
         v_statusproceso(i),
         v_motivorechazo(i),
         v_motivospv(i),
         (SELECT cod_zona_arri
            FROM int_ocr_cabec_servi_postv
           WHERE cod_pais = v_codigopais(i)
             AND cod_clie = v_codigocliente(i)
             AND num_docu = v_numerodocumento(i)
             AND rownum = 1),
         (SELECT cod_regi_arri
            FROM int_ocr_cabec_servi_postv
           WHERE cod_pais = v_codigopais(i)
             AND cod_clie = v_codigocliente(i)
             AND num_docu = v_numerodocumento(i)
             AND rownum = 1),
         v_indaccion(i),
         psnumerolote);
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_POSVE_DEACC: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_posve_deacc;

  PROCEDURE int_pr_ocr_recep_acdat_web
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9 --anulado = 9; activo =1
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codpais IS TABLE OF int_ocr_actua_datos.cod_pais %TYPE;
    TYPE t_codclie IS TABLE OF int_ocr_actua_datos.cod_clie %TYPE;
    TYPE t_uniadmi IS TABLE OF int_ocr_actua_datos.cod_unid_admi %TYPE;
    TYPE t_tipingr IS TABLE OF int_ocr_actua_datos.tip_ingr %TYPE;
    TYPE t_codclierete IS TABLE OF int_ocr_actua_datos.cod_clie_rete %TYPE;
    TYPE t_valape1 IS TABLE OF int_ocr_actua_datos.val_ape1 %TYPE;
    TYPE t_valape2 IS TABLE OF int_ocr_actua_datos.val_ape2 %TYPE;
    TYPE t_valnom1 IS TABLE OF int_ocr_actua_datos.val_nom1 %TYPE;
    TYPE t_valnom2 IS TABLE OF int_ocr_actua_datos.val_nom2 %TYPE;
    TYPE t_tipdocu IS TABLE OF int_ocr_actua_datos.cod_tipo_docu %TYPE;
    TYPE t_numdocuiden IS TABLE OF int_ocr_actua_datos.num_docu_iden %TYPE;
    TYPE t_fecnaci IS TABLE OF int_ocr_actua_datos.fec_naci %TYPE;
    TYPE t_indestacivi IS TABLE OF int_ocr_actua_datos.ind_esta_civi %TYPE;
    TYPE t_indniveeduc IS TABLE OF int_ocr_actua_datos.ind_nive_educ %TYPE;
    TYPE t_valcodisegm IS TABLE OF int_ocr_actua_datos.val_codi_segm %TYPE;
    TYPE t_valdireclie IS TABLE OF int_ocr_actua_datos.val_dire_nuev %TYPE;
    TYPE t_valtelfclie IS TABLE OF int_ocr_actua_datos.val_tele_nuev %TYPE;
    TYPE t_valceluclie IS TABLE OF int_ocr_actua_datos.val_celu_nuev %TYPE;
    TYPE t_valcodiventdire IS TABLE OF int_ocr_actua_datos.val_codi_vent_dire %TYPE;
    TYPE t_valmailclie IS TABLE OF int_ocr_actua_datos.val_mail_nuev %TYPE;
    TYPE t_valdireentrclie IS TABLE OF int_ocr_actua_datos.val_dire_entre_clie %TYPE;
    TYPE t_valteleentrclie IS TABLE OF int_ocr_actua_datos.val_tele_entre_clie %TYPE;
    TYPE t_valceluentrclie IS TABLE OF int_ocr_actua_datos.val_celu_entre_clie %TYPE;
    TYPE t_valnom1refefamiclie IS TABLE OF int_ocr_actua_datos.val_nom1_refe_fami_clie %TYPE;
    TYPE t_valape1refefamiclie IS TABLE OF int_ocr_actua_datos.val_ape1_refe_fami_clie %TYPE;
    TYPE t_valdirerefefamiclie IS TABLE OF int_ocr_actua_datos.val_dire_refe_fami_clie %TYPE;
    TYPE t_valtelerefefamiclie IS TABLE OF int_ocr_actua_datos.val_tele_refe_fami_clie %TYPE;
    TYPE t_valcelurefefamiclie IS TABLE OF int_ocr_actua_datos.val_celu_refe_fami_clie %TYPE;
    TYPE t_valtipovincrefefamiclie IS TABLE OF int_ocr_actua_datos.val_tipo_vinc_refe_fami_clie %TYPE;
    TYPE t_valnom1refenofamiclie IS TABLE OF int_ocr_actua_datos.val_nom1_refe_nofa_clie %TYPE;
    TYPE t_valape1refenofamiclie IS TABLE OF int_ocr_actua_datos.val_ape1_refe_nofa_clie %TYPE;
    TYPE t_valdirerefenofamiclie IS TABLE OF int_ocr_actua_datos.val_dire_refe_nofa_clie %TYPE;
    TYPE t_valtelerefenofamiclie IS TABLE OF int_ocr_actua_datos.val_tele_refe_nofa_clie %TYPE;
    TYPE t_valcelurefenofamiclie IS TABLE OF int_ocr_actua_datos.val_celu_refe_nofa_clie %TYPE;
    TYPE t_valtipovincrefenofamiclie IS TABLE OF int_ocr_actua_datos.val_tipo_vinc_refe_nofa_clie %TYPE;
    TYPE t_valnom1fiad IS TABLE OF int_ocr_actua_datos.val_nom1_fiad %TYPE;
    TYPE t_valape1fiad IS TABLE OF int_ocr_actua_datos.val_ape1_fiad %TYPE;
    TYPE t_valdirefiad IS TABLE OF int_ocr_actua_datos.val_dire_fiad %TYPE;
    TYPE t_valteflfiad IS TABLE OF int_ocr_actua_datos.val_tefl_fiad %TYPE;
    TYPE t_valcelufiad IS TABLE OF int_ocr_actua_datos.val_celu_fiad %TYPE;
    TYPE t_tipdocufiad IS TABLE OF int_ocr_actua_datos.tip_docu_fiad %TYPE;
    TYPE t_coddocuidfi IS TABLE OF int_ocr_actua_datos.cod_docu_idfi %TYPE;
    TYPE t_valtipovincfiad IS TABLE OF int_ocr_actua_datos.val_tipo_vinc_fiad %TYPE;
    TYPE t_numdocu IS TABLE OF int_ocr_actua_datos.num_docu %TYPE;
    TYPE t_fecingreso IS TABLE OF int_ocr_actua_datos.fec_hora_crea_soli %TYPE;
  
    TYPE t_codprem IS TABLE OF int_ocr_solic_credi.cod_prem %TYPE;
    TYPE t_codperi IS TABLE OF int_ocr_solic_credi.cod_peri %TYPE;
    TYPE t_valape2fiad IS TABLE OF int_ocr_solic_credi.val_ape2_fiad %TYPE;
    TYPE t_valnom2fiad IS TABLE OF int_ocr_solic_credi.val_nom2_fiad %TYPE;
    TYPE t_tipviaclie IS TABLE OF int_ocr_solic_credi.tip_via_clie %TYPE;
    TYPE t_valobse IS TABLE OF int_ocr_solic_credi.val_obse%TYPE;
    TYPE t_oidnaci IS TABLE OF int_ocr_solic_credi.oid_naci%TYPE;
    TYPE t_codsexo IS TABLE OF int_ocr_solic_credi.cod_sexo%TYPE;
    TYPE t_ciudcodciuddomi IS TABLE OF int_ocr_solic_credi.ciud_cod_ciud_domi%TYPE;
    TYPE t_desvillapobldomi IS TABLE OF int_ocr_solic_credi.des_villa_pobl_domi%TYPE;
  
    ----------------------
    v_codpais                   t_codpais := t_codpais();
    v_codclie                   t_codclie := t_codclie();
    v_uniadmi                   t_uniadmi := t_uniadmi();
    v_tipingr                   t_tipingr := t_tipingr();
    v_codclierete               t_codclierete := t_codclierete();
    v_valape1                   t_valape1 := t_valape1();
    v_valape2                   t_valape2 := t_valape2();
    v_valnom1                   t_valnom1 := t_valnom1();
    v_valnom2                   t_valnom2 := t_valnom2();
    v_tipdocu                   t_tipdocu := t_tipdocu();
    v_numdocuiden               t_numdocuiden := t_numdocuiden();
    v_fecnaci                   t_fecnaci := t_fecnaci();
    v_indestacivi               t_indestacivi := t_indestacivi();
    v_indniveeduc               t_indniveeduc := t_indniveeduc();
    v_valcodisegm               t_valcodisegm := t_valcodisegm();
    v_valdireclie               t_valdireclie := t_valdireclie();
    v_valtelfclie               t_valtelfclie := t_valtelfclie();
    v_valceluclie               t_valceluclie := t_valceluclie();
    v_valcodiventdire           t_valcodiventdire := t_valcodiventdire();
    v_valmailclie               t_valmailclie := t_valmailclie();
    v_valdireentrclie           t_valdireentrclie := t_valdireentrclie();
    v_valteleentrclie           t_valteleentrclie := t_valteleentrclie();
    v_valceluentrclie           t_valceluentrclie := t_valceluentrclie();
    v_valnom1refefamiclie       t_valnom1refefamiclie := t_valnom1refefamiclie();
    v_valape1refefamiclie       t_valape1refefamiclie := t_valape1refefamiclie();
    v_valdirerefefamiclie       t_valdirerefefamiclie := t_valdirerefefamiclie();
    v_valtelerefefamiclie       t_valtelerefefamiclie := t_valtelerefefamiclie();
    v_valcelurefefamiclie       t_valcelurefefamiclie := t_valcelurefefamiclie();
    v_valtipovincrefefamiclie   t_valtipovincrefefamiclie := t_valtipovincrefefamiclie();
    v_valnom1refenofamiclie     t_valnom1refenofamiclie := t_valnom1refenofamiclie();
    v_valape1refenofamiclie     t_valape1refenofamiclie := t_valape1refenofamiclie();
    v_valdirerefenofamiclie     t_valdirerefenofamiclie := t_valdirerefenofamiclie();
    v_valtelerefenofamiclie     t_valtelerefenofamiclie := t_valtelerefenofamiclie();
    v_valcelurefenofamiclie     t_valcelurefenofamiclie := t_valcelurefenofamiclie();
    v_valtipovincrefenofamiclie t_valtipovincrefenofamiclie := t_valtipovincrefenofamiclie();
    v_valnom1fiad               t_valnom1fiad := t_valnom1fiad();
    v_valape1fiad               t_valape1fiad := t_valape1fiad();
    v_valdirefiad               t_valdirefiad := t_valdirefiad();
    v_valteflfiad               t_valteflfiad := t_valteflfiad();
    v_valcelufiad               t_valcelufiad := t_valcelufiad();
    v_tipdocufiad               t_tipdocufiad := t_tipdocufiad();
    v_coddocuidfi               t_coddocuidfi := t_coddocuidfi();
    v_valtipovincfiad           t_valtipovincfiad := t_valtipovincfiad();
    v_numdocu                   t_numdocu := t_numdocu();
    v_fecingreso                t_fecingreso := t_fecingreso();
  
    v_codperi          t_codperi := t_codperi();
    v_codprem          t_codprem := t_codprem();
    v_valobse          t_valobse := t_valobse();
    v_valape2fiad      t_valape2fiad := t_valape2fiad();
    v_valnom2fiad      t_valnom2fiad := t_valnom2fiad();
    v_oidnaci          t_oidnaci := t_oidnaci();
    v_codsexo          t_codsexo := t_codsexo();
    v_ciudcodciuddomi  t_ciudcodciuddomi := t_ciudcodciuddomi();
    v_tipviaclie       t_tipviaclie := t_tipviaclie();
    v_desvillapobldomi t_desvillapobldomi := t_desvillapobldomi();
    ----------------------
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle,
                                                 4000);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_codpais(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 2) THEN
                  v_codclie.extend;
                  v_codclie(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 3) THEN
                  v_uniadmi.extend;
                  v_uniadmi(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 4) THEN
                  v_tipingr.extend;
                
                  v_tipingr(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                
                ELSIF (posicion = 5) THEN
                  v_codclierete.extend;
                  v_codclierete(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 6) THEN
                  v_valape1.extend;
                  v_valape1(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 7) THEN
                  v_valape2.extend;
                  v_valape2(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 8) THEN
                  v_valnom1.extend;
                  v_valnom1(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 9) THEN
                  v_valnom2.extend;
                  v_valnom2(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                
                ELSIF (posicion = 10) THEN
                  v_tipdocu.extend;
                  v_tipdocu(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 11) THEN
                  v_numdocuiden.extend;
                  v_numdocuiden(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 12) THEN
                  v_fecnaci.extend;
                  v_fecnaci(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 13) THEN
                  v_indestacivi.extend;
                  v_indestacivi(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 14) THEN
                  v_indniveeduc.extend;
                  v_indniveeduc(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 15) THEN
                  v_valcodisegm.extend;
                  v_valcodisegm(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 16) THEN
                  v_valdireclie.extend;
                  v_valdireclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 17) THEN
                  v_valtelfclie.extend;
                  v_valtelfclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 18) THEN
                  v_valceluclie.extend;
                  v_valceluclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 19) THEN
                  v_valcodiventdire.extend;
                  v_valcodiventdire(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 20) THEN
                  v_valmailclie.extend;
                  v_valmailclie(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 21) THEN
                  v_valdireentrclie.extend;
                  v_valdireentrclie(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 22) THEN
                  v_valteleentrclie.extend;
                  v_valteleentrclie(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 23) THEN
                  v_valceluentrclie.extend;
                  v_valceluentrclie(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 24) THEN
                  v_valnom1refefamiclie.extend;
                  v_valnom1refefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 25) THEN
                  v_valape1refefamiclie.extend;
                  v_valape1refefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                
                ELSIF (posicion = 26) THEN
                  v_valdirerefefamiclie.extend;
                  v_valdirerefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 27) THEN
                  v_valtelerefefamiclie.extend;
                  v_valtelerefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 28) THEN
                  v_valcelurefefamiclie.extend;
                  v_valcelurefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                    inicio,
                                                                    longitud),
                                                             lscadena,
                                                             lsreplace));
                ELSIF (posicion = 29) THEN
                  v_valtipovincrefefamiclie.extend;
                  v_valtipovincrefefamiclie(i) := TRIM(translate(substr(lslinea,
                                                                        inicio,
                                                                        longitud),
                                                                 lscadena,
                                                                 lsreplace));
                
                ELSIF (posicion = 30) THEN
                  v_valnom1refenofamiclie.extend;
                  v_valnom1refenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                
                ELSIF (posicion = 31) THEN
                  v_valape1refenofamiclie.extend;
                  v_valape1refenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                
                ELSIF (posicion = 32) THEN
                  v_valdirerefenofamiclie.extend;
                  v_valdirerefenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                
                ELSIF (posicion = 33) THEN
                  v_valtelerefenofamiclie.extend;
                  v_valtelerefenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                ELSIF (posicion = 34) THEN
                  v_valcelurefenofamiclie.extend;
                  v_valcelurefenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                      inicio,
                                                                      longitud),
                                                               lscadena,
                                                               lsreplace));
                ELSIF (posicion = 35) THEN
                  v_valtipovincrefenofamiclie.extend;
                  v_valtipovincrefenofamiclie(i) := TRIM(translate(substr(lslinea,
                                                                          inicio,
                                                                          longitud),
                                                                   lscadena,
                                                                   lsreplace));
                ELSIF (posicion = 36) THEN
                  v_valnom1fiad.extend;
                  v_valnom1fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 37) THEN
                  v_valape1fiad.extend;
                  v_valape1fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 38) THEN
                  v_valdirefiad.extend;
                  v_valdirefiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                  -----
                ELSIF (posicion = 39) THEN
                  v_valteflfiad.extend;
                  v_valteflfiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 40) THEN
                  v_valcelufiad.extend;
                  v_valcelufiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 41) THEN
                  v_tipdocufiad.extend;
                  v_tipdocufiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 42) THEN
                  v_coddocuidfi.extend;
                  v_coddocuidfi(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 43) THEN
                  v_valtipovincfiad.extend;
                  v_valtipovincfiad(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 44) THEN
                  v_numdocu.extend;
                  v_numdocu(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 45) THEN
                  v_fecingreso.extend;
                  v_fecingreso(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 46) THEN
                  v_codperi.extend;
                  v_codperi(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 47) THEN
                  v_codprem.extend;
                  v_codprem(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 48) THEN
                  v_valobse.extend;
                  v_valobse(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 49) THEN
                  v_valape2fiad.extend;
                  v_valape2fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                ELSIF (posicion = 50) THEN
                  v_valnom2fiad.extend;
                  v_valnom2fiad(i) := TRIM(translate(substr(lslinea,
                                                            inicio,
                                                            longitud),
                                                     lscadena,
                                                     lsreplace));
                
                ELSIF (posicion = 51) THEN
                  v_oidnaci.extend;
                  v_oidnaci(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 52) THEN
                  v_codsexo.extend;
                  v_codsexo(i) := TRIM(translate(substr(lslinea,
                                                        inicio,
                                                        longitud),
                                                 lscadena,
                                                 lsreplace));
                ELSIF (posicion = 53) THEN
                  v_ciudcodciuddomi.extend;
                  v_ciudcodciuddomi(i) := TRIM(translate(substr(lslinea,
                                                                inicio,
                                                                longitud),
                                                         lscadena,
                                                         lsreplace));
                ELSIF (posicion = 54) THEN
                  v_tipviaclie.extend;
                  v_tipviaclie(i) := TRIM(translate(substr(lslinea,
                                                           inicio,
                                                           longitud),
                                                    lscadena,
                                                    lsreplace));
                ELSIF (posicion = 55) THEN
                  v_desvillapobldomi.extend;
                  v_desvillapobldomi(i) := TRIM(translate(substr(lslinea,
                                                                 inicio,
                                                                 longitud),
                                                          lscadena,
                                                          lsreplace));
                
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codpais.count
    
      INSERT INTO int_ocr_actua_datos
        (cod_pais,
         cod_clie,
         cod_unid_admi,
         tip_ingr,
         cod_clie_rete,
         val_ape1,
         val_ape2,
         val_nom1,
         val_nom2,
         fec_naci,
         num_docu_iden,
         ind_esta_civi,
         ind_nive_educ,
         val_dire_nuev,
         val_tele_nuev,
         val_celu_nuev,
         val_codi_vent_dire,
         val_nom1_refe_fami_clie,
         val_ape1_refe_fami_clie,
         val_dire_refe_fami_clie,
         val_tele_refe_fami_clie,
         val_tipo_vinc_refe_fami_clie,
         val_nom1_refe_nofa_clie,
         val_ape1_refe_nofa_clie,
         val_dire_refe_nofa_clie,
         val_tele_refe_nofa_clie,
         val_tipo_vinc_refe_nofa_clie,
         val_nom1_fiad,
         val_ape1_fiad,
         val_dire_fiad,
         val_tefl_fiad,
         val_tipo_vinc_fiad,
         cod_docu_idfi,
         val_dire_entre_clie,
         val_tele_entre_clie,
         val_mail_nuev,
         val_codi_segm,
         val_celu_refe_fami_clie,
         val_celu_refe_nofa_clie,
         val_celu_fiad,
         val_celu_entre_clie,
         tip_docu_fiad,
         cod_tipo_docu,
         num_docu,
         fec_hora_crea_soli,
         cod_depa,
         cod_prov,
         cod_dist,
         cod_cent_pobl,
         ind_orig,
         cod_peri,
         cod_prem,
         val_obse,
         val_ape2_fiad,
         val_nom2_fiad,
         oid_naci,
         cod_sexo,
         ciud_cod_ciud_domi,
         ciud_cod_ugeo_regi_domi,
         tip_via_clie,
         des_villa_pobl_domi)
      VALUES
        (v_codpais(i),
         v_codclie(i),
         v_uniadmi(i),
         v_tipingr(i),
         v_codclierete(i),
         v_valape1(i),
         v_valape2(i),
         v_valnom1(i),
         v_valnom2(i),
         v_fecnaci(i),
         v_numdocuiden(i),
         v_indestacivi(i),
         v_indniveeduc(i),
         v_valdireclie(i),
         v_valtelfclie(i),
         v_valceluclie(i),
         v_valcodiventdire(i),
         v_valnom1refefamiclie(i),
         v_valape1refefamiclie(i),
         v_valdirerefefamiclie(i),
         v_valtelerefefamiclie(i),
         v_valtipovincrefefamiclie(i),
         v_valnom1refenofamiclie(i),
         v_valape1refenofamiclie(i),
         v_valdirerefenofamiclie(i),
         v_valtelerefenofamiclie(i),
         v_valtipovincrefenofamiclie(i),
         v_valnom1fiad(i),
         v_valape1fiad(i),
         v_valdirefiad(i),
         v_valteflfiad(i),
         v_valtipovincfiad(i),
         v_coddocuidfi(i),
         v_valdireentrclie(i),
         v_valteleentrclie(i),
         v_valmailclie(i),
         v_valcodisegm(i),
         v_valcelurefefamiclie(i),
         v_valcelurefenofamiclie(i),
         v_valcelufiad(i),
         v_valceluentrclie(i),
         v_tipdocufiad(i),
         v_tipdocu(i),
         v_numdocu(i),
         v_fecingreso(i),
         substr(v_valcodisegm(i), 1, 6),
         substr(v_valcodisegm(i), 7, 6),
         substr(v_valcodisegm(i), 13, 6),
         substr(v_valcodisegm(i), 19, 6),
         psindicadororigen,
         v_codperi(i),
         v_codprem(i),
         v_valobse(i),
         v_valape2fiad(i),
         v_valnom2fiad(i),
         v_oidnaci(i),
         v_codsexo(i),
         v_ciudcodciuddomi(i),
         substr(v_uniadmi(i), 1, 2),
         v_tipviaclie(i),
         v_desvillapobldomi(i));
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_recep_acdat_web: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_acdat_web;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de cliente a web
  Fecha Creacion    : 19/06/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_clien_web
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz(psindicadoractivo VARCHAR2) IS
      SELECT pscodigopais prefijopais,
             a.cod_clie codigoconsultora,
             '1' tipocontacto,
             NULL codigoclienterecom,
             a.val_ape1 apellidopaterno,
             a.val_ape2 apellidomaterno,
             a.val_nom1 nombre,
             a.val_nom2 nombre2,
             lpad(c.tdoc_oid_tipo_docu - 2000, 2, '0') tipodocumentoconsultora,
             to_char(b.fec_naci, 'ddmmyyyy') fechanacimiento,
             lpad(nvl(b.escv_oid_esta_civi, 2001) - 2000, 2, '0') estadocivil,
             lpad(nvl(b.nied_oid_nive_estu, 2001) - 2000, 2, '0') niveleducativo,
             decode((SELECT y.cod_unid_geog
                      FROM mae_clien_direc y
                     WHERE y.tidc_oid_tipo_dire = 2007
                       AND y.ind_elim = 0
                       AND y.clie_oid_clie = a.oid_clie),
                    NULL,
                    (SELECT y.cod_unid_geog
                       FROM mae_clien_direc y
                      WHERE y.ind_dire_ppal = 1
                        AND y.ind_elim = 0
                        AND y.clie_oid_clie = a.oid_clie),
                    (SELECT y.cod_unid_geog
                       FROM mae_clien_direc y
                      WHERE y.tidc_oid_tipo_dire = 2007
                        AND y.ind_elim = 0
                        AND y.clie_oid_clie = a.oid_clie)) codigosegmento,
             '0' codigoventadirecta,
             (SELECT val_text_comu
                FROM mae_clien_comun
               WHERE ticm_oid_tipo_comu = 1
                 AND clie_oid_clie = a.oid_clie) telefono,
             decode((SELECT y.val_nomb_via || ' ' || y.num_ppal || ' ' ||
                           y.val_obse
                      FROM mae_clien_direc y
                     WHERE y.tidc_oid_tipo_dire = 2007
                       AND y.ind_elim = 0
                       AND y.clie_oid_clie = a.oid_clie),
                    NULL,
                    (SELECT y.val_nomb_via || ' ' || y.num_ppal || ' ' ||
                            y.val_obse
                       FROM mae_clien_direc y
                      WHERE y.ind_dire_ppal = 1
                        AND y.ind_elim = 0
                        AND y.clie_oid_clie = a.oid_clie),
                    (SELECT y.val_nomb_via || ' ' || y.num_ppal || ' ' ||
                            y.val_obse
                       FROM mae_clien_direc y
                      WHERE y.tidc_oid_tipo_dire = 2007
                        AND y.ind_elim = 0
                        AND y.clie_oid_clie = a.oid_clie)) direccionentrega,
             (SELECT val_text_comu
                FROM mae_clien_comun
               WHERE ticm_oid_tipo_comu = 2002
                 AND clie_oid_clie = a.oid_clie) telefonoentrega,
             (SELECT val_text_comu
                FROM mae_clien_comun
               WHERE ticm_oid_tipo_comu = 2003
                 AND clie_oid_clie = a.oid_clie) telefono2entrega,
             (SELECT x.val_nom1 || ' ' || x.val_nom2
                FROM mae_refer x
               WHERE x.tipo_refe = 1
                 AND x.cod_clie = a.cod_clie) nombrereferenciafamiliar,
             (SELECT x.val_ape1 || ' ' || x.val_ape2
                FROM mae_refer x
               WHERE x.tipo_refe = 1
                 AND x.cod_clie = a.cod_clie) apellidoreferenciafamiliar,
             (SELECT x.val_dire
                FROM mae_refer x
               WHERE x.tipo_refe = 1
                 AND x.cod_clie = a.cod_clie) direccionreferenciafamiliar,
             (SELECT x.val_telf
                FROM mae_refer x
               WHERE x.tipo_refe = 1
                 AND x.cod_clie = a.cod_clie) telefonoreferenciafamiliar,
             (SELECT x.val_telf_trab
                FROM mae_refer x
               WHERE x.tipo_refe = 1
                 AND x.cod_clie = a.cod_clie) telefono2familiarreferencia,
             (SELECT x.tipo_vincu
                FROM mae_refer x
               WHERE x.tipo_refe = 1
                 AND x.cod_clie = a.cod_clie) tipovinculoreferenciafamiliar,
             (SELECT x.val_nom1 || ' ' || x.val_nom2
                FROM mae_refer x
               WHERE x.tipo_refe = 2
                 AND x.cod_clie = a.cod_clie) nombrereferencianofamiiar,
             (SELECT x.val_ape1 || ' ' || x.val_ape2
                FROM mae_refer x
               WHERE x.tipo_refe = 2
                 AND x.cod_clie = a.cod_clie) apellidoreferencianofamiliar,
             (SELECT x.val_dire
                FROM mae_refer x
               WHERE x.tipo_refe = 2
                 AND x.cod_clie = a.cod_clie) direccionreferencianofamiliar,
             (SELECT x.val_telf
                FROM mae_refer x
               WHERE x.tipo_refe = 2
                 AND x.cod_clie = a.cod_clie) telefonoreferencianofamiliar,
             (SELECT x.val_telf_trab
                FROM mae_refer x
               WHERE x.tipo_refe = 2
                 AND x.cod_clie = a.cod_clie) telefono2nofamiliarreferencia,
             (SELECT x.tipo_vincu
                FROM mae_refer x
               WHERE x.tipo_refe = 2
                 AND x.cod_clie = a.cod_clie) tipovinculorefenofamiliar,
             (SELECT x.val_nom1 || ' ' || x.val_nom2
                FROM mae_refer x
               WHERE x.tipo_refe = 3
                 AND x.cod_clie = a.cod_clie) nombrefiadorreferencia,
             (SELECT x.val_ape1 || ' ' || x.val_ape2
                FROM mae_refer x
               WHERE x.tipo_refe = 3
                 AND x.cod_clie = a.cod_clie) apellidofiadorreferencia,
             (SELECT x.val_dire
                FROM mae_refer x
               WHERE x.tipo_refe = 3
                 AND x.cod_clie = a.cod_clie) direccionfiadorreferencia,
             (SELECT x.val_telf
                FROM mae_refer x
               WHERE x.tipo_refe = 3
                 AND x.cod_clie = a.cod_clie) telefonofiadorreferencia,
             (SELECT x.val_telf_trab
                FROM mae_refer x
               WHERE x.tipo_refe = 3
                 AND x.cod_clie = a.cod_clie) telefono2fiadorreferencia,
             (SELECT x.tipo_docu_refe
                FROM mae_refer x
               WHERE x.tipo_refe = 3
                 AND x.cod_clie = a.cod_clie) tipodocumentofiadorreferencia,
             (SELECT x.num_docu_refe
                FROM mae_refer x
               WHERE x.tipo_refe = 3
                 AND x.cod_clie = a.cod_clie) nrodocumentofiadorreferencia,
             (SELECT x.tipo_vincu
                FROM mae_refer x
               WHERE x.tipo_refe = 3
                 AND x.cod_clie = a.cod_clie) tipovinculofiadorreferencia,
             NULL cod_peri,
             NULL cod_prem,
             NULL val_obse,
             NULL val_ape2,
             NULL val_nom1,
             b.snon_oid_naci oid_naci,
             a.cod_sexo cod_sexo,
             (SELECT ciud_cod_ciud
                FROM mae_clien_direc
               WHERE ind_dire_ppal = 1
                 AND ind_elim = 0) ciud_cod_ciud_domi,
             nvl((SELECT cod_tipo_via
                   FROM seg_tipo_via,
                        mae_clien_direc
                  WHERE oid_tipo_via = tivi_oid_tipo_via
                    AND ind_dire_ppal = 1),
                 '99') tipo_via,
             (SELECT des_villa_pobl
                FROM mae_clien_direc
               WHERE ind_dire_ppal = 1
                 AND ind_elim = 0) des_villa_pobl_domi
        FROM mae_clien             a,
             mae_clien_datos_adici b,
             mae_clien_ident       c
       WHERE a.oid_clie = b.clie_oid_clie
         AND b.ind_acti = 1
         AND a.oid_clie = c.clie_oid_clie
         AND c.val_iden_docu_prin = 1
         AND a.oid_clie IN
             (SELECT oid_clie
                FROM mae_clien
               WHERE fec_ulti_actu >=
                     (SELECT MAX(fec_ipro)
                        FROM bas_histo_lotes
                       WHERE inte_cod_inte = 'OCR-47')
              UNION
              SELECT clie_oid_clie
                FROM mae_clien_comun
               WHERE fec_ulti_actu >=
                     (SELECT MAX(fec_ipro)
                        FROM bas_histo_lotes
                       WHERE inte_cod_inte = 'OCR-47')
              UNION
              SELECT clie_oid_clie
                FROM mae_clien_direc
               WHERE fec_ulti_actu >=
                     (SELECT MAX(fec_ipro)
                        FROM bas_histo_lotes
                       WHERE inte_cod_inte = 'OCR-47')
              UNION
              SELECT clie_oid_clie
                FROM mae_clien_ident
               WHERE fec_ulti_actu >=
                     (SELECT MAX(fec_ipro)
                        FROM bas_histo_lotes
                       WHERE inte_cod_inte = 'OCR-47'));
  
    TYPE interfazrec IS RECORD(
      prefijopais                   seg_pais.cod_pais%TYPE,
      codigoconsultora              mae_clien.cod_clie%TYPE,
      tipocontacto                  VARCHAR(1),
      codigoclienterecom            mae_clien.cod_clie%TYPE,
      apellidopaterno               mae_clien.val_ape1%TYPE,
      apellidomaterno               mae_clien.val_ape2%TYPE,
      nombre                        mae_clien.val_nom1%TYPE,
      nombre2                       mae_clien.val_nom2%TYPE,
      tipodocumentoconsultora       VARCHAR(2),
      fechanacimiento               VARCHAR(8),
      estadocivil                   VARCHAR2(2),
      niveleducativo                VARCHAR2(2),
      codigosegmento                mae_clien_direc.cod_unid_geog%TYPE,
      codigoventadirecta            VARCHAR(1),
      telefono                      mae_clien_comun.val_text_comu%TYPE,
      direccionentrega              VARCHAR(200),
      telefonoentrega               mae_clien_comun.val_text_comu%TYPE,
      telefono2entrega              mae_clien_comun.val_text_comu%TYPE,
      nombrereferenciafamiliar      VARCHAR(100),
      apellidoreferenciafamiliar    VARCHAR(100),
      direccionreferenciafamiliar   mae_refer.val_dire%TYPE,
      telefonoreferenciafamiliar    mae_refer.val_telf%TYPE,
      telefono2familiarreferencia   mae_refer.val_telf_trab%TYPE,
      tipovinculoreferenciafamiliar mae_refer.tipo_vincu%TYPE,
      nombrereferencianofamiiar     VARCHAR(100),
      apellidoreferencianofamiliar  VARCHAR(100),
      direccionreferencianofamiliar mae_refer.val_dire%TYPE,
      telefonoreferencianofamiliar  mae_refer.val_telf%TYPE,
      telefono2nofamiliarreferencia mae_refer.val_telf_trab%TYPE,
      tipovinculorefenofamiliar     mae_refer.tipo_vincu%TYPE,
      nombrefiadorreferencia        VARCHAR(100),
      apellidofiadorreferencia      VARCHAR(100),
      direccionfiadorreferencia     mae_refer.val_dire%TYPE,
      telefonofiadorreferencia      mae_refer.val_telf%TYPE,
      telefono2fiadorreferencia     mae_refer.val_telf_trab%TYPE,
      tipodocumentofiadorreferencia mae_refer.tipo_docu_refe%TYPE,
      nrodocumentofiadorreferencia  mae_refer.num_docu_refe%TYPE,
      tipovinculofiadorreferencia   mae_refer.tipo_vincu%TYPE,
      codperi                       VARCHAR2(6),
      codprem                       VARCHAR2(5),
      valobse                       mae_clien_direc.val_obse%TYPE,
      valape2                       mae_refer.val_ape2%TYPE,
      valnom2                       mae_refer.val_nom2%TYPE,
      oidnaci                       mae_clien_datos_adici.snon_oid_naci%TYPE,
      codsexo                       VARCHAR2(1),
      ciudcodciuddomi               mae_clien_direc.ciud_cod_ciud%TYPE,
      tipovia                       VARCHAR2(2),
      desvillapobldomi              mae_clien_direc.des_villa_pobl%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo   VARCHAR2(50);
    psindicadoractivo VARCHAR2(1);
    lbabrirutlfile    BOOLEAN;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  BEGIN
    psindicadoractivo := '1';
  
    lbabrirutlfile := TRUE;
    OPEN c_interfaz(psindicadoractivo);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := translate(interfazrecord(x).prefijopais,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).codigoconsultora,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).tipocontacto,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).codigoclienterecom,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).apellidopaterno,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).apellidomaterno,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).nombre,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).nombre2,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).tipodocumentoconsultora,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).fechanacimiento,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).estadocivil,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).niveleducativo,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).codigosegmento,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).codigoventadirecta,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).telefono,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).direccionentrega,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).telefonoentrega,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).telefono2entrega,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).nombrereferenciafamiliar,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).apellidoreferenciafamiliar,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).direccionreferenciafamiliar,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).telefonoreferenciafamiliar,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).telefono2familiarreferencia,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x)
                               .tipovinculoreferenciafamiliar,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).nombrereferencianofamiiar,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x)
                               .apellidoreferencianofamiliar,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x)
                               .direccionreferencianofamiliar,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x)
                               .telefonoreferencianofamiliar,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x)
                               .telefono2nofamiliarreferencia,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).tipovinculorefenofamiliar,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).nombrefiadorreferencia,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).apellidofiadorreferencia,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).direccionfiadorreferencia,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).telefonofiadorreferencia,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).telefono2fiadorreferencia,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x)
                               .tipodocumentofiadorreferencia,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x)
                               .nrodocumentofiadorreferencia,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).tipovinculofiadorreferencia,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).codperi,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).codprem,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).valobse,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).valape2,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).valnom2,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).oidnaci,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).codsexo,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).ciudcodciuddomi,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).tipovia,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).desvillapobldomi,
                               lscadena,
                               lsreplace);
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'INT_PR_OCR_ENVI_CLIEN_WEB: ' || ls_sqlerrm);
  END int_pr_ocr_envi_clien_web;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de territorios web
  Fecha Creacion    : 15/09/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_terri_web
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT DISTINCT f.cod_pais prefijopais,
                      b.cod_zona codigozona,
                      a.cod_terr codigoterritorio,
                      e.orde_1 || e.orde_2 || e.orde_3 codigosegmento
        FROM zon_terri             a,
             zon_zona              b,
             zon_secci             c,
             zon_terri_admin       d,
             zon_valor_estru_geopo e,
             seg_pais              f
       WHERE a.oid_terr = d.terr_oid_terr
         AND d.zscc_oid_secc = c.oid_secc
         AND c.zzon_oid_zona = b.oid_zona
         AND a.vepo_oid_valo_estr_geop = e.oid_valo_estr_geop
         AND d.ind_borr = 0
         AND a.pais_oid_pais = f.oid_pais;
  
    TYPE interfazrec IS RECORD(
      prefijopais      seg_pais.cod_pais%TYPE,
      codigozona       zon_zona.cod_zona%TYPE,
      codigoterritorio zon_terri.cod_terr%TYPE,
      codigosegmento   VARCHAR2(18));
  
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  BEGIN
  
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := translate(interfazrecord(x).prefijopais,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).codigozona,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).codigoterritorio,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).codigosegmento,
                               lscadena,
                               lsreplace);
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'INT_PR_OCR_ENVI_TERRI_WEB: ' || ls_sqlerrm);
  END int_pr_ocr_envi_terri_web;
  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de  segmentos web
  Fecha Creacion    : 15/09/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_segme_web
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT f.cod_pais prefijopais,
             e.orde_1 || nvl(e.orde_2, '000000') || nvl(e.orde_3, '000000') codigosegmento,
             (SELECT des_geog
                FROM zon_valor_estru_geopo
               WHERE orde_1 = e.orde_1
                 AND orde_2 IS NULL
                 AND orde_3 IS NULL
                 AND orde_4 IS NULL
                 AND ind_acti = 1
                 AND ind_borr = 0) estado,
             (SELECT des_geog
                FROM zon_valor_estru_geopo
               WHERE orde_1 = e.orde_1
                 AND orde_2 = e.orde_2
                 AND orde_3 IS NULL
                 AND orde_4 IS NULL
                 AND ind_acti = 1
                 AND ind_borr = 0) municipio,
             (SELECT des_geog
                FROM zon_valor_estru_geopo
               WHERE orde_1 = e.orde_1
                 AND orde_2 = e.orde_2
                 AND orde_3 = e.orde_3
                 AND orde_4 IS NULL
                 AND ind_acti = 1
                 AND ind_borr = 0) parroquia
        FROM zon_valor_estru_geopo e,
             seg_pais              f
       WHERE e.pais_oid_pais = f.oid_pais;
  
    TYPE interfazrec IS RECORD(
      prefijopais    seg_pais.cod_pais%TYPE,
      codigosegmento VARCHAR2(24),
      estado         zon_valor_estru_geopo.des_geog%TYPE,
      municipio      zon_valor_estru_geopo.des_geog%TYPE,
      parroquia      zon_valor_estru_geopo.des_geog%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  BEGIN
  
    lbabrirutlfile := TRUE;
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := translate(interfazrecord(x).prefijopais,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).codigosegmento,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).estado,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).municipio,
                               lscadena,
                               lsreplace) || ';' ||
                     translate(interfazrecord(x).parroquia,
                               lscadena,
                               lsreplace);
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'INT_PR_OCR_ENVI_SEGME_WEB: ' || ls_sqlerrm);
  END int_pr_ocr_envi_segme_web;
  /***************************************************************************
  Descripcion       : Actualiza el codigo de cliente de las tablas INT_SOLIC_CABEC
                      y  INT_SOLIC_POSIC si existen en MAE_CLIEN, la comparacion
                      de codigos es numerica
  Fecha Creacion    : 31/01/2012
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_actua_clien
  (
    pscodigopais      VARCHAR2,
    psindactuacliente VARCHAR2,
    psnumerolotesto   VARCHAR2
  ) IS
  
    CURSOR c_cliente IS
      SELECT cab.cod_clie codcliearch,
             cab.tip_soli,
             cab.cod_clie codclieorig,
             '0' ind_docu_iden,
             num_docu,
             oid_cab,
             sta_proc
        FROM int_solic_cabec cab
       WHERE cab.num_lote_sto = psnumerolotesto;
  
    TYPE t_codcliearch IS TABLE OF int_solic_cabec.cod_clie%TYPE;
    TYPE t_tip_soli IS TABLE OF int_solic_cabec.tip_soli%TYPE;
    TYPE t_codclieorig IS TABLE OF int_solic_cabec.cod_clie%TYPE;
    TYPE t_ind_docu_iden IS TABLE OF int_solic_cabec.ind_docu_iden%TYPE;
    TYPE t_num_docu IS TABLE OF int_solic_cabec.num_docu%TYPE;
    TYPE t_oid_cab IS TABLE OF int_solic_cabec.oid_cab%TYPE;
    TYPE t_sta_proc IS TABLE OF int_solic_cabec.sta_proc%TYPE;
  
    v_codcliearch   t_codcliearch;
    v_tip_soli      t_tip_soli;
    v_codclieorig   t_codclieorig;
    v_ind_docu_iden t_ind_docu_iden;
    v_num_docu      t_num_docu;
    v_oid_cab       t_oid_cab;
    v_sta_proc      t_sta_proc;
  
    i BINARY_INTEGER := 0;
  
    lnnumerodias sto_param_gener_occrr.val_param%TYPE;
  
  BEGIN
  
    lnnumerodias := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         'STO_NUM_DIAS_ATRAS');
  
    OPEN c_cliente;
    LOOP
      FETCH c_cliente BULK COLLECT
        INTO v_codcliearch,
             v_tip_soli,
             v_codclieorig,
             v_ind_docu_iden,
             v_num_docu,
             v_oid_cab,
             v_sta_proc LIMIT w_filas;
    
      IF v_codcliearch.count > 0 THEN
      
        FOR i IN v_codcliearch.first .. v_codcliearch.last
        LOOP
          /*Si el pedido esta rechazado se eliminan todos sus detalles
          y el codgo se le coloca 'R' antes del codigo de consultora*/
          IF v_sta_proc(i) = 'R' THEN
            v_codclieorig(i) := 'R' || v_codcliearch(i);
            DELETE int_solic_posic isp
             WHERE num_docu = v_num_docu(i)
               AND num_lote_sto = psnumerolotesto
               AND isp.sta_proc = 'R';
          
            UPDATE int_solic_cabec c
               SET tip_soli      = 'SOC',
                   cod_clie      = v_codclieorig(i),
                   ind_docu_iden = v_ind_docu_iden(i)
             WHERE c.oid_cab = v_oid_cab(i)
               AND c.num_lote_sto = psnumerolotesto;
          
          ELSE
          
            /*Si el pedido viene con codigo de consultora nulo(0) se coloca una 'C'
            al preimpreso o el oid generando en caso que este ultimo valor sea nulo*/
            IF v_codcliearch(i) = '0' THEN
              IF v_num_docu(i) IS NOT NULL THEN
                v_codclieorig(i) := 'C' || v_num_docu(i);
              
              ELSE
                v_codclieorig(i) := 'C' || v_oid_cab(i);
              END IF;
            
              UPDATE int_solic_cabec c
                 SET tip_soli      = 'SOC',
                     cod_clie      = v_codclieorig(i),
                     ind_docu_iden = v_ind_docu_iden(i)
               WHERE c.cod_clie = v_codcliearch(i)
                 AND c.num_lote_sto = psnumerolotesto
                 AND c.num_docu = v_num_docu(i);
            
              UPDATE int_solic_posic c
                 SET cod_clie = v_codclieorig(i)
               WHERE c.cod_clie = v_codcliearch(i)
                 AND c.num_lote_sto = psnumerolotesto
                 AND c.num_docu = v_num_docu(i);
            ELSE
              /*Si el pedido es de tipo 'SOCN' se busca el campo codigo de consultora
              como documento de Identidad en caso no encontrase se coloca una 'C'*/
              IF v_tip_soli(i) = 'SOCN' THEN
              
                    SELECT MAX(c.cod_clie)
                      INTO v_codclieorig(i)
                      FROM mae_clien_ident i,
                           mae_clien       c
                     WHERE c.oid_clie = i.clie_oid_clie
                       AND i.tdoc_oid_tipo_docu = 2001
                       AND i.val_iden_docu_prin = 1
                       AND ltrim(i.num_docu_iden, '0') =
                           ltrim(v_codcliearch(i), '0');
              
                IF v_codclieorig(i) IS NULL THEN
                  v_codclieorig(i) := 'C' || v_codcliearch(i);
                END IF;
                v_ind_docu_iden(i) := '1';
              
              ELSE
              
                /*Se valida que el cliente exista si existe se mantiene el valor del cliente*/
                BEGIN
                  SELECT a.cod_clie
                    INTO v_codclieorig(i)
                    FROM mae_clien a
                   WHERE ltrim(a.cod_clie, '0') =
                         ltrim(v_codcliearch(i), '0');
                EXCEPTION
                  WHEN no_data_found THEN
                    /*Si el codigo de consultora no existe se valida el ind. de actualizacion,
                    Si es '1' se busca el codigo como numero de documento de identidad*/
                    IF psindactuacliente = '1' THEN
                    
                        SELECT MAX(c.cod_clie)
                              INTO v_codclieorig(i)
                              FROM mae_clien_ident i,
                                   mae_clien       c
                             WHERE c.oid_clie = i.clie_oid_clie
                               AND i.tdoc_oid_tipo_docu = 2001
                               AND i.num_docu_iden = v_codcliearch(i)
                               AND i.val_iden_docu_prin = 1
                               AND i.fec_ulti_actu >=
                                   trunc(SYSDATE) - lnnumerodias;                      
                    
                      IF v_codclieorig(i) IS NULL THEN
                        
                            SELECT MAX(c.cod_clie)
                              INTO v_codclieorig(i)
                              FROM mae_clien_ident i,
                                   mae_clien       c
                             WHERE c.oid_clie = i.clie_oid_clie
                               --AND i.tdoc_oid_tipo_docu = 2001
                               AND i.num_docu_iden = v_codcliearch(i)
                               AND i.val_iden_docu_prin = 1
                               AND i.fec_ulti_actu >=
                                   trunc(SYSDATE) - lnnumerodias;   
                                   
                             IF v_codclieorig(i) IS NULL THEN
                      
                                v_codclieorig(i) := 'C' || v_codcliearch(i);
                                
                             END IF;
                      END IF;
                      /*Si el codigo de consultora no existe se valida el ind. de actualizacion,
                      Si es '2' se busca el codigo como numero de preimpreso en solicitud de credito*/
                    ELSIF psindactuacliente = '2' THEN
                      BEGIN
                        SELECT b.cod_clie
                          INTO v_codclieorig(i)
                          FROM sto_docum_digit       b,
                               int_solic_conso_credi c
                         WHERE c.sec_nume_docu = b.sec_nume_docu
                           AND c.num_lote = b.num_lote
                           AND c.num_docu = v_codcliearch(i)
                           AND b.fec_modi >= trunc(SYSDATE) - lnnumerodias
                           AND b.ind_envi = '1'
                           AND rownum = 1;
                      EXCEPTION
                        WHEN no_data_found THEN
                          v_codclieorig(i) := 'C' || v_codcliearch(i);
                      END;
                    ELSIF psindactuacliente = '3' THEN
                      BEGIN
                        SELECT b.cod_clie
                          INTO v_codclieorig(i)
                          FROM sto_docum_digit       b,
                               int_solic_conso_credi c
                         WHERE c.sec_nume_docu = b.sec_nume_docu
                           AND c.num_lote = b.num_lote
                           AND ltrim(c.num_docu, '0') =
                               ltrim(v_codcliearch(i), '0')
                           AND b.fec_modi >= trunc(SYSDATE) - lnnumerodias
                           AND b.ind_envi = '1';
                      EXCEPTION
                        WHEN no_data_found THEN
                          v_codclieorig(i) := 'C' || v_codcliearch(i);
                      END;
                    ELSIF psindactuacliente = '4' THEN
                                      
                            SELECT MAX(b.cod_clie)
                              INTO v_codclieorig(i)
                              FROM mae_clien_ident a,
                                   mae_clien       b
                             WHERE b.oid_clie = a.clie_oid_clie
                               AND a.tdoc_oid_tipo_docu = 2001
                               AND ltrim(a.num_docu_iden, '0') =
                                   ltrim(v_codcliearch(i), '0')
                               AND a.val_iden_docu_prin = 1
                               AND a.fec_ulti_actu >=
                                   trunc(SYSDATE) - lnnumerodias;
                            
                      IF v_codclieorig(i) IS NULL THEN
                        v_codclieorig(i) := 'C' || v_codcliearch(i);
                      END IF;
                    ELSIF psindactuacliente = '5' THEN  
                        
                        SELECT MAX(b.cod_clie)
                        INTO v_codclieorig(i)
                        FROM mae_clien_ident a,
                             mae_clien       b
                       WHERE b.oid_clie = a.clie_oid_clie
                         AND a.tdoc_oid_tipo_docu = 2001
                         AND trim( translate( ltrim(a.num_docu_iden, '0'),'ABCDEFGHIJKLMNOPQRSTUVWXYZ' ,'                          ')) =
                             trim( ltrim(v_codcliearch(i), '0'))
                         AND a.val_iden_docu_prin = 1
                         AND a.fec_ulti_actu >=
                             trunc(SYSDATE) - lnnumerodias;
                      IF v_codclieorig(i) IS NULL THEN
                        v_codclieorig(i) := 'C' || v_codcliearch(i);
                      END IF;
                    ELSE
                      v_codclieorig(i) := 'C' || v_codcliearch(i);
                    END IF;
                  
                END;
              END IF;
              IF v_codclieorig(i) != v_codcliearch(i) THEN
                UPDATE int_solic_cabec c
                   SET tip_soli      = 'SOC',
                       cod_clie      = v_codclieorig(i),
                       ind_docu_iden = v_ind_docu_iden(i)
                 WHERE c.cod_clie = v_codcliearch(i)
                   AND c.num_lote_sto = psnumerolotesto;
              
                UPDATE int_solic_posic c
                   SET cod_clie = v_codclieorig(i)
                 WHERE c.cod_clie = v_codcliearch(i)
                   AND c.num_lote_sto = psnumerolotesto;
              END IF;
            END IF;
          
          END IF;
        END LOOP;
      
      END IF;
      EXIT WHEN c_cliente%NOTFOUND;
    
    END LOOP;
    CLOSE c_cliente;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_ACTUA_CLIEN: ' || ls_sqlerrm);
    
  END ocr_actua_clien;

  /***************************************************************************
  Descripcion       : Actualiza el codigo de cliente de las tablas INT_SOLIC_CONSO_CABEC
                      e INT_SOLIC_CONSO_DETAL si existen en un preimpreso anterior o
                      si existe el documento de identidad
  Fecha Creacion    : 29/09/2010
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ocr_actua_clien_valid_pedid
  (
    pscodigopais      VARCHAR2,
    psindactuacliente VARCHAR2
  ) IS
    CURSOR c_clientenoexistente(vnnumerodias NUMBER) IS
      SELECT *
        FROM (SELECT cab.sec_nume_docu,
                     cab.num_lote,
                     cab.cod_clie,
                     CASE
                     /*SI ES SOCN*/
                       WHEN cab.ind_docu_iden = '1' THEN
                        (SELECT MAX(c.cod_clie)
                           FROM mae_clien_ident i,
                                mae_clien       c
                          WHERE c.oid_clie = i.clie_oid_clie
                            AND i.tdoc_oid_tipo_docu = 2001
                            AND i.val_iden_docu_prin = 1
                            AND ltrim(i.num_docu_iden, '0') =
                                ltrim(ltrim(cab.cod_clie, 'C'), '0'))
                     /*SI NO ES SOCN*/
                       WHEN cab.ind_docu_iden = '0' AND
                            psindactuacliente = '1' THEN
                        (SELECT MAX(c.cod_clie)
                           FROM mae_clien_ident i,
                                mae_clien       c
                          WHERE c.oid_clie = i.clie_oid_clie
                            AND i.tdoc_oid_tipo_docu = 2001
                            AND i.num_docu_iden = ltrim(cab.cod_clie, 'C')
                            AND i.val_iden_docu_prin = 1
                            AND i.fec_ulti_actu >=
                                trunc(SYSDATE) - vnnumerodias)
                       WHEN cab.ind_docu_iden = '0' AND
                            psindactuacliente = '2' THEN
                        (SELECT b.cod_clie
                           FROM sto_docum_digit       b,
                                int_solic_conso_credi c
                          WHERE c.sec_nume_docu = b.sec_nume_docu
                            AND c.num_lote = b.num_lote
                            AND b.fec_modi >= trunc(SYSDATE) - vnnumerodias
                            AND b.ind_envi = '1'
                            AND c.num_docu = ltrim(cab.cod_clie, 'C'))
                       WHEN psindactuacliente = '3' THEN
                        (SELECT b.cod_clie
                           FROM sto_docum_digit       b,
                                int_solic_conso_credi c
                          WHERE c.sec_nume_docu = b.sec_nume_docu
                            AND c.num_lote = b.num_lote
                            AND b.fec_modi >= trunc(SYSDATE) - vnnumerodias
                            AND b.ind_envi = '1'
                            AND b.cod_tipo_docu = 'SCC'
                            AND ltrim(c.num_docu, '0') =
                                ltrim(ltrim(cab.cod_clie, 'C'), '0'))
                       WHEN cab.ind_docu_iden = '0' AND
                            psindactuacliente = '4' THEN
                        (SELECT MAX(b.cod_clie)
                           FROM mae_clien_ident a,
                                mae_clien       b
                          WHERE b.oid_clie = a.clie_oid_clie
                            AND a.tdoc_oid_tipo_docu = 2001
                            AND a.val_iden_docu_prin = 1
                            AND a.fec_ulti_actu >=
                                trunc(SYSDATE) - vnnumerodias
                            AND ltrim(a.num_docu_iden, '0') =
                                ltrim(ltrim(cab.cod_clie, 'C'), '0'))
                       ELSE
                        cab.cod_clie
                     END cod_clie_orig
                FROM int_solic_conso_cabec cab
               WHERE cab.cod_clie LIKE 'C%')
       WHERE cod_clie_orig IS NOT NULL;
  
    TYPE t_secnumedocu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_codclieorig IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
  
    v_secnumedocu t_secnumedocu;
    v_numlote     t_numlote;
    v_codclie     t_codclie;
    v_codclieorig t_codclieorig;
  
    i            BINARY_INTEGER := 0;
    lnnumerodias sto_param_gener_occrr.val_param%TYPE;
  
  BEGIN
  
    lnnumerodias := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         'STO_NUM_DIAS_ATRAS');
  
    OPEN c_clientenoexistente(lnnumerodias);
    LOOP
      FETCH c_clientenoexistente BULK COLLECT
        INTO v_secnumedocu,
             v_numlote,
             v_codclie,
             v_codclieorig LIMIT w_filas;
    
      IF v_codclieorig.count > 0 THEN
        /*BORRANDO MICAS*/
        FORALL i IN 1 .. v_codclie.count
          DELETE sto_audit_excep a
           WHERE EXISTS (SELECT 1
                    FROM int_solic_conso_cabec c
                   WHERE c.cod_clie = v_codclieorig(i)
                     AND c.sec_nume_docu = a.sec_nume_docu
                     AND c.num_lote = a.num_lote
                     AND c.ind_orig_cabe = '0');
      
        FORALL i IN 1 .. v_codclie.count
          DELETE sto_detal_docum_excep e
           WHERE EXISTS (SELECT 1
                    FROM int_solic_conso_cabec c
                   WHERE c.cod_clie = v_codclieorig(i)
                     AND c.sec_nume_docu = e.sec_nume_docu
                     AND c.num_lote = e.num_lote
                     AND c.ind_orig_cabe = '0');
      
        FORALL i IN 1 .. v_codclie.count
          DELETE sto_docum_digit d
           WHERE EXISTS (SELECT 1
                    FROM int_solic_conso_cabec c
                   WHERE c.cod_clie = v_codclieorig(i)
                     AND c.sec_nume_docu = d.sec_nume_docu
                     AND c.num_lote = d.num_lote
                     AND c.ind_orig_cabe = '0');
      
        FORALL i IN 1 .. v_codclie.count
          DELETE int_solic_conso_cabec c
           WHERE c.cod_clie = v_codclieorig(i)
             AND c.ind_orig_cabe = '0';
      
        /*MODIFICANDO A CODIGO ORIGINAL*/
        FORALL i IN 1 .. v_codclie.count
          UPDATE int_solic_conso_detal c
             SET cod_clie = v_codclieorig(i)
           WHERE c.cod_clie = v_codclie(i)
             AND c.num_lote = v_numlote(i);
      
        FORALL i IN 1 .. v_codclie.count
          UPDATE int_solic_conso_cabec c
             SET cod_clie = v_codclieorig(i)
           WHERE c.sec_nume_docu = v_secnumedocu(i)
             AND c.num_lote = v_numlote(i);
      
        FORALL i IN 1 .. v_codclie.count
          UPDATE sto_docum_digit c
             SET cod_clie = v_codclieorig(i)
           WHERE c.sec_nume_docu = v_secnumedocu(i)
             AND c.num_lote = v_numlote(i);
      
        FORALL i IN 1 .. v_codclie.count
          UPDATE sto_docum_digit c
             SET cod_clie = v_codclieorig(i)
           WHERE c.sec_nume_docu_cabe = v_secnumedocu(i)
             AND c.num_lote = v_numlote(i);
      
      END IF;
      EXIT WHEN c_clientenoexistente%NOTFOUND;
    
    END LOOP;
    CLOSE c_clientenoexistente;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_ACTUA_CLIEN_VALID_PEDID: ' ||
                              ls_sqlerrm);
    
  END ocr_actua_clien_valid_pedid;
  /***************************************************************************
  Descripcion       : Devuelve le codigo de Compa?ia de la tabla BAS_PAIS_COMPA
  Fecha Creacion    : 29/09/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION int_fn_ocr_devue_compa_basco(pscodigopais VARCHAR2)
    RETURN VARCHAR2 IS
  
    lscodigocia VARCHAR2(2);
  
  BEGIN
  
    SELECT cod_comp
      INTO lscodigocia
      FROM bas_pais_compa
     WHERE cod_pais = pscodigopais;
  
    RETURN lscodigocia;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_FN_OCR_DEVUE_COMPA_BASCO: ' ||
                              ls_sqlerrm);
    
  END int_fn_ocr_devue_compa_basco;

  /***************************************************************************
  Descripcion       : Procedimiento que consolida los detalles del pedido
                      considerando los detalles del lote anteriormente cargado
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
  Fecha Creacion    : 20/04/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_ocr_conso_detal_lotes
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psnumerolotesto VARCHAR2
  ) IS
  
    CURSOR curinsdetal IS
      SELECT c.cod_clie,
             c.num_lote
        FROM int_solic_conso_cabec c
       WHERE EXISTS (SELECT 1
                FROM int_solic_cabec cab
               WHERE c.cod_pais = cab.cod_pais
                 AND c.cod_clie = cab.cod_clie
                 AND c.cod_peri = cab.cam_soli
                 AND nvl(cab.ind_vali_dean,0) = '0'
                 AND cab.num_lote_sto = psnumerolotesto)
         AND c.cod_pais = pscodigopais
         AND c.cod_peri = pscodigoperiodo
         AND c.ind_erro_rech = '0'
         AND c.ind_erro_remp = '0'
         AND c.ind_erro_node = '0'
         AND c.ind_ocs_proc = '0'
         AND c.ind_proc_gp2 = '0'
         AND c.ind_rece_onli = '0'
          AND nvl(c.ind_vali_dean,0) = '0';  
  
    TYPE t_cod_clie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
  
    v_cod_clie t_cod_clie;
    v_num_lote t_num_lote;
  
    i BINARY_INTEGER := 0;
  
  BEGIN
  
    OPEN curinsdetal;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsdetal BULK COLLECT
        INTO v_cod_clie,
             v_num_lote LIMIT w_filas;
    
      EXIT WHEN v_cod_clie.count = 0;
    
      FOR i IN 1 .. v_cod_clie.count
      LOOP
      
        UPDATE int_solic_posic d
           SET d.num_lote = v_num_lote(i)
         WHERE d.num_lote_sto = psnumerolotesto
           AND d.cod_clie = v_cod_clie(i)
           AND d.cam_soli = pscodigoperiodo
           AND d.cod_pais = pscodigopais;
      
        UPDATE int_solic_cabec c
           SET c.num_lote = v_num_lote(i)
         WHERE c.num_lote_sto = psnumerolotesto
           AND c.cod_clie = v_cod_clie(i)
           AND c.cam_soli = pscodigoperiodo
           AND c.cod_pais = pscodigopais;
      
      END LOOP;
    END LOOP;
    CLOSE curinsdetal;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_CONSO_DETAL_LOTES: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_conso_detal_lotes;

  /***************************************************************************
  Descripcion       : Procedimiento que actualiza el indicador de primer pedido
                      en int_solic_conso_cabec
  Parametros Entrada:
      psCodigoPais     : Codigo de pais
      psCodigoPeriodo  : Codigo de periodo
  Fecha Creacion    : 23/04/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_actua_indic_prime_pedi
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psnumerolotesto VARCHAR2
  ) IS
    CURSOR cur_prime_pedi IS
      SELECT cab.cod_clie
        FROM int_solic_cabec       cab,
             mae_clien_datos_adici cda,
             mae_estat_clien       mec,
             mae_clien             mc
       WHERE cda.esta_oid_esta_clie = mec.oid_esta_clie
         AND mc.oid_clie = cda.clie_oid_clie
         AND mc.cod_clie = cab.cod_clie
         AND cab.num_lote_sto = psnumerolotesto
         AND mec.cod_esta_clie IN ('01', '07');
  
    TYPE t_cod_clie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
  
    v_cod_clie t_cod_clie;
  
    i           BINARY_INTEGER := 0;
    v_row_count NUMBER := 0;
  
  BEGIN
  
    OPEN cur_prime_pedi;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH cur_prime_pedi BULK COLLECT
        INTO v_cod_clie LIMIT w_filas;
    
      EXIT WHEN v_row_count = cur_prime_pedi%ROWCOUNT;
      v_row_count := cur_prime_pedi%ROWCOUNT;
    
      -- Bulk bind of data in memory table...
      FORALL i IN 1 .. v_cod_clie.count
        UPDATE int_solic_conso_cabec cab
           SET cab.tip_orde = 'P'
         WHERE cod_pais = pscodigopais
           AND cod_peri = pscodigoperiodo
           AND cod_clie = v_cod_clie(i);
    
    END LOOP;
    CLOSE cur_prime_pedi;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR int_pr_actua_indic_prime_pedi: ' ||
                              ls_sqlerrm);
    
  END int_pr_actua_indic_prime_pedi;

  /**************************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Codigos de venta rechazados
  Fecha Creacion    : 13/07/2010
  Autor             : Dennys Oliva Iriarte
  **************************************************************************************/
  PROCEDURE int_pr_recep_codig_recha_ocs
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psusuario        VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    ----------------------
    TYPE t_cod_pais IS TABLE OF ocr_cvent_errad.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF ocr_cvent_errad.cod_peri%TYPE;
    TYPE t_fec_proc IS TABLE OF VARCHAR2(8);
    TYPE t_cod_vent IS TABLE OF ocr_cvent_errad.cod_vent%TYPE;
    TYPE t_cod_clie IS TABLE OF ocr_cvent_errad.cod_clie%TYPE;
    TYPE t_num_prei IS TABLE OF ocr_cvent_errad.num_prei%TYPE;
    TYPE t_num_indi IS TABLE OF ocr_cvent_errad.num_indi%TYPE;
    TYPE t_num_cant IS TABLE OF ocr_cvent_errad.num_cant%TYPE;
    TYPE t_fec_digi IS TABLE OF VARCHAR2(20);
    TYPE t_cod_compa IS TABLE OF VARCHAR2(2);
  
    v_cod_pais t_cod_pais := t_cod_pais();
    v_cod_peri t_cod_peri := t_cod_peri();
    v_fec_proc t_fec_proc := t_fec_proc();
    v_cod_vent t_cod_vent := t_cod_vent();
    v_cod_clie t_cod_clie := t_cod_clie();
    v_num_prei t_num_prei := t_num_prei();
    v_num_indi t_num_indi := t_num_indi();
    v_num_cant t_num_cant := t_num_cant();
  
    v_fec_digi  t_fec_digi := t_fec_digi();
    v_cod_compa t_cod_compa := t_cod_compa();
    ----------------------
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(4000);
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
    -------------------------
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 2) THEN
                  v_cod_compa.extend;
                  v_cod_compa(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 3) THEN
                  v_cod_peri.extend;
                  v_cod_peri(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 5) THEN
                  v_num_prei.extend;
                  v_num_prei(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 6) THEN
                  v_fec_proc.extend;
                  v_fec_proc(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 7) THEN
                  v_num_indi.extend;
                  v_num_indi(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 8) THEN
                  v_cod_vent.extend;
                  v_cod_vent(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 9) THEN
                  v_num_cant.extend;
                  v_num_cant(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       0);
                ELSIF (posicion = 10) THEN
                  v_fec_digi.extend;
                  v_fec_digi(i) := substr(lslinea, inicio, longitud);
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FOR i IN 1 .. v_cod_pais.count
    LOOP
      IF v_cod_clie(i) IS NOT NULL THEN
        BEGIN
          INSERT INTO ocr_cvent_errad
            (cod_pais,
             cod_peri,
             fec_proc,
             cod_vent,
             cod_clie,
             num_prei,
             num_indi,
             num_cant,
             fec_digi,
             usu_digi)
          VALUES
            (v_cod_pais(i),
             v_cod_peri(i),
             to_date(v_fec_proc(i), 'YYYYMMDD'),
             v_cod_vent(i),
             v_cod_clie(i),
             v_num_prei(i),
             v_num_indi(i),
             v_num_cant(i),
             SYSDATE,
             psusuario);
        EXCEPTION
          WHEN dup_val_on_index THEN
            NULL;
        END;
      
      END IF;
    END LOOP;
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_recep_codig_recha_ocs: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_recep_codig_recha_ocs;

  /***************************************************************************
  Descripcion       : Interface Recepcion de Ordenes de Transporte
  Fecha Creacion    : 07/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_orden_trans
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_orden_trans.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_orden_trans.cod_comp%TYPE;
    TYPE t_cod_tipo_docu IS TABLE OF int_ocr_orden_trans.cod_tipo_docu%TYPE;
    TYPE t_num_docu IS TABLE OF int_ocr_orden_trans.num_docu%TYPE;
    TYPE t_cod_comp_tran IS TABLE OF int_ocr_orden_trans.cod_comp_tran%TYPE;
    TYPE t_cod_cent_acop IS TABLE OF int_ocr_orden_trans.cod_cent_acop%TYPE;
    TYPE t_cod_esta_entr IS TABLE OF int_ocr_orden_trans.cod_esta_entr%TYPE;
    TYPE t_cod_esta_reco IS TABLE OF int_ocr_orden_trans.cod_esta_reco%TYPE;
    TYPE t_cod_nove IS TABLE OF int_ocr_orden_trans.cod_nove%TYPE;
    TYPE t_cod_reci_conf IS TABLE OF int_ocr_orden_trans.cod_reci_conf%TYPE;
    TYPE t_fec_proc IS TABLE OF VARCHAR2(8);
    TYPE t_hor_proc IS TABLE OF VARCHAR2(4);
    TYPE t_tip_hora IS TABLE OF VARCHAR2(2);
    TYPE t_cod_esta_ent2 IS TABLE OF int_ocr_orden_trans.cod_esta_ent2%TYPE;
    TYPE t_cod_moti_rech IS TABLE OF int_ocr_orden_trans.cod_moti_rech%TYPE;
  
    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_comp      t_cod_comp := t_cod_comp();
    v_cod_tipo_docu t_cod_tipo_docu := t_cod_tipo_docu();
    v_num_docu      t_num_docu := t_num_docu();
    v_cod_comp_tran t_cod_comp_tran := t_cod_comp_tran();
    v_cod_cent_acop t_cod_cent_acop := t_cod_cent_acop();
    v_cod_esta_entr t_cod_esta_entr := t_cod_esta_entr();
    v_cod_esta_reco t_cod_esta_reco := t_cod_esta_reco();
    v_cod_nove      t_cod_nove := t_cod_nove();
    v_cod_reci_conf t_cod_reci_conf := t_cod_reci_conf();
    v_fec_proc      t_fec_proc := t_fec_proc();
    v_hor_proc      t_hor_proc := t_hor_proc();
    v_tip_hora      t_tip_hora := t_tip_hora();
    v_cod_esta_ent2 t_cod_esta_ent2 := t_cod_esta_ent2();
    v_cod_moti_rech t_cod_moti_rech := t_cod_moti_rech();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i                   BINARY_INTEGER := 0;
    posicion            NUMBER := 0;
    longitud            NUMBER := 0;
    nfechaprocesoactual VARCHAR2(8); --bas_ctrl_fact.fec_proc%TYPE;
    nfechaproceso       bas_ctrl_fact.fec_proc%TYPE;
    inicio              NUMBER := 0;
  
  BEGIN
  
    EXECUTE IMMEDIATE 'TRUNCATE TABLE int_ocr_orden_trans';
  
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
        
          i      := i + 1;
          inicio := 1;
        
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          BEGIN
            SELECT to_char(c.fec_proc, 'yyyymmdd')
              INTO nfechaprocesoactual
              FROM bas_ctrl_fact c
             WHERE c.cod_pais = pscodigopais
               AND c.sta_camp = '0'
               AND c.ind_camp_act = '1';
          EXCEPTION
            WHEN no_data_found THEN
              nfechaprocesoactual := to_char(SYSDATE, 'yyyymmdd');
          END;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := TRIM(substr(lslinea, inicio, longitud));
                  v_cod_pais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_cod_pais(i),
                                                                               v_cod_comp(i));
                
                ELSIF (posicion = 3) THEN
                  v_cod_tipo_docu.extend;
                  v_cod_tipo_docu(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 4) THEN
                  v_num_docu.extend;
                  v_num_docu(i) := TRIM(substr(lslinea, inicio, longitud));
                
                ELSIF (posicion = 5) THEN
                  v_cod_comp_tran.extend;
                  v_cod_comp_tran(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 6) THEN
                  v_cod_cent_acop.extend;
                  v_cod_cent_acop(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 7) THEN
                  v_cod_esta_entr.extend;
                  v_cod_esta_entr(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 8) THEN
                  v_cod_esta_reco.extend;
                  v_cod_esta_reco(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 9) THEN
                  v_cod_nove.extend;
                  v_cod_nove(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 10) THEN
                  v_cod_reci_conf.extend;
                  v_cod_reci_conf(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 11) THEN
                
                  BEGIN
                    nfechaproceso := to_date(substr(lslinea,
                                                    inicio,
                                                    longitud),
                                             'YYYYMMDD');
                  
                  EXCEPTION
                    WHEN OTHERS THEN
                      nfechaproceso := to_date(nfechaprocesoactual,
                                               'YYYYMMDD');
                  END;
                
                  v_fec_proc.extend;
                  v_fec_proc(i) := to_char(nfechaproceso, 'YYYYMMDD');
                
                ELSIF (posicion = 12) THEN
                  v_hor_proc.extend;
                  v_hor_proc(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       '0101');
                ELSIF (posicion = 13) THEN
                  v_tip_hora.extend;
                  v_tip_hora(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       'AM');
                ELSIF (posicion = 14) THEN
                  v_cod_esta_ent2.extend;
                  v_cod_esta_ent2(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 15) THEN
                  v_cod_moti_rech.extend;
                  v_cod_moti_rech(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_cod_pais.count
      INSERT INTO int_ocr_orden_trans
        (cod_pais,
         cod_comp,
         cod_tipo_docu,
         num_docu,
         cod_comp_tran,
         cod_cent_acop,
         cod_esta_entr,
         cod_esta_reco,
         cod_nove,
         cod_reci_conf,
         fec_proc,
         cod_esta_ent2,
         cod_moti_rech,
         ind_orig)
      VALUES
        (v_cod_pais(i),
         v_cod_comp(i),
         v_cod_tipo_docu(i),
         v_num_docu(i),
         v_cod_comp_tran(i),
         v_cod_cent_acop(i),
         v_cod_esta_entr(i),
         v_cod_esta_reco(i),
         v_cod_nove(i),
         v_cod_reci_conf(i),
         to_date(v_fec_proc(i) || v_hor_proc(i) || v_tip_hora(i),
                 'yyyymmddHHMIAM'),
         v_cod_esta_ent2(i),
         v_cod_moti_rech(i),
         psindicadororigen);
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR ocr_pr_recep_orden_trans: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END ocr_pr_recep_orden_trans;

  /***************************************************************************
  Descripcion       : Consolida las Ordenes de Transporte
  Fecha Creacion    : 07/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_orden_trans
  (
    pscodigopais          VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2,
    pscodigotipodocumento VARCHAR2
  ) IS
    CURSOR curinsconsolcredi IS
      SELECT cod_pais             cod_pais,
             cod_comp             cod_comp,
             num_docu             num_docu,
             cod_tipo_docu        cod_tipo_docu,
             cod_comp_tran        cod_comp_tran,
             cod_cent_acop        cod_cent_acop,
             cod_esta_entr        cod_esta_entr,
             cod_esta_reco        cod_esta_reco,
             cod_nove             cod_nove,
             ind_caja_comp        cod_reci_conf, -----cod_reci_conf,
             NULL                 oid_pais,
             fec_proc             fec_proc,
             sta_proc             sta_proc,
             cod_moti_rech        cod_moti_rech,
             psnumlote            num_lote,
             cod_esta_ent2        cod_esta_ent2,
             NULL                 val_mens,
             NULL                 val_dire,
             seq_docu_sto.nextval sec_nume_docu,
             NULL                 ind_nove,
             NULL                 ind_envi,
             tip_orde,
             NULL                 cod_zona,
             NULL                 cod_cali,
             NULL                 cod_clie,
             NULL                 fec_fact,
             NULL                 nom_clie,
             NULL                 cod_peri,
             NULL                 cod_regi,
             NULL                 cod_secc,
             NULL                 fec_solu_nove,
             NULL                 cod_zona_arri,
             ind_orig,
             ind_caja_comp,
             ind_fuer_caja,
             hor_fact,
             fec_ocr,
             val_lati,
             val_long
        FROM int_ocr_orden_trans
       WHERE cod_pais = pscodigopais;
  
    TYPE orden_trans_tab_t IS TABLE OF int_solic_conso_orden_trans%ROWTYPE INDEX BY BINARY_INTEGER;
    orden_trans_tab orden_trans_tab_t; -- In-memory table
    
    TYPE orden_trans_histo_tab_t IS TABLE OF int_histo_conso_orden_trans%ROWTYPE INDEX BY BINARY_INTEGER;
    orden_trans_histo_tab orden_trans_histo_tab_t; -- In-memory table
    
    j BINARY_INTEGER := 0;
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t; -- In-memory table
  
  BEGIN
  
    OPEN curinsconsolcredi;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsolcredi BULK COLLECT
        INTO orden_trans_tab LIMIT w_filas;
      EXIT WHEN orden_trans_tab.count = 0;
    
      FOR j IN orden_trans_tab.first .. orden_trans_tab.last
      LOOP
        sto_tab(j).cod_pais := orden_trans_tab(j).cod_pais;
        sto_tab(j).cod_tipo_docu := pscodigotipodocumento;
        sto_tab(j).num_lote := orden_trans_tab(j).num_lote;
        sto_tab(j).sec_nume_docu := orden_trans_tab(j).sec_nume_docu;
        sto_tab(j).num_docu := orden_trans_tab(j).num_docu;
        sto_tab(j).ind_envi := '0';
        sto_tab(j).ind_rech := '0';
        sto_tab(j).fec_digi := SYSDATE;
        sto_tab(j).usu_digi := pscodigousuario;
        sto_tab(j).fec_modi := SYSDATE;
        sto_tab(j).usu_modi := pscodigousuario;
        -- sto_tab(j).cod_zona := sol_post_venta_tab(j).cod_zona_arri;
        sto_tab(j).cod_clie := orden_trans_tab(j).cod_clie;
        -- sto_tab(j).cod_regi := sol_post_venta_tab(j).cod_regi_arri;
        --sto_tab(j).sec_nume_docu_cabe := sol_credi_tab(j).sec_nume_docu_cabe;
        sto_tab(j).cod_peri := orden_trans_tab(j).cod_peri;
        --sto_tab(j).COD_MOTI_RECH      :=
        -- sto_tab(j).val_obse_rech_defi := sol_post_venta_tab(j).val_obse_rech_defi;
        sto_tab(j).ind_rece_ocr := '0';
        sto_tab(j).ind_rece_web := '0';
        sto_tab(j).ind_rece_dd := '0';
        sto_tab(j).ind_rece_digi := '0';
        sto_tab(j).ind_rece_cc := '0';
        sto_tab(j).ind_rece_mens := '0';
        sto_tab(j).ind_elim := '0';
        sto_tab(j).cod_zona_arri := orden_trans_tab(j).cod_zona_arri;
        sto_tab(j).ind_rece_onli := '0';
        sto_tab(j).ind_rece_ivr := '0';
        --agregando hora en formato 24hmm, Hora de entrega
        -- orden_trans_tab(j).hor_fact := to_char(SYSDATE,'HH24MI');
      
        IF orden_trans_tab(j).ind_orig = 'O' THEN
          sto_tab(j).ind_rece_ocr := '1';
        ELSIF orden_trans_tab(j).ind_orig = 'W' THEN
          sto_tab(j).ind_rece_web := '1';
        END IF;
      
        SELECT MIN(cod_peri)
          INTO sto_tab(j).cod_peri
          FROM bas_ctrl_fact
         WHERE sta_camp = '0'
           AND ind_camp_act = '1';
      
        UPDATE sto_docum_digit a
           SET a.ind_rech           = '1',
               a.val_obse_rech_defi = 'OT LLEGO NUEVAMENTE',
               a.fec_modi           = SYSDATE,
               a.usu_modi           = pscodigousuario
         WHERE a.ind_envi = '0'
           AND a.ind_rech = '0'
           AND a.cod_tipo_docu = 'OT'
           AND EXISTS
         (SELECT 1
                  FROM int_solic_conso_orden_trans b
                 WHERE a.sec_nume_docu = b.sec_nume_docu
                   AND a.num_lote = b.num_lote
                   AND b.num_docu = orden_trans_tab(j).num_docu);
                   
                   
        orden_trans_histo_tab(j) := orden_trans_tab(j);
      
      END LOOP;
    
      FORALL j IN orden_trans_tab.first .. orden_trans_tab.last
        INSERT INTO int_solic_conso_orden_trans VALUES orden_trans_tab (j);
        
      FORALL j IN orden_trans_histo_tab.first .. orden_trans_histo_tab.last
        INSERT INTO int_histo_conso_orden_trans VALUES orden_trans_histo_tab (j);
    
      FORALL j IN orden_trans_tab.first .. orden_trans_tab.last
        INSERT INTO sto_docum_digit VALUES sto_tab (j);
    
    END LOOP;
    CLOSE curinsconsolcredi;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR ocr_pr_conso_orden_trans: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_orden_trans;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Ordenes de transporte del
                      proceso de facturacion
  Fecha Creacion    : 14/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_envi_orden_trans_factu
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psfechafacturacion VARCHAR2,
    pscodigoperiodo    VARCHAR2
  ) IS
  
    CURSOR c_interfaz(voidoeriodo NUMBER) IS
      SELECT b.cod_pais,
             'OT',
             a.val_nume_soli,
             'E',
             a.val_nume_soli,
             c.cod_clie,
             h.cod_regi,
             g.cod_zona,
             f.cod_secc,
             pscodigoperiodo, ---- e.cod_terr,
             i.val_barr,
             nvl(gen_pkg_gener.gen_fn_clien_datos_oid(c.oid_clie,
                                                      'DES_PROV'),
                 '-') AS des_prov
      ---i.val_obse
        FROM ped_solic_cabec a,
             seg_pais        b,
             mae_clien       c,
             zon_terri_admin d,
             zon_terri       e,
             zon_secci       f,
             zon_zona        g,
             zon_regio       h,
             mae_clien_direc i
       WHERE a.pais_oid_pais = b.oid_pais
         AND a.clie_oid_clie = c.oid_clie
         AND c.oid_clie = i.clie_oid_clie
         AND i.ind_elim = 0
         AND i.ind_dire_ppal = 1
         AND a.ztad_oid_terr_admi = d.oid_terr_admi
         AND d.terr_oid_terr = e.oid_terr
         AND d.zscc_oid_secc = f.oid_secc
         AND f.zzon_oid_zona = g.oid_zona
         AND g.zorg_oid_regi = h.oid_regi
         AND a.perd_oid_peri = voidoeriodo
         AND a.fec_fact = to_date(psfechafacturacion, 'dd/MM/YYYY')
         AND a.tspa_oid_tipo_soli_pais IN
             (SELECT tspa_oid_tipo_soli_pais
                FROM int_lar_tipo_solici_pedido_dis)
      UNION
      SELECT c.cod_pais,
             'OT',
             c.val_nume_bore br,
             ' ',
             c.val_nume_bole_desp,
             c.cod_clie codcons,
             c.cod_regi,
             c.cod_zona,
             c.cod_secc,
             pscodigoperiodo, ----c.cod_terr,
             d.val_barr,
             nvl(gen_pkg_gener.gen_fn_clien_datos_oid(c.clie_oid_clie,
                                                      'DES_PROV'),
                 '-') AS des_prov
      ---d.val_obse
        FROM int_rec_cabec_borec c,
             mae_clien_direc     d
       WHERE trunc(c.fec_ingr) = to_date(psfechafacturacion, 'dd/MM/YYYY')
         AND c.clie_oid_clie = d.clie_oid_clie
         AND d.ind_elim = 0
         AND d.ind_dire_ppal = 1;
  
    TYPE interfazrec IS RECORD(
      v_cod_pais      seg_pais.cod_pais%TYPE,
      v_tipo_docu     VARCHAR2(2),
      v_val_nume_sol  ped_solic_cabec.val_nume_soli%TYPE,
      v_indicador     VARCHAR2(1),
      v_val_nume_soli ped_solic_cabec.val_nume_soli%TYPE,
      v_cod_clie      mae_clien.cod_clie%TYPE,
      v_cod_regi      zon_regio.cod_regi%TYPE,
      v_cod_zona      zon_zona.cod_zona%TYPE,
      v_cod_secc      zon_secci.cod_secc%TYPE,
      v_cod_terr      zon_terri.cod_terr%TYPE,
      v_val_barr      mae_clien_direc.val_barr%TYPE,
      v_val_obse      mae_clien_direc.val_obse%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
    lsoidperiodo    cra_perio.oid_peri%TYPE;
    lscodigocia     bas_pais_compa.cod_comp%TYPE;
  
  BEGIN
  
    lsoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
  
    SELECT cod_comp
      INTO lscodigocia
      FROM bas_pais_compa
     WHERE cod_pais = pscodigopais;
  
    lbabrirutlfile := TRUE;
    OPEN c_interfaz(lsoidperiodo);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := TRIM(substr(interfazrecord(x).v_cod_pais, 1, 2)) || ';' ||
                     lscodigocia || ';' ||
                     TRIM(interfazrecord(x).v_tipo_docu) || ';' ||
                     TRIM(interfazrecord(x).v_val_nume_sol) || ';' ||
                     TRIM(interfazrecord(x).v_indicador) || ';' ||
                     TRIM(interfazrecord(x).v_val_nume_soli) || ';' ||
                     TRIM(interfazrecord(x).v_cod_clie) || ';' ||
                     TRIM(interfazrecord(x).v_cod_regi) || ';' ||
                     TRIM(interfazrecord(x).v_cod_zona) || ';' ||
                     TRIM(interfazrecord(x).v_cod_secc) || ';' ||
                     TRIM(interfazrecord(x).v_cod_terr) || ';' ||
                     TRIM(interfazrecord(x).v_val_obse) || ';' ||
                     TRIM(interfazrecord(x).v_val_barr);
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'int_pr_envi_orden_trans_factu: ' ||
                              ls_sqlerrm);
  END int_pr_envi_orden_trans_factu;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Envio de Ordenes de transporte
                      anuladas
  Fecha Creacion    : 14/09/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE int_pr_envi_orden_trans_anula
  (
    pscodigopais       VARCHAR2,
    pscodigosistema    VARCHAR2,
    pscodigointerfaz   VARCHAR2,
    psnombrearchivo    VARCHAR2,
    psfechafacturacion VARCHAR2,
    pscodigoperiodo    VARCHAR2
  ) IS
  
    CURSOR c_interfaz(voidoeriodo NUMBER) IS
      SELECT b.cod_pais,
             'OT',
             'E',
             a.val_nume_soli
        FROM ped_solic_cabec a,
             seg_pais        b
       WHERE a.pais_oid_pais = b.oid_pais
         AND a.esso_oid_esta_soli = 4
         AND a.perd_oid_peri = voidoeriodo
         AND a.fec_fact = to_date(psfechafacturacion, 'dd/MM/YYYY')
         AND a.tspa_oid_tipo_soli_pais IN
             (SELECT tspa_oid_tipo_soli_pais
                FROM int_lar_tipo_solici_pedido_dis);
  
    TYPE interfazrec IS RECORD(
      v_cod_pais      seg_pais.cod_pais%TYPE,
      v_tipo_docu     VARCHAR2(2),
      v_indicador     VARCHAR2(1),
      v_val_nume_soli ped_solic_cabec.val_nume_soli%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
    lsoidperiodo    cra_perio.oid_peri%TYPE;
    lscodigocia     bas_pais_compa.cod_comp%TYPE;
  
  BEGIN
  
    lsoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
  
    SELECT cod_comp
      INTO lscodigocia
      FROM bas_pais_compa
     WHERE cod_pais = pscodigopais;
  
    lbabrirutlfile := TRUE;
    OPEN c_interfaz(lsoidperiodo);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := TRIM(substr(interfazrecord(x).v_cod_pais, 1, 2)) || ';' ||
                     lscodigocia || ';' ||
                     TRIM(interfazrecord(x).v_tipo_docu) || ';' ||
                     TRIM(interfazrecord(x).v_indicador) || ';' ||
                     TRIM(interfazrecord(x).v_val_nume_soli);
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'int_pr_envi_orden_trans_anula: ' ||
                              ls_sqlerrm);
  END int_pr_envi_orden_trans_anula;
  /***************************************************************************
  OCR_PR_ENVI_RETOR_CODAS Clientes de OCR
    Fecha Creacion    : 24/02/2011OCR_PR_ENVIO_RETOR_CODAS
  Autor             : Christian Luque
  ***************************************************************************/
  PROCEDURE ocr_pr_envio_retor_codas
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psperiodo        VARCHAR2
  ) IS
    CURSOR c_interfaz
    (
      vscodigocia     VARCHAR2,
      vscodigopaisocr VARCHAR2
    ) IS
      SELECT vscodigopaisocr codigopais,
             vscodigocia codigocia,
             a.num_docu,
             a.cod_clie,
             a.num_lote,
             a.sec_nume_docu,
             to_char(SYSDATE, 'dd/MM/yyyy HH24:MI:SS')
        FROM int_solic_conso_credi a,
             sto_docum_digit       b
       WHERE a.cod_peri = psperiodo
         AND a.sec_nume_docu = b.sec_nume_docu
         AND a.num_lote = b.num_lote
         AND b.ind_envi = '1'
         AND nvl(a.ind_envi_ocr, 0) = 0;
  
    TYPE interfazrec IS RECORD(
      codigopais     seg_pais.cod_pais%TYPE,
      codigocompania int_solic_conso_credi.cod_comp%TYPE,
      numdocu        int_solic_conso_credi.num_docu%TYPE,
      codigocliente  mae_clien.cod_clie%TYPE,
      numlote        int_solic_conso_credi.num_lote%TYPE,
      secnumedocu    int_solic_conso_credi.sec_nume_docu%TYPE,
      fecha          VARCHAR2(30));
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
    lbabrirutlfile  BOOLEAN;
    lscodigocia     bas_pais_compa.cod_comp%TYPE;
    lscodigopaisocr bas_pais_compa.cod_pais_ocr%TYPE;
  
  BEGIN
  
    SELECT cod_comp,
           cod_pais_ocr
      INTO lscodigocia,
           lscodigopaisocr
      FROM bas_pais_compa
     WHERE cod_pais = pscodigopais;
  
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz(lscodigocia, lscodigopaisocr);
  
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
      
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigopais || ';' || interfazrecord(x).codigocompania || ';' || interfazrecord(x)
                     .numdocu || ';' || interfazrecord(x).codigocliente || ';' || interfazrecord(x)
                     .fecha;
        
          utl_file.put_line(v_handle, lslinea);
        
          UPDATE int_solic_conso_credi
             SET ind_envi_ocr = '1'
           WHERE num_lote = interfazrecord(x).numlote
             AND sec_nume_docu = interfazrecord(x).secnumedocu;
        
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_ENVIO_RETOR_CODAS: ' ||
                              ls_sqlerrm);
  END ocr_pr_envio_retor_codas;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Ingreso
                      de Metas
  Fecha Creacion    : 01/03/2011
  Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_ingre_metas
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_ingre_metas.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_ingre_metas.cod_comp%TYPE;
    TYPE t_num_docu IS TABLE OF int_ocr_ingre_metas.num_docu%TYPE;
    TYPE t_num_line IS TABLE OF int_ocr_ingre_metas.num_line%TYPE;
    TYPE t_fec_proc IS TABLE OF int_ocr_ingre_metas.fec_proc%TYPE;
    TYPE t_cod_camp_proc IS TABLE OF int_ocr_ingre_metas.cod_camp_proc%TYPE;
    TYPE t_cod_clie IS TABLE OF int_ocr_ingre_metas.cod_clie%TYPE;
    TYPE t_tip_meta IS TABLE OF int_ocr_ingre_metas.tip_meta%TYPE;
    TYPE t_val_mnto_meta IS TABLE OF int_ocr_ingre_metas.val_mnto_meta%TYPE;
    TYPE t_cod_camp_inic IS TABLE OF int_ocr_ingre_metas.cod_camp_inic%TYPE;
    TYPE t_cod_regi IS TABLE OF int_ocr_ingre_metas.cod_regi%TYPE;
    TYPE t_cod_zona IS TABLE OF int_ocr_ingre_metas.cod_zona%TYPE;
    TYPE t_cod_esta IS TABLE OF int_ocr_ingre_metas.cod_esta%TYPE;
    TYPE t_cod_moti_rech IS TABLE OF int_ocr_ingre_metas.cod_moti_rech%TYPE;
  
    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_comp      t_cod_comp := t_cod_comp();
    v_num_docu      t_num_docu := t_num_docu();
    v_num_line      t_num_line := t_num_line();
    v_fec_proc      t_fec_proc := t_fec_proc();
    v_cod_camp_proc t_cod_camp_proc := t_cod_camp_proc();
    v_cod_clie      t_cod_clie := t_cod_clie();
    v_tip_meta      t_tip_meta := t_tip_meta();
    v_val_mnto_meta t_val_mnto_meta := t_val_mnto_meta();
    v_cod_camp_inic t_cod_camp_inic := t_cod_camp_inic();
    v_cod_regi      t_cod_regi := t_cod_regi();
    v_cod_zona      t_cod_zona := t_cod_zona();
    v_cod_esta      t_cod_esta := t_cod_esta();
    v_cod_moti_rech t_cod_moti_rech := t_cod_moti_rech();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
        
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                  v_cod_pais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_cod_pais(i),
                                                                               v_cod_comp(i));
                ELSIF (posicion = 3) THEN
                  v_num_docu.extend;
                  v_num_docu(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 4) THEN
                  v_num_line.extend;
                  v_num_line(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 5) THEN
                  v_fec_proc.extend;
                  v_fec_proc(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 6) THEN
                  v_cod_camp_proc.extend;
                  v_cod_camp_proc(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 7) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 8) THEN
                  v_tip_meta.extend;
                  v_tip_meta(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 9) THEN
                  v_val_mnto_meta.extend;
                  v_val_mnto_meta(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 10) THEN
                  v_cod_camp_inic.extend;
                  v_cod_camp_inic(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 11) THEN
                  v_cod_regi.extend;
                  v_cod_regi(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 12) THEN
                  v_cod_zona.extend;
                  v_cod_zona(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 13) THEN
                  v_cod_esta.extend;
                  v_cod_esta(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 14) THEN
                  v_cod_moti_rech.extend;
                  v_cod_moti_rech(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_cod_pais.count
      INSERT INTO int_ocr_ingre_metas
        (cod_pais,
         cod_comp,
         num_docu,
         num_line,
         fec_proc,
         cod_camp_proc,
         cod_clie,
         tip_meta,
         val_mnto_meta,
         cod_camp_inic,
         cod_regi,
         cod_zona,
         cod_esta,
         cod_moti_rech,
         ind_orig)
      VALUES
        (v_cod_pais(i),
         v_cod_comp(i),
         v_num_docu(i),
         v_num_line(i),
         v_fec_proc(i),
         v_cod_camp_proc(i),
         v_cod_clie(i),
         v_tip_meta(i),
         v_val_mnto_meta(i),
         v_cod_camp_inic(i),
         v_cod_regi(i),
         v_cod_zona(i),
         v_cod_esta(i),
         v_cod_moti_rech(i),
         psindicadororigen);
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_INGRE_METAS: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_recep_ingre_metas;

  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Ingreso de Metas
  Fecha Creacion    : 01/03/2011
  Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_ingre_metas
  (
    pscodigopais          VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2,
    pscodigotipodocumento VARCHAR2
  ) IS
  
    CURSOR curinsconsolingremetas IS
      SELECT imet.cod_pais,
             imet.cod_comp,
             imet.num_docu,
             imet.num_line,
             trunc(SYSDATE) fec_proc,
             cod_camp_proc camp_proc,
             imet.cod_clie,
             imet.tip_meta,
             imet.val_mnto_meta,
             NULL val_dire,
             seq_docu_sto.nextval sec_nume_docu,
             psnumlote num_lote,
             imet.cod_camp_inic,
             imet.cod_regi,
             imet.cod_zona,
             imet.cod_esta,
             imet.cod_moti_rech,
             ind_orig
        FROM int_ocr_ingre_metas imet
       WHERE imet.cod_pais = pscodigopais;
  
    TYPE solic_ingre_metas_tab_t IS TABLE OF int_solic_conso_ingre_metas%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_ingre_metas_tab solic_ingre_metas_tab_t; -- In-memory table
  
    j BINARY_INTEGER := 0;
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t; -- In-memory table
  BEGIN
  
    OPEN curinsconsolingremetas;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsolingremetas BULK COLLECT
        INTO sol_ingre_metas_tab LIMIT w_filas;
      EXIT WHEN sol_ingre_metas_tab.count = 0;
    
      FOR j IN sol_ingre_metas_tab.first .. sol_ingre_metas_tab.last
      LOOP
        sto_tab(j).cod_pais := sol_ingre_metas_tab(j).cod_pais;
        sto_tab(j).cod_tipo_docu := pscodigotipodocumento;
        sto_tab(j).num_lote := sol_ingre_metas_tab(j).num_lote;
        sto_tab(j).sec_nume_docu := sol_ingre_metas_tab(j).sec_nume_docu;
        sto_tab(j).num_docu := sol_ingre_metas_tab(j).num_docu;
        sto_tab(j).ind_envi := '0';
        sto_tab(j).ind_rech := '0';
        sto_tab(j).fec_digi := SYSDATE;
        sto_tab(j).usu_digi := pscodigousuario;
        sto_tab(j).fec_modi := SYSDATE;
        sto_tab(j).usu_modi := pscodigousuario;
        sto_tab(j).cod_zona := gen_pkg_gener.gen_fn_clien_datos_codig(sol_ingre_metas_tab(j)
                                                                      .cod_clie,
                                                                      'COD_ZONA');
        sto_tab(j).cod_clie := sol_ingre_metas_tab(j).cod_clie;
        sto_tab(j).cod_regi := gen_pkg_gener.gen_fn_clien_datos_codig(sol_ingre_metas_tab(j)
                                                                      .cod_clie,
                                                                      'COD_REGI');
        --sto_tab(j).sec_nume_docu_cabe := sol_ingre_metas_tab(j).sec_nume_docu_cabe;
        sto_tab(j).cod_peri := sol_ingre_metas_tab(j).cod_camp_proc;
        --sto_tab(j).COD_MOTI_RECH      :=
        -- sto_tab(j).val_obse_rech_defi := sol_post_venta_tab(j).val_obse_rech_defi;
        sto_tab(j).ind_rece_ocr := '0';
        sto_tab(j).ind_rece_web := '0';
        sto_tab(j).ind_rece_dd := '0';
        sto_tab(j).ind_rece_digi := '0';
        sto_tab(j).ind_rece_cc := '0';
        sto_tab(j).ind_rece_mens := '0';
        sto_tab(j).ind_elim := '0';
        sto_tab(j).cod_zona_arri := sol_ingre_metas_tab(j).cod_zona_arri;
        sto_tab(j).ind_rece_onli := '0';
        sto_tab(j).ind_rece_ivr := '0';
      
        IF sol_ingre_metas_tab(j).ind_orig = 'O' THEN
          sto_tab(j).ind_rece_ocr := '1';
        ELSIF sol_ingre_metas_tab(j).ind_orig = 'W' THEN
          sto_tab(j).ind_rece_web := '1';
        END IF;
      
      END LOOP;
    
      FORALL i IN sol_ingre_metas_tab.first .. sol_ingre_metas_tab.last
        INSERT INTO int_solic_conso_ingre_metas
        VALUES sol_ingre_metas_tab
          (i);
    
      FORALL j IN sol_ingre_metas_tab.first .. sol_ingre_metas_tab.last
        INSERT INTO sto_docum_digit VALUES sto_tab (j);
    
    END LOOP;
    CLOSE curinsconsolingremetas;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_INGRE_METAS: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_ingre_metas;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Familia Segura
  Fecha Creacion    : 02/05/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_famil_segur
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_famil_segur.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_famil_segur.cod_comp%TYPE;
    TYPE t_num_cert IS TABLE OF int_ocr_famil_segur.num_docu%TYPE;
    TYPE t_cod_peri IS TABLE OF int_ocr_famil_segur.cam_proc%TYPE;
    TYPE t_tip_docu_iden IS TABLE OF int_ocr_famil_segur.tip_docu_iden%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_ocr_famil_segur.num_docu_iden%TYPE;
    TYPE t_cod_clie IS TABLE OF int_ocr_famil_segur.cod_clie%TYPE;
    TYPE t_num_coas IS TABLE OF int_ocr_famil_segur.can_coas%TYPE;
    TYPE t_nom_ben1 IS TABLE OF int_ocr_famil_segur.nom_ben1%TYPE;
    TYPE t_num_docu_ben1 IS TABLE OF int_ocr_famil_segur.num_docu_ben1%TYPE;
    TYPE t_tip_docu_ben1 IS TABLE OF int_ocr_famil_segur.tip_docu_ben1%TYPE;
    TYPE t_nom_ben2 IS TABLE OF int_ocr_famil_segur.nom_ben2%TYPE;
    TYPE t_num_docu_ben2 IS TABLE OF int_ocr_famil_segur.num_docu_ben2%TYPE;
    TYPE t_tip_docu_ben2 IS TABLE OF int_ocr_famil_segur.tip_docu_ben2%TYPE;
    TYPE t_fec_proc IS TABLE OF int_ocr_famil_segur.fec_proc%TYPE;
    TYPE t_cod_esta IS TABLE OF int_ocr_famil_segur.cod_esta_ocr%TYPE;
    TYPE t_cod_moti_rech IS TABLE OF int_ocr_famil_segur.mot_rech_ocr%TYPE;
    --
    TYPE t_cod_regi_orig_docu IS TABLE OF int_ocr_famil_segur.cod_regi_orig_docu%TYPE;
    TYPE t_cod_zona_orig_docu IS TABLE OF int_ocr_famil_segur.cod_zona_orig_docu%TYPE;
    --
    TYPE t_ind_firm IS TABLE OF int_ocr_famil_segur.ind_firm%TYPE;
    --
    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_comp      t_cod_comp := t_cod_comp();
    v_num_cert      t_num_cert := t_num_cert();
    v_cod_peri      t_cod_peri := t_cod_peri();
    v_tip_docu_iden t_tip_docu_iden := t_tip_docu_iden();
    v_num_docu_iden t_num_docu_iden := t_num_docu_iden();
    v_cod_clie      t_cod_clie := t_cod_clie();
    v_num_coas      t_num_coas := t_num_coas();
    v_nom_ben1      t_nom_ben1 := t_nom_ben1();
    v_num_docu_ben1 t_num_docu_ben1 := t_num_docu_ben1();
    v_tip_docu_ben1 t_tip_docu_ben1 := t_tip_docu_ben1();
    v_nom_ben2      t_nom_ben2 := t_nom_ben2();
    v_num_docu_ben2 t_num_docu_ben2 := t_num_docu_ben2();
    v_tip_docu_ben2 t_tip_docu_ben2 := t_tip_docu_ben2();
    v_fec_proc      t_fec_proc := t_fec_proc();
    v_cod_esta      t_cod_esta := t_cod_esta();
    v_cod_moti_rech t_cod_moti_rech := t_cod_moti_rech();
    --
    v_cod_regi_orig_docu t_cod_regi_orig_docu := t_cod_regi_orig_docu();
    v_cod_zona_orig_docu t_cod_zona_orig_docu := t_cod_zona_orig_docu();
    --
    v_ind_firm t_ind_firm := t_ind_firm();
    --
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    i               BINARY_INTEGER := 0;
    posicion        NUMBER := 0;
    longitud        NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
        
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
        
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
          
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                  v_cod_pais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_cod_pais(i),
                                                                               v_cod_comp(i));
                ELSIF (posicion = 3) THEN
                  v_num_cert.extend;
                  v_num_cert(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 4) THEN
                  v_cod_peri.extend;
                  v_cod_peri(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 5) THEN
                  v_tip_docu_iden.extend;
                  v_tip_docu_iden(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 6) THEN
                  v_num_docu_iden.extend;
                  v_num_docu_iden(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 7) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 8) THEN
                  v_num_coas.extend;
                  v_num_coas(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 9) THEN
                  v_nom_ben1.extend;
                  v_nom_ben1(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 10) THEN
                  v_num_docu_ben1.extend;
                  v_num_docu_ben1(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 11) THEN
                  v_tip_docu_ben1.extend;
                  v_tip_docu_ben1(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 12) THEN
                  v_nom_ben2.extend;
                  v_nom_ben2(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 13) THEN
                  v_num_docu_ben2.extend;
                  v_num_docu_ben2(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 14) THEN
                  v_tip_docu_ben2.extend;
                  v_tip_docu_ben2(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 15) THEN
                  v_fec_proc.extend;
                  -- Si la fecha llega con un formato invalido, se captura la
                  -- excepcion y se le coloca null
                  BEGIN
                    v_fec_proc(i) := to_date(TRIM(substr(lslinea,
                                                         inicio,
                                                         longitud)),
                                             'yyyyMMdd');
                  EXCEPTION
                    WHEN OTHERS THEN
                      v_fec_proc(i) := NULL;
                  END;
                
                ELSIF (posicion = 16) THEN
                  v_cod_esta.extend;
                  v_cod_esta(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 17) THEN
                  v_cod_moti_rech.extend;
                  v_cod_moti_rech(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                  --
                ELSIF (posicion = 18) THEN
                  -- REGION DE ARRIBO
                  v_cod_regi_orig_docu.extend;
                  v_cod_regi_orig_docu(i) := TRIM(translate(substr(lslinea,
                                                                   inicio,
                                                                   longitud),
                                                            lscadena,
                                                            lsreplace));
                ELSIF (posicion = 19) THEN
                  -- ZONA DE ARRIBO
                  v_cod_zona_orig_docu.extend;
                  v_cod_zona_orig_docu(i) := TRIM(translate(substr(lslinea,
                                                                   inicio,
                                                                   longitud),
                                                            lscadena,
                                                            lsreplace));
                  --
                ELSIF (posicion = 20) THEN
                  -- INDICADOR DE FIRMA
                  v_ind_firm.extend;
                  v_ind_firm(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                  --
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_cod_pais.count
      INSERT INTO int_ocr_famil_segur
        (cod_pais,
         cod_comp,
         num_docu,
         cam_proc,
         tip_docu_iden,
         num_docu_iden,
         cod_clie,
         can_coas,
         nom_ben1,
         num_docu_ben1,
         tip_docu_ben1,
         nom_ben2,
         num_docu_ben2,
         tip_docu_ben2,
         fec_proc,
         cod_esta_ocr,
         mot_rech_ocr,
         --
         cod_regi_orig_docu,
         cod_zona_orig_docu,
         --
         ind_firm,
         ind_orig)
      VALUES
        (v_cod_pais(i),
         v_cod_comp(i),
         v_num_cert(i),
         v_cod_peri(i),
         v_tip_docu_iden(i),
         v_num_docu_iden(i),
         v_cod_clie(i),
         v_num_coas(i),
         v_nom_ben1(i),
         v_num_docu_ben1(i),
         v_tip_docu_ben1(i),
         v_nom_ben2(i),
         v_num_docu_ben2(i),
         v_tip_docu_ben2(i),
         v_fec_proc(i),
         v_cod_esta(i),
         v_cod_moti_rech(i),
         --
         v_cod_regi_orig_docu(i),
         v_cod_zona_orig_docu(i),
         --
         v_ind_firm(i),
         psindicadororigen);
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_FAMIL_SEGUR: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_recep_famil_segur;

  /***************************************************************************
  Descripcion       : Consolida las Cabeceras de Familia Segura
  Fecha Creacion    : 02/05/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_famil_segur
  (
    pscodigopais          VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2,
    pscodigotipodocumento VARCHAR2
  ) IS
    CURSOR curinsconsolfs IS
      SELECT cod_pais cod_pais,
             cod_comp cod_comp,
             num_docu num_docu,
             fec_proc, --SYSDATE fec_proc, -- Fecha y hora
             cam_proc cam_proc,
             cod_clie cod_clie,
             nvl(tip_docu_iden, '01') tip_docu_iden, -- Si no lo envia, siempre coloca '01'
             num_docu_iden num_docu_iden,
             NULL cam_inic,
             can_coas can_coas,
             seq_docu_sto.nextval sec_nume_docu,
             psnumlote num_lote,
             NULL cam_regis,
             nom_ben1 nom_ben1,
             tip_docu_ben1 tip_docu_ben1,
             num_docu_ben1 num_docu_ben1,
             nom_ben2 nom_ben2,
             tip_docu_ben2 tip_docu_ben2,
             num_docu_ben2 num_docu_ben2,
             NULL clie_oid_clie,
             NULL tdoc_oid_tipo_docu,
             NULL zzon_oid_zona,
             NULL zorg_oid_regi,
             NULL fec_naci,
             NULL cod_sexo,
             NULL cod_esta_civi,
             NULL val_edad,
             cod_esta_ocr cod_esta_ocr,
             mot_rech_ocr mot_rech_ocr,
             NULL cam_anti,
             ---------
             -- Si la zona de arribo enviada, se encuentra parametrizada, se reemplaza la region y
             -- zona de arribo por la region y la zona de la consultora para evitar la validacion
             -- de Region y Zona de Arribo (FAS-12)
             ---------
             CASE
               WHEN nvl(cod_zona_orig_docu, '0') IN
                    (SELECT pg.val_param
                       FROM sto_param_gener_occrr pg
                      WHERE pg.cod_para LIKE 'FAS_ZON_ARRI_GENER_%') THEN
                (SELECT zr.cod_regi
                   FROM mae_clien             m,
                        mae_clien_unida_admin ua,
                        zon_terri_admin       zta,
                        zon_secci             zs,
                        zon_zona              zz,
                        zon_regio             zr
                  WHERE m.oid_clie = ua.clie_oid_clie
                    AND m.cod_clie = sgr.cod_clie
                    AND ua.ind_acti = '1'
                    AND zta.oid_terr_admi = ua.ztad_oid_terr_admi
                    AND zta.ind_borr = '0'
                    AND zs.oid_secc = zta.zscc_oid_secc
                    AND zz.oid_zona = zs.zzon_oid_zona
                    AND zr.oid_regi = zz.zorg_oid_regi
                    AND rownum = 1)
               ELSE
                cod_regi_orig_docu
             END cod_regi_orig_docu,
             
             CASE
               WHEN nvl(cod_zona_orig_docu, '0') IN
                    (SELECT pg.val_param
                       FROM sto_param_gener_occrr pg
                      WHERE pg.cod_para LIKE 'FAS_ZON_ARRI_GENER_%') THEN
                (SELECT zz.cod_zona
                   FROM mae_clien             m,
                        mae_clien_unida_admin ua,
                        zon_terri_admin       zta,
                        zon_secci             zs,
                        zon_zona              zz,
                        zon_regio             zr
                  WHERE m.oid_clie = ua.clie_oid_clie
                    AND m.cod_clie = sgr.cod_clie
                    AND ua.ind_acti = '1'
                    AND zta.oid_terr_admi = ua.ztad_oid_terr_admi
                    AND zta.ind_borr = '0'
                    AND zs.oid_secc = zta.zscc_oid_secc
                    AND zz.oid_zona = zs.zzon_oid_zona
                    AND zr.oid_regi = zz.zorg_oid_regi
                    AND rownum = 1)
               ELSE
                cod_zona_orig_docu
             END cod_zona_orig_docu,
             ---------------------------
             --
             ind_firm,
             ind_orig
      --
        FROM int_ocr_famil_segur sgr
       WHERE cod_pais = pscodigopais;
  
    TYPE solic_famil_segur_tab_t IS TABLE OF int_solic_conso_famil_segur%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_famil_segur_tab solic_famil_segur_tab_t; -- In-memory table
  
    j BINARY_INTEGER := 0;
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t; -- In-memory table
  
  BEGIN
  
    OPEN curinsconsolfs;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsolfs BULK COLLECT
        INTO sol_famil_segur_tab LIMIT w_filas;
      EXIT WHEN sol_famil_segur_tab.count = 0;
    
      FOR j IN sol_famil_segur_tab.first .. sol_famil_segur_tab.last
      LOOP
        -- Manipumate data in the memory table...
        sto_tab(j).cod_pais := sol_famil_segur_tab(j).cod_pais;
        sto_tab(j).cod_tipo_docu := pscodigotipodocumento;
        sto_tab(j).num_lote := sol_famil_segur_tab(j).num_lote;
        sto_tab(j).sec_nume_docu := sol_famil_segur_tab(j).sec_nume_docu;
        sto_tab(j).num_docu := sol_famil_segur_tab(j).num_docu;
        sto_tab(j).ind_envi := '0';
        sto_tab(j).ind_rech := '0';
        sto_tab(j).fec_digi := SYSDATE;
        sto_tab(j).usu_digi := pscodigousuario;
        sto_tab(j).fec_modi := SYSDATE;
        sto_tab(j).usu_modi := pscodigousuario;
        sto_tab(j).cod_zona := gen_pkg_gener.gen_fn_clien_datos_codig(sol_famil_segur_tab(j)
                                                                      .cod_clie,
                                                                      'COD_ZONA');
        sto_tab(j).cod_clie := sol_famil_segur_tab(j).cod_clie;
        sto_tab(j).cod_regi := gen_pkg_gener.gen_fn_clien_datos_codig(sol_famil_segur_tab(j)
                                                                      .cod_clie,
                                                                      'COD_REGI');
        --        sto_tab(j).sec_nume_docu_cabe := sol_famil_segur_tab(j).sec_nume_docu_cabe;
        sto_tab(j).cod_peri := sol_famil_segur_tab(j).cam_proc;
        --sto_tab(j).COD_MOTI_RECH      :=
        -- sto_tab(j).val_obse_rech_defi := sol_famil_segur_tab(j).val_obse_rech_defi;
        sto_tab(j).ind_rece_ocr := '0';
        sto_tab(j).ind_rece_web := '0';
        sto_tab(j).ind_rece_dd := '0';
        sto_tab(j).ind_rece_digi := '0';
        sto_tab(j).ind_rece_cc := '0';
        sto_tab(j).ind_rece_mens := '0';
        sto_tab(j).ind_elim := '0';
        sto_tab(j).cod_zona_arri := sol_famil_segur_tab(j)
                                    .cod_zona_orig_docu;
        sto_tab(j).ind_rece_onli := '0';
        sto_tab(j).ind_rece_ivr := '0';
      
        IF sol_famil_segur_tab(j).ind_orig = 'O' THEN
          sto_tab(j).ind_rece_ocr := '1';
        ELSIF sol_famil_segur_tab(j).ind_orig = 'W' THEN
          sto_tab(j).ind_rece_web := '1';
        END IF;
      
      END LOOP;
    
      FORALL j IN sol_famil_segur_tab.first .. sol_famil_segur_tab.last
        INSERT INTO int_solic_conso_famil_segur
        VALUES sol_famil_segur_tab
          (j);
      FORALL j IN sol_famil_segur_tab.first .. sol_famil_segur_tab.last
        INSERT INTO sto_docum_digit VALUES sto_tab (j);
    END LOOP;
    CLOSE curinsconsolfs;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_FAMIL_SEGUR: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_famil_segur;

  /******************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Familia Segura WEB
  Fecha Creacion    : 02/05/2011
  Autor             : Jose Luis Rodriguez
  *********************************************************************************/
  PROCEDURE int_pr_web_recep_famil_segur
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_famil_segur.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_famil_segur.cod_comp%TYPE;
    TYPE t_num_cert IS TABLE OF int_ocr_famil_segur.num_docu%TYPE;
    TYPE t_cod_peri IS TABLE OF int_ocr_famil_segur.cam_proc%TYPE;
    TYPE t_tip_docu_iden IS TABLE OF int_ocr_famil_segur.tip_docu_iden%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_ocr_famil_segur.num_docu_iden%TYPE;
    TYPE t_cod_clie IS TABLE OF int_ocr_famil_segur.cod_clie%TYPE;
    TYPE t_num_coas IS TABLE OF int_ocr_famil_segur.can_coas%TYPE;
    TYPE t_nom_ben1 IS TABLE OF int_ocr_famil_segur.nom_ben1%TYPE;
    TYPE t_num_docu_ben1 IS TABLE OF int_ocr_famil_segur.num_docu_ben1%TYPE;
    TYPE t_tip_docu_ben1 IS TABLE OF int_ocr_famil_segur.tip_docu_ben1%TYPE;
    TYPE t_nom_ben2 IS TABLE OF int_ocr_famil_segur.nom_ben2%TYPE;
    TYPE t_num_docu_ben2 IS TABLE OF int_ocr_famil_segur.num_docu_ben2%TYPE;
    TYPE t_tip_docu_ben2 IS TABLE OF int_ocr_famil_segur.tip_docu_ben2%TYPE;
    TYPE t_fec_proc IS TABLE OF int_ocr_famil_segur.fec_proc%TYPE;
    TYPE t_cod_esta IS TABLE OF int_ocr_famil_segur.cod_esta_ocr%TYPE;
    TYPE t_cod_moti_rech IS TABLE OF int_ocr_famil_segur.mot_rech_ocr%TYPE;
    --
    TYPE t_cod_regi_orig_docu IS TABLE OF int_ocr_famil_segur.cod_regi_orig_docu%TYPE;
    TYPE t_cod_zona_orig_docu IS TABLE OF int_ocr_famil_segur.cod_zona_orig_docu%TYPE;
    --
    TYPE t_ind_firm IS TABLE OF int_ocr_famil_segur.ind_firm%TYPE;
    --
    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_comp      t_cod_comp := t_cod_comp();
    v_num_cert      t_num_cert := t_num_cert();
    v_cod_peri      t_cod_peri := t_cod_peri();
    v_tip_docu_iden t_tip_docu_iden := t_tip_docu_iden();
    v_num_docu_iden t_num_docu_iden := t_num_docu_iden();
    v_cod_clie      t_cod_clie := t_cod_clie();
    v_num_coas      t_num_coas := t_num_coas();
    v_nom_ben1      t_nom_ben1 := t_nom_ben1();
    v_num_docu_ben1 t_num_docu_ben1 := t_num_docu_ben1();
    v_tip_docu_ben1 t_tip_docu_ben1 := t_tip_docu_ben1();
    v_nom_ben2      t_nom_ben2 := t_nom_ben2();
    v_num_docu_ben2 t_num_docu_ben2 := t_num_docu_ben2();
    v_tip_docu_ben2 t_tip_docu_ben2 := t_tip_docu_ben2();
    v_fec_proc      t_fec_proc := t_fec_proc();
    v_cod_esta      t_cod_esta := t_cod_esta();
    v_cod_moti_rech t_cod_moti_rech := t_cod_moti_rech();
    --
    v_cod_regi_orig_docu t_cod_regi_orig_docu := t_cod_regi_orig_docu();
    v_cod_zona_orig_docu t_cod_zona_orig_docu := t_cod_zona_orig_docu();
    --
    v_ind_firm t_ind_firm := t_ind_firm();
    --
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    i               BINARY_INTEGER := 0;
    posicion        NUMBER := 0;
    longitud        NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
        
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
        
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
          
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                  v_cod_pais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_cod_pais(i),
                                                                               v_cod_comp(i));
                ELSIF (posicion = 3) THEN
                  v_num_cert.extend;
                  v_num_cert(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 4) THEN
                  v_cod_peri.extend;
                  v_cod_peri(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 5) THEN
                  v_tip_docu_iden.extend;
                  v_tip_docu_iden(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 6) THEN
                  v_num_docu_iden.extend;
                  v_num_docu_iden(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 7) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 8) THEN
                  v_num_coas.extend;
                  v_num_coas(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 9) THEN
                  v_nom_ben1.extend;
                  v_nom_ben1(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 10) THEN
                  v_num_docu_ben1.extend;
                  v_num_docu_ben1(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 11) THEN
                  v_tip_docu_ben1.extend;
                  v_tip_docu_ben1(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 12) THEN
                  v_nom_ben2.extend;
                  v_nom_ben2(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 13) THEN
                  v_num_docu_ben2.extend;
                  v_num_docu_ben2(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 14) THEN
                  v_tip_docu_ben2.extend;
                  v_tip_docu_ben2(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 15) THEN
                  v_fec_proc.extend;
                  -- Si la fecha llega con un formato invalido, se captura la
                  -- excepcion y se le coloca null
                  BEGIN
                    v_fec_proc(i) := to_date(TRIM(substr(lslinea,
                                                         inicio,
                                                         longitud)),
                                             'yyyyMMdd');
                  EXCEPTION
                    WHEN OTHERS THEN
                      v_fec_proc(i) := NULL;
                  END;
                
                ELSIF (posicion = 16) THEN
                  v_cod_esta.extend;
                  v_cod_esta(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 17) THEN
                  v_cod_moti_rech.extend;
                  v_cod_moti_rech(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                  --
                ELSIF (posicion = 18) THEN
                  -- REGION DE ARRIBO
                  v_cod_regi_orig_docu.extend;
                  v_cod_regi_orig_docu(i) := TRIM(translate(substr(lslinea,
                                                                   inicio,
                                                                   longitud),
                                                            lscadena,
                                                            lsreplace));
                ELSIF (posicion = 19) THEN
                  -- ZONA DE ARRIBO
                  v_cod_zona_orig_docu.extend;
                  v_cod_zona_orig_docu(i) := TRIM(translate(substr(lslinea,
                                                                   inicio,
                                                                   longitud),
                                                            lscadena,
                                                            lsreplace));
                  --
                ELSIF (posicion = 20) THEN
                  -- INDICADOR DE FIRMA
                  v_ind_firm.extend;
                  v_ind_firm(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                  --
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_cod_pais.count
      INSERT INTO int_ocr_famil_segur
        (cod_pais,
         cod_comp,
         num_docu,
         cam_proc,
         tip_docu_iden,
         num_docu_iden,
         cod_clie,
         can_coas,
         nom_ben1,
         num_docu_ben1,
         tip_docu_ben1,
         nom_ben2,
         num_docu_ben2,
         tip_docu_ben2,
         fec_proc,
         cod_esta_ocr,
         mot_rech_ocr,
         cod_regi_orig_docu,
         cod_zona_orig_docu,
         ind_firm,
         ind_orig)
      VALUES
        (v_cod_pais(i),
         v_cod_comp(i),
         v_num_cert(i),
         v_cod_peri(i),
         v_tip_docu_iden(i),
         v_num_docu_iden(i),
         v_cod_clie(i),
         v_num_coas(i),
         v_nom_ben1(i),
         v_num_docu_ben1(i),
         v_tip_docu_ben1(i),
         v_nom_ben2(i),
         v_num_docu_ben2(i),
         v_tip_docu_ben2(i),
         v_fec_proc(i),
         v_cod_esta(i),
         v_cod_moti_rech(i),
         v_cod_regi_orig_docu(i),
         v_cod_zona_orig_docu(i),
         v_ind_firm(i),
         psindicadororigen);
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_WEB_RECEP_FAMIL_SEGUR: ' ||
                              ls_sqlerrm);
    
  END int_pr_web_recep_famil_segur;

  /***************************************************************************
    Descripcion       : Consolida las Cabeceras de MICA Web
    Fecha Creacion    : 22/06/2011
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_micaw_cabec
  (
    pscodigopais      VARCHAR2,
    pscodigodocumento VARCHAR2,
    pscodigousuario   VARCHAR2
  ) IS
    CURSOR curinsconsol IS
      SELECT c.cod_clie cod_cliente,
             c.cam_soli cod_periodo,
             MAX(c.fec_proc) fec_proceso
        FROM int_solic_cabec c
       WHERE c.cod_pais = pscodigopais
       GROUP BY c.cod_clie,
                c.cam_soli;
  
    TYPE interfazcab IS RECORD(
      codcliente int_solic_cabec.cod_clie%TYPE,
      codperiodo int_solic_cabec.cam_soli%TYPE,
      fecproceso int_solic_cabec.fec_proc%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
    interfazcabrecord interfazcabtab;
  
    vncontador      NUMBER;
    vnoidcliente    NUMBER;
    vnoidterritorio NUMBER;
  
  BEGIN
  
    OPEN curinsconsol;
    LOOP
      FETCH curinsconsol BULK COLLECT
        INTO interfazcabrecord LIMIT w_filas;
    
      IF interfazcabrecord.count > 0 THEN
        FOR x IN interfazcabrecord.first .. interfazcabrecord.last
        LOOP
        
          --Se valida que el cliente no este registrado
          SELECT COUNT(1)
            INTO vncontador
            FROM int_mica_conso_cabec m
           WHERE m.cod_clie = interfazcabrecord(x).codcliente
             AND m.cod_peri = interfazcabrecord(x).codperiodo;
        
          -- Si existe el cliente se eliminan de las tablas
          IF (vncontador > 0) THEN
          
            DELETE FROM int_mica_conso_detal
             WHERE cod_clie = interfazcabrecord(x).codcliente
               AND cod_peri = interfazcabrecord(x).codperiodo;
          
            DELETE FROM int_mica_conso_cabec
             WHERE cod_clie = interfazcabrecord(x).codcliente
               AND cod_peri = interfazcabrecord(x).codperiodo;
          
          END IF;
        
          BEGIN
            SELECT oid_clie
              INTO vnoidcliente
              FROM mae_clien
             WHERE cod_clie = interfazcabrecord(x).codcliente;
          EXCEPTION
            WHEN no_data_found THEN
              vnoidcliente := NULL;
          END;
        
          BEGIN
            SELECT ztad_oid_terr_admi
              INTO vnoidterritorio
              FROM mae_clien_unida_admin
             WHERE ind_acti = 1
               AND clie_oid_clie = vnoidcliente;
          EXCEPTION
            WHEN no_data_found THEN
              vnoidterritorio := NULL;
          END;
          /* DESCOMENTAR PARA HABILITAR REGISTRO DE MICAS EN INT_SOLIC_CONSO_CABEC*/
          /*EVI_PKG_EJECU_VIRTU.evi_pr_inser_regis_archi_mica(
          pscodigopais,
          interfazcabrecord(x).codPeriodo,
          interfazcabrecord(x).codCliente,
          'N',
          interfazcabrecord(x).fecProceso
          );*/
        
          INSERT INTO int_mica_conso_cabec
          VALUES
            (interfazcabrecord(x).codcliente,
             interfazcabrecord(x).codperiodo,
             vnoidcliente,
             vnoidterritorio);
        END LOOP;
      END IF;
    
      EXIT WHEN curinsconsol%NOTFOUND;
    END LOOP;
    CLOSE curinsconsol;
  
    -- Se inserta en la tabla detalle de Mica WEB
    ocr_pr_conso_micaw_detal(pscodigopais,
                             pscodigodocumento,
                             pscodigousuario);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_MICAW_CABEC: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_micaw_cabec;

  /***************************************************************************
  Descripcion       : Consolida los Detalles de MICA Web
  Fecha Creacion    : 22/06/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_micaw_detal
  (
    pscodigopais      VARCHAR2,
    pscodigodocumento VARCHAR2,
    pscodigousuario   VARCHAR2
  ) IS
    CURSOR curinsconsol IS
      SELECT p.cod_clie cod_cliente,
             p.cam_soli cod_periodo,
             TRIM(p.cod_prod) cod_venta,
             SUM(p.uni_dema) uni_demanda
        FROM int_solic_posic p
       WHERE p.cod_pais = pscodigopais
       GROUP BY p.cod_clie,
                p.cam_soli,
                TRIM(p.cod_prod);
  
    TYPE interfazdet IS RECORD(
      codcliente int_solic_cabec.cod_clie%TYPE,
      codperiodo int_solic_cabec.cam_soli%TYPE,
      codventa   int_solic_posic.cod_vent%TYPE,
      unidemanda int_solic_posic.uni_dema%TYPE);
  
    TYPE interfazdettab IS TABLE OF interfazdet;
    interfazdetrecord interfazdettab;
  
  BEGIN
  
    OPEN curinsconsol;
    LOOP
      FETCH curinsconsol BULK COLLECT
        INTO interfazdetrecord LIMIT w_filas;
    
      IF interfazdetrecord.count > 0 THEN
        FOR x IN interfazdetrecord.first .. interfazdetrecord.last
        LOOP
        
          INSERT INTO int_mica_conso_detal
          VALUES
            (interfazdetrecord(x).codcliente,
             interfazdetrecord(x).codperiodo,
             interfazdetrecord(x).codventa,
             interfazdetrecord(x).unidemanda);
        
        END LOOP;
      END IF;
    
      EXIT WHEN curinsconsol%NOTFOUND;
    END LOOP;
    CLOSE curinsconsol;
    /* DESCOMENTAR PARA HABILITAR REGISTRO DE MICAS EN INT_SOLIC_CONSO_CABEC*/
    --    GEN_PKG_GENER.gen_pr_actua_valor_saldo_deudo(pscodigopais);
    --    EVI_PKG_EJECU_VIRTU.EVI_PR_CARGA_RESUM_PRE_FACTU;
    --    OCR_SOLIC_PEDIDOS.OCR_PR_ACT_IND_CONT_ACT(pscodigopais);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_MICAW_DETAL: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_micaw_detal;

  /**************************************************************************
    Descripcion       : ocr_pr_carga_tempo_pedid_digit
                        Inserta Cabeceras Detalles hacia temporales de Pedidos
    Fecha Creacion    : 26/02/2008
    Parametros Entrada:
        psCodigoPais : Codigo de pais
        psCodigoPeriodo : Codigo de periodo
        psnumerolotesto   Lote generado por STO,
        psindicadororigen Origen (DIGITADO ->G),
        pscodigointerfaz  codigo de Interfaz,
        psnumerolote      numero Lote de interfaz.
    Autor             : Jose Cairampoma
  ****************************************************************************/
  PROCEDURE ocr_pr_carga_tempo_pedid_digit
  (
    pscodigopais      VARCHAR2,
    pscodigoperiodo   VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnumerolote      VARCHAR2
  ) AS
    CURSOR curinsconsolcabec IS
      SELECT cab.cod_pais,
             cab.cod_peri,
             cab.cod_clie,
             0,
             'SOC',
             '000',
             '01',
             'N',
             cab.fec_soli,
             'A',
             seq_solic_cab.nextval,
             cab.num_lote,
             NULL                  cod_comp,
             NULL                  num_docu,
             cab.cod_regi          cod_regi_arri,
             cab.cod_zona          cod_zona_arri,
             NULL                  cod_moti_rech,
             cab.ind_proc          ind_proc,
             NULL                  fec_proc,
             NULL                  fec_fact,
             NULL                  num_lote_dd,
             NULL                  ind_clie_vali,
             NULL                  ind_docu_iden,
             psnumerolotesto       num_lote_sto,
             pscodigointerfaz      cod_inte,
             psnumerolote          num_lote_inte,
             NULL                  val_acci,
             NULL                  xml_pedi_entr,
             NULL                  xml_pedi_sali,
             NULL                  soca_oid_docu_refe,
             NULL,
             NULL,
             NULL,
             NULL,
             NULL
        FROM ped_solic_digit_cabec cab
       WHERE cab.cod_pais = pscodigopais
         AND cab.cod_peri = pscodigoperiodo
         AND cab.ind_proc = psindicadororigen
         AND EXISTS (SELECT NULL
                FROM ped_solic_digit_detal det
               WHERE det.cod_pais = cab.cod_pais
                 AND det.cod_peri = cab.cod_peri
                 AND det.cod_clie = cab.cod_clie
                 AND det.num_lote = cab.num_lote
                 AND det.ind_ocs_detal = '0'
                 AND det.ind_proc = psindicadororigen);
  
    CURSOR curinsconsoldet IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             'OC',
             det.cod_vent,
             det.val_unid_dem,
             'A' sta_proc,
             seq_solic_pos.nextval,
             det.num_lote,
             NULL cod_comp,
             NULL num_docu,
             NULL cod_moti_rech,
             NULL num_line_oc,
             det.ind_proc ind_proc,
             NULL cod_vent,
             NULL num_lote_dd,
             psnumerolotesto num_lote_sto,
             pscodigointerfaz cod_inte,
             psnumerolote num_lote_inte
        FROM ped_solic_digit_detal det
       WHERE det.cod_pais = pscodigopais
         AND det.cod_peri = pscodigoperiodo
         AND det.ind_ocs_detal = '0'
         AND det.ind_proc = psindicadororigen;
  
    TYPE solic_cab_tab_t IS TABLE OF int_solic_cabec%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_cab_tab solic_cab_tab_t;
  
    TYPE solic_det_tab_t IS TABLE OF int_solic_posic%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_det_tab solic_det_tab_t;
  
    rows NATURAL := 10000;
    i    BINARY_INTEGER := 0;
    j    BINARY_INTEGER := 0;
  
    lsnumerolote bas_ctrl_fact.num_lote%TYPE;
  
  BEGIN
  
    SELECT bas.num_lote
      INTO lsnumerolote
      FROM bas_ctrl_fact bas
     WHERE bas.cod_pais = pscodigopais
       AND bas.cod_peri = pscodigoperiodo;
  
    OPEN curinsconsolcabec;
    LOOP
      FETCH curinsconsolcabec BULK COLLECT
        INTO sol_cab_tab LIMIT rows;
      EXIT WHEN sol_cab_tab.count = 0;
    
      FOR j IN sol_cab_tab.first .. sol_cab_tab.last
      LOOP
        sol_cab_tab(j).num_lote := lsnumerolote;
      END LOOP;
    
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        INSERT INTO int_solic_cabec VALUES sol_cab_tab (i);
    
    END LOOP;
    CLOSE curinsconsolcabec;
  
    OPEN curinsconsoldet;
    LOOP
    
      FETCH curinsconsoldet BULK COLLECT
        INTO sol_det_tab LIMIT rows;
      EXIT WHEN sol_det_tab.count = 0;
    
      FOR j IN sol_det_tab.first .. sol_det_tab.last
      LOOP
        sol_det_tab(j).num_lote := lsnumerolote;
      END LOOP;
    
      FORALL j IN sol_det_tab.first .. sol_det_tab.last
        INSERT INTO int_solic_posic VALUES sol_det_tab (j);
    
    END LOOP;
    CLOSE curinsconsoldet;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CARGA_TEMPO_PEDID_DIGIT: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_carga_tempo_pedid_digit;

  /**************************************************************************
    Descripcion       : ocr_pr_marca_pedid_digit
                        Marca los Pedidos Digitados que fueron Consolidados
    Fecha Creacion    : 27/02/2008
    Parametros Entrada:
        psCodigoPeriodo : Codigo de periodo
        psnumerolotesto   Lote generado por STO,
  
    Autor             : Jose Cairampoma
  ****************************************************************************/
  PROCEDURE ocr_pr_marca_pedid_digit
  (
    pscodigoperiodo VARCHAR2,
    psnumerolotesto VARCHAR2
  ) AS
    CURSOR curinsconsolcabec IS
      SELECT cab.cam_soli,
             cab.cod_clie,
             cab.ind_proc
        FROM int_solic_cabec cab
       WHERE cab.cam_soli = pscodigoperiodo
         AND cab.num_lote_sto = psnumerolotesto;
  
    TYPE t_cam_soli IS TABLE OF int_solic_cabec.cam_soli%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_cabec.cod_clie%TYPE;
    TYPE t_ind_proc IS TABLE OF int_solic_cabec.ind_proc%TYPE;
  
    v_cam_soli t_cam_soli;
    v_cod_clie t_cod_clie;
    v_ind_proc t_ind_proc;
  
    rows NATURAL := 10000;
    i    BINARY_INTEGER := 0;
  
  BEGIN
  
    OPEN curinsconsolcabec;
    LOOP
      FETCH curinsconsolcabec BULK COLLECT
        INTO v_cam_soli,
             v_cod_clie,
             v_ind_proc LIMIT rows;
    
      IF v_cam_soli.count > 0 THEN
      
        FORALL i IN 1 .. v_cam_soli.count
          UPDATE ped_solic_digit_cabec c
             SET c.ind_ocs = '1'
           WHERE c.ind_proc = v_ind_proc(i)
             AND c.cod_peri = v_cam_soli(i)
             AND c.cod_clie = v_cod_clie(i);
      
        FORALL i IN 1 .. v_cam_soli.count
          UPDATE ped_solic_digit_detal c
             SET c.ind_ocs_detal = '1'
           WHERE c.ind_proc = v_ind_proc(i)
             AND c.cod_peri = v_cam_soli(i)
             AND c.cod_clie = v_cod_clie(i);
      END IF;
      EXIT WHEN curinsconsolcabec%NOTFOUND;
    END LOOP;
    CLOSE curinsconsolcabec;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_MARCA_PEDID_DIGIT: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_marca_pedid_digit;
  /**************************************************************************
    Descripcion       : Elimina los pedidos con preimpreso duplicado
    Fecha Creacion    : 22/04/2013
    Autor             : Jose Cairampoma
  ****************************************************************************/
  PROCEDURE ocr_pr_elimi_pedid_dupli_preim
  (
    pscodigopais    VARCHAR2,
    psnumerolotesto VARCHAR2
  ) IS
  
    CURSOR cursolcabec IS
      SELECT cod_pais,
             cam_soli,
             cod_clie,
             num_clie,
             tip_soli,
             cod_suba,
             cod_acce,
             tip_desp,
             fec_soli,
             sta_proc,
             oid_cab,
             num_lote,
             cod_comp,
             num_docu,
             cod_regi_arri,
             cod_zona_arri,
             cod_moti_rech,
             ind_proc,
             fec_proc,
             fec_fact,
             num_lote_dd,
             ind_clie_vali,
             ind_docu_iden,
             num_lote_sto,
             cod_inte,
             num_lote_inte,
             val_acci,
             xml_pedi_entr,
             xml_pedi_sali,
             soca_oid_soli_cabe
        FROM int_solic_cabec cab
       WHERE cab.cod_pais = pscodigopais
         AND cab.num_lote_sto = psnumerolotesto
         AND EXISTS (SELECT NULL
                FROM int_solic_pedid_detal ped
               WHERE ped.cod_peri = cab.cam_soli
                 AND ped.cod_clie = cab.cod_clie
                 AND ped.num_docu = cab.num_docu);
  
    TYPE solic_cab_tab_t IS TABLE OF int_solic_cabec_recha%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_cab_tab solic_cab_tab_t;
  
    rows NATURAL := 10000;
  BEGIN
  
    OPEN cursolcabec;
    LOOP
      FETCH cursolcabec BULK COLLECT
        INTO sol_cab_tab LIMIT rows;
      EXIT WHEN sol_cab_tab.count = 0;
    
      FOR j IN sol_cab_tab.first .. sol_cab_tab.last
      LOOP
        sol_cab_tab(j).oid_cab := seq_solic_cabec_rec.nextval;
      END LOOP;
    
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        INSERT INTO int_solic_cabec_recha VALUES sol_cab_tab (i);
    
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        INSERT INTO int_solic_posic_recha
          (cod_pais,
           cam_soli,
           cod_clie,
           tip_posi,
           cod_prod,
           uni_dema,
           sta_proc,
           oid_posic,
           num_lote,
           cod_comp,
           num_docu,
           cod_moti_rech,
           num_line_oc,
           ind_proc,
           cod_vent,
           num_lote_dd,
           num_lote_sto,
           cod_inte,
           num_lote_inte)
          SELECT pos.cod_pais,
                 pos.cam_soli,
                 pos.cod_clie,
                 pos.tip_posi,
                 pos.cod_prod,
                 pos.uni_dema,
                 pos.sta_proc,
                 seq_solic_posic_rec.nextval,
                 pos.num_lote,
                 pos.cod_comp,
                 pos.num_docu,
                 pos.cod_moti_rech,
                 pos.num_line_oc,
                 pos.ind_proc,
                 pos.cod_vent,
                 pos.num_lote_dd,
                 pos.num_lote_sto,
                 pos.cod_inte,
                 pos.num_lote_inte
            FROM int_solic_posic pos
           WHERE pos.cod_pais = sol_cab_tab(i).cod_pais
             AND pos.cam_soli = sol_cab_tab(i).cam_soli
             AND pos.cod_clie = sol_cab_tab(i).cod_clie
             AND pos.num_lote = sol_cab_tab(i).num_lote
             AND pos.num_lote_sto = sol_cab_tab(i).num_lote_sto;
    
      -- Se eliminan la cabecera con sus detalles
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        DELETE int_solic_posic
         WHERE cod_pais = sol_cab_tab(i).cod_pais
           AND cam_soli = sol_cab_tab(i).cam_soli
           AND cod_clie = sol_cab_tab(i).cod_clie
           AND num_lote = sol_cab_tab(i).num_lote
           AND num_lote_sto = sol_cab_tab(i).num_lote_sto;
    
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        DELETE FROM int_solic_cabec
         WHERE cod_pais = sol_cab_tab(i).cod_pais
           AND cam_soli = sol_cab_tab(i).cam_soli
           AND cod_clie = sol_cab_tab(i).cod_clie
           AND num_lote = sol_cab_tab(i).num_lote;
    
    END LOOP;
    CLOSE cursolcabec;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ocr_pr_elimi_pedid_dupli_preim: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_elimi_pedid_dupli_preim;
  /**************************************************************************
    Descripcion       : Elimina los pedidos duplicados que llegan por WEB y DD
    Fecha Creacion    : 09/02/2012
    Autor             : Jose Luis Rodriguez
  ****************************************************************************/
  PROCEDURE ocr_pr_elimi_pedid_dupli_webdd
  (
    pscodigopais    VARCHAR2,
    psnumerolotesto VARCHAR2
  ) IS
  
    CURSOR cursolcabec IS
      SELECT cod_pais,
             cam_soli,
             cod_clie,
             num_clie,
             tip_soli,
             cod_suba,
             cod_acce,
             tip_desp,
             fec_soli,
             sta_proc,
             oid_cab,
             num_lote,
             cod_comp,
             num_docu,
             cod_regi_arri,
             cod_zona_arri,
             cod_moti_rech,
             ind_proc,
             fec_proc,
             fec_fact,
             num_lote_dd,
             ind_clie_vali,
             ind_docu_iden,
             num_lote_sto,
             cod_inte,
             num_lote_inte,
             val_acci,
             xml_pedi_entr,
             xml_pedi_sali,
             soca_oid_soli_cabe
        FROM int_solic_cabec cab
       WHERE cab.cod_pais = pscodigopais
         AND cab.num_lote_sto = psnumerolotesto
         AND EXISTS
       (SELECT NULL
                FROM int_solic_conso_cabec cns
               WHERE cns.cod_peri = cab.cam_soli
                 AND cns.cod_clie = cab.cod_clie
                 AND ((cns.ind_rece_web = '1' AND cab.ind_proc = 'W') OR
                     (cns.ind_rece_dd = '1' AND cab.ind_proc = 'D')));
  
    TYPE solic_cab_tab_t IS TABLE OF int_solic_cabec_recha%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_cab_tab solic_cab_tab_t;
  
    rows NATURAL := 10000;
  BEGIN
  
    OPEN cursolcabec;
    LOOP
      FETCH cursolcabec BULK COLLECT
        INTO sol_cab_tab LIMIT rows;
      EXIT WHEN sol_cab_tab.count = 0;
    
      FOR j IN sol_cab_tab.first .. sol_cab_tab.last
      LOOP
        sol_cab_tab(j).oid_cab := seq_solic_cabec_rec.nextval;
      END LOOP;
    
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        INSERT INTO int_solic_cabec_recha VALUES sol_cab_tab (i);
    
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        INSERT INTO int_solic_posic_recha
          (cod_pais,
           cam_soli,
           cod_clie,
           tip_posi,
           cod_prod,
           uni_dema,
           sta_proc,
           oid_posic,
           num_lote,
           cod_comp,
           num_docu,
           cod_moti_rech,
           num_line_oc,
           ind_proc,
           cod_vent,
           num_lote_dd,
           num_lote_sto,
           cod_inte,
           num_lote_inte)
          SELECT pos.cod_pais,
                 pos.cam_soli,
                 pos.cod_clie,
                 pos.tip_posi,
                 pos.cod_prod,
                 pos.uni_dema,
                 pos.sta_proc,
                 seq_solic_posic_rec.nextval,
                 pos.num_lote,
                 pos.cod_comp,
                 pos.num_docu,
                 pos.cod_moti_rech,
                 pos.num_line_oc,
                 pos.ind_proc,
                 pos.cod_vent,
                 pos.num_lote_dd,
                 pos.num_lote_sto,
                 pos.cod_inte,
                 pos.num_lote_inte
            FROM int_solic_posic pos
           WHERE pos.cod_pais = sol_cab_tab(i).cod_pais
             AND pos.cam_soli = sol_cab_tab(i).cam_soli
             AND pos.cod_clie = sol_cab_tab(i).cod_clie
             AND pos.num_lote = sol_cab_tab(i).num_lote
             AND pos.num_lote_sto = sol_cab_tab(i).num_lote_sto;
    
      -- Se eliminan la cabecera con sus detalles
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        DELETE int_solic_posic
         WHERE cod_pais = sol_cab_tab(i).cod_pais
           AND cam_soli = sol_cab_tab(i).cam_soli
           AND cod_clie = sol_cab_tab(i).cod_clie
           AND num_lote = sol_cab_tab(i).num_lote
           AND num_lote_sto = sol_cab_tab(i).num_lote_sto;
    
      FORALL i IN sol_cab_tab.first .. sol_cab_tab.last
        DELETE FROM int_solic_cabec
         WHERE cod_pais = sol_cab_tab(i).cod_pais
           AND cam_soli = sol_cab_tab(i).cam_soli
           AND cod_clie = sol_cab_tab(i).cod_clie
           AND num_lote = sol_cab_tab(i).num_lote;
    
    END LOOP;
    CLOSE cursolcabec;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR OCR_PR_ELIMI_PEDID_DUPLI_WEBDD: ' ||
                              ls_sqlerrm);
    
  END ocr_pr_elimi_pedid_dupli_webdd;

  /***************************************************************************
    Descripcion       : Genera Interfaz de Recepcion de Cabeceras de OCR
                        Flexipago
    Fecha Creacion    : 21/05/2012
    Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  PROCEDURE ocr_pr_recep_conso_cabec_flexi
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psnumerolote      VARCHAR2,
    psnumerolotesto   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_codpais IS TABLE OF int_solic_cabec.cod_pais%TYPE;
    TYPE t_codcia IS TABLE OF int_solic_cabec.cod_comp%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_cabec.num_docu%TYPE;
    TYPE t_camsoli IS TABLE OF int_solic_cabec.cam_soli%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_cabec.cod_clie%TYPE;
    TYPE t_numclie IS TABLE OF int_solic_cabec.num_clie%TYPE;
    TYPE t_tipsoli IS TABLE OF int_solic_cabec.tip_soli%TYPE;
    TYPE t_codsuba IS TABLE OF int_solic_cabec.cod_suba%TYPE;
    TYPE t_codacce IS TABLE OF int_solic_cabec.cod_acce%TYPE;
    TYPE t_tipdesp IS TABLE OF int_solic_cabec.tip_desp%TYPE;
    TYPE t_fecsoli IS TABLE OF int_solic_cabec.fec_soli%TYPE;
    TYPE t_regarri IS TABLE OF int_solic_cabec.cod_regi_arri%TYPE;
    TYPE t_zonarri IS TABLE OF int_solic_cabec.cod_zona_arri%TYPE;
    TYPE t_staproc IS TABLE OF VARCHAR2(2);
    TYPE t_motrech IS TABLE OF int_solic_cabec.cod_moti_rech%TYPE;
    TYPE t_monflex IS TABLE OF int_solic_cabec.val_mnto_flex%TYPE;
    TYPE t_indutif IS TABLE OF int_solic_cabec.ind_util_flex%TYPE;
    TYPE t_indacef IS TABLE OF int_solic_cabec.ind_acep_flex%TYPE;
  
    v_codpais t_codpais := t_codpais();
    v_codcia  t_codcia := t_codcia();
    v_numdocu t_numdocu := t_numdocu();
    v_camsoli t_camsoli := t_camsoli();
    v_codclie t_codclie := t_codclie();
    v_numclie t_numclie := t_numclie();
    v_tipsoli t_tipsoli := t_tipsoli();
    v_codsuba t_codsuba := t_codsuba();
    v_codacce t_codacce := t_codacce();
    v_tipdesp t_tipdesp := t_tipdesp();
    v_fecsoli t_fecsoli := t_fecsoli();
    v_regarri t_regarri := t_regarri();
    v_zonarri t_zonarri := t_zonarri();
    v_staproc t_staproc := t_staproc();
    v_motrech t_motrech := t_motrech();
    v_monflex t_monflex := t_monflex();
    v_indutif t_indutif := t_indutif();
    v_indacef t_indacef := t_indacef();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    i               BINARY_INTEGER := 0;
    posicion        NUMBER := 0;
    longitud        NUMBER := 0;
    inicio          NUMBER := 0;
  
    v_error_carga  NUMBER := 0;
    v_error_status NUMBER := 0;
  
    v_camactiva int_solic_cabec.cam_soli%TYPE;
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    --Obteniendo la periodo activa
    SELECT c.cod_peri
      INTO v_camactiva
      FROM bas_ctrl_fact c
     WHERE c.cod_pais = pscodigopais
       AND c.ind_camp_act = 1
       AND c.sta_camp = '0';
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          v_error_carga  := 0;
          v_error_status := 0;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_codpais.extend;
                  v_codpais(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '00');
                  -- Se valida que el pais no sea nulo si lo es se pone el pais por defecto
                  IF (v_codpais(i) = '00') THEN
                    v_codpais(i) := pscodigopais;
                    v_error_carga := 1;
                  END IF;
                
                ELSIF (posicion = 2) THEN
                  v_codcia.extend;
                  v_codcia(i) := TRIM(substr(lslinea, inicio, longitud));
                
                  v_codpais(i) := nvl(sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_codpais(i),
                                                                                  v_codcia(i)),
                                      '00');
                
                  -- Se valida que el pais no sea nulo si lo es se pone el pais por defecto
                  IF (v_codpais(i) = '00') THEN
                    v_codpais(i) := pscodigopais;
                    v_error_carga := 1;
                  END IF;
                
                ELSIF (posicion = 3) THEN
                  v_numdocu.extend;
                  v_numdocu(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_camsoli.extend;
                  v_camsoli(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                
                  -- Se valida que la periodo no sea nulo si lo es se pone la periodo activa
                  IF (v_camsoli(i) = '0') THEN
                    v_camsoli(i) := v_camactiva;
                    v_error_carga := 1;
                  ELSE
                    BEGIN
                      SELECT cod_peri
                        INTO v_camsoli(i)
                        FROM bas_ctrl_fact
                       WHERE cod_pais = v_codpais(i)
                         AND cod_peri = v_camsoli(i);
                    EXCEPTION
                      WHEN no_data_found THEN
                        BEGIN
                          SELECT cod_peri
                            INTO v_camsoli(i)
                            FROM seg_perio_corpo
                           WHERE cod_peri = v_camsoli(i);
                        
                          /*INSERTA LA periodo EN CASO NO EXISTA EN EL ARCHIVO DE CONTROL*/
                          INSERT INTO bas_ctrl_fact
                            (cod_pais,
                             cod_peri,
                             fec_proc,
                             val_mnt_min_fact,
                             val_mnt_min_acept,
                             val_mnt_max,
                             val_unid_max,
                             sta_camp,
                             usu_digi,
                             fec_digi,
                             cod_marc,
                             des_marc,
                             cod_cana,
                             des_cana,
                             val_mnt_min_deud,
                             ind_camp_act,
                             num_lote)
                            SELECT cod_pais,
                                   v_camsoli(i),
                                   fec_proc,
                                   val_mnt_min_fact,
                                   val_mnt_min_acept,
                                   val_mnt_max,
                                   val_unid_max,
                                   '1',
                                   USER,
                                   SYSDATE,
                                   cod_marc,
                                   des_marc,
                                   cod_cana,
                                   des_cana,
                                   val_mnt_min_deud,
                                   '0',
                                   num_lote
                              FROM bas_ctrl_fact c
                             WHERE c.cod_pais = pscodigopais
                               AND c.ind_camp_act = '1'
                               AND c.sta_camp = '0';
                        
                        EXCEPTION
                          WHEN no_data_found THEN
                            v_camsoli(i) := v_camactiva;
                            v_error_carga := 1;
                        END;
                    END;
                  
                  END IF;
                
                ELSIF (posicion = 5) THEN
                  v_codclie.extend;
                  v_codclie(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      '0');
                ELSIF (posicion = 6) THEN
                  v_numclie.extend;
                  v_numclie(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      0);
                ELSIF (posicion = 7) THEN
                  v_tipsoli.extend;
                  v_tipsoli(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 8) THEN
                  v_codsuba.extend;
                  v_codsuba(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 9) THEN
                  v_codacce.extend;
                  v_codacce(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 10) THEN
                  v_tipdesp.extend;
                  v_tipdesp(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 11) THEN
                  v_fecsoli.extend;
                  v_fecsoli(i) := to_date(substr(lslinea, inicio, longitud),
                                          'yyyyMMdd');
                ELSIF (posicion = 12) THEN
                  v_regarri.extend;
                  v_regarri(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 13) THEN
                  v_zonarri.extend;
                  v_zonarri(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 14) THEN
                  v_staproc.extend;
                  v_staproc(i) := TRIM(substr(lslinea, inicio, longitud));
                  -- Se existe error en el cod pais, compa?ia o periodo y ademas el status del proceso
                  -- es Aprobado (01) se cambia el status a Rechazado (02) y el motivo a Error de Carga (20)
                  IF (v_error_carga = 1 AND v_staproc(i) = '01') THEN
                    v_staproc(i) := '02';
                    v_error_status := 1;
                  END IF;
                
                ELSIF (posicion = 15) THEN
                  v_motrech.extend;
                  v_motrech(i) := TRIM(substr(lslinea, inicio, longitud));
                
                  IF (v_error_status = 1) THEN
                    v_motrech(i) := '20';
                  END IF;
                
                ELSIF (posicion = 16) THEN
                  v_monflex.extend;
                  v_monflex(i) := nvl(TRIM(substr(lslinea, inicio, longitud)),
                                      0);
                
                ELSIF (posicion = 17) THEN
                  v_indutif.extend;
                  v_indutif(i) := TRIM(substr(lslinea, inicio, longitud));
                
                ELSIF (posicion = 18) THEN
                  v_indacef.extend;
                  v_indacef(i) := TRIM(substr(lslinea, inicio, longitud));
                
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_codpais.count
      INSERT INTO int_solic_cabec
        (cod_pais,
         cam_soli,
         cod_clie,
         oid_cab,
         num_lote,
         num_clie,
         tip_soli,
         cod_comp,
         num_docu,
         cod_regi_arri,
         cod_zona_arri,
         cod_suba,
         cod_acce,
         tip_desp,
         fec_soli,
         sta_proc,
         cod_moti_rech,
         cod_inte,
         num_lote_inte,
         num_lote_sto,
         ind_proc,
         val_mnto_flex,
         ind_util_flex,
         ind_acep_flex)
      VALUES
        (v_codpais(i),
         v_camsoli(i),
         v_codclie(i),
         seq_solic_cab.nextval,
         (SELECT bas.num_lote
            FROM bas_ctrl_fact bas
           WHERE bas.cod_pais = v_codpais(i)
             AND bas.cod_peri = v_camsoli(i)),
         v_numclie(i),
         v_tipsoli(i),
         v_codcia(i),
         v_numdocu(i),
         v_regarri(i),
         v_zonarri(i),
         v_codsuba(i),
         v_codacce(i),
         v_tipdesp(i),
         v_fecsoli(i),
         decode(v_staproc(i), '01', 'A', 'R'),
         v_motrech(i),
         pscodigointerfaz,
         psnumerolote,
         psnumerolotesto,
         psindicadororigen,
         v_monflex(i),
         v_indutif(i),
         v_indacef(i));
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_RECEP_CONSO_CABEC_FLEXI: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END ocr_pr_recep_conso_cabec_flexi;

  /***************************************************************************
      Descripcion       : Genera el Numero de documento correlativo
                          para la cabecera y el detalle de post venta
                          Flexipago
      Fecha Creacion    : 11/06/2012
      Autor             : Jorge Luis Velasquez
  ***************************************************************************/
  PROCEDURE ocr_pr_numdocu_post_venta(psnumlote VARCHAR2) IS
    CURSOR c_reclamo IS
      SELECT cod_clie,
             num_docu_cruc,
             num_docu
        FROM int_ocr_cabec_servi_postv
       WHERE num_lote = psnumlote
       ORDER BY cod_clie,
                num_docu_cruc;
  
    TYPE reclamorec IS RECORD(
      codigocliente int_ocr_cabec_servi_postv.cod_clie%TYPE,
      codigopedido  int_ocr_cabec_servi_postv.num_docu_cruc%TYPE,
      numdocumento  int_ocr_cabec_servi_postv.num_docu%TYPE);
  
    TYPE reclamorectab IS TABLE OF reclamorec;
    reclamorecord reclamorectab;
  
    codigoclienteant int_ocr_cabec_servi_postv.cod_clie%TYPE := '';
    codigopedidoant  int_ocr_cabec_servi_postv.num_docu_cruc%TYPE := '';
    i                BINARY_INTEGER := 1;
  
  BEGIN
  
    OPEN c_reclamo;
    LOOP
      FETCH c_reclamo BULK COLLECT
        INTO reclamorecord LIMIT w_filas;
      /*  */
    
      IF reclamorecord.count > 0 THEN
        FOR x IN reclamorecord.first .. reclamorecord.last
        LOOP
        
          IF (reclamorecord(x).codigocliente != codigoclienteant AND reclamorecord(x)
             .codigopedido != codigopedidoant) THEN
            i := i + 1;
          END IF;
        
          UPDATE int_ocr_cabec_servi_postv
             SET num_docu = to_char(SYSDATE, 'DDMM') ||
                            lpad(num_docu, 4, '0')
           WHERE num_lote = psnumlote
             AND num_docu = reclamorecord(x).numdocumento;
        
          UPDATE int_ocr_detal_servi_postv
             SET num_docu = to_char(SYSDATE, 'DDMM') ||
                            lpad(num_docu, 4, '0')
           WHERE num_lote = psnumlote
             AND num_docu = reclamorecord(x).numdocumento;
        
          codigoclienteant := reclamorecord(x).codigocliente;
          codigopedidoant  := reclamorecord(x).codigopedido;
        
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_reclamo%NOTFOUND;
    
    END LOOP;
    CLOSE c_reclamo;
  
  END ocr_pr_numdocu_post_venta;

  /***************************************************************************
    Descripcion       : Genera el envio de correo
    Fecha Creacion    : 16/07/2012
    Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE ocr_pr_envio_mail
  (
    pscodigopais     VARCHAR2,
    pscodigointerfaz VARCHAR2,
    pscompletado     VARCHAR2,
    mensaje          VARCHAR2,
    psnumerolote     VARCHAR2,
    codigousuario    VARCHAR2,
    psnomreporte     VARCHAR2
  ) IS
  
    regreporte bas_repor_param%ROWTYPE;
  
    l_mail_conn utl_smtp.connection;
  
    lssubject       VARCHAR2(500);
    lslinea         VARCHAR2(4000);
    lnidproceso     NUMBER;
    lscodigosistema VARCHAR2(3);
    lnocurrencias   NUMBER;
  
    CURSOR cursorhistlotes IS
      SELECT a.id_proc_batc id,
             a.num_lote lote,
             b.cod_inte interface,
             b.cod_homo documento,
             nvl((SELECT num_regi
                   FROM int_archi_cntrl c
                  WHERE c.cod_tipo_docu = b.cod_homo
                    AND c.id_proc_batc = a.id_proc_batc
                    AND c.num_lote_arch =
                        substr(a.des_nomb_arch,
                               length(a.des_nomb_arch) -
                               length(c.num_lote_arch) - 3,
                               length(c.num_lote_arch))),
                 a.reg_proc) nro_reg,
             to_char(a.fec_ipro, 'dd/mm/yyyy hh24:mi:ss') fecha,
             a.usu_proc usuario,
             a.des_nomb_arch archivo,
             a.des_erro error
        FROM bas_histo_lotes a,
             bas_inter       b
       WHERE a.inte_cod_inte = b.cod_inte
         AND b.cod_homo IS NOT NULL
         AND a.id_proc_batc = lnidproceso
       ORDER BY a.num_lote,
                a.inpa_cod_inte;
  
    CURSOR cursornumlotes IS
      SELECT DISTINCT substr(des_nomb_arch,
                             instr(des_nomb_arch, '.') - 4,
                             4) || '-' lote
        FROM bas_histo_lotes
       WHERE sist_cod_sist = 'OCR'
         AND inte_cod_inte = 'OCR-35'
         AND id_proc_batc = lnidproceso;
  
  BEGIN
  
    lscodigosistema := substr(pscodigointerfaz, 1, 3);
  
    SELECT *
      INTO regreporte
      FROM bas_repor_param
     WHERE pais_cod_pais = pscodigopais
       AND nom_repo = psnomreporte;
  
    BEGIN
      SELECT id_proc_batc
        INTO lnidproceso
        FROM bas_histo_lotes
       WHERE pais_cod_pais = pscodigopais
         AND sist_cod_sist = lscodigosistema
         AND inte_cod_inte = pscodigointerfaz
         AND num_lote = psnumerolote;
    EXCEPTION
      WHEN OTHERS THEN
        lnidproceso := -1;
    END;
  
    IF (psnomreporte = 'procesoOCRInterfazCompuesta') THEN
      --Verificamos si hay interface de lote de cierre
      SELECT COUNT(1)
        INTO lnocurrencias
        FROM bas_histo_lotes
       WHERE inte_cod_inte = 'OCR-35'
         AND id_proc_batc = lnidproceso
         AND des_info_ctrl LIKE 'tipoLote=C%';
    
      IF (lnocurrencias = 1) THEN
        --Verificamos si hay interface de lote normal, si no hay ya no se envia este correo
        SELECT COUNT(1)
          INTO lnocurrencias
          FROM bas_histo_lotes
         WHERE inte_cod_inte = 'OCR-35'
           AND id_proc_batc = lnidproceso
           AND des_info_ctrl LIKE 'tipoLote=L%';
      
        IF (lnocurrencias = 0) THEN
          RETURN;
        END IF;
      END IF;
    
    END IF;
  
    IF (psnomreporte = 'procesoOCRInterfazCompuestaError') THEN
    
      IF (pscompletado = '1') THEN
        --Verificamos si la interfaz Compuesta
        SELECT COUNT(1)
          INTO lnocurrencias
          FROM bas_histo_lotes a,
               bas_inter       b
         WHERE a.inte_cod_inte = b.cod_inte
           AND b.cod_homo IS NOT NULL
           AND a.id_proc_batc = lnidproceso
           AND a.ind_loer = 'S';
      
        IF (lnocurrencias = 0) THEN
          RETURN;
        END IF;
      END IF;
    END IF;
  
    IF (regreporte.ind_emai = 'S') THEN
    
      lssubject := regreporte.val_subj;
    
      --Armamos el asunto
      lssubject := lssubject || ', lotes: ';
    
      FOR clote IN cursornumlotes
      LOOP
        lssubject := lssubject || clote.lote;
      END LOOP;
    
      lssubject := substr(lssubject, 1, length(lssubject) - 1);
      -- --
    
      l_mail_conn := log_email.begin_mail(sender     => regreporte.ema_orig,
                                          recipients => regreporte.ema_copi,
                                          subject    => lssubject,
                                          mime_type  => 'text/html');
    
      lslinea := lslinea ||
                 '<html><head><meta content="text/html charset=ISO-8859-1" http-equiv="content-type"><title></title></head><body>';
      lslinea := lslinea ||
                 '<table border="0" cellpadding="0" cellspacing="0">';
      lslinea := lslinea ||
                 '<tbody><tr><td><font face="Arial" size="2">Se ha realizado la Recepcion de OCR para los siguientes documentos:</font></td></tr><tr><td></td></tr>';
      lslinea := lslinea ||
                 '<tr><td width="95%"><table border="1" cellpadding="0" cellspacing="0"><tr>';
      lslinea := lslinea ||
                 '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>ID</b></font></td>';
      lslinea := lslinea ||
                 '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>LOTE</b></font></td>';
      lslinea := lslinea ||
                 '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>INTERFACE</b></font></td>';
      lslinea := lslinea ||
                 '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>DOCUMENTO</b></font></td>';
      lslinea := lslinea ||
                 '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>NRO REG</b></font></td>';
      lslinea := lslinea ||
                 '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>FECHA</b></font></td>';
      lslinea := lslinea ||
                 '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>USUARIO</b></font></td>';
      lslinea := lslinea ||
                 '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>ARCHIVO</b></font></td>';
      lslinea := lslinea ||
                 '<td background="#7030A0" align="center" bgcolor="#7030A0"><font face="Arial" size="2" color="#ffffff"><b>ERROR</b></font></td></tr>';
    
      log_email.write_text(l_mail_conn, lslinea);
    
      FOR clote IN cursorhistlotes
      LOOP
        lslinea := '<tr>';
        lslinea := lslinea ||
                   '<td align="center"><font face="Arial" size="2">' ||
                   clote.id || '</font></td>';
        lslinea := lslinea ||
                   '<td align="center"><font face="Arial" size="2">' ||
                   clote.lote || '</font></td>';
        lslinea := lslinea ||
                   '<td align="center"><font face="Arial" size="2">' ||
                   clote.interface || '</font></td>';
        lslinea := lslinea ||
                   '<td align="center"><font face="Arial" size="2">' ||
                   clote.documento || '</font></td>';
        lslinea := lslinea ||
                   '<td align="center"><font face="Arial" size="2">' ||
                   clote.nro_reg || '</font></td>';
        lslinea := lslinea ||
                   '<td align="center"><font face="Arial" size="2">' ||
                   clote.fecha || '</font></td>';
        lslinea := lslinea ||
                   '<td align="center"><font face="Arial" size="2">' ||
                   clote.usuario || '</font></td>';
        lslinea := lslinea ||
                   '<td align="center"><font face="Arial" size="2">' ||
                   clote.archivo || '</font></td>';
        lslinea := lslinea ||
                   '<td align="center"><font face="Arial" size="2">' ||
                   clote.error || '</font></td>';
        lslinea := lslinea ||
                  
                   '</tr>';
      
        log_email.write_text(l_mail_conn, lslinea);
      END LOOP;
    
      lslinea := '</table></td></tr><tr><td></td></tr>';
    
      lslinea := lslinea ||
                 '<tr><td><font face="Arial" size="2"> Mensaje :' ||
                 mensaje || '</font></td></tr>';
    
      lslinea := lslinea ||
                 '<tr><td><br/><br/><br/><br/><font face="Arial" size="2"><strong>NOTA: Por favor no responda a este mensaje, es generado automaticamente desde una cuenta no monitoreada. </strong></font><br/><br/><br/><br/></td></tr>';
      lslinea := lslinea || '</tbody></table></body></html>';
    
      log_email.write_text(l_mail_conn, lslinea);
      log_email.end_mail(conn => l_mail_conn);
    
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_ENVIO_MAIL: **' || ls_sqlerrm);
  END ocr_pr_envio_mail;

  /***************************************************************************
   Archivo           : 1. MAE_PRODU.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_maest_produ_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz(vscodigopais VARCHAR2) IS
      SELECT oid_prod,
             pais_oid_pais,
             cod_sap,
             codi_anti,
             des_cort
        FROM mae_produ
       WHERE pais_oid_pais =
             (SELECT oid_pais FROM seg_pais a WHERE cod_pais = vscodigopais);
  
    TYPE interfazrec IS RECORD(
      oidprod     mae_produ.oid_prod%TYPE,
      paisoidpais mae_produ.pais_oid_pais%TYPE,
      codsap      mae_produ.cod_sap%TYPE,
      codianti    mae_produ.codi_anti%TYPE,
      descort     mae_produ.des_cort%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz(pscodigopais);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .oidprod || ';' || interfazrecord(x).paisoidpais || ';' || interfazrecord(x)
                     .codsap || ';' || interfazrecord(x).codianti || ';' || interfazrecord(x)
                     .descort;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_maest_produ_prol: ' ||
                              ls_sqlerrm);
  END int_pr_ocr_maest_produ_prol;

  /***************************************************************************
   Archivo           : 2. GEN_I18N_SICC_PAIS.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_gen_sicc_pais_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT oid_i18n,
             attr_enti,
             attr_num_atri,
             idio_oid_idio,
             val_i18n,
             val_oid
        FROM gen_i18n_sicc_pais
       WHERE attr_enti IN ('MAE_PRODU', 'PRE_ESTRA');
  
    TYPE interfazrec IS RECORD(
      oidi18n     gen_i18n_sicc_pais.oid_i18n%TYPE,
      attrenti    gen_i18n_sicc_pais.attr_enti%TYPE,
      attrnumatri gen_i18n_sicc_pais.attr_num_atri%TYPE,
      idiooididio gen_i18n_sicc_pais.idio_oid_idio%TYPE,
      vali18n     gen_i18n_sicc_pais.val_i18n%TYPE,
      valoid      gen_i18n_sicc_pais.val_oid%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .oidi18n || ';' || interfazrecord(x).attrenti || ';' || interfazrecord(x)
                     .attrnumatri || ';' || interfazrecord(x).idiooididio || ';' || interfazrecord(x)
                     .vali18n || ';' || interfazrecord(x).valoid;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      END IF;
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_gen_sicc_pais_prol: ' ||
                              ls_sqlerrm);
  END int_pr_ocr_gen_sicc_pais_prol;

  /***************************************************************************
    Archivo           : 3. PRE_OFERT_DETAL.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_pre_ofer_deta_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT oid_deta_ofer,
             ofer_oid_ofer,
             prod_oid_prod,
             num_line_ofer,
             num_unid_esti,
             cod_orig,
             val_fact_repe,
             ind_prod_prin,
             imp_prec_cata,
             imp_prec_posi,
             imp_cost_esta,
             imp_vent_neta_esti,
             num_pagi_cata,
             ocat_oid_catal,
             tofe_oid_tipo_ofer,
             civi_oid_ciclo_vida,
             cndp_oid_cond_prom,
             ind_digi,
             ind_impr_gp,
             ind_codi_vent_gene,
             ind_matr_fact_gene,
             val_posi_pagi,
             val_codi_vent,
             val_cent,
             precio_unitario,
             num_orde_deta,
             imp_prec_publ,
             gofe_oid_grup_ofer
        FROM pre_ofert_detal a
       WHERE a.ofer_oid_ofer IN
             (SELECT oid_ofer
                FROM pre_ofert
               WHERE mfca_oid_cabe IN
                     (SELECT oid_cabe
                        FROM pre_matri_factu_cabec a
                       WHERE a.perd_oid_peri >=
                             fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_codig_perio_actua)
                         AND a.perd_oid_peri <=
                             fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_nsgte_campa(fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                                                         1))));
  
    TYPE interfazrec IS RECORD(
      oiddetaofer      pre_ofert_detal.oid_deta_ofer%TYPE,
      oferoidofer      pre_ofert_detal.ofer_oid_ofer%TYPE,
      prodoidprod      pre_ofert_detal.prod_oid_prod%TYPE,
      numlineofer      pre_ofert_detal.num_line_ofer%TYPE,
      numunidesti      pre_ofert_detal.num_unid_esti%TYPE,
      codorig          pre_ofert_detal.cod_orig%TYPE,
      valfactrepe      pre_ofert_detal.val_fact_repe%TYPE,
      indprodprin      pre_ofert_detal.ind_prod_prin%TYPE,
      imppreccata      pre_ofert_detal.imp_prec_cata%TYPE,
      impprecposi      pre_ofert_detal.imp_prec_posi%TYPE,
      impcostesta      pre_ofert_detal.imp_cost_esta%TYPE,
      impventnetaesti  pre_ofert_detal.imp_vent_neta_esti%TYPE,
      numpagicata      pre_ofert_detal.num_pagi_cata%TYPE,
      ocatoidcatal     pre_ofert_detal.ocat_oid_catal%TYPE,
      tofeoidtipoofer  pre_ofert_detal.tofe_oid_tipo_ofer%TYPE,
      civioidciclovida pre_ofert_detal.civi_oid_ciclo_vida%TYPE,
      cndpoidcondprom  pre_ofert_detal.cndp_oid_cond_prom%TYPE,
      inddigi          pre_ofert_detal.ind_digi%TYPE,
      indimprgp        pre_ofert_detal.ind_impr_gp%TYPE,
      indcodiventgene  pre_ofert_detal.ind_codi_vent_gene%TYPE,
      indmatrfactgene  pre_ofert_detal.ind_matr_fact_gene%TYPE,
      valposipagi      pre_ofert_detal.val_posi_pagi%TYPE,
      valcodivent      pre_ofert_detal.val_codi_vent%TYPE,
      valcent          pre_ofert_detal.val_cent%TYPE,
      preciounitario   pre_ofert_detal.precio_unitario%TYPE,
      numordedeta      pre_ofert_detal.num_orde_deta%TYPE,
      impprecpubl      pre_ofert_detal.imp_prec_publ%TYPE,
      gofeoidgrupofer  pre_ofert_detal.gofe_oid_grup_ofer%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .oiddetaofer || ';' || interfazrecord(x).oferoidofer || ';' || interfazrecord(x)
                     .prodoidprod || ';' || interfazrecord(x).numlineofer || ';' || interfazrecord(x)
                     .numunidesti || ';' || interfazrecord(x).codorig || ';' || interfazrecord(x)
                     .valfactrepe || ';' || interfazrecord(x).indprodprin || ';' || interfazrecord(x)
                     .imppreccata || ';' || interfazrecord(x).impprecposi || ';' || interfazrecord(x)
                     .impcostesta || ';' || interfazrecord(x)
                     .impventnetaesti || ';' || interfazrecord(x)
                     .numpagicata || ';' || interfazrecord(x).ocatoidcatal || ';' || interfazrecord(x)
                     .tofeoidtipoofer || ';' || interfazrecord(x)
                     .civioidciclovida || ';' || interfazrecord(x)
                     .cndpoidcondprom || ';' || interfazrecord(x).inddigi || ';' || interfazrecord(x)
                     .indimprgp || ';' || interfazrecord(x).indcodiventgene || ';' || interfazrecord(x)
                     .indmatrfactgene || ';' || interfazrecord(x)
                     .valposipagi || ';' || interfazrecord(x).valcodivent || ';' || interfazrecord(x)
                     .valcent || ';' || interfazrecord(x).preciounitario || ';' || interfazrecord(x)
                     .numordedeta || ';' || interfazrecord(x).impprecpubl || ';' || interfazrecord(x)
                     .gofeoidgrupofer;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_pre_ofer_deta_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_pre_ofer_deta_prol;

  /***************************************************************************
   Archivo           : 4. PRE_OFERT.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_pre_ofer_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT oid_ofer,
             coes_oid_estr,
             num_ofer,
             argv_oid_argu_vent,
             mfca_oid_cabe,
             ind_codi_vent_gene,
             ind_desp_compl,
             ind_desp_auto,
             ind_matr_fact_gene,
             ind_recu_obli,
             ind_regi_esta_gene,
             num_orde,
             ocat_oid_cata,
             num_grup,
             num_grup_cndt,
             num_grup_cond,
             val_cond_g1_cndt,
             val_cond_g2_cndo,
             num_paqu,
             num_prim_posi_rank,
             num_ulti_posi_rank,
             fopa_oid_form_pago,
             sbac_oid_sbac,
             acce_oid_acce
        FROM pre_ofert a
       WHERE a.mfca_oid_cabe IN
             (SELECT oid_cabe
                FROM pre_matri_factu_cabec a
               WHERE a.perd_oid_peri >=
                     fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_codig_perio_actua)
                 AND a.perd_oid_peri <=
                     fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_nsgte_campa(fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                                                 1)));
  
    TYPE interfazrec IS RECORD(
      oidofer         pre_ofert.oid_ofer%TYPE,
      coesoidestr     pre_ofert.coes_oid_estr%TYPE,
      numofer         pre_ofert.num_ofer%TYPE,
      argvoidarguvent pre_ofert.argv_oid_argu_vent%TYPE,
      mfcaoidcabe     pre_ofert.mfca_oid_cabe%TYPE,
      indcodiventgene pre_ofert.ind_codi_vent_gene%TYPE,
      inddespcompl    pre_ofert.ind_desp_compl%TYPE,
      inddespauto     pre_ofert.ind_desp_auto%TYPE,
      indmatrfactgene pre_ofert.ind_matr_fact_gene%TYPE,
      indrecuobli     pre_ofert.ind_recu_obli%TYPE,
      indregiestagene pre_ofert.ind_regi_esta_gene%TYPE,
      numorde         pre_ofert.num_orde%TYPE,
      ocatoidcata     pre_ofert.ocat_oid_cata%TYPE,
      numgrup         pre_ofert.num_grup%TYPE,
      numgrupcndt     pre_ofert.num_grup_cndt%TYPE,
      numgrupcond     pre_ofert.num_grup_cond%TYPE,
      valcondg1cndt   pre_ofert.val_cond_g1_cndt%TYPE,
      valcondg2cndo   pre_ofert.val_cond_g2_cndo%TYPE,
      numpaqu         pre_ofert.num_paqu%TYPE,
      numprimposirank pre_ofert.num_prim_posi_rank%TYPE,
      numultiposirank pre_ofert.num_ulti_posi_rank%TYPE,
      fopaoidformpago pre_ofert.fopa_oid_form_pago%TYPE,
      sbacoidsbac     pre_ofert.sbac_oid_sbac%TYPE,
      acceoidacce     pre_ofert.acce_oid_acce%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .oidofer || ';' || interfazrecord(x).coesoidestr || ';' || interfazrecord(x)
                     .numofer || ';' || interfazrecord(x).argvoidarguvent || ';' || interfazrecord(x)
                     .mfcaoidcabe || ';' || interfazrecord(x)
                     .indcodiventgene || ';' || interfazrecord(x)
                     .inddespcompl || ';' || interfazrecord(x).inddespauto || ';' || interfazrecord(x)
                     .indmatrfactgene || ';' || interfazrecord(x)
                     .indrecuobli || ';' || interfazrecord(x)
                     .indregiestagene || ';' || interfazrecord(x).numorde || ';' || interfazrecord(x)
                     .ocatoidcata || ';' || interfazrecord(x).numgrup || ';' || interfazrecord(x)
                     .numgrupcndt || ';' || interfazrecord(x).numgrupcond || ';' || interfazrecord(x)
                     .valcondg1cndt || ';' || interfazrecord(x)
                     .valcondg2cndo || ';' || interfazrecord(x).numpaqu || ';' || interfazrecord(x)
                     .numprimposirank || ';' || interfazrecord(x)
                     .numultiposirank || ';' || interfazrecord(x)
                     .fopaoidformpago || ';' || interfazrecord(x)
                     .sbacoidsbac || ';' || interfazrecord(x).acceoidacce;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_pre_ofer_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_pre_ofer_prol;

  /***************************************************************************
   Archivo           : 4. PRE_CATAL.TXT
   Fecha Creacion    : 26/04/2013
  Autor             : Sebastian Guerra
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_prec_cata_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
    
      SELECT oid_cata,
             cod_cata,
             des_cata,
             pais_oid_pais,
             ind_revi
        FROM pre_catal;
  
    TYPE interfazrec IS RECORD(
      oid_cata      pre_catal.oid_cata%TYPE,
      cod_cata      pre_catal.cod_cata%TYPE,
      des_cata      pre_catal.des_cata%TYPE,
      pais_oid_pais pre_catal.pais_oid_pais%TYPE,
      ind_revi      pre_catal.ind_revi%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .oid_cata || ';' || interfazrecord(x).cod_cata || ';' || interfazrecord(x)
                     .des_cata || ';' || interfazrecord(x).pais_oid_pais || ';' || interfazrecord(x)
                     .ind_revi;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_envi_prec_cata_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_envi_prec_cata_prol;

  /***************************************************************************
   Archivo           : 5. PRE_MATRI_FACTU.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_matri_factu_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT oid_matr_fact,
             cod_esta,
             mfca_oid_cabe,
             ofde_oid_deta_ofer,
             ind_matr_fact
        FROM pre_matri_factu a
       WHERE a.mfca_oid_cabe IN
             (SELECT oid_cabe
                FROM pre_matri_factu_cabec a
               WHERE a.perd_oid_peri >=
                     fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_codig_perio_actua)
                 AND a.perd_oid_peri <=
                     fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_nsgte_campa(fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                                                 1)));
  
    TYPE interfazrec IS RECORD(
      oid_matrfact    pre_matri_factu.oid_matr_fact%TYPE,
      codesta         pre_matri_factu.cod_esta%TYPE,
      mfcaoidcabe     pre_matri_factu.mfca_oid_cabe%TYPE,
      ofdeoiddetaofer pre_matri_factu.ofde_oid_deta_ofer%TYPE,
      indmatrfact     pre_matri_factu.ind_matr_fact%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .oid_matrfact || ';' || interfazrecord(x).codesta || ';' || interfazrecord(x)
                     .mfcaoidcabe || ';' || interfazrecord(x)
                     .ofdeoiddetaofer || ';' || interfazrecord(x)
                     .indmatrfact;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_pre_matri_factu_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_matri_factu_prol;

  /***************************************************************************
   Archivo           : 6. PRE_RANGO_PROMO.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_rango_promo_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT oid_rang_prom,
             ocat_oid_cata,
             pomo_oid_prom,
             cod_tipo_rang,
             num_rang_inte,
             val_desd,
             val_hast,
             ind_excl
        FROM pre_rango_promo a
       WHERE a.pomo_oid_prom IN
             (SELECT p.oid_prom
                FROM pre_promo p
               WHERE p.ofer_oid_ofer IN
                     (SELECT oid_ofer
                        FROM pre_ofert
                       WHERE mfca_oid_cabe IN
                             (SELECT oid_cabe
                                FROM pre_matri_factu_cabec a
                               WHERE a.perd_oid_peri >=
                                     fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_codig_perio_actua)
                                 AND a.perd_oid_peri <=
                                     fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_nsgte_campa(fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                                                                 1)))));
  
    TYPE interfazrec IS RECORD(
      oidrangprom pre_rango_promo.oid_rang_prom%TYPE,
      ocatoidcata pre_rango_promo.ocat_oid_cata%TYPE,
      pomooidprom pre_rango_promo.pomo_oid_prom%TYPE,
      codtiporang pre_rango_promo.cod_tipo_rang%TYPE,
      numranginte pre_rango_promo.num_rang_inte%TYPE,
      valdesd     pre_rango_promo.val_desd%TYPE,
      valhast     pre_rango_promo.val_hast%TYPE,
      indexcl     pre_rango_promo.ind_excl%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
        
          lslinea := interfazrecord(x)
                     .oidrangprom || ';' || interfazrecord(x).ocatoidcata || ';' || interfazrecord(x)
                     .pomooidprom || ';' || interfazrecord(x).codtiporang || ';' || interfazrecord(x)
                     .numranginte || ';' || interfazrecord(x).valdesd || ';' || interfazrecord(x)
                     .valhast || ';' || interfazrecord(x).indexcl;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_rango_promo_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_rango_promo_prol;

  /***************************************************************************
   Archivo           : 7. PRE_GRUPO_OFERT.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_grupo_ofert_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT oid_grup_ofer,
             ofer_oid_ofer,
             num_grup,
             cod_fact_cuad,
             cues_oid_ind_cuad_tipo_estr,
             ind_cndt,
             ind_cndo,
             ind_grup
        FROM pre_grupo_ofert a
       WHERE a.ofer_oid_ofer IN
             (SELECT oid_ofer
                FROM pre_ofert
               WHERE mfca_oid_cabe IN
                     (SELECT oid_cabe
                        FROM pre_matri_factu_cabec a
                       WHERE a.perd_oid_peri >=
                             fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_codig_perio_actua)
                         AND a.perd_oid_peri <=
                             fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_nsgte_campa(fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                                                         1))));
  
    TYPE interfazrec IS RECORD(
      oidgrupofer            pre_grupo_ofert.oid_grup_ofer%TYPE,
      oferoidofer            pre_grupo_ofert.ofer_oid_ofer%TYPE,
      numgrup                pre_grupo_ofert.num_grup%TYPE,
      codfactcuad            pre_grupo_ofert.cod_fact_cuad%TYPE,
      cuesoidindcuadtipoestr pre_grupo_ofert.cues_oid_ind_cuad_tipo_estr%TYPE,
      indcndt                pre_grupo_ofert.ind_cndt%TYPE,
      indcndo                pre_grupo_ofert.ind_cndo%TYPE,
      indgrup                pre_grupo_ofert.ind_grup%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .oidgrupofer || ';' || interfazrecord(x).oferoidofer || ';' || interfazrecord(x)
                     .numgrup || ';' || interfazrecord(x).codfactcuad || ';' || interfazrecord(x)
                     .cuesoidindcuadtipoestr || ';' || interfazrecord(x)
                     .indcndt || ';' || interfazrecord(x).indcndo || ';' || interfazrecord(x)
                     .indgrup;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_grupo_ofert_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_grupo_ofert_prol;

  /***************************************************************************
   Archivo           : 8. PRE_MATRI_REEMP.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_matri_reemp_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT oid_matr_reem,
             mafa_oid_cod_ppal,
             mafa_oid_cod_reem,
             ind_mens,
             ind_reem_ante_cuad,
             --zorg_oid_regi,
             (SELECT cod_regi FROM zon_regio WHERE oid_regi = zorg_oid_regi),
             --zzon_oid_zona,
             (SELECT cod_zona FROM zon_zona WHERE oid_zona = zzon_oid_zona),
             ticl_oid_tipo_clie,
             ind_acti,
             usu_crea,
             '1' tipo,
             0 num_orde
        FROM pre_matri_reemp a
       WHERE a.mafa_oid_cod_ppal IN
             (SELECT m.oid_matr_fact
                FROM pre_matri_factu m
               WHERE m.mfca_oid_cabe IN
                     (SELECT oid_cabe
                        FROM pre_matri_factu_cabec a
                       WHERE a.perd_oid_peri >=
                             fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_codig_perio_actua)
                         AND a.perd_oid_peri <=
                             fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_nsgte_campa(fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                                                         1))))
      UNION
      SELECT pmca.oid_matr_ater,
             pmca.mafa_oid_cod_ppal,
             pmca.mafa_oid_cod_alte,
             0,
             0,
             NULL,
             NULL,
             NULL,
             '1',
             'ADMIN',
             '2' tipo,
             pmca.num_orde
        FROM pre_matri_factu       pmf,
             pre_matri_codig_alter pmca,
             pre_matri_factu       pmf2,
             pre_ofert_detal       pod,
             pre_ofert_detal       pod1,
             --bel_stock             bs,
             gen_i18n_sicc_pais    gen,
             pre_catal             cat,
             pre_ofert             pof,
             cra_perio             per,
             seg_perio_corpo       spc,
             pre_matri_factu_cabec pmfc
       WHERE pmf.mfca_oid_cabe = pmfc.oid_cabe
         AND pmfc.perd_oid_peri = per.oid_peri
         AND per.peri_oid_peri = spc.oid_peri
         AND spc.cod_peri IN
             (fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
              fin_pkg_gener.fin_fn_obtie_nsgte_campa(fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                     1))
         AND pmf.oid_matr_fact = pmca.mafa_oid_cod_ppal
         AND pmca.mafa_oid_cod_alte = pmf2.oid_matr_fact
            --AND pmca.num_orde = 1
         AND pmf2.ofde_oid_deta_ofer = pod.oid_deta_ofer
         AND pmf.ofde_oid_deta_ofer = pod1.oid_deta_ofer
         AND pod.ofer_oid_ofer = pof.oid_ofer
            --AND pod.prod_oid_prod = bs.prod_oid_prod
            --AND bs.esme_oid_esta_merc = 2001
            --AND bs.val_sald > 0
            --AND bs.almc_oid_alma = 2001
         AND pod.ocat_oid_catal = cat.oid_cata
         AND pod.prod_oid_prod = gen.val_oid
         AND gen.attr_enti = 'MAE_PRODU';
  
    TYPE interfazrec IS RECORD(
      oidmatrreem     pre_matri_reemp.oid_matr_reem%TYPE,
      mafaoidcodppal  pre_matri_reemp.mafa_oid_cod_ppal%TYPE,
      mafaoidcodreem  pre_matri_reemp.mafa_oid_cod_reem%TYPE,
      indmens         pre_matri_reemp.ind_mens%TYPE,
      indreemantecuad pre_matri_reemp.ind_reem_ante_cuad%TYPE,
      zorgoidregi     pre_matri_reemp.zorg_oid_regi%TYPE,
      zzonoidzona     pre_matri_reemp.zzon_oid_zona%TYPE,
      ticloidtipoclie pre_matri_reemp.ticl_oid_tipo_clie%TYPE,
      indacti         pre_matri_reemp.ind_acti%TYPE,
      usucrea         pre_matri_reemp.usu_crea%TYPE,
      tipo            pre_matri_reemp.usu_crea%TYPE,
      num_orde        pre_matri_codig_alter.num_orde%TYPE
      
      );
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .oidmatrreem || ';' || interfazrecord(x).mafaoidcodppal || ';' || interfazrecord(x)
                     .mafaoidcodreem || ';' || interfazrecord(x).indmens || ';' || interfazrecord(x)
                     .indreemantecuad || ';' || interfazrecord(x)
                     .zorgoidregi || ';' || interfazrecord(x).zzonoidzona || ';' || interfazrecord(x)
                     .ticloidtipoclie || ';' || interfazrecord(x).indacti || ';' || interfazrecord(x)
                     .usucrea || ';' || interfazrecord(x).tipo || ';' || interfazrecord(x)
                     .num_orde;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_matri_reemp_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_matri_reemp_prol;

  /***************************************************************************
   Archivo           : 9. PRE_PROMO.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_pre_promo_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT oid_prom,
             ofer_oid_ofer,
             num_cond,
             val_fact_cuad,
             icpr_oid_indi_cuad_prom
        FROM pre_promo a
       WHERE a.ofer_oid_ofer IN
             (SELECT oid_ofer
                FROM pre_ofert
               WHERE mfca_oid_cabe IN
                     (SELECT oid_cabe
                        FROM pre_matri_factu_cabec a
                       WHERE a.perd_oid_peri >=
                             fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_codig_perio_actua)
                         AND a.perd_oid_peri <=
                             fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_nsgte_campa(fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                                                         1))));
  
    TYPE interfazrec IS RECORD(
      oidprom             pre_promo.oid_prom%TYPE,
      oferoidofer         pre_promo.ofer_oid_ofer%TYPE,
      numcond             pre_promo.num_cond%TYPE,
      valfactcuad         pre_promo.val_fact_cuad%TYPE,
      icproidindicuadprom pre_promo.icpr_oid_indi_cuad_prom%TYPE
      
      );
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .oidprom || ';' || interfazrecord(x).oferoidofer || ';' || interfazrecord(x)
                     .numcond || ';' || interfazrecord(x).valfactcuad || ';' || interfazrecord(x)
                     .icproidindicuadprom;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_pre_promo_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_pre_promo_prol;

  /***************************************************************************
   Archivo           : 10. CRA_PERIO.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_cra_perio_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT a.oid_peri,
             a.marc_oid_marc,
             a.cana_oid_cana,
             a.pais_oid_pais,
             a.acce_oid_acce,
             a.peri_oid_peri,
             to_char(fec_inic, 'DD/MM/YYYY') fec_inic,
             to_char(fec_fina, 'DD/MM/YYYY') fec_fina,
             a.val_esta,
             spc.cod_peri,
             a.ind_peri_cort,
             a.ind_peri_cruc
        FROM cra_perio       a,
             seg_perio_corpo spc
       WHERE a.peri_oid_peri = spc.oid_peri
         AND a.oid_peri IN
             (fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_codig_perio_actua),
              fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_nsgte_campa(fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                                          1)));
  
    TYPE interfazrec IS RECORD(
      oidperi     cra_perio.oid_peri%TYPE,
      marcoidmarc cra_perio.marc_oid_marc%TYPE,
      canaoidcana cra_perio.cana_oid_cana%TYPE,
      paisoidpais cra_perio.pais_oid_pais%TYPE,
      acceoidacce cra_perio.acce_oid_acce%TYPE,
      perioidperi cra_perio.peri_oid_peri%TYPE,
      fecinic     VARCHAR2(10),
      fecfina     VARCHAR2(10),
      valesta     cra_perio.val_esta%TYPE,
      cod_peri    seg_perio_corpo.cod_peri%TYPE,
      indpericort cra_perio.ind_peri_cort%TYPE,
      indpericruc cra_perio.ind_peri_cruc%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .oidperi || ';' || interfazrecord(x).marcoidmarc || ';' || interfazrecord(x)
                     .canaoidcana || ';' || interfazrecord(x).paisoidpais || ';' || interfazrecord(x)
                     .acceoidacce || ';' || interfazrecord(x).perioidperi || ';' || interfazrecord(x)
                     .fecinic || ';' || interfazrecord(x).fecfina || ';' || interfazrecord(x)
                     .valesta || ';' || interfazrecord(x).cod_peri || ';' || interfazrecord(x)
                     .indpericort || ';' || interfazrecord(x).indpericruc;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_cra_perio_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_cra_perio_prol;

  /***************************************************************************
   Archivo           : 11. PRE_MATRI_FACTU_CABEC.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_factu_cabec_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT oid_cabe,
             perd_oid_peri,
             num_pedi_esti,
             num_unid_esti,
             num_clie_esti,
             tota_mont_vent_neta,
             ind_matr_fact_gene,
             ind_regi_esta_gene,
             ind_matr_fact
        FROM pre_matri_factu_cabec a
       WHERE a.perd_oid_peri IN
             (fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_codig_perio_actua),
              fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_nsgte_campa(fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                                          1)));
  
    TYPE interfazrec IS RECORD(
      oidcabe          pre_matri_factu_cabec.oid_cabe%TYPE,
      perdoidperi      pre_matri_factu_cabec.perd_oid_peri%TYPE,
      numpediesti      pre_matri_factu_cabec.num_pedi_esti%TYPE,
      numunidesti      pre_matri_factu_cabec.num_unid_esti%TYPE,
      numclieesti      pre_matri_factu_cabec.num_clie_esti%TYPE,
      totamontventneta pre_matri_factu_cabec.tota_mont_vent_neta%TYPE,
      indmatrfactgene  pre_matri_factu_cabec.ind_matr_fact_gene%TYPE,
      indregiestagene  pre_matri_factu_cabec.ind_regi_esta_gene%TYPE,
      indmatrfact      pre_matri_factu_cabec.ind_matr_fact%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .oidcabe || ';' || interfazrecord(x).perdoidperi || ';' || interfazrecord(x)
                     .numpediesti || ';' || interfazrecord(x).numunidesti || ';' || interfazrecord(x)
                     .numclieesti || ';' || interfazrecord(x)
                     .totamontventneta || ';' || interfazrecord(x)
                     .indmatrfactgene || ';' || interfazrecord(x)
                     .indregiestagene || ';' || interfazrecord(x)
                     .indmatrfact;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_factu_cabec_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_factu_cabec_prol;

  /***************************************************************************
   Archivo           : 12. PED_GESTI_STOCK.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_gesti_stock_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT a.oid_gest_stoc,
             a.val_limi_ctrl_vent,
             a.clas_oid_clas,
             zon.cod_zona, --  a.zzon_oid_zona
             a.perd_oid_peri,
             a.ind_ctrl_liqu,
             a.ind_ulti_noti,
             a.cod_gest_stoc,
             a.val_porc,
             a.val_unid,
             a.ofde_oid_deta_ofer,
             regio.cod_regi, --a.zorg_oid_regi
             a.sbti_oid_subt_clie,
             a.tccl_oid_tipo_clas,
             a.ticl_oid_tipo_clie,
             a.ind_acti,
             to_char(a.fec_acti, 'yyyymmdd') fec_acti,
             a.usu_crea,
             to_char(a.fec_crea, 'yyyymmdd') fec_crea
        FROM ped_gesti_stock a,
             zon_zona        zon,
             zon_regio       regio
       WHERE a.zzon_oid_zona = zon.oid_zona(+)
         AND a.zorg_oid_regi = regio.oid_regi(+)
         AND a.perd_oid_peri >=
             fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_codig_perio_actua)
         AND a.perd_oid_peri <=
             fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_obtie_nsgte_campa(fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                                         1))
         AND (a.ind_acti IS NULL OR a.ind_acti = '1');
  
    TYPE interfazrec IS RECORD(
      oidgeststoc     ped_gesti_stock.oid_gest_stoc%TYPE,
      vallimictrlvent ped_gesti_stock.val_limi_ctrl_vent%TYPE,
      clasoidclas     ped_gesti_stock.clas_oid_clas%TYPE,
      zzonoidzona     ped_gesti_stock.zzon_oid_zona%TYPE,
      perdoidperi     ped_gesti_stock.perd_oid_peri%TYPE,
      indctrlliqu     ped_gesti_stock.ind_ctrl_liqu%TYPE,
      indultinoti     ped_gesti_stock.ind_ulti_noti%TYPE,
      codgeststoc     ped_gesti_stock.cod_gest_stoc%TYPE,
      valporc         ped_gesti_stock.val_porc%TYPE,
      valunid         ped_gesti_stock.val_unid%TYPE,
      ofdeoiddetaofer ped_gesti_stock.ofde_oid_deta_ofer%TYPE,
      zorgoidregi     ped_gesti_stock.zorg_oid_regi%TYPE,
      sbtioidsubtclie ped_gesti_stock.sbti_oid_subt_clie%TYPE,
      tccloidtipoclas ped_gesti_stock.tccl_oid_tipo_clas%TYPE,
      ticloidtipoclie ped_gesti_stock.ticl_oid_tipo_clie%TYPE,
      indacti         ped_gesti_stock.ind_acti%TYPE,
      fecacti         VARCHAR2(8),
      usucrea         ped_gesti_stock.usu_crea%TYPE,
      feccrea         VARCHAR2(8));
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .oidgeststoc || ';' || interfazrecord(x)
                     .vallimictrlvent || ';' || interfazrecord(x)
                     .clasoidclas || ';' || interfazrecord(x).zzonoidzona || ';' || interfazrecord(x)
                     .perdoidperi || ';' || interfazrecord(x).indctrlliqu || ';' || interfazrecord(x)
                     .indultinoti || ';' || interfazrecord(x).codgeststoc || ';' || interfazrecord(x)
                     .valporc || ';' || interfazrecord(x).valunid || ';' || interfazrecord(x)
                     .ofdeoiddetaofer || ';' || interfazrecord(x)
                     .zorgoidregi || ';' || interfazrecord(x)
                     .sbtioidsubtclie || ';' || interfazrecord(x)
                     .tccloidtipoclas || ';' || interfazrecord(x)
                     .ticloidtipoclie || ';' || interfazrecord(x).indacti || ';' || interfazrecord(x)
                     .fecacti || ';' || interfazrecord(x).usucrea || ';' || interfazrecord(x)
                     .feccrea;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_gesti_stock_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_gesti_stock_prol;

  /***************************************************************************
   Archivo           : 13. LIMITE_VENTA.TXT
   Fecha Creacion    : 05/11/2012
  Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE int_pr_ocr_limite_venta_prol
  
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz(vscodigopais VARCHAR2) IS
      SELECT codigopais,
             codigoperiodo,
             codigoventa,
             codigozona,
             indactividad,
             limiteventa,
             val_unid
        FROM (SELECT d.cod_pais AS codigopais,
                     c.cod_peri AS codigoperiodo,
                     e.val_codi_vent AS codigoventa,
                     f.cod_zona AS codigozona,
                     decode(a.val_limi_ctrl_vent, 0, '1', NULL) AS indactividad,
                     a.val_limi_ctrl_vent AS limiteventa,
                     a.val_unid
                FROM ped_gesti_stock a,
                     cra_perio       b,
                     seg_perio_corpo c,
                     seg_pais        d,
                     pre_ofert_detal e,
                     zon_zona        f,
                     pre_tipo_ofert  g
               WHERE a.perd_oid_peri = b.oid_peri
                 AND b.peri_oid_peri = c.oid_peri
                 AND c.cod_peri IN ((SELECT cod_peri
                                      FROM bas_ctrl_fact
                                     WHERE ind_camp_act = 1
                                       AND sta_camp = 0),
                                    gen_fn_calcu_perio((SELECT cod_peri
                                                         FROM bas_ctrl_fact
                                                        WHERE ind_camp_act = 1
                                                          AND sta_camp = 0),
                                                       1))
                 AND b.pais_oid_pais = d.oid_pais
                 AND d.cod_pais = vscodigopais -- obtener codigo del pais
                 AND a.ofde_oid_deta_ofer = e.oid_deta_ofer
                 AND a.zzon_oid_zona = f.oid_zona
                 AND a.val_limi_ctrl_vent IS NOT NULL
                 AND g.oid_tipo_ofer = e.tofe_oid_tipo_ofer
                    --AND g.cod_tipo_ofer NOT IN (21, 23)
                 AND (a.ind_acti IS NULL OR a.ind_acti = '1')
              UNION
              SELECT d.cod_pais AS codigopais,
                     c.cod_peri AS codigoperiodo,
                     e.val_codi_vent AS codigoventa,
                     f.cod_zona AS codigozona,
                     decode(a.val_limi_ctrl_vent, 0, '1', NULL) AS indactividad,
                     a.val_limi_ctrl_vent AS limiteventa,
                     a.val_unid
                FROM ped_gesti_stock a,
                     cra_perio       b,
                     seg_perio_corpo c,
                     seg_pais        d,
                     pre_ofert_detal e,
                     zon_zona        f,
                     pre_tipo_ofert  g
               WHERE a.perd_oid_peri = b.oid_peri
                 AND b.peri_oid_peri = c.oid_peri
                 AND c.cod_peri IN ((SELECT cod_peri
                                      FROM bas_ctrl_fact
                                     WHERE ind_camp_act = 1
                                       AND sta_camp = 0),
                                    gen_fn_calcu_perio((SELECT cod_peri
                                                         FROM bas_ctrl_fact
                                                        WHERE ind_camp_act = 1
                                                          AND sta_camp = 0),
                                                       1))
                 AND b.pais_oid_pais = d.oid_pais
                 AND d.cod_pais = vscodigopais -- obtener codigo del pais
                 AND a.ofde_oid_deta_ofer = e.oid_deta_ofer
                 AND a.zzon_oid_zona IS NULL
                 AND a.zorg_oid_regi IS NULL
                 AND f.ind_acti = 1
                 AND a.val_limi_ctrl_vent IS NOT NULL
                 AND g.oid_tipo_ofer = e.tofe_oid_tipo_ofer
                 AND a.clas_oid_clas IS NULL
                 AND a.tccl_oid_tipo_clas IS NULL
                    --AND g.cod_tipo_ofer NOT IN (21, 23)
                 AND (a.ind_acti IS NULL OR a.ind_acti = '1')
              UNION
              SELECT d.cod_pais AS codigopais,
                     c.cod_peri AS codigoperiodo,
                     e.val_codi_vent AS codigoventa,
                     f.cod_zona AS codigozona,
                     decode(a.val_limi_ctrl_vent, 0, '1', NULL) AS indactividad,
                     a.val_limi_ctrl_vent AS limiteventa,
                     a.val_unid
                FROM ped_gesti_stock a,
                     cra_perio       b,
                     seg_perio_corpo c,
                     seg_pais        d,
                     pre_ofert_detal e,
                     zon_zona        f,
                     zon_regio       g,
                     pre_tipo_ofert  h
               WHERE a.perd_oid_peri = b.oid_peri
                 AND b.peri_oid_peri = c.oid_peri
                 AND c.cod_peri IN ((SELECT cod_peri
                                      FROM bas_ctrl_fact
                                     WHERE ind_camp_act = 1
                                       AND sta_camp = 0),
                                    gen_fn_calcu_perio((SELECT cod_peri
                                                         FROM bas_ctrl_fact
                                                        WHERE ind_camp_act = 1
                                                          AND sta_camp = 0),
                                                       1))
                 AND b.pais_oid_pais = d.oid_pais
                 AND d.cod_pais = vscodigopais -- obtener codigo del pais
                 AND a.ofde_oid_deta_ofer = e.oid_deta_ofer
                 AND a.zzon_oid_zona IS NULL
                 AND a.zorg_oid_regi IS NOT NULL
                 AND f.ind_acti = 1
                 AND g.oid_regi = a.zorg_oid_regi
                 AND g.oid_regi = f.zorg_oid_regi
                 AND g.ind_acti = 1
                 AND a.clas_oid_clas IS NULL
                 AND a.tccl_oid_tipo_clas IS NULL
                 AND a.val_limi_ctrl_vent IS NOT NULL
                 AND h.oid_tipo_ofer = e.tofe_oid_tipo_ofer
                    --AND h.cod_tipo_ofer NOT IN (21, 23)
                 AND (a.ind_acti IS NULL OR a.ind_acti = '1'))
      --WHERE limiteventa <> 0
      ;
  
    TYPE interfazrec IS RECORD(
      codigopais    seg_pais.cod_pais%TYPE,
      codigoperiodo seg_perio_corpo.cod_peri%TYPE,
      codigoventa   pre_ofert_detal.val_codi_vent%TYPE,
      codigozona    zon_zona.cod_zona%TYPE,
      indactividad  ped_gesti_stock.val_limi_ctrl_vent%TYPE,
      limiteventa   ped_gesti_stock.val_limi_ctrl_vent%TYPE,
      valunid       ped_gesti_stock.val_unid%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz(pscodigopais);
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigopais || ';' || interfazrecord(x).codigoperiodo || ';' || interfazrecord(x)
                     .codigoventa || ';' || interfazrecord(x).codigozona || ';' || interfazrecord(x)
                     .indactividad || ';' || interfazrecord(x).limiteventa || ';' || interfazrecord(x)
                     .valunid;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_limite_venta_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_limite_venta_prol;

  /******************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Contrato Ejecutivas OCR-76
  Fecha Creacion    : 28/01/2013
  Autor             : Sergio Buchelli
  *********************************************************************************/
  PROCEDURE int_pr_ocr_contr_ejecu
  (
    pscodigopais      VARCHAR2,
    pscodigosistema   VARCHAR2,
    pscodigointerfaz  VARCHAR2,
    psnombrearchivo   VARCHAR2,
    psindicadororigen VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_contr_desar.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_contr_desar.cod_comp%TYPE;
    TYPE t_num_docu IS TABLE OF int_ocr_contr_desar.num_docu%TYPE;
    TYPE t_cod_peri IS TABLE OF int_ocr_contr_desar.cod_peri%TYPE;
    TYPE t_cod_tipo_docu IS TABLE OF int_ocr_contr_desar.cod_tipo_docu%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_ocr_contr_desar.num_docu_iden%TYPE;
    TYPE t_num_docu_lega IS TABLE OF int_ocr_contr_desar.num_docu_lega%TYPE;
    TYPE t_cod_clie IS TABLE OF int_ocr_contr_desar.cod_clie%TYPE;
    TYPE t_fec_proc IS TABLE OF int_ocr_contr_desar.fec_proc%TYPE;
  
    TYPE t_ind_esta_proc IS TABLE OF int_ocr_contr_desar.ind_esta_proc%TYPE;
    TYPE t_cod_moti_rech IS TABLE OF int_ocr_contr_desar.cod_moti_rech%TYPE;
    --
    TYPE t_cod_regi IS TABLE OF int_ocr_contr_desar.cod_regi%TYPE;
    TYPE t_cod_zona IS TABLE OF int_ocr_contr_desar.cod_zona%TYPE;
    --
    TYPE t_ind_firm IS TABLE OF int_ocr_contr_desar.ind_firm%TYPE;
    --
    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_comp      t_cod_comp := t_cod_comp();
    v_num_docu      t_num_docu := t_num_docu();
    v_cod_peri      t_cod_peri := t_cod_peri();
    v_cod_tipo_docu t_cod_tipo_docu := t_cod_tipo_docu();
    --
    v_num_docu_iden t_num_docu_iden := t_num_docu_iden();
    v_num_docu_lega t_num_docu_lega := t_num_docu_lega();
    --
    v_cod_clie t_cod_clie := t_cod_clie();
    --
    v_fec_proc      t_fec_proc := t_fec_proc();
    v_ind_esta_proc t_ind_esta_proc := t_ind_esta_proc();
    v_cod_moti_rech t_cod_moti_rech := t_cod_moti_rech();
    --
    v_cod_regi t_cod_regi := t_cod_regi();
    v_cod_zona t_cod_zona := t_cod_zona();
    --
    v_ind_firm t_ind_firm := t_ind_firm();
    --
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo      bas_inter.dir_temp%TYPE;
    v_handle        utl_file.file_type;
    lslinea         VARCHAR2(1000);
    lsnombrearchivo VARCHAR2(50);
    i               BINARY_INTEGER := 0;
    posicion        NUMBER := 0;
    longitud        NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
        
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
        
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
          
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                  v_cod_pais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_cod_pais(i),
                                                                               v_cod_comp(i));
                ELSIF (posicion = 3) THEN
                  v_num_docu.extend;
                  v_num_docu(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 4) THEN
                  v_cod_peri.extend;
                  v_cod_peri(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 5) THEN
                  v_cod_tipo_docu.extend;
                  v_cod_tipo_docu(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 6) THEN
                  v_num_docu_iden.extend;
                  v_num_docu_iden(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 7) THEN
                  v_num_docu_lega.extend;
                  v_num_docu_lega(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 8) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                
                ELSIF (posicion = 9) THEN
                  v_fec_proc.extend;
                  -- Si la fecha llega con un formato invalido, se captura la
                  -- excepcion y se le coloca null
                  BEGIN
                    v_fec_proc(i) := to_date(TRIM(substr(lslinea,
                                                         inicio,
                                                         longitud)),
                                             'yyyyMMdd');
                  EXCEPTION
                    WHEN OTHERS THEN
                      v_fec_proc(i) := NULL;
                  END;
                
                ELSIF (posicion = 10) THEN
                  v_ind_esta_proc.extend;
                  v_ind_esta_proc(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 11) THEN
                  v_cod_moti_rech.extend;
                  v_cod_moti_rech(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                  --
                ELSIF (posicion = 12) THEN
                  -- REGION DE ARRIBO
                  v_cod_regi.extend;
                  v_cod_regi(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 13) THEN
                  -- ZONA DE ARRIBO
                  v_cod_zona.extend;
                  v_cod_zona(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                  --
                ELSIF (posicion = 14) THEN
                  -- INDICADOR DE FIRMA
                  v_ind_firm.extend;
                  v_ind_firm(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                  --
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    DELETE FROM int_ocr_contr_desar;
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_cod_pais.count
      INSERT INTO int_ocr_contr_desar
        (cod_pais,
         cod_comp,
         num_docu,
         cod_peri,
         cod_tipo_docu,
         num_docu_iden,
         num_docu_lega,
         cod_clie,
         fec_proc,
         ind_esta_proc,
         cod_moti_rech,
         cod_regi,
         cod_zona,
         ind_firm,
         ind_orig)
      VALUES
        (v_cod_pais(i),
         v_cod_comp(i),
         v_num_docu(i),
         v_cod_peri(i),
         v_cod_tipo_docu(i),
         v_num_docu_iden(i),
         v_num_docu_lega(i),
         v_cod_clie(i),
         v_fec_proc(i),
         v_ind_esta_proc(i),
         v_cod_moti_rech(i),
         v_cod_regi(i),
         v_cod_zona(i),
         v_ind_firm(i),
         psindicadororigen);
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_CONTR_EJECU: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_contr_ejecu;

  /***************************************************************************
  Descripcion       : Consolida Contrato ejecutivas
  Fecha Creacion    : 29/01/2013
  Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE int_pr_ocr_conso_ejecu
  (
    pscodigopais      VARCHAR2,
    pscodigousuario   VARCHAR2,
    psnumlote         VARCHAR2,
    pscodigodocumento VARCHAR2
  ) IS
  
    CURSOR curinsconsoced IS
      SELECT cod_pais,
             cod_comp,
             num_docu,
             cod_peri,
             cod_tipo_docu,
             num_docu_iden,
             num_docu_lega,
             cod_clie,
             fec_proc,
             ind_esta_proc,
             cod_moti_rech,
             cod_regi,
             cod_zona,
             ind_firm,
             seq_docu_sto.nextval sec_nume_docu,
             psnumlote            num_lote,
             ind_orig
        FROM int_ocr_contr_desar cred
       WHERE cred.cod_pais = pscodigopais;
  
    TYPE solic_contr_desa_tab_t IS TABLE OF int_solic_conso_contr_desar%ROWTYPE INDEX BY BINARY_INTEGER;
    solic_contr_desa_tab solic_contr_desa_tab_t; -- In-memory table
  
    i BINARY_INTEGER := 0;
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t; -- In-memory table
  
  BEGIN
  
    OPEN curinsconsoced;
    LOOP
    
      FETCH curinsconsoced BULK COLLECT
        INTO solic_contr_desa_tab LIMIT w_filas;
      EXIT WHEN solic_contr_desa_tab.count = 0;
    
      FOR j IN solic_contr_desa_tab.first .. solic_contr_desa_tab.last
      LOOP
      
        sto_tab(j).cod_pais := solic_contr_desa_tab(j).cod_pais;
        sto_tab(j).cod_tipo_docu := pscodigodocumento;
        sto_tab(j).num_lote := solic_contr_desa_tab(j).num_lote;
        sto_tab(j).sec_nume_docu := solic_contr_desa_tab(j).sec_nume_docu;
        sto_tab(j).num_docu := solic_contr_desa_tab(j).num_docu;
        sto_tab(j).ind_envi := '0';
        sto_tab(j).ind_rech := '0';
        sto_tab(j).fec_digi := SYSDATE;
        sto_tab(j).usu_digi := pscodigousuario;
        sto_tab(j).fec_modi := SYSDATE;
        sto_tab(j).usu_modi := pscodigousuario;
        sto_tab(j).cod_zona := solic_contr_desa_tab(j).cod_zona;
        sto_tab(j).cod_clie := solic_contr_desa_tab(j).cod_clie;
        sto_tab(j).cod_zona_arri := solic_contr_desa_tab(j).cod_zona;
        sto_tab(j).cod_peri := solic_contr_desa_tab(j).cod_peri;
        --sto_tab(j).COD_MOTI_RECH      :=
        --sto_tab(j).val_obse_rech_defi := sol_credi_tab(j).val_obse_rech_defi;
        sto_tab(j).ind_rece_ocr := '0';
        sto_tab(j).ind_rece_web := '0';
        sto_tab(j).ind_rece_dd := '0';
        sto_tab(j).ind_rece_digi := '0';
        sto_tab(j).ind_rece_cc := '0';
        sto_tab(j).ind_rece_mens := '0';
        sto_tab(j).ind_elim := '0';
        sto_tab(j).ind_rece_onli := '0';
        sto_tab(j).ind_rece_ivr := '0';
      
        IF solic_contr_desa_tab(j).ind_orig = 'O' THEN
          sto_tab(j).ind_rece_ocr := '1';
        ELSIF solic_contr_desa_tab(j).ind_orig = 'W' THEN
          sto_tab(j).ind_rece_web := '1';
        END IF;
      
      END LOOP;
    
      FORALL i IN solic_contr_desa_tab.first .. solic_contr_desa_tab.last
        INSERT INTO int_solic_conso_contr_desar
        VALUES solic_contr_desa_tab
          (i);
    
      FORALL j IN solic_contr_desa_tab.first .. solic_contr_desa_tab.last
        INSERT INTO sto_docum_digit VALUES sto_tab (j);
    END LOOP;
    CLOSE curinsconsoced;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_CONSO_EJECU: ' ||
                              ls_sqlerrm);
  END int_pr_ocr_conso_ejecu;

  /***************************************************************************
   Archivo           : Envio de Informacion de Nuevos Faltantes
   Fecha Creacion    : 05/02/2013
   Autor             : Danny Amaro
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envia_nuevo_falta
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT b.cod_zona AS codigozona,
             nvl((SELECT b.cod_alma
                   FROM ape_confi_liafp_detal a,
                        bel_almac             b,
                        app_confi_centr_distr c,
                        ape_confi_liafp_cabec d
                  WHERE a.zzon_oid_zona = oid_zona
                    AND a.liac_oid_conf_lafp_cabe = oid_conf_lafp_cabe
                    AND d.ccdi_oid_conf_cent_dist = c.oid_conf_cent_dist
                    AND c.oid_conf_cent_dist = b.ccdi_oid_confi_centr_distr),
                 'A01') codigoalmacen
        FROM zon_zona b
       WHERE b.ind_acti = 1;
  
    TYPE interfazrec IS RECORD(
      codigozona    zon_zona.cod_zona%TYPE,
      codigoalmacen bel_almac.cod_alma%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .codigozona || ';' || interfazrecord(x).codigoalmacen;
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_envia_nuevo_falta: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_envia_nuevo_falta;

  /***************************************************************************
   Descripcion       : Envio de Cupones - Prol
   Fecha Creacion    : 08/05/2013
   Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_cup_cupon_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
    ------   Premios Electivos para consultoras constantes, de programa Constante   -----
      SELECT camp,
             cod_cons,
             cod_prog,
             cod_cupon,
             cantidad,
             electivo,
             defecto
        FROM (SELECT fin_pkg_gener.fin_fn_obtie_codig_perio_actua camp,
                     nv.cod_cons,
                     nv.cod_prog,
                     eq.cod_cupon,
                     '1' cantidad,
                     decode((SELECT '1'
                              FROM lov_equiv_matr lem
                             WHERE lem.cod_prog = nv.cod_prog
                               AND eq.cod_cupon = lem.cod_cupon
                               AND eq.cod_peri = lem.cod_peri),
                            '1',
                            '1',
                            '0') electivo,
                     decode((SELECT '1'
                              FROM lov_cupon_defec lv
                             WHERE lv.cod_prog = nv.cod_prog
                               AND eq.cod_cupon = lv.cod_cupo
                               AND eq.cod_peri = lv.cod_peri),
                            '1',
                            '1',
                            '0') defecto
                FROM cup_equiv_matr       eq,
                     cup_consu_nueva      nv,
                     lov_equiv_matr       leq,
                     cup_consu_factu      cf,
                     cup_prog_nueva_cupon cpn
               WHERE nv.cod_prog = eq.cod_prog
                 AND nv.camp_ini_ccc >=
                     gen_pkg_gener.gen_fn_perio_nsigu(nv.cod_pais,
                                                      fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                      -3) -----   campa?a de Proceso - 3
                 AND nv.camp_ini_ccc <
                     fin_pkg_gener.fin_fn_obtie_codig_perio_actua -----   para que no tome 1er pedido
                 AND eq.cod_nivel =
                     (SELECT CASE
                               WHEN cpn2.ind_cupo = 0 THEN
                                lpad((gen_pkg_gener.gen_fn_devue_difer_perio_pais(nv2.cod_pais,
                                                                                  nv2.camp_ini_ccc,
                                                                                  fin_pkg_gener.fin_fn_obtie_codig_perio_actua)),
                                     2,
                                     0)
                               ELSE
                                lpad((gen_pkg_gener.gen_fn_devue_difer_perio_pais(nv2.cod_pais,
                                                                                  nv2.camp_ini_ccc,
                                                                                  fin_pkg_gener.fin_fn_obtie_codig_perio_actua) + 1),
                                     2,
                                     0)
                             END
                        FROM cup_consu_nueva      nv2,
                             cup_prog_nueva_cupon cpn2
                       WHERE nv2.cod_cons = nv.cod_cons
                         AND nv2.cod_prog = nv.cod_prog
                         AND nv2.cod_prog = cpn2.cod_prog)
                 AND eq.cod_peri =
                     fin_pkg_gener.fin_fn_obtie_codig_perio_actua
                 AND eq.cod_peri = leq.cod_peri
                 AND eq.cod_prog = leq.cod_prog
                 AND eq.cod_cupon = leq.cod_cupon
                 AND eq.cod_prog = cf.cod_prog
                 AND nv.cod_cons = cf.cod_cons
                 AND cf.ind_cons = 0
                 AND nv.cod_prog = cpn.cod_prog
                 AND cpn.ind_cons_prem_elec = 1
                 AND NOT EXISTS (SELECT NULL
                        FROM cup_consu_cupon ccc
                       WHERE nv.cod_prog = ccc.cod_prog
                         AND eq.cod_cupon = ccc.cod_cupon
                         AND nv.cod_cons = ccc.cod_cons
                         AND ccc.ind_utili = 1)
                 AND NOT EXISTS (SELECT NULL
                        FROM int_solic_conso_cabec isc
                       WHERE isc.cod_clie = nv.cod_cons
                         AND isc.cod_peri =
                             fin_pkg_gener.fin_fn_obtie_codig_perio_actua
                         AND isc.ind_ocs_proc = 1
                         AND isc.ind_proc_gp2 = 1)
              ----
              UNION ------   Premios Electivos para consultoras constantes o no , para progrma que no exige constancia Prem. Electivo  -----
              ------
              SELECT fin_pkg_gener.fin_fn_obtie_codig_perio_actua camp,
                     nv.cod_cons,
                     nv.cod_prog,
                     eq.cod_cupon,
                     '1' cantidad,
                     decode((SELECT '1'
                              FROM lov_equiv_matr lem
                             WHERE lem.cod_prog = nv.cod_prog
                               AND eq.cod_cupon = lem.cod_cupon
                               AND eq.cod_peri = lem.cod_peri),
                            '1',
                            '1',
                            '0') electivo,
                     decode((SELECT '1'
                              FROM lov_cupon_defec lv
                             WHERE lv.cod_prog = nv.cod_prog
                               AND eq.cod_cupon = lv.cod_cupo
                               AND eq.cod_peri = lv.cod_peri),
                            '1',
                            '1',
                            '0') defecto
                FROM cup_equiv_matr       eq,
                     cup_consu_nueva      nv,
                     lov_equiv_matr       leq,
                     cup_prog_nueva_cupon cpn
               WHERE nv.cod_prog = eq.cod_prog
                 AND nv.camp_ini_ccc >=
                     gen_pkg_gener.gen_fn_perio_nsigu(nv.cod_pais,
                                                      fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                      -3) -----   campa?a de Proceso - 3
                 AND nv.camp_ini_ccc <
                     fin_pkg_gener.fin_fn_obtie_codig_perio_actua -----   para que no tome 1er pedido
                 AND eq.cod_nivel =
                     (SELECT CASE
                               WHEN cpn2.ind_cupo = 0 THEN
                                lpad((gen_pkg_gener.gen_fn_devue_difer_perio_pais(nv2.cod_pais,
                                                                                  nv2.camp_ini_ccc,
                                                                                  fin_pkg_gener.fin_fn_obtie_codig_perio_actua)),
                                     2,
                                     0)
                               ELSE
                                lpad((gen_pkg_gener.gen_fn_devue_difer_perio_pais(nv2.cod_pais,
                                                                                  nv2.camp_ini_ccc,
                                                                                  fin_pkg_gener.fin_fn_obtie_codig_perio_actua) + 1),
                                     2,
                                     0)
                             END
                        FROM cup_consu_nueva      nv2,
                             cup_prog_nueva_cupon cpn2
                       WHERE nv2.cod_cons = nv.cod_cons
                         AND nv2.cod_prog = nv.cod_prog
                         AND nv2.cod_prog = cpn2.cod_prog)
                 AND eq.cod_peri =
                     fin_pkg_gener.fin_fn_obtie_codig_perio_actua
                 AND eq.cod_peri = leq.cod_peri
                 AND eq.cod_prog = leq.cod_prog
                 AND eq.cod_cupon = leq.cod_cupon
                 AND nv.cod_prog = cpn.cod_prog
                 AND cpn.ind_cons_prem_elec = 0
                 AND NOT EXISTS (SELECT NULL
                        FROM cup_consu_cupon ccc
                       WHERE nv.cod_prog = ccc.cod_prog
                         AND eq.cod_cupon = ccc.cod_cupon
                         AND nv.cod_cons = ccc.cod_cons
                         AND ccc.ind_utili = 1)
                 AND NOT EXISTS (SELECT NULL
                        FROM int_solic_conso_cabec isc
                       WHERE isc.cod_clie = nv.cod_cons
                         AND isc.cod_peri =
                             fin_pkg_gener.fin_fn_obtie_codig_perio_actua
                         AND isc.ind_ocs_proc = 1
                         AND isc.ind_proc_gp2 = 1)
              
              UNION -----    Cuponera para consultoras de Programa que no exige constancia Cupones  -----
              
              SELECT fin_pkg_gener.fin_fn_obtie_codig_perio_actua camp,
                     nv.cod_cons,
                     nv.cod_prog,
                     eq.cod_cupon,
                     '1' cantidad,
                     '0' electivo,
                     '0' defecto
                FROM cup_equiv_matr eq,
                     cup_consu_nueva nv,
                     cup_prog_nueva_cupon cpn,
                     (SELECT dat.cod_prog,
                             dat.cod_cons,
                             nivel.niv,
                             gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                              dat.camp_ini_ccc,
                                                              to_number(nivel.niv) - 1) cam_inic,
                             decode(nvl(dat.ind_vige, ' '),
                                    'N',
                                    gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                     (gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                                                       dat.camp_ini_ccc,
                                                                                                       to_number(nivel.niv) - 1)),
                                                                     (SELECT num_vige
                                                                        FROM nvs_progr_nivel npn
                                                                       WHERE dat.cod_prog =
                                                                             npn.cod_prog
                                                                         AND npn.cod_nive =
                                                                             nivel.niv) - 1),
                                    gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                     (gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                                                       dat.camp_ini_ccc,
                                                                                                       to_number(nivel.niv) - 1)),
                                                                     dat.num_vig - 1)) cam_fin
                        FROM (SELECT nv2.cod_prog,
                                     cpn2.ind_vige,
                                     nv2.cod_cons,
                                     nv2.camp_ini_ccc con_camp_ini,
                                     CASE
                                       WHEN cpn2.ind_cupo = 0 THEN
                                        gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                         nv2.camp_ini_ccc,
                                                                         1)
                                       ELSE
                                        nv2.camp_ini_ccc
                                     END camp_ini_ccc,
                                     CASE
                                       WHEN cpn2.ind_cupo = 0 THEN
                                        lpad((gen_pkg_gener.gen_fn_devue_difer_perio_pais(nv2.cod_pais,
                                                                                          nv2.camp_ini_ccc,
                                                                                          fin_pkg_gener.fin_fn_obtie_codig_perio_actua)),
                                             2,
                                             0)
                                       ELSE
                                        lpad((gen_pkg_gener.gen_fn_devue_difer_perio_pais(nv2.cod_pais,
                                                                                          nv2.camp_ini_ccc,
                                                                                          fin_pkg_gener.fin_fn_obtie_codig_perio_actua) + 1),
                                             2,
                                             0)
                                     END niv,
                                     cpn2.ind_cupo,
                                     ---  cpn2.num_vig
                                     CASE
                                       WHEN nvl(cpn2.ind_vige, ' ') <> 'N' THEN
                                        cpn2.num_vig
                                       ELSE
                                        (SELECT num_vige
                                           FROM nvs_progr_nivel npn
                                          WHERE cpn2.cod_prog = npn.cod_prog
                                            AND npn.cod_nive =
                                                decode(cpn2.ind_cupo,
                                                       0,
                                                       lpad((gen_pkg_gener.gen_fn_devue_difer_perio_pais(nv2.cod_pais,
                                                                                                         nv2.camp_ini_ccc,
                                                                                                         fin_pkg_gener.fin_fn_obtie_codig_perio_actua)),
                                                            2,
                                                            0),
                                                       lpad((gen_pkg_gener.gen_fn_devue_difer_perio_pais(nv2.cod_pais,
                                                                                                         nv2.camp_ini_ccc,
                                                                                                         fin_pkg_gener.fin_fn_obtie_codig_perio_actua) + 1),
                                                            2,
                                                            0)))
                                     END num_vig
                                FROM cup_consu_nueva      nv2,
                                     cup_prog_nueva_cupon cpn2
                               WHERE nv2.cod_prog = cpn2.cod_prog
                                 AND nv2.camp_ini_ccc >=
                                     gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                      fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                      -3)) dat,
                             (SELECT element niv
                                FROM (WITH x AS (SELECT '01,02,03,04' str
                                                   FROM dual)
                                       SELECT regexp_substr(str,
                                                            '[^,]+',
                                                            1,
                                                            LEVEL) element
                                         FROM x
                                       CONNECT BY LEVEL <=
                                                  length(regexp_replace(str,
                                                                        '[^,]+')) + 1) cod
                              ) nivel
                       WHERE dat.niv >= nivel.niv) niveles
               WHERE nv.cod_prog = eq.cod_prog
                 AND nv.camp_ini_ccc >=
                     gen_pkg_gener.gen_fn_perio_nsigu(nv.cod_pais,
                                                      fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                      -3) -----   campa?a de Proceso - 3
                 AND nv.camp_ini_ccc <
                     fin_pkg_gener.fin_fn_obtie_codig_perio_actua -----   para que no tome 1er pedido
                 AND nv.cod_cons = niveles.cod_cons
                 AND nv.cod_prog = niveles.cod_prog
                 AND fin_pkg_gener.fin_fn_obtie_codig_perio_actua >=
                     niveles.cam_inic
                 AND fin_pkg_gener.fin_fn_obtie_codig_perio_actua <=
                     niveles.cam_fin
                 AND eq.cod_nivel = niveles.niv
                 AND eq.cod_peri =
                     fin_pkg_gener.fin_fn_obtie_codig_perio_actua
                 AND nv.cod_prog = cpn.cod_prog
                 AND cpn.ind_const = 0
                 AND NOT EXISTS (SELECT NULL
                        FROM lov_equiv_matr lem
                       WHERE lem.cod_peri = eq.cod_peri
                         AND lem.cod_prog = eq.cod_prog
                         AND lem.cod_cupon = eq.cod_cupon)
                 AND  (   ( NOT EXISTS (SELECT NULL
                        FROM cup_consu_cupon ccc
                       WHERE nv.cod_prog = ccc.cod_prog
                         AND eq.cod_cupon = ccc.cod_cupon
                         AND nv.cod_cons = ccc.cod_cons
                         AND ccc.ind_utili = 1) AND nvl(CPN.IND_CUPO_REUT,0) = 0 )
                        OR 
                        ( NOT EXISTS (SELECT NULL
                        FROM cup_consu_cupon ccc
                       WHERE nv.cod_prog = ccc.cod_prog
                         AND eq.cod_cupon = ccc.cod_cupon
                         AND nv.cod_cons = ccc.cod_cons
                         AND ccc.cam_utili = fin_pkg_gener.fin_fn_obtie_codig_perio_actua) 
                          and nvl(CPN.IND_CUPO_REUT,0) = 1 )
                          )
              
              UNION -----    Cuponera para consultoras constantes de Programa que exige constancia Cupones  -----
              
              SELECT fin_pkg_gener.fin_fn_obtie_codig_perio_actua camp,
                     nv.cod_cons,
                     nv.cod_prog,
                     eq.cod_cupon,
                     '1' cantidad,
                     '0' electivo,
                     '0' defecto
                FROM cup_equiv_matr eq,
                     cup_consu_nueva nv,
                     cup_prog_nueva_cupon cpn,
                     cup_consu_factu fc,
                     (SELECT dat.cod_prog,
                             dat.cod_cons,
                             nivel.niv,
                             gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                              dat.camp_ini_ccc,
                                                              to_number(nivel.niv) - 1) cam_inic,
                             decode(nvl(dat.ind_vige, ' '),
                                    'N',
                                    gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                     (gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                                                       dat.camp_ini_ccc,
                                                                                                       to_number(nivel.niv) - 1)),
                                                                     ---  dat.num_vig
                                                                     (SELECT num_vige
                                                                        FROM nvs_progr_nivel npn
                                                                       WHERE dat.cod_prog =
                                                                             npn.cod_prog
                                                                         AND npn.cod_nive =
                                                                             nivel.niv) - 1),
                                    gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                     (gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                                                       dat.camp_ini_ccc,
                                                                                                       to_number(nivel.niv) - 1)),
                                                                     dat.num_vig - 1)) cam_fin
                        FROM (SELECT nv2.cod_prog,
                                     cpn2.ind_vige,
                                     nv2.cod_cons,
                                     nv2.camp_ini_ccc con_camp_ini,
                                     CASE
                                       WHEN cpn2.ind_cupo = 0 THEN
                                        gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                         nv2.camp_ini_ccc,
                                                                         1)
                                       ELSE
                                        nv2.camp_ini_ccc
                                     END camp_ini_ccc,
                                     CASE
                                       WHEN cpn2.ind_cupo = 0 THEN
                                        lpad((gen_pkg_gener.gen_fn_devue_difer_perio_pais(nv2.cod_pais,
                                                                                          nv2.camp_ini_ccc,
                                                                                          fin_pkg_gener.fin_fn_obtie_codig_perio_actua)),
                                             2,
                                             0)
                                       ELSE
                                        lpad((gen_pkg_gener.gen_fn_devue_difer_perio_pais(nv2.cod_pais,
                                                                                          nv2.camp_ini_ccc,
                                                                                          fin_pkg_gener.fin_fn_obtie_codig_perio_actua) + 1),
                                             2,
                                             0)
                                     END niv,
                                     cpn2.ind_cupo,
                                     ----cpn2.num_vig
                                     CASE
                                       WHEN nvl(cpn2.ind_vige, ' ') <> 'N' THEN
                                        cpn2.num_vig
                                       ELSE
                                        (SELECT num_vige
                                           FROM nvs_progr_nivel npn
                                          WHERE cpn2.cod_prog = npn.cod_prog
                                            AND npn.cod_nive =
                                                decode(cpn2.ind_cupo,
                                                       0,
                                                       lpad((gen_pkg_gener.gen_fn_devue_difer_perio_pais(nv2.cod_pais,
                                                                                                         nv2.camp_ini_ccc,
                                                                                                         fin_pkg_gener.fin_fn_obtie_codig_perio_actua)),
                                                            2,
                                                            0),
                                                       lpad((gen_pkg_gener.gen_fn_devue_difer_perio_pais(nv2.cod_pais,
                                                                                                         nv2.camp_ini_ccc,
                                                                                                         fin_pkg_gener.fin_fn_obtie_codig_perio_actua) + 1),
                                                            2,
                                                            0)))
                                     END num_vig
                                FROM cup_consu_nueva      nv2,
                                     cup_prog_nueva_cupon cpn2
                               WHERE nv2.cod_prog = cpn2.cod_prog
                                 AND nv2.camp_ini_ccc >=
                                     gen_pkg_gener.gen_fn_perio_nsigu(pscodigopais,
                                                                      fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                                      -3)) dat,
                             (SELECT element niv
                                FROM (WITH x AS (SELECT '01,02,03,04' str
                                                   FROM dual)
                                       SELECT regexp_substr(str,
                                                            '[^,]+',
                                                            1,
                                                            LEVEL) element
                                         FROM x
                                       CONNECT BY LEVEL <=
                                                  length(regexp_replace(str,
                                                                        '[^,]+')) + 1) cod
                              ) nivel
                       WHERE dat.niv >= nivel.niv) niveles
               WHERE nv.cod_prog = eq.cod_prog
                 AND nv.camp_ini_ccc >=
                     gen_pkg_gener.gen_fn_perio_nsigu(nv.cod_pais,
                                                      fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                                      -3) -----   campa?a de Proceso - 3
                 AND nv.camp_ini_ccc <
                     fin_pkg_gener.fin_fn_obtie_codig_perio_actua -----   para que no tome 1er pedido
                 AND nv.cod_cons = niveles.cod_cons
                 AND nv.cod_prog = niveles.cod_prog
                 AND fin_pkg_gener.fin_fn_obtie_codig_perio_actua >=
                     niveles.cam_inic
                 AND fin_pkg_gener.fin_fn_obtie_codig_perio_actua <=
                     niveles.cam_fin
                 AND eq.cod_nivel = niveles.niv
                 AND eq.cod_peri =
                     fin_pkg_gener.fin_fn_obtie_codig_perio_actua
                 AND nv.cod_prog = cpn.cod_prog
                 AND cpn.ind_const = 1
                 AND nv.cod_prog = fc.cod_prog
                 AND nv.cod_cons = fc.cod_cons
                 AND fc.ind_cons = 0
                 AND NOT EXISTS (SELECT NULL
                        FROM lov_equiv_matr lem
                       WHERE lem.cod_peri = eq.cod_peri
                         AND lem.cod_prog = eq.cod_prog
                         AND lem.cod_cupon = eq.cod_cupon)
                 AND  (   ( NOT EXISTS (SELECT NULL
                        FROM cup_consu_cupon ccc
                       WHERE nv.cod_prog = ccc.cod_prog
                         AND eq.cod_cupon = ccc.cod_cupon
                         AND nv.cod_cons = ccc.cod_cons
                         AND ccc.ind_utili = 1) AND nvl(CPN.IND_CUPO_REUT,0) = 0 )
                        OR 
                        ( NOT EXISTS (SELECT NULL
                        FROM cup_consu_cupon ccc
                       WHERE nv.cod_prog = ccc.cod_prog
                         AND eq.cod_cupon = ccc.cod_cupon
                         AND nv.cod_cons = ccc.cod_cons
                         AND ccc.cam_utili = fin_pkg_gener.fin_fn_obtie_codig_perio_actua)
                           and nvl(CPN.IND_CUPO_REUT,0) = 1 )
                          )
                         )
       ORDER BY camp,
                cod_cons;
  
    TYPE interfazrec IS RECORD(
      campanya         VARCHAR2(6),
      codigoconsultora cup_consu_nueva.cod_cons%TYPE,
      codigoprograma   cup_consu_nueva.cod_prog%TYPE,
      codigocupon      cup_equiv_matr.cod_cupon%TYPE,
      cantidad         VARCHAR2(1),
      electivo         VARCHAR2(1),
      defecto          VARCHAR2(1));
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .campanya || ';' || interfazrecord(x).codigoconsultora || ';' || interfazrecord(x)
                     .codigoprograma || ';' || interfazrecord(x).codigocupon || ';' || interfazrecord(x)
                     .cantidad || ';' || interfazrecord(x).electivo || ';' || interfazrecord(x)
                     .defecto;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_envi_cup_cupon_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_envi_cup_cupon_prol;

  /***************************************************************************
   Descripcion       : Envio de Cupones de Homologacion - Prol
   Fecha Creacion    : 08/05/2013
   Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_cup_homol_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT eq.cod_peri,
             eq.cod_prog,
             eq.cod_cupon,
             eq.cod_venta
        FROM cup_equiv_matr eq
       WHERE eq.cod_peri = fin_pkg_gener.fin_fn_obtie_codig_perio_actua --- campaña de proceso
      ;
  
    TYPE interfazrec IS RECORD(
      campanya       cup_equiv_matr.cod_peri%TYPE,
      codigoprograma cup_equiv_matr.cod_prog%TYPE,
      codigocupon    cup_equiv_matr.cod_cupon%TYPE,
      codigoventa    cup_equiv_matr.cod_venta%TYPE);
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .campanya || ';' || interfazrecord(x).codigoprograma || ';' || interfazrecord(x)
                     .codigocupon || ';' || interfazrecord(x).codigoventa;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_envi_cup_homol_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_envi_cup_homol_prol;

  /***************************************************************************
   Descripcion       : Envio de Cupones de Participantes - Prol
   Fecha Creacion    : 08/05/2013
   Autor             : Aurelio Oviedo
  ***************************************************************************/
  PROCEDURE int_pr_ocr_envi_cup_parti_prol
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
  
    CURSOR c_interfaz IS
      SELECT fin_pkg_gener.fin_fn_obtie_codig_perio_actua camp,
             nv.cod_cons,
             nv.cod_prog,
             CASE
               WHEN cpn.ind_const = 1 AND cpn.ind_cons_prem_elec = 1 THEN
                decode(f.ind_cons, '1', '0', '1')
               ELSE
                '1'
             END participa,
             CASE
               WHEN cpn.ind_const = 1 AND cpn.ind_cons_prem_elec = 1 THEN
                decode(f.ind_cons, '1', 'Por Constancia', '')
             END motivo
        FROM cup_consu_nueva      nv,
             cup_consu_factu      f,
             cup_prog_nueva_cupon cpn
       WHERE nv.camp_ini_ccc >=
             gen_pkg_gener.gen_fn_perio_nsigu(nv.cod_pais,
                                              fin_pkg_gener.fin_fn_obtie_codig_perio_actua,
                                              -3) --- Campa?a de Proceso menos 3
         AND nv.cod_cons = f.cod_cons
         AND nv.cod_prog = f.cod_prog
         AND nv.cod_prog = cpn.cod_prog;
  
    TYPE interfazrec IS RECORD(
      campanya           VARCHAR2(6),
      codigoconsultora   cup_consu_nueva.cod_cons%TYPE,
      codigoprograma     cup_consu_nueva.cod_prog%TYPE,
      indicadorparticipa cup_prog_nueva_cupon.ind_const%TYPE,
      motivo             VARCHAR2(15));
  
    TYPE interfazrectab IS TABLE OF interfazrec;
  
    interfazrecord interfazrectab;
  
    /* Variables usadas para la generacion del archivo de texto */
  
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    lbabrirutlfile BOOLEAN;
  
  BEGIN
  
    /* Generando Archivo de Texto (Detalle) */
  
    lbabrirutlfile := TRUE;
  
    OPEN c_interfaz;
    LOOP
      FETCH c_interfaz BULK COLLECT
        INTO interfazrecord LIMIT w_filas;
    
      /* Procedimiento inicial para generar interfaz */
    
      IF lbabrirutlfile THEN
        gen_pkg_inter_archi.gen_pr_inici_inter(pscodigopais,
                                               pscodigosistema,
                                               pscodigointerfaz,
                                               psnombrearchivo,
                                               lsdirtempo,
                                               lsnombrearchivo,
                                               v_handle);
        lbabrirutlfile := FALSE;
      END IF;
    
      IF interfazrecord.count > 0 THEN
        FOR x IN interfazrecord.first .. interfazrecord.last
        LOOP
          lslinea := interfazrecord(x)
                     .campanya || ';' || interfazrecord(x).codigoconsultora || ';' || interfazrecord(x)
                     .codigoprograma || ';' || interfazrecord(x)
                     .indicadorparticipa || ';' || interfazrecord(x).motivo;
        
          utl_file.put_line(v_handle, lslinea);
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_interfaz%NOTFOUND;
    END LOOP;
  
    CLOSE c_interfaz;
  
    IF NOT lbabrirutlfile THEN
      utl_file.fclose(v_handle);
    
      /* Procedimiento final para generar interfaz */
    
      gen_pkg_inter_archi.gen_pr_final_inter('SICC_DIR',
                                             lsdirtempo,
                                             psnombrearchivo,
                                             lsnombrearchivo);
    END IF;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_envi_cup_parti_prol: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_envi_cup_parti_prol;

  /*****************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Carta Invitacion Flexipago
  Fecha Creacion    : 09/05/2013
  Autor             : Sebastian Guerra
  *****************************************************************************/
  PROCEDURE int_pr_ocr_recep_carta_flexi
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2,
    psnumerolote     VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_sec_nume_docu IS TABLE OF int_ocr_carta_flexi.sec_nume_docu%TYPE;
    TYPE t_cod_pais IS TABLE OF int_ocr_carta_flexi.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_carta_flexi.cod_comp%TYPE;
    TYPE t_num_docu IS TABLE OF int_ocr_carta_flexi.num_docu%TYPE;
    TYPE t_cod_tipo_docu_iden IS TABLE OF int_ocr_carta_flexi.cod_tipo_docu_iden%TYPE;
    TYPE t_num_docu_iden IS TABLE OF int_ocr_carta_flexi.num_docu_iden%TYPE;
    TYPE t_cod_clie IS TABLE OF int_ocr_carta_flexi.cod_clie%TYPE;
    TYPE t_fec_proc IS TABLE OF int_ocr_carta_flexi.fec_proc%TYPE;
    TYPE t_cod_esta_ocr IS TABLE OF int_ocr_carta_flexi.cod_esta_ocr%TYPE;
    TYPE t_mot_rech_ocr IS TABLE OF int_ocr_carta_flexi.mot_rech_ocr%TYPE;
    TYPE t_cod_regi IS TABLE OF int_ocr_carta_flexi.cod_regi%TYPE;
    TYPE t_cod_zona IS TABLE OF int_ocr_carta_flexi.cod_zona%TYPE;
    TYPE t_ind_firm_cons IS TABLE OF int_ocr_carta_flexi.ind_firm_cons%TYPE;
    TYPE t_ind_firm_repr_lega IS TABLE OF int_ocr_carta_flexi.ind_firm_repr_lega%TYPE;
    TYPE t_cam_regi IS TABLE OF int_ocr_carta_flexi.cam_regi%TYPE;
  
    v_sec_nume_docu      t_sec_nume_docu := t_sec_nume_docu();
    v_cod_pais           t_cod_pais := t_cod_pais();
    v_cod_comp           t_cod_comp := t_cod_comp();
    v_num_docu           t_num_docu := t_num_docu();
    v_cod_tipo_docu_iden t_cod_tipo_docu_iden := t_cod_tipo_docu_iden();
    v_num_docu_iden      t_num_docu_iden := t_num_docu_iden();
    v_cod_clie           t_cod_clie := t_cod_clie();
    v_fec_proc           t_fec_proc := t_fec_proc();
    v_cod_esta_ocr       t_cod_esta_ocr := t_cod_esta_ocr();
    v_mot_rech_ocr       t_mot_rech_ocr := t_mot_rech_ocr();
    v_cod_regi           t_cod_regi := t_cod_regi();
    v_cod_zona           t_cod_zona := t_cod_zona();
    v_ind_firm_cons      t_ind_firm_cons := t_ind_firm_cons();
    v_ind_firm_repr_lega t_ind_firm_repr_lega := t_ind_firm_repr_lega();
    v_cam_regi           t_cam_regi := t_cam_regi();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
  
    inicio NUMBER := 0;
  
    lscadena  VARCHAR2(100) := 'a"'',;|' || chr(10) || chr(13) || chr(20);
    lsreplace VARCHAR2(100) := 'a        ';
  
  BEGIN
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
      LOOP
        BEGIN
        
          utl_file.get_line(v_handle, lslinea);
          i      := i + 1;
          inicio := 1;
          v_sec_nume_docu.extend;
          v_sec_nume_docu(i) := i;
        
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 3) THEN
                  v_num_docu.extend;
                  v_num_docu(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 4) THEN
                  v_cam_regi.extend;
                  v_cam_regi(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 5) THEN
                  v_cod_tipo_docu_iden.extend;
                  v_cod_tipo_docu_iden(i) := TRIM(translate(substr(lslinea,
                                                                   inicio,
                                                                   longitud),
                                                            lscadena,
                                                            lsreplace));
                ELSIF (posicion = 6) THEN
                  v_num_docu_iden.extend;
                  v_num_docu_iden(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 7) THEN
                  v_cod_clie.extend;
                  v_cod_clie(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 8) THEN
                  v_fec_proc.extend;
                  v_fec_proc(i) := to_date(TRIM(translate(substr(lslinea,
                                                                 inicio,
                                                                 longitud),
                                                          lscadena,
                                                          lsreplace)),
                                           'YYYYMMDD');
                ELSIF (posicion = 9) THEN
                  v_cod_esta_ocr.extend;
                  v_cod_esta_ocr(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 10) THEN
                  v_mot_rech_ocr.extend;
                  v_mot_rech_ocr(i) := TRIM(translate(substr(lslinea,
                                                             inicio,
                                                             longitud),
                                                      lscadena,
                                                      lsreplace));
                ELSIF (posicion = 11) THEN
                  v_cod_regi.extend;
                  v_cod_regi(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 12) THEN
                  v_cod_zona.extend;
                  v_cod_zona(i) := TRIM(translate(substr(lslinea,
                                                         inicio,
                                                         longitud),
                                                  lscadena,
                                                  lsreplace));
                ELSIF (posicion = 13) THEN
                  v_ind_firm_cons.extend;
                  v_ind_firm_cons(i) := TRIM(translate(substr(lslinea,
                                                              inicio,
                                                              longitud),
                                                       lscadena,
                                                       lsreplace));
                ELSIF (posicion = 14) THEN
                  v_ind_firm_repr_lega.extend;
                  v_ind_firm_repr_lega(i) := TRIM(translate(substr(lslinea,
                                                                   inicio,
                                                                   longitud),
                                                            lscadena,
                                                            lsreplace));
                END IF;
              
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    DELETE FROM int_ocr_carta_flexi;
  
    -- Bulk bind of data in memory table...
    FORALL i IN 1 .. v_cod_pais.count
    
      INSERT INTO int_ocr_carta_flexi
        (num_lote,
         sec_nume_docu,
         cod_pais,
         cod_comp,
         num_docu,
         cod_tipo_docu_iden,
         num_docu_iden,
         cod_clie,
         fec_proc,
         cod_esta_ocr,
         mot_rech_ocr,
         cod_regi,
         cod_zona,
         ind_firm_cons,
         ind_firm_repr_lega,
         cam_regi)
      VALUES
        (psnumerolote,
         v_sec_nume_docu(i),
         v_cod_pais(i),
         v_cod_comp(i),
         v_num_docu(i),
         v_cod_tipo_docu_iden(i),
         v_num_docu_iden(i),
         v_cod_clie(i),
         v_fec_proc(i),
         v_cod_esta_ocr(i),
         v_mot_rech_ocr(i),
         v_cod_regi(i),
         v_cod_zona(i),
         v_ind_firm_cons(i),
         v_ind_firm_repr_lega(i),
         v_cam_regi(i));
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR INT_PR_OCR_RECEP_CARTA_FLEXI: ' ||
                              ls_sqlerrm);
    
  END int_pr_ocr_recep_carta_flexi;

  /***************************************************************************
  Descripcion       : Consolida las Cartas de Invitación Flexipago
  Fecha Creacion    : 10/05/2013
  Autor             : Sebastian Guerra
  ***************************************************************************/
  PROCEDURE ocr_pr_conso_carta_flexi
  (
    pscodigopais          VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumlote             VARCHAR2,
    pscodigotipodocumento VARCHAR2
  ) IS
  
    CURSOR curinsconsolcartaflexi IS
      SELECT psnumlote num_lote,
             seq_docu_sto.nextval sec_nume_docu,
             cod_pais,
             cod_comp,
             num_docu,
             cod_tipo_docu_iden,
             num_docu_iden,
             cod_clie,
             trunc(SYSDATE) fec_proc,
             cod_esta_ocr,
             mot_rech_ocr,
             cod_regi,
             cod_zona,
             ind_firm_cons,
             ind_firm_repr_lega,
             cam_regi
        FROM int_ocr_carta_flexi
       WHERE cod_pais = pscodigopais;
  
    TYPE solic_carta_flexi_tab_t IS TABLE OF int_solic_conso_carta_flexi%ROWTYPE INDEX BY BINARY_INTEGER;
    sol_carta_flexi_tab solic_carta_flexi_tab_t; -- In-memory table
  
    j BINARY_INTEGER := 0;
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t; -- In-memory table
  BEGIN
  
    OPEN curinsconsolcartaflexi;
    LOOP
      -- Bulk collect data into memory table - X rows at a time
      FETCH curinsconsolcartaflexi BULK COLLECT
        INTO sol_carta_flexi_tab LIMIT w_filas;
      EXIT WHEN sol_carta_flexi_tab.count = 0;
    
      FOR j IN sol_carta_flexi_tab.first .. sol_carta_flexi_tab.last
      LOOP
        sto_tab(j).cod_pais := sol_carta_flexi_tab(j).cod_pais;
        sto_tab(j).cod_tipo_docu := pscodigotipodocumento;
        sto_tab(j).num_lote := sol_carta_flexi_tab(j).num_lote;
        sto_tab(j).sec_nume_docu := sol_carta_flexi_tab(j).sec_nume_docu;
        sto_tab(j).num_docu := sol_carta_flexi_tab(j).num_docu;
        sto_tab(j).ind_envi := '0';
        sto_tab(j).ind_rech := '0';
        sto_tab(j).fec_digi := SYSDATE;
        sto_tab(j).usu_digi := pscodigousuario;
        sto_tab(j).fec_modi := SYSDATE;
        sto_tab(j).usu_modi := pscodigousuario;
        sto_tab(j).cod_zona := sol_carta_flexi_tab(j).cod_zona;
        sto_tab(j).cod_clie := sol_carta_flexi_tab(j).cod_clie;
        sto_tab(j).cod_regi := sol_carta_flexi_tab(j).cod_regi;
        sto_tab(j).cod_peri := sol_carta_flexi_tab(j).cam_regi;
        sto_tab(j).ind_rece_ocr := '0';
        sto_tab(j).ind_rece_web := '0';
        sto_tab(j).ind_rece_dd := '0';
        sto_tab(j).ind_rece_digi := '0';
        sto_tab(j).ind_rece_cc := '0';
        sto_tab(j).ind_rece_mens := '0';
        sto_tab(j).ind_elim := '0';
        sto_tab(j).ind_rece_onli := '0';
        sto_tab(j).ind_rece_ivr := '0';
      END LOOP;
    
      FORALL i IN sol_carta_flexi_tab.first .. sol_carta_flexi_tab.last
        INSERT INTO int_solic_conso_carta_flexi
        VALUES sol_carta_flexi_tab
          (i);
    
      FORALL j IN sol_carta_flexi_tab.first .. sol_carta_flexi_tab.last
        INSERT INTO sto_docum_digit VALUES sto_tab (j);
    
    END LOOP;
    CLOSE curinsconsolcartaflexi;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_PR_CONSO_CARTA_FLEXI: ' ||
                              ls_sqlerrm);
  END ocr_pr_conso_carta_flexi;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Boletas de Entrega
  Fecha Creacion    : 18/06/2013
  Autor             : Ivan Tocto Jaimes
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_bolet_entre
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_orden_trans.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_orden_trans.cod_comp%TYPE;
    TYPE t_tip_orde IS TABLE OF int_ocr_orden_trans.tip_orde%TYPE;
    TYPE t_num_docu IS TABLE OF int_ocr_orden_trans.num_docu%TYPE;
    TYPE t_cod_comp_tran IS TABLE OF int_ocr_orden_trans.cod_comp_tran%TYPE;
    TYPE t_cod_cent_acop IS TABLE OF int_ocr_orden_trans.cod_cent_acop%TYPE;
    TYPE t_cod_esta_entr IS TABLE OF int_ocr_orden_trans.cod_esta_entr%TYPE;
    TYPE t_cod_nove IS TABLE OF int_ocr_orden_trans.cod_nove%TYPE;
    TYPE t_ind_caja_comp IS TABLE OF int_ocr_orden_trans.ind_caja_comp%TYPE;
    TYPE t_ind_fuer_caja IS TABLE OF int_ocr_orden_trans.ind_fuer_caja%TYPE;
    TYPE t_fec_fact IS TABLE OF int_ocr_orden_trans.fec_fact%TYPE;
    TYPE t_cod_esta_ent2 IS TABLE OF int_ocr_orden_trans.cod_esta_ent2%TYPE;
    TYPE t_cod_moti_rech IS TABLE OF int_ocr_orden_trans.cod_moti_rech%TYPE;
    TYPE t_hor_fact IS TABLE OF int_ocr_orden_trans.hor_fact%TYPE;
    TYPE t_fec_ocr IS TABLE OF int_ocr_orden_trans.fec_ocr%TYPE;
    TYPE t_val_lati IS TABLE OF int_ocr_orden_trans.val_lati%TYPE;
    TYPE t_val_long IS TABLE OF int_ocr_orden_trans.val_long%TYPE;
  
    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_comp      t_cod_comp := t_cod_comp();
    v_tip_orde      t_tip_orde := t_tip_orde();
    v_num_docu      t_num_docu := t_num_docu();
    v_cod_comp_tran t_cod_comp_tran := t_cod_comp_tran();
    v_cod_cent_acop t_cod_cent_acop := t_cod_cent_acop();
    v_cod_esta_entr t_cod_esta_entr := t_cod_esta_entr();
    v_cod_nove      t_cod_nove := t_cod_nove();
    v_ind_caja_comp t_ind_caja_comp := t_ind_caja_comp();
    v_ind_fuer_caja t_ind_fuer_caja := t_ind_fuer_caja();
    v_fec_fact      t_fec_fact := t_fec_fact();
    v_cod_esta_ent2 t_cod_esta_ent2 := t_cod_esta_ent2();
    v_cod_moti_rech t_cod_moti_rech := t_cod_moti_rech();
    v_hor_fact      t_hor_fact := t_hor_fact();
    v_fec_ocr       t_fec_ocr := t_fec_ocr();
    v_val_lati      t_val_lati := t_val_lati();
    v_val_long       t_val_long := t_val_long();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
    inicio   NUMBER := 0;
  
    lsfechaprocesoactual VARCHAR2(8);
    ldfechaproceso       DATE;
  
  BEGIN
  
    EXECUTE IMMEDIATE 'TRUNCATE TABLE int_ocr_orden_trans';
  
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
    
      BEGIN
        SELECT to_char(c.fec_proc, 'yyyymmdd')
          INTO lsfechaprocesoactual
          FROM bas_ctrl_fact c
         WHERE c.cod_pais = pscodigopais
           AND c.sta_camp = '0'
           AND c.ind_camp_act = '1';
      EXCEPTION
        WHEN no_data_found THEN
          lsfechaprocesoactual := to_char(SYSDATE, 'yyyymmdd');
      END;
    
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
        
          i      := i + 1;
          inicio := 1;
        
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := TRIM(substr(lslinea, inicio, longitud));
                  v_cod_pais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_cod_pais(i),
                                                                               v_cod_comp(i));
                ELSIF (posicion = 3) THEN
                  v_tip_orde.extend;
                  v_tip_orde(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_num_docu.extend;
                  v_num_docu(i) := TRIM(substr(lslinea, inicio, longitud));
                
                ELSIF (posicion = 5) THEN
                  v_cod_comp_tran.extend;
                  v_cod_comp_tran(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 6) THEN
                  v_cod_cent_acop.extend;
                  v_cod_cent_acop(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 7) THEN
                  v_cod_esta_entr.extend;
                  v_cod_esta_entr(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 8) THEN
                  v_cod_nove.extend;
                  v_cod_nove(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 9) THEN
                  v_ind_caja_comp.extend;
                  v_ind_caja_comp(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 10) THEN
                  v_ind_fuer_caja.extend;
                  v_ind_fuer_caja(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 11) THEN
                
                  BEGIN
                    ldfechaproceso := to_date(substr(lslinea,
                                                     inicio,
                                                     longitud),
                                              'YYYYMMDD');
                  EXCEPTION
                    WHEN OTHERS THEN
                      ldfechaproceso := to_date(lsfechaprocesoactual,
                                                'YYYYMMDD');
                  END;
                
                  v_fec_fact.extend;
                  v_fec_fact(i) := ldfechaproceso;
                
                  v_fec_ocr.extend;
                  v_fec_ocr(i) := substr(lslinea, inicio, longitud);
                
                ELSIF (posicion = 12) THEN
                  v_cod_esta_ent2.extend;
                  v_cod_esta_ent2(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 13) THEN
                  v_cod_moti_rech.extend;
                  v_cod_moti_rech(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                
                ELSIF (posicion = 14) THEN
                  v_hor_fact.extend;
                  v_hor_fact(i) := TRIM(substr(lslinea, inicio, longitud));
                  
                ELSIF (posicion = 15) THEN
                  v_val_lati.extend;
                  v_val_lati(i) := TRIM(substr(lslinea, inicio, longitud));
                  
                ELSIF (posicion = 16) THEN
                  v_val_long.extend;
                  v_val_long(i) := TRIM(substr(lslinea, inicio, longitud));
                
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    --- Actualiza la informacion y se queda con el ultimo registro
    FOR i IN 1 .. v_cod_pais.count
    LOOP
      BEGIN
      
        INSERT INTO int_ocr_orden_trans
          (cod_pais,
           cod_comp,
           num_docu,
           cod_tipo_docu,
           cod_comp_tran,
           cod_cent_acop,
           cod_esta_entr,
           cod_nove,
           cod_moti_rech,
           sta_proc,
           ---cod_esta_ent2,
           tip_orde,
           fec_proc,
           fec_fact,
           fec_ocr,
           ind_caja_comp,
           ind_fuer_caja,
           hor_fact,
           val_lati,
           val_long)
        VALUES
          (v_cod_pais(i),
           v_cod_comp(i),
           v_num_docu(i),
           'OT',
           v_cod_comp_tran(i),
           v_cod_cent_acop(i),
           v_cod_esta_entr(i),
           v_cod_nove(i),
           v_cod_moti_rech(i),
           v_cod_esta_ent2(i),
           v_tip_orde(i),
           v_fec_fact(i),
           v_fec_fact(i),
           v_fec_ocr(i),
           v_ind_caja_comp(i),
           v_ind_fuer_caja(i),
           v_hor_fact(i), --to_char(SYSDATE,'HH24MI'));
           v_val_lati(i),
           v_val_long(i)); 
      
      EXCEPTION
        WHEN dup_val_on_index THEN
        
          UPDATE int_ocr_orden_trans
             SET cod_pais = v_cod_pais(i),
                 cod_comp = v_cod_comp(i),
                 ---num_docu = v_num_docu(i),
                 cod_tipo_docu = 'OT',
                 cod_comp_tran = v_cod_comp_tran(i),
                 cod_cent_acop = v_cod_cent_acop(i),
                 cod_esta_entr = v_cod_esta_entr(i),
                 cod_nove      = v_cod_nove(i),
                 cod_moti_rech = v_cod_moti_rech(i),
                 sta_proc      = v_cod_esta_ent2(i),
                 tip_orde      = v_tip_orde(i),
                 fec_proc      = v_fec_fact(i),
                 fec_fact      = v_fec_fact(i),
                 fec_ocr       = v_fec_ocr(i),
                 ind_caja_comp = v_ind_caja_comp(i),
                 ind_fuer_caja = v_ind_fuer_caja(i),
                 hor_fact      = v_hor_fact(i), --to_char(SYSDATE,'HH24MI')
                 val_lati      = v_val_lati(i),
                 val_long      = v_val_long(i)
           WHERE num_docu = v_num_docu(i);
        
      END;
    END LOOP;
  
    -- Bulk bind of data in memory table...
    /*FORALL i IN 1 .. v_cod_pais.count
    INSERT INTO int_ocr_orden_trans
      (cod_pais,
       cod_comp,
       num_docu,
       cod_tipo_docu,
       cod_comp_tran,
       cod_cent_acop,
       cod_esta_entr,
       cod_nove,
       cod_moti_rech,
       sta_proc,
       ----cod_esta_ent2,
       tip_orde,
       fec_proc,
       fec_fact,
       ind_caja_comp,
       ind_fuer_caja)
    values
      (v_cod_pais(i),
       v_cod_comp(i),
       v_num_docu(i),
       'OT',
       v_cod_comp_tran(i),
       v_cod_cent_acop(i),
       v_cod_esta_entr(i),
       v_cod_nove(i),
       v_cod_moti_rech(i),
       v_cod_esta_ent2(i),
       v_tip_orde(i),
       v_fec_fact(i),
       v_fec_fact(i),
       v_ind_caja_comp(i),
      v_ind_fuer_caja(i));*/
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_recep_bolet_entre: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_bolet_entre;

  /***************************************************************************
  Descripcion       : Genera Interfaz de Recepcion de Archivo de Boletas de Recojo
  Fecha Creacion    : 18/06/2013
  Autor             : Ivan Tocto Jaimes
  ***************************************************************************/
  PROCEDURE int_pr_ocr_recep_bolet_recoj
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psnombrearchivo  VARCHAR2
  ) IS
    CURSOR c_interfaz IS
      SELECT a.pos_camp,
             a.lon_camp,
             a.can_deci,
             a.ide_camp,
             t.sig_tdat
        FROM bas_estru_archi a,
             bas_tipo_dato   t
       WHERE a.tdat_cod_tdat = t.cod_tdat
         AND a.est_esar != 9
         AND a.pais_cod_pais = pscodigopais
         AND a.sist_cod_sist = pscodigosistema
         AND a.inte_cod_inte = pscodigointerfaz
       ORDER BY a.pos_camp ASC;
  
    TYPE interfazcab IS RECORD(
      posiccampo  bas_estru_archi.pos_camp%TYPE,
      longcampo   bas_estru_archi.lon_camp%TYPE,
      cantdecimal bas_estru_archi.can_deci%TYPE,
      idcampo     bas_estru_archi.ide_camp%TYPE,
      sigla       bas_tipo_dato.sig_tdat%TYPE);
  
    TYPE interfazcabtab IS TABLE OF interfazcab;
  
    interfazrecord interfazcabtab;
  
    TYPE t_cod_pais IS TABLE OF int_ocr_orden_trans.cod_pais%TYPE;
    TYPE t_cod_comp IS TABLE OF int_ocr_orden_trans.cod_comp%TYPE;
    TYPE t_tip_orde IS TABLE OF int_ocr_orden_trans.tip_orde%TYPE;
    TYPE t_num_docu IS TABLE OF int_ocr_orden_trans.num_docu%TYPE;
    TYPE t_cod_comp_tran IS TABLE OF int_ocr_orden_trans.cod_comp_tran%TYPE;
    TYPE t_cod_cent_acop IS TABLE OF int_ocr_orden_trans.cod_cent_acop%TYPE;
    TYPE t_cod_esta_entr IS TABLE OF int_ocr_orden_trans.cod_esta_entr%TYPE;
    TYPE t_cod_nove IS TABLE OF int_ocr_orden_trans.cod_nove%TYPE;
    TYPE t_ind_caja_comp IS TABLE OF int_ocr_orden_trans.ind_caja_comp%TYPE;
    TYPE t_ind_fuer_caja IS TABLE OF int_ocr_orden_trans.ind_fuer_caja%TYPE;
    TYPE t_fec_fact IS TABLE OF int_ocr_orden_trans.fec_fact%TYPE;
    TYPE t_cod_esta_ent2 IS TABLE OF int_ocr_orden_trans.cod_esta_ent2%TYPE;
    TYPE t_cod_moti_rech IS TABLE OF int_ocr_orden_trans.cod_moti_rech%TYPE;
    TYPE t_fec_ocr IS TABLE OF int_ocr_orden_trans.fec_ocr%TYPE;
  
    v_cod_pais      t_cod_pais := t_cod_pais();
    v_cod_comp      t_cod_comp := t_cod_comp();
    v_tip_orde      t_tip_orde := t_tip_orde();
    v_num_docu      t_num_docu := t_num_docu();
    v_cod_comp_tran t_cod_comp_tran := t_cod_comp_tran();
    v_cod_cent_acop t_cod_cent_acop := t_cod_cent_acop();
    v_cod_esta_entr t_cod_esta_entr := t_cod_esta_entr();
    v_cod_nove      t_cod_nove := t_cod_nove();
    v_ind_caja_comp t_ind_caja_comp := t_ind_caja_comp();
    v_ind_fuer_caja t_ind_fuer_caja := t_ind_fuer_caja();
    v_fec_fact      t_fec_fact := t_fec_fact();
    v_cod_esta_ent2 t_cod_esta_ent2 := t_cod_esta_ent2();
    v_cod_moti_rech t_cod_moti_rech := t_cod_moti_rech();
    v_fec_ocr       t_fec_ocr := t_fec_ocr();
  
    /* Variables usadas para la generacion del archivo de texto */
    lsdirtempo bas_inter.dir_temp%TYPE;
    v_handle   utl_file.file_type;
  
    lslinea VARCHAR2(1000);
  
    lsnombrearchivo VARCHAR2(50);
  
    /* Variables de parametros */
  
    i        BINARY_INTEGER := 0;
    posicion NUMBER := 0;
    longitud NUMBER := 0;
    inicio   NUMBER := 0;
  
    lsfechaprocesoactual VARCHAR2(8);
    ldfechaproceso       DATE;
  
  BEGIN
  
    EXECUTE IMMEDIATE 'TRUNCATE TABLE int_ocr_orden_trans';
  
    /* Procedimiento inicial para generar interfaz */
    gen_pkg_inter_archi.gen_pr_inici_inter_lectu(pscodigopais,
                                                 pscodigosistema,
                                                 pscodigointerfaz,
                                                 psnombrearchivo,
                                                 'TXT',
                                                 lsdirtempo,
                                                 lsnombrearchivo,
                                                 v_handle);
  
    IF utl_file.is_open(v_handle) THEN
    
      BEGIN
        SELECT to_char(c.fec_proc, 'yyyymmdd')
          INTO lsfechaprocesoactual
          FROM bas_ctrl_fact c
         WHERE c.cod_pais = pscodigopais
           AND c.sta_camp = '0'
           AND c.ind_camp_act = '1';
      EXCEPTION
        WHEN no_data_found THEN
          lsfechaprocesoactual := to_char(SYSDATE, 'yyyymmdd');
      END;
    
      LOOP
        BEGIN
          utl_file.get_line(v_handle, lslinea);
        
          i      := i + 1;
          inicio := 1;
        
          IF lslinea IS NULL THEN
            EXIT;
          END IF;
        
          OPEN c_interfaz;
          LOOP
            FETCH c_interfaz BULK COLLECT
              INTO interfazrecord LIMIT w_filas;
            IF interfazrecord.count > 0 THEN
              FOR x IN interfazrecord.first .. interfazrecord.last
              LOOP
              
                posicion := interfazrecord(x).posiccampo;
                longitud := interfazrecord(x).longcampo;
              
                IF (posicion = 1) THEN
                  v_cod_pais.extend;
                  v_cod_pais(i) := nvl(TRIM(substr(lslinea,
                                                   inicio,
                                                   longitud)),
                                       pscodigopais);
                
                ELSIF (posicion = 2) THEN
                  v_cod_comp.extend;
                  v_cod_comp(i) := TRIM(substr(lslinea, inicio, longitud));
                  v_cod_pais(i) := sto_pkg_gener.sto_fn_devue_codig_pais_bycia(v_cod_pais(i),
                                                                               v_cod_comp(i));
                ELSIF (posicion = 3) THEN
                  v_tip_orde.extend;
                  v_tip_orde(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 4) THEN
                  v_num_docu.extend;
                  v_num_docu(i) := TRIM(substr(lslinea, inicio, longitud));
                
                ELSIF (posicion = 5) THEN
                  v_cod_comp_tran.extend;
                  v_cod_comp_tran(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 6) THEN
                  v_cod_cent_acop.extend;
                  v_cod_cent_acop(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 7) THEN
                  v_cod_esta_entr.extend;
                  v_cod_esta_entr(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 8) THEN
                  v_cod_nove.extend;
                  v_cod_nove(i) := TRIM(substr(lslinea, inicio, longitud));
                ELSIF (posicion = 9) THEN
                  v_ind_caja_comp.extend;
                  v_ind_caja_comp(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 10) THEN
                  v_ind_fuer_caja.extend;
                  v_ind_fuer_caja(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 11) THEN
                  BEGIN
                    ldfechaproceso := to_date(substr(lslinea,
                                                     inicio,
                                                     longitud),
                                              'YYYYMMDD');
                  EXCEPTION
                    WHEN OTHERS THEN
                      ldfechaproceso := to_date(lsfechaprocesoactual,
                                                'YYYYMMDD');
                  END;
                
                  v_fec_fact.extend;
                  v_fec_fact(i) := ldfechaproceso;
                
                  v_fec_ocr.extend;
                  v_fec_ocr(i) := substr(lslinea, inicio, longitud);
                
                ELSIF (posicion = 12) THEN
                  v_cod_esta_ent2.extend;
                  v_cod_esta_ent2(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                ELSIF (posicion = 13) THEN
                  v_cod_moti_rech.extend;
                  v_cod_moti_rech(i) := TRIM(substr(lslinea,
                                                    inicio,
                                                    longitud));
                END IF;
                inicio := inicio + longitud;
              
              END LOOP;
            END IF;
            EXIT WHEN c_interfaz%NOTFOUND;
          END LOOP;
          CLOSE c_interfaz;
        
        EXCEPTION
          WHEN no_data_found THEN
            EXIT;
        END;
      END LOOP;
    END IF;
  
    utl_file.fclose(v_handle);
  
    --- Actualiza la informacion y se queda con el ultimo registro
    FOR i IN 1 .. v_cod_pais.count
    LOOP
      BEGIN
      
        INSERT INTO int_ocr_orden_trans
          (cod_pais,
           cod_comp,
           num_docu,
           cod_tipo_docu,
           cod_comp_tran,
           cod_cent_acop,
           cod_esta_reco,
           cod_nove,
           cod_moti_rech,
           sta_proc,
           ---cod_esta_ent2,
           tip_orde,
           fec_proc,
           fec_fact,
           fec_ocr,
           ind_caja_comp,
           ind_fuer_caja)
        VALUES
          (v_cod_pais(i),
           v_cod_comp(i),
           v_num_docu(i),
           'OT',
           v_cod_comp_tran(i),
           v_cod_cent_acop(i),
           v_cod_esta_entr(i),
           v_cod_nove(i),
           v_cod_moti_rech(i),
           v_cod_esta_ent2(i),
           v_tip_orde(i),
           v_fec_fact(i),
           v_fec_fact(i),
           v_fec_ocr(i),
           v_ind_caja_comp(i),
           v_ind_fuer_caja(i));
      
      EXCEPTION
        WHEN dup_val_on_index THEN
        
          UPDATE int_ocr_orden_trans
             SET cod_pais = v_cod_pais(i),
                 cod_comp = v_cod_comp(i),
                 ---num_docu = v_num_docu(i),
                 cod_tipo_docu = 'OT',
                 cod_comp_tran = v_cod_comp_tran(i),
                 cod_cent_acop = v_cod_cent_acop(i),
                 cod_esta_reco = v_cod_esta_entr(i),
                 cod_nove      = v_cod_nove(i),
                 cod_moti_rech = v_cod_moti_rech(i),
                 sta_proc      = v_cod_esta_ent2(i),
                 tip_orde      = v_tip_orde(i),
                 fec_proc      = v_fec_fact(i),
                 fec_fact      = v_fec_fact(i),
                 ind_caja_comp = v_ind_caja_comp(i),
                 ind_fuer_caja = v_ind_fuer_caja(i)
           WHERE num_docu = v_num_docu(i);
        
      END;
    END LOOP;
  
    -- Bulk bind of data in memory table...
    /*FORALL i IN 1 .. v_cod_pais.count
    INSERT INTO int_ocr_orden_trans
      (cod_pais,
       cod_comp,
       num_docu,
       cod_tipo_docu,
       cod_comp_tran,
       cod_cent_acop,
       cod_esta_reco,
       cod_nove,
       cod_moti_rech,
       sta_proc,
       ---cod_esta_ent2,
       tip_orde,
       fec_proc,
       fec_fact,
       ind_caja_comp,
       ind_fuer_caja)
    values
      (v_cod_pais(i),
       v_cod_comp(i),
       v_num_docu(i),
       'OT',
       v_cod_comp_tran(i),
       v_cod_cent_acop(i),
       v_cod_esta_entr(i),
       v_cod_nove(i),
       v_cod_moti_rech(i),
       v_cod_esta_ent2(i),
       v_tip_orde(i),
       v_fec_fact(i),
       v_fec_fact(i),
       v_ind_caja_comp(i),
      v_ind_fuer_caja(i)); */
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR int_pr_ocr_recep_bolet_recoj: ' ||
                              psnombrearchivo || '**' || ls_sqlerrm);
    
  END int_pr_ocr_recep_bolet_recoj;

END int_pkg_occrr;
/
