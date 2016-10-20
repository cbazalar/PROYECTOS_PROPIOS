CREATE OR REPLACE PACKAGE COB_PKG_REPOR_ESTAD IS

  /* Declaracion de Variables */
  ln_sqlcode NUMBER(10);
  ls_sqlerrm VARCHAR2(150);
  W_FILAS    NUMBER := 1000;

  PROCEDURE COB_PR_MIGRA_ESTAD_RECUP_COBRA(p_cod_peri_inic IN seg_perio_corpo.cod_peri%TYPE,
                                           p_cod_peri_fina IN seg_perio_corpo.cod_peri%TYPE);

  PROCEDURE COB_PR_CARGA_ESTAD_RECUP_COBRA(p_cod_peri IN seg_perio_corpo.cod_peri%TYPE);

  /*********************************************************************************
    Descripcion       : Procedimiento que carga las novedades de los Movimientos de Cuenta Corriente
                                 en el Estadistico de recuperacion de cartea (CBZ)
    Fecha Creacion    : 26/02/2009
    Autor             : Jorge Florencio
  ***********************************************************************************/
  PROCEDURE COB_PR_CARGA_ESTAD_RECUP_COBRA(p_cod_pais seg_pais.cod_pais%TYPE,
                                           p_cod_soci seg_socie.cod_soci%TYPE);

  PROCEDURE COB_PR_ACTUA_ESTAD_RECUP_COBRA(p_cod_peri IN seg_perio_corpo.cod_peri%TYPE);

  /*************************************************************************************
   Descripcion       : Procedimiento que actualiza los cargos en el Estadistico de recuperacion de cartea (CBZ)
                               que han sido actualizados.
   Fecha Creacion    : 26/02/2009
  Autor             : Jorge Florencio
  ************************************************************************************/
  PROCEDURE COB_PR_ACTUA_ESTAD_RECUP_COBRA(p_cod_pais seg_pais.cod_pais%TYPE,
                                           p_cod_soci seg_socie.cod_soci%TYPE);

  PROCEDURE COB_PR_ACTUA_ESTAD_RECUP_COBRA;

  /*************************************************************************************
   Descripcion       : Procedimiento que actualiza la fecha de cierre y "resetea" a cero
                       el ultimo numero de historia con el fin de recalcular una zona
                       determinada de acuerdo  a una nueva  fecha de cierre.
   Fecha Creacion    : 05/08/2009
  Autor             : EL
  ************************************************************************************/
  PROCEDURE COB_PR_RESET_ESTAD_RECUP_COBRA(p_cod_pais seg_pais.cod_pais%TYPE,
                                           p_cod_soci seg_socie.cod_soci%TYPE,
                                           p_cod_peri seg_perio_corpo.cod_peri%TYPE,
                                           p_cod_regi zon_regio.cod_regi%TYPE,
                                           p_cod_zona zon_zona.cod_zona%TYPE,
                                           p_fec_cier DATE);

 PROCEDURE COB_PR_VALID_ESTAD_RECUP_COBRA;
 
  /**************************************************************************
     Descripcion       : Proceso que valida y registra fechas de excepcion
                       para la Generacion de Cronograma de Venta (tempo)
    Fecha Creacion    : 05/08/2009
    Parametros Entrada :
              Codigo Pais
              Codigo Sociedad
              Codigo Periodo
              Codigo de la Etapa
              Codigo de Region
              Codigo de Zona
              Codigo de Usuario
              Tipo de Excepcion (de Cierre o de Generacion)
              Fecha Excepcional
  ***************************************************************************/
  PROCEDURE COB_PR_CRONO_VENTA_FECHA_EXCEP(p_cod_pais     seg_pais.cod_pais%TYPE,
                                           p_cod_soci     seg_socie.cod_soci%TYPE,
                                           p_cod_peri     seg_perio_corpo.cod_peri%TYPE,
                                           p_cod_regi     zon_regio.cod_regi%type,
                                           p_cod_zona     zon_zona.cod_zona%type,
                                           p_cod_usuario  cob_detal_asign_carte.usu_crea%TYPE,
                                           p_str_fec_exce VARCHAR2);

  /*************************************************************************************
   Descripcion       : Funcion que obtiene la cobranza del banco.
   Fecha Creacion    : 26/02/2009
  Autor             : Jorge Florencio
  ************************************************************************************/
  FUNCTION cob_fn_obtie_cobra_banco(p_cod_pais    seg_pais.cod_pais%TYPE,
                                    p_oid_movi_cc ccc_movim_cuent_corri.oid_movi_cc%TYPE)
    RETURN NUMBER;

  FUNCTION cob_fn_obtie_cobra_banco_sabon(p_oid_movi_cc IN ccc_movim_cuent_corri.oid_movi_cc%TYPE)
    RETURN NUMBER;

  /*************************************************************************************
   Descripcion       : Funcion que obtiene la cobranza bancaria a una fecha determinada .
   Fecha Creacion    : 26/02/2009
  Autor             : Jorge Florencio
  ************************************************************************************/
  FUNCTION cob_fn_obtie_cobra_banco_fecha(p_cod_pais    IN seg_pais.cod_pais%TYPE,
                                          p_oid_movi_cc IN ccc_movim_cuent_corri.oid_movi_cc%TYPE,
                                          p_fec_cier    IN DATE)
    RETURN NUMBER;

  FUNCTION cob_fn_obtie_cobra_banco_sabon(p_oid_movi_cc IN ccc_movim_cuent_corri.oid_movi_cc%TYPE,
                                          p_fec_cier    IN DATE)
    RETURN NUMBER;

  /*************************************************************************************
   Descripcion       : Funcion que obtiene la cobranza por abonos no monetarios
                               a una fecha determinada.
   Fecha Creacion    : 26/02/2009
  Autor             : Jorge Florencio
  ************************************************************************************/
  FUNCTION cob_fn_obtie_cobra_abono_nomon(p_cod_pais    seg_pais.cod_pais%TYPE,
                                          p_oid_movi_cc ccc_movim_cuent_corri.oid_movi_cc%TYPE)
    RETURN NUMBER;

  /*************************************************************************************
   Descripcion       : Funcion que calcula y registra una "Fecha de Cierre"
                       para un Periodo, Region y Zona  dados por parametro.
   Fecha Creacion    : 17/08/2009
  Autor             : EL.
  ************************************************************************************/
  FUNCTION cob_fn_regis_fecha_cierr_venta(p_cod_pais seg_pais.cod_pais%TYPE,
                                          p_cod_soci seg_socie.cod_soci%TYPE,
                                          p_cod_peri seg_perio_corpo.cod_peri%TYPE,
                                          p_cod_regi zon_regio.cod_regi%TYPE,
                                          p_cod_zona zon_zona.cod_zona%TYPE)
    RETURN DATE;

  /*************************************************************************************
   Descripcion       : Funcion que obtiene  una "Fecha de Cierre"
                       para un Periodo, Region y Zona  dados por parametro.
   Fecha Creacion    : 04/08/2009
  Autor             : EL.
  ************************************************************************************/
  FUNCTION cob_fn_obtie_fecha_cierr_venta(p_cod_pais seg_pais.cod_pais%TYPE,
                                          p_cod_soci seg_socie.cod_soci%TYPE,
                                          p_cod_peri seg_perio_corpo.cod_peri%TYPE,
                                          p_cod_regi zon_regio.cod_regi%TYPE,
                                          p_cod_zona zon_zona.cod_zona%TYPE)
    RETURN DATE;

  /*************************************************************************************
  Descripcion       : Procedimiento que carga el Reporte Detallado
                      Recuperacion Cartera Cobrador para formato CSV
  Fecha Creacion    : 27/01/2014
  Autor             : Carlos Bazalar
  ************************************************************************************/
  PROCEDURE cob_pr_detal_carte_cobra_csv(pscodigopais          VARCHAR2,
                                         pscodigoSociedad      VARCHAR2,
                                         pscodigoPeriodoInicio VARCHAR2,
                                         pscodigoPeriodoFin    VARCHAR2,
                                         pscodigoEtapaDeuda    VARCHAR2,
                                         pscodigoCobrador      VARCHAR2,
                                         psnombrearchivo       VARCHAR2,
                                         pstitulo              VARCHAR2,
                                         psdirectorio          OUT VARCHAR2,
                                         psvistaReporte        VARCHAR2);

  /*************************************************************************************
  Descripcion       : Procedimiento que carga el Reporte Detallado
                      Cobranza 31 dias para formato CSV
  Fecha Creacion    : 27/01/2014
  Autor             : Carlos Bazalar
  ************************************************************************************/
  PROCEDURE cob_pr_detal_cobra_31dia_csv(pscodigopais    VARCHAR2,
                                         psnombrearchivo VARCHAR2,
                                         pstitulo        VARCHAR2,
                                         psdirectorio    OUT VARCHAR2);

  /*************************************************************************************
    Descripcion       : Procedimiento que carga el Reporte Detallado
                     Cobranza 31 dias para formato CSV
    Fecha Creacion    : 27/01/2014
    Autor             : Carlos Bazalar
  ************************************************************************************/
  PROCEDURE cob_pr_saldo_pendi_csv(pscodigopais    IN VARCHAR2,
                                   psnombrearchivo IN VARCHAR2,
                                   pstitulo        IN VARCHAR2,
                                   psdirectorio    OUT VARCHAR2);

  /*************************************************************************************
  Descripcion           : Procedimiento que carga el Reporte Carga Masiva de Gestion para formato CSV
  Fecha Creacion    : 23/10/2014
  Autor                   : Sebastian Guerra
  ************************************************************************************/
  PROCEDURE cob_pr_carga_masiv_gesti_csv(pscodigopais          VARCHAR2,
                                         pscodigoSociedad      VARCHAR2,
                                         pscodigoPeriodoInicio VARCHAR2,
                                         pscodigoPeriodoFin    VARCHAR2,
                                         pscodigoEtapaDeuda    VARCHAR2,
                                         pscodigoCobrador      VARCHAR2,
                                         psnombrearchivo       VARCHAR2,
                                         pstitulo              VARCHAR2,
                                         psdirectorio          OUT VARCHAR2);

END COB_PKG_REPOR_ESTAD;
/
CREATE OR REPLACE PACKAGE BODY COB_PKG_REPOR_ESTAD IS

 -- Declaracion de Variables --
   gv_log_cod_modu                 fin_modul.cod_modu%TYPE;
   gv_log_cod_proc                 fin_proce_modul.cod_proc%TYPE;

   gv_cod_proc_ejec                NUMBER(12);
   gv_log_cod_pais                 seg_pais.cod_pais%TYPE;
   gv_log_cod_soci                 seg_socie.Cod_Soci%TYPE;
   gv_des_log                      VARCHAR2(2500);
   gv_log_user                     fin_proce_ejecu.usu_proc%TYPE;
   gc_cod_proc_carg_dire           ccc_proce.cod_proc%TYPE:='CCC007';
   gc_cod_subp_carg_erro_banc      ccc_subpr.cod_subp%TYPE:='2';


/*  Declaracion de rutinas locales */

   /*************************************************************************************
    Descripcion       : Procedimiento que registra los cargos fraccionados en la
                                tabla de CCC_MOVIM_CARGO_FRACC.
    Fecha Creacion    : 26/02/2009
   Autor             : Jorge Florencio
   ************************************************************************************/
   PROCEDURE cob_pr_regis_cargo_fracc(
      p_oid_ante_regi_proc fin_contr_regis_progr.oid_ante_regi_proc%TYPE,
      p_oid_ulti_regi_proc   fin_contr_regis_progr.oid_ulti_regi_proc%TYPE);

   /*************************************************************************************
    Descripcion       : Procedimiento que registra los cargos directos en la
                                tabla de CCC_MOVIM_CARGO_FRACC.
    Fecha Creacion    : 26/02/2009
   Autor             : Jorge Florencio
   ************************************************************************************/
   PROCEDURE cob_pr_regis_cargo_direc(
      p_oid_ante_regi_proc   fin_contr_regis_progr.oid_ante_regi_proc%TYPE,
      p_oid_ulti_regi_proc     fin_contr_regis_progr.oid_ulti_regi_proc%TYPE);

    /*************************************************************************************
    Descripcion       : Procedimiento que registra las atenciones de servicio  en la
                                tabla de CCC_MOVIM_CARGO_FRACC.
    Fecha Creacion    : 26/02/2009
   Autor             : Jorge Florencio
   ************************************************************************************/
   PROCEDURE cob_pr_regis_atenc_servi (
      p_oid_ante_regi_proc   fin_contr_regis_progr.oid_ante_regi_proc%TYPE,
      p_oid_ulti_regi_proc     fin_contr_regis_progr.oid_ulti_regi_proc%TYPE);

    /*************************************************************************************
    Descripcion       : Procedimiento que realiza la carga inicial de fecahs de cierre
                        en tabla temporal
    Fecha Creacion    : 07/08/2009
   Autor             : EL
   ************************************************************************************/
   PROCEDURE cob_pr_carga_tempo_fecha_cierr (
     p_cod_pais  seg_pais.cod_pais%TYPE,
     p_cod_soci seg_socie.cod_soci%TYPE    );

   /* Ejecutable de rutinas */

 PROCEDURE COB_PR_MIGRA_ESTAD_RECUP_COBRA(
  p_cod_peri_inic                IN   seg_perio_corpo.cod_peri%TYPE,
  p_cod_peri_fina                IN   seg_perio_corpo.cod_peri%TYPE)
 IS

  lv_imp_fact_fox                  NUMBER(12,2);
  lv_imp_card_fox                  NUMBER(12,2);
  lv_imp_carf_fox                  NUMBER(12,2);
  lv_cob_21_fox                    NUMBER(12,2);
  lv_cob_31_fox                    NUMBER(12,2);
  lv_cob_42_fox                    NUMBER(12,2);
  lv_cob_63_fox                    NUMBER(12,2);
  lv_cob_84_fox                    NUMBER(12,2);
  lv_cob_126_fox                   NUMBER(12,2);
  lv_cob_189_fox                   NUMBER(12,2);
  lv_cob_999_fox                   NUMBER(12,2);
  lv_imp_abon_nmon_fox             NUMBER(12,2);
  lv_imp_abon_frac_fox             NUMBER(12,2);

  lv_imp_fact                      NUMBER(12,2);
  lv_imp_carg_dire                 NUMBER(12,2);
  lv_imp_carf                      NUMBER(12,2);
  lv_cob_21                        NUMBER(12,2);
  lv_cob_31                        NUMBER(12,2);
  lv_cob_42                        NUMBER(12,2);
  lv_cob_63                        NUMBER(12,2);
  lv_cob_84                        NUMBER(12,2);
  lv_cob_126                       NUMBER(12,2);
  lv_cob_189                       NUMBER(12,2);
  lv_cob_999                       NUMBER(12,2);
  lv_imp_abon_nmon                 NUMBER(12,2);
  lv_imp_abon_frac                 NUMBER(12,2);
  lv_sald_pend                     NUMBER(12,2);
  lv_sald_pend_real                NUMBER(12,2);
  lv_imp_fact_neto                 NUMBER(12,2);

  lv_imp_abon_31                   NUMBER(12,2);
  lv_imp_abon_42                   NUMBER(12,2);

  lv_imp_abon_21                   NUMBER(12,2);
  lv_imp_abon_63                   NUMBER(12,2);
  lv_imp_abon_84                   NUMBER(12,2);
  lv_imp_abon_126                  NUMBER(12,2);
  lv_imp_abon_189                  NUMBER(12,2);
  lv_imp_abon_999                  NUMBER(12,2);

  lv_dif                           NUMBER(12,2);
  lv_reg_cbz                       cob_repor_estad_recup_cobra%ROWTYPE;

 BEGIN

  /*
  INSERT INTO cbz_campa_cuadre
   SELECT
    stdcampa,
    SUM(stdmfact),
    SUM(stdc021d),
    SUM(stdc031d),
    SUM(stdc042d),
    SUM(stdc063d),
    SUM(stdc084d),
    SUM(stdc126d),
    SUM(stdc189d),
    SUM(stdc999d),
    SUM(stdmcard),
    SUM(stdabnmo),
    SUM(stdmcarf),
    SUM(stdmabnf)
   FROM migra_orig_do_lb.ec_stdzona
   WHERE stdcampa >= '201310'
   GROUP BY stdcampa;
   */

  FOR v_zonas IN (
    SELECT
     cod_pais,
     cod_soci,
     cod_peri,
     '99' des_regi,
     '9995' cod_zona,
     'ZONA 9995' des_zona,
     '9995' oid_clie_zona,
     SUM(imp_fact) imp_fact,
     SUM(imp_sald_pend_sac) imp_sald_pend_sac,
     SUM(cob_dias_21) cob_21,
     SUM(cob_dias_31) cob_31,
     SUM(cob_dias_42) cob_42,
   SUM(cob_dias_63) cob_63,
   SUM(cob_dias_84) cob_84,
   SUM(cob_dias_126) cob_126,
   SUM(cob_dias_189) cob_189,
   SUM(cob_dias_999) cob_999,
   SUM(imp_carg_dire) imp_carg_dire,
   SUM(imp_abon_conx) imp_abon_con,
   SUM(imp_abon_nmon) imp_abon_nmon,
   SUM(imp_sald_pend) imp_sald_pend,
   SUM(imp_carg_frac) imp_carg_frac,
   SUM(imp_abon_frac) imp_abon_frac
  FROM cob_repor_estad_recup_cobra
  WHERE cod_peri >= p_cod_peri_inic
  AND cod_peri <= p_cod_peri_fina
  GROUP BY
   cod_pais,
   cod_soci,
   cod_peri
  ORDER BY 1,2,3 ASC)   LOOP

  SELECT
   SUM(std.stdmfact) imp_fact,
   SUM(std.stdmcard) imp_card,
   SUM(std.stdmcarf) imp_carf,
   SUM(std.stdc021d) cob_21,
   SUM(std.stdc031d) cob_31,
   SUM(std.stdc042d) cob_42,
   SUM(std.stdc063d) cob_63,
   SUM(std.stdc084d) cob_84,
   SUM(std.stdc126d) cob_126,
   SUM(std.stdc189d) cob_189,
   SUM(std.stdc999d) cob_999,
   SUM(std.stdabnmo) imp_abon_nmon,
   SUM(std.stdmabnf) imp_abon_frac
  INTO
   lv_imp_fact_fox ,
   lv_imp_card_fox ,
   lv_imp_carf_fox ,
   lv_cob_21_fox,
   lv_cob_31_fox,
   lv_cob_42_fox,
   lv_cob_63_fox,
   lv_cob_84_fox,
   lv_cob_126_fox,
   lv_cob_189_fox,
   lv_cob_999_fox ,
   lv_imp_abon_nmon_fox ,
   lv_imp_abon_frac_fox
  FROM cbz_campa_cuadre std
  WHERE std.stdcampa = v_zonas.cod_peri;

    --Dbms_Output.put_line('Monto Facturado SICC :  ' || v_zonas.imp_fact);
    --Dbms_Output.put_line('Monto Facturado FOX :  ' || lv_imp_fact_fox);

    IF lv_imp_fact_fox <> v_zonas.imp_fact THEN

      DELETE FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri
      AND cbz.cod_zona = v_zonas.cod_zona
      AND cbz.oid_clie = 999999999999
      AND cbz.imp_fact IS NOT NULL;

      SELECT SUM(cbz.imp_fact)
      INTO lv_imp_fact
      FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri;

      lv_dif := lv_imp_fact - lv_imp_fact_fox;

      lv_reg_cbz.cod_pais :=  v_zonas.cod_pais;
      lv_reg_cbz.cod_soci :=  v_zonas.cod_soci;
      lv_reg_cbz.des_regi :=  v_zonas.des_regi;
      lv_reg_cbz.cod_peri := v_zonas.cod_peri;
      lv_reg_cbz.cod_zona := v_zonas.cod_zona;
      lv_reg_cbz.des_zona := v_zonas.des_zona;
      lv_reg_cbz.oid_clie_zona := v_zonas.oid_clie_zona;
      lv_reg_cbz.oid_clie :=999999999999;
      lv_reg_cbz.oid_movi_cc := v_zonas.cod_peri || v_zonas.cod_zona || 55;
      lv_reg_cbz.imp_fact := lv_dif*-1;
      lv_reg_cbz.imp_carg_dire := NULL;
      lv_reg_cbz.imp_carg_frac := NULL;
      lv_reg_cbz.cob_dias_21 :=  NULL;
      lv_reg_cbz.cob_dias_31 :=  NULL;
      lv_reg_cbz.cob_dias_42 :=  NULL;
      lv_reg_cbz.cob_dias_63 :=  NULL;
      lv_reg_cbz.cob_dias_84 :=  NULL;
      lv_reg_cbz.cob_dias_126 :=  NULL;
      lv_reg_cbz.cob_dias_189 :=  NULL;
      lv_reg_cbz.cob_dias_999 :=  NULL;
      lv_reg_cbz.imp_abon_nmon := NULL;
      lv_reg_cbz.imp_abon_frac := NULL;

      --dbms_output.put_line(' DIF ' || lv_reg_cbz.imp_fact);

       INSERT INTO cob_repor_estad_recup_cobra VALUES lv_reg_cbz;

     END IF;

    IF lv_imp_card_fox <> v_zonas.imp_carg_dire THEN

      DELETE FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri
      AND cbz.oid_clie = 999999999999
      AND cbz.cod_zona = v_zonas.cod_zona
      AND cbz.imp_carg_dire IS NOT NULL;

      SELECT SUM(cbz.imp_carg_dire)
      INTO lv_imp_carg_dire
      FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri;

      lv_dif := lv_imp_carg_dire - lv_imp_card_fox;

      lv_reg_cbz.cod_pais :=  v_zonas.cod_pais;
      lv_reg_cbz.cod_soci :=  v_zonas.cod_soci;
      lv_reg_cbz.des_regi :=  v_zonas.des_regi;
      lv_reg_cbz.cod_peri := v_zonas.cod_peri;
      lv_reg_cbz.cod_zona := v_zonas.cod_zona;
      lv_reg_cbz.des_zona := v_zonas.des_zona;
      lv_reg_cbz.oid_clie_zona := v_zonas.oid_clie_zona;
      lv_reg_cbz.oid_clie := 999999999999;
      lv_reg_cbz.oid_movi_cc := v_zonas.cod_peri || v_zonas.cod_zona ||66;
      lv_reg_cbz.imp_fact := NULL;
      lv_reg_cbz.imp_carg_frac := NULL;
      lv_reg_cbz.imp_carg_dire := lv_dif*-1;
      lv_reg_cbz.cob_dias_21 :=  NULL;
      lv_reg_cbz.cob_dias_31 :=  NULL;
      lv_reg_cbz.cob_dias_42 :=  NULL;
      lv_reg_cbz.cob_dias_63 :=  NULL;
      lv_reg_cbz.cob_dias_84 :=  NULL;
      lv_reg_cbz.cob_dias_126 :=  NULL;
      lv_reg_cbz.cob_dias_189 :=  NULL;
      lv_reg_cbz.cob_dias_999 :=  NULL;
      lv_reg_cbz.imp_abon_nmon := NULL;
      lv_reg_cbz.imp_abon_frac := NULL;


       INSERT INTO cob_repor_estad_recup_cobra VALUES lv_reg_cbz;

     END IF;

    IF lv_imp_carf_fox <> v_zonas.imp_carg_frac THEN

      DELETE FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri
      AND cbz.oid_clie = 999999999999
       AND cbz.cod_zona = v_zonas.cod_zona
      AND cbz.imp_carg_frac IS NOT NULL;

      SELECT SUM(cbz.imp_carg_frac)
      INTO lv_imp_carf
      FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri;

      lv_dif := lv_imp_carf - lv_imp_carf_fox;

      lv_reg_cbz.cod_pais :=  v_zonas.cod_pais;
      lv_reg_cbz.cod_soci :=  v_zonas.cod_soci;
      lv_reg_cbz.des_regi :=  v_zonas.des_regi;
      lv_reg_cbz.cod_peri := v_zonas.cod_peri;
      lv_reg_cbz.cod_zona := v_zonas.cod_zona;
      lv_reg_cbz.des_zona := v_zonas.des_zona;
      lv_reg_cbz.oid_clie_zona := v_zonas.oid_clie_zona;
      lv_reg_cbz.oid_clie := 999999999999;
      lv_reg_cbz.oid_movi_cc := v_zonas.cod_peri || v_zonas.cod_zona || 77;
      lv_reg_cbz.imp_fact := NULL;
      lv_reg_cbz.imp_carg_dire := NULL;
      lv_reg_cbz.imp_carg_frac := lv_dif*-1;
      lv_reg_cbz.cob_dias_21 :=  NULL;
      lv_reg_cbz.cob_dias_31 :=  NULL;
      lv_reg_cbz.cob_dias_42 :=  NULL;
      lv_reg_cbz.cob_dias_63 :=  NULL;
      lv_reg_cbz.cob_dias_84 :=  NULL;
      lv_reg_cbz.cob_dias_126 :=  NULL;
      lv_reg_cbz.cob_dias_189 :=  NULL;
      lv_reg_cbz.cob_dias_999 :=  NULL;
      lv_reg_cbz.imp_abon_nmon := NULL;
      lv_reg_cbz.imp_abon_frac := NULL;


       INSERT INTO cob_repor_estad_recup_cobra VALUES lv_reg_cbz;

     END IF;

    IF lv_cob_21_fox <> v_zonas.cob_21 THEN

      DELETE FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri
      AND cbz.cod_zona = v_zonas.cod_zona
      AND cbz.oid_clie = 999999999999
      AND cbz.cob_dias_21 IS NOT NULL;

      SELECT SUM(cbz.cob_dias_21)
      INTO lv_cob_21
      FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri;

       -- Cobrado --
      SELECT NVL(SUM(ap.imp_abon),0)
      INTO lv_imp_abon_21
      FROM ccc_aplic_abono_cargo ap,
           cob_repor_estad_recup_cobra cbz
      WHERE ap.mvcc_oid_movi_carg = cbz.oid_movi_cc
       AND cbz.cod_peri =  v_zonas.cod_peri
        AND ap.cmba_oid_movi_banc is NOT NULL
        AND ap.fec_apli <= cbz.fec_cier_21;

      lv_dif := lv_cob_21 - lv_cob_21_fox - NVL(lv_imp_abon_21,0);

      lv_reg_cbz.cod_pais :=  v_zonas.cod_pais;
      lv_reg_cbz.cod_soci :=  v_zonas.cod_soci;
      lv_reg_cbz.des_regi :=  v_zonas.des_regi;
      lv_reg_cbz.cod_peri := v_zonas.cod_peri;
      lv_reg_cbz.cod_zona := v_zonas.cod_zona;
      lv_reg_cbz.des_zona := v_zonas.des_zona;
      lv_reg_cbz.oid_clie_zona := v_zonas.oid_clie_zona;
      lv_reg_cbz.oid_clie := 999999999999;
      lv_reg_cbz.oid_movi_cc := v_zonas.cod_peri || v_zonas.cod_zona || 21;
       lv_reg_cbz.imp_fact := NULL;
      lv_reg_cbz.imp_carg_dire := NULL;
      lv_reg_cbz.imp_carg_frac := NULL;
      lv_reg_cbz.cob_dias_21 := lv_dif*-1;
      lv_reg_cbz.cob_dias_31 :=  NULL;
      lv_reg_cbz.cob_dias_42 :=  NULL;
      lv_reg_cbz.cob_dias_63 :=  NULL;
      lv_reg_cbz.cob_dias_84 :=  NULL;
      lv_reg_cbz.cob_dias_126 :=  NULL;
      lv_reg_cbz.cob_dias_189 :=  NULL;
      lv_reg_cbz.cob_dias_999 :=  NULL;
      lv_reg_cbz.imp_abon_nmon :=  NULL;
      lv_reg_cbz.imp_abon_frac := NULL;


       INSERT INTO cob_repor_estad_recup_cobra VALUES lv_reg_cbz;

--       dbms_output.put_line(v_zonas.cod_zona || 'ACTUA ' || lv_cob_21);
--       dbms_output.put_line(v_zonas.cod_zona || 'FOX ' || lv_cob_21_fox);
  --     dbms_output.put_line(v_zonas.cod_zona || 'DIF ' || lv_reg_cbz.cob_dias_21);

     END IF;

     IF lv_cob_31_fox <> v_zonas.cob_31 THEN

      DELETE FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri
      AND cbz.cod_zona = v_zonas.cod_zona
      AND cbz.oid_clie = 999999999999
      AND cbz.cob_dias_31 IS NOT NULL;

      SELECT SUM(cbz.cob_dias_31)
      INTO lv_cob_31
      FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri;

      -- Cobrado --
      SELECT NVL(SUM(ap.imp_abon),0)
      INTO lv_imp_abon_31
      FROM ccc_aplic_abono_cargo ap,
           cob_repor_estad_recup_cobra cbz
      WHERE ap.mvcc_oid_movi_carg = cbz.oid_movi_cc
       AND cbz.cod_peri =  v_zonas.cod_peri
        AND ap.cmba_oid_movi_banc is NOT NULL
        AND ap.fec_apli <= cbz.fec_cier_31;

      lv_dif := lv_cob_31 - lv_cob_31_fox - NVL(lv_imp_abon_31,0);

      lv_reg_cbz.cod_pais :=  v_zonas.cod_pais;
      lv_reg_cbz.cod_soci :=  v_zonas.cod_soci;
      lv_reg_cbz.des_regi :=  v_zonas.des_regi;
      lv_reg_cbz.cod_peri := v_zonas.cod_peri;
      lv_reg_cbz.cod_zona := v_zonas.cod_zona;
      lv_reg_cbz.des_zona := v_zonas.des_zona;
      lv_reg_cbz.oid_clie_zona := v_zonas.oid_clie_zona;
      lv_reg_cbz.oid_clie := 999999999999;
      lv_reg_cbz.oid_movi_cc := v_zonas.cod_peri || v_zonas.cod_zona || 31;
       lv_reg_cbz.imp_fact := NULL;
      lv_reg_cbz.imp_carg_dire := NULL;
      lv_reg_cbz.imp_carg_frac := NULL;
      lv_reg_cbz.cob_dias_31 := lv_dif*-1;
      lv_reg_cbz.cob_dias_21 :=  NULL;
      lv_reg_cbz.cob_dias_42 :=  NULL;
      lv_reg_cbz.cob_dias_63 :=  NULL;
      lv_reg_cbz.cob_dias_84 :=  NULL;
      lv_reg_cbz.cob_dias_126 := NULL;
      lv_reg_cbz.cob_dias_189 := NULL;
      lv_reg_cbz.cob_dias_999 := NULL;
      lv_reg_cbz.imp_abon_nmon :=  NULL;
      lv_reg_cbz.imp_abon_frac := NULL;

       INSERT INTO cob_repor_estad_recup_cobra VALUES lv_reg_cbz;


     END IF;

     IF lv_cob_42_fox <> v_zonas.cob_42 THEN

      DELETE FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri
      AND cbz.cod_zona = v_zonas.cod_zona
      AND cbz.oid_clie = 999999999999
      AND cbz.cob_dias_42 IS NOT NULL;

      SELECT SUM(cbz.cob_dias_42)
      INTO lv_cob_42
      FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri;

      -- Cobrado --
      SELECT NVL(SUM(ap.imp_abon),0)
      INTO lv_imp_abon_42
      FROM ccc_aplic_abono_cargo ap,
           cob_repor_estad_recup_cobra cbz
      WHERE ap.mvcc_oid_movi_carg = cbz.oid_movi_cc
        AND ap.cmba_oid_movi_banc is NOT NULL
         AND cbz.cod_peri =  v_zonas.cod_peri
        AND ap.fec_apli <= cbz.fec_cier_42;

      lv_dif := lv_cob_42 - lv_cob_42_fox - NVL(lv_imp_abon_42,0);

      lv_reg_cbz.cod_pais :=  v_zonas.cod_pais;
      lv_reg_cbz.cod_soci :=  v_zonas.cod_soci;
      lv_reg_cbz.des_regi :=  v_zonas.des_regi;
      lv_reg_cbz.cod_peri := v_zonas.cod_peri;
      lv_reg_cbz.cod_zona := v_zonas.cod_zona;
      lv_reg_cbz.des_zona := v_zonas.des_zona;
      lv_reg_cbz.oid_clie_zona := v_zonas.oid_clie_zona;
      lv_reg_cbz.oid_clie := 999999999999;
      lv_reg_cbz.oid_movi_cc := v_zonas.cod_peri || v_zonas.cod_zona || 42;
       lv_reg_cbz.imp_fact := NULL;
      lv_reg_cbz.imp_carg_dire := NULL;
      lv_reg_cbz.imp_carg_frac := NULL;
      lv_reg_cbz.cob_dias_42 := lv_dif*-1;
      lv_reg_cbz.cob_dias_21 :=  NULL;
      lv_reg_cbz.cob_dias_31 :=  NULL;
      lv_reg_cbz.cob_dias_63 :=  NULL;
      lv_reg_cbz.cob_dias_84 :=  NULL;
      lv_reg_cbz.cob_dias_126 :=  NULL;
      lv_reg_cbz.cob_dias_189 :=  NULL;
      lv_reg_cbz.cob_dias_999 :=  NULL;
      lv_reg_cbz.imp_abon_nmon :=  NULL;
      lv_reg_cbz.imp_abon_frac := NULL;

      INSERT INTO cob_repor_estad_recup_cobra VALUES lv_reg_cbz;


     END IF;

     IF lv_cob_63_fox <> v_zonas.cob_63 THEN

      DELETE FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri
      AND cbz.cod_zona = v_zonas.cod_zona
      AND cbz.oid_clie = 999999999999
      AND cbz.cob_dias_63 IS NOT NULL;

      SELECT SUM(cbz.cob_dias_63)
      INTO lv_cob_63
      FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri;

      SELECT NVL(SUM(ap.imp_abon),0)
      INTO lv_imp_abon_63
      FROM ccc_aplic_abono_cargo ap,
           cob_repor_estad_recup_cobra cbz
      WHERE ap.mvcc_oid_movi_carg = cbz.oid_movi_cc
       AND cbz.cod_peri =  v_zonas.cod_peri
        AND ap.cmba_oid_movi_banc is NOT NULL
        AND ap.fec_apli <= cbz.fec_cier_63;

      lv_dif := lv_cob_63 - lv_cob_63_fox - NVL(lv_imp_abon_63,0);

      lv_reg_cbz.cod_pais :=  v_zonas.cod_pais;
      lv_reg_cbz.cod_soci :=  v_zonas.cod_soci;
      lv_reg_cbz.des_regi :=  v_zonas.des_regi;
      lv_reg_cbz.cod_peri := v_zonas.cod_peri;
      lv_reg_cbz.cod_zona := v_zonas.cod_zona;
      lv_reg_cbz.des_zona := v_zonas.des_zona;
      lv_reg_cbz.oid_clie_zona := v_zonas.oid_clie_zona;
      lv_reg_cbz.oid_clie := 999999999999;
      lv_reg_cbz.oid_movi_cc := v_zonas.cod_peri || v_zonas.cod_zona || 63;
       lv_reg_cbz.imp_fact := NULL;
      lv_reg_cbz.imp_carg_dire := NULL;
      lv_reg_cbz.imp_carg_frac := NULL;
      lv_reg_cbz.cob_dias_63 := lv_dif*-1;
      lv_reg_cbz.cob_dias_21 :=  NULL;
      lv_reg_cbz.cob_dias_42 :=  NULL;
      lv_reg_cbz.cob_dias_31 :=  NULL;
      lv_reg_cbz.cob_dias_84 :=  NULL;
      lv_reg_cbz.cob_dias_126 :=  NULL;
      lv_reg_cbz.cob_dias_189 :=  NULL;
      lv_reg_cbz.cob_dias_999 :=  NULL;
      lv_reg_cbz.imp_abon_nmon :=  NULL;
      lv_reg_cbz.imp_abon_frac := NULL;

      INSERT INTO cob_repor_estad_recup_cobra VALUES lv_reg_cbz;

     END IF;

     IF lv_cob_84_fox <> v_zonas.cob_84 THEN

      DELETE FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri
      AND cbz.cod_zona = v_zonas.cod_zona
      AND cbz.oid_clie = 999999999999
      AND cbz.cob_dias_84 IS NOT NULL;

      SELECT SUM(cbz.cob_dias_84)
      INTO lv_cob_84
      FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri;

            SELECT NVL(SUM(ap.imp_abon),0)
      INTO lv_imp_abon_84
      FROM ccc_aplic_abono_cargo ap,
           cob_repor_estad_recup_cobra cbz
      WHERE ap.mvcc_oid_movi_carg = cbz.oid_movi_cc
       AND cbz.cod_peri =  v_zonas.cod_peri
        AND ap.cmba_oid_movi_banc is NOT NULL
        AND ap.fec_apli <= cbz.fec_cier_84;

      lv_dif := lv_cob_84 - lv_cob_84_fox - NVL(lv_imp_abon_84,0);

      lv_reg_cbz.cod_pais :=  v_zonas.cod_pais;
      lv_reg_cbz.cod_soci :=  v_zonas.cod_soci;
      lv_reg_cbz.des_regi :=  v_zonas.des_regi;
      lv_reg_cbz.cod_peri := v_zonas.cod_peri;
      lv_reg_cbz.cod_zona := v_zonas.cod_zona;
      lv_reg_cbz.des_zona := v_zonas.des_zona;
      lv_reg_cbz.oid_clie_zona := v_zonas.oid_clie_zona;
      lv_reg_cbz.oid_clie := 999999999999;
      lv_reg_cbz.oid_movi_cc := v_zonas.cod_peri || v_zonas.cod_zona || 84;
      lv_reg_cbz.cob_dias_84 := lv_dif*-1;
       lv_reg_cbz.imp_fact := NULL;
      lv_reg_cbz.imp_carg_dire := NULL;
      lv_reg_cbz.imp_carg_frac := NULL;
      lv_reg_cbz.cob_dias_21 :=  NULL;
      lv_reg_cbz.cob_dias_42 :=  NULL;
      lv_reg_cbz.cob_dias_63 :=  NULL;
      lv_reg_cbz.cob_dias_31 :=  NULL;
      lv_reg_cbz.cob_dias_126 :=  NULL;
      lv_reg_cbz.cob_dias_189 :=  NULL;
      lv_reg_cbz.cob_dias_999 :=  NULL;
      lv_reg_cbz.imp_abon_nmon :=  NULL;
      lv_reg_cbz.imp_abon_frac := NULL;

      INSERT INTO cob_repor_estad_recup_cobra VALUES lv_reg_cbz;

     END IF;

      IF lv_cob_126_fox <> v_zonas.cob_126 THEN

      DELETE FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri
      AND cbz.cod_zona = v_zonas.cod_zona
      AND cbz.oid_clie = 999999999999
      AND cbz.cob_dias_126 IS NOT NULL;

      SELECT SUM(cbz.cob_dias_126)
      INTO lv_cob_126
      FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri;

            SELECT NVL(SUM(ap.imp_abon),0)
      INTO lv_imp_abon_126
      FROM ccc_aplic_abono_cargo ap,
           cob_repor_estad_recup_cobra cbz
      WHERE ap.mvcc_oid_movi_carg = cbz.oid_movi_cc
       AND cbz.cod_peri =  v_zonas.cod_peri
        AND ap.cmba_oid_movi_banc is NOT NULL
        AND ap.fec_apli <= cbz.fec_cier_126;

      lv_dif := lv_cob_126 - lv_cob_126_fox - NVL(lv_imp_abon_126,0);

      lv_reg_cbz.cod_pais :=  v_zonas.cod_pais;
      lv_reg_cbz.cod_soci :=  v_zonas.cod_soci;
      lv_reg_cbz.des_regi :=  v_zonas.des_regi;
      lv_reg_cbz.cod_peri := v_zonas.cod_peri;
      lv_reg_cbz.cod_zona := v_zonas.cod_zona;
      lv_reg_cbz.des_zona := v_zonas.des_zona;
      lv_reg_cbz.oid_clie_zona := v_zonas.oid_clie_zona;
      lv_reg_cbz.oid_clie := 999999999999;
      lv_reg_cbz.oid_movi_cc := v_zonas.cod_peri || v_zonas.cod_zona || 26;
       lv_reg_cbz.imp_fact := NULL;
      lv_reg_cbz.imp_carg_dire := NULL;
      lv_reg_cbz.imp_carg_frac := NULL;
      lv_reg_cbz.cob_dias_126 := lv_dif*-1;
       lv_reg_cbz.cob_dias_21 :=  NULL;
      lv_reg_cbz.cob_dias_42 :=  NULL;
      lv_reg_cbz.cob_dias_63 :=  NULL;
      lv_reg_cbz.cob_dias_84 :=  NULL;
      lv_reg_cbz.cob_dias_999 :=  NULL;
      lv_reg_cbz.cob_dias_189 :=  NULL;
      lv_reg_cbz.cob_dias_31 :=  NULL;
      lv_reg_cbz.imp_abon_nmon :=  NULL;
      lv_reg_cbz.imp_abon_frac := NULL;

      INSERT INTO cob_repor_estad_recup_cobra VALUES lv_reg_cbz;

     END IF;


    IF NVL(lv_imp_abon_nmon_fox,0) <> NVL(v_zonas.imp_abon_nmon,0) THEN

      DELETE FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri
      AND cbz.cod_zona = v_zonas.cod_zona
      AND cbz.oid_clie = 999999999999
      AND cbz.imp_abon_nmon IS NOT NULL;

      SELECT SUM(cbz.imp_abon_nmon)
      INTO lv_imp_abon_nmon
      FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri;

      lv_dif := lv_imp_abon_nmon - lv_imp_abon_nmon_fox;

      lv_reg_cbz.cod_pais :=  v_zonas.cod_pais;
      lv_reg_cbz.cod_soci :=  v_zonas.cod_soci;
      lv_reg_cbz.des_regi :=  v_zonas.des_regi;
      lv_reg_cbz.cod_peri := v_zonas.cod_peri;
      lv_reg_cbz.cod_zona := v_zonas.cod_zona;
      lv_reg_cbz.des_zona := v_zonas.des_zona;
      lv_reg_cbz.oid_clie_zona := v_zonas.oid_clie_zona;
      lv_reg_cbz.oid_clie := 999999999999;
      lv_reg_cbz.oid_movi_cc := v_zonas.cod_peri || v_zonas.cod_zona || 01;
      lv_reg_cbz.imp_fact := NULL;
      lv_reg_cbz.imp_carg_dire := NULL;
      lv_reg_cbz.imp_carg_frac := NULL;
      lv_reg_cbz.cob_dias_999 := NULL;
      lv_reg_cbz.cob_dias_21 := NULL;
      lv_reg_cbz.cob_dias_42 :=  NULL;
      lv_reg_cbz.cob_dias_63 :=  NULL;
      lv_reg_cbz.cob_dias_84 :=  NULL;
      lv_reg_cbz.cob_dias_126 :=  NULL;
      lv_reg_cbz.cob_dias_189 :=  NULL;
      lv_reg_cbz.cob_dias_31 :=  NULL;
      lv_reg_cbz.imp_abon_nmon := lv_dif*-1;
      lv_reg_cbz.imp_abon_frac := NULL;

      INSERT INTO cob_repor_estad_recup_cobra VALUES lv_reg_cbz;

       -- dbms_output.put_line(v_zonas.cod_zona || 'ACTUA ' || lv_imp_abon_nmon);
      -- dbms_output.put_line(v_zonas.cod_zona || 'FOX ' || lv_imp_abon_nmon_fox);
      -- dbms_output.put_line(v_zonas.cod_zona || 'DIF ' || lv_reg_cbz.imp_abon_nmon);

    END IF;

     IF NVL(lv_imp_abon_frac_fox,0) <> NVL(v_zonas.imp_abon_frac,0) THEN

      DELETE FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri
      AND cbz.cod_zona = v_zonas.cod_zona
      AND cbz.oid_clie = 999999999999
      AND cbz.imp_abon_frac IS NOT NULL;

      SELECT SUM(cbz.imp_abon_frac)
      INTO lv_imp_abon_frac
      FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri;

      lv_dif := lv_imp_abon_frac - lv_imp_abon_frac_fox;

      lv_reg_cbz.cod_pais :=  v_zonas.cod_pais;
      lv_reg_cbz.cod_soci :=  v_zonas.cod_soci;
      lv_reg_cbz.des_regi :=  v_zonas.des_regi;
      lv_reg_cbz.cod_peri := v_zonas.cod_peri;
      lv_reg_cbz.cod_zona := v_zonas.cod_zona;
      lv_reg_cbz.des_zona := v_zonas.des_zona;
      lv_reg_cbz.oid_clie_zona := v_zonas.oid_clie_zona;
      lv_reg_cbz.oid_clie := 999999999999;
      lv_reg_cbz.oid_movi_cc := v_zonas.cod_peri || v_zonas.cod_zona || 02;
      lv_reg_cbz.imp_fact := NULL;
      lv_reg_cbz.imp_carg_dire := NULL;
      lv_reg_cbz.imp_carg_frac := NULL;
      lv_reg_cbz.cob_dias_999 := NULL;
      lv_reg_cbz.cob_dias_21 := NULL;
      lv_reg_cbz.cob_dias_42 :=  NULL;
      lv_reg_cbz.cob_dias_63 :=  NULL;
      lv_reg_cbz.cob_dias_84 :=  NULL;
      lv_reg_cbz.cob_dias_126 :=  NULL;
      lv_reg_cbz.cob_dias_189 :=  NULL;
      lv_reg_cbz.cob_dias_31 :=  NULL;
      lv_reg_cbz.imp_abon_nmon := NULL;
      lv_reg_cbz.imp_abon_frac := lv_dif*-1;

      INSERT INTO cob_repor_estad_recup_cobra VALUES lv_reg_cbz;

       -- dbms_output.put_line(v_zonas.cod_zona || 'ACTUA ' || lv_imp_abon_nmon);
      -- dbms_output.put_line(v_zonas.cod_zona || 'FOX ' || lv_imp_abon_nmon_fox);
      -- dbms_output.put_line(v_zonas.cod_zona || 'DIF ' || lv_reg_cbz.imp_abon_nmon);

    END IF;

    -- Cuadrando el facturado neto --
    UPDATE cob_repor_estad_recup_cobra cbz
    SET cbz.imp_Fact_neto = NVL(cbz.imp_fact,0) + NVL(cbz.imp_carg_dire,0) + NVL(cbz.imp_carg_frac,0) - NVL(cbz.imp_abon_frac,0) - NVL(cbz.imp_abon_nmon,0)
    WHERE cbz.cod_peri = v_zonas.cod_peri;

    -- Validando el Saldo
    SELECT SUM(mcc.imp_pend)
    INTO lv_sald_pend_real
    FROM ccc_movim_cuent_corri mcc,
         cra_perio cp,
         seg_perio_corpo spc
    WHERE mcc.imp_movi > 0
    AND mcc.perd_oid_peri = cp.oid_peri
    AND cp.peri_oid_peri = spc.oid_peri
    AND spc.cod_peri = v_zonas.cod_peri;

    SELECT SUM(cbz.imp_sald_pend_sac), SUM(cbz.imp_fact_neto)
    INTO lv_sald_pend, lv_imp_fact_neto
    FROM cob_repor_estad_recup_cobra cbz
    WHERE cbz.cod_peri = v_zonas.cod_peri;

    IF lv_sald_pend_real <> lv_sald_pend THEN
     dbms_Output.put_line('Campaña :  ' || v_zonas.cod_peri);
     dbms_output.put_line('Diferencia de Saldos ');
     dbms_output.put_line('Saldos Real ' || lv_sald_pend_real);
     dbms_output.put_line('Saldo CBZ ' || lv_sald_pend);
    END IF;

    lv_cob_999_fox := NVL(lv_imp_fact_neto,0) - NVL(lv_sald_pend,0);

    IF lv_cob_999_fox <> v_zonas.cob_999 THEN

      DELETE FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri
      AND cbz.cod_zona = v_zonas.cod_zona
      AND cbz.oid_clie = 999999999999
      AND cbz.cob_dias_999 IS NOT NULL;

      SELECT SUM(cbz.cob_dias_999)
      INTO lv_cob_999
      FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri;


      SELECT NVL(SUM(ap.imp_abon),0)
      INTO lv_imp_abon_999
      FROM ccc_aplic_abono_cargo ap,
           cob_repor_estad_recup_cobra cbz
      WHERE ap.mvcc_oid_movi_carg = cbz.oid_movi_cc
       AND cbz.cod_peri =  v_zonas.cod_peri
        AND ap.cmba_oid_movi_banc is NOT NULL;

      lv_dif := lv_cob_999 - lv_cob_999_fox;


      lv_reg_cbz.cod_pais :=  v_zonas.cod_pais;
      lv_reg_cbz.cod_soci :=  v_zonas.cod_soci;
      lv_reg_cbz.des_regi :=  v_zonas.des_regi;
      lv_reg_cbz.cod_peri := v_zonas.cod_peri;
      lv_reg_cbz.cod_zona := v_zonas.cod_zona;
      lv_reg_cbz.des_zona := v_zonas.des_zona;
      lv_reg_cbz.oid_clie_zona := v_zonas.oid_clie_zona;
      lv_reg_cbz.oid_clie := 999999999999;
      lv_reg_cbz.oid_movi_cc := v_zonas.cod_peri || v_zonas.cod_zona || 99;
      lv_reg_cbz.imp_fact := NULL;
      lv_reg_cbz.imp_carg_dire := NULL;
      lv_reg_cbz.imp_carg_frac := NULL;
      lv_reg_cbz.cob_dias_999 := lv_dif*-1;
      lv_reg_cbz.cob_dias_21 := NULL;
      lv_reg_cbz.cob_dias_42 :=  NULL;
      lv_reg_cbz.cob_dias_63 :=  NULL;
      lv_reg_cbz.cob_dias_84 :=  NULL;
      lv_reg_cbz.cob_dias_126 :=  NULL;
      lv_reg_cbz.cob_dias_189 :=  NULL;
      lv_reg_cbz.cob_dias_31 :=  NULL;
      lv_reg_cbz.imp_abon_nmon :=  NULL;
      lv_reg_cbz.imp_abon_frac := NULL;

      INSERT INTO cob_repor_estad_recup_cobra VALUES lv_reg_cbz;


    END IF;

    IF lv_cob_189_fox <> v_zonas.cob_189 THEN

      DELETE FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri
      AND cbz.cod_zona = v_zonas.cod_zona
      AND cbz.oid_clie = 999999999999
      AND cbz.cob_dias_189 IS NOT NULL;

      SELECT SUM(cbz.cob_dias_189)
      INTO lv_cob_189
      FROM cob_repor_estad_recup_cobra cbz
      WHERE cbz.cod_peri = v_zonas.cod_peri;

           SELECT NVL(SUM(ap.imp_abon),0)
      INTO lv_imp_abon_189
      FROM ccc_aplic_abono_cargo ap,
           cob_repor_estad_recup_cobra cbz
      WHERE ap.mvcc_oid_movi_carg = cbz.oid_movi_cc
       AND cbz.cod_peri =  v_zonas.cod_peri
        AND ap.cmba_oid_movi_banc is NOT NULL
        AND ap.fec_apli <= cbz.fec_cier_189;

      lv_dif := lv_cob_189 - lv_cob_189_fox - NVL(lv_imp_abon_189,0);

      lv_reg_cbz.cod_pais :=  v_zonas.cod_pais;
      lv_reg_cbz.cod_soci :=  v_zonas.cod_soci;
      lv_reg_cbz.des_regi :=  v_zonas.des_regi;
      lv_reg_cbz.cod_peri := v_zonas.cod_peri;
      lv_reg_cbz.cod_zona := v_zonas.cod_zona;
      lv_reg_cbz.des_zona := v_zonas.des_zona;
      lv_reg_cbz.oid_clie_zona := v_zonas.oid_clie_zona;
      lv_reg_cbz.oid_clie := 999999999999;
      lv_reg_cbz.oid_movi_cc := v_zonas.cod_peri || v_zonas.cod_zona || 89;
      lv_reg_cbz.imp_fact := NULL;
      lv_reg_cbz.imp_carg_dire := NULL;
      lv_reg_cbz.imp_carg_frac := NULL;
      lv_reg_cbz.cob_dias_189 := lv_dif*-1;
       lv_reg_cbz.cob_dias_21 :=  NULL;
      lv_reg_cbz.cob_dias_42 :=  NULL;
      lv_reg_cbz.cob_dias_63 :=  NULL;
      lv_reg_cbz.cob_dias_84 :=  NULL;
      lv_reg_cbz.cob_dias_126 :=  NULL;
      lv_reg_cbz.cob_dias_31 :=  NULL;
      lv_reg_cbz.cob_dias_999 :=  NULL;
      lv_reg_cbz.imp_abon_nmon :=  NULL;
      lv_reg_cbz.imp_abon_frac := NULL;

      INSERT INTO cob_repor_estad_recup_cobra VALUES lv_reg_cbz;

     END IF;

  END LOOP;

 END COB_PR_MIGRA_ESTAD_RECUP_COBRA;


 PROCEDURE COB_PR_CARGA_ESTAD_RECUP_COBRA(
  p_cod_peri                       IN   seg_perio_corpo.cod_peri%TYPE)
 IS

  TYPE t_tab_cbz IS TABLE OF cob_repor_estad_recup_cobra%ROWTYPE;
  lv_tab_cbz                       t_tab_cbz;


  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_cod_soci                      seg_socie.cod_soci%TYPE;
  lv_val_dias_tram_1               cob_repor_estad_param.val_dias_tram_1%TYPE;
  lv_val_dias_tram_2               cob_repor_estad_param.val_dias_tram_2%TYPE;
  lv_val_dias_tram_3               cob_repor_estad_param.val_dias_tram_3%TYPE;
  lv_val_dias_tram_4               cob_repor_estad_param.val_dias_tram_4%TYPE;
  lv_val_dias_tram_5               cob_repor_estad_param.val_dias_tram_5%TYPE;
  lv_val_dias_tram_6               cob_repor_estad_param.val_dias_tram_6%TYPE;
  lv_val_dias_tram_7               cob_repor_estad_param.val_dias_tram_7%TYPE;
  lv_val_dias_tram_8               cob_repor_estad_param.val_dias_tram_8%TYPE;
  lv_val_dias_tram_9               cob_repor_estad_param.val_dias_tram_9%TYPE;
  lv_val_dias_tram_15              cob_repor_estad_param.val_dias_tram_15%TYPE;
  lv_val_dias_tram_16              cob_repor_estad_param.val_dias_tram_16%TYPE;
  lv_val_dias_tram_17              cob_repor_estad_param.val_dias_tram_17%TYPE;
  lv_val_dias_tram_18              cob_repor_estad_param.val_dias_tram_18%TYPE;
  lv_val_dias_tram_19              cob_repor_estad_param.val_dias_tram_19%TYPE;
  lv_val_dias_tram_20              cob_repor_estad_param.val_dias_tram_20%TYPE;
  lv_val_dias_tram_22              cob_repor_estad_param.val_dias_tram_22%TYPE;
  lv_val_dias_tram_23              cob_repor_estad_param.val_dias_tram_23%TYPE;
  lv_val_dias_tram_24              cob_repor_estad_param.val_dias_tram_24%TYPE;
  lv_val_dias_tram_25              cob_repor_estad_param.val_dias_tram_25%TYPE;
  lv_val_dias_tram_26              cob_repor_estad_param.val_dias_tram_26%TYPE;
  lv_val_dias_tram_27              cob_repor_estad_param.val_dias_tram_27%TYPE;
  lv_val_dias_tram_28              cob_repor_estad_param.val_dias_tram_28%TYPE;
  lv_val_dias_tram_29              cob_repor_estad_param.val_dias_tram_29%TYPE;
  lv_val_dias_tram_30              cob_repor_estad_param.val_dias_tram_30%TYPE;
  lv_val_dias_tram_32              cob_repor_estad_param.val_dias_tram_32%TYPE;
  lv_val_dias_tram_33              cob_repor_estad_param.val_dias_tram_33%TYPE;
  lv_val_dias_tram_34              cob_repor_estad_param.val_dias_tram_34%TYPE;
  lv_val_dias_tram_35              cob_repor_estad_param.val_dias_tram_35%TYPE;
  lv_val_dias_tram_36              cob_repor_estad_param.val_dias_tram_36%TYPE;
  lv_val_dias_tram_37              cob_repor_estad_param.val_dias_tram_37%TYPE;
  lv_val_dias_tram_38              cob_repor_estad_param.val_dias_tram_38%TYPE;
  lv_val_dias_tram_39              cob_repor_estad_param.val_dias_tram_39%TYPE;
  lv_val_dias_tram_40              cob_repor_estad_param.val_dias_tram_40%TYPE;
  lv_val_dias_tram_41              cob_repor_estad_param.val_dias_tram_41%TYPE;
  lv_ind_regi_frac                 cob_repor_estad_param.ind_regi_frac%TYPE;
  lv_ind_regi_dire                 cob_repor_estad_param.ind_regi_dire%TYPE;
  lv_ind_regi_serv                 cob_repor_estad_param.ind_regi_serv%TYPE;
  lv_oid_subp_fact                 ccc_subpr.oid_subp%TYPE;
  lv_ind_etap_vent                 cob_pais_socie_param.ind_etap_vent%TYPE;

 BEGIN

  lv_cod_pais := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_cod_soci := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoSociedad');

  gv_log_cod_pais := lv_cod_pais;
  gv_log_cod_soci := lv_cod_soci;
  gv_log_user := USER;
  gv_log_cod_modu := 'COB';
  gv_log_cod_proc := '21';

  dbms_output.put_line(lv_cod_pais);
  dbms_output.put_line(lv_cod_soci);

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE_EJEC(lv_cod_pais, lv_cod_soci, gv_log_cod_modu, gv_log_cod_proc , gv_log_user, gv_cod_proc_ejec);

  gv_des_log:='Inicio COB_PR_CARGA_ESTAD_RECUP_COBRA  Campaña ' || p_cod_peri;
  fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( lv_cod_pais, lv_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                     gv_cod_proc_ejec, gv_des_log);

  SELECT
   prm.val_dias_tram_1,
   prm.val_dias_tram_2,
   prm.val_dias_tram_3,
   prm.val_dias_tram_4,
   prm.val_dias_tram_5,
   prm.val_dias_tram_6,
   prm.val_dias_tram_7,
   prm.val_dias_tram_8,
   prm.val_dias_tram_9,
   prm.val_dias_tram_15,
   prm.val_dias_tram_16,
   prm.val_dias_tram_17,
   prm.val_dias_tram_18,
   prm.val_dias_tram_19,
   prm.val_dias_tram_20,
   prm.val_dias_tram_22,
   prm.val_dias_tram_23,
   prm.val_dias_tram_24,
   prm.val_dias_tram_25,
   prm.val_dias_tram_26,
   prm.val_dias_tram_27,
   prm.val_dias_tram_28,
   prm.val_dias_tram_29,
   prm.val_dias_tram_30,
   prm.val_dias_tram_32,
   prm.val_dias_tram_33,
   prm.val_dias_tram_34,
   prm.val_dias_tram_35,
   prm.val_dias_tram_36,
   prm.val_dias_tram_37,
   prm.val_dias_tram_38,
   prm.val_dias_tram_39,
   prm.val_dias_tram_40,
   prm.val_dias_tram_41,
   prm.ind_regi_frac,
   prm.ind_regi_dire,
   prm.ind_regi_serv
  INTO
   lv_val_dias_tram_1,
   lv_val_dias_tram_2,
   lv_val_dias_tram_3,
   lv_val_dias_tram_4,
   lv_val_dias_tram_5,
   lv_val_dias_tram_6,
   lv_val_dias_tram_7,
   lv_val_dias_tram_8,
   lv_val_dias_tram_9,
   lv_val_dias_tram_15,
   lv_val_dias_tram_16,
   lv_val_dias_tram_17,
   lv_val_dias_tram_18,
   lv_val_dias_tram_19,
   lv_val_dias_tram_20,
   lv_val_dias_tram_22,
   lv_val_dias_tram_23,
   lv_val_dias_tram_24,
   lv_val_dias_tram_25,
   lv_val_dias_tram_26,
   lv_val_dias_tram_27,
   lv_val_dias_tram_28,
   lv_val_dias_tram_29,
   lv_val_dias_tram_30,
   lv_val_dias_tram_32,
   lv_val_dias_tram_33,
   lv_val_dias_tram_34,
   lv_val_dias_tram_35,
   lv_val_dias_tram_36,
   lv_val_dias_tram_37,
   lv_val_dias_tram_38,
   lv_val_dias_tram_39,
   lv_val_dias_tram_40,
   lv_val_dias_tram_41,
   lv_ind_regi_frac,
   lv_ind_regi_dire,
   lv_ind_regi_serv
  FROM  cob_repor_estad_param prm
  WHERE prm.cod_pais = lv_cod_pais
    AND prm.cod_soci = lv_cod_soci;

  SELECT psp.ind_etap_vent
  INTO lv_ind_etap_vent
  FROM cob_pais_socie_param  psp
  WHERE psp.cod_pais = lv_cod_pais
    AND psp.cod_soci = lv_cod_soci;

  SELECT
   lv_cod_pais,
   lv_cod_soci,
   mcc.perd_oid_peri,
   spc.cod_peri,
   zr.oid_regi,
   zr.cod_regi,
   zr.des_regi,
   zr.clie_oid_clie,
   zz.oid_zona,
   zz.cod_zona,
   zz.des_zona,
   zz.clie_oid_clie,
   NULL, --fecha cierre zona
   zs.oid_secc,
   zs.cod_secc,
   zs.des_secci,
   zs.clie_oid_clie,
   mcc.clie_oid_clie,
   mcc.oid_movi_cc,
   mcc.subp_oid_subp_crea,
   0, -- Indicador cargo fraccionado
   mcc.imp_movi,
   mcc.imp_paga,
   mcc.imp_pend,
   0,            --Importe diferencia
   0,            -- Ultimo numero de historia
   0,            -- Total Facturado
   0,            -- Facturado Neto
   0,            -- Importe Saldo Pendiente SAC
   0,            -- Cobranza 21
   mcc.fec_docu + lv_val_dias_tram_1,  -- Fecha Cierre 21
   0,            -- Cobranza 31
   mcc.fec_docu + lv_val_dias_tram_2,  --Fecha Cierre 31
   0,            --Cobranza Estimada 31
   0,            -- Porcentaje de Recuperacion
   0,             -- Importe pendiente de Recuperar
   0,             -- Cobranza 42
                         -- Fecha Cierre 42
   mcc.fec_docu + lv_val_dias_tram_3,
   0,             -- Cobranza 63
   mcc.fec_docu + lv_val_dias_tram_4,  -- Fecha Cierre 63
   0,             -- Cobranza 84
   mcc.fec_docu + lv_val_dias_tram_5,  -- Fecha Cierre 84
   0,             -- Cobranza 126
   mcc.fec_docu + lv_val_dias_tram_6,  -- Fecha Cierre 126
   0,             -- Cobranza 189
   mcc.fec_docu + lv_val_dias_tram_7,  -- Fecha Cierre 126
   0,              -- Cobranza 999
   0,              -- Importe Cargo Directo
   0,              --Importe Abonos Con
   0,              --Importe Abonos No Monetarios
   0,              --Importe Saldo Pendiente
   0,              --Importe Cargo Fraccionado
   0,              --Importe Abono Fraccionado
   0,              --Importe Facturado No Enviado
   0,              --Importe Devolucion
   USER,
   SYSDATE,
   CASE
    WHEN lv_ind_etap_vent=1 THEN
     FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_CIERR_VENTA(
                  lv_cod_pais,lv_cod_soci,spc.cod_peri,zr.cod_regi,zz.cod_zona)
     --cob_fn_obtie_fecha_cierr_venta(p_cod_pais, p_cod_soci, spc.cod_peri,zr.cod_regi,zz.cod_zona)
   ELSE
    NULL
   END CASE,
   NULL,
   0,
   0,
   0,
   0,
   0,              -- Cobranza 47,
   mcc.fec_docu + lv_val_dias_tram_8,
   0,              -- Cobranza 53
   mcc.fec_docu + lv_val_dias_tram_9,
   1,               -- ind_deud_pend
   0,
   mcc.fec_docu + lv_val_dias_tram_22,  -- Fecha Cierre 22
   0,
   mcc.fec_docu + lv_val_dias_tram_23,  -- Fecha Cierre 23
   0,
   mcc.fec_docu + lv_val_dias_tram_24,  -- Fecha Cierre 24
   0,
   mcc.fec_docu + lv_val_dias_tram_25,  -- Fecha Cierre 25
   0,
   mcc.fec_docu + lv_val_dias_tram_26,  -- Fecha Cierre 26
   0,
   mcc.fec_docu + lv_val_dias_tram_36,  -- Fecha Cierre 36
   0,
   mcc.fec_docu + lv_val_dias_tram_15,  -- Fecha Cierre 15
   0,
   mcc.fec_docu + lv_val_dias_tram_16,  -- Fecha Cierre 16
   0,
   mcc.fec_docu + lv_val_dias_tram_17,  -- Fecha Cierre 17
   0,
   mcc.fec_docu + lv_val_dias_tram_18,  -- Fecha Cierre 18
   0,
   mcc.fec_docu + lv_val_dias_tram_19,  -- Fecha Cierre 19
   0,
   mcc.fec_docu + lv_val_dias_tram_20,  -- Fecha Cierre 20
   0,
   mcc.fec_docu + lv_val_dias_tram_27,  -- Fecha Cierre 27
   0,
   mcc.fec_docu + lv_val_dias_tram_28,  -- Fecha Cierre 28
   0,
   mcc.fec_docu + lv_val_dias_tram_29,  -- Fecha Cierre 29
   0,
   mcc.fec_docu + lv_val_dias_tram_30,  -- Fecha Cierre 30
   0,
   mcc.fec_docu + lv_val_dias_tram_32,  -- Fecha Cierre 32
   0,
   mcc.fec_docu + lv_val_dias_tram_33,  -- Fecha Cierre 33
   0,
   mcc.fec_docu + lv_val_dias_tram_34,  -- Fecha Cierre 34
   0,
   mcc.fec_docu + lv_val_dias_tram_35,  -- Fecha Cierre 35
   0,
   mcc.fec_docu + lv_val_dias_tram_37,  -- Fecha Cierre 37
   0,
   mcc.fec_docu + lv_val_dias_tram_38,  -- Fecha Cierre 38
   0,
   mcc.fec_docu + lv_val_dias_tram_39,  -- Fecha Cierre 39
   0,
   mcc.fec_docu + lv_val_dias_tram_40,  -- Fecha Cierre 40
   0,
   mcc.fec_docu + lv_val_dias_tram_41  -- Fecha Cierre 41
  BULK COLLECT INTO lv_tab_cbz
  FROM
   ccc_movim_cuent_corri mcc,
   cra_perio cp,
   seg_perio_corpo spc,
   zon_secci zs,
   zon_zona zz,
   zon_Regio zr
  WHERE mcc.perd_oid_peri = cp.oid_peri
    AND cp.peri_oid_peri = spc.oid_peri
    AND mcc.zscc_oid_secc = zs.oid_secc
    AND zz.oid_zona = zs.zzon_oid_zona
    AND zr.oid_regi = zz.zorg_oid_regi
    AND mcc.imp_movi > 0
    AND spc.cod_peri = p_cod_peri
    AND EXISTS (
            SELECT cs.oid_subp
            FROM
             cob_repor_estad_param_subpr param,
             ccc_subpr cs,
             ccc_proce cp
            WHERE param.cod_pais = lv_cod_pais
            AND param.cod_soci = lv_cod_soci
            AND param.cod_proc = cp.cod_proc
            AND param.cod_subp = cs.cod_subp
            AND cp.oid_proc = cs.ccpr_oid_proc
            AND cs.oid_subp = mcc.subp_oid_subp_crea);

  FORALL X IN lv_tab_cbz.FIRST .. lv_tab_cbz.LAST
   INSERT INTO cob_repor_estad_recup_cobra VALUES lv_tab_cbz(x);

  SELECT cs.oid_subp
  INTO lv_oid_subp_fact
  FROM
   ccc_proce cp,
   ccc_subpr cs
  WHERE cp.oid_proc=cs.ccpr_oid_proc
    AND cp.cod_proc = 'CCC001'
    AND cs.cod_subp = 1;

  -- O/C
  UPDATE cob_repor_estad_recup_cobra z
  SET
   z.imp_fact=z.imp_movi,
   z.imp_fact_neto=z.imp_movi
  WHERE z.oid_subp_crea=lv_oid_subp_fact
    AND z.ind_cfra = 0
    AND z.cod_peri = p_cod_peri;

  -- Atenciones de Servicio
  UPDATE cob_repor_estad_recup_cobra z
  SET
   z.imp_fact=z.imp_movi,
   z.imp_fact_neto=z.imp_movi
  WHERE z.oid_subp_crea=lv_oid_subp_fact
    AND z.ind_cfra = 3
    AND z.cod_peri = p_cod_peri;

  -- Kit de Nuevas
  UPDATE cob_repor_estad_recup_cobra z
  SET
   z.imp_fact=z.imp_movi,
   z.imp_fact_neto=z.imp_movi
  WHERE z.oid_subp_crea=lv_oid_subp_fact
    AND z.ind_cfra = 9
    AND z.cod_peri = p_cod_peri;

  -- Cargos Directos --
  UPDATE cob_repor_estad_recup_cobra z
  SET
   z.imp_carg_dire=z.imp_movi
  WHERE EXISTS (SELECT NULL
                      FROM   ccc_proce cp,
                             ccc_subpr cs
                      WHERE  cp.oid_proc = cs.ccpr_oid_proc
                      AND    cp.ind_cv_dire = 1
                      AND    cs.val_indi_cons = 'D'
                      AND    cs.oid_subp = z.oid_subp_crea )
            AND z.cod_peri = p_cod_peri;


  -- Cargos Directos x Operacion Bancaria Errada--
  UPDATE cob_repor_estad_recup_cobra z
  SET z.imp_carg_erro_banc = z.imp_movi
  WHERE EXISTS (SELECT NULL
                      FROM   ccc_proce cp,
                             ccc_subpr cs
                      WHERE  cp.oid_proc = cs.ccpr_oid_proc
                      AND cp.cod_proc = gc_cod_proc_carg_dire
                      AND cs.cod_subp = gc_cod_subp_carg_erro_banc )
    AND z.cod_peri = p_cod_peri;


  UPDATE cob_repor_estad_recup_cobra z
  SET
   z.imp_fact_neto=z.imp_fact + z.imp_carg_dire + z.imp_carg_frac - z.imp_abon_nmon - z.imp_abon_frac,
   z.imp_sald_pend_sac=z.imp_fact + z.imp_carg_frac + z.imp_carg_dire - z.cob_dias_999 - z.imp_abon_frac - z.imp_abon_nmon,
   z.imp_dife = ABS(z.imp_pend -( z.imp_fact + z.imp_carg_frac + z.imp_carg_dire - z.cob_dias_999 -  z.imp_abon_frac - z.imp_abon_nmon))
  WHERE  z.cod_peri = p_cod_peri;


  gv_des_log:= 'Fin COB_PR_CARGA_ESTAD_RECUP_COBRA';
  fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( lv_cod_pais, lv_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(lv_cod_pais, lv_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, 2);

 END COB_PR_CARGA_ESTAD_RECUP_COBRA;

 PROCEDURE COB_PR_CARGA_ESTAD_RECUP_COBRA(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_cod_soci                       IN   seg_socie.cod_soci%TYPE)
 IS

  TYPE t_tab_cbz IS TABLE OF cob_repor_estad_recup_cobra%ROWTYPE;
  lv_tab_cbz                       t_tab_cbz;
  lv_tab_cbz_frac                  t_tab_cbz;

  lv_oid_ante_regi_proc            fin_contr_regis_progr.oid_ante_regi_proc%TYPE;
  lv_oid_ulti_regi_proc            fin_contr_regis_progr.oid_ulti_regi_proc%TYPE;

  lv_val_dias_tram_1               cob_repor_estad_param.val_dias_tram_1%TYPE;
  lv_val_dias_tram_2               cob_repor_estad_param.val_dias_tram_2%TYPE;
  lv_val_dias_tram_3               cob_repor_estad_param.val_dias_tram_3%TYPE;
  lv_val_dias_tram_4               cob_repor_estad_param.val_dias_tram_4%TYPE;
  lv_val_dias_tram_5               cob_repor_estad_param.val_dias_tram_5%TYPE;
  lv_val_dias_tram_6               cob_repor_estad_param.val_dias_tram_6%TYPE;
  lv_val_dias_tram_7               cob_repor_estad_param.val_dias_tram_7%TYPE;
  lv_val_dias_tram_8               cob_repor_estad_param.val_dias_tram_8%TYPE;
  lv_val_dias_tram_9               cob_repor_estad_param.val_dias_tram_9%TYPE;
  lv_val_dias_tram_15              cob_repor_estad_param.val_dias_tram_15%TYPE;
  lv_val_dias_tram_16              cob_repor_estad_param.val_dias_tram_16%TYPE;
  lv_val_dias_tram_17              cob_repor_estad_param.val_dias_tram_17%TYPE;
  lv_val_dias_tram_18              cob_repor_estad_param.val_dias_tram_18%TYPE;
  lv_val_dias_tram_19              cob_repor_estad_param.val_dias_tram_19%TYPE;
  lv_val_dias_tram_20              cob_repor_estad_param.val_dias_tram_20%TYPE;
  lv_val_dias_tram_22              cob_repor_estad_param.val_dias_tram_22%TYPE;
  lv_val_dias_tram_23              cob_repor_estad_param.val_dias_tram_23%TYPE;
  lv_val_dias_tram_24              cob_repor_estad_param.val_dias_tram_24%TYPE;
  lv_val_dias_tram_25              cob_repor_estad_param.val_dias_tram_25%TYPE;
  lv_val_dias_tram_26              cob_repor_estad_param.val_dias_tram_26%TYPE;
  lv_val_dias_tram_27              cob_repor_estad_param.val_dias_tram_27%TYPE;
  lv_val_dias_tram_28              cob_repor_estad_param.val_dias_tram_28%TYPE;
  lv_val_dias_tram_29              cob_repor_estad_param.val_dias_tram_29%TYPE;
  lv_val_dias_tram_30              cob_repor_estad_param.val_dias_tram_30%TYPE;
  lv_val_dias_tram_32              cob_repor_estad_param.val_dias_tram_32%TYPE;
  lv_val_dias_tram_33              cob_repor_estad_param.val_dias_tram_33%TYPE;
  lv_val_dias_tram_34              cob_repor_estad_param.val_dias_tram_34%TYPE;
  lv_val_dias_tram_35              cob_repor_estad_param.val_dias_tram_35%TYPE;
  lv_val_dias_tram_36              cob_repor_estad_param.val_dias_tram_36%TYPE;
  lv_val_dias_tram_37              cob_repor_estad_param.val_dias_tram_37%TYPE;
  lv_val_dias_tram_38              cob_repor_estad_param.val_dias_tram_38%TYPE;
  lv_val_dias_tram_39              cob_repor_estad_param.val_dias_tram_39%TYPE;
  lv_val_dias_tram_40              cob_repor_estad_param.val_dias_tram_40%TYPE;
  lv_val_dias_tram_41              cob_repor_estad_param.val_dias_tram_41%TYPE;
  lv_ind_regi_frac                 cob_repor_estad_param.ind_regi_frac%TYPE;
  lv_ind_regi_dire                 cob_repor_estad_param.ind_regi_dire%TYPE;
  lv_ind_regi_serv                 cob_repor_estad_param.ind_regi_serv%TYPE;
  lv_oid_subp_fact                 ccc_subpr.oid_subp%TYPE;
  lv_ind_etap_vent                 cob_pais_socie_param.ind_etap_vent%TYPE;

 BEGIN

  gv_log_cod_pais := p_cod_pais;
  gv_log_cod_soci := p_cod_soci;
  gv_log_user := USER;
  gv_log_cod_modu := 'COB';
  gv_log_cod_proc := '21';

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE_EJEC(p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc , gv_log_user, gv_cod_proc_ejec);

  gv_des_log:='Inicio COB_PR_CARGA_ESTAD_RECUP_COBRA  Pais: ' || p_cod_pais ;
  fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                     gv_cod_proc_ejec, gv_des_log);

  SELECT
   prm.val_dias_tram_1,
   prm.val_dias_tram_2,
   prm.val_dias_tram_3,
   prm.val_dias_tram_4,
   prm.val_dias_tram_5,
   prm.val_dias_tram_6,
   prm.val_dias_tram_7,
   prm.val_dias_tram_8,
   prm.val_dias_tram_9,
   prm.val_dias_tram_15,
   prm.val_dias_tram_16,
   prm.val_dias_tram_17,
   prm.val_dias_tram_18,
   prm.val_dias_tram_19,
   prm.val_dias_tram_20,
   prm.val_dias_tram_22,
   prm.val_dias_tram_23,
   prm.val_dias_tram_24,
   prm.val_dias_tram_25,
   prm.val_dias_tram_26,
   prm.val_dias_tram_27,
   prm.val_dias_tram_28,
   prm.val_dias_tram_29,
   prm.val_dias_tram_30,
   prm.val_dias_tram_32,
   prm.val_dias_tram_33,
   prm.val_dias_tram_34,
   prm.val_dias_tram_35,
   prm.val_dias_tram_36,
   prm.val_dias_tram_37,
   prm.val_dias_tram_38,
   prm.val_dias_tram_39,
   prm.val_dias_tram_40,
   prm.val_dias_tram_41,
   prm.ind_regi_frac,
   prm.ind_regi_dire,
   prm.ind_regi_serv
  INTO
   lv_val_dias_tram_1,
   lv_val_dias_tram_2,
   lv_val_dias_tram_3,
   lv_val_dias_tram_4,
   lv_val_dias_tram_5,
   lv_val_dias_tram_6,
   lv_val_dias_tram_7,
   lv_val_dias_tram_8,
   lv_val_dias_tram_9,
   lv_val_dias_tram_15,
   lv_val_dias_tram_16,
   lv_val_dias_tram_17,
   lv_val_dias_tram_18,
   lv_val_dias_tram_19,
   lv_val_dias_tram_20,
   lv_val_dias_tram_22,
   lv_val_dias_tram_23,
   lv_val_dias_tram_24,
   lv_val_dias_tram_25,
   lv_val_dias_tram_26,
   lv_val_dias_tram_27,
   lv_val_dias_tram_28,
   lv_val_dias_tram_29,
   lv_val_dias_tram_30,
   lv_val_dias_tram_32,
   lv_val_dias_tram_33,
   lv_val_dias_tram_34,
   lv_val_dias_tram_35,
   lv_val_dias_tram_36,
   lv_val_dias_tram_37,
   lv_val_dias_tram_38,
   lv_val_dias_tram_39,
   lv_val_dias_tram_40,
   lv_val_dias_tram_41,
   lv_ind_regi_frac,
   lv_ind_regi_dire,
   lv_ind_regi_serv
  FROM  cob_repor_estad_param prm
  WHERE prm.cod_pais = p_cod_pais
    AND prm.cod_soci = p_cod_soci;


      SELECT f.oid_ulti_regi_proc
      INTO lv_oid_ante_regi_proc
      FROM fin_contr_regis_progr f
      WHERE f.cod_modu='COB'
        AND f.cod_prog='21';

      SELECT MAX(mcc.oid_movi_cc)
      INTO lv_oid_ulti_regi_proc
      FROM ccc_movim_cuent_corri mcc
      WHERE mcc.oid_movi_cc > lv_oid_ante_regi_proc ;

      SELECT psp.ind_etap_vent
      INTO lv_ind_etap_vent
      FROM cob_pais_socie_param  psp
      WHERE psp.cod_pais=p_cod_pais
        AND psp.cod_soci=p_cod_soci;

      gv_des_log:='Oid Movi CC Inicio : ' || lv_oid_ante_regi_proc || ' Oid Movi CC Fin : ' || lv_oid_ulti_regi_proc;
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                     gv_cod_proc_ejec, gv_des_log);

      IF lv_oid_ulti_regi_proc IS NOT NULL THEN

         IF  lv_ind_regi_frac = 1 THEN
                cob_pr_regis_cargo_fracc(lv_oid_ante_regi_proc,lv_oid_ulti_regi_proc);
         END IF;

         IF  lv_ind_regi_dire = 1 THEN
                cob_pr_regis_cargo_direc(lv_oid_ante_regi_proc,lv_oid_ulti_regi_proc);
         END IF;

         IF  lv_ind_regi_serv = 1 THEN
                cob_pr_regis_atenc_servi(lv_oid_ante_regi_proc,lv_oid_ulti_regi_proc);
         END IF;

          /*
          gv_des_log:='Inicia carga cronograma';
          fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                     gv_cod_proc_ejec, gv_des_log);

          cob_pr_carga_tempo_fecha_cierr(p_cod_pais, p_cod_soci );

          gv_des_log:='Fin carga cronograma';
          fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                     gv_cod_proc_ejec, gv_des_log);
          */


         SELECT
            p_cod_pais,
            p_cod_soci,
            mcc.perd_oid_peri,
            spc.cod_peri,
            zr.oid_regi,
            zr.cod_regi,
            zr.des_regi,
            zr.clie_oid_clie,
            zz.oid_zona,
            zz.cod_zona,
            zz.des_zona,
            zz.clie_oid_clie,
            NULL, --fecha cierre zona
            zs.oid_secc,
            zs.cod_secc,
            zs.des_secci,
            zs.clie_oid_clie,
            mcc.clie_oid_clie,
            mcc.oid_movi_cc,
            mcc.subp_oid_subp_crea,
            0, -- Indicador cargo fraccionado
            mcc.imp_movi,
            mcc.imp_paga,
            mcc.imp_pend,
            0,            --Importe diferencia
            0,            -- Ultimo numero de historia
            0,            -- Total Facturado
            0,            -- Facturado Neto
            0,            -- Importe Saldo Pendiente SAC
            0,            -- Cobranza 21
                        -- Fecha Cierre 21
            mcc.fec_docu + lv_val_dias_tram_1,
            0,            -- Cobranza 31
                         --Fecha Cierre 31
            mcc.fec_docu + lv_val_dias_tram_2,
            0,            --Cobranza Estimada 31
            0,            -- Porcentaje de Recuperacion
            0,             -- Importe pendiente de Recuperar
            0,             -- Cobranza 42
                         -- Fecha Cierre 42
            mcc.fec_docu + lv_val_dias_tram_3,
            0,             -- Cobranza 63
                          -- Fecha Cierre 63
            mcc.fec_docu + lv_val_dias_tram_4,
            0,             -- Cobranza 84
                         -- Fecha Cierre 84
            mcc.fec_docu + lv_val_dias_tram_5,
            0,             -- Cobranza 126
                          -- Fecha Cierre 126
            mcc.fec_docu + lv_val_dias_tram_6,
            0,             -- Cobranza 189
                        -- Fecha Cierre 126
            mcc.fec_docu + lv_val_dias_tram_7,
            0,              -- Cobranza 999
            0,              -- Importe Cargo Directo
            0,              --Importe Abonos Con
            0,              --Importe Abonos No Monetarios
            0,              --Importe Saldo Pendiente
            0,              --Importe Cargo Fraccionado
            0,              --Importe Abono Fraccionado
            0,              --Importe Facturado No Enviado
            0,              --Importe Devolucion
            USER,
            SYSDATE,
            CASE
               WHEN lv_ind_etap_vent=1 THEN
                  FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_CIERR_VENTA(p_cod_pais,p_cod_soci,spc.cod_peri,zr.cod_regi,zz.cod_zona)
                  --cob_fn_obtie_fecha_cierr_venta(p_cod_pais, p_cod_soci, spc.cod_peri,zr.cod_regi,zz.cod_zona)
               ELSE
                 NULL
             END CASE,
             NULL,
             0,
             0,
             0,
             0,
             0,              -- Cobranza 47,
             mcc.fec_docu + lv_val_dias_tram_8,
             0,              -- Cobranza 53
             mcc.fec_docu + lv_val_dias_tram_9,
             1,               -- ind_deud_pend
             0,
             mcc.fec_docu + lv_val_dias_tram_22,  -- Fecha Cierre 22
             0,
             mcc.fec_docu + lv_val_dias_tram_23,  -- Fecha Cierre 23
             0,
             mcc.fec_docu + lv_val_dias_tram_24,  -- Fecha Cierre 24
             0,
             mcc.fec_docu + lv_val_dias_tram_25,  -- Fecha Cierre 25
             0,
             mcc.fec_docu + lv_val_dias_tram_26,  -- Fecha Cierre 26
             0,
             mcc.fec_docu + lv_val_dias_tram_36,  -- Fecha Cierre 36
             0,
             mcc.fec_docu + lv_val_dias_tram_15,  -- Fecha Cierre 15
             0,
             mcc.fec_docu + lv_val_dias_tram_16,  -- Fecha Cierre 16
             0,
             mcc.fec_docu + lv_val_dias_tram_17,  -- Fecha Cierre 17
             0,
             mcc.fec_docu + lv_val_dias_tram_18,  -- Fecha Cierre 18
             0,
             mcc.fec_docu + lv_val_dias_tram_19,  -- Fecha Cierre 19
             0,
             mcc.fec_docu + lv_val_dias_tram_20,  -- Fecha Cierre 20
             0,
             mcc.fec_docu + lv_val_dias_tram_27,  -- Fecha Cierre 27
             0,
             mcc.fec_docu + lv_val_dias_tram_28,  -- Fecha Cierre 28
             0,
             mcc.fec_docu + lv_val_dias_tram_29,  -- Fecha Cierre 29
             0,
             mcc.fec_docu + lv_val_dias_tram_30,  -- Fecha Cierre 30
             0,
             mcc.fec_docu + lv_val_dias_tram_32,  -- Fecha Cierre 32
             0,
             mcc.fec_docu + lv_val_dias_tram_33,  -- Fecha Cierre 33
             0,
             mcc.fec_docu + lv_val_dias_tram_34,  -- Fecha Cierre 34
             0,
             mcc.fec_docu + lv_val_dias_tram_35,  -- Fecha Cierre 35
             0,
             mcc.fec_docu + lv_val_dias_tram_37,  -- Fecha Cierre 37
             0,
             mcc.fec_docu + lv_val_dias_tram_38,  -- Fecha Cierre 38
             0,
             mcc.fec_docu + lv_val_dias_tram_39,  -- Fecha Cierre 39
             0,
             mcc.fec_docu + lv_val_dias_tram_40,  -- Fecha Cierre 40
             0,
             mcc.fec_docu + lv_val_dias_tram_41  -- Fecha Cierre 41
          BULK COLLECT INTO lv_tab_cbz
          FROM ccc_Movim_Cuent_Corri mcc,
                    cra_perio cp,
                    seg_perio_corpo spc,
                    zon_secci zs,
                    zon_zona zz,
                    Zon_Regio zr
          WHERE mcc.perd_oid_peri = cp.oid_peri
          AND cp.peri_oid_peri = spc.oid_peri
          AND mcc.zscc_oid_secc = zs.oid_secc
          AND zz.oid_zona = zs.zzon_oid_zona
          AND zr.oid_regi = zz.zorg_oid_regi
          AND mcc.imp_movi > 0
          AND mcc.oid_movi_cc > lv_oid_ante_regi_proc
          AND mcc.oid_movi_cc <= lv_oid_ulti_regi_proc
          AND EXISTS (
            SELECT cs.oid_subp
            FROM cob_repor_estad_param_subpr param,
                       ccc_subpr cs,
                       ccc_proce cp
            WHERE param.cod_pais=p_cod_pais
            AND param.cod_soci=p_cod_soci
            AND param.cod_proc=cp.cod_proc
            AND param.cod_subp=cs.cod_subp
            AND cp.oid_proc=cs.ccpr_oid_proc
            AND cs.oid_subp=mcc.subp_oid_subp_crea)
          AND NOT EXISTS (
             SELECT 1
             FROM ccc_movim_cargo_fracc k
             WHERE k.oid_movi_cc=mcc.oid_movi_cc);

         FORALL X IN lv_tab_cbz.FIRST .. lv_tab_cbz.LAST
            INSERT INTO cob_repor_estad_recup_cobra VALUES lv_tab_cbz(x);

         SELECT
            p_cod_pais,
            p_cod_soci,
            k.oid_peri,
            spc.cod_peri,
            zr.oid_regi,
            zr.cod_regi,
            zr.des_regi,
            zr.clie_oid_clie,
            zz.oid_zona,
            zz.cod_zona,
            zz.des_zona,
            zz.clie_oid_clie,
            NULL, --fecha cierre zona
            zs.oid_secc,
            zs.cod_secc,
            zs.des_secci,
            zs.clie_oid_clie,
            mcc.clie_oid_clie,
            mcc.oid_movi_cc,
            mcc.subp_oid_subp_crea,
            k.tip_movi, -- Indicador cargo fraccionado
            mcc.imp_movi,
            mcc.imp_paga,
            mcc.imp_pend,
            0,  --Importe diferencia
            0,
            0,            -- Total Facturado
            0,            -- Facturado Neto
            0,            -- Importe Saldo Pendiente SAC
            0,            -- Cobranza 21
                        -- Fecha Cierre 21
          FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_FACTU(p_cod_pais,zz.oid_zona,k.oid_peri) + lv_val_dias_tram_1,
          0,            -- Cobranza 31
                         --Fecha Cierre 31
          FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_FACTU(p_cod_pais,zz.oid_zona,k.oid_peri) + lv_val_dias_tram_2,
          0,            --Cobranza Estimada 31
          0,            -- Porcentaje de Recuperacion
          0,             -- Importe pendiente de Recuperar
          0,             -- Cobranza 42
                         -- Fecha Cierre 42
          FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_FACTU(p_cod_pais,zz.oid_zona,k.oid_peri) + lv_val_dias_tram_3,
          0,             -- Cobranza 63
                          -- Fecha Cierre 63
          FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_FACTU(p_cod_pais,zz.oid_zona,k.oid_peri) + lv_val_dias_tram_4,
          0,             -- Cobranza 84
                         -- Fecha Cierre 84
          FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_FACTU(p_cod_pais,zz.oid_zona,k.oid_peri) + lv_val_dias_tram_5,
          0,             -- Cobranza 126
                          -- Fecha Cierre 126
          FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_FACTU(p_cod_pais,zz.oid_zona,k.oid_peri) + lv_val_dias_tram_6,
          0,             -- Cobranza 189
                         -- Fecha Cierre 126
          FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_FACTU(p_cod_pais,zz.oid_zona,k.oid_peri) + lv_val_dias_tram_7,
          0,              -- Cobranza 999
          0,              -- Importe Cargo Directo
          0,              --Importe Abonos Con
          0,              --Importe Abonos No Monetarios
          0,              --Importe Saldo Pendiente
          0,              --Importe Cargo Fraccionado
          0,              --Importe Abono Fraccionado
          0,              --Importe Facturado No Enviado
          0,              --Importe Devolucion
          USER,
          SYSDATE,
          CASE
               WHEN lv_ind_etap_vent=1 THEN
                    FIN_PKG_GENER.FIN_FN_OBTIE_FECHA_CIERR_VENTA(p_cod_pais,p_cod_soci,spc.cod_peri,zr.cod_regi,zz.cod_zona)
                    --cob_fn_obtie_fecha_cierr_venta(p_cod_pais, p_cod_soci, spc.cod_peri, zr.cod_regi, zz.cod_zona)
               ELSE
                 NULL
           END CASE,
           NULL,
           0,
           0,
           0,
           0,
           0,              -- Cobranza 47,
           mcc.fec_docu + lv_val_dias_tram_8,
           0,              -- Cobranza 53
           mcc.fec_docu + lv_val_dias_tram_9,
           0,               -- ind_deud_pend
           0,
             mcc.fec_docu + lv_val_dias_tram_22,  -- Fecha Cierre 22
             0,
             mcc.fec_docu + lv_val_dias_tram_23,  -- Fecha Cierre 23
             0,
             mcc.fec_docu + lv_val_dias_tram_24,  -- Fecha Cierre 24
             0,
             mcc.fec_docu + lv_val_dias_tram_25,  -- Fecha Cierre 25
             0,
             mcc.fec_docu + lv_val_dias_tram_26,  -- Fecha Cierre 26
             0,
             mcc.fec_docu + lv_val_dias_tram_36,  -- Fecha Cierre 36
             0,
             mcc.fec_docu + lv_val_dias_tram_15,  -- Fecha Cierre 15
             0,
             mcc.fec_docu + lv_val_dias_tram_16,  -- Fecha Cierre 16
             0,
             mcc.fec_docu + lv_val_dias_tram_17,  -- Fecha Cierre 17
             0,
             mcc.fec_docu + lv_val_dias_tram_18,  -- Fecha Cierre 18
             0,
             mcc.fec_docu + lv_val_dias_tram_19,  -- Fecha Cierre 19
             0,
             mcc.fec_docu + lv_val_dias_tram_20,  -- Fecha Cierre 20
             0,
             mcc.fec_docu + lv_val_dias_tram_27,  -- Fecha Cierre 27
             0,
             mcc.fec_docu + lv_val_dias_tram_28,  -- Fecha Cierre 28
             0,
             mcc.fec_docu + lv_val_dias_tram_29,  -- Fecha Cierre 29
             0,
             mcc.fec_docu + lv_val_dias_tram_30,  -- Fecha Cierre 30
             0,
             mcc.fec_docu + lv_val_dias_tram_32,  -- Fecha Cierre 32
             0,
             mcc.fec_docu + lv_val_dias_tram_33,  -- Fecha Cierre 33
             0,
             mcc.fec_docu + lv_val_dias_tram_34,  -- Fecha Cierre 34
             0,
             mcc.fec_docu + lv_val_dias_tram_35,  -- Fecha Cierre 35
             0,
             mcc.fec_docu + lv_val_dias_tram_37,  -- Fecha Cierre 37
             0,
             mcc.fec_docu + lv_val_dias_tram_38,  -- Fecha Cierre 38
             0,
             mcc.fec_docu + lv_val_dias_tram_39,  -- Fecha Cierre 39
             0,
             mcc.fec_docu + lv_val_dias_tram_40,  -- Fecha Cierre 40
             0,
             mcc.fec_docu + lv_val_dias_tram_41  -- Fecha Cierre 41
         BULK COLLECT INTO lv_tab_cbz_frac
         FROM
            ccc_Movim_Cuent_Corri mcc,
            ccc_movim_cargo_fracc k,
            cra_perio cp,
            seg_perio_corpo spc,
            zon_secci zs,
            zon_zona zz,
            Zon_Regio zr
         WHERE k.oid_peri = cp.oid_peri
           AND cp.peri_oid_peri = spc.oid_peri
           AND mcc.zscc_oid_secc = zs.oid_secc
           AND zz.oid_zona = zs.zzon_oid_zona
           AND zr.oid_regi = zz.zorg_oid_regi
           AND k.oid_movi_cc = mcc.oid_movi_cc
           AND mcc.imp_movi>0
           AND mcc.oid_movi_cc>lv_oid_ante_regi_proc
           AND mcc.oid_movi_cc<=lv_oid_ulti_regi_proc;


         FORALL X IN lv_tab_cbz_frac.FIRST .. lv_tab_cbz_frac.LAST
            INSERT INTO cob_repor_estad_recup_cobra VALUES lv_tab_cbz_frac(x);

         SELECT cs.oid_subp
         INTO lv_oid_subp_fact
         FROM ccc_proce cp,
                   ccc_subpr cs
         WHERE cp.oid_proc=cs.ccpr_oid_proc
         AND cp.cod_proc='CCC001'
         AND cs.cod_subp=1;

         -- O/C
         UPDATE cob_repor_estad_recup_cobra z
         SET
            z.imp_fact=z.imp_movi,
            z.imp_fact_neto=z.imp_movi
         WHERE z.oid_subp_crea=lv_oid_subp_fact
           AND z.ind_cfra=0
           AND z.oid_movi_cc > lv_oid_ante_regi_proc;

         -- Atenciones de Servicio
         UPDATE cob_repor_estad_recup_cobra z
         SET
            z.imp_fact=z.imp_movi,
            z.imp_fact_neto=z.imp_movi
         WHERE z.oid_subp_crea=lv_oid_subp_fact
           AND z.ind_cfra=3
           AND z.oid_movi_cc > lv_oid_ante_regi_proc;

         -- Kit de Nuevas
         UPDATE cob_repor_estad_recup_cobra z
         SET
            z.imp_fact=z.imp_movi,
            z.imp_fact_neto=z.imp_movi
         WHERE z.oid_subp_crea=lv_oid_subp_fact
         AND z.ind_cfra=9
         AND z.oid_movi_cc > lv_oid_ante_regi_proc;

         -- Cargos Directos --
         UPDATE cob_repor_estad_recup_cobra z
         SET
            z.imp_carg_dire=z.imp_movi
         WHERE EXISTS (SELECT NULL
                      FROM   ccc_proce cp,
                             ccc_subpr cs
                      WHERE  cp.oid_proc = cs.ccpr_oid_proc
                      AND    cp.ind_cv_dire = 1
                      AND    cs.val_indi_cons = 'D'
                      AND    cs.oid_subp = z.oid_subp_crea )
           AND z.oid_movi_cc>=lv_oid_ante_regi_proc;


         -- Cargos Directos x Operacion Bancaria Errada--
         UPDATE cob_repor_estad_recup_cobra z
         SET z.imp_carg_erro_banc = z.imp_movi
         WHERE EXISTS (SELECT NULL
                      FROM   ccc_proce cp,
                             ccc_subpr cs
                      WHERE  cp.oid_proc = cs.ccpr_oid_proc
                      AND cp.cod_proc = gc_cod_proc_carg_dire
                      AND cs.cod_subp = gc_cod_subp_carg_erro_banc )
         AND z.oid_movi_cc > lv_oid_ante_regi_proc;


         UPDATE cob_repor_estad_recup_cobra z
         SET
            z.imp_fact_neto=z.imp_fact + z.imp_carg_dire + z.imp_carg_frac - z.imp_abon_nmon - z.imp_abon_frac,
            z.imp_sald_pend_sac=z.imp_fact + z.imp_carg_frac + z.imp_carg_dire - z.cob_dias_999 - z.imp_abon_frac - z.imp_abon_nmon,
            z.imp_dife = ABS(z.imp_pend -( z.imp_fact + z.imp_carg_frac + z.imp_carg_dire - z.cob_dias_999 -  z.imp_abon_frac - z.imp_abon_nmon))
         WHERE z.oid_movi_cc > lv_oid_ante_regi_proc;

         UPDATE fin_contr_regis_progr f
         SET
            f.oid_ante_regi_proc = f.oid_ulti_regi_proc,
            f.oid_ulti_regi_proc   = lv_oid_ulti_regi_proc
         WHERE f.cod_modu='COB'
           AND f.cod_prog='21';

       END IF;


      gv_des_log:= 'Fin COB_PR_CARGA_ESTAD_RECUP_COBRA';
      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);

      FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, 2);


   EXCEPTION
      WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := substr(SQLERRM, 1, 250);

           gv_des_log:='Fin del proceso de manera erronea :' ||ln_sqlcode || ' '|| ls_sqlerrm;

           FIN_PKG_GENER.FIN_PR_ACTUA_LOG_PROCE_EJEC(gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, gv_des_log);
           FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, 9);
           RAISE_application_error(-20123,
                              'ERROR COB_PR_CARGA_ESTAD_RECUP_COBRA: ' ||
                               ls_sqlerrm);

    END COB_PR_CARGA_ESTAD_RECUP_COBRA;


   PROCEDURE cob_pr_regis_cargo_fracc(
      p_oid_ante_regi_proc fin_contr_regis_progr.oid_ante_regi_proc%TYPE,
      p_oid_ulti_regi_proc   fin_contr_regis_progr.oid_ulti_regi_proc%TYPE)
   IS

      TYPE t_tab_cfrac IS TABLE OF ccc_movim_Cargo_fracc%ROWTYPE;
       v_tab_cfrac                   t_tab_cfrac;

   BEGIN

      SELECT mcc.perd_oid_peri+1,
                     mcc.oid_movi_cc,
                     9
       BULK COLLECT INTO v_tab_cfrac
       FROM ccc_Movim_Cuent_Corri mcc,
                  cra_perio cp,
                  zon_secci zs,
                  zon_zona zz,
                  cra_crono cr,
                  cra_activ ca
       WHERE 1=1
       AND mcc.perd_oid_peri=cp.oid_peri
       AND mcc.zscc_oid_secc=zs.oid_secc
       AND zz.oid_zona=zs.zzon_oid_zona
       AND cr.zzon_oid_zona=zz.oid_zona
       AND cr.perd_oid_peri=mcc.perd_oid_peri+1
       AND cr.cact_oid_acti=ca.oid_acti
       AND ca.cod_acti='FA'
       AND mcc.fec_venc>cr.fec_inic
       AND mcc.imp_movi>0
       AND mcc.oid_movi_cc >= p_oid_ante_regi_proc
       AND mcc.oid_movi_cc <= p_oid_ulti_regi_proc;

      FORALL x IN v_tab_cfrac.FIRST .. v_tab_cfrac.LAST
         INSERT INTO ccc_movim_cargo_fracc VALUES v_tab_cfrac(x);

   EXCEPTION
      WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := substr(SQLERRM, 1, 250);

           RAISE_application_error(-20123,
                              'ERROR COB_PR_REGIS_CARGO_FRACC: ' ||
                               ls_sqlerrm);

   END cob_pr_regis_cargo_fracc;

   PROCEDURE cob_pr_regis_cargo_direc(
      p_oid_ante_regi_proc   fin_contr_regis_progr.oid_ante_regi_proc%TYPE,
      p_oid_ulti_regi_proc     fin_contr_regis_progr.oid_ulti_regi_proc%TYPE)
   IS
      CURSOR c_carg_dire(
         p_oid_ante_regi_proc   fin_contr_regis_progr.oid_ante_regi_proc%TYPE,
         p_oid_ulti_regi_proc     fin_contr_regis_progr.oid_ulti_regi_proc%TYPE)
      IS
      SELECT
                   mcc.oid_movi_cc,
                   mcc.perd_oid_peri,
                   mcc.clie_oid_clie,
                   mcc.fec_docu
      FROM ccc_movim_cuent_corri mcc
      WHERE mcc.oid_movi_cc>p_oid_ante_regi_proc
      AND mcc.oid_movi_cc<=p_oid_ulti_regi_proc
      AND EXISTS (SELECT NULL
                               FROM   ccc_proce cp,
                                             ccc_subpr cs
                               WHERE  cp.oid_proc = cs.ccpr_oid_proc
                               AND cp.ind_cv_dire = 1
                               AND cs.val_indi_cons = 'D'
                               AND cs.oid_subp = mcc.subp_oid_subp_crea )
      AND NOT EXISTS (
        SELECT 1
        FROM ccc_movim_cargo_fracc cf
        WHERE cf.oid_movi_cc=mcc.oid_movi_cc);

      v_oid_peri cra_perio.oid_peri%TYPE;
      vreg_ccc_movim_cfrac ccc_movim_cargo_fracc%ROWTYPE;

   BEGIN

      FOR v_Carg_dire IN c_Carg_dire(p_oid_ante_regi_proc,p_oid_ulti_regi_proc) LOOP

         v_oid_peri:=FIN_PKG_GENER.FIN_FN_OBTIE_OID_ULTIM_PEDID(v_Carg_dire.clie_oid_clie,v_carg_dire.perd_oid_peri);

         vreg_ccc_movim_cfrac.oid_peri:=v_oid_peri;
         vreg_ccc_movim_cfrac.oid_movi_cc:=v_Carg_dire.oid_movi_cc;
         vreg_ccc_movim_cfrac.tip_movi:=2;

          INSERT INTO ccc_movim_cargo_fracc VALUES vreg_ccc_movim_cfrac;

      END LOOP;

   EXCEPTION

      WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := substr(SQLERRM, 1, 250);

           RAISE_application_error(-20123,
                              'ERROR COB_PR_REGIS_CARGO_DIREC: ' ||
                               ls_sqlerrm);

   END cob_pr_regis_cargo_direc;

   PROCEDURE cob_pr_regis_atenc_servi (
      p_oid_ante_regi_proc   fin_contr_regis_progr.oid_ante_regi_proc%TYPE,
      p_oid_ulti_regi_proc     fin_contr_regis_progr.oid_ulti_regi_proc%TYPE)
   IS
      CURSOR c_aten_serv(
         p_oid_ante_regi_proc   fin_contr_regis_progr.oid_ante_regi_proc%TYPE,
         p_oid_ulti_regi_proc     fin_contr_regis_progr.oid_ulti_regi_proc%TYPE,
         p_oid_tipo_soli_pais             ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE)
      IS
         SELECT
                   mcc.oid_movi_cc,
                   mcc.perd_oid_peri,
                   mcc.clie_oid_clie,
                   mcc.fec_docu,
                   psc.soca_oid_docu_refe
         FROM ccc_movim_cuent_corri mcc,
                     ped_solic_Cabec psc
         WHERE psc.oid_soli_cabe=mcc.soca_oid_soli_cabe
         AND mcc.subp_oid_subp_crea=2001
         AND mcc.tspa_oid_tipo_soli_pais=p_oid_tipo_soli_pais
        AND mcc.imp_movi>0
        AND mcc.oid_movi_cc>p_oid_ante_regi_proc
        AND mcc.oid_movi_cc<=p_oid_ulti_regi_proc
        AND 0 =
            (SELECT COUNT(*)
             FROM ped_solic_cabec psc2
             WHERE psc2.soca_oid_soli_cabe=psc.oid_soli_cabe
             AND psc2.modu_oid_modu<>15)
               AND NOT EXISTS (
        SELECT 1
        FROM ccc_movim_cargo_fracc cf
        WHERE cf.oid_movi_cc=mcc.oid_movi_cc);

      v_oid_tipo_soli_pais  ped_tipo_solic_pais.oid_tipo_soli_pais%TYPE;
      v_oid_peri cra_perio.oid_peri%TYPE;
      vreg_ccc_movim_cfrac ccc_movim_cargo_fracc%ROWTYPE;

   BEGIN

      v_oid_tipo_soli_pais:= FIN_PKG_GENER.FIN_FN_OBTIE_OID_SOLIC_PAIS('C1');

      FOR v_aten_serv IN c_aten_serv(p_oid_ante_regi_proc,p_oid_ulti_regi_proc,v_oid_tipo_soli_pais) LOOP

         SELECT NVL(MAX(psc.perd_oid_peri), v_aten_serv.perd_oid_peri)
         INTO v_oid_peri
         FROM Ped_Solic_Cabec psc
         WHERE psc.clie_oid_clie= v_aten_serv.clie_oid_clie
         AND psc.perd_oid_peri<=v_aten_serv.perd_oid_peri
         AND psc.tspa_oid_tipo_soli_pais=v_oid_tipo_soli_pais
         AND psc.ind_ts_no_conso=0
         AND psc.soca_oid_soli_cabe IS NULL
         AND psc.fec_fact IS NOT NULL
         AND psc.oid_soli_cabe=v_aten_serv.soca_oid_docu_refe;

         vreg_ccc_movim_cfrac.oid_peri:=v_oid_peri;
         vreg_ccc_movim_cfrac.oid_movi_cc:=v_aten_serv.oid_movi_cc;
         vreg_ccc_movim_cfrac.tip_movi:=3;

          INSERT INTO ccc_movim_cargo_fracc VALUES vreg_ccc_movim_cfrac;

      END LOOP;

    EXCEPTION

      WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := substr(SQLERRM, 1, 250);

           RAISE_application_error(-20123,
                              'ERROR COB_PR_REGIS_ATENC_SERVI: ' ||
                               ls_sqlerrm);

   END cob_pr_regis_atenc_servi ;

 PROCEDURE COB_PR_ACTUA_ESTAD_RECUP_COBRA(
  p_cod_peri                     IN   seg_perio_corpo.cod_peri%TYPE)
 IS

  TYPE t_tab_oid_movi_cc           IS TABLE OF ccc_movim_cuent_corri.oid_movi_cc%TYPE;
  TYPE t_tab_imp_paga              IS TABLE OF ccc_movim_cuent_corri.imp_paga%TYPE;
  TYPE t_tab_imp_pend              IS TABLE OF ccc_movim_cuent_corri.imp_pend%TYPE;
  TYPE t_tab_ulti_nume_hist        IS TABLE OF ccc_movim_cuent_corri.val_ulti_nume_hist%TYPE;

  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_cod_soci                      seg_socie.cod_soci%TYPE;
  lv_tab_oid_movi_cc               t_tab_oid_movi_cc;
  lv_tab_imp_paga                  t_tab_imp_paga;
  lv_tab_imp_pend                  t_tab_imp_pend;
  lv_tab_ulti_nume_hist            t_tab_ulti_nume_hist;
  lv_ind_etap_vent                 cob_pais_socie_param.ind_etap_vent%TYPE;

 BEGIN

  lv_cod_pais := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_cod_soci := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoSociedad');

  gv_log_cod_pais := lv_cod_pais;
  gv_log_cod_soci := lv_cod_soci;
  gv_log_user := USER;
  gv_log_cod_modu := 'COB';
  gv_log_cod_proc := '22';

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE_EJEC(lv_cod_pais, lv_cod_soci, gv_log_cod_modu, gv_log_cod_proc , gv_log_user, gv_cod_proc_ejec);

  gv_des_log:='Inicio COB_PR_ACTUA_ESTAD_RECUP_COBRA  Campaña: ' || p_cod_peri;
  fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( lv_cod_pais, lv_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                     gv_cod_proc_ejec, gv_des_log);

  SELECT psp.ind_etap_vent
  INTO lv_ind_etap_vent
  FROM cob_pais_socie_param  psp
  WHERE psp.cod_pais = lv_cod_pais
    AND psp.cod_soci = lv_cod_soci;

  SELECT
   cob.oid_movi_cc,
   mcc.imp_paga,
   mcc.imp_pend,
   mcc.val_ulti_nume_hist
  BULK COLLECT INTO
   lv_tab_oid_movi_cc,
   lv_tab_imp_paga,
   lv_tab_imp_pend,
   lv_tab_ulti_nume_hist
  FROM
   cob_repor_estad_recup_cobra cob,
   ccc_movim_cuent_corri mcc
  WHERE cob.oid_movi_cc = mcc.oid_movi_cc
    AND cob.ulti_nume_hist < mcc.val_ulti_nume_hist
    AND cob.cod_pais = lv_cod_pais
    AND cob.cod_soci = lv_cod_soci
    AND cob.cod_peri = p_cod_peri;

  FORALL x IN lv_tab_oid_movi_cc.FIRST .. lv_tab_oid_movi_cc.LAST
   UPDATE cob_repor_estad_recup_cobra z
   SET
    z.imp_paga = lv_tab_imp_paga(x),
    z.imp_pend = lv_tab_imp_pend(x),
    z.ulti_nume_hist = lv_tab_ulti_nume_hist(x),
    z.cob_dias_21   = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_21),
    --z.cob_dias_22 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_22),
    --z.cob_dias_23 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_23),
    --z.cob_dias_24 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_24),
    --z.cob_dias_25 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_25),
    --z.cob_dias_26 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_26),
    z.cob_dias_31 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_31),
    z.cob_dias_36 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_36),
    z.cob_dias_42 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_42),
    z.cob_dias_47 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_47),
    z.cob_dias_53 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_53),
    z.cob_dias_63 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_63),
    z.cob_dias_84 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_84),
    z.cob_dias_126 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_126),
    z.cob_dias_189 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_189),
    z.cob_dias_999 = COB_FN_OBTIE_COBRA_BANCO(lv_cod_pais, z.oid_movi_cc),
    z.imp_abon_nmon = COB_FN_OBTIE_COBRA_ABONO_NOMON(lv_cod_pais, z.oid_movi_cc),
    z.imp_abon_frac = 0,
    z.fec_modi = SYSDATE,
    z.cob_dias_vent =
                     CASE
                      WHEN lv_ind_etap_vent=1 THEN
                       COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_vent)
                     ELSE
                      0
                     END
   WHERE z.oid_movi_cc = lv_tab_oid_movi_cc(x);

  FORALL x IN lv_tab_oid_movi_cc.FIRST .. lv_tab_oid_movi_cc.LAST
   UPDATE cob_repor_estad_recup_cobra z
   SET
    z.imp_fact_neto = z.imp_fact + z.imp_carg_dire + z.imp_carg_frac - z.imp_abon_nmon - z.imp_abon_frac,
    z.imp_sald_pend_sac = z.imp_fact + z.imp_carg_frac + z.imp_carg_dire - z.cob_dias_999 - z.imp_abon_frac - z.imp_abon_nmon ,
    z.imp_dife = ABS(z.imp_pend -( z.imp_fact + z.imp_carg_frac + z.imp_carg_dire - z.cob_dias_999 -  z.imp_abon_frac - z.imp_abon_nmon))
   WHERE z.oid_movi_cc = lv_tab_oid_movi_cc(x);

  gv_des_log:= 'Fin COB_PR_ACTUA_ESTAD_RECUP_COBRA';
  fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( lv_cod_pais, lv_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(lv_cod_pais, lv_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, 2);

 EXCEPTION
      WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := substr(SQLERRM, 1, 250);

           RAISE_application_error(-20123,
                              'ERROR COB_PR_CARGA_ESTAD_RECUP_COBRA: ' ||
                               ls_sqlerrm);

 END COB_PR_ACTUA_ESTAD_RECUP_COBRA;

 PROCEDURE COB_PR_ACTUA_ESTAD_RECUP_COBRA(
  p_cod_pais                     seg_pais.cod_pais%TYPE,
  p_cod_soci                     seg_socie.cod_soci%TYPE)
 IS

  TYPE t_tab_oid_movi_cc         IS TABLE OF ccc_movim_cuent_corri.oid_movi_cc%TYPE;
  TYPE t_tab_imp_paga            IS TABLE OF ccc_movim_cuent_corri.imp_paga%TYPE;
  TYPE t_tab_imp_pend            IS TABLE OF ccc_movim_cuent_corri.imp_pend%TYPE;
  TYPE t_tab_ulti_nume_hist      IS TABLE OF ccc_movim_cuent_corri.val_ulti_nume_hist%TYPE;

  lv_tab_oid_movi_cc             t_tab_oid_movi_cc;
  lv_tab_imp_paga                t_tab_imp_paga;
  lv_tab_imp_pend                t_tab_imp_pend;
  lv_tab_ulti_nume_hist          t_tab_ulti_nume_hist;
  lv_ind_etap_vent               cob_pais_socie_param.ind_etap_vent%TYPE;

 BEGIN

  gv_log_cod_pais := p_cod_pais;
  gv_log_cod_soci := p_cod_soci;
  gv_log_user := USER;
  gv_log_cod_modu := 'COB';
  gv_log_cod_proc := '22';

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE_EJEC(p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc , gv_log_user, gv_cod_proc_ejec);

  gv_des_log:='Inicio COB_PR_ACTUA_ESTAD_RECUP_COBRA  Pais: ' || p_cod_pais ;
  fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                     gv_cod_proc_ejec, gv_des_log);

  SELECT psp.ind_etap_vent
  INTO lv_ind_etap_vent
  FROM cob_pais_socie_param  psp
  WHERE psp.cod_pais=p_cod_pais
    AND psp.cod_soci=p_cod_soci;

  SELECT
   cob.oid_movi_cc,
   mcc.imp_paga,
   mcc.imp_pend,
   mcc.val_ulti_nume_hist
  BULK COLLECT INTO
   lv_tab_oid_movi_cc,
   lv_tab_imp_paga,
   lv_tab_imp_pend,
   lv_tab_ulti_nume_hist
  FROM
   cob_repor_estad_recup_cobra cob,
   ccc_movim_cuent_corri  mcc
  WHERE cob.oid_movi_cc = mcc.oid_movi_cc
    AND cob.ulti_nume_hist < mcc.val_ulti_nume_hist
    AND cob.cod_pais = p_cod_pais
    AND cob.cod_soci = p_cod_soci;

  FORALL x IN lv_tab_oid_movi_cc.FIRST .. lv_tab_oid_movi_cc.LAST
   UPDATE cob_repor_estad_recup_cobra z
   SET
    z.imp_paga=lv_tab_imp_paga(x),
    z.imp_pend=lv_tab_imp_pend(x),
    z.ulti_nume_hist=lv_tab_ulti_nume_hist(x),
    z.cob_dias_15 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_15),
    z.cob_dias_16 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_16),
    z.cob_dias_17 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_17),
    z.cob_dias_18 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_18),
    z.cob_dias_19 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_19),
    z.cob_dias_20 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_20),
    z.cob_dias_21 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_21),
    z.cob_dias_22 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_22),
    z.cob_dias_23 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_23),
    z.cob_dias_24 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_24),
    z.cob_dias_25 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_25),
    z.cob_dias_26 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_26),
    z.cob_dias_27 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_27),
    z.cob_dias_28 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_28),
    z.cob_dias_29 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_29),
    z.cob_dias_30 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_30),
    z.cob_dias_31 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_31),
    z.cob_dias_32 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_32),
    z.cob_dias_33 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_33),
    z.cob_dias_34 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_34),
    z.cob_dias_35 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_35),
    z.cob_dias_36 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_36),
    z.cob_dias_37 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_37),
    z.cob_dias_38 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_38),
    z.cob_dias_39 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_39),
    z.cob_dias_40 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_40),
    z.cob_dias_41 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_41),
    z.cob_dias_42 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_42),
    z.cob_dias_47 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_47),
    z.cob_dias_53 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_53),
    z.cob_dias_63 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_63),
    z.cob_dias_84 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_84),
    z.cob_dias_126 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_126),
    z.cob_dias_189 = COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_189),
    z.cob_dias_999 = COB_FN_OBTIE_COBRA_BANCO(p_cod_pais, z.oid_movi_cc),
    z.imp_abon_nmon = COB_FN_OBTIE_COBRA_ABONO_NOMON(p_cod_pais, z.oid_movi_cc),
    z.imp_abon_frac = 0,
    z.fec_modi = SYSDATE,
    z.cob_dias_vent =
                     CASE
                      WHEN lv_ind_etap_vent=1 THEN
                       COB_FN_OBTIE_COBRA_BANCO_FECHA(p_cod_pais, z.oid_movi_cc,z.fec_cier_vent)
                     ELSE
                      0
                     END
   WHERE z.oid_movi_cc = lv_tab_oid_movi_cc(x);

  FORALL x IN lv_tab_oid_movi_cc.FIRST .. lv_tab_oid_movi_cc.LAST
   UPDATE cob_repor_estad_recup_cobra z
   SET
    z.imp_fact_neto = z.imp_fact + z.imp_carg_dire + z.imp_carg_frac - z.imp_abon_nmon - z.imp_abon_frac,
    z.imp_sald_pend_sac = z.imp_fact + z.imp_carg_frac + z.imp_carg_dire - z.cob_dias_999 - z.imp_abon_frac - z.imp_abon_nmon ,
    z.imp_dife = ABS(z.imp_pend -( z.imp_fact + z.imp_carg_frac + z.imp_carg_dire - z.cob_dias_999 -  z.imp_abon_frac - z.imp_abon_nmon))
   WHERE z.oid_movi_cc = lv_tab_oid_movi_cc(x);

  gv_des_log:= 'Fin COB_PR_ACTUA_ESTAD_RECUP_COBRA';
  fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, 2);

 EXCEPTION
      WHEN OTHERS THEN
           ln_sqlcode := SQLCODE;
           ls_sqlerrm := substr(SQLERRM, 1, 250);

           RAISE_application_error(-20123,
                              'ERROR COB_PR_CARGA_ESTAD_RECUP_COBRA: ' ||
                               ls_sqlerrm);

 END COB_PR_ACTUA_ESTAD_RECUP_COBRA;

 PROCEDURE COB_PR_ACTUA_ESTAD_RECUP_COBRA
 IS

  TYPE t_tab_oid_movi_cc           IS TABLE OF ccc_movim_cuent_corri.oid_movi_cc%TYPE;
  TYPE t_tab_imp_paga              IS TABLE OF ccc_movim_cuent_corri.imp_paga%TYPE;
  TYPE t_tab_imp_pend              IS TABLE OF ccc_movim_cuent_corri.imp_pend%TYPE;
  TYPE t_tab_ulti_nume_hist        IS TABLE OF ccc_movim_cuent_corri.val_ulti_nume_hist%TYPE;

  lv_tab_oid_movi_cc               t_tab_oid_movi_cc;
  lv_tab_imp_paga                  t_tab_imp_paga;
  lv_tab_imp_pend                  t_tab_imp_pend;
  lv_tab_ulti_nume_hist            t_tab_ulti_nume_hist;
  lv_ind_etap_vent                 cob_pais_socie_param.ind_etap_vent%TYPE;
  lv_cod_peri_rota                 seg_perio_corpo.cod_peri%TYPE;
  lv_cod_pais                      seg_pais.cod_pais%TYPE;
  lv_cod_soci                      seg_socie.cod_soci%TYPE;

 BEGIN

  lv_cod_pais := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoPais');
  lv_cod_soci := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoSociedad');

  gv_log_cod_pais := lv_cod_pais;
  gv_log_cod_soci := lv_cod_soci;
  gv_log_user := USER;
  gv_log_cod_modu := 'COB';
  gv_log_cod_proc := '22';

  FIN_PKG_GENER.FIN_PR_REGIS_PROCE_EJEC(lv_cod_pais, lv_cod_soci, gv_log_cod_modu, gv_log_cod_proc , gv_log_user, gv_cod_proc_ejec);

  gv_des_log:='Inicio COB_PR_ACTUA_ESTAD_RECUP_COBRA  Pais: ' || lv_cod_pais ;
  fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( lv_cod_pais, lv_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                     gv_cod_proc_ejec, gv_des_log);

  SELECT psp.ind_etap_vent
  INTO lv_ind_etap_vent
  FROM cob_pais_socie_param  psp
  WHERE psp.cod_pais = lv_cod_pais
    AND psp.cod_soci = lv_cod_soci;

  lv_cod_peri_rota := COB_PKG_GENER.COB_FN_OBTIE_PARAM_GENER('CodigoPeriodoRotacion');

  SELECT
   cob.oid_movi_cc,
   mcc.imp_paga,
   mcc.imp_pend,
   mcc.val_ulti_nume_hist
  BULK COLLECT INTO
   lv_tab_oid_movi_cc,
   lv_tab_imp_paga,
   lv_tab_imp_pend,
   lv_tab_ulti_nume_hist
  FROM
   cob_repor_estad_recup_cobra cob,
   ccc_movim_cuent_corri  mcc
  WHERE cob.oid_movi_cc = mcc.oid_movi_cc
    AND cob.ulti_nume_hist < mcc.val_ulti_nume_hist
    AND cob.cod_pais = lv_cod_pais
    AND cob.cod_soci = lv_cod_soci
    AND cob.ind_deud_pend = 1;

  IF lv_cod_peri_rota IS NULL THEN

   FORALL x IN lv_tab_oid_movi_cc.FIRST .. lv_tab_oid_movi_cc.LAST

    UPDATE cob_repor_estad_recup_cobra z
    SET
     z.imp_paga=lv_tab_imp_paga(x),
     z.imp_pend=lv_tab_imp_pend(x),
     z.ulti_nume_hist = lv_tab_ulti_nume_hist(x),
     z.cob_dias_15 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_15),
     z.cob_dias_16 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_16),
     z.cob_dias_17 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_17),
     z.cob_dias_18 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_18),
     z.cob_dias_19 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_19),
     z.cob_dias_20 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_20),
     z.cob_dias_21 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_21),
     z.cob_dias_22 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_22),
     z.cob_dias_23 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_23),
     z.cob_dias_24 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_24),
     z.cob_dias_25 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_25),
     z.cob_dias_26 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_26),
     z.cob_dias_27 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_27),
     z.cob_dias_28 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_28),
     z.cob_dias_29 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_29),
     z.cob_dias_30 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_30),
     z.cob_dias_31 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_31),
     z.cob_dias_32 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_32),
     z.cob_dias_33 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_33),
     z.cob_dias_34 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_34),
     z.cob_dias_35 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_35),
     z.cob_dias_36 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_36),
     z.cob_dias_37 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_37),
     z.cob_dias_38 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_38),
     z.cob_dias_39 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_39),
     z.cob_dias_40 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_40),
     z.cob_dias_41 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_41),
     z.cob_dias_42 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_42),
     z.cob_dias_47 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_47),
     z.cob_dias_53 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_53),
     z.cob_dias_63 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_63),
     z.cob_dias_84 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_84),
     z.cob_dias_126 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_126),
     z.cob_dias_189 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_189),
     z.cob_dias_999 = COB_FN_OBTIE_COBRA_BANCO(lv_cod_pais, z.oid_movi_cc),
     z.imp_abon_nmon = COB_FN_OBTIE_COBRA_ABONO_NOMON(lv_cod_pais, z.oid_movi_cc),
     z.imp_abon_frac = 0,
     z.fec_modi = SYSDATE,
     z.cob_dias_vent =
     CASE
      WHEN lv_ind_etap_vent=1 THEN
          COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_vent)
      ELSE
           0
      END
     WHERE z.oid_movi_cc = lv_tab_oid_movi_cc(x);

    ELSE

     FORALL x IN lv_tab_oid_movi_cc.FIRST .. lv_tab_oid_movi_cc.LAST

      UPDATE cob_repor_estad_recup_cobra z
      SET
       z.imp_paga=lv_tab_imp_paga(x),
       z.imp_pend=lv_tab_imp_pend(x),
       z.ulti_nume_hist=lv_tab_ulti_nume_hist(x),
       z.cob_dias_21   = COB_FN_OBTIE_COBRA_BANCO_SABON(z.oid_movi_cc,z.fec_cier_21),
       z.cob_dias_31   = COB_FN_OBTIE_COBRA_BANCO_SABON(z.oid_movi_cc,z.fec_cier_31),
       z.cob_dias_42   = COB_FN_OBTIE_COBRA_BANCO_SABON(z.oid_movi_cc,z.fec_cier_42),
       z.cob_dias_63   = COB_FN_OBTIE_COBRA_BANCO_SABON(z.oid_movi_cc,z.fec_cier_63),
       z.cob_dias_84   = COB_FN_OBTIE_COBRA_BANCO_SABON(z.oid_movi_cc,z.fec_cier_84),
       z.cob_dias_126  = COB_FN_OBTIE_COBRA_BANCO_SABON(z.oid_movi_cc,z.fec_cier_126),
       z.cob_dias_189  = COB_FN_OBTIE_COBRA_BANCO_SABON(z.oid_movi_cc,z.fec_cier_189),
       z.cob_dias_999  = COB_FN_OBTIE_COBRA_BANCO_SABON(z.oid_movi_cc),
       z.imp_abon_nmon = 0,
       z.imp_abon_frac=0,
       z.fec_modi= SYSDATE,
       z.cob_dias_vent =
       CASE
        WHEN lv_ind_etap_vent=1 THEN
          COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_vent)
        ELSE
           0
       END
      WHERE z.oid_movi_cc = lv_tab_oid_movi_cc(x)
        AND z.cod_peri < lv_cod_peri_rota;

      FORALL x IN lv_tab_oid_movi_cc.FIRST .. lv_tab_oid_movi_cc.LAST

     UPDATE cob_repor_estad_recup_cobra z
     SET
      z.imp_paga=lv_tab_imp_paga(x),
      z.imp_pend=lv_tab_imp_pend(x),
      z.ulti_nume_hist=lv_tab_ulti_nume_hist(x),
      z.cob_dias_15 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_15),
      z.cob_dias_16 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_16),
      z.cob_dias_17 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_17),
      z.cob_dias_18 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_18),
      z.cob_dias_19 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_19),
      z.cob_dias_20 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_20),
      z.cob_dias_21 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_21),
      z.cob_dias_22 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_22),
      z.cob_dias_23 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_23),
      z.cob_dias_24 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_24),
      z.cob_dias_25 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_25),
      z.cob_dias_26 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_26),
      z.cob_dias_27 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_27),
      z.cob_dias_28 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_28),
      z.cob_dias_29 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_29),
      z.cob_dias_30 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_30),
      z.cob_dias_31 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_31),
      z.cob_dias_32 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_32),
      z.cob_dias_33 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_33),
      z.cob_dias_34 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_34),
      z.cob_dias_35 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_35),
      z.cob_dias_36 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_36),
      z.cob_dias_37 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_37),
      z.cob_dias_38 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_38),
      z.cob_dias_39 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_39),
      z.cob_dias_40 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_40),
      z.cob_dias_41 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_41),
      z.cob_dias_42 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_42),
      z.cob_dias_47 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_47),
      z.cob_dias_53 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_53),
      z.cob_dias_63 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_63),
      z.cob_dias_84 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_84),
      z.cob_dias_126 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_126),
      z.cob_dias_189 = COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_189),
      z.cob_dias_999 = COB_FN_OBTIE_COBRA_BANCO(lv_cod_pais, z.oid_movi_cc),
      z.imp_abon_nmon = COB_FN_OBTIE_COBRA_ABONO_NOMON(lv_cod_pais, z.oid_movi_cc),
      z.imp_abon_frac=0,
      z.fec_modi= SYSDATE,
      z.cob_dias_vent =
      CASE
       WHEN lv_ind_etap_vent=1 THEN
          COB_FN_OBTIE_COBRA_BANCO_FECHA(lv_cod_pais, z.oid_movi_cc,z.fec_cier_vent)
       ELSE
           0
       END
      WHERE z.oid_movi_cc = lv_tab_oid_movi_cc(x)
        AND z.cod_peri >= lv_cod_peri_rota;

   END IF;

   FORALL x IN lv_tab_oid_movi_cc.FIRST .. lv_tab_oid_movi_cc.LAST

    UPDATE cob_repor_estad_recup_cobra z
    SET
     z.imp_fact_neto = z.imp_fact + z.imp_carg_dire + z.imp_carg_frac - z.imp_abon_nmon - z.imp_abon_frac,
     z.imp_sald_pend_sac = z.imp_fact + z.imp_carg_frac + z.imp_carg_dire - z.cob_dias_999 - z.imp_abon_frac - z.imp_abon_nmon ,
     z.imp_dife = ABS(z.imp_pend -( z.imp_fact + z.imp_carg_frac + z.imp_carg_dire - z.cob_dias_999 -  z.imp_abon_frac - z.imp_abon_nmon)),
     z.ind_deud_pend =
     CASE
      WHEN (z.imp_fact + z.imp_carg_frac + z.imp_carg_dire - z.cob_dias_999 - z.imp_abon_frac - z.imp_abon_nmon) = 0 THEN
        0
      ELSE
        1
     END
    WHERE z.oid_movi_cc = lv_tab_oid_movi_cc(x);

   gv_des_log:= 'Fin COB_PR_ACTUA_ESTAD_RECUP_COBRA';
   fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( lv_cod_pais, lv_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);

  FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(lv_cod_pais, lv_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, 2);

 EXCEPTION

  WHEN OTHERS THEN

   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(SQLERRM, 1, 250);

   RAISE_application_error(-20123,'ERROR COB_PR_CARGA_ESTAD_RECUP_COBRA: ' || ls_sqlerrm);

 END COB_PR_ACTUA_ESTAD_RECUP_COBRA;

   FUNCTION cob_fn_obtie_cobra_banco(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_oid_movi_cc ccc_movim_cuent_corri.oid_movi_cc%TYPE)
   RETURN NUMBER
   IS

      v_imp_pago_hmcc                     NUMBER(12,2);
      v_imp_pago_mcc                       NUMBER(12,2);
      v_imp_cobr                                NUMBER(12,2);
   BEGIN

      BEGIN
         SELECT SUM(hmcc.imp_pago)
         INTO v_imp_pago_hmcc
         FROM ccc_histo_movim_cc hmcc
         WHERE hmcc.mvcc_oid_movi_cc =p_oid_movi_Cc
         AND EXISTS (
                     SELECT NULL
                     FROM cob_subpr_pago_banca spg
                     WHERE spg.ind_acti = 1
                     AND spg.cb_oid_subp = hmcc.subp_oid_subp
                     AND spg.cod_pais = p_cod_pais );
      EXCEPTION
        WHEN no_data_found THEN
          v_imp_pago_hmcc:=0;
      END;

     BEGIN
        SELECT SUM(mcc.imp_pago)
        INTO v_imp_pago_mcc
        FROM ccc_movim_cuent_corri mcc
        WHERE mcc.oid_movi_cc=p_oid_movi_Cc
        AND EXISTS (
                     SELECT NULL
                     FROM cob_subpr_pago_banca spg
                     WHERE spg.ind_acti = 1
                     AND spg.cb_oid_subp = mcc.subp_oid_subp_ulti
                     AND spg.cod_pais = p_cod_pais );
     EXCEPTION
       WHEN no_data_found THEN
          v_imp_pago_mcc:=0;
     END;

      v_imp_cobr:= NVL(v_imp_pago_hmcc,0) + NVL(v_imp_pago_mcc,0);

      RETURN v_imp_cobr;

   END cob_fn_obtie_cobra_banco;

 FUNCTION cob_fn_obtie_cobra_banco_sabon(
  p_oid_movi_cc                    IN   ccc_movim_cuent_corri.oid_movi_cc%TYPE)
 RETURN NUMBER
 IS

  v_imp_pago_hmcc                  NUMBER(12,2);
  v_imp_pago_mcc                   NUMBER(12,2);
  v_imp_cobr                       NUMBER(12,2);

 BEGIN

  BEGIN

   SELECT SUM(hmcc.imp_pago)
   INTO v_imp_pago_hmcc
   FROM ccc_histo_movim_cc hmcc
   WHERE hmcc.mvcc_oid_movi_cc =p_oid_movi_Cc;

  EXCEPTION

   WHEN no_data_found THEN
    v_imp_pago_hmcc:=0;

  END;

  BEGIN

   SELECT SUM(mcc.imp_pago)
   INTO v_imp_pago_mcc
   FROM ccc_movim_cuent_corri mcc
   WHERE mcc.oid_movi_cc=p_oid_movi_Cc;

  EXCEPTION

   WHEN no_data_found THEN
    v_imp_pago_mcc:=0;

  END;

  v_imp_cobr:= NVL(v_imp_pago_hmcc,0) + NVL(v_imp_pago_mcc,0);

  RETURN v_imp_cobr;

 END cob_fn_obtie_cobra_banco_sabon;

 FUNCTION cob_fn_obtie_cobra_banco_fecha(
  p_cod_pais                       IN   seg_pais.cod_pais%TYPE,
  p_oid_movi_cc                    IN   ccc_movim_cuent_corri.oid_movi_cc%TYPE,
  p_fec_cier                       IN   DATE)
 RETURN NUMBER
   IS
      v_imp_pago_hmcc                    NUMBER(12,2);
      v_imp_pago_mcc                      NUMBER(12,2);
      v_imp_cobr                              NUMBER(12,2);
      v_fec_cier                               DATE;
   BEGIN

      v_fec_cier:=NVL(p_fec_cier,trunc(SYSDATE));

      BEGIN
         SELECT SUM(hmcc.imp_pago)
         INTO v_imp_pago_hmcc
         FROM ccc_histo_movim_cc hmcc
         WHERE hmcc.mvcc_oid_movi_cc =p_oid_movi_Cc
         AND hmcc.fec_movi <= v_fec_cier
         AND EXISTS (
                     SELECT NULL
                     FROM cob_subpr_pago_banca spg
                     WHERE spg.ind_acti = 1
                     AND spg.cb_oid_subp = hmcc.subp_oid_subp
                     AND spg.cod_pais = p_cod_pais );
      EXCEPTION
        WHEN no_data_found THEN
          v_imp_pago_hmcc:=0;
      END;

     BEGIN
        SELECT SUM(mcc.imp_pago)
        INTO v_imp_pago_mcc
        FROM ccc_movim_cuent_corri mcc
        WHERE mcc.oid_movi_cc=p_oid_movi_Cc
        AND mcc.fec_ulti_movi  <= v_fec_cier
        AND EXISTS (
                     SELECT NULL
                     FROM cob_subpr_pago_banca spg
                     WHERE spg.ind_acti = 1
                     AND spg.cb_oid_subp = mcc.subp_oid_subp_ulti
                     AND spg.cod_pais = p_cod_pais );
     EXCEPTION
       WHEN no_data_found THEN
          v_imp_pago_mcc:=0;
     END;

      v_imp_cobr:= NVL(v_imp_pago_hmcc,0) + NVL(v_imp_pago_mcc,0);

   RETURN v_imp_cobr;
   END cob_fn_obtie_cobra_banco_fecha;

 FUNCTION cob_fn_obtie_cobra_banco_sabon(
  p_oid_movi_cc                    IN   ccc_movim_cuent_corri.oid_movi_cc%TYPE,
  p_fec_cier                       IN   DATE)
 RETURN NUMBER
 IS

  v_imp_pago_hmcc                  NUMBER(12,2);
  v_imp_pago_mcc                   NUMBER(12,2);
  v_imp_cobr                       NUMBER(12,2);
  v_fec_cier                       DATE;

 BEGIN

  v_fec_cier:=NVL(p_fec_cier,trunc(SYSDATE));

  BEGIN

   SELECT SUM(hmcc.imp_pago)
   INTO v_imp_pago_hmcc
   FROM ccc_histo_movim_cc hmcc
   WHERE hmcc.mvcc_oid_movi_cc =p_oid_movi_Cc
     AND hmcc.fec_movi <= v_fec_cier;

  EXCEPTION

   WHEN no_data_found THEN
    v_imp_pago_hmcc:=0;

  END;

  BEGIN

   SELECT SUM(mcc.imp_pago)
   INTO v_imp_pago_mcc
   FROM ccc_movim_cuent_corri mcc
   WHERE mcc.oid_movi_cc=p_oid_movi_Cc
     AND mcc.fec_ulti_movi  <= v_fec_cier;

  EXCEPTION

   WHEN no_data_found THEN
    v_imp_pago_mcc:=0;

  END;

  v_imp_cobr:= NVL(v_imp_pago_hmcc,0) + NVL(v_imp_pago_mcc,0);

  RETURN v_imp_cobr;

 END cob_fn_obtie_cobra_banco_sabon;

   FUNCTION cob_fn_obtie_cobra_abono_nomon(
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_oid_movi_cc ccc_movim_cuent_corri.oid_movi_cc%TYPE)
    RETURN NUMBER
   IS
      v_imp_pago_hmcc   NUMBER(12,2);
      v_imp_pago_mcc    NUMBER(12,2);
      v_imp_cobr        NUMBER(12,2);
   BEGIN

      BEGIN
         SELECT SUM(hmcc.imp_pago)
         INTO v_imp_pago_hmcc
         FROM ccc_histo_movim_cc hmcc
         WHERE hmcc.mvcc_oid_movi_cc =p_oid_movi_Cc
         AND  NOT EXISTS (
                     SELECT NULL
                     FROM cob_subpr_pago_banca spg
                     WHERE spg.ind_acti = 1
                     AND spg.cb_oid_subp = hmcc.subp_oid_subp
                     AND spg.cod_pais = p_cod_pais );
      EXCEPTION
        WHEN no_data_found THEN
          v_imp_pago_hmcc:=0;
      END;

     BEGIN
        SELECT SUM(mcc.imp_pago)
        INTO v_imp_pago_mcc
        FROM ccc_movim_cuent_corri mcc
        WHERE mcc.oid_movi_cc=p_oid_movi_Cc
        AND  NOT EXISTS (
                     SELECT NULL
                     FROM cob_subpr_pago_banca spg
                     WHERE spg.ind_acti = 1
                     AND spg.cb_oid_subp = mcc.subp_oid_subp_ulti
                     AND spg.cod_pais = p_cod_pais );
     EXCEPTION
       WHEN no_data_found THEN
          v_imp_pago_mcc:=0;
     END;

      v_imp_cobr:= NVL(v_imp_pago_hmcc,0) + NVL(v_imp_pago_mcc,0);

      RETURN v_imp_cobr;

   END cob_fn_obtie_cobra_abono_nomon;

   PROCEDURE COB_PR_RESET_ESTAD_RECUP_COBRA(
     p_cod_pais                   seg_pais.cod_pais%TYPE,
     p_cod_soci                   seg_socie.cod_soci%TYPE,
     p_cod_peri                   seg_perio_corpo.cod_peri%TYPE,
     p_cod_regi                   zon_regio.cod_regi%TYPE,
     p_cod_zona                   zon_zona.cod_zona%TYPE,
     p_fec_cier                   DATE )
   IS
   BEGIN

       UPDATE cob_repor_estad_recup_cobra re
       SET re.fec_cier_vent = p_fec_cier,
           re.ulti_nume_hist = 0
       WHERE re.cod_pais = p_cod_pais
       AND   re.cod_soci = p_cod_soci
       AND   re.cod_peri = p_cod_peri
       AND   re.cod_regi = p_cod_regi
       AND   re.cod_zona = p_cod_zona;

   END COB_PR_RESET_ESTAD_RECUP_COBRA;

 PROCEDURE COB_PR_VALID_ESTAD_RECUP_COBRA
 IS
 
  lv_cant_inco                   NUMBER(12);
  
 BEGIN
 
  COB_PR_ACTUA_ESTAD_RECUP_COBRA;
  
  -- Diferencia de Saldos --
  SELECT COUNT(*)
  INTO lv_cant_inco
  FROM cob_repor_estad_recup_cobra cbz
  WHERE EXISTS (
   SELECT NULL
   FROM ccc_movim_cuent_corri mcc
   WHERE mcc.oid_movi_cc = cbz.oid_movi_cc
   AND mcc.imp_pend <> cbz.imp_sald_pend_sac);
  
  IF lv_cant_inco > 0 THEN
       
   UPDATE cob_repor_estad_recup_cobra cbz
   SET cbz.ulti_nume_hist = -1
   WHERE EXISTS (
    SELECT NULL
    FROM ccc_movim_cuent_corri mcc
    WHERE mcc.oid_movi_cc = cbz.oid_movi_cc
    AND mcc.imp_pend <> cbz.imp_sald_pend_sac);
  
   COB_PR_ACTUA_ESTAD_RECUP_COBRA;
  
  END IF;
  
    
 END COB_PR_VALID_ESTAD_RECUP_COBRA;
 
   PROCEDURE COB_PR_CRONO_VENTA_FECHA_EXCEP (
      p_cod_pais seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_peri seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi  zon_regio.cod_regi%type,
      p_cod_zona   zon_zona.cod_zona%type,
      p_cod_usuario cob_detal_asign_carte.usu_crea%TYPE,
      p_str_fec_exce      VARCHAR2)
   IS
   /* Valida y registra fecha de cierre de finida por el usuario */

      lv_fecha_exce     DATE;
      lv_fecha_wrk      DATE;
      lv_existe_reg     NUMBER(11);
      lv_fecha_orig     DATE;
      lv_flg_error      NUMBER(1);
      lv_msg_error      VARCHAR2(80);

      lv_max_dias_exce  NUMBER(3);
      lv_dif_fechas     NUMBER(12);


   BEGIN
    --  Inicializa variables globales para nuevo log   ;
          gv_log_cod_pais := p_cod_pais;
          gv_log_cod_soci := p_cod_soci;
          gv_log_user := USER;
          gv_log_cod_modu := 'COB';
          gv_log_cod_proc := '65';

      FIN_PKG_GENER.FIN_PR_REGIS_PROCE_EJEC(p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_log_user , gv_cod_proc_ejec);

--      DBMS_OUTPUT.put_line ( 'Parametros venta excep pais : ' || gv_log_cod_pais || ' socie: ' || gv_log_cod_soci || ' user: ' || gv_log_user ||
--                           ' cod modu: '  || gv_log_cod_modu || '  cod proce: ' || gv_log_cod_proc );

      gv_des_log:='Inicio  COB_PR_CRONO_VENTA_FECHA_EXCEP   Cod Pais: ' || p_cod_pais || ' Cod Socie: ' || p_cod_soci ||
                  ' Cod Peri: ' || p_cod_peri ||   ' Cod regi: ' || p_cod_regi || ' Cod zona: ' || p_cod_zona ||
                  ' Usuario: ' || p_cod_usuario || ' Fecha excep: ' || p_str_fec_exce;

--      dbms_output.put_line (gv_des_log);

      fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                 gv_cod_proc_ejec, gv_des_log);

      lv_flg_error := 0;

--      convierte fecha de excepcion que se recibe en variable tipo varchar
--      a variable de tipo date
      lv_fecha_exce := to_date(p_str_fec_exce, 'DD/MM/YYYY');
--      dbms_output.put_line('fecha string: '  || p_str_fec_exce || ' fecha date: ' || lv_fecha_exce );

      lv_fecha_wrk := fin_pkg_gener.fin_fn_obtie_fecha_util(p_str_fec_exce, 1);
--      dbms_output.put_line('fecha no feriado: '  || lv_fecha_wrk || ' fecha excepcion: ' || lv_fecha_exce );

      IF lv_fecha_wrk <> lv_fecha_exce THEN
             lv_flg_error := 1;
             lv_msg_error := 'Fecha excepcion: ' || lv_fecha_exce || ' no debe ser Sabado, Domingo ni Feriado. ' ;
             fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                         gv_cod_proc_ejec, lv_msg_error);
             raise_application_error(-20002, lv_msg_error);
      END IF;

      SELECT par.max_dias_exce
      INTO   lv_max_dias_exce
      FROM   cob_crono_venta_param par
      WHERE par.cod_pais = p_cod_pais
      AND   par.cod_soci = p_cod_soci
      AND   p_cod_peri BETWEEN par.cod_peri_inic AND par.cod_peri_fin;

      lv_fecha_wrk := SYSDATE;
      BEGIN
            lv_existe_reg := 1;
            SELECT rep. fec_cier_vent
            INTO lv_fecha_orig
            FROM cob_repor_estad_recup_cobra rep
            WHERE rep.cod_pais = p_cod_pais
            AND   rep.cod_soci = p_cod_soci
            AND   rep.cod_peri = p_cod_peri
            AND   rep.cod_regi = p_cod_regi
            AND   rep.cod_zona = p_cod_zona
            AND   rownum = 1;
      EXCEPTION
            WHEN no_data_found THEN
                 lv_existe_reg := 0;
      END;

      IF lv_existe_reg = 1 THEN
          IF  lv_fecha_orig > lv_fecha_exce THEN
              lv_dif_fechas := lv_fecha_orig - lv_fecha_exce;
          ELSE
              lv_dif_fechas := lv_fecha_exce - lv_fecha_orig;
          END IF;

          IF lv_dif_fechas > lv_max_dias_exce THEN
             lv_flg_error := 1;
             lv_msg_error := 'Fecha excepcion: ' || lv_fecha_exce || ' es demasiado lejana a fecha original: ' || lv_fecha_orig;
             fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                         gv_cod_proc_ejec, lv_msg_error);
             raise_application_error(-20003, lv_msg_error);
          ELSE
              IF lv_dif_fechas = 0 THEN
                 lv_flg_error := 1;
                 lv_msg_error := 'Fecha excepcion es la misma, no necesita cambio.' ;
                 fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                             gv_cod_proc_ejec, lv_msg_error);
                 raise_application_error(-20004, lv_msg_error);
              END IF;
          END IF;
      END IF;

      IF lv_flg_error = 0 THEN
          BEGIN
                SELECT ex.fec_exce
                INTO lv_fecha_wrk
                FROM  cob_estad_crono_venta_excep ex
                WHERE ex.cod_pais = p_cod_pais
                AND   ex.cod_soci = p_cod_soci
                AND   ex.cod_peri = p_cod_peri
                AND   ex.cod_regi = p_cod_regi
                AND   ex.cod_zona = p_cod_zona;
          EXCEPTION
                WHEN no_data_found THEN
                     lv_fecha_wrk := NULL;
          END;

          IF lv_fecha_wrk IS NULL THEN
             INSERT INTO cob_estad_crono_venta_excep ex
                (cod_pais, cod_soci, cod_peri,  cod_regi, cod_zona, fec_exce,
                 usu_modi,  fec_modi )
             VALUES(p_cod_pais, p_cod_soci, p_cod_peri, p_cod_regi,
                    p_cod_zona, lv_fecha_exce, p_cod_usuario, SYSDATE );
          ELSE
              UPDATE cob_estad_crono_venta_excep ex
              SET ex.fec_exce = lv_fecha_exce,
                  ex.usu_modi = p_cod_usuario,
                  ex.fec_modi = SYSDATE
              WHERE ex.cod_pais = p_cod_pais
              AND   ex.cod_soci = p_cod_soci
              AND   ex.cod_peri = p_cod_peri
              AND   ex.cod_regi = p_cod_regi
              AND   ex.cod_zona = p_cod_zona;

          END IF;
          IF lv_existe_reg = 1 THEN
             gv_des_log:='Invoca rutina cob_pr_reset_estad_recup_cobra ';
             fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                         gv_cod_proc_ejec, gv_des_log);

             cob_pr_reset_estad_recup_cobra(p_cod_pais, p_cod_soci, p_cod_peri, p_cod_regi, p_cod_zona, lv_fecha_exce);
          END IF;
         gv_des_log:='Fin rutina  COB_PR_CRONO_VENTA_FECHA_EXCEP';
         fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( gv_log_cod_pais, gv_log_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                     gv_cod_proc_ejec, gv_des_log);
         FIN_PKG_GENER.FIN_PR_FINAL_PROCE_EJEC(p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc, gv_cod_proc_ejec, 2);
      ELSE
          raise_application_error(-20008, lv_msg_error );
      END IF;

   END COB_PR_CRONO_VENTA_FECHA_EXCEP;


   FUNCTION cob_fn_regis_fecha_cierr_venta(
      p_cod_pais  seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_peri  seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi  zon_regio.cod_regi%TYPE,
      p_cod_zona  zon_zona.cod_zona%TYPE )
   RETURN DATE
   IS
   lv_fec_cier  DATE;
   lv_flg_crear  NUMBER(1);
   lv_flg_exist  NUMBER(12);

   lv_num_peri_cier        cob_crono_venta_param.num_peri_cier%TYPE;
   lv_num_dias_grac_cier   cob_crono_venta_param.num_dias_grac_cier%TYPE;
   lv_num_dias_cier        cob_crono_venta_param.num_dias_cier%TYPE;

   lv_cod_peri_x           seg_perio_corpo.cod_peri%TYPE;
   lv_oid_peri_x           cra_perio.oid_peri%TYPE;
   lv_fec_fact_x           DATE;
   lv_fec_char             VARCHAR2(10);
   lv_fec_char_w            VARCHAR2(10);
   lv_year                 VARCHAR2(4);

   BEGIN

    lv_flg_crear := 0;

   BEGIN
  --   dbms_output.put_line('inicio fn regis fecha '  );

       SELECT cro.fec_cier
       INTO lv_fec_cier
       FROM cob_tempo_estad_crono_venta cro
       WHERE cro.cod_pais = p_cod_pais
       AND   cro.cod_soci = p_cod_soci
       AND   cro.cod_peri = p_cod_peri
       AND   cro.cod_regi = p_cod_regi
       AND   cro.cod_zona = p_cod_zona ;
   EXCEPTION
         WHEN NO_DATA_FOUND THEN
         lv_flg_crear := 1;
   END;

   /* Calcula y registra  fecha de cierre */
   IF lv_flg_crear = 1 THEN
    --    dbms_output.put_line('No existe en tempo  camp:  ' || p_cod_peri || ' Region: ' || p_cod_regi || ' Zona: ' || p_cod_zona );
      /* Determina si existe  una fecha excepcional */
      lv_flg_exist := 0;
      SELECT count(*)
      INTO lv_flg_exist
      FROM cob_estad_crono_venta_excep exp
      WHERE exp.cod_pais = p_cod_pais
      AND   exp.cod_soci = p_cod_soci
      AND   exp.cod_peri = p_cod_peri
      AND   exp.cod_regi = p_cod_regi
      AND   exp.cod_zona = p_cod_zona;

      IF lv_flg_exist > 0  THEN
        SELECT exp.fec_exce
        INTO lv_fec_cier
        FROM cob_estad_crono_venta_excep exp
        WHERE exp.cod_pais = p_cod_pais
        AND   exp.cod_soci = p_cod_soci
        AND   exp.cod_peri = p_cod_peri
        AND   exp.cod_regi = p_cod_regi
        AND   exp.cod_zona = p_cod_zona;
      ELSE
          /*  si no hay definida una fecha excepcional,  procede a calcular */
          /*  recupera parametros para el calculo  */
        BEGIN
            SELECT par.num_peri_cier, par.num_dias_grac_cier, par.num_dias_cier
            INTO lv_num_peri_cier, lv_num_dias_grac_cier, lv_num_dias_cier
            FROM cob_crono_venta_param par
            WHERE par.cod_pais = p_cod_pais
            AND   par.cod_soci = p_cod_soci
            AND   par.cod_peri_inic <= p_cod_peri
            AND   par.cod_peri_fin >= p_cod_peri;
        EXCEPTION
            WHEN  OTHERS THEN
                  gv_des_log:='error regis_fecha_cierr_venta, No hay parametria cod_peri : ' || p_cod_peri ;
                  fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                             gv_cod_proc_ejec, gv_des_log);
                   raise_application_error(-20006, gv_des_log);
        END;
        /* obtiene  codigo de periodo  n siguiente */
        lv_cod_peri_x := cob_pkg_gener.cob_fn_calcu_perio_nsgte(p_cod_peri, lv_num_peri_cier );

         -- obtiene oid de campa?a x
        lv_oid_peri_x :=FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_pais, lv_cod_peri_x);

        BEGIN
            /* Obtiene fecha de facturacion inicial de la campa?a X+n calculada */
            SELECT cro.FEC_INIC
            INTO   lv_fec_fact_x
            FROM   CRA_CRONO CRO,
                   cra_activ act,
                   zon_zona zon
            WHERE  cro.perd_oid_peri = lv_oid_peri_x
            AND    cro.cact_oid_acti = act.OID_ACTI
            AND    act.cod_acti = 'FA'
            AND    cro.zzon_oid_zona = zon.oid_zona
            AND    zon.cod_zona = p_cod_zona;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                 /* determina la fecha de facturacion de la campa?a de proceso */
                --dbms_output.put_line('Busca oid de campa?a de proceso: :  ' || p_cod_peri  );
                lv_oid_peri_x :=FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_pais, p_cod_peri);
               -- dbms_output.put_line('Busca fecha fact  de campa?a de proceso: :  ' || p_cod_peri || ' oid: ' || lv_oid_peri_x || ' Region: ' || p_cod_regi || ' Zona: ' || p_cod_zona );
                BEGIN
                    SELECT cro.FEC_INIC
                    INTO   lv_fec_fact_x
                    FROM   CRA_CRONO CRO,
                           cra_activ act,
                           zon_zona zon
                    WHERE  cro.perd_oid_peri = lv_oid_peri_x
                    AND    cro.cact_oid_acti = act.OID_ACTI
                    AND    act.cod_acti = 'FA'
                    AND    cro.zzon_oid_zona = zon.oid_zona
                    AND    zon.cod_zona = p_cod_zona;
                EXCEPTION
                    WHEN NO_DATA_FOUND THEN
                         lv_fec_fact_x := NULL;
                         gv_des_log:='error regis_fecha_cierr_venta, oid peri: ' || lv_oid_peri_x || ' zona: ' || p_cod_zona  ;
                         fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                         gv_cod_proc_ejec, gv_des_log);
                END;
              --  dbms_output.put_line('Resultado por excep fecha fact de campa?a de proceso: :  ' || lv_fec_fact_x );
        END;
      --  dbms_output.put_line('Resultado fecha fact de campa?a de proceso: :  ' || lv_fec_fact_x );
      BEGIN
         lv_year := extract(YEAR FROM lv_fec_fact_x);
      EXCEPTION
        WHEN  OTHERS THEN
          gv_des_log:='ERROR AL RECUPERA YEAR : ' || lv_fec_fact_x ;
          fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                     gv_cod_proc_ejec, gv_des_log);
          raise_application_error(-20008, gv_des_log);
      END;
      BEGIN
      lv_fec_char := to_char(lv_fec_fact_x, 'DD/MM/YYYY');
      EXCEPTION
            WHEN  OTHERS THEN
            gv_des_log:='error al convertir fecha  a char  : ' || lv_fec_fact_x ;
            fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                       gv_cod_proc_ejec, gv_des_log);
             raise_application_error(-20010, gv_des_log);
      END;
      IF  lv_year < 50 THEN
          lv_fec_char_w := substr(lv_fec_char, 1, 6) || '2' || substr(lv_fec_char, 8, 3) ;
      ELSE
         lv_fec_char_w := substr(lv_fec_char, 1, 6) || '1' || substr(lv_fec_char, 8, 3);
      END IF;
      lv_fec_fact_x := to_date(lv_fec_char_w, 'DD/MM/YYYY');
   --   dbms_output.put_line('fecha fact CORREGIDA :  ' || lv_fec_fact_x );

        /* aplica dias de gracia a la fecha de cierre */
        lv_fec_cier := lv_fec_fact_x + lv_num_dias_grac_cier;
   --     dbms_output.put_line('fecha fact mas dias gracia :  ' || lv_fec_cier );
        /* ajusta a dia util */
        lv_fec_cier := fin_pkg_gener.fin_fn_obtie_fecha_util(lv_fec_cier, 1);
   --     dbms_output.put_line('fecha ajus dia util :  ' || lv_fec_cier );

      END IF;
      BEGIN
          lv_year := extract(YEAR FROM lv_fec_cier);
      EXCEPTION
            WHEN  OTHERS THEN
            gv_des_log:='error segunda extrac year  lv_fec_cier : ' || lv_fec_cier ;
            fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                       gv_cod_proc_ejec, gv_des_log);
             raise_application_error(-20012, gv_des_log);

      END;
      BEGIN
          lv_fec_char := to_char(lv_fec_cier, 'DD/MM/YYYY');
      EXCEPTION
         WHEN  OTHERS THEN
            gv_des_log:='error segunda convercion fecha  a char  lv_fec_cier : ' || lv_fec_cier ;
            fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                       gv_cod_proc_ejec, gv_des_log);
             raise_application_error(-20014, gv_des_log);

      END;
      IF  lv_year < 50 THEN
          lv_fec_char_w := substr(lv_fec_char, 1, 6) || '2' || substr(lv_fec_char, 8, 3) ;
      ELSE
         lv_fec_char_w := substr(lv_fec_char, 1, 6) || '1' || substr(lv_fec_char, 8, 3);
      END IF;

   --    dbms_output.put_line('a?o :  ' || lv_year );
       lv_fec_char := lv_fec_char_w;
  --     dbms_output.put_line('fecha char :  ' || lv_fec_char );
    BEGIN
      INSERT INTO cob_tempo_estad_crono_venta
      (cod_pais, cod_soci, cod_peri, cod_regi, cod_zona, fec_cier)
      VALUES(p_cod_pais, p_cod_soci, p_cod_peri, p_cod_regi, p_cod_zona, to_date(lv_fec_char, 'DD/MM/YYYY') );
     --   dbms_output.put_line('CREA en tempo  camp:  ' || p_cod_peri || ' Region: ' || p_cod_regi || ' Zona: ' || p_cod_zona || ' Fecha: ' || lv_fec_cier );
   EXCEPTION
       WHEN  OTHERS THEN
            gv_des_log:='error al insertar en tempo  feha char  : ' || lv_fec_char ;
            fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                       gv_cod_proc_ejec, gv_des_log);
             raise_application_error(-20016, gv_des_log);

   END;
   END IF;

       RETURN lv_fec_cier;
   END cob_fn_regis_fecha_cierr_venta ;


   FUNCTION cob_fn_obtie_fecha_cierr_venta(
      p_cod_pais  seg_pais.cod_pais%TYPE,
      p_cod_soci seg_socie.cod_soci%TYPE,
      p_cod_peri  seg_perio_corpo.cod_peri%TYPE,
      p_cod_regi  zon_regio.cod_regi%TYPE,
      p_cod_zona  zon_zona.cod_zona%TYPE )
   RETURN DATE
   IS
   lv_fec_cier  DATE;
   lv_flg_crear  NUMBER(1);
   lv_flg_exist  NUMBER(12);

   lv_num_peri_cier        cob_crono_venta_param.num_peri_cier%TYPE;
   lv_num_dias_grac_cier   cob_crono_venta_param.num_dias_grac_cier%TYPE;
   lv_num_dias_cier        cob_crono_venta_param.num_dias_cier%TYPE;

   lv_cod_peri_x           seg_perio_corpo.cod_peri%TYPE;
   lv_oid_peri_x           cra_perio.oid_peri%TYPE;
   lv_fec_fact_x           DATE;

   BEGIN

   lv_flg_crear := 0;
   BEGIN
       SELECT cro.fec_cier
       INTO lv_fec_cier
       FROM cob_tempo_estad_crono_venta cro
       WHERE cro.cod_pais = p_cod_pais
       AND   cro.cod_soci = p_cod_soci
       AND   cro.cod_peri = p_cod_peri
       AND   cro.cod_regi = p_cod_regi
       AND   cro.cod_zona = p_cod_zona ;
   EXCEPTION
         WHEN NO_DATA_FOUND THEN
         lv_flg_crear := 1;
   END;

   /* Calcula y registra  fecha de cierre */
   IF lv_flg_crear = 1 THEN
        --dbms_output.put_line('No existe en tempo  camp:  ' || p_cod_peri || ' Region: ' || p_cod_regi || ' Zona: ' || p_cod_zona );
      /* Determina si existe  una fecha excepcional */
      lv_flg_exist := 0;
      SELECT count(*)
      INTO lv_flg_exist
      FROM cob_estad_crono_venta_excep exp
      WHERE exp.cod_pais = p_cod_pais
      AND   exp.cod_soci = p_cod_soci
      AND   exp.cod_peri = p_cod_peri
      AND   exp.cod_regi = p_cod_regi
      AND   exp.cod_zona = p_cod_zona;

      IF lv_flg_exist > 0  THEN
        SELECT exp.fec_exce
        INTO lv_fec_cier
        FROM cob_estad_crono_venta_excep exp
        WHERE exp.cod_pais = p_cod_pais
        AND   exp.cod_soci = p_cod_soci
        AND   exp.cod_peri = p_cod_peri
        AND   exp.cod_regi = p_cod_regi
        AND   exp.cod_zona = p_cod_zona;
      ELSE
          /*  si no hay definida una fecha excepcional,  procede a calcular */
          /*  recupera parametros para el calculo  */
        SELECT par.num_peri_cier, par.num_dias_grac_cier, par.num_dias_cier
        INTO lv_num_peri_cier, lv_num_dias_grac_cier, lv_num_dias_cier
        FROM cob_crono_venta_param par
        WHERE par.cod_pais = p_cod_pais
        AND   par.cod_soci = p_cod_soci
        AND   par.cod_peri_inic <= p_cod_peri
        AND   par.cod_peri_fin >= p_cod_peri;

        /* obtiene  codigo de periodo  n siguiente */
        lv_cod_peri_x := cob_pkg_gener.cob_fn_calcu_perio_nsgte(p_cod_peri, lv_num_peri_cier );

         -- obtiene oid de campa?a x
        lv_oid_peri_x :=FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_pais, lv_cod_peri_x);

        BEGIN
            /* Obtiene fecha de facturacion inicial de la campa?a X+n calculada */
            SELECT cro.FEC_INIC
            INTO   lv_fec_fact_x
            FROM   CRA_CRONO CRO,
                   cra_activ act,
                   zon_zona zon
            WHERE  cro.perd_oid_peri = lv_oid_peri_x
            AND    cro.cact_oid_acti = act.OID_ACTI
            AND    act.cod_acti = 'FA'
            AND    cro.zzon_oid_zona = zon.oid_zona
            AND    zon.cod_zona = p_cod_zona;
        EXCEPTION
            WHEN NO_DATA_FOUND THEN
                 /* determina la fecha de facturacion de la campa?a de proceso */
                --dbms_output.put_line('Busca oid de campa?a de proceso: :  ' || p_cod_peri  );
                lv_oid_peri_x :=FIN_PKG_GENER.FIN_FN_OBTIE_OID_PERIO(p_cod_pais, p_cod_peri);
               -- dbms_output.put_line('Busca fecha fact  de campa?a de proceso: :  ' || p_cod_peri || ' oid: ' || lv_oid_peri_x || ' Region: ' || p_cod_regi || ' Zona: ' || p_cod_zona );
                SELECT cro.FEC_INIC
                INTO   lv_fec_fact_x
                FROM   CRA_CRONO CRO,
                       cra_activ act,
                       zon_zona zon
                WHERE  cro.perd_oid_peri = lv_oid_peri_x
                AND    cro.cact_oid_acti = act.OID_ACTI
                AND    act.cod_acti = 'FA'
                AND    cro.zzon_oid_zona = zon.oid_zona
                AND    zon.cod_zona = p_cod_zona;
               -- dbms_output.put_line('Resultado fecha fact de campa?a de proceso: :  ' || lv_fec_fact_x );
        END;
        /* aplica dias de gracia a la fecha de cierre */
        lv_fec_cier := lv_fec_fact_x + lv_num_dias_grac_cier;
        /* ajusta a dia util */
        lv_fec_cier := fin_pkg_gener.fin_fn_obtie_fecha_util(lv_fec_cier, 1);

      END IF;
        --dbms_output.put_line('CREA en tempo  camp:  ' || p_cod_peri || ' Region: ' || p_cod_regi || ' Zona: ' || p_cod_zona || ' Fecha: ' || lv_fec_cier );

   END IF;

       RETURN lv_fec_cier;
   END cob_fn_obtie_fecha_cierr_venta ;

   PROCEDURE cob_pr_carga_tempo_fecha_cierr(
     p_cod_pais  seg_pais.cod_pais%TYPE,
     p_cod_soci seg_socie.cod_soci%TYPE   )
   IS

      lv_oid_ante_regi_proc                    fin_contr_regis_progr.oid_ante_regi_proc%TYPE;
      lv_oid_ulti_regi_proc                     fin_contr_regis_progr.oid_ulti_regi_proc%TYPE;

      lv_fecha_cier    DATE;

      lv_cont_x        NUMBER(12);

      CURSOR c_Zonas  IS
          SELECT DISTINCT
             spc.cod_peri,
             zr.cod_regi,
             zz.cod_zona
          FROM Ccc_Movim_Cuent_Corri mcc,
                    cra_perio cp,
                    seg_perio_corpo spc,
                    zon_secci zs,
                    zon_zona zz,
                    Zon_Regio zr
          WHERE mcc.perd_oid_peri=cp.oid_peri
          AND cp.peri_oid_peri=spc.oid_peri
          AND mcc.zscc_oid_secc=zs.oid_secc
          AND zz.oid_zona= zs.zzon_oid_zona
          AND zr.oid_regi =zz.zorg_oid_regi
          AND mcc.imp_movi>0
          AND mcc.oid_movi_cc>lv_oid_ante_regi_proc
          AND mcc.oid_movi_cc<=lv_oid_ulti_regi_proc
          AND NOT EXISTS (
             SELECT 1
             FROM ccc_movim_cargo_fracc k
             WHERE k.oid_movi_cc=mcc.oid_movi_cc);


      CURSOR c_Zonas_frac  IS
          SELECT DISTINCT
             spc.cod_peri,
             zr.cod_regi,
             zz.cod_zona
          FROM Ccc_Movim_Cuent_Corri mcc,
               ccc_movim_cargo_fracc k,
                    cra_perio cp,
                    seg_perio_corpo spc,
                    zon_secci zs,
                    zon_zona zz,
                    Zon_Regio zr
          WHERE k.oid_peri = cp.oid_peri
          AND cp.peri_oid_peri = spc.oid_peri
          AND mcc.zscc_oid_secc = zs.oid_secc
          AND zz.oid_zona = zs.zzon_oid_zona
          AND zr.oid_regi = zz.zorg_oid_regi
          AND mcc.imp_movi > 0
          AND mcc.oid_movi_cc > lv_oid_ante_regi_proc
          AND mcc.oid_movi_cc <= lv_oid_ulti_regi_proc
          AND k.oid_movi_cc = mcc.oid_movi_cc;

BEGIN
 -- dbms_output.put_line(' INICIA  CARGA TEMPO '  );

--          gv_des_log:='Rutina carga crono:  recup parametros';
--          fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
--                                                     gv_cod_proc_ejec, gv_des_log);

       SELECT f.oid_ulti_regi_proc
       INTO lv_oid_ante_regi_proc
       FROM fin_contr_regis_progr f
       WHERE f.cod_modu='COB'
       AND f.cod_prog='21';

--          gv_des_log:='Rutina carga crono:  recup oid max  de ccc_movim';
--          fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
--                                                     gv_cod_proc_ejec, gv_des_log);


       SELECT MAX(mcc.oid_movi_cc)
       INTO lv_oid_ulti_regi_proc
       FROM ccc_movim_cuent_corri mcc
       WHERE mcc.oid_movi_cc>lv_oid_ante_regi_proc ;

       gv_des_log:='Rutina carga crono:  inicia for 1 ';
       fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                  gv_cod_proc_ejec, gv_des_log);

 -- dbms_output.put_line('ENTRA PRIMER LOOP '  );
    lv_cont_x := 0;
    FOR v_zonas IN c_zonas LOOP
      --  dbms_output.put_line(' busca fecha cod peri: ' || v_zonas.cod_peri || ' region: ' || v_zonas.cod_regi || ' zona: ' || v_zonas.cod_zona );
      BEGIN
        lv_fecha_cier := cob_fn_regis_fecha_cierr_venta(p_cod_pais, p_cod_soci, v_zonas.cod_peri, v_zonas.cod_regi, v_zonas.cod_zona);
      EXCEPTION
         WHEN  OTHERS THEN
            gv_des_log:='error al invocar priemr for  fecha devuelta : ' || lv_fecha_cier ;
            fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                                       gv_cod_proc_ejec, gv_des_log);
             raise_application_error(-20020, gv_des_log);

      END;
        lv_cont_x := lv_cont_x + 1;
      --  dbms_output.put_line(' Fecha result: ' || lv_fecha_cier );

    END LOOP;


    gv_des_log:='Rutina carga crono: fin for 1 :'  || lv_cont_x;
    fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                               gv_cod_proc_ejec, gv_des_log);

   --  dbms_output.put_line('ENTRA SEGUNDO LOOP '  );
    lv_cont_x := 0;
    FOR v_zonas_frac IN c_zonas_frac LOOP
     --   dbms_output.put_line('frac busca fecha cod peri: ' || v_zonas_frac.cod_peri || ' region: ' || v_zonas_frac.cod_regi || ' zona: ' || v_zonas_frac.cod_zona );
        lv_fecha_cier := cob_fn_regis_fecha_cierr_venta(p_cod_pais, p_cod_soci, v_zonas_frac.cod_peri, v_zonas_frac.cod_regi, v_zonas_frac.cod_zona);
        lv_cont_x := lv_cont_x + 1;
     --   dbms_output.put_line(' Fecha result: ' || lv_fecha_cier );

    END LOOP;


    gv_des_log:='Rutina carga crono: fin for 2 :'  || lv_cont_x;
    fin_pkg_gener.FIN_PR_ACTUA_LOG_PROCE_EJEC( p_cod_pais, p_cod_soci, gv_log_cod_modu, gv_log_cod_proc,
                                               gv_cod_proc_ejec, gv_des_log);

 END cob_pr_carga_tempo_fecha_cierr  ;


/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Detallado
                    Recuperacion Cartera Cobrador para formato CSV
Fecha Creacion    : 27/01/2014
Autor             : Carlos Bazalar
************************************************************************************/
 PROCEDURE cob_pr_detal_carte_cobra_csv(
  pscodigopais          VARCHAR2,
  pscodigoSociedad      VARCHAR2,
  pscodigoPeriodoInicio VARCHAR2,
  pscodigoPeriodoFin    VARCHAR2,
  pscodigoEtapaDeuda    VARCHAR2,
  pscodigoCobrador      VARCHAR2,
  psnombrearchivo       VARCHAR2,
  pstitulo              VARCHAR2,
  psdirectorio          OUT VARCHAR2,
  psvistaReporte        VARCHAR2)
 IS
 
  lsdirtempo                     bas_inter.dir_temp%TYPE;
  w_filas                        number := 5000;
  v_handle                       utl_file.file_type;
  lslinea                        VARCHAR2(4000);
  lv_titu                        VARCHAR2(4000);

  CURSOR C_REPOR_A IS
   WITH carte AS
    (
     SELECT  *
     FROM cob_detal_asign_carte  det
     WHERE det.cod_pais = pscodigopais
       AND det.cod_soci = pscodigoSociedad
       AND det.cod_peri >= pscodigoPeriodoInicio
       AND det.cod_peri <= pscodigoPeriodoFin
       AND det.cod_etap_deud = pscodigoEtapaDeuda
       AND det.cod_usua_cobr = DECODE(pscodigoCobrador, NULL, COD_USUA_COBR,'Todos',COD_USUA_COBR, pscodigoCobrador))
    SELECT
     gen.val_i18n des_pais,
     socie.val_deno des_soci,
     etapa.val_desc des_etap_deud,
       cobra.val_nomb_usua_cobr nom_cobr,
       carte.cod_usua_cobr,
       carte.cod_peri ,
       carte.des_regi_clie ,
       carte.cod_zona_clie,
       carte.cod_secc,
       carte.cod_terr,
       carte.oid_clie,
       carte.cod_clie,
       carte.cod_digi_ctrl,
       NVL((SELECT TRIM(MC.VAL_NOM1) || ' ' || TRIM(MC.VAL_NOM2)
            FROM MAE_CLIEN MC
            WHERE MC.OID_CLIE=CARTE.OID_CLIE),' ') nom_clie,
       NVL((SELECT TRIM(MC.VAL_APE1)
            FROM MAE_CLIEN MC
            WHERE MC.OID_CLIE=CARTE.OID_CLIE),' ') val_ape1_clie,
       NVL((SELECT TRIM(MC.VAL_APE2)
            FROM MAE_CLIEN MC
            WHERE MC.OID_CLIE=CARTE.OID_CLIE),' ') val_ape2_clie,
        carte.cod_tipo_docu_iden,
        carte.des_tipo_docu_iden,
        carte.num_docu_iden,
        carte.des_dpto,
        carte.des_prov,
        carte.des_dist,
        carte.des_urba,
        carte.val_cod_post,
        carte.val_dire val_dire ,
        carte.val_dire_refe val_refe,
        carte.num_tele_fijo,
        carte.num_tele_trab,
        carte.num_tele_movi,
        TO_CHAR(carte.fec_docu,'DD/MM/YYYY') fec_docu,
        TO_CHAR(carte.fec_asig,'DD/MM/YYYY') fec_asig,
        TO_CHAR(carte.fec_cier,'DD/MM/YYYY') fec_cier,
        TO_CHAR(carte.fec_venc,'DD/MM/YYYY') fec_venc,
        round(TRUNC(SYSDATE) - TRUNC(carte.fec_docu),0) num_dias_mora,
        TO_CHAR(carte.num_iden_cuot) num_bole_desp,
        carte.imp_deud_orig,
        carte.imp_deud_orig - carte.imp_deud_asig imp_abon_ante,
        carte.imp_deud_asig,
        carte.imp_deud_canc,
        carte.imp_pago_banc,
        carte.imp_pago_otro,
        carte.imp_deud_pend,
        ROUND(carte.imp_deud_canc/carte.imp_deud_asig*100,2) imp_porc_recu,
        accio.des_acci_cobr,
        FIN_PKG_GENER.FIN_FN_OBTIE_REFER_CLIEN(carte.cod_clie,1) val_refe_fami,
        FIN_PKG_GENER.FIN_FN_OBTIE_REFER_CLIEN(carte.cod_clie,2) val_refe_nofa,
        FIN_PKG_GENER.FIN_FN_OBTIE_REFER_CLIEN(carte.cod_clie,3) val_refe_aval,
        FIN_PKG_GENER.FIN_FN_OBTIE_REFER_OBSER_CLIEN(carte.cod_clie) val_refe_obse,
        carte.val_mail,
        mce.camp_prim_pedi, 
        mce.camp_ulti_pedi,
        mce.val_nume_pedi,
        TO_CHAR(SYSDATE,'DD-MM-YYYY') fec_gene_repo,
        carte.val_obse_ulti_gest val_obse_gest,
        gen_pkg_gener.gen_fn_devue_pedid_orig (carte.cod_clie,carte.cod_peri) origen_pedido,
        gen_pkg_gener.gen_fn_devue_prome_pagos_ultim(carte.oid_clie,pscodigoPeriodoFin)DIAS_PAGOS
    FROM 
     carte,
     mae_clien_estat mce,
     cob_etapa_deuda_pais etapa,
     cob_accio_cobra_pais accio,
     cob_usuar_cobra_Pais cobra,
     seg_socie socie,
     seg_pais pais,
     zon_regio regio,
     zon_zona zona,
     gen_i18n_sicc_comun gen
  WHERE carte.cod_soci = socie.cod_soci
    AND carte.cod_pais = pais.cod_pais
    AND carte.cod_etap_deud = etapa.cod_etap_deud
    AND carte.cod_ulti_gest_cobr = accio.cod_acci_cobr
    AND carte.cod_etap_deud = accio.cod_etap_deud
    AND carte.cod_regi_clie = regio.cod_regi
    AND carte.cod_zona_clie = zona.cod_zona
    AND regio.oid_regi = zona.zorg_oid_regi
    AND gen.attr_enti = 'SEG_PAIS'
    AND gen.val_oid = pais.oid_pais
    AND carte.cod_usua_cobr = cobra.cod_usua_cobr
    AND cobra.ind_supe = 0
    AND carte.oid_clie = mce.oid_clie(+) 
  ORDER BY val_nomb_usua_cobr,
          cod_peri ,
          des_regi_clie ,
          cod_zona_clie,
          cod_secc,
          cod_clie;

  CURSOR C_REPOR_H IS
   WITH carte AS (
    SELECT *
    FROM cob_detal_asign_carte  det
    WHERE det.cod_pais = pscodigopais
      AND det.cod_soci = pscodigoSociedad
      AND det.cod_peri >= pscodigoPeriodoInicio
      AND det.cod_peri <= pscodigoPeriodoFin
      AND det.cod_etap_deud = pscodigoEtapaDeuda
      AND det.cod_usua_cobr = DECODE(pscodigoCobrador, NULL, COD_USUA_COBR,'Todos',COD_USUA_COBR, pscodigoCobrador))
   SELECT
    gen.val_i18n des_pais,
    socie.val_deno des_soci,
    etapa.val_desc des_etap_deud,
    cobra.val_nomb_usua_cobr nom_cobr,
    carte.cod_usua_cobr,
    carte.cod_peri ,
    carte.des_regi_clie ,
    carte.cod_zona_clie,
    carte.cod_secc,
    carte.cod_terr,
    carte.oid_clie,
    carte.cod_clie,
    carte.cod_digi_ctrl,
    NVL((SELECT TRIM(MC.VAL_NOM1) || ' ' || TRIM(MC.VAL_NOM2)
            FROM MAE_CLIEN MC
            WHERE MC.OID_CLIE=CARTE.OID_CLIE),' ') nom_clie,
       NVL((SELECT TRIM(MC.VAL_APE1)
            FROM MAE_CLIEN MC
            WHERE MC.OID_CLIE=CARTE.OID_CLIE),' ') val_ape1_clie,
       NVL((SELECT TRIM(MC.VAL_APE2)
            FROM MAE_CLIEN MC
            WHERE MC.OID_CLIE=CARTE.OID_CLIE),' ') val_ape2_clie,
    carte.cod_tipo_docu_iden,
    carte.des_tipo_docu_iden,
    carte.num_docu_iden,
    carte.des_dpto,
    carte.des_prov,
    carte.des_dist,
    carte.des_urba,
    carte.val_cod_post,
    carte.val_dire val_dire ,
    carte.val_dire_refe val_refe,
    carte.num_tele_fijo,
    carte.num_tele_trab,
    carte.num_tele_movi,
    TO_CHAR(carte.fec_docu,'DD/MM/YYYY') fec_docu,
    TO_CHAR(carte.fec_asig,'DD/MM/YYYY') fec_asig,
    TO_CHAR(carte.fec_cier,'DD/MM/YYYY') fec_cier,
    TO_CHAR(carte.fec_venc,'DD/MM/YYYY') fec_venc,
    round(TRUNC(SYSDATE) - TRUNC(carte.fec_docu),0) num_dias_mora,
    TO_CHAR(carte.num_iden_cuot) num_bole_desp,
    carte.imp_deud_orig,
    carte.imp_deud_orig - carte.imp_deud_asig imp_abon_ante,
    carte.imp_deud_asig,
    carte.imp_deud_canc,
    carte.imp_pago_banc,
    carte.imp_pago_otro,
    carte.imp_deud_pend,
    ROUND(carte.imp_deud_canc/carte.imp_deud_asig*100,2) imp_porc_recu,
    accio.des_acci_cobr,
    FIN_PKG_GENER.FIN_FN_OBTIE_REFER_CLIEN(carte.cod_clie,1) val_refe_fami,
    FIN_PKG_GENER.FIN_FN_OBTIE_REFER_CLIEN(carte.cod_clie,2) val_refe_nofa,
    FIN_PKG_GENER.FIN_FN_OBTIE_REFER_CLIEN(carte.cod_clie,3) val_refe_aval,
    FIN_PKG_GENER.FIN_FN_OBTIE_REFER_OBSER_CLIEN(carte.cod_clie) val_refe_obse,
    carte.val_mail,
    mce.camp_prim_pedi, 
    mce.camp_ulti_pedi,
    mce.val_nume_pedi,
    TO_CHAR(SYSDATE,'DD-MM-YYYY') fec_gene_repo,
    carte.val_obse_ulti_gest val_obse_gest,
    gen_pkg_gener.gen_fn_devue_pedid_orig (carte.cod_clie,carte.cod_peri) origen_pedido,
    gen_pkg_gener.gen_fn_devue_prome_pagos_ultim(carte.oid_clie,pscodigoPeriodoFin)DIAS_PAGOS
   FROM 
     carte,
     mae_clien_estat mce,
     cob_etapa_deuda_pais etapa,
     cob_accio_cobra_pais accio,
     cob_usuar_cobra_Pais cobra,
     seg_socie socie,
     seg_pais pais,
     zon_regio regio,
     zon_zona zona,
     gen_i18n_sicc_comun gen
   WHERE carte.cod_soci = socie.cod_soci
     AND carte.cod_pais = pais.cod_pais
     AND carte.cod_etap_deud = etapa.cod_etap_deud
     AND carte.cod_ulti_gest_cobr = accio.cod_acci_cobr
     AND carte.cod_etap_deud = accio.cod_etap_deud
     AND carte.cod_regi_clie = regio.cod_regi
     AND carte.cod_zona_clie = zona.cod_zona
     AND regio.oid_regi = zona.zorg_oid_regi
     AND gen.attr_enti = 'SEG_PAIS'
     AND gen.val_oid = pais.oid_pais
     AND carte.cod_usua_cobr = cobra.cod_usua_cobr
     AND cobra.ind_supe = 0
     AND carte.oid_clie = mce.oid_clie(+) 
   ORDER BY 
    val_nomb_usua_cobr,
    cod_peri ,
    des_regi_clie ,
    cod_zona_clie,
    cod_secc,
    cod_clie;

 TYPE detalleUnidadesReg IS RECORD(
          des_pais              VARCHAR2(100),
          val_deno              seg_socie.val_deno%TYPE,
          val_desc              cob_etapa_deuda_pais.val_desc%TYPE,
          val_nomb_usua_cobr    cob_usuar_cobra_Pais.val_nomb_usua_cobr%TYPE,
          cod_usua_cobr         cob_usuar_cobra_Pais.cod_usua_cobr%TYPE,
          cod_peri              VARCHAR2(6),
          des_regi_clie         cob_detal_asign_carte.des_regi_clie%TYPE,
          cod_zona_clie         cob_detal_asign_carte.cod_zona_clie%TYPE,
          cod_secc              cob_detal_asign_carte.cod_secc%TYPE,
          cod_terr              cob_detal_asign_carte.cod_terr%TYPE,
          oid_clie              cob_detal_asign_carte.oid_clie%TYPE,
          cod_clie              cob_detal_asign_carte.cod_clie%TYPE,
          cod_digi_ctrl         cob_detal_asign_carte.cod_digi_ctrl%TYPE,
          nom_clie              VARCHAR2(100),
          val_ape1_clie         VARCHAR2(100),
          val_ape2_clie         VARCHAR2(100),
          cod_tipo_docu_iden    cob_detal_asign_carte.cod_tipo_docu_iden%TYPE,
          des_tipo_docu_iden    cob_detal_asign_carte.des_tipo_docu_iden%TYPE,
          num_docu_iden         cob_detal_asign_carte.num_docu_iden%TYPE,
          des_dpto              cob_detal_asign_carte.des_dpto%TYPE,
          des_prov              cob_detal_asign_carte.des_prov%TYPE,
          des_dist              cob_detal_asign_carte.des_dist%TYPE,
          des_urba              cob_detal_asign_carte.des_urba%TYPE,
          val_cod_post          cob_detal_asign_carte.val_cod_post%TYPE,
          val_dire              cob_detal_asign_carte.val_dire%TYPE,
          val_dire_refe         cob_detal_asign_carte.val_dire_refe%TYPE,
          num_tele_fijo         cob_detal_asign_carte.num_tele_fijo%TYPE,
          num_tele_trab         cob_detal_asign_carte.num_tele_trab%TYPE,
          num_tele_movi         cob_detal_asign_carte.num_tele_movi%TYPE,
          fec_docu              VARCHAR2(10),
          fec_asig              VARCHAR2(10),
          fec_cier              VARCHAR2(10),
          fec_venc              VARCHAR2(10),
          num_dias_mora         NUMBER(10),
          num_bole_desp         VARCHAR2(20),
          imp_deud_orig         cob_detal_asign_carte.imp_deud_orig%TYPE,
          imp_abon_ante         cob_detal_asign_carte.imp_deud_orig%TYPE,
          imp_deud_asig         cob_detal_asign_carte.imp_deud_asig%TYPE,
          imp_deud_canc         cob_detal_asign_carte.imp_deud_canc%TYPE,
          imp_pago_banc         cob_detal_asign_carte.imp_pago_banc%TYPE,
          imp_pago_otro         cob_detal_asign_carte.imp_pago_otro%TYPE,
          imp_deud_pend         cob_detal_asign_carte.imp_deud_pend%TYPE,
          imp_porc_recu         NUMBER(12,2),
          des_acci_cobr         cob_accio_cobra_pais.des_acci_cobr%TYPE,
          val_refe_fami         VARCHAR2(500),
          val_refe_nofa         VARCHAR2(500),
          val_refe_aval         VARCHAR2(500),
          val_refe_obse         VARCHAR2(500),
          val_mail              VARCHAR2(100),
          camp_prime_pedi       VARCHAR2(6),
          camp_ulti_pedi        VARCHAR2(6),
          num_pedi              NUMBER(12),
          fec_gene_repo         VARCHAR2(10),
          val_obse_ulti_gest    cob_detal_asign_carte.val_obse_ulti_gest%TYPE,
          origen_pedido         STO_ORIGE_DOCUM.DES_ORIG%TYPE,
          DIAS_PAGOS            NUMBER(6)
      );

     TYPE detalleUnidadesRegTab IS TABLE OF detalleUnidadesReg;
     detalleUnidadesRegRecord detalleUnidadesRegTab;

     lbAbrirUtlFile  BOOLEAN;

 BEGIN

  lbAbrirUtlFile := true;
  EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';

  lv_titu := 'Cobrador, Etapa, Campaña, Región, Zona, Sección, Territorio, Codigo Cliente, ' ||
             'Dig. Cont., Nombres, Apellido Paterno, Apellido Materno, Tipo Docum Ident, Num Docum Ident, ' ||
             'Departamento, Provincia, Distrito, Urbanización, Cod Post, Dirección, Referencia, Telf 1, Telf 2, Movil,' ||
             'Fecha Documento, Fecha Vencimiento, Fecha Asignación, Fecha Cierre, ' ||
             'Dias de Atraso, Boleta de Despacho, Importe Deuda Original, Importe Abonos Anteriores, ' ||
             'Importe Deuda Asignada, Importe Deuda Recuperada,  Importe Pago Banco, Importe Otros Abonos, ' ||
             'Importe Deuda Pendiente, % Recup, Ultima Gestion, Refer. Fami, Refer. No Fam, Refer. Aval, Refer. Obser,' ||
             'Mail, Primer Pedido, Ultimo Pedido, Numero Pedidos,  Observación de la Gestión, Origen Pedido, Promedio Días Pago de últimos 18 pedidos';
             
  IF psvistaReporte = 'A' THEN
  
   OPEN C_REPOR_A;
   LOOP
    FETCH C_REPOR_A BULK COLLECT INTO detalleUnidadesRegRecord LIMIT W_FILAS;
    
    IF lbAbrirUtlFile THEN
    
     GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', lv_titu, lsdirtempo, v_handle);
     psdirectorio := lsdirtempo;
     lbAbrirUtlFile := FALSE;
    
    END IF ;
    
    IF detalleUnidadesRegRecord.COUNT > 0 THEN
    
     FOR x IN detalleUnidadesRegRecord.FIRST .. detalleUnidadesRegRecord.LAST LOOP
     
      lslinea :=   '=T("'|| detalleUnidadesRegRecord(x).cod_usua_cobr || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_desc || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_peri || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_regi_clie || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).cod_zona_clie || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_secc || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_terr || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).cod_clie || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_digi_ctrl || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).nom_clie || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_ape1_clie || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_ape2_clie || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).cod_tipo_docu_iden || '")' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).num_docu_iden || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_dpto || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_prov || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_dist || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_urba || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_cod_post || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_dire || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_dire_refe || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).num_tele_fijo || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).num_tele_trab || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).num_tele_movi || '"' || ',' ||                                               
                             '"'|| detalleUnidadesRegRecord(x).fec_docu || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).fec_venc || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).fec_asig || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).fec_cier || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).num_dias_mora || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).num_bole_desp || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_deud_orig || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_abon_ante || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_deud_asig || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_deud_canc || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_pago_banc || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_pago_otro || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_deud_pend || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_porc_recu || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_acci_cobr || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_refe_fami || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_refe_nofa || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_refe_aval || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_refe_obse || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_mail || '"' || ',' ||     
                             '"'|| detalleUnidadesRegRecord(x).camp_prime_pedi || '"' || ',' || 
                             '"'|| detalleUnidadesRegRecord(x).camp_ulti_pedi || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).num_pedi || '"' || ',' || 
                             '"'|| detalleUnidadesRegRecord(x).val_obse_ulti_gest || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).origen_pedido || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).DIAS_PAGOS || '"' ;
                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR_A%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR_A;
    END IF;

  IF psvistaReporte = 'H' THEN
  
   OPEN C_REPOR_H;
   LOOP
   
    FETCH C_REPOR_H BULK COLLECT INTO detalleUnidadesRegRecord LIMIT W_FILAS;
    
    IF lbAbrirUtlFile THEN
    
     GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', lv_titu, lsdirtempo, v_handle);
     psdirectorio := lsdirtempo;
     lbAbrirUtlFile := FALSE;
    
    END IF ;
    
    IF detalleUnidadesRegRecord.COUNT > 0 THEN
    
     FOR x IN detalleUnidadesRegRecord.FIRST .. detalleUnidadesRegRecord.LAST LOOP
      
     lslinea :=   '=T("'|| detalleUnidadesRegRecord(x).cod_usua_cobr || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_desc || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_peri || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_regi_clie || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).cod_zona_clie || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_secc || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_terr || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).cod_clie || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_digi_ctrl || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).nom_clie || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_ape1_clie || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_ape2_clie || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).cod_tipo_docu_iden || '")' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).num_docu_iden || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_dpto || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_prov || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_dist || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_urba || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_cod_post || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_dire || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_dire_refe || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).num_tele_fijo || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).num_tele_trab || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).num_tele_movi || '"' || ',' ||                                               
                             '"'|| detalleUnidadesRegRecord(x).fec_docu || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).fec_venc || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).fec_asig || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).fec_cier || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).num_dias_mora || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).num_bole_desp || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_deud_orig || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_abon_ante || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_deud_asig || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_deud_canc || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_pago_banc || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_pago_otro || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_deud_pend || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_porc_recu || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_acci_cobr || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_refe_fami || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_refe_nofa || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_refe_aval || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_refe_obse || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).Val_mail || '"' || ',' ||     
                             '"'|| detalleUnidadesRegRecord(x).camp_prime_pedi || '"' || ',' || 
                             '"'|| detalleUnidadesRegRecord(x).camp_ulti_pedi || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).num_pedi || '"' || ',' || 
                             '"'|| detalleUnidadesRegRecord(x).val_obse_ulti_gest || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).origen_pedido || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).DIAS_PAGOS || '"' ;
                             
                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        
        EXIT WHEN C_REPOR_H%NOTFOUND;
     
     END LOOP;
    
    CLOSE C_REPOR_H;
    
  END IF;

  IF NOT lbAbrirUtlFile THEN
   utl_file.fclose(V_HANDLE);
  END IF;

 EXCEPTION
 
  WHEN OTHERS THEN

   ln_sqlcode := SQLCODE;
   ls_sqlerrm := substr(sqlerrm,1,250);
   RAISE_APPLICATION_ERROR(-20123, 'ERROR cob_pr_detal_carte_cobra_csv: '||ls_sqlerrm);

 END cob_pr_detal_carte_cobra_csv ;


/*************************************************************************************
Descripcion       : Procedimiento que carga el Reporte Detallado
                    Cobranza 31 dias para formato CSV
Fecha Creacion    : 27/01/2014
Autor             : Carlos Bazalar
************************************************************************************/
PROCEDURE cob_pr_detal_cobra_31dia_csv(
    pscodigopais          VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
)
IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR IS
    SELECT
      COD_REGI,
      COD_ZONA,
      COD_SECC,
      NUM_DOCU_IDEN,
      COD_CLIE,
      COD_DIGI_CTRL,
      NOM_CLIE,
      NUM_TELE,
      PRIM_PEDI,
      FEC_DOCU,
      IMP_FAC_NETO,
      COB_DIAS_31,
      COB_DIAS_999,
      IMP_SALD_PEND_SAC,
      IMP_CARG_DIRE
    FROM COB_TMP_DETAL_COBRA_31DIA
    ORDER BY
      COD_REGI,
      COD_ZONA,
      COD_SECC,
      COD_CLIE;

    TYPE detalleUnidadesReg IS RECORD(
      COD_REGI                 COB_TMP_DETAL_COBRA_31DIA.COD_REGI%TYPE,
      COD_ZONA                 COB_TMP_DETAL_COBRA_31DIA.COD_ZONA%TYPE,
      COD_SECC                 COB_TMP_DETAL_COBRA_31DIA.COD_SECC%TYPE,
      NUM_DOCU_IDEN            COB_TMP_DETAL_COBRA_31DIA.NUM_DOCU_IDEN%TYPE,
      COD_CLIE                 COB_TMP_DETAL_COBRA_31DIA.COD_CLIE%TYPE,
      COD_DIGI_CTRL            COB_TMP_DETAL_COBRA_31DIA.COD_DIGI_CTRL%TYPE,
      NOM_CLIE                 COB_TMP_DETAL_COBRA_31DIA.NOM_CLIE%TYPE,
      NUM_TELE                 COB_TMP_DETAL_COBRA_31DIA.NUM_TELE%TYPE,
      PRIM_PEDI                COB_TMP_DETAL_COBRA_31DIA.PRIM_PEDI%TYPE,
      FEC_DOCU                 COB_TMP_DETAL_COBRA_31DIA.FEC_DOCU%TYPE,
      IMP_FAC_NETO             COB_TMP_DETAL_COBRA_31DIA.IMP_FAC_NETO%TYPE,
      COB_DIAS_31              COB_TMP_DETAL_COBRA_31DIA.COB_DIAS_31%TYPE,
      COB_DIAS_999             COB_TMP_DETAL_COBRA_31DIA.COB_DIAS_999%TYPE,
      IMP_SALD_PEND_SAC        COB_TMP_DETAL_COBRA_31DIA.IMP_SALD_PEND_SAC%TYPE,
      IMP_CARG_DIRE            COB_TMP_DETAL_COBRA_31DIA.IMP_CARG_DIRE%TYPE
    );

     TYPE detalleUnidadesRegTab IS TABLE OF detalleUnidadesReg;
     detalleUnidadesRegRecord detalleUnidadesRegTab;

     lbAbrirUtlFile  BOOLEAN;
BEGIN

lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR;
      LOOP
       FETCH C_REPOR BULK COLLECT INTO detalleUnidadesRegRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleUnidadesRegRecord.COUNT > 0 THEN
          FOR x IN detalleUnidadesRegRecord.FIRST .. detalleUnidadesRegRecord.LAST LOOP
                lslinea :=   '=T("'|| detalleUnidadesRegRecord(x).COD_REGI || '")' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).COD_ZONA || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_secc || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).num_docu_iden || '")' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).cod_clie || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_digi_ctrl || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).nom_clie || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).num_tele || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).PRIM_PEDI || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).FEC_DOCU || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).IMP_FAC_NETO || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).COB_DIAS_31 || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).COB_DIAS_999 || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).IMP_CARG_DIRE || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).IMP_SALD_PEND_SAC || '"' ;
                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR%NOTFOUND;
    END LOOP;
    CLOSE C_REPOR;

    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR cob_pr_detal_cobra_31dia_csv: '||ls_sqlerrm);

END cob_pr_detal_cobra_31dia_csv ;


 /*************************************************************************************
   Descripcion       : Procedimiento que carga el Reporte Detallado
                    Cobranza 31 dias para formato CSV
   Fecha Creacion    : 27/01/2014
   Autor             : Carlos Bazalar
 ************************************************************************************/
 PROCEDURE cob_pr_saldo_pendi_csv(
  pscodigopais                   IN   VARCHAR2,
  psnombrearchivo                IN   VARCHAR2,
  pstitulo                       IN   VARCHAR2,
  psdirectorio                   OUT  VARCHAR2)
 IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR IS
    SELECT
      COD_PERI,
      COD_REGI,
      COD_ZONA,
      COD_SECC,
      COD_TERR,
      COD_CLIE,
      COD_DIGI_CTRL,
      NOM_CLIE,
      VAL_APE1,
      VAL_APE2,
      NUM_DOCU_IDEN,
      NOM_DEPA,
      NOM_PROV,
      NOM_DIST,
      VAL_DIRE,
      VAL_TELE_FIJO,
      VAL_TELE_CELU,
      CAM_INGR,
      VAL_URBA,
      NUM_IDEN_CUOT,
      FEC_DOCU,
      IMP_MOVI,
      IMP_PEND,
      FEC_VENC,
      VAL_EMAI,
      FEC_NACI,
      ORI_PEDI,
      DIA_PAGO
    FROM COB_TMP_SALDO_PENDI;

    TYPE detalleUnidadesReg IS RECORD(
      COD_PERI                     COB_TMP_SALDO_PENDI.COD_PERI%TYPE,
      COD_REGI                     COB_TMP_SALDO_PENDI.COD_REGI%TYPE,
      COD_ZONA                     COB_TMP_SALDO_PENDI.COD_ZONA%TYPE,
      COD_SECC                     COB_TMP_SALDO_PENDI.COD_SECC%TYPE,
      COD_TERR                     COB_TMP_SALDO_PENDI.COD_TERR%TYPE,
      COD_CLIE                     COB_TMP_SALDO_PENDI.COD_CLIE%TYPE,
      COD_DIGI_CTRL                COB_TMP_SALDO_PENDI.COD_DIGI_CTRL%TYPE,
      NOM_CLIE                     COB_TMP_SALDO_PENDI.NOM_CLIE%TYPE,
      VAL_APE1                     COB_TMP_SALDO_PENDI.VAL_APE1%TYPE,
      VAL_APE2                     COB_TMP_SALDO_PENDI.VAL_APE2%TYPE,
      NUM_DOCU_IDEN                COB_TMP_SALDO_PENDI.NUM_DOCU_IDEN%TYPE,
      NOM_DEPA                     COB_TMP_SALDO_PENDI.NOM_DEPA%TYPE,
      NOM_PROV                     COB_TMP_SALDO_PENDI.NOM_PROV%TYPE,
      NOM_DIST                     COB_TMP_SALDO_PENDI.NOM_DIST%TYPE,
      VAL_DIRE                     COB_TMP_SALDO_PENDI.VAL_DIRE%TYPE,
      VAL_TELE_FIJO                COB_TMP_SALDO_PENDI.VAL_TELE_FIJO%TYPE,
      VAL_TELE_CELU                COB_TMP_SALDO_PENDI.VAL_TELE_CELU%TYPE,
      CAM_INGR                     COB_TMP_SALDO_PENDI.CAM_INGR%TYPE,
      VAL_URBA                     COB_TMP_SALDO_PENDI.VAL_URBA%TYPE,
      NUM_IDEN_CUOT                COB_TMP_SALDO_PENDI.NUM_IDEN_CUOT%TYPE,
      FEC_DOCU                     COB_TMP_SALDO_PENDI.FEC_DOCU%TYPE,
      IMP_MOVI                     COB_TMP_SALDO_PENDI.IMP_MOVI%TYPE,
      IMP_PEND                     COB_TMP_SALDO_PENDI.IMP_PEND%TYPE,
      FEC_VENC                     COB_TMP_SALDO_PENDI.FEC_VENC%TYPE,
      VAL_EMAI                     COB_TMP_SALDO_PENDI.VAL_EMAI%TYPE,
      FEC_NACI                     COB_TMP_SALDO_PENDI.FEC_NACI%TYPE,
      ORI_PEDI                     COB_TMP_SALDO_PENDI.ORI_PEDI%TYPE,
      DIA_PAGO                     COB_TMP_SALDO_PENDI.DIA_PAGO%TYPE
    );

     TYPE detalleUnidadesRegTab IS TABLE OF detalleUnidadesReg;
     detalleUnidadesRegRecord detalleUnidadesRegTab;

     lbAbrirUtlFile  BOOLEAN;

 BEGIN

  lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR;
      LOOP
       FETCH C_REPOR BULK COLLECT INTO detalleUnidadesRegRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleUnidadesRegRecord.COUNT > 0 THEN
          FOR x IN detalleUnidadesRegRecord.FIRST .. detalleUnidadesRegRecord.LAST LOOP
                lslinea :=
                             '=T("'|| detalleUnidadesRegRecord(x).COD_REGI || '")' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).COD_ZONA || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_secc || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).COD_TERR || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).cod_clie || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_digi_ctrl || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).nom_clie || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).VAL_APE1 || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).VAL_APE2 || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).num_docu_iden || '")' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).CAM_INGR || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).NOM_DEPA || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).NOM_PROV || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).NOM_DIST || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).VAL_URBA || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).VAL_DIRE || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).VAL_TELE_FIJO || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).VAL_TELE_CELU || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).COD_PERI || '")' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).NUM_IDEN_CUOT || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).FEC_DOCU || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).FEC_VENC || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).IMP_MOVI || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).IMP_PEND || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).FEC_NACI || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).VAL_EMAI || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).ORI_PEDI || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).DIA_PAGO || '"' ;
                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR%NOTFOUND;
    END LOOP;
    CLOSE C_REPOR;

    IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;
 EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR cob_pr_saldo_pendi_csv: '||ls_sqlerrm);

 END cob_pr_saldo_pendi_csv ;

/*************************************************************************************
  Descripcion           : Procedimiento que carga el Reporte Carga Masiva de Gestion para formato CSV
  Fecha Creacion    : 23/10/2014
  Autor                   : Sebastian Guerra
************************************************************************************/
PROCEDURE cob_pr_carga_masiv_gesti_csv(
    pscodigopais          VARCHAR2,
    pscodigoSociedad      VARCHAR2,
    pscodigoPeriodoInicio VARCHAR2,
    pscodigoPeriodoFin    VARCHAR2,
    pscodigoEtapaDeuda    VARCHAR2,
    pscodigoCobrador      VARCHAR2,
    psnombrearchivo       VARCHAR2,
    pstitulo              VARCHAR2,
    psdirectorio          OUT VARCHAR2
)
IS
    lsdirtempo      bas_inter.dir_temp%TYPE;
    w_filas         number := 5000;
    v_handle        utl_file.file_type;
    lslinea         varchar2(4000);

    CURSOR C_REPOR IS
    SELECT gen.val_i18n des_pais,
       socie.val_deno des_soci,
       (select val_nomb_usua_cobr  from cob_usuar_cobra_pais where cod_usua_cobr = gest.cod_usua_cobr_pais) cobrador,
       gest.cod_etap_deud,
       etapa.val_desc des_etap_deud,
       gest.cod_peri,
       carte.des_regi_clie,
       carte.cod_zona_clie,
       carte.cod_secc,
       gest.cod_clie,
       NVL((SELECT TRIM(MC.VAL_NOM1) || ' ' || TRIM(MC.VAL_NOM2)
             FROM MAE_CLIEN MC
            WHERE MC.OID_CLIE = CARTE.OID_CLIE),
           ' ') nom_clie,
       NVL((SELECT TRIM(MC.VAL_APE1)
             FROM MAE_CLIEN MC
            WHERE MC.OID_CLIE = CARTE.OID_CLIE),
           ' ') val_ape1_clie,
       NVL((SELECT TRIM(MC.VAL_APE2)
             FROM MAE_CLIEN MC
            WHERE MC.OID_CLIE = CARTE.OID_CLIE),
           ' ') val_ape2_clie,
       carte.des_tipo_docu_iden,
       carte.num_docu_iden,
       (SELECT COUNT(*) val_cant_pedi
          FROM ped_solic_cabec_acum2 sca2
         WHERE sca2.clie_oid_clie = carte.oid_clie
           AND sca2.perd_oid_peri <=
               (select cp.oid_peri
                  from cra_perio cp, seg_perio_corpo sp
                 where cp.peri_oid_peri = sp.oid_peri
                   and sp.cod_peri = carte.cod_peri)
         GROUP BY sca2.clie_oid_clie) Nro_Pedido,
       TO_CHAR(carte.fec_docu,'DD/MM/YYYY') fec_docu,
       TO_CHAR(carte.fec_asig,'DD/MM/YYYY') fec_asig,
       TO_CHAR(carte.fec_cier,'DD/MM/YYYY') fec_cier,
       carte.imp_deud_orig,
       carte.imp_deud_asig,
       carte.imp_deud_canc,
       carte.imp_deud_pend,
       gest.fec_gest,
       gest.cod_acci_cobr,
       accio.des_acci_cobr,
       gest.val_obse,
       gen_pkg_gener.gen_fn_devue_pedid_orig (carte.cod_clie,carte.cod_peri) origen_pedido,
       gen_pkg_gener.gen_fn_devue_prome_pagos_ultim(carte.oid_clie,pscodigoPeriodoFin)DIAS_PAGOS
  FROM cob_gesti_cobra_pais  gest,
       cob_detal_asign_carte carte,
       cob_etapa_deuda_pais  etapa,
       cob_accio_cobra_pais  accio,
       cob_usuar_cobra_Pais  cobra,
       seg_socie             socie,
       seg_pais              pais,
       zon_regio             regio,
       zon_zona              zona,
       gen_i18n_sicc_comun   gen
 WHERE carte.cod_soci = socie.cod_soci
   AND carte.cod_pais = pais.cod_pais
   AND carte.cod_etap_deud = etapa.cod_etap_deud
   AND gest.cod_acci_cobr = accio.cod_acci_cobr
   AND gest.cod_etap_deud = accio.cod_etap_deud
   AND carte.cod_regi_clie = regio.cod_regi
   AND carte.cod_zona_clie = zona.cod_zona
   AND regio.oid_regi = zona.zorg_oid_regi
   AND gen.attr_enti ='SEG_PAIS'
   AND gen.val_oid = pais.oid_pais
   AND carte.cod_usua_cobr = cobra.cod_usua_cobr
   AND cobra.ind_supe = 0
   and carte.cod_etap_deud = gest.cod_etap_deud
   and carte.cod_peri = gest.cod_peri
   and carte.cod_clie = gest.cod_clie
   AND carte.cod_pais = pscodigopais
   AND carte.cod_soci = pscodigoSociedad
   AND carte.cod_peri >= pscodigoPeriodoInicio
   AND carte.cod_peri <= pscodigoPeriodoFin
   AND carte.cod_etap_deud = pscodigoEtapaDeuda
   AND carte.cod_usua_cobr =
       DECODE(pscodigoCobrador,
              NULL,
              carte.cod_usua_cobr,
              'Todos',
              carte.cod_usua_cobr,
              pscodigoCobrador)
 ORDER BY val_nomb_usua_cobr,
          cod_peri,
          des_regi_clie,
          cod_zona_clie,
          cod_secc,
          cod_clie;

 TYPE detalleUnidadesReg IS RECORD(
          des_pais              VARCHAR2(100),
          des_soci              seg_socie.val_deno%TYPE,
          cobrador              cob_gesti_cobra_pais.cod_usua_cobr_pais%TYPE,
          cod_etap_deud         cob_gesti_cobra_pais.cod_etap_deud%TYPE,
          des_etap_deud         cob_etapa_deuda_pais.val_desc%TYPE,
          cod_peri              VARCHAR2(6),
          des_regi_clie         cob_detal_asign_carte.des_regi_clie%TYPE,
          cod_zona_clie         cob_detal_asign_carte.cod_zona_clie%TYPE,
          cod_secc              cob_detal_asign_carte.cod_secc%TYPE,
          cod_clie              cob_detal_asign_carte.cod_clie%TYPE,
          nom_clie              VARCHAR2(100),
          val_ape1_clie         VARCHAR2(100),
          val_ape2_clie         VARCHAR2(100),
          des_tipo_docu_iden    cob_detal_asign_carte.des_tipo_docu_iden%TYPE,
          num_docu_iden         cob_detal_asign_carte.num_docu_iden%TYPE,
          Nro_Pedido            NUMBER(10),
          fec_docu              VARCHAR2(10),
          fec_asig              VARCHAR2(10),
          fec_cier              VARCHAR2(10),
          imp_deud_orig         cob_detal_asign_carte.imp_deud_orig%TYPE,
          imp_deud_asig         cob_detal_asign_carte.imp_deud_asig%TYPE,
          imp_deud_canc         cob_detal_asign_carte.imp_deud_canc%TYPE,
          imp_deud_pend         cob_detal_asign_carte.imp_deud_pend%TYPE,
          fec_gest              cob_gesti_cobra_pais.fec_gest%TYPE,
          cod_acci_cobr         cob_accio_cobra_pais.Cod_Acci_Cobr%TYPE,
          des_acci_cobr         cob_accio_cobra_pais.des_acci_cobr%TYPE,
          val_obse              cob_gesti_cobra_pais.val_obse%TYPE,
          origen_pedido         STO_ORIGE_DOCUM.DES_ORIG%TYPE,
          DIAS_PAGOS            NUMBER(6)

      );

     TYPE detalleUnidadesRegTab IS TABLE OF detalleUnidadesReg;
     detalleUnidadesRegRecord detalleUnidadesRegTab;

     lbAbrirUtlFile  BOOLEAN;
BEGIN

lbAbrirUtlFile := true;
    EXECUTE IMMEDIATE 'alter session set nls_territory=AMERICA';
    OPEN C_REPOR;
      LOOP
       FETCH C_REPOR BULK COLLECT INTO detalleUnidadesRegRecord LIMIT W_FILAS;
       IF lbAbrirUtlFile THEN
          GEN_PKG_INTER_ARCHI.GEN_PR_INICI_REPOR_ORACL(pscodigopais, psnombrearchivo, '.csv', pstitulo, lsdirtempo, v_handle);
          psdirectorio := lsdirtempo;
          lbAbrirUtlFile := FALSE;
       END IF ;
       IF detalleUnidadesRegRecord.COUNT > 0 THEN
          FOR x IN detalleUnidadesRegRecord.FIRST .. detalleUnidadesRegRecord.LAST LOOP
                lslinea :=   '"'|| detalleUnidadesRegRecord(x).cobrador || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_etap_deud || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_peri || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_regi_clie || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).cod_zona_clie || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_secc || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).cod_clie || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).nom_clie || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_ape1_clie || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_ape2_clie || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_tipo_docu_iden || '"' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).num_docu_iden || '")' || ',' ||
                             '=T("'|| detalleUnidadesRegRecord(x).Nro_Pedido || '")' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).fec_docu || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).fec_asig || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).fec_cier || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_deud_orig || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_deud_asig || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_deud_canc || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).imp_deud_pend || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).fec_gest || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).cod_acci_cobr || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).des_acci_cobr || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).val_obse || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).origen_pedido || '"' || ',' ||
                             '"'|| detalleUnidadesRegRecord(x).DIAS_PAGOS || '"' ;

                 UTL_FILE.PUT_LINE (V_HANDLE, lslinea );

          END LOOP;
        END IF;
        EXIT WHEN C_REPOR%NOTFOUND;
     END LOOP;
    CLOSE C_REPOR;

     IF NOT lbAbrirUtlFile THEN
        utl_file.fclose(V_HANDLE);
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        ln_sqlcode := SQLCODE;
        ls_sqlerrm := substr(sqlerrm,1,250);
        RAISE_APPLICATION_ERROR(-20123, 'ERROR cob_pr_carga_masiv_gesti_csv: '||ls_sqlerrm);

END cob_pr_carga_masiv_gesti_csv ;

END COB_PKG_REPOR_ESTAD;
/
