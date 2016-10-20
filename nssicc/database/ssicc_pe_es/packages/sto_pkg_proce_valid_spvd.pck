CREATE OR REPLACE PACKAGE sto_pkg_proce_valid_spvd AS

  w_filas NUMBER := 1000; -- Numero de filas a procesar cada vez
  existe  BOOLEAN := FALSE;
  p_rango_inicial_codigo_venta  CONSTANT VARCHAR2(15) := 'STO_RICV';
  p_rango_final_codigo_venta    CONSTANT VARCHAR2(15) := 'STO_RFCV';
  p_rango_inicial_codigo_premio CONSTANT VARCHAR2(15) := 'STO_RICP';
  p_rango_final_codigo_premio   CONSTANT VARCHAR2(15) := 'STO_RFCP';
  p_indicador_matriz            CONSTANT VARCHAR2(1) := 'P';
  p_indicador_envio_factura     CONSTANT INTEGER := 1;
  p_codigo_precio_envia         CONSTANT VARCHAR2(1) := 'F';
  p_indicador_devuelve_factura  CONSTANT INTEGER := 1;
  p_codigo_trueque_premio       CONSTANT VARCHAR2(2) := 'TP';
  p_dias_para_fm                CONSTANT VARCHAR2(15) := 'STO_DPFM';
  p_numero_fm                   CONSTANT VARCHAR2(15) := 'STO_NUM_FM';
  p_dias_para_dn                CONSTANT VARCHAR2(15) := 'STO_DPDN';
  p_numero_dn                   CONSTANT VARCHAR2(15) := 'STO_NUM_DN';
  p_dias_para_fp                CONSTANT VARCHAR2(15) := 'STO_DPFP';
  p_numero_fp                   CONSTANT VARCHAR2(15) := 'STO_NUM_FP';
  p_dias_para_es                CONSTANT VARCHAR2(15) := 'STO_DPES';
  p_numero_es                   CONSTANT VARCHAR2(15) := 'STO_NUM_ES';
  p_dias_para_gr                CONSTANT VARCHAR2(15) := 'STO_DPGR';
  p_numero_gr                   CONSTANT VARCHAR2(15) := 'STO_NUM_GR';
  p_dias_para_cm                CONSTANT VARCHAR2(15) := 'STO_DPCM';
  p_numero_cm                   CONSTANT VARCHAR2(15) := 'STO_NUM_CM';
  p_dias_para_tm                CONSTANT VARCHAR2(15) := 'STO_DPTM';
  p_numero_tm                   CONSTANT VARCHAR2(15) := 'STO_NUM_TM';

  /***************************************************************************
  Descripcion       : Validacion del Pais
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_pais
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de la operacion
  Fecha Creacion    : 01/06/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_opera
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de los codigos de venta
  Fecha Creacion    : 02/06/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_codig_venta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Recuperacion de precios
  Fecha Creacion    : 02/06/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recup_preci
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de caracteristicas codigo venta/premio
                      del Codigo Venta 2 (SD510)
  Fecha Creacion    : 03/06/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_carac_cvpv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Verifica la garantia de premios
  Fecha Creacion    : 12/03/2014
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_garan_premi
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    psfecha            DATE,
    psnumpedi          VARCHAR2,
    psoidsoliposi      NUMBER
  ) return BOOLEAN;
  /***************************************************************************
  Descripcion       : Validacion de Cantidad Dias hacia Atras (SD520)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_canti_diatr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Verifica la cantidad de dias hacia atras
  Fecha Creacion    : 20/06/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_canti_diatr
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    psnumpedi          VARCHAR2,
    psoidperirec       number,
    pstipproc          VARCHAR2,
    psfecrefe          date,
    psnumdias          number,
    psoidperiref       number
  ) return BOOLEAN;

  /***************************************************************************
  Descripcion       : Verifica si el usuario tiene acceso a una operacion determinada
  Fecha Creacion    : 13/02/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_usuar_opera
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    psusuario          VARCHAR2
  ) return BOOLEAN;
  /***************************************************************************
  Descripcion       : Validacion de Unidades Reclamadas (SD530)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_unida_recla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Unidades Reclamadas (SD530)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  FUNCTION sto_fn_spvd_unida_recla
  (
    pscodigopais       VARCHAR2,
    pscodclie          VARCHAR2,
    psoidsoliposi      NUMBER,
    psnumuni           NUMBER
  ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : Validacion de Unidades Reclamadas de un Faltante
  Fecha Creacion    : 01/07/2044
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_unida_recla_falta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Unidades de Faltante de Premio (FP)
  Fecha Creacion    : 09/03/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_unida_fp
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Unidades de Faltante de Premio (FP)
  Fecha Creacion    : 09/03/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_unida_fp_falta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de cantidad de reclamos aceptados ECUADOR
  Fecha Creacion    : 07/05/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_canti_recla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de cantidad de reclamos aceptados ECUADOR
  Fecha Creacion    : 07/05/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_canti_recla
  (
    pscodoper          VARCHAR2,
    psnumpedi          VARCHAR2,
    psoidcabe          NUMBER,
    pssecnumedocucabe  NUMBER,
    pstipproc          VARCHAR2
  ) return BOOLEAN;

  /***************************************************************************
  Descripcion       : Validacion de motivo real de devolucion
  Fecha Creacion    : 07/05/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_moti_real_devo
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de motivo real de devolucion
  Fecha Creacion    : 07/05/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_moti_real_devo
  (
    pscodoCliente      VARCHAR2,
    psoidperiref       NUMBER,
    pscodventdevu      VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2
  ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : Validacion para ingresar de manera automatica las excepciones a la validacion
  Fecha Creacion    : 12/04/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_agre_excep_auto
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Validacion de Unidades Boletas Electronicas CHILE
  Fecha Creacion    : 16/04/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_unida_bolec
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );


  /***************************************************************************
  Descripcion       : Validacion de Faltante mercancia/Facturado no enviado
                      (SD540)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_fmefa_noenv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Faltantes
  Fecha Creacion    : 21/02/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_fmefa_noenv
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  ) RETURN BOOLEAN;


  /***************************************************************************
  Descripcion       :Validacion del Tipo de Bloqueo en Devoluciones (SD610)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_tipo_bldev
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en devoluciones
  Fecha Creacion    : 21/02/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_tipo_bldev
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Faltante de Premios
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recur_falta_premi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Faltante de Premios
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_recur_falta_premi
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Error Interno
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recur_error_inter
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Error Interno
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_recur_error_inter
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Producto Gratis
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recur_produ_grati
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Producto Gratis
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_recur_produ_grati
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Cambio de productos
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recur_cambi_produ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Cambio de productos
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_recur_cambi_produ
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Trueque de productos
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recur_trueq_produ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Trueque de productos
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_recur_trueq_produ
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : Validacion de Garantia de codigos de venta (SD620)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_garan_codvet
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Trueques de codigos de venta -
                      por generico/supergenerico- (SD630)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_trueq_gesge
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Garantia de codigos de premios
                      (SD710 Y SD715)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_garan_codpr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Trueques de premios (por premios diferente nivel)
                     (SD720)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_trupr_pdniv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de la Devolucion de premios (SD730)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_devul_premi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Excepciones codigos de venta (SD640)
  Fecha Creacion    : 12/06/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_excep_codve
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /****************************************************************************
  Descripcion       : Verifica si el CUV esta en la tabla de excepciones de CUV
  Fecha Creacion    : 20/06/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_excep_codve
  (
    psoidperiref       NUMBER,
    pscodventdevu      VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2
  ) RETURN BOOLEAN;
  /***************************************************************************
  Descripcion       : VALIDACION CORPORATIVA PORCENTAJE MONTO BRUTO POR DEVOLUCION
  Fecha Creacion    : 22/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_porce_devol
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION CORPORATIVA PORCENTAJE UNIDADES POR DEVOLUCION
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_porce_unida
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION CORPORATIVA DE DESVIACION DE PRECIOS PARA TRUEQUES
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_desvi_preci
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION CORPORATIVA PORCENTAJE UNIDADES POR FALTANTE
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_unida_falta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION CORPORATIVA PORCENTAJE MONTO BRUTO POR FALTANTE
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_mobru_falta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION SOLO DEVOLUCION EN RECLAMO DE RECLAMO
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_devol_recla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION MAV SOLO EN CANJE O FALTANTE
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_mavca_falta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION DEVUELVE MAV SOLO EN CANJE O FALTANTE
  Fecha Creacion    : 11/03/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_mavca_falta
  (
    pscodigopais      in  VARCHAR2,
    psCodOperSICC     in  VARCHAR2,
    psTipoOperSICC    in  VARCHAR2,
    psOidSoliPosi     in  number,
    pscodigoVenta     in  VARCHAR2
  ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : VALIDACION ENVIAS SON DE PROMOCION
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_envia_promo
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION DE DEVOLUCION DE OFERTA CON GRATIS
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_devol_ofert
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION COMPLETA ENVIA DE TRUEQUES
  Fecha Creacion    : 26/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_envia_trueq
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION MOTIVO POST VENTA
  Fecha Creacion    : 05/01/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_motiv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION DIFERENCIA PRECIOS TRUEQUES A NIVEL DETALLE
  Fecha Creacion    : 05/01/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_difer_preci
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION COMPLETAR DEVUELVES
  Fecha Creacion    : 06/01/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_compl_devue
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de la operacion
  Fecha Creacion    : 16/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_opera_accio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de indicador de origen
  Fecha Creacion    : 16/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_indic_orige
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion recuperacion de precios de productos deseados
  Fecha Creacion    : 28/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_repre_desea
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion recuperacion de precios de productos deseados
  Fecha Creacion    : 28/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_spvd_repre_desea
  (
     pscodigopais   in VARCHAR2,
     psOidPeriCDR   in number,
     pscodigoVenta  in VARCHAR2,
     psCodOperSICC  in VARCHAR2,
     psTipoOperSICC in VARCHAR2,
     psOidSoliCabe  in number
   ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : Validacion recuperacion de precios de productos devueltos
  Fecha Creacion    : 28/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_repre_devue
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion recuperacion de precios de productos devueltos
  Fecha Creacion    : 28/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_spvd_repre_devue
  (
     pscodigopais   in VARCHAR2,
     psOidPeriCDR   in number,
     pscodigoVenta  in VARCHAR2,
     psCodOperSICC  in VARCHAR2,
     psTipoOperSICC in VARCHAR2,
     psOidSoliCabe  in number
   ) RETURN BOOLEAN;


  /***************************************************************************
  Descripcion       : Validacion de rechazo por OCR
  Fecha Creacion    : 20/05/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recha_ocr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de facturados no enviados para revision
  Fecha Creacion    : 20/05/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_factu_noenv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de monto minimo
  Fecha Creacion    : 08/06/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_monto_minim
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de monto minimo
  Fecha Creacion    : 08/06/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_spvd_monto_minim
  (
    pscodigopais          in  VARCHAR2,
    pscodoCliente         in  VARCHAR2,
    psOidSoliPosi         in  number,
    psunidadesReclamar    in  number,
    psCodOperSICC         in  VARCHAR2,
    psTipoOperSICC        in  VARCHAR2
  ) RETURN BOOLEAN;
  /***************************************************************************
  Descripcion       : Validacion de producto ganador
  Fecha Creacion    : 10/06/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_produ_ganad
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de producto ganador
  Fecha Creacion    : 10/06/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_spvd_produ_ganad
  (
    pscodoper          VARCHAR2,
    psoidsoliposi      NUMBER
  ) RETURN BOOLEAN;
  /***************************************************************************
  Descripcion       : Validacion de atencion FFNNEE
  Fecha Creacion    : 17/07/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_atenc_ffnne
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de atencion FFNNE2
  Fecha Creacion    : 17/12/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_atenc_ffne2
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION CONTROL DE PRODUCTO ENVIA
  Fecha Creacion    : 03/05/2010
  Autor             : Jose A. cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_produ_envia
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Validacion Centro de Servicios
  Fecha Creacion    : 31/05/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_centr_servi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion 1 de FFNE
  Fecha Creacion    : 08/07/2010
  Autor             : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_atenc_ffnne_val1
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion 2 de FFNE
  Fecha Creacion    : 08/07/2010
  Autor             : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_atenc_ffnne_val2
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion 3 de FFNE
  Fecha Creacion    : 08/07/2010
  Autor             : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_atenc_ffnne_val3
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : VALIDACION FNNE VENEZUELA
  Fecha Creacion    : 05/01/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_cntrl_fnne
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION FNNE VENEZUELA
  Fecha Creacion    : 11/03/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_cntrl_fnne
  (
    pscodigopais      in  VARCHAR2,
    psCodOperSICC     in  VARCHAR2,
    psTipoOperSICC    in  VARCHAR2,
    psOidSoliPosi     in  number,
    pscodigomotivo    in  VARCHAR2
  ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : Validacion por Motivo de Devolucion de Mercaderia
  Fecha Creacion    : 23/08/2010
  Autor             : Dennys Oliva Iriarte
  **************************************************************************/
  PROCEDURE sto_pr_spvd_motiv_devol
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

    /***************************************************************************
  Descripcion       : Validacion por Motivo de Devolucion de Mercaderia
  Fecha Creacion    : 23/08/2010
  Autor             : Dennys Oliva Iriarte
  **************************************************************************/
  FUNCTION sto_fn_spvd_motiv_devol
  (
    pscodoper               VARCHAR2,
    pstipoper               VARCHAR2,
    psmotpv                 VARCHAR2
  ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : Validacion Faltantes de Premios
  Fecha Creacion    : 16/09/2010
  Autor             : Jesse James Rios Franco
  **************************************************************************/
  PROCEDURE sto_pr_spvd_falta_premi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Validacion Faltantes de Pedido Chequeado
  Fecha Creacion    : 23/03/2011
  Autor             : Jose Luis Rodriguez
  **************************************************************************/
  PROCEDURE sto_pr_spvd_falta_pedid_chequ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Verifica si es faltante de pedido chequeado SPVD-47
  Fecha Creacion    : 23/07/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_falta_pedid_chequ
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    psnumpedi          VARCHAR2
  ) return BOOLEAN;


  /***************************************************************************
  Descripcion       : Validacion Tipo Operacion reemplaza a
                      sto_pr_spvd_opera y sto_pr_spvd_opera_accion
  Fecha Creacion    : 23/03/2011
  Autor             : Sandro Quintana Aponte
  **************************************************************************/
  PROCEDURE sto_pr_spvd_tipo_opera
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Devuelve el Tipo de Operacion
  Fecha Creacion    : 20/06/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_tipo_opera
  (
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    psmotspv           VARCHAR2,
    psindexpr          VARCHAR2
  ) return varchar2;
  /***************************************************************************
  Descripcion       : VALIDACION DE DEVOLUCION GRATIS DE UNA OFERTA
  Fecha Creacion    : 06/04/2011
  Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_devol_grati
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION CUVS IGUALES EN UN PEDIDO
  Fecha Creacion    : 06/06/2011
  Autor             : Christian Luque
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_igual_pedid
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Validacion para Operacion Trueque de Mercaderia
    Fecha Creacion    : 14/12/2011
    Autor             : Vidal Cupe
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_opera_trueq
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : VALIDACION operacion truequemercaderia SPVD-52
  Fecha Creacion    : 11/03/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_opera_trueq
  (
    pscodigopais      in  VARCHAR2,
    psCodOperSICC     in  VARCHAR2,
    psTipoOperSICC    in  VARCHAR2,
    psOidSoliPosi     in  number,
    pscodigoVentaDes  in  VARCHAR2,
    psperiodVentaDes  in  number
  ) RETURN BOOLEAN;


  /***************************************************************************
  Descripcion       : VALIDACION NO ACEPTA DEVOLUCION DE UN CDR
  Fecha Creacion    : 27/05/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_nodev_recla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : FUNCION NO ACEPTA DEVOLUCION DE UN CDR
  Fecha Creacion    : 22/07/2015
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_nodev_recla
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    pscodclie          VARCHAR2,
    psindoc            number,
    pstsol             number,
    psmodu             number

  ) RETURN BOOLEAN;
  /***************************************************************************
  Descripcion       : VALIDACION NO ACEPTA DEVOLUCION DE UN PEDIDO RESCATADO POR MONTO MINIMO
  Fecha Creacion    : 27/05/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_nodev_pedid_monmi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : FUNCIO NO ACEPTA DEVOLUCION DE UN PEDIDO RESCATADO POR MONTO MINIMO (SPVD-61)
  Fecha Creacion    : 27/07/2015
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_nodev_pedid_monmi
  (
    pscodigopais          VARCHAR2,
    pscodoper             VARCHAR2,
    pstipoper             VARCHAR2,
    pstposposi            NUMBER,
    psstpoposi            NUMBER,
    psmotspv              VARCHAR2
  ) RETURN BOOLEAN;
  /***************************************************************************
  Descripcion       : VALIDACION NO ACEPTA DEVOLUCION DE UN CATALOGO ESPECIFICO
  Fecha Creacion    : 27/05/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_nodev_catal
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : FUNCION NO ACEPTA DEVOLUCION DE UN CATALOGO ESPECIFICO (SPVD-62)
  Fecha Creacion    : 22/07/2015
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_nodev_catal
  (
     pscodigopais   in VARCHAR2,
     psOidPeriCDR   in number,
     pscodigoVenta  in VARCHAR2,
     psCodOperSICC  in VARCHAR2,
     psTipoOperSICC in VARCHAR2,
     pscodigomotivo in  VARCHAR2
  ) RETURN BOOLEAN;
  /***************************************************************************
  Descripcion       : VALIDACION NO ACEPTA DEVOLUCION DE UN CATALOGO ESPECIFICO
  Fecha Creacion    : 27/05/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_nodev_remes
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : FUNCION NO ACEPTA DEVOLUCION DE UN CATALOGO ESPECIFICO
  Fecha Creacion    : 23/07/2015
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_nodev_remes
  (
     pscodigopais   in VARCHAR2,
     psOidSoliCabe  in number,
     psCodOperSICC  in VARCHAR2,
     psTipoOperSICC in VARCHAR2,
     pscodigomotivo in  VARCHAR2
  ) RETURN BOOLEAN;

  /***************************************************************************
  Descripcion       : VALIDACION NO ACEPTA DEVOLUCION DE X dias atras
  Fecha Creacion    : 23/07/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_nodev_xdias
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Verifica la devoluion de x dias atras
  Fecha Creacion    : 23/07/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_nodev_xdias
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    psnumpedi          VARCHAR2
  ) return BOOLEAN;

  /***************************************************************************
  Descripcion       : VALIDACION NO ACEPTA DEVOLUCION DE por Origen del CUV del pedido (SPVD-70)
  Fecha Creacion    : 23/07/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_orige_produ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : FUNCION NO ACEPTA DEVOLUCION DE por Origen del CUV del pedido (SPVD-70)
  Fecha Creacion    : 23/07/2015
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_orige_produ
  (
     pscodigopais   in VARCHAR2,
     psOidSoliCabe  in number,
     pscodigoVenta  in VARCHAR2,
     psCodOperSICC  in VARCHAR2,
     psTipoOperSICC in VARCHAR2,
     pscodigomotivo in  VARCHAR2
  ) RETURN BOOLEAN;

 /***************************************************************************
  Descripcion       : FUNCION Verifica la cantidad de dias antes de la facturacion
                      que acepta el CDR
  Fecha Creacion    : 20/06/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_dias_fact
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    pscodclie          VARCHAR2
  ) return VARCHAR2;

  /***************************************************************************
  Descripcion       : Validacion si el producto es recuperacion
  Fecha Creacion    : 21/02/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_valid_recup
  (
    psoidsoliposi      NUMBER
  ) RETURN BOOLEAN;

  /***************************************************************************
    Descripcion       : Validacion validar la restricción de antigüedad de la
                        operación que va  a realizar
    Fecha Creacion    : 06/08/2012
    Autor             : Giovanni Ascarza
  ***************************************************************************/
PROCEDURE sto_pr_spvd_vali_anti_oper
  (
    pscodoperssicc     in     VARCHAR2,
    pstipoperssicc     in     VARCHAR2,
    pscodigomotivo     in      VARCHAR2,
    psindiceexpress    in      VARCHAR2,
    pscodigopais       in VARCHAR2,
    psnumpedi          in VARCHAR2,
    psoidperirec       in number,
    psusuario          in  VARCHAR2,
    pCodOpCorrecto     out varchar2,
    pmensaje           out varchar2


  );

 /***************************************************************************
    Descripcion       : Validacion de excepcion y producto ganador
    Fecha Creacion    : 06/08/2012
    Autor             : Giovanni Ascarza
  ***************************************************************************/
 PROCEDURE sto_pr_spvd_vali_eprod_gana
  (
    psOidPeriCDR      in  number,
    pscodigoVenta     in  VARCHAR2,
    psCodOperSICC     in  VARCHAR2,
    psTipoOperSICC    in  VARCHAR2,
    psOidSoliPosi     in  number,
    pscodigomotivo    in  VARCHAR2,
    pmensaje          out varchar2

  );

 /***************************************************************************
    Descripcion       : Validacion de aceptacion del CDR
    Fecha Creacion    : 24/03/2015
    Autor             : sandro quintana
  ***************************************************************************/
 PROCEDURE sto_pr_spvd_vali_acep_cdr
  (
    psOidPeriCDR      in  number,
    pscodigoVenta     in  VARCHAR2,
    psCodOperSICC     in  VARCHAR2,
    psTipoOperSICC    in  VARCHAR2,
    psOidSoliPosi     in  number,
    pscodigomotivo    in  VARCHAR2,
    pmensaje          out varchar2

  );
 /***************************************************************************
    Descripcion       : Validacion de unidad de reclamo
    Fecha Creacion    : 06/08/2012
    Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_vali_unid_recla
  (
    pscodigopais          in  VARCHAR2,
    pscodoCliente         in  VARCHAR2,
    psOidSoliPosi         in  number,
    psunidadesReclamar    in  number,
    psCodOperSICC         in  VARCHAR2,
    psTipoOperSICC        in  VARCHAR2,
    pmensaje              out VARCHAR2

  );

 /***************************************************************************
    Descripcion       : Validacion de excepcion y producto que desea
    Fecha Creacion    : 06/08/2012
    Autor             : Giovanni Ascarza
  ***************************************************************************/
 PROCEDURE sto_pr_spvd_vali_eprod_desea
  (
    psOidPeriCDR      in  number,
    pscodigoVenta     in  VARCHAR2,
    psCodOperSICC     in  VARCHAR2,
    psTipoOperSICC    in  VARCHAR2,
    psOidSoliPosi     in  number,
    pscodigomotivo    in  VARCHAR2,
    psnumeropedido    in  VARCHAR2,
    pmensaje          out varchar2

  );

   /***************************************************************************
    Descripcion       : Validacion de unidad de desea
    Fecha Creacion    : 06/08/2012
    Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_vali_unid_desea
  (
    pscodigopais          in  VARCHAR2,
    pscodoCliente         in  VARCHAR2,
    psOidSoliPosi         in  number,
    psunidadesReclamar    in  number,
    psCodOperSICC         in  VARCHAR2,
    psTipoOperSICC        in  VARCHAR2,
    pmensaje              out VARCHAR2

  );

   /***************************************************************************
    Descripcion       : Validacion de motivo del CDR
    Fecha Creacion    : 18/05/2015
    Autor             : sandro quintana
  ***************************************************************************/
 PROCEDURE sto_pr_spvd_vali_moti_cdr
  (
    psOidPeriCDR      in  number,
    pscodigoVenta     in  VARCHAR2,
    psCodOperSICC     in  VARCHAR2,
    psTipoOperSICC    in  VARCHAR2,
    psOidSoliPosi     in  number,
    pscodigomotivo    in  VARCHAR2,
    pmensaje          out varchar2

  );

END sto_pkg_proce_valid_spvd;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_valid_spvd AS
  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  /***************************************************************************
  Descripcion       : Validacion del Pais
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_pais
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.cod_vent_devu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             sp.oid_pais
        FROM seg_pais                    sp,
             int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND sp.cod_pais = det.cod_pais;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_oid_pais IS TABLE OF seg_pais.oid_pais%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numdocu       t_numdocu;
    v_tiprefe       t_tiprefe;
    v_cod_vent_devu t_cod_vent_devu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_oid_pais           t_oid_pais;

    i BINARY_INTEGER := 0;
    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_cod_vent_devu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_oid_pais LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        FORALL i IN 1 .. v_codpais.count
          UPDATE int_solic_conso_poven_detal
             SET oid_pais = v_oid_pais(i)
           WHERE cod_pais = v_codpais(i)
             AND cod_peri = v_codperi(i)
             AND cod_clie = v_codclie(i)
             AND num_docu = v_numdocu(i)
             AND tip_refe = v_tiprefe(i)
             AND num_lote = v_num_lote(i)
             AND sec_nume_docu = v_sec_nume_docu(i);

        -- Actualizamos Documentos Validados
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(j)
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_PAIS: ' || ls_sqlerrm);

  END sto_pr_spvd_pais;

  /***************************************************************************
  Descripcion       : Validacion de la operacion
  Fecha Creacion    : 01/06/2008
  Autor             : Leonardo Lizana Chauca
  Ultimo Cambio     : 01/08/2010
  Mofificado por    : Jose Cairampoma
                      Este SP no sirve solo se usa para pruebas
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_opera
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.cod_vent_devu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.ind_envi_fact,
             det.cod_vent_dese,
             det.cod_prec_envi,
             det.cod_prec,
             det.oid_oper,
             det.ind_devu_fact,
             cab.num_docu_cruc,
             cab.oid_peri_refe,
             det.ind_devu_fisi,
             cab.oid_cabe,
             det.can_vent_devu,
             det.can_prod_dese,
             det.cod_oper
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
      ---sto_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
            ---and det.sec_nume_docu = 17912310
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND ((det.cod_vent_dese IS NOT NULL) OR (det.cod_vent_devu IS NOT NULL));

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_ind_envi_fact IS TABLE OF int_solic_conso_poven_detal.ind_envi_fact%TYPE;
    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;
    TYPE t_cod_prec_envi IS TABLE OF int_solic_conso_poven_detal.cod_prec_envi%TYPE;
    TYPE t_cod_prec IS TABLE OF int_solic_conso_poven_detal.cod_prec%TYPE;
    TYPE t_oid_oper IS TABLE OF int_solic_conso_poven_detal.oid_oper%TYPE;
    TYPE t_ind_devu_fact IS TABLE OF int_solic_conso_poven_detal.ind_devu_fact%TYPE;
    TYPE t_num_docu_cruc IS TABLE OF int_solic_conso_poven_cabec.num_docu_cruc%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_ind_devu_fisi IS TABLE OF int_solic_conso_poven_detal.ind_devu_fisi%TYPE;
    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;

    TYPE t_can_vent_devu IS TABLE OF int_solic_conso_poven_detal.can_vent_devu%TYPE;
    TYPE t_can_prod_dese IS TABLE OF int_solic_conso_poven_detal.can_prod_dese%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;

    lv_val_prec_cata_unit_loca ped_solic_posic.val_prec_cata_unit_loca%TYPE;
    lv_val_prec_fact_unit_loca ped_solic_posic.val_prec_fact_unit_loca%TYPE;
    lv_val_prec_cont_unit_loca ped_solic_posic.val_prec_cont_unit_loca%TYPE;
    lv_oid_soli_posi           ped_solic_posic.oid_soli_posi%TYPE;
    lv_prod_oid_prod_envi      ped_solic_posic.prod_oid_prod%TYPE;

    lv_precio_unitario pre_ofert_detal.precio_unitario%TYPE;
    lv_imp_prec_posi   pre_ofert_detal.imp_prec_posi%TYPE;

    lv_panp_oid_para_nive_prem inc_premi_artic.panp_oid_para_nive_prem%TYPE;
    lv_lopa_oid_lote_prem_arti inc_artic_lote.lopa_oid_lote_prem_arti%TYPE;
    lv_copa_oid_para_gral      inc_param_gener_premi.copa_oid_para_gral%TYPE;
    lv_imp_prec_publ           inc_artic_lote.imp_prec_publ%TYPE;

    lv_imp_prec_cata INTEGER;

    lv_tofe_oid_tipo_ofer pre_tipo_ofert.oid_tipo_ofer%TYPE;
    lv_oid_matr_fact      pre_matri_factu.oid_matr_fact%TYPE;

    lv_modu_oid_modu_envi      ped_solic_cabec.modu_oid_modu%TYPE;
    lv_ofde_oid_deta_ofer_envi pre_ofert_detal.oid_deta_ofer%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numdocu       t_numdocu;
    v_tiprefe       t_tiprefe;
    v_cod_vent_devu      t_cod_vent_devu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_ind_envi_fact t_ind_envi_fact;
    v_cod_vent_dese t_cod_vent_dese;
    v_cod_prec_envi t_cod_prec_envi;
    v_cod_prec      t_cod_prec;
    v_oid_oper           t_oid_oper;
    v_ind_devu_fact t_ind_devu_fact;
    v_num_docu_cruc t_num_docu_cruc;
    v_oid_peri_refe t_oid_peri_refe;
    v_ind_devu_fisi t_ind_devu_fisi;
    v_oid_cabe      t_oid_cabe;
    v_can_vent_devu t_can_vent_devu;
    v_can_prod_dese t_can_prod_dese;
    v_cod_oper           t_cod_oper;

    lsvalor     VARCHAR2(20) := '0';
    existeenvia BOOLEAN;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_cod_vent_devu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_ind_envi_fact,
             v_cod_vent_dese,
             v_cod_prec_envi,
             v_cod_prec,
             v_oid_oper,
             v_ind_devu_fact,
             v_num_docu_cruc,
             v_oid_peri_refe,
             v_ind_devu_fisi,
             v_oid_cabe,
             v_can_vent_devu,
             v_can_prod_dese,
             v_cod_oper LIMIT w_filas;
      IF v_codpais.count > 0 THEN
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe  := FALSE;
          lsvalor := v_codclie(i);
          BEGIN
            existeenvia := TRUE;
            lv_panp_oid_para_nive_prem := NULL;
            lv_lopa_oid_lote_prem_arti := NULL;
            lv_copa_oid_para_gral      := NULL;
            IF (v_cod_vent_dese(i) IS NOT NULL AND v_can_prod_dese(i) IS NOT NULL) THEN
              IF (v_cod_prec(i) = p_indicador_matriz) THEN

                BEGIN
                  SELECT d.tofe_oid_tipo_ofer,
                         maf.oid_matr_fact,
                         d.prod_oid_prod,
                         d.precio_unitario,
                         decode(d.precio_unitario,
                                0,
                                d.imp_prec_posi,
                                0) imp_prec_posi
                    INTO lv_tofe_oid_tipo_ofer,
                         lv_oid_matr_fact,
                         lv_prod_oid_prod_envi,
                         lv_precio_unitario,
                         lv_imp_prec_posi
                    FROM pre_ofert_detal       d,
                         pre_matri_factu       maf,
                         pre_matri_factu_cabec cab
                   WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                     AND cab.oid_cabe = maf.mfca_oid_cabe
                     AND cab.perd_oid_peri = v_oid_peri_refe(i)
                     AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i));
                EXCEPTION
                  WHEN no_data_found THEN
                    lv_tofe_oid_tipo_ofer := NULL;
                    lv_oid_matr_fact      := NULL;
                    lv_prod_oid_prod_envi := NULL;
                    lv_precio_unitario    := NULL;
                    lv_imp_prec_posi      := NULL;
                    existeenvia           := FALSE;
                END;

                IF (v_ind_devu_fisi(i) = 0) THEN

                  BEGIN

                    SELECT c.val_prec_cata_unit_loca,
                           decode(c.val_prec_cata_unit_loca,
                                  0,
                                  0,
                                  c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                           c.val_prec_cont_unit_loca,
                           c.oid_soli_posi,
                           c.prod_oid_prod,
                           b.modu_oid_modu,
                           c.ofde_oid_deta_ofer
                      INTO lv_val_prec_cata_unit_loca,
                           lv_val_prec_fact_unit_loca,
                           lv_val_prec_cont_unit_loca,
                           lv_oid_soli_posi,
                           lv_prod_oid_prod_envi,
                           lv_modu_oid_modu_envi,
                           lv_ofde_oid_deta_ofer_envi
                      FROM ped_solic_cabec a,
                           ped_solic_cabec b,
                           ped_solic_posic c
                     WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                       AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                          --AND a.val_nume_soli = v_num_docu_cruc(i)
                       AND a.oid_soli_cabe = v_oid_cabe(i)
                       AND to_number(c.val_codi_vent) = to_number(v_cod_vent_dese(i))
                       AND c.espo_oid_esta_posi <> 2
                       AND rownum = 1
                     ORDER BY c.oid_soli_posi DESC;

                    /*IF lv_modu_oid_modu_envi <> 15 THEN
                      SELECT d.tofe_oid_tipo_ofer,
                             maf.oid_matr_fact
                        INTO lv_tofe_oid_tipo_ofer,
                             lv_oid_matr_fact
                        FROM pre_ofert_detal       d,
                             pre_matri_factu       maf,
                             pre_matri_factu_cabec cab
                       WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                         AND cab.oid_cabe = maf.mfca_oid_cabe
                         AND cab.perd_oid_peri = v_oid_peri_refe(i)
                         AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i));
                    ELSE*/

                      SELECT d.tofe_oid_tipo_ofer,
                             maf.oid_matr_fact
                        INTO lv_tofe_oid_tipo_ofer,
                             lv_oid_matr_fact
                        FROM pre_ofert_detal d,
                             pre_matri_factu maf
                       WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                         AND d.oid_deta_ofer = lv_ofde_oid_deta_ofer_envi
                         AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i))
                         AND rownum = 1;

                    /*END IF;*/

                    IF ((lv_tofe_oid_tipo_ofer IS NOT NULL) AND (lv_oid_matr_fact IS NOT NULL)) THEN
                      existeenvia := TRUE;
                    END IF;

                    IF (v_cod_prec_envi(i) = p_codigo_precio_envia) THEN

                      UPDATE int_solic_conso_poven_detal a
                         SET val_prec_cata_envi = lv_val_prec_fact_unit_loca,
                             val_prec_cont_envi = lv_val_prec_cont_unit_loca,
                             oid_soli_posi_envi = lv_oid_soli_posi,
                             prod_oid_prod_envi = lv_prod_oid_prod_envi,
                             tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                             mafa_oid_envi      = lv_oid_matr_fact,
                             copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                             lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                             panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND sec_nume_docu = v_sec_nume_docu(i)
                         AND cod_vent_dese = v_cod_vent_dese(i);
                      existeenvia := TRUE AND existeenvia;

                    ELSE

                      UPDATE int_solic_conso_poven_detal
                         SET val_prec_cata_envi = lv_val_prec_cata_unit_loca,
                             val_prec_cont_envi = lv_val_prec_cont_unit_loca,
                             oid_soli_posi_envi = lv_oid_soli_posi,
                             prod_oid_prod_envi = lv_prod_oid_prod_envi,
                             tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                             mafa_oid_envi      = lv_oid_matr_fact,
                             copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                             lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                             panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                      WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND sec_nume_docu = v_sec_nume_docu(i)
                         AND cod_vent_dese = v_cod_vent_dese(i);
                      existeenvia := TRUE AND existeenvia;

                    END IF;

                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_val_prec_cata_unit_loca := NULL;
                      lv_val_prec_fact_unit_loca := NULL;
                      lv_val_prec_cont_unit_loca := NULL;
                      lv_oid_soli_posi           := NULL;
                      lv_prod_oid_prod_envi      := NULL;
                      existeenvia                := FALSE;
                  END;

            ELSE
                  IF (v_ind_envi_fact(i) = p_indicador_envio_factura) THEN

                    BEGIN

                      SELECT c.val_prec_cata_unit_loca,
                             decode(c.val_prec_cata_unit_loca,
                          0,
                          0,
                                    c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                             c.val_prec_cont_unit_loca,
                             c.oid_soli_posi,
                             c.prod_oid_prod,
                             b.modu_oid_modu,
                             c.ofde_oid_deta_ofer
                        INTO lv_val_prec_cata_unit_loca,
                             lv_val_prec_fact_unit_loca,
                             lv_val_prec_cont_unit_loca,
                             lv_oid_soli_posi,
                             lv_prod_oid_prod_envi,
                             lv_modu_oid_modu_envi,
                             lv_ofde_oid_deta_ofer_envi
                        FROM ped_solic_cabec a,
                             ped_solic_cabec b,
                             ped_solic_posic c
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                            -- AND a.val_nume_soli =
                            --   v_num_docu_cruc(i)
                         AND a.oid_soli_cabe = v_oid_cabe(i)
                         AND to_number(c.val_codi_vent) = to_number(v_cod_vent_dese(i))
                         AND c.espo_oid_esta_posi <> 2
                         AND rownum = 1
                       ORDER BY c.oid_soli_posi DESC;

                      /*IF lv_modu_oid_modu_envi <> 15 THEN
                        SELECT d.tofe_oid_tipo_ofer,
                               maf.oid_matr_fact
                          INTO lv_tofe_oid_tipo_ofer,
                               lv_oid_matr_fact
                          FROM pre_ofert_detal       d,
                               pre_matri_factu       maf,
                               pre_matri_factu_cabec cab
                         WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                           AND cab.oid_cabe = maf.mfca_oid_cabe
                           AND cab.perd_oid_peri = v_oid_peri_refe(i)
                           AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i));
                      ELSE*/

                        SELECT d.tofe_oid_tipo_ofer,
                               maf.oid_matr_fact
                          INTO lv_tofe_oid_tipo_ofer,
                               lv_oid_matr_fact
                          FROM pre_ofert_detal d,
                               pre_matri_factu maf
                         WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                           AND d.oid_deta_ofer = lv_ofde_oid_deta_ofer_envi
                           AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i))
                           AND rownum = 1;

                      /*END IF;*/

                      IF ((lv_tofe_oid_tipo_ofer IS NOT NULL) AND (lv_oid_matr_fact IS NOT NULL)) THEN
                        existeenvia := TRUE;
                      END IF;

                      IF (v_cod_prec_envi(i) = p_codigo_precio_envia) THEN

                        UPDATE int_solic_conso_poven_detal a
                           SET val_prec_cata_envi = lv_val_prec_fact_unit_loca,
                               val_prec_cont_envi = lv_val_prec_cont_unit_loca,
                               oid_soli_posi_envi = lv_oid_soli_posi,
                               prod_oid_prod_envi = lv_prod_oid_prod_envi,
                               tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                               mafa_oid_envi      = lv_oid_matr_fact,
                               copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                               lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                               panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                         WHERE cod_pais = pscodigopais
                           AND cod_peri = v_codperi(i)
                           AND cod_clie = v_codclie(i)
                           AND num_docu = v_numdocu(i)
                           AND tip_refe = v_tiprefe(i)
                           AND sec_nume_docu = v_sec_nume_docu(i)
                           AND cod_vent_dese = v_cod_vent_dese(i);
                        existeenvia := TRUE AND existeenvia;

                      ELSE

                        UPDATE int_solic_conso_poven_detal
                           SET val_prec_cata_envi = lv_val_prec_cata_unit_loca,
                               val_prec_cont_envi = lv_val_prec_cont_unit_loca,
                               oid_soli_posi_envi = lv_oid_soli_posi,
                               prod_oid_prod_envi = lv_prod_oid_prod_envi,
                               tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                               mafa_oid_envi      = lv_oid_matr_fact,
                               copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                               lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                               panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                         WHERE cod_pais = pscodigopais
                           AND cod_peri = v_codperi(i)
                           AND cod_clie = v_codclie(i)
                           AND num_docu = v_numdocu(i)
                           AND tip_refe = v_tiprefe(i)
                           AND sec_nume_docu = v_sec_nume_docu(i)
                           AND cod_vent_dese = v_cod_vent_dese(i);
                        existeenvia := TRUE AND existeenvia;
                      END IF;

                    EXCEPTION
                      WHEN no_data_found THEN
                        lv_val_prec_cata_unit_loca := NULL;
                        lv_val_prec_fact_unit_loca := NULL;
                        lv_val_prec_cont_unit_loca := NULL;
                        lv_oid_soli_posi           := NULL;
                        lv_prod_oid_prod_envi      := NULL;
                        existeenvia                := FALSE;
                    END;

                  ELSE

                    IF (lv_precio_unitario IS NOT NULL) THEN
                      UPDATE int_solic_conso_poven_detal
                         SET val_prec_cata_envi = lv_precio_unitario,
                             val_prec_cont_envi = lv_imp_prec_posi,
                             prod_oid_prod_envi = lv_prod_oid_prod_envi,
                             tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                             mafa_oid_envi      = lv_oid_matr_fact,
                             copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                             lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                             panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND sec_nume_docu = v_sec_nume_docu(i)
                         AND cod_vent_dese = v_cod_vent_dese(i);

                      existeenvia := TRUE AND existeenvia;

                    ELSE

            UPDATE int_solic_conso_poven_detal
                         SET val_prec_cata_envi = 0,
                             val_prec_cont_envi = lv_imp_prec_posi,
                             prod_oid_prod_envi = lv_prod_oid_prod_envi,
                             tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                             mafa_oid_envi      = lv_oid_matr_fact,
                             copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                             lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                             panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND sec_nume_docu = v_sec_nume_docu(i)
                         AND cod_vent_dese = v_cod_vent_dese(i);

                      existeenvia := TRUE AND existeenvia;

                    END IF;

                  END IF;

                END IF;

              ELSE

                BEGIN

                  SELECT c.panp_oid_para_nive_prem,
                         a.lopa_oid_lote_prem_arti,
                         e.copa_oid_para_gral,
                         decode(dd.oid_reem_arti_lote,
                                NULL,
                                a.imp_prec_publ,
                                dd.imp_prec_publ) AS imp_prec_publ,
                         0 AS imp_prec_cata,
                         decode(dd.oid_reem_arti_lote,
                                NULL,
                                a.prod_oid_prod,
                                dd.prod_oid_prod) AS prod_oid_prod
                    INTO lv_panp_oid_para_nive_prem,
                         lv_lopa_oid_lote_prem_arti,
                         lv_copa_oid_para_gral,
                         lv_imp_prec_publ,
                         lv_imp_prec_cata,
                         lv_prod_oid_prod_envi
                    FROM inc_artic_lote a,
                         inc_lote_premi_artic b,
                         inc_premi_artic c,
                         inc_param_nivel_premi d,
                         inc_param_gener_premi e,
                         inc_concu_param_gener f,
                         inc_reemp_artic_lote dd,
                         (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                          MAX(id.oid_arti_lote) oid_arti_lote
                            FROM inc_artic_lote id
                           GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                   WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                     AND a.cod_vent_fict = id.cod_vent_fict
                     AND a.oid_arti_lote = id.oid_arti_lote
                     AND b.prar_oid_prem_arti = c.oid_prem_arti
                     AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                     AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                     AND e.copa_oid_para_gral = f.oid_para_gral
                     AND dd.arlo_oid_arti_lote(+) = a.oid_arti_lote
                     AND ((v_cod_oper(i) IN ('AP',
                                             'PA',
                                             'FP',
                                             'PF',
                                             'TP',
                                             'PT',
                                             'CP',
                                             'PC',
                                             'SP',
                                             'PS') AND
                         (v_cod_vent_dese(i) = to_number(dd.cod_vent_fict) OR
                         v_cod_vent_dese(i) = to_number(a.cod_vent_fict))) OR
                         (v_cod_oper(i) NOT IN ('AP',
                                                 'PA',
                                                 'FP',
                                                 'PF',
                                                 'TP',
                                                 'PT',
                                                 'CP',
                                                 'PC',
                                                 'SP',
                                                 'PS') AND
                         (v_cod_vent_devu(i) = to_number(dd.cod_vent_fict) OR
                         v_cod_vent_devu(i) = to_number(a.cod_vent_fict))))
                     AND rownum = 1;

            UPDATE int_solic_conso_poven_detal
                     SET val_prec_cata_envi            = lv_imp_prec_cata,
                         val_prec_cont_envi            = lv_imp_prec_publ,
                         prod_oid_prod_envi            = lv_prod_oid_prod_envi,
                         copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                         lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                         panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                   WHERE cod_pais = pscodigopais
                     AND cod_peri = v_codperi(i)
                     AND cod_clie = v_codclie(i)
                     AND num_docu = v_numdocu(i)
                     AND tip_refe = v_tiprefe(i)
              AND sec_nume_docu = v_sec_nume_docu(i)
                     AND cod_vent_dese = v_cod_vent_dese(i);

                  existeenvia := TRUE;

                EXCEPTION
                  WHEN no_data_found THEN
                    lv_panp_oid_para_nive_prem := NULL;
                    lv_lopa_oid_lote_prem_arti := NULL;
                    lv_copa_oid_para_gral      := NULL;
                    lv_imp_prec_publ           := NULL;
                    lv_imp_prec_cata           := NULL;
                    existeenvia                := FALSE;
                END;

              END IF;

            END IF;

            IF (v_cod_vent_dese(i) IS NOT NULL AND v_can_prod_dese(i) IS NULL) THEN
              existeenvia := FALSE;
            END IF;

            existe := existeenvia;

          EXCEPTION
            WHEN OTHERS THEN
              existe := FALSE;
          END;
          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_OPERA: ' || lsvalor || ls_sqlerrm);

  END sto_pr_spvd_opera;

  /***************************************************************************
  Descripcion       : Validacion de los codigos de venta
  Fecha Creacion    : 02/06/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_codig_venta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.cod_vent_devu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND ((det.ind_devu_fisi = 1 AND det.ind_ingr_envi = 1 AND det.cod_vent_dese IS NOT NULL AND
             det.can_prod_dese > 0 AND det.can_prod_dese IS NOT NULL AND det.ind_ingr_devu = 1 AND
             det.cod_vent_devu IS NOT NULL AND det.can_vent_devu > 0 AND
             det.can_vent_devu IS NOT NULL) OR
             (det.ind_devu_fisi = 1 AND (det.ind_ingr_envi <> 1 OR det.ind_ingr_envi IS NULL) AND
             det.cod_vent_dese IS NULL AND det.ind_ingr_devu = 1 AND
             det.cod_vent_devu IS NOT NULL AND det.can_vent_devu > 0 AND
             det.can_vent_devu IS NOT NULL) OR
             (det.ind_devu_fisi = 1 AND det.ind_ingr_envi = 1 AND det.cod_vent_dese IS NOT NULL AND
             (det.ind_ingr_devu <> 1 OR det.ind_ingr_envi IS NULL) AND det.cod_vent_devu IS NULL AND
             det.can_prod_dese > 0 AND det.can_prod_dese IS NOT NULL) OR
             (det.ind_devu_fisi = 1 AND det.ind_envi_gener_devu = 1 AND
             det.cod_vent_dese IS NOT NULL AND det.cod_vent_devu IS NOT NULL AND
             det.can_prod_dese > 0 AND det.can_prod_dese IS NOT NULL AND det.can_vent_devu > 0 AND
             det.can_vent_devu IS NOT NULL) OR
             (det.ind_devu_fisi = 1 AND det.ind_devu_gener_envi = 1 AND
             det.cod_vent_dese IS NOT NULL AND det.cod_vent_devu IS NOT NULL AND
             det.can_prod_dese > 0 AND det.can_prod_dese IS NOT NULL AND det.can_vent_devu > 0 AND
             det.can_vent_devu IS NOT NULL) OR
             (det.ind_devu_fisi = 0 AND det.cod_vent_dese IS NOT NULL AND
             det.cod_vent_devu IS NOT NULL AND det.can_prod_dese > 0 AND
             det.can_prod_dese IS NOT NULL AND det.can_vent_devu > 0 AND
             det.can_vent_devu IS NOT NULL) OR
             (det.tip_refe = 'T' AND ((det.can_prod_dese > 0 AND det.can_prod_dese IS NOT NULL) OR
             (det.can_vent_devu > 0 AND det.can_vent_devu IS NOT NULL))));

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numdocu       t_numdocu;
    v_tiprefe       t_tiprefe;
    v_cod_vent_devu t_cod_vent_devu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    j BINARY_INTEGER := 0;
    k BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_cod_vent_devu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FORALL k IN 1 .. v_codpais.count
          UPDATE int_solic_conso_poven_detal det
             SET det.can_prod_dese = det.can_vent_devu
           WHERE det.tip_refe IN ('C',
                                  'P')
             AND det.sec_nume_docu = v_sec_nume_docu(k);

        -- Actualizamos Documentos Validados
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(j)
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_CODIG_VENTA: ' || ls_sqlerrm);

  END sto_pr_spvd_codig_venta;

  /***************************************************************************
  Descripcion       : Recuperacion de precios
  Fecha Creacion    : 02/06/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recup_preci
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.cod_vent_devu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.ind_envi_fact,
             det.cod_vent_dese,
             det.cod_prec_envi,
             det.cod_prec,
             det.oid_oper,
             det.ind_devu_fact,
             cab.num_docu_cruc,
             cab.oid_peri_refe,
             det.ind_devu_fisi
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND det.num_lote = cab.num_lote ---- SQA 20101102 se agrega nro lote
         AND (det.cod_vent_dese IS NOT NULL OR det.cod_vent_devu IS NOT NULL);

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_ind_envi_fact IS TABLE OF int_solic_conso_poven_detal.ind_envi_fact%TYPE;
    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;
    TYPE t_cod_prec_envi IS TABLE OF int_solic_conso_poven_detal.cod_prec_envi%TYPE;
    TYPE t_cod_prec IS TABLE OF int_solic_conso_poven_detal.cod_prec%TYPE;
    TYPE t_oid_oper IS TABLE OF int_solic_conso_poven_detal.oid_oper%TYPE;
    TYPE t_ind_devu_fact IS TABLE OF int_solic_conso_poven_detal.ind_devu_fact%TYPE;
    TYPE t_num_docu_cruc IS TABLE OF int_solic_conso_poven_cabec.num_docu_cruc%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_ind_devu_fisi IS TABLE OF int_solic_conso_poven_detal.ind_devu_fisi%TYPE;

    lv_val_prec_cata_unit_loca ped_solic_posic.val_prec_cata_unit_loca%TYPE;
    lv_val_prec_fact_unit_loca ped_solic_posic.val_prec_fact_unit_loca%TYPE;
    lv_val_prec_cont_unit_loca ped_solic_posic.val_prec_cont_unit_loca%TYPE;
    lv_oid_soli_posi           ped_solic_posic.oid_soli_posi%TYPE;
    lv_prod_oid_prod_envi      ped_solic_posic.prod_oid_prod%TYPE;
    lv_prod_oid_prod_devu      ped_solic_posic.prod_oid_prod%TYPE;

    lv_precio_unitario pre_ofert_detal.precio_unitario%TYPE;
    lv_imp_prec_posi   pre_ofert_detal.imp_prec_posi%TYPE;

    lv_panp_oid_para_nive_prem  inc_premi_artic.panp_oid_para_nive_prem%TYPE;
    lv_lopa_oid_lote_prem_arti  inc_artic_lote.lopa_oid_lote_prem_arti%TYPE;
    lv_copa_oid_para_gral       inc_param_gener_premi.copa_oid_para_gral%TYPE;
    lv_imp_prec_publ            inc_artic_lote.imp_prec_publ%TYPE;
    lv_oid_lote_prem_artic_devu inc_artic_lote.lopa_oid_lote_prem_arti%TYPE;
    lv_imp_prec_cata            INTEGER;

    lv_ind_cent_dist_gara      inc_artic_lote.ind_cent_dist_gara%TYPE;
    lv_cese_oid_cese_gara      inc_artic_lote.cese_oid_cese_gara%TYPE;
    lv_num_mese_gara           inc_artic_lote.num_mese_gara%TYPE;
    lv_oid_para_nive_prem_devu inc_premi_artic.panp_oid_para_nive_prem%TYPE;
    lv_copa_oid_para_gene_devu inc_concu_param_gener.oid_para_gral%TYPE;
    lv_tofe_oid_tipo_ofer      pre_tipo_ofert.oid_tipo_ofer%TYPE;
    lv_oid_matr_fact           pre_matri_factu.oid_matr_fact%TYPE;

    lv_modu_oid_modu      ped_solic_cabec.modu_oid_modu%TYPE;
    lv_ofde_oid_deta_ofer pre_ofert_detal.oid_deta_ofer%TYPE;

    lv_modu_oid_modu_envi      ped_solic_cabec.modu_oid_modu%TYPE;
    lv_ofde_oid_deta_ofer_envi pre_ofert_detal.oid_deta_ofer%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numdocu       t_numdocu;
    v_tiprefe       t_tiprefe;
    v_cod_vent_devu t_cod_vent_devu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_ind_envi_fact t_ind_envi_fact;
    v_cod_vent_dese t_cod_vent_dese;
    v_cod_prec_envi t_cod_prec_envi;
    v_cod_prec      t_cod_prec;
    v_oid_oper      t_oid_oper;
    v_ind_devu_fact t_ind_devu_fact;
    v_num_docu_cruc t_num_docu_cruc;
    v_oid_peri_refe t_oid_peri_refe;
    v_ind_devu_fisi t_ind_devu_fisi;
    lsvalor         VARCHAR2(20) := '0';
    existeenvia     BOOLEAN;
    existedevu      BOOLEAN;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_cod_vent_devu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_ind_envi_fact,
             v_cod_vent_dese,
             v_cod_prec_envi,
             v_cod_prec,
             v_oid_oper,
             v_ind_devu_fact,
             v_num_docu_cruc,
             v_oid_peri_refe,
             v_ind_devu_fisi LIMIT w_filas;
      IF v_codpais.count > 0 THEN
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe  := FALSE;
          lsvalor := v_codclie(i);
          BEGIN
            existeenvia := TRUE;
            IF v_cod_vent_dese(i) IS NOT NULL THEN
              IF (v_cod_prec(i) = p_indicador_matriz) THEN

                BEGIN
                  SELECT d.tofe_oid_tipo_ofer,
                         maf.oid_matr_fact,
                         d.prod_oid_prod,
                         d.precio_unitario,
                         decode(d.precio_unitario,
                                0,
                                d.imp_prec_posi,
                                0) imp_prec_posi
                    INTO lv_tofe_oid_tipo_ofer,
                         lv_oid_matr_fact,
                         lv_prod_oid_prod_envi,
                         lv_precio_unitario,
                         lv_imp_prec_posi
                    FROM pre_ofert_detal       d,
                         pre_matri_factu       maf,
                         pre_matri_factu_cabec cab
                   WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                     AND cab.oid_cabe = maf.mfca_oid_cabe
                     AND cab.perd_oid_peri = v_oid_peri_refe(i)
                     AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i));
                EXCEPTION
                  WHEN no_data_found THEN
                    lv_tofe_oid_tipo_ofer := NULL;
                    lv_oid_matr_fact      := NULL;
                    lv_prod_oid_prod_envi := NULL;
                    lv_precio_unitario    := NULL;
                    lv_imp_prec_posi      := NULL;
                    existeenvia           := FALSE;
                END;

                IF (v_ind_devu_fisi(i) = 0) THEN

                  BEGIN

                    /* Query Antiguo 23/04/2010
                    SELECT c.val_prec_cata_unit_loca,
                           decode(c.val_prec_cata_unit_loca,
                                  0,
                                  0,
                                  c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                           c.val_prec_cont_unit_loca,
                           c.oid_soli_posi,
                           c.prod_oid_prod,
                           b.modu_oid_modu,
                           c.ofde_oid_deta_ofer
                      INTO lv_val_prec_cata_unit_loca,
                           lv_val_prec_fact_unit_loca,
                           lv_val_prec_cont_unit_loca,
                           lv_oid_soli_posi,
                           lv_prod_oid_prod_envi,
                           lv_modu_oid_modu_envi,
                           lv_ofde_oid_deta_ofer_envi
                      FROM ped_solic_cabec a,
                           ped_solic_cabec b,
                           ped_solic_posic c
                     WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                       AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                       AND a.val_nume_soli = v_num_docu_cruc(i)
                       AND to_number(c.val_codi_vent) =
                           to_number(v_cod_vent_dese(i))
                       AND c.espo_oid_esta_posi <> 2
                       AND rownum = 1
                     ORDER BY c.oid_soli_posi DESC;
                    */

                    SELECT c.val_prec_cata_unit_loca,
                           decode(c.val_prec_cata_unit_loca,
                                  0,
                                  0,
                                  c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                           c.val_prec_cont_unit_loca,
                           c.oid_soli_posi,
                           c.prod_oid_prod,
                           b.modu_oid_modu,
                           c.ofde_oid_deta_ofer
                      INTO lv_val_prec_cata_unit_loca,
                           lv_val_prec_fact_unit_loca,
                           lv_val_prec_cont_unit_loca,
                           lv_oid_soli_posi,
                           lv_prod_oid_prod_devu,
                           lv_modu_oid_modu,
                           lv_ofde_oid_deta_ofer
                      FROM ped_solic_posic c,
                           (SELECT c.oid_soli_posi,
                                   b.modu_oid_modu,
                                   c.num_unid_aten,
                                   SUM(nvl(d.num_unid_recl,
                                           0)) num_unid_recl,
                                   c.num_unid_aten - SUM(nvl(d.num_unid_recl,
                                                             0)) num_unid_disp
                              FROM ped_solic_cabec       a,
                                   ped_solic_cabec       b,
                                   ped_solic_posic       c,
                                   rec_linea_opera_recla d
                             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                               AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                               AND d.sopo_oid_soli_posi(+) = c.oid_soli_posi
                               AND d.timo_oid_tipo_movi(+) = 2
                               AND a.val_nume_soli = v_num_docu_cruc(i)
                                  /*AND a.val_nume_soli = 1001343985 ---1001343096 */
                               AND to_number(c.val_codi_vent) = to_number(v_cod_vent_devu(i)) --- 05412
                               AND c.espo_oid_esta_posi <> 2
                             GROUP BY c.oid_soli_posi,
                                      b.modu_oid_modu,
                                      num_unid_aten
                             ORDER BY num_unid_disp DESC,
                                      modu_oid_modu ASC) b
                     WHERE c.oid_soli_posi = b.oid_soli_posi
                       AND rownum = 1
                     ORDER BY c.oid_soli_posi DESC;

                    IF lv_modu_oid_modu_envi <> 15 THEN
                      SELECT d.tofe_oid_tipo_ofer,
                             maf.oid_matr_fact
                        INTO lv_tofe_oid_tipo_ofer,
                             lv_oid_matr_fact
                        FROM pre_ofert_detal       d,
                             pre_matri_factu       maf,
                             pre_matri_factu_cabec cab
                       WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                         AND cab.oid_cabe = maf.mfca_oid_cabe
                         AND cab.perd_oid_peri = v_oid_peri_refe(i)
                         AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i));
                    ELSE
                      SELECT d.tofe_oid_tipo_ofer,
                             maf.oid_matr_fact
                        INTO lv_tofe_oid_tipo_ofer,
                             lv_oid_matr_fact
                        FROM pre_ofert_detal d,
                             pre_matri_factu maf
                       WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                         AND d.oid_deta_ofer = lv_ofde_oid_deta_ofer_envi
                         AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i))
                         AND rownum = 1;
                    END IF;

                    IF ((lv_tofe_oid_tipo_ofer IS NOT NULL) AND (lv_oid_matr_fact IS NOT NULL)) THEN
                      existeenvia := TRUE;
                    END IF;

                    IF (v_cod_prec_envi(i) = p_codigo_precio_envia) THEN

                      UPDATE int_solic_conso_poven_detal a
                         SET val_prec_cata_envi = lv_val_prec_fact_unit_loca,
                             val_prec_cont_envi = lv_val_prec_cont_unit_loca,
                             oid_soli_posi_envi = lv_oid_soli_posi,
                             prod_oid_prod_envi = lv_prod_oid_prod_envi,
                             tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                             mafa_oid_envi      = lv_oid_matr_fact
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND to_number(cod_vent_dese) = to_number(v_cod_vent_dese(i));
                      existeenvia := TRUE AND existeenvia;

                    ELSE

                      UPDATE int_solic_conso_poven_detal
                         SET val_prec_cata_envi = lv_val_prec_cata_unit_loca,
                             val_prec_cont_envi = lv_val_prec_cont_unit_loca,
                             oid_soli_posi_envi = lv_oid_soli_posi,
                             prod_oid_prod_envi = lv_prod_oid_prod_envi,
                             tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                             mafa_oid_envi      = lv_oid_matr_fact
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND to_number(cod_vent_dese) = to_number(v_cod_vent_dese(i));
                      existeenvia := TRUE AND existeenvia;

                    END IF;

                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_val_prec_cata_unit_loca := NULL;
                      lv_val_prec_fact_unit_loca := NULL;
                      lv_val_prec_cont_unit_loca := NULL;
                      lv_oid_soli_posi           := NULL;
                      lv_prod_oid_prod_envi      := NULL;
                      existeenvia                := FALSE;
                  END;

                ELSE
                  IF (v_ind_envi_fact(i) = p_indicador_envio_factura) THEN

                    BEGIN

                      SELECT c.val_prec_cata_unit_loca,
                             decode(c.val_prec_cata_unit_loca,
                                    0,
                                    0,
                                    c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                             c.val_prec_cont_unit_loca,
                             c.oid_soli_posi,
                             c.prod_oid_prod,
                             b.modu_oid_modu,
                             c.ofde_oid_deta_ofer
                        INTO lv_val_prec_cata_unit_loca,
                             lv_val_prec_fact_unit_loca,
                             lv_val_prec_cont_unit_loca,
                             lv_oid_soli_posi,
                             lv_prod_oid_prod_envi,
                             lv_modu_oid_modu_envi,
                             lv_ofde_oid_deta_ofer_envi
                        FROM ped_solic_cabec a,
                             ped_solic_cabec b,
                             ped_solic_posic c
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                         AND a.val_nume_soli = v_num_docu_cruc(i)
                         AND to_number(c.val_codi_vent) = to_number(v_cod_vent_dese(i))
                         AND c.espo_oid_esta_posi <> 2
                         AND rownum = 1
                       ORDER BY c.oid_soli_posi DESC;

                      IF lv_modu_oid_modu_envi <> 15 THEN
                        SELECT d.tofe_oid_tipo_ofer,
                               maf.oid_matr_fact
                          INTO lv_tofe_oid_tipo_ofer,
                               lv_oid_matr_fact
                          FROM pre_ofert_detal       d,
                               pre_matri_factu       maf,
                               pre_matri_factu_cabec cab
                         WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                           AND cab.oid_cabe = maf.mfca_oid_cabe
                           AND cab.perd_oid_peri = v_oid_peri_refe(i)
                           AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i));
                      ELSE
                        SELECT d.tofe_oid_tipo_ofer,
                               maf.oid_matr_fact
                          INTO lv_tofe_oid_tipo_ofer,
                               lv_oid_matr_fact
                          FROM pre_ofert_detal d,
                               pre_matri_factu maf
                         WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                           AND d.oid_deta_ofer = lv_ofde_oid_deta_ofer_envi
                           AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i))
                           AND rownum = 1;
                      END IF;

                      IF ((lv_tofe_oid_tipo_ofer IS NOT NULL) AND (lv_oid_matr_fact IS NOT NULL)) THEN
                        existeenvia := TRUE;
                      END IF;

                      IF (v_cod_prec_envi(i) = p_codigo_precio_envia) THEN

                        UPDATE int_solic_conso_poven_detal a
                           SET val_prec_cata_envi = lv_val_prec_fact_unit_loca,
                               val_prec_cont_envi = lv_val_prec_cont_unit_loca,
                               oid_soli_posi_envi = lv_oid_soli_posi,
                               prod_oid_prod_envi = lv_prod_oid_prod_envi,
                               tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                               mafa_oid_envi      = lv_oid_matr_fact
                         WHERE cod_pais = pscodigopais
                           AND cod_peri = v_codperi(i)
                           AND cod_clie = v_codclie(i)
                           AND num_docu = v_numdocu(i)
                           AND tip_refe = v_tiprefe(i)
                           AND to_number(cod_vent_dese) = to_number(v_cod_vent_dese(i));
                        existeenvia := TRUE AND existeenvia;

                      ELSE

                        UPDATE int_solic_conso_poven_detal
                           SET val_prec_cata_envi = lv_val_prec_cata_unit_loca,
                               val_prec_cont_envi = lv_val_prec_cont_unit_loca,
                               oid_soli_posi_envi = lv_oid_soli_posi,
                               prod_oid_prod_envi = lv_prod_oid_prod_envi,
                               tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                               mafa_oid_envi      = lv_oid_matr_fact
                         WHERE cod_pais = pscodigopais
                           AND cod_peri = v_codperi(i)
                           AND cod_clie = v_codclie(i)
                           AND num_docu = v_numdocu(i)
                           AND tip_refe = v_tiprefe(i)
                           AND to_number(cod_vent_dese) = to_number(v_cod_vent_dese(i));
                        existeenvia := TRUE AND existeenvia;
                      END IF;

                    EXCEPTION
                      WHEN no_data_found THEN
                        lv_val_prec_cata_unit_loca := NULL;
                        lv_val_prec_fact_unit_loca := NULL;
                        lv_val_prec_cont_unit_loca := NULL;
                        lv_oid_soli_posi           := NULL;
                        lv_prod_oid_prod_envi      := NULL;
                        existeenvia                := FALSE;
                    END;

                  ELSE

                    IF (lv_precio_unitario IS NOT NULL) THEN
                      UPDATE int_solic_conso_poven_detal
                         SET val_prec_cata_envi = lv_precio_unitario,
                             val_prec_cont_envi = lv_imp_prec_posi,
                             prod_oid_prod_envi = lv_prod_oid_prod_envi,
                             tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                             mafa_oid_envi      = lv_oid_matr_fact
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND to_number(cod_vent_dese) = to_number(v_cod_vent_dese(i));

                      existeenvia := TRUE AND existeenvia;

                    ELSE

                      UPDATE int_solic_conso_poven_detal
                         SET val_prec_cata_envi = 0,
                             val_prec_cont_envi = lv_imp_prec_posi,
                             prod_oid_prod_envi = lv_prod_oid_prod_envi,
                             tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                             mafa_oid_envi      = lv_oid_matr_fact
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND to_number(cod_vent_dese) = to_number(v_cod_vent_dese(i));

                      existeenvia := TRUE AND existeenvia;

                    END IF;

                  END IF;

                END IF;

              ELSE

                BEGIN

                  SELECT c.panp_oid_para_nive_prem,
                         a.lopa_oid_lote_prem_arti,
                         e.copa_oid_para_gral,
                         a.imp_prec_publ,
                         0 AS imp_prec_cata

                    INTO lv_panp_oid_para_nive_prem,
                         lv_lopa_oid_lote_prem_arti,
                         lv_copa_oid_para_gral,
                         lv_imp_prec_publ,
                         lv_imp_prec_cata

                    FROM inc_artic_lote a,
                         inc_lote_premi_artic b,
                         inc_premi_artic c,
                         inc_param_nivel_premi d,
                         inc_param_gener_premi e,
                         inc_concu_param_gener f,
                         (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                          MAX(id.oid_arti_lote) oid_arti_lote
                            FROM inc_artic_lote id
                           GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio

                   WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                     AND a.cod_vent_fict = id.cod_vent_fict --- SQA se toma solo un cuv premio
                     AND a.oid_arti_lote = id.oid_arti_lote --- SQA se toma solo un cuv premio
                     AND b.prar_oid_prem_arti = c.oid_prem_arti
                     AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                     AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                     AND e.copa_oid_para_gral = f.oid_para_gral
                     AND to_number(a.cod_vent_fict) = to_number(v_cod_vent_dese(i));

                  UPDATE int_solic_conso_poven_detal
                     SET val_prec_cata_envi            = lv_imp_prec_cata,
                         val_prec_cont_envi            = lv_imp_prec_publ,
                         prod_oid_prod_envi            = lv_prod_oid_prod_envi,
                         copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                         lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                         panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                   WHERE cod_pais = pscodigopais
                     AND cod_peri = v_codperi(i)
                     AND cod_clie = v_codclie(i)
                     AND num_docu = v_numdocu(i)
                     AND tip_refe = v_tiprefe(i)
                     AND to_number(cod_vent_dese) = to_number(v_cod_vent_dese(i));

                  existeenvia := TRUE;

                EXCEPTION
                  WHEN no_data_found THEN
                    lv_panp_oid_para_nive_prem := NULL;
                    lv_lopa_oid_lote_prem_arti := NULL;
                    lv_copa_oid_para_gral      := NULL;
                    lv_imp_prec_publ           := NULL;
                    lv_imp_prec_cata           := NULL;
                    existeenvia                := FALSE;
                END;

              END IF;

            END IF;

            existedevu := TRUE;
            IF v_cod_vent_devu(i) IS NOT NULL THEN

              IF (v_ind_devu_fact(i) = p_indicador_devuelve_factura) THEN

                IF (v_cod_prec(i) = p_indicador_matriz) THEN
                  BEGIN

                    /* Query antiguo 23/04/10
                    SELECT c.val_prec_cata_unit_loca,
                           decode(c.val_prec_cata_unit_loca,
                                  0,
                                  0,
                                  c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                           c.val_prec_cont_unit_loca,
                           c.oid_soli_posi,
                           c.prod_oid_prod,
                           b.modu_oid_modu,
                           c.ofde_oid_deta_ofer
                      INTO lv_val_prec_cata_unit_loca,
                           lv_val_prec_fact_unit_loca,
                           lv_val_prec_cont_unit_loca,
                           lv_oid_soli_posi,
                           lv_prod_oid_prod_devu,
                           lv_modu_oid_modu,
                           lv_ofde_oid_deta_ofer
                      FROM ped_solic_cabec a,
                           ped_solic_cabec b,
                           ped_solic_posic c
                     WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                       AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                       AND a.val_nume_soli = v_num_docu_cruc(i)
                       AND to_number(c.val_codi_vent) =
                           to_number(v_cod_vent_devu(i))
                       AND c.espo_oid_esta_posi <> 2
                       AND rownum = 1
                     ORDER BY c.oid_soli_posi DESC;
                    */

                    SELECT c.val_prec_cata_unit_loca,
                           decode(c.val_prec_cata_unit_loca,
                                  0,
                                  0,
                                  c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                           c.val_prec_cont_unit_loca,
                           c.oid_soli_posi,
                           c.prod_oid_prod,
                           b.modu_oid_modu,
                           c.ofde_oid_deta_ofer
                      INTO lv_val_prec_cata_unit_loca,
                           lv_val_prec_fact_unit_loca,
                           lv_val_prec_cont_unit_loca,
                           lv_oid_soli_posi,
                           lv_prod_oid_prod_devu,
                           lv_modu_oid_modu,
                           lv_ofde_oid_deta_ofer
                      FROM ped_solic_posic c,
                           (SELECT c.oid_soli_posi,
                                   b.modu_oid_modu,
                                   c.num_unid_aten,
                                   SUM(nvl(d.num_unid_recl,
                                           0)) num_unid_recl,
                                   c.num_unid_aten - SUM(nvl(d.num_unid_recl,
                                                             0)) num_unid_disp
                              FROM ped_solic_cabec       a,
                                   ped_solic_cabec       b,
                                   ped_solic_posic       c,
                                   rec_linea_opera_recla d
                             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                               AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                               AND d.sopo_oid_soli_posi(+) = c.oid_soli_posi
                               AND d.timo_oid_tipo_movi(+) = 2
                               AND a.val_nume_soli = v_num_docu_cruc(i)
                                  /*AND a.val_nume_soli = 1001343985 ---1001343096 */
                               AND to_number(c.val_codi_vent) = to_number(v_cod_vent_devu(i)) --- 05412
                               AND c.espo_oid_esta_posi <> 2
                             GROUP BY c.oid_soli_posi,
                                      b.modu_oid_modu,
                                      num_unid_aten
                             ORDER BY num_unid_disp DESC,
                                      modu_oid_modu ASC) b
                     WHERE c.oid_soli_posi = b.oid_soli_posi
                       AND rownum = 1
                     ORDER BY c.oid_soli_posi DESC;

                    IF lv_modu_oid_modu <> 15 THEN

                      SELECT d.tofe_oid_tipo_ofer,
                             maf.oid_matr_fact
                        INTO lv_tofe_oid_tipo_ofer,
                             lv_oid_matr_fact
                        FROM pre_ofert_detal       d,
                             pre_matri_factu       maf,
                             pre_matri_factu_cabec cab
                       WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                         AND cab.oid_cabe = maf.mfca_oid_cabe
                         AND cab.perd_oid_peri = v_oid_peri_refe(i)
                         AND to_number(d.val_codi_vent) = to_number(v_cod_vent_devu(i));

                    ELSE

                      SELECT d.tofe_oid_tipo_ofer,
                             maf.oid_matr_fact
                        INTO lv_tofe_oid_tipo_ofer,
                             lv_oid_matr_fact
                        FROM pre_ofert_detal d,
                             pre_matri_factu maf
                       WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                         AND d.oid_deta_ofer = lv_ofde_oid_deta_ofer
                         AND to_number(d.val_codi_vent) = to_number(v_cod_vent_devu(i))
                         AND rownum = 1;

                    END IF;

                    UPDATE int_solic_conso_poven_detal
                       SET val_prec_cata_devu = lv_val_prec_fact_unit_loca,
                           val_prec_cont_devu = lv_val_prec_cont_unit_loca,
                           oid_soli_posi_devu = lv_oid_soli_posi,
                           prod_oid_prod_devu = lv_prod_oid_prod_devu,
                           tofe_oid_devu      = lv_tofe_oid_tipo_ofer,
                           mafa_oid_devu      = lv_oid_matr_fact
                     WHERE cod_pais = pscodigopais
                       AND cod_peri = v_codperi(i)
                       AND cod_clie = v_codclie(i)
                       AND num_docu = v_numdocu(i)
                       AND tip_refe = v_tiprefe(i)
                       AND to_number(cod_vent_devu) = to_number(v_cod_vent_devu(i));

                    existedevu := TRUE;
                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_val_prec_cata_unit_loca := NULL;
                      lv_val_prec_fact_unit_loca := NULL;
                      lv_val_prec_cont_unit_loca := NULL;
                      lv_oid_soli_posi           := NULL;
                      lv_prod_oid_prod_devu      := NULL;
                      lv_tofe_oid_tipo_ofer      := NULL;
                      lv_oid_matr_fact           := NULL;
                      existedevu                 := FALSE;
                  END;

                ELSE

                  BEGIN
                    SELECT c.val_prec_cata_unit_loca,
                           decode(c.val_prec_cata_unit_loca,
                                  0,
                                  0,
                                  c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                           c.val_prec_cont_unit_loca,
                           c.oid_soli_posi,
                           d.ind_cent_dist_gara,
                           d.cese_oid_cese_gara,
                           d.num_mese_gara,
                           d.prod_oid_prod,
                           i.oid_para_gral,
                           d.lopa_oid_lote_prem_arti,
                           f.panp_oid_para_nive_prem

                      INTO lv_val_prec_cata_unit_loca,
                           lv_val_prec_fact_unit_loca,
                           lv_val_prec_cont_unit_loca,
                           lv_oid_soli_posi,
                           lv_ind_cent_dist_gara,
                           lv_cese_oid_cese_gara,
                           lv_num_mese_gara,
                           lv_prod_oid_prod_devu,
                           lv_copa_oid_para_gene_devu,
                           lv_oid_lote_prem_artic_devu,
                           lv_oid_para_nive_prem_devu

                      FROM ped_solic_cabec a,
                           ped_solic_cabec b,
                           ped_solic_posic c,
                           inc_artic_lote d,
                           inc_lote_premi_artic e,
                           inc_premi_artic f,
                           inc_param_nivel_premi g,
                           inc_param_gener_premi h,
                           inc_concu_param_gener i,
                           (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                            MAX(id.oid_arti_lote) oid_arti_lote
                              FROM inc_artic_lote id
                             GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio

                     WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                       AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                       AND b.copa_oid_para_gene = i.oid_para_gral
                       AND e.num_prem = b.num_prem
                       AND d.lopa_oid_lote_prem_arti = e.oid_lote_prem_arti
                       AND e.prar_oid_prem_arti = f.oid_prem_arti
                       AND f.panp_oid_para_nive_prem = g.oid_para_nive_prem
                       AND g.pagp_oid_para_gene_prem = h.oid_para_gene_prem
                       AND h.copa_oid_para_gral = i.oid_para_gral
                       AND a.val_nume_soli = v_num_docu_cruc(i)
                       AND to_number(c.val_codi_vent_fict) = to_number(v_cod_vent_devu(i))
                       AND d.cod_vent_fict = c.val_codi_vent_fict
                       AND d.cod_vent_fict = id.cod_vent_fict --- SQA se toma solo un cuv premio
                       AND d.oid_arti_lote = id.oid_arti_lote --- SQA se toma solo un cuv premio
                       AND c.espo_oid_esta_posi <> 2
                       AND rownum = 1
                     ORDER BY c.oid_soli_posi DESC;

                    UPDATE int_solic_conso_poven_detal
                       SET val_prec_cata_devu            = '0',
                           val_prec_cont_devu            = lv_val_prec_cont_unit_loca,
                           oid_soli_posi_devu            = lv_oid_soli_posi,
                           ind_cent_gara                 = lv_ind_cent_dist_gara,
                           cese_oid_cese_gara            = lv_cese_oid_cese_gara,
                           num_mes_gara                  = lv_num_mese_gara,
                           prod_oid_prod_devu            = lv_prod_oid_prod_devu,
                           copa_oid_para_gene_devu       = lv_copa_oid_para_gene_devu,
                           lopa_oid_lote_prem_artic_devu = lv_oid_lote_prem_artic_devu,
                           panp_oid_para_nive_prem_devu  = lv_oid_para_nive_prem_devu

                     WHERE cod_pais = pscodigopais
                       AND cod_peri = v_codperi(i)
                       AND cod_clie = v_codclie(i)
                       AND num_docu = v_numdocu(i)
                       AND tip_refe = v_tiprefe(i)
                       AND to_number(cod_vent_devu) = to_number(v_cod_vent_devu(i));
                    existedevu := TRUE;
                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_val_prec_cata_unit_loca  := NULL;
                      lv_val_prec_fact_unit_loca  := NULL;
                      lv_val_prec_cont_unit_loca  := NULL;
                      lv_oid_soli_posi            := NULL;
                      lv_ind_cent_dist_gara       := NULL;
                      lv_cese_oid_cese_gara       := NULL;
                      lv_num_mese_gara            := NULL;
                      lv_prod_oid_prod_devu       := NULL;
                      lv_copa_oid_para_gene_devu  := NULL;
                      lv_oid_lote_prem_artic_devu := NULL;
                      lv_oid_para_nive_prem_devu  := NULL;
                      existedevu                  := FALSE;
                  END;

                END IF;
              ELSE

                IF (v_cod_prec(i) = p_indicador_matriz) THEN
                  BEGIN
                    SELECT d.tofe_oid_tipo_ofer,
                           maf.oid_matr_fact,
                           d.prod_oid_prod
                      INTO lv_tofe_oid_tipo_ofer,
                           lv_oid_matr_fact,
                           lv_prod_oid_prod_devu
                      FROM pre_ofert_detal       d,
                           pre_matri_factu       maf,
                           pre_matri_factu_cabec cab
                     WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                       AND cab.oid_cabe = maf.mfca_oid_cabe
                       AND cab.perd_oid_peri = v_oid_peri_refe(i)
                       AND to_number(d.val_codi_vent) = to_number(v_cod_vent_devu(i));

                    UPDATE int_solic_conso_poven_detal
                       SET val_prec_cata_devu = 0,
                           val_prec_cont_devu = 0,
                           prod_oid_prod_devu = lv_prod_oid_prod_devu,
                           tofe_oid_devu      = lv_tofe_oid_tipo_ofer,
                           mafa_oid_devu      = lv_oid_matr_fact
                     WHERE cod_pais = pscodigopais
                       AND cod_peri = v_codperi(i)
                       AND cod_clie = v_codclie(i)
                       AND num_docu = v_numdocu(i)
                       AND tip_refe = v_tiprefe(i)
                       AND to_number(cod_vent_devu) = to_number(v_cod_vent_devu(i));
                    existedevu := TRUE;
                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_tofe_oid_tipo_ofer := NULL;
                      lv_oid_matr_fact      := NULL;
                      lv_prod_oid_prod_devu := NULL;
                      existedevu            := FALSE;
                  END;
                ELSE
                  BEGIN
                    SELECT c.panp_oid_para_nive_prem,
                           a.lopa_oid_lote_prem_arti,
                           e.copa_oid_para_gral,
                           a.imp_prec_publ,
                           0 AS imp_prec_cata,
                           a.prod_oid_prod

                      INTO lv_panp_oid_para_nive_prem,
                           lv_lopa_oid_lote_prem_arti,
                           lv_copa_oid_para_gral,
                           lv_imp_prec_publ,
                           lv_imp_prec_cata,
                           lv_prod_oid_prod_devu

                      FROM inc_artic_lote a,
                           inc_lote_premi_artic b,
                           inc_premi_artic c,
                           inc_param_nivel_premi d,
                           inc_param_gener_premi e,
                           inc_concu_param_gener f,
                           (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                            MAX(id.oid_arti_lote) oid_arti_lote
                              FROM inc_artic_lote id
                             GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio

                     WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                       AND b.prar_oid_prem_arti = c.oid_prem_arti
                       AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                       AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                       AND e.copa_oid_para_gral = f.oid_para_gral
                       AND a.cod_vent_fict = id.cod_vent_fict
                       AND a.oid_arti_lote = id.oid_arti_lote
                       AND to_number(a.cod_vent_fict) = to_number(v_cod_vent_devu(i));

                    UPDATE int_solic_conso_poven_detal
                       SET val_prec_cata_devu            = lv_imp_prec_cata,
                           val_prec_cont_devu            = lv_imp_prec_publ,
                           prod_oid_prod_devu            = lv_prod_oid_prod_devu,
                           copa_oid_para_gene_devu       = lv_copa_oid_para_gral,
                           lopa_oid_lote_prem_artic_devu = lv_lopa_oid_lote_prem_arti,
                           panp_oid_para_nive_prem_devu  = lv_panp_oid_para_nive_prem

                     WHERE cod_pais = pscodigopais
                       AND cod_peri = v_codperi(i)
                       AND cod_clie = v_codclie(i)
                       AND num_docu = v_numdocu(i)
                       AND tip_refe = v_tiprefe(i)
                       AND to_number(cod_vent_devu) = to_number(v_cod_vent_devu(i));
                    existedevu := TRUE;
                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_panp_oid_para_nive_prem := NULL;
                      lv_lopa_oid_lote_prem_arti := NULL;
                      lv_copa_oid_para_gral      := NULL;
                      lv_imp_prec_publ           := NULL;
                      lv_imp_prec_cata           := NULL;
                      lv_prod_oid_prod_devu      := NULL;
                      existedevu                 := FALSE;
                  END;
                END IF;
              END IF;

              IF (v_ind_devu_fisi(i) = 0) THEN
                BEGIN
                  SELECT d.tofe_oid_tipo_ofer,
                         maf.oid_matr_fact,
                         d.prod_oid_prod
                    INTO lv_tofe_oid_tipo_ofer,
                         lv_oid_matr_fact,
                         lv_prod_oid_prod_devu
                    FROM pre_ofert_detal       d,
                         pre_matri_factu       maf,
                         pre_matri_factu_cabec cab
                   WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                     AND cab.oid_cabe = maf.mfca_oid_cabe
                     AND cab.perd_oid_peri = v_oid_peri_refe(i)
                     AND to_number(d.val_codi_vent) = to_number(v_cod_vent_devu(i));

                  UPDATE int_solic_conso_poven_detal
                     SET val_prec_cata_devu = 0,
                         val_prec_cont_devu = 0,
                         oid_soli_posi_devu = NULL,
                         prod_oid_prod_devu = lv_prod_oid_prod_devu,
                         tofe_oid_devu      = lv_tofe_oid_tipo_ofer,
                         mafa_oid_devu      = lv_oid_matr_fact
                   WHERE cod_pais = pscodigopais
                     AND cod_peri = v_codperi(i)
                     AND cod_clie = v_codclie(i)
                     AND num_docu = v_numdocu(i)
                     AND tip_refe = v_tiprefe(i)
                     AND to_number(cod_vent_devu) = to_number(v_cod_vent_devu(i));

                  existedevu := TRUE;

                EXCEPTION
                  WHEN no_data_found THEN
                    lv_tofe_oid_tipo_ofer := NULL;
                    lv_oid_matr_fact      := NULL;
                    lv_prod_oid_prod_devu := NULL;
                    existedevu            := FALSE;
                END;
              END IF;

            END IF;

            existe := existedevu AND existeenvia;

          EXCEPTION
            WHEN OTHERS THEN
              existe := FALSE;
          END;
          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_RECUP_PRECI: ' || lsvalor || ls_sqlerrm);

  END sto_pr_spvd_recup_preci;

  /***************************************************************************
  Descripcion       : Validacion de caracteristicas codigo venta/premio
                      del Codigo Venta 2 (SD510)
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_carac_cvpv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor
    (
      ps_rangoinicialcodigoventa  VARCHAR2,
      ps_rangofinalcodigoventa    VARCHAR2,
      ps_rangoinicialcodigopremio VARCHAR2,
      ps_rangofinalcodigopremio   VARCHAR2
    ) IS
      SELECT det.sec_nume_docu,
             det.num_lote

        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND ((det.cod_vent_devu IS NOT NULL AND det.cod_vent_dese IS NOT NULL AND
             ps_rangoinicialcodigoventa <= det.cod_vent_devu AND
             ps_rangofinalcodigoventa >= det.cod_vent_devu AND
             ps_rangoinicialcodigoventa <= det.cod_vent_dese AND
             ps_rangofinalcodigoventa >= det.cod_vent_dese) OR
             (det.cod_vent_devu IS NOT NULL AND det.cod_vent_dese IS NOT NULL AND
             ps_rangoinicialcodigopremio <= det.cod_vent_devu AND
             ps_rangofinalcodigopremio >= det.cod_vent_devu AND
             ps_rangoinicialcodigopremio <= det.cod_vent_dese AND
             ps_rangofinalcodigopremio >= det.cod_vent_dese) OR
             (det.cod_vent_devu IS NULL AND det.cod_vent_dese IS NOT NULL AND
             ps_rangoinicialcodigopremio <= det.cod_vent_dese AND
             ps_rangofinalcodigopremio >= det.cod_vent_dese) OR
             (det.cod_vent_devu IS NULL AND det.cod_vent_dese IS NOT NULL AND
             ps_rangoinicialcodigoventa <= det.cod_vent_dese AND
             ps_rangofinalcodigoventa >= det.cod_vent_dese) OR
             (det.cod_vent_devu IS NOT NULL AND det.cod_vent_dese IS NULL AND
             ps_rangoinicialcodigopremio <= det.cod_vent_devu AND
             ps_rangofinalcodigopremio >= det.cod_vent_devu) OR
             (det.cod_vent_devu IS NOT NULL AND det.cod_vent_dese IS NULL AND
             ps_rangoinicialcodigoventa <= det.cod_vent_devu AND
             ps_rangofinalcodigoventa >= det.cod_vent_devu));

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

    j BINARY_INTEGER := 0;

    lv_rangoinicialcodigoventa  VARCHAR2(15);
    lv_rangofinalcodigoventa    VARCHAR2(15);
    lv_rangoinicialcodigopremio VARCHAR2(15);
    lv_rangofinalcodigopremio   VARCHAR2(15);

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lv_rangoinicialcodigoventa  := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                        p_rango_inicial_codigo_venta);
    lv_rangofinalcodigoventa    := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                        p_rango_final_codigo_venta);
    lv_rangoinicialcodigopremio := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                        p_rango_inicial_codigo_premio);
    lv_rangofinalcodigopremio   := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                        p_rango_final_codigo_premio);

    OPEN c_cursor(lv_rangoinicialcodigoventa,
                  lv_rangofinalcodigoventa,
                  lv_rangoinicialcodigopremio,
                  lv_rangofinalcodigopremio);
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_CARAC_CVPV: ' || ls_sqlerrm);

  END sto_pr_spvd_carac_cvpv;

  /***************************************************************************
  Descripcion       : FUNCION Verifica la garantia de premios
  Fecha Creacion    : 20/06/2012
  Autor             : Sandro Quintana Aponte
                        pstipproc = 'L'   en linea
                                  = 'S'   por STO

             OUTPUT  si es TRUE esta ok, sino es error
  ***************************************************************************/
  FUNCTION sto_fn_spvd_garan_premi
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    psfecha            DATE,
    psnumpedi          VARCHAR2,
    psoidsoliposi      NUMBER
  ) RETURN BOOLEAN IS


    v_cod_oper            int_solic_conso_poven_detal.cod_oper%TYPE;
    v_num_dias_atra       int_solic_conso_poven_detal.num_dias_atra%TYPE;
    v_oid_peri_recl       int_solic_conso_poven_cabec.oid_peri_recl%TYPE;
    v_oid_peri_refe       int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    v_fec_refe            int_solic_conso_poven_cabec.fec_refe%TYPE;
    v_cod_sap             mae_produ.cod_sap%TYPE;

    ls_anti_peri NUMBER;
    ls_dif_dias  NUMBER;
    v_fec_inic   DATE;
    v_fec_pedi   DATE;
    v_fec_ot     DATE;
    ls_num_dias  NUMBER;
    cuenta       NUMBER;
    lsparametrovalFNE   sto_param_gener_occrr.val_param%TYPE;
    lsparametrodiasGAR   sto_param_gener_occrr.val_param%TYPE;
    existe BOOLEAN := TRUE;

  BEGIN


    v_cod_oper := pscodoper;

    lsparametrodiasGAR := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_DIASGAR');

    ---- Fecha del pedido

    select fec_fact into v_fec_pedi from ped_solic_Cabec psc where psc.val_nume_soli = psnumpedi;

    ---- Fecha de OT

      BEGIN

        SELECT  b.fec_proc
          INTO v_fec_ot
          FROM int_solic_conso_orden_trans b
         WHERE b.num_docu = psnumpedi
           AND b.cod_reci_conf = 'S'
           and rownum = 1;

      EXCEPTION
        WHEN no_data_found THEN
          v_fec_ot := v_fec_pedi + to_number(nvl( lsparametrodiasGAR ,0)) ;
      END;

    ---- Codigo SAP

    select mp.cod_sap into v_cod_sap
    from ped_solic_posic psp, mae_produ mp
    where psp.prod_oid_prod = mp.oid_prod
    and psp.oid_soli_posi = psoidsoliposi;

    ---- numero de dias de garantia

      BEGIN
         select ipg.num_dias_gara
          into ls_num_dias
          from INC_PRODU_GARAN ipg
          where ipg.cod_pais = pscodigopais
            and ipg.cod_sap  = v_cod_sap
            and ipg.est_regi = '1' ;

      EXCEPTION
        WHEN no_data_found THEN
          ls_num_dias := 99999999;
      END;


    ----- Datos para procesar
      cuenta := 0;

      ---ls_dif_dias:=  trunc(sysdate) - trunc(v_fec_ot);
      ls_dif_dias:=  trunc(psfecha) - trunc(v_fec_ot);

      if ls_dif_dias <= ls_num_dias then
         cuenta := 1;
       end if;

       if cuenta = 0 then
          existe := false;
       else
          existe := true;
       end if;

    RETURN (existe);

  END sto_fn_spvd_garan_premi;


  /***************************************************************************
  Descripcion       : Validacion de Cantidad Dias hacia Atras (SD520)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_canti_diatr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.cod_vent_devu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.sta_proc,
             det.ind_devu_gener_envi,
             det.num_dias_atra,
             cab.oid_peri_recl,
             cab.oid_peri_refe,
             cab.fec_refe,
             cab.fec_digi,
             cab.ind_orig,
             det.cod_oper,
             det.mot_spv
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_sta_proc IS TABLE OF int_solic_conso_poven_detal.sta_proc%TYPE;
    TYPE t_ind_devu_gener_envi IS TABLE OF int_solic_conso_poven_detal.ind_devu_gener_envi%TYPE;
    TYPE t_num_dias_atra IS TABLE OF int_solic_conso_poven_detal.num_dias_atra%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;

    TYPE t_oid_peri_recl IS TABLE OF int_solic_conso_poven_cabec.oid_peri_recl%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_ind_orig      IS TABLE OF int_solic_conso_poven_cabec.ind_orig%TYPE;
    TYPE t_fec_refe      IS TABLE OF int_solic_conso_poven_cabec.fec_refe%TYPE;
    TYPE t_fec_digi      IS TABLE OF int_solic_conso_poven_cabec.fec_digi%TYPE;
    TYPE t_mot_spv       IS TABLE OF int_solic_conso_poven_detal.mot_spv%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numdocu       t_numdocu;
    v_tiprefe       t_tiprefe;
    v_cod_vent_devu t_cod_vent_devu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_cod_oper           t_cod_oper;

    v_sta_proc            t_sta_proc;
    v_ind_devu_gener_envi t_ind_devu_gener_envi;
    v_num_dias_atra       t_num_dias_atra;
    v_oid_peri_recl       t_oid_peri_recl;
    v_oid_peri_refe       t_oid_peri_refe;
    v_ind_orig            t_ind_orig;
    v_fec_refe            t_fec_refe;
    v_fec_digi            t_fec_digi;
    v_mot_spv             t_mot_spv;

    i            BINARY_INTEGER := 0;
    ls_anti_peri NUMBER;
    ls_dif_dias  NUMBER;
    v_fec_inic   DATE;
    cuenta       NUMBER;
    lsparametrovalFNE   sto_param_gener_occrr.val_param%TYPE;
    lsparametromotSPVD59   sto_param_gener_occrr.val_param%TYPE;
    lsparametrodiaSPVD59   sto_param_gener_occrr.val_param%TYPE;
    lscampa                int_solic_conso_cabec.cod_peri%TYPE;

    v_excep        boolean := false;
    lsfecha        cra_perio.fec_inic%TYPE;


  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsparametrovalFNE := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_VALDIAFNE');
    lsparametromotSPVD59 := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_MOT_SPVD59');
    lsparametrodiaSPVD59 := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_DIA_SPVD59');
    lsfecha := to_date('01/01/1900', 'DD/MM/YYYY');

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_cod_vent_devu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_sta_proc,
             v_ind_devu_gener_envi,
             v_num_dias_atra,
             v_oid_peri_recl,
             v_oid_peri_refe,
             v_fec_refe,
             v_fec_digi,
             v_ind_orig,
             v_cod_oper,
             v_mot_spv

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          cuenta := 0;

          lsfecha := to_date(trunc(v_fec_digi(i)) , 'DD/MM/YYYY');

          lscampa := gen_pkg_gener.gen_fn_devuelve_des_perio( v_oid_peri_refe(i) );

          v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(v_codclie(i),
                                                                           null,
                                                                           lscampa, ---null,
                                                                           lsfecha,
                                                                           'SPVD',
                                                                           'SPVD-6'));
          if not v_excep then

          --- si hay motivo registrado y es el motivo que se ingreso
          if lsparametromotSPVD59 is not null and lsparametromotSPVD59 = v_mot_spv(i) then

             ---- Verifica la antiguedad
             ls_dif_dias:=  trunc(sysdate) - trunc(v_fec_refe(i));

             if (lsparametrodiaSPVD59 is null) then
                 lsparametrodiaSPVD59 := 99999;
             end if;

             if ls_dif_dias <= lsparametrodiaSPVD59 then
             cuenta := 1;
             end if;

          end if;
          else
                 cuenta := 9;
          end if;

          if cuenta = 0 then

          IF ( (v_cod_oper(i) = 'FA' or v_cod_oper(i) = 'FM' or v_cod_oper(i) = 'MF') and
               lsparametrovalFNE = 'S')
          then
              ---- Calcula la diferencia de dias entre hoy y la fecha del pedido
              /*select to_date(sysdate,'DD-MM-YYYY') - to_date(v_fec_refe(i),'DD-MM-YYYY')
              into ls_dif_dias
                from dual;*/

               ls_dif_dias:=  trunc(sysdate) - trunc(v_fec_refe(i));

              ---- Si los dias trasncurridos son mayores a lo de la parametria se rechaza de lo contrario se acepta
              cuenta := 0;
              if ls_dif_dias <= v_num_dias_atra(i) then
                 cuenta := 1;
              end if;

          else
          ls_anti_peri := trunc(v_num_dias_atra(i) / 21);
          --existe       := FALSE;

          SELECT min(fec_inic) INTO v_fec_inic FROM cra_perio WHERE oid_peri = v_oid_peri_recl(i);

          SELECT COUNT(*)
            INTO cuenta
            FROM (SELECT tabla2.*
                    FROM (SELECT tabla.*
                            FROM (SELECT oid_peri
                                    FROM cra_perio
                                   WHERE fec_inic <= v_fec_inic
                                   ORDER BY fec_inic DESC) tabla
                           WHERE rownum <= (ls_anti_peri + 1)) tabla2
                   WHERE tabla2.oid_peri = v_oid_peri_refe(i)) auxiliar;
          END IF;

          END IF;

          IF (cuenta > 0) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_CANTI_DIAS_ATRAS: ' || ls_sqlerrm);

  END sto_pr_spvd_canti_diatr;

  /***************************************************************************
  Descripcion       : FUNCION Verifica la cantidad de dias hacia atras
  Fecha Creacion    : 20/06/2012
  Autor             : Sandro Quintana Aponte
                        pstipproc = 'L'   en linea
                                  = 'S'   por STO

             OUTPUT  si es TRUE esta ok, sino es error
  ***************************************************************************/
  FUNCTION sto_fn_spvd_canti_diatr
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    psnumpedi          VARCHAR2,
    psoidperirec       number,
    pstipproc          VARCHAR2,
    psfecrefe          date,
    psnumdias          number,
    psoidperiref       number
  ) RETURN BOOLEAN IS


    v_cod_oper            int_solic_conso_poven_detal.cod_oper%TYPE;
    v_num_dias_atra       int_solic_conso_poven_detal.num_dias_atra%TYPE;
    v_oid_peri_recl       int_solic_conso_poven_cabec.oid_peri_recl%TYPE;
    v_oid_peri_refe       int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    v_fec_refe            int_solic_conso_poven_cabec.fec_refe%TYPE;

    ls_anti_peri NUMBER;
    ls_dif_dias  NUMBER;
    v_fec_inic   DATE;
    cuenta       NUMBER;
    lsparametrovalFNE   sto_param_gener_occrr.val_param%TYPE;

    existe BOOLEAN := TRUE;

  BEGIN


    lsparametrovalFNE := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_VALDIAFNE');
    v_cod_oper := pscodoper;
    v_oid_peri_recl := psoidperirec;


    ----- Datos para procesar
    if pstipproc = 'S' then   --- Si es desde STO se toma los datos del parametro

      v_num_dias_atra := psnumdias;
      v_fec_refe      := psfecrefe;
      v_oid_peri_refe := psoidperiref;

    else
      --- Datos del pedido
      select psc.fec_fact, psc.perd_oid_peri
      into v_fec_refe,v_oid_peri_refe
      from ped_solic_cabec psc
      where psc.val_nume_soli = psnumpedi
      and psc.sbac_oid_sbac = 888;

      --- Obtiene numero de dias para el tipo de operacion
      --- 1. Convierte operacion
      --- se traslado este codigo a la funcion debe de ejecutarse primero para obtener el codigo de operacion
      --- sto_pkg_proce_valid_spvd.sto_fn_spvd_tipo_opera

      --- 2. Busca el numero de dias de la operacion
      select B.NUM_DIAS_HACI_ATRA
      into v_num_dias_atra
      from rec_opera a, rec_tipos_opera b
      where b.ROPE_OID_OPER = a.OID_OPER
      and a.cod_oper      = v_cod_oper
      and b.val_tipo_oper = pstipoper;

     end if;

          IF ( (v_cod_oper = 'FA' or v_cod_oper = 'FM' or v_cod_oper = 'MF') and
               lsparametrovalFNE = 'S')
          then
              ---- Calcula la diferencia de dias entre hoy y la fecha del pedido
              ls_dif_dias:=  trunc(sysdate) - trunc(v_fec_refe);

              ---- Si los dias trasncurridos son mayores a lo de la parametria se rechaza de lo contrario se acepta
              cuenta := 0;
              if ls_dif_dias <= v_num_dias_atra then
                 cuenta := 1;
              end if;

          else
              ls_anti_peri := trunc(v_num_dias_atra / 21);
              --existe       := FALSE;

              SELECT min(fec_inic) INTO v_fec_inic FROM cra_perio WHERE oid_peri = v_oid_peri_recl;

              SELECT COUNT(*)
                INTO cuenta
                FROM (SELECT tabla2.*
                        FROM (SELECT tabla.*
                                FROM (SELECT oid_peri
                                        FROM cra_perio
                                       WHERE fec_inic <= v_fec_inic
                                       ORDER BY fec_inic DESC) tabla
                               WHERE rownum <= (ls_anti_peri + 1)) tabla2
                       WHERE tabla2.oid_peri = v_oid_peri_refe) auxiliar;
          END IF;

       if cuenta = 0 then
          existe := false;
       else
          existe := true;
       end if;

    RETURN (existe);

  END sto_fn_spvd_canti_diatr;

  /***************************************************************************
  Descripcion       : FUNCION Verifica si el usuario tiene acceso a una operacion determinada
  Fecha Creacion    : 13/02/2013
  Autor             : Sandro Quintana Aponte
                        pstipproc = 'L'   en linea
                                  = 'S'   por STO

             OUTPUT  si es TRUE esta ok, sino es error
  ***************************************************************************/
  FUNCTION sto_fn_spvd_usuar_opera
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    psusuario          VARCHAR2
  ) RETURN BOOLEAN IS


    v_cod_oper            int_solic_conso_poven_detal.cod_oper%TYPE;
    v_num_dias_atra       int_solic_conso_poven_detal.num_dias_atra%TYPE;
    v_oid_peri_recl       int_solic_conso_poven_cabec.oid_peri_recl%TYPE;
    v_oid_peri_refe       int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    v_fec_refe            int_solic_conso_poven_cabec.fec_refe%TYPE;

    ls_anti_peri NUMBER;
    ls_dif_dias  NUMBER;
    ls_autoriza  NUMBER;
    v_fec_inic   DATE;
    cuenta       NUMBER;
    lsparametrovalFNE   sto_param_gener_occrr.val_param%TYPE;

    existe BOOLEAN := TRUE;

  BEGIN

    ---- Cuenta si tiene autorizacion
    select count(*) into cuenta from (
          select *
          from REC_USUAR_OPERA ruo
          where RUO.COD_OPER = pscodoper
          and RUO.TIP_OPER = pstipoper
          and RUO.USU_AUTO = psusuario
          union all
          select *
          from REC_USUAR_OPERA ruo
          where RUO.COD_OPER = pscodoper
          and RUO.USU_AUTO = psusuario
        ) tabla;

       if cuenta = 0 then
          existe := false;
       else
          existe := true;
       end if;

    RETURN (existe);

  END sto_fn_spvd_usuar_opera;


  /***************************************************************************
  Descripcion       : Validacion de Unidades Reclamadas (SD530)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_unida_recla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.num_lote,
             det.tip_refe,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_devu,
             det.ind_ingr_envi,
             det.ind_envi_gener_devu,
             det.oid_soli_posi_devu,
             pos.num_unid_aten,
             det.can_vent_devu,
             SUM(nvl(lin.num_unid_recl,
                     0))
        FROM int_solic_conso_poven_detal det,
             ped_solic_posic             pos,
             rec_linea_opera_recla       lin,
             sto_tmp_docum_digit         occ
      /*sto_docum_digit         occ*/
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND lin.timo_oid_tipo_movi(+) = 2
         AND lin.sopo_oid_soli_posi(+) = pos.oid_soli_posi
         AND pos.oid_soli_posi(+) = det.oid_soli_posi_devu
      /*and occ.sec_nume_docu = 17689880*/
       GROUP BY det.cod_pais,
                det.cod_peri,
                det.cod_clie,
                det.num_docu,
                det.tip_refe,
                det.docu_cod_tipo_docu,
                det.sec_nume_docu,
                det.num_lote,
                det.cod_vent_devu,
                det.ind_ingr_envi,
                det.ind_envi_gener_devu,
                det.oid_soli_posi_devu,
                pos.num_unid_aten,
                det.can_vent_devu;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_ind_ingr_envi IS TABLE OF int_solic_conso_poven_detal.ind_ingr_envi%TYPE;
    TYPE t_ind_envi_gener_devu IS TABLE OF int_solic_conso_poven_detal.ind_envi_gener_devu%TYPE;

    TYPE t_oid_soli_posi_devu IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
    TYPE t_num_unid_aten IS TABLE OF ped_solic_posic.num_unid_aten%TYPE;
    TYPE t_can_vent_devu IS TABLE OF int_solic_conso_poven_detal.can_vent_devu%TYPE;
    TYPE t_num_unid_recl IS TABLE OF rec_linea_opera_recla.num_unid_recl%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_numlote t_numlote;
    v_tiprefe t_tiprefe;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_cod_vent_devu       t_cod_vent_devu;
    v_ind_ingr_envi       t_ind_ingr_envi;
    v_ind_envi_gener_devu t_ind_envi_gener_devu;

    v_oid_soli_posi_devu t_oid_soli_posi_devu;
    v_num_unid_aten      t_num_unid_aten;
    v_can_vent_devu      t_can_vent_devu;
    v_num_unid_recl      t_num_unid_recl;

    v_num_recl_actu NUMBER;
    v_num_recl_otro NUMBER;

    lsparametroindSPVD07   sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    lsparametroindSPVD07 := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_SPVD07');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_numlote,
             v_tiprefe,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_devu,
             v_ind_ingr_envi,
             v_ind_envi_gener_devu,
             v_oid_soli_posi_devu,
             v_num_unid_aten,
             v_can_vent_devu,
             v_num_unid_recl

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          --- Si unidades atentidas es cero se pasa a la otra validacion
          IF (v_oid_soli_posi_devu(i) IS NULL OR v_num_unid_aten(i) = 0) THEN

            existe := TRUE;

          ELSE

            --- validad devuelve en el Lote actual
            SELECT nvl(SUM(nvl(det2.can_vent_devu,
                               0)),
                       0)
              INTO v_num_recl_actu
              FROM int_solic_conso_poven_detal det2,
                   sto_docum_digit             dig
             WHERE det2.cod_pais = v_codpais(i)
               AND det2.cod_clie = v_codclie(i)
               AND det2.cod_peri = v_codperi(i)
               AND det2.num_lote = v_num_lote(i)
               AND det2.oid_soli_posi_devu = v_oid_soli_posi_devu(i)
               AND dig.sec_nume_docu = det2.sec_nume_docu
               AND dig.ind_envi = 0
               AND dig.ind_rech = 0;

            --- validad devuelve en otros Lotes
            SELECT nvl(SUM(nvl(det2.can_vent_devu,
                               0)),
                       0)
              INTO v_num_recl_otro
              FROM int_solic_conso_poven_detal det2,
                   sto_docum_digit             dig
             WHERE det2.cod_pais = v_codpais(i)
               AND det2.cod_clie = v_codclie(i)
               --- SQA 15/05/2012 AND det2.cod_peri <> v_codperi(i)   --- se cambia operador
               AND det2.num_lote <> v_num_lote(i)
               AND det2.oid_soli_posi_devu = v_oid_soli_posi_devu(i)
               AND dig.sec_nume_docu = det2.sec_nume_docu
               AND dig.ind_envi = 0
               AND dig.ind_rech = 0;

            /*v_can_vent_devu(i)*/
            /*v_num_recl_actu + v_num_unid_recl(i)) THEN*/
            if (lsparametroindSPVD07 = '1' and v_num_unid_recl(i)>= v_num_unid_aten(i))   then
                existe := TRUE;
            ELSE
            IF (v_num_unid_aten(i) >= v_num_recl_actu + v_num_recl_otro + v_num_unid_recl(i)) THEN
              existe := TRUE;
            ELSE
              existe := FALSE;
            END IF;
            END IF;

          END IF;

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_unida_recla: ' || ls_sqlerrm);

  END sto_pr_spvd_unida_recla;

  /***************************************************************************
  Descripcion       : FUNCION  Verifica si excede en unidades reclamadas
  Fecha Creacion    : 20/06/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_unida_recla
  (
    pscodigopais       VARCHAR2,
    pscodclie          VARCHAR2,
    psoidsoliposi      NUMBER,
    psnumuni           NUMBER
  ) RETURN BOOLEAN IS


    v_oid_soli_posi_devu int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
    v_num_unid_aten      ped_solic_posic.num_unid_aten%TYPE;
    v_num_unid_recl      rec_linea_opera_recla.num_unid_recl%TYPE;

    v_num_recl_actu NUMBER;
    v_num_recl_otro NUMBER;

  BEGIN

      v_oid_soli_posi_devu := psoidsoliposi;
      v_num_recl_otro := psnumuni;

      BEGIN
         SELECT pos.num_unid_aten, SUM(nvl(lin.num_unid_recl,0))
          into v_num_unid_aten, v_num_unid_recl
          FROM ped_solic_posic             pos,
               rec_linea_opera_recla       lin
         WHERE lin.timo_oid_tipo_movi(+) = 2
           AND lin.sopo_oid_soli_posi(+) = pos.oid_soli_posi
           AND pos.oid_soli_posi = v_oid_soli_posi_devu
         GROUP BY pos.num_unid_aten;
      EXCEPTION
        WHEN no_data_found THEN
          v_oid_soli_posi_devu := null;
          v_num_unid_aten      := 0;
          v_num_unid_recl      := 0;
      END;

          --- Si no ingreso el cuv o no existe se toma como error
          IF (v_oid_soli_posi_devu IS NULL) THEN
            existe := FALSE;
          ELSE
             --- Si unidades atentidas es cero se toma como error
             IF (v_num_unid_aten = 0) THEN
                existe := FALSE;
             ELSE
                --- validad devuelve en el Lote actual
                SELECT nvl(SUM(nvl(det2.can_vent_devu,0)),0)
                  INTO v_num_recl_actu
                  FROM int_solic_conso_poven_detal det2,
                       sto_docum_digit             dig
                 WHERE det2.cod_pais = pscodigopais
                   AND det2.cod_clie = pscodclie
                   AND det2.oid_soli_posi_devu = v_oid_soli_posi_devu
                   AND dig.sec_nume_docu = det2.sec_nume_docu
                   AND dig.ind_envi = 0
                   AND dig.ind_rech = 0;

                IF (v_num_unid_aten >= v_num_recl_actu + v_num_recl_otro + v_num_unid_recl) THEN
                  existe := TRUE;
                ELSE
                  existe := FALSE;
                END IF;

            END IF;

          END IF;

    RETURN (existe);


  END sto_fn_spvd_unida_recla;

  /***************************************************************************
  Descripcion       : Validacion de Unidades Reclamadas de un Faltante
  Fecha Creacion    : 01/07/2011
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_unida_recla_falta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.num_lote,
             det.tip_refe,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_devu,
             det.ind_ingr_envi,
             det.ind_envi_gener_devu,
             det.oid_soli_posi_devu,
             pos.num_unid_aten,
             det.can_vent_devu,
             SUM(nvl(lin.num_unid_recl,
                     0))
        FROM int_solic_conso_poven_detal det,
             ped_solic_posic             pos,
             rec_linea_opera_recla       lin,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND lin.timo_oid_tipo_movi(+) = 2
         AND lin.sopo_oid_soli_posi(+) = pos.oid_soli_posi
         AND pos.oid_soli_posi(+) = det.oid_soli_posi_devu
       GROUP BY det.cod_pais,
                det.cod_peri,
                det.cod_clie,
                det.num_docu,
                det.tip_refe,
                det.docu_cod_tipo_docu,
                det.sec_nume_docu,
                det.num_lote,
                det.cod_vent_devu,
                det.ind_ingr_envi,
                det.ind_envi_gener_devu,
                det.oid_soli_posi_devu,
                pos.num_unid_aten,
                det.can_vent_devu;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_ind_ingr_envi IS TABLE OF int_solic_conso_poven_detal.ind_ingr_envi%TYPE;
    TYPE t_ind_envi_gener_devu IS TABLE OF int_solic_conso_poven_detal.ind_envi_gener_devu%TYPE;

    TYPE t_oid_soli_posi_devu IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
    TYPE t_num_unid_aten IS TABLE OF ped_solic_posic.num_unid_aten%TYPE;
    TYPE t_can_vent_devu IS TABLE OF int_solic_conso_poven_detal.can_vent_devu%TYPE;
    TYPE t_num_unid_recl IS TABLE OF rec_linea_opera_recla.num_unid_recl%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_numlote t_numlote;
    v_tiprefe t_tiprefe;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_cod_vent_devu       t_cod_vent_devu;
    v_ind_ingr_envi       t_ind_ingr_envi;
    v_ind_envi_gener_devu t_ind_envi_gener_devu;

    v_oid_soli_posi_devu t_oid_soli_posi_devu;
    v_num_unid_aten      t_num_unid_aten;
    v_can_vent_devu      t_can_vent_devu;
    v_num_unid_recl      t_num_unid_recl;

    lsparametroindSPVD07   sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    lsparametroindSPVD07 := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_SPVD07');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_numlote,
             v_tiprefe,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_devu,
             v_ind_ingr_envi,
             v_ind_envi_gener_devu,
             v_oid_soli_posi_devu,
             v_num_unid_aten,
             v_can_vent_devu,
             v_num_unid_recl

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          IF (v_oid_soli_posi_devu(i) IS NULL) THEN

            existe := TRUE;

          ELSE

            IF ( v_num_unid_aten(i) = 0 or
                (lsparametroindSPVD07 = '1' and v_num_unid_recl(i)>= v_num_unid_aten(i)) ) then
              existe := FALSE;
            ELSE
              existe := TRUE;
            END IF;

          END IF;

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_unida_recla_falta: ' || ls_sqlerrm);

  END sto_pr_spvd_unida_recla_falta;

  /***************************************************************************
  Descripcion       : Validacion de Unidades de Faltante de Premio (FP)
  Fecha Creacion    : 09/03/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_unida_fp
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.num_lote,
             det.cod_oper,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_dese,
             det.can_prod_dese,
             cab.oid_cabe
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
      /*sto_docum_digit         occ*/
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         and occ.sec_nume_docu_cabe = cab.sec_nume_docu
      /*and occ.sec_nume_docu = 17689880*/
       ;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_codoper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;

    TYPE t_can_prod_dese IS TABLE OF int_solic_conso_poven_detal.can_prod_dese%TYPE;

    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_numlote t_numlote;
    v_codoper t_codoper;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_cod_vent_dese       t_cod_vent_dese;

    v_can_prod_dese      t_can_prod_dese;
    v_oid_cabe           t_oid_cabe;

    v_num_recl_actu NUMBER;
    v_num_recl_otro NUMBER;
    v_oid_soli_posi      number;
    v_num_unid_aten      number;
    v_num_unid_recl      number;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_numlote,
             v_codoper,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_dese,
             v_can_prod_dese,
             v_oid_cabe

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          --Insert into PED_TMP_CLIEN (COD_CLIE) Values ('paso 01');

          --- Si no es FP  O  Si es FP y no hay cuv se aprueba todos los registros
          IF ( v_codoper(i) <> 'FP' OR
               ( v_codoper(i) = 'FP' and v_cod_vent_dese(i) is null )
               ) THEN

            existe := TRUE;
          --Insert into PED_TMP_CLIEN (COD_CLIE) Values ('True');

          ELSE

          --Insert into PED_TMP_CLIEN (COD_CLIE) Values ('paso 02');

                v_oid_soli_posi := NULL;
                v_num_unid_aten := 0;
                v_num_unid_recl := 0;

            BEGIN
                select oid_soli_posi,
                       num_unid_aten,
                       SUM(nvl(lin.num_unid_recl,0))
                into   v_oid_soli_posi ,
                       v_num_unid_aten ,
                       v_num_unid_recl
                from  rec_linea_opera_recla  lin,
                       ( SELECT c.oid_soli_posi, c.num_unid_aten
                                      FROM ped_solic_cabec a,
                                           ped_solic_cabec b,
                                           ped_solic_posic c
                                     WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                                       AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                                       AND a.oid_soli_cabe = v_oid_cabe(i)
                                       AND to_number(c.val_codi_vent_fict) = to_number(v_cod_vent_dese(i))
                                       AND c.espo_oid_esta_posi <> 2
                                       AND rownum = 1) ped
                where lin.sopo_oid_soli_posi(+) = ped.oid_soli_posi
                AND   lin.timo_oid_tipo_movi(+) = 2
                group by oid_soli_posi, num_unid_aten;
            EXCEPTION
              WHEN no_data_found THEN
                v_oid_soli_posi := NULL;
                v_num_unid_aten := 0;
                v_num_unid_recl := 0;
            END;

           --Insert into PED_TMP_CLIEN (COD_CLIE) Values ('paso 03');

            --- Si unidades atentidas es cero se pasa a la otra validacion
            IF (v_oid_soli_posi IS NULL OR v_num_unid_aten = 0) THEN

              existe := TRUE;
          --Insert into PED_TMP_CLIEN (COD_CLIE) Values ('True');

            ELSE

          --Insert into PED_TMP_CLIEN (COD_CLIE) Values ('paso 04');

              --- validad devuelve en el Lote actual
              SELECT nvl(SUM(nvl(det2.can_prod_dese,
                                 0)),
                         0)
                INTO v_num_recl_actu
                FROM int_solic_conso_poven_detal det2,
                     sto_docum_digit             dig
               WHERE det2.cod_pais = v_codpais(i)
                 AND det2.cod_clie = v_codclie(i)
                 AND det2.cod_peri = v_codperi(i)
                 AND det2.num_lote = v_num_lote(i)
                 AND det2.cod_vent_dese = to_number(v_cod_vent_dese(i))
                 AND dig.sec_nume_docu = det2.sec_nume_docu
                 AND dig.ind_envi = 0
                 AND dig.ind_rech = 0;


              --- validad devuelve en otros Lotes
              SELECT nvl(SUM(nvl(det2.can_prod_dese,
                                 0)),
                         0)
                INTO v_num_recl_otro
                FROM int_solic_conso_poven_detal det2,
                     sto_docum_digit             dig
               WHERE det2.cod_pais = v_codpais(i)
                 AND det2.cod_clie = v_codclie(i)
                 ---- SQA  15/05/2012  AND det2.cod_peri <> v_codperi(i)
                 AND det2.num_lote <> v_num_lote(i)
                 AND det2.cod_vent_dese = to_number(v_cod_vent_dese(i))
                 AND dig.sec_nume_docu = det2.sec_nume_docu
                 AND dig.ind_envi = 0
                 AND dig.ind_rech = 0;

          --Insert into PED_TMP_CLIEN (COD_CLIE) Values ('paso 05');

              IF (v_num_unid_aten >= v_num_recl_actu + v_num_recl_otro + v_num_unid_recl) THEN
                existe := TRUE;
          --Insert into PED_TMP_CLIEN (COD_CLIE) Values ('True');
              ELSE
                existe := FALSE;
          --Insert into PED_TMP_CLIEN (COD_CLIE) Values ('False');
              END IF;

            END IF;
          END IF;

          --Insert into PED_TMP_CLIEN (COD_CLIE) Values ('paso 06');

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_unida_fp: ' || ls_sqlerrm);

  END sto_pr_spvd_unida_fp;

  /***************************************************************************
  Descripcion       : Validacion de Unidades Reclamadas de un Faltante (FP)
  Fecha Creacion    : 09/03/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_unida_fp_falta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.num_lote,
             det.cod_oper,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_dese,
             det.can_prod_dese,
             cab.oid_cabe
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
      /*sto_docum_digit         occ*/
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         and occ.sec_nume_docu_cabe = cab.sec_nume_docu
      /*and occ.sec_nume_docu = 17689880*/
       ;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_codoper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;

    TYPE t_can_prod_dese IS TABLE OF int_solic_conso_poven_detal.can_prod_dese%TYPE;

    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_numlote t_numlote;
    v_codoper t_codoper;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_cod_vent_dese       t_cod_vent_dese;

    v_can_prod_dese      t_can_prod_dese;
    v_oid_cabe           t_oid_cabe;

    v_oid_soli_posi      number;
    v_num_unid_aten      number;
    v_num_unid_recl      number;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_numlote,
             v_codoper,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_dese,
             v_can_prod_dese,
             v_oid_cabe

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          --- Si no es FP  O  Si es FP y no hay cuv se aprueba todos los registros
          IF ( v_codoper(i) <> 'FP' OR
               ( v_codoper(i) = 'FP' and v_cod_vent_dese(i) is null )
               ) THEN

            existe := TRUE;

          ELSE

            BEGIN
                select oid_soli_posi,
                       num_unid_aten,
                       SUM(nvl(lin.num_unid_recl,0))
                into   v_oid_soli_posi ,
                       v_num_unid_aten ,
                       v_num_unid_recl
                from  rec_linea_opera_recla  lin,
                       ( SELECT c.oid_soli_posi, c.num_unid_aten
                                      FROM ped_solic_cabec a,
                                           ped_solic_cabec b,
                                           ped_solic_posic c
                                     WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                                       AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                                       AND a.oid_soli_cabe = v_oid_cabe(i)
                                       AND to_number(c.val_codi_vent_fict) = to_number(v_cod_vent_dese(i))
                                       AND c.espo_oid_esta_posi <> 2
                                       AND rownum = 1) ped
                where lin.sopo_oid_soli_posi(+) = ped.oid_soli_posi
                AND   lin.timo_oid_tipo_movi(+) = 2
                group by oid_soli_posi, num_unid_aten;
            EXCEPTION
              WHEN no_data_found THEN
                v_oid_soli_posi := NULL;
                v_num_unid_aten := NULL;
                v_num_unid_recl := NULL;
            END;


            --- Si unidades atentidas es cero se pasa a la otra validacion
            IF (v_oid_soli_posi IS NULL ) THEN

              existe := TRUE;

            ELSE

              IF (v_num_unid_aten = 0) THEN
                existe := FALSE;
              ELSE
                existe := TRUE;
              END IF;

            END IF;
          END IF;

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_unida_fp_falta: ' || ls_sqlerrm);

  END sto_pr_spvd_unida_fp_falta;

  /***************************************************************************
  Descripcion       : Validacion de cantidad de reclamos aceptados ECUADOR
  Fecha Creacion    : 07/05/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_canti_recla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.num_lote,
             det.cod_oper,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_dese,
             det.can_prod_dese,
             cab.oid_cabe,
             cab.sec_nume_docu
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
      /*sto_docum_digit         occ*/
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         and occ.sec_nume_docu_cabe = cab.sec_nume_docu
      /*and occ.sec_nume_docu = 17689880*/
       ;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_codoper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;

    TYPE t_can_prod_dese IS TABLE OF int_solic_conso_poven_detal.can_prod_dese%TYPE;

    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_sec_nume_docu_cabe IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_numlote t_numlote;
    v_codoper t_codoper;
    v_secnumedocucabe t_sec_nume_docu_cabe;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_cod_vent_dese       t_cod_vent_dese;

    v_can_prod_dese      t_can_prod_dese;
    v_oid_cabe           t_oid_cabe;

    v_cant_recl number;
    v_secnumeped number;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_numlote,
             v_codoper,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_dese,
             v_can_prod_dese,
             v_oid_cabe,
             v_secnumedocucabe

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          --- Si no es Faltante se aprueban los registros
          IF ( v_codoper(i) <> 'FA' AND v_codoper(i) <> 'FM' and v_codoper(i) <> 'MF'
               ) THEN

            existe := TRUE;

          ELSE

            --- Valida si es que el pedido se le ha gestionado algun Faltante
            select count(*)
            into v_cant_recl
            from rec_opera a, rec_tipos_opera b, rec_cabec_recla c, rec_opera_recla d, ped_solic_cabec e
            where C.OID_CABE_RECL = D.CARE_OID_CABE_RECL
            and D.TIOP_OID_TIPO_OPER = B.OID_TIPO_OPER
            and b.ROPE_OID_OPER = a.OID_OPER
            and C.SOCA_OID_SOLI_CABE = E.OID_SOLI_CABE
            and e.oid_soli_cabe = v_oid_cabe(i)
            and cod_oper in ('FM','FA','MF');
            ----and E.VAL_NUME_SOLI = 1201226775

            --- Si ya existe un FFNE para esta solicitud se rechaza todos los registros
            IF (v_cant_recl >= 1) THEN

              existe := FALSE;

            ELSE

              ---- Obtiene el sec_nume_docu que se debe de aprobar
              select min(C.SEC_NUME_DOCU)
              into v_secnumeped
              from int_solic_conso_poven_detal a, sto_docum_digit b, int_solic_conso_poven_Cabec c
              where A.SEC_NUME_DOCU = B.SEC_NUME_DOCU
              and B.SEC_NUME_DOCU_CABE = C.SEC_NUME_DOCU
              and A.COD_OPER in ('FA','FM','MF')
              and C.OID_CABE_RECL_REFE is null
              and C.OID_CABE = v_oid_cabe(i)
              AND b.ind_envi = 0
              AND b.ind_rech = 0;

              IF ( v_secnumedocucabe(i) = v_secnumeped ) THEN
                existe := TRUE;
              ELSE
                existe := FALSE;
              END IF;

            END IF;
          END IF;


          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_canti_recla: ' || ls_sqlerrm);

  END sto_pr_spvd_canti_recla;

  /***************************************************************************
  Descripcion       : Validacion de cantidad de reclamos aceptados ECUADOR
  Fecha Creacion    : 07/05/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_canti_recla
  (
    pscodoper          VARCHAR2,
    psnumpedi          VARCHAR2,
    psoidcabe          NUMBER,
    pssecnumedocucabe  NUMBER,
    pstipproc          VARCHAR2
  ) return BOOLEAN IS

    v_cant_recl number;
    v_secnumeped number;
    v_oid_cabe   number;
    v_secnumedocucabe number;

  BEGIN
       if pstipproc = 'L' then
            select oid_soli_Cabe
            into v_oid_cabe
            from ped_solic_cabec
            where val_nume_soli = psnumpedi;
            v_secnumedocucabe := 0;
       else
            v_oid_cabe := psoidcabe;
            v_secnumedocucabe := pssecnumedocucabe ;
       end if;

          --- Si no es Faltante se aprueban los registros
          IF ( pscodoper <> 'FA' AND pscodoper <> 'FM' and pscodoper <> 'MF'
               ) THEN

            existe := TRUE;

          ELSE


            --- Valida si es que el pedido se le ha gestionado algun Faltante
            select count(*)
            into v_cant_recl
            from rec_opera a, rec_tipos_opera b, rec_cabec_recla c, rec_opera_recla d, ped_solic_cabec e
            where C.OID_CABE_RECL = D.CARE_OID_CABE_RECL
            and D.TIOP_OID_TIPO_OPER = B.OID_TIPO_OPER
            and b.ROPE_OID_OPER = a.OID_OPER
            and C.SOCA_OID_SOLI_CABE = E.OID_SOLI_CABE
            and e.oid_soli_cabe = v_oid_cabe
            and cod_oper in ('FM','FA','MF');
            ----and E.VAL_NUME_SOLI = 1201226775

            --- Si ya existe un FFNE para esta solicitud se rechaza todos los registros
            IF (v_cant_recl >= 1) THEN
              existe := FALSE;
            ELSE
              ---- Obtiene el sec_nume_docu que se debe de aprobar
              select nvl(min(C.SEC_NUME_DOCU),0)
              into v_secnumeped
              from int_solic_conso_poven_detal a, sto_docum_digit b, int_solic_conso_poven_Cabec c
              where A.SEC_NUME_DOCU = B.SEC_NUME_DOCU
              and B.SEC_NUME_DOCU_CABE = C.SEC_NUME_DOCU
              and A.COD_OPER in ('FA','FM','MF')
              and C.OID_CABE_RECL_REFE is null
              and C.OID_CABE in v_oid_cabe
              AND b.ind_envi = 0
              AND b.ind_rech = 0;

              IF ( v_secnumedocucabe = v_secnumeped) THEN
                existe := TRUE;
              ELSE
                existe := FALSE;
              END IF;
            END IF;
          END IF;

    RETURN (existe);

  END sto_fn_spvd_canti_recla;

  /***************************************************************************
  Descripcion       : Validacion de motivo real de devolucion, actualiza datos
  Fecha Creacion    : 08/11/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_moti_real_devo
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_clie,
             det.cod_oper,
             det.cod_tipo_oper,
             det.cod_vent_devu,
             cab.oid_peri_refe,
             cab.ztad_oid_terr_admi,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             cab.oid_cabe,
             cab.oid_clie
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
      /*sto_docum_digit         occ*/
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         and occ.sec_nume_docu_cabe = cab.sec_nume_docu
      /*and occ.sec_nume_docu = 17689880*/
       ;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_codoper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_codtipoper IS TABLE OF int_solic_conso_poven_detal.cod_tipo_oper%TYPE;
    TYPE t_codventdevu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_oidperirefe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_ztadoidterradmi IS TABLE OF int_solic_conso_poven_cabec.ztad_oid_terr_admi%TYPE;
    TYPE t_docucodtipodocu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_secnumedocu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_oidcabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_oidclie IS TABLE OF int_solic_conso_poven_cabec.oid_clie%TYPE;

    v_cod_moti_real   int_solic_conso_poven_detal.cod_moti_real%TYPE;
    v_codpais         t_codpais;
    v_codclie         t_codclie;
    v_codoper         t_codoper;
    v_codtipoper      t_codtipoper;
    v_codventdevu     t_codventdevu;
    v_oidperirefe     t_oidperirefe;
    v_ztadoidterradmi t_ztadoidterradmi;
    v_docucodtipodocu t_docucodtipodocu;
    v_secnumedocu     t_secnumedocu;
    v_numlote         t_numlote;
    v_oidcabe         t_oidcabe;
    v_oidclie         t_oidclie;
    lnnumregistros BINARY_INTEGER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codclie,
             v_codoper,
             v_codtipoper,
             v_codventdevu,
             v_oidperirefe,
             v_ztadoidterradmi,
             v_docucodtipodocu,
             v_secnumedocu,
             v_numlote,
             v_oidcabe,
             v_oidclie
             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          SELECT COUNT(1), min(COD_MOTI_REAL)
            INTO lnnumregistros,v_cod_moti_real
          from
          (
          SELECT *
            FROM mae_clien_unida_admin c,
                 mae_clien             m,
                 zon_terri_admin       d,
                 zon_terri             e,
                 zon_secci             f,
                 zon_zona              g,
                 zon_regio             h,
                 STO_LISTA_BLANC       a
           WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
             AND d.zscc_oid_secc = f.oid_secc
             AND d.terr_oid_terr = e.oid_terr
             AND f.zzon_oid_zona = g.oid_zona
             AND g.zorg_oid_regi = h.oid_regi
             and c.clie_oid_clie = m.oid_clie
             AND c.ind_acti = 1
             AND d.ind_borr = 0
             AND c.clie_oid_clie = v_oidclie(i)
             ---AND (v_oidperirefe(i) = a.OID_PERI_INIC OR a.OID_PERI_INIC IS NULL) --periodo
             AND ((a.oid_peri_fina is null and a.oid_peri_inic = v_oidperirefe(i)) or
                  (a.oid_peri_fina is not null and
                   v_oidperirefe(i) between a.oid_peri_inic and oid_peri_fina ))
             AND (h.oid_regi = a.oid_regi OR a.oid_regi IS NULL) --region
             AND (g.oid_zona = a.oid_zona OR a.oid_zona IS NULL) --zon
             AND (m.cod_clie = a.cod_clie OR a.cod_clie IS NULL) --cliente
             AND a.ind_acti = '1'
             and a.cod_oper = v_codoper(i)
             and a.tip_oper = v_codtipoper(i)
             and a.cod_vent = v_codventdevu(i)
             order by a.oid_regi, a.oid_zona, a.oid_peri_fina nulls last
             ) tempo
             where rownum = 1
             ;

          ---- Actualiza datos en INT_SOLIC_CONSO_POVEN_DETAL
          UPDATE int_solic_conso_poven_detal
             SET cod_moti_real = v_cod_moti_real,
                 IND_LIST_BLAN_PROD = decode(lnnumregistros,0,'0','1')
           WHERE num_lote = v_numlote(i)
             AND sec_nume_docu = v_secnumedocu(i);

          existe := true;

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docucodtipodocu(i)
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_secnumedocu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_moti_real_devo: ' || ls_sqlerrm);

  END sto_pr_spvd_moti_real_devo;


  /***************************************************************************
  Descripcion       : Validacion de motivo real de devolucion, actualiza datos
  Fecha Creacion    : 08/11/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_moti_real_devo
  (
    pscodoCliente      VARCHAR2,
    psoidperiref       NUMBER,
    pscodventdevu      VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2
  ) RETURN BOOLEAN IS

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_codoper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_codtipoper IS TABLE OF int_solic_conso_poven_detal.cod_tipo_oper%TYPE;
    TYPE t_codventdevu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_oidperirefe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_ztadoidterradmi IS TABLE OF int_solic_conso_poven_cabec.ztad_oid_terr_admi%TYPE;
    TYPE t_docucodtipodocu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_secnumedocu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_oidcabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_oidclie IS TABLE OF int_solic_conso_poven_cabec.oid_clie%TYPE;

    v_codclie         int_solic_conso_poven_detal.cod_clie%TYPE;
    v_oidperirefe     int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    v_codventdevu     int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    v_codoper         int_solic_conso_poven_detal.cod_oper%TYPE;
    v_codtipoper      int_solic_conso_poven_detal.cod_tipo_oper%TYPE;

    lnnumregistros BINARY_INTEGER := 0;

  BEGIN

    v_codclie         := pscodoCliente;
    v_oidperirefe     := psoidperiref;
    v_codventdevu     := pscodventdevu;
    v_codoper         := pscodoper;
    v_codtipoper      := pstipoper;

          SELECT COUNT(1)  ----, min(COD_MOTI_REAL)
            INTO lnnumregistros ---,v_cod_moti_real
          from
          (
          SELECT *
            FROM mae_clien_unida_admin c,
                 mae_clien             m,
                 zon_terri_admin       d,
                 zon_terri             e,
                 zon_secci             f,
                 zon_zona              g,
                 zon_regio             h,
                 STO_LISTA_BLANC       a
           WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
             AND d.zscc_oid_secc = f.oid_secc
             AND d.terr_oid_terr = e.oid_terr
             AND f.zzon_oid_zona = g.oid_zona
             AND g.zorg_oid_regi = h.oid_regi
             and c.clie_oid_clie = m.oid_clie
             AND c.ind_acti = 1
             AND d.ind_borr = 0
             AND c.clie_oid_clie = gen_pkg_gener.gen_fn_devuelve_id_cliente(v_codclie)
             ---AND (v_oidperirefe(i) = a.OID_PERI_INIC OR a.OID_PERI_INIC IS NULL) --periodo
             AND ((a.oid_peri_fina is null and a.oid_peri_inic = v_oidperirefe) or
                  (a.oid_peri_fina is not null and
                   v_oidperirefe between a.oid_peri_inic and oid_peri_fina ))
             AND (h.oid_regi = a.oid_regi OR a.oid_regi IS NULL) --region
             AND (g.oid_zona = a.oid_zona OR a.oid_zona IS NULL) --zon
             AND (m.cod_clie = a.cod_clie OR a.cod_clie IS NULL) --cliente
             AND a.ind_acti = '1'
             and a.cod_oper = v_codoper
             and a.tip_oper = v_codtipoper
             and a.cod_vent = v_codventdevu
             order by a.oid_regi, a.oid_zona, a.oid_peri_fina nulls last
             ) tempo
             where rownum = 1
             ;

        IF (lnnumregistros > 0) THEN
          existe := TRUE;
        ELSE
          existe := FALSE;
        END IF;

    RETURN (existe);

  END sto_fn_spvd_moti_real_devo;

  /***************************************************************************
  Descripcion       : Validacion para ingresar de manera automatica las excepciones a la validacion
  Fecha Creacion    : 12/04/2013
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_agre_excep_auto
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_clie,
             det.cod_oper,
             det.cod_tipo_oper,
             det.cod_vent_devu,
             cab.oid_peri_refe,
             cab.ztad_oid_terr_admi,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             cab.oid_cabe,
             cab.oid_clie,
             det.mot_spv
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
      /*sto_docum_digit         occ*/
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         and occ.sec_nume_docu_cabe = cab.sec_nume_docu
      /*and occ.sec_nume_docu = 17689880*/
       ;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_codoper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_codtipoper IS TABLE OF int_solic_conso_poven_detal.cod_tipo_oper%TYPE;
    TYPE t_codventdevu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_oidperirefe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_ztadoidterradmi IS TABLE OF int_solic_conso_poven_cabec.ztad_oid_terr_admi%TYPE;
    TYPE t_docucodtipodocu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_secnumedocu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_oidcabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_oidclie IS TABLE OF int_solic_conso_poven_cabec.oid_clie%TYPE;
    TYPE t_motspv  IS TABLE OF int_solic_conso_poven_detal.mot_spv%TYPE;

    v_cod_moti_real   int_solic_conso_poven_detal.cod_moti_real%TYPE;
    v_codpais         t_codpais;
    v_codclie         t_codclie;
    v_codoper         t_codoper;
    v_codtipoper      t_codtipoper;
    v_codventdevu     t_codventdevu;
    v_oidperirefe     t_oidperirefe;
    v_ztadoidterradmi t_ztadoidterradmi;
    v_docucodtipodocu t_docucodtipodocu;
    v_secnumedocu     t_secnumedocu;
    v_numlote         t_numlote;
    v_oidcabe         t_oidcabe;
    v_oidclie         t_oidclie;
    v_motspv          t_motspv;
    lnnumregistros BINARY_INTEGER := 0;

    lsparametromotSPVD59   sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    lsparametromotSPVD59 := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_MOT_SPVD59');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codclie,
             v_codoper,
             v_codtipoper,
             v_codventdevu,
             v_oidperirefe,
             v_ztadoidterradmi,
             v_docucodtipodocu,
             v_secnumedocu,
             v_numlote,
             v_oidcabe,
             v_oidclie,
             v_motspv
             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          --- si hay motivo registrado y es el motivo que se ingreso
          if lsparametromotSPVD59 is not null and lsparametromotSPVD59 = v_motspv(i) then

              --- Verifica si hay validaciones para agregar en la tabla de excepciones
              SELECT COUNT(1)
                INTO lnnumregistros
              from sto_param_Gener_occrr a
              where A.COD_PARA like 'STO_MOT_SPVD59_%';

              if lnnumregistros > 0 then

                  INSERT INTO STO_EXCLU_VALID(
                      OID_EXVA,
                      COD_PAIS,
                      COD_TIPO_DOCU,
                      COD_VALI,
                      COD_CLIE,
                      FEC_PROC,
                      IND_REGI,
                      FEC_DIGI,
                      USU_DIGI
                  )
                  select BAS_SEQ_STO_EXCLU_VALID.NEXTVAL,
                         pscodigopais,
                         substr(a.val_param,1,4),
                         val_param,
                         v_codclie(i),
                         trunc(sysdate),
                         '1',
                         sysdate,
                         psusuario
                  from sto_param_Gener_occrr a
                  where A.COD_PARA like 'STO_MOT_SPVD59_%';

              end if;

          end if;

          existe := true;

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docucodtipodocu(i)
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_secnumedocu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_agre_excep_auto: ' || ls_sqlerrm);

  END sto_pr_spvd_agre_excep_auto;


  /***************************************************************************
  Descripcion       : Validacion de Unidades Boletas Electronicas CHILE
  Fecha Creacion    : 16/04/2012
  Autor             : Sandro Quintana Aponte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_unida_bolec
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.num_lote,
             det.tip_refe,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_devu,
             det.ind_ingr_envi,
             det.ind_envi_gener_devu,
             det.oid_soli_posi_devu,
             det.val_prec_cata_devu,
             pos.num_unid_aten,
             det.can_vent_devu,
             SUM(nvl(lin.num_unid_recl,
                     0))
        FROM int_solic_conso_poven_detal det,
             ped_solic_posic             pos,
             rec_linea_opera_recla       lin,
             sto_tmp_docum_digit         occ
      /*sto_docum_digit         occ*/
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND lin.timo_oid_tipo_movi(+) = 2
         AND lin.sopo_oid_soli_posi(+) = pos.oid_soli_posi
         AND pos.oid_soli_posi(+) = det.oid_soli_posi_devu
      /*and occ.sec_nume_docu = 17689880*/
       GROUP BY det.cod_pais,
                det.cod_peri,
                det.cod_clie,
                det.num_docu,
                det.tip_refe,
                det.docu_cod_tipo_docu,
                det.sec_nume_docu,
                det.num_lote,
                det.cod_vent_devu,
                det.ind_ingr_envi,
                det.ind_envi_gener_devu,
                det.oid_soli_posi_devu,
                det.val_prec_cata_devu,
                pos.num_unid_aten,
                det.can_vent_devu;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_ind_ingr_envi IS TABLE OF int_solic_conso_poven_detal.ind_ingr_envi%TYPE;
    TYPE t_ind_envi_gener_devu IS TABLE OF int_solic_conso_poven_detal.ind_envi_gener_devu%TYPE;

    TYPE t_oid_soli_posi_devu IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
    TYPE t_num_unid_aten IS TABLE OF ped_solic_posic.num_unid_aten%TYPE;
    TYPE t_can_vent_devu IS TABLE OF int_solic_conso_poven_detal.can_vent_devu%TYPE;
    TYPE t_num_unid_recl IS TABLE OF rec_linea_opera_recla.num_unid_recl%TYPE;
    TYPE t_val_prec_cata_devu IS TABLE OF int_solic_conso_poven_detal.val_prec_cata_devu%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_numlote t_numlote;
    v_tiprefe t_tiprefe;
    v_val_prec_cata_devu  t_val_prec_cata_devu ;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_cod_vent_devu       t_cod_vent_devu;
    v_ind_ingr_envi       t_ind_ingr_envi;
    v_ind_envi_gener_devu t_ind_envi_gener_devu;

    v_oid_soli_posi_devu t_oid_soli_posi_devu;
    v_num_unid_aten      t_num_unid_aten;
    v_can_vent_devu      t_can_vent_devu;
    v_num_unid_recl      t_num_unid_recl;

    v_num_recl_actu NUMBER;
    v_num_recl_otro NUMBER;
    v_num_bole_elec NUMBER;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_numlote,
             v_tiprefe,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_devu,
             v_ind_ingr_envi,
             v_ind_envi_gener_devu,
             v_oid_soli_posi_devu,
             v_val_prec_cata_devu,
             v_num_unid_aten,
             v_can_vent_devu,
             v_num_unid_recl

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          --- Si no tiene oid de retorno o unidades atentidas es cero no se toma en cuenta
          --- debido a que esta rechazada con las otras validaciones SPVD-7 y SPVD-51
          --- Esto con el fin de no tener muchos registros para procesar
          IF (v_oid_soli_posi_devu(i) IS NULL OR v_num_unid_aten(i) = 0
              or v_val_prec_cata_devu(i) = 0) THEN

            existe := TRUE;

          ELSE

            --- validad devuelve en el Lote actual
            SELECT nvl(SUM(nvl(det2.can_vent_devu,
                               0)),
                       0)
              INTO v_num_recl_actu
              FROM int_solic_conso_poven_detal det2,
                   sto_docum_digit             dig
             WHERE det2.cod_pais = v_codpais(i)
               AND det2.cod_clie = v_codclie(i)
               AND det2.cod_peri = v_codperi(i)
               AND det2.num_lote = v_num_lote(i)
               AND det2.oid_soli_posi_devu = v_oid_soli_posi_devu(i)
               AND dig.sec_nume_docu = det2.sec_nume_docu
               AND dig.ind_envi = 0
               AND dig.ind_rech = 0;

            --- validad devuelve en otros Lotes
            SELECT nvl(SUM(nvl(det2.can_vent_devu,
                               0)),
                       0)
              INTO v_num_recl_otro
              FROM int_solic_conso_poven_detal det2,
                   sto_docum_digit             dig
             WHERE det2.cod_pais = v_codpais(i)
               AND det2.cod_clie = v_codclie(i)
               --- SQA 15/05/2012  AND det2.cod_peri <> v_codperi(i)   --- se cambia operador
               AND det2.num_lote <> v_num_lote(i)
               AND det2.oid_soli_posi_devu = v_oid_soli_posi_devu(i)
               AND dig.sec_nume_docu = det2.sec_nume_docu
               AND dig.ind_envi = 0
               AND dig.ind_rech = 0;

            --- Verifica Boletas electronicas disponibles
            SELECT nvl(SUM(nvl(bolec.num_unid,
                               0)),
                       0)
              INTO v_num_bole_elec
              FROM ped_bolet_elect_histo bolec
             WHERE bolec.oid_soli_posi_pedi = v_oid_soli_posi_devu(i)
               AND bolec.ind_recl not in( 1 , 2 );
               ----AND bolec.ind_recl not in('S','P');

            --- El nro de Boletas Electronicas disponibles debe de ser mayor o igual que lo solicitado
            IF (v_num_bole_elec >= v_num_recl_actu + v_num_recl_otro ) THEN
              existe := TRUE;
            ELSE
              existe := FALSE;
            END IF;

          END IF;

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_unida_bolec: ' || ls_sqlerrm);

  END sto_pr_spvd_unida_bolec;


  /***************************************************************************
  Descripcion       : Validacion de Faltante mercancia/Facturado no enviado
                      (SD540)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_fmefa_noenv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.cod_vent_devu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.esta_oid_esta_clie,
             det.val_revi_cheq,
             det.sta_proc,
             det.prod_oid_prod_envi,
             cab.fec_proc_docu,
             cab.oid_clie,
             op.ind_falt_merc
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             rec_opera                   op,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND op.cod_oper = det.cod_oper
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND det.docu_cod_tipo_docu = pscodigotipodoc
            --AND ((det.esta_oid_esta_clie <> 2 AND det.val_revi_cheq IS NOT NULL) OR (det.val_revi_cheq IS NULL));
         AND ((op.ind_falt_merc <> 1) OR (det.val_revi_cheq IS NULL) OR
             (op.ind_falt_merc = 1 AND det.esta_oid_esta_clie <> 2 AND
             det.val_revi_cheq IS NOT NULL));

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_esta_oid_esta_clie IS TABLE OF int_solic_conso_poven_detal.esta_oid_esta_clie%TYPE;
    TYPE t_val_revi_cheq IS TABLE OF int_solic_conso_poven_detal.val_revi_cheq%TYPE;
    TYPE t_sta_proc IS TABLE OF int_solic_conso_poven_detal.sta_proc%TYPE;
    TYPE t_prod_oid_prod_envi IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_envi%TYPE;
    TYPE t_fec_proc_docu IS TABLE OF int_solic_conso_poven_cabec.fec_proc_docu%TYPE;
    TYPE t_oid_clie IS TABLE OF int_solic_conso_poven_cabec.oid_clie%TYPE;

    TYPE t_ind_falt_merc IS TABLE OF rec_opera.ind_falt_merc%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numdocu       t_numdocu;
    v_tiprefe       t_tiprefe;
    v_cod_vent_devu t_cod_vent_devu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_esta_oid_esta_clie t_esta_oid_esta_clie;
    v_val_revi_cheq      t_val_revi_cheq;
    v_sta_proc           t_sta_proc;
    v_prod_oid_prod_envi t_prod_oid_prod_envi;
    v_fec_proc_docu      t_fec_proc_docu;
    v_oid_clie           t_oid_clie;
    v_ind_falt_merc      t_ind_falt_merc;

    lv_oidprod mae_produ.oid_prod%TYPE;

    lv_diasparafm INTEGER(15);
    lv_numerofm   INTEGER(15);
    lv_total      INTEGER(15);

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lv_diasparafm := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                          p_dias_para_fm);
    lv_numerofm   := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                          p_numero_fm);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_cod_vent_devu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_esta_oid_esta_clie,
             v_val_revi_cheq,
             v_sta_proc,
             v_prod_oid_prod_envi,
             v_fec_proc_docu,
             v_oid_clie,
             v_ind_falt_merc

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          BEGIN
            IF (v_ind_falt_merc(i) = 1 AND v_esta_oid_esta_clie(i) <> 2 AND
               v_val_revi_cheq(i) IS NOT NULL) THEN
              SELECT COUNT(*) total
                INTO lv_total
                FROM rec_cabec_recla a,
                     rec_opera_recla b,
                     rec_tipos_opera e,
                     rec_opera       f
               WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
                 AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
                 AND e.rope_oid_oper = f.oid_oper
                 AND f.ind_falt_merc = 1
                 AND a.clie_oid_clie = v_oid_clie(i)
                 AND trunc(v_fec_proc_docu(i)) - a.fec_ingr <= lv_diasparafm;

              IF (lv_total >= lv_numerofm) THEN
                SELECT b.oid_prod
                  INTO lv_oidprod
                  FROM rec_produ_fm a,
                       mae_produ    b
                 WHERE a.cod_sap = b.cod_sap
                   AND a.fec_desde <= v_fec_proc_docu(i)
                   AND a.fec_hasta >= v_fec_proc_docu(i)
                   AND b.oid_prod = v_prod_oid_prod_envi(i);
              END IF;
            END IF;
            existe := TRUE;

          EXCEPTION
            WHEN no_data_found THEN
              existe := FALSE;
          END;
          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_FMEFA_NOENV: ' || ls_sqlerrm);

  END sto_pr_spvd_fmefa_noenv;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en devoluciones
  Fecha Creacion    : 21/02/2013
  Autor             : Sandro Quintana Aponte
                      SPVD-8
  ***************************************************************************/
  FUNCTION sto_fn_spvd_fmefa_noenv
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  )  RETURN BOOLEAN IS

    lsoidclie       int_solic_conso_poven_cabec.oid_clie%TYPE;
    v_fec_proc_docu  cra_crono.fec_inic%TYPE;

    lnnrorecla   NUMBER;

    lv_diasparafm INTEGER(15);
    lv_numerofm   INTEGER(15);

  BEGIN

    existe := TRUE;

    if (pscodoperacion = 'FM' or pscodoperacion = 'FA') then

      lv_diasparafm := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            p_dias_para_fm);
      lv_numerofm   := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            p_numero_fm);

       ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
        BEGIN
          select PSC1.clie_oid_clie into lsoidclie
          from ped_solic_cabec psc1
          where psc1.val_nume_soli = psnumpedi
          and rownum = 1;
        EXCEPTION
          WHEN no_data_found THEN
            lsoidclie := 0;
        END;

        select trunc(sysdate) into v_fec_proc_docu from dual;

        SELECT COUNT(*) into lnnrorecla
        FROM rec_cabec_recla a,
             rec_opera_recla b,
             rec_tipos_opera e,
             rec_opera       f
        WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
         AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
         AND e.rope_oid_oper = f.oid_oper
         AND f.cod_oper in ('FM','FA')
         AND a.clie_oid_clie = lsoidclie
         AND trunc(v_fec_proc_docu) - a.fec_ingr <= lv_diasparafm;


        IF (lnnrorecla >= lv_numerofm) THEN
          existe := FALSE;
        ELSE
          existe := TRUE;
        END IF;

    end if;

    RETURN (existe);

  END sto_fn_spvd_fmefa_noenv;

  /***************************************************************************
  Descripcion       :Validacion del Tipo de Bloqueo en Devoluciones (SD610)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_tipo_bldev
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor
    (
      lv_cantmaxdn  NUMBER,
      lv_diasparadn NUMBER
    ) IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND ((det.cod_oper <> 'DN') OR
             (det.cod_oper = 'DN' AND
             lv_cantmaxdn >=
             (SELECT COUNT(*) total
                  FROM rec_cabec_recla a,
                       rec_opera_recla b,
                       rec_tipos_opera e,
                       rec_opera       f
                 WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
                   AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
                   AND e.rope_oid_oper = f.oid_oper
                   AND f.cod_oper = 'DN'
                   AND a.clie_oid_clie = cab.oid_clie
                   AND trunc(cab.fec_proc_docu) - a.fec_ingr <= lv_diasparadn)));
    --AND ((det.cod_oper <> 'DN' AND cab.ind_bloq_solo_devu <> 1) OR (det.cod_oper = 'DN'));

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

    lncantmaxdn  NUMBER;
    lndiasparadn NUMBER;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    lndiasparadn := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         p_dias_para_dn);
    lncantmaxdn  := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         p_numero_dn);

    OPEN c_cursor(lncantmaxdn,
                  lndiasparadn);
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_TIPO_BLDEV: ' || ls_sqlerrm);

  END sto_pr_spvd_tipo_bldev;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en devoluciones
  Fecha Creacion    : 21/02/2013
  Autor             : Sandro Quintana Aponte
                      SPVD-9
  ***************************************************************************/
  FUNCTION sto_fn_spvd_tipo_bldev
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  )  RETURN BOOLEAN IS

    lsoidclie       int_solic_conso_poven_cabec.oid_clie%TYPE;
    v_fec_proc_docu  cra_crono.fec_inic%TYPE;

    lnnrorecla   NUMBER;
    lncantmaxdn  NUMBER;
    lndiasparadn NUMBER;

  BEGIN

    existe := TRUE;

    if (pscodoperacion = 'DN' or pscodoperacion = 'DR' or pscodoperacion = 'DO') then

        lndiasparadn := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             p_dias_para_dn);
        lncantmaxdn  := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             p_numero_dn);

       ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
        BEGIN
          select PSC1.clie_oid_clie into lsoidclie
          from ped_solic_cabec psc1
          where psc1.val_nume_soli = psnumpedi
          and rownum = 1;
        EXCEPTION
          WHEN no_data_found THEN
            lsoidclie := 0;
        END;

        select trunc(sysdate) into v_fec_proc_docu from dual;

        SELECT COUNT(*) into lnnrorecla
        FROM rec_cabec_recla a,
             rec_opera_recla b,
             rec_tipos_opera e,
             rec_opera       f
        WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
         AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
         AND e.rope_oid_oper = f.oid_oper
         AND f.cod_oper in ('DN','DR','DO')
         AND a.clie_oid_clie = lsoidclie
         AND trunc(v_fec_proc_docu) - a.fec_ingr <= lndiasparadn;


        IF (lnnrorecla >= lncantmaxdn) THEN
          existe := FALSE;
        ELSE
          existe := TRUE;
        END IF;

    end if;

    RETURN (existe);

  END sto_fn_spvd_tipo_bldev;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Faltante de PRemios
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
                      SPVD-64
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recur_falta_premi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor
    (
      lv_cantmax  NUMBER,
      lv_diaspara NUMBER
    ) IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND ((det.cod_oper <> 'FP') OR
             (det.cod_oper = 'FP' AND
             lv_cantmax >=
             (SELECT COUNT(*) total
                  FROM rec_cabec_recla a,
                       rec_opera_recla b,
                       rec_tipos_opera e,
                       rec_opera       f
                 WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
                   AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
                   AND e.rope_oid_oper = f.oid_oper
                   AND f.cod_oper = 'FP'
                   AND a.clie_oid_clie = cab.oid_clie
                   AND trunc(cab.fec_proc_docu) - a.fec_ingr <= lv_diaspara)));

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

    lncantmax  NUMBER;
    lndiaspara NUMBER;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    lndiaspara := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         p_dias_para_fp);
    lncantmax  := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         p_numero_fp);

    OPEN c_cursor(lncantmax,
                  lndiaspara);
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_recur_falta_premi: ' || ls_sqlerrm);

  END sto_pr_spvd_recur_falta_premi;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Faltante de PRemios
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
                      SPVD-64
  ***************************************************************************/
  FUNCTION sto_fn_spvd_recur_falta_premi
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  )  RETURN BOOLEAN IS

    lsoidclie       int_solic_conso_poven_cabec.oid_clie%TYPE;
    v_fec_proc_docu  cra_crono.fec_inic%TYPE;

    lnnrorecla   NUMBER;
    lncantmax  NUMBER;
    lndiaspara NUMBER;

  BEGIN

    existe := TRUE;

    if (pscodoperacion = 'FP' or pscodoperacion = 'PF' ) then

        lndiaspara := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             p_dias_para_fp);
        lncantmax  := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             p_numero_fp);

       ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
        BEGIN
          select PSC1.clie_oid_clie into lsoidclie
          from ped_solic_cabec psc1
          where psc1.val_nume_soli = psnumpedi
          and rownum = 1;
        EXCEPTION
          WHEN no_data_found THEN
            lsoidclie := 0;
        END;

        select trunc(sysdate) into v_fec_proc_docu from dual;

        SELECT COUNT(*) into lnnrorecla
        FROM rec_cabec_recla a,
             rec_opera_recla b,
             rec_tipos_opera e,
             rec_opera       f
        WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
         AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
         AND e.rope_oid_oper = f.oid_oper
         AND f.cod_oper in ('FP','PF')
         AND a.clie_oid_clie = lsoidclie
         AND trunc(v_fec_proc_docu) - a.fec_ingr <= lndiaspara;


        IF (lnnrorecla >= lncantmax) THEN
          existe := FALSE;
        ELSE
          existe := TRUE;
        END IF;

    end if;

    RETURN (existe);

  END sto_fn_spvd_recur_falta_premi;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Error Interno
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
                      SPVD-65
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recur_error_inter
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor
    (
      lv_cantmax  NUMBER,
      lv_diaspara NUMBER
    ) IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND ((det.cod_oper not in ('ES','EA','ET','SE','TE') ) OR
             (det.cod_oper in ('ES','EA','ET','SE','TE') AND
             lv_cantmax >=
             (SELECT COUNT(*) total
                  FROM rec_cabec_recla a,
                       rec_opera_recla b,
                       rec_tipos_opera e,
                       rec_opera       f
                 WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
                   AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
                   AND e.rope_oid_oper = f.oid_oper
                   AND f.cod_oper in ('ES','EA','ET','SE','TE')
                   AND a.clie_oid_clie = cab.oid_clie
                   AND trunc(cab.fec_proc_docu) - a.fec_ingr <= lv_diaspara)));

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

    lncantmax  NUMBER;
    lndiaspara NUMBER;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    lndiaspara := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         p_dias_para_es);
    lncantmax  := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         p_numero_es);

    OPEN c_cursor(lncantmax,
                  lndiaspara);
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_recur_error_inter: ' || ls_sqlerrm);

  END sto_pr_spvd_recur_error_inter;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Error Interno
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
                      SPVD-65
  ***************************************************************************/
  FUNCTION sto_fn_spvd_recur_error_inter
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  )  RETURN BOOLEAN IS

    lsoidclie       int_solic_conso_poven_cabec.oid_clie%TYPE;
    v_fec_proc_docu  cra_crono.fec_inic%TYPE;

    lnnrorecla   NUMBER;
    lncantmax    NUMBER;
    lndiaspara  NUMBER;

  BEGIN

    existe := TRUE;

    if (pscodoperacion = 'ES' or pscodoperacion = 'EA' or pscodoperacion = 'ET' or
        pscodoperacion = 'SE' or pscodoperacion = 'TE')  then

        lndiaspara   := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             p_dias_para_es);
        lncantmax    := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             p_numero_es);

       ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
        BEGIN
          select PSC1.clie_oid_clie into lsoidclie
          from ped_solic_cabec psc1
          where psc1.val_nume_soli = psnumpedi
          and rownum = 1;
        EXCEPTION
          WHEN no_data_found THEN
            lsoidclie := 0;
        END;

        select trunc(sysdate) into v_fec_proc_docu from dual;

        SELECT COUNT(*) into lnnrorecla
        FROM rec_cabec_recla a,
             rec_opera_recla b,
             rec_tipos_opera e,
             rec_opera       f
        WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
         AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
         AND e.rope_oid_oper = f.oid_oper
         AND f.cod_oper in ('ES','EA','ET','SE','TE')
         AND a.clie_oid_clie = lsoidclie
         AND trunc(v_fec_proc_docu) - a.fec_ingr <= lndiaspara;


        IF (lnnrorecla >= lncantmax) THEN
          existe := FALSE;
        ELSE
          existe := TRUE;
        END IF;

    end if;

    RETURN (existe);

  END sto_fn_spvd_recur_error_inter;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Producto Gratis
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
                      SPVD-66
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recur_produ_grati
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor
    (
      lv_cantmax  NUMBER,
      lv_diaspara NUMBER
    ) IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND ((det.cod_oper not in ('AM','AP','MA','PA') ) OR
             (det.cod_oper in ('AM','AP','MA','PA') AND
             lv_cantmax >=
             (SELECT COUNT(*) total
                  FROM rec_cabec_recla a,
                       rec_opera_recla b,
                       rec_tipos_opera e,
                       rec_opera       f
                 WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
                   AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
                   AND e.rope_oid_oper = f.oid_oper
                   AND f.cod_oper in ('AM','AP','MA','PA')
                   AND a.clie_oid_clie = cab.oid_clie
                   AND trunc(cab.fec_proc_docu) - a.fec_ingr <= lv_diaspara)));

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

    lncantmax  NUMBER;
    lndiaspara NUMBER;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    lndiaspara := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         p_dias_para_gr);
    lncantmax  := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         p_numero_gr);

    OPEN c_cursor(lncantmax,
                  lndiaspara);
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_recur_produ_grati: ' || ls_sqlerrm);

  END sto_pr_spvd_recur_produ_grati;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Producto Gratis
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
                      SPVD-66
  ***************************************************************************/
  FUNCTION sto_fn_spvd_recur_produ_grati
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  )  RETURN BOOLEAN IS

    lsoidclie       int_solic_conso_poven_cabec.oid_clie%TYPE;
    v_fec_proc_docu  cra_crono.fec_inic%TYPE;

    lnnrorecla   NUMBER;
    lncantmax    NUMBER;
    lndiaspara  NUMBER;

  BEGIN

    existe := TRUE;

    if (pscodoperacion = 'AM' or pscodoperacion = 'AP' or
        pscodoperacion = 'MA' or pscodoperacion = 'PA')  then

        lndiaspara   := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             p_dias_para_es);
        lncantmax    := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             p_numero_es);

       ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
        BEGIN
          select PSC1.clie_oid_clie into lsoidclie
          from ped_solic_cabec psc1
          where psc1.val_nume_soli = psnumpedi
          and rownum = 1;
        EXCEPTION
          WHEN no_data_found THEN
            lsoidclie := 0;
        END;

        select trunc(sysdate) into v_fec_proc_docu from dual;

        SELECT COUNT(*) into lnnrorecla
        FROM rec_cabec_recla a,
             rec_opera_recla b,
             rec_tipos_opera e,
             rec_opera       f
        WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
         AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
         AND e.rope_oid_oper = f.oid_oper
         AND f.cod_oper in ('AM','AP','MA','PA')
         AND a.clie_oid_clie = lsoidclie
         AND trunc(v_fec_proc_docu) - a.fec_ingr <= lndiaspara;


        IF (lnnrorecla >= lncantmax) THEN
          existe := FALSE;
        ELSE
          existe := TRUE;
        END IF;

    end if;

    RETURN (existe);

  END sto_fn_spvd_recur_produ_grati;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Cambio de producto
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
                      SPVD-68
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recur_cambi_produ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor
    (
      lv_cantmax  NUMBER,
      lv_diaspara NUMBER
    ) IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND ((det.cod_oper not in ('CM','MC') ) OR
             (det.cod_oper in ('CM','MC') AND
             lv_cantmax >=
             (SELECT COUNT(*) total
                  FROM rec_cabec_recla a,
                       rec_opera_recla b,
                       rec_tipos_opera e,
                       rec_opera       f
                 WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
                   AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
                   AND e.rope_oid_oper = f.oid_oper
                   AND f.cod_oper in ('CM','MC')
                   AND a.clie_oid_clie = cab.oid_clie
                   AND trunc(cab.fec_proc_docu) - a.fec_ingr <= lv_diaspara)));

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

    lncantmax  NUMBER;
    lndiaspara NUMBER;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    lndiaspara := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         p_dias_para_cm);
    lncantmax  := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         p_numero_cm);

    OPEN c_cursor(lncantmax,
                  lndiaspara);
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_recur_cambi_produ: ' || ls_sqlerrm);

  END sto_pr_spvd_recur_cambi_produ;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Cambio de Producto
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
                      SPVD-68
  ***************************************************************************/
  FUNCTION sto_fn_spvd_recur_cambi_produ
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  )  RETURN BOOLEAN IS

    lsoidclie       int_solic_conso_poven_cabec.oid_clie%TYPE;
    v_fec_proc_docu  cra_crono.fec_inic%TYPE;

    lnnrorecla   NUMBER;
    lncantmax    NUMBER;
    lndiaspara  NUMBER;

  BEGIN

    existe := TRUE;

    if (pscodoperacion = 'CM' or pscodoperacion = 'MC' )  then

        lndiaspara   := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             p_dias_para_cm);
        lncantmax    := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             p_numero_cm);

       ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
        BEGIN
          select PSC1.clie_oid_clie into lsoidclie
          from ped_solic_cabec psc1
          where psc1.val_nume_soli = psnumpedi
          and rownum = 1;
        EXCEPTION
          WHEN no_data_found THEN
            lsoidclie := 0;
        END;

        select trunc(sysdate) into v_fec_proc_docu from dual;

        SELECT COUNT(*) into lnnrorecla
        FROM rec_cabec_recla a,
             rec_opera_recla b,
             rec_tipos_opera e,
             rec_opera       f
        WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
         AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
         AND e.rope_oid_oper = f.oid_oper
         AND f.cod_oper in ('CM','MC')
         AND a.clie_oid_clie = lsoidclie
         AND trunc(v_fec_proc_docu) - a.fec_ingr <= lndiaspara;


        IF (lnnrorecla >= lncantmax) THEN
          existe := FALSE;
        ELSE
          existe := TRUE;
        END IF;

    end if;

    RETURN (existe);

  END sto_fn_spvd_recur_cambi_produ;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Trueque de producto
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
                      SPVD-69
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recur_trueq_produ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor
    (
      lv_cantmax  NUMBER,
      lv_diaspara NUMBER
    ) IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND ((det.cod_oper not in ('TM','MT') ) OR
             (det.cod_oper in ('TM','MT') AND
             lv_cantmax >=
             (SELECT COUNT(*) total
                  FROM rec_cabec_recla a,
                       rec_opera_recla b,
                       rec_tipos_opera e,
                       rec_opera       f
                 WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
                   AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
                   AND e.rope_oid_oper = f.oid_oper
                   AND f.cod_oper in ('TM','MT')
                   AND a.clie_oid_clie = cab.oid_clie
                   AND trunc(cab.fec_proc_docu) - a.fec_ingr <= lv_diaspara)));

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

    lncantmax  NUMBER;
    lndiaspara NUMBER;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    lndiaspara := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         p_dias_para_tm);
    lncantmax  := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         p_numero_tm);

    OPEN c_cursor(lncantmax,
                  lndiaspara);
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_recur_trueq_produ: ' || ls_sqlerrm);

  END sto_pr_spvd_recur_trueq_produ;

  /***************************************************************************
  Descripcion       : Validacion de recurrencia en Truque de Producto
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana Aponte
                      SPVD-69
  ***************************************************************************/
  FUNCTION sto_fn_spvd_recur_trueq_produ
  (
    pscodigopais          VARCHAR2,
    pscodoperacion        VARCHAR2,
    psnumpedi             VARCHAR2
  )  RETURN BOOLEAN IS

    lsoidclie       int_solic_conso_poven_cabec.oid_clie%TYPE;
    v_fec_proc_docu  cra_crono.fec_inic%TYPE;

    lnnrorecla   NUMBER;
    lncantmax    NUMBER;
    lndiaspara  NUMBER;

  BEGIN

    existe := TRUE;

    if (pscodoperacion = 'TM' or pscodoperacion = 'MT')  then

        lndiaspara   := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             p_dias_para_tm);
        lncantmax    := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             p_numero_tm);

       ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
        BEGIN
          select PSC1.clie_oid_clie into lsoidclie
          from ped_solic_cabec psc1
          where psc1.val_nume_soli = psnumpedi
          and rownum = 1;
        EXCEPTION
          WHEN no_data_found THEN
            lsoidclie := 0;
        END;

        select trunc(sysdate) into v_fec_proc_docu from dual;

        SELECT COUNT(*) into lnnrorecla
        FROM rec_cabec_recla a,
             rec_opera_recla b,
             rec_tipos_opera e,
             rec_opera       f
        WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
         AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
         AND e.rope_oid_oper = f.oid_oper
         AND f.cod_oper in ('TM','MT')
         AND a.clie_oid_clie = lsoidclie
         AND trunc(v_fec_proc_docu) - a.fec_ingr <= lndiaspara;


        IF (lnnrorecla >= lncantmax) THEN
          existe := FALSE;
        ELSE
          existe := TRUE;
        END IF;

    end if;

    RETURN (existe);

  END sto_fn_spvd_recur_trueq_produ;


  /***************************************************************************
  Descripcion       : Validacion de Garantia de codigos de venta (SD620)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_garan_codvet
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.cod_vent_devu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.prod_oid_prod_devu,
             det.cod_oper,
             det.cod_tipo_oper,
             det.sta_proc,
             cab.fec_proc_docu,
             cab.fec_refe
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND det.docu_cod_tipo_docu = pscodigotipodoc;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_prod_oid_prod_devu IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_devu%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_cod_tipo_oper IS TABLE OF int_solic_conso_poven_detal.cod_tipo_oper%TYPE;
    TYPE t_sta_proc IS TABLE OF int_solic_conso_poven_detal.sta_proc%TYPE;
    TYPE t_fec_proc_docu IS TABLE OF int_solic_conso_poven_cabec.fec_proc_docu%TYPE;
    TYPE t_fec_refe IS TABLE OF int_solic_conso_poven_cabec.fec_refe%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numdocu       t_numdocu;
    v_tiprefe       t_tiprefe;
    v_cod_vent_devu t_cod_vent_devu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_prod_oid_prod_devu t_prod_oid_prod_devu;
    v_cod_oper           t_cod_oper;
    v_cod_tipo_oper      t_cod_tipo_oper;
    v_sta_proc           t_sta_proc;

    v_fec_proc_docu t_fec_proc_docu;
    v_fec_refe      t_fec_refe;

    lv_meses rec_garan_tipo_opera.meses%TYPE;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_cod_vent_devu,

             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,

             v_prod_oid_prod_devu,
             v_cod_oper,
             v_cod_tipo_oper,
             v_sta_proc,
             v_fec_proc_docu,
             v_fec_refe

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe := FALSE;
          BEGIN
            SELECT a.meses
              INTO lv_meses
              FROM rec_garan_tipo_opera a,
                   mae_produ            b
             WHERE a.cod_sap = b.cod_sap
               AND b.oid_prod = v_prod_oid_prod_devu(i)
               AND a.cod_oper = v_cod_oper(i)
               AND a.cod_tipo_oper = v_cod_tipo_oper(i);

            existe := TRUE;
          EXCEPTION
            WHEN no_data_found THEN
              BEGIN
                SELECT a.meses
                  INTO lv_meses
                  FROM rec_garan_tipo_opera a,
                       mae_produ            b,
                       mae_negoc            c
                 WHERE a.cod_nego = c.cod_nego
                   AND a.cod_sap = b.cod_sap
                   AND b.nego_oid_nego = c.oid_nego
                   AND b.oid_prod = v_prod_oid_prod_devu(i)
                   AND a.cod_oper = v_cod_oper(i)
                   AND a.cod_tipo_oper = v_cod_tipo_oper(i);
                existe := TRUE;
              EXCEPTION
                WHEN no_data_found THEN
                  existe := FALSE;
                  UPDATE sto_docum_digit occ
                     SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                         occ.cod_ulti_vali_exit = pscodigovalidactual,
                         occ.usu_modi           = psusuario,
                         occ.fec_modi           = SYSDATE
                   WHERE occ.cod_pais = v_codpais(i)
                     AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
                     AND occ.num_lote = v_num_lote(i)
                     AND occ.sec_nume_docu = v_sec_nume_docu(i);

              END;

          END;
          IF (existe) THEN
            IF (v_fec_proc_docu(i) <= (v_fec_refe(i) + lv_meses * 30)) THEN
              UPDATE sto_docum_digit occ
                 SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = v_codpais(i)
                 AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
                 AND occ.num_lote = v_num_lote(i)
                 AND occ.sec_nume_docu = v_sec_nume_docu(i);
            END IF;

          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_GARAN_CODVET: ' || ls_sqlerrm);

  END sto_pr_spvd_garan_codvet;

  /***************************************************************************
  Descripcion       : Validacion de Trueques de codigos de venta -
                      por generico/supergenerico- (SD630)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_trueq_gesge
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.cod_vent_devu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_oper,
             det.prod_oid_prod_envi,
             det.prod_oid_prod_devu
        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_prod_oid_prod_envi IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_envi%TYPE;
    TYPE t_prod_oid_prod_devu IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_devu%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numdocu       t_numdocu;
    v_tiprefe       t_tiprefe;
    v_cod_vent_devu t_cod_vent_devu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_cod_oper           t_cod_oper;
    v_prod_oid_prod_envi t_prod_oid_prod_envi;
    v_prod_oid_prod_devu t_prod_oid_prod_devu;

    lv_oid_prod mae_produ.oid_prod%TYPE;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_cod_vent_devu,

             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,

             v_cod_oper,
             v_prod_oid_prod_envi,
             v_prod_oid_prod_devu LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          BEGIN
            IF (v_cod_oper(i) = p_codigo_trueque_premio) THEN
              SELECT a.oid_prod
                INTO lv_oid_prod
                FROM mae_produ a,
                     mae_produ b
               WHERE nvl(a.sgen_oid_supe_gene,
                         0) = nvl(b.sgen_oid_supe_gene,
                                  0)
                 AND nvl(a.gene_oid_gene,
                         0) = nvl(b.gene_oid_gene,
                                  0)
                 AND a.oid_prod = v_prod_oid_prod_envi(i)
                 AND b.oid_prod = v_prod_oid_prod_devu(i);
            END IF;
            existe := TRUE;

          EXCEPTION
            WHEN no_data_found THEN
              existe := FALSE;
          END;

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;
      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_TRUEQ_GESGE: ' || ls_sqlerrm);

  END sto_pr_spvd_trueq_gesge;

  /***************************************************************************
  Descripcion       : Validacion de Garantia de codigos de premios
                      (SD710 Y SD715)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_garan_codpr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.cod_oper,
             det.cod_tipo_oper,
             det.fec_digi,
             cab.num_docu_cruc,
             cab.cod_clie,
             det.oid_soli_posi_devu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND det.docu_cod_tipo_docu = pscodigotipodoc;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_cod_tipo_oper IS TABLE OF int_solic_conso_poven_detal.cod_tipo_oper%TYPE;
    TYPE t_fec_digi IS TABLE OF int_solic_conso_poven_detal.fec_digi%TYPE;
    TYPE t_oid_soli_posi_devu IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
    TYPE t_num_docu_cruc IS TABLE OF int_solic_conso_poven_cabec.num_docu_cruc%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_poven_cabec.cod_clie%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

    v_cod_oper            t_cod_oper       ;
    v_cod_tipo_oper       t_cod_tipo_oper   ;
    v_fec_digi            t_fec_digi         ;
    v_oid_soli_posi_devu  t_oid_soli_posi_devu;
    v_num_docu_cruc       t_num_docu_cruc     ;
    v_cod_clie       t_cod_clie     ;

  lsfecha        cra_perio.fec_inic%TYPE;
  cuenta         NUMBER := 0;
    v_excep        boolean := false;
    v_existe        boolean := false;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_cod_oper,
             v_cod_tipo_oper,
             v_fec_digi,
             v_num_docu_cruc,
             v_cod_clie,
             v_oid_soli_posi_devu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          cuenta := 0;

          lsfecha := to_date(trunc(v_fec_digi(i)) , 'DD/MM/YYYY');

          v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(v_cod_clie(i),
                                                                           null,
                                                                           null,
                                                                           lsfecha,
                                                                           'SPVD',
                                                                           'SPVD-71'));
          if v_excep then
                 cuenta := 9;
          end if;

          if cuenta = 0 then

            IF ( v_oid_soli_posi_devu(i) is null ) then
                   cuenta := 1;
            else

                v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_garan_premi(pscodigopais,
                                                                        v_cod_oper(i),
                                                                        v_cod_tipo_oper(i),
                                                                        v_fec_digi(i),
                                                                        v_num_docu_cruc(i),
                                                                        v_oid_soli_posi_devu(i)));
                if v_excep then
                   cuenta := 2;
                END IF;
            END IF;

          END IF;

          IF (cuenta > 0) THEN

          UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
      END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;

    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_GARAN_CODPR: ' || ls_sqlerrm);

  END sto_pr_spvd_garan_codpr;

  /***************************************************************************
  Descripcion       : Validacion de Trueques de premios (por premios diferente nivel)
                     (SD720)
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_trupr_pdniv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND ((det.ind_true_prem = '1' AND
             det.panp_oid_para_nive_prem_envi = det.panp_oid_para_nive_prem_devu AND
             det.copa_oid_para_gene_envi = det.copa_oid_para_gene_devu) OR
             (det.ind_true_prem IS NULL OR det.ind_true_prem = '0'));

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_TRUPR_PDNIV: ' || ls_sqlerrm);

  END sto_pr_spvd_trupr_pdniv;

  /***************************************************************************
  Descripcion       : Validacion de la Devolucion de premios (SD730)
  Fecha Creacion    : 12/06/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_devul_premi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor
    (
      ps_rangoinicialcodigopremio VARCHAR2,
      ps_rangofinalcodigopremio   VARCHAR2
    ) IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND ((det.cod_oper IN ('CP',
                                'TP') AND det.cod_vent_devu >= ps_rangoinicialcodigopremio AND
             det.cod_vent_devu <= ps_rangofinalcodigopremio) OR
             (det.cod_oper NOT IN ('CP',
                                    'TP')));

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

    lv_rangoinicialcodigopremio VARCHAR2(15);
    lv_rangofinalcodigopremio   VARCHAR2(15);

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    lv_rangoinicialcodigopremio := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                        p_rango_inicial_codigo_premio);
    lv_rangofinalcodigopremio   := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                        p_rango_final_codigo_premio);
    OPEN c_cursor(lv_rangoinicialcodigopremio,
                  lv_rangofinalcodigopremio);
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_DEVUL_PREMI: ' || ls_sqlerrm);

  END sto_pr_spvd_devul_premi;

  /****************************************************************************
  Descripcion       : Validacion de Excepciones codigos de venta (SD640)
  Fecha Creacion    : 30/05/2008
  Autor             : Leonardo Lizana Chauca
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_excep_codve
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_dese,
             det.cod_vent_devu,
             det.cod_oper,
             det.cod_tipo_oper,
             cab.oid_peri_refe,
             det.ind_list_blan_prod
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.cod_pais = det.cod_pais;
    --         and 16123826 = occ.sec_nume_docu;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_cod_tipo_oper IS TABLE OF int_solic_conso_poven_detal.cod_tipo_oper%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_ind_list_blan_prod IS TABLE OF int_solic_conso_poven_detal.ind_list_blan_prod%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;
    v_cod_vent_dese t_cod_vent_dese;
    v_cod_vent_devu t_cod_vent_devu;
    v_cod_oper      t_cod_oper;
    v_cod_tipo_oper t_cod_tipo_oper;
    v_oid_peri_refe t_oid_peri_refe;
    v_ind_list_blan_prod t_ind_list_blan_prod;

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_dese,
             v_cod_vent_devu,
             v_cod_oper,
             v_cod_tipo_oper,
             v_oid_peri_refe,
             v_ind_list_blan_prod
             LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          existe := TRUE;

          ---- Si esta maracado por la lista blanca de productos o motivo de aprobacion no se procesa
          if  v_ind_list_blan_prod(j) = '1' then
              existe := TRUE;
          else
          BEGIN

            numero := 0;

            SELECT COUNT(1)
              INTO numero
              FROM rec_excep_codig_venta_opera a,
                   pre_ofert_detal             b,
                   pre_matri_factu_cabec       c,
                   cra_perio                   d,
                   seg_perio_corpo             e,
                   pre_ofert                   f,
                   pre_tipo_ofert              g,
                   pre_catal                   h
             WHERE b.ofer_oid_ofer = f.oid_ofer
               AND f.mfca_oid_cabe = c.oid_cabe
               AND c.perd_oid_peri = d.oid_peri
               AND d.peri_oid_peri = e.oid_peri
               AND c.perd_oid_peri = v_oid_peri_refe(j)
               AND a.cod_peri <= e.cod_peri --decode(e.COD_PERI,null, a.cod_peri,e.COD_PERI)
               AND nvl(a.cod_peri_fina,
                       a.cod_peri) >= e.cod_peri
               AND b.tofe_oid_tipo_ofer = g.oid_tipo_ofer
               AND f.ocat_oid_cata = h.oid_cata
               AND to_number(v_cod_vent_devu(j)) = to_number(b.val_codi_vent)
               AND a.cod_oper = v_cod_oper(j)
               AND a.cod_tipo_oper = v_cod_tipo_oper(j)
               AND to_number(a.cod_vent) = to_number(b.val_codi_vent)
               AND a.cod_vent IS NOT NULL;
            IF numero = 0 THEN

              /*BEGIN
              EXCEPTION
              WHEN OTHERS THEN

              END;*/

              SELECT COUNT(1)
                INTO numero
                FROM rec_excep_codig_venta_opera a,
                     pre_ofert_detal             b,
                     pre_matri_factu_cabec       c,
                     cra_perio                   d,
                     seg_perio_corpo             e,
                     pre_ofert                   f,
                     pre_tipo_ofert              g,
                     pre_catal                   h
               WHERE b.ofer_oid_ofer = f.oid_ofer
                 AND f.mfca_oid_cabe = c.oid_cabe
                 AND c.perd_oid_peri = d.oid_peri
                 AND d.peri_oid_peri = e.oid_peri
                 AND c.perd_oid_peri = v_oid_peri_refe(j)
                 AND a.cod_peri <= e.cod_peri --decode(e.COD_PERI,null, a.cod_peri,e.COD_PERI)
                 AND nvl(a.cod_peri_fina,
                         a.cod_peri) >= e.cod_peri
                 AND b.tofe_oid_tipo_ofer = g.oid_tipo_ofer
                 AND f.ocat_oid_cata = h.oid_cata
                 AND a.cod_tipo_ofer = g.cod_tipo_ofer
                 AND a.cod_oper = v_cod_oper(j)
                 AND a.cod_tipo_oper = v_cod_tipo_oper(j)
                    --AND  to_number(A.COD_VENT)=to_number(b.val_codi_vent)
                 AND to_number(v_cod_vent_devu(j)) = to_number(b.val_codi_vent)
                 AND a.cod_tipo_ofer IS NOT NULL;

              IF numero = 0 THEN
                SELECT COUNT(1)
                  INTO numero
                  FROM rec_excep_codig_venta_opera a,
                       pre_ofert_detal             b,
                       pre_matri_factu_cabec       c,
                       cra_perio                   d,
                       seg_perio_corpo             e,
                       pre_ofert                   f,
                       pre_tipo_ofert              g,
                       pre_catal                   h
                 WHERE b.ofer_oid_ofer = f.oid_ofer
                   AND f.mfca_oid_cabe = c.oid_cabe
                   AND c.perd_oid_peri = d.oid_peri
                   AND d.peri_oid_peri = e.oid_peri
                   AND c.perd_oid_peri = v_oid_peri_refe(j)
                   AND a.cod_peri <= e.cod_peri --decode(e.COD_PERI,null, a.cod_peri,e.COD_PERI)
                   AND nvl(a.cod_peri_fina,
                           a.cod_peri) >= e.cod_peri
                   AND b.tofe_oid_tipo_ofer = g.oid_tipo_ofer
                   AND f.ocat_oid_cata = h.oid_cata
                   AND a.cod_cata = h.cod_cata
                   AND a.cod_oper = v_cod_oper(j)
                   AND a.cod_tipo_oper = v_cod_tipo_oper(j)
                   AND to_number(v_cod_vent_devu(j)) = to_number(b.val_codi_vent)
                      --AND  to_number(A.COD_VENT)=to_number(b.val_codi_vent)
                   AND a.cod_cata IS NOT NULL;
              END IF;

            END IF;
            IF (numero > 0) THEN
              existe := FALSE;

            ELSE
              existe := TRUE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;

          END;

          END IF;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_EXCEP_CODVE: ' || ls_sqlerrm);

  END sto_pr_spvd_excep_codve;

  /****************************************************************************
  Descripcion       : FUNCION Verifica si el CUV esta en la tabla de excepciones de CUV
  Fecha Creacion    : 20/06/2012
  Autor             : Sandro Quintana Aponte

             OUTPUT  si existe es true  el codigo esta bien
                     si existe es false el codigo tiene excepcion
  ***************************************************************************/
  FUNCTION sto_fn_spvd_excep_codve
  (
    psoidperiref       NUMBER,
    pscodventdevu      VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2
  ) RETURN BOOLEAN IS

    v_oid_peri_refe int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    v_cod_vent_devu int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    v_cod_oper      int_solic_conso_poven_detal.cod_oper%TYPE;
    v_cod_tipo_oper int_solic_conso_poven_detal.cod_tipo_oper%TYPE;

    existe BOOLEAN := TRUE;
    numero NUMBER := 0;

  BEGIN

    v_oid_peri_refe := psoidperiref;
    v_cod_vent_devu := pscodventdevu;
    v_cod_oper      := pscodoper;
    v_cod_tipo_oper := pstipoper;


      existe := TRUE;

      BEGIN

        numero := 0;

        SELECT COUNT(1)
          INTO numero
          FROM rec_excep_codig_venta_opera a,
               pre_ofert_detal             b,
               pre_matri_factu_cabec       c,
               cra_perio                   d,
               seg_perio_corpo             e,
               pre_ofert                   f,
               pre_tipo_ofert              g,
               pre_catal                   h
         WHERE b.ofer_oid_ofer = f.oid_ofer
           AND f.mfca_oid_cabe = c.oid_cabe
           AND c.perd_oid_peri = d.oid_peri
           AND d.peri_oid_peri = e.oid_peri
           AND c.perd_oid_peri = v_oid_peri_refe
           AND a.cod_peri <= e.cod_peri
           AND nvl(a.cod_peri_fina,
                   a.cod_peri) >= e.cod_peri
           AND b.tofe_oid_tipo_ofer = g.oid_tipo_ofer
           AND f.ocat_oid_cata = h.oid_cata
           AND to_number(v_cod_vent_devu) = to_number(b.val_codi_vent)
           AND a.cod_oper = v_cod_oper
           AND a.cod_tipo_oper = v_cod_tipo_oper
           AND to_number(a.cod_vent) = to_number(b.val_codi_vent)
           AND a.cod_vent IS NOT NULL;

        IF numero = 0 THEN

          SELECT COUNT(1)
            INTO numero
            FROM rec_excep_codig_venta_opera a,
                 pre_ofert_detal             b,
                 pre_matri_factu_cabec       c,
                 cra_perio                   d,
                 seg_perio_corpo             e,
                 pre_ofert                   f,
                 pre_tipo_ofert              g,
                 pre_catal                   h
           WHERE b.ofer_oid_ofer = f.oid_ofer
             AND f.mfca_oid_cabe = c.oid_cabe
             AND c.perd_oid_peri = d.oid_peri
             AND d.peri_oid_peri = e.oid_peri
             AND c.perd_oid_peri = v_oid_peri_refe
             AND a.cod_peri <= e.cod_peri
             AND nvl(a.cod_peri_fina,
                     a.cod_peri) >= e.cod_peri
             AND b.tofe_oid_tipo_ofer = g.oid_tipo_ofer
             AND f.ocat_oid_cata = h.oid_cata
             AND a.cod_tipo_ofer = g.cod_tipo_ofer
             AND a.cod_oper = v_cod_oper
             AND a.cod_tipo_oper = v_cod_tipo_oper
             AND to_number(v_cod_vent_devu) = to_number(b.val_codi_vent)
             AND a.cod_tipo_ofer IS NOT NULL;

          IF numero = 0 THEN
            SELECT COUNT(1)
              INTO numero
              FROM rec_excep_codig_venta_opera a,
                   pre_ofert_detal             b,
                   pre_matri_factu_cabec       c,
                   cra_perio                   d,
                   seg_perio_corpo             e,
                   pre_ofert                   f,
                   pre_tipo_ofert              g,
                   pre_catal                   h
             WHERE b.ofer_oid_ofer = f.oid_ofer
               AND f.mfca_oid_cabe = c.oid_cabe
               AND c.perd_oid_peri = d.oid_peri
               AND d.peri_oid_peri = e.oid_peri
               AND c.perd_oid_peri = v_oid_peri_refe
               AND a.cod_peri <= e.cod_peri
               AND nvl(a.cod_peri_fina,
                       a.cod_peri) >= e.cod_peri
               AND b.tofe_oid_tipo_ofer = g.oid_tipo_ofer
               AND f.ocat_oid_cata = h.oid_cata
               AND a.cod_cata = h.cod_cata
               AND a.cod_oper = v_cod_oper
               AND a.cod_tipo_oper = v_cod_tipo_oper
               AND to_number(v_cod_vent_devu) = to_number(b.val_codi_vent)
               AND a.cod_cata IS NOT NULL;
          END IF;

        END IF;

        IF (numero > 0) THEN
          existe := FALSE;
        ELSE
          existe := TRUE;
        END IF;

      EXCEPTION
        WHEN no_data_found THEN
          existe := TRUE;
      END;

    RETURN (existe);

  END sto_fn_spvd_excep_codve;

  /***************************************************************************
  Descripcion       : VALIDACION CORPORATIVA PORCENTAJE MONTO BRUTO POR DEVOLUCION
  Fecha Creacion    : 22/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_porce_devol
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             ped.val_tota_paga_loca,
             det.cod_oper,
             ped.val_nume_soli,
             det.ind_list_blan_prod,
             (select count(*) from sto_param_gener_occrr x
              where x.cod_para like 'STO_MOT_APRO%'
              and x.val_param = det.mot_spv) motapro
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             ped_solic_cabec             ped,
             ---sto_docum_digit         occ
             sto_tmp_docum_digit occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
            ---and det.num_lote = '00000540'
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.oid_cabe = ped.oid_soli_cabe
         AND cab.cod_pais = det.cod_pais
         and occ.sec_nume_docu_cabe = cab.sec_nume_docu;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_motapro IS TABLE OF int_solic_conso_poven_detal.IND_NUME_VECE_PEDI%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_val_tota_paga_loca IS TABLE OF ped_solic_cabec.val_tota_paga_loca%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_ind_list_blan_prod IS TABLE OF int_solic_conso_poven_detal.ind_list_blan_prod%TYPE;

    TYPE t_val_nume_soli IS TABLE OF ped_solic_cabec.val_nume_soli%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_tiprefe t_tiprefe;
    v_motapro t_motapro;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_val_tota_paga_loca t_val_tota_paga_loca;
    v_cod_oper           t_cod_oper;
    v_ind_list_blan_prod t_ind_list_blan_prod;

    v_val_nume_soli t_val_nume_soli;

    existe     BOOLEAN := TRUE;
    monto_devu NUMBER := 0;

    lnoidcliente        mae_clien.oid_clie%TYPE;
    lnporcentaje        NUMBER := 0;
    lnmontopedido       NUMBER;
    lnmontodevolucion   NUMBER;
    lnmontodocgestion   NUMBER;
    lnmontototalgestion NUMBER;
    lsparametrovaldev   sto_param_gener_occrr.val_param%TYPE;
    lsparametrobaloon   sto_param_gener_occrr.val_param%TYPE;
    lsmensaje           varchar2(500);

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    lsparametrovaldev := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_VALDEV');
    lsparametrobaloon := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_BALOON_SPV');

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_val_tota_paga_loca,
             v_cod_oper,
             v_val_nume_soli,
             v_ind_list_blan_prod,
             v_motapro
             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe     := FALSE;
          monto_devu := 0;

          IF (v_cod_oper(i) != 'DN' or
              (v_cod_oper(i) = 'DN' and v_ind_list_blan_prod(i) = '1' ) or
              (v_cod_oper(i) = 'DN' and v_motapro(i) > 0 ) ) THEN

            existe := TRUE;

          ELSE

            IF (v_val_tota_paga_loca(i) IS NULL OR v_val_tota_paga_loca(i) = 0) THEN
              existe := FALSE;

            ELSE

              -- Nuevo parametro
              ----if sto_pkg_gener.sto_fn_obten_param_ocr(v_codpais(i),'STO_IND_VALDEV') = '0' then

              IF lsparametrovaldev = '1' THEN

                BEGIN
                  lnoidcliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(v_codclie(i));
                EXCEPTION
                  WHEN OTHERS THEN
                    lnoidcliente := '';
                END;

                lnporcentaje := rec_pkg_proce.rec_fn_porce_monto_devol(lnoidcliente,
                                                                       v_codpais(i),
                                                                       v_codperi(i));

                rec_pkg_proce.rec_pr_monto_evalu_devol(v_val_nume_soli(i),
                                                       lnmontopedido,
                                                       lnmontodevolucion);

                rec_pkg_proce.rec_pr_monto_gesti_devol(v_val_nume_soli(i),
                                                       lnmontototalgestion,
                                                       v_num_lote(i),
                                                       v_numdocu(i),
                                                       v_codclie(i),
                                                       v_codperi(i),
                                                       v_codpais(i),
                                                       lnmontodocgestion);

                IF lnporcentaje = 100 THEN
                  existe := TRUE;
                ELSE
                  IF ((lnmontototalgestion + abs(lnmontodevolucion)) / lnmontopedido) * 100 <=
                     lnporcentaje THEN
                    existe := TRUE;
                  else
                    if lsparametrobaloon = '1' then
                     lsmensaje := 'Excede el '|| TO_CHAR(lnporcentaje,'999.99')||'% permitido' ||
                                  ' (Total pedido : '|| TO_CHAR(lnmontopedido,'999999,999.99') ||') '||
                                  ' (Total devuelto : '|| TO_CHAR(abs(lnmontodevolucion),'999999,999.99')||') ' ||
                                  ' (Total en gestion : '|| TO_CHAR(lnmontototalgestion,'999999,999.99')||') ' ||
                                    ' (% calculado : '|| TO_CHAR(((lnmontototalgestion + abs(lnmontodevolucion)) / lnmontopedido) * 100 ,'999.99') || '%)' ;
                     sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(i),v_num_lote(i),lsmensaje);
                  END IF;
                END IF;
                END IF;

              ELSE

                monto_devu := sto_pkg_gener.sto_fn_devue_produ_canti_devue(v_codpais(i),
                                                                           v_codperi(i),
                                                                           v_codclie(i),
                                                                           v_numdocu(i),
                                                                           v_docu_cod_tipo_docu(i));

                lnporcentaje := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_PMON_DEV');

                IF (monto_devu / v_val_tota_paga_loca(i) * 100 <=
                   sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         'STO_PMON_DEV')) THEN

                  existe := TRUE;
                ELSE
                    if lsparametrobaloon = '1' then
                     lsmensaje := 'Excede el '|| TO_CHAR(lnporcentaje,'999.99')||'% permitido' ||
                                  ' (Total pedido : '|| TO_CHAR(v_val_tota_paga_loca(i),'999999,999.99') ||') '||
                                  ' (Total devuelto : '|| TO_CHAR(monto_devu,'999999,999.99')||') ' ||
                                    ' (% calculado : '|| TO_CHAR((monto_devu / v_val_tota_paga_loca(i) * 100),'999.99')||'%)'  ;
                     sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(i),v_num_lote(i),lsmensaje);
                END IF;
                END IF;

              END IF;

            END IF;
          END IF;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_PORCE_DEVOL: ' || ls_sqlerrm);

  END sto_pr_spvd_porce_devol;

  /***************************************************************************
  Descripcion       : VALIDACION CORPORATIVA PORCENTAJE UNIDADES POR DEVOLUCION
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_porce_unida
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             ped.num_unid_aten_tota,
             det.cod_oper,
             det.ind_list_blan_prod
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             ped_solic_cabec             ped,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.oid_cabe = ped.oid_soli_cabe
         AND cab.cod_pais = det.cod_pais;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_ind_list_blan_prod IS TABLE OF int_solic_conso_poven_detal.ind_list_blan_prod%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_num_unid_aten_tota IS TABLE OF ped_solic_cabec.num_unid_aten_tota%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_tiprefe t_tiprefe;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_num_unid_aten_tota t_num_unid_aten_tota;
    v_cod_oper           t_cod_oper;
    v_ind_list_blan_prod t_ind_list_blan_prod;

    existe     BOOLEAN := TRUE;
    monto_devu NUMBER := 0;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_num_unid_aten_tota,
             v_cod_oper,
             v_ind_list_blan_prod
             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          existe     := FALSE;
          monto_devu := 0;

          IF ((v_cod_oper(i) != 'DN') or
              (v_cod_oper(i) = 'DN' and v_ind_list_blan_prod(i) = '1')) THEN

            existe := TRUE;

          ELSE

            IF (v_num_unid_aten_tota(i) IS NULL OR v_num_unid_aten_tota(i) = 0) THEN
              existe := FALSE;

            ELSE

              monto_devu := sto_pkg_gener.sto_fn_devue_venta_devue_devue(v_codpais(i),
                                                                         v_codperi(i),
                                                                         v_codclie(i),
                                                                         v_numdocu(i),
                                                                         v_docu_cod_tipo_docu(i));

              IF (monto_devu / v_num_unid_aten_tota(i) * 100 <=
                 sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                       'STO_PUNI_DEV')) THEN

                existe := TRUE;
              ELSE
                existe := FALSE;
              END IF;
            END IF;
          END IF;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_PORCE_UNIDA: ' || ls_sqlerrm);

  END sto_pr_spvd_porce_unida;

  /***************************************************************************
  Descripcion       : VALIDACION CORPORATIVA DE DESVIACION DE PRECIOS PARA TRUEQUES
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_desvi_preci
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.docu_cod_tipo_docu,
             cab.sec_nume_docu,
             det.num_lote,
             det.cod_oper,
             det.ind_list_blan_prod,
             SUM(det.val_prec_cata_devu * det.can_vent_devu),
             SUM(det.val_prec_cata_envi * det.can_prod_dese)
      --det.cod_vent_devu,
      --det.cod_vent_dese

        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.cod_pais = det.cod_pais
       GROUP BY det.cod_pais,
                det.cod_peri,
                det.cod_clie,
                det.num_docu,
                det.tip_refe,
                det.docu_cod_tipo_docu,
                cab.sec_nume_docu,
                det.num_lote,
                det.cod_oper,
                det.ind_list_blan_prod;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_cabec.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_val_prec_cata_devu_tota IS TABLE OF int_solic_conso_poven_detal.val_prec_cata_devu%TYPE;
    TYPE t_val_prec_cata_envi_tota IS TABLE OF int_solic_conso_poven_detal.val_prec_cata_envi%TYPE;
    TYPE t_ind_list_blan_prod IS TABLE OF int_solic_conso_poven_detal.ind_list_blan_prod%TYPE;

    --TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    --TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_tiprefe t_tiprefe;

    v_docu_cod_tipo_docu      t_docu_cod_tipo_docu;
    v_sec_nume_docu           t_sec_nume_docu;
    v_num_lote                t_num_lote;
    v_cod_oper                t_cod_oper;
    v_val_prec_cata_devu_tota t_val_prec_cata_devu_tota;
    v_val_prec_cata_envi_tota t_val_prec_cata_envi_tota;
    v_ind_list_blan_prod t_ind_list_blan_prod;

    --v_cod_vent_devu t_cod_vent_devu;
    --v_cod_vent_dese t_cod_vent_dese;

    existe     BOOLEAN := TRUE;
    monto_devu NUMBER := 0;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_oper,
             v_ind_list_blan_prod,
             v_val_prec_cata_devu_tota,
             v_val_prec_cata_envi_tota --,v_cod_vent_devu,v_cod_vent_dese
             LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        existe     := FALSE;
        monto_devu := 0;
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          IF (v_cod_oper(i) != 'TM') or
            (v_cod_oper(i) = 'TM' and v_ind_list_blan_prod(i) = '1' ) THEN
            existe := TRUE;
          ELSE
            IF ((v_val_prec_cata_devu_tota(i) - v_val_prec_cata_envi_tota(i)) <= 0) THEN
              existe := TRUE;
            ELSE
              IF (abs(v_val_prec_cata_devu_tota(i) - v_val_prec_cata_envi_tota(i)) <=
                 sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                       'STO_DESV_TRQ')) THEN
                existe := TRUE;
              ELSE
                existe := FALSE;
              END IF;
            END IF;
          END IF;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu IN
                   (SELECT sec_nume_docu
                      FROM sto_docum_digit
                     WHERE sec_nume_docu_cabe = v_sec_nume_docu(i));
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_DESVI_PRECI: ' || ls_sqlerrm);

  END sto_pr_spvd_desvi_preci;

  /***************************************************************************
  Descripcion       : VALIDACION CORPORATIVA PORCENTAJE UNIDADES POR FALTANTE
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_unida_falta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             ped.num_unid_aten_tota,
             det.cod_oper,
             det.ind_list_blan_prod
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             ped_solic_cabec             ped,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.oid_cabe = ped.oid_soli_cabe
         AND cab.cod_pais = det.cod_pais;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_num_unid_aten_tota IS TABLE OF ped_solic_cabec.num_unid_aten_tota%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_ind_list_blan_prod IS TABLE OF int_solic_conso_poven_detal.ind_list_blan_prod%TYPE;

    v_ind_list_blan_prod t_ind_list_blan_prod;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_tiprefe t_tiprefe;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_num_unid_aten_tota t_num_unid_aten_tota;
    v_cod_oper           t_cod_oper;

    existe     BOOLEAN := TRUE;
    monto_devu NUMBER := 0;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_num_unid_aten_tota,
             v_cod_oper,
             v_ind_list_blan_prod
             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe     := FALSE;
          monto_devu := 0;
          IF (v_cod_oper(i) != 'FM' AND v_cod_oper(i) != 'FA') then

            existe := TRUE;

          ELSE
             if (v_cod_oper(i) = 'FM' and v_ind_list_blan_prod(i) = '1') or
                (v_cod_oper(i) = 'FA' and v_ind_list_blan_prod(i) = '1')  THEN
                   existe := TRUE;
             else
            IF (v_num_unid_aten_tota(i) IS NULL OR v_num_unid_aten_tota(i) = 0) THEN
              existe := FALSE;
            ELSE
              monto_devu := sto_pkg_gener.sto_fn_devue_venta_devue_falta(v_codpais(i),
                                                                         v_codperi(i),
                                                                         v_codclie(i),
                                                                         v_numdocu(i),
                                                                         v_docu_cod_tipo_docu(i));

              IF (monto_devu / v_num_unid_aten_tota(i) * 100 <=
                 sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                       'STO_PUNI_FM')) THEN

                existe := TRUE;
              END IF;
            END IF;
          END IF;
          END IF;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_UNIDA_FALTA: ' || ls_sqlerrm);

  END sto_pr_spvd_unida_falta;

  /***************************************************************************
  Descripcion       : VALIDACION CORPORATIVA PORCENTAJE MONTO BRUTO POR FALTANTE
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_mobru_falta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             ped.val_tota_paga_loca,
             det.cod_oper,
             ped.val_nume_soli,
             det.ind_list_blan_prod
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             ped_solic_cabec             ped,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.oid_cabe = ped.oid_soli_cabe(+)
         AND cab.cod_pais = det.cod_pais;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_val_tota_paga_loca IS TABLE OF ped_solic_cabec.val_tota_paga_loca%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_ind_list_blan_prod IS TABLE OF int_solic_conso_poven_detal.ind_list_blan_prod%TYPE;

    TYPE t_val_nume_soli IS TABLE OF ped_solic_cabec.val_nume_soli%TYPE;

    v_ind_list_blan_prod t_ind_list_blan_prod;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_tiprefe t_tiprefe;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_val_tota_paga_loca t_val_tota_paga_loca;
    v_cod_oper           t_cod_oper;
    lnoidcliente        mae_clien.oid_clie%TYPE;
    lnporcentaje        NUMBER := 0;
    lv_exclfne          VARCHAR2(15);

    lnmontopedido       NUMBER;
    lnmontofaltante     NUMBER;
    lnmontodocgestion   NUMBER;
    lnmontototalgestion NUMBER;

    v_val_nume_soli t_val_nume_soli;

    existe     BOOLEAN := TRUE;
    monto_devu NUMBER := 0;
  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lv_exclfne:= sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                     'STO_IND_EXCLFNE');

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_val_tota_paga_loca,
             v_cod_oper,
             v_val_nume_soli,
             v_ind_list_blan_prod
             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe     := FALSE;
          monto_devu := 0;

          IF (v_cod_oper(i) != 'FM' AND v_cod_oper(i) != 'FA') THEN

            existe := TRUE;

          ELSE
             if (v_cod_oper(i) = 'FM' and v_ind_list_blan_prod(i) = '1') or
                (v_cod_oper(i) = 'FA' and v_ind_list_blan_prod(i) = '1')  THEN
                   existe := TRUE;
             else

                  IF (v_val_tota_paga_loca(i) IS NULL OR v_val_tota_paga_loca(i) = 0) THEN
                    existe := FALSE;

                  ELSE

            if lv_exclfne = '1' then
              ----- Valida si hay excepcion en la lista "blanca" de consultoras
              BEGIN
                lnoidcliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(v_codclie(i));
              EXCEPTION
                WHEN OTHERS THEN
                  lnoidcliente := '';
              END;
                      lnporcentaje := rec_pkg_proce.rec_fn_porce_monto_falta(lnoidcliente,
                                                                     v_codpais(i),
                                                                     v_codperi(i));

                      rec_pkg_proce.rec_pr_monto_evalu_falta(v_val_nume_soli(i),
                                                             lnmontopedido,
                                                             lnmontofaltante);

                      rec_pkg_proce.rec_pr_monto_gesti_falta(v_val_nume_soli(i),
                                                             lnmontototalgestion,
                                                             v_num_lote(i),
                                                             v_numdocu(i),
                                                             v_codclie(i),
                                                             v_codperi(i),
                                                             v_codpais(i),
                                                             lnmontodocgestion);

                      IF lnporcentaje = 100 THEN
               existe := TRUE;
            ELSE
                        IF ((lnmontototalgestion + abs(lnmontofaltante)) / lnmontopedido) * 100 <=
                           lnporcentaje THEN
                          existe := TRUE;
                        END IF;
                      END IF;


            ELSE


              monto_devu := sto_pkg_gener.sto_fn_devue_produ_canti_falta(v_codpais(i),
                                                                         v_codperi(i),
                                                                         v_codclie(i),
                                                                         v_numdocu(i),
                                                                         v_docu_cod_tipo_docu(i));

              IF (monto_devu / v_val_tota_paga_loca(i) * 100 <=
                 sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                       'STO_PMON_FM')) THEN

                existe := TRUE;
              END IF;
            END IF;

          END IF;

          END IF;
          END IF;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_PORCE_DEVOL: ' || ls_sqlerrm);

  END sto_pr_spvd_mobru_falta;

  /***************************************************************************
  Descripcion       : VALIDACION SOLO DEVOLUCION EN RECLAMO DE RECLAMO
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_devol_recla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND (det.cod_vent_dese IS NULL OR
             det.cod_oper NOT IN (SELECT cod_oper
                                     FROM rec_opera ro
                                    WHERE ro.ind_ingr_envi = 0
                                      AND ro.val_ingr_devu = 1
                                      AND ro.ind_devu_gene_envi = 0));

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_devol_recla: ' || ls_sqlerrm);

  END sto_pr_spvd_devol_recla;

  /***************************************************************************
  Descripcion       : VALIDACION MAV SOLO EN CANJE O FALTANTE
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_mavca_falta
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             ped_solic_posic             pos,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND det.oid_soli_posi_devu = pos.oid_soli_posi(+)
         AND ((pos.tpos_oid_tipo_posi IS NULL) OR
             (pos.tpos_oid_tipo_posi NOT IN (3,
                                              12,
                                              13)) OR
             (pos.tpos_oid_tipo_posi IN (3,
                                          12,
                                          13) AND
             det.cod_oper IN ('FM',
                                'CM')) OR cod_vent_devu IS NULL);

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote

             LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);
      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_MAVCA_FALTA: ' || ls_sqlerrm);

  END sto_pr_spvd_mavca_falta;

  /***************************************************************************
  Descripcion       : VALIDACION devuelve MAV solo en canje o faltante (SPVD-23)
  Fecha Creacion    : 11/03/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_mavca_falta
  (
    pscodigopais      in  VARCHAR2,
    psCodOperSICC     in  VARCHAR2,
    psTipoOperSICC    in  VARCHAR2,
    psOidSoliPosi     in  number,
    pscodigoVenta     in  VARCHAR2
  )  RETURN BOOLEAN  IS

    lsparametrovalSPVD44   sto_param_gener_occrr.val_param%TYPE;
    lsparametromotSPVD44   sto_param_gener_occrr.val_param%TYPE;

    lsoidtipoposi          ped_solic_posic.tpos_oid_tipo_posi%TYPE;

    existe BOOLEAN := TRUE;

    i BINARY_INTEGER := 0;
    contador BINARY_INTEGER := 0;

  BEGIN


    BEGIN

      select psp.tpos_oid_tipo_posi
        into lsoidtipoposi
        from ped_solic_posic psp,
             ped_solic_Cabec psc,
             ped_solic_cabec psc1,
             mae_clien       mae
       where PSP.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
         and PSC.SOCA_OID_SOLI_CABE = PSC1.OID_SOLI_CABE
         and psc1.clie_oid_clie = mae.oid_clie
         and PSP.OID_SOLI_POSI = psOidSoliPosi
         and rownum = 1;

    EXCEPTION
      WHEN no_data_found THEN
        lsoidtipoposi := null;
    END;

    if ((lsoidtipoposi IS NULL) OR
                 (lsoidtipoposi NOT IN (3,12,13)) OR
                 (lsoidtipoposi IN (3,12,13) AND
                 psCodOperSICC IN ('FM','CM')) OR pscodigoVenta IS NULL) then
        existe := true;
    else
        existe := false;
    end if;



  RETURN (existe);

  END sto_fn_spvd_mavca_falta;


  /***************************************************************************
  Descripcion       : VALIDACION ENVIAS SON DE PROMOCION
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_envia_promo
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND ((det.cod_oper IN ('ES',
                                'EA')) OR (det.cod_vent_dese IS NULL) OR
             ((det.cod_oper NOT IN ('ES',
                                     'EA')) AND
             det.cod_vent_dese NOT IN
             (
                /*SELECT val_codi_vent
                FROM pre_matri_factu_cabec a,
                  pre_ofert b,
                  pre_ofert_detal c,
                  pre_estra d,
                  pre_tipo_estra e
                    WHERE a.perd_oid_peri = cab.oid_peri_refe
                     AND a.oid_cabe = b.mfca_oid_cabe
                     AND b.oid_ofer = c.ofer_oid_ofer
                     AND b.coes_oid_estr = d.oid_estr
                     AND d.ties_oid_tipo_estr = e.oid_tipo_estr
                     AND e.ind_estr_vs = 1

                    UNION
                    */
                SELECT val_codi_vent
                  FROM pre_matri_factu_cabec a,
                        pre_ofert             b,
                        pre_ofert_detal       c,
                        pre_grupo_ofert       d
                 WHERE a.perd_oid_peri = cab.oid_peri_refe
                   AND a.oid_cabe = b.mfca_oid_cabe
                   AND b.oid_ofer = c.ofer_oid_ofer
                   AND b.coes_oid_estr IN
                       (SELECT oid_estr
                          FROM pre_estra
                         WHERE ties_oid_tipo_estr IN
                               (SELECT oid_tipo_estr FROM pre_tipo_estra WHERE cod_tipo_estr = 4))
                   AND c.gofe_oid_grup_ofer = d.oid_grup_ofer
                   AND d.ind_cndo = 1)));

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_ENVIA_PROMO: ' || ls_sqlerrm);

  END sto_pr_spvd_envia_promo;

  /***************************************************************************
  Descripcion       : VALIDACION DE DEVOLUCION DE OFERTA CON GRATIS
  Fecha Creacion    : 25/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_devol_ofert
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_oper,
             cab.oid_peri_refe,
             det.cod_vent_devu,
             cab.num_docu_cruc
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_num_docu_cruc IS TABLE OF int_solic_conso_poven_cabec.num_docu_cruc%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_tiprefe t_tiprefe;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_cod_oper           t_cod_oper;
    v_oid_peri_refe      t_oid_peri_refe;
    v_cod_vent_devu      t_cod_vent_devu;
    v_num_docu_cruc      t_num_docu_cruc;

    numero   NUMBER := 0;
    contador NUMBER := 0;
    existe   BOOLEAN := TRUE;

    ls_oid_soli_cabe ped_solic_cabec.oid_soli_cabe%TYPE;

    TYPE t_val_codi_vent IS TABLE OF pre_ofert_detal.val_codi_vent%TYPE;

    v_val_codi_vent t_val_codi_vent;

    -- ls_cod_vent_devu_grat pre_ofert_detal.val_codi_vent%TYPE;

    CURSOR c_val_codi_ven
    (
      vsoidsolicabe NUMBER,
      vscodventdevu VARCHAR2
    ) IS
      SELECT b.val_codi_vent

        FROM ped_solic_posic a,
             pre_ofert_detal b
       WHERE a.ofde_oid_deta_ofer = b.oid_deta_ofer
         AND a.soca_oid_soli_cabe = vsoidsolicabe
         AND b.imp_prec_cata = 0
         AND b.tofe_oid_tipo_ofer = 2040
         AND a.num_unid_aten > 0
         AND b.ofer_oid_ofer IN (SELECT b.ofer_oid_ofer
                                   FROM ped_solic_posic a,
                                        pre_ofert_detal b
                                  WHERE a.ofde_oid_deta_ofer = b.oid_deta_ofer
                                       --and a.SOCA_OID_SOLI_CABE=c.OID_SOLI_CABE
                                    AND a.soca_oid_soli_cabe = vsoidsolicabe
                                    AND a.val_codi_vent = vscodventdevu
                                    AND b.imp_prec_cata <> 0
                                    AND b.cod_orig <> 'MAV');

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,

             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_oper,
             v_oid_peri_refe,
             v_cod_vent_devu,
             v_num_docu_cruc

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          IF (v_cod_oper(i) = 'EA' OR v_cod_oper(i) = 'ES') THEN
            existe := TRUE;
          ELSE

            existe := FALSE;
            BEGIN
              numero := 0;

              SELECT COUNT(*)
                INTO numero
                FROM pre_matri_factu_cabec a,
                     pre_ofert             b,
                     pre_ofert_detal       c,
                     pre_estra             d,
                     pre_tipo_estra        e
               WHERE a.perd_oid_peri = v_oid_peri_refe(i)
                 AND a.oid_cabe = b.mfca_oid_cabe
                 AND b.oid_ofer = c.ofer_oid_ofer
                 AND b.coes_oid_estr = d.oid_estr
                 AND d.ties_oid_tipo_estr = e.oid_tipo_estr
                 AND c.ind_impr_gp = 1
                 AND val_codi_vent = v_cod_vent_devu(i)
                 AND c.ofer_oid_ofer IN
                     (SELECT ofer_oid_ofer FROM pre_ofert_detal WHERE tofe_oid_tipo_ofer = 2040);

              IF (numero > 0) THEN
                existe := FALSE;

                SELECT ped.oid_soli_cabe
                  INTO ls_oid_soli_cabe
                  FROM ped_solic_cabec ped,
                       ped_solic_cabec cons
                 WHERE ped.soca_oid_soli_cabe = cons.oid_soli_cabe
                   AND cons.val_nume_soli = v_num_docu_cruc(i)
                   AND ped.tspa_oid_tipo_soli_pais =
                       int_pkg_recla.gen_fn_devue_oid_tipo_solpa('SOC')
                   AND rownum = 1;

                IF (ls_oid_soli_cabe IS NOT NULL) THEN

                  BEGIN
                    existe := TRUE;
                    OPEN c_val_codi_ven(ls_oid_soli_cabe,
                                        v_cod_vent_devu(i));
                    LOOP
                      FETCH c_val_codi_ven BULK COLLECT
                        INTO v_val_codi_vent LIMIT w_filas;
                      IF v_val_codi_vent.count > 0 THEN
                        FOR j IN v_val_codi_vent.first .. v_val_codi_vent.last
                        LOOP
                          contador := 0;
                          IF (v_val_codi_vent(j) IS NOT NULL) THEN

                            SELECT COUNT(*)
                              INTO contador
                              FROM int_solic_conso_poven_cabec cab,
                                   int_solic_conso_poven_detal det
                             WHERE cab.cod_pais = det.cod_pais
                               AND cab.cod_peri = det.cod_peri
                               AND cab.cod_clie = det.cod_clie
                               AND cab.num_docu = det.num_docu
                               AND cab.cod_pais = pscodigopais
                               AND det.docu_cod_tipo_docu = v_docu_cod_tipo_docu(i)
                               AND cab.cod_pais = v_codpais(i)
                               AND cab.cod_peri = v_codperi(i)
                               AND cab.cod_clie = v_codclie(i)
                               AND cab.num_docu = v_numdocu(i)
                               AND det.cod_vent_devu = v_val_codi_vent(j);

                            IF (contador = 0) THEN
                              existe := existe AND FALSE;
                            ELSE
                              existe := existe AND TRUE;
                            END IF;

                          ELSE
                            existe := existe AND TRUE;
                          END IF;

                        END LOOP;
                      END IF;

                      EXIT WHEN c_val_codi_ven%NOTFOUND;

                    END LOOP;
                    CLOSE c_val_codi_ven;

                  END;
                ELSE
                  existe := TRUE;
                END IF;

              ELSE
                existe := TRUE;
              END IF;

            EXCEPTION
              WHEN no_data_found THEN
                existe := TRUE;

            END;
          END IF;
          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;
        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_DEVOL_OFERT: ' || ls_sqlerrm);

  END sto_pr_spvd_devol_ofert;

  /***************************************************************************
  Descripcion       : VALIDACION COMPLETA ENVIA DE TRUEQUES
  Fecha Creacion    : 26/09/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_envia_trueq
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_listaauxiliar
    (
      ps_oidperiodoreferencia NUMBER,
      ps_ofer_oid_ofer        NUMBER,
      ps_val_codi_vent        VARCHAR2
    ) IS
      SELECT DISTINCT val_codi_vent,
                      ofer_oid_ofer,
                      prod_oid_prod,
                      tofe_oid_tipo_ofer,
                      val_fact_repe,
                      oid_matr_fact
        FROM (SELECT val_codi_vent,
                     ofer_oid_ofer,
                     prod_oid_prod,
                     tofe_oid_tipo_ofer,
                     c.val_fact_repe,
                     (SELECT pre_matri_factu.oid_matr_fact
                        FROM pre_matri_factu
                       WHERE oid_deta_ofer = pre_matri_factu.ofde_oid_deta_ofer) oid_matr_fact
                FROM pre_matri_factu_cabec a,
                     pre_ofert             b,
                     pre_ofert_detal       c,
                     pre_estra             d,
                     pre_tipo_estra        e
               WHERE a.perd_oid_peri = ps_oidperiodoreferencia
                 AND a.oid_cabe = b.mfca_oid_cabe
                 AND b.oid_ofer = c.ofer_oid_ofer
                 AND b.coes_oid_estr = d.oid_estr
                 AND d.ties_oid_tipo_estr = e.oid_tipo_estr
                 AND e.cod_tipo_estr IN (2,
                                         6)
                    -- AND e.cod_tipo_estr in  (2,6) compuesta fija y compuesta fija vs grupo
                    --and c.IND_IMPR_GP=1
                    --and c.ind_prod_prin=0
                 AND c.ofer_oid_ofer = ps_ofer_oid_ofer
                 AND to_number(c.val_codi_vent) <> to_number(ps_val_codi_vent)

              UNION

              SELECT val_codi_vent,
                     ofer_oid_ofer,
                     prod_oid_prod,
                     tofe_oid_tipo_ofer,
                     c.val_fact_repe,
                     (SELECT pre_matri_factu.oid_matr_fact
                        FROM pre_matri_factu
                       WHERE oid_deta_ofer = pre_matri_factu.ofde_oid_deta_ofer) oid_matr_fact
                FROM pre_matri_factu_cabec a,
                     pre_ofert             b,
                     pre_ofert_detal       c,
                     pre_estra             d,
                     pre_tipo_estra        e
               WHERE a.perd_oid_peri = ps_oidperiodoreferencia
                 AND a.oid_cabe = b.mfca_oid_cabe
                 AND b.oid_ofer = c.ofer_oid_ofer
                 AND b.coes_oid_estr = d.oid_estr
                 AND d.ties_oid_tipo_estr = e.oid_tipo_estr
                 AND e.cod_tipo_estr NOT IN (2,
                                             6)
                    --AND e.cod_tipo_estr not in  (2,6)
                 AND to_number(c.val_codi_vent) <> to_number(ps_val_codi_vent)
                    --and c.IND_IMPR_GP=1
                    --and c.ind_prod_prin=0
                 AND c.tofe_oid_tipo_ofer = 2040 -- GRATIS
                 AND c.ofer_oid_ofer IN (SELECT ofer_oid_ofer
                                           FROM pre_ofert_detal,
                                                pre_ofert
                                          WHERE oid_ofer = ofer_oid_ofer
                                            AND mfca_oid_cabe = b.mfca_oid_cabe
                                            AND tofe_oid_tipo_ofer = 2040 --(2040,2008) gratis y en conjunto
                                         )
                 AND ofer_oid_ofer = ps_ofer_oid_ofer
                 AND rownum = 1);

    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             cab.oid_peri_refe,
             det.cod_vent_dese,
             det.cod_cia,
             det.can_prod_dese,
             det.cod_oper,
             det.cod_tipo_oper,
             det.oid_pais,
             det.oid_oper,
             det.oid_tipo_oper,
             det.ind_ingr_envi,
             det.ind_ingr_devu,
             det.ind_devu_fisi,
             det.ind_envi_gener_devu,
             det.ind_devu_gener_envi,
             det.ind_envi_fact,
             det.ind_devu_fact,
             det.cod_prec_envi,
             det.cod_prec,
             det.val_prec_cata_envi,
             det.val_prec_cont_envi,
             det.panp_oid_para_nive_prem_envi,
             det.lopa_oid_lote_prem_artic_envi,
             det.copa_oid_para_gene_envi,
             det.tofe_oid_envi,
             det.mafa_oid_envi,
             det.prod_oid_prod_envi,
             det.tspa_oid_tipo_soli_pais_envu,
             det.tspa_oid_tipo_soli_pais_devu,
             det.oid_soli_posi_envi,
             det.oid_soli_posi_devu,
             det.ind_cent_gara,
             det.cese_oid_cese_gara,
             det.num_dias_atra,
             det.esta_oid_esta_clie,
             det.val_revi_cheq,
             det.num_mes_gara,
             det.ind_gara_prem,
             det.ind_true_prem,
             det.sec_nume_docu,
             det.cod_zona,
             det.cod_regi,
             det.ind_envi_sto,
             det.des_obse,
             cab.oid_cabe
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_lote = cab.num_lote ---- SQA 20101102 se agrega nro lote
         AND det.num_docu = cab.num_docu;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;

    TYPE t_val_codi_vent IS TABLE OF pre_ofert_detal.val_codi_vent%TYPE;
    TYPE t_ofer_oid_ofer IS TABLE OF pre_ofert_detal.ofer_oid_ofer%TYPE;
    TYPE t_prod_oid_prod IS TABLE OF pre_ofert_detal.prod_oid_prod%TYPE;
    TYPE t_tofe_oid_tipo_ofer IS TABLE OF pre_ofert_detal.tofe_oid_tipo_ofer%TYPE;
    TYPE t_val_fact_repe IS TABLE OF pre_ofert_detal.val_fact_repe%TYPE;
    TYPE t_oid_matr_fact IS TABLE OF pre_matri_factu.oid_matr_fact%TYPE;

    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_cod_tipo_oper IS TABLE OF int_solic_conso_poven_detal.cod_tipo_oper%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_poven_detal.oid_pais%TYPE;
    TYPE t_oid_oper IS TABLE OF int_solic_conso_poven_detal.oid_oper%TYPE;
    TYPE t_oid_tipo_oper IS TABLE OF int_solic_conso_poven_detal.oid_tipo_oper%TYPE;
    TYPE t_ind_ingr_envi IS TABLE OF int_solic_conso_poven_detal.ind_ingr_envi %TYPE;
    TYPE t_ind_ingr_devu IS TABLE OF int_solic_conso_poven_detal.ind_ingr_devu%TYPE;
    TYPE t_ind_devu_fisi IS TABLE OF int_solic_conso_poven_detal.ind_devu_fisi%TYPE;
    TYPE t_ind_envi_gener_devu IS TABLE OF int_solic_conso_poven_detal.ind_envi_gener_devu%TYPE;
    TYPE t_ind_devu_gener_envi IS TABLE OF int_solic_conso_poven_detal.ind_devu_gener_envi%TYPE;
    TYPE t_ind_envi_fact IS TABLE OF int_solic_conso_poven_detal.ind_envi_fact%TYPE;
    TYPE t_ind_devu_fact IS TABLE OF int_solic_conso_poven_detal.ind_devu_fact%TYPE;
    TYPE t_cod_prec_envi IS TABLE OF int_solic_conso_poven_detal.cod_prec_envi%TYPE;
    TYPE t_cod_prec IS TABLE OF int_solic_conso_poven_detal.cod_prec%TYPE;
    TYPE t_val_prec_cata_envi IS TABLE OF int_solic_conso_poven_detal.val_prec_cata_envi%TYPE;
    TYPE t_val_prec_cont_envi IS TABLE OF int_solic_conso_poven_detal.val_prec_cont_envi%TYPE;
    TYPE t_panp_oid_nive_prem_envi IS TABLE OF int_solic_conso_poven_detal.panp_oid_para_nive_prem_envi%TYPE;
    TYPE t_lopa_oid_prem_artic_envi IS TABLE OF int_solic_conso_poven_detal.lopa_oid_lote_prem_artic_envi%TYPE;
    TYPE t_copa_oid_para_gene_envi IS TABLE OF int_solic_conso_poven_detal.copa_oid_para_gene_envi%TYPE;
    TYPE t_tofe_oid_envi IS TABLE OF int_solic_conso_poven_detal.tofe_oid_envi%TYPE;
    TYPE t_mafa_oid_envi IS TABLE OF int_solic_conso_poven_detal.mafa_oid_envi%TYPE;
    TYPE t_prod_oid_prod_envi IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_envi%TYPE;
    TYPE t_tspa_oid_soli_pais_envu IS TABLE OF int_solic_conso_poven_detal.tspa_oid_tipo_soli_pais_envu%TYPE;
    TYPE t_tspa_oid_soli_pais_devu IS TABLE OF int_solic_conso_poven_detal.tspa_oid_tipo_soli_pais_devu%TYPE;
    TYPE t_oid_soli_posi_envi IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_envi%TYPE;
    TYPE t_oid_soli_posi_devu IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
    TYPE t_ind_cent_gara IS TABLE OF int_solic_conso_poven_detal.ind_cent_gara%TYPE;
    TYPE t_cese_oid_cese_gara IS TABLE OF int_solic_conso_poven_detal.cese_oid_cese_gara%TYPE;
    TYPE t_num_dias_atra IS TABLE OF int_solic_conso_poven_detal.num_dias_atra%TYPE;
    TYPE t_esta_oid_esta_clie IS TABLE OF int_solic_conso_poven_detal.esta_oid_esta_clie%TYPE;
    TYPE t_val_revi_cheq IS TABLE OF int_solic_conso_poven_detal.val_revi_cheq%TYPE;
    TYPE t_num_mes_gara IS TABLE OF int_solic_conso_poven_detal.num_mes_gara%TYPE;
    TYPE t_ind_gara_prem IS TABLE OF int_solic_conso_poven_detal.ind_gara_prem%TYPE;
    TYPE t_ind_true_prem IS TABLE OF int_solic_conso_poven_detal.ind_true_prem%TYPE;
    TYPE t_cod_zona IS TABLE OF int_solic_conso_poven_detal.cod_zona%TYPE;
    TYPE t_cod_regi IS TABLE OF int_solic_conso_poven_detal.cod_regi%TYPE;
    TYPE t_ind_envi_sto IS TABLE OF int_solic_conso_poven_detal.ind_envi_sto%TYPE;
    TYPE t_des_obse IS TABLE OF int_solic_conso_poven_detal.des_obse%TYPE;
    TYPE t_cod_cia IS TABLE OF int_solic_conso_poven_detal.cod_cia%TYPE;
    TYPE t_can_prod_dese IS TABLE OF int_solic_conso_poven_detal.can_prod_dese%TYPE;
    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_tiprefe t_tiprefe;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_oid_peri_refe      t_oid_peri_refe;
    v_cod_vent_dese      t_cod_vent_dese;

    v_val_codi_vent      t_val_codi_vent;
    v_ofer_oid_ofer      t_ofer_oid_ofer;
    v_prod_oid_prod      t_prod_oid_prod;
    v_tofe_oid_tipo_ofer t_tofe_oid_tipo_ofer;
    v_val_fact_repe      t_val_fact_repe;
    v_oid_matr_fact      t_oid_matr_fact;

    v_cod_cia                  t_cod_cia;
    v_can_prod_dese            t_can_prod_dese;
    v_cod_oper                 t_cod_oper;
    v_cod_tipo_oper            t_cod_tipo_oper;
    v_oid_pais                 t_oid_pais;
    v_oid_oper                 t_oid_oper;
    v_oid_tipo_oper            t_oid_tipo_oper;
    v_ind_ingr_envi            t_ind_ingr_envi;
    v_ind_ingr_devu            t_ind_ingr_devu;
    v_ind_devu_fisi            t_ind_devu_fisi;
    v_ind_envi_gener_devu      t_ind_envi_gener_devu;
    v_ind_devu_gener_envi      t_ind_devu_gener_envi;
    v_ind_envi_fact            t_ind_envi_fact;
    v_ind_devu_fact            t_ind_devu_fact;
    v_cod_prec_envi            t_cod_prec_envi;
    v_cod_prec                 t_cod_prec;
    v_val_prec_cata_envi       t_val_prec_cata_envi;
    v_val_prec_cont_envi       t_val_prec_cont_envi;
    v_panp_oid_nive_prem_envi  t_panp_oid_nive_prem_envi;
    v_lopa_oid_prem_artic_envi t_lopa_oid_prem_artic_envi;
    v_copa_oid_para_gene_envi  t_copa_oid_para_gene_envi;
    v_tofe_oid_envi            t_tofe_oid_envi;
    v_mafa_oid_envi            t_mafa_oid_envi;
    v_prod_oid_prod_envi       t_prod_oid_prod_envi;
    v_tspa_oid_soli_pais_envu  t_tspa_oid_soli_pais_envu;
    v_tspa_oid_soli_pais_devu  t_tspa_oid_soli_pais_devu;
    v_oid_soli_posi_envi       t_oid_soli_posi_envi;
    v_oid_soli_posi_devu       t_oid_soli_posi_devu;
    v_ind_cent_gara            t_ind_cent_gara;
    v_cese_oid_cese_gara       t_cese_oid_cese_gara;
    v_num_dias_atra            t_num_dias_atra;
    v_esta_oid_esta_clie       t_esta_oid_esta_clie;
    v_val_revi_cheq            t_val_revi_cheq;
    v_num_mes_gara             t_num_mes_gara;
    v_ind_gara_prem            t_ind_gara_prem;
    v_ind_true_prem            t_ind_true_prem;
    v_cod_zona                 t_cod_zona;
    v_cod_regi                 t_cod_regi;
    v_ind_envi_sto             t_ind_envi_sto;
    v_des_obse                 t_des_obse;
    v_oid_cabe                 t_oid_cabe;

    numero     NUMBER := 0;
    numero1    NUMBER := 0;
    t_auxiliar NUMBER := 0;

    existe BOOLEAN := TRUE;

    lsoidofer             NUMBER;
    lsfactrep             NUMBER;
    ls_sec_nume_docu_cabe NUMBER;
    ls_seq_socu_digi      NUMBER;
    v_num_line            NUMBER;
    ls_num_proc           VARCHAR2(30);
    ls_usu_digi           VARCHAR2(20);
    ls_usu_modi           VARCHAR2(20);

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,

             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_oid_peri_refe,
             v_cod_vent_dese,
             v_cod_cia,
             v_can_prod_dese,
             v_cod_oper,
             v_cod_tipo_oper,
             v_oid_pais,
             v_oid_oper,
             v_oid_tipo_oper,
             v_ind_ingr_envi,
             v_ind_ingr_devu,
             v_ind_devu_fisi,
             v_ind_envi_gener_devu,
             v_ind_devu_gener_envi,
             v_ind_envi_fact,
             v_ind_devu_fact,
             v_cod_prec_envi,
             v_cod_prec,
             v_val_prec_cata_envi,
             v_val_prec_cont_envi,
             v_panp_oid_nive_prem_envi,
             v_lopa_oid_prem_artic_envi,
             v_copa_oid_para_gene_envi,
             v_tofe_oid_envi,
             v_mafa_oid_envi,
             v_prod_oid_prod_envi,
             v_oid_soli_posi_devu,
             v_tspa_oid_soli_pais_envu,
             v_tspa_oid_soli_pais_devu,
             v_oid_soli_posi_envi,
             v_ind_cent_gara,
             v_cese_oid_cese_gara,
             v_num_dias_atra,
             v_esta_oid_esta_clie,
             v_val_revi_cheq,
             v_num_mes_gara,
             v_ind_gara_prem,
             v_ind_true_prem,
             v_sec_nume_docu,
             v_cod_zona,
             v_cod_regi,
             v_ind_envi_sto,
             v_des_obse,
             v_oid_cabe

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          IF (v_cod_oper(i) <> 'TM') THEN
            existe := TRUE;
          ELSE

            IF (sto_pkg_gener.sto_fn_devue_indic_exist_coven(v_cod_vent_dese(i),
                                                             v_oid_peri_refe(i)) > 0) THEN

              existe     := FALSE;
              t_auxiliar := 0;
              lsoidofer  := sto_pkg_gener.sto_fn_devue_ofert_coven(v_cod_vent_dese(i),
                                                                   v_oid_peri_refe(i));
              lsfactrep  := sto_pkg_gener.sto_fn_devue_fact_repe(v_cod_vent_dese(i),
                                                                 v_oid_peri_refe(i));

              OPEN c_listaauxiliar(v_oid_peri_refe(i),
                                   lsoidofer,
                                   v_cod_vent_dese(i));
              LOOP
                FETCH c_listaauxiliar BULK COLLECT
                  INTO v_val_codi_vent,
                       v_ofer_oid_ofer,
                       v_prod_oid_prod,
                       v_tofe_oid_tipo_ofer,
                       v_val_fact_repe,

                       v_oid_matr_fact

                       LIMIT w_filas;
                IF (v_val_codi_vent.count > 0) THEN

                  FOR j IN v_val_codi_vent.first .. v_val_codi_vent.last

                  LOOP

                    BEGIN
                      SELECT COUNT(*)
                        INTO numero
                        FROM int_solic_conso_poven_detal det
                       WHERE v_codpais(i) = det.cod_pais
                         AND v_codperi(i) = det.cod_peri
                         AND v_codclie(i) = det.cod_clie
                         AND v_numdocu(i) = det.num_docu
                         AND to_number(v_val_codi_vent(j)) IN (to_number(det.cod_vent_dese));

                      SELECT COUNT(pos.oid_soli_posi)
                        INTO numero1
                        FROM ped_solic_cabec cons,
                             ped_solic_cabec ped,
                             ped_solic_posic pos
                       WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                         AND pos.soca_oid_soli_cabe = ped.oid_soli_cabe
                         AND cons.oid_soli_cabe = v_oid_cabe(i)
                         AND to_number(pos.val_codi_vent) = to_number(v_val_codi_vent(j));

                      IF ((numero + numero1) = 0) THEN

                        t_auxiliar := 1;
                        --Insert en el detalle

                        SELECT seq_docu_sto.nextval INTO ls_seq_socu_digi FROM dual;

                        SELECT MAX(num_line)
                          INTO v_num_line
                          FROM int_solic_conso_poven_detal
                         WHERE cod_pais = v_codpais(i)
                           AND cod_peri = v_codperi(i)
                           AND cod_clie = v_codclie(i)
                           AND num_lote = v_num_lote(i)
                           AND num_docu = v_numdocu(i);

                        v_num_line := v_num_line + 1;

                        INSERT INTO int_solic_conso_poven_detal
                          (cod_pais,
                           cod_peri,
                           cod_clie,
                           num_lote,
                           docu_cod_tipo_docu,
                           num_docu,
                           cod_cia,
                           tip_refe,
                           cod_vent_devu,
                           cod_vent_dese,
                           can_vent_devu,
                           can_prod_dese,
                           sta_proc,
                           cod_moti_rech,
                           mot_spv,
                           cod_oper,
                           cod_tipo_oper,
                           oid_pais,
                           oid_oper,
                           oid_tipo_oper,
                           ind_ingr_envi,
                           ind_ingr_devu,
                           ind_devu_fisi,
                           ind_envi_gener_devu,
                           ind_devu_gener_envi,
                           ind_envi_fact,
                           ind_devu_fact,
                           cod_prec_envi,
                           cod_prec,
                           val_prec_cata_envi,
                           val_prec_cont_envi,
                           val_prec_cata_devu,
                           val_prec_cont_devu,
                           panp_oid_para_nive_prem_envi,
                           lopa_oid_lote_prem_artic_envi,
                           copa_oid_para_gene_envi,
                           panp_oid_para_nive_prem_devu,
                           lopa_oid_lote_prem_artic_devu,
                           copa_oid_para_gene_devu,
                           tofe_oid_envi,
                           mafa_oid_envi,
                           prod_oid_prod_envi,
                           tofe_oid_devu,
                           mafa_oid_devu,
                           prod_oid_prod_devu,
                           tspa_oid_tipo_soli_pais_envu,
                           tspa_oid_tipo_soli_pais_devu,
                           oid_soli_posi_envi,
                           oid_soli_posi_devu,
                           ind_cent_gara,
                           cese_oid_cese_gara,
                           num_dias_atra,
                           esta_oid_esta_clie,
                           val_revi_cheq,
                           num_mes_gara,
                           ind_gara_prem,
                           ind_true_prem,
                           sec_nume_docu,
                           cod_zona,
                           cod_regi,
                           ind_envi_sto,
                           des_obse,
                           val_fact_repe,
                           num_line)
                        VALUES
                          (v_codpais(i),
                           v_codperi(i),
                           v_codclie(i),
                           v_num_lote(i),
                           v_docu_cod_tipo_docu(i),
                           v_numdocu(i),
                           v_cod_cia(i),
                           v_tiprefe(i),
                           NULL,
                           v_val_codi_vent(j),
                           NULL,
                           v_can_prod_dese(i),
                           NULL,
                           NULL,
                           NULL,
                           v_cod_oper(i),
                           v_cod_tipo_oper(i),
                           v_oid_pais(i),
                           v_oid_oper(i),
                           v_oid_tipo_oper(i),
                           v_ind_ingr_envi(i),
                           v_ind_ingr_devu(i),
                           v_ind_devu_fisi(i),
                           v_ind_envi_gener_devu(i),
                           v_ind_devu_gener_envi(i),
                           v_ind_envi_fact(i),
                           v_ind_devu_fact(i),
                           v_cod_prec_envi(i),
                           v_cod_prec(i),
                           NULL, --v_VAL_PREC_CATA_ENVI(i)            ,
                           NULL, --v_VAL_PREC_CONT_ENVI(i)      ,
                           NULL,
                           NULL,
                           NULL, --v_PANP_OID_NIVE_PREM_ENVI(i)   ,
                           NULL, --v_LOPA_OID_PREM_ARTIC_ENVI(i)  ,
                           NULL, --v_COPA_OID_PARA_GENE_ENVI(i)  ,
                           NULL,
                           NULL,
                           NULL,
                           NULL, --v_TOFE_OID_ENVI(i)                  ,
                           NULL, --v_MAFA_OID_ENVI(i)                  ,
                           NULL, --v_PROD_OID_PROD_ENVI(i) ,
                           NULL,
                           NULL,
                           NULL,
                           NULL, --v_TSPA_OID_SOLI_PAIS_ENVU(i)   ,
                           NULL, --v_TSPA_OID_SOLI_PAIS_DEVU(i)   ,
                           NULL, --v_OID_SOLI_POSI_ENVI(i)             ,
                           NULL, --v_OID_SOLI_POSI_DEVU(i)             ,
                           v_ind_cent_gara(i),
                           v_cese_oid_cese_gara(i),
                           v_num_dias_atra(i),
                           v_esta_oid_esta_clie(i),
                           v_val_revi_cheq(i),
                           v_num_mes_gara(i),
                           v_ind_gara_prem(i),
                           v_ind_true_prem(i),
                           ls_seq_socu_digi, --v_sec_nume_docu(i)                  ,
                           v_cod_zona(i),
                           v_cod_regi(i),
                           v_ind_envi_sto(i),
                           v_des_obse(i),
                           v_val_fact_repe(j),
                           v_num_line);

                        SELECT sec_nume_docu_cabe,
                               num_proc,
                               usu_digi,
                               usu_modi
                          INTO ls_sec_nume_docu_cabe,
                               ls_num_proc,
                               ls_usu_digi,
                               ls_usu_modi
                          FROM sto_docum_digit
                         WHERE sec_nume_docu = v_sec_nume_docu(i)
                           AND rownum = 1;

                        INSERT INTO sto_docum_digit
                          (cod_pais,
                           cod_tipo_docu,
                           num_lote,
                           sec_nume_docu,
                           num_docu,
                           cod_ulti_vali_ejec,
                           cod_ulti_vali_exit,
                           cod_ulti_vali_erro,
                           ind_envi,
                           ind_rech,
                           fec_digi,
                           usu_digi,
                           fec_modi,
                           usu_modi,
                           num_proc,
                           cod_zona,
                           cod_clie,
                           cod_regi,
                           sec_nume_docu_cabe)
                        VALUES
                          (v_codpais(i),
                           v_docu_cod_tipo_docu(i),
                           v_num_lote(i),
                           ls_seq_socu_digi,
                           v_numdocu(i),
                           pscodigovalidactual,
                           pscodigovalidactual,
                           NULL, --'SPVD-24',
                           '0',
                           '0',
                           SYSDATE,
                           'AUTO',
                           SYSDATE,
                           'AUTO',
                           ls_num_proc,
                           v_cod_zona(i),
                           v_codclie(i),
                           v_cod_regi(i),
                           ls_sec_nume_docu_cabe);

                      END IF;
                      existe := TRUE;
                    END;
                  END LOOP;

                  IF (t_auxiliar > 0) THEN

                    UPDATE int_solic_conso_poven_detal det
                       SET det.val_fact_repe = lsfactrep
                     WHERE v_codpais(i) = det.cod_pais
                       AND v_codperi(i) = det.cod_peri
                       AND v_codclie(i) = det.cod_clie
                       AND v_numdocu(i) = det.num_docu
                       AND v_sec_nume_docu(i) = det.sec_nume_docu;

                  END IF;

                END IF;

                EXIT WHEN c_listaauxiliar%NOTFOUND;

              END LOOP;
              CLOSE c_listaauxiliar;

            ELSE
              existe := TRUE;
            END IF;
          END IF;
          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;
        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_ENVIA_TRUEQ: ' || ls_sqlerrm);

  END sto_pr_spvd_envia_trueq;

  /***************************************************************************
  Descripcion       : VALIDACION MOTIVO POST VENTA
  Fecha Creacion    : 05/01/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_motiv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor(psparammotivo VARCHAR2) IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND (det.cod_oper <> 'DN' OR
             (det.cod_oper = 'DN' AND (det.mot_spv IS NULL OR det.mot_spv <> psparammotivo)));

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

    i BINARY_INTEGER := 0;

    lsparammotivo sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsparammotivo := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                          'STO_MSPV');

    OPEN c_cursor(lsparammotivo);
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_MOTIV: ' || ls_sqlerrm);

  END sto_pr_spvd_motiv;

  /***************************************************************************
  Descripcion       : VALIDACION DIFERENCIA PRECIOS TRUEQUES A NIVEL DETALLE

  Fecha Creacion    : 05/01/2009

  Parametros Entrada:
                      psCodigoPais          : Codigo de pais
                      psCodigoTipoDoc       : Codigo de tipo doc
                      psCodigoValidActual   : Codigo de Validacion Actual
                      psCodigoValidAnterior : Codigo de Validacion Anterior
                      psIndRequisito        : Indicador Requisito
                      psUsuario             : Codigo de Usuario

  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_difer_preci
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_devu,
             det.cod_vent_dese,
             det.cod_oper,
             cab.num_docu,
             cab.oid_peri_refe,
             det.can_vent_devu,
             det.can_prod_dese,
             det.mafa_oid_devu,
             det.mafa_oid_envi,
             det.tofe_oid_devu,
             det.tofe_oid_envi
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.cod_pais = det.cod_pais;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_num_docu IS TABLE OF int_solic_conso_poven_cabec.num_docu%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;

    TYPE t_can_vent_devu IS TABLE OF int_solic_conso_poven_detal.can_vent_devu%TYPE;
    TYPE t_can_prod_dese IS TABLE OF int_solic_conso_poven_detal.can_prod_dese%TYPE;
    TYPE t_mafa_oid_devu IS TABLE OF int_solic_conso_poven_detal.mafa_oid_devu%TYPE;
    TYPE t_mafa_oid_envi IS TABLE OF int_solic_conso_poven_detal.mafa_oid_envi%TYPE;
    TYPE t_tofe_oid_devu IS TABLE OF int_solic_conso_poven_detal.tofe_oid_devu%TYPE;
    TYPE t_tofe_oid_envi IS TABLE OF int_solic_conso_poven_detal.tofe_oid_envi%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numdocu            t_numdocu;
    v_tiprefe            t_tiprefe;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_cod_vent_devu      t_cod_vent_devu;
    v_cod_vent_dese      t_cod_vent_dese;
    v_cod_oper           t_cod_oper;
    v_num_docu           t_num_docu;
    v_oid_peri_refe      t_oid_peri_refe;
    v_can_vent_devu      t_can_vent_devu;
    v_can_prod_dese      t_can_prod_dese;
    v_mafa_oid_devu      t_mafa_oid_devu;
    v_mafa_oid_envi      t_mafa_oid_envi;
    v_tofe_oid_devu      t_tofe_oid_devu;
    v_tofe_oid_envi      t_tofe_oid_envi;

    existe BOOLEAN := TRUE;

    ls_val_prec_cata_devu pre_ofert_detal.precio_unitario%TYPE;
    ls_val_prec_cata_envi pre_ofert_detal.precio_unitario%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_devu,
             v_cod_vent_dese,
             v_cod_oper,
             v_num_docu,
             v_oid_peri_refe,
             v_can_vent_devu,
             v_can_prod_dese,
             v_mafa_oid_devu,
             v_mafa_oid_envi,
             v_tofe_oid_devu,
             v_tofe_oid_envi

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        --existe := FALSE;

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe := FALSE;
          IF (v_cod_oper(i) != 'TM') THEN
            existe := TRUE;
          ELSE
            IF (v_mafa_oid_envi(i) IS NOT NULL AND v_mafa_oid_devu(i) IS NOT NULL AND
               v_cod_vent_devu(i) IS NOT NULL AND v_cod_vent_dese(i) IS NOT NULL AND
               v_tofe_oid_devu(i) IS NOT NULL AND v_tofe_oid_envi(i) IS NOT NULL) THEN

              BEGIN

                SELECT d.precio_unitario
                  INTO ls_val_prec_cata_devu
                  FROM pre_ofert_detal       d,
                       pre_ofert             o,
                       pre_matri_factu_cabec c
                 WHERE to_number(d.val_codi_vent) = to_number(v_cod_vent_devu(i))
                   AND d.ofer_oid_ofer = o.oid_ofer
                   AND o.mfca_oid_cabe = c.oid_cabe
                   AND c.perd_oid_peri = v_oid_peri_refe(i);

                SELECT d.precio_unitario
                  INTO ls_val_prec_cata_envi
                  FROM pre_ofert_detal       d,
                       pre_ofert             o,
                       pre_matri_factu_cabec c
                 WHERE to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i))
                   AND d.ofer_oid_ofer = o.oid_ofer
                   AND o.mfca_oid_cabe = c.oid_cabe
                   AND c.perd_oid_peri = v_oid_peri_refe(i);

                IF (ls_val_prec_cata_envi * v_can_prod_dese(i) -
                   ls_val_prec_cata_devu * v_can_vent_devu(i) >= 0) THEN
                  existe := TRUE;
                ELSE
                  IF (abs(ls_val_prec_cata_devu * v_can_vent_devu(i) -
                          ls_val_prec_cata_envi * v_can_prod_dese(i)) <=
                     to_number(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                     'STO_DIF_TRQ'))) THEN
                    existe := TRUE;
                  ELSE
                    existe := FALSE;
                  END IF;
                END IF;

              EXCEPTION
                WHEN OTHERS THEN
                  existe := FALSE;
              END;

            ELSE
              existe := TRUE;
            END IF;
          END IF;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_DIFER_PRECI: ' || ls_sqlerrm);

  END sto_pr_spvd_difer_preci;

  /***************************************************************************
  Descripcion       : VALIDACION COMPLETAR DEVUELVES
  Fecha Creacion    : 06/01/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_compl_devue
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_listaauxiliar
    (
      ps_det_soca_oid_soli_cabe NUMBER,
      ps_det_ofer_oid_ofer      NUMBER,
      ps_oid_soli_posi_devu     NUMBER
    ) IS
      SELECT d.val_codi_vent,
             d.oid_soli_posi,
             d.val_prec_fact_unit_loca,
             d.num_unid_aten
        FROM ped_solic_posic d,
             pre_ofert_detal p
       WHERE d.soca_oid_soli_cabe = ps_det_soca_oid_soli_cabe
         AND d.ofde_oid_deta_ofer = p.oid_deta_ofer
         AND p.ofer_oid_ofer = ps_det_ofer_oid_ofer
         AND d.oid_soli_posi <> ps_oid_soli_posi_devu
         AND d.num_unid_aten > 0;

    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_devu,
             cab.oid_peri_refe,
             det.cod_vent_dese,
             det.cod_cia,
             det.can_vent_devu,
             det.cod_oper,
             det.cod_tipo_oper,
             det.oid_pais,
             det.oid_oper,
             det.oid_tipo_oper,
             det.ind_ingr_envi,
             det.ind_ingr_devu,
             det.ind_devu_fisi,
             det.ind_envi_gener_devu,
             det.ind_devu_gener_envi,
             det.ind_envi_fact,
             det.ind_devu_fact,
             det.cod_prec_envi,
             det.cod_prec,
             det.val_prec_cata_devu,
             det.val_prec_cont_devu,
             det.panp_oid_para_nive_prem_devu,
             det.lopa_oid_lote_prem_artic_devu,
             det.copa_oid_para_gene_devu,
             det.tofe_oid_devu,
             det.mafa_oid_devu,
             det.prod_oid_prod_devu,
             det.tspa_oid_tipo_soli_pais_envu,
             det.tspa_oid_tipo_soli_pais_devu,
             det.oid_soli_posi_envi,
             det.oid_soli_posi_devu,
             det.ind_cent_gara,
             det.cese_oid_cese_gara,
             det.num_dias_atra,
             det.esta_oid_esta_clie,
             det.val_revi_cheq,
             det.num_mes_gara,
             det.ind_gara_prem,
             det.ind_true_prem,
             det.sec_nume_docu,
             det.cod_zona,
             det.cod_regi,
             det.ind_envi_sto,
             det.des_obse,
             cab.oid_cabe,
             det.mot_spv
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND det.num_lote = cab.num_lote ---- SQA 20101102 se agrega nro lote
         AND cab.cod_pais = det.cod_pais;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;

    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_cod_tipo_oper IS TABLE OF int_solic_conso_poven_detal.cod_tipo_oper%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_poven_detal.oid_pais%TYPE;
    TYPE t_oid_oper IS TABLE OF int_solic_conso_poven_detal.oid_oper%TYPE;
    TYPE t_oid_tipo_oper IS TABLE OF int_solic_conso_poven_detal.oid_tipo_oper%TYPE;
    TYPE t_ind_ingr_envi IS TABLE OF int_solic_conso_poven_detal.ind_ingr_envi %TYPE;
    TYPE t_ind_ingr_devu IS TABLE OF int_solic_conso_poven_detal.ind_ingr_devu%TYPE;
    TYPE t_ind_devu_fisi IS TABLE OF int_solic_conso_poven_detal.ind_devu_fisi%TYPE;
    TYPE t_ind_envi_gener_devu IS TABLE OF int_solic_conso_poven_detal.ind_envi_gener_devu%TYPE;
    TYPE t_ind_devu_gener_envi IS TABLE OF int_solic_conso_poven_detal.ind_devu_gener_envi%TYPE;
    TYPE t_ind_envi_fact IS TABLE OF int_solic_conso_poven_detal.ind_envi_fact%TYPE;
    TYPE t_ind_devu_fact IS TABLE OF int_solic_conso_poven_detal.ind_devu_fact%TYPE;
    TYPE t_cod_prec_envi IS TABLE OF int_solic_conso_poven_detal.cod_prec_envi%TYPE;
    TYPE t_cod_prec IS TABLE OF int_solic_conso_poven_detal.cod_prec%TYPE;
    TYPE t_val_prec_cata_devu IS TABLE OF int_solic_conso_poven_detal.val_prec_cata_devu%TYPE;
    TYPE t_val_prec_cont_devu IS TABLE OF int_solic_conso_poven_detal.val_prec_cont_devu%TYPE;
    TYPE t_panp_oid_nive_prem_devu IS TABLE OF int_solic_conso_poven_detal.panp_oid_para_nive_prem_devu%TYPE;
    TYPE t_lopa_oid_prem_artic_devu IS TABLE OF int_solic_conso_poven_detal.lopa_oid_lote_prem_artic_devu%TYPE;
    TYPE t_copa_oid_para_gene_devu IS TABLE OF int_solic_conso_poven_detal.copa_oid_para_gene_devu%TYPE;
    TYPE t_tofe_oid_devu IS TABLE OF int_solic_conso_poven_detal.tofe_oid_devu%TYPE;
    TYPE t_mafa_oid_devu IS TABLE OF int_solic_conso_poven_detal.mafa_oid_devu%TYPE;
    TYPE t_prod_oid_prod_devu IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_devu%TYPE;
    TYPE t_tspa_oid_soli_pais_envu IS TABLE OF int_solic_conso_poven_detal.tspa_oid_tipo_soli_pais_envu%TYPE;
    TYPE t_tspa_oid_soli_pais_devu IS TABLE OF int_solic_conso_poven_detal.tspa_oid_tipo_soli_pais_devu%TYPE;
    TYPE t_oid_soli_posi_envi IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_envi%TYPE;
    TYPE t_oid_soli_posi_devu IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
    TYPE t_ind_cent_gara IS TABLE OF int_solic_conso_poven_detal.ind_cent_gara%TYPE;
    TYPE t_cese_oid_cese_gara IS TABLE OF int_solic_conso_poven_detal.cese_oid_cese_gara%TYPE;
    TYPE t_num_dias_atra IS TABLE OF int_solic_conso_poven_detal.num_dias_atra%TYPE;
    TYPE t_esta_oid_esta_clie IS TABLE OF int_solic_conso_poven_detal.esta_oid_esta_clie%TYPE;
    TYPE t_val_revi_cheq IS TABLE OF int_solic_conso_poven_detal.val_revi_cheq%TYPE;
    TYPE t_num_mes_gara IS TABLE OF int_solic_conso_poven_detal.num_mes_gara%TYPE;
    TYPE t_ind_gara_prem IS TABLE OF int_solic_conso_poven_detal.ind_gara_prem%TYPE;
    TYPE t_ind_true_prem IS TABLE OF int_solic_conso_poven_detal.ind_true_prem%TYPE;
    TYPE t_cod_zona IS TABLE OF int_solic_conso_poven_detal.cod_zona%TYPE;
    TYPE t_cod_regi IS TABLE OF int_solic_conso_poven_detal.cod_regi%TYPE;
    TYPE t_ind_envi_sto IS TABLE OF int_solic_conso_poven_detal.ind_envi_sto%TYPE;
    TYPE t_des_obse IS TABLE OF int_solic_conso_poven_detal.des_obse%TYPE;
    TYPE t_cod_cia IS TABLE OF int_solic_conso_poven_detal.cod_cia%TYPE;
    TYPE t_can_vent_devu IS TABLE OF int_solic_conso_poven_detal.can_vent_devu%TYPE;
    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_mot_spv IS TABLE OF int_solic_conso_poven_detal.mot_spv%TYPE;

    TYPE t_val_codi_vent_aux IS TABLE OF ped_solic_posic.val_codi_vent %TYPE;
    TYPE t_oid_soli_posi IS TABLE OF ped_solic_posic.oid_soli_posi %TYPE;
    TYPE t_val_prec_fact_unit_loca IS TABLE OF ped_solic_posic.val_prec_fact_unit_loca %TYPE;
    TYPE t_num_unid_aten IS TABLE OF ped_solic_posic.num_unid_aten %TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_tiprefe t_tiprefe;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_cod_vent_devu      t_cod_vent_devu;
    v_oid_peri_refe      t_oid_peri_refe;
    v_cod_vent_dese      t_cod_vent_dese;

    v_cod_cia                  t_cod_cia;
    v_can_vent_devu            t_can_vent_devu;
    v_cod_oper                 t_cod_oper;
    v_cod_tipo_oper            t_cod_tipo_oper;
    v_oid_pais                 t_oid_pais;
    v_oid_oper                 t_oid_oper;
    v_oid_tipo_oper            t_oid_tipo_oper;
    v_ind_ingr_envi            t_ind_ingr_envi;
    v_ind_ingr_devu            t_ind_ingr_devu;
    v_ind_devu_fisi            t_ind_devu_fisi;
    v_ind_envi_gener_devu      t_ind_envi_gener_devu;
    v_ind_devu_gener_envi      t_ind_devu_gener_envi;
    v_ind_envi_fact            t_ind_envi_fact;
    v_ind_devu_fact            t_ind_devu_fact;
    v_cod_prec_envi            t_cod_prec_envi;
    v_cod_prec                 t_cod_prec;
    v_val_prec_cata_devu       t_val_prec_cata_devu;
    v_val_prec_cont_devu       t_val_prec_cont_devu;
    v_panp_oid_nive_prem_devu  t_panp_oid_nive_prem_devu;
    v_lopa_oid_prem_artic_devu t_lopa_oid_prem_artic_devu;
    v_copa_oid_para_gene_devu  t_copa_oid_para_gene_devu;
    v_tofe_oid_devu            t_tofe_oid_devu;
    v_mafa_oid_devu            t_mafa_oid_devu;
    v_prod_oid_prod_devu       t_prod_oid_prod_devu;
    v_tspa_oid_soli_pais_envu  t_tspa_oid_soli_pais_envu;
    v_tspa_oid_soli_pais_devu  t_tspa_oid_soli_pais_devu;
    v_oid_soli_posi_envi       t_oid_soli_posi_envi;
    v_oid_soli_posi_devu       t_oid_soli_posi_devu;
    v_ind_cent_gara            t_ind_cent_gara;
    v_cese_oid_cese_gara       t_cese_oid_cese_gara;
    v_num_dias_atra            t_num_dias_atra;
    v_esta_oid_esta_clie       t_esta_oid_esta_clie;
    v_val_revi_cheq            t_val_revi_cheq;
    v_num_mes_gara             t_num_mes_gara;
    v_ind_gara_prem            t_ind_gara_prem;
    v_ind_true_prem            t_ind_true_prem;
    v_cod_zona                 t_cod_zona;
    v_cod_regi                 t_cod_regi;
    v_ind_envi_sto             t_ind_envi_sto;
    v_des_obse                 t_des_obse;
    v_oid_cabe                 t_oid_cabe;
    v_mot_spv                  t_mot_spv;

    v_val_codi_vent_aux       t_val_codi_vent_aux;
    v_oid_soli_posi           t_oid_soli_posi;
    v_val_prec_fact_unit_loca t_val_prec_fact_unit_loca;
    v_num_unid_aten           t_num_unid_aten;

    numero NUMBER := 0;

    existe BOOLEAN := TRUE;

    ls_sec_nume_docu_cabe NUMBER;
    ls_seq_socu_digi      NUMBER;
    ls_num_proc           VARCHAR2(30);
    ls_usu_digi           VARCHAR2(20);
    ls_usu_modi           VARCHAR2(20);

    ls_det_ofer_oid_ofer      pre_ofert_detal.ofer_oid_ofer%TYPE;
    ls_det_soca_oid_soli_cabe ped_solic_posic.soca_oid_soli_cabe%TYPE;

    cuentaoefertas NUMBER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_devu,
             v_oid_peri_refe,
             v_cod_vent_dese,
             v_cod_cia,
             v_can_vent_devu,
             v_cod_oper,
             v_cod_tipo_oper,
             v_oid_pais,
             v_oid_oper,
             v_oid_tipo_oper,
             v_ind_ingr_envi,
             v_ind_ingr_devu,
             v_ind_devu_fisi,
             v_ind_envi_gener_devu,
             v_ind_devu_gener_envi,
             v_ind_envi_fact,
             v_ind_devu_fact,
             v_cod_prec_envi,
             v_cod_prec,
             v_val_prec_cata_devu,
             v_val_prec_cont_devu,
             v_panp_oid_nive_prem_devu,
             v_lopa_oid_prem_artic_devu,
             v_copa_oid_para_gene_devu,
             v_tofe_oid_devu,
             v_mafa_oid_devu,
             v_prod_oid_prod_devu,
             v_oid_soli_posi_devu,
             v_tspa_oid_soli_pais_envu,
             v_tspa_oid_soli_pais_devu,
             v_oid_soli_posi_envi,
             v_ind_cent_gara,
             v_cese_oid_cese_gara,
             v_num_dias_atra,
             v_esta_oid_esta_clie,
             v_val_revi_cheq,
             v_num_mes_gara,
             v_ind_gara_prem,
             v_ind_true_prem,
             v_sec_nume_docu,
             v_cod_zona,
             v_cod_regi,
             v_ind_envi_sto,
             v_des_obse,
             v_oid_cabe,
             v_mot_spv LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe := FALSE;
          IF (v_cod_oper(i) <> 'DN') THEN
            existe := TRUE;
          ELSE

            IF (v_oid_soli_posi_devu(i) IS NOT NULL) THEN

              SELECT p.ofer_oid_ofer,
                     d.soca_oid_soli_cabe
                INTO ls_det_ofer_oid_ofer,
                     ls_det_soca_oid_soli_cabe
                FROM ped_solic_posic d,
                     pre_ofert_detal p
               WHERE d.oid_soli_posi = v_oid_soli_posi_devu(i)
                 AND d.ofde_oid_deta_ofer = p.oid_deta_ofer
                 AND p.ind_impr_gp = 1;

              IF (ls_det_ofer_oid_ofer IS NULL AND ls_det_soca_oid_soli_cabe IS NULL) THEN
                existe := TRUE;
              END IF;

              SELECT COUNT(oid_ofer)
                INTO cuentaoefertas
                FROM pre_ofert      b,
                     pre_estra      d,
                     pre_tipo_estra e
               WHERE b.coes_oid_estr = d.oid_estr
                 AND d.ties_oid_tipo_estr = e.oid_tipo_estr
                 AND e.cod_tipo_estr IN (2,
                                         6)
                 AND b.oid_ofer = ls_det_ofer_oid_ofer;

              IF (cuentaoefertas > 0) THEN

                OPEN c_listaauxiliar(ls_det_soca_oid_soli_cabe,
                                     ls_det_ofer_oid_ofer,
                                     v_oid_soli_posi_devu(i));
                LOOP
                  FETCH c_listaauxiliar BULK COLLECT
                    INTO v_val_codi_vent_aux,
                         v_oid_soli_posi,
                         v_val_prec_fact_unit_loca,
                         v_num_unid_aten

                         LIMIT w_filas;
                  IF (v_val_codi_vent_aux.count > 0) THEN

                    FOR j IN v_val_codi_vent_aux.first .. v_val_codi_vent_aux.last

                    LOOP
                      numero := 0;
                      BEGIN
                        SELECT COUNT(*)
                          INTO numero
                          FROM int_solic_conso_poven_detal det
                         WHERE v_codpais(i) = det.cod_pais
                           AND v_codperi(i) = det.cod_peri
                           AND v_codclie(i) = det.cod_clie
                           AND v_numdocu(i) = det.num_docu
                           AND v_num_lote(i) = det.num_lote
                           AND det.cod_vent_devu = v_val_codi_vent_aux(j);

                        IF (numero = 0) THEN

                          --Insert en el detalle
                          SELECT seq_docu_sto.nextval INTO ls_seq_socu_digi FROM dual;

                          INSERT INTO int_solic_conso_poven_detal
                            (cod_pais,
                             cod_peri,
                             cod_clie,
                             num_lote,
                             docu_cod_tipo_docu,
                             num_docu,
                             cod_cia,
                             tip_refe,
                             cod_vent_devu,
                             cod_vent_dese,
                             can_vent_devu,
                             can_prod_dese,
                             sta_proc,
                             cod_moti_rech,
                             mot_spv,
                             cod_oper,
                             cod_tipo_oper,
                             oid_pais,
                             oid_oper,
                             oid_tipo_oper,
                             ind_ingr_envi,
                             ind_ingr_devu,
                             ind_devu_fisi,
                             ind_envi_gener_devu,
                             ind_devu_gener_envi,
                             ind_envi_fact,
                             ind_devu_fact,
                             cod_prec_envi,
                             cod_prec,
                             val_prec_cata_envi,
                             val_prec_cont_envi,
                             val_prec_cata_devu,
                             val_prec_cont_devu,
                             panp_oid_para_nive_prem_envi,
                             lopa_oid_lote_prem_artic_envi,
                             copa_oid_para_gene_envi,
                             panp_oid_para_nive_prem_devu,
                             lopa_oid_lote_prem_artic_devu,
                             copa_oid_para_gene_devu,
                             tofe_oid_envi,
                             mafa_oid_envi,
                             prod_oid_prod_envi,
                             tofe_oid_devu,
                             mafa_oid_devu,
                             prod_oid_prod_devu,
                             tspa_oid_tipo_soli_pais_envu,
                             tspa_oid_tipo_soli_pais_devu,
                             oid_soli_posi_envi,
                             oid_soli_posi_devu,
                             ind_cent_gara,
                             cese_oid_cese_gara,
                             num_dias_atra,
                             esta_oid_esta_clie,
                             val_revi_cheq,
                             num_mes_gara,
                             ind_gara_prem,
                             ind_true_prem,
                             sec_nume_docu,
                             cod_zona,
                             cod_regi,
                             ind_envi_sto,
                             des_obse

                             )
                          VALUES
                            (v_codpais(i),
                             v_codperi(i),
                             v_codclie(i),
                             v_num_lote(i),
                             v_docu_cod_tipo_docu(i),
                             v_numdocu(i),
                             v_cod_cia(i),
                             v_tiprefe(i),
                             v_val_codi_vent_aux(j),
                             NULL,
                             v_can_vent_devu(i),
                             NULL,
                             NULL,
                             v_mot_spv(i),
                             NULL,
                             v_cod_oper(i),
                             v_cod_tipo_oper(i),
                             v_oid_pais(i),
                             v_oid_oper(i),
                             v_oid_tipo_oper(i),
                             v_ind_ingr_envi(i),
                             v_ind_ingr_devu(i),
                             v_ind_devu_fisi(i),
                             v_ind_envi_gener_devu(i),
                             v_ind_devu_gener_envi(i),
                             v_ind_envi_fact(i),
                             v_ind_devu_fact(i),
                             v_cod_prec_envi(i),
                             v_cod_prec(i),
                             NULL, --v_VAL_PREC_CATA_ENVI(i)            ,
                             NULL, --v_VAL_PREC_CONT_ENVI(i)      ,
                             NULL,
                             NULL,
                             NULL, --v_PANP_OID_NIVE_PREM_ENVI(i)   ,
                             NULL, --v_LOPA_OID_PREM_ARTIC_ENVI(i)  ,
                             NULL, --v_COPA_OID_PARA_GENE_ENVI(i)  ,
                             NULL,
                             NULL,
                             NULL,
                             NULL, --v_TOFE_OID_ENVI(i)                  ,
                             NULL, --v_MAFA_OID_ENVI(i)                  ,
                             NULL, --v_PROD_OID_PROD_ENVI(i) ,
                             NULL,
                             NULL,
                             NULL,
                             NULL, --v_TSPA_OID_SOLI_PAIS_ENVU(i)   ,
                             NULL, --v_TSPA_OID_SOLI_PAIS_DEVU(i)   ,
                             NULL, --v_OID_SOLI_POSI_ENVI(i)             ,
                             NULL, --v_OID_SOLI_POSI_DEVU(i)             ,
                             v_ind_cent_gara(i),
                             v_cese_oid_cese_gara(i),
                             v_num_dias_atra(i),
                             v_esta_oid_esta_clie(i),
                             v_val_revi_cheq(i),
                             v_num_mes_gara(i),
                             v_ind_gara_prem(i),
                             v_ind_true_prem(i),
                             ls_seq_socu_digi, --v_sec_nume_docu(i)                  ,
                             v_cod_zona(i),
                             v_cod_regi(i),
                             v_ind_envi_sto(i),
                             v_des_obse(i));

                          SELECT sec_nume_docu_cabe,
                                 num_proc,
                                 usu_digi,
                                 usu_modi
                            INTO ls_sec_nume_docu_cabe,
                                 ls_num_proc,
                                 ls_usu_digi,
                                 ls_usu_modi
                            FROM sto_docum_digit
                           WHERE sec_nume_docu = v_sec_nume_docu(i)
                             AND rownum = 1;

                          INSERT INTO sto_docum_digit
                            (cod_pais,
                             cod_tipo_docu,
                             num_lote,
                             sec_nume_docu,
                             num_docu,
                             cod_ulti_vali_ejec,
                             cod_ulti_vali_exit,
                             cod_ulti_vali_erro,
                             ind_envi,
                             ind_rech,
                             fec_digi,
                             usu_digi,
                             fec_modi,
                             usu_modi,
                             num_proc,
                             cod_zona,
                             cod_clie,
                             cod_regi,
                             sec_nume_docu_cabe)
                          VALUES
                            (v_codpais(i),
                             v_docu_cod_tipo_docu(i),
                             v_num_lote(i),
                             ls_seq_socu_digi,
                             v_numdocu(i),
                             pscodigovalidactual,
                             pscodigovalidactual,
                             NULL, --'SPVD-24',
                             '0',
                             '0',
                             SYSDATE,
                             'AUTO',
                             SYSDATE,
                             'AUTO',
                             ls_num_proc,
                             v_cod_zona(i),
                             v_codclie(i),
                             v_cod_regi(i),
                             ls_sec_nume_docu_cabe);

                        END IF;
                        existe := TRUE;
                      END;

                    END LOOP;

                  END IF;

                  EXIT WHEN c_listaauxiliar%NOTFOUND;

                END LOOP;
                CLOSE c_listaauxiliar;
              END IF;

            ELSE
              existe := TRUE;
            END IF;
          END IF;
          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;
        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_COMPL_DEVUE: ' || ls_sqlerrm);

  END sto_pr_spvd_compl_devue;

  /***************************************************************************
  Descripcion       : Validacion de la operacion
  Fecha Creacion    : 16/04/2009
  Autor             : Cristhian Roman
                      Este SP no sirve solo se usa para pruebas
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_opera_accio
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.cod_vent_devu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
           det.ind_envi_fact,
           det.cod_vent_dese,
           det.cod_prec_envi,
           det.cod_prec,
             det.oid_oper,
           det.ind_devu_fact,
           cab.num_docu_cruc,
           cab.oid_peri_refe,
           det.ind_devu_fisi,
           cab.oid_cabe,
           det.can_vent_devu,
           det.ind_nume_vece_pedi,
           det.oid_soli_posi_devu,
           det.copa_oid_para_gene_devu,
           det.can_prod_dese
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
       AND det.cod_peri = cab.cod_peri
       AND det.cod_clie = cab.cod_clie
       AND det.num_docu = cab.num_docu
       AND det.num_lote = cab.num_lote ---- SQA 20101102 se agrega nro lote
       AND ((det.cod_vent_dese IS NOT NULL) OR
           (det.cod_vent_devu IS NOT NULL));

  TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
  TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
  TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
  TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
  TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

  TYPE t_ind_envi_fact IS TABLE OF int_solic_conso_poven_detal.ind_envi_fact%TYPE;
  TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;
  TYPE t_cod_prec_envi IS TABLE OF int_solic_conso_poven_detal.cod_prec_envi%TYPE;
  TYPE t_cod_prec IS TABLE OF int_solic_conso_poven_detal.cod_prec%TYPE;
    TYPE t_oid_oper IS TABLE OF int_solic_conso_poven_detal.oid_oper%TYPE;
  TYPE t_ind_devu_fact IS TABLE OF int_solic_conso_poven_detal.ind_devu_fact%TYPE;
  TYPE t_num_docu_cruc IS TABLE OF int_solic_conso_poven_cabec.num_docu_cruc%TYPE;
  TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
  TYPE t_ind_devu_fisi IS TABLE OF int_solic_conso_poven_detal.ind_devu_fisi%TYPE;
  TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;

  TYPE t_can_vent_devu IS TABLE OF int_solic_conso_poven_detal.can_vent_devu%TYPE;
  TYPE t_can_prod_dese IS TABLE OF int_solic_conso_poven_detal.can_prod_dese%TYPE;

  TYPE t_ind_nume_vece_pedi IS TABLE OF int_solic_conso_poven_detal.ind_nume_vece_pedi%TYPE;
  TYPE t_oid_soli_posi_devu IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
  TYPE t_copa_oid_para_gene_devu IS TABLE OF int_solic_conso_poven_detal.copa_oid_para_gene_devu%TYPE;

  lv_val_prec_cata_unit_loca ped_solic_posic.val_prec_cata_unit_loca%TYPE;
  lv_val_prec_fact_unit_loca ped_solic_posic.val_prec_fact_unit_loca%TYPE;
  lv_val_prec_cont_unit_loca ped_solic_posic.val_prec_cont_unit_loca%TYPE;
  lv_oid_soli_posi           ped_solic_posic.oid_soli_posi%TYPE;

  lv_prod_oid_prod_devu ped_solic_posic.prod_oid_prod%TYPE;

  lv_panp_oid_para_nive_prem  inc_premi_artic.panp_oid_para_nive_prem%TYPE;
  lv_lopa_oid_lote_prem_arti  inc_artic_lote.lopa_oid_lote_prem_arti%TYPE;
  lv_copa_oid_para_gral       inc_param_gener_premi.copa_oid_para_gral%TYPE;
  lv_imp_prec_publ            inc_artic_lote.imp_prec_publ%TYPE;
  lv_oid_lote_prem_artic_devu inc_artic_lote.lopa_oid_lote_prem_arti%TYPE;
  lv_imp_prec_cata            INTEGER;

  lv_ind_cent_dist_gara      inc_artic_lote.ind_cent_dist_gara%TYPE;
  lv_cese_oid_cese_gara      inc_artic_lote.cese_oid_cese_gara%TYPE;
  lv_num_mese_gara           inc_artic_lote.num_mese_gara%TYPE;
  lv_oid_para_nive_prem_devu inc_premi_artic.panp_oid_para_nive_prem%TYPE;
  lv_copa_oid_para_gene_devu inc_concu_param_gener.oid_para_gral%TYPE;
  lv_tofe_oid_tipo_ofer      pre_tipo_ofert.oid_tipo_ofer%TYPE;
  lv_oid_matr_fact           pre_matri_factu.oid_matr_fact%TYPE;

  lv_modu_oid_modu      ped_solic_cabec.modu_oid_modu%TYPE;
  lv_ofde_oid_deta_ofer pre_ofert_detal.oid_deta_ofer%TYPE;

  v_ind_nume_vece_pedi t_ind_nume_vece_pedi;
  v_oid_soli_posi_devu t_oid_soli_posi_devu;

  v_codpais       t_codpais;
  v_codperi       t_codperi;
  v_codclie       t_codclie;
  v_numdocu       t_numdocu;
  v_tiprefe       t_tiprefe;
    v_cod_vent_devu      t_cod_vent_devu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

  v_ind_envi_fact t_ind_envi_fact;
  v_cod_vent_dese t_cod_vent_dese;
  v_cod_prec_envi t_cod_prec_envi;
  v_cod_prec      t_cod_prec;
    v_oid_oper           t_oid_oper;
  v_ind_devu_fact t_ind_devu_fact;
  v_num_docu_cruc t_num_docu_cruc;
  v_oid_peri_refe t_oid_peri_refe;
  v_ind_devu_fisi t_ind_devu_fisi;
  v_oid_cabe      t_oid_cabe;

  v_can_vent_devu t_can_vent_devu;
  v_can_prod_dese t_can_prod_dese;

  v_copa_oid_para_gene_devu t_copa_oid_para_gene_devu;

  existedevu              BOOLEAN;
  psoidperiparammigra     NUMBER;
  lsparametropermigracion NUMBER;
  lsregistro              VARCHAR2(500) := NULL;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
      INTO v_codpais,
           v_codperi,
           v_codclie,
           v_numdocu,
           v_tiprefe,
             v_cod_vent_devu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
           v_ind_envi_fact,
           v_cod_vent_dese,
           v_cod_prec_envi,
           v_cod_prec,
             v_oid_oper,
           v_ind_devu_fact,
           v_num_docu_cruc,
           v_oid_peri_refe,
           v_ind_devu_fisi,
           v_oid_cabe,
           v_can_vent_devu,
           v_ind_nume_vece_pedi,
           v_oid_soli_posi_devu,
           v_copa_oid_para_gene_devu,
           v_can_prod_dese LIMIT w_filas;
    IF v_codpais.count > 0 THEN
      FOR i IN v_codpais.first .. v_codpais.last LOOP
        existe                     := FALSE;
        lv_panp_oid_para_nive_prem := NULL;
        lv_lopa_oid_lote_prem_arti := NULL;
        lv_copa_oid_para_gral      := NULL;
        BEGIN
          lsregistro := ' PREIMP. ' || v_numdocu(i) || ' CLIENTE.' ||
                        v_codclie(i);
          existedevu := TRUE;
          IF (v_cod_vent_devu(i) IS NOT NULL AND
             v_can_vent_devu(i) IS NOT NULL) THEN
            -- Si devuelve esta en factura
            IF (v_ind_devu_fact(i) = p_indicador_devuelve_factura) THEN

              -- Si el codigo de venta esta en la matriz de precios
              IF (v_cod_prec(i) = p_indicador_matriz) THEN
                BEGIN

                  SELECT c.val_prec_cata_unit_loca,
                         decode(c.val_prec_cata_unit_loca,
                                0,
                                0,
                                c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                         c.val_prec_cont_unit_loca,
                         c.oid_soli_posi,
                         c.prod_oid_prod,
                         b.modu_oid_modu,
                         c.ofde_oid_deta_ofer
                    INTO lv_val_prec_cata_unit_loca,
                         lv_val_prec_fact_unit_loca,
                         lv_val_prec_cont_unit_loca,
                         lv_oid_soli_posi,
                         lv_prod_oid_prod_devu,
                         lv_modu_oid_modu,
                         lv_ofde_oid_deta_ofer
                    FROM ped_solic_posic c,
                         (SELECT c.oid_soli_posi,
                                 b.modu_oid_modu,
                                 c.num_unid_aten,
                                 SUM(nvl(d.num_unid_recl, 0)) num_unid_recl,
                                 c.num_unid_aten -
                                 SUM(nvl(d.num_unid_recl, 0)) num_unid_disp
                            FROM ped_solic_cabec       a,
                                 ped_solic_cabec       b,
                                 ped_solic_posic       c,
                                 rec_linea_opera_recla d
                           WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                             AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                             AND d.sopo_oid_soli_posi(+) = c.oid_soli_posi
                             AND d.timo_oid_tipo_movi(+) = 2
                             AND a.oid_soli_cabe = v_oid_cabe(i)
                                /*AND a.val_nume_soli = 1001343985 ---1001343096 */
                             AND to_number(c.val_codi_vent) =
                                 to_number(v_cod_vent_devu(i)) --- 05412
                             AND c.oid_soli_posi = (CASE
                                   WHEN v_ind_nume_vece_pedi(i) > 1 THEN
                                    v_oid_soli_posi_devu(i)
                                   ELSE
                                    c.oid_soli_posi
                                 END)
                             AND c.espo_oid_esta_posi <> 2
                           GROUP BY c.oid_soli_posi,
                                    b.modu_oid_modu,
                                    num_unid_aten
                           ORDER BY num_unid_disp DESC, modu_oid_modu ASC) b
                   WHERE c.oid_soli_posi = b.oid_soli_posi
                     AND rownum = 1
                   ORDER BY c.oid_soli_posi DESC;

                  /*IF lv_modu_oid_modu <> 15 THEN

                    SELECT d.tofe_oid_tipo_ofer, maf.oid_matr_fact
                      INTO lv_tofe_oid_tipo_ofer, lv_oid_matr_fact
                      FROM pre_ofert_detal       d,
                           pre_matri_factu       maf,
                           pre_matri_factu_cabec cab
                     WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                       AND cab.oid_cabe = maf.mfca_oid_cabe
                       AND cab.perd_oid_peri = v_oid_peri_refe(i)
                       AND to_number(d.val_codi_vent) =
                           to_number(v_cod_vent_devu(i));

                  ELSE*/

                    SELECT d.tofe_oid_tipo_ofer, maf.oid_matr_fact
                      INTO lv_tofe_oid_tipo_ofer, lv_oid_matr_fact
                      FROM pre_ofert_detal d, pre_matri_factu maf
                     WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                       AND d.oid_deta_ofer = lv_ofde_oid_deta_ofer
                       AND to_number(d.val_codi_vent) =
                           to_number(v_cod_vent_devu(i))
                       AND rownum = 1;

                  /*END IF;*/

                  UPDATE int_solic_conso_poven_detal
                     SET val_prec_cata_devu            = lv_val_prec_fact_unit_loca,
                         val_prec_cont_devu            = lv_val_prec_cont_unit_loca,
                         oid_soli_posi_devu            = lv_oid_soli_posi,
                         prod_oid_prod_devu            = lv_prod_oid_prod_devu,
                         tofe_oid_devu                 = lv_tofe_oid_tipo_ofer,
                         mafa_oid_devu                 = lv_oid_matr_fact,
                         copa_oid_para_gene_devu       = lv_copa_oid_para_gral,
                         lopa_oid_lote_prem_artic_devu = lv_lopa_oid_lote_prem_arti,
                         panp_oid_para_nive_prem_devu  = lv_panp_oid_para_nive_prem
                   WHERE cod_pais = pscodigopais
                     AND cod_peri = v_codperi(i)
                     AND cod_clie = v_codclie(i)
                     AND num_docu = v_numdocu(i)
                     AND tip_refe = v_tiprefe(i)
                     AND sec_nume_docu = v_sec_nume_docu(i)
                     AND cod_vent_devu = v_cod_vent_devu(i);

                  existedevu := TRUE;
                EXCEPTION
                  WHEN no_data_found THEN
                    lv_val_prec_cata_unit_loca := NULL;
                    lv_val_prec_fact_unit_loca := NULL;
                    lv_val_prec_cont_unit_loca := NULL;
                    lv_oid_soli_posi           := NULL;
                    lv_prod_oid_prod_devu      := NULL;
                    lv_tofe_oid_tipo_ofer      := NULL;
                    lv_oid_matr_fact           := NULL;
                    existedevu                 := FALSE;

                END;

                -- Si por el contrario esta en la matriz de incentivos
            ELSE

                BEGIN

                  lsparametropermigracion := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                                  'STO_PERI_MIGRA');
                  IF (lsparametropermigracion IS NOT NULL) THEN

                    SELECT p.oid_peri
                      INTO psoidperiparammigra
                      FROM cra_perio p, seg_perio_corpo o
                     WHERE o.oid_peri = p.peri_oid_peri
                       AND to_number(o.cod_peri) =
                           to_number(lsparametropermigracion);

                  END IF;

                  -- si periodo de referencia es mayor a periodo migrado
                  IF ((lsparametropermigracion IS NULL) OR
                     (v_oid_peri_refe(i) > psoidperiparammigra)) THEN

                    begin

                      SELECT c.val_prec_cata_unit_loca,
                             decode(c.val_prec_cata_unit_loca,
                                    0,
                                    0,
                                    c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                             c.val_prec_cont_unit_loca,
                             c.oid_soli_posi,
                             decode(dd.oid_reem_arti_lote,
                                    NULL,
                                    d.ind_cent_dist_gara,
                                    dd.ind_cent_dist_gara) AS ind_cent_dist_gara,
                             decode(dd.oid_reem_arti_lote,
                                    NULL,
                                    d.cese_oid_cese_gara,
                                    dd.cese_oid_cese_gara) AS cese_oid_cese_gara,
                             decode(dd.oid_reem_arti_lote,
                                    NULL,
                                    d.num_mese_gara,
                                    dd.num_mese_gara) AS num_mese_gara,
                             decode(dd.oid_reem_arti_lote,
                          NULL,
                                    d.prod_oid_prod,
                                    dd.prod_oid_prod) AS prod_oid_prod,
                             i.oid_para_gral,
                             d.lopa_oid_lote_prem_arti,
                             f.panp_oid_para_nive_prem
                        INTO lv_val_prec_cata_unit_loca,
                             lv_val_prec_fact_unit_loca,
                             lv_val_prec_cont_unit_loca,
                             lv_oid_soli_posi,
                             lv_ind_cent_dist_gara,
                             lv_cese_oid_cese_gara,
                             lv_num_mese_gara,
                             lv_prod_oid_prod_devu,
                             lv_copa_oid_para_gene_devu,
                             lv_oid_lote_prem_artic_devu,
                             lv_oid_para_nive_prem_devu
                        FROM ped_solic_cabec a,
                             ped_solic_cabec b,
                             ped_solic_posic c,
                             inc_artic_lote d,
                             inc_reemp_artic_lote dd,
                             inc_lote_premi_artic e,
                             inc_premi_artic f,
                             inc_param_nivel_premi g,
                             inc_param_gener_premi h,
                             inc_concu_param_gener i,
                             (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                              MAX(id.oid_arti_lote) oid_arti_lote
                                FROM inc_artic_lote id
                               GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                         AND b.copa_oid_para_gene = i.oid_para_gral
                            --AND e.num_prem = b.num_prem
                         AND d.lopa_oid_lote_prem_arti =
                             e.oid_lote_prem_arti
                         AND d.cod_vent_fict = id.cod_vent_fict
                         AND d.oid_arti_lote = id.oid_arti_lote
                         AND e.prar_oid_prem_arti = f.oid_prem_arti
                         AND f.panp_oid_para_nive_prem =
                             g.oid_para_nive_prem
                         AND g.pagp_oid_para_gene_prem =
                             h.oid_para_gene_prem
                         AND h.copa_oid_para_gral = i.oid_para_gral
                         AND dd.arlo_oid_arti_lote(+) = d.oid_arti_lote
                         AND a.oid_soli_cabe = v_oid_cabe(i) --43590098--v
                         AND to_number(c.val_codi_vent_fict) =
                             to_number(v_cod_vent_devu(i)) --98698)--
                         AND (d.cod_vent_fict = c.val_codi_vent_fict OR
                             dd.cod_vent_fict = c.val_codi_vent_fict)
                         AND c.espo_oid_esta_posi <> 2
                         AND rownum = 1
                       ORDER BY c.oid_soli_posi DESC;
                    exception
                      when no_data_found then
                        SELECT c.val_prec_cata_unit_loca,
                               decode(c.val_prec_cata_unit_loca,
                                      0,
                          0,
                                      c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                               c.val_prec_cont_unit_loca,
                               c.oid_soli_posi,
                               decode(dd.oid_reem_arti_lote,
                                      NULL,
                                      d.ind_cent_dist_gara,
                                      dd.ind_cent_dist_gara) AS ind_cent_dist_gara,
                               decode(dd.oid_reem_arti_lote,
                                      NULL,
                                      d.cese_oid_cese_gara,
                                      dd.cese_oid_cese_gara) AS cese_oid_cese_gara,
                               decode(dd.oid_reem_arti_lote,
                                      NULL,
                                      d.num_mese_gara,
                                      dd.num_mese_gara) AS num_mese_gara,
                               decode(dd.oid_reem_arti_lote,
                                      NULL,
                                      d.prod_oid_prod,
                                      dd.prod_oid_prod) AS prod_oid_prod,
                               i.oid_para_gral,
                               d.lopa_oid_lote_prem_arti,
                               f.panp_oid_para_nive_prem
                          INTO lv_val_prec_cata_unit_loca,
                               lv_val_prec_fact_unit_loca,
                               lv_val_prec_cont_unit_loca,
                               lv_oid_soli_posi,
                               lv_ind_cent_dist_gara,
                               lv_cese_oid_cese_gara,
                               lv_num_mese_gara,
                               lv_prod_oid_prod_devu,
                               lv_copa_oid_para_gene_devu,
                               lv_oid_lote_prem_artic_devu,
                               lv_oid_para_nive_prem_devu
                          FROM ped_solic_cabec a,
                               ped_solic_cabec b,
                               ped_solic_posic c,
                               inc_artic_lote d,
                               inc_reemp_artic_lote dd,
                               inc_lote_premi_artic e,
                               inc_premi_artic f,
                               inc_param_nivel_premi g,
                               inc_param_gener_premi h,
                               inc_concu_param_gener i,
                               (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                                MAX(id.oid_arti_lote) oid_arti_lote
                                  FROM inc_artic_lote id
                                 GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                         WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                           AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                           AND i.oid_para_gral =
                               v_copa_oid_para_gene_devu(i)
                              --AND e.num_prem = b.num_prem
                           AND d.lopa_oid_lote_prem_arti =
                               e.oid_lote_prem_arti
                           AND d.cod_vent_fict = id.cod_vent_fict
                           AND d.oid_arti_lote = id.oid_arti_lote
                           AND e.prar_oid_prem_arti = f.oid_prem_arti
                           AND f.panp_oid_para_nive_prem =
                               g.oid_para_nive_prem
                           AND g.pagp_oid_para_gene_prem =
                               h.oid_para_gene_prem
                           AND h.copa_oid_para_gral = i.oid_para_gral
                           AND dd.arlo_oid_arti_lote(+) = d.oid_arti_lote
                           AND a.oid_soli_cabe = v_oid_cabe(i) --43590098--v
                           AND to_number(c.val_codi_vent_fict) =
                               to_number(v_cod_vent_devu(i)) --98698)--
                           AND (d.cod_vent_fict = c.val_codi_vent_fict OR
                               dd.cod_vent_fict = c.val_codi_vent_fict)
                           AND c.espo_oid_esta_posi <> 2
                           AND rownum = 1
                         ORDER BY c.oid_soli_posi DESC;
                    end;

                  ELSE
                    -- de otro modo, si el periodo de referencia es menor o igual al migrado
                    SELECT t.val_prec_cata_unit_loca,
                           t.val_prec_fact_unit_loca,
                           t.val_prec_cont_unit_loca,
                           t.oid_soli_posi,
                           t.ind_cent_dist_gara,
                           t.cese_oid_cese_gara,
                           t.num_mese_gara,
                           t.prod_oid_prod,
                           t.oid_para_gral,
                           t.lopa_oid_lote_prem_arti,
                           t.panp_oid_para_nive_prem
                      INTO lv_val_prec_cata_unit_loca,
                           lv_val_prec_fact_unit_loca,
                           lv_val_prec_cont_unit_loca,
                           lv_oid_soli_posi,
                           lv_ind_cent_dist_gara,
                           lv_cese_oid_cese_gara,
                           lv_num_mese_gara,
                           lv_prod_oid_prod_devu,
                           lv_copa_oid_para_gene_devu,
                           lv_oid_lote_prem_artic_devu,
                           lv_oid_para_nive_prem_devu
                      FROM (SELECT 0                         AS val_prec_cata_unit_loca,
                                   0                         AS val_prec_fact_unit_loca,
                                   d.imp_prec_publ           val_prec_cont_unit_loca,
                                   NULL                      AS oid_soli_posi,
                                   d.ind_cent_dist_gara,
                                   d.cese_oid_cese_gara,
                                   d.num_mese_gara,
                                   d.prod_oid_prod,
                                   i.oid_para_gral,
                                   d.lopa_oid_lote_prem_arti,
                                   f.panp_oid_para_nive_prem
                              FROM inc_artic_lote d,
                                   inc_lote_premi_artic e,
                                   inc_premi_artic f,
                                   inc_param_nivel_premi g,
                                   inc_param_gener_premi h,
                                   inc_concu_param_gener i,
                                   (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                                    MAX(id.oid_arti_lote) oid_arti_lote
                                      FROM inc_artic_lote id
                                     GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                             WHERE d.lopa_oid_lote_prem_arti =
                                   e.oid_lote_prem_arti
                               AND d.cod_vent_fict = id.cod_vent_fict
                               AND d.oid_arti_lote = id.oid_arti_lote
                               AND e.prar_oid_prem_arti = f.oid_prem_arti
                               AND f.panp_oid_para_nive_prem =
                                   g.oid_para_nive_prem
                               AND g.pagp_oid_para_gene_prem =
                                   h.oid_para_gene_prem
                               AND h.copa_oid_para_gral = i.oid_para_gral
                               AND to_number(d.cod_vent_fict) =
                                   to_number(v_cod_vent_devu(i))
                            UNION
                            SELECT 0                         AS val_prec_cata_unit_loca,
                                   0                         AS val_prec_fact_unit_loca,
                                   r.imp_prec_publ           val_prec_cont_unit_loca,
                                   NULL                      AS oid_soli_posi,
                                   r.ind_cent_dist_gara,
                                   r.cese_oid_cese_gara,
                                   r.num_mese_gara,
                                   r.prod_oid_prod,
                                   i.oid_para_gral,
                                   d.lopa_oid_lote_prem_arti,
                                   f.panp_oid_para_nive_prem
                              FROM inc_reemp_artic_lote r,
                                   inc_artic_lote d,
                                   inc_lote_premi_artic e,
                                   inc_premi_artic f,
                                   inc_param_nivel_premi g,
                                   inc_param_gener_premi h,
                                   inc_concu_param_gener i,
                                   (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                                    MAX(id.oid_arti_lote) oid_arti_lote
                                      FROM inc_artic_lote id
                                     GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                             WHERE r.arlo_oid_arti_lote = d.oid_arti_lote
                               AND d.lopa_oid_lote_prem_arti =
                                   e.oid_lote_prem_arti
                               AND d.cod_vent_fict = id.cod_vent_fict
                               AND d.oid_arti_lote = id.oid_arti_lote
                               AND e.prar_oid_prem_arti = f.oid_prem_arti
                               AND f.panp_oid_para_nive_prem =
                                   g.oid_para_nive_prem
                               AND g.pagp_oid_para_gene_prem =
                                   h.oid_para_gene_prem
                               AND h.copa_oid_para_gral = i.oid_para_gral
                               AND to_number(d.cod_vent_fict) =
                                   to_number(v_cod_vent_devu(i))) t
                     WHERE rownum = 1;

                  END IF;
                  -- fin si

                  UPDATE int_solic_conso_poven_detal
                     SET val_prec_cata_devu            = '0',
                         val_prec_cont_devu            = lv_val_prec_cont_unit_loca,
                         oid_soli_posi_devu            = lv_oid_soli_posi,
                         ind_cent_gara                 = lv_ind_cent_dist_gara,
                         cese_oid_cese_gara            = lv_cese_oid_cese_gara,
                         num_mes_gara                  = lv_num_mese_gara,
                         prod_oid_prod_devu            = lv_prod_oid_prod_devu,
                         copa_oid_para_gene_devu       = lv_copa_oid_para_gene_devu,
                         lopa_oid_lote_prem_artic_devu = lv_oid_lote_prem_artic_devu,
                         panp_oid_para_nive_prem_devu  = lv_oid_para_nive_prem_devu
                   WHERE cod_pais = pscodigopais
                     AND cod_peri = v_codperi(i)
                     AND cod_clie = v_codclie(i)
                     AND num_docu = v_numdocu(i)
                     AND tip_refe = v_tiprefe(i)
                     AND sec_nume_docu = v_sec_nume_docu(i)
                     AND cod_vent_devu = v_cod_vent_devu(i);
                  existedevu := TRUE;

                EXCEPTION
                  WHEN no_data_found THEN
                    lv_val_prec_cata_unit_loca  := NULL;
                    lv_val_prec_fact_unit_loca  := NULL;
                    lv_val_prec_cont_unit_loca  := NULL;
                    lv_oid_soli_posi            := NULL;
                    lv_ind_cent_dist_gara       := NULL;
                    lv_cese_oid_cese_gara       := NULL;
                    lv_num_mese_gara            := NULL;
                    lv_prod_oid_prod_devu       := NULL;
                    lv_copa_oid_para_gene_devu  := NULL;
                    lv_oid_lote_prem_artic_devu := NULL;
                    lv_oid_para_nive_prem_devu  := NULL;
                    existedevu                  := FALSE;
                END;

              END IF;

              -- Si el devuelve no esta en factura
            ELSE

              -- Si el codigo esta en la matriz de precios
              IF (v_cod_prec(i) = p_indicador_matriz) THEN
                BEGIN
                  SELECT d.tofe_oid_tipo_ofer,
                         maf.oid_matr_fact,
                         d.prod_oid_prod
                    INTO lv_tofe_oid_tipo_ofer,
                         lv_oid_matr_fact,
                         lv_prod_oid_prod_devu
                    FROM pre_ofert_detal       d,
                         pre_matri_factu       maf,
                         pre_matri_factu_cabec cab
                   WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                     AND cab.oid_cabe = maf.mfca_oid_cabe
                     AND cab.perd_oid_peri = v_oid_peri_refe(i)
                     AND to_number(d.val_codi_vent) =
                         to_number(v_cod_vent_devu(i));

                  UPDATE int_solic_conso_poven_detal
                     SET val_prec_cata_devu            = 0,
                         val_prec_cont_devu            = 0,
                         prod_oid_prod_devu            = lv_prod_oid_prod_devu,
                         tofe_oid_devu                 = lv_tofe_oid_tipo_ofer,
                         mafa_oid_devu                 = lv_oid_matr_fact,
                         copa_oid_para_gene_devu       = lv_copa_oid_para_gral,
                         lopa_oid_lote_prem_artic_devu = lv_lopa_oid_lote_prem_arti,
                         panp_oid_para_nive_prem_devu  = lv_panp_oid_para_nive_prem
                   WHERE cod_pais = pscodigopais
                     AND cod_peri = v_codperi(i)
                     AND cod_clie = v_codclie(i)
                     AND num_docu = v_numdocu(i)
                     AND tip_refe = v_tiprefe(i)
                     AND sec_nume_docu = v_sec_nume_docu(i)
                     AND cod_vent_devu = v_cod_vent_devu(i);
                  existedevu := TRUE;
                EXCEPTION
                  WHEN no_data_found THEN
                    lv_tofe_oid_tipo_ofer := NULL;
                    lv_oid_matr_fact      := NULL;
                    lv_prod_oid_prod_devu := NULL;
                    existedevu            := FALSE;
                END;

                -- Si esta en la matriz de incentivos
              ELSE
                BEGIN
                  SELECT c.panp_oid_para_nive_prem,
                         a.lopa_oid_lote_prem_arti,
                         e.copa_oid_para_gral,
                         decode(dd.oid_reem_arti_lote,
                                NULL,
                                a.imp_prec_publ,
                                dd.imp_prec_publ) AS imp_prec_publ,
                         0 AS imp_prec_cata,
                         decode(dd.oid_reem_arti_lote,
                          NULL,
                                a.prod_oid_prod,
                                dd.prod_oid_prod) AS prod_oid_prod
                    INTO lv_panp_oid_para_nive_prem,
                         lv_lopa_oid_lote_prem_arti,
                         lv_copa_oid_para_gral,
                         lv_imp_prec_publ,
                         lv_imp_prec_cata,
                         lv_prod_oid_prod_devu
                    FROM inc_artic_lote a,
                         inc_lote_premi_artic b,
                         inc_premi_artic c,
                         inc_param_nivel_premi d,
                         inc_param_gener_premi e,
                         inc_concu_param_gener f,
                         inc_reemp_artic_lote dd,
                         (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                          MAX(id.oid_arti_lote) oid_arti_lote
                            FROM inc_artic_lote id
                           GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                   WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                     AND a.cod_vent_fict = id.cod_vent_fict
                     AND a.oid_arti_lote = id.oid_arti_lote
                     AND b.prar_oid_prem_arti = c.oid_prem_arti
                     AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                     AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                     AND e.copa_oid_para_gral = f.oid_para_gral
                     AND dd.arlo_oid_arti_lote(+) = a.oid_arti_lote
                     AND (to_number(a.cod_vent_fict) =
                         to_number(v_cod_vent_devu(i)) OR
                         to_number(dd.cod_vent_fict) =
                         to_number(v_cod_vent_devu(i)))
                     AND rownum = 1;

            UPDATE int_solic_conso_poven_detal
                     SET val_prec_cata_devu            = lv_imp_prec_cata,
                         val_prec_cont_devu            = lv_imp_prec_publ,
                         prod_oid_prod_devu            = lv_prod_oid_prod_devu,
                         copa_oid_para_gene_devu       = lv_copa_oid_para_gral,
                         lopa_oid_lote_prem_artic_devu = lv_lopa_oid_lote_prem_arti,
                         panp_oid_para_nive_prem_devu  = lv_panp_oid_para_nive_prem
                   WHERE cod_pais = pscodigopais
                     AND cod_peri = v_codperi(i)
                     AND cod_clie = v_codclie(i)
                     AND num_docu = v_numdocu(i)
                     AND tip_refe = v_tiprefe(i)
                     AND sec_nume_docu = v_sec_nume_docu(i)
                     AND cod_vent_devu = v_cod_vent_devu(i);
                  existedevu := TRUE;
                EXCEPTION
                  WHEN no_data_found THEN
                    lv_panp_oid_para_nive_prem := NULL;
                    lv_lopa_oid_lote_prem_arti := NULL;
                    lv_copa_oid_para_gral      := NULL;
                    lv_imp_prec_publ           := NULL;
                    lv_imp_prec_cata           := NULL;
                    lv_prod_oid_prod_devu      := NULL;
                    existedevu                 := FALSE;
                END;
              END IF;
            END IF;

            IF (v_ind_devu_fisi(i) = 0) THEN
              BEGIN
                SELECT d.tofe_oid_tipo_ofer,
                       maf.oid_matr_fact,
                       d.prod_oid_prod
                  INTO lv_tofe_oid_tipo_ofer,
                       lv_oid_matr_fact,
                       lv_prod_oid_prod_devu
                  FROM pre_ofert_detal       d,
                       pre_matri_factu       maf,
                       pre_matri_factu_cabec cab
                 WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                   AND cab.oid_cabe = maf.mfca_oid_cabe
                   AND cab.perd_oid_peri = v_oid_peri_refe(i)
                   AND to_number(d.val_codi_vent) =
                       to_number(v_cod_vent_devu(i));

            UPDATE int_solic_conso_poven_detal
                   SET val_prec_cata_devu = 0,
                       val_prec_cont_devu = 0,
                       oid_soli_posi_devu = NULL,
                       prod_oid_prod_devu = lv_prod_oid_prod_devu,
                       tofe_oid_devu      = lv_tofe_oid_tipo_ofer,
                       mafa_oid_devu      = lv_oid_matr_fact
                 WHERE cod_pais = pscodigopais
                   AND cod_peri = v_codperi(i)
                   AND cod_clie = v_codclie(i)
                   AND num_docu = v_numdocu(i)
                   AND tip_refe = v_tiprefe(i)
               AND sec_nume_docu = v_sec_nume_docu(i)
                   AND cod_vent_devu = v_cod_vent_devu(i);

                existedevu := TRUE;

              EXCEPTION
                WHEN no_data_found THEN
                  lv_tofe_oid_tipo_ofer := NULL;
                  lv_oid_matr_fact      := NULL;
                  lv_prod_oid_prod_devu := NULL;
                  existedevu            := FALSE;
              END;
            END IF;

          END IF;

          IF (v_cod_vent_devu(i) IS NOT NULL AND v_can_vent_devu(i) IS NULL) THEN
            existedevu := FALSE;
          END IF;

          existe := existedevu;

        EXCEPTION
          WHEN OTHERS THEN
            existe := FALSE;

            ln_sqlcode := SQLCODE;
            ls_sqlerrm := substr(SQLERRM, 1, 250);
            raise_application_error(-20123,
                                    'ERROR STO_PR_SPVD_REPRE_DEVUE: ' ||
                                    ls_sqlerrm || lsregistro);
        END;
        IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                            'ERROR STO_PR_SPVD_OPERA_ACCIO: ' || ls_sqlerrm ||
                            lsregistro);

  END sto_pr_spvd_opera_accio;

  /***************************************************************************
  Descripcion       : Validacion de indicador de origen
  Fecha Creacion    : 16/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_indic_orige
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor(vsnumerolote VARCHAR2) IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_lote,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             cab.num_docu,
             cab.ind_orig

        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.num_lote = vsnumerolote;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_docu IS TABLE OF int_solic_conso_poven_cabec.num_docu%TYPE;
    TYPE t_ind_orig IS TABLE OF int_solic_conso_poven_cabec.ind_orig%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_docu           t_num_docu;
    v_ind_orig           t_ind_orig;

    contador     NUMBER := 0;
    numero       NUMBER := 0;
    existe       BOOLEAN := TRUE;
    verifica     BOOLEAN := TRUE;
    lsnumerolote sto_tipo_docum_digit.num_lote%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsnumerolote := sto_pkg_gener.sto_fn_devue_nume_lote(pscodigopais,
                                                         pscodigotipodoc);

    OPEN c_cursor(lsnumerolote);
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_docu,
             v_ind_orig LIMIT w_filas;

      IF (v_codpais.count > 0) THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          IF (v_ind_orig(i) = '1') THEN

            existe := FALSE;
            BEGIN
              contador := 0;
              SELECT COUNT(*)
                INTO contador
                FROM int_solic_conso_poven_cabec c
               WHERE c.num_lote < lsnumerolote
                 AND c.cod_clie = v_codclie(i)
                 AND c.num_docu = v_num_docu(i)
                 AND c.cod_peri = v_codperi(i)
                 AND c.cod_pais = v_codpais(i);

              IF (contador > 0) THEN
                existe := FALSE;

              ELSE
                existe := TRUE;
              END IF;

            EXCEPTION
              WHEN no_data_found THEN
                existe := TRUE;

            END;

          ELSE
            verifica := FALSE;
            BEGIN

              numero := 0;

              SELECT COUNT(*)
                INTO numero
                FROM int_solic_conso_poven_cabec c,
                     sto_docum_digit             s
               WHERE c.num_lote < lsnumerolote
                 AND c.cod_clie = v_codclie(i)
                 AND c.num_docu = v_num_docu(i)
                 AND c.cod_peri = v_codperi(i)
                 AND c.cod_pais = v_codpais(i)
                 AND c.sec_nume_docu = s.sec_nume_docu
                 AND s.cod_tipo_docu = 'SPVC'
                 AND s.ind_rech = 0
                 AND s.ind_envi = 0;

              IF (numero > 0) THEN
                verifica := FALSE;

              ELSE
                verifica := TRUE;
              END IF;

            EXCEPTION
              WHEN no_data_found THEN
                verifica := TRUE;

            END;

          END IF;
          IF (existe AND verifica) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;

    CLOSE c_cursor;
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_INDIC_ORIGE: ' || ls_sqlerrm);

  END sto_pr_spvd_indic_orige;

  /***************************************************************************
  Descripcion       : Validacion recuperacion de precios de productos deseados
  Fecha Creacion    : 28/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_repre_desea
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.cod_vent_devu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.ind_envi_fact,
             det.cod_vent_dese,
             det.cod_prec_envi,
             det.cod_prec,
             det.oid_oper,
             det.ind_devu_fact,
             cab.num_docu_cruc,
             cab.oid_peri_refe,
             det.ind_devu_fisi,
             cab.oid_cabe,
             det.can_vent_devu,
             det.can_prod_dese,
             det.copa_oid_para_gene_devu,
             det.cod_oper
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
      ---sto_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.sec_nume_docu_cabe = cab.sec_nume_docu
            ---and det.sec_nume_docu = 17912310
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND ((det.cod_vent_dese IS NOT NULL) OR (det.cod_vent_devu IS NOT NULL));

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_ind_envi_fact IS TABLE OF int_solic_conso_poven_detal.ind_envi_fact%TYPE;
    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;
    TYPE t_cod_prec_envi IS TABLE OF int_solic_conso_poven_detal.cod_prec_envi%TYPE;
    TYPE t_cod_prec IS TABLE OF int_solic_conso_poven_detal.cod_prec%TYPE;
    TYPE t_oid_oper IS TABLE OF int_solic_conso_poven_detal.oid_oper%TYPE;
    TYPE t_ind_devu_fact IS TABLE OF int_solic_conso_poven_detal.ind_devu_fact%TYPE;
    TYPE t_num_docu_cruc IS TABLE OF int_solic_conso_poven_cabec.num_docu_cruc%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_ind_devu_fisi IS TABLE OF int_solic_conso_poven_detal.ind_devu_fisi%TYPE;
    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;

    TYPE t_can_vent_devu IS TABLE OF int_solic_conso_poven_detal.can_vent_devu%TYPE;
    TYPE t_can_prod_dese IS TABLE OF int_solic_conso_poven_detal.can_prod_dese%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
  TYPE t_copa_oid_para_gene_devu IS TABLE OF int_solic_conso_poven_detal.copa_oid_para_gene_devu%TYPE;

    lv_val_prec_cata_unit_loca ped_solic_posic.val_prec_cata_unit_loca%TYPE;
    lv_val_prec_fact_unit_loca ped_solic_posic.val_prec_fact_unit_loca%TYPE;
    lv_val_prec_cont_unit_loca ped_solic_posic.val_prec_cont_unit_loca%TYPE;
    lv_oid_soli_posi           ped_solic_posic.oid_soli_posi%TYPE;
    lv_prod_oid_prod_envi      ped_solic_posic.prod_oid_prod%TYPE;

    lv_precio_unitario pre_ofert_detal.precio_unitario%TYPE;
    lv_imp_prec_posi   pre_ofert_detal.imp_prec_posi%TYPE;

    lv_panp_oid_para_nive_prem inc_premi_artic.panp_oid_para_nive_prem%TYPE;
    lv_lopa_oid_lote_prem_arti inc_artic_lote.lopa_oid_lote_prem_arti%TYPE;
    lv_copa_oid_para_gral      inc_param_gener_premi.copa_oid_para_gral%TYPE;
    lv_imp_prec_publ           inc_artic_lote.imp_prec_publ%TYPE;

    lv_imp_prec_cata INTEGER;

    lv_tofe_oid_tipo_ofer pre_tipo_ofert.oid_tipo_ofer%TYPE;
    lv_oid_matr_fact      pre_matri_factu.oid_matr_fact%TYPE;

    lv_modu_oid_modu_envi      ped_solic_cabec.modu_oid_modu%TYPE;
    lv_ofde_oid_deta_ofer_envi pre_ofert_detal.oid_deta_ofer%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numdocu       t_numdocu;
    v_tiprefe       t_tiprefe;
    v_cod_vent_devu t_cod_vent_devu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_ind_envi_fact t_ind_envi_fact;
    v_cod_vent_dese t_cod_vent_dese;
    v_cod_prec_envi t_cod_prec_envi;
    v_cod_prec      t_cod_prec;
    v_oid_oper      t_oid_oper;
    v_ind_devu_fact t_ind_devu_fact;
    v_num_docu_cruc t_num_docu_cruc;
    v_oid_peri_refe t_oid_peri_refe;
    v_ind_devu_fisi t_ind_devu_fisi;
    v_oid_cabe      t_oid_cabe;
    v_can_vent_devu t_can_vent_devu;
    v_can_prod_dese t_can_prod_dese;
    v_cod_oper      t_cod_oper;
  v_copa_oid_para_gene_devu t_copa_oid_para_gene_devu;

    lsparametrovalREMP sto_param_gener_occrr.val_param%TYPE;
    v_incremento int_solic_conso_poven_detal.copa_oid_para_gene_devu%TYPE;

    lsvalor     VARCHAR2(20) := '0';
    existeenvia BOOLEAN;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsparametrovalREMP := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_REMP_PRE_ENV');

    if lsparametrovalREMP = 'N' then
       v_incremento := 9000000000;
    else
       v_incremento := 0;
    end if;

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_cod_vent_devu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_ind_envi_fact,
             v_cod_vent_dese,
             v_cod_prec_envi,
             v_cod_prec,
             v_oid_oper,
             v_ind_devu_fact,
             v_num_docu_cruc,
             v_oid_peri_refe,
             v_ind_devu_fisi,
             v_oid_cabe,
             v_can_vent_devu,
             v_can_prod_dese,
             v_copa_oid_para_gene_devu,
             v_cod_oper LIMIT w_filas;
      IF v_codpais.count > 0 THEN
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe  := FALSE;
          lsvalor := v_codclie(i);
          BEGIN
            existeenvia := TRUE;
            lv_panp_oid_para_nive_prem := NULL;
            lv_lopa_oid_lote_prem_arti := NULL;
            lv_copa_oid_para_gral      := NULL;
            IF (v_cod_vent_dese(i) IS NOT NULL AND v_can_prod_dese(i) IS NOT NULL) THEN
              IF (v_cod_prec(i) = p_indicador_matriz) THEN

                BEGIN
                  SELECT d.tofe_oid_tipo_ofer,
                         maf.oid_matr_fact,
                         d.prod_oid_prod,
                         d.precio_unitario,
                         decode(d.precio_unitario,
                                0,
                                d.imp_prec_posi,
                                0) imp_prec_posi
                    INTO lv_tofe_oid_tipo_ofer,
                         lv_oid_matr_fact,
                         lv_prod_oid_prod_envi,
                         lv_precio_unitario,
                         lv_imp_prec_posi
                    FROM pre_ofert_detal       d,
                         pre_matri_factu       maf,
                         pre_matri_factu_cabec cab
                   WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                     AND cab.oid_cabe = maf.mfca_oid_cabe
                     AND cab.perd_oid_peri = v_oid_peri_refe(i)
                     AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i));
                EXCEPTION
                  WHEN no_data_found THEN
                    lv_tofe_oid_tipo_ofer := NULL;
                    lv_oid_matr_fact      := NULL;
                    lv_prod_oid_prod_envi := NULL;
                    lv_precio_unitario    := NULL;
                    lv_imp_prec_posi      := NULL;
                    existeenvia           := FALSE;
                END;

                IF (v_ind_devu_fisi(i) = 0) THEN

                  BEGIN

                    SELECT c.val_prec_cata_unit_loca,
                           decode(c.val_prec_cata_unit_loca,
                                  0,
                                  0,
                                  c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                           c.val_prec_cont_unit_loca,
                           c.oid_soli_posi,
                           c.prod_oid_prod,
                           b.modu_oid_modu,
                           c.ofde_oid_deta_ofer
                      INTO lv_val_prec_cata_unit_loca,
                           lv_val_prec_fact_unit_loca,
                           lv_val_prec_cont_unit_loca,
                           lv_oid_soli_posi,
                           lv_prod_oid_prod_envi,
                           lv_modu_oid_modu_envi,
                           lv_ofde_oid_deta_ofer_envi
                      FROM ped_solic_cabec a,
                           ped_solic_cabec b,
                           ped_solic_posic c
                     WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                       AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                          --AND a.val_nume_soli = v_num_docu_cruc(i)
                       AND a.oid_soli_cabe = v_oid_cabe(i)
                       AND to_number(c.val_codi_vent) = to_number(v_cod_vent_dese(i))
                       AND c.espo_oid_esta_posi <> 2
                       AND rownum = 1
                     ORDER BY c.oid_soli_posi DESC;

                    IF lv_modu_oid_modu_envi <> 15 THEN
                      SELECT d.tofe_oid_tipo_ofer,
                             maf.oid_matr_fact
                        INTO lv_tofe_oid_tipo_ofer,
                             lv_oid_matr_fact
                        FROM pre_ofert_detal       d,
                             pre_matri_factu       maf,
                             pre_matri_factu_cabec cab
                       WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                         AND cab.oid_cabe = maf.mfca_oid_cabe
                         AND cab.perd_oid_peri = v_oid_peri_refe(i)
                         AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i));
                    ELSE
                      SELECT d.tofe_oid_tipo_ofer,
                             maf.oid_matr_fact
                        INTO lv_tofe_oid_tipo_ofer,
                             lv_oid_matr_fact
                        FROM pre_ofert_detal d,
                             pre_matri_factu maf
                       WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                         AND d.oid_deta_ofer = lv_ofde_oid_deta_ofer_envi
                         AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i))
                         AND rownum = 1;
                    END IF;

                    IF ((lv_tofe_oid_tipo_ofer IS NOT NULL) AND (lv_oid_matr_fact IS NOT NULL)) THEN
                      existeenvia := TRUE;
                    END IF;

                    IF (v_cod_prec_envi(i) = p_codigo_precio_envia) THEN

                      UPDATE int_solic_conso_poven_detal a
                         SET val_prec_cata_envi = lv_val_prec_fact_unit_loca,
                             val_prec_cont_envi = lv_val_prec_cont_unit_loca,
                             oid_soli_posi_envi = lv_oid_soli_posi,
                             prod_oid_prod_envi = lv_prod_oid_prod_envi,
                             tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                             mafa_oid_envi      = lv_oid_matr_fact,
                             copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                             lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                             panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND sec_nume_docu = v_sec_nume_docu(i)
                         AND cod_vent_dese = v_cod_vent_dese(i);
                      existeenvia := TRUE AND existeenvia;

                    ELSE

                      UPDATE int_solic_conso_poven_detal
                         SET val_prec_cata_envi = lv_val_prec_cata_unit_loca,
                             val_prec_cont_envi = lv_val_prec_cont_unit_loca,
                             oid_soli_posi_envi = lv_oid_soli_posi,
                             prod_oid_prod_envi = lv_prod_oid_prod_envi,
                             tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                             mafa_oid_envi      = lv_oid_matr_fact,
                             copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                             lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                             panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND sec_nume_docu = v_sec_nume_docu(i)
                         AND cod_vent_dese = v_cod_vent_dese(i);
                      existeenvia := TRUE AND existeenvia;

                    END IF;

                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_val_prec_cata_unit_loca := NULL;
                      lv_val_prec_fact_unit_loca := NULL;
                      lv_val_prec_cont_unit_loca := NULL;
                      lv_oid_soli_posi           := NULL;
                      lv_prod_oid_prod_envi      := NULL;
                      existeenvia                := FALSE;
                  END;

                ELSE
                  IF (v_ind_envi_fact(i) = p_indicador_envio_factura) THEN

                    BEGIN

                      SELECT c.val_prec_cata_unit_loca,
                             decode(c.val_prec_cata_unit_loca,
                                    0,
                                    0,
                                    c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                             c.val_prec_cont_unit_loca,
                             c.oid_soli_posi,
                             c.prod_oid_prod,
                             b.modu_oid_modu,
                             c.ofde_oid_deta_ofer
                        INTO lv_val_prec_cata_unit_loca,
                             lv_val_prec_fact_unit_loca,
                             lv_val_prec_cont_unit_loca,
                             lv_oid_soli_posi,
                             lv_prod_oid_prod_envi,
                             lv_modu_oid_modu_envi,
                             lv_ofde_oid_deta_ofer_envi
                        FROM ped_solic_cabec a,
                             ped_solic_cabec b,
                             ped_solic_posic c
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                            -- AND a.val_nume_soli =
                            --   v_num_docu_cruc(i)
                         AND a.oid_soli_cabe = v_oid_cabe(i)
                         AND to_number(c.val_codi_vent) = to_number(v_cod_vent_dese(i))
                         AND c.espo_oid_esta_posi <> 2
                         AND rownum = 1
                       ORDER BY c.oid_soli_posi DESC;

                      IF lv_modu_oid_modu_envi <> 15 THEN
                        SELECT d.tofe_oid_tipo_ofer,
                               maf.oid_matr_fact
                          INTO lv_tofe_oid_tipo_ofer,
                               lv_oid_matr_fact
                          FROM pre_ofert_detal       d,
                               pre_matri_factu       maf,
                               pre_matri_factu_cabec cab
                         WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                           AND cab.oid_cabe = maf.mfca_oid_cabe
                           AND cab.perd_oid_peri = v_oid_peri_refe(i)
                           AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i));
                      ELSE
                        SELECT d.tofe_oid_tipo_ofer,
                               maf.oid_matr_fact
                          INTO lv_tofe_oid_tipo_ofer,
                               lv_oid_matr_fact
                          FROM pre_ofert_detal d,
                               pre_matri_factu maf
                         WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                           AND d.oid_deta_ofer = lv_ofde_oid_deta_ofer_envi
                           AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese(i))
                           AND rownum = 1;
                      END IF;

                      IF ((lv_tofe_oid_tipo_ofer IS NOT NULL) AND (lv_oid_matr_fact IS NOT NULL)) THEN
                        existeenvia := TRUE;
                      END IF;

                      IF (v_cod_prec_envi(i) = p_codigo_precio_envia) THEN

                        UPDATE int_solic_conso_poven_detal a
                           SET val_prec_cata_envi = lv_val_prec_fact_unit_loca,
                               val_prec_cont_envi = lv_val_prec_cont_unit_loca,
                               oid_soli_posi_envi = lv_oid_soli_posi,
                               prod_oid_prod_envi = lv_prod_oid_prod_envi,
                               tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                               mafa_oid_envi      = lv_oid_matr_fact,
                               copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                               lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                               panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                         WHERE cod_pais = pscodigopais
                           AND cod_peri = v_codperi(i)
                           AND cod_clie = v_codclie(i)
                           AND num_docu = v_numdocu(i)
                           AND tip_refe = v_tiprefe(i)
                           AND sec_nume_docu = v_sec_nume_docu(i)
                           AND cod_vent_dese = v_cod_vent_dese(i);
                        existeenvia := TRUE AND existeenvia;

                      ELSE

                        UPDATE int_solic_conso_poven_detal
                           SET val_prec_cata_envi = lv_val_prec_cata_unit_loca,
                               val_prec_cont_envi = lv_val_prec_cont_unit_loca,
                               oid_soli_posi_envi = lv_oid_soli_posi,
                               prod_oid_prod_envi = lv_prod_oid_prod_envi,
                               tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                               mafa_oid_envi      = lv_oid_matr_fact,
                               copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                               lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                               panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                         WHERE cod_pais = pscodigopais
                           AND cod_peri = v_codperi(i)
                           AND cod_clie = v_codclie(i)
                           AND num_docu = v_numdocu(i)
                           AND tip_refe = v_tiprefe(i)
                           AND sec_nume_docu = v_sec_nume_docu(i)
                           AND cod_vent_dese = v_cod_vent_dese(i);
                        existeenvia := TRUE AND existeenvia;
                      END IF;

                    EXCEPTION
                      WHEN no_data_found THEN
                        lv_val_prec_cata_unit_loca := NULL;
                        lv_val_prec_fact_unit_loca := NULL;
                        lv_val_prec_cont_unit_loca := NULL;
                        lv_oid_soli_posi           := NULL;
                        lv_prod_oid_prod_envi      := NULL;
                        existeenvia                := FALSE;
                    END;

                  ELSE

                    IF (lv_precio_unitario IS NOT NULL) THEN
                      UPDATE int_solic_conso_poven_detal
                         SET val_prec_cata_envi = lv_precio_unitario,
                             val_prec_cont_envi = lv_imp_prec_posi,
                             prod_oid_prod_envi = lv_prod_oid_prod_envi,
                             tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                             mafa_oid_envi      = lv_oid_matr_fact,
                             copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                             lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                             panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND sec_nume_docu = v_sec_nume_docu(i)
                         AND cod_vent_dese = v_cod_vent_dese(i);

                      existeenvia := TRUE AND existeenvia;

                    ELSE

                      UPDATE int_solic_conso_poven_detal
                         SET val_prec_cata_envi = 0,
                             val_prec_cont_envi = lv_imp_prec_posi,
                             prod_oid_prod_envi = lv_prod_oid_prod_envi,
                             tofe_oid_envi      = lv_tofe_oid_tipo_ofer,
                             mafa_oid_envi      = lv_oid_matr_fact,
                             copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                             lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                             panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND sec_nume_docu = v_sec_nume_docu(i)
                         AND cod_vent_dese = v_cod_vent_dese(i);

                      existeenvia := TRUE AND existeenvia;

                    END IF;

                  END IF;

                END IF;

              ELSE
                  ---- Si es canje de premio tiene que ser por el mismo premio - se valida el COPA
                  IF v_cod_oper(i) in('CP','PC','FP','PF') THEN
                    --------------------------
                    /*BEGIN

                  SELECT c.panp_oid_para_nive_prem,
                         a.lopa_oid_lote_prem_arti,
                         e.copa_oid_para_gral,
                         decode(dd.oid_reem_arti_lote,
                                NULL,
                                a.imp_prec_publ,
                                dd.imp_prec_publ) AS imp_prec_publ,
                         0 AS imp_prec_cata,
                         decode(dd.oid_reem_arti_lote,
                                NULL,
                                a.prod_oid_prod,
                                dd.prod_oid_prod) AS prod_oid_prod
                    INTO lv_panp_oid_para_nive_prem,
                         lv_lopa_oid_lote_prem_arti,
                         lv_copa_oid_para_gral,
                         lv_imp_prec_publ,
                         lv_imp_prec_cata,
                         lv_prod_oid_prod_envi
                    FROM inc_artic_lote a,
                         inc_lote_premi_artic b,
                         inc_premi_artic c,
                         inc_param_nivel_premi d,
                         inc_param_gener_premi e,
                         inc_concu_param_gener f,
                             inc_reemp_artic_lote dd
                       WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                         AND b.prar_oid_prem_arti = c.oid_prem_arti
                         AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                         AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                         AND e.copa_oid_para_gral = f.oid_para_gral
                         AND dd.arlo_oid_arti_lote(+) = a.oid_arti_lote + v_incremento
                         and f.oid_para_gral = v_copa_oid_para_gene_devu(i)
                         AND ((v_cod_oper(i) IN ('AP',
                                                 'PA',
                                                 'FP',
                                                 'PF',
                                                 'TP',
                                                 'PT',
                                                 'CP',
                                                 'PC',
                                                 'SP',
                                                 'PS') AND
                             (v_cod_vent_dese(i) = to_number(dd.cod_vent_fict) OR
                             v_cod_vent_dese(i) = to_number(a.cod_vent_fict))) OR
                             (v_cod_oper(i) NOT IN ('AP',
                                                     'PA',
                                                     'FP',
                                                     'PF',
                                                     'TP',
                                                     'PT',
                                                     'CP',
                                                     'PC',
                                                     'SP',
                                                     'PS') AND
                             (v_cod_vent_devu(i) = to_number(dd.cod_vent_fict) OR
                             v_cod_vent_devu(i) = to_number(a.cod_vent_fict))))
                         AND rownum = 1;

                      UPDATE int_solic_conso_poven_detal
                         SET val_prec_cata_envi            = lv_imp_prec_cata,
                             val_prec_cont_envi            = lv_imp_prec_publ,
                             prod_oid_prod_envi            = lv_prod_oid_prod_envi,
                             copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                             lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                             panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND sec_nume_docu = v_sec_nume_docu(i)
                         AND cod_vent_dese = v_cod_vent_dese(i);

                      existeenvia := TRUE;

                    EXCEPTION
                      WHEN no_data_found THEN
                        lv_panp_oid_para_nive_prem := NULL;
                        lv_lopa_oid_lote_prem_arti := NULL;
                        lv_copa_oid_para_gral      := NULL;
                        lv_imp_prec_publ           := NULL;
                        lv_imp_prec_cata           := NULL;
                        existeenvia                := FALSE;
                    END; */
                    --------------------

                    BEGIN

                      SELECT c.panp_oid_para_nive_prem,
                             a.lopa_oid_lote_prem_arti,
                             e.copa_oid_para_gral,
                             a.imp_prec_publ,
                             0 AS imp_prec_cata,
                             a.prod_oid_prod
                        INTO lv_panp_oid_para_nive_prem,
                             lv_lopa_oid_lote_prem_arti,
                             lv_copa_oid_para_gral,
                             lv_imp_prec_publ,
                             lv_imp_prec_cata,
                             lv_prod_oid_prod_envi
                        FROM inc_artic_lote a,
                             inc_lote_premi_artic b,
                             inc_premi_artic c,
                             inc_param_nivel_premi d,
                             inc_param_gener_premi e,
                             inc_concu_param_gener f
                       WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                         AND b.prar_oid_prem_arti = c.oid_prem_arti
                         AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                         AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                         AND e.copa_oid_para_gral = f.oid_para_gral
                         and f.oid_para_gral = v_copa_oid_para_gene_devu(i)
                         AND to_number(a.cod_vent_fict) = v_cod_vent_dese(i);

                      UPDATE int_solic_conso_poven_detal
                         SET val_prec_cata_envi            = lv_imp_prec_cata,
                             val_prec_cont_envi            = lv_imp_prec_publ,
                             prod_oid_prod_envi            = lv_prod_oid_prod_envi,
                             copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                             lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                             panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND sec_nume_docu = v_sec_nume_docu(i)
                         AND cod_vent_dese = v_cod_vent_dese(i);

                      existeenvia := TRUE;

                    EXCEPTION
                      WHEN no_data_found THEN
                        lv_panp_oid_para_nive_prem := NULL;
                        lv_lopa_oid_lote_prem_arti := NULL;
                        lv_copa_oid_para_gral      := NULL;
                        lv_imp_prec_publ           := NULL;
                        lv_imp_prec_cata           := NULL;
                        existeenvia                := FALSE;
                    END;

                    if existeenvia = FALSE then

                        BEGIN

                          SELECT c.panp_oid_para_nive_prem,
                                 a.lopa_oid_lote_prem_arti,
                                 e.copa_oid_para_gral,
                                 decode(dd.oid_reem_arti_lote,
                                        NULL,
                                        a.imp_prec_publ,
                                        dd.imp_prec_publ) AS imp_prec_publ,
                                 0 AS imp_prec_cata,
                                 decode(dd.oid_reem_arti_lote,
                                        NULL,
                                        a.prod_oid_prod,
                                        dd.prod_oid_prod) AS prod_oid_prod
                            INTO lv_panp_oid_para_nive_prem,
                                 lv_lopa_oid_lote_prem_arti,
                                 lv_copa_oid_para_gral,
                                 lv_imp_prec_publ,
                                 lv_imp_prec_cata,
                                 lv_prod_oid_prod_envi
                            FROM inc_artic_lote a,
                                 inc_lote_premi_artic b,
                                 inc_premi_artic c,
                                 inc_param_nivel_premi d,
                                 inc_param_gener_premi e,
                                 inc_concu_param_gener f,
                                 inc_reemp_artic_lote dd
                           WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                             AND b.prar_oid_prem_arti = c.oid_prem_arti
                             AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                             AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                             AND e.copa_oid_para_gral = f.oid_para_gral
                             AND dd.arlo_oid_arti_lote(+) = a.oid_arti_lote
                             and f.oid_para_gral = v_copa_oid_para_gene_devu(i)
                             and to_number(dd.cod_vent_fict) = v_cod_vent_dese(i);


                          UPDATE int_solic_conso_poven_detal
                             SET val_prec_cata_envi            = lv_imp_prec_cata,
                                 val_prec_cont_envi            = lv_imp_prec_publ,
                                 prod_oid_prod_envi            = lv_prod_oid_prod_envi,
                                 copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                                 lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                                 panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                           WHERE cod_pais = pscodigopais
                             AND cod_peri = v_codperi(i)
                             AND cod_clie = v_codclie(i)
                             AND num_docu = v_numdocu(i)
                             AND tip_refe = v_tiprefe(i)
                             AND sec_nume_docu = v_sec_nume_docu(i)
                             AND cod_vent_dese = v_cod_vent_dese(i);

                          existeenvia := TRUE;

                        EXCEPTION
                          WHEN no_data_found THEN
                            lv_panp_oid_para_nive_prem := NULL;
                            lv_lopa_oid_lote_prem_arti := NULL;
                            lv_copa_oid_para_gral      := NULL;
                            lv_imp_prec_publ           := NULL;
                            lv_imp_prec_cata           := NULL;
                            existeenvia                := FALSE;
                        END;

                    end if;

                  ELSE
                    BEGIN
                      --- Query para los premios sin interesar si es el reemplazo
                      SELECT c.panp_oid_para_nive_prem,
                             a.lopa_oid_lote_prem_arti,
                             e.copa_oid_para_gral,
                             a.imp_prec_publ AS imp_prec_publ,
                             0 AS imp_prec_cata,
                             a.prod_oid_prod AS prod_oid_prod
                        INTO lv_panp_oid_para_nive_prem,
                             lv_lopa_oid_lote_prem_arti,
                             lv_copa_oid_para_gral,
                             lv_imp_prec_publ,
                             lv_imp_prec_cata,
                             lv_prod_oid_prod_envi
                        FROM inc_artic_lote a,
                             inc_lote_premi_artic b,
                             inc_premi_artic c,
                             inc_param_nivel_premi d,
                             inc_param_gener_premi e,
                             inc_concu_param_gener f,
                             (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                              MAX(id.oid_arti_lote) oid_arti_lote
                                FROM inc_artic_lote id
                               GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                       WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                         AND a.cod_vent_fict = id.cod_vent_fict
                         AND a.oid_arti_lote = id.oid_arti_lote
                         AND b.prar_oid_prem_arti = c.oid_prem_arti
                         AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                         AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                         AND e.copa_oid_para_gral = f.oid_para_gral
                         AND ((v_cod_oper(i) IN ('AP',
                                                 'PA',
                                                 'FP',
                                                 'PF',
                                                 'TP',
                                                 'PT',
                                                 'CP',
                                                 'PC',
                                                 'SP',
                                                 'PS') AND
                             (v_cod_vent_dese(i) = to_number(a.cod_vent_fict))) OR
                             (v_cod_oper(i) NOT IN ('AP',
                                                     'PA',
                                                     'FP',
                                                     'PF',
                                                     'TP',
                                                     'PT',
                                                     'CP',
                                                     'PC',
                                                     'SP',
                                                     'PS') AND
                             (v_cod_vent_devu(i) = to_number(a.cod_vent_fict))))
                         AND rownum = 1;

                      UPDATE int_solic_conso_poven_detal
                         SET val_prec_cata_envi            = lv_imp_prec_cata,
                             val_prec_cont_envi            = lv_imp_prec_publ,
                             prod_oid_prod_envi            = lv_prod_oid_prod_envi,
                             copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                             lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                             panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND sec_nume_docu = v_sec_nume_docu(i)
                         AND cod_vent_dese = v_cod_vent_dese(i);

                      existeenvia := TRUE;

                    EXCEPTION
                      WHEN no_data_found THEN
                        BEGIN
                            ----- Query solo para los reemplazos
                      SELECT c.panp_oid_para_nive_prem,
                             a.lopa_oid_lote_prem_arti,
                             e.copa_oid_para_gral,
                             decode(dd.oid_reem_arti_lote,
                                    NULL,
                                    a.imp_prec_publ,
                                    dd.imp_prec_publ) AS imp_prec_publ,
                             0 AS imp_prec_cata,
                             decode(dd.oid_reem_arti_lote,
                                    NULL,
                                    a.prod_oid_prod,
                                    dd.prod_oid_prod) AS prod_oid_prod
                        INTO lv_panp_oid_para_nive_prem,
                             lv_lopa_oid_lote_prem_arti,
                             lv_copa_oid_para_gral,
                             lv_imp_prec_publ,
                             lv_imp_prec_cata,
                             lv_prod_oid_prod_envi
                        FROM inc_artic_lote a,
                             inc_lote_premi_artic b,
                             inc_premi_artic c,
                             inc_param_nivel_premi d,
                             inc_param_gener_premi e,
                             inc_concu_param_gener f,
                         inc_reemp_artic_lote dd,
                         (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                          MAX(id.oid_arti_lote) oid_arti_lote
                            FROM inc_artic_lote id
                           GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                   WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                     AND a.cod_vent_fict = id.cod_vent_fict
                     AND a.oid_arti_lote = id.oid_arti_lote
                     AND b.prar_oid_prem_arti = c.oid_prem_arti
                     AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                     AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                     AND e.copa_oid_para_gral = f.oid_para_gral
                         AND dd.arlo_oid_arti_lote(+) = a.oid_arti_lote --- + v_incremento
                     AND ((v_cod_oper(i) IN ('AP',
                                             'PA',
                                             'FP',
                                             'PF',
                                             'TP',
                                             'PT',
                                             'CP',
                                             'PC',
                                             'SP',
                                             'PS') AND
                         (v_cod_vent_dese(i) = to_number(dd.cod_vent_fict) OR
                         v_cod_vent_dese(i) = to_number(a.cod_vent_fict))) OR
                         (v_cod_oper(i) NOT IN ('AP',
                                                 'PA',
                                                 'FP',
                                                 'PF',
                                                 'TP',
                                                 'PT',
                                                 'CP',
                                                 'PC',
                                                 'SP',
                                                 'PS') AND
                         (v_cod_vent_devu(i) = to_number(dd.cod_vent_fict) OR
                         v_cod_vent_devu(i) = to_number(a.cod_vent_fict))))
                     AND rownum = 1;

                  UPDATE int_solic_conso_poven_detal
                     SET val_prec_cata_envi            = lv_imp_prec_cata,
                         val_prec_cont_envi            = lv_imp_prec_publ,
                         prod_oid_prod_envi            = lv_prod_oid_prod_envi,
                         copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                         lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                         panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                   WHERE cod_pais = pscodigopais
                     AND cod_peri = v_codperi(i)
                     AND cod_clie = v_codclie(i)
                     AND num_docu = v_numdocu(i)
                     AND tip_refe = v_tiprefe(i)
                     AND sec_nume_docu = v_sec_nume_docu(i)
                     AND cod_vent_dese = v_cod_vent_dese(i);

                  existeenvia := TRUE;

                EXCEPTION
                  WHEN no_data_found THEN
                    lv_panp_oid_para_nive_prem := NULL;
                    lv_lopa_oid_lote_prem_arti := NULL;
                    lv_copa_oid_para_gral      := NULL;
                    lv_imp_prec_publ           := NULL;
                    lv_imp_prec_cata           := NULL;
                    existeenvia                := FALSE;
                END;
                    END;

              END IF;

            END IF;

            END IF;

            IF (v_cod_vent_dese(i) IS NOT NULL AND v_can_prod_dese(i) IS NULL) THEN
              existeenvia := FALSE;
            END IF;

            existe := existeenvia;

          EXCEPTION
            WHEN OTHERS THEN
              existe := FALSE;
          END;
          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_REPRE_DESEA: ' || lsvalor || ls_sqlerrm);

  END sto_pr_spvd_repre_desea;

  /***************************************************************************
  Descripcion       : Validacion recuperacion de precios de productos deseados
  Fecha Creacion    : 28/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
 FUNCTION sto_fn_spvd_repre_desea
  (
     pscodigopais   in VARCHAR2,
     psOidPeriCDR   in number,
     pscodigoVenta  in VARCHAR2,
     psCodOperSICC  in VARCHAR2,
     psTipoOperSICC in VARCHAR2,
     psOidSoliCabe  in number
    ) RETURN BOOLEAN  IS


    lv_val_prec_cata_unit_loca ped_solic_posic.val_prec_cata_unit_loca%TYPE;
    lv_val_prec_fact_unit_loca ped_solic_posic.val_prec_fact_unit_loca%TYPE;
    lv_val_prec_cont_unit_loca ped_solic_posic.val_prec_cont_unit_loca%TYPE;
    lv_oid_soli_posi           ped_solic_posic.oid_soli_posi%TYPE;
    lv_prod_oid_prod_envi      ped_solic_posic.prod_oid_prod%TYPE;

    lv_precio_unitario pre_ofert_detal.precio_unitario%TYPE;
    lv_imp_prec_posi   pre_ofert_detal.imp_prec_posi%TYPE;

    lv_panp_oid_para_nive_prem inc_premi_artic.panp_oid_para_nive_prem%TYPE;
    lv_lopa_oid_lote_prem_arti inc_artic_lote.lopa_oid_lote_prem_arti%TYPE;
    lv_copa_oid_para_gral      inc_param_gener_premi.copa_oid_para_gral%TYPE;
    lv_imp_prec_publ           inc_artic_lote.imp_prec_publ%TYPE;

    lv_imp_prec_cata INTEGER;

    lv_tofe_oid_tipo_ofer pre_tipo_ofert.oid_tipo_ofer%TYPE;
    lv_oid_matr_fact      pre_matri_factu.oid_matr_fact%TYPE;

    lv_modu_oid_modu_envi      ped_solic_cabec.modu_oid_modu%TYPE;
    lv_ofde_oid_deta_ofer_envi pre_ofert_detal.oid_deta_ofer%TYPE;

    lsparametrovalREMP sto_param_gener_occrr.val_param%TYPE;
    v_incremento int_solic_conso_poven_detal.copa_oid_para_gene_devu%TYPE;


    v_cod_vent_dese            int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    v_oid_peri_refe            int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    v_oid_cabe                 int_solic_conso_poven_cabec.oid_cabe%TYPE;
    v_ind_devu_fisi            int_solic_conso_poven_detal.ind_devu_fisi%TYPE;
    v_cod_prec                 int_solic_conso_poven_detal.cod_prec%TYPE;
    v_cod_oper                 int_solic_conso_poven_detal.cod_oper%TYPE;
    v_ind_devu_fact            int_solic_conso_poven_detal.ind_devu_fact%TYPE;
    v_cod_prec_envi            int_solic_conso_poven_detal.cod_prec_envi%TYPE;
    v_ind_envi_fact            int_solic_conso_poven_detal.ind_envi_fact%TYPE;
    v_copa_oid_para_gene_devu  int_solic_conso_poven_detal.copa_oid_para_gene_devu%TYPE;

    lsvalor     VARCHAR2(20) := '0';
    existeenvia BOOLEAN;

  BEGIN

    lsparametrovalREMP := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_REMP_PRE_ENV');

    if lsparametrovalREMP = 'N' then
       v_incremento := 9000000000;
    else
       v_incremento := 0;
    end if;

    v_cod_vent_dese := pscodigoVenta;
    v_oid_peri_refe := psOidPeriCDR;
    v_oid_cabe      := psOidSoliCabe;
    v_cod_oper      := psCodOperSICC;

          existe  := FALSE;
          BEGIN
            existeenvia := TRUE;
            lv_panp_oid_para_nive_prem := NULL;
            lv_lopa_oid_lote_prem_arti := NULL;
            lv_copa_oid_para_gral      := NULL;
            IF (v_cod_vent_dese IS NOT NULL ) THEN

                SELECT ra.ind_devu_fisi_fact,ra.ind_envi_esta_fact,ra.cod_prec, ra.cod_prec_envi
                  INTO v_ind_devu_fisi,v_ind_envi_fact,v_cod_prec, v_cod_prec_envi
                  FROM (SELECT a.cod_oper,
                               b.val_tipo_oper,
                               a.ind_devu_fisi_fact,
                               b.ind_envi_esta_fact,
                               d.cod_prec,
                               c.cod_prec_envi
                          FROM rec_opera       a,
                               rec_tipos_opera b,
                               rec_preci_envia c,
                               rec_preci       d
                         WHERE a.oid_oper = b.rope_oid_oper
                           AND a.penv_oid_precio_envia = c.oid_prec_envi
                           AND a.peci_oid_peci = d.oid_prec
                           AND a.cod_oper = psCodOperSICC
                           AND b.val_tipo_oper = psTipoOperSICC) ra,
                       rec_param_opera e
                 WHERE ra.cod_oper = e.cod_oper(+)
                   AND ra.val_tipo_oper = e.cod_tipo_oper(+);


              IF (v_cod_prec = p_indicador_matriz) THEN

                BEGIN
                  SELECT d.tofe_oid_tipo_ofer,
                         maf.oid_matr_fact,
                         d.prod_oid_prod,
                         d.precio_unitario,
                         decode(d.precio_unitario,
                                0,
                                d.imp_prec_posi,
                                0) imp_prec_posi
                    INTO lv_tofe_oid_tipo_ofer,
                         lv_oid_matr_fact,
                         lv_prod_oid_prod_envi,
                         lv_precio_unitario,
                         lv_imp_prec_posi
                    FROM pre_ofert_detal       d,
                         pre_matri_factu       maf,
                         pre_matri_factu_cabec cab
                   WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                     AND cab.oid_cabe = maf.mfca_oid_cabe
                     AND cab.perd_oid_peri = v_oid_peri_refe
                     AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese);
                EXCEPTION
                  WHEN no_data_found THEN
                    lv_tofe_oid_tipo_ofer := NULL;
                    lv_oid_matr_fact      := NULL;
                    lv_prod_oid_prod_envi := NULL;
                    lv_precio_unitario    := NULL;
                    lv_imp_prec_posi      := NULL;
                    existeenvia           := FALSE;
                END;

                IF (v_ind_devu_fisi = 0) THEN

                  BEGIN

                    SELECT c.val_prec_cata_unit_loca,
                           decode(c.val_prec_cata_unit_loca,
                                  0,
                                  0,
                                  c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                           c.val_prec_cont_unit_loca,
                           c.oid_soli_posi,
                           c.prod_oid_prod,
                           b.modu_oid_modu,
                           c.ofde_oid_deta_ofer
                      INTO lv_val_prec_cata_unit_loca,
                           lv_val_prec_fact_unit_loca,
                           lv_val_prec_cont_unit_loca,
                           lv_oid_soli_posi,
                           lv_prod_oid_prod_envi,
                           lv_modu_oid_modu_envi,
                           lv_ofde_oid_deta_ofer_envi
                      FROM ped_solic_cabec a,
                           ped_solic_cabec b,
                           ped_solic_posic c
                     WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                       AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                       AND a.oid_soli_cabe = v_oid_cabe
                       AND to_number(c.val_codi_vent) = to_number(v_cod_vent_dese)
                       AND c.espo_oid_esta_posi <> 2
                       AND rownum = 1
                     ORDER BY c.oid_soli_posi DESC;

                    IF lv_modu_oid_modu_envi <> 15 THEN
                      SELECT d.tofe_oid_tipo_ofer,
                             maf.oid_matr_fact
                        INTO lv_tofe_oid_tipo_ofer,
                             lv_oid_matr_fact
                        FROM pre_ofert_detal       d,
                             pre_matri_factu       maf,
                             pre_matri_factu_cabec cab
                       WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                         AND cab.oid_cabe = maf.mfca_oid_cabe
                         AND cab.perd_oid_peri = v_oid_peri_refe
                         AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese);
                    ELSE
                      SELECT d.tofe_oid_tipo_ofer,
                             maf.oid_matr_fact
                        INTO lv_tofe_oid_tipo_ofer,
                             lv_oid_matr_fact
                        FROM pre_ofert_detal d,
                             pre_matri_factu maf
                       WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                         AND d.oid_deta_ofer = lv_ofde_oid_deta_ofer_envi
                         AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese)
                         AND rownum = 1;
                    END IF;

                    IF ((lv_tofe_oid_tipo_ofer IS NOT NULL) AND (lv_oid_matr_fact IS NOT NULL)) THEN
                      existeenvia := TRUE;
                    END IF;

                    IF (v_cod_prec_envi = p_codigo_precio_envia) THEN

                      existeenvia := TRUE AND existeenvia;

                    ELSE

                      existeenvia := TRUE AND existeenvia;

                    END IF;

                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_val_prec_cata_unit_loca := NULL;
                      lv_val_prec_fact_unit_loca := NULL;
                      lv_val_prec_cont_unit_loca := NULL;
                      lv_oid_soli_posi           := NULL;
                      lv_prod_oid_prod_envi      := NULL;
                      existeenvia                := FALSE;
                  END;

                ELSE
                  IF (v_ind_envi_fact = p_indicador_envio_factura) THEN

                    BEGIN

                      SELECT c.val_prec_cata_unit_loca,
                             decode(c.val_prec_cata_unit_loca,
                                    0,
                                    0,
                                    c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                             c.val_prec_cont_unit_loca,
                             c.oid_soli_posi,
                             c.prod_oid_prod,
                             b.modu_oid_modu,
                             c.ofde_oid_deta_ofer
                        INTO lv_val_prec_cata_unit_loca,
                             lv_val_prec_fact_unit_loca,
                             lv_val_prec_cont_unit_loca,
                             lv_oid_soli_posi,
                             lv_prod_oid_prod_envi,
                             lv_modu_oid_modu_envi,
                             lv_ofde_oid_deta_ofer_envi
                        FROM ped_solic_cabec a,
                             ped_solic_cabec b,
                             ped_solic_posic c
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                         AND a.oid_soli_cabe = v_oid_cabe
                         AND to_number(c.val_codi_vent) = to_number(v_cod_vent_dese)
                         AND c.espo_oid_esta_posi <> 2
                         AND rownum = 1
                       ORDER BY c.oid_soli_posi DESC;

                      IF lv_modu_oid_modu_envi <> 15 THEN
                        SELECT d.tofe_oid_tipo_ofer,
                               maf.oid_matr_fact
                          INTO lv_tofe_oid_tipo_ofer,
                               lv_oid_matr_fact
                          FROM pre_ofert_detal       d,
                               pre_matri_factu       maf,
                               pre_matri_factu_cabec cab
                         WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                           AND cab.oid_cabe = maf.mfca_oid_cabe
                           AND cab.perd_oid_peri = v_oid_peri_refe
                           AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese);
                      ELSE
                        SELECT d.tofe_oid_tipo_ofer,
                               maf.oid_matr_fact
                          INTO lv_tofe_oid_tipo_ofer,
                               lv_oid_matr_fact
                          FROM pre_ofert_detal d,
                               pre_matri_factu maf
                         WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                           AND d.oid_deta_ofer = lv_ofde_oid_deta_ofer_envi
                           AND to_number(d.val_codi_vent) = to_number(v_cod_vent_dese)
                           AND rownum = 1;
                      END IF;

                      IF ((lv_tofe_oid_tipo_ofer IS NOT NULL) AND (lv_oid_matr_fact IS NOT NULL)) THEN
                        existeenvia := TRUE;
                      END IF;

                      IF (v_cod_prec_envi = p_codigo_precio_envia) THEN

                        existeenvia := TRUE AND existeenvia;

                      ELSE

                        existeenvia := TRUE AND existeenvia;
                      END IF;

                    EXCEPTION
                      WHEN no_data_found THEN
                        lv_val_prec_cata_unit_loca := NULL;
                        lv_val_prec_fact_unit_loca := NULL;
                        lv_val_prec_cont_unit_loca := NULL;
                        lv_oid_soli_posi           := NULL;
                        lv_prod_oid_prod_envi      := NULL;
                        existeenvia                := FALSE;
                    END;

                  ELSE

                    IF (lv_precio_unitario IS NOT NULL) THEN

                      existeenvia := TRUE AND existeenvia;

                    ELSE

                      existeenvia := TRUE AND existeenvia;

                    END IF;

                  END IF;

                END IF;

              ELSE


                  ---- Si es canje de premio tiene que ser por el mismo premio - se valida el COPA
                  IF v_cod_oper in('CP','PC','FP','PF') THEN

                     existeenvia := TRUE;

                    /*
                    BEGIN

                      SELECT c.panp_oid_para_nive_prem,
                             a.lopa_oid_lote_prem_arti,
                             e.copa_oid_para_gral,
                             a.imp_prec_publ,
                             0 AS imp_prec_cata,
                             a.prod_oid_prod
                        INTO lv_panp_oid_para_nive_prem,
                             lv_lopa_oid_lote_prem_arti,
                             lv_copa_oid_para_gral,
                             lv_imp_prec_publ,
                             lv_imp_prec_cata,
                             lv_prod_oid_prod_envi
                        FROM inc_artic_lote a,
                             inc_lote_premi_artic b,
                             inc_premi_artic c,
                             inc_param_nivel_premi d,
                             inc_param_gener_premi e,
                             inc_concu_param_gener f
                       WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                         AND b.prar_oid_prem_arti = c.oid_prem_arti
                         AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                         AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                         AND e.copa_oid_para_gral = f.oid_para_gral
                         and f.oid_para_gral = v_copa_oid_para_gene_devu(i)
                         AND to_number(a.cod_vent_fict) = v_cod_vent_dese(i);

                      UPDATE int_solic_conso_poven_detal
                         SET val_prec_cata_envi            = lv_imp_prec_cata,
                             val_prec_cont_envi            = lv_imp_prec_publ,
                             prod_oid_prod_envi            = lv_prod_oid_prod_envi,
                             copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                             lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                             panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                       WHERE cod_pais = pscodigopais
                         AND cod_peri = v_codperi(i)
                         AND cod_clie = v_codclie(i)
                         AND num_docu = v_numdocu(i)
                         AND tip_refe = v_tiprefe(i)
                         AND sec_nume_docu = v_sec_nume_docu(i)
                         AND cod_vent_dese = v_cod_vent_dese(i);

                      existeenvia := TRUE;

                    EXCEPTION
                      WHEN no_data_found THEN
                        lv_panp_oid_para_nive_prem := NULL;
                        lv_lopa_oid_lote_prem_arti := NULL;
                        lv_copa_oid_para_gral      := NULL;
                        lv_imp_prec_publ           := NULL;
                        lv_imp_prec_cata           := NULL;
                        existeenvia                := FALSE;
                    END;

                    if existeenvia = FALSE then

                        BEGIN

                          SELECT c.panp_oid_para_nive_prem,
                                 a.lopa_oid_lote_prem_arti,
                                 e.copa_oid_para_gral,
                                 decode(dd.oid_reem_arti_lote,
                                        NULL,
                                        a.imp_prec_publ,
                                        dd.imp_prec_publ) AS imp_prec_publ,
                                 0 AS imp_prec_cata,
                                 decode(dd.oid_reem_arti_lote,
                                        NULL,
                                        a.prod_oid_prod,
                                        dd.prod_oid_prod) AS prod_oid_prod
                            INTO lv_panp_oid_para_nive_prem,
                                 lv_lopa_oid_lote_prem_arti,
                                 lv_copa_oid_para_gral,
                                 lv_imp_prec_publ,
                                 lv_imp_prec_cata,
                                 lv_prod_oid_prod_envi
                            FROM inc_artic_lote a,
                                 inc_lote_premi_artic b,
                                 inc_premi_artic c,
                                 inc_param_nivel_premi d,
                                 inc_param_gener_premi e,
                                 inc_concu_param_gener f,
                                 inc_reemp_artic_lote dd
                           WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                             AND b.prar_oid_prem_arti = c.oid_prem_arti
                             AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                             AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                             AND e.copa_oid_para_gral = f.oid_para_gral
                             AND dd.arlo_oid_arti_lote(+) = a.oid_arti_lote
                             and f.oid_para_gral = v_copa_oid_para_gene_devu(i)
                             and to_number(dd.cod_vent_fict) = v_cod_vent_dese(i);


                          UPDATE int_solic_conso_poven_detal
                             SET val_prec_cata_envi            = lv_imp_prec_cata,
                                 val_prec_cont_envi            = lv_imp_prec_publ,
                                 prod_oid_prod_envi            = lv_prod_oid_prod_envi,
                                 copa_oid_para_gene_envi       = lv_copa_oid_para_gral,
                                 lopa_oid_lote_prem_artic_envi = lv_lopa_oid_lote_prem_arti,
                                 panp_oid_para_nive_prem_envi  = lv_panp_oid_para_nive_prem
                           WHERE cod_pais = pscodigopais
                             AND cod_peri = v_codperi(i)
                             AND cod_clie = v_codclie(i)
                             AND num_docu = v_numdocu(i)
                             AND tip_refe = v_tiprefe(i)
                             AND sec_nume_docu = v_sec_nume_docu(i)
                             AND cod_vent_dese = v_cod_vent_dese(i);

                          existeenvia := TRUE;

                        EXCEPTION
                          WHEN no_data_found THEN
                            lv_panp_oid_para_nive_prem := NULL;
                            lv_lopa_oid_lote_prem_arti := NULL;
                            lv_copa_oid_para_gral      := NULL;
                            lv_imp_prec_publ           := NULL;
                            lv_imp_prec_cata           := NULL;
                            existeenvia                := FALSE;
                        END;
                    end if;
                        */

                  ELSE
                    BEGIN
                      --- Query para los premios sin interesar si es el reemplazo
                      SELECT c.panp_oid_para_nive_prem,
                             a.lopa_oid_lote_prem_arti,
                             e.copa_oid_para_gral,
                             a.imp_prec_publ AS imp_prec_publ,
                             0 AS imp_prec_cata,
                             a.prod_oid_prod AS prod_oid_prod
                        INTO lv_panp_oid_para_nive_prem,
                             lv_lopa_oid_lote_prem_arti,
                             lv_copa_oid_para_gral,
                             lv_imp_prec_publ,
                             lv_imp_prec_cata,
                             lv_prod_oid_prod_envi
                        FROM inc_artic_lote a,
                             inc_lote_premi_artic b,
                             inc_premi_artic c,
                             inc_param_nivel_premi d,
                             inc_param_gener_premi e,
                             inc_concu_param_gener f,
                             (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                              MAX(id.oid_arti_lote) oid_arti_lote
                                FROM inc_artic_lote id
                               GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                       WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                         AND a.cod_vent_fict = id.cod_vent_fict
                         AND a.oid_arti_lote = id.oid_arti_lote
                         AND b.prar_oid_prem_arti = c.oid_prem_arti
                         AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                         AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                         AND e.copa_oid_para_gral = f.oid_para_gral
                         AND ((v_cod_oper IN ('AP',
                                                 'PA',
                                                 'FP',
                                                 'PF',
                                                 'TP',
                                                 'PT',
                                                 'CP',
                                                 'PC',
                                                 'SP',
                                                 'PS') AND
                             (v_cod_vent_dese = to_number(a.cod_vent_fict)))
                             /*OR (v_cod_oper(i) NOT IN ('AP',
                                                     'PA',
                                                     'FP',
                                                     'PF',
                                                     'TP',
                                                     'PT',
                                                     'CP',
                                                     'PC',
                                                     'SP',
                                                     'PS') AND
                             (v_cod_vent_devu(i) = to_number(a.cod_vent_fict))) */
                             )
                         AND rownum = 1;

                      existeenvia := TRUE;

                    EXCEPTION
                      WHEN no_data_found THEN
                        BEGIN
                            ----- Query solo para los reemplazos
                            SELECT c.panp_oid_para_nive_prem,
                                   a.lopa_oid_lote_prem_arti,
                                   e.copa_oid_para_gral,
                                   decode(dd.oid_reem_arti_lote,
                                          NULL,
                                          a.imp_prec_publ,
                                          dd.imp_prec_publ) AS imp_prec_publ,
                                   0 AS imp_prec_cata,
                                   decode(dd.oid_reem_arti_lote,
                                          NULL,
                                          a.prod_oid_prod,
                                          dd.prod_oid_prod) AS prod_oid_prod
                              INTO lv_panp_oid_para_nive_prem,
                                   lv_lopa_oid_lote_prem_arti,
                                   lv_copa_oid_para_gral,
                                   lv_imp_prec_publ,
                                   lv_imp_prec_cata,
                                   lv_prod_oid_prod_envi
                              FROM inc_artic_lote a,
                                   inc_lote_premi_artic b,
                                   inc_premi_artic c,
                                   inc_param_nivel_premi d,
                                   inc_param_gener_premi e,
                                   inc_concu_param_gener f,
                                   inc_reemp_artic_lote dd,
                                   (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                                    MAX(id.oid_arti_lote) oid_arti_lote
                                      FROM inc_artic_lote id
                                     GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                             WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                               AND a.cod_vent_fict = id.cod_vent_fict
                               AND a.oid_arti_lote = id.oid_arti_lote
                               AND b.prar_oid_prem_arti = c.oid_prem_arti
                               AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                               AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                               AND e.copa_oid_para_gral = f.oid_para_gral
                               AND dd.arlo_oid_arti_lote(+) = a.oid_arti_lote --- + v_incremento
                               AND ((v_cod_oper IN ('AP',
                                                       'PA',
                                                       'FP',
                                                       'PF',
                                                       'TP',
                                                       'PT',
                                                       'CP',
                                                       'PC',
                                                       'SP',
                                                       'PS') AND
                                   (v_cod_vent_dese = to_number(dd.cod_vent_fict) OR
                                   v_cod_vent_dese = to_number(a.cod_vent_fict)))
                                   /* OR (v_cod_oper(i) NOT IN ('AP',
                                                           'PA',
                                                           'FP',
                                                           'PF',
                                                           'TP',
                                                           'PT',
                                                           'CP',
                                                           'PC',
                                                           'SP',
                                                           'PS') AND
                                   (v_cod_vent_devu(i) = to_number(dd.cod_vent_fict) OR
                                   v_cod_vent_devu(i) = to_number(a.cod_vent_fict))) */
                                   )
                               AND rownum = 1;

                            existeenvia := TRUE;

                          EXCEPTION
                          WHEN no_data_found THEN
                            existeenvia                := FALSE;
                        END;
                    END;

                 END IF;

              END IF;

            END IF;

            existe := existeenvia;

          EXCEPTION
            WHEN OTHERS THEN
              existe := FALSE;
          END;


    RETURN (existe);

  END sto_fn_spvd_repre_desea;

  /***************************************************************************
  Descripcion       : Validacion recuperacion de precios de productos devueltos
  Fecha Creacion    : 28/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
PROCEDURE sto_pr_spvd_repre_devue
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
    ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.cod_vent_devu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.ind_envi_fact,
             det.cod_vent_dese,
             det.cod_prec_envi,
             det.cod_prec,
             det.oid_oper,
             det.ind_devu_fact,
             cab.num_docu_cruc,
             cab.oid_peri_refe,
             det.ind_devu_fisi,
             cab.oid_cabe,
             det.can_vent_devu,
             det.ind_nume_vece_pedi,
             det.oid_soli_posi_devu,
           det.copa_oid_para_gene_devu,
             det.can_prod_dese
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.sec_nume_docu_cabe = cab.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND det.num_lote = cab.num_lote ---- SQA 20101102 se agrega nro lote
       AND ((det.cod_vent_dese IS NOT NULL) OR
           (det.cod_vent_devu IS NOT NULL));

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_ind_envi_fact IS TABLE OF int_solic_conso_poven_detal.ind_envi_fact%TYPE;
    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;
    TYPE t_cod_prec_envi IS TABLE OF int_solic_conso_poven_detal.cod_prec_envi%TYPE;
    TYPE t_cod_prec IS TABLE OF int_solic_conso_poven_detal.cod_prec%TYPE;
    TYPE t_oid_oper IS TABLE OF int_solic_conso_poven_detal.oid_oper%TYPE;
    TYPE t_ind_devu_fact IS TABLE OF int_solic_conso_poven_detal.ind_devu_fact%TYPE;
    TYPE t_num_docu_cruc IS TABLE OF int_solic_conso_poven_cabec.num_docu_cruc%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_ind_devu_fisi IS TABLE OF int_solic_conso_poven_detal.ind_devu_fisi%TYPE;
    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;

    TYPE t_can_vent_devu IS TABLE OF int_solic_conso_poven_detal.can_vent_devu%TYPE;
    TYPE t_can_prod_dese IS TABLE OF int_solic_conso_poven_detal.can_prod_dese%TYPE;

    TYPE t_ind_nume_vece_pedi IS TABLE OF int_solic_conso_poven_detal.ind_nume_vece_pedi%TYPE;
    TYPE t_oid_soli_posi_devu IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
  TYPE t_copa_oid_para_gene_devu IS TABLE OF int_solic_conso_poven_detal.copa_oid_para_gene_devu%TYPE;

    lv_val_prec_cata_unit_loca ped_solic_posic.val_prec_cata_unit_loca%TYPE;
    lv_val_prec_fact_unit_loca ped_solic_posic.val_prec_fact_unit_loca%TYPE;
    lv_val_prec_cont_unit_loca ped_solic_posic.val_prec_cont_unit_loca%TYPE;
    lv_oid_soli_posi           ped_solic_posic.oid_soli_posi%TYPE;

    lv_prod_oid_prod_devu ped_solic_posic.prod_oid_prod%TYPE;

    lv_panp_oid_para_nive_prem  inc_premi_artic.panp_oid_para_nive_prem%TYPE;
    lv_lopa_oid_lote_prem_arti  inc_artic_lote.lopa_oid_lote_prem_arti%TYPE;
    lv_copa_oid_para_gral       inc_param_gener_premi.copa_oid_para_gral%TYPE;
    lv_oid_para_gral            inc_param_gener_premi.copa_oid_para_gral%TYPE;
    lv_imp_prec_publ            inc_artic_lote.imp_prec_publ%TYPE;
    lv_oid_lote_prem_artic_devu inc_artic_lote.lopa_oid_lote_prem_arti%TYPE;
    lv_imp_prec_cata            INTEGER;

    lv_ind_cent_dist_gara      inc_artic_lote.ind_cent_dist_gara%TYPE;
    lv_cese_oid_cese_gara      inc_artic_lote.cese_oid_cese_gara%TYPE;
    lv_num_mese_gara           inc_artic_lote.num_mese_gara%TYPE;
    lv_oid_para_nive_prem_devu inc_premi_artic.panp_oid_para_nive_prem%TYPE;
    lv_copa_oid_para_gene_devu inc_concu_param_gener.oid_para_gral%TYPE;
    lv_tofe_oid_tipo_ofer      pre_tipo_ofert.oid_tipo_ofer%TYPE;
    lv_oid_matr_fact           pre_matri_factu.oid_matr_fact%TYPE;

    lv_modu_oid_modu      ped_solic_cabec.modu_oid_modu%TYPE;
    lv_ofde_oid_deta_ofer pre_ofert_detal.oid_deta_ofer%TYPE;

    v_ind_nume_vece_pedi t_ind_nume_vece_pedi;
    v_oid_soli_posi_devu t_oid_soli_posi_devu;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numdocu       t_numdocu;
    v_tiprefe       t_tiprefe;
    v_cod_vent_devu t_cod_vent_devu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_ind_envi_fact t_ind_envi_fact;
    v_cod_vent_dese t_cod_vent_dese;
    v_cod_prec_envi t_cod_prec_envi;
    v_cod_prec      t_cod_prec;
    v_oid_oper      t_oid_oper;
    v_ind_devu_fact t_ind_devu_fact;
    v_num_docu_cruc t_num_docu_cruc;
    v_oid_peri_refe t_oid_peri_refe;
    v_ind_devu_fisi t_ind_devu_fisi;
    v_oid_cabe      t_oid_cabe;

    v_can_vent_devu t_can_vent_devu;
    v_can_prod_dese t_can_prod_dese;

  v_copa_oid_para_gene_devu t_copa_oid_para_gene_devu;

    lsparametrovalREMP sto_param_gener_occrr.val_param%TYPE;
    v_incremento int_solic_conso_poven_detal.copa_oid_para_gene_devu%TYPE;

    existedevu              BOOLEAN;
    psoidperiparammigra     NUMBER;
    lsparametropermigracion NUMBER;
    lsregistro              VARCHAR2(500) := NULL;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsparametrovalREMP := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_REMP_PRE_DEV');

    if lsparametrovalREMP = 'N' then
       v_incremento := 9000000000;
    else
       v_incremento := 0;
    end if;


    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_tiprefe,
             v_cod_vent_devu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_ind_envi_fact,
             v_cod_vent_dese,
             v_cod_prec_envi,
             v_cod_prec,
             v_oid_oper,
             v_ind_devu_fact,
             v_num_docu_cruc,
             v_oid_peri_refe,
             v_ind_devu_fisi,
             v_oid_cabe,
             v_can_vent_devu,
             v_ind_nume_vece_pedi,
             v_oid_soli_posi_devu,
           v_copa_oid_para_gene_devu,
             v_can_prod_dese LIMIT w_filas;
      IF v_codpais.count > 0 THEN
      FOR i IN v_codpais.first .. v_codpais.last LOOP
        existe                     := FALSE;
          lv_panp_oid_para_nive_prem := NULL;
          lv_lopa_oid_lote_prem_arti := NULL;
          lv_copa_oid_para_gral      := NULL;
          BEGIN
          lsregistro := ' PREIMP. ' || v_numdocu(i) || ' CLIENTE.' ||
                        v_codclie(i);
            existedevu := TRUE;
          IF (v_cod_vent_devu(i) IS NOT NULL AND
             v_can_vent_devu(i) IS NOT NULL) THEN
              -- Si devuelve esta en factura
              IF (v_ind_devu_fact(i) = p_indicador_devuelve_factura) THEN

                -- Si el codigo de venta esta en la matriz de precios
                IF (v_cod_prec(i) = p_indicador_matriz) THEN
                  BEGIN

                    SELECT c.val_prec_cata_unit_loca,
                           decode(c.val_prec_cata_unit_loca,
                                  0,
                                  0,
                                  c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                           c.val_prec_cont_unit_loca,
                           c.oid_soli_posi,
                           c.prod_oid_prod,
                           b.modu_oid_modu,
                           c.ofde_oid_deta_ofer
                      INTO lv_val_prec_cata_unit_loca,
                           lv_val_prec_fact_unit_loca,
                           lv_val_prec_cont_unit_loca,
                           lv_oid_soli_posi,
                           lv_prod_oid_prod_devu,
                           lv_modu_oid_modu,
                           lv_ofde_oid_deta_ofer
                      FROM ped_solic_posic c,
                           (SELECT c.oid_soli_posi,
                                   b.modu_oid_modu,
                                   c.num_unid_aten,
                                 SUM(nvl(d.num_unid_recl, 0)) num_unid_recl,
                                 c.num_unid_aten -
                                 SUM(nvl(d.num_unid_recl, 0)) num_unid_disp
                              FROM ped_solic_cabec       a,
                                   ped_solic_cabec       b,
                                   ped_solic_posic       c,
                                   rec_linea_opera_recla d
                             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                               AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                               AND d.sopo_oid_soli_posi(+) = c.oid_soli_posi
                               AND d.timo_oid_tipo_movi(+) = 2
                               AND a.oid_soli_cabe = v_oid_cabe(i)
                                  /*AND a.val_nume_soli = 1001343985 ---1001343096 */
                             AND to_number(c.val_codi_vent) =
                                 to_number(v_cod_vent_devu(i)) --- 05412
                               AND c.oid_soli_posi = (CASE
                                     WHEN v_ind_nume_vece_pedi(i) > 1 THEN
                                      v_oid_soli_posi_devu(i)
                                     ELSE
                                      c.oid_soli_posi
                                   END)
                               AND c.espo_oid_esta_posi <> 2
                             GROUP BY c.oid_soli_posi,
                                      b.modu_oid_modu,
                                      num_unid_aten
                           ORDER BY num_unid_disp DESC, modu_oid_modu ASC) b
                     WHERE c.oid_soli_posi = b.oid_soli_posi
                       AND rownum = 1
                     ORDER BY c.oid_soli_posi DESC;

                    SELECT d.tofe_oid_tipo_ofer, maf.oid_matr_fact
                      INTO lv_tofe_oid_tipo_ofer, lv_oid_matr_fact
                      FROM pre_ofert_detal d, pre_matri_factu maf
                       WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                         AND d.oid_deta_ofer = lv_ofde_oid_deta_ofer
                       AND to_number(d.val_codi_vent) =
                           to_number(v_cod_vent_devu(i))
                         AND rownum = 1;

                    UPDATE int_solic_conso_poven_detal
                     SET val_prec_cata_devu            = lv_val_prec_fact_unit_loca,
                         val_prec_cont_devu            = lv_val_prec_cont_unit_loca,
                         oid_soli_posi_devu            = lv_oid_soli_posi,
                         prod_oid_prod_devu            = lv_prod_oid_prod_devu,
                         tofe_oid_devu                 = lv_tofe_oid_tipo_ofer,
                         mafa_oid_devu                 = lv_oid_matr_fact,
                           copa_oid_para_gene_devu       = lv_copa_oid_para_gral,
                           lopa_oid_lote_prem_artic_devu = lv_lopa_oid_lote_prem_arti,
                           panp_oid_para_nive_prem_devu  = lv_panp_oid_para_nive_prem
                     WHERE cod_pais = pscodigopais
                       AND cod_peri = v_codperi(i)
                       AND cod_clie = v_codclie(i)
                       AND num_docu = v_numdocu(i)
                       AND tip_refe = v_tiprefe(i)
                       AND sec_nume_docu = v_sec_nume_docu(i)
                       AND cod_vent_devu = v_cod_vent_devu(i);

                    existedevu := TRUE;
                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_val_prec_cata_unit_loca := NULL;
                      lv_val_prec_fact_unit_loca := NULL;
                      lv_val_prec_cont_unit_loca := NULL;
                      lv_oid_soli_posi           := NULL;
                      lv_prod_oid_prod_devu      := NULL;
                      lv_tofe_oid_tipo_ofer      := NULL;
                      lv_oid_matr_fact           := NULL;
                      existedevu                 := FALSE;

                  END;

                  -- Si por el contrario esta en la matriz de incentivos
                ELSE

                  BEGIN

                    lsparametropermigracion := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                                    'STO_PERI_MIGRA');
                    IF (lsparametropermigracion IS NOT NULL) THEN

                      SELECT p.oid_peri
                        INTO psoidperiparammigra
                      FROM cra_perio p, seg_perio_corpo o
                       WHERE o.oid_peri = p.peri_oid_peri
                       AND to_number(o.cod_peri) =
                           to_number(lsparametropermigracion);

                    END IF;

                    -- si periodo de referencia es mayor a periodo migrado
                    IF ((lsparametropermigracion IS NULL) OR
                       (v_oid_peri_refe(i) > psoidperiparammigra)) THEN

                    begin

                      SELECT c.val_prec_cata_unit_loca,
                             decode(c.val_prec_cata_unit_loca,
                                    0,
                                    0,
                                    c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                             c.val_prec_cont_unit_loca,
                             c.oid_soli_posi,
                             decode(dd.oid_reem_arti_lote,
                                    NULL,
                                    d.ind_cent_dist_gara,
                                    dd.ind_cent_dist_gara) AS ind_cent_dist_gara,
                             decode(dd.oid_reem_arti_lote,
                                    NULL,
                                    d.cese_oid_cese_gara,
                                    dd.cese_oid_cese_gara) AS cese_oid_cese_gara,
                             decode(dd.oid_reem_arti_lote,
                                    NULL,
                                    d.num_mese_gara,
                                    dd.num_mese_gara) AS num_mese_gara,
                             decode(dd.oid_reem_arti_lote,
                                    NULL,
                                    d.prod_oid_prod,
                                    dd.prod_oid_prod) AS prod_oid_prod,
                             i.oid_para_gral,
                             d.lopa_oid_lote_prem_arti,
                             f.panp_oid_para_nive_prem
                        INTO lv_val_prec_cata_unit_loca,
                             lv_val_prec_fact_unit_loca,
                             lv_val_prec_cont_unit_loca,
                             lv_oid_soli_posi,
                             lv_ind_cent_dist_gara,
                             lv_cese_oid_cese_gara,
                             lv_num_mese_gara,
                             lv_prod_oid_prod_devu,
                             lv_copa_oid_para_gene_devu,
                             lv_oid_lote_prem_artic_devu,
                             lv_oid_para_nive_prem_devu
                        FROM ped_solic_cabec a,
                             ped_solic_cabec b,
                             ped_solic_posic c,
                             inc_artic_lote d,
                             inc_reemp_artic_lote dd,
                             inc_lote_premi_artic e,
                             inc_premi_artic f,
                             inc_param_nivel_premi g,
                             inc_param_gener_premi h,
                             inc_concu_param_gener i/*,
                             (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                              MAX(id.oid_arti_lote) oid_arti_lote
                                FROM inc_artic_lote id
                               GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio */
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                         AND b.copa_oid_para_gene = i.oid_para_gral
                            --AND e.num_prem = b.num_prem
                         AND d.lopa_oid_lote_prem_arti =
                             e.oid_lote_prem_arti
                         /*AND d.cod_vent_fict = id.cod_vent_fict
                         AND d.oid_arti_lote = id.oid_arti_lote*/
                         AND e.prar_oid_prem_arti = f.oid_prem_arti
                         AND f.panp_oid_para_nive_prem =
                             g.oid_para_nive_prem
                         AND g.pagp_oid_para_gene_prem =
                             h.oid_para_gene_prem
                         AND h.copa_oid_para_gral = i.oid_para_gral
                         AND dd.arlo_oid_arti_lote(+) = d.oid_arti_lote + v_incremento
                         AND a.oid_soli_cabe = v_oid_cabe(i) --43590098--v
                         AND to_number(c.val_codi_vent_fict) =
                             to_number(v_cod_vent_devu(i)) --98698)--
                         AND (d.cod_vent_fict = c.val_codi_vent_fict OR
                             dd.cod_vent_fict = c.val_codi_vent_fict)
                         AND c.oid_soli_posi = (CASE
                               WHEN v_ind_nume_vece_pedi(i) > 1 THEN
                                v_oid_soli_posi_devu(i)
                               ELSE
                                c.oid_soli_posi
                             END)
                         AND c.espo_oid_esta_posi <> 2
                         AND rownum = 1
                       ORDER BY c.oid_soli_posi DESC;
                    exception
                      when no_data_found then
                           begin
                        SELECT c.val_prec_cata_unit_loca,
                               decode(c.val_prec_cata_unit_loca,
                                      0,
                                      0,
                                      c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                               c.val_prec_cont_unit_loca,
                               c.oid_soli_posi,
                               decode(dd.oid_reem_arti_lote,
                                      NULL,
                                      d.ind_cent_dist_gara,
                                      dd.ind_cent_dist_gara) AS ind_cent_dist_gara,
                               decode(dd.oid_reem_arti_lote,
                                      NULL,
                                      d.cese_oid_cese_gara,
                                      dd.cese_oid_cese_gara) AS cese_oid_cese_gara,
                               decode(dd.oid_reem_arti_lote,
                                      NULL,
                                      d.num_mese_gara,
                                      dd.num_mese_gara) AS num_mese_gara,
                               decode(dd.oid_reem_arti_lote,
                                      NULL,
                                      d.prod_oid_prod,
                                      dd.prod_oid_prod) AS prod_oid_prod,
                               i.oid_para_gral,
                               d.lopa_oid_lote_prem_arti,
                               f.panp_oid_para_nive_prem
                          INTO lv_val_prec_cata_unit_loca,
                               lv_val_prec_fact_unit_loca,
                               lv_val_prec_cont_unit_loca,
                               lv_oid_soli_posi,
                               lv_ind_cent_dist_gara,
                               lv_cese_oid_cese_gara,
                               lv_num_mese_gara,
                               lv_prod_oid_prod_devu,
                               lv_copa_oid_para_gene_devu,
                               lv_oid_lote_prem_artic_devu,
                               lv_oid_para_nive_prem_devu
                          FROM ped_solic_cabec a,
                               ped_solic_cabec b,
                               ped_solic_posic c,
                               inc_artic_lote d,
                               inc_reemp_artic_lote dd,
                               inc_lote_premi_artic e,
                               inc_premi_artic f,
                               inc_param_nivel_premi g,
                               inc_param_gener_premi h,
                               inc_concu_param_gener i,
                               (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                                MAX(id.oid_arti_lote) oid_arti_lote
                                  FROM inc_artic_lote id
                                 GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                         WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                           AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                           AND i.oid_para_gral =
                               v_copa_oid_para_gene_devu(i)
                              --AND e.num_prem = b.num_prem
                           AND d.lopa_oid_lote_prem_arti =
                               e.oid_lote_prem_arti
                         AND d.cod_vent_fict = id.cod_vent_fict
                         AND d.oid_arti_lote = id.oid_arti_lote
                         AND e.prar_oid_prem_arti = f.oid_prem_arti
                           AND f.panp_oid_para_nive_prem =
                               g.oid_para_nive_prem
                           AND g.pagp_oid_para_gene_prem =
                               h.oid_para_gene_prem
                         AND h.copa_oid_para_gral = i.oid_para_gral
                         AND dd.arlo_oid_arti_lote(+) = d.oid_arti_lote + v_incremento
                         AND a.oid_soli_cabe = v_oid_cabe(i) --43590098--v
                           AND to_number(c.val_codi_vent_fict) =
                               to_number(v_cod_vent_devu(i)) --98698)--
                         AND (d.cod_vent_fict = c.val_codi_vent_fict OR
                             dd.cod_vent_fict = c.val_codi_vent_fict)
                         AND c.oid_soli_posi = (CASE
                               WHEN v_ind_nume_vece_pedi(i) > 1 THEN
                                v_oid_soli_posi_devu(i)
                               ELSE
                                c.oid_soli_posi
                             END)
                         AND c.espo_oid_esta_posi <> 2
                         AND rownum = 1
                       ORDER BY c.oid_soli_posi DESC;
                       exception
                          when no_data_found then

                              --- Revisa los concursos de un producto y toma el copa debido a que se esta
                              --- despachando en un mismo CDR difentes premios
                              select max(oid_para_gral) into lv_oid_para_gral
                              from inc_artic_lote ial,
                                                  INC_LOTE_PREMI_ARTIC ilp,
                                                  INC_PREMI_ARTIC ipa,
                                                  INC_PARAM_NIVEL_PREMI ipn,
                                                  INC_PARAM_GENER_PREMI ipg,
                                                  inc_concu_param_gener icp
                              where IAL.LOPA_OID_LOTE_PREM_ARTI = ILP.OID_LOTE_PREM_ARTI
                              and ILP.PRAR_OID_PREM_ARTI = IPA.OID_PREM_ARTI
                              and IPA.PANP_OID_PARA_NIVE_PREM = IPN.OID_PARA_NIVE_PREM
                              and IPN.PAGP_OID_PARA_GENE_PREM =  IPG.OID_PARA_GENE_PREM
                              and ipg.copa_oid_para_gral = icp.oid_para_gral
                              and IAL.COD_VENT_FICT = v_cod_vent_devu(i);


                              if (lv_oid_para_gral is null or lv_oid_para_gral = 0) then

                                  select  max(oid_para_gral) into lv_oid_para_gral
                                  from inc_artic_lote ial,
                                                      INC_LOTE_PREMI_ARTIC ilp,
                                                      INC_PREMI_ARTIC ipa,
                                                      INC_PARAM_NIVEL_PREMI ipn,
                                                      INC_PARAM_GENER_PREMI ipg,
                                                      inc_concu_param_gener icp,
                                                      inc_reemp_artic_lote ral
                                  where IAL.LOPA_OID_LOTE_PREM_ARTI = ILP.OID_LOTE_PREM_ARTI
                                  and ILP.PRAR_OID_PREM_ARTI = IPA.OID_PREM_ARTI
                                  and IPA.PANP_OID_PARA_NIVE_PREM = IPN.OID_PARA_NIVE_PREM
                                  and IPN.PAGP_OID_PARA_GENE_PREM =  IPG.OID_PARA_GENE_PREM
                                  and ipg.copa_oid_para_gral = icp.oid_para_gral
                                  and ial.oid_arti_lote = ral.arlo_oid_arti_lote
                                  and RAL.COD_VENT_FICT = v_cod_vent_devu(i);

                              end if;


                              --- Revisa nuevamente con el copa encontrado
                              SELECT c.val_prec_cata_unit_loca,
                                     decode(c.val_prec_cata_unit_loca,
                                            0,
                                            0,
                                            c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                                     c.val_prec_cont_unit_loca,
                                     c.oid_soli_posi,
                                     decode(dd.oid_reem_arti_lote,
                                            NULL,
                                            d.ind_cent_dist_gara,
                                            dd.ind_cent_dist_gara) AS ind_cent_dist_gara,
                                     decode(dd.oid_reem_arti_lote,
                                            NULL,
                                            d.cese_oid_cese_gara,
                                            dd.cese_oid_cese_gara) AS cese_oid_cese_gara,
                                     decode(dd.oid_reem_arti_lote,
                                            NULL,
                                            d.num_mese_gara,
                                            dd.num_mese_gara) AS num_mese_gara,
                                     decode(dd.oid_reem_arti_lote,
                                            NULL,
                                            d.prod_oid_prod,
                                            dd.prod_oid_prod) AS prod_oid_prod,
                                     i.oid_para_gral,
                                     d.lopa_oid_lote_prem_arti,
                                     f.panp_oid_para_nive_prem
                                INTO lv_val_prec_cata_unit_loca,
                                     lv_val_prec_fact_unit_loca,
                                     lv_val_prec_cont_unit_loca,
                                     lv_oid_soli_posi,
                                     lv_ind_cent_dist_gara,
                                     lv_cese_oid_cese_gara,
                                     lv_num_mese_gara,
                                     lv_prod_oid_prod_devu,
                                     lv_copa_oid_para_gene_devu,
                                     lv_oid_lote_prem_artic_devu,
                                     lv_oid_para_nive_prem_devu
                                FROM ped_solic_cabec a,
                                     ped_solic_cabec b,
                                     ped_solic_posic c,
                                     inc_artic_lote d,
                                     inc_reemp_artic_lote dd,
                                     inc_lote_premi_artic e,
                                     inc_premi_artic f,
                                     inc_param_nivel_premi g,
                                     inc_param_gener_premi h,
                                     inc_concu_param_gener i,
                                     (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                                      MAX(id.oid_arti_lote) oid_arti_lote
                                        FROM inc_artic_lote id
                                       GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                               WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                                 AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                                 AND i.oid_para_gral = lv_oid_para_gral
                                    --AND e.num_prem = b.num_prem
                                 AND d.lopa_oid_lote_prem_arti =
                                     e.oid_lote_prem_arti
                               AND d.cod_vent_fict = id.cod_vent_fict
                               AND d.oid_arti_lote = id.oid_arti_lote
                               AND e.prar_oid_prem_arti = f.oid_prem_arti
                                 AND f.panp_oid_para_nive_prem =
                                     g.oid_para_nive_prem
                                 AND g.pagp_oid_para_gene_prem =
                                     h.oid_para_gene_prem
                               AND h.copa_oid_para_gral = i.oid_para_gral
                               AND dd.arlo_oid_arti_lote(+) = d.oid_arti_lote ----+ v_incremento
                               AND a.oid_soli_cabe = v_oid_cabe(i) --43590098--v
                                 AND to_number(c.val_codi_vent_fict) =
                                     to_number(v_cod_vent_devu(i)) --98698)--
                               AND (d.cod_vent_fict = c.val_codi_vent_fict OR
                                   dd.cod_vent_fict = c.val_codi_vent_fict)
                               AND c.oid_soli_posi = (CASE
                                     WHEN v_ind_nume_vece_pedi(i) > 1 THEN
                                      v_oid_soli_posi_devu(i)
                                     ELSE
                                      c.oid_soli_posi
                                   END)
                               AND c.espo_oid_esta_posi <> 2
                               AND rownum = 1
                             ORDER BY c.oid_soli_posi DESC;

                       end;
                    end;

                    ELSE
                      -- de otro modo, si el periodo de referencia es menor o igual al migrado
                      SELECT t.val_prec_cata_unit_loca,
                             t.val_prec_fact_unit_loca,
                             t.val_prec_cont_unit_loca,
                             t.oid_soli_posi,
                             t.ind_cent_dist_gara,
                             t.cese_oid_cese_gara,
                             t.num_mese_gara,
                             t.prod_oid_prod,
                             t.oid_para_gral,
                             t.lopa_oid_lote_prem_arti,
                             t.panp_oid_para_nive_prem
                        INTO lv_val_prec_cata_unit_loca,
                             lv_val_prec_fact_unit_loca,
                             lv_val_prec_cont_unit_loca,
                             lv_oid_soli_posi,
                             lv_ind_cent_dist_gara,
                             lv_cese_oid_cese_gara,
                             lv_num_mese_gara,
                             lv_prod_oid_prod_devu,
                             lv_copa_oid_para_gene_devu,
                             lv_oid_lote_prem_artic_devu,
                             lv_oid_para_nive_prem_devu
                        FROM (SELECT 0                         AS val_prec_cata_unit_loca,
                                     0                         AS val_prec_fact_unit_loca,
                                     d.imp_prec_publ           val_prec_cont_unit_loca,
                                     NULL                      AS oid_soli_posi,
                                     d.ind_cent_dist_gara,
                                     d.cese_oid_cese_gara,
                                     d.num_mese_gara,
                                     d.prod_oid_prod,
                                     i.oid_para_gral,
                                     d.lopa_oid_lote_prem_arti,
                                     f.panp_oid_para_nive_prem
                                FROM inc_artic_lote d,
                                     inc_lote_premi_artic e,
                                     inc_premi_artic f,
                                     inc_param_nivel_premi g,
                                     inc_param_gener_premi h,
                                     inc_concu_param_gener i,
                                     (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                                      MAX(id.oid_arti_lote) oid_arti_lote
                                        FROM inc_artic_lote id
                                       GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                             WHERE d.lopa_oid_lote_prem_arti =
                                   e.oid_lote_prem_arti
                                 AND d.cod_vent_fict = id.cod_vent_fict
                                 AND d.oid_arti_lote = id.oid_arti_lote
                                 AND e.prar_oid_prem_arti = f.oid_prem_arti
                               AND f.panp_oid_para_nive_prem =
                                   g.oid_para_nive_prem
                               AND g.pagp_oid_para_gene_prem =
                                   h.oid_para_gene_prem
                                 AND h.copa_oid_para_gral = i.oid_para_gral
                               AND to_number(d.cod_vent_fict) =
                                   to_number(v_cod_vent_devu(i))
                              UNION
                              SELECT 0                         AS val_prec_cata_unit_loca,
                                     0                         AS val_prec_fact_unit_loca,
                                     r.imp_prec_publ           val_prec_cont_unit_loca,
                                     NULL                      AS oid_soli_posi,
                                     r.ind_cent_dist_gara,
                                     r.cese_oid_cese_gara,
                                     r.num_mese_gara,
                                     r.prod_oid_prod,
                                     i.oid_para_gral,
                                     d.lopa_oid_lote_prem_arti,
                                     f.panp_oid_para_nive_prem
                                FROM inc_reemp_artic_lote r,
                                     inc_artic_lote d,
                                     inc_lote_premi_artic e,
                                     inc_premi_artic f,
                                     inc_param_nivel_premi g,
                                     inc_param_gener_premi h,
                                     inc_concu_param_gener i,
                                     (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                                      MAX(id.oid_arti_lote) oid_arti_lote
                                        FROM inc_artic_lote id
                                       GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                               WHERE r.arlo_oid_arti_lote = d.oid_arti_lote
                               AND d.lopa_oid_lote_prem_arti =
                                   e.oid_lote_prem_arti
                                 AND d.cod_vent_fict = id.cod_vent_fict
                                 AND d.oid_arti_lote = id.oid_arti_lote
                                 AND e.prar_oid_prem_arti = f.oid_prem_arti
                               AND f.panp_oid_para_nive_prem =
                                   g.oid_para_nive_prem
                               AND g.pagp_oid_para_gene_prem =
                                   h.oid_para_gene_prem
                                 AND h.copa_oid_para_gral = i.oid_para_gral
                               AND to_number(d.cod_vent_fict) =
                                   to_number(v_cod_vent_devu(i))) t
                       WHERE rownum = 1;

                    END IF;
                    -- fin si

                    UPDATE int_solic_conso_poven_detal
                       SET val_prec_cata_devu            = '0',
                           val_prec_cont_devu            = lv_val_prec_cont_unit_loca,
                           oid_soli_posi_devu            = lv_oid_soli_posi,
                           ind_cent_gara                 = lv_ind_cent_dist_gara,
                           cese_oid_cese_gara            = lv_cese_oid_cese_gara,
                           num_mes_gara                  = lv_num_mese_gara,
                           prod_oid_prod_devu            = lv_prod_oid_prod_devu,
                           copa_oid_para_gene_devu       = lv_copa_oid_para_gene_devu,
                           lopa_oid_lote_prem_artic_devu = lv_oid_lote_prem_artic_devu,
                           panp_oid_para_nive_prem_devu  = lv_oid_para_nive_prem_devu
                     WHERE cod_pais = pscodigopais
                       AND cod_peri = v_codperi(i)
                       AND cod_clie = v_codclie(i)
                       AND num_docu = v_numdocu(i)
                       AND tip_refe = v_tiprefe(i)
                       AND sec_nume_docu = v_sec_nume_docu(i)
                       AND cod_vent_devu = v_cod_vent_devu(i);
                    existedevu := TRUE;

                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_val_prec_cata_unit_loca  := NULL;
                      lv_val_prec_fact_unit_loca  := NULL;
                      lv_val_prec_cont_unit_loca  := NULL;
                      lv_oid_soli_posi            := NULL;
                      lv_ind_cent_dist_gara       := NULL;
                      lv_cese_oid_cese_gara       := NULL;
                      lv_num_mese_gara            := NULL;
                      lv_prod_oid_prod_devu       := NULL;
                      lv_copa_oid_para_gene_devu  := NULL;
                      lv_oid_lote_prem_artic_devu := NULL;
                      lv_oid_para_nive_prem_devu  := NULL;
                      existedevu                  := FALSE;
                  END;

                END IF;

                -- Si el devuelve no esta en factura
              ELSE

                -- Si el codigo esta en la matriz de precios
                IF (v_cod_prec(i) = p_indicador_matriz) THEN
                  BEGIN
                    SELECT d.tofe_oid_tipo_ofer,
                           maf.oid_matr_fact,
                           d.prod_oid_prod
                      INTO lv_tofe_oid_tipo_ofer,
                           lv_oid_matr_fact,
                           lv_prod_oid_prod_devu
                      FROM pre_ofert_detal       d,
                           pre_matri_factu       maf,
                           pre_matri_factu_cabec cab
                     WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                       AND cab.oid_cabe = maf.mfca_oid_cabe
                       AND cab.perd_oid_peri = v_oid_peri_refe(i)
                     AND to_number(d.val_codi_vent) =
                         to_number(v_cod_vent_devu(i));

                    UPDATE int_solic_conso_poven_detal
                     SET val_prec_cata_devu            = 0,
                         val_prec_cont_devu            = 0,
                         prod_oid_prod_devu            = lv_prod_oid_prod_devu,
                         tofe_oid_devu                 = lv_tofe_oid_tipo_ofer,
                         mafa_oid_devu                 = lv_oid_matr_fact,
                           copa_oid_para_gene_devu       = lv_copa_oid_para_gral,
                           lopa_oid_lote_prem_artic_devu = lv_lopa_oid_lote_prem_arti,
                           panp_oid_para_nive_prem_devu  = lv_panp_oid_para_nive_prem
                     WHERE cod_pais = pscodigopais
                       AND cod_peri = v_codperi(i)
                       AND cod_clie = v_codclie(i)
                       AND num_docu = v_numdocu(i)
                       AND tip_refe = v_tiprefe(i)
                       AND sec_nume_docu = v_sec_nume_docu(i)
                       AND cod_vent_devu = v_cod_vent_devu(i);
                    existedevu := TRUE;
                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_tofe_oid_tipo_ofer := NULL;
                      lv_oid_matr_fact      := NULL;
                      lv_prod_oid_prod_devu := NULL;
                      existedevu            := FALSE;
                  END;

                  -- Si esta en la matriz de incentivos
                ELSE
                  BEGIN
                    SELECT c.panp_oid_para_nive_prem,
                           a.lopa_oid_lote_prem_arti,
                           e.copa_oid_para_gral,
                           a.imp_prec_publ,
                           /*decode(dd.oid_reem_arti_lote,
                                  NULL,
                                  a.imp_prec_publ,
                                  dd.imp_prec_publ) AS imp_prec_publ,*/
                           0 AS imp_prec_cata,
                           /*decode(dd.oid_reem_arti_lote,
                                  NULL,
                                  a.prod_oid_prod,
                                  dd.prod_oid_prod) AS prod_oid_prod*/
                           a.prod_oid_prod
                      INTO lv_panp_oid_para_nive_prem,
                           lv_lopa_oid_lote_prem_arti,
                           lv_copa_oid_para_gral,
                           lv_imp_prec_publ,
                           lv_imp_prec_cata,
                           lv_prod_oid_prod_devu
                      FROM inc_artic_lote a,
                           inc_lote_premi_artic b,
                           inc_premi_artic c,
                           inc_param_nivel_premi d,
                           inc_param_gener_premi e,
                           inc_concu_param_gener f,
                           ---inc_reemp_artic_lote dd,
                           (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                            MAX(id.oid_arti_lote) oid_arti_lote
                              FROM inc_artic_lote id
                             GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                     WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                       AND a.cod_vent_fict = id.cod_vent_fict
                       AND a.oid_arti_lote = id.oid_arti_lote
                       AND b.prar_oid_prem_arti = c.oid_prem_arti
                       AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                       AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                       AND e.copa_oid_para_gral = f.oid_para_gral
                       ---AND dd.arlo_oid_arti_lote(+) = a.oid_arti_lote
                     AND (to_number(a.cod_vent_fict) =
                         to_number(v_cod_vent_devu(i))
                         /*OR
                         to_number(dd.cod_vent_fict) =
                         to_number(v_cod_vent_devu(i))*/
                         )
                       AND rownum = 1;

                    UPDATE int_solic_conso_poven_detal
                       SET val_prec_cata_devu            = lv_imp_prec_cata,
                           val_prec_cont_devu            = lv_imp_prec_publ,
                           prod_oid_prod_devu            = lv_prod_oid_prod_devu,
                           copa_oid_para_gene_devu       = lv_copa_oid_para_gral,
                           lopa_oid_lote_prem_artic_devu = lv_lopa_oid_lote_prem_arti,
                           panp_oid_para_nive_prem_devu  = lv_panp_oid_para_nive_prem
                     WHERE cod_pais = pscodigopais
                       AND cod_peri = v_codperi(i)
                       AND cod_clie = v_codclie(i)
                       AND num_docu = v_numdocu(i)
                       AND tip_refe = v_tiprefe(i)
                       AND sec_nume_docu = v_sec_nume_docu(i)
                       AND cod_vent_devu = v_cod_vent_devu(i);
                    existedevu := TRUE;
                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_panp_oid_para_nive_prem := NULL;
                      lv_lopa_oid_lote_prem_arti := NULL;
                      lv_copa_oid_para_gral      := NULL;
                      lv_imp_prec_publ           := NULL;
                      lv_imp_prec_cata           := NULL;
                      lv_prod_oid_prod_devu      := NULL;
                      existedevu                 := FALSE;
                  END;
                END IF;
              END IF;

              IF (v_ind_devu_fisi(i) = 0) THEN
                BEGIN
                  SELECT d.tofe_oid_tipo_ofer,
                         maf.oid_matr_fact,
                         d.prod_oid_prod
                    INTO lv_tofe_oid_tipo_ofer,
                         lv_oid_matr_fact,
                         lv_prod_oid_prod_devu
                    FROM pre_ofert_detal       d,
                         pre_matri_factu       maf,
                         pre_matri_factu_cabec cab
                   WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                     AND cab.oid_cabe = maf.mfca_oid_cabe
                     AND cab.perd_oid_peri = v_oid_peri_refe(i)
                   AND to_number(d.val_codi_vent) =
                       to_number(v_cod_vent_devu(i));

                  UPDATE int_solic_conso_poven_detal
                     SET val_prec_cata_devu = 0,
                         val_prec_cont_devu = 0,
                         oid_soli_posi_devu = NULL,
                         prod_oid_prod_devu = lv_prod_oid_prod_devu,
                         tofe_oid_devu      = lv_tofe_oid_tipo_ofer,
                         mafa_oid_devu      = lv_oid_matr_fact
                   WHERE cod_pais = pscodigopais
                     AND cod_peri = v_codperi(i)
                     AND cod_clie = v_codclie(i)
                     AND num_docu = v_numdocu(i)
                     AND tip_refe = v_tiprefe(i)
                     AND sec_nume_docu = v_sec_nume_docu(i)
                     AND cod_vent_devu = v_cod_vent_devu(i);

                  existedevu := TRUE;

                EXCEPTION
                  WHEN no_data_found THEN
                    lv_tofe_oid_tipo_ofer := NULL;
                    lv_oid_matr_fact      := NULL;
                    lv_prod_oid_prod_devu := NULL;
                    existedevu            := FALSE;
                END;
              END IF;

            END IF;

            IF (v_cod_vent_devu(i) IS NOT NULL AND v_can_vent_devu(i) IS NULL) THEN
              existedevu := FALSE;
            END IF;

            existe := existedevu;

          EXCEPTION
            WHEN OTHERS THEN
              existe := FALSE;

              ln_sqlcode := SQLCODE;
            ls_sqlerrm := substr(SQLERRM, 1, 250);
              raise_application_error(-20123,
                                    'ERROR STO_PR_SPVD_REPRE_DEVUE: ' ||
                                    ls_sqlerrm || lsregistro);
          END;
          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                            'ERROR STO_PR_SPVD_REPRE_DEVUE: ' || ls_sqlerrm ||
                            lsregistro);

  END sto_pr_spvd_repre_devue;

  /***************************************************************************
  Descripcion       : Validacion recuperacion de precios de productos devueltos
  Fecha Creacion    : 28/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
FUNCTION sto_fn_spvd_repre_devue
  (
     pscodigopais   in VARCHAR2,
     psOidPeriCDR   in number,
     pscodigoVenta  in VARCHAR2,
     psCodOperSICC  in VARCHAR2,
     psTipoOperSICC in VARCHAR2,
     psOidSoliCabe  in number
    ) RETURN BOOLEAN  IS



    v_cod_vent_devu            int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    v_oid_peri_refe            int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    v_oid_cabe                 int_solic_conso_poven_cabec.oid_cabe%TYPE;
    v_ind_devu_fisi            int_solic_conso_poven_detal.ind_devu_fisi%TYPE;
    v_cod_prec                 int_solic_conso_poven_detal.cod_prec%TYPE;
    v_ind_devu_fact            int_solic_conso_poven_detal.ind_devu_fact%TYPE;


    lv_val_prec_cata_unit_loca ped_solic_posic.val_prec_cata_unit_loca%TYPE;
    lv_val_prec_fact_unit_loca ped_solic_posic.val_prec_fact_unit_loca%TYPE;
    lv_val_prec_cont_unit_loca ped_solic_posic.val_prec_cont_unit_loca%TYPE;
    lv_oid_soli_posi           ped_solic_posic.oid_soli_posi%TYPE;

    lv_prod_oid_prod_devu ped_solic_posic.prod_oid_prod%TYPE;

    lv_panp_oid_para_nive_prem  inc_premi_artic.panp_oid_para_nive_prem%TYPE;
    lv_lopa_oid_lote_prem_arti  inc_artic_lote.lopa_oid_lote_prem_arti%TYPE;
    lv_copa_oid_para_gral       inc_param_gener_premi.copa_oid_para_gral%TYPE;
    lv_oid_para_gral            inc_param_gener_premi.copa_oid_para_gral%TYPE;
    lv_imp_prec_publ            inc_artic_lote.imp_prec_publ%TYPE;
    lv_oid_lote_prem_artic_devu inc_artic_lote.lopa_oid_lote_prem_arti%TYPE;
    lv_imp_prec_cata            INTEGER;

    lv_ind_cent_dist_gara      inc_artic_lote.ind_cent_dist_gara%TYPE;
    lv_cese_oid_cese_gara      inc_artic_lote.cese_oid_cese_gara%TYPE;
    lv_num_mese_gara           inc_artic_lote.num_mese_gara%TYPE;
    lv_oid_para_nive_prem_devu inc_premi_artic.panp_oid_para_nive_prem%TYPE;
    lv_copa_oid_para_gene_devu inc_concu_param_gener.oid_para_gral%TYPE;
    lv_tofe_oid_tipo_ofer      pre_tipo_ofert.oid_tipo_ofer%TYPE;
    lv_oid_matr_fact           pre_matri_factu.oid_matr_fact%TYPE;

    lv_modu_oid_modu      ped_solic_cabec.modu_oid_modu%TYPE;
    lv_ofde_oid_deta_ofer pre_ofert_detal.oid_deta_ofer%TYPE;

    existedevu              BOOLEAN;
    psoidperiparammigra     NUMBER;
    lsparametropermigracion NUMBER;
    lsregistro              VARCHAR2(500) := NULL;

    lsparametrovalREMP sto_param_gener_occrr.val_param%TYPE;
    v_incremento int_solic_conso_poven_detal.copa_oid_para_gene_devu%TYPE;


  BEGIN


    lsparametrovalREMP := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_REMP_PRE_ENV');

    if lsparametrovalREMP = 'N' then
       v_incremento := 9000000000;
    else
       v_incremento := 0;
    end if;

    v_cod_vent_devu := pscodigoVenta;
    v_oid_peri_refe := psOidPeriCDR;
    v_oid_cabe      := psOidSoliCabe;

        existe                     := FALSE;

          lv_panp_oid_para_nive_prem := NULL;
          lv_lopa_oid_lote_prem_arti := NULL;
          lv_copa_oid_para_gral      := NULL;
          BEGIN

          existedevu := TRUE;
          IF (v_cod_vent_devu IS NOT NULL ) THEN

            SELECT ra.ind_devu_fisi_fact,ra.ind_devu_esta_fact,ra.cod_prec
              INTO v_ind_devu_fisi,v_ind_devu_fact,v_cod_prec
              FROM (SELECT a.cod_oper,
                           b.val_tipo_oper,
                           a.ind_devu_fisi_fact,
                           b.ind_devu_esta_fact,
                           d.cod_prec
                      FROM rec_opera       a,
                           rec_tipos_opera b,
                           rec_preci_envia c,
                           rec_preci       d
                     WHERE a.oid_oper = b.rope_oid_oper
                       AND a.penv_oid_precio_envia = c.oid_prec_envi
                       AND a.peci_oid_peci = d.oid_prec
                       AND a.cod_oper = psCodOperSICC
                       AND b.val_tipo_oper = psTipoOperSICC) ra,
                   rec_param_opera e
             WHERE ra.cod_oper = e.cod_oper(+)
               AND ra.val_tipo_oper = e.cod_tipo_oper(+);

              -- Si devuelve esta en factura
              IF (v_ind_devu_fact = p_indicador_devuelve_factura) THEN

                -- Si el codigo de venta esta en la matriz de precios
                IF (v_cod_prec = p_indicador_matriz) THEN
                  BEGIN

                    SELECT c.val_prec_cata_unit_loca,
                           decode(c.val_prec_cata_unit_loca,
                                  0,
                                  0,
                                  c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                           c.val_prec_cont_unit_loca,
                           c.oid_soli_posi,
                           c.prod_oid_prod,
                           b.modu_oid_modu,
                           c.ofde_oid_deta_ofer
                      INTO lv_val_prec_cata_unit_loca,
                           lv_val_prec_fact_unit_loca,
                           lv_val_prec_cont_unit_loca,
                           lv_oid_soli_posi,
                           lv_prod_oid_prod_devu,
                           lv_modu_oid_modu,
                           lv_ofde_oid_deta_ofer
                      FROM ped_solic_posic c,
                           (SELECT c.oid_soli_posi,
                                   b.modu_oid_modu,
                                   c.num_unid_aten,
                                 SUM(nvl(d.num_unid_recl, 0)) num_unid_recl,
                                 c.num_unid_aten -
                                 SUM(nvl(d.num_unid_recl, 0)) num_unid_disp
                              FROM ped_solic_cabec       a,
                                   ped_solic_cabec       b,
                                   ped_solic_posic       c,
                                   rec_linea_opera_recla d
                             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                               AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                               AND d.sopo_oid_soli_posi(+) = c.oid_soli_posi
                               AND d.timo_oid_tipo_movi(+) = 2
                               AND a.oid_soli_cabe = v_oid_cabe
                                  /*AND a.val_nume_soli = 1001343985 ---1001343096 */
                             AND to_number(c.val_codi_vent) =
                                 to_number(v_cod_vent_devu) --- 05412
                               AND c.oid_soli_posi = c.oid_soli_posi
                               AND c.espo_oid_esta_posi <> 2
                             GROUP BY c.oid_soli_posi,
                                      b.modu_oid_modu,
                                      num_unid_aten
                           ORDER BY num_unid_disp DESC, modu_oid_modu ASC) b
                     WHERE c.oid_soli_posi = b.oid_soli_posi
                       AND rownum = 1
                     ORDER BY c.oid_soli_posi DESC;


                    SELECT d.tofe_oid_tipo_ofer, maf.oid_matr_fact
                      INTO lv_tofe_oid_tipo_ofer, lv_oid_matr_fact
                      FROM pre_ofert_detal d, pre_matri_factu maf
                       WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                         AND d.oid_deta_ofer = lv_ofde_oid_deta_ofer
                       AND to_number(d.val_codi_vent) =
                           to_number(v_cod_vent_devu)
                         AND rownum = 1;

                    existedevu := TRUE;
                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_val_prec_cata_unit_loca := NULL;
                      lv_val_prec_fact_unit_loca := NULL;
                      lv_val_prec_cont_unit_loca := NULL;
                      lv_oid_soli_posi           := NULL;
                      lv_prod_oid_prod_devu      := NULL;
                      lv_tofe_oid_tipo_ofer      := NULL;
                      lv_oid_matr_fact           := NULL;
                      existedevu                 := FALSE;

                  END;

                  -- Si por el contrario esta en la matriz de incentivos
                ELSE

                  BEGIN

                    lsparametropermigracion := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                                    'STO_PERI_MIGRA');
                    IF (lsparametropermigracion IS NOT NULL) THEN

                      SELECT p.oid_peri
                        INTO psoidperiparammigra
                      FROM cra_perio p, seg_perio_corpo o
                       WHERE o.oid_peri = p.peri_oid_peri
                       AND to_number(o.cod_peri) =
                           to_number(lsparametropermigracion);

                    END IF;

                    -- si periodo de referencia es mayor a periodo migrado
                    IF ((lsparametropermigracion IS NULL) OR
                       (v_oid_peri_refe > psoidperiparammigra)) THEN

                    begin

                      SELECT c.val_prec_cata_unit_loca,
                             decode(c.val_prec_cata_unit_loca,
                                    0,
                                    0,
                                    c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                             c.val_prec_cont_unit_loca,
                             c.oid_soli_posi,
                             decode(dd.oid_reem_arti_lote,
                                    NULL,
                                    d.ind_cent_dist_gara,
                                    dd.ind_cent_dist_gara) AS ind_cent_dist_gara,
                             decode(dd.oid_reem_arti_lote,
                                    NULL,
                                    d.cese_oid_cese_gara,
                                    dd.cese_oid_cese_gara) AS cese_oid_cese_gara,
                             decode(dd.oid_reem_arti_lote,
                                    NULL,
                                    d.num_mese_gara,
                                    dd.num_mese_gara) AS num_mese_gara,
                             decode(dd.oid_reem_arti_lote,
                                    NULL,
                                    d.prod_oid_prod,
                                    dd.prod_oid_prod) AS prod_oid_prod,
                             i.oid_para_gral,
                             d.lopa_oid_lote_prem_arti,
                             f.panp_oid_para_nive_prem
                        INTO lv_val_prec_cata_unit_loca,
                             lv_val_prec_fact_unit_loca,
                             lv_val_prec_cont_unit_loca,
                             lv_oid_soli_posi,
                             lv_ind_cent_dist_gara,
                             lv_cese_oid_cese_gara,
                             lv_num_mese_gara,
                             lv_prod_oid_prod_devu,
                             lv_copa_oid_para_gene_devu,
                             lv_oid_lote_prem_artic_devu,
                             lv_oid_para_nive_prem_devu
                        FROM ped_solic_cabec a,
                             ped_solic_cabec b,
                             ped_solic_posic c,
                             inc_artic_lote d,
                             inc_reemp_artic_lote dd,
                             inc_lote_premi_artic e,
                             inc_premi_artic f,
                             inc_param_nivel_premi g,
                             inc_param_gener_premi h,
                             inc_concu_param_gener i
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                         AND b.copa_oid_para_gene = i.oid_para_gral
                         AND d.lopa_oid_lote_prem_arti =
                             e.oid_lote_prem_arti
                         AND e.prar_oid_prem_arti = f.oid_prem_arti
                         AND f.panp_oid_para_nive_prem =
                             g.oid_para_nive_prem
                         AND g.pagp_oid_para_gene_prem =
                             h.oid_para_gene_prem
                         AND h.copa_oid_para_gral = i.oid_para_gral
                         AND dd.arlo_oid_arti_lote(+) = d.oid_arti_lote + v_incremento
                         AND a.oid_soli_cabe = v_oid_cabe --43590098--v
                         AND to_number(c.val_codi_vent_fict) =
                             to_number(v_cod_vent_devu) --98698)--
                         AND (d.cod_vent_fict = c.val_codi_vent_fict OR
                             dd.cod_vent_fict = c.val_codi_vent_fict)
                         AND c.espo_oid_esta_posi <> 2
                         AND rownum = 1
                       ORDER BY c.oid_soli_posi DESC;
                    exception
                      when no_data_found then
                           begin
                        SELECT c.val_prec_cata_unit_loca,
                               decode(c.val_prec_cata_unit_loca,
                                      0,
                                      0,
                                      c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                               c.val_prec_cont_unit_loca,
                               c.oid_soli_posi,
                               decode(dd.oid_reem_arti_lote,
                                      NULL,
                                      d.ind_cent_dist_gara,
                                      dd.ind_cent_dist_gara) AS ind_cent_dist_gara,
                               decode(dd.oid_reem_arti_lote,
                                      NULL,
                                      d.cese_oid_cese_gara,
                                      dd.cese_oid_cese_gara) AS cese_oid_cese_gara,
                               decode(dd.oid_reem_arti_lote,
                                      NULL,
                                      d.num_mese_gara,
                                      dd.num_mese_gara) AS num_mese_gara,
                               decode(dd.oid_reem_arti_lote,
                                      NULL,
                                      d.prod_oid_prod,
                                      dd.prod_oid_prod) AS prod_oid_prod,
                               i.oid_para_gral,
                               d.lopa_oid_lote_prem_arti,
                               f.panp_oid_para_nive_prem
                          INTO lv_val_prec_cata_unit_loca,
                               lv_val_prec_fact_unit_loca,
                               lv_val_prec_cont_unit_loca,
                               lv_oid_soli_posi,
                               lv_ind_cent_dist_gara,
                               lv_cese_oid_cese_gara,
                               lv_num_mese_gara,
                               lv_prod_oid_prod_devu,
                               lv_copa_oid_para_gene_devu,
                               lv_oid_lote_prem_artic_devu,
                               lv_oid_para_nive_prem_devu
                          FROM ped_solic_cabec a,
                               ped_solic_cabec b,
                               ped_solic_posic c,
                               inc_artic_lote d,
                               inc_reemp_artic_lote dd,
                               inc_lote_premi_artic e,
                               inc_premi_artic f,
                               inc_param_nivel_premi g,
                               inc_param_gener_premi h,
                               inc_concu_param_gener i,
                               (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                                MAX(id.oid_arti_lote) oid_arti_lote
                                  FROM inc_artic_lote id
                                 GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                         WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                           AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                           ---AND i.oid_para_gral =
                               ---v_copa_oid_para_gene_devu(i)
                              --AND e.num_prem = b.num_prem
                           AND d.lopa_oid_lote_prem_arti =
                               e.oid_lote_prem_arti
                         AND d.cod_vent_fict = id.cod_vent_fict
                         AND d.oid_arti_lote = id.oid_arti_lote
                         AND e.prar_oid_prem_arti = f.oid_prem_arti
                           AND f.panp_oid_para_nive_prem =
                               g.oid_para_nive_prem
                           AND g.pagp_oid_para_gene_prem =
                               h.oid_para_gene_prem
                         AND h.copa_oid_para_gral = i.oid_para_gral
                         AND dd.arlo_oid_arti_lote(+) = d.oid_arti_lote + v_incremento
                         AND a.oid_soli_cabe = v_oid_cabe --43590098--v
                           AND to_number(c.val_codi_vent_fict) =
                               to_number(v_cod_vent_devu) --98698)--
                         AND (d.cod_vent_fict = c.val_codi_vent_fict OR
                             dd.cod_vent_fict = c.val_codi_vent_fict)
                         AND c.espo_oid_esta_posi <> 2
                         AND rownum = 1
                       ORDER BY c.oid_soli_posi DESC;
                       exception
                          when no_data_found then

                              --- Revisa los concursos de un producto y toma el copa debido a que se esta
                              --- despachando en un mismo CDR difentes premios
                              select max(oid_para_gral) into lv_oid_para_gral
                              from inc_artic_lote ial,
                                                  INC_LOTE_PREMI_ARTIC ilp,
                                                  INC_PREMI_ARTIC ipa,
                                                  INC_PARAM_NIVEL_PREMI ipn,
                                                  INC_PARAM_GENER_PREMI ipg,
                                                  inc_concu_param_gener icp
                              where IAL.LOPA_OID_LOTE_PREM_ARTI = ILP.OID_LOTE_PREM_ARTI
                              and ILP.PRAR_OID_PREM_ARTI = IPA.OID_PREM_ARTI
                              and IPA.PANP_OID_PARA_NIVE_PREM = IPN.OID_PARA_NIVE_PREM
                              and IPN.PAGP_OID_PARA_GENE_PREM =  IPG.OID_PARA_GENE_PREM
                              and ipg.copa_oid_para_gral = icp.oid_para_gral
                              and IAL.COD_VENT_FICT = v_cod_vent_devu;

                              if (lv_oid_para_gral is null or lv_oid_para_gral = 0) then

                                  select  max(oid_para_gral) into lv_oid_para_gral
                                  from inc_artic_lote ial,
                                                      INC_LOTE_PREMI_ARTIC ilp,
                                                      INC_PREMI_ARTIC ipa,
                                                      INC_PARAM_NIVEL_PREMI ipn,
                                                      INC_PARAM_GENER_PREMI ipg,
                                                      inc_concu_param_gener icp,
                                                      inc_reemp_artic_lote ral
                                  where IAL.LOPA_OID_LOTE_PREM_ARTI = ILP.OID_LOTE_PREM_ARTI
                                  and ILP.PRAR_OID_PREM_ARTI = IPA.OID_PREM_ARTI
                                  and IPA.PANP_OID_PARA_NIVE_PREM = IPN.OID_PARA_NIVE_PREM
                                  and IPN.PAGP_OID_PARA_GENE_PREM =  IPG.OID_PARA_GENE_PREM
                                  and ipg.copa_oid_para_gral = icp.oid_para_gral
                                  and ial.oid_arti_lote = ral.arlo_oid_arti_lote
                                  and RAL.COD_VENT_FICT = v_cod_vent_devu;

                              end if;

                              --- Revisa nuevamente con el copa encontrado
                              SELECT c.val_prec_cata_unit_loca,
                                     decode(c.val_prec_cata_unit_loca,
                                            0,
                                            0,
                                            c.val_prec_fact_unit_loca) val_prec_fact_unit_loca,
                                     c.val_prec_cont_unit_loca,
                                     c.oid_soli_posi,
                                     decode(dd.oid_reem_arti_lote,
                                            NULL,
                                            d.ind_cent_dist_gara,
                                            dd.ind_cent_dist_gara) AS ind_cent_dist_gara,
                                     decode(dd.oid_reem_arti_lote,
                                            NULL,
                                            d.cese_oid_cese_gara,
                                            dd.cese_oid_cese_gara) AS cese_oid_cese_gara,
                                     decode(dd.oid_reem_arti_lote,
                                            NULL,
                                            d.num_mese_gara,
                                            dd.num_mese_gara) AS num_mese_gara,
                                     decode(dd.oid_reem_arti_lote,
                                            NULL,
                                            d.prod_oid_prod,
                                            dd.prod_oid_prod) AS prod_oid_prod,
                                     i.oid_para_gral,
                                     d.lopa_oid_lote_prem_arti,
                                     f.panp_oid_para_nive_prem
                                INTO lv_val_prec_cata_unit_loca,
                                     lv_val_prec_fact_unit_loca,
                                     lv_val_prec_cont_unit_loca,
                                     lv_oid_soli_posi,
                                     lv_ind_cent_dist_gara,
                                     lv_cese_oid_cese_gara,
                                     lv_num_mese_gara,
                                     lv_prod_oid_prod_devu,
                                     lv_copa_oid_para_gene_devu,
                                     lv_oid_lote_prem_artic_devu,
                                     lv_oid_para_nive_prem_devu
                                FROM ped_solic_cabec a,
                                     ped_solic_cabec b,
                                     ped_solic_posic c,
                                     inc_artic_lote d,
                                     inc_reemp_artic_lote dd,
                                     inc_lote_premi_artic e,
                                     inc_premi_artic f,
                                     inc_param_nivel_premi g,
                                     inc_param_gener_premi h,
                                     inc_concu_param_gener i,
                                     (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                                      MAX(id.oid_arti_lote) oid_arti_lote
                                        FROM inc_artic_lote id
                                       GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                               WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                                 AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                                 AND i.oid_para_gral = lv_oid_para_gral
                                 AND d.lopa_oid_lote_prem_arti =
                                     e.oid_lote_prem_arti
                               AND d.cod_vent_fict = id.cod_vent_fict
                               AND d.oid_arti_lote = id.oid_arti_lote
                               AND e.prar_oid_prem_arti = f.oid_prem_arti
                                 AND f.panp_oid_para_nive_prem =
                                     g.oid_para_nive_prem
                                 AND g.pagp_oid_para_gene_prem =
                                     h.oid_para_gene_prem
                               AND h.copa_oid_para_gral = i.oid_para_gral
                               AND dd.arlo_oid_arti_lote(+) = d.oid_arti_lote ---- + v_incremento
                               AND a.oid_soli_cabe = v_oid_cabe --43590098--v
                                 AND to_number(c.val_codi_vent_fict) =
                                     to_number(v_cod_vent_devu) --98698)--
                               AND (d.cod_vent_fict = c.val_codi_vent_fict OR
                                   dd.cod_vent_fict = c.val_codi_vent_fict)
                               AND c.espo_oid_esta_posi <> 2
                               AND rownum = 1
                             ORDER BY c.oid_soli_posi DESC;

                       end;
                    end;

                    ELSE
                      -- de otro modo, si el periodo de referencia es menor o igual al migrado
                      SELECT t.val_prec_cata_unit_loca,
                             t.val_prec_fact_unit_loca,
                             t.val_prec_cont_unit_loca,
                             t.oid_soli_posi,
                             t.ind_cent_dist_gara,
                             t.cese_oid_cese_gara,
                             t.num_mese_gara,
                             t.prod_oid_prod,
                             t.oid_para_gral,
                             t.lopa_oid_lote_prem_arti,
                             t.panp_oid_para_nive_prem
                        INTO lv_val_prec_cata_unit_loca,
                             lv_val_prec_fact_unit_loca,
                             lv_val_prec_cont_unit_loca,
                             lv_oid_soli_posi,
                             lv_ind_cent_dist_gara,
                             lv_cese_oid_cese_gara,
                             lv_num_mese_gara,
                             lv_prod_oid_prod_devu,
                             lv_copa_oid_para_gene_devu,
                             lv_oid_lote_prem_artic_devu,
                             lv_oid_para_nive_prem_devu
                        FROM (SELECT 0                         AS val_prec_cata_unit_loca,
                                     0                         AS val_prec_fact_unit_loca,
                                     d.imp_prec_publ           val_prec_cont_unit_loca,
                                     NULL                      AS oid_soli_posi,
                                     d.ind_cent_dist_gara,
                                     d.cese_oid_cese_gara,
                                     d.num_mese_gara,
                                     d.prod_oid_prod,
                                     i.oid_para_gral,
                                     d.lopa_oid_lote_prem_arti,
                                     f.panp_oid_para_nive_prem
                                FROM inc_artic_lote d,
                                     inc_lote_premi_artic e,
                                     inc_premi_artic f,
                                     inc_param_nivel_premi g,
                                     inc_param_gener_premi h,
                                     inc_concu_param_gener i,
                                     (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                                      MAX(id.oid_arti_lote) oid_arti_lote
                                        FROM inc_artic_lote id
                                       GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                             WHERE d.lopa_oid_lote_prem_arti =
                                   e.oid_lote_prem_arti
                                 AND d.cod_vent_fict = id.cod_vent_fict
                                 AND d.oid_arti_lote = id.oid_arti_lote
                                 AND e.prar_oid_prem_arti = f.oid_prem_arti
                               AND f.panp_oid_para_nive_prem =
                                   g.oid_para_nive_prem
                               AND g.pagp_oid_para_gene_prem =
                                   h.oid_para_gene_prem
                                 AND h.copa_oid_para_gral = i.oid_para_gral
                               AND to_number(d.cod_vent_fict) =
                                   to_number(v_cod_vent_devu)
                              UNION
                              SELECT 0                         AS val_prec_cata_unit_loca,
                                     0                         AS val_prec_fact_unit_loca,
                                     r.imp_prec_publ           val_prec_cont_unit_loca,
                                     NULL                      AS oid_soli_posi,
                                     r.ind_cent_dist_gara,
                                     r.cese_oid_cese_gara,
                                     r.num_mese_gara,
                                     r.prod_oid_prod,
                                     i.oid_para_gral,
                                     d.lopa_oid_lote_prem_arti,
                                     f.panp_oid_para_nive_prem
                                FROM inc_reemp_artic_lote r,
                                     inc_artic_lote d,
                                     inc_lote_premi_artic e,
                                     inc_premi_artic f,
                                     inc_param_nivel_premi g,
                                     inc_param_gener_premi h,
                                     inc_concu_param_gener i,
                                     (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                                      MAX(id.oid_arti_lote) oid_arti_lote
                                        FROM inc_artic_lote id
                                       GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                               WHERE r.arlo_oid_arti_lote = d.oid_arti_lote
                               AND d.lopa_oid_lote_prem_arti =
                                   e.oid_lote_prem_arti
                                 AND d.cod_vent_fict = id.cod_vent_fict
                                 AND d.oid_arti_lote = id.oid_arti_lote
                                 AND e.prar_oid_prem_arti = f.oid_prem_arti
                               AND f.panp_oid_para_nive_prem =
                                   g.oid_para_nive_prem
                               AND g.pagp_oid_para_gene_prem =
                                   h.oid_para_gene_prem
                                 AND h.copa_oid_para_gral = i.oid_para_gral
                               AND to_number(d.cod_vent_fict) =
                                   to_number(v_cod_vent_devu)) t
                       WHERE rownum = 1;

                    END IF;
                    -- fin si

                    existedevu := TRUE;

                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_val_prec_cata_unit_loca  := NULL;
                      lv_val_prec_fact_unit_loca  := NULL;
                      lv_val_prec_cont_unit_loca  := NULL;
                      lv_oid_soli_posi            := NULL;
                      lv_ind_cent_dist_gara       := NULL;
                      lv_cese_oid_cese_gara       := NULL;
                      lv_num_mese_gara            := NULL;
                      lv_prod_oid_prod_devu       := NULL;
                      lv_copa_oid_para_gene_devu  := NULL;
                      lv_oid_lote_prem_artic_devu := NULL;
                      lv_oid_para_nive_prem_devu  := NULL;
                      existedevu                  := FALSE;
                  END;

                END IF;

                -- Si el devuelve no esta en factura
              ELSE

                -- Si el codigo esta en la matriz de precios
                IF (v_cod_prec = p_indicador_matriz) THEN
                  BEGIN
                    SELECT d.tofe_oid_tipo_ofer,
                           maf.oid_matr_fact,
                           d.prod_oid_prod
                      INTO lv_tofe_oid_tipo_ofer,
                           lv_oid_matr_fact,
                           lv_prod_oid_prod_devu
                      FROM pre_ofert_detal       d,
                           pre_matri_factu       maf,
                           pre_matri_factu_cabec cab
                     WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                       AND cab.oid_cabe = maf.mfca_oid_cabe
                       AND cab.perd_oid_peri = v_oid_peri_refe
                     AND to_number(d.val_codi_vent) =
                         to_number(v_cod_vent_devu);

                    existedevu := TRUE;
                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_tofe_oid_tipo_ofer := NULL;
                      lv_oid_matr_fact      := NULL;
                      lv_prod_oid_prod_devu := NULL;
                      existedevu            := FALSE;
                  END;

                  -- Si esta en la matriz de incentivos
                ELSE
                  BEGIN
                    SELECT c.panp_oid_para_nive_prem,
                           a.lopa_oid_lote_prem_arti,
                           e.copa_oid_para_gral,
                           a.imp_prec_publ,
                           0 AS imp_prec_cata,
                           a.prod_oid_prod
                      INTO lv_panp_oid_para_nive_prem,
                           lv_lopa_oid_lote_prem_arti,
                           lv_copa_oid_para_gral,
                           lv_imp_prec_publ,
                           lv_imp_prec_cata,
                           lv_prod_oid_prod_devu
                      FROM inc_artic_lote a,
                           inc_lote_premi_artic b,
                           inc_premi_artic c,
                           inc_param_nivel_premi d,
                           inc_param_gener_premi e,
                           inc_concu_param_gener f,
                           (SELECT DISTINCT id.cod_vent_fict cod_vent_fict,
                                            MAX(id.oid_arti_lote) oid_arti_lote
                              FROM inc_artic_lote id
                             GROUP BY id.cod_vent_fict) id --- SQA se toma solo un cuv premio
                     WHERE a.lopa_oid_lote_prem_arti = b.oid_lote_prem_arti
                       AND a.cod_vent_fict = id.cod_vent_fict
                       AND a.oid_arti_lote = id.oid_arti_lote
                       AND b.prar_oid_prem_arti = c.oid_prem_arti
                       AND c.panp_oid_para_nive_prem = d.oid_para_nive_prem
                       AND d.pagp_oid_para_gene_prem = e.oid_para_gene_prem
                       AND e.copa_oid_para_gral = f.oid_para_gral
                     AND (to_number(a.cod_vent_fict) =
                         to_number(v_cod_vent_devu)
                         )
                       AND rownum = 1;

                    existedevu := TRUE;
                  EXCEPTION
                    WHEN no_data_found THEN
                      lv_panp_oid_para_nive_prem := NULL;
                      lv_lopa_oid_lote_prem_arti := NULL;
                      lv_copa_oid_para_gral      := NULL;
                      lv_imp_prec_publ           := NULL;
                      lv_imp_prec_cata           := NULL;
                      lv_prod_oid_prod_devu      := NULL;
                      existedevu                 := FALSE;
                  END;
                END IF;
              END IF;

              IF (v_ind_devu_fisi = 0) THEN
                BEGIN
                  SELECT d.tofe_oid_tipo_ofer,
                         maf.oid_matr_fact,
                         d.prod_oid_prod
                    INTO lv_tofe_oid_tipo_ofer,
                         lv_oid_matr_fact,
                         lv_prod_oid_prod_devu
                    FROM pre_ofert_detal       d,
                         pre_matri_factu       maf,
                         pre_matri_factu_cabec cab
                   WHERE maf.ofde_oid_deta_ofer = d.oid_deta_ofer
                     AND cab.oid_cabe = maf.mfca_oid_cabe
                     AND cab.perd_oid_peri = v_oid_peri_refe
                   AND to_number(d.val_codi_vent) =
                       to_number(v_cod_vent_devu);

                  existedevu := TRUE;

                EXCEPTION
                  WHEN no_data_found THEN
                    lv_tofe_oid_tipo_ofer := NULL;
                    lv_oid_matr_fact      := NULL;
                    lv_prod_oid_prod_devu := NULL;
                    existedevu            := FALSE;
                END;
              END IF;

            END IF;

            existe := existedevu;

          EXCEPTION
            WHEN OTHERS THEN
              existe := FALSE;
          END;

    RETURN (existe);

  END sto_fn_spvd_repre_devue;


  /***************************************************************************
  Descripcion       : Validacion de rechazo por OCR
  Fecha Creacion    : 20/05/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_recha_ocr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote

        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_moti_rech IS NULL;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        -- Actualizamos Documentos Validados
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(j)
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_RECHA_OCR: ' || ls_sqlerrm);

  END sto_pr_spvd_recha_ocr;

  /***************************************************************************
  Descripcion       : Validacion de facturados no enviados para revision
  Fecha Creacion    : 20/05/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_factu_noenv
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote

        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_oper IS NOT NULL
         AND det.cod_oper NOT IN ('FM',
                                  'FA');

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        -- Actualizamos Documentos Validados
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(j)
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_FACTU_NOENV: ' || ls_sqlerrm);

  END sto_pr_spvd_factu_noenv;

  /***************************************************************************
  Descripcion       : Validacion de monto minimo
  Fecha Creacion    : 08/06/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_monto_minim
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_lote,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             cab.num_docu,
             det.cod_oper,
             cab.sbti_oid_subt_clie,
             cab.ztad_oid_terr_admi,
             cab.ticl_oid_tipo_clie,
             cab.oid_cabe,
             cab.oid_clie,
             cab.num_docu_cruc,
             det.oid_soli_posi_devu,
             det.ind_list_blan_prod
      --             det.ind_ingr_devu,
      --             det.can_vent_devu,
      --             det.val_prec_cata_devu
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.num_lote = det.num_lote;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_docu IS TABLE OF int_solic_conso_poven_cabec.num_docu%TYPE;
    TYPE t_codoper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_sbti_oid_subt_clie IS TABLE OF int_solic_conso_poven_cabec.sbti_oid_subt_clie%TYPE;
    TYPE t_ztad_oid_terr_admi IS TABLE OF int_solic_conso_poven_cabec.ztad_oid_terr_admi%TYPE;
    TYPE t_ticl_oid_tipo_clie IS TABLE OF int_solic_conso_poven_cabec.ticl_oid_tipo_clie%TYPE;
    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_oid_clie IS TABLE OF int_solic_conso_poven_cabec.oid_clie%TYPE;
    TYPE t_oid_soli_posi_devu IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
    TYPE t_ind_list_blan_prod IS TABLE OF int_solic_conso_poven_detal.ind_list_blan_prod%TYPE;
    TYPE t_num_doc_cruc IS TABLE OF int_solic_conso_poven_cabec.num_docu_cruc%TYPE;

    --TYPE t_ind_ingr_devu IS TABLE OF int_solic_conso_poven_detal.ind_ingr_devu%TYPE;
    --    TYPE t_can_vent_devu IS TABLE OF int_solic_conso_poven_detal.can_vent_devu%TYPE;
    --  TYPE t_val_prec_cata_devu IS TABLE OF int_solic_conso_poven_detal.val_prec_cata_devu%TYPE;

    v_codpais            t_codpais;
    v_codperi            t_codperi;
    v_codclie            t_codclie;
    v_numlote            t_numlote;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_docu           t_num_docu;
    v_codoper            t_codoper;
    v_sbti_oid_subt_clie t_sbti_oid_subt_clie;
    v_ztad_oid_terr_admi t_ztad_oid_terr_admi;
    v_ticl_oid_tipo_clie t_ticl_oid_tipo_clie;
    v_oid_cabe           t_oid_cabe;
    v_oid_clie           t_oid_clie;
    v_oid_soli_posi_devu t_oid_soli_posi_devu;
    v_ind_list_blan_prod t_ind_list_blan_prod;
    v_num_doc_cruc       t_num_doc_cruc;

    ls_tota_paga_loca ped_solic_cabec.val_tota_paga_loca %TYPE;
    var_temporal      ped_solic_cabec.val_tota_paga_loca%TYPE;
    ls_valor_nivel1   ped_monto_minim.val_niv1%TYPE;
    ls_tota_devuelve  ped_solic_cabec.val_tota_paga_loca%TYPE;
    ls_ind_oc         ped_solic_cabec.ind_oc%TYPE;

    existe BOOLEAN := TRUE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_docu,
             v_codoper,
             v_sbti_oid_subt_clie,
             v_ztad_oid_terr_admi,
             v_ticl_oid_tipo_clie,
             v_oid_cabe,
             v_oid_clie,
             v_num_doc_cruc,
             v_oid_soli_posi_devu,
             v_ind_list_blan_prod
       LIMIT w_filas;

      IF (v_codpais.count > 0) THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          existe := FALSE;
          IF ((v_codoper(i) != 'DN') or
              (v_codoper(i) = 'DN' and v_ind_list_blan_prod(i) = '1' )) THEN

            existe := TRUE;

          ELSE

            BEGIN

              --- Verifica si el codigo es una devolucion de un pedido
              SELECT b.ind_oc
                INTO ls_ind_oc
                FROM ped_solic_posic a,
                     ped_solic_cabec b
               WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
                 AND a.oid_soli_posi = v_oid_soli_posi_devu(i);

               ---- Solo valida si la operacion es sobre una orden de compra
               if ls_ind_oc = 1 then

              --- Recupera el total del pedido
                  SELECT sum(b.val_tota_paga_loca)
                INTO ls_tota_paga_loca
                FROM ped_solic_cabec a,
                     ped_solic_cabec b
               WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                 AND a.oid_soli_cabe = v_oid_cabe(i)
                 AND b.ind_oc = 1;

                --- Revisa lo que se proceso como devolucion hasta la fecha
                SELECT  nvl(SUM(nvl(det2.can_vent_devu,0)*
                (SELECT nvl(val_prec_cata_unit_loca,
                                     0)
                            FROM ped_solic_posic
                           WHERE oid_soli_posi = det2.oid_soli_posi_devu)
                ),0)
                  INTO ls_tota_devuelve
                  FROM int_solic_conso_poven_detal det2,
                       int_solic_conso_poven_cabec cab2,
                       sto_docum_digit             dig
                 WHERE det2.cod_pais = pscodigopais
                   AND det2.cod_clie = v_codclie(i)
                   ---AND det2.oid_soli_posi_devu = v_oid_soli_posi_devu(i)
                   and CAB2.NUM_DOCU_CRUC = v_num_doc_cruc(i)
                   AND dig.sec_nume_docu = det2.sec_nume_docu
                   AND dig.sec_nume_docu_cabe = cab2.sec_nume_docu
                   AND dig.ind_rech = 0
                   AND det2.cod_oper = 'DN';

              var_temporal := ls_tota_paga_loca - ls_tota_devuelve;

              SELECT nvl(max(a.val_niv1),0)
                into ls_valor_nivel1
                FROM mae_clien_unida_admin c,
                     zon_terri_admin       d,
                     zon_terri             e,
                     zon_secci             f,
                     zon_zona              g,
                     zon_regio             h,
                     mae_clien_tipo_subti  i,
                     mae_clien_clasi       j,
                     ped_monto_minim       a
               WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
                 AND d.zscc_oid_secc = f.oid_secc
                 AND d.terr_oid_terr = e.oid_terr
                 AND f.zzon_oid_zona = g.oid_zona
                 AND g.zorg_oid_regi = h.oid_regi
                 AND c.clie_oid_clie = i.clie_oid_clie
                 AND c.ind_acti = 1
                 AND d.ind_borr = 0
                 AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
                 AND c.clie_oid_clie = v_oid_clie(i)
                 --AND (c.clie_oid_clie = a.clie_oid_clie OR a.clie_oid_clie IS NULL) --cliente
                 AND (i.ticl_oid_tipo_clie = a.TICL_OID_TIPO_CLIE OR a.TICL_OID_TIPO_CLIE IS NULL) --tipo
                 AND (i.sbti_oid_subt_clie = a.SBTI_OID_SUBT_CLIE OR a.SBTI_OID_SUBT_CLIE IS NULL) --subtipo
                 AND (j.tccl_oid_tipo_clasi = a.TCCL_OID_TIPO_CLAS OR TCCL_OID_TIPO_CLAS IS NULL) --tipo clasif
                 AND (j.clas_oid_clas = a.clas_oid_clas OR a.clas_oid_clas IS NULL) --clasif
                 AND (h.oid_regi = a.zorg_oid_regi OR a.zorg_oid_regi IS NULL) --region
                 AND (g.oid_zona = a.zzon_oid_zona OR a.zzon_oid_zona IS NULL) --zon
                 ;

              IF (var_temporal < ls_valor_nivel1) THEN
                existe := FALSE;
              ELSE
                existe := TRUE;
              END IF;
               else
                  existe := TRUE;
               END IF;

            EXCEPTION
              WHEN OTHERS THEN
                existe := FALSE;
            END;

          END IF;

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;

    CLOSE c_cursor;
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVC_MONTO_MINIM: ' || ls_sqlerrm);

  END sto_pr_spvd_monto_minim;
  /***************************************************************************
  Descripcion       : Validacion de monto minimo
  Fecha Creacion    : 08/06/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_spvd_monto_minim
  (
    pscodigopais          in  VARCHAR2,
    pscodoCliente         in  VARCHAR2,
    psOidSoliPosi         in  number,
    psunidadesReclamar    in  number,
    psCodOperSICC         in  VARCHAR2,
    psTipoOperSICC        in  VARCHAR2
  ) RETURN BOOLEAN  IS

    ls_tota_paga_loca ped_solic_cabec.val_tota_paga_loca %TYPE;
    var_temporal      ped_solic_cabec.val_tota_paga_loca%TYPE;
    ls_valor_nivel1   ped_monto_minim.val_niv1%TYPE;
    ls_tota_devuelve  ped_solic_cabec.val_tota_paga_loca%TYPE;
    ls_val_prec_cata  ped_solic_posic.val_prec_cata_unit_loca%TYPE;

    v_num_unid_aten      number;
    v_num_unid_recl      number;

    v_oidclie  int_solic_conso_poven_cabec.oid_clie%TYPE;
    v_oidcabe  int_solic_conso_poven_cabec.oid_cabe%TYPE;
    ls_ind_oc         ped_solic_cabec.ind_oc%TYPE;
    v_numped    int_solic_conso_poven_cabec.num_docu_cruc%TYPE;


    existe BOOLEAN := TRUE;

  BEGIN

        BEGIN
            SELECT cab.soca_oid_soli_cabe,nvl(pos.val_prec_cata_unit_loca,0),CAB1.VAL_NUME_SOLI
                into v_oidcabe,ls_val_prec_cata,v_numped
                FROM ped_solic_posic             pos,
                     ped_solic_cabec             cab1,
                     ped_solic_cabec             cab
                 WHERE pos.soca_oid_soli_cabe = cab.oid_soli_cabe
                   AND cab.soca_oid_soli_cabe = CAB1.OID_SOLI_CABE
                   AND pos.oid_soli_posi = psoidsoliposi;
        EXCEPTION
          WHEN no_data_found THEN
            v_oidcabe := 0;
            ls_val_prec_cata := 0;
            v_numped := '';
        END;

        BEGIN
            SELECT mae.oid_clie into v_oidclie FROM mae_clien  mae where mae.cod_clie = pscodoCliente;
        EXCEPTION
          WHEN no_data_found THEN
            v_oidclie := 0;
        END;

          existe := FALSE;
          IF (psCodOperSICC != 'DN') THEN

            existe := TRUE;

          ELSE

            BEGIN

              --- Verifica si el codigo es una devolucion de un pedido
              SELECT b.ind_oc
                INTO ls_ind_oc
                FROM ped_solic_posic a,
                     ped_solic_cabec b
               WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
                 AND a.oid_soli_posi = psoidsoliposi;

               ---- Solo valida si la operacion es sobre una orden de compra
               if ls_ind_oc = 1 then


              --- Recupera el total del pedido
                  SELECT sum(b.val_tota_paga_loca)
                INTO ls_tota_paga_loca
                FROM ped_solic_cabec a,
                     ped_solic_cabec b
               WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                 AND a.oid_soli_cabe = v_oidcabe
                 AND b.ind_oc = 1;

                --- Revisa lo que se proceso como devolucion hasta la fecha
                /*SELECT  nvl(SUM(nvl(det2.can_vent_devu,0)*
                (SELECT nvl(val_prec_cata_unit_loca,0)
                            FROM ped_solic_posic
                           WHERE oid_soli_posi = det2.oid_soli_posi_devu)
                ),0)
                  INTO ls_tota_devuelve
                  FROM int_solic_conso_poven_detal det2,
                       sto_docum_digit             dig
                 WHERE det2.cod_pais = pscodigopais
                   AND det2.cod_clie = pscodoCliente
                   AND det2.oid_soli_posi_devu = psoidsoliposi
                   AND dig.sec_nume_docu = det2.sec_nume_docu
                   AND dig.ind_rech = 0
                   AND det2.cod_oper = 'DN';*/

                SELECT  nvl(SUM(nvl(det2.can_vent_devu,0)*
                (SELECT nvl(val_prec_cata_unit_loca,0)
                            FROM ped_solic_posic
                           WHERE oid_soli_posi = det2.oid_soli_posi_devu)
                ),0)
                  INTO ls_tota_devuelve
                  FROM int_solic_conso_poven_detal det2,
                       int_solic_conso_poven_cabec cab2,
                       sto_docum_digit             dig
                 WHERE det2.cod_pais = pscodigopais
                   AND det2.cod_clie = pscodoCliente
                   ---AND det2.oid_soli_posi_devu = psoidsoliposi
                   and CAB2.NUM_DOCU_CRUC = v_numped
                   AND dig.sec_nume_docu = det2.sec_nume_docu
                   AND dig.sec_nume_docu_cabe = cab2.sec_nume_docu
                   AND dig.ind_rech = 0
                   AND det2.cod_oper = 'DN';

              var_temporal := ls_tota_paga_loca - ls_tota_devuelve - ( psunidadesReclamar * ls_val_prec_cata);

              SELECT nvl(max(a.val_niv1),0)
                into ls_valor_nivel1
                FROM mae_clien_unida_admin c,
                     zon_terri_admin       d,
                     zon_terri             e,
                     zon_secci             f,
                     zon_zona              g,
                     zon_regio             h,
                     mae_clien_tipo_subti  i,
                     mae_clien_clasi       j,
                     ped_monto_minim       a
               WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
                 AND d.zscc_oid_secc = f.oid_secc
                 AND d.terr_oid_terr = e.oid_terr
                 AND f.zzon_oid_zona = g.oid_zona
                 AND g.zorg_oid_regi = h.oid_regi
                 AND c.clie_oid_clie = i.clie_oid_clie
                 AND c.ind_acti = 1
                 AND d.ind_borr = 0
                 AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
                 AND c.clie_oid_clie = v_oidclie
                 --AND (c.clie_oid_clie = a.clie_oid_clie OR a.clie_oid_clie IS NULL) --cliente
                 AND (i.ticl_oid_tipo_clie = a.TICL_OID_TIPO_CLIE OR a.TICL_OID_TIPO_CLIE IS NULL) --tipo
                 AND (i.sbti_oid_subt_clie = a.SBTI_OID_SUBT_CLIE OR a.SBTI_OID_SUBT_CLIE IS NULL) --subtipo
                 AND (j.tccl_oid_tipo_clasi = a.TCCL_OID_TIPO_CLAS OR TCCL_OID_TIPO_CLAS IS NULL) --tipo clasif
                 AND (j.clas_oid_clas = a.clas_oid_clas OR a.clas_oid_clas IS NULL) --clasif
                 AND (h.oid_regi = a.zorg_oid_regi OR a.zorg_oid_regi IS NULL) --region
                 AND (g.oid_zona = a.zzon_oid_zona OR a.zzon_oid_zona IS NULL) --zon
                 ;

              IF (var_temporal < ls_valor_nivel1) THEN
                existe := FALSE;
              ELSE
                existe := TRUE;
              END IF;
               else
                  existe := TRUE;
               END IF;

            EXCEPTION
              WHEN OTHERS THEN
                existe := FALSE;
            END;

          END IF;


    RETURN (existe);

  END sto_fn_spvd_monto_minim;

  /***************************************************************************
  Descripcion       : Validacion de producto ganador
  Fecha Creacion    : 10/06/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_produ_ganad
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.num_line

        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ,
             ped_subti_posic             pos,
             ped_solic_posic             pos2

       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND pos2.oid_soli_posi(+) = det.oid_soli_posi_devu
         AND pos.oid_subt_posi(+) = pos2.stpo_oid_subt_posi
         AND ((det.cod_oper <> 'DN') OR
             (det.cod_oper = 'DN' AND (pos.cod_subt_posi IS NULL OR pos.cod_subt_posi <> 'MM')));

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_num_line IS TABLE OF int_solic_conso_poven_detal.num_line%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_num_line           t_num_line;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_num_line

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        -- Actualizamos Documentos Validados
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = v_codpais(j)
             AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(j)
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_PRODU_GANAD: ' || ls_sqlerrm);

  END sto_pr_spvd_produ_ganad;

  /***************************************************************************
  Descripcion       : Validacion de producto ganador
  Fecha Creacion    : 10/06/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  FUNCTION sto_fn_spvd_produ_ganad
  (
    pscodoper          VARCHAR2,
    psoidsoliposi      NUMBER
  ) RETURN BOOLEAN IS

    v_cod_subt_posi  ped_subti_posic.cod_subt_posi%TYPE;

    existe BOOLEAN := TRUE;

  BEGIN

        BEGIN
            SELECT pos.cod_subt_posi
                into v_cod_subt_posi
                FROM ped_subti_posic             pos,
                     ped_solic_posic             pos2
                 WHERE pos.oid_subt_posi(+)  = pos2.stpo_oid_subt_posi
                   AND pos2.oid_soli_posi(+) = psoidsoliposi;
        EXCEPTION
          WHEN no_data_found THEN
            v_cod_subt_posi := null;
        END;

        if((pscodoper <> 'DN') OR
               (pscodoper = 'DN' AND (v_cod_subt_posi IS NULL OR v_cod_subt_posi <> 'MM'))) then
           existe := TRUE;
        else
           existe := FALSE;
        end if;

 RETURN (existe);
  END sto_fn_spvd_produ_ganad;


  /***************************************************************************
  Descripcion       : Validacion de atencion FFNNEE
  Fecha Creacion    : 17/07/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_atenc_ffnne
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_oper,
             det.prod_oid_prod_devu,
             cab.oid_cabe,
             cab.oid_peri_recl,
             cab.cod_regi_arri
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
      --sto_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu;
    --and det.sec_nume_docu = 16249631;
    --and cab.sec_nume_docu = 16249563;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_prod_oid_prod_devu IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_devu%TYPE;

    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_oid_peri_recl IS TABLE OF int_solic_conso_poven_cabec.oid_peri_recl%TYPE;
    TYPE t_cod_regi_arri IS TABLE OF int_solic_conso_poven_cabec.cod_regi_arri%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_cod_oper           t_cod_oper;
    v_prod_oid_prod_devu t_prod_oid_prod_devu;
    v_oid_cabe           t_oid_cabe;
    v_oid_peri_recl      t_oid_peri_recl;
    v_cod_regi_arri      t_cod_regi_arri;

    i      BINARY_INTEGER := 0;
    cuenta NUMBER := 0;

    t_envi2          NUMBER;
    t_devu2          NUMBER;
    t_oid_oper2      NUMBER;
    t_oid_tipo_oper2 NUMBER;

    periodoactivo           bas_ctrl_fact.cod_peri%TYPE;
    periodoinicio           seg_perio_corpo.cod_peri%TYPE;
    periodoiniciorecurrente seg_perio_corpo.cod_peri%TYPE;
    lsparametroantifne      sto_param_gener_occrr.val_param%TYPE;
    lsparametrocantfne      sto_param_gener_occrr.val_param%TYPE;
    lsparametrocampfne      sto_param_gener_occrr.val_param%TYPE;
    --valida                  BOOLEAN := TRUE;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsparametroantifne := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_ANTI_FNE');
    lsparametrocantfne := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_CANT_FNE');
    lsparametrocampfne := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_CAMP_FNE');

    SELECT cod_peri
      INTO periodoactivo
      FROM bas_ctrl_fact
     WHERE sta_camp = 0
       AND ind_camp_act = 1;

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_oper,
             v_prod_oid_prod_devu,
             v_oid_cabe,
             v_oid_peri_recl,
             v_cod_regi_arri

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          --  IF (v_cod_oper(i) IN ('FM', 'MF', 'FA')) THEN
          IF (v_cod_oper(i) IN ('FM',
                                'MF',
                                'FA',
                                'FP',
                                'PF')) THEN

            SELECT gen_pkg_gener.gen_fn_devuelve_des_perio(oid_peri)
              INTO periodoinicio
              FROM (SELECT rownum fila,
                           t.*
                      FROM (SELECT cra_perio.*
                              FROM cra_perio
                             WHERE fec_fina <
                                   (SELECT p.fec_fina
                                      FROM cra_perio p
                                     WHERE p.val_nomb_peri LIKE '%' || periodoactivo || '%')
                             ORDER BY fec_fina DESC) t) tt
             WHERE tt.fila = to_number(lsparametroantifne);

            SELECT COUNT(*)
              INTO cuenta
              FROM ped_solic_cabec c
             WHERE c.oid_soli_cabe = v_oid_cabe(i)
               AND EXISTS
             (SELECT 1
                      FROM int_lar_tipo_solici_pedido_dis ltsp
                     WHERE ltsp.tspa_oid_tipo_soli_pais = c.tspa_oid_tipo_soli_pais)
               AND gen_pkg_gener.gen_fn_devuelve_des_perio(c.perd_oid_peri) <= periodoactivo
               AND gen_pkg_gener.gen_fn_devuelve_des_perio(c.perd_oid_peri) >= periodoinicio;

            IF (cuenta > 0) THEN

              /*              SELECT COUNT(*)
                              INTO cuenta
                              FROM mae_clien_datos_adici a
                             WHERE a.clie_oid_clie IN
                                   (SELECT c.oid_clie
                                      FROM mae_clien c
                                     WHERE c.cod_clie = v_codclie(i))
                               AND a.esta_oid_esta_clie = 2;
              */
              SELECT COUNT(*)
                INTO cuenta
                FROM mae_tipo_clasi_clien mcc,
                     mae_clien_tipo_subti m,
                     mae_clien_clasi      mc,
                     mae_clien            mmm
               WHERE mcc.cod_tipo_clas = '21'
                 AND m.oid_clie_tipo_subt = mc.ctsu_oid_clie_tipo_subt
                 AND mc.tccl_oid_tipo_clasi = mcc.oid_tipo_clas
                 AND m.clie_oid_clie = mmm.oid_clie
                 AND mmm.cod_clie = v_codclie(i);

              IF (cuenta > 0) THEN
                existe := TRUE;
              ELSE

                /*IF (v_codpais(i) <> 'VEL' AND v_codpais(i) <> 'VEE') THEN*/
                SELECT COUNT(*)
                  INTO cuenta
                  FROM ped_solic_cabec c
                 WHERE c.oid_soli_cabe = v_oid_cabe(i)
                   AND EXISTS
                 (SELECT 1
                          FROM int_lar_tipo_solici_pedido_dis ltsp
                         WHERE ltsp.tspa_oid_tipo_soli_pais = c.tspa_oid_tipo_soli_pais)
                   AND c.recq_oid_resu_cheq IN
                       (SELECT oid_resu_cheq FROM rec_resul_chequ WHERE cod_resu_cheq = 'OK');
                /*ELSE
                  cuenta := 0;
                END IF;*/
                IF (cuenta > 0) THEN

                  SELECT COUNT(*)
                    INTO cuenta
                    FROM rec_produ_fm fm
                   WHERE fm.cod_peri_inic <= periodoactivo
                     AND fm.cod_peri_fin >= periodoactivo
                     AND fm.cod_sap =
                         (SELECT cod_sap FROM mae_produ WHERE oid_prod = v_prod_oid_prod_devu(i))
                     AND fm.cod_regi = v_cod_regi_arri(i);

                  IF (cuenta > 0) THEN
                    existe := TRUE;
                  ELSE
                    existe := FALSE;
                  END IF;
                ELSE

                  SELECT gen_pkg_gener.gen_fn_devuelve_des_perio(oid_peri)
                    INTO periodoiniciorecurrente
                    FROM (SELECT rownum fila,
                                 t.*
                            FROM (SELECT cra_perio.*
                                    FROM cra_perio
                                   WHERE fec_fina <
                                         (SELECT p.fec_fina
                                            FROM cra_perio p
                                           WHERE p.val_nomb_peri LIKE '%' || periodoactivo || '%')
                                   ORDER BY fec_fina DESC) t) tt
                   WHERE tt.fila = to_number(lsparametrocampfne);

                  SELECT COUNT(*)
                    INTO cuenta
                    FROM rec_cabec_recla a,
                         rec_opera_recla b,
                         rec_tipos_opera e,
                         rec_opera       f
                   WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
                     AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
                     AND e.rope_oid_oper = f.oid_oper
                     AND f.ind_falt_merc = 1
                     AND a.clie_oid_clie =
                         (SELECT oid_clie FROM mae_clien WHERE cod_clie = v_codclie(i))
                     AND gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_recl) <
                         periodoactivo
                     AND gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_recl) >=
                         periodoiniciorecurrente; --periodoactivo;

                  IF (cuenta >= to_number(lsparametrocantfne)) THEN
                    --valida := FALSE;
                    SELECT COUNT(*)
                      INTO cuenta
                      FROM rec_produ_fm fm
                     WHERE fm.cod_peri_inic <= periodoactivo
                       AND fm.cod_peri_fin >= periodoactivo
                       AND fm.cod_sap =
                           (SELECT cod_sap FROM mae_produ WHERE oid_prod = v_prod_oid_prod_devu(i))
                       AND fm.cod_regi = v_cod_regi_arri(i);

                    IF (cuenta > 0) THEN
                      existe := TRUE;
                    ELSE
                      existe := FALSE;
                    END IF;

                  ELSE
                    --valida := TRUE;
                    --valida := FALSE;
                    SELECT COUNT(*)
                      INTO cuenta
                      FROM rec_produ_fm fm
                     WHERE fm.cod_peri_inic <= periodoactivo
                       AND fm.cod_peri_fin >= periodoactivo
                       AND fm.cod_sap =
                           (SELECT cod_sap FROM mae_produ WHERE oid_prod = v_prod_oid_prod_devu(i))
                       AND fm.cod_regi = v_cod_regi_arri(i);

                    IF (cuenta > 0) THEN
                      existe := TRUE;
                    ELSE
                      existe := TRUE; --SOLO A ENVIAR

                      /*IF (v_codpais(i) = 'VEL' OR v_codpais(i) = 'VEE') THEN*/

                      SELECT tspa_oid_soli_con_stoc,
                             tspa_oid_soli_pais_gene,
                             oid_oper,
                             oid_tipo_oper
                        INTO t_envi2,
                             t_devu2,
                             t_oid_oper2,
                             t_oid_tipo_oper2
                        FROM rec_opera       op,
                             rec_tipos_opera top
                       WHERE top.rope_oid_oper = op.oid_oper
                         AND op.cod_oper = 'FM'
                         AND top.val_tipo_oper = '01';

                      UPDATE int_solic_conso_poven_detal l
                         SET l.tip_refe                     = 'F',
                             l.cod_oper                     = 'FM',
                             l.cod_tipo_oper                = '01',
                             l.oid_oper                     = t_oid_oper2,
                             l.oid_tipo_oper                = t_oid_tipo_oper2,
                             l.ind_ingr_envi                = 0,
                             l.ind_ingr_devu                = 1,
                             l.ind_devu_fisi                = 1,
                             l.ind_envi_gener_devu          = 0,
                             l.ind_devu_gener_envi          = 1,
                             l.ind_envi_fact                = 1,
                             l.ind_devu_fact                = 1,
                             l.cod_prec_envi                = 'F',
                             l.cod_prec                     = 'P',
                             l.tspa_oid_tipo_soli_pais_devu = t_devu2,
                             l.tspa_oid_tipo_soli_pais_envu = t_envi2
                       WHERE l.sec_nume_docu = v_sec_nume_docu(i);

                      /*END IF;*/

                    END IF;
                  END IF;

                END IF;

              END IF;

            ELSE
              existe := FALSE;
            END IF;

          ELSE
            existe := TRUE;
          END IF;

          IF (existe = TRUE) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_ATENC_FFNNE: ' || ls_sqlerrm);

  END sto_pr_spvd_atenc_ffnne;

  /***************************************************************************
  Descripcion       : Validacion de atencion FFNNE2
  Fecha Creacion    : 17/12/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_atenc_ffne2
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_oper,
             det.prod_oid_prod_devu,
             cab.oid_cabe,
             cab.oid_peri_recl,
             cab.cod_regi_arri
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_prod_oid_prod_devu IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_devu%TYPE;

    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_oid_peri_recl IS TABLE OF int_solic_conso_poven_cabec.oid_peri_recl%TYPE;
    TYPE t_cod_regi_arri IS TABLE OF int_solic_conso_poven_cabec.cod_regi_arri%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_cod_oper           t_cod_oper;
    v_prod_oid_prod_devu t_prod_oid_prod_devu;
    v_oid_cabe           t_oid_cabe;
    v_oid_peri_recl      t_oid_peri_recl;
    v_cod_regi_arri      t_cod_regi_arri;

    i      BINARY_INTEGER := 0;
    cuenta NUMBER := 0;

    t_envi2          NUMBER;
    t_devu2          NUMBER;
    t_oid_oper2      NUMBER;
    t_oid_tipo_oper2 NUMBER;

    periodoactivo bas_ctrl_fact.cod_peri%TYPE;
    periodoinicio seg_perio_corpo.cod_peri%TYPE;

    lsparametroantifne sto_param_gener_occrr.val_param%TYPE;

    periodo_pedido seg_perio_corpo.cod_peri%TYPE;
    oid_peri_pedi  ped_solic_cabec.perd_oid_peri%TYPE;
    existe         BOOLEAN := TRUE;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsparametroantifne := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_ANTI_FNE');

    SELECT cod_peri
      INTO periodoactivo
      FROM bas_ctrl_fact
     WHERE sta_camp = 0
       AND ind_camp_act = 1;

    SELECT gen_pkg_gener.gen_fn_devuelve_des_perio(oid_peri)
      INTO periodoinicio
      FROM (SELECT rownum fila,
                   t.*
              FROM (SELECT cra_perio.*
                      FROM cra_perio
                     WHERE fec_fina <
                           (SELECT p.fec_fina
                              FROM cra_perio p
                             WHERE p.val_nomb_peri LIKE '%' || periodoactivo || '%')
                     ORDER BY fec_fina DESC) t) tt
     WHERE tt.fila = to_number(lsparametroantifne);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_oper,
             v_prod_oid_prod_devu,
             v_oid_cabe,
             v_oid_peri_recl,
             v_cod_regi_arri

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          IF (v_cod_oper(i) IN ('FM',
                                'MF',
                                'FA',
                                'FP',
                                'PF')) THEN

            SELECT COUNT(*)
              INTO cuenta
              FROM ped_solic_cabec c
             WHERE c.oid_soli_cabe = v_oid_cabe(i)
               AND EXISTS
             (SELECT 1
                      FROM int_lar_tipo_solici_pedido_dis ltsp
                     WHERE ltsp.tspa_oid_tipo_soli_pais = c.tspa_oid_tipo_soli_pais)
               AND gen_pkg_gener.gen_fn_devuelve_des_perio(c.perd_oid_peri) <= periodoactivo
               AND gen_pkg_gener.gen_fn_devuelve_des_perio(c.perd_oid_peri) >= periodoinicio;

            IF (cuenta > 0) THEN

              /*SELECT COUNT(*)
               INTO cuenta
               FROM mae_clien_datos_adici a
              WHERE a.clie_oid_clie IN
                    (SELECT c.oid_clie
                       FROM mae_clien c
                      WHERE c.cod_clie = v_codclie(i))
                AND a.esta_oid_esta_clie = 2;*/

              SELECT COUNT(*)
                INTO cuenta
                FROM mae_tipo_clasi_clien mcc,
                     mae_clien_tipo_subti m,
                     mae_clien_clasi      mc,
                     mae_clien            mmm
               WHERE mcc.cod_tipo_clas = '21'
                 AND m.oid_clie_tipo_subt = mc.ctsu_oid_clie_tipo_subt
                 AND mc.tccl_oid_tipo_clasi = mcc.oid_tipo_clas
                 AND m.clie_oid_clie = mmm.oid_clie
                 AND mmm.cod_clie = v_codclie(i);

              IF (cuenta > 0) THEN
                existe := TRUE;
              ELSE

                BEGIN

                  SELECT perd_oid_peri
                    INTO oid_peri_pedi
                    FROM ped_solic_cabec
                   WHERE oid_soli_cabe = v_oid_cabe(i);

                  SELECT a.cod_peri
                    INTO periodo_pedido
                    FROM seg_perio_corpo a,
                         cra_perio       b
                   WHERE b.oid_peri = oid_peri_pedi
                     AND a.oid_peri = b.peri_oid_peri;

                  SELECT COUNT(*)
                    INTO cuenta
                    FROM rec_produ_fm fm
                   WHERE fm.cod_peri_inic <= periodo_pedido
                     AND fm.cod_peri_fin >= periodo_pedido
                     AND fm.cod_sap =
                         (SELECT cod_sap FROM mae_produ WHERE oid_prod = v_prod_oid_prod_devu(i))
                     AND fm.cod_regi = v_cod_regi_arri(i);

                  IF (cuenta > 0) THEN
                    existe := TRUE;

                    SELECT tspa_oid_soli_con_stoc,
                           tspa_oid_soli_pais_gene,
                           oid_oper,
                           oid_tipo_oper
                      INTO t_envi2,
                           t_devu2,
                           t_oid_oper2,
                           t_oid_tipo_oper2
                      FROM rec_opera       op,
                           rec_tipos_opera top
                     WHERE top.rope_oid_oper = op.oid_oper
                       AND op.cod_oper = 'FM'
                       AND top.val_tipo_oper = '01';

                    UPDATE int_solic_conso_poven_detal l
                       SET l.tip_refe                     = 'F',
                           l.cod_oper                     = 'FM',
                           l.cod_tipo_oper                = '01',
                           l.oid_oper                     = t_oid_oper2,
                           l.oid_tipo_oper                = t_oid_tipo_oper2,
                           l.ind_ingr_envi                = 0,
                           l.ind_ingr_devu                = 1,
                           l.ind_devu_fisi                = 1,
                           l.ind_envi_gener_devu          = 0,
                           l.ind_devu_gener_envi          = 1,
                           l.ind_envi_fact                = 1,
                           l.ind_devu_fact                = 1,
                           l.cod_prec_envi                = 'F',
                           l.cod_prec                     = 'P',
                           l.tspa_oid_tipo_soli_pais_devu = t_devu2,
                           l.tspa_oid_tipo_soli_pais_envu = t_envi2
                     WHERE l.sec_nume_docu = v_sec_nume_docu(i);

                  ELSE
                    existe := FALSE;
                  END IF;
                EXCEPTION
                  WHEN OTHERS THEN
                    existe := TRUE;
                END;
              END IF;

            ELSE
              existe := FALSE;
            END IF;

          ELSE
            existe := TRUE;
          END IF;

          IF (existe = TRUE) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_ATENC_FFNE2: ' || ls_sqlerrm);

  END sto_pr_spvd_atenc_ffne2;

  /***************************************************************************
  Descripcion       : VALIDACION CONTROL DE PRODUCTO ENVIA
  Fecha Creacion    : 03/05/2010
  Autor             : Jose A. cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_produ_envia
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND (det.cod_vent_dese IS NULL OR
             det.cod_oper NOT IN (SELECT cod_oper
                                     FROM rec_opera ro
                                    WHERE ro.ind_ingr_envi = 0
                                      AND ro.val_ingr_devu = 1
                                      AND ro.ind_devu_gene_envi = 0))
      UNION ALL
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND (det.cod_vent_dese IS NOT NULL AND
             det.cod_oper IN (SELECT cod_oper
                                 FROM rec_opera ro
                                WHERE ro.ind_ingr_envi = 0
                                  AND ro.val_ingr_devu = 1
                                  AND ro.ind_devu_gene_envi = 0
                                  AND substr(ro.cod_oper,
                                             1,
                                             1) = 'E'));

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;

    i BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_PRODU_ENVIA: ' || ls_sqlerrm);

  END sto_pr_spvd_produ_envia;
  /***************************************************************************
  Descripcion       : Validacion Centro de Servicios
  Fecha Creacion    : 31/05/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_centr_servi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote,
             det.cod_oper,
             det.lopa_oid_lote_prem_artic_devu
        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_oid_lote_prem_artic_dev IS TABLE OF int_solic_conso_poven_detal.lopa_oid_lote_prem_artic_devu%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;

    v_sec_nume_docu           t_sec_nume_docu;
    v_num_lote                t_num_lote;
    v_oid_lote_prem_artic_dev t_oid_lote_prem_artic_dev;
    v_cod_oper                t_cod_oper;

    v_ind_centro_distribucion     NUMBER;
    v_descripcion_centro_servicio VARCHAR2(80);
    is_valid                      BOOLEAN;
    i                             BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote,
             v_cod_oper,
             v_oid_lote_prem_artic_dev LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          is_valid := TRUE;

          BEGIN
            SELECT a.ind_cent_dist_gara,
                   b.des_cent_serv
              INTO v_ind_centro_distribucion,
                   v_descripcion_centro_servicio
              FROM inc_artic_lote  a,
                   inc_centr_servi b
             WHERE lopa_oid_lote_prem_arti = v_oid_lote_prem_artic_dev(i)
               AND a.cese_oid_cese_gara = b.oid_cent_serv;
          EXCEPTION
            WHEN no_data_found THEN
              v_descripcion_centro_servicio := '';
              v_ind_centro_distribucion     := 0;
          END;

          IF v_ind_centro_distribucion = 1 AND v_cod_oper(i) = 'CP' THEN
            is_valid := FALSE;
          END IF;

          IF is_valid THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          ELSE
            UPDATE int_solic_conso_poven_detal
               SET des_cent_serv = v_descripcion_centro_servicio
             WHERE sec_nume_docu = v_sec_nume_docu(i);
          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_CENTR_SERVI: ' || ls_sqlerrm);

  END sto_pr_spvd_centr_servi;

  /***************************************************************************
  Descripcion       : Validacion 1 de FFNE
  Fecha Creacion    : 08/07/2010
  Autor             : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_atenc_ffnne_val1
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_oper,
             det.prod_oid_prod_devu,
             cab.oid_cabe,
             cab.oid_peri_recl,
             cab.cod_regi_arri
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_prod_oid_prod_devu IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_devu%TYPE;

    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_oid_peri_recl IS TABLE OF int_solic_conso_poven_cabec.oid_peri_recl%TYPE;
    TYPE t_cod_regi_arri IS TABLE OF int_solic_conso_poven_cabec.cod_regi_arri%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_cod_oper           t_cod_oper;
    v_prod_oid_prod_devu t_prod_oid_prod_devu;
    v_oid_cabe           t_oid_cabe;
    v_oid_peri_recl      t_oid_peri_recl;
    v_cod_regi_arri      t_cod_regi_arri;

    i      BINARY_INTEGER := 0;
    cuenta NUMBER := 0;

    periodoactivo bas_ctrl_fact.cod_peri%TYPE;
    periodoinicio seg_perio_corpo.cod_peri%TYPE;

    lsparametroantifne sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsparametroantifne := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_ANTI_FNE');

    SELECT cod_peri
      INTO periodoactivo
      FROM bas_ctrl_fact
     WHERE sta_camp = 0
       AND ind_camp_act = 1;

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_oper,
             v_prod_oid_prod_devu,
             v_oid_cabe,
             v_oid_peri_recl,
             v_cod_regi_arri

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          IF (v_cod_oper(i) IN ('FM',
                                'MF',
                                'FA',
                                'FP',
                                'PF')) THEN

            SELECT gen_pkg_gener.gen_fn_devuelve_des_perio(oid_peri)
              INTO periodoinicio
              FROM (SELECT rownum fila,
                           t.*
                      FROM (SELECT cra_perio.*
                              FROM cra_perio
                             WHERE fec_fina <
                                   (SELECT p.fec_fina
                                      FROM cra_perio p
                                     WHERE p.val_nomb_peri LIKE '%' || periodoactivo || '%')
                             ORDER BY fec_fina DESC) t) tt
             WHERE tt.fila = to_number(lsparametroantifne);

            SELECT COUNT(*)
              INTO cuenta
              FROM ped_solic_cabec c
             WHERE c.oid_soli_cabe = v_oid_cabe(i)
               AND EXISTS
             (SELECT 1
                      FROM int_lar_tipo_solici_pedido_dis ltsp
                     WHERE ltsp.tspa_oid_tipo_soli_pais = c.tspa_oid_tipo_soli_pais)
               AND gen_pkg_gener.gen_fn_devuelve_des_perio(c.perd_oid_peri) <= periodoactivo
               AND gen_pkg_gener.gen_fn_devuelve_des_perio(c.perd_oid_peri) >= periodoinicio;

            IF (cuenta > 0) THEN
              existe := TRUE;
            ELSE
              existe := FALSE;
            END IF;

          ELSE
            existe := TRUE;
          END IF;

          IF (existe = TRUE) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_ATENC_FFNNE_VAL1: ' || ls_sqlerrm);

  END sto_pr_spvd_atenc_ffnne_val1;

  /***************************************************************************
  Descripcion       : Validacion 2 de FFNE
  Fecha Creacion    : 08/07/2010
  Autor             : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_atenc_ffnne_val2
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_oper,
             det.prod_oid_prod_devu,
             cab.oid_cabe,
             cab.oid_peri_recl,
             cab.cod_regi_arri
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_prod_oid_prod_devu IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_devu%TYPE;

    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_oid_peri_recl IS TABLE OF int_solic_conso_poven_cabec.oid_peri_recl%TYPE;
    TYPE t_cod_regi_arri IS TABLE OF int_solic_conso_poven_cabec.cod_regi_arri%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_cod_oper           t_cod_oper;
    v_prod_oid_prod_devu t_prod_oid_prod_devu;
    v_oid_cabe           t_oid_cabe;
    v_oid_peri_recl      t_oid_peri_recl;
    v_cod_regi_arri      t_cod_regi_arri;

    i      BINARY_INTEGER := 0;
    cuenta NUMBER := 0;

    periodoactivo bas_ctrl_fact.cod_peri%TYPE;
    periodoinicio seg_perio_corpo.cod_peri%TYPE;

    lsparametroantifne sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsparametroantifne := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_ANTI_FNE');

    SELECT cod_peri
      INTO periodoactivo
      FROM bas_ctrl_fact
     WHERE sta_camp = 0
       AND ind_camp_act = 1;

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_oper,
             v_prod_oid_prod_devu,
             v_oid_cabe,
             v_oid_peri_recl,
             v_cod_regi_arri

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          IF (v_cod_oper(i) IN ('FM',
                                'MF',
                                'FA',
                                'FP',
                                'PF')) THEN

            SELECT gen_pkg_gener.gen_fn_devuelve_des_perio(oid_peri)
              INTO periodoinicio
              FROM (SELECT rownum fila,
                           t.*
                      FROM (SELECT cra_perio.*
                              FROM cra_perio
                             WHERE fec_fina <
                                   (SELECT p.fec_fina
                                      FROM cra_perio p
                                     WHERE p.val_nomb_peri LIKE '%' || periodoactivo || '%')
                             ORDER BY fec_fina DESC) t) tt
             WHERE tt.fila = to_number(lsparametroantifne);

            SELECT COUNT(*)
              INTO cuenta
              FROM ped_solic_cabec c
             WHERE c.oid_soli_cabe = v_oid_cabe(i)
               AND EXISTS
             (SELECT 1
                      FROM int_lar_tipo_solici_pedido_dis ltsp
                     WHERE ltsp.tspa_oid_tipo_soli_pais = c.tspa_oid_tipo_soli_pais)
               AND gen_pkg_gener.gen_fn_devuelve_des_perio(c.perd_oid_peri) <= periodoactivo
               AND gen_pkg_gener.gen_fn_devuelve_des_perio(c.perd_oid_peri) >= periodoinicio;

            IF (cuenta > 0) THEN

              --- Verifica si la Consejera es Nueva
              SELECT COUNT(*)
                INTO cuenta
                FROM mae_tipo_clasi_clien mcc,
                     mae_clien_tipo_subti m,
                     mae_clien_clasi      mc,
                     mae_clien            mmm
               WHERE mcc.cod_tipo_clas = '21'
                 AND m.oid_clie_tipo_subt = mc.ctsu_oid_clie_tipo_subt
                 AND mc.tccl_oid_tipo_clasi = mcc.oid_tipo_clas
                 AND m.clie_oid_clie = mmm.oid_clie
                 AND mmm.cod_clie = v_codclie(i);

              IF (cuenta > 0) THEN
                existe := TRUE;
              ELSE

                --- Verifica si el pedido fue chequeado
                SELECT COUNT(*)
                  INTO cuenta
                  FROM ped_solic_cabec c
                 WHERE c.oid_soli_cabe = v_oid_cabe(i)
                   AND EXISTS
                 (SELECT 1
                          FROM int_lar_tipo_solici_pedido_dis ltsp
                         WHERE ltsp.tspa_oid_tipo_soli_pais = c.tspa_oid_tipo_soli_pais)
                   AND c.recq_oid_resu_cheq IN
                       (SELECT oid_resu_cheq FROM rec_resul_chequ WHERE cod_resu_cheq = 'OK');

                IF (cuenta > 0) THEN

                  --- Verifica si el producto tiene incidencia en error de sacado
                  SELECT COUNT(*)
                    INTO cuenta
                    FROM rec_produ_fm fm
                   WHERE fm.cod_peri_inic <= periodoactivo
                     AND fm.cod_peri_fin >= periodoactivo
                     AND fm.cod_sap =
                         (SELECT cod_sap FROM mae_produ WHERE oid_prod = v_prod_oid_prod_devu(i))
                     AND fm.cod_regi = v_cod_regi_arri(i);

                  IF (cuenta > 0) THEN
                    existe := TRUE;
                  ELSE
                    existe := FALSE;
                  END IF;

                ELSE

                  existe := TRUE;

                END IF;

              END IF;

            ELSE
              existe := FALSE;
            END IF;

          ELSE
            existe := TRUE;
          END IF;

          IF (existe = TRUE) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_ATENC_FFNNE_VAL2: ' || ls_sqlerrm);

  END sto_pr_spvd_atenc_ffnne_val2;

  /***************************************************************************
  Descripcion       : Validacion 3 de FFNE
  Fecha Creacion    : 08/07/2010
  Autor             : Jesse James Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_atenc_ffnne_val3
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_oper,
             det.prod_oid_prod_devu,
             cab.oid_cabe,
             cab.oid_peri_recl,
             cab.cod_regi_arri
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_pais = cab.cod_pais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu;

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_prod_oid_prod_devu IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_devu%TYPE;

    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_oid_peri_recl IS TABLE OF int_solic_conso_poven_cabec.oid_peri_recl%TYPE;
    TYPE t_cod_regi_arri IS TABLE OF int_solic_conso_poven_cabec.cod_regi_arri%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_cod_oper           t_cod_oper;
    v_prod_oid_prod_devu t_prod_oid_prod_devu;
    v_oid_cabe           t_oid_cabe;
    v_oid_peri_recl      t_oid_peri_recl;
    v_cod_regi_arri      t_cod_regi_arri;

    i      BINARY_INTEGER := 0;
    cuenta NUMBER := 0;

    t_envi2          NUMBER;
    t_devu2          NUMBER;
    t_oid_oper2      NUMBER;
    t_oid_tipo_oper2 NUMBER;

    periodoactivo           bas_ctrl_fact.cod_peri%TYPE;
    periodoinicio           seg_perio_corpo.cod_peri%TYPE;
    periodoiniciorecurrente seg_perio_corpo.cod_peri%TYPE;
    lsparametroantifne      sto_param_gener_occrr.val_param%TYPE;
    lsparametrocantfne      sto_param_gener_occrr.val_param%TYPE;
    lsparametrocampfne      sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsparametroantifne := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_ANTI_FNE');
    lsparametrocantfne := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_CANT_FNE');
    lsparametrocampfne := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_CAMP_FNE');

    SELECT cod_peri
      INTO periodoactivo
      FROM bas_ctrl_fact
     WHERE sta_camp = 0
       AND ind_camp_act = 1;

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_oper,
             v_prod_oid_prod_devu,
             v_oid_cabe,
             v_oid_peri_recl,
             v_cod_regi_arri

             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP

          IF (v_cod_oper(i) IN ('FM',
                                'MF',
                                'FA',
                                'FP',
                                'PF')) THEN

            SELECT gen_pkg_gener.gen_fn_devuelve_des_perio(oid_peri)
              INTO periodoinicio
              FROM (SELECT rownum fila,
                           t.*
                      FROM (SELECT cra_perio.*
                              FROM cra_perio
                             WHERE fec_fina <
                                   (SELECT p.fec_fina
                                      FROM cra_perio p
                                     WHERE p.val_nomb_peri LIKE '%' || periodoactivo || '%')
                             ORDER BY fec_fina DESC) t) tt
             WHERE tt.fila = to_number(lsparametroantifne);

            SELECT COUNT(*)
              INTO cuenta
              FROM ped_solic_cabec c
             WHERE c.oid_soli_cabe = v_oid_cabe(i)
               AND EXISTS
             (SELECT 1
                      FROM int_lar_tipo_solici_pedido_dis ltsp
                     WHERE ltsp.tspa_oid_tipo_soli_pais = c.tspa_oid_tipo_soli_pais)
               AND gen_pkg_gener.gen_fn_devuelve_des_perio(c.perd_oid_peri) <= periodoactivo
               AND gen_pkg_gener.gen_fn_devuelve_des_perio(c.perd_oid_peri) >= periodoinicio;

            IF (cuenta > 0) THEN

              --- Verifica si la Consejera es Nueva
              SELECT COUNT(*)
                INTO cuenta
                FROM mae_tipo_clasi_clien mcc,
                     mae_clien_tipo_subti m,
                     mae_clien_clasi      mc,
                     mae_clien            mmm
               WHERE mcc.cod_tipo_clas = '21'
                 AND m.oid_clie_tipo_subt = mc.ctsu_oid_clie_tipo_subt
                 AND mc.tccl_oid_tipo_clasi = mcc.oid_tipo_clas
                 AND m.clie_oid_clie = mmm.oid_clie
                 AND mmm.cod_clie = v_codclie(i);

              IF (cuenta > 0) THEN
                existe := TRUE;
              ELSE

                --- Verifica si el pedido fue chequeado
                SELECT COUNT(*)
                  INTO cuenta
                  FROM ped_solic_cabec c
                 WHERE c.oid_soli_cabe = v_oid_cabe(i)
                   AND EXISTS
                 (SELECT 1
                          FROM int_lar_tipo_solici_pedido_dis ltsp
                         WHERE ltsp.tspa_oid_tipo_soli_pais = c.tspa_oid_tipo_soli_pais)
                   AND c.recq_oid_resu_cheq IN
                       (SELECT oid_resu_cheq FROM rec_resul_chequ WHERE cod_resu_cheq = 'OK');

                IF (cuenta > 0) THEN

                  --- Verifica si el producto tiene incidencia en error de sacado
                  SELECT COUNT(*)
                    INTO cuenta
                    FROM rec_produ_fm fm
                   WHERE fm.cod_peri_inic <= periodoactivo
                     AND fm.cod_peri_fin >= periodoactivo
                     AND fm.cod_sap =
                         (SELECT cod_sap FROM mae_produ WHERE oid_prod = v_prod_oid_prod_devu(i))
                     AND fm.cod_regi = v_cod_regi_arri(i);

                  IF (cuenta > 0) THEN
                    existe := TRUE;
                  ELSE
                    existe := FALSE;
                  END IF;

                ELSE

                  --- Inicio de la validacion de recurrencia
                  SELECT gen_pkg_gener.gen_fn_devuelve_des_perio(oid_peri)
                    INTO periodoiniciorecurrente
                    FROM (SELECT rownum fila,
                                 t.*
                            FROM (SELECT cra_perio.*
                                    FROM cra_perio
                                   WHERE fec_fina <
                                         (SELECT p.fec_fina
                                            FROM cra_perio p
                                           WHERE p.val_nomb_peri LIKE '%' || periodoactivo || '%')
                                   ORDER BY fec_fina DESC) t) tt
                   WHERE tt.fila = to_number(lsparametrocampfne);

                  SELECT COUNT(*)
                    INTO cuenta
                    FROM rec_cabec_recla a,
                         rec_opera_recla b,
                         rec_tipos_opera e,
                         rec_opera       f
                   WHERE a.oid_cabe_recl = b.care_oid_cabe_recl
                     AND b.tiop_oid_tipo_oper = e.oid_tipo_oper
                     AND e.rope_oid_oper = f.oid_oper
                     AND f.ind_falt_merc = 1
                     AND a.clie_oid_clie =
                         (SELECT oid_clie FROM mae_clien WHERE cod_clie = v_codclie(i))
                     AND gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_recl) <
                         periodoactivo
                     AND gen_pkg_gener.gen_fn_devuelve_des_perio(a.perd_oid_peri_recl) >=
                         periodoiniciorecurrente; --periodoactivo;

                  -- Si es recurrente
                  IF (cuenta >= to_number(lsparametrocantfne)) THEN

                    -- Verifica si el producto tiene incidencia en error de sacado
                    SELECT COUNT(*)
                      INTO cuenta
                      FROM rec_produ_fm fm
                     WHERE fm.cod_peri_inic <= periodoactivo
                       AND fm.cod_peri_fin >= periodoactivo
                       AND fm.cod_sap =
                           (SELECT cod_sap FROM mae_produ WHERE oid_prod = v_prod_oid_prod_devu(i))
                       AND fm.cod_regi = v_cod_regi_arri(i);

                    IF (cuenta > 0) THEN
                      existe := TRUE;
                    ELSE
                      existe := FALSE;
                    END IF;

                  ELSE

                    -- Si NO es recurrente

                    SELECT COUNT(*)
                      INTO cuenta
                      FROM rec_produ_fm fm
                     WHERE fm.cod_peri_inic <= periodoactivo
                       AND fm.cod_peri_fin >= periodoactivo
                       AND fm.cod_sap =
                           (SELECT cod_sap FROM mae_produ WHERE oid_prod = v_prod_oid_prod_devu(i))
                       AND fm.cod_regi = v_cod_regi_arri(i);

                    IF (cuenta > 0) THEN
                      existe := TRUE;
                    ELSE
                      existe := TRUE; --SOLO A ENVIAR

                      SELECT tspa_oid_soli_con_stoc,
                             tspa_oid_soli_pais_gene,
                             oid_oper,
                             oid_tipo_oper
                        INTO t_envi2,
                             t_devu2,
                             t_oid_oper2,
                             t_oid_tipo_oper2
                        FROM rec_opera       op,
                             rec_tipos_opera top
                       WHERE top.rope_oid_oper = op.oid_oper
                         AND op.cod_oper = 'FM'
                         AND top.val_tipo_oper = '01';

                      UPDATE int_solic_conso_poven_detal l
                         SET l.tip_refe                     = 'F',
                             l.cod_oper                     = 'FM',
                             l.cod_tipo_oper                = '01',
                             l.oid_oper                     = t_oid_oper2,
                             l.oid_tipo_oper                = t_oid_tipo_oper2,
                             l.ind_ingr_envi                = 0,
                             l.ind_ingr_devu                = 1,
                             l.ind_devu_fisi                = 1,
                             l.ind_envi_gener_devu          = 0,
                             l.ind_devu_gener_envi          = 1,
                             l.ind_envi_fact                = 1,
                             l.ind_devu_fact                = 1,
                             l.cod_prec_envi                = 'F',
                             l.cod_prec                     = 'P',
                             l.tspa_oid_tipo_soli_pais_devu = t_devu2,
                             l.tspa_oid_tipo_soli_pais_envu = t_envi2
                       WHERE l.sec_nume_docu = v_sec_nume_docu(i);

                    END IF;
                  END IF;

                END IF;

              END IF;

            ELSE
              existe := FALSE;
            END IF;

          ELSE
            existe := TRUE;
          END IF;

          IF (existe = TRUE) THEN

            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_ATENC_FFNNE_VAL3: ' || ls_sqlerrm);

  END sto_pr_spvd_atenc_ffnne_val3;

  /***************************************************************************
  Descripcion       : VALIDACION FNNE VENEZUELA
  Fecha Creacion    : 05/01/2009
  Autor             : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_cntrl_fnne
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote,
             det.cod_oper,
             det.ind_apro_fnne,
             (SELECT COUNT(1)
                FROM ped_solic_posic     c,
                     ped_solic_cabec     d,
                     ped_tipo_solic_pais tsp,
                     ped_tipo_solic      ts
               WHERE c.oid_soli_posi = det.oid_soli_posi_devu
                 AND c.soca_oid_soli_cabe = d.oid_soli_cabe
                 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                 AND oid_tipo_soli_pais = d.tspa_oid_tipo_soli_pais
                 AND cod_tipo_soli IN ('SEFM',
                                       'SEFP',
                                       'SEMF',
                                       'SEPF')) ind_recl_recl,
             'S' ind_regi_vali,
             cab.ind_orig,
             det.mot_spv
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND occ.sec_nume_docu_cabe = cab.sec_nume_docu;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_ind_apro_fnne IS TABLE OF int_solic_conso_poven_detal.ind_apro_fnne%TYPE;
    TYPE t_ind_orig IS TABLE OF int_solic_conso_poven_cabec.ind_orig%TYPE;
    TYPE t_mot_spv IS TABLE OF int_solic_conso_poven_detal.mot_spv%TYPE;
    TYPE t_ind_recl_recl IS TABLE OF NUMBER;
    TYPE t_ind_regi_vali IS TABLE OF VARCHAR2(1);

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;
    v_cod_oper      t_cod_oper;
    v_ind_apro_fnne t_ind_apro_fnne;
    v_ind_recl_recl t_ind_recl_recl;
    v_ind_regi_vali t_ind_regi_vali;
    v_ind_orig      t_ind_orig;
    v_mot_spv       t_mot_spv;

    lsparametrovalSPVD44   sto_param_gener_occrr.val_param%TYPE;
    lsparametromotSPVD44   sto_param_gener_occrr.val_param%TYPE;

    i BINARY_INTEGER := 0;

  BEGIN

    lsparametrovalSPVD44 := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_SPVD44');
    lsparametromotSPVD44 := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_MOT_SPVD44');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote,
             v_cod_oper,
             v_ind_apro_fnne,
             v_ind_recl_recl,
             v_ind_regi_vali,
             v_ind_orig,
             v_mot_spv       LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          IF (v_cod_oper(i) IN ('FM','FA')) THEN

             IF ((v_ind_orig(i) = '2' or v_ind_orig(i) = 'C') and
                 v_mot_spv(i) = lsparametromotSPVD44 and
                 v_mot_spv(i) is not null) THEN   --- si es por oficina y el motivo es el de spvd44

                v_ind_regi_vali(i) := 'S';

             else
            IF (v_ind_recl_recl(i) = 0) THEN   ---- Si el reclamo es sobre un pedido

              IF lsparametrovalSPVD44 = '1' THEN   --- nueva forma de trabajar con spvd44
                IF v_cod_oper(i) = 'FA' THEN

                  UPDATE int_solic_conso_poven_detal
                     SET cod_oper      = 'FM',
                         ind_apro_fnne = '1',
                         tip_refe      = 'F'
                   WHERE num_lote = v_num_lote(i)
                     AND sec_nume_docu = v_sec_nume_docu(i);

                  v_ind_regi_vali(i) := 'N';

                END IF;
              else

              IF v_cod_oper(i) = 'FA' AND v_ind_apro_fnne(i) IS NULL THEN

                UPDATE int_solic_conso_poven_detal
                   SET cod_oper      = 'FM',
                       ind_apro_fnne = '1',
                       tip_refe      = 'F'
                 WHERE num_lote = v_num_lote(i)
                   AND sec_nume_docu = v_sec_nume_docu(i);

                v_ind_regi_vali(i) := 'N';

              ELSIF v_cod_oper(i) = 'FA' AND v_ind_apro_fnne(i) = '1' THEN

                UPDATE int_solic_conso_poven_detal
                   SET ind_apro_fnne = '0'
                 WHERE num_lote = v_num_lote(i)
                   AND sec_nume_docu = v_sec_nume_docu(i);

              ELSIF v_cod_oper(i) = 'FM' AND v_ind_apro_fnne(i) IS NULL THEN

                v_ind_regi_vali(i) := 'S';

              ELSE

                v_ind_regi_vali(i) := 'N';

              END IF;


              END IF;

            ELSE
              IF lsparametrovalSPVD44 = '1' THEN   --- nueva forma de trabajar con spvd44
                IF v_cod_oper(i) <> 'FA' THEN

                  UPDATE int_solic_conso_poven_detal
                     SET cod_oper      = 'FA',
                         ind_apro_fnne = '1',
                         tip_refe      = 'G',
                         cod_vent_dese = null,
                         can_prod_dese = 0
                   WHERE num_lote = v_num_lote(i)
                     AND sec_nume_docu = v_sec_nume_docu(i);

                  v_ind_regi_vali(i) := 'N';

                END IF;
              END IF;

            END IF;
             END IF;

          END IF;
        END LOOP;

        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i)
             AND v_ind_regi_vali(i) = 'S';

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_CNTRL_FNNE: ' || ls_sqlerrm);

  END sto_pr_spvd_cntrl_fnne;

  /***************************************************************************
  Descripcion       : VALIDACION FNNE VENEZUELA
  Fecha Creacion    : 11/03/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_cntrl_fnne
  (
    pscodigopais      in  VARCHAR2,
    psCodOperSICC     in  VARCHAR2,
    psTipoOperSICC    in  VARCHAR2,
    psOidSoliPosi     in  number,
    pscodigomotivo    in  VARCHAR2
  )  RETURN BOOLEAN  IS

    lsparametrovalSPVD44   sto_param_gener_occrr.val_param%TYPE;
    lsparametromotSPVD44   sto_param_gener_occrr.val_param%TYPE;

    existe BOOLEAN := TRUE;

    i BINARY_INTEGER := 0;
    contador BINARY_INTEGER := 0;

  BEGIN

    lsparametrovalSPVD44 := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_SPVD44');
    lsparametromotSPVD44 := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_MOT_SPVD44');

          existe := true;

          IF (psCodOperSICC IN ('FM','FA')) THEN

             IF (pscodigomotivo = lsparametromotSPVD44 and
                 pscodigomotivo is not null) THEN   --- si es por oficina y el motivo es el de spvd44

                 existe := true;

             else

                SELECT COUNT(1) into contador
                FROM ped_solic_posic     c,
                     ped_solic_cabec     d,
                     ped_tipo_solic_pais tsp,
                     ped_tipo_solic      ts
               WHERE c.oid_soli_posi = psOidSoliPosi
                 AND c.soca_oid_soli_cabe = d.oid_soli_cabe
                 AND tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                 AND oid_tipo_soli_pais = d.tspa_oid_tipo_soli_pais
                 AND cod_tipo_soli IN ('SEFM',
                                       'SEFP',
                                       'SEMF',
                                       'SEPF');

                IF (contador = 0) THEN   ---- Si el reclamo es sobre un pedido
                   IF psCodOperSICC = 'FA' THEN
                      existe := false;  ----- Tiene que ingresar un FM
                   END IF;
                 ELSE
                    IF psCodOperSICC <> 'FA' THEN
                       existe := false;  ---- Tiene que ingresar un FA
                    END IF;
                END IF;
             END IF;

          END IF;

  RETURN (existe);

  END sto_fn_spvd_cntrl_fnne;


  /***************************************************************************
  Descripcion       : Validacion por Motivo de Devolucion de Mercaderia
  Fecha Creacion    : 23/08/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_motiv_devol
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ--,
--             rec_motiv_devol             b
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
--         AND det.mot_spv = b.cod_moti_devo(+)
--         AND b.cod_moti_devo IS NOT NULL;
         AND
         (
         exists (select 1 from REC_VALID_MOTIV_DEVUL a
         where a.cod_oper=det.cod_oper
         and det.cod_tipo_oper=decode(a.cod_tipo_oper,null,det.cod_tipo_oper,a.cod_tipo_oper)
         and a.cod_moti=det.mot_spv
         )
         or not exists (select 1 from REC_VALID_MOTIV_DEVUL a
         where a.cod_oper=det.cod_oper)
         );

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;
    i               BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_MOTIV_DEVOL: ' || ls_sqlerrm);

  END sto_pr_spvd_motiv_devol;

  /***************************************************************************
  Descripcion       : Validacion por Motivo de Devolucion de Mercaderia
  Fecha Creacion    : 23/08/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  FUNCTION sto_fn_spvd_motiv_devol
  (
    pscodoper     VARCHAR2,
    pstipoper     VARCHAR2,
    psmotpv       VARCHAR2
  ) RETURN BOOLEAN IS

    lvalopemot  NUMBER := 0;
    lvalope     NUMBER := 0;
    existe     BOOLEAN := TRUE;

  BEGIN

      --- si la operacion esta en la tabla
      select count(*) into lvalope  from REC_VALID_MOTIV_DEVUL a
      where a.cod_oper = pscodoper;

      --- Valida si la operacion tipo y motivo esta en la tabla
      select count(*) into lvalopemot from REC_VALID_MOTIV_DEVUL a
      where a.cod_oper = pscodoper
      and pstipoper = decode(a.cod_tipo_oper,null,pstipoper,a.cod_tipo_oper)
      and a.cod_moti = psmotpv;

      --- Si no existe la operacion en la tabla no se valida osea todo esta ok
      if lvalope = 0 then
         existe := true;
      else
         --- Si no existe el tipo asociado al motivo es un error
         if lvalopemot = 0 then
            existe := false;
         else
            existe := true;
         end if;
      end if;

    RETURN (existe);

  END sto_fn_spvd_motiv_devol;

  /***************************************************************************
  Descripcion       : Validacion Faltantes de Premios
  Fecha Creacion    : 16/09/2010
  Autor             : Jesse James Rios Franco
  **************************************************************************/
  PROCEDURE sto_pr_spvd_falta_premi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         occ
       WHERE det.cod_pais = pscodigopais
         AND occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND det.cod_oper <> 'FP';

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;
    i               BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;

    LOOP

      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;

    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_FALTA_PREMI: ' || ls_sqlerrm);
  END sto_pr_spvd_falta_premi;

  /***************************************************************************
    Descripcion       : Validacion Faltantes de Pedido Chequeado
    Fecha Creacion    : 23/03/2011
    Autor             : Jose Luis Rodriguez
  **************************************************************************/
  PROCEDURE sto_pr_spvd_falta_pedid_chequ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_oper,
             ped.val_nume_soli
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             ped_solic_cabec             ped,
             ---sto_docum_digit         occ
             sto_tmp_docum_digit occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.oid_cabe = ped.oid_soli_cabe
         AND cab.cod_pais = det.cod_pais;
    ----and cab.sec_nume_docu in(18350600 ,18350823 );
    --- AND det.cod_oper IN('FA','FM','MF');

    TYPE t_cod_pais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_num_soli IS TABLE OF ped_solic_cabec.val_nume_soli%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;

    v_cod_pais      t_cod_pais;
    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;
    v_cod_oper      t_cod_oper;
    v_num_soli      t_num_soli;
    i               BINARY_INTEGER := 0;

    v_ind_pedi_cheq sto_param_gener_occrr.val_param%TYPE;
    v_cod_pedi_cheq VARCHAR2(2);

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    -- Se obtiene el indicador de Faltante de Pedido Chequeado
    v_ind_pedi_cheq := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_IND_FALPCHK');

    /*SELECT a.val_param
     INTO v_ind_pedi_cheq
     FROM sto_param_gener_occrr a
    WHERE a.cod_para = 'STO_IND_FALPCHK';*/

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_cod_pais,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_oper,
             v_num_soli LIMIT w_filas;

      IF v_cod_pais.count > 0 THEN

        FOR i IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          -- Si el indicador esta apagado todos los registros se toman como validos
          IF (v_ind_pedi_cheq = '0' OR
             (v_cod_oper(i) <> 'FA' AND v_cod_oper(i) <> 'FM' AND v_cod_oper(i) <> 'MF')) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_cod_pais(i)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          ELSIF v_ind_pedi_cheq = '1' AND
                (v_cod_oper(i) = 'FA' OR v_cod_oper(i) = 'FM' OR v_cod_oper(i) = 'MF') THEN
            -- Se obtiene el codigo de chequeado
            v_cod_pedi_cheq := rec_pkg_proce.rec_fn_resul_chequ_pedid(v_num_soli(i));
            IF (v_cod_pedi_cheq != 'OK') THEN
              -- Si el valor del codigo de cheaueado es diferente a OK se toma como correcto
              UPDATE sto_docum_digit occ
                 SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                     occ.cod_ulti_vali_exit = pscodigovalidactual,
                     occ.usu_modi           = psusuario,
                     occ.fec_modi           = SYSDATE
               WHERE occ.cod_pais = v_cod_pais(i)
                 AND occ.cod_tipo_docu = pscodigotipodoc
                 AND occ.num_lote = v_num_lote(i)
                 AND occ.sec_nume_docu = v_sec_nume_docu(i);
            END IF;

          END IF;
        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;
    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_FALTA_PEDID_CHEQU: ' || ls_sqlerrm);
  END sto_pr_spvd_falta_pedid_chequ;

  /***************************************************************************
  Descripcion       : Verifica faltante pedido chequeado (SPVD-47)
  Fecha Creacion    : 23/07/2013
  Autor             : Sandro Quintana Aponte
             OUTPUT  si es TRUE esta ok, sino es error
  ***************************************************************************/
  FUNCTION sto_fn_spvd_falta_pedid_chequ
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    psnumpedi          VARCHAR2
  ) RETURN BOOLEAN IS


    v_cod_oper            int_solic_conso_poven_detal.cod_oper%TYPE;

    cuenta       NUMBER;
    chequeo      NUMBER;
    v_ind_pedi_cheq   sto_param_gener_occrr.val_param%TYPE;

    v_cod_pedi_cheq VARCHAR2(2);

    existe BOOLEAN := TRUE;

  BEGIN

    -- Se obtiene el indicador de Faltante de Pedido Chequeado
    v_ind_pedi_cheq := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_IND_FALPCHK');

    v_cod_oper := pscodoper;

          cuenta := 0;

          /*
          -- Si el indicador esta apagado todos los registros se toman como validos
          IF (v_ind_pedi_cheq = '0' OR
             (v_cod_oper <> 'FA' AND v_cod_oper <> 'FM' AND v_cod_oper <> 'MF')) THEN

             cuenta := 1;
             
          ELSIF v_ind_pedi_cheq = '1' AND
                (v_cod_oper = 'FA' OR v_cod_oper = 'FM' OR v_cod_oper = 'MF') THEN
            -- Se obtiene el codigo de chequeado
            v_cod_pedi_cheq := rec_pkg_proce.rec_fn_resul_chequ_pedid(psnumpedi);
            IF (v_cod_pedi_cheq != 'OK') THEN
              cuenta := 1;
            END IF;

          END IF;
          */

          -- Si el indicador esta apagado todos los registros se toman como validos
          IF (v_ind_pedi_cheq = '0' OR
             (v_cod_oper <> 'FA' AND v_cod_oper <> 'FM' AND v_cod_oper <> 'MF')) THEN

             cuenta := 1;
             
          ELSE
            IF v_ind_pedi_cheq = '1' AND
                (v_cod_oper = 'FA' OR v_cod_oper = 'FM' OR v_cod_oper = 'MF') THEN
                -- Se obtiene el codigo de chequeado
                v_cod_pedi_cheq := rec_pkg_proce.rec_fn_resul_chequ_pedid(psnumpedi);
                IF (v_cod_pedi_cheq != 'OK') THEN
                  cuenta := 1;
                END IF;
            else
               IF v_ind_pedi_cheq = '2' AND
                  (v_cod_oper = 'FA' OR v_cod_oper = 'FM' OR v_cod_oper = 'MF') THEN

                  select count(*) into chequeo
                  from ped_Segui_pedid psp, ped_solic_Cabec psc
                  where psp.soca_oid_soli_Cabe = psc.oid_soli_Cabe
                  and psc.val_nume_soli = psnumpedi
                  and psp.fec_cheq is not null
                  and psp.hor_cheq = '01';

                  cuenta := 1 ;
                  if chequeo = 1 then
                     cuenta := 0 ;
                  end if;

                  --- cuenta := 1;
              
               END IF;
            END IF;
          END IF;


       if cuenta = 0 then
          existe := false;
       else
          existe := true;
       end if;

    RETURN (existe);

  END sto_fn_spvd_falta_pedid_chequ;

  /***************************************************************************
  Descripcion       : Validacion Tipo Operacion reemplaza a
                      sto_pr_spvd_opera y sto_pr_spvd_opera_accion
  Fecha Creacion    : 23/03/2011
  Autor             : Sandro Quintana Aponte
  **************************************************************************/
  PROCEDURE sto_pr_spvd_tipo_opera
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.cod_vent_devu,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.oid_pais,
             det.oid_oper,
             det.oid_tipo_oper,
             det.cod_tipo_oper,
             det.cod_oper,
             det.cod_vent_dese,
             det.ind_acci,
             cab.ind_expr,
             det.mot_spv,
             rto.cod_oper_sicc,
             rto.tipo_oper_sicc,
             rto.ind_express,
             rto.val_mot_spv,
             rto.cod_oper_mot,
             rto.ind_ocr,
             rto.ind_accion_fne,
             rto.cod_oper_acc
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             rec_digit_homol_oper        rto,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.oid_pais IS NOT NULL
         AND det.tip_refe IS NOT NULL
         AND cab.num_docu = det.num_docu
         AND cab.cod_clie = det.cod_clie
         AND cab.cod_peri = det.cod_peri
         AND cab.cod_pais = det.cod_pais
         AND cab.num_lote = det.num_lote
         AND det.tip_refe = rto.cod_oper;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_num_docu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tip_refe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_oid_pais IS TABLE OF int_solic_conso_poven_detal.oid_pais%TYPE;
    TYPE t_oid_oper IS TABLE OF int_solic_conso_poven_detal.oid_oper%TYPE;
    TYPE t_oid_tipo_oper IS TABLE OF int_solic_conso_poven_detal.oid_tipo_oper%TYPE;
    TYPE t_cod_tipo_oper IS TABLE OF int_solic_conso_poven_detal.cod_tipo_oper%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;
    TYPE t_ind_acci IS TABLE OF int_solic_conso_poven_detal.ind_acci%TYPE;
    TYPE t_ind_expr IS TABLE OF int_solic_conso_poven_cabec.ind_expr%TYPE;
    TYPE t_mot_spv IS TABLE OF int_solic_conso_poven_detal.mot_spv%TYPE;
    TYPE t_cod_oper_sicc IS TABLE OF rec_digit_homol_oper.cod_oper_sicc%TYPE;
    TYPE t_tipo_oper_sicc IS TABLE OF rec_digit_homol_oper.tipo_oper_sicc%TYPE;
    TYPE t_ind_express IS TABLE OF rec_digit_homol_oper.ind_express%TYPE;
    TYPE t_val_mot_spv IS TABLE OF rec_digit_homol_oper.val_mot_spv%TYPE;
    TYPE t_cod_oper_mot IS TABLE OF rec_digit_homol_oper.cod_oper_mot%TYPE;
    TYPE t_ind_ocr IS TABLE OF rec_digit_homol_oper.ind_ocr%TYPE;
    TYPE t_ind_accion_fne IS TABLE OF rec_digit_homol_oper.ind_accion_fne%TYPE;
    TYPE t_cod_oper_acc IS TABLE OF rec_digit_homol_oper.cod_oper_acc%TYPE;

    lsparametrovalMOTFP   sto_param_gener_occrr.val_param%TYPE;

    v_cod_pais           t_cod_pais;
    v_cod_peri           t_cod_peri;
    v_cod_clie           t_cod_clie;
    v_num_docu           t_num_docu;
    v_tip_refe           t_tip_refe;
    v_cod_vent_devu      t_cod_vent_devu;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;
    v_oid_pais           t_oid_pais;
    v_oid_oper           t_oid_oper;
    v_oid_tipo_oper      t_oid_tipo_oper;
    v_cod_tipo_oper      t_cod_tipo_oper;
    v_cod_oper           t_cod_oper;
    v_cod_vent_dese      t_cod_vent_dese;
    v_ind_acci           t_ind_acci;
    v_ind_expr           t_ind_expr;
    v_mot_spv            t_mot_spv;

    v_cod_oper_sicc  t_cod_oper_sicc;
    v_tipo_oper_sicc t_tipo_oper_sicc;
    v_ind_express    t_ind_express;
    v_val_mot_spv    t_val_mot_spv;
    v_cod_oper_mot   t_cod_oper_mot;
    v_ind_ocr        t_ind_ocr;
    v_ind_accion_fne t_ind_accion_fne;
    v_cod_oper_acc   t_cod_oper_acc;

    v_codoper             rec_opera.cod_oper%TYPE;
    v_valtipooper         rec_tipos_opera.val_tipo_oper%TYPE;
    v_oidoper             rec_opera.oid_oper%TYPE;
    v_oidtipooper         rec_tipos_opera.oid_tipo_oper%TYPE;
    v_indingrenvi         rec_opera.ind_ingr_envi%TYPE;
    v_valingrdevu         rec_opera.val_ingr_devu%TYPE;
    v_indenvigenedevu     rec_opera.ind_envi_gene_devu%TYPE;
    v_inddevugeneenvi     rec_opera.ind_devu_gene_envi%TYPE;
    v_inddevufisifact     rec_opera.ind_devu_fisi_fact%TYPE;
    v_inddevuestafact     rec_tipos_opera.ind_devu_esta_fact%TYPE;
    v_indenviestafact     rec_tipos_opera.ind_envi_esta_fact%TYPE;
    v_codprecenvi         rec_preci_envia.cod_prec_envi%TYPE;
    v_codprec             rec_preci.cod_prec%TYPE;
    v_tspaoidsoliconstoc  rec_opera.tspa_oid_soli_con_stoc%TYPE;
    v_tspaoidsolipaisgene rec_opera.tspa_oid_soli_pais_gene%TYPE;
    v_numdiashaciatra     rec_tipos_opera.num_dias_haci_atra%TYPE;
    v_indgaraprem         rec_param_opera.ind_gara_prem%TYPE;
    v_indtrueprem         rec_param_opera.ind_true_prem%TYPE;

    lv_codigooperacion     int_solic_conso_poven_detal.cod_oper%TYPE;
    lv_codigotipooperacion int_solic_conso_poven_detal.cod_tipo_oper%TYPE;

    --excepciones
    no_hay_operacion EXCEPTION;

  BEGIN

    ---- Recupera el motivo para cambiar a FALTANTE DE PREMIO

    lsparametrovalMOTFP := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_MOT_SPV_FP');

    if lsparametrovalMOTFP is null or lsparametrovalMOTFP = ''  then
       lsparametrovalMOTFP := '07';
    end if;

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_num_docu,
             v_tip_refe,
             v_cod_vent_devu,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_oid_pais,
             v_oid_oper,
             v_oid_tipo_oper,
             v_cod_tipo_oper,
             v_cod_oper,
             v_cod_vent_dese,
             v_ind_acci,
             v_ind_expr,
             v_mot_spv,
             v_cod_oper_sicc,
             v_tipo_oper_sicc,
             v_ind_express,
             v_val_mot_spv,
             v_cod_oper_mot,
             v_ind_ocr,
             v_ind_accion_fne,
             v_cod_oper_acc

             LIMIT w_filas;

      IF v_cod_pais.count > 0 THEN

        FOR i IN v_cod_pais.first .. v_cod_pais.last
        LOOP
          lv_codigotipooperacion := v_tipo_oper_sicc(i);
          lv_codigooperacion     := v_cod_oper_sicc(i);

          -- Valida si es OCR Corporativo y se tiene que validar la accion y la accion es Enviar
          IF (v_ind_ocr(i) = '1' AND v_ind_accion_fne(i) = '1' AND v_ind_acci(i) = '1') THEN
            lv_codigooperacion := v_cod_oper_acc(i);
          END IF;

          -- Valida si evalua el Motivo 07 convierte en premio
          ----IF (v_val_mot_spv(i) = '1' AND v_mot_spv(i) = '07') THEN
          IF (v_val_mot_spv(i) = '1' AND v_mot_spv(i) = lsparametrovalMOTFP) THEN
            lv_codigooperacion := v_cod_oper_mot(i);
          END IF;

          -- Si es Express cambia la operacion  EJM  de CM a  MC
          IF (v_ind_express(i) = '1' AND v_ind_expr(i) = '1') THEN
            lv_codigooperacion := concat(substr(lv_codigooperacion,
                                                2,
                                                1),
                                         substr(lv_codigooperacion,
                                                1,
                                                1));
          END IF;

          IF (lv_codigooperacion IS NOT NULL) THEN
            SELECT ra.cod_oper,
                   ra.val_tipo_oper,
                   ra.oid_oper,
                   ra.oid_tipo_oper,
                   ra.ind_ingr_envi,
                   ra.val_ingr_devu,
                   ra.ind_envi_gene_devu,
                   ra.ind_devu_gene_envi,
                   ra.ind_devu_fisi_fact,
                   ra.ind_devu_esta_fact,
                   ra.ind_envi_esta_fact,
                   ra.cod_prec_envi,
                   ra.cod_prec,
                   ra.tspa_oid_soli_con_stoc,
                   ra.tspa_oid_soli_pais_gene,
                   ra.num_dias_haci_atra,
                   decode(e.ind_gara_prem,
                          NULL,
                          0,
                          e.ind_gara_prem),
                   decode(e.ind_true_prem,
                          NULL,
                          0,
                          e.ind_true_prem)
              INTO v_codoper,
                   v_valtipooper,
                   v_oidoper,
                   v_oidtipooper,
                   v_indingrenvi,
                   v_valingrdevu,
                   v_indenvigenedevu,
                   v_inddevugeneenvi,
                   v_inddevufisifact,
                   v_inddevuestafact,
                   v_indenviestafact,
                   v_codprecenvi,
                   v_codprec,
                   v_tspaoidsoliconstoc,
                   v_tspaoidsolipaisgene,
                   v_numdiashaciatra,
                   v_indgaraprem,
                   v_indtrueprem
              FROM (SELECT a.cod_oper,
                           b.val_tipo_oper,
                           a.oid_oper,
                           b.oid_tipo_oper,
                           a.ind_ingr_envi,
                           a.val_ingr_devu,
                           a.ind_envi_gene_devu,
                           a.ind_devu_gene_envi,
                           a.ind_devu_fisi_fact,
                           b.ind_devu_esta_fact,
                           b.ind_envi_esta_fact,
                           c.cod_prec_envi,
                           d.cod_prec,
                           a.tspa_oid_soli_con_stoc,
                           a.tspa_oid_soli_pais_gene,
                           b.num_dias_haci_atra
                      FROM rec_opera       a,
                           rec_tipos_opera b,
                           rec_preci_envia c,
                           rec_preci       d
                     WHERE a.oid_oper = b.rope_oid_oper
                       AND a.penv_oid_precio_envia = c.oid_prec_envi
                       AND a.peci_oid_peci = d.oid_prec
                       AND a.cod_oper = lv_codigooperacion
                       AND b.val_tipo_oper = lv_codigotipooperacion) ra,
                   rec_param_opera e
             WHERE ra.cod_oper = e.cod_oper(+)
               AND ra.val_tipo_oper = e.cod_tipo_oper(+);

            UPDATE int_solic_conso_poven_detal
               SET oid_tipo_oper                = v_oidtipooper,
                   ind_ingr_envi                = v_indingrenvi,
                   ind_ingr_devu                = v_valingrdevu,
                   ind_devu_fisi                = v_inddevufisifact,
                   ind_envi_gener_devu          = v_indenvigenedevu,
                   ind_devu_gener_envi          = v_inddevugeneenvi,
                   ind_devu_fact                = v_inddevuestafact,
                   ind_envi_fact                = v_indenviestafact,
                   cod_prec_envi                = v_codprecenvi,
                   cod_prec                     = v_codprec,
                   num_dias_atra                = v_numdiashaciatra,
                   ind_gara_prem                = v_indgaraprem,
                   ind_true_prem                = v_indtrueprem,
                   tspa_oid_tipo_soli_pais_envu = v_tspaoidsoliconstoc,
                   tspa_oid_tipo_soli_pais_devu = v_tspaoidsolipaisgene,
                   oid_oper                     = v_oidoper,
                   cod_oper                     = lv_codigooperacion,
                   cod_tipo_oper                = lv_codigotipooperacion
             WHERE cod_pais = v_cod_pais(i)
               AND cod_peri = v_cod_peri(i)
               AND cod_clie = v_cod_clie(i)
               AND num_docu = v_num_docu(i)
               AND tip_refe = v_tip_refe(i)
               AND num_lote = v_num_lote(i)
               AND sec_nume_docu = v_sec_nume_docu(i);

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_cod_pais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;

      END IF;
      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              ' *** ERROR sto_pr_spvd_tipo_opera ***: ' || ls_sqlerrm);

  END sto_pr_spvd_tipo_opera;

  /***************************************************************************
  Descripcion       : FUNCION Devuelve el Codigo de Operacion SSICC a usar
  Fecha Creacion    : 20/06/2012
  Autor             : Sandro Quintana Aponte

             OUTPUT  si v_cod_oper es nullo hay error
  ***************************************************************************/
  FUNCTION sto_fn_spvd_tipo_opera
  (
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    psmotspv           VARCHAR2,
    psindexpr          VARCHAR2
  ) RETURN VARCHAR2 IS


    v_cod_oper            int_solic_conso_poven_detal.cod_oper%TYPE;
    v_ind_express         rec_digit_homol_oper.ind_express%type;
    v_val_mot_spv         rec_digit_homol_oper.val_mot_spv%type;
    v_cod_oper_mot        rec_digit_homol_oper.cod_oper_mot%type;

    pscodigopais     sto_param_gener_occrr.cod_pais%TYPE;

    lsparametrovalMOTFP   sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    ---- Recupera el motivo para cambiar a FALTANTE DE PREMIO
    select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;

    lsparametrovalMOTFP := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_MOT_SPV_FP');

    if lsparametrovalMOTFP is null or lsparametrovalMOTFP = ''  then
       lsparametrovalMOTFP := '07';
    end if;


    v_cod_oper := pscodoper;

      --- Datos de la operacion y tipo de opercion
          BEGIN
              select rto.ind_express, rto.val_mot_spv, rto.cod_oper_mot
              into v_ind_express, v_val_mot_spv, v_cod_oper_mot
              from rec_digit_homol_oper        rto
              where rto.cod_oper_sicc = v_cod_oper
              and rto.tipo_oper_sicc = pstipoper
              and NVL(rto.IND_LINE_IGUA, '*') != '0';
          EXCEPTION
            WHEN no_data_found THEN
              v_cod_oper     := null;
              v_ind_express  := null;
              v_val_mot_spv  := null;
              v_cod_oper_mot := null;
          END;

          -- Valida si evalua el Motivo 07 convierte en premio
          --- IF (v_val_mot_spv = '1' AND psmotspv = '07') THEN
          IF (v_val_mot_spv = '1' AND psmotspv = lsparametrovalMOTFP) THEN
            v_cod_oper := v_cod_oper_mot;
          END IF;

          -- Si es Express cambia la operacion  EJM  de CM a  MC
          IF (v_ind_express = '1' AND psindexpr = '1') THEN
            v_cod_oper := concat(substr(v_cod_oper,2,1),substr(v_cod_oper,1,1));
          END IF;

    RETURN (v_cod_oper);

  END sto_fn_spvd_tipo_opera;

  /***************************************************************************
  Descripcion       : VALIDACION DE DEVOLUCION GRATIS DE UNA OFERTA
  Fecha Creacion    : 06/04/2011
  Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_devol_grati
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote,
             det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             pos.prod_oid_prod,
             pos.soca_oid_soli_cabe,
             ofe.oid_ofer,
             (SELECT COUNT(1)
                FROM ped_solic_posic posl,
                     pre_ofert_detal pref,
                     pre_ofert       ofet,
                     pre_tipo_ofert  tofe
               WHERE posl.ofde_oid_deta_ofer = pref.oid_deta_ofer
                 AND pref.ofer_oid_ofer = ofe.oid_ofer
                 AND pref.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer(+)
                 AND nvl(posl.val_prec_cata_unit_loca,
                         0) = 0
                 AND posl.soca_oid_soli_cabe = pos.soca_oid_soli_cabe
                 AND ofet.oid_ofer = ofe.oid_ofer
                 AND TRIM(tofe.cod_tipo_ofer) NOT IN
                     (SELECT TRIM(val_param)
                        FROM sto_param_gener_occrr spo
                       WHERE spo.cod_para LIKE 'STO_EXCL_OFERTA%')) num_ofer,
             det.cod_oper,
             nvl(pos.val_prec_cata_unit_loca,
                 0) val_prec_cata_unit_loca
        FROM int_solic_conso_poven_detal det,
             ped_solic_posic             pos,
             pre_ofert_detal             pre,
             pre_ofert                   ofe,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.oid_soli_posi_devu = pos.oid_soli_posi(+)
         AND pos.ofde_oid_deta_ofer = pre.oid_deta_ofer(+)
         AND pre.ofer_oid_ofer = ofe.oid_ofer(+);

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_pais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_oid_prod IS TABLE OF mae_produ.oid_prod%TYPE;
    TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE;
    TYPE t_oid_ofer IS TABLE OF pre_ofert.oid_ofer%TYPE;
    TYPE t_num_ofer IS TABLE OF NUMBER;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_val_prec_cata_unit_loca IS TABLE OF ped_solic_posic.val_prec_cata_unit_loca%TYPE;

    v_sec_nume_docu           t_sec_nume_docu;
    v_num_lote                t_num_lote;
    v_cod_pais                t_cod_pais;
    v_cod_peri                t_cod_peri;
    v_cod_clie                t_cod_clie;
    v_oid_prod                t_oid_prod;
    v_oid_soli_cabe           t_oid_soli_cabe;
    v_oid_ofer                t_oid_ofer;
    v_num_ofer                t_num_ofer;
    v_cod_oper                t_cod_oper;
    v_val_prec_cata_unit_loca t_val_prec_cata_unit_loca;

    i                         BINARY_INTEGER := 0;

    isvalid BOOLEAN := TRUE;

    ln_contador NUMBER(12);

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote,
             v_cod_pais,
             v_cod_peri,
             v_cod_clie,
             v_oid_prod,
             v_oid_soli_cabe,
             v_oid_ofer,
             v_num_ofer,
             v_cod_oper,
             v_val_prec_cata_unit_loca
             LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          isvalid := TRUE;

          IF (v_cod_oper(i) IN ('DN',
                                'TM',
                                'MT') AND v_val_prec_cata_unit_loca(i) <> 0) THEN

            IF (v_num_ofer(i) > 0) THEN

              SELECT COUNT(1)
                INTO ln_contador
                FROM (SELECT pos.num_unid_aten,
                             nvl(SUM(rel.num_unid_recl),
                                 0) num_recl_pro,
                             (SELECT nvl(SUM(nvl(det.can_vent_devu,
                                                 0)),
                                         0)
                                FROM int_solic_conso_poven_detal det,
                                     sto_docum_digit             dig
                               WHERE det.cod_pais = v_cod_pais(i) ---- del query 01
                                 AND det.cod_clie = v_cod_clie(i) ---- del query 01
                                 AND det.cod_peri = v_cod_peri(i) ---- del query 01
                                 AND det.oid_soli_posi_devu = pos.oid_soli_posi
                                 AND dig.sec_nume_docu = det.sec_nume_docu
                                 AND dig.ind_envi = 0
                                 AND dig.ind_rech = 0) num_recl_pen
                        FROM ped_solic_posic       pos,
                             pre_ofert_detal       pre,
                             pre_ofert             ofe,
                             pre_tipo_ofert        tofe,
                             rec_linea_opera_recla rel
                       WHERE pos.ofde_oid_deta_ofer = pre.oid_deta_ofer
                         AND pre.ofer_oid_ofer = ofe.oid_ofer
                         AND pre.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer(+)
                         AND (nvl(pos.val_prec_cata_unit_loca,
                                  0) = 0 OR pos.prod_oid_prod = v_oid_prod(i)) ---- del query 01
                         AND pos.soca_oid_soli_cabe = v_oid_soli_cabe(i) ---- del query 01
                         AND ofe.oid_ofer = v_oid_ofer(i) ---- del query 01
                         AND pos.oid_soli_posi = rel.sopo_oid_soli_posi(+)
                         AND rel.timo_oid_tipo_movi(+) = 2
                         AND TRIM(tofe.cod_tipo_ofer) NOT IN
                             (SELECT TRIM(val_param)
                                FROM sto_param_gener_occrr
                               WHERE cod_para LIKE 'STO_EXCL_OFERTA%')
                       GROUP BY pos.oid_soli_posi,
                                pos.val_prec_cata_unit_loca,
                                pos.val_codi_vent,
                                pos.num_unid_aten) matriz
               WHERE matriz.num_recl_pro < matriz.num_unid_aten
                 AND matriz.num_recl_pen = 0;

              -- Si aun tiene algun producto de la oferta por devolver, de ser asi se rechaza el registro.
              IF (ln_contador > 0) THEN
                isvalid := FALSE;
              END IF;

            END IF;

          END IF;



          IF (isvalid) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_DEVOL_GRATI: ' || ls_sqlerrm);

  END sto_pr_spvd_devol_grati;

  /***************************************************************************
  Descripcion       : VALIDACION CUVS IGUALES EN PEDIDO
  Fecha Creacion    : 06/06/2011
  Autor             : Christian Luque
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_igual_pedid
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_clie,
             det.cod_vent_devu,
             det.oid_soli_posi_devu,
             cab.num_docu_cruc,
             nvl(pos.val_codi_vent,pos.val_codi_vent_fict) val_codi_vent,
             det.sec_nume_docu,
             (SELECT COUNT(*) cuenta
                FROM ped_solic_cabec a,
                     ped_solic_cabec b,
                     ped_solic_posic c
               WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                 AND b.oid_soli_cabe = c.soca_oid_soli_cabe
                 AND a.val_nume_soli = cab.num_docu_cruc
                 AND to_number(nvl(c.val_codi_vent,c.val_codi_vent_fict)) = to_number(det.cod_vent_devu)) cuenta,
             det.num_lote
        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit         dig,
             int_solic_conso_poven_cabec cab,
             ped_solic_posic             pos
       WHERE det.sec_nume_docu = dig.sec_nume_docu
         AND dig.sec_nume_docu_cabe = cab.sec_nume_docu
            --AND dig.cod_tipo_docu = pscodigotipodoc
         AND det.oid_soli_posi_devu = pos.oid_soli_posi(+);

    TYPE t_cod_clie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_oid_soli_posi_devu IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
    TYPE t_num_docu_cruc IS TABLE OF int_solic_conso_poven_cabec.num_docu_cruc%TYPE;
    TYPE t_val_codi_vent IS TABLE OF ped_solic_posic.val_codi_vent%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_cuenta IS TABLE OF NUMBER;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    v_cod_clie           t_cod_clie;
    v_cod_vent_devu      t_cod_vent_devu;
    v_oid_soli_posi_devu t_oid_soli_posi_devu;
    v_num_docu_cruc      t_num_docu_cruc;
    v_val_codi_vent      t_val_codi_vent;
    v_sec_nume_docu      t_sec_nume_docu;
    v_cuenta             t_cuenta;
    v_num_lote           t_num_lote;

    v_oid_soli_posi int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
    v_con_error     NUMBER(4);

    v_existe NUMBER(4);

    i BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_cod_clie,
             v_cod_vent_devu,
             v_oid_soli_posi_devu,
             v_num_docu_cruc,
             v_val_codi_vent,
             v_sec_nume_docu,
             v_cuenta,
             v_num_lote

             LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          v_oid_soli_posi := v_oid_soli_posi_devu(i);
          v_con_error     := 0;

          IF v_cuenta(i) > 1 THEN
            SELECT COUNT(*)
              INTO v_existe
              FROM ped_solic_cabec a,
                   ped_solic_cabec b,
                   ped_solic_posic c
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND b.oid_soli_cabe = c.soca_oid_soli_cabe
               AND a.val_nume_soli = v_num_docu_cruc(i)
               AND to_number(nvl(c.val_codi_vent,c.val_codi_vent_fict)) = to_number(v_val_codi_vent(i))
               AND c.oid_soli_posi = v_oid_soli_posi_devu(i);

            IF v_existe = 0 THEN
              v_oid_soli_posi := 0;
              v_con_error     := 1;
            END IF;

          END IF;

          IF v_con_error = 0 THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

            UPDATE int_solic_conso_poven_detal
               SET ind_nume_vece_pedi = v_cuenta(i),
                   oid_soli_posi_devu = v_oid_soli_posi_devu(i)
             WHERE cod_pais = pscodigopais
               AND cod_clie = v_cod_clie(i)
               AND num_lote = v_num_lote(i)
               AND sec_nume_docu = v_sec_nume_docu(i);
          ELSE
            UPDATE int_solic_conso_poven_detal
               SET ind_nume_vece_pedi = v_cuenta(i),
                   oid_soli_posi_devu = v_oid_soli_posi
             WHERE cod_pais = pscodigopais
               AND cod_clie = v_cod_clie(i)
               AND num_lote = v_num_lote(i)
               AND sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;
      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR STO_PR_SPVD_IGUAL_PEDID: ' || ls_sqlerrm);

  END sto_pr_spvd_igual_pedid;


  /***************************************************************************
    Descripcion       : Validacion para Operacion Trueque de Mercaderia
    Fecha Creacion    : 14/12/2011
    Autor             : Vidal Cupe
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_opera_trueq
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_operatrue IS
          SELECT
             det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.tip_refe,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_oper,
             det.prod_oid_prod_envi,
             det.prod_oid_prod_devu,
             det.cod_vent_devu,
             det.cod_vent_dese,
             nvl(mdev.gene_oid_gene,0) gene_oid_gene,
             nvl(mdev.sgen_oid_supe_gene,1) sgen_oid_supe_gene,
             nvl(menv.gene_oid_gene,0) gene_oid_gene,
             nvl(menv.sgen_oid_supe_gene,1) sgen_oid_supe_gene
        FROM int_solic_conso_poven_detal det,
             sto_tmp_docum_digit        occ,
             mae_produ                  mdev,
             mae_produ                  menv
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         and det.PROD_OID_PROD_DEVU = mdev.OID_PROD (+)
         and det.PROD_OID_PROD_ENVI = menv.OID_PROD (+);

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_tiprefe IS TABLE OF int_solic_conso_poven_detal.tip_refe%TYPE;
    TYPE t_secnumedocu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_codoper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_prod_oid_prod_envi IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_envi%TYPE;
    TYPE t_prod_oid_prod_devu IS TABLE OF int_solic_conso_poven_detal.prod_oid_prod_devu%TYPE;
    TYPE t_mdev_gene_oid_gene IS TABLE OF mae_produ.gene_oid_gene%TYPE;
    TYPE t_mdev_sgen_oid_supe_gene IS TABLE OF mae_produ.sgen_oid_supe_gene%TYPE;
    TYPE t_menv_gene_oid_gene IS TABLE OF mae_produ.gene_oid_gene%TYPE;
    TYPE t_menv_sgen_oid_supe_gene IS TABLE OF mae_produ.sgen_oid_supe_gene%TYPE;

    TYPE t_codventdevu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_codventdese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;


    v_codpais                 t_codpais;
    v_codperi                 t_codperi;
    v_codclie                 t_codclie;
    v_numdocu                 t_numdocu;
    v_tiprefe                 t_tiprefe;
    v_secnumedocu             t_secnumedocu;
    v_numlote                 t_numlote;
    v_codoper                 t_codoper;
    v_prod_oid_prod_envi      t_prod_oid_prod_envi;
    v_prod_oid_prod_devu      t_prod_oid_prod_devu;
    v_mdev_gene_oid_gene      t_mdev_gene_oid_gene;
    v_mdev_sgen_oid_supe_gene t_mdev_sgen_oid_supe_gene;
    v_menv_gene_oid_gene      t_menv_gene_oid_gene;
    v_menv_sgen_oid_supe_gene t_menv_sgen_oid_supe_gene;

    v_codventdevu             t_codventdevu;
    v_codventdese             t_codventdese;

    lsparametrobaloon   sto_param_gener_occrr.val_param%TYPE;
    lsmensaje           varchar2(500);

    rows NATURAL := 5000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;

    ln_excep    NUMBER := 0;

  BEGIN

    lsparametrobaloon := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_BALOON_SPV');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);



    OPEN c_operatrue;
    LOOP
      FETCH c_operatrue BULK COLLECT
        INTO  v_codpais,
              v_codperi,
              v_codclie,
              v_numdocu,
              v_tiprefe,
              v_secnumedocu,
              v_numlote,
              v_codoper,
              v_prod_oid_prod_envi,
              v_prod_oid_prod_devu,
              v_codventdevu,
              v_codventdese,
              v_mdev_gene_oid_gene,
              v_mdev_sgen_oid_supe_gene,
              v_menv_gene_oid_gene,
              v_menv_sgen_oid_supe_gene LIMIT rows;

      IF v_codpais.count > 0 THEN

        -- Actualizamos documentos Validados OK
        FOR i IN v_codpais.first .. v_codpais.last
        LOOP


          existe := FALSE;

          IF v_codoper(i) != 'TM' then  --- Si no es Trueque se aprueba todo
            existe := TRUE;
          ELSE
            --- Verifica si el trueque esta exceptuado de esta validacion
          select count(1) into ln_excep from Rec_Excep_Trueq a
          where a.cod_sap_envi=(select cod_sap from mae_produ where oid_prod=v_prod_oid_prod_envi(i))
          and a.cod_sap_devu=(select cod_sap from mae_produ where oid_prod=v_prod_oid_prod_devu(i));

            IF ln_excep > 0 then  --- Se aprueba si esta exceptuada
              existe := TRUE;
            ELSE

               IF v_mdev_gene_oid_gene(i) = v_menv_gene_oid_gene(i) AND
                  v_mdev_sgen_oid_supe_gene(i) = v_menv_sgen_oid_supe_gene(i) AND
                  (v_mdev_gene_oid_gene(i)+v_menv_gene_oid_gene(i)+v_mdev_sgen_oid_supe_gene(i)+v_menv_sgen_oid_supe_gene(i)<>2)
                 then  --- Se aprueba si es el mismo generico y supergenerico

                    existe := TRUE;

               end if;
            end if;
          end if;

          /*IF v_codoper(i) != 'TM' OR ln_excep>0 OR
          ( v_codoper(i) = 'TM' AND ( v_mdev_gene_oid_gene(i) = v_menv_gene_oid_gene(i) AND
                                      v_mdev_sgen_oid_supe_gene(i) = v_menv_sgen_oid_supe_gene(i) )
                                      )  THEN

            if (v_mdev_gene_oid_gene(i)+v_menv_gene_oid_gene(i)+v_mdev_sgen_oid_supe_gene(i)+v_menv_sgen_oid_supe_gene(i)<>2) then */


          IF (existe) then  --- Registro aprobado

          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi = psusuario,
                 occ.fec_modi = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.sec_nume_docu = v_secnumedocu(i)
             AND occ.num_lote = v_numlote(i);

             /*end if  ;*/

           ELSE

              if lsparametrobaloon = '1' then
                 lsmensaje := 'CUV DEVUELVE : '|| v_codventdevu(i) || ' ' || gen_pkg_gener.gen_fn_devuelve_descripcion(v_prod_oid_prod_devu(i),'MAE_PRODU','es') || '  .............  ' ||
                              'CUV DESEA : '|| v_codventdese(i) || ' ' || gen_pkg_gener.gen_fn_devuelve_descripcion(v_prod_oid_prod_envi(i),'MAE_PRODU','es');
                 sto_pkg_gener.sto_pr_add_mensa_error(v_secnumedocu(i),v_numlote(i),lsmensaje);
              end if;

           END IF;

         END LOOP;
      END IF;

      EXIT WHEN c_operatrue%NOTFOUND;
    END LOOP;
    CLOSE c_operatrue;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_opera_trueq: ' || ls_sqlerrm);

  END sto_pr_spvd_opera_trueq;

  /***************************************************************************
  Descripcion       : VALIDACION operacion truequemercaderia (SPVD-52)
  Fecha Creacion    : 11/03/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_opera_trueq
  (
    pscodigopais      in  VARCHAR2,
    psCodOperSICC     in  VARCHAR2,
    psTipoOperSICC    in  VARCHAR2,
    psOidSoliPosi     in  number,
    pscodigoVentaDes  in  VARCHAR2,
    psperiodVentaDes  in  number
  )  RETURN BOOLEAN  IS

    lsparametrovalSPVD44   sto_param_gener_occrr.val_param%TYPE;
    lsparametromotSPVD44   sto_param_gener_occrr.val_param%TYPE;

    lsoidtipoposi          ped_solic_posic.tpos_oid_tipo_posi%TYPE;


    lsmdevgeneoidgene      mae_produ.gene_oid_gene%TYPE;
    lsmdevsgenoidsupegene  mae_produ.sgen_oid_supe_gene%TYPE;
    lsmenvgeneoidgene      mae_produ.gene_oid_gene%TYPE;
    lsmenvsgenoidsupegene  mae_produ.sgen_oid_supe_gene%TYPE;
    lsoidproddevu          mae_produ.oid_prod%TYPE;
    lsoidprodenvi          mae_produ.oid_prod%TYPE;

    existe BOOLEAN := TRUE;

    i BINARY_INTEGER := 0;
    ln_excep BINARY_INTEGER := 0;

  BEGIN


    BEGIN

      select nvl(mp.gene_oid_gene,0), nvl(mp.sgen_oid_supe_gene,1), mp.oid_prod
        into lsmdevgeneoidgene,lsmdevsgenoidsupegene,lsoidproddevu
        from ped_solic_posic psp,
             ped_solic_Cabec psc,
             ped_solic_cabec psc1,
             mae_clien       mae,
             mae_produ       mp
       where PSP.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
         and PSC.SOCA_OID_SOLI_CABE = PSC1.OID_SOLI_CABE
         and psc1.clie_oid_clie = mae.oid_clie
         and PSP.OID_SOLI_POSI = psOidSoliPosi
         and psp.prod_oid_prod = mp.oid_prod
         and rownum = 1;

    EXCEPTION
      WHEN no_data_found THEN
        lsmdevgeneoidgene := 0;
        lsmdevsgenoidsupegene := 1;
        lsoidproddevu := 0;
    END;

    BEGIN

        select nvl(mp.gene_oid_gene,0), nvl(mp.sgen_oid_supe_gene,1) , mp.oid_prod
        into lsmenvgeneoidgene,lsmenvsgenoidsupegene,lsoidprodenvi
        from pre_ofert_detal pod, pre_matri_factu mf, pre_matri_factu_cabec mfc,
             mae_produ mp
        where POD.OID_DETA_OFER = MF.OFDE_OID_DETA_OFER
        and MF.MFCA_OID_CABE = MFC.OID_CABE
        and POD.PROD_OID_PROD = MP.OID_PROD
        and POD.VAL_CODI_VENT = pscodigoVentaDes
        and MFC.PERD_OID_PERI = psperiodVentaDes;

    EXCEPTION
      WHEN no_data_found THEN
        lsmenvgeneoidgene := 0;
        lsmenvsgenoidsupegene := 1;
        lsoidprodenvi := 0;
    END;


          existe := FALSE;

          IF psCodOperSICC != 'TM' then  --- Si no es Trueque se aprueba todo
            existe := TRUE;
          ELSE
            --- Verifica si el trueque esta exceptuado de esta validacion
          select count(1) into ln_excep from Rec_Excep_Trueq a
          where a.cod_sap_envi=(select cod_sap from mae_produ where oid_prod=lsoidprodenvi )
          and a.cod_sap_devu=(select cod_sap from mae_produ where oid_prod=lsoidproddevu );

            IF ln_excep > 0 then  --- Se aprueba si esta exceptuada
              existe := TRUE;
            ELSE

               IF lsmdevgeneoidgene = lsmenvgeneoidgene AND
                  lsmdevsgenoidsupegene = lsmenvsgenoidsupegene AND
                  (lsmdevgeneoidgene+lsmenvgeneoidgene+lsmdevsgenoidsupegene+lsmenvsgenoidsupegene<>2)
                 then  --- Se aprueba si es el mismo generico y supergenerico

                    existe := TRUE;

               end if;
            end if;
          end if;



  RETURN (existe);

  END sto_fn_spvd_opera_trueq;

  /***************************************************************************
  Descripcion       : VALIDACION NO ACEPTA DEVLUCIONES DE UN RECLAMO (SPVD-60)
  Fecha Creacion    : 27/05/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_nodev_recla
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.num_lote,
             det.cod_oper,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_devu,
             cab.modu_oid_modu,
             cab.ind_oc,
             det.oid_soli_posi_devu,
             pos.num_unid_aten,
             cab.tspa_oid_tipo_soli_pais,
             det.ind_list_blan_prod,
             det.can_vent_devu
        FROM int_solic_conso_poven_detal det,
             ped_solic_posic             pos,
             ped_solic_cabec             cab,
             sto_tmp_docum_digit         occ
      /*sto_docum_digit         occ*/
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.oid_soli_posi_devu = pos.oid_soli_posi(+)
         and pos.soca_oid_soli_cabe = cab.oid_soli_cabe (+);

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_codoper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_ind_list_blan_prod IS TABLE OF int_solic_conso_poven_detal.ind_list_blan_prod%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_modu_oid_modu IS TABLE OF ped_solic_cabec.modu_oid_modu%TYPE;
    TYPE t_tspa_oid_tipo_soli_pais IS TABLE OF ped_solic_cabec.tspa_oid_tipo_soli_pais%TYPE;
    TYPE t_ind_oc        IS TABLE OF ped_solic_cabec.ind_oc%TYPE;

    TYPE t_oid_soli_posi_devu IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
    TYPE t_num_unid_aten IS TABLE OF ped_solic_posic.num_unid_aten%TYPE;
    TYPE t_can_vent_devu IS TABLE OF int_solic_conso_poven_detal.can_vent_devu%TYPE;
    TYPE t_num_unid_recl IS TABLE OF rec_linea_opera_recla.num_unid_recl%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_numlote t_numlote;
    v_codoper t_codoper;

    v_oid_soli_posi_devu t_oid_soli_posi_devu;
    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_cod_vent_devu       t_cod_vent_devu;
    v_modu_oid_modu       t_modu_oid_modu;
    v_ind_oc              t_ind_oc;

    v_tspa_oid_tipo_soli_pais t_tspa_oid_tipo_soli_pais;
    v_num_unid_aten      t_num_unid_aten;
    v_can_vent_devu      t_can_vent_devu;
    v_num_unid_recl      t_num_unid_recl;
    v_ind_list_blan_prod t_ind_list_blan_prod;

    v_num_recl_actu NUMBER;
    v_num_recl_otro NUMBER;
    v_posicion_NMP  NUMBER;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_numlote,
             v_codoper,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_devu,
             v_modu_oid_modu,
             v_ind_oc,
             v_oid_soli_posi_devu,
             v_num_unid_aten,
             v_tspa_oid_tipo_soli_pais,
             v_ind_list_blan_prod,
             v_can_vent_devu
             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          --- Si no es devolucion se aprueba
          IF (v_codoper(i) <> 'DN' or
              (v_codoper(i) = 'DN'  and v_ind_list_blan_prod(i) = '1') ) THEN

            existe := TRUE;

          ELSE
            --- Verifica si en el texto dice NMP
            v_posicion_NMP := instr(gen_pkg_gener.GEN_FN_DEVUE_DESCR_TIPOS_SOLIC(v_tspa_oid_tipo_soli_pais(i)),'NMP');

            --- si no es NMP
            if (v_posicion_NMP = 0 or v_posicion_NMP is null) then
            --- Si lo que esta devolviendo pertenece a una tenciond e cDR o una Atencion de mercaderia RECHAZA
            IF ( v_modu_oid_modu(i) = 15 or
                (v_modu_oid_modu(i) = 1  and v_ind_oc(i) = 0 )
                ) THEN
              existe := FALSE;
            ELSE
              existe := TRUE;
            END IF;
            ELSE
              existe := TRUE;
            END IF;

          END IF;

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_nodev_recla: ' || ls_sqlerrm);

  END sto_pr_spvd_nodev_recla;

  /***************************************************************************
  Descripcion       : FUNCION NO ACEPTA DEVLUCIONES DE UN RECLAMO (SPVD-60)
  Fecha Creacion    : 22/07/2015
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_nodev_recla
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    pscodclie          VARCHAR2,
    psindoc            number,
    pstsol             number,
    psmodu             number
  )  RETURN BOOLEAN IS


    v_num_recl_actu NUMBER;
    v_num_recl_otro NUMBER;
    v_posicion_NMP  NUMBER;
    existe BOOLEAN := TRUE;

  BEGIN


        --- Si no es devolucion se aprueba
        IF ( pscodoper <> 'DN' ) THEN

          existe := TRUE;

        ELSE
          --- Verifica si en el texto dice NMP
          v_posicion_NMP := instr(gen_pkg_gener.GEN_FN_DEVUE_DESCR_TIPOS_SOLIC(pstsol),'NMP');

          --- si no es NMP
          if (v_posicion_NMP = 0 or v_posicion_NMP is null) then
            --- Si lo que esta devolviendo pertenece a una tenciond e cDR o una Atencion de mercaderia RECHAZA
            IF ( psmodu = 15 or
                (psmodu = 1  and psindoc = 0 )
                ) THEN
              existe := FALSE;
            ELSE
              existe := TRUE;
            END IF;
          ELSE
            existe := TRUE;
          END IF;

        END IF;

    RETURN (existe);

  END sto_fn_spvd_nodev_recla;

  /***************************************************************************
  Descripcion       : VALIDACION NO ACEPTA DEVLUCIONES DE UN PEDIDO RESCATADO POR MINIMO  (SPVD-61)
  Fecha Creacion    : 27/05/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_nodev_pedid_monmi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.cod_pais,
             det.cod_peri,
             det.cod_clie,
             det.num_docu,
             det.num_lote,
             det.cod_oper,
             det.docu_cod_tipo_docu,
             det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_devu,
             cab.modu_oid_modu,
             cab.ind_oc,
             det.oid_soli_posi_devu,
             pos.num_unid_aten,
             pos.tpos_oid_tipo_posi,
             pos.stpo_oid_subt_posi,
             det.can_vent_devu,
             det.ind_list_blan_prod,
             (select count(*) from sto_param_gener_occrr x
              where x.cod_para like 'STO_MOT_APRO%'
              and x.val_param = det.mot_spv) motapro
        FROM int_solic_conso_poven_detal det,
             ped_solic_posic             pos,
             ped_solic_cabec             cab,
             sto_tmp_docum_digit         occ
      /*sto_docum_digit         occ*/
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.oid_soli_posi_devu = pos.oid_soli_posi(+)
         and pos.soca_oid_soli_cabe = cab.oid_soli_cabe (+);

    TYPE t_codpais IS TABLE OF int_solic_conso_poven_detal.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_poven_detal.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_poven_detal.cod_clie%TYPE;
    TYPE t_numdocu IS TABLE OF int_solic_conso_poven_detal.num_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_codoper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_motapro IS TABLE OF int_solic_conso_poven_detal.IND_NUME_VECE_PEDI%TYPE;

    TYPE t_docu_cod_tipo_docu IS TABLE OF int_solic_conso_poven_detal.docu_cod_tipo_docu%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;

    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_modu_oid_modu IS TABLE OF ped_solic_cabec.modu_oid_modu%TYPE;
    TYPE t_ind_oc        IS TABLE OF ped_solic_cabec.ind_oc%TYPE;

    TYPE t_oid_soli_posi_devu IS TABLE OF int_solic_conso_poven_detal.oid_soli_posi_devu%TYPE;
    TYPE t_num_unid_aten      IS TABLE OF ped_solic_posic.num_unid_aten%TYPE;
    TYPE t_tpos_oid_tipo_posi IS TABLE OF ped_solic_posic.tpos_oid_tipo_posi%TYPE;
    TYPE t_stpo_oid_subt_posi IS TABLE OF ped_solic_posic.stpo_oid_subt_posi%TYPE;
    TYPE t_can_vent_devu      IS TABLE OF int_solic_conso_poven_detal.can_vent_devu%TYPE;
    TYPE t_num_unid_recl      IS TABLE OF rec_linea_opera_recla.num_unid_recl%TYPE;
    TYPE t_ind_list_blan_prod IS TABLE OF int_solic_conso_poven_detal.ind_list_blan_prod%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numdocu t_numdocu;
    v_numlote t_numlote;
    v_codoper t_codoper;
    v_motapro t_motapro;

    v_docu_cod_tipo_docu t_docu_cod_tipo_docu;
    v_sec_nume_docu      t_sec_nume_docu;
    v_num_lote           t_num_lote;

    v_cod_vent_devu       t_cod_vent_devu;
    v_modu_oid_modu       t_modu_oid_modu;
    v_ind_oc              t_ind_oc;

    v_oid_soli_posi_devu t_oid_soli_posi_devu;
    v_num_unid_aten      t_num_unid_aten;
    v_tpos_oid_tipo_posi t_tpos_oid_tipo_posi;
    v_stpo_oid_subt_posi t_stpo_oid_subt_posi;
    v_can_vent_devu      t_can_vent_devu;
    v_num_unid_recl      t_num_unid_recl;
    v_ind_list_blan_prod t_ind_list_blan_prod;

    v_num_recl_actu NUMBER;
    v_num_recl_otro NUMBER;
    lsmensaje           varchar2(500);

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numdocu,
             v_numlote,
             v_codoper,
             v_docu_cod_tipo_docu,
             v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_devu,
             v_modu_oid_modu,
             v_ind_oc,
             v_oid_soli_posi_devu,
             v_num_unid_aten,
             v_tpos_oid_tipo_posi,
             v_stpo_oid_subt_posi,
             v_can_vent_devu,
             v_ind_list_blan_prod,
             v_motapro
             LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          --- Si no es devolucion se aprueba
          IF (v_codoper(i) <> 'DN' or
              (v_codoper(i) = 'DN' and v_ind_list_blan_prod(i) = '1') ) THEN

            existe := TRUE;

          ELSE
            --- Si Tipo de posicion es monto minimo y subtipo de posicion es monto minimo - RECHAZA
            IF ( v_tpos_oid_tipo_posi(i) = 2029  and v_stpo_oid_subt_posi(i) = 2027 and
                 v_motapro(i) = 0) THEN
              existe := FALSE;
            ELSE
              existe := TRUE;
            END IF;

          END IF;

          IF (existe) THEN
            UPDATE sto_docum_digit occ
               SET -- Actualziamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_codpais(i)
               AND occ.cod_tipo_docu = v_docu_cod_tipo_docu(i)
               AND occ.num_lote = v_num_lote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);
          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_nodev_pedid_monmi: ' || ls_sqlerrm);

  END sto_pr_spvd_nodev_pedid_monmi;

  /***************************************************************************
  Descripcion       : FUNCION NO ACEPTA DEVLUCIONES DE UN PEDIDO RESCATADO POR MINIMO  (SPVD-61)
  Fecha Creacion    : 22/07/2015
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_nodev_pedid_monmi
  (
    pscodigopais          VARCHAR2,
    pscodoper             VARCHAR2,
    pstipoper             VARCHAR2,
    pstposposi            NUMBER,
    psstpoposi            NUMBER,
    psmotspv              VARCHAR2
  )  RETURN BOOLEAN IS


    v_num_recl_actu NUMBER;
    v_num_recl_otro NUMBER;
    v_motapro       NUMBER;
    lsmensaje           varchar2(500);
    existe BOOLEAN := TRUE;

  BEGIN

      select count(*) into v_motapro from sto_param_gener_occrr x
      where x.cod_para like 'STO_MOT_APRO%'
      and x.val_param = psmotspv;

      --- Si no es devolucion se aprueba
      IF (pscodoper <> 'DN' ) THEN

        existe := TRUE;

      ELSE
        --- Si Tipo de posicion es monto minimo y subtipo de posicion es monto minimo - RECHAZA
        IF ( pstposposi = 2029  and psstpoposi = 2027 and
             v_motapro = 0) THEN
          existe := FALSE;
        ELSE
          existe := TRUE;
        END IF;

      END IF;

    RETURN (existe);

  END sto_fn_spvd_nodev_pedid_monmi;

  /***************************************************************************
  Descripcion       : VALIDACION NO ACEPTA DEVOLUCIONES DE UN CATALOGO  (SPVD-62)
  Fecha Creacion    : 27/05/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_nodev_catal
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_dese,
             det.cod_vent_devu,
             det.cod_oper,
             det.cod_tipo_oper,
             cab.oid_peri_refe,
             det.ind_list_blan_prod,
             (select count(*) from sto_param_gener_occrr x
              where x.cod_para like 'STO_MOT_APRO%'
              and x.val_param = det.mot_spv) motapro
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.cod_pais = det.cod_pais;
    --         and 16123826 = occ.sec_nume_docu;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_cod_tipo_oper IS TABLE OF int_solic_conso_poven_detal.cod_tipo_oper%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_ind_list_blan_prod IS TABLE OF int_solic_conso_poven_detal.ind_list_blan_prod%TYPE;
    TYPE t_motapro IS TABLE OF int_solic_conso_poven_detal.IND_NUME_VECE_PEDI%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;
    v_cod_vent_dese t_cod_vent_dese;
    v_cod_vent_devu t_cod_vent_devu;
    v_cod_oper      t_cod_oper;
    v_cod_tipo_oper t_cod_tipo_oper;
    v_oid_peri_refe t_oid_peri_refe;
    v_ind_list_blan_prod t_ind_list_blan_prod;
    v_motapro t_motapro;

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;
    lsmensaje           varchar2(500);

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_dese,
             v_cod_vent_devu,
             v_cod_oper,
             v_cod_tipo_oper,
             v_oid_peri_refe,
             v_ind_list_blan_prod,
             v_motapro  LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          existe := TRUE;

          ---- Si no es DN no se procesa
          if  v_cod_oper(j) <> 'DN' or
              (v_cod_oper(j) = 'DN' and v_ind_list_blan_prod(j) = '1' ) then
              existe := TRUE;
          else
              BEGIN

                numero := 0;
                SELECT  count(1)
                  INTO numero
                  FROM
                       pre_ofert_detal             b,
                       pre_matri_factu_cabec       c,
                       pre_ofert                   f,
                       pre_tipo_ofert              g,
                       pre_catal                   h,
                       (select val_param cod_cata
                          from sto_param_gener_occrr
                          where cod_para like '%CAT%62%') cata
                 WHERE b.ofer_oid_ofer = f.oid_ofer
                   AND f.mfca_oid_cabe = c.oid_cabe
                   AND c.perd_oid_peri = v_oid_peri_refe(j)
                   AND b.tofe_oid_tipo_ofer = g.oid_tipo_ofer
                   AND f.ocat_oid_cata = h.oid_cata
                   and H.COD_CATA = cata.cod_cata
                   AND to_number(b.val_codi_vent) = to_number(v_cod_vent_devu(j));

                IF (numero > 0 and v_motapro(j) = 0  ) THEN
                  existe := FALSE;
                ELSE
                  existe := TRUE;
                END IF;

              EXCEPTION
                WHEN no_data_found THEN
                  existe := TRUE;
              END;

          END IF;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_nodev_catal: ' || ls_sqlerrm);


  END sto_pr_spvd_nodev_catal;

  /***************************************************************************
  Descripcion       : FUNCION NO ACEPTA DEVOLUCIONES DE UN CATALOGO  (SPVD-62)
  Fecha Creacion    : 22/07/2015
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_nodev_catal
  (
     pscodigopais   in VARCHAR2,
     psOidPeriCDR   in number,
     pscodigoVenta  in VARCHAR2,
     psCodOperSICC  in VARCHAR2,
     psTipoOperSICC in VARCHAR2,
     pscodigomotivo in  VARCHAR2
  )  RETURN BOOLEAN IS

    j      BINARY_INTEGER := 0;
    v_motapro       NUMBER;

    existe BOOLEAN := TRUE;
    numero NUMBER := 0;
    lsmensaje           varchar2(500);

  BEGIN

      select count(*) into v_motapro from sto_param_gener_occrr x
      where x.cod_para like 'STO_MOT_APRO%'
      and x.val_param = pscodigomotivo;

          existe := TRUE;

          ---- Si no es DN no se procesa
          if  psCodOperSICC <> 'DN'   then
              existe := TRUE;
          else
              BEGIN

                numero := 0;
                SELECT  count(1)
                  INTO numero
                  FROM
                       pre_ofert_detal             b,
                       pre_matri_factu_cabec       c,
                       pre_ofert                   f,
                       pre_tipo_ofert              g,
                       pre_catal                   h,
                       (select val_param cod_cata
                          from sto_param_gener_occrr
                          where cod_para like '%CAT%62%') cata
                 WHERE b.ofer_oid_ofer = f.oid_ofer
                   AND f.mfca_oid_cabe = c.oid_cabe
                   AND c.perd_oid_peri = psOidPeriCDR
                   AND b.tofe_oid_tipo_ofer = g.oid_tipo_ofer
                   AND f.ocat_oid_cata = h.oid_cata
                   and H.COD_CATA = cata.cod_cata
                   AND to_number(b.val_codi_vent) = to_number(pscodigoVenta);

                IF (numero > 0 and v_motapro = 0  ) THEN
                  existe := FALSE;
                ELSE
                  existe := TRUE;
                END IF;

              EXCEPTION
                WHEN no_data_found THEN
                  existe := TRUE;
              END;

          END IF;


    RETURN (existe);

  END sto_fn_spvd_nodev_catal;

  /***************************************************************************
  Descripcion       : VALIDACION NO ACEPTA DEVOLUCIONES DE UNA REMESA  (SPVD-63)
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_nodev_remes
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_dese,
             det.cod_vent_devu,
             det.cod_oper,
             det.cod_tipo_oper,
             cab.oid_peri_refe,
             cab.oid_cabe,
             det.ind_list_blan_prod,
             (select count(*) from sto_param_gener_occrr x
              where x.cod_para like 'STO_MOT_APRO%'
              and x.val_param = det.mot_spv) motapro
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.cod_pais = det.cod_pais;
    --         and 16123826 = occ.sec_nume_docu;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_cod_tipo_oper IS TABLE OF int_solic_conso_poven_detal.cod_tipo_oper%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_ind_list_blan_prod IS TABLE OF int_solic_conso_poven_detal.ind_list_blan_prod%TYPE;
    TYPE t_motapro IS TABLE OF int_solic_conso_poven_detal.IND_NUME_VECE_PEDI%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;
    v_cod_vent_dese t_cod_vent_dese;
    v_cod_vent_devu t_cod_vent_devu;
    v_cod_oper      t_cod_oper;
    v_cod_tipo_oper t_cod_tipo_oper;
    v_oid_peri_refe t_oid_peri_refe;
    v_oid_cabe      t_oid_cabe;
    v_ind_list_blan_prod t_ind_list_blan_prod;
    lsparametroremesa   sto_param_gener_occrr.val_param%TYPE;
    v_motapro t_motapro;
    lsparametrobaloon   sto_param_gener_occrr.val_param%TYPE;


    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;
    lsmensaje           varchar2(500);

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsparametroremesa := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_REMESA');
    lsparametrobaloon := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_BALOON_SPV');
    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_dese,
             v_cod_vent_devu,
             v_cod_oper,
             v_cod_tipo_oper,
             v_oid_peri_refe,
             v_oid_cabe,
             v_ind_list_blan_prod,
             v_motapro  LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          existe := TRUE;

          ---- Si no es DN no se procesa
          if ( v_cod_oper(j) <> 'DN' or lsparametroremesa is null or
               (v_cod_oper(j) = 'DN' and v_ind_list_blan_prod(j) = '1' ) ) then
              existe := TRUE;
          else
              BEGIN

                numero := 0;
                SELECT  a.ind_comp
                  INTO numero
                  FROM ped_segui_pedid             a
                 WHERE a.soca_oid_soli_cabe = v_oid_cabe(j);

                IF ( numero >= lsparametroremesa and v_motapro(j) = 0 ) THEN
                  existe := FALSE;

                  if lsparametrobaloon = '1' then
                   lsmensaje := 'Excede el numero permitido de devolucion de remesa =  '|| lsparametroremesa ||
                                ' (Remesa del pedido : '|| TO_CHAR(numero,'999') || ')' ;
                   sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(j),v_num_lote(j),lsmensaje);
                  end if;

                ELSE
                  existe := TRUE;
                END IF;

              EXCEPTION
                WHEN no_data_found THEN
                  existe := TRUE;
              END;

          END IF;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_nodev_remes: ' || ls_sqlerrm);


  END sto_pr_spvd_nodev_remes;

  /***************************************************************************
  Descripcion       : FUNCION NO ACEPTA DEVOLUCIONES DE UNA REMESA  (SPVD-63)
  Fecha Creacion    : 23/07/2015
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_nodev_remes
  (
     pscodigopais   in VARCHAR2,
     psOidSoliCabe  in number,
     psCodOperSICC  in VARCHAR2,
     psTipoOperSICC in VARCHAR2,
     pscodigomotivo in  VARCHAR2
  )  RETURN BOOLEAN IS


    v_motapro       NUMBER;
    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;
    lsparametroremesa   sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    lsparametroremesa := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_REMESA');

      select count(*) into v_motapro from sto_param_gener_occrr x
      where x.cod_para like 'STO_MOT_APRO%'
      and x.val_param = pscodigomotivo;

      existe := TRUE;

      ---- Si no es DN no se procesa
      if ( psCodOperSICC <> 'DN' or lsparametroremesa is null ) then
          existe := TRUE;
      else
          BEGIN

            numero := 0;
            SELECT  a.ind_comp
              INTO numero
              FROM ped_segui_pedid             a
             WHERE a.soca_oid_soli_cabe = psOidSoliCabe;

            IF ( numero >= lsparametroremesa and v_motapro = 0 ) THEN
              existe := FALSE;

            ELSE
              existe := TRUE;
            END IF;

          EXCEPTION
            WHEN no_data_found THEN
              existe := TRUE;
          END;

      END IF;

    RETURN (existe);

  END sto_fn_spvd_nodev_remes;

  /***************************************************************************
  Descripcion       : VALIDACION NO ACEPTA DEVOLUCIONES DE X DIAS  (SPVD-67)
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_nodev_xdias
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_dese,
             det.cod_vent_devu,
             det.cod_oper,
             det.cod_tipo_oper,
             cab.oid_peri_refe,
             cab.oid_cabe,
             cab.num_docu_cruc,
             det.ind_list_blan_prod
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         and occ.sec_nume_docu_cabe = cab.sec_nume_docu;
         /*AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.cod_pais = det.cod_pais; */
    --         and 16123826 = occ.sec_nume_docu;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_cod_tipo_oper IS TABLE OF int_solic_conso_poven_detal.cod_tipo_oper%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_ind_list_blan_prod IS TABLE OF int_solic_conso_poven_detal.ind_list_blan_prod%TYPE;
    TYPE t_num_docu_cruc IS TABLE OF int_solic_conso_poven_cabec.num_docu_cruc%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;
    v_cod_vent_dese t_cod_vent_dese;
    v_cod_vent_devu t_cod_vent_devu;
    v_cod_oper      t_cod_oper;
    v_cod_tipo_oper t_cod_tipo_oper;
    v_oid_peri_refe t_oid_peri_refe;
    v_oid_cabe      t_oid_cabe;
    v_ind_list_blan_prod t_ind_list_blan_prod;
    v_num_docu_cruc t_num_docu_cruc;

    lsparametrodiasdev   sto_param_gener_occrr.val_param%TYPE;
    lsfechaent           ped_segui_pedid.fec%TYPE;
    lsfechaped           ped_segui_pedid.fec%TYPE;
    cuenta       NUMBER;

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsparametrodiasdev := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_DIASDEV');

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_dese,
             v_cod_vent_devu,
             v_cod_oper,
             v_cod_tipo_oper,
             v_oid_peri_refe,
             v_oid_cabe,
             v_num_docu_cruc,
             v_ind_list_blan_prod LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          cuenta := 0;

          ---- Si no es DN no se procesa
          if ( v_cod_oper(j) <> 'DN' ) then
              cuenta := 1;
          else
              --- Busca la fecha del pedido de la consultara
              BEGIN

                SELECT  b.fec_fact
                  INTO lsfechaped
                  FROM Ped_Solic_Cabec b
                 WHERE b.oid_soli_cabe = v_oid_cabe(j);

              EXCEPTION
                WHEN no_data_found THEN
                   lsfechaped := to_date('01/01/1900','DD/MM/YYYY');
                   cuenta := 1;
              END;

              --- Si encontro la fecha de pedido
              if ( cuenta = 0 ) then

                --- Busca la fecha de Entrega del pedido
                BEGIN

                  SELECT  b.fec_proc
                  INTO lsfechaent
                    FROM int_solic_conso_orden_trans b
                   WHERE b.num_docu = v_num_docu_cruc(j)
                     AND b.cod_reci_conf = 'S'
                     and rownum = 1;

                EXCEPTION
                  WHEN no_data_found THEN
                     ----cuenta := 1;
                     lsfechaent := to_date('01/01/1900','DD/MM/YYYY');
                END;

               END IF;

               if ( cuenta = 0 ) then
                  --- obtiene la verdadera fecha de pedido
                  if (trunc(lsfechaent) > trunc(sysdate)) then
                      lsfechaent := trunc(sysdate);
                  end if;

                  if (trunc(lsfechaent) < trunc(lsfechaped)) then
                      lsfechaent := trunc(lsfechaped);
                  end if;

                IF ( (trunc(sysdate) - trunc(lsfechaent)) >= lsparametrodiasdev ) THEN
                     cuenta := 0;
                ELSE
                     cuenta := 1;
                  END IF;
                END IF;

          END IF;

           if cuenta = 0 then
              existe := false;
           else
              existe := true;
           end if;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_nodev_xdias: ' || ls_sqlerrm);


  END sto_pr_spvd_nodev_xdias;

  /***************************************************************************
  Descripcion       : Verifica la devoluion de x dias atras (SPVD-67)
  Fecha Creacion    : 23/07/2013
  Autor             : Sandro Quintana Aponte
             OUTPUT  si es TRUE esta ok, sino es error
  ***************************************************************************/
  FUNCTION sto_fn_spvd_nodev_xdias
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    psnumpedi          VARCHAR2
  ) RETURN BOOLEAN IS


    v_cod_oper            int_solic_conso_poven_detal.cod_oper%TYPE;
    v_num_dias_atra       int_solic_conso_poven_detal.num_dias_atra%TYPE;
    v_oid_peri_recl       int_solic_conso_poven_cabec.oid_peri_recl%TYPE;
    v_oid_peri_refe       int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    v_fec_refe            int_solic_conso_poven_cabec.fec_refe%TYPE;

    ls_anti_peri NUMBER;
    ls_dif_dias  NUMBER;
    v_fec_inic   DATE;
    cuenta       NUMBER;
    lsparametrodiasdev   sto_param_gener_occrr.val_param%TYPE;
    lsfechaent           ped_segui_pedid.fec%TYPE;
    lsfechaped           ped_segui_pedid.fec%TYPE;
    lsfechahoy           ped_segui_pedid.fec%TYPE;

    existe BOOLEAN := TRUE;

  BEGIN


    lsparametrodiasdev := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_DIASDEV');
    v_cod_oper := pscodoper;

          cuenta := 0;

          ---- Si no es DN no se procesa
          if ( v_cod_oper <> 'DN' ) then
              cuenta := 1;
          else
              --- Busca la fecha del pedido de la consultara
              BEGIN

                SELECT  b.fec_fact
                  INTO lsfechaped
                  FROM Ped_Solic_Cabec b
                 WHERE b.val_nume_soli = psnumpedi;

              EXCEPTION
                WHEN no_data_found THEN
                   lsfechaped := to_date('01/01/1900','DD/MM/YYYY');
                   cuenta := 1;
              END;

              --- Si encontro la fecha de pedido
              if ( cuenta = 0 ) then

                --- Busca la fecha de Entrega del pedido
                BEGIN

                  SELECT  b.fec_proc
                    INTO lsfechaent
                    FROM int_solic_conso_orden_trans b
                   WHERE b.num_docu = psnumpedi
                     AND b.cod_reci_conf = 'S'
                     and rownum = 1;

              EXCEPTION
                WHEN no_data_found THEN
                     lsfechaent := to_date('01/01/1900','DD/MM/YYYY');
                     ----cuenta := 1;
              END;

          END IF;

               if ( cuenta = 0 ) then
                  --- obtiene la verdadera fecha de pedido
                  if (trunc(lsfechaent) > trunc(sysdate)) then
                      lsfechaent := trunc(sysdate);
                  end if;

                  if (trunc(lsfechaent) < trunc(lsfechaped)) then
                      lsfechaent := trunc(lsfechaped);
                  end if;

                  IF ( (trunc(sysdate) - trunc(lsfechaent)) >= lsparametrodiasdev ) THEN
                     cuenta := 0;
                  ELSE
                     cuenta := 1;
                  END IF;
               END IF;

          END IF;

       if cuenta = 0 then
          existe := false;
       else
          existe := true;
       end if;

    RETURN (existe);

  END sto_fn_spvd_nodev_xdias;

  /***************************************************************************
  Descripcion       : VALIDACION NO ACEPTA DEVOLUCION DE por Origen del CUV del pedido (SPVD-70)
  Fecha Creacion    : 13/06/2013
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_orige_produ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cursor IS
      SELECT det.sec_nume_docu,
             det.num_lote,
             det.cod_vent_dese,
             det.cod_vent_devu,
             det.cod_oper,
             det.cod_tipo_oper,
             cab.oid_peri_refe,
             cab.oid_cabe,
             pos.soca_oid_soli_cabe,
             cab.num_docu_cruc,
             det.ind_list_blan_prod,
             (select count(*) from sto_param_gener_occrr x
              where x.cod_para like 'STO_MOT_RECH%'
              and x.val_param = det.mot_spv) motrecha
        FROM int_solic_conso_poven_detal det,
             int_solic_conso_poven_cabec cab,
             ped_solic_posic             pos,
             sto_tmp_docum_digit         occ
       WHERE occ.sec_nume_docu = det.sec_nume_docu
         AND occ.num_lote = det.num_lote
         AND pos.oid_soli_posi(+) = det.oid_soli_posi_devu
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         and occ.sec_nume_docu_cabe = cab.sec_nume_docu;
         /*AND det.cod_peri = cab.cod_peri
         AND det.cod_clie = cab.cod_clie
         AND det.num_docu = cab.num_docu
         AND cab.cod_pais = det.cod_pais; */
    --         and 16123826 = occ.sec_nume_docu;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_poven_detal.sec_nume_docu%TYPE;
    TYPE t_num_lote IS TABLE OF int_solic_conso_poven_detal.num_lote%TYPE;
    TYPE t_cod_vent_dese IS TABLE OF int_solic_conso_poven_detal.cod_vent_dese%TYPE;
    TYPE t_cod_vent_devu IS TABLE OF int_solic_conso_poven_detal.cod_vent_devu%TYPE;
    TYPE t_cod_oper IS TABLE OF int_solic_conso_poven_detal.cod_oper%TYPE;
    TYPE t_cod_tipo_oper IS TABLE OF int_solic_conso_poven_detal.cod_tipo_oper%TYPE;
    TYPE t_oid_peri_refe IS TABLE OF int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    TYPE t_oid_cabe IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_oid_cabe_soli IS TABLE OF int_solic_conso_poven_cabec.oid_cabe%TYPE;
    TYPE t_ind_list_blan_prod IS TABLE OF int_solic_conso_poven_detal.ind_list_blan_prod%TYPE;
    TYPE t_num_docu_cruc IS TABLE OF int_solic_conso_poven_cabec.num_docu_cruc%TYPE;
    TYPE t_motrecha IS TABLE OF int_solic_conso_poven_detal.IND_NUME_VECE_PEDI%TYPE;

    v_sec_nume_docu t_sec_nume_docu;
    v_num_lote      t_num_lote;
    v_cod_vent_dese t_cod_vent_dese;
    v_cod_vent_devu t_cod_vent_devu;
    v_cod_oper      t_cod_oper;
    v_cod_tipo_oper t_cod_tipo_oper;
    v_oid_peri_refe t_oid_peri_refe;
    v_oid_cabe      t_oid_cabe;
    v_oid_cabe_soli t_oid_cabe_soli;
    v_ind_list_blan_prod t_ind_list_blan_prod;
    v_num_docu_cruc t_num_docu_cruc;
    v_motrecha t_motrecha;

    lsparametrodiasdev   sto_param_gener_occrr.val_param%TYPE;
    lsfechaent           ped_segui_pedid.fec%TYPE;
    lsfechaped           ped_segui_pedid.fec%TYPE;
    cuenta       NUMBER;
    nroreg       NUMBER;

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;
    lsmensaje           varchar2(100);
    lsorigen            varchar2(500);
    lsparametrobaloon   sto_param_gener_occrr.val_param%TYPE;

  BEGIN
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lsparametrodiasdev := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_DIASDEV');
    lsparametrobaloon := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                              'STO_IND_BALOON_SPV');

    OPEN c_cursor;
    LOOP
      FETCH c_cursor BULK COLLECT
        INTO v_sec_nume_docu,
             v_num_lote,
             v_cod_vent_dese,
             v_cod_vent_devu,
             v_cod_oper,
             v_cod_tipo_oper,
             v_oid_peri_refe,
             v_oid_cabe,
             v_oid_cabe_soli,
             v_num_docu_cruc,
             v_ind_list_blan_prod,
             v_motrecha LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          cuenta := 0;

          ---- Si no es DN no se procesa
          if ( v_cod_oper(j) <> 'DN' or
               (v_cod_oper(j) = 'DN' and v_ind_list_blan_prod(j) = '1' ) ) then
              cuenta := 1;
          else

             if ( v_motrecha(j) = 0 ) then
                cuenta := 1;
             else
                lsmensaje := '';
                lsorigen := gen_pkg_gener.gen_fn_devue_orige_pedid_posic(v_oid_cabe_soli(j),v_cod_vent_devu(j));

                select count(*) into nroreg from sto_param_gener_occrr x
                where x.cod_para like 'STO_ORI_RECH%'
                and x.val_param = lsorigen;

                ---if lsorigen is not null and lsparametrobaloon = '1'  then
                if nroreg <> 0 then
                cuenta := 0;
                    if lsparametrobaloon = '1'  then
                   lsmensaje := 'El Cuv '|| v_cod_vent_devu(j) || ' es de origen '||lsorigen ;
                   sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(j),v_num_lote(j),lsmensaje);
                    end if;
                else
                   cuenta := 1;
                END IF;
             END IF;

          END IF;

           if cuenta = 0 then
              existe := false;
           else
              existe := true;
           end if;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_num_lote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);
          END IF;
        END LOOP;

      END IF;

      EXIT WHEN c_cursor%NOTFOUND;

    END LOOP;
    CLOSE c_cursor;

    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_orige_produ: ' || ls_sqlerrm);


  END sto_pr_spvd_orige_produ;


  /***************************************************************************
  Descripcion       : FUNCION NO ACEPTA DEVOLUCION DE por Origen del CUV del pedido (SPVD-70)
  Fecha Creacion    : 23/07/2015
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION sto_fn_spvd_orige_produ
  (
     pscodigopais   in VARCHAR2,
     psOidSoliCabe  in number,
     pscodigoVenta  in VARCHAR2,
     psCodOperSICC  in VARCHAR2,
     psTipoOperSICC in VARCHAR2,
     pscodigomotivo in  VARCHAR2
  ) RETURN BOOLEAN IS


    lsparametrodiasdev   sto_param_gener_occrr.val_param%TYPE;
    cuenta       NUMBER;
    nroreg       NUMBER;

    v_motrech       NUMBER;

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;
    numero NUMBER := 0;
    lsorigen            varchar2(500);

  BEGIN

      select count(*) into v_motrech from sto_param_gener_occrr x
      where x.cod_para like 'STO_MOT_RECH%'
      and x.val_param = pscodigomotivo;

      cuenta := 0;

      ---- Si no es DN no se procesa
      if ( psCodOperSICC <> 'DN'  ) then
          cuenta := 1;
      else

         if ( v_motrech = 0 ) then
            cuenta := 1;
         else
            lsorigen := gen_pkg_gener.gen_fn_devue_orige_pedid_posic(psOidSoliCabe,pscodigoVenta);

            select count(*) into nroreg from sto_param_gener_occrr x
            where x.cod_para like 'STO_ORI_RECH%'
            and x.val_param = lsorigen;

            ---if lsorigen is not null and lsparametrobaloon = '1'  then
            if nroreg <> 0 then
               cuenta := 0;
            else
               cuenta := 1;
            END IF;
         END IF;

      END IF;

       if cuenta = 0 then
          existe := false;
       else
          existe := true;
       end if;


    RETURN (existe);

  END sto_fn_spvd_orige_produ;


 /***************************************************************************
  Descripcion       : FUNCION Verifica la cantidad de dias antes de la facturacion
                      que acepta el CDR
  Fecha Creacion    : 17/09/2014
  Autor             : Sandro Quintana Aponte

             OUTPUT  si es nullo esta ok, sino es error
  ***************************************************************************/
  FUNCTION sto_fn_spvd_dias_fact
  (
    pscodigopais       VARCHAR2,
    pscodoper          VARCHAR2,
    pstipoper          VARCHAR2,
    pscodclie          VARCHAR2
  ) RETURN VARCHAR2 IS


    v_cod_oper            int_solic_conso_poven_detal.cod_oper%TYPE;
    v_num_dias_ante       rec_tipos_opera.NUM_DIAS_ante_Fact%TYPE;
    v_oid_peri            int_solic_conso_poven_cabec.oid_peri_recl%TYPE;
    v_oid_peri_refe       int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    v_oid_peri_actu       int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    v_fec_refe            int_solic_conso_poven_cabec.fec_refe%TYPE;
    v_fec_calc            int_solic_conso_poven_cabec.fec_refe%TYPE;
    v_oid_pais            cra_perio.pais_oid_pais%TYPE;
    v_oid_terr            zon_terri.oid_terr%TYPE;
    v_oid_cabe_grup_zona  cra_cabec_grupo_zona.oid_cabe_grup_zona%TYPE;
    v_oid_acti            cra_activ.oid_acti%TYPE;
    cuenta_cierre  NUMBER := 0;
    cuenta_cierre1 NUMBER := 0;
    cuenta_cierre2 NUMBER := 0;
    cuenta_pedido  NUMBER := 0;
    oid_peri_next  NUMBER := 0;
    oid_peri_next1 NUMBER := 0;


    ls_anti_peri NUMBER;
    ls_dif_dias  NUMBER;
    v_fec_inic   DATE;
    cuenta       NUMBER;
    ls_mensaje   varchar2(100);

    tmpfechafact     cra_crono.fec_inic%TYPE;
    v_fec_proc_docu  cra_crono.fec_inic%TYPE;

    existe BOOLEAN := TRUE;



  BEGIN

    ls_mensaje   := null;

      ----- Ubica el periodo actual
      SELECT c.oid_peri, c.pais_oid_pais INTO v_oid_peri_actu, v_oid_pais
        FROM bas_ctrl_fact   a, seg_perio_corpo b, cra_perio       c
       WHERE a.cod_peri = b.cod_peri
         AND b.oid_peri = c.peri_oid_peri
         AND a.sta_camp = 0
         AND a.ind_camp_act = 1;

      ----- Ubica el territorio de la consultora
      SELECT mcua.ztad_oid_terr_admi
        INTO v_oid_terr
        FROM mae_clien_unida_admin mcua,
             mae_clien             mc
       WHERE mcua.clie_oid_clie = mc.oid_clie
         AND mcua.perd_oid_peri_fin IS NULL
         AND mc.cod_clie = pscodclie
         AND rownum = 1;

      ----- Ubica el grupo de zona
      select a.oid_cabe_grup_zona
        into v_oid_cabe_grup_zona
      from cra_cabec_grupo_zona a , cra_detal_grupo_zona b
      where A.OID_CABE_GRUP_ZONA = B.CGZO_OID_CABE_GRUP_ZONA
      and B.ZZON_OID_ZONA = (SELECT c.oid_zona
                                  FROM zon_terri_admin a,
                                       zon_secci       b,
                                       zon_zona        c
                                 WHERE a.zscc_oid_secc = b.oid_secc
                                   AND b.zzon_oid_zona = c.oid_zona
                                   AND a.oid_terr_admi = v_oid_terr);

      ----- Ubica el oid de la actividad
      select oid_acti into v_oid_acti
      from cra_activ a, seg_pais b
      where A.PAIS_OID_PAIS = B.OID_PAIS
      and B.COD_PAIS = pscodigopais
      and A.COD_ACTI = 'FA';


        -- Validamos si hubo cierre de zona para la campa¿a actual
        SELECT COUNT(1) INTO cuenta_cierre1
          FROM fac_contr_cierr
         WHERE tcie_oid_tipo_cier = 2
           AND perd_oid_peri = v_oid_peri_actu
           AND zzon_oid_zona = (SELECT c.oid_zona
                                  FROM zon_terri_admin a,
                                       zon_secci       b,
                                       zon_zona        c
                                 WHERE a.zscc_oid_secc = b.oid_secc
                                   AND b.zzon_oid_zona = c.oid_zona
                                   AND a.oid_terr_admi = v_oid_terr);

        -- Validamos si hubo cierre de region de la campa¿a actual
        SELECT COUNT(1) INTO cuenta_cierre2
          FROM fac_contr_cierr
         WHERE tcie_oid_tipo_cier = 1
           AND perd_oid_peri = v_oid_peri_actu
           AND zorg_oid_regi = (SELECT d.oid_regi
                                  FROM zon_terri_admin a,
                                       zon_secci       b,
                                       zon_zona        c,
                                       zon_regio       d
                                 WHERE a.zscc_oid_secc = b.oid_secc
                                   AND b.zzon_oid_zona = c.oid_zona
                                   AND d.oid_regi = c.zorg_oid_regi
                                   AND a.oid_terr_admi = v_oid_terr);

        cuenta_cierre := cuenta_cierre2 + cuenta_cierre1;

        IF (cuenta_cierre = 0) THEN
          -- Validamos si la consultora paso pedido
          SELECT COUNT(1) INTO cuenta_pedido
            FROM ped_solic_cabec a,
                 ped_solic_cabec cons,
                 mae_clien       mc
           WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe
             AND a.clie_oid_clie = mc.oid_clie
             AND a.ind_oc = 1
             AND a.ind_ts_no_conso = 1
             AND a.perd_oid_peri = v_oid_peri_actu
             AND cons.val_tota_paga_loca > 0
             AND a.esso_oid_esta_soli IN (1,5)
             AND mc.cod_clie = pscodclie;
        END IF;

        -- Si la consultora no paso pedido, no ha cerrado su zona ni su region
        -- entonces la validacion es con periodo actual, caso contrario el sgte
        IF (cuenta_cierre = 0 AND cuenta_pedido = 0) THEN

          v_oid_peri := v_oid_peri_actu;

        ELSE

          SELECT oid_peri INTO oid_peri_next
            FROM (SELECT oid_peri
                    FROM cra_perio
                   WHERE fec_inic > (SELECT fec_inic FROM cra_perio WHERE oid_peri = v_oid_peri_actu)
                   ORDER BY fec_inic ASC)
           WHERE rownum = 1;

          v_oid_peri := oid_peri_next;

        END IF;

        existe := TRUE;
        BEGIN

          SELECT fec_inic
            INTO tmpfechafact
            FROM cra_crono
           WHERE cact_oid_acti = (SELECT oid_acti
                                    FROM cra_activ
                                   WHERE pais_oid_pais = v_oid_pais
                                     AND cod_acti = 'FA')
             AND perd_oid_peri = v_oid_peri
             AND zzon_oid_zona = (SELECT a.oid_zona
                                    FROM zon_zona        a,
                                         zon_secci       b,
                                         zon_terri_admin c
                                   WHERE c.zscc_oid_secc = b.oid_secc
                                     AND b.zzon_oid_zona = a.oid_zona
                                     AND c.oid_terr_admi = v_oid_terr);

          EXCEPTION
            WHEN no_data_found THEN
              existe := FALSE;
              ls_mensaje   := 'No hay Cronograma para esta consultora en campa¿a '
                    || gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(v_oid_peri);
          END;

        if ls_mensaje is null then
           BEGIN

              select B.NUM_DIAS_ante_Fact into v_num_dias_ante
              from rec_opera a, rec_tipos_opera b
              where b.ROPE_OID_OPER = a.OID_OPER
              and cod_oper = pscodoper
              and val_tipo_oper = pstipoper;

          EXCEPTION
            WHEN no_data_found THEN
              existe := FALSE;
              ls_mensaje   := 'No existe la operacion '||pscodoper || '-' || pstipoper;
          END;
        end if;

        if (ls_mensaje is null  and v_num_dias_ante is not null)
          then

              ---- Calcula le nueva fecha
              v_fec_calc := trunc(tmpfechafact) +
                             cra_pkg_proce.CRA_FN_CALCU_DESPL_VALID(pscodigopais,
                                                                    v_oid_cabe_grup_zona,
                                                                    v_oid_acti,
                                                                    trunc(tmpfechafact),
                                                                    v_num_dias_ante*-1);

              ---- Calcula la diferencia de dias entre hoy y la fecha del pedido
              --- ls_dif_dias:=  trunc(sysdate) - trunc(tmpfechafact);
              ls_dif_dias:=  trunc(tmpfechafact) - trunc(sysdate);

              ---- Si los dias trasncurridos son mayores a lo de la parametria se rechaza de lo contrario se acepta
              cuenta := 0;
              ---if ls_dif_dias <= v_num_dias_ante then
              if trunc(sysdate) > trunc(v_fec_calc) then
                 ls_mensaje := 'Solo puede pasar '|| pscodoper || '-' || pstipoper ||
                               ' hasta ' ||to_char(v_num_dias_ante) || ' antes de la facturacion de ' ||
                               gen_pkg_gener.GEN_FN_DEVUELVE_DES_PERIO(v_oid_peri) ||
                               ' (FF. ' ||to_char(trunc(tmpfechafact),'DD/MM/YYYY') || ' / ' ||
                               'FC. ' ||to_char(trunc(v_fec_calc),'DD/MM/YYYY') || ')';
              end if;

        end if;


    RETURN (ls_mensaje);

  END sto_fn_spvd_dias_fact;

  /***************************************************************************
  Descripcion       : Validacion si el producto es recuperacion
  Fecha Creacion    : 21/02/2013
  Autor             : Sandro Quintana Aponte
                      SPVD-79
  ***************************************************************************/
  FUNCTION sto_fn_spvd_valid_recup
  (
    psoidsoliposi      NUMBER
  )  RETURN BOOLEAN IS

    lnnrorecla   NUMBER;

  BEGIN

    existe := TRUE;

        select count(*) into lnnrorecla
        from ped_solic_posic x  ,
             pre_ofert_detal y,
             pre_matri_factu z ,
             pre_matri_recup a
        where X.OFDE_OID_DETA_OFER  = Y.OID_DETA_OFER
        and Y.OID_DETA_OFER = Z.OFDE_OID_DETA_OFER
        and z.OID_MATR_FACT = A.MAFA_OID_COD_PPAL
        and X.OID_SOLI_POSI = psoidsoliposi;

        IF (lnnrorecla >0) THEN
          existe := TRUE;
        ELSE
          existe := FALSE;
        END IF;

    RETURN (existe);

  END sto_fn_spvd_valid_recup;


  /***************************************************************************
    Descripcion       : Validacion validar la restricción de antigüedad de la
                        operación que va  a realizar
    Fecha Creacion    : 06/08/2012
    Autor             : Giovanni Ascarza
  ***************************************************************************/
PROCEDURE sto_pr_spvd_vali_anti_oper(pscodoperssicc  in VARCHAR2,
    pstipoperssicc     in  VARCHAR2,
    pscodigomotivo     in  VARCHAR2,
    psindiceexpress    in  VARCHAR2,
    pscodigopais       in  VARCHAR2,
    psnumpedi          in  VARCHAR2,
    psoidperirec       in  number,
    psusuario          in  VARCHAR2,
    pCodOpCorrecto     out varchar2,
    pmensaje           out varchar2

  ) IS

  varCodOperSICC  varchar2(500);
  v_existe boolean := false;
  v_excep  boolean := false;
  lsparametroval   sto_param_gener_occrr.val_param%TYPE;
  lsfecha          cra_perio.fec_inic%TYPE;
  lscodclie        mae_clien.cod_clie%TYPE;

    ls_mensaje   varchar2(100);


  BEGIN

   lsfecha := to_date('01/01/1900','DD/MM/YYYY');
    ls_mensaje   := null;


   ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
  BEGIN
    select mae.cod_clie
      into lscodclie
      from ped_solic_cabec psc1, mae_clien mae
      where psc1.clie_oid_clie = mae.oid_clie
      and psc1.val_nume_soli = psnumpedi
      and rownum = 1;
    EXCEPTION
      WHEN no_data_found THEN
        lscodclie := null;
    END;

   varCodOperSICC  := sto_pkg_proce_valid_spvd.sto_fn_spvd_tipo_opera ( pscodoperssicc,
                                           pstipoperssicc,
                                           pscodigomotivo,
                                           psindiceexpress);

   if varCodOperSICC is null then

    pmensaje := 'Codigo de Operacion sin parametria';
    else

    lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_IND_CDR_LINEA-16');

      if lsparametroval = '1' then
      v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                   null,
                                                                   null,
                                                                   lsfecha,
                                                                   'SPVC',
                                                                   'SPVC-16'));
         if not v_excep then
         v_existe := (sto_pkg_proce_valid_spvc.sto_fn_spvc_sadmc_bload(psnumpedi));
      else
         v_existe := true;
      end if;
      else
         v_existe := true;
      end if;

      if not v_existe then
      pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                         'SPVC',
                                                         'SPVC-16',
                                                         'W');
         if pmensaje is null then
            pmensaje := 'Bloqueada para ingresar CDR (SPVC-16)' || ' ';
         end if;
      else

      pCodOpCorrecto := varCodOperSICC;

      lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_IND_VAL_USU_CDR');

          if lsparametroval = '1' then
        v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_usuar_opera(pscodigopais,
                                                                      varCodOperSICC,
                                                                      pstipoperssicc,
                                                                      psusuario));
          else
             v_existe := true;
          end if;
          if not v_existe then
        pmensaje := 'Usuario no autorizado para ingresar esta operacion ' ||
                    varCodOperSICC || ' - ' || pstipoperssicc || ' - ' || psusuario;
          else

-------

          lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                 'STO_IND_CDR_LINEA_67');

      if lsparametroval = '1' then
            v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                         null,
                                                                         null,
                                                                         lsfecha,
                                                                         'SPVD',
                                                                         'SPVD-67'));
                 if not v_excep then
              v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_nodev_xdias(pscodigopais,
                                                                            varCodOperSICC,
                                                                            pstipoperssicc,
                                                                            psnumpedi));
            else
              v_existe := true;
            end if;
          else
            v_existe := true;
          end if;
          if not v_existe then
            pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                               'SPVD',
                                                               'SPVD-67',
                                                               'W');
            if pmensaje is null then
              pmensaje := 'No se acepta devoluciones excede en numero de dias (SPVD-67)' || ' ';
            end if;
          else

            lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                     'STO_IND_CDR_LINEA_76');

            if lsparametroval = '1' then
              v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                           null,
                                                                           null,
                                                                           lsfecha,
                                                                           'SPVD',
                                                                             'SPVD-76'));
              if not v_excep then
                  ls_mensaje := (sto_pkg_proce_valid_spvd.sto_fn_spvd_dias_fact(pscodigopais,
                                                                              varCodOperSICC,
                                                                              pstipoperssicc,
                                                                               lscodclie));
         v_existe := true;
                  if ls_mensaje is not null then
                      v_existe := false;
      end if;

        else
           v_existe := true;
        end if;
                else
                   v_existe := true;
                end if;
        if not v_existe then

                      pmensaje := ls_mensaje;

                /*pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                   'SPVD',
                                                                   'SPVD-76',
                                                                   'W');
            if pmensaje is null then
                  pmensaje := 'Codigo de Operacion excede en numero de dias (SPVD-76)' || ' ';
                end if;*/

                  else

                  lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_IND_CDR_LINEA_6');

                      if lsparametroval = '1' then
                    v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                                 null,
                                                                                 null,
                                                                                 lsfecha,
                                                                                 'SPVD',
                                                                               'SPVD-6'));
                         if not v_excep then
                    v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_canti_diatr(pscodigopais,
                                                                                    varCodOperSICC,
                                                                                  pstipoperssicc,
                                                                                  psnumpedi,
                                                                                  psoidperirec,
                                                                                  'L',
                                                                                   null,
                                                                                  0,
                                                                                  0));
                             else
                                v_existe := true;
                             end if;
                          else
                             v_existe := true;
                          end if;
                          if not v_existe then
                      pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                         'SPVD',
                                                                     'SPVD-6',
                                                                         'W');
                              if pmensaje is null then
                    pmensaje := 'Codigo de Operacion excede en numero de dias (SPVD-6)' || ' ';
                              end if;
                          else

                      lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                         'STO_IND_CDR_LINEA_56');

                              if lsparametroval = '1' then
                        v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                                     null,
                                                                                     null,
                                                                                     lsfecha,
                                                                                     'SPVD',
                                                                                 'SPVD-56'));
                                 if not v_excep then
                      v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_canti_recla(varCodOperSICC,
                                                                                    psnumpedi,
                                                                                    0,
                                                                                    0,
                                                                                    'L'));
                                 else
                                    v_existe := true;
                                 end if;
                              else
                                 v_existe := true;
                              end if;
                              if not v_existe then
                        pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                           'SPVD',
                                                                       'SPVD-56',
                                                                           'W');
                                  if pmensaje is null then
                      pmensaje := 'Excede en cantidad de reclamos por campaña (SPVD-56)' || ' ';
                                  end if;

                                      end if;
                                  end if;
                              end if;
                          end if;
--------
   end if;

   end if;

   end if;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                            'ERROR sto_pr_spvd_vali_anti_oper: ' ||
                            ls_sqlerrm);

  END sto_pr_spvd_vali_anti_oper;
 /***************************************************************************
    Descripcion       : Validacion de excepcion y producto ganador
    Fecha Creacion    : 06/08/2012
    Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_vali_eprod_gana(psOidPeriCDR   in number,
    pscodigoVenta     in  VARCHAR2,
    psCodOperSICC     in  VARCHAR2,
    psTipoOperSICC    in  VARCHAR2,
    psOidSoliPosi     in  number,
    pscodigomotivo    in  VARCHAR2,
    pmensaje          out varchar2

  ) IS

  v_tieneExcepcion boolean := false;
  v_tieneReclamo boolean := false;
    v_existe         boolean := false;
  v_listaBlanca boolean := false;
  v_excep  boolean := false;
  lsfecha          cra_perio.fec_inic%TYPE;
    lsFechaAct       cra_perio.fec_inic%TYPE;

  lsOidPeriCDR     int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
  lscodclie        int_solic_conso_poven_cabec.cod_clie%TYPE;
  lsparametroval   sto_param_gener_occrr.val_param%TYPE;
    lsnumpedi      ped_solic_cabec.val_nume_soli%TYPE;
    lsoidcabe      ped_solic_cabec.oid_soli_cabe%TYPE;
    lsindoc        ped_solic_cabec.ind_oc%TYPE;
    lstsol         ped_solic_cabec.tspa_oid_tipo_soli_pais%TYPE;
    lsmodu         ped_solic_cabec.modu_oid_modu%TYPE;
  pscodigopais     sto_param_gener_occrr.cod_pais%TYPE;
  lnoidpais        int_solic_conso_poven_cabec.oid_pais%TYPE;

  BEGIN


   lsfecha := to_date('01/01/1900','DD/MM/YYYY');

    select trunc(sysdate) into lsFechaAct from dual;


   select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;

   lnoidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

   ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
      BEGIN

      select PSC1.PERD_OID_PERI, mae.cod_clie, psc1.val_nume_soli, psc1.oid_soli_cabe,
             psc.ind_oc,psc.tspa_oid_tipo_soli_pais,psc.modu_oid_modu
        into lsOidPeriCDR, lscodclie, lsnumpedi, lsoidcabe,lsindoc,lstsol,lsmodu
        from ped_solic_posic psp,
             ped_solic_Cabec psc,
             ped_solic_cabec psc1,
             mae_clien       mae
        where PSP.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and PSC.SOCA_OID_SOLI_CABE = PSC1.OID_SOLI_CABE
        and psc1.clie_oid_clie = mae.oid_clie
        and PSP.OID_SOLI_POSI = psOidSoliPosi
        and rownum = 1;

      EXCEPTION
        WHEN no_data_found THEN
          lsOidPeriCDR := psOidPeriCDR;
      END;


    lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_IND_CDR_LINEA_32');

    if lsparametroval = '1' then

      v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                   null,
                                                                   null,
                                                                   lsfecha,
                                                                   'SPVD',
                                                                   'SPVD-32'));
      if not v_excep then
        v_tieneExcepcion := sto_pkg_proce_valid_spvd.sto_fn_spvd_repre_devue(pscodigopais,
                                                                             lsOidPeriCDR,
                                                                             pscodigoVenta,
                                                                             psCodOperSICC,
                                                                             psTipoOperSICC,
                                                                             lsoidcabe );
      else
        v_tieneExcepcion := true;
      end if;

    else
      v_tieneExcepcion := true;
    end if;
    --Si esta en excepciones
    if not v_tieneExcepcion then
      pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                         'SPVD',
                                                         'SPVD-32',
                                                         'W');
      if pmensaje is null then
        pmensaje := 'CUV que devulve no pertenece a la operacion (SPVD-32)';
      end if;
      pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
    else
-----------------------------
-----------

    lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_IND_CDR_LINEA_58');

     if lsparametroval = '1' then
        v_listaBlanca  := sto_pkg_proce_valid_spvd.sto_fn_spvd_moti_real_devo(lscodclie,
                                                                             lsOidPeriCDR,
                                                                             pscodigoVenta,
                                                                             psCodOperSICC,
                                                                             psTipoOperSICC);
     else
        v_listaBlanca := false;
     end if;

    if not v_listaBlanca then

      lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_IND_CDR_LINEA_12');

   if lsparametroval = '1' then

        v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                     null,
                                                                     null,
                                                                     lsfecha,
                                                                     'SPVD',
                                                                     'SPVD-12'));
         if not v_excep then
          v_tieneExcepcion  := sto_pkg_proce_valid_spvd.sto_fn_spvd_excep_codve(lsOidPeriCDR,
                                                                           pscodigoVenta,
                                                                           psCodOperSICC,
                                                                           psTipoOperSICC);
   else
      v_tieneExcepcion := true;
   end if;

       else
          v_tieneExcepcion := true;
       end if;
   --Si esta en excepciones
   if not v_tieneExcepcion then
        pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                           'SPVD',
                                                           'SPVD-12',
                                                           'W');
      if pmensaje is null then
             pmensaje := 'CUV inscrito en excepciones (SPVD-12)';
      end if;
          pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
   else
     -- valida producto ganador

        lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_IND_CDR_LINEA_36');

     if lsparametroval = '1' then
        v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                       null,
                                                                       null,
                                                                       lsfecha,
                                                                       'SPVD',
                                                                       'SPVD-36'));
        if not v_excep then
           v_tieneReclamo := sto_pkg_proce_valid_spvd.sto_fn_spvd_produ_ganad(psCodOperSICC,
                                                                               psOidSoliPosi);
        else
           v_tieneReclamo := true;
        end if;
     else
        v_tieneReclamo := true;
     end if;

     if not v_tieneReclamo then
          pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                             'SPVD',
                                                             'SPVD-36',
                                                             'W');
        if pmensaje is null then
               pmensaje := 'CUV es producto ganador (SPVD-36)';
        end if;
            pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
     else

        lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_IND_CDR_LINEA_9');

        if lsparametroval = '1' then
          v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                       null,
                                                                       null,
                                                                       lsfecha,
                                                                       'SPVD',
                                                                       'SPVD-9'));
          if not v_excep then
            v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_tipo_bldev(pscodigopais,
                                                                         psCodOperSICC,
                                                                         lsnumpedi));
          else
            v_existe := true;
          end if;
        else
          v_existe := true;
        end if;
        if not v_existe then
          pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                             'SPVD',
                                                             'SPVD-9',
                                                             'W');
          if pmensaje is null then
            pmensaje := 'Recurrencia en devoluciones (SPVD-9)' || ' ';
          end if;
            pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
        else

          lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                 'STO_IND_CDR_LINEA_8');

          if lsparametroval = '1' then
            v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                         null,
                                                                         null,
                                                                         lsfecha,
                                                                         'SPVD',
                                                                         'SPVD-8'));
            if not v_excep then
              v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_fmefa_noenv(pscodigopais,
                                                                            psCodOperSICC,
                                                                            lsnumpedi));
            else
              v_existe := true;
            end if;
          else
            v_existe := true;
          end if;
          if not v_existe then
            pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                               'SPVD',
                                                               'SPVD-8',
                                                               'W');
            if pmensaje is null then
              pmensaje := 'Recurrencia en Faltantes (SPVD-8)' || ' ';
            end if;
              pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
          else
            lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                   'STO_IND_CDR_LINEA_64');

            if lsparametroval = '1' then
              v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                           null,
                                                                           null,
                                                                           lsfecha,
                                                                           'SPVD',
                                                                           'SPVD-64'));
              if not v_excep then
                v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_recur_falta_premi(pscodigopais,
                                                                                    psCodOperSICC,
                                                                                    lsnumpedi));
              else
                v_existe := true;
              end if;
            else
              v_existe := true;
            end if;
            if not v_existe then
              pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                 'SPVD',
                                                                 'SPVD-64',
                                                                 'W');
              if pmensaje is null then
                pmensaje := 'Recurrencia en Faltante de Premio (SPVD-64)' || ' ';
              end if;
                pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
            else

              lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                     'STO_IND_CDR_LINEA_65');

              if lsparametroval = '1' then
                v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                             null,
                                                                             null,
                                                                             lsfecha,
                                                                             'SPVD',
                                                                             'SPVD-65'));
                if not v_excep then
                  v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_recur_error_inter(pscodigopais,
                                                                                      psCodOperSICC,
                                                                                      lsnumpedi));
                else
                  v_existe := true;
                end if;
              else
                v_existe := true;
              end if;
              if not v_existe then
                pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                   'SPVD',
                                                                   'SPVD-65',
                                                                   'W');
                if pmensaje is null then
                  pmensaje := 'Recurrencia en Error Interno (SPVD-65)' || ' ';
                end if;
                  pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
              else

                lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_IND_CDR_LINEA_66');

                if lsparametroval = '1' then
                  v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                               null,
                                                                               null,
                                                                               lsfecha,
                                                                               'SPVD',
                                                                               'SPVD-66'));
                  if not v_excep then
                    v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_recur_produ_grati(pscodigopais,
                                                                                        psCodOperSICC,
                                                                                        lsnumpedi));
                  else
                    v_existe := true;
                  end if;
                else
                  v_existe := true;
                end if;
                if not v_existe then
                  pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                     'SPVD',
                                                                     'SPVD-66',
                                                                     'W');
                  if pmensaje is null then
                    pmensaje := 'Recurrencia en Productos Gratis (SPVD-66)' || ' ';
                  end if;
                    pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
                  else

                    lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                           'STO_IND_CDR_LINEA_68');

                    if lsparametroval = '1' then
                      v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                                   null,
                                                                                   null,
                                                                                   lsfecha,
                                                                                   'SPVD',
                                                                                   'SPVD-68'));
                      if not v_excep then
                        v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_recur_cambi_produ(pscodigopais,
                                                                                            psCodOperSICC,
                                                                                            lsnumpedi));
                      else
                        v_existe := true;
                      end if;
                    else
                      v_existe := true;
                    end if;
                    if not v_existe then
                      pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                         'SPVD',
                                                                         'SPVD-68',
                                                                         'W');
                      if pmensaje is null then
                        pmensaje := 'Recurrencia en Cambio de Productos (SPVD-68)' || ' ';
                      end if;
                      pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
                    else

                      lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                             'STO_IND_CDR_LINEA_69');

                      if lsparametroval = '1' then
                        v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                                     null,
                                                                                     null,
                                                                                     lsfecha,
                                                                                     'SPVD',
                                                                                     'SPVD-69'));
                        if not v_excep then
                          v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_recur_trueq_produ(pscodigopais,
                                                                                              psCodOperSICC,
                                                                                              lsnumpedi));
                        else
                          v_existe := true;
                        end if;
                      else
                        v_existe := true;
                      end if;
                      if not v_existe then
                        pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                           'SPVD',
                                                                           'SPVD-69',
                                                                           'W');
                        if pmensaje is null then
                          pmensaje := 'Recurrencia en Trueque de Productos (SPVD-69)' || ' ';
                        end if;
                        pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;

                      else

                        lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                               'STO_IND_CDR_LINEA_60');

                        if lsparametroval = '1' then
                          v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                                       null,
                                                                                       null,
                                                                                       lsfecha,
                                                                                       'SPVD',
                                                                                       'SPVD-60'));
                          if not v_excep then
                            v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_nodev_recla(pscodigopais,
                                                                                          psCodOperSICC,
                                                                                          psTipoOperSICC,
                                                                                          lscodclie,
                                                                                          lsindoc,
                                                                                          lstsol,
                                                                                          lsmodu));
                          else
                            v_existe := true;
                          end if;
                        else
                          v_existe := true;
                        end if;
                        if not v_existe then
                          pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                             'SPVD',
                                                                             'SPVD-60',
                                                                             'W');
                          if pmensaje is null then
                            pmensaje := 'No acepta devolucion de un CDR (SPVD-60)' || ' ';
                          end if;
                          pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
                        else
                          ----
                            lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                                   'STO_IND_CDR_LINEA_23');

                            if lsparametroval = '1' then
                              v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                                           null,
                                                                                           null,
                                                                                           lsfecha,
                                                                                           'SPVD',
                                                                                           'SPVD-23'));
                              if not v_excep then
                                v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_mavca_falta(pscodigopais,
                                                                                              psCodOperSICC,
                                                                                              psTipoOperSICC,
                                                                                              psOidSoliPosi,
                                                                                              pscodigoVenta));
                              else
                                v_existe := true;
                              end if;
                            else
                              v_existe := true;
                            end if;

                            if not v_existe then
                              pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                                 'SPVD',
                                                                                 'SPVD-23',
                                                                                 'W');
                              if pmensaje is null then
                                pmensaje := 'Devolucion de MAV solo para CM y FM(SPVD-23)' || ' ';
                              end if;
                              pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
                              
                            else
 
                                lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                                       'STO_IND_CDR_LINEA_47');

                                if lsparametroval = '1' then
                                  v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                                               null,
                                                                                               null,
                                                                                               lsfecha,
                                                                                               'SPVD',
                                                                                               'SPVD-47'));
                                  if not v_excep then
                                    v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_falta_pedid_chequ(pscodigopais,
                                                                                                  psCodOperSICC,
                                                                                                  psTipoOperSICC,
                                                                                                  lsnumpedi));
                                  else
                                    v_existe := true;
                                  end if;
                                else
                                  v_existe := true;
                                end if;

                                if not v_existe then
                                  pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                                     'SPVD',
                                                                                     'SPVD-47',
                                                                                     'W');
                                  if pmensaje is null then
                                    pmensaje := 'No se acepta faltante sobre pedido chequeado(SPVD-47)' || ' ';
                                  end if;
                                  pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
                                 
                                end if;
                            
                            end if;
                          ----
                        end if;

                      end if;
                    end if;
                end if;
              end if;
            end if;
          end if;
        end if;


     end if;

   end if;
    end if;

    end if;

   -- valida FFNE

    lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_IND_CDR_LINEA_44');

   if lsparametroval = '1' then
      v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                   null,
                                                                   null,
                                                                   lsfecha,
                                                                   'SPVD',
                                                                   'SPVD-44'));
       if not v_excep then
        v_tieneReclamo := sto_pkg_proce_valid_spvd.sto_fn_spvd_cntrl_fnne(pscodigopais,
                                                                          psCodOperSICC,
                                                                          psTipoOperSICC,
                                                                          psOidSoliPosi,
                                                                          pscodigomotivo);
       else
          v_tieneReclamo := true;
       end if;
   else
      v_tieneReclamo := true;
   end if;

   if not v_tieneReclamo then
      pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                         'SPVD',
                                                         'SPVD-44',
                                                         'W');
      if pmensaje is null then
         if psCodOperSICC = 'FA' then
            pmensaje := 'Solo se acepta FM por reclamo de un pedido (SPVD-44)';
         else
            pmensaje := 'Solo se acepta FA por reclamo de un CDR (SPVD-44)';
         end if;
      end if;
    else

      lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_IND_CDR_LINEA_71');

      if lsparametroval = '1' then
        v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                     null,
                                                                     null,
                                                                     lsfecha,
                                                                     'SPVD',
                                                                     'SPVD-71'));
        if not v_excep then
          v_existe := (sto_pkg_proce_valid_spvd.sto_fn_spvd_garan_premi(pscodigopais,
                                                                              psCodOperSICC,
                                                                        psTipoOperSICC,
                                                                        lsFechaAct,
                                                                        lsnumpedi,
                                                                        psOidSoliPosi));
        else
          v_existe := true;
        end if;
      else
        v_existe := true;
      end if;
      if not v_existe then
        pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                           'SPVD',
                                                           'SPVD-71',
                                                           'W');
        if pmensaje is null then
          pmensaje := 'Producto fuera de garantia (SPVD-71)' || ' ';
        end if;
        pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
      end if;
   end if;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_vali_eprod_gana: ' ||
                              ls_sqlerrm);

  END sto_pr_spvd_vali_eprod_gana;


 /***************************************************************************
    Descripcion       : Validacion de aceptacion de CDR
    Fecha Creacion    : 24/03/2015
    Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_vali_acep_cdr(psOidPeriCDR   in number,
    pscodigoVenta     in  VARCHAR2,
    psCodOperSICC     in  VARCHAR2,
    psTipoOperSICC    in  VARCHAR2,
    psOidSoliPosi     in  number,
    pscodigomotivo    in  VARCHAR2,
    pmensaje          out varchar2

  ) IS

  v_tieneExcepcion boolean := false;
  v_tieneReclamo boolean := false;
    v_existe         boolean := false;
  v_listaBlanca boolean := false;
  v_excep  boolean := false;
  lsfecha          cra_perio.fec_inic%TYPE;
    lsFechaAct       cra_perio.fec_inic%TYPE;

  lsOidPeriCDR     int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
  lscodclie        int_solic_conso_poven_cabec.cod_clie%TYPE;
  lsparametroval   sto_param_gener_occrr.val_param%TYPE;
    lsnumpedi      ped_solic_cabec.val_nume_soli%TYPE;
    lsoidcabe      ped_solic_cabec.oid_soli_cabe%TYPE;
  pscodigopais     sto_param_gener_occrr.cod_pais%TYPE;
  lnoidpais        int_solic_conso_poven_cabec.oid_pais%TYPE;

  BEGIN

   lsfecha := to_date('01/01/1900','DD/MM/YYYY');

    select trunc(sysdate) into lsFechaAct from dual;


   select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;

   lnoidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

   ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
      BEGIN

      select PSC1.PERD_OID_PERI, mae.cod_clie, psc1.val_nume_soli, psc1.oid_soli_cabe
        into lsOidPeriCDR, lscodclie, lsnumpedi, lsoidcabe
        from ped_solic_posic psp,
             ped_solic_Cabec psc,
             ped_solic_cabec psc1,
             mae_clien       mae
        where PSP.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and PSC.SOCA_OID_SOLI_CABE = PSC1.OID_SOLI_CABE
        and psc1.clie_oid_clie = mae.oid_clie
        and PSP.OID_SOLI_POSI = psOidSoliPosi
        and rownum = 1;

      EXCEPTION
        WHEN no_data_found THEN
          lsOidPeriCDR := psOidPeriCDR;
      END;


    lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_IND_CDR_LINEA_58');

     if lsparametroval = '1' then
        v_listaBlanca  := sto_pkg_proce_valid_spvd.sto_fn_spvd_moti_real_devo(lscodclie,
                                                                             lsOidPeriCDR,
                                                                             pscodigoVenta,
                                                                             psCodOperSICC,
                                                                             psTipoOperSICC);
         pmensaje := null;
         if v_listaBlanca then
            pmensaje := 'Lista Blanca';
         else
            lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                   'STO_IND_CDR_LINEA_79');
            if lsparametroval = '1' then
               v_listaBlanca  := sto_pkg_proce_valid_spvd.sto_fn_spvd_valid_recup(psOidSoliPosi);
               if v_listaBlanca then
                  pmensaje := 'Recuperacion';
               else
                  v_listaBlanca := false;
                  pmensaje := null;
               end if;

            end if;

         end if;
     else
        v_listaBlanca := false;
        pmensaje := null;

     end if;


  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_vali_acep_cdr: ' ||
                              ls_sqlerrm);

  END sto_pr_spvd_vali_acep_cdr;


 /***************************************************************************
    Descripcion       : Validacion de motivo de CDR
    Fecha Creacion    : 18/05/2015
    Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_vali_moti_cdr(psOidPeriCDR   in number,
    pscodigoVenta     in  VARCHAR2,
    psCodOperSICC     in  VARCHAR2,
    psTipoOperSICC    in  VARCHAR2,
    psOidSoliPosi     in  number,
    pscodigomotivo    in  VARCHAR2,
    pmensaje          out varchar2

  ) IS

  v_tieneExcepcion boolean := false;
  v_tieneReclamo boolean := false;
    v_existe         boolean := false;
  v_listaBlanca boolean := false;
  v_excep  boolean := false;
  lsfecha          cra_perio.fec_inic%TYPE;
    lsFechaAct       cra_perio.fec_inic%TYPE;

  lsOidPeriCDR     int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
  lscodclie        int_solic_conso_poven_cabec.cod_clie%TYPE;
  lsparametroval   sto_param_gener_occrr.val_param%TYPE;
    lsnumpedi      ped_solic_cabec.val_nume_soli%TYPE;
    lsoidcabe      ped_solic_cabec.oid_soli_cabe%TYPE;
    lsoidcabepos   ped_solic_cabec.oid_soli_cabe%TYPE;
  pscodigopais     sto_param_gener_occrr.cod_pais%TYPE;
  lnoidpais        int_solic_conso_poven_cabec.oid_pais%TYPE;

  lstposposi       ped_solic_posic.tpos_oid_tipo_posi%TYPE;
  lsstpoposi       ped_solic_posic.stpo_oid_subt_posi%TYPE;

  BEGIN

   lsfecha := to_date('01/01/1900','DD/MM/YYYY');

    select trunc(sysdate) into lsFechaAct from dual;


   select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;

   lnoidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

   ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
      BEGIN

      select PSC1.PERD_OID_PERI, mae.cod_clie, psc1.val_nume_soli, psc1.oid_soli_cabe,
             psp.tpos_oid_tipo_posi, psp.stpo_oid_subt_posi, psp.soca_oid_soli_cabe
        into lsOidPeriCDR, lscodclie, lsnumpedi, lsoidcabe,lstposposi, lsstpoposi, lsoidcabepos
        from ped_solic_posic psp,
             ped_solic_Cabec psc,
             ped_solic_cabec psc1,
             mae_clien       mae
        where PSP.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
        and PSC.SOCA_OID_SOLI_CABE = PSC1.OID_SOLI_CABE
        and psc1.clie_oid_clie = mae.oid_clie
        and PSP.OID_SOLI_POSI = psOidSoliPosi
        and rownum = 1;

      EXCEPTION
        WHEN no_data_found THEN
          lsOidPeriCDR := psOidPeriCDR;
      END;



   lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_CDR_LINEA_45');

   if lsparametroval = '1' then
       v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,null,null,lsfecha,'SPVD','SPVD-45'));
       if not v_excep then
          v_tieneExcepcion := sto_pkg_proce_valid_spvd.sto_fn_spvd_motiv_devol(psCodOperSICC,
                                                                               psTipoOperSICC,
                                                                               pscodigomotivo);
       else
          v_tieneExcepcion  := true;
       end if;

   else
      v_tieneExcepcion  := true;
   end if;


   --Si el motivo no corresponte a la operacion
   pmensaje := null;
   if not v_tieneExcepcion then
      pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,'SPVD','SPVD-45','W');
      if pmensaje is null then
         pmensaje := 'Motivo no corresponde a la operacion (SPVD-45)';
      end if;
      pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
   else
   -----
      --- si no es error de sacado
      if psCodOperSICC <> 'ES' and psCodOperSICC <> 'EA' then

        lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_IND_CDR_LINEA_58');

         if lsparametroval = '1' then
            v_listaBlanca  := sto_pkg_proce_valid_spvd.sto_fn_spvd_moti_real_devo(lscodclie,
                                                                                 lsOidPeriCDR,
                                                                                 pscodigoVenta,
                                                                                 psCodOperSICC,
                                                                                 psTipoOperSICC);
         else
            v_listaBlanca := false;
         end if;

        if not v_listaBlanca then

          lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                 'STO_IND_CDR_LINEA_61');

           if lsparametroval = '1' then

              v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                             null,
                                                                             null,
                                                                             lsfecha,
                                                                             'SPVD',
                                                                             'SPVD-61'));
             if not v_excep then
                v_tieneExcepcion  := sto_pkg_proce_valid_spvd.sto_fn_spvd_nodev_pedid_monmi(pscodigopais,
                                                                                 psCodOperSICC,
                                                                                 psTipoOperSICC,
                                                                                 lstposposi,
                                                                                 lsstpoposi,
                                                                                 pscodigomotivo);
             else
                v_tieneExcepcion := true;
             end if;

           else
              v_tieneExcepcion := true;
           end if;
           --Si esta en excepciones
           if not v_tieneExcepcion then
                pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                   'SPVD',
                                                                   'SPVD-61',
                                                                   'W');
              if pmensaje is null then
                     pmensaje := 'No acepta devoluciones de pedido rescatado por minimo (SPVD-61)';
              end if;
              pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
           else
             -- valida producto ganador

              lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                     'STO_IND_CDR_LINEA_62');

             if lsparametroval = '1' then
                v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                               null,
                                                                               null,
                                                                               lsfecha,
                                                                               'SPVD',
                                                                               'SPVD-62'));
                if not v_excep then
                   v_tieneReclamo := sto_pkg_proce_valid_spvd.sto_fn_spvd_nodev_catal(pscodigopais,
                                                                                 lsOidPeriCDR,
                                                                                 pscodigoVenta,
                                                                                 psCodOperSICC,
                                                                                 psTipoOperSICC,
                                                                                 pscodigomotivo);
                else
                   v_tieneReclamo := true;
                 end if;
              else
                 v_tieneReclamo := true;
              end if;

             if not v_tieneReclamo then
                  pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                     'SPVD',
                                                                     'SPVD-62',
                                                                     'W');
                if pmensaje is null then
                   pmensaje := 'No acepta devolucion de un catalogo especifico (SPVD-62)';
                end if;
                pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
             else

                lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_IND_CDR_LINEA_63');

                if lsparametroval = '1' then
                    v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                                 null,
                                                                                 null,
                                                                                 lsfecha,
                                                                                 'SPVD',
                                                                                 'SPVD-63'));
                    if not v_excep then
                      v_existe := sto_pkg_proce_valid_spvd.sto_fn_spvd_nodev_remes(pscodigopais,
                                                                                 lsoidcabe,
                                                                                 psCodOperSICC,
                                                                                 psTipoOperSICC,
                                                                                 pscodigomotivo);
                    else
                      v_existe := true;
                    end if;
                else
                  v_existe := true;
                end if;
                if not v_existe then
                   pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                     'SPVD',
                                                                     'SPVD-63',
                                                                     'W');
                   if pmensaje is null then
                      pmensaje := 'No acepta devolucion por remesa (SPVD-63)' || ' ';
                   end if;
                   pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
                else

                    lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                           'STO_IND_CDR_LINEA_70');

                    if lsparametroval = '1' then
                      v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                                   null,
                                                                                   null,
                                                                                   lsfecha,
                                                                                   'SPVD',
                                                                                   'SPVD-70'));
                      if not v_excep then
                        v_existe := sto_pkg_proce_valid_spvd.sto_fn_spvd_orige_produ(pscodigopais,
                                                                                 lsoidcabepos,
                                                                                 pscodigoVenta,
                                                                                 psCodOperSICC,
                                                                                 psTipoOperSICC,
                                                                                 pscodigomotivo);
                      else
                        v_existe := true;
                      end if;
                    else
                      v_existe := true;
                    end if;
                    if not v_existe then
                       pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                                          'SPVD',
                                                                          'SPVD-70',
                                                                          'W');
                       if pmensaje is null then
                         pmensaje := 'No acepta devolucion por origen de producto (SPVD-70)' || ' ';
                       end if;
                        pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
                    end if;

                  end if;

             end if;

           end if;
        end if;

      end if;

   -----
   end if;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_vali_moti_cdr: ' ||
                              ls_sqlerrm);

  END sto_pr_spvd_vali_moti_cdr;


 /***************************************************************************
    Descripcion       : Validacion de unidad de reclamo
    Fecha Creacion    : 06/08/2012
    Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_vali_unid_recla
  (
    pscodigopais          in  VARCHAR2,
    pscodoCliente         in  VARCHAR2,
    psOidSoliPosi         in  number,
    psunidadesReclamar    in  number,
    psCodOperSICC         in  VARCHAR2,
    psTipoOperSICC        in  VARCHAR2,
    pmensaje              out VARCHAR2
  ) IS

  v_tieneExcepcion boolean := false;
  v_tieneMontoMini boolean := false;
  v_excep  boolean := false;
  lsparametroval   sto_param_gener_occrr.val_param%TYPE;
  lsCUV            int_solic_conso_poven_detal.cod_vent_dese%TYPE;
  lsfecha          cra_perio.fec_inic%TYPE;
  v_listaBlanca boolean := false;
  lsOidPeriCDR     int_solic_conso_poven_cabec.oid_peri_refe%TYPE;

  BEGIN

    BEGIN
      select nvl(psp.val_codi_vent,psp.val_codi_vent_fict), PSC1.PERD_OID_PERI
        into lsCUV, lsOidPeriCDR
        from ped_solic_posic psp,
             ped_solic_Cabec psc,
             ped_solic_cabec psc1
       where PSP.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
         and PSC.SOCA_OID_SOLI_CABE = PSC1.OID_SOLI_CABE
         and PSP.OID_SOLI_POSI = psOidSoliPosi
         and rownum = 1;
    EXCEPTION
      WHEN no_data_found THEN
        lsCUV := '';
        lsOidPeriCDR := null;
    END;

   lsfecha := to_date('01/01/1900','DD/MM/YYYY');

   lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_CDR_LINEA_7');

   if lsparametroval = '1' then
       v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(pscodoCliente,null,null,lsfecha,'SPVD','SPVD-7'));
       if not v_excep then
   v_tieneExcepcion  := sto_pkg_proce_valid_spvd.sto_fn_spvd_unida_recla(pscodigopais,
                                                                           pscodoCliente,
                                                                           psOidSoliPosi,
                                                                           psunidadesReclamar);
   else
      v_tieneExcepcion  := true;
   end if;

   else
      v_tieneExcepcion  := true;
   end if;


   --Si excede en numero de unidades
   if not v_tieneExcepcion then
      pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,'SPVD','SPVD-7','W');
      if pmensaje is null then
         pmensaje := 'Excede el numero de unidades disponibles para reclamar (SPVD-7)';
      end if;
      pmensaje := pmensaje || ' - CUV : ' || lsCUV;
   else

    lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_IND_CDR_LINEA_58');

     if lsparametroval = '1' then
        v_listaBlanca  := sto_pkg_proce_valid_spvd.sto_fn_spvd_moti_real_devo(pscodoCliente,
                                                                             lsOidPeriCDR,
                                                                             lsCUV,
                                                                             psCodOperSICC,
                                                                             psTipoOperSICC);
     else
        v_listaBlanca := false;
     end if;

    if not v_listaBlanca then
          --Verifica la validacion de Monto Minimo
         lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_CDR_LINEA_35');

         if lsparametroval = '1' then
             v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(pscodoCliente,null,null,lsfecha,'SPVD','SPVD-35'));
             if not v_excep then
             v_tieneMontoMini  := sto_pkg_proce_valid_spvd.sto_fn_spvd_monto_minim(pscodigopais,pscodoCliente,psOidSoliPosi,psunidadesReclamar,psCodOperSICC,psTipoOperSICC);
         else
             v_tieneMontoMini := true;
         end if;
         else
             v_tieneMontoMini := true;
         end if;

         if not v_tieneMontoMini then
            pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,'SPVD','SPVD-35','W');
            if pmensaje is null then
               pmensaje := 'Excede en Monto Minimo del pedido (SPVD-35)';
            end if;
            pmensaje := pmensaje || ' - CUV : ' || lsCUV;
         end if;
    end if;
   end if;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_vali_unid_recla: ' || ls_sqlerrm);

  END sto_pr_spvd_vali_unid_recla;

 /***************************************************************************
    Descripcion       : Validacion de excepcion y producto DESEA
    Fecha Creacion    : 06/08/2012
    Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_vali_eprod_desea(psOidPeriCDR   in number,
                                        pscodigoVenta  in VARCHAR2,
                                        psCodOperSICC  in VARCHAR2,
                                        psTipoOperSICC in VARCHAR2,
                                        psOidSoliPosi  in number,
                                        pscodigomotivo in VARCHAR2,
                                        psnumeropedido    in  VARCHAR2,
                                        pmensaje       out varchar2
                                        ) IS

    v_tieneExcepcion boolean := false;
    v_tieneReclamo   boolean := false;
    v_existe         boolean := false;
    v_listaBlanca    boolean := false;
    v_excep          boolean := false;
    lsfecha          cra_perio.fec_inic%TYPE;

    lsOidPeriCDR   int_solic_conso_poven_cabec.oid_peri_refe%TYPE;
    lscodclie      int_solic_conso_poven_cabec.cod_clie%TYPE;
    lsparametroval sto_param_gener_occrr.val_param%TYPE;
    lsnumpedi      ped_solic_cabec.val_nume_soli%TYPE;
    lsoidcabe      ped_solic_cabec.oid_soli_cabe%TYPE;
    pscodigopais   sto_param_gener_occrr.cod_pais%TYPE;
    lnoidpais      int_solic_conso_poven_cabec.oid_pais%TYPE;

  BEGIN

    lsfecha := to_date('01/01/1900', 'DD/MM/YYYY');

    select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum = 1;

    lnoidpais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

    ---- Obtiene el verdadero oid del reclamo y el codigo del cliente
    BEGIN

      select PSC1.PERD_OID_PERI, mae.cod_clie, psc1.val_nume_soli, psc1.oid_soli_cabe
        into lsOidPeriCDR, lscodclie, lsnumpedi, lsoidcabe
        from ped_solic_posic psp,
             ped_solic_Cabec psc,
             ped_solic_cabec psc1,
             mae_clien       mae
       where PSP.SOCA_OID_SOLI_CABE = PSC.OID_SOLI_CABE
         and PSC.SOCA_OID_SOLI_CABE = PSC1.OID_SOLI_CABE
         and psc1.clie_oid_clie = mae.oid_clie
         and PSP.OID_SOLI_POSI = psOidSoliPosi
         and rownum = 1;

    EXCEPTION
      WHEN no_data_found THEN
        lsOidPeriCDR := psOidPeriCDR;
    END;


    lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_IND_CDR_LINEA_31');

    if lsparametroval = '1' then

      v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                   null,
                                                                   null,
                                                                   lsfecha,
                                                                   'SPVD',
                                                                   'SPVD-31'));
      if not v_excep then
        v_tieneExcepcion := sto_pkg_proce_valid_spvd.sto_fn_spvd_repre_desea(pscodigopais,
                                                                             lsOidPeriCDR,
                                                                             pscodigoVenta,
                                                                             psCodOperSICC,
                                                                             psTipoOperSICC,
                                                                             lsoidcabe );
      else
        v_tieneExcepcion := true;
      end if;

    else
      v_tieneExcepcion := true;
    end if;
    --Si esta en excepciones
    if not v_tieneExcepcion then
      pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                         'SPVD',
                                                         'SPVD-31',
                                                         'W');
      if pmensaje is null then
        pmensaje := 'CUV que envia no pertenece a la operacion (SPVD-31)';
      end if;
      pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;

    else

        lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_IND_CDR_LINEA_52');

        if lsparametroval = '1' then

          v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(lscodclie,
                                                                       null,
                                                                       null,
                                                                       lsfecha,
                                                                       'SPVD',
                                                                       'SPVD-52'));
          if not v_excep then
            v_tieneExcepcion := sto_pkg_proce_valid_spvd.sto_fn_spvd_opera_trueq(pscodigopais,
                                                                                 psCodOperSICC,
                                                                                 psTipoOperSICC,
                                                                                 psOidSoliPosi,
                                                                                 pscodigoVenta,
                                                                                 psOidPeriCDR);
          else
            v_tieneExcepcion := true;
          end if;

        else
          v_tieneExcepcion := true;
        end if;

        --Si esta en excepciones
        if not v_tieneExcepcion then
          pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,
                                                             'SPVD',
                                                             'SPVD-52',
                                                             'W');
          if pmensaje is null then
            pmensaje := 'Solo acepta truque de misma familia (SPVD-52)';
          end if;
          pmensaje := pmensaje || ' - CUV : ' || pscodigoVenta;
       end if;

    end if;


  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_vali_eprod_desea: ' ||
                              ls_sqlerrm);

  END sto_pr_spvd_vali_eprod_desea;


 /***************************************************************************
    Descripcion       : Validacion de unidad de DESEA
    Fecha Creacion    : 06/08/2012
    Autor             : Giovanni Ascarza
  ***************************************************************************/
  PROCEDURE sto_pr_spvd_vali_unid_desea
  (
    pscodigopais          in  VARCHAR2,
    pscodoCliente         in  VARCHAR2,
    psOidSoliPosi         in  number,
    psunidadesReclamar    in  number,
    psCodOperSICC         in  VARCHAR2,
    psTipoOperSICC        in  VARCHAR2,
    pmensaje              out VARCHAR2
  ) IS

  v_tieneExcepcion boolean := false;
  v_tieneMontoMini boolean := false;
  v_excep  boolean := false;
  lsparametroval   sto_param_gener_occrr.val_param%TYPE;
  lsCUV            int_solic_conso_poven_detal.cod_vent_dese%TYPE;
  lsfecha          cra_perio.fec_inic%TYPE;

  lsparametrovalUniDesea  sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    BEGIN
      select nvl(psp.val_codi_vent,psp.val_codi_vent_fict)
        into lsCUV
        from ped_solic_posic psp
       where PSP.OID_SOLI_POSI = psOidSoliPosi
         and rownum = 1;
    EXCEPTION
      WHEN no_data_found THEN
        lsCUV := '';
    END;

   lsfecha := to_date('01/01/1900','DD/MM/YYYY');

   lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_CDR_LINEA_77');

   lsparametrovalUniDesea := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_NUM_UNI_ENVI');

   if lsparametroval = '1' then
       v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(pscodoCliente,null,null,lsfecha,'SPVD','SPVD-77'));
       if not v_excep then
          if nvl(lsparametrovalUniDesea,0) > 0 and  nvl(lsparametrovalUniDesea,0) < psunidadesReclamar then
             v_tieneExcepcion  := false;
          else
             v_tieneExcepcion  := true;
          end if;
   else
      v_tieneExcepcion  := true;
   end if;

   else
      v_tieneExcepcion  := true;
   end if;


   --Si excede en numero de unidades
   if not v_tieneExcepcion then
      pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,'SPVD','SPVD-77','W');
      if pmensaje is null then
         pmensaje := 'Excede el numero de unidades maximas para enviar (SPVD-77)';
      end if;
      pmensaje := pmensaje || ' - CUV : ' || lsCUV;
     /*
   else
      --Verifica la validacion de Monto Minimo
     lsparametroval := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_IND_CDR_LINEA_35');

     if lsparametroval = '1' then
         v_excep := (sto_pkg_proce_valid_spvc.sto_fn_spvc_excep_valid(pscodoCliente,null,null,lsfecha,'SPVD','SPVD-35'));
         if not v_excep then
         v_tieneMontoMini  := sto_pkg_proce_valid_spvd.sto_fn_spvd_monto_minim(pscodigopais,pscodoCliente,psOidSoliPosi,psunidadesReclamar,psCodOperSICC,psTipoOperSICC);
     else
         v_tieneMontoMini := true;
     end if;
     else
         v_tieneMontoMini := true;
     end if;

     if not v_tieneMontoMini then
        pmensaje := sto_pkg_gener.sto_fn_devue_valor_mensa(pscodigopais,'SPVD','SPVD-35','W');
        if pmensaje is null then
           pmensaje := 'Excede en Monto Minimo del pedido (SPVD-35)';
        end if;
        pmensaje := pmensaje || ' - CUV : ' || lsCUV;
     end if;
     */
   end if;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);

      raise_application_error(-20123,
                              'ERROR sto_pr_spvd_vali_unid_desea: ' || ls_sqlerrm);

  END sto_pr_spvd_vali_unid_desea;

END sto_pkg_proce_valid_spvd;
/
