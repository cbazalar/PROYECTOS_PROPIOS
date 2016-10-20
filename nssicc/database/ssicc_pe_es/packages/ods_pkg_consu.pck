CREATE OR REPLACE PACKAGE ods_pkg_consu IS

  /**************************************************************************
  Descripcion : Obtiene Valor de Desceunto
  Fecha Creacion : 03/10/2013
  Autor :
  ***************************************************************************/
  FUNCTION gen_fn_devue_dscto_gener
  (
    pnoidperiodo  NUMBER,
    pscodigoventa VARCHAR2
  ) RETURN VARCHAR2;

  /***************************************************************************
    Descripcion       : Funcion que obtiene el  codigo de un periodo.
    Fecha Creacion    : 03/10/2013
    Autor             :
  *****************************************************************************/
  FUNCTION fin_fn_obtie_codig_perio(p_oid_peri cra_perio.oid_peri%TYPE)
    RETURN seg_perio_corpo.cod_peri%TYPE;

  /***************************************************************************
    Descripcion       :
    Fecha Creacion    : 03/10/2013
    Autor             :
  *****************************************************************************/
  FUNCTION gen_fn_clien_bloqu
  (
    pscodcliente     VARCHAR2,
    pscodtipobloqueo VARCHAR2
  ) RETURN VARCHAR2;

  /***************************************************************************
  Descripcion       : Obtiene Monto Maximo por cliente
  Fecha Creacion    : 03/10/2013
  Autor             :
  ***************************************************************************/
  FUNCTION ped_fn_obtie_mmaxim
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Obtiene Monto Mínimo por cliente
  Fecha Creacion    : 15/10/2013
  Autor             :
  ***************************************************************************/
  FUNCTION ped_fn_obtie_mminim
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER
  ) RETURN NUMBER;

  /***************************************************************************
  Descripcion       : Obtiene Saldo vencido
  Fecha Creacion    : 15/10/2013
  Autor             :
  ***************************************************************************/
  FUNCTION ccc_fn_obtie_saldo_venci
  (
    p_oid_clie IN mae_clien.oid_clie%TYPE,
    p_fec_venc IN DATE DEFAULT SYSDATE
  ) RETURN NUMBER;

  /***************************************************************************
     Descripcion       : Obtiene el Saldo en base a la fecha de facturacion del
                         cronograma
                         para la zona determinada.
     Fecha Creacion    : 06/11/2013
     Autor             : Jorge Florencio
  ***************************************************************************/
  FUNCTION ccc_fn_obtie_saldo_zonas_campa
  (
    p_oid_clie IN mae_clien.oid_clie%TYPE,
    p_oid_zona IN zon_zona.oid_zona%TYPE
  ) RETURN NUMBER;
  ------------------------------------------------------------------------------
  -- Funcion de uso general que devuelve el valor internacionalizado
  ------------------------------------------------------------------------------
  FUNCTION valor_gen_i18n_sicc
  (
    oid_idioma_param IN INT,
    val_oid_param    IN INT,
    attr_enti_param  IN VARCHAR2
  ) RETURN VARCHAR2;

  /**************************************************************************
  Descripcion        : Calcula Dias de Entrga de Pedido a partir de la fecha de la zona de facturacion.
  Fecha Creacion     : 30/08/2013
  Autor              : Rosalvina Ramirez
  ***************************************************************************/

  FUNCTION ped_fn_obt_dias_fecha_ent3
  (
    pscodregi       VARCHAR2,
    pscodzona       VARCHAR2,
    pscodsecc       VARCHAR2,
    pscodterr       VARCHAR2,
    pnremesa        NUMBER,
    pscodperiactual VARCHAR2
  ) RETURN DATE;

  /**************************************************************************
  Descripcion        : Devuelve el día de facturación.
  Fecha Creacion     : 10/09/2014
  Autor              : Rosalvina Ramirez
  ***************************************************************************/

  FUNCTION ped_fn_dev_dia_fact
  (
    pscodperi VARCHAR2,
    pscodzona VARCHAR2,
    pnremesa  NUMBER
  ) RETURN DATE;

  /**************************************************************************
  Descripcion        : Devuelve listado de habiles.
  Fecha Creacion     : 01/04/2015
  Autor              : Rosalvina Ramirez
  ***************************************************************************/
  PROCEDURE flx_pr_gener_lista_habil
  (
    pscodcampcomu VARCHAR2,
    pscodregi     VARCHAR2,
    pscodzona     VARCHAR2,
    pscodsecc     VARCHAR2
  );

  /************************************************************************/
  /* Descripcion    :Obtiene la N siguiente campanha da una campanha  */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/

  FUNCTION gen_fn_dev_nsgte_campa
  (
    pscodperiodo VARCHAR2,
    numcampanhas NUMBER
  ) RETURN VARCHAR2;

  FUNCTION flx_fn_obtie_monmi_consu_campa
  (
    p_oid_clie IN mae_clien.oid_clie%TYPE,
    p_cod_peri IN seg_perio_corpo.cod_peri%TYPE
  ) RETURN NUMBER;

  /***************************************************************************
   Descripcion       : Devuelve el Saldo de las Campa?as Anteriores a la actual
   Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
  *****************************************************************************/
  FUNCTION ccc_fn_obtie_saldo_campa_anter
  (
    p_oid_clie IN mae_clien.oid_clie%TYPE,
    p_cod_peri IN seg_perio_corpo.cod_peri%TYPE DEFAULT NULL
  ) RETURN NUMBER;

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
END ods_pkg_consu;
/
CREATE OR REPLACE PACKAGE BODY ods_pkg_consu IS

  /**************************************************************************
  Descripcion : Obtiene Valor de Desceunto
  Fecha Creacion : 03/03/2007
  Autor :
  ***************************************************************************/
  FUNCTION gen_fn_devue_dscto_gener
  (
    pnoidperiodo  NUMBER,
    pscodigoventa VARCHAR2
  )
  
   RETURN VARCHAR2 IS
  
  BEGIN
    RETURN gen_pkg_gener.gen_fn_devue_dscto_gener(pnoidperiodo,
                                                  pscodigoventa);
  
  END gen_fn_devue_dscto_gener;

  /***************************************************************************
    Descripcion       : Funcion que obtiene el  codigo de un periodo.
    Fecha Creacion    : 03/10/2013
    Autor             :
  *****************************************************************************/
  FUNCTION fin_fn_obtie_codig_perio(p_oid_peri cra_perio.oid_peri%TYPE)
    RETURN seg_perio_corpo.cod_peri%TYPE IS
  BEGIN
    RETURN fin_pkg_gener.fin_fn_obtie_codig_perio(p_oid_peri);
  END fin_fn_obtie_codig_perio;

  /***************************************************************************
    Descripcion       :
    Fecha Creacion    : 03/10/2013
    Autor             :
  *****************************************************************************/
  FUNCTION gen_fn_clien_bloqu
  (
    pscodcliente     VARCHAR2,
    pscodtipobloqueo VARCHAR2
  ) RETURN VARCHAR2 IS
  BEGIN
    RETURN gen_pkg_gener.gen_fn_clien_bloqu(pscodcliente, pscodtipobloqueo);
  END gen_fn_clien_bloqu;

  /***************************************************************************
  Descripcion       : Obtiene Monto Maximo por cliente
  Fecha Creacion    : 03/10/2013
  Autor             :
  ***************************************************************************/
  FUNCTION ped_fn_obtie_mmaxim
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER
  ) RETURN NUMBER IS
  BEGIN
    RETURN ped_pkg_cuadr_ofert.ped_fn_obtie_mmaxim(p_oidclie,
                                                   p_oidterradmin);
  END ped_fn_obtie_mmaxim;

  /***************************************************************************
  Descripcion       : Obtiene Monto Mínimo por cliente
  Fecha Creacion    : 15/10/2013
  Autor             : CMori
  ***************************************************************************/
  FUNCTION ped_fn_obtie_mminim
  (
    p_oidclie      NUMBER,
    p_oidterradmin NUMBER
  ) RETURN NUMBER IS
    lnnivel1  NUMBER(12, 2);
    lnnominal NUMBER(12, 2);
    lnnivel2  NUMBER(12, 2);
  BEGIN
    ped_pkg_cuadr_ofert.ped_pr_obtie_mminim(p_oidclie,
                                            p_oidterradmin,
                                            lnnominal,
                                            lnnivel1,
                                            lnnivel2);
    RETURN lnnivel1;
  END ped_fn_obtie_mminim;

  /***************************************************************************
  Descripcion       : Obtiene Saldo vencido
  Fecha Creacion    : 15/10/2013
  Autor             :
  ***************************************************************************/
  FUNCTION ccc_fn_obtie_saldo_venci
  (
    p_oid_clie IN mae_clien.oid_clie%TYPE,
    p_fec_venc IN DATE DEFAULT SYSDATE
  ) RETURN NUMBER IS
  BEGIN
    RETURN ccc_pkg_gener.ccc_fn_obtie_saldo_venci(p_oid_clie, p_fec_venc);
  END ccc_fn_obtie_saldo_venci;

  /***************************************************************************
  Descripcion       : Obtiene el Saldo en base a la fecha de facturacion del
                      cronograma para la zona determinada.
  Fecha Creacion    : 06/11/2013
  Autor             : Jorge Florencio
  ***************************************************************************/
  FUNCTION ccc_fn_obtie_saldo_zonas_campa
  (
    p_oid_clie IN mae_clien.oid_clie%TYPE,
    p_oid_zona IN zon_zona.oid_zona%TYPE
  ) RETURN NUMBER IS
  BEGIN
    RETURN ccc_pkg_gener.ccc_fn_obtie_saldo_zonas_campa(p_oid_clie,
                                                        p_oid_zona);
  END ccc_fn_obtie_saldo_zonas_campa;

  ------------------------------------------------------------------------------
  -- Funcion de uso general que devuelve el valor internacionalizado
  ------------------------------------------------------------------------------
  FUNCTION valor_gen_i18n_sicc
  (
    oid_idioma_param IN INT,
    val_oid_param    IN INT,
    attr_enti_param  IN VARCHAR2
  ) RETURN VARCHAR2 IS
  BEGIN
    RETURN pq_apl_aux.valor_gen_i18n_sicc(oid_idioma_param,
                                          val_oid_param,
                                          attr_enti_param);
  
  END valor_gen_i18n_sicc;

  /**************************************************************************
  Descripcion        : Calcula Dias de Entrga de Pedido a partir de la fecha de la zona de facturacion.
  Fecha Creacion     : 30/08/2013
  Autor              : Rosalvina Ramirez
  ***************************************************************************/

  FUNCTION ped_fn_obt_dias_fecha_ent3
  (
    pscodregi       VARCHAR2,
    pscodzona       VARCHAR2,
    pscodsecc       VARCHAR2,
    pscodterr       VARCHAR2,
    pnremesa        NUMBER,
    pscodperiactual VARCHAR2
  ) RETURN DATE IS
  
  BEGIN
  
    RETURN fac_pkg_proc.ped_fn_obt_dias_fecha_ent3(pscodregi,
                                                   pscodzona,
                                                   pscodsecc,
                                                   pscodterr,
                                                   pnremesa,
                                                   pscodperiactual);
  
  END ped_fn_obt_dias_fecha_ent3;

  /**************************************************************************
  Descripcion        : Devuelve el día de facturación.
  Fecha Creacion     : 10/09/2014
  Autor              : Rosalvina Ramirez
  ***************************************************************************/

  FUNCTION ped_fn_dev_dia_fact
  (
    pscodperi VARCHAR2,
    pscodzona VARCHAR2,
    pnremesa  NUMBER
  ) RETURN DATE IS
  
  BEGIN
  
    RETURN fac_pkg_proc.ped_fn_dev_dia_fact(pscodperi, pscodzona, pnremesa);
  
  END ped_fn_dev_dia_fact;

  PROCEDURE flx_pr_gener_lista_habil
  (
    pscodcampcomu VARCHAR2,
    pscodregi     VARCHAR2,
    pscodzona     VARCHAR2,
    pscodsecc     VARCHAR2
  ) AS
  
  BEGIN
  
    DELETE FROM flx_repor_lista_habil;
  
    INSERT INTO flx_repor_lista_habil
      SELECT zz.cod_zona,
             zs.cod_secc,
             (SELECT CASE
                       WHEN MIN(h.cod_peri_comu) = pscodcampcomu THEN
                        '1 - Consultoras invitadas en Campaña ' ||
                        pscodcampcomu
                       WHEN cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(MIN(h.cod_peri_comu),
                                                                       1) =
                            pscodcampcomu THEN
                        '2 - Consultoras invitadas en Campaña ' ||
                        cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(ch.cod_peri_comu,
                                                                   -1)
                       ELSE
                        '3 - Consultoras Invitadas en Campañas Anteriores a ' ||
                        cup_pkg_prog_nuevas.gen_fn_dev_nsgte_campa(ch.cod_peri_comu,
                                                                   -1)
                     END
                FROM flx_consu_habil_flexi h
               WHERE h.cod_clie = mc.cod_clie) numinvi,
             
             (SELECT num_docu_iden
                FROM mae_clien_ident
               WHERE clie_oid_clie = mc.oid_clie
                 AND val_iden_docu_prin = 1),
             ch.cod_clie,
             mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' ||
             mc.val_ape2 nom_clie,
             ch.val_pedi_base,
             ch.val_line_cred,
             CASE
               WHEN ch.ind_acti = 1 THEN
                0
               ELSE
                1
             END ind_acti,
             ch.ind_acti ind_habil,
             decode(ch.ind_acti, 1, 'Si', 'No') ind_firm_cart,
             fcc.des_cali,
             nvl((SELECT MIN(cfc.cod_peri)
                   FROM flx_cuota_flexi_factu_cabec cfc
                  WHERE cfc.cod_clie = mc.cod_clie
                  GROUP BY cfc.cod_clie),
                 '') min_flexi,
             nvl((SELECT MAX(cfc.cod_peri)
                   FROM flx_cuota_flexi_factu_cabec cfc
                  WHERE cfc.cod_clie = mc.cod_clie
                  GROUP BY cfc.cod_clie),
                 '') max_flexi,
             CASE
               WHEN ch.ind_acti = 0 AND ch.ind_canc = 0 THEN
                'Inactiva'
               WHEN ch.ind_acti = 1 AND ch.ind_canc = 0 THEN
                'Activa'
               WHEN ch.ind_acti = 0 AND ch.ind_canc = 1 THEN
                'Cancelada'
               ELSE
                ''
             END estatus
        FROM flx_consu_habil_flexi ch,
             flx_calif_compo       fcc,
             mae_clien             mc,
             mae_clien_unida_admin mcua,
             zon_terri_admin       zta,
             zon_secci             zs,
             zon_zona              zz,
             zon_regio             zr
       WHERE ch.cod_clie = mc.cod_clie
         AND mc.oid_clie = mcua.clie_oid_clie
         AND mcua.ind_acti = 1
         AND mcua.ztad_oid_terr_admi = zta.oid_terr_admi
         AND zta.zscc_oid_secc = zs.oid_secc
         AND zs.zzon_oid_zona = zz.oid_zona
         AND zz.zorg_oid_regi = zr.oid_regi
         AND ch.cod_peri_comu = pscodcampcomu
         AND ch.ind_habi = 1
         AND ch.ind_cali_comp = fcc.cod_cali
         AND EXISTS
       (SELECT NULL
                FROM mae_clien_datos_adici mad
               WHERE mad.clie_oid_clie = mc.oid_clie
                 AND mad.esta_oid_esta_clie NOT IN (5, 7))
         AND zr.cod_regi = decode(pscodregi, NULL, zr.cod_regi, pscodregi)
         AND zz.cod_zona = decode(pscodzona, NULL, zz.cod_zona, pscodzona)
         AND zs.cod_secc = decode(pscodsecc, NULL, zs.cod_secc, pscodsecc)
       ORDER BY zr.cod_regi,
                zz.cod_zona,
                zs.cod_secc,
                numinvi,
                mc.val_nom1 || ' ' || mc.val_nom2 || ' ' || mc.val_ape1 || ' ' ||
                mc.val_ape2;
  
  END flx_pr_gener_lista_habil;

  /************************************************************************/
  /* Descripcion    :Obtiene la N siguiente campanha da una campanha  */
  /* Autor         : Marco Silva                                          */
  /* Fecha      : 04/11/2005                                          */
  /************************************************************************/
  FUNCTION gen_fn_dev_nsgte_campa
  (
    pscodperiodo VARCHAR2,
    numcampanhas NUMBER
  ) RETURN VARCHAR2 IS
  
  BEGIN
  
    RETURN gen_pkg_gener.gen_fn_perio_nsigu(NULL,
                                            pscodperiodo,
                                            numcampanhas);
  
  END gen_fn_dev_nsgte_campa;

  FUNCTION flx_fn_obtie_monmi_consu_campa
  (
    p_oid_clie IN mae_clien.oid_clie%TYPE,
    p_cod_peri IN seg_perio_corpo.cod_peri%TYPE
  ) RETURN NUMBER IS
  BEGIN
    RETURN flx_pkg_proce.flx_fn_obtie_monmi_consu_campa(p_oid_clie,
                                                        p_cod_peri);
  END;

  /***************************************************************************
   Descripcion       : Devuelve el Saldo de las Campa?as Anteriores a la actual
   Fecha Creacion    : 25/02/2009
   Autor             : Jorge Florencio
  *****************************************************************************/
  FUNCTION ccc_fn_obtie_saldo_campa_anter
  (
    p_oid_clie IN mae_clien.oid_clie%TYPE,
    p_cod_peri IN seg_perio_corpo.cod_peri%TYPE DEFAULT NULL
  ) RETURN NUMBER IS
  BEGIN
    RETURN ccc_pkg_gener.ccc_fn_obtie_saldo_campa_anter(p_oid_clie,
                                                        p_cod_peri);
  END;
  
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
  
  BEGIN
  
    RETURN fac_pkg_proc.ped_fn_dev_dia_desf (pscodperi,pscodregi,pscodzona,pscodsecc,pscodterr,psdiafact,pstipcons); 

 EXCEPTION

      WHEN OTHERS THEN
        RETURN 0;
    
 END ped_fn_dev_dia_desf;
END ods_pkg_consu;
/
