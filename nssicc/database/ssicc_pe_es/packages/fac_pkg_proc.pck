CREATE OR REPLACE PACKAGE fac_pkg_proc IS

  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(1000);

  /***************************************************************************
  Descripcion       : Calculo de Gastos Administrativos
  Fecha Creacion    : 06/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_gastos_admi_sicc;
 /**************************************************************************
  Descripcion        : Altualiza el tipo de Pedido
  Fecha Creacion     : 24/09/2015
  Autor              : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_tipo_pedido
  (
    p_oidcons NUMBER
  ) ;

  /***************************************************************************
  Descripcion       : Generacion de Consolidados
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_consolidado
  (
    pnoidperi          NUMBER,
    psfechafacturacion VARCHAR2,
    pscodigopais       VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Funcion generacion de Consolidados por oid solicitud
  Fecha Creacion    : 24/05/2012
  Autor             : Sandro Quintana
  ***************************************************************************/
  FUNCTION fac_fn_genera_consolidado
  (
    p_oidsolic         NUMBER,
    psfechafacturacion VARCHAR2,
    pscodigopais       VARCHAR2
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Calculos Consolidado
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado(p_oidcons NUMBER);
  /***************************************************************************
  Descripcion       : Calculos Consolidado
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado_r(p_oidcons NUMBER);
  /***************************************************************************
  Descripcion       : Calculos Consolidado
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado_z
  (
    p_oidzona          NUMBER,
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Calculos Consolidado
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado_z2
  (
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Calculos Consolidado Modo 2
  Fecha Creacion    : 26/03/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado2(p_oidcons NUMBER);
  /***************************************************************************
  Descripcion       : Calculos Consolidado Modo 2
  Fecha Creacion    : 26/03/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado3(p_oidcons NUMBER);

  /***************************************************************************
  Descripcion       : Calculos Consolidado Modo 2 reclamos
  Fecha Creacion    : 26/03/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado2_r(p_oidcons NUMBER);
  /***************************************************************************
  Descripcion       : Calculos Consolidado Modo 2 reclamos
  Fecha Creacion    : 26/03/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado3_r(p_oidcons NUMBER);

  /***************************************************************************
  Descripcion       : Genera Documentos Legales
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_doclega(p_oidcons NUMBER);
  /***************************************************************************
  Descripcion       : Genera Documentos Legales
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_doclega_z
  (
    p_oidzona          NUMBER,
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Registro Unico de Ventas
  Fecha Creacion    : 26/03/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_ruv(p_oidcons NUMBER);
  /***************************************************************************
  Descripcion       : Genera Registro Unico de Ventas
  Fecha Creacion    : 26/03/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_ruv_z
  (
    p_oidzona          NUMBER,
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Genera Registro Unico de Ventas
  Fecha Creacion    : 26/03/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_ccc(p_oidcons NUMBER);
  /***************************************************************************
  Descripcion       : Genera Cuenta Corriente
  Fecha Creacion    : 26/03/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_ccc_z
  (
    p_oidzona          NUMBER,
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Generacion de Consolidados
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_bol_elec_z
  (
    p_oidzona          NUMBER,
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  );
  /***************************************************************************
  Descripcion       : Generacion de Consolidados
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_bol_elec(p_oidcons NUMBER);

  /***************************************************************************
  Descripcion       : Generacion de Boleta Electronica Reclamos
  Fecha Creacion    : 25/05/2012
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE fac_pr_genera_bol_elec_rec(pnoidconso NUMBER);

  /**************************************************************************
  Descripcion        : Calcula Dias de Entrga de Pedido
  Fecha Creacion     : 30/01/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_obt_dias_fecha_ent
  (
    pnoidsoli NUMBER,
    pnremesa NUMBER,
    pscodperi VARCHAR2,
    lsfecfact DATE
  ) RETURN NUMBER;

  /**************************************************************************
  Descripcion        : Calcula Dias de Entrga de Pedido a partir de la fecha de la zona de facturacion.
  Fecha Creacion     : 30/08/2013
  Autor              : Rosalvina Ramirez
  ***************************************************************************/

 FUNCTION ped_fn_obt_dias_fecha_ent2
  (
    psCodPais VARCHAR2,
    psCodRegi VARCHAR2,
    psCodZona VARCHAR2,
    psCodSecc VARCHAR2,
    psCodTerr VARCHAR2,
    pnremesa NUMBER,
    pscodperiActual VARCHAR2,
    psCodigoCliente VARCHAR2
  ) RETURN DATE;
  /**************************************************************************
  Descripcion        : Calcula Dias de Entrga de Pedido a partir de la fecha de la zona de facturacion.
  Fecha Creacion     : 30/08/2013
  Autor              : Rosalvina Ramirez
  ***************************************************************************/

 FUNCTION ped_fn_obt_dias_fecha_ent3
  (
    psCodRegi VARCHAR2,
    psCodZona VARCHAR2,
    psCodSecc VARCHAR2,
    psCodTerr VARCHAR2,
    pnremesa NUMBER,
    pscodperiActual VARCHAR2
  ) RETURN DATE;

    /**************************************************************************
  Descripcion        : Devuelve el dia de facturacion.
  Fecha Creacion     : 10/09/2014
  Autor              : Rosalvina Ramirez
  ***************************************************************************/

  FUNCTION ped_fn_dev_dia_fact
  (
    pscodperi VARCHAR2,
    pscodzona VARCHAR2,
    pnremesa NUMBER
  ) RETURN DATE;
  /***************************************************************************
  Descripcion       : Genera Fecha de Reparto
  Fecha Creacion    : 22/04/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_fecrep_z
  (
    p_oidzona          NUMBER,
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  );

  /***************************************************************************
  Descripcion       : Genera Fecha de Reparto
  Fecha Creacion    : 22/04/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_fecrep(p_oidcons NUMBER, p_codperi VARCHAR2);
 /***************************************************************************
  Descripcion       : Genera PUP por Producto
  Fecha Creacion    : 08/07/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_pup
  (
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  );

 /**************************************************************************
  Descripcion        : Calcula IVA Asumido por Colombia
  Fecha Creacion     : 23/09/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_gener_iva_asum
  (
    pnoidcabe NUMBER,
    pntasa    NUMBER,
    pndecim   NUMBER,
    dato      NUMBER,
    valgratis NUMBER,
    valfacsiniva NUMBER
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Genera Factura Global Mexico
  Fecha Creacion    : 28/10/2014
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_fact_global
    (
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  ) ;

  /***************************************************************************
  Descripcion       : Cambio en promesa de entrega
  Fecha Creacion    : 27/05/2015
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_cambio_fecha_prom
  (
    psvalnumesoli          VARCHAR2,
    psNuevafecha           VARCHAR2,
    psNuevoTurno           VARCHAR2
  ) ;

  /***************************************************************************
  Descripcion       : Actualiza datos de pedido intentariado 
  Fecha Creacion    : 23/09/2015
  Autor             : sandro Quitana
  ***************************************************************************/
  PROCEDURE ped_pr_actua_datos_ws
  (
    pscodclie      in        VARCHAR2,
    pscodplat      in        VARCHAR2,
    psfecentr      in        VARCHAR2,
    psindentr      in        VARCHAR2,
    pslatentr      in        VARCHAR2,
    pslonentr      in        VARCHAR2,
    psnoventr      in        VARCHAR2,
    psnropedi      in        VARCHAR2,
    psdesobse      in        VARCHAR2,
    pscodpais      in        VARCHAR2,
    psindinve      in        VARCHAR2,
    pstipentr      in        VARCHAR2
  ) ;


  /***************************************************************************
  Descripcion       : Devueve datos al web service generico
  Fecha Creacion    : 23/09/2015
  Autor             : sandro Quitana
  ***************************************************************************/
  PROCEDURE ped_pr_datos_pedid_ws
  (
    pscodserv      in        VARCHAR2,
    psdatserv      in        VARCHAR2,
    psdatserv2     in        VARCHAR2,
    psdatserv3     in        VARCHAR2,
    psdatserv4     in        VARCHAR2,
    pscodcert      in        VARCHAR2,
    pscodreto      out       VARCHAR2,
    psvalreto      out       VARCHAR2,
    psvalreto2     out       VARCHAR2,
    psvalreto3     out       VARCHAR2,
    psvalreto4     out       VARCHAR2
  ) ;
  
      /**************************************************************************
  Descripcion        : Devuelve el dias de desfase
  Fecha Creacion     : 15/12/2015
  Autor              : Rosalvina Ramirez
  ***************************************************************************/

    FUNCTION ped_fn_dev_dia_desf
    (
      pscodperi VARCHAR2,
      pscodregi VARCHAR2,
      pscodzona VARCHAR2,
      pscodsecc VARCHAR2,
      pscodterr NUMBER,
      psdiafact NUMBER,
      pstipcons VARCHAR2
    ) RETURN NUMBER;
END fac_pkg_proc;
/
CREATE OR REPLACE PACKAGE BODY fac_pkg_proc IS

  /***************************************************************************
  Descripcion       : Calculo de Gastos Administrativos
  Fecha Creacion    : 06/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/

  PROCEDURE fac_pr_gastos_admi_sicc IS

    ln_valtasa NUMBER(2);
    ln_oidperi NUMBER(10);
    lv_fecfact DATE;
    ln_numinte NUMBER(20);

    lv_indiejec VARCHAR2(20);
    lv_codpais  VARCHAR2(20);
    --w_filas        NUMBER(12);

  BEGIN

    SELECT a.val_tasa_impu
      INTO ln_valtasa
      FROM ped_tasa_impue  a,
           ped_impue_gener b
     WHERE b.taim_oid_tasa_impu = a.oid_tasa_impu
       AND b.sbac_oid_sbac = 888;

    SELECT x.oid_peri
      INTO ln_oidperi
      FROM cra_perio       x,
           seg_perio_corpo y,
           bas_ctrl_fact   z
     WHERE x.peri_oid_peri = y.oid_peri
       AND y.cod_peri = z.cod_peri
       AND z.ind_camp_act = 1
       AND z.sta_camp = 0;

    SELECT z.fec_proc
      INTO lv_fecfact
      FROM bas_ctrl_fact z
     WHERE z.ind_camp_act = 1
       AND z.sta_camp = 0;

    SELECT z.cod_pais
      INTO lv_codpais
      FROM cra_perio       x,
           seg_perio_corpo y,
           bas_ctrl_fact   z
     WHERE x.peri_oid_peri = y.oid_peri
       AND y.cod_peri = z.cod_peri
       AND z.ind_camp_act = 1
       AND z.sta_camp = 0;

    lv_indiejec := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lv_codpais,
                                                            'STO_GASTO_ADMIN'),
                       'N');

    IF lv_indiejec <> 'S' THEN
      RETURN;
    END IF;

    UPDATE ped_solic_cabec a
       SET a.val_tota_gast_admi         =
           (SELECT SUM(nvl(val_tota_gast_admi,
                           0))
              FROM ped_solic_cabec
             WHERE soca_oid_soli_cabe = a.oid_soli_cabe
               AND nvl(val_tota_gast_admi,
                       0) <> 0),
           a.val_tota_gast_admi_sin_impu = round((SELECT SUM(nvl(val_tota_gast_admi,
                                                                 0))
                                                    FROM ped_solic_cabec
                                                   WHERE soca_oid_soli_cabe = a.oid_soli_cabe
                                                     AND nvl(val_tota_gast_admi,
                                                             0) <> 0) / (1 + (ln_valtasa / 100)),
                                                 2),
           a.val_tota_impu_gast_admi    =
           (SELECT SUM(nvl(val_tota_gast_admi,
                           0))
              FROM ped_solic_cabec
             WHERE soca_oid_soli_cabe = a.oid_soli_cabe
               AND nvl(val_tota_gast_admi,
                       0) <> 0) - round((SELECT SUM(nvl(val_tota_gast_admi,
                                                        0))
                                           FROM ped_solic_cabec
                                          WHERE soca_oid_soli_cabe = a.oid_soli_cabe
                                            AND nvl(val_tota_gast_admi,
                                                    0) <> 0) / (1 + (ln_valtasa / 100)),
                                        2),
           a.val_tota_paga_loca          = a.val_tota_paga_loca +
                                           (SELECT SUM(nvl(val_tota_gast_admi,
                                                           0))
                                              FROM ped_solic_cabec
                                             WHERE soca_oid_soli_cabe = a.oid_soli_cabe
                                               AND nvl(val_tota_gast_admi,
                                                       0) <> 0),
           a.val_impo_impu_tota_loca     = a.val_impo_impu_tota_loca +
                                           (SELECT SUM(nvl(val_tota_gast_admi,
                                                           0))
                                              FROM ped_solic_cabec
                                             WHERE soca_oid_soli_cabe = a.oid_soli_cabe
                                               AND nvl(val_tota_gast_admi,
                                                       0) <> 0) -
                                           round((SELECT SUM(nvl(val_tota_gast_admi,
                                                                 0))
                                                    FROM ped_solic_cabec
                                                   WHERE soca_oid_soli_cabe = a.oid_soli_cabe
                                                     AND nvl(val_tota_gast_admi,
                                                             0) <> 0) / (1 + (ln_valtasa / 100)),
                                                 2)
     WHERE a.perd_oid_peri = ln_oidperi
          --AND case when a.val_tota_gast_admi>0 then a.fec_fact else trunc(to_date(lv_fecfact)) end = lv_fecfact
       AND a.fec_fact = lv_fecfact
       and a.num_unid_aten_tota<>0
       AND a.tspa_oid_tipo_soli_pais IN
           (SELECT a.oid_tipo_soli_pais --, c.VAL_I18N
              FROM ped_tipo_solic_pais a,
                   ped_tipo_solic      b
             WHERE a.tsol_oid_tipo_soli = b.oid_tipo_soli
               AND (b.cod_tipo_soli IN ('C1','C52','C53') OR (b.ind_anul = 1 AND b.ind_cons = 1)))
       AND EXISTS (SELECT 1
              FROM ped_solic_cabec
             WHERE soca_oid_soli_cabe = a.oid_soli_cabe
               AND nvl(val_tota_gast_admi,
                       0) <> 0)
       AND a.val_tota_gast_admi IS NULL;

    UPDATE fac_docum_conta_cabec a
       SET a.val_tota_gast_admi         =
           (SELECT nvl(val_tota_gast_admi,
                       0)
              FROM ped_solic_cabec
             WHERE oid_soli_cabe = a.soca_oid_soli_cabe
               AND nvl(val_tota_gast_admi,
                       0) <> 0),
           a.val_tota_gast_admi_sin_impu =
           (SELECT nvl(val_tota_gast_admi_sin_impu,
                       0)
              FROM ped_solic_cabec
             WHERE oid_soli_cabe = a.soca_oid_soli_cabe
               AND nvl(val_tota_gast_admi,
                       0) <> 0),
           a.val_tota_impu_gast_admi    =
           (SELECT nvl(val_tota_impu_gast_admi,
                       0)
              FROM ped_solic_cabec
             WHERE oid_soli_cabe = a.soca_oid_soli_cabe
               AND nvl(val_tota_gast_admi,
                       0) <> 0),
           a.val_tota_paga_loca          = a.val_tota_paga_loca +
                                           (SELECT nvl(val_tota_gast_admi,
                                                       0)
                                              FROM ped_solic_cabec
                                             WHERE oid_soli_cabe = a.soca_oid_soli_cabe
                                               AND nvl(val_tota_gast_admi,
                                                       0) <> 0),
           a.imp_impu_tota_loca          = a.imp_impu_tota_loca +
                                           (SELECT nvl(val_tota_impu_gast_admi,
                                                       0)
                                              FROM ped_solic_cabec
                                             WHERE oid_soli_cabe = a.soca_oid_soli_cabe
                                               AND nvl(val_tota_gast_admi,
                                                       0) <> 0)
     WHERE a.perd_oid_peri = ln_oidperi
          --AND a.fec_fact = lv_fecfact
       AND a.fec_fact = lv_fecfact
       AND oid_cabe IN
           (SELECT oid_cabe
              FROM (SELECT soca_oid_soli_cabe,
                           MAX(oid_cabe) oid_cabe
                      FROM fac_docum_conta_cabec fdcc
                     WHERE fdcc.perd_oid_peri = ln_oidperi
                       AND fdcc.fec_fact = lv_fecfact
                       AND fdcc.tido_oid_tipo_docu IN
                           (SELECT DISTINCT y.tido_oid_tipo_docu
                              FROM ped_solic_cabec x,
                                   ped_solic_cabec y
                             WHERE x.oid_soli_cabe = fdcc.soca_oid_soli_cabe
                               AND x.oid_soli_cabe = y.soca_oid_soli_cabe
                               AND nvl(y.val_tota_gast_admi,
                                       0) <> 0)
                       AND fdcc.soca_oid_soli_cabe IN
                           (SELECT oid_soli_cabe
                              FROM ped_solic_cabec a
                             WHERE a.tspa_oid_tipo_soli_pais IN
                                   (SELECT a.oid_tipo_soli_pais --, c.VAL_I18N
                                      FROM ped_tipo_solic_pais a,
                                           ped_tipo_solic      b
                                     WHERE a.tsol_oid_tipo_soli = b.oid_tipo_soli
                                       AND (b.cod_tipo_soli IN ('C1','C52','C53') OR
                                           (b.ind_anul = 1 AND b.ind_cons = 1)))
                               AND a.fec_fact = lv_fecfact
                               AND a.perd_oid_peri = ln_oidperi
                               AND EXISTS (SELECT 1
                                      FROM ped_solic_cabec
                                     WHERE soca_oid_soli_cabe = a.oid_soli_cabe
                                       AND nvl(val_tota_gast_admi,
                                               0) <> 0))
                     GROUP BY fdcc.soca_oid_soli_cabe))
       AND a.val_tota_gast_admi IS NULL;

    UPDATE fac_regis_unico_venta a
       SET a.val_tota_gast_admi =
           (SELECT nvl(b.val_tota_gast_admi,
                       0)
              FROM fac_docum_conta_cabec b
             WHERE b.oid_cabe = a.dcca_oid_cabe),
           a.val_base_impo      = a.val_base_impo +
                                  (SELECT nvl(b.val_tota_gast_admi_sin_impu,
                                              0)
                                     FROM fac_docum_conta_cabec b
                                    WHERE b.oid_cabe = a.dcca_oid_cabe),
           a.val_base_impo_neto = a.val_base_impo +
                                  (SELECT nvl(b.val_tota_gast_admi_sin_impu,
                                              0)
                                     FROM fac_docum_conta_cabec b
                                    WHERE b.oid_cabe = a.dcca_oid_cabe) - val_desc,
           a.imp_impu           = a.imp_impu + (SELECT nvl(b.val_tota_impu_gast_admi,
                                                           0)
                                                  FROM fac_docum_conta_cabec b
                                                 WHERE b.oid_cabe = a.dcca_oid_cabe),
           a.imp_tota           = a.imp_tota + (SELECT nvl(b.val_tota_gast_admi,
                                                           0)
                                                  FROM fac_docum_conta_cabec b
                                                 WHERE b.oid_cabe = a.dcca_oid_cabe)
     WHERE dcca_oid_cabe IN (SELECT oid_cabe
                               FROM fac_docum_conta_cabec x
                              WHERE x.perd_oid_peri = ln_oidperi
                                AND x.fec_fact = lv_fecfact
                                AND nvl(x.val_tota_gast_admi,
                                        0) <> 0)
       AND a.val_tota_gast_admi IS NULL;

    UPDATE fac_regis_unico_venta a
       SET a.tido_oid_tipo_docu = 29
     WHERE dcca_oid_cabe IN (SELECT oid_cabe
                               FROM fac_docum_conta_cabec a
                              WHERE a.perd_oid_peri = ln_oidperi
                                   --AND a.fec_fact = lv_fecfact
                                AND a.fec_fact = lv_fecfact
                                AND a.tido_oid_tipo_docu = 9);

    BEGIN
      SELECT nvl(MAX(aa.val_ulti_nume_docu_inte),
                 0) + 1
        INTO ln_numinte
        FROM fac_docum_subac aa
       WHERE aa.tido_oid_tipo_docu = 29
         AND aa.sbac_oid_sbac = 888;
    EXCEPTION
      WHEN OTHERS THEN
        ln_numinte := 0;
    END;

    UPDATE fac_docum_conta_cabec a
       SET a.tido_oid_tipo_docu = 29,
           num_docu_cont_inte   = ln_numinte
     WHERE a.perd_oid_peri = ln_oidperi
          --AND a.fec_fact = lv_fecfact
       AND a.fec_fact = lv_fecfact
       AND a.tido_oid_tipo_docu = 9;

    /*UPDATE ped_solic_cabec a
      SET a.modu_oid_modu=23
    WHERE a.perd_oid_peri = ln_oidperi
         --AND case when a.val_tota_gast_admi>0 then a.fec_fact else trunc(to_date(lv_fecfact)) end = lv_fecfact
      AND a.fec_fact = lv_fecfact
      and a.modu_oid_modu<>23
      AND a.tspa_oid_tipo_soli_pais IN
          (SELECT a.oid_tipo_soli_pais --, c.VAL_I18N
             FROM ped_tipo_solic_pais a,
                  ped_tipo_solic      b
            WHERE a.tsol_oid_tipo_soli = b.oid_tipo_soli
              AND b.cod_tipo_soli='C1')
      AND EXISTS (SELECT 1
             FROM ped_solic_cabec
            WHERE soca_oid_soli_cabe = a.oid_soli_cabe
              AND modu_oid_modu=23);*/

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_gastos_admi_sicc: ' || ls_sqlerrm);
  END fac_pr_gastos_admi_sicc;
 /**************************************************************************
  Descripcion        : Altualiza el tipo de Pedido
  Fecha Creacion     : 24/09/2015
  Autor              : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_tipo_pedido
  (
    p_oidcons NUMBER
  )  IS

    ped_serv NUMBER := 0;
    ped_expr NUMBER := 0;
    ped_flex NUMBER := 0;
   ped_recl NUMBER := 0;
   ped_ince NUMBER := 0;
   ped_mav NUMBER := 0;

    lv_indicadorNMP              VARCHAR2(100) := IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','indicadorNMP');

    lstipoPed VARCHAR2(100):='';

  BEGIN
            select count(1)
            into ped_serv
            from ped_solic_cabec a
            where a.oid_soli_cabe=p_oidcons
            and
            (
            exists (select 1 from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe and ind_oc=1)
            or (
                   exists (select 1
                          from ped_solic_cabec x, ped_tipo_solic_pais y, ped_tipo_solic z, gen_i18n_sicc_comun gen
                          where x.tspa_oid_tipo_soli_pais=y.oid_tipo_soli_pais
                          and y.tsol_oid_tipo_soli=z.oid_tipo_soli
                          and z.oid_tipo_soli=gen.val_oid
                          and gen.attr_enti='PED_TIPO_SOLIC'
                          and gen.val_i18n like '%NMP%'
                          and x.soca_oid_soli_cabe=a.oid_soli_cabe
                          )
                   and lv_indicadorNMP='1'
               )
            )
            ;

            select count(1)
            into ped_expr
            from ped_solic_cabec a
            where a.oid_soli_cabe=p_oidcons
            and exists (select 1 from ped_solic_cabec, ped_tipo_solic_pais, ped_tipo_solic, gen_i18n_sicc_comun
            where soca_oid_soli_cabe=a.oid_soli_cabe and tspa_oid_tipo_soli_pais=oid_tipo_soli_pais
            and tsol_oid_tipo_soli=oid_tipo_soli and oid_tipo_soli=val_oid and attr_enti='PED_TIPO_SOLIC'
            and upper(val_i18n) like '%XPRES%'
            );


            select count(1)
            into ped_flex
            from ped_solic_cabec a
            where a.oid_soli_cabe=p_oidcons
            and exists (select 1 from ped_solic_cabec, ped_tipo_solic_pais, ped_tipo_solic, gen_i18n_sicc_comun
            where soca_oid_soli_cabe=a.oid_soli_cabe and tspa_oid_tipo_soli_pais=oid_tipo_soli_pais
            and tsol_oid_tipo_soli=oid_tipo_soli and oid_tipo_soli=val_oid and attr_enti='PED_TIPO_SOLIC'
            and upper(val_i18n) like '%CARGO POR USO%'
            and (select count(1) from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe)=1
            );

            select count(1)
            into ped_recl
            from ped_solic_cabec a
            where a.oid_soli_cabe=p_oidcons
            and exists (select 1 from ped_solic_cabec
            where soca_oid_soli_cabe=a.oid_soli_cabe and modu_oid_modu=15
            and (select count(1) from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe)=1
            );

            select count(1)
            into ped_ince
            from ped_solic_cabec a
            where a.oid_soli_cabe=p_oidcons
            and exists (select 1 from ped_solic_cabec
            where soca_oid_soli_cabe=a.oid_soli_cabe and modu_oid_modu=13
            and (select count(1) from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe)=1
            );

            select count(1)
            into ped_mav
            from ped_solic_cabec a
            where a.oid_soli_cabe=p_oidcons
            and exists (select 1 from ped_solic_cabec
            where soca_oid_soli_cabe=a.oid_soli_cabe and modu_oid_modu=9
            and (select count(1) from ped_solic_cabec where soca_oid_soli_cabe=a.oid_soli_cabe)=1
            );
            
            if ped_serv>0 then
               lstipoPed:='NORMAL';
            else 
                 if ped_expr>0 then
                    lstipoPed:='EXPRESS';
                 else 
                      if ped_flex>0 then
                           lstipoPed:='FLEXIPAGO';
                      else 
                           if ped_recl>0 then 
                                  lstipoPed:='RECLAMO';
                           else 
                                if ped_ince>0 then
                                       lstipoPed:='INCENTIVO';
                                else
                                    if ped_mav>0 then
                                       lstipoPed:='MAV';
                                    end if;
                                end if;
                           end if;
                        end if;
                    end if;
              end if;


    update ped_solic_cabec x set x.val_obse_revi=lstipoPed where oid_soli_cabe=p_oidcons;
  
  EXCEPTION when others then
    NULL;

  END ped_pr_actua_tipo_pedido;

  /***************************************************************************
  Descripcion       : Generacion de Consolidados
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_consolidado
  (
    pnoidperi          NUMBER,
    psfechafacturacion VARCHAR2,
    pscodigopais       VARCHAR2
  ) IS

    --w_filas        NUMBER(12);
    ln_numconso     NUMBER(12);
    lnnumsoliinicio NUMBER(12);
    lsanio          VARCHAR2(10);
    --lnnumlote       NUMBER(10);
    lsnumlote VARCHAR2(15);
  BEGIN

    SELECT to_char(to_date(psfechafacturacion,
                           'dd/mm/yyyy'),
                   'yy')
      INTO lsanio
      FROM dual;

    BEGIN
      SELECT lsanio || lpad(val_ulti_nume_soli,
                            8,
                            '0')
        INTO lsnumlote
        FROM ped_numer_solic a
       WHERE a.val_oper = 'FAC001'
         AND a.val_anio = lsanio
         AND a.cod_pais = pscodigopais;
    EXCEPTION
      WHEN no_data_found THEN
        INSERT INTO ped_numer_solic
        VALUES
          (ped_nuso_seq.nextval,
           '_',
           '_',
           '_',
           pscodigopais,
           'FAC001',
           lsanio,
           1);
        lsnumlote := lsanio || lpad('1',
                                    8,
                                    '0');
    END;

    UPDATE ped_numer_solic a
       SET val_ulti_nume_soli = val_ulti_nume_soli + 1
     WHERE a.val_oper = 'FAC001'
       AND a.val_anio = lsanio
       AND a.cod_pais = pscodigopais;

    SELECT COUNT(1)
      INTO ln_numconso
      FROM (SELECT DISTINCT a.clie_oid_clie,
                            b.tsol_oid_tipo_cons,
                            a.fopa_oid_form_pago,
                            a.zzon_oid_zona,
                            a.ztad_oid_terr_admi,
                            a.cldi_oid_clie_dire,
                            a.terr_oid_terr
              FROM ped_solic_cabec     a,
                   ped_tipo_solic_pais b,
                   ped_tipo_solic      c
             WHERE perd_oid_peri = pnoidperi
               AND grpr_oid_grup_proc = 4
               AND a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
               AND b.tsol_oid_tipo_soli = c.oid_tipo_soli
               AND c.ind_soli_nega = 0
            /*and soca_oid_soli_cabe in
            (
            select a.oid_soli_cabe from ped_solic_cabec a, ped_tipo_solic_pais b, ped_tipo_solic c where perd_oid_peri=2474 and fec_fact='17/02/2012'
            and a.tspa_oid_tipo_soli_pais=b.oid_tipo_soli_pais and b.tsol_oid_tipo_soli=c.oid_tipo_soli and c.ind_soli_nega=0 and a.ind_ts_no_conso=0 and a.tspa_oid_tipo_soli_pais=2001
            )*/
            );

    lnnumsoliinicio := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                              'PED001',
                                                              '000',
                                                              ln_numconso + 1);

    INSERT INTO ped_solic_cabec
      (oid_soli_cabe,
       fec_prog_fact,
       fec_fact,
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
       num_lote_fact)
      SELECT ped_soca_seq.nextval,
             to_date(psfechafacturacion,
                     'dd/mm/yyyy'),
             to_date(psfechafacturacion,
                     'dd/mm/yyyy'),
             0,
             a.tsol_oid_tipo_cons,
             a.mone_oid_mone,
             1,
             a.almc_oid_alma,
             1,
             a.ticl_oid_tipo_clie,
             NULL,
             a.perd_oid_peri,
             a.clie_oid_clie,
             a.clie_oid_clie,
             a.clie_oid_clie,
             a.clie_oid_clie,
             a.cldi_oid_clie_dire,
             (SELECT tdoc_oid_tipo_docu
                FROM mae_clien_ident
               WHERE clie_oid_clie = a.clie_oid_clie
                 AND val_iden_docu_prin = 1
                 AND rownum = 1), --tdoc_oid_tipo_docu
             a.soci_oid_soci,
             a.sbac_oid_sbac,
             a.terr_oid_terr,
             a.zzon_oid_zona,
             to_char(to_date(psfechafacturacion,
                             'dd/mm/yyyy'),
                     'yy') || lpad(to_char(lnnumsoliinicio + rownum),
                                   8,
                                   '0'),
             NULL, --val_usua
             NULL, --val_tasa_impue
             trunc(SYSDATE), --fec_cron
             0,
             1,
             NULL, -- num_docu_orig
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0, -- ind_oc
             0, -- ind_ped_pru
             0, -- ind_no_conso
             '', --glosa
             0,
             0,
             a.pais_oid_pais,
             NULL, --tido_oid_tipo_docu
             a.vepo_oid_valo_estr_geop,
             1,
             5,
             a.sbti_oid_subt_clie,
             NULL, -- acceso fisico
             NULL, --consolidado
             a.fopa_oid_form_pago,
             NULL, --clase sol
             a.ztad_oid_terr_admi,
             43, --operacion
             12, --proceso
             0,
             NULL, --fec_prog_fact_comp
             0,
             0,
             lsnumlote
        FROM (SELECT DISTINCT a.sbti_oid_subt_clie,
                              a.vepo_oid_valo_estr_geop,
                              a.pais_oid_pais,
                              c.sbac_oid_sbac,
                              b.soci_oid_soci,
                              a.perd_oid_peri,
                              a.ticl_oid_tipo_clie,
                              a.almc_oid_alma,
                              a.clie_oid_clie,
                              a.mone_oid_mone,
                              b.tsol_oid_tipo_cons,
                              a.fopa_oid_form_pago,
                              a.zzon_oid_zona,
                              a.ztad_oid_terr_admi,
                              a.cldi_oid_clie_dire,
                              a.terr_oid_terr
                FROM ped_solic_cabec     a,
                     ped_tipo_solic_pais b,
                     ped_tipo_solic      c
               WHERE perd_oid_peri = pnoidperi
                 AND grpr_oid_grup_proc = 4
                 and a.fec_prog_fact>=to_date(psfechafacturacion,
                     'dd/mm/yyyy')
                 AND a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
                 AND b.tsol_oid_tipo_soli = c.oid_tipo_soli
                 AND c.ind_soli_nega = 0
              /*and soca_oid_soli_cabe in
              (
              select a.oid_soli_cabe from ped_solic_cabec a, ped_tipo_solic_pais b, ped_tipo_solic c where perd_oid_peri=2474 and fec_fact='17/02/2012'
              and a.tspa_oid_tipo_soli_pais=b.oid_tipo_soli_pais and b.tsol_oid_tipo_soli=c.oid_tipo_soli and c.ind_soli_nega=0 and a.ind_ts_no_conso=0 and a.tspa_oid_tipo_soli_pais=2001
              )*/
              ) a;

    UPDATE ped_solic_cabec ped
       SET ped.soca_oid_soli_cabe =
           (SELECT MAX(oid_soli_cabe)
              FROM ped_solic_cabec a
             WHERE perd_oid_peri = pnoidperi
               AND grpr_oid_grup_proc = 5
               AND a.tspa_oid_tipo_soli_pais = ped.tspa_oid_tipo_soli_pais_cons
               AND a.clie_oid_clie = ped.clie_oid_clie
               AND a.ztad_oid_terr_admi = ped.ztad_oid_terr_admi
               AND nvl(a.fopa_oid_form_pago,0) = nvl(ped.fopa_oid_form_pago,0)),
           ped.fec_fact           = to_date(psfechafacturacion,
                                            'dd/mm/yyyy'),
           ped.grpr_oid_grup_proc = 5
     WHERE ped.oid_soli_cabe IN (SELECT oid_soli_cabe
                                   FROM ped_solic_cabec     a,
                                        ped_tipo_solic_pais b,
                                        ped_tipo_solic      c
                                  WHERE perd_oid_peri = pnoidperi
                                    AND grpr_oid_grup_proc = 4
                                    and a.fec_prog_fact>=to_date(psfechafacturacion,
                                        'dd/mm/yyyy')
                                    AND a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
                                    AND b.tsol_oid_tipo_soli = c.oid_tipo_soli
                                    AND c.ind_soli_nega = 0);

    DELETE FROM ped_solic_cabec_secue
     WHERE soca_oid_soli_cabe IN (SELECT a.oid_soli_cabe --, c.oid_ruta_tran,0, null, 0,0
                                    FROM ped_solic_cabec a --, zon_zona b, app_rutas_trans c
                                   WHERE a.perd_oid_peri = pnoidperi
                                     AND a.fec_fact = to_date(psfechafacturacion,
                                                              'dd/mm/yyyy')
                                        --AND a.num_unid_aten_tota > 0
                                     AND a.ind_inte_lari_gene = 0
                                     AND a.ind_ts_no_conso = 0
                                     AND a.tspa_oid_tipo_soli_pais IN
                                         (SELECT x.oid_tipo_soli_pais
                                            FROM ped_tipo_solic_pais x,
                                                 ped_tipo_solic      y
                                           WHERE x.tsol_oid_tipo_soli = y.oid_tipo_soli
                                             AND y.ind_cons = 1
                                             AND y.ind_soli_nega = 0));

    INSERT INTO ped_solic_cabec_secue
      SELECT a.oid_soli_cabe,
             c.oid_ruta_tran,
             0,
             NULL,
             0,
             0
        FROM ped_solic_cabec a,
             zon_zona        b,
             app_rutas_trans c
       WHERE a.perd_oid_peri = pnoidperi
         AND a.zzon_oid_zona = b.oid_zona
         AND b.cod_zona = c.cod_ruta
         AND a.fec_fact = to_date(psfechafacturacion,
                                  'dd/mm/yyyy')
            --AND a.num_unid_aten_tota > 0
         AND a.ind_inte_lari_gene = 0
         AND a.ind_ts_no_conso = 0
         AND a.tspa_oid_tipo_soli_pais IN (SELECT x.oid_tipo_soli_pais
                                             FROM ped_tipo_solic_pais x,
                                                  ped_tipo_solic      y
                                            WHERE x.tsol_oid_tipo_soli = y.oid_tipo_soli
                                              AND y.ind_cons = 1
                                              AND y.ind_soli_nega = 0);
                                              
     UPDATE ped_solic_cabec psc
        SET psc.num_lote_fact = nvl(psc.num_lote_fact, lsnumlote)
      WHERE psc.fec_fact = to_date(psfechafacturacion, 'dd/mm/yyyy')
        AND psc.tspa_oid_tipo_soli_pais = fin_pkg_gener.FIN_FN_OBTIE_OID_SOLIC_PAIS('CFI')
        AND psc.grpr_oid_grup_proc = 5;

/*
    INSERT INTO ped_segui_pedid x
      SELECT ped_sepe_seq.nextval,
             a.oid_soli_cabe,
             NULL,
             '1',
             '2',
             a.fec_fact+nvl(ped_fn_obt_dias_fecha_ent(a.oid_soli_cabe),0),
             NULL,
             '1'
        FROM ped_solic_cabec a
       WHERE a.perd_oid_peri = pnoidperi
         AND a.fec_fact = to_date(psfechafacturacion,
                                  'dd/mm/yyyy')
            --AND a.num_unid_aten_tota > 0
         AND a.ind_inte_lari_gene = 0
         AND a.ind_ts_no_conso = 0
         and not exists (select 1 from ped_segui_pedid where soca_oid_soli_cabe=a.oid_soli_cabe)
         AND a.tspa_oid_tipo_soli_pais IN (SELECT x.oid_tipo_soli_pais
                                             FROM ped_tipo_solic_pais x,
                                                  ped_tipo_solic      y
                                            WHERE x.tsol_oid_tipo_soli = y.oid_tipo_soli
                                              AND y.ind_cons = 1
                                              AND y.ind_soli_nega = 0);*/

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_genera_consolidado: ' || ls_sqlerrm);
  END fac_pr_genera_consolidado;

  /***************************************************************************
  Descripcion       : Generacion de Consolidados
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  FUNCTION fac_fn_genera_consolidado
  (
    p_oidsolic         NUMBER,
    psfechafacturacion VARCHAR2,
    pscodigopais       VARCHAR2
  ) RETURN NUMBER IS
    --w_filas        NUMBER(12);
    ln_numconso      NUMBER(12);
    lnnumsoliinicio  NUMBER(12);
    lsanio           VARCHAR2(10);
    lnnumlote        NUMBER(10);
    lsnumlote        VARCHAR2(15);
    lsoidconsolidado NUMBER;
  BEGIN

    SELECT to_char(to_date(psfechafacturacion,
                           'dd/mm/yyyy'),
                   'yy')
      INTO lsanio
      FROM dual;

    BEGIN
      SELECT lsanio || lpad(val_ulti_nume_soli,
                            8,
                            '0')
        INTO lnnumlote
        FROM ped_numer_solic a
       WHERE a.val_oper = 'FAC001'
         AND a.val_anio = lsanio
         AND a.cod_pais = pscodigopais;
    EXCEPTION
      WHEN no_data_found THEN
        INSERT INTO ped_numer_solic
        VALUES
          (ped_nuso_seq.nextval,
           '_',
           '_',
           '_',
           pscodigopais,
           'FAC001',
           lsanio,
           1);
        lnnumlote := lsanio || lpad('1',
                                    8,
                                    '0');
    END;

    UPDATE ped_numer_solic a
       SET val_ulti_nume_soli = val_ulti_nume_soli + 1
     WHERE a.val_oper = 'FAC001'
       AND a.val_anio = lsanio
       AND a.cod_pais = pscodigopais;

    lnnumsoliinicio := sto_pkg_gener.sto_fn_resrv_secue_nsoli(pscodigopais,
                                                              'PED001',
                                                              '000',
                                                              1);
    SELECT ped_soca_seq.nextval INTO lsoidconsolidado FROM dual;

    ----- insert into BAS_TMP_REPOR_LOG (nom_repo,des_log) values(p_oidsolic,'fac_fn_genera_consolidado p_oidsolic'); ----- sqa


    INSERT INTO ped_solic_cabec
      (oid_soli_cabe,
       fec_prog_fact,
       fec_fact,
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
       num_lote_fact,
       soca_oid_docu_refe)
      SELECT lsoidconsolidado, -----ped_soca_seq.nextval,
             to_date(psfechafacturacion,
                     'dd/mm/yyyy'),
             to_date(psfechafacturacion,
                     'dd/mm/yyyy'),
             0,
             a.tsol_oid_tipo_cons,
             a.mone_oid_mone,
             1,
             a.almc_oid_alma,
             1,
             a.ticl_oid_tipo_clie,
             NULL,
             a.perd_oid_peri,
             a.clie_oid_clie,
             a.clie_oid_clie,
             a.clie_oid_clie,
             a.clie_oid_clie,
             a.cldi_oid_clie_dire,
             (SELECT tdoc_oid_tipo_docu
                FROM mae_clien_ident
               WHERE clie_oid_clie = a.clie_oid_clie
                 AND val_iden_docu_prin = 1
                 AND rownum = 1), --tdoc_oid_tipo_docu
             a.soci_oid_soci,
             a.sbac_oid_sbac,
             a.terr_oid_terr,
             a.zzon_oid_zona,
             to_char(to_date(psfechafacturacion,
                             'dd/mm/yyyy'),
                     'yy') || lpad(to_char(lnnumsoliinicio + rownum),
                                   8,
                                   '0'),
             NULL, --val_usua
             NULL, --val_tasa_impue
             trunc(SYSDATE), --fec_cron
             0,
             1,
             NULL, -- num_docu_orig
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0,
             0, -- ind_oc
             0, -- ind_ped_pru
             0, -- ind_no_conso
             '', --glosa
             0,
             0,
             a.pais_oid_pais,
             NULL, --tido_oid_tipo_docu
             a.vepo_oid_valo_estr_geop,
             1,
             5,
             a.sbti_oid_subt_clie,
             NULL, -- acceso fisico
             NULL, --consolidado
             a.fopa_oid_form_pago,
             NULL, --clase sol
             a.ztad_oid_terr_admi,
             43, --operacion
             12, --proceso
             0,
             NULL, --fec_prog_fact_comp
             0,
             0,
             lsnumlote,
             a.soca_oid_docu_refe
        FROM (SELECT DISTINCT a.sbti_oid_subt_clie,
                              a.vepo_oid_valo_estr_geop,
                              a.pais_oid_pais,
                              c.sbac_oid_sbac,
                              b.soci_oid_soci,
                              a.perd_oid_peri,
                              a.ticl_oid_tipo_clie,
                              a.almc_oid_alma,
                              a.clie_oid_clie,
                              a.mone_oid_mone,
                              b.tsol_oid_tipo_cons,
                              a.fopa_oid_form_pago,
                              a.zzon_oid_zona,
                              a.ztad_oid_terr_admi,
                              a.cldi_oid_clie_dire,
                              a.terr_oid_terr,
                              a.soca_oid_docu_refe
                FROM ped_solic_cabec     a,
                     ped_tipo_solic_pais b,
                     ped_tipo_solic      c
               WHERE a.oid_soli_cabe = p_oidsolic
                 AND a.tspa_oid_tipo_soli_pais = b.oid_tipo_soli_pais
                 AND b.tsol_oid_tipo_soli = c.oid_tipo_soli
                 AND c.ind_soli_nega = 1) a;

    ----insert into BAS_TMP_REPOR_LOG (nom_repo,des_log) values(lsoidconsolidado,'fac_fn_genera_consolidado lsoidconsolidado'); ----- sqa

    UPDATE ped_solic_cabec ped
       SET ped.soca_oid_soli_cabe = lsoidconsolidado,
           ped.fec_fact           = to_date(psfechafacturacion,
                                            'dd/mm/yyyy'),
           ped.grpr_oid_grup_proc = 5
     WHERE ped.oid_soli_cabe = p_oidsolic;


    RETURN lsoidconsolidado;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_fn_genera_consolidado: ' || ls_sqlerrm);
  END fac_fn_genera_consolidado;

  /***************************************************************************
  Descripcion       : Calculos Consolidado
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado(p_oidcons NUMBER) IS

    ln_valtasa     NUMBER(6,4);
    ln_valtasa2     NUMBER(2);
    ln_oidtasa     NUMBER(10);
    ln_oidtasa2     NUMBER(10);
    ln_valtasaflet NUMBER(2);
    ln_decim       NUMBER(3);
    --w_filas        NUMBER(12);
    ln_asum       VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'porcentajeIvaAsum'),0);

    ln_imp_estatal NUMBER(12,2):=TO_NUMBER(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'impuestoEstatal'))/100;

    ln_imp_estatal_temp NUMBER(12,2);

    ln_desc_flete VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'descFlete'),'N');

    ln_ajuste    VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'ivaAjuste2'),'N');


  BEGIN


       ped_pr_actua_tipo_pedido(p_oidcons);
 


    SELECT a.num_deci
      INTO ln_decim
      FROM seg_moned a,
           seg_pais  b
     WHERE b.mone_oid_mone = a.oid_mone
       AND b.oid_pais = (SELECT pais_oid_pais FROM ped_solic_cabec WHERE oid_soli_cabe = p_oidcons);


    begin

      SELECT a.val_tasa_impu,
             a.oid_tasa_impu,
             a.val_tasa_impu
        INTO ln_valtasa,
             ln_oidtasa,
             ln_valtasaflet
        FROM ped_tasa_impue  a,
             fac_tipos_impue_ubige c,
             ped_solic_cabec d
       WHERE a.oid_tasa_impu=c.taim_oid_tasa_impu
       and c.vepo_oid_valo_estr_geop=d.zzon_oid_zona
       and d.oid_soli_cabe=p_oidcons
       and IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'impuestoEstatal') is null;

    exception when no_data_found then

    SELECT a.val_tasa_impu,
           a.oid_tasa_impu
      INTO ln_valtasa,
           ln_oidtasa
      FROM ped_tasa_impue  a,
           ped_impue_gener b
     WHERE b.taim_oid_tasa_impu = a.oid_tasa_impu
       AND b.sbac_oid_sbac = 888;

    SELECT a.val_tasa_impu
      INTO ln_valtasaflet
      FROM ped_tasa_impue  a,
           ped_impue_gener b
     WHERE b.taim_oid_tasa_impu_flet = a.oid_tasa_impu
       AND b.sbac_oid_sbac = 888;

    end;

    if ln_valtasa=0 then

      SELECT a.val_tasa_impu,
             a.oid_tasa_impu
        INTO ln_valtasa2,
             ln_oidtasa2
        FROM ped_tasa_impue  a,
             ped_impue_gener b
       WHERE b.taim_oid_tasa_impu = a.oid_tasa_impu
         AND b.sbac_oid_sbac = 888;

    UPDATE ped_solic_posic pos
       SET pos.val_prec_cata_unit_loca = round(nvl(val_prec_cata_unit_loca,
                                                        0)  / (1 + (ln_valtasa2 / 100)),ln_decim)
       , pos.val_prec_cata_unit_docu = round(nvl(val_prec_cata_unit_docu,
                                                        0)  / (1 + (ln_valtasa2 / 100)),ln_decim)
       ,pos.val_prec_cont_unit_loca = round(nvl(val_prec_cont_unit_loca,
                                                        0)  / (1 + (ln_valtasa2 / 100)),ln_decim)
       ,pos.val_prec_conta_unit_docu = round(nvl(val_prec_conta_unit_docu,
                                                        0)  / (1 + (ln_valtasa2 / 100)),ln_decim)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts,
                                         ped_tipo_solic_pais ptsp2,
                                         ped_tipo_solic      pts2
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons
                                     AND ped.tspa_oid_tipo_soli_pais = ptsp2.oid_tipo_soli_pais
                                     AND ptsp2.tsol_oid_tipo_soli = pts2.oid_tipo_soli
                                     and (ped.ind_oc=1 or pts2.oid_tipo_soli in
                                             (
                                                  select distinct TSPA_OID_SOLI_CON_STOC
                                                  from
                                                  (
                                                  select A.TSPA_OID_SOLI_CON_STOC
                                                  from rec_opera a, rec_tipos_opera b
                                                  where b.ROPE_OID_OPER = a.OID_OPER
                                                  and A.TSPA_OID_SOLI_CON_STOC is not null
                                                  and A.IND_DEVU_FISI_FACT = 0
                                                  union
                                                  select A.TSPA_OID_SOLI_CON_STOC
                                                  from rec_opera a, rec_tipos_opera b
                                                  where b.ROPE_OID_OPER = a.OID_OPER
                                                  and A.TSPA_OID_SOLI_CON_STOC is not null
                                                  and B.IND_ENVI_ESTA_FACT = 1
                                                  )
                                             )
                                          )
                                     );


    end if;

    UPDATE ped_solic_posic pos
       SET  pos.val_impo_desc_unit_loca     = nvl(round(nvl(val_prec_cata_unit_loca,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0),
           pos.val_impo_desc_unit_docu     = nvl(round(nvl(val_prec_cata_unit_docu,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);


    UPDATE ped_solic_posic pos
       SET pos.val_prec_conta_unit_docu    = nvl(pos.val_prec_cont_unit_loca,
                                                 0),
           pos.val_prec_cata_unit_docu     = nvl(pos.val_prec_cata_unit_loca,
                                                 0),
           pos.val_prec_sin_impu_unit_loca = round((nvl(val_prec_cata_unit_loca,
                                                        0) + nvl(val_prec_cont_unit_loca,
                                                                  0)) / (1 + (ln_valtasa / 100)),
                                                   ln_decim) * decode((SELECT COUNT(1)
                                                                        FROM ped_solic_cabec     psc,
                                                                             inc_concu_tipo_prog ictp
                                                                       WHERE psc.oid_soli_cabe =
                                                                             pos.soca_oid_soli_cabe
                                                                         AND psc.ictp_oid_tipo_prog =
                                                                             ictp.oid_tipo_prog
                                                                         AND ictp.cod_tipo_prog = 'B'),
                                                                      0,
                                                                      1,
                                                                      0),
           pos.val_prec_sin_impu_unit_docu = round((nvl(val_prec_cata_unit_docu,
                                                        0) + nvl(val_prec_conta_unit_docu,
                                                                  0)) / (1 + (ln_valtasa / 100)),
                                                   ln_decim) * decode((SELECT COUNT(1)
                                                                        FROM ped_solic_cabec     psc,
                                                                             inc_concu_tipo_prog ictp
                                                                       WHERE psc.oid_soli_cabe =
                                                                             pos.soca_oid_soli_cabe
                                                                         AND psc.ictp_oid_tipo_prog =
                                                                             ictp.oid_tipo_prog
                                                                         AND ictp.cod_tipo_prog = 'B'),
                                                                      0,
                                                                      1,
                                                                      0),
           pos.num_unid_aten               = nvl(pos.num_unid_compr,
                                                 0),
           pos.val_tasa_impu               = ln_valtasa,
           pos.taim_oid_tasa_impu          = ln_oidtasa,

           pos.ind_dent_fuer_caja_bols     = (select mp.cod_ind_dent_caja from mae_produ mp where oid_prod=pos.prod_oid_prod),
           pos.val_prec_neto_unit_loca     = nvl(round((nvl(val_prec_cata_unit_loca,
                                                            0) - nvl(val_impo_desc_unit_loca,
                                                                      0)) / (1 + (ln_valtasa / 100)),
                                                       ln_decim),
                                                 0),
           pos.val_prec_neto_unit_docu     = nvl(round((val_prec_cata_unit_docu -
                                                       val_impo_desc_unit_docu) /
                                                       (1 + (ln_valtasa / 100)),
                                                       ln_decim),
                                                 0),
           pos.val_prec_fact_unit_loca    =
           ((nvl(val_prec_cata_unit_loca,
                 0) + nvl(val_prec_cont_unit_loca,
                            0)) - round(nvl(val_prec_cata_unit_loca,
                                             0) * nvl(val_porc_desc,
                                                      0) / 100,
                                         ln_decim)) *
           decode((SELECT COUNT(1)
                    FROM ped_solic_cabec     psc,
                         inc_concu_tipo_prog ictp
                   WHERE psc.oid_soli_cabe = pos.soca_oid_soli_cabe
                     AND psc.ictp_oid_tipo_prog = ictp.oid_tipo_prog
                     AND ictp.cod_tipo_prog = 'B'),
                  0,
                  1,
                  0),
           pos.val_prec_fact_unit_docu    =
           ((nvl(val_prec_cata_unit_docu,
                 0) + nvl(val_prec_cont_unit_loca,
                            0)) - nvl(round(nvl(val_prec_cata_unit_docu,
                                                 0) * nvl(val_porc_desc,
                                                          0) / 100,
                                             ln_decim),
                                       0)) *
           decode((SELECT COUNT(1)
                    FROM ped_solic_cabec     psc,
                         inc_concu_tipo_prog ictp
                   WHERE psc.oid_soli_cabe = pos.soca_oid_soli_cabe
                     AND psc.ictp_oid_tipo_prog = ictp.oid_tipo_prog
                     AND ictp.cod_tipo_prog = 'B'),
                  0,
                  1,
                  0)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);


    UPDATE ped_solic_posic pos
       SET pos.val_impo_impu_unit_loca    =
           (nvl(val_prec_cata_unit_loca,0) - nvl(val_impo_desc_unit_loca,0))
           - round((nvl(val_prec_cata_unit_loca,0) - nvl(val_impo_desc_unit_loca,0))
           / (1 + (ln_valtasa / 100)),
                                      ln_decim),
           pos.val_impo_impu_unit_docu    =
           (nvl(val_prec_cata_unit_docu,0) - nvl(val_impo_desc_unit_docu,0))
           - round((nvl(val_prec_cata_unit_docu,0) - nvl(val_impo_desc_unit_docu,0))
           / (1 + (ln_valtasa / 100)),
                                      ln_decim)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);
/*
    UPDATE ped_solic_posic c
       SET c.val_prec_neto_unit_loca    =
           (decode(c.val_prec_cata_unit_loca,0,0,c.val_prec_sin_impu_unit_loca)-nvl(round(nvl(c.val_impo_desc_unit_loca,0)/ (1 + (ln_valtasa / 100)) ,ln_decim),0)),
           c.val_prec_neto_unit_docu    =
           (decode(c.val_prec_cata_unit_loca,0,0,c.val_prec_sin_impu_unit_loca)-nvl(round(nvl(c.val_impo_desc_unit_loca,0)/ (1 + (ln_valtasa / 100)) ,ln_decim),0))
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);

*/



    UPDATE ped_solic_posic c
       SET c.imp_impu_tota_prod_naci  = (select VAL_IMPU_PROD_NACI from (select x.VAL_IMPU_PROD_NACI, x.prod_oid_prod from INT_IMPUE_PRODU_NACIO x order by x.fec_carg desc) where prod_oid_prod=c.prod_oid_prod and rownum=1)*c.num_unid_aten
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);


    UPDATE ped_solic_posic c
       SET c.imp_iva_impu_tota_prod_naci  = round(c.imp_impu_tota_prod_naci*(ln_valtasa/100),ln_decim)
     WHERE c.val_prec_cata_unit_loca=0
     and soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_posic pos
       SET pos.val_prec_sin_impu_tota_loca = round(nvl(val_prec_sin_impu_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_sin_impu_tota_docu = round(nvl(val_prec_sin_impu_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_desc_tota_loca     = round(nvl(val_impo_desc_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_des_sin_imp_tota     = round(nvl(val_impo_desc_unit_loca,
                                                       0)/ (1 + (ln_valtasa / 100)) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_desc_tota_docu     = round(nvl(val_impo_desc_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_neto_tota_loca     = round(nvl(val_prec_neto_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_neto_tota_docu     = round(nvl(val_prec_neto_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_fact_tota_loca     = round(nvl(val_prec_fact_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_fact_tota_docu     = round(nvl(val_prec_fact_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_impu_tota_loca     = round(nvl(val_impo_impu_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_impu_tota_docu     = round(nvl(val_impo_impu_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           /*pos.val_impo_impu_tota_loca     = (decode (pos.val_prec_cata_unit_loca,0,0,pos.val_prec_sin_impu_tota_loca)-round(pos.val_impo_desc_tota_loca/(1+(ln_valtasa/100)),0))*(ln_valtasa/100)
                                                   ,
           pos.val_impo_impu_tota_docu     = (decode (pos.val_prec_cata_unit_loca,0,0,pos.val_prec_sin_impu_tota_loca)-round(pos.val_impo_desc_tota_loca/(1+(ln_valtasa/100)),0))*(ln_valtasa/100)
                                                   ,*/
           pos.val_prec_cata_tota_loca     = round(nvl(val_prec_cata_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_cata_tota_docu     = round(nvl(val_prec_cata_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_cont_tota_loca     = round(nvl(val_prec_cont_unit_loca,
                                                       0) * num_unid_aten *
                                                   decode((SELECT COUNT(1)
                                                            FROM ped_solic_cabec     psc,
                                                                 inc_concu_tipo_prog ictp
                                                           WHERE psc.oid_soli_cabe =
                                                                 pos.soca_oid_soli_cabe
                                                             AND psc.ictp_oid_tipo_prog =
                                                                 ictp.oid_tipo_prog
                                                             AND ictp.cod_tipo_prog = 'B'),
                                                          0,
                                                          1,
                                                          0),
                                                   2),
           pos.val_prec_cont_tota_docu     = round(nvl(val_prec_conta_unit_docu,
                                                       0) * num_unid_aten *
                                                   decode((SELECT COUNT(1)
                                                            FROM ped_solic_cabec     psc,
                                                                 inc_concu_tipo_prog ictp
                                                           WHERE psc.oid_soli_cabe =
                                                                 pos.soca_oid_soli_cabe
                                                             AND psc.ictp_oid_tipo_prog =
                                                                 ictp.oid_tipo_prog
                                                             AND ictp.cod_tipo_prog = 'B'),
                                                          0,
                                                          1,
                                                          0),
                                                   2),
           pos.val_prec_tota_tota_loca     = round(nvl(val_prec_fact_unit_loca,
                                                       0) * num_unid_aten,
                                                   2),
           pos.ind_no_impr                 = decode((SELECT COUNT(1)
                                                      FROM pre_ofert_detal      x,
                                                           fac_tipo_ofert_exclu y
                                                     WHERE x.tofe_oid_tipo_ofer =
                                                           y.tofe_oid_tipo_ofer
                                                       AND x.oid_deta_ofer = pos.ofde_oid_deta_ofer
                                                       and x.imp_prec_cata=0),
                                                    0,
                                                    0,
                                                    1)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);


      /*
    UPDATE ped_solic_posic pos set
           pos.val_impo_impu_tota_loca     = (decode (pos.val_prec_cata_unit_loca,0,0,pos.val_prec_sin_impu_tota_loca)-round(pos.val_impo_desc_tota_loca/(1+(ln_valtasa/100)),ln_decim))*(ln_valtasa/100)
                                                   ,
           pos.val_impo_impu_tota_docu     = (decode (pos.val_prec_cata_unit_loca,0,0,pos.val_prec_sin_impu_tota_loca)-round(pos.val_impo_desc_tota_loca/(1+(ln_valtasa/100)),ln_decim))*(ln_valtasa/100)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);

*/

    UPDATE ped_solic_cabec ped
       SET ped.val_prec_cata_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0)))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_sin_impu_tota_do =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0)))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0),
                              0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_sin_impu_tota_1 =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0),
                              0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_fact_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_fact_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_tota_loc_uni_dem =
           (SELECT SUM(nvl(b.val_prec_cata_unit_loca,
                           0) * nvl(num_unid_dema,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_neto_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_neto_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_sin_impu_tota =
           (SELECT SUM(round(nvl(b.val_impo_des_sin_imp_tota,0) ,
                             2))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_tota_loca     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_tota_docu     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0) + nvl(b.val_prec_cont_tota_loca,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0) + nvl(b.val_prec_cont_tota_docu,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_impu_tota_loca       =
           round((SELECT SUM(nvl(b.val_impo_impu_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),ln_decim),
           ped.val_impo_impu_tota_docu       =
           round((SELECT SUM(nvl(b.val_impo_impu_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),ln_decim),
           ped.num_unid_aten_tota            =
           (SELECT SUM(b.num_unid_aten)
              FROM ped_solic_posic b,
                   pre_ofert_detal c
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe
               AND b.ofde_oid_deta_ofer = c.oid_deta_ofer(+)
               AND NOT EXISTS (SELECT 1
                      FROM fac_tipo_ofert_exclu
                     WHERE tofe_oid_tipo_ofer = c.tofe_oid_tipo_ofer)),
           ped.num_unid_por_aten_tota        =
           (SELECT SUM(b.num_unid_por_aten)
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.imp_impu_tota_prod_naci        =
           (SELECT SUM(b.imp_impu_tota_prod_naci)
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.imp_iva_impu_tota_prod_naci        =
           (SELECT SUM(b.imp_iva_impu_tota_prod_naci)
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_tasa_impu                  = ln_valtasa,
           ped.val_tasa_flet                  = ln_valtasaflet,
           ped.taim_oid_tasa_impu             = ln_oidtasa,
           ped.val_impo_flet_tota_loca        = ped.val_impo_flet_loca+nvl(ped.val_reca_flet_loca,0),
           ped.val_impo_flet_tota_docu        = ped.val_impo_flet_loca+nvl(ped.val_reca_flet_loca,0)
     WHERE ped.soca_oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_base_flet_loca     =
           (SELECT MAX(nvl(a.val_base_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_base_flet_docu     =
           (SELECT MAX(nvl(a.val_base_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_loca     =
           (SELECT MAX(nvl(a.val_impo_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_flet_loca     =
           (SELECT MAX(nvl(a.val_impo_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_docu     =
           (SELECT MAX(nvl(a.val_impo_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_tota_loca =
           (SELECT MAX(nvl(a.val_impo_flet_tota_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_tota_docu =
           (SELECT MAX(nvl(a.val_impo_flet_tota_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_reca_flet_loca     =
           (SELECT MAX(nvl(a.val_reca_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_reca_flet_docu     =
           (SELECT MAX(nvl(a.val_reca_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi     =
           (SELECT SUM(nvl(a.val_tota_gast_admi,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi2     =
           (SELECT SUM(nvl(a.val_tota_gast_admi2,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_comi_flex     =
           (SELECT SUM(nvl(a.val_tota_comi_flex,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.imp_impu_tota_prod_naci     =
           (SELECT SUM(nvl(a.imp_impu_tota_prod_naci,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.imp_iva_impu_tota_prod_naci     =
           (SELECT SUM(nvl(a.imp_iva_impu_tota_prod_naci,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_3_tota_loca     =
           (SELECT SUM(nvl(a.val_impo_desc_3_tota_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe)
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_prec_cata_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0)))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_sin_impu_tota_do =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0)))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0),
                              0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_sin_impu_tota_1 =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0),
                              0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_fact_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_fact_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_tota_loc_uni_dem =
           (SELECT SUM(nvl(b.val_prec_cata_unit_loca,
                           0) * num_unid_dema)
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_neto_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_neto_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_sin_impu_tota    = round(nvl(cons.val_impo_flet_tota_loca,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_impo_desc_1_sin_impu_tota =
           (SELECT SUM(round(nvl(b.val_impo_desc_tota_loca,
                                 0) / (1 + (ln_valtasa / 100)),
                             ln_decim))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_1_tota_loca     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_1_tota_docu     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0) + nvl(b.val_prec_cont_tota_loca,
                                    0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0) + nvl(b.val_prec_cont_tota_docu,
                                    0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_impu_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe) +
           (cons.val_impo_flet_tota_loca -
           round(cons.val_impo_flet_tota_loca / (1 + (ln_valtasaflet / 100)),
                  ln_decim)) +
           (cons.val_tota_gast_admi - round(cons.val_tota_gast_admi / (1 + (ln_valtasaflet / 100)),
                                            ln_decim))+
           (cons.val_tota_gast_admi2 - round(cons.val_tota_gast_admi2 / (1 + (ln_valtasaflet / 100)),
                                            ln_decim)),
           cons.val_impo_impu_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe) +
           (cons.val_impo_flet_tota_loca -
           round(cons.val_impo_flet_tota_loca / (1 + (ln_valtasaflet / 100)),
                  ln_decim)) +
           (cons.val_tota_gast_admi - round(cons.val_tota_gast_admi / (1 + (ln_valtasaflet / 100)),
                                            ln_decim))+
           (cons.val_tota_gast_admi2 - round(cons.val_tota_gast_admi2 / (1 + (ln_valtasaflet / 100)),
                                            ln_decim)),
           cons.num_unid_aten_tota            =
           (SELECT SUM(b.num_unid_aten)
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.num_unid_por_aten_tota        =
           (SELECT SUM(b.num_unid_por_aten)
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi_sin_impu    = round(nvl(cons.val_tota_gast_admi,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_gast_admi2_sin_impu    = round(nvl(cons.val_tota_gast_admi2,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_impu_gast_admi        = nvl(cons.val_tota_gast_admi,
                                                     0) -
                                                 round(nvl(cons.val_tota_gast_admi,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_impu_gast2_admi        = nvl(cons.val_tota_gast_admi2,
                                                     0) -
                                                 round(nvl(cons.val_tota_gast_admi2,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_comi_flex_sin_impu    = round(nvl(cons.val_tota_comi_flex,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_impo_impu_comi_flex        = nvl(cons.val_tota_comi_flex,
                                                     0) -
                                                 round(nvl(cons.val_tota_comi_flex,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tasa_impu                  = ln_valtasa,
           cons.val_tasa_flet                  = ln_valtasaflet,
           cons.taim_oid_tasa_impu             = ln_oidtasa
     WHERE cons.oid_soli_cabe = p_oidcons;


    if ln_ajuste='S' then

/*--------------------------------------------------------------------------------------------------------------*/
    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca =            (nvl(cons.val_prec_cata_sin_impu_tota,
                0) - nvl(cons.val_impo_desc_1_sin_impu_tota,
                          0) + nvl(cons.val_impo_flet_sin_impu_tota,
                                              0)
                                        + nvl(cons.val_tota_gast_admi_sin_impu,
                                              0)+ nvl(cons.val_tota_gast_admi2_sin_impu,
                                              0)
                                              ) * (ln_valtasa/100)
     WHERE cons.oid_soli_cabe = p_oidcons;
/*--------------------------------------------------------------------------------------------------------------*/
     end if;


    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = nvl(cons.val_prec_cata_tota_loca,
                                         0) + nvl(cons.val_prec_cont_tota_loca,
                                                  0) - nvl(cons.val_impo_desc_tota_loca,
                                                           0) + nvl(cons.val_impo_flet_tota_loca,
                                                                    0) +
                                     nvl(cons.val_tota_gast_admi,
                                         0) + nvl(cons.val_tota_gast_admi2,
                                         0) + nvl(cons.val_tota_comi_flex,
                                                  0)
     WHERE cons.oid_soli_cabe = p_oidcons;



if ln_imp_estatal is not null then


   begin
   select y.val_tasa_impu into ln_imp_estatal_temp
   from fac_tipos_impue_ubige x, ped_tasa_impue y, ped_solic_cabec z
   where x.taim_oid_tasa_impu=y.oid_tasa_impu
   and x.vepo_oid_valo_estr_geop=z.zzon_oid_zona
   and z.oid_soli_cabe=p_oidcons
   ;

   if ln_imp_estatal_temp is not null then
      ln_imp_estatal:=ln_imp_estatal_temp;
   end if;


   exception when others then
        NULL;
   end;



    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = (cons.val_prec_cata_tota_loca+nvl(cons.val_impo_flet_tota_loca,0))*(ln_imp_estatal/100), val_impo_redo_loca=0
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca+cons.val_impo_impu_tota_loca
     WHERE cons.oid_soli_cabe = p_oidcons;

end if;


if ln_desc_flete ='S' then

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_desc_flet = round(cons.val_impo_flet_tota_loca*(ln_valtasa/100),ln_decim)
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = cons.val_impo_impu_tota_loca-cons.val_impo_desc_flet
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca-cons.val_impo_desc_flet
     WHERE cons.oid_soli_cabe = p_oidcons;

end if;


    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca-cons.val_impo_desc_3_tota_loca-nvl(cons.val_impo_desc_4_tota_loca,0)
     WHERE cons.oid_soli_cabe = p_oidcons
     and nvl(cons.val_impo_desc_3_tota_loca,0)+nvl(cons.val_impo_desc_4_tota_loca,0)<>0
     ;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_desc_3_sin_impu_tota = round(nvl(cons.val_impo_desc_3_tota_loca,
                                                           0) / (1 + (ln_valtasa / 100)),
                                                       ln_decim),
           cons.val_impo_desc_4_sin_impu_tota = round(nvl(cons.val_impo_desc_4_tota_loca,
                                                           0) / (1 + (ln_valtasa / 100)),
                                                       ln_decim)       
     WHERE cons.oid_soli_cabe = p_oidcons
     and nvl(cons.val_impo_desc_3_tota_loca,0)+nvl(cons.val_impo_desc_4_tota_loca,0)<>0
     ;


    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = cons.val_impo_impu_tota_loca
       -(cons.val_impo_desc_3_tota_loca-val_impo_desc_3_sin_impu_tota)
       -(nvl(cons.val_impo_desc_4_tota_loca,0)-nvl(val_impo_desc_4_sin_impu_tota,0))
     WHERE cons.oid_soli_cabe = p_oidcons
     and nvl(cons.val_impo_desc_3_tota_loca,0)+nvl(cons.val_impo_desc_4_tota_loca,0)<>0
     ;

/*if ln_asum>0 then
    UPDATE ped_solic_cabec cons
       SET cons.val_impo_iva_asum_empr = ped_fn_gener_iva_asum(p_oidcons, ln_valtasa, ln_decim, ln_asum)
     WHERE cons.oid_soli_cabe = p_oidcons;
end if;*/




    UPDATE ped_solic_cabec cons
       SET cons.val_impo_redo_loca =
           (nvl(cons.val_prec_cata_sin_impu_tota,
                0) - nvl(cons.val_impo_desc_1_sin_impu_tota,
                          0) + nvl(cons.val_impo_impu_tota_loca,
                                    0) + nvl(cons.val_impo_flet_sin_impu_tota,
                                              0)
                                        + nvl(cons.val_tota_gast_admi_sin_impu,
                                              0)+ nvl(cons.val_tota_gast_admi2_sin_impu,
                                              0)
                                              ) - nvl(cons.val_tota_paga_loca,
                                                        0) - nvl(cons.val_impo_desc_3_sin_impu_tota,0),
           cons.val_impo_redo_docu = nvl(cons.val_impo_redo_loca,
                                         0)
     WHERE cons.oid_soli_cabe = p_oidcons;



  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_calculo_consolidado: ' || ls_sqlerrm || ' p_oidcons:' ||
                              p_oidcons);
  END fac_pr_calculo_consolidado;

  /***************************************************************************
  Descripcion       : Calculos Consolidado
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado_r(p_oidcons NUMBER) IS

    ln_valtasa     NUMBER(2);
    ln_oidtasa     NUMBER(10);
    ln_valtasaflet NUMBER(2);
    ln_decim       NUMBER(3);
    --w_filas        NUMBER(12);

    ln_imp_estatal NUMBER(12,2):=TO_NUMBER(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'impuestoEstatal'))/100;

    ln_imp_estatal_temp NUMBER(12,2);

    ln_desc_flete VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'descFlete'),'N');


    ls_ICENC VARCHAR2(2):=IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'ICENC');

  BEGIN



    SELECT a.num_deci
      INTO ln_decim
      FROM seg_moned a,
           seg_pais  b
     WHERE b.mone_oid_mone = a.oid_mone
       AND b.oid_pais = (SELECT pais_oid_pais FROM ped_solic_cabec WHERE oid_soli_cabe = p_oidcons);

    begin

      SELECT a.val_tasa_impu,
             a.oid_tasa_impu,
             a.val_tasa_impu
        INTO ln_valtasa,
             ln_oidtasa,
             ln_valtasaflet
        FROM ped_tasa_impue  a,
             fac_tipos_impue_ubige c,
             ped_solic_cabec d
       WHERE a.oid_tasa_impu=c.taim_oid_tasa_impu
       and c.vepo_oid_valo_estr_geop=d.zzon_oid_zona
       and d.oid_soli_cabe=p_oidcons
       and IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'impuestoEstatal') is null;

    exception when no_data_found then

    SELECT a.val_tasa_impu,
           a.oid_tasa_impu
      INTO ln_valtasa,
           ln_oidtasa
      FROM ped_tasa_impue  a,
           ped_impue_gener b
     WHERE b.taim_oid_tasa_impu = a.oid_tasa_impu
       AND b.sbac_oid_sbac = 888;

    SELECT a.val_tasa_impu
      INTO ln_valtasaflet
      FROM ped_tasa_impue  a,
           ped_impue_gener b
     WHERE b.taim_oid_tasa_impu_flet = a.oid_tasa_impu
       AND b.sbac_oid_sbac = 888;

    end;


    UPDATE ped_solic_posic pos
       SET pos.val_impo_desc_unit_loca     = nvl(round(nvl(val_prec_cata_unit_loca,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0),
           pos.val_impo_desc_unit_docu     = nvl(round(nvl(val_prec_cata_unit_docu,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 1
                                     AND cons.oid_soli_cabe = p_oidcons);


   /* UPDATE ped_solic_posic pos
       SET  pos.val_impo_desc_unit_loca     = nvl(round(nvl(val_prec_cata_unit_loca,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0),
           pos.val_impo_desc_unit_docu     = nvl(round(nvl(val_prec_cata_unit_docu,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);*/



    UPDATE ped_solic_posic pos
       SET pos.val_prec_conta_unit_docu    = nvl(pos.val_prec_cont_unit_loca,
                                                 0),
           pos.val_prec_cata_unit_docu     = nvl(pos.val_prec_cata_unit_loca,
                                                 0),
           pos.val_prec_sin_impu_unit_loca = round((nvl(val_prec_cata_unit_loca,
                                                        0) + nvl(val_prec_cont_unit_loca,
                                                                  0)) / (1 + (ln_valtasa / 100)),
                                                   ln_decim) * decode((SELECT COUNT(1)
                                                                        FROM ped_solic_cabec     psc,
                                                                             inc_concu_tipo_prog ictp
                                                                       WHERE psc.oid_soli_cabe =
                                                                             pos.soca_oid_soli_cabe
                                                                         AND psc.ictp_oid_tipo_prog =
                                                                             ictp.oid_tipo_prog
                                                                         AND ictp.cod_tipo_prog = 'B'),
                                                                      0,
                                                                      1,
                                                                      0),
           pos.val_prec_sin_impu_unit_docu = round((nvl(val_prec_cata_unit_docu,
                                                        0) + nvl(val_prec_conta_unit_docu,
                                                                  0)) / (1 + (ln_valtasa / 100)),
                                                   ln_decim) * decode((SELECT COUNT(1)
                                                                        FROM ped_solic_cabec     psc,
                                                                             inc_concu_tipo_prog ictp
                                                                       WHERE psc.oid_soli_cabe =
                                                                             pos.soca_oid_soli_cabe
                                                                         AND psc.ictp_oid_tipo_prog =
                                                                             ictp.oid_tipo_prog
                                                                         AND ictp.cod_tipo_prog = 'B'),
                                                                      0,
                                                                      1,
                                                                      0),
           pos.num_unid_aten               = nvl(pos.num_unid_compr,
                                                 0),
           pos.val_tasa_impu               = ln_valtasa,
           pos.taim_oid_tasa_impu          = ln_oidtasa,

           pos.ind_dent_fuer_caja_bols     = (select mp.cod_ind_dent_caja from mae_produ mp where oid_prod=pos.prod_oid_prod),

           /*pos.val_impo_desc_unit_loca     = nvl(round(nvl(val_prec_cata_unit_loca,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0),
           pos.val_impo_desc_unit_docu     = nvl(round(nvl(val_prec_cata_unit_docu,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0),*/
           pos.val_prec_neto_unit_loca     = nvl(round((nvl(val_prec_cata_unit_loca,
                                                            0) - nvl(val_impo_desc_unit_loca,
                                                                      0)) / (1 + (ln_valtasa / 100)),
                                                       ln_decim),
                                                 0),
           pos.val_prec_neto_unit_docu     = nvl(round((val_prec_cata_unit_docu -
                                                       val_impo_desc_unit_docu) /
                                                       (1 + (ln_valtasa / 100)),
                                                       ln_decim),
                                                 0),
           pos.val_prec_fact_unit_loca    =
           ((nvl(val_prec_cata_unit_loca,
                 0) + nvl(val_prec_cont_unit_loca,
                            0)) - round(nvl(val_prec_cata_unit_loca,
                                             0) * nvl(val_porc_desc,
                                                      0) / 100,
                                         ln_decim)) *
           decode((SELECT COUNT(1)
                    FROM ped_solic_cabec     psc,
                         inc_concu_tipo_prog ictp
                   WHERE psc.oid_soli_cabe = pos.soca_oid_soli_cabe
                     AND psc.ictp_oid_tipo_prog = ictp.oid_tipo_prog
                     AND ictp.cod_tipo_prog = 'B'),
                  0,
                  1,
                  0),
           pos.val_prec_fact_unit_docu    =
           ((nvl(val_prec_cata_unit_docu,
                 0) + nvl(val_prec_cont_unit_loca,
                            0)) - nvl(round(nvl(val_prec_cata_unit_docu,
                                                 0) * nvl(val_porc_desc,
                                                          0) / 100,
                                             ln_decim),
                                       0)) *
           decode((SELECT COUNT(1)
                    FROM ped_solic_cabec     psc,
                         inc_concu_tipo_prog ictp
                   WHERE psc.oid_soli_cabe = pos.soca_oid_soli_cabe
                     AND psc.ictp_oid_tipo_prog = ictp.oid_tipo_prog
                     AND ictp.cod_tipo_prog = 'B'),
                  0,
                  1,
                  0),

           pos.val_impo_impu_unit_loca    =
           (nvl(val_prec_cata_unit_loca,0) - nvl(val_impo_desc_unit_loca,0))
           - round((nvl(val_prec_cata_unit_loca,0) - nvl(val_impo_desc_unit_loca,0))
           / (1 + (ln_valtasa / 100)),
                                      ln_decim),

           pos.val_impo_impu_unit_docu    =
           (nvl(val_prec_cata_unit_docu,
                0) - nvl(val_impo_desc_unit_docu,
                          0)) - round((nvl(val_prec_cata_unit_docu,
                                           0) - nvl(val_impo_desc_unit_docu,
                                                     0)) / (1 + (ln_valtasa / 100)),
                                      ln_decim)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 1
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_posic pos
       SET pos.val_prec_sin_impu_tota_loca = round(nvl(val_prec_sin_impu_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_sin_impu_tota_docu = round(nvl(val_prec_sin_impu_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_desc_tota_loca     = round(nvl(val_impo_desc_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_des_sin_imp_tota     = round(nvl(val_impo_desc_unit_loca,
                                                       0)/ (1 + (ln_valtasa / 100)) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_desc_tota_docu     = round(nvl(val_impo_desc_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_neto_tota_loca     = round(nvl(val_prec_neto_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_neto_tota_docu     = round(nvl(val_prec_neto_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_fact_tota_loca     = round(nvl(val_prec_fact_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_fact_tota_docu     = round(nvl(val_prec_fact_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_impu_tota_loca     = round(nvl(val_impo_impu_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_impu_tota_docu     = round(nvl(val_impo_impu_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_cata_tota_loca     = round(nvl(val_prec_cata_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_cata_tota_docu     = round(nvl(val_prec_cata_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_cont_tota_loca     = round(nvl(val_prec_cont_unit_loca,
                                                       0) * num_unid_aten *
                                                   decode((SELECT COUNT(1)
                                                            FROM ped_solic_cabec     psc,
                                                                 inc_concu_tipo_prog ictp
                                                           WHERE psc.oid_soli_cabe =
                                                                 pos.soca_oid_soli_cabe
                                                             AND psc.ictp_oid_tipo_prog =
                                                                 ictp.oid_tipo_prog
                                                             AND ictp.cod_tipo_prog = 'B'),
                                                          0,
                                                          1,
                                                          0),
                                                   2),
           pos.val_prec_cont_tota_docu     = round(nvl(val_prec_conta_unit_docu,
                                                       0) * num_unid_aten *
                                                   decode((SELECT COUNT(1)
                                                            FROM ped_solic_cabec     psc,
                                                                 inc_concu_tipo_prog ictp
                                                           WHERE psc.oid_soli_cabe =
                                                                 pos.soca_oid_soli_cabe
                                                             AND psc.ictp_oid_tipo_prog =
                                                                 ictp.oid_tipo_prog
                                                             AND ictp.cod_tipo_prog = 'B'),
                                                          0,
                                                          1,
                                                          0),
                                                   2),
           pos.val_prec_tota_tota_loca     = round(nvl(val_prec_fact_unit_loca,
                                                       0) * num_unid_aten,
                                                   2),
           pos.ind_no_impr                 = decode((SELECT COUNT(1)
                                                      FROM pre_ofert_detal      x,
                                                           fac_tipo_ofert_exclu y
                                                     WHERE x.tofe_oid_tipo_ofer =
                                                           y.tofe_oid_tipo_ofer
                                                       AND x.oid_deta_ofer = pos.ofde_oid_deta_ofer
                                                       and x.imp_prec_cata=0
                                                       ),
                                                    0,
                                                    0,
                                                    1)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 1
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_cabec ped
       SET ped.val_prec_cata_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0)))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_sin_impu_tota_do =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0)))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0),
                              0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_sin_impu_tota_1 =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0),
                              0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_fact_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_fact_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_tota_loc_uni_dem =
           (SELECT SUM(nvl(b.val_prec_cata_unit_loca,
                           0) * nvl(num_unid_dema,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_neto_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_neto_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_sin_impu_tota =
           (SELECT SUM(round(nvl(b.val_impo_des_sin_imp_tota,0) ,
                             2))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_tota_loca     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_tota_docu     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0) + nvl(b.val_prec_cont_tota_loca,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0) + nvl(b.val_prec_cont_tota_docu,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_impu_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_impu_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.num_unid_aten_tota            =
           (SELECT SUM(b.num_unid_aten)
              FROM ped_solic_posic b,
                   pre_ofert_detal c
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe
               AND b.ofde_oid_deta_ofer = c.oid_deta_ofer(+)
               AND NOT EXISTS (SELECT 1
                      FROM fac_tipo_ofert_exclu
                     WHERE tofe_oid_tipo_ofer = c.tofe_oid_tipo_ofer)),
           ped.num_unid_por_aten_tota        =
           (SELECT SUM(b.num_unid_por_aten)
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_tasa_impu                  = ln_valtasa,
           ped.val_tasa_flet                  = ln_valtasaflet,
           ped.taim_oid_tasa_impu             = ln_oidtasa,
           ped.val_impo_flet_tota_loca        = ped.val_impo_flet_loca+nvl(ped.val_reca_flet_loca,0),
           ped.val_impo_flet_tota_docu        = ped.val_impo_flet_loca+nvl(ped.val_reca_flet_loca,0)
     WHERE ped.soca_oid_soli_cabe = p_oidcons;


    if ls_ICENC='1' then
    UPDATE ped_solic_posic c
       SET c.imp_impu_tota_prod_naci  = (select VAL_IMPU_PROD_NACI from (select x.VAL_IMPU_PROD_NACI, x.prod_oid_prod from INT_IMPUE_PRODU_NACIO x order by x.fec_carg desc) where prod_oid_prod=c.prod_oid_prod and rownum=1)*c.num_unid_aten
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     --AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);


    end if;


    UPDATE ped_solic_cabec cons
       SET cons.val_base_flet_loca     =
           (SELECT MAX(nvl(a.val_base_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_base_flet_docu     =
           (SELECT MAX(nvl(a.val_base_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_loca     =
           (SELECT MAX(nvl(a.val_impo_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_flet_loca     =
           (SELECT MAX(nvl(a.val_impo_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_docu     =
           (SELECT MAX(nvl(a.val_impo_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_tota_loca =
           (SELECT MAX(nvl(a.val_impo_flet_tota_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_tota_docu =
           (SELECT MAX(nvl(a.val_impo_flet_tota_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_reca_flet_loca     =
           (SELECT MAX(nvl(a.val_reca_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_reca_flet_docu     =
           (SELECT MAX(nvl(a.val_reca_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi     =
           (SELECT SUM(nvl(a.val_tota_gast_admi,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi2     =
           (SELECT SUM(nvl(a.val_tota_gast_admi2,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_comi_flex     =
           (SELECT SUM(nvl(a.val_tota_comi_flex,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe)
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_prec_cata_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0)))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_sin_impu_tota_do =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0)))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0),
                              0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_sin_impu_tota_1 =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0),
                              0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_fact_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_fact_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_tota_loc_uni_dem =
           (SELECT SUM(nvl(b.val_prec_cata_unit_loca,
                           0) * num_unid_dema)
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_neto_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_neto_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_sin_impu_tota    = round(nvl(cons.val_impo_flet_tota_loca,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_impo_desc_1_sin_impu_tota =
           (SELECT SUM(round(nvl(b.val_impo_desc_tota_loca,
                                 0) / (1 + (ln_valtasa / 100)),
                             ln_decim))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_1_tota_loca     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_1_tota_docu     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0) + nvl(b.val_prec_cont_tota_loca,
                                    0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0) + nvl(b.val_prec_cont_tota_docu,
                                    0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_impu_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe) +
           (cons.val_impo_flet_tota_loca -
           round(cons.val_impo_flet_tota_loca / (1 + (ln_valtasaflet / 100)),
                  2)) +
           (cons.val_tota_gast_admi - round(cons.val_tota_gast_admi / (1 + (ln_valtasaflet / 100)),
                                            2))+
           (cons.val_tota_gast_admi2 - round(cons.val_tota_gast_admi2 / (1 + (ln_valtasaflet / 100)),
                                            2)),
           cons.val_impo_impu_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe) +
           (cons.val_impo_flet_tota_loca -
           round(cons.val_impo_flet_tota_loca / (1 + (ln_valtasaflet / 100)),
                  2)) +
           (cons.val_tota_gast_admi - round(cons.val_tota_gast_admi / (1 + (ln_valtasaflet / 100)),
                                            2))+
           (cons.val_tota_gast_admi2 - round(cons.val_tota_gast_admi2 / (1 + (ln_valtasaflet / 100)),
                                            2)),
           cons.num_unid_aten_tota            =
           (SELECT SUM(b.num_unid_aten)
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.num_unid_por_aten_tota        =
           (SELECT SUM(b.num_unid_por_aten)
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi_sin_impu    = round(nvl(cons.val_tota_gast_admi,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_gast_admi2_sin_impu    = round(nvl(cons.val_tota_gast_admi2,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_impu_gast_admi        = nvl(cons.val_tota_gast_admi,
                                                     0) -
                                                 round(nvl(cons.val_tota_gast_admi,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_impu_gast2_admi        = nvl(cons.val_tota_gast_admi2,
                                                     0) -
                                                 round(nvl(cons.val_tota_gast_admi2,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_comi_flex_sin_impu    = round(nvl(cons.val_tota_comi_flex,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_impo_impu_comi_flex        = nvl(cons.val_tota_comi_flex,
                                                     0) -
                                                 round(nvl(cons.val_tota_comi_flex,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tasa_impu                  = ln_valtasa,
           cons.val_tasa_flet                  = ln_valtasaflet,
           cons.taim_oid_tasa_impu             = ln_oidtasa
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = nvl(cons.val_prec_cata_tota_loca,
                                         0) + nvl(cons.val_prec_cont_tota_loca,
                                                  0) - nvl(cons.val_impo_desc_tota_loca,
                                                           0) + nvl(cons.val_impo_flet_tota_loca,
                                                                    0) +
                                     nvl(cons.val_tota_gast_admi,
                                         0)+
                                     nvl(cons.val_tota_gast_admi2,
                                         0) + nvl(cons.val_tota_comi_flex,
                                                  0)
     WHERE cons.oid_soli_cabe = p_oidcons;


if ln_imp_estatal is not null then

   begin
   select y.val_tasa_impu into ln_imp_estatal_temp
   from fac_tipos_impue_ubige x, ped_tasa_impue y, ped_solic_cabec z
   where x.taim_oid_tasa_impu=y.oid_tasa_impu
   and x.vepo_oid_valo_estr_geop=z.zzon_oid_zona
   and z.oid_soli_cabe=p_oidcons
   ;

   if ln_imp_estatal_temp is not null then
      ln_imp_estatal:=ln_imp_estatal_temp;
   end if;


   exception when others then
        NULL;
   end;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = (cons.val_prec_cata_tota_loca+nvl(cons.val_impo_flet_tota_loca,0))*(ln_imp_estatal/100), val_impo_redo_loca=0
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca+cons.val_impo_impu_tota_loca
     WHERE cons.oid_soli_cabe = p_oidcons;

end if;

if ln_desc_flete ='S' then

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_desc_flet = round(cons.val_impo_flet_tota_loca*(ln_valtasa/100),ln_decim)
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = cons.val_impo_impu_tota_loca-cons.val_impo_desc_flet
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca-cons.val_impo_desc_flet
     WHERE cons.oid_soli_cabe = p_oidcons;

end if;


    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca-cons.val_impo_desc_3_tota_loca
     WHERE cons.oid_soli_cabe = p_oidcons
     and nvl(cons.val_impo_desc_3_tota_loca,0)<>0
     ;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_desc_3_sin_impu_tota = round(nvl(cons.val_impo_desc_3_tota_loca,
                                                           0) / (1 + (ln_valtasa / 100)),
                                                       ln_decim)
     WHERE cons.oid_soli_cabe = p_oidcons
     and nvl(cons.val_impo_desc_3_tota_loca,0)<>0
     ;


    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = cons.val_impo_impu_tota_loca-(cons.val_impo_desc_3_tota_loca-val_impo_desc_3_sin_impu_tota)
     WHERE cons.oid_soli_cabe = p_oidcons
     and nvl(cons.val_impo_desc_3_tota_loca,0)<>0
     ;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_redo_loca =
           (nvl(cons.val_prec_cata_sin_impu_tota,
                0) - nvl(cons.val_impo_desc_1_sin_impu_tota,
                          0) + nvl(cons.val_impo_impu_tota_loca,
                                    0) + nvl(cons.val_impo_flet_sin_impu_tota,
                                              0)+ nvl(cons.val_tota_gast_admi_sin_impu,
                                              0)+ nvl(cons.val_tota_gast_admi2_sin_impu,
                                              0)
                                              ) - nvl(cons.val_tota_paga_loca,
                                                        0) -nvl(cons.val_impo_desc_3_sin_impu_tota,0),
           cons.val_impo_redo_docu = nvl(cons.val_impo_redo_loca,
                                         0)
     WHERE cons.oid_soli_cabe = p_oidcons;

/*    DELETE FROM ped_solic_acumu_impue psci WHERE psci.soca_oid_soli_cabe = p_oidcons;

    INSERT INTO ped_solic_acumu_impue
      (oid_acum,
       taim_oid_tasa_impu,
       soca_oid_soli_cabe,
       val_base_impo_posi_loca,
       val_tota_prec_posi_loca,
       val_tota_desc_cabe_loca,
       val_base_impo_desc_cabe,
       val_base_impo_prec_cont,
       val_base_impo_flet_loca,
       val_tota_base_impo_loca,
       val_tota_impu_loca,
       val_base_impo_posi_docu,
       val_tota_impo_loca,
       val_tota_prec_posi_docu,
       val_tota_desc_cabe_docu,
       val_base_impo_desc_cabe_docu,
       val_base_impo_prec_cont_docu,
       val_base_impo_flet_docu,
       val_tota_base_impo_docu,
       val_tota_impu_docu,
       val_tota_impo_docu)
    VALUES
      (p_oidcons,
       ln_oidtasa,
       p_oidcons,
       nvl((SELECT x.val_prec_neto_tota_loca + x.val_prec_cont_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       0,
       0,
       0,
       nvl((SELECT x.val_prec_cont_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_impo_flet_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_prec_neto_tota_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_impo_impu_tota_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_prec_neto_tota_docu + x.val_prec_cont_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_tota_paga_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       0,
       0,
       0,
       nvl((SELECT x.val_prec_cont_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       0,
       nvl((SELECT x.val_prec_neto_tota_docu FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_impo_impu_tota_docu FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_tota_paga_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0));*/

    --commit;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_calculo_consolidado_r: ' || ls_sqlerrm || ' p_oidcons:' ||
                              p_oidcons);
  END fac_pr_calculo_consolidado_r;

  /***************************************************************************
  Descripcion       : Calculos Consolidado
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado_z
  (
    p_oidzona          NUMBER,
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  ) IS

    CURSOR c_cons
    (
      p_oidperi NUMBER,
      p_fecfact VARCHAR2
    ) IS
      SELECT a.oid_soli_cabe
        FROM ped_solic_cabec a
       WHERE a.perd_oid_peri = p_oidperi
         AND a.fec_fact = to_date(p_fecfact,
                                  'dd/mm/yyyy')
            --AND a.num_unid_aten_tota > 0
         AND a.zzon_oid_zona = p_oidzona
         AND a.ind_inte_lari_gene = 0
         AND a.ind_ts_no_conso = 0
         AND a.tspa_oid_tipo_soli_pais IN (SELECT x.oid_tipo_soli_pais
                                             FROM ped_tipo_solic_pais x,
                                                  ped_tipo_solic      y
                                            WHERE x.tsol_oid_tipo_soli = y.oid_tipo_soli
                                              AND y.ind_cons = 1
                                              AND y.ind_soli_nega = 0);

    r_cons c_cons%ROWTYPE;

    ln_valtasa     NUMBER(2);
    ln_oidtasa     NUMBER(10);
    ln_valtasaflet NUMBER(2);
    --ln_oidtasaflet    NUMBER(10);
    --w_filas        NUMBER(12);

    lv_codpais  VARCHAR2(20);
    lv_tipocalc VARCHAR2(1);
    ln_oidperi  NUMBER;
    --w_filas        NUMBER(12);

  BEGIN

    ln_oidperi := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodperi);

    SELECT z.cod_pais
      INTO lv_codpais
      FROM cra_perio       x,
           seg_perio_corpo y,
           bas_ctrl_fact   z
     WHERE x.peri_oid_peri = y.oid_peri
       AND y.cod_peri = z.cod_peri
       AND z.ind_camp_act = 1
       AND z.sta_camp = 0;

    lv_tipocalc := nvl(sto_pkg_gener.sto_fn_obten_param_ocr(lv_codpais,
                                                            'STO_TIPO_CALC_FACT'),
                       '1');

    OPEN c_cons(ln_oidperi,
                psfechafacturacion);
    LOOP
      FETCH c_cons
        INTO r_cons;
      EXIT WHEN c_cons%NOTFOUND;

      IF lv_tipocalc = '1' THEN
        fac_pr_calculo_consolidado(r_cons.oid_soli_cabe);
      ELSE
            IF lv_tipocalc = '2' then
            fac_pr_calculo_consolidado2(r_cons.oid_soli_cabe);
            ELSE
                fac_pr_calculo_consolidado3(r_cons.oid_soli_cabe);
      END IF;
      END IF;

    END LOOP;
    CLOSE c_cons;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_calculo_consolidado_z: ' || ls_sqlerrm || ' p_oidzona:' ||
                              p_oidzona);
  END fac_pr_calculo_consolidado_z;

  /***************************************************************************
  Descripcion       : Calculos Consolidado
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado_z2
  (
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  ) IS

    CURSOR c_cons
    IS
      SELECT a.oid_zona
        FROM zon_zona a
       WHERE a.ind_acti=1;

    r_cons c_cons%ROWTYPE;

    lv_codpais  VARCHAR2(20);
    ln_oidperi  NUMBER;
    --w_filas        NUMBER(12);

  BEGIN

    ln_oidperi := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodperi);

    SELECT z.cod_pais
      INTO lv_codpais
      FROM cra_perio       x,
           seg_perio_corpo y,
           bas_ctrl_fact   z
     WHERE x.peri_oid_peri = y.oid_peri
       AND y.cod_peri = z.cod_peri
       AND z.ind_camp_act = 1
       AND z.sta_camp = 0;


    OPEN c_cons;
    LOOP
      FETCH c_cons
        INTO r_cons;
      EXIT WHEN c_cons%NOTFOUND;

        fac_pr_calculo_consolidado_z(r_cons.oid_zona, pscodperi, psfechafacturacion) ;

    END LOOP;
    CLOSE c_cons;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_calculo_consolidado_z2: ' || ls_sqlerrm);
  END fac_pr_calculo_consolidado_z2;
  /***************************************************************************
  Descripcion       : Calculos Consolidado
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado2(p_oidcons NUMBER) IS

    ln_valtasa     NUMBER(2);
    ln_oidtasa     NUMBER(10);
    ln_valtasaflet NUMBER(2);
    ln_decim       NUMBER(3);
    --ln_oidtasaflet    NUMBER(10);
    --w_filas        NUMBER(12);

    ln_rete       VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'porcentajeRetencion'),10);

    ln_desc_flete VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'descFlete'),'N');


  BEGIN

       ped_pr_actua_tipo_pedido(p_oidcons);


    SELECT a.num_deci
      INTO ln_decim
      FROM seg_moned a,
           seg_pais  b
     WHERE b.mone_oid_mone = a.oid_mone
       AND b.oid_pais = (SELECT pais_oid_pais FROM ped_solic_cabec WHERE oid_soli_cabe = p_oidcons);

    SELECT a.val_tasa_impu,
           a.oid_tasa_impu
      INTO ln_valtasa,
           ln_oidtasa
      FROM ped_tasa_impue  a,
           ped_impue_gener b
     WHERE b.taim_oid_tasa_impu = a.oid_tasa_impu
       AND b.sbac_oid_sbac = 888;

    SELECT a.val_tasa_impu
      INTO ln_valtasaflet
      FROM ped_tasa_impue  a,
           ped_impue_gener b
     WHERE b.taim_oid_tasa_impu_flet = a.oid_tasa_impu
       AND b.sbac_oid_sbac = 888;

    UPDATE ped_solic_posic pos
       SET pos.val_prec_conta_unit_docu    = nvl(pos.val_prec_cont_unit_loca,
                                                 0),
           pos.val_prec_cata_unit_docu     = nvl(pos.val_prec_cata_unit_loca,
                                                 0),
           pos.val_prec_sin_impu_unit_loca = round(nvl(val_prec_cata_unit_loca,0) / (1 + (ln_valtasa / 100)),
                                                   ln_decim) * decode((SELECT COUNT(1)
                                                                        FROM ped_solic_cabec     psc,
                                                                             inc_concu_tipo_prog ictp
                                                                       WHERE psc.oid_soli_cabe =
                                                                             pos.soca_oid_soli_cabe
                                                                         AND psc.ictp_oid_tipo_prog =
                                                                             ictp.oid_tipo_prog
                                                                         AND ictp.cod_tipo_prog = 'B'),
                                                                      0,
                                                                      1,
                                                                      0),
           pos.val_prec_sin_impu_unit_docu = round(nvl(val_prec_cata_unit_docu,
                                                       0) / (1 + (ln_valtasa / 100)),
                                                   ln_decim) * decode((SELECT COUNT(1)
                                                                        FROM ped_solic_cabec     psc,
                                                                             inc_concu_tipo_prog ictp
                                                                       WHERE psc.oid_soli_cabe =
                                                                             pos.soca_oid_soli_cabe
                                                                         AND psc.ictp_oid_tipo_prog =
                                                                             ictp.oid_tipo_prog
                                                                         AND ictp.cod_tipo_prog = 'B'),
                                                                      0,
                                                                      1,
                                                                      0),
           pos.num_unid_aten               = nvl(pos.num_unid_compr,
                                                 0),
           pos.val_tasa_impu               = ln_valtasa,
           pos.taim_oid_tasa_impu          = ln_oidtasa,
           pos.ind_dent_fuer_caja_bols     = (select mp.cod_ind_dent_caja from mae_produ mp where oid_prod=pos.prod_oid_prod)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_posic pos
       SET pos.val_impo_desc_unit_loca = nvl(round(nvl(val_prec_sin_impu_unit_loca,
                                                       0) * nvl(val_porc_desc,
                                                                0) / 100,
                                                   ln_decim),
                                             0),
           pos.val_impo_desc_unit_docu = nvl(round(nvl(val_prec_sin_impu_unit_docu,
                                                       0) * nvl(val_porc_desc,
                                                                0) / 100,
                                                   ln_decim),
                                             0)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_posic pos
       SET pos.val_prec_neto_unit_loca = nvl(round(nvl(val_prec_sin_impu_unit_loca,
                                                       0) - nvl(val_impo_desc_unit_loca,
                                                                ln_decim)),
                                             0),
           pos.val_prec_neto_unit_docu = nvl(round(nvl(val_prec_sin_impu_unit_docu,
                                                       0) - nvl(val_impo_desc_unit_docu,
                                                                ln_decim)),
                                             0),
           pos.val_prec_fact_unit_loca = nvl(val_prec_cata_unit_loca,
                                             0) - nvl(val_impo_desc_unit_loca,
                                                      0),
           pos.val_prec_fact_unit_docu = nvl(val_prec_cata_unit_docu,
                                             0) - nvl(val_impo_desc_unit_docu,
                                                      0),
           pos.val_impo_impu_unit_loca =
           (nvl(val_prec_cata_unit_loca,
                0) - nvl(val_prec_sin_impu_unit_loca,
                          0)),
           pos.val_impo_impu_unit_docu =
           (nvl(val_prec_cata_unit_docu,
                0) - nvl(val_prec_sin_impu_unit_docu,
                          0))
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_posic pos
       SET pos.val_prec_sin_impu_tota_loca = round(nvl(val_prec_sin_impu_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_sin_impu_tota_docu = round(nvl(val_prec_sin_impu_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_desc_tota_loca     = round(nvl(val_impo_desc_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_des_sin_imp_tota     = round(nvl(val_impo_desc_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_desc_tota_docu     = round(nvl(val_impo_desc_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_neto_tota_loca     = round(nvl(val_prec_neto_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_neto_tota_docu     = round(nvl(val_prec_neto_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_fact_tota_loca     = round(nvl(val_prec_fact_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_fact_tota_docu     = round(nvl(val_prec_fact_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_impu_tota_loca     = round(nvl(val_impo_impu_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_impu_tota_docu     = round(nvl(val_impo_impu_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_cata_tota_loca     = round(nvl(val_prec_cata_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_cata_tota_docu     = round(nvl(val_prec_cata_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_cont_tota_loca     = round(nvl(val_prec_cont_unit_loca,
                                                       0) * num_unid_aten *
                                                   decode((SELECT COUNT(1)
                                                            FROM ped_solic_cabec     psc,
                                                                 inc_concu_tipo_prog ictp
                                                           WHERE psc.oid_soli_cabe =
                                                                 pos.soca_oid_soli_cabe
                                                             AND psc.ictp_oid_tipo_prog =
                                                                 ictp.oid_tipo_prog
                                                             AND ictp.cod_tipo_prog = 'B'),
                                                          0,
                                                          1,
                                                          0),
                                                   2),
           pos.val_prec_cont_tota_docu     = round(nvl(val_prec_conta_unit_docu,
                                                       0) * num_unid_aten *
                                                   decode((SELECT COUNT(1)
                                                            FROM ped_solic_cabec     psc,
                                                                 inc_concu_tipo_prog ictp
                                                           WHERE psc.oid_soli_cabe =
                                                                 pos.soca_oid_soli_cabe
                                                             AND psc.ictp_oid_tipo_prog =
                                                                 ictp.oid_tipo_prog
                                                             AND ictp.cod_tipo_prog = 'B'),
                                                          0,
                                                          1,
                                                          0),
                                                   ln_decim),
           pos.val_prec_tota_tota_loca     = round(nvl(val_prec_fact_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.ind_no_impr                 = decode((SELECT COUNT(1)
                                                      FROM pre_ofert_detal      x,
                                                           fac_tipo_ofert_exclu y
                                                     WHERE x.tofe_oid_tipo_ofer =
                                                           y.tofe_oid_tipo_ofer
                                                       AND x.oid_deta_ofer = pos.ofde_oid_deta_ofer
                                                       and x.imp_prec_cata=0),
                                                    0,
                                                    0,
                                                    1)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_cabec ped
       SET ped.val_prec_cata_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0)))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_sin_impu_tota_do =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0)))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0),
                              0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_sin_impu_tota_1 =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0),
                              0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_fact_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_fact_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_tota_loc_uni_dem =
           (SELECT SUM(nvl(b.val_prec_cata_unit_loca,
                           0) * nvl(num_unid_dema,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_neto_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_neto_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_sin_impu_tota =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_tota_loca     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_tota_docu     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0) + nvl(b.val_prec_cont_tota_loca,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0) + nvl(b.val_prec_cont_tota_docu,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_impu_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_impu_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.num_unid_aten_tota            =
           (SELECT SUM(b.num_unid_aten)
              FROM ped_solic_posic b,
                   pre_ofert_detal c
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe
               AND b.ofde_oid_deta_ofer = c.oid_deta_ofer(+)
               AND NOT EXISTS (SELECT 1
                      FROM fac_tipo_ofert_exclu
                     WHERE tofe_oid_tipo_ofer = c.tofe_oid_tipo_ofer)),
           ped.num_unid_por_aten_tota        =
           (SELECT SUM(b.num_unid_por_aten)
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_tasa_impu                  = ln_valtasa,
           ped.val_tasa_flet                  = ln_valtasaflet,
           ped.taim_oid_tasa_impu             = ln_oidtasa,
           ped.val_impo_flet_tota_loca        = ped.val_impo_flet_loca+nvl(ped.val_reca_flet_loca,0),
           ped.val_impo_flet_tota_docu        = ped.val_impo_flet_loca+nvl(ped.val_reca_flet_loca,0)
     WHERE ped.soca_oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_base_flet_loca     =
           (SELECT MAX(nvl(a.val_base_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_base_flet_docu     =
           (SELECT MAX(nvl(a.val_base_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_loca     =
           (SELECT MAX(nvl(a.val_impo_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_flet_loca     =
           (SELECT MAX(nvl(a.val_impo_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_docu     =
           (SELECT MAX(nvl(a.val_impo_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_tota_loca =
           (SELECT MAX(nvl(a.val_impo_flet_tota_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_tota_docu =
           (SELECT MAX(nvl(a.val_impo_flet_tota_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_reca_flet_loca     =
           (SELECT MAX(nvl(a.val_reca_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_reca_flet_docu     =
           (SELECT MAX(nvl(a.val_reca_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi     =
           (SELECT SUM(nvl(a.val_tota_gast_admi,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi2     =
           (SELECT SUM(nvl(a.val_tota_gast_admi2,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_comi_flex     =
           (SELECT SUM(nvl(a.val_tota_comi_flex,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe)
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_prec_cata_tota_loca =(SELECT SUM(nvl(b.val_prec_cata_tota_loca,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_tota_docu =(SELECT SUM(nvl(b.val_prec_cata_tota_docu,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_sin_impu_tota =(SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,0),0,0,nvl(b.val_prec_sin_impu_tota_loca,0))) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_sin_impu_tota_do =(SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,0),0,0,nvl(b.val_prec_sin_impu_tota_docu,0))) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_sin_impu_tota   = (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,0),0,nvl(b.val_prec_sin_impu_tota_loca,0),0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_sin_impu_tota_1 =  (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,0),0,nvl(b.val_prec_sin_impu_tota_docu,0),0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_fact_tota_loca       = (SELECT SUM(nvl(b.val_prec_fact_tota_loca,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_fact_tota_docu       = (SELECT SUM(nvl(b.val_prec_fact_tota_docu,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_tota_loca       = (SELECT SUM(nvl(b.val_prec_cont_tota_loca,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_tota_docu       = (SELECT SUM(nvl(b.val_prec_cont_tota_docu,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_tota_loc_uni_dem = (SELECT SUM(nvl(b.val_prec_cata_unit_loca,0) * num_unid_dema) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_neto_tota_loca       = (SELECT SUM(nvl(b.val_prec_neto_tota_loca,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_neto_tota_docu       = (SELECT SUM(nvl(b.val_prec_neto_tota_docu,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_sin_impu_tota    = round(nvl(cons.val_impo_flet_tota_loca,0) / (1 + (ln_valtasaflet / 100)),ln_decim),
           cons.val_impo_desc_1_sin_impu_tota = (SELECT SUM(round(nvl(b.val_impo_desc_tota_loca,0),ln_decim)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_1_tota_loca     = (SELECT SUM(nvl(b.val_impo_desc_tota_loca,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_1_tota_docu     = (SELECT SUM(nvl(b.val_impo_desc_tota_docu,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_tota_loca       = (SELECT SUM(nvl(b.val_impo_desc_tota_loca,0) + nvl(b.val_prec_cont_tota_loca,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_tota_docu       = (SELECT SUM(nvl(b.val_impo_desc_tota_docu,0) + nvl(b.val_prec_cont_tota_docu,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_impu_tota_loca       = (SELECT SUM(nvl(b.val_impo_impu_tota_loca,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe) + (cons.val_impo_flet_tota_loca - round(cons.val_impo_flet_tota_loca / (1 + (ln_valtasaflet / 100)),ln_decim)) +(cons.val_tota_gast_admi - round(cons.val_tota_gast_admi / (1 + (ln_valtasaflet / 100)),ln_decim))+(cons.val_tota_gast_admi2 - round(cons.val_tota_gast_admi2 / (1 + (ln_valtasaflet / 100)),ln_decim)),
           cons.val_impo_impu_tota_docu       = (SELECT SUM(nvl(b.val_impo_impu_tota_docu,0)) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe) + (cons.val_impo_flet_tota_loca - round(cons.val_impo_flet_tota_loca / (1 + (ln_valtasaflet / 100)),ln_decim)) +(cons.val_tota_gast_admi - round(cons.val_tota_gast_admi / (1 + (ln_valtasaflet / 100)),ln_decim))+(cons.val_tota_gast_admi2 - round(cons.val_tota_gast_admi2 / (1 + (ln_valtasaflet / 100)),ln_decim)),
           cons.num_unid_aten_tota            = (SELECT SUM(b.num_unid_aten) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.num_unid_por_aten_tota        = (SELECT SUM(b.num_unid_por_aten) FROM ped_solic_cabec a,ped_solic_posic b WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi_sin_impu    = round(nvl(cons.val_tota_gast_admi,0) / (1 + (ln_valtasaflet / 100)),ln_decim),
           cons.val_tota_impu_gast_admi        = nvl(cons.val_tota_gast_admi,0) -round(nvl(cons.val_tota_gast_admi,0) / (1 + (ln_valtasaflet / 100)),ln_decim),
           cons.val_tota_gast_admi2_sin_impu    = round(nvl(cons.val_tota_gast_admi2,0) / (1 + (ln_valtasaflet / 100)),ln_decim),
           cons.val_tota_impu_gast2_admi        = nvl(cons.val_tota_gast_admi2,0) -round(nvl(cons.val_tota_gast_admi2,0) / (1 + (ln_valtasaflet / 100)),ln_decim),
           cons.val_tota_comi_flex_sin_impu    = round(nvl(cons.val_tota_comi_flex,0) / (1 + (ln_valtasaflet / 100)),ln_decim),
           cons.val_impo_impu_comi_flex        = nvl(cons.val_tota_comi_flex,0) -round(nvl(cons.val_tota_comi_flex,0) / (1 + (ln_valtasaflet / 100)),ln_decim),
           cons.val_tasa_impu                  = ln_valtasa,
           cons.val_tasa_flet                  = ln_valtasaflet,
           cons.taim_oid_tasa_impu             = ln_oidtasa
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_rete_desc = round(cons.val_impo_desc_1_tota_loca * (ln_rete/100),
                                           ln_decim)
     WHERE cons.oid_soli_cabe = p_oidcons;




    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = nvl(cons.val_prec_cata_tota_loca,
                                         0) + nvl(cons.val_prec_cont_tota_loca,
                                                  0) - nvl(cons.val_impo_desc_tota_loca,
                                                           0) + nvl(cons.val_impo_flet_tota_loca,
                                                                    0) +
                                     nvl(cons.val_tota_gast_admi,
                                         0) + nvl(cons.val_tota_gast_admi2,
                                         0) + nvl(cons.val_tota_comi_flex,
                                                  0) + cons.val_impo_rete_desc
     WHERE cons.oid_soli_cabe = p_oidcons;

if ln_desc_flete ='S' then

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_desc_flet = round(cons.val_impo_flet_tota_loca*(ln_valtasa/100),ln_decim)
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = cons.val_impo_impu_tota_loca-cons.val_impo_desc_flet
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca-cons.val_impo_desc_flet
     WHERE cons.oid_soli_cabe = p_oidcons;

end if;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_redo_loca =
           (nvl(cons.val_prec_cata_sin_impu_tota,
                0) - nvl(cons.val_impo_desc_1_sin_impu_tota,
                          0) + nvl(cons.val_impo_impu_tota_loca,
                                    0) + nvl(cons.val_impo_flet_sin_impu_tota,
                                              0)
                                       + nvl(cons.val_tota_gast_admi_sin_impu,
                                              0)
                                       + nvl(cons.val_tota_gast_admi2_sin_impu,
                                              0)
                                              ) - nvl(cons.val_tota_paga_loca,
                                                        0) + cons.val_impo_rete_desc,
           cons.val_impo_redo_docu = nvl(cons.val_impo_redo_loca,
                                         0)
     WHERE cons.oid_soli_cabe = p_oidcons;

    /*DELETE FROM ped_solic_acumu_impue psci WHERE psci.soca_oid_soli_cabe = p_oidcons;

    INSERT INTO ped_solic_acumu_impue
      (oid_acum,
       taim_oid_tasa_impu,
       soca_oid_soli_cabe,
       val_base_impo_posi_loca,
       val_tota_prec_posi_loca,
       val_tota_desc_cabe_loca,
       val_base_impo_desc_cabe,
       val_base_impo_prec_cont,
       val_base_impo_flet_loca,
       val_tota_base_impo_loca,
       val_tota_impu_loca,
       val_base_impo_posi_docu,
       val_tota_impo_loca,
       val_tota_prec_posi_docu,
       val_tota_desc_cabe_docu,
       val_base_impo_desc_cabe_docu,
       val_base_impo_prec_cont_docu,
       val_base_impo_flet_docu,
       val_tota_base_impo_docu,
       val_tota_impu_docu,
       val_tota_impo_docu)
    VALUES
      (p_oidcons,
       ln_oidtasa,
       p_oidcons,
       (SELECT x.val_prec_neto_tota_loca + x.val_prec_cont_sin_impu_tota
          FROM ped_solic_cabec x
         WHERE oid_soli_cabe = p_oidcons),
       0,
       0,
       0,
       (SELECT x.val_prec_cont_sin_impu_tota FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
       (SELECT x.val_impo_flet_sin_impu_tota FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
       (SELECT x.val_prec_neto_tota_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
       (SELECT x.val_impo_impu_tota_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
       (SELECT x.val_prec_neto_tota_docu + x.val_prec_cont_sin_impu_tota
          FROM ped_solic_cabec x
         WHERE oid_soli_cabe = p_oidcons),
       (SELECT x.val_tota_paga_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
       0,
       0,
       0,
       (SELECT x.val_prec_cont_sin_impu_tota FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
       0,
       (SELECT x.val_prec_neto_tota_docu FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
       (SELECT x.val_impo_impu_tota_docu FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
       (SELECT x.val_tota_paga_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons));*/

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_calculo_consolidado2: ' || ls_sqlerrm || ' p_oidcons:' ||
                              p_oidcons);
  END fac_pr_calculo_consolidado2;
  /***************************************************************************
  Descripcion       : Calculos Consolidado
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_calculo_consolidado3(p_oidcons NUMBER) IS

    ln_valtasa     NUMBER(2);
    ln_valtasa2     NUMBER(2);
    ln_oidtasa     NUMBER(10);
    ln_oidtasa2     NUMBER(10);
    ln_valtasaflet NUMBER(2);
    ln_decim       NUMBER(3);
    --w_filas        NUMBER(12);
    ln_asum       VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'porcentajeIvaAsum'),0);

    ln_imp_estatal VARCHAR2(2):=IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'impuestoEstatal');

    ln_imp_estatal_temp VARCHAR2(2);

    ln_desc_flete VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'descFlete'),'N');

  BEGIN

       ped_pr_actua_tipo_pedido(p_oidcons);


    SELECT a.num_deci
      INTO ln_decim
      FROM seg_moned a,
           seg_pais  b
     WHERE b.mone_oid_mone = a.oid_mone
       AND b.oid_pais = (SELECT pais_oid_pais FROM ped_solic_cabec WHERE oid_soli_cabe = p_oidcons);


    begin

      SELECT a.val_tasa_impu,
             a.oid_tasa_impu,
             a.val_tasa_impu
        INTO ln_valtasa,
             ln_oidtasa,
             ln_valtasaflet
        FROM ped_tasa_impue  a,
             fac_tipos_impue_ubige c,
             ped_solic_cabec d
       WHERE a.oid_tasa_impu=c.taim_oid_tasa_impu
       and c.vepo_oid_valo_estr_geop=d.zzon_oid_zona
       and d.oid_soli_cabe=p_oidcons
       and IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'impuestoEstatal') is null;

    exception when no_data_found then

    SELECT a.val_tasa_impu,
           a.oid_tasa_impu
      INTO ln_valtasa,
           ln_oidtasa
      FROM ped_tasa_impue  a,
           ped_impue_gener b
     WHERE b.taim_oid_tasa_impu = a.oid_tasa_impu
       AND b.sbac_oid_sbac = 888;

    SELECT a.val_tasa_impu
      INTO ln_valtasaflet
      FROM ped_tasa_impue  a,
           ped_impue_gener b
     WHERE b.taim_oid_tasa_impu_flet = a.oid_tasa_impu
       AND b.sbac_oid_sbac = 888;

    end;

    if ln_valtasa=0 then

      SELECT a.val_tasa_impu,
             a.oid_tasa_impu
        INTO ln_valtasa2,
             ln_oidtasa2
        FROM ped_tasa_impue  a,
             ped_impue_gener b
       WHERE b.taim_oid_tasa_impu = a.oid_tasa_impu
         AND b.sbac_oid_sbac = 888;

    UPDATE ped_solic_posic pos
       SET pos.val_prec_cata_unit_loca = round(nvl(val_prec_cata_unit_loca,
                                                        0)  / (1 + (ln_valtasa2 / 100)),ln_decim)
       , pos.val_prec_cata_unit_docu = round(nvl(val_prec_cata_unit_docu,
                                                        0)  / (1 + (ln_valtasa2 / 100)),ln_decim)
       ,pos.val_prec_cont_unit_loca = round(nvl(val_prec_cont_unit_loca,
                                                        0)  / (1 + (ln_valtasa2 / 100)),ln_decim)
       ,pos.val_prec_conta_unit_docu = round(nvl(val_prec_conta_unit_docu,
                                                        0)  / (1 + (ln_valtasa2 / 100)),ln_decim)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts,
                                         ped_tipo_solic_pais ptsp2,
                                         ped_tipo_solic      pts2
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons
                                     AND ped.tspa_oid_tipo_soli_pais = ptsp2.oid_tipo_soli_pais
                                     AND ptsp2.tsol_oid_tipo_soli = pts2.oid_tipo_soli
                                     and (ped.ind_oc=1 or pts2.oid_tipo_soli in
                                             (
                                                  select distinct TSPA_OID_SOLI_CON_STOC
                                                  from
                                                  (
                                                  select A.TSPA_OID_SOLI_CON_STOC
                                                  from rec_opera a, rec_tipos_opera b
                                                  where b.ROPE_OID_OPER = a.OID_OPER
                                                  and A.TSPA_OID_SOLI_CON_STOC is not null
                                                  and A.IND_DEVU_FISI_FACT = 0
                                                  union
                                                  select A.TSPA_OID_SOLI_CON_STOC
                                                  from rec_opera a, rec_tipos_opera b
                                                  where b.ROPE_OID_OPER = a.OID_OPER
                                                  and A.TSPA_OID_SOLI_CON_STOC is not null
                                                  and B.IND_ENVI_ESTA_FACT = 1
                                                  )
                                             )
                                          )
                                     );


    end if;



    UPDATE ped_solic_posic pos
       SET pos.val_prec_conta_unit_docu    = nvl(pos.val_prec_cont_unit_loca,
                                                 0),
           pos.val_prec_cata_unit_docu     = nvl(pos.val_prec_cata_unit_loca,
                                                 0),
           pos.val_prec_sin_impu_unit_loca = round((nvl(val_prec_cata_unit_loca,
                                                        0) + nvl(val_prec_cont_unit_loca,
                                                                  0)) / (1 + (ln_valtasa / 100)),
                                                   ln_decim) * decode((SELECT COUNT(1)
                                                                        FROM ped_solic_cabec     psc,
                                                                             inc_concu_tipo_prog ictp
                                                                       WHERE psc.oid_soli_cabe =
                                                                             pos.soca_oid_soli_cabe
                                                                         AND psc.ictp_oid_tipo_prog =
                                                                             ictp.oid_tipo_prog
                                                                         AND ictp.cod_tipo_prog = 'B'),
                                                                      0,
                                                                      1,
                                                                      0),
           pos.val_prec_sin_impu_unit_docu = round((nvl(val_prec_cata_unit_docu,
                                                        0) + nvl(val_prec_conta_unit_docu,
                                                                  0)) / (1 + (ln_valtasa / 100)),
                                                   ln_decim) * decode((SELECT COUNT(1)
                                                                        FROM ped_solic_cabec     psc,
                                                                             inc_concu_tipo_prog ictp
                                                                       WHERE psc.oid_soli_cabe =
                                                                             pos.soca_oid_soli_cabe
                                                                         AND psc.ictp_oid_tipo_prog =
                                                                             ictp.oid_tipo_prog
                                                                         AND ictp.cod_tipo_prog = 'B'),
                                                                      0,
                                                                      1,
                                                                      0),
           pos.num_unid_aten               = nvl(pos.num_unid_compr,
                                                 0),
           pos.val_tasa_impu               = ln_valtasa,
           pos.taim_oid_tasa_impu          = ln_oidtasa,

           pos.ind_dent_fuer_caja_bols     = (select mp.cod_ind_dent_caja from mae_produ mp where oid_prod=pos.prod_oid_prod)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_posic pos
       SET  pos.val_impo_desc_unit_loca     = nvl(round(nvl(val_prec_sin_impu_unit_loca,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0),
           pos.val_impo_desc_unit_docu     = nvl(round(nvl(val_prec_sin_impu_unit_loca,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);


    UPDATE ped_solic_posic pos
       SET pos.val_prec_neto_unit_loca     = nvl(round((nvl(val_prec_cata_unit_loca,
                                                            0) - nvl(val_impo_desc_unit_loca,
                                                                      0)) / (1 + (ln_valtasa / 100)),
                                                       ln_decim),
                                                 0),
           pos.val_prec_neto_unit_docu     = nvl(round((val_prec_cata_unit_docu -
                                                       val_impo_desc_unit_docu) /
                                                       (1 + (ln_valtasa / 100)),
                                                       ln_decim),
                                                 0),
           pos.val_prec_fact_unit_loca    =
           ((nvl(val_prec_cata_unit_loca,
                 0) + nvl(val_prec_cont_unit_loca,
                            0)) - nvl(val_impo_desc_unit_loca,0)) *
           decode((SELECT COUNT(1)
                    FROM ped_solic_cabec     psc,
                         inc_concu_tipo_prog ictp
                   WHERE psc.oid_soli_cabe = pos.soca_oid_soli_cabe
                     AND psc.ictp_oid_tipo_prog = ictp.oid_tipo_prog
                     AND ictp.cod_tipo_prog = 'B'),
                  0,
                  1,
                  0),
           pos.val_prec_fact_unit_docu    =
           ((nvl(val_prec_cata_unit_docu,
                 0) + nvl(val_prec_cont_unit_loca,
                            0)) - nvl(val_impo_desc_unit_loca,0)) *
           decode((SELECT COUNT(1)
                    FROM ped_solic_cabec     psc,
                         inc_concu_tipo_prog ictp
                   WHERE psc.oid_soli_cabe = pos.soca_oid_soli_cabe
                     AND psc.ictp_oid_tipo_prog = ictp.oid_tipo_prog
                     AND ictp.cod_tipo_prog = 'B'),
                  0,
                  1,
                  0)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_posic pos
       SET pos.val_impo_impu_unit_loca    =
           round((nvl(val_prec_sin_impu_unit_loca,0) - nvl(val_impo_desc_unit_loca/1.16,0))
           *(ln_valtasa / 100),
                                      ln_decim),
           pos.val_impo_impu_unit_docu    =
           round((nvl(val_prec_sin_impu_unit_loca,0) - nvl(val_impo_desc_unit_loca/1.16,0))
           *(ln_valtasa / 100),
                                      ln_decim)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);
                                     






    UPDATE ped_solic_posic pos
       SET pos.val_prec_sin_impu_tota_loca = round(nvl(val_prec_sin_impu_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_sin_impu_tota_docu = round(nvl(val_prec_sin_impu_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_desc_tota_loca     = round(nvl(val_impo_desc_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_des_sin_imp_tota     = round(nvl(val_impo_desc_unit_loca,
                                                       0)/ (1 + (ln_valtasa / 100)) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_desc_tota_docu     = round(nvl(val_impo_desc_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_neto_tota_loca     = round(nvl(val_prec_neto_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_neto_tota_docu     = round(nvl(val_prec_neto_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_fact_tota_loca     = round(nvl(val_prec_fact_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_fact_tota_docu     = round(nvl(val_prec_fact_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_impu_tota_loca     = round(nvl(val_impo_impu_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_impu_tota_docu     = round(nvl(val_impo_impu_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           /*pos.val_impo_impu_tota_loca     = (decode (pos.val_prec_cata_unit_loca,0,0,pos.val_prec_sin_impu_tota_loca)-round(pos.val_impo_desc_tota_loca/(1+(ln_valtasa/100)),0))*(ln_valtasa/100)
                                                   ,
           pos.val_impo_impu_tota_docu     = (decode (pos.val_prec_cata_unit_loca,0,0,pos.val_prec_sin_impu_tota_loca)-round(pos.val_impo_desc_tota_loca/(1+(ln_valtasa/100)),0))*(ln_valtasa/100)
                                                   ,*/
           pos.val_prec_cata_tota_loca     = round(nvl(val_prec_cata_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_cata_tota_docu     = round(nvl(val_prec_cata_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_cont_tota_loca     = round(nvl(val_prec_cont_unit_loca,
                                                       0) * num_unid_aten *
                                                   decode((SELECT COUNT(1)
                                                            FROM ped_solic_cabec     psc,
                                                                 inc_concu_tipo_prog ictp
                                                           WHERE psc.oid_soli_cabe =
                                                                 pos.soca_oid_soli_cabe
                                                             AND psc.ictp_oid_tipo_prog =
                                                                 ictp.oid_tipo_prog
                                                             AND ictp.cod_tipo_prog = 'B'),
                                                          0,
                                                          1,
                                                          0),
                                                   2),
           pos.val_prec_cont_tota_docu     = round(nvl(val_prec_conta_unit_docu,
                                                       0) * num_unid_aten *
                                                   decode((SELECT COUNT(1)
                                                            FROM ped_solic_cabec     psc,
                                                                 inc_concu_tipo_prog ictp
                                                           WHERE psc.oid_soli_cabe =
                                                                 pos.soca_oid_soli_cabe
                                                             AND psc.ictp_oid_tipo_prog =
                                                                 ictp.oid_tipo_prog
                                                             AND ictp.cod_tipo_prog = 'B'),
                                                          0,
                                                          1,
                                                          0),
                                                   2),
           pos.val_prec_tota_tota_loca     = round(nvl(val_prec_fact_unit_loca,
                                                       0) * num_unid_aten,
                                                   2),
           pos.ind_no_impr                 = decode((SELECT COUNT(1)
                                                      FROM pre_ofert_detal      x,
                                                           fac_tipo_ofert_exclu y
                                                     WHERE x.tofe_oid_tipo_ofer =
                                                           y.tofe_oid_tipo_ofer
                                                       AND x.oid_deta_ofer = pos.ofde_oid_deta_ofer
                                                       and x.imp_prec_cata=0),
                                                    0,
                                                    0,
                                                    1)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);


      /*
    UPDATE ped_solic_posic pos set
           pos.val_impo_impu_tota_loca     = (decode (pos.val_prec_cata_unit_loca,0,0,pos.val_prec_sin_impu_tota_loca)-round(pos.val_impo_desc_tota_loca/(1+(ln_valtasa/100)),ln_decim))*(ln_valtasa/100)
                                                   ,
           pos.val_impo_impu_tota_docu     = (decode (pos.val_prec_cata_unit_loca,0,0,pos.val_prec_sin_impu_tota_loca)-round(pos.val_impo_desc_tota_loca/(1+(ln_valtasa/100)),ln_decim))*(ln_valtasa/100)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);

*/


    UPDATE ped_solic_posic pos
       SET pos.val_prec_cont_tota_loca    = nvl(pos.val_prec_cont_unit_loca*pos.num_unid_aten,
                                                 0),
           pos.val_prec_cata_tota_loca     = nvl(pos.val_prec_cata_unit_loca*pos.num_unid_aten,
                                                 0),
           pos.val_prec_cont_tota_docu    = nvl(pos.val_prec_cont_unit_loca*pos.num_unid_aten,
                                                 0),
           pos.val_prec_cata_tota_docu     = nvl(pos.val_prec_cata_unit_loca*pos.num_unid_aten,
                                                 0),                                      
           pos.val_prec_sin_impu_tota_loca = round((nvl(val_prec_cata_unit_loca*pos.num_unid_aten,
                                                        0) + nvl(val_prec_cont_unit_loca*pos.num_unid_aten,
                                                                  0)) / (1 + (ln_valtasa / 100)),
                                                   ln_decim) * decode((SELECT COUNT(1)
                                                                        FROM ped_solic_cabec     psc,
                                                                             inc_concu_tipo_prog ictp
                                                                       WHERE psc.oid_soli_cabe =
                                                                             pos.soca_oid_soli_cabe
                                                                         AND psc.ictp_oid_tipo_prog =
                                                                             ictp.oid_tipo_prog
                                                                         AND ictp.cod_tipo_prog = 'B'),
                                                                      0,
                                                                      1,
                                                                      0),
           pos.val_prec_sin_impu_tota_docu = round((nvl(val_prec_cata_unit_docu*pos.num_unid_aten,
                                                        0) + nvl(val_prec_conta_unit_docu*pos.num_unid_aten,
                                                                  0)) / (1 + (ln_valtasa / 100)),
                                                   ln_decim) * decode((SELECT COUNT(1)
                                                                        FROM ped_solic_cabec     psc,
                                                                             inc_concu_tipo_prog ictp
                                                                       WHERE psc.oid_soli_cabe =
                                                                             pos.soca_oid_soli_cabe
                                                                         AND psc.ictp_oid_tipo_prog =
                                                                             ictp.oid_tipo_prog
                                                                         AND ictp.cod_tipo_prog = 'B'),
                                                                      0,
                                                                      1,
                                                                      0)
             WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_posic pos
       SET  pos.val_impo_desc_tota_loca     = nvl(round(nvl(val_prec_sin_impu_tota_loca,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0),
           pos.val_impo_desc_tota_docu     = nvl(round(nvl(val_prec_sin_impu_tota_loca,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);


    UPDATE ped_solic_posic pos
       SET pos.val_prec_neto_tota_loca     = nvl(round((nvl(val_prec_cata_tota_loca,
                                                            0) - nvl(val_impo_desc_tota_loca,
                                                                      0)) / (1 + (ln_valtasa / 100)),
                                                       ln_decim),
                                                 0),
           pos.val_prec_neto_tota_docu     = nvl(round((val_prec_cata_tota_docu -
                                                       val_impo_desc_tota_docu) /
                                                       (1 + (ln_valtasa / 100)),
                                                       ln_decim),
                                                 0),
           pos.val_prec_fact_tota_loca    =
           ((nvl(val_prec_cata_tota_loca,
                 0) + nvl(val_prec_cont_tota_loca,
                            0)) - nvl(val_impo_desc_tota_loca,0)) *
           decode((SELECT COUNT(1)
                    FROM ped_solic_cabec     psc,
                         inc_concu_tipo_prog ictp
                   WHERE psc.oid_soli_cabe = pos.soca_oid_soli_cabe
                     AND psc.ictp_oid_tipo_prog = ictp.oid_tipo_prog
                     AND ictp.cod_tipo_prog = 'B'),
                  0,
                  1,
                  0),
           pos.val_prec_fact_tota_docu    =
           ((nvl(val_prec_cata_tota_docu,
                 0) + nvl(val_prec_cont_tota_loca,
                            0)) - nvl(val_impo_desc_tota_loca,0)) *
           decode((SELECT COUNT(1)
                    FROM ped_solic_cabec     psc,
                         inc_concu_tipo_prog ictp
                   WHERE psc.oid_soli_cabe = pos.soca_oid_soli_cabe
                     AND psc.ictp_oid_tipo_prog = ictp.oid_tipo_prog
                     AND ictp.cod_tipo_prog = 'B'),
                  0,
                  1,
                  0)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_posic pos
       SET pos.val_impo_impu_tota_loca    =
           round((nvl(val_prec_sin_impu_tota_loca,0) - nvl(val_impo_desc_tota_loca/(1 + (ln_valtasa / 100)),0))
           *(ln_valtasa / 100),
                                      ln_decim),
           pos.val_impo_impu_unit_docu    =
           round((nvl(val_prec_sin_impu_tota_loca,0) - nvl(val_impo_desc_tota_loca/(1 + (ln_valtasa / 100)),0))
           *(ln_valtasa / 100),
                                      ln_decim)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);
                                     




-- Se actualizan los datos de cabecera del pedidos
    UPDATE ped_solic_cabec ped
       SET ped.val_prec_cata_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0)))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_sin_impu_tota_do =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0)))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0),
                              0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_sin_impu_tota_1 =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0),
                              0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_fact_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_fact_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_tota_loc_uni_dem =
           (SELECT SUM(nvl(b.val_prec_cata_unit_loca,
                           0) * nvl(num_unid_dema,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_neto_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_neto_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_sin_impu_tota =
           (SELECT SUM(round(nvl(b.val_impo_des_sin_imp_tota,0) ,
                             2))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_tota_loca     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_tota_docu     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0) + nvl(b.val_prec_cont_tota_loca,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0) + nvl(b.val_prec_cont_tota_docu,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_impu_tota_loca       =
           round((SELECT SUM(nvl(b.val_impo_impu_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),ln_decim),
           ped.val_impo_impu_tota_docu       =
           round((SELECT SUM(nvl(b.val_impo_impu_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),ln_decim),
           ped.num_unid_aten_tota            =
           (SELECT SUM(b.num_unid_aten)
              FROM ped_solic_posic b,
                   pre_ofert_detal c
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe
               AND b.ofde_oid_deta_ofer = c.oid_deta_ofer(+)
               AND NOT EXISTS (SELECT 1
                      FROM fac_tipo_ofert_exclu
                     WHERE tofe_oid_tipo_ofer = c.tofe_oid_tipo_ofer)),
           ped.num_unid_por_aten_tota        =
           (SELECT SUM(b.num_unid_por_aten)
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.imp_impu_tota_prod_naci        =
           (SELECT SUM(b.imp_impu_tota_prod_naci)
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.imp_iva_impu_tota_prod_naci        =
           (SELECT SUM(b.imp_iva_impu_tota_prod_naci)
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_tasa_impu                  = ln_valtasa,
           ped.val_tasa_flet                  = ln_valtasaflet,
           ped.taim_oid_tasa_impu             = ln_oidtasa,
           ped.val_impo_flet_tota_loca        = ped.val_impo_flet_loca+nvl(ped.val_reca_flet_loca,0),
           ped.val_impo_flet_tota_docu        = ped.val_impo_flet_loca+nvl(ped.val_reca_flet_loca,0)
     WHERE ped.soca_oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_base_flet_loca     =
           (SELECT MAX(nvl(a.val_base_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_base_flet_docu     =
           (SELECT MAX(nvl(a.val_base_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_loca     =
           (SELECT MAX(nvl(a.val_impo_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_flet_loca     =
           (SELECT MAX(nvl(a.val_impo_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_docu     =
           (SELECT MAX(nvl(a.val_impo_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_tota_loca =
           (SELECT MAX(nvl(a.val_impo_flet_tota_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_tota_docu =
           (SELECT MAX(nvl(a.val_impo_flet_tota_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_reca_flet_loca     =
           (SELECT MAX(nvl(a.val_reca_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_reca_flet_docu     =
           (SELECT MAX(nvl(a.val_reca_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi     =
           (SELECT SUM(nvl(a.val_tota_gast_admi,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi2     =
           (SELECT SUM(nvl(a.val_tota_gast_admi2,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_comi_flex     =
           (SELECT SUM(nvl(a.val_tota_comi_flex,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.imp_impu_tota_prod_naci     =
           (SELECT SUM(nvl(a.imp_impu_tota_prod_naci,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.imp_iva_impu_tota_prod_naci     =
           (SELECT SUM(nvl(a.imp_iva_impu_tota_prod_naci,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_3_tota_loca     =
           (SELECT SUM(nvl(a.val_impo_desc_3_tota_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe)
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_prec_cata_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0)))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_sin_impu_tota_do =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0)))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0),
                              0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_sin_impu_tota_1 =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0),
                              0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_fact_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_fact_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_tota_loc_uni_dem =
           (SELECT SUM(nvl(b.val_prec_cata_unit_loca,
                           0) * num_unid_dema)
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_neto_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_neto_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_sin_impu_tota    = round(nvl(cons.val_impo_flet_tota_loca,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_impo_desc_1_sin_impu_tota =
           (SELECT SUM(round(nvl(b.val_impo_desc_tota_loca,
                                 0) / (1 + (ln_valtasa / 100)),
                             ln_decim))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_1_tota_loca     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_1_tota_docu     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0) + nvl(b.val_prec_cont_tota_loca,
                                    0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0) + nvl(b.val_prec_cont_tota_docu,
                                    0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_impu_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe) +
           (cons.val_impo_flet_tota_loca -
           round(cons.val_impo_flet_tota_loca / (1 + (ln_valtasaflet / 100)),
                  ln_decim)) +
           (cons.val_tota_gast_admi - round(cons.val_tota_gast_admi / (1 + (ln_valtasaflet / 100)),
                                            ln_decim))+
           (cons.val_tota_gast_admi2 - round(cons.val_tota_gast_admi2 / (1 + (ln_valtasaflet / 100)),
                                            ln_decim)),
           cons.val_impo_impu_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe) +
           (cons.val_impo_flet_tota_loca -
           round(cons.val_impo_flet_tota_loca / (1 + (ln_valtasaflet / 100)),
                  ln_decim)) +
           (cons.val_tota_gast_admi - round(cons.val_tota_gast_admi / (1 + (ln_valtasaflet / 100)),
                                            ln_decim))+
           (cons.val_tota_gast_admi2 - round(cons.val_tota_gast_admi2 / (1 + (ln_valtasaflet / 100)),
                                            ln_decim)),
           cons.num_unid_aten_tota            =
           (SELECT SUM(b.num_unid_aten)
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.num_unid_por_aten_tota        =
           (SELECT SUM(b.num_unid_por_aten)
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi_sin_impu    = round(nvl(cons.val_tota_gast_admi,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_gast_admi2_sin_impu    = round(nvl(cons.val_tota_gast_admi2,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_impu_gast_admi        = nvl(cons.val_tota_gast_admi,
                                                     0) -
                                                 round(nvl(cons.val_tota_gast_admi,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_impu_gast2_admi        = nvl(cons.val_tota_gast_admi2,
                                                     0) -
                                                 round(nvl(cons.val_tota_gast_admi2,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_comi_flex_sin_impu    = round(nvl(cons.val_tota_comi_flex,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_impo_impu_comi_flex        = nvl(cons.val_tota_comi_flex,
                                                     0) -
                                                 round(nvl(cons.val_tota_comi_flex,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tasa_impu                  = ln_valtasa,
           cons.val_tasa_flet                  = ln_valtasaflet,
           cons.taim_oid_tasa_impu             = ln_oidtasa
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = nvl(cons.val_prec_cata_tota_loca,
                                         0) + nvl(cons.val_prec_cont_tota_loca,
                                                  0) - nvl(cons.val_impo_desc_tota_loca,
                                                           0) + nvl(cons.val_impo_flet_tota_loca,
                                                                    0) +
                                     nvl(cons.val_tota_gast_admi,
                                         0) + nvl(cons.val_tota_gast_admi2,
                                         0) + nvl(cons.val_tota_comi_flex,
                                                  0)
     WHERE cons.oid_soli_cabe = p_oidcons;



if ln_imp_estatal is not null then


   begin
   select y.val_tasa_impu into ln_imp_estatal_temp
   from fac_tipos_impue_ubige x, ped_tasa_impue y, ped_solic_cabec z
   where x.taim_oid_tasa_impu=y.oid_tasa_impu
   and x.vepo_oid_valo_estr_geop=z.zzon_oid_zona
   and z.oid_soli_cabe=p_oidcons
   ;

   if ln_imp_estatal_temp is not null then
      ln_imp_estatal:=ln_imp_estatal_temp;
   end if;


   exception when others then
        NULL;
   end;



    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = (cons.val_prec_cata_tota_loca+nvl(cons.val_impo_flet_tota_loca,0))*(ln_imp_estatal/100), val_impo_redo_loca=0
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca+cons.val_impo_impu_tota_loca
     WHERE cons.oid_soli_cabe = p_oidcons;

end if;


if ln_desc_flete ='S' then

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_desc_flet = round(cons.val_impo_flet_tota_loca*(ln_valtasa/100),ln_decim)
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = cons.val_impo_impu_tota_loca-cons.val_impo_desc_flet
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca-cons.val_impo_desc_flet
     WHERE cons.oid_soli_cabe = p_oidcons;

end if;


    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca-cons.val_impo_desc_3_tota_loca
     WHERE cons.oid_soli_cabe = p_oidcons
     and nvl(cons.val_impo_desc_3_tota_loca,0)<>0
     ;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_desc_3_sin_impu_tota = round(nvl(cons.val_impo_desc_3_tota_loca,
                                                           0) / (1 + (ln_valtasa / 100)),
                                                       ln_decim)
     WHERE cons.oid_soli_cabe = p_oidcons
     and nvl(cons.val_impo_desc_3_tota_loca,0)<>0
     ;


    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = cons.val_impo_impu_tota_loca-(cons.val_impo_desc_3_tota_loca-val_impo_desc_3_sin_impu_tota)
     WHERE cons.oid_soli_cabe = p_oidcons
     and nvl(cons.val_impo_desc_3_tota_loca,0)<>0
     ;

/*if ln_asum>0 then
    UPDATE ped_solic_cabec cons
       SET cons.val_impo_iva_asum_empr = ped_fn_gener_iva_asum(p_oidcons, ln_valtasa, ln_decim, ln_asum)
     WHERE cons.oid_soli_cabe = p_oidcons;
end if;*/




    UPDATE ped_solic_cabec cons
       SET cons.val_impo_redo_loca =
           (nvl(cons.val_prec_cata_sin_impu_tota,
                0) - nvl(cons.val_impo_desc_1_sin_impu_tota,
                          0) + nvl(cons.val_impo_impu_tota_loca,
                                    0) + nvl(cons.val_impo_flet_sin_impu_tota,
                                              0)
                                        + nvl(cons.val_tota_gast_admi_sin_impu,
                                              0)+ nvl(cons.val_tota_gast_admi2_sin_impu,
                                              0)
                                              ) - nvl(cons.val_tota_paga_loca,
                                                        0) - nvl(cons.val_impo_desc_3_sin_impu_tota,0),
           cons.val_impo_redo_docu = nvl(cons.val_impo_redo_loca,
                                         0)
     WHERE cons.oid_soli_cabe = p_oidcons;


/*    DELETE FROM ped_solic_acumu_impue psci WHERE psci.soca_oid_soli_cabe = p_oidcons;

    INSERT INTO ped_solic_acumu_impue
      (oid_acum,
       taim_oid_tasa_impu,
       soca_oid_soli_cabe,
       val_base_impo_posi_loca,
       val_tota_prec_posi_loca,
       val_tota_desc_cabe_loca,
       val_base_impo_desc_cabe,
       val_base_impo_prec_cont,
       val_base_impo_flet_loca,
       val_tota_base_impo_loca,
       val_tota_impu_loca,
       val_base_impo_posi_docu,
       val_tota_impo_loca,
       val_tota_prec_posi_docu,
       val_tota_desc_cabe_docu,
       val_base_impo_desc_cabe_docu,
       val_base_impo_prec_cont_docu,
       val_base_impo_flet_docu,
       val_tota_base_impo_docu,
       val_tota_impu_docu,
       val_tota_impo_docu)
    VALUES
      (p_oidcons,
       ln_oidtasa,
       p_oidcons,
       nvl((SELECT x.val_prec_neto_tota_loca + x.val_prec_cont_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       0,
       0,
       0,
       nvl((SELECT x.val_prec_cont_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_impo_flet_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_prec_neto_tota_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_impo_impu_tota_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_prec_neto_tota_docu + x.val_prec_cont_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_tota_paga_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       0,
       0,
       0,
       nvl((SELECT x.val_prec_cont_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       0,
       nvl((SELECT x.val_prec_neto_tota_docu FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_impo_impu_tota_docu FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_tota_paga_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0));*/

    --commit;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_calculo_consolidado3: ' || ls_sqlerrm || ' p_oidcons:' ||
                              p_oidcons);


  END fac_pr_calculo_consolidado3;
    /***************************************************************************
  Descripcion       : Calculos Consolidado para REclamos
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
PROCEDURE fac_pr_calculo_consolidado2_r(p_oidcons NUMBER)
IS


    ln_valtasa        NUMBER(2);
    ln_oidtasa        NUMBER(10);
    ln_valtasaflet    NUMBER(2);
    ln_decim          NUMBER(3);
    ln_indanul        NUMBER(1);
    ln_factor         NUMBER(5,3);
    --ln_oidtasaflet    NUMBER(10);
    --w_filas        NUMBER(12);

    ln_rete       VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'porcentajeRetencion'),10);

    ln_desc_flete VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'descFlete'),'N');


begin

    select a.num_deci
    into ln_decim
    from seg_moned a, seg_pais b
    where b.mone_oid_mone=a.oid_mone
    and b.oid_pais=(select pais_oid_pais from ped_solic_cabec where oid_soli_cabe=p_oidcons);


    select a.val_tasa_impu, a.oid_tasa_impu into ln_valtasa, ln_oidtasa
    from ped_tasa_impue a, ped_impue_gener b
    where b.taim_oid_tasa_impu=a.oid_tasa_impu
    and b.sbac_oid_sbac=888;

    select a.val_tasa_impu into ln_valtasaflet
    from ped_tasa_impue a, ped_impue_gener b
    where b.taim_oid_tasa_impu_flet=a.oid_tasa_impu
    and b.sbac_oid_sbac=888;

    select TS.IND_ANUL
    into ln_indanul
    from ped_tipo_solic_pais tsp, ped_tipo_solic ts
    where tsp.tsol_oid_tipo_soli = ts.oid_tipo_soli
    and tsp.oid_tipo_soli_pais=(select TSPA_OID_TIPO_SOLI_PAIS from ped_solic_cabec where oid_soli_cabe=p_oidcons);



update ped_solic_posic pos set pos.val_prec_conta_unit_docu=nvl(pos.val_prec_cont_unit_loca,0)
,pos.val_prec_cata_unit_docu=nvl(pos.val_prec_cata_unit_loca,0)
,pos.VAL_PREC_SIN_IMPU_UNIT_LOCA=round(nvl(val_prec_cata_unit_loca,0)/(1+(ln_valtasa/100)),ln_decim)*decode((select count(1) from ped_solic_cabec psc, inc_concu_tipo_prog ictp where psc.oid_soli_cabe=pos.soca_oid_soli_cabe and psc.ictp_oid_tipo_prog=ictp.oid_tipo_prog and ictp.cod_tipo_prog='B'),0,1,0)
,pos.VAL_PREC_SIN_IMPU_UNIT_DOCU=round(nvl(val_prec_cata_unit_loca,0)/(1+(ln_valtasa/100)),ln_decim)*decode((select count(1) from ped_solic_cabec psc, inc_concu_tipo_prog ictp where psc.oid_soli_cabe=pos.soca_oid_soli_cabe and psc.ictp_oid_tipo_prog=ictp.oid_tipo_prog and ictp.cod_tipo_prog='B'),0,1,0)
,pos.num_unid_aten=nvl(pos.num_unid_compr,0)
,pos.val_tasa_impu=ln_valtasa
,pos.taim_oid_tasa_impu=ln_oidtasa
where soca_oid_soli_cabe in
(
select ped.oid_soli_cabe from ped_solic_cabec cons, ped_solic_cabec ped, ped_tipo_solic_pais ptsp, ped_tipo_solic pts
where cons.oid_soli_cabe=ped.soca_oid_soli_cabe and cons.tspa_oid_tipo_soli_pais=ptsp.oid_tipo_soli_pais
and ptsp.tsol_oid_tipo_soli=pts.oid_tipo_soli and pts.ind_cons=1 and pts.ind_soli_nega=1
and cons.oid_soli_cabe=p_oidcons
);

update ped_solic_posic pos set
pos.VAL_IMPO_DESC_UNIT_LOCA=nvl(round(nvl(VAL_PREC_SIN_IMPU_UNIT_LOCA,0)*nvl(val_porc_desc,0)/100,ln_decim),0)
,pos.VAL_IMPO_DESC_UNIT_DOCU=nvl(round(nvl(VAL_PREC_SIN_IMPU_UNIT_DOCU,0)*nvl(val_porc_desc,0)/100,ln_decim),0)
where soca_oid_soli_cabe in
(
select ped.oid_soli_cabe from ped_solic_cabec cons, ped_solic_cabec ped, ped_tipo_solic_pais ptsp, ped_tipo_solic pts
where cons.oid_soli_cabe=ped.soca_oid_soli_cabe and cons.tspa_oid_tipo_soli_pais=ptsp.oid_tipo_soli_pais
and ptsp.tsol_oid_tipo_soli=pts.oid_tipo_soli and pts.ind_cons=1 and pts.ind_soli_nega=1
and cons.oid_soli_cabe=p_oidcons
);

update ped_solic_posic pos set
pos.VAL_PREC_NETO_UNIT_LOCA=nvl(round(nvl(VAL_PREC_SIN_IMPU_UNIT_LOCA,0)-nvl(VAL_IMPO_DESC_UNIT_LOCA,0),ln_decim),0)
,pos.VAL_PREC_NETO_UNIT_DOCU=nvl(round(nvl(VAL_PREC_SIN_IMPU_UNIT_DOCU,0)-nvl(VAL_IMPO_DESC_UNIT_DOCU,0)),0)
,pos.VAL_PREC_FACT_UNIT_LOCA=nvl(val_prec_cata_unit_loca,0)-nvl(VAL_IMPO_DESC_UNIT_LOCA,0)
,pos.VAL_PREC_FACT_UNIT_DOCU=nvl(val_prec_cata_unit_docu,0)-nvl(VAL_IMPO_DESC_UNIT_docu,0)
,pos.VAL_IMPO_IMPU_UNIT_LOCA=(nvl(val_prec_cata_unit_loca,0)-nvl(VAL_PREC_SIN_IMPU_UNIT_LOCA,0))
,pos.VAL_IMPO_IMPU_UNIT_DOCU=(nvl(val_prec_cata_unit_docu,0)-nvl(VAL_PREC_SIN_IMPU_UNIT_docu,0))
where soca_oid_soli_cabe in
(
select ped.oid_soli_cabe from ped_solic_cabec cons, ped_solic_cabec ped, ped_tipo_solic_pais ptsp, ped_tipo_solic pts
where cons.oid_soli_cabe=ped.soca_oid_soli_cabe and cons.tspa_oid_tipo_soli_pais=ptsp.oid_tipo_soli_pais
and ptsp.tsol_oid_tipo_soli=pts.oid_tipo_soli and pts.ind_cons=1 and pts.ind_soli_nega=1
and cons.oid_soli_cabe=p_oidcons
);



update ped_solic_posic pos
set pos.VAL_PREC_SIN_IMPU_TOTA_LOCA=round(nvl(VAL_PREC_SIN_IMPU_UNIT_LOCA,0)*num_unid_aten,ln_decim)
,pos.VAL_PREC_SIN_IMPU_TOTA_DOCU=round(nvl(VAL_PREC_SIN_IMPU_UNIT_DOCU,0)*num_unid_aten,ln_decim)
,pos.VAL_IMPO_DESC_TOTA_LOCA=round(nvl(VAL_IMPO_DESC_UNIT_LOCA,0)*num_unid_aten,ln_decim)
,pos.VAL_IMPO_DESC_TOTA_DOCU=round(nvl(VAL_IMPO_DESC_UNIT_DOCU,0)*num_unid_aten,ln_decim)
,pos.val_impo_des_sin_imp_tota=round(nvl(VAL_IMPO_DESC_UNIT_LOCA,0)*num_unid_aten,ln_decim)
,pos.VAL_PREC_NETO_TOTA_LOCA=round(nvl(VAL_PREC_NETO_UNIT_LOCA,0)*num_unid_aten,ln_decim)
,pos.VAL_PREC_NETO_TOTA_DOCU=round(nvl(VAL_PREC_NETO_UNIT_DOCU,0)*num_unid_aten,ln_decim)
,pos.VAL_PREC_FACT_TOTA_LOCA=round(nvl(VAL_PREC_FACT_UNIT_LOCA,0)*num_unid_aten,ln_decim)
,pos.VAL_PREC_FACT_TOTA_DOCU=round(nvl(VAL_PREC_FACT_UNIT_DOCU,0)*num_unid_aten,ln_decim)
,pos.VAL_IMPO_IMPU_TOTA_LOCA=round(nvl(VAL_IMPO_IMPU_UNIT_LOCA,0)*num_unid_aten,ln_decim)
,pos.VAL_IMPO_IMPU_TOTA_DOCU=round(nvl(VAL_IMPO_IMPU_UNIT_DOCU,0)*num_unid_aten,ln_decim)
,pos.VAL_PREC_CATA_TOTA_LOCA=round(nvl(VAL_PREC_CATA_UNIT_LOCA,0)*num_unid_aten,ln_decim)
,pos.VAL_PREC_CATA_TOTA_DOCU=round(nvl(VAL_PREC_CATA_UNIT_DOCU,0)*num_unid_aten,ln_decim)
,pos.VAL_PREC_CONT_TOTA_LOCA=round(nvl(VAL_PREC_CONT_UNIT_LOCA,0)*num_unid_aten*decode((select count(1) from ped_solic_cabec psc, inc_concu_tipo_prog ictp where psc.oid_soli_cabe=pos.soca_oid_soli_cabe and psc.ictp_oid_tipo_prog=ictp.oid_tipo_prog and ictp.cod_tipo_prog='B'),0,1,0),2)
,pos.VAL_PREC_CONT_TOTA_DOCU=round(nvl(VAL_PREC_CONTA_UNIT_DOCU,0)*num_unid_aten*decode((select count(1) from ped_solic_cabec psc, inc_concu_tipo_prog ictp where psc.oid_soli_cabe=pos.soca_oid_soli_cabe and psc.ictp_oid_tipo_prog=ictp.oid_tipo_prog and ictp.cod_tipo_prog='B'),0,1,0),2)
,pos.VAL_PREC_TOTA_TOTA_LOCA=round(nvl(VAL_PREC_FACT_UNIT_LOCA,0)*num_unid_aten,2)
,pos.ind_no_impr=decode((select count(1) from pre_ofert_detal x, fac_tipo_ofert_exclu y where x.tofe_oid_tipo_ofer=y.tofe_oid_tipo_ofer and x.oid_deta_ofer=pos.ofde_oid_deta_ofer),0,0,1)
where soca_oid_soli_cabe in
(
select ped.oid_soli_cabe from ped_solic_cabec cons, ped_solic_cabec ped, ped_tipo_solic_pais ptsp, ped_tipo_solic pts
where cons.oid_soli_cabe=ped.soca_oid_soli_cabe and cons.tspa_oid_tipo_soli_pais=ptsp.oid_tipo_soli_pais
and ptsp.tsol_oid_tipo_soli=pts.oid_tipo_soli and pts.ind_cons=1 and pts.ind_soli_nega=1
and cons.oid_soli_cabe=p_oidcons
);

update ped_solic_cabec ped
set ped.val_prec_cata_tota_loca=(select sum(nvl(b.val_prec_cata_tota_loca,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.val_prec_cata_tota_docu=(select sum(nvl(b.val_prec_cata_tota_docu,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.val_prec_cata_sin_impu_tota=(select sum(decode(nvl(b.val_prec_cata_unit_loca,0),0,0,nvl(b.val_prec_sin_impu_tota_loca,0))) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.val_prec_cata_sin_impu_tota_do=(select sum(decode(nvl(b.val_prec_cata_unit_docu,0),0,0,nvl(b.val_prec_sin_impu_tota_docu,0))) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.val_prec_cont_sin_impu_tota=(select sum(decode(nvl(b.val_prec_cata_unit_loca,0),0,nvl(b.val_prec_sin_impu_tota_loca,0),0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.val_prec_cont_sin_impu_tota_1=(select sum(decode(nvl(b.val_prec_cata_unit_docu,0),0,nvl(b.val_prec_sin_impu_tota_docu,0),0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.val_prec_fact_tota_loca=(select sum(nvl(b.val_prec_fact_tota_loca,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.val_prec_fact_tota_docu=(select sum(nvl(b.val_prec_fact_tota_docu,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.val_prec_cont_tota_loca=(select sum(nvl(b.val_prec_cont_tota_loca,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.val_prec_cont_tota_docu=(select sum(nvl(b.val_prec_cont_tota_docu,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.val_prec_cata_tota_loc_uni_dem=(select sum(nvl(b.val_prec_cata_unit_loca,0)*nvl(num_unid_dema,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.val_prec_neto_tota_loca=(select sum(nvl(b.val_prec_neto_tota_loca,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.val_prec_neto_tota_docu=(select sum(nvl(b.val_prec_neto_tota_docu,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.Val_Impo_Desc_1_Sin_Impu_Tota=(select sum(nvl(b.VAL_IMPO_DESC_TOTA_LOCA,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.VAL_IMPO_DESC_1_TOTA_LOCA=(select sum(nvl(b.VAL_IMPO_DESC_TOTA_LOCA,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.VAL_IMPO_DESC_1_TOTA_DOCU=(select sum(nvl(b.VAL_IMPO_DESC_TOTA_DOCU,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.VAL_IMPO_DESC_TOTA_LOCA=(select sum(nvl(b.VAL_IMPO_DESC_TOTA_LOCA,0)+nvl(b.val_prec_cont_tota_loca,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.VAL_IMPO_DESC_TOTA_DOCU=(select sum(nvl(b.VAL_IMPO_DESC_TOTA_DOCU,0)+nvl(b.val_prec_cont_tota_docu,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.VAL_IMPO_IMPU_TOTA_LOCA=(select sum(nvl(b.VAL_IMPO_IMPU_TOTA_LOCA,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.VAL_IMPO_IMPU_TOTA_DOCU=(select sum(nvl(b.VAL_IMPO_IMPU_TOTA_DOCU,0)) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.num_unid_aten_tota=(select sum(b.num_unid_aten) from ped_solic_posic b, pre_ofert_detal c where b.soca_oid_soli_cabe=ped.oid_soli_cabe and b.ofde_oid_deta_ofer=c.oid_deta_ofer(+) and not exists (select 1 from fac_tipo_ofert_exclu where tofe_oid_tipo_ofer=c.tofe_oid_tipo_ofer))
, ped.num_unid_por_aten_tota=(select sum(b.num_unid_por_aten) from ped_solic_posic b where b.soca_oid_soli_cabe=ped.oid_soli_cabe)
, ped.val_tasa_impu=ln_valtasa
, ped.val_tasa_flet=ln_valtasaflet
, ped.taim_oid_tasa_impu=ln_oidtasa
where ped.soca_oid_soli_cabe =p_oidcons;


update ped_solic_cabec cons
set cons.val_base_flet_loca=(select max(nvl(a.val_base_flet_loca,0)) from ped_solic_cabec a where a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_base_flet_docu=(select max(nvl(a.val_base_flet_docu,0)) from ped_solic_cabec a where a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_impo_flet_loca=(select max(nvl(a.val_impo_flet_loca,0)) from ped_solic_cabec a where a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_impo_flet_docu=(select max(nvl(a.val_impo_flet_docu,0)) from ped_solic_cabec a where a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_impo_flet_tota_loca=(select max(nvl(a.val_impo_flet_tota_loca,0)) from ped_solic_cabec a where a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_impo_flet_tota_docu=(select max(nvl(a.val_impo_flet_tota_docu,0)) from ped_solic_cabec a where a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_reca_flet_loca=(select max(nvl(a.val_reca_flet_loca,0)) from ped_solic_cabec a where a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_reca_flet_docu=(select max(nvl(a.val_reca_flet_docu,0)) from ped_solic_cabec a where a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_tota_gast_admi=(select sum(nvl(a.val_tota_gast_admi,0)) from ped_solic_cabec a where a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_tota_gast_admi2=(select sum(nvl(a.val_tota_gast_admi2,0)) from ped_solic_cabec a where a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_tota_comi_flex=(select sum(nvl(a.val_tota_comi_flex,0)) from ped_solic_cabec a where a.soca_oid_soli_cabe=cons.oid_soli_cabe)
where cons.oid_soli_cabe=p_oidcons;


update ped_solic_cabec cons
set cons.val_prec_cata_tota_loca=(select sum(nvl(b.val_prec_cata_tota_loca,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_prec_cata_tota_docu=(select sum(nvl(b.val_prec_cata_tota_docu,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_prec_cata_sin_impu_tota=(select sum(decode(nvl(b.val_prec_cata_unit_loca,0),0,0,nvl(b.val_prec_sin_impu_tota_loca,0))) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_prec_cata_sin_impu_tota_do=(select sum(decode(nvl(b.val_prec_cata_unit_docu,0),0,0,nvl(b.val_prec_sin_impu_tota_docu,0))) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_prec_cont_sin_impu_tota=(select sum(decode(nvl(b.val_prec_cata_unit_loca,0),0,nvl(b.val_prec_sin_impu_tota_loca,0),0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_prec_cont_sin_impu_tota_1=(select sum(decode(nvl(b.val_prec_cata_unit_docu,0),0,nvl(b.val_prec_sin_impu_tota_docu,0),0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_prec_fact_tota_loca=(select sum(nvl(b.val_prec_fact_tota_loca,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_prec_fact_tota_docu=(select sum(nvl(b.val_prec_fact_tota_docu,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_prec_cont_tota_loca=(select sum(nvl(b.val_prec_cont_tota_loca,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_prec_cont_tota_docu=(select sum(nvl(b.val_prec_cont_tota_docu,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_prec_cata_tota_loc_uni_dem=(select sum(nvl(b.val_prec_cata_unit_loca,0)*num_unid_dema) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_prec_neto_tota_loca=(select sum(nvl(b.val_prec_neto_tota_loca,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_prec_neto_tota_docu=(select sum(nvl(b.val_prec_neto_tota_docu,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_impo_flet_sin_impu_tota=round(nvl(cons.val_impo_flet_tota_loca,0)/(1+(ln_valtasaflet/100)),ln_decim)
, cons.Val_Impo_Desc_1_Sin_Impu_Tota=(select sum(round(nvl(b.VAL_IMPO_DESC_TOTA_LOCA,0),ln_decim)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.VAL_IMPO_DESC_1_TOTA_LOCA=(select sum(nvl(b.VAL_IMPO_DESC_TOTA_LOCA,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.VAL_IMPO_DESC_1_TOTA_DOCU=(select sum(nvl(b.VAL_IMPO_DESC_TOTA_DOCU,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.VAL_IMPO_DESC_TOTA_LOCA=(select sum(nvl(b.VAL_IMPO_DESC_TOTA_LOCA,0)+nvl(b.val_prec_cont_tota_loca,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.VAL_IMPO_DESC_TOTA_DOCU=(select sum(nvl(b.VAL_IMPO_DESC_TOTA_DOCU,0)+nvl(b.val_prec_cont_tota_docu,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.VAL_IMPO_IMPU_TOTA_LOCA=(select sum(nvl(b.VAL_IMPO_IMPU_TOTA_LOCA,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)+(cons.val_impo_flet_tota_loca-round(cons.val_impo_flet_tota_loca/(1+(ln_valtasaflet/100)),ln_decim))+(cons.val_tota_gast_admi-round(cons.val_tota_gast_admi/(1+(ln_valtasaflet/100)),ln_decim))+(cons.val_tota_gast_admi2-round(cons.val_tota_gast_admi2/(1+(ln_valtasaflet/100)),ln_decim))
, cons.VAL_IMPO_IMPU_TOTA_DOCU=(select sum(nvl(b.VAL_IMPO_IMPU_TOTA_DOCU,0)) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)+(cons.val_impo_flet_tota_loca-round(cons.val_impo_flet_tota_loca/(1+(ln_valtasaflet/100)),ln_decim))+(cons.val_tota_gast_admi-round(cons.val_tota_gast_admi/(1+(ln_valtasaflet/100)),ln_decim))+(cons.val_tota_gast_admi2-round(cons.val_tota_gast_admi2/(1+(ln_valtasaflet/100)),ln_decim))
, cons.num_unid_aten_tota=(select sum(b.num_unid_aten) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.num_unid_por_aten_tota=(select sum(b.num_unid_por_aten) from ped_solic_cabec a, ped_solic_posic b where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.soca_oid_soli_cabe=cons.oid_soli_cabe)
, cons.val_tota_gast_admi_sin_impu=round(nvl(cons.val_tota_gast_admi,0)/(1+(ln_valtasaflet/100)),ln_decim)
, cons.val_tota_gast_admi2_sin_impu=round(nvl(cons.val_tota_gast_admi2,0)/(1+(ln_valtasaflet/100)),ln_decim)
, cons.val_tota_impu_gast_admi=nvl(cons.val_tota_gast_admi,0)-round(nvl(cons.val_tota_gast_admi,0)/(1+(ln_valtasaflet/100)),ln_decim)
, cons.val_tota_impu_gast2_admi=nvl(cons.val_tota_gast_admi2,0)-round(nvl(cons.val_tota_gast_admi2,0)/(1+(ln_valtasaflet/100)),ln_decim)
, cons.val_tota_comi_flex_sin_impu=round(nvl(cons.val_tota_comi_flex,0)/(1+(ln_valtasaflet/100)),ln_decim)
, cons.val_impo_impu_comi_flex=nvl(cons.val_tota_comi_flex,0)-round(nvl(cons.val_tota_comi_flex,0)/(1+(ln_valtasaflet/100)),ln_decim)
, cons.val_tasa_impu=ln_valtasa
, cons.val_tasa_flet=ln_valtasaflet
, cons.taim_oid_tasa_impu=ln_oidtasa
where cons.oid_soli_cabe=p_oidcons;

if  ln_indanul = 1 then
    ln_factor := (ln_rete/100);
else
    ln_factor := 0;
end if;

update ped_solic_cabec cons
set cons.val_impo_rete_desc=round(cons.val_impo_desc_1_tota_loca*ln_factor, ln_decim)
where cons.oid_soli_cabe =p_oidcons;


update ped_solic_cabec cons
set cons.val_tota_paga_loca=nvl(cons.val_prec_cata_tota_loca,0)+nvl(cons.val_prec_cont_tota_loca,0)-nvl(cons.val_impo_desc_tota_loca,0)+nvl(cons.val_impo_flet_tota_loca,0)+nvl(cons.val_tota_gast_admi,0)+nvl(cons.val_tota_gast_admi2,0)+nvl(cons.val_tota_comi_flex,0)+cons.val_impo_rete_desc
where cons.oid_soli_cabe=p_oidcons;

if ln_desc_flete ='S' then

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_desc_flet = round(cons.val_impo_flet_tota_loca*(ln_valtasa/100),ln_decim)
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = cons.val_impo_impu_tota_loca-cons.val_impo_desc_flet
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca-cons.val_impo_desc_flet
     WHERE cons.oid_soli_cabe = p_oidcons;

end if;

update ped_solic_cabec cons
set cons.val_impo_redo_loca=
    (nvl(cons.val_prec_cata_sin_impu_tota,0)
    -nvl(cons.val_impo_desc_1_sin_impu_tota,0
    )+nvl(cons.val_impo_impu_tota_loca,0)+nvl(cons.val_impo_flet_sin_impu_tota,0)+nvl(cons.val_tota_gast_admi_sin_impu,0)+nvl(cons.val_tota_gast_admi2_sin_impu,0))-nvl(cons.val_tota_paga_loca,0)+cons.val_impo_rete_desc
, cons.val_impo_redo_docu=nvl(cons.val_impo_redo_loca,0)
where cons.oid_soli_cabe=p_oidcons;


/*delete from ped_solic_acumu_impue psci where psci.soca_oid_soli_cabe=p_oidcons;

insert into ped_solic_acumu_impue
(
OID_ACUM,
TAIM_OID_TASA_IMPU,
SOCA_OID_SOLI_CABE,
VAL_BASE_IMPO_POSI_LOCA,
VAL_TOTA_PREC_POSI_LOCA,
VAL_TOTA_DESC_CABE_LOCA,
VAL_BASE_IMPO_DESC_CABE,
VAL_BASE_IMPO_PREC_CONT,
VAL_BASE_IMPO_FLET_LOCA,
VAL_TOTA_BASE_IMPO_LOCA,
VAL_TOTA_IMPU_LOCA,
VAL_BASE_IMPO_POSI_DOCU,
VAL_TOTA_IMPO_LOCA,
VAL_TOTA_PREC_POSI_DOCU,
VAL_TOTA_DESC_CABE_DOCU,
VAL_BASE_IMPO_DESC_CABE_DOCU,
VAL_BASE_IMPO_PREC_CONT_DOCU,
VAL_BASE_IMPO_FLET_DOCU,
VAL_TOTA_BASE_IMPO_DOCU,
VAL_TOTA_IMPU_DOCU,
VAL_TOTA_IMPO_DOCU
)
values
(
p_oidcons,
ln_oidtasa,
p_oidcons,
(select x.val_prec_neto_tota_loca+x.val_prec_cont_sin_impu_tota from ped_solic_cabec x where oid_soli_cabe=p_oidcons),
0,
0,
0,
(select x.val_prec_cont_sin_impu_tota from ped_solic_cabec x where oid_soli_cabe=p_oidcons),
(select x.val_impo_flet_sin_impu_tota from ped_solic_cabec x where oid_soli_cabe=p_oidcons),
(select x.val_prec_neto_tota_loca from ped_solic_cabec x where oid_soli_cabe=p_oidcons),
(select x.val_impo_impu_tota_loca from ped_solic_cabec x where oid_soli_cabe=p_oidcons),
(select x.val_prec_neto_tota_docu+x.val_prec_cont_sin_impu_tota from ped_solic_cabec x where oid_soli_cabe=p_oidcons),
(select x.val_tota_paga_loca from ped_solic_cabec x where oid_soli_cabe=p_oidcons),
0,
0,
0,
(select x.val_prec_cont_sin_impu_tota from ped_solic_cabec x where oid_soli_cabe=p_oidcons),
0,
(select x.val_prec_neto_tota_docu from ped_solic_cabec x where oid_soli_cabe=p_oidcons),
(select x.val_impo_impu_tota_docu from ped_solic_cabec x where oid_soli_cabe=p_oidcons),
(select x.val_tota_paga_loca from ped_solic_cabec x where oid_soli_cabe=p_oidcons)
);*/

    return;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_calculo_consolidado2_r: ' || ls_sqlerrm || ' p_oidcons:' || p_oidcons);
  END fac_pr_calculo_consolidado2_r;
  /***************************************************************************
  Descripcion       : Calculos Consolidado para REclamos
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
PROCEDURE fac_pr_calculo_consolidado3_r(p_oidcons NUMBER)
IS


    ln_valtasa     NUMBER(2);
    ln_oidtasa     NUMBER(10);
    ln_valtasaflet NUMBER(2);
    ln_decim       NUMBER(3);
    --w_filas        NUMBER(12);

    ln_imp_estatal VARCHAR2(2):=IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'impuestoEstatal');

    ln_imp_estatal_temp VARCHAR2(2);

    ln_desc_flete VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'descFlete'),'N');


    ls_ICENC VARCHAR2(2):=IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'ICENC');

  BEGIN



    SELECT a.num_deci
      INTO ln_decim
      FROM seg_moned a,
           seg_pais  b
     WHERE b.mone_oid_mone = a.oid_mone
       AND b.oid_pais = (SELECT pais_oid_pais FROM ped_solic_cabec WHERE oid_soli_cabe = p_oidcons);

    begin

      SELECT a.val_tasa_impu,
             a.oid_tasa_impu,
             a.val_tasa_impu
        INTO ln_valtasa,
             ln_oidtasa,
             ln_valtasaflet
        FROM ped_tasa_impue  a,
             fac_tipos_impue_ubige c,
             ped_solic_cabec d
       WHERE a.oid_tasa_impu=c.taim_oid_tasa_impu
       and c.vepo_oid_valo_estr_geop=d.zzon_oid_zona
       and d.oid_soli_cabe=p_oidcons
       and IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'impuestoEstatal') is null;

    exception when no_data_found then

    SELECT a.val_tasa_impu,
           a.oid_tasa_impu
      INTO ln_valtasa,
           ln_oidtasa
      FROM ped_tasa_impue  a,
           ped_impue_gener b
     WHERE b.taim_oid_tasa_impu = a.oid_tasa_impu
       AND b.sbac_oid_sbac = 888;

    SELECT a.val_tasa_impu
      INTO ln_valtasaflet
      FROM ped_tasa_impue  a,
           ped_impue_gener b
     WHERE b.taim_oid_tasa_impu_flet = a.oid_tasa_impu
       AND b.sbac_oid_sbac = 888;

    end;





    UPDATE ped_solic_posic pos
       SET pos.val_prec_conta_unit_docu    = nvl(pos.val_prec_cont_unit_loca,
                                                 0),
           pos.val_prec_cata_unit_docu     = nvl(pos.val_prec_cata_unit_loca,
                                                 0),
           pos.val_prec_sin_impu_unit_loca = round((nvl(val_prec_cata_unit_loca,
                                                        0) + nvl(val_prec_cont_unit_loca,
                                                                  0)) / (1 + (ln_valtasa / 100)),
                                                   ln_decim) * decode((SELECT COUNT(1)
                                                                        FROM ped_solic_cabec     psc,
                                                                             inc_concu_tipo_prog ictp
                                                                       WHERE psc.oid_soli_cabe =
                                                                             pos.soca_oid_soli_cabe
                                                                         AND psc.ictp_oid_tipo_prog =
                                                                             ictp.oid_tipo_prog
                                                                         AND ictp.cod_tipo_prog = 'B'),
                                                                      0,
                                                                      1,
                                                                      0),
           pos.val_prec_sin_impu_unit_docu = round((nvl(val_prec_cata_unit_docu,
                                                        0) + nvl(val_prec_conta_unit_docu,
                                                                  0)) / (1 + (ln_valtasa / 100)),
                                                   ln_decim) * decode((SELECT COUNT(1)
                                                                        FROM ped_solic_cabec     psc,
                                                                             inc_concu_tipo_prog ictp
                                                                       WHERE psc.oid_soli_cabe =
                                                                             pos.soca_oid_soli_cabe
                                                                         AND psc.ictp_oid_tipo_prog =
                                                                             ictp.oid_tipo_prog
                                                                         AND ictp.cod_tipo_prog = 'B'),
                                                                      0,
                                                                      1,
                                                                      0),
           pos.num_unid_aten               = nvl(pos.num_unid_compr,
                                                 0),
           pos.val_tasa_impu               = ln_valtasa,
           pos.taim_oid_tasa_impu          = ln_oidtasa,

           pos.ind_dent_fuer_caja_bols     = (select mp.cod_ind_dent_caja from mae_produ mp where oid_prod=pos.prod_oid_prod)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 1
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_posic pos
       SET  pos.val_impo_desc_unit_loca     = nvl(round(nvl(val_prec_sin_impu_unit_loca,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0),
           pos.val_impo_desc_unit_docu     = nvl(round(nvl(val_prec_sin_impu_unit_loca,
                                                           0) * nvl(val_porc_desc,
                                                                    0) / 100,
                                                       ln_decim),
                                                 0)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 1
                                     AND cons.oid_soli_cabe = p_oidcons);


    UPDATE ped_solic_posic pos
       SET pos.val_prec_neto_unit_loca     = nvl(round((nvl(val_prec_cata_unit_loca,
                                                            0) - nvl(val_impo_desc_unit_loca,
                                                                      0)) / (1 + (ln_valtasa / 100)),
                                                       ln_decim),
                                                 0),
           pos.val_prec_neto_unit_docu     = nvl(round((val_prec_cata_unit_docu -
                                                       val_impo_desc_unit_docu) /
                                                       (1 + (ln_valtasa / 100)),
                                                       ln_decim),
                                                 0),
           pos.val_prec_fact_unit_loca    =
           ((nvl(val_prec_cata_unit_loca,
                 0) + nvl(val_prec_cont_unit_loca,
                            0)) - nvl(val_impo_desc_unit_loca,0)) *
           decode((SELECT COUNT(1)
                    FROM ped_solic_cabec     psc,
                         inc_concu_tipo_prog ictp
                   WHERE psc.oid_soli_cabe = pos.soca_oid_soli_cabe
                     AND psc.ictp_oid_tipo_prog = ictp.oid_tipo_prog
                     AND ictp.cod_tipo_prog = 'B'),
                  0,
                  1,
                  0),
           pos.val_prec_fact_unit_docu    =
           ((nvl(val_prec_cata_unit_docu,
                 0) + nvl(val_prec_cont_unit_loca,
                            0)) - nvl(val_impo_desc_unit_loca,0)) *
           decode((SELECT COUNT(1)
                    FROM ped_solic_cabec     psc,
                         inc_concu_tipo_prog ictp
                   WHERE psc.oid_soli_cabe = pos.soca_oid_soli_cabe
                     AND psc.ictp_oid_tipo_prog = ictp.oid_tipo_prog
                     AND ictp.cod_tipo_prog = 'B'),
                  0,
                  1,
                  0)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 1
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_posic pos
       SET pos.val_impo_impu_unit_loca    =
           round((nvl(val_prec_sin_impu_unit_loca,0) - nvl(val_impo_desc_unit_loca/1.16,0))
           *(ln_valtasa / 100),
                                      ln_decim),
           pos.val_impo_impu_unit_docu    =
           round((nvl(val_prec_sin_impu_unit_loca,0) - nvl(val_impo_desc_unit_loca/1.16,0))
           *(ln_valtasa / 100),
                                      ln_decim)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 1
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_posic pos
       SET pos.val_prec_sin_impu_tota_loca = round(nvl(val_prec_sin_impu_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_sin_impu_tota_docu = round(nvl(val_prec_sin_impu_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_desc_tota_loca     = round(nvl(val_impo_desc_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_des_sin_imp_tota     = round(nvl(val_impo_desc_unit_loca,
                                                       0)/ (1 + (ln_valtasa / 100)) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_desc_tota_docu     = round(nvl(val_impo_desc_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_neto_tota_loca     = round(nvl(val_prec_neto_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_neto_tota_docu     = round(nvl(val_prec_neto_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_fact_tota_loca     = round(nvl(val_prec_fact_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_fact_tota_docu     = round(nvl(val_prec_fact_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_impu_tota_loca     = round(nvl(val_impo_impu_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_impo_impu_tota_docu     = round(nvl(val_impo_impu_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_cata_tota_loca     = round(nvl(val_prec_cata_unit_loca,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_cata_tota_docu     = round(nvl(val_prec_cata_unit_docu,
                                                       0) * num_unid_aten,
                                                   ln_decim),
           pos.val_prec_cont_tota_loca     = round(nvl(val_prec_cont_unit_loca,
                                                       0) * num_unid_aten *
                                                   decode((SELECT COUNT(1)
                                                            FROM ped_solic_cabec     psc,
                                                                 inc_concu_tipo_prog ictp
                                                           WHERE psc.oid_soli_cabe =
                                                                 pos.soca_oid_soli_cabe
                                                             AND psc.ictp_oid_tipo_prog =
                                                                 ictp.oid_tipo_prog
                                                             AND ictp.cod_tipo_prog = 'B'),
                                                          0,
                                                          1,
                                                          0),
                                                   2),
           pos.val_prec_cont_tota_docu     = round(nvl(val_prec_conta_unit_docu,
                                                       0) * num_unid_aten *
                                                   decode((SELECT COUNT(1)
                                                            FROM ped_solic_cabec     psc,
                                                                 inc_concu_tipo_prog ictp
                                                           WHERE psc.oid_soli_cabe =
                                                                 pos.soca_oid_soli_cabe
                                                             AND psc.ictp_oid_tipo_prog =
                                                                 ictp.oid_tipo_prog
                                                             AND ictp.cod_tipo_prog = 'B'),
                                                          0,
                                                          1,
                                                          0),
                                                   2),
           pos.val_prec_tota_tota_loca     = round(nvl(val_prec_fact_unit_loca,
                                                       0) * num_unid_aten,
                                                   2),
           pos.ind_no_impr                 = decode((SELECT COUNT(1)
                                                      FROM pre_ofert_detal      x,
                                                           fac_tipo_ofert_exclu y
                                                     WHERE x.tofe_oid_tipo_ofer =
                                                           y.tofe_oid_tipo_ofer
                                                       AND x.oid_deta_ofer = pos.ofde_oid_deta_ofer
                                                       and x.imp_prec_cata=0
                                                       ),
                                                    0,
                                                    0,
                                                    1)
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     AND pts.ind_soli_nega = 1
                                     AND cons.oid_soli_cabe = p_oidcons);

    UPDATE ped_solic_cabec ped
       SET ped.val_prec_cata_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0)))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_sin_impu_tota_do =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0)))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0),
                              0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_sin_impu_tota_1 =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0),
                              0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_fact_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_fact_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cont_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_cata_tota_loc_uni_dem =
           (SELECT SUM(nvl(b.val_prec_cata_unit_loca,
                           0) * nvl(num_unid_dema,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_neto_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_prec_neto_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_sin_impu_tota =
           (SELECT SUM(round(nvl(b.val_impo_des_sin_imp_tota,0) ,
                             2))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_tota_loca     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_1_tota_docu     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0) + nvl(b.val_prec_cont_tota_loca,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_desc_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0) + nvl(b.val_prec_cont_tota_docu,
                                    0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_impu_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_loca,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_impo_impu_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_docu,
                           0))
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.num_unid_aten_tota            =
           (SELECT SUM(b.num_unid_aten)
              FROM ped_solic_posic b,
                   pre_ofert_detal c
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe
               AND b.ofde_oid_deta_ofer = c.oid_deta_ofer(+)
               AND NOT EXISTS (SELECT 1
                      FROM fac_tipo_ofert_exclu
                     WHERE tofe_oid_tipo_ofer = c.tofe_oid_tipo_ofer)),
           ped.num_unid_por_aten_tota        =
           (SELECT SUM(b.num_unid_por_aten)
              FROM ped_solic_posic b
             WHERE b.soca_oid_soli_cabe = ped.oid_soli_cabe),
           ped.val_tasa_impu                  = ln_valtasa,
           ped.val_tasa_flet                  = ln_valtasaflet,
           ped.taim_oid_tasa_impu             = ln_oidtasa,
           ped.val_impo_flet_tota_loca        = ped.val_impo_flet_loca+nvl(ped.val_reca_flet_loca,0),
           ped.val_impo_flet_tota_docu        = ped.val_impo_flet_loca+nvl(ped.val_reca_flet_loca,0)
     WHERE ped.soca_oid_soli_cabe = p_oidcons;


    if ls_ICENC='1' then
    UPDATE ped_solic_posic c
       SET c.imp_impu_tota_prod_naci  = (select VAL_IMPU_PROD_NACI from (select x.VAL_IMPU_PROD_NACI, x.prod_oid_prod from INT_IMPUE_PRODU_NACIO x order by x.fec_carg desc) where prod_oid_prod=c.prod_oid_prod and rownum=1)*c.num_unid_aten
     WHERE soca_oid_soli_cabe IN (SELECT ped.oid_soli_cabe
                                    FROM ped_solic_cabec     cons,
                                         ped_solic_cabec     ped,
                                         ped_tipo_solic_pais ptsp,
                                         ped_tipo_solic      pts
                                   WHERE cons.oid_soli_cabe = ped.soca_oid_soli_cabe
                                     AND cons.tspa_oid_tipo_soli_pais = ptsp.oid_tipo_soli_pais
                                     AND ptsp.tsol_oid_tipo_soli = pts.oid_tipo_soli
                                     AND pts.ind_cons = 1
                                     --AND pts.ind_soli_nega = 0
                                     AND cons.oid_soli_cabe = p_oidcons);


    end if;


    UPDATE ped_solic_cabec cons
       SET cons.val_base_flet_loca     =
           (SELECT MAX(nvl(a.val_base_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_base_flet_docu     =
           (SELECT MAX(nvl(a.val_base_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_loca     =
           (SELECT MAX(nvl(a.val_impo_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_flet_loca     =
           (SELECT MAX(nvl(a.val_impo_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_docu     =
           (SELECT MAX(nvl(a.val_impo_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_tota_loca =
           (SELECT MAX(nvl(a.val_impo_flet_tota_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_tota_docu =
           (SELECT MAX(nvl(a.val_impo_flet_tota_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_reca_flet_loca     =
           (SELECT MAX(nvl(a.val_reca_flet_loca,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_reca_flet_docu     =
           (SELECT MAX(nvl(a.val_reca_flet_docu,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi     =
           (SELECT SUM(nvl(a.val_tota_gast_admi,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi2     =
           (SELECT SUM(nvl(a.val_tota_gast_admi2,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_comi_flex     =
           (SELECT SUM(nvl(a.val_tota_comi_flex,
                           0))
              FROM ped_solic_cabec a
             WHERE a.soca_oid_soli_cabe = cons.oid_soli_cabe)
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_prec_cata_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cata_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0)))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_sin_impu_tota_do =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0)))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_sin_impu_tota   =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_loca,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_loca,
                                  0),
                              0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_sin_impu_tota_1 =
           (SELECT SUM(decode(nvl(b.val_prec_cata_unit_docu,
                                  0),
                              0,
                              nvl(b.val_prec_sin_impu_tota_docu,
                                  0),
                              0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_fact_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_fact_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_fact_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cont_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_cont_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_cata_tota_loc_uni_dem =
           (SELECT SUM(nvl(b.val_prec_cata_unit_loca,
                           0) * num_unid_dema)
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_neto_tota_loca       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_prec_neto_tota_docu       =
           (SELECT SUM(nvl(b.val_prec_neto_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_flet_sin_impu_tota    = round(nvl(cons.val_impo_flet_tota_loca,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_impo_desc_1_sin_impu_tota =
           (SELECT SUM(round(nvl(b.val_impo_desc_tota_loca,
                                 0) / (1 + (ln_valtasa / 100)),
                             ln_decim))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_1_tota_loca     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_1_tota_docu     =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_loca,
                           0) + nvl(b.val_prec_cont_tota_loca,
                                    0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_desc_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_desc_tota_docu,
                           0) + nvl(b.val_prec_cont_tota_docu,
                                    0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_impo_impu_tota_loca       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_loca,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe) +
           (cons.val_impo_flet_tota_loca -
           round(cons.val_impo_flet_tota_loca / (1 + (ln_valtasaflet / 100)),
                  2)) +
           (cons.val_tota_gast_admi - round(cons.val_tota_gast_admi / (1 + (ln_valtasaflet / 100)),
                                            2))+
           (cons.val_tota_gast_admi2 - round(cons.val_tota_gast_admi2 / (1 + (ln_valtasaflet / 100)),
                                            2)),
           cons.val_impo_impu_tota_docu       =
           (SELECT SUM(nvl(b.val_impo_impu_tota_docu,
                           0))
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe) +
           (cons.val_impo_flet_tota_loca -
           round(cons.val_impo_flet_tota_loca / (1 + (ln_valtasaflet / 100)),
                  2)) +
           (cons.val_tota_gast_admi - round(cons.val_tota_gast_admi / (1 + (ln_valtasaflet / 100)),
                                            2))+
           (cons.val_tota_gast_admi2 - round(cons.val_tota_gast_admi2 / (1 + (ln_valtasaflet / 100)),
                                            2)),
           cons.num_unid_aten_tota            =
           (SELECT SUM(b.num_unid_aten)
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.num_unid_por_aten_tota        =
           (SELECT SUM(b.num_unid_por_aten)
              FROM ped_solic_cabec a,
                   ped_solic_posic b
             WHERE a.oid_soli_cabe = b.soca_oid_soli_cabe
               AND a.soca_oid_soli_cabe = cons.oid_soli_cabe),
           cons.val_tota_gast_admi_sin_impu    = round(nvl(cons.val_tota_gast_admi,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_gast_admi2_sin_impu    = round(nvl(cons.val_tota_gast_admi2,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_impu_gast_admi        = nvl(cons.val_tota_gast_admi,
                                                     0) -
                                                 round(nvl(cons.val_tota_gast_admi,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_impu_gast2_admi        = nvl(cons.val_tota_gast_admi2,
                                                     0) -
                                                 round(nvl(cons.val_tota_gast_admi2,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tota_comi_flex_sin_impu    = round(nvl(cons.val_tota_comi_flex,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_impo_impu_comi_flex        = nvl(cons.val_tota_comi_flex,
                                                     0) -
                                                 round(nvl(cons.val_tota_comi_flex,
                                                           0) / (1 + (ln_valtasaflet / 100)),
                                                       ln_decim),
           cons.val_tasa_impu                  = ln_valtasa,
           cons.val_tasa_flet                  = ln_valtasaflet,
           cons.taim_oid_tasa_impu             = ln_oidtasa
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = nvl(cons.val_prec_cata_tota_loca,
                                         0) + nvl(cons.val_prec_cont_tota_loca,
                                                  0) - nvl(cons.val_impo_desc_tota_loca,
                                                           0) + nvl(cons.val_impo_flet_tota_loca,
                                                                    0) +
                                     nvl(cons.val_tota_gast_admi,
                                         0)+
                                     nvl(cons.val_tota_gast_admi2,
                                         0) + nvl(cons.val_tota_comi_flex,
                                                  0)
     WHERE cons.oid_soli_cabe = p_oidcons;


if ln_imp_estatal is not null then

   begin
   select y.val_tasa_impu into ln_imp_estatal_temp
   from fac_tipos_impue_ubige x, ped_tasa_impue y, ped_solic_cabec z
   where x.taim_oid_tasa_impu=y.oid_tasa_impu
   and x.vepo_oid_valo_estr_geop=z.zzon_oid_zona
   and z.oid_soli_cabe=p_oidcons
   ;

   if ln_imp_estatal_temp is not null then
      ln_imp_estatal:=ln_imp_estatal_temp;
   end if;


   exception when others then
        NULL;
   end;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = (cons.val_prec_cata_tota_loca+nvl(cons.val_impo_flet_tota_loca,0))*(ln_imp_estatal/100), val_impo_redo_loca=0
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca+cons.val_impo_impu_tota_loca
     WHERE cons.oid_soli_cabe = p_oidcons;

end if;

if ln_desc_flete ='S' then

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_desc_flet = round(cons.val_impo_flet_tota_loca*(ln_valtasa/100),ln_decim)
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = cons.val_impo_impu_tota_loca-cons.val_impo_desc_flet
     WHERE cons.oid_soli_cabe = p_oidcons;

    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca-cons.val_impo_desc_flet
     WHERE cons.oid_soli_cabe = p_oidcons;

end if;


    UPDATE ped_solic_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca-cons.val_impo_desc_3_tota_loca
     WHERE cons.oid_soli_cabe = p_oidcons
     and nvl(cons.val_impo_desc_3_tota_loca,0)<>0
     ;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_desc_3_sin_impu_tota = round(nvl(cons.val_impo_desc_3_tota_loca,
                                                           0) / (1 + (ln_valtasa / 100)),
                                                       ln_decim)
     WHERE cons.oid_soli_cabe = p_oidcons
     and nvl(cons.val_impo_desc_3_tota_loca,0)<>0
     ;


    UPDATE ped_solic_cabec cons
       SET cons.val_impo_impu_tota_loca = cons.val_impo_impu_tota_loca-(cons.val_impo_desc_3_tota_loca-val_impo_desc_3_sin_impu_tota)
     WHERE cons.oid_soli_cabe = p_oidcons
     and nvl(cons.val_impo_desc_3_tota_loca,0)<>0
     ;

    UPDATE ped_solic_cabec cons
       SET cons.val_impo_redo_loca =
           (nvl(cons.val_prec_cata_sin_impu_tota,
                0) - nvl(cons.val_impo_desc_1_sin_impu_tota,
                          0) + nvl(cons.val_impo_impu_tota_loca,
                                    0) + nvl(cons.val_impo_flet_sin_impu_tota,
                                              0)+ nvl(cons.val_tota_gast_admi_sin_impu,
                                              0)+ nvl(cons.val_tota_gast_admi2_sin_impu,
                                              0)
                                              ) - nvl(cons.val_tota_paga_loca,
                                                        0) -nvl(cons.val_impo_desc_3_sin_impu_tota,0),
           cons.val_impo_redo_docu = nvl(cons.val_impo_redo_loca,
                                         0)
     WHERE cons.oid_soli_cabe = p_oidcons;

/*    DELETE FROM ped_solic_acumu_impue psci WHERE psci.soca_oid_soli_cabe = p_oidcons;

    INSERT INTO ped_solic_acumu_impue
      (oid_acum,
       taim_oid_tasa_impu,
       soca_oid_soli_cabe,
       val_base_impo_posi_loca,
       val_tota_prec_posi_loca,
       val_tota_desc_cabe_loca,
       val_base_impo_desc_cabe,
       val_base_impo_prec_cont,
       val_base_impo_flet_loca,
       val_tota_base_impo_loca,
       val_tota_impu_loca,
       val_base_impo_posi_docu,
       val_tota_impo_loca,
       val_tota_prec_posi_docu,
       val_tota_desc_cabe_docu,
       val_base_impo_desc_cabe_docu,
       val_base_impo_prec_cont_docu,
       val_base_impo_flet_docu,
       val_tota_base_impo_docu,
       val_tota_impu_docu,
       val_tota_impo_docu)
    VALUES
      (p_oidcons,
       ln_oidtasa,
       p_oidcons,
       nvl((SELECT x.val_prec_neto_tota_loca + x.val_prec_cont_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       0,
       0,
       0,
       nvl((SELECT x.val_prec_cont_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_impo_flet_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_prec_neto_tota_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_impo_impu_tota_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_prec_neto_tota_docu + x.val_prec_cont_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_tota_paga_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       0,
       0,
       0,
       nvl((SELECT x.val_prec_cont_sin_impu_tota
             FROM ped_solic_cabec x
            WHERE oid_soli_cabe = p_oidcons),
           0),
       0,
       nvl((SELECT x.val_prec_neto_tota_docu FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_impo_impu_tota_docu FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0),
       nvl((SELECT x.val_tota_paga_loca FROM ped_solic_cabec x WHERE oid_soli_cabe = p_oidcons),
           0));*/

    --commit;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_calculo_consolidado_3r: ' || ls_sqlerrm || ' p_oidcons:' ||
                              p_oidcons);
   END fac_pr_calculo_consolidado3_r;
  /***************************************************************************
  Descripcion       : Genera Documentos Legales
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_doclega(p_oidcons NUMBER) IS
      ln_decim          NUMBER(3);
      ln_valgratis      NUMBER(12,2);

    CURSOR c_doclega IS
      SELECT DISTINCT a.fec_fact,
                      a.sbac_oid_sbac,
                      a.soci_oid_soci,
                      a.pais_oid_pais,
                      a.val_tasa_impu,
                      tido_oid_tipo_docu,
                      f.cod_tipo_prog,
                      d.oid_clie_dire,
                      d.val_obse,
                      e.num_docu_iden,
                      e.tdoc_oid_tipo_docu,
                      a.clie_oid_clie,
                      a.tspa_oid_tipo_soli_pais_cons,
                      d.val_nomb_via || ' ' || d.num_ppal || ' ' || d.val_obse dire,
                      c.val_nom1,
                      c.val_nom2,
                      c.val_ape1,
                      c.val_ape2,
                      g.zorg_oid_regi,
                      a.zzon_oid_zona,
                      a.ztad_oid_terr_admi,
                      k.zsgv_oid_subg_vent,
                      h.oid_terr,
                      j.oid_secc,
                      a.perd_oid_peri,
                      a.fopa_oid_form_pago,
                      a.ictp_oid_tipo_prog,/*
                      nvl(a.val_impo_flet_tota_loca,0) val_impo_flet_tota_loca,
                      nvl(a.val_impo_flet_sin_impu_tota,0) val_impo_flet_sin_impu_tota,
                      nvl(a.val_tota_gast_admi,0) val_tota_gast_admi,
                      nvl(a.val_tota_gast_admi_sin_impu,0) val_tota_gast_admi_sin_impu,
                      nvl(a.val_tota_impu_gast_admi,0) val_tota_impu_gast_admi,
                      nvl(a.val_tota_comi_flex,0) val_tota_comi_flex,
                      nvl(a.val_tota_comi_flex_sin_impu,0) val_tota_comi_flex_sin_impu,
                      nvl(a.val_impo_impu_comi_flex,0) val_impo_impu_comi_flex,*/
                      nvl(a.val_impo_iva_asum_empr,0) val_impo_iva_asum_empr,
                      --, a.val_tota_gast_admi
                      --, a.val_tota_gast_admi_sin_impu
                      --, a.val_tota_impu_gast_admi
                      --a.val_impo_desc_1_sin_impu_tota,
                      --a.val_impo_impu_tota_loca,
                      trunc((SELECT COUNT(1)
                               FROM ped_solic_cabec x,
                                    ped_solic_posic y
                              WHERE x.oid_soli_cabe = y.soca_oid_soli_cabe
                                AND x.soca_oid_soli_cabe = a.soca_oid_soli_cabe
                                AND x.tido_oid_tipo_docu = a.tido_oid_tipo_docu
                                AND y.ind_no_impr = 0
                                AND y.num_unid_aten <> 0) --num_posic
                            / (SELECT DISTINCT num_line
                                 FROM fac_formu            ff,
                                      fac_formu_tipo_solic ffts
                                WHERE ff.oid_form = ffts.fors_oid_form
                                  AND ffts.tspa_oid_tipo_soli_pais = a.tspa_oid_tipo_soli_pais_cons
                                  AND ff.tido_oid_tipo_docu = a.tido_oid_tipo_docu),
                            0) + case when mod((SELECT COUNT(1)
                               FROM ped_solic_cabec x,
                                    ped_solic_posic y
                              WHERE x.oid_soli_cabe = y.soca_oid_soli_cabe
                                AND x.soca_oid_soli_cabe = a.soca_oid_soli_cabe
                                AND x.tido_oid_tipo_docu = a.tido_oid_tipo_docu
                                AND y.ind_no_impr = 0
                                AND y.num_unid_aten <> 0) --num_posic
                            , (SELECT DISTINCT num_line
                                 FROM fac_formu            ff,
                                      fac_formu_tipo_solic ffts
                                WHERE ff.oid_form = ffts.fors_oid_form
                                  AND ffts.tspa_oid_tipo_soli_pais = a.tspa_oid_tipo_soli_pais_cons
                                  AND ff.tido_oid_tipo_docu = a.tido_oid_tipo_docu))=0 then 0
                                  else
                                  1 end num_lineas,
                      (SELECT DISTINCT ff.oid_form
                         FROM fac_formu            ff,
                              fac_formu_tipo_solic ffts
                        WHERE ff.oid_form = ffts.fors_oid_form
                          AND ffts.tspa_oid_tipo_soli_pais = a.tspa_oid_tipo_soli_pais_cons
                          AND ff.tido_oid_tipo_docu = a.tido_oid_tipo_docu) oid_form
        FROM ped_solic_cabec     a,
             mae_clien           c,
             mae_clien_direc     d,
             mae_clien_ident     e,
             inc_concu_tipo_prog f,
             zon_zona            g,
             zon_terri           h,
             zon_terri_admin     i,
             zon_secci           j,
             zon_regio           k
       WHERE a.soca_oid_soli_cabe = p_oidcons
         AND a.clie_oid_clie = c.oid_clie
         AND a.ztad_oid_terr_admi = i.oid_terr_admi
         AND i.terr_oid_terr = h.oid_terr
         AND i.zscc_oid_secc = j.oid_secc
         AND i.terr_oid_terr = h.oid_terr
         AND j.zzon_oid_zona = g.oid_zona
         AND g.zorg_oid_regi = k.oid_regi
         --AND i.ind_borr = 0
         AND a.ictp_oid_tipo_prog = f.oid_tipo_prog(+)
         AND c.oid_clie = d.clie_oid_clie
         AND d.ind_dire_ppal = 1
         AND d.ind_elim = 0
         AND c.oid_clie = e.clie_oid_clie
         AND e.val_iden_docu_prin = 1
         AND a.num_unid_aten_tota <> 0
         AND NOT EXISTS
       (SELECT 1 FROM fac_docum_conta_cabec WHERE soca_oid_soli_cabe = p_oidcons)
       order by a.tido_oid_tipo_docu asc
       ;

    r_doclega c_doclega%ROWTYPE;

    CURSOR c_posic
    (
      p_tido     NUMBER,
      p_tspacons NUMBER
    ) IS
      SELECT val_codi_vent,
             num_unid_aten,
             oid_soli_posi,
             prod_oid_prod,
             val_porc_desc,
             ind_dent_fuer_caja_bols,
             cod_cata,
             rownum cod_posi,
             val_prec_cata_unit_loca,
             val_prec_cont_unit_loca,
             val_prec_cata_unit_docu,
             val_prec_conta_unit_docu,
             val_prec_fact_unit_loca,
             val_prec_fact_unit_docu,
             val_prec_sin_impu_unit_loca,
             val_prec_sin_impu_unit_docu,
             val_prec_sin_impu_tota_docu,
             val_prec_sin_impu_tota_loca,
             val_prec_neto_unit_loca,
             val_prec_neto_unit_docu,
             val_prec_tota_tota_loca,
             val_prec_tota_tota_docu,
             val_prec_neto_tota_loca,
             val_prec_neto_tota_docu,
             val_prec_fact_tota_loca,
             val_prec_fact_tota_docu,
             val_impo_desc_unit_loca,
             val_impo_desc_unit_docu,
             val_impo_impu_unit_loca,
             val_impo_impu_unit_docu,
             val_impo_impu_tota_loca,
             val_impo_impu_tota_docu,
             val_impo_desc_tota_loca,
             val_impo_desc_tota_docu,
             val_impo_des_sin_imp_unit_loca,
             val_impo_des_sin_imp_unit_docu,
             val_impo_des_sin_imp_tota,
             val_impo_des_sin_imp_tota_docu,
             val_prec_cata_tota_loca,
             val_prec_cata_tota_docu,
             val_prec_cont_tota_loca,
             val_prec_cont_tota_docu,
             imp_impu_tota_prod_naci,
             imp_iva_impu_tota_prod_naci
        FROM (SELECT b.val_codi_vent,
                     b.num_unid_aten,
                     b.oid_soli_posi,
                     b.prod_oid_prod,
                     b.val_porc_desc,
                     b.ind_dent_fuer_caja_bols,
                     (SELECT cod_cata
                        FROM pre_ofert_detal x,
                             pre_catal       y
                       WHERE x.oid_deta_ofer = b.ofde_oid_deta_ofer
                         AND x.ocat_oid_catal = y.oid_cata) cod_cata --, b.cod_posi
                    ,
                     b.val_prec_cata_unit_loca,
                     b.val_prec_cont_unit_loca,
                     b.val_prec_cata_unit_docu,
                     b.val_prec_conta_unit_docu,
                     b.val_prec_fact_unit_loca,
                     b.val_prec_fact_unit_docu,
                     b.val_prec_sin_impu_unit_loca,
                     b.val_prec_sin_impu_unit_docu,
                     b.val_prec_sin_impu_tota_docu,
                     b.val_prec_sin_impu_tota_loca,
                     b.val_prec_neto_unit_loca,
                     b.val_prec_neto_unit_docu,
                     b.val_prec_tota_tota_loca,
                     b.val_prec_tota_tota_docu,
                     b.val_prec_neto_tota_loca,
                     b.val_prec_neto_tota_docu,
                     b.val_prec_fact_tota_loca,
                     b.val_prec_fact_tota_docu,
                     b.val_impo_desc_unit_loca,
                     b.val_impo_desc_unit_docu,
                     b.val_impo_impu_unit_loca,
                     b.val_impo_impu_unit_docu,
                     b.val_impo_impu_tota_loca,
                     b.val_impo_impu_tota_docu,
                     b.val_impo_desc_tota_loca,
                     b.val_impo_desc_tota_docu,
                     b.val_impo_des_sin_imp_unit_loca,
                     b.val_impo_des_sin_imp_unit_docu,
                     b.val_impo_des_sin_imp_tota,
                     b.val_impo_des_sin_imp_tota_docu,
                     b.val_prec_cata_tota_loca,
                     b.val_prec_cata_tota_docu,
                     b.val_prec_cont_tota_loca,
                     b.val_prec_cont_tota_docu,
                     b.imp_impu_tota_prod_naci,
                     b.imp_iva_impu_tota_prod_naci
                FROM ped_solic_cabec a,
                     ped_solic_posic b
                     , pre_ofert_detal c
                    ,
                     mae_produ d
                   , pre_ofert e
                    ,
                     gen_i18n_sicc_pais f
               WHERE a.soca_oid_soli_cabe = p_oidcons
                 AND a.tido_oid_tipo_docu = p_tido
                 AND a.oid_soli_cabe = b.soca_oid_soli_cabe
                 and b.ofde_oid_deta_ofer=c.oid_deta_ofer(+)
                 and c.ofer_oid_ofer=e.oid_ofer(+)
                 AND b.espo_oid_esta_posi <> 2
                 AND b.num_unid_aten <> 0
                 AND b.ind_no_impr = 0
                    --and c.ocat_oid_catal=e.oid_cata(+)
                 AND d.oid_prod = f.val_oid
                 AND f.attr_enti = 'MAE_PRODU'
                 AND b.prod_oid_prod = d.oid_prod
                 AND NOT EXISTS (SELECT 1
                        FROM fac_docum_conta_cabec,
                             fac_docum_conta_linea
                       WHERE oid_cabe = dcca_oid_cabe
                         AND soca_oid_soli_cabe = p_oidcons
                         AND sopo_oid_soli_posi = b.oid_soli_posi)
               ORDER BY e.coes_oid_estr,
                        e.num_ofer,
                        b.val_prec_cata_unit_loca asc,
                        b.val_codi_vent)
       WHERE rownum <= (SELECT DISTINCT num_line
                          FROM fac_formu            ff,
                               fac_formu_tipo_solic ffts
                         WHERE ff.oid_form = ffts.fors_oid_form
                           AND ffts.tspa_oid_tipo_soli_pais = p_tspacons
                           AND ff.tido_oid_tipo_docu = p_tido);

    r_posic c_posic%ROWTYPE;

    ln_valtasa     NUMBER(6,4);
    ln_valtasaflet NUMBER(2);
    ln_posic       NUMBER(4);
    ln_oidcabe     NUMBER(10);
    ln_numinte     NUMBER(10);
    lv_serie     VARCHAR2(10);
    --w_filas        NUMBER(12);

    ln_rete       VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'porcentajeRetencion'),10);

    ln_imp_inte       NUMBER:=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'impuestoIntereses'),0);

    ln_desc_tota   NUMBER:=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'descuentoTotal'),0);


    ln_cree       VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'porcentajeCree'),0);


    ln_iva       VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'ivaAsumeEmpresa'),0);

    ln_ajuste    VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'ivaAjuste2'),'N');

    ln_imp_estatal NUMBER(12,2):=TO_NUMBER(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'impuestoEstatal'))/100;

    ln_imp_estatal_temp NUMBER(12,2);


    ln_desc_flete VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'descFlete'),'N');

    ln_num_docu_inte VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indNumDocuInte'),'S');

    ls_ind_num_iden_fisc VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'indNumIdenFisc'),'N');

    ls_num_iden_fisc VARCHAR2(100);

    ls_num_iden_nnal VARCHAR2(100);

    ls_ind_fisc VARCHAR2(30);


  BEGIN

    SELECT a.num_deci
      INTO ln_decim
      FROM seg_moned a,
           seg_pais  b
     WHERE b.mone_oid_mone = a.oid_mone
       AND b.oid_pais = (SELECT pais_oid_pais FROM ped_solic_cabec WHERE oid_soli_cabe = p_oidcons);

    OPEN c_doclega;
    LOOP
      FETCH c_doclega
        INTO r_doclega;
      EXIT WHEN c_doclega%NOTFOUND;

      FOR i IN 1 .. r_doclega.num_lineas
      LOOP
        SELECT fac_dcca_seq.nextval INTO ln_oidcabe FROM dual;

        select a.val_ulti_nume_docu_inte, a.val_seri_docu_lega
        into ln_numinte, lv_serie
        from fac_docum_subac a where a.sbac_oid_sbac=888
        and a.tido_oid_tipo_docu=r_doclega.tido_oid_tipo_docu
        and rownum=1;
        
        ls_num_iden_fisc:=r_doclega.num_docu_iden;
        
        ls_num_iden_nnal:=r_doclega.num_docu_iden;
        
        if ls_ind_num_iden_fisc='S' then
           select mtd.val_sigl
           into ls_ind_fisc 
           from mae_tipo_docum mtd
           where mtd.oid_tipo_docu=r_doclega.tdoc_oid_tipo_docu;
           
           if ls_ind_fisc='NIT' then
              begin
                select num_docu_iden into ls_num_iden_nnal
                from mae_clien_ident mci
                where mci.clie_oid_clie=r_doclega.clie_oid_clie
                and mci.tdoc_oid_tipo_docu<>r_doclega.tdoc_oid_tipo_docu
                and rownum=1                ;
              exception when others then
                ls_num_iden_nnal:=r_doclega.num_docu_iden;        
              end;
           else
              begin
                select num_docu_iden into ls_num_iden_fisc
                from mae_clien_ident mci
                where mci.clie_oid_clie=r_doclega.clie_oid_clie
                and exists (select 1 from mae_tipo_docum where oid_tipo_docu=mci.tdoc_oid_tipo_docu and val_sigl='NIT')
                and rownum=1                ;
              exception when others then
                ls_num_iden_fisc:=null;        
              end;
           end if;
        
        end if;

        INSERT INTO fac_docum_conta_cabec
          (oid_cabe,
           pais_oid_pais,
           soci_oid_soci,
           sbac_oid_sbac,
           tido_oid_tipo_docu,
           val_ejer_docu_inte,
           num_docu_cont_inte,
           val_esta,
           fec_emis,
           fec_fact,
           val_seri_docu_lega,
           num_docu_lega,
           val_nume_iden_fisc,
           val_nume_iden_nnal,
           val_obse,
           val_nom1,
           val_nom2,
           val_ape1,
           val_ape2,
           zorg_oid_regi,
           zsgv_oid_subg_vent,
           zzon_oid_zona,
           terr_oid_terr,
           cldi_oid_clie_dire,
           perd_oid_peri,
           fopa_oid_form_pago,
           soca_oid_soli_cabe,
           fors_oid_form,
           zscc_oid_secc,
           imp_flet_tota_loca,
           imp_flet_impu_tota_loca,
           val_tipo_dire,
           val_tipo_camb,
           ind_impr,
           imp_des3_total_loca,
           imp_flet_tota_docu,
           imp_des3_tota_docu,
           val_prec_cata_impu,
           val_prec_cont_impu,
           val_prec_cata_tota_loca_unid,
           val_prec_cata_tota_loca,
           val_prec_cont_tota_loca,
           imp_des1_tota_loca,
           imp_des1_impu,
           val_prec_fact_tota_loca,
           val_prec_tota_tota_loca,
           imp_des3_impu,
           imp_desc_tota_loca,
           val_prec_neto_tota_loca,
           imp_impu_tota_loca,
           val_tota_paga_loca,
           val_tota_paga_docu,
           imp_redo_loca,
           imp_redo_cons_loca,
           num_unid_aten_tota,
           val_prec_cata_tota_docu,
           val_prec_cata_sin_impu_tota,
           val_prec_cont_tota_docu,
           val_prec_cont_sin_impu_tota,
           imp_des1_tota_docu,
           imp_des3_sin_impu_tota,
           imp_desc_tota_docu,
           val_prec_fact_tota_docu,
           imp_impu_tota_docu,
           val_prec_tota_tota_docu,
           val_prec_neto_tota_docu,
           imp_flet_sin_impu_tota_docu,
           imp_redo_cons_docu,
           imp_redo_docu,
           num_lote_cont,
           fec_cont,
           val_dire_comp,
           val_impo_iva_asum_empr,
           val_tota_gast_admi,
           val_tota_gast_admi_sin_impu,
           val_tota_impu_gast_admi,
           val_tota_comi_flex,
           val_tota_comi_flex_sin_impu,
           val_impo_impu_comi_flex,
           imp_des1_sin_impu_tota,
           ICTP_OID_TIPO_PROG,
           val_tota_gast_admi2,
           val_tota_gast_admi2_sin_impu,
           val_tota_impu_gast_admi2
           )
        VALUES
          (ln_oidcabe,
           r_doclega.pais_oid_pais,
           r_doclega.soci_oid_soci,
           r_doclega.sbac_oid_sbac,
           r_doclega.tido_oid_tipo_docu,
           to_char(r_doclega.fec_fact,
                   'yy'),
           decode(ln_num_docu_inte,'S',ln_numinte+i,NULL),
           1,
           SYSDATE,
           r_doclega.fec_fact,
           lv_serie,
           NULL,
           ls_num_iden_fisc,
           ls_num_iden_nnal,
           '',
           r_doclega.val_nom1,
           r_doclega.val_nom2,
           r_doclega.val_ape1,
           r_doclega.val_ape2,
           r_doclega.zorg_oid_regi,
           r_doclega.zsgv_oid_subg_vent,
           r_doclega.zzon_oid_zona,
           r_doclega.oid_terr,
           r_doclega.oid_clie_dire,
           r_doclega.perd_oid_peri,
           r_doclega.fopa_oid_form_pago,
           p_oidcons,
           r_doclega.oid_form,
           r_doclega.oid_secc,
           decode(i,
                  1,
                  1,
                  0), --IMP_FLET_TOTA_LOCA
           decode(i,
                  1,
                  1,
                  0), --IMP_FLET_TOTA_LOCA
           '01',
           1,
           1,
           decode(i,
                  1,
                  1,
                  0), --IMP_DESC3_TOTA_LOCA
           decode(i,
                  1,
                  1,
                  0), --IMP_FLET_TOTA_DOCU
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,--r_doclega.val_impo_impu_tota_loca,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,
           0,--r_doclega.val_impo_impu_tota_loca,
           0,
           0,
           0,
           0,
           0,
           NULL,
           NULL,
           r_doclega.dire,
           r_doclega.val_impo_iva_asum_empr,
           decode(i,
                  1,
                  1,
                  0),
           decode(i,
                  1,
                  1,
                  0),
           decode(i,
                  1,
                  1,
                  0),
           decode(i,
                  1,
                  1,
                  0),
           decode(i,
                  1,
                  1,
                  0),
           decode(i,
                  1,
                  1,
                  0),
                  0,
                  r_doclega.ictp_oid_tipo_prog,
           decode(i,
                  1,
                  1,
                  0),
           decode(i,
                  1,
                  1,
                  0),
           decode(i,
                  1,
                  1,
                  0)
                  );

        OPEN c_posic(r_doclega.tido_oid_tipo_docu,
                     r_doclega.tspa_oid_tipo_soli_pais_cons);
        LOOP
          FETCH c_posic
            INTO r_posic;
          EXIT WHEN c_posic%NOTFOUND;

          INSERT INTO fac_docum_conta_linea
            (dcca_oid_cabe,
             num_unid_aten,
             sopo_oid_soli_posi,
             prod_oid_prod,
             val_prec_tota_tota_docu,
             val_prec_neto_tota_docu,
             imp_impu_tota_docu,
             imp_desc_sin_impu_tota_docu,
             imp_desc_tota_docu,
             val_prec_fact_tota_docu,
             val_prec_cata_tota_docu,
             val_prec_cont_tota_docu,
             val_prec_tota_unit_docu,
             imp_impu_unit_docu,
             val_prec_neto_unit_docu,
             imp_desc_sin_impu_unit_docu,
             imp_desc_unit_docu,
             val_porc_desc,
             val_prec_sin_impu_unit_docu,
             val_prec_fact_unit_docu,
             val_prec_cont_unit_docu,
             val_prec_cata_unit_docu,
             val_prec_tota_tota_loca,
             imp_impu_tota_loca,
             val_prec_neto_tota_loca,
             imp_desc_sin_impu_tota_loca,
             imp_desc_tota_loca,
             val_prec_sin_impu_tota_loca,
             val_prec_fact_tota_loca,
             val_prec_cata_loca_unid_dema,
             val_prec_cata_tota_loca,
             val_prec_cont_tota_loca,
             val_prec_tota_unit_loca,
             imp_impu_unit_loca,
             val_prec_neto_unit_loca,
             imp_desc_sin_impu_unit,
             imp_desc_unit_loca,
             val_prec_sin_impu_unit,
             val_prec_fact_unit_loca,
             val_prec_cont_unit_loca,
             val_prec_cata_unit_loca,
             val_prec_sin_impu_tota_docu,
             num_linea,
             ind_no_impr,
             val_cata,
             OID,
             ind_dent_fuer_caja_bols,
             imp_impu_tota_prod_naci,
             imp_iva_impu_tota_prod_naci
             )
          VALUES
            (ln_oidcabe,
             r_posic.num_unid_aten,
             r_posic.oid_soli_posi,
             r_posic.prod_oid_prod,
             r_posic.val_prec_tota_tota_docu,
             r_posic.val_prec_neto_tota_docu,
             r_posic.val_impo_impu_tota_docu,
             r_posic.val_impo_des_sin_imp_tota_docu,
             r_posic.val_impo_desc_tota_docu,
             r_posic.val_prec_fact_tota_docu,
             r_posic.val_prec_cata_tota_docu,
             r_posic.val_prec_cont_tota_docu,
             0,
             r_posic.val_impo_impu_unit_docu,
             r_posic.val_prec_neto_unit_docu,
             r_posic.val_impo_des_sin_imp_unit_docu,
             r_posic.val_impo_desc_unit_docu,
             r_posic.val_porc_desc,
             r_posic.val_prec_sin_impu_unit_loca,
             r_posic.val_prec_fact_unit_docu,
             r_posic.val_prec_conta_unit_docu,
             r_posic.val_prec_cata_unit_docu,
             r_posic.val_prec_tota_tota_loca,
             r_posic.val_impo_impu_tota_loca,
             r_posic.val_prec_neto_tota_loca,
             r_posic.val_impo_des_sin_imp_tota,
             r_posic.val_impo_desc_tota_loca,
             r_posic.val_prec_sin_impu_tota_loca,
             r_posic.val_prec_fact_tota_loca,
             0,
             r_posic.val_prec_cata_tota_loca,
             r_posic.val_prec_cont_tota_loca,
             0,
             r_posic.val_impo_impu_unit_loca,
             r_posic.val_prec_neto_unit_loca,
             r_posic.val_impo_des_sin_imp_unit_loca,
             r_posic.val_impo_desc_unit_loca,
             r_posic.val_prec_sin_impu_unit_loca,
             r_posic.val_prec_fact_unit_loca,
             r_posic.val_prec_cont_unit_loca,
             r_posic.val_prec_cata_unit_loca,
             r_posic.val_prec_sin_impu_tota_docu,
             r_posic.cod_posi,
             0,
             r_posic.cod_cata,
             fac_dcli_seq.nextval,
             r_posic.ind_dent_fuer_caja_bols,
             r_posic.imp_impu_tota_prod_naci,
             r_posic.imp_iva_impu_tota_prod_naci);
        END LOOP;
        CLOSE c_posic;

        UPDATE fac_docum_conta_cabec cab
           SET cab.val_tota_gast_admi          = decode(cab.val_tota_gast_admi,
                                                        1,
                                                        (SELECT nvl(x.val_tota_gast_admi,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.val_tota_gast_admi2          = decode(cab.val_tota_gast_admi2,
                                                        1,
                                                        (SELECT nvl(x.val_tota_gast_admi2,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.val_tota_comi_flex          = decode(cab.val_tota_comi_flex,
                                                        1,
                                                        (SELECT nvl(x.val_tota_comi_flex,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.val_tota_gast_admi_sin_impu = decode(cab.val_tota_gast_admi_sin_impu,
                                                        1,
                                                        (SELECT nvl(x.val_tota_gast_admi_sin_impu,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.val_tota_gast_admi2_sin_impu = decode(cab.val_tota_gast_admi2_sin_impu,
                                                        1,
                                                        (SELECT nvl(x.val_tota_gast_admi2_sin_impu,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.val_tota_comi_flex_sin_impu = decode(cab.val_tota_comi_flex_sin_impu,
                                                        1,
                                                        (SELECT nvl(x.val_tota_comi_flex_sin_impu,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.val_tota_impu_gast_admi     = decode(cab.val_tota_impu_gast_admi,
                                                        1,
                                                        (SELECT nvl(x.val_tota_impu_gast_admi,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.val_tota_impu_gast_admi2     = decode(cab.val_tota_impu_gast_admi2,
                                                        1,
                                                        (SELECT nvl(x.val_tota_impu_gast2_admi,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.val_impo_impu_comi_flex     = decode(cab.val_impo_impu_comi_flex,
                                                        1,
                                                        (SELECT nvl(x.val_impo_impu_comi_flex,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.imp_flet_tota_loca          = decode(cab.imp_flet_tota_loca,
                                                        1,
                                                        (SELECT nvl(x.val_impo_flet_tota_loca,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.imp_flet_impu_tota_loca     = decode(cab.imp_flet_tota_loca,
                                                        1,
                                                        (SELECT nvl(x.val_impo_flet_sin_impu_tota,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.imp_flet_sin_impu_tota_docu  = decode(cab.imp_flet_tota_loca,
                                                        1,
                                                        (SELECT nvl(x.val_impo_flet_sin_impu_tota,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.imp_flet_tota_docu          = decode(cab.imp_flet_tota_docu,
                                                        1,
                                                        (SELECT nvl(x.val_impo_flet_tota_docu,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.imp_des3_total_loca          = decode(cab.imp_des3_total_loca,
                                                        1,
                                                        (SELECT nvl(x.val_impo_desc_3_tota_loca,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.imp_des3_sin_impu_tota       = decode(cab.imp_des3_total_loca,
                                                        1,
                                                        (SELECT nvl(x.val_impo_desc_3_sin_impu_tota,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.imp_des4_tota_loca          = decode(cab.imp_des4_tota_loca,
                                                        1,
                                                        (SELECT nvl(x.val_impo_desc_4_tota_loca,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
               cab.imp_des4_sin_impu_tota       = decode(cab.imp_des4_tota_loca,
                                                        1,
                                                        (SELECT nvl(x.val_impo_desc_4_sin_impu_tota,
                                                                    0)
                                                           FROM ped_solic_cabec x
                                                          WHERE oid_soli_cabe = cab.soca_oid_soli_cabe),
                                                        0)*decode(cab.tido_oid_tipo_docu,30,0,9,0,1),
                cab.val_prec_cata_tota_loca    =
               (SELECT SUM(b.val_prec_cata_tota_loca)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.val_prec_cata_tota_docu    =
               (SELECT SUM(b.val_prec_cata_tota_docu)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.val_prec_cata_sin_impu_tota =
               (SELECT SUM(decode(b.val_prec_cata_unit_loca,0,0,b.val_prec_sin_impu_tota_loca))
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.val_prec_cont_sin_impu_tota =
               (SELECT SUM(decode(b.val_prec_cata_unit_loca,0,b.val_prec_sin_impu_tota_loca,0))
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.val_prec_fact_tota_loca    =
               (SELECT SUM(b.val_prec_fact_tota_loca)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.val_prec_fact_tota_docu    =
               (SELECT SUM(b.val_prec_fact_tota_docu)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.val_prec_cont_tota_loca    =
               (SELECT SUM(b.val_prec_cont_tota_loca)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.val_prec_cont_tota_docu    =
               (SELECT SUM(b.val_prec_cont_tota_docu)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.val_prec_neto_tota_loca    =
               (SELECT SUM(b.val_prec_neto_tota_loca)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.val_prec_neto_tota_docu    =
               (SELECT SUM(b.val_prec_neto_tota_docu)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.imp_des1_sin_impu_tota     =
               (SELECT SUM(b.imp_desc_sin_impu_tota_loca)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.imp_desc_tota_loca         =
               (SELECT SUM(b.imp_desc_tota_loca)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.imp_desc_tota_docu         =
               (SELECT SUM(b.imp_desc_tota_docu)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.imp_impu_tota_loca         =
               round((SELECT SUM(b.imp_impu_tota_loca)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),ln_decim),
               cab.imp_impu_tota_docu         =
               round((SELECT SUM(b.imp_impu_tota_docu)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),ln_decim),
               cab.num_unid_aten_tota         =
               (SELECT SUM(b.num_unid_aten)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.val_tota_paga_loca         =
               (SELECT SUM(decode(b.val_prec_cata_unit_loca,
                                  0,
                                  0,
                                  b.val_prec_fact_tota_loca))
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe),
               cab.imp_iva_impu_tota_prod_naci         =
               (SELECT SUM(b.imp_iva_impu_tota_prod_naci)
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe)
         WHERE cab.oid_cabe = ln_oidcabe;

         update fac_docum_conta_cabec cab
         set cab.imp_des1_impu=cab.imp_des1_sin_impu_tota+cab.val_prec_cont_sin_impu_tota+cab.imp_redo_loca
         WHERE cab.oid_cabe = ln_oidcabe;

        UPDATE fac_docum_conta_cabec cab
           SET cab.val_impo_rete_desc =
           case
             when cab.imp_desc_tota_loca<0
             then (select sum(nvl(x.val_impo_rete_desc,0)) from ped_solic_cabec x where oid_soli_cabe=cab.soca_oid_soli_cabe)
             else round(cab.imp_desc_tota_loca*(ln_rete/100),ln_decim) end
           --SET cab.val_impo_rete_desc = round(cab.imp_desc_tota_loca*(ln_rete/100),ln_decim)
         WHERE cab.oid_cabe = ln_oidcabe;

        if ln_desc_tota=1 then
          update fac_docum_conta_cabec a
          set a.imp_desc_tota_loca=a.imp_desc_tota_loca+a.val_prec_cont_tota_loca
            where a.oid_cabe=ln_oidcabe;
        end if;

        UPDATE fac_docum_conta_cabec cab
           SET cab.imp_impu_tota_loca = cab.imp_impu_tota_loca
                                        +(cab.imp_flet_tota_loca-cab.imp_flet_impu_tota_loca)
                                        +(cab.val_tota_impu_gast_admi*ln_imp_inte)
                                        +(cab.val_tota_impu_gast_admi2*ln_imp_inte)
         WHERE cab.oid_cabe = ln_oidcabe;

 /*-----------------------------------------------------------*/        
      if ln_ajuste='S' then
      
      SELECT a.val_tasa_impu
        INTO ln_valtasa
        FROM ped_tasa_impue  a,
             ped_impue_gener b
       WHERE b.taim_oid_tasa_impu = a.oid_tasa_impu
         AND b.sbac_oid_sbac = 888;
                  
             UPDATE fac_docum_conta_cabec cab
       SET cab.imp_impu_tota_loca =   
               (
                 (nvl(cab.val_prec_cata_sin_impu_tota,0)+nvl(cab.val_prec_cont_sin_impu_tota,0))
               - nvl(cab.imp_des1_sin_impu_tota,0)
               - (select sum(decode(lin.val_prec_cont_tota_loca,
                                       0,
                                       0,
                                       lin.val_prec_sin_impu_tota_loca))
                from fac_docum_conta_linea lin where dcca_oid_cabe=cab.oid_cabe)
               + nvl(cab.imp_flet_impu_tota_loca,0)
               + nvl(cab.val_impo_rete_desc,0) +
               nvl(cab.val_tota_gast_admi_sin_impu,0)+
               nvl(cab.val_tota_gast_admi2_sin_impu,0)
               ) * (ln_valtasa/100)
     WHERE cab.oid_cabe = ln_oidcabe;
     
     end if;
/*-----------------------------------------------------------*/        


        UPDATE fac_docum_conta_cabec cab
           SET cab.val_tota_paga_loca =
               (SELECT SUM(decode(b.val_prec_cata_unit_loca,
                                  0,
                                  0,
                                  b.val_prec_fact_tota_loca))
                  FROM fac_docum_conta_linea b
                 WHERE b.dcca_oid_cabe = cab.oid_cabe) +
               nvl(cab.imp_flet_tota_loca,
                   0) + nvl(cab.val_impo_rete_desc,0) + nvl(cab.val_tota_gast_admi,
                            0) + nvl(cab.val_tota_gast_admi2,
                            0)
         WHERE cab.oid_cabe = ln_oidcabe;

        /*
        UPDATE fac_docum_conta_cabec cab
           SET cab.val_tota_paga_loca = cab.val_tota_paga_loca+nvl(cab.imp_iva_impu_tota_prod_naci,0),
               cab.imp_impu_tota_loca = cab.imp_impu_tota_loca+nvl(cab.imp_iva_impu_tota_prod_naci,0)
         WHERE cab.oid_cabe = ln_oidcabe;
         */

if ln_imp_estatal is not null then

   begin
   select y.val_tasa_impu into ln_imp_estatal_temp
   from fac_tipos_impue_ubige x, ped_tasa_impue y, ped_solic_cabec z
   where x.taim_oid_tasa_impu=y.oid_tasa_impu
   and x.vepo_oid_valo_estr_geop=z.zzon_oid_zona
   and z.oid_soli_cabe=p_oidcons
   ;

   if ln_imp_estatal_temp is not null then
      ln_imp_estatal:=ln_imp_estatal_temp;
   end if;


   exception when others then
        NULL;
   end;


        UPDATE fac_docum_conta_cabec cab
       SET cab.imp_impu_tota_loca = (cab.val_prec_cata_tota_loca+cab.imp_flet_tota_loca)*(ln_imp_estatal/100), cab.imp_redo_loca=0
     WHERE cab.oid_cabe = ln_oidcabe;

    UPDATE fac_docum_conta_cabec cab
       SET cab.val_tota_paga_loca = cab.val_tota_paga_loca+cab.imp_impu_tota_loca
     WHERE cab.oid_cabe = ln_oidcabe;

end if;

if ln_desc_flete ='S' then

      SELECT a.val_tasa_impu
        INTO ln_valtasa
        FROM ped_tasa_impue  a,
             ped_impue_gener b
       WHERE b.taim_oid_tasa_impu = a.oid_tasa_impu
         AND b.sbac_oid_sbac = 888;


    UPDATE fac_docum_conta_cabec cons
       SET cons.val_impo_desc_flet = round(cons.imp_flet_tota_loca*(ln_valtasa/100),ln_decim)
     WHERE cons.oid_cabe = ln_oidcabe;

    UPDATE fac_docum_conta_cabec cons
       SET cons.imp_impu_tota_loca = cons.imp_impu_tota_loca-cons.val_impo_desc_flet
     WHERE cons.oid_cabe = ln_oidcabe;

    UPDATE fac_docum_conta_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca-cons.val_impo_desc_flet
     WHERE cons.oid_cabe = ln_oidcabe;

end if;



    UPDATE fac_docum_conta_cabec cons
       SET cons.imp_impu_tota_loca = cons.imp_impu_tota_loca
       -(cons.imp_des3_total_loca-cons.imp_des3_sin_impu_tota)
       -(nvl(cons.imp_des4_tota_loca,0)-nvl(cons.imp_des4_sin_impu_tota,0))
     WHERE cons.oid_cabe = ln_oidcabe
     and nvl(cons.imp_des3_total_loca,0)+nvl(cons.imp_des4_tota_loca,0)<>0
     ;

    UPDATE fac_docum_conta_cabec cons
       SET cons.val_tota_paga_loca = cons.val_tota_paga_loca-cons.imp_des3_total_loca-nvl(cons.imp_des4_tota_loca,0)
     WHERE cons.oid_cabe = ln_oidcabe
     and nvl(cons.imp_des3_total_loca,0)<>0
     ;

        UPDATE fac_docum_conta_cabec cab
           SET cab.imp_redo_loca =
               (nvl(cab.val_prec_cata_sin_impu_tota,0)+nvl(cab.val_prec_cont_sin_impu_tota,0)
               - nvl(cab.imp_des1_sin_impu_tota,0)
               - (select sum(decode(lin.val_prec_cont_tota_loca,
                                       0,
                                       0,
                                       lin.val_prec_sin_impu_tota_loca))
                from fac_docum_conta_linea lin where dcca_oid_cabe=cab.oid_cabe)
               + nvl(cab.imp_impu_tota_loca,0)
               + nvl(cab.imp_flet_impu_tota_loca,0)
               + nvl(cab.val_impo_rete_desc,0) +
               nvl(cab.val_tota_gast_admi_sin_impu,
                    0)+
               nvl(cab.val_tota_gast_admi2_sin_impu,
                    0) - nvl(cab.val_tota_paga_loca,
                              0)-nvl(cab.imp_des3_sin_impu_tota,0)
                              )
         WHERE cab.oid_cabe = ln_oidcabe;


         if ln_cree is not null then
            update fac_docum_conta_cabec a
            set a.val_impo_impu_cree=round(a.val_prec_neto_tota_loca*(ln_cree/1000),ln_decim)
            where a.oid_cabe=ln_oidcabe;
         end if;

         if ln_iva is not null then

            begin
              select sum(d.imp_prec_posi*0.53*b.num_unid_aten) into ln_valgratis
            from fac_docum_conta_cabec a, fac_docum_conta_linea b, ped_solic_posic c, pre_ofert_detal d
            where a.oid_cabe=b.dcca_oid_cabe
            and b.sopo_oid_soli_posi=c.oid_soli_posi
            and c.ofde_oid_deta_ofer=d.oid_deta_ofer
            and c.val_prec_cata_unit_loca=0
            and not exists (select 1 from fac_tipo_ofert_exclu ftoe where ftoe.tofe_oid_tipo_ofer=d.tofe_oid_tipo_ofer)
            and a.oid_cabe=ln_oidcabe;
            exception when others then
              ln_valgratis:=0;
            end;


            update fac_docum_conta_cabec a
            set a.val_impo_iva_asum_empr=round(ped_fn_gener_iva_asum(a.oid_cabe,r_doclega.val_tasa_impu, ln_decim,ln_iva, ln_valgratis, a.val_prec_neto_tota_loca), ln_decim)
            where a.oid_cabe=ln_oidcabe
            and a.tido_oid_tipo_docu=1
            ;

            update fac_docum_conta_cabec a
            set a.val_impo_iva_asum_empr=round(ped_fn_gener_iva_asum(a.oid_cabe,r_doclega.val_tasa_impu, ln_decim,ln_iva, abs(ln_valgratis), abs(a.val_prec_neto_tota_loca)), ln_decim)*-1
            where a.oid_cabe=ln_oidcabe
            and a.tido_oid_tipo_docu=32
            and exists (select 1
            from ped_solic_cabec x, ped_solic_cabec y, ped_tipo_solic_pais z, ped_tipo_solic z1
            where x.oid_soli_cabe=a.soca_oid_soli_cabe
            and y.soca_oid_soli_cabe=x.oid_soli_cabe
            and y.tspa_oid_tipo_soli_pais=z.oid_tipo_soli_pais
            and z.tsol_oid_tipo_soli=z1.oid_tipo_soli
            and z1.ind_anul=1
            )
            ;


         end if;


      END LOOP;

    END LOOP;
    CLOSE c_doclega;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_genera_doclega: ' || ls_sqlerrm || ' p_oidcons:' ||
                              p_oidcons);

  END fac_pr_genera_doclega;
  /***************************************************************************
  Descripcion       : Genera documentos legales
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_doclega_z
  (
    p_oidzona          NUMBER,
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  ) IS

    CURSOR c_cons
    (
      p_oidperi NUMBER,
      p_fecfact VARCHAR2
    ) IS
      SELECT a.oid_soli_cabe
        FROM ped_solic_cabec a
       WHERE a.perd_oid_peri = p_oidperi
         AND a.fec_fact = to_date(p_fecfact,
                                  'dd/mm/yyyy')
         AND a.ind_inte_lari_gene = 0
         AND a.num_unid_aten_tota > 0
         AND a.ind_ts_no_conso = 0
         AND a.zzon_oid_zona = p_oidzona
         AND a.tspa_oid_tipo_soli_pais IN (SELECT x.oid_tipo_soli_pais
                                             FROM ped_tipo_solic_pais x,
                                                  ped_tipo_solic      y
                                            WHERE x.tsol_oid_tipo_soli = y.oid_tipo_soli
                                              AND y.ind_cons = 1
                                              AND y.ind_soli_nega = 0);

    r_cons c_cons%ROWTYPE;

    ln_valtasa     NUMBER(2);
    ln_oidtasa     NUMBER(10);
    ln_valtasaflet NUMBER(2);
    --ln_oidtasaflet    NUMBER(10);
    --w_filas        NUMBER(12);

    lv_codpais VARCHAR2(20);
    ln_oidperi NUMBER;
    --w_filas        NUMBER(12);

  BEGIN

    ln_oidperi := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodperi);

    SELECT z.cod_pais
      INTO lv_codpais
      FROM cra_perio       x,
           seg_perio_corpo y,
           bas_ctrl_fact   z
     WHERE x.peri_oid_peri = y.oid_peri
       AND y.cod_peri = z.cod_peri
       AND z.ind_camp_act = 1
       AND z.sta_camp = 0;

    OPEN c_cons(ln_oidperi,
                psfechafacturacion);
    LOOP
      FETCH c_cons
        INTO r_cons;
      EXIT WHEN c_cons%NOTFOUND;

      fac_pr_genera_doclega(r_cons.oid_soli_cabe);

    END LOOP;
    CLOSE c_cons;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_genera_doclega_z: ' || ls_sqlerrm || ' p_oidzona:' ||
                              p_oidzona);
  END fac_pr_genera_doclega_z;
  /***************************************************************************
  Descripcion       : Genera Documentos Legales
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_ruv(p_oidcons NUMBER) IS

    CURSOR c_doclega IS
      SELECT b.val_nom1,
             b.val_nom2,
             b.val_ape1,
             b.val_ape2,
             b.zorg_oid_regi,
             b.soci_oid_soci,
             b.sbac_oid_sbac,
             b.num_docu_cont_inte,
             b.fec_emis,
             b.pais_oid_pais,
             b.fec_fact,
             b.val_nume_iden_fisc,
             b.val_nume_iden_nnal,
             a.clie_oid_clie,
             b.oid_cabe,
             a.taim_oid_tasa_impu,
             b.tido_oid_tipo_docu,
             b.val_seri_docu_lega,
             b.num_docu_lega,
             b.val_impo_rete_desc,
             b.val_prec_cata_sin_impu_tota,
             b.val_prec_cont_sin_impu_tota,
             b.imp_flet_impu_tota_loca,
             b.val_tota_gast_admi,
             b.val_tota_gast_admi_sin_impu,
             b.val_tota_impu_gast_admi,
             b.val_tota_comi_flex,
             b.val_tota_comi_flex_sin_impu,
             b.val_impo_impu_comi_flex,
             b.imp_impu_tota_loca,
             b.val_tota_paga_loca,
             b.imp_des1_impu,
             b.imp_redo_loca,
             b.val_tota_gast_admi2_sin_impu,
             b.imp_des3_sin_impu_tota
        FROM ped_solic_cabec       a,
             fac_docum_conta_cabec b
       WHERE a.oid_soli_cabe = p_oidcons
         AND a.oid_soli_cabe = b.soca_oid_soli_cabe
         AND NOT EXISTS
       (SELECT 1 FROM fac_regis_unico_venta WHERE dcca_oid_cabe = b.oid_cabe);

    r_doclega c_doclega%ROWTYPE;

    ln_valtasa     NUMBER(2);
    ln_valtasaflet NUMBER(2);
    ln_posic       NUMBER(4);
    ln_oidcabe     NUMBER(10);
    --w_filas        NUMBER(12);

    ln_impPremio       VARCHAR2(2):=nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS', 'ln_impPremio'),'N');


  BEGIN

    DELETE FROM fac_regis_unico_venta
     WHERE dcca_oid_cabe IN
           (SELECT oid_cabe FROM fac_docum_conta_cabec WHERE soca_oid_soli_cabe = p_oidcons);

    OPEN c_doclega;
    LOOP
      FETCH c_doclega
        INTO r_doclega;
      EXIT WHEN c_doclega%NOTFOUND;

      INSERT INTO fac_regis_unico_venta
        (oid_regi,
         pais_oid_pais,
         soci_oid_soci,
         sbac_oid_sbac,
         val_ejer_docu_inte,
         num_docu_cont_inte,
         fec_emis,
         val_base_impo,
         imp_impu,
         imp_tota,
         val_punt_emis,
         val_nume_iden_fisc,
         val_nume_iden_nnal,
         val_inte_mora,
         val_desc,
         val_base_impo_neto,
         val_nom1,
         val_nom2,
         val_ape1,
         val_ape2,
         clie_oid_clie,
         ind_esta,
         ind_tran_grat,
         dcca_oid_cabe,
         taim_oid_tasa_impu,
         tido_oid_tipo_docu,
         val_indi_ruv,
         val_nume_docu_lega,
         val_seri_docu_lega,
         val_impo_rete_desc,
         val_tota_gast_admi,
         val_tota_gast_admi_sin_impu,
         val_tota_impu_gast_admi,
         val_tota_comi_flex,
         val_tota_comi_flex_sin_impu,
         val_impo_impu_comi_flex)
      VALUES
        (fac_ruve_seq.nextval,
         r_doclega.pais_oid_pais,
         r_doclega.soci_oid_soci,
         r_doclega.sbac_oid_sbac,
         to_char(r_doclega.fec_fact,
                 'yy'),
         r_doclega.num_docu_cont_inte,
         trunc(r_doclega.fec_fact),
         nvl(r_doclega.val_prec_cata_sin_impu_tota,0)
         + nvl(r_doclega.val_prec_cont_sin_impu_tota,0)
         + nvl(r_doclega.imp_flet_impu_tota_loca,0)
         + nvl(r_doclega.val_tota_gast_admi_sin_impu,0)
         ,
         nvl(r_doclega.imp_impu_tota_loca,
             0),
         nvl(r_doclega.val_tota_paga_loca,
             0),
         NULL,
         r_doclega.val_nume_iden_fisc,
         r_doclega.val_nume_iden_nnal,
         0,
         nvl(r_doclega.imp_des1_impu,0)+nvl(r_doclega.imp_redo_loca,0)+nvl(r_doclega.imp_des3_sin_impu_tota,0),
         nvl(r_doclega.val_prec_cata_sin_impu_tota+r_doclega.val_prec_cont_sin_impu_tota,
             0) - nvl(r_doclega.imp_des1_impu,0) - nvl(r_doclega.imp_des3_sin_impu_tota,0) - nvl(r_doclega.imp_redo_loca,0)
             +nvl(r_doclega.imp_flet_impu_tota_loca,
                      0)+nvl(r_doclega.val_tota_gast_admi_sin_impu,
                      0)+nvl(r_doclega.val_tota_gast_admi2_sin_impu,
                      0),
         r_doclega.val_nom1,
         r_doclega.val_nom2,
         r_doclega.val_ape1,
         r_doclega.val_ape2,
         r_doclega.clie_oid_clie,
         0,
         0,
         r_doclega.oid_cabe,
         r_doclega.taim_oid_tasa_impu,
         r_doclega.tido_oid_tipo_docu,
         'A',
         r_doclega.num_docu_lega,
         r_doclega.val_seri_docu_lega,
         r_doclega.val_impo_rete_desc,
         r_doclega.val_tota_gast_admi,
         r_doclega.val_tota_gast_admi_sin_impu,
         r_doclega.val_tota_impu_gast_admi,
         r_doclega.val_tota_comi_flex,
         r_doclega.val_tota_comi_flex_sin_impu,
         r_doclega.val_impo_impu_comi_flex);

    END LOOP;
    CLOSE c_doclega;

if ln_impPremio='S' then

      SELECT a.val_tasa_impu
        INTO ln_valtasa
        FROM ped_tasa_impue  a,
             ped_impue_gener b
       WHERE b.taim_oid_tasa_impu = a.oid_tasa_impu
         AND b.sbac_oid_sbac = 888;

    update fac_regis_unico_venta a set a.imp_impu=
    (
    select
    nvl(sum(((decode(j.val_prec_cont_tota_loca,0,m.VAL_COST_ESTD*j.num_unid_aten,j.val_prec_cont_tota_loca)*ln_valtasa)/100) ),0)
        from fac_docum_conta_linea j, mae_produ m where dcca_oid_cabe=a.dcca_oid_cabe and j.prod_oid_prod=m.oid_prod
    )
    , a.val_base_impo=
        (
    select
    nvl(sum(((decode(j.val_prec_cont_tota_loca,0,m.VAL_COST_ESTD*j.num_unid_aten,j.val_prec_cont_tota_loca))) ),0)
        from fac_docum_conta_linea j, mae_produ m where dcca_oid_cabe=a.dcca_oid_cabe and j.prod_oid_prod=m.oid_prod
    )
    , a.val_base_impo_neto=
        (
    select
    nvl(sum(((decode(j.val_prec_cont_tota_loca,0,m.VAL_COST_ESTD*j.num_unid_aten,j.val_prec_cont_tota_loca))) ),0)
        from fac_docum_conta_linea j, mae_produ m where dcca_oid_cabe=a.dcca_oid_cabe and j.prod_oid_prod=m.oid_prod
    )
    where dcca_oid_cabe in
     (select x.oid_cabe from fac_docum_conta_cabec x where x.soca_oid_soli_cabe=p_oidcons and x.ictp_oid_tipo_prog in (2001,2002))
     ;

end if;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_genera_ruv: ' || ls_sqlerrm || ' p_oidcons:' ||
                              p_oidcons);

  END fac_pr_genera_ruv;

  /***************************************************************************
  Descripcion       : Genera documentos legales
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_ruv_z
  (
    p_oidzona          NUMBER,
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  ) IS

    CURSOR c_cons
    (
      p_oidperi NUMBER,
      p_fecfact VARCHAR2
    ) IS
      SELECT a.oid_soli_cabe
        FROM ped_solic_cabec a
       WHERE a.perd_oid_peri = p_oidperi
         AND a.fec_fact = to_date(p_fecfact,
                                  'dd/mm/yyyy')
         AND a.ind_inte_lari_gene = 0
         AND a.num_unid_aten_tota > 0
         AND a.ind_ts_no_conso = 0
         AND a.zzon_oid_zona = p_oidzona
         AND a.tspa_oid_tipo_soli_pais IN (SELECT x.oid_tipo_soli_pais
                                             FROM ped_tipo_solic_pais x,
                                                  ped_tipo_solic      y
                                            WHERE x.tsol_oid_tipo_soli = y.oid_tipo_soli
                                              AND y.ind_cons = 1
                                              AND y.ind_soli_nega = 0);

    r_cons c_cons%ROWTYPE;

    ln_valtasa     NUMBER(2);
    ln_oidtasa     NUMBER(10);
    ln_valtasaflet NUMBER(2);
    --ln_oidtasaflet    NUMBER(10);
    --w_filas        NUMBER(12);

    lv_codpais VARCHAR2(20);
    ln_oidperi NUMBER;
    --w_filas        NUMBER(12);

  BEGIN

    ln_oidperi := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodperi);

    SELECT z.cod_pais
      INTO lv_codpais
      FROM cra_perio       x,
           seg_perio_corpo y,
           bas_ctrl_fact   z
     WHERE x.peri_oid_peri = y.oid_peri
       AND y.cod_peri = z.cod_peri
       AND z.ind_camp_act = 1
       AND z.sta_camp = 0;

    OPEN c_cons(ln_oidperi,
                psfechafacturacion);
    LOOP
      FETCH c_cons
        INTO r_cons;
      EXIT WHEN c_cons%NOTFOUND;

      fac_pr_genera_ruv(r_cons.oid_soli_cabe);

    END LOOP;
    CLOSE c_cons;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_genera_ruv_z: ' || ls_sqlerrm || 'p_oidzona:' ||
                              p_oidzona);
  END fac_pr_genera_ruv_z;
  /***************************************************************************
  Descripcion       : Genera Documentos Legales
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_ccc(p_oidcons NUMBER) IS

    lv_ind_ejec_proc_flex ccc_param_gener.val_para%TYPE;

  BEGIN

    lv_ind_ejec_proc_flex := ccc_pkg_gener.ccc_fn_obtie_param_gener('IndicadorFlexipago');

    IF lv_ind_ejec_proc_flex IS NULL THEN

      ccc_pkg_proce.ccc_pr_gener_regis_cuent_corri(p_oidcons);

    ELSE

      ccc_pkg_proce.ccc_pr_gener_cuota_cuent_corri(p_oidcons);

    END IF;

  EXCEPTION

    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_genera_ccc: ' || ls_sqlerrm || 'p_oidcons_' ||
                              p_oidcons);

  END fac_pr_genera_ccc;

  /***************************************************************************
  Descripcion       : Genera documentos legales
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_ccc_z
  (
    p_oidzona          NUMBER,
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  ) IS

    CURSOR c_cons
    (
      p_oidperi NUMBER,
      p_fecfact VARCHAR2
    ) IS
      SELECT a.oid_soli_cabe
        FROM ped_solic_cabec a
       WHERE a.perd_oid_peri = p_oidperi
         AND a.fec_fact = to_date(p_fecfact,
                                  'dd/mm/yyyy')
         AND a.num_unid_aten_tota > 0
         AND a.ind_ts_no_conso = 0
         AND a.ind_inte_lari_gene = 0
         AND a.zzon_oid_zona = p_oidzona
         AND a.tspa_oid_tipo_soli_pais IN (SELECT x.oid_tipo_soli_pais
                                             FROM ped_tipo_solic_pais x,
                                                  ped_tipo_solic      y
                                            WHERE x.tsol_oid_tipo_soli = y.oid_tipo_soli
                                              AND y.ind_cons = 1
                                              AND y.ind_soli_nega = 0)
         AND NOT EXISTS
       (SELECT 1 FROM ccc_movim_cuent_corri WHERE soca_oid_soli_cabe = a.oid_soli_cabe);

    r_cons c_cons%ROWTYPE;

    ln_valtasa     NUMBER(2);
    ln_oidtasa     NUMBER(10);
    ln_valtasaflet NUMBER(2);
    --ln_oidtasaflet    NUMBER(10);
    --w_filas        NUMBER(12);

    lv_codpais VARCHAR2(20);
    ln_oidperi NUMBER;
    --w_filas        NUMBER(12);

  BEGIN

    ln_oidperi := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodperi);

    SELECT z.cod_pais
      INTO lv_codpais
      FROM cra_perio       x,
           seg_perio_corpo y,
           bas_ctrl_fact   z
     WHERE x.peri_oid_peri = y.oid_peri
       AND y.cod_peri = z.cod_peri
       AND z.ind_camp_act = 1
       AND z.sta_camp = 0;

    OPEN c_cons(ln_oidperi,
                psfechafacturacion);
    LOOP
      FETCH c_cons
        INTO r_cons;
      EXIT WHEN c_cons%NOTFOUND;

      fac_pr_genera_ccc(r_cons.oid_soli_cabe);
      --RETURN;

    END LOOP;
    CLOSE c_cons;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_genera_ccc_z: ' || ls_sqlerrm || ' p_oizona:' ||
                              p_oidzona);
  END fac_pr_genera_ccc_z;
  /***************************************************************************
  Descripcion       : Genera documentos legales
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_bol_elec_z
  (
    p_oidzona          NUMBER,
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  ) IS

    CURSOR c_cons
    (
      p_oidperi NUMBER,
      p_fecfact VARCHAR2
    ) IS
      SELECT a.oid_soli_cabe
        FROM ped_solic_cabec a
       WHERE a.perd_oid_peri = p_oidperi
         AND a.fec_fact = to_date(p_fecfact,
                                  'dd/mm/yyyy')
         AND a.ind_inte_lari_gene = 0
         AND a.num_unid_aten_tota > 0
         AND a.ind_ts_no_conso = 0
         AND a.zzon_oid_zona = p_oidzona
         AND a.tspa_oid_tipo_soli_pais IN (SELECT x.oid_tipo_soli_pais
                                             FROM ped_tipo_solic_pais x,
                                                  ped_tipo_solic      y
                                            WHERE x.tsol_oid_tipo_soli = y.oid_tipo_soli
                                              AND y.ind_cons = 1
                                              AND y.ind_soli_nega = 0);

    r_cons c_cons%ROWTYPE;

    ln_valtasa     NUMBER(2);
    ln_oidtasa     NUMBER(10);
    ln_valtasaflet NUMBER(2);
    --ln_oidtasaflet    NUMBER(10);
    --w_filas        NUMBER(12);

    lv_codpais VARCHAR2(20);
    ln_oidperi NUMBER;
    --w_filas        NUMBER(12);

  BEGIN

    ln_oidperi := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodperi);

    SELECT z.cod_pais
      INTO lv_codpais
      FROM cra_perio       x,
           seg_perio_corpo y,
           bas_ctrl_fact   z
     WHERE x.peri_oid_peri = y.oid_peri
       AND y.cod_peri = z.cod_peri
       AND z.ind_camp_act = 1
       AND z.sta_camp = 0;

    OPEN c_cons(ln_oidperi,
                psfechafacturacion);
    LOOP
      FETCH c_cons
        INTO r_cons;
      EXIT WHEN c_cons%NOTFOUND;

      fac_pr_genera_bol_elec(r_cons.oid_soli_cabe);

    END LOOP;
    CLOSE c_cons;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_genera_bol_elec_z: ' || ls_sqlerrm || ' p_oidzona:' ||
                              p_oidzona);
  END fac_pr_genera_bol_elec_z;

  /***************************************************************************
  Descripcion       : Generacion de Consolidados
  Fecha Creacion    : 22/02/2012
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_bol_elec(p_oidcons NUMBER) IS

    CURSOR c_facturacabecera IS
      SELECT con.val_nume_soli,
             doc_cont.oid_cabe,
             nvl(doc_cont.num_docu_lega,
                 doc_cont.num_docu_cont_inte) num_docu_lega,
             con.fec_fact fec_emis,
             doc_cont.num_docu_cont_inte,
             mc.oid_clie,
             mc.cod_clie,
             --psCodPeriodo COD_PERI,
             zon_regio.cod_regi,
             zon_zona.cod_zona,
             zon_terri.cod_terr,
             doc_cont.val_ape1 || ' ' || doc_cont.val_ape2 || ', ' || doc_cont.val_nom1 || ' ' ||
             doc_cont.val_nom2 nom_comp,
             doc_cont.val_dire_comp,
             doc_cont.val_nume_iden_fisc,
             doc_cont.imp_desc_tota_loca,
             doc_cont.imp_impu_tota_loca,
             doc_cont.imp_flet_tota_loca,
             doc_cont.val_tota_paga_loca,
             sper.cod_peri,
             doc_cont.perd_oid_peri,
             doc_cont.val_tota_gast_admi,
             fac_tipo_docum.cod_tipo_docu
        FROM fac_docum_conta_cabec doc_cont,
             fac_tipo_docum,
             ped_solic_cabec       con,
             mae_clien             mc,
             zon_terri,
             zon_zona,
             zon_regio,
             cra_perio             per,
             seg_perio_corpo       sper
       WHERE doc_cont.soca_oid_soli_cabe = con.oid_soli_cabe
         AND doc_cont.tido_oid_tipo_docu = fac_tipo_docum.oid_tipo_docu
         AND con.clie_oid_clie = mc.oid_clie
         AND fac_tipo_docum.cod_tipo_docu in ('001','023')
         AND doc_cont.terr_oid_terr = zon_terri.oid_terr
         AND doc_cont.zzon_oid_zona = zon_zona.oid_zona
         AND doc_cont.zorg_oid_regi = zon_regio.oid_regi
         AND con.oid_soli_cabe = p_oidcons
         AND con.perd_oid_peri = per.oid_peri
         AND per.peri_oid_peri = sper.oid_peri;

    r_facturacabecera c_facturacabecera%ROWTYPE;

    CURSOR c_detallefactura(oid_cabe NUMBER) IS
      SELECT x.dcca_oid_cabe,
             x.oid,
             x.oid oid_line,
             x.num_linea,
             x.num_unid_aten,
             x.prod_oid_prod,
             y.val_i18n des_prod,
             z.oid_soli_posi,
             z.val_codi_vent,
             x.val_prec_cata_unit_loca,
             x.val_prec_cata_tota_loca,
             x.val_prec_cont_unit_loca,
             x.val_prec_cont_tota_loca,
             x.imp_desc_unit_loca,
             x.imp_desc_tota_loca,
             x.val_prec_fact_unit_loca,
             x.val_prec_fact_tota_loca,
             x.imp_impu_unit_loca,
             x.imp_impu_tota_loca,
             x.val_prec_neto_unit_loca,
             x.val_prec_neto_tota_loca,
             nvl(x.val_porc_desc,
                 0) val_porc_desc,
             decode(x.val_prec_cata_unit_loca,
                    0,
                    x.val_prec_sin_impu_unit,
                    x.val_prec_cata_unit_loca) val_prec_cata_unit_fact,
             decode(x.val_prec_cata_tota_loca,
                    0,
                    x.val_prec_sin_impu_tota_loca,
                    x.val_prec_sin_impu_tota_loca) val_prec_sin_impu_tota_fact,
             decode(x.val_prec_cata_unit_loca,
                    0,
                    x.val_prec_cont_tota_loca,
                    x.imp_desc_sin_impu_tota_loca) imp_desc_tota_fact,
             decode(x.val_prec_cata_unit_loca,
                    0,
                    0,
                    x.val_prec_neto_tota_loca) val_prec_neto_tota_fact,
             decode(x.val_prec_cata_tota_loca,
                    0,
                    0,
                    x.imp_impu_tota_loca) imp_impu_tota_fact --,
      --  DECODE(X.VAL_PREC_CATA_TOTA_LOCA, 0, 'S', 'N') IND_PROD_GRAT
        FROM fac_docum_conta_linea x,
             (SELECT val_i18n,
                     val_oid
                FROM gen_i18n_sicc
               WHERE attr_enti = 'MAE_PRODU') y,
             ped_solic_posic z,
             pre_ofert_detal p
       WHERE x.prod_oid_prod = y.val_oid
         AND x.sopo_oid_soli_posi = z.oid_soli_posi
         AND z.ofde_oid_deta_ofer = p.oid_deta_ofer
         AND NOT EXISTS (SELECT NULL
                FROM fac_tipo_ofert_exclu o
               WHERE o.tofe_oid_tipo_ofer = p.tofe_oid_tipo_ofer)
         AND x.dcca_oid_cabe = oid_cabe
         AND x.num_unid_aten > 0
         AND x.val_prec_cata_unit_loca > 0
       ORDER BY x.num_linea;

    r_detallefactura c_detallefactura%ROWTYPE;
    
    ln_inte_flex NUMBER(12,2);

  BEGIN

    DELETE FROM ped_bolet_elect
     WHERE val_nume_soli =
           (SELECT val_nume_soli FROM ped_solic_cabec WHERE oid_soli_cabe = p_oidcons);

    OPEN c_facturacabecera;
    LOOP
      FETCH c_facturacabecera
        INTO r_facturacabecera;
      EXIT WHEN c_facturacabecera%NOTFOUND;

      OPEN c_detallefactura(r_facturacabecera.oid_cabe);
      LOOP
        FETCH c_detallefactura
          INTO r_detallefactura;
        EXIT WHEN c_detallefactura%NOTFOUND;
        FOR i IN 1 .. r_detallefactura.num_unid_aten
        LOOP
          INSERT INTO ped_bolet_elect
            (oid_bole_elec,
             oid_peri,
             cod_peri,
             oid_clie,
             cod_clie,
             cod_terr,
             fec_proc,
             val_nume_soli,
             val_codi_vent,
             val_desc_prod,
             num_unid,
             val_prec_cata,
             ind_recl,
             oid_line_recl,
             cod_orig,
             val_foli,
             cod_zona,
             oid_soli_posi_pedi,
             cod_tipo_docu)
          VALUES
            (ped_boel_seq.nextval,
             r_facturacabecera.perd_oid_peri,
             r_facturacabecera.cod_peri,
             r_facturacabecera.oid_clie,
             r_facturacabecera.cod_clie,
             r_facturacabecera.cod_terr, -- || r_facturacabecera.cod_terr,
             r_facturacabecera.fec_emis,
             r_facturacabecera.val_nume_soli,
             r_detallefactura.val_codi_vent,
             r_detallefactura.des_prod,
             1,
             r_detallefactura.val_prec_cata_unit_fact,
             0,
             NULL,
             NULL,
             NULL,
             r_facturacabecera.cod_zona,
             r_detallefactura.oid_soli_posi,
             r_facturacabecera.cod_tipo_docu);


        END LOOP;
      END LOOP;
      CLOSE c_detallefactura;

                      /*
      Insera Flete por factura
      */
         if nvl(r_facturacabecera.imp_flet_tota_loca,0)<>0 then

         INSERT INTO ped_bolet_elect
            (oid_bole_elec,
             oid_peri,
             cod_peri,
             oid_clie,
             cod_clie,
             cod_terr,
             fec_proc,
             val_nume_soli,
             val_codi_vent,
             val_desc_prod,
             num_unid,
             val_prec_cata,
             ind_recl,
             oid_line_recl,
             cod_orig,
             val_foli,
             cod_zona,
             oid_soli_posi_pedi,
             cod_tipo_docu)
          VALUES
            (ped_boel_seq.nextval,
             r_facturacabecera.perd_oid_peri,
             r_facturacabecera.cod_peri,
             r_facturacabecera.oid_clie,
             r_facturacabecera.cod_clie,
             r_facturacabecera.cod_terr, -- || r_facturacabecera.cod_terr,
             r_facturacabecera.fec_emis,
             r_facturacabecera.val_nume_soli,
             NULL,
             'FLETE',
             0,
             r_facturacabecera.imp_flet_tota_loca,
             0,
             NULL,
             NULL,
             NULL,
             r_facturacabecera.cod_zona,
             r_detallefactura.oid_soli_posi,
             r_facturacabecera.cod_tipo_docu);
           end if;
        -- Fin de Flete


                      /*
      Insera GA por factura
      */
         if nvl(r_facturacabecera.val_tota_gast_admi,0)<>0 then

         INSERT INTO ped_bolet_elect
            (oid_bole_elec,
             oid_peri,
             cod_peri,
             oid_clie,
             cod_clie,
             cod_terr,
             fec_proc,
             val_nume_soli,
             val_codi_vent,
             val_desc_prod,
             num_unid,
             val_prec_cata,
             ind_recl,
             oid_line_recl,
             cod_orig,
             val_foli,
             cod_zona,
             oid_soli_posi_pedi,
             cod_tipo_docu)
          VALUES
            (ped_boel_seq.nextval,
             r_facturacabecera.perd_oid_peri,
             r_facturacabecera.cod_peri,
             r_facturacabecera.oid_clie,
             r_facturacabecera.cod_clie,
             r_facturacabecera.cod_terr, -- || r_facturacabecera.cod_terr,
             r_facturacabecera.fec_emis,
             r_facturacabecera.val_nume_soli,
             NULL,
             'INTERES',
             0,
             r_facturacabecera.val_tota_gast_admi,
             0,
             NULL,
             NULL,
             NULL,
             r_facturacabecera.cod_zona,
             r_detallefactura.oid_soli_posi,
             r_facturacabecera.cod_tipo_docu);
           end if;
        -- Fin de GA

select nvl(sum(b.val_prec_cata_tota_loca),0) into ln_inte_flex
from ped_solic_cabec a, ped_solic_posic b, ped_tipo_solic_pais c, ped_tipo_solic d
where a.oid_soli_cabe=b.soca_oid_soli_cabe and a.tspa_oid_tipo_soli_pais=c.oid_tipo_soli_pais and c.tsol_oid_tipo_soli=d.oid_tipo_soli and d.cod_tipo_soli='SCUF'
and a.soca_oid_soli_cabe=p_oidcons;


                      /*
      Insera Flexipago por factura
      */
         if ln_inte_flex<>0 then

         INSERT INTO ped_bolet_elect
            (oid_bole_elec,
             oid_peri,
             cod_peri,
             oid_clie,
             cod_clie,
             cod_terr,
             fec_proc,
             val_nume_soli,
             val_codi_vent,
             val_desc_prod,
             num_unid,
             val_prec_cata,
             ind_recl,
             oid_line_recl,
             cod_orig,
             val_foli,
             cod_zona,
             oid_soli_posi_pedi,
             cod_tipo_docu)
          VALUES
            (ped_boel_seq.nextval,
             r_facturacabecera.perd_oid_peri,
             r_facturacabecera.cod_peri,
             r_facturacabecera.oid_clie,
             r_facturacabecera.cod_clie,
             r_facturacabecera.cod_terr, -- || r_facturacabecera.cod_terr,
             r_facturacabecera.fec_emis,
             r_facturacabecera.val_nume_soli,
             NULL,
             'INTERES FLEXIPAGO',
             0,
             ln_inte_flex,
             0,
             NULL,
             NULL,
             NULL,
             r_facturacabecera.cod_zona,
             r_detallefactura.oid_soli_posi,
             r_facturacabecera.cod_tipo_docu);
           end if;
        -- Fin de Interes Flexipago

    END LOOP;
    CLOSE c_facturacabecera;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_genera_bol_elec: ' || ls_sqlerrm || ' p_oidcons:' ||
                              p_oidcons);
  END fac_pr_genera_bol_elec;

  /***************************************************************************
  Descripcion       : Generacion de Boleta electronica Reclamos
  Fecha Creacion    : 25/05/2012
  Autor             : Sandro Quintana
  ***************************************************************************/
  PROCEDURE fac_pr_genera_bol_elec_rec(pnoidconso NUMBER) IS

    varoidboleelec ped_bolet_elect_histo.oid_bole_elec%TYPE;
    varvalfolirefe ped_bolet_elect_histo.val_foli%TYPE;
    varvalfechrefe ped_bolet_elect_histo.fec_proc%TYPE;
    varvalnumrecl  rec_cabec_recla.num_recl%TYPE;
    varvalnumesoli ped_bolet_elect_histo.val_nume_soli%TYPE;
    varoidperi     ped_nota_credi_elect.oid_peri%TYPE;
    varcodperi     ped_nota_credi_elect.cod_peri%TYPE;
    varcodpais     int_solic_conso_poven_cabec.cod_pais%TYPE;
    valtiporefe    ped_nota_credi_elect.val_tipo_refe%TYPE;
    valmoti        ped_nota_credi_elect.val_moti%TYPE;
    valcodoper     rec_opera.cod_oper%TYPE;
    varcodvent     ped_solic_posic.val_codi_vent%TYPE;
    varoidlinrec   rec_linea_opera_recla.oid_line_oper_recl%TYPE;
    varprodoid     rec_linea_opera_recla.prod_oid_prod%TYPE;

    CURSOR c_facturacabecera IS
      SELECT con.val_nume_soli,
             con.perd_oid_peri oid_peri,
             doc_cont.oid_cabe,
             nvl(doc_cont.num_docu_lega,
                 doc_cont.num_docu_cont_inte) num_docu_lega,
             con.fec_fact fec_emis,
             doc_cont.num_docu_cont_inte,
             mc.oid_clie,
             mc.cod_clie,
             --psCodPeriodo COD_PERI,
             zon_regio.cod_regi,
             zon_zona.cod_zona,
             zon_terri.cod_terr,
             doc_cont.val_ape1 || ' ' || doc_cont.val_ape2 || ' ' || doc_cont.val_nom1 || ' ' ||
             doc_cont.val_nom2 nom_comp,
             ltrim(doc_cont.val_dire_comp) val_dire_comp,
             doc_cont.val_nume_iden_fisc,
             doc_cont.imp_desc_tota_loca,
             doc_cont.imp_impu_tota_loca,
             doc_cont.imp_flet_impu_tota_loca,
             doc_cont.imp_flet_tota_loca,
             doc_cont.val_tota_paga_loca,
             sper.cod_peri,
             dir.des_villa_pobl val_vill,
             decode(dir.ciud_cod_ciud,null,'',
             (select des_geog from  zon_valor_estru_geopo
               where orde_1=substr(dir.cod_unid_geog,1,6) and orde_2 = substr(dir.cod_unid_geog,7,6)
               and orde_3 = substr(dir.cod_unid_geog,13,6)and orde_4 is null)
              ) val_comu,
              doc_cont.val_tota_gast_admi,          --- total interes
              doc_cont.val_tota_gast_admi_sin_impu, --- interes sin impuesto
              doc_cont.val_tota_impu_gast_admi      --- impusto del intere
        FROM fac_docum_conta_cabec doc_cont,
             fac_tipo_docum,
             ped_solic_cabec       con,
             mae_clien             mc,
             mae_clien_direc       dir,
             zon_terri,
             zon_zona,
             zon_regio,
             cra_perio             per,
             seg_perio_corpo       sper
       WHERE doc_cont.soca_oid_soli_cabe = con.oid_soli_cabe
         AND doc_cont.tido_oid_tipo_docu = fac_tipo_docum.oid_tipo_docu
         AND con.clie_oid_clie = mc.oid_clie
         and mc.OID_CLIE = DIR.CLIE_OID_CLIE (+)
         and DIR.OID_CLIE_DIRE (+) = imp_pkg_proce_laser.imp_fn_obtiene_direc_clie(varcodpais,mc.oid_clie)
         AND doc_cont.terr_oid_terr = zon_terri.oid_terr
         AND doc_cont.zzon_oid_zona = zon_zona.oid_zona
         AND doc_cont.zorg_oid_regi = zon_regio.oid_regi
         AND doc_cont.soca_oid_soli_cabe = pnoidconso
         AND con.perd_oid_peri = per.oid_peri
         AND per.peri_oid_peri = sper.oid_peri;
    ---AND fac_tipo_docum.cod_tipo_docu = '001'
    ---AND doc_cont.zzon_oid_zona = pnoidzona
    ---AND to_char(con.fec_fact,
    ---            'DD/MM/YYYY') = psfechafacturacion
    ---AND con.perd_oid_peri = pnoidperi

    r_facturacabecera c_facturacabecera%ROWTYPE;

    CURSOR c_detallefactura(oid_cabe NUMBER) IS
      SELECT x.dcca_oid_cabe,
             x.oid,
             x.oid oid_line,
             x.num_linea,
             abs(x.num_unid_aten) num_unid_aten,
             m.cod_sap,
             x.prod_oid_prod,
             y.val_i18n des_prod,
             z.oid_soli_posi,
             z.oid_line_oper_recl,
             z.val_codi_vent,
             x.val_prec_cata_unit_loca,
             x.val_prec_cata_tota_loca,
             x.val_prec_cont_unit_loca,
             x.val_prec_cont_tota_loca,
             x.imp_desc_unit_loca,
             x.imp_desc_tota_loca,
             x.val_prec_fact_unit_loca,
             x.val_prec_fact_tota_loca,
             x.imp_impu_unit_loca,
             x.imp_impu_tota_loca,
             x.val_prec_neto_unit_loca,
             x.val_prec_neto_tota_loca,
             x.val_prec_sin_impu_unit,
             nvl(x.val_porc_desc,
                 0) val_porc_desc,
             decode(x.val_prec_cata_unit_loca,
                    0,
                    x.val_prec_sin_impu_unit,
                    x.val_prec_cata_unit_loca) val_prec_cata_unit_fact,
             decode(x.val_prec_cata_tota_loca,
                    0,
                    x.val_prec_sin_impu_tota_loca,
                    x.val_prec_sin_impu_tota_loca) val_prec_sin_impu_tota_fact,
             decode(x.val_prec_cata_unit_loca,
                    0,
                    x.val_prec_cont_tota_loca,
                    x.imp_desc_sin_impu_tota_loca) imp_desc_tota_fact,
             decode(x.val_prec_cata_unit_loca,
                    0,
                    0,
                    x.val_prec_neto_tota_loca) val_prec_neto_tota_fact,
             decode(x.val_prec_cata_tota_loca,
                    0,
                    0,
                    x.imp_impu_tota_loca) imp_impu_tota_fact --,
      --  DECODE(X.VAL_PREC_CATA_TOTA_LOCA, 0, 'S', 'N') IND_PROD_GRAT
        FROM fac_docum_conta_linea x,
             (SELECT val_i18n,
                     val_oid
                FROM gen_i18n_sicc
               WHERE attr_enti = 'MAE_PRODU') y,
             ped_solic_posic z,
             pre_ofert_detal p,
             mae_produ m
       WHERE x.prod_oid_prod = y.val_oid
         AND x.sopo_oid_soli_posi = z.oid_soli_posi
         AND z.ofde_oid_deta_ofer = p.oid_deta_ofer
         AND x.prod_oid_prod = m.oid_prod(+)
         AND NOT EXISTS (SELECT NULL
                FROM fac_tipo_ofert_exclu o
               WHERE o.tofe_oid_tipo_ofer = p.tofe_oid_tipo_ofer)
         AND x.dcca_oid_cabe = oid_cabe
         AND x.num_unid_aten <> 0
         AND x.val_prec_cata_unit_loca > 0
       ORDER BY x.num_linea;

    r_detallefactura c_detallefactura%ROWTYPE;

  BEGIN

    select BAS.COD_PERI, CRA.OID_PERI , bas.cod_pais
    into varcodperi, varoidperi , varcodpais
    from bas_Ctrl_fact bas,seg_perio_corpo seg, cra_perio cra
    where BAS.COD_PERI = SEG.COD_PERI
    and SEG.OID_PERI = CRA.PERI_OID_PERI
    and BAS.IND_CAMP_ACT = 1
    and rownum = 1;


    ----delete from ped_bolet_elect where cod_zona=(select cod_zona from zon_zona where oid_zona=pnoidzona);

    OPEN c_facturacabecera;
    LOOP
      FETCH c_facturacabecera
        INTO r_facturacabecera;
      EXIT WHEN c_facturacabecera%NOTFOUND;

      OPEN c_detallefactura(r_facturacabecera.oid_cabe);
      LOOP
        FETCH c_detallefactura
          INTO r_detallefactura;
        EXIT WHEN c_detallefactura%NOTFOUND;
        FOR i IN 1 .. r_detallefactura.num_unid_aten
        LOOP
          valtiporefe  := 'A';
          valmoti      := '1';
          varcodvent   := r_detallefactura.val_codi_vent;
          varoidlinrec := r_detallefactura.oid_line_oper_recl;
          varprodoid   := r_detallefactura.prod_oid_prod;
          
          --- Verifica si es por boleta de recojo y recupera los datos para validar
          if nvl(varoidlinrec,0) = 0 then
             BEGIN
                select IRLB.COD_VENT_DISC, IRLB.LOR_OID_LINE_OPER_RECL 
                  into varcodvent, varoidlinrec
                from ped_solic_cabec psc, INT_REC_CABEC_BOREC ircb, INT_REC_LINEA_BOREC irlb
                where PSC.OID_SOLI_CABE = ircb.SOCA_OID_SOLI_CABE_ABON
                and IRCB.COD_CABE_BORE = IRLB.COD_CABE_BORE
                and psc.soca_OID_SOLI_CABE = pnoidconso
                and IRLB.PROD_OID_PROD_DISC = varprodoid               
                AND rownum = 1;
             
             EXCEPTION
               WHEN no_data_found THEN
                 valtiporefe  := 'A';
             END;
            
          end if;
          
          --- Recupera informacion de la boleta electronica asignada
          BEGIN
            SELECT bol.oid_bole_elec,
                   bol.val_foli,
                   bol.fec_proc,
                   rc.num_recl,
                   bol.val_nume_soli,
                   ro.cod_oper
              INTO varoidboleelec,
                   varvalfolirefe,
                   varvalfechrefe,
                   varvalnumrecl,
                   varvalnumesoli,
                   valcodoper
              FROM ped_bolet_elect_histo bol,
                   rec_linea_opera_recla rlo,
                   rec_opera_recla       ror,
                   rec_cabec_recla       rc,
                   rec_tipos_opera       rto,
                   rec_opera             ro
             WHERE bol.oid_line_recl = rlo.oid_line_oper_recl
               AND rlo.opre_oid_oper_recl = ror.oid_oper_recl
               AND ror.care_oid_cabe_recl = rc.oid_cabe_recl
               and ROR.TIOP_OID_TIPO_OPER = RTO.OID_TIPO_OPER
               and RTO.ROPE_OID_OPER = RO.OID_OPER
               AND bol.ind_recl = 1   ---- 'S' - Indicador de usado
               AND bol.oid_line_recl = varoidlinrec ---- r_detallefactura.oid_line_oper_recl
               AND rownum = 1;
          EXCEPTION
            WHEN no_data_found THEN
              varoidboleelec := 0;
              varvalfechrefe := NULL;
              varvalnumrecl  := NULL;
              varvalfolirefe := NULL;
              varvalnumesoli := null;
              valtiporefe    := 'M';
              valcodoper     := '';
              --- Genera numero de folio con X para los registros que no existen
              select '0' || lpad(int_rcf_seq.NEXTVAL, 8, '0')
              into varvalfolirefe
              from dual;

              --- busca el numero del CDR con el oid del reclamo
              BEGIN
                  SELECT rc.num_recl,psc.val_nume_soli,ro.cod_oper,psc.fec_fact
                    INTO varvalnumrecl, varvalnumesoli,valcodoper,varvalfechrefe
                    FROM rec_linea_opera_recla rlo,
                         rec_opera_recla       ror,
                         rec_cabec_recla       rc,
                         ped_solic_cabec       psc,
                         rec_tipos_opera       rto,
                         rec_opera             ro
                   WHERE rlo.opre_oid_oper_recl = ror.oid_oper_recl
                     AND ror.care_oid_cabe_recl = rc.oid_cabe_recl
                     and rc.soca_oid_soli_cabe = psc.oid_soli_cabe
                     and ROR.TIOP_OID_TIPO_OPER = RTO.OID_TIPO_OPER
                     and RTO.ROPE_OID_OPER = RO.OID_OPER
                     and rlo.oid_line_oper_recl = varoidlinrec; ---- r_detallefactura.oid_line_oper_recl;
              EXCEPTION
                WHEN no_data_found THEN
                     varvalnumrecl  := NULL;
                     varvalnumesoli := null;
                     valcodoper     := '';
              END;
          END;

          ---- Si es Trueque le coloca motivo 3
          if (valcodoper in ('TM','MT','TP','PT')) then valmoti := '3';
          end if;

          INSERT INTO ped_nota_credi_elect
            (oid_nocr_elec,
             oid_peri,
             cod_peri,
             oid_clie,
             cod_clie,
             cod_terr,
             val_nomb,
             num_docu_iden,
             val_dire,
             val_vill,
             val_comu,
             fec_proc,
             val_nume_soli,
             num_recl,
             num_abon,
             val_codi_vent,
             num_unid,
             val_prec_cata,
             cod_prod,
             des_prod,
             val_prec_sin_impu,
             val_impo_impu,
             val_prec_tota,
             val_foli_refe,
             val_fech_refe,
             val_tipo_refe,
             val_moti,
             val_foli,
             val_estr,
             oid_soli_posi_pedi,
             oid_soli_posi_recl)
          VALUES
            (PED_NOCR_SEQ.nextval,----ped_boel_seq.nextval,
             varoidperi,  ----- r_facturacabecera.oid_peri,
             varcodperi,  ----- r_facturacabecera.cod_peri,
             r_facturacabecera.oid_clie,
             r_facturacabecera.cod_clie,
             r_facturacabecera.cod_terr,
             r_facturacabecera.nom_comp,
             r_facturacabecera.val_nume_iden_fisc,
             r_facturacabecera.val_dire_comp,
             r_facturacabecera.val_vill, ----'', --- VAL_VILL
             r_facturacabecera.val_comu, ---'', --- VAL_COMU
             r_facturacabecera.fec_emis,
             varvalnumesoli,          ------r_facturacabecera.val_nume_soli,
             varvalnumrecl, --- NUM_RECL,
             substr(r_facturacabecera.val_nume_soli,
                    3,
                    8), --- NUM_ABON,
             varcodvent, ---- r_detallefactura.val_codi_vent,
             1,
             r_detallefactura.val_prec_cata_unit_fact,
             r_detallefactura.cod_sap,
             r_detallefactura.des_prod,
             r_detallefactura.val_prec_sin_impu_unit,
             r_detallefactura.imp_impu_unit_loca, --- VAL_IMPO_IMPU
             r_detallefactura.val_prec_sin_impu_unit + r_detallefactura.imp_impu_unit_loca,
             varvalfolirefe, --- VAL_FOLI_REFE
             varvalfechrefe, --- VAL_FECH_REFE
             valtiporefe,    --- VAL_TIPO_REFE
             valmoti,        --- VAL_MOTI
             NULL,
             NULL,
             r_detallefactura.oid_soli_posi,
             varoidlinrec ----- r_detallefactura.oid_line_oper_recl
             );

          --- Actualiza la Boleta eletronica como procesada
          IF varoidboleelec <> 0 THEN
            UPDATE ped_bolet_elect_histo SET ind_recl = 2 WHERE oid_bole_elec = varoidboleelec;
            ----UPDATE ped_bolet_elect_histo SET ind_recl = 'P' WHERE oid_bole_elec = varoidboleelec;
          END IF;

        --RETURN;
        END LOOP;
      END LOOP;
      CLOSE c_detallefactura;

      ---- Insera Flete por nota de credito

      if nvl(r_facturacabecera.imp_flet_tota_loca,0)<>0 then

          --- Recupera informacion de la boleta electronica asignada al flete
          BEGIN
            SELECT bol.val_foli,bol.fec_proc
              INTO varvalfolirefe,varvalfechrefe
              FROM ped_bolet_elect_histo bol
             WHERE bol.val_nume_soli = varvalnumesoli
               AND bol.val_desc_prod = 'FLETE'
               AND rownum = 1;
          EXCEPTION
            WHEN no_data_found THEN
              varvalfechrefe := NULL;
              varvalfolirefe := NULL;
              --- Genera numero de folio con X para los registros que no existen
              select '0' || lpad(int_rcf_seq.NEXTVAL, 8, '0')
              into varvalfolirefe
              from dual;
          END;

          INSERT INTO ped_nota_credi_elect
            (oid_nocr_elec,
             oid_peri,
             cod_peri,
             oid_clie,
             cod_clie,
             cod_terr,
             val_nomb,
             num_docu_iden,
             val_dire,
             val_vill,
             val_comu,
             fec_proc,
             val_nume_soli,
             num_recl,
             num_abon,
             val_codi_vent,
             num_unid,
             val_prec_cata,
             cod_prod,
             des_prod,
             val_prec_sin_impu,
             val_impo_impu,
             val_prec_tota,
             val_foli_refe,
             val_fech_refe,
             val_tipo_refe,
             val_moti,
             val_foli,
             val_estr,
             oid_soli_posi_pedi,
             oid_soli_posi_recl)
          VALUES
            (PED_NOCR_SEQ.nextval,----ped_boel_seq.nextval,
             varoidperi,  ----- r_facturacabecera.oid_peri,
             varcodperi,  ----- r_facturacabecera.cod_peri,
             r_facturacabecera.oid_clie,
             r_facturacabecera.cod_clie,
             r_facturacabecera.cod_terr,
             r_facturacabecera.nom_comp,
             r_facturacabecera.val_nume_iden_fisc,
             r_facturacabecera.val_dire_comp,
             r_facturacabecera.val_vill, ----'', --- VAL_VILL
             r_facturacabecera.val_comu, ---'', --- VAL_COMU
             r_facturacabecera.fec_emis,
             varvalnumesoli,          ------r_facturacabecera.val_nume_soli,
             varvalnumrecl, --- NUM_RECL,
             substr(r_facturacabecera.val_nume_soli,
                    3,
                    8), --- NUM_ABON,
             null,      --- r_detallefactura.val_codi_vent,
             0,         --- 1,
             abs(r_facturacabecera.imp_flet_tota_loca),  ----r_detallefactura.val_prec_cata_unit_fact,
             null,      --- r_detallefactura.cod_sap,
             'FLETE',   --- r_detallefactura.des_prod,
             abs(r_facturacabecera.imp_flet_impu_tota_loca),         --- r_detallefactura.val_prec_sin_impu_unit,
             abs(r_facturacabecera.imp_flet_tota_loca-r_facturacabecera.imp_flet_impu_tota_loca),         --- r_detallefactura.imp_impu_unit_loca, --- VAL_IMPO_IMPU
             abs(r_facturacabecera.imp_flet_tota_loca),         --- r_detallefactura.val_prec_sin_impu_unit + r_detallefactura.imp_impu_unit_loca,
             varvalfolirefe, --- VAL_FOLI_REFE
             varvalfechrefe, --- VAL_FECH_REFE
             'A',    --- VAL_TIPO_REFE
             '1',        --- VAL_MOTI
             NULL,
             NULL,
             0,              ---- r_detallefactura.oid_soli_posi,
             0               ---- r_detallefactura.oid_line_oper_recl
             );



      end if;

      -- Fin de Flete

      ---- Insera INTERES por nota de credito

      if nvl(r_facturacabecera.val_tota_gast_admi_sin_impu,0)<>0 then

          --- Recupera informacion de la boleta electronica asignada al flete
          BEGIN
            SELECT bol.val_foli,bol.fec_proc
              INTO varvalfolirefe,varvalfechrefe
              FROM ped_bolet_elect_histo bol
             WHERE bol.val_nume_soli = varvalnumesoli
               AND bol.val_desc_prod = 'INTERES'
               AND rownum = 1;
          EXCEPTION
            WHEN no_data_found THEN
              varvalfechrefe := NULL;
              varvalfolirefe := NULL;
              --- Genera numero de folio con X para los registros que no existen
              select '0' || lpad(int_rcf_seq.NEXTVAL, 8, '0')
              into varvalfolirefe
              from dual;
          END;

          INSERT INTO ped_nota_credi_elect
            (oid_nocr_elec,
             oid_peri,
             cod_peri,
             oid_clie,
             cod_clie,
             cod_terr,
             val_nomb,
             num_docu_iden,
             val_dire,
             val_vill,
             val_comu,
             fec_proc,
             val_nume_soli,
             num_recl,
             num_abon,
             val_codi_vent,
             num_unid,
             val_prec_cata,
             cod_prod,
             des_prod,
             val_prec_sin_impu,
             val_impo_impu,
             val_prec_tota,
             val_foli_refe,
             val_fech_refe,
             val_tipo_refe,
             val_moti,
             val_foli,
             val_estr,
             oid_soli_posi_pedi,
             oid_soli_posi_recl)
          VALUES
            (PED_NOCR_SEQ.nextval,----ped_boel_seq.nextval,
             varoidperi,  ----- r_facturacabecera.oid_peri,
             varcodperi,  ----- r_facturacabecera.cod_peri,
             r_facturacabecera.oid_clie,
             r_facturacabecera.cod_clie,
             r_facturacabecera.cod_terr,
             r_facturacabecera.nom_comp,
             r_facturacabecera.val_nume_iden_fisc,
             r_facturacabecera.val_dire_comp,
             r_facturacabecera.val_vill, ----'', --- VAL_VILL
             r_facturacabecera.val_comu, ---'', --- VAL_COMU
             r_facturacabecera.fec_emis,
             varvalnumesoli,          ------r_facturacabecera.val_nume_soli,
             varvalnumrecl, --- NUM_RECL,
             substr(r_facturacabecera.val_nume_soli,
                    3,
                    8), --- NUM_ABON,
             null,      --- r_detallefactura.val_codi_vent,
             0,         --- 1,
             abs(r_facturacabecera.val_tota_gast_admi),  ----r_detallefactura.val_prec_cata_unit_fact,
             null,      --- r_detallefactura.cod_sap,
             'INTERES',   --- r_detallefactura.des_prod,
             abs(r_facturacabecera.val_tota_gast_admi_sin_impu),  --- r_detallefactura.val_prec_sin_impu_unit,
             abs(r_facturacabecera.val_tota_impu_gast_admi),      --- r_detallefactura.imp_impu_unit_loca, --- VAL_IMPO_IMPU
             abs(r_facturacabecera.val_tota_gast_admi),           --- r_detallefactura.val_prec_sin_impu_unit + r_detallefactura.imp_impu_unit_loca,
             varvalfolirefe, --- VAL_FOLI_REFE
             varvalfechrefe, --- VAL_FECH_REFE
             'A',    --- VAL_TIPO_REFE
             '1',        --- VAL_MOTI
             NULL,
             NULL,
             0,              ---- r_detallefactura.oid_soli_posi,
             0               ---- r_detallefactura.oid_line_oper_recl
             );

      end if;

      -- Fin de Interes

    END LOOP;
    CLOSE c_facturacabecera;

  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_genera_bol_elec_rec: ' || ls_sqlerrm);
  END fac_pr_genera_bol_elec_rec;

  /**************************************************************************
  Descripcion        : Calcula Dias de Entrga de Pedido
  Fecha Creacion     : 30/01/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_obt_dias_fecha_ent
  (
    pnoidsoli NUMBER,
    pnremesa NUMBER,
    pscodperi VARCHAR2,
    lsfecfact DATE
  ) RETURN NUMBER IS

  lnnumdias NUMBER:=0;
  --lsfecfact date;
  lnferia number:=0;
  tmp NUMBER;
  ln_dia number;
  psCodigoPais  varchar2(3);

  BEGIN

    /*select fec_fact
    into lsfecfact
    from ped_solic_cabec where oid_soli_cabe=pnoidsoli;*/

     select cod_pais into psCodigoPais from bas_ctrl_fact where sta_camp = '0' and ind_camp_act='1';

    BEGIN
      select h.val_dias_desf
      into lnnumdias
      from
      ped_solic_cabec a,
      zon_terri_admin b,
      zon_secci c,
      zon_zona d,
      zon_regio e,
      cra_perio f,
      seg_perio_corpo g,
      sat_execp_fecee h,
      zon_terri i
       where a.ztad_oid_terr_admi=b.oid_terr_admi
           and a.oid_soli_cabe=pnoidsoli
       and b.zscc_oid_secc=c.oid_secc
       and b.terr_oid_terr=i.oid_terr
       and c.zzon_oid_zona=d.oid_zona
       and d.zorg_oid_regi = e.oid_regi
       and a.perd_oid_peri=f.oid_peri
       and f.peri_oid_peri=g.oid_peri
       and nvl(to_date(h.fec_exce,'yyyymmdd'),a.fec_prog_fact)=a.fec_prog_fact
       and e.cod_regi=decode(h.cod_regi, null, e.cod_regi, h.cod_regi)
       and d.cod_zona=decode(h.cod_zona, null, d.cod_zona, h.cod_zona)
       and c.cod_secc=decode(h.cod_secc, null, c.cod_secc, h.cod_secc)
       and i.cod_terr=decode(h.cod_terr, null, i.cod_terr, h.cod_terr)
       and (case when exists (select 1 from mae_clien_tipo_subti x, mae_clien_clasi y
           , lar_tipo_clien_vip z where x.oid_clie_tipo_subt=y.ctsu_oid_clie_tipo_subt
           and y.clas_oid_clas=z.oid_clas and y.tccl_oid_tipo_clasi=z.oid_tipo_clas
           and x.clie_oid_clie=a.clie_oid_clie
           ) then 'B' else 'N' end = h.tip_cons or h.tip_cons is null)
        ;
    EXCEPTION
      WHEN no_data_found THEN

          begin

          select h.val_dias_desf
          into lnnumdias
          from
          ped_solic_cabec a,
          zon_terri_admin b,
          zon_secci c,
          zon_zona d,
          zon_regio e,
          cra_perio f,
          seg_perio_corpo g,
          sat_param_calcu_fecee h,
          zon_terri i
           where a.ztad_oid_terr_admi=b.oid_terr_admi
           and a.oid_soli_cabe=pnoidsoli
           and b.zscc_oid_secc=c.oid_secc
           and b.terr_oid_terr=i.oid_terr
           and c.zzon_oid_zona=d.oid_zona
           and d.zorg_oid_regi = e.oid_regi
           and a.perd_oid_peri=f.oid_peri
           and f.peri_oid_peri=g.oid_peri
           and nvl(h.cod_camp,pscodperi)=pscodperi
           and e.cod_regi=decode(h.cod_regi, null, e.cod_regi, h.cod_regi)
           and d.cod_zona=decode(h.cod_zona, null, d.cod_zona, h.cod_zona)
           and c.cod_secc=decode(h.cod_secc, null, c.cod_secc, h.cod_secc)
           and i.cod_terr=decode(h.cod_terr, null, i.cod_terr, h.cod_terr)
           and h.dia_fact=pnremesa
           and (case when exists (select 1 from mae_clien_tipo_subti x, mae_clien_clasi y
           , lar_tipo_clien_vip z where x.oid_clie_tipo_subt=y.ctsu_oid_clie_tipo_subt
           and y.clas_oid_clas=z.oid_clas and y.tccl_oid_tipo_clasi=z.oid_tipo_clas
           and x.clie_oid_clie=a.clie_oid_clie
           ) then 'B' else 'N' end = h.tip_cons or h.tip_cons is null)
           ;

            EXCEPTION
              WHEN no_data_found THEN

               IF (gen_pkg_gener.gen_fn_param_pais(psCodigoPais,'LAS','001') = '0') THEN
                      begin

                      select nvl(max(h.val_dias_desf),0)
                      into lnnumdias
                      from
                      ped_solic_cabec a,
                      zon_terri_admin b,
                      zon_secci c,
                      zon_zona d,
                      zon_regio e,
                      cra_perio f,
                      seg_perio_corpo g,
                      sat_param_calcu_fecee h,
                      zon_terri i
                       where a.ztad_oid_terr_admi=b.oid_terr_admi
                       and a.oid_soli_cabe=pnoidsoli
                       and b.zscc_oid_secc=c.oid_secc
                       and b.terr_oid_terr=i.oid_terr
                       and c.zzon_oid_zona=d.oid_zona
                       and d.zorg_oid_regi = e.oid_regi
                       and a.perd_oid_peri=f.oid_peri
                       and f.peri_oid_peri=g.oid_peri
                       and e.cod_regi=decode(h.cod_regi, null, e.cod_regi, h.cod_regi)
                       and d.cod_zona=decode(h.cod_zona, null, d.cod_zona, h.cod_zona)
                       and c.cod_secc=decode(h.cod_secc, null, c.cod_secc, h.cod_secc)
                       and i.cod_terr=decode(h.cod_terr, null, i.cod_terr, h.cod_terr)
                       and nvl(h.cod_camp,pscodperi)=pscodperi
                       and pnremesa <h.dia_fact
                       and (case when exists (select 1 from mae_clien_tipo_subti x, mae_clien_clasi y
                       , lar_tipo_clien_vip z where x.oid_clie_tipo_subt=y.ctsu_oid_clie_tipo_subt
                       and y.clas_oid_clas=z.oid_clas and y.tccl_oid_tipo_clasi=z.oid_tipo_clas
                       and x.clie_oid_clie=a.clie_oid_clie
                       ) then 'B' else 'N' end = h.tip_cons or h.tip_cons is null)
                       ;

                      EXCEPTION
                        WHEN no_data_found THEN
                          select max(h.val_dias_desf) into lnnumdias
                          from sat_param_calcu_fecee h
                          where h.cod_camp='XXXXXX'
                          and h.cod_regi='XX'
                          and h.cod_zona='XXXX'
                          and h.cod_secc='X'
                          and h.cod_terr='XXXXXX'
                          and h.tip_cons='X'
                          and h.dia_fact='1';
                      END;
             else

                      begin

                      select nvl(max(h.dia_fact),0)
                      into ln_dia
                      from
                      ped_solic_cabec a,
                      zon_terri_admin b,
                      zon_secci c,
                      zon_zona d,
                      zon_regio e,
                      cra_perio f,
                      seg_perio_corpo g,
                      sat_param_calcu_fecee h,
                      zon_terri i
                       where a.ztad_oid_terr_admi=b.oid_terr_admi
                       and a.oid_soli_cabe=pnoidsoli
                       and b.zscc_oid_secc=c.oid_secc
                       and b.terr_oid_terr=i.oid_terr
                       and c.zzon_oid_zona=d.oid_zona
                       and d.zorg_oid_regi = e.oid_regi
                       and a.perd_oid_peri=f.oid_peri
                       and f.peri_oid_peri=g.oid_peri
                       and e.cod_regi=decode(h.cod_regi, null, e.cod_regi, h.cod_regi)
                       and d.cod_zona=decode(h.cod_zona, null, d.cod_zona, h.cod_zona)
                       and c.cod_secc=decode(h.cod_secc, null, c.cod_secc, h.cod_secc)
                       and i.cod_terr=decode(h.cod_terr, null, i.cod_terr, h.cod_terr)
                       and nvl(h.cod_camp,pscodperi)=pscodperi
                       and pnremesa <> h.dia_fact
                       and (case when exists (select 1 from mae_clien_tipo_subti x, mae_clien_clasi y
                       , lar_tipo_clien_vip z where x.oid_clie_tipo_subt=y.ctsu_oid_clie_tipo_subt
                       and y.clas_oid_clas=z.oid_clas and y.tccl_oid_tipo_clasi=z.oid_tipo_clas
                       and x.clie_oid_clie=a.clie_oid_clie
                       ) then 'B' else 'N' end = h.tip_cons or h.tip_cons is null)
                       ;

                      select nvl(max(h.val_dias_desf),0)
                      into lnnumdias
                      from
                      ped_solic_cabec a,
                      zon_terri_admin b,
                      zon_secci c,
                      zon_zona d,
                      zon_regio e,
                      cra_perio f,
                      seg_perio_corpo g,
                      sat_param_calcu_fecee h,
                      zon_terri i
                       where a.ztad_oid_terr_admi=b.oid_terr_admi
                       and a.oid_soli_cabe=pnoidsoli
                       and b.zscc_oid_secc=c.oid_secc
                       and b.terr_oid_terr=i.oid_terr
                       and c.zzon_oid_zona=d.oid_zona
                       and d.zorg_oid_regi = e.oid_regi
                       and a.perd_oid_peri=f.oid_peri
                       and f.peri_oid_peri=g.oid_peri
                       and e.cod_regi=decode(h.cod_regi, null, e.cod_regi, h.cod_regi)
                       and d.cod_zona=decode(h.cod_zona, null, d.cod_zona, h.cod_zona)
                       and c.cod_secc=decode(h.cod_secc, null, c.cod_secc, h.cod_secc)
                       and i.cod_terr=decode(h.cod_terr, null, i.cod_terr, h.cod_terr)
                       and nvl(h.cod_camp,pscodperi)=pscodperi
                       and ln_dia  = h.dia_fact
                       and (case when exists (select 1 from mae_clien_tipo_subti x, mae_clien_clasi y
                       , lar_tipo_clien_vip z where x.oid_clie_tipo_subt=y.ctsu_oid_clie_tipo_subt
                       and y.clas_oid_clas=z.oid_clas and y.tccl_oid_tipo_clasi=z.oid_tipo_clas
                       and x.clie_oid_clie=a.clie_oid_clie
                       ) then 'B' else 'N' end = h.tip_cons or h.tip_cons is null)
                       ;

                      EXCEPTION
                        WHEN no_data_found THEN
                          select max(h.val_dias_desf) into lnnumdias
                          from sat_param_calcu_fecee h
                          where h.cod_camp='XXXXXX'
                          and h.cod_regi='XX'
                          and h.cod_zona='XXXX'
                          and h.cod_secc='X'
                          and h.cod_terr='XXXXXX'
                          and h.tip_cons='X'
                          and h.dia_fact='1';
                      END;

             end if;
            end;
    END;

    select count(distinct(fec_feri)) into lnferia
    from cra_feria x where x.fec_feri>lsfecfact
    and x.fec_feri<=lsfecfact+lnnumdias
    and x.ind_trap=1;

    select count(1) into tmp from cra_feria where fec_feri=lsfecfact+lnnumdias+lnferia and ind_trap=1;

    while  tmp>0
    loop
      lnferia:=lnferia+1;
      select count(1) into tmp from cra_feria where fec_feri=lsfecfact+lnnumdias+lnferia and ind_trap=1;
    end loop;

    RETURN lnnumdias+lnferia;

  EXCEPTION

    WHEN OTHERS THEN

      lnnumdias:=0;
      return lnnumdias;
      /*ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ped_fn_obt_dias_fecha_ent: ' || ls_sqlerrm);*/

  END ped_fn_obt_dias_fecha_ent;

  /**************************************************************************
  Descripcion        : Calcula Dias de Entrga de Pedido a partir de la fecha de la zona de facturacion.
  Fecha Creacion     : 30/08/2013
  Autor              : Rosalvina Ramirez
  ***************************************************************************/

 FUNCTION ped_fn_obt_dias_fecha_ent2
  (
    psCodPais VARCHAR2,
    psCodRegi VARCHAR2,
    psCodZona VARCHAR2,
    psCodSecc VARCHAR2,
    psCodTerr VARCHAR2,
    pnremesa NUMBER,
    pscodperiActual VARCHAR2,
    psCodigoCliente VARCHAR2
  ) RETURN DATE IS

  lnnumdias NUMBER:=0;
  lsfecfact date;
  lnferia number:=0;
  tmp NUMBER;
  lvCodPeri VARCHAR2(6);

  BEGIN

   BEGIN
      select  pscodperiActual, psc.fec_fact
      into lvCodPeri, lsfecfact
      from ped_solic_cabec psc, mae_clien mc
      where psc.clie_oid_clie = mc.oid_clie
      and mc.cod_clie=psCodigoCliente
      and psc.tspa_oid_tipo_soli_pais = 2001
      and psc.perd_oid_peri = FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCodPais, pscodperiActual);
   EXCEPTION
   WHEN no_data_found THEN
     lvCodPeri:=FIN_PKG_GENER.FIN_FN_OBTIE_NSGTE_CAMPA(pscodperiActual,1);

    select cr.fec_inic
      into lsfecfact
      from cra_crono cr, cra_activ ca, zon_zona zz
     where cr.cact_oid_acti = ca.oid_acti
       and ca.pais_oid_pais = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(psCodPais)
       and cr.perd_oid_peri =
           FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(psCodPais, lvCodPeri)
       and cr.zzon_oid_zona = zz.oid_zona
       and zz.cod_zona = psCodZona
       and ca.cod_acti = 'FA';

   END;

   begin
          select h.val_dias_desf
          into lnnumdias
          from
          sat_param_calcu_fecee h
           where
           nvl(h.cod_camp, lvCodPeri)=lvCodPeri
           and h.cod_regi = psCodRegi
           and h.cod_zona = psCodZona
           and h.cod_secc = psCodSecc
           and to_number(h.cod_terr,999999) = psCodTerr
           and h.dia_fact=pnRemesa
           and (case when exists (select 1 from mae_clien_tipo_subti x, mae_clien_clasi y
           , lar_tipo_clien_vip z,  mae_clien mc where x.oid_clie_tipo_subt=y.ctsu_oid_clie_tipo_subt
           and y.clas_oid_clas=z.oid_clas and y.tccl_oid_tipo_clasi=z.oid_tipo_clas
           and x.clie_oid_clie=mc.oid_clie
           and mc.cod_clie = psCodigoCliente
           ) then 'B' else 'N' end = h.tip_cons or h.tip_cons is null)
           ;

            EXCEPTION
              WHEN no_data_found THEN
                      select nvl(max(h.val_dias_desf),0)
                      into lnnumdias
                      from
                      sat_param_calcu_fecee h
                       where
                        h.cod_regi = psCodRegi
                       and h.cod_zona = psCodZona
                       and h.cod_secc = psCodSecc
                       and to_number(h.cod_terr,999999) = psCodTerr
                       and nvl(h.cod_camp,lvCodPeri)=lvCodPeri
                       and pnremesa>h.dia_fact
                       and (case when exists (select 1 from mae_clien_tipo_subti x, mae_clien_clasi y
                       , lar_tipo_clien_vip z , mae_clien mc where x.oid_clie_tipo_subt=y.ctsu_oid_clie_tipo_subt
                       and y.clas_oid_clas=z.oid_clas and y.tccl_oid_tipo_clasi=z.oid_tipo_clas
                       and x.clie_oid_clie=mc.oid_clie
                       and mc.cod_clie = psCodigoCliente
                       ) then 'B' else 'N' end = h.tip_cons or h.tip_cons is null)
                       ;
          end;

    select count(distinct(fec_feri)) into lnferia
    from cra_feria x where x.fec_feri>lsfecfact
    and x.fec_feri<=lsfecfact+lnnumdias
    and x.ind_trap=1;

    select count(1) into tmp from cra_feria where fec_feri=lsfecfact+lnnumdias+lnferia and ind_trap=1;

    while  tmp>0
    loop
      lnferia:=lnferia+1;
      select count(1) into tmp from cra_feria where fec_feri=lsfecfact+lnnumdias+lnferia and ind_trap=1;
    end loop;

    RETURN lsfecfact + lnnumdias + lnferia;

  EXCEPTION

    WHEN OTHERS THEN
      RETURN lsfecfact;

  END ped_fn_obt_dias_fecha_ent2;
  /**************************************************************************
  Descripcion        : Calcula Dias de Entrga de Pedido a partir de la fecha de la zona de facturacion.
  Fecha Creacion     : 30/08/2013
  Autor              : Rosalvina Ramirez
  ***************************************************************************/

 FUNCTION ped_fn_obt_dias_fecha_ent3
  (
    psCodRegi VARCHAR2,
    psCodZona VARCHAR2,
    psCodSecc VARCHAR2,
    psCodTerr VARCHAR2,
    pnremesa NUMBER,
    pscodperiActual VARCHAR2
  ) RETURN DATE IS

  lnnumdias NUMBER:=0;
  lsfecfact date;
  lnferia number:=0;
  tmp NUMBER;


  BEGIN



   lsfecfact:=ped_fn_dev_dia_fact(pscodperiActual,psCodZona,pnremesa);




   begin
          select nvl(max(h.val_dias_desf),0)
          into lnnumdias
          from
          sat_param_calcu_fecee h
           where
           nvl(h.cod_camp,pscodperiActual)=pscodperiActual
           and h.cod_regi = psCodRegi
           and h.cod_zona = psCodZona
           and (h.cod_secc = psCodSecc or h.cod_secc is null)
           and (lpad(h.cod_terr,6,'0') = psCodTerr or h.cod_terr is null)
           and h.dia_fact=pnRemesa
           and h.tip_cons='N'
           ;

            EXCEPTION
              WHEN no_data_found THEN
                      select nvl(max(h.val_dias_desf),0)
                      into lnnumdias
                      from
                      sat_param_calcu_fecee h
                       where
                        h.cod_regi = psCodRegi
                       and h.cod_zona = psCodZona
                       and (h.cod_secc = psCodSecc or h.cod_secc is null)
                       and (to_number(h.cod_terr,999999) = psCodTerr or h.cod_terr is null)
                       and nvl(h.cod_camp,pscodperiActual)=pscodperiActual
                       and pnremesa>h.dia_fact
                       and (case when exists (select 1 from mae_clien_tipo_subti x, mae_clien_clasi y
                       , lar_tipo_clien_vip z , mae_clien mc where x.oid_clie_tipo_subt=y.ctsu_oid_clie_tipo_subt
                       and y.clas_oid_clas=z.oid_clas and y.tccl_oid_tipo_clasi=z.oid_tipo_clas
                       and x.clie_oid_clie=mc.oid_clie
                       and mc.cod_clie = ''
                       ) then 'B' else 'N' end = h.tip_cons or h.tip_cons is null)
                       ;
          end;

    select count(distinct(fec_feri)) into lnferia
    from cra_feria x where x.fec_feri>lsfecfact
    and x.fec_feri<=lsfecfact+lnnumdias
    and x.ind_trap=1;

    select count(1) into tmp from cra_feria where fec_feri=lsfecfact+lnnumdias+lnferia and ind_trap=1;

    while  tmp>0
    loop
      lnferia:=lnferia+1;
      select count(1) into tmp from cra_feria where fec_feri=lsfecfact+lnnumdias+lnferia and ind_trap=1;
    end loop;

    RETURN lsfecfact + lnnumdias + lnferia;

  END ped_fn_obt_dias_fecha_ent3;

  /**************************************************************************
  Descripcion        : Devuelve el dia de facturacion.
  Fecha Creacion     : 10/09/2014
  Autor              : Rosalvina Ramirez
  ***************************************************************************/

  FUNCTION ped_fn_dev_dia_fact
  (
    pscodperi VARCHAR2,
    pscodzona VARCHAR2,
    pnremesa NUMBER
  ) RETURN DATE IS

  lsfecfact date;
  lnferia number;
  tmp number;
  lscodpais varchar2(10);



  BEGIN


select distinct cod_pais into lscodpais from bas_ctrl_fact;

    select cr.fec_inic
      into lsfecfact
      from cra_crono cr, cra_activ ca, zon_zona zz
     where cr.cact_oid_acti = ca.oid_acti
       and ca.pais_oid_pais = GEN_PKG_GENER.GEN_FN_DEVUELVE_ID_PAIS(lscodpais)
       and cr.perd_oid_peri =
           FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(lscodpais,pscodperi)
       and cr.zzon_oid_zona = zz.oid_zona
       and zz.cod_zona = psCodZona
       and ca.cod_acti = 'FA';


    select count(distinct(fec_feri)) into lnferia
    from cra_feria x where x.fec_feri>lsfecfact
    and x.fec_feri<=lsfecfact+(pnremesa-1)
    and x.ind_no_labo=1;

    select count(1) into tmp from cra_feria where fec_feri=lsfecfact+(pnremesa-1)+lnferia and ind_no_labo=1;

    while  tmp>0
    loop
      lnferia:=lnferia+1;
      select count(1) into tmp from cra_feria where fec_feri=lsfecfact+(pnremesa-1)+lnferia and ind_no_labo=1;
    end loop;

    RETURN lsfecfact + (pnremesa-1) + lnferia;
      RETURN lsfecfact;


  EXCEPTION

    WHEN OTHERS THEN
      RETURN trunc(sysdate);

  END ped_fn_dev_dia_fact;

  /***************************************************************************
  Descripcion       : Genera Fecha de Reparto
  Fecha Creacion    : 22/04/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_fecrep_z
  (
    p_oidzona          NUMBER,
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  ) IS

    CURSOR c_cons
    (
      p_oidperi NUMBER,
      p_fecfact VARCHAR2
    ) IS
      SELECT a.oid_soli_cabe
        FROM ped_solic_cabec a
       WHERE a.perd_oid_peri = p_oidperi
         AND a.fec_fact = to_date(p_fecfact,
                                  'dd/mm/yyyy')
         --AND a.ind_inte_lari_gene = 0
         AND a.num_unid_aten_tota > 0
         AND a.ind_ts_no_conso = 0
         AND a.zzon_oid_zona = p_oidzona
         AND a.tspa_oid_tipo_soli_pais IN (SELECT x.oid_tipo_soli_pais
                                             FROM ped_tipo_solic_pais x,
                                                  ped_tipo_solic      y
                                            WHERE x.tsol_oid_tipo_soli = y.oid_tipo_soli
                                              AND y.ind_cons = 1
                                              AND y.ind_soli_nega = 0);

    r_cons c_cons%ROWTYPE;

    ln_valtasa     NUMBER(2);
    ln_oidtasa     NUMBER(10);
    ln_valtasaflet NUMBER(2);
    --ln_oidtasaflet    NUMBER(10);
    --w_filas        NUMBER(12);

    lv_codpais VARCHAR2(20);
    ln_oidperi NUMBER;
    --w_filas        NUMBER(12);

  BEGIN

    ln_oidperi := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodperi);

    SELECT z.cod_pais
      INTO lv_codpais
      FROM cra_perio       x,
           seg_perio_corpo y,
           bas_ctrl_fact   z
     WHERE x.peri_oid_peri = y.oid_peri
       AND y.cod_peri = z.cod_peri
       AND z.ind_camp_act = 1
       AND z.sta_camp = 0;

    OPEN c_cons(ln_oidperi,
                psfechafacturacion);
    LOOP
      FETCH c_cons
        INTO r_cons;
      EXIT WHEN c_cons%NOTFOUND;

      fac_pr_genera_fecrep(r_cons.oid_soli_cabe,pscodperi);

    END LOOP;
    CLOSE c_cons;

    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR fac_pr_genera_fecrep_z: ' || ls_sqlerrm || 'p_oidzona:' ||
                              p_oidzona);
  END fac_pr_genera_fecrep_z;

  /***************************************************************************
  Descripcion       : Genera Fecha de Reparto
  Fecha Creacion    : 22/04/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_fecrep(p_oidcons NUMBER, p_codperi VARCHAR2) IS

  lnremesa NUMBER;
  ls_COD_COMP_TRAN VARCHAR2(50);
  ls_COD_CENT_ACOP VARCHAR2(50);
  ls_NOM_COMP_TRAN VARCHAR2(50);
  ls_NOM_CENT_ACOP VARCHAR2(50);
  ls_VAL_EMAI				VARCHAR2(50);

  lv_actividadRep VARCHAR2(100) := nvl(IMP_PKG_PROCE_GENER.IMP_FN_OBTIE_PARAM_IMPRE('LAS','actividadRep'),'RP');
  l_fec_fact                date;
  l_oid_zona                number;
  ln_diasRep                number(4):= 0;
  ln_desplazamiento         number(4):=0;

  BEGIN

    begin
    select a.fec_fact-b.fec_inic+1-
           (select count(distinct(fec_feri))
           from cra_feria x, cra_cabec_grupo_zona y, cra_detal_grupo_zona z, cra_activ z1
           where x.fec_feri<a.fec_fact
           and x.fec_feri>b.fec_inic
           and (x.ind_no_labo=1 or x.ind_fest=1)
           and x.cact_oid_acti=z1.oid_acti
           and z1.cod_acti='RF'
           and x.cgzo_oid_cabe_grup_zona=y.oid_cabe_grup_zona
           and y.oid_cabe_grup_zona=z.cgzo_oid_cabe_grup_zona
           and z.zzon_oid_zona=a.zzon_oid_zona
           ), a.fec_fact,a.zzon_oid_zona
    into lnremesa, l_fec_fact, l_oid_zona
    from ped_solic_cabec a, cra_crono b, cra_activ c
    where a.perd_oid_peri=b.perd_oid_peri
    and a.pais_oid_pais=c.pais_oid_pais
    and c.cod_acti='FA'
    and b.cact_oid_acti=c.oid_acti
    and a.zzon_oid_zona=b.zzon_oid_zona
    and a.oid_soli_cabe=p_oidcons;
    exception when others then
          lnremesa:=0;
    end;

if lnremesa<0 then
  lnremesa:=0;
end if;


    begin
    select h.cod_comp_tran, h.cod_cent_acop, h.nom_comp_tran, h.nom_cent_acop, h.val_emai
    into ls_COD_COMP_TRAN, ls_COD_CENT_ACOP, ls_NOM_COMP_TRAN, ls_NOM_CENT_ACOP, ls_VAL_EMAI
    from ped_solic_cabec a, zon_terri_admin b, zon_terri c--, zon_secci d,
    , sto_acopi_cober g, sto_centr_acopi h
    where a.oid_soli_cabe=p_oidcons
    and a.ztad_oid_terr_admi=b.oid_terr_admi
    and b.terr_oid_terr=c.oid_terr
    and ltrim(g.cod_terr,'0')=ltrim(c.cod_terr,'0')
    and g.cod_cent_acop=h.cod_cent_acop
    and h.ind_como is null
    and rownum=1
    ;
    exception when others then
    ls_COD_COMP_TRAN:='';
    ls_COD_CENT_ACOP:='';
    ls_NOM_COMP_TRAN:='';
    ls_NOM_CENT_ACOP:='';
    ls_VAL_EMAI:='';
    end;

   begin

   SELECT cc.fec_inic - cr.fec_inic - decode(cc.cod_tipo_dias,1,0,
                    (select count(*) from cra_feria cf where cf.cgzo_oid_cabe_grup_zona = ccg.oid_cabe_grup_zona
                and cf.cact_oid_acti = cc.cact_oid_acti
                and cf.fec_feri > cr.fec_inic
                and cf.fec_feri < cc.fec_inic
                and ((cf.ind_fest = 1 ) or (cf.ind_no_labo = 1)))
                )
      INTO ln_desplazamiento
      FROM cra_crono cc, CRA_PERIO CP, seg_perio_corpo spc, CRA_ACTIV act, cra_crono cr, cra_activ ca, cra_cabec_grupo_zona ccg, cra_detal_grupo_zona ccd
    WHERE cc.PERD_OID_PERI = cp.oid_peri
      AND cp.peri_oid_peri = spc.oid_peri
      AND spc.cod_peri = p_codperi
      AND cc.ZZON_OID_ZONA = l_oid_zona
      AND cc.CACT_OID_ACTI = act.OID_ACTI
        AND act.COD_ACTI = lv_actividadRep
        and cr.perd_oid_peri = cc.perd_oid_peri
        and cc.zzon_oid_zona = cr.zzon_oid_zona
        and cr.cact_oid_acti = ca.oid_acti
        and ca.cod_acti = 'FA'
        and ccg.oid_cabe_grup_zona =  ccd.cgzo_oid_cabe_grup_zona
        and ccd.zzon_oid_zona = cc.zzon_oid_zona;
    exception when others then
        ln_desplazamiento:=0;
    end;

    ln_diasRep := ped_fn_obt_dias_fecha_ent(p_oidcons, lnremesa,p_codperi, l_fec_fact);

    INSERT INTO ped_segui_pedid x
    (OID_SEGU_PEDI,
    SOCA_OID_SOLI_CABE,
    SOCA_OID_CONS,
    TDSE_OID_TIPO,
    HISE_OID_HITO,
    FEC,
    NUM_DOCU,
    IND_COMP,
    COD_COMP_TRAN,
    COD_CENT_ACOP,
    NOM_COMP_TRAN,
    NOM_CENT_ACOP,
    VAL_EMAI
    )
      SELECT ped_sepe_seq.nextval,
             a.oid_soli_cabe,
             NULL,
             '1',
             '2',
             case
               when (ln_diasRep>0) then
                    a.fec_fact + ln_diasRep
               else
                    a.fec_fact + ln_desplazamiento
               end,
             NULL,
             lnremesa,
             ls_COD_COMP_TRAN,
             ls_COD_CENT_ACOP,
             ls_NOM_COMP_TRAN,
             ls_NOM_CENT_ACOP,
             ls_VAL_EMAI
        FROM ped_solic_cabec a
       WHERE a.oid_soli_cabe=p_oidcons
       and not exists (select 1 from ped_segui_pedid where soca_oid_soli_cabe=p_oidcons);


  EXCEPTION
    WHEN OTHERS THEN
      ln_sqlcode := SQLCODE;


  END fac_pr_genera_fecrep;

 /***************************************************************************
  Descripcion       : Genera PUP por Producto
  Fecha Creacion    : 08/07/2013
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_pup
  (
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  ) IS


    CURSOR c_cons2
    (
      p_oidperi NUMBER
    ) IS
    select f.cod_sap, sum(b.num_unid_dema_real) pedi
    , sum(b.val_prec_cata_unit_loca*b.num_unid_dema_real) monto_cat
    from ped_solic_cabec a, ped_solic_posic b
    , ped_tipo_solic_pais d, ped_tipo_solic e
    , mae_produ f
    where a.perd_oid_peri=p_oidperi
    and a.oid_soli_cabe=b.soca_oid_soli_cabe
    and b.prod_oid_prod=f.oid_prod
    and a.tspa_oid_tipo_soli_pais=d.oid_tipo_soli_pais
    and d.tsol_oid_tipo_soli=e.oid_tipo_soli
    and e.cod_tipo_soli='SOC'
    group by f.cod_sap;

    r_cons2 c_cons2%ROWTYPE;

    ln_acum_ped_camp        NUMBER(7);
    ln_acum_ped_ant         NUMBER(7);
    ln_acum_prod_camp        NUMBER(7);
    ln_acum_prod_ant         NUMBER(7);

    lv_codpais VARCHAR2(20);
    ln_oidperi NUMBER;
    --w_filas        NUMBER(12);

  BEGIN

    SELECT z.cod_pais
      INTO lv_codpais
      FROM bas_ctrl_fact   z
     WHERE z.ind_camp_act = 1
       AND z.sta_camp = 0;

    ln_oidperi := gen_pkg_gener.gen_fn_devuelve_id_cra_perio2(pscodperi);

    select count(a.oid_soli_cabe) pedi into ln_acum_ped_camp
    from ped_solic_cabec a, ped_tipo_solic_pais d, ped_tipo_solic e
    where a.perd_oid_peri=ln_oidperi
    and a.tspa_oid_tipo_soli_pais=d.oid_tipo_soli_pais
    and d.tsol_oid_tipo_soli=e.oid_tipo_soli
    and e.cod_tipo_soli='SOC';


    BEGIN

    select y.num_pedi_acum into ln_acum_ped_ant
    from PED_ACUMU_PEDID_CAMPA y
    where y.cod_peri=pscodperi;


  EXCEPTION
    WHEN OTHERS THEN
      ln_acum_ped_ant:=NULL;
  END ;


if ln_acum_ped_ant is null then

      insert into PED_ACUMU_PEDID_CAMPA values (
      pscodperi,
      to_date(psfechafacturacion,'dd/mm/yyyy'),
      0,
      ln_acum_ped_camp
      );
else
    update PED_ACUMU_PEDID_CAMPA
    set fec_fact=to_date(psfechafacturacion,'dd/mm/yyyy'),
    num_pedi=ln_acum_ped_ant,
    num_pedi_acum=ln_acum_ped_camp
    where cod_peri=pscodperi;

end if;






            OPEN c_cons2(ln_oidperi);
            LOOP
              FETCH c_cons2
                INTO r_cons2;
              EXIT WHEN c_cons2%NOTFOUND;


                    BEGIN
                    select y.num_pedi_acum into ln_acum_prod_ant
                    from PED_ACUMU_PRODU_CAMPA y
                    where y.cod_peri=pscodperi
                    and y.cod_sap=r_cons2.cod_sap
                    ;
                  EXCEPTION
                    WHEN OTHERS THEN
                      ln_acum_prod_ant:=NULL;
                  END ;

              if ln_acum_prod_ant is null then

              insert into PED_ACUMU_PRODU_CAMPA values (
              pscodperi,
                    to_date(psfechafacturacion,'dd/mm/yyyy'),
              r_cons2.cod_sap,
              ln_acum_prod_ant,
              r_cons2.pedi,
                    round((r_cons2.pedi/ln_acum_ped_camp),2)
                    ,round((r_cons2.monto_cat/decode(r_cons2.pedi,0,1,r_cons2.pedi)),2)
              );

              else
                  update PED_ACUMU_PRODU_CAMPA
                  set fec_fact=to_date(psfechafacturacion,'dd/mm/yyyy'),
                  num_pedi=ln_acum_prod_ant,
                  num_pedi_acum=r_cons2.pedi,
                  pup=round((r_cons2.pedi/ln_acum_ped_camp),2)
                  , val_prom=round((r_cons2.monto_cat/decode(r_cons2.pedi,0,1,r_cons2.pedi)),2)
                  where cod_peri=pscodperi
                  and cod_sap=r_cons2.cod_sap
                  ;
              end if;

            END LOOP;
            CLOSE c_cons2;


    RETURN;
  EXCEPTION
    WHEN OTHERS THEN
      RETURN;
  END fac_pr_genera_pup;

 /**************************************************************************
  Descripcion        : Calcula IVA Asumido por Colombia
  Fecha Creacion     : 23/09/2013
  Autor              : Jorge Yepez
  ***************************************************************************/
  FUNCTION ped_fn_gener_iva_asum
  (
    pnoidcabe NUMBER,
    pntasa    NUMBER,
    pndecim   NUMBER,
    dato      NUMBER,
    valgratis NUMBER,
    valfacsiniva NUMBER
  ) RETURN NUMBER IS

--  valgratis NUMBER(12,2):=0;
--  valfacsiniva NUMBER(12,2):=0;
  valbase1 NUMBER(12,2):=0;
  valbase2 NUMBER(12,2):=0;
  valbase3 NUMBER(12,2):=0;
  resultado NUMBER(12,2):=0;

  BEGIN
/*       select a.val_prec_cont_tota_loca, a.val_prec_neto_tota_loca
       into valgratis, valfacsiniva
       from fac_docum_conta_cabec a where a.oid_cabe=pnoidcabe;   */

       valbase1:=valgratis+valfacsiniva;

       if valbase1>0 and (valgratis/valbase1)>(dato/100) then
          valbase2:=valbase1*(dato/100);
          valbase3:=valgratis-valbase2;
          resultado:=round(valbase3*(pntasa/100),pndecim);
       else
         resultado:=0;
       end if;


    RETURN resultado;

  EXCEPTION

    WHEN OTHERS THEN

      ln_sqlcode := SQLCODE;
      ls_sqlerrm := substr(SQLERRM,
                           1,
                           250);
      raise_application_error(-20123,
                              'ERROR ped_fn_gener_iva_asum: ' || ls_sqlerrm);

  END ped_fn_gener_iva_asum;

  /***************************************************************************
  Descripcion       : Genera Factura Global Mexico
  Fecha Creacion    : 28/10/2014
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_genera_fact_global
  (
    pscodperi          VARCHAR2,
    psfechafacturacion VARCHAR2
  ) IS
    CURSOR c_doclega IS
      SELECT
             1
        FROM dual
       ;

    r_doclega c_doclega%ROWTYPE;





  BEGIN

  NULL;

  END fac_pr_genera_fact_global;


  /***************************************************************************
  Descripcion       : Cambio en promesa de entrega
  Fecha Creacion    : 27/05/2015
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE fac_pr_cambio_fecha_prom
  (
    psvalnumesoli          VARCHAR2,
    psNuevafecha           VARCHAR2,
    psNuevoTurno           VARCHAR2
  ) IS



  BEGIN
      update ped_segui_pedid a
      set a.fec_orig=decode(a.fec_orig,null,a.fec,a.fec_orig),
      a.fec=trunc(to_date(psnuevafecha,'YYYY-MM-DD')),
      a.val_turn_orig=decode(a.val_turn_orig,null,a.val_turn,a.val_turn_orig),
      a.val_turn=psNuevoTurno
      where a.soca_oid_soli_cabe=(select oid_soli_cabe from ped_solic_cabec where val_nume_soli=psvalnumesoli);
      
  EXCEPTION when others then
  NULL;

  END fac_pr_cambio_fecha_prom;

  /***************************************************************************
  Descripcion       : Cambio en promesa de entrega
  Fecha Creacion    : 27/05/2015
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_actua_datos_ws
  (
    pscodclie      in        VARCHAR2,
    pscodplat      in        VARCHAR2,
    psfecentr      in        VARCHAR2,
    psindentr      in        VARCHAR2,
    pslatentr      in        VARCHAR2,
    pslonentr      in        VARCHAR2,
    psnoventr      in        VARCHAR2,
    psnropedi      in        VARCHAR2,
    psdesobse      in        VARCHAR2,
    pscodpais      in        VARCHAR2,
    psindinve      in        VARCHAR2,
    pstipentr      in        VARCHAR2
  ) IS

    lv_fecfact      DATE;
    lnactua         number:=1;
    lvhora          VARCHAR2(8);
    lvnumconso      NUMBER(12);


  BEGIN

    lnactua := 1;

    -- valida la fecha para actualizar
    BEGIN
       SELECT TO_DATE(replace(psfecentr,'T',''), 'YYYY/MM/DD HH24:MI:SS') into lv_fecfact FROM dual;
       lvhora := substr(psfecentr,12);
    EXCEPTION
      WHEN OTHERS THEN

        BEGIN
         SELECT TO_DATE(psfecentr, 'YYYY/MM/DD') into lv_fecfact FROM dual;
         lvhora := '00:00:00';
        EXCEPTION
          WHEN OTHERS THEN
            lnactua := 0;
        END;    
    END;    

    --- valida pedido
    BEGIN
       select oid_soli_cabe into lvnumconso 
       from ped_solic_cabec
       where val_nume_soli =  psnropedi; 
    
    EXCEPTION
       WHEN OTHERS THEN
            lnactua := 0;
    end; 
    --- valida el tipo de entrega a actualizar
    if lnactua = 1 then
      --- if pstipentr = '01' then 
          update PED_sEGUI_PEDID
             set fec_entr = decode(pstipentr,'01',lv_fecfact,fec_entr), 
                 hor_entr = decode(pstipentr,'01',lvhora,hor_entr),
                 cod_nove = substr(psnoventr,1,2),
                 fec_nove = lv_fecfact,
                 des_obser = substr(psdesobse,1,30),
                 val_lati = substr(pslatentr,1,30),
                 val_long = substr(pslonentr,1,30),
                 tip_cheq = substr(psindinve,1,2),
                 fec_cheq = decode(psindinve,'01',lv_fecfact,
                            decode(psindinve,'02',lv_fecfact, fec_cheq)),
                 hor_cheq = psindinve
          where soca_oid_soli_Cabe =   lvnumconso;
       
      --- end if;
    end if;

  EXCEPTION when others then
  NULL; 

  END ped_pr_actua_datos_ws;

  /***************************************************************************
  Descripcion       : Devulve informacion generica en WS
  Fecha Creacion    : 27/05/2015
  Autor             : Jorge Yepez
  ***************************************************************************/
  PROCEDURE ped_pr_datos_pedid_ws
  (
    pscodserv      in        VARCHAR2,
    psdatserv      in        VARCHAR2,
    psdatserv2     in        VARCHAR2,
    psdatserv3     in        VARCHAR2,
    psdatserv4     in        VARCHAR2,
    pscodcert      in        VARCHAR2,
    pscodreto      out       VARCHAR2,
    psvalreto      out       VARCHAR2,
    psvalreto2     out       VARCHAR2,
    psvalreto3     out       VARCHAR2,
    psvalreto4     out       VARCHAR2
  ) IS

    lv_fecfact      DATE;
    lnactua         number:=1;
    lvhora          VARCHAR2(8);
    lvnumconso      NUMBER(12);
    pscodigopais     sto_param_gener_occrr.cod_pais%TYPE;  
    psoidclie        mae_clien.oid_clie%TYPE;  
    psoiddire        mae_clien_direc.oid_clie_dire%type;

  BEGIN

    pscodreto := null;
    psvalreto := null;

    lnactua := 1;

      case pscodserv
           when 'DatGen' then

              pscodreto := '00';
              --- recupera pais
              BEGIN
                 select cod_pais into pscodigopais from bas_Ctrl_Fact where rownum=1;
              EXCEPTION WHEN OTHERS THEN
                 pscodreto := '01';
                 psvalreto := 'Codigo de pais no existe';
              end;
              --- valida si hay registros en el registro recupera cliente y periodo
              if pscodreto = '00' then
                BEGIN
                  select mae.val_nom1||' ' ||mae.val_nom2|| ' ' || mae.val_ape1 || ' ' || mae.val_ape2 , mae.oid_clie
                   into psvalreto, psoidclie
                  from mae_clien mae
                  where mae.cod_clie = psdatserv;
                  
                  psoiddire := IMP_PKG_PROCE_LASER.imp_fn_obtiene_direc_clie(pscodigopais , psoidclie);
                  psvalreto2 := IMP_PKG_PROCE_LASER.imp_fn_obtiene_text_direc(psoiddire,'');
                  
                EXCEPTION WHEN OTHERS THEN
                   pscodreto := '02';
                   psvalreto := 'No existe consultora';
                end;
             end if;
              --- obtiene telefono
              if pscodreto = '00' then
                BEGIN
                  select mcc.val_text_comu into psvalreto3
                  from mae_clien_comun mcc
                  where mcc.clie_oid_clie = psoidclie
                  and mcc.ticm_oid_tipo_comu = 6
                  and rownum = 1;
                                    
                EXCEPTION WHEN OTHERS THEN
                   pscodreto := '00';
                end;
             end if;             
           ---when 'Tracking' then
          else
            pscodreto := '99';
            psvalreto := 'Codigo no soportado';
      end case;

  EXCEPTION when others then
  NULL; 

  END ped_pr_datos_pedid_ws;

  /**************************************************************************
  Descripcion        : Devuelve el dias de desfase
  Fecha Creacion     : 15/12/2015
  Autor              : Rosalvina Ramirez
  ***************************************************************************/

    FUNCTION ped_fn_dev_dia_desf
    (
      pscodperi VARCHAR2,
      pscodregi VARCHAR2,
      pscodzona VARCHAR2,
      pscodsecc VARCHAR2,
      pscodterr NUMBER,
      psdiafact NUMBER,
      pstipcons VARCHAR2
    ) RETURN NUMBER IS

    lddiadesf NUMBER(8);
    ln_dia    NUMBER(8);

    BEGIN

      BEGIN

        select  f.val_dias_desf into lddiadesf from sat_param_calcu_fecee f
        where f.cod_camp=pscodperi
        and (f.cod_regi = pscodregi or f.cod_regi is null ) 
        and (f.cod_zona = pscodzona or f.cod_zona is null ) 
        and (f.cod_secc = pscodsecc or f.cod_secc is null ) 
        and (to_number(f.cod_terr) = pscodterr or f.cod_terr is null ) 
        and f.dia_fact = psdiafact
        and (f.tip_cons = pstipcons or f.tip_cons is null ) ;

      EXCEPTION
           WHEN no_data_found THEN

    BEGIN

                  select  nvl(max(f.dia_fact),0) into ln_dia from sat_param_calcu_fecee f
                  where f.cod_camp=pscodperi
                    and (f.cod_regi = pscodregi or f.cod_regi is null ) 
                    and (f.cod_zona = pscodzona or f.cod_zona is null ) 
                    and (f.cod_secc = pscodsecc or f.cod_secc is null ) 
                    and (to_number(f.cod_terr) = pscodterr or f.cod_terr is null ) 
                    and f.dia_fact  <> psdiafact
                    and (f.tip_cons = pstipcons or f.tip_cons is null ) ;

                  select  nvl(max(f.val_dias_desf),0) into lddiadesf from sat_param_calcu_fecee f
                  where f.cod_camp=pscodperi
                    and (f.cod_regi = pscodregi or f.cod_regi is null ) 
                    and (f.cod_zona = pscodzona or f.cod_zona is null ) 
                    and (f.cod_secc = pscodsecc or f.cod_secc is null ) 
                    and (to_number(f.cod_terr) = pscodterr or f.cod_terr is null ) 
                    and f.dia_fact  = ln_dia
                    and (f.tip_cons = pstipcons or f.tip_cons is null ) ;

                EXCEPTION
                        WHEN no_data_found THEN
                          select max(f.val_dias_desf) into lddiadesf
                          from sat_param_calcu_fecee f
                          where f.cod_camp='XXXXXX'
                          and f.cod_regi='XX'
                          and f.cod_zona='XXXX'
                          and f.cod_secc='X'
                          and f.cod_terr='XXXXXX'
                          and f.tip_cons='X'
                          and f.dia_fact='1';
                 END;
         END;

   RETURN lddiadesf;

    EXCEPTION

      WHEN OTHERS THEN
        RETURN 0;

    END ped_fn_dev_dia_desf; 
END fac_pkg_proc;
/
