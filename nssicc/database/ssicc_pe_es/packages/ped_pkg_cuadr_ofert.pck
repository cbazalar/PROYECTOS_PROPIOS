CREATE OR REPLACE PACKAGE ped_pkg_cuadr_ofert IS
  /***************************************************************************
  Descripcion       : Cuadre de Ofertas
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_ofert_sicc
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER,
    p_oid_terr_admi IN NUMBER
  );
  /***************************************************************************
  Descripcion       : Cuadre de Ofertas
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_ofert(p_oid_cabe IN NUMBER);
  /***************************************************************************
  Descripcion       : Cuadre de Ofertas
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_ofert_sto(p_oid_cabe IN NUMBER);
  /***************************************************************************
  Descripcion       : Cuadre CV
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_cv
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER
  );
  /***************************************************************************
  Descripcion       : Cuadre CV Promociones
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_cv_promo
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER
  );
  /***************************************************************************
  Descripcion       : Cuadre CF
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_cf
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER
  );
  /***************************************************************************
  Descripcion       : Cuadre CF Promociones
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_cf_promo
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER
  );
  /***************************************************************************
  Descripcion       : Cuadre Promociones
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_promo
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER
  );
  /***************************************************************************
  Descripcion       : Cuadre Promocion
  Fecha Creacion    : 11/10/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_cuadr_promo
  (
    p_oidsoli   NUMBER,
    p_indcuadre NUMBER,
    p_oidprom   NUMBER
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Cuadre Cond.
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_condi
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER
  );
  /***************************************************************************
  Descripcion       : Cuadre Promocion
  Fecha Creacion    : 11/10/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_cuadr_condi
  (
    p_oidsoli      NUMBER,
    p_indcuadre    NUMBER,
    p_oidofer      NUMBER,
    p_oidgrupoofer NUMBER
  ) RETURN NUMBER;
  /***************************************************************************
  Descripcion       : Secuenciacion
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_secue;
  /***************************************************************************
  Descripcion       : Prueba Cuadre
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_prueb_cuadr(tmpzona IN VARCHAR2);
  /***************************************************************************
  Descripcion       : Stock
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_stock;
  /***************************************************************************
  Descripcion       : Stock
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_stock_online(p_oidsolicabe IN NUMBER);
  /***************************************************************************
  Descripcion       : Stock
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_stock_online_1(p_oidsolicabe IN NUMBER);
  /***************************************************************************
  Descripcion       : Flete
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_flete;
  /***************************************************************************
  Descripcion       : Inicializa Stock
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ini_stock;
  /***************************************************************************
  Descripcion       : Reporte Faltante
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_repor_falta;
  /***************************************************************************
  Descripcion       : Reporte Faltante
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_repor_falta_prol;
  /***************************************************************************
  Descripcion       : Asigancion de Stocks. Integracion Familia Protegida
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  Fecha Actualiza   : 30/06/2011
  Autor Actualiza   : Carlos Diaz Valverde
  ***************************************************************************/
  PROCEDURE ped_pr_proce_asign_stock
  (
    p_codigopais IN VARCHAR2,
    p_fechafact  IN VARCHAR2,
    p_usuario    IN VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Calculo de Descuentos
  Fecha Creacion    : 31/01/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_calcu_desc(p_oidcabe IN NUMBER);
  /***************************************************************************
  Descripcion       : Recuperacion Obligatoria
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_recup_oblig(p_oidcabe NUMBER);
  /***************************************************************************
  Descripcion       : Limite de Venta
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_limit_venta
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    p_oidoferdetal NUMBER,
    p_unidades     NUMBER
  ) RETURN NUMBER;
  /***************************************************************************
  Descripcion       : Obtiene Monto Minimo por cliente
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_obtie_mminim
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    p_nominal      OUT NUMBER,
    p_nivel1       OUT NUMBER,
    p_nivel2       OUT NUMBER
  );
  /***************************************************************************
  Descripcion       : Obtiene Monto Minimo Incentivos por cliente
  Fecha Creacion    : 30/01/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_obtie_mminim2
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    p_nominal      OUT NUMBER,
    p_nivel1       OUT NUMBER
  );
  /***************************************************************************
  Descripcion       : Monto Minimo SiCC
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_monto_minim
  (
    p_oidsoli NUMBER,
    psusuario VARCHAR2
  )
  --RETURN NUMBER
  ;
  /***************************************************************************
  Descripcion       : Monto Minimo SiCC
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_monto_minim_sto
  (
    p_oidsoli      NUMBER,
    p_oidperi      NUMBER,
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    psusuario      VARCHAR2
  ) RETURN NUMBER;
  /***************************************************************************
  Descripcion       : Monto Minimo Incentivos SiCC
  Fecha Creacion    : 30/01/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_monto_minim2
  (
    p_oidsoli NUMBER,
    psusuario VARCHAR2
  )
  --RETURN NUMBER
  ;
  /***************************************************************************
  Descripcion       : Obtiene Monto Maximo por cliente
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_obtie_mmaxim
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER
  ) RETURN NUMBER;
  /***************************************************************************
  Descripcion       : Monto Minimo SiCC
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_monto_maxim
  (
    p_oidsoli NUMBER,
    psusuario VARCHAR2
  )
  --RETURN NUMBER
  ;
  /***************************************************************************
  Descripcion       : Monto Minimo SiCC
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_monto_maxim_sto
  (
    p_oidsoli      NUMBER,
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    psusuario      VARCHAR2
  ) RETURN NUMBER;
  /***************************************************************************
  Descripcion       : Monto Minimo SiCC
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ofert_espec
  (
    p_oidsoli    NUMBER,
    pscodigopais VARCHAR
  )
  --RETURN NUMBER
  ;
  /***************************************************************************
  Descripcion       : Ofertas Especiales Colombia
  Fecha Creacion    : 29/01/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ofert_espec2
  (
    p_oidsoli    NUMBER,
    pscodigopais VARCHAR
  )
  --RETURN NUMBER
  ;
  /**************************************************************************
    Descripcion       : DEvuelve el grupo de proceso de un consolidado
    Autor             : Jose Luis Rodriguez
    Fecha Creacion    : 26/01/2011
  ***************************************************************************/
  FUNCTION ocr_fn_devue_ofer_espe
  (
    p_val_tota_paga_loca IN NUMBER,
    p_perio              IN NUMBER,
    p_estra              IN NUMBER,
    p_oidofer            IN NUMBER
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion        : Devuelve el origen de un consolidado
  Fecha Creacion     : 18/09/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_devue_orig_conso(pnoidcons NUMBER) RETURN VARCHAR;
  /***************************************************************************
  Descripcion       : Generacion de Estadisticas
  Fecha Creacion    : 18/01/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_estadist
  (
    pscodigopais VARCHAR2,
    pnoidsoli    NUMBER
  );

  /**************************************************************************
  Descripcion        : Devuelve el numero de unidades reservadas de BEL_STOCK
  Fecha Creacion     : 25/01/2012
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION ped_fn_resrv_stock
  (
    pnoidsolicabe NUMBER,
    pnoidperiodo  NUMBER,
    pnoidcliente  NUMBER,
    pnoidproducto NUMBER,
    pnoidalmacen  NUMBER,
    pnuniddemanda NUMBER,
    pscodigoventa VARCHAR2
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion        : Devuelve una marca que identifica si un pedido se ha cambiado
  Fecha Creacion     : 26/06/2013
  Autor              : Jorge Y?pez
  ***************************************************************************/
  FUNCTION ped_fn_gener_marca_ped(pnsecnumedocu NUMBER) RETURN NUMBER;
  /**************************************************************************
  Descripcion        : Actualiza los productos no digitables de Compuestas Fijas
                       con almacen distinto
  Fecha Creacion     : 07/05/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_almc_posic
  (
    pnoidperiodo NUMBER,
    pdfecha      DATE
  );

  /**************************************************************************
  Descripcion        : Crea oferta MAV en matriz y devuelve el CUV
  Fecha Creacion     : 24/01/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_inser_ofer_mav
  (
    pnoidprod     NUMBER,
    pnoidtipoofer NUMBER,
    pnpreccata    NUMBER,
    pnpreccont    NUMBER,
    pnfopa        NUMBER,
    pnoidperi     NUMBER
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Devuelve el siguiente CUV adicional disponible
  Fecha Creacion     : 18/09/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_devue_cuv_adici(pnoidperi NUMBER) RETURN VARCHAR;
  /**************************************************************************
  Descripcion        : Devuelve el siguiente CUV adicional disponible
  Fecha Creacion     : 18/09/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_devue_num_gratis
  (
    pscodperi VARCHAR2,
    pnoidsoli NUMBER,
    pnoidofer NUMBER
  ) RETURN NUMBER;
  /**************************************************************************
  Descripcion        : torna el stock de las reservas no asignadas
  Fecha Creacion     : 25/01/2012
  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ped_pr_retor_stock_resrv_nasig
  (
    pnoidsolicabe NUMBER,
    pnoidperiodo  NUMBER,
    pnoidcliente  NUMBER
  );
  /**************************************************************************
  Descripcion        : Retorna el stock de una solicitud
  Fecha Creacion     : 13/02/2012
  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ped_pr_retor_stock_solic(pnoidsolicabe NUMBER);
  /***************************************************************************
  Descripcion       : Monto Minimo SiCC
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_asignar_cuv
  (
    p_oidperi NUMBER,
    p_oidcata NUMBER
  );
  /***************************************************************************
  Descripcion       : Actualiza Posiciones para pasar Monto Minimo SiCC
  Fecha Creacion    : 28/05/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_posic_mm
  (
    pscodigopais VARCHAR2,
    p_oidperi    NUMBER,
    p_fecfact    DATE
  );
  /***************************************************************************
  Descripcion       : Actualiza N?mero de pedidos web consecutivos por consultora
  Fecha Creacion    : 29/05/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_pedid_web
  (
    pscodigopais VARCHAR2,
    p_oidperi    NUMBER,
    p_fecfact    DATE
  );
  /***************************************************************************
  Descripcion       : Reversa solicitud de SiCC a STO
  Fecha Creacion    : 06/06/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_rever_pedid_sto
  (
    pscodigopais VARCHAR2,
    p_oidsoli    NUMBER,
    p_codvali    VARCHAR2,
    p_mtopedid   NUMBER,
    p_motivo     VARCHAR
  );

  /***************************************************************************
  Descripcion       : Calcula Oferta Especial Navidad
  Fecha Creacion    : 18/07/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ofert_navid2
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psfecha         VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Calcula Oferta Especial Navidad
  Fecha Creacion    : 18/07/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ofert_navid
  (
    pscodigopais VARCHAR2,
    p_oidsoli    NUMBER,
    p_oidalma    NUMBER,
    p_oidestra   NUMBER
  );
  /***************************************************************************
  Descripcion       : Calcula Oferta Especial Navidad
  Fecha Creacion    : 18/07/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ofert_navid4
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psfecha         VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Calcula Oferta Especial Navidad
  Fecha Creacion    : 18/07/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ofert_navid3
  (
    pscodigopais VARCHAR2,
    p_oidsoli    NUMBER,
    p_oidalma    NUMBER,
    p_oidestra   NUMBER,
    pscodperi    VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Regenera solicitudes de un consolidado
  Fecha Creacion    : 18/09/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_gener_solic
  (
    pscodigopais VARCHAR2,
    p_oidsoli    NUMBER,
    p_tipo       VARCHAR,
    p_periodo    VARCHAR,
    p_valrecu    VARCHAR2
  );
  /**************************************************************************
  Descripcion : Proceso que ejecuta el Reporte de Desviaci?n de Pedidos.
  Fecha Creacion : 23/10/2013
  Fecha Modificacion  : 23/10/2013
  Autor : Yahir Rivas Luna
  ***************************************************************************/
  PROCEDURE imp_pr_proce_desvi_pedid_gp3
  (
    p_codigopais       IN VARCHAR2,
    p_promedio         IN VARCHAR2,
    p_desviacion       IN VARCHAR2,
    p_fechafacturacion IN VARCHAR2
  );

  /**************************************************************************
  Descripcion        : Devuelve si el pedido tiene excepcion para la validacion
  Fecha Creacion     : 18/09/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_devue_apro_gp2
  (
    pnoidsoli NUMBER,
    pscodvali VARCHAR,
    pscodpais VARCHAR
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Cuadre de Ofertas de Revista
  Fecha Creacion    : 11/08/2014
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_revis
  (
    p_oidsoli    NUMBER,
    pscodigopais VARCHAR
  );

  /***************************************************************************
  Descripcion       : Cuadre de Ofertas de Revista
  Fecha Creacion    : 11/08/2014
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_nx
  (
    p_oidsoli    NUMBER,
    pscodigopais VARCHAR
  );

  /**************************************************************************
  Descripcion        : Devuelve CUV a insertar en proceso de ofertas N por
  Fecha Creacion     : 20/10/2014
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_devue_cuv_nx
  (
    pnoidnxofer NUMBER,
    pnoidprod   NUMBER,
    pnoidsoli   NUMBER,
    pnfacrepe   NUMBER
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion : Proceso que busca que pedidos tienen descuento de cabecera.
  Fecha Creacion : 12/08/2014
  Fecha Modificacion  : 12/08/2014
  Autor : Jorge Yepez
  ***************************************************************************/

  PROCEDURE ped_pr_descu_cabec
  (
    p_codigopais       IN VARCHAR2,
    p_codperi          IN VARCHAR2,
    p_usuario          IN VARCHAR2,
    p_oidperi          IN NUMBER,
    p_fechafacturacion IN VARCHAR2
  );
  /**************************************************************************
  Descripcion : Proceso que busca que pedidos tienen descuento de cabecera
  por canje de premio.
  Fecha Creacion : 18/06/2015
  Fecha Modificacion  : 18/06/2015
  Autor : Jorge Yepez
  ***************************************************************************/

  PROCEDURE ped_pr_descu_cabec2
  (
    p_codigopais       IN VARCHAR2,
    p_codperi          IN VARCHAR2,
    p_usuario          IN VARCHAR2,
    p_oidperi          IN NUMBER,
    p_fechafacturacion IN VARCHAR2
  );
  /**************************************************************************
  Descripcion : Proceso que despacha compuestas fijas de manera completa.
  Fecha Creacion : 16/02/2014
  Fecha Modificacion  : 16/02/2014
  Autor : Jorge Yepez
  ***************************************************************************/

  PROCEDURE ped_pr_despa_compl
  (
    p_codigopais       IN VARCHAR2,
    p_oidperi          IN NUMBER,
    p_fechafacturacion IN VARCHAR2
  );

  PROCEDURE ped_pr_actua_direc_solic1;

  PROCEDURE ped_pr_gener_oport_ahorr(lscodpais IN VARCHAR2);

  /***************************************************************************
  Descripcion       : Elimina Productos Faltantes PROL Individuales
  Fecha Creacion    : 11/08/2014
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_elim_falt_prol_indi
  (
    p_oidsoli    NUMBER,
    pscodigopais VARCHAR
  );

  /***************************************************************************
  Descripcion       : Elimina Productos Faltantes PROL Compuesta Fija
  Fecha Creacion    : 11/08/2014
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_elim_falt_prol_cfija
  (
    p_oidsoli    NUMBER,
    pscodigopais VARCHAR
  );

  /***************************************************************************
  Descripcion       : Carga matriz de Facturación por lote desde Planit
  Fecha Creacion    : 17/07/2015
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_carg_matr_plan
  (
    p_numlote    VARCHAR,
    pscodigopais VARCHAR
  );

  /***************************************************************************
  Descripcion       : Carga matriz de Facturación por lote desde Planit
  Fecha Creacion    : 17/07/2015
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_limit_venta
  (
    p_oidsoli    NUMBER
  )
;


  /***************************************************************************
  Descripcion       : Reemplazos en un pedido
  Fecha Creacion    : 18/08/2015
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_reemp_pedid
  (
    p_oidsoli    NUMBER,
    p_tiporee    NUMBER
  )
  ;

  /***************************************************************************
  Descripcion       : Procea el pedido enviado desde el WS
  Fecha Creacion    : 29/09/2015
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE ped_pr_gener_pedid_ws
  (
    p_numcorr    VARCHAR
  )
;
  /***************************************************************************
  Descripcion       : Cambia a GP3 los pedidos aptos que se validaron en GP1
  Fecha Creacion    : 15/01/2016
  Autor             : Jorge Yepez
  Fecha Actualiza   : 15/01/2016
  Autor Actualiza   : Carlos Diaz Valverde
  ***************************************************************************/
  PROCEDURE ped_pr_cambi_gp1_gp3
  (
    p_codigopais IN VARCHAR2,
    p_fechafact  IN VARCHAR2,
    p_codperi    IN VARCHAR2
  ) ;

END ped_pkg_cuadr_ofert;
/
CREATE OR REPLACE PACKAGE BODY ped_pkg_cuadr_ofert IS
  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);
  tmp        NUMBER(10);
  /***************************************************************************
  Descripcion       : Cuadre de Ofertas
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_ofert_sicc
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER,
    p_oid_terr_admi IN NUMBER
  ) IS
  
  BEGIN
  
    -- Limite de Venta Inicial
  
    ped_pr_limit_venta(p_oidsoli);
    
  
    --Reemplazos Antes del cuadre
  
    ped_pr_reemp_pedid(p_oidsoli,1);
  
    -- Proceso de Cuadre
    ped_pr_cuadr_cf(p_oidsoli,
                    p_clie_oid_clie,
                    p_perd_oid_peri);
    ped_pr_cuadr_cv(p_oidsoli,
                    p_clie_oid_clie,
                    p_perd_oid_peri);
    ped_pr_cuadr_promo(p_oidsoli,
                       p_clie_oid_clie,
                       p_perd_oid_peri);
    ped_pr_cuadr_condi(p_oidsoli,
                       p_clie_oid_clie,
                       p_perd_oid_peri);

    ped_pr_cuadr_revis(p_oidsoli,'');

    ped_pr_cuadr_nx(p_oidsoli,'');
  
  
    --Reemplazos Despues del cuadre


  
    ped_pr_reemp_pedid(p_oidsoli,0);

  
    -- Se Aplica el Factor de Repeticion
  
    UPDATE ped_solic_posic a
       SET a.num_unid_por_aten = a.num_unid_por_aten * (select val_fact_repe from pre_ofert_detal where oid_deta_ofer=a.ofde_oid_deta_ofer)
     WHERE a.soca_oid_soli_cabe = p_oidsoli
       ;

    UPDATE ped_solic_posic a
       SET a.num_unid_dema_real = a.num_unid_por_aten,
           a.num_unid_compr =  a.num_unid_por_aten
     WHERE a.soca_oid_soli_cabe = p_oidsoli
       ;

    -- Limite de Venta Final

    ped_pr_limit_venta(p_oidsoli);

  
  EXCEPTION
    WHEN OTHERS THEN
    update ped_solic_cabec set esso_oid_esta_soli=6, val_obse_revi=ls_sqlerrm
    where oid_soli_cabe=p_oidsoli;
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_cuadr_ofert_sicc: ' ||
                              ls_sqlerrm || ' cliente:' || p_clie_oid_clie);
  END ped_pr_cuadr_ofert_sicc;

  /***************************************************************************
  Descripcion       : Cuadre de Ofertas
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_ofert(p_oid_cabe IN NUMBER) IS
  
    --w_filas        NUMBER(12);
  
    v_oidclie     NUMBER(12);
    v_oidperi     NUMBER(12);
    v_oidterradmi NUMBER(12);
  
  BEGIN

  
    SELECT clie_oid_clie,
           perd_oid_peri,
           ztad_oid_terr_admi
      INTO v_oidclie,
           v_oidperi,
           v_oidterradmi
      FROM ped_solic_cabec
     WHERE oid_soli_cabe = p_oid_cabe;
  

  
    ped_pr_cuadr_ofert_sicc(p_oid_cabe,
                            v_oidclie,
                            v_oidperi,
                            v_oidterradmi);
  

  
  EXCEPTION
    WHEN OTHERS THEN
    ln_sqlcode := SQLCODE;
    ls_sqlerrm := substr(SQLERRM,
                         1,
                         250);
    update ped_solic_cabec set esso_oid_esta_soli=6, val_obse_revi=ls_sqlerrm
    where oid_soli_cabe=p_oid_cabe;

    raise_application_error(-20123,
                            'ERROR ped_pr_cuadr_ofert: ' || ls_sqlerrm);
  END ped_pr_cuadr_ofert;

  /***************************************************************************
  Descripcion       : Cuadre de Ofertas
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_ofert_sto(p_oid_cabe IN NUMBER) IS
  
    --w_filas        NUMBER(12);
  
    v_oidclie     NUMBER(12);
    v_oidperi     NUMBER(12);
    v_oidterradmi NUMBER(12);
    v_hash        NUMBER(12);
    v_hash_ant    NUMBER(12);
  
  BEGIN
  
NULL;  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_cuadr_ofert_sto: ' ||
                              ls_sqlerrm);
  END ped_pr_cuadr_ofert_sto;
  /***************************************************************************
  Descripcion       : Cuadre CV
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_cv
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER
  ) IS
    w_filas                        NUMBER := 1000;
    varcuadregrupo                 NUMBER(12);
    varresiduo                     NUMBER(12);
    varunidins                     NUMBER(12);
    varoidoferant                  NUMBER(12);
    varoidofer                     NUMBER(12);
    varinddespauto                 NUMBER(12);
    varoidgrupooferta              NUMBER(12);
    varcues_oid_ind_cuad_tipo_estr NUMBER(1);
    varcod_fact_cuad               NUMBER(12);
    varsuma                        NUMBER(12);
  
    varoiddetaofer      NUMBER(12);
    varprodoidprod      NUMBER(12);
    varvalfactrepe      NUMBER(2);
    varimppreccata      NUMBER(12, 2);
    varimpprecposi      NUMBER(12, 2);
    varfopaoidformapago NUMBER(12);
    varoidsolicabe      NUMBER(12);
    varvalcodivent      VARCHAR2(5);
    varpreciounitario   NUMBER(12, 2);
    varpagicata         NUMBER(3);
    varcata             NUMBER(12);
    varfactrepe         NUMBER(3);
    varfopa             NUMBER(12);
  
    CURSOR c_ofertas_cv IS
    
      SELECT DISTINCT xx.ofer_oid_ofer,
                      ind_desp_auto,
                      oid_grup_ofer,
                      cues_oid_ind_cuad_tipo_estr,
                      cod_fact_cuad,
                      oid_soli_cabe,
                      nvl((SELECT SUM(b.num_unid_por_aten)
                            FROM ped_solic_cabec a,
                                 ped_solic_posic b,
                                 pre_ofert_detal c,
                                 pre_ofert       d,
                                 pre_grupo_ofert h
                           WHERE a.oid_soli_cabe = p_oidsoli
                             AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                             and b.ofde_oid_deta_ofer=c.oid_deta_ofer
                             and c.ofer_oid_ofer=d.oid_ofer
                             AND d.coes_oid_estr = 2003
                             AND c.ofer_oid_ofer = h.ofer_oid_ofer
                             AND c.gofe_oid_grup_ofer = h.oid_grup_ofer
                             AND h.cues_oid_ind_cuad_tipo_estr IN (1)
                             AND h.oid_grup_ofer = xx.oid_grup_ofer),
                          0) suma
        FROM pre_grupo_ofert xx,
             (SELECT DISTINCT c.ofer_oid_ofer,
                              d.ind_desp_auto,
                              c.gofe_oid_grup_ofer,
                              a.oid_soli_cabe
                FROM ped_solic_cabec a,
                     ped_solic_posic b,
                     pre_ofert_detal c,
                     pre_ofert       d
               WHERE a.oid_soli_cabe = p_oidsoli
                 and a.oid_soli_cabe=b.soca_oid_soli_cabe
                 and b.ofde_oid_deta_ofer=c.oid_deta_ofer
                 and c.ofer_oid_ofer=d.oid_ofer
                 AND d.coes_oid_estr = 2003) yy
       WHERE xx.ofer_oid_ofer = yy.ofer_oid_ofer
         AND xx.cues_oid_ind_cuad_tipo_estr IN (1)
       ORDER BY 1 DESC,
                (suma / cod_fact_cuad) DESC,
                3 DESC;
  
    TYPE cvrec IS RECORD(
      varoidofer                     NUMBER(12),
      varinddespauto                 NUMBER(12),
      varoidgrupooferta              NUMBER(12),
      varcues_oid_ind_cuad_tipo_estr NUMBER(1),
      varcod_fact_cuad               NUMBER(12),
      varoidsolicabe                 NUMBER(12),
      varsuma                        NUMBER(12));
    TYPE cvrectab IS TABLE OF cvrec;
    cvrecord cvrectab;
  
    CURSOR c_ofertas_cv2 IS
    
      SELECT DISTINCT xx.ofer_oid_ofer,
                      ind_desp_auto,
                      oid_grup_ofer,
                      cues_oid_ind_cuad_tipo_estr,
                      cod_fact_cuad,
                      oid_soli_cabe,
                      nvl((SELECT SUM(b.num_unid_por_aten)
                            FROM ped_solic_cabec a,
                                 ped_solic_posic b,
                                 pre_ofert_detal c,
                                 pre_ofert       d,
                                 pre_grupo_ofert h
                           WHERE a.oid_soli_cabe = p_oidsoli
                             AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                             and b.ofde_oid_deta_ofer=c.oid_deta_ofer
                             and c.ofer_oid_ofer = d.oid_ofer
                             AND d.coes_oid_estr = 2003
                             AND c.ofer_oid_ofer = h.ofer_oid_ofer
                             AND c.gofe_oid_grup_ofer = h.oid_grup_ofer
                             AND h.cues_oid_ind_cuad_tipo_estr = 2
                             AND h.oid_grup_ofer = xx.oid_grup_ofer),
                          0) suma
        FROM pre_grupo_ofert xx,
             (SELECT DISTINCT c.ofer_oid_ofer,
                              d.ind_desp_auto,
                              c.gofe_oid_grup_ofer,
                              a.oid_soli_cabe
                FROM ped_solic_cabec a,
                     ped_solic_posic b,
                     pre_ofert_detal c,
                     pre_ofert       d
               WHERE a.oid_soli_cabe = p_oidsoli
                 AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                 and b.ofde_oid_deta_ofer = c.oid_deta_ofer
                 and c.ofer_oid_ofer = d.oid_ofer
                 AND d.coes_oid_estr = 2003) yy
       WHERE xx.ofer_oid_ofer = yy.ofer_oid_ofer
         AND xx.cues_oid_ind_cuad_tipo_estr = 2
       ORDER BY 1 DESC,
                (suma / cod_fact_cuad) ASC,
                3 DESC;
  
    TYPE cv2rec IS RECORD(
      varoidofer                     NUMBER(12),
      varinddespauto                 NUMBER(12),
      varoidgrupooferta              NUMBER(12),
      varcues_oid_ind_cuad_tipo_estr NUMBER(1),
      varcod_fact_cuad               NUMBER(12),
      varoidsolicabe                 NUMBER(12),
      varsuma                        NUMBER(12));
    TYPE cv2rectab IS TABLE OF cv2rec;
    cv2record cv2rectab;
  
    CURSOR c_ofertas_cv3 IS
    
      SELECT DISTINCT xx.ofer_oid_ofer,
                      ind_desp_auto,
                      oid_grup_ofer,
                      cues_oid_ind_cuad_tipo_estr,
                      cod_fact_cuad,
                      oid_soli_cabe,
                      nvl((SELECT SUM(b.num_unid_por_aten)
                            FROM ped_solic_cabec a,
                                 ped_solic_posic b,
                                 pre_ofert_detal c,
                                 pre_ofert       d,
                                 pre_grupo_ofert h
                           WHERE a.oid_soli_cabe = p_oidsoli
                             AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                             and b.ofde_oid_deta_ofer=c.oid_deta_ofer
                             and c.ofer_oid_ofer=d.oid_ofer
                             AND d.coes_oid_estr = 2003
                             AND c.ofer_oid_ofer = h.ofer_oid_ofer
                             AND c.gofe_oid_grup_ofer = h.oid_grup_ofer
                             AND h.cues_oid_ind_cuad_tipo_estr = 3
                             AND h.oid_grup_ofer = xx.oid_grup_ofer),
                          0) suma
        FROM pre_grupo_ofert xx,
             (SELECT DISTINCT c.ofer_oid_ofer,
                              d.ind_desp_auto,
                              c.gofe_oid_grup_ofer,
                              a.oid_soli_cabe
                FROM ped_solic_cabec a,
                     ped_solic_posic b,
                     pre_ofert_detal c,
                     pre_ofert       d
               WHERE a.oid_soli_cabe = p_oidsoli
                 AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                 and b.ofde_oid_deta_ofer=c.oid_deta_ofer
                 and c.ofer_oid_ofer=d.oid_ofer
                 AND d.coes_oid_estr = 2003) yy
       WHERE xx.ofer_oid_ofer = yy.ofer_oid_ofer
         AND xx.cues_oid_ind_cuad_tipo_estr IN (3)
       ORDER BY 1 DESC,
                (suma / cod_fact_cuad) ASC,
                3 DESC;
  
    TYPE cv3rec IS RECORD(
      varoidofer                     NUMBER(12),
      varinddespauto                 NUMBER(12),
      varoidgrupooferta              NUMBER(12),
      varcues_oid_ind_cuad_tipo_estr NUMBER(1),
      varcod_fact_cuad               NUMBER(12),
      varoidsolicabe                 NUMBER(12),
      varsuma                        NUMBER(12));
    TYPE cv3rectab IS TABLE OF cv3rec;
    cv3record cv3rectab;
  
  BEGIN
    w_filas       := 1000;
    varoidoferant := 0;
  
    OPEN c_ofertas_cv;
    LOOP
      FETCH c_ofertas_cv BULK COLLECT
        INTO cvrecord LIMIT w_filas;
      IF cvrecord.count > 0 THEN
        FOR x IN cvrecord.first .. cvrecord.last
        LOOP
        
          varoidofer                     := cvrecord(x).varoidofer;
          varinddespauto                 := cvrecord(x).varinddespauto;
          varoidgrupooferta              := cvrecord(x).varoidgrupooferta;
          varcues_oid_ind_cuad_tipo_estr := cvrecord(x)
                                            .varcues_oid_ind_cuad_tipo_estr;
          varcod_fact_cuad               := cvrecord(x).varcod_fact_cuad;
          varoidsolicabe                 := cvrecord(x).varoidsolicabe;
          varsuma                        := cvrecord(x).varsuma;
        
          IF varoidofer <> varoidoferant THEN
            varcuadregrupo := 0;
          END IF;
          IF varcod_fact_cuad > varsuma THEN
            IF varcuadregrupo < varcod_fact_cuad THEN
              varcuadregrupo := 1; --varcod_fact_cuad;
            END IF;
          ELSE
            IF (varcuadregrupo * varcod_fact_cuad) < varsuma THEN
              varcuadregrupo := varsuma / varcod_fact_cuad;
              varresiduo     := MOD(varsuma, varcod_fact_cuad);
            END IF;
          END IF;
          IF (varcuadregrupo * varcod_fact_cuad) > varsuma THEN
          
            varunidins := (varcuadregrupo * varcod_fact_cuad) - varsuma;
          
            IF varunidins > 0 THEN
            
              SELECT oid_deta_ofer,
                     prod_oid_prod,
                     val_fact_repe,
                     imp_prec_cata,
                     imp_prec_posi,
                     fopa_oid_form_pago,
                     val_codi_vent,
                     precio_unitario,
                     num_pagi_cata,
                     ocat_oid_catal,
                     val_fact_repe,
                     fopa_oid_form_pago
                INTO varoiddetaofer,
                     varprodoidprod,
                     varvalfactrepe,
                     varimppreccata,
                     varimpprecposi,
                     varfopaoidformapago,
                     varvalcodivent,
                     varpreciounitario,
                     varpagicata,
                     varcata,
                     varfactrepe,
                     varfopa
                FROM (SELECT *
                        FROM pre_ofert_detal a
                       WHERE a.ofer_oid_ofer = varoidofer
                         AND a.gofe_oid_grup_ofer = varoidgrupooferta
                       ORDER BY a.num_posi_rank ASC)
               WHERE rownum = 1;
            
              SELECT nvl((SELECT SUM(1)
                           FROM ped_solic_posic b
                          WHERE b.soca_oid_soli_cabe = p_oidsoli
                            AND b.val_codi_vent = varvalcodivent),
                         0)
                INTO tmp
                FROM dual;
            
              IF tmp > 0 THEN
                UPDATE ped_solic_posic a
                   SET a.num_unid_por_aten = a.num_unid_por_aten + varunidins
                 WHERE a.soca_oid_soli_cabe = p_oidsoli
                   AND a.val_codi_vent = varvalcodivent;
              ELSE


                    INSERT INTO ped_solic_posic
                      (oid_soli_posi,
                       cod_posi,
                       num_unid_dema,
                       num_unid_por_aten,
                       val_tasa_impu,
                       soca_oid_soli_cabe,
                       taim_oid_tasa_impu,
                       tpos_oid_tipo_posi,
                       prod_oid_prod,
                       fopa_oid_form_pago,
                       val_prec_cata_unit_loca,
                       val_prec_cont_unit_loca,
                       val_prec_cata_unit_docu,
                       val_prec_conta_unit_docu,
                       val_prec_fact_unit_loca,
                       val_prec_fact_unit_docu,
                       val_prec_sin_impu_unit_loca,
                       val_prec_sin_impu_unit_docu,
                       val_prec_sin_impu_tota_docu,
                       val_impo_desc_unit_loca,
                       val_impo_desc_unit_docu,
                       val_prec_neto_unit_loca,
                       val_prec_neto_tota_docu,
                       val_prec_neto_unit_docu,
                       val_prec_tota_tota_loca,
                       val_prec_tota_tota_docu,
                       val_impo_impu_unit_loca,
                       val_impo_impu_unit_docu,
                       val_impo_desc_tota_docu,
                       val_impo_impu_tota_loca,
                       val_impo_impu_tota_docu,
                       val_impo_desc_tota_loca,
                       val_prec_tota_unit_loca,
                       val_prec_tota_unit_docu,
                       val_prec_cont_tota_loca,
                       val_prec_cata_tota_loca,
                       val_prec_cata_tota_docu,
                       val_prec_cont_tota_docu,
                       val_porc_desc,
                       val_prec_cata_tota_loca_unid,
                       num_unid_dema_real,
                       num_unid_compr,
                       val_prec_fact_tota_loca,
                       val_prec_fact_tota_docu,
                       val_prec_sin_impu_tota_loca,
                       val_prec_neto_tota_loca,
                       ofde_oid_deta_ofer,
                       espo_oid_esta_posi,
                       stpo_oid_subt_posi,
                       val_codi_vent,
                       ind_no_impr,
                       oid_nive_ofer,
                       oid_nive_ofer_rang,
                       num_unid_orig,
                       val_codi_orig)
                    VALUES
                      (ped_sopo_seq.nextval,
                       (SELECT MAX(cod_posi)
                          FROM ped_solic_posic
                         WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
                       varunidins,
                       varunidins,
                       0,
                       p_oidsoli,
                       NULL,
                       4,
                       varprodoidprod,
                       varfopaoidformapago,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       varunidins,
                       varunidins,
                       0,
                       0,
                       0,
                       0,
                       varoiddetaofer,
                       4,
                       5,
                       varvalcodivent,
                       0,
                       NULL,
                       NULL,
                       NULL,
                       NULL);

              

              
              END IF;
            
            END IF;
          END IF;
          varoidoferant := varoidofer;
        
        END LOOP;
      END IF;
      EXIT WHEN c_ofertas_cv%NOTFOUND;
    END LOOP;
    CLOSE c_ofertas_cv;
  
    -- Se cuadra al multiplo
  
    OPEN c_ofertas_cv3;
    LOOP
      FETCH c_ofertas_cv3 BULK COLLECT
        INTO cv3record LIMIT w_filas;
      IF cv3record.count > 0 THEN
        FOR x IN cv3record.first .. cv3record.last
        LOOP
        
          varoidofer                     := cv3record(x).varoidofer;
          varinddespauto                 := cv3record(x).varinddespauto;
          varoidgrupooferta              := cv3record(x).varoidgrupooferta;
          varcues_oid_ind_cuad_tipo_estr := cv3record(x)
                                            .varcues_oid_ind_cuad_tipo_estr;
          varcod_fact_cuad               := cv3record(x).varcod_fact_cuad;
          varoidsolicabe                 := cv3record(x).varoidsolicabe;
          varsuma                        := cv3record(x).varsuma;
        
          IF varsuma > varcod_fact_cuad THEN
            IF MOD(varsuma, varcod_fact_cuad) = 0 THEN
              varunidins := 0;
            ELSE
              varunidins := varcod_fact_cuad -
                            MOD(varsuma, varcod_fact_cuad);
            END IF;
          ELSE
            varunidins := varcod_fact_cuad - varsuma;
          END IF;
        
          IF varunidins > 0 THEN
          
            SELECT oid_deta_ofer,
                   prod_oid_prod,
                   val_fact_repe,
                   imp_prec_cata,
                   imp_prec_posi,
                   fopa_oid_form_pago,
                   val_codi_vent,
                   precio_unitario,
                   num_pagi_cata,
                   ocat_oid_catal,
                   val_fact_repe,
                   fopa_oid_form_pago
              INTO varoiddetaofer,
                   varprodoidprod,
                   varvalfactrepe,
                   varimppreccata,
                   varimpprecposi,
                   varfopaoidformapago,
                   varvalcodivent,
                   varpreciounitario,
                   varpagicata,
                   varcata,
                   varfactrepe,
                   varfopa
              FROM (SELECT *
                      FROM pre_ofert_detal a
                     WHERE a.ofer_oid_ofer = varoidofer
                       AND a.gofe_oid_grup_ofer = varoidgrupooferta
                     ORDER BY a.num_posi_rank ASC)
             WHERE rownum = 1;
          
            SELECT nvl((SELECT SUM(1)
                         FROM ped_solic_posic a
                        WHERE a.soca_oid_soli_cabe = p_oidsoli
                          AND a.val_codi_vent = varvalcodivent),
                       0)
              INTO tmp
              FROM dual;
          
            IF tmp > 0 THEN
              UPDATE ped_solic_posic a
                 SET a.num_unid_por_aten = a.num_unid_por_aten + varunidins
               WHERE a.soca_oid_soli_cabe = p_oidsoli
                 AND a.val_codi_vent = varvalcodivent;
            ELSE
            
                    INSERT INTO ped_solic_posic
                      (oid_soli_posi,
                       cod_posi,
                       num_unid_dema,
                       num_unid_por_aten,
                       val_tasa_impu,
                       soca_oid_soli_cabe,
                       taim_oid_tasa_impu,
                       tpos_oid_tipo_posi,
                       prod_oid_prod,
                       fopa_oid_form_pago,
                       val_prec_cata_unit_loca,
                       val_prec_cont_unit_loca,
                       val_prec_cata_unit_docu,
                       val_prec_conta_unit_docu,
                       val_prec_fact_unit_loca,
                       val_prec_fact_unit_docu,
                       val_prec_sin_impu_unit_loca,
                       val_prec_sin_impu_unit_docu,
                       val_prec_sin_impu_tota_docu,
                       val_impo_desc_unit_loca,
                       val_impo_desc_unit_docu,
                       val_prec_neto_unit_loca,
                       val_prec_neto_tota_docu,
                       val_prec_neto_unit_docu,
                       val_prec_tota_tota_loca,
                       val_prec_tota_tota_docu,
                       val_impo_impu_unit_loca,
                       val_impo_impu_unit_docu,
                       val_impo_desc_tota_docu,
                       val_impo_impu_tota_loca,
                       val_impo_impu_tota_docu,
                       val_impo_desc_tota_loca,
                       val_prec_tota_unit_loca,
                       val_prec_tota_unit_docu,
                       val_prec_cont_tota_loca,
                       val_prec_cata_tota_loca,
                       val_prec_cata_tota_docu,
                       val_prec_cont_tota_docu,
                       val_porc_desc,
                       val_prec_cata_tota_loca_unid,
                       num_unid_dema_real,
                       num_unid_compr,
                       val_prec_fact_tota_loca,
                       val_prec_fact_tota_docu,
                       val_prec_sin_impu_tota_loca,
                       val_prec_neto_tota_loca,
                       ofde_oid_deta_ofer,
                       espo_oid_esta_posi,
                       stpo_oid_subt_posi,
                       val_codi_vent,
                       ind_no_impr,
                       oid_nive_ofer,
                       oid_nive_ofer_rang,
                       num_unid_orig,
                       val_codi_orig)
                    VALUES
                      (ped_sopo_seq.nextval,
                       (SELECT MAX(cod_posi)
                          FROM ped_solic_posic
                         WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
                       varunidins,
                       varunidins,
                       0,
                       p_oidsoli,
                       NULL,
                       4,
                       varprodoidprod,
                       varfopaoidformapago,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       varunidins,
                       varunidins,
                       0,
                       0,
                       0,
                       0,
                       varoiddetaofer,
                       4,
                       5,
                       varvalcodivent,
                       0,
                       NULL,
                       NULL,
                       NULL,
                       NULL);
            
            END IF;
          
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_ofertas_cv3%NOTFOUND;
    END LOOP;
    CLOSE c_ofertas_cv3;
  
    -- Se cuadra al Menor por Unidades
    varunidins := 0;
    OPEN c_ofertas_cv2;
    LOOP
      FETCH c_ofertas_cv2 BULK COLLECT
        INTO cv2record LIMIT w_filas;
      IF cv2record.count > 0 THEN
        FOR x IN cv2record.first .. cv2record.last
        LOOP
        
          varoidofer                     := cv2record(x).varoidofer;
          varinddespauto                 := cv2record(x).varinddespauto;
          varoidgrupooferta              := cv2record(x).varoidgrupooferta;
          varcues_oid_ind_cuad_tipo_estr := cv2record(x)
                                            .varcues_oid_ind_cuad_tipo_estr;
          varcod_fact_cuad               := cv2record(x).varcod_fact_cuad;
          varoidsolicabe                 := cv2record(x).varoidsolicabe;
          varsuma                        := cv2record(x).varsuma;
        
          IF varoidofer <> varoidoferant THEN
            varcuadregrupo := 0;
          END IF;
        
          IF varcuadregrupo = 0 THEN
            varcuadregrupo := varsuma / varcod_fact_cuad;
            varresiduo     := MOD(varsuma, varcod_fact_cuad);
          END IF;
        
          IF varsuma > varcuadregrupo THEN
            varunidins := varsuma - (varcuadregrupo * varcod_fact_cuad);
          END IF;
        
          IF varunidins > 0 THEN
          
            SELECT val_codi_vent
              INTO varvalcodivent
              FROM (SELECT *
                      FROM pre_ofert_detal a
                     WHERE a.ofer_oid_ofer = varoidofer
                       AND a.gofe_oid_grup_ofer = varoidgrupooferta
                       AND EXISTS
                     (SELECT *
                              FROM ped_solic_posic b
                             WHERE b.val_codi_vent = a.val_codi_vent
                               AND b.soca_oid_soli_cabe = p_oidsoli
                               )
                     ORDER BY a.num_posi_rank DESC)
             WHERE rownum = 1;
          
            UPDATE ped_solic_posic a
               SET a.num_unid_por_aten = a.num_unid_por_aten - varunidins
             WHERE a.soca_oid_soli_cabe = p_oidsoli
               AND a.val_codi_vent = varvalcodivent;
          
          END IF;
        
          varoidoferant := varoidofer;
        
        END LOOP;
      END IF;
      EXIT WHEN c_ofertas_cv2%NOTFOUND;
    END LOOP;
    CLOSE c_ofertas_cv2;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_cuadr_cv: ' || ls_sqlerrm);
  END ped_pr_cuadr_cv;
  /***************************************************************************
  Descripcion       : Cuadre CV Promociones
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_cv_promo
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER
  ) IS
    w_filas                        NUMBER := 1000;
    varcuadregrupo                 NUMBER(12);
    varresiduo                     NUMBER(12);
    varunidins                     NUMBER(12);
    varoidoferant                  NUMBER(12);
    varoidofer                     NUMBER(12);
    varinddespauto                 NUMBER(12);
    varoidgrupooferta              NUMBER(12);
    varcues_oid_ind_cuad_tipo_estr NUMBER(1);
    varcod_fact_cuad               NUMBER(12);
    varoid_prom                    NUMBER(12);
    varicpr_oid_indi_cuad_prom     NUMBER(12);
    varval_fact_cuad               NUMBER(12, 2);
    varsuma                        NUMBER(12);
    varaux                         NUMBER(12);
  
    varoiddetaofer      NUMBER(12);
    varprodoidprod      NUMBER(12);
    varvalfactrepe      NUMBER(2);
    varimppreccata      NUMBER(12, 2);
    varimpprecposi      NUMBER(12, 2);
    varfopaoidformapago NUMBER(12);
    varoidsolicabe      NUMBER(12);
    varvalcodivent      VARCHAR2(5);
    varpreciounitario   NUMBER(12, 2);
    varpagicata         NUMBER(3);
    varcata             NUMBER(12);
    varfactrepe         NUMBER(3);
    varfopa             NUMBER(12);
  
    CURSOR c_ofertas_cv IS
    
      SELECT DISTINCT xx.ofer_oid_ofer,
                      ind_desp_auto,
                      oid_grup_ofer,
                      cues_oid_ind_cuad_tipo_estr,
                      cod_fact_cuad,
                      oid_soli_cabe,
                      nvl((SELECT SUM(b.num_unid_por_aten)
                            FROM ped_solic_cabec a,
                                 ped_solic_posic b,
                                 pre_ofert_detal c,
                                 pre_ofert       d,
                                 pre_grupo_ofert h
                           WHERE a.oid_soli_cabe = p_oidsoli
                             AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                             and b.ofde_oid_deta_ofer = c.oid_deta_ofer
                             and c.ofer_oid_ofer = d.oid_ofer
                             AND d.coes_oid_estr = (2007)
                             AND c.ofer_oid_ofer = h.ofer_oid_ofer
                             AND c.gofe_oid_grup_ofer = h.oid_grup_ofer
                             AND h.cues_oid_ind_cuad_tipo_estr IN (6)
                             AND h.oid_grup_ofer = xx.oid_grup_ofer),
                          0) suma
        FROM pre_grupo_ofert xx,
             (SELECT DISTINCT c.ofer_oid_ofer,
                              d.ind_desp_auto,
                              c.gofe_oid_grup_ofer,
                              a.oid_soli_cabe
                FROM ped_solic_cabec a,
                     ped_solic_posic b,
                     pre_ofert_detal c,
                     pre_ofert       d
               WHERE a.oid_soli_cabe = p_oidsoli
                 and a.oid_soli_cabe = b.soca_oid_soli_cabe
                 and b.ofde_oid_deta_ofer = c.oid_deta_ofer
                 and c.ofer_oid_ofer = d.oid_ofer
                 AND d.coes_oid_estr = 2007
                 AND (SELECT SUM(y.num_unid_por_aten)
                        FROM ped_solic_cabec x, ped_solic_posic y, pre_ofert_detal z
                       WHERE x.oid_soli_cabe = p_oidsoli
                         AND x.oid_soli_cabe = y.soca_oid_soli_cabe
                         AND y.ofde_oid_deta_ofer = z.oid_deta_ofer
                         AND z.ofer_oid_ofer = c.ofer_oid_ofer) > 0) yy
       WHERE xx.ofer_oid_ofer = yy.ofer_oid_ofer
         AND xx.cues_oid_ind_cuad_tipo_estr IN (6)
       ORDER BY 1 DESC,
                (suma / cod_fact_cuad) DESC,
                3 DESC;
  
    TYPE cvrec IS RECORD(
      varoidofer                     NUMBER(12),
      varinddespauto                 NUMBER(12),
      varoidgrupooferta              NUMBER(12),
      varcues_oid_ind_cuad_tipo_estr NUMBER(1),
      varcod_fact_cuad               NUMBER(12),
      varoidsolicabe                 NUMBER(12),
      varsuma                        NUMBER(12));
    TYPE cvrectab IS TABLE OF cvrec;
    cvrecord cvrectab;
  
    CURSOR c_ofertas_cv2 IS
    
      SELECT DISTINCT ofer_oid_ofer,
                      ind_desp_auto,
                      oid_grup_ofer,
                      cues_oid_ind_cuad_tipo_estr,
                      cod_fact_cuad,
                      oid_soli_cabe,
                      nvl((SELECT SUM(b.num_unid_por_aten)
                            FROM ped_solic_cabec a,
                                 ped_solic_posic b,
                                 pre_ofert_detal c,
                                 pre_ofert d,
                                 pre_grupo_ofert h
                           WHERE a.oid_soli_cabe = p_oidsoli
                             AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                             and b.ofde_oid_deta_ofer = c.oid_deta_ofer
                             and c.ofer_oid_ofer = d.oid_ofer
                             AND d.coes_oid_estr = 2007
                             AND c.ofer_oid_ofer = h.ofer_oid_ofer
                             AND c.gofe_oid_grup_ofer = h.oid_grup_ofer
                             AND h.cues_oid_ind_cuad_tipo_estr = 7
                             AND h.oid_grup_ofer = xx.oid_grup_ofer),
                          0) suma
        FROM pre_grupo_ofert xx,
             (SELECT DISTINCT d.oid_ofer,
                              d.ind_desp_auto,
                              c.gofe_oid_grup_ofer,
                              a.oid_soli_cabe
                FROM ped_solic_cabec a,
                     ped_solic_posic b,
                     pre_ofert_detal c,
                     pre_ofert d
               WHERE a.oid_soli_cabe = p_oidsoli
                 and a.oid_soli_cabe=b.soca_oid_soli_cabe
                 and b.ofde_oid_deta_ofer = c.oid_deta_ofer
                 and c.ofer_oid_ofer = d.oid_ofer
                 AND d.coes_oid_estr = 2007) yy
       WHERE xx.ofer_oid_ofer = yy.oid_ofer
         AND xx.cues_oid_ind_cuad_tipo_estr = 7
       ORDER BY 1 DESC,
                (suma / cod_fact_cuad) ASC,
                3 DESC;
  
    TYPE cv2rec IS RECORD(
      varoidofer                     NUMBER(12),
      varinddespauto                 NUMBER(12),
      varoidgrupooferta              NUMBER(12),
      varcues_oid_ind_cuad_tipo_estr NUMBER(1),
      varcod_fact_cuad               NUMBER(12),
      varoidsolicabe                 NUMBER(12),
      varsuma                        NUMBER(12));
    TYPE cv2rectab IS TABLE OF cv2rec;
    cv2record cv2rectab;
  
    CURSOR c_ofertas_cv3 IS
    
      SELECT DISTINCT ofer_oid_ofer,
                      ind_desp_auto,
                      oid_grup_ofer,
                      cues_oid_ind_cuad_tipo_estr,
                      cod_fact_cuad,
                      oid_soli_cabe,
                      nvl((SELECT SUM(b.num_unid_por_aten)
                            FROM ped_solic_cabec a,
                                 ped_solic_posic b,
                                 pre_ofert_detal c,
                                 pre_ofert d,
                                 pre_grupo_ofert h
                           WHERE a.oid_soli_cabe = p_oidsoli
                             and a.oid_soli_cabe = b.soca_oid_soli_cabe
                             and b.ofde_oid_deta_ofer = c.oid_deta_ofer
                             and c.ofer_oid_ofer = d.oid_ofer
                             AND d.coes_oid_estr = 2007
                             AND c.ofer_oid_ofer = h.ofer_oid_ofer
                             AND c.gofe_oid_grup_ofer = h.oid_grup_ofer
                             AND h.cues_oid_ind_cuad_tipo_estr = 8
                             AND h.oid_grup_ofer = xx.oid_grup_ofer),
                          0) suma
        FROM pre_grupo_ofert xx,
             (SELECT DISTINCT d.oid_ofer,
                              d.ind_desp_auto,
                              c.gofe_oid_grup_ofer,
                              a.oid_soli_cabe
                FROM ped_solic_cabec a,
                     ped_solic_posic b,
                     pre_ofert_detal c,
                     pre_ofert       d
               WHERE a.oid_soli_cabe = p_oidsoli
                 AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                 and b.ofde_oid_deta_ofer = c.oid_deta_ofer
                 and c.ofer_oid_ofer = d.oid_ofer
                 AND d.coes_oid_estr = 2007) yy
       WHERE xx.ofer_oid_ofer = yy.oid_ofer
         AND xx.cues_oid_ind_cuad_tipo_estr IN (8)
       ORDER BY 1 DESC,
                (suma / cod_fact_cuad) ASC,
                3 DESC;
  
    TYPE cv3rec IS RECORD(
      varoidofer                     NUMBER(12),
      varinddespauto                 NUMBER(12),
      varoidgrupooferta              NUMBER(12),
      varcues_oid_ind_cuad_tipo_estr NUMBER(1),
      varcod_fact_cuad               NUMBER(12),
      varoidsolicabe                 NUMBER(12),
      varsuma                        NUMBER(12));
    TYPE cv3rectab IS TABLE OF cv3rec;
    cv3record cv3rectab;
  
  BEGIN
    w_filas       := 1000;
    varoidoferant := 0;
  
    OPEN c_ofertas_cv;
    LOOP
      FETCH c_ofertas_cv BULK COLLECT
        INTO cvrecord LIMIT w_filas;
      IF cvrecord.count > 0 THEN
        FOR x IN cvrecord.first .. cvrecord.last
        LOOP
        
          varoidofer                     := cvrecord(x).varoidofer;
          varinddespauto                 := cvrecord(x).varinddespauto;
          varoidgrupooferta              := cvrecord(x).varoidgrupooferta;
          varcues_oid_ind_cuad_tipo_estr := cvrecord(x)
                                            .varcues_oid_ind_cuad_tipo_estr;
          varcod_fact_cuad               := cvrecord(x).varcod_fact_cuad;
          varoidsolicabe                 := cvrecord(x).varoidsolicabe;
          varsuma                        := cvrecord(x).varsuma;
        
          IF varoidofer <> varoidoferant THEN
            varcuadregrupo := 0;
          END IF;
          IF varcod_fact_cuad > varsuma THEN
            IF varcuadregrupo < varcod_fact_cuad THEN
              varcuadregrupo := 1; --varcod_fact_cuad;
            END IF;
          ELSE
            IF (varcuadregrupo * varcod_fact_cuad) < varsuma THEN
              varcuadregrupo := varsuma / varcod_fact_cuad;
              varresiduo     := MOD(varsuma, varcod_fact_cuad);
            END IF;
          END IF;
          IF (varcuadregrupo * varcod_fact_cuad) > varsuma THEN
          
            varunidins := (varcuadregrupo * varcod_fact_cuad) - varsuma;
          
            IF varunidins > 0 THEN
            
              SELECT oid_deta_ofer,
                     prod_oid_prod,
                     val_fact_repe,
                     imp_prec_cata,
                     imp_prec_posi,
                     fopa_oid_form_pago,
                     val_codi_vent,
                     precio_unitario,
                     num_pagi_cata,
                     ocat_oid_catal,
                     val_fact_repe,
                     fopa_oid_form_pago
                INTO varoiddetaofer,
                     varprodoidprod,
                     varvalfactrepe,
                     varimppreccata,
                     varimpprecposi,
                     varfopaoidformapago,
                     varvalcodivent,
                     varpreciounitario,
                     varpagicata,
                     varcata,
                     varfactrepe,
                     varfopa
                FROM (SELECT *
                        FROM pre_ofert_detal a
                       WHERE a.ofer_oid_ofer = varoidofer
                         AND a.gofe_oid_grup_ofer = varoidgrupooferta
                       ORDER BY a.num_posi_rank ASC)
               WHERE rownum = 1;
            
              SELECT nvl((SELECT SUM(1)
                           FROM ped_solic_posic a
                          WHERE a.soca_oid_soli_cabe = p_oidsoli
                            AND a.val_codi_vent = varvalcodivent),
                         0)
                INTO tmp
                FROM dual;
            
              IF tmp > 0 THEN
                UPDATE ped_solic_posic  a
                   SET a.num_unid_por_aten = a.num_unid_por_aten + varunidins
                 WHERE a.soca_oid_soli_cabe = p_oidsoli
                   AND a.val_codi_vent = varvalcodivent;
              ELSE
              
                    INSERT INTO ped_solic_posic
                      (oid_soli_posi,
                       cod_posi,
                       num_unid_dema,
                       num_unid_por_aten,
                       val_tasa_impu,
                       soca_oid_soli_cabe,
                       taim_oid_tasa_impu,
                       tpos_oid_tipo_posi,
                       prod_oid_prod,
                       fopa_oid_form_pago,
                       val_prec_cata_unit_loca,
                       val_prec_cont_unit_loca,
                       val_prec_cata_unit_docu,
                       val_prec_conta_unit_docu,
                       val_prec_fact_unit_loca,
                       val_prec_fact_unit_docu,
                       val_prec_sin_impu_unit_loca,
                       val_prec_sin_impu_unit_docu,
                       val_prec_sin_impu_tota_docu,
                       val_impo_desc_unit_loca,
                       val_impo_desc_unit_docu,
                       val_prec_neto_unit_loca,
                       val_prec_neto_tota_docu,
                       val_prec_neto_unit_docu,
                       val_prec_tota_tota_loca,
                       val_prec_tota_tota_docu,
                       val_impo_impu_unit_loca,
                       val_impo_impu_unit_docu,
                       val_impo_desc_tota_docu,
                       val_impo_impu_tota_loca,
                       val_impo_impu_tota_docu,
                       val_impo_desc_tota_loca,
                       val_prec_tota_unit_loca,
                       val_prec_tota_unit_docu,
                       val_prec_cont_tota_loca,
                       val_prec_cata_tota_loca,
                       val_prec_cata_tota_docu,
                       val_prec_cont_tota_docu,
                       val_porc_desc,
                       val_prec_cata_tota_loca_unid,
                       num_unid_dema_real,
                       num_unid_compr,
                       val_prec_fact_tota_loca,
                       val_prec_fact_tota_docu,
                       val_prec_sin_impu_tota_loca,
                       val_prec_neto_tota_loca,
                       ofde_oid_deta_ofer,
                       espo_oid_esta_posi,
                       stpo_oid_subt_posi,
                       val_codi_vent,
                       ind_no_impr,
                       oid_nive_ofer,
                       oid_nive_ofer_rang,
                       num_unid_orig,
                       val_codi_orig)
                    VALUES
                      (ped_sopo_seq.nextval,
                       (SELECT MAX(cod_posi)
                          FROM ped_solic_posic
                         WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
                       varunidins,
                       varunidins,
                       0,
                       p_oidsoli,
                       NULL,
                       4,
                       varprodoidprod,
                       varfopaoidformapago,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       varunidins,
                       varunidins,
                       0,
                       0,
                       0,
                       0,
                       varoiddetaofer,
                       4,
                       5,
                       varvalcodivent,
                       0,
                       NULL,
                       NULL,
                       NULL,
                       NULL);
              
              END IF;
            END IF;
          
          END IF;
          varoidoferant := varoidofer;
        
        END LOOP;
      END IF;
      EXIT WHEN c_ofertas_cv%NOTFOUND;
    END LOOP;
    CLOSE c_ofertas_cv;
  
    -- Se cuadra al multiplo
  
    OPEN c_ofertas_cv3;
    LOOP
      FETCH c_ofertas_cv3 BULK COLLECT
        INTO cv3record LIMIT w_filas;
      IF cv3record.count > 0 THEN
        FOR x IN cv3record.first .. cv3record.last
        LOOP
        
          varoidofer                     := cv3record(x).varoidofer;
          varinddespauto                 := cv3record(x).varinddespauto;
          varoidgrupooferta              := cv3record(x).varoidgrupooferta;
          varcues_oid_ind_cuad_tipo_estr := cv3record(x)
                                            .varcues_oid_ind_cuad_tipo_estr;
          varcod_fact_cuad               := cv3record(x).varcod_fact_cuad;
          varoidsolicabe                 := cv3record(x).varoidsolicabe;
          varsuma                        := cv3record(x).varsuma;
        
          IF varsuma > varcod_fact_cuad THEN
            IF MOD(varsuma, varcod_fact_cuad) = 0 THEN
              varunidins := 0;
            ELSE
              varunidins := varcod_fact_cuad -
                            MOD(varsuma, varcod_fact_cuad);
            END IF;
          ELSE
            varunidins := varcod_fact_cuad - varsuma;
          END IF;
        
          IF varunidins > 0 THEN
          
            SELECT oid_deta_ofer,
                   prod_oid_prod,
                   val_fact_repe,
                   imp_prec_cata,
                   imp_prec_posi,
                   fopa_oid_form_pago,
                   val_codi_vent,
                   precio_unitario,
                   num_pagi_cata,
                   ocat_oid_catal,
                   val_fact_repe,
                   fopa_oid_form_pago
              INTO varoiddetaofer,
                   varprodoidprod,
                   varvalfactrepe,
                   varimppreccata,
                   varimpprecposi,
                   varfopaoidformapago,
                   varvalcodivent,
                   varpreciounitario,
                   varpagicata,
                   varcata,
                   varfactrepe,
                   varfopa
              FROM (SELECT *
                      FROM pre_ofert_detal a
                     WHERE a.ofer_oid_ofer = varoidofer
                       AND a.gofe_oid_grup_ofer = varoidgrupooferta
                     ORDER BY a.num_posi_rank ASC)
             WHERE rownum = 1;
          
            SELECT nvl((SELECT SUM(1)
                         FROM ped_solic_posic a
                        WHERE a.soca_oid_soli_cabe = p_oidsoli
                          AND a.val_codi_vent = varvalcodivent),
                       0)
              INTO tmp
              FROM dual;
          
            IF tmp > 0 THEN
              UPDATE ped_solic_posic a
                 SET a.num_unid_por_aten = a.num_unid_por_aten + varunidins
               WHERE a.soca_oid_soli_cabe = p_oidsoli
                 AND a.val_codi_vent = varvalcodivent;
            ELSE
            
                    INSERT INTO ped_solic_posic
                      (oid_soli_posi,
                       cod_posi,
                       num_unid_dema,
                       num_unid_por_aten,
                       val_tasa_impu,
                       soca_oid_soli_cabe,
                       taim_oid_tasa_impu,
                       tpos_oid_tipo_posi,
                       prod_oid_prod,
                       fopa_oid_form_pago,
                       val_prec_cata_unit_loca,
                       val_prec_cont_unit_loca,
                       val_prec_cata_unit_docu,
                       val_prec_conta_unit_docu,
                       val_prec_fact_unit_loca,
                       val_prec_fact_unit_docu,
                       val_prec_sin_impu_unit_loca,
                       val_prec_sin_impu_unit_docu,
                       val_prec_sin_impu_tota_docu,
                       val_impo_desc_unit_loca,
                       val_impo_desc_unit_docu,
                       val_prec_neto_unit_loca,
                       val_prec_neto_tota_docu,
                       val_prec_neto_unit_docu,
                       val_prec_tota_tota_loca,
                       val_prec_tota_tota_docu,
                       val_impo_impu_unit_loca,
                       val_impo_impu_unit_docu,
                       val_impo_desc_tota_docu,
                       val_impo_impu_tota_loca,
                       val_impo_impu_tota_docu,
                       val_impo_desc_tota_loca,
                       val_prec_tota_unit_loca,
                       val_prec_tota_unit_docu,
                       val_prec_cont_tota_loca,
                       val_prec_cata_tota_loca,
                       val_prec_cata_tota_docu,
                       val_prec_cont_tota_docu,
                       val_porc_desc,
                       val_prec_cata_tota_loca_unid,
                       num_unid_dema_real,
                       num_unid_compr,
                       val_prec_fact_tota_loca,
                       val_prec_fact_tota_docu,
                       val_prec_sin_impu_tota_loca,
                       val_prec_neto_tota_loca,
                       ofde_oid_deta_ofer,
                       espo_oid_esta_posi,
                       stpo_oid_subt_posi,
                       val_codi_vent,
                       ind_no_impr,
                       oid_nive_ofer,
                       oid_nive_ofer_rang,
                       num_unid_orig,
                       val_codi_orig)
                    VALUES
                      (ped_sopo_seq.nextval,
                       (SELECT MAX(cod_posi)
                          FROM ped_solic_posic
                         WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
                       varunidins,
                       varunidins,
                       0,
                       p_oidsoli,
                       NULL,
                       4,
                       varprodoidprod,
                       varfopaoidformapago,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       varunidins,
                       varunidins,
                       0,
                       0,
                       0,
                       0,
                       varoiddetaofer,
                       4,
                       5,
                       varvalcodivent,
                       0,
                       NULL,
                       NULL,
                       NULL,
                       NULL);
            
            END IF;
          
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_ofertas_cv3%NOTFOUND;
    END LOOP;
    CLOSE c_ofertas_cv3;
  
    -- Se cuadra al Menor por Unidades
    varunidins := 0;
    OPEN c_ofertas_cv2;
    LOOP
      FETCH c_ofertas_cv2 BULK COLLECT
        INTO cv2record LIMIT w_filas;
      IF cv2record.count > 0 THEN
        FOR x IN cv2record.first .. cv2record.last
        LOOP
        
          varoidofer                     := cv2record(x).varoidofer;
          varinddespauto                 := cv2record(x).varinddespauto;
          varoidgrupooferta              := cv2record(x).varoidgrupooferta;
          varcues_oid_ind_cuad_tipo_estr := cv2record(x)
                                            .varcues_oid_ind_cuad_tipo_estr;
          varcod_fact_cuad               := cv2record(x).varcod_fact_cuad;
          varoidsolicabe                 := cv2record(x).varoidsolicabe;
          varsuma                        := cv2record(x).varsuma;
        
          IF varoidofer <> varoidoferant THEN
            varcuadregrupo := 0;
          END IF;
        
          IF varcuadregrupo = 0 THEN
            varcuadregrupo := varsuma / varcod_fact_cuad;
            varresiduo     := MOD(varsuma, varcod_fact_cuad);
          END IF;
        
          IF varsuma > varcuadregrupo THEN
            varunidins := varsuma - (varcuadregrupo * varcod_fact_cuad);
          END IF;
        
          IF varunidins > 0 THEN
          
            SELECT val_codi_vent
              INTO varvalcodivent
              FROM (SELECT *
                      FROM pre_ofert_detal a
                     WHERE a.ofer_oid_ofer = varoidofer
                       AND a.gofe_oid_grup_ofer = varoidgrupooferta
                       AND EXISTS
                     (SELECT *
                              FROM ped_solic_posic b
                             WHERE b.val_codi_vent = a.val_codi_vent
                               AND b.soca_oid_soli_cabe = p_oidsoli
                               )
                     ORDER BY a.num_posi_rank DESC)
             WHERE rownum = 1;
          
            UPDATE ped_solic_posic  a
               SET a.num_unid_por_aten = a.num_unid_por_aten - varunidins
             WHERE a.soca_oid_soli_cabe = p_oidsoli
               AND a.val_codi_vent = varvalcodivent;
          
          END IF;
        
          varoidoferant := varoidofer;
        
        END LOOP;
      END IF;
      EXIT WHEN c_ofertas_cv2%NOTFOUND;
    END LOOP;
    CLOSE c_ofertas_cv2;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_cuadr_cv_promo: ' || ls_sqlerrm);
  END ped_pr_cuadr_cv_promo;
  /***************************************************************************
  Descripcion       : Cuadre CF
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_cf
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER
  ) IS
  
  BEGIN





                    INSERT INTO ped_solic_posic
                      (oid_soli_posi,
                       cod_posi,
                       num_unid_dema,
                       num_unid_por_aten,
                       val_tasa_impu,
                       soca_oid_soli_cabe,
                       taim_oid_tasa_impu,
                       tpos_oid_tipo_posi,
                       prod_oid_prod,
                       fopa_oid_form_pago,
                       val_prec_cata_unit_loca,
                       val_prec_cont_unit_loca,
                       val_prec_cata_unit_docu,
                       val_prec_conta_unit_docu,
                       val_prec_fact_unit_loca,
                       val_prec_fact_unit_docu,
                       val_prec_sin_impu_unit_loca,
                       val_prec_sin_impu_unit_docu,
                       val_prec_sin_impu_tota_docu,
                       val_impo_desc_unit_loca,
                       val_impo_desc_unit_docu,
                       val_prec_neto_unit_loca,
                       val_prec_neto_tota_docu,
                       val_prec_neto_unit_docu,
                       val_prec_tota_tota_loca,
                       val_prec_tota_tota_docu,
                       val_impo_impu_unit_loca,
                       val_impo_impu_unit_docu,
                       val_impo_desc_tota_docu,
                       val_impo_impu_tota_loca,
                       val_impo_impu_tota_docu,
                       val_impo_desc_tota_loca,
                       val_prec_tota_unit_loca,
                       val_prec_tota_unit_docu,
                       val_prec_cont_tota_loca,
                       val_prec_cata_tota_loca,
                       val_prec_cata_tota_docu,
                       val_prec_cont_tota_docu,
                       val_porc_desc,
                       val_prec_cata_tota_loca_unid,
                       num_unid_dema_real,
                       num_unid_compr,
                       val_prec_fact_tota_loca,
                       val_prec_fact_tota_docu,
                       val_prec_sin_impu_tota_loca,
                       val_prec_neto_tota_loca,
                       ofde_oid_deta_ofer,
                       espo_oid_esta_posi,
                       stpo_oid_subt_posi,
                       val_codi_vent,
                       ind_no_impr,
                       oid_nive_ofer,
                       oid_nive_ofer_rang,
                       num_unid_orig,
                       val_codi_orig)
                      SELECT ped_sopo_seq.nextval,
                             (SELECT MAX(cod_posi)
                             FROM ped_solic_posic
                             WHERE soca_oid_soli_cabe = p_oidsoli) + rownum,
                             c.num_unid_dema,
                             c.num_unid_por_aten,
                             c.val_tasa_impu,
                             p_oidsoli,
                             c.taim_oid_tasa_impu,
                             '4',
                             d.prod_oid_prod, 
                             d.fopa_oid_form_pago,
                             d.precio_unitario,
                             decode(d.imp_prec_cata, 0, d.imp_prec_posi, 0),
                             d.precio_unitario,
                             decode(d.imp_prec_cata, 0, d.imp_prec_posi, 0),
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             c.num_unid_dema,
                             c.num_unid_por_aten,
                             0,
                             0,
                             0,
                             0,
                             d.oid_deta_ofer,
                             4,
                             '5',
                             d.val_codi_vent,
                             0,
                             NULL,
                             NULL,
                             NULL,
                             NULL
                          FROM ped_solic_cabec a,
                               pre_ofert_detal b,
                               ped_solic_posic c,
                               pre_ofert_detal d,
                               pre_ofert e
                         WHERE a.oid_soli_cabe = c.soca_oid_soli_cabe
                           and c.ofde_oid_deta_ofer=b.oid_deta_ofer
                           and b.ofer_oid_ofer = e.oid_ofer
                           AND b.ofer_oid_ofer = d.ofer_oid_ofer
                           AND d.ind_prod_prin = 0
                           AND a.oid_soli_cabe = p_oidsoli
                           AND e.coes_oid_estr = 2002;

  
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_cuadr_cf: ' || ls_sqlerrm);
  END ped_pr_cuadr_cf;
  /***************************************************************************
  Descripcion       : Cuadre CF Promociones
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_cf_promo
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER
  ) IS
  
  BEGIN

                    INSERT INTO ped_solic_posic
                      (oid_soli_posi,
                       cod_posi,
                       num_unid_dema,
                       num_unid_por_aten,
                       val_tasa_impu,
                       soca_oid_soli_cabe,
                       taim_oid_tasa_impu,
                       tpos_oid_tipo_posi,
                       prod_oid_prod,
                       fopa_oid_form_pago,
                       val_prec_cata_unit_loca,
                       val_prec_cont_unit_loca,
                       val_prec_cata_unit_docu,
                       val_prec_conta_unit_docu,
                       val_prec_fact_unit_loca,
                       val_prec_fact_unit_docu,
                       val_prec_sin_impu_unit_loca,
                       val_prec_sin_impu_unit_docu,
                       val_prec_sin_impu_tota_docu,
                       val_impo_desc_unit_loca,
                       val_impo_desc_unit_docu,
                       val_prec_neto_unit_loca,
                       val_prec_neto_tota_docu,
                       val_prec_neto_unit_docu,
                       val_prec_tota_tota_loca,
                       val_prec_tota_tota_docu,
                       val_impo_impu_unit_loca,
                       val_impo_impu_unit_docu,
                       val_impo_desc_tota_docu,
                       val_impo_impu_tota_loca,
                       val_impo_impu_tota_docu,
                       val_impo_desc_tota_loca,
                       val_prec_tota_unit_loca,
                       val_prec_tota_unit_docu,
                       val_prec_cont_tota_loca,
                       val_prec_cata_tota_loca,
                       val_prec_cata_tota_docu,
                       val_prec_cont_tota_docu,
                       val_porc_desc,
                       val_prec_cata_tota_loca_unid,
                       num_unid_dema_real,
                       num_unid_compr,
                       val_prec_fact_tota_loca,
                       val_prec_fact_tota_docu,
                       val_prec_sin_impu_tota_loca,
                       val_prec_neto_tota_loca,
                       ofde_oid_deta_ofer,
                       espo_oid_esta_posi,
                       stpo_oid_subt_posi,
                       val_codi_vent,
                       ind_no_impr,
                       oid_nive_ofer,
                       oid_nive_ofer_rang,
                       num_unid_orig,
                       val_codi_orig)
                      SELECT ped_sopo_seq.nextval,
                             (SELECT MAX(cod_posi)
                             FROM ped_solic_posic
                             WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
                             c.num_unid_dema,
                             c.num_unid_por_aten,
                             c.val_tasa_impu,
                             p_oidsoli,
                             c.taim_oid_tasa_impu,
                             '4',
                             d.prod_oid_prod, 
                             d.fopa_oid_form_pago,
                             d.precio_unitario,
                             decode(d.imp_prec_cata, 0, d.imp_prec_posi, 0),
                             d.precio_unitario,
                             decode(d.imp_prec_cata, 0, d.imp_prec_posi, 0),
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             c.num_unid_dema,
                             c.num_unid_por_aten,
                             0,
                             0,
                             0,
                             0,
                             d.oid_deta_ofer,
                             4,
                             '5',
                             d.val_codi_vent,
                             0,
                             NULL,
                             NULL,
                             NULL,
                             NULL
                          FROM ped_solic_cabec a,
                               pre_ofert_detal b,
                               ped_solic_posic c,
                               pre_ofert_detal d,
                               pre_ofert e
                         WHERE a.oid_soli_cabe = c.soca_oid_soli_cabe
                           and c.ofde_oid_deta_ofer=b.oid_deta_ofer
                           and b.ofer_oid_ofer = e.oid_ofer
                           AND b.ofer_oid_ofer = d.ofer_oid_ofer
                           AND b.gofe_oid_grup_ofer = d.gofe_oid_grup_ofer
                           AND d.ind_prod_prin = 0
                           AND a.oid_soli_cabe = p_oidsoli
                           AND e.coes_oid_estr = 2006
                           AND d.val_codi_vent NOT IN
                           (SELECT val_codi_vent
                              FROM ped_solic_posic
                             WHERE soca_oid_soli_cabe = p_oidsoli)
                           ;

  

  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_cuadr_cf_promo: ' || ls_sqlerrm);
  END ped_pr_cuadr_cf_promo;
  /***************************************************************************
  Descripcion       : Cuadre Promociones
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_promo
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER
  ) IS
  
    varunidins    NUMBER(12);
    varoidoferant NUMBER(12);
    tmpcuv        VARCHAR2(10);
    tmpunid       NUMBER(12);
    tmpunid2      NUMBER(12);
  
    CURSOR c_ofertas_prom IS
      SELECT b.val_codi_vent,
             round(ped_fn_cuadr_promo(p_oidsoli,
                                      e.icpr_oid_indi_cuad_prom,
                                      e.oid_prom) / e.val_fact_cuad,
                   0) unid_real,
             b.num_unid_dema_real
        FROM ped_solic_cabec a,
             ped_solic_posic b,
             pre_ofert_detal c,
             pre_ofert       d,
             pre_promo       e
      --SET a.num_unid_cuad =
       WHERE a.oid_soli_cabe = p_oidsoli
         AND a.oid_soli_cabe = b.soca_oid_soli_cabe
         and b.ofde_oid_deta_ofer = c.oid_deta_ofer
         and c.ofer_oid_ofer = d.oid_ofer
         AND c.ofer_oid_ofer = e.ofer_oid_ofer
         AND d.coes_oid_estr IN (2005, 2006);
  
    r_ofertas_prom c_ofertas_prom%ROWTYPE;
  
    CURSOR c_ofertas_prom_cv IS
      SELECT ofer_oid_ofer,
             gofe_oid_grup_ofer,
             trunc(ped_fn_cuadr_promo(p_oidsoli,
                                      icpr_oid_indi_cuad_prom,
                                      oid_prom) / val_fact_cuad) unid_real,
             dema
        FROM (SELECT c.ofer_oid_ofer,
                     c.gofe_oid_grup_ofer,
                     e.oid_prom,
                     e.icpr_oid_indi_cuad_prom,
                     e.val_fact_cuad,
                     SUM(b.num_unid_por_aten) dema
                FROM ped_solic_cabec a,
                     ped_solic_posic b,
                     pre_ofert_detal c,
                     pre_ofert       d,
                     pre_promo       e
              --SET a.num_unid_cuad =
               WHERE a.oid_soli_cabe = p_oidsoli
                 AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                 and b.ofde_oid_deta_ofer = c.oid_deta_ofer
                 and c.ofer_oid_ofer = d.oid_ofer
                 AND c.ofer_oid_ofer = e.ofer_oid_ofer
                 AND d.coes_oid_estr IN (2007)
               GROUP BY c.ofer_oid_ofer,
                        c.gofe_oid_grup_ofer,
                        e.oid_prom,
                        e.icpr_oid_indi_cuad_prom,
                        e.val_fact_cuad
              --, round(ped_fn_cuadr_promo(p_clie_oid_clie, p_perd_oid_peri, b.icpr_oid_indi_cuad_prom, b.oid_prom)/b.val_fact_cuad,0)
              )
       ORDER BY 1,
                2,
                4 DESC;
  
    r_ofertas_prom_cv c_ofertas_prom_cv%ROWTYPE;
  
    CURSOR c_ofertas_prom_auto IS
      SELECT d.val_codi_vent,
             trunc(ped_fn_cuadr_promo(p_oidsoli,
                                      h.icpr_oid_indi_cuad_prom,
                                      h.oid_prom) / h.val_fact_cuad
                   ) unid,
             d.oid_deta_ofer,
             d.ofer_oid_ofer,
             c.coes_oid_estr,
             d.ocat_oid_catal,
             d.num_pagi_cata,
             d.val_fact_repe,
             d.precio_unitario,
             d.imp_prec_cata,
             d.gofe_oid_grup_ofer,
             d.fopa_oid_form_pago,
             d.prod_oid_prod
        FROM pre_ofert             c,
             pre_ofert_detal       d,
             pre_matri_factu_cabec e,
             cra_perio             f,
             pre_promo             h
       WHERE f.oid_peri = p_perd_oid_peri
         AND d.ofer_oid_ofer = c.oid_ofer
         AND c.mfca_oid_cabe = e.oid_cabe
         AND e.perd_oid_peri = f.oid_peri
         AND c.coes_oid_estr IN (2005, 2006)
         AND c.oid_ofer = h.ofer_oid_ofer
         AND c.ind_desp_auto = 1
            --and round(ped_fn_cuadr_promo(p_clie_oid_clie, p_perd_oid_peri, h.icpr_oid_indi_cuad_prom, h.oid_prom)/h.val_fact_cuad,0)>0
         AND d.val_codi_vent NOT IN
             (SELECT a.val_codi_vent
                FROM ped_solic_posic a
               WHERE a.soca_oid_soli_cabe = p_oidsoli);
  
    r_ofertas_prom_auto c_ofertas_prom_auto%ROWTYPE;
  
  BEGIN
  
    OPEN c_ofertas_prom;
    LOOP
    
      FETCH c_ofertas_prom
        INTO r_ofertas_prom;
      EXIT WHEN c_ofertas_prom%NOTFOUND;
    
      UPDATE ped_solic_posic a
         SET a.num_unid_por_aten = r_ofertas_prom.unid_real,
             a.val_obse = 'PROMOCION NO CUMPLE'
       WHERE a.val_codi_vent = r_ofertas_prom.val_codi_vent
         AND a.num_unid_por_aten > r_ofertas_prom.unid_real
         and a.soca_oid_soli_cabe=p_oidsoli;
    
    END LOOP;
    CLOSE c_ofertas_prom;
  
    OPEN c_ofertas_prom_cv;
    LOOP
    
      FETCH c_ofertas_prom_cv
        INTO r_ofertas_prom_cv;
      EXIT WHEN c_ofertas_prom_cv%NOTFOUND;
    
      IF r_ofertas_prom_cv.ofer_oid_ofer <> varoidoferant THEN
        varunidins := 0;
      END IF;
    
      IF r_ofertas_prom_cv.unid_real = 0 THEN
        UPDATE ped_solic_posic a
           SET a.num_unid_por_aten = 0,
               a.val_obse='PROMOCION NO CUMPLE'
         WHERE ofde_oid_deta_ofer in (select oid_deta_ofer from pre_ofert_detal where gofe_oid_grup_ofer = r_ofertas_prom_cv.gofe_oid_grup_ofer and ofer_oid_ofer=r_ofertas_prom_cv.ofer_oid_ofer)
           AND soca_oid_soli_cabe = p_oidsoli;
      ELSE
        IF r_ofertas_prom_cv.unid_real < r_ofertas_prom_cv.dema THEN
          varunidins := r_ofertas_prom_cv.dema -
                        r_ofertas_prom_cv.unid_real;
          WHILE varunidins > 0
          LOOP
            SELECT val_codi_vent,
                   num_unid_por_aten
              INTO tmpcuv,
                   tmpunid
              FROM (SELECT b.val_codi_vent,
                           b.num_unid_por_aten
                      FROM ped_solic_cabec a,
                           ped_solic_posic b,
                           pre_ofert_detal c
                     WHERE a.oid_soli_cabe = p_oidsoli
                       and a.oid_soli_cabe = b.soca_oid_soli_cabe
                       and b.ofde_oid_deta_ofer = c.oid_deta_ofer
                       AND c.gofe_oid_grup_ofer =
                           r_ofertas_prom_cv.gofe_oid_grup_ofer
                     ORDER BY 2 DESC)
             WHERE rownum = 1;
          
            IF varunidins > tmpunid THEN
            
              varunidins := varunidins - tmpunid;
            
              UPDATE ped_solic_posic a
                 SET num_unid_por_aten = 0,
               a.val_obse='PROMOCION NO CUMPLE'
               WHERE a.soca_oid_soli_cabe = p_oidsoli
                 AND a.val_codi_vent = tmpcuv;
            
            ELSE
            
              UPDATE ped_solic_posic a
                 SET num_unid_por_aten = num_unid_por_aten - varunidins
               WHERE a.soca_oid_soli_cabe = p_oidsoli
                 AND a.val_codi_vent = tmpcuv;
            
              varunidins := 0;
            
            END IF;
          
          END LOOP;
        END IF;
      END IF;
    
    END LOOP;
    CLOSE c_ofertas_prom_cv;
  
    ped_pr_cuadr_cf_promo(p_oidsoli,
                          p_clie_oid_clie,
                          p_perd_oid_peri);
    ped_pr_cuadr_cv_promo(p_oidsoli,
                          p_clie_oid_clie,
                          p_perd_oid_peri);
  
    OPEN c_ofertas_prom_auto;
    LOOP
    
      FETCH c_ofertas_prom_auto
        INTO r_ofertas_prom_auto;
      EXIT WHEN c_ofertas_prom_auto%NOTFOUND;
    
      IF r_ofertas_prom_auto.unid > 0 THEN

                    INSERT INTO ped_solic_posic
                      (oid_soli_posi,
                       cod_posi,
                       num_unid_dema,
                       num_unid_por_aten,
                       val_tasa_impu,
                       soca_oid_soli_cabe,
                       taim_oid_tasa_impu,
                       tpos_oid_tipo_posi,
                       prod_oid_prod,
                       fopa_oid_form_pago,
                       val_prec_cata_unit_loca,
                       val_prec_cont_unit_loca,
                       val_prec_cata_unit_docu,
                       val_prec_conta_unit_docu,
                       val_prec_fact_unit_loca,
                       val_prec_fact_unit_docu,
                       val_prec_sin_impu_unit_loca,
                       val_prec_sin_impu_unit_docu,
                       val_prec_sin_impu_tota_docu,
                       val_impo_desc_unit_loca,
                       val_impo_desc_unit_docu,
                       val_prec_neto_unit_loca,
                       val_prec_neto_tota_docu,
                       val_prec_neto_unit_docu,
                       val_prec_tota_tota_loca,
                       val_prec_tota_tota_docu,
                       val_impo_impu_unit_loca,
                       val_impo_impu_unit_docu,
                       val_impo_desc_tota_docu,
                       val_impo_impu_tota_loca,
                       val_impo_impu_tota_docu,
                       val_impo_desc_tota_loca,
                       val_prec_tota_unit_loca,
                       val_prec_tota_unit_docu,
                       val_prec_cont_tota_loca,
                       val_prec_cata_tota_loca,
                       val_prec_cata_tota_docu,
                       val_prec_cont_tota_docu,
                       val_porc_desc,
                       val_prec_cata_tota_loca_unid,
                       num_unid_dema_real,
                       num_unid_compr,
                       val_prec_fact_tota_loca,
                       val_prec_fact_tota_docu,
                       val_prec_sin_impu_tota_loca,
                       val_prec_neto_tota_loca,
                       ofde_oid_deta_ofer,
                       espo_oid_esta_posi,
                       stpo_oid_subt_posi,
                       val_codi_vent,
                       ind_no_impr,
                       oid_nive_ofer,
                       oid_nive_ofer_rang,
                       num_unid_orig,
                       val_codi_orig,
                       VAL_OBSE)
                    VALUES
                      (ped_sopo_seq.nextval,
                       (SELECT MAX(cod_posi)
                          FROM ped_solic_posic
                         WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
                       r_ofertas_prom_auto.unid,
                       r_ofertas_prom_auto.unid,
                       0,
                       p_oidsoli,
                       NULL,
                       4,
                       r_ofertas_prom_auto.prod_oid_prod,
                       r_ofertas_prom_auto.fopa_oid_form_pago,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       r_ofertas_prom_auto.unid,
                       r_ofertas_prom_auto.unid,
                       0,
                       0,
                       0,
                       0,
                       r_ofertas_prom_auto.oid_deta_ofer,
                       4,
                       5,
                       r_ofertas_prom_auto.val_codi_vent,
                       0,
                       NULL,
                       NULL,
                       NULL,
                       NULL,
                       'PROMOCION AUTOMATICA');

      

      
      END IF;
    
    END LOOP;
    CLOSE c_ofertas_prom_auto;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_cuadr_promo: ' || ls_sqlerrm);
  END ped_pr_cuadr_promo;
  /***************************************************************************
  Descripcion       : Cuadre Promocion
  Fecha Creacion    : 11/10/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_cuadr_promo
  (
    p_oidsoli   NUMBER,
    p_indcuadre NUMBER,
    p_oidprom   NUMBER
  ) RETURN NUMBER IS
  
    v_cuadre1 NUMBER := 0;
    v_cuadre2 NUMBER := 0;
  
  BEGIN
  
    SELECT nvl(SUM(b.num_unid_por_aten), 0),
           nvl(SUM(b.num_unid_por_aten * b.val_prec_cata_unit_loca), 0)
      INTO v_cuadre1,
           v_cuadre2
      FROM ped_solic_cabec  a,
           ped_solic_posic b,
           pre_ofert_detal c,
           pre_ofert d
     WHERE a.oid_soli_cabe = p_oidsoli
       and a.oid_soli_cabe = b.soca_oid_soli_cabe
       and b.ofde_oid_deta_ofer = c.oid_deta_ofer
       and c.ofer_oid_ofer = d.oid_ofer
       AND d.coes_oid_estr NOT IN (2005, 2006, 2007)
       AND b.val_codi_vent IN
           (SELECT l.val_codi_vent
              FROM pre_ofert       h,
                   pre_promo       i,
                   pre_rango_promo j,
                   pre_ofert_detal l,
                   pre_ofert       ll
             WHERE h.oid_ofer = i.ofer_oid_ofer
               AND i.oid_prom = j.pomo_oid_prom
               AND j.ocat_oid_cata = ll.ocat_oid_cata
               AND l.num_pagi_cata >= j.val_desd
               AND l.num_pagi_cata <= j.val_hast
               AND j.cod_tipo_rang = 'R'
               AND j.ind_excl = 0
               AND ll.oid_ofer = l.ofer_oid_ofer
               AND h.mfca_oid_cabe = ll.mfca_oid_cabe
               AND h.oid_ofer = i.ofer_oid_ofer
               AND i.oid_prom = p_oidprom
               AND l.prod_oid_prod NOT IN
                   (SELECT p.oid_prod
                      FROM pre_ofert       m,
                           pre_promo       n,
                           pre_rango_promo o,
                           mae_produ       p,
                           pre_ofert_detal q
                     WHERE m.oid_ofer = n.ofer_oid_ofer
                       AND n.oid_prom = o.pomo_oid_prom
                       AND o.ocat_oid_cata = q.ocat_oid_catal
                       AND q.num_pagi_cata >= o.val_desd
                       AND q.num_pagi_cata <= o.val_hast
                       AND o.cod_tipo_rang = 'R'
                       AND q.prod_oid_prod = p.oid_prod
                       AND o.ind_excl = 1
                       AND o.pomo_oid_prom = j.pomo_oid_prom
                    UNION ALL
                    SELECT xx.oid_prod
                      FROM pre_rango_promo x,
                           mae_produ       xx
                     WHERE x.cod_tipo_rang = 'P'
                       AND x.ind_excl = 1
                       AND x.pomo_oid_prom = i.oid_prom
                       AND x.val_desd = xx.oid_prod));
  
    IF p_indcuadre = 1 THEN
      RETURN v_cuadre1;
    ELSE
      RETURN v_cuadre2;
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN v_cuadre1;
  END ped_fn_cuadr_promo;

  /***************************************************************************
  Descripcion       : Cuadre Cond.
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_condi
  (
    p_oidsoli       IN NUMBER,
    p_clie_oid_clie IN NUMBER,
    p_perd_oid_peri IN NUMBER
  ) IS
  
    CURSOR c_ofertas_cond IS
    
      SELECT ofer_oid_ofer,
             val_cond_g1_cndt,
             val_cond_g2_cndo,
             ind_desp_auto,
             CASE
               WHEN val_cond_g1_cndt = 'Y' THEN
                decode(MIN(decode(ind_cndt, 1, unid, -1)), -1, 0)
               ELSE
                SUM(decode(ind_cndt, 0, 0, unid))
             END cndt,
             SUM(decode(ind_cndo, 0, 0, unid)) cndo
        FROM (SELECT DISTINCT h.ofer_oid_ofer,
                              e.ind_desp_auto,
                              e.val_cond_g1_cndt,
                              e.val_cond_g2_cndo,
                              h.oid_grup_ofer,
                              h.ind_cndt,
                              h.ind_cndo,
                              round(ped_fn_cuadr_condi(p_oidsoli,
                                                       h.cues_oid_ind_cuad_tipo_estr,
                                                       c.ofer_oid_ofer,
                                                       h.oid_grup_ofer) /
                                    h.cod_fact_cuad) unid
                FROM ped_solic_cabec a,
                     ped_solic_posic b,
                     pre_ofert_detal c,
                     pre_ofert       d,
                     pre_ofert       e,
                     pre_grupo_ofert h
               WHERE a.oid_soli_cabe = p_oidsoli
                 and a.oid_soli_cabe = b.soca_oid_soli_cabe
                 and b.ofde_oid_deta_ofer = c.oid_deta_ofer
                 and c.ofer_oid_ofer = d.oid_ofer
                 AND d.coes_oid_estr IN (2004)
                 AND c.ofer_oid_ofer = e.oid_ofer
                 AND e.oid_ofer = h.ofer_oid_ofer)
       GROUP BY ofer_oid_ofer,
                val_cond_g1_cndt,
                val_cond_g2_cndo,
                ind_desp_auto;
  
    r_ofertas_cond c_ofertas_cond%ROWTYPE;
  
    var_tmp  NUMBER;
    var_cuv  VARCHAR2(15);
    var_unid NUMBER;
  BEGIN
    var_tmp := 0;
    OPEN c_ofertas_cond;
    LOOP
    
      FETCH c_ofertas_cond
        INTO r_ofertas_cond;
      EXIT WHEN c_ofertas_cond%NOTFOUND;
    
      IF r_ofertas_cond.cndt <= r_ofertas_cond.cndo THEN
        var_tmp := r_ofertas_cond.cndt - r_ofertas_cond.cndo;
        WHILE var_tmp > 0
        LOOP
          SELECT val_codi_vent,
                 num_unid_por_aten
            INTO var_cuv,
                 var_unid
            FROM (SELECT b.val_codi_vent,
                         b.num_unid_por_aten 
                    FROM ped_solic_cabec a,
                         ped_solic_posic b,
                         pre_ofert_detal c,
                         pre_ofert       d,
                         pre_grupo_ofert y
                   WHERE a.oid_soli_cabe = p_oidsoli
                     and a.oid_soli_cabe = b.soca_oid_soli_cabe
                     and b.ofde_oid_deta_ofer =c.oid_deta_ofer
                     and c.ofer_oid_ofer = d.oid_ofer     
                     AND c.ofer_oid_ofer = y.ofer_oid_ofer
                     AND c.ofer_oid_ofer = r_ofertas_cond.ofer_oid_ofer
                     AND y.ind_cndo = 1
                     AND b.num_unid_por_aten > 0
                   ORDER BY b.num_unid_por_aten DESC)
           WHERE rownum = 1;
        
          IF var_tmp >= var_unid THEN
            UPDATE ped_solic_posic x
               SET x.num_unid_por_aten = 0
             WHERE x.soca_oid_soli_cabe = p_oidsoli
               AND x.val_codi_vent = var_cuv;
          
            var_tmp := var_tmp - var_unid;
          
          ELSE
            UPDATE ped_solic_posic x
               SET x.num_unid_por_aten = var_tmp - var_unid
             WHERE x.soca_oid_soli_cabe = p_oidsoli
               AND x.val_codi_vent = var_cuv;
          
            var_tmp := 0;
          
          END IF;
        END LOOP;
      ELSE
        IF r_ofertas_cond.ind_desp_auto = 1 THEN
          IF r_ofertas_cond.cndo = 0 THEN


                    INSERT INTO ped_solic_posic
                      (oid_soli_posi,
                       cod_posi,
                       num_unid_dema,
                       num_unid_por_aten,
                       val_tasa_impu,
                       soca_oid_soli_cabe,
                       taim_oid_tasa_impu,
                       tpos_oid_tipo_posi,
                       prod_oid_prod,
                       fopa_oid_form_pago,
                       val_prec_cata_unit_loca,
                       val_prec_cont_unit_loca,
                       val_prec_cata_unit_docu,
                       val_prec_conta_unit_docu,
                       val_prec_fact_unit_loca,
                       val_prec_fact_unit_docu,
                       val_prec_sin_impu_unit_loca,
                       val_prec_sin_impu_unit_docu,
                       val_prec_sin_impu_tota_docu,
                       val_impo_desc_unit_loca,
                       val_impo_desc_unit_docu,
                       val_prec_neto_unit_loca,
                       val_prec_neto_tota_docu,
                       val_prec_neto_unit_docu,
                       val_prec_tota_tota_loca,
                       val_prec_tota_tota_docu,
                       val_impo_impu_unit_loca,
                       val_impo_impu_unit_docu,
                       val_impo_desc_tota_docu,
                       val_impo_impu_tota_loca,
                       val_impo_impu_tota_docu,
                       val_impo_desc_tota_loca,
                       val_prec_tota_unit_loca,
                       val_prec_tota_unit_docu,
                       val_prec_cont_tota_loca,
                       val_prec_cata_tota_loca,
                       val_prec_cata_tota_docu,
                       val_prec_cont_tota_docu,
                       val_porc_desc,
                       val_prec_cata_tota_loca_unid,
                       num_unid_dema_real,
                       num_unid_compr,
                       val_prec_fact_tota_loca,
                       val_prec_fact_tota_docu,
                       val_prec_sin_impu_tota_loca,
                       val_prec_neto_tota_loca,
                       ofde_oid_deta_ofer,
                       espo_oid_esta_posi,
                       stpo_oid_subt_posi,
                       val_codi_vent,
                       ind_no_impr,
                       oid_nive_ofer,
                       oid_nive_ofer_rang,
                       num_unid_orig,
                       val_codi_orig)
                      SELECT ped_sopo_seq.nextval,
                             (SELECT MAX(cod_posi)
                             FROM ped_solic_posic
                             WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
                             r_ofertas_cond.cndt,
                             r_ofertas_cond.cndt,
                             0,
                             p_oidsoli,
                             0,
                             '4',
                             b.prod_oid_prod, 
                             b.fopa_oid_form_pago,
                             b.precio_unitario,
                             decode(b.imp_prec_cata, 0, b.imp_prec_posi, 0),
                             b.precio_unitario,
                             decode(b.imp_prec_cata, 0, b.imp_prec_posi, 0),
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             r_ofertas_cond.cndt,
                             r_ofertas_cond.cndt,
                             0,
                             0,
                             0,
                             0,
                             b.oid_deta_ofer,
                             4,
                             '5',
                             b.val_codi_vent,
                             0,
                             NULL,
                             NULL,
                             NULL,
                             NULL
                FROM pre_ofert_detal b,
                     pre_grupo_ofert c
               WHERE b.ofer_oid_ofer = r_ofertas_cond.ofer_oid_ofer
                 AND b.ofer_oid_ofer = c.ofer_oid_ofer
                 AND c.ind_cndo = 1
                 AND rownum = 1;



          ELSE
            SELECT val_codi_vent,
                   num_unid_por_aten
              INTO var_cuv,
                   var_unid
              FROM (SELECT b.val_codi_vent,
                           b.num_unid_por_aten
                      FROM ped_solic_cabec a,
                           ped_solic_posic b,
                           pre_ofert_detal c,
                           pre_ofert       d,
                           pre_grupo_ofert y
                     WHERE a.oid_soli_cabe = p_oidsoli
                       and a.oid_soli_cabe = b.soca_oid_soli_cabe
                       and b.ofde_oid_deta_ofer = c.oid_deta_ofer
                       and c.ofer_oid_ofer = d.oid_ofer
                       AND c.ofer_oid_ofer = y.ofer_oid_ofer
                       AND c.ofer_oid_ofer = r_ofertas_cond.ofer_oid_ofer
                       AND y.ind_cndo = 1
                       AND b.num_unid_por_aten > 0
                     ORDER BY b.num_unid_por_aten DESC)
             WHERE rownum = 1;
          
            UPDATE ped_solic_posic x
               SET x.num_unid_por_aten = x.num_unid_por_aten + r_ofertas_cond.cndt -
                                     r_ofertas_cond.cndo
             WHERE x.soca_oid_soli_cabe = p_oidsoli
               AND x.val_codi_vent = var_cuv;
          END IF;
        END IF;
      END IF;
    
    END LOOP;
    CLOSE c_ofertas_cond;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_cuadr_condi: ' || ls_sqlerrm);
  END ped_pr_cuadr_condi;

  /***************************************************************************
  Descripcion       : Cuadre Promocion
  Fecha Creacion    : 11/10/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_cuadr_condi
  (
    p_oidsoli      NUMBER,
    p_indcuadre    NUMBER,
    p_oidofer      NUMBER,
    p_oidgrupoofer NUMBER
  ) RETURN NUMBER IS
  
    v_cuadre1 NUMBER := 0;
    v_cuadre2 NUMBER := 0;
  
  BEGIN
  
    SELECT nvl(SUM(a.num_unid_por_aten), 0),
           nvl(SUM(a.num_unid_por_aten * a.val_prec_cata_unit_loca), 0)
      INTO v_cuadre1,
           v_cuadre2
      FROM ped_solic_posic a
     WHERE a.soca_oid_soli_cabe = p_oidsoli
       AND a.val_codi_vent IN
           (SELECT l.val_codi_vent
              FROM pre_ofert_detal l
             WHERE l.ofer_oid_ofer = p_oidofer
               AND l.gofe_oid_grup_ofer = p_oidgrupoofer);
  
    IF p_indcuadre = 5 THEN
      RETURN v_cuadre1;
    ELSE
      RETURN v_cuadre2;
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN v_cuadre1;
  END ped_fn_cuadr_condi;
  /***************************************************************************
  Descripcion       : Secuenciacion
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_secue IS
    w_filas NUMBER := 100000;
  
    varnsec            NUMBER(12);
    varvalnumesoli     NUMBER(12);
    varnumsecufactdiar NUMBER(12);
    varsocaoidsolicabe NUMBER(1);
    varvalsecurutaterr NUMBER(12);
    varoidcabe         NUMBER(12);
    vartidooidtipodocu NUMBER(12);
    varoiddocuimpr     NUMBER(12);
    varvalbuff         BLOB;
  
    varmininte NUMBER(12);
  
    len    NUMBER(12);
    bl1    BLOB;
    bl2    BLOB;
    ini    NUMBER(2);
    posi   NUMBER(2);
    posfin NUMBER(2);
  
    CURSOR c_sec IS
      SELECT val_nume_soli,
             num_secu_fact_diar,
             soca_oid_soli_cabe,
             val_secu_ruta_terr,
             row_number() over(PARTITION BY substr(val_secu_ruta_terr, 1, 6) ORDER BY substr(val_secu_ruta_terr, 1, 6)) nsec
        FROM (SELECT a.val_nume_soli,
                     b.num_secu_fact_diar,
                     b.soca_oid_soli_cabe,
                     b.val_secu_ruta_terr
                FROM ped_solic_cabec_secue b,
                     ped_solic_cabec       a
               WHERE b.soca_oid_soli_cabe = a.oid_soli_cabe
                 AND a.perd_oid_peri =
                     (SELECT x.oid_peri
                        FROM cra_perio       x,
                             seg_perio_corpo y,
                             bas_ctrl_fact   z
                       WHERE x.peri_oid_peri = y.oid_peri
                         AND y.cod_peri = z.cod_peri
                         AND z.ind_camp_act = 1
                         AND z.sta_camp = 0)
                    --      and a.FEC_FACT='14/01/2009'
                 AND nvl(a.ind_inte_lari_gene, 0) = 0
                 AND a.tspa_oid_tipo_soli_pais IN
                     (SELECT tspa_oid_tipo_soli_pais
                        FROM int_lar_tipo_solici_pedido_dis)
                 AND a.num_unid_aten_tota > 0
               ORDER BY val_secu_ruta_terr ASC,
                        a.clie_oid_clie);
    TYPE secrec IS RECORD(
      varnsec            NUMBER(12),
      varvalnumesoli     NUMBER(12),
      varnumsecufactdiar NUMBER(12),
      varsocaoidsolicabe NUMBER(1),
      varvalsecurutaterr NUMBER(12));
    TYPE secrectab IS TABLE OF secrec;
    secrecord secrectab;
  
    CURSOR c_df IS
      SELECT DISTINCT b.tido_oid_tipo_docu
        FROM fac_docum_conta_cabec b,
             ped_solic_cabec       a,
             ped_solic_cabec_secue c
       WHERE b.soca_oid_soli_cabe(+) = a.oid_soli_cabe
         AND a.oid_soli_cabe = c.soca_oid_soli_cabe
         AND a.perd_oid_peri = (SELECT x.oid_peri
                                  FROM cra_perio       x,
                                       seg_perio_corpo y,
                                       bas_ctrl_fact   z
                                 WHERE x.peri_oid_peri = y.oid_peri
                                   AND y.cod_peri = z.cod_peri
                                   AND z.ind_camp_act = 1
                                   AND z.sta_camp = 0)
            --      and a.FEC_FACT='13/01/2009'
         AND nvl(a.ind_inte_lari_gene, 0) = 0
         AND a.tspa_oid_tipo_soli_pais IN
             (SELECT tspa_oid_tipo_soli_pais
                FROM int_lar_tipo_solici_pedido_dis)
         AND a.num_unid_aten_tota > 0
      --and b.TIDO_OID_TIPO_DOCU=1
       ORDER BY 1;
  
    TYPE dfrec IS RECORD(
      vartidooidtipodocu NUMBER(12));
    TYPE dfrectab IS TABLE OF dfrec;
    dfrecord dfrectab;
  
    CURSOR c_docfac IS
      SELECT b.oid_cabe,
             b.soca_oid_soli_cabe,
             c.val_secu_ruta_terr
      --distinct b.num_docu_cont_inte
        FROM fac_docum_conta_cabec b,
             ped_solic_cabec       a,
             ped_solic_cabec_secue c
       WHERE b.soca_oid_soli_cabe(+) = a.oid_soli_cabe
         AND a.oid_soli_cabe = c.soca_oid_soli_cabe
         AND a.perd_oid_peri = (SELECT x.oid_peri
                                  FROM cra_perio       x,
                                       seg_perio_corpo y,
                                       bas_ctrl_fact   z
                                 WHERE x.peri_oid_peri = y.oid_peri
                                   AND y.cod_peri = z.cod_peri
                                   AND z.ind_camp_act = 1
                                   AND z.sta_camp = 0)
            --      and a.FEC_FACT='14/01/2009'
         AND nvl(a.ind_inte_lari_gene, 0) = 0
         AND a.tspa_oid_tipo_soli_pais IN
             (SELECT tspa_oid_tipo_soli_pais
                FROM int_lar_tipo_solici_pedido_dis)
         AND a.num_unid_aten_tota > 0
         AND b.tido_oid_tipo_docu = 1
       ORDER BY 3,
                2,
                1;
  
    TYPE docfacrec IS RECORD(
      varoidcabe         NUMBER(12),
      varsocaoidsolicabe NUMBER(1),
      varvalsecurutaterr NUMBER(12));
    TYPE docfacrectab IS TABLE OF docfacrec;
    docfacrecord docfacrectab;
  
    CURSOR c_paqdoc IS
      SELECT xxx.oid_docu_impr,
             xxx.val_buff,
             yyy.num_secu_fact_diar
        FROM (SELECT gdi.oid_docu_impr,
                     gdi.val_buff,
                     REPLACE(utl_raw.cast_to_varchar2(dbms_lob.substr(gdi.val_buff,
                                                                      10,
                                                                      dbms_lob.instr(gdi.val_buff,
                                                                                     utl_raw.cast_to_raw('<nbd>')) + 5)),
                             '<',
                             '') bd,
                     utl_raw.cast_to_varchar2(dbms_lob.substr(gdi.val_buff,
                                                              9,
                                                              dbms_lob.instr(gdi.val_buff,
                                                                             utl_raw.cast_to_raw('<ccon>')) + 6)) cons,
                     REPLACE(REPLACE(utl_raw.cast_to_varchar2(dbms_lob.substr(gdi.val_buff,
                                                                              3,
                                                                              dbms_lob.instr(gdi.val_buff,
                                                                                             utl_raw.cast_to_raw('<nsec>')) + 6)),
                                     '<',
                                     ''),
                             '/',
                             '') sec
                FROM gen_colas       gc,
                     gen_docum_impri gdi --,
               WHERE gc.gspo_oid_spool IN
                     (SELECT DISTINCT sdi.gspo_oid_spoo
                        FROM fac_secue_docum_inter sdi,
                             ped_solic_cabec       a
                       WHERE sdi.soca_oid_soli_cabe = a.oid_soli_cabe
                         AND a.perd_oid_peri =
                             (SELECT x.oid_peri
                                FROM cra_perio       x,
                                     seg_perio_corpo y,
                                     bas_ctrl_fact   z
                               WHERE x.peri_oid_peri = y.oid_peri
                                 AND y.cod_peri = z.cod_peri
                                 AND z.ind_camp_act = 1
                                 AND z.sta_camp = 0)
                            --              and a.FEC_FACT='14/01/2009'
                         AND nvl(a.ind_inte_lari_gene, 0) = 0
                         AND a.num_unid_aten_tota > 0)
                 AND gc.oid_cola = gdi.gcol_oid_cola
                 AND gc.imvi_oid_impr IN
                     (SELECT oid_impr
                        FROM fac_impre_virtu x
                       WHERE x.cod_impr LIKE
                             '%' ||
                             (SELECT DISTINCT cod_pais FROM bas_ctrl_fact) || '%'
                         AND x.cod_impr LIKE '%XEROX%')
               ORDER BY 1) xxx,
             (SELECT a.val_nume_soli,
                     b.num_secu_fact_diar,
                     b.soca_oid_soli_cabe,
                     b.val_secu_ruta_terr
                FROM ped_solic_cabec_secue b,
                     ped_solic_cabec       a
               WHERE b.soca_oid_soli_cabe = a.oid_soli_cabe
                 AND a.perd_oid_peri =
                     (SELECT x.oid_peri
                        FROM cra_perio       x,
                             seg_perio_corpo y,
                             bas_ctrl_fact   z
                       WHERE x.peri_oid_peri = y.oid_peri
                         AND y.cod_peri = z.cod_peri
                         AND z.ind_camp_act = 1
                         AND z.sta_camp = 0)
                 AND a.fec_fact = '14/01/2009'
                 AND a.tspa_oid_tipo_soli_pais IN
                     (SELECT tspa_oid_tipo_soli_pais
                        FROM int_lar_tipo_solici_pedido_dis)
                 AND a.num_unid_aten_tota > 0
               ORDER BY val_secu_ruta_terr ASC,
                        a.clie_oid_clie) yyy
       WHERE xxx.bd(+) = yyy.val_nume_soli
       ORDER BY val_secu_ruta_terr ASC,
                cons;
  
    TYPE paqdocrec IS RECORD(
      varoiddocuimpr     NUMBER(12),
      varvalbuff         BLOB,
      varnumsecufactdiar NUMBER(12));
    TYPE paqdocrectab IS TABLE OF paqdocrec;
    paqdocrecord paqdocrectab;
  
  BEGIN
    w_filas := 100000;
  
    OPEN c_sec;
    LOOP
      FETCH c_sec BULK COLLECT
        INTO secrecord LIMIT w_filas;
      IF secrecord.count > 0 THEN
        FOR x IN secrecord.first .. secrecord.last
        LOOP
        
          varnsec            := secrecord(x).varnsec;
          varvalnumesoli     := secrecord(x).varvalnumesoli;
          varnumsecufactdiar := secrecord(x).varnumsecufactdiar;
          varsocaoidsolicabe := secrecord(x).varsocaoidsolicabe;
          varvalsecurutaterr := secrecord(x).varvalsecurutaterr;
        
          UPDATE ped_solic_cabec_secue a
             SET a.num_secu_fact_diar = varnsec
           WHERE a.soca_oid_soli_cabe = varsocaoidsolicabe;
        
        END LOOP;
      END IF;
      EXIT WHEN c_sec%NOTFOUND;
    END LOOP;
    CLOSE c_sec;
  
    OPEN c_df;
    LOOP
      FETCH c_df BULK COLLECT
        INTO dfrecord LIMIT w_filas;
      IF dfrecord.count > 0 THEN
        FOR x IN dfrecord.first .. dfrecord.last
        LOOP
        
          vartidooidtipodocu := dfrecord(x).vartidooidtipodocu;
        
          SELECT MIN(b.num_docu_cont_inte)
            INTO varmininte
            FROM fac_docum_conta_cabec b,
                 ped_solic_cabec       a,
                 ped_solic_cabec_secue c
           WHERE b.soca_oid_soli_cabe(+) = a.oid_soli_cabe
             AND a.oid_soli_cabe = c.soca_oid_soli_cabe
             AND a.perd_oid_peri = 2414
             AND a.fec_fact = '13/01/2009'
             AND a.tspa_oid_tipo_soli_pais IN
                 (SELECT tspa_oid_tipo_soli_pais
                    FROM int_lar_tipo_solici_pedido_dis)
             AND a.num_unid_aten_tota > 0
             AND b.tido_oid_tipo_docu = vartidooidtipodocu
           ORDER BY 1;
        
          OPEN c_docfac;
          LOOP
            FETCH c_docfac BULK COLLECT
              INTO docfacrecord LIMIT w_filas;
            IF docfacrecord.count > 0 THEN
              FOR x IN docfacrecord.first .. docfacrecord.last
              LOOP
              
                varoidcabe         := docfacrecord(x).varoidcabe;
                varsocaoidsolicabe := docfacrecord(x).varsocaoidsolicabe;
                varvalsecurutaterr := docfacrecord(x).varvalsecurutaterr;
              
                UPDATE fac_docum_conta_cabec a
                   SET a.num_docu_cont_inte = varmininte
                 WHERE a.oid_cabe = varoidcabe;
              
                UPDATE fac_docum_conta_cabec
                   SET num_docu_cont_inte = varmininte
                 WHERE oid_cabe = varoidcabe;
              
                UPDATE fac_regis_unico_venta
                   SET num_docu_cont_inte = varmininte
                 WHERE dcca_oid_cabe = varoidcabe;
              
                varmininte := varmininte + 1;
              
              END LOOP;
            END IF;
            EXIT WHEN c_docfac%NOTFOUND;
          END LOOP;
          CLOSE c_docfac;
        
        END LOOP;
      END IF;
      EXIT WHEN c_df%NOTFOUND;
    END LOOP;
    CLOSE c_df;
  
    OPEN c_paqdoc;
    LOOP
      FETCH c_paqdoc BULK COLLECT
        INTO paqdocrecord LIMIT w_filas;
      IF paqdocrecord.count > 0 THEN
        FOR x IN paqdocrecord.first .. paqdocrecord.last
        LOOP
        
          varoiddocuimpr     := paqdocrecord(x).varoiddocuimpr;
          varvalbuff         := paqdocrecord(x).varvalbuff;
          varnumsecufactdiar := paqdocrecord(x).varnumsecufactdiar;
        
          len := dbms_lob.getlength(varvalbuff);
          bl2 := varvalbuff;
          ini := 1;
        
          posi := dbms_lob.instr(bl2, utl_raw.cast_to_raw('<nsec>')) - 1;
        
          IF (posi > 0) THEN
            IF (posi > 32767) THEN
              bl1 := dbms_lob.substr(bl2, 32767, 1);
              dbms_lob.append(bl1,
                              dbms_lob.substr(bl2, (posi - 32767), 32768));
            ELSE
              bl1 := dbms_lob.substr(bl2, posi, 1);
            END IF;
          
            ini := posi + length(varnumsecufactdiar) + 1;
            dbms_lob.append(bl1,
                            utl_raw.cast_to_raw(lpad(to_char(varnumsecufactdiar),
                                                     13)));
            posfin := (len - ini);
          
            IF (posfin > 32767) THEN
              dbms_lob.append(bl1, dbms_lob.substr(bl2, 32767, ini));
              dbms_lob.append(bl1,
                              dbms_lob.substr(bl2,
                                              (posfin - 32767),
                                              (ini + 32767)));
            ELSE
              dbms_lob.append(bl1, dbms_lob.substr(bl2, posfin, ini));
            END IF;
          
            bl2 := bl1;
            bl1 := utl_raw.cast_to_raw(' ');
          END IF;
        
          UPDATE gen_docum_impri
             SET val_buff = bl2
           WHERE oid_docu_impr = varoiddocuimpr;
        
        END LOOP;
      END IF;
      EXIT WHEN c_paqdoc%NOTFOUND;
    END LOOP;
    CLOSE c_paqdoc;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'ERROR ped_pr_secue: ' || ls_sqlerrm);
  END ped_pr_secue;
  /***************************************************************************
  Descripcion       : Prueba Cuadre
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_prueb_cuadr(tmpzona IN VARCHAR2) IS
    varsecnumedocu NUMBER(12);
  
    CURSOR c_f IS
      SELECT a.sec_nume_docu
        FROM int_solic_conso_cabec a
       WHERE a.cod_peri = '200903'
         AND a.num_lote = '00000483'
         AND cod_zona = tmpzona;
  
    TYPE frec IS RECORD(
      varsecnumedocu NUMBER(12));
    TYPE frectab IS TABLE OF frec;
    frecord frectab;
  
  BEGIN
    OPEN c_f;
    LOOP
      FETCH c_f BULK COLLECT
        INTO frecord LIMIT 10000;
      IF frecord.count > 0 THEN
        FOR y IN frecord.first .. frecord.last
        LOOP
        
          varsecnumedocu := frecord(y).varsecnumedocu;
        
          ped_pr_cuadr_ofert(varsecnumedocu);
        END LOOP;
      END IF;
      EXIT WHEN c_f%NOTFOUND;
    END LOOP;
    CLOSE c_f;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_prueb_cuadr: ' || ls_sqlerrm);
  END ped_pr_prueb_cuadr;
  /***************************************************************************
  Descripcion       : Stock
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_stock IS
  
    w_filas        NUMBER(12);
    tmp_unidades   NUMBER(12);
    varoidsoliposi NUMBER(12);
    varunidcomp    NUMBER(12);
    varorden       NUMBER(12);
    varorden2      NUMBER(12);
    varorden3      NUMBER(12);
    varcodclie     VARCHAR2(12);
  
    varprodoidprod NUMBER(12);
    varalmcoidalma NUMBER(12);
    varoidclie     NUMBER(12);
    vardiferencia  NUMBER(12);
  
    varcontrolunidades NUMBER(12);
    varcontrolporc     NUMBER(12);
    varunidporaten     NUMBER(12);
  
    varoiddetaoferalter NUMBER(12);
    varoidprodalter     NUMBER(12);
    varsaldoalter       NUMBER(12);
    varcodiventalter    VARCHAR2(12);
  
    varcodventfict VARCHAR(12);
    varcopa        NUMBER(12);
  
    varunid NUMBER(12);
  
    lscodpais       VARCHAR2(15);
    lscodperi       VARCHAR2(15);
    lnoidperi       NUMBER(10);
    lsfecha         DATE;
    lscalcufletdesc VARCHAR2(1);
  
    ln_tiponavi VARCHAR2(10);
  
    lsindgenebr      VARCHAR2(1);
    lsindrecuate     VARCHAR2(1);
    lscodigoperiodo  VARCHAR2(6);
    lscodigoperiodo1 VARCHAR2(6);
    lscodigoperiodo2 VARCHAR2(6);
    lnoidperiodo     NUMBER;
    lnoidperiodo1    NUMBER;
    lnoidperiodo2    NUMBER;
  
    CURSOR c_reversto(monto NUMBER) IS
      SELECT x.oid_soli_cabe,
             x.val_base_flet_loca,
             x.clie_oid_clie
        FROM ped_solic_cabec       x,
             int_solic_conso_cabec b
       WHERE x.oid_soli_cabe = b.soca_oid_soli_cabe_refe
         AND x.oid_soli_cabe IN (SELECT a.oid_soli_cabe
                                   FROM tmp_ped         tmp,
                                        ped_solic_cabec a
                                  WHERE a.oid_soli_cabe = tmp.oid_soli_cabe
                                    AND a.ind_oc = 1)
         AND x.val_base_flet_loca <= monto;
  
    r_reversto c_reversto%ROWTYPE;
  
    CURSOR c_stock IS
      SELECT prod_oid_prod,
             almc_oid_alma,
             (val_sald - comp) * -1 dif
        FROM (SELECT xx.prod_oid_prod,
                     xx.almc_oid_alma,
                     xx.comp,
                     nvl(z.val_sald, 0) val_sald
                FROM (SELECT prod_oid_prod,
                             nvl(b.almc_oid_almc, tmp.almc_oid_alma) almc_oid_alma,
                             SUM(b.num_unid_por_aten) comp
                        FROM tmp_ped tmp,
                             --ped_solic_cabec a,
                             ped_solic_posic b
                       WHERE tmp.oid_soli_cabe = b.soca_oid_soli_cabe
                            /*and a.perd_oid_peri =
                            (SELECT x.oid_peri
                               FROM cra_perio x, seg_perio_corpo y, bas_ctrl_fact z
                              WHERE x.peri_oid_peri = y.oid_peri
                                AND y.cod_peri = z.cod_peri
                                AND z.ind_camp_act = 1
                                AND z.sta_camp = 0)*/
                         AND b.espo_oid_esta_posi <> 2
                      --AND a.oid_soli_cabe = tmp.oid_soli_cabe
                       GROUP BY prod_oid_prod,
                                nvl(b.almc_oid_almc, tmp.almc_oid_alma)) xx,
                     bel_stock z
               WHERE xx.prod_oid_prod = z.prod_oid_prod(+)
                 AND xx.almc_oid_alma = z.almc_oid_alma(+)
                 AND z.esme_oid_esta_merc = 2001
                 AND z.val_sald <> 0)
       WHERE val_sald < comp;
  
    TYPE stockrec IS RECORD(
      varprodoidprod NUMBER(12),
      varalmcoidalma NUMBER(12),
      vardif         NUMBER(12));
  
    TYPE stockrectab IS TABLE OF stockrec;
    stockrecord stockrectab;
  
    CURSOR c_prod
    (
      tmpoidprod NUMBER,
      tmpoidalmc NUMBER
    ) IS
      SELECT b.oid_soli_posi,
             b.num_unid_compr,
             --e.cod_clie,
             '0' cod_clie,
             nvl((SELECT MIN(f.num_orde)
                   FROM ped_secue_tipif      f,
                        mae_clien_tipo_subti g,
                        mae_clien_clasi      h
                  WHERE a.clie_oid_clie = g.clie_oid_clie
                    AND g.oid_clie_tipo_subt = h.ctsu_oid_clie_tipo_subt
                    AND f.ticl_oid_tipo_clie = g.ticl_oid_tipo_clie
                    AND f.sbti_oid_subt_clie = g.sbti_oid_subt_clie
                    AND f.tccl_oid_tipo_clas = h.tccl_oid_tipo_clasi
                    AND f.clas_oid_clas = h.clas_oid_clas),
                 99) prioridad,
             CASE
               WHEN a.tspa_oid_tipo_soli_pais IN
                    (SELECT a.oid_tipo_soli_pais --, c.VAL_I18N
                       FROM ped_tipo_solic_pais a,
                            ped_tipo_solic      b,
                            gen_i18n_sicc_comun c
                      WHERE a.tsol_oid_tipo_soli = b.oid_tipo_soli
                        AND b.oid_tipo_soli = c.val_oid
                        AND c.attr_enti = 'PED_TIPO_SOLIC'
                        AND upper(c.val_i18n) LIKE '%MAV%'
                        AND b.ind_cons = 0
                        AND b.ind_soli_nega = 0) THEN
                0
               ELSE
                99
             END prioridad2,
             CASE
               WHEN c.ind_rece_web = 1 AND c.ind_fac_refac = 'FA' AND
                    c.ind_vali_prol = '1' THEN
                0
               ELSE
                99
             END prioridad3
        FROM tmp_ped               tmp,
             ped_solic_cabec       a,
             int_solic_conso_cabec c,
             ped_solic_posic       b
      --, mae_clien e
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND a.oid_soli_cabe = c.soca_oid_soli_cabe_refe(+)
         AND a.oid_soli_cabe = tmp.oid_soli_cabe
            --AND tmp.clie_oid_clie = e.oid_clie
         AND b.prod_oid_prod = tmpoidprod
         AND nvl(b.almc_oid_almc, a.almc_oid_alma) = tmpoidalmc
         AND b.espo_oid_esta_posi <> 2
       ORDER BY 6               DESC,
                tmp.ind_oc      DESC,
                5               DESC,
                4               DESC,
                a.clie_oid_clie DESC;
  
    TYPE prodrec IS RECORD(
      varoidsoliposi NUMBER(12),
      varunidcomp    NUMBER(12),
      varcodclie     VARCHAR2(12),
      varorden       NUMBER(12),
      varorden2      NUMBER(12),
      varorden3      NUMBER(12));
  
    TYPE prodrectab IS TABLE OF prodrec;
    prodrecord prodrectab;
  
    CURSOR c_prod2 IS
    
      SELECT b.prod_oid_prod,
             nvl(b.almc_oid_almc, tmp.almc_oid_alma) almc_oid_alma,
             SUM(b.num_unid_compr)
        FROM tmp_ped tmp
             --, ped_solic_cabec a
            ,
             ped_solic_posic b
       WHERE tmp.oid_soli_cabe = b.soca_oid_soli_cabe
            /* and a.perd_oid_peri =
                            (SELECT x.oid_peri
                               FROM cra_perio x, seg_perio_corpo y, bas_ctrl_fact z
                              WHERE x.peri_oid_peri = y.oid_peri
                                AND y.cod_peri = z.cod_peri
                                AND z.ind_camp_act = 1
                                AND z.sta_camp = 0)
            AND a.oid_soli_cabe = tmp.oid_soli_cabe*/
         AND b.espo_oid_esta_posi <> 2
         AND b.stpo_oid_subt_posi <> 7
       GROUP BY b.prod_oid_prod,
                nvl(b.almc_oid_almc, tmp.almc_oid_alma);
  
    TYPE prod2rec IS RECORD(
      varprodoidprod NUMBER(12),
      varalmcoidalma NUMBER(12),
      varunidcomp    NUMBER(12));
  
    TYPE prod2rectab IS TABLE OF prod2rec;
    prod2record prod2rectab;
  
    CURSOR c_cstock IS
    
      SELECT DISTINCT b.oid_soli_posi,
                      d.val_unid,
                      d.val_porc,
                      b.num_unid_por_aten
        FROM tmp_ped tmp,
             --ped_solic_cabec      a,
             ped_solic_posic      b,
             ped_secue_proce      c,
             ped_gesti_stock      d,
             zon_zona             e,
             zon_regio            f,
             mae_clien_tipo_subti g,
             mae_clien_clasi      h
       WHERE tmp.oid_soli_cabe = b.soca_oid_soli_cabe
            /*and a.perd_oid_peri =
            (SELECT x.oid_peri
               FROM cra_perio x, seg_perio_corpo y, bas_ctrl_fact z
              WHERE x.peri_oid_peri = y.oid_peri
                AND y.cod_peri = z.cod_peri
                AND z.ind_camp_act = 1
                AND z.sta_camp = 0)  */
         AND d.perd_oid_peri = (SELECT x.oid_peri
                                  FROM cra_perio       x,
                                       seg_perio_corpo y,
                                       bas_ctrl_fact   z
                                 WHERE x.peri_oid_peri = y.oid_peri
                                   AND y.cod_peri = z.cod_peri
                                   AND z.ind_camp_act = 1
                                   AND z.sta_camp = 0)
            --AND a.oid_soli_cabe = tmp.oid_soli_cabe
         AND tmp.tspa_oid_tipo_soli_pais = c.tspa_oid_tipo_soli_pais
         AND c.proc_oid_proc = 51
         AND b.ofde_oid_deta_ofer = d.ofde_oid_deta_ofer
         AND (d.val_unid IS NOT NULL OR d.val_porc IS NOT NULL)
         AND (d.ind_acti IS NULL OR d.ind_acti = '1')
         AND tmp.zzon_oid_zona = e.oid_zona
         AND e.zorg_oid_regi = f.oid_regi
         AND tmp.zzon_oid_zona =
             decode(d.zzon_oid_zona,
                    NULL,
                    tmp.zzon_oid_zona,
                    d.zzon_oid_zona)
         AND f.oid_regi =
             decode(d.zorg_oid_regi, NULL, f.oid_regi, d.zorg_oid_regi)
         AND tmp.clie_oid_clie = g.clie_oid_clie
         AND b.espo_oid_esta_posi <> 2
         AND g.oid_clie_tipo_subt = h.ctsu_oid_clie_tipo_subt
         AND g.ticl_oid_tipo_clie =
             decode(d.ticl_oid_tipo_clie,
                    NULL,
                    g.ticl_oid_tipo_clie,
                    d.ticl_oid_tipo_clie)
         AND g.sbti_oid_subt_clie =
             decode(d.sbti_oid_subt_clie,
                    NULL,
                    g.sbti_oid_subt_clie,
                    d.sbti_oid_subt_clie)
         AND h.tccl_oid_tipo_clasi =
             decode(d.tccl_oid_tipo_clas,
                    NULL,
                    h.tccl_oid_tipo_clasi,
                    d.tccl_oid_tipo_clas)
         AND h.clas_oid_clas =
             decode(d.clas_oid_clas, NULL, h.clas_oid_clas, d.clas_oid_clas)
         AND b.espo_oid_esta_posi <> 2
         AND tmp.ind_oc = 1;
  
    TYPE cstockrec IS RECORD(
      varoidsoliposi     NUMBER(12),
      varcontrolunidades NUMBER(12),
      varcontrolporc     NUMBER(12),
      varunidporaten     NUMBER(12));
  
    TYPE cstockrectab IS TABLE OF cstockrec;
    cstockrecord cstockrectab;
  
    CURSOR c_liq IS
    
      SELECT DISTINCT b.oid_soli_posi
        FROM tmp_ped tmp,
             --ped_solic_cabec      a,
             ped_solic_posic      b,
             ped_secue_proce      c,
             ped_gesti_stock      d,
             zon_zona             e,
             zon_regio            f,
             mae_clien_tipo_subti g,
             mae_clien_clasi      h
       WHERE tmp.oid_soli_cabe = b.soca_oid_soli_cabe
            /*and a.perd_oid_peri =
            (SELECT x.oid_peri
               FROM cra_perio x, seg_perio_corpo y, bas_ctrl_fact z
              WHERE x.peri_oid_peri = y.oid_peri
                AND y.cod_peri = z.cod_peri
                AND z.ind_camp_act = 1
                AND z.sta_camp = 0)   */
         AND d.perd_oid_peri = (SELECT x.oid_peri
                                  FROM cra_perio       x,
                                       seg_perio_corpo y,
                                       bas_ctrl_fact   z
                                 WHERE x.peri_oid_peri = y.oid_peri
                                   AND y.cod_peri = z.cod_peri
                                   AND z.ind_camp_act = 1
                                   AND z.sta_camp = 0)
            --AND a.oid_soli_cabe = tmp.oid_soli_cabe
         AND tmp.tspa_oid_tipo_soli_pais = c.tspa_oid_tipo_soli_pais
         AND c.proc_oid_proc = 51
         AND b.ofde_oid_deta_ofer = d.ofde_oid_deta_ofer
         AND nvl(d.ind_ctrl_liqu, 0) > 0
         AND tmp.zzon_oid_zona = e.oid_zona
         AND e.zorg_oid_regi = f.oid_regi
         AND tmp.zzon_oid_zona =
             decode(d.zzon_oid_zona,
                    NULL,
                    tmp.zzon_oid_zona,
                    d.zzon_oid_zona)
         AND f.oid_regi =
             decode(d.zorg_oid_regi, NULL, f.oid_regi, d.zorg_oid_regi)
         AND tmp.clie_oid_clie = g.clie_oid_clie
         AND b.espo_oid_esta_posi <> 2
         AND g.oid_clie_tipo_subt = h.ctsu_oid_clie_tipo_subt
         AND g.ticl_oid_tipo_clie =
             decode(d.ticl_oid_tipo_clie,
                    NULL,
                    g.ticl_oid_tipo_clie,
                    d.ticl_oid_tipo_clie)
         AND g.sbti_oid_subt_clie =
             decode(d.sbti_oid_subt_clie,
                    NULL,
                    g.sbti_oid_subt_clie,
                    d.sbti_oid_subt_clie)
         AND h.tccl_oid_tipo_clasi =
             decode(d.tccl_oid_tipo_clas,
                    NULL,
                    h.tccl_oid_tipo_clasi,
                    d.tccl_oid_tipo_clas)
         AND h.clas_oid_clas =
             decode(d.clas_oid_clas, NULL, h.clas_oid_clas, d.clas_oid_clas)
         AND b.espo_oid_esta_posi <> 2
         AND tmp.ind_oc = 1;
  
    TYPE cliqrec IS RECORD(
      varoidsoliposi NUMBER(12));
  
    TYPE cliqrectab IS TABLE OF cliqrec;
    cliqrecord cliqrectab;
  
    CURSOR c_falta IS
      SELECT b.ofde_oid_deta_ofer,
             b.oid_soli_posi,
             --             SUM(b.num_unid_por_aten - b.num_unid_compr) comp
             b.num_unid_por_aten,
             nvl(b.almc_oid_almc, tmp.almc_oid_alma) almc_oid_alma
        FROM tmp_ped tmp
             --, ped_solic_cabec a
            ,
             ped_solic_posic b
       WHERE tmp.oid_soli_cabe = b.soca_oid_soli_cabe
            /*and a.perd_oid_peri =
                              (SELECT x.oid_peri
                                 FROM cra_perio x, seg_perio_corpo y, bas_ctrl_fact z
                                WHERE x.peri_oid_peri = y.oid_peri
                                  AND y.cod_peri = z.cod_peri
                                  AND z.ind_camp_act = 1
                                  AND z.sta_camp = 0)
            AND a.oid_soli_cabe = tmp.oid_soli_cabe*/
         AND b.num_unid_por_aten - b.num_unid_compr > 0
         AND b.espo_oid_esta_posi <> 2
         AND tmp.ind_oc = 1
         AND b.ofde_oid_deta_ofer IN
             (SELECT pmf.ofde_oid_deta_ofer
                FROM pre_matri_factu       pmf,
                     pre_matri_codig_alter pmca,
                     pre_matri_factu       pmf2,
                     pre_ofert_detal       pod,
                     bel_stock             bs
               WHERE pmf.oid_matr_fact = pmca.mafa_oid_cod_ppal
                 AND pmca.mafa_oid_cod_alte = pmf2.oid_matr_fact
                 AND pmf2.ofde_oid_deta_ofer = pod.oid_deta_ofer
                 AND pod.prod_oid_prod = bs.prod_oid_prod
                 AND bs.esme_oid_esta_merc = 2001
                 AND nvl(pmca.ind_acti, 1) = 1
                 AND bs.val_sald > 0);
    --GROUP BY b.ofde_oid_deta_ofer;
  
    TYPE faltarec IS RECORD(
      varprodoidprod NUMBER(12),
      varoidsoliposi NUMBER(12),
      varunidporaten NUMBER(12),
      varalmcoidalma NUMBER(12));
  
    TYPE faltarectab IS TABLE OF faltarec;
    faltarecord faltarectab;
  
    CURSOR c_alter
    (
      tmpoiddetaofer NUMBER,
      tmpoidalma     NUMBER,
      unidporaten    NUMBER
    ) IS
      SELECT *
        FROM (SELECT pod.oid_deta_ofer,
                     pod.prod_oid_prod,
                     bs.val_sald,
                     pod.val_codi_vent
                FROM pre_matri_factu       pmf,
                     pre_matri_codig_alter pmca,
                     pre_matri_factu       pmf2,
                     pre_ofert_detal       pod,
                     bel_stock             bs
               WHERE pmf.ofde_oid_deta_ofer = tmpoiddetaofer
                 AND pmf.oid_matr_fact = pmca.mafa_oid_cod_ppal
                 AND pmca.mafa_oid_cod_alte = pmf2.oid_matr_fact
                 AND pmf2.ofde_oid_deta_ofer = pod.oid_deta_ofer
                 AND pod.prod_oid_prod = bs.prod_oid_prod
                 AND bs.esme_oid_esta_merc = 2001
                 AND bs.val_sald >= unidporaten
                 AND nvl(pmca.ind_acti, 1) = 1
                 AND bs.almc_oid_alma = tmpoidalma
               ORDER BY pmca.num_orde ASC)
       WHERE rownum = 1;
  
    TYPE alterrec IS RECORD(
      varoiddetaoferalter NUMBER(12),
      varoidprodalter     NUMBER(12),
      varsaldoalter       NUMBER(12),
      varcodiventalter    VARCHAR2(12));
  
    TYPE alterrectab IS TABLE OF alterrec;
    alterrecord alterrectab;
  
    CURSOR c_falta_prem IS
      SELECT b.val_codi_vent_fict,
             a.copa_oid_para_gene,
             b.oid_soli_posi,
             --             SUM(b.num_unid_por_aten - b.num_unid_compr) comp
             b.num_unid_por_aten,
             nvl(b.almc_oid_almc, tmp.almc_oid_alma) almc_oid_alma,
             a.clie_oid_clie
        FROM tmp_ped         tmp,
             ped_solic_cabec a,
             ped_solic_posic b
       WHERE tmp.oid_soli_cabe = b.soca_oid_soli_cabe
            /*and a.perd_oid_peri =
            (SELECT x.oid_peri
               FROM cra_perio x, seg_perio_corpo y, bas_ctrl_fact z
              WHERE x.peri_oid_peri = y.oid_peri
                AND y.cod_peri = z.cod_peri
                AND z.ind_camp_act = 1
                AND z.sta_camp = 0)*/
         AND a.oid_soli_cabe = tmp.oid_soli_cabe
         AND b.num_unid_por_aten - b.num_unid_compr > 0
         AND b.espo_oid_esta_posi <> 2
            --         AND tmp.ind_oc = 1
         AND b.val_codi_vent_fict IS NOT NULL;
    --GROUP BY b.ofde_oid_deta_ofer;
  
    TYPE faltapremrec IS RECORD(
      varcodventfict VARCHAR(12),
      varcopa        NUMBER(12),
      varoidsoliposi NUMBER(12),
      varunidporaten NUMBER(12),
      varalmcoidalma NUMBER(12),
      varoidclie     NUMBER(12));
  
    TYPE faltapremrectab IS TABLE OF faltapremrec;
    faltapremrecord faltapremrectab;
  
    CURSOR c_alter_prem
    (
      tmpcodventfict VARCHAR,
      tmpcopa        NUMBER,
      tmpoidalma     NUMBER,
      unidporaten    NUMBER,
      p_oidclie      NUMBER
    ) IS
    /*SELECT *
            FROM (
                  select
                      ral.num_unid*unidporaten ,ral.prod_oid_prod, sto.val_sald, ral.cod_vent_fict
                  from
                      inc_concu_param_gener cpg , inc_param_gener_premi pgp , inc_param_nivel_premi pnp
                      , inc_premi_artic pa, inc_lote_premi_artic lpa , inc_artic_lote al
                      , inc_reemp_artic_lote ral--, ped_solic_cabec sc , ped_solic_posic sp
                      , bel_stock sto
                  where
                  cpg.oid_para_gral = pgp.copa_oid_para_gral
                  and pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
                  and pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
                  and pa.oid_prem_arti = lpa.prar_oid_prem_arti
                  and lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
                  and al.oid_arti_lote = ral.arlo_oid_arti_lote
                  and ral.ind_acti =1
                  and ctre_oid_crit_reem = 1
                  and inc_pkg_proce_incen.INC_FN_VALID_AMBIT_GEOGR_REEMP(ral.oid_reem_arti_lote,p_oidclie)='1'
                  --
                  and cpg.oid_para_gral= tmpcopa
                  and al.cod_vent_fict=tmpcodventfict
                  and ral.prod_oid_prod=sto.prod_oid_prod
                  and sto.val_sald>=unidporaten
                  and sto.almc_oid_alma=tmpoidalma
                  AND sto.esme_oid_esta_merc = 2001
                  order by ral.num_orde asc
                   )
           WHERE rownum = 1;*/
      SELECT *
        FROM (SELECT ral.num_unid * unidporaten,
                     ral.prod_oid_prod,
                     sto.val_sald,
                     ral.cod_vent_fict
                FROM inc_concu_param_gener cpg,
                     inc_param_gener_premi pgp,
                     inc_param_nivel_premi pnp,
                     inc_premi_artic       pa,
                     inc_lote_premi_artic  lpa,
                     inc_artic_lote        al,
                     inc_reemp_artic_lote  ral --, ped_solic_cabec sc , ped_solic_posic sp
                    ,
                     bel_stock             sto
                     ----- Nuevo HRG 22/12/2013
                    ,
                     (SELECT g.arlo_oid_arti_lote,
                             g.num_orde
                        FROM inc_concu_param_gener a,
                             inc_param_gener_premi b,
                             inc_param_nivel_premi c,
                             inc_premi_artic       d,
                             inc_lote_premi_artic  e,
                             inc_artic_lote        f,
                             inc_reemp_artic_lote  g
                       WHERE a.oid_para_gral = b.copa_oid_para_gral
                         AND b.oid_para_gene_prem = c.pagp_oid_para_gene_prem
                         AND c.oid_para_nive_prem = d.panp_oid_para_nive_prem
                         AND d.oid_prem_arti = e.prar_oid_prem_arti
                         AND e.oid_lote_prem_arti = f.lopa_oid_lote_prem_arti
                         AND f.oid_arti_lote = g.arlo_oid_arti_lote
                         AND g.ind_acti = 1
                         AND a.oid_para_gral = tmpcopa
                         AND g.cod_vent_fict = tmpcodventfict
                         AND rownum = 1) llreemp
              -----
               WHERE cpg.oid_para_gral = pgp.copa_oid_para_gral
                 AND pgp.oid_para_gene_prem = pnp.pagp_oid_para_gene_prem
                 AND pnp.oid_para_nive_prem = pa.panp_oid_para_nive_prem
                 AND pa.oid_prem_arti = lpa.prar_oid_prem_arti
                 AND lpa.oid_lote_prem_arti = al.lopa_oid_lote_prem_arti
                 AND al.oid_arti_lote = ral.arlo_oid_arti_lote
                 AND ral.arlo_oid_arti_lote = llreemp.arlo_oid_arti_lote(+) ---Nuevo HRG 22/12/2013
                 AND ral.ind_acti = 1
                 AND ctre_oid_crit_reem = 1
                 AND inc_pkg_proce_incen.inc_fn_valid_ambit_geogr_reemp(ral.oid_reem_arti_lote,
                                                                        p_oidclie) = '1'
                    --
                 AND cpg.oid_para_gral = tmpcopa
                    --and al.cod_vent_fict=tmpcodventfict   Se elimina HRG 22/12/2013
                 AND (al.cod_vent_fict = tmpcodventfict OR
                     ral.num_orde > llreemp.num_orde) -- Nuevo HRG 22/12/2013
                 AND ral.prod_oid_prod = sto.prod_oid_prod
                 AND sto.val_sald >= unidporaten
                 AND sto.almc_oid_alma = tmpoidalma
                 AND sto.esme_oid_esta_merc = 2001
               ORDER BY ral.num_orde ASC)
       WHERE rownum = 1;
  
    TYPE alterpremrec IS RECORD(
      varunid          NUMBER(12),
      varoidprodalter  NUMBER(12),
      varsaldoalter    NUMBER(12),
      varcodiventalter VARCHAR2(12));
  
    TYPE alterpremrectab IS TABLE OF alterpremrec;
    alterpremrecord alterpremrectab;
  
    lnmonto NUMBER(12, 2);
  
    lslimventauto VARCHAR2(2);
  
    lstipolimiauto VARCHAR2(2);
  
    lsporcdesv   VARCHAR2(5);
    lsnumpedprom VARCHAR2(5);
    lsindprocmav VARCHAR2(1);  -- procesa solo MAV

    lnnroprocmav NUMBER(10);

  
  BEGIN
  
    SELECT x.oid_peri,
           z.cod_pais,
           z.cod_peri,
           z.fec_proc
      INTO lnoidperi,
           lscodpais,
           lscodperi,
           lsfecha
      FROM cra_perio       x,
           seg_perio_corpo y,
           bas_ctrl_fact   z
     WHERE x.peri_oid_peri = y.oid_peri
       AND y.cod_peri = z.cod_peri
       AND z.ind_camp_act = 1
       AND z.sta_camp = 0;
  
    lnmonto := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                    'STO_MONTO_FACTU');
  
    lslimventauto := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                              'STO_LIMVEN_AUTO'),
                         'N');
  
    lstipolimiauto := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                               'STO_TIPOLV_AUTO'),
                          '1');
  
    ln_tiponavi := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                            'STO_TIPO_NAVID'),
                       '1');
  
    lsindrecuate := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                             'STO_IND_RECUP_ATE'),
                        'N');

    lsindprocmav := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                             'STO_IND_PROC_MAV'),
                        'N');

  
    SELECT val_para
      INTO lsnumpedprom
      FROM bas_param_pais x
     WHERE x.cod_pais = lscodpais
       AND x.cod_sist = 'IMP'
       AND x.cod_para = '010';
  
    lsporcdesv := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                       'STO_DESV_GP3');
  
    w_filas := 100000;
  
    UPDATE ped_solic_cabec a
       SET a.grpr_oid_grup_proc = 3,
           a.val_tota_paga_loca = 0
     WHERE a.grpr_oid_grup_proc = 1
       AND a.modu_oid_modu = 13
       AND a.perd_oid_peri = (SELECT x.oid_peri
                                FROM cra_perio       x,
                                     seg_perio_corpo y,
                                     bas_ctrl_fact   z
                               WHERE x.peri_oid_peri = y.oid_peri
                                 AND y.cod_peri = z.cod_peri
                                 AND z.ind_camp_act = 1
                                 AND z.sta_camp = 0)
       AND a.tspa_oid_tipo_soli_pais IN
           (SELECT a.oid_tipo_soli_pais --, c.VAL_I18N
              FROM ped_tipo_solic_pais a,
                   ped_tipo_solic      b --,
            --gen_i18n_sicc_comun c
             WHERE a.tsol_oid_tipo_soli = b.oid_tipo_soli
                  --AND b.oid_tipo_soli = c.val_oid
               AND b.ind_cons = 0
               AND b.ind_soli_nega = 0);
  
    UPDATE ped_solic_cabec aa
       SET almc_oid_alma = nvl((SELECT DISTINCT a1.oid_alma
                                 FROM bel_almac             a1,
                                      app_confi_centr_distr b1,
                                      ape_confi_liafp_cabec c1,
                                      ape_confi_liafp_detal d1
                                WHERE a1.ccdi_oid_confi_centr_distr =
                                      b1.oid_conf_cent_dist
                                  AND b1.oid_conf_cent_dist =
                                      c1.ccdi_oid_conf_cent_dist
                                  AND c1.oid_conf_lafp_cabe =
                                      d1.liac_oid_conf_lafp_cabe
                                  AND d1.zzon_oid_zona = aa.zzon_oid_zona),
                               aa.almc_oid_alma)
     WHERE oid_soli_cabe IN
           (SELECT oid_soli_cabe
              FROM ped_solic_cabec     a,
                   ped_tipo_solic_pais c,
                   ped_tipo_solic      d
             WHERE a.perd_oid_peri =
                   (SELECT x.oid_peri
                      FROM cra_perio       x,
                           seg_perio_corpo y,
                           bas_ctrl_fact   z
                     WHERE x.peri_oid_peri = y.oid_peri
                       AND y.cod_peri = z.cod_peri
                       AND z.ind_camp_act = 1
                       AND z.sta_camp = 0)
               AND a.grpr_oid_grup_proc IN (3)
               AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
               AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
               AND nvl(d.ind_soli_nega, 0) = 0
               AND a.fec_repo_falt IS NULL
            --AND a.ind_oc = 0
            /*and nvl((SELECT DISTINCT a1.oid_alma
              FROM bel_almac             a1,
                   app_confi_centr_distr b1,
                   ape_confi_liafp_cabec c1,
                   ape_confi_liafp_detal d1
             WHERE a1.ccdi_oid_confi_centr_distr = b1.oid_conf_cent_dist
               AND b1.oid_conf_cent_dist = c1.ccdi_oid_conf_cent_dist
               AND c1.oid_conf_lafp_cabe = d1.liac_oid_conf_lafp_cabe
               AND d1.zzon_oid_zona = a.zzon_oid_zona),
            2001)<>a.almc_oid_alma*/
            );
  
    INSERT INTO tmp_ped
      (SELECT oid_soli_cabe,
              tspa_oid_tipo_soli_pais,
              clie_oid_clie,
              zzon_oid_zona,
              ind_oc,
              a.almc_oid_alma
         FROM ped_solic_cabec     a,
              ped_tipo_solic_pais c,
              ped_tipo_solic      d
        WHERE a.perd_oid_peri = (SELECT x.oid_peri
                                   FROM cra_perio       x,
                                        seg_perio_corpo y,
                                        bas_ctrl_fact   z
                                  WHERE x.peri_oid_peri = y.oid_peri
                                    AND y.cod_peri = z.cod_peri
                                    AND z.ind_camp_act = 1
                                    AND z.sta_camp = 0)
          AND a.grpr_oid_grup_proc IN (3, 4)
          AND a.fec_prog_fact = (SELECT z.fec_proc
                                   FROM bas_ctrl_fact z
                                  WHERE z.ind_camp_act = 1
                                    AND z.sta_camp = 0)
          AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
          AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
          AND nvl(d.ind_soli_nega, 0) = 0
          AND a.fec_repo_falt IS NULL
          AND a.ind_oc = 1);
  
    ---- Proceso para recuperar las atenciones de otras campaña
    IF lsindrecuate = 'S' THEN
    
      lsindgenebr := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                          'STO_IND_GENE_BR');
    
      SELECT z.cod_peri
        INTO lscodigoperiodo
        FROM bas_ctrl_fact z
       WHERE z.ind_camp_act = 1
         AND z.sta_camp = 0;
    
      SELECT z.cod_peri,
             x.oid_peri
        INTO lscodigoperiodo,
             lnoidperiodo
        FROM cra_perio       x,
             seg_perio_corpo y,
             bas_ctrl_fact   z
       WHERE x.peri_oid_peri = y.oid_peri
         AND y.cod_peri = z.cod_peri
         AND z.ind_camp_act = 1
         AND z.sta_camp = 0;
    
      --- Obtiene periodos para controlar la nueva forma de trabajo de colombia
      lscodigoperiodo1 := fin_pkg_gener.fin_fn_obtie_nsgte_campa(lscodigoperiodo,
                                                                 -1);
      lscodigoperiodo2 := fin_pkg_gener.fin_fn_obtie_nsgte_campa(lscodigoperiodo,
                                                                 -2);
    
      IF lsindgenebr = '1' THEN
        lnoidperiodo1 := fin_pkg_gener.fin_fn_obtie_oid_perio(lscodpais,
                                                              lscodigoperiodo1);
        lnoidperiodo2 := fin_pkg_gener.fin_fn_obtie_oid_perio(lscodpais,
                                                              lscodigoperiodo2);
      ELSE
        lnoidperiodo1 := lnoidperiodo;
        lnoidperiodo2 := lnoidperiodo;
      END IF;
    
      --- Recupera las atenciones solo para Recojo inteligente (Colombia)
      IF lsindgenebr = '1' THEN
        --- Actualiza las atenciones para la campa?a actual
        UPDATE ped_solic_cabec psc
           SET psc.perd_oid_peri = lnoidperiodo,
               psc.fec_prog_fact =
               (SELECT fec_fina FROM cra_perio WHERE oid_peri = lnoidperiodo),
               psc.val_punt_emis = 777
         WHERE psc.fec_fact IS NULL
           AND psc.ind_oc = 0
           AND psc.fec_repo_falt IS NULL
           AND psc.modu_oid_modu <> 23
           AND psc.perd_oid_peri IN
               (lnoidperiodo, lnoidperiodo1, lnoidperiodo2)
           AND psc.clie_oid_clie IN (SELECT clie_oid_clie FROM tmp_ped);
      END IF;
    
    END IF;
  
    INSERT INTO tmp_ped
      (SELECT oid_soli_cabe,
              tspa_oid_tipo_soli_pais,
              clie_oid_clie,
              zzon_oid_zona,
              ind_oc,
              a.almc_oid_alma
         FROM ped_solic_cabec     a,
              ped_tipo_solic_pais c,
              ped_tipo_solic      d
        WHERE a.perd_oid_peri = (SELECT x.oid_peri
                                   FROM cra_perio       x,
                                        seg_perio_corpo y,
                                        bas_ctrl_fact   z
                                  WHERE x.peri_oid_peri = y.oid_peri
                                    AND y.cod_peri = z.cod_peri
                                    AND z.ind_camp_act = 1
                                    AND z.sta_camp = 0)
          AND a.grpr_oid_grup_proc IN (3)
          AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
          AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
          AND nvl(d.ind_soli_nega, 0) = 0
          AND a.fec_repo_falt IS NULL
          AND a.ind_oc = 0
          AND a.modu_oid_modu <> 23
          AND ((a.zzon_oid_zona IN
              (
                 /*SELECT DISTINCT zzon_oid_zona
                    FROM ped_solic_cabec a, ped_tipo_solic_pais c, ped_tipo_solic d
                   WHERE a.ind_oc = 1
                   AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
                   AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
                   AND nvl(d.ind_soli_nega, 0) = 0
                   AND a.grpr_oid_grup_proc IN (3, 4)
                   and a.perd_oid_peri =
                 (SELECT x.oid_peri
                    FROM cra_perio x, seg_perio_corpo y, bas_ctrl_fact z
                   WHERE x.peri_oid_peri = y.oid_peri
                     AND y.cod_peri = z.cod_peri
                     AND z.ind_camp_act = 1
                     AND z.sta_camp = 0)
                  UNION*/
                 SELECT DISTINCT zzon_oid_zona
                   FROM fac_contr_cierr
                  WHERE zzon_oid_zona IS NOT NULL
                    AND EXISTS
                  (SELECT 1
                           FROM ped_tipo_solic_servi
                          WHERE tspa_oid_tipo_soli_pais =
                                a.tspa_oid_tipo_soli_pais)
                    AND perd_oid_peri = (SELECT x.oid_peri
                                           FROM cra_perio       x,
                                                seg_perio_corpo y,
                                                bas_ctrl_fact   z
                                          WHERE x.peri_oid_peri = y.oid_peri
                                            AND y.cod_peri = z.cod_peri
                                            AND z.ind_camp_act = 1
                                            AND z.sta_camp = 0
                                         --and exists (select 1 from tmp_ped where ind_oc=1)
                                         )
                    AND (a.val_punt_emis IS NULL OR a.val_punt_emis <> 666)))
              
              OR
              a.clie_oid_clie IN
              (SELECT DISTINCT a.clie_oid_clie
                  FROM ped_solic_cabec     a,
                       ped_tipo_solic_pais c,
                       ped_tipo_solic      d
                 WHERE a.ind_oc = 1
                   AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
                   AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
                   AND nvl(d.ind_soli_nega, 0) = 0
                   AND a.grpr_oid_grup_proc IN (3, 4)
                   AND a.perd_oid_peri = (SELECT x.oid_peri
                                            FROM cra_perio       x,
                                                 seg_perio_corpo y,
                                                 bas_ctrl_fact   z
                                           WHERE x.peri_oid_peri = y.oid_peri
                                             AND y.cod_peri = z.cod_peri
                                             AND z.ind_camp_act = 1
                                             AND z.sta_camp = 0
                                          --and exists (select 1 from tmp_ped where ind_oc=1)
                                          ))));
  
    INSERT INTO tmp_ped
      (SELECT oid_soli_cabe,
              tspa_oid_tipo_soli_pais,
              clie_oid_clie,
              zzon_oid_zona,
              ind_oc,
              a.almc_oid_alma
         FROM ped_solic_cabec a --, ped_tipo_solic_pais c, ped_tipo_solic d
        WHERE a.perd_oid_peri = (SELECT x.oid_peri
                                   FROM cra_perio       x,
                                        seg_perio_corpo y,
                                        bas_ctrl_fact   z
                                  WHERE x.peri_oid_peri = y.oid_peri
                                    AND y.cod_peri = z.cod_peri
                                    AND z.ind_camp_act = 1
                                    AND z.sta_camp = 0)
          AND a.grpr_oid_grup_proc IN (3)
          AND a.tspa_oid_tipo_soli_pais IN
              (SELECT a.oid_tipo_soli_pais --, c.VAL_I18N
                 FROM ped_tipo_solic_pais a,
                      ped_tipo_solic      b,
                      gen_i18n_sicc_comun c
                WHERE a.tsol_oid_tipo_soli = b.oid_tipo_soli
                  AND b.oid_tipo_soli = c.val_oid
                  AND c.attr_enti = 'PED_TIPO_SOLIC'
                  AND (upper(c.val_i18n) LIKE '%XPRES%' OR
                      upper(c.val_i18n) LIKE '%NMP%' OR
                      (upper(c.val_i18n) LIKE '%MAV%' AND
                      a.fec_prog_fact <
                      to_date('01/01/2099', 'dd/MM/yyyy')))
                  AND b.ind_cons = 0
                  AND b.ind_soli_nega = 0)
          AND a.fec_repo_falt IS NULL
          AND a.ind_oc = 0
          AND a.modu_oid_modu <> 23
          AND a.oid_soli_cabe NOT IN (SELECT oid_soli_cabe FROM tmp_ped));

    --- valida si solo procesa MAV
    if lsindprocmav = 'S' then  
        -- Revisa si hay solicitudes MAV
        select count(0) into lnnroprocmav 
        from tmp_ped where tspa_oid_tipo_soli_pais in(
             SELECT a.oid_tipo_soli_pais 
             FROM ped_tipo_solic_pais a, ped_tipo_solic      b, gen_i18n_sicc_comun c
             WHERE a.tsol_oid_tipo_soli = b.oid_tipo_soli
              AND b.oid_tipo_soli = c.val_oid
              AND c.attr_enti = 'PED_TIPO_SOLIC'
              AND upper(c.val_i18n) LIKE '%MAV%');
        -- Si hay alguna solicitud MAV      
        if lnnroprocmav > 0 then  
           -- Borra del temporal todo lo que no es MAV
           delete tmp_ped where tspa_oid_tipo_soli_pais not in(
           SELECT a.oid_tipo_soli_pais 
           FROM ped_tipo_solic_pais a, ped_tipo_solic      b, gen_i18n_sicc_comun c
           WHERE a.tsol_oid_tipo_soli = b.oid_tipo_soli
            AND b.oid_tipo_soli = c.val_oid
            AND c.attr_enti = 'PED_TIPO_SOLIC'
            AND upper(c.val_i18n) LIKE '%MAV%');    

        end if;

    end if;
    
    /* UPDATE ped_solic_posic
      SET num_unid_por_aten = num_unid_dema_real, ind_ctrl_stoc=null, ind_ctrl_liqu=null
    WHERE soca_oid_soli_cabe IN
          (SELECT a.oid_soli_cabe
             FROM tmp_ped tmp, ped_solic_cabec a
            WHERE a.oid_soli_cabe = tmp.oid_soli_cabe);*/
  
    -- Se actualiza el control de stock
  
    OPEN c_cstock;
    LOOP
      FETCH c_cstock BULK COLLECT
        INTO cstockrecord LIMIT w_filas;
      IF cstockrecord.count > 0 THEN
        FOR x IN cstockrecord.first .. cstockrecord.last
        LOOP
        
          varoidsoliposi     := cstockrecord(x).varoidsoliposi;
          varcontrolunidades := cstockrecord(x).varcontrolunidades;
          varcontrolporc     := cstockrecord(x).varcontrolporc;
          varunidporaten     := cstockrecord(x).varunidporaten;
        
          IF varcontrolporc IS NOT NULL THEN
            tmp_unidades := round(varunidporaten * varcontrolporc / 100);
          ELSE
            tmp_unidades := varcontrolunidades;
          END IF;
        
          IF tmp_unidades < varunidporaten THEN
            UPDATE ped_solic_posic
               SET num_unid_por_aten = tmp_unidades,
                   ind_ctrl_stoc     = 1
             WHERE oid_soli_posi = varoidsoliposi;
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_cstock%NOTFOUND;
    END LOOP;
    CLOSE c_cstock;
  
    -- Se actualiza el control de liquidacion
  
    OPEN c_liq;
    LOOP
      FETCH c_liq BULK COLLECT
        INTO cliqrecord LIMIT w_filas;
      IF cliqrecord.count > 0 THEN
        FOR x IN cliqrecord.first .. cliqrecord.last
        LOOP
        
          varoidsoliposi := cliqrecord(x).varoidsoliposi;
        
          UPDATE ped_solic_posic
             SET ind_ctrl_liqu = 1
           WHERE oid_soli_posi = varoidsoliposi;
        
        END LOOP;
      END IF;
      EXIT WHEN c_liq%NOTFOUND;
    END LOOP;
    CLOSE c_liq;
  
    --Se actualiza inicialmente las unidades comprometidas com las unidades por atender real de todas las posiciones
    UPDATE ped_solic_posic
       SET num_unid_compr = num_unid_por_aten
     WHERE soca_oid_soli_cabe IN
           (SELECT a.oid_soli_cabe
              FROM tmp_ped         tmp,
                   ped_solic_cabec a
             WHERE tmp.oid_soli_cabe = a.oid_soli_cabe);
  
    --Se actualiza inicialmente las unidades comprometidas a cero de las posiciones anuladas
  
    UPDATE ped_solic_posic
       SET num_unid_compr = 0
     WHERE espo_oid_esta_posi = 2
       AND soca_oid_soli_cabe IN
           (SELECT a.oid_soli_cabe
              FROM tmp_ped         tmp,
                   ped_solic_cabec a
             WHERE tmp.oid_soli_cabe = a.oid_soli_cabe);
  
    --Se actualiza las unidades comprometidas a cero para los productos que no tienen stock
  
    UPDATE ped_solic_posic
       SET num_unid_compr = 0
     WHERE soca_oid_soli_cabe IN
           (SELECT a.oid_soli_cabe
              FROM tmp_ped         tmp,
                   ped_solic_cabec a
             WHERE tmp.oid_soli_cabe = a.oid_soli_cabe)
       AND (prod_oid_prod IN
           (SELECT prod_oid_prod
               FROM bel_stock
              WHERE esme_oid_esta_merc = 2001
                AND val_sald = 0
                AND almc_oid_alma =
                    nvl(ped_solic_posic.almc_oid_almc,
                        (SELECT almc_oid_alma
                           FROM ped_solic_cabec
                          WHERE oid_soli_cabe =
                                ped_solic_posic.soca_oid_soli_cabe))) OR
           NOT EXISTS
            (SELECT prod_oid_prod
               FROM bel_stock
              WHERE esme_oid_esta_merc = 2001
                AND prod_oid_prod = ped_solic_posic.prod_oid_prod
                AND almc_oid_alma =
                    nvl(ped_solic_posic.almc_oid_almc,
                        (SELECT almc_oid_alma
                           FROM ped_solic_cabec
                          WHERE oid_soli_cabe =
                                ped_solic_posic.soca_oid_soli_cabe))));
  
    -- Se actualiza el stock en los casos de discrepancias
  
    OPEN c_stock;
    LOOP
      FETCH c_stock BULK COLLECT
        INTO stockrecord LIMIT w_filas;
      IF stockrecord.count > 0 THEN
        FOR x IN stockrecord.first .. stockrecord.last
        LOOP
        
          varprodoidprod := stockrecord(x).varprodoidprod;
          varalmcoidalma := stockrecord(x).varalmcoidalma;
          vardiferencia  := stockrecord(x).vardif;
        
          --TMP_UNIDADES:=0;
        
          OPEN c_prod(varprodoidprod, varalmcoidalma);
          LOOP
            FETCH c_prod BULK COLLECT
              INTO prodrecord LIMIT w_filas;
            IF prodrecord.count > 0 THEN
              FOR y IN prodrecord.first .. prodrecord.last
              LOOP
              
                varoidsoliposi := prodrecord(y).varoidsoliposi;
                varunidcomp    := prodrecord(y).varunidcomp;
                varcodclie     := prodrecord(y).varcodclie;
                varorden       := prodrecord(y).varorden;
              
                tmp_unidades := 0;
                IF vardiferencia > 0 THEN
                  IF vardiferencia >= varunidcomp THEN
                    tmp_unidades := 0;
                  ELSE
                    tmp_unidades := varunidcomp - vardiferencia;
                  END IF;
                  UPDATE ped_solic_posic
                     SET num_unid_compr = tmp_unidades
                   WHERE oid_soli_posi = varoidsoliposi;
                
                  IF vardiferencia >= varunidcomp THEN
                    vardiferencia := vardiferencia - varunidcomp;
                  ELSE
                    vardiferencia := 0;
                  END IF;
                ELSE
                  EXIT;
                END IF;
              
              END LOOP;
            END IF;
            EXIT WHEN c_prod%NOTFOUND;
          END LOOP;
          CLOSE c_prod;
        
        END LOOP;
      END IF;
      EXIT WHEN c_stock%NOTFOUND;
    END LOOP;
    CLOSE c_stock;
  
    --Se actualiza el stock
    OPEN c_prod2;
    LOOP
      FETCH c_prod2 BULK COLLECT
        INTO prod2record LIMIT w_filas;
      IF prod2record.count > 0 THEN
        FOR y IN prod2record.first .. prod2record.last
        LOOP
        
          varprodoidprod := prod2record(y).varprodoidprod;
          varalmcoidalma := prod2record(y).varalmcoidalma;
          varunidcomp    := prod2record(y).varunidcomp;
        
          UPDATE bel_stock
             SET val_sald = val_sald - varunidcomp
           WHERE esme_oid_esta_merc = 2001
             AND prod_oid_prod = varprodoidprod
             AND almc_oid_alma = varalmcoidalma;
        
        END LOOP;
      END IF;
      EXIT WHEN c_prod2%NOTFOUND;
    END LOOP;
    CLOSE c_prod2;
  
    -- Se buscan ofertas alternativas para los productos con faltante
  
    OPEN c_falta;
    LOOP
      FETCH c_falta BULK COLLECT
        INTO faltarecord LIMIT w_filas;
      IF faltarecord.count > 0 THEN
        FOR x IN faltarecord.first .. faltarecord.last
        LOOP
        
          varprodoidprod := faltarecord(x).varprodoidprod;
          varoidsoliposi := faltarecord(x).varoidsoliposi;
          varunidporaten := faltarecord(x).varunidporaten;
          varalmcoidalma := faltarecord(x).varalmcoidalma;
        
          OPEN c_alter(varprodoidprod, varalmcoidalma, varunidporaten);
          LOOP
            FETCH c_alter BULK COLLECT
              INTO alterrecord LIMIT w_filas;
            IF alterrecord.count > 0 THEN
              FOR y IN alterrecord.first .. alterrecord.last
              LOOP
              
                varoiddetaoferalter := alterrecord(y).varoiddetaoferalter;
                varoidprodalter     := alterrecord(y).varoidprodalter;
                varsaldoalter       := alterrecord(y).varsaldoalter;
                varcodiventalter    := alterrecord(y).varcodiventalter;
              
                IF varsaldoalter >= varunidporaten THEN
                
                  INSERT INTO ped_solic_posic
                    (oid_soli_posi,
                     cod_posi,
                     val_lote_prod,
                     num_unid_dema,
                     num_unid_por_aten,
                     val_tasa_impu,
                     soca_oid_soli_cabe,
                     taim_oid_tasa_impu,
                     tpos_oid_tipo_posi,
                     prod_oid_prod,
                     fopa_oid_form_pago,
                     ind_limi_vent,
                     ind_ctrl_stoc,
                     ind_ctrl_liqu,
                     val_prec_cata_unit_loca,
                     val_prec_cont_unit_loca,
                     val_prec_cata_unit_docu,
                     val_prec_conta_unit_docu,
                     val_prec_fact_unit_loca,
                     val_prec_fact_unit_docu,
                     val_prec_sin_impu_unit_loca,
                     val_prec_sin_impu_unit_docu,
                     val_prec_sin_impu_tota_docu,
                     val_impo_desc_unit_loca,
                     val_impo_desc_unit_docu,
                     val_prec_neto_unit_loca,
                     val_prec_neto_tota_docu,
                     val_prec_neto_unit_docu,
                     val_prec_tota_tota_loca,
                     val_prec_tota_tota_docu,
                     val_impo_impu_unit_loca,
                     val_impo_impu_unit_docu,
                     val_impo_desc_tota_docu,
                     val_impo_impu_tota_loca,
                     val_impo_impu_tota_docu,
                     val_impo_desc_tota_loca,
                     val_prec_tota_unit_loca,
                     val_prec_tota_unit_docu,
                     val_prec_cont_tota_loca,
                     val_prec_cata_tota_loca,
                     val_prec_cata_tota_docu,
                     val_prec_cont_tota_docu,
                     val_porc_desc,
                     val_prec_cata_tota_loca_unid,
                     num_unid_dema_real,
                     num_unid_compr,
                     num_unid_camb,
                     num_unid_vent,
                     num_unid_aten,
                     val_codi_vent_fict,
                     val_prec_fact_tota_loca,
                     val_prec_fact_tota_docu,
                     val_prec_sin_impu_tota_loca,
                     val_prec_neto_tota_loca,
                     ofde_oid_deta_ofer,
                     espo_oid_esta_posi,
                     stpo_oid_subt_posi,
                     ind_recu_obli,
                     val_codi_vent,
                     sopo_oid_soli_posi,
                     ind_no_impr,
                     ind_dent_fuer_caja_bols,
                     val_cata,
                     num_pagi_cata,
                     num_cons,
                     num_docu_cont_inte,
                     val_ejer_docu_cont_inte,
                     val_impo_des_sin_imp_unit_loca,
                     val_impo_des_sin_imp_unit_docu,
                     val_impo_des_sin_imp_tota,
                     val_impo_des_sin_imp_tota_docu,
                     val_obse,
                     baca_oid_cabe,
                     eslo_oid_esln)
                    (SELECT ped_sopo_seq.nextval,
                            (SELECT MAX(cod_posi)
                               FROM ped_solic_posic
                              WHERE soca_oid_soli_cabe =
                                    b.soca_oid_soli_cabe) + rownum,
                            b.val_lote_prod,
                            b.num_unid_dema,
                            b.num_unid_por_aten,
                            b.val_tasa_impu,
                            b.soca_oid_soli_cabe,
                            b.taim_oid_tasa_impu,
                            5,
                            varoidprodalter,
                            b.fopa_oid_form_pago,
                            b.ind_limi_vent,
                            b.ind_ctrl_stoc,
                            b.ind_ctrl_liqu,
                            b.val_prec_cata_unit_loca,
                            b.val_prec_cont_unit_loca,
                            b.val_prec_cata_unit_docu,
                            b.val_prec_conta_unit_docu,
                            b.val_prec_fact_unit_loca,
                            b.val_prec_fact_unit_docu,
                            b.val_prec_sin_impu_unit_loca,
                            b.val_prec_sin_impu_unit_docu,
                            b.val_prec_sin_impu_tota_docu,
                            b.val_impo_desc_unit_loca,
                            b.val_impo_desc_unit_docu,
                            b.val_prec_neto_unit_loca,
                            b.val_prec_neto_tota_docu,
                            b.val_prec_neto_unit_docu,
                            b.val_prec_tota_tota_loca,
                            b.val_prec_tota_tota_docu,
                            b.val_impo_impu_unit_loca,
                            b.val_impo_impu_unit_docu,
                            b.val_impo_desc_tota_docu,
                            b.val_impo_impu_tota_loca,
                            b.val_impo_impu_tota_docu,
                            b.val_impo_desc_tota_loca,
                            b.val_prec_tota_unit_loca,
                            b.val_prec_tota_unit_docu,
                            b.val_prec_cont_tota_loca,
                            b.val_prec_cata_tota_loca,
                            b.val_prec_cata_tota_docu,
                            b.val_prec_cont_tota_docu,
                            b.val_porc_desc,
                            b.val_prec_cata_tota_loca_unid,
                            num_unid_dema_real,
                            num_unid_por_aten,
                            b.num_unid_camb,
                            b.num_unid_vent,
                            b.num_unid_aten,
                            b.val_codi_vent_fict,
                            b.val_prec_fact_tota_loca,
                            b.val_prec_fact_tota_docu,
                            b.val_prec_sin_impu_tota_loca,
                            b.val_prec_neto_tota_loca,
                            varoiddetaoferalter,
                            b.espo_oid_esta_posi,
                            7,
                            b.ind_recu_obli,
                            varcodiventalter,
                            b.sopo_oid_soli_posi,
                            b.ind_no_impr,
                            b.ind_dent_fuer_caja_bols,
                            b.val_cata,
                            b.num_pagi_cata,
                            b.num_cons,
                            b.num_docu_cont_inte,
                            b.val_ejer_docu_cont_inte,
                            b.val_impo_des_sin_imp_unit_loca,
                            b.val_impo_des_sin_imp_unit_docu,
                            b.val_impo_des_sin_imp_tota,
                            b.val_impo_des_sin_imp_tota_docu,
                            b.val_obse,
                            b.baca_oid_cabe,
                            b.eslo_oid_esln
                       FROM ped_solic_posic b
                      WHERE b.oid_soli_posi = varoidsoliposi);
                
                  UPDATE ped_solic_posic
                     SET num_unid_compr = 0,
                         --num_unid_por_aten  = 0,
                         --num_unid_dema_real = 0,
                         espo_oid_esta_posi = 2,
                         val_obse           = 'ANULADO POR ALTERNATIVO'
                  --STPO_OID_SUBT_POSI=2028
                   WHERE oid_soli_posi = varoidsoliposi;
                
                  UPDATE bel_stock
                     SET val_sald = val_sald - varunidporaten
                   WHERE esme_oid_esta_merc = 2001
                     AND prod_oid_prod = varoidprodalter
                     AND almc_oid_alma = varalmcoidalma;
                
                END IF;
              
              END LOOP;
            END IF;
            EXIT WHEN c_alter%NOTFOUND;
          END LOOP;
          CLOSE c_alter;
        
        END LOOP;
      END IF;
      EXIT WHEN c_falta%NOTFOUND;
    END LOOP;
    CLOSE c_falta;
  
    -- Se buscan ofertas alternativas para los premios con faltante
  
    OPEN c_falta_prem;
    LOOP
      FETCH c_falta_prem BULK COLLECT
        INTO faltapremrecord LIMIT w_filas;
      IF faltapremrecord.count > 0 THEN
        FOR x IN faltapremrecord.first .. faltapremrecord.last
        LOOP
        
          varcodventfict := faltapremrecord(x).varcodventfict;
          varcopa        := faltapremrecord(x).varcopa;
          varalmcoidalma := faltapremrecord(x).varalmcoidalma;
          varunidporaten := faltapremrecord(x).varunidporaten;
          varoidsoliposi := faltapremrecord(x).varoidsoliposi;
          varoidclie     := faltapremrecord(x).varoidclie;
        
          OPEN c_alter_prem(varcodventfict,
                            varcopa,
                            varalmcoidalma,
                            varunidporaten,
                            varoidclie);
          LOOP
            FETCH c_alter_prem BULK COLLECT
              INTO alterpremrecord LIMIT w_filas;
            IF alterpremrecord.count > 0 THEN
              FOR y IN alterpremrecord.first .. alterpremrecord.last
              LOOP
              
                varunid          := alterpremrecord(y).varunid;
                varoidprodalter  := alterpremrecord(y).varoidprodalter;
                varsaldoalter    := alterpremrecord(y).varsaldoalter;
                varcodiventalter := alterpremrecord(y).varcodiventalter;
              
                IF varsaldoalter >= varunidporaten THEN
                
                  INSERT INTO ped_solic_posic
                    (oid_soli_posi,
                     cod_posi,
                     val_lote_prod,
                     num_unid_dema,
                     num_unid_por_aten,
                     val_tasa_impu,
                     soca_oid_soli_cabe,
                     taim_oid_tasa_impu,
                     tpos_oid_tipo_posi,
                     prod_oid_prod,
                     fopa_oid_form_pago,
                     ind_limi_vent,
                     ind_ctrl_stoc,
                     ind_ctrl_liqu,
                     val_prec_cata_unit_loca,
                     val_prec_cont_unit_loca,
                     val_prec_cata_unit_docu,
                     val_prec_conta_unit_docu,
                     val_prec_fact_unit_loca,
                     val_prec_fact_unit_docu,
                     val_prec_sin_impu_unit_loca,
                     val_prec_sin_impu_unit_docu,
                     val_prec_sin_impu_tota_docu,
                     val_impo_desc_unit_loca,
                     val_impo_desc_unit_docu,
                     val_prec_neto_unit_loca,
                     val_prec_neto_tota_docu,
                     val_prec_neto_unit_docu,
                     val_prec_tota_tota_loca,
                     val_prec_tota_tota_docu,
                     val_impo_impu_unit_loca,
                     val_impo_impu_unit_docu,
                     val_impo_desc_tota_docu,
                     val_impo_impu_tota_loca,
                     val_impo_impu_tota_docu,
                     val_impo_desc_tota_loca,
                     val_prec_tota_unit_loca,
                     val_prec_tota_unit_docu,
                     val_prec_cont_tota_loca,
                     val_prec_cata_tota_loca,
                     val_prec_cata_tota_docu,
                     val_prec_cont_tota_docu,
                     val_porc_desc,
                     val_prec_cata_tota_loca_unid,
                     num_unid_dema_real,
                     num_unid_compr,
                     num_unid_camb,
                     num_unid_vent,
                     num_unid_aten,
                     val_codi_vent,
                     val_prec_fact_tota_loca,
                     val_prec_fact_tota_docu,
                     val_prec_sin_impu_tota_loca,
                     val_prec_neto_tota_loca,
                     ofde_oid_deta_ofer,
                     espo_oid_esta_posi,
                     stpo_oid_subt_posi,
                     ind_recu_obli,
                     val_codi_vent_fict,
                     sopo_oid_soli_posi,
                     ind_no_impr,
                     ind_dent_fuer_caja_bols,
                     val_cata,
                     num_pagi_cata,
                     num_cons,
                     num_docu_cont_inte,
                     val_ejer_docu_cont_inte,
                     val_impo_des_sin_imp_unit_loca,
                     val_impo_des_sin_imp_unit_docu,
                     val_impo_des_sin_imp_tota,
                     val_impo_des_sin_imp_tota_docu,
                     val_obse,
                     baca_oid_cabe,
                     eslo_oid_esln)
                    (SELECT ped_sopo_seq.nextval,
                            (SELECT MAX(cod_posi)
                               FROM ped_solic_posic
                              WHERE soca_oid_soli_cabe =
                                    b.soca_oid_soli_cabe) + rownum,
                            b.val_lote_prod,
                            b.num_unid_dema,
                            b.num_unid_por_aten,
                            b.val_tasa_impu,
                            b.soca_oid_soli_cabe,
                            b.taim_oid_tasa_impu,
                            5,
                            varoidprodalter,
                            b.fopa_oid_form_pago,
                            b.ind_limi_vent,
                            b.ind_ctrl_stoc,
                            b.ind_ctrl_liqu,
                            b.val_prec_cata_unit_loca,
                            b.val_prec_cont_unit_loca,
                            b.val_prec_cata_unit_docu,
                            b.val_prec_conta_unit_docu,
                            b.val_prec_fact_unit_loca,
                            b.val_prec_fact_unit_docu,
                            b.val_prec_sin_impu_unit_loca,
                            b.val_prec_sin_impu_unit_docu,
                            b.val_prec_sin_impu_tota_docu,
                            b.val_impo_desc_unit_loca,
                            b.val_impo_desc_unit_docu,
                            b.val_prec_neto_unit_loca,
                            b.val_prec_neto_tota_docu,
                            b.val_prec_neto_unit_docu,
                            b.val_prec_tota_tota_loca,
                            b.val_prec_tota_tota_docu,
                            b.val_impo_impu_unit_loca,
                            b.val_impo_impu_unit_docu,
                            b.val_impo_desc_tota_docu,
                            b.val_impo_impu_tota_loca,
                            b.val_impo_impu_tota_docu,
                            b.val_impo_desc_tota_loca,
                            b.val_prec_tota_unit_loca,
                            b.val_prec_tota_unit_docu,
                            b.val_prec_cont_tota_loca,
                            b.val_prec_cata_tota_loca,
                            b.val_prec_cata_tota_docu,
                            b.val_prec_cont_tota_docu,
                            b.val_porc_desc,
                            b.val_prec_cata_tota_loca_unid,
                            num_unid_dema_real,
                            num_unid_por_aten,
                            b.num_unid_camb,
                            b.num_unid_vent,
                            b.num_unid_aten,
                            b.val_codi_vent,
                            b.val_prec_fact_tota_loca,
                            b.val_prec_fact_tota_docu,
                            b.val_prec_sin_impu_tota_loca,
                            b.val_prec_neto_tota_loca,
                            b.ofde_oid_deta_ofer,
                            b.espo_oid_esta_posi,
                            7,
                            b.ind_recu_obli,
                            varcodiventalter,
                            b.sopo_oid_soli_posi,
                            b.ind_no_impr,
                            b.ind_dent_fuer_caja_bols,
                            b.val_cata,
                            b.num_pagi_cata,
                            b.num_cons,
                            b.num_docu_cont_inte,
                            b.val_ejer_docu_cont_inte,
                            b.val_impo_des_sin_imp_unit_loca,
                            b.val_impo_des_sin_imp_unit_docu,
                            b.val_impo_des_sin_imp_tota,
                            b.val_impo_des_sin_imp_tota_docu,
                            b.val_obse,
                            b.baca_oid_cabe,
                            b.eslo_oid_esln
                       FROM ped_solic_posic b
                      WHERE b.oid_soli_posi = varoidsoliposi);
                
                  UPDATE ped_solic_posic
                     SET num_unid_compr = 0,
                         --num_unid_por_aten  = 0,
                         num_unid_dema_real = 0,
                         espo_oid_esta_posi = 2,
                         val_obse           = 'ANULADO POR PREMIO ALTERNATIVO'
                  --STPO_OID_SUBT_POSI=2028
                   WHERE oid_soli_posi = varoidsoliposi;
                
                  UPDATE bel_stock
                     SET val_sald = val_sald - varunidporaten
                   WHERE esme_oid_esta_merc = 2001
                     AND prod_oid_prod = varoidprodalter
                     AND almc_oid_alma = varalmcoidalma;
                
                END IF;
              
              END LOOP;
            END IF;
            EXIT WHEN c_alter_prem%NOTFOUND;
          END LOOP;
          CLOSE c_alter_prem;
        
        END LOOP;
      END IF;
      EXIT WHEN c_falta_prem%NOTFOUND;
    END LOOP;
    CLOSE c_falta_prem;
  
    -- Se actualizan los pedidos procesados
  
    UPDATE ped_solic_cabec a
       SET a.fec_repo_falt =
           (SELECT fec_proc
              FROM bas_ctrl_fact
             WHERE sta_camp = '0'
               AND ind_camp_act = '1')
     WHERE oid_soli_cabe IN
           (SELECT a.oid_soli_cabe
              FROM tmp_ped         tmp,
                   ped_solic_cabec a
             WHERE a.oid_soli_cabe = tmp.oid_soli_cabe);
  
    UPDATE ped_solic_cabec a
       SET a.grpr_oid_grup_proc = 4
     WHERE oid_soli_cabe IN (SELECT a.oid_soli_cabe
                               FROM tmp_ped         tmp,
                                    ped_solic_cabec a
                              WHERE a.oid_soli_cabe = tmp.oid_soli_cabe
                                AND a.ind_oc = 0);
  
    -- Se actualizan los pedidos con unidades atendidas solo de folleteria
  
    UPDATE ped_solic_posic
       SET num_unid_compr     = 0,
           num_unid_dema_real = 0
     WHERE num_unid_compr > 0
       AND soca_oid_soli_cabe IN
           (SELECT oid_soli_cabe
              FROM ped_solic_cabec
             WHERE perd_oid_peri = (SELECT x.oid_peri
                                      FROM cra_perio       x,
                                           seg_perio_corpo y,
                                           bas_ctrl_fact   z
                                     WHERE x.peri_oid_peri = y.oid_peri
                                       AND y.cod_peri = z.cod_peri
                                       AND z.ind_camp_act = 1
                                       AND z.sta_camp = 0)
               AND fec_prog_fact = (SELECT z.fec_proc
                                      FROM bas_ctrl_fact z
                                     WHERE z.ind_camp_act = 1
                                       AND z.sta_camp = 0)
               AND grpr_oid_grup_proc = 3
               AND ind_ts_no_conso = 1
               AND NOT EXISTS
             (SELECT 1
                      FROM ped_solic_posic a,
                           pre_ofert_detal b
                     WHERE a.num_unid_compr > 0
                       AND a.soca_oid_soli_cabe = oid_soli_cabe
                       AND a.ofde_oid_deta_ofer = b.oid_deta_ofer
                       AND b.tofe_oid_tipo_ofer NOT IN
                           (SELECT tofe_oid_tipo_ofer
                              FROM fac_tipo_ofert_exclu))
               AND EXISTS
             (SELECT 1
                      FROM ped_solic_posic a,
                           pre_ofert_detal b
                     WHERE a.num_unid_compr > 0
                       AND a.soca_oid_soli_cabe = oid_soli_cabe
                       AND a.ofde_oid_deta_ofer = b.oid_deta_ofer
                       AND b.tofe_oid_tipo_ofer IN
                           (SELECT tofe_oid_tipo_ofer
                              FROM fac_tipo_ofert_exclu)));
  
    -- Se actualizan los pedidos con unidades atendidas a cero para pedidos que no cumplen con el monto minimo para facturar
  
    IF lnmonto IS NOT NULL THEN
    
      lnmonto := lnmonto / 100;
    
      UPDATE ped_solic_cabec a
         SET a.val_base_flet_loca =
             (SELECT SUM(b.num_unid_compr * b.val_prec_cata_unit_loca)
                FROM ped_solic_posic b
               WHERE b.soca_oid_soli_cabe = a.oid_soli_cabe
                 AND b.espo_oid_esta_posi <> 2),
             a.val_base_flet_docu =
             (SELECT SUM(b.num_unid_compr * b.val_prec_cata_unit_loca)
                FROM ped_solic_posic b
               WHERE b.soca_oid_soli_cabe = a.oid_soli_cabe
                 AND b.espo_oid_esta_posi <> 2)
       WHERE a.oid_soli_cabe IN
             (SELECT oid_soli_cabe
                FROM ped_solic_cabec     a,
                     ped_tipo_solic_pais b
               WHERE b.ind_suje_flet = 1
                 AND a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
                 AND a.perd_oid_peri =
                     (SELECT x.oid_peri
                        FROM cra_perio       x,
                             seg_perio_corpo y,
                             bas_ctrl_fact   z
                       WHERE x.peri_oid_peri = y.oid_peri
                         AND y.cod_peri = z.cod_peri
                         AND z.ind_camp_act = 1
                         AND z.sta_camp = 0)
                 AND a.grpr_oid_grup_proc IN (3, 4));
    
      /* UPDATE ped_solic_cabec x
      SET x.grpr_oid_grup_proc = 3,
          x.fec_repo_falt = null
      WHERE x.oid_soli_cabe IN (SELECT a.oid_soli_cabe
                      FROM tmp_ped         tmp,
                           ped_solic_cabec a
                     WHERE a.oid_soli_cabe = tmp.oid_soli_cabe
                       AND a.ind_oc = 1)
            and x.zzon_oid_zona not in
                (
                SELECT DISTINCT zzon_oid_zona
                  FROM fac_contr_cierr
                 WHERE zzon_oid_zona IS NOT NULL
                   AND perd_oid_peri = (SELECT x.oid_peri
                                          FROM cra_perio       x,
                                               seg_perio_corpo y,
                                               bas_ctrl_fact   z
                                         WHERE x.peri_oid_peri = y.oid_peri
                                           AND y.cod_peri = z.cod_peri
                                           AND z.ind_camp_act = 1
                                           AND z.sta_camp = 0
                                        )
                  )
               and x.val_base_flet_loca<=lnmonto
                  ;*/
    
      OPEN c_reversto(lnmonto);
      LOOP
        FETCH c_reversto
          INTO r_reversto;
        EXIT WHEN c_reversto%NOTFOUND;
        BEGIN
        
          IF ped_fn_devue_apro_gp2(r_reversto.oid_soli_cabe,
                                   sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                                        'STO_VAL_MMIN_FAC'),
                                   lscodpais) = 0 THEN
            ped_pkg_cuadr_ofert.ped_pr_rever_pedid_sto(lscodpais,
                                                       r_reversto.oid_soli_cabe,
                                                       'STO_VAL_MMIN_FAC',
                                                       r_reversto.val_base_flet_loca,
                                                       '4');
          
            UPDATE ped_solic_cabec a
               SET a.grpr_oid_grup_proc = 3,
                   fec_repo_falt        = NULL
             WHERE a.oid_soli_cabe IN
                   (SELECT y.oid_soli_cabe
                      FROM tmp_ped y
                     WHERE y.clie_oid_clie = r_reversto.clie_oid_clie
                       AND y.ind_oc = 0);
          
          END IF;
        
        END;
      END LOOP;
      CLOSE c_reversto;
    
    END IF;
  
    -- Se actualizan los pedidos con faltante de expofertas
  
    IF lslimventauto = 'S' THEN
    
      BEGIN
      
        INSERT INTO ped_gesti_stock
          (oid_gest_stoc,
           val_limi_ctrl_vent,
           perd_oid_peri,
           ind_ctrl_liqu,
           ind_ulti_noti,
           cod_gest_stoc,
           ofde_oid_deta_ofer,
           ticl_oid_tipo_clie,
           ind_acti,
           usu_crea,
           fec_crea,
           ind_csap)
          SELECT ped_gest_seq.nextval,
                 0,
                 lnoidperi,
                 0,
                 1,
                 ped_gest_seq.currval,
                 x.ofde_oid_deta_ofer,
                 2,
                 '1',
                 'SISTEMA',
                 SYSDATE,
                 '0'
            FROM (SELECT DISTINCT ofde_oid_deta_ofer
                    FROM ped_solic_posic
                   WHERE num_unid_dema_real - num_unid_compr > 0
                     AND ofde_oid_deta_ofer IN
                         (SELECT x.oid_deta_ofer
                            FROM pre_ofert_detal x,
                                 pre_tipo_ofert  y
                           WHERE x.tofe_oid_tipo_ofer = y.oid_tipo_ofer
                             AND ((y.oid_tipo_ofer IN
                                 (2020, 2021, 2022, 2045) AND
                                 lstipolimiauto = '1') OR
                                 (x.precio_unitario > 0 AND
                                 lstipolimiauto = '2')))
                     AND soca_oid_soli_cabe IN
                         (SELECT oid_soli_cabe
                            FROM ped_solic_cabec
                           WHERE perd_oid_peri = lnoidperi
                             AND fec_prog_fact =
                                 (SELECT z.fec_proc
                                    FROM bas_ctrl_fact z
                                   WHERE z.ind_camp_act = 1
                                     AND z.sta_camp = 0)
                             AND grpr_oid_grup_proc = 3
                             AND ind_ts_no_conso = 1)) x
           WHERE NOT EXISTS
           (SELECT 1
                    FROM ped_gesti_stock
                   WHERE ofde_oid_deta_ofer = x.ofde_oid_deta_ofer
                     AND val_limi_ctrl_vent = 0);
      
      EXCEPTION
        WHEN OTHERS THEN
          NULL;
      END;
    
      UPDATE ped_solic_posic
         SET num_unid_dema_real = num_unid_compr,
             ind_limi_vent      = 1
       WHERE num_unid_dema_real - num_unid_compr > 0
         AND ofde_oid_deta_ofer IN
             (SELECT x.oid_deta_ofer
                FROM pre_ofert_detal x,
                     pre_tipo_ofert  y
               WHERE x.tofe_oid_tipo_ofer = y.oid_tipo_ofer
                 AND ((y.oid_tipo_ofer IN (2020, 2021, 2022, 2045) AND
                     lstipolimiauto = '1') OR
                     (x.precio_unitario > 0 AND lstipolimiauto = '2')))
         AND soca_oid_soli_cabe IN
             (SELECT oid_soli_cabe
                FROM ped_solic_cabec
               WHERE perd_oid_peri = lnoidperi
                 AND fec_prog_fact = (SELECT z.fec_proc
                                        FROM bas_ctrl_fact z
                                       WHERE z.ind_camp_act = 1
                                         AND z.sta_camp = 0)
                 AND grpr_oid_grup_proc = 3
                 AND ind_ts_no_conso = 1);
    
    END IF;
  
    IF lsporcdesv IS NOT NULL THEN
      imp_pr_proce_desvi_pedid_gp3(lscodpais,
                                   lsnumpedprom,
                                   lsporcdesv,
                                   to_char(lsfecha, 'dd/mm/yyyy'));
    END IF;
  
    ped_pr_descu_cabec(lscodpais,
                       lscodperi,
                       'SISTEMA',
                       lnoidperi,
                       to_char(lsfecha, 'dd/mm/yyyy'));
  
    IF ln_tiponavi = '1' THEN
      ped_pr_ofert_navid2(lscodpais,
                          lscodperi,
                          to_char(lsfecha, 'dd/mm/yyyy'));
    ELSE
      ped_pr_ofert_navid4(lscodpais,
                          lscodperi,
                          to_char(lsfecha, 'dd/mm/yyyy'));
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'ERROR ped_pr_stock: ' || ls_sqlerrm);
  END ped_pr_stock;

  /***************************************************************************
  Descripcion       : Stock
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_stock_online(p_oidsolicabe IN NUMBER) IS
  
    w_filas        NUMBER(12);
    tmp_unidades   NUMBER(12);
    varoidsoliposi NUMBER(12);
    varunidcomp    NUMBER(12);
    varorden       NUMBER(12);
    varcodclie     VARCHAR2(12);
  
    varperdoidperi NUMBER(12);
    varclieoidclie NUMBER(12);
  
    varprodoidprod NUMBER(12);
    varalmcoidalma NUMBER(12);
    vardiferencia  NUMBER(12);
  
    varcontrolunidades NUMBER(12);
    varcontrolporc     NUMBER(12);
    varunidporaten     NUMBER(12);
  
    varoiddetaoferalter NUMBER(12);
    varoidprodalter     NUMBER(12);
    varsaldoalter       NUMBER(12);
    varcodiventalter    VARCHAR2(12);
  
    varindol VARCHAR(10);
  
    lsalmacenprol VARCHAR2(10);
  
    lscodpais VARCHAR2(10);
  
    CURSOR c_stock IS
      SELECT b.prod_oid_prod,
             a.almc_oid_alma,
             b.oid_soli_posi,
             b.num_unid_por_aten comp,
             val_codi_vent
        FROM --tmp_ped         tmp,
             ped_solic_cabec a,
             ped_solic_posic b
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND a.oid_soli_cabe = p_oidsolicabe
         AND b.espo_oid_esta_posi <> 2
         AND NOT EXISTS
       (SELECT 1
                FROM ped_produ_alter x
               WHERE x.clie_oid_clie = a.clie_oid_clie
                 AND x.perd_oid_peri = a.perd_oid_peri
                 AND x.cod_vent_orig = b.val_codi_vent);
  
    TYPE stockrec IS RECORD(
      varprodoidprod NUMBER(12),
      varalmcoidalma NUMBER(12),
      varoidsoliposi NUMBER(12),
      varunidcomp    NUMBER(12),
      varcodigoventa ped_solic_posic.val_codi_vent%TYPE);
  
    TYPE stockrectab IS TABLE OF stockrec;
    stockrecord stockrectab;
  
    CURSOR c_cstock IS
    
      SELECT DISTINCT b.oid_soli_posi,
                      d.val_unid,
                      d.val_porc,
                      b.num_unid_por_aten
        FROM --tmp_ped              tmp,
             ped_solic_cabec      a,
             ped_solic_posic      b,
             ped_secue_proce      c,
             ped_gesti_stock      d,
             zon_zona             e,
             zon_regio            f,
             mae_clien_tipo_subti g,
             mae_clien_clasi      h
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND a.oid_soli_cabe = p_oidsolicabe
            /*and a.perd_oid_peri =
            (SELECT x.oid_peri
               FROM cra_perio x, seg_perio_corpo y, bas_ctrl_fact z
              WHERE x.peri_oid_peri = y.oid_peri
                AND y.cod_peri = z.cod_peri
                AND z.ind_camp_act = 1
                AND z.sta_camp = 0)  */
         AND d.perd_oid_peri = (SELECT x.oid_peri
                                  FROM cra_perio       x,
                                       seg_perio_corpo y,
                                       bas_ctrl_fact   z
                                 WHERE x.peri_oid_peri = y.oid_peri
                                   AND y.cod_peri = z.cod_peri
                                   AND z.ind_camp_act = 1
                                   AND z.sta_camp = 0)
            --AND a.oid_soli_cabe = tmp.oid_soli_cabe
         AND a.tspa_oid_tipo_soli_pais = c.tspa_oid_tipo_soli_pais
         AND c.proc_oid_proc = 51
         AND b.ofde_oid_deta_ofer = d.ofde_oid_deta_ofer
         AND (d.val_unid IS NOT NULL OR d.val_porc IS NOT NULL)
         AND a.zzon_oid_zona = e.oid_zona
         AND e.zorg_oid_regi = f.oid_regi
         AND a.zzon_oid_zona =
             decode(d.zzon_oid_zona, NULL, a.zzon_oid_zona, d.zzon_oid_zona)
         AND f.oid_regi =
             decode(d.zorg_oid_regi, NULL, f.oid_regi, d.zorg_oid_regi)
         AND a.clie_oid_clie = g.clie_oid_clie
         AND b.espo_oid_esta_posi <> 2
         AND g.oid_clie_tipo_subt = h.ctsu_oid_clie_tipo_subt
         AND g.ticl_oid_tipo_clie =
             decode(d.ticl_oid_tipo_clie,
                    NULL,
                    g.ticl_oid_tipo_clie,
                    d.ticl_oid_tipo_clie)
         AND g.sbti_oid_subt_clie =
             decode(d.sbti_oid_subt_clie,
                    NULL,
                    g.sbti_oid_subt_clie,
                    d.sbti_oid_subt_clie)
         AND h.tccl_oid_tipo_clasi =
             decode(d.tccl_oid_tipo_clas,
                    NULL,
                    h.tccl_oid_tipo_clasi,
                    d.tccl_oid_tipo_clas)
         AND h.clas_oid_clas =
             decode(d.clas_oid_clas, NULL, h.clas_oid_clas, d.clas_oid_clas)
         AND b.espo_oid_esta_posi <> 2
         AND nvl(b.val_lote_prod, '0') = '0'
         AND a.ind_oc = 1;
  
    TYPE cstockrec IS RECORD(
      varoidsoliposi     NUMBER(12),
      varcontrolunidades NUMBER(12),
      varcontrolporc     NUMBER(12),
      varunidporaten     NUMBER(12));
  
    TYPE cstockrectab IS TABLE OF cstockrec;
    cstockrecord cstockrectab;
  
  BEGIN
    w_filas := 100000;
  
    SELECT cod_pais
      INTO lscodpais
      FROM seg_pais
     WHERE oid_pais = (SELECT pais_oid_pais
                         FROM ped_solic_cabec
                        WHERE oid_soli_cabe = p_oidsolicabe);
  
    lsalmacenprol := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                          'STO_ALMAC_PROL');
  
    SELECT nvl(a.ind_rece_onli, 0)
      INTO varindol
      FROM ped_solic_cabec a
     WHERE oid_soli_cabe = p_oidsolicabe;
  
    IF varindol <> 1 THEN
      RETURN;
    END IF;
  
    -- Se actualiza el control de stock
  
    OPEN c_cstock;
    LOOP
      FETCH c_cstock BULK COLLECT
        INTO cstockrecord LIMIT w_filas;
      IF cstockrecord.count > 0 THEN
        FOR x IN cstockrecord.first .. cstockrecord.last
        LOOP
        
          varoidsoliposi     := cstockrecord(x).varoidsoliposi;
          varcontrolunidades := cstockrecord(x).varcontrolunidades;
          varcontrolporc     := cstockrecord(x).varcontrolporc;
          varunidporaten     := cstockrecord(x).varunidporaten;
        
          IF varcontrolporc IS NOT NULL THEN
            tmp_unidades := round(varunidporaten * varcontrolporc / 100);
          ELSE
            tmp_unidades := varcontrolunidades;
          END IF;
        
          IF tmp_unidades < varunidporaten THEN
            UPDATE ped_solic_posic
               SET num_unid_por_aten = tmp_unidades,
                   ind_ctrl_stoc     = 1
             WHERE oid_soli_posi = varoidsoliposi;
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_cstock%NOTFOUND;
    END LOOP;
    CLOSE c_cstock;
  
    --Se actualiza inicialmente las unidades comprometidas com las unidades por atender real de todas las posiciones
    UPDATE ped_solic_posic
       SET num_unid_compr = num_unid_por_aten
     WHERE soca_oid_soli_cabe = p_oidsolicabe;
  
    --Se actualiza inicialmente las unidades comprometidas a cero de los productos con alternativo
    UPDATE ped_solic_posic
       SET num_unid_compr = 0
     WHERE soca_oid_soli_cabe = p_oidsolicabe
       AND EXISTS (SELECT 1
              FROM ped_produ_alter x
             WHERE x.clie_oid_clie =
                   (SELECT clie_oid_clie
                      FROM ped_solic_cabec
                     WHERE oid_soli_cabe = p_oidsolicabe)
               AND x.perd_oid_peri =
                   (SELECT perd_oid_peri
                      FROM ped_solic_cabec
                     WHERE oid_soli_cabe = p_oidsolicabe)
               AND x.cod_vent_orig = val_codi_vent);
  
    --Se crean las posiciones de los productos con alternativo
    INSERT INTO ped_solic_posic
      (oid_soli_posi,
       cod_posi,
       num_unid_dema,
       num_unid_por_aten,
       val_tasa_impu,
       soca_oid_soli_cabe,
       taim_oid_tasa_impu,
       tpos_oid_tipo_posi,
       prod_oid_prod,
       fopa_oid_form_pago,
       val_prec_cata_unit_loca,
       val_prec_cont_unit_loca,
       val_prec_cata_unit_docu,
       val_prec_conta_unit_docu,
       val_prec_fact_unit_loca,
       val_prec_fact_unit_docu,
       val_prec_sin_impu_unit_loca,
       val_prec_sin_impu_unit_docu,
       val_prec_sin_impu_tota_docu,
       val_impo_desc_unit_loca,
       val_impo_desc_unit_docu,
       val_prec_neto_unit_loca,
       val_prec_neto_tota_docu,
       val_prec_neto_unit_docu,
       val_prec_tota_tota_loca,
       val_prec_tota_tota_docu,
       val_impo_impu_unit_loca,
       val_impo_impu_unit_docu,
       val_impo_desc_tota_docu,
       val_impo_impu_tota_loca,
       val_impo_impu_tota_docu,
       val_impo_desc_tota_loca,
       val_prec_tota_unit_loca,
       val_prec_tota_unit_docu,
       val_prec_cont_tota_loca,
       val_prec_cata_tota_loca,
       val_prec_cata_tota_docu,
       val_prec_cont_tota_docu,
       val_porc_desc,
       val_prec_cata_tota_loca_unid,
       num_unid_dema_real,
       num_unid_compr,
       val_prec_fact_tota_loca,
       val_prec_fact_tota_docu,
       val_prec_sin_impu_tota_loca,
       val_prec_neto_tota_loca,
       ofde_oid_deta_ofer,
       espo_oid_esta_posi,
       stpo_oid_subt_posi,
       val_codi_vent,
       ind_no_impr,
       sopo_oid_soli_posi)
      SELECT ped_sopo_seq.nextval,
             (SELECT MAX(cod_posi)
                FROM ped_solic_posic
               WHERE soca_oid_soli_cabe = a.oid_soli_cabe) + rownum,
             b.num_unid_dema_real,
             b.num_unid_dema_real,
             0,
             a.oid_soli_cabe,
             NULL,
             5,
             g.prod_oid_prod,
             g.fopa_oid_form_pago,
             g.imp_prec_cata / g.val_fact_repe,
             decode(g.imp_prec_cata, 0, g.imp_prec_posi, 0),
             g.imp_prec_cata / g.val_fact_repe,
             decode(g.imp_prec_cata, 0, g.imp_prec_posi, 0),
             0,
             0,
             0,
             0,
             0,
             0,
             NULL,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             NULL,
             0,
             b.num_unid_dema_real,
             b.num_unid_dema_real,
             0,
             0,
             0,
             0,
             g.oid_deta_ofer,
             4,
             7,
             g.val_codi_vent,
             0,
             b.oid_soli_posi
        FROM ped_solic_cabec       a,
             ped_solic_posic       b,
             ped_produ_alter       c,
             cra_perio             d,
             pre_matri_factu_cabec e,
             pre_ofert             f,
             pre_ofert_detal       g
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND b.val_codi_vent = c.cod_vent_orig
         AND c.perd_oid_peri = d.oid_peri
         AND d.oid_peri = e.perd_oid_peri
         AND e.oid_cabe = f.mfca_oid_cabe
         AND f.oid_ofer = g.ofer_oid_ofer
         AND g.val_codi_vent = c.cod_vent_alte
         AND a.oid_soli_cabe = p_oidsolicabe
         AND a.clie_oid_clie = c.clie_oid_clie;
  
    --Se actualiza inicialmente las unidades comprometidas a cero de las posiciones anuladas
  
    UPDATE ped_solic_posic
       SET num_unid_compr    = 0,
           num_unid_por_aten = 0
     WHERE espo_oid_esta_posi = 2
       AND soca_oid_soli_cabe = p_oidsolicabe;
  
    --Se actualiza las unidades comprometidas a cero para los productos que no tienen stock
    /*
    UPDATE ped_solic_posic
       SET num_unid_compr    = 0,
           num_unid_por_aten = 0
     WHERE soca_oid_soli_cabe = p_oidsolicabe
       AND (prod_oid_prod IN
           (SELECT prod_oid_prod
               FROM bel_stock
              WHERE esme_oid_esta_merc = 2001
                AND val_sald = 0
                AND almc_oid_alma =
                    (SELECT almc_oid_alma
                       FROM ped_solic_cabec
                      WHERE oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe)) OR NOT EXISTS
            (SELECT prod_oid_prod
               FROM bel_stock
              WHERE esme_oid_esta_merc = 2001
                AND prod_oid_prod = ped_solic_posic.prod_oid_prod
                AND almc_oid_alma =
                    (SELECT almc_oid_alma
                       FROM ped_solic_cabec
                      WHERE oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe)));
    */
    -- Se actualiza el stock en los casos de discrepancias
  
    OPEN c_stock;
    LOOP
      FETCH c_stock BULK COLLECT
        INTO stockrecord LIMIT w_filas;
      IF stockrecord.count > 0 THEN
        FOR x IN stockrecord.first .. stockrecord.last
        LOOP
        
          varprodoidprod := stockrecord(x).varprodoidprod;
          varalmcoidalma := stockrecord(x).varalmcoidalma;
          varoidsoliposi := stockrecord(x).varoidsoliposi;
          varunidcomp    := stockrecord(x).varunidcomp;
        
          SELECT perd_oid_peri
            INTO varperdoidperi
            FROM ped_solic_cabec
           WHERE oid_soli_cabe = p_oidsolicabe;
          SELECT clie_oid_clie
            INTO varclieoidclie
            FROM ped_solic_cabec
           WHERE oid_soli_cabe = p_oidsolicabe;
        
          tmp_unidades := ped_fn_resrv_stock(p_oidsolicabe,
                                             varperdoidperi,
                                             varclieoidclie,
                                             varprodoidprod,
                                             varalmcoidalma,
                                             varunidcomp,
                                             stockrecord(x).varcodigoventa);
        
          UPDATE ped_solic_posic
             SET num_unid_por_aten = tmp_unidades,
                 num_unid_compr    = tmp_unidades
           WHERE oid_soli_posi = varoidsoliposi;
        
        END LOOP;
      END IF;
      EXIT WHEN c_stock%NOTFOUND;
    END LOOP;
    CLOSE c_stock;
  
    ped_pr_retor_stock_resrv_nasig(p_oidsolicabe,
                                   varperdoidperi,
                                   varclieoidclie);
    /*
    --Se actualiza el stock
    OPEN c_prod2;
    LOOP
      FETCH c_prod2 BULK COLLECT
        INTO prod2record LIMIT w_filas;
      IF prod2record.count > 0 THEN
        FOR y IN prod2record.first .. prod2record.last
        LOOP
    
          varprodoidprod := prod2record(y).varprodoidprod;
          varalmcoidalma := prod2record(y).varalmcoidalma;
          varunidcomp    := prod2record(y).varunidcomp;
    
          UPDATE bel_stock
             SET val_sald = val_sald - varunidcomp
           WHERE esme_oid_esta_merc = 2001
             AND prod_oid_prod = varprodoidprod
             AND almc_oid_alma = varalmcoidalma;
    
        END LOOP;
      END IF;
      EXIT WHEN c_prod2%NOTFOUND;
    END LOOP;
    CLOSE c_prod2;
    
    -- Se actualizan los pedidos procesados
    
    
      UPDATE ped_solic_posic b SET b.val_lote_prod = '1' WHERE soca_oid_soli_cabe = p_oidsolicabe;
    */
    /*UPDATE ped_solic_cabec a
           SET a.grpr_oid_grup_proc = 4
         WHERE oid_soli_cabe IN (SELECT a.oid_soli_cabe
                                   FROM tmp_ped tmp, ped_solic_cabec a
                                  WHERE a.oid_soli_cabe = tmp.oid_soli_cabe
                                    AND a.ind_oc = 0);
    */
  
    UPDATE ped_solic_cabec a
       SET a.fec_repo_falt =
           (SELECT fec_proc
              FROM bas_ctrl_fact
             WHERE sta_camp = '0'
               AND ind_camp_act = '1')
     WHERE oid_soli_cabe = p_oidsolicabe;
  
    IF lsalmacenprol IS NOT NULL THEN
      UPDATE ped_solic_cabec a
         SET a.fec_repo_falt = NULL,
             a.almc_oid_alma =
             (SELECT oid_alma FROM bel_almac WHERE cod_alma = lsalmacenprol)
       WHERE oid_soli_cabe = p_oidsolicabe;
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_stock_online: ' || ls_sqlerrm);
  END ped_pr_stock_online;

  /***************************************************************************
  Descripcion       : Stock
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_stock_online_1(p_oidsolicabe IN NUMBER) IS
  
    w_filas        NUMBER(12);
    tmp_unidades   NUMBER(12);
    varoidsoliposi NUMBER(12);
    varunidcomp    NUMBER(12);
    varorden       NUMBER(12);
    varcodclie     VARCHAR2(12);
  
    varperdoidperi NUMBER(12);
    varclieoidclie NUMBER(12);
  
    varprodoidprod NUMBER(12);
    varalmcoidalma NUMBER(12);
    vardiferencia  NUMBER(12);
  
    varcontrolunidades NUMBER(12);
    varcontrolporc     NUMBER(12);
    varunidporaten     NUMBER(12);
  
    varoiddetaoferalter NUMBER(12);
    varoidprodalter     NUMBER(12);
    varsaldoalter       NUMBER(12);
    varcodiventalter    VARCHAR2(12);
  
    varindol VARCHAR(10);
  
    CURSOR c_stock IS
      SELECT b.prod_oid_prod,
             a.almc_oid_alma,
             b.oid_soli_posi,
             b.num_unid_por_aten comp,
             b.val_codi_vent
        FROM --tmp_ped         tmp,
             ped_solic_cabec a,
             ped_solic_posic b
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND a.oid_soli_cabe = p_oidsolicabe
         AND b.espo_oid_esta_posi <> 2
         AND NOT EXISTS
       (SELECT 1
                FROM ped_produ_alter x
               WHERE x.clie_oid_clie = a.clie_oid_clie
                 AND x.perd_oid_peri = a.perd_oid_peri
                 AND x.cod_vent_orig = b.val_codi_vent);
  
    TYPE stockrec IS RECORD(
      varprodoidprod NUMBER(12),
      varalmcoidalma NUMBER(12),
      varoidsoliposi NUMBER(12),
      varunidcomp    NUMBER(12),
      varcodigoventa ped_solic_posic.val_codi_vent%TYPE
      
      );
  
    TYPE stockrectab IS TABLE OF stockrec;
    stockrecord stockrectab;
  
    CURSOR c_cstock IS
    
      SELECT DISTINCT b.oid_soli_posi,
                      d.val_unid,
                      d.val_porc,
                      b.num_unid_por_aten
        FROM --tmp_ped              tmp,
             ped_solic_cabec      a,
             ped_solic_posic      b,
             ped_secue_proce      c,
             ped_gesti_stock      d,
             zon_zona             e,
             zon_regio            f,
             mae_clien_tipo_subti g,
             mae_clien_clasi      h
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND a.oid_soli_cabe = p_oidsolicabe
            /*and a.perd_oid_peri =
            (SELECT x.oid_peri
               FROM cra_perio x, seg_perio_corpo y, bas_ctrl_fact z
              WHERE x.peri_oid_peri = y.oid_peri
                AND y.cod_peri = z.cod_peri
                AND z.ind_camp_act = 1
                AND z.sta_camp = 0)  */
         AND d.perd_oid_peri = (SELECT x.oid_peri
                                  FROM cra_perio       x,
                                       seg_perio_corpo y,
                                       bas_ctrl_fact   z
                                 WHERE x.peri_oid_peri = y.oid_peri
                                   AND y.cod_peri = z.cod_peri
                                   AND z.ind_camp_act = 1
                                   AND z.sta_camp = 0)
            --AND a.oid_soli_cabe = tmp.oid_soli_cabe
         AND a.tspa_oid_tipo_soli_pais = c.tspa_oid_tipo_soli_pais
         AND c.proc_oid_proc = 51
         AND b.ofde_oid_deta_ofer = d.ofde_oid_deta_ofer
         AND (d.val_unid IS NOT NULL OR d.val_porc IS NOT NULL)
         AND a.zzon_oid_zona = e.oid_zona
         AND e.zorg_oid_regi = f.oid_regi
         AND a.zzon_oid_zona =
             decode(d.zzon_oid_zona, NULL, a.zzon_oid_zona, d.zzon_oid_zona)
         AND f.oid_regi =
             decode(d.zorg_oid_regi, NULL, f.oid_regi, d.zorg_oid_regi)
         AND a.clie_oid_clie = g.clie_oid_clie
         AND b.espo_oid_esta_posi <> 2
         AND g.oid_clie_tipo_subt = h.ctsu_oid_clie_tipo_subt
         AND g.ticl_oid_tipo_clie =
             decode(d.ticl_oid_tipo_clie,
                    NULL,
                    g.ticl_oid_tipo_clie,
                    d.ticl_oid_tipo_clie)
         AND g.sbti_oid_subt_clie =
             decode(d.sbti_oid_subt_clie,
                    NULL,
                    g.sbti_oid_subt_clie,
                    d.sbti_oid_subt_clie)
         AND h.tccl_oid_tipo_clasi =
             decode(d.tccl_oid_tipo_clas,
                    NULL,
                    h.tccl_oid_tipo_clasi,
                    d.tccl_oid_tipo_clas)
         AND h.clas_oid_clas =
             decode(d.clas_oid_clas, NULL, h.clas_oid_clas, d.clas_oid_clas)
         AND b.espo_oid_esta_posi <> 2
         AND nvl(b.val_lote_prod, '0') = '0'
         AND a.ind_oc = 1;
  
    TYPE cstockrec IS RECORD(
      varoidsoliposi     NUMBER(12),
      varcontrolunidades NUMBER(12),
      varcontrolporc     NUMBER(12),
      varunidporaten     NUMBER(12));
  
    TYPE cstockrectab IS TABLE OF cstockrec;
    cstockrecord cstockrectab;
  
  BEGIN
    w_filas := 100000;
  
    SELECT nvl(a.ind_rece_onli, 0)
      INTO varindol
      FROM ped_solic_cabec a
     WHERE oid_soli_cabe = p_oidsolicabe;
  
    IF varindol <> 1 THEN
      RETURN;
    END IF;
  
    -- Se actualiza el control de stock
  
    OPEN c_cstock;
    LOOP
      FETCH c_cstock BULK COLLECT
        INTO cstockrecord LIMIT w_filas;
      IF cstockrecord.count > 0 THEN
        FOR x IN cstockrecord.first .. cstockrecord.last
        LOOP
        
          varoidsoliposi     := cstockrecord(x).varoidsoliposi;
          varcontrolunidades := cstockrecord(x).varcontrolunidades;
          varcontrolporc     := cstockrecord(x).varcontrolporc;
          varunidporaten     := cstockrecord(x).varunidporaten;
        
          IF varcontrolporc IS NOT NULL THEN
            tmp_unidades := round(varunidporaten * varcontrolporc / 100);
          ELSE
            tmp_unidades := varcontrolunidades;
          END IF;
        
          IF tmp_unidades < varunidporaten THEN
            UPDATE ped_solic_posic
               SET num_unid_por_aten = tmp_unidades,
                   ind_ctrl_stoc     = 1
             WHERE oid_soli_posi = varoidsoliposi;
          END IF;
        
        END LOOP;
      END IF;
      EXIT WHEN c_cstock%NOTFOUND;
    END LOOP;
    CLOSE c_cstock;
  
    --Se actualiza inicialmente las unidades comprometidas com las unidades por atender real de todas las posiciones
    UPDATE ped_solic_posic
       SET num_unid_compr = 0
     WHERE soca_oid_soli_cabe = p_oidsolicabe;
  
    --Se actualiza inicialmente las unidades comprometidas a cero de las posiciones anuladas
  
    UPDATE ped_solic_posic
       SET num_unid_por_aten = 0
     WHERE espo_oid_esta_posi = 2
       AND soca_oid_soli_cabe = p_oidsolicabe;
  
    --Se actualiza las unidades comprometidas a cero para los productos que no tienen stock
  
    /*UPDATE ped_solic_posic
         SET num_unid_por_aten = 0
       WHERE soca_oid_soli_cabe = p_oidsolicabe
         AND (prod_oid_prod IN
             (SELECT prod_oid_prod
                 FROM bel_stock
                WHERE esme_oid_esta_merc = 2001
                  AND val_sald = 0
                  AND almc_oid_alma =
                      (SELECT almc_oid_alma
                         FROM ped_solic_cabec
                        WHERE oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe)) OR NOT EXISTS
              (SELECT prod_oid_prod
                 FROM bel_stock
                WHERE esme_oid_esta_merc = 2001
                  AND prod_oid_prod = ped_solic_posic.prod_oid_prod
                  AND almc_oid_alma =
                      (SELECT almc_oid_alma
                         FROM ped_solic_cabec
                        WHERE oid_soli_cabe = ped_solic_posic.soca_oid_soli_cabe)));
    */
    -- Se actualiza el stock en los casos de discrepancias
  
    OPEN c_stock;
    LOOP
      FETCH c_stock BULK COLLECT
        INTO stockrecord LIMIT w_filas;
      IF stockrecord.count > 0 THEN
        FOR x IN stockrecord.first .. stockrecord.last
        LOOP
        
          varprodoidprod := stockrecord(x).varprodoidprod;
          varalmcoidalma := stockrecord(x).varalmcoidalma;
          varoidsoliposi := stockrecord(x).varoidsoliposi;
          varunidcomp    := stockrecord(x).varunidcomp;
        
          SELECT perd_oid_peri
            INTO varperdoidperi
            FROM ped_solic_cabec
           WHERE oid_soli_cabe = p_oidsolicabe;
          SELECT clie_oid_clie
            INTO varclieoidclie
            FROM ped_solic_cabec
           WHERE oid_soli_cabe = p_oidsolicabe;
        
          tmp_unidades := ped_fn_resrv_stock(p_oidsolicabe,
                                             varperdoidperi,
                                             varclieoidclie,
                                             varprodoidprod,
                                             varalmcoidalma,
                                             varunidcomp,
                                             stockrecord(x).varcodigoventa);
        
          UPDATE ped_solic_posic
             SET num_unid_por_aten = tmp_unidades
           WHERE oid_soli_posi = varoidsoliposi;
        
        END LOOP;
      END IF;
      EXIT WHEN c_stock%NOTFOUND;
    END LOOP;
    CLOSE c_stock;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_stock_online_1: ' || ls_sqlerrm);
  END ped_pr_stock_online_1;

  /***************************************************************************
  Descripcion       : Flete
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_flete IS
  
    lscodpais        VARCHAR2(15);
    lsparaactuadirec VARCHAR2(15);
    lsformapago      NUMBER(12);
    lsmontoformapago NUMBER(12, 2);
    lscuvformapago   VARCHAR2(15);
    lsparageneopor   VARCHAR2(15);
    lscalcufletdesc  VARCHAR2(1);
    lsfletebuzon     NUMBER(10);
    lscalcufletunid  VARCHAR2(1);
    lsactuacronobr   VARCHAR2(1);
    lscodigoperiodo  VARCHAR2(6);
    lscodalma        VARCHAR2(10);
    lsactindcaja     VARCHAR2(1);
  
    lndecim NUMBER(3);
    lntasa  NUMBER(3);
  
    ls_codperi VARCHAR2(6);
    ln_oidperi NUMBER(12);
  
    ls_fecha VARCHAR2(15);
  
  BEGIN
  
    SELECT a.cod_peri,
           b.oid_peri,
           to_char(a.fec_proc, 'dd/mm/yyyy')
      INTO ls_codperi,
           ln_oidperi,
           ls_fecha
      FROM bas_ctrl_fact   a,
           cra_perio       b,
           seg_perio_corpo c
     WHERE a.cod_peri = c.cod_peri
       AND c.oid_peri = b.peri_oid_peri
       AND a.sta_camp = 0
       AND a.ind_camp_act = 1;
  
    SELECT DISTINCT cod_pais INTO lscodpais FROM bas_ctrl_fact;
  
    lscalcufletdesc := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                            'STO_CALCU_FLETE_DESC');
  
    lscalcufletdesc := nvl(lscalcufletdesc, 'N');
  
    lscalcufletunid := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                                'STO_CALCU_FLETE_UNID'),
                           'N');
  
    lsfletebuzon := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                             'STO_FLETE_BUZON'),
                        0);
  
    ped_pr_descu_cabec2(lscodpais,
                        ls_codperi,
                        'SISTEMA',
                        ln_oidperi,
                        ls_fecha);
  
    -- Se actualizan los precios para quitarle el impuesto a las zonas francas
  
    SELECT y.num_deci
      INTO lndecim
      FROM seg_pais  x,
           seg_moned y
     WHERE x.mone_oid_mone = y.oid_mone
       AND x.cod_pais = lscodpais;
  
    SELECT MAX(y.val_tasa_impu)
      INTO lntasa
      FROM ped_impue_gener x,
           ped_tasa_impue  y,
           seg_pais        z
     WHERE x.taim_oid_tasa_impu = y.oid_tasa_impu
       AND x.sbac_oid_sbac = 888
       AND x.pais_oid_pais = z.oid_pais
          --and y.val_tasa_impu<>0
       AND z.cod_pais = lscodpais;
  
    UPDATE ped_solic_posic a
       SET a.val_prec_cata_unit_loca = round(a.val_prec_cata_unit_loca /
                                             (1 + (lntasa / 100)),
                                             lndecim)
     WHERE a.oid_soli_posi IN
           (SELECT b1.oid_soli_posi
              FROM ped_solic_cabec       a1,
                   ped_solic_posic       b1,
                   zon_zona              c1,
                   fac_tipos_impue_ubige d1,
                   ped_tasa_impue        e1,
                   ped_tipo_solic_pais   f1,
                   ped_tipo_solic        g1
             WHERE a1.oid_soli_cabe = b1.soca_oid_soli_cabe
               AND a1.zzon_oid_zona = c1.oid_zona
               AND c1.oid_zona = d1.vepo_oid_valo_estr_geop
               AND d1.taim_oid_tasa_impu = e1.oid_tasa_impu
               AND e1.val_tasa_impu = 0
               AND a1.tspa_oid_tipo_soli_pais = f1.oid_tipo_soli_pais
               AND f1.tsol_oid_tipo_soli = g1.oid_tipo_soli
               AND g1.ind_soli_nega = 0
               AND a1.perd_oid_peri =
                   (SELECT x.oid_peri
                      FROM cra_perio       x,
                           seg_perio_corpo y,
                           bas_ctrl_fact   z
                     WHERE x.peri_oid_peri = y.oid_peri
                       AND y.cod_peri = z.cod_peri
                       AND z.ind_camp_act = 1
                       AND z.sta_camp = 0)
               AND a1.grpr_oid_grup_proc IN (3, 4));
  
    -- Se calcula el flete
  
    UPDATE ped_solic_cabec a
       SET a.val_base_flet_loca =
           (SELECT SUM(decode(lscalcufletunid,
                              'N',
                              b.num_unid_compr,
                              b.num_unid_por_aten) *
                       (nvl(b.val_prec_cata_unit_loca, 0) -
                        decode(lscalcufletdesc,
                               'N',
                               nvl(b.val_impo_desc_unit_loca, 0),
                               0)))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = a.oid_soli_cabe
               AND b.espo_oid_esta_posi <> 2)
     WHERE a.oid_soli_cabe IN
           (SELECT oid_soli_cabe
              FROM ped_solic_cabec     a,
                   ped_tipo_solic_pais b
             WHERE b.ind_suje_flet = 1
               AND a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
               AND a.perd_oid_peri =
                   (SELECT x.oid_peri
                      FROM cra_perio       x,
                           seg_perio_corpo y,
                           bas_ctrl_fact   z
                     WHERE x.peri_oid_peri = y.oid_peri
                       AND y.cod_peri = z.cod_peri
                       AND z.ind_camp_act = 1
                       AND z.sta_camp = 0)
               AND a.grpr_oid_grup_proc IN (3, 4));
  
    UPDATE ped_solic_cabec a
       SET a.val_impo_flet_loca = nvl((SELECT MAX(DISTINCT CASE
                                                   WHEN decode(fl.val_mont_fijo,
                                                               0,
                                                               nvl((SELECT MIN(valo_mont_fijo)
                                                                     FROM ped_detal_flete
                                                                    WHERE flet_oid_flet =
                                                                          fl.oid_flete
                                                                      AND a.val_base_flet_loca >=
                                                                          rango_infe
                                                                      AND a.val_base_flet_loca <=
                                                                          rang_supe),
                                                                   0),
                                                               fl.val_mont_fijo) +
                                                        (fl.val_tasa / 100 *
                                                         a.val_base_flet_loca) >=
                                                        fl.val_flet_maxi THEN
                                                    fl.val_flet_maxi
                                                   WHEN decode(fl.val_mont_fijo,
                                                               0,
                                                               nvl((SELECT MIN(valo_mont_fijo)
                                                                     FROM ped_detal_flete
                                                                    WHERE flet_oid_flet =
                                                                          fl.oid_flete
                                                                      AND a.val_base_flet_loca >=
                                                                          rango_infe
                                                                      AND a.val_base_flet_loca <=
                                                                          rang_supe),
                                                                   0),
                                                               fl.val_mont_fijo) +
                                                        (fl.val_tasa / 100 *
                                                         a.val_base_flet_loca) <=
                                                        fl.val_flet_mini THEN
                                                    fl.val_flet_mini
                                                   ELSE
                                                    decode(fl.val_mont_fijo,
                                                           0,
                                                           nvl((SELECT MIN(valo_mont_fijo)
                                                                 FROM ped_detal_flete
                                                                WHERE flet_oid_flet =
                                                                      fl.oid_flete
                                                                  AND a.val_base_flet_loca >=
                                                                      rango_infe
                                                                  AND a.val_base_flet_loca <=
                                                                      rang_supe),
                                                               0),
                                                           fl.val_mont_fijo) +
                                                    (fl.val_tasa / 100 *
                                                     a.val_base_flet_loca)
                                                 END)
                                        FROM ped_flete            fl,
                                             mae_clien_tipo_subti mcts,
                                             mae_clien_clasi      mcc
                                       WHERE decode(fl.zzon_zona,
                                                    NULL,
                                                    a.zzon_oid_zona,
                                                    fl.zzon_zona) =
                                             a.zzon_oid_zona
                                         AND decode(fl.ticl_oid_tipo_clie,
                                                    NULL,
                                                    a.ticl_oid_tipo_clie,
                                                    fl.ticl_oid_tipo_clie) =
                                             a.ticl_oid_tipo_clie
                                         AND decode(fl.sbti_oid_subt_clie,
                                                    NULL,
                                                    a.sbti_oid_subt_clie,
                                                    fl.sbti_oid_subt_clie) =
                                             a.sbti_oid_subt_clie
                                         AND mcts.clie_oid_clie =
                                             a.clie_oid_clie
                                         AND mcts.oid_clie_tipo_subt =
                                             mcc.ctsu_oid_clie_tipo_subt(+)
                                         AND decode(fl.tccl_oid_tipo_clas,
                                                    NULL,
                                                    mcc.tccl_oid_tipo_clasi,
                                                    fl.tccl_oid_tipo_clas) =
                                             mcc.tccl_oid_tipo_clasi
                                         AND decode(fl.clas_oid_clas,
                                                    NULL,
                                                    mcc.clas_oid_clas,
                                                    fl.clas_oid_clas) =
                                             mcc.clas_oid_clas),
                                      0),
           a.val_base_flet_docu = a.val_base_flet_loca
     WHERE a.oid_soli_cabe IN
           (SELECT oid_soli_cabe
              FROM ped_solic_cabec     a,
                   ped_tipo_solic_pais b
             WHERE b.ind_suje_flet = 1
               AND a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
               AND a.perd_oid_peri =
                   (SELECT x.oid_peri
                      FROM cra_perio       x,
                           seg_perio_corpo y,
                           bas_ctrl_fact   z
                     WHERE x.peri_oid_peri = y.oid_peri
                       AND y.cod_peri = z.cod_peri
                       AND z.ind_camp_act = 1
                       AND z.sta_camp = 0)
               AND a.grpr_oid_grup_proc IN (3, 4));
  
    /* Flete por territorio Colombia*/
    UPDATE ped_solic_cabec a
       SET a.val_impo_flet_loca = lsfletebuzon --(select val_flet from sto_acopi_cober d, zon_terri c where d.cod_terr=c.cod_terr and c.oid_terr=a.terr_oid_terr and rownum=1)
     WHERE a.oid_soli_cabe IN
           (SELECT oid_soli_cabe
              FROM ped_solic_cabec     a,
                   ped_tipo_solic_pais b,
                   zon_terri           c,
                   sto_acopi_cober     d
             WHERE b.ind_suje_flet = 1
               AND a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
               AND a.terr_oid_terr = c.oid_terr
               AND c.cod_terr = d.cod_terr
               AND ltrim(d.cod_buzo) IS NOT NULL
               AND a.perd_oid_peri =
                   (SELECT x.oid_peri
                      FROM cra_perio       x,
                           seg_perio_corpo y,
                           bas_ctrl_fact   z
                     WHERE x.peri_oid_peri = y.oid_peri
                       AND y.cod_peri = z.cod_peri
                       AND z.ind_camp_act = 1
                       AND z.sta_camp = 0)
               AND a.grpr_oid_grup_proc IN (3, 4));
  
    /* Flete por territorio Colombia*/
  
    UPDATE ped_solic_cabec a
       SET a.val_impo_flet_docu      = a.val_impo_flet_loca,
           a.val_impo_flet_tota_loca = a.val_impo_flet_loca,
           a.val_impo_flet_tota_docu = a.val_impo_flet_loca,
           a.val_tota_flet_loca      = a.val_impo_flet_loca,
           a.val_tota_flet_docu      = a.val_impo_flet_loca
     WHERE a.oid_soli_cabe IN
           (SELECT oid_soli_cabe
              FROM ped_solic_cabec     a,
                   ped_tipo_solic_pais b
             WHERE b.ind_suje_flet = 1
               AND a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
               AND a.perd_oid_peri =
                   (SELECT x.oid_peri
                      FROM cra_perio       x,
                           seg_perio_corpo y,
                           bas_ctrl_fact   z
                     WHERE x.peri_oid_peri = y.oid_peri
                       AND y.cod_peri = z.cod_peri
                       AND z.ind_camp_act = 1
                       AND z.sta_camp = 0)
               AND a.grpr_oid_grup_proc IN (3, 4));
  
    UPDATE ped_solic_cabec a
       SET a.val_impo_flet_loca      = 0,
           a.val_impo_flet_docu      = 0,
           a.val_impo_flet_tota_loca = 0,
           a.val_impo_flet_tota_docu = 0,
           a.val_tota_flet_docu      = 0,
           a.val_tota_flet_loca      = 0
     WHERE a.oid_soli_cabe IN
           (SELECT oid_soli_cabe
              FROM ped_solic_cabec     a,
                   ped_tipo_solic_pais b
             WHERE b.ind_suje_flet = 1
               AND a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
               AND a.perd_oid_peri =
                   (SELECT x.oid_peri
                      FROM cra_perio       x,
                           seg_perio_corpo y,
                           bas_ctrl_fact   z
                     WHERE x.peri_oid_peri = y.oid_peri
                       AND y.cod_peri = z.cod_peri
                       AND z.ind_camp_act = 1
                       AND z.sta_camp = 0)
               AND a.grpr_oid_grup_proc IN (3, 4))
       AND a.clie_oid_clie IN
           (SELECT a.clie_oid_clie
              FROM (SELECT clie_oid_clie
                      FROM ped_solic_cabec     a,
                           ped_tipo_solic_pais b
                     WHERE b.ind_suje_flet = 1
                       AND a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
                       AND a.ind_oc = 1
                       AND a.perd_oid_peri =
                           (SELECT x.oid_peri
                              FROM cra_perio       x,
                                   seg_perio_corpo y,
                                   bas_ctrl_fact   z
                             WHERE x.peri_oid_peri = y.oid_peri
                               AND y.cod_peri = z.cod_peri
                               AND z.ind_camp_act = 1
                               AND z.sta_camp = 0)
                       AND a.grpr_oid_grup_proc IN (3, 4)) a,
                   mae_clien_tipo_subti b,
                   mae_clien_clasi c,
                   mae_exenc_flete d
             WHERE a.clie_oid_clie = b.clie_oid_clie
               AND b.oid_clie_tipo_subt = c.ctsu_oid_clie_tipo_subt
               AND d.ind_exen_flet = 1
               AND b.ticl_oid_tipo_clie =
                   decode(d.ticl_oid_tipo_clie,
                          NULL,
                          b.ticl_oid_tipo_clie,
                          d.ticl_oid_tipo_clie)
               AND b.sbti_oid_subt_clie =
                   decode(d.sbti_oid_subt_clie,
                          NULL,
                          b.sbti_oid_subt_clie,
                          d.sbti_oid_subt_clie)
               AND c.tccl_oid_tipo_clasi =
                   decode(d.tccl_oid_tipo_clas,
                          NULL,
                          c.tccl_oid_tipo_clasi,
                          d.tccl_oid_tipo_clas)
               AND c.clas_oid_clas =
                   decode(d.clas_oid_clas,
                          NULL,
                          c.clas_oid_clas,
                          d.clas_oid_clas));
  
    UPDATE ped_solic_cabec a
       SET a.val_impo_flet_loca      = 0,
           a.val_impo_flet_docu      = 0,
           a.val_impo_flet_tota_loca = 0,
           a.val_impo_flet_tota_docu = 0,
           a.val_tota_flet_docu      = 0,
           a.val_tota_flet_loca      = 0
     WHERE a.oid_soli_cabe IN
           (SELECT oid_soli_cabe
              FROM ped_solic_cabec     a,
                   ped_tipo_solic_pais b
             WHERE b.ind_suje_flet = 1
               AND a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
               AND a.perd_oid_peri =
                   (SELECT x.oid_peri
                      FROM cra_perio       x,
                           seg_perio_corpo y,
                           bas_ctrl_fact   z
                     WHERE x.peri_oid_peri = y.oid_peri
                       AND y.cod_peri = z.cod_peri
                       AND z.ind_camp_act = 1
                       AND z.sta_camp = 0)
               AND a.grpr_oid_grup_proc IN (3, 4))
       AND NOT EXISTS
     (SELECT 1
              FROM ped_solic_posic a
             WHERE a.num_unid_compr > 0
               AND a.soca_oid_soli_cabe = oid_soli_cabe);
  
    UPDATE ped_solic_cabec a
       SET a.val_reca_flet_loca = nvl((SELECT MAX(fl.val_cont_entr)
                                        FROM ped_flete            fl,
                                             mae_clien_tipo_subti mcts,
                                             mae_clien_clasi      mcc
                                       WHERE decode(fl.zzon_zona,
                                                    NULL,
                                                    a.zzon_oid_zona,
                                                    fl.zzon_zona) =
                                             a.zzon_oid_zona
                                         AND decode(fl.ticl_oid_tipo_clie,
                                                    NULL,
                                                    a.ticl_oid_tipo_clie,
                                                    fl.ticl_oid_tipo_clie) =
                                             a.ticl_oid_tipo_clie
                                         AND decode(fl.sbti_oid_subt_clie,
                                                    NULL,
                                                    a.sbti_oid_subt_clie,
                                                    fl.sbti_oid_subt_clie) =
                                             a.sbti_oid_subt_clie
                                         AND mcts.clie_oid_clie =
                                             a.clie_oid_clie
                                         AND mcts.oid_clie_tipo_subt =
                                             mcc.ctsu_oid_clie_tipo_subt(+)
                                         AND decode(fl.tccl_oid_tipo_clas,
                                                    NULL,
                                                    mcc.tccl_oid_tipo_clasi,
                                                    fl.tccl_oid_tipo_clas) =
                                             mcc.tccl_oid_tipo_clasi
                                         AND decode(fl.clas_oid_clas,
                                                    NULL,
                                                    mcc.clas_oid_clas,
                                                    fl.clas_oid_clas) =
                                             mcc.clas_oid_clas),
                                      0)
     WHERE a.val_reca_flet IS NOT NULL
       AND a.val_impo_flet_loca > 0
       AND a.perd_oid_peri = (SELECT x.oid_peri
                                FROM cra_perio       x,
                                     seg_perio_corpo y,
                                     bas_ctrl_fact   z
                               WHERE x.peri_oid_peri = y.oid_peri
                                 AND y.cod_peri = z.cod_peri
                                 AND z.ind_camp_act = 1
                                 AND z.sta_camp = 0)
       AND a.grpr_oid_grup_proc IN (3, 4)
       AND ind_oc = 1;
  
    UPDATE ped_solic_cabec a
       SET a.val_impo_flet_tota_loca = a.val_impo_flet_tota_loca +
                                       nvl(a.val_reca_flet_loca, 0),
           a.val_impo_flet_tota_docu = a.val_impo_flet_tota_docu +
                                       nvl(a.val_reca_flet_loca, 0),
           a.val_tota_flet_docu      = a.val_tota_flet_docu +
                                       nvl(a.val_reca_flet_loca, 0),
           a.val_tota_flet_loca      = a.val_tota_flet_loca +
                                       nvl(a.val_reca_flet_loca, 0)
     WHERE a.val_reca_flet IS NOT NULL
       AND a.val_impo_flet_loca >= 0
       AND a.perd_oid_peri = (SELECT x.oid_peri
                                FROM cra_perio       x,
                                     seg_perio_corpo y,
                                     bas_ctrl_fact   z
                               WHERE x.peri_oid_peri = y.oid_peri
                                 AND y.cod_peri = z.cod_peri
                                 AND z.ind_camp_act = 1
                                 AND z.sta_camp = 0)
       AND a.grpr_oid_grup_proc IN (3, 4)
       AND ind_oc = 1;
  
    -- Se actualiza la bolsa de faltantes
  
    INSERT INTO inc_bolsa_falta
      (oid_bols_falt,
       num_unid_falt,
       fec_alta,
       fec_solu,
       val_obse,
       sopo_oid_soli_posi,
       perd_oid_peri,
       cod_orig_falt,
       copa_oid_para_gene,
       val_codi_vent_fict,
       clie_oid_clie,
       num_prem,
       prod_oid_prod)
      SELECT inc_bofa_seq.nextval,
             num_unid_por_aten - num_unid_compr,
             SYSDATE,
             NULL,
             'XXX',
             oid_soli_posi,
             a.perd_oid_peri,
             CASE
               WHEN a.tspa_oid_tipo_soli_pais IN
                    (SELECT tsp.oid_tipo_soli_pais
                       FROM ped_tipo_solic_pais tsp,
                            ped_tipo_solic      ts
                      WHERE tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
                        AND cod_tipo_soli IN ('SIN', 'SINC')) THEN
                'FO'
               ELSE
                NULL
             END,
             a.copa_oid_para_gene,
             b.val_codi_vent_fict,
             a.clie_oid_clie,
             a.num_prem,
             b.prod_oid_prod
        FROM ped_solic_cabec a,
             ped_solic_posic b --, tmp_ped c
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
            --and a.oid_soli_cabe=c.oid_soli_cabe
         AND a.modu_oid_modu = 13
         AND num_unid_por_aten - num_unid_compr > 0
         AND b.espo_oid_esta_posi <> 2
         AND a.grpr_oid_grup_proc = 4
         AND NOT EXISTS
       (SELECT 1
                FROM inc_bolsa_falta
               WHERE sopo_oid_soli_posi = b.oid_soli_posi)
         AND a.fec_repo_falt = (SELECT z.fec_proc
                                  FROM bas_ctrl_fact z
                                 WHERE z.ind_camp_act = 1
                                   AND z.sta_camp = 0)
         AND a.perd_oid_peri = (SELECT x.oid_peri
                                  FROM cra_perio       x,
                                       seg_perio_corpo y,
                                       bas_ctrl_fact   z
                                 WHERE x.peri_oid_peri = y.oid_peri
                                   AND y.cod_peri = z.cod_peri
                                   AND z.ind_camp_act = 1
                                   AND z.sta_camp = 0);
  
    -- Generacion de Oportunidad de Ahorro
  
    lsparaactuadirec := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                             'STO_ACTUA_DIREC');
  
    lsparageneopor := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                           'STO_OPORT_AHORR');
  
    IF nvl(lsparaactuadirec, '') = 'S' THEN
      ped_pr_actua_direc_solic;
    END IF;
  
    IF nvl(lsparageneopor, '') = 'S' THEN
      UPDATE ped_solic_posic a
         SET a.val_prec_publ_unit_loca =
             (SELECT nvl(imp_prec_publ, 0)
                FROM pre_ofert_detal
               WHERE oid_deta_ofer = a.ofde_oid_deta_ofer),
             a.val_prec_publ_tota_loca = CASE
                                           WHEN ((SELECT nvl(imp_prec_publ, 0)
                                                    FROM pre_ofert_detal
                                                   WHERE oid_deta_ofer =
                                                         a.ofde_oid_deta_ofer) *
                                                a.num_unid_compr) -
                                                (a.val_prec_cata_unit_loca *
                                                a.num_unid_compr) < 0 THEN
                                            0
                                           ELSE
                                            ((SELECT nvl(imp_prec_publ, 0)
                                                FROM pre_ofert_detal
                                               WHERE oid_deta_ofer =
                                                     a.ofde_oid_deta_ofer) *
                                            a.num_unid_compr) -
                                            (a.val_prec_cata_unit_loca *
                                            a.num_unid_compr)
                                         END
       WHERE a.soca_oid_soli_cabe IN
             (SELECT oid_soli_cabe
                FROM ped_solic_cabec x,
                     cra_perio       y,
                     seg_perio_corpo z,
                     bas_ctrl_fact   z1
               WHERE x.perd_oid_peri = y.oid_peri
                 AND y.peri_oid_peri = z.oid_peri
                 AND z.cod_peri = z1.cod_peri
                 AND z1.sta_camp = 0
                 AND z1.ind_camp_act = 1
                 AND x.fec_prog_fact = z1.fec_proc
                 AND x.grpr_oid_grup_proc = 4
                 AND x.ind_oc = 1)
         AND ofde_oid_deta_ofer IN
             (SELECT oid_deta_ofer
                FROM pre_ofert_detal       x,
                     pre_ofert             y,
                     pre_matri_factu_cabec z,
                     cra_perio             x1,
                     seg_perio_corpo       y1,
                     bas_ctrl_fact         z1,
                     pre_tipo_ofert        zz
               WHERE x.ofer_oid_ofer = y.oid_ofer
                 AND y.mfca_oid_cabe = z.oid_cabe
                 AND z.perd_oid_peri = x1.oid_peri
                 AND x1.peri_oid_peri = y1.oid_peri
                 AND y1.cod_peri = z1.cod_peri
                 AND z1.sta_camp = 0
                 AND z1.ind_camp_act = 1
                 AND x.tofe_oid_tipo_ofer = zz.oid_tipo_ofer
                 AND zz.num_secc_deta_fact = 1);
    
      UPDATE ped_solic_cabec a
         SET a.val_gana_tota_loca
             --=(select sum(nvl(B.VAL_PREC_PUBL_TOTA_LOCA,0)+(nvl(B.Val_Impo_Desc_unit_Loca,0)*b.num_unid_compr))
              =
             (SELECT SUM(decode(b2.num_secc_deta_fact,
                                1,
                                nvl(b.val_prec_publ_tota_loca, 0),
                                0)) + SUM(decode(b2.num_secc_deta_fact,
                                                 0,
                                                 nvl((b.val_impo_desc_unit_loca *
                                                     b.num_unid_compr),
                                                     0),
                                                 0))
                FROM ped_solic_posic b,
                     pre_ofert_detal b1,
                     pre_tipo_ofert  b2
               WHERE b.soca_oid_soli_cabe = a.oid_soli_cabe
                 AND b.ofde_oid_deta_ofer = b1.oid_deta_ofer
                 AND b1.tofe_oid_tipo_ofer = b2.oid_tipo_ofer)
       WHERE a.oid_soli_cabe IN (SELECT oid_soli_cabe
                                   FROM ped_solic_cabec x,
                                        cra_perio       y,
                                        seg_perio_corpo z,
                                        bas_ctrl_fact   z1
                                  WHERE x.perd_oid_peri = y.oid_peri
                                    AND y.peri_oid_peri = z.oid_peri
                                    AND z.cod_peri = z1.cod_peri
                                    AND z1.sta_camp = 0
                                    AND z1.ind_camp_act = 1
                                    AND x.fec_prog_fact = z1.fec_proc
                                    AND x.grpr_oid_grup_proc = 4
                                    AND x.ind_oc = 1);
    
    ELSIF nvl(lsparageneopor, '') = 'C' THEN
    
      UPDATE ped_solic_cabec a
         SET a.val_gana_tota_loca
             --=(select sum(nvl(B.VAL_PREC_PUBL_TOTA_LOCA,0)+(nvl(B.Val_Impo_Desc_unit_Loca,0)*b.num_unid_compr))
              =
             (SELECT SUM(CASE
                           WHEN b.val_prec_cata_unit_loca = 0 THEN
                            b.val_prec_cata_tota_loca
                           ELSE
                            b.num_unid_compr *
                            nvl(round(nvl(b.val_prec_cata_unit_loca, 0) *
                                      nvl(b.val_porc_desc, 0) / 100,
                                      0),
                                0)
                         END)
                FROM ped_solic_posic b,
                     pre_ofert_detal b1,
                     pre_tipo_ofert  b2
               WHERE b.soca_oid_soli_cabe = a.oid_soli_cabe
                 AND b.ofde_oid_deta_ofer = b1.oid_deta_ofer
                 AND b1.tofe_oid_tipo_ofer = b2.oid_tipo_ofer)
       WHERE a.oid_soli_cabe IN (SELECT oid_soli_cabe
                                   FROM ped_solic_cabec x,
                                        cra_perio       y,
                                        seg_perio_corpo z,
                                        bas_ctrl_fact   z1
                                  WHERE x.perd_oid_peri = y.oid_peri
                                    AND y.peri_oid_peri = z.oid_peri
                                    AND z.cod_peri = z1.cod_peri
                                    AND z1.sta_camp = 0
                                    AND z1.ind_camp_act = 1
                                    AND x.fec_prog_fact = z1.fec_proc
                                    AND x.grpr_oid_grup_proc = 4
                                    AND x.ind_oc = 1);
    
    END IF;
  
    DELETE FROM imp_paque_docum_detal_factu;
    DELETE FROM imp_paque_docum_cupon;
    DELETE FROM imp_paque_docum_laser_ctact;
    DELETE FROM imp_paque_docum_ocs;
    DELETE FROM imp_paque_docum_bolet_despa;
    DELETE FROM imp_paque_docum_unot;
    DELETE FROM imp_paque_docum_laser_factu;
    DELETE FROM imp_nota_credi_laser;
    DELETE FROM imp_paque_docum_final;
    DELETE FROM imp_paque_docum_factu_matri;
    DELETE FROM imp_paque_docum_bolet_matri;
    DELETE FROM imp_paque_docum_progr_punto;
  
    BEGIN
      INSERT INTO app_rutas_trans
        SELECT app_rutr_seq.nextval,
               pais_oid_pais,
               cod_zona,
               NULL,
               cod_zona
          FROM zon_zona
         WHERE NOT EXISTS
         (SELECT 1 FROM app_rutas_trans WHERE cod_ruta = cod_zona);
    
    EXCEPTION
      WHEN OTHERS THEN
        NULL;
    END;
  
    -- Actualiza Forma Pago
  
    lsformapago := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                        'STO_FORMA_PAGO_DIFER');
  
    lscuvformapago := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                           'STO_CUV_FORMA_PAGO');
  
    IF lsformapago IS NOT NULL THEN
      UPDATE ped_solic_cabec
         SET fopa_oid_form_pago = lsformapago
      --where val_tota_paga_loca>=decode(lsMontoFormaPago, null, val_tota_paga_loca, to_number(lsMontoFormaPago))
       WHERE val_tota_paga_loca >=
             (SELECT nvl(imp_ingr_fami, '0')
                FROM mae_clien_datos_adici
               WHERE clie_oid_clie = ped_solic_cabec.clie_oid_clie
                 AND imp_ingr_fami IS NOT NULL)
         AND oid_soli_cabe IN
             (SELECT oid_soli_cabe
                FROM ped_solic_cabec     a,
                     ped_tipo_solic_pais b
               WHERE b.ind_suje_flet = 1
                 AND a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
                 AND a.perd_oid_peri =
                     (SELECT x.oid_peri
                        FROM cra_perio       x,
                             seg_perio_corpo y,
                             bas_ctrl_fact   z
                       WHERE x.peri_oid_peri = y.oid_peri
                         AND y.cod_peri = z.cod_peri
                         AND z.ind_camp_act = 1
                         AND z.sta_camp = 0)
                 AND a.grpr_oid_grup_proc IN (3, 4))
         AND EXISTS
       (SELECT 1
                FROM ped_solic_posic
               WHERE soca_oid_soli_cabe = oid_soli_cabe
                 AND espo_oid_esta_posi <> 2
                 AND val_codi_vent = decode(lscuvformapago,
                                            NULL,
                                            val_codi_vent,
                                            lscuvformapago))
         AND EXISTS
       (SELECT 1
                FROM mae_clien_datos_adici
               WHERE clie_oid_clie = ped_solic_cabec.clie_oid_clie
                 AND imp_ingr_fami IS NOT NULL);
      UPDATE ped_solic_cabec
         SET fopa_oid_form_pago = lsformapago
       WHERE perd_oid_peri = (SELECT x.oid_peri
                                FROM cra_perio       x,
                                     seg_perio_corpo y,
                                     bas_ctrl_fact   z
                               WHERE x.peri_oid_peri = y.oid_peri
                                 AND y.cod_peri = z.cod_peri
                                 AND z.ind_camp_act = 1
                                 AND z.sta_camp = 0)
            
         AND grpr_oid_grup_proc IN (4)
         AND fopa_oid_form_pago <> lsformapago
         AND tspa_oid_tipo_soli_pais IN
             (SELECT oid_tipo_soli_pais
                FROM ped_tipo_solic_pais,
                     ped_tipo_solic
               WHERE tsol_oid_tipo_soli = oid_tipo_soli
                 AND ind_soli_nega = 0)
         AND clie_oid_clie IN
             (SELECT clie_oid_clie
                FROM ped_solic_cabec a
               WHERE a.perd_oid_peri =
                     (SELECT x.oid_peri
                        FROM cra_perio       x,
                             seg_perio_corpo y,
                             bas_ctrl_fact   z
                       WHERE x.peri_oid_peri = y.oid_peri
                         AND y.cod_peri = z.cod_peri
                         AND z.ind_camp_act = 1
                         AND z.sta_camp = 0)
                 AND a.grpr_oid_grup_proc IN (3, 4)
                 AND fopa_oid_form_pago = lsformapago);
    END IF;
  
    -- Actualiza Cronograma de Boleta de Recojo segun el pais
    lsactindcaja := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                         'STO_ACTUA_IND_CAJA');
    IF lsactindcaja = 'S' THEN
    
      UPDATE mae_produ SET cod_ind_dent_caja = val_atri_1;
    
      UPDATE mae_produ
         SET cod_ind_dent_caja = 'F'
       WHERE oid_prod IN
             (SELECT DISTINCT b.prod_oid_prod
                FROM ped_solic_cabec     a,
                     ped_solic_posic     b,
                     ped_tipo_solic_pais e,
                     ped_tipo_solic      f,
                     mae_produ           g
               WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                 AND a.perd_oid_peri =
                     (SELECT c.oid_peri
                        FROM cra_perio       c,
                             seg_perio_corpo d
                       WHERE c.peri_oid_peri = d.oid_peri
                         AND d.cod_peri =
                             (SELECT cod_peri
                                FROM bas_ctrl_fact b
                               WHERE b.sta_camp = 0
                                 AND b.ind_camp_act = 1))
                 AND a.tspa_oid_tipo_soli_pais = e.oid_tipo_soli_pais
                 AND e.tsol_oid_tipo_soli = f.oid_tipo_soli
                 AND nvl(f.ind_soli_nega, 0) = 0
                 AND b.prod_oid_prod = g.oid_prod
                 AND a.grpr_oid_grup_proc = 4
                 AND g.val_atri_1 IS NULL);
    
    END IF;
  
    BEGIN
      SELECT bpp.val_para
        INTO lscodalma
        FROM bas_param_pais bpp
       WHERE bpp.cod_pais = lscodpais
         AND bpp.cod_sist = 'MAV'
         AND bpp.cod_para = '001';
    EXCEPTION
      WHEN OTHERS THEN
        lscodalma := '';
    END;
  
    BEGIN
      IF lscodalma <> '' THEN
        UPDATE ped_solic_cabec aa
           SET aa.almc_oid_alma = 2001
         WHERE oid_soli_cabe IN
               (SELECT oid_soli_cabe
                  FROM ped_solic_cabec     a,
                       ped_tipo_solic_pais b
                 WHERE a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
                   AND a.perd_oid_peri =
                       (SELECT x.oid_peri
                          FROM cra_perio       x,
                               seg_perio_corpo y,
                               bas_ctrl_fact   z
                         WHERE x.peri_oid_peri = y.oid_peri
                           AND y.cod_peri = z.cod_peri
                           AND z.ind_camp_act = 1
                           AND z.sta_camp = 0)
                   AND a.grpr_oid_grup_proc = 4
                   AND a.almc_oid_alma =
                       (SELECT oid_alma
                          FROM bel_almac
                         WHERE cod_alma = lscodalma));
      END IF;
    EXCEPTION
      WHEN OTHERS THEN
        RETURN;
    END;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123, 'ERROR ped_pr_flete: ' || ls_sqlerrm);
  END ped_pr_flete;
  /***************************************************************************
  Descripcion       : Inicializa Stock
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ini_stock IS
  
    w_filas       NUMBER(12);
    varoidiniprod NUMBER(12);
    varsaldoini   NUMBER(12);
    varalmacini   NUMBER(12);
  
    lscodpais VARCHAR2(3);
    lscodperi VARCHAR2(10);
  
    lsparaactisaldoini VARCHAR2(2);
    lsparaactisaldofin VARCHAR2(2);
    
    lsRevRec VARCHAR2(1);
  
    CURSOR ini_stock IS
      SELECT prod_oid_prod,
             val_cant,
             almc_oid_alma_sali
        FROM bel_movim_almac_detal a
       WHERE a.mval_oid_movi_alma IN
             (SELECT MAX(cab.oid_movi_alma) numeromovimiento
                FROM bel_movim_almac_cabec cab
               WHERE --cab.almc_oid_alma_2 = 2001 -- Almacen VD
              --AND
               cab.val_obse LIKE '%SAM6%')
         AND a.tmal_oid_tipo_movi_alma = 2005;
  
    TYPE ini_stockrec IS RECORD(
      varoidiniprod NUMBER(12),
      varsaldoini   NUMBER(12),
      varalmacini   NUMBER(12));
  
    TYPE ini_stockrectab IS TABLE OF ini_stockrec;
    ini_stockrecord ini_stockrectab;
  
    lnoidperiodo cra_perio.oid_peri%TYPE;
    ldfecha      DATE;
  
    ln_oidestra NUMBER(10);
  
  BEGIN
  
    SELECT cod_pais
      INTO lscodpais
      FROM (SELECT cod_pais
              FROM (SELECT cod_pais,
                           COUNT(*) xx
                      FROM bas_ctrl_fact
                     GROUP BY cod_pais)
             ORDER BY xx DESC)
     WHERE rownum = 1;
  
    ln_oidestra := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                        'STO_ESTRA_NAVID');

    lsRevRec := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,'STO_REVER_RECLA_STOCK'),'N');

  
    SELECT cod_peri,
           fec_proc
      INTO lscodperi,
           ldfecha
      FROM bas_ctrl_fact a
     WHERE a.sta_camp = 0
       AND a.ind_camp_act = 1;
  
    lnoidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(lscodperi);
  
    -- Actualizacion de Posiciones con almacen diferenciado
    ped_pr_actua_almc_posic(lnoidperiodo, ldfecha);
  
    -- Creacion de Ofertas Alterhativas Automaticas
    pre_pkg_proce.pre_pr_crea_ofert_alter(lscodpais, lscodperi);
  
    lsparaactisaldoini := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                               'STO_ACTIV_SALDO_INI');
    lsparaactisaldofin := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                               'STO_ACTIV_SALDO_FIN');
  
    -- Actualiza periodo de Reclamos
    ocr_solic_pedidos.ocr_pr_actua_peri_cdr(lnoidperiodo);
  
    --Procedimiento para actualizar el precio publico en la matriz
    UPDATE pre_ofert_detal a
       SET a.imp_prec_publ = CASE
                               WHEN a.tofe_oid_tipo_ofer IN
                                    (SELECT oid_tipo_ofer
                                       FROM pre_tipo_ofert
                                      WHERE num_secc_deta_fact IN (0, 2)) THEN
                                a.precio_unitario
                               WHEN a.tofe_oid_tipo_ofer IN
                                    (SELECT oid_tipo_ofer
                                       FROM pre_tipo_ofert
                                      WHERE num_secc_deta_fact = 1) THEN
                                nvl((SELECT MAX(bb.precio_unitario)
                                      FROM pre_ofert             aa,
                                           pre_ofert_detal       bb,
                                           pre_matri_factu_cabec cc
                                     WHERE aa.oid_ofer = bb.ofer_oid_ofer
                                       AND aa.mfca_oid_cabe = cc.oid_cabe
                                       AND cc.perd_oid_peri = lnoidperiodo
                                       AND bb.prod_oid_prod = a.prod_oid_prod
                                       AND bb.tofe_oid_tipo_ofer <> 2019
                                       AND bb.tofe_oid_tipo_ofer IN
                                           (SELECT oid_tipo_ofer
                                              FROM pre_tipo_ofert
                                             WHERE num_secc_deta_fact = 0)),
                                    a.imp_prec_posi * 0.7)
                               ELSE
                                0
                             END
     WHERE a.ofer_oid_ofer IN
           (SELECT oid_ofer
              FROM pre_ofert             x,
                   pre_matri_factu_cabec y
             WHERE x.mfca_oid_cabe = y.oid_cabe
               AND y.perd_oid_peri = lnoidperiodo);
  
    w_filas := 100000;
  
    -- Empieza la logica de consolidacion
  
    IF lsparaactisaldoini IS NOT NULL AND lsparaactisaldofin IS NOT NULL THEN
    
      UPDATE int_solic_conso_cabec
         SET fec_prog_fact =
             (SELECT fec_proc
                FROM bas_ctrl_fact
               WHERE ind_camp_act = 1
                 AND sta_camp = 0)
       WHERE soca_oid_soli_cabe_refe IN
             (SELECT oid_soli_cabe
                FROM ped_solic_cabec
               WHERE esso_oid_esta_soli = 7 --and grpr_oid_grup_proc=2
                 AND ind_oc = 1
                 AND perd_oid_peri = lnoidperiodo);
    
      UPDATE ped_solic_cabec
         SET esso_oid_esta_soli = 1,
             grpr_oid_grup_proc = 3,
             fec_prog_fact     =
             (SELECT fec_proc
                FROM bas_ctrl_fact
               WHERE ind_camp_act = 1
                 AND sta_camp = 0)
       WHERE esso_oid_esta_soli = 7 --and grpr_oid_grup_proc=2
         AND ind_oc = 1
         AND perd_oid_peri = lnoidperiodo;
    
      UPDATE int_solic_conso_cabec
         SET fec_prog_fact = '01/01/2000'
       WHERE soca_oid_soli_cabe_refe IN
             (SELECT oid_soli_cabe
                FROM ped_solic_cabec a
               WHERE a.ind_oc = 1
                 AND a.grpr_oid_grup_proc = 3
                 AND a.perd_oid_peri = lnoidperiodo
                 AND a.fec_prog_fact >
                     (SELECT fec_inic
                        FROM cra_crono x,
                             seg_pais  y,
                             cra_activ z
                       WHERE x.perd_oid_peri = a.perd_oid_peri
                         AND z.pais_oid_pais = y.oid_pais
                         AND y.cod_pais = lscodpais
                         AND x.cact_oid_acti = z.oid_acti
                         AND z.cod_acti = lsparaactisaldoini
                         AND x.zzon_oid_zona = a.zzon_oid_zona)
                 AND a.fec_prog_fact <
                     (SELECT fec_inic
                        FROM cra_crono x,
                             seg_pais  y,
                             cra_activ z
                       WHERE x.perd_oid_peri = a.perd_oid_peri
                         AND z.pais_oid_pais = y.oid_pais
                         AND y.cod_pais = lscodpais
                         AND x.cact_oid_acti = z.oid_acti
                         AND z.cod_acti = lsparaactisaldofin
                         AND x.zzon_oid_zona = a.zzon_oid_zona)
                 AND NOT EXISTS
               (SELECT 1
                        FROM mae_clien_tipo_subti xx,
                             mae_clien_clasi      yy,
                             app_rutas_clasi      zz
                       WHERE xx.oid_clie_tipo_subt =
                             yy.ctsu_oid_clie_tipo_subt
                         AND xx.clie_oid_clie = a.clie_oid_clie
                         AND xx.ticl_oid_tipo_clie =
                             decode(zz.ticl_oid_tipo_clie,
                                    NULL,
                                    xx.ticl_oid_tipo_clie,
                                    zz.ticl_oid_tipo_clie)
                         AND xx.sbti_oid_subt_clie =
                             decode(zz.sbti_oid_subt_clie,
                                    NULL,
                                    xx.sbti_oid_subt_clie,
                                    zz.sbti_oid_subt_clie)
                         AND yy.tccl_oid_tipo_clasi =
                             decode(zz.tccl_oid_tipo_clas,
                                    NULL,
                                    yy.tccl_oid_tipo_clasi,
                                    zz.tccl_oid_tipo_clas)
                         AND yy.clas_oid_clas =
                             decode(zz.clas_oid_clas,
                                    NULL,
                                    yy.clas_oid_clas,
                                    zz.clas_oid_clas))
              
              );
    
      UPDATE ped_solic_cabec a
         SET a.esso_oid_esta_soli = 7,
             a.fec_prog_fact      = '01/01/2000',
             grpr_oid_grup_proc   = 2
       WHERE a.ind_oc = 1
         AND a.grpr_oid_grup_proc = 3
         AND a.perd_oid_peri = lnoidperiodo
         AND a.fec_prog_fact >
             (SELECT fec_inic
                FROM cra_crono x,
                     seg_pais  y,
                     cra_activ z
               WHERE x.perd_oid_peri = a.perd_oid_peri
                 AND z.pais_oid_pais = y.oid_pais
                 AND y.cod_pais = lscodpais
                 AND x.cact_oid_acti = z.oid_acti
                 AND z.cod_acti = lsparaactisaldoini
                 AND x.zzon_oid_zona = a.zzon_oid_zona)
         AND a.fec_prog_fact <
             (SELECT fec_inic
                FROM cra_crono x,
                     seg_pais  y,
                     cra_activ z
               WHERE x.perd_oid_peri = a.perd_oid_peri
                 AND z.pais_oid_pais = y.oid_pais
                 AND y.cod_pais = lscodpais
                 AND x.cact_oid_acti = z.oid_acti
                 AND z.cod_acti = lsparaactisaldofin
                 AND x.zzon_oid_zona = a.zzon_oid_zona)
         AND NOT EXISTS
       (SELECT 1
                FROM mae_clien_tipo_subti xx,
                     mae_clien_clasi      yy,
                     app_rutas_clasi      zz
               WHERE xx.oid_clie_tipo_subt = yy.ctsu_oid_clie_tipo_subt
                 AND xx.clie_oid_clie = a.clie_oid_clie
                 AND xx.ticl_oid_tipo_clie =
                     decode(zz.ticl_oid_tipo_clie,
                            NULL,
                            xx.ticl_oid_tipo_clie,
                            zz.ticl_oid_tipo_clie)
                 AND xx.sbti_oid_subt_clie =
                     decode(zz.sbti_oid_subt_clie,
                            NULL,
                            xx.sbti_oid_subt_clie,
                            zz.sbti_oid_subt_clie)
                 AND yy.tccl_oid_tipo_clasi =
                     decode(zz.tccl_oid_tipo_clas,
                            NULL,
                            yy.tccl_oid_tipo_clasi,
                            zz.tccl_oid_tipo_clas)
                 AND yy.clas_oid_clas =
                     decode(zz.clas_oid_clas,
                            NULL,
                            yy.clas_oid_clas,
                            zz.clas_oid_clas));
    
    END IF;
  
    UPDATE ped_solic_cabec
       SET fec_repo_falt      = decode(ind_rece_onli, 1, fec_repo_falt, NULL),
           grpr_oid_grup_proc = decode(ind_oc, 1, grpr_oid_grup_proc, 3)
     WHERE oid_soli_cabe IN
           (SELECT oid_soli_cabe
              FROM ped_solic_cabec     a,
                   ped_tipo_solic_pais c,
                   ped_tipo_solic      d
             WHERE perd_oid_peri = lnoidperiodo
               AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
               AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
               AND nvl(d.ind_soli_nega, 0) = 0
               AND a.grpr_oid_grup_proc IN (3, 4));
  
    -- Se actualizan Unidades con Tipo de Oferta Excluidos actualizados a cero por facturacion de MAV
  
    UPDATE ped_solic_posic
       SET num_unid_dema_real = num_unid_por_aten
     WHERE oid_soli_posi IN
           (SELECT oid_soli_posi
              FROM ped_solic_cabec     a,
                   ped_solic_posic     b,
                   ped_tipo_solic_pais c,
                   ped_tipo_solic      d,
                   pre_ofert_detal     e
             WHERE perd_oid_peri = lnoidperiodo
               AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
               AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
               AND a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND nvl(d.ind_soli_nega, 0) = 0
               AND a.grpr_oid_grup_proc IN (3, 4)
               AND b.espo_oid_esta_posi = 4
               AND b.ofde_oid_deta_ofer = e.oid_deta_ofer
               AND EXISTS
             (SELECT 1
                      FROM fac_tipo_ofert_exclu
                     WHERE tofe_oid_tipo_ofer = e.tofe_oid_tipo_ofer)
               AND b.num_unid_por_aten > b.num_unid_dema_real);
  
    -- Se actualiza Control de Stock, Control de Liquidacion
  
    UPDATE ped_solic_posic
       SET num_unid_por_aten = num_unid_dema_real,
           ind_ctrl_stoc     = NULL,
           ind_ctrl_liqu     = NULL
     WHERE oid_soli_posi IN
           (SELECT oid_soli_posi
              FROM ped_solic_cabec     a,
                   ped_solic_posic     b,
                   ped_tipo_solic_pais c,
                   ped_tipo_solic      d
             WHERE perd_oid_peri = lnoidperiodo
               AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
               AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
               AND a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND nvl(d.ind_soli_nega, 0) = 0
               AND a.grpr_oid_grup_proc IN (3, 4)
               AND b.espo_oid_esta_posi = 4
               AND (b.ind_ctrl_stoc IS NOT NULL OR
                   b.ind_ctrl_liqu IS NOT NULL));
  
    -- Se eliminan las ofertas navide?as
    /*
    DELETE FROM ped_solic_posic
     WHERE oid_soli_posi IN (SELECT b.oid_soli_posi
                               FROM ped_solic_cabec     a,
                                    ped_solic_posic     b,
                                    ped_tipo_solic_pais d,
                                    ped_tipo_solic      e,
                                    pre_ofert_detal     f,
                                    pre_ofert           g
                              WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                                AND a.perd_oid_peri = lnoidperiodo
                                AND a.ind_oc = 1
                                AND a.tspa_oid_tipo_soli_pais = d.oid_tipo_soli_pais
                                AND d.tsol_oid_tipo_soli = e.oid_tipo_soli
                                AND b.stpo_oid_subt_posi = 22
                                and b.ofde_oid_deta_ofer=f.oid_deta_ofer
                                and f.ofer_oid_ofer=g.oid_ofer
                                and g.coes_oid_estr=ln_oidestra
                                AND nvl(e.ind_soli_nega,
                                        0) = 0
                                AND a.grpr_oid_grup_proc IN (3,
                                                             4));
                                                             */
  
    -- Se actualizan las ofertas navide?as
  
    /*    update ped_solic_posic
        set num_unid_por_aten=num_unid_dema
        , num_unid_dema_real=num_unid_dema
        , num_unid_compr=num_unid_dema
         WHERE oid_soli_posi IN (SELECT b.oid_soli_posi
                                   FROM ped_solic_cabec     a,
                                        ped_solic_posic     b,
                                        ped_tipo_solic_pais d,
                                        ped_tipo_solic      e,
                                        pre_ofert_detal     f,
                                        pre_ofert           g
                                  WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                                    AND a.perd_oid_peri = lnoidperiodo
                                    AND a.ind_oc = 1
                                    AND a.tspa_oid_tipo_soli_pais = d.oid_tipo_soli_pais
                                    AND d.tsol_oid_tipo_soli = e.oid_tipo_soli
                                    AND b.stpo_oid_subt_posi = 5
                                    and b.ofde_oid_deta_ofer=f.oid_deta_ofer
                                    and f.ofer_oid_ofer=g.oid_ofer
                                    and g.coes_oid_estr=ln_oidestra
                                    AND nvl(e.ind_soli_nega,
                                            0) = 0
                                    AND a.grpr_oid_grup_proc IN (3,
                                                                 4));
    
    */
    -- Se eliminan los alternativos
  
    DELETE FROM ped_solic_posic
     WHERE oid_soli_posi IN
           (SELECT b.oid_soli_posi
              FROM ped_solic_cabec     a,
                   ped_solic_posic     b,
                   ped_tipo_solic_pais d,
                   ped_tipo_solic      e
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.perd_oid_peri = lnoidperiodo
               AND a.ind_oc = 1
               AND a.tspa_oid_tipo_soli_pais = d.oid_tipo_soli_pais
               AND d.tsol_oid_tipo_soli = e.oid_tipo_soli
               AND b.stpo_oid_subt_posi = 7
               AND nvl(e.ind_soli_nega, 0) = 0
               AND a.grpr_oid_grup_proc IN (3, 4));
  
    UPDATE ped_solic_posic
       SET espo_oid_esta_posi = 4,
           val_obse           = NULL
     WHERE oid_soli_posi IN
           (SELECT b.oid_soli_posi
              FROM ped_solic_cabec     a,
                   ped_solic_posic     b,
                   ped_tipo_solic_pais d,
                   ped_tipo_solic      e
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.perd_oid_peri = lnoidperiodo
               AND a.ind_oc = 1
               AND a.tspa_oid_tipo_soli_pais = d.oid_tipo_soli_pais
               AND d.tsol_oid_tipo_soli = e.oid_tipo_soli
               AND nvl(e.ind_soli_nega, 0) = 0
               AND b.espo_oid_esta_posi = 2
               AND b.val_obse = 'ANULADO POR ALTERNATIVO'
               AND a.grpr_oid_grup_proc IN (3, 4));
  
    /*
    delete from inc_artic_premi_alter where bofa_oid_bols_falt in
    (
    select f.oid_bols_falt
    from ped_solic_cabec a, ped_solic_posic b, ped_tipo_solic_pais d, ped_tipo_solic e, inc_bolsa_falta f
    where a.OID_SOLI_CABE=b.SOCA_OID_SOLI_CABE
    and a.PERD_OID_PERI=(SELECT x.oid_peri
                      FROM cra_perio x, seg_perio_corpo y, bas_ctrl_fact z
                     WHERE x.peri_oid_peri = y.oid_peri
                       AND y.cod_peri = z.cod_peri
                       AND z.ind_camp_act = 1
                       AND z.sta_camp = 0)
    and a.ind_oc=0
    and a.TSPA_OID_TIPO_SOLI_PAIS=d.OID_TIPO_SOLI_PAIS and d.TSOL_OID_TIPO_SOLI=e.OID_TIPO_SOLI
    and nvl(e.IND_SOLI_NEGA,0)=0
    and b.oid_soli_posi=f.sopo_oid_soli_posi
    );
    
    
    delete from inc_bolsa_falta where oid_bols_falt in
    (
    select f.oid_bols_falt
    from ped_solic_cabec a, ped_solic_posic b, ped_tipo_solic_pais d, ped_tipo_solic e, inc_bolsa_falta f
    where a.OID_SOLI_CABE=b.SOCA_OID_SOLI_CABE
    and a.PERD_OID_PERI=(SELECT x.oid_peri
                      FROM cra_perio x, seg_perio_corpo y, bas_ctrl_fact z
                     WHERE x.peri_oid_peri = y.oid_peri
                       AND y.cod_peri = z.cod_peri
                       AND z.ind_camp_act = 1
                       AND z.sta_camp = 0)
    and a.ind_oc=0
    and a.TSPA_OID_TIPO_SOLI_PAIS=d.OID_TIPO_SOLI_PAIS and d.TSOL_OID_TIPO_SOLI=e.OID_TIPO_SOLI
    and nvl(e.IND_SOLI_NEGA,0)=0
    and b.oid_soli_posi=f.sopo_oid_soli_posi
    );
    */

    -- Se actualizan los reclamos que se hayan procesado por facturación MAV
    
    if lsRevRec='S' then
        update ped_solic_cabec a set
        a.grpr_oid_grup_proc=3,
        a.fec_repo_falt=null
        where (a.modu_oid_modu=15 or (a.modu_oid_modu=15 and a.ind_oc=0))
        and a.grpr_oid_grup_proc=4
        and a.fec_repo_falt is not null
        and a.perd_oid_peri=lnoidperiodo
        and a.fec_prog_fact=ldfecha;
    end if;
      
    -- Se inicializa el stock
    DELETE FROM bel_stock;
  
    OPEN ini_stock;
    LOOP
      FETCH ini_stock BULK COLLECT
        INTO ini_stockrecord LIMIT w_filas;
      IF ini_stockrecord.count > 0 THEN
        FOR x IN ini_stockrecord.first .. ini_stockrecord.last
        LOOP
        
          varoidiniprod := ini_stockrecord(x).varoidiniprod;
          varsaldoini   := ini_stockrecord(x).varsaldoini;
          varalmacini   := ini_stockrecord(x).varalmacini;
        
          INSERT INTO bel_stock
            (oid_stoc,
             almc_oid_alma,
             esme_oid_esta_merc,
             prod_oid_prod,
             val_sald)
          VALUES
            (bel_stck_seq.nextval,
             varalmacini,
             2001,
             varoidiniprod,
             varsaldoini);
        
        END LOOP;
      END IF;
      EXIT WHEN ini_stock%NOTFOUND;
    END LOOP;
    CLOSE ini_stock;
  
    /*Habilita el inicio de Dia PROL*/
    /*UPDATE bas_ctrl_fact c
      SET c.ind_acti_prol = '1'
    WHERE c.ind_camp_act = '1'
      AND c.sta_camp = '0';*/
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_ini_stock: ' || ls_sqlerrm);
  END ped_pr_ini_stock;
  /***************************************************************************
  Descripcion       : Reporte Faltante
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_repor_falta IS
  
  BEGIN
  
    INSERT INTO fac_proye_falta_produ
    
      SELECT cod_pais,
             (SELECT fec_proc
                FROM bas_ctrl_fact
               WHERE sta_camp = 0
                 AND ind_camp_act = 1) fec_fact,
             cod_unid_nego,
             cod_sap,
             des_cort,
             cod_marc_prod,
             des_marc_prod,
             unidades_comprometidas + nvl((SELECT SUM(val_sald)
                                            FROM bel_stock,
                                                 mae_produ
                                           WHERE oid_prod = prod_oid_prod
                                             AND esme_oid_esta_merc = 2001
                                             AND cod_sap = xx.cod_sap),
                                          0) stock,
             unidades_demandadas,
             unidades_comprometidas,
             unidades_faltante,
             unidades_faltante / unidades_demandadas * 100 porc_falt,
             monto_demanda,
             monto_atendido,
             monto_faltante,
             decode(monto_demanda,
                    0,
                    0,
                    (monto_faltante / monto_demanda * 100)) porc_falt_monto,
             unidades_faltante / SUM(unidades_demandadas) over(PARTITION BY cod_unid_nego) * 100 por_falt_nego,
             monto_faltante / SUM(monto_demanda) over(PARTITION BY cod_pais) * 100 por_falt_tota_fact,
             NULL,
             oc,
             nvl((SELECT MAX(num_vers) + 1
                   FROM fac_proye_falta_produ
                  WHERE fec_fact = (SELECT fec_proc
                                      FROM bas_ctrl_fact
                                     WHERE sta_camp = 0
                                       AND ind_camp_act = 1)),
                 1) version
        FROM (SELECT cod_pais,
                     cod_unid_nego,
                     cod_sap,
                     des_cort,
                     cod_marc_prod,
                     des_marc_prod,
                     SUM(num_unid_compr) unidades_comprometidas,
                     SUM(num_unid_dema_real) unidades_demandadas,
                     SUM(falt) unidades_faltante,
                     SUM(mto_dem) monto_demanda,
                     SUM(mto_comp) monto_atendido,
                     SUM(mto_falt) monto_faltante,
                     COUNT(DISTINCT oid_soli_cabe) oc
                FROM (SELECT f.cod_pais,
                             g.cod_unid_nego,
                             c.cod_sap,
                             i.val_i18n AS des_cort,
                             h.cod_marc_prod,
                             h.des_marc_prod,
                             a.oid_soli_cabe,
                             b.num_unid_compr,
                             b.num_unid_dema_real,
                             b.num_unid_dema_real - b.num_unid_compr falt,
                             b.val_prec_cata_unit_loca * b.num_unid_dema_real mto_dem,
                             b.val_prec_cata_unit_loca * b.num_unid_compr mto_comp,
                             b.val_prec_cata_unit_loca *
                             (b.num_unid_dema_real - b.num_unid_compr) mto_falt
                        FROM ped_solic_cabec     a,
                             ped_solic_posic     b,
                             mae_produ           c,
                             ped_tipo_solic_pais d,
                             ped_tipo_solic      e,
                             seg_pais            f,
                             mae_unida_negoc     g,
                             seg_marca_produ     h,
                             gen_i18n_sicc_pais  i
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND a.perd_oid_peri =
                             (SELECT x.oid_peri
                                FROM cra_perio       x,
                                     seg_perio_corpo y,
                                     bas_ctrl_fact   z
                               WHERE x.peri_oid_peri = y.oid_peri
                                 AND y.cod_peri = z.cod_peri
                                 AND z.ind_camp_act = 1
                                 AND z.sta_camp = 0)
                         AND i.attr_enti = 'MAE_PRODU'
                         AND i.val_oid = c.oid_prod
                         AND b.prod_oid_prod = c.oid_prod
                         AND a.modu_oid_modu <> 9
                         AND a.tspa_oid_tipo_soli_pais = d.oid_tipo_soli_pais
                         AND d.tsol_oid_tipo_soli = e.oid_tipo_soli
                         AND nvl(e.ind_soli_nega, 0) = 0
                         AND a.grpr_oid_grup_proc IN (3, 4)
                         AND a.fec_repo_falt IS NOT NULL
                         AND b.espo_oid_esta_posi <> 2
                         AND a.pais_oid_pais = f.oid_pais
                         AND c.uneg_oid_unid_nego = g.oid_unid_nego
                         AND c.mapr_oid_marc_prod = h.oid_marc_prod)
               GROUP BY cod_pais,
                        cod_unid_nego,
                        cod_sap,
                        des_cort,
                        cod_marc_prod,
                        des_marc_prod) xx
       WHERE xx.unidades_demandadas > 0;
  
    INSERT INTO fac_proye_falta_produ
      (pais_cod_pais,
       fec_fact,
       num_vers,
       cod_prod,
       des_prod,
       cod_nego,
       cod_marc_prod,
       des_marc_prod,
       num_uni_stoc_dispo,
       tot_unid_dema,
       tot_unid_aten,
       tot_unid_falt,
       por_falt_unid_dema,
       tot_mont_unid_dema,
       tot_mont_unid_aten,
       tot_mont_unid_falt,
       por_falt_mont_dema,
       por_falt_nego,
       por_falt_tota_fact,
       ind_valo_criti,
       tot_soli_oc)
      SELECT pais_cod_pais,
             fec_fact,
             num_vers,
             cod_prod,
             des_prod,
             '999',
             cod_marc_prod,
             des_marc_prod,
             num_uni_stoc_dispo,
             tot_unid_dema,
             tot_unid_aten,
             tot_unid_falt,
             por_falt_unid_dema,
             tot_mont_unid_dema,
             tot_mont_unid_aten,
             tot_mont_unid_falt,
             por_falt_mont_dema,
             por_falt_nego,
             por_falt_tota_fact,
             ind_valo_criti,
             tot_soli_oc
        FROM fac_proye_falta_produ a
       WHERE a.fec_fact = (SELECT fec_proc
                             FROM bas_ctrl_fact
                            WHERE sta_camp = 0
                              AND ind_camp_act = 1)
         AND a.num_vers =
             (SELECT MAX(num_vers)
                FROM fac_proye_falta_produ
               WHERE fec_fact = (SELECT fec_proc
                                   FROM bas_ctrl_fact
                                  WHERE sta_camp = 0
                                    AND ind_camp_act = 1));
  
    INSERT INTO fac_proye_falta_negoc
      SELECT cod_pais,
             (SELECT fec_proc
                FROM bas_ctrl_fact
               WHERE sta_camp = 0
                 AND ind_camp_act = 1) fec_fact,
             cod_unid_nego,
             des_cort,
             unidades_comprometidas +
             nvl((SELECT SUM(val_sald)
                   FROM bel_stock,
                        mae_produ,
                        mae_unida_negoc
                  WHERE oid_prod = prod_oid_prod
                    AND esme_oid_esta_merc = 2001
                    AND uneg_oid_unid_nego = oid_unid_nego
                    AND cod_unid_nego = xx.cod_unid_nego),
                 0) stock,
             unidades_demandadas,
             unidades_comprometidas,
             unidades_faltante,
             unidades_faltante / unidades_demandadas * 100 porc_falt,
             monto_demanda,
             monto_atendido,
             monto_faltante,
             decode(monto_demanda,
                    0,
                    0,
                    (monto_faltante / monto_demanda * 100)) porc_falt_monto,
             monto_faltante / SUM(monto_demanda) over(PARTITION BY cod_pais) * 100 por_falt_tota_fact,
             CASE
               WHEN (SELECT a.val_porc_maxi_perm
                       FROM bas_param_porce_falta a
                      WHERE a.cod_nego = cod_unid_nego) <
                    decode(monto_demanda,
                           0,
                           0,
                           (monto_faltante / monto_demanda * 100)) THEN
                'S'
               ELSE
                'N'
             END valor_critico,
             (SELECT cod_peri
                FROM bas_ctrl_fact
               WHERE sta_camp = 0
                 AND ind_camp_act = 1) periodo,
             oc,
             nvl((SELECT MAX(num_vers) + 1
                   FROM fac_proye_falta_negoc
                  WHERE fec_fact = (SELECT fec_proc
                                      FROM bas_ctrl_fact
                                     WHERE sta_camp = 0
                                       AND ind_camp_act = 1)),
                 1) version,
             NULL
        FROM (SELECT cod_pais,
                     cod_unid_nego,
                     des_cort,
                     SUM(num_unid_compr) unidades_comprometidas,
                     SUM(num_unid_dema_real) unidades_demandadas,
                     SUM(falt) unidades_faltante,
                     SUM(mto_dem) monto_demanda,
                     SUM(mto_comp) monto_atendido,
                     SUM(mto_falt) monto_faltante,
                     COUNT(DISTINCT oid_soli_cabe) oc
                FROM (SELECT f.cod_pais,
                             g.cod_unid_nego,
                             i.val_i18n AS des_cort,
                             a.oid_soli_cabe,
                             b.num_unid_compr,
                             b.num_unid_dema_real,
                             b.num_unid_dema_real - b.num_unid_compr falt,
                             b.val_prec_cata_unit_loca * b.num_unid_dema_real mto_dem,
                             b.val_prec_cata_unit_loca * b.num_unid_compr mto_comp,
                             b.val_prec_cata_unit_loca *
                             (b.num_unid_dema_real - b.num_unid_compr) mto_falt
                        FROM ped_solic_cabec     a,
                             ped_solic_posic     b,
                             mae_produ           c,
                             ped_tipo_solic_pais d,
                             ped_tipo_solic      e,
                             seg_pais            f,
                             mae_unida_negoc     g,
                             gen_i18n_sicc_pais  i
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND a.perd_oid_peri =
                             (SELECT x.oid_peri
                                FROM cra_perio       x,
                                     seg_perio_corpo y,
                                     bas_ctrl_fact   z
                               WHERE x.peri_oid_peri = y.oid_peri
                                 AND y.cod_peri = z.cod_peri
                                 AND z.ind_camp_act = 1
                                 AND z.sta_camp = 0)
                         AND i.attr_enti = 'MAE_UNIDA_NEGOC'
                         AND i.val_oid = g.oid_unid_nego
                         AND b.prod_oid_prod = c.oid_prod
                         AND a.modu_oid_modu <> 9
                         AND a.tspa_oid_tipo_soli_pais = d.oid_tipo_soli_pais
                         AND d.tsol_oid_tipo_soli = e.oid_tipo_soli
                         AND nvl(e.ind_soli_nega, 0) = 0
                         AND a.grpr_oid_grup_proc IN (3, 4)
                         AND a.fec_repo_falt IS NOT NULL
                         AND b.espo_oid_esta_posi <> 2
                         AND a.pais_oid_pais = f.oid_pais
                         AND c.uneg_oid_unid_nego = g.oid_unid_nego)
               GROUP BY cod_pais,
                        cod_unid_nego,
                        des_cort) xx
       WHERE xx.unidades_demandadas > 0;
  
    INSERT INTO fac_proye_falta_negoc
      SELECT cod_pais,
             (SELECT fec_proc
                FROM bas_ctrl_fact
               WHERE sta_camp = 0
                 AND ind_camp_act = 1) fec_fact,
             '999',
             'GENERAL',
             unidades_comprometidas + nvl((SELECT SUM(val_sald)
                                            FROM bel_stock,
                                                 mae_produ
                                           WHERE oid_prod = prod_oid_prod
                                             AND esme_oid_esta_merc = 2001
                                             AND cod_sap IN
                                                 (SELECT DISTINCT cod_prod
                                                    FROM fac_proye_falta_produ
                                                   WHERE fec_fact =
                                                         (SELECT fec_proc
                                                            FROM bas_ctrl_fact
                                                           WHERE sta_camp = 0
                                                             AND ind_camp_act = 1))),
                                          0) stock,
             unidades_demandadas,
             unidades_comprometidas,
             unidades_faltante,
             unidades_faltante / unidades_demandadas * 100 porc_falt,
             monto_demanda,
             monto_atendido,
             monto_faltante,
             decode(monto_demanda,
                    0,
                    0,
                    (monto_faltante / monto_demanda * 100)) porc_falt_monto,
             monto_faltante / SUM(monto_demanda) over(PARTITION BY cod_pais) * 100 por_falt_tota_fact,
             CASE
               WHEN (SELECT a.val_porc_maxi_perm
                       FROM bas_param_porce_falta a
                      WHERE a.cod_nego = '999') <
                    decode(monto_demanda,
                           0,
                           0,
                           (monto_faltante / monto_demanda * 100)) THEN
                'S'
               ELSE
                'N'
             END valor_critico,
             (SELECT cod_peri
                FROM bas_ctrl_fact
               WHERE sta_camp = 0
                 AND ind_camp_act = 1) periodo,
             oc,
             nvl((SELECT MAX(num_vers)
                   FROM fac_proye_falta_negoc
                  WHERE fec_fact = (SELECT fec_proc
                                      FROM bas_ctrl_fact
                                     WHERE sta_camp = 0
                                       AND ind_camp_act = 1)),
                 1) version,
             NULL
        FROM (SELECT cod_pais,
                     SUM(num_unid_compr) unidades_comprometidas,
                     SUM(num_unid_dema_real) unidades_demandadas,
                     SUM(falt) unidades_faltante,
                     SUM(mto_dem) monto_demanda,
                     SUM(mto_comp) monto_atendido,
                     SUM(mto_falt) monto_faltante,
                     COUNT(DISTINCT oid_soli_cabe) oc
                FROM (SELECT f.cod_pais,
                             a.oid_soli_cabe,
                             b.num_unid_compr,
                             b.num_unid_dema_real,
                             b.num_unid_dema_real - b.num_unid_compr falt,
                             b.val_prec_cata_unit_loca * b.num_unid_dema_real mto_dem,
                             b.val_prec_cata_unit_loca * b.num_unid_compr mto_comp,
                             b.val_prec_cata_unit_loca *
                             (b.num_unid_dema_real - b.num_unid_compr) mto_falt
                        FROM ped_solic_cabec     a,
                             ped_solic_posic     b,
                             ped_tipo_solic_pais d,
                             ped_tipo_solic      e,
                             seg_pais            f
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND a.perd_oid_peri =
                             (SELECT x.oid_peri
                                FROM cra_perio       x,
                                     seg_perio_corpo y,
                                     bas_ctrl_fact   z
                               WHERE x.peri_oid_peri = y.oid_peri
                                 AND y.cod_peri = z.cod_peri
                                 AND z.ind_camp_act = 1
                                 AND z.sta_camp = 0)
                         AND a.tspa_oid_tipo_soli_pais = d.oid_tipo_soli_pais
                         AND a.modu_oid_modu <> 9
                         AND d.tsol_oid_tipo_soli = e.oid_tipo_soli
                         AND nvl(e.ind_soli_nega, 0) = 0
                         AND a.grpr_oid_grup_proc IN (3, 4)
                         AND a.fec_repo_falt IS NOT NULL
                         AND b.espo_oid_esta_posi <> 2
                         AND a.pais_oid_pais = f.oid_pais)
               GROUP BY cod_pais) xx
       WHERE xx.unidades_demandadas > 0;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN;
      /*
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_repor_falta: ' || ls_sqlerrm);
      */
  END ped_pr_repor_falta;
  /***************************************************************************
  Descripcion       : Reporte Faltante
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_repor_falta_prol IS
  
  BEGIN
  
    INSERT INTO fac_proye_falta_produ_prol
    
      SELECT cod_pais,
             (SELECT fec_proc
                FROM bas_ctrl_fact
               WHERE sta_camp = 0
                 AND ind_camp_act = 1) fec_fact,
             cod_unid_nego,
             cod_sap,
             des_cort,
             cod_marc_prod,
             des_marc_prod,
             unidades_comprometidas + nvl((SELECT SUM(val_sald)
                                            FROM bel_stock,
                                                 mae_produ
                                           WHERE oid_prod = prod_oid_prod
                                             AND esme_oid_esta_merc = 2001
                                             AND cod_sap = xx.cod_sap),
                                          0) stock,
             unidades_demandadas,
             unidades_comprometidas,
             unidades_faltante,
             unidades_faltante / unidades_demandadas * 100 porc_falt,
             monto_demanda,
             monto_atendido,
             monto_faltante,
             decode(monto_demanda,
                    0,
                    0,
                    (monto_faltante / monto_demanda * 100)) porc_falt_monto,
             unidades_faltante / SUM(unidades_demandadas) over(PARTITION BY cod_unid_nego) * 100 por_falt_nego,
             monto_faltante / SUM(monto_demanda) over(PARTITION BY cod_pais) * 100 por_falt_tota_fact,
             NULL,
             oc,
             nvl((SELECT MAX(num_vers) + 1
                   FROM fac_proye_falta_produ_prol
                  WHERE fec_fact = (SELECT fec_proc
                                      FROM bas_ctrl_fact
                                     WHERE sta_camp = 0
                                       AND ind_camp_act = 1)),
                 1) version
        FROM (SELECT cod_pais,
                     cod_unid_nego,
                     cod_sap,
                     des_cort,
                     cod_marc_prod,
                     des_marc_prod,
                     SUM(num_unid_compr) unidades_comprometidas,
                     SUM(num_unid_dema_real) unidades_demandadas,
                     SUM(falt) unidades_faltante,
                     SUM(mto_dem) monto_demanda,
                     SUM(mto_comp) monto_atendido,
                     SUM(mto_falt) monto_faltante,
                     COUNT(DISTINCT oid_soli_cabe) oc
                FROM (SELECT f.cod_pais,
                             g.cod_unid_nego,
                             c.cod_sap,
                             i.val_i18n AS des_cort,
                             h.cod_marc_prod,
                             h.des_marc_prod,
                             a.oid_soli_cabe,
                             b.num_unid_compr,
                             b.num_unid_dema_real,
                             b.num_unid_dema_real - b.num_unid_compr falt,
                             b.val_prec_cata_unit_loca * b.num_unid_dema_real mto_dem,
                             b.val_prec_cata_unit_loca * b.num_unid_compr mto_comp,
                             b.val_prec_cata_unit_loca *
                             (b.num_unid_dema_real - b.num_unid_compr) mto_falt
                        FROM ped_solic_cabec       a,
                             ped_solic_posic       b,
                             mae_produ             c,
                             ped_tipo_solic_pais   d,
                             ped_tipo_solic        e,
                             seg_pais              f,
                             mae_unida_negoc       g,
                             seg_marca_produ       h,
                             gen_i18n_sicc_pais    i,
                             int_solic_conso_cabec j
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND a.oid_soli_cabe = j.soca_oid_soli_cabe_refe
                         AND j.ind_vali_prol = '1'
                         AND a.perd_oid_peri =
                             (SELECT x.oid_peri
                                FROM cra_perio       x,
                                     seg_perio_corpo y,
                                     bas_ctrl_fact   z
                               WHERE x.peri_oid_peri = y.oid_peri
                                 AND y.cod_peri = z.cod_peri
                                 AND z.ind_camp_act = 1
                                 AND z.sta_camp = 0)
                         AND i.attr_enti = 'MAE_PRODU'
                         AND i.val_oid = c.oid_prod
                         AND b.prod_oid_prod = c.oid_prod
                         AND a.modu_oid_modu <> 9
                         AND a.tspa_oid_tipo_soli_pais = d.oid_tipo_soli_pais
                         AND d.tsol_oid_tipo_soli = e.oid_tipo_soli
                         AND nvl(e.ind_soli_nega, 0) = 0
                         AND a.grpr_oid_grup_proc IN (3, 4)
                         AND a.fec_repo_falt IS NOT NULL
                         AND b.espo_oid_esta_posi <> 2
                         AND a.pais_oid_pais = f.oid_pais
                         AND c.uneg_oid_unid_nego = g.oid_unid_nego
                         AND c.mapr_oid_marc_prod = h.oid_marc_prod)
               GROUP BY cod_pais,
                        cod_unid_nego,
                        cod_sap,
                        des_cort,
                        cod_marc_prod,
                        des_marc_prod) xx
       WHERE xx.unidades_demandadas > 0;
  
    INSERT INTO fac_proye_falta_produ_prol
      (pais_cod_pais,
       fec_fact,
       num_vers,
       cod_prod,
       des_prod,
       cod_nego,
       cod_marc_prod,
       des_marc_prod,
       num_uni_stoc_dispo,
       tot_unid_dema,
       tot_unid_aten,
       tot_unid_falt,
       por_falt_unid_dema,
       tot_mont_unid_dema,
       tot_mont_unid_aten,
       tot_mont_unid_falt,
       por_falt_mont_dema,
       por_falt_nego,
       por_falt_tota_fact,
       ind_valo_criti,
       tot_soli_oc)
      SELECT pais_cod_pais,
             fec_fact,
             num_vers,
             cod_prod,
             des_prod,
             '999',
             cod_marc_prod,
             des_marc_prod,
             num_uni_stoc_dispo,
             tot_unid_dema,
             tot_unid_aten,
             tot_unid_falt,
             por_falt_unid_dema,
             tot_mont_unid_dema,
             tot_mont_unid_aten,
             tot_mont_unid_falt,
             por_falt_mont_dema,
             por_falt_nego,
             por_falt_tota_fact,
             ind_valo_criti,
             tot_soli_oc
        FROM fac_proye_falta_produ_prol a
       WHERE a.fec_fact = (SELECT fec_proc
                             FROM bas_ctrl_fact
                            WHERE sta_camp = 0
                              AND ind_camp_act = 1)
         AND a.num_vers =
             (SELECT MAX(num_vers)
                FROM fac_proye_falta_produ_prol
               WHERE fec_fact = (SELECT fec_proc
                                   FROM bas_ctrl_fact
                                  WHERE sta_camp = 0
                                    AND ind_camp_act = 1));
  
    INSERT INTO fac_proye_falta_negoc_prol
      SELECT cod_pais,
             (SELECT fec_proc
                FROM bas_ctrl_fact
               WHERE sta_camp = 0
                 AND ind_camp_act = 1) fec_fact,
             cod_unid_nego,
             des_cort,
             unidades_comprometidas +
             nvl((SELECT SUM(val_sald)
                   FROM bel_stock,
                        mae_produ,
                        mae_unida_negoc
                  WHERE oid_prod = prod_oid_prod
                    AND esme_oid_esta_merc = 2001
                    AND uneg_oid_unid_nego = oid_unid_nego
                    AND cod_unid_nego = xx.cod_unid_nego),
                 0) stock,
             unidades_demandadas,
             unidades_comprometidas,
             unidades_faltante,
             unidades_faltante / unidades_demandadas * 100 porc_falt,
             monto_demanda,
             monto_atendido,
             monto_faltante,
             decode(monto_demanda,
                    0,
                    0,
                    (monto_faltante / monto_demanda * 100)) porc_falt_monto,
             monto_faltante / SUM(monto_demanda) over(PARTITION BY cod_pais) * 100 por_falt_tota_fact,
             CASE
               WHEN (SELECT a.val_porc_maxi_perm
                       FROM bas_param_porce_falta a
                      WHERE a.cod_nego = cod_unid_nego) <
                    decode(monto_demanda,
                           0,
                           0,
                           (monto_faltante / monto_demanda * 100)) THEN
                'S'
               ELSE
                'N'
             END valor_critico,
             (SELECT cod_peri
                FROM bas_ctrl_fact
               WHERE sta_camp = 0
                 AND ind_camp_act = 1) periodo,
             oc,
             nvl((SELECT MAX(num_vers) + 1
                   FROM fac_proye_falta_negoc_prol
                  WHERE fec_fact = (SELECT fec_proc
                                      FROM bas_ctrl_fact
                                     WHERE sta_camp = 0
                                       AND ind_camp_act = 1)),
                 1) version,
             NULL
        FROM (SELECT cod_pais,
                     cod_unid_nego,
                     des_cort,
                     SUM(num_unid_compr) unidades_comprometidas,
                     SUM(num_unid_dema_real) unidades_demandadas,
                     SUM(falt) unidades_faltante,
                     SUM(mto_dem) monto_demanda,
                     SUM(mto_comp) monto_atendido,
                     SUM(mto_falt) monto_faltante,
                     COUNT(DISTINCT oid_soli_cabe) oc
                FROM (SELECT f.cod_pais,
                             g.cod_unid_nego,
                             i.val_i18n AS des_cort,
                             a.oid_soli_cabe,
                             b.num_unid_compr,
                             b.num_unid_dema_real,
                             b.num_unid_dema_real - b.num_unid_compr falt,
                             b.val_prec_cata_unit_loca * b.num_unid_dema_real mto_dem,
                             b.val_prec_cata_unit_loca * b.num_unid_compr mto_comp,
                             b.val_prec_cata_unit_loca *
                             (b.num_unid_dema_real - b.num_unid_compr) mto_falt
                        FROM ped_solic_cabec       a,
                             ped_solic_posic       b,
                             mae_produ             c,
                             ped_tipo_solic_pais   d,
                             ped_tipo_solic        e,
                             seg_pais              f,
                             mae_unida_negoc       g,
                             gen_i18n_sicc_pais    i,
                             int_solic_conso_cabec j
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND a.oid_soli_cabe = j.soca_oid_soli_cabe_refe
                         AND j.ind_vali_prol = '1'
                         AND a.perd_oid_peri =
                             (SELECT x.oid_peri
                                FROM cra_perio       x,
                                     seg_perio_corpo y,
                                     bas_ctrl_fact   z
                               WHERE x.peri_oid_peri = y.oid_peri
                                 AND y.cod_peri = z.cod_peri
                                 AND z.ind_camp_act = 1
                                 AND z.sta_camp = 0)
                         AND i.attr_enti = 'MAE_UNIDA_NEGOC'
                         AND i.val_oid = g.oid_unid_nego
                         AND b.prod_oid_prod = c.oid_prod
                         AND a.modu_oid_modu <> 9
                         AND a.tspa_oid_tipo_soli_pais = d.oid_tipo_soli_pais
                         AND d.tsol_oid_tipo_soli = e.oid_tipo_soli
                         AND nvl(e.ind_soli_nega, 0) = 0
                         AND a.grpr_oid_grup_proc IN (3, 4)
                         AND a.fec_repo_falt IS NOT NULL
                         AND b.espo_oid_esta_posi <> 2
                         AND a.pais_oid_pais = f.oid_pais
                         AND c.uneg_oid_unid_nego = g.oid_unid_nego)
               GROUP BY cod_pais,
                        cod_unid_nego,
                        des_cort) xx
       WHERE xx.unidades_demandadas > 0;
  
    INSERT INTO fac_proye_falta_negoc_prol
      SELECT cod_pais,
             (SELECT fec_proc
                FROM bas_ctrl_fact
               WHERE sta_camp = 0
                 AND ind_camp_act = 1) fec_fact,
             '999',
             'GENERAL',
             unidades_comprometidas + nvl((SELECT SUM(val_sald)
                                            FROM bel_stock,
                                                 mae_produ
                                           WHERE oid_prod = prod_oid_prod
                                             AND esme_oid_esta_merc = 2001
                                             AND cod_sap IN
                                                 (SELECT DISTINCT cod_prod
                                                    FROM fac_proye_falta_produ
                                                   WHERE fec_fact =
                                                         (SELECT fec_proc
                                                            FROM bas_ctrl_fact
                                                           WHERE sta_camp = 0
                                                             AND ind_camp_act = 1))),
                                          0) stock,
             unidades_demandadas,
             unidades_comprometidas,
             unidades_faltante,
             unidades_faltante / unidades_demandadas * 100 porc_falt,
             monto_demanda,
             monto_atendido,
             monto_faltante,
             decode(monto_demanda,
                    0,
                    0,
                    (monto_faltante / monto_demanda * 100)) porc_falt_monto,
             monto_faltante / SUM(monto_demanda) over(PARTITION BY cod_pais) * 100 por_falt_tota_fact,
             CASE
               WHEN (SELECT a.val_porc_maxi_perm
                       FROM bas_param_porce_falta a
                      WHERE a.cod_nego = '999') <
                    decode(monto_demanda,
                           0,
                           0,
                           (monto_faltante / monto_demanda * 100)) THEN
                'S'
               ELSE
                'N'
             END valor_critico,
             (SELECT cod_peri
                FROM bas_ctrl_fact
               WHERE sta_camp = 0
                 AND ind_camp_act = 1) periodo,
             oc,
             nvl((SELECT MAX(num_vers)
                   FROM fac_proye_falta_negoc_prol
                  WHERE fec_fact = (SELECT fec_proc
                                      FROM bas_ctrl_fact
                                     WHERE sta_camp = 0
                                       AND ind_camp_act = 1)),
                 1) version,
             NULL
        FROM (SELECT cod_pais,
                     SUM(num_unid_compr) unidades_comprometidas,
                     SUM(num_unid_dema_real) unidades_demandadas,
                     SUM(falt) unidades_faltante,
                     SUM(mto_dem) monto_demanda,
                     SUM(mto_comp) monto_atendido,
                     SUM(mto_falt) monto_faltante,
                     COUNT(DISTINCT oid_soli_cabe) oc
                FROM (SELECT f.cod_pais,
                             a.oid_soli_cabe,
                             b.num_unid_compr,
                             b.num_unid_dema_real,
                             b.num_unid_dema_real - b.num_unid_compr falt,
                             b.val_prec_cata_unit_loca * b.num_unid_dema_real mto_dem,
                             b.val_prec_cata_unit_loca * b.num_unid_compr mto_comp,
                             b.val_prec_cata_unit_loca *
                             (b.num_unid_dema_real - b.num_unid_compr) mto_falt
                        FROM ped_solic_cabec       a,
                             ped_solic_posic       b,
                             ped_tipo_solic_pais   d,
                             ped_tipo_solic        e,
                             seg_pais              f,
                             int_solic_conso_cabec g
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND a.oid_soli_cabe = g.soca_oid_soli_cabe_refe
                         AND g.ind_vali_prol = '1'
                         AND a.perd_oid_peri =
                             (SELECT x.oid_peri
                                FROM cra_perio       x,
                                     seg_perio_corpo y,
                                     bas_ctrl_fact   z
                               WHERE x.peri_oid_peri = y.oid_peri
                                 AND y.cod_peri = z.cod_peri
                                 AND z.ind_camp_act = 1
                                 AND z.sta_camp = 0)
                         AND a.tspa_oid_tipo_soli_pais = d.oid_tipo_soli_pais
                         AND a.modu_oid_modu <> 9
                         AND d.tsol_oid_tipo_soli = e.oid_tipo_soli
                         AND nvl(e.ind_soli_nega, 0) = 0
                         AND a.grpr_oid_grup_proc IN (3, 4)
                         AND a.fec_repo_falt IS NOT NULL
                         AND b.espo_oid_esta_posi <> 2
                         AND a.pais_oid_pais = f.oid_pais)
               GROUP BY cod_pais) xx
       WHERE xx.unidades_demandadas > 0;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN;
      /*
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_repor_falta: ' || ls_sqlerrm);
      */
  END ped_pr_repor_falta_prol;
  /***************************************************************************
  Descripcion       : Asigancion de Stocks. Integracion Familia Protegida
  Fecha Creacion    : 15/06/2010
  Autor             : Jorge Yepez
  Fecha Actualiza   : 30/06/2011
  Autor Actualiza   : Carlos Diaz Valverde
  ***************************************************************************/
  PROCEDURE ped_pr_proce_asign_stock
  (
    p_codigopais IN VARCHAR2,
    p_fechafact  IN VARCHAR2,
    p_usuario    IN VARCHAR2
  ) IS
  
    -- Variables
    vartmp                NUMBER;
    lsindicadorfamse      VARCHAR2(1);
    lv_ind_ejec_gast_admi VARCHAR2(1) := ccc_pkg_gener.ccc_fn_obtie_param_gener('IndicadorEjecucionGastoAdministrativo');
    lv_ind_inte_mora      VARCHAR2(1) := ccc_pkg_gener.ccc_fn_obtie_param_gener('IndicadorEjecucionInteresesDeMora');
    lv_ind_ejec_flex      VARCHAR2(1) := ccc_pkg_gener.ccc_fn_obtie_param_gener('IndicadorActivacionFlexipagoGP3');
    lsrecupoblig          VARCHAR2(1) := sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                              'STO_RECUP_OBLIG');
    lsprol                VARCHAR2(1) := sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                              'STO_PROL');
  
    lsrepfal VARCHAR2(1) := sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                 'STO_REPOR_FALTA');
  
  BEGIN
  
    -- Logica Familia Segura ------------------------------------- --
    BEGIN
    
      SELECT val_para
        INTO lsindicadorfamse
        FROM bas_param_pais
       WHERE cod_pais = p_codigopais
         AND cod_sist = 'SGR'
         AND cod_para = '002'
         AND nom_para = 'indFamiliaProtegida';
    
      IF (lsindicadorfamse = '1') THEN
        sgr_pkg_proce_famse.sgr_pr_consu_poliz_vigen(p_codigopais,
                                                     p_usuario);
        ccc_pkg_proce.ccc_pr_gener_cargo_famil_segur(p_codigopais,
                                                     to_date(p_fechafact,
                                                             'dd/MM/yyyy'),
                                                     p_usuario);
      END IF;
    
    EXCEPTION
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM, 1, 250);
        raise_application_error(-20123,
                                'ERROR PED_PR_PROCE_ASIGN_STOCK - FAMILIA SEGURA: ' ||
                                ls_sqlerrm);
    END;
  
    -- --
  
    -- Inicializar Variables  ---------------------------------------
    vartmp := 0;
  
    -- Logica Asignacion Stocks -------------------------------------
    BEGIN
    
      SELECT DISTINCT 1
        INTO vartmp
        FROM ped_solic_cabec     a,
             ped_tipo_solic_pais c,
             ped_tipo_solic      d
       WHERE a.perd_oid_peri = (SELECT x.oid_peri
                                  FROM cra_perio       x,
                                       seg_perio_corpo y,
                                       bas_ctrl_fact   z
                                 WHERE x.peri_oid_peri = y.oid_peri
                                   AND y.cod_peri = z.cod_peri
                                   AND z.ind_camp_act = 1
                                   AND z.sta_camp = 0)
         AND a.grpr_oid_grup_proc IN (3)
            /*
            AND   a.fec_prog_fact =
                  (
                    SELECT    z.fec_proc
                                     FROM bas_ctrl_fact z
                                    WHERE z.ind_camp_act = 1
                      AND     z.sta_camp = 0
                  )
            */
         AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
         AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
         AND nvl(d.ind_soli_nega, 0) = 0
         AND a.fec_repo_falt IS NULL
         AND a.modu_oid_modu = 9;
    
    EXCEPTION
      WHEN OTHERS THEN
        vartmp := 0;
    END;
  
    IF nvl(lsprol, 'N') = 'S' THEN
      UPDATE ped_solic_cabec a
         SET a.fec_prog_fact      = to_date('01/01/2000', 'DD/MM/YYYY'),
             a.esso_oid_esta_soli = 6
       WHERE a.ind_rece_onli = 1
         AND a.fec_fact IS NULL
         AND a.esso_oid_esta_soli <> 8
         AND a.grpr_oid_grup_proc = 3
         AND a.perd_oid_peri = (SELECT x.oid_peri
                                  FROM cra_perio       x,
                                       seg_perio_corpo y,
                                       bas_ctrl_fact   z
                                 WHERE x.peri_oid_peri = y.oid_peri
                                   AND y.cod_peri = z.cod_peri
                                   AND z.ind_camp_act = 1
                                   AND z.sta_camp = 0)
         AND a.ind_oc = 1;
    
      UPDATE ped_solic_cabec a
         SET esso_oid_esta_soli = 1,
             a.fec_prog_fact   =
             (SELECT z.fec_proc
                FROM bas_ctrl_fact z
               WHERE z.ind_camp_act = 1
                 AND z.sta_camp = 0)
       WHERE a.ind_rece_onli = 1
         AND a.esso_oid_esta_soli <> 8
         AND a.fec_fact IS NULL
         AND a.grpr_oid_grup_proc = 3
         AND a.perd_oid_peri = (SELECT x.oid_peri
                                  FROM cra_perio       x,
                                       seg_perio_corpo y,
                                       bas_ctrl_fact   z
                                 WHERE x.peri_oid_peri = y.oid_peri
                                   AND y.cod_peri = z.cod_peri
                                   AND z.ind_camp_act = 1
                                   AND z.sta_camp = 0)
         AND a.ind_oc = 1
         AND NOT EXISTS
       (SELECT 1
                FROM int_solic_conso_cabec x1,
                     sto_detal_docum_excep y1
               WHERE x1.soca_oid_soli_cabe_refe = a.oid_soli_cabe
                 AND x1.sec_nume_docu = y1.sec_nume_docu
                 AND y1.ind_apro = 0)
         AND (SELECT z.fec_proc
                FROM bas_ctrl_fact z
               WHERE z.ind_camp_act = 1
                 AND z.sta_camp = 0) >=
             (SELECT fec_inic
                FROM cra_crono x,
                     cra_activ z1
               WHERE x.zzon_oid_zona = a.zzon_oid_zona
                 AND x.cact_oid_acti = z1.oid_acti
                 AND z1.pais_oid_pais = a.pais_oid_pais
                 AND z1.cod_acti = 'FA'
                 AND x.perd_oid_peri = a.perd_oid_peri);
    
    END IF;
  
    ped_pr_ini_stock;
    ped_pr_stock;
    IF nvl(vartmp, 0) = 0 OR lsrepfal = 'S' THEN
      ped_pr_repor_falta;
      BEGIN
        ped_pr_repor_falta_prol;
      EXCEPTION
        WHEN OTHERS THEN
          NULL;
      END;
    END IF;
  
    COMMIT;
    -- Logica Gasto Administrativo ------------------------------------- --
    BEGIN
    
      IF lv_ind_ejec_gast_admi = '1' THEN
        ccc_pkg_proce.ccc_pr_gener_cargo_gasto_admin(p_usuario);
      END IF;
    
    EXCEPTION
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM, 1, 250);
        raise_application_error(-20123,
                                'ERROR PED_PR_PROCE_ASIGN_STOCK - GASTO ADMINISTRATIVO: ' ||
                                ls_sqlerrm);
    END;
  
    COMMIT;
  
    -- Logica Intereses de Mora ------------------------------------- --
    BEGIN
    
      IF lv_ind_inte_mora = '1' THEN
        ccc_pkg_proce.ccc_pr_gener_inter_mora(p_codigopais);
      END IF;
    
    EXCEPTION
      WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(SQLERRM, 1, 250);
        raise_application_error(-20123,
                                'ERROR PED_PR_PROCE_INTER_MORA - INTERESES DE MORA: ' ||
                                ls_sqlerrm);
    END;
  
    --- Inicio Logica Flexipago GP3 -------------------------------------
  
    IF nvl(vartmp, 0) = 0 THEN
    
      BEGIN
      
        IF lv_ind_ejec_flex = 'S' THEN
          flx_pkg_proce.flx_pr_proce_flexi_gp3;
        END IF;
      
      EXCEPTION
      
        WHEN OTHERS THEN
          ln_sqlcode := SQLCODE;
          ls_sqlerrm := substr(SQLERRM, 1, 250);
          raise_application_error(-20123,
                                  'ERROR PED_PR_PROCE_ASIGN_STOCK - FLEXIPAGO: ' ||
                                  ls_sqlerrm);
      END;
    
    END IF;
  
    --- Fin Logica Flexipago GP3 -------------------------------------
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR PED_PR_PROCE_ASIGN_STOCK: ' ||
                              ls_sqlerrm);
  END ped_pr_proce_asign_stock;

  /***************************************************************************
  Descripcion       : Calculo de Descuentos
  Fecha Creacion    : 31/01/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_calcu_desc(p_oidcabe IN NUMBER) IS
  
    w_filas     NUMBER(12);
    varoiddto   NUMBER(12);
    varoidcabe  NUMBER(12);
    varoidposic NUMBER(12);
    varbc       NUMBER(12, 2);
  
    CURSOR descuentos IS
      WITH descact AS
       (SELECT oid_desc,
               bc
          FROM (SELECT DISTINCT oid_desc,
                                dto_descu.num_desc_corr,
                                bac.oid_cabe,
                                nvl((SELECT SUM(nvl(val_prec_cata_unit_loca, 0) *
                                               nvl(num_unid_dema_real, 0))
                                      FROM ped_solic_posic
                                     WHERE soca_oid_soli_cabe =
                                           x.oid_soli_cabe
                                       AND oid_soli_posi IN
                                           (SELECT DISTINCT oid_soli_posi
                                              FROM ped_solic_posic      psc,
                                                   dto_base_calcu_detal bad,
                                                   pre_ofert_detal      pod,
                                                   mae_produ            mp,
                                                   pre_tipo_ofert       pto
                                             WHERE soca_oid_soli_cabe =
                                                   x.oid_soli_cabe
                                               AND bac.oid_cabe =
                                                   bad.bcca_oid_cabe
                                               AND mp.oid_prod =
                                                   psc.prod_oid_prod
                                               AND psc.ofde_oid_deta_ofer =
                                                   pod.oid_deta_ofer
                                               AND psc.espo_oid_esta_posi <> 2
                                               AND pod.tofe_oid_tipo_ofer =
                                                   pto.oid_tipo_ofer
                                               AND pto.ind_apor_mont_esca = 1
                                               AND pod.tofe_oid_tipo_ofer NOT IN
                                                   (SELECT det.tofe_oid_tipo_ofer
                                                      FROM dto_base_calcu_detal det
                                                     WHERE det.bcca_oid_cabe =
                                                           bac.oid_cabe)
                                               AND decode(bad.mapr_oid_marc_prod,
                                                          NULL,
                                                          mp.mapr_oid_marc_prod,
                                                          bad.mapr_oid_marc_prod) =
                                                   mp.mapr_oid_marc_prod)),
                                    0) bc
                  FROM dto_descu,
                       ped_solic_cabec x,
                       ped_tipo_solic_pais y,
                       ped_tipo_solic z,
                       dto_base_calcu_cabec bac,
                       cra_perio perio_ini,
                       cra_perio perio_fin,
                       cra_perio perio_solicitud,
                       dto_alcan_dto_admin,
                       dto_descu_acces,
                       dto_descu_subac,
                       v_mae_tipif_clien,
                       dto_alcan_dto_clasi_clien dtoclasi,
                       (SELECT s.dcto_oid_desc        oiddesc,
                               rel.ticl_oid_tipo_clie tipo,
                               s.sbti_oid_subt_clie   subtipo
                          FROM dto_descu_subti_clien s,
                               mae_subti_clien       rel
                         WHERE rel.oid_subt_clie = s.sbti_oid_subt_clie
                        UNION
                        SELECT t.dcto_oid_desc,
                               t.ticl_oid_tipo_clie,
                               NULL
                          FROM dto_descu_tipo_clien t
                         WHERE t.ticl_oid_tipo_clie NOT IN
                               (SELECT DISTINCT rel.ticl_oid_tipo_clie
                                  FROM dto_descu_subti_clien s,
                                       mae_subti_clien       rel
                                 WHERE rel.oid_subt_clie = s.sbti_oid_subt_clie
                                   AND s.dcto_oid_desc = t.dcto_oid_desc)) tipo_subtipo_descuento
                 WHERE dto_descu.ind_acti = 1
                   AND x.oid_soli_cabe = p_oidcabe
                   AND x.tspa_oid_tipo_soli_pais = y.oid_tipo_soli_pais
                   AND y.tsol_oid_tipo_soli = z.oid_tipo_soli
                   AND dto_descu.pais_oid_pais = x.pais_oid_pais
                   AND dto_descu.marc_oid_marc = z.marc_oid_marc
                   AND bac.dcto_oid_desc = dto_descu.oid_desc
                   AND perio_solicitud.oid_peri = x.perd_oid_peri
                   AND perio_ini.oid_peri = dto_descu.perd_oid_peri
                   AND perio_ini.fec_inic <= perio_solicitud.fec_inic
                   AND perio_fin.oid_peri(+) =
                       dto_descu.perd_oid_peri_limi_fin
                   AND ((perio_fin.fec_fina >= perio_solicitud.fec_fina) OR
                       perio_fin.fec_fina IS NULL)
                   AND dto_descu.oid_desc =
                       dto_alcan_dto_admin.dcto_oid_desc(+)
                   AND (dto_descu.ind_naci = 1 OR
                       (dto_alcan_dto_admin.zzon_oid_zona = NULL OR
                       dto_alcan_dto_admin.zzon_oid_zona IS NULL) AND
                       (dto_alcan_dto_admin.zorg_oid_regi = NULL OR
                       dto_alcan_dto_admin.zorg_oid_regi IS NULL) AND
                       (dto_alcan_dto_admin.zsgv_oid_subg_vent = NULL))
                   AND dto_descu.oid_desc = dto_descu_acces.dcto_oid_desc(+)
                   AND (dto_descu_acces.acce_oid_acce IS NULL OR
                       dto_descu_acces.acce_oid_acce = z.acce_oid_acce)
                   AND dto_descu.oid_desc = dto_descu_subac.dcto_oid_desc(+)
                   AND (dto_descu_subac.sbac_oid_sbac IS NULL OR
                       dto_descu_subac.sbac_oid_sbac = x.sbac_oid_sbac)
                   AND v_mae_tipif_clien.clie_oid_clie = x.clie_oid_clie
                   AND dto_descu.oid_desc = tipo_subtipo_descuento.oiddesc(+)
                   AND (((SELECT dadc.clie_oid_clie
                            FROM dto_alcan_dto_clien dadc
                           WHERE dadc.dcto_oid_desc = oid_desc
                             AND dadc.clie_oid_clie = x.clie_oid_clie) IS NOT NULL AND
                       tipo_subtipo_descuento.oiddesc IS NULL) OR
                       tipo_subtipo_descuento.oiddesc IS NOT NULL)
                   AND (tipo_subtipo_descuento.tipo =
                       v_mae_tipif_clien.ticl_oid_tipo_clie OR
                       tipo_subtipo_descuento.tipo IS NULL)
                   AND (tipo_subtipo_descuento.subtipo =
                       v_mae_tipif_clien.sbti_oid_subt_clie OR
                       tipo_subtipo_descuento.subtipo IS NULL)
                   AND dto_descu.oid_desc = dtoclasi.dcto_oid_desc(+)
                   AND (dtoclasi.clas_oid_clas IS NULL OR
                       dtoclasi.clas_oid_clas =
                       v_mae_tipif_clien.clas_oid_clas)
                   AND (dtoclasi.tccl_oid_tipo_clas IS NULL OR
                       dtoclasi.tccl_oid_tipo_clas =
                       v_mae_tipif_clien.tccl_oid_tipo_clasi)
                 ORDER BY num_desc_corr))
      SELECT DISTINCT oid_soli_posi,
                      bac.oid_cabe,
                      descact.oid_desc,
                      nvl((SELECT val_porc_desc
                            FROM dto_escln a
                           WHERE a.baca_oid_cabe = bac.oid_cabe
                             AND nvl(val_desd, 0) <= bc
                             AND nvl(val_hast, 99999999999) >= bc),
                          '0') porc
        FROM ped_solic_posic      xx,
             dto_base_aplic_cabec bac,
             dto_base_aplic_detal det,
             pre_ofert_detal      pod,
             mae_produ            mp,
             pre_tipo_ofert       pto,
             descact
       WHERE soca_oid_soli_cabe = p_oidcabe
         AND bac.dcto_oid_desc = descact.oid_desc
         AND bac.oid_cabe = det.baca_oid_cabe
         AND xx.ofde_oid_deta_ofer = pod.oid_deta_ofer
         AND xx.prod_oid_prod = mp.oid_prod
         AND decode(det.gene_oid_gene,
                    NULL,
                    nvl(mp.gene_oid_gene, 0),
                    det.gene_oid_gene) = nvl(mp.gene_oid_gene, 0)
         AND decode(det.sgen_oid_supe_gene,
                    NULL,
                    nvl(mp.sgen_oid_supe_gene, 0),
                    det.sgen_oid_supe_gene) = nvl(mp.sgen_oid_supe_gene, 0)
         AND decode(det.uneg_oid_unid_nego,
                    NULL,
                    nvl(mp.uneg_oid_unid_nego, 0),
                    det.uneg_oid_unid_nego) = nvl(mp.uneg_oid_unid_nego, 0)
         AND decode(det.nego_oid_nego,
                    NULL,
                    nvl(mp.nego_oid_nego, 0),
                    det.nego_oid_nego) = nvl(mp.nego_oid_nego, 0)
         AND decode(det.tofe_oid_tipo_ofer,
                    NULL,
                    pod.tofe_oid_tipo_ofer,
                    det.tofe_oid_tipo_ofer) = pod.tofe_oid_tipo_ofer
         AND decode(det.mapr_oid_marc_prod,
                    NULL,
                    nvl(mp.mapr_oid_marc_prod, 0),
                    det.mapr_oid_marc_prod) = nvl(mp.mapr_oid_marc_prod, 0)
         AND xx.val_prec_cata_unit_loca > 0
         AND pod.tofe_oid_tipo_ofer = pto.oid_tipo_ofer
         AND pto.ind_comi = 1
         AND det.ind_excl_tipo_ofer = 0
      UNION
      SELECT DISTINCT oid_soli_posi,
                      bac.oid_cabe,
                      descact.oid_desc,
                      nvl((SELECT val_porc_desc
                            FROM dto_escln a
                           WHERE a.baca_oid_cabe = bac.oid_cabe
                             AND nvl(val_desd, 0) <= bc
                             AND nvl(val_hast, 99999999999) >= bc),
                          '0') porc
        FROM ped_solic_posic      xx,
             dto_base_aplic_cabec bac,
             dto_base_aplic_detal det,
             pre_ofert_detal      pod,
             mae_produ            mp,
             pre_tipo_ofert       pto,
             descact
       WHERE soca_oid_soli_cabe = p_oidcabe AND
       bac.dcto_oid_desc = descact.oid_desc AND
       bac.oid_cabe = det.baca_oid_cabe AND
       xx.ofde_oid_deta_ofer = pod.oid_deta_ofer AND
       xx.prod_oid_prod = mp.oid_prod AND
       decode(det.gene_oid_gene,
              NULL,
              nvl(mp.gene_oid_gene, 0),
              det.gene_oid_gene) = nvl(mp.gene_oid_gene, 0) AND
       decode(det.sgen_oid_supe_gene,
              NULL,
              nvl(mp.sgen_oid_supe_gene, 0),
              det.sgen_oid_supe_gene) = nvl(mp.sgen_oid_supe_gene, 0) AND
       decode(det.uneg_oid_unid_nego,
              NULL,
              nvl(mp.uneg_oid_unid_nego, 0),
              det.uneg_oid_unid_nego) = nvl(mp.uneg_oid_unid_nego, 0) AND
       decode(det.nego_oid_nego,
              NULL,
              nvl(mp.nego_oid_nego, 0),
              det.nego_oid_nego) = nvl(mp.nego_oid_nego, 0) AND
       xx.val_prec_cata_unit_loca > 0 AND
       pod.tofe_oid_tipo_ofer NOT IN
       (SELECT nvl(det1.tofe_oid_tipo_ofer, 0)
          FROM dto_base_aplic_detal det1
         WHERE det1.baca_oid_cabe = bac.oid_cabe) AND
       pod.tofe_oid_tipo_ofer = pto.oid_tipo_ofer AND
       pto.ind_comi = 1 AND
       decode(det.mapr_oid_marc_prod,
              NULL,
              nvl(mp.mapr_oid_marc_prod, 0),
              det.mapr_oid_marc_prod) = nvl(mp.mapr_oid_marc_prod, 0) AND
       det.ind_excl_tipo_ofer = 1;
  
    TYPE descuentosrec IS RECORD(
      varoidposic NUMBER(12),
      varoidcabe  NUMBER(12),
      varoiddto   NUMBER(12),
      varbc       NUMBER(12));
  
    TYPE descuentosrectab IS TABLE OF descuentosrec;
    descuentosrecord descuentosrectab;
  
  BEGIN
  
    w_filas := 100000;
  
    OPEN descuentos;
    LOOP
      FETCH descuentos BULK COLLECT
        INTO descuentosrecord LIMIT w_filas;
      IF descuentosrecord.count > 0 THEN
        FOR x IN descuentosrecord.first .. descuentosrecord.last
        LOOP
        
          varoidposic := descuentosrecord(x).varoidposic;
          varoidcabe  := descuentosrecord(x).varoidcabe;
          varoiddto   := descuentosrecord(x).varoiddto;
          varbc       := descuentosrecord(x).varbc;
        
          UPDATE ped_solic_posic
          --set val_porc_desc=varporcentaje
             SET val_lote_prod = varbc
           WHERE oid_soli_posi = varoidposic;
        
        END LOOP;
      END IF;
      EXIT WHEN descuentos%NOTFOUND;
    END LOOP;
    CLOSE descuentos;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_ini_stock: ' || ls_sqlerrm);
  END ped_pr_calcu_desc;

  /***************************************************************************
  Descripcion       : Recuperacion Obligatoria
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_recup_oblig(p_oidcabe NUMBER) IS
  
  BEGIN
  
    -- Empieza el proceso de Recuperaciones Obligatorias
  
    INSERT INTO ped_solic_posic
      (oid_soli_posi,
       cod_posi,
       num_unid_dema,
       num_unid_por_aten,
       val_tasa_impu,
       soca_oid_soli_cabe,
       taim_oid_tasa_impu,
       tpos_oid_tipo_posi,
       prod_oid_prod,
       fopa_oid_form_pago,
       val_prec_cata_unit_loca,
       val_prec_cont_unit_loca,
       val_prec_cata_unit_docu,
       val_prec_conta_unit_docu,
       val_prec_fact_unit_loca,
       val_prec_fact_unit_docu,
       val_prec_sin_impu_unit_loca,
       val_prec_sin_impu_unit_docu,
       val_prec_sin_impu_tota_docu,
       val_impo_desc_unit_loca,
       val_impo_desc_unit_docu,
       val_prec_neto_unit_loca,
       val_prec_neto_tota_docu,
       val_prec_neto_unit_docu,
       val_prec_tota_tota_loca,
       val_prec_tota_tota_docu,
       val_impo_impu_unit_loca,
       val_impo_impu_unit_docu,
       val_impo_desc_tota_docu,
       val_impo_impu_tota_loca,
       val_impo_impu_tota_docu,
       val_impo_desc_tota_loca,
       val_prec_tota_unit_loca,
       val_prec_tota_unit_docu,
       val_prec_cont_tota_loca,
       val_prec_cata_tota_loca,
       val_prec_cata_tota_docu,
       val_prec_cont_tota_docu,
       val_porc_desc,
       val_prec_cata_tota_loca_unid,
       num_unid_dema_real,
       num_unid_compr,
       val_prec_fact_tota_loca,
       val_prec_fact_tota_docu,
       val_prec_sin_impu_tota_loca,
       val_prec_neto_tota_loca,
       ofde_oid_deta_ofer,
       espo_oid_esta_posi,
       stpo_oid_subt_posi,
       val_codi_vent,
       ind_no_impr,
       sopo_oid_soli_posi)
      SELECT ped_sopo_seq.nextval,
             (SELECT MAX(cod_posi)
                FROM ped_solic_posic
               WHERE soca_oid_soli_cabe = a.oid_soli_cabe) + rownum,
             k.num_unid_dema_real - k.num_unid_aten,
             k.num_unid_dema_real - k.num_unid_aten,
             0,
             a.oid_soli_cabe,
             NULL,
             2,
             c.prod_oid_prod,
             c.fopa_oid_form_pago,
             c.precio_unitario,
             decode(c.imp_prec_cata, 0, c.imp_prec_posi, 0),
             c.precio_unitario,
             decode(c.imp_prec_cata, 0, c.imp_prec_posi, 0),
             0,
             0,
             0,
             0,
             0,
             c.precio_unitario * ((nvl(k.val_porc_desc, 0)) / 100), --0,
             c.precio_unitario * ((nvl(k.val_porc_desc, 0)) / 100),
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             /*c.precio_unitario * (k.num_unid_dema_real - k.num_unid_aten) *
             ((nvl(k.val_porc_desc,
                   0)) / 100),*/
             0, --
             0,
             0,
             0,
             0,
             0,
             0,
             k.val_porc_desc,
             0,
             k.num_unid_dema_real - k.num_unid_aten,
             k.num_unid_dema_real - k.num_unid_aten,
             0,
             0,
             0,
             0,
             c.oid_deta_ofer,
             4,
             2,
             c.val_codi_vent,
             0,
             k.oid_soli_posi
      --l.oid_soli_cabe, l.fec_fact, l.perd_oid_peri, c.val_codi_vent, j.val_codi_vent, k.num_unid_dema_real-k.num_unid_aten, k.val_codi_vent, k.oid_soli_posi
        FROM ped_solic_cabec       a,
             pre_ofert_detal       c,
             pre_matri_factu       d,
             pre_matri_factu_cabec e,
             pre_matri_recup       g,
             pre_matri_factu       h,
             pre_matri_factu_cabec i,
             pre_ofert_detal       j,
             ped_solic_posic       k,
             ped_solic_cabec       l
       WHERE a.oid_soli_cabe = p_oidcabe
         AND c.oid_deta_ofer = d.ofde_oid_deta_ofer
         AND d.mfca_oid_cabe = e.oid_cabe
         AND e.perd_oid_peri = a.perd_oid_peri
         AND d.oid_matr_fact = g.mafa_oid_cod_ppal
         AND g.mafa_oid_cod_recu = h.oid_matr_fact
         AND h.mfca_oid_cabe = i.oid_cabe
         AND i.perd_oid_peri = l.perd_oid_peri
         AND h.ofde_oid_deta_ofer = j.oid_deta_ofer
         AND j.oid_deta_ofer = k.ofde_oid_deta_ofer
         AND k.soca_oid_soli_cabe = l.oid_soli_cabe
         AND a.clie_oid_clie = l.clie_oid_clie
         AND k.num_unid_dema_real > k.num_unid_aten
         AND k.espo_oid_esta_posi = 4
         AND g.num_unid_maxi_recu IS NULL
         and nvl(g.ind_acti,'1')='1';
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_recup_oblig: ' || ls_sqlerrm);
  END ped_pr_recup_oblig;

  /***************************************************************************
  Descripcion       : Limite de Venta
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_limit_venta
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    p_oidoferdetal NUMBER,
    p_unidades     NUMBER
  ) RETURN NUMBER IS
  
    v_limite NUMBER := 0;
  
  BEGIN
  
    SELECT MAX(h.val_limi_ctrl_vent)
      INTO v_limite
      FROM ped_gesti_stock      h,
           zon_zona             k,
           zon_regio            l,
           mae_clien_tipo_subti m,
           mae_clien_clasi      n,
           zon_terri_admin      q,
           zon_secci            r
     WHERE h.ofde_oid_deta_ofer = p_oidoferdetal
       AND h.val_limi_ctrl_vent IS NOT NULL
       AND h.val_limi_ctrl_vent < p_unidades
       AND m.clie_oid_clie = p_oidclie
       AND q.oid_terr_admi = p_oidterradmin
       AND q.zscc_oid_secc = r.oid_secc
       AND r.zzon_oid_zona = k.oid_zona
       AND k.zorg_oid_regi = l.oid_regi
       AND m.oid_clie_tipo_subt = n.ctsu_oid_clie_tipo_subt
       AND (m.ticl_oid_tipo_clie = h.ticl_oid_tipo_clie OR
           h.ticl_oid_tipo_clie IS NULL)
       AND (m.sbti_oid_subt_clie = h.sbti_oid_subt_clie OR
           h.sbti_oid_subt_clie IS NULL)
       AND (n.tccl_oid_tipo_clasi = h.tccl_oid_tipo_clas OR
           h.tccl_oid_tipo_clas IS NULL)
       AND (n.clas_oid_clas = h.clas_oid_clas OR h.clas_oid_clas IS NULL)
       AND (l.oid_regi = h.zorg_oid_regi OR h.zorg_oid_regi IS NULL)
       AND (k.oid_zona = h.zzon_oid_zona OR h.zzon_oid_zona IS NULL)
       and nvl(h.ind_acti,'1')='1'
       ;
  
    RETURN v_limite;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN p_unidades;
  END ped_fn_limit_venta;

  /***************************************************************************
  Descripcion       : Obtiene Monto Minimo por cliente
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_obtie_mminim
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    p_nominal      OUT NUMBER,
    p_nivel1       OUT NUMBER,
    p_nivel2       OUT NUMBER
  )
  --RETURN NUMBER
   IS
  
  BEGIN
  
    SELECT MIN(h.val_niv1),
           MIN(h.val_mont_mini_nomi),
           MIN(h.val_niv2)
      INTO p_nivel1,
           p_nominal,
           p_nivel2
      FROM ped_monto_minim      h,
           zon_zona             k,
           zon_regio            l,
           mae_clien_tipo_subti m,
           mae_clien_clasi      n,
           zon_terri_admin      q,
           zon_secci            r
     WHERE m.clie_oid_clie = p_oidclie
       AND q.oid_terr_admi = p_oidterradmin
       AND q.zscc_oid_secc = r.oid_secc
       AND r.zzon_oid_zona = k.oid_zona
       AND k.zorg_oid_regi = l.oid_regi
       AND m.oid_clie_tipo_subt = n.ctsu_oid_clie_tipo_subt
       AND (m.ticl_oid_tipo_clie = h.ticl_oid_tipo_clie OR
           h.ticl_oid_tipo_clie IS NULL)
       AND (m.sbti_oid_subt_clie = h.sbti_oid_subt_clie OR
           h.sbti_oid_subt_clie IS NULL)
       AND (n.tccl_oid_tipo_clasi = h.tccl_oid_tipo_clas OR
           h.tccl_oid_tipo_clas IS NULL)
       AND (n.clas_oid_clas = h.clas_oid_clas OR h.clas_oid_clas IS NULL)
       AND (l.oid_regi = h.zorg_oid_regi OR h.zorg_oid_regi IS NULL)
       AND (k.oid_zona = h.zzon_oid_zona OR h.zzon_oid_zona IS NULL);
  
    --return v_mminim;
  
  EXCEPTION
    WHEN OTHERS THEN
      p_nivel1  := 0;
      p_nominal := 0;
  END ped_pr_obtie_mminim;
  /***************************************************************************
  Descripcion       : Obtiene Monto Minimo Incentivos por cliente
  Fecha Creacion    : 30/01/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_obtie_mminim2
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    p_nominal      OUT NUMBER,
    p_nivel1       OUT NUMBER
  )
  --RETURN NUMBER
   IS
  
  BEGIN
  
    SELECT DISTINCT h.val_niv1,
                    h.val_mont_mini_nomi
      INTO p_nivel1,
           p_nominal
      FROM ped_monto_minim      h,
           zon_zona             k,
           zon_regio            l,
           mae_clien_tipo_subti m,
           mae_clien_clasi      n,
           zon_terri_admin      q,
           zon_secci            r
     WHERE m.clie_oid_clie = p_oidclie
       AND q.oid_terr_admi = p_oidterradmin
       AND q.zscc_oid_secc = r.oid_secc
       AND r.zzon_oid_zona = k.oid_zona
       AND k.zorg_oid_regi = l.oid_regi
       AND m.oid_clie_tipo_subt = n.ctsu_oid_clie_tipo_subt
       AND (m.ticl_oid_tipo_clie = h.ticl_oid_tipo_clie OR
           h.ticl_oid_tipo_clie IS NULL)
       AND (m.sbti_oid_subt_clie = h.sbti_oid_subt_clie OR
           h.sbti_oid_subt_clie IS NULL)
       AND (n.tccl_oid_tipo_clasi = h.tccl_oid_tipo_clas OR
           h.tccl_oid_tipo_clas IS NULL)
       AND (n.clas_oid_clas = h.clas_oid_clas OR h.clas_oid_clas IS NULL)
       AND (l.oid_regi = h.zorg_oid_regi OR h.zorg_oid_regi IS NULL)
       AND (k.oid_zona = h.zzon_oid_zona OR h.zzon_oid_zona IS NULL);
  
    --return v_mminim;
  
  EXCEPTION
    WHEN OTHERS THEN
      p_nivel1  := 0;
      p_nominal := 0;
  END ped_pr_obtie_mminim2;

  /***************************************************************************
  Descripcion       : Monto Minimo SiCC
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_monto_minim
  (
    p_oidsoli NUMBER,
    psusuario VARCHAR2
  )
  --RETURN NUMBER
   IS
  
    p_oidclie      NUMBER;
    p_oidterradmin NUMBER;
    p_nivel1       NUMBER(12, 2);
    p_nivel2       NUMBER(12, 2);
    p_nominal      NUMBER(12, 2);
    p_mtopedid     NUMBER(12, 2);
  
    lsoid_clasi      sto_param_gener_occrr.val_param%TYPE;
    lsoid_tipo_clasi sto_param_gener_occrr.val_param%TYPE;
    ln_oidperi       NUMBER;
    ln_diferen       NUMBER(12, 2);
  
    pscodigopais VARCHAR2(10);
  
    CURSOR c_ganador
    (
      p_oidperi      NUMBER,
      l_oidterradmin NUMBER
    ) IS
      SELECT b.prod_oid_prod,
             b.oid_deta_ofer,
             b.fopa_oid_form_pago,
             b.precio_unitario,
             b.val_codi_vent
        FROM pre_ofert             a,
             pre_ofert_detal       b,
             pre_matri_factu_cabec c
       WHERE a.mfca_oid_cabe = c.oid_cabe
         AND c.perd_oid_peri = p_oidperi
         AND a.oid_ofer = b.ofer_oid_ofer
         AND a.coes_oid_estr = 2018
         AND (EXISTS
              (SELECT 1
                 FROM pre_venta_exclu d,
                      zon_terri_admin e,
                      zon_secci       f,
                      zon_zona        g,
                      zon_regio       h
                WHERE d.ofer_oid_ofer = a.oid_ofer
                  AND e.oid_terr_admi = l_oidterradmin
                  AND e.zscc_oid_secc = f.oid_secc
                  AND f.zzon_oid_zona = g.oid_zona
                  AND g.zorg_oid_regi = h.oid_regi
                  AND (d.zzon_oid_zona IS NULL OR
                      d.zzon_oid_zona = g.oid_zona)
                  AND (d.zorg_oid_regi IS NULL OR
                      d.zorg_oid_regi = h.oid_regi)) OR NOT EXISTS
              (SELECT 1 FROM pre_venta_exclu WHERE ofer_oid_ofer = a.oid_ofer))
       ORDER BY b.imp_prec_cata ASC;
  
    r_ganador c_ganador%ROWTYPE;
  
    CURSOR c_ganador2(p_oidperi NUMBER) IS
      SELECT b.prod_oid_prod,
             b.oid_deta_ofer,
             b.fopa_oid_form_pago,
             b.precio_unitario,
             b.val_codi_vent
        FROM pre_ofert             a,
             pre_ofert_detal       b,
             pre_matri_factu_cabec c
       WHERE a.mfca_oid_cabe = c.oid_cabe
         AND c.perd_oid_peri = p_oidperi
         AND a.oid_ofer = b.ofer_oid_ofer
         AND a.coes_oid_estr = 2021
       ORDER BY b.imp_prec_cata ASC;
  
    r_ganador2 c_ganador2%ROWTYPE;


    ln_tolmontmin NUMBER;
  
  BEGIN
  
    SELECT DISTINCT cod_pais INTO pscodigopais FROM bas_ctrl_fact;

    ln_tolmontmin := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                          'STO_TOLER_MM'),
                                     0);

  
    SELECT a.clie_oid_clie,
           b.ztad_oid_terr_admi,
           a.perd_oid_peri
      INTO p_oidclie,
           p_oidterradmin,
           ln_oidperi
      FROM ped_solic_cabec       a,
           int_solic_conso_cabec b
     WHERE a.oid_soli_cabe = p_oidsoli
       AND a.oid_soli_cabe = b.soca_oid_soli_cabe_refe;
  
    ped_pr_obtie_mminim(p_oidclie,
                        p_oidterradmin,
                        p_nominal,
                        p_nivel1,
                        p_nivel2);
  
    SELECT SUM(b.num_unid_por_aten * b.val_prec_cata_unit_loca)
      INTO p_mtopedid
      FROM ped_solic_posic b
     WHERE soca_oid_soli_cabe = p_oidsoli;
  
    IF p_mtopedid+ln_tolmontmin < p_nominal THEN
    
      --ped_pr_rever_pedid_sto(pscodigopais, p_oidsoli, 'STO_VAL_MON_MINI', p_mtopedid, '1');
    
      UPDATE ped_solic_cabec a
         SET a.espe_oid_esta_pedi = 2
       WHERE a.oid_soli_cabe = p_oidsoli;
       
       update int_solic_conso_cabec a set a.ind_vali_dean=0 where a.soca_oid_soli_cabe_refe=p_oidsoli;
    
    END IF;
  
    IF p_mtopedid >= p_nominal AND p_mtopedid <= p_nivel1 THEN
      ln_diferen := p_nivel1 - p_mtopedid;
      OPEN c_ganador(ln_oidperi, p_oidterradmin);
      LOOP
        FETCH c_ganador
          INTO r_ganador;
        EXIT WHEN c_ganador%NOTFOUND;
      
        IF ln_diferen > 0 THEN
          INSERT INTO ped_solic_posic
            (oid_soli_posi,
             cod_posi,
             num_unid_dema,
             num_unid_por_aten,
             val_tasa_impu,
             soca_oid_soli_cabe,
             taim_oid_tasa_impu,
             tpos_oid_tipo_posi,
             prod_oid_prod,
             fopa_oid_form_pago,
             val_prec_cata_unit_loca,
             val_prec_cont_unit_loca,
             val_prec_cata_unit_docu,
             val_prec_conta_unit_docu,
             val_prec_fact_unit_loca,
             val_prec_fact_unit_docu,
             val_prec_sin_impu_unit_loca,
             val_prec_sin_impu_unit_docu,
             val_prec_sin_impu_tota_docu,
             val_impo_desc_unit_loca,
             val_impo_desc_unit_docu,
             val_prec_neto_unit_loca,
             val_prec_neto_tota_docu,
             val_prec_neto_unit_docu,
             val_prec_tota_tota_loca,
             val_prec_tota_tota_docu,
             val_impo_impu_unit_loca,
             val_impo_impu_unit_docu,
             val_impo_desc_tota_docu,
             val_impo_impu_tota_loca,
             val_impo_impu_tota_docu,
             val_impo_desc_tota_loca,
             val_prec_tota_unit_loca,
             val_prec_tota_unit_docu,
             val_prec_cont_tota_loca,
             val_prec_cata_tota_loca,
             val_prec_cata_tota_docu,
             val_prec_cont_tota_docu,
             val_porc_desc,
             val_prec_cata_tota_loca_unid,
             num_unid_dema_real,
             num_unid_compr,
             val_prec_fact_tota_loca,
             val_prec_fact_tota_docu,
             val_prec_sin_impu_tota_loca,
             val_prec_neto_tota_loca,
             ofde_oid_deta_ofer,
             espo_oid_esta_posi,
             stpo_oid_subt_posi,
             val_codi_vent,
             ind_no_impr)
          VALUES
            (ped_sopo_seq.nextval,
             (SELECT MAX(cod_posi)
                FROM ped_solic_posic
               WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
             1,
             1,
             0,
             p_oidsoli,
             NULL,
             2029,
             r_ganador.prod_oid_prod,
             r_ganador.fopa_oid_form_pago,
             r_ganador.precio_unitario,
             0,
             r_ganador.precio_unitario,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             NULL,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             NULL,
             0,
             1,
             1,
             0,
             0,
             0,
             0,
             r_ganador.oid_deta_ofer,
             4,
             2027,
             r_ganador.val_codi_vent,
             0);
        
          ln_diferen := ln_diferen - r_ganador.precio_unitario;
        
        END IF;
      
      END LOOP;
    
      -- Cerramos el cursor
      CLOSE c_ganador;
    END IF;
  
    IF p_mtopedid > p_nivel1 AND p_mtopedid <= p_nivel2 THEN
      ln_diferen := p_nivel2 - p_mtopedid;
      OPEN c_ganador2(ln_oidperi);
      LOOP
        FETCH c_ganador2
          INTO r_ganador2;
        EXIT WHEN c_ganador2%NOTFOUND;
      
        IF ln_diferen > 0 THEN
        
          INSERT INTO ped_solic_posic
            (oid_soli_posi,
             cod_posi,
             num_unid_dema,
             num_unid_por_aten,
             val_tasa_impu,
             soca_oid_soli_cabe,
             taim_oid_tasa_impu,
             tpos_oid_tipo_posi,
             prod_oid_prod,
             fopa_oid_form_pago,
             val_prec_cata_unit_loca,
             val_prec_cont_unit_loca,
             val_prec_cata_unit_docu,
             val_prec_conta_unit_docu,
             val_prec_fact_unit_loca,
             val_prec_fact_unit_docu,
             val_prec_sin_impu_unit_loca,
             val_prec_sin_impu_unit_docu,
             val_prec_sin_impu_tota_docu,
             val_impo_desc_unit_loca,
             val_impo_desc_unit_docu,
             val_prec_neto_unit_loca,
             val_prec_neto_tota_docu,
             val_prec_neto_unit_docu,
             val_prec_tota_tota_loca,
             val_prec_tota_tota_docu,
             val_impo_impu_unit_loca,
             val_impo_impu_unit_docu,
             val_impo_desc_tota_docu,
             val_impo_impu_tota_loca,
             val_impo_impu_tota_docu,
             val_impo_desc_tota_loca,
             val_prec_tota_unit_loca,
             val_prec_tota_unit_docu,
             val_prec_cont_tota_loca,
             val_prec_cata_tota_loca,
             val_prec_cata_tota_docu,
             val_prec_cont_tota_docu,
             val_porc_desc,
             val_prec_cata_tota_loca_unid,
             num_unid_dema_real,
             num_unid_compr,
             val_prec_fact_tota_loca,
             val_prec_fact_tota_docu,
             val_prec_sin_impu_tota_loca,
             val_prec_neto_tota_loca,
             ofde_oid_deta_ofer,
             espo_oid_esta_posi,
             stpo_oid_subt_posi,
             val_codi_vent,
             ind_no_impr)
          VALUES
            (ped_sopo_seq.nextval,
             (SELECT MAX(cod_posi)
                FROM ped_solic_posic
               WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
             1,
             1,
             0,
             p_oidsoli,
             NULL,
             2029,
             r_ganador2.prod_oid_prod,
             r_ganador2.fopa_oid_form_pago,
             r_ganador2.precio_unitario,
             0,
             r_ganador2.precio_unitario,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             NULL,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             NULL,
             0,
             1,
             1,
             0,
             0,
             0,
             0,
             r_ganador2.oid_deta_ofer,
             4,
             2027,
             r_ganador2.val_codi_vent,
             0);
        
          ln_diferen := ln_diferen - r_ganador2.precio_unitario;
        END IF;
      
      END LOOP;
    
      -- Cerramos el cursor
      CLOSE c_ganador2;
    
    END IF;
    
    update ped_solic_cabec set val_tota_paga_loca=p_mtopedid where oid_soli_cabe=p_oidsoli;
    
    COMMIT;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_monto_minim: ' || ls_sqlerrm);
  END ped_pr_monto_minim;

  /***************************************************************************
  Descripcion       : Monto Minimo STO
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_monto_minim_sto
  (
    p_oidsoli      NUMBER,
    p_oidperi      NUMBER,
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    psusuario      VARCHAR2
  ) RETURN NUMBER IS
  
    p_nivel1   NUMBER(12, 2);
    p_nivel2   NUMBER(12, 2);
    p_nominal  NUMBER(12, 2);
    p_mtopedid NUMBER(12, 2);
  
    ln_diferen NUMBER(12, 2);
  
    ln_resp NUMBER(2);
  
    pscodigopais VARCHAR2(10);
  
    CURSOR c_ganador(p_oidperi NUMBER) IS
      SELECT b.prod_oid_prod,
             b.oid_deta_ofer,
             b.fopa_oid_form_pago,
             b.precio_unitario,
             b.val_codi_vent,
             a.oid_ofer,
             a.ocat_oid_cata
        FROM pre_ofert             a,
             pre_ofert_detal       b,
             pre_matri_factu_cabec c
       WHERE a.mfca_oid_cabe = c.oid_cabe
         AND c.perd_oid_peri = p_oidperi
         AND a.oid_ofer = b.ofer_oid_ofer
         AND a.coes_oid_estr = 2018
       ORDER BY b.imp_prec_cata ASC;
  
    r_ganador c_ganador%ROWTYPE;
  
    CURSOR c_ganador2(p_oidperi NUMBER) IS
      SELECT b.prod_oid_prod,
             b.oid_deta_ofer,
             b.fopa_oid_form_pago,
             b.precio_unitario,
             b.val_codi_vent,
             a.oid_ofer,
             a.ocat_oid_cata
        FROM pre_ofert             a,
             pre_ofert_detal       b,
             pre_matri_factu_cabec c
       WHERE a.mfca_oid_cabe = c.oid_cabe
         AND c.perd_oid_peri = p_oidperi
         AND a.oid_ofer = b.ofer_oid_ofer
         AND a.coes_oid_estr = 2018
       ORDER BY b.imp_prec_cata ASC;
  
    r_ganador2 c_ganador2%ROWTYPE;
  
  BEGIN
  
    SELECT DISTINCT cod_pais INTO pscodigopais FROM bas_ctrl_fact;
  
    ped_pr_obtie_mminim(p_oidclie,
                        p_oidterradmin,
                        p_nominal,
                        p_nivel1,
                        p_nivel2);
  
    SELECT SUM(b.num_unid_cuad * b.precio_unitario)
      INTO p_mtopedid
      FROM tmp_cuadr_ofert b
     WHERE b.sec_nume_docu = p_oidsoli;
  
    IF p_mtopedid <= p_nominal THEN
    
      ln_resp := 0;
    
    END IF;
  
    IF p_mtopedid > p_nominal AND p_mtopedid <= p_nivel1 THEN
      ln_diferen := p_nivel1 - p_mtopedid;
      OPEN c_ganador(p_oidperi);
      LOOP
        FETCH c_ganador
          INTO r_ganador;
        EXIT WHEN c_ganador%NOTFOUND;
      
        IF ln_diferen > 0 THEN
          INSERT INTO tmp_cuadr_ofert
            (clie_oid_clie,
             perd_oid_peri,
             oid_soli_cabe,
             oid_soli_posi,
             val_codi_vent,
             num_unid,
             num_unid_cuad,
             val_esta_posi,
             val_obse,
             ind_limi_vent,
             cod_esta_posi,
             ofde_oid_deta_ofer,
             ofer_oid_ofer,
             coes_oid_estr,
             ocat_oid_cata,
             num_pagi_cata,
             val_fact_repe,
             precio_unitario,
             val_prec_cata,
             gofe_oid_grup_ofer,
             fopa_oid_form_pago,
             sec_nume_docu,
             prod_oid_prod)
          VALUES
            (p_oidclie,
             p_oidterradmin,
             0,
             ped_sopo_seq.nextval,
             r_ganador.val_codi_vent,
             0,
             1,
             2,
             'Agregado Producto Ganador',
             NULL,
             'MM',
             r_ganador.oid_deta_ofer,
             r_ganador.oid_ofer,
             2018,
             r_ganador.ocat_oid_cata,
             0,
             1,
             r_ganador.precio_unitario,
             r_ganador.precio_unitario,
             NULL,
             r_ganador.fopa_oid_form_pago,
             p_oidsoli,
             r_ganador.prod_oid_prod);
        
          ln_diferen := ln_diferen - r_ganador.precio_unitario;
        END IF;
      END LOOP;
    
      -- Cerramos el cursor
      CLOSE c_ganador;
    
      ln_resp := 1;
    ELSE
      ln_resp := 1;
    
    END IF;
  
    IF p_mtopedid > p_nivel1 AND p_mtopedid <= p_nivel2 THEN
      ln_diferen := p_nivel2 - p_mtopedid;
      OPEN c_ganador2(p_oidperi);
      LOOP
        FETCH c_ganador2
          INTO r_ganador2;
        EXIT WHEN c_ganador2%NOTFOUND;
      
        IF ln_diferen > 0 THEN
        
          INSERT INTO tmp_cuadr_ofert
            (clie_oid_clie,
             perd_oid_peri,
             oid_soli_cabe,
             oid_soli_posi,
             val_codi_vent,
             num_unid,
             num_unid_cuad,
             val_esta_posi,
             val_obse,
             ind_limi_vent,
             cod_esta_posi,
             ofde_oid_deta_ofer,
             ofer_oid_ofer,
             coes_oid_estr,
             ocat_oid_cata,
             num_pagi_cata,
             val_fact_repe,
             precio_unitario,
             val_prec_cata,
             gofe_oid_grup_ofer,
             fopa_oid_form_pago,
             sec_nume_docu,
             prod_oid_prod)
          VALUES
            (p_oidclie,
             p_oidterradmin,
             0,
             ped_sopo_seq.nextval,
             r_ganador2.val_codi_vent,
             0,
             1,
             2,
             'Agregado Producto Ganador',
             NULL,
             'MM',
             r_ganador2.oid_deta_ofer,
             r_ganador2.oid_ofer,
             2018,
             r_ganador2.ocat_oid_cata,
             0,
             1,
             r_ganador2.precio_unitario,
             r_ganador2.precio_unitario,
             NULL,
             r_ganador2.fopa_oid_form_pago,
             p_oidsoli,
             r_ganador2.prod_oid_prod);
        
          ln_diferen := ln_diferen - r_ganador2.precio_unitario;
        END IF;
      END LOOP;
      ln_resp := 1;
    
      -- Cerramos el cursor
      CLOSE c_ganador2;
    
    ELSE
      ln_resp := 1;
    
    END IF;
  
    RETURN ln_resp;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_monto_minim_sto: ' ||
                              ls_sqlerrm || ' pedido:' || p_oidsoli);
  END ped_fn_monto_minim_sto;

  /***************************************************************************
  Descripcion       : Monto Minimo Incentivos SiCC
  Fecha Creacion    : 30/01/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_monto_minim2
  (
    p_oidsoli NUMBER,
    psusuario VARCHAR2
  )
  --RETURN NUMBER
   IS
  
    p_oidclie      NUMBER;
    p_oidterradmin NUMBER;
    p_nivel1       NUMBER(12, 2);
    p_nivel2       NUMBER(12, 2);
    p_nominal      NUMBER(12, 2);
    p_mtopedid     NUMBER(12, 2);
  
    lsoid_clasi      sto_param_gener_occrr.val_param%TYPE;
    lsoid_tipo_clasi sto_param_gener_occrr.val_param%TYPE;
    ls_codvali       sto_param_gener_occrr.val_param%TYPE;
    ls_codmens       sto_param_gener_occrr.val_param%TYPE;
    ln_secnumedocu   int_solic_conso_cabec.sec_nume_docu%TYPE;
    ls_numlote       int_solic_conso_cabec.num_lote%TYPE;
    ld_fecprogfact   ped_solic_cabec.fec_prog_fact%TYPE;
    ls_indhistexce   VARCHAR2(3);
    ls_indgest       VARCHAR2(3);
    ls_codclie       VARCHAR2(15);
    ls_codperi       VARCHAR2(15);
    ln_oidperi       NUMBER;
    ln_diferen       NUMBER(12, 2);
  
    pscodigopais VARCHAR2(10);
  
    CURSOR c_ganador(p_oidperi NUMBER) IS
      SELECT b.prod_oid_prod,
             b.oid_deta_ofer,
             b.fopa_oid_form_pago,
             b.precio_unitario,
             b.val_codi_vent
        FROM pre_ofert             a,
             pre_ofert_detal       b,
             pre_matri_factu_cabec c
       WHERE a.mfca_oid_cabe = c.oid_cabe
         AND c.perd_oid_peri = p_oidperi
         AND a.oid_ofer = b.ofer_oid_ofer
         AND a.coes_oid_estr = 2018
       ORDER BY b.imp_prec_cata ASC;
  
    r_ganador c_ganador%ROWTYPE;
  
  BEGIN
  
    SELECT DISTINCT cod_pais INTO pscodigopais FROM bas_ctrl_fact;
  
    SELECT a.clie_oid_clie,
           b.ztad_oid_terr_admi,
           b.sec_nume_docu,
           a.fec_prog_fact,
           b.cod_clie,
           b.cod_peri,
           a.perd_oid_peri
      INTO p_oidclie,
           p_oidterradmin,
           ln_secnumedocu,
           ld_fecprogfact,
           ls_codclie,
           ls_codperi,
           ln_oidperi
      FROM ped_solic_cabec       a,
           int_solic_conso_cabec b
     WHERE a.oid_soli_cabe = p_oidsoli
       AND a.oid_soli_cabe = b.soca_oid_soli_cabe_refe;
  
    ped_pr_obtie_mminim(p_oidclie,
                        p_oidterradmin,
                        p_nominal,
                        p_nivel1,
                        p_nivel2);
  
    SELECT SUM(b.num_unid_por_aten * b.val_prec_cata_unit_loca)
      INTO p_mtopedid
      FROM ped_solic_posic b
     WHERE soca_oid_soli_cabe = p_oidsoli;
  
    IF p_mtopedid <= p_nominal THEN
      --update ped_solic_cabec a set a.esso_oid_esta_soli=2 where oid_soli_cabe=p_oidsoli;
      /*lsoid_clasi      := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_MTMI_OID_CLAS');
      lsoid_tipo_clasi := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_MTMA_OID_TICLAS');
      
      ls_codvali := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                         'STO_VAL_MON_MINI');
      
      DELETE FROM mae_clien_clasi a
       WHERE a.clas_oid_clas = lsoid_clasi
         AND a.tccl_oid_tipo_clasi = lsoid_tipo_clasi
         AND a.ctsu_oid_clie_tipo_subt IN
             (SELECT b.oid_clie_tipo_subt
                FROM mae_clien_tipo_subti b
               WHERE b.clie_oid_clie = p_oidclie);*/
    
      UPDATE int_solic_conso_cabec consol
         SET consol.fec_prog_fact = ld_fecprogfact,
             consol.fec_modi      = SYSDATE,
             consol.usu_modi      = psusuario,
             consol.ind_val_mtmi  = '0',
             consol.ind_erro_mtmi = '1',
             consol.ind_ocs_proc  = '0',
             consol.val_mont_pedi = p_mtopedid
       WHERE consol.soca_oid_soli_cabe_refe = p_oidsoli;
    
      ls_codmens := sto_pkg_gener.sto_fn_devue_codig_mensa(pscodigopais,
                                                           'OCC',
                                                           ls_codvali);
    
      SELECT num_lote
        INTO ls_numlote
        FROM int_solic_conso_cabec
       WHERE soca_oid_soli_cabe_refe = p_oidsoli;
    
      SELECT p.ind_hist_exce,
             ind_gest
        INTO ls_indhistexce,
             ls_indgest
        FROM sto_param_valid p
       WHERE p.cod_pais = pscodigopais
         AND p.cod_tipo_docu = 'OCC'
         AND p.cod_vali = ls_codvali;
    
      IF ls_indhistexce = '1' THEN
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
           cod_mens)
        VALUES
          (pscodigopais,
           'OCC',
           ls_numlote,
           ls_codvali,
           ln_secnumedocu,
           to_char(SYSDATE, 'YYYYMMDDHHMISS'),
           ls_indgest,
           SYSDATE,
           psusuario,
           ls_codmens);
      END IF;
    
      DELETE sto_detal_docum_excep
       WHERE sec_nume_docu = ln_secnumedocu
         AND num_lote = ls_numlote
         AND cod_vali = ls_codvali
         AND cod_tipo_docu = 'OCC';
    
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
         cod_mens)
      VALUES
        (pscodigopais,
         'OCC',
         ls_numlote,
         ls_codvali,
         ln_secnumedocu,
         ls_indgest,
         SYSDATE,
         psusuario,
         SYSDATE,
         psusuario,
         ls_codmens);
    
      UPDATE sto_docum_digit a
         SET a.ind_envi = '0',
             a.ind_rech = '0',
             a.fec_modi = SYSDATE,
             a.usu_modi = psusuario
       WHERE a.num_lote = ls_numlote
         AND a.sec_nume_docu = ln_secnumedocu;
    
      UPDATE sto_docum_digit a
         SET a.ind_envi = '0',
             a.ind_rech = '0',
             a.fec_modi = SYSDATE,
             a.usu_modi = psusuario
       WHERE a.num_lote = ls_numlote
         AND a.sec_nume_docu_cabe = ln_secnumedocu;
    
      ped_pkg_cuadr_ofert.ped_pr_retor_stock_solic(p_oidsoli);
    
      DELETE FROM car_soli_entr_bloq a
       WHERE a.soca_oid_soli_cabe = p_oidsoli;
    
      DELETE ped_solic_posic p WHERE soca_oid_soli_cabe = p_oidsoli;
    
      DELETE mav_solic_envio p WHERE soca_oid_soli_cabe = p_oidsoli;
    
      DELETE ped_solic_mensa p WHERE soca_oid_soli_cabe = p_oidsoli;
    
      DELETE ped_solic_cabec p WHERE oid_soli_cabe = p_oidsoli;
    
      DELETE inc_premi_elegi x
       WHERE p_oidclie = x.clie_oid_clie
         AND EXISTS
       (SELECT 1
                FROM int_solic_conso_detal det
               WHERE ls_codclie = det.cod_clie
                 AND ls_numlote = det.num_lote
                 AND ls_codperi = det.cod_peri
                 AND det.copa_oid_para_gral = x.copa_oid_para_gral
                 AND det.panp_oid_para_nive_prem = x.panp_oid_para_nive_prem
                 AND det.num_prem = x.num_prem);
    
    END IF;
  
    IF p_mtopedid > p_nominal AND p_mtopedid <= p_nivel1 THEN
      ln_diferen := p_mtopedid - p_nominal;
      OPEN c_ganador(ln_oidperi);
      LOOP
        FETCH c_ganador
          INTO r_ganador;
        EXIT WHEN c_ganador%NOTFOUND;
      
        INSERT INTO ped_solic_posic
          (oid_soli_posi,
           cod_posi,
           num_unid_dema,
           num_unid_por_aten,
           val_tasa_impu,
           soca_oid_soli_cabe,
           taim_oid_tasa_impu,
           tpos_oid_tipo_posi,
           prod_oid_prod,
           fopa_oid_form_pago,
           val_prec_cata_unit_loca,
           val_prec_cont_unit_loca,
           val_prec_cata_unit_docu,
           val_prec_conta_unit_docu,
           val_prec_fact_unit_loca,
           val_prec_fact_unit_docu,
           val_prec_sin_impu_unit_loca,
           val_prec_sin_impu_unit_docu,
           val_prec_sin_impu_tota_docu,
           val_impo_desc_unit_loca,
           val_impo_desc_unit_docu,
           val_prec_neto_unit_loca,
           val_prec_neto_tota_docu,
           val_prec_neto_unit_docu,
           val_prec_tota_tota_loca,
           val_prec_tota_tota_docu,
           val_impo_impu_unit_loca,
           val_impo_impu_unit_docu,
           val_impo_desc_tota_docu,
           val_impo_impu_tota_loca,
           val_impo_impu_tota_docu,
           val_impo_desc_tota_loca,
           val_prec_tota_unit_loca,
           val_prec_tota_unit_docu,
           val_prec_cont_tota_loca,
           val_prec_cata_tota_loca,
           val_prec_cata_tota_docu,
           val_prec_cont_tota_docu,
           val_porc_desc,
           val_prec_cata_tota_loca_unid,
           num_unid_dema_real,
           num_unid_compr,
           val_prec_fact_tota_loca,
           val_prec_fact_tota_docu,
           val_prec_sin_impu_tota_loca,
           val_prec_neto_tota_loca,
           ofde_oid_deta_ofer,
           espo_oid_esta_posi,
           stpo_oid_subt_posi,
           val_codi_vent,
           ind_no_impr)
        VALUES
          (ped_sopo_seq.nextval,
           (SELECT MAX(cod_posi)
              FROM ped_solic_posic
             WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
           1,
           1,
           0,
           p_oidsoli,
           NULL,
           2029,
           r_ganador.prod_oid_prod,
           r_ganador.fopa_oid_form_pago,
           r_ganador.precio_unitario,
           0,
           r_ganador.precio_unitario,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           NULL,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           NULL,
           0,
           1,
           1,
           0,
           0,
           0,
           0,
           r_ganador.oid_deta_ofer,
           4,
           2027,
           r_ganador.val_codi_vent,
           0);
      
        ln_diferen := ln_diferen - r_ganador.precio_unitario;
        IF ln_diferen <= 0 THEN
          EXIT;
        END IF;
      END LOOP;
    
      -- Cerramos el cursor
      CLOSE c_ganador;
    
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_monto_minim2: ' || ls_sqlerrm);
  END ped_pr_monto_minim2;

  /***************************************************************************
  Descripcion       : Obtiene Monto Maximo por cliente
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_obtie_mmaxim
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER
  ) RETURN NUMBER IS
  
    p_mmaxim NUMBER;
  
  BEGIN
  
    SELECT val_mont_maxi_perm
      INTO p_mmaxim
      FROM (SELECT nvl(i.zorg_oid_regi, 0),
                   nvl(i.zzon_oid_zona, 0),
                   nvl(i.zscc_oid_secc, 0),
                   h.val_mont_maxi_perm
              FROM car_param_carte       h,
                   car_asign_codig_confi i,
                   zon_zona              k,
                   zon_regio             l,
                   zon_terri_admin       q,
                   zon_secci             r,
                   mae_clien_datos_adici s
             WHERE s.clie_oid_clie = p_oidclie
               AND h.oid_para_cart = i.paca_oid_para_cart
               AND h.ind_mont_maxi = 1
               AND q.oid_terr_admi = p_oidterradmin
               AND s.niri_oid_nive_ries = i.niri_oid_nive_ries
               AND q.zscc_oid_secc = r.oid_secc
               AND r.zzon_oid_zona = k.oid_zona
               AND k.zorg_oid_regi = l.oid_regi
               AND (l.oid_regi = i.zorg_oid_regi OR i.zorg_oid_regi IS NULL)
               AND (k.oid_zona = i.zzon_oid_zona OR i.zzon_oid_zona IS NULL)
               AND (r.oid_secc = i.zscc_oid_secc OR i.zscc_oid_secc IS NULL)
             ORDER BY 1 DESC,
                      2 DESC,
                      3 DESC,
                      4 DESC)
     WHERE rownum = 1;
  
    RETURN p_mmaxim;
  
  EXCEPTION
    WHEN OTHERS THEN
      p_mmaxim := 9999999999;
      RETURN p_mmaxim;
    
  END ped_fn_obtie_mmaxim;
  /***************************************************************************
  Descripcion       : Monto Minimo SiCC
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_monto_maxim
  (
    p_oidsoli NUMBER,
    psusuario VARCHAR2
  )
  --RETURN NUMBER
   IS
  
    p_mtopedid     NUMBER(12, 2);
    p_mtomaxim     NUMBER(12, 2);
    p_temp         NUMBER(12, 2);
    p_oidclie      NUMBER;
    p_oidterradmin NUMBER;
  
    ls_elimmax   sto_param_gener_occrr.val_param%TYPE;
    ln_nivelries int_solic_conso_cabec.niri_oid_nive_ries%TYPE;
    ln_diferen   NUMBER(12, 2);
  
    pscodigopais VARCHAR2(10);
  
    CURSOR c_eliminar IS
      SELECT y.oid_soli_posi,
             CASE
               WHEN coes_oid_estr IN (2002, 2006) THEN
                y.num_unid_por_aten / b.val_fact_repe
               ELSE
                y.num_unid_por_aten
             END num_unid_por_aten,
             a.coes_oid_estr,
             b.ofer_oid_ofer,
             b.oid_deta_ofer,
             b.precio_unitario,
             b.val_fact_repe
        FROM ped_solic_cabec     x,
             ped_solic_posic     y,
             pre_ofert           a,
             pre_ofert_detal     b,
             ped_tipo_ofert_prio c
       WHERE x.oid_soli_cabe = p_oidsoli
         AND x.oid_soli_cabe = y.soca_oid_soli_cabe
         AND a.oid_ofer = b.ofer_oid_ofer
         AND b.oid_deta_ofer = y.ofde_oid_deta_ofer
         AND b.tofe_oid_tipo_ofer = c.tofe_oid_tipo_ofer(+)
         AND b.ind_digi = 1
         AND (y.val_prec_cata_unit_loca > 0 OR
             a.coes_oid_estr IN (2002, 2006))
       ORDER BY nvl(c.num_prio, 99) ASC,
                b.precio_unitario DESC;
  
    r_eliminar c_eliminar%ROWTYPE;
  
  BEGIN
  
    SELECT DISTINCT cod_pais INTO pscodigopais FROM bas_ctrl_fact;
  
    SELECT a.clie_oid_clie,
           b.ztad_oid_terr_admi,
           b.niri_oid_nive_ries
      INTO p_oidclie,
           p_oidterradmin,
           ln_nivelries
      FROM ped_solic_cabec       a,
           int_solic_conso_cabec b
     WHERE a.oid_soli_cabe = p_oidsoli
       AND a.oid_soli_cabe = b.soca_oid_soli_cabe_refe;
  
    p_mtomaxim := nvl(ped_fn_obtie_mmaxim(p_oidclie, p_oidterradmin),
                      999999999);
  
    SELECT SUM(b.num_unid_por_aten * b.val_prec_cata_unit_loca)
      INTO p_mtopedid
      FROM ped_solic_posic b
     WHERE soca_oid_soli_cabe = p_oidsoli;
  
    IF p_mtopedid > p_mtomaxim THEN
      ls_elimmax := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_ELIM_MMAXI'),
                        'N');
    
      IF ls_elimmax <> 'S' THEN
      
        --ped_pr_rever_pedid_sto(pscodigopais, p_oidsoli, 'STO_VAL_MON_MAXI', p_mtopedid, '2');
      
        UPDATE ped_solic_cabec a
           SET a.espe_oid_esta_pedi = 3
         WHERE a.oid_soli_cabe = p_oidsoli;
      
      ELSE
        ln_diferen := p_mtopedid - p_mtomaxim;
        OPEN c_eliminar;
        LOOP
          FETCH c_eliminar
            INTO r_eliminar;
          EXIT WHEN c_eliminar%NOTFOUND;
        
          IF r_eliminar.coes_oid_estr IN (2002, 2006) THEN
          
            WHILE r_eliminar.num_unid_por_aten > 0 AND ln_diferen > 0
            LOOP
              UPDATE ped_solic_posic a
                 SET a.num_unid_por_aten  = a.num_unid_por_aten - (1 *
                                            (SELECT val_fact_repe
                                                                      FROM pre_ofert_detal
                                                                     WHERE oid_deta_ofer =
                                                                           a.ofde_oid_deta_ofer)),
                     a.num_unid_dema_real = a.num_unid_dema_real - (1 *
                                            (SELECT val_fact_repe
                                                                       FROM pre_ofert_detal
                                                                      WHERE oid_deta_ofer =
                                                                            a.ofde_oid_deta_ofer)),
                     a.num_unid_compr     = a.num_unid_compr - (1 *
                                            (SELECT val_fact_repe
                                                                   FROM pre_ofert_detal
                                                                  WHERE oid_deta_ofer =
                                                                        a.ofde_oid_deta_ofer)),
                     a.tpos_oid_tipo_posi = 1,
                     a.stpo_oid_subt_posi = 21
               WHERE soca_oid_soli_cabe = p_oidsoli
                 AND a.num_unid_dema_real > 0
                 AND ofde_oid_deta_ofer IN
                     (SELECT oid_deta_ofer
                        FROM pre_ofert_detal
                       WHERE ofer_oid_ofer = r_eliminar.ofer_oid_ofer
                      --and (oid_deta_ofer=r_eliminar.oid_deta_ofer or precio_unitario=0)
                      );
            
              r_eliminar.num_unid_por_aten := r_eliminar.num_unid_por_aten - 1;
            
              BEGIN
                SELECT SUM(imp_prec_cata)
                  INTO p_temp
                  FROM pre_ofert_detal
                 WHERE ofer_oid_ofer = r_eliminar.ofer_oid_ofer;
              EXCEPTION
                WHEN OTHERS THEN
                  p_temp := 0;
              END;
            
              ln_diferen := ln_diferen - p_temp;
            
            END LOOP;
          
          ELSE
          
            WHILE r_eliminar.num_unid_por_aten > 0 AND ln_diferen > 0
            LOOP
            
              UPDATE ped_solic_posic a
                 SET a.num_unid_por_aten  = a.num_unid_por_aten - 1,
                     a.num_unid_dema_real = a.num_unid_dema_real - 1,
                     a.num_unid_compr     = a.num_unid_compr - 1,
                     a.tpos_oid_tipo_posi = 1,
                     a.stpo_oid_subt_posi = 21
               WHERE soca_oid_soli_cabe = p_oidsoli
                 AND oid_soli_posi = r_eliminar.oid_soli_posi;
            
              r_eliminar.num_unid_por_aten := r_eliminar.num_unid_por_aten - 1;
            
              ln_diferen := ln_diferen - r_eliminar.precio_unitario;
            
            END LOOP;
          
            IF ln_diferen <= 0 THEN
              EXIT;
            END IF;
          
          END IF;
        
          IF ln_diferen <= 0 THEN
            EXIT;
          END IF;
        
        END LOOP;
      
        -- Cerramos el cursor
        CLOSE c_eliminar;
      END IF;
    END IF;
  
    UPDATE mae_clien_datos_adici a
       SET a.niri_oid_nive_ries = ln_nivelries
     WHERE a.clie_oid_clie = p_oidclie
       AND ln_nivelries IS NOT NULL;
  
    COMMIT;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_monto_maxim: ' || ls_sqlerrm);
  END ped_pr_monto_maxim;
  /***************************************************************************
  Descripcion       : Monto Minimo SiCC
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_monto_maxim_sto
  (
    p_oidsoli      NUMBER,
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER,
    psusuario      VARCHAR2
  ) RETURN NUMBER IS
  
    p_mtopedid NUMBER(12, 2);
    p_mtomaxim NUMBER(12, 2);
  
    ls_elimmax sto_param_gener_occrr.val_param%TYPE;
    ln_diferen NUMBER(12, 2);
  
    pscodigopais VARCHAR2(10);
  
    ln_resp NUMBER(2);
  
    CURSOR c_eliminar IS
      SELECT b.ofer_oid_ofer,
             b.oid_deta_ofer,
             b.precio_unitario * y.num_unid_cuad monto
        FROM tmp_cuadr_ofert     y,
             pre_ofert           a,
             pre_ofert_detal     b,
             ped_tipo_ofert_prio c
       WHERE y.sec_nume_docu = p_oidsoli
         AND a.oid_ofer = b.ofer_oid_ofer
         AND b.oid_deta_ofer = y.ofde_oid_deta_ofer
         AND b.tofe_oid_tipo_ofer = c.tofe_oid_tipo_ofer(+)
       ORDER BY nvl(c.num_prio, 99) ASC,
                b.precio_unitario DESC;
  
    r_eliminar c_eliminar%ROWTYPE;
  
  BEGIN
  
    ln_resp := 1;
  
    SELECT DISTINCT cod_pais INTO pscodigopais FROM bas_ctrl_fact;
  
    p_mtomaxim := ped_fn_obtie_mmaxim(p_oidclie, p_oidterradmin);
  
    SELECT SUM(b.num_unid_por_aten * b.val_prec_cata_unit_loca)
      INTO p_mtopedid
      FROM ped_solic_posic b
     WHERE soca_oid_soli_cabe = p_oidsoli;
  
    IF p_mtopedid > p_mtomaxim THEN
      ls_elimmax := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_ELIM_MMAXI'),
                        'N');
    
      IF ls_elimmax <> 'S' THEN
        ln_resp := 0;
      
      ELSE
        ln_diferen := p_mtopedid - p_mtomaxim;
        OPEN c_eliminar;
        LOOP
          FETCH c_eliminar
            INTO r_eliminar;
          EXIT WHEN c_eliminar%NOTFOUND;
        
          UPDATE tmp_cuadr_ofert x
             SET x.num_unid_cuad = 0,
                 x.val_obse      = 'Anulado por Monto M?ximo'
           WHERE x.clie_oid_clie = p_oidclie
             AND x.sec_nume_docu = p_oidsoli;
        
          ln_diferen := ln_diferen - r_eliminar.monto;
          IF ln_diferen <= 0 THEN
            EXIT;
          END IF;
        END LOOP;
      
        -- Cerramos el cursor
        CLOSE c_eliminar;
      END IF;
    END IF;
  
    RETURN ln_resp;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_fn_monto_maxim_sto: ' ||
                              ls_sqlerrm || ' pedido:' || p_oidsoli);
  END ped_fn_monto_maxim_sto;
  /***************************************************************************
  Descripcion       : Monto Minimo SiCC
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ofert_espec
  (
    p_oidsoli    NUMBER,
    pscodigopais VARCHAR
  )
  --RETURN NUMBER
   IS
  
    lnoidestra1 NUMBER := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_ESTRA_ESPE1');
    lnoidestra2 NUMBER := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_ESTRA_ESPE2');
  
    lsindenviomensaje VARCHAR2(10) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                           'OCR_IND_ENVI_MENSA');
    lscodigomensaje   VARCHAR2(10) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                           'STO_COD_MEN_CUV_ERRA');
    lsindgenestat     VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                           'IND_GENE_ESTAT');
  
    lnoid_mens NUMBER := 0;
  
    lscodperi VARCHAR2(10);
  
    CURSOR ini_stock2(vnoidsolicitud NUMBER) IS
      SELECT a.clie_oid_clie
        FROM ped_solic_cabec     a,
             ped_tipo_solic_pais e,
             ped_tipo_solic      f
       WHERE a.oid_soli_cabe = vnoidsolicitud
         AND a.tspa_oid_tipo_soli_pais = e.oid_tipo_soli_pais
         AND e.tsol_oid_tipo_soli = f.oid_tipo_soli
         AND nvl(f.ind_soli_nega, 0) = 0
         AND a.ind_oc = 1;
  
    TYPE ini_stockrec2 IS RECORD(
      varoidclie NUMBER(12));
  
    TYPE ini_stockrectab2 IS TABLE OF ini_stockrec2;
    ini_stockrecord2 ini_stockrectab2;
  
    CURSOR ofert_espec1(vnoidsolicitud NUMBER) IS
      SELECT oid_soli_cabe,
             perd_oid_peri,
             val_tota_paga_loca -
             nvl((SELECT SUM(val_prec_cata_unit_loca * num_unid_dema_real)
                   FROM ped_solic_posic x,
                        pre_ofert_detal y,
                        pre_ofert       z
                  WHERE x.ofde_oid_deta_ofer = y.oid_deta_ofer
                    AND y.ofer_oid_ofer = z.oid_ofer
                    AND z.coes_oid_estr = lnoidestra1
                    AND x.soca_oid_soli_cabe = a.oid_soli_cabe),
                 0) val_tota_paga_loca
        FROM ped_solic_cabec     a,
             ped_tipo_solic_pais e,
             ped_tipo_solic      f
       WHERE a.oid_soli_cabe = vnoidsolicitud
         AND a.tspa_oid_tipo_soli_pais = e.oid_tipo_soli_pais
         AND e.tsol_oid_tipo_soli = f.oid_tipo_soli
         AND nvl(f.ind_soli_nega, 0) = 0
         AND a.ind_oc = 1;
  
    TYPE ofert_espec1rec IS RECORD(
      varoidsoli  NUMBER(12),
      varperio    NUMBER(12),
      vartotapaga NUMBER(12, 2));
  
    TYPE ofert_espec1rectab IS TABLE OF ofert_espec1rec;
    ofert_espec1record ofert_espec1rectab;
  
    CURSOR ofert_espec2
    (
      p_estra        NUMBER,
      vnoidsolicitud NUMBER
    ) IS
      SELECT a.oid_soli_cabe,
             val_tota_paga_loca -
             nvl((SELECT SUM(val_prec_cata_unit_loca * num_unid_dema_real)
                   FROM ped_solic_posic x,
                        pre_ofert_detal y,
                        pre_ofert       z
                  WHERE x.ofde_oid_deta_ofer = y.oid_deta_ofer
                    AND y.ofer_oid_ofer = z.oid_ofer
                    AND z.coes_oid_estr = lnoidestra2
                    AND x.soca_oid_soli_cabe = a.oid_soli_cabe),
                 0) val_tota_paga_loca,
             a.perd_oid_peri,
             ocr_fn_devue_ofer_espe(val_tota_paga_loca -
                                    nvl((SELECT SUM(val_prec_cata_unit_loca *
                                                   num_unid_dema_real)
                                          FROM ped_solic_posic x,
                                               pre_ofert_detal y,
                                               pre_ofert       z
                                         WHERE x.ofde_oid_deta_ofer =
                                               y.oid_deta_ofer
                                           AND y.ofer_oid_ofer = z.oid_ofer
                                           AND z.coes_oid_estr =
                                               lnoidestra2
                                           AND x.soca_oid_soli_cabe =
                                               a.oid_soli_cabe),
                                        0),
                                    a.perd_oid_peri,
                                    p_estra,
                                    (SELECT MAX(z.oid_ofer)
                                       FROM ped_solic_posic x,
                                            pre_ofert_detal y,
                                            pre_ofert       z
                                      WHERE x.ofde_oid_deta_ofer =
                                            y.oid_deta_ofer
                                        AND y.ofer_oid_ofer = z.oid_ofer
                                        AND z.coes_oid_estr = 2020
                                        AND x.soca_oid_soli_cabe =
                                            a.oid_soli_cabe)) oid_ofer
        FROM ped_solic_cabec     a,
             ped_tipo_solic_pais e,
             ped_tipo_solic      f
       WHERE a.oid_soli_cabe = vnoidsolicitud
         AND a.tspa_oid_tipo_soli_pais = e.oid_tipo_soli_pais
         AND e.tsol_oid_tipo_soli = f.oid_tipo_soli
         AND nvl(f.ind_soli_nega, 0) = 0
         AND a.ind_oc = 1
         AND EXISTS (SELECT oid_ofer
                FROM ped_solic_posic b,
                     pre_ofert       c,
                     pre_ofert_detal d
               WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                 AND b.ofde_oid_deta_ofer = d.oid_deta_ofer
                 AND d.ofer_oid_ofer = c.oid_ofer
                 AND c.coes_oid_estr = p_estra);
  
    TYPE ofert_espec2rec IS RECORD(
      varoidsoli  NUMBER(12),
      vartotapaga NUMBER(12, 2),
      varperio    NUMBER(12),
      varoidofer  NUMBER(12));
  
    TYPE ofert_espec2rectab IS TABLE OF ofert_espec2rec;
    ofert_espec2record ofert_espec2rectab;
  
    CURSOR prod
    (
      p_estra1      NUMBER,
      p_valtotapaga NUMBER,
      p_perio       NUMBER
    ) IS
    
      SELECT c.oid_deta_ofer,
             c.prod_oid_prod,
             d.fopa_oid_form_pago,
             c.val_codi_vent,
             c.precio_unitario,
             c.imp_prec_posi
        FROM pre_ofert_detal       c,
             pre_ofert             d,
             pre_promo             e,
             pre_matri_factu_cabec f
       WHERE c.ofer_oid_ofer = d.oid_ofer
         AND d.oid_ofer = e.ofer_oid_ofer
         AND d.coes_oid_estr = p_estra1
         AND d.mfca_oid_cabe = f.oid_cabe
         AND f.perd_oid_peri = p_perio
         AND e.val_fact_cuad <= p_valtotapaga
         AND d.oid_ofer = (SELECT oid_ofer
                             FROM (SELECT oid_ofer,
                                          MAX(e.val_fact_cuad)
                                     FROM pre_ofert_detal       c,
                                          pre_ofert             d,
                                          pre_promo             e,
                                          pre_matri_factu_cabec f
                                    WHERE c.ofer_oid_ofer = d.oid_ofer
                                      AND d.oid_ofer = e.ofer_oid_ofer
                                      AND d.coes_oid_estr = p_estra1
                                      AND d.mfca_oid_cabe = f.oid_cabe
                                      AND f.perd_oid_peri = p_perio
                                      AND e.val_fact_cuad <= p_valtotapaga
                                    GROUP BY oid_ofer
                                    ORDER BY 2 DESC)
                            WHERE rownum = 1);
  
    TYPE prodrec IS RECORD(
      varoiddetaofer NUMBER(12),
      varprodoidprod NUMBER(12),
      varfopa        NUMBER(12),
      varcodivent    VARCHAR(10),
      varpreciounit  NUMBER(12, 2),
      varpreciocont  NUMBER(12, 2));
  
    TYPE prodrectab IS TABLE OF prodrec;
    prodrecord prodrectab;
  
    CURSOR prod2
    (
      p_estra1      NUMBER,
      p_valtotapaga NUMBER,
      p_perio       NUMBER,
      p_oidofer     NUMBER
    ) IS
    
      SELECT c.oid_deta_ofer,
             c.prod_oid_prod,
             d.fopa_oid_form_pago,
             c.val_codi_vent,
             c.precio_unitario,
             c.imp_prec_posi
        FROM pre_ofert_detal       c,
             pre_ofert             d,
             pre_promo             e,
             pre_matri_factu_cabec f
       WHERE c.ofer_oid_ofer = d.oid_ofer
         AND d.oid_ofer = e.ofer_oid_ofer
         AND d.coes_oid_estr = p_estra1
         AND d.mfca_oid_cabe = f.oid_cabe
         AND f.perd_oid_peri = p_perio
         AND e.val_fact_cuad <= p_valtotapaga
         AND d.oid_ofer = p_oidofer;
  
    TYPE prod2rec IS RECORD(
      varoiddetaofer NUMBER(12),
      varprodoidprod NUMBER(12),
      varfopa        NUMBER(12),
      varcodivent    VARCHAR(10),
      varpreciounit  NUMBER(12, 2),
      varpreciocont  NUMBER(12, 2));
  
    TYPE prod2rectab IS TABLE OF prod2rec;
    prod2record prod2rectab;
  
    varoidsoli  NUMBER(12);
    varperio    NUMBER(12);
    vartotapaga NUMBER(12, 2);
    varoidofer  NUMBER(12);
  
    varoiddetaofer NUMBER(12);
    varprodoidprod NUMBER(12);
    varfopa        NUMBER(12);
    varcodivent    VARCHAR(10);
    varpreciounit  NUMBER(12, 2);
    varpreciocont  NUMBER(12, 2);
    varuniddema    NUMBER(12);
  
    lnlimiteventa NUMBER := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                     'STO_LIMI_VENT_OE'),
                                1);
  
    ln_oidclie NUMBER(10);
  
  BEGIN
  
    SELECT cod_peri
      INTO lscodperi
      FROM bas_ctrl_fact a
     WHERE a.sta_camp = 0
       AND a.ind_camp_act = 1;
  
    SELECT clie_oid_clie
      INTO ln_oidclie
      FROM ped_solic_cabec
     WHERE oid_soli_cabe = p_oidsoli;
  
    IF lsindgenestat = 'S' THEN
      INSERT INTO mae_clien_estat
        (oid_clie)
        SELECT ln_oidclie
          FROM dual
         WHERE NOT EXISTS
         (SELECT 1 FROM mae_clien_estat WHERE oid_clie = ln_oidclie);
    
      UPDATE mae_clien_estat a
         SET a.camp_ingr =
             (SELECT cod_peri
                FROM seg_perio_corpo       x,
                     cra_perio             y,
                     mae_clien_prime_conta z
               WHERE z.clie_oid_clie = ln_oidclie
                 AND z.perd_oid_peri = y.oid_peri
                 AND y.peri_oid_peri = x.oid_peri)
       WHERE (SELECT camp_ingr
                FROM mae_clien_estat
               WHERE oid_clie = ln_oidclie) IS NULL;
    
    END IF;
  
    -- Creacion de Mensajes Codigos Errados
    IF (lsindenviomensaje = 'S') THEN
      SELECT oid_mens
        INTO lnoid_mens
        FROM msg_mensa
       WHERE cod_mens = lscodigomensaje;
    
      OPEN ini_stock2(p_oidsoli);
    
      LOOP
        FETCH ini_stock2 BULK COLLECT
          INTO ini_stockrecord2 LIMIT 100000;
      
        IF ini_stockrecord2.count > 0 THEN
          FOR x IN ini_stockrecord2.first .. ini_stockrecord2.last
          LOOP
          
            -- Borramos en la tabla MSG_BUZON_MENSA
            DELETE FROM msg_buzon_mensa
             WHERE clie_oid_clie = ini_stockrecord2(x).varoidclie
               AND mens_oid_mens = lnoid_mens
               AND peri_oid_peri IS NULL;
          
            -- Insertamos en MSG_BUZON_MENSA
            INSERT INTO msg_buzon_mensa
              (oid_buzo_mens,
               num_secu,
               dato_vari_01,
               dato_vari_02,
               dato_vari_03,
               dato_vari_04,
               dato_vari_05,
               dato_vari_06,
               dato_vari_07,
               dato_vari_08,
               dato_vari_09,
               dato_vari_10,
               clie_oid_clie,
               mens_oid_mens,
               modu_oid_modu_orig,
               fec_grab,
               ind_list_cons,
               ind_acti,
               ind_esta_mens)
            
              SELECT msg_bume_seq.nextval,
                     msg_bum2_seq.nextval,
                     e.val_nom1 || ' ' || e.val_nom2 || ' ' || e.val_ape1 || ' ' ||
                     e.val_ape2,
                     '2',
                     '3',
                     '4',
                     '5',
                     c.val_unid_dem,
                     c.docu_num_docu,
                     '1',
                     decode(nvl(c.val_unid_dem, 0),
                            0,
                            'Sin Unidades',
                            'No Existe'),
                     c.cod_vent,
                     ini_stockrecord2(x).varoidclie,
                     lnoid_mens,
                     6,
                     SYSDATE,
                     0,
                     1,
                     1
                FROM int_solic_conso_detal c,
                     sto_detal_docum_excep d,
                     mae_clien             e
               WHERE c.sec_nume_docu = d.sec_nume_docu
                 AND d.cod_vali = 'OCD-2'
                 AND c.cod_clie = e.cod_clie
                 AND e.oid_clie = ini_stockrecord2(x).varoidclie
                 AND c.cod_peri = lscodperi;
          
          END LOOP;
        END IF;
        EXIT WHEN ini_stock2%NOTFOUND;
      END LOOP;
      CLOSE ini_stock2;
    
    END IF;
  
    --Programa de Ofertas Especiales
    IF lnoidestra1 IS NOT NULL THEN
      OPEN ofert_espec1(p_oidsoli);
      LOOP
        FETCH ofert_espec1 BULK COLLECT
          INTO ofert_espec1record LIMIT 100000;
      
        IF ofert_espec1record.count > 0 THEN
          FOR x IN ofert_espec1record.first .. ofert_espec1record.last
          LOOP
          
            varoidsoli  := ofert_espec1record(x).varoidsoli;
            varperio    := ofert_espec1record(x).varperio;
            vartotapaga := ofert_espec1record(x).vartotapaga;
          
            DELETE FROM ped_solic_posic p --set num_unid_dema=0, num_unid_por_aten=0, num_unid_dema_real=0, num_unid_compr=0, num_unid_aten=0, espo_oid_esta_posi=2, val_obse='ANULADO POR ESTRATEGIA ESPECIAL'
             WHERE p.soca_oid_soli_cabe = varoidsoli
               AND EXISTS
             (SELECT 1
                      FROM pre_ofert_detal c,
                           pre_ofert       d
                     WHERE c.ofer_oid_ofer = d.oid_ofer
                       AND d.coes_oid_estr = lnoidestra1
                       AND p. ofde_oid_deta_ofer = c.oid_deta_ofer);
          
            OPEN prod(lnoidestra1, vartotapaga, varperio);
            LOOP
              FETCH prod BULK COLLECT
                INTO prodrecord LIMIT 100000;
            
              IF prodrecord.count > 0 THEN
                FOR x IN prodrecord.first .. prodrecord.last
                LOOP
                
                  varoiddetaofer := prodrecord(x).varoiddetaofer;
                  varprodoidprod := prodrecord(x).varprodoidprod;
                  varfopa        := prodrecord(x).varfopa;
                  varcodivent    := prodrecord(x).varcodivent;
                  varpreciounit  := prodrecord(x).varpreciounit;
                  varpreciocont  := prodrecord(x).varpreciocont;
                
                  INSERT INTO ped_solic_posic
                    (oid_soli_posi,
                     cod_posi,
                     num_unid_dema,
                     num_unid_por_aten,
                     val_tasa_impu,
                     soca_oid_soli_cabe,
                     taim_oid_tasa_impu,
                     tpos_oid_tipo_posi,
                     prod_oid_prod,
                     fopa_oid_form_pago,
                     val_prec_cata_unit_loca,
                     val_prec_cont_unit_loca,
                     val_prec_cata_unit_docu,
                     val_prec_conta_unit_docu,
                     val_prec_fact_unit_loca,
                     val_prec_fact_unit_docu,
                     val_prec_sin_impu_unit_loca,
                     val_prec_sin_impu_unit_docu,
                     val_prec_sin_impu_tota_docu,
                     val_impo_desc_unit_loca,
                     val_impo_desc_unit_docu,
                     val_prec_neto_unit_loca,
                     val_prec_neto_tota_docu,
                     val_prec_neto_unit_docu,
                     val_prec_tota_tota_loca,
                     val_prec_tota_tota_docu,
                     val_impo_impu_unit_loca,
                     val_impo_impu_unit_docu,
                     val_impo_desc_tota_docu,
                     val_impo_impu_tota_loca,
                     val_impo_impu_tota_docu,
                     val_impo_desc_tota_loca,
                     val_prec_tota_unit_loca,
                     val_prec_tota_unit_docu,
                     val_prec_cont_tota_loca,
                     val_prec_cata_tota_loca,
                     val_prec_cata_tota_docu,
                     val_prec_cont_tota_docu,
                     val_porc_desc,
                     val_prec_cata_tota_loca_unid,
                     num_unid_dema_real,
                     num_unid_compr,
                     val_prec_fact_tota_loca,
                     val_prec_fact_tota_docu,
                     val_prec_sin_impu_tota_loca,
                     val_prec_neto_tota_loca,
                     ofde_oid_deta_ofer,
                     espo_oid_esta_posi,
                     stpo_oid_subt_posi,
                     val_codi_vent,
                     ind_no_impr)
                  VALUES
                    (ped_sopo_seq.nextval,
                     (SELECT MAX(cod_posi)
                        FROM ped_solic_posic
                       WHERE soca_oid_soli_cabe = varoidsoli) + 1,
                     1,
                     1,
                     0,
                     varoidsoli,
                     NULL,
                     1,
                     varprodoidprod,
                     varfopa,
                     varpreciounit,
                     decode(varpreciounit, 0, varpreciocont, 0),
                     varpreciounit,
                     decode(varpreciounit, 0, varpreciocont, 0),
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     NULL,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     NULL,
                     0,
                     1,
                     1,
                     0,
                     0,
                     0,
                     0,
                     varoiddetaofer,
                     4,
                     22,
                     varcodivent,
                     0);
                
                END LOOP;
              END IF;
              EXIT WHEN prod%NOTFOUND;
            END LOOP;
            CLOSE prod;
          
          END LOOP;
        END IF;
        EXIT WHEN ofert_espec1%NOTFOUND;
      END LOOP;
      CLOSE ofert_espec1;
    
    END IF;
  
    IF lnoidestra2 IS NOT NULL THEN
      OPEN ofert_espec2(lnoidestra2, p_oidsoli);
      LOOP
        FETCH ofert_espec2 BULK COLLECT
          INTO ofert_espec2record LIMIT 100000;
      
        IF ofert_espec2record.count > 0 THEN
          FOR x IN ofert_espec2record.first .. ofert_espec2record.last
          LOOP
          
            varoidsoli  := ofert_espec2record(x).varoidsoli;
            vartotapaga := ofert_espec2record(x).vartotapaga;
            varperio    := ofert_espec2record(x).varperio;
            varoidofer  := ofert_espec2record(x).varoidofer;
          
            SELECT MAX(num_unid_dema)
              INTO varuniddema
              FROM ped_solic_posic a,
                   pre_ofert_detal b,
                   pre_ofert       c
             WHERE a.ofde_oid_deta_ofer = b.oid_deta_ofer
               AND b.ofer_oid_ofer = c.oid_ofer
               AND a.soca_oid_soli_cabe = p_oidsoli
               AND c.coes_oid_estr = lnoidestra2;
          
            IF varuniddema > lnlimiteventa THEN
              varuniddema := lnlimiteventa;
            END IF;
          
            DELETE FROM ped_solic_posic b
             WHERE varoidsoli = b.soca_oid_soli_cabe
               AND EXISTS
             (SELECT 1
                      FROM pre_ofert_detal c,
                           pre_ofert       d
                     WHERE b.ofde_oid_deta_ofer = c.oid_deta_ofer
                       AND c.ofer_oid_ofer = d.oid_ofer
                       AND d.coes_oid_estr = lnoidestra2);
          
            OPEN prod2(lnoidestra2, vartotapaga, varperio, varoidofer);
            LOOP
              FETCH prod2 BULK COLLECT
                INTO prod2record LIMIT 100000;
            
              IF prod2record.count > 0 THEN
                FOR x IN prod2record.first .. prod2record.last
                LOOP
                  varoiddetaofer := prod2record(x).varoiddetaofer;
                  varprodoidprod := prod2record(x).varprodoidprod;
                  varfopa        := prod2record(x).varfopa;
                  varcodivent    := prod2record(x).varcodivent;
                  varpreciounit  := prod2record(x).varpreciounit;
                  varpreciocont  := prod2record(x).varpreciocont;
                
                  INSERT INTO ped_solic_posic
                    (oid_soli_posi,
                     cod_posi,
                     num_unid_dema,
                     num_unid_por_aten,
                     val_tasa_impu,
                     soca_oid_soli_cabe,
                     taim_oid_tasa_impu,
                     tpos_oid_tipo_posi,
                     prod_oid_prod,
                     fopa_oid_form_pago,
                     val_prec_cata_unit_loca,
                     val_prec_cont_unit_loca,
                     val_prec_cata_unit_docu,
                     val_prec_conta_unit_docu,
                     val_prec_fact_unit_loca,
                     val_prec_fact_unit_docu,
                     val_prec_sin_impu_unit_loca,
                     val_prec_sin_impu_unit_docu,
                     val_prec_sin_impu_tota_docu,
                     val_impo_desc_unit_loca,
                     val_impo_desc_unit_docu,
                     val_prec_neto_unit_loca,
                     val_prec_neto_tota_docu,
                     val_prec_neto_unit_docu,
                     val_prec_tota_tota_loca,
                     val_prec_tota_tota_docu,
                     val_impo_impu_unit_loca,
                     val_impo_impu_unit_docu,
                     val_impo_desc_tota_docu,
                     val_impo_impu_tota_loca,
                     val_impo_impu_tota_docu,
                     val_impo_desc_tota_loca,
                     val_prec_tota_unit_loca,
                     val_prec_tota_unit_docu,
                     val_prec_cont_tota_loca,
                     val_prec_cata_tota_loca,
                     val_prec_cata_tota_docu,
                     val_prec_cont_tota_docu,
                     val_porc_desc,
                     val_prec_cata_tota_loca_unid,
                     num_unid_dema_real,
                     num_unid_compr,
                     val_prec_fact_tota_loca,
                     val_prec_fact_tota_docu,
                     val_prec_sin_impu_tota_loca,
                     val_prec_neto_tota_loca,
                     ofde_oid_deta_ofer,
                     espo_oid_esta_posi,
                     stpo_oid_subt_posi,
                     val_codi_vent,
                     ind_no_impr)
                  VALUES
                    (ped_sopo_seq.nextval,
                     (SELECT MAX(cod_posi)
                        FROM ped_solic_posic
                       WHERE soca_oid_soli_cabe = varoidsoli) + 1,
                     varuniddema,
                     varuniddema,
                     0,
                     varoidsoli,
                     NULL,
                     1,
                     varprodoidprod,
                     varfopa,
                     varpreciounit,
                     decode(varpreciounit, 0, varpreciocont, 0),
                     varpreciounit,
                     decode(varpreciounit, 0, varpreciocont, 0),
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     NULL,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     NULL,
                     0,
                     varuniddema,
                     varuniddema,
                     0,
                     0,
                     0,
                     0,
                     varoiddetaofer,
                     4,
                     22,
                     varcodivent,
                     0);
                
                END LOOP;
              ELSE
              
                OPEN prod(lnoidestra2, vartotapaga, varperio);
                LOOP
                  FETCH prod BULK COLLECT
                    INTO prodrecord LIMIT 100000;
                
                  IF prodrecord.count > 0 THEN
                    FOR x IN prodrecord.first .. prodrecord.last
                    LOOP
                    
                      varoiddetaofer := prodrecord(x).varoiddetaofer;
                      varprodoidprod := prodrecord(x).varprodoidprod;
                      varfopa        := prodrecord(x).varfopa;
                      varcodivent    := prodrecord(x).varcodivent;
                      varpreciounit  := prodrecord(x).varpreciounit;
                      varpreciocont  := prodrecord(x).varpreciocont;
                    
                      INSERT INTO ped_solic_posic
                        (oid_soli_posi,
                         cod_posi,
                         num_unid_dema,
                         num_unid_por_aten,
                         val_tasa_impu,
                         soca_oid_soli_cabe,
                         taim_oid_tasa_impu,
                         tpos_oid_tipo_posi,
                         prod_oid_prod,
                         fopa_oid_form_pago,
                         val_prec_cata_unit_loca,
                         val_prec_cont_unit_loca,
                         val_prec_cata_unit_docu,
                         val_prec_conta_unit_docu,
                         val_prec_fact_unit_loca,
                         val_prec_fact_unit_docu,
                         val_prec_sin_impu_unit_loca,
                         val_prec_sin_impu_unit_docu,
                         val_prec_sin_impu_tota_docu,
                         val_impo_desc_unit_loca,
                         val_impo_desc_unit_docu,
                         val_prec_neto_unit_loca,
                         val_prec_neto_tota_docu,
                         val_prec_neto_unit_docu,
                         val_prec_tota_tota_loca,
                         val_prec_tota_tota_docu,
                         val_impo_impu_unit_loca,
                         val_impo_impu_unit_docu,
                         val_impo_desc_tota_docu,
                         val_impo_impu_tota_loca,
                         val_impo_impu_tota_docu,
                         val_impo_desc_tota_loca,
                         val_prec_tota_unit_loca,
                         val_prec_tota_unit_docu,
                         val_prec_cont_tota_loca,
                         val_prec_cata_tota_loca,
                         val_prec_cata_tota_docu,
                         val_prec_cont_tota_docu,
                         val_porc_desc,
                         val_prec_cata_tota_loca_unid,
                         num_unid_dema_real,
                         num_unid_compr,
                         val_prec_fact_tota_loca,
                         val_prec_fact_tota_docu,
                         val_prec_sin_impu_tota_loca,
                         val_prec_neto_tota_loca,
                         ofde_oid_deta_ofer,
                         espo_oid_esta_posi,
                         stpo_oid_subt_posi,
                         val_codi_vent,
                         ind_no_impr)
                      VALUES
                        (ped_sopo_seq.nextval,
                         (SELECT MAX(cod_posi)
                            FROM ped_solic_posic
                           WHERE soca_oid_soli_cabe = varoidsoli) + 1,
                         varuniddema,
                         varuniddema,
                         0,
                         varoidsoli,
                         NULL,
                         1,
                         varprodoidprod,
                         varfopa,
                         varpreciounit,
                         decode(varpreciounit, 0, varpreciocont, 0),
                         varpreciounit,
                         decode(varpreciounit, 0, varpreciocont, 0),
                         0,
                         0,
                         0,
                         0,
                         0,
                         0,
                         NULL,
                         0,
                         0,
                         0,
                         0,
                         0,
                         0,
                         0,
                         0,
                         0,
                         0,
                         0,
                         0,
                         0,
                         0,
                         0,
                         0,
                         0,
                         NULL,
                         0,
                         varuniddema,
                         varuniddema,
                         0,
                         0,
                         0,
                         0,
                         varoiddetaofer,
                         4,
                         22,
                         varcodivent,
                         0);
                    
                    END LOOP;
                  END IF;
                  EXIT WHEN prod%NOTFOUND;
                END LOOP;
                CLOSE prod;
              
              END IF;
              EXIT WHEN prod2%NOTFOUND;
            END LOOP;
            CLOSE prod2;
          
            UPDATE ped_solic_cabec
               SET val_tota_paga_loca = nvl((SELECT SUM(val_prec_cata_unit_loca *
                                                       num_unid_dema_real)
                                              FROM ped_solic_posic x
                                             WHERE x.soca_oid_soli_cabe =
                                                   varoidsoli),
                                            0)
             WHERE oid_soli_cabe = varoidsoli;
          
          END LOOP;
        END IF;
        EXIT WHEN ofert_espec2%NOTFOUND;
      END LOOP;
      CLOSE ofert_espec2;
    
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN;
  END ped_pr_ofert_espec;

  /***************************************************************************
  Descripcion       : Ofertas Especiales Colombia
  Fecha Creacion    : 29/01/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ofert_espec2
  (
    p_oidsoli    NUMBER,
    pscodigopais VARCHAR
  )
  --RETURN NUMBER
   IS
  
    lnoidestra3 NUMBER := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_ESTRA_ESPE3');
  
    lnoidestra4 NUMBER := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                               'STO_ESTRA_ESPE4');
  
    lscodperi VARCHAR2(10);
  
    ln_oidclie NUMBER(10);
    ln_tota    NUMBER(10, 2);
    ln_limite  NUMBER(10, 2);
  
  BEGIN
  
    SELECT cod_peri
      INTO lscodperi
      FROM bas_ctrl_fact a
     WHERE a.sta_camp = 0
       AND a.ind_camp_act = 1;
  
    SELECT clie_oid_clie,
           val_tota_paga_loca -
           (SELECT SUM(a.val_prec_cata_unit_loca * a.num_unid_por_aten)
              FROM ped_solic_posic a,
                   pre_ofert_detal b,
                   pre_ofert       c
             WHERE a.soca_oid_soli_cabe = p_oidsoli
               AND a.ofde_oid_deta_ofer = b.oid_deta_ofer
               AND b.ofer_oid_ofer = c.oid_ofer
               AND c.coes_oid_estr IN (lnoidestra3, lnoidestra4))
      INTO ln_oidclie,
           ln_tota
      FROM ped_solic_cabec
     WHERE oid_soli_cabe = p_oidsoli;
  
    BEGIN
      SELECT a.monto
        INTO ln_limite
        FROM ped_desar_venta a
       WHERE a.cod_clie =
             (SELECT cod_clie FROM mae_clien WHERE oid_clie = ln_oidclie)
         AND a.cod_peri = lscodperi;
    EXCEPTION
      WHEN OTHERS THEN
        ln_limite := 9999999;
    END;
  
    IF ln_tota < ln_limite THEN
    
      UPDATE ped_solic_posic x
         SET x.espo_oid_esta_posi = 2,
             x.num_unid_por_aten  = 0,
             x.num_unid_dema_real = 0,
             x.num_unid_compr     = 0,
             x.stpo_oid_subt_posi = 2036
       WHERE x.soca_oid_soli_cabe = p_oidsoli
         AND x.ofde_oid_deta_ofer IN
             (SELECT b.oid_deta_ofer
                FROM ped_solic_posic a,
                     pre_ofert_detal b,
                     pre_ofert       c
               WHERE a.soca_oid_soli_cabe = p_oidsoli
                 AND a.ofde_oid_deta_ofer = b.oid_deta_ofer
                 AND b.ofer_oid_ofer = c.oid_ofer
                 AND c.coes_oid_estr IN (lnoidestra3, lnoidestra4));
    
      UPDATE ped_solic_cabec
         SET val_tota_paga_loca = nvl((SELECT SUM(val_prec_cata_unit_loca *
                                                 num_unid_dema_real)
                                        FROM ped_solic_posic x
                                       WHERE x.soca_oid_soli_cabe =
                                             p_oidsoli),
                                      0)
       WHERE oid_soli_cabe = p_oidsoli;
    
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN;
  END ped_pr_ofert_espec2;

  /**************************************************************************
    Descripcion       : DEvuelve el grupo de proceso de un consolidado
    Autor             : Jose Luis Rodriguez
    Fecha Creacion    : 26/01/2011
  ***************************************************************************/
  FUNCTION ocr_fn_devue_ofer_espe
  (
    p_val_tota_paga_loca IN NUMBER,
    p_perio              IN NUMBER,
    p_estra              IN NUMBER,
    p_oidofer            IN NUMBER
  ) RETURN NUMBER IS
    vn_grupo NUMBER(12);
  BEGIN
    vn_grupo := 0;
  
    SELECT COUNT(1)
      INTO vn_grupo
      FROM (SELECT oid_ofer
              FROM pre_ofert             c,
                   pre_ofert_detal       d,
                   pre_matri_factu_cabec g,
                   pre_promo             h
             WHERE d.ofer_oid_ofer = c.oid_ofer
               AND d.ofer_oid_ofer = c.oid_ofer
               AND c.mfca_oid_cabe = g.oid_cabe
               AND g.perd_oid_peri = p_perio
               AND c.coes_oid_estr = p_estra
               AND c.oid_ofer = h.ofer_oid_ofer
               AND h.val_fact_cuad <= p_val_tota_paga_loca
               AND c.oid_ofer = p_oidofer
             ORDER BY h.val_fact_cuad DESC)
     WHERE rownum = 1;
  
    IF vn_grupo > 0 THEN
      vn_grupo := p_oidofer;
    
    ELSE
    
      SELECT oid_ofer
        INTO vn_grupo
        FROM (SELECT oid_ofer
                FROM pre_ofert             c,
                     pre_ofert_detal       d,
                     pre_matri_factu_cabec g,
                     pre_promo             h
               WHERE d.ofer_oid_ofer = c.oid_ofer
                 AND d.ofer_oid_ofer = c.oid_ofer
                 AND c.mfca_oid_cabe = g.oid_cabe
                 AND g.perd_oid_peri = p_perio
                 AND c.coes_oid_estr = p_estra
                 AND c.oid_ofer = h.ofer_oid_ofer
                 AND h.val_fact_cuad <= p_val_tota_paga_loca
               ORDER BY h.val_fact_cuad DESC)
       WHERE rownum = 1;
    END IF;
  
    RETURN vn_grupo;
  
  EXCEPTION
    WHEN no_data_found THEN
      RETURN - 1;
    
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 1000);
      raise_application_error(-20123,
                              'ERROR OCR_FN_DEVUE_GRUPO_PROCE: ' ||
                              ls_sqlerrm);
  END ocr_fn_devue_ofer_espe;

  /***************************************************************************
  Descripcion       : Generacion de Estadisticas
  Fecha Creacion    : 18/01/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_estadist
  (
    pscodigopais VARCHAR2,
    pnoidsoli    NUMBER
  ) IS
  
    lsindgenestat VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                       'IND_GENE_ESTAT');
  
    --w_filas NUMBER;
    lnoidclie     NUMBER(12);
    lnoidperi     NUMBER(12);
    lscodperi     VARCHAR2(6);
    lnmonto       NUMBER(12, 2);
    lnsoca        NUMBER(12);
    lnfecfact     DATE;
    lnvalnumesoli NUMBER(10);
    lnestat       NUMBER(12);
  
  BEGIN
  
    --w_filas := 100000;
  
    -- Empieza el proceso de Recuperaciones Obligatorias
  
    IF lsindgenestat = 'S' THEN
    
      SELECT a.clie_oid_clie,
             a.perd_oid_peri,
             c.cod_peri,
             a.val_tota_paga_loca,
             a.oid_soli_cabe,
             a.fec_fact,
             a.val_nume_soli,
             d.esta_oid_esta_clie
        INTO lnoidclie,
             lnoidperi,
             lscodperi,
             lnmonto,
             lnsoca,
             lnfecfact,
             lnvalnumesoli,
             lnestat
        FROM ped_solic_cabec       a,
             cra_perio             b,
             seg_perio_corpo       c,
             mae_clien_datos_adici d
       WHERE oid_soli_cabe = pnoidsoli
         AND a.perd_oid_peri = b.oid_peri
         AND b.peri_oid_peri = c.oid_peri
         AND a.clie_oid_clie = d.clie_oid_clie;
    
      INSERT INTO mae_clien_estat
        (oid_clie,
         fec_ulti_actu)
        SELECT lnoidclie,
               SYSDATE
          FROM dual
         WHERE NOT EXISTS
         (SELECT 1 FROM mae_clien_estat WHERE oid_clie = lnoidclie);
    
      UPDATE mae_clien_estat a
         SET a.camp_ulti_pedi = lscodperi,
             a.val_mont_fact  = lnmonto,
             a.fec_ulti_actu  = SYSDATE,
             a.val_nume_pedi  = nvl(a.val_nume_pedi, 0) + 1,
             a.soca_oid_soca  = lnsoca,
             a.fec_fact_ulti  = lnfecfact,
             a.val_nume_soli  = lnvalnumesoli
       WHERE a.oid_clie = lnoidclie;
    
      UPDATE mae_clien_estat a
         SET a.camp_ingr     =
             (SELECT cod_peri
                FROM cra_perio       a1,
                     seg_perio_corpo b1
               WHERE a1.peri_oid_peri = b1.oid_peri
                 AND a1.oid_peri =
                     (SELECT MAX(perd_oid_peri)
                        FROM mae_clien_prime_conta
                       WHERE clie_oid_clie = lnoidclie)),
             a.camp_prim_pedi =
             (SELECT cod_peri
                FROM cra_perio       a1,
                     seg_perio_corpo b1
               WHERE a1.peri_oid_peri = b1.oid_peri
                 AND a1.oid_peri =
                     ((SELECT MIN(perd_oid_peri)
                         FROM ped_solic_cabec
                        WHERE clie_oid_clie = lnoidclie
                          AND tspa_oid_tipo_soli_pais =
                              (SELECT oid_tipo_soli_pais
                                 FROM ped_tipo_solic_pais x,
                                      ped_tipo_solic      y
                                WHERE x.tsol_oid_tipo_soli = y.oid_tipo_soli
                                  AND y.cod_tipo_soli = 'SOC')
                          AND fec_fact IS NOT NULL))),
             a.fec_ulti_actu  = SYSDATE
       WHERE a.oid_clie = lnoidclie
         AND a.camp_ingr IS NULL;
    
      IF lnestat = '8' THEN
        UPDATE mae_clien_estat a
           SET a.camp_ingr     =
               (SELECT cod_peri
                  FROM cra_perio       a1,
                       seg_perio_corpo b1
                 WHERE a1.peri_oid_peri = b1.oid_peri
                   AND a1.oid_peri =
                       (SELECT MAX(perd_oid_peri)
                          FROM mae_clien_prime_conta
                         WHERE clie_oid_clie = lnoidclie)),
               a.camp_prim_pedi =
               (SELECT cod_peri
                  FROM cra_perio       a1,
                       seg_perio_corpo b1
                 WHERE a1.peri_oid_peri = b1.oid_peri
                   AND a1.oid_peri = lnoidperi)
         WHERE oid_clie = lnoidclie;
      
      END IF;
    
    END IF;
  
  END ped_pr_estadist;

  /**************************************************************************
  Descripcion        : Devuelve el numero de unidades reservadas de BEL_STOCK
  Fecha Creacion     : 25/01/2012
  Autor              : Jose Cairampoma
  ***************************************************************************/
  FUNCTION ped_fn_resrv_stock
  (
    pnoidsolicabe NUMBER,
    pnoidperiodo  NUMBER,
    pnoidcliente  NUMBER,
    pnoidproducto NUMBER,
    pnoidalmacen  NUMBER,
    pnuniddemanda NUMBER,
    pscodigoventa VARCHAR2
  ) RETURN NUMBER IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  
    lnsaldostock   NUMBER := 0;
    lnstockreserva NUMBER := 0;
    lntotalstock   NUMBER := 0;
  
  BEGIN
    BEGIN
      SELECT ns.val_sald
        INTO lnsaldostock
        FROM bel_stock ns
       WHERE ns.almc_oid_alma = pnoidalmacen --oidalma
         AND ns.prod_oid_prod = pnoidproducto
         FOR UPDATE;
    EXCEPTION
      WHEN no_data_found THEN
        lnsaldostock := 0;
        INSERT INTO bel_stock
          (oid_stoc,
           almc_oid_alma,
           esme_oid_esta_merc,
           prod_oid_prod,
           val_sald)
        VALUES
          (bel_stck_seq.nextval,
           pnoidalmacen,
           2001,
           pnoidproducto,
           lnsaldostock);
    END;
    BEGIN
      /*OBTENGO LA RESERVA DEL PRODUCTO PARA LA CONSULTORA QUE NO HAYA SIDO ATENDIDA*/
      SELECT num_unid_rese
        INTO lnstockreserva
        FROM ped_reser_stock
       WHERE perd_oid_peri = pnoidperiodo
         AND clie_oid_clie = pnoidcliente
         AND almc_oid_alma = pnoidalmacen
         AND prod_oid_prod = pnoidproducto
         AND val_codi_vent = pscodigoventa;
    EXCEPTION
      WHEN no_data_found THEN
        lnstockreserva := 0;
    END;
  
    lntotalstock := lnsaldostock + lnstockreserva;
    IF lntotalstock < pnuniddemanda THEN
      lnstockreserva := lntotalstock;
    ELSE
      lnstockreserva := pnuniddemanda;
    END IF;
  
    /*ELIMINO LA RESERVA*/
    DELETE ped_reser_stock
     WHERE perd_oid_peri = pnoidperiodo
       AND clie_oid_clie = pnoidcliente
       AND almc_oid_alma = pnoidalmacen
       AND prod_oid_prod = pnoidproducto
       AND val_codi_vent = pscodigoventa;
  
    INSERT INTO ped_reser_stock
      (oid_rese_stoc,
       soca_oid_soli_cabe,
       perd_oid_peri,
       clie_oid_clie,
       almc_oid_alma,
       prod_oid_prod,
       num_unid_rese,
       val_codi_vent)
    VALUES
      (ped_rest_seq.nextval,
       pnoidsolicabe,
       pnoidperiodo,
       pnoidcliente,
       pnoidalmacen,
       pnoidproducto,
       lnstockreserva,
       pscodigoventa);
    /*SUMO EL STOCK RESERVADO*/
    UPDATE bel_stock ns
       SET ns.val_sald = lntotalstock - lnstockreserva
     WHERE ns.almc_oid_alma = pnoidalmacen
       AND ns.prod_oid_prod = pnoidproducto;
  
    COMMIT;
  
    RETURN lnstockreserva;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR PED_FN_RESRV_STOCK: ' || ls_sqlerrm);
    
  END ped_fn_resrv_stock;

  /**************************************************************************
  Descripcion        : Devuelve una marca que identifica si un pedido se ha cambiado
  Fecha Creacion     : 26/06/2013
  Autor              : Jorge Y?pez
  ***************************************************************************/
  FUNCTION ped_fn_gener_marca_ped(pnsecnumedocu NUMBER) RETURN NUMBER IS
  
    lnhash NUMBER := 0;
  
  BEGIN
  
    SELECT SUM(to_number(x.cod_vent) + x.val_unid_dem)
      INTO lnhash
      FROM int_solic_conso_detal x
     WHERE x.sec_nume_docu_cabe = pnsecnumedocu
       AND x.sta_proc = 'A';
  
    RETURN lnhash;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      RETURN 0;
    
  END ped_fn_gener_marca_ped;

  /**************************************************************************
  Descripcion        : Actualiza los productos no digitables de Compuestas Fijas
                       con almacen distinto
  Fecha Creacion     : 07/05/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_almc_posic
  (
    pnoidperiodo NUMBER,
    pdfecha      DATE
  ) IS
  
    CURSOR c_posic IS
    
      SELECT a.oid_soli_cabe,
             d.oid_ofer,
             b.almc_oid_almc
        FROM ped_solic_cabec a,
             ped_solic_posic b,
             pre_ofert_detal c,
             pre_ofert       d
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND b.ofde_oid_deta_ofer = c.oid_deta_ofer
         AND c.ofer_oid_ofer = d.oid_ofer
         AND d.coes_oid_estr = 2002
         AND a.perd_oid_peri = pnoidperiodo
         AND a.fec_prog_fact = pdfecha
         AND b.almc_oid_almc IS NOT NULL;
  
    r_posic c_posic%ROWTYPE;
  
  BEGIN
  
    -- Iteramos sobre el cursor
    OPEN c_posic;
    LOOP
      FETCH c_posic
        INTO r_posic;
      EXIT WHEN c_posic%NOTFOUND;
    
      UPDATE ped_solic_posic x
         SET x.almc_oid_almc = r_posic.almc_oid_almc
       WHERE soca_oid_soli_cabe = r_posic.oid_soli_cabe
         AND almc_oid_almc IS NULL
         AND ofde_oid_deta_ofer IN
             (SELECT oid_deta_ofer
                FROM pre_ofert_detal
               WHERE ofer_oid_ofer = r_posic.oid_ofer);
    
    END LOOP;
  
    -- Cerramos el cursor
    CLOSE c_posic;
  
    --RETURN 0;
    COMMIT;
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_fn_actua_almc_posic: ' ||
                              ls_sqlerrm);
    
  END ped_pr_actua_almc_posic;
  /**************************************************************************
  Descripcion        : Crea oferta MAV en matriz y devuelve el CUV
  Fecha Creacion     : 24/01/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_inser_ofer_mav
  (
    pnoidprod     NUMBER,
    pnoidtipoofer NUMBER,
    pnpreccata    NUMBER,
    pnpreccont    NUMBER,
    pnfopa        NUMBER,
    pnoidperi     NUMBER
  ) RETURN NUMBER IS
  
    lnoidofer     NUMBER := 0;
    lnoiddetaofer NUMBER := 0;
    lnoidmatr     NUMBER := 0;
    lnoidcabe     NUMBER := 0;
    lnnumofer     NUMBER := 0;
    lscodvent     VARCHAR2(6);
  
    lnoidcata    NUMBER := 0;
    pscodigopais VARCHAR2(6);
  
  BEGIN
  
    SELECT DISTINCT cod_pais INTO pscodigopais FROM bas_ctrl_fact;
  
    lnoidcata := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                          'MAV_OIDCATA'),
                     2041);
  
    SELECT oid_cabe
      INTO lnoidcabe
      FROM pre_matri_factu_cabec
     WHERE perd_oid_peri = pnoidperi;
  
    SELECT pre_ofer_seq.nextval INTO lnoidofer FROM dual;
  
    SELECT pre_ofde_seq.nextval INTO lnoiddetaofer FROM dual;
  
    SELECT pre_mafa_seq.nextval INTO lnoidmatr FROM dual;
  
    BEGIN
      SELECT to_char(to_number(nvl(val_codi_vent, '95001')) + 1)
        INTO lscodvent
        FROM (SELECT val_codi_vent
                FROM pre_ofert_detal a,
                     pre_ofert       b
               WHERE a.ofer_oid_ofer = b.oid_ofer
                 AND b.mfca_oid_cabe = lnoidcabe
                 AND val_codi_vent IS NOT NULL
               ORDER BY val_codi_vent DESC)
       WHERE rownum = 1
      --for update
      ;
    EXCEPTION
      WHEN OTHERS THEN
        lnoiddetaofer := 0;
        COMMIT;
        RETURN lnoiddetaofer;
    END;
  
    BEGIN
      SELECT num_ofer + 1
        INTO lnnumofer
        FROM (SELECT num_ofer
                FROM pre_ofert b
               WHERE b.mfca_oid_cabe = lnoidcabe
                 AND coes_oid_estr = 2014
               ORDER BY num_ofer DESC)
       WHERE rownum = 1
      --for update
      ;
    EXCEPTION
      WHEN OTHERS THEN
      
        lnnumofer := 1;
        --continue;
      --commit;
      --return lnoiddetaofer;
    END;
  
    INSERT INTO pre_ofert
      (oid_ofer,
       coes_oid_estr,
       num_ofer,
       num_orde,
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
       argv_oid_argu_vent,
       acce_oid_acce,
       mfca_oid_cabe,
       ind_codi_vent_gene,
       ind_desp_compl,
       ind_desp_auto,
       ind_matr_fact_gene,
       ind_recu_obli,
       ind_regi_esta_gene,
       ocat_oid_cata)
    VALUES
      (lnoidofer,
       2014,
       lnnumofer,
       NULL,
       0,
       0,
       0,
       NULL,
       NULL,
       0,
       NULL,
       NULL,
       pnfopa,
       NULL,
       2001,
       NULL,
       lnoidcabe,
       1,
       0,
       1,
       1,
       NULL,
       NULL,
       lnoidcata);
  
    INSERT INTO pre_ofert_detal
      (oid_deta_ofer,
       ofer_oid_ofer,
       prod_oid_prod,
       num_line_ofer,
       val_text_brev,
       num_unid_esti,
       cod_orig,
       val_fact_repe,
       num_posi_rank,
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
       fopa_oid_form_pago,
       gofe_oid_grup_ofer,
       ind_digi,
       ind_impr_gp,
       ind_codi_vent_gene,
       ind_matr_fact_gene,
       val_posi_pagi,
       val_codi_vent,
       val_cent,
       fec_ulti_actu,
       precio_unitario,
       num_punt_fijo,
       vari_oid_vari,
       prfi_oid_prog_fide,
       num_orde_deta,
       imp_prec_publ,
       fec_digi)
    VALUES
      (lnoiddetaofer,
       lnoidofer,
       pnoidprod,
       1,
       NULL,
       NULL,
       'MAV',
       1,
       NULL,
       NULL,
       pnpreccata,
       pnpreccont,
       NULL,
       NULL,
       NULL,
       lnoidcata,
       pnoidtipoofer,
       4,
       NULL,
       pnfopa,
       NULL,
       0,
       0,
       1,
       1,
       NULL,
       lscodvent,
       NULL,
       SYSDATE,
       pnpreccata,
       NULL,
       NULL,
       NULL,
       NULL,
       NULL,
       SYSDATE);
  
    INSERT INTO pre_matri_factu
      (oid_matr_fact,
       cod_esta,
       mfca_oid_cabe,
       ofde_oid_deta_ofer,
       fec_ulti_actu,
       num_punt_fijo,
       vari_oid_vari,
       prfi_oid_prog_fide,
       ind_matr_fact)
    VALUES
      (lnoidmatr,
       NULL,
       lnoidcabe,
       lnoiddetaofer,
       SYSDATE,
       NULL,
       NULL,
       NULL,
       NULL);
  
    COMMIT;
  
    RETURN lnoiddetaofer;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_fn_inser_ofer_mav: ' || ls_sqlerrm);
    
  END ped_fn_inser_ofer_mav;

  /**************************************************************************
  Descripcion        : Devuelve el siguiente CUV adicional disponible
  Fecha Creacion     : 18/09/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_devue_cuv_adici(pnoidperi NUMBER) RETURN VARCHAR IS
  
    tmp  NUMBER(5) := 95001;
    tmp2 NUMBER(5) := 0;
  
  BEGIN
  
    SELECT COUNT(1)
      INTO tmp2
      FROM pre_ofert             a,
           pre_ofert_detal       b,
           pre_matri_factu_cabec c
     WHERE a.oid_ofer = b.ofer_oid_ofer
       AND a.mfca_oid_cabe = c.oid_cabe
       AND c.perd_oid_peri = pnoidperi
       AND b.val_codi_vent = to_char(tmp);
  
    WHILE tmp2 > 0 AND tmp < 97999
    LOOP
      tmp := tmp + 1;
      SELECT COUNT(1)
        INTO tmp2
        FROM pre_ofert             a,
             pre_ofert_detal       b,
             pre_matri_factu_cabec c
       WHERE a.oid_ofer = b.ofer_oid_ofer
         AND a.mfca_oid_cabe = c.oid_cabe
         AND c.perd_oid_peri = pnoidperi
         AND b.val_codi_vent = to_char(tmp);
    END LOOP;
  
    RETURN to_char(tmp);
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_fn_inser_ofer_mav: ' || ls_sqlerrm);
    
  END ped_fn_devue_cuv_adici;

  /**************************************************************************
  Descripcion        : Devuelve el siguiente CUV adicional disponible
  Fecha Creacion     : 18/09/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_devue_num_gratis
  (
    pscodperi VARCHAR2,
    pnoidsoli NUMBER,
    pnoidofer NUMBER
  ) RETURN NUMBER IS
  
    tmp2 NUMBER(5) := 0;
  
  BEGIN
  
    SELECT nvl(SUM(a.num_unid_por_aten), 0)
      INTO tmp2
      FROM ped_solic_posic a
     WHERE a.soca_oid_soli_cabe = pnoidsoli
       AND a.val_codi_vent IN
           (SELECT val_codi_vent
              FROM pre_cuv_apoya_oespe
             WHERE oid_ofer = pnoidofer);
  
    RETURN tmp2;
  
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_fn_devue_num_gratis: ' ||
                              ls_sqlerrm);
    
  END ped_fn_devue_num_gratis;
  /**************************************************************************
  Descripcion        : Devuelve el origen de un consolidado
  Fecha Creacion     : 18/09/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_devue_orig_conso(pnoidcons NUMBER) RETURN VARCHAR IS
  
    lstemp VARCHAR2(100);
  
  BEGIN
  
    BEGIN
      SELECT CASE
               WHEN a2.ind_rece_ocr = 1 AND a2.ind_rece_web = 0 AND
                    a2.ind_rece_digi = 0 AND a2.ind_rece_dd = 0 AND
                    a2.ind_rece_ivr = 0 THEN
                'OCR'
               WHEN a2.ind_rece_ocr = 0 AND a2.ind_rece_web = 1 AND
                    a2.ind_rece_digi = 0 AND a2.ind_rece_dd = 0 AND
                    a2.ind_rece_ivr = 0 THEN
                'WEB'
               WHEN a2.ind_rece_ocr = 0 AND a2.ind_rece_web = 0 AND
                    a2.ind_rece_digi = 1 AND a2.ind_rece_dd = 0 AND
                    a2.ind_rece_ivr = 0 THEN
                'DIGITADO'
               WHEN a2.ind_rece_ocr = 0 AND a2.ind_rece_web = 0 AND
                    a2.ind_rece_digi = 0 AND a2.ind_rece_dd = 1 AND
                    a2.ind_rece_ivr = 0 THEN
                'DD'
               WHEN a2.ind_rece_ocr = 0 AND a2.ind_rece_web = 0 AND
                    a2.ind_rece_digi = 0 AND a2.ind_rece_dd = 0 AND
                    a2.ind_rece_ivr = 1 THEN
                'IVR'
               ELSE
                'MIXTO'
             END
        INTO lstemp
        FROM ped_solic_cabec       a1,
             int_solic_conso_cabec a2,
             cra_perio             a3,
             seg_perio_corpo       a4,
             mae_clien             a5
       WHERE a1.soca_oid_soli_cabe = pnoidcons
         AND a1.oid_soli_cabe = a2.soca_oid_soli_cabe_refe
         AND a1.perd_oid_peri = a3.oid_peri
         AND a3.peri_oid_peri = a4.oid_peri
         AND a1.clie_oid_clie = a5.oid_clie
         AND a2.cod_clie = a5.cod_clie
         AND a2.cod_peri = a4.cod_peri
         AND rownum = 1;
    
    EXCEPTION
      WHEN OTHERS THEN
        SELECT CASE
                 WHEN a2.ind_rece_ocr = 1 AND a2.ind_rece_web = 0 AND
                      a2.ind_rece_digi = 0 AND a2.ind_rece_dd = 0 AND
                      a2.ind_rece_ivr = 0 THEN
                  'OCR'
                 WHEN a2.ind_rece_ocr = 0 AND a2.ind_rece_web = 1 AND
                      a2.ind_rece_digi = 0 AND a2.ind_rece_dd = 0 AND
                      a2.ind_rece_ivr = 0 THEN
                  'WEB'
                 WHEN a2.ind_rece_ocr = 0 AND a2.ind_rece_web = 0 AND
                      a2.ind_rece_digi = 1 AND a2.ind_rece_dd = 0 AND
                      a2.ind_rece_ivr = 0 THEN
                  'DIGITADO'
                 WHEN a2.ind_rece_ocr = 0 AND a2.ind_rece_web = 0 AND
                      a2.ind_rece_digi = 0 AND a2.ind_rece_dd = 1 AND
                      a2.ind_rece_ivr = 0 THEN
                  'DD'
                 WHEN a2.ind_rece_ocr = 0 AND a2.ind_rece_web = 0 AND
                      a2.ind_rece_digi = 0 AND a2.ind_rece_dd = 0 AND
                      a2.ind_rece_ivr = 1 THEN
                  'IVR'
                 ELSE
                  'MIXTO'
               END
          INTO lstemp
          FROM ped_solic_cabec             a1,
               ped_histo_solic_conso_cabec a2,
               cra_perio                   a3,
               seg_perio_corpo             a4,
               mae_clien                   a5
         WHERE a1.soca_oid_soli_cabe = pnoidcons
           AND a1.oid_soli_cabe = a2.soca_oid_soli_cabe_refe
           AND a1.perd_oid_peri = a3.oid_peri
           AND a3.peri_oid_peri = a4.oid_peri
           AND a1.clie_oid_clie = a5.oid_clie
           AND a2.cod_clie = a5.cod_clie
           AND a2.cod_peri = a4.cod_peri
           AND rownum = 1;
      
    END;
  
    RETURN lstemp;
  
  EXCEPTION
    WHEN OTHERS THEN
    
      RETURN 'MIXTO';
    
  END ped_fn_devue_orig_conso;

  /**************************************************************************
  Descripcion        : Retorna el stock de las reservas no asignadas
  Fecha Creacion     : 25/01/2012
  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ped_pr_retor_stock_resrv_nasig
  (
    pnoidsolicabe NUMBER,
    pnoidperiodo  NUMBER,
    pnoidcliente  NUMBER
  ) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  
    CURSOR c_reserva IS
      SELECT oid_rese_stoc,
             prod_oid_prod,
             almc_oid_alma,
             num_unid_rese
        FROM ped_reser_stock r
       WHERE perd_oid_peri = pnoidperiodo
         AND clie_oid_clie = pnoidcliente
         AND NOT EXISTS
       (SELECT 1
                FROM ped_solic_posic p
               WHERE p.soca_oid_soli_cabe = r.soca_oid_soli_cabe
                 AND r.prod_oid_prod = p.prod_oid_prod
                 AND r.val_codi_vent = p.val_codi_vent);
  
    TYPE t_oid_rese_stoc IS TABLE OF ped_reser_stock.oid_rese_stoc%TYPE;
    TYPE t_prod_oid_prod IS TABLE OF ped_reser_stock.prod_oid_prod%TYPE;
    TYPE t_almc_oid_alma IS TABLE OF ped_reser_stock.almc_oid_alma%TYPE;
    TYPE t_num_unid_rese IS TABLE OF ped_reser_stock.num_unid_rese%TYPE;
  
    v_oid_rese_stoc t_oid_rese_stoc;
    v_prod_oid_prod t_prod_oid_prod;
    v_almc_oid_alma t_almc_oid_alma;
    v_num_unid_rese t_num_unid_rese;
    w_filas         NUMBER := 5000; -- Numero de filas a procesar cada vez
  
    i            BINARY_INTEGER := 0;
    lnsaldostock NUMBER := 0;
  BEGIN
    OPEN c_reserva;
    LOOP
      FETCH c_reserva BULK COLLECT
        INTO v_oid_rese_stoc,
             v_prod_oid_prod,
             v_almc_oid_alma,
             v_num_unid_rese LIMIT w_filas;
    
      IF v_prod_oid_prod.count > 0 THEN
      
        /*RETORNA STOCK*/
        FOR i IN v_prod_oid_prod.first .. v_prod_oid_prod.last
        LOOP
        
          BEGIN
            SELECT ns.val_sald
              INTO lnsaldostock
              FROM bel_stock ns
             WHERE ns.almc_oid_alma = v_almc_oid_alma(i) --oidalma
               AND ns.prod_oid_prod = v_prod_oid_prod(i);
          
            UPDATE bel_stock ns
               SET ns.val_sald = ns.val_sald + v_num_unid_rese(i)
             WHERE ns.almc_oid_alma = v_almc_oid_alma(i)
               AND ns.prod_oid_prod = v_prod_oid_prod(i);
          
          EXCEPTION
            WHEN no_data_found THEN
              INSERT INTO bel_stock
                (oid_stoc,
                 almc_oid_alma,
                 esme_oid_esta_merc,
                 prod_oid_prod,
                 val_sald)
              VALUES
                (bel_stck_seq.nextval,
                 v_almc_oid_alma(i),
                 2001,
                 v_prod_oid_prod(i),
                 v_num_unid_rese(i));
          END;
        
          DELETE ped_reser_stock WHERE oid_rese_stoc = v_oid_rese_stoc(i);
        END LOOP;
      
      END IF;
      EXIT WHEN c_reserva%NOTFOUND;
    
    END LOOP;
    CLOSE c_reserva;
    COMMIT;
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR PED_PR_RETOR_STOCK_RESRV_NASIG: ' ||
                              ls_sqlerrm);
    
  END ped_pr_retor_stock_resrv_nasig;
  /**************************************************************************
  Descripcion        : Retorna el stock de una solicitud
  Fecha Creacion     : 13/02/2012
  Autor              : Jose Cairampoma
  ***************************************************************************/
  PROCEDURE ped_pr_retor_stock_solic(pnoidsolicabe NUMBER) IS
    PRAGMA AUTONOMOUS_TRANSACTION;
  
    CURSOR c_reserva IS
      SELECT r.oid_rese_stoc,
             prod_oid_prod,
             almc_oid_alma,
             num_unid_rese
        FROM ped_reser_stock r
       WHERE soca_oid_soli_cabe = pnoidsolicabe;
  
    TYPE t_oid_rese_stoc IS TABLE OF ped_reser_stock.oid_rese_stoc%TYPE;
    TYPE t_prod_oid_prod IS TABLE OF ped_reser_stock.prod_oid_prod%TYPE;
    TYPE t_almc_oid_alma IS TABLE OF ped_reser_stock.almc_oid_alma%TYPE;
    TYPE t_num_unid_rese IS TABLE OF ped_reser_stock.num_unid_rese%TYPE;
  
    v_oid_rese_stoc t_oid_rese_stoc;
    v_prod_oid_prod t_prod_oid_prod;
    v_almc_oid_alma t_almc_oid_alma;
    v_num_unid_rese t_num_unid_rese;
    w_filas         NUMBER := 5000; -- Numero de filas a procesar cada vez
  
    i BINARY_INTEGER := 0;
  
    lnsaldostock NUMBER := 0;
  BEGIN
    OPEN c_reserva;
    LOOP
      FETCH c_reserva BULK COLLECT
        INTO v_oid_rese_stoc,
             v_prod_oid_prod,
             v_almc_oid_alma,
             v_num_unid_rese LIMIT w_filas;
    
      IF v_prod_oid_prod.count > 0 THEN
      
        /*RETORNA STOCK*/
        FOR i IN v_prod_oid_prod.first .. v_prod_oid_prod.last
        LOOP
        
          BEGIN
            SELECT ns.val_sald
              INTO lnsaldostock
              FROM bel_stock ns
             WHERE ns.almc_oid_alma = v_almc_oid_alma(i) --oidalma
               AND ns.prod_oid_prod = v_prod_oid_prod(i);
          
            UPDATE bel_stock ns
               SET ns.val_sald = ns.val_sald + v_num_unid_rese(i)
             WHERE ns.almc_oid_alma = v_almc_oid_alma(i)
               AND ns.prod_oid_prod = v_prod_oid_prod(i);
          EXCEPTION
            WHEN no_data_found THEN
              INSERT INTO bel_stock
                (oid_stoc,
                 almc_oid_alma,
                 esme_oid_esta_merc,
                 prod_oid_prod,
                 val_sald)
              VALUES
                (bel_stck_seq.nextval,
                 v_almc_oid_alma(i),
                 2001,
                 v_prod_oid_prod(i),
                 v_num_unid_rese(i));
          END;
        
          /*ELIMINO LA RESERVA*/
        
          DELETE ped_reser_stock WHERE oid_rese_stoc = v_oid_rese_stoc(i);
        END LOOP;
      END IF;
      EXIT WHEN c_reserva%NOTFOUND;
    
    END LOOP;
    CLOSE c_reserva;
    COMMIT;
  EXCEPTION
  
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR PED_PR_RETOR_STOCK_SOLIC: ' ||
                              ls_sqlerrm);
    
  END ped_pr_retor_stock_solic;

  /***************************************************************************
  Descripcion       : Monto Minimo SiCC
  Fecha Creacion    : 14/12/2011
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_asignar_cuv
  (
    p_oidperi NUMBER,
    p_oidcata NUMBER
  )
  --RETURN NUMBER
   IS
  
    p_cuenta NUMBER;
    p_cuv    VARCHAR2(10);
  
    CURSOR c_eliminar IS
      SELECT mp.cod_sap           cod_prod,
             mp.cod_unic_vent     cuv,
             e.ties_oid_tipo_estr tipoestra,
             e.tipr_oid_tipo_prod tipoprodu,
             od.oid_deta_ofer     oiddetalle,
             o.oid_ofer,
             o.mfca_oid_cabe
        FROM pre_ofert_detal       od,
             pre_ofert             o,
             mae_produ             mp,
             pre_estra             e,
             pre_catal_orden       oc,
             pre_matri_factu_cabec pmfc
       WHERE od.ofer_oid_ofer = o.oid_ofer
         AND od.prod_oid_prod = mp.oid_prod
         AND o.coes_oid_estr = e.oid_estr
         AND oc.ocat_oid_cata = od.ocat_oid_catal
         AND o.mfca_oid_cabe = pmfc.oid_cabe
         AND pmfc.perd_oid_peri = p_oidperi
         AND (od.ocat_oid_catal = p_oidcata OR p_oidcata IS NULL)
         AND (od.ind_codi_vent_gene = 0 OR od.ind_codi_vent_gene IS NULL)
         AND (o.ind_codi_vent_gene = 0 OR o.ind_codi_vent_gene IS NULL)
       ORDER BY mp.cod_sap,
                oc.num_orde,
                od.ind_impr_gp DESC,
                e.num_orde_asig_cuv;
  
    r_eliminar c_eliminar%ROWTYPE;
  
  BEGIN
  
    OPEN c_eliminar;
    LOOP
      FETCH c_eliminar
        INTO r_eliminar;
      EXIT WHEN c_eliminar%NOTFOUND;
    
      SELECT COUNT(1)
        INTO p_cuenta
        FROM pre_ofert             a,
             pre_ofert_detal       b,
             pre_matri_factu_cabec pmfc
       WHERE a.oid_ofer = b.ofer_oid_ofer
         AND a.mfca_oid_cabe = pmfc.oid_cabe
         AND pmfc.perd_oid_peri = p_oidperi
         AND b.val_codi_vent = r_eliminar.cuv;
    
      IF r_eliminar.tipoestra = 2 OR r_eliminar.tipoestra = 6 OR
         r_eliminar.tipoprodu <> 1 OR p_cuenta > 0 THEN
        p_cuv := ped_fn_devue_cuv_adici(p_oidperi);
        UPDATE pre_ofert_detal a
           SET a.val_codi_vent      = p_cuv,
               a.ind_codi_vent_gene = 1
         WHERE a.oid_deta_ofer = r_eliminar.oiddetalle;
      
      ELSE
        UPDATE pre_ofert_detal a
           SET a.val_codi_vent      = r_eliminar.cuv,
               a.ind_codi_vent_gene = 1
         WHERE a.oid_deta_ofer = r_eliminar.oiddetalle;
      END IF;
    
      UPDATE pre_ofert a
         SET a.ind_codi_vent_gene = 1
       WHERE a.oid_ofer = r_eliminar.oid_ofer
         AND NOT EXISTS (SELECT 1
                FROM pre_ofert_detal b
               WHERE b.ofer_oid_ofer = r_eliminar.oid_ofer
                 AND b.ind_codi_vent_gene = 0);
                 
    delete from pre_matri_factu where ofde_oid_deta_ofer=r_eliminar.oiddetalle;
    
    insert into pre_matri_factu (OID_MATR_FACT,cod_esta, mfca_oid_cabe, ofde_oid_deta_ofer, fec_ulti_actu) 
    values 
    (pre_mafa_seq.nextval, '1', r_eliminar.mfca_oid_cabe, r_eliminar.oiddetalle, sysdate);
    
    update pre_ofert_detal set ind_matr_fact_gene=1 where oid_deta_ofer=r_eliminar.oiddetalle;
    update pre_ofert set ind_matr_fact_gene=1 where oid_ofer=r_eliminar.oid_ofer;
    
    END LOOP;
  
    -- Cerramos el cursor
    CLOSE c_eliminar;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_monto_maxim: ' || ls_sqlerrm);
  END ped_pr_asignar_cuv;

  /***************************************************************************
  Descripcion       : Actualiza Posiciones para pasar Monto Minimo SiCC
  Fecha Creacion    : 28/05/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_posic_mm
  (
    pscodigopais VARCHAR2,
    p_oidperi    NUMBER,
    p_fecfact    DATE
  )
  --RETURN NUMBER
   IS
  
    lsindactuamm VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                      'IND_ACTUA_MM');
  
  BEGIN
  
    IF lsindactuamm = 'S' THEN
    
      UPDATE ped_solic_posic w
         SET w.stpo_oid_subt_posi = 2027,
             w.tpos_oid_tipo_posi = 2029,
             w.val_obse           = 'MARCADO COMO MONTO MINIMO'
       WHERE w.oid_soli_posi IN
             (SELECT x.oid_soli_posi
                FROM ped_solic_posic x,
                     int_solic_conso_cabec y,
                     int_solic_conso_detal z,
                     (SELECT a.oid_soli_cabe,
                             MIN(c.fec_digi) fec_digi
                        FROM ped_solic_cabec       a,
                             int_solic_conso_cabec b,
                             sto_audit_excep       c
                       WHERE a.fec_fact = p_fecfact
                         AND a.perd_oid_peri = p_oidperi
                         AND a.oid_soli_cabe = b.soca_oid_soli_cabe_refe
                         AND b.sec_nume_docu = c.sec_nume_docu
                         AND c.cod_vali = 'OCC-16'
                       GROUP BY a.oid_soli_cabe) z1
               WHERE x.soca_oid_soli_cabe = z1.oid_soli_cabe
                 AND x.soca_oid_soli_cabe = y.soca_oid_soli_cabe_refe
                 AND y.sec_nume_docu = z.sec_nume_docu_cabe
                 AND x.val_codi_vent = z.cod_vent
                 AND trunc(z.fec_digi) > trunc(z1.fec_digi));
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_actua_posic_MM: ' || ls_sqlerrm);
  END ped_pr_actua_posic_mm;

  /***************************************************************************
  Descripcion       : Actualiza N?mero de pedidos web consecutivos por consultora
  Fecha Creacion    : 29/05/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_pedid_web
  (
    pscodigopais VARCHAR2,
    p_oidperi    NUMBER,
    p_fecfact    DATE
  )
  --RETURN NUMBER
   IS
  
    lsindactuamm VARCHAR2(15) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                      'NUM_WEB_NO_OCS');
  
  BEGIN
  
    UPDATE mae_clien_datos_adici x
       SET x.num_pedi_web = nvl(x.num_pedi_web, 0) + 1
     WHERE clie_oid_clie IN
           (SELECT a.clie_oid_clie
              FROM ped_solic_cabec       a,
                   int_solic_conso_cabec b
             WHERE a.fec_fact = p_fecfact
               AND a.perd_oid_peri = p_oidperi
               AND a.oid_soli_cabe = b.soca_oid_soli_cabe_refe
               AND b.ind_rece_web = 1);
  
    UPDATE mae_clien_datos_adici x
       SET x.num_pedi_web = 0
     WHERE x.num_pedi_web < lsindactuamm
       AND clie_oid_clie IN
           (SELECT a.clie_oid_clie
              FROM ped_solic_cabec       a,
                   int_solic_conso_cabec b
             WHERE a.fec_fact = p_fecfact
               AND a.perd_oid_peri = p_oidperi
               AND a.oid_soli_cabe = b.soca_oid_soli_cabe_refe
               AND b.ind_rece_web = 0);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_actua_pedid_web: ' ||
                              ls_sqlerrm);
  END ped_pr_actua_pedid_web;

  /***************************************************************************
  Descripcion       : Reversa solicitud de SiCC a STO
  Fecha Creacion    : 06/06/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_rever_pedid_sto
  (
    pscodigopais VARCHAR2,
    p_oidsoli    NUMBER,
    p_codvali    VARCHAR2,
    p_mtopedid   NUMBER,
    p_motivo     VARCHAR
  )
  --RETURN NUMBER
   IS
  
    ls_codvali     VARCHAR2(15);
    ls_codmens     VARCHAR2(15);
    ls_numlote     VARCHAR2(15);
    ls_indhistexce VARCHAR2(15);
    ls_indgest     VARCHAR2(15);
    ln_secnumedocu NUMBER(15);
    ln_oidclie     NUMBER(15);
    ln_oidperi     NUMBER(15);
    ld_fecprogfact DATE;
  
    v_detalles      NUMBER(12);
    v_montocatdeman NUMBER(12, 2);
  
  BEGIN
  
    ls_codvali := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                       p_codvali);
  
    UPDATE int_solic_conso_cabec consol
       SET consol.fec_prog_fact = ld_fecprogfact,
           --consol.fec_modi      = SYSDATE,
           --consol.usu_modi      = 'SISTEMA',
           --consol.ind_val_mtmi  = '0',
           consol.ind_erro_mtmi = decode(p_motivo,
                                         '1',
                                         '1',
                                         consol.ind_erro_mtmi),
           consol.ind_erro_mtma = decode(p_motivo,
                                         '2',
                                         '1',
                                         consol.ind_erro_mtma),
           consol.ind_ocs_proc  = '0',
           consol.val_mont_pedi = decode(p_motivo,
                                         '1',
                                         p_mtopedid,
                                         '2',
                                         p_mtopedid,
                                         '3',
                                         p_mtopedid,
                                         '4',
                                         p_mtopedid,
                                         NULL),
           consol.ind_erro_desv = decode(p_motivo, '3', '1', '0'),
           consol.ind_erro_mmfc = decode(p_motivo, '4', '1', '0')
     WHERE consol.soca_oid_soli_cabe_refe = p_oidsoli;
  
    ls_codmens := sto_pkg_gener.sto_fn_devue_codig_mensa(pscodigopais,
                                                         'OCC',
                                                         ls_codvali);
  
    SELECT num_lote,
           sec_nume_docu,
           clie_oid_clie,
           fec_prog_fact,
           perd_oid_peri
      INTO ls_numlote,
           ln_secnumedocu,
           ln_oidclie,
           ld_fecprogfact,
           ln_oidperi
      FROM int_solic_conso_cabec
     WHERE soca_oid_soli_cabe_refe = p_oidsoli;
  
    SELECT p.ind_hist_exce,
           ind_gest
      INTO ls_indhistexce,
           ls_indgest
      FROM sto_param_valid p
     WHERE p.cod_pais = pscodigopais
       AND p.cod_tipo_docu = 'OCC'
       AND p.cod_vali = ls_codvali;
  
    IF ls_indhistexce = '1' THEN
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
         cod_mens)
      VALUES
        (pscodigopais,
         'OCC',
         ls_numlote,
         ls_codvali,
         ln_secnumedocu,
         to_char(SYSDATE, 'YYYYMMDDHHMISS'),
         ls_indgest,
         SYSDATE,
         'SISTEMA',
         ls_codmens);
    END IF;
  
    DELETE sto_detal_docum_excep
     WHERE sec_nume_docu = ln_secnumedocu
       AND num_lote = ls_numlote
       AND cod_vali = ls_codvali
       AND cod_tipo_docu = 'OCC';
  
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
       cod_mens)
    VALUES
      (pscodigopais,
       'OCC',
       ls_numlote,
       ls_codvali,
       ln_secnumedocu,
       ls_indgest,
       SYSDATE,
       'SISTEMA',
       SYSDATE,
       'SISTEMA',
       ls_codmens);
  
    UPDATE sto_docum_digit a
       SET a.ind_envi = '0',
           a.ind_rech = '0',
           a.fec_modi = SYSDATE,
           a.usu_modi = 'SISTEMA'
     WHERE a.num_lote = ls_numlote
       AND a.sec_nume_docu = ln_secnumedocu;
  
    UPDATE sto_docum_digit a
       SET a.ind_envi = '0',
           a.ind_rech = '0',
           a.fec_modi = SYSDATE,
           a.usu_modi = 'SISTEMA'
     WHERE a.num_lote = ls_numlote
       AND a.sec_nume_docu_cabe = ln_secnumedocu;
  
    ped_pkg_cuadr_ofert.ped_pr_retor_stock_solic(p_oidsoli);
  
    DELETE FROM car_soli_entr_bloq a
     WHERE a.soca_oid_soli_cabe = p_oidsoli;
  
    DELETE ped_solic_posic p WHERE soca_oid_soli_cabe = p_oidsoli;
  
    DELETE ped_solic_mensa p WHERE soca_oid_soli_cabe = p_oidsoli;
  
    DELETE mav_solic_envio p WHERE soca_oid_soli_cabe = p_oidsoli;
  
    DELETE FROM inc_solic_concu_recom WHERE soca_oid_soli_cabe = p_oidsoli;
  
    DELETE FROM inc_solic_concu_punta WHERE soca_oid_soli_cabe = p_oidsoli;
  
    DELETE FROM inc_solic_concu_recom_temp
     WHERE soca_oid_soli_cabe = p_oidsoli;
  
    DELETE FROM inc_solic_geren_recom WHERE soca_oid_soli_cabe = p_oidsoli;
  
    --ELIMINAMOS INFORMACION DE NUEVO MAV
    sto_pkg_proce_gener.sto_pr_elimi_mav_envio(p_oidsoli);
  
    DELETE ped_solic_cabec p WHERE oid_soli_cabe = p_oidsoli;
  
    DELETE inc_premi_elegi x
     WHERE x.clie_oid_clie = ln_oidclie
       AND EXISTS
     (SELECT 1
              FROM int_solic_conso_detal det
             WHERE sec_nume_docu_cabe = ln_secnumedocu
               AND det.copa_oid_para_gral = x.copa_oid_para_gral
               AND det.panp_oid_para_nive_prem = x.panp_oid_para_nive_prem
               AND det.num_prem = x.num_prem);
  
    --Reversion Ped_solic_cabec_acum2
    SELECT nvl(COUNT(*), 0),
           nvl(SUM(e.num_unid_dema_real * e.val_prec_cata_unit_loca), 0)
      INTO v_detalles,
           v_montocatdeman
      FROM ped_solic_cabec     a,
           ped_solic_cabec     b,
           ped_tipo_solic_pais c,
           ped_tipo_solic      d,
           ped_solic_posic     e
     WHERE a.clie_oid_clie = ln_oidclie
       AND a.perd_oid_peri = ln_oidperi
       AND b.perd_oid_peri = a.perd_oid_peri
       AND a.soca_oid_soli_cabe = b.oid_soli_cabe
       AND e.soca_oid_soli_cabe = a.oid_soli_cabe
       AND a.tspa_oid_tipo_soli_pais = c.oid_tipo_soli_pais
       AND c.tsol_oid_tipo_soli = d.oid_tipo_soli
       AND d.cod_tipo_soli = 'SOC'
       AND a.fec_fact IS NOT NULL
       AND e.espo_oid_esta_posi <> 2
       AND b.esso_oid_esta_soli <> 4;
  
    IF v_detalles = 0 THEN
    
      DELETE FROM ped_solic_cabec_acum2
       WHERE clie_oid_clie = ln_oidclie
         AND perd_oid_peri = ln_oidperi;
    
    ELSE
    
      UPDATE ped_solic_cabec_acum2
         SET val_cant_pedi = 1,
             val_mont_tota = v_montocatdeman
       WHERE clie_oid_clie = ln_oidclie
         AND perd_oid_peri = ln_oidperi;
    
    END IF;
  
    -----------
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_rever_pedid_sto: ' ||
                              ls_sqlerrm);
  END ped_pr_rever_pedid_sto;

  /***************************************************************************
  Descripcion       : Calcula Oferta Especial Navidad
  Fecha Creacion    : 18/07/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ofert_navid2
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psfecha         VARCHAR2
  )
  --RETURN NUMBER
   IS
  
    ln_oidestra NUMBER(10) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                   'STO_ESTRA_NAVID');
  
    CURSOR c_pedid(p_oidestra NUMBER) IS
      SELECT DISTINCT a.oid_soli_cabe,
                      a.almc_oid_alma
        FROM ped_solic_cabec a,
             ped_solic_posic b,
             pre_ofert_detal c,
             pre_ofert       d,
             cra_perio       e,
             seg_perio_corpo f
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND b.ofde_oid_deta_ofer = c.oid_deta_ofer
         AND c.ofer_oid_ofer = d.oid_ofer
         AND d.coes_oid_estr = p_oidestra
         AND a.perd_oid_peri = e.oid_peri
         AND e.peri_oid_peri = f.oid_peri
         AND f.cod_peri = pscodigoperiodo
         AND a.fec_prog_fact = to_date(psfecha, 'dd/mm/yyyy');
  
    r_pedid c_pedid%ROWTYPE;
  
  BEGIN
  
    IF ln_oidestra IS NULL THEN
      RETURN;
    END IF;
  
    OPEN c_pedid(ln_oidestra);
    LOOP
      FETCH c_pedid
        INTO r_pedid;
      EXIT WHEN c_pedid%NOTFOUND;
    
      ped_pr_ofert_navid(pscodigopais,
                         r_pedid.oid_soli_cabe,
                         r_pedid.almc_oid_alma,
                         ln_oidestra);
    
    END LOOP;
    CLOSE c_pedid;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_ofert_navid2: ' || ls_sqlerrm);
  END ped_pr_ofert_navid2;

  /***************************************************************************
  Descripcion       : Calcula Oferta Especial Navidad
  Fecha Creacion    : 18/07/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ofert_navid
  (
    pscodigopais VARCHAR2,
    p_oidsoli    NUMBER,
    p_oidalma    NUMBER,
    p_oidestra   NUMBER
  )
  --RETURN NUMBER
   IS
  
    ln_faltante NUMBER(10);
  
    CURSOR c_prod(p_oidofer NUMBER) IS
      SELECT b.ofer_oid_ofer,
             b.oid_deta_ofer,
             b.prod_oid_prod,
             b.precio_unitario,
             b.imp_prec_posi,
             b.val_codi_vent,
             b.fopa_oid_form_pago
        FROM pre_ofert       a,
             pre_ofert_detal b,
             bel_stock       c
       WHERE a.oid_ofer = b.ofer_oid_ofer
         AND a.coes_oid_estr = p_oidestra
         AND a.oid_ofer = p_oidofer
         AND b.prod_oid_prod = c.prod_oid_prod
         AND c.esme_oid_esta_merc = 2001
         AND c.almc_oid_alma = p_oidalma
         AND c.val_sald > 0
         AND NOT EXISTS
       (SELECT 1
                FROM ped_solic_posic x
               WHERE x.ofde_oid_deta_ofer = b.oid_deta_ofer
                 AND x.soca_oid_soli_cabe = p_oidsoli)
       ORDER BY b.num_posi_rank;
  
    r_prod c_prod%ROWTYPE;
  
    CURSOR c_ofer IS
      SELECT a.oid_soli_posi,
             c.oid_ofer,
             b.oid_deta_ofer,
             a.num_unid_dema_real - a.num_unid_compr faltante
        FROM ped_solic_posic a,
             pre_ofert_detal b,
             pre_ofert       c
       WHERE a.soca_oid_soli_cabe = p_oidsoli
         AND a.ofde_oid_deta_ofer = b.oid_deta_ofer
         AND b.ofer_oid_ofer = c.oid_ofer
         AND c.coes_oid_estr = p_oidestra;
  
    r_ofer c_ofer%ROWTYPE;
  
    CURSOR c_ofer2(p_oidofer NUMBER) IS
      SELECT a.oid_soli_posi,
             a.prod_oid_prod
        FROM ped_solic_posic a,
             pre_ofert_detal b,
             pre_ofert       c,
             bel_stock       d
       WHERE a.soca_oid_soli_cabe = p_oidsoli
         AND a.ofde_oid_deta_ofer = b.oid_deta_ofer
         AND b.ofer_oid_ofer = c.oid_ofer
         AND c.coes_oid_estr = p_oidestra
         AND c.oid_ofer = p_oidofer
         AND a.prod_oid_prod = d.prod_oid_prod
         AND d.esme_oid_esta_merc = 2001
         AND d.val_sald > 0
       ORDER BY a.cod_posi ASC;
  
    r_ofer2 c_ofer2%ROWTYPE;
  
  BEGIN
  
    OPEN c_ofer;
    LOOP
      FETCH c_ofer
        INTO r_ofer;
      EXIT WHEN c_ofer%NOTFOUND;
    
      UPDATE ped_solic_posic psp
         SET psp.num_unid_dema = psp.num_unid_dema_real
       WHERE psp.oid_soli_posi = r_ofer.oid_soli_posi;
    
      OPEN c_prod(r_ofer.oid_ofer);
      LOOP
        FETCH c_prod
          INTO r_prod;
        EXIT WHEN c_prod%NOTFOUND OR r_ofer.faltante = 0;
        INSERT INTO ped_solic_posic
          (oid_soli_posi,
           cod_posi,
           num_unid_dema,
           num_unid_por_aten,
           val_tasa_impu,
           soca_oid_soli_cabe,
           taim_oid_tasa_impu,
           tpos_oid_tipo_posi,
           prod_oid_prod,
           fopa_oid_form_pago,
           val_prec_cata_unit_loca,
           val_prec_cont_unit_loca,
           val_prec_cata_unit_docu,
           val_prec_conta_unit_docu,
           val_prec_fact_unit_loca,
           val_prec_fact_unit_docu,
           val_prec_sin_impu_unit_loca,
           val_prec_sin_impu_unit_docu,
           val_prec_sin_impu_tota_docu,
           val_impo_desc_unit_loca,
           val_impo_desc_unit_docu,
           val_prec_neto_unit_loca,
           val_prec_neto_tota_docu,
           val_prec_neto_unit_docu,
           val_prec_tota_tota_loca,
           val_prec_tota_tota_docu,
           val_impo_impu_unit_loca,
           val_impo_impu_unit_docu,
           val_impo_desc_tota_docu,
           val_impo_impu_tota_loca,
           val_impo_impu_tota_docu,
           val_impo_desc_tota_loca,
           val_prec_tota_unit_loca,
           val_prec_tota_unit_docu,
           val_prec_cont_tota_loca,
           val_prec_cata_tota_loca,
           val_prec_cata_tota_docu,
           val_prec_cont_tota_docu,
           val_porc_desc,
           val_prec_cata_tota_loca_unid,
           num_unid_dema_real,
           num_unid_compr,
           val_prec_fact_tota_loca,
           val_prec_fact_tota_docu,
           val_prec_sin_impu_tota_loca,
           val_prec_neto_tota_loca,
           ofde_oid_deta_ofer,
           espo_oid_esta_posi,
           stpo_oid_subt_posi,
           val_codi_vent,
           ind_no_impr)
        VALUES
          (ped_sopo_seq.nextval,
           (SELECT MAX(cod_posi)
              FROM ped_solic_posic
             WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
           1,
           1,
           0,
           p_oidsoli,
           NULL,
           1,
           r_prod.prod_oid_prod,
           r_prod.fopa_oid_form_pago,
           r_prod.precio_unitario,
           decode(r_prod.precio_unitario, 0, r_prod.imp_prec_posi, 0),
           r_prod.precio_unitario,
           decode(r_prod.precio_unitario, 0, r_prod.imp_prec_posi, 0),
           0,
           0,
           0,
           0,
           0,
           0,
           NULL,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           NULL,
           0,
           1,
           1,
           0,
           0,
           0,
           0,
           r_prod.oid_deta_ofer,
           4,
           22,
           r_prod.val_codi_vent,
           0);
      
        UPDATE bel_stock
           SET val_sald = val_sald - 1
         WHERE prod_oid_prod = r_prod.prod_oid_prod
           AND esme_oid_esta_merc = 2001
           AND almc_oid_alma = 2001;
      
        r_ofer.faltante := r_ofer.faltante - 1;
      END LOOP;
      CLOSE c_prod;
    
      WHILE r_ofer.faltante > 0
      LOOP
        OPEN c_ofer2(r_ofer.oid_ofer);
        LOOP
          FETCH c_ofer2
            INTO r_ofer2;
          IF c_ofer2%ROWCOUNT = 0 THEN
            r_ofer.faltante := 0;
          END IF;
        
          EXIT WHEN c_ofer2%NOTFOUND OR r_ofer.faltante = 0;
        
          IF c_ofer2%ROWCOUNT = 0 THEN
            r_ofer.faltante := 0;
          ELSE
          
            UPDATE ped_solic_posic
               SET --num_unid_dema=num_unid_dema+1,
                   num_unid_dema_real = num_unid_dema_real + 1,
                   num_unid_por_aten  = num_unid_por_aten + 1,
                   num_unid_compr     = num_unid_compr + 1
             WHERE oid_soli_posi = r_ofer2.oid_soli_posi;
          
            r_ofer.faltante := r_ofer.faltante - 1;
          
            UPDATE bel_stock
               SET val_sald = val_sald - 1
             WHERE prod_oid_prod = r_ofer2.prod_oid_prod
               AND esme_oid_esta_merc = 2001
               AND almc_oid_alma = 2001;
          
          END IF;
        
        END LOOP;
        CLOSE c_ofer2;
      
      END LOOP;
    
      UPDATE ped_solic_posic psp
         SET psp.num_unid_dema_real = psp.num_unid_compr
       WHERE psp.oid_soli_posi = r_ofer.oid_soli_posi;
    
    END LOOP;
    CLOSE c_ofer;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_ofert_navid: ' || ls_sqlerrm);
  END ped_pr_ofert_navid;

  /***************************************************************************
  Descripcion       : Calcula Oferta Especial Navidad
  Fecha Creacion    : 18/07/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ofert_navid4
  (
    pscodigopais    VARCHAR2,
    pscodigoperiodo VARCHAR2,
    psfecha         VARCHAR2
  )
  --RETURN NUMBER
   IS
  
    ln_oidestra NUMBER(10) := sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                                   'STO_ESTRA_NAVID');
  
    CURSOR c_pedid(p_oidestra NUMBER) IS
      SELECT DISTINCT a.oid_soli_cabe,
                      a.almc_oid_alma
        FROM ped_solic_cabec a,
             ped_solic_posic b,
             cra_perio       e,
             seg_perio_corpo f
       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND a.perd_oid_peri = e.oid_peri
         AND e.peri_oid_peri = f.oid_peri
         AND f.cod_peri = pscodigoperiodo
         AND a.fec_prog_fact = to_date(psfecha, 'dd/mm/yyyy')
         AND a.grpr_oid_grup_proc = 3
         AND EXISTS
       (SELECT 1
                FROM ped_solic_posic
               WHERE soca_oid_soli_cabe = a.oid_soli_cabe
                 AND val_codi_vent IN
                     (SELECT val_codi_vent FROM pre_cuv_apoya_oespe));
  
    r_pedid c_pedid%ROWTYPE;
  
  BEGIN
  
    IF ln_oidestra IS NULL THEN
      RETURN;
    END IF;
  
    DELETE FROM pre_cuv_apoya_oespe;
  
    INSERT INTO pre_cuv_apoya_oespe
      SELECT DISTINCT b.oid_ofer,
                      proxrango.val_codi_vent
        FROM pre_ofert_detal a,
             pre_ofert b,
             pre_matri_factu_cabec c,
             cra_perio d,
             seg_perio_corpo e,
             pre_estra f,
             pre_promo g,
             gen_i18n_sicc_pais gestr,
             mae_produ prod,
             pre_tipo_ofert tof,
             pre_catal pcata,
             (SELECT h.oid_ofer,
                     j.oid_rang_prom,
                     l.val_codi_vent,
                     k.cod_sap,
                     int_pkg_recla.gen_fn_desc_prod(k.oid_prod) descripcion,
                     cat.des_cata,
                     l.num_pagi_cata,
                     v.cod_tipo_ofer tipo_oferta
                FROM pre_ofert             h,
                     pre_promo             i,
                     pre_rango_promo       j,
                     mae_produ             k,
                     pre_ofert_detal       l,
                     pre_ofert             ll,
                     pre_catal             cat,
                     pre_matri_factu_cabec pmfc,
                     cra_perio             crap,
                     seg_perio_corpo       spcorp,
                     pre_tipo_ofert        v
               WHERE h.oid_ofer = i.ofer_oid_ofer
                 AND i.oid_prom = j.pomo_oid_prom
                 AND j.ocat_oid_cata = ll.ocat_oid_cata
                 AND ll.ocat_oid_cata = cat.oid_cata
                 AND l.num_pagi_cata >= j.val_desd
                 AND l.num_pagi_cata <= j.val_hast
                 AND j.cod_tipo_rang = 'R'
                 AND ll.mfca_oid_cabe = pmfc.oid_cabe
                 AND pmfc.perd_oid_peri = crap.oid_peri
                 AND crap.peri_oid_peri = spcorp.oid_peri
                 AND spcorp.cod_peri = pscodigoperiodo
                 AND l.prod_oid_prod = k.oid_prod
                 AND j.ind_excl = 0
                 AND h.mfca_oid_cabe = ll.mfca_oid_cabe
                 AND ll.oid_ofer = l.ofer_oid_ofer
                 AND l.tofe_oid_tipo_ofer = v.oid_tipo_ofer
                 AND k.cod_sap NOT IN
                     (SELECT p.cod_sap
                        FROM pre_ofert       m,
                             pre_promo       n,
                             pre_rango_promo o,
                             mae_produ       p,
                             pre_ofert_detal q
                       WHERE m.oid_ofer = n.ofer_oid_ofer
                         AND n.oid_prom = o.pomo_oid_prom
                         AND o.ocat_oid_cata = q.ocat_oid_catal
                         AND q.num_pagi_cata >= o.val_desd
                         AND q.num_pagi_cata <= o.val_hast
                         AND o.cod_tipo_rang = 'R'
                         AND q.prod_oid_prod = p.oid_prod
                         AND o.ind_excl = 1
                         AND o.pomo_oid_prom = j.pomo_oid_prom)
                 AND k.oid_prod NOT IN
                     (SELECT x.val_desd
                        FROM pre_rango_promo x
                       WHERE x.cod_tipo_rang = 'P'
                         AND x.ind_excl = 1
                         AND x.pomo_oid_prom = i.oid_prom)
              UNION
              SELECT h.oid_ofer,
                     j.oid_rang_prom,
                     l.val_codi_vent,
                     k.cod_sap,
                     int_pkg_recla.gen_fn_desc_prod(k.oid_prod) descripcion,
                     cat.des_cata,
                     l.num_pagi_cata,
                     v.cod_tipo_ofer
                FROM pre_ofert             h,
                     pre_promo             i,
                     pre_rango_promo       j,
                     mae_produ             k,
                     pre_ofert_detal       l,
                     pre_ofert             ll,
                     pre_catal             cat,
                     pre_matri_factu_cabec pmfc,
                     cra_perio             crap,
                     seg_perio_corpo       spcorp,
                     pre_tipo_ofert        v
               WHERE h.oid_ofer = i.ofer_oid_ofer
                 AND i.oid_prom = j.pomo_oid_prom
                 AND j.ocat_oid_cata = ll.ocat_oid_cata
                 AND ll.ocat_oid_cata = cat.oid_cata
                 AND j.cod_tipo_rang = 'P'
                 AND ll.mfca_oid_cabe = pmfc.oid_cabe
                 AND pmfc.perd_oid_peri = crap.oid_peri
                 AND crap.peri_oid_peri = spcorp.oid_peri
                 AND spcorp.cod_peri = pscodigoperiodo
                 AND j.val_desd = k.oid_prod
                 AND k.oid_prod = l.prod_oid_prod
                 AND j.ind_excl = 0
                 AND h.mfca_oid_cabe = ll.mfca_oid_cabe
                 AND ll.oid_ofer = l.ofer_oid_ofer
                 AND l.tofe_oid_tipo_ofer = v.oid_tipo_ofer
               ORDER BY 1,
                        2,
                        3,
                        4) proxrango
       WHERE a.ofer_oid_ofer = b.oid_ofer
         AND b.mfca_oid_cabe = c.oid_cabe
         AND c.perd_oid_peri = d.oid_peri
         AND d.peri_oid_peri = e.oid_peri
         AND b.coes_oid_estr = f.oid_estr
         AND b.oid_ofer = g.ofer_oid_ofer
         AND proxrango.oid_ofer = b.oid_ofer
         AND gestr.val_oid = f.oid_estr
         AND a.prod_oid_prod = prod.oid_prod
         AND a.tofe_oid_tipo_ofer = tof.oid_tipo_ofer
         AND b.ocat_oid_cata = pcata.oid_cata
         AND f.oid_estr = ln_oidestra
         AND e.cod_peri = pscodigoperiodo
         AND gestr.attr_enti = 'PRE_ESTRA';
  
    DELETE FROM pre_contr_produ_tombo;
  
    INSERT INTO pre_contr_produ_tombo
      SELECT b.ofer_oid_ofer,
             b.val_codi_vent,
             0
        FROM pre_ofert             a,
             pre_ofert_detal       b,
             pre_matri_factu_cabec c,
             cra_perio             d,
             seg_perio_corpo       e
       WHERE a.oid_ofer = b.ofer_oid_ofer
         AND a.coes_oid_estr = ln_oidestra
         AND c.perd_oid_peri = d.oid_peri
         AND d.peri_oid_peri = e.oid_peri
         AND a.mfca_oid_cabe = c.oid_cabe
         AND e.cod_peri = pscodigoperiodo;
  
    OPEN c_pedid(ln_oidestra);
    LOOP
      FETCH c_pedid
        INTO r_pedid;
      EXIT WHEN c_pedid%NOTFOUND;
    
      ped_pr_ofert_navid3(pscodigopais,
                          r_pedid.oid_soli_cabe,
                          r_pedid.almc_oid_alma,
                          ln_oidestra,
                          pscodigoperiodo);
    
    END LOOP;
    CLOSE c_pedid;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_ofert_navid4: ' || ls_sqlerrm);
  END ped_pr_ofert_navid4;

  /***************************************************************************
  Descripcion       : Calcula Oferta Especial Navidad
  Fecha Creacion    : 18/07/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_ofert_navid3
  (
    pscodigopais VARCHAR2,
    p_oidsoli    NUMBER,
    p_oidalma    NUMBER,
    p_oidestra   NUMBER,
    pscodperi    VARCHAR2
  )
  --RETURN NUMBER
   IS
  
    ln_faltante         NUMBER(10);
    ln_oiddetaoferraspa NUMBER(10);
    ln_oidprodraspa     NUMBER(10);
    ln_precioraspa      NUMBER(12, 2);
    ln_preciocont       NUMBER(12, 2);
    ln_stockraspa       NUMBER(12);
    ls_cuvraspa         VARCHAR2(12);
  
    CURSOR c_prod(p_oidofer NUMBER) IS
      SELECT b.ofer_oid_ofer,
             b.oid_deta_ofer,
             b.prod_oid_prod,
             b.precio_unitario,
             b.imp_prec_posi,
             b.val_codi_vent,
             b.fopa_oid_form_pago,
             b.val_fact_repe
        FROM pre_ofert             a,
             pre_ofert_detal       b,
             bel_stock             c,
             pre_contr_produ_tombo d
       WHERE a.oid_ofer = b.ofer_oid_ofer
         AND a.coes_oid_estr = p_oidestra
         AND b.val_codi_vent = d.val_codi_vent(+)
         AND a.oid_ofer = p_oidofer
         AND b.prod_oid_prod = c.prod_oid_prod
         AND c.esme_oid_esta_merc = 2001
         AND c.almc_oid_alma = p_oidalma
         AND c.val_sald > 0
         AND NOT EXISTS
       (SELECT 1
                FROM ped_solic_posic x
               WHERE x.ofde_oid_deta_ofer = b.oid_deta_ofer
                 AND x.soca_oid_soli_cabe = p_oidsoli)
       ORDER BY d.num_unid ASC;
  
    r_prod c_prod%ROWTYPE;
  
    CURSOR c_ofer IS
      SELECT c.oid_ofer
             --, b.oid_deta_ofer
            ,
             ped_fn_devue_num_gratis(pscodperi, p_oidsoli, c.oid_ofer) faltante
        FROM --pre_ofert_detal b,
              pre_ofert             c,
             pre_matri_factu_cabec d,
             cra_perio             e,
             seg_perio_corpo       f
       WHERE
      --b.ofer_oid_ofer=c.oid_ofer
      --and
       c.coes_oid_estr = p_oidestra
       AND c.mfca_oid_cabe = d.oid_cabe
       AND d.perd_oid_peri = e.oid_peri
       AND e.peri_oid_peri = f.oid_peri
       AND f.cod_peri = pscodperi;
  
    r_ofer c_ofer%ROWTYPE;
  
    CURSOR c_ofer2(p_oidofer NUMBER) IS
      SELECT a.oid_soli_posi,
             a.prod_oid_prod
        FROM ped_solic_posic a,
             pre_ofert_detal b,
             pre_ofert       c,
             bel_stock       d
       WHERE a.soca_oid_soli_cabe = p_oidsoli
         AND a.ofde_oid_deta_ofer = b.oid_deta_ofer
         AND b.ofer_oid_ofer = c.oid_ofer
         AND c.coes_oid_estr = p_oidestra
         AND c.oid_ofer = p_oidofer
         AND a.prod_oid_prod = d.prod_oid_prod
         AND d.esme_oid_esta_merc = 2001
         AND d.val_sald > 0
       ORDER BY a.cod_posi ASC;
  
    r_ofer2 c_ofer2%ROWTYPE;
  
  BEGIN
  
    UPDATE bel_stock x
       SET x.val_sald = x.val_sald +
                        (SELECT nvl(SUM(b.num_unid_compr), 0)
                           FROM ped_solic_posic b
                          WHERE b.soca_oid_soli_cabe = p_oidsoli
                            AND b.prod_oid_prod = x.prod_oid_prod
                            AND ofde_oid_deta_ofer IN
                                (SELECT b.oid_deta_ofer
                                   FROM pre_ofert_detal       b,
                                        pre_ofert             c,
                                        pre_matri_factu_cabec d,
                                        cra_perio             e,
                                        seg_perio_corpo       f
                                  WHERE b.ofer_oid_ofer = c.oid_ofer
                                    AND c.coes_oid_estr = p_oidestra
                                    AND c.mfca_oid_cabe = d.oid_cabe
                                    AND d.perd_oid_peri = e.oid_peri
                                    AND e.peri_oid_peri = f.oid_peri
                                    AND f.cod_peri = pscodperi))
     WHERE prod_oid_prod IN
           (SELECT b.prod_oid_prod
              FROM pre_ofert_detal       b,
                   pre_ofert             c,
                   pre_matri_factu_cabec d,
                   cra_perio             e,
                   seg_perio_corpo       f
             WHERE b.ofer_oid_ofer = c.oid_ofer
               AND c.coes_oid_estr = p_oidestra
               AND c.mfca_oid_cabe = d.oid_cabe
               AND d.perd_oid_peri = e.oid_peri
               AND e.peri_oid_peri = f.oid_peri
               AND f.cod_peri = pscodperi);
  
    DELETE FROM ped_solic_posic
     WHERE soca_oid_soli_cabe = p_oidsoli
       AND ofde_oid_deta_ofer IN
           (SELECT b.oid_deta_ofer
              FROM pre_ofert_detal       b,
                   pre_ofert             c,
                   pre_matri_factu_cabec d,
                   cra_perio             e,
                   seg_perio_corpo       f
             WHERE b.ofer_oid_ofer = c.oid_ofer
               AND c.coes_oid_estr = p_oidestra
               AND c.mfca_oid_cabe = d.oid_cabe
               AND d.perd_oid_peri = e.oid_peri
               AND e.peri_oid_peri = f.oid_peri
               AND f.cod_peri = pscodperi);
  
    UPDATE bel_stock x
       SET x.val_sald = x.val_sald +
                        (SELECT nvl(SUM(b.num_unid_compr), 0)
                           FROM ped_solic_posic b
                          WHERE b.soca_oid_soli_cabe = p_oidsoli
                            AND b.prod_oid_prod = x.prod_oid_prod)
     WHERE prod_oid_prod IN
           (SELECT g.oid_ofer_raspa FROM pre_apoya_raspa g);
  
    DELETE FROM ped_solic_posic
     WHERE soca_oid_soli_cabe = p_oidsoli
       AND prod_oid_prod IN
           (SELECT g.oid_ofer_raspa FROM pre_apoya_raspa g);
  
    OPEN c_ofer;
    LOOP
      FETCH c_ofer
        INTO r_ofer;
      EXIT WHEN c_ofer%NOTFOUND;
    
      --update ped_solic_posic psp set psp.num_unid_dema=psp.num_unid_dema_real
      --where psp.oid_soli_posi=r_ofer.oid_soli_posi
      --;
    
      ln_faltante := r_ofer.faltante;
    
      OPEN c_prod(r_ofer.oid_ofer);
      LOOP
        FETCH c_prod
          INTO r_prod;
        EXIT WHEN c_prod%NOTFOUND OR r_ofer.faltante = 0;
        INSERT INTO ped_solic_posic
          (oid_soli_posi,
           cod_posi,
           num_unid_dema,
           num_unid_por_aten,
           val_tasa_impu,
           soca_oid_soli_cabe,
           taim_oid_tasa_impu,
           tpos_oid_tipo_posi,
           prod_oid_prod,
           fopa_oid_form_pago,
           val_prec_cata_unit_loca,
           val_prec_cont_unit_loca,
           val_prec_cata_unit_docu,
           val_prec_conta_unit_docu,
           val_prec_fact_unit_loca,
           val_prec_fact_unit_docu,
           val_prec_sin_impu_unit_loca,
           val_prec_sin_impu_unit_docu,
           val_prec_sin_impu_tota_docu,
           val_impo_desc_unit_loca,
           val_impo_desc_unit_docu,
           val_prec_neto_unit_loca,
           val_prec_neto_tota_docu,
           val_prec_neto_unit_docu,
           val_prec_tota_tota_loca,
           val_prec_tota_tota_docu,
           val_impo_impu_unit_loca,
           val_impo_impu_unit_docu,
           val_impo_desc_tota_docu,
           val_impo_impu_tota_loca,
           val_impo_impu_tota_docu,
           val_impo_desc_tota_loca,
           val_prec_tota_unit_loca,
           val_prec_tota_unit_docu,
           val_prec_cont_tota_loca,
           val_prec_cata_tota_loca,
           val_prec_cata_tota_docu,
           val_prec_cont_tota_docu,
           val_porc_desc,
           val_prec_cata_tota_loca_unid,
           num_unid_dema_real,
           num_unid_compr,
           val_prec_fact_tota_loca,
           val_prec_fact_tota_docu,
           val_prec_sin_impu_tota_loca,
           val_prec_neto_tota_loca,
           ofde_oid_deta_ofer,
           espo_oid_esta_posi,
           stpo_oid_subt_posi,
           val_codi_vent,
           ind_no_impr)
        VALUES
          (ped_sopo_seq.nextval,
           (SELECT MAX(cod_posi)
              FROM ped_solic_posic
             WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
           1 * r_prod.val_fact_repe,
           1 * r_prod.val_fact_repe,
           0,
           p_oidsoli,
           NULL,
           1,
           r_prod.prod_oid_prod,
           r_prod.fopa_oid_form_pago,
           r_prod.precio_unitario,
           decode(r_prod.precio_unitario, 0, r_prod.imp_prec_posi, 0),
           r_prod.precio_unitario,
           decode(r_prod.precio_unitario, 0, r_prod.imp_prec_posi, 0),
           0,
           0,
           0,
           0,
           0,
           0,
           NULL,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           NULL,
           0,
           1 * r_prod.val_fact_repe,
           1 * r_prod.val_fact_repe,
           0,
           0,
           0,
           0,
           r_prod.oid_deta_ofer,
           4,
           22,
           r_prod.val_codi_vent,
           0);
      
        UPDATE bel_stock
           SET val_sald = val_sald - 1
         WHERE prod_oid_prod = r_prod.prod_oid_prod
           AND esme_oid_esta_merc = 2001
           AND almc_oid_alma = 2001;
      
        r_ofer.faltante := r_ofer.faltante - 1;
      
        UPDATE pre_contr_produ_tombo a
           SET a.num_unid = a.num_unid + 1
         WHERE a.val_codi_vent = r_prod.val_codi_vent;
      
      END LOOP;
      CLOSE c_prod;
    
      WHILE r_ofer.faltante > 0
      LOOP
        OPEN c_ofer2(r_ofer.oid_ofer);
        LOOP
          FETCH c_ofer2
            INTO r_ofer2;
          IF c_ofer2%ROWCOUNT = 0 THEN
            r_ofer.faltante := 0;
          END IF;
        
          EXIT WHEN c_ofer2%NOTFOUND OR r_ofer.faltante = 0;
        
          IF c_ofer2%ROWCOUNT = 0 THEN
            r_ofer.faltante := 0;
          ELSE
          
            UPDATE ped_solic_posic
               SET --num_unid_dema=num_unid_dema+1,
                   num_unid_dema_real = num_unid_dema_real + 1,
                   num_unid_por_aten  = num_unid_por_aten + 1,
                   num_unid_compr     = num_unid_compr + 1
             WHERE oid_soli_posi = r_ofer2.oid_soli_posi;
          
            r_ofer.faltante := r_ofer.faltante - 1;
          
            UPDATE bel_stock
               SET val_sald = val_sald - 1
             WHERE prod_oid_prod = r_ofer2.prod_oid_prod
               AND esme_oid_esta_merc = 2001
               AND almc_oid_alma = 2001;
          
          END IF;
        
        END LOOP;
        CLOSE c_ofer2;
      
      END LOOP;
    
      BEGIN
        SELECT b.prod_oid_prod,
               b.oid_deta_ofer,
               b.precio_unitario,
               b.imp_prec_posi,
               b.val_codi_vent
          INTO ln_oidprodraspa,
               ln_oiddetaoferraspa,
               ln_precioraspa,
               ln_preciocont,
               ls_cuvraspa
          FROM pre_apoya_raspa       a,
               pre_ofert_detal       b,
               pre_ofert             c,
               pre_matri_factu_cabec d,
               cra_perio             e,
               seg_perio_corpo       f
         WHERE a.oid_ofer_navi = r_ofer.oid_ofer
           AND a.oid_ofer_raspa = b.prod_oid_prod
           AND b.ofer_oid_ofer = c.oid_ofer
           AND c.mfca_oid_cabe = d.oid_cabe
           AND d.perd_oid_peri = e.oid_peri
           AND e.peri_oid_peri = f.oid_peri
           AND f.cod_peri = pscodperi
           AND rownum = 1;
      EXCEPTION
        WHEN OTHERS THEN
          ln_oidprodraspa     := NULL;
          ln_oiddetaoferraspa := NULL;
          ln_precioraspa      := NULL;
          ln_preciocont       := NULL;
      END;
    
      BEGIN
        SELECT val_sald
          INTO ln_stockraspa
          FROM bel_stock
         WHERE prod_oid_prod = ln_oidprodraspa;
      EXCEPTION
        WHEN OTHERS THEN
          ln_stockraspa := 0;
      END;
    
      IF ln_oidprodraspa IS NOT NULL AND ln_faltante > 0 THEN
        INSERT INTO ped_solic_posic
          (oid_soli_posi,
           cod_posi,
           num_unid_dema,
           num_unid_por_aten,
           val_tasa_impu,
           soca_oid_soli_cabe,
           taim_oid_tasa_impu,
           tpos_oid_tipo_posi,
           prod_oid_prod,
           fopa_oid_form_pago,
           val_prec_cata_unit_loca,
           val_prec_cont_unit_loca,
           val_prec_cata_unit_docu,
           val_prec_conta_unit_docu,
           val_prec_fact_unit_loca,
           val_prec_fact_unit_docu,
           val_prec_sin_impu_unit_loca,
           val_prec_sin_impu_unit_docu,
           val_prec_sin_impu_tota_docu,
           val_impo_desc_unit_loca,
           val_impo_desc_unit_docu,
           val_prec_neto_unit_loca,
           val_prec_neto_tota_docu,
           val_prec_neto_unit_docu,
           val_prec_tota_tota_loca,
           val_prec_tota_tota_docu,
           val_impo_impu_unit_loca,
           val_impo_impu_unit_docu,
           val_impo_desc_tota_docu,
           val_impo_impu_tota_loca,
           val_impo_impu_tota_docu,
           val_impo_desc_tota_loca,
           val_prec_tota_unit_loca,
           val_prec_tota_unit_docu,
           val_prec_cont_tota_loca,
           val_prec_cata_tota_loca,
           val_prec_cata_tota_docu,
           val_prec_cont_tota_docu,
           val_porc_desc,
           val_prec_cata_tota_loca_unid,
           num_unid_dema_real,
           num_unid_compr,
           val_prec_fact_tota_loca,
           val_prec_fact_tota_docu,
           val_prec_sin_impu_tota_loca,
           val_prec_neto_tota_loca,
           ofde_oid_deta_ofer,
           espo_oid_esta_posi,
           stpo_oid_subt_posi,
           val_codi_vent,
           ind_no_impr)
        VALUES
          (ped_sopo_seq.nextval,
           (SELECT MAX(cod_posi)
              FROM ped_solic_posic
             WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
           ln_faltante,
           ln_faltante,
           0,
           p_oidsoli,
           NULL,
           1,
           ln_oidprodraspa,
           NULL,
           ln_precioraspa,
           decode(ln_precioraspa, 0, ln_preciocont, 0),
           ln_precioraspa,
           decode(ln_precioraspa, 0, ln_preciocont, 0),
           0,
           0,
           0,
           0,
           0,
           0,
           NULL,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           NULL,
           0,
           ln_faltante,
           CASE WHEN ln_stockraspa >= ln_faltante THEN ln_faltante ELSE
           ln_stockraspa END,
           0,
           0,
           0,
           0,
           ln_oiddetaoferraspa,
           4,
           22,
           ls_cuvraspa,
           0);
      
        UPDATE bel_stock
           SET val_sald = CASE
                            WHEN ln_stockraspa >= ln_faltante THEN
                             ln_stockraspa - ln_faltante
                            ELSE
                             0
                          END
         WHERE prod_oid_prod = ln_oidprodraspa
           AND esme_oid_esta_merc = 2001
           AND almc_oid_alma = 2001;
      
      END IF;
    
    END LOOP;
    CLOSE c_ofer;
  
    UPDATE ped_solic_posic x
       SET x.espo_oid_esta_posi = 2
     WHERE x.soca_oid_soli_cabe = p_oidsoli
       AND x.val_codi_vent IN
           (SELECT val_codi_vent FROM pre_cuv_apoya_oespe);
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_ofert_navid3: ' || ls_sqlerrm);
  END ped_pr_ofert_navid3;

  /***************************************************************************
  Descripcion       : Regenera solicitudes de un consolidado
  Fecha Creacion    : 18/09/2013
  Autor             : Jorge Yepez
                     p_periodo = null parametro actual
                     p_periodo = 'P' los cdrs van a la campa?a proxima, aplica para p_tipo = 2
  ***************************************************************************/
  PROCEDURE ped_pr_gener_solic
  (
    pscodigopais VARCHAR2,
    p_oidsoli    NUMBER,
    p_tipo       VARCHAR,
    p_periodo    VARCHAR,
    p_valrecu    VARCHAR2
  )
  --RETURN NUMBER
   IS
  
    ln_nuevoid      NUMBER(12);
    ln_valnumesoli  NUMBER(12);
    l_oid_peri      NUMBER := 0;
    oid_peri_next   NUMBER := 0;
    fec_fina_next   cra_perio.fec_fina%TYPE;
    oid_peri_act    NUMBER := 0;
    fec_fina_act    cra_perio.fec_fina%TYPE;
    l_perd_oid_peri NUMBER := 0;
    l_fec_prog_fact cra_perio.fec_fina%TYPE;
  
    CURSOR c_prod IS
      SELECT a.oid_soli_cabe,
             to_char(fec_fact, 'yy') ano,
             a.fec_prog_fact,
             a.perd_oid_peri
        FROM ped_solic_cabec a
       WHERE soca_oid_soli_cabe = p_oidsoli
         AND ((p_tipo = 1) OR
             (p_tipo = 2 AND a.ind_oc = 0 AND NOT EXISTS
              (SELECT 1
                  FROM ped_tipo_solic_pais y,
                       ped_tipo_solic      z,
                       gen_i18n_sicc_comun gen
                 WHERE y.oid_tipo_soli_pais = a.tspa_oid_tipo_soli_pais
                   AND y.tsol_oid_tipo_soli = z.oid_tipo_soli
                   AND z.oid_tipo_soli = gen.val_oid
                   AND gen.attr_enti = 'PED_TIPO_SOLIC'
                   AND gen.val_i18n LIKE '%NMP%')) OR
             (p_tipo = 3 AND a.ind_oc = 0 AND
             (a.modu_oid_modu = 15 OR
             (a.modu_oid_modu = 1 AND ind_oc = 0)) AND NOT EXISTS
              (SELECT 1
                  FROM ped_tipo_solic_pais y,
                       ped_tipo_solic      z,
                       gen_i18n_sicc_comun gen
                 WHERE y.oid_tipo_soli_pais = a.tspa_oid_tipo_soli_pais
                   AND y.tsol_oid_tipo_soli = z.oid_tipo_soli
                   AND z.oid_tipo_soli = gen.val_oid
                   AND gen.attr_enti = 'PED_TIPO_SOLIC'
                   AND gen.val_i18n LIKE '%NMP%')) OR
             (p_tipo = 4 AND a.ind_oc = 0 AND a.modu_oid_modu = 13));
  
    r_prod c_prod%ROWTYPE;
  
  BEGIN
    ----- Ubica el periodo actual
    SELECT c.oid_peri
      INTO l_oid_peri
      FROM bas_ctrl_fact   a,
           seg_perio_corpo b,
           cra_perio       c
     WHERE a.cod_peri = b.cod_peri
       AND b.oid_peri = c.peri_oid_peri
       AND a.sta_camp = 0
       AND a.ind_camp_act = 1;
  
    ----- Datos del proximo periodo
    SELECT oid_peri,
           fec_fina
      INTO oid_peri_next,
           fec_fina_next
      FROM (SELECT oid_peri,
                   fec_fina
              FROM cra_perio
             WHERE fec_inic > (SELECT fec_inic
                                 FROM cra_perio
                                WHERE oid_peri = l_oid_peri)
             ORDER BY fec_inic ASC)
     WHERE rownum = 1;
  
    ----- Datos del periodo actual
    SELECT oid_peri,
           fec_fina
      INTO oid_peri_act,
           fec_fina_act
      FROM (SELECT oid_peri,
                   fec_fina
              FROM cra_perio
             WHERE fec_inic >= (SELECT fec_inic
                                  FROM cra_perio
                                 WHERE oid_peri = l_oid_peri)
             ORDER BY fec_inic ASC)
     WHERE rownum = 1;
  
    OPEN c_prod;
    LOOP
      FETCH c_prod
        INTO r_prod;
      EXIT WHEN c_prod%NOTFOUND;
    
      ln_nuevoid := ped_soca_seq.nextval;
    
      ln_valnumesoli := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                               'PED001',
                                                               '000',
                                                               1,
                                                               r_prod.ano);
    
      ln_valnumesoli := to_number(r_prod.ano ||
                                  lpad(to_char(ln_valnumesoli), 8, '0'));
    
      l_perd_oid_peri := r_prod.perd_oid_peri;
      l_fec_prog_fact := r_prod.fec_prog_fact;
    
      l_perd_oid_peri := oid_peri_act;
      l_fec_prog_fact := fec_fina_act;
    
      --- Si el periodo es el proximo y solicitudes que no son orden de compra
      IF p_periodo = 'P' AND p_tipo = 2 THEN
        l_perd_oid_peri := oid_peri_next;
        l_fec_prog_fact := fec_fina_next;
      END IF;
    
      --- Si el periodo es el Actual y solicitudes que no son orden de compra
      IF p_periodo = 'A' AND p_tipo = 2 THEN
        l_perd_oid_peri := oid_peri_act;
        l_fec_prog_fact := fec_fina_act;
      END IF;
    
      INSERT INTO ped_solic_cabec
        (oid_soli_cabe,
         fec_prog_fact,
         fec_fact,
         num_clien,
         val_grup_reve,
         tspa_oid_tipo_soli_pais,
         mone_oid_mone,
         tids_oid_tipo_desp,
         almc_oid_alma,
         modu_oid_modu,
         ticl_oid_tipo_clie,
         taim_oid_tasa_impu,
         perd_oid_peri,
         soca_oid_soli_cabe,
         clie_oid_clie,
         clie_oid_clie_rece_fact,
         clie_oid_clie_paga,
         clie_oid_clie_dest,
         cldi_oid_clie_dire,
         tdoc_oid_tipo_docu,
         soci_oid_soci,
         sbac_oid_sbac,
         terr_oid_terr,
         zzon_oid_zona,
         ind_esta,
         ind_impr,
         ind_exen_flet,
         val_nume_soli,
         val_usua,
         val_tasa_impu,
         fec_cron,
         ind_perm_unio_sol,
         ind_gene_cc,
         ind_apli_manu,
         val_tipo_camb,
         num_docu_cont_inte,
         num_docu_orig,
         val_lote_repo_falt,
         fec_repo_falt,
         val_base_flet_loca,
         val_impo_flet_loca,
         val_impo_flet_tota_loca,
         val_impo_flet_sin_impu_tota,
         val_reca_flet_loca,
         val_otro_reca_loca,
         val_tota_paga_loca,
         val_prec_cata_tota_loca,
         val_prec_cata_sin_impu_tota,
         val_prec_fact_tota_loca,
         val_impo_impu_tota_loca,
         val_impo_desc_1_tota_loca,
         val_impo_desc_1_tota_docu,
         val_impo_desc_1_sin_impu_tota,
         val_impo_desc_3_tota_docu,
         val_impo_desc_3_sin_impu_tota,
         val_impo_desc_tota_loca,
         val_impo_dto_1_sin_imp_tot_loc,
         val_impo_redo_loca,
         val_base_flet_docu,
         val_impo_flet_docu,
         val_impo_desc_tota_docu,
         val_impo_flet_sin_impu_docu,
         val_reca_flet_docu,
         val_otro_reca_docu,
         val_tota_flet_docu,
         val_impo_flet_tota_docu,
         val_tota_flet_loca,
         val_tota_paga_docu,
         val_prec_cata_tota_docu,
         val_prec_cata_sin_impu_tota_do,
         val_prec_cont_tota_loca,
         val_prec_cont_sin_impu_tota,
         val_prec_cont_sin_impu_tota_1,
         val_prec_fact_tota_docu,
         val_prec_cata_tota_loc_uni_dem,
         val_prec_neto_tota_docu,
         val_prec_neto_tota_loca,
         val_impo_impu_tota_docu,
         val_impo_redo_docu,
         val_impo_redo_cons_loca,
         val_impo_redo_cons_docu,
         val_unid_dema_real_tota,
         num_unid_por_aten_tota,
         num_unid_aten_tota,
         ind_oc,
         ind_pedi_prue,
         ind_ts_no_conso,
         val_glos_obse,
         val_obse_revi,
         num_prem,
         val_impo_desc_3_tota_loca,
         val_impo_dto_3_sin_imp_tot_loc,
         pais_oid_pais,
         tido_oid_tipo_docu,
         vepo_oid_valo_estr_geop,
         recq_oid_resu_cheq,
         esso_oid_esta_soli,
         copa_oid_para_gene,
         grpr_oid_grup_proc,
         sbti_oid_subt_clie,
         acfi_oid_acce_fisi,
         tspa_oid_tipo_soli_pais_cons,
         fopa_oid_form_pago,
         clie_oid_cons_asoc,
         espe_oid_esta_pedi,
         clso_oid_clas_soli,
         ztad_oid_terr_admi,
         inre_oid_indi_revi,
         oper_oid_oper,
         proc_oid_proc,
         soca_oid_docu_refe,
         tccl_oid_tccl_flet,
         clas_oid_clas_flet,
         val_punt_emis,
         num_lote_fact,
         val_prec_cont_tota_docu,
         ind_inte_lari_gene,
         fec_prog_fact_comp,
         ictp_oid_tipo_prog,
         ictp_oid_conc_tipo_prog,
         val_orig_cheq,
         val_impo_iva_asum_empr,
         val_gana_tota_loca,
         val_gana_tota_docu,
         val_tasa_flet,
         val_reca_flet,
         ind_rece_onli,
         val_tota_gast_admi,
         val_tota_gast_admi_sin_impu,
         val_tota_impu_gast_admi,
         val_impo_rete_desc,
         val_tota_comi_flex,
         val_tota_comi_flex_sin_impu,
         val_impo_impu_comi_flex,
         val_mont_bapl_dcto,
         ind_vali_prol,
         imp_impu_tota_prod_naci,
         imp_iva_impu_tota_prod_naci)
        SELECT ln_nuevoid,
               l_fec_prog_fact, --- FEC_PROG_FACT,
               NULL,
               num_clien,
               val_grup_reve,
               tspa_oid_tipo_soli_pais,
               mone_oid_mone,
               tids_oid_tipo_desp,
               almc_oid_alma,
               modu_oid_modu,
               ticl_oid_tipo_clie,
               taim_oid_tasa_impu,
               l_perd_oid_peri, --- PERD_OID_PERI,
               NULL,
               clie_oid_clie,
               clie_oid_clie_rece_fact,
               clie_oid_clie_paga,
               clie_oid_clie_dest,
               cldi_oid_clie_dire,
               tdoc_oid_tipo_docu,
               soci_oid_soci,
               sbac_oid_sbac,
               terr_oid_terr,
               zzon_oid_zona,
               ind_esta,
               ind_impr,
               ind_exen_flet,
               ln_valnumesoli,
               val_usua,
               val_tasa_impu,
               fec_cron,
               ind_perm_unio_sol,
               ind_gene_cc,
               ind_apli_manu,
               val_tipo_camb,
               num_docu_cont_inte,
               num_docu_orig,
               val_lote_repo_falt,
               NULL,
               val_base_flet_loca,
               val_impo_flet_loca,
               val_impo_flet_tota_loca,
               val_impo_flet_sin_impu_tota,
               val_reca_flet_loca,
               val_otro_reca_loca,
               val_tota_paga_loca,
               val_prec_cata_tota_loca,
               val_prec_cata_sin_impu_tota,
               val_prec_fact_tota_loca,
               val_impo_impu_tota_loca,
               val_impo_desc_1_tota_loca,
               val_impo_desc_1_tota_docu,
               val_impo_desc_1_sin_impu_tota,
               val_impo_desc_3_tota_docu,
               val_impo_desc_3_sin_impu_tota,
               val_impo_desc_tota_loca,
               val_impo_dto_1_sin_imp_tot_loc,
               val_impo_redo_loca,
               val_base_flet_docu,
               val_impo_flet_docu,
               val_impo_desc_tota_docu,
               val_impo_flet_sin_impu_docu,
               val_reca_flet_docu,
               val_otro_reca_docu,
               val_tota_flet_docu,
               val_impo_flet_tota_docu,
               val_tota_flet_loca,
               val_tota_paga_docu,
               val_prec_cata_tota_docu,
               val_prec_cata_sin_impu_tota_do,
               val_prec_cont_tota_loca,
               val_prec_cont_sin_impu_tota,
               val_prec_cont_sin_impu_tota_1,
               val_prec_fact_tota_docu,
               val_prec_cata_tota_loc_uni_dem,
               val_prec_neto_tota_docu,
               val_prec_neto_tota_loca,
               val_impo_impu_tota_docu,
               val_impo_redo_docu,
               val_impo_redo_cons_loca,
               val_impo_redo_cons_docu,
               val_unid_dema_real_tota,
               num_unid_por_aten_tota,
               num_unid_aten_tota,
               ind_oc,
               ind_pedi_prue,
               ind_ts_no_conso,
               'GENERADO POR ANULACION', --- VAL_GLOS_OBSE,
               p_oidsoli, --- VAL_OBSE_REVI,
               num_prem,
               val_impo_desc_3_tota_loca,
               val_impo_dto_3_sin_imp_tot_loc,
               pais_oid_pais,
               tido_oid_tipo_docu,
               vepo_oid_valo_estr_geop,
               recq_oid_resu_cheq,
               esso_oid_esta_soli,
               copa_oid_para_gene,
               3,
               sbti_oid_subt_clie,
               acfi_oid_acce_fisi,
               tspa_oid_tipo_soli_pais_cons,
               fopa_oid_form_pago,
               clie_oid_cons_asoc,
               espe_oid_esta_pedi,
               clso_oid_clas_soli,
               ztad_oid_terr_admi,
               inre_oid_indi_revi,
               oper_oid_oper,
               1,
               soca_oid_docu_refe,
               tccl_oid_tccl_flet,
               clas_oid_clas_flet,
               p_valrecu, --- VAL_PUNT_EMIS,
               num_lote_fact,
               val_prec_cont_tota_docu,
               ind_inte_lari_gene,
               fec_prog_fact_comp,
               ictp_oid_tipo_prog,
               ictp_oid_conc_tipo_prog,
               val_orig_cheq,
               val_impo_iva_asum_empr,
               val_gana_tota_loca,
               val_gana_tota_docu,
               val_tasa_flet,
               val_reca_flet,
               ind_rece_onli,
               val_tota_gast_admi,
               val_tota_gast_admi_sin_impu,
               val_tota_impu_gast_admi,
               val_impo_rete_desc,
               val_tota_comi_flex,
               val_tota_comi_flex_sin_impu,
               val_impo_impu_comi_flex,
               val_mont_bapl_dcto,
               ind_vali_prol,
               imp_impu_tota_prod_naci,
               imp_iva_impu_tota_prod_naci
          FROM ped_solic_cabec
         WHERE oid_soli_cabe = r_prod.oid_soli_cabe;
    
      INSERT INTO ped_solic_posic
        (oid_soli_posi,
         cod_posi,
         num_unid_dema,
         num_unid_por_aten,
         val_tasa_impu,
         soca_oid_soli_cabe,
         taim_oid_tasa_impu,
         tpos_oid_tipo_posi,
         prod_oid_prod,
         fopa_oid_form_pago,
         val_prec_cata_unit_loca,
         val_prec_cont_unit_loca,
         val_prec_cata_unit_docu,
         val_prec_conta_unit_docu,
         val_prec_fact_unit_loca,
         val_prec_fact_unit_docu,
         val_prec_sin_impu_unit_loca,
         val_prec_sin_impu_unit_docu,
         val_prec_sin_impu_tota_docu,
         val_impo_desc_unit_loca,
         val_impo_desc_unit_docu,
         val_prec_neto_unit_loca,
         val_prec_neto_tota_docu,
         val_prec_neto_unit_docu,
         val_prec_tota_tota_loca,
         val_prec_tota_tota_docu,
         val_impo_impu_unit_loca,
         val_impo_impu_unit_docu,
         val_impo_desc_tota_docu,
         val_impo_impu_tota_loca,
         val_impo_impu_tota_docu,
         val_impo_desc_tota_loca,
         val_prec_tota_unit_loca,
         val_prec_tota_unit_docu,
         val_prec_cont_tota_loca,
         val_prec_cata_tota_loca,
         val_prec_cata_tota_docu,
         val_prec_cont_tota_docu,
         val_porc_desc,
         val_prec_cata_tota_loca_unid,
         num_unid_dema_real,
         num_unid_compr,
         val_prec_fact_tota_loca,
         val_prec_fact_tota_docu,
         val_prec_sin_impu_tota_loca,
         val_prec_neto_tota_loca,
         ofde_oid_deta_ofer,
         espo_oid_esta_posi,
         stpo_oid_subt_posi,
         val_codi_vent,
         val_codi_vent_fict,
         ind_no_impr,
         oid_line_oper_recl)
        SELECT ped_sopo_seq.nextval,
               cod_posi,
               num_unid_dema,
               num_unid_por_aten,
               val_tasa_impu,
               ln_nuevoid,
               taim_oid_tasa_impu,
               tpos_oid_tipo_posi,
               prod_oid_prod,
               fopa_oid_form_pago,
               val_prec_cata_unit_loca,
               val_prec_cont_unit_loca,
               val_prec_cata_unit_docu,
               val_prec_conta_unit_docu,
               val_prec_fact_unit_loca,
               val_prec_fact_unit_docu,
               val_prec_sin_impu_unit_loca,
               val_prec_sin_impu_unit_docu,
               val_prec_sin_impu_tota_docu,
               val_impo_desc_unit_loca,
               val_impo_desc_unit_docu,
               val_prec_neto_unit_loca,
               val_prec_neto_tota_docu,
               val_prec_neto_unit_docu,
               val_prec_tota_tota_loca,
               val_prec_tota_tota_docu,
               val_impo_impu_unit_loca,
               val_impo_impu_unit_docu,
               val_impo_desc_tota_docu,
               val_impo_impu_tota_loca,
               val_impo_impu_tota_docu,
               val_impo_desc_tota_loca,
               val_prec_tota_unit_loca,
               val_prec_tota_unit_docu,
               val_prec_cont_tota_loca,
               val_prec_cata_tota_loca,
               val_prec_cata_tota_docu,
               val_prec_cont_tota_docu,
               val_porc_desc,
               val_prec_cata_tota_loca_unid,
               num_unid_dema_real,
               num_unid_compr,
               val_prec_fact_tota_loca,
               val_prec_fact_tota_docu,
               val_prec_sin_impu_tota_loca,
               val_prec_neto_tota_loca,
               ofde_oid_deta_ofer,
               espo_oid_esta_posi,
               stpo_oid_subt_posi,
               val_codi_vent,
               val_codi_vent_fict,
               ind_no_impr,
               oid_line_oper_recl
          FROM ped_solic_posic
         WHERE soca_oid_soli_cabe = r_prod.oid_soli_cabe;
    
    END LOOP;
    CLOSE c_prod;
  
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM, 1, 250);
      raise_application_error(-20123,
                              'ERROR ped_pr_gener_solic: ' || ls_sqlerrm);
  END ped_pr_gener_solic;

  /**************************************************************************
  Descripcion : Proceso que ejecuta el Reporte de Desviaci?n de Pedidos.
  Fecha Creacion : 23/10/2013
  Fecha Modificacion  : 23/10/2013
  Autor : Yahir Rivas Luna
  ***************************************************************************/

  PROCEDURE imp_pr_proce_desvi_pedid_gp3
  (
    p_codigopais       IN VARCHAR2,
    p_promedio         IN VARCHAR2,
    p_desviacion       IN VARCHAR2,
    p_fechafacturacion IN VARCHAR2
  ) IS
  
    lnoidtiposolipais NUMBER;
    lnoidpais         NUMBER;
  
  BEGIN
  
    EXECUTE IMMEDIATE 'TRUNCATE TABLE IMP_REPOR_DESVI_PEDID'; -- eliminamos la data de la tabla temporal
  
    lnoidtiposolipais := fin_pkg_gener.fin_fn_obtie_oid_solic_pais('SOC');
    lnoidpais         := gen_pkg_gener.gen_fn_devuelve_id_pais(p_codigopais);
  
    FOR y IN (SELECT m.cod_clie,
                     a.oid_soli_cabe,
                     gen_pkg_gener.gen_fn_clien_datos(m.cod_clie, 'COD_ZONA') AS zona,
                     gen_pkg_gener.gen_fn_clien_datos(m.cod_clie, 'COD_SECC') AS seccion,
                     fin_pkg_gener.fin_fn_obtie_numer_docum_ident(m.oid_clie) AS dociden,
                     hip_pkg_consu.hip_fn_obtie_prome_venta_pedid('',
                                                                  '',
                                                                  '',
                                                                  '',
                                                                  m.cod_clie,
                                                                  p_promedio) promedio, -- cambiar por valor del parametro
                     (SELECT SUM((val_prec_cata_unit_loca -
                                 nvl(val_impo_desc_unit_loca, 0)) *
                                 num_unid_compr)
                        FROM ped_solic_posic
                       WHERE soca_oid_soli_cabe = a.oid_soli_cabe) monto_pedido,
                     fin_pkg_gener.fin_fn_obtie_descr_estat_clien(m.oid_clie) AS estatus
                FROM ped_solic_cabec a,
                     mae_clien       m
               WHERE a.tspa_oid_tipo_soli_pais = lnoidtiposolipais
                 AND a.grpr_oid_grup_proc = 3
                 AND a.pais_oid_pais = lnoidpais --cambiar el valor por el OID del pais
                 AND a.fec_prog_fact =
                     to_date(p_fechafacturacion, 'dd/mm/yyyy') -- la fecha debe de ser la de facturacion y debe ser recibida por el store
                    --AND A.VAL_TOTA_PAGA_LOCA > 0
                    --AND HIP_PKG_CONSU.HIP_FN_OBTIE_PROME_VENTA_PEDID('','','','',m.COD_CLIE,to_number(p_promedio)) > 0
                 AND a.clie_oid_clie = m.oid_clie
                 AND NOT EXISTS
               (SELECT 1
                        FROM mae_clien_datos_adici x,
                             car_param_carte       y
                       WHERE x.clie_oid_clie = m.oid_clie
                         AND x.niri_oid_nive_ries = y.niri_oid_nive_ries
                         AND y.ind_mont_maxi = 1))
    LOOP
    
      IF (y.promedio > 0 AND (((y.monto_pedido / y.promedio) - 1) * 100 >
         to_number(p_desviacion))) THEN
      
        IF ped_fn_devue_apro_gp2(y.oid_soli_cabe,
                                 sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                      'STO_VAL_DESV_PED'),
                                 p_codigopais) = 0 THEN
        
          ped_pr_rever_pedid_sto(p_codigopais,
                                 y.oid_soli_cabe,
                                 'STO_VAL_DESV_PED',
                                 y.monto_pedido,
                                 '3');
        
          UPDATE int_solic_conso_cabec a
             SET a.val_porc_desv =
                 ((y.monto_pedido / y.promedio) - 1) * 100,
                 a.val_prom_pedi = y.promedio,
                 a.val_mont_real = y.monto_pedido
           WHERE a.soca_oid_soli_cabe_refe = y.oid_soli_cabe;
        
        END IF;
      
      END IF;
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      raise_application_error(-20123,
                              'ERROR IMP_PR_PROCE_DESVI_PEDID_GP3: ' ||
                              substr(SQLERRM, 1, 250));
    
  END imp_pr_proce_desvi_pedid_gp3;

  /**************************************************************************
  Descripcion        : Devuelve si el pedido tiene excepcion para la validacion
  Fecha Creacion     : 18/09/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_devue_apro_gp2
  (
    pnoidsoli NUMBER,
    pscodvali VARCHAR,
    pscodpais VARCHAR
    
  ) RETURN NUMBER IS
  
    lnnumregistros NUMBER;
  
    ln_oidestra NUMBER(10) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodpais,
                                                                       'STO_EXCEP_GP2'),
                                  0);
  
  BEGIN
  
    IF ln_oidestra = 0 THEN
      RETURN 0;
    END IF;
  
    SELECT COUNT(1)
      INTO lnnumregistros
      FROM int_solic_conso_cabec a,
           sto_detal_docum_excep b
     WHERE a.sec_nume_docu = b.sec_nume_docu
       AND a.soca_oid_soli_cabe_refe = pnoidsoli
       AND b.cod_vali = pscodvali
    --and b.ind_apro=1
    ;
  
    IF lnnumregistros = 0 THEN
    
      SELECT COUNT(1)
        INTO lnnumregistros
        FROM mae_clien_unida_admin mcua,
             zon_terri_admin       zta,
             zon_terri             zt,
             zon_secci             zs,
             zon_zona              zz,
             zon_regio             zr,
             mae_clien             mc,
             sto_exclu_valid       ex,
             ped_solic_cabec       ps,
             cra_perio             cp,
             seg_perio_corpo       spc
       WHERE mcua.ztad_oid_terr_admi = zta.oid_terr_admi
         AND zta.zscc_oid_secc = zs.oid_secc
         AND zta.terr_oid_terr = zt.oid_terr
         AND zs.zzon_oid_zona = zz.oid_zona
         AND zz.zorg_oid_regi = zr.oid_regi
         AND ps.perd_oid_peri = cp.oid_peri
         AND cp.peri_oid_peri = spc.oid_peri
         AND mcua.ind_acti = 1
         AND zta.ind_borr = 0
         AND ps.oid_soli_cabe = pnoidsoli
         AND mc.oid_clie = mcua.clie_oid_clie
         AND mc.oid_clie = ps.clie_oid_clie
         AND ex.cod_tipo_docu = 'OCC'
            --AND (ex.cod_orig = v_cod_orig(i) OR ex.cod_orig IS NULL)
         AND (ex.cod_regi = zr.cod_regi OR ex.cod_regi IS NULL)
         AND (ex.cod_zona = zz.cod_zona OR ex.cod_zona IS NULL)
         AND (ex.cod_peri = spc.cod_peri OR ex.cod_peri IS NULL)
         AND (ex.cod_clie = mc.cod_clie OR ex.cod_clie IS NULL)
         AND (ex.fec_proc = ps.fec_prog_fact OR ex.fec_proc IS NULL)
         AND ex.cod_vali = pscodvali;
    
    END IF;
  
    RETURN lnnumregistros;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0;
    
  END ped_fn_devue_apro_gp2;

  /***************************************************************************
  Descripcion       : Cuadre de Ofertas de Revista
  Fecha Creacion    : 11/08/2014
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_revis
  (
    p_oidsoli    NUMBER,
    pscodigopais VARCHAR
  )
  --RETURN NUMBER
   IS
  
    lnunid   NUMBER(12, 2);
    lnprecio NUMBER(12, 2);
    lnnivel  NUMBER(12);
  
    -- Ubica Ofertas de esa campa?a que pasa pedido  
    CURSOR c_prod IS
      SELECT d.oid_nive_ofer,
             d.tip_cuad,
             d.tip_nive
        FROM pre_nivel_ofert d,
             ped_solic_cabec g
       WHERE d.perd_oid_peri = g.perd_oid_peri
         AND g.oid_soli_cabe = p_oidsoli
      --and f.oid_ofer=d.oid_ofer
      ;
  
    r_prod c_prod%ROWTYPE;
  
    -- Detalle de los productos gratis que se asignan por oferta de un nivel especifico
    CURSOR c_prod2(p_oidnivel NUMBER) IS
      SELECT d.oid_deta_ofer,
             e.prod_oid_prod,
             e.precio_unitario,
             d.val_unid,
             d.niof_oid_nive_ofer_rang,
             d.oid_nive_ofer_grat,
             e.fopa_oid_form_pago,
             e.imp_prec_posi,
             e.val_codi_vent
        FROM pre_nivel_ofert_grati d,
             pre_ofert_detal       e
       WHERE d.oid_deta_ofer = e.oid_deta_ofer
         AND d.niof_oid_nive_ofer_rang = p_oidnivel;
  
    r_prod2 c_prod2%ROWTYPE;
  
  BEGIN
    ---null;
    --- revisa las ofertas a procesar
    OPEN c_prod;
    LOOP
      FETCH c_prod
        INTO r_prod;
      EXIT WHEN c_prod%NOTFOUND;
    
      --- Suma las unidades que solicito de la oferta
      --- Se considera si el cuadre es por unidades o por monto
      SELECT SUM(decode(r_prod.tip_cuad,
                        '1',
                        num_unid_por_aten,
                        num_unid_por_aten * val_prec_cata_unit_loca))
        INTO lnunid
        FROM (SELECT DISTINCT c.val_codi_vent,
                              num_unid_por_aten,
                              val_prec_cata_unit_loca
                FROM ped_solic_posic       c,
                     pre_nivel_ofert_produ d
               WHERE c.soca_oid_soli_cabe = p_oidsoli
                 AND c.ofde_oid_deta_ofer = d.oid_deta_ofer
                 AND d.niof_oid_nive_ofer = r_prod.oid_nive_ofer);
    
      --- Ubica el rango que califica segun las unidades 
      BEGIN
        SELECT a.val_prec_unit,
               a.oid_nive_ofer_rang
          INTO lnprecio,
               lnnivel
          FROM pre_nivel_ofert_rango a
         WHERE a.val_rang_infe <= lnunid
           AND a.val_rang_supe >= lnunid
           AND a.niof_oid_nive_ofer = r_prod.oid_nive_ofer;
      EXCEPTION
        WHEN no_data_found THEN
          lnunid := 0;
      END;
    
      -- Si encontro rango para evaluar
      IF lnunid > 0 THEN
        --- Si es de nivel de precios se actualiza precio 
        --- y para lo demas solo se actualiza el oid
        ---if r_prod.tip_nive='1' then              
        UPDATE ped_solic_posic a
           SET val_prec_cata_unit_loca = decode(r_prod.tip_nive,
                                                '1',
                                                lnprecio,
                                                val_prec_cata_unit_loca),
               val_prec_cont_unit_loca = decode(r_prod.tip_nive,
                                                '1',
                                                0,
                                                val_prec_cont_unit_loca),
               oid_nive_ofer_conc           = r_prod.oid_nive_ofer,
               oid_nive_ofer_rang_conc      = lnnivel
         WHERE soca_oid_soli_cabe = p_oidsoli
           AND oid_soli_posi IN
               (SELECT c.oid_soli_posi
                  FROM ped_solic_posic       c,
                       pre_nivel_ofert_produ d
                 WHERE c.soca_oid_soli_cabe = p_oidsoli
                   AND c.ofde_oid_deta_ofer = d.oid_deta_ofer
                   AND d.niof_oid_nive_ofer = r_prod.oid_nive_ofer);
        ---end if;
      
        --- Despacha los gratis del rango si llego a alguno
      
        OPEN c_prod2(lnnivel);
        LOOP
          FETCH c_prod2
            INTO r_prod2;
          EXIT WHEN c_prod2%NOTFOUND;
        
          INSERT INTO ped_solic_posic
            (oid_soli_posi,
             cod_posi,
             num_unid_dema,
             num_unid_por_aten,
             val_tasa_impu,
             soca_oid_soli_cabe,
             taim_oid_tasa_impu,
             tpos_oid_tipo_posi,
             prod_oid_prod,
             fopa_oid_form_pago,
             val_prec_cata_unit_loca,
             val_prec_cont_unit_loca,
             val_prec_cata_unit_docu,
             val_prec_conta_unit_docu,
             val_prec_fact_unit_loca,
             val_prec_fact_unit_docu,
             val_prec_sin_impu_unit_loca,
             val_prec_sin_impu_unit_docu,
             val_prec_sin_impu_tota_docu,
             val_impo_desc_unit_loca,
             val_impo_desc_unit_docu,
             val_prec_neto_unit_loca,
             val_prec_neto_tota_docu,
             val_prec_neto_unit_docu,
             val_prec_tota_tota_loca,
             val_prec_tota_tota_docu,
             val_impo_impu_unit_loca,
             val_impo_impu_unit_docu,
             val_impo_desc_tota_docu,
             val_impo_impu_tota_loca,
             val_impo_impu_tota_docu,
             val_impo_desc_tota_loca,
             val_prec_tota_unit_loca,
             val_prec_tota_unit_docu,
             val_prec_cont_tota_loca,
             val_prec_cata_tota_loca,
             val_prec_cata_tota_docu,
             val_prec_cont_tota_docu,
             val_porc_desc,
             val_prec_cata_tota_loca_unid,
             num_unid_dema_real,
             num_unid_compr,
             val_prec_fact_tota_loca,
             val_prec_fact_tota_docu,
             val_prec_sin_impu_tota_loca,
             val_prec_neto_tota_loca,
             ofde_oid_deta_ofer,
             espo_oid_esta_posi,
             stpo_oid_subt_posi,
             val_codi_vent,
             ind_no_impr,
             oid_nive_ofer_conc,
             oid_nive_ofer_rang_conc,
             oid_nive_ofer_grat_conc)
          VALUES
            (ped_sopo_seq.nextval,
             (SELECT MAX(cod_posi)
                FROM ped_solic_posic
               WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
             r_prod2.val_unid,
             r_prod2.val_unid,
             0,
             p_oidsoli,
             NULL,
             1,
             r_prod2.prod_oid_prod,
             r_prod2.fopa_oid_form_pago,
             r_prod2.precio_unitario,
             decode(r_prod2.precio_unitario, 0, r_prod2.imp_prec_posi, 0),
             r_prod2.precio_unitario,
             decode(r_prod2.precio_unitario, 0, r_prod2.imp_prec_posi, 0),
             0,
             0,
             0,
             0,
             0,
             0,
             NULL,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             NULL,
             0,
             r_prod2.val_unid,
             r_prod2.val_unid,
             0,
             0,
             0,
             0,
             r_prod2.oid_deta_ofer,
             4,
             22,
             r_prod2.val_codi_vent,
             0,
             r_prod.oid_nive_ofer,
             r_prod2.niof_oid_nive_ofer_rang,
             r_prod2.oid_nive_ofer_grat);
        
        END LOOP;
        CLOSE c_prod2;
      END IF;
    END LOOP;
    CLOSE c_prod;

  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN;
  END ped_pr_cuadr_revis;

  /***************************************************************************
  Descripcion       : Cuadre de Ofertas de Revista
  Fecha Creacion    : 11/08/2014
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_cuadr_nx
  (
    p_oidsoli    NUMBER,
    pscodigopais VARCHAR
  )
  --RETURN NUMBER
   IS
  
    lnunid    NUMBER(12);
    lnunidori NUMBER(12);
    lnprecio  NUMBER(12, 2);
    lnnivel   NUMBER(12);
  
    ln_fopa        NUMBER(10);
    ls_codvent     VARCHAR2(15);
    ls_codventori  VARCHAR2(15);
    ln_oiddetaofer NUMBER(10);


    lsindactreemp  VARCHAR2(1);
  
    -- Ubica Ofertas de esa campa?a que pasa pedido
    CURSOR c_prod IS
      SELECT d.oid_nx_ofer,
             d.tip_cuad,
             d.tip_nive
        FROM pre_nx_ofert    d,
             ped_solic_cabec g
       WHERE d.perd_oid_peri = g.perd_oid_peri
         AND g.oid_soli_cabe = p_oidsoli;
  
    r_prod c_prod%ROWTYPE;
  
    -- Detalle de la oferta para calcular 
    CURSOR c_fr(p_oid_nx_ofer NUMBER) IS
      SELECT d.val_fact_repe,
             d.val_prec_unit,
             d.oid_nx_ofer_rang
        FROM pre_nx_ofert_rango d
       WHERE d.niof_oid_nx_ofer = p_oid_nx_ofer
       ORDER BY val_fact_repe DESC;
  
    r_fr c_fr%ROWTYPE;
  
    -- Detalle de los productos que solicito de la oferta
    CURSOR c_prod3(p_oid_nx_ofer NUMBER) IS
      SELECT c.oid_soli_posi,
             c.num_unid_por_aten,
             c.prod_oid_prod
        FROM ped_solic_posic    c,
             pre_ofert_detal    e,
             pre_nx_ofert_produ f
       WHERE c.soca_oid_soli_cabe = p_oidsoli
         AND c.ofde_oid_deta_ofer = e.oid_deta_ofer
         AND e.oid_deta_ofer = f.oid_deta_ofer
         AND f.niof_oid_nx_ofer = p_oid_nx_ofer
         AND c.val_prec_cata_unit_loca = 0
       ORDER BY c.num_unid_por_aten DESC,
                e.val_codi_vent     ASC;
  
    r_prod3 c_prod3%ROWTYPE;
  
    -- Detalle de los productos gratis que se asignan por oferta
    CURSOR c_prod2(p_oidnivel NUMBER) IS
      SELECT d.oid_deta_ofer,
             e.prod_oid_prod,
             e.precio_unitario,
             d.val_unid,
             d.niof_oid_nx_ofer_rang,
             d.oid_nx_ofer_grat,
             e.fopa_oid_form_pago,
             e.imp_prec_posi,
             e.val_codi_vent
        FROM pre_nx_ofert_grati d,
             pre_ofert_detal    e
       WHERE d.oid_deta_ofer = e.oid_deta_ofer
         AND d.niof_oid_nx_ofer_rang = p_oidnivel;
  
    r_prod2 c_prod2%ROWTYPE;
  
    ln_temp    NUMBER(10) := 0;
    ln_temp2   NUMBER(10) := 0;
    ln_temp_p  NUMBER(10) := 0;
    ln_temp2_p NUMBER(10) := 0;
  
  BEGIN
    --null;


    lsindactreemp := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(pscodigopais,
                                                             'STO_IND_ACT_REEMP'),
                        'N');
  
    OPEN c_prod;
    LOOP
      FETCH c_prod
        INTO r_prod;
      EXIT WHEN c_prod%NOTFOUND;
    
      --- Suma las unidades que solicito de la oferta
      SELECT nvl(SUM(num_unid_por_aten), 0)
        INTO lnunid
        FROM (SELECT DISTINCT c.val_codi_vent,
                              num_unid_por_aten
                FROM ped_solic_posic    c,
                     pre_nx_ofert_produ d
               WHERE c.soca_oid_soli_cabe = p_oidsoli
                 AND c.ofde_oid_deta_ofer = d.oid_deta_ofer
                 AND d.niof_oid_nx_ofer = r_prod.oid_nx_ofer);
    
      -- Si solicito algun producto de la oferta se procede a validar
      IF lnunid > 0 THEN
      
        --- Asigna precio cero a todos los productos que solicito de la oferta
        --- Solo cuando es tipo nivel es precio
        IF r_prod.tip_nive = 1 THEN
          UPDATE ped_solic_posic
             SET val_prec_cata_unit_loca = 0,
                 num_unid_orig           = num_unid_por_aten,
                 val_codi_orig           = val_codi_vent
           WHERE oid_soli_posi IN
                 (SELECT DISTINCT c.oid_soli_posi
                    FROM ped_solic_posic    c,
                         pre_nx_ofert_produ d
                   WHERE c.soca_oid_soli_cabe = p_oidsoli
                     AND c.ofde_oid_deta_ofer = d.oid_deta_ofer
                     AND d.niof_oid_nx_ofer = r_prod.oid_nx_ofer);
        END IF;
      
        -- Revisa el detalle de la oferta para validar cual le tocaria              
        OPEN c_fr(r_prod.oid_nx_ofer);
        LOOP
          FETCH c_fr
            INTO r_fr;
          EXIT WHEN c_fr%NOTFOUND;
        
          ln_temp2 := trunc(lnunid / r_fr.val_fact_repe);
          ln_temp  := trunc(lnunid / r_fr.val_fact_repe) *
                      r_fr.val_fact_repe;
        
          ln_temp2_p := trunc(lnunid / r_fr.val_fact_repe);
          ln_temp_p  := trunc(lnunid / r_fr.val_fact_repe) *
                        r_fr.val_fact_repe;
        
          --- Solo cuando es tipo nivel es precio
          IF r_prod.tip_nive = 1 THEN
          
            -- revisa los productos que solicito de la oferta 
            OPEN c_prod3(r_prod.oid_nx_ofer);
            LOOP
              FETCH c_prod3
                INTO r_prod3;
              EXIT WHEN c_prod3%NOTFOUND;
            
              IF r_fr.val_fact_repe > 1 THEN
              
                IF ln_temp > 0 THEN
                
                  IF r_prod3.num_unid_por_aten <= ln_temp THEN
                  
                    --- recupera datos del producto                                 
                    SELECT oid_deta_ofer,
                           val_codi_vent,
                           fopa_oid_form_pago
                      INTO ln_oiddetaofer,
                           ls_codvent,
                           ln_fopa
                      FROM pre_ofert_detal
                     WHERE oid_deta_ofer =
                           ped_fn_devue_cuv_nx(r_prod.oid_nx_ofer,
                                               r_prod3.prod_oid_prod,
                                               p_oidsoli,
                                               r_fr.val_fact_repe);
                  
                    UPDATE ped_solic_posic x
                       SET x.val_prec_cata_unit_loca = r_fr.val_prec_unit,
                           x.val_prec_cont_unit_loca = 0,
                           x.ofde_oid_deta_ofer      = ln_oiddetaofer,
                           x.val_codi_vent           = ls_codvent,
                           x.fopa_oid_form_pago      = ln_fopa,
                           x.oid_nive_ofer           = r_prod.oid_nx_ofer,
                           x.oid_nive_ofer_rang      = r_fr.oid_nx_ofer_rang
                     WHERE x.oid_soli_posi = r_prod3.oid_soli_posi
                       AND x.val_prec_cata_unit_loca = 0;
                  
                    ln_temp := ln_temp - r_prod3.num_unid_por_aten;
                  
                    lnunid := lnunid - r_prod3.num_unid_por_aten;
                  
                  ELSE
                  
                    --- Recupera el CUV original
                    SELECT num_unid_orig,
                           val_codi_orig
                      INTO lnunidori,
                           ls_codventori
                      FROM ped_solic_posic x
                     WHERE x.oid_soli_posi = r_prod3.oid_soli_posi
                       AND x.val_prec_cata_unit_loca = 0;
                  
                    --- actualiza el registro con lo que se evaluo    
                    SELECT oid_deta_ofer,
                           val_codi_vent,
                           fopa_oid_form_pago
                      INTO ln_oiddetaofer,
                           ls_codvent,
                           ln_fopa
                      FROM pre_ofert_detal
                     WHERE oid_deta_ofer =
                           ped_fn_devue_cuv_nx(r_prod.oid_nx_ofer,
                                               r_prod3.prod_oid_prod,
                                               p_oidsoli,
                                               r_fr.val_fact_repe);
                  
                    UPDATE ped_solic_posic x
                       SET x.val_prec_cata_unit_loca = r_fr.val_prec_unit,
                           x.val_prec_cont_unit_loca = 0,
                           num_unid_por_aten         = ln_temp,
                           num_unid_dema_real        = ln_temp,
                           num_unid_compr            = ln_temp,
                           x.ofde_oid_deta_ofer      = ln_oiddetaofer,
                           x.val_codi_vent           = ls_codvent,
                           x.fopa_oid_form_pago      = ln_fopa,
                           x.oid_nive_ofer           = r_prod.oid_nx_ofer,
                           x.oid_nive_ofer_rang      = r_fr.oid_nx_ofer_rang
                     WHERE x.oid_soli_posi = r_prod3.oid_soli_posi
                          --and rownum<=ln_temp
                       AND x.val_prec_cata_unit_loca = 0;
                  
                    --- Ingresa un registro con la diferencia para ser evaluado posteriormente                                   
                    ---lnunid :=  r_prod3.num_unid_por_aten - ln_temp;
                    lnunid :=  lnunid - ln_temp;
                  
                    INSERT INTO ped_solic_posic
                      (oid_soli_posi,
                       cod_posi,
                       num_unid_dema,
                       num_unid_por_aten,
                       val_tasa_impu,
                       soca_oid_soli_cabe,
                       taim_oid_tasa_impu,
                       tpos_oid_tipo_posi,
                       prod_oid_prod,
                       fopa_oid_form_pago,
                       val_prec_cata_unit_loca,
                       val_prec_cont_unit_loca,
                       val_prec_cata_unit_docu,
                       val_prec_conta_unit_docu,
                       val_prec_fact_unit_loca,
                       val_prec_fact_unit_docu,
                       val_prec_sin_impu_unit_loca,
                       val_prec_sin_impu_unit_docu,
                       val_prec_sin_impu_tota_docu,
                       val_impo_desc_unit_loca,
                       val_impo_desc_unit_docu,
                       val_prec_neto_unit_loca,
                       val_prec_neto_tota_docu,
                       val_prec_neto_unit_docu,
                       val_prec_tota_tota_loca,
                       val_prec_tota_tota_docu,
                       val_impo_impu_unit_loca,
                       val_impo_impu_unit_docu,
                       val_impo_desc_tota_docu,
                       val_impo_impu_tota_loca,
                       val_impo_impu_tota_docu,
                       val_impo_desc_tota_loca,
                       val_prec_tota_unit_loca,
                       val_prec_tota_unit_docu,
                       val_prec_cont_tota_loca,
                       val_prec_cata_tota_loca,
                       val_prec_cata_tota_docu,
                       val_prec_cont_tota_docu,
                       val_porc_desc,
                       val_prec_cata_tota_loca_unid,
                       num_unid_dema_real,
                       num_unid_compr,
                       val_prec_fact_tota_loca,
                       val_prec_fact_tota_docu,
                       val_prec_sin_impu_tota_loca,
                       val_prec_neto_tota_loca,
                       ofde_oid_deta_ofer,
                       espo_oid_esta_posi,
                       stpo_oid_subt_posi,
                       val_codi_vent,
                       ind_no_impr,
                       oid_nive_ofer,
                       oid_nive_ofer_rang,
                       num_unid_orig,
                       val_codi_orig)
                    VALUES
                      (ped_sopo_seq.nextval,
                       (SELECT MAX(cod_posi)
                          FROM ped_solic_posic
                         WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
                       r_prod3.num_unid_por_aten - ln_temp, ----lnunid,
                       r_prod3.num_unid_por_aten - ln_temp, ----lnunid,
                       0,
                       p_oidsoli,
                       NULL,
                       1,
                       r_prod3.prod_oid_prod,
                       ln_fopa,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       0,
                       NULL,
                       0,
                       r_prod3.num_unid_por_aten - ln_temp, ----lnunid,
                       r_prod3.num_unid_por_aten - ln_temp, ---lnunid,
                       0,
                       0,
                       0,
                       0,
                       ln_oiddetaofer,
                       4,
                       22,
                       ls_codvent,
                       0,
                       r_prod.oid_nx_ofer,
                       r_fr.oid_nx_ofer_rang,
                       lnunidori,
                       ls_codventori);
                  
                    EXIT;
                  
                  END IF;
                
                END IF;
              
              ELSE
              
                --- recupera datos del producto                                 
                SELECT oid_deta_ofer,
                       val_codi_vent,
                       fopa_oid_form_pago
                  INTO ln_oiddetaofer,
                       ls_codvent,
                       ln_fopa
                  FROM pre_ofert_detal
                 WHERE oid_deta_ofer =
                       ped_fn_devue_cuv_nx(r_prod.oid_nx_ofer,
                                           r_prod3.prod_oid_prod,
                                           p_oidsoli,
                                           r_fr.val_fact_repe);
              
                UPDATE ped_solic_posic x
                   SET x.val_prec_cata_unit_loca = r_fr.val_prec_unit,
                       x.val_prec_cont_unit_loca = 0,
                       x.ofde_oid_deta_ofer      = ln_oiddetaofer,
                       x.val_codi_vent           = ls_codvent,
                       x.fopa_oid_form_pago      = ln_fopa,
                       x.oid_nive_ofer           = r_prod.oid_nx_ofer,
                       x.oid_nive_ofer_rang      = r_fr.oid_nx_ofer_rang
                 WHERE x.oid_soli_posi = r_prod3.oid_soli_posi
                      --and rownum<=ln_temp
                   AND x.val_prec_cata_unit_loca = 0;
              
              END IF;
            
            END LOOP;
            CLOSE c_prod3;
          ELSE
          
            --- Descuenta las unidades que se  esta cuadrando
            lnunid := lnunid - ln_temp;
          
          END IF;
        
          --- Despacha los gratis del rango si llego alguno
          IF ln_temp_p > 0 THEN
          
            OPEN c_prod2(r_fr.oid_nx_ofer_rang);
            LOOP
              FETCH c_prod2
                INTO r_prod2;
              EXIT WHEN c_prod2%NOTFOUND;
            
              INSERT INTO ped_solic_posic
                (oid_soli_posi,
                 cod_posi,
                 num_unid_dema,
                 num_unid_por_aten,
                 val_tasa_impu,
                 soca_oid_soli_cabe,
                 taim_oid_tasa_impu,
                 tpos_oid_tipo_posi,
                 prod_oid_prod,
                 fopa_oid_form_pago,
                 val_prec_cata_unit_loca,
                 val_prec_cont_unit_loca,
                 val_prec_cata_unit_docu,
                 val_prec_conta_unit_docu,
                 val_prec_fact_unit_loca,
                 val_prec_fact_unit_docu,
                 val_prec_sin_impu_unit_loca,
                 val_prec_sin_impu_unit_docu,
                 val_prec_sin_impu_tota_docu,
                 val_impo_desc_unit_loca,
                 val_impo_desc_unit_docu,
                 val_prec_neto_unit_loca,
                 val_prec_neto_tota_docu,
                 val_prec_neto_unit_docu,
                 val_prec_tota_tota_loca,
                 val_prec_tota_tota_docu,
                 val_impo_impu_unit_loca,
                 val_impo_impu_unit_docu,
                 val_impo_desc_tota_docu,
                 val_impo_impu_tota_loca,
                 val_impo_impu_tota_docu,
                 val_impo_desc_tota_loca,
                 val_prec_tota_unit_loca,
                 val_prec_tota_unit_docu,
                 val_prec_cont_tota_loca,
                 val_prec_cata_tota_loca,
                 val_prec_cata_tota_docu,
                 val_prec_cont_tota_docu,
                 val_porc_desc,
                 val_prec_cata_tota_loca_unid,
                 num_unid_dema_real,
                 num_unid_compr,
                 val_prec_fact_tota_loca,
                 val_prec_fact_tota_docu,
                 val_prec_sin_impu_tota_loca,
                 val_prec_neto_tota_loca,
                 ofde_oid_deta_ofer,
                 espo_oid_esta_posi,
                 stpo_oid_subt_posi,
                 val_codi_vent,
                 ind_no_impr,
                 oid_nive_ofer,
                 oid_nive_ofer_rang,
                 oid_nive_ofer_grat)
              VALUES
                (ped_sopo_seq.nextval,
                 (SELECT MAX(cod_posi)
                    FROM ped_solic_posic
                   WHERE soca_oid_soli_cabe = p_oidsoli) + 1,
                 ln_temp2_p, ----- r_prod2.val_unid*ln_temp,
                 r_prod2.val_unid * ln_temp2_p, ----- r_prod2.val_unid*ln_temp,
                 0,
                 p_oidsoli,
                 NULL,
                 1,
                 r_prod2.prod_oid_prod,
                 r_prod2.fopa_oid_form_pago,
                 r_prod2.precio_unitario,
                 decode(r_prod2.precio_unitario,
                        0,
                        r_prod2.imp_prec_posi,
                        0),
                 r_prod2.precio_unitario,
                 decode(r_prod2.precio_unitario,
                        0,
                        r_prod2.imp_prec_posi,
                        0),
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 NULL,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 0,
                 NULL,
                 0,
                 r_prod2.val_unid * ln_temp2_p,
                 r_prod2.val_unid * ln_temp2_p,
                 0,
                 0,
                 0,
                 0,
                 r_prod2.oid_deta_ofer,
                 4,
                 22,
                 r_prod2.val_codi_vent,
                 0,
                 r_prod.oid_nx_ofer,
                 r_prod2.niof_oid_nx_ofer_rang,
                 r_prod2.oid_nx_ofer_grat);
            
            END LOOP;
            CLOSE c_prod2;
          
          END IF;
        
        END LOOP;
        CLOSE c_fr;
      
      END IF;
    
    END LOOP;
    CLOSE c_prod;

    --- activa reemplazo de cuvs
    if lsindactreemp = 'S' then
       ped_pr_reemp_pedid(p_oidsoli,0);
    end if;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN;
  END ped_pr_cuadr_nx;

  /**************************************************************************
  Descripcion        : Devuelve CUV a insertar en proceso de ofertas N por
  Fecha Creacion     : 20/10/2014
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_devue_cuv_nx
  (
    pnoidnxofer NUMBER,
    pnoidprod   NUMBER,
    pnoidsoli   NUMBER,
    pnfacrepe   NUMBER
    
  ) RETURN NUMBER IS
  
    ln_retorno NUMBER(10) := 0;
  
  BEGIN
  
    SELECT MIN(x.oid_deta_ofer)
      INTO ln_retorno
      FROM pre_nx_ofert_produ x,
           pre_ofert_detal    y
     WHERE x.niof_oid_nx_ofer = pnoidnxofer
       AND x.oid_deta_ofer = y.oid_deta_ofer
       AND y.prod_oid_prod = pnoidprod
       AND y.val_fact_repe = pnfacrepe
    /*and not exists (select 1 from ped_solic_posic 
    where soca_oid_soli_cabe=pnoidsoli 
    and ofde_oid_deta_ofer=x.oid_deta_ofer
    )*/
    ;
  
    RETURN ln_retorno;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN 0;
    
  END ped_fn_devue_cuv_nx;

  /**************************************************************************
  Descripcion : Proceso que busca que pedidos tienen descuento de cabecera.
  Fecha Creacion : 12/08/2014
  Fecha Modificacion  : 12/08/2014
  Autor : Jorge Yepez
  ***************************************************************************/

  PROCEDURE ped_pr_descu_cabec
  (
    p_codigopais       IN VARCHAR2,
    p_codperi          IN VARCHAR2,
    p_usuario          IN VARCHAR2,
    p_oidperi          IN NUMBER,
    p_fechafacturacion IN VARCHAR2
  ) IS
  
    lnoidtiposolipais NUMBER;
  
    ls_desccabec VARCHAR2(10) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                          'STO_DESCU_CABEC'),
                                     'N');
  
  BEGIN
  
    IF ls_desccabec = 'S' THEN
    
      lnoidtiposolipais := fin_pkg_gener.fin_fn_obtie_oid_solic_pais('SOC');
    
      FOR y IN (SELECT m.cod_clie,
                       a.oid_soli_cabe,
                       n.mon_desc AS monto
                  FROM ped_solic_cabec a,
                       mae_clien       m,
                       nvs_clien_descu n
                 WHERE a.tspa_oid_tipo_soli_pais = lnoidtiposolipais
                   AND a.grpr_oid_grup_proc = 3
                   AND a.fec_prog_fact =
                       to_date(p_fechafacturacion, 'dd/mm/yyyy') -- la fecha debe de ser la de facturacion y debe ser recibida por el store
                   AND a.clie_oid_clie = m.oid_clie
                   AND a.perd_oid_peri = p_oidperi
                   AND m.cod_clie = n.cod_clie
                   AND n.cam_asig = p_codperi
                   AND n.est_regi <> 9
                   AND n.est_desc = 'P')
      LOOP
      
        UPDATE ped_solic_cabec x
           SET x.val_impo_desc_3_tota_loca = y.monto
         WHERE x.oid_soli_cabe = y.oid_soli_cabe;
      
        UPDATE nvs_clien_descu
           SET est_desc = 'S', -- asignado
               usu_modi = p_usuario,
               fec_modi = SYSDATE
         WHERE cod_clie = y.cod_clie
           AND est_regi <> 9
           AND est_desc = 'P' -- PENDIENTE
           AND cam_asig = p_codperi;
      
      END LOOP;
    
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      raise_application_error(-20123,
                              'ERROR PED_PR_DESCU_CABEC: ' ||
                              substr(SQLERRM, 1, 250));
    
  END ped_pr_descu_cabec;

  /**************************************************************************
  Descripcion : Proceso que busca que pedidos tienen descuento de cabecera
  por canje de premio.
  Fecha Creacion : 18/06/2015
  Fecha Modificacion  : 18/06/2015
  Autor : Jorge Yepez
  ***************************************************************************/

  PROCEDURE ped_pr_descu_cabec2
  (
    p_codigopais       IN VARCHAR2,
    p_codperi          IN VARCHAR2,
    p_usuario          IN VARCHAR2,
    p_oidperi          IN NUMBER,
    p_fechafacturacion IN VARCHAR2
  ) IS
  
    lnoidtiposolipais NUMBER;
  
    ls_desccabec VARCHAR2(10) := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(p_codigopais,
                                                                          'STO_DESCU_CABEC2'),
                                     'N');
  
  BEGIN
  
    IF ls_desccabec = 'S' THEN
    
      lnoidtiposolipais := fin_pkg_gener.fin_fn_obtie_oid_solic_pais('SOC');
    
      FOR y IN (SELECT m.cod_clie,
                       a.oid_soli_cabe,
                       n.mon_desc AS monto
                  FROM ped_solic_cabec       a,
                       mae_clien             m,
                       inc_premi_descu_pedid n
                 WHERE a.tspa_oid_tipo_soli_pais = lnoidtiposolipais
                   AND a.grpr_oid_grup_proc = 4
                   AND a.fec_prog_fact =
                       to_date(p_fechafacturacion, 'dd/mm/yyyy') -- la fecha debe de ser la de facturacion y debe ser recibida por el store
                   AND a.clie_oid_clie = m.oid_clie
                   AND a.perd_oid_peri = p_oidperi
                   AND m.cod_clie = n.cod_clie
                   AND n.cam_proc = p_codperi
                   AND n.est_reg <> 'A'
                   AND n.soca_oid_soli_cabe = a.oid_soli_cabe)
      LOOP
      
        UPDATE ped_solic_cabec x
           SET x.val_impo_desc_4_tota_loca = y.monto
         WHERE x.oid_soli_cabe = y.oid_soli_cabe;
      
        UPDATE inc_premi_descu_pedid
           SET est_desc = 'A', -- asignado
               usu_modi = p_usuario,
               fec_modi = SYSDATE
         WHERE soca_oid_soli_cabe = y.oid_soli_cabe
           AND cod_clie = y.cod_clie
           AND cam_proc = p_codperi;
      
      END LOOP;
    
    END IF;
  
  EXCEPTION
    WHEN OTHERS THEN
      raise_application_error(-20123,
                              'ERROR PED_PR_DESCU_CABEC: ' ||
                              substr(SQLERRM, 1, 250));
    
  END ped_pr_descu_cabec2;

  /**************************************************************************
  Descripcion : Proceso que despacha compuestas fijas de manera completa.
  Fecha Creacion : 16/02/2014
  Fecha Modificacion  : 16/02/2014
  Autor : Jorge Yepez
  ***************************************************************************/

  PROCEDURE ped_pr_despa_compl
  (
    p_codigopais       IN VARCHAR2,
    p_oidperi          IN NUMBER,
    p_fechafacturacion IN VARCHAR2
  ) IS
  
    lntemp NUMBER;
  
  BEGIN
  
    FOR y IN (SELECT *
                FROM (SELECT a.oid_soli_cabe,
                             c.ofer_oid_ofer,
                             c.val_fact_repe,
                             SUM(b.num_unid_por_aten / c.val_fact_repe) a,
                             SUM(b.num_unid_compr / c.val_fact_repe) b
                        FROM ped_solic_cabec     a,
                             ped_solic_posic     b,
                             pre_ofert_detal     c,
                             pre_ofert           d,
                             ped_tipo_solic_pais e,
                             ped_tipo_solic      f
                       WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
                         AND a.tspa_oid_tipo_soli_pais = e.oid_tipo_soli_pais
                         AND e.tsol_oid_tipo_soli = f.oid_tipo_soli
                         AND a.perd_oid_peri = p_oidperi
                         AND a.fec_fact =
                             to_date(p_fechafacturacion, 'dd/mm/yyyy')
                         AND b.ofde_oid_deta_ofer = c.oid_deta_ofer
                         AND c.ofer_oid_ofer = d.oid_ofer
                         AND d.coes_oid_estr IN (2002, 2007)
                         AND f.cod_tipo_soli = 'SOC'
                         AND d.ind_desp_compl = 1
                      --and stpo_oid_subt_posi<>2030
                       GROUP BY a.oid_soli_cabe,
                                c.ofer_oid_ofer,
                                c.val_fact_repe
                       ORDER BY a.oid_soli_cabe,
                                c.ofer_oid_ofer,
                                c.val_fact_repe)
               WHERE a <> b)
    LOOP
    
      SELECT MIN(a.num_unid_compr / b.val_fact_repe)
        INTO lntemp
        FROM ped_solic_posic a,
             pre_ofert_detal b
       WHERE a.soca_oid_soli_cabe = y.oid_soli_cabe
         AND a.ofde_oid_deta_ofer = b.oid_deta_ofer
         AND b.ofer_oid_ofer = y.ofer_oid_ofer;
    
      UPDATE ped_solic_posic x
         SET x.num_unid_compr = lntemp *
                                (SELECT val_fact_repe
                                   FROM pre_ofert_detal
                                  WHERE oid_deta_ofer = x.ofde_oid_deta_ofer)
       WHERE x.oid_soli_posi IN
             (SELECT a.oid_soli_posi
                FROM ped_solic_posic a,
                     pre_ofert_detal b
               WHERE a.soca_oid_soli_cabe = y.oid_soli_cabe
                 AND a.ofde_oid_deta_ofer = b.oid_deta_ofer
                 AND b.ofer_oid_ofer = y.ofer_oid_ofer);
    
    END LOOP;
  
  EXCEPTION
    WHEN OTHERS THEN
      raise_application_error(-20123,
                              'ERROR PED_PR_DESPA_COMPL: ' ||
                              substr(SQLERRM, 1, 250));
    
  END ped_pr_despa_compl;

  PROCEDURE ped_pr_actua_direc_solic1 IS
  
    CURSOR c_pedidos(p_oidperiodo NUMBER) IS
      SELECT oid_soli_cabe,
             clie_oid_clie,
             oid_clie_dire,
             oid_terr_admi,
             zzon_oid_zona,
             terr_oid_terr,
             vepo_oid_valo_estr_geop
        FROM (SELECT sol.clie_oid_clie,
                     sol.oid_soli_cabe,
                     mcd.oid_clie_dire,
                     sol.cldi_oid_clie_dire_soli,
                     mcd.terr_oid_terr,
                     sol.terr_oid_terr_soli,
                     zta.oid_terr_admi,
                     sol.ztad_oid_terr_admi_soli,
                     sec.zzon_oid_zona,
                     sol.zzon_oid_zona_soli,
                     (SELECT veg.oid_valo_estr_geop
                        FROM zon_valor_estru_geopo veg
                       WHERE veg.orde_1 = substr(mcd.cod_unid_geog, 0, 6)
                         AND veg.orde_2 = substr(mcd.cod_unid_geog, 7, 6)
                         AND veg.orde_3 = substr(mcd.cod_unid_geog, 13, 6)
                         AND nvl(veg.orde_4, 'X') =
                             nvl(substr(mcd.cod_unid_geog, 19, 6), 'X')
                         AND veg.ind_acti = 1
                         AND veg.ind_borr = 0) vepo_oid_valo_estr_geop,
                     sol.vepo_oid_valo_estr_geop_soli
                FROM mae_clien_direc mcd,
                     zon_secci sec,
                     zon_terri_admin zta,
                     (SELECT psc.clie_oid_clie,
                             psc.oid_soli_cabe,
                             psc.cldi_oid_clie_dire cldi_oid_clie_dire_soli,
                             psc.ztad_oid_terr_admi ztad_oid_terr_admi_soli,
                             psc.zzon_oid_zona zzon_oid_zona_soli,
                             psc.terr_oid_terr terr_oid_terr_soli,
                             psc.vepo_oid_valo_estr_geop vepo_oid_valo_estr_geop_soli,
                             (SELECT x.oid_clie_dire
                                FROM mae_clien_direc x
                               WHERE x.clie_oid_clie = psc.clie_oid_clie
                                 AND x.ind_elim = 0
                                 AND x.ind_dire_ppal = 1
                                 AND rownum = 1) oid_clie_dire_actu
                        FROM ped_solic_cabec     psc,
                             ped_tipo_solic_pais ptsp,
                             ped_tipo_solic      pts
                       WHERE psc.tspa_oid_tipo_soli_pais =
                             ptsp.oid_tipo_soli_pais
                         AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                         AND psc.perd_oid_peri = p_oidperiodo
                         AND nvl(pts.ind_soli_nega, 0) = 0 -- solicitudes positivas
                         AND psc.grpr_oid_grup_proc = 3 -- solicitudes en GP3
                      ) sol
               WHERE zta.terr_oid_terr = mcd.terr_oid_terr
                 AND zta.zscc_oid_secc = sec.oid_secc
                 AND zta.ind_borr = 0
                 AND zta.perd_oid_peri_fina IS NULL
                 AND sec.ind_borr = 0
                 AND sec.ind_acti = 1
                 AND sol.clie_oid_clie = mcd.clie_oid_clie
                 AND sol.oid_clie_dire_actu = mcd.oid_clie_dire)
       WHERE oid_clie_dire != cldi_oid_clie_dire_soli
          OR terr_oid_terr != terr_oid_terr_soli
          OR oid_terr_admi != ztad_oid_terr_admi_soli
          OR zzon_oid_zona != zzon_oid_zona_soli
          OR vepo_oid_valo_estr_geop != vepo_oid_valo_estr_geop_soli
       ORDER BY clie_oid_clie;
  
    TYPE t_oid_soli_cabe IS TABLE OF ped_solic_cabec.oid_soli_cabe%TYPE;
    TYPE t_clie_oid_clie IS TABLE OF ped_solic_cabec.clie_oid_clie%TYPE;
    TYPE t_oid_clie_dire IS TABLE OF ped_solic_cabec.cldi_oid_clie_dire%TYPE;
    TYPE t_oid_terr_admi IS TABLE OF ped_solic_cabec.ztad_oid_terr_admi%TYPE;
    TYPE t_zzon_oid_zona IS TABLE OF ped_solic_cabec.zzon_oid_zona%TYPE;
    TYPE t_terr_oid_terr IS TABLE OF ped_solic_cabec.terr_oid_terr%TYPE;
    TYPE t_vepo_oid_valo_estr_geop IS TABLE OF ped_solic_cabec.vepo_oid_valo_estr_geop%TYPE;
  
    v_oid_soli_cabe           t_oid_soli_cabe;
    v_clie_oid_clie           t_clie_oid_clie;
    v_oid_clie_dire           t_oid_clie_dire;
    v_oid_terr_admi           t_oid_terr_admi;
    v_zzon_oid_zona           t_zzon_oid_zona;
    v_terr_oid_terr           t_terr_oid_terr;
    v_vepo_oid_valo_estr_geop t_vepo_oid_valo_estr_geop;
  
    rows NATURAL := 500; -- Numero de filas a procesar a la vez
    i    BINARY_INTEGER := 0;
  
    l_codigoperiodo VARCHAR2(6);
    l_oidperiodo    NUMBER;
  
  BEGIN
  
    -- Obtenemos el valor del periodo vigente
    SELECT cod_peri
      INTO l_codigoperiodo
      FROM bas_ctrl_fact
     WHERE sta_camp = 0
       AND ind_camp_act = 1;
  
    l_oidperiodo := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(l_codigoperiodo);
  
    OPEN c_pedidos(l_oidperiodo);
    LOOP
      FETCH c_pedidos BULK COLLECT
        INTO v_oid_soli_cabe,
             v_clie_oid_clie,
             v_oid_clie_dire,
             v_oid_terr_admi,
             v_zzon_oid_zona,
             v_terr_oid_terr,
             v_vepo_oid_valo_estr_geop LIMIT rows;
      EXIT WHEN v_oid_soli_cabe.count = 0;
    
      FORALL i IN 1 .. v_oid_soli_cabe.count
        UPDATE ped_solic_cabec psc
           SET psc.cldi_oid_clie_dire      = v_oid_clie_dire(i),
               psc.ztad_oid_terr_admi      = v_oid_terr_admi(i),
               psc.zzon_oid_zona           = v_zzon_oid_zona(i),
               psc.terr_oid_terr           = v_terr_oid_terr(i),
               psc.vepo_oid_valo_estr_geop = v_vepo_oid_valo_estr_geop(i)
         WHERE psc.oid_soli_cabe = v_oid_soli_cabe(i);
    
    END LOOP;
    CLOSE c_pedidos;
  
    FOR y IN (SELECT oid_soli_cabe,
                     (SELECT MAX(x.tido_oid_tipo_docu)
                        FROM mae_tipo_docum  x,
                             mae_clien_ident y
                       WHERE y.clie_oid_clie = a.clie_oid_clie
                         AND y.tdoc_oid_tipo_docu = x.oid_tipo_docu
                         AND y.val_iden_docu_prin = 1) ntido
                FROM ped_solic_cabec a
               WHERE perd_oid_peri = l_oidperiodo
                 AND tido_oid_tipo_docu IN (1, 29)
                 AND grpr_oid_grup_proc IN (3, 4)
                 AND EXISTS
               (SELECT 1
                        FROM ped_tipo_solic_pais x1
                       WHERE x1.oid_tipo_soli_pais =
                             a.tspa_oid_tipo_soli_pais
                         AND x1.tido_oid_tipo_docu IS NULL)
                 AND tido_oid_tipo_docu <>
                     (SELECT MAX(x.tido_oid_tipo_docu)
                        FROM mae_tipo_docum  x,
                             mae_clien_ident y
                       WHERE y.clie_oid_clie = a.clie_oid_clie
                         AND y.tdoc_oid_tipo_docu = x.oid_tipo_docu
                         AND y.val_iden_docu_prin = 1))
    LOOP
    
      UPDATE ped_solic_cabec x
         SET x.tido_oid_tipo_docu = y.ntido
       WHERE x.oid_soli_cabe = y.oid_soli_cabe;
    
    END LOOP;
  
  END;

  PROCEDURE ped_pr_gener_oport_ahorr(lscodpais IN VARCHAR2) IS
  
    lsparaactuadirec VARCHAR2(10) := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                                          'STO_ACTUA_DIREC');
  
    lsparageneopor VARCHAR2(10) := sto_pkg_gener.sto_fn_obten_param_ocr(lscodpais,
                                                                        'STO_OPORT_AHORR');
  BEGIN
  
    IF nvl(lsparaactuadirec, '') = 'S' THEN
      ped_pr_actua_direc_solic1;
    END IF;
  
    IF nvl(lsparageneopor, '') = 'S' THEN
      UPDATE ped_solic_posic a
         SET a.val_prec_publ_unit_loca =
             (SELECT nvl(imp_prec_publ, 0)
                FROM pre_ofert_detal
               WHERE oid_deta_ofer = a.ofde_oid_deta_ofer),
             a.val_prec_publ_tota_loca = CASE
                                           WHEN ((SELECT nvl(imp_prec_publ, 0)
                                                    FROM pre_ofert_detal
                                                   WHERE oid_deta_ofer =
                                                         a.ofde_oid_deta_ofer) *
                                                a.num_unid_compr) -
                                                (a.val_prec_cata_unit_loca *
                                                a.num_unid_compr) < 0 THEN
                                            0
                                           ELSE
                                            ((SELECT nvl(imp_prec_publ, 0)
                                                FROM pre_ofert_detal
                                               WHERE oid_deta_ofer =
                                                     a.ofde_oid_deta_ofer) *
                                            a.num_unid_compr) -
                                            (a.val_prec_cata_unit_loca *
                                            a.num_unid_compr)
                                         END
       WHERE a.soca_oid_soli_cabe IN
             (SELECT oid_soli_cabe
                FROM ped_solic_cabec x,
                     cra_perio       y,
                     seg_perio_corpo z,
                     bas_ctrl_fact   z1
               WHERE x.perd_oid_peri = y.oid_peri
                 AND y.peri_oid_peri = z.oid_peri
                 AND z.cod_peri = z1.cod_peri
                 AND z1.sta_camp = 0
                 AND z1.ind_camp_act = 1
                 AND x.fec_prog_fact = z1.fec_proc
                 AND x.grpr_oid_grup_proc = 3
                 AND x.ind_oc = 1)
         AND ofde_oid_deta_ofer IN
             (SELECT oid_deta_ofer
                FROM pre_ofert_detal       x,
                     pre_ofert             y,
                     pre_matri_factu_cabec z,
                     cra_perio             x1,
                     seg_perio_corpo       y1,
                     bas_ctrl_fact         z1,
                     pre_tipo_ofert        zz
               WHERE x.ofer_oid_ofer = y.oid_ofer
                 AND y.mfca_oid_cabe = z.oid_cabe
                 AND z.perd_oid_peri = x1.oid_peri
                 AND x1.peri_oid_peri = y1.oid_peri
                 AND y1.cod_peri = z1.cod_peri
                 AND z1.sta_camp = 0
                 AND z1.ind_camp_act = 1
                 AND x.tofe_oid_tipo_ofer = zz.oid_tipo_ofer
                 AND zz.num_secc_deta_fact = 1);
    
      UPDATE ped_solic_cabec a
         SET a.val_gana_tota_loca
             --=(select sum(nvl(B.VAL_PREC_PUBL_TOTA_LOCA,0)+(nvl(B.Val_Impo_Desc_unit_Loca,0)*b.num_unid_compr))
              =
             (SELECT SUM(decode(b2.num_secc_deta_fact,
                                1,
                                nvl(b.val_prec_publ_tota_loca, 0),
                                0)) + SUM(decode(b2.num_secc_deta_fact,
                                                 0,
                                                 nvl((b.val_impo_desc_unit_loca *
                                                     b.num_unid_compr),
                                                     0),
                                                 0))
                FROM ped_solic_posic b,
                     pre_ofert_detal b1,
                     pre_tipo_ofert  b2
               WHERE b.soca_oid_soli_cabe = a.oid_soli_cabe
                 AND b.ofde_oid_deta_ofer = b1.oid_deta_ofer
                 AND b1.tofe_oid_tipo_ofer = b2.oid_tipo_ofer)
       WHERE a.oid_soli_cabe IN (SELECT oid_soli_cabe
                                   FROM ped_solic_cabec x,
                                        cra_perio       y,
                                        seg_perio_corpo z,
                                        bas_ctrl_fact   z1
                                  WHERE x.perd_oid_peri = y.oid_peri
                                    AND y.peri_oid_peri = z.oid_peri
                                    AND z.cod_peri = z1.cod_peri
                                    AND z1.sta_camp = 0
                                    AND z1.ind_camp_act = 1
                                    AND x.fec_prog_fact = z1.fec_proc
                                    AND x.grpr_oid_grup_proc = 3
                                    AND x.ind_oc = 1);
    
    ELSIF nvl(lsparageneopor, '') = 'C' THEN
    
      UPDATE ped_solic_cabec a
         SET a.val_gana_tota_loca
             --=(select sum(nvl(B.VAL_PREC_PUBL_TOTA_LOCA,0)+(nvl(B.Val_Impo_Desc_unit_Loca,0)*b.num_unid_compr))
              =
             (SELECT SUM(CASE
                           WHEN b.val_prec_cata_unit_loca = 0 THEN
                            b.val_prec_cata_tota_loca
                           ELSE
                            b.num_unid_compr *
                            nvl(round(nvl(b.val_prec_cata_unit_loca, 0) *
                                      nvl(b.val_porc_desc, 0) / 100,
                                      0),
                                0)
                         END)
                FROM ped_solic_posic b,
                     pre_ofert_detal b1,
                     pre_tipo_ofert  b2
               WHERE b.soca_oid_soli_cabe = a.oid_soli_cabe
                 AND b.ofde_oid_deta_ofer = b1.oid_deta_ofer
                 AND b1.tofe_oid_tipo_ofer = b2.oid_tipo_ofer)
       WHERE a.oid_soli_cabe IN (SELECT oid_soli_cabe
                                   FROM ped_solic_cabec x,
                                        cra_perio       y,
                                        seg_perio_corpo z,
                                        bas_ctrl_fact   z1
                                  WHERE x.perd_oid_peri = y.oid_peri
                                    AND y.peri_oid_peri = z.oid_peri
                                    AND z.cod_peri = z1.cod_peri
                                    AND z1.sta_camp = 0
                                    AND z1.ind_camp_act = 1
                                    AND x.fec_prog_fact = z1.fec_proc
                                    AND x.grpr_oid_grup_proc = 3
                                    AND x.ind_oc = 1);
    
    END IF;
  
  END;

  /***************************************************************************
  Descripcion       : Elimina Productos Faltantes PROL Individuales
  Fecha Creacion    : 11/08/2014
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_elim_falt_prol_indi
  (
    p_oidsoli    NUMBER,
    pscodigopais VARCHAR
  )
  --RETURN NUMBER
   IS
  
  BEGIN
    UPDATE ped_solic_posic
       SET --espo_oid_esta_posi=2,
           num_unid_por_aten = 0,
           num_unid_dema_real = 0,
           num_unid_compr     = 0
     WHERE oid_soli_posi IN
           (SELECT a.oid_soli_posi
              FROM ped_solic_posic       a,
                   ped_solic_cabec       b,
                   pre_ofert_detal       c,
                   pre_ofert             d,
                   int_solic_conso_cabec e,
                   int_solic_conso_detal f
             WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
               AND b.oid_soli_cabe = p_oidsoli
               AND a.ofde_oid_deta_ofer = c.oid_deta_ofer
               AND c.ofer_oid_ofer = d.oid_ofer
               AND b.oid_soli_cabe = e.soca_oid_soli_cabe_refe
               AND e.sec_nume_docu = f.sec_nume_docu_cabe
               AND d.coes_oid_estr IN (2001, 2002)
               AND a.val_codi_vent = f.cod_vent
               AND f.ind_recu_prol = '1');
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN;
  END ped_pr_elim_falt_prol_indi;

  /***************************************************************************
  Descripcion       : Elimina Productos Faltantes PROL Compuesta Fija
  Fecha Creacion    : 11/08/2014
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_elim_falt_prol_cfija
  (
    p_oidsoli    NUMBER,
    pscodigopais VARCHAR
  )
  --RETURN NUMBER
   IS
  
  BEGIN
    UPDATE ped_solic_posic
       SET --espo_oid_esta_posi=2,
           num_unid_por_aten = 0,
           num_unid_dema_real = 0,
           num_unid_compr     = 0
     WHERE oid_soli_posi IN
           (SELECT a.oid_soli_posi
              FROM ped_solic_posic       a,
                   ped_solic_cabec       b,
                   pre_ofert_detal       c,
                   pre_ofert             d,
                   int_solic_conso_cabec e,
                   int_solic_conso_detal f
             WHERE a.soca_oid_soli_cabe = b.oid_soli_cabe
               AND b.oid_soli_cabe = p_oidsoli
               AND a.ofde_oid_deta_ofer = c.oid_deta_ofer
               AND c.ofer_oid_ofer = d.oid_ofer
               AND b.oid_soli_cabe = e.soca_oid_soli_cabe_refe
               AND e.sec_nume_docu = f.sec_nume_docu_cabe
               AND d.coes_oid_estr IN (2002)
               AND a.val_codi_vent = f.cod_vent
               AND f.ind_recu_prol = '1'
               AND a.num_unid_por_aten > 0);
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN;
  END ped_pr_elim_falt_prol_cfija;

  /***************************************************************************
  Descripcion       : Carga matriz de Facturación por lote desde Planit
  Fecha Creacion    : 17/07/2015
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_carg_matr_plan
  (
    p_numlote    VARCHAR,
    pscodigopais VARCHAR
  )
  --RETURN NUMBER
   IS
  
    CURSOR c_ofer IS
      SELECT *
        FROM pre_ofert_plani x
        WHERE x.num_lote = p_numlote
       ORDER BY x.cod_estr,
                x.num_ofer,
                x.num_grup;
  
    r_ofer c_ofer%ROWTYPE;


    CURSOR c_cond(p_codestr VARCHAR, p_numofer NUMBER) IS
      SELECT *
        FROM pre_condi_plani x
        WHERE x.num_lote = p_numlote
        and x.cod_estr=p_codestr
        and x.num_ofer=p_numofer
       ORDER BY x.num_cond;
  
    r_cond c_cond%ROWTYPE;


    CURSOR c_ofer_nive IS
      SELECT *
        FROM pre_ofer_nive_plani x
        WHERE x.num_lote = p_numlote
       ORDER BY x.tip_nive, x.num_nive;
  
    r_ofer_nive c_ofer_nive%ROWTYPE;


    CURSOR c_rang_ofer_nive(p_numnivel VARCHAR) IS
      SELECT *
        FROM PRE_RANG_OFER_NIVE_PLANI x
        WHERE x.num_lote = p_numlote 
        and x.num_nive=p_numnivel
       ORDER BY x.num_rang;
  
    r_rang_ofer_nive c_rang_ofer_nive%ROWTYPE;

    CURSOR c_grat_ofer_nive(p_numnivel VARCHAR) IS
      SELECT *
        FROM PRE_GRATI_OFER_NIVE_PLANI x
        WHERE x.num_lote = p_numlote 
        and x.num_nive=p_numnivel
       ORDER BY x.num_rang;
  
    r_grat_ofer_nive c_grat_ofer_nive%ROWTYPE;

    CURSOR c_prod_ofer_nive(p_numnivel VARCHAR) IS
      SELECT *
        FROM PRE_PROD_OFER_NIVE_PLANI x
        WHERE x.num_lote = p_numlote 
        and x.num_nive=p_numnivel
       ;
  
    r_prod_ofer_nive c_prod_ofer_nive%ROWTYPE;


    ln_oferant NUMBER(10);
    ln_grupant NUMBER(10);
    ln_oidofer NUMBER(10);
    ln_oiddetaofer NUMBER(10);
    ln_oidgrupofer NUMBER(10);
    ln_oidcabematr NUMBER(10);
    ln_oidniveofer NUMBER(10);
    ln_oidniverang NUMBER(10);  
    ln_oidprom NUMBER(10);
    ls_llaveant    VARCHAR(50):=' ';
    ls_llavegrupant    VARCHAR(50);
    ls_tiponivel    VARCHAR(1);
     
  BEGIN


    OPEN c_ofer;    
    LOOP
      FETCH c_ofer
        INTO r_ofer;
      EXIT WHEN c_ofer%NOTFOUND;
      
         if ln_oidcabematr is null then
            begin
            select x.oid_cabe into ln_oidcabematr
            from pre_matri_factu_cabec x, cra_perio y, seg_perio_corpo z
            where x.perd_oid_peri=y.oid_peri
            and y.peri_oid_peri=z.oid_peri
            and z.cod_peri=r_ofer.cod_camp;
            
            exception when others then
               select pre_mfca_seq.nextval into ln_oidcabematr from dual;   
               
               Insert into PRE_MATRI_FACTU_CABEC
               (OID_CABE, 
               PERD_OID_PERI, 
               NUM_PEDI_ESTI, 
               NUM_UNID_ESTI, 
               NUM_CLIE_ESTI, 
               TOTA_MONT_VENT_NETA, 
               VAL_TIPO_CAMB, 
               IND_MATR_FACT_GENE, 
               IND_REGI_ESTA_GENE, 
               IND_MATR_FACT, 
               MONE_OID_MONE               
                )
               Values
               (ln_oidcabematr, 
               (select x.oid_peri from cra_perio x, seg_perio_corpo y where x.peri_oid_peri=y.oid_peri and y.cod_peri=r_ofer.cod_camp), 
               1, 
               1, 
               1, 
               1, 
               1, 
               0, 
               0, 
               0, 
               NULL 
               );
    
            end;
         end if;
    
         
         if ls_llaveant<>r_ofer.cod_estr || r_ofer.num_ofer then
            select pre_ofer_seq.nextval into ln_oidofer from dual;
            
            Insert into PRE_OFERT
            (OID_OFER, 
            COES_OID_ESTR, 
            NUM_OFER, 
            NUM_ORDE, 
            NUM_GRUP, 
            NUM_GRUP_CNDT, 
            NUM_GRUP_COND, 
            VAL_COND_G1_CNDT, 
            VAL_COND_G2_CNDO, 
            NUM_PAQU, 
            NUM_PRIM_POSI_RANK, 
            NUM_ULTI_POSI_RANK,  
            FOPA_OID_FORM_PAGO, 
            SBAC_OID_SBAC, 
            ARGV_OID_ARGU_VENT, 
            ACCE_OID_ACCE, 
            MFCA_OID_CABE, 
            IND_CODI_VENT_GENE, 
            IND_DESP_COMPL, 
            IND_DESP_AUTO, 
            IND_MATR_FACT_GENE, 
            IND_RECU_OBLI, 
            IND_REGI_ESTA_GENE, 
            OCAT_OID_CATA,
            NUM_LOTE_PLAN
            )
            Values
            (ln_oidofer, 
            (select oid_estr from pre_estra where cod_estr=r_ofer.cod_estr), 
            r_ofer.num_ofer, 
            NULL, 
            r_ofer.num_grup_tota, 
            NULL, 
            NULL, 
            NULL, 
            NULL, 
            NULL, 
            0, 
            0, 
            NULL, 
            NULL, 
          (select oid_estr from pre_estra where cod_estr=r_ofer.cod_estr), 
            NULL, 
            ln_oidcabematr, 
            1, 
            0, 
            r_ofer.ind_desp_auto, 
            1, 
            0, 
            NULL, 
            (select oid_cata from pre_catal where cod_cata=r_ofer.cod_cata),
            p_numlote
            );
          
          ln_oidprom:=null;  

          OPEN c_cond(r_ofer.cod_estr, r_ofer.num_ofer);
          LOOP
            FETCH c_cond
              INTO r_cond;
            EXIT WHEN c_cond%NOTFOUND;

            if ln_oidprom is null then
               select pre_pomo_seq.nextval into ln_oidprom from dual; 
               
               Insert into PRE_PROMO
               (OID_PROM, 
               OFER_OID_OFER, 
               NUM_COND, 
               VAL_FACT_CUAD, 
               ICPR_OID_INDI_CUAD_PROM)
               Values
               (ln_oidprom, 
               ln_oidofer, 
               r_cond.num_cond, 
               --case when r_cond.cod_cond='01' then '1'
               --when r_cond.cod_cond='02' then '2' end,                 
               --case when r_cond.cod_cond='01' then r_cond.uni_cond
               --when r_cond.cod_cond='02' then r_cond.val_cond end                 
               case when r_cond.cod_cond='1' then r_cond.uni_cond
               when r_cond.cod_cond='2' then (r_cond.val_cond/1000) end, 
               r_cond.cod_cond 
               );
                         
            end if;

            Insert into PRE_RANGO_PROMO
               (OID_RANG_PROM, 
                OCAT_OID_CATA, 
                POMO_OID_PROM, 
                COD_TIPO_RANG, 
                NUM_RANG_INTE, 
                VAL_DESD, 
                VAL_HAST, 
                IND_EXCL)
             Values
             (
               pre_rapr_seq.nextval, 
               --r_cond.cod_cata, 
               (SELECT OID_CATA FROM PRE_CATAL WHERE r_cond.cod_cata = PRE_CATAL.COD_CATA),
               ln_oidprom, 
               r_cond.tip_rang_cond, 
               r_cond.num_rang_cond, 
                case when r_cond.tip_rang_cond='R' then r_cond.num_pagi_desd
                when r_cond.tip_rang_cond='P' then (select oid_prod from mae_produ where cod_sap=r_cond.cod_sap) end, 
                case when r_cond.tip_rang_cond='R' then r_cond.num_pagi_hast
                when r_cond.tip_rang_cond='P' then NULL end, 
                r_cond.ind_excl
                );

            
            
          END LOOP;
          CLOSE c_cond;


            
         end if;
         
         --ln_oidgrupofer := null         
         if r_ofer.num_grup is null then 
            ln_oidgrupofer := null ;
         end if;
         if r_ofer.num_grup is not null and ls_llavegrupant<>r_ofer.cod_estr || r_ofer.num_ofer  || r_ofer.num_grup then
            select pre_gofe_seq.nextval into ln_oidgrupofer from dual;
            
            Insert into PRE_GRUPO_OFERT
            (OID_GRUP_OFER, 
            OFER_OID_OFER, 
            NUM_GRUP, 
            COD_FACT_CUAD, 
            CUES_OID_IND_CUAD_TIPO_ESTR, 
            IND_CNDT, 
            IND_CNDO, 
            IND_GRUP)
            Values
            (ln_oidgrupofer, 
            ln_oidofer, 
            r_ofer.num_grup, 
            r_ofer.cod_fact_cuad, 
            case when r_ofer.num_grup_tota=1 and r_ofer.cod_estr='3' then 3
            when r_ofer.num_grup_tota>1 and r_ofer.cod_estr='3' then 1
            when r_ofer.num_grup_tota=1 and r_ofer.cod_estr='7' then 8
            when r_ofer.num_grup_tota>1 and r_ofer.cod_estr='7' then 6 end, 
            0, 
            0, 
            1
            );
            
         end if;
         
         select pre_ofde_seq.nextval into ln_oiddetaofer from dual;
         
         Insert into PRE_OFERT_DETAL
         (OID_DETA_OFER, 
         OFER_OID_OFER, 
         PROD_OID_PROD, 
         NUM_LINE_OFER, 
         VAL_TEXT_BREV, 
         NUM_UNID_ESTI, 
         COD_ORIG, 
         VAL_FACT_REPE, 
         NUM_POSI_RANK, 
         IND_PROD_PRIN, 
         IMP_PREC_CATA, 
         IMP_PREC_POSI, 
         IMP_COST_ESTA, 
         IMP_VENT_NETA_ESTI, 
         NUM_PAGI_CATA, 
         OCAT_OID_CATAL, 
         TOFE_OID_TIPO_OFER, 
         CIVI_OID_CICLO_VIDA, 
         CNDP_OID_COND_PROM, 
         FOPA_OID_FORM_PAGO, 
         GOFE_OID_GRUP_OFER, 
         IND_DIGI, 
         IND_IMPR_GP, 
         IND_CODI_VENT_GENE, 
         IND_MATR_FACT_GENE, 
         VAL_POSI_PAGI, 
         VAL_CODI_VENT, 
         VAL_CENT, 
         FEC_ULTI_ACTU, 
         PRECIO_UNITARIO, 
         NUM_PUNT_FIJO, 
         VARI_OID_VARI, 
         PRFI_OID_PROG_FIDE, 
         NUM_ORDE_DETA, 
         IMP_PREC_PUBL, 
         FEC_DIGI)
         Values
       (ln_oiddetaofer, 
       ln_oidofer, 
       (select oid_PROD from mae_produ where cod_sap=r_ofer.cod_sap), 
       (select nvl(max(num_line_ofer),0) from pre_ofert_detal where ofer_oid_ofer=ln_oidofer)+1, 
       NULL, 
       r_ofer.num_unid_esti, 
       'PL', 
       r_ofer.fact_repe, 
       0, 
       r_ofer.ind_prin, 
       r_ofer.val_prec_cata/1000, 
       r_ofer.val_prec_cont/1000, 
       r_ofer.val_cost_repo/1000, 
       0, 
       r_ofer.num_pagi, 
       (select oid_cata from pre_catal where cod_cata=r_ofer.cod_cata), 
       (select oid_tipo_ofer from pre_tipo_ofert where cod_tipo_ofer=r_ofer.cod_tipo_ofer and cana_oid_cana=2001), 
       4, 
       2005, 
       NULL, 
       ln_oidgrupofer, 
       r_ofer.ind_digi, 
       r_ofer.ind_impr, 
       1, 
       1, 
       1, 
       r_ofer.val_codi_vent, 
       r_ofer.cod_cent, 
       sysdate, 
       r_ofer.val_prec_unit/1000, 
       NULL, 
       NULL, 
       NULL, 
       NULL, 
       r_ofer.val_prec_unit/1000, 
       sysdate);

       

       Insert into PRE_MATRI_FACTU
       (OID_MATR_FACT, 
       COD_ESTA, 
       MFCA_OID_CABE, 
       OFDE_OID_DETA_OFER, 
       FEC_ULTI_ACTU, 
       NUM_PUNT_FIJO, 
       VARI_OID_VARI, 
       PRFI_OID_PROG_FIDE, 
       IND_MATR_FACT)
       Values
       (pre_mafa_seq.nextval, 
       '1', 
       ln_oidcabematr, 
       ln_oiddetaofer, 
       SYSDATE, 
       NULL, 
       NULL, 
       NULL, 
       NULL);
       

      
       ls_llaveant:=r_ofer.cod_estr || r_ofer.num_ofer;
       ls_llavegrupant:=r_ofer.cod_estr || r_ofer.num_ofer  || r_ofer.num_grup;       
             
    END LOOP;
    -- Actualiza Lote Planit
    --UPDATE PRE_OFERT_PLANI SET IND_PROC = '1' 
    --WHERE NUM_LOTE = p_numlote ;   
    --
    CLOSE c_ofer;


      OPEN c_ofer_nive;
      LOOP
        FETCH c_ofer_nive
          INTO r_ofer_nive;
        EXIT WHEN c_ofer_nive%NOTFOUND;
        
        
        select case when exists (select 1 from PRE_RANG_OFER_NIVE_PLANI where num_nive=r_ofer_nive.num_nive and fact_repe is null) then '1'
        else '2' end into ls_tiponivel
        from dual;
        
        if ls_tiponivel='1' then

        select PRE_NIOF_SEQ.Nextval into ln_oidniveofer
        from dual;
                
        
                     Insert into PRE_NIVEL_OFERT
                     (OID_NIVE_OFER, 
                      OCAT_OID_CATA, 
                      PERD_OID_PERI, 
                      NUM_PAGI, 
                      TIP_NIVE, 
                      TIP_CUAD, 
                      COD_USUA, 
                      FEC_DIGI,
                      NUM_LOTE_PLAN
                       )
                      Values
                      (ln_oidniveofer, 
                     (select oid_cata from pre_catal where cod_cata=r_ofer_nive.cod_cata), 
                     (select x.oid_peri from cra_perio x, seg_perio_corpo y where x.peri_oid_peri=y.oid_peri and y.cod_peri=r_ofer_nive.cod_camp), 
                     r_ofer_nive.num_pagi, 
                     r_ofer_nive.tip_nive, 
                      r_ofer_nive.tip_cuad, 
                      'PLANIT', 
                      SYSDATE,
                      p_numlote
                       );
              
                            OPEN c_rang_ofer_nive(r_ofer_nive.num_nive);
                            LOOP
                              FETCH c_rang_ofer_nive
                                INTO r_rang_ofer_nive;
                              EXIT WHEN c_rang_ofer_nive%NOTFOUND;
                             
                              --MCH 
                              select PRE_NIOF_RANG_SEQ.NEXTVAL into ln_oidniverang  
                              from dual;
              
                            Insert into PRE_NIVEL_OFERT_RANGO
                               (OID_NIVE_OFER_RANG, 
                                NIOF_OID_NIVE_OFER, 
                                VAL_RANG_INFE, 
                                VAL_RANG_SUPE, 
                                VAL_PREC_UNIT, 
                                COD_USUA, 
                                FEC_DIGI)
                             Values
                               --PRE_NIOF_RANG_SEQ.NEXTVAL
                               (ln_oidniverang , 
                               ln_oidniveofer, 
                               r_rang_ofer_nive.val_rang_infe/1000, 
                               r_rang_ofer_nive.val_rang_supe/1000, 
                               r_rang_ofer_nive.val_prec_unit/1000, 
                                'PLANIT', 
                                SYSDATE
                                );
              
                      
                            END LOOP;
                            CLOSE c_rang_ofer_nive;


                            OPEN c_grat_ofer_nive(r_ofer_nive.num_nive);
                            LOOP
                              FETCH c_grat_ofer_nive
                                INTO r_grat_ofer_nive;
                              EXIT WHEN c_grat_ofer_nive%NOTFOUND;
              
                            Insert into PRE_NIVEL_OFERT_GRATI
                               (OID_NIVE_OFER_GRAT, 
                                NIOF_OID_NIVE_OFER_RANG, 
                                OID_DETA_OFER, 
                                VAL_UNID, 
                                NUM_SECU, 
                                COD_USUA, 
                                FEC_DIGI)
                             Values
                               -- ln_oidniveofer
                               (pre_niof_grat_seq.nextval, 
                               ln_oidniverang, 
                               (select oid_deta_ofer from pre_ofert_detal a, pre_ofert b where a.ofer_oid_ofer=b.oid_ofer and a.val_codi_vent=r_grat_ofer_nive.val_codi_vent and b.mfca_oid_cabe=ln_oidcabematr), 
                               r_grat_ofer_nive.num_cant, 
                               r_grat_ofer_nive.num_rang, 
                               'PLANIT', 
                               SYSDATE
                               );

              
                      
                            END LOOP;
                            CLOSE c_grat_ofer_nive;              


                            OPEN c_prod_ofer_nive(r_ofer_nive.num_nive);
                            LOOP
                              FETCH c_prod_ofer_nive
                                INTO r_prod_ofer_nive;
                              EXIT WHEN c_prod_ofer_nive%NOTFOUND;
              
                            Insert into PRE_NIVEL_OFERT_PRODU
                               (OID_NIVE_OFER_PROD, 
                                NIOF_OID_NIVE_OFER, 
                                OID_DETA_OFER, 
                                COD_USUA, 
                                FEC_DIGI)
                             Values
                               (pre_niop_seq.nextval, 
                               ln_oidniveofer, 
                               (select oid_deta_ofer from pre_ofert_detal a, pre_ofert b where a.ofer_oid_ofer=b.oid_ofer and a.val_codi_vent=r_prod_ofer_nive.val_codi_vent and b.mfca_oid_cabe=ln_oidcabematr), 
                               'PLANIT', 
                               SYSDATE
                               );

              
                      
                            END LOOP;
                            CLOSE c_prod_ofer_nive;              

else


        select pre_nxof_seq.nextval into ln_oidniveofer
        from dual; 
        

                     Insert into PRE_NX_OFERT
                     (OID_NX_OFER, 
                      OCAT_OID_CATA, 
                      PERD_OID_PERI, 
                      NUM_PAGI, 
                      TIP_NIVE, 
                      TIP_CUAD, 
                      COD_USUA, 
                      FEC_DIGI,
                      NUM_LOTE_PLAN )
                      Values
                      (ln_oidniveofer, 
                     (select oid_cata from pre_catal where cod_cata=r_ofer_nive.cod_cata), 
                     (select x.oid_peri from cra_perio x, seg_perio_corpo y where x.peri_oid_peri=y.oid_peri and y.cod_peri=r_ofer_nive.cod_camp), 
                     r_ofer_nive.num_pagi, 
                     r_ofer_nive.tip_nive, 
                      r_ofer_nive.tip_cuad, 
                      'PLANIT', 
                      SYSDATE,
                      p_numlote );
              
                            OPEN c_rang_ofer_nive(r_ofer_nive.num_nive);
                            LOOP
                              FETCH c_rang_ofer_nive
                                INTO r_rang_ofer_nive;
                              EXIT WHEN c_rang_ofer_nive%NOTFOUND;
                              
                              -- MCH
                              select pre_nxof_rang_seq.nextval into ln_oidniverang
                              from dual;
              
                            Insert into PRE_NX_OFERT_RANGO
                               (OID_NX_OFER_RANG, 
                                NIOF_OID_NX_OFER, 
                                VAL_FACT_REPE, 
                                VAL_PREC_UNIT, 
                                COD_USUA, 
                                FEC_DIGI)
                             Values
                               --pre_nxof_rang_seq.nextval  
                               (ln_oidniverang ,   
                               ln_oidniveofer, 
                               r_rang_ofer_nive.fact_repe, 
                               r_rang_ofer_nive.val_prec_unit/1000, 
                                'PLANIT', 
                                SYSDATE
                                );
              
                      
                            END LOOP;
                            CLOSE c_rang_ofer_nive;


                            OPEN c_grat_ofer_nive(r_ofer_nive.num_nive);
                            LOOP
                              FETCH c_grat_ofer_nive
                                INTO r_grat_ofer_nive;
                              EXIT WHEN c_grat_ofer_nive%NOTFOUND;
              
                            Insert into PRE_NX_OFERT_GRATI
                               (OID_NX_OFER_GRAT, 
                                NIOF_OID_NX_OFER_RANG, 
                                OID_DETA_OFER, 
                                VAL_UNID, 
                                NUM_SECU, 
                                COD_USUA, 
                                FEC_DIGI)
                             Values
                               --ln_oidniveofer
                               (pre_nxof_grat_seq.nextval, 
                                ln_oidniverang , 
                               (select oid_deta_ofer from pre_ofert_detal a, pre_ofert b where a.ofer_oid_ofer=b.oid_ofer and a.val_codi_vent=r_grat_ofer_nive.val_codi_vent and b.mfca_oid_cabe=ln_oidcabematr), 
                               r_grat_ofer_nive.num_cant, 
                               r_grat_ofer_nive.num_rang, 
                               'PLANIT', 
                               SYSDATE
                               );

              
                      
                            END LOOP;
                            CLOSE c_grat_ofer_nive;              


                            OPEN c_prod_ofer_nive(r_ofer_nive.num_nive);
                            LOOP
                              FETCH c_prod_ofer_nive
                                INTO r_prod_ofer_nive;
                              EXIT WHEN c_prod_ofer_nive%NOTFOUND;
              
                            Insert into PRE_NX_OFERT_PRODU
                               (OID_NX_OFER_PROD, 
                                NIOF_OID_NX_OFER, 
                                OID_DETA_OFER, 
                                COD_USUA, 
                                FEC_DIGI)
                             Values
                               (pre_nxop_seq.nextval, 
                               ln_oidniveofer, 
                               (select oid_deta_ofer from pre_ofert_detal a, pre_ofert b where a.ofer_oid_ofer=b.oid_ofer and a.val_codi_vent=r_prod_ofer_nive.val_codi_vent and b.mfca_oid_cabe=ln_oidcabematr), 
                               'PLANIT', 
                               SYSDATE
                               );

              
                      
                            END LOOP;
                            CLOSE c_prod_ofer_nive;          



          end if;

              
                    END LOOP;
                    -- Actualiza Lote Planit
                     UPDATE PRE_OFER_NIVE_PLANI SET IND_PROC = '1' 
                     WHERE NUM_LOTE = p_numlote ;   
                    --
                    CLOSE c_ofer_nive;
        
        
  
  EXCEPTION
    WHEN OTHERS THEN
      --RETURN;
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR ped_pkg_carg_matr_plan : '||ls_sqlerrm);
  END ped_pr_carg_matr_plan;


  /***************************************************************************
  Descripcion       : Procesa limite de venta a pedido
  Fecha Creacion    : 20/08/2015
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_limit_venta
  (
    p_oidsoli    NUMBER
  )
  --RETURN NUMBER
   IS
  
    CURSOR c_ofer IS
    select a.oid_soli_cabe, b.oid_soli_posi, b.num_unid_por_aten, ped_fn_limit_venta(a.clie_oid_clie,
                                                a.ztad_oid_terr_admi,
                                                b.ofde_oid_deta_ofer,
                                                b.num_unid_por_aten) lim
    from ped_solic_cabec a, ped_solic_posic b
     WHERE a.oid_soli_cabe = p_oidsoli
       AND a.oid_soli_cabe = b.soca_oid_soli_cabe
       AND b.ind_limi_vent IS NULL;
  
    r_ofer c_ofer%ROWTYPE;


    ln_oferant      NUMBER(10);
    ls_tiponivel    VARCHAR(1);
     
  BEGIN


    OPEN c_ofer;
    LOOP
      FETCH c_ofer
        INTO r_ofer;
      EXIT WHEN c_ofer%NOTFOUND;

      if r_ofer.lim<r_ofer.num_unid_por_aten then
            
       update ped_solic_posic a
       SET a.num_unid_por_aten = r_ofer.lim,
           a.num_unid_dema_real = r_ofer.lim,
           a.num_unid_compr = r_ofer.lim,
           a.ind_limi_vent = 1,
           a.val_obse = 'LIMITE DE VENTA'
       where oid_soli_posi=r_ofer.oid_soli_posi;

      end if;

      END LOOP;
      CLOSE c_ofer;

  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN;
  END ped_pr_limit_venta;

  /***************************************************************************
  Descripcion       : Reemplazos en un pedido
  Fecha Creacion    : 18/08/2015
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_reemp_pedid
  (
    p_oidsoli    NUMBER,
    p_tiporee    NUMBER
  )


  
   IS
  

  l_desReemp VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'desReemp');

    ln_oferant      NUMBER(10);
    ls_tiponivel    VARCHAR(1);
     
  BEGIN


                    INSERT INTO ped_solic_posic
                      (oid_soli_posi,
                       cod_posi,
                       num_unid_dema,
                       num_unid_por_aten,
                       val_tasa_impu,
                       soca_oid_soli_cabe,
                       taim_oid_tasa_impu,
                       tpos_oid_tipo_posi,
                       prod_oid_prod,
                       fopa_oid_form_pago,
                       val_prec_cata_unit_loca,
                       val_prec_cont_unit_loca,
                       val_prec_cata_unit_docu,
                       val_prec_conta_unit_docu,
                       val_prec_fact_unit_loca,
                       val_prec_fact_unit_docu,
                       val_prec_sin_impu_unit_loca,
                       val_prec_sin_impu_unit_docu,
                       val_prec_sin_impu_tota_docu,
                       val_impo_desc_unit_loca,
                       val_impo_desc_unit_docu,
                       val_prec_neto_unit_loca,
                       val_prec_neto_tota_docu,
                       val_prec_neto_unit_docu,
                       val_prec_tota_tota_loca,
                       val_prec_tota_tota_docu,
                       val_impo_impu_unit_loca,
                       val_impo_impu_unit_docu,
                       val_impo_desc_tota_docu,
                       val_impo_impu_tota_loca,
                       val_impo_impu_tota_docu,
                       val_impo_desc_tota_loca,
                       val_prec_tota_unit_loca,
                       val_prec_tota_unit_docu,
                       val_prec_cont_tota_loca,
                       val_prec_cata_tota_loca,
                       val_prec_cata_tota_docu,
                       val_prec_cont_tota_docu,
                       val_porc_desc,
                       val_prec_cata_tota_loca_unid,
                       num_unid_dema_real,
                       num_unid_compr,
                       val_prec_fact_tota_loca,
                       val_prec_fact_tota_docu,
                       val_prec_sin_impu_tota_loca,
                       val_prec_neto_tota_loca,
                       ofde_oid_deta_ofer,
                       espo_oid_esta_posi,
                       stpo_oid_subt_posi,
                       val_codi_vent,
                       ind_no_impr,
                       oid_nive_ofer,
                       oid_nive_ofer_rang,
                       num_unid_orig,
                       val_codi_orig)
                      select ped_sopo_seq.nextval,
                             (SELECT MAX(cod_posi)
                             FROM ped_solic_posic
                             WHERE soca_oid_soli_cabe = p_oidsoli) + rownum,
                             x1.num_unid_dema,
                             x1.num_unid_por_aten,
                             x1.val_tasa_impu,
                             p_oidsoli,
                             x1.taim_oid_tasa_impu,
                             4,
                             x1.prod_oid_prod, 
                             x1.fopa_oid_form_pago,
                             x1.precio_unitario,
                             x1.precio,
                             x1.precio_unitario,
                             x1.precio,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             0,
                             x1.num_unid_dema_real,
                             x1.num_unid_por_aten,
                             0,
                             0,
                             0,
                             0,
                             x1.oid_deta_ofer,
                             4,
                             2029,
                             x1.val_codi_vent,
                             x1.ind_no_impr,
                             x1.oid_nive_ofer,
                             x1.oid_nive_ofer_rang,
                             x1.num_unid_orig,
                             x1.val_codi_orig                             
                              from 
                             (
                      SELECT distinct
                             b.num_unid_dema,
                             b.num_unid_dema_real,
                             b.num_unid_por_aten,
                             b.val_tasa_impu,
                             p_oidsoli,
                             b.taim_oid_tasa_impu,
                             4,
                             i.prod_oid_prod, 
                             i.fopa_oid_form_pago,
                             i.precio_unitario,
                             decode(i.imp_prec_cata, 0, i.imp_prec_posi, 0) precio,
                             i.oid_deta_ofer,
                             i.val_codi_vent,
                             b.ind_no_impr,
                             b.oid_nive_ofer,
                             b.oid_nive_ofer_rang,
                             b.num_unid_orig,
                             b.val_codi_orig
                        FROM ped_solic_cabec       a,
                             pre_ofert             c,
                             pre_matri_factu_cabec d,
                             pre_ofert_detal       e,
                             pre_matri_factu       f,
                             pre_matri_reemp       g,
                             pre_matri_factu       h,
                             pre_ofert_detal       i,
                             ped_solic_posic       b,
                             zon_terri_admin z1,
                             zon_terri z2,
                             zon_secci z3,
                             zon_zona z4,
                             zon_regio z5,
                             mae_clien_tipo_subti z6,
                             mae_clien_clasi z7
                       WHERE a.perd_oid_peri = d.perd_oid_peri
                         and a.ztad_oid_terr_admi=z1.oid_terr_admi
                         and z1.ZSCC_OID_SECC=z3.oid_secc
                         and z1.terr_oid_terr=z2.oid_terr
                         and z3.zzon_oid_zona=z4.oid_zona
                         and z4.zorg_oid_regi=z5.oid_regi
                         and (g.zzon_oid_zona is null or g.zzon_oid_zona=z4.oid_zona)
                         and (g.zorg_oid_regi is null or g.zorg_oid_regi=z5.oid_regi)
                         and a.clie_oid_clie=z6.clie_oid_clie
                         and z6.oid_clie_tipo_subt=z7.ctsu_oid_clie_tipo_subt
                         and (g.ticl_oid_tipo_clie is null or g.ticl_oid_tipo_clie=z6.ticl_oid_tipo_clie)
                         and (g.sbti_oid_subti_clien is null or g.sbti_oid_subti_clien=z6.sbti_oid_subt_clie)
                         and (g.tccl_oid_tipo_clas is null or g.tccl_oid_tipo_clas=z7.tccl_oid_tipo_clasi)
                         and (g.clas_oid_clas is null or g.clas_oid_clas=z7.clas_oid_clas)
                         AND a.oid_soli_cabe=b.soca_oid_soli_cabe
                         AND d.oid_cabe = c.mfca_oid_cabe
                         AND c.oid_ofer = e.ofer_oid_ofer
                         AND b.val_codi_vent = e.val_codi_vent
                         AND e.oid_deta_ofer = f.ofde_oid_deta_ofer
                         AND f.oid_matr_fact = g.mafa_oid_cod_ppal
                         AND g.mafa_oid_cod_reem = h.oid_matr_fact
                         AND h.ofde_oid_deta_ofer = i.oid_deta_ofer
                         and b.stpo_oid_subt_posi<>2030
                         and b.num_unid_por_aten>0
                         AND a.oid_soli_cabe = p_oidsoli
                         AND g.ind_reem_ante_cuad = p_tiporee
                         and g.ind_acti=1) x1;

                       
                        
                          UPDATE ped_solic_posic x
                             SET x.num_unid_por_aten      = 0,
                                 x.num_unid_dema_real     = 0,
                                 x.num_unid_compr         = 0,
                                 x.num_unid_aten          = 0,
                                 x.stpo_oid_subt_posi     = 2030,
                                 x.tpos_oid_tipo_posi     = 4,
                                 x.val_obse               = l_desReemp || ' ' ||
                                 (SELECT max(i.val_codi_vent)
                                    FROM ped_solic_cabec       a,
                                         pre_ofert             c,
                                         pre_matri_factu_cabec d,
                                         pre_ofert_detal       e,
                                         pre_matri_factu       f,
                                         pre_matri_reemp       g,
                                         pre_matri_factu       h,
                                         pre_ofert_detal       i,
                                         ped_solic_posic       b,
                                         zon_terri_admin z1,
                                         zon_terri z2,
                                         zon_secci z3,
                                         zon_zona z4,
                                         zon_regio z5,
                                         mae_clien_tipo_subti z6,
                                         mae_clien_clasi z7
                                   WHERE a.perd_oid_peri = d.perd_oid_peri
                                       and a.ztad_oid_terr_admi=z1.oid_terr_admi
                                       and z1.ZSCC_OID_SECC=z3.oid_secc
                                       and z1.terr_oid_terr=z2.oid_terr
                                       and z3.zzon_oid_zona=z4.oid_zona
                                       and z4.zorg_oid_regi=z5.oid_regi
                                       and (g.zzon_oid_zona is null or g.zzon_oid_zona=z4.oid_zona)
                                       and (g.zorg_oid_regi is null or g.zorg_oid_regi=z5.oid_regi)
                                       and a.clie_oid_clie=z6.clie_oid_clie
                                       and z6.oid_clie_tipo_subt=z7.ctsu_oid_clie_tipo_subt
                                       and (g.ticl_oid_tipo_clie is null or g.ticl_oid_tipo_clie=z6.ticl_oid_tipo_clie)
                                       and (g.sbti_oid_subti_clien is null or g.sbti_oid_subti_clien=z6.sbti_oid_subt_clie)
                                       and (g.tccl_oid_tipo_clas is null or g.tccl_oid_tipo_clas=z7.tccl_oid_tipo_clasi)
                                       and (g.clas_oid_clas is null or g.clas_oid_clas=z7.clas_oid_clas)
                                     AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                                     AND d.oid_cabe = c.mfca_oid_cabe
                                     AND c.oid_ofer = e.ofer_oid_ofer
                                     AND b.val_codi_vent = e.val_codi_vent
                                     AND e.oid_deta_ofer = f.ofde_oid_deta_ofer
                                     AND f.oid_matr_fact = g.mafa_oid_cod_ppal
                                     AND g.mafa_oid_cod_reem = h.oid_matr_fact
                                     AND h.ofde_oid_deta_ofer = i.oid_deta_ofer
                                     AND a.oid_soli_cabe = p_oidsoli
                                     and e.val_codi_vent = x.val_codi_vent
                                     AND g.ind_reem_ante_cuad = p_tiporee)
                                 
                           WHERE val_codi_vent IN
                                 (SELECT e.val_codi_vent
                                    FROM ped_solic_cabec       a,
                                         pre_ofert             c,
                                         pre_matri_factu_cabec d,
                                         pre_ofert_detal       e,
                                         pre_matri_factu       f,
                                         pre_matri_reemp       g,
                                         pre_matri_factu       h,
                                         pre_ofert_detal       i,
                                         ped_solic_posic       b,
                                         zon_terri_admin z1,
                                         zon_terri z2,
                                         zon_secci z3,
                                         zon_zona z4,
                                         zon_regio z5,
                                         mae_clien_tipo_subti z6,
                                         mae_clien_clasi z7
                                   WHERE a.perd_oid_peri = d.perd_oid_peri
                                       and a.ztad_oid_terr_admi=z1.oid_terr_admi
                                       and z1.ZSCC_OID_SECC=z3.oid_secc
                                       and z1.terr_oid_terr=z2.oid_terr
                                       and z3.zzon_oid_zona=z4.oid_zona
                                       and z4.zorg_oid_regi=z5.oid_regi
                                       and (g.zzon_oid_zona is null or g.zzon_oid_zona=z4.oid_zona)
                                       and (g.zorg_oid_regi is null or g.zorg_oid_regi=z5.oid_regi)
                                       and a.clie_oid_clie=z6.clie_oid_clie
                                       and z6.oid_clie_tipo_subt=z7.ctsu_oid_clie_tipo_subt
                                       and (g.ticl_oid_tipo_clie is null or g.ticl_oid_tipo_clie=z6.ticl_oid_tipo_clie)
                                       and (g.sbti_oid_subti_clien is null or g.sbti_oid_subti_clien=z6.sbti_oid_subt_clie)
                                       and (g.tccl_oid_tipo_clas is null or g.tccl_oid_tipo_clas=z7.tccl_oid_tipo_clasi)
                                       and (g.clas_oid_clas is null or g.clas_oid_clas=z7.clas_oid_clas)
                                      AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                                     AND d.oid_cabe = c.mfca_oid_cabe
                                     AND c.oid_ofer = e.ofer_oid_ofer
                                     AND b.val_codi_vent = e.val_codi_vent
                                     AND e.oid_deta_ofer = f.ofde_oid_deta_ofer
                                     AND f.oid_matr_fact = g.mafa_oid_cod_ppal
                                     AND g.mafa_oid_cod_reem = h.oid_matr_fact
                                     AND h.ofde_oid_deta_ofer = i.oid_deta_ofer
                                     AND a.oid_soli_cabe = p_oidsoli
                                     and b.num_unid_por_aten>0
                                     AND g.ind_reem_ante_cuad = p_tiporee
                                     and g.ind_acti=1)
                             AND x.soca_oid_soli_cabe = p_oidsoli;
  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN;
  END ped_pr_reemp_pedid;

  /***************************************************************************
  Descripcion       : Procea el pedido enviado desde el WS
  Fecha Creacion    : 29/09/2015
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE ped_pr_gener_pedid_ws
  (
    p_numcorr    VARCHAR
  )
  
   IS
    pscodigopais     sto_param_gener_occrr.cod_pais%TYPE;  
    psnumcorr        ped_solic_Cabec_ws.Num_Corr%TYPE;  
    pscodperi        ped_solic_Cabec_ws.Cod_Peri%TYPE;  
    pscodclie        ped_solic_Cabec_ws.Cod_Clie%TYPE;  
    pscodzona        zon_zona.cod_zona%TYPE;  
    lnoidzona        zon_zona.oid_zona%TYPE;  
    lnoidterr        zon_terri.oid_terr%TYPE; 
    lnoidterradmi    zon_terri_admin.oid_terr_admi%TYPE; 
    lnoidclie        mae_clien.oid_clie%TYPE; 
    lnoidperi        cra_perio.oid_peri%TYPE; 
    
    psno_existe_deuda      BOOLEAN;
    pssaldo_deudor         NUMBER;
    pssaldo_rechazo        NUMBER;
    psabono_pendien        NUMBER;
    psexce_flex            NUMBER;
    pssald_maxi_camp_flex  NUMBER;
    psobse                 VARCHAR2(100);    
    psnroregistros         NUMBER;
    
    lscoderror       PED_SOLIC_ERROR_WS.Cod_Error%TYPE;
    lsdeserror       PED_SOLIC_ERROR_WS.Des_Error%TYPE;
    lsesta          VARCHAR(1);

    lnnumsoliinicio          NUMBER;
    lnnumsoliformat          NUMBER;
    
    lnoidsolicabe            NUMBER;
    
    lnoidtiposolisoc         NUMBER;

    
  BEGIN
      lscoderror := '00';
      --- recupera pais
      BEGIN
         select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;
      EXCEPTION WHEN OTHERS THEN
         lscoderror := '01';
         lsdeserror := 'Codigo de pais no existe';
      end;
      --- valida si hay registros en el registro recupera cliente y periodo
      if lscoderror = '00' then
        BEGIN
          select pscw.cod_clie, pscw.cod_peri into pscodclie , pscodperi
          from PED_SOLIC_CABEC_WS pscw
          where pscw.num_corr = p_numcorr;
        EXCEPTION WHEN OTHERS THEN
           lscoderror := '02';
           lsdeserror := 'No existe el numero de lote';
        end;
      end if;
      --- recupera la zona
      if lscoderror = '00' then
        BEGIN
          select zz.cod_zona, a.oid_clie, zz.oid_zona, zt.oid_terr, zta.oid_terr_admi into pscodzona, lnoidclie, lnoidzona, lnoidterr, lnoidterradmi 
          from mae_clien a, mae_clien_unida_admin vmae, zon_terri_admin zta, zon_terri zt, zon_secci zs, zon_zona zz
          where a.oid_clie=vmae.clie_oid_clie and a.cod_clie = pscodclie
          and vmae.ind_acti='1'
          and vmae.ztad_oid_terr_admi=zta.oid_terr_admi
          and zta.terr_oid_terr=zt.oid_terr
          and zta.zscc_oid_secc=zs.oid_secc
          and zs.zzon_oid_zona=zz.oid_zona
          ;
        EXCEPTION WHEN OTHERS THEN
           lscoderror := '03';
           lsdeserror := 'No existe la zona ni cliente';
        end;
      end if;
      --- valida el periodo
      if lscoderror = '00' then
        BEGIN
          select val_esta, cra.oid_peri into lsesta, lnoidperi
          from cra_perio cra, seg_perio_corpo seg
          where CRA.PERI_OID_PERI = SEG.OID_PERI
          and SEG.COD_PERI = pscodperi;
        EXCEPTION WHEN OTHERS THEN
           lscoderror := '04';
           lsdeserror := 'Periodo errado';
        end;
      end if;
      --- invoca el SP de deuda
      if lscoderror = '00' then
         sto_pkg_proce_valid_occ.sto_pr_calcu_deuda(pscodigopais,pscodclie,pscodzona,
                                                    '',pscodperi,psno_existe_deuda,
                                                    pssaldo_deudor,pssaldo_rechazo,
                                                    psabono_pendien,psexce_flex,
                                                    pssald_maxi_camp_flex,psobse);
                                                    
         if not psno_existe_deuda then
           
            update PED_SOLIC_CABEC_WS pscw
            set pscw.flag_erro = '1', pscw.mon_deu = pssaldo_deudor - psabono_pendien
            where num_corr = p_numcorr;
            
            delete  PED_SOLIC_ERROR_WS 
            where num_corr_ped = p_numcorr;

            lscoderror := '05';
            lsdeserror := 'Consultora con deuda';


         end if;
         
      end if;

      --- si hay error actualiza tabla
      if lscoderror <> '00' then

            Insert into PED_SOLIC_ERROR_WS
               (NUM_CORR_PED, COD_ERROR, DES_ERROR)
             Values
               (p_numcorr, lscoderror, lsdeserror);
            
      end if;

--------------------------------------
      select count(0) into psnroregistros 
      from PED_SOLIC_POSIC_WS where num_corr_ped = p_numcorr; 
      
      if psnroregistros > 0 then
      
          lnnumsoliinicio := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                                    'PED001',
                                                                    '000',
                                                                    1);
        
          lnnumsoliformat := to_char(SYSDATE, 'YY') ||
                             lpad(lnnumsoliinicio, 8, '0') + 1;

          select ped_soca_seq.nextval into lnoidsolicabe from dual;

          SELECT tsp.oid_tipo_soli_pais
            INTO lnoidtiposolisoc
            FROM ped_tipo_solic_pais tsp,
                 ped_tipo_solic      ts
           WHERE ts.oid_tipo_soli = tsp.tsol_oid_tipo_soli
             AND ts.cod_tipo_soli = 'SOC';

                  /*SE INSERTA LA CABECERA DE LOS PEDIDOS*/
                  INSERT INTO ped_solic_cabec
                    (oid_soli_cabe,
                     fec_prog_fact,
                     num_clien,
                     tspa_oid_tipo_soli_pais,
                     mone_oid_mone,
                     tids_oid_tipo_desp,
                     almc_oid_alma,
                     modu_oid_modu,
                     ticl_oid_tipo_clie,
                     taim_oid_tasa_impu,
                     perd_oid_peri,
                     clie_oid_clie,
                     clie_oid_clie_rece_fact,
                     clie_oid_clie_paga,
                     clie_oid_clie_dest,
                     cldi_oid_clie_dire,
                     tdoc_oid_tipo_docu,
                     soci_oid_soci,
                     sbac_oid_sbac,
                     terr_oid_terr,
                     zzon_oid_zona,
                     val_nume_soli,
                     val_usua,
                     val_tasa_impu,
                     fec_cron,
                     ind_perm_unio_sol,
                     val_tipo_camb,
                     num_docu_orig,
                     val_base_flet_loca,
                     val_impo_flet_loca,
                     val_impo_flet_tota_loca,
                     val_impo_flet_sin_impu_tota,
                     val_reca_flet_loca,
                     val_otro_reca_loca,
                     val_tota_paga_loca,
                     val_prec_cata_tota_loca,
                     val_prec_cata_sin_impu_tota,
                     val_prec_fact_tota_loca,
                     val_impo_impu_tota_loca,
                     val_impo_desc_1_tota_loca,
                     val_impo_desc_1_tota_docu,
                     val_impo_desc_1_sin_impu_tota,
                     val_impo_desc_3_tota_docu,
                     val_impo_desc_3_sin_impu_tota,
                     val_impo_desc_tota_loca,
                     val_impo_dto_1_sin_imp_tot_loc,
                     val_impo_redo_loca,
                     val_base_flet_docu,
                     val_impo_flet_docu,
                     val_impo_desc_tota_docu,
                     val_impo_flet_sin_impu_docu,
                     val_reca_flet_docu,
                     val_otro_reca_docu,
                     val_tota_flet_docu,
                     val_impo_flet_tota_docu,
                     val_tota_flet_loca,
                     val_tota_paga_docu,
                     val_prec_cata_tota_docu,
                     val_prec_cata_sin_impu_tota_do,
                     val_prec_cont_tota_loca,
                     val_prec_cont_sin_impu_tota,
                     val_prec_cont_sin_impu_tota_1,
                     val_prec_fact_tota_docu,
                     val_prec_cata_tota_loc_uni_dem,
                     val_prec_neto_tota_docu,
                     val_prec_neto_tota_loca,
                     val_impo_impu_tota_docu,
                     val_impo_redo_docu,
                     val_impo_redo_cons_loca,
                     val_impo_redo_cons_docu,
                     ind_oc,
                     ind_pedi_prue,
                     ind_ts_no_conso,
                     val_glos_obse,
                     val_impo_desc_3_tota_loca,
                     val_impo_dto_3_sin_imp_tot_loc,
                     pais_oid_pais,
                     tido_oid_tipo_docu,
                     vepo_oid_valo_estr_geop,
                     esso_oid_esta_soli,
                     grpr_oid_grup_proc,
                     sbti_oid_subt_clie,
                     acfi_oid_acce_fisi,
                     tspa_oid_tipo_soli_pais_cons,
                     fopa_oid_form_pago,
                     clso_oid_clas_soli,
                     ztad_oid_terr_admi,
                     oper_oid_oper,
                     proc_oid_proc,
                     ind_inte_lari_gene,
                     fec_prog_fact_comp,
                     val_reca_flet,
                     ind_rece_onli,
                     ind_vali_prol)
                     SELECT lnoidsolicabe,
                     trunc(sysdate),
                     0,
                     lnoidtiposolisoc,
                     NULL,
                     NULL,
                     2001,
                     1,
                     2,
                     NULL,
                     cp.oid_peri,
                     mc.oid_clie,
                     mc.oid_clie,
                     mc.oid_clie,
                     mc.oid_clie,
                     NULL,
                     1,
                     2001,
                     888,
                     lnoidterr,
                     lnoidzona,
                     lnnumsoliformat,
                     '',
                     NULL,
                     trunc(SYSDATE),
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     0,
                     1,
                     0,
                     1,
                     '',
                     0,
                     0,
                     mc.pais_oid_pais,
                     1,
                     NULL,
                     1,
                     1,
                     1,
                     1,
                     NULL, --v_cab_tspa_oid_tipo_solp_cons(i),
                     NULL,
                     NULL,
                     lnoidterradmi, --v_cab_ztad_oid_terr_admi(i),
                     40,
                     --1,
                     1,
                     0,
                     '',
                     0,
                     0,
                     0
                      FROM PED_SOLIC_CABEC_WS pws,
                           mae_clien mc,
                           cra_perio cp,
                           seg_perio_corpo spc
                     WHERE pws.cod_clie=mc.cod_clie
                     and pws.num_corr=p_numcorr
                     and pws.cod_peri=spc.cod_peri
                     and spc.oid_peri=cp.peri_oid_peri
                     ;


                
                  /*INSERTA LOS DETALES DEL PEDIDO*/
                
                  INSERT INTO ped_solic_posic
                    (oid_soli_posi,
                     cod_posi,
                     num_unid_dema,
                     num_unid_por_aten,
                     val_tasa_impu,
                     soca_oid_soli_cabe,
                     taim_oid_tasa_impu,
                     tpos_oid_tipo_posi,
                     prod_oid_prod,
                     fopa_oid_form_pago,
                     val_prec_cata_unit_loca,
                     val_prec_cont_unit_loca,
                     val_prec_cata_unit_docu,
                     val_prec_conta_unit_docu,
                     val_prec_fact_unit_loca,
                     val_prec_fact_unit_docu,
                     val_prec_sin_impu_unit_loca,
                     val_prec_sin_impu_unit_docu,
                     val_prec_sin_impu_tota_docu,
                     val_impo_desc_unit_loca,
                     val_impo_desc_unit_docu,
                     val_prec_neto_unit_loca,
                     val_prec_neto_tota_docu,
                     val_prec_neto_unit_docu,
                     val_prec_tota_tota_loca,
                     val_prec_tota_tota_docu,
                     val_impo_impu_unit_loca,
                     val_impo_impu_unit_docu,
                     val_impo_desc_tota_docu,
                     val_impo_impu_tota_loca,
                     val_impo_impu_tota_docu,
                     val_impo_desc_tota_loca,
                     val_prec_tota_unit_loca,
                     val_prec_tota_unit_docu,
                     val_prec_cont_tota_loca,
                     val_prec_cata_tota_loca,
                     val_prec_cata_tota_docu,
                     val_prec_cont_tota_docu,
                     val_porc_desc,
                     val_prec_cata_tota_loca_unid,
                     num_unid_dema_real,
                     num_unid_compr,
                     val_prec_fact_tota_loca,
                     val_prec_fact_tota_docu,
                     val_prec_sin_impu_tota_loca,
                     val_prec_neto_tota_loca,
                     ofde_oid_deta_ofer,
                     espo_oid_esta_posi,
                     stpo_oid_subt_posi,
                     val_codi_vent,
                     ind_no_impr,
                     almc_oid_almc,
                     ind_recu_prol)
                    SELECT ped_sopo_seq.nextval,
                           rownum,
                           det.num_unid,
                           det.num_unid,
                           0,
                           lnoidsolicabe,
                           NULL,
                           1,
                           pof.prod_oid_prod,
                           NULL,
                           nvl(pof.precio_unitario, 0),
                           decode(nvl(pof.imp_prec_cata, 0),
                                  0,
                                  pof.imp_prec_posi,
                                  0),
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           NULL,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           0,
                           NULL,
                           0,
                           det.num_unid,
                           det.num_unid,
                           0,
                           0,
                           0,
                           0,
                           pof.oid_deta_ofer,
                           4,
                           1,
                           det.codi_vent,
                           0,
                           NULL,
                           NULL
                      FROM PED_SOLIC_POSIC_WS det,
                           PED_SOLIC_CABEC_WS cab,
                           pre_matri_factu_cabec pmfc,
                           pre_ofert po,
                           pre_ofert_detal pof,
                           cra_perio cp,
                           seg_perio_corpo spc
                     WHERE det.num_corr_ped=cab.num_corr
                       and cab.cod_peri=spc.cod_peri
                       and spc.oid_peri=cp.peri_oid_peri
                       and cp.oid_peri=pmfc.perd_oid_peri
                       and pmfc.oid_cabe=po.mfca_oid_cabe
                       and po.oid_ofer=pof.ofer_oid_ofer
                       and det.codi_vent=pof.val_codi_vent
                       and cab.num_corr = p_numcorr
                       ;


            --------------------------------------
            ped_pr_cuadr_ofert_sicc(lnoidsolicabe, lnoidclie, lnoidperi, lnoidterradmi);

            update ped_solic_posic_ws det
            set det.num_unid_real=(select num_unid_por_aten from ped_solic_posic where soca_oid_soli_cabe=lnoidsolicabe and DET.CODI_VENT = val_codi_vent),
                det.num_unid_aten=(select num_unid_por_aten from ped_solic_posic where soca_oid_soli_cabe=lnoidsolicabe and DET.CODI_VENT = val_codi_vent),
                det.obs_deta=(select val_obse from ped_solic_posic where soca_oid_soli_cabe=lnoidsolicabe and DET.CODI_VENT = val_codi_vent)
            where num_corr_ped=p_numcorr;


            insert into ped_solic_posic_ws det
            select p_numcorr,
                   pos.val_codi_vent,
                   0,
                   pos.num_unid_por_aten,
                   pos.num_unid_por_aten,
                   pos.val_prec_cata_unit_loca,
                   0,
                   0,
                   pos.val_prec_cata_unit_loca*pos.num_unid_por_aten,
                   pos.val_obse,
                   pos.ofde_oid_deta_ofer,
                   NULL
            from ped_solic_posic pos where soca_oid_soli_cabe=lnoidsolicabe
            and val_codi_vent not in (select codi_vent from ped_solic_posic_ws where num_corr_ped=p_numcorr);


            delete from ped_solic_posic where soca_oid_soli_cabe=lnoidsolicabe;
            delete from ped_solic_cabec where oid_soli_cabe=lnoidsolicabe;

      end if;


      --- actualiza la informacion de fecha y hora
      update PED_SOLIC_CABEC_WS pscw
      set pscw.fec_actu = to_char(trunc(sysdate) , 'DD/MM/YYYY'), 
          pscw.hor_actu = to_char(systimestamp,'hh24:mi:ss.FF') ---   TO_CHAR(sysdate, 'HH:MI:SS AM')  
      where num_corr = p_numcorr;
      

      RETURN;

  
  EXCEPTION
    WHEN OTHERS THEN
      RETURN;
  END ped_pr_gener_pedid_ws;

  /***************************************************************************
  Descripcion       : Cambia a GP3 los pedidos aptos que se validaron en GP1
  Fecha Creacion    : 15/01/2016
  Autor             : Jorge Yepez
  Fecha Actualiza   : 15/01/2016
  Autor Actualiza   : Carlos Diaz Valverde
  ***************************************************************************/
  PROCEDURE ped_pr_cambi_gp1_gp3
  (
    p_codigopais IN VARCHAR2,
    p_fechafact  IN VARCHAR2,
    p_codperi    IN VARCHAR2
  ) IS
BEGIN
     update ped_solic_cabec x set grpr_oid_grup_proc=3 where
     x.tspa_oid_tipo_soli_pais=(select c.oid_tipo_soli_pais from ped_tipo_solic_pais c, ped_tipo_solic d
                                where c.tsol_oid_tipo_soli=d.oid_tipo_soli
                                and d.cod_tipo_soli='SOC')
     and x.fec_fact=to_date(p_fechafact,'dd/mm/yyyy')
     and x.perd_oid_peri=(select a.oid_peri from cra_perio a, seg_perio_corpo b
                       where a.peri_oid_peri=b.oid_peri and b.cod_peri=p_codperi
     )
     and x.esso_oid_esta_soli=1 and x.espe_oid_esta_pedi=1 and x.grpr_oid_grup_proc=1
     ;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(sqlerrm,1,1000);
      RAISE_APPLICATION_ERROR(-20123, 'ERROR ped_pr_cambi_gp1_gp3 : ' || ls_sqlerrm);
END ped_pr_cambi_gp1_gp3;


END ped_pkg_cuadr_ofert;
/
