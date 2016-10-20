CREATE OR REPLACE PACKAGE sto_pkg_proce_valid_occ IS
  /**************************************************************************
  Descripcion       : STO_PR_OCC_TIPO_SOLIC_OK
                    Procedimiento de Validacion de Tipo de Solicitud Sin Error
                    segun secuencia de ejecucion
  Fecha Creacion    : 22/02/2008
  Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_occ_tipo_solic
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Validacion de direcciones del cliente sin error
                      para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_direc_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Validacion de unidades administrativas sin error
                      para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
   ***************************************************************************/
  PROCEDURE sto_pr_occ_unida_admin
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de tipo de despacho sin error
                      para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_tipo_despa
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de tipo de cambio sin error
              para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_tipo_cambi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de cliente activo sin error
              para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_clien_activ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de segundo pedido sin error
              para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_segun_pedid
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de bloqueo de cliente sin error
              para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_bloqu_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de indicador no imprimible sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_valid_ssicc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de indicador no imprimible sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_cande
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Reemmplazo y anulados
  Fecha Creacion    : 15/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_reemp_anula
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Deuda
  Fecha Creacion    : 15/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_deuda
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Bloqueo por control
  Fecha Creacion    : 15/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_bloqu_contr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Rechazo por OCR
  Fecha Creacion    : 15/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_recha_ocr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de  Monto Minimo
  Fecha Creacion    : 15/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_monto_minim
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de  Monto Maximo
  Fecha Creacion    : 15/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_monto_maxim
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion que actualiza deuda
  Fecha Creacion    : 20/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_actua_deuda
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion para validar que las consultoras no tengan deuda en otra marca
  Fecha Creacion    : 11/02/2010
  Autor             : Jorge Florencio
  ***************************************************************************/
  PROCEDURE sto_pr_occ_deuda_marca
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de la Deuda de las Consultoras Cupon
  Fecha Creacion    : 11/02/2010
  Autor             : Jorge Florencio
  ***************************************************************************/
  PROCEDURE sto_pr_occ_deuda_cupon
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
   Descripcion       : Validacion de la Deuda de las Consultoras por Campa?a
   Fecha Creacion    : 07/12/2011
   Autor             : Jorge Florencio
  ***************************************************************************/
  PROCEDURE sto_pr_occ_deuda_campa
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
   Descripcion       : Validacion de la Deuda de las Consultoras por Campa?a
   Fecha Creacion    : 07/12/2011
   Autor             : Jorge Florencio
  ***************************************************************************/
  PROCEDURE sto_pr_occ_deuda_campa2
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Validacion de la Deuda de las Consultoras por Campa?a
  Fecha Creacion    : 07/12/2011
  Autor             : Jorge Florencio
  ***************************************************************************/
  PROCEDURE sto_pr_calcu_deuda
  (
    pscodigopais          VARCHAR2,
    pscodclie             VARCHAR2,
    pscodzona             VARCHAR2,
    psusuario             VARCHAR2,
    pscodperi             VARCHAR2,
    psno_existe_deuda     OUT BOOLEAN,
    pssaldo_deudor        OUT NUMBER,
    pssaldo_rechazo       OUT NUMBER,
    psabono_pendien       OUT NUMBER,
    psexce_flex           OUT NUMBER,
    pssald_maxi_camp_flex OUT NUMBER,
    psobse                OUT VARCHAR2
  ) ;

  /***************************************************************************
  Descripcion       : Validacion de codigo de cliente inexistente
  Fecha Creacion    : 23/09/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_clien_inexi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de codigo de cliente inexistente
  Fecha Creacion    : 24/09/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_clien_nueva
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Devuelve el numero de actividades por cronograma y periodo
                      considerando como base
  Fecha Creacion    : 12/05/2010
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_nuact_byzpe
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pnoidzona       NUMBER,
    lnnumperio      NUMBER
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion       : Devuelve el promedio de venta por consultora para FAD
  Fecha Creacion    : 17/01/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION sto_fn_devue_prom_venta
  (
    pnoidcliente NUMBER,
    pnoidperiini NUMBER,
    pnoidperifin NUMBER
  ) RETURN NUMBER;
  /***************************************************************************
  Descripcion       : Validacion de existencia de cronogramas de actividades
  Fecha Creacion    : 01/03/2010
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_exicr_activ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de error de vencimiento de cronograma
  Fecha Creacion    : 02/03/2010
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_error_vecro
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de unidades maximas por producto y pedido
  Fecha Creacion    : 04/03/2010
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_unida_maxim
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_OCC_PROGR_NUEVA_OK
                    Procedimiento de Validacion para ejecucion del programa
                    de Nuevas segun secuencia de ejecucion
  Fecha Creacion    : 15/04/2010
  Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_occ_progr_nueva
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_OCC_SESSI_EXPER_OK
                    Procedimiento de Validacion para ejecucion del programa
                    de Session Experte segun secuencia de ejecucion
  Fecha Creacion    : 15/04/2010

  Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_occ_sessi_exper
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_OCC_DUPLA_CYZON_OK
                    Procedimiento de Validacion para ejecucion del programa
                    de Dupla Cyzone segun secuencia de ejecucion
  Fecha Creacion    : 15/04/2010
  Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_occ_dupla_cyzon
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_OCC_OPORT_PRIVI_OK
                    Procedimiento de Validacion para ejecucion del programa
                    de Oportunidades Privilege segun secuencia de ejecucion
  Fecha Creacion    : 16/04/2010
  Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_occ_oport_privi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Documentos SAD Rechazados
  Fecha Creacion    : 23/04/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_dosad_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Bloqueo por control 2
  Fecha Creacion    : 07/06/2010
  Autor             : Jesse Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_occ_bloqu_contr2
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Validacion de Bloqueo por control 2
  Fecha Creacion    : 07/06/2010
  Autor             : Jesse Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_occ_bloqu_contr3
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Validacion de Nueva en Segundo Dia
  Fecha Creacion    : 24/08/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_nueva_sedia
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion Dummy utilizada por la consolidacion de
                      pedidos, con el fin de aparecer en la pantalla
                      de gestion
  Fecha Creacion    : 07/10/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_conso_clien_dummy
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Zonas de Arribo
  Fecha Creacion    : 08/11/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_zonas_arrib
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Rechazo por OCR 2
  Fecha Creacion    : 28/12/2010
  Autor             : Christian Gonzales
  ***************************************************************************/

  PROCEDURE sto_pr_occ_recha_ocr2
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Micas
  Fecha Creacion    : 28/12/2010
  Autor             : Christian Gonzales
  ***************************************************************************/

  PROCEDURE sto_pr_occ_micas
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion     : Validacion de Tipo de documento invalido
                        para Orden de compra Cabecera
  Fecha Creacion    : 28/12/2010
  Autor             : Nicolas Lopez
   ***************************************************************************/
  PROCEDURE sto_pr_occ_tipo_docum
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de secuenciacion de Zonas y Territorios sin error
                      para Orden de compra Cabecera
  Fecha Creacion    : 26/01/2011
  Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE sto_pr_occ_secue_zonter
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Validacion de Cobro de Flete Adicional por pase fuera de fecha
    Fecha Creacion    : 12/04/2011
    Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_occ_flete_adici_fuefe
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : STO_PR_OCC_PREMI_CORXP
                    Procedimiento de Validacion para ejecucion del programa
                    de Despacho de Premios del Concurso RxP
  Fecha Creacion    : 11/07/2011
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE sto_pr_occ_premi_corxp
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Valida si una consultara esta habilitada papra pedido
                      online
  Fecha Creacion    : 11/10/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  FUNCTION sto_fn_valid_bloqu_onlin
  (
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion       : Valida si una consultara esta habilitada papra pedido
                      adicional
  Fecha Creacion    : 07/03/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION sto_fn_valid_fad
  (
    pnoidperiodo VARCHAR2,
    pnoidcliente VARCHAR2
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion       : sto_pr_occ_marca_PROL
                    Marca en MAE_CLIE el  Indicador PROL (Pedidos con Reserva On Line)
                    IND_PROL [NULL o 0]: No habilitada
                                    [1]: Habilitada
  Fecha Creacion    : 26/10/2011
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE sto_pr_occ_marca_prol;

  /***************************************************************************
    Descripcion       : Validacion de Error en Configuracion de Matriz
    Fecha Creacion    : 02/07/2012
    Autor             : Jorge Velasquez
  ***************************************************************************/
  PROCEDURE sto_pr_occ_confi_matri
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Valiacion Fecha Facturacion
    Fecha Creacion    : 04/07/2012
    Autor             : Jorge Velasquez
  ***************************************************************************/
  PROCEDURE sto_pr_occ_fecha_factu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Validacion codigo Ciudad
    Fecha Creacion    : 04/07/2012
    Autor             : Jorge Velasquez
  ***************************************************************************/
  PROCEDURE sto_pr_occ_codig_ciuda
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Validacion de cuadre de ofertas
    Fecha Creacion    : 31/05/2013
    Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE sto_pr_occ_cuadr_ofert
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Validacion de Monto Minimo STO
    Fecha Creacion    : 31/05/2013
    Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE sto_pr_occ_monto_minim_sto
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Validacion de Monto Maximo STO
    Fecha Creacion    : 31/05/2013
    Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE sto_pr_occ_monto_maxim_sto
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Validacion de Monto Minimo Stock
    Fecha Creacion    : 14/06/2013
    Autor             : Carlos Chata
  ***************************************************************************/
  PROCEDURE sto_pr_occ_monto_minim_stock
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
    Descripcion       : Validacion de Forma de Pago Por Zona
    Fecha Creacion    : 26/06/2013
    Autor             : Carlos Chata
  ***************************************************************************/
  PROCEDURE sto_pr_occ_forma_pago_zona
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
    Descripcion       : Validacion de SCC En Gestion o rechazada
    Fecha Creacion    : 07/08/2013
    Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
  PROCEDURE sto_pr_occ_gesti_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /**************************************************************************
  Descripcion       : Validacion de cliente incobrable
  Fecha Creacion    : 26/08/2013
  Autor             : Yahir Rivas Luna.
  **************************************************************************/
  PROCEDURE sto_pr_occ_clien_incob
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Validacion de Anulacion por refacturacion para que puedan cambiar el pedido
  Fecha Creacion    : 28/12/2010
  Autor             : Sandro Quintana
  ***************************************************************************/

  PROCEDURE sto_pr_occ_anula_refa
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Validacion de Error desviacion
  Fecha Creacion    : 14/05/2014
  Autor             : José Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_occ_desvi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

  /***************************************************************************
    Descripcion       : Validacion de Forma de Pago Por Clasificacion
    Fecha Creacion    : 03/04/2014
    Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
    PROCEDURE sto_pr_occ_forma_pago_clasi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  );

END sto_pkg_proce_valid_occ;
/
CREATE OR REPLACE PACKAGE BODY sto_pkg_proce_valid_occ IS

  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);
  c_codigo_programa_dupla_cyzone CONSTANT VARCHAR2(3) := '002';

  /**************************************************************************
  Descripcion       : STO_PR_OCC_TIPO_SOLIC_OK
                    Procedimiento de Validacion de Tipo de Solicitud Sin Error
                    segun secuencia de ejecucion
  Fecha Creacion    : 22/02/2008
  Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_occ_tipo_solic
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_tiposolicitud IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             ptsp.oid_tipo_soli_pais,
             ptsp.mone_oid_mone,
             ptsp.almc_oid_alma,
             pts.ticl_oid_tipo_clie,
             ptsp.tsol_oid_tipo_cons,
             pts.clso_oid_clas_soli,
             nvl(nvl(ptsp.fopa_oid_form_pago,
                     (SELECT fopa_oid_form_pago
                        FROM mae_clien
                       WHERE cod_clie = cons.cod_clie)),
                 (SELECT fopa_oid_form_pago
                    FROM seg_pais
                   WHERE cod_pais = cons.cod_pais)) fopa_oid_form_pago,
             pcs.ind_orde_comp,
             ptsp.ind_perm_unio,
             ptsp.ind_pedi_prue,
             per.oid_peri,
             1 proc,
             sub.oid_sbac,
             ptsp.soci_oid_soci,
             ptsp.val_glos,
             ptsp.cact_oid_acti,
             pais.oid_pais,
             decode(ptsp.tido_oid_tipo_docu,
                    NULL,
                    (SELECT tido.tido_oid_tipo_docu
                       FROM mae_tipo_docum tido
                      WHERE tido.oid_tipo_docu =
                            (SELECT cli.tdoc_oid_tipo_docu
                               FROM mae_clien_ident cli
                              WHERE cli.clie_oid_clie =
                                    (SELECT oid_clie
                                       FROM mae_clien
                                      WHERE cod_clie = cons.cod_clie)
                                AND cli.val_iden_docu_prin = 1
                                AND rownum = 1)),
                    ptsp.tido_oid_tipo_docu) oid_tipo_docum,
             tsp.tpos_oid_tipo_posi,
             tsp.stpo_oid_subt_posi
        FROM ped_tipo_solic        pts,
             ped_tipo_solic_pais   ptsp,
             seg_acces             acc,
             cra_perio             per,
             seg_perio_corpo       pc,
             ped_clase_solic       pcs,
             seg_subac             sub,
             seg_pais              pais,
             int_solic_conso_cabec cons,
             sto_proce_docum_digit occ,
             ped_tipo_solic_proce  tsp,
             bel_opera
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND pts.oid_tipo_soli = ptsp.tsol_oid_tipo_soli
         AND pts.acce_oid_acce = acc.oid_acce
         AND per.pais_oid_pais = ptsp.pais_oid_pais
         AND per.marc_oid_marc = pts.marc_oid_marc
         AND per.cana_oid_cana = acc.cana_oid_cana
         AND pc.oid_peri = per.peri_oid_peri
         AND pcs.oid_clas_soli = pts.clso_oid_clas_soli
         AND sub.acce_oid_acce = pts.acce_oid_acce
         AND pais.oid_pais = ptsp.pais_oid_pais
         AND pts.cod_tipo_soli = cons.tipo_soli
         AND pais.cod_pais = cons.cod_pais
         AND pc.cod_peri = cons.cod_peri
         AND sub.cod_sbac = cons.cod_sbac
         AND bel_opera.cod_oper = 'OCR005'
         AND tsp.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
         AND bel_opera.oid_oper = tsp.oper_oid_oper;

    TYPE t_codpais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_oidtiposolipais IS TABLE OF int_solic_conso_cabec.tspa_oid_tipo_soli_pais%TYPE;
    TYPE t_oidmoneda IS TABLE OF int_solic_conso_cabec.mone_oid_mone%TYPE;
    TYPE t_oidalma IS TABLE OF int_solic_conso_cabec.almc_oid_alma%TYPE;
    TYPE t_oidtipocliente IS TABLE OF int_solic_conso_cabec.ticl_oid_tipo_clie%TYPE;
    TYPE t_oidtipocons IS TABLE OF int_solic_conso_cabec.tspa_oid_tipo_soli_pais_cons%TYPE;
    TYPE t_oidclassoli IS TABLE OF int_solic_conso_cabec.clso_oid_clas_soli%TYPE;
    TYPE t_oidformapago IS TABLE OF int_solic_conso_cabec.fopa_oid_form_pago%TYPE;
    TYPE t_indordencomp IS TABLE OF int_solic_conso_cabec.ind_oc%TYPE;
    TYPE t_indpermunio IS TABLE OF int_solic_conso_cabec.ind_perm_unio_sol%TYPE;
    TYPE t_indpediprue IS TABLE OF int_solic_conso_cabec.ind_pedi_prue%TYPE;
    TYPE t_oidperi IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;
    TYPE t_proc IS TABLE OF int_solic_conso_cabec.proc_oid_proc%TYPE;
    TYPE t_oidsbac IS TABLE OF int_solic_conso_cabec.sbac_oid_sbac%TYPE;
    TYPE t_oidsoci IS TABLE OF int_solic_conso_cabec.soci_oid_soci%TYPE;
    TYPE t_valglos IS TABLE OF int_solic_conso_cabec.val_glos_obse%TYPE;
    TYPE t_oidacti IS TABLE OF int_solic_conso_cabec.cact_oid_acti%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    TYPE t_oidpais IS TABLE OF seg_pais.oid_pais%TYPE;
    TYPE t_tipdocu IS TABLE OF int_solic_conso_cabec.tido_oid_tipo_docu%TYPE;

    TYPE t_oidtipoposi IS TABLE OF ped_tipo_solic_proce.tpos_oid_tipo_posi%TYPE;
    TYPE t_oidsubtipoposi IS TABLE OF ped_tipo_solic_proce.stpo_oid_subt_posi%TYPE;

    v_codpais         t_codpais;
    v_codperi         t_codperi;
    v_codclie         t_codclie;
    v_numlote         t_numlote;
    v_oidtiposolipais t_oidtiposolipais;
    v_oidmoneda       t_oidmoneda;
    v_oidalma         t_oidalma;
    v_oidtipocliente  t_oidtipocliente;
    v_oidtipocons     t_oidtipocons;
    v_oidclassoli     t_oidclassoli;
    v_oidformapago    t_oidformapago;
    v_indordencomp    t_indordencomp;
    v_indpermunio     t_indpermunio;
    v_indpediprue     t_indpediprue;
    v_oidperi         t_oidperi;
    v_proc            t_proc;
    v_oidsbac         t_oidsbac;
    v_oidsoci         t_oidsoci;
    v_valglos         t_valglos;
    v_oidacti         t_oidacti;

    v_sec_nume_docu t_sec_nume_docu;
    v_oidpais       t_oidpais;
    v_tipdocu       t_tipdocu;

    v_oidtipoposi    t_oidtipoposi;
    v_oidsubtipoposi t_oidsubtipoposi;

    rows         NATURAL := 1000; -- Numero de filas a procesar cada vez
    i            BINARY_INTEGER := 0;
    j            BINARY_INTEGER := 0;
    k            BINARY_INTEGER := 0;
    j            BINARY_INTEGER := 0;
    lsactuadetal sto_tipo_docum_digit.cod_tipo_docu%TYPE;

  BEGIN

    lsactuadetal := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_ACTUA_DETAL_VALID_TS'),
                        'N');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_tiposolicitud;
    LOOP
      FETCH c_tiposolicitud BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_oidtiposolipais,
             v_oidmoneda,
             v_oidalma,
             v_oidtipocliente,
             v_oidtipocons,
             v_oidclassoli,
             v_oidformapago,
             v_indordencomp,
             v_indpermunio,
             v_indpediprue,
             v_oidperi,
             v_proc,
             v_oidsbac,
             v_oidsoci,
             v_valglos,
             v_oidacti,
             v_oidpais,
             v_tipdocu,
             v_oidtipoposi,
             v_oidsubtipoposi LIMIT rows;

      IF v_codpais.count > 0 THEN

        -- Actualizamos CAMPOS ADICIONALES
        FORALL i IN 1 .. v_codpais.count
          UPDATE int_solic_conso_cabec a
             SET a.tspa_oid_tipo_soli_pais      = v_oidtiposolipais(i),
                 a.mone_oid_mone                = v_oidmoneda(i),
                 a.almc_oid_alma                = v_oidalma(i),
                 a.ticl_oid_tipo_clie           = v_oidtipocliente(i),
                 a.tspa_oid_tipo_soli_pais_cons = v_oidtipocons(i),
                 a.clso_oid_clas_soli           = v_oidclassoli(i),
                 a.fopa_oid_form_pago           = v_oidformapago(i),
                 a.ind_oc                       = v_indordencomp(i),
                 a.ind_perm_unio_sol            = v_indpermunio(i),
                 a.ind_pedi_prue                = v_indpediprue(i),
                 a.perd_oid_peri                = v_oidperi(i),
                 a.proc_oid_proc                = v_proc(i),
                 a.sbac_oid_sbac                = v_oidsbac(i),
                 a.soci_oid_soci                = v_oidsoci(i),
                 a.val_glos_obse                = v_valglos(i),
                 a.cact_oid_acti                = v_oidacti(i),
                 a.pais_oid_pais                = v_oidpais(i),
                 a.tido_oid_tipo_docu           = v_tipdocu(i),
                 a.val_tota_paga_prom           = NULL
           WHERE sec_nume_docu = v_sec_nume_docu(i)
             AND num_lote = v_numlote(i);

        IF lsactuadetal = 'S' THEN
          FORALL k IN 1 .. v_codpais.count
            UPDATE int_solic_conso_detal
               SET tpos_oid_tipo_posi      = v_oidtipoposi(k),
                   stpo_oid_subt_posi      = v_oidsubtipoposi(k),
                   prod_oid_prod           = NULL,
                   fopa_oid_form_pago      = NULL,
                   ofde_oid_deta_ofer      = NULL,
                   panp_oid_para_nive_prem = NULL,
                   num_prem                = NULL,
                   copa_oid_para_gral      = NULL,
                   ind_no_impr             = NULL
             WHERE cod_pais = v_codpais(k)
               AND cod_peri = v_codperi(k)
               AND cod_clie = v_codclie(k)
               AND num_lote = v_numlote(k);
        END IF;

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_tiposolicitud%NOTFOUND;
    END LOOP;
    CLOSE c_tiposolicitud;

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
                              'ERROR STO_PR_OCC_TIPO_SOLIC: ' || ls_sqlerrm);

  END sto_pr_occ_tipo_solic;

  /***************************************************************************
  Descripcion       : Validacion de direcciones del cliente para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/

  PROCEDURE sto_pr_occ_direc_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_validacion IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cli.cod_clie, --  actualizar en la posicion 64,65,66,67
             cli.oid_clie, --Oid de Cliente
             mcd.oid_clie_dire, --  actualizar en la posicion 68
             (SELECT mci.tdoc_oid_tipo_docu
                FROM mae_clien_ident mci
               WHERE mci.clie_oid_clie = cli.oid_clie
                 AND mci.val_iden_docu_prin = 1
                 AND rownum = 1) tipo_doc, --  actualizar en la posicion 69
             cts.ticl_oid_tipo_clie, --  actualizar en la posicion 61
             cts.sbti_oid_subt_clie, --  actualizar en la posicion 97
             CASE
               WHEN (substr(mcd.cod_unid_geog, 19, 6) IS NULL) THEN
                (SELECT veg.oid_valo_estr_geop
                   FROM zon_valor_estru_geopo veg
                  WHERE veg.orde_1 = substr(mcd.cod_unid_geog, 0, 6)
                    AND veg.orde_2 = substr(mcd.cod_unid_geog, 7, 6)
                    AND veg.orde_3 = substr(mcd.cod_unid_geog, 13, 6)
                    AND veg.orde_4 IS NULL)
               ELSE
                (SELECT veg.oid_valo_estr_geop
                   FROM zon_valor_estru_geopo veg
                  WHERE veg.orde_1 = substr(mcd.cod_unid_geog, 0, 6)
                    AND veg.orde_2 = substr(mcd.cod_unid_geog, 7, 6)
                    AND veg.orde_3 = substr(mcd.cod_unid_geog, 13, 6)
                    AND veg.orde_4 = substr(mcd.cod_unid_geog, 19, 6))
             END vepo_oid_valo_estr_geop, --  actualizar en la posicion 93
             (SELECT esta_oid_esta_clie
                FROM mae_clien_datos_adici
               WHERE clie_oid_clie = cli.oid_clie
                 AND rownum = 1) estatus, --  actualizar en la posicion 109
             mcd. terr_oid_terr oidterr
        FROM seg_pais              pais,
             mae_clien             cli,
             mae_clien_direc       mcd,
             mae_clien_tipo_subti  cts,
             int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cts.clie_oid_clie = cli.oid_clie
         AND pais.oid_pais = cli.pais_oid_pais
         AND mcd.clie_oid_clie = cli.oid_clie
         AND mcd.ind_elim = 0
         AND mcd.ind_dire_ppal = 1
         AND cli.cod_clie = cons.cod_clie
         AND cts. ticl_oid_tipo_clie = 2
         AND mcd.terr_oid_terr IS NOT NULL;

    TYPE t_codpais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_codigoclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_oidclie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_oidcliedire IS TABLE OF int_solic_conso_cabec.cldi_oid_clie_dire%TYPE;

    TYPE t_tipodocumento IS TABLE OF int_solic_conso_cabec.tdoc_oid_tipo_docu%TYPE;
    TYPE t_oidtipoclie IS TABLE OF int_solic_conso_cabec.ticl_oid_tipo_clie%TYPE;
    TYPE t_oidsubtclien IS TABLE OF int_solic_conso_cabec.sbti_oid_subt_clie%TYPE;
    TYPE t_oidvaloestrgeop IS TABLE OF int_solic_conso_cabec.vepo_oid_valo_estr_geop%TYPE;
    TYPE t_estatus IS TABLE OF int_solic_conso_cabec.esta_oid_esta_clie%TYPE;

    TYPE t_oidterr IS TABLE OF int_solic_conso_cabec.terr_oid_terr%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_codpais     t_codpais;
    v_codperi     t_codperi;
    v_codclie     t_codclie;
    v_numlote     t_numlote;
    v_codigoclie  t_codigoclie;
    v_oidclie     t_oidclie;
    v_oidcliedire t_oidcliedire;

    v_tipodocumento   t_tipodocumento;
    v_oidtipoclie     t_oidtipoclie;
    v_oidsubtclien    t_oidsubtclien;
    v_oidvaloestrgeop t_oidvaloestrgeop;
    v_estatus         t_estatus;

    v_oidterr       t_oidterr;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;
    j       BINARY_INTEGER := 0;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_codigoclie,
             v_oidclie,
             v_oidcliedire,
             v_tipodocumento,
             v_oidtipoclie,
             v_oidsubtclien,
             v_oidvaloestrgeop,
             v_estatus,
             v_oidterr LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        FORALL i IN 1 .. v_codpais.count
          UPDATE int_solic_conso_cabec
             SET clie_oid_clie           = v_oidclie(i),
                 clie_oid_clie_rece_fact = v_oidclie(i),
                 clie_oid_clie_paga      = v_oidclie(i),
                 clie_oid_clie_dest      = v_oidclie(i),
                 cldi_oid_clie_dire      = v_oidcliedire(i),
                 tdoc_oid_tipo_docu      = v_tipodocumento(i),
                 ticl_oid_tipo_clie      = v_oidtipoclie(i),
                 sbti_oid_subt_clie      = v_oidsubtclien(i),
                 vepo_oid_valo_estr_geop = v_oidvaloestrgeop(i),
                 esta_oid_esta_clie      = v_estatus(i),
                 terr_oid_terr           = v_oidterr(i),
                 tip_orde                = decode(nvl(v_estatus(i), 0),
                                                  1,
                                                  'P',
                                                  'N')
           WHERE sec_nume_docu = v_sec_nume_docu(i)
             AND num_lote = v_numlote(i);

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_validacion%NOTFOUND;

    END LOOP;
    CLOSE c_validacion;

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
                              'ERROR STO_PR_OCC_DIREC_CLIEN: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_direc_clien;

  /***************************************************************************
    Descripcion       : Validacion de unidades administrativas sin error
                      para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
   ***************************************************************************/
  PROCEDURE sto_pr_occ_unida_admin
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_unidadadminist IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             ter.oid_terr,
             zon.oid_zona,
             zta.oid_terr_admi,
             ter.cod_terr,
             zon.cod_zona,
             zon.des_zona,
             reg.cod_regi,
             reg.des_regi
        FROM mae_clien_unida_admin cua,
             seg_perio_corpo       a,
             cra_perio             b,
             zon_sub_geren_venta   sgv,
             zon_regio             reg,
             zon_zona              zon,
             zon_secci             sec,
             zon_terri_admin       zta,
             zon_terri             ter,
             int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cua.clie_oid_clie = cons.clie_oid_clie
         AND sgv.oid_subg_vent = reg.zsgv_oid_subg_vent
         AND reg.oid_regi = zon.zorg_oid_regi
         AND zon.oid_zona = sec.zzon_oid_zona
         AND sec.oid_secc = zta.zscc_oid_secc
         AND ter.oid_terr = zta.terr_oid_terr
            --AND cua.ind_acti = 1
         AND cua.perd_oid_peri_fin IS NULL
         AND b.oid_peri = cua.perd_oid_peri_ini
         AND a.oid_peri = b.peri_oid_peri
         AND a.cod_peri <= cons.cod_peri
         AND cua.ztad_oid_terr_admi = zta.oid_terr_admi
         AND zon.ind_acti = 1
         and zon.ind_fact <> 0
         AND ter.ind_borr = 0
         AND zon.eszo_oid_esta_zona IS NULL
         AND zta. terr_oid_terr = cons.terr_oid_terr;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_oidterr IS TABLE OF int_solic_conso_cabec.terr_oid_terr%TYPE;
    TYPE t_oidzona IS TABLE OF int_solic_conso_cabec.zzon_oid_zona%TYPE;
    TYPE t_oidterradm IS TABLE OF int_solic_conso_cabec.ztad_oid_terr_admi%TYPE;
    TYPE t_codterr IS TABLE OF zon_terri.cod_terr%TYPE;
    TYPE t_codzona IS TABLE OF zon_zona.cod_zona%TYPE;
    TYPE t_deszona IS TABLE OF zon_zona.des_zona%TYPE;
    TYPE t_codregi IS TABLE OF zon_regio.cod_regi%TYPE;
    TYPE t_desregi IS TABLE OF zon_regio.des_regi%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_oidterr       t_oidterr;
    v_oidzona       t_oidzona;
    v_oidterradm    t_oidterradm;
    v_codterr       t_codterr;
    v_codzona       t_codzona;
    v_deszona       t_deszona;
    v_codregi       t_codregi;
    v_desregi       t_desregi;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;
    j       BINARY_INTEGER := 0;

    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;

    vs_cod_peri          bas_ctrl_fact.cod_peri%TYPE;
    lsindicadorejecucion sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodoc);

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    -- Store NVS_PR_REGIS_PROGR_DUPLA -- FFVV
    lsindicadorejecucion := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                 'STO_REGI_PROG_DUPL');

    IF lsindicadorejecucion = 'S' THEN

      -- Incluir la campanha de proceso
      SELECT cod_peri
        INTO vs_cod_peri
        FROM bas_ctrl_fact a
       WHERE a.cod_pais = pscodigopais
         AND a.sta_camp = '0'
         AND ind_camp_act = '1';

      nvs_pkg_progr_nueva.nvs_pr_regis_progr_dupla(vs_cod_peri, psusuario);

    END IF;
    --

    OPEN c_unidadadminist;
    LOOP

      FETCH c_unidadadminist BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_oidterr,
             v_oidzona,
             v_oidterradm,
             v_codterr,
             v_codzona,
             v_deszona,
             v_codregi,
             v_desregi LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE int_solic_conso_cabec
             SET terr_oid_terr      = v_oidterr(i),
                 zzon_oid_zona      = v_oidzona(i),
                 ztad_oid_terr_admi = v_oidterradm(i),
                 cod_terr           = v_codterr(i),
                 cod_zona           = v_codzona(i),
                 des_zona           = v_deszona(i),
                 cod_regi           = v_codregi(i),
                 des_regi           = v_desregi(i),
                 fec_modi           = SYSDATE
           WHERE sec_nume_docu = v_sec_nume_docu(i)
             AND num_lote = v_numlote(i);

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE,
                 cod_zona               = v_codzona(j),
                 cod_regi               = v_codregi(j)
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET cod_zona = v_codzona(j),
                 cod_regi = v_codregi(j)
           WHERE occ.cod_tipo_docu = lscodigodocumentodetalle
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu_cabe = v_sec_nume_docu(j);

      END IF;

      EXIT WHEN c_unidadadminist%NOTFOUND;

    END LOOP;
    CLOSE c_unidadadminist;

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
                              'ERROR STO_PR_OCC_UNIDA_ADMIN: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_unida_admin;

  /***************************************************************************
  Descripcion       : Validacion de tipo de despacho sin error
                      para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_tipo_despa
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_tipodespacho IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.fec_soli, -- posicion 4
             cons.fec_prog_fact, -- posicion 47
             cons.esta_oid_esta_clie, -- posicion 109
             cons.ind_fac_refac, -- posicion 110
             td.ind_cron,
             (SELECT fec_inic
                FROM cra_crono
               WHERE perd_oid_peri = cons.perd_oid_peri
                 AND zzon_oid_zona = cons.zzon_oid_zona
                 AND cact_oid_acti =
                     (SELECT oid_acti
                        FROM cra_activ
                       WHERE cod_acti = 'FA'
                         AND pais_oid_pais = cons.pais_oid_pais)) fec_fact,
             (SELECT fec_inic
                FROM cra_crono
               WHERE perd_oid_peri = cons.perd_oid_peri
                 AND zzon_oid_zona = cons.zzon_oid_zona
                 AND cact_oid_acti =
                     (SELECT oid_acti
                        FROM cra_activ
                       WHERE cod_acti = 'RF'
                         AND pais_oid_pais = cons.pais_oid_pais)) fec_refac,
             oid_tipo_desp
        FROM ped_tipo_despa        td,
             int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND td.cod_tipo_desp = cons.tip_desp;

    TYPE t_codpais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_cabec.fec_soli%TYPE;
    TYPE t_fecprogfact IS TABLE OF int_solic_conso_cabec.fec_prog_fact%TYPE;
    TYPE t_oidestat IS TABLE OF int_solic_conso_cabec.esta_oid_esta_clie%TYPE;
    TYPE t_indfactrefact IS TABLE OF int_solic_conso_cabec.ind_fac_refac%TYPE;
    TYPE t_indcron IS TABLE OF ped_tipo_despa.ind_cron%TYPE;
    TYPE t_fecfact IS TABLE OF cra_crono.fec_inic%TYPE;
    TYPE t_fecrefact IS TABLE OF cra_crono.fec_inic%TYPE;
    TYPE t_tipdesp IS TABLE OF ped_tipo_despa.oid_tipo_desp%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_fecsoli       t_fec_soli;
    v_fecprogfact   t_fecprogfact;
    v_oidestat      t_oidestat;
    v_indfactrefact t_indfactrefact;
    v_indcron       t_indcron;
    v_fecfact       t_fecfact;
    v_fecrefact     t_fecrefact;

    v_sec_nume_docu t_sec_nume_docu;
    v_tipdesp       t_tipdesp;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;

    vaux               VARCHAR2(2);
    ldfechafacturacion bas_ctrl_fact.fec_proc%TYPE;
    lbinderror         BOOLEAN := TRUE;

    lnoidperiodo NUMBER(10);

    lsexcep NUMBER;

    lsnumpedprom  VARCHAR2(5);


    lsfechaDA sto_param_gener_occrr.val_param%TYPE;

 

  BEGIN


    lsfechaDA := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                 'STO_FECHA_DA'),'N');


    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    SELECT a.fec_proc
      INTO ldfechafacturacion
      FROM bas_ctrl_fact a
     WHERE a.ind_camp_act = '1'
       AND a.sta_camp = '0';


    begin
    select val_para
    into lsnumpedprom
    from bas_param_pais x
    where x.cod_pais=pscodigopais
    and x.cod_sist='IMP'
    and x.cod_para='010';
    exception when others then
            lsnumpedprom:='5';
    end;

    OPEN c_tipodespacho;
    LOOP
      FETCH c_tipodespacho BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_fecsoli,
             v_fecprogfact,
             v_oidestat,
             v_indfactrefact,
             v_indcron,
             v_fecfact,
             v_fecrefact,
             v_tipdesp LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FOR i IN v_codpais.first .. v_codpais.last
        LOOP
          lbinderror := TRUE;
          lsexcep    := 0;
          SELECT COUNT(1)
            INTO lsexcep
            FROM sto_clien_excep_fecha
           WHERE cod_clie = v_codclie(i)
             AND cod_peri = v_codperi(i);


          if lsfechaDA='S' then
          
              begin
              SELECT a.fec_proc
                INTO ldfechafacturacion
                FROM bas_ctrl_fact a
               WHERE a.cod_peri=v_codperi(i);
               exception when others then
                         NULL;
               end;
          
          end if;

         lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(v_codperi(i));

          IF v_indcron(i) = 1 THEN
            IF ((ldfechafacturacion > v_fecrefact(i) OR
               ldfechafacturacion < v_fecfact(i)) AND lsexcep = 0) THEN
              lbinderror := FALSE;
              --vaux := '';
            END IF;
            /*IF v_oidestat(i) = 1 AND v_fecsoli(i) != v_fecfact(i) THEN
              vaux := '';
            ELSE
              UPDATE int_solic_conso_cabec
                 SET fec_prog_fact      = v_fecsoli(i),
                     tids_oid_tipo_desp = v_tipdesp(i)
               WHERE cod_pais = v_codpais(i)
                     AND cod_peri = v_codperi(i)
                     AND cod_clie = v_codclie(i)
                     AND num_lote = v_numlote(i);
            END IF;*/
            IF ldfechafacturacion = v_fecfact(i) THEN
              vaux := 'FA';
            ELSE
              vaux := 'FR';
            END IF;
            UPDATE int_solic_conso_cabec
               SET ind_fac_refac      = vaux,
                   tids_oid_tipo_desp = v_tipdesp(i),
                   fec_prog_fact      = ldfechafacturacion
             WHERE sec_nume_docu = v_sec_nume_docu(i)
               AND num_lote = v_numlote(i);
            --END IF;
          ELSE

            UPDATE int_solic_conso_cabec
               SET fec_prog_fact      = ldfechafacturacion,
                   ind_fac_refac      = 'FA',
                   tids_oid_tipo_desp = v_tipdesp(i)
             WHERE num_lote = v_numlote(i)
               AND sec_nume_docu = v_sec_nume_docu(i);

          END IF;

          update int_solic_conso_cabec
          set val_prom_pedi=nvl(val_prom_pedi,HIP_PKG_CONSU.HIP_FN_OBTIE_PROME_VENTA_PEDID('','','','',v_codclie(i), lsnumpedprom))
          where sec_nume_docu=v_sec_nume_docu(i)
          ;

          IF lbinderror THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(i)
               AND occ.sec_nume_docu = v_sec_nume_docu(i);

          END IF;

        END LOOP;

      END IF;

      EXIT WHEN c_tipodespacho%NOTFOUND;

    END LOOP;
    CLOSE c_tipodespacho;

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
                              'ERROR STO_PR_OCC_TIPO_DESPA: ' || ls_sqlerrm);

  END sto_pr_occ_tipo_despa;

  /***************************************************************************
  Descripcion       : Validacion de tipo de cambio sin error
              para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_tipo_cambi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_tipocambio IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             mtc.val_tipo_camb
        FROM pre_matri_factu_cabec mtc,
             int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND mtc.perd_oid_peri IN (cons.perd_oid_peri);

    TYPE t_codpais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_valtipocambio IS TABLE OF int_solic_conso_cabec.val_tipo_camb%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_codpais       t_codpais;
    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_valtipocambio t_valtipocambio;

    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;
    j       BINARY_INTEGER := 0;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_tipocambio;
    LOOP
      FETCH c_tipocambio BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_valtipocambio LIMIT w_filas;

      IF v_codpais.count > 0 THEN
        FORALL i IN 1 .. v_codpais.count
          UPDATE int_solic_conso_cabec
             SET val_tipo_camb = v_valtipocambio(i)
           WHERE sec_nume_docu = v_sec_nume_docu(i)
             AND num_lote = v_numlote(i);

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_tipocambio%NOTFOUND;

    END LOOP;
    CLOSE c_tipocambio;

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
                              'ERROR STO_PR_OCC_TIPO_CAMBI: ' || ls_sqlerrm);

  END sto_pr_occ_tipo_cambi;

  /***************************************************************************
  Descripcion       : Validacion de cliente activo sin error
              para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_clien_activ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_clienteactivo IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM mae_clien_datos_adici adi,
             int_solic_conso_cabec cons,
             sto_proce_docum_digit occ,
             mae_clien             mae
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND adi.clie_oid_clie = mae.oid_clie
         AND mae.cod_clie = cons.cod_clie
         AND adi.ind_acti = 1;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_clienteactivo;
    LOOP
      FETCH c_clienteactivo BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET -- Actualziamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_clienteactivo%NOTFOUND;
    END LOOP;
    CLOSE c_clienteactivo;

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
                              'ERROR STO_PR_OCC_CLIEN_ACTIV: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_clien_activ;

  /***************************************************************************
  Descripcion       : Validacion de segundo pedido sin error
              para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_segun_pedid
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_segundopedido IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.clie_oid_clie,
             cons.perd_oid_peri,
             cons.ind_error_sgpe,
             cons.cod_peri
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_num_lote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_perd_oid_peri IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;
    TYPE t_ind_error_sgpe IS TABLE OF int_solic_conso_cabec.ind_error_sgpe%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;

    v_num_lote       t_num_lote;
    v_sec_nume_docu  t_sec_nume_docu;
    v_clie_oid_clie  t_clie_oid_clie;
    v_perd_oid_peri  t_perd_oid_peri;
    v_ind_error_sgpe t_ind_error_sgpe;
    v_cod_peri       t_cod_peri;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    lnnumregistros       NUMBER(10);
    lnok                 NUMBER(1) := 1;
    lnmontoped           NUMBER(12, 2);
    lnpromventa          NUMBER(12, 2);
    lnvaliprom           VARCHAR2(1);
    lnvalimtmi           VARCHAR2(1);
    lnperiodoinicio      VARCHAR2(6);
    lnperiodofin         VARCHAR2(6);
    lberrorsegundopedido BOOLEAN;
    lnindprom            VARCHAR2(1);
    lnindmtmi            VARCHAR2(1);

    lnmontopromedio NUMBER(12, 2);

    lstclasfad VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                    'STO_TCLAS_FAD');

    lsclasfad VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                   'STO_CLAS_FAD');

    lsmtomin VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                  'STO_FAD_MTOMIN');

    lsperiodosreq VARCHAR2(2) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                      'STO_FAD_PERIODOS');

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_segundopedido;
    LOOP
      FETCH c_segundopedido BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_clie_oid_clie,
             v_perd_oid_peri,
             v_ind_error_sgpe,
             v_cod_peri LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          lberrorsegundopedido := TRUE;

          IF v_ind_error_sgpe(i) = '0' THEN
            lberrorsegundopedido := FALSE;
          ELSIF v_ind_error_sgpe(i) = '1' THEN

            lnnumregistros := sto_fn_valid_fad(v_perd_oid_peri(i),
                                               v_clie_oid_clie(i));

            IF lnnumregistros > 0 THEN

              lnok := 1;

              BEGIN

                SELECT a.ind_vali_prom,
                       a.ind_vali_mtmi
                  INTO lnvaliprom,
                       lnvalimtmi
                  FROM sto_factu_adici_clien a
                 WHERE a.oid_clie = v_clie_oid_clie(i)
                 GROUP BY a.ind_vali_prom,
                          a.ind_vali_mtmi;

              EXCEPTION
                WHEN OTHERS THEN
                  lnok := 0;
              END;

              IF lnok = 1 THEN

                lnindprom   := '1';
                lnpromventa := 0;

                IF lnvaliprom = '1' THEN
                  IF lsperiodosreq IS NOT NULL THEN
                    -- dato requerido para validar promedio de venta
                    -- valida que haya pasado el primer pedido en la campa?a actual
                    IF gen_pkg_gener.gen_fn_clien_perio_ultim_pedid(v_clie_oid_clie(i)) =
                       v_cod_peri(i) THEN

                      lnperiodoinicio := fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_calcu_perio_nante(v_cod_peri(i),
                                                                                                                     lsperiodosreq));
                      lnperiodofin    := fin_pkg_gener.fin_fn_obtie_oid_perio(fin_pkg_gener.fin_fn_calcu_perio_nante(v_cod_peri(i),
                                                                                                                     1));

                      lnpromventa := sto_fn_devue_prom_venta(v_clie_oid_clie(i),
                                                             lnperiodoinicio,
                                                             lnperiodofin);

                      IF gen_pkg_gener.gen_fn_clien_monto_ultim_pedid(v_clie_oid_clie(i)) >=
                         lnpromventa THEN
                        -- cumple condicion que el primer pedido sea >= al promedio de venta.
                        lnindprom := '0';
                      END IF;
                    END IF;
                  END IF;
                ELSE
                  lnindprom := '0';
                END IF;
                ---
                IF lnindprom = '1' THEN
                  -- adiciona mensajes de sto
                  sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(i),
                                                       v_num_lote(i),
                                                       'Primer pedido de la campaña no cumple promedio de venta $.' ||
                                                       lnpromventa);
                END IF;
                ---
                -- valida monto minimo FAD
                lnindmtmi  := '1';
                lnmontoped := 0;

                IF lnvalimtmi = '1' THEN
                  IF lsmtomin IS NOT NULL THEN
                    -- parametro que indica cual es el monto a validar
                    -- obtiene el monto del pedido
                    SELECT nvl(SUM(det.val_unid_dem *
                                   det.val_prec_cata_unit_loca *
                                   det.val_fact_repe),
                               0)
                      INTO lnmontoped
                      FROM int_solic_conso_detal det,
                           int_solic_conso_cabec cab
                     WHERE cab.sec_nume_docu = v_sec_nume_docu(i)
                       AND cab.sec_nume_docu = det.sec_nume_docu_cabe;

                    IF lnmontoped >= lsmtomin THEN
                      -- cumple condicion de monto minimo FAD
                      lnindmtmi := '0';
                    END IF;
                  END IF;
                ELSE
                  lnindmtmi := '0';
                END IF;
                ---
                IF lnindmtmi = '1' THEN
                  -- adiciona mensajes de sto
                  sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(i),
                                                       v_num_lote(i),
                                                       'El monto del segundo pedido $.' ||
                                                       lnmontoped ||
                                                       ' es menor al monto minimo FAD $.' ||
                                                       lsmtomin);
                END IF;
                -- valida que cumpla ambos casos
                IF lnindprom = '0' AND lnindmtmi = '0' THEN
                  lberrorsegundopedido := FALSE;
                END IF;

              ELSE

                lberrorsegundopedido := FALSE;

              END IF;

            END IF;

            IF lnnumregistros > 0 AND lstclasfad IS NOT NULL AND
               lsclasfad IS NOT NULL THEN

              DELETE FROM mae_clien_clasi x
               WHERE x.ctsu_oid_clie_tipo_subt IN
                     (SELECT oid_clie_tipo_subt
                        FROM mae_clien_tipo_subti y
                       WHERE y.clie_oid_clie = v_clie_oid_clie(i)
                         AND y.ticl_oid_tipo_clie = 2)
                 AND x.tccl_oid_tipo_clasi = lstclasfad
                 AND x.clas_oid_clas = lsclasfad;

              INSERT INTO mae_clien_clasi(
                    OID_CLIE_CLAS,
                    CTSU_OID_CLIE_TIPO_SUBT,
                    CLAS_OID_CLAS,
                    PERD_OID_PERI,
                    TCCL_OID_TIPO_CLASI,
                    FEC_CLAS,
                    IND_PPAL,
                    FEC_ULTI_ACTU,
                    USU_MODI,
                    FEC_CREA,
                    USU_CREA)
                SELECT mae_clcl_seq.nextval,
                       x.oid_clie_tipo_subt,
                       lsclasfad,
                       v_perd_oid_peri(i),
                       lstclasfad,
                       SYSDATE,
                       '0',
                       SYSDATE,
                       'STO',
                       SYSDATE,
                       'STO'
                  FROM mae_clien_tipo_subti x
                 WHERE x.clie_oid_clie = v_clie_oid_clie(i)
                   AND x.ticl_oid_tipo_clie = 2;
            END IF;
          END IF;

          IF NOT lberrorsegundopedido THEN
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
      EXIT WHEN c_segundopedido%NOTFOUND;
    END LOOP;
    CLOSE c_segundopedido;

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
                              'ERROR STO_PR_OCC_SEGUN_PEDID: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_segun_pedid;

  /***************************************************************************
  Descripcion       : Validacion de bloqueo de cliente sin error
              para Orden de compra Cabecera
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_bloqu_clien
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_bloqueocliente IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.clie_oid_clie,
             cons.ind_fac_refac
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    CURSOR c_motivosbloqueo
    (
      ln_oidclie     NUMBER,
      ln_indfacrefac VARCHAR2
    ) IS
      SELECT a.tibq_oid_tipo_bloq,
             bloq_desc.val_i18n
        FROM mae_clien_bloqu a,
             mae_accio_proce_bloqu b,
             mae_accio_bloqu c,
             mae_proce_bloqu d,
             (SELECT val_oid,
                     val_i18n
                FROM gen_i18n_sicc_comun
               WHERE attr_enti = 'MAE_TIPO_BLOQU') bloq_desc
       WHERE a.fec_desb IS NULL
         AND a.clie_oid_clie = ln_oidclie
         AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
         AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
         AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
         AND d.cod_proc_bloq = ln_indfacrefac
         AND c.cod_acci_bloq = 'FN'
         AND a.tibq_oid_tipo_bloq = bloq_desc.val_oid;

    TYPE motbloqdiarectab IS TABLE OF c_motivosbloqueo%ROWTYPE INDEX BY BINARY_INTEGER;
    motbloqrecord motbloqdiarectab;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_oidclie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_indfacrefac IS TABLE OF int_solic_conso_cabec.ind_fac_refac%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_oidclie       t_oidclie;
    v_indfacrefac   t_indfacrefac;
    w_filas         NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    contador NUMBER := 0;
    numero   NUMBER := 0;

    verifica BOOLEAN;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_bloqueocliente;
    LOOP
      FETCH c_bloqueocliente BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_oidclie,
             v_indfacrefac LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          verifica := TRUE;
          SELECT COUNT(*)
            INTO contador
            FROM mae_clien_bloqu
           WHERE fec_desb IS NULL
             AND clie_oid_clie = v_oidclie(j);

          IF (contador > 0) THEN
            SELECT COUNT(*)
              INTO numero
              FROM mae_clien_bloqu       a,
                   mae_accio_proce_bloqu b,
                   mae_accio_bloqu       c,
                   mae_proce_bloqu       d
             WHERE a.fec_desb IS NULL
               AND a.clie_oid_clie = v_oidclie(j)
               AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
               AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
               AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
               AND d.cod_proc_bloq = v_indfacrefac(j)
               AND c.cod_acci_bloq = 'FS';

            IF (numero <> contador) THEN
              verifica := FALSE;

              OPEN c_motivosbloqueo(v_oidclie(j), v_indfacrefac(j));
              LOOP
                FETCH c_motivosbloqueo BULK COLLECT
                  INTO motbloqrecord LIMIT w_filas;
                IF motbloqrecord.count > 0 THEN
                  FOR i IN motbloqrecord.first .. motbloqrecord.last
                  LOOP
                    -- actualiza int_solic_Conso_cabec con tipo de bloqueo
                    UPDATE int_solic_conso_cabec
                       SET ind_bloq_admi = motbloqrecord(i)
                                           .tibq_oid_tipo_bloq
                     WHERE sec_nume_docu = v_sec_nume_docu(j)
                       AND num_lote = v_numlote(j);
                    -- adiciona mensajes de sto
                    sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(j),
                                                         v_numlote(j),
                                                         'Motivo : ' || motbloqrecord(i)
                                                         .val_i18n);
                  END LOOP;
                END IF;
                EXIT WHEN c_motivosbloqueo%NOTFOUND;
              END LOOP;
              CLOSE c_motivosbloqueo;
              /*
              UPDATE int_solic_conso_cabec
                 SET ind_bloq_admi =
                     (SELECT MAX(tibq_oid_tipo_bloq)
                        FROM mae_clien_bloqu
                       WHERE fec_desb IS NULL
                         AND clie_oid_clie = v_oidclie(j)
                         AND oid_bloq NOT IN (SELECT oid_bloq
                                                FROM mae_clien_bloqu       a,
                                                     mae_accio_proce_bloqu b,
                                                     mae_accio_bloqu       c,
                                                     mae_proce_bloqu       d
                                               WHERE a.fec_desb IS NULL
                                                 AND a.clie_oid_clie = v_oidclie(j)
                                                 AND a.tibq_oid_tipo_bloq = b.tibq_oid_tipo_bloq
                                                 AND b.mabl_oid_acci_bloq = c.oid_acci_bloq
                                                 AND b.mapb_oid_proc_bloq = d.oid_proc_bloq
                                                 AND d.cod_proc_bloq = v_indfacrefac(j)
                                                 AND c.cod_acci_bloq = 'FS'))
               WHERE sec_nume_docu = v_sec_nume_docu(j)
                 AND num_lote = v_numlote(j);*/
            END IF;
          END IF;

          IF (verifica) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_bloqueocliente%NOTFOUND;

    END LOOP;
    CLOSE c_bloqueocliente;

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
                              'ERROR STO_PR_OCC_BLOQU_CLIEN: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_bloqu_clien;

  /**************************************************************************
  Descripcion       : STO_PR_OCC_TIPO_SOLIC_OK
                    Procedimiento de Validacion de Tipo de Solicitud Sin Error
                    segun secuencia de ejecucion
  Fecha Creacion    : 22/02/2008
  Autor             : Marco Silva
  ***************************************************************************/
  PROCEDURE sto_pr_occ_valid_ssicc
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_validacion IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.ind_bloq_admi = '0'
         AND cons.ind_bloq_fina = '0'
         AND ((cons.ind_erro_deud = '0') OR (cons.ind_erro_deud = '1') OR
             (cons.ind_erro_deud = '2' AND cons.ind_admi_cart = '1'))
         AND cons.ind_ocs_proc = '0'
         AND cons.ind_error_sgpe = '0'
         AND cons.ind_ocs_bloq = '0'
         AND cons.ind_erro_rech = '0'
         AND (cons.ind_erro_mtmi = '0' OR
             (cons.ind_erro_mtmi = '0' AND cons.ind_comp_mont = '1'))
         AND cons.ind_erro_mmfc = '0'   
         AND cons.ind_erro_mtma = '0'
         AND cons.ind_erro_remp = '0'
         AND cons.ind_erro_node = '0'
         AND cons.ind_cont_act = '0'
         AND cons.ind_anul = 0
         AND cons.ind_orig_cabe = '1';

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 5000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion;
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_numlote.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

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
                              'ERROR STO_PR_OCC_VALID_SSICC: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_valid_ssicc;

  /***************************************************************************
  Descripcion       : Validacion de indicador no imprimible sin error
                      para Orden de compra Detalle
  Fecha Creacion    : 13/05/2008
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_cande
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  )

   IS
    CURSOR c_validacion
    (
      vscodigodocumentodetalle VARCHAR2,
      vsultimavaliddetalle     VARCHAR2,
      vsparamcodventa          VARCHAR2
    ) IS
      SELECT occ.num_lote,
             occ.sec_nume_docu
        FROM sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND EXISTS
       (SELECT 1
                FROM sto_docum_digit occ2
               WHERE occ.cod_pais = occ2.cod_pais
                 AND occ2.num_proc = psnumeroproceso
                 AND occ.num_lote = occ2.num_lote
                 AND occ.sec_nume_docu = occ2.sec_nume_docu_cabe
                 AND (SELECT cod_vent
                        FROM int_solic_conso_detal
                       WHERE sec_nume_docu = occ2.sec_nume_docu) <=
                     vsparamcodventa
                 AND occ2.cod_tipo_docu = vscodigodocumentodetalle
                 AND occ2.ind_envi = '0' --No enviados
                 AND occ2.ind_rech = '0'
                 AND occ2.cod_ulti_vali_ejec = vsultimavaliddetalle
                 AND occ2.cod_ulti_vali_exit = vsultimavaliddetalle
                 AND occ2.cod_ulti_vali_erro IS NULL);

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    rows                     NATURAL := 5000; -- Numero de filas a procesar cada vez
    i                        BINARY_INTEGER := 0;
    lscodigodocumentodetalle sto_tipo_docum_digit.cod_tipo_docu%TYPE;
    lsultimavaliddetalle     VARCHAR2(10);
    lsparamcodventa          sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    lscodigodocumentodetalle := sto_pkg_gener.sto_fn_devue_codig_docum_detal(pscodigopais,
                                                                             pscodigotipodoc);

    lsultimavaliddetalle := sto_pkg_gener.sto_fn_devue_ultim_valid_ejecu(pscodigopais,
                                                                         lscodigodocumentodetalle,
                                                                         psnumeroproceso);

    lsparamcodventa := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_MAXI_COVE_PROD');
    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_validacion(lscodigodocumentodetalle,
                      lsultimavaliddetalle,
                      lsparamcodventa);
    LOOP
      FETCH c_validacion BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_numlote.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL i IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;
      EXIT WHEN c_validacion%NOTFOUND;
    END LOOP;
    CLOSE c_validacion;

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
                              'ERROR sto_pr_occ_cande: ' || ls_sqlerrm);

  END sto_pr_occ_cande;

  /***************************************************************************
  Descripcion       : Validacion de  de Reemmplazo y anulados
  Fecha Creacion    : 15/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_reemp_anula
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_reempanula IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.ind_ocs_proc = '0'
         AND cons.ind_erro_remp = '0'
         AND cons.ind_anul = '0'
         AND cons.ind_orig_cabe = '1';

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_reempanula;
    LOOP
      FETCH c_reempanula BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_reempanula%NOTFOUND;

    END LOOP;
    CLOSE c_reempanula;

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
                              'ERROR STO_PR_OCC_REEMP_ANULA: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_reemp_anula;

  /***************************************************************************
  Descripcion       : Validacion de  Deuda
  Fecha Creacion    : 15/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_deuda
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.ind_ocs_proc,
             cons.ind_erro_deud,
             cons.ind_admi_cart,
             cons.ind_orig_cabe
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_ind_ocs_proc IS TABLE OF int_solic_conso_cabec.ind_ocs_proc%TYPE;
    TYPE t_ind_erro_deud IS TABLE OF int_solic_conso_cabec.ind_erro_deud%TYPE;
    TYPE t_ind_admi_cart IS TABLE OF int_solic_conso_cabec.ind_admi_cart%TYPE;
    TYPE t_ind_orig_cabe IS TABLE OF int_solic_conso_cabec.ind_orig_cabe%TYPE;

    v_num_lote      t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_ind_ocs_proc  t_ind_ocs_proc;
    v_ind_erro_deud t_ind_erro_deud;
    v_ind_admi_cart t_ind_admi_cart;
    v_ind_orig_cabe t_ind_orig_cabe;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    existe BOOLEAN := TRUE;
    j      BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_ind_ocs_proc,
             v_ind_erro_deud,
             v_ind_admi_cart,
             v_ind_orig_cabe LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          existe := TRUE;

          IF (v_ind_erro_deud(j) = 2 AND v_ind_admi_cart(j) = 0 AND
             v_ind_orig_cabe(j) = '1') THEN
            existe := FALSE;
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
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

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
                              'ERROR STO_PR_OCC_DEUDA: ' || ls_sqlerrm);

  END sto_pr_occ_deuda;

  /***************************************************************************
  Descripcion       : Validacion de  Bloqueo por control
  Fecha Creacion    : 15/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_bloqu_contr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.ind_ocs_proc,
             cons.ind_ocs_bloq,
             cons.ind_orig_cabe
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_ind_ocs_proc IS TABLE OF int_solic_conso_cabec.ind_ocs_proc%TYPE;
    TYPE t_ind_ocs_bloq IS TABLE OF int_solic_conso_cabec.ind_ocs_bloq%TYPE;
    TYPE t_ind_orig_cabe IS TABLE OF int_solic_conso_cabec.ind_orig_cabe%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_ind_ocs_proc  t_ind_ocs_proc;
    v_ind_ocs_bloq  t_ind_ocs_bloq;
    v_ind_orig_cabe t_ind_orig_cabe;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    numero NUMBER := 0;
    j      BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_ind_ocs_proc,
             v_ind_ocs_bloq,
             v_ind_orig_cabe LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          numero := 0;

          IF (v_ind_ocs_proc(j) = '0' AND v_ind_ocs_bloq(j) = '1' AND
             v_ind_orig_cabe(j) = '1') THEN
            numero := 1;
          END IF;

          IF (numero = 0) THEN

            -- Actualziamos Indicadores de Validacion
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

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
                              'ERROR STO_PR_OCC_BLOQU_CONTR: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_bloqu_contr;

  /***************************************************************************
  Descripcion       : Validacion de  Rechazo por OCR
  Fecha Creacion    : 15/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_recha_ocr
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.ind_ocs_proc,
             cons.ind_erro_rech,
             cons.ind_orig_cabe
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_ind_ocs_proc IS TABLE OF int_solic_conso_cabec.ind_ocs_proc%TYPE;
    TYPE t_ind_erro_rech IS TABLE OF int_solic_conso_cabec.ind_erro_rech%TYPE;
    TYPE t_ind_orig_cabe IS TABLE OF int_solic_conso_cabec.ind_orig_cabe%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_ind_ocs_proc  t_ind_ocs_proc;
    v_ind_erro_rech t_ind_erro_rech;
    v_ind_orig_cabe t_ind_orig_cabe;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_ind_ocs_proc,
             v_ind_erro_rech,
             v_ind_orig_cabe LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          existe := TRUE;

          IF (v_ind_ocs_proc(j) = '0' AND
             (v_ind_erro_rech(j) = '1' OR v_ind_orig_cabe(j) = '0')) THEN
            existe := FALSE;
          END IF;

          IF (existe) THEN
            -- Actualiza los Indicadores de Validacion
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

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
                              'ERROR STO_PR_OCC_RECHA_OCR: ' || ls_sqlerrm);

  END sto_pr_occ_recha_ocr;

  /***************************************************************************
  Descripcion       : Validacion de  Monto Minimo
  Fecha Creacion    : 15/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_monto_minim
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_valid IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.ind_erro_mtmi = '0';

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    i BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_valid;
    LOOP
      FETCH c_valid BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;
      EXIT WHEN c_valid%NOTFOUND;

    END LOOP;
    CLOSE c_valid;

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
                              'ERROR STO_PR_OCC_MONTO_MINIM: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_monto_minim;

  /***************************************************************************
  Descripcion       : Validacion de  Monto Maximo
  Fecha Creacion    : 15/12/2008
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_monto_maxim
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_valid IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.ind_erro_mtma = '0';

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    i BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_valid;
    LOOP
      FETCH c_valid BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FORALL i IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(i)
             AND occ.sec_nume_docu = v_sec_nume_docu(i);

      END IF;
      EXIT WHEN c_valid%NOTFOUND;

    END LOOP;
    CLOSE c_valid;

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
                              'ERROR STO_PR_OCC_MONTO_MAXIM: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_monto_maxim;

  /***************************************************************************
  Descripcion       : Validacion que actualiza deuda
  Fecha Creacion    : 20/04/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_actua_deuda
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.fec_soli,
             cons.clie_oid_clie,
             cons.perd_oid_peri
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND (cons.cod_zona IS NULL OR
             cons.cod_zona NOT IN
             (SELECT cod_zona
                 FROM int_ped_zona_ofici
                WHERE est_zoof = 1
                  AND ind_vali_deud = 1));

    CURSOR c_oficina IS
      SELECT cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.fec_soli
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ,
             int_ped_zona_ofici    zoof
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_zona = zoof.cod_zona
         AND zoof.est_zoof = 1
         AND zoof.ind_vali_deud = 1;

    TYPE t_codperi IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    TYPE t_fec_soli IS TABLE OF int_solic_conso_cabec.fec_soli%TYPE;
    TYPE t_oidclie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_oidperi IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;

    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_fec_soli      t_fec_soli;

    v_oidclie t_oidclie;
    v_oidperi t_oidperi;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j                  BINARY_INTEGER := 0;
    lb_no_existe_deuda BOOLEAN := TRUE;
    saldo_deudor       NUMBER := 0;
    saldo_rechazo      NUMBER := 0;
    j                  BINARY_INTEGER := 0;

    lncontador1 NUMBER;
    --lncontador2 NUMBER;

    lv_porc_deud NUMBER(3);
    lv_tipo_bene NUMBER(1);
    lv_usu_cre   VARCHAR2(50);
    lv_obser     VARCHAR2(200);
    lv_monto     NUMBER(8, 2);

    lv_oid_tipo_soli_pais NUMBER(12);
    lv_oid_soli_cabe      NUMBER(12);
    lv_fec_ulti_pedi      DATE;
    lv_imp_ulti_pedi      NUMBER(12, 2);
    lv_imp_abon           NUMBER(12, 2);

    ln_oid_pais NUMBER(12);
    lnmontoped  NUMBER(12, 2);

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    ln_oid_pais := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

    lv_oid_tipo_soli_pais := fin_pkg_gener.fin_fn_obtie_oid_solic_pais('C1');

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_fec_soli,
             v_oidclie,
             v_oidperi LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          lncontador1        := 0;
          lb_no_existe_deuda := FALSE;

          saldo_deudor := gen_pkg_gener.gen_fn_calcu_valor_saldo_deudo(v_codclie(j));

          SELECT b.val_mnt_min_deud
            INTO saldo_rechazo
            FROM bas_ctrl_fact b
           WHERE b.cod_pais = pscodigopais
             AND b.cod_peri = v_codperi(j);

          UPDATE int_solic_conso_cabec r
             SET r.ind_erro_deud = decode(sign(saldo_deudor - saldo_rechazo),
                                          1,
                                          2,
                                          1),
                 r.val_sald_deud = saldo_deudor,
                 r.fec_admi_cart = NULL,
                 r.usu_admi_cart = NULL,
                 r.obs_prub      = NULL
           WHERE num_lote = v_numlote(j)
             AND sec_nume_docu = v_sec_nume_docu(j);

          IF (saldo_deudor - saldo_rechazo > 0) THEN
            --Si despues de ejecutar la validacion se determina que el cliente se debe bloquear por deuda
            SELECT COUNT(1)
              INTO lncontador1
              FROM sto_clien_excep_valid_deuda
             WHERE nvl(oid_peri, v_oidperi(j)) = v_oidperi(j)
               AND oid_clie = v_oidclie(j);

            IF lncontador1 = 0 THEN
              --En caso el query anterior no devuelva registros se hace la siguiente consulta(lncontador1 = 0)
              BEGIN
                -- obtiene si tiene beneficio de deuda
                SELECT val_porc,
                       tip_bene,
                       usu_crea,
                       val_obse,
                       val_mont
                  INTO lv_porc_deud,
                       lv_tipo_bene,
                       lv_usu_cre,
                       lv_obser,
                       lv_monto
                  FROM (SELECT DISTINCT a.val_porc,
                                        a.tip_bene,
                                        a.usu_crea,
                                        a.val_obse,
                                        a.val_mont --, cons.cod_clie
                          FROM int_solic_conso_cabec cons,
                               (SELECT *
                                  FROM sto_docum_digit
                                 WHERE sec_nume_docu = v_sec_nume_docu(j)) occ,
                               mae_clien_unida_admin c,
                               zon_terri_admin d,
                               zon_terri e,
                               zon_secci f,
                               zon_zona g,
                               zon_regio h,
                               mae_clien_tipo_subti i,
                               mae_clien_clasi j,
                               sto_clien_benef_deuda a
                         WHERE occ.sec_nume_docu = cons.sec_nume_docu
                           AND occ.num_lote = cons.num_lote
                           AND occ.cod_tipo_docu = pscodigotipodoc
                           AND c.ztad_oid_terr_admi = d.oid_terr_admi
                           AND d.zscc_oid_secc = f.oid_secc
                           AND d.terr_oid_terr = e.oid_terr
                           AND f.zzon_oid_zona = g.oid_zona
                           AND g.zorg_oid_regi = h.oid_regi
                           AND c.clie_oid_clie = i.clie_oid_clie
                           AND c.ind_acti = 1
                           AND d.ind_borr = 0
                           AND i.oid_clie_tipo_subt =
                               j.ctsu_oid_clie_tipo_subt
                           AND c.clie_oid_clie = cons.clie_oid_clie
                           AND cons.perd_oid_peri =
                               nvl(a.oid_peri, cons.perd_oid_peri) --periodo
                           AND c.clie_oid_clie =
                               nvl(a.clie_oid_clie, c.clie_oid_clie) --cliente
                           AND i.ticl_oid_tipo_clie =
                               nvl(a.oid_tipo_clie, i.ticl_oid_tipo_clie) --tipo
                           AND i.sbti_oid_subt_clie =
                               nvl(a.oid_subt_clie, i.sbti_oid_subt_clie) --subtipo
                           AND j.tccl_oid_tipo_clasi =
                               nvl(a.oid_tipo_clas_clie,
                                   j.tccl_oid_tipo_clasi) --tipo clasif
                           AND j.clas_oid_clas =
                               nvl(a.oid_clas_clie, j.clas_oid_clas) --clasif
                           AND h.oid_regi = nvl(a.oid_regi, h.oid_regi) --region
                           AND g.oid_zona = nvl(a.oid_zona, g.oid_zona) --zon
                           AND a.ind_elim = '0'
                           AND nvl(a.clie_oid_clie, cons.clie_oid_clie) =
                               cons.clie_oid_clie
                         ORDER BY nvl(val_mont, 0) + nvl(val_porc, 0) DESC)
                 WHERE rownum = 1;

                IF lv_tipo_bene = 2 THEN
                  SELECT MAX(psc.oid_soli_cabe)
                    INTO lv_oid_soli_cabe
                    FROM ped_solic_cabec psc
                   WHERE psc.clie_oid_clie = v_oidclie(j)
                     AND psc.tspa_oid_tipo_soli_pais =
                         lv_oid_tipo_soli_pais
                     AND psc.val_tota_paga_loca > 0
                     AND psc.fec_fact IS NOT NULL;

                  SELECT MAX(psc.fec_fact),
                         SUM(psc.val_tota_paga_loca)
                    INTO lv_fec_ulti_pedi,
                         lv_imp_ulti_pedi
                    FROM ped_solic_cabec psc
                   WHERE psc.oid_soli_cabe = lv_oid_soli_cabe
                     AND psc.clie_oid_clie = v_oidclie(j)
                     AND psc.tspa_oid_tipo_soli_pais =
                         lv_oid_tipo_soli_pais
                     AND psc.val_tota_paga_loca > 0
                     AND psc.fec_fact IS NOT NULL;

                  SELECT nvl(SUM(mb.imp_pago), 0)
                    INTO lv_imp_abon
                    FROM ccc_movim_banca mb
                   WHERE mb.pais_oid_pais = ln_oid_pais
                     AND mb.clie_oid_clie = v_oidclie(j)
                     AND mb.fec_pago > lv_fec_ulti_pedi
                     AND mb.cod_iden_proc = 'P';

                  -- Se calcula si el monto de la deuda es mayor al porcentaje de beneficio recuperado
                  saldo_deudor := round(lv_imp_ulti_pedi *
                                        (100 - lv_porc_deud) / 100,
                                        2) - nvl(lv_imp_abon, 0);

                  IF saldo_deudor <= 0 THEN
                    lb_no_existe_deuda := TRUE;
                  ELSE
                    lb_no_existe_deuda := FALSE;
                  END IF;
                ELSE
                  IF saldo_deudor <= lv_monto OR lv_monto = 0 THEN
                    lb_no_existe_deuda := TRUE;
                  ELSE
                    lb_no_existe_deuda := FALSE;
                  END IF;
                END IF;

              EXCEPTION
                WHEN no_data_found THEN
                  lb_no_existe_deuda := FALSE;
              END;

            ELSE
              lb_no_existe_deuda := TRUE;
              lv_usu_cre         := psusuario;
              lv_obser           := 'Excepto de deuda';
            END IF;

            IF lb_no_existe_deuda THEN
              UPDATE int_solic_conso_cabec r
                 SET r.ind_erro_deud = '2',
                     r.ind_admi_cart = '1',
                     --r.val_mont_pedi = NULL,
                     --r.val_sald_deud = 0,
                     r.usu_admi_cart = lv_usu_cre,
                     r.obs_prub      = lv_obser,
                     r.fec_admi_cart = SYSDATE
               WHERE num_lote = v_numlote(j)
                 AND sec_nume_docu = v_sec_nume_docu(j);
            ELSE
              -- si tiene deuda se actualiza el monto del pedido
              SELECT nvl(SUM(det.val_unid_dem * det.val_prec_cata_unit_loca *
                             det.val_fact_repe),
                         0)
                INTO lnmontoped
                FROM int_solic_conso_detal det,
                     int_solic_conso_cabec cab
               WHERE cab.sec_nume_docu = v_sec_nume_docu(j)
                 AND cab.sec_nume_docu = det.sec_nume_docu_cabe;

              UPDATE int_solic_conso_cabec r
                 SET r.val_mont_pedi = CASE
                                         WHEN r.val_mont_pedi = 0 OR
                                              r.val_mont_pedi IS NULL THEN
                                          lnmontoped
                                         ELSE
                                          r.val_mont_pedi
                                       END
               WHERE num_lote = v_numlote(j)
                 AND sec_nume_docu = v_sec_nume_docu(j);
            END IF;

          ELSE
            lb_no_existe_deuda := TRUE;
          END IF;

          IF lb_no_existe_deuda THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;
    OPEN c_oficina;
    LOOP
      FETCH c_oficina BULK COLLECT
        INTO v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_fec_soli LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE int_solic_conso_cabec r
             SET r.ind_erro_deud = 1,
                 r.val_sald_deud = gen_pkg_gener.gen_fn_calcu_valor_saldo_deudo(v_codclie(j))
           WHERE num_lote = v_numlote(j)
             AND sec_nume_docu = v_sec_nume_docu(j);

        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_oficina%NOTFOUND;

    END LOOP;
    CLOSE c_oficina;

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
                              'ERROR STO_PR_OCC_ACTUA_DEUDA: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_actua_deuda;

  /***************************************************************************
  Descripcion       : Validacion para validar que las consultoras no tengan deuda en otra marca
  Fecha Creacion    : 11/02/2010
  Autor             : Jorge Florencio
  ***************************************************************************/
  PROCEDURE sto_pr_occ_deuda_marca
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.fec_soli,
             cons.ind_admi_cart
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_fec_soli IS TABLE OF int_solic_conso_cabec.fec_soli%TYPE;
    TYPE t_ind_admi_cart IS TABLE OF int_solic_conso_cabec.ind_admi_cart%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_fec_soli      t_fec_soli;
    v_ind_admi_cart t_ind_admi_cart;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    existe        BOOLEAN := TRUE;
    saldo_deudor  ccc_deuda_marca.val_sald_deud%TYPE;
    saldo_rechazo bas_ctrl_fact.val_mnt_min_deud%TYPE;
    j             BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_fec_soli,
             v_ind_admi_cart LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        -- Saldo minimo autorizado para la campa?a activa

        SELECT b.val_mnt_min_deud
          INTO saldo_rechazo
          FROM bas_ctrl_fact b
         WHERE b.cod_pais = pscodigopais
           AND b.ind_camp_act = '1'
           AND b.sta_camp = '0';

        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          BEGIN
            -- Obtiene deuda en Esika
            SELECT nvl(m.val_sald_deud, 0)
              INTO saldo_deudor
              FROM ccc_deuda_marca m
             WHERE m.cod_clie = v_codclie(j);
          EXCEPTION
            WHEN no_data_found THEN
              saldo_deudor := 0;
          END;

          -- Siempre actualizamos la deuda con la suma de ambas marcas
          UPDATE int_solic_conso_cabec r
             SET r.val_sald_deud = nvl(gen_pkg_gener.gen_fn_calcu_valor_saldo_deudo(v_codclie(j)),
                                       0) + saldo_deudor
           WHERE num_lote = v_numlote(j)
             AND sec_nume_docu = v_sec_nume_docu(j);

          IF (saldo_deudor - saldo_rechazo > 0) AND
             (v_ind_admi_cart(j) = 0) THEN
            existe := FALSE;

            UPDATE int_solic_conso_cabec r
               SET r.ind_erro_deud = 1
             WHERE num_lote = v_numlote(j)
               AND sec_nume_docu = v_sec_nume_docu(j);

          ELSE
            existe := TRUE;
          END IF;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

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
                              'ERROR STO_PR_OCC_DEUDA_MARCA: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_deuda_marca;

  /***************************************************************************
  Descripcion       : Validacion de la Deuda de las Consultoras Cupon
  Fecha Creacion    : 11/02/2010
  Autor             : Jorge Florencio
  ***************************************************************************/
  PROCEDURE sto_pr_occ_deuda_cupon
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.fec_soli
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND (cons.cod_zona IS NULL OR
             cons.cod_zona NOT IN
             (SELECT cod_zona
                 FROM int_ped_zona_ofici
                WHERE est_zoof = 1
                  AND ind_vali_deud = 1));

    CURSOR c_oficina IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.fec_soli
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ,
             int_ped_zona_ofici    zoof
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_zona = zoof.cod_zona
         AND zoof.est_zoof = 1
         AND zoof.ind_vali_deud = 1;

    TYPE t_codpais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    TYPE t_fec_soli IS TABLE OF int_solic_conso_cabec.fec_soli%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_fec_soli      t_fec_soli;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j             BINARY_INTEGER := 0;
    existe        BOOLEAN := TRUE;
    saldo_deudor  NUMBER := 0;
    saldo_rechazo NUMBER := 0;
    j             BINARY_INTEGER := 0;

    lv_oid_tipo_soli_pais  ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
    lv_oid_pais            seg_pais.oid_pais%TYPE;
    lv_porc_deud_ante_vene ccc_pais_socie_param.val_porc_deud_ante_vene%TYPE;
    lv_oid_clie            mae_clien.oid_clie%TYPE;
    lv_fec_ulti_pedi       DATE;
    lv_imp_ulti_pedi       NUMBER(12, 2);
    lv_imp_abon            NUMBER(12, 2);
    lv_ind_erro_deud       int_solic_conso_cabec.ind_erro_deud%TYPE;
    lv_oid_soli_cabe       ped_solic_cabec.oid_soli_cabe%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lv_oid_tipo_soli_pais := fin_pkg_gener.fin_fn_obtie_oid_solic_pais('C1');
    lv_oid_pais           := gen_pkg_gener.gen_fn_devuelve_id_pais(pscodigopais);

    SELECT psp.val_porc_deud_ante_vene
      INTO lv_porc_deud_ante_vene
      FROM ccc_pais_socie_param psp;

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_fec_soli LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP
          saldo_deudor := 0;
          existe       := FALSE;

          BEGIN

            saldo_deudor := gen_pkg_gener.gen_fn_calcu_valor_saldo_deudo(v_codclie(j));

            SELECT b.val_mnt_min_deud
              INTO saldo_rechazo
              FROM bas_ctrl_fact b
             WHERE b.cod_pais = v_codpais(j)
               AND b.cod_peri = v_codperi(j);

            UPDATE int_solic_conso_cabec r
               SET r.ind_erro_deud = decode(sign(saldo_deudor -
                                                 saldo_rechazo),
                                            1,
                                            2,
                                            1),
                   r.val_sald_deud = saldo_deudor
             WHERE num_lote = v_numlote(j)
               AND sec_nume_docu = v_sec_nume_docu(j);

            IF (saldo_deudor - saldo_rechazo > 0) THEN

              existe := FALSE;

              BEGIN
                SELECT x.oid_clie
                  INTO lv_oid_clie
                  FROM ccc_gener_consu_cupon x
                 WHERE x.cod_clie = v_codclie(j);

                -- Obteniendo los datos del ultimo pedido
                SELECT MAX(psc.oid_soli_cabe)
                  INTO lv_oid_soli_cabe
                  FROM ped_solic_cabec psc
                 WHERE psc.clie_oid_clie = lv_oid_clie
                   AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
                   AND psc.val_tota_paga_loca > 0
                   AND psc.fec_fact IS NOT NULL;

                SELECT MAX(psc.fec_fact),
                       SUM(psc.val_tota_paga_loca)
                  INTO lv_fec_ulti_pedi,
                       lv_imp_ulti_pedi
                  FROM ped_solic_cabec psc
                 WHERE psc.oid_soli_cabe = lv_oid_soli_cabe
                   AND psc.clie_oid_clie = lv_oid_clie
                   AND psc.tspa_oid_tipo_soli_pais = lv_oid_tipo_soli_pais
                   AND psc.val_tota_paga_loca > 0
                   AND psc.fec_fact IS NOT NULL;

                BEGIN
                  SELECT SUM(mb.imp_pago)
                    INTO lv_imp_abon
                    FROM ccc_movim_banca mb
                   WHERE mb.pais_oid_pais = lv_oid_pais
                     AND mb.clie_oid_clie = lv_oid_clie
                     AND mb.fec_pago > lv_fec_ulti_pedi
                     AND mb.cod_iden_proc = 'P';
                EXCEPTION
                  WHEN no_data_found THEN
                    lv_imp_abon := 0;
                END;

                saldo_deudor := round(lv_imp_ulti_pedi *
                                      lv_porc_deud_ante_vene / 100,
                                      2) - nvl(lv_imp_abon, 0);

                IF saldo_deudor <= 0 THEN
                  existe           := TRUE;
                  lv_ind_erro_deud := 1;

                  UPDATE int_solic_conso_cabec r
                     SET r.ind_erro_deud = lv_ind_erro_deud,
                         r.val_sald_deud = 0,
                         r.usu_admi_cart = 'MAKI'
                   WHERE num_lote = v_numlote(j)
                     AND sec_nume_docu = v_sec_nume_docu(j)
                     AND r.cod_clie = v_codclie(j);

                END IF;

              EXCEPTION
                WHEN no_data_found THEN
                  NULL;
              END;

            ELSE
              existe := TRUE;
            END IF;

          END;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;
    OPEN c_oficina;
    LOOP
      FETCH c_oficina BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_fec_soli LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        FORALL j IN 1 .. v_codpais.count
          UPDATE int_solic_conso_cabec r
             SET r.ind_erro_deud = 1,
                 r.val_sald_deud = gen_pkg_gener.gen_fn_calcu_valor_saldo_deudo(v_codclie(j))
           WHERE num_lote = v_numlote(j)
             AND sec_nume_docu = v_sec_nume_docu(j);

        FORALL j IN 1 .. v_codpais.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_oficina%NOTFOUND;

    END LOOP;
    CLOSE c_oficina;

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
                              'ERROR STO_PR_OCC_DEUDA_CUPON: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_deuda_cupon;

  /***************************************************************************
  Descripcion       : Validacion de la Deuda de las Consultoras por Campa?a
  Fecha Creacion    : 07/12/2011
  Autor             : Jorge Florencio
  ***************************************************************************/
  PROCEDURE sto_pr_occ_deuda_campa
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.fec_soli,
             cons.clie_oid_clie,
             cons.perd_oid_peri
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND (cons.cod_zona IS NULL OR
             cons.cod_zona NOT IN
             (SELECT cod_zona
                 FROM int_ped_zona_ofici
                WHERE est_zoof = 1
                  AND ind_vali_deud = 1));

    CURSOR c_oficina IS
      SELECT cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.fec_soli
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ,
             int_ped_zona_ofici    zoof
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_zona = zoof.cod_zona
         AND zoof.est_zoof = 1
         AND zoof.ind_vali_deud = 1;

    TYPE t_codperi IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    TYPE t_fec_soli IS TABLE OF int_solic_conso_cabec.fec_soli%TYPE;
    TYPE t_oidclie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_oidperi IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;

    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;
    v_fec_soli      t_fec_soli;

    v_oidclie t_oidclie;
    v_oidperi t_oidperi;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j                      BINARY_INTEGER := 0;
    lb_no_existe_deuda     BOOLEAN := TRUE;
    saldo_deudor           NUMBER := 0;
    saldo_rechazo          NUMBER := 0;
    abono_pendien          NUMBER := 0;
    lv_sald_maxi_camp_flex NUMBER(12, 2) := 0;
    j                      BINARY_INTEGER := 0;

    lncontador1 NUMBER;
    --lncontador2 NUMBER;

    lv_porc_deud NUMBER(3);
    lv_tipo_bene NUMBER(1);
    lv_usu_cre   VARCHAR2(50);
    lv_obser     VARCHAR2(200);
    lv_monto     NUMBER(8, 2);

    lv_oid_tipo_soli_pais NUMBER(12);
    lv_oid_soli_cabe      NUMBER(12);
    lv_fec_ulti_pedi      DATE;
    lv_imp_ulti_pedi      NUMBER(12, 2);
    lv_imp_abon           NUMBER(12, 2);

    lnmontoped   NUMBER(12, 2);
    lv_exce_flex NUMBER(1) := 0;
    lsactuaflex VARCHAR2(10);
    lsabonpend VARCHAR2(10);



  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    lv_oid_tipo_soli_pais := fin_pkg_gener.fin_fn_obtie_oid_solic_pais('C1');

    lsactuaflex := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_ACTUA_FLEX'),
                       'N');

    lsabonpend := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_ABPEN_DEUDA'),
                       'N');


    abono_pendien:=0;
    
    

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_fec_soli,
             v_oidclie,
             v_oidperi LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          lncontador1        := 0;
          lb_no_existe_deuda := FALSE;

          if lsabonpend='S' then
             begin
             select nvl(x.val_recl_pend,0) into abono_pendien 
             from mae_clien x
             where x.cod_clie=v_codclie(j);
             exception
             when others then
                  NULL;
             end;
          end if;

          saldo_deudor := ccc_pkg_gener.ccc_fn_calcu_valor_saldo_deudo(v_codclie(j));

          SELECT b.val_mnt_min_deud
            INTO saldo_rechazo
            FROM bas_ctrl_fact b
           WHERE b.cod_pais = pscodigopais
             AND b.cod_peri = v_codperi(j);

          UPDATE int_solic_conso_cabec r
             SET r.ind_erro_deud = decode(sign(saldo_deudor - saldo_rechazo - abono_pendien),
                                          1,
                                          2,
                                          1),
                 r.val_sald_deud = saldo_deudor,
                 --r.fec_admi_cart = NULL,
                 --r.usu_admi_cart = NULL,
                 --r.obs_prub      = NULL,
                 r.val_recl_pend = abono_pendien,
                 r.val_sald_rech = saldo_rechazo          
           WHERE num_lote = v_numlote(j)
             AND sec_nume_docu = v_sec_nume_docu(j);

          IF lsactuaflex = 'S' THEN

            BEGIN
              -- Validacion Flexipago
              SELECT nvl(SUM(ff.val_sald_maxi_camp), 0)
                INTO lv_sald_maxi_camp_flex
                FROM flx_gener_finan_consu_flexi ff
               WHERE ff.oid_clie = v_oidclie(j)
                AND ff.oid_peri_sald_maxi_camp =  v_oidperi(j);

            EXCEPTION
              WHEN OTHERS THEN
                NULL;
            END;

            IF saldo_deudor - saldo_rechazo - abono_pendien <= lv_sald_maxi_camp_flex THEN
              UPDATE int_solic_conso_cabec ff
                 SET ff.val_impo_desc_3_tota_loca = saldo_deudor
                WHERE ff.sec_nume_docu = v_sec_nume_docu(j);

            END IF;

          END IF;

          IF (saldo_deudor - saldo_rechazo - abono_pendien > 0) THEN
            --Si despues de ejecutar la validacion se determina que el cliente se debe bloquear por deuda
            SELECT COUNT(1)
              INTO lncontador1
              FROM sto_clien_excep_valid_deuda
             WHERE nvl(oid_peri, v_oidperi(j)) = v_oidperi(j)
               AND oid_clie = v_oidclie(j);

            IF lncontador1 = 0 THEN

              -- Validacion Flexipago
              SELECT nvl(SUM(ff.val_sald_maxi_camp), 0)
                INTO lv_sald_maxi_camp_flex
                FROM flx_gener_finan_consu_flexi ff
               WHERE ff.oid_clie = v_oidclie(j)
                 AND ff.oid_peri_sald_maxi_camp = v_oidperi(j)
              --and ff.val_mont_flex_fina
              ;

              IF (saldo_deudor - saldo_rechazo - abono_pendien > lv_sald_maxi_camp_flex) THEN

                BEGIN
                  -- obtiene si tiene beneficio de deuda
                  SELECT val_porc,
                         tip_bene,
                         usu_crea,
                         val_obse,
                         val_mont
                    INTO lv_porc_deud,
                         lv_tipo_bene,
                         lv_usu_cre,
                         lv_obser,
                         lv_monto
                    FROM (SELECT DISTINCT a.val_porc,
                                          a.tip_bene,
                                          a.usu_crea,
                                          a.val_obse,
                                          a.val_mont --, cons.cod_clie
                            FROM int_solic_conso_cabec cons,
                                 (SELECT *
                                    FROM sto_docum_digit
                                   WHERE sec_nume_docu = v_sec_nume_docu(j)) occ,
                                 mae_clien_unida_admin c,
                                 zon_terri_admin d,
                                 zon_terri e,
                                 zon_secci f,
                                 zon_zona g,
                                 zon_regio h,
                                 mae_clien_tipo_subti i,
                                 mae_clien_clasi j,
                                 sto_clien_benef_deuda a
                           WHERE occ.sec_nume_docu = cons.sec_nume_docu
                             AND occ.num_lote = cons.num_lote
                             AND occ.cod_tipo_docu = pscodigotipodoc
                             AND c.ztad_oid_terr_admi = d.oid_terr_admi
                             AND d.zscc_oid_secc = f.oid_secc
                             AND d.terr_oid_terr = e.oid_terr
                             AND f.zzon_oid_zona = g.oid_zona
                             AND g.zorg_oid_regi = h.oid_regi
                             AND c.clie_oid_clie = i.clie_oid_clie
                             AND c.ind_acti = 1
                             AND d.ind_borr = 0
                             AND i.oid_clie_tipo_subt =
                                 j.ctsu_oid_clie_tipo_subt
                             AND c.clie_oid_clie = cons.clie_oid_clie
                             AND cons.perd_oid_peri =
                                 nvl(a.oid_peri, cons.perd_oid_peri) --periodo
                             AND c.clie_oid_clie =
                                 nvl(a.clie_oid_clie, c.clie_oid_clie) --cliente
                             AND i.ticl_oid_tipo_clie =
                                 nvl(a.oid_tipo_clie, i.ticl_oid_tipo_clie) --tipo
                             AND i.sbti_oid_subt_clie =
                                 nvl(a.oid_subt_clie, i.sbti_oid_subt_clie) --subtipo
                             AND j.tccl_oid_tipo_clasi =
                                 nvl(a.oid_tipo_clas_clie,
                                     j.tccl_oid_tipo_clasi) --tipo clasif
                             AND j.clas_oid_clas =
                                 nvl(a.oid_clas_clie, j.clas_oid_clas) --clasif
                             AND h.oid_regi = nvl(a.oid_regi, h.oid_regi) --region
                             AND g.oid_zona = nvl(a.oid_zona, g.oid_zona) --zon
                             AND a.ind_elim = '0'
                             AND nvl(a.clie_oid_clie, cons.clie_oid_clie) =
                                 cons.clie_oid_clie
                           ORDER BY nvl(val_mont, 0) + nvl(val_porc, 0) DESC)
                   WHERE rownum = 1;

                  IF lv_tipo_bene = 2 THEN
                    SELECT MAX(psc.oid_soli_cabe)
                      INTO lv_oid_soli_cabe
                      FROM ped_solic_cabec psc
                     WHERE psc.clie_oid_clie = v_oidclie(j)
                       AND psc.tspa_oid_tipo_soli_pais =
                           lv_oid_tipo_soli_pais
                       AND psc.val_tota_paga_loca > 0
                       AND psc.fec_fact IS NOT NULL;

                    SELECT MAX(psc.fec_fact),
                           SUM(psc.val_tota_paga_loca)
                      INTO lv_fec_ulti_pedi,
                           lv_imp_ulti_pedi
                      FROM ped_solic_cabec psc
                     WHERE psc.oid_soli_cabe = lv_oid_soli_cabe
                       AND psc.clie_oid_clie = v_oidclie(j)
                       AND psc.tspa_oid_tipo_soli_pais =
                           lv_oid_tipo_soli_pais
                       AND psc.val_tota_paga_loca > 0
                       AND psc.fec_fact IS NOT NULL;

                    /*SELECT nvl(SUM(mb.imp_pago), 0)
                     INTO lv_imp_abon
                     FROM ccc_movim_banca mb
                    WHERE mb.pais_oid_pais = ln_oid_pais
                      AND mb.clie_oid_clie = v_oidclie(j)
                      AND mb.fec_pago > lv_fec_ulti_pedi
                      AND mb.cod_iden_proc = 'P';*/

                    SELECT nvl(SUM(mb.imp_movi - mb.imp_pend), 0)
                      INTO lv_imp_abon
                      FROM ccc_movim_cuent_corri mb
                     WHERE mb.clie_oid_clie = v_oidclie(j)
                       AND mb.soca_oid_soli_cabe = lv_oid_soli_cabe;

                    -- Se calcula si el monto de la deuda es mayor al porcentaje de beneficio recuperado
                    saldo_deudor := round(lv_imp_ulti_pedi *
                                          (100 - lv_porc_deud) / 100,
                                          2) - nvl(lv_imp_abon, 0);

                    IF saldo_deudor <= 0 THEN
                      lb_no_existe_deuda := TRUE;
                    ELSE
                      lb_no_existe_deuda := FALSE;
                    END IF;
                  ELSE
                    IF saldo_deudor <= lv_monto OR lv_monto = 0 THEN
                      lb_no_existe_deuda := TRUE;
                    ELSE
                      lb_no_existe_deuda := FALSE;
                    END IF;
                  END IF;

                EXCEPTION
                  WHEN no_data_found THEN
                    lb_no_existe_deuda := FALSE;
                END;

              ELSE

                lb_no_existe_deuda := TRUE;
                lv_usu_cre         := psusuario;
                lv_obser           := 'Excepto de deuda por Flexipago';
                lv_exce_flex       := 1;

              END IF;

            ELSE
              lb_no_existe_deuda := TRUE;
              lv_usu_cre         := psusuario;
              lv_obser           := 'Excepto de deuda';
            END IF;

            IF (lb_no_existe_deuda AND lv_exce_flex = 0) THEN
              UPDATE int_solic_conso_cabec r
                 SET r.ind_erro_deud = '2',
                     r.ind_admi_cart = '1',
                     --r.val_mont_pedi = NULL,
                     --r.val_sald_deud = 0,
                     r.usu_admi_cart = lv_usu_cre,
                     r.obs_prub      = lv_obser,
                     r.fec_admi_cart = SYSDATE
               WHERE num_lote = v_numlote(j)
                 AND sec_nume_docu = v_sec_nume_docu(j);
            ELSE
              -- si tiene deuda se actualiza el monto del pedido
              SELECT nvl(SUM(det.val_unid_dem * det.val_prec_cata_unit_loca *
                             det.val_fact_repe),
                         0)
                INTO lnmontoped
                FROM int_solic_conso_detal det,
                     int_solic_conso_cabec cab
               WHERE cab.sec_nume_docu = v_sec_nume_docu(j)
                 AND cab.sec_nume_docu = det.sec_nume_docu_cabe;

              UPDATE int_solic_conso_cabec r
                 SET r.val_mont_pedi = CASE
                                         WHEN r.val_mont_pedi = 0 OR
                                              r.val_mont_pedi IS NULL THEN
                                          lnmontoped
                                         ELSE
                                          r.val_mont_pedi
                                       END
               WHERE num_lote = v_numlote(j)
                 AND sec_nume_docu = v_sec_nume_docu(j);
            END IF;

          ELSE
            lb_no_existe_deuda := TRUE;
          END IF;

          IF lb_no_existe_deuda THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;
    
    OPEN c_oficina;
    LOOP
      FETCH c_oficina BULK COLLECT
        INTO v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_fec_soli LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE int_solic_conso_cabec r
             SET r.ind_erro_deud = 1,
                 r.val_sald_deud = gen_pkg_gener.gen_fn_calcu_valor_saldo_deudo(v_codclie(j))
           WHERE num_lote = v_numlote(j)
             AND sec_nume_docu = v_sec_nume_docu(j);

        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_oficina%NOTFOUND;

    END LOOP;
    CLOSE c_oficina;

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
                              'ERROR STO_PR_OCC_DEUDA_CAMPA: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_deuda_campa;

  /***************************************************************************
  Descripcion       : Validacion de la Deuda de las Consultoras por Campa?a
  Fecha Creacion    : 07/12/2011
  Autor             : Jorge Florencio
  ***************************************************************************/
  PROCEDURE sto_pr_occ_deuda_campa2
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.fec_soli,
             cons.clie_oid_clie,
             cons.perd_oid_peri,
             cons.cod_zona
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         ;


    r_deuda c_deuda%ROWTYPE;


    j                      BINARY_INTEGER := 0;
    lb_no_existe_deuda     BOOLEAN := TRUE;
    saldo_deudor           NUMBER := 0;
    saldo_rechazo          NUMBER := 0;
    abono_pendien          NUMBER := 0;
    lv_sald_maxi_camp_flex NUMBER := 0;

    lv_obser     VARCHAR2(200);


    lnmontoped   NUMBER(12, 2);
    lv_exce_flex NUMBER := 0;



  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);




    abono_pendien:=0;
    
    

    OPEN c_deuda;
    LOOP
      FETCH c_deuda
        INTO r_deuda;
      EXIT WHEN c_deuda%NOTFOUND;
            
            sto_pr_calcu_deuda(
            pscodigopais,
            r_deuda.cod_clie,
            r_deuda.cod_zona,
            psusuario,
            r_deuda.cod_peri,
            lb_no_existe_deuda,
            saldo_deudor,
            saldo_rechazo,
            abono_pendien,
            lv_exce_flex,
            lv_sald_maxi_camp_flex,
            lv_obser
            );

          UPDATE int_solic_conso_cabec r
             SET r.ind_erro_deud = decode(sign(saldo_deudor - saldo_rechazo - abono_pendien),
                                          1,
                                          2,
                                          1),
                 r.val_sald_deud = saldo_deudor,
                 r.fec_admi_cart = NULL,
                 r.usu_admi_cart = NULL,
                 r.obs_prub      = NULL,
                 r.val_recl_pend = abono_pendien,
                 r.val_sald_rech = saldo_rechazo
           WHERE num_lote = r_deuda.num_lote
             AND sec_nume_docu = r_deuda.sec_nume_docu;

          IF lv_sald_maxi_camp_flex is not null THEN

            IF saldo_deudor - saldo_rechazo - abono_pendien <= lv_sald_maxi_camp_flex THEN
              UPDATE int_solic_conso_cabec ff
                 SET ff.val_impo_desc_3_tota_loca = saldo_deudor
                WHERE ff.sec_nume_docu = r_deuda.sec_nume_docu;

            END IF;

          END IF;


            IF (lb_no_existe_deuda AND lv_exce_flex = 0) THEN
              UPDATE int_solic_conso_cabec r
                 SET r.ind_erro_deud = '2',
                     r.ind_admi_cart = '1',
                     --r.val_mont_pedi = NULL,
                     --r.val_sald_deud = 0,
                     r.usu_admi_cart = psusuario,
                     r.obs_prub      = lv_obser,
                     r.fec_admi_cart = SYSDATE
               WHERE num_lote = r_deuda.num_lote
                 AND sec_nume_docu = r_deuda.sec_nume_docu;
            ELSE
              -- si tiene deuda se actualiza el monto del pedido
              SELECT nvl(SUM(det.val_unid_dem * det.val_prec_cata_unit_loca *
                             det.val_fact_repe),
                         0)
                INTO lnmontoped
                FROM int_solic_conso_detal det,
                     int_solic_conso_cabec cab
               WHERE cab.sec_nume_docu = r_deuda.sec_nume_docu
                 AND cab.sec_nume_docu = det.sec_nume_docu_cabe;

              UPDATE int_solic_conso_cabec r
                 SET r.val_mont_pedi = CASE
                                         WHEN r.val_mont_pedi = 0 OR
                                              r.val_mont_pedi IS NULL THEN
                                          lnmontoped
                                         ELSE
                                          r.val_mont_pedi
                                       END
               WHERE num_lote = r_deuda.num_lote
                 AND sec_nume_docu = r_deuda.sec_nume_docu;
            END IF;


          IF lb_no_existe_deuda THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = r_deuda.num_lote
               AND occ.sec_nume_docu = r_deuda.sec_nume_docu;

          END IF;

      END LOOP;
      CLOSE c_deuda;
    


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
                              'ERROR STO_PR_OCC_DEUDA_CAMPA: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_deuda_campa2;

  /***************************************************************************
  Descripcion       : Validacion de la Deuda de las Consultoras por Campa?a
  Fecha Creacion    : 07/12/2011
  Autor             : Jorge Florencio
  ***************************************************************************/
  PROCEDURE sto_pr_calcu_deuda
  (
    pscodigopais          VARCHAR2,
    pscodclie             VARCHAR2,
    pscodzona             VARCHAR2,
    psusuario             VARCHAR2,
    pscodperi             VARCHAR2,
    psno_existe_deuda     OUT BOOLEAN,
    pssaldo_deudor        OUT NUMBER,
    pssaldo_rechazo       OUT NUMBER,
    psabono_pendien       OUT NUMBER,
    psexce_flex           OUT NUMBER,
    pssald_maxi_camp_flex OUT NUMBER,
    psobse                OUT VARCHAR2
  ) IS
 


    lncontador1 NUMBER;
    lncontador2 NUMBER;

    lv_porc_deud NUMBER(3);
    lv_tipo_bene NUMBER(1);
    lv_usu_cre   VARCHAR2(50);
    lv_monto     NUMBER(8, 2);

    lv_oid_tipo_soli_pais NUMBER(12);
    lv_oid_soli_cabe      NUMBER(12);
    lv_fec_ulti_pedi      DATE;
    lv_imp_ulti_pedi      NUMBER(12, 2);
    lv_imp_abon           NUMBER(12, 2);

    lv_oidclie            NUMBER(12);
    lv_oidperi            NUMBER(12);

    lnmontoped   NUMBER(12, 2);
    lsactuaflex VARCHAR2(10);
    lsabonpend VARCHAR2(10);



  BEGIN

  psexce_flex:=0;

    begin
    select oid_clie into lv_oidclie from mae_clien where cod_clie=pscodclie;
    
    select a.oid_peri into lv_oidperi from cra_perio a, seg_perio_corpo b
    where a.peri_oid_peri=b.oid_peri and b.cod_peri=pscodperi;
    exception when others then
            return;
    end;
    
    
    lv_oid_tipo_soli_pais := fin_pkg_gener.fin_fn_obtie_oid_solic_pais('C1');

    lsactuaflex := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_ACTUA_FLEX'),
                       'N');

    lsabonpend := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                            'STO_ABPEN_DEUDA'),
                       'N');


    psabono_pendien:=0;
    
    


          lncontador1        := 0;
          lncontador2        := 0;
          psno_existe_deuda := FALSE;

          if lsabonpend='S' then
             begin
             select nvl(x.val_recl_pend,0) into psabono_pendien 
             from mae_clien x
             where x.cod_clie=pscodclie;
             exception
             when others then
                  NULL;
             end;
          end if;

          pssaldo_deudor := ccc_pkg_gener.ccc_fn_calcu_valor_saldo_deudo(pscodclie);

          SELECT b.val_mnt_min_deud
            INTO pssaldo_rechazo
            FROM bas_ctrl_fact b
           WHERE b.cod_pais = pscodigopais
             AND b.cod_peri = pscodperi;


          IF lsactuaflex = 'S' THEN

            BEGIN
              -- Validacion Flexipago
              SELECT nvl(SUM(ff.val_sald_maxi_camp), 0)
                INTO pssald_maxi_camp_flex
                FROM flx_gener_finan_consu_flexi ff
               WHERE ff.oid_clie = lv_oidclie
                AND ff.oid_peri_sald_maxi_camp =  lv_oidperi;

            EXCEPTION
              WHEN OTHERS THEN
                NULL;
            END;


          END IF;

          IF (pssaldo_deudor - pssaldo_rechazo - psabono_pendien > 0) THEN
            --Si despues de ejecutar la validacion se determina que el cliente se debe bloquear por deuda
            SELECT COUNT(1)
              INTO lncontador1
              FROM sto_clien_excep_valid_deuda
             WHERE nvl(oid_peri, lv_oidperi) = lv_oidperi
               AND oid_clie = lv_oidclie;

            SELECT COUNT(1)
              INTO lncontador2
              FROM int_ped_zona_ofici x
             WHERE x.cod_zona = pscodzona
               AND x.est_zoof = 1
                  AND x.ind_vali_deud = 1;


            IF lncontador1+lncontador2 = 0 THEN

              -- Validacion Flexipago
              SELECT nvl(SUM(ff.val_sald_maxi_camp), 0)
                INTO pssald_maxi_camp_flex
                FROM flx_gener_finan_consu_flexi ff
               WHERE ff.oid_clie = lv_oidclie
                 AND ff.oid_peri_sald_maxi_camp = lv_oidperi
              --and ff.val_mont_flex_fina
              ;

              IF (pssaldo_deudor - pssaldo_rechazo - psabono_pendien > pssald_maxi_camp_flex) THEN

                BEGIN
                  -- obtiene si tiene beneficio de deuda
                  SELECT val_porc,
                         tip_bene,
                         usu_crea,
                         val_obse,
                         val_mont
                    INTO lv_porc_deud,
                         lv_tipo_bene,
                         lv_usu_cre,
                         psobse,
                         lv_monto
                    FROM (SELECT DISTINCT a.val_porc,
                                          a.tip_bene,
                                          a.usu_crea,
                                          a.val_obse,
                                          a.val_mont --, cons.cod_clie
                            FROM mae_clien_unida_admin c,
                                 zon_terri_admin d,
                                 zon_terri e,
                                 zon_secci f,
                                 zon_zona g,
                                 zon_regio h,
                                 mae_clien_tipo_subti i,
                                 mae_clien_clasi j,
                                 sto_clien_benef_deuda a
                           WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
                             AND d.zscc_oid_secc = f.oid_secc
                             AND d.terr_oid_terr = e.oid_terr
                             AND f.zzon_oid_zona = g.oid_zona
                             AND g.zorg_oid_regi = h.oid_regi
                             AND c.clie_oid_clie = i.clie_oid_clie
                             AND c.ind_acti = 1
                             AND d.ind_borr = 0
                             AND i.oid_clie_tipo_subt =
                                 j.ctsu_oid_clie_tipo_subt
                             AND c.clie_oid_clie = lv_oidclie
                             AND lv_oidperi =
                                 nvl(a.oid_peri, lv_oidperi) --periodo
                             AND c.clie_oid_clie =
                                 nvl(a.clie_oid_clie, c.clie_oid_clie) --cliente
                             AND i.ticl_oid_tipo_clie =
                                 nvl(a.oid_tipo_clie, i.ticl_oid_tipo_clie) --tipo
                             AND i.sbti_oid_subt_clie =
                                 nvl(a.oid_subt_clie, i.sbti_oid_subt_clie) --subtipo
                             AND j.tccl_oid_tipo_clasi =
                                 nvl(a.oid_tipo_clas_clie,
                                     j.tccl_oid_tipo_clasi) --tipo clasif
                             AND j.clas_oid_clas =
                                 nvl(a.oid_clas_clie, j.clas_oid_clas) --clasif
                             AND h.oid_regi = nvl(a.oid_regi, h.oid_regi) --region
                             AND g.oid_zona = nvl(a.oid_zona, g.oid_zona) --zon
                             AND a.ind_elim = '0'
                             AND nvl(a.clie_oid_clie, lv_oidclie) =
                                 lv_oidclie
                           ORDER BY nvl(val_mont, 0) + nvl(val_porc, 0) DESC)
                   WHERE rownum = 1;

                  IF lv_tipo_bene = 2 THEN
                    SELECT MAX(psc.oid_soli_cabe)
                      INTO lv_oid_soli_cabe
                      FROM ped_solic_cabec psc
                     WHERE psc.clie_oid_clie = lv_oidclie
                       AND psc.tspa_oid_tipo_soli_pais =
                           lv_oid_tipo_soli_pais
                       AND psc.val_tota_paga_loca > 0
                       AND psc.fec_fact IS NOT NULL;

                    SELECT MAX(psc.fec_fact),
                           SUM(psc.val_tota_paga_loca)
                      INTO lv_fec_ulti_pedi,
                           lv_imp_ulti_pedi
                      FROM ped_solic_cabec psc
                     WHERE psc.oid_soli_cabe = lv_oid_soli_cabe
                       AND psc.clie_oid_clie = lv_oidclie
                       AND psc.tspa_oid_tipo_soli_pais =
                           lv_oid_tipo_soli_pais
                       AND psc.val_tota_paga_loca > 0
                       AND psc.fec_fact IS NOT NULL;


                    SELECT nvl(SUM(mb.imp_movi - mb.imp_pend), 0)
                      INTO lv_imp_abon
                      FROM ccc_movim_cuent_corri mb
                     WHERE mb.clie_oid_clie = lv_oidclie
                       AND mb.soca_oid_soli_cabe = lv_oid_soli_cabe;

                    -- Se calcula si el monto de la deuda es mayor al porcentaje de beneficio recuperado
                    pssaldo_deudor := round(lv_imp_ulti_pedi *
                                          (100 - lv_porc_deud) / 100,
                                          2) - nvl(lv_imp_abon, 0);

                    IF pssaldo_deudor <= 0 THEN
                      psno_existe_deuda := TRUE;
                    ELSE
                      psno_existe_deuda := FALSE;
                    END IF;
                  ELSE
                    IF pssaldo_deudor <= lv_monto OR lv_monto = 0 THEN
                      psno_existe_deuda := TRUE;
                    ELSE
                      psno_existe_deuda := FALSE;
                    END IF;
                  END IF;

                EXCEPTION
                  WHEN no_data_found THEN
                    psno_existe_deuda := FALSE;
                END;

              ELSE

                psno_existe_deuda := TRUE;
                psobse           := 'Excepto de deuda por Flexipago';
                psexce_flex       := 1;

              END IF;

            ELSE
              psno_existe_deuda  := TRUE;
              psobse           := 'Excepto de deuda';
            END IF;



          ELSE
            psno_existe_deuda := TRUE;
          END IF;
    

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR sto_pr_calcu_deuda: ' ||
                              ls_sqlerrm);

  END sto_pr_calcu_deuda;

  /***************************************************************************
  Descripcion       : Validacion de codigo de cliente inexistente
  Fecha Creacion    : 23/09/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_clien_inexi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.cod_pais,
             cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.num_clie,
             cons.tipo_soli,
             cons.cod_sbac,
             cons.cod_acce,
             cons.tip_desp,
             cons.sta_proc,
             cons.val_mont_pedi,
             cons.cod_zona_arri,
             cons.ind_docu_iden
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codpais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_codperi IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    TYPE t_num_clie IS TABLE OF int_solic_conso_cabec.num_clie%TYPE;
    TYPE t_tipo_soli IS TABLE OF int_solic_conso_cabec.tipo_soli%TYPE;
    TYPE t_cod_sbac IS TABLE OF int_solic_conso_cabec.cod_sbac%TYPE;
    TYPE t_cod_acce IS TABLE OF int_solic_conso_cabec.cod_acce%TYPE;
    TYPE t_tip_desp IS TABLE OF int_solic_conso_cabec.tip_desp%TYPE;
    TYPE t_sta_proc IS TABLE OF int_solic_conso_cabec.sta_proc%TYPE;
    TYPE t_val_mont_pedi IS TABLE OF int_solic_conso_cabec.val_mont_pedi%TYPE;
    TYPE t_cod_zona_arri IS TABLE OF int_solic_conso_cabec.cod_zona_arri%TYPE;
    TYPE t_ind_docu_iden IS TABLE OF int_solic_conso_cabec.ind_docu_iden%TYPE;

    v_codpais t_codpais;
    v_codperi t_codperi;
    v_codclie t_codclie;
    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    v_num_clie      t_num_clie;
    v_tipo_soli     t_tipo_soli;
    v_cod_sbac      t_cod_sbac;
    v_cod_acce      t_cod_acce;
    v_tip_desp      t_tip_desp;
    v_sta_proc      t_sta_proc;
    v_val_mont_pedi t_val_mont_pedi;
    v_cod_zona_arri t_cod_zona_arri;

    v_ind_docu_iden t_ind_docu_iden;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;

    j BINARY_INTEGER := 0;

    lsindtipo VARCHAR2(1);
    lsvalor   VARCHAR2(100);

    lsnumerodias sto_param_gener_occrr.val_param%TYPE;

    lscodigocliente mae_clien.cod_clie%TYPE;

  BEGIN

    lsnumerodias := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         'STO_NUM_DIAS_ATRAS');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    SELECT ind_actu_clie_cedu
      INTO lsindtipo
      FROM bas_pais
     WHERE cod_pais = pscodigopais;

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_codpais,
             v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu,
             v_num_clie,
             v_tipo_soli,
             v_cod_sbac,
             v_cod_acce,
             v_tip_desp,
             v_sta_proc,
             v_val_mont_pedi,
             v_cod_zona_arri,
             v_ind_docu_iden LIMIT w_filas;

      IF v_codpais.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_codpais.first .. v_codpais.last
        LOOP

          existe := TRUE;

          SELECT MIN(cod_clie)
            INTO lscodigocliente
            FROM mae_clien
           WHERE cod_clie = v_codclie(j);

          IF 'C' = substr(v_codclie(j), 0, 1) THEN

            lsvalor := substr(v_codclie(j), 2, 99);

            IF v_ind_docu_iden(j) = '1' THEN
             
                 SELECT MAX(cod_clie)
                  INTO lscodigocliente
                  FROM mae_clien             c,
                       mae_clien_ident       i,
                       mae_clien_datos_adici a
                 WHERE c.oid_clie = i.clie_oid_clie
                   AND c.oid_clie = a.clie_oid_clie
                   AND a.ind_acti = 1
                   AND i.tdoc_oid_tipo_docu = 2001
                   AND i.val_iden_docu_prin = 1
                   AND ltrim(i.num_docu_iden, '0') = ltrim(lsvalor, '0');

            ELSIF v_ind_docu_iden(j) = '0' AND lsindtipo = 1 THEN
                
                 SELECT MAX(cod_clie)
                    INTO lscodigocliente
                    FROM mae_clien             c,
                         mae_clien_ident       i,
                         mae_clien_datos_adici da
                   WHERE c.oid_clie = i.clie_oid_clie
                     AND i.clie_oid_clie = da.clie_oid_clie
                     AND da.ind_acti = 1
                     AND i.tdoc_oid_tipo_docu = 2001
                     AND i.num_docu_iden = lsvalor
                     AND i.val_iden_docu_prin = 1
                     AND i.fec_ulti_actu >= trunc(SYSDATE) - lsnumerodias;
                     
                  IF lscodigocliente IS NULL THEN
                        
                    SELECT MAX(cod_clie)
                      INTO lscodigocliente
                      FROM mae_clien             c,
                           mae_clien_ident       i,
                           mae_clien_datos_adici da
                     WHERE c.oid_clie = i.clie_oid_clie
                       AND i.clie_oid_clie = da.clie_oid_clie
                       AND da.ind_acti = 1
                       --AND i.tdoc_oid_tipo_docu = 2001
                       AND i.num_docu_iden = lsvalor
                       AND i.val_iden_docu_prin = 1
                       AND i.fec_ulti_actu >= trunc(SYSDATE) - lsnumerodias;
                                    
                  END IF;                 

            ELSIF v_ind_docu_iden(j) = '0' AND lsindtipo = 2 THEN

              SELECT MIN(a.cod_clie)
                INTO lscodigocliente
                FROM int_solic_conso_credi a,
                     sto_docum_digit       b
               WHERE a.sec_nume_docu = b.sec_nume_docu
                 AND b.ind_envi = '1'
                 AND a.num_docu = lsvalor
                 AND b.fec_modi >= trunc(SYSDATE) - lsnumerodias;

            ELSIF lsindtipo = 3 THEN

              SELECT MIN(a.cod_clie)
                INTO lscodigocliente
                FROM int_solic_conso_credi a,
                     sto_docum_digit       b
               WHERE a.sec_nume_docu = b.sec_nume_docu
                 AND b.ind_envi = '1'
                 AND ltrim(a.num_docu, '0') = ltrim(substr(lsvalor, 2), 0)
                 AND b.fec_modi >= trunc(SYSDATE) - lsnumerodias;

            ELSIF v_ind_docu_iden(j) = '0' AND lsindtipo = 4 THEN

              SELECT MIN(cod_clie)
                INTO lscodigocliente
                FROM mae_clien       c,
                     mae_clien_ident i
               WHERE clie_oid_clie = oid_clie
                 AND ltrim(i.num_docu_iden, '0') = ltrim(lsvalor, '0')
                 AND i.fec_ulti_actu >= trunc(SYSDATE) - lsnumerodias
                 AND i.tdoc_oid_tipo_docu = 2001
                 AND i.val_iden_docu_prin = 1;
                 
            ELSIF v_ind_docu_iden(j) = '0' AND lsindtipo = 5 THEN

              SELECT MIN(cod_clie)
                INTO lscodigocliente
                FROM mae_clien       c,
                     mae_clien_ident i
               WHERE clie_oid_clie = oid_clie
                 AND trim( translate( ltrim(i.num_docu_iden, '0'),'ABCDEFGHIJKLMNOPQRSTUVWXYZ' ,'                          ')) = trim ( ltrim(lsvalor, '0'))
                 AND i.fec_ulti_actu >= trunc(SYSDATE) - lsnumerodias;

            END IF;

            IF lscodigocliente IS NOT NULL THEN

              UPDATE sto_docum_digit
                 SET cod_clie = lscodigocliente
               WHERE sec_nume_docu = v_sec_nume_docu(j);

              UPDATE sto_docum_digit
                 SET cod_clie = lscodigocliente
               WHERE sec_nume_docu_cabe = v_sec_nume_docu(j);

              UPDATE int_solic_conso_cabec
                 SET cod_clie = lscodigocliente
               WHERE sec_nume_docu = v_sec_nume_docu(j);

              UPDATE int_solic_conso_detal d
                 SET cod_clie = lscodigocliente
               WHERE sec_nume_docu IN
                     (SELECT sec_nume_docu
                        FROM sto_docum_digit
                       WHERE sec_nume_docu_cabe = v_sec_nume_docu(j));

            ELSE
              existe := FALSE;

            END IF;

          END IF;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

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
                              'ERROR STO_PR_OCC_CLIEN_INEXI: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_clien_inexi;

  /***************************************************************************
  Descripcion       : Validacion de codigo de cliente inexistente
  Fecha Creacion    : 24/09/2009
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_clien_nueva
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j      BINARY_INTEGER := 0;
    existe BOOLEAN := TRUE;

    lsnumerodias sto_param_gener_occrr.val_param%TYPE;

    lscodigocliente mae_clien.cod_clie%TYPE;
    lsdocumento     mae_clien_ident.num_docu_iden%TYPE;

    j BINARY_INTEGER := 0;

    lsindactuacliente bas_pais.ind_actu_clie_cedu%TYPE;

  BEGIN

    lsnumerodias := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         'STO_NUM_DIAS_ATRAS');

    SELECT ind_actu_clie_cedu
      INTO lsindactuacliente
      FROM bas_pais
     WHERE cod_pais = pscodigopais;

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_codclie,
             v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          BEGIN

            SELECT cod_clie
              INTO lscodigocliente
              FROM mae_clien
             WHERE cod_clie = v_codclie(j);

          EXCEPTION
            WHEN no_data_found THEN
              lscodigocliente := NULL;
          END;

          IF (lsindactuacliente = '1') THEN

            BEGIN
              SELECT num_docu_iden
                INTO lsdocumento
                FROM mae_clien_ident
               WHERE num_docu_iden = v_codclie(j)
                 AND val_iden_docu_prin = 1
                 AND fec_ulti_actu >= trunc(SYSDATE) - lsnumerodias;

            EXCEPTION
              WHEN no_data_found THEN
                lsdocumento := NULL;
            END;

          ELSE
            IF (lsindactuacliente = '2') THEN

              BEGIN
                SELECT num_docu
                  INTO lsdocumento
                  FROM sto_docum_digit
                 WHERE num_docu = v_codclie(j)
                   AND ind_envi = '1'
                   AND fec_modi >= trunc(SYSDATE) - lsnumerodias;

              EXCEPTION
                WHEN no_data_found THEN
                  lsdocumento := NULL;
              END;

            END IF;
          END IF;
          IF (lscodigocliente = lsdocumento) THEN
            existe := FALSE;

          ELSE
            existe := TRUE;
          END IF;

          IF (existe) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

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
                              'ERROR sto_pr_occ_clien_nueva: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_clien_nueva;

  /**************************************************************************
  Descripcion       : Devuelve el numero de actividades por cronograma y periodo
                      considerando como base
  Fecha Creacion    : 12/05/2010
  Autor             : Jose Cairampoma
  ***************************************************************************/
  FUNCTION sto_fn_devue_nuact_byzpe
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    pnoidzona       NUMBER,
    lnnumperio      NUMBER
  ) RETURN NUMBER IS

    lnresult  NUMBER;
    lsperiodo seg_perio_corpo.cod_peri%TYPE;
  BEGIN

    lsperiodo := cob_pkg_gener.cob_fn_calcu_perio_nsgte(pscodigoperiodo,
                                                        lnnumperio);
    SELECT COUNT(1)
      INTO lnresult
      FROM cra_crono a,
           cra_activ e,
           seg_pais  sp
     WHERE e.oid_acti = a.cact_oid_acti
       AND e.pais_oid_pais = sp.oid_pais
       AND sp.cod_pais = pscodigopais
       AND a.perd_oid_peri IN
           (SELECT b.oid_peri
              FROM cra_perio b
             WHERE b.peri_oid_peri IN
                   (SELECT c.oid_peri
                      FROM seg_perio_corpo c
                     WHERE c.cod_peri = lsperiodo))
       AND a.zzon_oid_zona = pnoidzona
       AND e.cod_acti IN (SELECT f.val_param
                            FROM sto_param_gener_occrr f
                           WHERE f.cod_para LIKE '%STO_CRA_COD_ACTIV%'
                             AND f.val_param IS NOT NULL);

    RETURN lnresult;

  EXCEPTION
    WHEN OTHERS THEN
      raise_application_error(-20123,
                              'ERROR sto_fn_devue_nuact_byzpe: ' ||
                              ls_sqlerrm);
  END sto_fn_devue_nuact_byzpe;
  /**************************************************************************
  Descripcion       : Devuelve el promedio de venta por consultora para FAD
  Fecha Creacion    : 17/01/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION sto_fn_devue_prom_venta
  (
    pnoidcliente NUMBER,
    pnoidperiini NUMBER,
    pnoidperifin NUMBER
  ) RETURN NUMBER IS

    lnresult NUMBER;
  BEGIN

    BEGIN
      /*      SELECT SUM(x) / pnpedidos
              INTO lnresult
              FROM (SELECT *
                      FROM (SELECT perd_oid_peri,
                                   SUM(val_tota_paga_loca) x
                              FROM ped_solic_cabec
                             WHERE clie_oid_clie = pnoidcliente
                               AND ind_oc = 1
                               AND val_tota_paga_loca > 0
                               AND fec_fact IS NOT NULL
                             GROUP BY perd_oid_peri
                             ORDER BY perd_oid_peri DESC)
                     WHERE rownum <= pnpedidos);
      */

      SELECT nvl(round(SUM(x) / COUNT(*)), 0)
        INTO lnresult
        FROM (SELECT *
                FROM (SELECT perd_oid_peri,
                             SUM(val_tota_paga_loca) x
                        FROM ped_solic_cabec
                       WHERE clie_oid_clie = pnoidcliente
                         AND ind_oc = 1
                         AND val_tota_paga_loca > 0
                         AND fec_fact IS NOT NULL
                         AND perd_oid_peri >= pnoidperiini
                         AND perd_oid_peri < pnoidperifin
                       GROUP BY perd_oid_peri));

    EXCEPTION
      WHEN OTHERS THEN
        lnresult := 0;
    END;

    RETURN lnresult;

  EXCEPTION
    WHEN OTHERS THEN
      raise_application_error(-20123,
                              'ERROR sto_fn_devue_prom_venta: ' ||
                              ls_sqlerrm);
  END sto_fn_devue_prom_venta;

  /***************************************************************************
  Descripcion       : Validacion de existencia de cronogramas de actividades
  Fecha Creacion    : 01/03/2010
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_exicr_activ
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda
    (
      vnindicadorperiodo NUMBER,
      vnactbase          NUMBER
    ) IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             sto_fn_devue_nuact_byzpe(occ.cod_pais,
                                      cons.cod_peri,
                                      cons.zzon_oid_zona,
                                      0) v_nperiactu,
             CASE
               WHEN vnindicadorperiodo > 1 THEN
                sto_fn_devue_nuact_byzpe(occ.cod_pais,
                                         cons.cod_peri,
                                         cons.zzon_oid_zona,
                                         1)
               ELSE
                vnactbase
             END v_nperiactu1,
             CASE
               WHEN vnindicadorperiodo > 2 THEN
                sto_fn_devue_nuact_byzpe(occ.cod_pais,
                                         cons.cod_peri,
                                         cons.zzon_oid_zona,
                                         2)
               ELSE
                vnactbase
             END v_nperiactu2,
             CASE
               WHEN vnindicadorperiodo > 3 THEN
                sto_fn_devue_nuact_byzpe(occ.cod_pais,
                                         cons.cod_peri,
                                         cons.zzon_oid_zona,
                                         3)
               ELSE
                vnactbase
             END v_nperiactu3
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_nperiactu IS TABLE OF NUMBER;
    TYPE t_nperiactu1 IS TABLE OF NUMBER;
    TYPE t_nperiactu2 IS TABLE OF NUMBER;
    TYPE t_nperiactu3 IS TABLE OF NUMBER;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_nperiactu     t_nperiactu;
    v_nperiactu1    t_nperiactu1;
    v_nperiactu2    t_nperiactu2;
    v_nperiactu3    t_nperiactu3;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    lnindicadorperiodo NUMBER;

    j BINARY_INTEGER := 0;

    lnactbase NUMBER := 0;

  BEGIN

    lnindicadorperiodo := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_CRA_CANT_PERI');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    SELECT COUNT(1)
      INTO lnactbase
      FROM (SELECT f.val_param
              FROM sto_param_gener_occrr f
             WHERE f.cod_para LIKE '%STO_CRA_COD_ACTIV%'
               AND f.val_param IS NOT NULL);

    OPEN c_deuda(lnindicadorperiodo, lnactbase);
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_nperiactu,
             v_nperiactu1,
             v_nperiactu2,
             v_nperiactu3 LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          IF lnactbase > 0 AND lnactbase = v_nperiactu(j) AND
             lnactbase = v_nperiactu1(j) AND lnactbase = v_nperiactu2(j) AND
             lnactbase = v_nperiactu3(j) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

            UPDATE int_solic_conso_cabec
               SET ind_vali_exis_cron = NULL
             WHERE num_lote = v_numlote(j)
               AND sec_nume_docu = v_sec_nume_docu(j);

          ELSE

            UPDATE int_solic_conso_cabec
               SET ind_vali_exis_cron = lnindicadorperiodo
             WHERE num_lote = v_numlote(j)
               AND sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

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
                              'ERROR STO_PR_OCC_EXICR_ACTIV: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_exicr_activ;

  /***************************************************************************
  Descripcion       : Validacion de error de vencimiento de cronograma
  Fecha Creacion    : 02/03/2010
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_error_vecro
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.perd_oid_peri,
             cons.zzon_oid_zona,
             cons.pais_oid_pais
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_perd_oid_peri IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;
    TYPE t_zzon_oid_zona IS TABLE OF int_solic_conso_cabec.zzon_oid_zona%TYPE;
    TYPE t_pais_oid_pais IS TABLE OF int_solic_conso_cabec.pais_oid_pais%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_perd_oid_peri t_perd_oid_peri;
    v_zzon_oid_zona t_zzon_oid_zona;
    v_pais_oid_pais t_pais_oid_pais;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    lsindicadorperiodo sto_param_gener_occrr.val_param%TYPE;

    j BINARY_INTEGER := 0;

    lncontador1 NUMBER := 0;
    lncontador2 NUMBER := 0;
    lncontador3 NUMBER := 0;

    lncodigoperiodo seg_perio_corpo.cod_peri%TYPE;
    lntotal         NUMBER := 0;
    lserror         VARCHAR2(100);

  BEGIN

    lsindicadorperiodo := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_CRA_CANT_PERI');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_perd_oid_peri,
             v_zzon_oid_zona,
             v_pais_oid_pais LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          lncontador1 := 0;
          lncontador2 := 0;
          lncontador3 := 0;
          lntotal     := 0;
          lserror     := '';

          SELECT c.cod_peri
            INTO lncodigoperiodo
            FROM seg_perio_corpo c,
                 cra_perio       b
           WHERE c.oid_peri = b.peri_oid_peri
             AND b.oid_peri = v_perd_oid_peri(j);

          IF (lsindicadorperiodo = '2') THEN

            SELECT COUNT(1)
              INTO lncontador1
              FROM (SELECT *
                      FROM (SELECT c.cod_zona,
                                   a.fec_inic v1,
                                   b.fec_inic cv_1
                              FROM cra_crono a,
                                   cra_crono b,
                                   zon_zona  c,
                                   cra_activ e,
                                   cra_activ f
                             WHERE e.cod_acti = 'V1'
                               AND a.perd_oid_peri IN
                                   (SELECT bb.oid_peri
                                      FROM cra_perio bb
                                     WHERE bb.peri_oid_peri IN
                                           (SELECT cc.oid_peri
                                              FROM seg_perio_corpo cc
                                             WHERE cc.cod_peri =
                                                   lncodigoperiodo))
                               AND f.cod_acti = 'CV'
                               AND b.perd_oid_peri IN
                                   (SELECT bv.oid_peri
                                      FROM cra_perio bv
                                     WHERE bv.peri_oid_peri IN
                                           (SELECT cv.oid_peri
                                              FROM seg_perio_corpo cv
                                             WHERE cv.cod_peri =
                                                   cob_pkg_gener.cob_fn_calcu_perio_nsgte(lncodigoperiodo,
                                                                                          1)))
                               AND a.cact_oid_acti = e.oid_acti
                               AND b.cact_oid_acti = f.oid_acti
                               AND a.zzon_oid_zona = b.zzon_oid_zona
                               AND a.zzon_oid_zona = v_zzon_oid_zona(j)
                               AND a.zzon_oid_zona = c.oid_zona
                               AND e.pais_oid_pais = v_pais_oid_pais(j)
                               AND f.pais_oid_pais = v_pais_oid_pais(j))
                     WHERE v1 <> cv_1);

            lntotal := lncontador1;

            IF (lncontador1 > 0) THEN

              UPDATE int_solic_conso_cabec
                 SET ind_vali_venc_cron = '1'
               WHERE sec_nume_docu = v_sec_nume_docu(j)
                 AND num_lote = v_numlote(j);

            END IF;
          END IF;

          IF (lsindicadorperiodo = '3') THEN

            SELECT COUNT(1)
              INTO lncontador1
              FROM (SELECT *
                      FROM (SELECT c.cod_zona,
                                   a.fec_inic v1,
                                   b.fec_inic cv_1
                              FROM cra_crono a,
                                   cra_crono b,
                                   zon_zona  c,
                                   cra_activ e,
                                   cra_activ f
                             WHERE e.cod_acti = 'V1'
                               AND a.perd_oid_peri IN
                                   (SELECT bb.oid_peri
                                      FROM cra_perio bb
                                     WHERE bb.peri_oid_peri IN
                                           (SELECT cc.oid_peri
                                              FROM seg_perio_corpo cc
                                             WHERE cc.cod_peri =
                                                   lncodigoperiodo))
                               AND f.cod_acti = 'CV'
                               AND b.perd_oid_peri IN
                                   (SELECT bv.oid_peri
                                      FROM cra_perio bv
                                     WHERE bv.peri_oid_peri IN
                                           (SELECT cv.oid_peri
                                              FROM seg_perio_corpo cv
                                             WHERE cv.cod_peri =
                                                   cob_pkg_gener.cob_fn_calcu_perio_nsgte(lncodigoperiodo,
                                                                                          1)))
                               AND a.cact_oid_acti = e.oid_acti
                               AND b.cact_oid_acti = f.oid_acti
                               AND a.zzon_oid_zona = b.zzon_oid_zona
                               AND a.zzon_oid_zona = v_zzon_oid_zona(j)
                               AND a.zzon_oid_zona = c.oid_zona
                               AND e.pais_oid_pais = v_pais_oid_pais(j)
                               AND f.pais_oid_pais = v_pais_oid_pais(j))
                     WHERE v1 <> cv_1);

            SELECT COUNT(1)
              INTO lncontador2
              FROM (SELECT *
                      FROM (SELECT c.cod_zona,
                                   a.fec_inic v1,
                                   b.fec_inic cv_1
                              FROM cra_crono a,
                                   cra_crono b,
                                   zon_zona  c,
                                   cra_activ e,
                                   cra_activ f
                             WHERE e.cod_acti = 'V2'
                               AND a.perd_oid_peri IN
                                   (SELECT bb.oid_peri
                                      FROM cra_perio bb
                                     WHERE bb.peri_oid_peri IN
                                           (SELECT cc.oid_peri
                                              FROM seg_perio_corpo cc
                                             WHERE cc.cod_peri =
                                                   lncodigoperiodo))
                               AND f.cod_acti = 'CV'
                               AND b.perd_oid_peri IN
                                   (SELECT bv.oid_peri
                                      FROM cra_perio bv
                                     WHERE bv.peri_oid_peri IN
                                           (SELECT cv.oid_peri
                                              FROM seg_perio_corpo cv
                                             WHERE cv.cod_peri =
                                                   cob_pkg_gener.cob_fn_calcu_perio_nsgte(lncodigoperiodo,
                                                                                          2)))
                               AND a.cact_oid_acti = e.oid_acti
                               AND b.cact_oid_acti = f.oid_acti
                               AND a.zzon_oid_zona = b.zzon_oid_zona
                               AND a.zzon_oid_zona = v_zzon_oid_zona(j)
                               AND a.zzon_oid_zona = c.oid_zona
                               AND e.pais_oid_pais = v_pais_oid_pais(j)
                               AND f.pais_oid_pais = v_pais_oid_pais(j))
                     WHERE v1 <> cv_1);

            lntotal := lncontador1 + lncontador2;

            IF (lncontador1 > 0 OR lncontador2 > 0) THEN

              UPDATE int_solic_conso_cabec
                 SET ind_vali_venc_cron = '2'
               WHERE sec_nume_docu = v_sec_nume_docu(j)
                 AND num_lote = v_numlote(j);

            END IF;

          END IF;

          IF (lsindicadorperiodo = '4') THEN

            SELECT COUNT(1)
              INTO lncontador1
              FROM (SELECT *
                      FROM (SELECT c.cod_zona,
                                   a.fec_inic v1,
                                   b.fec_inic cv_1
                              FROM cra_crono a,
                                   cra_crono b,
                                   zon_zona  c,
                                   cra_activ e,
                                   cra_activ f
                             WHERE e.cod_acti = 'V1'
                               AND a.perd_oid_peri IN
                                   (SELECT bb.oid_peri
                                      FROM cra_perio bb
                                     WHERE bb.peri_oid_peri IN
                                           (SELECT cc.oid_peri
                                              FROM seg_perio_corpo cc
                                             WHERE cc.cod_peri =
                                                   lncodigoperiodo))
                               AND f.cod_acti = 'CV'
                               AND b.perd_oid_peri IN
                                   (SELECT bv.oid_peri
                                      FROM cra_perio bv
                                     WHERE bv.peri_oid_peri IN
                                           (SELECT cv.oid_peri
                                              FROM seg_perio_corpo cv
                                             WHERE cv.cod_peri =
                                                   cob_pkg_gener.cob_fn_calcu_perio_nsgte(lncodigoperiodo,
                                                                                          1)))
                               AND a.cact_oid_acti = e.oid_acti
                               AND b.cact_oid_acti = f.oid_acti
                               AND a.zzon_oid_zona = b.zzon_oid_zona
                               AND a.zzon_oid_zona = v_zzon_oid_zona(j)
                               AND a.zzon_oid_zona = c.oid_zona
                               AND e.pais_oid_pais = v_pais_oid_pais(j)
                               AND f.pais_oid_pais = v_pais_oid_pais(j))
                     WHERE v1 <> cv_1);

            SELECT COUNT(1)
              INTO lncontador2
              FROM (SELECT *
                      FROM (SELECT c.cod_zona,
                                   a.fec_inic v1,
                                   b.fec_inic cv_1
                              FROM cra_crono a,
                                   cra_crono b,
                                   zon_zona  c,
                                   cra_activ e,
                                   cra_activ f
                             WHERE e.cod_acti = 'V2'
                               AND a.perd_oid_peri IN
                                   (SELECT bb.oid_peri
                                      FROM cra_perio bb
                                     WHERE bb.peri_oid_peri IN
                                           (SELECT cc.oid_peri
                                              FROM seg_perio_corpo cc
                                             WHERE cc.cod_peri =
                                                   lncodigoperiodo))
                               AND f.cod_acti = 'CV'
                               AND b.perd_oid_peri IN
                                   (SELECT bv.oid_peri
                                      FROM cra_perio bv
                                     WHERE bv.peri_oid_peri IN
                                           (SELECT cv.oid_peri
                                              FROM seg_perio_corpo cv
                                             WHERE cv.cod_peri =
                                                   cob_pkg_gener.cob_fn_calcu_perio_nsgte(lncodigoperiodo,
                                                                                          2)))
                               AND a.cact_oid_acti = e.oid_acti
                               AND b.cact_oid_acti = f.oid_acti
                               AND a.zzon_oid_zona = b.zzon_oid_zona
                               AND a.zzon_oid_zona = v_zzon_oid_zona(j)
                               AND a.zzon_oid_zona = c.oid_zona
                               AND e.pais_oid_pais = v_pais_oid_pais(j)
                               AND f.pais_oid_pais = v_pais_oid_pais(j))
                     WHERE v1 <> cv_1);

            SELECT COUNT(1)
              INTO lncontador3
              FROM (SELECT *
                      FROM (SELECT c.cod_zona,
                                   a.fec_inic v1,
                                   b.fec_inic cv_1
                              FROM cra_crono a,
                                   cra_crono b,
                                   zon_zona  c,
                                   cra_activ e,
                                   cra_activ f
                             WHERE e.cod_acti = 'V3'
                               AND a.perd_oid_peri IN
                                   (SELECT bb.oid_peri
                                      FROM cra_perio bb
                                     WHERE bb.peri_oid_peri IN
                                           (SELECT cc.oid_peri
                                              FROM seg_perio_corpo cc
                                             WHERE cc.cod_peri =
                                                   lncodigoperiodo))
                               AND f.cod_acti = 'CV'
                               AND b.perd_oid_peri IN
                                   (SELECT bv.oid_peri
                                      FROM cra_perio bv
                                     WHERE bv.peri_oid_peri IN
                                           (SELECT cv.oid_peri
                                              FROM seg_perio_corpo cv
                                             WHERE cv.cod_peri =
                                                   cob_pkg_gener.cob_fn_calcu_perio_nsgte(lncodigoperiodo,
                                                                                          3)))
                               AND a.cact_oid_acti = e.oid_acti
                               AND b.cact_oid_acti = f.oid_acti
                               AND a.zzon_oid_zona = b.zzon_oid_zona
                               AND a.zzon_oid_zona = v_zzon_oid_zona(j)
                               AND a.zzon_oid_zona = c.oid_zona
                               AND e.pais_oid_pais = v_pais_oid_pais(j)
                               AND f.pais_oid_pais = v_pais_oid_pais(j))
                     WHERE v1 <> cv_1);

            lntotal := lncontador1 + lncontador2 + lncontador3;

            IF (lncontador1 > 0 OR lncontador2 > 0 OR lncontador3 > 0) THEN

              UPDATE int_solic_conso_cabec
                 SET ind_vali_venc_cron = '3'
               WHERE sec_nume_docu = v_sec_nume_docu(j)
                 AND num_lote = v_numlote(j);

            END IF;

          END IF;

          IF (lntotal = 0) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

            UPDATE int_solic_conso_cabec
               SET ind_vali_venc_cron = NULL
             WHERE sec_nume_docu = v_sec_nume_docu(j)
               AND num_lote = v_numlote(j);

          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

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
                              'ERROR STO_PR_OCC_ERROR_VECRO: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_error_vecro;

  /***************************************************************************
  Descripcion       : Validacion de unidades maximas por producto y pedido
  Fecha Creacion    : 04/03/2010
  Autor             : Cristhian Roman
  ***************************************************************************/
  PROCEDURE sto_pr_occ_unida_maxim
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_unidadmaxima IS
      SELECT cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codperi IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j      BINARY_INTEGER := 0;
    numero NUMBER := 0;

    verifica BOOLEAN;

    lsparametrounidades sto_param_gener_occrr.val_param%TYPE;

  BEGIN

    lsparametrounidades := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                'OCR_MAXI_UNID');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_unidadmaxima;
    LOOP
      FETCH c_unidadmaxima BULK COLLECT
        INTO v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          verifica := TRUE;
          numero   := 0;

          SELECT COUNT(1)
            INTO numero
            FROM int_solic_conso_detal d
           WHERE d.cod_pais = pscodigopais
             AND d.cod_peri = v_codperi(j)
             AND d.cod_clie = v_codclie(j)
             AND d.num_lote = v_numlote(j)
             AND d.val_unid_dem > to_number(lsparametrounidades);

          IF (numero > 0) THEN
            verifica := FALSE;
          END IF;

          IF (verifica) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_unidadmaxima%NOTFOUND;

    END LOOP;
    CLOSE c_unidadmaxima;

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
                              'ERROR STO_PR_OCC_UNIDA_MAXIM: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_unida_maxim;

  /**************************************************************************
  Descripcion       : STO_PR_OCC_PROGR_NUEVA_OK
                    Procedimiento de Validacion para ejecucion del programa
                    de Nuevas segun secuencia de ejecucion
  Fecha Creacion    : 15/04/2010
  Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_occ_progr_nueva
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cabe_valid IS
      SELECT occ.num_lote,
             occ.sec_nume_docu,
             occ.cod_clie,
             cab.cod_peri
        FROM sto_proce_docum_digit occ,
             int_solic_conso_cabec cab
       WHERE cab.num_lote = occ.num_lote
         AND cab.sec_nume_docu = occ.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.cod_tipo_docu = pscodigotipodoc;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_cod_clie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_cod_clie      t_cod_clie;
    v_cod_peri      t_cod_peri;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    j    BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cabe_valid;
    LOOP
      FETCH c_cabe_valid BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_cod_clie,
             v_cod_peri LIMIT rows;

      IF v_numlote.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          cup_pkg_prog_nuevas.cup_pr_proce_despa_nueva_sto(pscodigopais,
                                                           v_cod_peri(j),
                                                           psusuario,
                                                           pscodigotipodoc,
                                                           psnumeroproceso,
                                                           v_cod_clie(j));

          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);
        END LOOP;
      END IF;
      EXIT WHEN c_cabe_valid%NOTFOUND;
    END LOOP;
    CLOSE c_cabe_valid;

    /* Inserta en STO los detalles agregados por el programa */
    sto_pkg_proce_gener.sto_pr_carga_sto_valid_carga(pscodigopais,
                                                     pscodigotipodoc,
                                                     psusuario,
                                                     psnumeroproceso);

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
                              'ERROR STO_PR_OCC_PROGR_NUEVA: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_progr_nueva;

  /**************************************************************************
  Descripcion       : STO_PR_OCC_SESSI_EXPER_OK
                    Procedimiento de Validacion para ejecucion del programa
                    de Session Experte segun secuencia de ejecucion
  Fecha Creacion    : 15/04/2010
  Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_occ_sessi_exper
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cabe_valid IS
      SELECT occ.num_lote,
             occ.sec_nume_docu
        FROM sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.cod_tipo_docu = pscodigotipodoc;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    j    BINARY_INTEGER := 0;

    lscodigoperiodo seg_perio_corpo.cod_peri%TYPE;
  BEGIN

    SELECT cod_peri
      INTO lscodigoperiodo
      FROM bas_ctrl_fact c
     WHERE c.sta_camp = '0'
       AND c.ind_camp_act = '1';

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    /* Invocacion al programa Session Experte - STO */
    sse_pkg_proce_pedid.sse_pr_procesa_pedid_consu_sto(pscodigopais,
                                                       lscodigoperiodo,
                                                       psusuario,
                                                       pscodigotipodoc,
                                                       psnumeroproceso);

    /* Inserta en STO los detalles agregados por el programa */
    sto_pkg_proce_gener.sto_pr_carga_sto_valid_carga(pscodigopais,
                                                     pscodigotipodoc,
                                                     psusuario,
                                                     psnumeroproceso);

    OPEN c_cabe_valid;
    LOOP
      FETCH c_cabe_valid BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_numlote.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET -- ACTUALZIAMOS INDICADORES DE VALIDACION
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cabe_valid%NOTFOUND;
    END LOOP;
    CLOSE c_cabe_valid;

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
                              'ERROR STO_PR_OCC_SESSI_EXPER: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_sessi_exper;

  /**************************************************************************
  Descripcion       : STO_PR_OCC_DUPLA_CYZON_OK
                    Procedimiento de Validacion para ejecucion del programa
                    de Dupla Cyzone segun secuencia de ejecucion
  Fecha Creacion    : 15/04/2010
  Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_occ_dupla_cyzon
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cabe_valid IS
      SELECT occ.num_lote,
             occ.sec_nume_docu
        FROM sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.cod_tipo_docu = pscodigotipodoc;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    j    BINARY_INTEGER := 0;

    lscodigoperiodo seg_perio_corpo.cod_peri%TYPE;
  BEGIN

    SELECT cod_peri
      INTO lscodigoperiodo
      FROM bas_ctrl_fact c
     WHERE c.sta_camp = '0'
       AND c.ind_camp_act = '1';

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    /* Invocacion al programa Dupla Cyzone - STO */
    cyz_pkg_progr_dupla.cyz_pr_carga_premi_solic_sto(pscodigopais,
                                                     c_codigo_programa_dupla_cyzone,
                                                     lscodigoperiodo,
                                                     psusuario,
                                                     pscodigotipodoc,
                                                     psnumeroproceso);
    cyz_pkg_progr_dupla.cyz_pr_actua_unida_cumpl_sto(pscodigopais,
                                                     lscodigoperiodo,
                                                     psusuario,
                                                     pscodigotipodoc,
                                                     psnumeroproceso);
    cyz_pkg_progr_dupla.cyz_pr_actua_unida_solic_sto(pscodigopais,
                                                     lscodigoperiodo,
                                                     psusuario,
                                                     pscodigotipodoc,
                                                     psnumeroproceso);
    cyz_pkg_progr_dupla.cyz_pr_actua_unida_bien2_sto(pscodigopais,
                                                     lscodigoperiodo,
                                                     psusuario,
                                                     pscodigotipodoc,
                                                     psnumeroproceso);

    /* Inserta en STO los detalles agregados por el programa */
    sto_pkg_proce_gener.sto_pr_carga_sto_valid_carga(pscodigopais,
                                                     pscodigotipodoc,
                                                     psusuario,
                                                     psnumeroproceso);

    OPEN c_cabe_valid;
    LOOP
      FETCH c_cabe_valid BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_numlote.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET -- ACTUALZIAMOS INDICADORES DE VALIDACION
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cabe_valid%NOTFOUND;
    END LOOP;
    CLOSE c_cabe_valid;

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
                              'ERROR STO_PR_OCC_DUPLA_CYZON: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_dupla_cyzon;

  /**************************************************************************
  Descripcion       : STO_PR_OCC_OPORT_PRIVI_OK
                    Procedimiento de Validacion para ejecucion del programa
                    de Oportunidades Privilege segun secuencia de ejecucion
  Fecha Creacion    : 16/04/2010
  Autor             : Dennys Oliva
  ***************************************************************************/
  PROCEDURE sto_pr_occ_oport_privi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cabe_valid IS
      SELECT occ.num_lote,
             occ.sec_nume_docu
        FROM sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.cod_tipo_docu = pscodigotipodoc;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    j    BINARY_INTEGER := 0;

    lscodigoperiodo seg_perio_corpo.cod_peri%TYPE;
  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    SELECT cod_peri
      INTO lscodigoperiodo
      FROM bas_ctrl_fact c
     WHERE c.sta_camp = '0'
       AND c.ind_camp_act = '1';

    /* Invocacion al programa Oportunidades Privilege - STO */
    ocr_solic_pedidos.ocr_pr_comp_oport_privi_sto(pscodigopais,
                                                  lscodigoperiodo,
                                                  psusuario,
                                                  pscodigotipodoc,
                                                  psnumeroproceso);

    /* Inserta en STO los detalles agregados por el programa */
    sto_pkg_proce_gener.sto_pr_carga_sto_valid_carga(pscodigopais,
                                                     pscodigotipodoc,
                                                     psusuario,
                                                     psnumeroproceso);

    OPEN c_cabe_valid;
    LOOP
      FETCH c_cabe_valid BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_numlote.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET -- ACTUALZIAMOS INDICADORES DE VALIDACION
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cabe_valid%NOTFOUND;
    END LOOP;
    CLOSE c_cabe_valid;

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
                              'ERROR STO_PR_OCC_OPORT_PRIVI: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_oport_privi;

  /***************************************************************************
  Descripcion       : Validacion de Documentos SAD Rechazados
  Fecha Creacion    : 23/04/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_dosad_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_unidadmaxima IS
      SELECT cons.cod_peri,
             cons.cod_clie,
             cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_codperi IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_codperi       t_codperi;
    v_codclie       t_codclie;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j      BINARY_INTEGER := 0;
    numero NUMBER := 0;

    verifica BOOLEAN;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_unidadmaxima;
    LOOP
      FETCH c_unidadmaxima BULK COLLECT
        INTO v_codperi,
             v_codclie,
             v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          verifica := TRUE;
          numero   := 0;

          SELECT COUNT(1)
            INTO numero
            FROM int_solic_conso_actua_datos a,
                 sto_detal_docum_excep       b
           WHERE a.sec_nume_docu = b.sec_nume_docu
             AND a.cod_clie = v_codclie(j)
             AND a.cod_peri = v_codperi(j);

          IF (numero > 0) THEN
            verifica := FALSE;
          END IF;

          IF (verifica) THEN
            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;
      END IF;
      EXIT WHEN c_unidadmaxima%NOTFOUND;

    END LOOP;
    CLOSE c_unidadmaxima;

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
                              'ERROR sto_pr_occ_dosad_recha: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_dosad_recha;
  /***************************************************************************
  Descripcion       : Validacion de Bloqueo por control 2
  Fecha Creacion    : 07/06/2010
  Autor             : Jesse Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_occ_bloqu_contr2
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    -- Listado Solicitudes con excepcion
    CURSOR c_bloqucontr2_excep IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND EXISTS
       (SELECT 1
                FROM mae_clien_unida_admin c,
                     zon_terri_admin       d,
                     zon_terri             e,
                     zon_secci             f,
                     zon_zona              g,
                     zon_regio             h,
                     mae_clien_tipo_subti  i,
                     mae_clien_clasi       j,
                     sto_bloqu_contr       a
               WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
                 AND d.zscc_oid_secc = f.oid_secc
                 AND d.terr_oid_terr = e.oid_terr
                 AND f.zzon_oid_zona = g.oid_zona
                 AND g.zorg_oid_regi = h.oid_regi
                 AND c.clie_oid_clie = i.clie_oid_clie
                 AND c.ind_acti = 1
                 AND d.ind_borr = 0
                 AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
                 AND c.clie_oid_clie = cons.clie_oid_clie
                 AND cons.perd_oid_peri =
                     nvl(a.oid_peri, cons.perd_oid_peri) --periodo
                 AND c.clie_oid_clie = nvl(a.clie_oid_clie, c.clie_oid_clie) --cliente
                 AND i.ticl_oid_tipo_clie =
                     nvl(a.oid_tipo_clie, i.ticl_oid_tipo_clie) --tipo
                 AND i.sbti_oid_subt_clie =
                     nvl(a.oid_subt_clie, i.sbti_oid_subt_clie) --subtipo
                 AND j.tccl_oid_tipo_clasi =
                     nvl(a.oid_clas_clie, j.tccl_oid_tipo_clasi) --tipo clasif
                 AND j.clas_oid_clas = nvl(a.oid_clas_clie, j.clas_oid_clas) --clasif
                 AND h.oid_regi = nvl(a.oid_regi, h.oid_regi) --region
                 AND g.oid_zona = nvl(a.oid_zona, g.oid_zona) --zon
                 AND a.ind_acti = '1'
                 AND a.ind_tipo_bloq = '1' -- Que sea de tipo EXCEPCION
                    -- Que sea Orden de compra
                 AND a.cod_tipo_docu = 'OCC'
                    -- Que sean de Bloqueo por control
                 AND a.val_moti_bloq = 'C');

    -- Listado Solicitudes sin bloqueos
    CURSOR c_bloqucontr2 IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND NOT EXISTS
       (SELECT 1
                FROM mae_clien_unida_admin c,
                     zon_terri_admin       d,
                     zon_terri             e,
                     zon_secci             f,
                     zon_zona              g,
                     zon_regio             h,
                     mae_clien_tipo_subti  i,
                     mae_clien_clasi       j,
                     sto_bloqu_contr       a
               WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
                 AND d.zscc_oid_secc = f.oid_secc
                 AND d.terr_oid_terr = e.oid_terr
                 AND f.zzon_oid_zona = g.oid_zona
                 AND g.zorg_oid_regi = h.oid_regi
                 AND c.clie_oid_clie = i.clie_oid_clie
                 AND c.ind_acti = 1
                 AND d.ind_borr = 0
                 AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
                 AND c.clie_oid_clie = cons.clie_oid_clie
                 AND cons.perd_oid_peri =
                     nvl(a.oid_peri, cons.perd_oid_peri) --periodo
                 AND c.clie_oid_clie = nvl(a.clie_oid_clie, c.clie_oid_clie) --cliente
                 AND i.ticl_oid_tipo_clie =
                     nvl(a.oid_tipo_clie, i.ticl_oid_tipo_clie) --tipo
                 AND i.sbti_oid_subt_clie =
                     nvl(a.oid_subt_clie, i.sbti_oid_subt_clie) --subtipo
                 AND j.tccl_oid_tipo_clasi =
                     nvl(a.oid_clas_clie, j.tccl_oid_tipo_clasi) --tipo clasif
                 AND j.clas_oid_clas = nvl(a.oid_clas_clie, j.clas_oid_clas) --clasif
                 AND h.oid_regi = nvl(a.oid_regi, h.oid_regi) --region
                 AND g.oid_zona = nvl(a.oid_zona, g.oid_zona) --zon
                 AND a.ind_acti = '1'
                 AND a.ind_tipo_bloq = '0' -- Tipo BLOQUEO
                    -- Que sea orden de compra
                 AND a.cod_tipo_docu = 'OCC'
                    -- Que sean de Bloqueo por control
                 AND a.val_moti_bloq = 'C');

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    v_numlote_excep       t_numlote;
    v_sec_nume_docu_excep t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;
    j       BINARY_INTEGER := 0;
    k       BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    --
    OPEN c_bloqucontr2_excep;
    LOOP
      FETCH c_bloqucontr2_excep BULK COLLECT
        INTO v_numlote_excep,
             v_sec_nume_docu_excep LIMIT w_filas;

      IF v_numlote_excep.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL i IN 1 .. v_numlote_excep.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote_excep(i)
             AND occ.sec_nume_docu = v_sec_nume_docu_excep(i);

      END IF;

      EXIT WHEN c_bloqucontr2_excep%NOTFOUND;
    END LOOP;
    CLOSE c_bloqucontr2_excep;
    --

    OPEN c_bloqucontr2;
    LOOP
      FETCH c_bloqucontr2 BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_numlote.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL k IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(k)
             AND occ.sec_nume_docu = v_sec_nume_docu(k);

      END IF;

      EXIT WHEN c_bloqucontr2%NOTFOUND;
    END LOOP;
    CLOSE c_bloqucontr2;

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
                              'ERROR STO_PR_OCC_BLOQU_CONTR2: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_bloqu_contr2;

  /***************************************************************************
  Descripcion       : Validacion de Bloqueo por control 2
  Fecha Creacion    : 07/06/2010
  Autor             : Jesse Rios Franco
  ***************************************************************************/
  PROCEDURE sto_pr_occ_bloqu_contr3
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    -- Listado Solicitudes con excepcion
    CURSOR c_bloqueo IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.clie_oid_clie,
             cons.perd_oid_peri
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_num_lote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_perd_oid_peri IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;

    v_num_lote      t_num_lote;
    v_sec_nume_docu t_sec_nume_docu;
    v_clie_oid_clie t_clie_oid_clie;
    v_perd_oid_peri t_perd_oid_peri;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    i       BINARY_INTEGER := 0;

    lnnumregistros NUMBER(10);
    lbbloqueado    BOOLEAN;
  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    --
    OPEN c_bloqueo;
    LOOP
      FETCH c_bloqueo BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_clie_oid_clie,
             v_perd_oid_peri LIMIT w_filas;

      IF v_num_lote.count > 0 THEN

        FOR i IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          lbbloqueado := FALSE;

          SELECT COUNT(1)
            INTO lnnumregistros
            FROM mae_clien_unida_admin c,
                 zon_terri_admin       d,
                 zon_terri             e,
                 zon_secci             f,
                 zon_zona              g,
                 zon_regio             h,
                 mae_clien_tipo_subti  i,
                 mae_clien_clasi       j,
                 sto_bloqu_contr       a
           WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
             AND d.zscc_oid_secc = f.oid_secc
             AND d.terr_oid_terr = e.oid_terr
             AND f.zzon_oid_zona = g.oid_zona
             AND g.zorg_oid_regi = h.oid_regi
             AND c.clie_oid_clie = i.clie_oid_clie
             AND c.ind_acti = 1
             AND d.ind_borr = 0
             AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
             AND c.clie_oid_clie = v_clie_oid_clie(i)
             AND (v_perd_oid_peri(i) = a.oid_peri OR a.oid_peri IS NULL) --periodo
             AND (c.clie_oid_clie = a.clie_oid_clie OR
                 a.clie_oid_clie IS NULL) --cliente
             AND (i.ticl_oid_tipo_clie = a.oid_tipo_clie OR
                 a.oid_tipo_clie IS NULL) --tipo
             AND (i.sbti_oid_subt_clie = a.oid_subt_clie OR
                 a.oid_subt_clie IS NULL) --subtipo
             AND (j.tccl_oid_tipo_clasi = a.oid_clas_clie OR
                 a.oid_clas_clie IS NULL) --tipo clasif
             AND (j.clas_oid_clas = a.oid_clas_clie OR
                 a.oid_clas_clie IS NULL) --clasif
             AND (h.oid_regi = a.oid_regi OR a.oid_regi IS NULL) --region
             AND (g.oid_zona = a.oid_zona OR a.oid_zona IS NULL) --zon
             AND a.ind_acti = '1'
             AND a.ind_tipo_bloq = '1' -- Que sea de tipo EXCEPCION
             AND a.cod_tipo_docu = pscodigotipodoc
             AND a.val_moti_bloq = 'C';

          IF lnnumregistros = 0 THEN

            SELECT COUNT(1)
              INTO lnnumregistros
              FROM mae_clien_unida_admin c,
                   zon_terri_admin       d,
                   zon_terri             e,
                   zon_secci             f,
                   zon_zona              g,
                   zon_regio             h,
                   mae_clien_tipo_subti  i,
                   mae_clien_clasi       j,
                   sto_bloqu_contr       a
             WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
               AND d.zscc_oid_secc = f.oid_secc
               AND d.terr_oid_terr = e.oid_terr
               AND f.zzon_oid_zona = g.oid_zona
               AND g.zorg_oid_regi = h.oid_regi
               AND c.clie_oid_clie = i.clie_oid_clie
               AND c.ind_acti = 1
               AND d.ind_borr = 0
               AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
               AND c.clie_oid_clie = v_clie_oid_clie(i)
               AND (v_perd_oid_peri(i) = a.oid_peri OR a.oid_peri IS NULL) --periodo
               AND (c.clie_oid_clie = a.clie_oid_clie OR
                   a.clie_oid_clie IS NULL) --cliente
               AND (i.ticl_oid_tipo_clie = a.oid_tipo_clie OR
                   a.oid_tipo_clie IS NULL) --tipo
               AND (i.sbti_oid_subt_clie = a.oid_subt_clie OR
                   a.oid_subt_clie IS NULL) --subtipo
               AND (j.tccl_oid_tipo_clasi = a.oid_clas_clie OR
                   a.oid_clas_clie IS NULL) --tipo clasif
               AND (j.clas_oid_clas = a.oid_clas_clie OR
                   a.oid_clas_clie IS NULL) --clasif
               AND (h.oid_regi = a.oid_regi OR a.oid_regi IS NULL) --region
               AND (g.oid_zona = a.oid_zona OR a.oid_zona IS NULL) --zon
               AND a.ind_acti = '1'
               AND a.ind_tipo_bloq = '0'
               AND a.cod_tipo_docu = pscodigotipodoc
               AND a.val_moti_bloq = 'C';
            IF lnnumregistros > 0 THEN
              lbbloqueado := TRUE;
            END IF;
          END IF;

          IF NOT lbbloqueado THEN
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

      EXIT WHEN c_bloqueo%NOTFOUND;
    END LOOP;
    CLOSE c_bloqueo;

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
                              'ERROR STO_PR_OCC_BLOQU_CONTR3: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_bloqu_contr3;

  /***************************************************************************
  Descripcion       : Validacion de Nueva en Segundo Dia
  Fecha Creacion    : 24/08/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_nueva_sedia
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_unidadmaxima IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.esta_oid_esta_clie,
             cons.perd_oid_peri,
             cons.zzon_oid_zona
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         order by cons.zzon_oid_zona
         ;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_esta_oid_esta_clie IS TABLE OF int_solic_conso_cabec.esta_oid_esta_clie%TYPE;
    TYPE t_perd_oid_peri IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;
    TYPE t_zzon_oid_zona IS TABLE OF int_solic_conso_cabec.zzon_oid_zona%TYPE;

    v_numlote            t_numlote;
    v_sec_nume_docu      t_sec_nume_docu;
    v_esta_oid_esta_clie t_esta_oid_esta_clie;
    v_perd_oid_peri      t_perd_oid_peri;
    v_zzon_oid_zona      t_zzon_oid_zona;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez
    j       BINARY_INTEGER := 0;

    remesa NUMBER := 0;

    tmp NUMBER := 0;

    zonaant NUMBER := 0;

    isok BOOLEAN;

    lsnumerodias NUMBER := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,'STO_NUM_DIAS_NUEVA'),3);

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_unidadmaxima;
    LOOP
      FETCH c_unidadmaxima BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_esta_oid_esta_clie,
             v_perd_oid_peri,
             v_zzon_oid_zona
             LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          if v_zzon_oid_zona(j)<>zonaant then
            select nvl(max(ind_comp),0) into remesa from ped_solic_cabec a, ped_segui_pedid b
            where a.zzon_oid_zona=v_zzon_oid_zona(j) and a.perd_oid_peri=v_perd_oid_peri(j)
            and a.oid_soli_cabe=b.soca_oid_soli_cabe;
          end if;

          begin

          select count(1) into tmp from sto_param_gener_occrr x
          where x.val_param=to_char(v_esta_oid_esta_clie(j))
          and x.cod_para like 'STO_RECH_SDIA%';

          exception when others then
              tmp := 0;
          end;


          isok := TRUE;
          IF tmp>0
            AND remesa+1 > lsnumerodias
          THEN
            isok := FALSE;
          END IF;

          -- Actualizamos Documentos Validados OK
          IF (isok) THEN

            UPDATE sto_docum_digit occ
               SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = pscodigopais
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

          zonaant := v_zzon_oid_zona(j);

        END LOOP;
      END IF;
      EXIT WHEN c_unidadmaxima%NOTFOUND;

    END LOOP;
    CLOSE c_unidadmaxima;

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
                              'ERROR sto_pr_occ_nueva_sedia: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_nueva_sedia;

  /***************************************************************************
  Descripcion       : Validacion Dummy utilizada por la consolidacion de
                      pedidos, con el fin de aparecer en la pantalla
                      de gestion
  Fecha Creacion    : 07/10/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_conso_clien_dummy
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_unidadmaxima IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_unidadmaxima;
    LOOP
      FETCH c_unidadmaxima BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        END LOOP;
      END IF;
      EXIT WHEN c_unidadmaxima%NOTFOUND;

    END LOOP;
    CLOSE c_unidadmaxima;

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
                              'ERROR sto_pr_occ_conso_clien_dummy: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_conso_clien_dummy;

  /***************************************************************************
  Descripcion       : Validacion de Zonas de Arribo
  Fecha Creacion    : 08/11/2010
  Autor             : Dennys Oliva Iriarte
  ***************************************************************************/
  PROCEDURE sto_pr_occ_zonas_arrib
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_zonas IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_zona = cons.cod_zona_arri;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_zonas;
    LOOP
      FETCH c_zonas BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_zonas%NOTFOUND;
    END LOOP;
    CLOSE c_zonas;

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
                              'ERROR sto_pr_occ_zonas_arrib: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_zonas_arrib;

  /***************************************************************************
  Descripcion       : Validacion de Rechazo por OCR 2
  Fecha Creacion    : 28/12/2010
  Autor             : Christian Gonzales
  ***************************************************************************/

  PROCEDURE sto_pr_occ_recha_ocr2
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND cons.ind_ocs_proc = '0'
         AND cons.ind_erro_rech = '0'
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualiza los Indicadores de Validacion
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

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
                              'ERROR STO_PR_OCC_RECHA_OCR2: ' || ls_sqlerrm);

  END sto_pr_occ_recha_ocr2;

  /***************************************************************************
  Descripcion       : Validacion de Micas
  Fecha Creacion    : 28/12/2010
  Autor             : Christian Gonzales
  ***************************************************************************/

  PROCEDURE sto_pr_occ_micas
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND cons.ind_ocs_proc = '0'
         AND cons.ind_orig_cabe = '1'
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;

    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualiza los Indicadores de Validacion
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

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
                              'ERROR STO_PR_OCC_MICAS: ' || ls_sqlerrm);

  END sto_pr_occ_micas;

  /***************************************************************************
    Descripcion     : Validacion de Tipo de documento invalido
                      para Orden de compra Cabecera
  Fecha Creacion    : 28/12/2010
  Autor             : Nicolas Lopez Ramos
   ***************************************************************************/
  PROCEDURE sto_pr_occ_tipo_docum
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_tipodocumento IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM seg_pais              pais,
             mae_clien             cli,
             mae_clien_tipo_subti  cts,
             int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cts.clie_oid_clie = cli.oid_clie
         AND pais.oid_pais = cli.pais_oid_pais
         AND cli.cod_clie = cons.cod_clie
         AND cts.ticl_oid_tipo_clie = 2
         AND NOT EXISTS
       (SELECT 1
                FROM mae_clien_ident mci,
                     mae_tipo_docum  mtd
               WHERE mci.tdoc_oid_tipo_docu = mtd.oid_tipo_docu
                 AND mci.val_iden_docu_prin = 1
                 AND mtd.ind_doc_iden_fisc = 0
                 AND mci.clie_oid_clie = cli.oid_clie);

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    OPEN c_tipodocumento;
    LOOP

      FETCH c_tipodocumento BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;

      EXIT WHEN c_tipodocumento%NOTFOUND;

    END LOOP;
    CLOSE c_tipodocumento;

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
                              'ERROR STO_PR_OCC_TIPO_DOCUM: ' || ls_sqlerrm);

  END sto_pr_occ_tipo_docum;

  /***************************************************************************
  Descripcion       : Validacion de secuenciacion de Zonas y Territorios sin error
                      para Orden de compra Cabecera
  Fecha Creacion    : 26/01/2011
  Autor             : Nicolas Lopez
  ***************************************************************************/
  PROCEDURE sto_pr_occ_secue_zonter
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_secuenzonaterr(vdfecha DATE) IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.cod_zona NOT IN
             (SELECT b.cod_zona
                FROM cra_crono a,
                     zon_zona  b
               WHERE a.zzon_oid_zona = b.oid_zona
                 AND a.perd_oid_peri = cons.perd_oid_peri
                 AND a.cact_oid_acti IN
                     (SELECT a.oid_acti --  Consulta relacion de oid  de la  actividad  segun el pais.
                        FROM cra_activ           a,
                             gen_i18n_sicc_comun b,
                             seg_pais            sp
                       WHERE a.oid_acti = b.val_oid
                         AND cod_pais = pscodigopais
                         AND b.attr_enti = 'CRA_ACTIV'
                         AND a.cod_acti = 'FA'
                         AND sp.oid_pais = a.pais_oid_pais)
                 AND b.cod_zona IN
                     (SELECT zon_zona.cod_zona -- Zonas que no estan secuenciadas.
                        FROM zon_zona,
                             zon_secci,
                             zon_terri_admin,
                             zon_terri
                       WHERE zzon_oid_zona = oid_zona
                         AND zscc_oid_secc = oid_secc
                         AND zon_terri_admin.terr_oid_terr = oid_terr
                         AND zon_zona.ind_borr = 0
                         AND zon_secci.ind_borr = 0
                         AND zon_terri.ind_borr = 0
                         AND zon_terri_admin.ind_borr = 0
                         AND zon_zona.ind_acti = 1
                         AND zon_secci.ind_acti = 1
                         AND zon_terri.oid_terr NOT IN
                             (SELECT terr_oid_terr FROM app_rutas_terri))
                 AND a.fec_inic = vdfecha);

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    rows NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    ldfechafacturacion bas_ctrl_fact.fec_proc%TYPE;

  BEGIN

    -- Obtenemos la Fecha de Facturacion
    SELECT a.fec_proc
      INTO ldfechafacturacion
      FROM bas_ctrl_fact a
     WHERE a.ind_camp_act = 1
       AND a.sta_camp = 0;

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_secuenzonaterr(ldfechafacturacion);
    LOOP
      FETCH c_secuenzonaterr BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_numlote.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET -- ACTUALZIAMOS INDICADORES DE VALIDACION
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_secuenzonaterr%NOTFOUND;
    END LOOP;
    CLOSE c_secuenzonaterr;

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
                              'ERROR STO_PR_OCC_SECUE_ZONTER: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_secue_zonter;

  /***************************************************************************
    Descripcion       : Validacion de Cobro de Flete Adicional por pase fuera de fecha
    Fecha Creacion    : 12/04/2011
    Autor             : Sergio Buchelli
  ***************************************************************************/
  PROCEDURE sto_pr_occ_flete_adici_fuefe
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cobroflete IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.perd_oid_peri,
             cons.zzon_oid_zona,
             cons.clie_oid_clie,
             cons.pais_oid_pais
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_perd_oid_peri IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;
    TYPE t_zzon_oid_zona IS TABLE OF int_solic_conso_cabec.zzon_oid_zona%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_pais_oid_pais IS TABLE OF int_solic_conso_cabec.pais_oid_pais%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_perd_oid_peri t_perd_oid_peri;
    v_zzon_oid_zona t_zzon_oid_zona;
    v_clie_oid_clie t_clie_oid_clie;
    v_pais_oid_pais t_pais_oid_pais;

    rows NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

    ldfechafacturacion bas_ctrl_fact.fec_proc%TYPE;
    ldfechacronograma  bas_ctrl_fact.fec_proc%TYPE;

    --lsmontoparametro     sto_param_gener_occrr.val_param%TYPE;
    lsactividadparametro sto_param_gener_occrr.val_param%TYPE;

    lstipoclasi    sto_param_gener_occrr.val_param%TYPE;
    lstipoclasi_of sto_param_gener_occrr.val_param%TYPE;

    lnexiste NUMBER(3);

    ln_excepSF NUMBER(5);

  BEGIN

    /*lsmontoparametro := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
    'STO_MONTO_RECAR_FLET');*/

    lsactividadparametro := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                 'STO_ACTIV_RECAR_FLET');

    lstipoclasi := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                        'STO_CLASI_RECAR_FLET');

    lstipoclasi_of := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                           'STO_CLASI_REC_FLE_OF');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    -- Consultamos si la consultora tiene la clasificacion

    -- Obtenemos la Fecha de Facturacion
    SELECT a.fec_proc
      INTO ldfechafacturacion
      FROM bas_ctrl_fact a
     WHERE a.ind_camp_act = 1
       AND a.sta_camp = 0;

    OPEN c_cobroflete;
    LOOP
      FETCH c_cobroflete BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu,
             v_perd_oid_peri,
             v_zzon_oid_zona,
             v_clie_oid_clie,
             v_pais_oid_pais LIMIT rows;

      IF v_numlote.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          --obteniendo la fecha de cronograma

          IF lsactividadparametro IS NOT NULL THEN

            BEGIN
              SELECT fec_inic
                INTO ldfechacronograma
                FROM cra_crono
               WHERE perd_oid_peri = v_perd_oid_peri(j)
                 AND zzon_oid_zona = v_zzon_oid_zona(j)
                 AND cact_oid_acti =
                     (SELECT oid_acti
                        FROM cra_activ
                       WHERE cod_acti = lsactividadparametro
                         AND pais_oid_pais = v_pais_oid_pais(j));
            EXCEPTION
              WHEN OTHERS THEN
                ldfechacronograma := ldfechacronograma;
            END;

            IF (ldfechafacturacion > ldfechacronograma) THEN

               select count(1) into ln_excepSF from
               mae_exenc_sobre_flete x, mae_clasi cl, mae_tipo_clasi_clien tcl
               ,mae_tipo_clien tcli, mae_subti_clien scl, mae_clien_tipo_subti y
               , mae_clien_clasi z
               where x.cod_tipo_clie=tcli.cod_tipo_clie
               and x.cod_subt_clie=scl.cod_subt_clie
               and x.cod_tipo_clas=tcl.cod_tipo_clas
               and x.cod_clas=cl.cod_clas
               and tcli.oid_tipo_clie=y.ticl_oid_tipo_clie
               and scl.oid_subt_clie=y.sbti_oid_subt_clie
               and tcl.oid_tipo_clas=z.tccl_oid_tipo_clasi
               and cl.oid_clas=z.clas_oid_clas
               and y.oid_clie_tipo_subt=z.ctsu_oid_clie_tipo_subt
               and y.clie_oid_clie=v_clie_oid_clie(j);

              if ln_excepSF=0 then

              UPDATE int_solic_conso_cabec
                 SET val_reca_flet = 1
               WHERE sec_nume_docu = v_sec_nume_docu(j)
                 AND num_lote = v_numlote(j);
              end if;
            ELSE
              UPDATE int_solic_conso_cabec
                 SET val_reca_flet = NULL
               WHERE sec_nume_docu = v_sec_nume_docu(j)
                 AND num_lote = v_numlote(j);

            END IF;

          END IF;

          IF lstipoclasi IS NOT NULL THEN

            SELECT COUNT(1)
              INTO lnexiste
              FROM mae_clien_tipo_subti a1,
                   mae_clien_clasi      b1
             WHERE a1.oid_clie_tipo_subt = b1.ctsu_oid_clie_tipo_subt
               AND a1.clie_oid_clie = v_clie_oid_clie(j)
               AND b1.tccl_oid_tipo_clasi IN (lstipoclasi, lstipoclasi_of);

            IF lnexiste > 0 THEN
              UPDATE int_solic_conso_cabec
                 SET val_reca_flet = 1
               WHERE sec_nume_docu = v_sec_nume_docu(j)
                 AND num_lote = v_numlote(j);
            END IF;

          END IF;

          UPDATE sto_docum_digit occ
             SET -- ACTUALZIAMOS INDICADORES DE VALIDACION
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        END LOOP;

      END IF;
      EXIT WHEN c_cobroflete%NOTFOUND;
    END LOOP;
    CLOSE c_cobroflete;

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
                              'ERROR STO_PR_OCC_FLETE_ADICI_FUEFE: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_flete_adici_fuefe;

  /**************************************************************************
  Descripcion       : STO_PR_OCC_PREMI_CORXP
                    Procedimiento de Validacion para ejecucion del programa
                    de Despacho de Premios del Concurso RxP
  Fecha Creacion    : 11/07/2011
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE sto_pr_occ_premi_corxp
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_cabe_valid IS
      SELECT occ.num_lote,
             occ.sec_nume_docu
        FROM sto_proce_docum_digit occ
       WHERE occ.num_proc = psnumeroproceso
         AND occ.cod_tipo_docu = pscodigotipodoc;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    j    BINARY_INTEGER := 0;

    lscodigoperiodo seg_perio_corpo.cod_peri%TYPE;
  BEGIN

    SELECT cod_peri
      INTO lscodigoperiodo
      FROM bas_ctrl_fact c
     WHERE c.sta_camp = '0'
       AND c.ind_camp_act = '1';

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    /* Invocacion al programa de Despacho Premios Concursos RxP */
    inc_pkg_proce_incen.inc_pr_despa_premi_corxp(pscodigopais,
                                                 lscodigoperiodo,
                                                 psusuario,
                                                 pscodigotipodoc,
                                                 psnumeroproceso);

    /* Inserta en STO los detalles agregados por el programa */
    sto_pkg_proce_gener.sto_pr_carga_sto_valid_carga(pscodigopais,
                                                     pscodigotipodoc,
                                                     psusuario,
                                                     psnumeroproceso);

    OPEN c_cabe_valid;
    LOOP
      FETCH c_cabe_valid BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_numlote.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FORALL j IN 1 .. v_numlote.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_cabe_valid%NOTFOUND;
    END LOOP;
    CLOSE c_cabe_valid;

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
                              'ERROR STO_PR_OCC_PREMI_CORXP: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_premi_corxp;

  /**************************************************************************
  Descripcion       : Valida si una consultara esta habilitada papra pedido
                      online
  Fecha Creacion    : 11/10/2011
  Autor             : Jose Luis Rodriguez
  ***************************************************************************/
  FUNCTION sto_fn_valid_bloqu_onlin
  (
    pscodigoperiodo VARCHAR2,
    pscodigocliente VARCHAR2
  ) RETURN NUMBER IS

    lnoidcliente NUMBER;
    lnoidperiodo NUMBER;
    lnaux        NUMBER := 0;
  BEGIN

    lnoidcliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(pscodigocliente);
    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

    -- 1 ----Verifica si esta Deshabilitada -----
    -- 1 ----Verifica si esta Deshabilitada -----

    SELECT COUNT(1)
      INTO lnaux
      FROM mae_clien_unida_admin c,
           zon_terri_admin       d,
           zon_terri             e,
           zon_secci             f,
           zon_zona              g,
           zon_regio             h,
           mae_clien_tipo_subti  i,
           mae_clien_clasi       j,
           sto_bloqu_contr       a
     WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
       AND d.zscc_oid_secc = f.oid_secc
       AND d.terr_oid_terr = e.oid_terr
       AND f.zzon_oid_zona = g.oid_zona
       AND g.zorg_oid_regi = h.oid_regi
       AND c.clie_oid_clie = i.clie_oid_clie
       AND c.ind_acti = 1
       AND d.ind_borr = 0
       AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
       AND c.clie_oid_clie = lnoidcliente
       AND lnoidperiodo = nvl(a.oid_peri, lnoidperiodo) --periodo
       AND c.clie_oid_clie = nvl(a.clie_oid_clie, c.clie_oid_clie) --cliente
       AND i.ticl_oid_tipo_clie =
           nvl(a.oid_tipo_clie, i.ticl_oid_tipo_clie) --tipo
       AND i.sbti_oid_subt_clie =
           nvl(a.oid_subt_clie, i.sbti_oid_subt_clie) --subtipo
       AND j.tccl_oid_tipo_clasi =
           nvl(a.oid_clas_clie, j.tccl_oid_tipo_clasi) --tipo clasif
       AND j.clas_oid_clas = nvl(a.oid_clas_clie, j.clas_oid_clas) --clasif
       AND h.oid_regi = nvl(a.oid_regi, h.oid_regi) --region
       AND g.oid_zona = nvl(a.oid_zona, g.oid_zona) --zon
       AND a.ind_acti = '1'
       AND a.ind_tipo_bloq = '1' -- Que sea de tipo DESHABILITADO
       AND a.cod_tipo_docu = 'OCC'
       AND a.val_moti_bloq = 'O';

    IF (lnaux > 0) THEN
      RETURN 0;
    END IF;

    -- 2 ----Verifica si esta Habilitada -----
    -- 2 ----Verifica si esta Habilitada -----

    SELECT COUNT(1)
      INTO lnaux
      FROM mae_clien_unida_admin c,
           zon_terri_admin       d,
           zon_terri             e,
           zon_secci             f,
           zon_zona              g,
           zon_regio             h,
           mae_clien_tipo_subti  i,
           mae_clien_clasi       j,
           sto_bloqu_contr       a
     WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
       AND d.zscc_oid_secc = f.oid_secc
       AND d.terr_oid_terr = e.oid_terr
       AND f.zzon_oid_zona = g.oid_zona
       AND g.zorg_oid_regi = h.oid_regi
       AND c.clie_oid_clie = i.clie_oid_clie
       AND c.ind_acti = 1
       AND d.ind_borr = 0
       AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
       AND c.clie_oid_clie = lnoidcliente
       AND lnoidperiodo = nvl(a.oid_peri, lnoidperiodo) --periodo
       AND c.clie_oid_clie = nvl(a.clie_oid_clie, c.clie_oid_clie) --cliente
       AND i.ticl_oid_tipo_clie =
           nvl(a.oid_tipo_clie, i.ticl_oid_tipo_clie) --tipo
       AND i.sbti_oid_subt_clie =
           nvl(a.oid_subt_clie, i.sbti_oid_subt_clie) --subtipo
       AND j.tccl_oid_tipo_clasi =
           nvl(a.oid_clas_clie, j.tccl_oid_tipo_clasi) --tipo clasif
       AND j.clas_oid_clas = nvl(a.oid_clas_clie, j.clas_oid_clas) --clasif
       AND h.oid_regi = nvl(a.oid_regi, h.oid_regi) --region
       AND g.oid_zona = nvl(a.oid_zona, g.oid_zona) --zon
       AND a.ind_acti = '1'
       AND a.ind_tipo_bloq = '0' -- Que sea Tipo Habilitado
       AND a.cod_tipo_docu = 'OCC'
       AND a.val_moti_bloq = 'O';

    RETURN lnaux;

  EXCEPTION
    WHEN OTHERS THEN
      raise_application_error(-20123,
                              'ERROR sto_fn_valid_bloqu_onlin: ' ||
                              ls_sqlerrm);
  END sto_fn_valid_bloqu_onlin;
  /**************************************************************************
  Descripcion       : Valida si una consultara esta habilitada papra pedido
                      adicional
  Fecha Creacion    : 07/03/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION sto_fn_valid_fad
  (
    pnoidperiodo VARCHAR2,
    pnoidcliente VARCHAR2
  ) RETURN NUMBER IS

    lnaux NUMBER := 0;
  BEGIN

    --lnoidcliente := gen_pkg_gener.gen_fn_devuelve_id_cliente(pscodigocliente);
    --lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodigoperiodo);

    -- 1 ----Verifica si esta Deshabilitada -----
    -- 1 ----Verifica si esta Deshabilitada -----

    SELECT COUNT(1)
      INTO lnaux
      FROM mae_clien_unida_admin c,
           zon_terri_admin       d,
           zon_terri             e,
           zon_secci             f,
           zon_zona              g,
           zon_regio             h,
           mae_clien_tipo_subti  i,
           mae_clien_clasi       j,
           sto_factu_adici       a
     WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
       AND d.zscc_oid_secc = f.oid_secc
       AND d.terr_oid_terr = e.oid_terr
       AND f.zzon_oid_zona = g.oid_zona
       AND g.zorg_oid_regi = h.oid_regi
       AND c.clie_oid_clie = i.clie_oid_clie
       AND c.ind_acti = 1
       AND d.ind_borr = 0
       AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
       AND c.clie_oid_clie = pnoidcliente
       AND pnoidperiodo = nvl(a.oid_peri, pnoidperiodo) --periodo
       AND c.clie_oid_clie = nvl(a.oid_clie, c.clie_oid_clie) --cliente
       AND i.ticl_oid_tipo_clie =
           nvl(a.oid_tipo_clie, i.ticl_oid_tipo_clie) --tipo
       AND i.sbti_oid_subt_clie =
           nvl(a.oid_subt_clie, i.sbti_oid_subt_clie) --subtipo
       AND j.tccl_oid_tipo_clasi =
           nvl(a.oid_tipo_clas, j.tccl_oid_tipo_clasi) --tipo clasif
       AND j.clas_oid_clas = nvl(a.oid_clas, j.clas_oid_clas) --clasif
       AND h.oid_regi = nvl(a.oid_regi, h.oid_regi) --region
       AND g.oid_zona = nvl(a.oid_zona, g.oid_zona) --zon
       AND a.ind_acti = '1'
    --AND a.ind_tipo_bloq = '1' -- Que sea de tipo DESHABILITADO
    --AND a.cod_tipo_docu = 'OCC'
    --AND a.val_moti_bloq = 'O'
    ;

    RETURN lnaux;

  EXCEPTION
    WHEN OTHERS THEN
      raise_application_error(-20123,
                              'ERROR sto_fn_valid_fad: ' || ls_sqlerrm);
  END sto_fn_valid_fad;
  /**************************************************************************
  Descripcion       : STO_PR_OCC_PREMI_CORXP
                    Procedimiento de Validacion para ejecucion del programa
                    de Despacho de Premios del Concurso RxP
  Fecha Creacion    : 11/07/2011
  Autor             : Sergio Apaza
  ***************************************************************************/
  PROCEDURE sto_pr_occ_marca_prol IS
    CURSOR c_marca_prol IS
      SELECT oid_clie,
             ind_prol
        FROM gtt_mae_ind_prol occ;

    TYPE t_oid_clie IS TABLE OF gtt_mae_ind_prol.oid_clie%TYPE;
    TYPE t_ind_prol IS TABLE OF gtt_mae_ind_prol.ind_prol%TYPE;

    lnoidperiodo NUMBER;

    v_oid_clie t_oid_clie;
    v_ind_prol t_ind_prol;

    rows            NATURAL := 1000; -- Numero de filas a procesar cada vez
    j               BINARY_INTEGER := 0;
    w_ind_prol      VARCHAR2(1 BYTE);
    lscodigoperiodo seg_perio_corpo.cod_peri%TYPE;

  BEGIN
    SELECT a.cod_peri
      INTO lscodigoperiodo
      FROM bas_ctrl_fact a
     WHERE a.ind_camp_act = '1'
       AND a.sta_camp = '0';

    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lscodigoperiodo);

    ---- Inserta los registros deshabilidatos y habilitados
    INSERT INTO gtt_mae_ind_prol
      SELECT DISTINCT clie_oid_clie,
                      MIN(ind_prol)
        FROM (SELECT DISTINCT c.clie_oid_clie,
                              '0' ind_prol
                FROM mae_clien_unida_admin c,
                     zon_terri_admin       d,
                     zon_terri             e,
                     zon_secci             f,
                     zon_zona              g,
                     zon_regio             h,
                     mae_clien_tipo_subti  i,
                     mae_clien_clasi       j,
                     sto_bloqu_contr       a
               WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
                 AND d.zscc_oid_secc = f.oid_secc
                 AND d.terr_oid_terr = e.oid_terr
                 AND f.zzon_oid_zona = g.oid_zona
                 AND g.zorg_oid_regi = h.oid_regi
                 AND c.clie_oid_clie = i.clie_oid_clie
                 AND c.ind_acti = 1
                 AND d.ind_borr = 0
                 AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
                 AND lnoidperiodo = nvl(a.oid_peri, lnoidperiodo) --periodo
                 AND c.clie_oid_clie = nvl(a.clie_oid_clie, c.clie_oid_clie) --cliente
                 AND i.ticl_oid_tipo_clie =
                     nvl(a.oid_tipo_clie, i.ticl_oid_tipo_clie) --tipo
                 AND i.sbti_oid_subt_clie =
                     nvl(a.oid_subt_clie, i.sbti_oid_subt_clie) --subtipo
                 AND j.tccl_oid_tipo_clasi =
                     nvl(a.oid_clas_clie, j.tccl_oid_tipo_clasi) --tipo clasif
                 AND j.clas_oid_clas = nvl(a.oid_clas_clie, j.clas_oid_clas) --clasif
                 AND h.oid_regi = nvl(a.oid_regi, h.oid_regi) --region
                 AND g.oid_zona = nvl(a.oid_zona, g.oid_zona) --zon
                 AND a.ind_acti = '1'
                 AND a.ind_tipo_bloq = '1' -- Que sea de tipo DESHABILITADO
                 AND a.cod_tipo_docu = 'OCC'
                 AND a.val_moti_bloq = 'O'
              UNION
              SELECT DISTINCT c.clie_oid_clie,
                              '1' ind_prol
                FROM mae_clien_unida_admin c,
                     zon_terri_admin       d,
                     zon_terri             e,
                     zon_secci             f,
                     zon_zona              g,
                     zon_regio             h,
                     mae_clien_tipo_subti  i,
                     mae_clien_clasi       j,
                     sto_bloqu_contr       a
               WHERE c.ztad_oid_terr_admi = d.oid_terr_admi
                 AND d.zscc_oid_secc = f.oid_secc
                 AND d.terr_oid_terr = e.oid_terr
                 AND f.zzon_oid_zona = g.oid_zona
                 AND g.zorg_oid_regi = h.oid_regi
                 AND c.clie_oid_clie = i.clie_oid_clie
                 AND c.ind_acti = 1
                 AND d.ind_borr = 0
                 AND i.oid_clie_tipo_subt = j.ctsu_oid_clie_tipo_subt
                 AND lnoidperiodo = nvl(a.oid_peri, lnoidperiodo) --periodo
                 AND c.clie_oid_clie = nvl(a.clie_oid_clie, c.clie_oid_clie) --cliente
                 AND i.ticl_oid_tipo_clie =
                     nvl(a.oid_tipo_clie, i.ticl_oid_tipo_clie) --tipo
                 AND i.sbti_oid_subt_clie =
                     nvl(a.oid_subt_clie, i.sbti_oid_subt_clie) --subtipo
                 AND j.tccl_oid_tipo_clasi =
                     nvl(a.oid_clas_clie, j.tccl_oid_tipo_clasi) --tipo clasif
                 AND j.clas_oid_clas = nvl(a.oid_clas_clie, j.clas_oid_clas) --clasif
                 AND h.oid_regi = nvl(a.oid_regi, h.oid_regi) --region
                 AND g.oid_zona = nvl(a.oid_zona, g.oid_zona) --zon
                 AND a.ind_acti = '1'
                 AND a.ind_tipo_bloq = '0' -- Que sea de tipo DESHABILITADO
                 AND a.cod_tipo_docu = 'OCC'
                 AND a.val_moti_bloq = 'O')
       GROUP BY clie_oid_clie;

    ---- Inserta los registros Habilitados que hay que deshabilitar
    INSERT INTO gtt_mae_ind_prol
      SELECT a.oid_clie,
             '0'
        FROM mae_clien a
       WHERE NOT EXISTS (SELECT NULL
                FROM gtt_mae_ind_prol b
               WHERE b.oid_clie = a.oid_clie)
         AND a.ind_prol = '1';

    OPEN c_marca_prol;
    LOOP
      FETCH c_marca_prol BULK COLLECT
        INTO v_oid_clie,
             v_ind_prol LIMIT rows;

      IF v_oid_clie.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_oid_clie.first .. v_oid_clie.last
        LOOP

          SELECT nvl(ind_prol, 0)
            INTO w_ind_prol
            FROM mae_clien
           WHERE oid_clie = v_oid_clie(j);

          --- Se actualiza teneindo en cuenta si esta en 0 o nullo
          IF ((v_ind_prol(j) = '0' AND w_ind_prol = '1') OR
             (v_ind_prol(j) = '1' AND w_ind_prol <> '1')) THEN

            UPDATE mae_clien mae
               SET mae.ind_prol = v_ind_prol(j)
             WHERE oid_clie = v_oid_clie(j);

          END IF;
        END LOOP;

      END IF;
      EXIT WHEN c_marca_prol%NOTFOUND;
    END LOOP;
    CLOSE c_marca_prol;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);

      raise_application_error(-20123,
                              'ERROR sto_pr_occ_marca_PROL: ' || ls_sqlerrm);

  END sto_pr_occ_marca_prol;

  /***************************************************************************
    Descripcion       : Validacion de Error en Configuracion de Matriz
    Fecha Creacion    : 02/07/2012
    Autor             : Jorge Velasquez
  ***************************************************************************/
  PROCEDURE sto_pr_occ_confi_matri
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    lsactprccont VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                      'STO_ACT_PREC_CONT');

    lsactprccont2 VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'STO_ACT_PREC_CONT2');

    CURSOR c_periodos IS
      SELECT t.cod_peri
        FROM sto_proce_docum_digit t
       WHERE t.num_proc = psnumeroproceso
         AND t.cod_tipo_docu = pscodigotipodoc
         AND t.cod_pais = pscodigopais
       GROUP BY t.cod_peri;

    TYPE t_gcod_peri IS TABLE OF int_solic_conso_detal.cod_peri%TYPE;

    v_gcod_peri t_gcod_peri;

    CURSOR c_matriz(vscodigoperiodo NUMBER) IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.num_proc = psnumeroproceso
         AND occ.cod_pais = pscodigopais
         AND cons.cod_peri = vscodigoperiodo;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    rows                   NATURAL := 1000; -- Numero de filas a procesar cada vez
    lncounterrornulos      NUMBER := 0;
    lncounterrorduplicados NUMBER := 0;
    lncounterrorice        NUMBER := 0;

    i BINARY_INTEGER := 0;
    j BINARY_INTEGER := 0;

    lnoidperiodo cra_perio.oid_peri%TYPE;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_periodos;
    LOOP
      FETCH c_periodos BULK COLLECT
        INTO v_gcod_peri LIMIT rows;

      IF v_gcod_peri.count > 0 THEN

        FOR j IN v_gcod_peri.first .. v_gcod_peri.last
        LOOP
          lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(v_gcod_peri(j));

          SELECT COUNT(1) --'NULOS'
            INTO lncounterrornulos
            FROM pre_matri_factu_cabec mfc,
                 pre_ofert             ofe,
                 pre_ofert_detal       ofedet,
                 pre_estra             es,
                 mae_produ             prod,
                 pre_catal             cat,
                 pre_tipo_ofert        tofe,
                 pre_estra             est
           WHERE mfc.oid_cabe = ofe.mfca_oid_cabe
             AND ofe.oid_ofer = ofedet.ofer_oid_ofer
             AND ofe.coes_oid_estr = es.oid_estr(+)
             AND ofedet.prod_oid_prod = prod.oid_prod
             AND mfc.perd_oid_peri = lnoidperiodo
             AND ofedet.ocat_oid_catal = cat.oid_cata(+)
             AND ofedet.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer(+)
             AND ofe.coes_oid_estr = est.oid_estr
             AND est.tipr_oid_tipo_prod = 1
             AND ofedet.val_codi_vent IS NULL;

          SELECT COUNT(1) --'DUPLICADOS'
            INTO lncounterrorduplicados
            FROM pre_matri_factu_cabec mfc,
                 pre_ofert ofe,
                 pre_ofert_detal ofedet,
                 pre_estra es,
                 mae_produ prod,
                 pre_catal cat,
                 pre_tipo_ofert tofe,
                 (SELECT v.val_oid,
                         v.val_i18n
                    FROM v_gen_i18n_sicc v
                   WHERE v.attr_enti = 'PRE_ESTRA'
                     AND v.idio_oid_idio = 1) i18est,
                 (SELECT pre_ofert_detal.val_codi_vent,
                         COUNT(1)
                    FROM pre_matri_factu_cabec,
                         pre_ofert_detal,
                         pre_ofert
                   WHERE pre_matri_factu_cabec.perd_oid_peri = lnoidperiodo
                     AND pre_matri_factu_cabec.oid_cabe =
                         pre_ofert.mfca_oid_cabe
                     AND pre_ofert_detal.ofer_oid_ofer = pre_ofert.oid_ofer
                   GROUP BY pre_ofert_detal.val_codi_vent
                  HAVING COUNT(1) > 1) t_val_codi
           WHERE ofedet.val_codi_vent = t_val_codi.val_codi_vent
             AND mfc.oid_cabe = ofe.mfca_oid_cabe
             AND ofe.oid_ofer = ofedet.ofer_oid_ofer
             AND ofe.coes_oid_estr = es.oid_estr(+)
             AND ofedet.prod_oid_prod = prod.oid_prod
             AND mfc.perd_oid_peri = lnoidperiodo
             AND ofedet.ocat_oid_catal = cat.oid_cata(+)
             AND ofedet.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer(+)
             AND i18est.val_oid(+) = ofe.coes_oid_estr;

          SELECT COUNT(1) --'ICE'
            INTO lncounterrorice
            FROM pre_matri_factu_cabec mfc,
                 pre_ofert ofe,
                 pre_ofert_detal ofedet,
                 pre_estra es,
                 mae_produ prod,
                 pre_catal cat,
                 pre_tipo_ofert tofe,
                 (SELECT z.prod_oid_prod,
                         z.val_impu_prod_naci,
                         MAX(fec_carg)
                    FROM int_impue_produ_nacio z
                   GROUP BY z.prod_oid_prod,
                            z.val_impu_prod_naci) ice,
                 (SELECT v.val_oid,
                         v.val_i18n
                    FROM v_gen_i18n_sicc v
                   WHERE v.attr_enti = 'PRE_ESTRA'
                     AND v.idio_oid_idio = 1) i18est
           WHERE mfc.oid_cabe = ofe.mfca_oid_cabe
             AND ofe.oid_ofer = ofedet.ofer_oid_ofer
             AND ofe.coes_oid_estr = es.oid_estr(+)
             AND ofedet.prod_oid_prod = prod.oid_prod
             AND mfc.perd_oid_peri = lnoidperiodo
             AND ofedet.ocat_oid_catal = cat.oid_cata(+)
             AND ofedet.tofe_oid_tipo_ofer = tofe.oid_tipo_ofer(+)
             AND i18est.val_oid(+) = ofe.coes_oid_estr
             AND ofedet.prod_oid_prod = ice.prod_oid_prod
             AND decode(ofedet.precio_unitario,
                        0,
                        ofedet.imp_prec_posi,
                        ofedet.precio_unitario) < ice.val_impu_prod_naci;

          IF lncounterrorduplicados = 0 AND lncounterrornulos = 0 AND
             lncounterrorice = 0 THEN
            OPEN c_matriz(v_gcod_peri(j));
            LOOP
              FETCH c_matriz BULK COLLECT
                INTO v_numlote,
                     v_sec_nume_docu LIMIT rows;

              -- Actualizamos Documentos Validados OK
              FORALL i IN 1 .. v_sec_nume_docu.count
                UPDATE sto_docum_digit occ
                   SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                       occ.cod_ulti_vali_exit = pscodigovalidactual,
                       occ.usu_modi           = psusuario,
                       occ.fec_modi           = SYSDATE
                 WHERE occ.cod_pais = pscodigopais
                   AND occ.cod_tipo_docu = pscodigotipodoc
                   AND occ.num_lote = v_numlote(i)
                   AND occ.sec_nume_docu = v_sec_nume_docu(i);

              EXIT WHEN c_matriz%NOTFOUND;
            END LOOP;
            CLOSE c_matriz;

          ELSE
            --- prueba de mensaje en validaciones

            OPEN c_matriz(v_gcod_peri(j));
            LOOP
              FETCH c_matriz BULK COLLECT
                INTO v_numlote,
                     v_sec_nume_docu LIMIT rows;

              FOR i IN 1 .. v_sec_nume_docu.count
              LOOP

                IF lncounterrornulos > 0 THEN

                  sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(i),
                                                       v_numlote(i),
                                                       'Existen Códigos Nulos');
                END IF;

                IF lncounterrorduplicados > 0 THEN

                  sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(i),
                                                       v_numlote(i),
                                                       'Existen Códigos Duplicados');

                END IF;

                IF lncounterrorice > 0 THEN

                  sto_pkg_gener.sto_pr_add_mensa_error(v_sec_nume_docu(i),
                                                       v_numlote(i),
                                                       'Existen Productos con precio menor a ICE');

                END IF;

              END LOOP;

              EXIT WHEN c_matriz%NOTFOUND;
            END LOOP;
            CLOSE c_matriz;

            -- fin

          END IF;
        END LOOP;
      END IF;
      EXIT WHEN c_periodos%NOTFOUND;

    END LOOP;
    CLOSE c_periodos;
    sto_pkg_gener.sto_pr_gener_after_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    BEGIN
      UPDATE pre_ofert_detal
         SET precio_unitario = round(imp_prec_cata / val_fact_repe, 0)
       WHERE precio_unitario - trunc(precio_unitario) > 0
         AND imp_prec_cata - trunc(imp_prec_cata) = 0
         AND EXISTS
       (SELECT 1
                FROM seg_pais,
                     seg_moned
               WHERE cod_pais =
                     (SELECT DISTINCT cod_pais FROM bas_ctrl_fact)
                 AND mone_oid_mone = oid_mone
                 AND num_deci = 0);

    EXCEPTION
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
    END;

    IF nvl(lsactprccont, 'N') = 'S' THEN
      BEGIN
        UPDATE pre_ofert_detal
           SET imp_prec_posi = 0
         WHERE imp_prec_posi <> 0;
      EXCEPTION
        WHEN OTHERS THEN
          ln_sqlcode := SQLCODE;
      END;
    END IF;

    IF nvl(lsactprccont2, 'N') = 'S' THEN
      BEGIN
        UPDATE pre_ofert_detal y
           SET y.imp_prec_posi = 0.1
         WHERE y.imp_prec_posi = 0
           AND y.imp_prec_cata = 0;
        --and not exists (select 1 from fac_tipo_ofert_exclu x where x.tofe_oid_tipo_ofer=y.tofe_oid_tipo_ofer);
      EXCEPTION
        WHEN OTHERS THEN
          ln_sqlcode := SQLCODE;
      END;
    END IF;


    BEGIN
      FOR y IN (
            select distinct f.oid_grup_ofer, d.num_grup, d.coes_oid_estr
            from pre_matri_factu_cabec a, cra_perio b, seg_perio_corpo c, pre_ofert d, pre_ofert_detal e, pre_grupo_ofert f
            where a.perd_oid_peri=b.oid_peri and b.peri_oid_peri=c.oid_peri 
            and a.oid_cabe=d.mfca_oid_cabe and d.oid_ofer=e.ofer_oid_ofer
            and d.oid_ofer=f.ofer_oid_ofer
            and d.coes_oid_estr in (2003,2007,2022)
            and CUES_OID_IND_CUAD_TIPO_ESTR is null)
      LOOP
      
        UPDATE pre_grupo_ofert x
           SET x.cues_oid_ind_cuad_tipo_estr = 
               case when y.coes_oid_estr in (2003) and y.num_grup=1 then 3
               when y.coes_oid_estr in (2003) and y.num_grup>1 then 1
               when y.coes_oid_estr in (2007,2022) and y.num_grup=1 then 8
               when y.coes_oid_estr in (2007,2022) and y.num_grup>1 then 6
               else NULL
               end
         WHERE x.oid_grup_ofer = y.oid_grup_ofer;
      

      
      END LOOP;

    EXCEPTION
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
    END;



  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR sto_pr_occ_confi_matri: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_confi_matri;

  /***************************************************************************
    Descripcion       : Valiacion Fecha Facturacion
    Fecha Creacion    : 04/07/2012
    Autor             : Jorge Velasquez
  ***************************************************************************/
  PROCEDURE sto_pr_occ_fecha_factu
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_factu IS
      SELECT cons.cod_pais,
             cons.num_lote,
             cons.sec_nume_docu,
             cons.perd_oid_peri,
             cons.zzon_oid_zona,
             cons.pais_oid_pais,
             cons.clie_oid_clie,
             cons.cod_peri,
             cons.ind_fac_refac
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_cod_pais IS TABLE OF int_solic_conso_cabec.cod_pais%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_perd_oid_peri IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;
    TYPE t_zzon_oid_zona IS TABLE OF int_solic_conso_cabec.zzon_oid_zona%TYPE;
    TYPE t_pais_oid_pais IS TABLE OF int_solic_conso_cabec.pais_oid_pais%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_cod_peri IS TABLE OF int_solic_conso_cabec.cod_peri%TYPE;
    TYPE t_ind_fac_refac IS TABLE OF int_solic_conso_cabec.ind_fac_refac%TYPE;

    v_cod_pais      t_cod_pais;
    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_perd_oid_peri t_perd_oid_peri;
    v_zzon_oid_zona t_zzon_oid_zona;
    v_pais_oid_pais t_pais_oid_pais;
    v_clie_oid_clie t_clie_oid_clie;
    v_cod_peri      t_cod_peri;
    v_ind_fac_refac t_ind_fac_refac;

    rows      NATURAL := 1000; -- Numero de filas a procesar cada vez
    j         BINARY_INTEGER := 0;
    ldfecproc DATE;
    lnfecha1  DATE;
    lnfecha2  DATE;
    lnerror   NUMBER := 0;
    lnferia   NUMBER := 0;

    lsvalidferia VARCHAR2(15) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                          'STO_VALI_FERI'),
                                     'N');


    lsvalidFAD VARCHAR2(15) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                          'STO_FAD_FECFAC'),
                                     'S');
  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    ---DESARROLLO DEL CURSOR

    OPEN c_factu;
    LOOP
      FETCH c_factu BULK COLLECT
        INTO v_cod_pais,
             v_numlote,
             v_sec_nume_docu,
             v_perd_oid_peri,
             v_zzon_oid_zona,
             v_pais_oid_pais,
             v_clie_oid_clie,
             v_cod_peri,
             v_ind_fac_refac LIMIT rows;

      IF v_cod_pais.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_cod_pais.first .. v_cod_pais.last
        LOOP

          BEGIN
            SELECT fec_inic
              INTO lnfecha1
              FROM cra_crono
             WHERE perd_oid_peri = v_perd_oid_peri(j)
               AND zzon_oid_zona = v_zzon_oid_zona(j)
               AND cact_oid_acti =
                   (SELECT oid_acti
                      FROM cra_activ
                     WHERE cod_acti = 'FA'
                       AND pais_oid_pais = v_pais_oid_pais(j));
          EXCEPTION
            WHEN OTHERS THEN
              lnfecha1 := to_date('01/01/2000', 'dd/mm/yyyy');
          END;

          BEGIN
            SELECT fec_proc
              INTO ldfecproc
              FROM bas_ctrl_fact a
             WHERE a.ind_camp_act = '1'
               AND a.sta_camp = '0';
          EXCEPTION
            WHEN OTHERS THEN
              lnfecha1 := to_date('01/01/2000', 'dd/mm/yyyy');
          END;

          BEGIN
            SELECT fec_inic
              INTO lnfecha2
              FROM cra_crono
             WHERE perd_oid_peri = v_perd_oid_peri(j)
               AND zzon_oid_zona = v_zzon_oid_zona(j)
               AND cact_oid_acti =
                   (SELECT oid_acti
                      FROM cra_activ
                     WHERE cod_acti = 'RF'
                       AND pais_oid_pais = v_pais_oid_pais(j));
          EXCEPTION
            WHEN OTHERS THEN
              lnfecha2 := to_date('01/01/2099', 'dd/mm/yyyy');
          END;

          BEGIN
            SELECT COUNT(*)
              INTO lnerror
              FROM dual
             WHERE (lnfecha1 <= ldfecproc AND lnfecha2 >= ldfecproc);
          EXCEPTION
            WHEN OTHERS THEN
              lnerror := 0;
          END;

          IF lnerror > 0 AND lsvalidferia = 'S' AND ldfecproc > lnfecha1 THEN
            SELECT COUNT(1)
              INTO lnferia
              FROM cra_feria            x,
                   cra_cabec_grupo_zona y,
                   cra_detal_grupo_zona z,
                   cra_activ            z1
             WHERE x.ind_no_labo = 1
               AND x.cgzo_oid_cabe_grup_zona = y.oid_cabe_grup_zona
               AND y.oid_cabe_grup_zona = z.cgzo_oid_cabe_grup_zona
               AND x.cact_oid_acti = z1.oid_acti
               AND z1.cod_acti = 'RF'
               AND z1.pais_oid_pais = v_pais_oid_pais(j)
               AND z.zzon_oid_zona = v_zzon_oid_zona(j)
               AND x.fec_feri = ldfecproc;

            IF lnferia > 0 THEN
              lnerror := 0;
            END IF;

          END IF;

          if lsvalidFAD='S' then
          if sto_fn_valid_fad(v_perd_oid_peri(j),v_clie_oid_clie(j))>0 then
             lnerror := 1;
          end if;
          end if;


          IF lnerror > 0 THEN

            UPDATE sto_docum_digit occ
               SET -- Actualizamos Indicadores de Validacion
                   occ.cod_ulti_vali_ejec = pscodigovalidactual,
                   occ.cod_ulti_vali_exit = pscodigovalidactual,
                   occ.usu_modi           = psusuario,
                   occ.fec_modi           = SYSDATE
             WHERE occ.cod_pais = v_cod_pais(j)
               AND occ.cod_tipo_docu = pscodigotipodoc
               AND occ.num_lote = v_numlote(j)
               AND occ.sec_nume_docu = v_sec_nume_docu(j);

          END IF;

        END LOOP;
      END IF;

      EXIT WHEN c_factu%NOTFOUND;
    END LOOP;

    CLOSE c_factu;

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
                              'ERROR sto_pr_occ_fecha_factu: ' ||
                              ls_sqlerrm);

  END sto_pr_occ_fecha_factu;

  /***************************************************************************
    Descripcion       : Validacion codigo Ciudad
    Fecha Creacion    : 04/07/2012
    Autor             : Jorge Velasquez
  ***************************************************************************/
  PROCEDURE sto_pr_occ_codig_ciuda
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_valida IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ,
             mae_clien_direc       mcd
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND cons.clie_oid_clie = mcd.clie_oid_clie
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND mcd.ciud_cod_ciud IS NOT NULL;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez
    i    BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    ---DESARROLLO DEL CURSOR

    OPEN c_valida;
    LOOP
      FETCH c_valida BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;

      -- Actualizamos Documentos Validados OK
      FORALL i IN 1 .. v_sec_nume_docu.count
        UPDATE sto_docum_digit occ
           SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
               occ.cod_ulti_vali_exit = pscodigovalidactual,
               occ.usu_modi           = psusuario,
               occ.fec_modi           = SYSDATE
         WHERE occ.cod_pais = pscodigopais
           AND occ.cod_tipo_docu = pscodigotipodoc
           AND occ.num_lote = v_numlote(i)
           AND occ.sec_nume_docu = v_sec_nume_docu(i);

      EXIT WHEN c_valida%NOTFOUND;
    END LOOP;

    CLOSE c_valida;

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
                              'ERROR sto_pr_occ_codig_ciuda: ' ||
                              ls_sqlerrm);
  END sto_pr_occ_codig_ciuda;

  /***************************************************************************
    Descripcion       : Validacion de cuadre de ofertas
    Fecha Creacion    : 31/05/2013
    Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE sto_pr_occ_cuadr_ofert
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_cuaofe IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_num_lote      t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cuaofe;
    LOOP
      FETCH c_cuaofe BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          ped_pkg_cuadr_ofert.ped_pr_cuadr_ofert_sto(v_sec_nume_docu(j));

          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        END LOOP;
      END IF;
      EXIT WHEN c_cuaofe%NOTFOUND;

    END LOOP;
    CLOSE c_cuaofe;

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
                              'ERROR STO_PR_OCC_CUADR_OFERT: ' ||
                              ls_sqlerrm);
  END sto_pr_occ_cuadr_ofert;

  /***************************************************************************
    Descripcion       : Validacion de Monto Minimo STO
    Fecha Creacion    : 31/05/2013
    Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE sto_pr_occ_monto_minim_sto
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_cuaofe IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.oid_soli_cabe,
             cons.perd_oid_peri,
             cons.clie_oid_clie,
             cons.ztad_oid_terr_admi
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_soli_cabe IS TABLE OF int_solic_conso_cabec.oid_soli_cabe%TYPE;
    TYPE t_perd_oid_peri IS TABLE OF int_solic_conso_cabec.perd_oid_peri%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_ztad_oid_terr_admi IS TABLE OF int_solic_conso_cabec.ztad_oid_terr_admi%TYPE;

    v_num_lote           t_numlote;
    v_sec_nume_docu      t_sec_nume_docu;
    v_oid_soli_cabe      t_oid_soli_cabe;
    v_perd_oid_peri      t_perd_oid_peri;
    v_clie_oid_clie      t_clie_oid_clie;
    v_ztad_oid_terr_admi t_ztad_oid_terr_admi;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    existe  BOOLEAN := TRUE;
    j       BINARY_INTEGER := 0;
    lnvalor NUMBER;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cuaofe;
    LOOP
      FETCH c_cuaofe BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_oid_soli_cabe,
             v_perd_oid_peri,
             v_clie_oid_clie,
             v_ztad_oid_terr_admi LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          lnvalor := ped_pkg_cuadr_ofert.ped_fn_monto_minim_sto(v_sec_nume_docu(j),
                                                                v_perd_oid_peri(j),
                                                                v_clie_oid_clie(j),
                                                                v_ztad_oid_terr_admi(j),
                                                                psusuario);

          IF lnvalor > 0 THEN

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
      EXIT WHEN c_cuaofe%NOTFOUND;

    END LOOP;
    CLOSE c_cuaofe;

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
                              'ERROR STO_PR_OCC_MONTO_MINIM_STO: ' ||
                              ls_sqlerrm);
  END sto_pr_occ_monto_minim_sto;

  /***************************************************************************
    Descripcion       : Validacion de Monto Maximo STO
    Fecha Creacion    : 31/05/2013
    Autor             : Ivan Tocto
  ***************************************************************************/
  PROCEDURE sto_pr_occ_monto_maxim_sto
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_cuaofe IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.oid_soli_cabe,
             cons.clie_oid_clie,
             cons.ztad_oid_terr_admi
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_soli_cabe IS TABLE OF int_solic_conso_cabec.oid_soli_cabe%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_ztad_oid_terr_admi IS TABLE OF int_solic_conso_cabec.ztad_oid_terr_admi%TYPE;

    v_num_lote           t_numlote;
    v_sec_nume_docu      t_sec_nume_docu;
    v_oid_soli_cabe      t_oid_soli_cabe;
    v_clie_oid_clie      t_clie_oid_clie;
    v_ztad_oid_terr_admi t_ztad_oid_terr_admi;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    existe  BOOLEAN := TRUE;
    j       BINARY_INTEGER := 0;
    lnvalor NUMBER;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cuaofe;
    LOOP
      FETCH c_cuaofe BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_oid_soli_cabe,
             v_clie_oid_clie,
             v_ztad_oid_terr_admi LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          lnvalor := ped_pkg_cuadr_ofert.ped_fn_monto_maxim_sto(v_sec_nume_docu(j),
                                                                v_clie_oid_clie(j),
                                                                v_ztad_oid_terr_admi(j),
                                                                psusuario);

          IF lnvalor > 0 THEN

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
      EXIT WHEN c_cuaofe%NOTFOUND;

    END LOOP;
    CLOSE c_cuaofe;

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
                              'ERROR STO_PR_OCC_MONTO_MAXIM_STO: ' ||
                              ls_sqlerrm);
  END sto_pr_occ_monto_maxim_sto;

  /***************************************************************************
    Descripcion       : Validacion de Monto Minimo Stock
    Fecha Creacion    : 14/06/2013
    Autor             : Carlos Chata
  ***************************************************************************/
  PROCEDURE sto_pr_occ_monto_minim_stock
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_cuaofe IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.oid_soli_cabe,
             cons.clie_oid_clie,
             cons.ztad_oid_terr_admi
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND nvl(cons.ind_erro_mmfc, '0') = '0';

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_soli_cabe IS TABLE OF int_solic_conso_cabec.oid_soli_cabe%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_ztad_oid_terr_admi IS TABLE OF int_solic_conso_cabec.ztad_oid_terr_admi%TYPE;

    v_num_lote           t_numlote;
    v_sec_nume_docu      t_sec_nume_docu;
    v_oid_soli_cabe      t_oid_soli_cabe;
    v_clie_oid_clie      t_clie_oid_clie;
    v_ztad_oid_terr_admi t_ztad_oid_terr_admi;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    existe  BOOLEAN := TRUE;
    j       BINARY_INTEGER := 0;
    lnvalor NUMBER;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cuaofe;
    LOOP
      FETCH c_cuaofe BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_oid_soli_cabe,
             v_clie_oid_clie,
             v_ztad_oid_terr_admi LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        END LOOP;
      END IF;
      EXIT WHEN c_cuaofe%NOTFOUND;

    END LOOP;
    CLOSE c_cuaofe;

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
                              'ERROR STO_PR_OCC_MONTO_MINIM_STOCK: ' ||
                              ls_sqlerrm);
  END sto_pr_occ_monto_minim_stock;
  /***************************************************************************
    Descripcion       : Validacion de Forma de Pago Por Zona
    Fecha Creacion    : 26/06/2013
    Autor             : Carlos Chata
  ***************************************************************************/
  PROCEDURE sto_pr_occ_forma_pago_zona
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_cuaofe IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.oid_soli_cabe,
             cons.clie_oid_clie,
             cons.zzon_oid_zona,
             fpzo.oid_fopa
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ,
             sto_form_pago_zona    fpzo
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND cons.zzon_oid_zona = fpzo.oid_zona(+)
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_soli_cabe IS TABLE OF int_solic_conso_cabec.oid_soli_cabe%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_zzon_oid_zona IS TABLE OF int_solic_conso_cabec.zzon_oid_zona%TYPE;
    TYPE t_oid_fopa IS TABLE OF sto_form_pago_zona.oid_fopa%TYPE;

    v_num_lote      t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_oid_soli_cabe t_oid_soli_cabe;
    v_clie_oid_clie t_clie_oid_clie;
    v_zzon_oid_zona t_zzon_oid_zona;
    v_oid_fopa      t_oid_fopa;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    existe  BOOLEAN := TRUE;
    j       BINARY_INTEGER := 0;
    lnvalor NUMBER;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cuaofe;
    LOOP
      FETCH c_cuaofe BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_oid_soli_cabe,
             v_clie_oid_clie,
             v_zzon_oid_zona,
             v_oid_fopa LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          /*UPDATE int_solic_conso_cabec cons
            SET cons.fopa_oid_form_pago = nvl(v_oid_fopa(j),
                                              cons.fopa_oid_form_pago)
          WHERE cod_pais = pscodigopais
            --AND cons.num_lote = v_num_lote(j)
            AND cons.sec_nume_docu = v_sec_nume_docu(j)
            --AND cons.oid_soli_cabe = v_oid_soli_cabe(j)
            --AND cons.clie_oid_clie = v_clie_oid_clie(j)
            --AND cons.zzon_oid_zona = v_zzon_oid_zona(j)
            ;*/
          IF v_oid_fopa(j) IS NOT NULL THEN
            UPDATE mae_clien_datos_adici x
               SET x.imp_ingr_fami = 0
             WHERE clie_oid_clie = v_clie_oid_clie(j);
          ELSE
            UPDATE mae_clien_datos_adici x
               SET x.imp_ingr_fami = NULL
             WHERE clie_oid_clie = v_clie_oid_clie(j);
          END IF;

          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        END LOOP;
      END IF;
      EXIT WHEN c_cuaofe%NOTFOUND;

    END LOOP;
    CLOSE c_cuaofe;

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
                              'ERROR STO_PR_OCC_FORMA_PAGO_ZONA: ' ||
                              ls_sqlerrm);
  END sto_pr_occ_forma_pago_zona;

  /***************************************************************************
    Descripcion       : Validacion de SCC En Gestion o rechazada
    Fecha Creacion    : 07/08/2013
    Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/
  PROCEDURE sto_pr_occ_gesti_recha
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_gesrec IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.cod_clie
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_secnumedocu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_codclie IS TABLE OF int_solic_conso_cabec.cod_clie%TYPE;

    lsnumerodias sto_param_gener_occrr.val_param%TYPE;

    v_num_lote      t_numlote;
    v_sec_nume_docu t_secnumedocu;
    v_cod_clie      t_codclie;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    existe BOOLEAN := TRUE;
    j      BINARY_INTEGER := 0;

    lsvalor VARCHAR2(100);

    lscodigocliente mae_clien.cod_clie%TYPE;

  BEGIN

    lsnumerodias := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         'STO_NUM_DIAS_ATRAS');

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_gesrec;
    LOOP
      FETCH c_gesrec BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_cod_clie LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

          existe := TRUE;

          IF 'C' = substr(v_cod_clie(j), 0, 1) THEN
            lsvalor := substr(v_cod_clie(j), 2, 99);

            BEGIN

              SELECT MIN(a.cod_clie)
                INTO lscodigocliente
                FROM int_solic_conso_credi a,
                     sto_docum_digit       b
               WHERE a.sec_nume_docu = b.sec_nume_docu
                 AND b.ind_envi = '0'
                 AND b.ind_rech = '0'
                 AND a.num_docu_iden = lsvalor
                 AND b.fec_modi >= trunc(SYSDATE) - lsnumerodias;

              IF lscodigocliente IS NOT NULL THEN
                existe := FALSE;
              END IF;

            EXCEPTION
              WHEN OTHERS THEN
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
      EXIT WHEN c_gesrec%NOTFOUND;

    END LOOP;
    CLOSE c_gesrec;

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
                              'ERROR STO_PR_OCC_SC_EN_GESTION: ' ||
                              ls_sqlerrm);
  END sto_pr_occ_gesti_recha;

  /**************************************************************************
  Descripcion       : Validacion de cliente incobrable
  Fecha Creacion    : 26/08/2013
  Autor             : Yahir Rivas Luna.
  **************************************************************************/
  PROCEDURE sto_pr_occ_clien_incob
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    -- Solo filtramos los que tienen numeros de documentos validos
    CURSOR c_clienincob(psmontomaxincobrable NUMBER) IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc -- SCC
         AND occ.cod_pais = pscodigopais -- 'COE'
         AND (NOT EXISTS
              (SELECT 1
                 FROM ccc_consu_casti_cabec inco
                WHERE inco.num_docu_iden =
                      (SELECT iden.num_docu_iden
                         FROM mae_clien_ident iden
                        WHERE cons.clie_oid_clie = iden.clie_oid_clie
                          AND iden.val_iden_docu_prin = 1)
                  AND inco.ind_acti = 1) OR EXISTS
              (SELECT 1
                 FROM ccc_consu_casti_cabec inco
                WHERE inco.num_docu_iden =
                      (SELECT iden.num_docu_iden
                         FROM mae_clien_ident iden
                        WHERE cons.clie_oid_clie = iden.clie_oid_clie
                          AND iden.val_iden_docu_prin = 1)
                  AND inco.ind_acti = 1
                  AND inco.imp_deud_actu <= psmontomaxincobrable))
      UNION
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc -- SCC
         AND occ.cod_pais = pscodigopais -- 'COE'
         AND (NOT EXISTS (SELECT 1
                            FROM ccc_consu_casti_cabec inco
                           WHERE cons.cod_clie = inco.cod_clie
                             AND inco.ind_acti = 1) OR EXISTS
              (SELECT 1
                 FROM ccc_consu_casti_cabec inco
                WHERE cons.cod_clie = inco.cod_clie
                  AND inco.ind_acti = 1
                  AND inco.imp_deud_actu <= psmontomaxincobrable));

    TYPE t_numlote IS TABLE OF int_solic_conso_credi.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_credi.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;

    rows NATURAL := 1000; -- Numero de filas a procesar cada vez

    j                    BINARY_INTEGER := 0;
    lsmontomaxincobrable NUMBER;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);
    -- Obtenemos el parametro del monto maximo permitido para aprobar el incobrable
    lsmontomaxincobrable := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                     'STO_MTO_MAX_INCO'),
                                0);

    OPEN c_clienincob(lsmontomaxincobrable);
    LOOP
      FETCH c_clienincob BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT rows;

      IF v_numlote.count > 0 THEN
        -- Actualizamos Documentos Validados OK
        FOR j IN v_numlote.first .. v_numlote.last
        LOOP

          UPDATE sto_docum_digit occ
             SET -- Actualizamos Indicadores de Validacion
                 occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        END LOOP;
      END IF;
      EXIT WHEN c_clienincob%NOTFOUND;
    END LOOP;
    CLOSE c_clienincob;

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
                              'STO_PR_OCC_CLIEN_INCOB: ' || ls_sqlerrm);

  END sto_pr_occ_clien_incob;

  /***************************************************************************
  Descripcion       : Validacion de Anulacion por refacturacion para que puedan cambiar el pedido
  Fecha Creacion    : 28/12/2010
  Autor             : Sandro Quintana
  ***************************************************************************/

  PROCEDURE sto_pr_occ_anula_refa
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND nvl(cons.ind_ped_rec_anul,0) <> '2'
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote t_numlote;

    v_sec_nume_docu t_sec_nume_docu;

    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualiza los Indicadores de Validacion
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

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
                              'ERROR sto_pr_occ_anula_refa: ' || ls_sqlerrm);

  END sto_pr_occ_anula_refa;
  /***************************************************************************
  Descripcion       : Validacion de Error desviacion
  Fecha Creacion    : 14/05/2014
  Autor             : José Cairampoma
  ***************************************************************************/
  PROCEDURE sto_pr_occ_desvi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS
    CURSOR c_deuda IS
      SELECT cons.num_lote,
             cons.sec_nume_docu
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_lote = cons.num_lote
         AND occ.num_proc = psnumeroproceso
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais
         AND cons.ind_erro_desv = '0';

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;

    v_numlote       t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    j BINARY_INTEGER := 0;

  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_deuda;
    LOOP
      FETCH c_deuda BULK COLLECT
        INTO v_numlote,
             v_sec_nume_docu LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualiza los Indicadores de Validacion
        FORALL j IN 1 .. v_sec_nume_docu.count
          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_numlote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

      END IF;
      EXIT WHEN c_deuda%NOTFOUND;

    END LOOP;
    CLOSE c_deuda;

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
                              'ERROR sto_pr_occ_desvi: ' || ls_sqlerrm);

  END sto_pr_occ_desvi;

   /***************************************************************************
    Descripcion       : Validacion de Forma de Pago Por Clasificacion
    Fecha Creacion    : 03/04/2014
    Autor             : Gonzalo Javier Huertas Agurto
  ***************************************************************************/

  PROCEDURE sto_pr_occ_forma_pago_clasi
  (
    pscodigopais          VARCHAR2,
    pscodigotipodoc       VARCHAR2,
    pscodigovalidactual   VARCHAR2,
    pscodigovalidanterior VARCHAR2,
    psusuario             VARCHAR2,
    psnumeroproceso       VARCHAR2
  ) IS

    CURSOR c_cuaofe IS
      SELECT cons.num_lote,
             cons.sec_nume_docu,
             cons.oid_soli_cabe,
             cons.clie_oid_clie,
             cons.zzon_oid_zona
        FROM int_solic_conso_cabec cons,
             sto_proce_docum_digit occ
       WHERE occ.sec_nume_docu = cons.sec_nume_docu
         AND occ.num_proc = psnumeroproceso
         AND occ.num_lote = cons.num_lote
         AND occ.cod_tipo_docu = pscodigotipodoc
         AND occ.cod_pais = pscodigopais;

    TYPE t_numlote IS TABLE OF int_solic_conso_cabec.num_lote%TYPE;
    TYPE t_sec_nume_docu IS TABLE OF int_solic_conso_cabec.sec_nume_docu%TYPE;
    TYPE t_oid_soli_cabe IS TABLE OF int_solic_conso_cabec.oid_soli_cabe%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF int_solic_conso_cabec.clie_oid_clie%TYPE;
    TYPE t_zzon_oid_zona IS TABLE OF int_solic_conso_cabec.zzon_oid_zona%TYPE;


    v_num_lote      t_numlote;
    v_sec_nume_docu t_sec_nume_docu;
    v_oid_soli_cabe t_oid_soli_cabe;
    v_clie_oid_clie t_clie_oid_clie;
    v_zzon_oid_zona t_zzon_oid_zona;


    w_filas NUMBER := 5000; -- Numero de filas a procesar cada vez

    existe  BOOLEAN := TRUE;
    j       BINARY_INTEGER := 0;
    lnvalor NUMBER;


CURSOR c_fopa(p_secnumedocu NUMBER) IS
                           SELECT DISTINCT a.oid_deta_ofer,a.fopa_oid_form_pago, det.sec_nume_docu
                            FROM int_solic_conso_cabec cons,
                                 int_solic_conso_detal det,
                                 mae_clien_unida_admin c,
                                 zon_terri_admin d,
                                 zon_terri e,
                                 zon_secci f,
                                 zon_zona g,
                                 zon_regio h,
                                 mae_clien_tipo_subti i,
                                 mae_clien_clasi j,
                                 sto_forma_pago_clasi a
                           WHERE cons.sec_nume_docu=p_secnumedocu
                             AND cons.sec_nume_docu=det.sec_nume_docu_cabe
                             AND c.ztad_oid_terr_admi = d.oid_terr_admi
                             AND d.zscc_oid_secc = f.oid_secc
                             AND d.terr_oid_terr = e.oid_terr
                             AND f.zzon_oid_zona = g.oid_zona
                             AND g.zorg_oid_regi = h.oid_regi
                             AND c.clie_oid_clie = i.clie_oid_clie
                             AND c.ind_acti = 1
                             AND d.ind_borr = 0
                             AND i.oid_clie_tipo_subt =j.ctsu_oid_clie_tipo_subt
                             AND c.clie_oid_clie = cons.clie_oid_clie
                             AND cons.cod_peri = a.cod_peri
                             and det.ofde_oid_deta_ofer=a.oid_deta_ofer
                             AND i.ticl_oid_tipo_clie = nvl(a.oid_tipo_clie, i.ticl_oid_tipo_clie) --tipo
                             AND i.sbti_oid_subt_clie = nvl(a.oid_subt_clie, i.sbti_oid_subt_clie) --subtipo
                             AND j.tccl_oid_tipo_clasi = nvl(a.oid_tipo_clas,j.tccl_oid_tipo_clasi) --tipo clasif
                             AND j.clas_oid_clas =nvl(a.oid_clas, j.clas_oid_clas) --clasif
                             AND h.oid_regi = nvl(a.oid_regi, h.oid_regi) --region
                             AND g.oid_zona = nvl(a.oid_zona, g.oid_zona); --zon

r_fopa c_fopa%ROWTYPE;


  BEGIN

    sto_pkg_gener.sto_pr_gener_befor_exval(pscodigopais,
                                           pscodigotipodoc,
                                           pscodigovalidactual,
                                           pscodigovalidanterior,
                                           psusuario,
                                           psnumeroproceso);

    OPEN c_cuaofe;
    LOOP
      FETCH c_cuaofe BULK COLLECT
        INTO v_num_lote,
             v_sec_nume_docu,
             v_oid_soli_cabe,
             v_clie_oid_clie,
             v_zzon_oid_zona LIMIT w_filas;

      IF v_sec_nume_docu.count > 0 THEN

        -- Actualizamos Documentos Validados OK
        FOR j IN v_sec_nume_docu.first .. v_sec_nume_docu.last
        LOOP

                OPEN c_fopa(v_sec_nume_docu(j));
                LOOP
                    FETCH c_fopa INTO r_fopa;
                    EXIT WHEN c_fopa%NOTFOUND;

                    update int_solic_conso_detal
                    set fopa_oid_form_pago=r_fopa.fopa_oid_form_pago
                    where ofde_oid_deta_ofer=r_fopa.oid_deta_ofer
                    and sec_nume_docu=r_fopa.sec_nume_docu;

                END LOOP;

                CLOSE c_fopa;

          UPDATE sto_docum_digit occ
             SET occ.cod_ulti_vali_ejec = pscodigovalidactual,
                 occ.cod_ulti_vali_exit = pscodigovalidactual,
                 occ.usu_modi           = psusuario,
                 occ.fec_modi           = SYSDATE
           WHERE occ.cod_pais = pscodigopais
             AND occ.cod_tipo_docu = pscodigotipodoc
             AND occ.num_lote = v_num_lote(j)
             AND occ.sec_nume_docu = v_sec_nume_docu(j);

        END LOOP;
      END IF;
      EXIT WHEN c_cuaofe%NOTFOUND;

    END LOOP;
    CLOSE c_cuaofe;

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
                              'ERROR sto_pr_occ_forma_pago_clasi: ' ||
                              ls_sqlerrm);
  END sto_pr_occ_forma_pago_clasi;

END sto_pkg_proce_valid_occ;
/
