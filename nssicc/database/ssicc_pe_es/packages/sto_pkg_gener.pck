CREATE OR REPLACE PACKAGE sto_pkg_gener AS

  TYPE tregisbenefdeuda IS RECORD(
    oid_clie_bene_deud         NUMBER(12),
    codigo_periodo             VARCHAR(6),
    tipo_cliente               VARCHAR2(100),
    subtipo_cliente            VARCHAR2(100),
    tipo_clasificacion_cliente VARCHAR2(100),
    clasificacion              VARCHAR2(100),
    codigo_cliente             VARCHAR2(15),
    desc_region                VARCHAR2(100),
    desc_zona                  VARCHAR2(100),
    tip_bene                   NUMBER(1),
    oid_peri                   NUMBER(12),
    oid_tipo_clie              NUMBER(12),
    oid_subt_clie              NUMBER(12),
    oid_tipo_clas_clie         NUMBER(12),
    oid_clas_clie              NUMBER(12),
    clie_oid_clie              NUMBER(12),
    oid_regi                   NUMBER(12),
    oid_zona                   NUMBER(12),
    val_mont                   NUMBER(8, 2),
    val_porc                   NUMBER(3));

  TYPE tabla_beneficio_deuda IS TABLE OF tregisbenefdeuda;

  TYPE tregisfactuadicional IS RECORD(
    oid_fact_adic      NUMBER(12),
    codigo_periodo     VARCHAR(6),
    tipo_cliente       VARCHAR2(100),
    subtipo_cliente    VARCHAR2(100),
    tipo_clasificacion VARCHAR2(100),
    clasificacion      VARCHAR2(100),
    codigo_cliente     VARCHAR2(15),
    desc_region        VARCHAR2(100),
    desc_zona          VARCHAR2(100),
    oid_peri           NUMBER(12),
    oid_tipo_clie      NUMBER(12),
    oid_subt_clie      NUMBER(12),
    oid_tipo_clas      NUMBER(12),
    oid_clas           NUMBER(12),
    oid_clie           NUMBER(12),
    oid_regi           NUMBER(12),
    oid_zona           NUMBER(12),
    ind_vali_prom      VARCHAR2(1),
    ind_vali_mtmi      VARCHAR2(1));

  TYPE tabla_facturacion_adicional IS TABLE OF tregisfactuadicional;

  TYPE tformapagoclasificacion IS RECORD(
    OID                NUMBER(10),
    codigo_periodo     VARCHAR(6),
    tipo_cliente       VARCHAR2(100),
    subtipo_cliente    VARCHAR2(100),
    tipo_clasificacion VARCHAR2(100),
    clasificacion      VARCHAR2(100),
    forma_pago         VARCHAR2(100),
    desc_region        VARCHAR2(100),
    desc_zona          VARCHAR2(100),
    codigo_cuv         VARCHAR2(100),
    oid_deta_ofer      NUMBER(10),
    fopa_oid_form_pago NUMBER(10),
    oid_tipo_clie      NUMBER(12),
    oid_subt_clie      NUMBER(12),
    oid_tipo_clas      NUMBER(12),
    oid_clas           NUMBER(12),
    oid_regi           NUMBER(12),
    oid_zona           NUMBER(12));
  TYPE tabla_forma_pago_clasificacion IS TABLE OF tformapagoclasificacion;
  /* Declaracion de variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1500);
  w_filas    NUMBER := 1000;
  /**************************************************************************
  Descripcion        : Devuelve Numero de Proceso a Usar
  Fecha Creacion     : 21/06/2012
  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_nproce
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumeroproceso       OUT VARCHAR2
  );
  /**************************************************************************
  Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                       separndo n Numeros para inserciones.
  Fecha Creacion     : 27/11/2007
  
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_nproce
  (
    pscodigopais  VARCHAR2,
    pscodtipodocu VARCHAR2,
    psusuario     VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Devuelve el valor del parametro de la tabla
               STO_PARAM_GENER_OCCRR
   Fecha Creacion    : 26/05/2008
  Parametros Entrada:
      PS_COD_PAIS   : Codigo de pais
      PS_COD_PARAM   : Codigo de parametro
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_obten_param_ocr
  (
    pscodigopais      VARCHAR2,
    pscodigoparametro VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                       separndo n Numeros para inserciones.
  Fecha Creacion     : 05/01/2007
  
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_secue_nsoli
  (
    pscodigopais      VARCHAR2,
    pscodigooperacion VARCHAR2,
    pscodigosubacceso VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                       separndo n Numeros para inserciones.
  Fecha Creacion     : 27/11/2007
  
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_resrv_secue_nsoli
  (
    pscodigopais      VARCHAR2,
    pscodigooperacion VARCHAR2,
    pscodigosubacceso VARCHAR2,
    pnnumreservados   NUMBER
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                       separndo n Numeros para inserciones.
  Fecha Creacion     : 27/11/2007
  
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_resrv_secue_nsoli
  (
    pscodigopais      VARCHAR2,
    pscodigooperacion VARCHAR2,
    pscodigosubacceso VARCHAR2,
    pnnumreservados   NUMBER,
    psanio            VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Devuelve el codigo de Documento Hijo de un Documento
                      Cabecera
  Fecha Creacion    : 26/05/2008
  Parametros Entrada:
      psCodigoPais   : Codigo de pais
      psCodigoParametro   : Codigo de parametro
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_codig_docum_detal
  (
    pscodigopais     VARCHAR2,
    pscodigocabecera VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Devuelve el indicador de rechazo automatico de
  Fecha Creacion    : 06/06/2008
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_indic_recha_autom
  (
    pscodigopais       VARCHAR2,
    pscodigotipodoc    VARCHAR2,
    pscodigovalidacion VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Devuelve el codigo de la Ultima Validacion que debe ser
                       ejecutada para un documento
  Fecha Creacion     : 09/06/2008
  
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_ultim_valid_ejecu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion        : Devuelve el modulo once de la cadena ingresda
  Fecha Creacion     : 17/06/2008
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_modul_once
  (
    pscodtemp VARCHAR2,
    pscodpais VARCHAR2
  ) RETURN VARCHAR2;
  /***************************************************************************
  Descripcion       : Actualiza los Registros para ser Validados
  Fecha Creacion    : 01/02/2012
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_updat_docum_proce
  (
    pscodigopais     VARCHAR2,
    pscodigotipodocu VARCHAR2,
    psnumeroproceso  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Actualiza los Registros para ser Validados
  Fecha Creacion    : 22/10/2009
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_inici_valid_nomas
  (
    pscodigopais     VARCHAR2,
    pscodigotipodocu VARCHAR2,
    psnumeroproceso  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Inicializa los valores de los Docuemnetos procesados por
                      STO
  Fecha Creacion    : 09/03/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_inici_valid_masiv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Marca los registros para ser eliminados
  Fecha Creacion    : 26/02/2013
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_updat_delet
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumlote             VARCHAR2,
    pssecnumedocu         VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Marca los registros para ser eliminados
  Fecha Creacion    : 26/02/2013
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_updat_delet_onlin
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumlote             VARCHAR2,
    pssecnumedocu         VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Borra la excepciones de un proceso
  Fecha Creacion    : 11/02/2013
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_delet_excep
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Avtualiza el valor de el ultimo numero de solicitud
  Fecha Creacion    : 23/06/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_updat_numer_solic(pscodigopais VARCHAR2
                                     
                                     );
  /**************************************************************************
  Descripcion        : Devuelve el codigo de cliente generado
  Fecha Creacion     : 23/06/2008
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_codig_clien(pscodpais VARCHAR2) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Registra el inicio de Validacion
  Fecha Creacion    : 09/03/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_updat_histo_valid
  (
    pscodigopais       VARCHAR2,
    pstipodocumento    VARCHAR2,
    pscodigovalidacion VARCHAR2,
    psnumeroproceso    VARCHAR2,
    psindicadorerror   VARCHAR2,
    pslogerror         VARCHAR2
    
  );

  /***************************************************************************
  Descripcion       : Actualiza como rechazado los detalles que no fueron enviados
                      por uqe su cabecera ya fue enviada.
  Fecha Creacion    : 21/07/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_regis_detal_sncab
  (
    pscodigopais            VARCHAR2,
    pstipodocumentocabecera VARCHAR2,
    pstipodocumentodetalle  VARCHAR2,
    psnumeroproceso         VARCHAR2
  );
  /**************************************************************************
  Descripcion       : Devuel el codigo de Tipo de documento apartir del codigo de Interfaz
  Fecha Creacion    : 26/05/2008
  Parametros Entrada:
      psCodigoPais   : Codigo de pais
      psCodigoSistema   : Codigo de parametro
      psCodigoInterfaz   : Codigo de parametro
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_tipo_docum_byint
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psindcabecera    VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Devuelve el Codigo de Mensaje
   Fecha Creacion    : 13/08/2008
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_codig_mensa
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigovalidacion    VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Devuelve la desccripcion del Mensaje
   Fecha Creacion    : 12/09/2012
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_devue_valor_mensa
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigovalidacion    VARCHAR2,
    pstipomensaje         VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion       : Devuelve el Codigo de Pais por Compañia y Pais OCR
    Fecha Creacion    : 13/08/2008
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_codig_pais_bycia
  (
    pscodigopaisocr VARCHAR2,
    pscodigocia     VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla DOCUM_DIGIT los
                      documentos a validar q cumplan los filtros APROB-LEVAN
                      return 1 y actulizamos en la tabla STO_DETAL_DOCUM_EXCEP
  
  
  Fecha Creacion    : 27/08/2008
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_aprob
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    psnumeroproceso       VARCHAR2,
    psusuario             VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla STO_TIPO_DOCUM_DIGIT EL NUMERO DE LOTE
            SEGUN EL TIPO DE DOCUMENTO
  
  Fecha Creacion    : 01/09/2008
  Parametros Entrada: psCodigoPais          Código Pais
                      psDocuCodTipoDocu     Codigo Tipo Documento
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_nume_lote
  (
    pscodigopais VARCHAR2,
    pstipodocu   VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion       : Obteniene el numero de lote del documento
  Fecha Creacion    : 20/10/2011
  Parametros Entrada:
                      psCodigoPais   Código Pais
                      pstipodocu     Codigo Tipo Documento
                      psnumlote      Lote
  Autor             : Jose A. Cairampoma G.
  ***************************************************************************/
  PROCEDURE sto_pr_devue_lote
  (
    pscodigopais VARCHAR2,
    pstipodocu   VARCHAR2,
    psnumlote    OUT VARCHAR2
  );
  /**************************************************************************
  Descripcion       : Actualiza el numero de lote sumandole 1
  
  Fecha Creacion    : 01/09/2008
  Parametros Entrada:
  
                      psCodigoPais          Código Pais
                      psDocuCodTipoDocu     Codigo Tipo Documento
  
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_updat_nume_lote
  (
    pscodigopais VARCHAR2,
    pstipodocu   VARCHAR2
  );
  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla STO_TIPO_DOCUM_DIGIT EL codigo del
            documento
  
  
  Fecha Creacion    : 01/09/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      pscodinterfaz     Codigo de interfaz
  
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_codi_docu
  (
    pscodigopais  VARCHAR2,
    pscodinterfaz VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla int_solic_conso_poven_detal EL codigo de
            operacion
  
  
  Fecha Creacion    : 12/10/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      psnunlote         numero de lote
            psnumsecuencia    numero de secuencia
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_codi_oper
  (
    pscodigopais   VARCHAR2,
    psnunlote      VARCHAR2,
    psnumsecuencia NUMBER
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla int_solic_conso_poven_detal el producto
              de VAL_PREC_CATA_DEVU y CAN_VENT_DEVU
  
  
  Fecha Creacion    : 22/09/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      psperiodo         periodo
            pscliente       cliente
            psnumsecuencia    numero de secuencia
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_produ_canti
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    pscliente       VARCHAR2,
    psnumdocu       NUMBER,
    pstipodocumento VARCHAR2
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla int_solic_conso_poven_detal la suma de  CAN_VENT_DEVU
  
  
  Fecha Creacion    : 22/09/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      psperiodo         periodo
            pscliente       cliente
            psnumsecuencia    numero de secuencia
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_venta_devue
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    pscliente       VARCHAR2,
    psnumsecuencia  NUMBER,
    pstipodocumento VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Inidica so exite el codigo de venta
  
  Fecha Creacion    : 26/09/2008
  Parametros Entrada:
  
  
                       pscodigoventa
             psoidperiodoreferencia
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_indic_exist_coven(pscodigoventa VARCHAR2,
                                          
                                          psoidperiodoreferencia NUMBER
                                          
                                          ) RETURN NUMBER;
  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla STO_TIPO_DOCUM_DIGIT el indicador de activo STO
  
  
  Fecha Creacion    : 29/09/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      pstipodocumento   Codigo de Documento
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_indic_activ_sto
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Inidica la oferta que corresponde al codigo de venta
  
  Fecha Creacion    : 15/10/2008
  Parametros Entrada:
  
  
                       pscodigoventa
             psoidperiodoreferencia
  
  Autor             : Arturo Blumen
  ***************************************************************************/
  FUNCTION sto_fn_devue_ofert_coven(pscodigoventa VARCHAR2,
                                    
                                    psoidperiodoreferencia NUMBER
                                    
                                    ) RETURN NUMBER;
  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla int_solic_conso_poven_cabec numero de documento
              de cruce
  
  
  Fecha Creacion    : 27/10/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      psnunlote         numero de lote
            psnumsecuencia    numero de secuencia
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_docu_cruc
  (
    pscodigopais        VARCHAR2,
    pscodigodocumento   VARCHAR2,
    psnunlote           VARCHAR2,
    psnumsecuencia      NUMBER,
    psnumsecuenciapadre NUMBER
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla int_solic_conso_poven_cabec el periodo de
              referencia
  
  
  Fecha Creacion    : 27/10/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      psnunlote         numero de lote
            psnumsecuencia    numero de secuencia
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_peri_refe
  (
    pscodigopais        VARCHAR2,
    pscodigodocumento   VARCHAR2,
    psnunlote           VARCHAR2,
    psnumsecuencia      NUMBER,
    psnumsecuenciapadre NUMBER
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion       : Inserta en la tabla STO_TMP_DOCUM_DIGIT todos los
                      registra aptos para validarse de la tabla STO_DOCUM_IDGIT
  
  Fecha Creacion    : 06/10/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_regis_docum_tempo_aptos
  (
    pscodigopais          VARCHAR2,
    psdocucodtipodocu     VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Inserta en la tabla STO_TMP_DOCUM_DIGIT todos los
                      registra aptos para enviarse a SICC
                      de la tabla STO_DOCUM_IDGIT
  Fecha Creacion    : 01/07/2014
  Autor             : Jose Antonio Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_regis_docum_tempo_envio
  (
    pscodigopais     VARCHAR2,
    pscodigotipodocu VARCHAR2,
    psnumeroproceso  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Agrega Mensajes de Error para un documento de STO
  Fecha Creacion    : 13/10/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_add_mensa_error
  (
    pnsecnumedocu NUMBER,
    psnumlote     VARCHAR2,
    psmensaje     VARCHAR2
  );
  /**************************************************************************
  Descripcion       : Proceso Ejecutado despues de el Envio de documentos Validos
  Fecha Creacion    : 17/05/2012
  Autor             : Jose Antonio Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_after_envio
  (
    pscodigopais     VARCHAR2,
    pscodigotipodocu VARCHAR2,
    psnumeroproceso  VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Validación generica de  error para Documentos STO
  Fecha Creacion    : 13/10/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_error
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación generica de  Rechazo STO
  Fecha Creacion    : 09/03/2015
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_recha
  (
    pscodpais     VARCHAR2,
    pscodtipodocu VARCHAR2,
    psnumlote     VARCHAR2,
    pnsecnumedocu NUMBER,
    psnumproc     VARCHAR2,
    psusuario     VARCHAR2,
    psmotirech    VARCHAR2,
    psobse        VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Auditoria para los registros procesados
  Fecha Creacion    : 09/02/2015
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/

  PROCEDURE sto_pr_gener_audi_proce
  (
    pscodpais     VARCHAR2,
    pscodtipodocu VARCHAR2,
    psnumproc     VARCHAR2,
    psacci        VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validación generica de Recuperación Rechazo STO
  Fecha Creacion    : 09/03/2015
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_recup_recha
  (
    pscodpais     VARCHAR2,
    pscodtipodocu VARCHAR2,
    psnumlote     VARCHAR2,
    pnsecnumedocu NUMBER,
    psnumproc     VARCHAR2,
    psusuario     VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Inidica el factor de repetición que corresponde al codigo de venta
  
  Fecha Creacion    : 15/10/2008
  Parametros Entrada:
  
  
                       pscodigoventa
             psoidperiodoreferencia
  
  Autor             : Arturo Blumen
  ***************************************************************************/
  FUNCTION sto_fn_devue_fact_repe(pscodigoventa VARCHAR2,
                                  
                                  psoidperiodoreferencia NUMBER
                                  
                                  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla sto_motiv_recha la descripcon del
              motivo de rechazo
  
  
  Fecha Creacion    : 12/12/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      pscodigodocumento     Codigo de Documento
            pscodigomotivo    Codigo de Motivo
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_moti_rech
  (
    pscodigopais      VARCHAR2,
    pscodigodocumento VARCHAR2,
    pscodigomotivo    VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion       : Obtenemos los datos de la clasificacion de la Consultora
  
  Fecha Creacion    : 12/01/2009
  Parametros Entrada:
                      varCod_clie          Código Cliente
  
  Autor             : efernandezo
  ***************************************************************************/
  PROCEDURE sto_pr_clasi_consu(varcod_clie VARCHAR2);

  /**************************************************************************
  Descripcion        : Devuelve la suma de las ventas de la tabla ped_solic_cabec
  Fecha Creacion     : 02/02/2009
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_venta
  (
    pscodigopais       VARCHAR2,
    psoidtiposolipais  NUMBER,
    psoidtiposoliconso NUMBER,
    pscodigoperiodo    VARCHAR2,
    psoidcliente       NUMBER
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve la suma del total de registros de  ped_solic_cabec
  Fecha Creacion     : 02/02/2009
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_total_regis
  (
    pscodigopais       VARCHAR2,
    psoidtiposolipais  NUMBER,
    psoidtiposoliconso NUMBER,
    pscodigoperiodo    VARCHAR2,
    psoidcliente       NUMBER
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion        : Devuelve la suma de las devoluciones de rec_linea_opera_recla
  Fecha Creacion     : 02/02/2009
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_descu
  (
    pscodigopais       VARCHAR2,
    psoidtiposolipais  NUMBER,
    psoidtiposoliconso NUMBER,
    pscodigoperiodo    VARCHAR2,
    psoidcliente       NUMBER
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Craga la informacinn en una tabla tempral para pasarlo a
                      Historicos
  Fecha Creacion    : 11/03/2009
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_carga_aptos_histo
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Carga los registros sin Gestion en una tabla temporal
                      para pasarlo a historicos
  Fecha Creacion    : 13/06/2012
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_docum_sgest_histo
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psnumeroproceso VARCHAR2
  );
  /**************************************************************************
  Descripcion        : Devuelve el codigo de referencia
  Fecha Creacion     : 24/04/2009
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_codig_refer
  (
    pscodtemp VARCHAR2,
    pscodpais VARCHAR2
  ) RETURN VARCHAR2;
  /**************************************************************************
  Descripcion        : Devuelve el codigo de referencia
  Fecha Creacion     : 06/05/2009
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_codre_esika
  (
    pscodtemp VARCHAR2,
    pscodpais VARCHAR2
  ) RETURN VARCHAR2;

  FUNCTION sto_fn_devue_venta_devue_falta
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    pscliente       VARCHAR2,
    psnumsecuencia  NUMBER,
    pstipodocumento VARCHAR2
  ) RETURN NUMBER;
  FUNCTION sto_fn_devue_venta_devue_devue
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    pscliente       VARCHAR2,
    psnumsecuencia  NUMBER,
    pstipodocumento VARCHAR2
  ) RETURN NUMBER;

  FUNCTION sto_fn_devue_produ_canti_devue
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    pscliente       VARCHAR2,
    psnumdocu       NUMBER,
    pstipodocumento VARCHAR2
  ) RETURN NUMBER;
  FUNCTION sto_fn_devue_produ_canti_falta
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    pscliente       VARCHAR2,
    psnumdocu       NUMBER,
    pstipodocumento VARCHAR2
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion       : Obtiene el indicador de Ejecucion automatica de Validaciones
                      1:Activo
                      0 o otro:Inactivo;
  Fecha Creacion    : 19/06/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_indic_valid_autom
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       :  Procedimiento con pasos antes de indicar
                      los registros validos
  Fecha Creacion    : 03/05/2010
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_befor_exval
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Procedimiento con pasos posteriores despues de indicar
                      los registros validos
  Fecha Creacion    : 03/05/2010
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_after_exval
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Devuelve el modulo diez de la cadena ingresda
  Fecha Creacion     : 08/11/2010
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_fn_devue_modul_diez(pscodigo VARCHAR2) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Valida si el codigo de cliente cumple con el modulo diez
  Fecha Creacion     : 08/11/2010
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_fn_valid_modul_diez(pscodigo VARCHAR2) RETURN VARCHAR2;
  /***************************************************************************
  Descripcion       : Genera una tabla virtual para la consulta de Beneficio Deuda
  Fecha Creacion    : 05/11/2010
  Autor             : Jesse Rios Franco
  ***************************************************************************/
  FUNCTION sto_fn_benef_deuda
  (
    pscodigopais     VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoiso      VARCHAR2,
    pnoidtipocliente NUMBER,
    pnoidregion      NUMBER,
    pnoidcliente     NUMBER,
    pnoidperiodo     NUMBER
  ) RETURN tabla_beneficio_deuda
    PIPELINED;

  /***************************************************************************
  Descripcion       : Genera una tabla virtual para la consulta de
                      Facturacion Adicional
  Fecha Creacion    : 11/04/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  FUNCTION sto_fn_factu_adici
  (
    pscodigopais  VARCHAR2,
    pscodigomarca VARCHAR2,
    pscodigocanal VARCHAR2,
    pscodigoiso   VARCHAR2
  ) RETURN tabla_facturacion_adicional
    PIPELINED;

  /**************************************************************************
  Descripcion       : MArca registros a los que no se debe ejecutar validacion
  Fecha Creacion    : 10/12/2012
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_exclu_valid
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    psnumeroproceso       VARCHAR2,
    psusuario             VARCHAR2
  );
  /***************************************************************************
   Descripcion       : Depura Informacion Orden de Compra
   Fecha Creacion    : 03/10/2012
   Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_depur_orden_compr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumeroproceso       VARCHAR2,
    pscodigoperiodo       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Devuelve la fecha real de entrega que obtiene de
                      la interfaz de OT    - Colombia -
  
  Fecha Creacion    : 10/01/2013
  Parametros Entrada: pscodigocliente
                      pscodigoperiodo
  
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_fn_devue_fecha_entre
  (
    pscodigocliente VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Devuelve la fecha real de entrega que obtiene de
                      la interfaz  - Colombia -
  --------------------------------------------------------------------------
  Fecha Creacion    : 10/01/2013
  Parametros Entrada: pscodigocliente
                      pscodigoperiodo
  --------------------------------------------------------------------------
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_fn_fecha_entre_forma
  (
    pscodigocliente VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Carga la tabla temporal que contiene la informacion
                      para mostrar en la pantalla de seguimiento de pedidos
                      - Colombia -
  --------------------------------------------------------------------------
  Fecha Creacion    : 10/01/2013
  Parametros Entrada: pscodigocliente
                      pscodigoperiodo
  --------------------------------------------------------------------------
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_carga_segui_pedid
  (
    pscodigocliente VARCHAR2,
    pscodigoperiodo VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Carga la Tabla Temporal que contiene la informacion
                      para mostrar en el reporte de errores STO
                      - Colombia -
  --------------------------------------------------------------------------
  Fecha Creacion    : 08/04/2013
  Autor             : Eduardo Sanchez
  ***************************************************************************/
  PROCEDURE sto_pr_carga_tempo_repo_erro
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigousuario VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Carga la Tabla Temporal que contiene la informacion
                      para mostrar en el reporte al termino de facturación
                      - Colombia -
  --------------------------------------------------------------------------
  Fecha Creacion    : 15/07/2013
  Parametros Entrada: pscodigoperiodo
                      pscodigousuario
  --------------------------------------------------------------------------
  Autor             : Eduardo Sanchez
  ***************************************************************************/

  PROCEDURE sto_pr_carga_tmp_ceaco_factu
  (
    pscodigoperiodo    VARCHAR2,
    psfechafacturacion VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Carga la Tabla Temporal que contiene la informacion
                      para mostrar en el reporte de simulacion de faltantes
  --------------------------------------------------------------------------
  Fecha Creacion    : 18/07/2013
  Parametros Entrada: pscodigoperiodo
                      pscodigousuario
                      pnNroPedidosProyectar
                      pnTipoPup -> DA: Demanda Anticipada --DC :Demanda Campaña
                      pnPromedioMontoPedido
                      pnPorcMaximoFaltante
  
  --------------------------------------------------------------------------
  Autor             : Eduardo Sanchez
  ***************************************************************************/
  PROCEDURE sto_pr_carga_tmp_simul_falta
  (
    pscodigoperiodo       VARCHAR2,
    psfechafacturacion    VARCHAR2,
    pnnropedidosproyectar NUMBER,
    pntipopup             VARCHAR2,
    pnpromediomontopedido NUMBER,
    pnporcmaximofaltante  NUMBER
  );

  /**************************************************************************
  Descripcion       : Devuelve el telefono
  Fecha Creacion    : 15/08/2013
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_fn_devue_telef(pscodigocliente VARCHAR2) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion       : Devuelve la estructura geopolitica
                      Departamento - Provincia - Distrito
  Fecha Creacion    : 08/11/2013
  Parametros Entrada:  pscodigoPais (codigo País)
                       psunidadAdmin (Unidad Administrativa)
  
  Autor             : Yahir Rivas Luna
  ***************************************************************************/
  FUNCTION sto_fn_devue_estru_geopo
  (
    pscodigopais  VARCHAR2,
    psunidadadmin VARCHAR2
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Genera una tabla virtual para la consulta de
                      Forma de Pago Clasificacion
  Fecha Creacion    : 02/04/2014
  Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
  FUNCTION sto_fn_forma_pago_clasi
  (
    pscodigopais  VARCHAR2,
    pscodigomarca VARCHAR2,
    pscodigocanal VARCHAR2,
    pscodigoiso   VARCHAR2
  ) RETURN tabla_forma_pago_clasificacion
    PIPELINED;

  /**************************************************************************
  Descripcion        : Devuelve descripcion de Indicador de Solicitud de Credito.
  Fecha Creacion     : 20/05/2014
  Autor              : Carlos Bazalar
  ***************************************************************************/
  FUNCTION sto_fn_devue_recha_solcr(pscodigocliente VARCHAR2) RETURN VARCHAR2;
  
  /**************************************************************************
  Descripcion        : Devuelve campos de gestion de documento STO.
  Fecha Creacion     : 12/02/2015
  Autor              : Rosalvina Ramirez
  ***************************************************************************/
  
  FUNCTION sto_fn_devue_gesti_docum(psCampo          VARCHAR2,
                                    psTipoDocu       VARCHAR2,
                                    psCodClie        VARCHAR2,                                    
                                    psOidPeri        VARCHAR2
                                  ) RETURN VARCHAR2;

END sto_pkg_gener;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_gener IS
  indicador_modulo_once CONSTANT VARCHAR2(1) := 'S';

  /**************************************************************************
  Descripcion        : Devuelve Numero de Proceso a Usar
  Fecha Creacion     : 21/06/2012
  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_nproce
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumeroproceso       OUT VARCHAR2
  ) AS
  
    lnmodo sto_tipo_docum_digit.val_modo_gene_proc%TYPE;
  BEGIN
  
    SELECT val_modo_gene_proc
      INTO lnmodo
      FROM sto_tipo_docum_digit
     WHERE cod_tipo_docu = pscodigotipodocumento
       AND cod_pais = pscodigopais;
  
    IF lnmodo = 1 THEN
    
      psnumeroproceso := lpad(sto_hipr_seq.nextval, 12, 0) || '000';
    
    ELSE
    
      SELECT to_char(SYSDATE, 'YYYYMMDD') ||
             lpad(nvl(MAX(substr(hp.proc_num_proc, 9, 4)), 0) + 1, 4, '0') ||
             '000'
        INTO psnumeroproceso
        FROM sto_histo_proce hp
       WHERE hp.docu_cod_tipo_docu = pscodigotipodocumento
         AND hp.pais_cod_pais = pscodigopais
         AND trunc(SYSDATE) = trunc(hp.fec_ipro);
    
    END IF;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_GENER_NPROCE: ' || ls_sqlerrm);
    
  END sto_pr_gener_nproce;
  /**************************************************************************
  Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                       separndo n Numeros para inserciones.
  Fecha Creacion     : 27/11/2007
  
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_nproce
  (
    pscodigopais  VARCHAR2,
    pscodtipodocu VARCHAR2,
    psusuario     VARCHAR2
  ) RETURN VARCHAR2 IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  
    lsnumeroproceso sto_histo_proce.proc_num_proc%TYPE;
  BEGIN
  
    SELECT to_char(SYSDATE, 'YYYYMMDD') ||
           lpad(nvl(MAX(substr(hp.proc_num_proc, 9, 4)), 0) + 1, 4, '0') ||
           '000'
      INTO lsnumeroproceso
      FROM sto_histo_proce hp
     WHERE hp.docu_cod_tipo_docu = pscodtipodocu
       AND hp.pais_cod_pais = pscodigopais
       AND trunc(SYSDATE) = trunc(hp.fec_ipro);
  
    INSERT INTO sto_histo_proce
      (pais_cod_pais,
       docu_cod_tipo_docu,
       proc_num_proc,
       fec_ipro,
       fec_fpro,
       ind_loer,
       log_proc,
       usu_proc,
       ind_espr,
       reg_proc,
       cod_acci)
    VALUES
      (pscodigopais,
       pscodtipodocu,
       lsnumeroproceso,
       SYSDATE,
       NULL,
       NULL,
       NULL,
       psusuario,
       NULL,
       NULL,
       NULL);
  
    COMMIT;
    RETURN lsnumeroproceso;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_FN_DEVUE_NPROCE: ' || ls_sqlerrm);
    
  END sto_fn_devue_nproce;

  /**************************************************************************
  Descripcion       : Devuelve el valor del parametro de la tabla
               STO_PARAM_GENER_OCCRR
   Fecha Creacion    : 26/05/2008
  Parametros Entrada:
      psCodigoPais   : Codigo de pais
      psCodigoParametro   : Codigo de parametro
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_obten_param_ocr
  (
    pscodigopais      VARCHAR2,
    pscodigoparametro VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lsvalor VARCHAR2(15);
  
  BEGIN
  
    SELECT a.val_param
      INTO lsvalor
      FROM sto_param_gener_occrr a
     WHERE a.cod_pais = pscodigopais
       AND a.cod_para = pscodigoparametro;
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      RETURN '';
    
  END sto_fn_obten_param_ocr;

  /**************************************************************************
  Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                       separndo n Numeros para inserciones.
  Fecha Creacion     : 05/01/2007
  
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_secue_nsoli
  (
    pscodigopais      VARCHAR2,
    pscodigooperacion VARCHAR2,
    pscodigosubacceso VARCHAR2
  ) RETURN NUMBER IS
  
    lnsolicitudinicio NUMBER;
  
  BEGIN
  
    SELECT ns.val_ulti_nume_soli
      INTO lnsolicitudinicio
      FROM ped_numer_solic ns
     WHERE ns.val_oper = pscodigooperacion
       AND ns.val_anio = to_char(to_number(to_char(SYSDATE, 'YY')))
       AND ns.cod_suba = pscodigosubacceso
       AND ns.cod_pais = pscodigopais
       FOR UPDATE;
  
    RETURN lnsolicitudinicio;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_FN_ACTUA_NUMER_NSOLI: ' ||
                              ls_sqlerrm);
    
  END sto_fn_devue_secue_nsoli;

  /**************************************************************************
  Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                       separndo n Numeros para inserciones.
  Fecha Creacion     : 27/11/2007
  
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_resrv_secue_nsoli
  (
    pscodigopais      VARCHAR2,
    pscodigooperacion VARCHAR2,
    pscodigosubacceso VARCHAR2,
    pnnumreservados   NUMBER
  ) RETURN NUMBER IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  
    lnnumsoliinicio      NUMBER := NULL;
    lnnumsoliformatfinal NUMBER;
  BEGIN
  
    lnnumsoliinicio := sto_pkg_gener.sto_fn_devue_secue_nsoli(pscodigopais,
                                                              pscodigooperacion,
                                                              pscodigosubacceso);
  
    lnnumsoliformatfinal := lnnumsoliinicio + pnnumreservados + 1;
  
    UPDATE ped_numer_solic ns
       SET ns.val_ulti_nume_soli = lnnumsoliformatfinal
     WHERE ns.val_oper = pscodigooperacion
       AND ns.val_anio = to_char(to_number(to_char(SYSDATE, 'YY')))
       AND ns.cod_suba = pscodigosubacceso
       AND ns.cod_pais = pscodigopais;
  
    COMMIT;
  
    RETURN lnnumsoliinicio;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_FN_RESRV_SECUE_NSOLI: ' ||
                              ls_sqlerrm);
    
  END sto_fn_resrv_secue_nsoli;

  /**************************************************************************
  Descripcion        : Devuelve el Inicio de de numero de solicitua actual
                       separndo n Numeros para inserciones.
  Fecha Creacion     : 27/11/2007
  
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_resrv_secue_nsoli
  (
    pscodigopais      VARCHAR2,
    pscodigooperacion VARCHAR2,
    pscodigosubacceso VARCHAR2,
    pnnumreservados   NUMBER,
    psanio            VARCHAR2
  ) RETURN NUMBER IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  
    lnnumsoliformatfinal NUMBER;
  BEGIN
  
    UPDATE ped_numer_solic ns
       SET ns.val_ulti_nume_soli = val_ulti_nume_soli + pnnumreservados + 1
     WHERE ns.val_oper = nvl(pscodigooperacion, ns.val_oper)
       AND ns.val_anio = nvl(psanio, ns.val_anio)
       AND ns.cod_suba = nvl(pscodigosubacceso, ns.cod_suba)
       AND ns.cod_pais = pscodigopais
    RETURNING val_ulti_nume_soli INTO lnnumsoliformatfinal;
  
    COMMIT;
  
    RETURN lnnumsoliformatfinal - pnnumreservados - 1;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_FN_RESRV_SECUE_NSOLI: ' ||
                              ls_sqlerrm);
    
  END sto_fn_resrv_secue_nsoli;

  /**************************************************************************
  Descripcion       : Devuelve el codigo de Documento Hijo de un Documento
                      Cabecera
  Fecha Creacion    : 05/06/2008
  Parametros Entrada:
      psCodigoPais   : Codigo de pais
      psCodigoParametro   : Codigo de parametro
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_codig_docum_detal
  (
    pscodigopais     VARCHAR2,
    pscodigocabecera VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lscodidodocumentohijo sto_tipo_docum_digit.cod_tipo_docu_cabe%TYPE;
  
  BEGIN
  
    SELECT td.cod_tipo_docu
      INTO lscodidodocumentohijo
      FROM sto_tipo_docum_digit td
     WHERE td.cod_tipo_docu_cabe = pscodigocabecera
       AND td.cod_pais = pscodigopais;
  
    RETURN lscodidodocumentohijo;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
    
  END sto_fn_devue_codig_docum_detal;

  /**************************************************************************
  Descripcion       : Devuelve el indicador de rechazo automatico de
  Fecha Creacion    : 06/06/2008
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_indic_recha_autom
  (
    pscodigopais       VARCHAR2,
    pscodigotipodoc    VARCHAR2,
    pscodigovalidacion VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lsindicador sto_param_valid.ind_rech_auto%TYPE;
  
  BEGIN
  
    SELECT ind_rech_auto
      INTO lsindicador
      FROM sto_param_valid
     WHERE cod_pais = pscodigopais
       AND cod_tipo_docu = pscodigotipodoc
       AND cod_vali = pscodigovalidacion;
  
    RETURN lsindicador;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
    
  END sto_fn_devue_indic_recha_autom;

  /**************************************************************************
  Descripcion        : Devuelve el codigo de la Ultima Validacion que debe ser
                       ejecutada para un documento
  Fecha Creacion     : 09/06/2008
  
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_ultim_valid_ejecu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lscodigoultimavalidacion sto_param_valid.cod_vali%TYPE;
  
  BEGIN
  
    SELECT cod_vali
      INTO lscodigoultimavalidacion
      FROM sto_histo_valid h
     WHERE h.cod_pais = pscodigopais
       AND h.cod_tipo_docu = pscodigotipodocumento
       AND h.num_proc = psnumeroproceso
       AND h.num_secu = (SELECT MAX(h1.num_secu)
                           FROM sto_histo_valid h1
                          WHERE h1.cod_pais = pscodigopais
                            AND h1.cod_tipo_docu = pscodigotipodocumento
                            AND h1.num_proc = psnumeroproceso);
  
    RETURN lscodigoultimavalidacion;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_FN_DEVUE_MAXIM_NSECU_VALID: ' ||
                              ls_sqlerrm);
    
  END sto_fn_devue_ultim_valid_ejecu;

  /**************************************************************************
  Descripcion        : Devuelve el modulo once de la cadena ingresda
  Fecha Creacion     : 17/06/2008
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_modul_once
  (
    pscodtemp VARCHAR2,
    pscodpais VARCHAR2
  ) RETURN VARCHAR2 IS
  
    chrnit      VARCHAR2(100);
    intcontador NUMBER;
    intacum     NUMBER;
    intresto    NUMBER;
    intlongiden NUMBER; --Longitud de la cadena identificación}
  
    -- Arreglo
    TYPE arreglopa IS TABLE OF NUMBER(2) INDEX BY BINARY_INTEGER;
  
    -- Variable de tipo arreglo
    arrpa arreglopa;
    arrda arreglopa;
  BEGIN
    IF nvl(sto_fn_obten_param_ocr(pscodpais, 'STO_INDI_MODU_ONCE'), 'N') =
       indicador_modulo_once THEN
      chrnit := pscodtemp;
      -- Válida el dígito de chequeo
      SELECT length(chrnit) + 1 INTO intlongiden FROM dual;
    
      -- Llenar el arreglo PA, estos son números primos, según loe stablece el algoritmo
      arrpa(1) := 2;
      arrpa(2) := 3;
      arrpa(3) := 4;
      arrpa(4) := 5;
      arrpa(5) := 6;
      arrpa(6) := 7;
      arrpa(7) := 2;
      arrpa(8) := 3;
      arrpa(9) := 4;
      arrpa(10) := 5;
      arrpa(11) := 6;
      arrpa(12) := 7;
      arrpa(13) := 2;
      arrpa(14) := 3;
      arrpa(15) := 4;
      intcontador := 1;
      -- Llena el arreglo DA con la cadena de entrada que es el NIT
      FOR intcontador IN 1 .. intlongiden - 1
      LOOP
        arrda(intcontador) := to_number(substr(chrnit,
                                               intlongiden - intcontador,
                                               1));
      END LOOP;
      -- Llena con ceroas el resto de posiciones del arreglo
      FOR intcontador IN intlongiden .. 15
      LOOP
        arrda(intcontador) := 0;
      END LOOP;
      intacum := 0;
      FOR intcontador IN 1 .. 15
      LOOP
        intacum := intacum + (arrda(intcontador) * arrpa(intcontador));
      END LOOP;
      -- Obtiene el modulo 11 del acumulado
      intresto := MOD(intacum, 11);
      IF (intresto > 1) THEN
        RETURN to_char(11 - intresto);
      ELSE
        RETURN to_char(intresto);
      END IF;
    ELSE
      RETURN pscodtemp;
    END IF;
  
  END sto_fn_devue_modul_once;
  /***************************************************************************
  Descripcion       : Actualiza los Registros para ser Validados
  Fecha Creacion    : 01/02/2012
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_updat_docum_proce
  (
    pscodigopais     VARCHAR2,
    pscodigotipodocu VARCHAR2,
    psnumeroproceso  VARCHAR2
  ) IS
    CURSOR c_stolist(vscodigodocumentodetalle VARCHAR2) IS
      SELECT tm.num_lote,
             tm.sec_nume_docu
        FROM sto_tmp_gestio_docum_digit tm,
             sto_docum_digit            dd
       WHERE tm.cod_tipo_docu = pscodigotipodocu
         AND dd.cod_pais = tm.cod_pais
         AND dd.cod_tipo_docu = tm.cod_tipo_docu
         AND dd.num_lote = tm.num_lote
         AND dd.sec_nume_docu = tm.sec_nume_docu
         AND dd.ind_envi = '0'
         AND dd.ind_rech = '0'
      UNION ALL
      SELECT tm.num_lote,
             dd.sec_nume_docu_cabe
        FROM sto_tmp_gestio_docum_digit tm,
             sto_docum_digit            dd
       WHERE tm.cod_tipo_docu = vscodigodocumentodetalle
         AND dd.cod_pais = tm.cod_pais
         AND dd.cod_tipo_docu = tm.cod_tipo_docu
         AND dd.num_lote = tm.num_lote
         AND dd.sec_nume_docu = tm.sec_nume_docu
         AND dd.ind_envi = '0'
         AND dd.ind_rech = '0';
  
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_secnumedocu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
  
    v_numlote     t_numlote;
    v_secnumedocu t_secnumedocu;
  
    rows NATURAL := 5000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
  
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
  BEGIN
  
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodocu);
  
    OPEN c_stolist(lscodigodocumentodetalle);
    LOOP
      FETCH c_stolist BULK COLLECT
        INTO v_numlote,
             v_secnumedocu LIMIT rows;
    
      IF v_numlote.count > 0 THEN
      
        /*Inserta Registros de Error*/
        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit d1
             SET d1.num_proc = psnumeroproceso
           WHERE d1.cod_pais = pscodigopais
             AND d1.num_lote = v_numlote(i)
             AND d1.sec_nume_docu = v_secnumedocu(i)
             AND d1.cod_tipo_docu = pscodigotipodocu;
      
        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit d1
             SET d1.num_proc = psnumeroproceso
           WHERE d1.cod_pais = pscodigopais
             AND d1.num_lote = v_numlote(i)
             AND d1.sec_nume_docu_cabe = v_secnumedocu(i)
             AND d1.cod_tipo_docu = lscodigodocumentodetalle;
      
      END IF;
      EXIT WHEN c_stolist%NOTFOUND;
    
    END LOOP;
    CLOSE c_stolist;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_UPDAT_DOCUM_PROCE: ' ||
                              ls_sqlerrm);
    
  END sto_pr_updat_docum_proce;

  /***************************************************************************
  Descripcion       : Actualiza los Registros para ser Validados
  Fecha Creacion    : 22/10/2009
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_inici_valid_nomas
  (
    pscodigopais     VARCHAR2,
    pscodigotipodocu VARCHAR2,
    psnumeroproceso  VARCHAR2
  ) IS
    CURSOR c_errorins(vscodigodocumentodetalle VARCHAR2) IS
      SELECT tm.num_lote,
             tm.sec_nume_docu
        FROM sto_tmp_gestio_docum_digit tm,
             sto_docum_digit            dd
       WHERE tm.cod_tipo_docu = pscodigotipodocu
         AND dd.cod_pais = tm.cod_pais
         AND dd.cod_tipo_docu = tm.cod_tipo_docu
         AND dd.num_lote = tm.num_lote
         AND dd.sec_nume_docu = tm.sec_nume_docu
         AND dd.ind_rech = '0'
         AND dd.ind_envi = '0'
         AND NOT EXISTS
       (SELECT 1
                FROM sto_histo_proce h
               WHERE h.proc_num_proc != psnumeroproceso
                 AND h.proc_num_proc = num_proc
                 AND h.fec_fpro IS NULL
                 AND pscodigotipodocu = h.docu_cod_tipo_docu)
      UNION
      SELECT DISTINCT tm.num_lote,
                      dd.sec_nume_docu_cabe
        FROM sto_tmp_gestio_docum_digit tm,
             sto_docum_digit            dd
       WHERE tm.cod_tipo_docu = vscodigodocumentodetalle
         AND dd.cod_pais = tm.cod_pais
         AND dd.cod_tipo_docu = tm.cod_tipo_docu
         AND dd.num_lote = tm.num_lote
         AND dd.sec_nume_docu = tm.sec_nume_docu
         AND dd.ind_rech = '0'
         AND dd.ind_envi = '0';
  
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_secnumedocu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
  
    v_numlote     t_numlote;
    v_secnumedocu t_secnumedocu;
  
    i                   BINARY_INTEGER := 0;
    lsnumeroprocesohijo sto_histo_proce.proc_num_proc%TYPE;
  
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
    lnnumrows                sto_tipo_docum_digit.num_regi_tran%TYPE;
  
    lnsecuencia NUMBER := 0;
  
    lnregproc NUMBER;
  
  BEGIN
  
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodocu);
  
    SELECT t.num_regi_tran
      INTO lnnumrows
      FROM sto_tipo_docum_digit t
     WHERE cod_tipo_docu = pscodigotipodocu;
  
    OPEN c_errorins(lscodigodocumentodetalle);
    LOOP
      FETCH c_errorins BULK COLLECT
        INTO v_numlote,
             v_secnumedocu LIMIT lnnumrows;
    
      IF v_numlote.count > 0 THEN
      
        lnsecuencia := lnsecuencia + 1;
      
        lsnumeroprocesohijo := lpad(psnumeroproceso + lnsecuencia, 15, '0');
      
        lnregproc := v_numlote.count;
      
        INSERT INTO sto_histo_proce
          (pais_cod_pais,
           docu_cod_tipo_docu,
           proc_num_proc,
           ind_loer,
           usu_proc,
           ind_espr,
           cod_acci,
           num_proc_padr,
           fec_ipro,
           reg_proc)
          SELECT pais_cod_pais,
                 docu_cod_tipo_docu,
                 lsnumeroprocesohijo,
                 ind_loer,
                 usu_proc,
                 ind_espr,
                 cod_acci,
                 psnumeroproceso,
                 SYSDATE,
                 lnregproc
            FROM sto_histo_proce h
           WHERE proc_num_proc = psnumeroproceso
             AND docu_cod_tipo_docu = pscodigotipodocu
             AND pais_cod_pais = pscodigopais;
      
        /*Inserta Registros de Error*/
        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit d1
             SET cod_ulti_vali_ejec = NULL,
                 cod_ulti_vali_exit = NULL,
                 cod_ulti_vali_erro = NULL,
                 d1.num_proc        = lsnumeroprocesohijo
           WHERE d1.cod_pais = pscodigopais
             AND d1.num_lote = v_numlote(i)
             AND d1.ind_envi = '0'
             AND d1.ind_rech = '0'
             AND d1.sec_nume_docu = v_secnumedocu(i)
             AND d1.cod_tipo_docu = pscodigotipodocu;
      
        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit d1
             SET cod_ulti_vali_ejec = NULL,
                 cod_ulti_vali_exit = NULL,
                 cod_ulti_vali_erro = NULL,
                 d1.num_proc        = lsnumeroprocesohijo
           WHERE d1.cod_pais = pscodigopais
             AND d1.num_lote = v_numlote(i)
             AND d1.ind_envi = '0'
             AND d1.ind_rech = '0'
             AND d1.sec_nume_docu_cabe = v_secnumedocu(i)
             AND d1.cod_tipo_docu = lscodigodocumentodetalle;
      
        COMMIT;
      
      END IF;
      EXIT WHEN c_errorins%NOTFOUND;
    
    END LOOP;
    CLOSE c_errorins;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      UPDATE sto_histo_proce h
         SET ind_loer = 'S',
             fec_fpro = SYSDATE,
             log_proc = ls_sqlerrm
       WHERE h.num_proc_padr = psnumeroproceso
         AND h.docu_cod_tipo_docu = pscodigotipodocu
         AND h.proc_num_proc = pscodigopais;
      COMMIT;
    
      raise_application_error(-20123,
                              'ERROR STO_PR_INICI_VALID_NOMAS: ' ||
                              ls_sqlerrm);
    
  END sto_pr_inici_valid_nomas;
  /***************************************************************************
  Descripcion       : Inicializa los valores de los Docuemnetos procesados por
                      STO
  Fecha Creacion    : 09/03/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_inici_valid_masiv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigousuario       VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
    CURSOR c_registros(vscodigoperiodo VARCHAR2) IS
      SELECT dd.num_lote,
             dd.sec_nume_docu
        FROM sto_docum_digit dd
       WHERE (vscodigoperiodo IS NULL OR cod_peri IS NULL OR
             cod_peri = vscodigoperiodo)
         AND cod_tipo_docu = pscodigotipodocumento
         AND ind_envi = '0'
         AND ind_rech = '0'
         AND NOT EXISTS
       (SELECT 1
                FROM sto_histo_proce h
               WHERE h.proc_num_proc != psnumeroproceso
                 AND h.proc_num_proc = dd.num_proc
                 AND h.docu_cod_tipo_docu = cod_tipo_docu
                 AND h.fec_fpro IS NULL
                 AND pscodigotipodocumento = h.docu_cod_tipo_docu);
  
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_secnumedocu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
  
    v_numlote     t_numlote;
    v_secnumedocu t_secnumedocu;
  
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
    lnnumrows sto_tipo_docum_digit.num_regi_tran%TYPE;
  
    lsnumeroprocesohijo sto_histo_proce.proc_num_proc%TYPE;
  
    lnsecuencia NUMBER := 0;
  
    lscodigoperiodo seg_perio_corpo.cod_peri%TYPE := NULL;
  
    lnestadisticas NUMBER := NULL;
  
    lnpedidos             NUMBER := 0;
    lnestadisticaspedidos NUMBER := 0;
  
    lnregproc NUMBER;
  
  BEGIN
  
    IF pscodigotipodocumento = 'OCC' THEN
      SELECT cod_peri
        INTO lscodigoperiodo
        FROM bas_ctrl_fact c
       WHERE c.sta_camp = '0'
         AND c.ind_camp_act = '1';
    
      SELECT MIN(num_rows)
        INTO lnestadisticas
        FROM dba_tables
       WHERE owner = USER
         AND table_name = 'STO_PROCE_DOCUM_DIGIT';
    
      SELECT COUNT(1) INTO lnpedidos FROM int_solic_conso_cabec;
    
      SELECT MIN(num_rows)
        INTO lnestadisticaspedidos
        FROM dba_tables
       WHERE owner = USER
         AND table_name = 'INT_SOLIC_CONSO_CABEC';
    
    END IF;
  
    SELECT t.num_regi_tran
      INTO lnnumrows
      FROM sto_tipo_docum_digit t
     WHERE cod_tipo_docu = pscodigotipodocumento;
  
    /*OBTIENE EL CODIGO DE DOCUMENTO DETALLE*/
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodocumento);
  
    /*ACTUALIZA LOS DOCUMENTOS A PROCESAR*/
    -- IF psindicadormasivo = '1' THEN
    OPEN c_registros(lscodigoperiodo);
    LOOP
      FETCH c_registros BULK COLLECT
        INTO v_numlote,
             v_secnumedocu LIMIT lnnumrows;
    
      IF v_numlote.count > 0 THEN
        IF lnsecuencia < 999 THEN
          lnsecuencia := lnsecuencia + 1;
        
          lsnumeroprocesohijo := lpad(psnumeroproceso + lnsecuencia,
                                      15,
                                      '0');
        
          lnregproc := v_numlote.count;
        
          /*INSERTA EN HISTORICO DE EJECUCION */
          INSERT INTO sto_histo_proce
            (pais_cod_pais,
             docu_cod_tipo_docu,
             proc_num_proc,
             ind_loer,
             usu_proc,
             ind_espr,
             cod_acci,
             num_proc_padr,
             fec_ipro,
             reg_proc)
            SELECT pais_cod_pais,
                   docu_cod_tipo_docu,
                   lsnumeroprocesohijo,
                   ind_loer,
                   usu_proc,
                   ind_espr,
                   cod_acci,
                   psnumeroproceso,
                   SYSDATE,
                   lnregproc
              FROM sto_histo_proce h
             WHERE proc_num_proc = psnumeroproceso
               AND docu_cod_tipo_docu = pscodigotipodocumento
               AND pais_cod_pais = pscodigopais;
        END IF;
      
        /*ACTUALIZA LAS CABECERAS A EJECUTAR*/
        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit d1
             SET cod_ulti_vali_ejec = NULL,
                 cod_ulti_vali_exit = NULL,
                 cod_ulti_vali_erro = NULL,
                 d1.num_proc        = lsnumeroprocesohijo,
                 d1.usu_modi        = pscodigousuario
           WHERE d1.cod_pais = pscodigopais
             AND d1.num_lote = v_numlote(i)
             AND d1.ind_envi = '0'
             AND d1.ind_rech = '0'
             AND d1.sec_nume_docu = v_secnumedocu(i)
             AND d1.cod_tipo_docu = pscodigotipodocumento;
      
        /*ACTUALIZA LOS DETALLES A EJECUTAR*/
        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit d1
             SET cod_ulti_vali_ejec = NULL,
                 cod_ulti_vali_exit = NULL,
                 cod_ulti_vali_erro = NULL,
                 d1.num_proc        = lsnumeroprocesohijo
           WHERE d1.cod_pais = pscodigopais
             AND d1.num_lote = v_numlote(i)
             AND d1.ind_rech = '0'
             AND d1.sec_nume_docu_cabe = v_secnumedocu(i)
             AND d1.cod_tipo_docu = lscodigodocumentodetalle;
      
        IF pscodigotipodocumento = 'OCC' AND lnestadisticas = 0 THEN
        
          INSERT INTO sto_proce_docum_digit
            (sec_nume_docu,
             num_proc,
             num_lote,
             cod_tipo_docu,
             cod_pais,
             sec_nume_docu_cabe,
             cod_clie)
            SELECT occ.sec_nume_docu,
                   occ.num_proc,
                   occ.num_lote,
                   occ.cod_tipo_docu,
                   occ.cod_pais,
                   occ.sec_nume_docu_cabe,
                   occ.cod_clie
              FROM sto_docum_digit occ
             WHERE occ.num_proc = lsnumeroprocesohijo
               AND occ.cod_tipo_docu = pscodigotipodocumento
               AND occ.cod_pais = pscodigopais;
        
        END IF;
      
        COMMIT;
      
      END IF;
      EXIT WHEN c_registros%NOTFOUND;
    
    END LOOP;
    CLOSE c_registros;
  
    IF pscodigotipodocumento = 'OCC' THEN
    
      IF lnestadisticas = 0 OR lnpedidos > lnestadisticaspedidos * 3 THEN
      
        dbms_stats.gather_table_stats(ownname => USER,
                                      tabname => 'STO_PROCE_DOCUM_DIGIT',
                                      cascade => TRUE);
        dbms_stats.gather_table_stats(ownname => USER,
                                      tabname => 'INT_SOLIC_CONSO_CABEC',
                                      cascade => TRUE);
        dbms_stats.gather_table_stats(ownname => USER,
                                      tabname => 'INT_SOLIC_CONSO_DETAL',
                                      cascade => TRUE);
      END IF;
    
      sto_pkg_gener.sto_pr_depur_orden_compr(pscodigopais,
                                             pscodigotipodocumento,
                                             psnumeroproceso,
                                             lscodigoperiodo);
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      UPDATE sto_histo_proce h
         SET ind_loer = 'S',
             fec_fpro = SYSDATE,
             log_proc = ls_sqlerrm
       WHERE h.num_proc_padr = psnumeroproceso
         AND h.docu_cod_tipo_docu = pscodigotipodocumento
         AND h.proc_num_proc = pscodigopais;
      COMMIT;
    
      raise_application_error(-20123,
                              'ERROR STO_PR_INICI_VALID_MASIV: ' ||
                              ls_sqlerrm);
  END sto_pr_inici_valid_masiv;

  /***************************************************************************
  Descripcion       : Marca los registros para ser eliminados
  Fecha Creacion    : 26/02/2013
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_updat_delet
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumlote             VARCHAR2,
    pssecnumedocu         VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
  BEGIN
  
    INSERT INTO sto_proce_docum_digit
      (sec_nume_docu,
       num_proc,
       num_lote,
       cod_tipo_docu,
       cod_pais)
    VALUES
      (pssecnumedocu,
       psnumeroproceso,
       psnumlote,
       pscodigotipodocumento,
       pscodigopais);
  
    UPDATE sto_docum_digit
       SET num_proc = psnumeroproceso
     WHERE sec_nume_docu = pssecnumedocu
       AND num_lote = psnumlote
       AND cod_tipo_docu = pscodigotipodocumento
       AND cod_pais = pscodigopais
       AND NOT EXISTS
     (SELECT 1
              FROM sto_histo_proce h
             WHERE h.proc_num_proc != psnumeroproceso
               AND h.proc_num_proc = num_proc
               AND h.fec_fpro IS NULL
               AND pscodigotipodocumento = h.docu_cod_tipo_docu);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_UPDAT_DELET: ' || ls_sqlerrm);
  END sto_pr_updat_delet;

  /***************************************************************************
  Descripcion       : Marca los registros para ser eliminados
  Fecha Creacion    : 26/02/2013
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_updat_delet_onlin
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumlote             VARCHAR2,
    pssecnumedocu         VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
  BEGIN
  
    INSERT INTO sto_proce_docum_digit
      (sec_nume_docu,
       num_proc,
       num_lote,
       cod_tipo_docu,
       cod_pais)
    VALUES
      (pssecnumedocu,
       psnumeroproceso,
       psnumlote,
       pscodigotipodocumento,
       pscodigopais);
  
    UPDATE sto_docum_digit
       SET num_proc = psnumeroproceso
     WHERE sec_nume_docu = pssecnumedocu
       AND num_lote = psnumlote
       AND cod_tipo_docu = pscodigotipodocumento
       AND cod_pais = pscodigopais;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_UPDAT_DELET_ONLIN: ' ||
                              ls_sqlerrm);
  END sto_pr_updat_delet_onlin;
  /***************************************************************************
  Descripcion       : Borra la excepciones de un proceso
  Fecha Creacion    : 11/02/2013
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_delet_excep
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
    CURSOR c_registros(vscodigodocumentodetalle VARCHAR2) IS
      SELECT e.num_lote,
             e.sec_nume_docu,
             e.cod_tipo_docu,
             e.cod_vali
        FROM sto_docum_digit       d,
             sto_detal_docum_excep e
       WHERE d.cod_pais = e.cod_pais
         AND d.cod_tipo_docu = e.cod_tipo_docu
         AND d.num_lote = e.num_lote
         AND d.sec_nume_docu = e.sec_nume_docu
         AND e.ind_apro = '0'
         AND d.cod_tipo_docu IN
             (pscodigotipodocumento, vscodigodocumentodetalle)
         AND d.num_proc = psnumeroproceso;
  
    TYPE t_num_lote IS TABLE OF sto_detal_docum_excep.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF sto_detal_docum_excep.sec_nume_docu%TYPE;
    TYPE t_cod_tipo_docu IS TABLE OF sto_detal_docum_excep.cod_tipo_docu%TYPE;
    TYPE t_cod_vali IS TABLE OF sto_detal_docum_excep.cod_vali%TYPE;
  
    v_num_lote               t_num_lote;
    v_sec_nume_docu          t_sec_nume_docu;
    v_cod_tipo_docu          t_cod_tipo_docu;
    v_cod_vali               t_cod_vali;
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
    CURSOR c_pedidos(vnoidperiodoactual NUMBER) IS
    
      SELECT p.oid_soli_cabe
        FROM sto_docum_digit       s,
             int_solic_conso_cabec c,
             ped_solic_cabec       p
       WHERE c.sec_nume_docu = s.sec_nume_docu
         AND c.num_lote = s.num_lote
         AND p.oid_soli_cabe = c.soca_oid_soli_cabe_refe
         AND c.ind_ocs_proc = '0'
            --AND p.perd_oid_peri = vnoidperiodoactual
         AND p.grpr_oid_grup_proc IN (1, 3)
         AND s.num_proc = psnumeroproceso;
  
    TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE;
    v_oid_soli_cabe t_oid_soli_cabe;
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
    lnoidperiodoactual cra_perio.oid_peri%TYPE;
  
    lscodigoperiodo seg_perio_corpo.cod_peri%TYPE := NULL;
  
  BEGIN
  
    /*OBTIENE EL CODIGO DE DOCUMENTO DETALLE*/
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodocumento);
  
    /*ACTUALIZA LOS DOCUMENTOS A PROCESAR*/
  
    OPEN c_registros(lscodigodocumentodetalle);
    LOOP
      FETCH c_registros BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_cod_tipo_docu,
             v_cod_vali LIMIT w_filas;
    
      IF v_num_lote.count > 0 THEN
      
        /*ACTUALIZA LAS CABECERAS A EJECUTAR*/
        FORALL i IN 1 .. v_num_lote.count
          DELETE sto_detal_docum_excep d1
           WHERE d1.cod_pais = pscodigopais
             AND d1.num_lote = v_num_lote(i)
             AND d1.sec_nume_docu = v_sec_nume_docu(i)
             AND d1.cod_tipo_docu = v_cod_tipo_docu(i)
             AND d1.cod_vali = v_cod_vali(i);
      
      END IF;
      EXIT WHEN c_registros%NOTFOUND;
    
    END LOOP;
    CLOSE c_registros;
  
    IF pscodigotipodocumento = 'OCC' THEN
      SELECT cod_peri
        INTO lscodigoperiodo
        FROM bas_ctrl_fact c
       WHERE c.sta_camp = '0'
         AND c.ind_camp_act = '1';
    
      lnoidperiodoactual := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lscodigoperiodo);
    
      OPEN c_pedidos(lnoidperiodoactual);
      LOOP
        FETCH c_pedidos BULK COLLECT
          INTO v_oid_soli_cabe LIMIT rows;
        IF v_oid_soli_cabe.count > 0 THEN
          FORALL i IN 1 .. v_oid_soli_cabe.count
            DELETE FROM car_soli_entr_bloq a
             WHERE a.soca_oid_soli_cabe = v_oid_soli_cabe(i);
        
          FORALL i IN 1 .. v_oid_soli_cabe.count
            DELETE FROM ped_solic_posic a
             WHERE a.soca_oid_soli_cabe = v_oid_soli_cabe(i);
        
          FORALL i IN 1 .. v_oid_soli_cabe.count
            DELETE FROM ped_solic_mensa a
             WHERE a.soca_oid_soli_cabe = v_oid_soli_cabe(i);
        
          FORALL i IN 1 .. v_oid_soli_cabe.count
            DELETE FROM inc_solic_concu_punta a
             WHERE a.soca_oid_soli_cabe = v_oid_soli_cabe(i);
        
          FORALL i IN 1 .. v_oid_soli_cabe.count
            DELETE FROM inc_solic_concu_recom a
             WHERE a.soca_oid_soli_cabe = v_oid_soli_cabe(i);
        
          FORALL i IN 1 .. v_oid_soli_cabe.count
            DELETE FROM mav_solic_envio a
             WHERE a.soca_oid_soli_cabe = v_oid_soli_cabe(i);
        
         --ELIMINAMOS INFORMACION DE NUEVO MAV
          FOR i IN 1 .. v_oid_soli_cabe.count LOOP
            sto_pkg_proce_gener.sto_pr_elimi_mav_envio(v_oid_soli_cabe(i));
          END LOOP; 
        
          FORALL i IN 1 .. v_oid_soli_cabe.count
            DELETE FROM ped_solic_cabec a
             WHERE a.oid_soli_cabe = v_oid_soli_cabe(i);
        END IF;
        EXIT WHEN c_pedidos%NOTFOUND;
      END LOOP;
      CLOSE c_pedidos;
    
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR sto_pr_delet_Excep: ' || ls_sqlerrm);
  END sto_pr_delet_excep;
  /***************************************************************************
  Descripcion       : Avtualiza el valor de el ultimo numero de solicitud
  Fecha Creacion    : 23/06/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_updat_numer_solic(pscodigopais VARCHAR2
                                     
                                     ) IS
    lsnum_posi_nume_clie seg_pais.num_posi_nume_clie%TYPE;
    lscodigo_tmp         ped_numer_solic.val_ulti_nume_soli%TYPE;
  
    lsoidpais seg_pais.oid_pais%TYPE;
  BEGIN
  
    SELECT oid_pais
      INTO lsoidpais
      FROM seg_pais
     WHERE seg_pais.cod_pais = pscodigopais;
  
    SELECT num_posi_nume_clie
      INTO lsnum_posi_nume_clie
      FROM seg_pais
     WHERE oid_pais = lsoidpais;
  
    SELECT val_ulti_nume_soli + 1
      INTO lscodigo_tmp
      FROM ped_numer_solic
     WHERE val_oper = 'MAECLT'
       AND cod_pais = pscodigopais;
  
    UPDATE ped_numer_solic
       SET val_ulti_nume_soli = lscodigo_tmp
     WHERE val_oper = 'MAECLT'
       AND cod_pais = pscodigopais;
  
  END sto_pr_updat_numer_solic;
  /**************************************************************************
  Descripcion        : Devuelve el codigo de cliente generado
  Fecha Creacion     : 23/06/2008
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_codig_clien(pscodpais VARCHAR2) RETURN VARCHAR2 IS
  
    lsnum_posi_nume_clie seg_pais.num_posi_nume_clie%TYPE;
    lscodigo_tmp         ped_numer_solic.val_ulti_nume_soli%TYPE;
  
    lsmod_once mae_clien.oid_clie%TYPE;
  
    lscodigofinal int_solic_conso_credi.cod_clie%TYPE;
    lsoidpais     seg_pais.oid_pais%TYPE;
  
  BEGIN
  
    SELECT oid_pais
      INTO lsoidpais
      FROM seg_pais
     WHERE seg_pais.cod_pais = pscodpais;
  
    SELECT num_posi_nume_clie
      INTO lsnum_posi_nume_clie
      FROM seg_pais
     WHERE oid_pais = lsoidpais;
  
    SELECT val_ulti_nume_soli + 1
      INTO lscodigo_tmp
      FROM ped_numer_solic
     WHERE val_oper = 'MAECLT'
       AND cod_pais = pscodpais;
  
    lsmod_once    := sto_pkg_gener.sto_fn_devue_modul_once(lscodigo_tmp,
                                                           pscodpais);
    lscodigofinal := lscodigo_tmp || lsmod_once;
  
    lscodigofinal := lpad(lscodigofinal, lsnum_posi_nume_clie, '0');
  
    RETURN lscodigofinal;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_FN_DEVUE_CODIG_CLIEN: ' ||
                              ls_sqlerrm);
    
  END sto_fn_devue_codig_clien;

  /***************************************************************************
  Descripcion       : Registra el inicio de Validacion
  Fecha Creacion    : 09/03/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_updat_histo_valid
  (
    pscodigopais       VARCHAR2,
    pstipodocumento    VARCHAR2,
    pscodigovalidacion VARCHAR2,
    psnumeroproceso    VARCHAR2,
    psindicadorerror   VARCHAR2,
    pslogerror         VARCHAR2
    
  ) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  BEGIN
    UPDATE sto_histo_valid
       SET fec_ipro = decode(fec_ipro, NULL, SYSDATE, fec_ipro),
           fec_fpro = decode(fec_ipro, NULL, fec_fpro, SYSDATE),
           ind_loer = psindicadorerror,
           log_vali = pslogerror,
           reg_proc = decode(fec_ipro,
                             NULL,
                             0,
                             (SELECT COUNT(1)
                                FROM sto_docum_digit
                               WHERE cod_ulti_vali_ejec = pscodigovalidacion
                                 AND num_proc = psnumeroproceso
                                 AND cod_tipo_docu = pstipodocumento))
     WHERE cod_pais = pscodigopais
       AND cod_tipo_docu = pstipodocumento
       AND cod_vali = pscodigovalidacion
       AND num_proc = psnumeroproceso;
  
    COMMIT;
  
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_UPDAT_HISTO_VALID: ' ||
                              ls_sqlerrm);
  END sto_pr_updat_histo_valid;

  /***************************************************************************
  Descripcion       : Actualiza como rechazado los detalles que no fueron enviados
                      por uqe su cabecera ya fue enviada.
  Fecha Creacion    : 21/07/2008
  Autor             : José A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_regis_detal_sncab
  (
    pscodigopais            VARCHAR2,
    pstipodocumentocabecera VARCHAR2,
    pstipodocumentodetalle  VARCHAR2,
    psnumeroproceso         VARCHAR2
  ) IS
  
    CURSOR c_documaprob IS
      SELECT ocd.num_lote,
             ocd.sec_nume_docu
        FROM sto_docum_digit occ,
             sto_docum_digit ocd
       WHERE occ.cod_pais = pscodigopais
         AND occ.cod_tipo_docu = pstipodocumentocabecera
         AND ocd.cod_tipo_docu = pstipodocumentodetalle
         AND ocd.ind_envi = '0'
         AND ocd.ind_rech = '0'
         AND ocd.sec_nume_docu_cabe = occ.sec_nume_docu
         AND ocd.num_lote = occ.num_lote
         AND occ.cod_pais = ocd.cod_pais
         AND occ.ind_envi = '1'
         AND occ.num_proc = psnumeroproceso
         AND ocd.num_proc = psnumeroproceso;
  
    TYPE t_num_lote IS TABLE OF sto_detal_docum_excep.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF sto_detal_docum_excep.sec_nume_docu%TYPE;
  
    v_num_lote      t_num_lote;
    v_sec_nume_docu t_sec_nume_docu;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
  
    j BINARY_INTEGER := 0;
  
  BEGIN
  
    OPEN c_documaprob;
    LOOP
      FETCH c_documaprob BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu LIMIT rows;
    
      IF v_num_lote.count > 0 THEN
      
        FORALL j IN 1 .. v_num_lote.count
          UPDATE sto_docum_digit occ
             SET occ.ind_rech = '1',
                 occ.fec_modi = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pstipodocumentodetalle
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);
      
      END IF;
      EXIT WHEN c_documaprob%NOTFOUND;
    END LOOP;
    CLOSE c_documaprob;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_REGIS_DETAL_SNCAB: ' ||
                              ls_sqlerrm);
  END sto_pr_regis_detal_sncab;
  /**************************************************************************
  Descripcion       : Devuel el codigo de Tipo de documento apartir del codigo de Interfaz
  Fecha Creacion    : 26/05/2008
  Parametros Entrada:
      psCodigoPais   : Codigo de pais
      psCodigoSistema   : Codigo de parametro
      psCodigoInterfaz   : Codigo de parametro
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_tipo_docum_byint
  (
    pscodigopais     VARCHAR2,
    pscodigosistema  VARCHAR2,
    pscodigointerfaz VARCHAR2,
    psindcabecera    VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lscodigo VARCHAR2(15);
  
  BEGIN
  
    SELECT decode(psindcabecera, '1', t.cod_tipo_docu, t.cod_tipo_docu_cabe)
      INTO lscodigo
      FROM sto_tipo_docum_digit t
     WHERE t.inte_cod_inte = pscodigointerfaz
       AND t.sist_cod_sist = pscodigosistema
       AND t.cod_pais = pscodigopais;
  
    RETURN lscodigo;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1500);
      raise_application_error(-20123,
                              'ERROR STO_FN_DEVUE_TIPO_DOCUM_BYINT: ' ||
                              ls_sqlerrm);
    
  END sto_fn_devue_tipo_docum_byint;

  /**************************************************************************
  Descripcion       : Devuelve el Codigo de Mensaje
   Fecha Creacion    : 13/08/2008
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_codig_mensa
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigovalidacion    VARCHAR2
  ) RETURN NUMBER IS
  
    lnvalor NUMBER;
  
  BEGIN
  
    SELECT cod_mens
      INTO lnvalor
      FROM sto_mensa_valid
     WHERE cod_pais = pscodigopais
       AND cod_vali = pscodigovalidacion
       AND cod_tipo_docu = pscodigotipodocumento
       AND rownum = 1;
  
    RETURN lnvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
    
  END sto_fn_devue_codig_mensa;

  /**************************************************************************
  Descripcion       : Devuelve el valor de Mensaje
   Fecha Creacion    : 13/08/2008
  Autor             : Jose Cairampoma
  
       pstipomensaje = 'C'  devuelve des_cort_mens
       pstipomensaje = 'L'  devuelve des_larg_mens
       pstipomensaje = 'W'  devuelve des_web_mens
  
  ***************************************************************************/
  FUNCTION sto_fn_devue_valor_mensa
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigovalidacion    VARCHAR2,
    pstipomensaje         VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lsvalor VARCHAR2(100);
  
  BEGIN
  
    ---- Ojo, esto devuelve el valor de mensaje WEB
  
    SELECT decode(pstipomensaje,
                  'W',
                  des_web_mens,
                  'L',
                  des_larg_mens,
                  des_cort_mens)
      INTO lsvalor
      FROM sto_mensa_valid
     WHERE cod_pais = pscodigopais
       AND cod_vali = pscodigovalidacion
       AND cod_tipo_docu = pscodigotipodocumento
       AND rownum = 1;
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
    
  END sto_fn_devue_valor_mensa;

  /**************************************************************************
  Descripcion       : Devuelve el Codigo de Pais por Compañia y Pais OCR
    Fecha Creacion    : 13/08/2008
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_codig_pais_bycia
  (
    pscodigopaisocr VARCHAR2,
    pscodigocia     VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lsvalor VARCHAR2(3);
  
  BEGIN
    SELECT cod_pais
      INTO lsvalor
      FROM bas_pais_compa
     WHERE cod_pais_ocr = pscodigopaisocr
       AND cod_comp = pscodigocia;
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
    
  END sto_fn_devue_codig_pais_bycia;
  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla DOCUM_DIGIT los
                      documentos a validar q cumplan los filtros APROB-LEVAN
                      return 1 y actulizamos en la tabla STO_DETAL_DOCUM_EXCEP
  
  
  Fecha Creacion    : 27/08/2008
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_aprob
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    psnumeroproceso       VARCHAR2,
    psusuario             VARCHAR2
    
  ) IS
  
    CURSOR c_documaprob IS
      SELECT sdde.num_lote,
             sdde.sec_nume_docu
        FROM sto_detal_docum_excep sdde,
             sto_docum_digit       std
       WHERE sdde.cod_pais = pscodigopais
         AND sdde.cod_tipo_docu = pscodigotipodocumento
         AND sdde.cod_pais = std.cod_pais
         AND sdde.cod_tipo_docu = std.cod_tipo_docu
         AND sdde.num_lote = std.num_lote
         AND sdde.sec_nume_docu = std.sec_nume_docu
         AND sdde.cod_vali = pscodigovalidactual
         AND ((pscodigovalidanterior IS NULL AND
             std.cod_ulti_vali_exit IS NULL) OR
             (std.cod_ulti_vali_ejec = pscodigovalidanterior AND
             std.cod_ulti_vali_exit = std.cod_ulti_vali_ejec))
         AND sdde.ind_apro = '1'
         AND std.ind_envi = '0'
         AND std.ind_rech = '0'
         AND std.num_proc = psnumeroproceso;
  
    TYPE t_num_lote IS TABLE OF sto_detal_docum_excep.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF sto_detal_docum_excep.sec_nume_docu%TYPE;
  
    v_num_lote      t_num_lote;
    v_sec_nume_docu t_sec_nume_docu;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
  
    j BINARY_INTEGER := 0;
  
  BEGIN
  
    OPEN c_documaprob;
    LOOP
      FETCH c_documaprob BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu LIMIT rows;
    
      IF v_sec_nume_docu.count > 0 THEN
      
        -- Actualziamos Cabeceras
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodocumento
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j)
             AND occ.ind_envi = '0'
             AND occ.ind_rech = '0';
      
      END IF;
      EXIT WHEN c_documaprob%NOTFOUND;
    END LOOP;
    CLOSE c_documaprob;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'STO_PR_GENER_APROB:' || ls_sqlerrm);
    
  END sto_pr_gener_aprob;

  /**************************************************************************
  Descripcion       : Obteniene el numero de lote del documento
  Fecha Creacion    : 20/10/2011
  Parametros Entrada:
                      psCodigoPais   Código Pais
                      pstipodocu     Codigo Tipo Documento
                      psnumlote      Lote
  Autor             : Jose A. Cairampoma G.
  ***************************************************************************/
  PROCEDURE sto_pr_devue_lote
  (
    pscodigopais VARCHAR2,
    pstipodocu   VARCHAR2,
    psnumlote    OUT VARCHAR2
  ) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  
  BEGIN
  
    UPDATE sto_tipo_docum_digit a
       SET a.num_lote = lpad(nvl(a.num_lote, 0) + 1, 8, '0')
     WHERE a.cod_pais = pscodigopais
       AND a.cod_tipo_docu = pstipodocu
    RETURNING a.num_lote INTO psnumlote;
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'STO_PR_DEVUE_LOTE:' || ls_sqlerrm);
    
  END sto_pr_devue_lote;

  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla STO_TIPO_DOCUM_DIGIT EL NUMERO DE LOTE
            SEGUN EL TIPO DE DOCUMENTO
  
  
  Fecha Creacion    : 01/09/2008
  Parametros Entrada:
  
                      psCodigoPais          Código Pais
                      psDocuCodTipoDocu     Codigo Tipo Documento
                      psIndicador           Indicador
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_nume_lote
  (
    pscodigopais VARCHAR2,
    pstipodocu   VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lsnumlote          sto_tipo_docum_digit.num_lote%TYPE;
    lsindcabecera      sto_tipo_docum_digit.ind_cabe%TYPE;
    lstipodocucabecera sto_tipo_docum_digit.cod_tipo_docu_cabe%TYPE;
    lscodtipodocu      sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
  BEGIN
  
    SELECT a.cod_tipo_docu,
           nvl(a.num_lote, 0),
           a.ind_cabe,
           a.cod_tipo_docu_cabe
      INTO lscodtipodocu,
           lsnumlote,
           lsindcabecera,
           lstipodocucabecera
      FROM sto_tipo_docum_digit a
     WHERE a.cod_pais = pscodigopais
       AND a.cod_tipo_docu = pstipodocu;
  
    IF (lsindcabecera = '0') THEN
    
      SELECT nvl(a.num_lote, 0),
             a.cod_tipo_docu
        INTO lsnumlote,
             lscodtipodocu
        FROM sto_tipo_docum_digit a
       WHERE a.cod_pais = pscodigopais
         AND a.cod_tipo_docu = lstipodocucabecera;
    END IF;
  
    RETURN lsnumlote;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
    
  END sto_fn_devue_nume_lote;

  /**************************************************************************
  Descripcion       : Actualiza el numero de lote sumandole 1
  
  Fecha Creacion    : 01/09/2008
  Parametros Entrada:
  
                      psCodigoPais          Código Pais
                      psDocuCodTipoDocu     Codigo Tipo Documento
  
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_updat_nume_lote
  (
    pscodigopais VARCHAR2,
    pstipodocu   VARCHAR2
  ) IS
  
    lsnumlote          sto_tipo_docum_digit.num_lote%TYPE;
    lsindcabecera      sto_tipo_docum_digit.ind_cabe%TYPE;
    lstipodocucabecera sto_tipo_docum_digit.cod_tipo_docu_cabe%TYPE;
    lscodtipodocu      sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  
  BEGIN
  
    SELECT a.cod_tipo_docu,
           nvl(a.num_lote, 0),
           a.ind_cabe,
           a.cod_tipo_docu_cabe
      INTO lscodtipodocu,
           lsnumlote,
           lsindcabecera,
           lstipodocucabecera
      FROM sto_tipo_docum_digit a
     WHERE a.cod_pais = pscodigopais
       AND a.cod_tipo_docu = pstipodocu;
  
    IF (lsindcabecera = '0') THEN
    
      SELECT nvl(a.num_lote, 0),
             a.cod_tipo_docu
        INTO lsnumlote,
             lscodtipodocu
        FROM sto_tipo_docum_digit a
       WHERE a.cod_pais = pscodigopais
         AND a.cod_tipo_docu = lstipodocucabecera;
    END IF;
  
    UPDATE sto_tipo_docum_digit a
       SET a.num_lote = lpad(lsnumlote + 1, 8, 0),
           a.fec_modi = SYSDATE
     WHERE a.cod_pais = pscodigopais
       AND a.cod_tipo_docu = lscodtipodocu;
  
    RETURN;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'STO_PR_UPDAT_NUME_LOTE:' || ls_sqlerrm);
    
  END sto_pr_updat_nume_lote;

  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla STO_TIPO_DOCUM_DIGIT EL codigo del
            documento
  
  
  Fecha Creacion    : 01/09/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      pscodinterfaz     Codigo de interfaz
  
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_codi_docu
  (
    pscodigopais  VARCHAR2,
    pscodinterfaz VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lsvalor VARCHAR2(10);
  BEGIN
  
    SELECT DISTINCT a.cod_tipo_docu
      INTO lsvalor
      FROM sto_tipo_docum_digit a,
           sto_tipo_docum_digit b
     WHERE (a.inte_cod_inte = pscodinterfaz AND a.ind_cabe = '1' AND
           a.cod_pais = pscodigopais)
        OR (b.ind_cabe = '0' AND b.inte_cod_inte = pscodinterfaz AND
           a.cod_tipo_docu = b.cod_tipo_docu_cabe AND a.ind_cabe = '1');
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
    
  END sto_fn_devue_codi_docu;

  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla int_solic_conso_poven_detal EL codigo de
            operacion
  
  
  Fecha Creacion    : 12/10/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      psnunlote         numero de lote
            psnumsecuencia    numero de documento
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_codi_oper
  (
    pscodigopais   VARCHAR2,
    psnunlote      VARCHAR2,
    psnumsecuencia NUMBER
  ) RETURN VARCHAR2 IS
  
    lsvalor VARCHAR(5);
  BEGIN
  
    SELECT a.cod_oper
      INTO lsvalor
      FROM int_solic_conso_poven_detal a
     WHERE a.cod_pais = pscodigopais
       AND a.num_lote = psnunlote
       AND a.sec_nume_docu = psnumsecuencia;
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
  END sto_fn_devue_codi_oper;
  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla int_solic_conso_poven_detal el producto
              de VAL_PREC_CATA_DEVU y CAN_VENT_DEVU
  
  
  Fecha Creacion    : 22/09/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      psperiodo         periodo
            pscliente       cliente
            psnumsecuencia    numero de secuencia
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_produ_canti
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    pscliente       VARCHAR2,
    psnumdocu       NUMBER,
    pstipodocumento VARCHAR2
  )
  
   RETURN NUMBER IS
    lsvalor NUMBER;
  BEGIN
  
    SELECT SUM(det.val_prec_cata_devu * det.can_vent_devu)
      INTO lsvalor
    
      FROM int_solic_conso_poven_cabec cab,
           int_solic_conso_poven_detal det,
           ped_solic_cabec             ped
     WHERE cab.oid_cabe = ped.oid_soli_cabe
       AND cab.cod_pais = det.cod_pais
       AND cab.cod_peri = det.cod_peri
       AND cab.cod_clie = det.cod_clie
       AND cab.num_docu = det.num_docu
       AND det.cod_peri = psperiodo
       AND det.cod_clie = pscliente
       AND det.num_docu = psnumdocu
       AND cab.cod_pais = pscodigopais
       AND det.docu_cod_tipo_docu = pstipodocumento;
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
    WHEN OTHERS THEN
      RETURN 0;
  END sto_fn_devue_produ_canti;

  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla int_solic_conso_poven_detal la suma de  CAN_VENT_DEVU
  
  
  Fecha Creacion    : 22/09/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      psperiodo         periodo
            pscliente       cliente
            psnumsecuencia    numero de secuencia
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_venta_devue
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    pscliente       VARCHAR2,
    psnumsecuencia  NUMBER,
    pstipodocumento VARCHAR2
  ) RETURN NUMBER IS
    lsvalor NUMBER;
  BEGIN
  
    SELECT SUM(det.can_vent_devu)
      INTO lsvalor
    
      FROM int_solic_conso_poven_cabec cab,
           int_solic_conso_poven_detal det,
           ped_solic_cabec             ped
     WHERE cab.oid_cabe = ped.oid_soli_cabe
       AND cab.cod_pais = det.cod_pais
       AND cab.cod_peri = det.cod_peri
       AND cab.cod_clie = det.cod_clie
       AND cab.num_docu = det.num_docu
       AND det.cod_peri = psperiodo
       AND det.cod_clie = pscliente
       AND det.num_docu = psnumsecuencia
       AND cab.cod_pais = pscodigopais
       AND det.docu_cod_tipo_docu = pstipodocumento;
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
    WHEN OTHERS THEN
      RETURN 0;
  END sto_fn_devue_venta_devue;

  /**************************************************************************
  Descripcion       : Inidica so exite el codigo de venta
  
  Fecha Creacion    : 26/09/2008
  Parametros Entrada:
  
  
                       pscodigoventa
             psoidperiodoreferencia
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_indic_exist_coven(pscodigoventa VARCHAR2,
                                          
                                          psoidperiodoreferencia NUMBER
                                          
                                          ) RETURN NUMBER IS
    lsvalor NUMBER;
  BEGIN
  
    SELECT COUNT(1)
      INTO lsvalor
      FROM int_solic_conso_poven_cabec
     WHERE to_number(pscodigoventa) IN
           (SELECT DISTINCT to_number(val_codi_vent)
              FROM (SELECT val_codi_vent,
                           ofer_oid_ofer
                      FROM pre_matri_factu_cabec a,
                           pre_ofert             b,
                           pre_ofert_detal       c,
                           pre_estra             d,
                           pre_tipo_estra        e
                     WHERE a.perd_oid_peri = psoidperiodoreferencia
                       AND a.oid_cabe = b.mfca_oid_cabe
                       AND b.oid_ofer = c.ofer_oid_ofer
                       AND b.coes_oid_estr = d.oid_estr
                       AND d.ties_oid_tipo_estr = e.oid_tipo_estr
                       AND e.cod_tipo_estr IN (2, 6)
                       AND c.ind_impr_gp = 1
                    
                    UNION
                    
                    SELECT val_codi_vent,
                           ofer_oid_ofer
                      FROM pre_matri_factu_cabec a,
                           pre_ofert             b,
                           pre_ofert_detal       c,
                           pre_estra             d,
                           pre_tipo_estra        e
                     WHERE a.perd_oid_peri = psoidperiodoreferencia
                       AND a.oid_cabe = b.mfca_oid_cabe
                       AND b.oid_ofer = c.ofer_oid_ofer
                       AND b.coes_oid_estr = d.oid_estr
                       AND d.ties_oid_tipo_estr = e.oid_tipo_estr
                       AND e.cod_tipo_estr NOT IN (2, 6)
                       AND c.ind_impr_gp = 1
                       AND c.ofer_oid_ofer IN
                           (SELECT ofer_oid_ofer
                              FROM pre_ofert_detal,
                                   pre_ofert
                             WHERE oid_ofer = ofer_oid_ofer
                               AND mfca_oid_cabe = b.mfca_oid_cabe
                               AND tofe_oid_tipo_ofer IN (2040))));
  
    IF (lsvalor > 0) THEN
      lsvalor := 1;
    END IF;
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
    WHEN OTHERS THEN
      RETURN 0;
    
  END sto_fn_devue_indic_exist_coven;
  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla STO_TIPO_DOCUM_DIGIT el indicador de activo STO
  
  
  Fecha Creacion    : 29/09/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      pstipodocumento   Codigo de Documento
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_indic_activ_sto
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lsvalor VARCHAR(1);
  BEGIN
  
    SELECT a.ind_sto_acti
      INTO lsvalor
      FROM sto_tipo_docum_digit a
     WHERE a.cod_pais = pscodigopais
       AND a.cod_tipo_docu = pstipodocumento;
  
    IF (lsvalor IS NULL) THEN
      lsvalor := '0';
    END IF;
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
    WHEN OTHERS THEN
      RETURN 0;
  END sto_fn_devue_indic_activ_sto;

  /**************************************************************************
  Descripcion       : Inidica la oferta que corresponde al codigo de venta
  
  Fecha Creacion    : 15/10/2008
  Parametros Entrada: pscodigoventa
                      psoidperiodoreferencia
  
  Autor             : Arturo Blumen
  ***************************************************************************/
  FUNCTION sto_fn_devue_ofert_coven(pscodigoventa VARCHAR2,
                                    
                                    psoidperiodoreferencia NUMBER
                                    
                                    ) RETURN NUMBER IS
    lsvalor NUMBER;
  BEGIN
  
    SELECT DISTINCT ofer_oid_ofer
      INTO lsvalor
      FROM (SELECT val_codi_vent,
                   ofer_oid_ofer
              FROM pre_matri_factu_cabec a,
                   pre_ofert             b,
                   pre_ofert_detal       c,
                   pre_estra             d,
                   pre_tipo_estra        e
             WHERE a.perd_oid_peri = psoidperiodoreferencia
               AND a.oid_cabe = b.mfca_oid_cabe
               AND b.oid_ofer = c.ofer_oid_ofer
               AND b.coes_oid_estr = d.oid_estr
               AND d.ties_oid_tipo_estr = e.oid_tipo_estr
               AND e.cod_tipo_estr IN (2, 6)
               AND c.ind_impr_gp = 1
               AND to_number(val_codi_vent) = to_number(pscodigoventa)
            
            UNION
            
            SELECT val_codi_vent,
                   ofer_oid_ofer
              FROM pre_matri_factu_cabec a,
                   pre_ofert             b,
                   pre_ofert_detal       c,
                   pre_estra             d,
                   pre_tipo_estra        e
             WHERE a.perd_oid_peri = psoidperiodoreferencia
               AND a.oid_cabe = b.mfca_oid_cabe
               AND b.oid_ofer = c.ofer_oid_ofer
               AND b.coes_oid_estr = d.oid_estr
               AND d.ties_oid_tipo_estr = e.oid_tipo_estr
               AND e.cod_tipo_estr NOT IN (2, 6)
               AND c.ind_impr_gp = 1
               AND to_number(val_codi_vent) = to_number(pscodigoventa)
               AND c.ofer_oid_ofer IN
                   (SELECT ofer_oid_ofer
                      FROM pre_ofert_detal,
                           pre_ofert
                     WHERE oid_ofer = ofer_oid_ofer
                       AND mfca_oid_cabe = b.mfca_oid_cabe
                       AND tofe_oid_tipo_ofer IN (2040)));
    RETURN lsvalor;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
  END sto_fn_devue_ofert_coven;
  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla int_solic_conso_poven_cabec numero de documento
              de cruce
  
  
  Fecha Creacion    : 27/10/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      psnunlote         numero de lote
            psnumsecuencia    numero de secuencia
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_docu_cruc
  (
    pscodigopais        VARCHAR2,
    pscodigodocumento   VARCHAR2,
    psnunlote           VARCHAR2,
    psnumsecuencia      NUMBER,
    psnumsecuenciapadre NUMBER
  ) RETURN VARCHAR2 IS
  
    lsvalor VARCHAR(10);
  
  BEGIN
  
    IF (pscodigodocumento = 'SPVC') THEN
      SELECT a.num_docu_cruc
        INTO lsvalor
        FROM int_solic_conso_poven_cabec a
       WHERE a.cod_pais = pscodigopais
         AND a.num_lote = psnunlote
         AND a.sec_nume_docu = psnumsecuencia;
    ELSE
      SELECT a.num_docu_cruc
        INTO lsvalor
        FROM int_solic_conso_poven_cabec a
       WHERE a.cod_pais = pscodigopais
         AND a.num_lote = psnunlote
         AND a.sec_nume_docu = psnumsecuenciapadre;
    
    END IF;
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
  END sto_fn_devue_docu_cruc;

  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla int_solic_conso_poven_cabec el periodo de
              referencia
  
  
  Fecha Creacion    : 27/10/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      psnunlote         numero de lote
            psnumsecuencia    numero de secuencia
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_peri_refe
  (
    pscodigopais        VARCHAR2,
    pscodigodocumento   VARCHAR2,
    psnunlote           VARCHAR2,
    psnumsecuencia      NUMBER,
    psnumsecuenciapadre NUMBER
  ) RETURN VARCHAR2 IS
  
    lsvalor VARCHAR(12);
  
  BEGIN
  
    IF (pscodigodocumento = 'SPVC') THEN
      SELECT a.oid_peri_refe
        INTO lsvalor
        FROM int_solic_conso_poven_cabec a
       WHERE a.cod_pais = pscodigopais
         AND a.num_lote = psnunlote
         AND a.sec_nume_docu = psnumsecuencia;
    ELSE
      SELECT a.oid_peri_refe
        INTO lsvalor
        FROM int_solic_conso_poven_cabec a
       WHERE a.cod_pais = pscodigopais
         AND a.num_lote = psnunlote
         AND a.sec_nume_docu = psnumsecuenciapadre;
    
    END IF;
  
    RETURN gen_pkg_gener.gen_fn_devuelve_des_perio(lsvalor);
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
  END sto_fn_devue_peri_refe;

  /**************************************************************************
  Descripcion       : Inserta en la tabla STO_TMP_DOCUM_DIGIT todos los
                      registra aptos para validarse de la tabla STO_DOCUM_IDGIT
  
  Fecha Creacion    : 06/10/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_regis_docum_tempo_aptos
  (
    pscodigopais          VARCHAR2,
    psdocucodtipodocu     VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
  BEGIN
  
    IF psdocucodtipodocu = 'OCD' OR psdocucodtipodocu = 'OCC' THEN
      DELETE sto_proce_docum_digit
       WHERE num_proc = psnumeroproceso
         AND cod_tipo_docu = psdocucodtipodocu;
      INSERT INTO sto_proce_docum_digit
        (sec_nume_docu,
         num_proc,
         num_lote,
         cod_tipo_docu,
         cod_pais,
         sec_nume_docu_cabe,
         cod_clie,
         cod_peri)
        SELECT occ.sec_nume_docu,
               occ.num_proc,
               occ.num_lote,
               occ.cod_tipo_docu,
               occ.cod_pais,
               occ.sec_nume_docu_cabe,
               occ.cod_clie,
               cod_peri
          FROM sto_docum_digit occ
         WHERE psnumeroproceso = occ.num_proc
           AND occ.cod_tipo_docu = psdocucodtipodocu
           AND occ.cod_pais = pscodigopais
           AND occ.ind_envi = '0'
           AND occ.ind_rech = '0'
           AND ((pscodigovalidanterior IS NULL AND
               occ.cod_ulti_vali_ejec IS NULL) OR
               (occ.cod_ulti_vali_ejec = occ.cod_ulti_vali_exit AND
               occ.cod_ulti_vali_exit = pscodigovalidanterior));
    
    ELSE
    
      DELETE sto_tmp_docum_digit;
    
      INSERT INTO sto_tmp_docum_digit
        (sec_nume_docu,
         num_proc,
         num_lote,
         cod_tipo_docu,
         cod_pais,
         sec_nume_docu_cabe,
         cod_clie,
         cod_peri)
        SELECT occ.sec_nume_docu,
               occ.num_proc,
               occ.num_lote,
               occ.cod_tipo_docu,
               occ.cod_pais,
               occ.sec_nume_docu_cabe,
               occ.cod_clie,
               cod_peri
          FROM sto_docum_digit occ
         WHERE psnumeroproceso = occ.num_proc
           AND occ.cod_tipo_docu = psdocucodtipodocu
           AND occ.cod_pais = pscodigopais
           AND occ.ind_envi = '0'
           AND occ.ind_rech = '0'
           AND ((pscodigovalidanterior IS NULL) OR
               (occ.cod_ulti_vali_exit = occ.cod_ulti_vali_exit AND
               occ.cod_ulti_vali_exit = pscodigovalidanterior));
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_REGIS_DOCUM_TEMPO_APTOS: ' ||
                              ls_sqlerrm);
  END sto_pr_regis_docum_tempo_aptos;

  /**************************************************************************
  Descripcion       : Inserta en la tabla STO_TMP_DOCUM_DIGIT todos los
                      registra aptos para enviarse a SICC
                      de la tabla STO_DOCUM_IDGIT
  Fecha Creacion    : 01/07/2014
  Autor             : Jose Antonio Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_regis_docum_tempo_envio
  (
    pscodigopais     VARCHAR2,
    pscodigotipodocu VARCHAR2,
    psnumeroproceso  VARCHAR2
  ) IS
  
    CURSOR cur_sto(vsultimavalidacion VARCHAR2) IS
      SELECT occ.sec_nume_docu,
             occ.num_proc,
             occ.num_lote,
             occ.cod_tipo_docu,
             occ.cod_pais,
             occ.sec_nume_docu_cabe,
             occ.cod_clie,
             occ.cod_peri
        FROM sto_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.cod_tipo_docu = pscodigotipodocu
         AND occ.cod_pais = pscodigopais
         AND occ.ind_envi = '0'
         AND occ.ind_rech = '0'
         AND occ.cod_ulti_vali_ejec = vsultimavalidacion
         AND occ.cod_ulti_vali_erro IS NULL;
  
    TYPE sto_tab_t IS TABLE OF sto_proce_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t;
  
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
    lsultimavalidcabecera    sto_param_valid.cod_vali%TYPE;
    lsultimavaliddetalle     sto_param_valid.cod_vali%TYPE;
  
    rows NATURAL := 5000;
  
    lnindicadorerror NUMBER;
  BEGIN
    dbms_application_info.set_module(module_name => 'STO - ' ||
                                                    pscodigotipodocu ||
                                                    ' - ' ||
                                                    psnumeroproceso,
                                     action_name => 'ENVIO');
  
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodocu);
    lsultimavalidcabecera    := sto_pkg_gener.sto_fn_devue_ultim_valid_ejecu(pscodigopais,
                                                                             pscodigotipodocu,
                                                                             psnumeroproceso);
    lsultimavaliddetalle     := sto_pkg_gener.sto_fn_devue_ultim_valid_ejecu(pscodigopais,
                                                                             lscodigodocumentodetalle,
                                                                             psnumeroproceso);
  
    IF pscodigotipodocu = 'OCC' THEN
      DELETE sto_proce_docum_digit
       WHERE num_proc = psnumeroproceso
         AND cod_tipo_docu IN (pscodigotipodocu, lscodigodocumentodetalle);
    ELSE
      DELETE sto_tmp_docum_digit;
    
    END IF;
    /*INSERTANDO Y BORRANDO CABECERAS PEDIDOS EN HISTORICO*/
    OPEN cur_sto(lsultimavalidcabecera);
    LOOP
    
      FETCH cur_sto BULK COLLECT
        INTO sto_tab LIMIT rows;
      EXIT WHEN sto_tab.count = 0;
      IF pscodigotipodocu = 'OCC' THEN
        FORALL i IN sto_tab.first .. sto_tab.last
          INSERT INTO sto_proce_docum_digit VALUES sto_tab (i);
      
        FORALL i IN sto_tab.first .. sto_tab.last
          INSERT INTO sto_proce_docum_digit
            SELECT occ.sec_nume_docu,
                   occ.num_proc,
                   occ.num_lote,
                   occ.cod_tipo_docu,
                   occ.cod_pais,
                   occ.sec_nume_docu_cabe,
                   occ.cod_clie,
                   occ.cod_peri
              FROM sto_docum_digit occ
             WHERE occ.num_proc = psnumeroproceso
               AND occ.cod_tipo_docu = lscodigodocumentodetalle
               AND occ.cod_pais = pscodigopais
               AND occ.ind_envi = '0'
               AND occ.ind_rech = '0'
               AND occ.cod_ulti_vali_ejec = lsultimavaliddetalle
               AND occ.cod_ulti_vali_erro IS NULL
               AND occ.sec_nume_docu_cabe = sto_tab(i).sec_nume_docu
               AND occ.num_lote = sto_tab(i).num_lote;
      
      ELSE
        FORALL i IN sto_tab.first .. sto_tab.last
          INSERT INTO sto_tmp_docum_digit VALUES sto_tab (i);
      
        IF lscodigodocumentodetalle IS NOT NULL THEN
          FORALL i IN sto_tab.first .. sto_tab.last
            INSERT INTO sto_tmp_docum_digit
              SELECT occ.sec_nume_docu,
                     occ.num_proc,
                     occ.num_lote,
                     occ.cod_tipo_docu,
                     occ.cod_pais,
                     occ.sec_nume_docu_cabe,
                     occ.cod_clie,
                     occ.cod_peri
                FROM sto_docum_digit occ
               WHERE occ.num_proc = psnumeroproceso
                 AND occ.cod_tipo_docu = lscodigodocumentodetalle
                 AND occ.cod_pais = pscodigopais
                 AND occ.ind_envi = '0'
                 AND occ.ind_rech = '0'
                 AND occ.cod_ulti_vali_ejec = lsultimavaliddetalle
                 AND occ.cod_ulti_vali_erro IS NULL
                 AND occ.sec_nume_docu_cabe = sto_tab(i).sec_nume_docu
                 AND occ.num_lote = sto_tab(i).num_lote;
        END IF;
      
      END IF;
    
      FOR i IN sto_tab.first .. sto_tab.last
      LOOP
      
        SELECT COUNT(1)
          INTO lnindicadorerror
          FROM sto_detal_docum_excep dd
         WHERE dd.sec_nume_docu = sto_tab(i).sec_nume_docu
           AND dd.num_lote = sto_tab(i).num_lote
           AND dd.cod_tipo_docu = pscodigotipodocu
           AND dd.cod_pais = pscodigopais
           AND dd.ind_apro = '0';
      
        IF lnindicadorerror = 0 THEN
        
          UPDATE sto_docum_digit occ
             SET occ.ind_envi = '1',
                 occ.fec_modi = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodocu
             AND occ.num_lote = sto_tab(i).num_lote
             AND occ.sec_nume_docu = sto_tab(i).sec_nume_docu
             AND occ.num_proc = psnumeroproceso;
        
          IF lscodigodocumentodetalle IS NOT NULL THEN
            UPDATE sto_docum_digit occ
               SET ind_envi = nvl2(occ.cod_ulti_vali_erro, '0', '1'),
                   ind_rech = nvl2(occ.cod_ulti_vali_erro, '1', '0'),
                   fec_modi = SYSDATE
             WHERE occ.num_proc = psnumeroproceso
               AND occ.cod_tipo_docu = lscodigodocumentodetalle
               AND occ.cod_pais = pscodigopais
               AND occ.ind_envi = '0'
               AND occ.ind_rech = '0'
               AND occ.sec_nume_docu_cabe = sto_tab(i).sec_nume_docu
               AND occ.num_lote = sto_tab(i).num_lote;
          END IF;
        END IF;
      END LOOP;
    
    END LOOP;
    CLOSE cur_sto;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR sto_pr_regis_docum_tempo_envio: ' ||
                              ls_sqlerrm);
  END sto_pr_regis_docum_tempo_envio;
  /**************************************************************************
  Descripcion       : Proceso Ejecutado despues de el Envio de documentos Validos
  Fecha Creacion    : 17/05/2012
  Autor             : Jose Antonio Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_after_envio
  (
    pscodigopais     VARCHAR2,
    pscodigotipodocu VARCHAR2,
    psnumeroproceso  VARCHAR2
  ) IS
  
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
    lsindejecstdtmpproc      bas_param_pais.cod_para%TYPE;
  BEGIN
  
    lsindejecstdtmpproc := gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                           'STO',
                                                           '007');
    IF lsindejecstdtmpproc = 'S' AND pscodigotipodocu = 'OCC' THEN
      lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                               pscodigotipodocu);
    
      DELETE sto_proce_docum_digit
       WHERE num_proc = psnumeroproceso
         AND cod_tipo_docu IN (pscodigotipodocu, lscodigodocumentodetalle);
    
      INSERT INTO sto_proce_docum_digit
        (sec_nume_docu,
         num_proc,
         num_lote,
         cod_tipo_docu,
         cod_pais,
         sec_nume_docu_cabe,
         cod_clie)
        SELECT occ.sec_nume_docu,
               occ.num_proc,
               occ.num_lote,
               occ.cod_tipo_docu,
               occ.cod_pais,
               occ.sec_nume_docu_cabe,
               occ.cod_clie
          FROM sto_docum_digit occ
         WHERE occ.num_proc = psnumeroproceso
           AND occ.cod_tipo_docu = pscodigotipodocu
           AND occ.cod_pais = pscodigopais;
    END IF;
  
    dbms_application_info.set_module(module_name => NULL,
                                     action_name => NULL);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_AFTER_ENVIO: ' || ls_sqlerrm);
  END sto_pr_after_envio;

  /***************************************************************************
  Descripcion       : Agrega Mensajes de Error para un documento de STO
  Fecha Creacion    : 13/10/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_add_mensa_error
  (
    pnsecnumedocu NUMBER,
    psnumlote     VARCHAR2,
    psmensaje     VARCHAR2
  ) IS
  
  BEGIN
  
    INSERT INTO sto_tmp_mensa_valid
      (sec_nume_docu,
       num_lote,
       val_obse_mens)
    VALUES
      (pnsecnumedocu,
       psnumlote,
       psmensaje);
  
  EXCEPTION
    WHEN dup_val_on_index THEN
    
      UPDATE sto_tmp_mensa_valid
         SET val_obse_mens = val_obse_mens || '/' || psmensaje
       WHERE sec_nume_docu = pnsecnumedocu
         AND num_lote = psnumlote;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'STO_PR_ADD_MENSA_ERROR:' || ls_sqlerrm);
    
  END sto_pr_add_mensa_error;

  /***************************************************************************
  Descripcion       : Validación generica de  error para Documentos STO
  Fecha Creacion    : 13/10/2008
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_error
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_errorins IS
      SELECT occ.num_lote,
             occ.sec_nume_docu,
             occ.cod_ulti_vali_erro,
             occ.cod_ulti_vali_exit,
             (SELECT MIN('S')
                FROM sto_combi_orige_docum co,
                     sto_orige_docum       o
               WHERE co.cod_pais = o.cod_pais
                 AND co.cod_tipo_docu = o.cod_tipo_docu
                 AND co.cod_orig = o.cod_orig
                 AND co.cod_pais = occ.cod_pais
                 AND co.cod_tipo_docu = occ.cod_tipo_docu
                 AND co.ind_rece_ocr = occ.ind_rece_ocr
                 AND co.ind_rece_web = occ.ind_rece_web
                 AND co.ind_rece_dd = occ.ind_rece_dd
                 AND co.ind_rece_digi = occ.ind_rece_digi
                 AND co.ind_rece_cc = occ.ind_rece_cc
                 AND co.ind_rece_mens = occ.ind_rece_mens
                 AND co.ind_rece_onli = occ.ind_rece_onli
                 AND co.ind_rece_ivr = occ.ind_rece_ivr
                 AND o.ind_forz_vali = 'S') ind_forz_vali,
             NULL cod_mens,
             NULL val_obse_mens,
             sec_nume_docu_cabe
        FROM sto_docum_digit occ
       WHERE psnumeroproceso = occ.num_proc
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND occ.ind_envi = '0'
         AND occ.ind_rech = '0'
         AND ((pscodigovalidanterior IS NULL AND
             occ.cod_ulti_vali_ejec IS NULL) OR
             (occ.cod_ulti_vali_ejec = occ.cod_ulti_vali_exit AND
             occ.cod_ulti_vali_exit = pscodigovalidanterior));
  
    TYPE t_num_lote IS TABLE OF sto_docum_digit.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF sto_docum_digit.sec_nume_docu%TYPE;
    TYPE t_cod_ulti_vali_erro IS TABLE OF sto_docum_digit.cod_ulti_vali_erro%TYPE;
    TYPE t_cod_ulti_vali_exit IS TABLE OF sto_docum_digit.cod_ulti_vali_exit%TYPE;
    TYPE t_ind_forz_vali IS TABLE OF sto_orige_docum.ind_forz_vali%TYPE;
    TYPE t_cod_mens IS TABLE OF sto_tmp_mensa_valid.cod_mens%TYPE;
    TYPE t_val_obse_mens IS TABLE OF sto_tmp_mensa_valid.val_obse_mens%TYPE;
    TYPE t_sec_nume_docu_cabe IS TABLE OF sto_docum_digit.sec_nume_docu_cabe%TYPE;
  
    v_num_lote           t_num_lote;
    v_sec_nume_docu      t_sec_nume_docu;
    v_cod_ulti_vali_erro t_cod_ulti_vali_erro;
    v_cod_ulti_vali_exit t_cod_ulti_vali_exit;
    v_ind_forz_vali      t_ind_forz_vali;
    v_cod_mens           t_cod_mens;
    v_val_obse_mens      t_val_obse_mens;
    v_sec_nume_docu_cabe t_sec_nume_docu_cabe;
  
    rows NATURAL := 5000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
  
    lsindrechazoautomatico sto_param_valid.ind_rech_auto%TYPE;
    lscodigomotivorechazo  sto_param_valid.cod_moti_rech%TYPE;
    lsindcontinuarerror    sto_param_valid.ind_cont%TYPE;
  
    lncodigomensaje          NUMBER;
    lsindhistoexce           sto_param_valid.ind_hist_exce%TYPE;
    lsinddevacabe            sto_tipo_docum_digit.ind_deva_cade%TYPE;
    lsindcabecera            sto_tipo_docum_digit.ind_cabe%TYPE;
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
    lsindgestion             sto_param_valid.ind_gest%TYPE;
    lsindobsemens            sto_param_valid.ind_obse_mens%TYPE;
  
    lsindforzenvierro sto_param_valid.ind_forz_envi_erro%TYPE;
  
    lscodultivalierroant sto_param_valid.cod_vali%TYPE;
  BEGIN
  
    SELECT a.ind_rech_auto,
           decode(a.ind_rech_auto, '1', a.cod_moti_rech, NULL),
           a.ind_hist_exce,
           t.ind_deva_cade,
           a.ind_cont,
           t.ind_cabe,
           a.ind_gest,
           a.ind_obse_mens,
           a.ind_forz_envi_erro
      INTO lsindrechazoautomatico,
           lscodigomotivorechazo,
           lsindhistoexce,
           lsinddevacabe,
           lsindcontinuarerror,
           lsindcabecera,
           lsindgestion,
           lsindobsemens,
           lsindforzenvierro
      FROM sto_param_valid      a,
           sto_tipo_docum_digit t
     WHERE a.cod_pais = t.cod_pais
       AND a.cod_tipo_docu = t.cod_tipo_docu
       AND a.cod_pais = pscodigopais
       AND a.cod_tipo_docu = pscodigotipodoc
       AND a.cod_vali = pscodigovalidactual;
  
    lncodigomensaje := sto_pkg_gener.sto_fn_devue_codig_mensa(pscodigopais,
                                                              pscodigotipodoc,
                                                              pscodigovalidactual);
  
    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodoc);
  
    OPEN c_errorins;
    LOOP
      FETCH c_errorins BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_cod_ulti_vali_erro,
             v_cod_ulti_vali_exit,
             v_ind_forz_vali,
             v_cod_mens,
             v_val_obse_mens,
             v_sec_nume_docu_cabe LIMIT rows;
    
      IF v_num_lote.count > 0 THEN
      
        FOR i IN v_num_lote.first .. v_num_lote.last
        LOOP
          lscodultivalierroant := v_cod_ulti_vali_erro(i);
          /*Por default el registro queda marcado como error*/
          v_cod_ulti_vali_erro(i) := pscodigovalidactual;
        
          /*Si la validacion permite continuar*/
          IF lsindcontinuarerror = 'S' THEN
            v_cod_ulti_vali_exit(i) := pscodigovalidactual;
          END IF;
        
          /*Si la validacion permite envio a pesar de error*/
          IF lsindforzenvierro = 'S' THEN
            v_cod_ulti_vali_erro(i) := lscodultivalierroant;
            v_cod_ulti_vali_exit(i) := pscodigovalidactual;
          END IF;
        
          /*Si la validacion permite envio a pesar de error (origen)*/
          IF v_ind_forz_vali(i) = 'S' THEN
            v_cod_ulti_vali_erro(i) := lscodultivalierroant;
            v_cod_ulti_vali_exit(i) := pscodigovalidactual;
          END IF;
        
          v_cod_mens(i) := lncodigomensaje;
          IF lsindobsemens = 'S' THEN
            BEGIN
              SELECT nvl(m.cod_mens, lncodigomensaje),
                     m.val_obse_mens
                INTO v_cod_mens(i),
                     v_val_obse_mens(i)
                FROM sto_tmp_mensa_valid m
               WHERE m.sec_nume_docu = v_sec_nume_docu(i)
                 AND m.num_lote = v_num_lote(i);
            EXCEPTION
              WHEN no_data_found THEN
                v_val_obse_mens(i) := NULL;
            END;
          END IF;
        
        END LOOP;
      
        /*Inserta Registros de Error*/
      
        FORALL i IN 1 .. v_num_lote.count
          INSERT INTO sto_detal_docum_excep
            (cod_pais,
             cod_tipo_docu,
             num_lote,
             cod_vali,
             sec_nume_docu,
             ind_gest,
             fec_digi,
             usu_digi,
             fec_modi,
             usu_modi,
             cod_mens,
             val_obse_mens,
             sec_nume_docu_cabe)
          VALUES
            (pscodigopais,
             pscodigotipodoc,
             v_num_lote(i),
             pscodigovalidactual,
             v_sec_nume_docu(i),
             lsindgestion,
             SYSDATE,
             psusuario,
             SYSDATE,
             psusuario,
             v_cod_mens(i),
             v_val_obse_mens(i),
             v_sec_nume_docu_cabe(i));
      
        /*Historico de Excepciones*/
        IF lsindhistoexce = '1' THEN
          FORALL i IN 1 .. v_num_lote.count
            INSERT INTO sto_audit_excep
              (cod_pais,
               cod_tipo_docu,
               num_lote,
               cod_vali,
               sec_nume_docu,
               proc_num_proc,
               ind_gest,
               fec_digi,
               usu_digi,
               cod_mens,
               sec_nume_docu_cabe,
               oid_audi_exce,
               cod_acci)
            VALUES
              (pscodigopais,
               pscodigotipodoc,
               v_num_lote(i),
               pscodigovalidactual,
               v_sec_nume_docu(i),
               psnumeroproceso,
               lsindgestion,
               SYSDATE,
               psusuario,
               v_cod_mens(i),
               v_sec_nume_docu_cabe(i),
               sto_auex_seq.nextval,
               'VA');
        END IF;
      
        /*Actualiza Indicadores de Error*/
        FORALL i IN 1 .. v_num_lote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_erro = v_cod_ulti_vali_erro(i),
                 occ.cod_ulti_vali_exit = v_cod_ulti_vali_exit(i),
                 occ.ind_rech           = lsindrechazoautomatico,
                 occ.fec_modi           = SYSDATE,
                 occ.usu_modi           = psusuario,
                 occ.cod_moti_rech      = lscodigomotivorechazo
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i)
             AND occ.ind_envi = '0'
             AND occ.ind_rech = '0';
      
        /*Actualiza con error en caso haya dependencia*/
        IF lsinddevacabe = 'S' AND lsindcontinuarerror = 'N' AND
           lsindcabecera = '1' THEN
          FORALL i IN 1 .. v_num_lote.count
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_erro = decode(v_ind_forz_vali(i),
                                                   'S',
                                                   occ.cod_ulti_vali_erro,
                                                   pscodigovalidactual),
                   occ.cod_ulti_vali_exit = decode(v_ind_forz_vali(i),
                                                   'S',
                                                   pscodigovalidactual,
                                                   occ.cod_ulti_vali_exit),
                   occ.fec_modi           = SYSDATE,
                   occ.usu_modi           = psusuario
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = lscodigodocumentodetalle
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu_cabe = v_sec_nume_docu(i)
               AND occ.ind_envi = '0'
               AND occ.ind_rech = '0';
        
        END IF;
      
        IF lsindrechazoautomatico = '1' THEN
          /*Actualiza los Rechazos Automaticos*/
          FORALL i IN 1 .. v_num_lote.count
            UPDATE sto_docum_digit occ
               SET occ.ind_rech      = lsindrechazoautomatico,
                   occ.fec_modi      = SYSDATE,
                   occ.usu_modi      = psusuario,
                   occ.cod_moti_rech = lscodigomotivorechazo
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = lscodigodocumentodetalle
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu_cabe = v_sec_nume_docu(i)
               AND occ.ind_envi = '0'
               AND occ.ind_rech = '0';
        
          /*Rechaza los Pedidos*/
          IF pscodigotipodoc = 'OCC' THEN
            FORALL i IN 1 .. v_num_lote.count
              UPDATE int_solic_conso_cabec c
                 SET c.ind_erro_rech = '1',
                     c.cod_moti_rech = lscodigomotivorechazo
               WHERE c.num_lote = v_num_lote(i)
                 AND c.sec_nume_docu = v_sec_nume_docu(i);
          
          END IF;
        END IF;
      
      END IF;
      EXIT WHEN c_errorins%NOTFOUND;
    
    END LOOP;
    CLOSE c_errorins;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_GENER_ERROR: ' || ls_sqlerrm);
    
  END sto_pr_gener_error;
  /***************************************************************************
  Descripcion       : Validación generica de  Rechazo STO
  Fecha Creacion    : 09/03/2015
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_recha
  (
    pscodpais     VARCHAR2,
    pscodtipodocu VARCHAR2,
    psnumlote     VARCHAR2,
    pnsecnumedocu NUMBER,
    psnumproc     VARCHAR2,
    psusuario     VARCHAR2,
    psmotirech    VARCHAR2,
    psobse        VARCHAR2
  ) IS
  
    lstipodocudeta sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  BEGIN
  
    lstipodocudeta := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodpais,
                                                                   pscodtipodocu);
  
    UPDATE sto_docum_digit
       SET ind_rech           = '1',
           usu_modi           = psusuario,
           fec_modi           = SYSDATE,
           cod_moti_rech      = psmotirech,
           val_obse_rech_defi = psobse,
           num_proc           = psnumproc
     WHERE cod_pais = pscodpais
       AND ind_envi = '0'
       AND ind_rech = '0'
       AND cod_tipo_docu IN (pscodtipodocu, lstipodocudeta)
       AND (sec_nume_docu_cabe = pnsecnumedocu OR
           sec_nume_docu = pnsecnumedocu)
       AND num_lote = psnumlote;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_GENER_RECHA: ' || ls_sqlerrm);
    
  END sto_pr_gener_recha;
  /***************************************************************************
  Descripcion       : Genera Auditoria para los registros procesados
  Fecha Creacion    : 09/02/2015
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/

  PROCEDURE sto_pr_gener_audi_proce
  (
    pscodpais     VARCHAR2,
    pscodtipodocu VARCHAR2,
    psnumproc     VARCHAR2,
    psacci        VARCHAR2
  ) IS
  
    CURSOR cursto(lstipodocumentodetalle VARCHAR2) IS
      SELECT d.*
        FROM sto_docum_digit d
       WHERE d.num_proc = psnumproc
         AND d.cod_tipo_docu IN (pscodtipodocu, lstipodocumentodetalle)
         AND d.cod_pais = pscodpais;
  
    TYPE sto_tab_t IS TABLE OF sto_docum_digit%ROWTYPE INDEX BY BINARY_INTEGER;
    sto_tab sto_tab_t;
  
    rows NATURAL := 5000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;
  
    lstipodocudeta sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  BEGIN
  
    lstipodocudeta := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodpais,
                                                                   pscodtipodocu);
  
    OPEN cursto(lstipodocudeta);
    LOOP
      FETCH cursto BULK COLLECT
        INTO sto_tab LIMIT rows;
      EXIT WHEN sto_tab.count = 0;
    
      FORALL i IN sto_tab.first .. sto_tab.last
        INSERT INTO sto_audit_excep
          (cod_pais,
           cod_tipo_docu,
           num_lote,
           cod_vali,
           sec_nume_docu,
           proc_num_proc,
           ind_gest,
           fec_digi,
           usu_digi,
           cod_mens,
           sec_nume_docu_cabe,
           oid_audi_exce,
           cod_acci)
        VALUES
          (sto_tab(i).cod_pais,
           sto_tab(i).cod_tipo_docu,
           sto_tab(i).num_lote,
           NULL,
           sto_tab(i).sec_nume_docu,
           sto_tab(i).num_proc,
           '0',
           SYSDATE,
           sto_tab(i).usu_modi,
           NULL,
           sto_tab(i).sec_nume_docu_cabe,
           sto_auex_seq.nextval,
           psacci);
    END LOOP;
    CLOSE cursto;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR sto_pr_gener_audi_proce: ' ||
                              ls_sqlerrm);
  END sto_pr_gener_audi_proce;

  /***************************************************************************
  Descripcion       : Validación generica de Recuperación Rechazo STO
  Fecha Creacion    : 09/03/2015
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_recup_recha
  (
    pscodpais     VARCHAR2,
    pscodtipodocu VARCHAR2,
    psnumlote     VARCHAR2,
    pnsecnumedocu NUMBER,
    psnumproc     VARCHAR2,
    psusuario     VARCHAR2
  ) IS
  
    lstipodocudeta sto_tipo_docum_digit.cod_tipo_docu%TYPE;
  BEGIN
  
    lstipodocudeta := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodpais,
                                                                   pscodtipodocu);
  
    UPDATE sto_docum_digit
       SET ind_rech = '0',
           usu_modi = psusuario,
           fec_modi = SYSDATE,
           num_proc = psnumproc
     WHERE cod_pais = pscodpais
       AND cod_tipo_docu IN (pscodtipodocu, lstipodocudeta)
       AND (sec_nume_docu_cabe = pnsecnumedocu OR
           sec_nume_docu = pnsecnumedocu)
       AND num_lote = psnumlote;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_GENER_RECUP_RECHA: ' ||
                              ls_sqlerrm);
    
  END sto_pr_gener_recup_recha;
  /**************************************************************************
  Descripcion       : Inidica el factor de repetición que corresponde al codigo de venta
  
  Fecha Creacion    : 15/10/2008
  Parametros Entrada:
  
  
                       pscodigoventa
             psoidperiodoreferencia
  
  Autor             : Arturo Blumen
  ***************************************************************************/
  FUNCTION sto_fn_devue_fact_repe(pscodigoventa VARCHAR2,
                                  
                                  psoidperiodoreferencia NUMBER
                                  
                                  ) RETURN NUMBER IS
    lsvalor NUMBER;
  BEGIN
  
    SELECT d.val_fact_repe
      INTO lsvalor
      FROM pre_ofert_detal       d,
           pre_ofert             o,
           pre_matri_factu_cabec c
     WHERE to_number(d.val_codi_vent) = to_number(pscodigoventa)
       AND d.ofer_oid_ofer = o.oid_ofer
       AND o.mfca_oid_cabe = c.oid_cabe
       AND c.perd_oid_peri = psoidperiodoreferencia;
  
    RETURN lsvalor;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
  END sto_fn_devue_fact_repe;

  /**************************************************************************
  Descripcion       : Obtenemos desde la tabla sto_motiv_recha la descripcon del
              motivo de rechazo
  
  
  Fecha Creacion    : 12/12/2008
  Parametros Entrada:
  
  
                      psCodigoPais          Código Pais
                      pscodigodocumento     Codigo de Documento
            pscodigomotivo    Codigo de Motivo
  
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_moti_rech
  (
    pscodigopais      VARCHAR2,
    pscodigodocumento VARCHAR2,
    pscodigomotivo    VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lsvalor VARCHAR2(100);
  BEGIN
  
    SELECT pscodigomotivo || a.des_moti_rech
      INTO lsvalor
      FROM sto_recha_motiv a
     WHERE a.cod_pais = pscodigopais
       AND a.cod_tipo_docu = pscodigodocumento
       AND a.cod_moti_rech = pscodigomotivo;
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
    
  END sto_fn_devue_moti_rech;

  /**************************************************************************
  Descripcion       : Obtenemos los datos de la clasificacion de la Consultora
  
  Fecha Creacion    : 12/01/2009
  Parametros Entrada:
                      varCod_clie          Código Cliente
  
  Autor             : efernandezo
  ***************************************************************************/
  PROCEDURE sto_pr_clasi_consu(varcod_clie VARCHAR2) IS
    i INTEGER;
    /* Declaracion de Variables */
    ln_sqlcode NUMBER(10);
    ls_sqlerrm VARCHAR2(150);
    w_filas    NUMBER := 1000;
  
    CURSOR c_tipo_cliente IS
      SELECT s.cod_tipo_clie,
             s.cod_subt_clie,
             s.cod_tipo_clas,
             s.cod_clas,
             s.ind_ord
        FROM sto_tipo_clien_orden s
       WHERE s.ind_acti = 1
       ORDER BY s.ind_ord;
  
    TYPE tipoclientetype IS RECORD(
      cod_tipo_clie VARCHAR2(2),
      cod_subt_clie VARCHAR2(2),
      cod_tipo_clas VARCHAR2(2),
      cod_clas      VARCHAR2(2),
      ind_ord       NUMBER);
  
    TYPE tipoclientetab IS TABLE OF tipoclientetype;
    tipoclienterecord tipoclientetab;
  
    CURSOR c_sto_tipo_cliente
    (
      variabletipoclie VARCHAR2,
      variablesubtclie VARCHAR2,
      variabletipoclas VARCHAR2,
      variableclas     VARCHAR2,
      variableorden    NUMBER
    ) IS
      SELECT cc.val_i18n                        AS desc_clas,
             tcc.val_i18n                       AS desc_tipo_clas,
             mae_tipo_clien.cod_tipo_clie,
             mae_subti_clien.cod_subt_clie,
             mae_tipo_clasi_clien.cod_tipo_clas,
             mae_clasi.cod_clas,
             variableorden                      AS ind_orde
        FROM mae_clien,
             mae_clien_tipo_subti,
             mae_clien_clasi,
             mae_tipo_clien,
             mae_subti_clien,
             mae_tipo_clasi_clien,
             mae_clasi,
             (SELECT val_oid,
                     val_i18n
                FROM gen_i18n_sicc_comun
               WHERE attr_enti = 'MAE_TIPO_CLIEN'
                 AND idio_oid_idio = 1) tc,
             (SELECT val_oid,
                     val_i18n
                FROM gen_i18n_sicc_comun
               WHERE attr_enti = 'MAE_SUBTI_CLIEN'
                 AND idio_oid_idio = 1) st,
             (SELECT val_oid,
                     val_i18n
                FROM gen_i18n_sicc_comun
               WHERE attr_enti = 'MAE_TIPO_CLASI_CLIEN'
                 AND idio_oid_idio = 1) tcc,
             (SELECT val_oid,
                     val_i18n
                FROM gen_i18n_sicc_comun
               WHERE attr_enti = 'MAE_CLASI'
                 AND idio_oid_idio = 1) cc
       WHERE mae_clien.oid_clie = mae_clien_tipo_subti.clie_oid_clie
         AND mae_clasi.tccl_oid_tipo_clas =
             mae_tipo_clasi_clien.oid_tipo_clas
         AND mae_clien_clasi.clas_oid_clas = mae_clasi.oid_clas
         AND mae_clien_clasi.ctsu_oid_clie_tipo_subt =
             mae_clien_tipo_subti.oid_clie_tipo_subt
         AND mae_clien_clasi.tccl_oid_tipo_clasi =
             mae_tipo_clasi_clien.oid_tipo_clas
         AND mae_clien_tipo_subti.clie_oid_clie = mae_clien.oid_clie
         AND mae_tipo_clien.oid_tipo_clie =
             mae_clien_tipo_subti.ticl_oid_tipo_clie
         AND mae_subti_clien.oid_subt_clie =
             mae_clien_tipo_subti.sbti_oid_subt_clie
         AND mae_tipo_clien.oid_tipo_clie = tc.val_oid(+)
         AND mae_subti_clien.oid_subt_clie = st.val_oid(+)
         AND mae_tipo_clasi_clien.oid_tipo_clas = tcc.val_oid(+)
         AND mae_clasi.oid_clas = cc.val_oid(+)
         AND mae_clien.cod_clie = varcod_clie
         AND mae_tipo_clien.cod_tipo_clie = variabletipoclie
         AND mae_subti_clien.cod_subt_clie = variablesubtclie
         AND mae_tipo_clasi_clien.cod_tipo_clas = variabletipoclas
         AND mae_clasi.cod_clas = variableclas
         AND rownum = 1;
  
    TYPE stotipoclientetype IS RECORD(
      desc_clasi     VARCHAR2(150),
      desc_tipo_clas VARCHAR2(150),
      cod_tipo_clie  VARCHAR2(2),
      cod_subt_clie  VARCHAR2(2),
      cod_tipo_clas  VARCHAR2(2),
      cod_clas       VARCHAR2(2),
      ind_orde       NUMBER);
  
    TYPE stotipoclientetab IS TABLE OF stotipoclientetype;
    stotipoclienterecord stotipoclientetab;
  
  BEGIN
  
    OPEN c_tipo_cliente;
    LOOP
      FETCH c_tipo_cliente BULK COLLECT
        INTO tipoclienterecord LIMIT w_filas;
    
      IF tipoclienterecord.count > 0 THEN
        FOR x IN tipoclienterecord.first .. tipoclienterecord.last
        LOOP
          i := i + 1;
        
          OPEN c_sto_tipo_cliente(tipoclienterecord(x).cod_tipo_clie,
                                  tipoclienterecord(x).cod_subt_clie,
                                  tipoclienterecord(x).cod_tipo_clas,
                                  tipoclienterecord(x).cod_clas,
                                  tipoclienterecord(x).ind_ord);
          LOOP
            FETCH c_sto_tipo_cliente BULK COLLECT
              INTO stotipoclienterecord LIMIT w_filas;
            IF stotipoclienterecord.count > 0 THEN
              FOR x IN stotipoclienterecord.first .. stotipoclienterecord.last
              LOOP
                i := i + 1;
              
                INSERT INTO gtt_sto_clasi_consu
                  (desc_clasi,
                   desc_tipo_clas,
                   cod_tipo_clie,
                   cod_subt_clie,
                   cod_tipo_clas,
                   cod_clas,
                   ind_orde)
                VALUES
                  (stotipoclienterecord(x).desc_clasi,
                   stotipoclienterecord(x).desc_tipo_clas,
                   stotipoclienterecord(x).cod_tipo_clie,
                   stotipoclienterecord(x).cod_subt_clie,
                   stotipoclienterecord(x).cod_tipo_clas,
                   stotipoclienterecord(x).cod_clas,
                   stotipoclienterecord(x).ind_orde);
              
              END LOOP;
            END IF;
            EXIT WHEN c_sto_tipo_cliente%NOTFOUND;
          END LOOP;
          CLOSE c_sto_tipo_cliente;
        
        END LOOP;
      END IF;
      EXIT WHEN c_tipo_cliente%NOTFOUND;
    END LOOP;
    CLOSE c_tipo_cliente;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(ln_sqlcode || '-' || SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR sto_pr_clasi_consu: ' || ls_sqlerrm);
  END sto_pr_clasi_consu;
  /**************************************************************************
  Descripcion        : Devuelve la suma de las ventas de la tabla ped_solic_cabec
  Fecha Creacion     : 02/02/2009
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_venta
  (
    pscodigopais       VARCHAR2,
    psoidtiposolipais  NUMBER,
    psoidtiposoliconso NUMBER,
    pscodigoperiodo    VARCHAR2,
    psoidcliente       NUMBER
  ) RETURN NUMBER IS
    lsvalor    NUMBER;
    lscampanas sto_param_gener_occrr.val_param%TYPE;
  
  BEGIN
  
    lscampanas := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                       'STO_CAMPANAS');
  
    SELECT SUM(con.val_tota_paga_loca) venta
      INTO lsvalor
      FROM ped_solic_cabec con,
           ped_solic_cabec ped
     WHERE con.oid_soli_cabe = ped.soca_oid_soli_cabe
       AND ped.tspa_oid_tipo_soli_pais = psoidtiposolipais
       AND con.tspa_oid_tipo_soli_pais = psoidtiposoliconso
       AND con.perd_oid_peri IN
           (SELECT a.oid_peri
              FROM (SELECT z1.oid_peri,
                           z2.cod_peri
                      FROM cra_perio       z1,
                           seg_perio_corpo z2
                     WHERE z1.peri_oid_peri = z2.oid_peri
                     ORDER BY z2.cod_peri DESC) a
             WHERE a.cod_peri < pscodigoperiodo
               AND rownum < (to_number(lscampanas) + 1))
       AND con.clie_oid_clie = psoidcliente
     GROUP BY con.clie_oid_clie;
  
    RETURN lsvalor;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
  END sto_fn_devue_venta;
  /**************************************************************************
  Descripcion        : Devuelve la suma del total de registros de  ped_solic_cabec
  Fecha Creacion     : 02/02/2009
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_total_regis
  (
    pscodigopais       VARCHAR2,
    psoidtiposolipais  NUMBER,
    psoidtiposoliconso NUMBER,
    pscodigoperiodo    VARCHAR2,
    psoidcliente       NUMBER
  ) RETURN NUMBER IS
    lsvalor    NUMBER;
    lscampanas sto_param_gener_occrr.val_param%TYPE;
  
  BEGIN
  
    lscampanas := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                       'STO_CAMPANAS');
  
    SELECT COUNT(*) totalregs
      INTO lsvalor
      FROM ped_solic_cabec con,
           ped_solic_cabec ped
     WHERE con.oid_soli_cabe = ped.soca_oid_soli_cabe
       AND ped.tspa_oid_tipo_soli_pais = psoidtiposolipais
       AND con.tspa_oid_tipo_soli_pais = psoidtiposoliconso
       AND con.perd_oid_peri IN
           (SELECT a.oid_peri
              FROM (SELECT z1.oid_peri,
                           z2.cod_peri
                      FROM cra_perio       z1,
                           seg_perio_corpo z2
                     WHERE z1.peri_oid_peri = z2.oid_peri
                     ORDER BY z2.cod_peri DESC) a
             WHERE a.cod_peri < pscodigoperiodo
               AND rownum < (to_number(lscampanas) + 1))
       AND con.clie_oid_clie = psoidcliente
     GROUP BY con.clie_oid_clie;
  
    RETURN lsvalor;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN NULL;
    WHEN OTHERS THEN
      RETURN NULL;
  END sto_fn_devue_total_regis;
  /**************************************************************************
  Descripcion        : Devuelve la suma de las devoluciones de rec_linea_opera_recla
  Fecha Creacion     : 02/02/2009
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_descu
  (
    pscodigopais       VARCHAR2,
    psoidtiposolipais  NUMBER,
    psoidtiposoliconso NUMBER,
    pscodigoperiodo    VARCHAR2,
    psoidcliente       NUMBER
  ) RETURN NUMBER IS
    lsvalor    NUMBER;
    lscampanas sto_param_gener_occrr.val_param%TYPE;
  
  BEGIN
  
    lscampanas := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                       'STO_CAMPANAS');
  
    SELECT nvl(SUM(e.imp_abon), 0) descuento
      INTO lsvalor
      FROM rec_cabec_recla       a,
           rec_opera_recla       b,
           rec_tipos_opera       c,
           rec_opera             d,
           rec_linea_opera_recla e,
           ped_solic_cabec       f
     WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
       AND b.oid_oper_recl = e.opre_oid_oper_recl
       AND d.oid_oper = c.rope_oid_oper
       AND c.oid_tipo_oper = b.tiop_oid_tipo_oper
       AND d.cod_oper IN ('DN', 'DE')
       AND a.soca_oid_soli_cabe = f.oid_soli_cabe
       AND a.soca_oid_soli_cabe IN
           (SELECT con.oid_soli_cabe
              FROM ped_solic_cabec con,
                   ped_solic_cabec ped
             WHERE con.oid_soli_cabe = ped.soca_oid_soli_cabe
               AND ped.tspa_oid_tipo_soli_pais = psoidtiposolipais
               AND con.tspa_oid_tipo_soli_pais = psoidtiposoliconso
               AND con.perd_oid_peri IN
                   (SELECT a.oid_peri
                      FROM (SELECT z1.oid_peri,
                                   z2.cod_peri
                              FROM cra_perio       z1,
                                   seg_perio_corpo z2
                             WHERE z1.peri_oid_peri = z2.oid_peri
                             ORDER BY z2.cod_peri DESC) a
                     WHERE a.cod_peri < pscodigoperiodo
                       AND rownum < (to_number(lscampanas) + 1))
               AND con.clie_oid_clie = psoidcliente);
  
    RETURN lsvalor;
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
    WHEN OTHERS THEN
      RETURN 0;
  END sto_fn_devue_descu;
  /***************************************************************************
  Descripcion       : Craga la informacinn en una tabla tempral para pasarlo a
                      Historicos
  Fecha Creacion    : 11/03/2009
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_carga_aptos_histo
  (
    pscodigopais    VARCHAR2,
    psnumlote       VARCHAR2,
    psfechainicio   VARCHAR2,
    psfechafin      VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
  
  BEGIN
  
    DELETE sto_tmp_docum_digit;
  
    INSERT INTO sto_tmp_docum_digit
      (cod_pais,
       cod_tipo_docu,
       num_lote,
       sec_nume_docu)
      SELECT sddi.cod_pais,
             sddi.cod_tipo_docu,
             sddi.num_lote,
             sddi.sec_nume_docu
        FROM sto_docum_digit sddi
       WHERE (psnumlote IS NULL OR sddi.num_lote = psnumlote)
         AND (pscodigoperiodo IS NULL OR sddi.cod_peri = pscodigoperiodo)
         AND sddi.cod_tipo_docu = pstipodocumento
         AND sddi.cod_pais = pscodigopais
         AND (psfechainicio IS NULL OR
             trunc(sddi.fec_digi) >= to_date(psfechainicio, 'DD/MM/YYYY'))
         AND (psfechafin IS NULL OR
             trunc(sddi.fec_digi) <= to_date(psfechafin, 'DD/MM/YYYY'));
  
  END sto_pr_carga_aptos_histo;

  /***************************************************************************
  Descripcion       : Carga los registros sin Gestion en una tabla temporal
                      para pasarlo a historicos
  Fecha Creacion    : 13/06/2012
  Autor             : Jose A. Cairampoma Granados
  ***************************************************************************/
  PROCEDURE sto_pr_docum_sgest_histo
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psnumeroproceso VARCHAR2
  ) IS
  
  BEGIN
    INSERT INTO sto_proce_docum_digit
      (cod_pais,
       cod_tipo_docu,
       num_lote,
       sec_nume_docu,
       num_proc)
      SELECT DISTINCT sddi.cod_pais,
                      sddi.cod_tipo_docu,
                      sddi.num_lote,
                      sddi.sec_nume_docu,
                      psnumeroproceso
        FROM sto_docum_digit       sddi,
             sto_detal_docum_excep sdde
       WHERE sdde.sec_nume_docu = sddi.sec_nume_docu
         AND sdde.num_lote = sddi.num_lote
         AND sdde.cod_tipo_docu = sddi.cod_tipo_docu
         AND sdde.cod_pais = sdde.cod_pais
         AND sddi.cod_peri = pscodigoperiodo
         AND sddi.cod_tipo_docu = pstipodocumento
         AND sddi.cod_pais = pscodigopais
         AND sddi.ind_envi = '0'
         AND sddi.ind_rech = '0';
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR STO_PR_DOCUM_SGEST_HISTO: ' ||
                              ls_sqlerrm);
  END sto_pr_docum_sgest_histo;
  /**************************************************************************
  Descripcion        : Devuelve el codigo de referencia
  Fecha Creacion     : 24/04/2009
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_codig_refer
  (
    pscodtemp VARCHAR2,
    pscodpais VARCHAR2
  ) RETURN VARCHAR2 IS
  
    chrnit      VARCHAR2(100);
    intcontador NUMBER;
    intacum     NUMBER;
    intresto    NUMBER;
    intlongiden NUMBER; --Longitud de la cadena identificación}
  
    intsuma      NUMBER;
    lssuma       VARCHAR2(100);
    lscadenatemp VARCHAR2(100);
    lsproducto   VARCHAR2(100);
    intnumero    NUMBER;
    -- Arreglo
    TYPE arreglopa IS TABLE OF NUMBER(2) INDEX BY BINARY_INTEGER;
  
    -- Variable de tipo arreglo
    arrpa arreglopa;
    arrda arreglopa;
  
    -- CONSTANTES
  
    lsbase      sto_param_gener_occrr.val_param%TYPE;
    lsconstante sto_param_gener_occrr.val_param%TYPE;
    lsfactor    sto_param_gener_occrr.val_param%TYPE;
  
  BEGIN
  
    lsbase      := sto_pkg_gener.sto_fn_obten_param_ocr(pscodpais,
                                                        'STO_BASE_COD_REFE');
    lsconstante := sto_pkg_gener.sto_fn_obten_param_ocr(pscodpais,
                                                        'STO_CONS_COD_REFE');
    lsfactor    := sto_pkg_gener.sto_fn_obten_param_ocr(pscodpais,
                                                        'STO_FACT_COD_REFE');
  
    chrnit := pscodtemp;
  
    intsuma      := to_number(pscodtemp) + to_number(lsconstante);
    lssuma       := to_char(intsuma);
    lscadenatemp := substr(lssuma, length(lssuma) - 5, length(lssuma));
  
    -- Válida el dígito de chequeo
    SELECT length(chrnit) + 1 INTO intlongiden FROM dual;
  
    intcontador := 1;
    intnumero   := 1;
    -- Llena el arreglo DA con la cadena de entrada que es el NIT
    FOR intcontador IN 1 .. intlongiden - 1
    LOOP
      arrda(intcontador) := to_number(substr(lscadenatemp,
                                             intlongiden - intcontador,
                                             1));
    END LOOP;
  
    FOR intcontador IN 1 .. intlongiden - 1
    LOOP
      arrpa(intcontador) := to_number(substr(lsfactor,
                                             intlongiden - intcontador,
                                             1));
    END LOOP;
  
    intsuma := 0;
    FOR intcontador IN 1 .. intlongiden - 1
    LOOP
      lsproducto := to_char(arrda(intcontador) * arrpa(intcontador));
    
      FOR intnumero IN 1 .. length(lsproducto)
      LOOP
        intsuma := intsuma +
                   to_number(substr(lsproducto,
                                    (length(lsproducto) + 1) - intnumero,
                                    1));
      END LOOP;
    
    END LOOP;
  
    intacum := intsuma + to_number(lsbase);
  
    -- Obtiene el verificador
    intresto := MOD(intacum, 10);
  
    IF (intresto = 0) THEN
      RETURN to_char(intresto);
    ELSE
      RETURN to_char(10 - intresto);
    END IF;
  
  END sto_fn_devue_codig_refer;
  /**************************************************************************
  Descripcion        : Devuelve el codigo de referencia
  Fecha Creacion     : 06/05/2009
  
  Autor              : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_devue_codre_esika
  (
    pscodtemp VARCHAR2,
    pscodpais VARCHAR2
  ) RETURN VARCHAR2 IS
  
    intcontador   NUMBER;
    intacum       NUMBER;
    intresto      NUMBER;
    intlongiden   NUMBER; --Longitud de la cadena identificación}
    intlongfactor NUMBER; --Longitud de la cadena factor
  
    intsuma NUMBER;
  
    lscadenatemp VARCHAR2(100);
    lsproducto   VARCHAR2(100);
  
    -- Arreglo
    TYPE arreglopa IS TABLE OF NUMBER(2) INDEX BY BINARY_INTEGER;
  
    -- Variable de tipo arreglo
    arrpa arreglopa;
    arrda arreglopa;
  
    -- CONSTANTES
  
    lsbase        sto_param_gener_occrr.val_param%TYPE;
    lsconstante   sto_param_gener_occrr.val_param%TYPE;
    lsfactor      sto_param_gener_occrr.val_param%TYPE;
    lsdenominador sto_param_gener_occrr.val_param%TYPE;
  
    indice NUMBER;
  
  BEGIN
  
    lsbase        := sto_pkg_gener.sto_fn_obten_param_ocr(pscodpais,
                                                          'STO_BASE_COD_ESIKA');
    lsconstante   := sto_pkg_gener.sto_fn_obten_param_ocr(pscodpais,
                                                          'STO_VERIFI_COD_ESIKA');
    lsfactor      := sto_pkg_gener.sto_fn_obten_param_ocr(pscodpais,
                                                          'STO_FACTOR_COD_ESIKA');
    lsdenominador := sto_pkg_gener.sto_fn_obten_param_ocr(pscodpais,
                                                          'STO_DENOMI_COD_ESIKA');
  
    lscadenatemp := pscodtemp;
  
    indice := 1;
  
    -- Válida el dígito de chequeo
    SELECT length(lscadenatemp) + 1 INTO intlongiden FROM dual;
    SELECT length(lsfactor) + 1 INTO intlongfactor FROM dual;
  
    intcontador := 1;
  
    -- Llena el arreglo DA con la cadena de entrada que es el NIT
    FOR intcontador IN 1 .. intlongiden - 1
    LOOP
      arrda(intcontador) := to_number(substr(lscadenatemp,
                                             intlongiden - intcontador,
                                             1));
    END LOOP;
  
    FOR intcontador IN 1 .. intlongfactor - 1
    LOOP
    
      IF (MOD(intcontador, 2) != 0) THEN
        arrpa(indice) := to_number(substr(lsfactor,
                                          intlongfactor - 1 - intcontador,
                                          2));
        indice := indice + 1;
      END IF;
    END LOOP;
  
    intsuma := 0;
    FOR intcontador IN 1 .. intlongiden - 1
    LOOP
      lsproducto := to_char(arrda(intcontador) * arrpa(intcontador));
      intsuma    := intsuma + to_number(lsproducto);
    
    END LOOP;
  
    intacum := intsuma + to_number(lsbase);
  
    -- Obtiene el verificador
    intresto := MOD(intacum, lsdenominador);
  
    RETURN to_char(to_number(lsconstante) - intresto);
  
  END sto_fn_devue_codre_esika;

  FUNCTION sto_fn_devue_venta_devue_falta
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    pscliente       VARCHAR2,
    psnumsecuencia  NUMBER,
    pstipodocumento VARCHAR2
  ) RETURN NUMBER IS
    lsvalor NUMBER;
  BEGIN
  
    SELECT SUM(det.can_vent_devu)
      INTO lsvalor
      FROM int_solic_conso_poven_cabec cab,
           int_solic_conso_poven_detal det,
           ped_solic_cabec             ped
     WHERE cab.oid_cabe = ped.oid_soli_cabe
       AND cab.cod_pais = det.cod_pais
       AND cab.cod_peri = det.cod_peri
       AND cab.cod_clie = det.cod_clie
       AND cab.num_docu = det.num_docu
       AND det.cod_peri = psperiodo
       AND det.cod_clie = pscliente
       AND det.num_docu = psnumsecuencia
       AND cab.cod_pais = pscodigopais
       AND det.docu_cod_tipo_docu = pstipodocumento
       AND det.cod_oper IN ('FM', 'FA', 'MF');
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
    WHEN OTHERS THEN
      RETURN 0;
  END sto_fn_devue_venta_devue_falta;

  FUNCTION sto_fn_devue_venta_devue_devue
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    pscliente       VARCHAR2,
    psnumsecuencia  NUMBER,
    pstipodocumento VARCHAR2
  ) RETURN NUMBER IS
    lsvalor NUMBER;
  BEGIN
  
    SELECT SUM(det.can_vent_devu)
      INTO lsvalor
      FROM int_solic_conso_poven_cabec cab,
           int_solic_conso_poven_detal det,
           ped_solic_cabec             ped
     WHERE cab.oid_cabe = ped.oid_soli_cabe
       AND cab.cod_pais = det.cod_pais
       AND cab.cod_peri = det.cod_peri
       AND cab.cod_clie = det.cod_clie
       AND cab.num_docu = det.num_docu
       AND det.cod_peri = psperiodo
       AND det.cod_clie = pscliente
       AND det.num_docu = psnumsecuencia
       AND cab.cod_pais = pscodigopais
       AND det.docu_cod_tipo_docu = pstipodocumento
       AND det.cod_oper IN ('DN');
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
    WHEN OTHERS THEN
      RETURN 0;
  END sto_fn_devue_venta_devue_devue;

  FUNCTION sto_fn_devue_produ_canti_devue
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    pscliente       VARCHAR2,
    psnumdocu       NUMBER,
    pstipodocumento VARCHAR2
  )
  
   RETURN NUMBER IS
    lsvalor NUMBER;
  BEGIN
  
    SELECT SUM(det.val_prec_cata_devu * det.can_vent_devu)
      INTO lsvalor
    
      FROM int_solic_conso_poven_cabec cab,
           int_solic_conso_poven_detal det,
           ped_solic_cabec             ped
     WHERE cab.oid_cabe = ped.oid_soli_cabe
       AND cab.cod_pais = det.cod_pais
       AND cab.cod_peri = det.cod_peri
       AND cab.cod_clie = det.cod_clie
       AND cab.num_docu = det.num_docu
       AND det.cod_peri = psperiodo
       AND det.cod_clie = pscliente
       AND det.num_docu = psnumdocu
       AND cab.cod_pais = pscodigopais
       AND det.docu_cod_tipo_docu = pstipodocumento
       AND det.cod_oper IN ('DN');
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
    WHEN OTHERS THEN
      RETURN 0;
  END sto_fn_devue_produ_canti_devue;

  FUNCTION sto_fn_devue_produ_canti_falta
  (
    pscodigopais    VARCHAR2,
    psperiodo       VARCHAR2,
    pscliente       VARCHAR2,
    psnumdocu       NUMBER,
    pstipodocumento VARCHAR2
  )
  
   RETURN NUMBER IS
    lsvalor NUMBER;
  BEGIN
  
    SELECT SUM(det.val_prec_cata_devu * det.can_vent_devu)
      INTO lsvalor
    
      FROM int_solic_conso_poven_cabec cab,
           int_solic_conso_poven_detal det,
           ped_solic_cabec             ped
     WHERE cab.oid_cabe = ped.oid_soli_cabe
       AND cab.cod_pais = det.cod_pais
       AND cab.cod_peri = det.cod_peri
       AND cab.cod_clie = det.cod_clie
       AND cab.num_docu = det.num_docu
       AND det.cod_peri = psperiodo
       AND det.cod_clie = pscliente
       AND det.num_docu = psnumdocu
       AND cab.cod_pais = pscodigopais
       AND det.docu_cod_tipo_docu = pstipodocumento
       AND det.cod_oper IN ('FM', 'FA', 'MF');
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
    WHEN OTHERS THEN
      RETURN 0;
  END sto_fn_devue_produ_canti_falta;
  /**************************************************************************
  Descripcion       : Obtiene el indicador de Ejecucion automatica de Validaciones
                      1:Activo
                      0 o otro:Inactivo;
  Fecha Creacion    : 19/06/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_indic_valid_autom
  (
    pscodigopais    VARCHAR2,
    pstipodocumento VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lsvalor VARCHAR(1);
  BEGIN
  
    SELECT nvl(a.ind_vali_auto, '0')
      INTO lsvalor
      FROM sto_tipo_docum_digit a
     WHERE a.cod_pais = pscodigopais
       AND a.cod_tipo_docu = pstipodocumento;
  
    RETURN lsvalor;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN 0;
    WHEN OTHERS THEN
      RETURN 0;
  END sto_fn_devue_indic_valid_autom;

  /***************************************************************************
  Descripcion       : Procedimiento con pasos posteriores despues de indicar
                      los registros validos
  Fecha Creacion    : 03/05/2010
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_after_exval
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
  BEGIN
  
    sto_pkg_gener.sto_pr_gener_error(pscodigopais,
                                     pscodigotipodoc,
                                     pscodigovalidactual,
                                     pscodigovalidanterior,
                                     psusuario,
                                     psnumeroproceso);
  
    dbms_application_info.set_module(module_name => NULL,
                                     action_name => NULL);
  
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR STO_PR_GENER_AFTER_EXVAL: ' ||
                              ls_sqlerrm);
    
  END sto_pr_gener_after_exval;
  /***************************************************************************
  Descripcion       :  Procedimiento con pasos antes de indicar
                      los registros validos
  Fecha Creacion    : 03/05/2010
  Autor             : Jose A. Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_gener_befor_exval
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
  
  BEGIN
    dbms_application_info.set_module(module_name => 'STO - ' ||
                                                    pscodigotipodoc ||
                                                    ' - ' ||
                                                    psnumeroproceso,
                                     action_name => pscodigovalidactual);
  
    sto_pr_exclu_valid(pscodigopais,
                       pscodigotipodoc,
                       pscodigovalidanterior,
                       pscodigovalidactual,
                       psnumeroproceso,
                       psusuario);
  
    sto_pr_gener_aprob(pscodigopais,
                       pscodigotipodoc,
                       pscodigovalidanterior,
                       pscodigovalidactual,
                       psnumeroproceso,
                       psusuario);
  
    sto_pr_regis_docum_tempo_aptos(pscodigopais,
                                   pscodigotipodoc,
                                   pscodigovalidanterior,
                                   psnumeroproceso);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR STO_PR_GENER_BEFOR_EXVAL: ' ||
                              ls_sqlerrm);
    
  END sto_pr_gener_befor_exval;

  /**************************************************************************
  Descripcion        : Devuelve el modulo diez de la cadena ingresda
  Fecha Creacion     : 08/11/2010
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_fn_devue_modul_diez(pscodigo VARCHAR2) RETURN VARCHAR2 IS
    lscodigo mae_clien.cod_clie%TYPE;
    cont     NUMBER;
    factor   NUMBER;
    multi    NUMBER;
    suma     NUMBER := 0;
    res      NUMBER := 0;
  BEGIN
    --Numero a trabajar
    lscodigo := substr(pscodigo, 0, length(pscodigo) - 1);
  
    cont   := length(lscodigo);
    factor := 2;
  
    LOOP
      -- Multiplica el digito por el factor segun corresponda [2 - 1]
      multi := to_number(substr(lscodigo, cont, 1)) * factor;
      -- Suma los digitos del resultado de la multiplicacion
      suma := suma + (trunc(multi / 10) + MOD(multi, 10));
      -- Actualiza el factor
      IF factor = 2 THEN
        factor := 1;
      ELSE
        factor := 2;
      END IF;
      cont := cont - 1;
      EXIT WHEN cont = 0;
    END LOOP;
    -- Obtiene el residuo de la suma entre 10
    res := MOD(suma, 10);
  
    IF (res != 0) THEN
      res := 10 - res;
    END IF;
  
    -- Devuelve el digito verificador
    RETURN to_char(res);
  
  END sto_fn_devue_modul_diez;

  /**************************************************************************
  Descripcion        : Valida si el codigo de cliente cumple con el modulo diez
  Fecha Creacion     : 08/11/2010
  Autor              : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_fn_valid_modul_diez(pscodigo VARCHAR2) RETURN VARCHAR2 IS
  BEGIN
  
    IF substr(pscodigo, length(pscodigo), 1) =
       sto_fn_devue_modul_diez(pscodigo) THEN
      RETURN '1';
    ELSE
      RETURN '0';
    END IF;
  
  END sto_fn_valid_modul_diez;
  /***************************************************************************
  Descripcion       : Genera una tabla virtual para la consulta de Beneficio Deuda
  Fecha Creacion    : 05/11/2010
  Autor             : Jesse Rios Franco
  ***************************************************************************/
  FUNCTION sto_fn_benef_deuda
  (
    pscodigopais     VARCHAR2,
    pscodigomarca    VARCHAR2,
    pscodigocanal    VARCHAR2,
    pscodigoiso      VARCHAR2,
    pnoidtipocliente NUMBER,
    pnoidregion      NUMBER,
    pnoidcliente     NUMBER,
    pnoidperiodo     NUMBER
  ) RETURN tabla_beneficio_deuda
    PIPELINED IS
  
    CURSOR c_benf_deuda IS
      SELECT bc.oid_clie_bene_deud,
             NULL                  AS codigo_periodo,
             NULL                  AS tipo_cliente,
             NULL                  AS subtipo_cliente,
             NULL                  AS ipo_clasificacion_cliente,
             NULL                  AS clasificacion,
             NULL                  AS codigo_cliente,
             NULL                  AS desc_region,
             NULL                  AS desc_zona,
             bc.tip_bene,
             bc.oid_peri,
             bc.oid_tipo_clie,
             bc.oid_subt_clie,
             bc.oid_tipo_clas_clie,
             bc.oid_clas_clie,
             bc.clie_oid_clie,
             bc.oid_regi,
             bc.oid_zona,
             bc.val_mont,
             bc.val_porc
        FROM sto_clien_benef_deuda bc
       WHERE nvl(bc.oid_tipo_clie, 0) =
             nvl(pnoidtipocliente, nvl(bc.oid_tipo_clie, 0))
         AND nvl(bc.oid_regi, 0) = nvl(pnoidregion, nvl(bc.oid_regi, 0))
         AND nvl(bc.clie_oid_clie, 0) =
             nvl(pnoidcliente, nvl(bc.clie_oid_clie, 0))
         AND nvl(bc.oid_peri, 0) = nvl(pnoidperiodo, nvl(bc.oid_peri, 0))
         AND bc.ind_elim = '0';
  
    tablaregistro tabla_beneficio_deuda;
  
  BEGIN
  
    OPEN c_benf_deuda;
  
    LOOP
      FETCH c_benf_deuda BULK COLLECT
        INTO tablaregistro LIMIT w_filas;
    
      IF tablaregistro.count > 0 THEN
      
        FOR x IN tablaregistro.first .. tablaregistro.last
        LOOP
        
          IF tablaregistro(x).oid_peri IS NOT NULL THEN
            tablaregistro(x).codigo_periodo := gen_pkg_gener.gen_fn_devuelve_des_perio(tablaregistro(x)
                                                                                       .oid_peri);
          END IF;
        
          BEGIN
            SELECT val_i18n descripcion
              INTO tablaregistro(x).tipo_cliente
              FROM gen_i18n_sicc_comun p,
                   mae_tipo_clien      c,
                   seg_idiom           i
             WHERE p.val_oid = c.oid_tipo_clie
               AND p.idio_oid_idio = i.oid_idio
               AND p.attr_enti = 'MAE_TIPO_CLIEN'
               AND i.cod_iso_idio = pscodigoiso
               AND oid_tipo_clie = tablaregistro(x).oid_tipo_clie;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).tipo_cliente := NULL;
          END;
        
          BEGIN
            SELECT val_i18n descripcion
              INTO tablaregistro(x).subtipo_cliente
              FROM gen_i18n_sicc_comun p,
                   mae_subti_clien     c,
                   mae_tipo_clien      t,
                   seg_idiom           i
             WHERE p.val_oid = c.oid_subt_clie
               AND p.idio_oid_idio = i.oid_idio
               AND p.attr_enti = 'MAE_SUBTI_CLIEN'
               AND t.oid_tipo_clie = c.ticl_oid_tipo_clie
               AND i.cod_iso_idio = pscodigoiso
               AND c.oid_subt_clie = tablaregistro(x).oid_subt_clie;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).subtipo_cliente := NULL;
          END;
        
          BEGIN
            SELECT val_i18n descripcion
              INTO tablaregistro(x).tipo_clasificacion_cliente
              FROM gen_i18n_sicc_comun,
                   seg_idiom,
                   mae_tipo_clasi_clien,
                   mae_tipo_clien,
                   mae_subti_clien
             WHERE gen_i18n_sicc_comun.val_oid =
                   mae_tipo_clasi_clien.oid_tipo_clas
               AND gen_i18n_sicc_comun.idio_oid_idio = seg_idiom.oid_idio
               AND gen_i18n_sicc_comun.attr_enti = 'MAE_TIPO_CLASI_CLIEN'
               AND seg_idiom.cod_iso_idio = pscodigoiso
               AND mae_subti_clien.oid_subt_clie =
                   mae_tipo_clasi_clien.sbti_oid_subt_clie
               AND mae_tipo_clien.oid_tipo_clie =
                   mae_subti_clien.ticl_oid_tipo_clie
               AND mae_tipo_clasi_clien.oid_tipo_clas = tablaregistro(x)
                  .oid_tipo_clas_clie;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).tipo_clasificacion_cliente := NULL;
          END;
        
          BEGIN
            SELECT val_i18n descripcion
              INTO tablaregistro(x).clasificacion
              FROM gen_i18n_sicc_comun,
                   seg_idiom,
                   mae_clasi,
                   mae_tipo_clasi_clien,
                   mae_subti_clien,
                   mae_tipo_clien
             WHERE gen_i18n_sicc_comun.val_oid = mae_clasi.oid_clas
               AND gen_i18n_sicc_comun.idio_oid_idio = seg_idiom.oid_idio
               AND gen_i18n_sicc_comun.attr_enti = 'MAE_CLASI'
               AND seg_idiom.cod_iso_idio = pscodigoiso
               AND mae_clasi.tccl_oid_tipo_clas =
                   mae_tipo_clasi_clien.oid_tipo_clas
               AND mae_subti_clien.oid_subt_clie =
                   mae_tipo_clasi_clien.sbti_oid_subt_clie
               AND mae_tipo_clien.oid_tipo_clie =
                   mae_subti_clien.ticl_oid_tipo_clie
               AND mae_clasi.oid_clas = tablaregistro(x).oid_clas_clie;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).clasificacion := NULL;
          END;
        
          BEGIN
            SELECT ci.cod_clie
              INTO tablaregistro(x).codigo_cliente
              FROM mae_clien ci
             WHERE ci.pais_oid_pais =
                   gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais)
               AND ci.oid_clie = tablaregistro(x).clie_oid_clie;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).codigo_cliente := NULL;
          END;
        
          BEGIN
            SELECT upper(nvl(zon_regio.des_regi, ' ')) descripcion
              INTO tablaregistro(x).desc_region
              FROM seg_pais,
                   seg_marca,
                   seg_canal,
                   zon_regio
             WHERE (zon_regio.ind_acti = 1)
               AND (seg_pais.cod_pais = pscodigopais)
               AND (seg_marca.cod_marc = pscodigomarca)
               AND (seg_canal.cod_cana = pscodigocanal)
               AND (seg_pais.oid_pais = zon_regio.pais_oid_pais)
               AND (seg_marca.oid_marc = zon_regio.marc_oid_marc)
               AND (seg_canal.oid_cana = zon_regio.cana_oid_cana)
               AND zon_regio.oid_regi = tablaregistro(x).oid_regi;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).desc_region := NULL;
          END;
        
          BEGIN
            SELECT nvl(zon_zona.des_zona, ' ') AS descripcion
              INTO tablaregistro(x).desc_zona
              FROM zon_zona,
                   seg_pais,
                   seg_canal,
                   seg_marca,
                   zon_regio
             WHERE (zon_zona.ind_acti = 1)
               AND (seg_pais.cod_pais = pscodigopais)
               AND (seg_marca.cod_marc = pscodigomarca)
               AND (seg_canal.cod_cana = pscodigocanal)
               AND (seg_canal.oid_cana = zon_regio.cana_oid_cana)
               AND (seg_pais.oid_pais = zon_regio.pais_oid_pais)
               AND (seg_marca.oid_marc = zon_regio.marc_oid_marc)
               AND (seg_canal.oid_cana = zon_zona.cana_oid_cana)
               AND (seg_pais.oid_pais = zon_zona.pais_oid_pais)
               AND (zon_regio.oid_regi = zon_zona.zorg_oid_regi)
               AND zon_zona.oid_zona = tablaregistro(x).oid_zona;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).desc_zona := NULL;
          END;
        
          PIPE ROW(tablaregistro(x));
        END LOOP;
      
      END IF;
    
      EXIT WHEN c_benf_deuda%NOTFOUND;
    END LOOP;
    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_FN_BENEF_DEUDA: ' || ls_sqlerrm);
  END sto_fn_benef_deuda;

  /***************************************************************************
  Descripcion       : Genera una tabla virtual para la consulta de
                      Facturacion Adicional
  Fecha Creacion    : 11/04/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  FUNCTION sto_fn_factu_adici
  (
    pscodigopais  VARCHAR2,
    pscodigomarca VARCHAR2,
    pscodigocanal VARCHAR2,
    pscodigoiso   VARCHAR2
  ) RETURN tabla_facturacion_adicional
    PIPELINED IS
  
    CURSOR c_factu_adici IS
      SELECT bc.oid_fact_adic,
             NULL             AS codigo_periodo,
             NULL             AS tipo_cliente,
             NULL             AS subtipo_cliente,
             NULL             AS tipo_clasificacion,
             NULL             AS clasificacion,
             NULL             AS codigo_cliente,
             NULL             AS desc_region,
             NULL             AS desc_zona,
             bc.oid_peri,
             bc.oid_tipo_clie,
             bc.oid_subt_clie,
             bc.oid_tipo_clas,
             bc.oid_clas,
             bc.oid_clie,
             bc.oid_regi,
             bc.oid_zona,
             bc.ind_vali_prom,
             bc.ind_vali_mtmi
        FROM sto_factu_adici bc
       WHERE nvl(bc.oid_tipo_clie, 0) = nvl(NULL, nvl(bc.oid_tipo_clie, 0))
         AND nvl(bc.oid_regi, 0) = nvl(NULL, nvl(bc.oid_regi, 0))
         AND nvl(bc.oid_clie, 0) = nvl(NULL, nvl(bc.oid_clie, 0))
         AND bc.ind_acti = '1';
  
    tablaregistro tabla_facturacion_adicional;
  
  BEGIN
  
    OPEN c_factu_adici;
    LOOP
      FETCH c_factu_adici BULK COLLECT
        INTO tablaregistro LIMIT w_filas;
    
      IF tablaregistro.count > 0 THEN
      
        FOR x IN tablaregistro.first .. tablaregistro.last
        LOOP
        
          IF tablaregistro(x).oid_peri IS NOT NULL THEN
            tablaregistro(x).codigo_periodo := gen_pkg_gener.gen_fn_devuelve_des_perio(tablaregistro(x)
                                                                                       .oid_peri);
          END IF;
        
          BEGIN
            SELECT val_i18n descripcion
              INTO tablaregistro(x).tipo_cliente
              FROM gen_i18n_sicc_comun p,
                   mae_tipo_clien      c,
                   seg_idiom           i
             WHERE p.val_oid = c.oid_tipo_clie
               AND p.idio_oid_idio = i.oid_idio
               AND p.attr_enti = 'MAE_TIPO_CLIEN'
               AND i.cod_iso_idio = pscodigoiso
               AND oid_tipo_clie = tablaregistro(x).oid_tipo_clie;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).tipo_cliente := NULL;
          END;
        
          BEGIN
            SELECT val_i18n descripcion
              INTO tablaregistro(x).subtipo_cliente
              FROM gen_i18n_sicc_comun p,
                   mae_subti_clien     c,
                   mae_tipo_clien      t,
                   seg_idiom           i
             WHERE p.val_oid = c.oid_subt_clie
               AND p.idio_oid_idio = i.oid_idio
               AND p.attr_enti = 'MAE_SUBTI_CLIEN'
               AND t.oid_tipo_clie = c.ticl_oid_tipo_clie
               AND i.cod_iso_idio = pscodigoiso
               AND c.oid_subt_clie = tablaregistro(x).oid_subt_clie;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).subtipo_cliente := NULL;
          END;
        
          BEGIN
            SELECT val_i18n descripcion
              INTO tablaregistro(x).tipo_clasificacion
              FROM gen_i18n_sicc_comun,
                   seg_idiom,
                   mae_tipo_clasi_clien,
                   mae_tipo_clien,
                   mae_subti_clien
             WHERE gen_i18n_sicc_comun.val_oid =
                   mae_tipo_clasi_clien.oid_tipo_clas
               AND gen_i18n_sicc_comun.idio_oid_idio = seg_idiom.oid_idio
               AND gen_i18n_sicc_comun.attr_enti = 'MAE_TIPO_CLASI_CLIEN'
               AND seg_idiom.cod_iso_idio = pscodigoiso
               AND mae_subti_clien.oid_subt_clie =
                   mae_tipo_clasi_clien.sbti_oid_subt_clie
               AND mae_tipo_clien.oid_tipo_clie =
                   mae_subti_clien.ticl_oid_tipo_clie
               AND mae_tipo_clasi_clien.oid_tipo_clas = tablaregistro(x)
                  .oid_tipo_clas;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).tipo_clasificacion := NULL;
          END;
        
          BEGIN
            SELECT val_i18n descripcion
              INTO tablaregistro(x).clasificacion
              FROM gen_i18n_sicc_comun,
                   seg_idiom,
                   mae_clasi,
                   mae_tipo_clasi_clien,
                   mae_subti_clien,
                   mae_tipo_clien
             WHERE gen_i18n_sicc_comun.val_oid = mae_clasi.oid_clas
               AND gen_i18n_sicc_comun.idio_oid_idio = seg_idiom.oid_idio
               AND gen_i18n_sicc_comun.attr_enti = 'MAE_CLASI'
               AND seg_idiom.cod_iso_idio = pscodigoiso
               AND mae_clasi.tccl_oid_tipo_clas =
                   mae_tipo_clasi_clien.oid_tipo_clas
               AND mae_subti_clien.oid_subt_clie =
                   mae_tipo_clasi_clien.sbti_oid_subt_clie
               AND mae_tipo_clien.oid_tipo_clie =
                   mae_subti_clien.ticl_oid_tipo_clie
               AND mae_clasi.oid_clas = tablaregistro(x).oid_clas;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).clasificacion := NULL;
          END;
        
          BEGIN
            SELECT ci.cod_clie
              INTO tablaregistro(x).codigo_cliente
              FROM mae_clien ci
             WHERE ci.pais_oid_pais =
                   gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais)
               AND ci.oid_clie = tablaregistro(x).oid_clie;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).codigo_cliente := NULL;
          END;
        
          BEGIN
            SELECT upper(nvl(zon_regio.des_regi, ' ')) descripcion
              INTO tablaregistro(x).desc_region
              FROM seg_pais,
                   seg_marca,
                   seg_canal,
                   zon_regio
             WHERE (zon_regio.ind_acti = 1)
               AND (seg_pais.cod_pais = pscodigopais)
               AND (seg_marca.cod_marc = pscodigomarca)
               AND (seg_canal.cod_cana = pscodigocanal)
               AND (seg_pais.oid_pais = zon_regio.pais_oid_pais)
               AND (seg_marca.oid_marc = zon_regio.marc_oid_marc)
               AND (seg_canal.oid_cana = zon_regio.cana_oid_cana)
               AND zon_regio.oid_regi = tablaregistro(x).oid_regi;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).desc_region := NULL;
          END;
        
          BEGIN
            SELECT nvl(zon_zona.des_zona, ' ') AS descripcion
              INTO tablaregistro(x).desc_zona
              FROM zon_zona,
                   seg_pais,
                   seg_canal,
                   seg_marca,
                   zon_regio
             WHERE (zon_zona.ind_acti = 1)
               AND (seg_pais.cod_pais = pscodigopais)
               AND (seg_marca.cod_marc = pscodigomarca)
               AND (seg_canal.cod_cana = pscodigocanal)
               AND (seg_canal.oid_cana = zon_regio.cana_oid_cana)
               AND (seg_pais.oid_pais = zon_regio.pais_oid_pais)
               AND (seg_marca.oid_marc = zon_regio.marc_oid_marc)
               AND (seg_canal.oid_cana = zon_zona.cana_oid_cana)
               AND (seg_pais.oid_pais = zon_zona.pais_oid_pais)
               AND (zon_regio.oid_regi = zon_zona.zorg_oid_regi)
               AND zon_zona.oid_zona = tablaregistro(x).oid_zona;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).desc_zona := NULL;
          END;
        
          PIPE ROW(tablaregistro(x));
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_factu_adici%NOTFOUND;
    END LOOP;
  
    CLOSE c_factu_adici;
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_FN_FACTU_ADICI: ' || ls_sqlerrm);
  END sto_fn_factu_adici;

  /**************************************************************************
  Descripcion       : MArca registros a los que no se debe ejecutar validacion
  Fecha Creacion    : 10/12/2012
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_exclu_valid
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    psnumeroproceso       VARCHAR2,
    psusuario             VARCHAR2
  ) IS
  
    CURSOR c_exclu IS
      SELECT occ.num_lote,
             occ.sec_nume_docu,
             (SELECT MIN(c.cod_orig)
                FROM sto_combi_orige_docum c
               WHERE c.cod_pais = occ.cod_pais
                 AND c.cod_tipo_docu = occ.cod_tipo_docu
                 AND c.ind_rece_ocr = occ.ind_rece_ocr
                 AND c.ind_rece_web = occ.ind_rece_web
                 AND c.ind_rece_dd = occ.ind_rece_dd
                 AND c.ind_rece_digi = occ.ind_rece_digi
                 AND c.ind_rece_cc = occ.ind_rece_cc
                 AND c.ind_rece_mens = occ.ind_rece_mens
                 AND c.ind_rece_onli = occ.ind_rece_onli
                 AND c.ind_rece_ivr = occ.ind_rece_ivr),
             occ.cod_peri,
             occ.cod_clie,
             trunc(occ.fec_modi)
        FROM sto_docum_digit occ
       WHERE psnumeroproceso = occ.num_proc
         AND occ.cod_tipo_docu = pscodigotipodocumento
         AND occ.cod_pais = pscodigopais
         AND occ.ind_envi = '0'
         AND occ.ind_rech = '0'
         AND ((pscodigovalidanterior IS NULL AND
             occ.cod_ulti_vali_ejec IS NULL) OR
             (occ.cod_ulti_vali_ejec = occ.cod_ulti_vali_exit AND
             occ.cod_ulti_vali_exit = pscodigovalidanterior));
  
    TYPE t_num_lote IS TABLE OF sto_docum_digit.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF sto_docum_digit.sec_nume_docu%TYPE;
    TYPE t_cod_orig IS TABLE OF sto_orige_docum.cod_orig%TYPE;
    TYPE t_cod_peri IS TABLE OF sto_docum_digit.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF sto_docum_digit.cod_clie%TYPE;
    TYPE t_fec_modi IS TABLE OF sto_docum_digit.fec_modi%TYPE;
  
    v_num_lote      t_num_lote;
    v_sec_nume_docu t_sec_nume_docu;
    v_cod_orig      t_cod_orig;
    v_cod_peri      t_cod_peri;
    v_cod_clie      t_cod_clie;
    v_fec_modi      t_fec_modi;
  
    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
  
    i BINARY_INTEGER := 0;
  
    lnnumregistros NUMBER(10);
  
    lsindexcluyevalidacion sto_tipo_docum_digit.ind_excl_vali%TYPE;
  
    lfecfactu sto_exclu_valid.fec_proc%TYPE;
  
  BEGIN
  
    SELECT nvl(MIN(ind_excl_valid), 'N')
      INTO lsindexcluyevalidacion
      FROM sto_tipo_docum_digit t,
           sto_param_valid      v
     WHERE t.cod_pais = v.cod_pais
       AND t.cod_tipo_docu = v.cod_tipo_docu
       AND t.cod_tipo_docu = pscodigotipodocumento
       AND t.cod_pais = pscodigopais
       AND t.ind_excl_vali = 'S'
       AND v.cod_vali = pscodigovalidactual;
  
    IF lsindexcluyevalidacion = 'S' THEN
    
      SELECT a.fec_proc
        INTO lfecfactu
        FROM bas_ctrl_fact a
       WHERE a.ind_camp_act = '1'
         AND a.sta_camp = '0';
    
      OPEN c_exclu;
      LOOP
        FETCH c_exclu BULK COLLECT
          INTO v_num_lote,
               v_sec_nume_docu,
               v_cod_orig,
               v_cod_peri,
               v_cod_clie,
               v_fec_modi LIMIT rows;
      
        IF v_sec_nume_docu.count > 0 THEN
        
          FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
          LOOP
          
            IF pscodigotipodocumento = 'OCC' THEN
              v_fec_modi(i) := lfecfactu;
            END IF;
          
            SELECT COUNT(1)
              INTO lnnumregistros
              FROM mae_clien_unida_admin mcua,
                   zon_terri_admin       zta,
                   zon_terri             zt,
                   zon_secci             zs,
                   zon_zona              zz,
                   zon_regio             zr,
                   mae_clien             mc,
                   sto_exclu_valid       ex
             WHERE mcua.ztad_oid_terr_admi = zta.oid_terr_admi
               AND zta.zscc_oid_secc = zs.oid_secc
               AND zta.terr_oid_terr = zt.oid_terr
               AND zs.zzon_oid_zona = zz.oid_zona
               AND zz.zorg_oid_regi = zr.oid_regi
               AND mcua.ind_acti = 1
               AND zta.ind_borr = 0
               AND mc.oid_clie = mcua.clie_oid_clie
               AND mc.cod_clie = v_cod_clie(i)
               AND ex.cod_tipo_docu = pscodigotipodocumento
               AND (ex.cod_orig = v_cod_orig(i) OR ex.cod_orig IS NULL)
               AND (ex.cod_regi = zr.cod_regi OR ex.cod_regi IS NULL)
               AND (ex.cod_zona = zz.cod_zona OR ex.cod_zona IS NULL)
               AND (ex.cod_peri = v_cod_peri(i) OR ex.cod_peri IS NULL)
               AND (ex.cod_clie = mc.cod_clie OR ex.cod_clie IS NULL)
               AND (ex.fec_proc = v_fec_modi(i) OR ex.fec_proc IS NULL)
               AND ex.cod_vali = pscodigovalidactual;
          
            IF lnnumregistros > 0 THEN
              UPDATE sto_docum_digit occ
                 SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.fec_modi           = SYSDATE,
                     occ.usu_modi           = psusuario
               WHERE occ.cod_pais = pscodigopais
                 AND occ.cod_tipo_docu = pscodigotipodocumento
                 AND occ.num_lote = v_num_lote(i)
                 AND occ.sec_nume_docu = v_sec_nume_docu(i);
            
            END IF;
          
          END LOOP;
        
        END IF;
      
        EXIT WHEN c_exclu%NOTFOUND;
      END LOOP;
      CLOSE c_exclu;
    END IF;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_EXCLU_VALID: ' || ls_sqlerrm);
  END sto_pr_exclu_valid;
  /***************************************************************************
   Descripcion       : Depura Informacion Orden de Compra
   Fecha Creacion    : 03/10/2012
   Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_depur_orden_compr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodocumento VARCHAR2,
    psnumeroproceso       VARCHAR2,
    pscodigoperiodo       VARCHAR2
  ) IS
  
    CURSOR c_pedidos
    (
      vnoidperiodoactual NUMBER,
      vnoidtiposolisoc   NUMBER
    ) IS
      SELECT oid_soli_cabe
        FROM ped_solic_cabec p
       WHERE p.perd_oid_peri = vnoidperiodoactual
         AND p.esso_oid_esta_soli NOT IN (2, 3)
         AND p.grpr_oid_grup_proc = 1
         AND p.tspa_oid_tipo_soli_pais = vnoidtiposolisoc
         AND NOT EXISTS
       (SELECT 1
                FROM int_solic_conso_cabec s
               WHERE s.soca_oid_soli_cabe_refe = p.oid_soli_cabe);
  
    TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE;
    v_oid_soli_cabe t_oid_soli_cabe;
  
    rows NATURAL := 1000; -- Number of rows to process at a time
    i    BINARY_INTEGER := 0;
  
    lnoidperiodoactual cra_perio.oid_peri%TYPE;
  
    lnoidtiposolisoc ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
  
  BEGIN
    lnoidperiodoactual := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);
  
    SELECT tsp.oid_tipo_soli_pais
      INTO lnoidtiposolisoc
      FROM ped_tipo_solic_pais tsp,
           ped_tipo_solic      ts
     WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
       AND ts.cod_tipo_soli = 'SOC';
  
    OPEN c_pedidos(lnoidperiodoactual, lnoidtiposolisoc);
    LOOP
      FETCH c_pedidos BULK COLLECT
        INTO v_oid_soli_cabe LIMIT rows;
      IF v_oid_soli_cabe.count > 0 THEN
        FORALL i IN 1 .. v_oid_soli_cabe.count
          DELETE FROM car_soli_entr_bloq a
           WHERE a.soca_oid_soli_cabe = v_oid_soli_cabe(i);
      
        FORALL i IN 1 .. v_oid_soli_cabe.count
          DELETE FROM ped_solic_posic a
           WHERE a.soca_oid_soli_cabe = v_oid_soli_cabe(i);
      
        FORALL i IN 1 .. v_oid_soli_cabe.count
          DELETE FROM ped_solic_cabec a
           WHERE a.oid_soli_cabe = v_oid_soli_cabe(i);
      END IF;
      EXIT WHEN c_pedidos%NOTFOUND;
    END LOOP;
    CLOSE c_pedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_PR_DEPUR_ORDEN_COMPR: ' ||
                              ls_sqlerrm);
  END sto_pr_depur_orden_compr;

  /**************************************************************************
  Descripcion       : Devuelve la fecha real de entrega que obtiene de
                      la interfaz  - Colombia -
  --------------------------------------------------------------------------
  Fecha Creacion    : 10/01/2013
  Parametros Entrada: pscodigocliente
                      pscodigoperiodo
  --------------------------------------------------------------------------
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_fn_devue_fecha_entre
  (
    pscodigocliente VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lsfec VARCHAR2(10);
  
  BEGIN
  
    BEGIN
      SELECT to_char(psp.fec, 'dd/mm/yyyy')
        INTO lsfec
        FROM ped_tipo_solic_pais tspa,
             ped_tipo_solic      ts,
             ped_solic_cabec     sc,
             mae_clien           m,
             cra_perio           cp,
             seg_perio_corpo     pc,
             ped_segui_pedid     psp
       WHERE tspa.oid_tipo_soli_pais = sc.tspa_oid_tipo_soli_pais
         AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
         AND ts.cod_tipo_soli = 'C1'
         AND m.oid_clie = sc.clie_oid_clie
         AND cp.oid_peri = sc.perd_oid_peri
         AND pc.oid_peri = cp.peri_oid_peri
         AND psp.soca_oid_soli_cabe = sc.oid_soli_cabe
         AND pc.cod_peri = pscodigoperiodo
         AND m.cod_clie = pscodigocliente;
    EXCEPTION
      WHEN no_data_found THEN
        lsfec := ' ';
    END;
  
    RETURN lsfec;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END sto_fn_devue_fecha_entre;

  /**************************************************************************
  Descripcion       : Devuelve la fecha real de entrega que obtiene de
                      la interfaz  - Colombia -
  --------------------------------------------------------------------------
  Fecha Creacion    : 10/01/2013
  Parametros Entrada: pscodigocliente
                      pscodigoperiodo
  --------------------------------------------------------------------------
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_fn_fecha_entre_forma
  (
    pscodigocliente VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) RETURN VARCHAR2 IS
  
    lsfec VARCHAR2(1000);
  
  BEGIN
  
    BEGIN
      SELECT to_char(psp.fec,
                     'dd "de" fmmonth "("fmday")    (aa/mm/dd)"  yy/mm/dd')
        INTO lsfec
        FROM ped_tipo_solic_pais tspa,
             ped_tipo_solic      ts,
             ped_solic_cabec     sc,
             mae_clien           m,
             cra_perio           cp,
             seg_perio_corpo     pc,
             ped_segui_pedid     psp
       WHERE tspa.oid_tipo_soli_pais = sc.tspa_oid_tipo_soli_pais
         AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
         AND ts.cod_tipo_soli = 'C1'
         AND m.oid_clie = sc.clie_oid_clie
         AND cp.oid_peri = sc.perd_oid_peri
         AND pc.oid_peri = cp.peri_oid_peri
         AND psp.soca_oid_soli_cabe = sc.oid_soli_cabe
         AND pc.cod_peri = pscodigoperiodo
         AND m.cod_clie = pscodigocliente;
    EXCEPTION
      WHEN no_data_found THEN
        lsfec := ' ';
    END;
  
    RETURN lsfec;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN NULL;
  END sto_fn_fecha_entre_forma;

  /**************************************************************************
  Descripcion       : Carga la tabla temporal que contiene la informacion
                      para mostrar en la pantalla de seguimiento de pedidos
                      - Colombia -
  --------------------------------------------------------------------------
  Fecha Creacion    : 10/01/2013
  Parametros Entrada: pscodigocliente
                      pscodigoperiodo
  --------------------------------------------------------------------------
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_carga_segui_pedid
  (
    pscodigocliente VARCHAR2,
    pscodigoperiodo VARCHAR2
  ) IS
  
    CURSOR c_pedidos IS
      SELECT sec_nume_docu,
             soca_oid_soli_cabe_refe
        FROM (SELECT *
                FROM ped_histo_solic_conso_cabec cab
               WHERE cab.cod_clie = pscodigocliente
                 AND cab.cod_peri = pscodigoperiodo
              UNION
              SELECT *
                FROM int_solic_conso_cabec cab
               WHERE cab.cod_clie = pscodigocliente
                 AND cab.cod_peri = pscodigoperiodo)
       ORDER BY fec_soli,
                num_lote;
  
    TYPE t_secnumedocu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_soca_oid_soli_cabe_refe IS TABLE OF int_solic_conso_cabec.soca_oid_soli_cabe_refe%TYPE;
  
    v_secnumedocu             t_secnumedocu;
    v_soca_oid_soli_cabe_refe t_soca_oid_soli_cabe_refe;
    rows                      NATURAL := 1000; -- Numero de filas a procesar cada vez
    i                         BINARY_INTEGER := 0;
  
    lsparametromuestraot sto_param_gener_occrr.val_param%TYPE;
    lsparametromuestraPS sto_param_gener_occrr.val_param%TYPE;
    pscodigopais         sto_param_gener_occrr.cod_pais%TYPE;
    lv_cod_cons          VARCHAR2(15);
  
  BEGIN
  
    SELECT cod_pais INTO pscodigopais FROM bas_ctrl_fact WHERE rownum = 1;
  
    lsparametromuestraot := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                 'STO_IND_OT');
                                                                 
    lsparametromuestraPS := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                 'STO_IND_PS'),'N');
  
    OPEN c_pedidos;
    LOOP
      FETCH c_pedidos BULK COLLECT
        INTO v_secnumedocu,
             v_soca_oid_soli_cabe_refe LIMIT rows;
      IF v_secnumedocu.count > 0 THEN
        FOR i IN v_secnumedocu.first .. v_secnumedocu.last
        LOOP
        
          INSERT INTO ped_gtt_segui_pedid
            (val_grup,
             val_orde,
             des_etap,
             des_esta,
             cod_clie,
             cod_peri,
             cod_pais)
            SELECT 'G' || i,
                   '0',
                   'Fecha exacta',
                   TRIM(to_char(fec, 'DAY', 'NLS_DATE_LANGUAGE = SPANISH')) || ' ' ||
                   to_char(fec, 'DD') || ' DE ' ||
                   TRIM(to_char(fec, 'MONTH', 'NLS_DATE_LANGUAGE = SPANISH')) ||
                   ' DEL ' || to_char(fec, 'YYYY'),
                   pscodigocliente,
                   pscodigoperiodo,
                   pscodigopais
              FROM ped_solic_cabec sc,
                   ped_solic_cabec scc,
                   ped_segui_pedid psp
             WHERE v_soca_oid_soli_cabe_refe(i) = sc.oid_soli_cabe
               AND scc.oid_soli_cabe = sc.soca_oid_soli_cabe
               AND psp.soca_oid_soli_cabe = scc.oid_soli_cabe;
        
          ----------------------------------------
          -- Informacion de la etapa de escaneo
          ----------------------------------------
          /*---------------------------------------------------------------------------------------------
             Estados :
               Normal    : El pedido llega aprobado de OCR
               Rechazado : El pedido llega rechazado de OCR. (En Novedades colocar el motivo de rechazo)
          ---------------------------------------------------------------------------------------------*/
          INSERT INTO ped_gtt_segui_pedid
            (val_grup,
             val_orde,
             des_etap,
             des_esta,
             fec_regi,
             des_nove,
             cod_clie,
             cod_peri,
             cod_pais)
            SELECT 'G' || i,
                   1,
                   'Escaneo',
                   decode(ind_erro_rech, 0, 'Normal', 'Rechazado'),
                   decode(ind_erro_rech, 0, fec_digi, fec_modi),
                   initcap(decode(ind_erro_rech,
                                  0,
                                  NULL,
                                  (SELECT rm.des_moti_rech
                                     FROM sto_recha_motiv rm
                                    WHERE rm.cod_tipo_docu = 'OCC'
                                      AND rm.cod_moti_rech = x.cod_moti_rech))),
                   REPLACE(cod_clie, 'R'),
                   cod_peri,
                   cod_pais
              FROM (SELECT *
                      FROM int_solic_conso_cabec ca
                     WHERE ca.sec_nume_docu = v_secnumedocu(i)
                       AND ca.cod_clie LIKE '%' || pscodigocliente
                       AND ca.cod_peri = pscodigoperiodo
                    UNION
                    SELECT *
                      FROM ped_histo_solic_conso_cabec ca
                     WHERE ca.sec_nume_docu = v_secnumedocu(i)
                       AND ca.cod_clie LIKE '%' || pscodigocliente
                       AND ca.cod_peri = pscodigoperiodo) x;
        
          ------------------------------------------
          -- Informacion de la etapa de facturacion
          ------------------------------------------
          /*---------------------------------------------------------------------------------------------
             Estados :
               Normal    : El pedido fue facturado. En Novedades Se debe mostrar el número de la factura
                           asignado y el número total de cajas que lo componen una vez cubicado.
               Rechazado : El pedido fue rechazado por alguna de los motivos definidos.
                           En Novedades Se debe mostrar el motivo por el cual fue rechazado.
               Anulado   : El pedido fue facturado, pero se anula por el usuario.
                           En Novedades Debe mostrar el motivo por el cual se anuló y el usuario que lo hizo.
          ---------------------------------------------------------------------------------------------*/
          -- Solo errores
          INSERT INTO ped_gtt_segui_pedid
            (val_grup,
             val_orde,
             des_etap,
             des_esta,
             fec_regi,
             des_nove,
             cod_clie,
             cod_peri,
             cod_pais)
            SELECT DISTINCT 'G' || i,
                            '2',
                            'Facturación',
                            'Rechazado',
                            trunc(excep.fec_digi),
                            mv.des_larg_mens,
                            REPLACE(cab.cod_clie, 'R'),
                            cab.cod_peri,
                            cab.cod_pais
              FROM (SELECT *
                      FROM ped_histo_solic_conso_cabec c
                     WHERE c.sec_nume_docu = v_secnumedocu(i)
                       AND c.cod_clie LIKE '%' || pscodigocliente
                       AND c.cod_peri = pscodigoperiodo
                    UNION
                    SELECT *
                      FROM int_solic_conso_cabec c
                     WHERE c.sec_nume_docu = v_secnumedocu(i)
                       AND c.cod_clie LIKE '%' || pscodigocliente
                       AND c.cod_peri = pscodigoperiodo) cab,
                   (SELECT *
                      FROM sto_docum_digit dd
                     WHERE dd.cod_tipo_docu = 'OCC'
                       AND dd.sec_nume_docu = v_secnumedocu(i)
                       AND dd.cod_clie LIKE '%' || pscodigocliente
                       AND dd.cod_peri = pscodigoperiodo
                    UNION
                    SELECT *
                      FROM sto_histo_docum_digit dd
                     WHERE dd.cod_tipo_docu = 'OCC'
                       AND dd.sec_nume_docu = v_secnumedocu(i)
                       AND dd.cod_clie LIKE '%' || pscodigocliente
                       AND dd.cod_peri = pscodigoperiodo) sto,
                   (SELECT *
                      FROM sto_audit_excep ae
                     WHERE ae.cod_tipo_docu = 'OCC'
                       AND ae.sec_nume_docu = v_secnumedocu(i)
                    UNION
                    SELECT *
                      FROM sto_histo_audit_excep ae
                     WHERE ae.cod_tipo_docu = 'OCC'
                       AND ae.sec_nume_docu = v_secnumedocu(i)) excep,
                   sto_param_valid pv,
                   sto_mensa_valid mv
             WHERE cab.sec_nume_docu = sto.sec_nume_docu
               AND cab.sec_nume_docu = excep.sec_nume_docu
               AND pv.cod_vali = excep.cod_vali
               AND mv.cod_vali = pv.cod_vali;
        
          -- Solo Facturados
          INSERT INTO ped_gtt_segui_pedid
            (val_grup,
             val_orde,
             des_etap,
             des_esta,
             fec_regi,
             des_nove,
             cod_clie,
             cod_peri,
             cod_pais)
            (SELECT 'G' || i,
                    3 orden,
                    'Facturación' etapa,
                    decode(sc.grpr_oid_grup_proc,
                           5,
                           decode((SELECT con.esso_oid_esta_soli
                                    FROM ped_solic_cabec con
                                   WHERE con.oid_soli_cabe =
                                         sc.soca_oid_soli_cabe),
                                  4,
                                  'Rechazado',
                                  'Facturado'),
                           NULL,
                           'Rechazado',
                           'En GP' || sc.grpr_oid_grup_proc) estado,
                    decode(sc.fec_fact,
                           NULL,
                           sto.fec_modi,
                           to_char(sc.fec_fact)) fecha,
                    initcap(decode(sc.grpr_oid_grup_proc,
                                   5,
                                   decode((SELECT con.esso_oid_esta_soli
                                            FROM ped_solic_cabec con
                                           WHERE con.oid_soli_cabe =
                                                 sc.soca_oid_soli_cabe),
                                          4,
                                          (SELECT DISTINCT ('Motivo : ' ||
                                                           xxx.descripcion ||
                                                           ' Usuario : ' ||
                                                           rcr.cod_usua_ingr)
                                             FROM rec_cabec_recla rcr,
                                                  rec_opera_recla rop,
                                                  rec_linea_opera_recla rlo,
                                                  (SELECT gen.val_i18n AS descripcion,
                                                          base.oid_moti_devo
                                                     FROM gen_i18n_sicc_pais gen,
                                                          seg_idiom          idi,
                                                          rec_motiv_devol    base
                                                    WHERE gen.idio_oid_idio =
                                                          idi.oid_idio
                                                      AND base.oid_moti_devo =
                                                          gen.val_oid
                                                      AND idi.cod_iso_idio = 'es'
                                                      AND gen.attr_enti =
                                                          'REC_MOTIV_DEVOL') xxx
                                            WHERE rcr.oid_cabe_recl =
                                                  rop.care_oid_cabe_recl
                                              AND rop.oid_oper_recl =
                                                  rlo.opre_oid_oper_recl
                                              AND xxx.oid_moti_devo =
                                                  rlo.modv_oid_moti_devo
                                              AND rcr.soca_oid_soli_cabe =
                                                  sc.soca_oid_soli_cabe
                                              AND rcr.esre_oid_esta_recl <> 5),
                                          (SELECT 'Nro.Factura : ' ||
                                                  dc.val_seri_docu_lega || '-' ||
                                                  dc.num_docu_lega
                                             FROM fac_docum_conta_cabec dc
                                            WHERE dc.soca_oid_soli_cabe =
                                                  sc.soca_oid_soli_cabe
                                              AND dc.tido_oid_tipo_docu IN
                                                  (1, 29)
                                              AND rownum = 1)))) novedades,
                    REPLACE(cab.cod_clie, 'R'),
                    cab.cod_peri,
                    cab.cod_pais
               FROM (SELECT *
                       FROM ped_histo_solic_conso_cabec c
                      WHERE c.sec_nume_docu = v_secnumedocu(i)
                        AND c.cod_clie LIKE '%' || pscodigocliente
                        AND c.cod_peri = pscodigoperiodo
                     UNION
                     SELECT *
                       FROM int_solic_conso_cabec c
                      WHERE c.sec_nume_docu = v_secnumedocu(i)
                        AND c.cod_clie LIKE '%' || pscodigocliente
                        AND c.cod_peri = pscodigoperiodo) cab,
                    (SELECT *
                       FROM sto_docum_digit dd
                      WHERE dd.cod_tipo_docu = 'OCC'
                        AND dd.sec_nume_docu = v_secnumedocu(i)
                        AND dd.cod_clie LIKE '%' || pscodigocliente
                        AND dd.cod_peri = pscodigoperiodo
                     UNION
                     SELECT *
                       FROM sto_histo_docum_digit dd
                      WHERE dd.cod_tipo_docu = 'OCC'
                        AND dd.sec_nume_docu = v_secnumedocu(i)
                        AND dd.cod_clie LIKE '%' || pscodigocliente
                        AND dd.cod_peri = pscodigoperiodo) sto,
                    ped_solic_cabec sc
              WHERE cab.soca_oid_soli_cabe_refe = sc.oid_soli_cabe
                AND cab.sec_nume_docu = sto.sec_nume_docu);
        
          ------------------------------------------
          -- Informacion de la etapa de Embalaje
          ------------------------------------------
          /*---------------------------------------------------------------------------------------------
             Estados :
               Terminado   : Si el pedido fue armado en APE
          ---------------------------------------------------------------------------------------------*/
        
          INSERT INTO ped_gtt_segui_pedid
            (val_grup,
             val_orde,
             des_etap,
             des_esta,
             fec_regi,
             des_nove,
             cod_clie,
             cod_peri,
             cod_pais)
            SELECT 'G' || i,
                   4,
                   'Embalaje',
                   'Terminado',
                   to_date(st.fec_fact, 'yyyy-mm-dd'),
                   initcap('Total cajas : ' || st.tot_caja),
                   TRIM(st.cod_clie),
                   st.cam_fact,
                   cab.cod_pais
              FROM sat_impre_bolet_entre st,
                   (SELECT *
                      FROM int_solic_conso_cabec c
                     WHERE c.cod_clie = pscodigocliente
                       AND c.cod_peri = pscodigoperiodo
                       AND c.sec_nume_docu = v_secnumedocu(i)
                    UNION
                    SELECT *
                      FROM ped_histo_solic_conso_cabec c
                     WHERE c.cod_clie = pscodigocliente
                       AND c.cod_peri = pscodigoperiodo
                       AND c.sec_nume_docu = v_secnumedocu(i)) cab,
                   ped_solic_cabec sc,
                   ped_solic_cabec scc
             WHERE TRIM(st.cod_clie) = cab.cod_clie
               AND st.cam_fact = cab.cod_peri
               AND sc.oid_soli_cabe = cab.soca_oid_soli_cabe_refe
               AND sc.soca_oid_soli_cabe = scc.oid_soli_cabe
               AND scc.val_nume_soli = st.nro_pedi;
        
          ------------------------------------------
          -- Informacion de la etapa Armado de Pedido
          ------------------------------------------
          /*---------------------------------------------------------------------------------------------
             Estados :
               Iniciado   : fecha y hora en que el pedido inicia su armado,
               siendo esta fecha y hora la de la inducción del pedido.
          ---------------------------------------------------------------------------------------------*/
        
          INSERT INTO ped_gtt_segui_pedid
            (val_grup,
             val_orde,
             des_etap,
             des_esta,
             fec_regi,
             des_nove,
             cod_clie,
             cod_peri,
             cod_pais)
            SELECT 'G' || i,
                   4,
                   'Armado - APE',
                   'Inicio',
                   to_date(ia.fec_inic_arma || ia.hor_inic_arma,
                           'yyyy-mm-dd hh24:mi:ss'),
                   '',
                   TRIM(ia.cod_cons),
                   cab.cod_peri,
                   cab.cod_pais
              FROM ape_inici_armad_pedid ia,
                   (SELECT *
                      FROM int_solic_conso_cabec c
                     WHERE TRIM(c.cod_clie) = pscodigocliente
                       AND c.cod_peri = pscodigoperiodo
                       AND c.sec_nume_docu = v_secnumedocu(i)
                    UNION
                    SELECT *
                      FROM ped_histo_solic_conso_cabec c
                     WHERE TRIM(c.cod_clie) = pscodigocliente
                       AND c.cod_peri = pscodigoperiodo
                       AND c.sec_nume_docu = v_secnumedocu(i)) cab,
                   ped_solic_cabec sc,
                   ped_solic_cabec scc
             WHERE TRIM(ia.cod_cons) = cab.cod_clie
               AND sc.oid_soli_cabe = cab.soca_oid_soli_cabe_refe
               AND sc.soca_oid_soli_cabe = scc.oid_soli_cabe
               AND scc.val_nume_soli = ia.num_pedi;
        
          ------------------------------------------
          -- Informacion de la etapa de Chequeo
          ------------------------------------------
          /*---------------------------------------------------------------------------------------------
             Estados :
               Si/No si fue chequeado
          ---------------------------------------------------------------------------------------------*/
        
          -- Chequeo configurado
          INSERT INTO ped_gtt_segui_pedid
            (val_grup,
             val_orde,
             des_etap,
             des_esta,
             fec_regi,
             des_nove,
             cod_clie,
             cod_peri,
             cod_pais)
            SELECT 'G' || i,
                   '5',
                   'Configurado para chequear ',
                   'En ' || t.des_tipo_cheq || ', Origen : ' ||
                   o.des_orig_cheq,
                   scc.fec_fact,
                   '',
                   pscodigocliente,
                   pscodigoperiodo,
                   pscodigopais
              FROM ped_pedid_chequ ch,
                   ped_orige_chequ o,
                   ped_tipo_chequ  t,
                   ped_solic_cabec sc,
                   ped_solic_cabec scc
             WHERE ch.cod_orig_cheq = o.cod_orig_cheq
               AND t.cod_tipo_cheq = ch.cod_tipo_cheq
               AND scc.oid_soli_cabe = sc.soca_oid_soli_cabe
               AND sc.oid_soli_cabe = v_soca_oid_soli_cabe_refe(i)
               AND ch.cod_peri = pscodigoperiodo
               AND ch.val_nume_soli = scc.val_nume_soli;
        
          BEGIN
            SELECT pch.cod_cons
              INTO lv_cod_cons
              FROM ped_solic_cabec     scc,
                   ped_solic_cabec     sc,
                   cra_perio           cp,
                   seg_perio_corpo     spc,
                   ped_tipo_solic_pais tspa,
                   ped_tipo_solic      ts,
                   mae_clien           m,
                   seg_pais            sp,
                   ape_pedid_chequ     pch
             WHERE cp.peri_oid_peri = spc.oid_peri
               AND scc.perd_oid_peri = cp.oid_peri
               AND scc.clie_oid_clie = m.oid_clie
               AND scc.grpr_oid_grup_proc = 5
               AND sp.oid_pais = scc.pais_oid_pais
               AND tspa.oid_tipo_soli_pais = scc.tspa_oid_tipo_soli_pais
               AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
               AND ts.cod_tipo_soli = 'C1'
               AND sc.soca_oid_soli_cabe = scc.oid_soli_cabe
               AND v_soca_oid_soli_cabe_refe(i) = sc.oid_soli_cabe
               AND TRIM(pch.cod_cons) = m.cod_clie
               AND pch.num_pedi = scc.val_nume_soli
               AND m.cod_clie = pscodigocliente
               AND spc.cod_peri = pscodigoperiodo;
          
            -- Resultado del chequeo
            INSERT INTO ped_gtt_segui_pedid
              (val_grup,
               val_orde,
               des_etap,
               des_esta,
               fec_regi,
               des_nove,
               cod_clie,
               cod_peri,
               cod_pais)
              SELECT 'G' || i,
                     6,
                     'Resultado de Chequeo - APE: ',
                     decode(pch.ind_pedi_cheq, 'S', 'Si', '-'),
                     to_date(pch.fec_cheq || pch.hor_cheq,
                             'yyyy-mm-dd hh24:mi:ss'),
                     '',
                     m.cod_clie,
                     spc.cod_peri,
                     sp.cod_pais
                FROM ped_solic_cabec     scc,
                     ped_solic_cabec     sc,
                     cra_perio           cp,
                     seg_perio_corpo     spc,
                     ped_tipo_solic_pais tspa,
                     ped_tipo_solic      ts,
                     mae_clien           m,
                     seg_pais            sp,
                     ape_pedid_chequ     pch
               WHERE cp.peri_oid_peri = spc.oid_peri
                 AND scc.perd_oid_peri = cp.oid_peri
                 AND scc.clie_oid_clie = m.oid_clie
                 AND scc.grpr_oid_grup_proc = 5
                 AND sp.oid_pais = scc.pais_oid_pais
                 AND tspa.oid_tipo_soli_pais = scc.tspa_oid_tipo_soli_pais
                 AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
                 AND ts.cod_tipo_soli = 'C1'
                 AND sc.soca_oid_soli_cabe = scc.oid_soli_cabe
                 AND v_soca_oid_soli_cabe_refe(i) = sc.oid_soli_cabe
                 AND TRIM(pch.cod_cons) = m.cod_clie
                 AND pch.num_pedi = scc.val_nume_soli
                 AND m.cod_clie = pscodigocliente
                 AND spc.cod_peri = pscodigoperiodo;
          EXCEPTION
            WHEN no_data_found THEN
            
              -- Resultado del chequeo
              INSERT INTO ped_gtt_segui_pedid
                (val_grup,
                 val_orde,
                 des_etap,
                 des_esta,
                 fec_regi,
                 des_nove,
                 cod_clie,
                 cod_peri,
                 cod_pais)
                SELECT 'G' || i,
                       6,
                       'Resultado de Chequeo: ',
                       decode((SELECT COUNT(1)
                                FROM ped_solic_cabec isc
                               WHERE isc.oid_soli_cabe = scc.oid_soli_cabe
                                 AND isc.recq_oid_resu_cheq =
                                     (SELECT oid_resu_cheq
                                        FROM rec_resul_chequ
                                       WHERE cod_resu_cheq = 'OK')
                                 AND isc.inre_oid_indi_revi = 2),
                              0,
                              'No',
                              'Si'),
                       scc.fec_fact,
                       '',
                       m.cod_clie,
                       spc.cod_peri,
                       sp.cod_pais
                  FROM ped_solic_cabec     scc,
                       ped_solic_cabec     sc,
                       cra_perio           cp,
                       seg_perio_corpo     spc,
                       ped_tipo_solic_pais tspa,
                       ped_tipo_solic      ts,
                       mae_clien           m,
                       seg_pais            sp
                 WHERE cp.peri_oid_peri = spc.oid_peri
                   AND scc.perd_oid_peri = cp.oid_peri
                   AND scc.clie_oid_clie = m.oid_clie
                   AND scc.grpr_oid_grup_proc = 5
                   AND sp.oid_pais = scc.pais_oid_pais
                   AND tspa.oid_tipo_soli_pais =
                       scc.tspa_oid_tipo_soli_pais
                   AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
                   AND ts.cod_tipo_soli = 'C1'
                   AND sc.soca_oid_soli_cabe = scc.oid_soli_cabe
                   AND v_soca_oid_soli_cabe_refe(i) = sc.oid_soli_cabe
                   AND m.cod_clie = pscodigocliente
                   AND spc.cod_peri = pscodigoperiodo;
            
          END;
          ------------------------------------------
          -- Informacion de la etapa de Embarque
          ------------------------------------------
          /*---------------------------------------------------------------------------------------------
             Estados :
               Mensaje: 'El pedido ya fue embarcado en el camion'
          ---------------------------------------------------------------------------------------------*/
        
          INSERT INTO ped_gtt_segui_pedid
            (val_grup,
             val_orde,
             des_etap,
             des_esta,
             fec_regi,
             des_nove,
             cod_clie,
             cod_peri,
             cod_pais)
            SELECT 'G' || i,
                   7,
                   'Embarque',
                   'El pedido ya fue embarcado en el camión',
                   to_date(sg.fec_desp || sg.hor_desp,
                           'yyyymmdd hh24:mi:ss'),
                   '',
                   pscodigocliente,
                   pscodigoperiodo,
                   pscodigopais
              FROM ped_solic_cabec     sc,
                   ped_solic_cabec     scc,
                   mae_clien           m,
                   cra_perio           cp,
                   seg_perio_corpo     pc,
                   ped_tipo_solic_pais tspa,
                   ped_tipo_solic      ts,
                   sat_segui_pedid     sg
             WHERE m.cod_clie = pscodigocliente
               AND m.oid_clie = sc.clie_oid_clie
               AND sc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = pc.oid_peri
               AND sc.grpr_oid_grup_proc = 5
               AND sc.tspa_oid_tipo_soli_pais =
                   fin_pkg_gener.fin_fn_obtie_oid_solic_pais('SOC')
               AND tspa.oid_tipo_soli_pais = scc.tspa_oid_tipo_soli_pais
               AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
               AND ts.cod_tipo_soli = 'C1'
               AND sg.cod_clie = pscodigocliente
               AND sc.soca_oid_soli_cabe = scc.oid_soli_cabe
               AND sg.nro_pedi = scc.val_nume_soli
               AND pc.cod_peri = pscodigoperiodo;
        
          INSERT INTO ped_gtt_segui_pedid
            (val_grup,
             val_orde,
             des_etap,
             des_esta,
             fec_regi,
             des_nove,
             cod_clie,
             cod_peri,
             cod_pais)
            SELECT 'G' || i,
                   7,
                   'Despacho - APE',
                   'El pedido ya fue embarcado en el camión',
                   to_date(ap.fec_desp || ap.hor_desp,
                           'yyyymmdd hh24:mi:ss'),
                   ap.num_asig,
                   pscodigocliente,
                   pscodigoperiodo,
                   pscodigopais
              FROM ped_solic_cabec     sc,
                   ped_solic_cabec     scc,
                   mae_clien           m,
                   cra_perio           cp,
                   seg_perio_corpo     pc,
                   ped_tipo_solic_pais tspa,
                   ped_tipo_solic      ts,
                   ape_pedid_despa     ap
             WHERE m.cod_clie = pscodigocliente
               AND m.oid_clie = sc.clie_oid_clie
               AND sc.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = pc.oid_peri
               AND sc.grpr_oid_grup_proc = 5
               AND sc.tspa_oid_tipo_soli_pais =
                   fin_pkg_gener.fin_fn_obtie_oid_solic_pais('SOC')
               AND tspa.oid_tipo_soli_pais = scc.tspa_oid_tipo_soli_pais
               AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
               AND ts.cod_tipo_soli = 'C1'
               AND TRIM(ap.cod_cons) = pscodigocliente
               AND sc.soca_oid_soli_cabe = scc.oid_soli_cabe
               AND ap.num_pedi = scc.val_nume_soli
               AND pc.cod_peri = pscodigoperiodo;
        
          ------------------------------------------
          -- Informacion de la etapa de Entrega
          ------------------------------------------
          /*---------------------------------------------------------------------------------------------
             Estados :
               Mensaje: Obtiene la descripcion del estado de entrega que vino en la interfaz de OT
          ---------------------------------------------------------------------------------------------*/
        
          INSERT INTO ped_gtt_segui_pedid
            (val_grup,
             val_orde,
             des_etap,
             des_esta,
             fec_regi,
             des_nove,
             cod_clie,
             cod_peri,
             cod_pais)
            SELECT 'G' || i,
                   8,
                   'Entrega',
                   et.val_desc,
                   ot.fec_proc,
                   nov.val_desc,
                   ot.cod_clie,
                   ot.cod_peri,
                   ot.cod_pais
              FROM (SELECT s.*
                      FROM int_solic_conso_orden_trans s,
                           sto_docum_digit             d
                     WHERE s.cod_peri = pscodigoperiodo
                       AND s.cod_clie = pscodigocliente
                       AND d.sec_nume_docu = s.sec_nume_docu
                       AND d.cod_tipo_docu = 'OT'
                       AND d.ind_envi IN
                           (SELECT val_param
                              FROM sto_param_gener_occrr
                             WHERE cod_para LIKE 'STO_IND_OT%')
                    UNION
                    SELECT s.*
                      FROM int_histo_conso_orden_trans s,
                           sto_histo_docum_digit       d
                     WHERE s.cod_peri = pscodigoperiodo
                       AND s.cod_clie = pscodigocliente
                       AND d.sec_nume_docu = s.sec_nume_docu
                       AND d.cod_tipo_docu = 'OT'
                       AND d.ind_envi IN
                           (SELECT val_param
                              FROM sto_param_gener_occrr
                             WHERE cod_para LIKE 'STO_IND_OT%')) ot,
                   sto_estad_orden_trans et,
                   sto_noved_orden_trans nov,
                   ped_solic_cabec sc,
                   ped_solic_cabec scc
             WHERE ot.cod_nove = nov.cod_nove(+)
               AND et.cod_esta_entr(+) =
                   nvl(ot.cod_esta_ent2, ot.cod_esta_entr)
               AND et.cod_tipo_orde_tran = ot.tip_orde
               AND sc.soca_oid_soli_cabe = scc.oid_soli_cabe
               AND scc.val_nume_soli = ot.num_docu
               AND v_soca_oid_soli_cabe_refe(i) = sc.oid_soli_cabe;

          if lsparametromuestraPS = 'S' then

             ---- Chequeo segun ped_seguid_pedid
            INSERT INTO ped_gtt_segui_pedid
              (val_grup,
               val_orde,
               des_etap,
               des_esta,
               fec_regi,
               des_nove,
               cod_clie,
               cod_peri,
               cod_pais)
              SELECT 'G' || i,
                     6,
                     'Resultado de Chequeo : ',
                     'Si',
                     psp.fec_cheq_inic,
                     '',
                     m.cod_clie,
                     spc.cod_peri,
                     sp.cod_pais
                FROM ped_solic_cabec     scc,
                     ped_solic_cabec     sc,
                     cra_perio           cp,
                     seg_perio_corpo     spc,
                     ped_tipo_solic_pais tspa,
                     ped_tipo_solic      ts,
                     mae_clien           m,
                     seg_pais            sp,
                     ped_segui_pedid     psp
               WHERE cp.peri_oid_peri = spc.oid_peri
                 AND scc.perd_oid_peri = cp.oid_peri
                 AND scc.clie_oid_clie = m.oid_clie
                 AND scc.grpr_oid_grup_proc = 5
                 AND sp.oid_pais = scc.pais_oid_pais
                 AND tspa.oid_tipo_soli_pais = scc.tspa_oid_tipo_soli_pais
                 AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
                 AND ts.cod_tipo_soli = 'C1'
                 AND sc.soca_oid_soli_cabe = scc.oid_soli_cabe
                 AND v_soca_oid_soli_cabe_refe(i) = sc.oid_soli_cabe
                 AND PSP.SOCA_OID_SOLI_CABE = scc.oid_soli_cabe
                 AND m.cod_clie = pscodigocliente
                 AND spc.cod_peri = pscodigoperiodo
                 and psp.fec_cheq_inic is not null
                 and not exists(
                  select 1 from ped_gtt_segui_pedid 
                  where val_grup = 'G' || i 
                  and val_orde = '6');

             ---- Chequeo segun ped_seguid_pedid
            INSERT INTO ped_gtt_segui_pedid
              (val_grup,
               val_orde,
               des_etap,
               des_esta,
               fec_regi,
               des_nove,
               cod_clie,
               cod_peri,
               cod_pais)
              SELECT 'G' || i,
                     7,
                     'Embarque',
                     'Inducido el ' ||
                     to_char(psp.fec_indu,'DD/MM/YYYY HH24:MM:SS'),
                     psp.fec_tran,
                     '',
                     m.cod_clie,
                     spc.cod_peri,
                     sp.cod_pais
                FROM ped_solic_cabec     scc,
                     ped_solic_cabec     sc,
                     cra_perio           cp,
                     seg_perio_corpo     spc,
                     ped_tipo_solic_pais tspa,
                     ped_tipo_solic      ts,
                     mae_clien           m,
                     seg_pais            sp,
                     ped_segui_pedid     psp
               WHERE cp.peri_oid_peri = spc.oid_peri
                 AND scc.perd_oid_peri = cp.oid_peri
                 AND scc.clie_oid_clie = m.oid_clie
                 AND scc.grpr_oid_grup_proc = 5
                 AND sp.oid_pais = scc.pais_oid_pais
                 AND tspa.oid_tipo_soli_pais = scc.tspa_oid_tipo_soli_pais
                 AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
                 AND ts.cod_tipo_soli = 'C1'
                 AND sc.soca_oid_soli_cabe = scc.oid_soli_cabe
                 AND v_soca_oid_soli_cabe_refe(i) = sc.oid_soli_cabe
                 AND PSP.SOCA_OID_SOLI_CABE = scc.oid_soli_cabe
                 AND m.cod_clie = pscodigocliente
                 AND spc.cod_peri = pscodigoperiodo
                 and psp.fec_tran is not null
                 and not exists(
                  select 1 from ped_gtt_segui_pedid 
                  where val_grup = 'G' || i 
                  and val_orde = '7');

                   
             ---- Pedido entregado

            INSERT INTO ped_gtt_segui_pedid
              (val_grup,
               val_orde,
               des_etap,
               des_esta,
               fec_regi,
               des_nove,
               cod_clie,
               cod_peri,
               cod_pais)
              SELECT 'G' || i,
                     8,
                     'Entrega',
                     decode(psp.cod_nove,'01','Tercero en domicilio',
                     decode(psp.cod_nove,'02','Novedad en Direccion',
                     decode(psp.cod_nove,'03','Fuera de zona',
                     decode(psp.cod_nove,'04','en otro domicilio',
                     decode(psp.cod_nove,'05','no hay quien reciba',
                     decode(psp.cod_nove,'06','Siniestro',
                     decode(psp.cod_nove,'07','No acepta pedido',
                     decode(psp.cod_nove,'10','ya no vive en direccion',
                     decode(psp.cod_nove,'11','Solicitado x Belcorp (Anulado)',
                     decode(psp.cod_nove,'12','No visitado',
                     decode(psp.cod_nove,'00','Entrega sin novedad',''
                     ))))))))))),
                     psp.fec_entr,
                     psp.des_obser,
                     m.cod_clie,
                     spc.cod_peri,
                     sp.cod_pais
                FROM ped_solic_cabec     scc,
                     ped_solic_cabec     sc,
                     cra_perio           cp,
                     seg_perio_corpo     spc,
                     ped_tipo_solic_pais tspa,
                     ped_tipo_solic      ts,
                     mae_clien           m,
                     seg_pais            sp,
                     ped_segui_pedid     psp
               WHERE cp.peri_oid_peri = spc.oid_peri
                 AND scc.perd_oid_peri = cp.oid_peri
                 AND scc.clie_oid_clie = m.oid_clie
                 AND scc.grpr_oid_grup_proc = 5
                 AND sp.oid_pais = scc.pais_oid_pais
                 AND tspa.oid_tipo_soli_pais = scc.tspa_oid_tipo_soli_pais
                 AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
                 AND ts.cod_tipo_soli = 'C1'
                 AND sc.soca_oid_soli_cabe = scc.oid_soli_cabe
                 AND v_soca_oid_soli_cabe_refe(i) = sc.oid_soli_cabe
                 AND PSP.SOCA_OID_SOLI_CABE = scc.oid_soli_cabe
                 AND m.cod_clie = pscodigocliente
                 AND spc.cod_peri = pscodigoperiodo
                 and psp.fec_entr is not null
                 and not exists(
                  select 1 from ped_gtt_segui_pedid 
                  where val_grup = 'G' || i 
                  and val_orde = '8');
  
                  
             ---- Chequeo en linea (pedido inventariado)
            INSERT INTO ped_gtt_segui_pedid
              (val_grup,
               val_orde,
               des_etap,
               des_esta,
               fec_regi,
               des_nove,
               cod_clie,
               cod_peri,
               cod_pais)
              SELECT 'G' || i,
                     9,
                     'Inventariado',
                     decode(psp.hor_cheq,'00','No requerido',
                     decode(psp.cod_nove,'01','Inventariado completo',
                     decode(psp.cod_nove,'02','Inventariado incompleto',
                     decode(psp.cod_nove,'03','No inventariado',''
                     )))),
                     psp.fec_cheq,
                     psp.des_obser,
                     m.cod_clie,
                     spc.cod_peri,
                     sp.cod_pais
                FROM ped_solic_cabec     scc,
                     ped_solic_cabec     sc,
                     cra_perio           cp,
                     seg_perio_corpo     spc,
                     ped_tipo_solic_pais tspa,
                     ped_tipo_solic      ts,
                     mae_clien           m,
                     seg_pais            sp,
                     ped_segui_pedid     psp
               WHERE cp.peri_oid_peri = spc.oid_peri
                 AND scc.perd_oid_peri = cp.oid_peri
                 AND scc.clie_oid_clie = m.oid_clie
                 AND scc.grpr_oid_grup_proc = 5
                 AND sp.oid_pais = scc.pais_oid_pais
                 AND tspa.oid_tipo_soli_pais = scc.tspa_oid_tipo_soli_pais
                 AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
                 AND ts.cod_tipo_soli = 'C1'
                 AND sc.soca_oid_soli_cabe = scc.oid_soli_cabe
                 AND v_soca_oid_soli_cabe_refe(i) = sc.oid_soli_cabe
                 AND PSP.SOCA_OID_SOLI_CABE = scc.oid_soli_cabe
                 AND m.cod_clie = pscodigocliente
                 AND spc.cod_peri = pscodigoperiodo
                 and psp.hor_cheq is not null
                 and not exists(
                  select 1 from ped_gtt_segui_pedid 
                  where val_grup = 'G' || i 
                  and val_orde = '9');

             ---- Novedades tiene fecha de entrega en nullo

            INSERT INTO ped_gtt_segui_pedid
              (val_grup,
               val_orde,
               des_etap,
               des_esta,
               fec_regi,
               des_nove,
               cod_clie,
               cod_peri,
               cod_pais)
              SELECT 'G' || i,
                     10,
                     'Novedades',
                     decode(psp.cod_nove,'01','Tercero en domicilio',
                     decode(psp.cod_nove,'02','Novedad en Direccion',
                     decode(psp.cod_nove,'03','Fuera de zona',
                     decode(psp.cod_nove,'04','en otro domicilio',
                     decode(psp.cod_nove,'05','no hay quien reciba',
                     decode(psp.cod_nove,'06','Siniestro',
                     decode(psp.cod_nove,'07','No acepta pedido',
                     decode(psp.cod_nove,'10','ya no vive en direccion',
                     decode(psp.cod_nove,'11','Solicitado x Belcorp (Anulado)',
                     decode(psp.cod_nove,'12','No visitado',
                     decode(psp.cod_nove,'00','Entrega sin novedad',''
                     ))))))))))),
                     psp.fec_nove,
                     psp.des_obser,
                     m.cod_clie,
                     spc.cod_peri,
                     sp.cod_pais
                FROM ped_solic_cabec     scc,
                     ped_solic_cabec     sc,
                     cra_perio           cp,
                     seg_perio_corpo     spc,
                     ped_tipo_solic_pais tspa,
                     ped_tipo_solic      ts,
                     mae_clien           m,
                     seg_pais            sp,
                     ped_segui_pedid     psp
               WHERE cp.peri_oid_peri = spc.oid_peri
                 AND scc.perd_oid_peri = cp.oid_peri
                 AND scc.clie_oid_clie = m.oid_clie
                 AND scc.grpr_oid_grup_proc = 5
                 AND sp.oid_pais = scc.pais_oid_pais
                 AND tspa.oid_tipo_soli_pais = scc.tspa_oid_tipo_soli_pais
                 AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
                 AND ts.cod_tipo_soli = 'C1'
                 AND sc.soca_oid_soli_cabe = scc.oid_soli_cabe
                 AND v_soca_oid_soli_cabe_refe(i) = sc.oid_soli_cabe
                 AND PSP.SOCA_OID_SOLI_CABE = scc.oid_soli_cabe
                 AND m.cod_clie = pscodigocliente
                 AND spc.cod_peri = pscodigoperiodo
                 and psp.fec_entr is null
                 and psp.fec_nove is not null
                 and psp.fec_nove <>  to_Date('01/01/0001','DD/MM/YYYY')
                 and not exists(
                  select 1 from ped_gtt_segui_pedid 
                  where val_grup = 'G' || i 
                  and val_orde = '10');                  

          end if;

        
        END LOOP;
      END IF;
      EXIT WHEN c_pedidos %NOTFOUND;
    END LOOP;
    CLOSE c_pedidos;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR sto_pr_carga_segui_pedid: ' ||
                              ls_sqlerrm);
  END sto_pr_carga_segui_pedid;

  /* PROCEDURE sto_pr_carga_segui_pedid
    (
      pscodigocliente VARCHAR2,
      pscodigoperiodo VARCHAR2
    ) IS
  
      ls_estado    VARCHAR2(100);
      ls_fecha     VARCHAR2(100);
      ls_novedades VARCHAR2(100);
  
    BEGIN
  
      NULL;
      ----------------------------------------
      -- Informacion de la etapa de escaneo
      ----------------------------------------
      \*---------------------------------------------------------------------------------------------
         Estados :
           Normal    : El pedido llega aprobado de OCR
           Rechazado : El pedido llega rechazado de OCR. (En Novedades colocar el motivo de rechazo)
      ---------------------------------------------------------------------------------------------*\
      INSERT INTO ped_gtt_segui_pedid
        (val_orde,
         des_etap,
         des_esta,
         fec_regi,
         des_nove,
         cod_clie,
         cod_peri,
         cod_pais)
        (SELECT 1,
                'Escaneo',
                decode(ca.ind_erro_rech,
                       0,
                       'Normal',
                       'Rechazado'),
                nvl(ca.fec_modi,
                    ca.fec_digi),
                initcap(decode(ca.ind_erro_rech,
                               0,
                               NULL,
                               (SELECT rm.des_moti_rech
                                  FROM sto_recha_motiv rm
                                 WHERE rm.cod_tipo_docu = 'OCC'
                                   AND rm.cod_moti_rech = ca.cod_moti_rech))),
                replace(cod_clie,'R'),
                cod_peri,
                cod_pais
           FROM (SELECT *
                   FROM int_solic_conso_cabec
                 UNION
                 SELECT * FROM ped_histo_solic_conso_cabec) ca
          WHERE ca.cod_peri = pscodigoperiodo
            AND ca.cod_clie LIKE '%' || pscodigocliente);
  
      ------------------------------------------
      -- Informacion de la etapa de facturacion
      ------------------------------------------
      \*---------------------------------------------------------------------------------------------
         Estados :
           Normal    : El pedido fue facturado. En Novedades Se debe mostrar el número de la factura
                       asignado y el número total de cajas que lo componen una vez cubicado.
           Rechazado : El pedido fue rechazado por alguna de los motivos definidos.
                       En Novedades Se debe mostrar el motivo por el cual fue rechazado.
           Anulado   : El pedido fue facturado, pero se anula por el usuario.
                       En Novedades Debe mostrar el motivo por el cual se anuló y el usuario que lo hizo.
      ---------------------------------------------------------------------------------------------*\
      INSERT INTO ped_gtt_segui_pedid
        (val_orde,
         des_etap,
         des_esta,
         fec_regi,
         des_nove,
         cod_clie,
         cod_peri,
         cod_pais)
        (SELECT 2 orden,
                'Facturación' etapa,
                decode(sc.grpr_oid_grup_proc,
                       5,
                       decode((SELECT con.esso_oid_esta_soli
                                FROM ped_solic_cabec con
                               WHERE con.oid_soli_cabe = sc.soca_oid_soli_cabe),
                              4,
                              'Rechazado',
                              'Facturado'),
                       NULL,
                       'Rechazado',
                       'En GP' || sc.grpr_oid_grup_proc) estado,
                --decode(sc.fec_fact,null,cab.fec_modi,sc.fec_fact) fecha,
                decode(sc.fec_fact,
                       NULL,
                       sto.fec_modi,
                       to_char(sc.fec_fact)) fecha,
                initcap(decode(sc.grpr_oid_grup_proc,
                               5,
                               decode((SELECT con.esso_oid_esta_soli
                                        FROM ped_solic_cabec con
                                       WHERE con.oid_soli_cabe = sc.soca_oid_soli_cabe),
                                      4,
                                      (SELECT DISTINCT ('Motivo : ' || xxx.descripcion || ' Usuario : ' ||
                                                       rcr.cod_usua_ingr)
                                         FROM rec_cabec_recla rcr,
                                              rec_opera_recla rop,
                                              rec_linea_opera_recla rlo,
                                              (SELECT gen.val_i18n AS descripcion,
                                                      base.oid_moti_devo
                                                 FROM gen_i18n_sicc_pais gen,
                                                      seg_idiom          idi,
                                                      rec_motiv_devol    base
                                                WHERE gen.idio_oid_idio = idi.oid_idio
                                                  AND base.oid_moti_devo = gen.val_oid
                                                  AND idi.cod_iso_idio = 'es'
                                                  AND gen.attr_enti = 'REC_MOTIV_DEVOL') xxx
                                        WHERE rcr.oid_cabe_recl = rop.care_oid_cabe_recl
                                          AND rop.oid_oper_recl = rlo.opre_oid_oper_recl
                                          AND xxx.oid_moti_devo = rlo.modv_oid_moti_devo
                                          AND rcr.soca_oid_soli_cabe = sc.soca_oid_soli_cabe),
                                      (SELECT 'Nro.Factura : ' ||
                                              \*MAX(*\dc.val_seri_docu_lega || '-' || dc.num_docu_lega--)
                                         FROM fac_docum_conta_cabec dc
                                        WHERE dc.soca_oid_soli_cabe = sc.soca_oid_soli_cabe
                                        and dc.tido_oid_tipo_docu in (1,29)
                                        and rownum = 1
                                        )),
                               NULL,
                               (SELECT mv.des_larg_mens
                                  FROM (SELECT *
                                          FROM sto_docum_digit
                                        UNION
                                        SELECT * FROM sto_histo_docum_digit) dd,
                                       (SELECT *
                                          FROM sto_detal_docum_excep
                                        UNION
                                        SELECT * FROM sto_histo_detal_docum_excep) de,
                                       sto_mensa_valid mv
                                 WHERE dd.sec_nume_docu = cab.sec_nume_docu
                                   AND dd.cod_tipo_docu = 'OCC'
                                   AND de.cod_vali = mv.cod_vali
                                   AND mv.cod_tipo_docu = dd.cod_tipo_docu
                                   AND de.sec_nume_docu = dd.sec_nume_docu
                                   AND rownum = 1))) novedades,
                replace(cab.cod_clie,'R'),
                cab.cod_peri,
                cab.cod_pais
  
           FROM (SELECT *
                   FROM ped_histo_solic_conso_cabec
                 UNION
                 SELECT * FROM int_solic_conso_cabec) cab,
                (SELECT *
                   FROM sto_docum_digit dd
                  WHERE dd.cod_tipo_docu = 'OCC'
                 UNION
                 SELECT * FROM sto_histo_docum_digit dd WHERE dd.cod_tipo_docu = 'OCC') sto,
                ped_solic_cabec sc
          WHERE cab.soca_oid_soli_cabe_refe = sc.oid_soli_cabe(+)
            AND cab.sec_nume_docu = sto.sec_nume_docu
            AND cab.cod_peri = pscodigoperiodo
            AND cab.cod_clie LIKE '%' || pscodigocliente);
  
      ------------------------------------------
      -- Informacion de la etapa de Embalaje
      ------------------------------------------
      \*---------------------------------------------------------------------------------------------
         Estados :
           Terminado   : Si el pedido fue armado en APE
      ---------------------------------------------------------------------------------------------*\
  
      INSERT INTO ped_gtt_segui_pedid
        (val_orde,
         des_etap,
         des_esta,
         fec_regi,
         des_nove,
         cod_clie,
         cod_peri,
         cod_pais)
        (SELECT 4,
                'Embalaje',
                'Terminado',
                to_date(st.fec_fact,
                        'yyyy-mm-dd'),
                initcap('Total cajas : ' || st.tot_caja),
                trim(st.cod_clie),
                st.cam_fact,
                cab.cod_pais
           FROM sat_impre_bolet_entre st,
                (SELECT *
                   FROM int_solic_conso_cabec
                 UNION
                 SELECT * FROM ped_histo_solic_conso_cabec) cab
          WHERE trim(st.cod_clie) = cab.cod_clie
            AND st.cam_fact = cab.cod_peri
            AND cab.cod_clie = pscodigocliente
            AND cab.cod_peri = pscodigoperiodo);
  
      ------------------------------------------
      -- Informacion de la etapa de Chequeo
      ------------------------------------------
      \*---------------------------------------------------------------------------------------------
         Estados :
           Si/No si fue chequeado
      ---------------------------------------------------------------------------------------------*\
  
      INSERT INTO ped_gtt_segui_pedid
        (val_orde,
         des_etap,
         des_esta,
         fec_regi,
         des_nove,
         cod_clie,
         cod_peri,
         cod_pais)
        (SELECT 5,
                'Chequeo',
                decode((SELECT COUNT(1)
                         FROM ped_solic_cabec isc
                        WHERE isc.oid_soli_cabe = sc.oid_soli_cabe
                          AND isc.recq_oid_resu_cheq =
                              (SELECT oid_resu_cheq FROM rec_resul_chequ WHERE cod_resu_cheq = 'OK')
                          AND isc.inre_oid_indi_revi = 2),
                       0,
                       'No',
                       'Si'),
                sc.fec_fact,
                '',
                m.cod_clie,
                spc.cod_peri,
                sp.cod_pais
           FROM ped_solic_cabec     sc,
                cra_perio           cp,
                seg_perio_corpo     spc,
                ped_tipo_solic_pais tspa,
                ped_tipo_solic      ts,
                mae_clien           m,
                seg_pais            sp
          WHERE cp.peri_oid_peri = spc.oid_peri
            AND sc.perd_oid_peri = cp.oid_peri
            AND sc.clie_oid_clie = m.oid_clie
            AND sc.grpr_oid_grup_proc = 5
            AND sp.oid_pais = sc.pais_oid_pais
            AND tspa.oid_tipo_soli_pais = sc.tspa_oid_tipo_soli_pais
            AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
            AND ts.cod_tipo_soli = 'C1'
            AND m.cod_clie = pscodigocliente
            AND spc.cod_peri = pscodigoperiodo);
  
      ------------------------------------------
      -- Informacion de la etapa de Embarque
      ------------------------------------------
      \*---------------------------------------------------------------------------------------------
         Estados :
           Mensaje: 'El pedido ya fue embarcado en el camion'
      ---------------------------------------------------------------------------------------------*\
  
      INSERT INTO ped_gtt_segui_pedid
        (val_orde,
         des_etap,
         des_esta,
         fec_regi,
         des_nove,
         cod_clie,
         cod_peri,
         cod_pais)
        (SELECT 6,
                'Embarque',
                'El pedido ya fue embarcado en el camión',
                to_date(sg.fec_desp || sg.hor_desp,
                        'yyyymmdd hh:mi:ss'),
                '',
                cab.cod_clie,
                cab.cod_peri,
                cab.cod_pais
           FROM ped_solic_cabec sc,
                mae_clien m,
                cra_perio cp,
                seg_perio_corpo pc,
                (SELECT *
                   FROM int_solic_conso_cabec
                 UNION
                 SELECT * FROM ped_histo_solic_conso_cabec) cab,
                ped_tipo_solic_pais tspa,
                ped_tipo_solic ts,
                sat_segui_pedid sg
          WHERE m.cod_clie = cab.cod_clie
            AND m.oid_clie = sc.clie_oid_clie
            AND sc.perd_oid_peri = cp.oid_peri
            AND cp.peri_oid_peri = pc.oid_peri
            AND sc.grpr_oid_grup_proc = 5
            AND tspa.oid_tipo_soli_pais = sc.tspa_oid_tipo_soli_pais
            AND ts.oid_tipo_soli = tspa.tsol_oid_tipo_soli
            AND ts.cod_tipo_soli = 'C1'
            AND sg.cod_clie = cab.cod_clie
            AND sg.nro_pedi = sc.val_nume_soli
            AND pc.cod_peri = pscodigoperiodo
            AND cab.cod_clie = pscodigocliente);
  
      ------------------------------------------
      -- Informacion de la etapa de Entrega
      ------------------------------------------
      \*---------------------------------------------------------------------------------------------
         Estados :
           Mensaje: Obtiene la descripcion del estado de entrega que vino en la interfaz de OT
      ---------------------------------------------------------------------------------------------*\
  
      INSERT INTO ped_gtt_segui_pedid
        (val_orde,
         des_etap,
         des_esta,
         fec_regi,
         des_nove,
         cod_clie,
         cod_peri,
         cod_pais)
        (SELECT 8,
                'Entrega',
                et.val_desc,
                ot.fec_proc,
                '',
                ot.cod_clie,
                ot.cod_peri,
                ot.cod_pais
  
           FROM (SELECT *
                   FROM int_solic_conso_orden_trans
                 UNION
                 SELECT * FROM int_histo_conso_orden_trans) ot,
                sto_estad_orden_trans et,
                (SELECT *
                   FROM sto_docum_digit
                 UNION
                 SELECT * FROM sto_histo_docum_digit) dd
          WHERE dd.sec_nume_docu = ot.sec_nume_docu
            AND dd.cod_tipo_docu = 'OT'
            AND dd.ind_envi = '1'
            AND et.cod_esta_entr = ot.cod_esta_entr
            AND et.cod_tipo_orde_tran = ot.tip_orde
            AND ot.cod_peri = pscodigoperiodo
            AND ot.cod_clie = pscodigocliente);
  
    EXCEPTION
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM,
                             1,
                             250);
        raise_application_error(-20123,
                                'ERROR sto_pr_carga_segui_pedid: ' || ls_sqlerrm);
    END sto_pr_carga_segui_pedid;
  */

  /**************************************************************************
  Descripcion       : Carga la Tabla Temporal que contiene la informacion
                      para mostrar en el reporte de errores STO
                      - Colombia -
  --------------------------------------------------------------------------
  Fecha Creacion    : 08/04/2013
  Parametros Entrada: pscodigopais
                      pscodigoperiodo
                      pscodigousuario
  --------------------------------------------------------------------------
  Autor             : Eduardo Sanchez
  ***************************************************************************/

  PROCEDURE sto_pr_carga_tempo_repo_erro
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pscodigousuario VARCHAR2
  ) IS
  
    lc_cod_tipodocu1_ordencompra   CONSTANT VARCHAR2(4) := 'OCC';
    lc_cod_tipodocu2_solicredito   CONSTANT VARCHAR2(4) := 'SCC';
    lc_cod_tipodocu3_actualizadato CONSTANT VARCHAR2(4) := 'SAD';
    lc_cod_tipodocu4_cyzone        CONSTANT VARCHAR2(4) := 'DCYZ';
    lc_cod_tipodocu5_familiaproteg CONSTANT VARCHAR2(4) := 'FAS';
    lc_cod_tipodocu6_ingresometa   CONSTANT VARCHAR2(4) := 'SIM';
    lc_cod_tipodocu7_contratoejec  CONSTANT VARCHAR2(4) := 'CED';
  
    lsimpresiondocumentos bas_param_pais.val_para%TYPE;
  
    lsfechaproceso bas_ctrl_fact.fec_proc%TYPE;
  
  BEGIN
  
    --Recuperamos el parametro de Documentos STO que van a ser mostrados en el reporte de Errores
    lsimpresiondocumentos := gen_pkg_gener.gen_fn_param_pais(pscodigopais,
                                                             'IMP',
                                                             '006');
  
    SELECT c.fec_proc
      INTO lsfechaproceso
      FROM bas_ctrl_fact c
     WHERE c.sta_camp = '0'
       AND c.ind_camp_act = '1';
  
    DELETE sto_tempo_repor_error WHERE cod_usua = pscodigousuario;
  
    IF (instr(lsimpresiondocumentos, lc_cod_tipodocu7_contratoejec) > 0) THEN
      INSERT INTO sto_tempo_repor_error
        (des_zona,
         des_secc,
         cod_terr,
         cod_tipo_docu,
         num_docu,
         cod_clie,
         nom_clie,
         des_vali,
         ind_orig,
         cod_regi_arri,
         cod_zona_arri,
         cod_peri,
         cod_usua,
         num_orde)
        SELECT gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'DES_ZONA') des_zona,
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'DES_SECC') des_secc,
               /*gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
               'COD_TERR')*/
               -- UA en cod_terri
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'COD_REGI') ||
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'COD_ZONA') ||
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'COD_SECC') /*||
                                                                                                                                                                                                                                                                                                       lpad(gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie, 'COD_TERR'),5,'0')*/ cod_terr,
               --------------------
               dd.cod_tipo_docu,
               dc.num_docu num_docu,
               dc.num_docu_iden, --dc.cod_clie,
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'NOM_CLIE') nom_clie,
               substr(pv.des_vali || nvl2(de.val_obse_mens, ' - ', NULL) ||
                      de.val_obse_mens,
                      1,
                      10),
               decode(dc.ind_orig, 'O', 'OCR', 'W', 'Web') ind_orig,
               dc.cod_regi cod_regi_arri,
               dc.cod_zona cod_zona_arri,
               dd.cod_peri,
               pscodigousuario,
               7
          FROM sto_detal_docum_excep       de,
               sto_docum_digit             dd,
               sto_param_valid             pv,
               int_solic_conso_contr_desar dc
         WHERE de.ind_apro = '0'
           AND dd.sec_nume_docu = de.sec_nume_docu
           AND dd.cod_tipo_docu = de.cod_tipo_docu
           AND dd.ind_rech != '1'
           AND dd.ind_envi = '0'
           AND pv.cod_tipo_docu = dd.cod_tipo_docu
           AND pv.cod_vali = de.cod_vali
           AND dc.sec_nume_docu = dd.sec_nume_docu
           AND dd.cod_peri = pscodigoperiodo
           AND dd.cod_tipo_docu = lc_cod_tipodocu7_contratoejec;
    END IF;
  
    IF (instr(lsimpresiondocumentos, lc_cod_tipodocu1_ordencompra) > 0) THEN
      INSERT INTO sto_tempo_repor_error
        (des_zona,
         des_secc,
         cod_terr,
         cod_tipo_docu,
         num_docu,
         cod_clie,
         nom_clie,
         des_vali,
         num_telf,
         val_deud,
         val_prec,
         cam_ingre,
         cod_regi_arri,
         cod_zona_arri,
         cod_peri,
         cod_usua,
         cod_tipo_docu_deud,
         num_orde)
        SELECT gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'DES_ZONA') des_zona,
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'DES_SECC') des_secc,
               /*gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
               'COD_TERR')*/
               -- UA en cod_terri
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'COD_REGI') ||
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'COD_ZONA') ||
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'COD_SECC') /*||
                                                                                                                                                                                                                                                                                                       lpad(gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie, 'COD_TERR'),5,'0')*/ cod_terr,
               --------------------
               dd.cod_tipo_docu,
               dc.docu_num_docu num_docu,
               -- Cedula --
               nvl((SELECT ci.num_docu_iden
                     FROM mae_clien_ident ci,
                          mae_clien       m
                    WHERE m.oid_clie = ci.clie_oid_clie
                      AND m.cod_clie = dc.cod_clie
                      AND ci.val_iden_docu_prin = '1'),
                   dc.cod_clie), --dc.cod_clie,
               ------------
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'NOM_CLIE') nom_clie,
               pv.des_vali || nvl2(de.val_obse_mens, ' - ', NULL) ||
               de.val_obse_mens,
               /*gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
               'NUM_TELE')*/
               -- Que salgan todos los telefonos --
               sto_fn_devue_telef(dc.cod_clie) num_telf,
               --------------------------------------
               dc.val_sald_deud val_deud,
               /*(SELECT nvl(SUM(det.val_unid_dem * det.val_prec_cata_unit_loca * det.val_fact_repe),
                         0)
                FROM int_solic_conso_detal det,
                     int_solic_conso_cabec cab
               WHERE cab.cod_pais = det.cod_pais
                 AND cab.cod_peri = det.cod_peri
                 AND cab.cod_clie = det.cod_clie
                 AND cab.num_lote = det.num_lote
                 AND cab.cod_pais = dc.cod_pais
                 AND cab.sec_nume_docu = dc.sec_nume_docu
                 AND cab.num_lote = dc.num_lote)*/
               -- ya no se calcula
               decode(de.cod_vali,
                      'OCC-19',
                      decode(dc.val_mont_pedi,
                             0,
                             (SELECT nvl(SUM(det.val_unid_dem *
                                             det.val_prec_cata_unit_loca *
                                             det.val_fact_repe),
                                         0)
                                FROM int_solic_conso_detal det,
                                     int_solic_conso_cabec cab
                               WHERE cab.cod_pais = det.cod_pais
                                 AND cab.cod_peri = det.cod_peri
                                 AND cab.cod_clie = det.cod_clie
                                 AND cab.num_lote = det.num_lote
                                 AND cab.cod_pais = dc.cod_pais
                                 AND cab.sec_nume_docu = dc.sec_nume_docu
                                 AND cab.num_lote = dc.num_lote),
                             dc.val_mont_pedi),
                      dc.val_mont_pedi) val_prec,
               (SELECT sp.cod_peri
                  FROM mae_clien_prime_conta pc,
                       cra_perio             cp,
                       seg_perio_corpo       sp
                 WHERE pc.perd_oid_peri = cp.oid_peri
                   AND cp.peri_oid_peri = sp.oid_peri
                   AND pc.clie_oid_clie = dc.clie_oid_clie) cam_ingre,
               dc.cod_regi_arri,
               dc.cod_zona_arri,
               dd.cod_peri,
               pscodigousuario,
               decode(de.cod_vali, 'OCC-19', 'OCCD', 'OCCO'),
               decode(de.cod_vali, 'OCC-19', 1, 2)
          FROM sto_detal_docum_excep de,
               sto_docum_digit       dd,
               sto_param_valid       pv,
               int_solic_conso_cabec dc
         WHERE de.ind_apro = '0'
           AND dd.sec_nume_docu = de.sec_nume_docu
           AND dd.cod_tipo_docu = de.cod_tipo_docu
           AND dd.ind_rech != '1'
           AND dd.ind_envi = '0'
           AND pv.cod_tipo_docu = dd.cod_tipo_docu
           AND pv.cod_vali = de.cod_vali
           AND dc.sec_nume_docu = dd.sec_nume_docu
           AND dd.cod_peri = pscodigoperiodo
           AND dd.cod_tipo_docu = lc_cod_tipodocu1_ordencompra;
    END IF;
  
    IF (instr(lsimpresiondocumentos, lc_cod_tipodocu2_solicredito) > 0) THEN
      INSERT INTO sto_tempo_repor_error
        (des_zona,
         des_secc,
         cod_terr,
         cod_tipo_docu,
         num_docu,
         cod_clie,
         nom_clie,
         des_vali,
         num_telf,
         val_deud,
         val_prec,
         cam_ingre,
         cod_regi_arri,
         cod_zona_arri,
         cod_peri,
         cod_usua,
         num_orde)
        SELECT substr(dc.uni_admi, 3, 4) des_zona,
               decode(substr(dc.uni_admi, 7, 1),
                      '0',
                      NULL,
                      substr(dc.uni_admi, 7, 1)) des_secc,
               -- UA x Territorio
               substr(dc.uni_admi, 0, 7) cod_terr,
               ------------------
               dd.cod_tipo_docu,
               dc.num_docu,
               -- cedula x cod_clie
               dc.num_docu_iden, --dc.cod_clie,
               -----
               decode(TRIM(dc.val_nom1 || ' ' || dc.val_nom2 || ' ' ||
                           dc.val_ape1 || ' ' || dc.val_ape2),
                      NULL,
                      gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                             'NOM_CLIE'),
                      dc.val_nom1 || ' ' || dc.val_nom2 || ' ' ||
                      dc.val_ape1 || ' ' || dc.val_ape2) nom_clie,
               pv.des_vali || nvl2(de.val_obse_mens, ' - ', NULL) ||
               de.val_obse_mens,
               dc.val_telf_clie || ' / ' || dc.val_celu_clie num_telf,
               (SELECT m.sal_deud_ante
                  FROM mae_clien m
                 WHERE m.cod_clie = dc.cod_clie) val_deud,
               (SELECT nvl(SUM(det.val_unid_dem *
                               det.val_prec_cata_unit_loca *
                               det.val_fact_repe),
                           0)
                  FROM int_solic_conso_detal det,
                       int_solic_conso_cabec cab
                 WHERE cab.cod_pais = det.cod_pais
                   AND cab.cod_peri = det.cod_peri
                   AND cab.cod_clie = det.cod_clie
                   AND cab.num_lote = det.num_lote
                   AND cab.cod_pais = dc.cod_pais
                   AND cab.cod_clie = dc.cod_clie) val_prec,
               (SELECT sp.cod_peri
                  FROM mae_clien_prime_conta pc,
                       cra_perio             cp,
                       seg_perio_corpo       sp,
                       mae_clien             m
                 WHERE pc.perd_oid_peri = cp.oid_peri
                   AND cp.peri_oid_peri = sp.oid_peri
                   AND pc.clie_oid_clie = m.oid_clie
                   AND m.cod_clie = dc.cod_clie) cam_ingre,
               dc.val_regi_arri cod_regi_arri,
               dc.val_zona_arri cod_zona_arri,
               dd.cod_peri,
               pscodigousuario,
               3
          FROM sto_detal_docum_excep de,
               sto_docum_digit       dd,
               sto_param_valid       pv,
               int_solic_conso_credi dc
         WHERE de.ind_apro = '0'
           AND dd.sec_nume_docu = de.sec_nume_docu
           AND dd.cod_tipo_docu = de.cod_tipo_docu
           AND dd.ind_rech != '1'
           AND dd.ind_envi = '0'
           AND pv.cod_tipo_docu = dd.cod_tipo_docu
           AND pv.cod_vali = de.cod_vali
           AND dc.sec_nume_docu = dd.sec_nume_docu
           AND dd.cod_peri = pscodigoperiodo
           AND dd.cod_tipo_docu = lc_cod_tipodocu2_solicredito;
    END IF;
  
    IF (instr(lsimpresiondocumentos, lc_cod_tipodocu3_actualizadato) > 0) THEN
      INSERT INTO sto_tempo_repor_error
        (des_zona,
         des_secc,
         cod_terr,
         cod_tipo_docu,
         num_docu,
         cod_clie,
         nom_clie,
         des_vali,
         num_telf,
         val_deud,
         val_prec,
         cod_regi_arri,
         cod_zona_arri,
         cod_peri,
         cod_usua)
        SELECT gen_pkg_gener.gen_fn_clien_datos_codig(sc.cod_clie,
                                                      'DES_ZONA') des_zona,
               gen_pkg_gener.gen_fn_clien_datos_codig(sc.cod_clie,
                                                      'DES_SECC') des_secc,
               gen_pkg_gener.gen_fn_clien_datos_codig(sc.cod_clie,
                                                      'COD_TERR') cod_terr,
               dd.cod_tipo_docu,
               sc.num_docu,
               sc.cod_clie,
               decode(TRIM(sc.val_nom1 || ' ' || sc.val_nom2 || ' ' ||
                           sc.val_ape1 || ' ' || sc.val_ape2),
                      NULL,
                      gen_pkg_gener.gen_fn_clien_datos_codig(sc.cod_clie,
                                                             'NOM_CLIE'),
                      sc.val_nom1 || ' ' || sc.val_nom2 || ' ' ||
                      sc.val_ape1 || ' ' || sc.val_ape2) nom_clie,
               pv.des_vali || nvl2(de.val_obse_mens, ' - ', NULL) ||
               de.val_obse_mens,
               ltrim(decode(TRIM(sc.val_telf_clie || ' ' ||
                                 sc.val_celu_clie),
                            NULL,
                            gen_pkg_gener.gen_fn_clien_datos_codig(sc.cod_clie,
                                                                   'NUM_TELE'),
                            sc.val_telf_clie || ' ' || sc.val_celu_clie)) num_telf,
               (SELECT m.sal_deud_ante
                  FROM mae_clien m
                 WHERE m.cod_clie = sc.cod_clie) val_deud,
               (SELECT nvl(SUM(det.val_unid_dem *
                               det.val_prec_cata_unit_loca *
                               det.val_fact_repe),
                           0)
                  FROM int_solic_conso_detal det,
                       int_solic_conso_cabec cab
                 WHERE cab.cod_pais = det.cod_pais
                   AND cab.cod_peri = det.cod_peri
                   AND cab.cod_clie = det.cod_clie
                   AND cab.num_lote = det.num_lote
                   AND cab.cod_pais = sc.cod_pais
                   AND cab.cod_clie = sc.cod_clie) val_prec,
               substr(sc.val_zona_arri, 0, 2),
               sc.val_zona_arri,
               dd.cod_peri,
               pscodigousuario
          FROM sto_detal_docum_excep       de,
               sto_docum_digit             dd,
               sto_param_valid             pv,
               int_solic_conso_actua_datos sc
         WHERE de.ind_apro = '0'
           AND dd.sec_nume_docu = de.sec_nume_docu
           AND dd.cod_tipo_docu = de.cod_tipo_docu
           AND dd.ind_rech != '1'
           AND dd.ind_envi = '0'
           AND pv.cod_tipo_docu = dd.cod_tipo_docu
           AND pv.cod_vali = de.cod_vali
           AND sc.sec_nume_docu = dd.sec_nume_docu
           AND dd.cod_peri = pscodigoperiodo
           AND dd.cod_tipo_docu = lc_cod_tipodocu3_actualizadato;
    END IF;
  
    IF (instr(lsimpresiondocumentos, lc_cod_tipodocu4_cyzone) > 0) THEN
      INSERT INTO sto_tempo_repor_error
        (des_zona,
         des_secc,
         cod_terr,
         cod_tipo_docu,
         num_docu,
         cod_clie,
         nom_clie,
         des_vali,
         cod_regi_arri,
         cod_zona_arri,
         cod_peri,
         cod_usua)
        SELECT gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'DES_ZONA') des_zona,
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'DES_SECC') des_secc,
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'COD_TERR') cod_terr,
               dd.cod_tipo_docu,
               dc.num_docu num_docu,
               dc.cod_clie,
               dc.val_nom1 || ' ' || dc.val_nom2 || ' ' || dc.val_ape1 || ' ' ||
               dc.val_ape2 nom_clie,
               pv.des_vali || nvl2(de.val_obse_mens, ' - ', NULL) ||
               de.val_obse_mens,
               dc.cod_regi_arri,
               dc.cod_zona_arri,
               dd.cod_peri,
               pscodigousuario
          FROM sto_detal_docum_excep       de,
               sto_docum_digit             dd,
               sto_param_valid             pv,
               int_solic_conso_dupla_cyzon dc
         WHERE de.ind_apro = '0'
           AND dd.sec_nume_docu = de.sec_nume_docu
           AND dd.cod_tipo_docu = de.cod_tipo_docu
           AND dd.ind_rech != '1'
           AND dd.ind_envi = '0'
           AND pv.cod_tipo_docu = dd.cod_tipo_docu
           AND pv.cod_vali = de.cod_vali
           AND dc.sec_nume_docu = dd.sec_nume_docu
           AND dd.cod_peri = pscodigoperiodo
           AND dd.cod_tipo_docu = lc_cod_tipodocu4_cyzone;
    END IF;
  
    IF (instr(lsimpresiondocumentos, lc_cod_tipodocu5_familiaproteg) > 0) THEN
      INSERT INTO sto_tempo_repor_error
        (des_zona,
         des_secc,
         cod_terr,
         cod_tipo_docu,
         num_docu,
         cod_clie,
         nom_clie,
         des_vali,
         ind_orig,
         cod_regi_arri,
         cod_zona_arri,
         cod_peri,
         cod_usua,
         num_orde)
        SELECT gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'DES_ZONA') des_zona,
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'DES_SECC') des_secc,
               /*gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
               'COD_TERR')*/
               -- UA en cod_terri
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'COD_REGI') ||
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'COD_ZONA') ||
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'COD_SECC') /*||
                                                                                                                                                                                                                                                                                                       lpad(gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie, 'COD_TERR'),5,'0')*/ cod_terr,
               --------------------
               dd.cod_tipo_docu,
               dc.num_docu num_docu,
               dc.num_docu_iden, --dc.cod_clie,
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'NOM_CLIE') nom_clie,
               pv.des_vali || nvl2(de.val_obse_mens, ' - ', NULL) ||
               de.val_obse_mens,
               decode(dc.ind_orig, 'O', 'OCR', 'W', 'Web') ind_orig,
               dc.cod_regi_orig_docu cod_regi_arri,
               dc.cod_zona_orig_docu cod_zona_arri,
               dd.cod_peri,
               pscodigousuario,
               4
          FROM sto_detal_docum_excep       de,
               sto_docum_digit             dd,
               sto_param_valid             pv,
               int_solic_conso_famil_segur dc
         WHERE de.ind_apro = '0'
           AND dd.sec_nume_docu = de.sec_nume_docu
           AND dd.cod_tipo_docu = de.cod_tipo_docu
           AND dd.ind_rech != '1'
           AND dd.ind_envi = '0'
           AND pv.cod_tipo_docu = dd.cod_tipo_docu
           AND pv.cod_vali = de.cod_vali
           AND dc.sec_nume_docu = dd.sec_nume_docu
           AND dd.cod_peri = pscodigoperiodo
           AND dd.cod_tipo_docu = lc_cod_tipodocu5_familiaproteg;
    END IF;
  
    IF (instr(lsimpresiondocumentos, lc_cod_tipodocu6_ingresometa) > 0) THEN
      INSERT INTO sto_tempo_repor_error
        (des_zona,
         des_secc,
         cod_terr,
         tip_meta,
         val_mnto_meta,
         cod_camp_inic,
         cod_tipo_docu,
         num_docu,
         cod_clie,
         nom_clie,
         des_vali,
         ind_orig,
         cod_regi_arri,
         cod_zona_arri,
         cod_peri,
         cod_usua)
        SELECT gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'DES_ZONA') des_zona,
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'DES_SECC') des_secc,
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'COD_TERR') cod_terr,
               dc.tip_meta,
               to_number(dc.val_mnto_meta) val_mnto_meta,
               dc.cod_camp_inic,
               dd.cod_tipo_docu,
               dc.num_docu,
               dc.cod_clie,
               gen_pkg_gener.gen_fn_clien_datos_codig(dc.cod_clie,
                                                      'NOM_CLIE') nom_clie,
               pv.des_vali || nvl2(de.val_obse_mens, ' - ', NULL) ||
               de.val_obse_mens,
               decode(dc.ind_orig, 'O', 'OCR', 'W', 'Web') ind_orig,
               dc.cod_regi_arri,
               dc.cod_zona_arri,
               dd.cod_peri,
               pscodigousuario
          FROM sto_detal_docum_excep       de,
               sto_docum_digit             dd,
               sto_param_valid             pv,
               int_solic_conso_ingre_metas dc
         WHERE de.ind_apro = '0'
           AND dd.sec_nume_docu = de.sec_nume_docu
           AND dd.cod_tipo_docu = de.cod_tipo_docu
           AND dd.ind_rech != '1'
           AND dd.ind_envi = '0'
           AND pv.cod_tipo_docu = dd.cod_tipo_docu
           AND pv.cod_vali = de.cod_vali
           AND dc.sec_nume_docu = dd.sec_nume_docu
           AND dd.cod_peri = pscodigoperiodo
           AND dd.cod_tipo_docu = lc_cod_tipodocu6_ingresometa;
    END IF;
  
    IF (instr(lsimpresiondocumentos, lc_cod_tipodocu2_solicredito) > 0) THEN
      INSERT INTO sto_tempo_repor_error
        (des_zona,
         des_secc,
         cod_terr,
         cod_tipo_docu,
         num_docu,
         cod_clie,
         nom_clie,
         cod_regi_arri,
         cod_zona_arri,
         cod_peri,
         cod_usua,
         fec_soli)
        SELECT gen_pkg_gener.gen_fn_clien_datos_codig(cc.cod_clie,
                                                      'DES_ZONA') des_zona,
               gen_pkg_gener.gen_fn_clien_datos_codig(cc.cod_clie,
                                                      'DES_SECC') des_secc,
               gen_pkg_gener.gen_fn_clien_datos_codig(cc.cod_clie,
                                                      'COD_TERR') cod_terr,
               'SCC-1',
               cc.num_docu,
               cc.cod_clie,
               gen_pkg_gener.gen_fn_clien_datos_codig(cc.cod_clie,
                                                      'NOM_CLIE') nom_clie,
               cc.val_regi_arri,
               cc.val_zona_arri,
               dd.cod_peri,
               pscodigousuario,
               to_char(cc.fec_proc, 'dd/mm/yyyy') fechasolicitud
          FROM int_solic_conso_credi cc,
               sto_docum_digit       dd
         WHERE cc.ind_erro_refe = 'S'
           AND dd.sec_nume_docu = cc.sec_nume_docu
           AND dd.cod_tipo_docu = lc_cod_tipodocu2_solicredito
           AND dd.ind_envi = '1'
           AND NOT EXISTS
         (SELECT 1
                  FROM mae_tipo_vincu  tv,
                       mae_clien_vincu mv,
                       mae_clien       m
                 WHERE tv.cod_tipo_vinc = '03'
                   AND mv.tivc_oid_tipo_vinc = tv.oid_tipo_vinc
                   AND nvl(mv.fec_hast, SYSDATE) >= SYSDATE
                   AND m.oid_clie = mv.clie_oid_clie_vndo
                   AND m.cod_clie = cc.cod_clie)
           AND EXISTS
         (SELECT 1 FROM mae_clien WHERE cod_clie = cc.cod_clie)
           AND dd.cod_peri = pscodigoperiodo;
    END IF;
  
    -- BORRO ALGUNA FOTO EN CASO EXISTA DEL DIA DE FACTURACION
    DELETE FROM sto_histo_repor_error e WHERE e.fec_gene = lsfechaproceso;
  
    -- se guarda la foto de la ejecucion
    INSERT INTO sto_histo_repor_error
      (des_zona,
       des_secc,
       cod_terr,
       cod_tipo_docu,
       num_docu,
       cod_clie,
       nom_clie,
       des_vali,
       num_telf,
       val_deud,
       val_prec,
       cam_ingre,
       cod_regi_arri,
       cod_zona_arri,
       ind_orig,
       cod_peri,
       cod_usua,
       tip_meta,
       val_mnto_meta,
       cod_camp_inic,
       fec_soli,
       cod_tipo_docu_deud,
       num_orde,
       fec_gene)
      (SELECT des_zona,
              des_secc,
              cod_terr,
              cod_tipo_docu,
              num_docu,
              cod_clie,
              nom_clie,
              des_vali,
              num_telf,
              val_deud,
              val_prec,
              cam_ingre,
              cod_regi_arri,
              cod_zona_arri,
              ind_orig,
              cod_peri,
              cod_usua,
              tip_meta,
              val_mnto_meta,
              cod_camp_inic,
              fec_soli,
              cod_tipo_docu_deud,
              num_orde,
              lsfechaproceso
         FROM sto_tempo_repor_error te
        WHERE te.cod_usua = pscodigousuario);
  
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR STO_PR_CARGA_TEMPO_REPO_ERRO: ' ||
                              ls_sqlerrm);
    
  END sto_pr_carga_tempo_repo_erro;

  /**************************************************************************
  Descripcion       : Carga la Tabla Temporal que contiene la informacion
                      para mostrar en el reporte al termino de facturación
                      - Colombia -
  --------------------------------------------------------------------------
  Fecha Creacion    : 15/07/2013
  Parametros Entrada: pscodigoperiodo
                      pscodigousuario
  --------------------------------------------------------------------------
  Autor             : Eduardo Sanchez
  ***************************************************************************/

  PROCEDURE sto_pr_carga_tmp_ceaco_factu
  (
    pscodigoperiodo    VARCHAR2,
    psfechafacturacion VARCHAR2
  ) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  
  BEGIN
  
    DELETE FROM sto_tmp_centr_acopi_factu;
    DELETE FROM sto_tmp_centr_acopi_fuped;
  
    INSERT INTO sto_tmp_centr_acopi_factu
      SELECT e.cod_peri,
             seg.cod_cent_acop,
             seg.nom_cent_acop,
             seg.cod_comp_tran,
             seg.nom_comp_tran,
             seg.val_emai,
             c.cod_regi,
             (SELECT des_regi FROM zon_regio WHERE cod_regi = c.cod_regi) des_regi,
             c.cod_zona,
             c.cod_secc,
             CASE
               WHEN EXISTS (SELECT 1
                       FROM ped_solic_cabec
                      WHERE soca_oid_soli_cabe = a.oid_soli_cabe
                        AND ind_oc = 1) THEN
                'BEP'
               ELSE
                'BEV'
             END tipo,
             a.val_nume_soli boleta_entrega,
             fac.val_nume_iden_fisc nro_cuenta,
             b.val_nom1 || ' ' || b.val_nom2 || ' ' || b.val_ape1 || ' ' ||
             b.val_ape2 consultora,
             fac.num_docu_cont_inte factura,
             to_char(a.fec_fact, 'dd/mm/yyyy') emision,
             to_char(seg.fec, 'dd/mm/yyyy') entrega
      
        FROM ped_solic_cabec       a,
             mae_clien             b,
             fac_docum_conta_cabec fac,
             sto_acopi_cober       c,
             cra_perio             d,
             seg_perio_corpo       e,
             ped_segui_pedid       seg,
             ped_tipo_solic_pais   f,
             ped_tipo_solic        g,
             zon_terri_admin       h,
             zon_terri             i
      
       WHERE a.clie_oid_clie = b.oid_clie
         AND a.perd_oid_peri = d.oid_peri
         AND d.peri_oid_peri = e.oid_peri
         AND a.oid_soli_cabe = fac.soca_oid_soli_cabe
         AND fac.tido_oid_tipo_docu = 1
         AND a.oid_soli_cabe = seg.soca_oid_soli_cabe
         AND a.tspa_oid_tipo_soli_pais = f.oid_tipo_soli_pais
         AND f.tsol_oid_tipo_soli = g.oid_tipo_soli
         AND a.ztad_oid_terr_admi = h.oid_terr_admi
         AND h.terr_oid_terr = i.oid_terr
         AND ltrim(c.cod_terr, '0') = ltrim(i.cod_terr, '0')
         AND g.cod_tipo_soli = 'C1'
         AND e.cod_peri = pscodigoperiodo
         AND a.fec_fact = to_date(psfechafacturacion, 'dd/MM/yyyy')
      
      UNION
      
      SELECT pscodigoperiodo cod_peri,
             cen.cod_cent_acop,
             cen.nom_cent_acop,
             cen.cod_comp_tran,
             cen.nom_comp_tran,
             cen.val_emai,
             cob.cod_regi,
             (SELECT des_regi FROM zon_regio WHERE cod_regi = cob.cod_regi) des_regi,
             cob.cod_zona,
             cob.cod_secc,
             'BER' tipo,
             cbr.cod_cabe_bore boleta_entrega,
             (SELECT num_docu_iden
                FROM mae_clien_ident
               WHERE clie_oid_clie = mae.oid_clie
                 AND val_iden_docu_prin = 1
                 AND rownum = 1) nro_cuenta,
             mae.val_nom1 || ' ' || mae.val_nom2 || ' ' || mae.val_ape1 || ' ' ||
             mae.val_ape2 consultora,
             decode(pedido.factura, NULL, cbr.cod_cabe_bore, pedido.factura) factura,
             psfechafacturacion emision,
             decode(pedido.entrega,
                    NULL,
                    psfechafacturacion,
                    pedido.entrega) entrega
      
        FROM int_bolet_recoj_xml brx,
             int_rec_cabec_borec cbr,
             sto_acopi_cober cob,
             sto_centr_acopi cen,
             mae_clien mae,
             (SELECT a.clie_oid_clie,
                     fac.num_docu_cont_inte factura,
                     a.fec_fact             emision,
                     seg.fec                entrega
                FROM ped_solic_cabec       a,
                     fac_docum_conta_cabec fac,
                     cra_perio             d,
                     seg_perio_corpo       e,
                     ped_segui_pedid       seg,
                     ped_tipo_solic_pais   f,
                     ped_tipo_solic        g
               WHERE a.perd_oid_peri = d.oid_peri
                 AND d.peri_oid_peri = e.oid_peri
                 AND a.oid_soli_cabe = fac.soca_oid_soli_cabe
                 AND fac.tido_oid_tipo_docu = 1
                 AND a.oid_soli_cabe = seg.soca_oid_soli_cabe
                 AND a.tspa_oid_tipo_soli_pais = f.oid_tipo_soli_pais
                 AND f.tsol_oid_tipo_soli = g.oid_tipo_soli
                 AND g.cod_tipo_soli = 'C1'
                 AND e.cod_peri = pscodigoperiodo
                 AND a.fec_fact = to_date(psfechafacturacion, 'dd/MM/yyyy')) pedido
       WHERE brx.cod_cabe_bore = cbr.cod_cabe_bore
         AND ltrim(cob.cod_terr, '0') = ltrim(cbr.cod_terr(+), '0')
         AND cob.cod_comp_tran = cen.cod_comp_tran
         AND cob.cod_cent_acop = cen.cod_cent_acop
         AND cbr.clie_oid_clie = mae.oid_clie
         AND cen.ind_como IS NULL
         AND cbr.clie_oid_clie = pedido.clie_oid_clie(+);
  
    INSERT INTO sto_tmp_centr_acopi_fuped
      SELECT e.cod_peri,
             seg.cod_cent_acop,
             seg.nom_cent_acop,
             seg.cod_comp_tran,
             seg.nom_comp_tran,
             l.cod_regi,
             k.cod_zona,
             n.cod_secc,
             i.cod_terr,
             j.val_i18n des_prod,
             SUM(c.num_unid_aten) unidades
        FROM ped_solic_cabec     a,
             ped_solic_cabec     b,
             ped_solic_posic     c,
             cra_perio           d,
             seg_perio_corpo     e,
             zon_terri_admin     m,
             zon_terri           i,
             zon_secci           n,
             zon_zona            k,
             zon_regio           l,
             ped_tipo_solic_pais f,
             ped_tipo_solic      g,
             mae_produ           h,
             gen_i18n_sicc_pais  j,
             ped_segui_pedid     seg
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND b.oid_soli_cabe = c.soca_oid_soli_cabe
         AND a.oid_soli_cabe = seg.soca_oid_soli_cabe
         AND a.ztad_oid_terr_admi = m.oid_terr_admi
         AND m.terr_oid_terr = i.oid_terr
         AND m.zscc_oid_secc = n.oid_secc
         AND n.zzon_oid_zona = k.oid_zona
         AND k.zorg_oid_regi = l.oid_regi
         AND c.prod_oid_prod = h.oid_prod
         AND h.oid_prod = j.val_oid
         AND j.attr_enti = 'MAE_PRODU'
         AND h.cod_ind_dent_caja = 'F'
         AND a.perd_oid_peri = d.oid_peri
         AND d.peri_oid_peri = e.oid_peri
         AND a.tspa_oid_tipo_soli_pais = f.oid_tipo_soli_pais
         AND f.tsol_oid_tipo_soli = g.oid_tipo_soli
         AND g.cod_tipo_soli = 'C1'
         AND c.num_unid_aten > 0
         AND e.cod_peri = pscodigoperiodo
         AND a.fec_fact = to_date(psfechafacturacion, 'dd/MM/yyyy')
       GROUP BY e.cod_peri,
                seg.cod_cent_acop,
                seg.nom_cent_acop,
                seg.cod_comp_tran,
                seg.nom_comp_tran,
                l.cod_regi,
                k.cod_zona,
                n.cod_secc,
                i.cod_terr,
                j.val_i18n;
  
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR sto_pr_carga_tmp_ceaco_factu: ' ||
                              ls_sqlerrm);
    
  END sto_pr_carga_tmp_ceaco_factu;

  /**************************************************************************
  Descripcion       : Carga la Tabla Temporal que contiene la informacion
                      para mostrar en el reporte de simulacion de faltantes
  --------------------------------------------------------------------------
  Fecha Creacion    : 18/07/2013
  Parametros Entrada: pscodigoperiodo
                      pscodigousuario
                      pnNroPedidosProyectar
                      pnTipoPup -> DA: Demanda Anticipada --DC :Demanda Campaña
                      pnPromedioMontoPedido
                      pnPorcMaximoFaltante
  
  --------------------------------------------------------------------------
  Autor             : Eduardo Sanchez
  ***************************************************************************/
  PROCEDURE sto_pr_carga_tmp_simul_falta
  (
    pscodigoperiodo       VARCHAR2,
    psfechafacturacion    VARCHAR2,
    pnnropedidosproyectar NUMBER,
    pntipopup             VARCHAR2,
    pnpromediomontopedido NUMBER,
    pnporcmaximofaltante  NUMBER
  ) IS
  BEGIN
  
  ped_pkg_cuadr_ofert.ped_pr_ini_stock;
  commit;
  
    DELETE FROM ped_tmp_simul_falta;
  
    INSERT INTO ped_tmp_simul_falta
      SELECT cod_unid_nego,
             cod_sap,
             val_i18n,
             pup,
             nvl(saldo, 0) saldo,
             nvl(unidades_proyectadas, 0) unidades_proyectadas,
             nvl(unidades_faltantes, 0) unidades_faltantes,
             nvl(valor_faltante_proyectado, 0) valor_faltante_proyectado,
             (nvl(valor_faltante_proyectado, 0) /
             (pnnropedidosproyectar * pnpromediomontopedido)) * 100 porc_faltante, --< Número de pedidos a proyectar > * < Promedio monto por pedido >
             (saldo / decode(pup, 0, 1, pup)) cobertura
        FROM (SELECT cod_unid_nego,
                     cod_sap,
                     val_i18n,
                     pup,
                     saldo,
                     unidades_proyectadas,
                     unidades_faltantes,
                     unidades_faltantes * prom valor_faltante_proyectado -- Promedio monto por pedido
                FROM (SELECT cod_unid_nego,
                             cod_sap,
                             val_i18n,
                             pup,
                             saldo,
                             prom,
                             unidades_proyectadas,
                             unidades_proyectadas - nvl(saldo, 0) unidades_faltantes
                        FROM (SELECT cod_unid_nego,
                                     cod_sap,
                                     val_i18n,
                                     pup,
                                     saldo,
                                     prom,
                                     pnnropedidosproyectar * pup unidades_proyectadas --< Número de pedidos a proyectar > * pup
                                FROM (SELECT cod_unid_nego,
                                             cod_sap,
                                             val_i18n,
                                             CASE
                                               WHEN pntipopup = 'DA' THEN
                                                (SELECT nvl(SUM(num_unid), 0) unid
                                                   FROM ped_deman_antic
                                                  WHERE cod_peri =
                                                        pscodigoperiodo
                                                    AND cod_sap = x.cod_sap) /
                                                decode((SELECT COUNT(1)
                                                         FROM ped_deman_antic
                                                        WHERE cod_peri =
                                                              pscodigoperiodo),
                                                       0,
                                                       1)
                                               ELSE
                                                nvl((SELECT pup
                                                      FROM ped_acumu_produ_campa
                                                     WHERE cod_peri = x.cod_peri
                                                       AND cod_sap = x.cod_sap
                                                       AND fec_fact =
                                                           (SELECT MAX(fec_fact)
                                                              FROM ped_acumu_produ_campa
                                                             WHERE cod_peri =
                                                                   x.cod_peri
                                                               AND cod_sap =
                                                                   x.cod_sap)),
                                                    0)
                                             END pup,
                                             nvl((SELECT val_prom
                                                   FROM ped_acumu_produ_campa
                                                  WHERE cod_peri = x.cod_peri
                                                    AND cod_sap = x.cod_sap
                                                    AND fec_fact =
                                                        (SELECT MAX(fec_fact)
                                                           FROM ped_acumu_produ_campa
                                                          WHERE cod_peri =
                                                                x.cod_peri
                                                            AND cod_sap =
                                                                x.cod_sap)),
                                                 0) prom,
                                             saldo
                                        FROM (SELECT DISTINCT a.cod_peri,
                                                              d.cod_unid_nego,
                                                              c.cod_sap,
                                                              e.val_i18n,
                                                              (SELECT SUM(val_sald)
                                                                 FROM bel_stock
                                                                WHERE prod_oid_prod =
                                                                      c.oid_prod
                                                                  AND val_sald >= 0) saldo
                                                FROM --int_solic_conso_cabec a,
                                                     --int_solic_conso_detal b,
                                                      ped_acumu_produ_campa a,
                                                     mae_produ             c,
                                                     mae_unida_negoc       d,
                                                     gen_i18n_sicc_pais    e
                                               WHERE a.cod_sap = c.cod_sap
                                                 AND a.cod_peri =
                                                     pscodigoperiodo
                                                 AND c.uneg_oid_unid_nego =
                                                     d.oid_unid_nego
                                                 AND c.oid_prod = e.val_oid
                                                 AND e.attr_enti = 'MAE_PRODU') x))));
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
    
      raise_application_error(-20123,
                              'ERROR sto_pr_carga_tmp_simul_falta: ' ||
                              ls_sqlerrm);
    
  END sto_pr_carga_tmp_simul_falta;

  /**************************************************************************
  Descripcion       : Devuelve el telefono
  Fecha Creacion    : 15/08/2013
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_fn_devue_telef(pscodigocliente VARCHAR2) RETURN VARCHAR2 IS
  
    CURSOR c_enviocup IS
      SELECT mae_clien_comun.val_text_comu
        FROM mae_clien_comun,
             mae_tipo_comun,
             mae_clien m
       WHERE mae_clien_comun.clie_oid_clie = m.oid_clie
         AND m.cod_clie = pscodigocliente
         AND mae_clien_comun.ticm_oid_tipo_comu =
             mae_tipo_comun.oid_tipo_comu
         AND cod_tipo_comu IN ('TF', 'TM', 'TT');
  
    TYPE t_codtelefono IS TABLE OF mae_clien_comun.val_text_comu%TYPE;
  
    v_codtelefono t_codtelefono;
    rows          NATURAL := 1000; -- Numero de filas a procesar cada vez
    i             BINARY_INTEGER := 0;
  
    lstelefonos VARCHAR2(100) := '';
  
  BEGIN
    OPEN c_enviocup;
    LOOP
      FETCH c_enviocup BULK COLLECT
        INTO v_codtelefono LIMIT rows;
      IF v_codtelefono.count > 0 THEN
        FOR i IN v_codtelefono.first .. v_codtelefono.last
        LOOP
          IF i = 1 THEN
            lstelefonos := v_codtelefono(i);
          ELSE
            lstelefonos := lstelefonos || ' / ' || v_codtelefono(i);
          END IF;
        END LOOP;
      
      END IF;
      EXIT WHEN c_enviocup %NOTFOUND;
    END LOOP;
    CLOSE c_enviocup;
  
    RETURN lstelefonos;
  
  END sto_fn_devue_telef;

  /**************************************************************************
  Descripcion       : Devuelve la estructura geopolitica
                      Departamento - Provincia - Distrito
  Fecha Creacion    : 08/11/2013
  Parametros Entrada:  pscodigoPais (codigo País)
                       psunidadAdmin (Unidad Administrativa)
  
  Autor             : Yahir Rivas Luna
  ***************************************************************************/

  FUNCTION sto_fn_devue_estru_geopo
  (
    pscodigopais  VARCHAR2,
    psunidadadmin VARCHAR2
  ) RETURN VARCHAR2 IS
    ls_result_estruc_geo VARCHAR2(30) := '0';
    ls_oid_terri         NUMBER;
    ls_oid_zona          NUMBER;
    ls_cod_regi          VARCHAR2(2);
    ls_vp_oid_estru_geo  NUMBER;
    ls_obten_vald_ua     VARCHAR2(2);
    ls_obten_geo         VARCHAR2(2);
  BEGIN
  
    SELECT decode(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                       'STO_VALID_UNIDA_ADMI'),
                  '1',
                  '1',
                  '0')
      INTO ls_obten_vald_ua
      FROM dual;
  
    SELECT decode(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                       'STO_GENER_ESTR_GEO'),
                  '1',
                  '1',
                  '0')
      INTO ls_obten_geo
      FROM dual;
  
    IF ls_obten_vald_ua = '1' THEN
    
      --  Obtiene el oid_terr en base a la unidad administrativa
      SELECT terri.oid_terr
        INTO ls_oid_terri
        FROM zon_terri terri
       WHERE terri.cod_terr = to_number(substr(psunidadadmin, 8, 6))
         AND nvl(terri.ind_borr, 0) = 0;
    
      --  Obtiene el oid_zona en base a la unidad administrativa
      SELECT oid_zona
        INTO ls_oid_zona
        FROM zon_zona
       WHERE cod_zona = substr(psunidadadmin, 3, 4);
    
      --  Obtiene el cod_regi en base al territorio y la zona obtenida anteriormente
      SELECT DISTINCT d.cod_regi
        INTO ls_cod_regi
        FROM zon_terri_admin a,
             zon_secci       b,
             zon_zona        c,
             zon_regio       d
       WHERE a.zscc_oid_secc = b.oid_secc
         AND b.zzon_oid_zona = c.oid_zona
         AND c.zorg_oid_regi = d.oid_regi
         AND a.ind_borr = '0'
         AND b.ind_acti = '1'
         AND c.ind_acti = '1'
         AND d.ind_acti = '1'
         AND a.oid_terr_admi IN
             (SELECT oid_terr_admi
                FROM zon_terri_admin
               WHERE terr_oid_terr = ls_oid_terri) --oid Territorio
         AND c.oid_zona = ls_oid_zona; --oid Zona
    
      IF ls_obten_geo = '1' THEN
      
        --  Obtiene el oid de estructura geopolítica, en base al territorio obtenido previamente
        SELECT vepo_oid_valo_estr_geop
          INTO ls_vp_oid_estru_geo
          FROM zon_terri
         WHERE oid_terr = ls_oid_terri
           AND ind_borr = 0;
      
        --cod_depa_clie , cod_prov_clie,cod_dist_clie
        SELECT orde_1 || '|' || orde_2 || '|' || orde_3 depaprovdis
          INTO ls_result_estruc_geo
          FROM zon_valor_estru_geopo
         WHERE ind_borr = 0
           AND oid_valo_estr_geop = ls_vp_oid_estru_geo;
      
      END IF;
    
    END IF;
  
    RETURN ls_result_estruc_geo;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '-1';
    WHEN OTHERS THEN
      RETURN '-1';
    
  END sto_fn_devue_estru_geopo;

  /***************************************************************************
  Descripcion       : Genera una tabla virtual para la consulta de
                      Forma de Pago Clasificacion
  Fecha Creacion    : 02/04/2014
  Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/

  FUNCTION sto_fn_forma_pago_clasi
  (
    pscodigopais  VARCHAR2,
    pscodigomarca VARCHAR2,
    pscodigocanal VARCHAR2,
    pscodigoiso   VARCHAR2
  ) RETURN tabla_forma_pago_clasificacion
    PIPELINED IS
  
    CURSOR c_forma_pago_clasi IS
      SELECT bc.oid,
             bc.cod_peri           AS codigo_periodo,
             NULL                  AS tipo_cliente,
             NULL                  AS subtipo_cliente,
             NULL                  AS tipo_clasificacion,
             NULL                  AS clasificacion,
             NULL                  AS forma_pago,
             NULL                  AS desc_region,
             NULL                  AS desc_zona,
             NULL                  AS codigo_cuv,
             bc.oid_deta_ofer,
             bc.fopa_oid_form_pago,
             bc.oid_tipo_clie,
             bc.oid_subt_clie,
             bc.oid_tipo_clas,
             bc.oid_clas,
             bc.oid_regi,
             bc.oid_zona
        FROM sto_forma_pago_clasi bc
       WHERE nvl(bc.oid_tipo_clie, 0) = nvl(NULL, nvl(bc.oid_tipo_clie, 0))
         AND nvl(bc.oid_regi, 0) = nvl(NULL, nvl(bc.oid_regi, 0))
         AND bc.ind_acti = '1';
  
    tablaregistro tabla_forma_pago_clasificacion;
  
  BEGIN
  
    OPEN c_forma_pago_clasi;
    LOOP
      FETCH c_forma_pago_clasi BULK COLLECT
        INTO tablaregistro LIMIT w_filas;
    
      IF tablaregistro.count > 0 THEN
      
        FOR x IN tablaregistro.first .. tablaregistro.last
        LOOP
        
          BEGIN
            SELECT val_i18n descripcion
              INTO tablaregistro(x).tipo_cliente
              FROM gen_i18n_sicc_comun p,
                   mae_tipo_clien      c,
                   seg_idiom           i
             WHERE p.val_oid = c.oid_tipo_clie
               AND p.idio_oid_idio = i.oid_idio
               AND p.attr_enti = 'MAE_TIPO_CLIEN'
               AND i.cod_iso_idio = pscodigoiso
               AND oid_tipo_clie = tablaregistro(x).oid_tipo_clie;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).tipo_cliente := NULL;
          END;
        
          BEGIN
            SELECT val_i18n descripcion
              INTO tablaregistro(x).subtipo_cliente
              FROM gen_i18n_sicc_comun p,
                   mae_subti_clien     c,
                   mae_tipo_clien      t,
                   seg_idiom           i
             WHERE p.val_oid = c.oid_subt_clie
               AND p.idio_oid_idio = i.oid_idio
               AND p.attr_enti = 'MAE_SUBTI_CLIEN'
               AND t.oid_tipo_clie = c.ticl_oid_tipo_clie
               AND i.cod_iso_idio = pscodigoiso
               AND c.oid_subt_clie = tablaregistro(x).oid_subt_clie;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).subtipo_cliente := NULL;
          END;
        
          BEGIN
            SELECT val_i18n descripcion
              INTO tablaregistro(x).tipo_clasificacion
              FROM gen_i18n_sicc_comun,
                   seg_idiom,
                   mae_tipo_clasi_clien,
                   mae_tipo_clien,
                   mae_subti_clien
             WHERE gen_i18n_sicc_comun.val_oid =
                   mae_tipo_clasi_clien.oid_tipo_clas
               AND gen_i18n_sicc_comun.idio_oid_idio = seg_idiom.oid_idio
               AND gen_i18n_sicc_comun.attr_enti = 'MAE_TIPO_CLASI_CLIEN'
               AND seg_idiom.cod_iso_idio = pscodigoiso
               AND mae_subti_clien.oid_subt_clie =
                   mae_tipo_clasi_clien.sbti_oid_subt_clie
               AND mae_tipo_clien.oid_tipo_clie =
                   mae_subti_clien.ticl_oid_tipo_clie
               AND mae_tipo_clasi_clien.oid_tipo_clas = tablaregistro(x)
                  .oid_tipo_clas;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).tipo_clasificacion := NULL;
          END;
        
          BEGIN
            SELECT val_i18n descripcion
              INTO tablaregistro(x).forma_pago
              FROM gen_i18n_sicc_comun
             WHERE attr_enti = 'BEL_FORMA_PAGO'
               AND val_oid = tablaregistro(x).fopa_oid_form_pago;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).forma_pago := NULL;
          END;
        
          BEGIN
            SELECT pod.val_codi_vent descripcion
              INTO tablaregistro(x).codigo_cuv
              FROM pre_ofert_detal       pod,
                   pre_ofert             po,
                   pre_matri_factu_cabec pmf,
                   cra_perio             cp,
                   seg_perio_corpo       sp
             WHERE pod.ofer_oid_ofer = po.oid_ofer
               AND pod.oid_deta_ofer = tablaregistro(x).oid_deta_ofer
               AND po.mfca_oid_cabe = pmf.oid_cabe
               AND pmf.perd_oid_peri = cp.oid_peri
               AND cp.peri_oid_peri = sp.oid_peri
               AND sp.cod_peri = tablaregistro(x).codigo_periodo;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).codigo_cuv := NULL;
          END;
        
          BEGIN
            SELECT val_i18n descripcion
              INTO tablaregistro(x).clasificacion
              FROM gen_i18n_sicc_comun,
                   seg_idiom,
                   mae_clasi,
                   mae_tipo_clasi_clien,
                   mae_subti_clien,
                   mae_tipo_clien
             WHERE gen_i18n_sicc_comun.val_oid = mae_clasi.oid_clas
               AND gen_i18n_sicc_comun.idio_oid_idio = seg_idiom.oid_idio
               AND gen_i18n_sicc_comun.attr_enti = 'MAE_CLASI'
               AND seg_idiom.cod_iso_idio = pscodigoiso
               AND mae_clasi.tccl_oid_tipo_clas =
                   mae_tipo_clasi_clien.oid_tipo_clas
               AND mae_subti_clien.oid_subt_clie =
                   mae_tipo_clasi_clien.sbti_oid_subt_clie
               AND mae_tipo_clien.oid_tipo_clie =
                   mae_subti_clien.ticl_oid_tipo_clie
               AND mae_clasi.oid_clas = tablaregistro(x).oid_clas;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).clasificacion := NULL;
          END;
        
          BEGIN
            SELECT upper(nvl(zon_regio.des_regi, ' ')) descripcion
              INTO tablaregistro(x).desc_region
              FROM seg_pais,
                   seg_marca,
                   seg_canal,
                   zon_regio
             WHERE (zon_regio.ind_acti = 1)
               AND (seg_pais.cod_pais = pscodigopais)
               AND (seg_marca.cod_marc = pscodigomarca)
               AND (seg_canal.cod_cana = pscodigocanal)
               AND (seg_pais.oid_pais = zon_regio.pais_oid_pais)
               AND (seg_marca.oid_marc = zon_regio.marc_oid_marc)
               AND (seg_canal.oid_cana = zon_regio.cana_oid_cana)
               AND zon_regio.oid_regi = tablaregistro(x).oid_regi;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).desc_region := NULL;
          END;
        
          BEGIN
            SELECT nvl(zon_zona.des_zona, ' ') AS descripcion
              INTO tablaregistro(x).desc_zona
              FROM zon_zona,
                   seg_pais,
                   seg_canal,
                   seg_marca,
                   zon_regio
             WHERE (zon_zona.ind_acti = 1)
               AND (seg_pais.cod_pais = pscodigopais)
               AND (seg_marca.cod_marc = pscodigomarca)
               AND (seg_canal.cod_cana = pscodigocanal)
               AND (seg_canal.oid_cana = zon_regio.cana_oid_cana)
               AND (seg_pais.oid_pais = zon_regio.pais_oid_pais)
               AND (seg_marca.oid_marc = zon_regio.marc_oid_marc)
               AND (seg_canal.oid_cana = zon_zona.cana_oid_cana)
               AND (seg_pais.oid_pais = zon_zona.pais_oid_pais)
               AND (zon_regio.oid_regi = zon_zona.zorg_oid_regi)
               AND zon_zona.oid_zona = tablaregistro(x).oid_zona;
          EXCEPTION
            WHEN no_data_found THEN
              tablaregistro(x).desc_zona := NULL;
          END;
        
          PIPE ROW(tablaregistro(x));
        
        END LOOP;
      
      END IF;
      EXIT WHEN c_forma_pago_clasi%NOTFOUND;
    END LOOP;
  
    CLOSE c_forma_pago_clasi;
  
    RETURN;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR STO_FN_FORMA_PAGO_CLASI: ' ||
                              ls_sqlerrm);
  END sto_fn_forma_pago_clasi;

  /**************************************************************************
  Descripcion        : Devuelve descripcion de Indicador de Solicitud de Credito.
  Fecha Creacion     : 20/05/2014
  Autor              : Carlos Bazalar
  ***************************************************************************/
  FUNCTION sto_fn_devue_recha_solcr(pscodigocliente VARCHAR2) RETURN VARCHAR2 IS
  
    lsretorno     VARCHAR2(500);
    lsdescripcion VARCHAR2(500);
    lncontador    NUMBER;
    lnsecuencial  sto_docum_digit.sec_nume_docu%TYPE;
  BEGIN
  
    IF substr(pscodigocliente, 1, 1) != 'C' THEN
      RETURN 'NO';
    END IF;
  
    lsretorno := 'NO';
    SELECT COUNT(1)
      INTO lncontador
      FROM int_solic_conso_credi a,
           sto_docum_digit       b
     WHERE ltrim(a.num_docu_iden, '0') =
           ltrim(substr(pscodigocliente, 2), '0')
       AND a.sec_nume_docu = b.sec_nume_docu
       AND b.cod_tipo_docu = 'SCC'
       AND b.ind_rech = 1;
  
    IF lncontador = 0 THEN
      RETURN lsretorno;
    ELSE
      lsretorno := 'SI   ';
      SELECT MAX(b.sec_nume_docu)
        INTO lnsecuencial
        FROM int_solic_conso_credi a,
             sto_docum_digit       b
       WHERE ltrim(a.num_docu_iden, '0') =
             ltrim(substr(pscodigocliente, 2), '0')
         AND a.sec_nume_docu = b.sec_nume_docu
         AND b.cod_tipo_docu = 'SCC'
         AND b.ind_rech = 1;
    
      BEGIN
        SELECT x.cod_moti_rech || ' - ' || y.des_moti_rech
          INTO lsdescripcion
          FROM sto_docum_digit x,
               sto_recha_motiv y
         WHERE x.sec_nume_docu = lnsecuencial
           AND x.cod_tipo_docu = 'SCC'
           AND y.cod_tipo_docu = x.cod_tipo_docu
           AND y.cod_moti_rech = x.cod_moti_rech;
        RETURN lsretorno || lsdescripcion;
      EXCEPTION
        WHEN OTHERS THEN
          SELECT x.cod_moti_rech
            INTO lsdescripcion
            FROM sto_docum_digit x
           WHERE x.sec_nume_docu = lnsecuencial
             AND x.cod_tipo_docu = 'SCC';
          lsretorno := 'SI - No se encontro descripción para el Código Rechazo: ' ||
                       lsdescripcion;
          RETURN lsretorno;
      END;
    END IF;
  
    RETURN lsretorno;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR sto_fn_devue_recha_solcr: ' ||
                              ls_sqlerrm);
    
  END sto_fn_devue_recha_solcr;
  
  /**************************************************************************
  Descripcion        : Devuelve campos de gestion de documento STO.
  Fecha Creacion     : 12/02/2015
  Autor              : Rosalvina Ramirez
  ***************************************************************************/
  
  FUNCTION sto_fn_devue_gesti_docum(psCampo          VARCHAR2,
                                    psTipoDocu       VARCHAR2,
                                    psCodClie        VARCHAR2,                                    
                                    psOidPeri        VARCHAR2
                                  ) RETURN VARCHAR2 IS
  
    ls_result          VARCHAR2(100);
    ls_sec_nume_docu   NUMBER(12):=0;
    lv_cod_pais        seg_pais.cod_pais%TYPE;    
    crea_sad           VARCHAR2(1); 
    
  BEGIN
    
   lv_cod_pais := CCC_PKG_GENER.CCC_FN_OBTIE_PARAM_GENER('CodigoPais');
   crea_sad := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lv_cod_pais,
                                                                     'STO_CREA_SAD'),
                                'S');
  
   IF (psTipoDocu = 'SCC') AND (crea_sad='L' ) THEN
            select min(cons.sec_nume_docu) into ls_sec_nume_docu
            from int_solic_conso_credi cons
            where cons.cod_clie = psCodClie
            and cons.oid_peri =  psOidPeri;
            
   ELSIF (psTipoDocu = 'SCC') AND (crea_sad='S' ) THEN
            select min(cons.sec_nume_docu) into ls_sec_nume_docu
            from int_solic_conso_actua_datos cons
            where cons.cod_clie = psCodClie
            and cons.oid_peri =  psOidPeri
            and cons.ind_vali_dnin_clie=1;  
                      
   ELSIF psTipoDocu = 'OCC' THEN
              select min(consc.sec_nume_docu) into ls_sec_nume_docu
              from int_solic_conso_cabec consc
              where consc.cod_clie = psCodClie
              and consc.perd_oid_peri =  psOidPeri;
   END IF;
    
  SELECT CASE
          WHEN pscampo = 'FEC_INGRE' THEN
             TO_CHAR(dd.fec_digi, 'DD/MM/YYYY hh24:mm:ss') 
          WHEN pscampo = 'USU_INGRE' THEN
            dd.usu_digi
          WHEN pscampo = 'FEC_MODI' THEN
            TO_CHAR(dd.fec_modi, 'DD/MM/YYYY hh24:mm:ss') 
          WHEN pscampo = 'USU_MODI' THEN
            TO_CHAR(dd.usu_modi)             
        END into ls_result       
      FROM sto_docum_digit dd
     WHERE 
     dd.sec_nume_docu = ls_sec_nume_docu
       AND dd.cod_tipo_docu = psTipoDocu
       AND ((dd.cod_tipo_docu = psTipoDocu and dd.ind_envi = 1 and
           dd.ind_rech = 0) or
           (dd.cod_tipo_docu = psTipoDocu and dd.ind_envi = 0 and
           dd.ind_rech = 1 and dd.val_obse_rech_defi = 'Reingreso'));        
    
    RETURN ls_result;
    
  EXCEPTION
    WHEN no_data_found THEN
      RETURN '';
      
  END sto_fn_devue_gesti_docum;

END sto_pkg_gener;
/
